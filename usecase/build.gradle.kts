@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("java-library")
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    id("kotlin-kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    // Anime
    implementation(project(":anime"))

    // Manga
    implementation(project(":manga"))

    // Characters
    implementation(project(":characters"))

    // Core
    implementation(project(":core"))

    // Dagger-Hilt
    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
}