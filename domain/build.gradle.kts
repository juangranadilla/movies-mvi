import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.library")
}
apply {
    plugin("kotlin-android")
    plugin("kotlin-android-extensions")
}

android {
    compileSdkVersion(AndroidSdk.compileSdkVersion)
    defaultConfig {
        minSdkVersion(AndroidSdk.minSdkVersion)
        targetSdkVersion(AndroidSdk.targetSdkVersion)
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Kotlin
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    implementation(Libs.core_ktx)

    // Android x
    implementation(Libs.lifecycle_livedata)

    // Coroutines
    implementation(Libs.coroutines_android)
    implementation(Libs.coroutines_core)

    // Koin
    implementation(Libs.koin)
    implementation(Libs.koin_viewmodel)

    // Gson
    implementation(Libs.gson)

    // Timber
    implementation(Libs.timber)
}