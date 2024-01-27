plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.multiplatformtest.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.multiplatformtest.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.coil.compose)
//    implementation ("com.google.android.exoplayer:exoplayer-core:2.17.2")
//    implementation ("com.google.android.exoplayer:exoplayer-ui:2.17.2")
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation(libs.androidx.material3.v100)
    implementation(libs.glide.compose)
    implementation ("androidx.navigation:navigation-compose:2.7.6")

    implementation(libs.firebase.storage)
    implementation(libs.firebase.messaging)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui.graphics)
    implementation(libs.androidx.constraintlayout.core)
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidx.media3.exoplayer)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}