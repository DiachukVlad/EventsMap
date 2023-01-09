package auth

import com.tarlad.eventsmap.shared.auth.Role
import com.tarlad.eventsmap.shared.auth.User
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Users : IntIdTable() {
    val email = varchar("email", 50)
    val pass = varchar("pass", 50)
    val role = enumeration<Role>("role")
}

class UserDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserDao>(Users)

    var email by Users.email
    var pass by Users.pass
    var role by Users.role
}

fun UserDao.asUser() = User(email = email, pass = pass, role = role, id = id.value)