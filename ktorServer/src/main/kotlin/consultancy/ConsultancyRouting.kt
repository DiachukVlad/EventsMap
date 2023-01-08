package consultancy

import com.tarlad.eventsmap.shared.consultancy.Consultancy
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Routing.consultancyRouting() {
    get("/consultancies") {
        var consultancies: List<Consultancy> = emptyList()
        transaction {
            consultancies = ConsultancyEntity.all().asConsultancies()
        }
        call.respond(consultancies)
    }
}

