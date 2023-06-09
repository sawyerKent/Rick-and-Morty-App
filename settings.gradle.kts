pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if(requested.id.id == "dagger.hilt.android.plugin"){
                useModule("com.google.dagger:hilt-android-gradle-plugin:2.42")
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Rick And Morty v2"
include(":app")
include(":lib_data")
