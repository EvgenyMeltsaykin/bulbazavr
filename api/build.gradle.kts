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
    implementation(Kotlin.stdlib)
    implementation(AndroidX.coreKtx)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    kapt("androidx.room:room-compiler:2.3.0")
    kapt("androidx.room:room-runtime:2.3.0")
    implementation("androidx.room:room-rxjava3:2.3.0")
    implementation("com.google.code.gson:gson:2.8.8")


    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.squareup.okio:okio:2.8.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    implementation("com.google.dagger:dagger:2.38")
    implementation("com.google.dagger:dagger-android-support:2.38")
    kapt("com.google.dagger:dagger-compiler:2.38")
}