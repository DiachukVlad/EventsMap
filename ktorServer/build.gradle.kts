plugins {
    application
    kotlin("jvm")
}

group = "com.tarlad.eventsmap"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(Kotlin.coroutinesCore)
    implementation(Kotlin.serialization)

    with(KtorServer) {
        implementation(core)
        implementation(contentNegotiation)
        implementation(netty)
        implementation(cors)
        implementation(doubleReceive)
        implementation(serialization)
        implementation(webSockets)
    }
    implementation(Koin.core)
    implementation(Koin.ktor)


    implementation("ch.qos.logback:logback-classic:${Versions.logback}")
    implementation("org.litote.kmongo:kmongo-coroutine-serialization:${Versions.mongodb}")
    implementation("org.litote.kmongo:kmongo-id-serialization:${Versions.mongodb}")
    implementation("org.litote.kmongo:kmongo:${Versions.mongodb}")
    implementation(project(":shared"))
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "16"
        }
    }
}