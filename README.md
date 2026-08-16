# MonetCore Sample

[![Build Status](https://github.com/Capslock800000/MonetCore-Sample/actions/workflows/build.yml/badge.svg)](https://github.com/Capslock800000/MonetCore-Sample/actions)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.4.10-purple.svg)](https://kotlinlang.org/)
[![API](https://img.shields.io/badge/API-28%2B-brightgreen.svg)](https://developer.android.com/about/versions/9)

MonetCore 官方示例应用。演示如何通过 `monet-client` SDK 跨进程调用 `monet-service` 生成 Material 3 动态主题。

## 效果预览

- **启动页**：Google Play 风格「正在核对信息」无限旋转进度动画，**自动检测 Monet Theme Service 安装状态**
- **主界面**：展示从 Service 获取的完整 Material 3 色板，支持深浅模式切换与种子色更换

## 前置依赖

本仓库仅包含 Sample App，**不包含** MonetCore 引擎本身。引擎源码通过 Git Submodule 引入：

```bash
git clone --recursive https://github.com/Capslock800000/MonetCore-Sample.git
```

若已克隆但缺少子模块：

```bash
git submodule update --init --recursive
```

### 运行前准备

1. **先安装 `monet-service` APK**（来自 MonetCore 主仓库 `:monet-service:assembleDebug`）
2. 再安装本 Sample App
3. `com.monettheme.permission.GENERATE_THEME` 权限已在 `AndroidManifest.xml` 中声明（Android 6+ 自动处理）

> **注意**：启动页会检测包名 `com.monettheme.service`，未安装时将弹出错误提示，无法继续。

## 构建

```bash
./gradlew :app:assembleDebug
```

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
