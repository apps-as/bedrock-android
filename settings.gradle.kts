rootProject.buildFileName = "build.gradle.kts"
rootProject.name = "bedrock"
include(":bedrock")


pluginManagement {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
    }
}
