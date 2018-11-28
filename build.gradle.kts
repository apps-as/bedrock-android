buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        // Android
        classpath("com.android.tools.build:gradle:${Versions.BuildScript.buildToolsVersion}")

        // Jetifier
        // TODO remove this when new jetifier is included in latest AGP - https://issuetracker.google.com/issues/115738511
        classpath("com.android.tools.build.jetifier:jetifier-processor:1.0.0-beta02")

        // Kotlin
        classpath(kotlin("gradle-plugin", version = Versions.Shared.kotlinVersion))

        // Android Maven Publish
        classpath("digital.wup:android-maven-publish:${Versions.BuildScript.androidMavenPublishVersion}")
    }
}

plugins {
    id("com.github.ben-manes.versions") version (Versions.BuildScript.dependenciesVersion)
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
