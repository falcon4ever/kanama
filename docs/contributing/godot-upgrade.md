# Godot Upgrade Runbook

This page is the authoritative process for bumping Kanama's Godot engine
baseline. It is maintainer material; game projects never need it. The
mechanical steps are automated by `scripts/upgrade_godot.sh` — this page
explains the order, what to expect, and the judgment calls the script
deliberately leaves to a human.

## Upgrade Policy

Kanama re-pins **only on stable Godot releases**. Dev, beta, and rc builds are
never adopted as the baseline; `scripts/upgrade_godot.sh` refuses a non-stable
pin. When a new stable ships, the bump is planned work, not an automatic
follow — wrapper-coverage pushes in flight should ideally land first.

The baseline has exactly one source of truth: `kanamaGodotVersion` in
`gradle.properties` (dot form, e.g. `4.8.stable`). The CI packaging workflow
carries the same version in dash form (`GODOT_VERSION: 4.8-stable` in
`.github/workflows/package.yml`, the GitHub release tag). Everything else
reads the property; `scripts/check_godot_version_pin.py` fails the build if
any pin drifts.

## What a Bump Regenerates

| Input / output | Refreshed by |
|---|---|
| `extension_api.json`, `gdextension/gdextension_interface.h` | `scripts/refresh_godot_api.sh` (dumped from the new binary) |
| `src/generated/godot` (Panama bindings) | `scripts/jextract.sh` (jextract 25+) |
| Virtual-method signature table (`processor/.../virtual-signatures.tsv`) | `scripts/generate_virtual_signature_table.py` |
| Engine-wide `MethodName`/`PropertyName`/`SignalName` constants | `scripts/generate_name_constants.py` |
| Generated wrappers, all platform trees (desktop/Android via one tree, iOS island + `ObjectCallsGenerated.kt`) | `scripts/generate_api_wrapper.py` re-adopt |
| Wrapper KDoc | `scripts/sync_kdoc_from_godot_docs.py --write` |
| Coverage + generator reports | `scripts/api_wrapper_coverage.py`, `scripts/api_wrapper_generator_report.py` |

## Prerequisites

- The new **stable** Godot editor binary for the host platform.
- `JEXTRACT_HOME` pointing at a jextract 25+ install.
- A Godot **source checkout at the matching stable tag** — KDoc syncs from its
  `doc/classes`. Never sync from an rc/`latest` tree; the script verifies the
  checkout's `version.py` against the pin.
- A clean working tree (the bump diff must stay reviewable).

## The Runbook

1. **Edit the pin — and only the pin.** Change `kanamaGodotVersion` in
   `gradle.properties` and `GODOT_VERSION` in `.github/workflows/package.yml`
   (dash form). No other hand edits; scattered version literals are what the
   pin check guards against.

2. **Run the mechanical pipeline:**

   ```sh
   JEXTRACT_HOME=/absolute/path/to/jextract-25 \
   scripts/upgrade_godot.sh \
     --godot-docs /absolute/path/to/godot-source/doc/classes \
     /absolute/path/to/new_godot_binary
   ```

   In order, the script: verifies pin/binary/policy/tooling (fail fast),
   refreshes the API/header inputs (dump + jextract + validation + virtual
   table), classifies the API diff, regenerates name constants, **re-adopts
   every committed generated wrapper on every platform** (desktop
   `--emit-class`, iOS `--ios-emit-class` plus `ObjectCallsGenerated.kt`),
   re-syncs KDoc, refreshes the coverage/generator reports, and re-runs the
   gates. It stops at the human-judgment boundary below.

3. **Read the classification.** The script diffs the new `extension_api.json`
   against `HEAD` and reports one of:

    - **Metadata-only** (only the `header` changed, like a rc→stable re-dump):
      expect churn limited to version strings; the rest of the bump is
      release-facing wording.
    - **Real API change**: added/removed/changed classes are listed. Expect
      wrapper regen churn. **New classes are not auto-adopted** — they are
      coverage/policy work (generator policy, ABI audits, then adoption).
      Wrappers whose class was **removed** from the API are flagged for a
      human decision. A new engine call shape that the generator refuses is a
      helper/audit task first — do not widen ABI-sensitive types to force a
      wrapper to generate.

