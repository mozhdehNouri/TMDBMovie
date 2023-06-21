plugins {
    id("tmdbMovie.android.application")
    id("tmdbMovie.android.application.compose")
    id("tmdbMovie.android.hilt")
    id("tmdbMovie.flavor")
}

android {
    namespace = "com.example.movietmdb"
    defaultConfig {
        versionCode = 1
        versionName = "1.0.0"
    }
    defaultConfig.vectorDrawables {
        useSupportLibrary = true
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":movie:ui"))
    implementation(project(":movie:domain"))
    implementation(project(":movie:data"))

    coreLibraryDesugaring(libs.android.desugarJdkLibs)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.compose.ui.tooling.preview)

}