import android.app.Activity
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

/**
 * 显示方向管理器
 * 负责设置屏幕方向和控制系统栏（状态栏、导航栏）的显示与隐藏
 */
class OrientationManager(private val activity: Activity) {

    /**
     * 设置显示方向和系统栏显示状态
     *
     * @param orientation 方向模式（PORTRAIT、LANDSCAPE、SENSOR_LANDSCAPE）
     * @param hideStatusBar 是否隐藏状态栏（顶部）
     * @param hideNavigationBar 是否隐藏导航栏（底部）
     */
    fun setOrientation(
        orientation: OrientationMode,
        hideStatusBar: Boolean = true,
        hideNavigationBar: Boolean = true
    ) {
        // 1. 设置屏幕方向
        val screenOrientation = when (orientation) {
            OrientationMode.PORTRAIT -> ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            OrientationMode.LANDSCAPE -> ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            OrientationMode.SENSOR_LANDSCAPE -> ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        }
        activity.requestedOrientation = screenOrientation

        // 2. 设置系统栏显示状态
        setupSystemBars(hideStatusBar, hideNavigationBar)
    }

    /**
     * 设置系统栏（状态栏和导航栏）的显示状态
     */
    private fun setupSystemBars(
        hideStatusBar: Boolean,
        hideNavigationBar: Boolean
    ) {
        val controller = WindowCompat.getInsetsController(activity.window, activity.window.decorView)
        val windowInsetsType = WindowInsetsCompat.Type.systemBars()

        if (hideStatusBar && hideNavigationBar) {
            // 隐藏状态栏和导航栏
            enableFullscreen(controller)
        } else if (hideStatusBar) {
            // 仅隐藏状态栏
            controller.hide(WindowInsetsCompat.Type.statusBars())
            controller.show(WindowInsetsCompat.Type.navigationBars())
            WindowCompat.setDecorFitsSystemWindows(activity.window, true)
        } else if (hideNavigationBar) {
            // 仅隐藏导航栏
            controller.show(WindowInsetsCompat.Type.statusBars())
            controller.hide(WindowInsetsCompat.Type.navigationBars())
            WindowCompat.setDecorFitsSystemWindows(activity.window, true)
        } else {
            // 都不隐藏
            controller.show(windowInsetsType)
            WindowCompat.setDecorFitsSystemWindows(activity.window, true)
        }
    }

    /**
     * 启用全屏模式（隐藏状态栏和导航栏）
     */
    private fun enableFullscreen(controller: WindowInsetsControllerCompat) {
        WindowCompat.setDecorFitsSystemWindows(activity.window, false)

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

    /**
     * 恢复默认方向设置和系统栏显示
     */
    fun restoreOrientation() {
        // 恢复默认方向
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        // 显示所有系统栏
        val controller = WindowCompat.getInsetsController(activity.window, activity.window.decorView)
        controller.show(WindowInsetsCompat.Type.systemBars())

        WindowCompat.setDecorFitsSystemWindows(activity.window, true)
    }

    /**
     * 显示方向枚举
     */
    enum class OrientationMode {
        PORTRAIT,           // 竖屏（垂直方向）
        LANDSCAPE,          // 横屏（水平方向，固定左侧在下）
        SENSOR_LANDSCAPE    // 传感器横屏（根据重力传感器自动旋转）
    }
}
