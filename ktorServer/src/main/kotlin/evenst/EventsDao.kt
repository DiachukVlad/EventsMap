package evenst

import base.BaseDao
import com.mongodb.reactivestreams.client.MongoCollection
import com.tarlad.eventsmap.shared.models.Event
import org.litote.kmongo.coroutine.toList

class EventsDao(collection: MongoCollection<Event>): BaseDao<Event>(collection) {
    suspend fun getAllEvents(): List<Event> = collection.find().toList()
    suspend fun insertEvent(vararg events: Event) {
        if (events.size > 1) {
            collection.insertMany(events.toList()).toList()
        } else if (events.size == 1) {
            collection.insertOne(events[0])
        }
    }
}