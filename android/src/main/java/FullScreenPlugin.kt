package com.plugin.fullscreen

import FullscreenManager
import android.app.Activity
import android.content.pm.ActivityInfo
import android.util.Log
import app.tauri.annotation.Command
import app.tauri.annotation.InvokeArg
import app.tauri.annotation.TauriPlugin
import app.tauri.plugin.JSObject
import app.tauri.plugin.Plugin
import app.tauri.plugin.Invoke

@InvokeArg
class FullScreenArgs {
    var orientation: Int? = 0
}

@TauriPlugin
class FullScreenPlugin(private val activity: Activity): Plugin(activity) {
    private val manager = FullscreenManager(activity)
    @Command
    fun full(invoke: Invoke) {
        val args = invoke.parseArgs(FullScreenArgs::class.java)
        val orientation = args.orientation ?: 0  // 默认横屏

        Log.i("FullScreen", "Setting fullscreen with orientation: $orientation")

        when (orientation) {
            0 -> {
                manager.setFullscreen(FullscreenManager.OrientationMode.LANDSCAPE, true)
            }
            1 -> {
                manager.setFullscreen(FullscreenManager.OrientationMode.PORTRAIT, true)
            }
        }

        val ret = JSObject()
        ret.put("value", true)
        invoke.resolve(ret)
    }

    @Command
    fun exit(invoke: Invoke) {
        manager.exitFullscreen()
        invoke.resolve()
    }


}
