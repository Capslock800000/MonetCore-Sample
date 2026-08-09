# MonetCore Sample

[![Build Status](https://github.com/Capslock800000/MonetCore-Sample/actions/workflows/build.yml/badge.svg)](https://github.com/Capslock800000/MonetCore-Sample/actions)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.4.10-purple.svg)](https://kotlinlang.org/)
[![API](https://img.shields.io/badge/API-28%2B-brightgreen.svg)](https://developer.android.com/about/versions/9)

MonetCore 官方示例应用。演示如何通过 `monet-client` SDK 跨进程调用 `monet-service` 生成 Material 3 动态主题。

## 效果预览

- **启动页**：Google Play 风格「正在核对信息」无限旋转进度动画
- **主界面**：展示从 Service 获取的完整 Material 3 色板，支持深浅模式切换与种子色更换

## 前置依赖

本仓库仅包含 Sample App，**不包含** MonetCore 引擎本身。构建前请确保已引入 `monet-client`：

### 方式一：Maven Central（推荐）

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
    }
}

// app/build.gradle.kts
dependencies {
    implementation("com.monettheme:monet-client:1.0.0")
}
```

### 方式二：本地 Maven

```bash
# 1. 克隆 MonetCore 主仓库
git clone https://github.com/Capslock800000/MonetCore.git
cd MonetCore

# 2. 发布到 mavenLocal
./gradlew :monet-api:publishToMavenLocal :monet-client:publishToMavenLocal
```

然后在 Sample App 的 `settings.gradle.kts` 中启用 `mavenLocal()`：

```kotlin
dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        google()
    }
}
```

## 构建

```bash
./gradlew :app:assembleDebug
```

## 运行

1. 先安装 `monet-service` APK（来自 MonetCore 主仓库 `:monet-service:assembleRelease`）
2. 再安装本 Sample App
3. `com.monettheme.permission.GENERATE_THEME` 权限已在 `AndroidManifest.xml` 中声明（Android 6+ 自动处理）

## 技术栈

| 组件 | 版本 |
|------|------|
| AGP | 9.2.1 |
| Gradle | 9.6.1 |
| Kotlin | 2.4.10 |
| Compose Compiler | 2.4.10 |
| compileSdk | 37 |
| minSdk | 28 |
| Material3 | 1.4.0 |

## 许可证

Apache License 2.0 — 详见 [LICENSE](LICENSE)
