@file:Suppress("SpellCheckingInspection")

import org.gradle.api.JavaVersion

object AndroidConfig {
    const val compileVersion = 29
    const val minSdkVersion = 23
    const val targetSdkVersion = 29
    const val buildToolsSdkVersion = "29.0.3"
    val sourceCompatibility = JavaVersion.VERSION_1_8
    val targetCompatibility = JavaVersion.VERSION_1_8
    const val versionCode = 1
    const val versionName = "1.2.12"
}

object Libs {
    // Kotlin
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinCoroutinesVersion"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion"
    const val kotlinCore = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"

    // Google
    const val coreKtx = "androidx.core:core-ktx:$coreVersion"
    const val dagger = "com.google.dagger:dagger:$dagger2Version"
    const val daggerAndroid = "com.google.dagger:dagger-android-support:$dagger2Version"
    const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:$dagger2Version"
    const val daggerProcessor = "com.google.dagger:dagger-compiler:$dagger2Version"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"

    // UI
    const val conductor = "com.bluelinelabs:conductor:$conductorVersion"

    // Misc
    const val timber = "com.jakewharton.timber:timber:$timberVersion"

    // Gradle Plugins
    const val dependenciesGradlePlugin = "com.github.ben-manes:gradle-versions-plugin:$dependenciesVersion"
    const val androidGradlePlugin = "com.android.tools.build:gradle:$buildToolsVersion"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val mavenPublishGradlePlugin = "digital.wup:android-maven-publish:$androidMavenPublishVersion"
}

private const val androidMavenPublishVersion = "3.6.3"
private const val buildToolsVersion = "4.0.0"
private const val conductorVersion = "3.0.0-rc5"
private const val coreVersion = "1.3.0"
private const val dagger2Version = "2.28"
private const val dependenciesVersion = "0.28.0"
private const val kotlinCoroutinesVersion = "1.3.7"
private const val kotlinVersion = "1.3.72"
private const val lifecycleVersion = "2.2.0"
private const val timberVersion = "4.7.1"