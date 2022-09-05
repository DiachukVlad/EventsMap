package di

import com.mongodb.MongoClientSettings
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoCollection
import com.mongodb.reactivestreams.client.MongoDatabase
import com.tarlad.eventsmap.shared.models.Event
import mongodb.MongoDBUtil
import org.bson.UuidRepresentation
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.reactivestreams.getCollection

val serverModule = module {
    single {
        KMongo.createClient(
        ).coroutine
    }
    single {
        get<CoroutineClient>().getDatabase(MongoDBUtil.DB_NAME)
    }

    // Collections
    single<CoroutineCollection<Event>> { get<CoroutineDatabase>().getCollection() }


    // DAOs

}