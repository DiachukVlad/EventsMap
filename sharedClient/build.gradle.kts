plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

group = "com.tarlad"
version = "1.0-SNAPSHOT"

kotlin {
    jvm()
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                with(KtorClient) {
                    implementation(core)
                    implementation(serialization)
                    implementation(contentNegotiation)
                    implementation(webSockets)
                }

                implementation(Kotlin.coroutinesCore)
                implementation(project(":models"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(KtorClient.okhttp)
            }
        }
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 26
        targetSdk = 33
    }
    lint {
        abortOnError = false
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}