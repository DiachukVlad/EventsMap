plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = AndroidSdk.compile
    defaultConfig {
        applicationId = "com.tarlad.eventsmap"
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target

        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    namespace = "com.tarlad.eventsmap"
}


kotlin {
    sourceSets.all {
        languageSettings {
            optIn("androidx.compose.material.ExperimentalMaterialApi")
            optIn("kotlin.RequiresOptIn")
        }
    }
}

dependencies {
    implementation(project(":shared"))

    implementation("androidx.activity:activity-compose:1.5.1")
    implementation("com.google.android.material:material:1.6.1")

    implementation("com.diachuk.routing:android-compose-routing:1.0")

    with (Compose) {
        implementation(compiler)
        implementation(ui)
        implementation(uiGraphics)
        implementation(uiTooling)
        implementation(foundationLayout)
        implementation(material)
        implementation(materialIconsCore)
        implementation(materialIconsExtended)
        implementation(navigation)
    }

    with (Koin) {
        implementation(core)
        implementation(android)
        implementation(compose)
    }

    with(KtorClient) {
        implementation(core)
        implementation(serialization)
        implementation(contentNegotiation)
        implementation(android)
    }
    implementation(KtorClient.okhttp)
    implementation(KtorClient.cio)
}