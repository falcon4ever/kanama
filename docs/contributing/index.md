# Contributor Guide

This section is for changing Kanama itself: runtime code, generated wrappers,
Android support, demos, documentation, and release-facing validation.

Start with the root `CONTRIBUTING.md`, then read the page that matches the area
you are changing:

- [Architecture](architecture.md) before touching bootstrap, FFI, script
  lifetime, ClassDB registration, or hot reload.
- [Wrapper Maintenance](wrapper-maintenance.md) before changing generated
  wrappers, generator policy, ABI helpers, or generated KDoc.
- [Godot Upgrade Runbook](godot-upgrade.md) before bumping the Godot engine
  baseline.
- [API Coverage](api-coverage.md) and
  [Wrapper Generator Report](wrapper-generator-report.md) before promoting
  wrapper coverage or changing generator skip policy.
- [Hot Reload Internals](hot-reload-internals.md) before changing script reload
  behavior or reload smoke checks.
- [Demo Porting Rules](demo-porting-rules.md) before changing demo ports or
  gameplay parity checks.
- [Android Internals](android-internals.md) before changing Android runtime or
  export support.
- [Web Internals](web-internals.md) before changing the in-development
  Kotlin/Wasm Web backend, its generated proxy, or the versioned JS bridge.

Use the narrowest useful check while iterating, then run the broader local gate
before release-facing changes:

```sh
mkdocs build --strict
python3 scripts/check_wrapper_generator.py
./gradlew jar
./scripts/local_ci.sh /path/to/godot-4.7-stable
```

For release-facing changes, prefer the fresh-clone gate so the result does not
depend on your active development checkout or old local build state:

```sh
./scripts/fresh_clone_smoke.sh /path/to/godot-4.7-stable
```
