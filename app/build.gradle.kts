plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinparcelize)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.screenshot)
}

android {
    namespace = "technical.test.pokedex"
    compileSdk = 34
    defaultConfig {
        applicationId = "technical.test.pokedex"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "technical.test.pokedex.CustomTestRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    experimentalProperties["android.experimental.enableScreenshotTest"] = true

    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtVersion.get()
    }
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.appcompact)
    implementation(libs.androidx.core)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.android.material)
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.runtime)

    // Compose
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)
    screenshotTestImplementation(libs.androidx.ui.tooling)
    screenshotTestImplementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.navigation)

    //Image Download
    implementation(libs.coil)

    // Kotlin coroutines
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.android)
    screenshotTestImplementation(libs.kotlin.coroutines.core)
    screenshotTestImplementation(libs.kotlin.coroutines.android)
    // Retrofit
    implementation(libs.retrofit.gson)
    implementation(libs.retrofit)
    // Gson
    implementation("com.google.code.gson:gson:2.10.1") // will be change by kotlin serialization
    // Kotlin serialization
    implementation(libs.kotlinx.serialization.json)
    // okhttp
    implementation(libs.okhttp.loggin.interceptor)
    implementation(libs.okhttp)

    // Tensorflow
    implementation(libs.tensorflowlite)

    // Hilt for Kotlin
    implementation(libs.hilt.android)
    implementation(libs.hilt.compose)
    ksp(libs.hilt.compiler)

    //Room, data persistence
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    androidTestImplementation(libs.room.testing)

    //Testing
    androidTestImplementation(libs.testing.androidx.runner)
    androidTestImplementation(libs.testing.mockito.kotlin)
    androidTestImplementation(libs.hilt.testing)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.core)
    debugImplementation(libs.androidx.ui.test.manifest)

    testImplementation(libs.testing.junit)
    testImplementation(libs.testing.androidx.arch.core)
    testImplementation(libs.testing.mockito.inline)
    testImplementation(libs.testing.mockito.kotlin)
    testImplementation(libs.testing.kotlin.coroutines)
}