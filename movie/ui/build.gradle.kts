plugins {
    id("tmdbMovie.android.feature")
    id("tmdbMovie.android.library.compose")
}
android {
    namespace = "com.example.tmdbmovie.movie.ui"
}
dependencies {
    coreLibraryDesugaring(libs.android.desugarJdkLibs)
    implementation(libs.androidx.compose.ui.tooling)

}
