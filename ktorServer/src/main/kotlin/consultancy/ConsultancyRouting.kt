package consultancy

import auth.get
import com.tarlad.eventsmap.shared.auth.Role
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Routing.consultancyRouting() {
    get("/consultancies", acceptedRoles = hashSetOf(Role.Student)) {
        val consultancies = transaction { ConsultancyDao.all().asConsultancies() }
        call.respond(consultancies)
    }
}

