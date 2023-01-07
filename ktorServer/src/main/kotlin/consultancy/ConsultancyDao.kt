package consultancy

import base.BaseDao
import com.mongodb.reactivestreams.client.MongoCollection
import com.tarlad.eventsmap.shared.consultancy.Consultancy
import org.litote.kmongo.coroutine.toList

class ConsultancyDao(collection: MongoCollection<Consultancy>) : BaseDao<Consultancy>(collection) {
    suspend fun getAllConsultancies(): List<Consultancy> = collection.find().toList()
    suspend fun insertConsultancy(vararg events: Consultancy) {
        if (events.size > 1) {
            collection.insertMany(events.toList()).toList()
        } else if (events.size == 1) {
            collection.insertOne(events[0])
        }
    }
}