import org.gradle.api.JavaVersion

object AndroidConfig {
    val compileVersion = 28
    val minSdkVersion = 21
    val targetSdkVersion = 28
    val buildToolsSdkVersion = "28.0.3"
    val sourceCompatibility = JavaVersion.VERSION_1_8
    val targetCompatibility = JavaVersion.VERSION_1_8
    val versionCode = 1
    val versionName = "1.0.7"
}

object Versions {
    object Shared {
        val kotlinVersion = "1.3.10"
    }

    object App {
        val kotlinCoroutinesVersion = "1.0.1"
        val conductorVersion = "2.1.5"
        val lifecycleVersion = "2.0.0"
        val jetpackVersion = "1.0.1"
        val dagger2Version = "2.19"
    }

    object Test {

    }

    object BuildScript {
        val dependenciesVersion = "0.20.0"
        val buildToolsVersion = "3.2.1"
        val androidMavenPublishVersion = "3.6.2"
    }
}