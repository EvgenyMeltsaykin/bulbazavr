
object Versions{
    const val kotlin = "1.5.21"
    const val coreKtx = "1.6.0"
    const val appCompat = "1.3.1"
    const val material = "1.4.0"
    const val constraintLayout = "2.0.4"
    const val jUnit = "4.13.2"
    const val gradle = "4.2.2"
    const val ktor = "1.6.1"
    const val room = "2.3.0"
    const val moxy = "2.2.2"
    const val navigation = "2.3.5"
    const val viewBindingPropertyDelegate = "1.4.4"
    const val rxJava = "3.0.13"
    const val glide = "4.12.0"
    const val appCompatV7 = "28.0.0"
    const val dagger = "2.38"
}

object Navigation{
    const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

object Dependencies{
    const val rxJava3 = "io.reactivex.rxjava3:rxjava:${Versions.rxJava}"
    const val buildGradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val viewBindingPropertyDelegate =
        "com.github.kirich1409:viewbindingpropertydelegate:${Versions.viewBindingPropertyDelegate}"
}

object Room{
    const val runtime =  "androidx.room:room-runtime:${Versions.room}"
    const val compiler =  "androidx.room:room-compiler:${Versions.room}"
    const val rxJava3 =  "androidx.room:room-rxjava3:${Versions.room}"
    const val test =  "androidx.room:room-testing:${Versions.room}"
}

object Moxy {
    const val moxy = "com.arello-mobile:moxy:${Versions.moxy}"
    const val compiler = "com.arello-mobile:moxy-compiler:${Versions.moxy}"
    const val android = "com.arello-mobile:moxy-android:${Versions.moxy}"
}

object Ktor{
    const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val cio = "io.ktor:ktor-client-cio:${Versions.ktor}"
}

object AndroidX{
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
}

object Kotlin {
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val stdlibCommon = "org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.kotlin}"
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val serialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
}

object Glide{
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}