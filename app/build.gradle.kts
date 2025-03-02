plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.health"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.health"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    //蓝牙相关的依赖
    implementation ("com.google.android.gms:play-services-location:19.0.1")
    implementation ("androidx.compose.ui:ui:1.0.0")
    implementation ("androidx.compose.material:material:1.0.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0")
    implementation ("androidx.activity:activity-compose:1.4.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
    //网络相关依赖
    implementation ("com.squareup.okhttp3:okhttp:4.0.0") // 使用最新版本
    implementation("io.coil-kt:coil-compose:2.0.0") // 根据最新版本更新
    implementation ("androidx.activity:activity-compose:1.3.1") // 用于支持 Compose 的 Activity
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")
    //导航相关依赖
    implementation ("androidx.navigation:navigation-compose:2.7.7")
    implementation ("androidx.compose.material:material:1.6.4")
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.runtime.android)
    implementation(libs.androidx.ui.test.android)
    androidTestImplementation ("androidx.navigation:navigation-testing:$rootProject.composeNavigationVersion")

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
}