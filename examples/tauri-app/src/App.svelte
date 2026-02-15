<script>
    import Greet from "./lib/Greet.svelte";
    import OrientationControl from "./lib/OrientationControl.svelte";
</script>

<main class="container">
    <h1>Welcome to Tauri!</h1>

    <div class="logos">
        <a href="https://vite.dev" target="_blank" rel="noreferrer">
            <img src="/vite.svg" class="logo vite" alt="Vite Logo" />
        </a>
        <a href="https://tauri.app" target="_blank" rel="noreferrer">
            <img src="/tauri.svg" class="logo tauri" alt="Tauri Logo" />
        </a>
        <a href="https://svelte.dev" target="_blank" rel="noreferrer">
            <img src="/svelte.svg" class="logo svelte" alt="Svelte Logo" />
        </a>
    </div>

    <p>Click on the Tauri, Vite, and Svelte logos to learn more.</p>

    <div class="content-section">
        <Greet />
    </div>

    <OrientationControl />

    <footer class="app-footer">
        <p>
            📱 <strong>Tauri Orientation Plugin</strong> - Control screen orientation and system bars on mobile
        </p>
        <p class="footer-note">
            Built with ❤️ using Tauri + Svelte + TypeScript
        </p>
    </footer>
</main>

<style>
    :global(body) {
        margin: 0;
        padding: 0;
        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, sans-serif;
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        min-height: 100vh;
    }

    .container {
        max-width: 1200px;
        margin: 0 auto;
        padding: 2rem 1rem;
    }

    h1 {
        font-size: 3rem;
        text-align: center;
        color: white;
        text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
        margin-bottom: 2rem;
    }

    .logos {
        display: flex;
        justify-content: center;
        align-items: center;
        gap: 2rem;
        margin-bottom: 2rem;
        flex-wrap: wrap;
    }

    .logo {
        height: 6rem;
        padding: 1rem;
        will-change: filter;
        transition: filter 0.3s;
    }

    .logo:hover {
        filter: drop-shadow(0 0 2rem rgba(255, 255, 255, 0.5));
    }

    .logo.vite:hover {
        filter: drop-shadow(0 0 2em #747bff);
    }

    .logo.tauri:hover {
        filter: drop-shadow(0 0 2em #ffc107);
    }

    .logo.svelte:hover {
        filter: drop-shadow(0 0 2em #ff3e00);
    }

    p {
        text-align: center;
        color: rgba(255, 255, 255, 0.9);
        font-size: 1.1rem;
        margin-bottom: 2rem;
    }

    .content-section {
        margin-bottom: 2rem;
    }

    .app-footer {
        margin-top: 3rem;
        padding: 2rem;
        text-align: center;
        color: rgba(255, 255, 255, 0.9);
    }

    .app-footer p {
        margin-bottom: 0.5rem;
    }

    .app-footer strong {
        color: white;
    }

    .footer-note {
        font-size: 0.9rem;
        opacity: 0.7;
    }

    @media (max-width: 768px) {
        h1 {
            font-size: 2rem;
        }

        .logos {
            gap: 1rem;
        }

        .logo {
            height: 4rem;
            padding: 0.5rem;
        }
    }
</style>
