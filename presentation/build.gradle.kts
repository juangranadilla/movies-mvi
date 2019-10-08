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

    // Koin
    implementation(Libs.koin)
    implementation(Libs.koin_viewmodel)

    // Timber
    implementation(Libs.timber)

    // Testing
    testImplementation(project(":domain"))
    testImplementation(TestLibs.junit)
    androidTestImplementation(TestLibs.test_runner)
    testImplementation(TestLibs.androidx_core_testing)
    testImplementation(TestLibs.google_truth)
    testImplementation(TestLibs.mockito_core)
    testImplementation(TestLibs.mockito_inline)
    testImplementation(TestLibs.mockito_kotlin)
    testImplementation(TestLibs.coroutines_testing)
}
