plugins {
    id("tmdbMovie.android.library")
    id("tmdbMovie.android.hilt")
}
android {
    namespace = "com.example.tmdbmovie.movie.data"
}
dependencies {
    coreLibraryDesugaring(libs.android.desugarJdkLibs)
    implementation(project(":core"))
    implementation(project(":movie:domain"))
    implementation(libs.kotlinx.coroutines.android)
}