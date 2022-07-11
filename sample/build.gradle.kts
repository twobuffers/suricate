import buildcfg.Libs

plugins {
    id(GradlePluginId.ANDROID_APPLICATION)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_KAPT)
}

android {
    compileSdk = AndroidConfig.COMPILE_SDK_VERSION
    buildToolsVersion = AndroidConfig.BUILD_TOOLS_VERSION
    defaultConfig {
        applicationId = "com.twobuffers.suricate.sample"
        minSdk = AndroidConfig.MIN_SDK_VERSION
        targetSdk = AndroidConfig.TARGET_SDK_VERSION
        versionCode = 1000001
        versionName = "1.0.1"
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = buildcfg.Versions.JAVA_VERSION
        targetCompatibility = buildcfg.Versions.JAVA_VERSION
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
        resValues = true
    }
}

dependencies {
    implementation(project(":suricate"))

    implementation(Libs.Kotlin.kotlinStdlib8)

    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.constraintlayout)
    implementation(Libs.AndroidX.Lifecycle.lifecycleRuntimeKtx) // androidx.lifecycle.lifecycleScope
}
