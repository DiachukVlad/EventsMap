plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.kotlin.native.cocoapods")
    kotlin("plugin.serialization")
}

group = "com.tarlad"
version = "1.0-SNAPSHOT"

kotlin {
    android()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }
    jvm()

    cocoapods {
        summary = "Kotlin CocoaPods library"
        homepage = "https://github.com/Kotlin/multitarget-xcode-with-kotlin-cocoapods-sample"

        podfile = project.file("../iosApp/Podfile")

        ios.deploymentTarget = "13.5"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                with(KtorClient) {
                    implementation(core)
                    implementation(serialization)
                    implementation(contentNegotiation)
                    implementation(webSockets)
                }

                with(Kotlin) {
                    implementation(coroutinesCore)
                    implementation(serialization)
                }

                implementation(Koin.core)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(KtorClient.okhttp)
                implementation(Koin.android)
                implementation(KtorClient.cio)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(KtorClient.okhttp)
            }
        }
        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation(KtorClient.cio)
            }
        }

        val iosX64Main by getting { dependsOn(iosMain) }
        val iosArm64Main by getting { dependsOn(iosMain) }
        val iosSimulatorArm64Main by getting { dependsOn(iosMain) }
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