# Godot Upgrade Runbook

This page is the process for moving Kanama to a new Godot release. It is
maintainer material; game projects never need it. You edit the pin, run one
script, and then do the parts that need a person: source-break notes, device
runs and release wording.

## Upgrade Policy

Kanama re-pins **only on stable Godot releases**. Dev, beta and rc builds are
never the baseline, and `scripts/upgrade_godot.sh` refuses a non-stable pin
unless you ask for a trial (see [Trying a Prerelease](#trying-a-prerelease)).
When a new stable ships, the bump is planned work, not an automatic follow;
wrapper-coverage work in flight should land first.

The baseline has one source of truth: `kanamaGodotVersion` in
`gradle.properties`, in Godot's own spelling (`4.8.stable` for a `.0` release,
`4.7.2.stable` for a maintenance release). The CI workflows carry the same
version in dash form (`GODOT_VERSION: 4.8-stable`, the GitHub release tag) in
`package.yml`, `ci.yml`, `web.yml` and `gate-red-runs-nightly.yml`, and
`web.yml` derives the export-template folder from it. Everything else reads the
property. `scripts/check_godot_version_pin.py` fails the build when a pin
drifts, and `--list-files` prints the files a pin edit touches. Maintenance
releases follow the same runbook; the diff is just smaller.

## What You Need

- The new **stable** Godot editor binary for your machine, and its export
  templates (`.tpz`).
- `JEXTRACT_HOME` pointing at a jextract 25+ install.
- A Godot **source checkout at the matching tag**: KDoc is synced from its
  `doc/classes`, and the script checks the checkout's `version.py` against the
  pin. Never sync from an rc or `master` tree.
- A clean working tree apart from the pin edit, so the bump diff stays
  reviewable.

## The Bump

1. **Edit the pin, and only the pin.** Change `kanamaGodotVersion` in
   `gradle.properties` and `GODOT_VERSION` in the four workflows
   (`python3 scripts/check_godot_version_pin.py --list-files` names them).

2. **Look at the plan** (optional). `--plan` runs the read-only checks and prints
   every step with its command, changing nothing:

   ```sh
   JEXTRACT_HOME=/absolute/path/to/jextract-25 \
   scripts/upgrade_godot.sh --plan \
     --godot-docs /absolute/path/to/godot-source/doc/classes \
     /absolute/path/to/new_godot_binary
   ```

3. **Run the bump** with the same arguments, without `--plan`. Add
   `--templates /path/to/Godot_vX_export_templates.tpz` to install the export
   templates when they are not installed yet. A run takes about two to three
   minutes:

   ```sh
   JEXTRACT_HOME=/absolute/path/to/jextract-25 \
   scripts/upgrade_godot.sh \
     --godot-docs /absolute/path/to/godot-source/doc/classes \
     --templates /absolute/path/to/export_templates.tpz \
     /absolute/path/to/new_godot_binary
   ```

   The script works through these steps in order:

   | Step | What it does |
   |---|---|
   | 1. preflight | checks the pin policy, the binary's version, jextract, the docs checkout's version, the export templates, and that only the pin files are dirty; lists every problem before stopping |
   | 2. refresh | `refresh_godot_api.sh --skip-validate`: dumps `extension_api.json` and `gdextension_interface.h` from the binary, runs jextract, regenerates the virtual-signature table |
   | 3. classify | diffs the new API against `HEAD`: no-op, metadata-only, or an API change with the added and removed classes and the removed methods, properties, signals, constants and enums |
   | 4. constants | `generate_name_constants.py` |
   | 5. tree | `generate_api_wrapper.py --write-tree`: re-renders every wrapper on every platform, **adopts every class the new API adds**, keeps the desktop `ObjectCalls` `actual` set in step with the expect, and repeats until a pass writes nothing |
   | 6. outputs | the builtin operators and methods, the engine-method table, the Web backend, wrappers and exercise, the API exercise, the generator, coverage, enum-migration and iOS-handwritten pages, then the KDoc sync |
   | 7. format | `./gradlew ktfmtFormat` (the regen leaves the iOS `ObjectCalls` region, the expect and toggled desktop members unformatted) |
   | 8. gates index | `docs/reference/generated/gates.md`, last because it reads the gate list |
   | 9. gates | every drift gate, `ktfmtCheck`, and the API validation, run to the end |

   API validation runs in step 9, after the re-adopt. Before step 5 the
   committed wrappers still call the methods the new Godot removed, so a
   removal is a line in the step 3 inventory and a source break for step 4
   below, not a reason to stop.

4. **Read the summary.** A step that fails is listed with its command; nothing
   is hidden behind the first failure. Three checks are listed as **human
   decisions** rather than failures, because an API change is expected to trip
   them:

   - *Public API signatures.* Each removed or changed signature needs a
     `- **Source break:**` line in `CHANGELOG.md` under `## Unreleased` (see the
     top of the CHANGELOG), then
     `python3 scripts/check_public_signature_changes.py --write`.
   - *Adopted-list classes.* `check_wrapper_generator.py` holds a list of
     classes to "no skipped method". When Godot gives one of them a method whose
     call shape has no `ObjectCalls` helper yet, the drift gate prints it as
     pending, with the `PENDING_HELPER_SKIPS` row to paste. Add the helper (see
     [Wrapper Maintenance](wrapper-maintenance.md)), or the row with its reason.
     Do not take the class out of its list.
   - *Gate evidence.* A pin move makes every entry of `evidence/gates.json`
     stale. Re-run each gate on the new pin, or accept it stale with a reason and
     an expiry date.

   The adopted classes are printed by step 5, each with its count of skipped
   methods. They are generated like every other class; review the list, and the
   new rows they get on the API exercise page.

## After the Script

These steps need a person or a device, so the script prints them and stops:

- Run local CI against the new binary:
  `scripts/local_ci.sh /absolute/path/to/new_godot_binary`.
- **Run the API exercise before any device gate.** The page
  (`docs/reference/generated/api-exercise.md`) is already regenerated; review its
  diff, since every new class gets rows that have never run. Then run the rows
  headless with the trace on (`runtime_smoke.sh` does, or
  `KANAMA_API_EXERCISE=1 KANAMA_API_EXERCISE_TRACE=1` on `api_exercise_smoke.tscn`
  with a `-PkanamaApiExercise=true` addon). A row that crashes, hangs, writes
  outside the scratch directory or would change a running game's state on a
  device gets a `METHOD_OVERRIDES` or exclusion entry with that reason in
  `scripts/api_exercise_policy.py`; a row that fails is a finding to fix. Only then
  the iOS and Android device runs, which run the same rows (see "The exercise rows"
  in `wrapper-maintenance.md`).
- When Godot adds a builtin member (a new `Vector2` method, a new operator), the
  Web builtin parity hashes need re-recording from a runtime smoke log:
  `python3 scripts/generate_builtin_ops.py --record-parity <log>`.
- Re-run the platform smoke and device gates per the release-gate matrix (§6) in
  the internal task repo, and record each run in `evidence/gates.json`.
  **Support claims move only after the matching gate passes**; the upgrade
  tooling never edits support wording.
- Re-import the demos with the new editor, commit the `.import` churn in the demos
  repo, and move `DEMOS_REF` in `.github/workflows/web.yml`.
- Refresh release-facing wording by hand: `CHANGELOG.md`, `README.md`, docs
  badges and version wording, templates, and companion demo defaults. The
  `.gdextension` `compatibility_minimum` is an invariant with its own rationale
  (see `AGENTS.md` "Looks Wrong But Isn't"); review it deliberately before
  touching it.
- Bump the KDoc docs-pin commit hash in
  `docs/contributing/wrapper-maintenance.md` to the new stable tag.
- **Scan `gdextension/gdextension_interface.h` for newly `@deprecated`
  functions and migrate off them.** 4.7 is the baseline: we don't carry
  deprecated GDExtension callbacks. `grep -B1 '@deprecated'
  gdextension/gdextension_interface.h | grep '@name'` lists them; cross-check
  each against the FFI lookups every backend binds (`grep -rnE
  'classdb_construct_object|script_instance_create|register_extension_class|get_godot_version'
  src ios --include '*.kt' --include '*.c'`) and move to the newest variant.
- **Check the backends have not diverged.** The desktop/Android JVM backend
  (`src/jvmMain`) and the iOS Kotlin/Native shim (`ios/bootstrap/kanama_ios_shim.c`)
  choose their own GDExtension entry points and *have* silently drifted before
  (desktop `construct_object2` vs iOS `construct_object3`, issue #91 / task 61).
  Both must bind the **same, newest** construct/register/script-instance
  functions. `scripts/check_gdextension_modernization.py` (step 9 and
  `local_ci.sh`) fails on a deprecated binding or a divergence.
- **Re-check the Android template's Kotlin libraries.** An exported APK runs on
  the `kotlin-stdlib` and `kotlinx-coroutines` of Godot's Android build template
  (`kotlinVersion` in `config.gradle` inside the export templates'
  `android_source.zip`, and `./gradlew :dependencies --configuration
  standardDebugRuntimeClasspath` there; 4.7.2: stdlib 2.1.21, coroutines 1.6.4),
  so `android/godot-plugin/{plugin,scripts}/build.gradle.kts` compile with
  `apiVersion` set to that stdlib version and against that coroutines version
  (`kanamaAndroidCoroutinesVersion` in `android/godot-plugin/gradle.properties`).
  Move both with the template. The other direction matters too: Kotlin 2.4
  already warns that API version 2.1 is deprecated, and a later Kotlin (around
  2.6, if the usual cycle holds) refuses it. A root Kotlin bump then fails the
  Android build until Godot's template ships a newer stdlib; keep the older
  Kotlin until it does, rather than raising the API level past the APK's.
- Before tagging:
  `scripts/fresh_clone_smoke.sh /absolute/path/to/new_godot_binary`.

A Godot bump also **invalidates the Web evidence**: the export template, the
generated proxy and the bridge protocol all move with it, and CI's Web matrix
only covers Chrome and Firefox. Re-run the full corpus on all three browsers,
including the Safari local gate, and re-run the fresh-checkout gate against a
clone built on the new pin; see
[Regression Cadence](device-runs.md#regression-cadence) for which gate runs
when.

## Never Finish With a Red Gate

The drift gate is the safety net: a botched bump leaves wrappers drifted, the
gate catches it, and the fix is to re-run the step, so mistakes are
recoverable. But a bump is never "done" over a red gate. Step 9 runs the
generator gates; the full set is local CI:

```sh
./scripts/local_ci.sh /absolute/path/to/new_godot_binary
mkdocs build --strict
```

## Proving the Pipeline: Dry Run

Run the pipeline against the **current** pin as a no-op bump:

```sh
scripts/upgrade_godot.sh --dry-run /absolute/path/to/current_godot_binary
```

The dry run starts from a clean tree, runs every step in write mode, requires
the API diff to be a no-op and every gate (the three human-decision checks
included) to pass, and fails unless the tree is byte-identical afterwards. Run
it after a toolchain or generator change, so the next real bump starts from a
proven pipeline.

## Trying a Prerelease

To see what a beta or rc would break before it ships, run the bump on a
throwaway branch with `--prerelease-trial`. It accepts a pin such as
`4.8.beta1` and a source checkout whose `version.py` says `beta`. Never merge a
prerelease pin.
