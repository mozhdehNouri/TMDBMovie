pluginManagement {
    includeBuild("build-logic")
    repositories {
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
    }
}
rootProject.name = "MovieTMDB"
include(":app")
include(":core")
include(":movie:data")
include(":movie:domain")
include(":movie:ui")
