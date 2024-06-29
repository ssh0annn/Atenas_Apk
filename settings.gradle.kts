pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
       // maven("https://jitpack.io")




    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url ="https://jitpack.io")


    }
}

rootProject.name = "Atenas_apk_2"
include(":app")
