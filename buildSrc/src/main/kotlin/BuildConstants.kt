import Versions.App.conductorVersion
import Versions.App.dagger2Version
import Versions.App.coreVersion
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
    val versionName = "1.1.5"
}

object Versions {
    object Shared {
        val kotlinVersion = "1.3.21"
    }

    object App {
        val kotlinCoroutinesVersion = "1.1.1"
        val conductorVersion = "3.0.0-rc1"
        val lifecycleVersion = "2.1.0-alpha02"
        val coreVersion = "1.1.0-alpha04"
        val dagger2Version = "2.21"
    }

    object BuildScript {
        val dependenciesVersion = "0.20.0"
        val buildToolsVersion = "3.3.0"
        val androidMavenPublishVersion = "3.6.2"
    }
}

val kotlinCore get() = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
val coroutinesCore get() = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion"
val coroutinesAndroid get() = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinCoroutinesVersion"
val lifecycle get() = "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"
val viewModelKtx get() = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
val coreKtx get() = "androidx.core:core-ktx:$coreVersion"
val conductor get() = "com.bluelinelabs:conductor:$conductorVersion"
val dagger get() = "com.google.dagger:dagger:$dagger2Version"
val daggerAndroid get() = "com.google.dagger:dagger-android-support:$dagger2Version"
val daggerAndroidProcessor get() = "com.google.dagger:dagger-android-processor:$dagger2Version"
val daggerProcessor get() = "com.google.dagger:dagger-compiler:$dagger2Version"