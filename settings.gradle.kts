pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AnimApp"
include(":app")
include(":anime")
include(":core")
include(":mvi")
include(":usecase")
include(":manga")
include(":characters")
include(":database")
