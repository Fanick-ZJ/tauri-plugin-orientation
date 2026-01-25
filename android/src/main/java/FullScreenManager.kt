import android.app.Activity
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class FullscreenManager(private val activity: Activity) {

    enum class OrientationMode {
        LANDSCAPE,      // 横屏（固定）
        PORTRAIT,       // 竖屏（固定）
    }

    fun setFullscreen(orientation: OrientationMode, immersive: Boolean = true) {
        // 1. 设置方向
        val screenOrientation = when (orientation) {
            OrientationMode.LANDSCAPE -> ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            OrientationMode.PORTRAIT -> ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        activity.requestedOrientation = screenOrientation

        // 2. 设置全屏
        if (immersive) {
            enableImmersiveMode()
        } else {
            enableNormalFullscreen()
        }
    }

    private fun enableImmersiveMode() {
        WindowCompat.setDecorFitsSystemWindows(activity.window, false)

        val controller = WindowCompat.getInsetsController(activity.window, activity.window.decorView)

        // 隐藏状态栏和导航栏
        controller.hide(WindowInsetsCompat.Type.systemBars())

        // 粘性模式：滑动显示，自动隐藏
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        // 透明背景
        activity.window.apply {
            statusBarColor = Color.TRANSPARENT
            navigationBarColor = Color.TRANSPARENT

            // 刘海屏适配
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                attributes.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            }
        }
    }

    private fun enableNormalFullscreen() {
        // 仅隐藏状态栏，保留导航栏（适合阅读类应用）
        val controller = WindowCompat.getInsetsController(activity.window, activity.window.decorView)
        controller.hide(WindowInsetsCompat.Type.statusBars())
        controller.show(WindowInsetsCompat.Type.navigationBars())
    }

    fun exitFullscreen() {
        // 恢复默认方向
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        // 显示系统栏
        val controller = WindowCompat.getInsetsController(activity.window, activity.window.decorView)
        controller.show(WindowInsetsCompat.Type.systemBars())

        WindowCompat.setDecorFitsSystemWindows(activity.window, true)
    }
}
