/**
 * 显示方向枚举
 */
export declare const Orientation: {
    readonly Portrait: 0;
    readonly Landscape: 1;
    readonly SensorLandscape: 2;
};
/**
 * 设置方向参数接口
 */
export interface OrientationOptions {
    /** 显示方向 */
    orientation: (typeof Orientation)[keyof typeof Orientation];
    /** 是否隐藏状态栏（顶部），默认 true */
    hideStatusBar?: boolean;
    /** 是否隐藏导航栏（底部），默认 true */
    hideNavigationBar?: boolean;
}
/**
 * 设置显示方向和系统栏显示状态
 *
 * @param options - 方向和系统栏配置选项
 * @returns Promise<boolean> - 是否设置成功
 *
 * @example
 * ```typescript
 * import { setOrientation, Orientation } from "tauri-plugin-orientation-api";
 *
 * // 设置为横屏，隐藏状态栏和导航栏
 * await setOrientation({ orientation: Orientation.Landscape });
 *
 * // 设置为竖屏，只隐藏状态栏
 * await setOrientation({
 *   orientation: Orientation.Portrait,
 *   hideStatusBar: true,
 *   hideNavigationBar: false
 * });
 *
 * // 设置为传感器横屏，不隐藏任何栏
 * await setOrientation({
 *   orientation: Orientation.SensorLandscape,
 *   hideStatusBar: false,
 *   hideNavigationBar: false
 * });
 * ```
 */
export declare function setOrientation(options: OrientationOptions): Promise<boolean>;
/**
 * 恢复默认方向设置和系统栏显示
 *
 * @returns Promise<boolean> - 是否恢复成功
 *
 * @example
 * ```typescript
 * import { restoreOrientation } from "tauri-plugin-orientation-api";
 *
 * await restoreOrientation();
 * ```
 */
export declare function restoreOrientation(): Promise<boolean>;
