dependencyResolutionManagement {
    repositories {
        maven {
            url = uri("https://nexus.partdp.ir/repository/part-android/")
            artifactUrls("https://nexus.partdp.ir/repository/part-android/")
            credentials {
                username = "android-user"
                password = "EL2BB+*wkXEaydY=/2>2Kx-tV4CV-%"
            }
        }
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
