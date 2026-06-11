# Changelog

All notable user-facing changes will be recorded here.

This project uses a Keep a Changelog-style format and follows semantic
versioning once public releases begin.

## Unreleased

### Added

- Added an experimental iOS Kotlin/Native backend that runs full Kanama project
  scripts: a C GDExtension shim plus GENERATED Godot API wrappers (the same
  wrapper generator as desktop/Android) over a C-shim generic `ptrcall`. Match3
  and the Kenney 3D platformer are device-validated end to end (live input,
  signals, animation, scene reload); per-frame Kanama binding overhead measured
  ~0.63 ms on iPhone 12. iOS remains experimental, not a supported export — see
  `docs/internals/ios-backend-roadmap.md` for the gaps.
- Added an iOS hand-written/stub registry: `// KANAMA-IOS-{STUB,HANDWRITTEN,SUGAR}`
  markers, `scripts/ios_handwritten_report.py` (generates
  `docs/internals/ios-backend-handwritten.md`), and `scripts/check_ios_no_silent_stubs.py`
  (fails CI on an un-annotated silent stub). The iOS script parser now warns when a demo
  uses a Kanama annotation that iOS does not wire (e.g. `@OnUnhandledInput`, `@Rpc`).
- Added iOS physical-device validation tooling: a visual smoke script with an
  optional Kotlin/Native frame probe that updates a Godot `Label` through a
  cached typed `ptrcall`. Simulator checks remain available for compile/link
  debugging only.
- Made the iOS install path build device-only xcframeworks by default, with an
  explicit `kanamaIosXcframeworkMode=full` escape hatch for simulator work.

### Changed

- Updated the build toolchain to Kotlin 2.3.21, KSP 2.3.9, and
  kotlinx.coroutines 1.11.0, with Gradle build cache enabled for the main and
  Android plugin builds.
- Enabled Kotlin Multiplatform cinterop commonization for the experimental
  iOS runtime.
- `installIosAddon` now preserves a project's Android (and desktop)
  `kanama.gdextension` library entries instead of overwriting them, so installing
  the iOS addon no longer regresses Android support (it mirrors `installAddonJar`'s
  Android-metadata preservation and asserts the entries survive).

## 0.2.2 - 2026-06-05

### Changed

- Updated the Kanama preview baseline to Godot 4.7 beta 5.
- Refreshed `extension_api.json` and generated Godot API name constants for the
  beta 5 version metadata; the beta 5 API/header body matches the beta 4 input
  used by the previous preview.
- Updated Gradle coordinates, release packaging defaults, documentation, and
  demo project defaults for Kanama `0.2.2`.

### Known Limitations

- Android remains experimental. Godot 4.7 beta 5 Android APK smoke validation is
  pending before changing Android support claims.

## 0.2.1 - 2026-05-26

### Added

- Fresh-clone smoke validation for release checks and clean source checkouts.
- A ready-to-run starter project creation task for first-time Kanama projects.

### Changed

- First-run documentation now starts from the new starter project flow and
  source-install validation path.
- Clean Gradle environments now get explicit JVM memory defaults.

### Fixed

- Local CI now creates the generated Godot GDExtension header when it is missing
  from a fresh checkout.
- Maven local validation now publishes/checks the current Kanama version in the
  effective Maven local repository.

## 0.2.0 - 2026-05-26

### Added

- Godot 4.7 beta 4 API baseline updates for generated wrappers, docs, and
  smoke validation.
- Editor workflow helpers for opening Kotlin sources and common build actions
  from the Kanama Tools dock.
- Basic Kotlin syntax highlighting in the Kanama Tools editor integration.
- Additional export inspector metadata support, including property hints,
  categories, groups, subgroups, and inspector tool buttons.
- Typed signal helper overloads for common connect, emit, and await usage while
  keeping the existing string-based signal APIs available.
- Convenience editor-time script helpers for `@Tool` scripts, including editor
  hint checks and inspector/property-list refresh.
- Generated engine-wide `MethodName`, `PropertyName`, and `SignalName`
  constants for type-safe Godot API name references.
- Multiplayer and porting guardrail audits for risky runtime node lookups, raw
  string dispatch, and `SceneReplicationConfig` custom properties.
- Multiplayer docs covering generated RPC helpers, ENet peer setup, replicated
  script properties, and review checklist items for ports.

### Changed

- Script/runtime hot paths are leaner, including vector math, object-array
  decoding, string-name handling, and common object call paths.
- Runtime diagnostics now report more lifecycle and script binding context when
  debugging editor/runtime integration failures.
- Example project registries are separated from runtime registration paths so
  local examples do not hide integration drift in external projects.
- Runtime and local CI smoke checks now assert the QoL metadata, tool button,
  and generated name-constant coverage.
- The replicated-script-property audit can now check multiple project roots in
  one run, so demo aggregate checks can use the same guardrail script as local
  CI.

### Fixed

- Script object lifecycle and property replay now preserve inspector-authored
  values more reliably across load/reload paths.
- Retained resource wrapper lifetime handling no longer drops resource handles
  too early in common script property flows.
- Custom resource script properties now release only the retained custom
  resource handle during parent cleanup. They no longer recursively clean the
  child script's exported resource properties, which could close shared
  resources such as `PackedScene` models too early.
- Getting-started, README, local docs preview, and release-validation examples
  were corrected for the current source-first workflow.

## 0.1.0 - 2026-05-19

### Added

- Desktop Kotlin script runtime for Godot through GDExtension and the JDK
  Foreign Function & Memory API.
- KSP-based script registration for lifecycle callbacks, exported properties,
  signals, global classes, and editor tool scripts.
- Hot reload support for desktop editor workflows.
- Generated Godot API wrappers with reproducibility checks and ABI policy
  audits.
- Promoted Kotlin wrapper classes for the Godot 4.7 beta 4 API baseline, with
  conservative method coverage documented in the wrapper coverage report.
- Generated KDoc carried from Godot API documentation for wrapper classes and
  methods.
- Starter project template and example smoke-test project.
- MkDocs documentation covering setup, API usage, distribution, wrapper
  coverage, and maintainer internals.
- Companion demo projects used as integration coverage for real gameplay code.
- Experimental Android export workflow through a Godot Android plugin AAR,
  Android ART, and PanamaPort, smoke-tested on emulator/Pixel 7 paths with
  eight public demo targets.

### Changed

- Wrapper generation is now an active source-promotion path instead of a report-only
  experiment.
- Public documentation has been organized around getting started, porting,
  manual pages, reference coverage, and internals.

### Known Limitations

- Kanama is desktop-first. macOS arm64 is the active development and smoke-test
  platform; Linux and Windows have runtime/demo smoke coverage with remaining
  automated editor/tool shutdown caveats documented in Version Support.
- Android exports are experimental. The current path includes Android smoke
  targets with selected touch overlays, D-pad controls, virtual joysticks, and
  demo warmup fixes for first-use hitches, but Vulkan/Mobile renderer coverage,
  hot reload, and complete phone-specific UI polish are not release claims yet.
- iOS is not supported. Web export is not planned.
- Broad `Callable`, `Dictionary`, generic container, virtual override, and
  ownership-sensitive APIs remain conservative policy buckets.
