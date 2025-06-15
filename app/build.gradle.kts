import com.android.build.gradle.internal.scope.ProjectInfo.Companion.getBaseName

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.jetbrains.kotlin.serialization)
//    id("kotlin-kapt")
//    id("dagger.hilt.android.plugin")
//    id("com.google.android.")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.example.farrell"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.farrell"
        minSdk = 25
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        manifestPlaceholders["GOOGLE_MAPS_API_KEY"] = "AIzaSyCt6QIBXWf8PYnziH-bhw18n8b7Ox7FPB4"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.compose.navigation)
    implementation(libs.kotlinx.serialization.json)
    implementation("com.google.accompanist:accompanist-permissions:0.35.0-alpha")

//    // Google Play Services Maps
    implementation("com.google.android.gms:play-services-maps:18.2.0")
//
//    // Google Play Services Location
//    implementation("com.google.android.gms:play-services-location:21.0.1")
//
//    // Maps Compose (wersja 2.11.4 jest dostępna)
//    implementation("com.google.maps.android:maps-compose:2.15.0")
//
//    // Google Maps Android Utilities — zmień na 2.11.0, bo 2.11.4 nie ma
//    implementation("com.google.maps.android:maps-utils-compose:2.11.0")
//
//    // Dagger Hilt
//    implementation("com.google.dagger:hilt-android:2.50")
//    kapt("com.google.dagger:hilt-android-compiler:2.50")
//
//    // Compose
//    implementation("androidx.activity:activity-compose:1.9.0")
//    implementation("androidx.compose.ui:ui:1.6.7")
//    implementation("androidx.compose.material3:material3:1.2.1")
//
//    // Core
//    implementation("androidx.core:core-ktx:1.13.1")
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    implementation("com.google.maps.android:maps-compose:6.6.0")

    // Optionally, you can include the Compose utils library for Clustering,
    // Street View metadata checks, etc.
    implementation("com.google.maps.android:maps-compose-utils:6.6.0")
}
