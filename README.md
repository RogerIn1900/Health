# Health

Kotlin / Jetpack Compose 健康记录、图表与扫码实践。当前分支重新启用健康应用入口，历史宿舍管理代码仍保留在源码中。

## 功能与边界

- 单个本地档案，按日期手动录入热量、步数与活动分钟。Room 以用户和日期作为联合主键，支持更新、逻辑删除和重新保存恢复。
- 自适应半圆环与柱状图，几何计算和命中判断独立测试，空值、负值和超目标输入有明确边界。
- CameraX / ZXing 扫码：亮度平面、行步长、旋转、重复识别抑制与资源释放；链接只有在用户点击后才打开。

没有连接健康传感器、健康云后端或账号登录。图表目标是演示值，不是个性化健康建议。健康数据独立保存在 `health-records.db`，不转换历史宿舍业务数据库。

## 构建

JDK 17，Android SDK 35，设置 `ANDROID_HOME`。

```sh
./gradlew :app:testDebugUnitTest :app:lintDebug :app:assembleDebug
ANDROID_SERIAL=<test-device> ./gradlew :app:connectedDebugAndroidTest
```

[工程说明与简历表述](docs/resume-target.md) · [验证记录](docs/verification.md)
