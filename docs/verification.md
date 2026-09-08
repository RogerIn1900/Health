# 验证记录

日期：2026-09-08。实现分支：`feat/resume-target-health`，基线 `8613fd8`。

## 已执行

- JDK 17，Gradle 8.7，Android SDK 35。
- `ANDROID_HOME=<sdk> ./gradlew :app:assembleDebugAndroidTest :app:testDebugUnitTest --console=plain`：成功；JUnit XML 汇总 **24 项单元测试，0 失败、0 跳过**。
- `ANDROID_HOME=<sdk> ANDROID_SERIAL=emulator-5556 ./gradlew :app:connectedDebugAndroidTest :app:lintDebug --console=plain`：成功；专用 ResumeTargetApi36 模拟器 Android 16 / API 36，**10 项设备测试，0 失败、0 跳过**。
- 上述任务实际构建并安装 Debug App 与测试 APK。Lint **0 error、344 warning、7 information**；本轮没有通过关闭检查或新增 baseline 隐藏错误，历史警告尚未逐一整改。
- `git diff --check`：通过。

## 覆盖范围

纯函数图表边界、QR 亮度平面和旋转、真实二维码解码、重复识别和帧关闭、输入校验和提交日期、Room 用户日期隔离/覆盖写入/逻辑删除恢复、图表点击及空数据、保存删除 UI、无相机权限时退出扫描并继续保存记录。

## 修复过的验证失败

旧 Espresso 在 Android 16 调用已不存在的 `InputManager.getInstance()`，导致 4 项 UI 测试失败。升级 Espresso 3.7.0 / Test JUnit 1.3.0，并移除正式 App 中误声明的 Espresso 和 Compose UI Test 依赖后，全部 10 项设备测试通过。官方依据见工程说明。

## 尚未验证

- 真实摄像头光照、对焦及不同 OEM 相机实现；当前扫描测试使用生成的 QR 帧，不虚构真实摄像头识别率。
- 系统权限对话框的所有拒绝/永久拒绝组合；当前设备回归验证未授予权限时的退路。
- GitHub Actions 已配置，远程执行尚未发生：`GIT_TERMINAL_PROMPT=0 git push --dry-run origin HEAD` 返回缺少 HTTPS 用户凭据。公开远程可读取不代表已获得推送权限。

## 复现与提交

设备测试需要使用专用设备，避免干扰个人应用。报告位于 `app/build/reports/tests`、`app/build/reports/androidTests` 和 `app/build/reports/lint-results-debug.html`。构建目录不提交；源码与测试随对应功能单独提交。
