plugins {
    id("tmdbMovie.android.library")
    id("tmdbMovie.android.library.compose")
}
android {
    namespace = "com.sample.core"
}
dependencies {
    coreLibraryDesugaring(libs.android.desugarJdkLibs)
    implementation(libs.androidx.core.ktx)
}