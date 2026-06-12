# Contributor Guide

This section is for changing Kanama itself: runtime code, generated wrappers,
Android support, demos, documentation, and release-facing validation.

Start with the root `CONTRIBUTING.md`, then read the page that matches the area
you are changing:

- [Architecture](architecture.md) before touching bootstrap, FFI, script
  lifetime, ClassDB registration, or hot reload.
- [Wrapper Maintenance](wrapper-maintenance.md) before changing generated
  wrappers, generator policy, ABI helpers, or generated KDoc.
- [Hot Reload Internals](hot-reload-internals.md) before changing script reload
  behavior or reload smoke checks.
- [Demo Porting Rules](demo-porting-rules.md) before changing demo ports or
  gameplay parity checks.
- [Android Internals](android-internals.md) before changing Android runtime or
  export support.

Use the narrowest useful check while iterating, then run the broader local gate
before release-facing changes:

```sh
mkdocs build --strict
python3 scripts/check_wrapper_generator.py
./gradlew jar
./scripts/local_ci.sh /path/to/godot-4.7-rc2
```

For release-facing changes, prefer the fresh-clone gate so the result does not
depend on your active development checkout or old local build state:

```sh
./scripts/fresh_clone_smoke.sh /path/to/godot-4.7-rc2
```
