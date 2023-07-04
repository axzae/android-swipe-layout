@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = Setup.Lib.namespace
    compileSdk = Setup.compileSdk

    defaultConfig {
        minSdk = Setup.minSdk
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
    implementation("androidx.recyclerview:recyclerview:1.3.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
}

apply(from = "$projectDir/publish.gradle.kts")

tasks {
    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from(android.sourceSets.getByName("main").java.srcDirs)
    }

    artifacts {
        archives(sourcesJar)
    }
}

project.afterEvaluate {
    project.tasks["publishMavenJavaPublicationToMavenLocal"].dependsOn("bundleReleaseAar")
    project.tasks["publishMavenJavaPublicationToMavenRepository"].dependsOn("bundleReleaseAar")
    project.tasks["signMavenJavaPublication"].dependsOn("bundleReleaseAar")
}
