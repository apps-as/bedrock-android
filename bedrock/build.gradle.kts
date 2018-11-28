import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("digital.wup.android-maven-publish")
}

android {
    compileSdkVersion(AndroidConfig.compileVersion)
    buildToolsVersion(AndroidConfig.buildToolsSdkVersion)

    defaultConfig {
        minSdkVersion(AndroidConfig.minSdkVersion)
        targetSdkVersion(AndroidConfig.targetSdkVersion)
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        setSourceCompatibility(AndroidConfig.sourceCompatibility)
        setTargetCompatibility(AndroidConfig.targetCompatibility)
    }
}

// https://github.com/gradle/kotlin-dsl/issues/644
androidExtensions {
    configure(delegateClosureOf<AndroidExtensionsExtension> {
        isExperimental = true
    })
}

dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.Shared.kotlinVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.App.kotlinCoroutinesVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.App.kotlinCoroutinesVersion}")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:${Versions.App.lifecycleVersion}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.App.lifecycleVersion}")

    // Jetpack
    implementation("androidx.core:core-ktx:${Versions.App.jetpackVersion}")

    // Conductor
    implementation("com.bluelinelabs:conductor:${Versions.App.conductorVersion}")
    implementation("com.bluelinelabs:conductor-archlifecycle:${Versions.App.conductorVersion}")

    // Dagger 2
    implementation("com.google.dagger:dagger:${Versions.App.dagger2Version}")
    implementation("com.google.dagger:dagger-android-support:${Versions.App.dagger2Version}")
    kapt("com.google.dagger:dagger-android-processor:${Versions.App.dagger2Version}")
    kapt("com.google.dagger:dagger-compiler:${Versions.App.dagger2Version}")
}

// Publish

publishing {
    publications {
        register("mavenAar", MavenPublication::class) {
            from(components["android"])
            groupId = "no.apps"
            artifactId = "bedrock"
            version = android.defaultConfig.versionName
        }
    }
    repositories {
        maven {
            url = uri("s3://no.apps.maven.s3.amazonaws.com")
            credentials(AwsCredentials::class) {
                accessKey = properties["maven.access"] as String
                secretKey = properties["maven.secret"] as String
            }
        }
    }
}