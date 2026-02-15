use serde::de::DeserializeOwned;
use tauri::{plugin::PluginApi, AppHandle, Runtime};

use crate::models::*;

pub fn init<R: Runtime, C: DeserializeOwned>(
    app: &AppHandle<R>,
    _api: PluginApi<R, C>,
) -> crate::Result<Orientation<R>> {
    Ok(Orientation(app.clone()))
}

/// Access to the orientation APIs.
pub struct Orientation<R: Runtime>(AppHandle<R>);

impl<R: Runtime> Orientation<R> {
    /// 设置显示方向（桌面端暂不支持）
    pub fn set_orientation(
        &self,
        _payload: SetOrientationRequest,
    ) -> crate::Result<SetOrientationResponse> {
        // TODO: Implement desktop orientation functionality if needed
        Ok(SetOrientationResponse { success: false })
    }

    /// 恢复默认方向设置（桌面端暂不支持）
    pub fn restore_orientation(
        &self,
        _payload: RestoreOrientationRequest,
    ) -> crate::Result<RestoreOrientationResponse> {
        // TODO: Implement desktop orientation functionality if needed
        Ok(RestoreOrientationResponse { success: false })
    }
}
