rootProject.name = "EventsMap"

pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
    }

}

include(":shared")
include(":ktorServer")
include(":androidApp")