plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.autobackgroundchanger"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.autobackgroundchanger"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Gson converter
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // Scalars converter
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")

    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3") // 👈 Thêm dòng này!
}
