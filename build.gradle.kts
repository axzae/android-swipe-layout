plugins {
    id("com.android.application") version "8.0.2" apply false
    id("com.android.library") version "8.0.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.21" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.3.1" apply false
}

buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.0.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
    }
}

tasks.register("preMerge") {
    description = "Runs all the tests/verification tasks on both top level and included build."

    // dependsOn(":example:check")
    // dependsOn(gradle.includedBuild("library").task(":library:validatePlugins"))
}
