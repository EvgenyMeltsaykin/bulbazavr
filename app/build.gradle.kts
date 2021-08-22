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
        minSdkVersion(24)
        targetSdkVersion(30)


        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }
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
    implementation(project(":core"))
    implementation(project(":api"))
    implementation(project(":database"))
    implementation(Navigation.navFragment)
    implementation(Navigation.navUiKtx)
    implementation(Kotlin.stdlib)
    implementation(Dependencies.viewBindingPropertyDelegate)
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.constraintLayout)
    implementation(Dependencies.material)
    implementation(Dependencies.jUnit)
    implementation(Moxy.moxy)
    implementation(Moxy.ktx)
    implementation(Moxy.androidx)
    kapt(Moxy.compiler)
    implementation(Glide.glide)
    kapt(Glide.compiler)
    implementation(RxJava.rxJava)
    implementation(RxJava.rxAndroid)
    implementation(AndroidX.legacySupport)
    implementation(Dagger.dagger)
    implementation(Dagger.androidSupport)
    kapt(Dagger.compiler)
    kapt(Room.compiler)
    kapt(Room.runtime)
    implementation(Room.rxJava3)
    implementation(Retrofit.converterGson)
    implementation(Dependencies.recyclerView)

}