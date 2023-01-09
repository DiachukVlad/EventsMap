package auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.tarlad.eventsmap.shared.auth.Role
import com.tarlad.eventsmap.shared.auth.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.ktor.ext.inject
import util.AppConfig
import java.util.*
import kotlin.time.Duration.Companion.days

const val USER_ID_CLAIM_NAME = "userId"

fun Routing.authRouting() {
    registerStudent()
    login()
}

private fun Routing.login() {
    val appConfig: AppConfig by inject()
    get("/users", acceptedRoles = hashSetOf(Role.Public)) {
        val users = transaction {
            UserDao.all().asUsers()
        }
    }
    post("/login") {
        val userCredentials = call.receive<User>()

        // user validation
        if (userCredentials.email.isEmpty() || userCredentials.pass.isEmpty()) {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        // Get UserEntity
        val user = transaction {
            UserDao.find(Users.email eq userCredentials.email).firstOrNull()?.asUser()
        }
        if (user == null) {
            call.respond(HttpStatusCode.NotFound)
            return@post
        }

        // Check password
        if (user.pass != userCredentials.pass) {
            call.respond(HttpStatusCode.Forbidden)
        }

        val token = JWT.create()
            .withAudience(appConfig.audience)
            .withIssuer(appConfig.issuer)
            .withClaim(USER_ID_CLAIM_NAME, user.id)
            .withExpiresAt(Date(System.currentTimeMillis() + 30.days.inWholeMilliseconds))
            .sign(Algorithm.HMAC256(appConfig.secret))
        call.respond(user.also { it.token = token })
    }
}

private fun Routing.registerStudent() {
    val appConfig: AppConfig by inject()

    post("/registerStudent") {
        // get credentials
        val user = call.receive<User>()

        // user validation
        if (user.email.isEmpty() || user.pass.isEmpty()) {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        // create user in DB
        val newUser = transaction {
            UserDao.new {
                email = user.email
                pass = user.pass
                role = Role.Student
            }
        }.asUser()

        // create token
        val token = JWT.create()
            .withAudience(appConfig.audience)
            .withIssuer(appConfig.issuer)
            .withClaim(USER_ID_CLAIM_NAME, newUser.id)
            .withExpiresAt(Date(System.currentTimeMillis() + 30.days.inWholeMilliseconds))
            .sign(Algorithm.HMAC256(appConfig.secret))

        // send user data back
        call.respond(newUser.also { it.token = token })
    }
}