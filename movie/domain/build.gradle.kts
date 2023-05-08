plugins {
    id("tmdbMovie.android.library")
}
android {
    namespace = "com.example.tmdbmovie.movie.domain"
}
dependencies {
    coreLibraryDesugaring(libs.android.desugarJdkLibs)
}