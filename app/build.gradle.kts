@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.navigation.safeargs)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.gms.google.services)
}

android {
    namespace = "com.example.foodie"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.foodie"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(libs.bundles.android.base)
    implementation(libs.bundles.android.navigation)
    implementation(libs.bundles.android.datastore)
    api(libs.bundles.kotlin.coroutines)
    implementation(libs.bundles.android.viewmodel)
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.android.compiler)
    implementation(libs.bundles.retrofit)
    implementation(libs.glide)
    implementation(libs.bundles.android.room)
    kapt(libs.androidx.room.compiler)
    implementation(libs.bundles.firebase)
    implementation(libs.android.lottie)
    testImplementation(libs.bundles.unit.test)
    androidTestImplementation(libs.bundles.android.test)
}