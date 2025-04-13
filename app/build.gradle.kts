plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp") version "2.1.20-1.0.32"
}

android {
    namespace = "com.example.mycryptoapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mycryptoapp"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation (libs.adapter.rxjava2)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation (libs.androidx.room.runtime)
    implementation (libs.adapter.rxjava2)
    implementation (libs.converter.gson)
    implementation (libs.rxandroid)
    implementation (libs.rxjava)
    implementation(libs.dagger.compiler)
    ksp(libs.dagger.compiler)
    //ViewModel
    implementation(libs.viewModel)
//Room
    implementation(libs.room)
// Кодогенератор Room
    ksp(libs.room.compiler)
// optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.room.ktx)
    implementation (libs.picasso)
    implementation (libs.okhttp)

}