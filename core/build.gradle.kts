import java.util.Properties

plugins {
    id("tmdbMovie.android.library")
    id("tmdbMovie.android.hilt")
    id("tmdbMovie.android.library.compose")
}
android {
    namespace = "com.sample.core"
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildTypes {
            debug {
                buildConfigField(
                    "String",
                    "API_URL",
                    "\"https://api.themoviedb.org\""
                )
                buildConfigField(
                    "String",
                    "IMAGE_BASE_URL",
                    "\"https://image.tmdb.org/t/p/original\""
                )
            }
            release {
                buildConfigField(
                    "String",
                    "API_URL",
                    "\"https://api.themoviedb.org\""
                )
                buildConfigField(
                    "String",
                    "IMAGE_BASE_URL",
                    "\"https://image.tmdb.org/t/p/original\""
                )
            }
        }

        //define apiKey from local.properties and get by BuildConfig.API_KEY
        val properties = Properties()
        properties.load(
            project.rootProject.file("local.properties").inputStream()
        )

        buildConfigField(
            "String",
            "API_KEY",
            "\"${properties.getProperty("API_KEY")}\""
        )
        buildConfigField(
            "String", "TOKEN", "\"${properties.getProperty("TOKEN")}\""
        )
    }

}
dependencies {
    coreLibraryDesugaring(libs.android.desugarJdkLibs)
    api(libs.retrofit.core)
    api(libs.retrofit.convertor.gson)
    implementation(libs.okhttp.logging)
    implementation(libs.coil.kt.compose)
}