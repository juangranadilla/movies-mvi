import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
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

    // Modules
    implementation(project(":domain"))

    // Kotlin
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    implementation(Libs.core_ktx)

    // Android x
    implementation(Libs.lifecycle_extensions)
    implementation(Libs.lifecycle_viewmodel)
    implementation(Libs.lifecycle_livedata)
    kapt(Libs.lifecycle_compiler)

    // Coroutines
    implementation(Libs.coroutines_android)
    implementation(Libs.coroutines_core)

    // Koin
    implementation(Libs.koin)
    implementation(Libs.koin_viewmodel)

    // Timber
    implementation(Libs.timber)
}
