package com.plugin.orientation

import OrientationManager
import android.app.Activity
import android.util.Log
import app.tauri.annotation.Command
import app.tauri.annotation.InvokeArg
import app.tauri.annotation.TauriPlugin
import app.tauri.plugin.JSObject
import app.tauri.plugin.Plugin
import app.tauri.plugin.Invoke

/**
 * 设置方向命令的参数
 */
@InvokeArg
class SetOrientationArgs {
    var orientation: Int = 0               // 方向：0=竖屏, 1=横屏, 2=传感器横屏
    var hideStatusBar: Boolean = true        // 是否隐藏状态栏
    var hideNavigationBar: Boolean = true    // 是否隐藏导航栏
}

/**
 * 恢复默认方向命令的参数（空）
 */
@InvokeArg
class RestoreOrientationArgs {

}

@TauriPlugin
class OrientationPlugin(private val activity: Activity) : Plugin(activity) {
    private val manager = OrientationManager(activity)

    /**
     * 设置显示方向
     */
    @Command
    fun set_orientation(invoke: Invoke) {
        val args = invoke.parseArgs(SetOrientationArgs::class.java)

        Log.i(
            "Orientation",
            "Setting orientation: ${args.orientation}, " +
                    "hideStatusBar: ${args.hideStatusBar}, " +
                    "hideNavigationBar: ${args.hideNavigationBar}"
        )

        // 将整数参数转换为枚举
        val orientationMode = when (args.orientation) {
            0 -> OrientationManager.OrientationMode.PORTRAIT
            1 -> OrientationManager.OrientationMode.LANDSCAPE
            2 -> OrientationManager.OrientationMode.SENSOR_LANDSCAPE
            else -> {
                Log.w("Orientation", "Unknown orientation: ${args.orientation}, defaulting to PORTRAIT")
                OrientationManager.OrientationMode.PORTRAIT
            }
        }

        // 调用管理器设置方向
        manager.setOrientation(
            orientationMode,
            args.hideStatusBar,
            args.hideNavigationBar
        )

        val ret = JSObject()
        ret.put("success", true)
        invoke.resolve(ret)
    }

    /**
     * 恢复默认方向设置
     */
    @Command
    fun restore_orientation(invoke: Invoke) {
        // 解析空参数类
        invoke.parseArgs(RestoreOrientationArgs::class.java)

        Log.i("Orientation", "Restoring default orientation")

        manager.restoreOrientation()

        val ret = JSObject()
        ret.put("success", true)
        invoke.resolve(ret)
    }
}
