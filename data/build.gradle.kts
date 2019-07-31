import org.jetbrains.kotlin.config.KotlinCompilerVersion
import java.util.*

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
    implementation(Libs.koin_viewmodel)

    // Retrofit
    implementation(Libs.retrofit) {
        exclude(module = "okhttp")
    }
    implementation(Libs.retrofit_gson_converter)

    // OkHttp
    implementation(Libs.okhttp)

    // Gson
    implementation(Libs.gson)

    // Retrofit coroutines adapter
    implementation(Libs.coroutines_adapter)

    // Timber
    implementation(Libs.timber)
}
