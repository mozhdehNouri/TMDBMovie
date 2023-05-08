plugins {
    id("tmdbMovie.android.library")
}
android {
    namespace = "com.example.tmdbmovie.core"
}
dependencies {
    coreLibraryDesugaring(libs.android.desugarJdkLibs)
}