4. **Human-judgment steps** (the script prints this list and exits; it never
   performs them):

    - Run local CI against the new binary:
      `scripts/local_ci.sh /absolute/path/to/new_godot_binary`.
    - Re-run the platform smoke/device gates per the release-gate matrix (§6) in
      the internal task repo. **Support claims move only after the matching gate
      passes** — the upgrade tooling never edits support wording.
    - Refresh release-facing wording by hand: `CHANGELOG.md`, `README.md`,
      docs badges/version wording, templates, and companion demo defaults.
      The `.gdextension` `compatibility_minimum` is an invariant with its own
      rationale (see `AGENTS.md` "Looks Wrong But Isn't") — review it
      deliberately before touching it.
    - Bump the KDoc docs-pin commit hash in
      `docs/contributing/wrapper-maintenance.md` to the new stable tag.
    - **Scan `gdextension/gdextension_interface.h` for newly `@deprecated`
      functions and migrate off them.** 4.7 is the baseline — we don't carry
      deprecated GDExtension callbacks. `grep -B1 '@deprecated'
      gdextension/gdextension_interface.h | grep '@name'` lists them; cross-check
      each against the FFI lookups every backend binds (`rg
      'classdb_construct_object|script_instance_create|register_extension_class|get_godot_version'
      src ios --glob '*.kt' --glob '*.c'`) and move to the newest variant.
    - **Assert the backends have not diverged.** The desktop/Android JVM backend
      (`src/main`) and the iOS Kotlin/Native shim (`ios/bootstrap/kanama_ios_shim.c`)
      choose their own GDExtension entry points and *have* silently drifted before
      (desktop `construct_object2` vs iOS `construct_object3` — issue #91 / task 61).
      Confirm both bind the **same, newest** construct/register/script-instance
      functions. Enforced by `scripts/check_gdextension_modernization.py` (in the
      gates below and in `local_ci.sh`) — it fails on any deprecated binding or a
      backend divergence.
    - Before tagging:
      `scripts/fresh_clone_smoke.sh /absolute/path/to/new_godot_binary`.

## Gates — Never Finish a Bump With Any of These Red

The drift-gate is the safety net: a botched bump leaves wrappers drifted, the
gate catches it, and the fix is to re-adopt — mistakes are recoverable, but an
upgrade is never "done" over a red gate.

```sh
python3 scripts/check_wrapper_generator.py        # full per-platform drift-gate
python3 scripts/check_gdextension_modernization.py # no deprecated bindings; backends converged
python3 scripts/sync_kdoc_from_godot_docs.py --godot-docs <doc/classes> --check
python3 scripts/check_godot_version_pin.py
python3 scripts/validate_godot_api.py
mkdocs build --strict
./scripts/local_ci.sh /absolute/path/to/new_godot_binary
```

A Godot bump also **invalidates the Web evidence**: the export template, the
generated proxy and the bridge protocol all move with it, and CI's Web matrix
only covers Chrome and Firefox. Re-run the full corpus on all three browsers,
including the Safari local gate, and re-run the fresh-checkout gate against a
clone built on the new pin — see
[Regression cadence](../exporting/web.md#regression-cadence) for exactly which
gate runs when.

## Dry Run

The pipeline can be exercised against the **current** pin as a no-op bump:

```sh
scripts/upgrade_godot.sh --dry-run /absolute/path/to/current_godot_binary
```

Dry-run mode re-dumps and re-validates the inputs, requires the API diff to
classify as a no-op, runs every remaining step in check-only mode (name
constants, reports, drift-gate, KDoc, version pin), and fails unless the git
tree is byte-identical afterwards. Re-run it after toolchain changes (new
jextract, generator refactors) so the next real bump starts from a proven
pipeline.
