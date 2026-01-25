use serde::de::DeserializeOwned;
use tauri::{
    plugin::{PluginApi, PluginHandle},
    AppHandle, Runtime,
};

use crate::models::*;

#[cfg(target_os = "ios")]
tauri::ios_plugin_binding!(init_plugin_full_screen);

// initializes the Kotlin or Swift plugin classes
pub fn init<R: Runtime, C: DeserializeOwned>(
    _app: &AppHandle<R>,
    api: PluginApi<R, C>,
) -> crate::Result<FullScreen<R>> {
    #[cfg(target_os = "android")]
    let handle = api.register_android_plugin("com.plugin.fullscreen", "FullScreenPlugin")?;
    #[cfg(target_os = "ios")]
    let handle = api.register_ios_plugin(init_plugin_full_screen)?;
    Ok(FullScreen(handle))
}

/// Access to the fullscreen APIs.
pub struct FullScreen<R: Runtime>(PluginHandle<R>);

impl<R: Runtime> FullScreen<R> {
    pub fn full(&self, payload: FullRequest) -> crate::Result<FullResponse> {
        self.0
            .run_mobile_plugin("full", payload)
            .map_err(Into::into)
    }
    pub fn exit(&self, payload: ExitRequest) -> crate::Result<ExitResponse> {
        self.0
            .run_mobile_plugin("exit", payload)
            .map_err(Into::into)
    }
}
