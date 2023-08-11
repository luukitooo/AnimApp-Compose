buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(libs.hilt.android.gradle.plugin)
    }
}

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.androidLibrary) apply false
}
true // Needed to make the Suppress annotation work for the plugins block