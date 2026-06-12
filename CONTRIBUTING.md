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
├── example_project/        # Godot smoke-test project
├── templates/starter/      # Starter project template
├── templates/starter_project/ # Full first-project Godot files
├── scripts/                # Local CI and audit scripts
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
./scripts/local_ci.sh /path/to/godot-4.7-rc2
```

Before release-facing changes, validate from isolated clones instead of your
development worktree:

```sh
./scripts/fresh_clone_smoke.sh /path/to/godot-4.7-rc2
```

For a consumer project:

```sh
./gradlew installAddonJar \
  -PkanamaProjectDir=/path/to/a/godot/project \
  -PkanamaProjectScriptsDir=/path/to/a/godot/project
```

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
that Godot calls into the extension. Do not generate normal ptrcall wrappers
for them.

Current policy:
- Skip virtual methods in generated wrapper shells.
- Wire lifecycle callbacks through the KSP/script dispatch layer.
- Add virtual override support only through an explicit design change that
  registers the correct ClassDB virtual dispatch metadata.

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

Keep `HANDAUTHORED_CLASSES` and generator policy audits current when changing
this boundary.

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

Android support is experimental. Kanama has been validated inside stock Godot
Android exports through emulator smoke tests, but it is not yet a polished
phone-ready target.

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
