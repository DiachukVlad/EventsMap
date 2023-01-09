package di

import io.ktor.server.application.*
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import util.AppConfig

val serverModule = module {
    factory { get<Application>().environment }
    factoryOf(::AppConfig)
}