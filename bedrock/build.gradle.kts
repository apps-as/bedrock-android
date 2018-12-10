import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension

plugins {
    id("com.android.library") version Versions.BuildScript.buildToolsVersion
    id("kotlin-android") version Versions.Shared.kotlinVersion
    id("kotlin-android-extensions") version Versions.Shared.kotlinVersion
    id("digital.wup.android-maven-publish") version Versions.BuildScript.androidMavenPublishVersion
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
    implementation(kotlinCore)
    implementation(coroutinesCore)
    implementation(coroutinesAndroid)

    implementation(lifecycle)
    implementation(viewModelKtx)
    implementation(coreKtx)

    implementation(conductor)
    implementation(conductorLifecycle)

    implementation(dagger)
    implementation(daggerAndroid)
    kapt(daggerAndroidProcessor)
    kapt(daggerProcessor)
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