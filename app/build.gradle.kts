plugins {
    id("tmdbMovie.android.application")
    id("tmdbMovie.android.application.compose")
}

android {
    namespace = "com.sample.movietmdb"
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
    coreLibraryDesugaring(libs.android.desugarJdkLibs)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)


}