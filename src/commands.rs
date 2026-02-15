use tauri::{command, AppHandle, Runtime};

use crate::models::*;
use crate::OrientationExt;
use crate::Result;

/// 设置显示方向
#[command]
pub(crate) async fn set_orientation<R: Runtime>(
    app: AppHandle<R>,
    payload: SetOrientationRequest,
) -> Result<SetOrientationResponse> {
    app.orientation().set_orientation(payload)
}

/// 恢复默认方向设置
#[command]
pub(crate) async fn restore_orientation<R: Runtime>(
    app: AppHandle<R>,
) -> Result<RestoreOrientationResponse> {
    app.orientation()
        .restore_orientation(RestoreOrientationRequest {})
}
