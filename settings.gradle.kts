pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://jitpack.io")
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }



    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        //Repositorio para animaciones peido por Carlos
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
        maven("https://jitpack.io")

    }
}

rootProject.name = "Atenas_apk_2"
include(":app")
