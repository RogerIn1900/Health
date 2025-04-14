// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    id("com.google.gms.google-services") version "4.4.2" apply false
//    //数据库
//    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
//
//
//    val room_version = "2.6.0"
//    id("androidx.room") version "$room_version" apply false
    id("com.google.devtools.ksp") version "2.1.0-1.0.29" apply false


    alias(libs.plugins.compose.compiler) apply false

}


