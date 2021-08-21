plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    kotlin("plugin.serialization")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode(1)
        versionName("1.0")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core"))

    kapt(Room.compiler)
    kapt(Room.runtime)
    implementation(Room.rxJava3)
    implementation(Dagger.dagger)
    implementation(Dagger.androidSupport)
    kapt(Dagger.compiler)
}