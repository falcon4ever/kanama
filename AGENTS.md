# Agent Guide

This file is the short operational map for coding agents and maintainers.
Public user documentation lives in `docs/`; keep this file focused on how to
work in the repository without scanning the whole tree or breaking Kanama's
release shape.

## Current Baseline

- Kanama preview line: `0.2.2`.
- Godot baseline: Godot `4.7 rc 2`.
- Desktop runtime/build JDK: JDK `25+`.
- Android remains experimental; Godot `4.7 rc 2` Android APK smoke
  revalidation is still a support gate.
- Release artifacts use two shapes: desktop kits for new projects and store
  add-ons for existing projects. See `docs/exporting/desktop.md`.

Always confirm this section against `README.md`,
`docs/reference/version-support.md`, and `CHANGELOG.md` before changing release
wording.

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
  `docs/contributing/android-internals.md`.
- New Kanama game projects: `docs/getting-started/source-checkout.md`,
  `docs/game-dev/scripts.md`, `docs/game-dev/godot-api.md`,
  `docs/game-dev/properties-resources.md`, and `docs/game-dev/signals.md`.
- Porting GDScript or working on gameplay demos:
  `docs/game-dev/porting-gdscript.md`,
  `docs/contributing/demo-porting-rules.md`, and the companion demo repo
  guide if present.

## Repository Map

- `src/main/kotlin`: Kanama runtime, bootstrap-facing Kotlin code, wrappers,
  value types, and editor/runtime support.
- `src/generated/godot`: generated Panama bindings from Godot headers.
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

## Repository Rules

- Public package namespace is `net.multigesture.kanama`.
- Do not reintroduce local workstation paths, private maintainer notes, raw
  task logs, or obsolete project names into tracked files.
- Keep running task lists, dev logs, bundles, raw logs, and private handoff
  notes outside the public repo.
- Keep Gradle coordinates, docs snippets, badges, changelog headings, package
  workflow variables, and companion demo defaults synchronized with the active
  release pass.
- Do not widen ABI-sensitive types to make wrappers generate. Add exact helper
  shapes and audits first.
- Prefer generated wrappers and focused policy fixes over ad hoc hand wrappers.
- Do not claim platform support unless the matching smoke path has passed.
- Do not treat package zips as proof of exported-game support; desktop kits
  validate editor/runtime onboarding.

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
  /absolute/path/to/godot-4.7-rc2
scripts/package_install_smoke.sh \
  --store-addon \
  build/distributions/kanama-store-addon-v<version>.zip \
  /absolute/path/to/godot-4.7-rc2
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
under `docs/reference/`. Public wrappers must be regeneratable unless they are
explicitly hand-authored policy classes.

Regenerate name constants after API refreshes:

```sh
python3 scripts/generate_name_constants.py
```

Refresh wrapper reports before release-facing wrapper changes:

```sh
python3 scripts/api_wrapper_generator_report.py --markdown docs/reference/wrapper-generator-report.md
python3 scripts/api_wrapper_coverage.py --markdown docs/reference/api-coverage.md
```

### Godot Upgrade

For a new Godot version, refresh API/header inputs and regenerated Panama
bindings together:

```sh
scripts/refresh_godot_api.sh /absolute/path/to/new_godot_binary
```

Then review and refresh, as needed:

- `extension_api.json`
- `gdextension_interface.h`
- `src/generated/godot`
- generated wrapper source and name constants
- generated KDoc via `scripts/sync_kdoc_from_godot_docs.py`
- wrapper generator and coverage reports
- Gradle version coordinates, package workflow `GODOT_VERSION`, docs badges,
  changelog, templates, and companion demo defaults

Run local CI and demo smoke checks before changing support claims.

### Android Work

Keep Android wording experimental unless the matching APK/emulator or device
smoke path has passed. Use the Gradle AAR workflow documented in
`docs/exporting/android.md`; do not infer Android support from desktop package
success.

## Validation

Run the narrowest useful check while iterating, then use the broader gate before
publishing or rewriting history:

```sh
python3 scripts/validate_godot_api.py --api extension_api.json
python3 scripts/check_wrapper_generator.py
mkdocs build --strict
./gradlew jar
./scripts/local_ci.sh /absolute/path/to/godot-4.7-rc2
```

Before a release tag, prefer an isolated clone gate:

```sh
./scripts/fresh_clone_smoke.sh /absolute/path/to/godot-4.7-rc2
```

Use demo-repo smoke tasks when a change affects real gameplay ports, wrappers
used by demos, package onboarding, or Android exports.
