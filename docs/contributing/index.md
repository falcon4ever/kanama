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

### JDK Lookup

The user rules are on [JDK Selection](../getting-started/editor-workflow.md#jdk-selection).
The Kanama Tools plugin (`plugin.gd`) and the native runtime (`bootstrap.c`)
resolve the JDK the same way; `scripts/check_jdk_locations_parity.py` keeps
their install-folder tables equal and `scripts/check_jdk_lookup_parity.sh`
compares their picks.

- **Order.** The `kanama/build/jdk_path` editor setting, then `JAVA_HOME` from
  the editor's environment, then the best JDK in the install folders.
- **Install folders.** A JDK counts when its `release` file says 25 or newer
  and `lib/server/libjvm.*` exists. Among those, a GA release beats an
  early-access one, exactly 25 beats a newer major version, then the newer
  version wins, then the path that sorts first.
- **The editor setting** must be an absolute path; `~/` is expanded. A wrong
  value is an error, never silently replaced by another JDK. A `JAVA_HOME`
  older than 25 is skipped, not an error, since it is usually set for another
  project.
- **Build Scripts** needs a full JDK with `include/jni.h`, because a JRE
  cannot compile the native bootstrap. If none qualifies, the build stops
  before it starts with an error that names `kanama/build/jdk_path` and
  `JAVA_HOME`. The bootstrap's CMake step also checks that `jni.h` defines
  `JNI_VERSION_21` (a JDK 21 or newer header) and names the JDK when it does
  not.
- **The runtime** cannot read editor settings when Godot loads it. The plugin
  writes a valid `kanama/build/jdk_path` to `.godot/kanama_jdk_home` in the
  project, and removes the file when the setting is empty or invalid; the
  runtime reads that file first. If the JDK it names has been removed or is
  older than 25, the runtime logs `[kanama] ignoring .godot/kanama_jdk_home=...`
  with the reason and goes on with `JAVA_HOME` and the install folders. The
  runtime reads the file once, when Godot loads the extension, which is before
  the plugin can write it. So on the first open of a project (a fresh clone, a
  deleted `.godot`) with the setting set, and after any change to the setting,
  the plugin warns "Restart the editor to use JDK ..." instead of reporting OK.
- **Gradle** runs on any JDK 25+. The build pins `jvmToolchain(25)`; with only
  another JDK installed (a JDK 26-only machine, say), the foojay toolchain
  resolver in `settings.gradle.kts`, and in the release kit's
  `settings.gradle.kts`, downloads a JDK 25 once into `~/.gradle/jdks`.
- **An exported game** with a bundled runtime ignores all of this and uses its
  own `runtime/` folder.

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
