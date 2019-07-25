
import org.jetbrains.kotlin.config.KotlinCompilerVersion
import java.util.*

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(AndroidSdk.compileSdkVersion)
    defaultConfig {
        applicationId = AndroidSdk.applicationId
        minSdkVersion(AndroidSdk.minSdkVersion)
        targetSdkVersion(AndroidSdk.targetSdkVersion)
        versionCode = AndroidSdk.versionCode
        versionName = AndroidSdk.versionName
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
    implementation(Libs.core_ktx)

    // Android x
    implementation(Libs.appcompat)
    implementation(Libs.legacy_support_v4)
    implementation(Libs.constraint)
    implementation(Libs.navigation_fragment)
    implementation(Libs.navigation_ui)
    implementation(Libs.lifecycle_extensions)
    implementation(Libs.lifecycle_viewmodel)
    implementation(Libs.lifecycle_livedata)
    kapt(Libs.lifecycle_compiler)

    // Material
    implementation(Libs.material)

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

    // Glide
    implementation(Libs.glide)
    annotationProcessor(Libs.glide_compiler)

    // Timber
    implementation(Libs.timber)

    // Testing
    testImplementation(TestLibs.junit)
    androidTestImplementation(TestLibs.test_runner)
    androidTestImplementation(TestLibs.espresso)
}
