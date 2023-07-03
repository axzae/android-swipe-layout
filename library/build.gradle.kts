@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
}

android {
    namespace = Setup.Lib.namespace
    compileSdk = Setup.compileSdk

    defaultConfig {
        minSdk = Setup.minSdk
    }
}

dependencies {
    implementation("com.android.support:recyclerview-v7:25.2.0")
    implementation("com.android.support:support-v4:25.2.0")
}

// TODO migrate publish settings
// apply from: "./gradle-mvn-push.gradle"

// build a jar with source files
// task sourcesJar(type: Jar) {
//    from android.sourceSets.main.java.srcDirs
//    classifier = "sources"
// }
// artifacts {
//    archives sourcesJar
// }
