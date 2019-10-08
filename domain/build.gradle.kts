import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
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

    // Gson
    implementation(Libs.gson)

    // Timber
    implementation(Libs.timber)

    // Testing
    testImplementation(TestLibs.junit)
    androidTestImplementation(TestLibs.test_runner)
    testImplementation(TestLibs.androidx_core_testing)
    testImplementation(TestLibs.google_truth)
    testImplementation(TestLibs.mockito_core)
    testImplementation(TestLibs.mockito_inline)
    testImplementation(TestLibs.mockito_kotlin)
    testImplementation(TestLibs.coroutines_testing)
}