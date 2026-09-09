# Agent Guide

This file is the short operational map for coding agents and maintainers.
Public user documentation lives in `docs/`; keep this file focused on how to
work in the repository without scanning the whole tree or breaking Kanama's
release shape.

## Current Baseline

- Kanama release line: `0.4.0` (tagged 2026-07-24; a pre-1.0 preview baseline).
- Godot baseline: Godot `4.7.2 stable` (re-pin only on stable releases; bumps
  follow `docs/contributing/godot-upgrade.md`).
- Desktop runtime/build JDK: JDK `25+`.
- Mobile is Supported (4.7 stable), promoted from Experimental 2026-07-14 once
  the §7 mobile promotion bar went green. Android is device-validated across
  four models (Pixel 7, Moto g 5G 2023, Galaxy S10+, Pixel 3 XL): debug
  validated to Android 9, but **release builds require Android 13+**. iOS is
  device-validated on iPhone 12 and iPhone 15 Pro. Carried caveats: packaged
  mobile addons are runtime-only (compiling project scripts needs the Kanama
  checkout), the Android release path depends on the JitPack PanamaPort fork,
  and there is no mobile hot reload. See
  `docs/reference/version-support.md` for the full claims and validation dates.
- Release artifacts use two shapes: desktop kits for new projects and store
  add-ons for existing projects. See `docs/exporting/desktop.md`.

Always confirm this section against `README.md`,
`docs/reference/version-support.md`, and `CHANGELOG.md` before changing release
wording.

## First Stop for Implementation Work

If you're working on a specific roadmap task, read the matching file in the
sibling `kanama-tasks/` checkout first (it sits beside this repo like
`kanama-demos/`; `scripts/audit_stale_blockers.py` resolves it the same way, or
through `KANAMA_TASKS_DIR`). Each task is standalone and points to the smallest
doc set and exact files to edit. The index is `kanama-tasks/README.md`. For
ad-hoc work not covered by a task file, use "Read First By Task" below.

## Read First By Task

Avoid broad context scans. Start with the smallest doc set that matches the
task, then use targeted `rg` searches.

- Project status, requirements, and user quick start: `README.md`,
  `docs/getting-started/index.md`, and
  `docs/reference/version-support.md`.
- Contributing to runtime, wrappers, generator, Android, docs, or packaging:
  `CONTRIBUTING.md` and `docs/contributing/index.md`.
- Bootstrap, FFI, script lifetime, ClassDB registration, script resources, or
  hot reload: `docs/contributing/architecture.md`.
- Generated wrappers, generator policy, ABI helpers, KDoc sync, or wrapper
  coverage: `docs/contributing/wrapper-maintenance.md`.
- Android runtime/export support: `docs/exporting/android.md` and
  `docs/contributing/backends/android.md`.
- New Kanama game projects: `docs/getting-started/source-checkout.md`,
  `docs/game-dev/scripts.md`, `docs/game-dev/godot-api.md`,
  `docs/game-dev/properties-resources.md`, and `docs/game-dev/signals.md`.
- Porting GDScript or working on gameplay demos:
  `docs/game-dev/porting-gdscript.md`,
  `docs/contributing/demo-porting-rules.md`, and the companion demo repo
  guide if present.

## Repository Map

- `src/commonMain/kotlin`: the shared generated Godot API wrapper tree, compiled
  as-is by the root JVM module, `:ios-runtime` and the Android plugin (see
  `docs/contributing/wrapper-maintenance.md`). Laid out as a KMP `commonMain` so
  the root can become multiplatform later without moving files again.
- `src/main/kotlin`: Kanama runtime, bootstrap-facing Kotlin code, the
  hand-shaped and desktop-only wrappers with their `<Class>.jvm.kt` companions,
  value types, and editor/runtime support.
- `src/generated/godot`: generated Panama bindings from Godot headers
  (gitignored; produced by `scripts/refresh_godot_api.sh`).
- `bootstrap`: the native C GDExtension bootstrap shared by desktop and Android.
- `gdextension`: the pinned `gdextension_interface.h` input.
- `kanama-common-api`: the Web call contract (opcodes, shapes,
  `InitialGodotCallDescriptors`) derived from `extension_api.json`; consumed by
  `web-runtime` and the processor's Web emitter. Native backends do not use it.
- `android`: the Godot Android plugin (runtime + scripts AARs) and the
  PanamaPort source remap.
- `ios` and `ios-runtime`: the iOS C shim and the Kotlin/Native runtime with
  its per-platform wrappers (hand-shaped, iOS-only generated, collision classes,
  `<Class>.ios.kt` companions); the shared tree comes from `src/commonMain`.
- `web-runtime`: the Kotlin/Wasm Web backend, the Web API wrappers (one
  generated file per Godot class under `api/generated/`, plus the hand-shaped
  facades beside them), generated proxies, JS bridge, and export tasks.
