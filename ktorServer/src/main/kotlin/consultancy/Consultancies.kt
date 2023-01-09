package consultancy

import com.tarlad.eventsmap.shared.consultancy.Consultancy
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Consultancies : IntIdTable() {
    val name = varchar("name", 50)
}

class ConsultancyDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ConsultancyDao>(Consultancies)

    var name by Consultancies.name
}

fun ConsultancyDao.asConsultancy() = Consultancy(id.value, name)
fun Iterable<ConsultancyDao>.asConsultancies() = this.map { it.asConsultancy() }