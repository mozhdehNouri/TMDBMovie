import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}
java {
    // Up to Java 11 APIs are available through desugaring
    // https://developer.android.com/studio/write/java11-minimal-support-table
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}


dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("AndroidApplicationCompose") {
            id = "tmdbMovie.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("AndroidApplication") {
            id = "tmdbMovie.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("AndroidLibraryCompose") {
            id = "tmdbMovie.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "tmdbMovie.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "tmdbMovie.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("AndroidRoom") {
            id = "tmdbMovie.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("AndroidHilt") {
            id = "tmdbMovie.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("AndroidApplicationFlavors") {
            id = "tmdbMovie.flavor"
            implementationClass = "AndroidApplicationFlavorsConventionPlugin"
        }
    }
}