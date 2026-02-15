use serde::{Deserialize, Serialize};
use serde_repr::{Deserialize_repr, Serialize_repr};

/// 显示方向枚举
#[derive(Serialize_repr, Deserialize_repr, PartialEq, Debug, Clone, Copy)]
#[repr(u8)]
pub enum Orientation {
    Portrait = 0,         // 竖屏（垂直方向）
    Landscape = 1,         // 横屏（水平方向，固定左侧在下）
    SensorLandscape = 2,   // 传感器横屏（根据重力传感器自动旋转横屏）
}

/// 设置显示方向的请求参数
#[derive(Debug, Deserialize, Serialize)]
#[serde(rename_all = "camelCase")]
pub struct SetOrientationRequest {
    /// 显示方向
    pub orientation: Orientation,
    /// 是否隐藏状态栏（顶部状态栏）
    #[serde(default = "default_hide_status_bar")]
    pub hide_status_bar: bool,
    /// 是否隐藏导航栏（底部导航栏）
    #[serde(default = "default_hide_navigation_bar")]
    pub hide_navigation_bar: bool,
}

fn default_hide_status_bar() -> bool {
    true
}

fn default_hide_navigation_bar() -> bool {
    true
}

/// 设置方向的响应
#[derive(Debug, Clone, Default, Deserialize, Serialize)]
#[serde(rename_all = "camelCase")]
pub struct SetOrientationResponse {
    pub success: bool,
}

/// 恢复默认方向设置的请求参数
#[derive(Debug, Deserialize, Serialize)]
#[serde(rename_all = "camelCase")]
pub struct RestoreOrientationRequest {}

/// 恢复默认方向设置的响应
#[derive(Debug, Clone, Default, Deserialize, Serialize)]
#[serde(rename_all = "camelCase")]
pub struct RestoreOrientationResponse {
    pub success: bool,
}
