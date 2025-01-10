@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("java-library")
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    id("com.squareup.sqldelight")
    id("kotlin-kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    // SQLDelight
    implementation("com.squareup.sqldelight:runtime:1.5.5")
    implementation("com.squareup.sqldelight:coroutines-extensions-jvm:1.5.5")

    // Dagger-Hilt
    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
}

sqldelight {
    database("AnimAppDatabase") {
        packageName = "com.luukitoo.database"
    }
}