plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.layarkita"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.layarkita"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:11.1.0")
    implementation ("androidx.room:room-runtime:2.6.1")
    implementation ("androidx.core:core-splashscreen:1.0.1")
    implementation(libs.room.common.jvm)
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}