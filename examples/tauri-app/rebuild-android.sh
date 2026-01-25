#!/bin/bash
# 快速重建 Android 应用（用于 Kotlin 代码修改后）

set -e

echo "🧹 清理 Android 构建缓存..."
rm -rf src-tauri/gen/android/app/build
rm -rf src-tauri/gen/android/.gradle

echo "📱 重新构建并启动 Android 应用..."
pnpm tauri android dev