- `annotations`: public KSP annotation definitions for game scripts.
- `processor`: KSP processor that emits script registrars and metadata.
- `project-scripts`: Gradle support for compiling consumer project scripts.
- `templates/starter` and `templates/starter_project`: source-checkout
  onboarding templates.
- `templates/release-kit` and `templates/store-addon`: packaged artifact
  templates.
- `example_project`: local smoke-test Godot project.
- `scripts`: CI, smoke, audit, packaging, and generator helper scripts.
- `docs`: public MkDocs documentation.

## Sibling Repositories

| Repo | Audience | Contents |
|---|---|---|
| `kanama/` (this repo) | public | Runtime, wrappers, generator, docs. |
| `kanama-demos/` | public | 11 public demos: Bunnymark, Match3, 3D-Platformer, dodge, squash, character-controller, Racing, FPS, third-person, tps-demo-kanama, and City-Builder. |

Maintainers may keep additional private checkouts beside these two. Nothing
from them belongs in tracked files: no paths, device or signing identifiers,
demo names, or copied notes.

## Generated vs Hand-Authored Files

| File | Generated by | Notes |
|---|---|---|
| `src/main/kotlin/binding/runtime/ObjectCalls.kt` | hand-written (desktop/Android) | The ptrcall helper surface the generated wrappers call. Not generated: `scripts/audit_generator_shape_policy.py` gates its shapes against the generator's call-shape table, and the iOS counterpart `ios-runtime/.../binding/runtime/ObjectCallsGenerated.kt` is the generated one. |
| The generated wrapper tree: `src/commonMain/kotlin/.../api/*.kt` (shared), `src/main/kotlin/.../api/<Class>.jvm.kt` companions and the desktop-only classes, `ios-runtime/.../api/<Class>.ios.kt` companions and the iOS-only classes, `docs/reference/generated/ios-shape-gap.md` | `scripts/generate_api_wrapper.py --write-tree` (then `sync_kdoc_from_godot_docs.py --write`) | Public wrappers must be regeneratable unless listed as hand-shaped in `PER_PLATFORM_WRAPPERS`; `check_wrapper_generator.py` fails on any hand edit. |
| `src/generated/godot/*` | `scripts/refresh_godot_api.sh` from Godot headers | Generated Panama bindings. |
| `web-runtime/src/commonMain/.../api/generated/*.kt` | `scripts/generate_web_wrappers.py` | The Web API wrappers, one class per file, from `scripts/platform_backend_calls.json` + `extension_api.json`. `--check` (in `local_ci.sh` and `:web-runtime:check`) fails on drift and on any hand-written dispatch outside the `WEB_HANDSHAPED` facades. Hand-shaped members live in the generator's `CLASS_POLICY`, not in the output. |
| `web-runtime/src/wasmJsMain/.../WebCommonGodotBackend.generated.kt` | `scripts/generate_web_backend.py` | The Web backend opcode dispatch from the same contract. |
| `docs/reference/generated/ios-backend-handwritten.md` | `scripts/ios_handwritten_report.py` | Generated report; do not hand-edit. |

Hand-authored policy classes are the exception and are explicitly marked. Prefer
generated wrappers and focused policy fixes over ad hoc hand wrappers.

## Repository Rules

- Public package namespace is `net.multigesture.kanama`.
- Do not reintroduce local workstation paths, private maintainer notes, raw
  task logs, or obsolete project names into tracked files.
- Keep running task lists, dev logs, bundles, raw logs, and private handoff
  notes outside the public repo.
- Keep Gradle coordinates, docs snippets, badges, changelog headings, package
  workflow variables, and companion demo defaults synchronized with the active
  release pass.
- Some invariants are pinned in more than one file; changing one and not the
  others is silent drift. Changing the example script's exported-property set
  means updating the count in **both** `scripts/runtime_smoke.sh`
  (`properties size = N`) and `scripts/local_ci.sh` (`propertyCount = N`).
  Adding a field to the serialized script model (`ScriptModelJson`) means bumping
  `SCRIPT_MODEL_SCHEMA_VERSION`. Before finishing, grep the repo for the old
  value of any pinned constant you touched.
- Claims drift the same way constants do, and nothing notices. **When you close
  a task that removes a limitation, grep for the comments citing it** and delete
  the ones that are now false. Task 30 gave iOS full wrapper breadth and nobody
  grepped for "missing iOS wrapper types"; that line steered decisions for six
  more weeks. Where the limitation is machine-checkable, write it as a
  `KANAMA-BLOCKED` marker instead — `scripts/audit_stale_blockers.py` fails
  the build when the blocker is lifted. See "Documented Limitations" in
  `CONTRIBUTING.md`.
