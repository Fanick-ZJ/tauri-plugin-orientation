use serde::de::DeserializeOwned;
use tauri::{plugin::PluginApi, AppHandle, Runtime};

use crate::models::*;

pub fn init<R: Runtime, C: DeserializeOwned>(
    app: &AppHandle<R>,
    _api: PluginApi<R, C>,
) -> crate::Result<FullScreen<R>> {
    Ok(FullScreen(app.clone()))
}

/// Access to the fullscreen APIs.
pub struct FullScreen<R: Runtime>(AppHandle<R>);

impl<R: Runtime> FullScreen<R> {
    pub fn full(&self, payload: FullRequest) -> crate::Result<FullResponse> {
        // TODO: Implement fullscreen functionality
        Ok(FullResponse { value: false })
    }
    pub fn exit(&self, payload: ExitRequest) -> crate::Result<ExitResponse> {
        // TODO: Implement fullscreen functionality
        Ok(ExitResponse { value: false })
    }
}
