object AndroidSdk {
    const val minSdkVersion = 21
    const val targetSdkVersion = 28
    const val compileSdkVersion = 28
    const val applicationId = "com.juangm.movies_mvi"
    const val versionCode = 1
    const val versionName = "0.1"
}

object BuildPlugins {

    object Versions {
        const val gradle_version = "3.4.1"
        const val kotlin_version = "1.3.50"
        const val gradle_versions_version = "0.25.0"
        const val navigation_safeargs_version = "1.0.0"
    }

    const val gradle = "com.android.tools.build:gradle:${Versions.gradle_version}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
    const val gradle_versions_plugin = "com.github.ben-manes:gradle-versions-plugin:${Versions.gradle_versions_version}"
    const val navigation_safeargs_plugin = "android.arch.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation_safeargs_version}"
}

object Libs {

    object Versions {
        const val core_ktx_version = "1.0.2"
        const val appcompat_version = "1.1.0"
        const val legacy_support_v4_version = "1.0.0"
        const val constraint_version = "1.1.3"
        const val navigation_version = "2.0.0"
        const val lifecycle_version = "2.2.0-alpha05"
        const val material_version = "1.1.0-alpha09"
        const val coroutines_version = "1.3.2"
        const val retrofit_version = "2.6.2"
        const val okhttp_version = "4.2.2"
        const val gson_version = "2.8.6"
        const val coroutines_adapter_version = "0.9.2"
        const val coil_version = "0.7.0"
        const val timber_version = "4.7.1"
        const val koin_version = "2.0.1"
    }

    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx_version}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
    const val legacy_support_v4 = "androidx.legacy:legacy-support-v4:${Versions.legacy_support_v4_version}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint_version}"
    const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_version}"
    const val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation_version}"
    const val lifecycle_extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle_version}"
    const val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    const val lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    const val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle_version}"
    const val material = "com.google.android.material:material:${Versions.material_version}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    const val koin = "org.koin:koin-android:${Versions.koin_version}"
    const val koin_viewmodel = "org.koin:koin-android-viewmodel:${Versions.koin_version}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val retrofit_gson_converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"
    const val okhttp = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_version}"
    const val gson = "com.google.code.gson:gson:${Versions.gson_version}"
    const val coroutines_adapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutines_adapter_version}"
    const val coil = "io.coil-kt:coil:${Versions.coil_version}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber_version}"
}

object TestLibs {

    object Versions {
        const val junit_version = "4.12"
        const val test_runner_version = "1.2.0"
        const val espresso_version = "3.2.0"
        const val androidx_core_testing_version = "2.1.0"
        const val google_truth_version = "1.0"
        const val mockito_version = "3.1.0"
        const val mockito_kotlin_version = "2.1.0"
        const val coroutines_version = "1.3.2"
    }

    const val junit = "junit:junit:${Versions.junit_version}"
    const val test_runner = "androidx.test:runner:${Versions.test_runner_version}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso_version}"
    const val androidx_core_testing = "androidx.arch.core:core-testing:${Versions.androidx_core_testing_version}"
    const val google_truth = "com.google.truth:truth:${Versions.google_truth_version}"
    const val mockito_core = "org.mockito:mockito-core:${Versions.mockito_version}"
    const val mockito_inline = "org.mockito:mockito-inline:${Versions.mockito_version}"
    const val mockito_kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_kotlin_version}"
    const val coroutines_testing= "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_version}"
}