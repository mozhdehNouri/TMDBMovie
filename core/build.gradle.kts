import java.util.Properties

plugins {
    id("tmdbMovie.android.library")
    id("tmdbMovie.android.hilt")
}
android {
    namespace = "com.example.tmdbmovie.core"
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildTypes {
            debug {
                buildConfigField("String", "API_URL", "\"https://api.themoviedb.org/3/\"")
            }
            release {
                buildConfigField("String", "API_URL", "\"https://api.themoviedb.org/3/\"")
            }
        }

        //define apiKey from local.properties and get by BuildConfig.API_KEY
        val properties = Properties()
        properties.load(
            project.rootProject.file("local.properties").inputStream()
        )

        buildConfigField(
            "String", "API_KEY", "\"${properties.getProperty("API_KEY")}\""
        )
    }

}
dependencies {
    coreLibraryDesugaring(libs.android.desugarJdkLibs)
    api(libs.retrofit.core)
    api(libs.retrofit.convertor.gson)
    implementation(libs.okhttp.logging)
}