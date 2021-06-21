@file:Suppress("SpellCheckingInspection")

import org.gradle.api.JavaVersion

object AndroidConfig {
    const val compileVersion = 30
    const val minSdkVersion = 26
    const val targetSdkVersion = 30
    const val buildToolsSdkVersion = "30.0.2"
    val sourceCompatibility = JavaVersion.VERSION_1_8
    val targetCompatibility = JavaVersion.VERSION_1_8
    const val versionCode = 1
    const val versionName = "2.2.0"
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
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:$lifecycleExtensionsVersion"
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
private const val buildToolsVersion = "4.2.1"
private const val conductorVersion = "3.0.1"
private const val coreVersion = "1.6.0-rc01"
private const val dagger2Version = "2.37"
private const val dependenciesVersion = "0.39.0"
private const val kotlinCoroutinesVersion = "1.5.0"
private const val kotlinVersion = "1.5.10"
private const val lifecycleVersion = "2.3.1"
private const val lifecycleExtensionsVersion = "2.2.0"
private const val timberVersion = "4.7.1"