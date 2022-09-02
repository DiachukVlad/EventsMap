plugins {
    application
    kotlin("multiplatform")
}

group = "com.tarlad.eventsmap"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("ApplicationKt")
}

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        withJava()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Kotlin.coroutinesCore)

                with(KtorServer) {
                    implementation(core)
                    implementation(contentNegotiation)
                    implementation(netty)
                    implementation(cors)
                    implementation(doubleReceive)
                    implementation(serialization)
                    implementation(webSockets)
                }


                implementation("ch.qos.logback:logback-classic:${Versions.logback}")
                implementation(project(":shared"))
            }
        }
        val jvmMain by getting
    }
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "16"
        }
    }
}