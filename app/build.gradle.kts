plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    kotlin("plugin.serialization")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")
}

android {
    compileSdkVersion(30)
    buildToolsVersion ("30.0.3")

    defaultConfig {
        applicationId ("com.poke.bulbazavr")
        minSdkVersion (24)
        targetSdkVersion (30)
        versionCode (1)
        versionName ("1.0")

        testInstrumentationRunner ("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

kapt {
    generateStubs = true
}

dependencies {
    implementation(Navigation.navFragment)
    implementation(Navigation.navUiKtx)
    implementation(Kotlin.stdlib)
    implementation(Dependencies.viewBindingPropertyDelegate)
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.constraintLayout)
    implementation(Dependencies.material)
    implementation(Dependencies.jUnit)
    implementation(Dependencies.rxJava3)


    implementation ("com.github.moxy-community:moxy:${Versions.moxy}")
    implementation ("com.github.moxy-community:moxy-androidx:${Versions.moxy}")
    implementation ("com.github.moxy-community:moxy-ktx:${Versions.moxy}")
    kapt ("com.github.moxy-community:moxy-compiler:${Versions.moxy}")

    implementation(Glide.glide)
    kapt(Glide.compiler)
    implementation("io.reactivex.rxjava3:rxandroid:3.0.0")
    //implementation ("androidx.fragment:fragment-ktx:1.3.6")
    //implementation ("androidx.core:core-ktx:1.6.0")
    implementation ("io.ktor:ktor-client-serialization-jvm:1.6.1")

    implementation ("androidx.legacy:legacy-support-v4:1.0.0")

    implementation ("com.google.dagger:dagger:2.38")
    implementation ("com.google.dagger:dagger-android-support:2.38")
    kapt ("com.google.dagger:dagger-compiler:2.38")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")
    implementation ("com.squareup.okio:okio:2.8.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")

}