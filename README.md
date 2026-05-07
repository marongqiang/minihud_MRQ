Mini HUD
==============
Mini HUD is a tiny client-side mod for Minecraft. that adds the coordinates, looking angle and current speed to the screen.
For more information and the downloads (compiled builds), see http://minecraft.curseforge.com/projects/minihud

## MRQ 修复

本分支基于上游代码进行了以下 bug 修复和性能优化：

### 严重修复
- **副手信标永不检测**: `OverlayRenderer.renderBeaconBoxForPlayerIfHoldingItem()` 中第二段检查错误使用 `getMainHandStack()` 改为 `getOffHandStack()`，修复副手持有信标时不显示覆盖范围
- **潮涌核心颜色回调错误**: `KeyCallbacks` 中 `CONDUIT_RANGE_OVERLAY_COLOR` 的回调错误调用了 `OverlayRendererBeaconRange` 改为 `OverlayRendererConduitRange`
- **结构数据并发竞态**: `DataStorage.addOrUpdateStructuresFromServer()` 添加 `synchronized(this.structures)` 保护，与渲染线程的 `getCopyOfStructureData()` 同步
- **MobCapData.hasValidData 逻辑错误**: `checkStagingComplete()` 中 tick 窗口检查失败时不再设置 `hasValidData = true`，修复报告无效数据为有效

### 并发安全
- `ShapeManager.shapes`: `ArrayList` → `CopyOnWriteArrayList`
- `RenderContainer.renderers`: `ArrayList` → `CopyOnWriteArrayList`

### 性能优化
- `RenderHandler`: `SimpleDateFormat` 缓存，避免每帧重复创建
- `DataStorage`: `PATTERN_SEED_NUMBER` 静态 Pattern 缓存
- 多个文件: 空 catch 块添加 `MiniHUD.logger` 日志输出

### 网络兼容
- `StructurePacketHandlerCarpet`: 协议版本不匹配时输出警告日志，不再静默丢弃

Compiling
=========
* Clone the repository
* Open a command prompt/terminal to the repository directory
* run 'gradlew build'
* The built jar file will be in build/libs/