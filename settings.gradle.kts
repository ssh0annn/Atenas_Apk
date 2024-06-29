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
        //Repositorio para animaciones peido por Carlos
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")

    }
}

rootProject.name = "Atenas_apk_2"
include(":app")
