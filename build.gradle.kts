buildscript {
    dependencies {
       classpath(libs.dagger.gradle.plugin)
    }
}
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.hilt.plugin) apply false
}
true // Needed to make the Suppress annotation work for the plugins block