use tauri::{command, AppHandle, Runtime};

use crate::models::*;
use crate::FullScreenExt;
use crate::Result;

#[command]
pub(crate) async fn full<R: Runtime>(
    app: AppHandle<R>,
    payload: FullRequest,
) -> Result<FullResponse> {
    app.full_screen().full(payload)
}

#[command]
pub(crate) async fn exit<R: Runtime>(
    app: AppHandle<R>,
    payload: ExitRequest,
) -> Result<ExitResponse> {
    app.full_screen().exit(payload)
}
