plugins {
    id("tmdbMovie.android.library")
    id("tmdbMovie.android.hilt")
}
android {
    namespace = "com.example.tmdbmovie.movie.domain"
}
dependencies {
    coreLibraryDesugaring(libs.android.desugarJdkLibs)
    implementation(project(":core"))
}