<script lang="ts">
	import { setOrientation, restoreOrientation, Orientation } from "tauri-plugin-orientation-api";

	let message = $state("");
	let loading = $state(false);

	async function showMessage(msg: string) {
		message = msg;
		setTimeout(() => (message = ""), 2000);
	}

	async function setPortrait() {
		loading = true;
		try {
			const success = await setOrientation({
				orientation: Orientation.Portrait,
				hideStatusBar: true,
				hideNavigationBar: true
			});
			showMessage(success ? "✓ 竖屏设置成功" : "设置失败");
		} catch (e) {
			console.error(e);
			showMessage("错误: " + e);
		} finally {
			loading = false;
		}
	}

	async function setLandscape() {
		loading = true;
		try {
			const success = await setOrientation({
				orientation: Orientation.Landscape,
				hideStatusBar: true,
				hideNavigationBar: true
			});
			showMessage(success ? "✓ 横屏设置成功" : "设置失败");
		} catch (e) {
			console.error(e);
			showMessage("错误: " + e);
		} finally {
			loading = false;
		}
	}

	async function setSensor() {
		loading = true;
		try {
			const success = await setOrientation({
				orientation: Orientation.SensorLandscape,
				hideStatusBar: true,
				hideNavigationBar: false
			});
			showMessage(success ? "✓ 传感器横屏设置成功" : "设置失败");
		} catch (e) {
			console.error(e);
			showMessage("错误: " + e);
		} finally {
			loading = false;
		}
	}

	async function restore() {
		loading = true;
		try {
			const success = await restoreOrientation();
			showMessage(success ? "✓ 已恢复默认设置" : "恢复失败");
		} catch (e) {
			console.error(e);
			showMessage("错误: " + e);
		} finally {
			loading = false;
		}
	}
</script>

<div class="orientation-control">
	<h2>屏幕方向控制</h2>

	<div class="button-group">
		<button
			onclick={setPortrait}
			disabled={loading}
			class="btn"
		>
			竖屏（隐藏栏）
		</button>

		<button
			onclick={setLandscape}
			disabled={loading}
			class="btn"
		>
			横屏（隐藏栏）
		</button>

		<button
			onclick={setSensor}
			disabled={loading}
			class="btn"
		>
			传感器横屏（仅隐藏状态栏）
		</button>

		<button
			onclick={restore}
			disabled={loading}
			class="btn restore"
		>
			恢复默认
		</button>
	</div>

	{#if message}
		<div class="message">{message}</div>
	{/if}
</div>

<style>
	.orientation-control {
		max-width: 600px;
		margin: 2rem auto;
		padding: 2rem;
		background: #f5f5f5;
		border: 1px solid #ddd;
		border-radius: 8px;
	}

	.orientation-control h2 {
		margin: 0 0 1.5rem 0;
		font-size: 1.5rem;
		text-align: center;
		color: #333;
	}

	.button-group {
		display: flex;
		flex-direction: column;
		gap: 1rem;
	}

	.btn {
		padding: 1rem 1.5rem;
		font-size: 1rem;
		border: none;
		border-radius: 4px;
		background: #007bff;
		color: white;
		cursor: pointer;
		transition: background 0.2s;
		font-weight: 500;
	}

	.btn:hover:not(:disabled) {
		background: #0056b3;
	}

	.btn:active:not(:disabled) {
		background: #00408a;
		transform: scale(0.98);
	}

	.btn:disabled {
		opacity: 0.6;
		cursor: not-allowed;
	}

	.btn.restore {
		background: #6c757d;
	}

	.btn.restore:hover:not(:disabled) {
		background: #5a6268;
	}

	.message {
		margin-top: 1.5rem;
		padding: 0.75rem 1rem;
		text-align: center;
		background: #d4edda;
		border: 1px solid #c3e6cb;
		border-radius: 4px;
		color: #155724;
		font-weight: 500;
		animation: fadeIn 0.3s ease;
	}

	@keyframes fadeIn {
		from {
			opacity: 0;
			transform: translateY(-10px);
		}
		to {
			opacity: 1;
			transform: translateY(0);
		}
	}

	@media (max-width: 768px) {
		.orientation-control {
			margin: 1rem;
			padding: 1.5rem;
		}

		.orientation-control h2 {
			font-size: 1.25rem;
		}

		.btn {
			padding: 0.875rem 1.25rem;
			font-size: 0.9375rem;
		}
	}
</style>
