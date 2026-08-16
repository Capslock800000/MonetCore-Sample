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
        mavenCentral()
        google()
    }
}

rootProject.name = "MonetCore-Sample"
include(":app")

// MonetCore submodule
include(":monet-api")
include(":monet-client")
project(":monet-api").projectDir = file("monetcore/monet-api")
project(":monet-client").projectDir = file("monetcore/monet-client")
