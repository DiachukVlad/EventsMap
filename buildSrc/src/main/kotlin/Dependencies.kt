
object Versions {
    const val kotlinVersion = "1.7.10"

    const val kotlinCoroutines = "1.6.4"
    const val kotlinxDateTime = "0.4.0"
    const val kotlinSerialization = "1.3.3"

    const val compose = "1.2.1"
    const val composeCompiler = "1.3.0"
    const val navCompose = "2.5.1"
    const val accompanist = "0.25.1"

    const val koin = "3.2.0"
    const val junit = "4.13"

    const val androidGradle = "7.2.2"

    const val ktor = "2.1.0"
    const val logback = "1.2.3"

    const val mongodb = "4.7.0"
}


object AndroidSdk {
    const val min = 26
    const val compile = 33
    const val target = compile
}

object Kotlin {
    const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlinVersion}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerialization}"
}

object Compose {
    const val compiler = "androidx.compose.compiler:compiler:${Versions.composeCompiler}"
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
    const val uiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val materialIconsCore = "androidx.compose.material:material-icons-core:${Versions.compose}"
    const val materialIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navCompose}"
}

object Koin {
    const val core = "io.insert-koin:koin-core:${Versions.koin}"
    const val ktor = "io.insert-koin:koin-ktor:${Versions.koin}"
    const val test = "io.insert-koin:koin-test:${Versions.koin}"
    const val android = "io.insert-koin:koin-android:${Versions.koin}"
    const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
}

object KtorClient {
    const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val serialization = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
    const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
    const val cio = "io.ktor:ktor-client-cio:${Versions.ktor}"
    const val android = "io.ktor:ktor-client-android:${Versions.ktor}"
    const val okhttp = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
    const val webSockets = "io.ktor:ktor-client-websockets:${Versions.ktor}"
}

object KtorServer {
    const val core = "io.ktor:ktor-server-core-jvm:${Versions.ktor}"
    const val contentNegotiation = "io.ktor:ktor-server-content-negotiation-jvm:${Versions.ktor}"
    const val netty = "io.ktor:ktor-server-netty-jvm:${Versions.ktor}"
    const val cors = "io.ktor:ktor-server-cors:${Versions.ktor}"
    const val doubleReceive = "io.ktor:ktor-server-double-receive:${Versions.ktor}"
    const val serialization = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
    const val webSockets = "io.ktor:ktor-server-websockets:${Versions.ktor}"
}


