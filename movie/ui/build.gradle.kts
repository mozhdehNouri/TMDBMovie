plugins {
    id("tmdbMovie.android.feature")
    id("tmdbMovie.android.library.compose")
    id("tmdbMovie.android.hilt")
}
android {
    namespace = "com.example.tmdbmovie.movie.ui"
}
dependencies {
    implementation(project(":movie:domain"))
    implementation(project(":core"))
    coreLibraryDesugaring(libs.android.desugarJdkLibs)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.lifecycle.viewModelCompose)


}
