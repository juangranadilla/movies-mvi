import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("com.github.ben-manes.versions")
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

    // Modules
    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))

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

    // Koin
    implementation(Libs.koin)
    implementation(Libs.koin_viewmodel)

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
