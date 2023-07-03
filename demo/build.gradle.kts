@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jlleitschuh.gradle.ktlint")
}

android {
    namespace = Setup.App.namespace
    compileSdk = Setup.compileSdk

    defaultConfig {
        applicationId = Setup.App.applicationId
        minSdk = Setup.minSdk
        targetSdk = Setup.targetSdk

        versionCode = Setup.versionCode
        versionName = Setup.versionName

        resValue("string", "app_name", Setup.App.name)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFile(getDefaultProguardFile("proguard-android.txt"))
            proguardFile("proguard-rules.pro")
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-Xjvm-default=enable",
        )
    }
}

dependencies {
    implementation(project(":library"))
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("com.daimajia.easing:library:1.0.0@aar")
    implementation("com.daimajia.androidanimations:library:1.1.2@aar")
    implementation("com.nineoldandroids:library:2.4.0")
    implementation("jp.wasabeef:recyclerview-animators:4.0.2")
}
