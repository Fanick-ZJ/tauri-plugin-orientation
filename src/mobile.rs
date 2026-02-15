use serde::de::DeserializeOwned;
use tauri::{
    plugin::{PluginApi, PluginHandle},
    AppHandle, Runtime,
};

use crate::models::*;

#[cfg(target_os = "ios")]
tauri::ios_plugin_binding!(init_plugin_orientation);

// initializes the Kotlin or Swift plugin classes
pub fn init<R: Runtime, C: DeserializeOwned>(
    _app: &AppHandle<R>,
    api: PluginApi<R, C>,
) -> crate::Result<Orientation<R>> {
    #[cfg(target_os = "android")]
    let handle = api.register_android_plugin("com.plugin.orientation", "OrientationPlugin")?;
    #[cfg(target_os = "ios")]
    let handle = api.register_ios_plugin(init_plugin_orientation)?;
    Ok(Orientation(handle))
}

/// Access to the orientation APIs.
pub struct Orientation<R: Runtime>(PluginHandle<R>);

impl<R: Runtime> Orientation<R> {
    /// 设置显示方向
    pub fn set_orientation(
        &self,
        payload: SetOrientationRequest,
    ) -> crate::Result<SetOrientationResponse> {
        self.0
            .run_mobile_plugin("set_orientation", payload)
            .map_err(Into::into)
    }

    /// 恢复默认方向设置
    pub fn restore_orientation(
        &self,
        payload: RestoreOrientationRequest,
    ) -> crate::Result<RestoreOrientationResponse> {
        self.0
            .run_mobile_plugin("restore_orientation", payload)
            .map_err(Into::into)
    }
}
