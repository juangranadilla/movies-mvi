
import org.jetbrains.kotlin.config.KotlinCompilerVersion
import java.util.*

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Config.Android.compileSdkVersion)
    defaultConfig {
        applicationId = Config.Android.applicationId
        minSdkVersion(Config.Android.minSdkVersion)
        targetSdkVersion(Config.Android.targetSdkVersion)
        versionCode = Config.Android.versionCode
        versionName = Config.Android.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Kotlin
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    implementation(Config.Libs.core_ktx)

    // Android x
    implementation(Config.Libs.appcompat)
    implementation(Config.Libs.legacy_support_v4)
    implementation(Config.Libs.constraint)
    implementation(Config.Libs.navigation_fragment)
    implementation(Config.Libs.navigation_ui)
    implementation(Config.Libs.lifecycle_extensions)
    implementation(Config.Libs.lifecycle_viewmodel)
    implementation(Config.Libs.lifecycle_livedata)
    kapt(Config.Libs.lifecycle_compiler)

    // Material
    implementation(Config.Libs.material)

    // Coroutines
    implementation(Config.Libs.coroutines_android)
    implementation(Config.Libs.coroutines_core)

    // Koin
    implementation(Config.Libs.koin)
    implementation(Config.Libs.koin_viewmodel)

    // Retrofit
    implementation(Config.Libs.retrofit) {
        exclude(module = "okhttp")
    }
    implementation(Config.Libs.retrofit_gson_converter)

    // OkHttp
    implementation(Config.Libs.okhttp)

    // Gson
    implementation(Config.Libs.gson)

    // Retrofit coroutines adapter
    implementation(Config.Libs.coroutines_adapter)

    // Glide
    implementation(Config.Libs.glide)
    annotationProcessor(Config.Libs.glide_compiler)

    // Timber
    implementation(Config.Libs.timber)

    // Testing
    testImplementation(Config.Libs.junit)
    androidTestImplementation(Config.Libs.test_runner)
    androidTestImplementation(Config.Libs.espresso)
}
