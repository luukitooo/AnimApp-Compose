@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

android {
    namespace = "com.luukitoo.animapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.luukitoo.animapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    implementation(libs.accompanist.insets)
    implementation(libs.accompanist.systemuicontroller)

    // MVI
    implementation(project(":mvi"))

    // UseCase
    implementation(project(":usecase"))

    // Anime
    implementation(project(":anime"))

    // Manga
    implementation(project(":manga"))

    // Characters
    implementation(project(":characters"))

    // Core
    implementation(project(":core"))

    // Database
    implementation(project(":database"))

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Dagger-Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Compose Navigation
    implementation(libs.androidx.navigation.compose)

    // Compose Lifecycle
    implementation(libs.androidx.lifecycle.runtime.compose)

    // Coil
    implementation(libs.coil.compose)

    // Youtube Player
    implementation(libs.core)

    // SQLDelight Android Driver
    implementation("com.squareup.sqldelight:android-driver:1.5.5")
}

kapt {
    correctErrorTypes = true
}