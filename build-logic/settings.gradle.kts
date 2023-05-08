dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    // sharing the root project version catalog
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "build-logic"
include(":convention")
