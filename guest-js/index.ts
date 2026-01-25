import { invoke } from "@tauri-apps/api/core";

export const FullScreenMode = {
  LandSpace: 0,
  Portrait: 1,
} as const;

export async function full(
  orientation: (typeof FullScreenMode)[keyof typeof FullScreenMode],
): Promise<boolean> {
  return await invoke<{ value: boolean }>("plugin:fullscreen|full", {
    payload: {
      orientation,
    },
  }).then((r) => r.value);
}

export async function exit(): Promise<boolean> {
  return await invoke<{ value: boolean }>("plugin:fullscreen|exit", {
    payload: {},
  }).then((r) => r.value);
}
