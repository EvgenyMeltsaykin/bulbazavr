// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        classpath(Dependencies.buildGradle)
        classpath(Kotlin.gradlePlugin)
        classpath(Navigation.safeArgs)
        classpath(Kotlin.serialization)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}