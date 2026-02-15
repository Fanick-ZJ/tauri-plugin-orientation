import { invoke } from "@tauri-apps/api/core";

/**
 * 显示方向枚举
 */
export const Orientation = {
  Portrait: 0, // 竖屏（垂直方向）
  Landscape: 1, // 横屏（水平方向，固定左侧在下）
  SensorLandscape: 2, // 传感器横屏（根据重力传感器自动旋转）
} as const;

/**
 * 设置方向参数接口
 */
export interface SetOrientationOptions {
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
export async function setOrientation(
  options: SetOrientationOptions,
): Promise<boolean> {
  return await invoke<{ success: boolean }>(
    "plugin:orientation|set_orientation",
    { payload: options },
  ).then((r) => r.success);
}

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
export async function restoreOrientation(): Promise<boolean> {
  return await invoke<{ success: boolean }>(
    "plugin:orientation|restore_orientation",
  ).then((r) => r.success);
}
