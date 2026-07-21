# Contributing to Kanama

This guide is for anyone changing Kanama: runtime contributors, demo porters,
and documentation contributors.

## Repo Layout

```text
kanama/
├── src/                    # Runtime, binding layer, API wrappers
├── annotations/            # KSP annotation definitions
├── processor/              # KSP processor for script registrars
├── project-scripts/        # Gradle module for user Kotlin scripts
├── bootstrap/              # Native GDExtension bootstrap (C, desktop + Android)
├── gdextension/            # Pinned gdextension_interface.h input
├── android/                # Godot Android plugin (AAR)
├── ios/                    # iOS C shim + headers
├── ios-runtime/            # Kotlin/Native iOS runtime + generated iOS wrappers
├── example_project/        # Godot smoke-test project
├── templates/              # starter, starter_project, release-kit, store-addon
├── scripts/                # Local CI, smoke, audit, and generator scripts
└── docs/                   # MkDocs documentation
```

The companion demo repository is
[falcon4ever/kanama-demos](https://github.com/falcon4ever/kanama-demos). It is
a separate checkout and contains external Godot projects ported to Kanama.

## Before You Start

1. Read `docs/contributing/architecture.md` before touching FFI, binding, or
   script lifetime code.
2. Check the open issue, pull request, or maintainer-provided task context for
   the current priority.
3. Establish a local baseline with:

   ```sh
   ./scripts/local_ci.sh /path/to/godot_binary
   ```

## Workflow

- Keep changes focused and reviewable.
- Prefer existing patterns and helper APIs over new abstractions.
- Do not rewrite generated or hand-authored wrapper policy casually.
- Add or update tests, smoke scripts, or audits when a change affects runtime
  behavior, ABI shape, generated wrappers, KSP marshalling, or public API.
- Run the narrowest useful validation while iterating, then run the broader
  check before committing.
- Keep release-facing decisions in documentation, issue threads, pull requests,
  or the changelog instead of ad hoc progress notes.

Typical validation:

```sh
./gradlew build
python3 scripts/validate_godot_api.py --api extension_api.json
./scripts/local_ci.sh /path/to/godot-4.7-stable
```

Before release-facing changes, validate from isolated clones instead of your
development worktree:

```sh
./scripts/fresh_clone_smoke.sh /path/to/godot-4.7-stable
```

For a consumer project:

```sh
./gradlew installAddonJar \
  -PkanamaProjectDir=/path/to/a/godot/project \
  -PkanamaProjectScriptsDir=/path/to/a/godot/project
```

## Formatting

Hand-written Kotlin is formatted with [ktfmt](https://github.com/facebook/ktfmt)
(googleStyle, 2-space indent), configured in the root `build.gradle.kts`. Format
your changes before pushing:

```sh
./gradlew ktfmtFormat   # rewrite files in place
./gradlew ktfmtCheck    # verify formatting without writing (what CI runs)
```

`local_ci.sh` runs `ktfmtCheck` as a gate stage, so an unformatted change fails
CI. Two things are deliberately **not** formatted:

- Generated Godot API wrappers under `**/net/multigesture/kanama/api/**` — they
  are byte-compared against `scripts/generate_api_wrapper.py` by the drift gate,
  so formatting them would break `check_wrapper_generator.py`.
- `*.gradle.kts` build scripts — the ktfmt Gradle-DSL parser mishandles
  `ios-runtime/build.gradle.kts`.

The knobs live in the `ktfmt { ... }` block in `build.gradle.kts`.

## Wrapper Work

1. Check `extension_api.json` for method names, hashes, argument metadata, and
   virtual-method flags.
2. Check the coverage and candidate reports before adding wrappers:

   ```sh
   python3 scripts/api_wrapper_coverage.py
   python3 scripts/api_wrapper_candidates.py
   ```

3. Validate method binds after wrapper changes:

   ```sh
   python3 scripts/validate_godot_api.py --api extension_api.json
   ```

4. Run the relevant ABI, shape, and signature audits.
5. Add a smoke or compile fixture for user-visible API additions.

Only add methods whose types have a known `ObjectCalls` marshal path. If a
method needs a new ABI shape, add the exact helper and audit coverage first.

## Virtual Methods

Methods marked `"is_virtual": true` in `extension_api.json` are override points
that Godot calls into the extension. They are never generated as normal ptrcall
wrappers — a script overrides them with `@OverrideVirtual` (the Kotlin function
name is the virtual's name, GDScript-style), validated against the generated
signature table (`scripts/generate_virtual_signature_table.py`) and dispatched
through the script-instance layer.

Current policy:
- Skip virtual methods in generated wrapper shells (they are not callable API).
- Lifecycle callbacks (`@OnReady`, `@OnProcess`, …) ride the same dispatch.
- Return marshalling covers every Variant-expressible family on desktop/Android;
  see the "Virtual-return coverage" section of
  `docs/contributing/wrapper-maintenance.md` for the exact bounds and the
  documented iOS residue.

## Generated vs Hand-Authored Wrappers

Generated wrappers should be fully regeneratable from `extension_api.json`.
Hand-authored wrappers are reserved for classes with ownership, singleton,
dynamic-call, or Kanama-specific policy.

Generated candidates:
- Pure nodes/resources with known helper shapes
- 1-to-1 MethodBind calls
- No special lifetime or ownership behavior

Hand-authored candidates:
- `FileAccess`, `DirAccess`, `SceneTree`, and similar managed objects
- Singletons and engine-owned instances
- Classes using unsupported `Array`, `Dictionary`, `Callable`, or ownership
  sensitive APIs
- Classes with Kanama-specific factories, closeable handles, or dynamic
  behavior

The hand-shaped exemption lists (`DESKTOP_HANDSHAPED` / `IOS_HANDSHAPED` in
`scripts/check_wrapper_generator.py`) and the generator policy audits must stay
current when changing this boundary — the full drift-gate fails otherwise.

## Porting And Demo Integration

Ported demos are integration pressure for real Kanama consumer code. When a
demo breaks after wrapper generation, decide whether the fix belongs in Kanama
or in stale demo code:

- Restore a Kanama convenience when it matches Godot defaults or common API
  usage.
- Fix the demo when generated wrappers are now more precise than an old
  permissive hand wrapper.
- Add guardrails when a mistake could recur in generated output.

The demos repository exposes:

```sh
./gradlew buildAllScripts
./gradlew demoParityAudit
```

## Android Port Notes

Android is Supported on Godot 4.7 stable (promoted from Experimental
2026-07-14): the Pixel 7 debug demo matrix, an R8-minified release APK (via
Kanama's PanamaPort fork), and the nine-demo Vulkan/Mobile renderer smoke have
all passed, alongside debug breadth on three more models. Note that release
builds require Android 13+ and packaged addons are runtime-only; see
`docs/reference/version-support.md` for the full support claim.

Known constraints:
- Android uses ART, so the GDExtension library must attach to the existing VM
  instead of creating a desktop JVM.
- PanamaPort mirrors the FFM API under `com.v7878.foreign`.
- Android downcall handles must use `invokeWithArguments(...)`; desktop can
  keep direct `invoke(...)`.
- Hot reload is desktop-editor-only. Android builds should load Kotlin classes
  from the APK at startup.
- `.gdextension` files need Android ABI entries for the bridge library.

See `docs/exporting/android.md` and `docs/contributing/android-internals.md`
for the current Android workflow and limitations.

## Design Rules

- Kotlin public APIs use lower-camel names. Do not add PascalCase aliases.
- `Node` and `GodotObject` wrappers are non-owning.
- `Resource` and `RefCounted` wrappers must follow the established closeable
  ownership policy.
- Do not expose broad `Callable`, `Dictionary`, or generic container APIs
  without explicit policy and tests.
- Do not ignore ABI metadata such as `uint32`, `int32`, enum slots, or typed
  arrays.
- Public API changes need focused validation.
- Prefer automated guardrails over one-off manual knowledge.
