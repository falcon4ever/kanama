# Work on Kanama

Use this path when you are changing Kanama itself: runtime code, annotations,
the KSP processor, generated wrappers, native bootstrap, docs, or package
artifacts.

## Local Checks

Run the main Gradle checks:

```sh
./gradlew check -PkanamaBuildNativeBootstrap=false
```

Build docs strictly:

```sh
mkdocs build --strict
```

Run local CI with a Godot binary:

```sh
scripts/local_ci.sh /absolute/path/to/godot-4.7-rc2
```

For a release-facing source check, run the isolated clone gate:

```sh
scripts/fresh_clone_smoke.sh /absolute/path/to/godot-4.7-rc2
```

## Package Builds

Build the host desktop kit and local host store addon:

```sh
./gradlew packageDistributions
```

Build only the desktop kit:

```sh
./gradlew packageDesktopKit
```

Validate a desktop kit without a sibling Kanama checkout:

```sh
scripts/package_install_smoke.sh \
  --desktop-kit \
  build/distributions/kanama-desktop-kit-v<version>-<platform>.zip \
  /absolute/path/to/godot-4.7-rc2
```

Validate a store addon through the same temporary-project smoke:

```sh
scripts/package_install_smoke.sh \
  --store-addon \
  build/distributions/kanama-store-addon-v<version>.zip \
  /absolute/path/to/godot-4.7-rc2
```

GitHub Actions builds the release matrix for macOS arm64, Linux x64, Linux
ARM64, and Windows x64. The package workflow intentionally has no pull request
trigger; release artifacts are built only from manual dispatch or `v*` tags.

## Wrapper and API Work

Use the contributing docs for deeper maintenance paths:

- [Architecture](../contributing/architecture.md)
- [Wrapper Maintenance](../contributing/wrapper-maintenance.md)
- [Hot Reload Internals](../contributing/hot-reload-internals.md)
- [Demo Porting Rules](../contributing/demo-porting-rules.md)
