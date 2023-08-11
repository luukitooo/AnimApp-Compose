buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(libs.hilt.android.gradle.plugin)
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.5")
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