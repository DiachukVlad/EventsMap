package auth

import com.tarlad.eventsmap.shared.auth.Role
import com.tarlad.eventsmap.shared.auth.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*
import org.jetbrains.exposed.sql.transactions.transaction

class UserContext(
    val call: ApplicationCall,
    val user: User
)

fun PipelineContext<Unit, ApplicationCall>.getUser(): User {
    val userId: Int? =
        call.principal<JWTPrincipal>()?.payload?.claims?.get(USER_ID_CLAIM_NAME)?.asInt()

    return userId?.let { id ->
        transaction {
            UserDao.find { Users.id eq id }.firstOrNull()?.asUser()
        }
    } ?: User(role = Role.Public)
}

fun Routing.get(path: String, acceptedRoles: Set<Role>, body: suspend UserContext.() -> Unit) {
    authenticate(AUTH_JWT, optional = acceptedRoles.contains(Role.Public)) {
        get(path) ktorGet@{
            val user = getUser()
            if (!acceptedRoles.contains(Role.Public) && !acceptedRoles.contains(user.role)) {
                call.respond(HttpStatusCode.Forbidden)
                return@ktorGet
            }
            body.invoke(UserContext(call, user))
        }
    }
}

fun Routing.post(path: String, acceptedRoles: Set<Role>, body: suspend UserContext.() -> Unit) {
    authenticate(AUTH_JWT, optional = acceptedRoles.contains(Role.Public)) {
        post(path) ktorPost@{
            val user = getUser()
            if (!acceptedRoles.contains(Role.Public) && !acceptedRoles.contains(user.role)) {
                call.respond(HttpStatusCode.Forbidden)
                return@ktorPost
            }
            body.invoke(UserContext(this.call, user))
        }
    }
}