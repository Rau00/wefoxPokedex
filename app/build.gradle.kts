plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinparcelize)
    alias(libs.plugins.kotlinkapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "technical.test.pokedex"
    compileSdk  = 34
    defaultConfig {
        applicationId = "technical.test.pokedex"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles( getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
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
    implementation (libs.kotlin.stdlib)
    implementation (libs.androidx.appcompact)
    implementation (libs.androidx.core)
    implementation (libs.androidx.constraintlayout)
    implementation (libs.android.material)
    implementation (libs.androidx.lifecycle.extensions)
    implementation (libs.androidx.lifecycle.viewmodel)
    implementation (libs.androidx.lifecycle.runtime)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.material3)

    implementation ("androidx.fragment:fragment-ktx:1.6.1") // will be change by compose

    //Testing
    testImplementation (libs.testing.junit)
    androidTestImplementation (libs.testing.androidx.runner)
    androidTestImplementation (libs.testing.espresso)
    testImplementation (libs.testing.androidx.arch.core)
    testImplementation(libs.testing.mockito.inline)
    testImplementation(libs.testing.mockito.kotlin)
    testImplementation (libs.testing.kotlin.coroutines)

    //Image Download
    implementation ("com.github.bumptech.glide:glide:4.13.0") // will be change by coil
    implementation(libs.coil)

    // Kotlin coroutines
    implementation (libs.kotlin.coroutines)
    // Retrofit
    implementation(libs.retrofit.gson)
    implementation (libs.retrofit)
    // Gson
    implementation("com.google.code.gson:gson:2.10.1") // will be change by kotlin serialization
    // okhttp
    implementation (libs.okhttp.loggin.interceptor)
    implementation (libs.okhttp)

    // Hilt for Kotlin
    implementation (libs.hilt.android)
    implementation (libs.hilt.compose)
    kapt (libs.hilt.compiler)

    //Room, data persistence
    implementation (libs.room.runtime)
    implementation (libs.room.ktx)
    ksp (libs.room.compiler)
    androidTestImplementation (libs.room.testing)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
