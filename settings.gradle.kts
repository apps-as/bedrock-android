rootProject.buildFileName = "build.gradle.kts"
rootProject.name = "bedrock"
include(":bedrock")


pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
    }
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.android.library" -> useModule("com.android.tools.build:gradle:${requested.version}")
                "kotlin-android", "kotlin-android-extensions", "kotlin-kapt" -> useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
                "de.mannodermaus.android-junit5" -> useModule("de.mannodermaus.gradle.plugins:android-junit5:${requested.version}")
            }
        }
    }
}
