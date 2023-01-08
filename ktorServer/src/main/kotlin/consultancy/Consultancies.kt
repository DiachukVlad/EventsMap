package consultancy

import com.tarlad.eventsmap.shared.consultancy.Consultancy
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Consultancies : IntIdTable() {
    val name = varchar("name", 50)
}

class ConsultancyEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ConsultancyEntity>(Consultancies)

    var name by Consultancies.name
}

fun ConsultancyEntity.asConsultancy() = Consultancy(name)
fun List<ConsultancyEntity>.asConsultancies() = this.map { it.asConsultancy() }
fun Iterable<ConsultancyEntity>.asConsultancies() = this.map { it.asConsultancy() }