use tauri::{
    plugin::{Builder, TauriPlugin},
    Manager, Runtime,
};

pub use models::*;

#[cfg(desktop)]
mod desktop;
#[cfg(mobile)]
mod mobile;

mod commands;
mod error;
mod models;

pub use error::{Error, Result};

#[cfg(desktop)]
use desktop::FullScreen;
#[cfg(mobile)]
use mobile::FullScreen;

/// Extensions to [`tauri::App`], [`tauri::AppHandle`] and [`tauri::Window`] to access the fullscreen APIs.
pub trait FullScreenExt<R: Runtime> {
    fn full_screen(&self) -> &FullScreen<R>;
}

impl<R: Runtime, T: Manager<R>> crate::FullScreenExt<R> for T {
    fn full_screen(&self) -> &FullScreen<R> {
        self.state::<FullScreen<R>>().inner()
    }
}

/// Initializes the plugin.
pub fn init<R: Runtime>() -> TauriPlugin<R> {
    Builder::new("fullscreen")
        .invoke_handler(tauri::generate_handler![commands::full, commands::exit])
        .setup(|app, api| {
            #[cfg(mobile)]
            let full_screen = mobile::init(app, api)?;
            #[cfg(desktop)]
            let full_screen = desktop::init(app, api)?;
            app.manage(full_screen);
            Ok(())
        })
        .build()
}