- Do not widen ABI-sensitive types to make wrappers generate. Add exact helper
  shapes and audits first.
- Prefer generated wrappers and focused policy fixes over ad hoc hand wrappers.
- Do not claim platform support unless the matching smoke path has passed.
- Do not treat package zips as proof of exported-game support; desktop kits
  validate editor/runtime onboarding.
- In GDExtension callbacks, never pre-empt work the engine performs after the
  callback returns — path assignment, ResourceCache registration, ownership
  transfer. Precedent: issue #106, where the `.kt` loader pre-set the script
  path inside `_load`, the engine's registering `set_path()` early-returned,
  and every load minted a distinct Script ("cannot assign X to X").
- When adding an engine-facing surface (Script/ScriptLanguage virtual, property
  metadata field, loader behavior), pair the Kanama fixture with the equivalent
  GDScript construct in a smoke parity probe (the
  `_kanama_classdb_script_class_smoke` / `_kanama_typed_global_class_smoke`
  pattern), and never leave a virtual returning a constant without a comment
  arguing why the constant is correct.

## Looks Wrong But Isn't

| Item | Why it's correct | Do not change |
|---|---|---|
| `REFCOUNTED_REFERENCE_HASH == REFCOUNTED_UNREFERENCE_HASH` (both `2240911060`) in `ScriptBridge.kt` | `reference()` and `unreference()` share the `bool()` signature, so their method hashes match by construction. | Verified legitimate against `extension_api.json` (architecture-review verdict; the review is archived in the internal task repo). |
| `compatibility_minimum = "4.7"` in `.gdextension` descriptors | `classdb_register_extension_class6` is a Godot 4.7 API; 4.3-4.6 loads would fail at class registration. | Keep the 4.7 invariant from architecture review F1. |
| `<Class>.jvm.kt` companions repeat a class's desktop-only members as extension functions | iOS has no audited `ObjectCalls` helper for those ptrcall shapes yet; the shared file holds the members both platforms can call and the companion header names what it waits on. | Do not move them back by hand: the regen does when the iOS helper lands. `--emit-class` regenerates the whole tree and writes one class, so the old single-class emit-union gotcha is gone. |
| PanamaPort textual remap from `java.lang.foreign.*` to `com.v7878.foreign.*` | It is a pragmatic Android ART compatibility path guarded by pre/post audits. | Do not clean it up without replacing the audit mitigation; see the architecture review Android section. |

## Common Workflows

### New Kanama Game Project

Use the source-checkout path for current `main` or unreleased changes:

```sh
./gradlew createStarterProject \
  -PkanamaStarterProjectDir=/absolute/path/to/kanama-starter
./gradlew installAddonJar \
  -PkanamaProjectDir=/absolute/path/to/kanama-starter \
  -PkanamaProjectScriptsDir=/absolute/path/to/kanama-starter
```

For an existing project, use `installStarterTemplate` instead of copying a full
starter project over it:

```sh
./gradlew installStarterTemplate \
  -PkanamaStarterProjectDir=/absolute/path/to/godot_project
./gradlew installAddonJar \
  -PkanamaProjectDir=/absolute/path/to/godot_project \
  -PkanamaProjectScriptsDir=/absolute/path/to/godot_project
```

Kotlin scripts belong in `kotlin-src/`, attach with `@ScriptClass`, and are
compiled through the Godot **Build Scripts** button or `buildScripts`.

### Package Artifacts

Build the host desktop kit and local store add-on:

```sh
./gradlew packageDistributions
```

Validate package install flows without a sibling Kanama checkout:

```sh
scripts/package_install_smoke.sh \
  --desktop-kit \
  build/distributions/kanama-desktop-kit-v<version>-<platform>.zip \
  /absolute/path/to/godot-4.7.2-stable
scripts/package_install_smoke.sh \
  --store-addon \
  build/distributions/kanama-store-addon-v<version>.zip \
  /absolute/path/to/godot-4.7.2-stable
```

Desktop kits are complete starter projects for one platform. Store add-ons are
install-safe `addons/` zips for existing projects.

### Port Existing GDScript

Follow `docs/game-dev/porting-gdscript.md` and
`docs/contributing/demo-porting-rules.md`. Preserve scene names, exported
property names, signal names, node paths, upstream gameplay semantics, and
license/attribution notes. Prefer fixing missing Kanama wrapper/runtime support
in this repo over hiding framework gaps in demo code.

### Wrapper Work

Use `scripts/generate_api_wrapper.py`,
`scripts/check_wrapper_generator.py`, wrapper audits, and the coverage pages
under `docs/contributing/`. Public wrappers must be regeneratable unless they are
explicitly hand-authored policy classes.

