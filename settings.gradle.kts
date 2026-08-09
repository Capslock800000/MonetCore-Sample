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

// ── 引入 MonetCore 子模块 ──────────────────────────────────
// 保持与 MonetCore 内部一致的路径名，确保 monet-client 的 project(":monet-api") 能解析
include(":monet-api")
include(":monet-client")
project(":monet-api").projectDir = file("monetcore/monet-api")
project(":monet-client").projectDir = file("monetcore/monet-client")
