# Contributor Guide

This section is for changing Kanama itself: runtime code, generated wrappers,
Android support, demos, documentation, and release-facing validation.

Start with the root `CONTRIBUTING.md`, then read the page that matches the area
you are changing:

- [Architecture](architecture.md) before touching bootstrap, FFI, script
  lifetime, ClassDB registration, or hot reload.
- [Wrapper Maintenance](wrapper-maintenance.md) before changing generated
  wrappers, generator policy, ABI helpers, or generated KDoc.
- [Shared Wrapper Tree: Design Check](shared-wrapper-tree-design-check.md)
  before proposing to merge the desktop and iOS generated wrapper trees.
- [Godot Upgrade Runbook](godot-upgrade.md) before bumping the Godot engine
  baseline.
- [API Coverage](../reference/generated/api-coverage.md) and
  [Wrapper Generator Report](../reference/generated/wrapper-generator-report.md)
  (generated reports) before promoting wrapper
  coverage or changing generator skip policy.
- [Hot Reload Internals](hot-reload-internals.md) before changing script reload
  behavior or reload smoke checks.
- [Demo Porting Rules](demo-porting-rules.md) before changing demo ports or
  gameplay parity checks.
- The backend internals pages, one per non-desktop platform, before changing
  that platform's runtime or export support:
  [Android Internals](backends/android.md),
  [iOS Backend Architecture](backends/ios.md), and
  [Web Internals](backends/web.md) (the Kotlin/Wasm backend, its generated
  proxy, and the versioned JS bridge). The user-facing export workflow for
  each lives under Shipping Your Game; these pages hold the design and the gates.
- [Gates Index](../reference/generated/gates.md) (generated from
  `local_ci.sh`, the workflows, and the ledger) to find what each `local_ci.sh`
  stage, CI job, and local-only device/browser gate proves and where it runs,
  and to see the `evidence/gates.json` ledger of when those local gates last
  ran on the current Godot pin.

Landing a change that **removes** a limitation is its own kind of change: the
comments asserting that limitation are now false and nothing else will notice.
Grep for them, and see "Documented Limitations" in the root `CONTRIBUTING.md`
for the `KANAMA-BLOCKED` marker that makes the machine-checkable ones fail the
build the day they go stale.

## Building From Source

The user steps are on [Install From a Source Checkout](../getting-started/source-checkout.md).
What sits under them:

- The desktop native bootstrap is built from the GDExtension headers tracked in
  this repository, the JDK 25 headers, CMake and the platform C toolchain. No
  Godot source checkout is involved.
- Gradle passes its JDK 25 toolchain to CMake as `-DKANAMA_JAVA_HOME`. Without
  Gradle, `bootstrap/CMakeLists.txt` uses `JAVA_HOME`, then on macOS
  `/usr/libexec/java_home -v 25`, then the system default JDK.
- On every OS the configure step stops with a message naming the JDK it found
  when that JDK's `jni.h` has no `JNI_VERSION_21`, so a bare
  `JNI_VERSION_21 undeclared` from `bootstrap.c` does not appear.
- `./gradlew syncExampleAddonJar` refreshes the checked-in `example_project`
  and is the local smoke-test path for it: the counterpart of `installAddonJar`
  for that project.

Use the narrowest useful check while iterating, then run the broader local gate
before release-facing changes:

```sh
mkdocs build --strict
python3 scripts/check_wrapper_generator.py
./gradlew jvmJar
./scripts/local_ci.sh /path/to/godot-4.7.2-stable
```

For release-facing changes, prefer the fresh-clone gate so the result does not
depend on your active development checkout or old local build state:

```sh
./scripts/fresh_clone_smoke.sh /path/to/godot-4.7.2-stable
```
