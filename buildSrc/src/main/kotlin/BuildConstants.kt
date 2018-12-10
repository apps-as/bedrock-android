import Versions.App.conductorVersion
import Versions.App.dagger2Version
import Versions.App.jetpackVersion
import Versions.App.kotlinCoroutinesVersion
import Versions.App.lifecycleVersion
import Versions.Shared.kotlinVersion
import org.gradle.api.JavaVersion

object AndroidConfig {
    val compileVersion = 28
    val minSdkVersion = 21
    val targetSdkVersion = 28
    val buildToolsSdkVersion = "28.0.3"
    val sourceCompatibility = JavaVersion.VERSION_1_8
    val targetCompatibility = JavaVersion.VERSION_1_8
    val versionCode = 1
    val versionName = "1.0.11"
}

object Versions {
    object Shared {
        val kotlinVersion = "1.3.11"
    }

    object App {
        val kotlinCoroutinesVersion = "1.0.1"
        val conductorVersion = "2.1.5"
        val lifecycleVersion = "2.0.0"
        val jetpackVersion = "1.0.1"
        val dagger2Version = "2.19"
    }

    object BuildScript {
        val dependenciesVersion = "0.20.0"
        val buildToolsVersion = "3.3.0-rc02"
        val androidMavenPublishVersion = "3.6.2"
    }
}

val kotlinCore get() = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
val coroutinesCore get() = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion"
val coroutinesAndroid get() = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinCoroutinesVersion"
val lifecycle get() = "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"
val viewModelKtx get() = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
val coreKtx get() = "androidx.core:core-ktx:$jetpackVersion"
val conductor get() = "com.bluelinelabs:conductor:$conductorVersion"
val conductorLifecycle get() = "com.bluelinelabs:conductor-archlifecycle:$conductorVersion"
val dagger get() = "com.google.dagger:dagger:$dagger2Version"
val daggerAndroid get() = "com.google.dagger:dagger-android-support:$dagger2Version"
val daggerAndroidProcessor get() = "com.google.dagger:dagger-android-processor:$dagger2Version"
val daggerProcessor get() = "com.google.dagger:dagger-compiler:$dagger2Version"