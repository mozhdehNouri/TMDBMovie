plugins {
    id("tmdbMovie.android.library")
}
android {
    namespace = "com.example.tmdbmovie.movie.data"
}
dependencies {
    coreLibraryDesugaring(libs.android.desugarJdkLibs)
}