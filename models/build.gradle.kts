plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.jetbrains.kotlin.native.cocoapods")
}

group = "com.tarlad"
version = "1.0-SNAPSHOT"

kotlin {
    jvm()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "models"
        }
    }

    cocoapods {
        summary = "Kotlin CocoaPods library"
        homepage = "https://github.com/Kotlin/multitarget-xcode-with-kotlin-cocoapods-sample"

        podfile = project.file("../iosApp/Podfile")

        ios.deploymentTarget = "13.5"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Kotlin.serialization)
            }
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}