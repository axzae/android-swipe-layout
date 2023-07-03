@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
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
}

dependencies {
    implementation(project(":library"))
    implementation("com.android.support:recyclerview-v7:25.2.0")
    implementation("com.daimajia.easing:library:1.0.0@aar")
    implementation("com.daimajia.androidanimations:library:1.1.2@aar")
    implementation("com.nineoldandroids:library:2.4.0")
    implementation("jp.wasabeef:recyclerview-animators:1.0.3@aar")
}
