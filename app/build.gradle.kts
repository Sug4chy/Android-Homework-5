import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "ru.sug4chy.smarthouselightbulb"
    compileSdk = 35

    defaultConfig {
        applicationId = "ru.sug4chy.smarthouselightbulb"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            val configProps = Properties()
            file("../config/config.properties").inputStream().use {
                configProps.load(it)
            }
            buildConfigField("String", "BASE_URL", "\"${configProps.getProperty("BASE_URL")}\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            val configProps = Properties()
            file("../config/config.properties").inputStream().use {
                configProps.load(it)
            }
            buildConfigField("String", "BASE_URL", "\"${configProps.getProperty("BASE_URL")}\"")
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
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // VBPD
    implementation(libs.view.binding.property.delegeate)

    // Navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.navigation.dynamic.features.fragment)

    // Dagger 2
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    // retrofit networking
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhhtp.logging.interceptor)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}