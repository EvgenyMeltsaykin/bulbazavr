
object Versions{
    const val kotlin = "1.5.21"
    const val coreKtx = "1.6.0"
    const val appCompat = "1.3.1"
    const val material = "1.4.0"
    const val constraintLayout = "2.0.4"
    const val jUnit = "4.13.2"
    const val gradle = "4.2.2"
    const val room = "2.3.0"
    const val moxy = "2.2.2"
    const val navigation = "2.3.5"
    const val viewBindingPropertyDelegate = "1.4.4"
    const val rxJava = "3.0.13"
    const val rxAndroid = "3.0.0"
    const val glide = "4.12.0"
    const val appCompatV7 = "28.0.0"
    const val dagger = "2.38"
    const val retrofit = "2.9.0"
    const val okhttp3 = "4.9.0"
    const val okio = "2.8.0"
    const val recyclerView = "1.2.1"
    const val legacySupport = "1.0.0"
    const val kotlinXSerializationJson = "1.2.2"
}

object KotlinX {
    const val serializationJson =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinXSerializationJson}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val adapterRxJava3 = "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
    const val okio = "com.squareup.okio:okio:${Versions.okio}"
}

object Navigation {
    const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val safeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

object Dependencies {
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val buildGradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val viewBindingPropertyDelegate =
        "com.github.kirich1409:viewbindingpropertydelegate:${Versions.viewBindingPropertyDelegate}"
}

object RxJava {
    const val rxJava = "io.reactivex.rxjava3:rxjava:${Versions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxAndroid}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val rxJava3 = "androidx.room:room-rxjava3:${Versions.room}"
    const val test = "androidx.room:room-testing:${Versions.room}"
}

object Moxy {
    const val moxy = "com.github.moxy-community:moxy:${Versions.moxy}"
    const val androidx = "com.github.moxy-community:moxy-androidx:${Versions.moxy}"
    const val ktx = "com.github.moxy-community:moxy-ktx:${Versions.moxy}"
    const val compiler = "com.github.moxy-community:moxy-compiler:${Versions.moxy}"
}

object AndroidX{
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
}

object Kotlin {
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val stdlibCommon = "org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.kotlin}"
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val serialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object Dagger {
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val androidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
}