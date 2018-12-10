plugins {
    id("com.github.ben-manes.versions") version (Versions.BuildScript.dependenciesVersion)
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}