Re-adopt the generated wrapper tree after a generator change (`--emit-class <Class>
--allow-overwrite` for one class):

```sh
python3 scripts/generate_api_wrapper.py --write-tree
python3 scripts/sync_kdoc_from_godot_docs.py --godot-docs /path/to/godot/doc/classes --write
```

Regenerate name constants after API refreshes:

```sh
python3 scripts/generate_name_constants.py
```

Refresh wrapper reports before release-facing wrapper changes:

```sh
python3 scripts/api_wrapper_generator_report.py --markdown docs/reference/generated/wrapper-generator-report.md
python3 scripts/api_wrapper_coverage.py --markdown docs/reference/generated/api-coverage.md
```

### Godot Upgrade

Follow `docs/contributing/godot-upgrade.md` (the authoritative runbook).
Kanama re-pins only on stable Godot releases. Edit `kanamaGodotVersion` in
`gradle.properties` (plus the CI `GODOT_VERSION` dash form), then run the
mechanical pipeline:

```sh
scripts/upgrade_godot.sh /absolute/path/to/new_godot_binary
```

It refreshes API/header inputs and Panama bindings, re-adopts all generated
wrappers, re-syncs KDoc, refreshes reports, and re-runs the gates — then stops
at the human-judgment boundary (device gates, support claims, release wording).
Run local CI and demo smoke checks before changing support claims.

**Every stable bump: scan for newly deprecated GDExtension functions and keep the
backends converged.** 4.7 is the baseline — we do not carry deprecated GDExtension
callbacks once a newer variant solves the same problem. Diff
`gdextension/gdextension_interface.h` for `@deprecated` entries, migrate every
backend to the newest variant, and confirm the **desktop/Android JVM** backend
(`src/main`) and the **iOS Kotlin/Native** shim (`ios/bootstrap/kanama_ios_shim.c`)
bind the **same** construct/register/script-instance entry points — they pick these
independently and have drifted silently before (`construct_object2` vs
`construct_object3`, issue #91 / task 61). Enforced by
`scripts/check_gdextension_modernization.py` (in `local_ci.sh` and the upgrade
gates); steps are in the upgrade runbook.

### Android Work

Android is Supported (4.7 stable), but never widen a support claim past what
the matching APK/emulator or device smoke path has actually validated — the
release floor (Android 13+) and the runtime-only addon caveat both still hold.
Use the Gradle AAR workflow documented in `docs/exporting/android.md`; do not
infer Android support from desktop package success.

## Validation

Run the narrowest useful check while iterating, then use the broader gate before
publishing or rewriting history:

```sh
python3 scripts/validate_godot_api.py --api extension_api.json
python3 scripts/check_wrapper_generator.py
mkdocs build --strict
./gradlew ktfmtFormat            # format hand-written Kotlin (ktfmtCheck gates CI)
./gradlew jar
./scripts/local_ci.sh /absolute/path/to/godot-4.7.2-stable
```

Hand-written Kotlin is formatted with ktfmt (googleStyle, 2-space) via the root
`build.gradle.kts`; `local_ci.sh` runs `ktfmtCheck` as a stage. The generated
`**/net/multigesture/kanama/api/**` wrappers and `*.gradle.kts` scripts are
excluded — see the Formatting section in `CONTRIBUTING.md`.

`local_ci.sh` is the definition of done for processor, runtime, and
script-model changes: it runs the static registrar/count/generated-code checks
that the in-editor runtime smoke does not exercise, so a passing runtime smoke
alone is not sufficient. It also compiles the generated code for every exported
property shape, so run it after widening any accepted `@ScriptProperty` type.

CI coverage is **build-time + desktop runtime only**. The PR gate compiles
desktop and runs the desktop/Linux runtime smokes — which also exercise the
runtime Android shares via PanamaPort. The Android runtime AAR and iOS
xcframework lanes run on push to `main` and on PRs that touch runtime,
processor, or mobile paths (docs-only PRs skip them; PRs build the debug device
xcframework only). It does **not** run on-device: the Android R8-minified runtime
gate (the task-42 PanamaPort-under-R8 class) and the iOS device runtime are
deliberate **local device gates**. A green PR means it builds everywhere and the
desktop runtime behaves — not that it is device-safe on mobile.

Before a release tag, prefer an isolated clone gate:

```sh
./scripts/fresh_clone_smoke.sh /absolute/path/to/godot-4.7.2-stable
```

The Web analog is `scripts/web_fresh_checkout_smoke.sh`: it exports from a clean
clone in an isolated workspace, proves no build-machine path reaches a served
file and that the demo source tree is untouched, then drives the artifact in a
browser with the harness from that clone. See `docs/exporting/web.md`.

Use demo-repo smoke tasks when a change affects real gameplay ports, wrappers
used by demos, package onboarding, or Android exports.
