import org.jetbrains.kotlin.config.KotlinCompilerVersion
import java.util.*

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

    buildTypes {
        val localProperties: File = rootProject.file("local.properties")
        val local = Properties()
        if (localProperties.exists()) {
            localProperties.inputStream().use { local.load(it) }
        }
        val tmdbApiKey = local.getProperty("tmdb_api_key", "")

        getByName("debug") {
            buildConfigField("String", "TMDB_API_KEY", tmdbApiKey)
            resValue("string", "api_key", tmdbApiKey)
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Modules
    implementation(project(":domain"))

    // Kotlin
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    implementation(Libs.core_ktx)

    // Coroutines
    implementation(Libs.coroutines_android)
    implementation(Libs.coroutines_core)

    // Koin
    implementation(Libs.koin)

    // Retrofit
    implementation(Libs.retrofit) {
        exclude(module = "okhttp")
    }
    implementation(Libs.retrofit_gson_converter)

    // OkHttp
    implementation(Libs.okhttp)

    // Retrofit coroutines adapter
    implementation(Libs.coroutines_adapter)

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
