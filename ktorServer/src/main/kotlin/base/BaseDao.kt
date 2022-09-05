package base

import com.mongodb.reactivestreams.client.MongoCollection
import com.tarlad.eventsmap.shared.models.BaseModel

open class BaseDao<T: BaseModel>(val collection: MongoCollection<T>) {
}