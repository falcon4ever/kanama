# Release Support Decision

> **Status: recommendation, awaiting human sign-off.** This is a decision-matrix
> document, not the decision. It synthesizes what the smoke and device gates
> actually proved into a proposed support-tier story and proposed public wording.
> A maintainer signs off, then the wording is applied to the public docs as a
> separate step (post-sign-off, likely task 18 — see
> [Out of scope](#out-of-scope)).

This document answers four questions for the 0.2.x release line:

1. Which platforms are **supported** vs **experimental**, and what does
   "supported" mean?
2. Which device / OS / Godot / JDK matrix must pass before a release tag?
3. Are iOS and Android the **same** support tier or **separate** tiers?
4. What wording should the public docs use?

Every claim below is grounded in a smoke or device gate that actually passed, per
the [AGENTS.md](https://github.com/falcon4ever/kanama/blob/main/AGENTS.md) rule:
*"Do not claim platform support unless the matching smoke path has passed."* Where
a gate only covered one device, the claim names that device — not a device family.

---

## 1. Platform support tiers

### What "supported" means

A platform is **supported** only when all of the following hold:

1. **Smoke path passed** — the matching automated smoke path is green on the
   current Godot baseline (AGENTS.md rule).
2. **Hardware validated** — a physical-device (not emulator/simulator) gate has
   passed. Emulator and simulator runs are compile/link and startup checks, not a
   support signal or a frame-rate signal.
3. **Packaging documented** — the export/packaging flow is user-facing in
   `docs/`, reproducible from a clean checkout without private handoff notes.
4. **Generator honest** — the full drift-gate is green (committed wrappers ==
   fresh regen per platform, `check_full_drift_gate`), there are 0 silent API
   stubs (`check_ios_no_silent_stubs.py`), and the coverage reports are refreshed.
5. **Below-desktop caveats are limitations, not blockers** — any remaining gap is
   documented as a bounded limitation, not an undocumented failure.

**Experimental** means (1)–(4) may hold but the platform is deliberately held
below "supported": packaging is not release-grade, the device matrix is narrow, or
per-platform caveats (renderer, dependency, demo breadth) keep the claim
conservative.

### Recommended tiers (0.2.x line)

| Tier | Platforms | Meaning |
|---|---|---|
| **Tier 1 — Supported** | Desktop (macOS validated; Linux/Windows pending 4.7-stable revalidation) | Primary supported runtime and package target. macOS arm64 meets all "supported" criteria on 4.7 stable. Linux/Windows are Tier 1 targets whose last full validation was on 4.7 **beta 2**; they are "supported pending revalidation" until re-run on 4.7 stable (a release-gate item, §6). |
| **Tier 2 — Experimental (device-validated)** | iOS **and** Android | Documented export path + a passing physical-device smoke gate + the Android-enabled public demo set passing on device. Explicitly **not** desktop-level support. Per-platform caveats in §5. |
| **Not planned** | Web | Kanama depends on a JVM/FFM-style runtime path. |

**Recommendation:** keep Tier 2 labelled **experimental**, not "supported with
caveats". The mobile backends have crossed the *demo-parity* bar but not the
*release-grade packaging + broad device matrix* bar that "supported" implies here.
Promotion to a supported mobile tier is a future decision, gated on §6.

---

## 2. Required device / OS / Godot / JDK matrix

The matrix below is what a release tag must have green. Device **models** only —
UDIDs, signing team IDs, and provisioning profile names stay in private maintainer
notes per AGENTS.md.

### Engine + toolchain (all platforms)

| Item | Pin | Source |
|---|---|---|
| Godot | **4.7 stable** | `kanamaGodotVersion=4.7.stable` in `gradle.properties` (single-source pin, task 23; checked by `scripts/check_godot_version_pin.py`) |
| Desktop build/runtime JDK | **JDK 25+** | `jvmToolchain(25)` in `build.gradle.kts`; JDK 25 per [Version Support](../reference/version-support.md) |
| Android Gradle/export tooling JDK | **JDK 21** | [exporting/android.md](../exporting/android.md) toolchain table |
| Android game runtime | **ART + PanamaPort** (no JDK embedded) | [exporting/android.md](../exporting/android.md) |
| iOS runtime | **Kotlin/Native** static `.xcframework` (no JVM) | [exporting/ios.md](../exporting/ios.md) |
| Kotlin / KSP | **2.3.21 / 2.3.9** | [Version Support](../reference/version-support.md) |

### Per-platform validation matrix

| Platform | Device / environment | OS | Renderer | Result proven | Evidence |
|---|---|---|---|---|---|
| macOS (desktop) | Apple Silicon arm64 | — | project renderer / OpenGL Compat (demo smokes) | `runtime_smoke.sh` green vs 4.7 stable `4.7.stable.official.5b4e0cb0f` (2026-06-21) | [Version Support](../reference/version-support.md) |
| Linux (desktop) | arm64 + x86_64 | — | project renderer | **Pending 4.7-stable revalidation** (last green on 4.7 beta 2) | [Version Support](../reference/version-support.md) |
| Windows (desktop) | x86_64 | — | project renderer | **Pending 4.7-stable revalidation** (last green on 4.7 beta 2 console binary) | [Version Support](../reference/version-support.md) |
| Android | **Pixel 7** (physical) + API 36 emulator | Android | **OpenGL Compatibility only** | Full 4.7-stable debug APK demo matrix + R8-minified Match3 release APK, both on Pixel 7 (2026-06-26) | [exporting/android.md](../exporting/android.md), [architecture-review-2026-06.md](./reference/architecture-review-2026-06.md) |
| iOS (full gate) | **iPhone 12** (physical) | iOS 26.5 | Godot iOS | Full 9-demo device gate + fresh-project install path, 0 guardrail failures; ~0.63 ms/frame binding overhead | [ios-demo-port-tracker.md](./active/ios-demo-port-tracker.md) (baseline 2026-06-25) |
| iOS (latest device) | **iPhone 15 Pro** (physical) | iOS 26.5 | Godot iOS | Playable demo corpus + singleton/virtual self-tests green (PTRCALL 59/0, OBJECTCALLS 91/0) | [ios-backend-roadmap.md](./active/ios-backend-roadmap.md), coverage PRs #29–31 |

**Android SDK/NDK for the matching 4.7-stable export templates:** SDK API 36,
build-tools 36.1.0, NDK 29.0.14206865 ([Version Support](../reference/version-support.md)).

**iOS device note:** the *repeatable full 9-demo device gate*
(`scripts/ios_device_gate.sh`) baseline is **iPhone 12** (iOS 26.5). **iPhone 15
Pro** (iOS 26.5) has device-validated the playable demo corpus, the FPS
`SceneTree.createTween` regression, the three retired singletons
(`InputMap`/`Time`/`PhysicsServer3D`), and the non-POD virtual return self-tests
(String / PackedStringArray / Variant). Both run Godot 4.7 stable. Neither is a
"recent iPhones" claim — the matrix names the two validated models.

### Generator coverage state (for the support claim)

Promoted, checked-in wrapper coverage (`api_wrapper_coverage.py`,
[api-coverage.md](../contributing/api-coverage.md)):

- **Classes: 1032 / 1036 — 99.6%**
- **Methods: 15274 / 15385 — 99.3%** (callable methods; engine virtuals tracked
  separately so they neither dilute callable coverage nor count as gaps)
- **Virtual methods** are dispatched via `@OverrideVirtual` and validated against a
  generated signature table. Marshalling bounds (task 13): desktop/Android cover
  the audited scalar/POD Variant shapes plus String, PackedStringArray, and
  Variant (`Any?`) returns; iOS value-returns cover
  Bool/Int/Float/Vector2/Vector2i/String/PackedStringArray and Variant over that
  audited inner-type set (unaudited inner types serialize as `nil` on iOS). Other
  `Packed*` arrays and Dictionary virtual returns are **deferred**.

Cross-platform shape identity is enforced by `check_full_drift_gate` (committed ==
fresh regen per platform, task 21). This is what lets a single support claim cover
the generated API across desktop, Android, and iOS.

---

## 3. iOS vs Android — same tier or separate?

**Recommendation: the same tier (Tier 2 — Experimental, device-validated), not
separate tiers.**

The [iOS backend roadmap](./active/ios-backend-roadmap.md) ("Mobile Parity With
Android") defines the convergence bar: both mobile backends should have a
documented export path, a physical-device smoke gate on the current Godot
baseline, and the Android-enabled public demo set passing on both. Both now meet
that bar:

| Convergence criterion | Android | iOS |
|---|---|---|
| Documented user-facing export path | ✅ [exporting/android.md](../exporting/android.md) | ✅ [exporting/ios.md](../exporting/ios.md) |
| Physical-device smoke gate on 4.7 stable | ✅ Pixel 7 (debug matrix + R8 Match3) | ✅ iPhone 12 full gate + iPhone 15 Pro corpus |
| Android-enabled public demo set on device | ✅ 8 demos | ✅ 9 demos (that set + Bunnymark) |
| Same generated wrapper API (drift-gate) | ✅ `check_full_drift_gate` | ✅ `check_full_drift_gate` |

Because they clear the same bar with the same generator, splitting them into
separate tiers would over-fit to their *caveats* rather than their *support level*.
Co-tiering keeps the public story simple: "desktop is supported; mobile (iOS +
Android) is experimental but device-validated."

**What each must keep proving to stay in Tier 2:**

- **Android:** the Pixel 7 (current-device) debug matrix and the R8-minified
  release APK gate stay green on the pinned PanamaPort fork; the OpenGL
  Compatibility path remains the validated renderer.
- **iOS:** the full `ios_device_gate.sh` 9-demo matrix + fresh-project path stay
  green on a recent iPhone; `compileKotlinIosArm64` and the on-device self-test
  matrix stay at 0 failures; 0 silent stubs holds.

**Asymmetries** (documented as §5 caveats, **not** tier splits): Android's
minified-release claim is tied to the PanamaPort fork, not upstream; iOS does not
run the heavy 3D / mobile-multiplayer demo (`tps-demo-kanama`). These are
per-platform limitations within the shared tier.

---

## 4. Wording recommendations for the public docs

These are **proposed** strings. Do not apply them here — application is
post-sign-off (§Out of scope). The theme: the public docs currently **under-claim
Android** (they still say "Pixel 7 pending", which task 05 closed), so the main fix
is to align them with the proven Pixel 7 gate while keeping "experimental".

### `docs/index.md` — Platform Status table

Current rows are stale for Android ("Pixel 7 pending") and thin for iOS. Proposed:

| Platform | Proposed status string |
|---|---|
| macOS | `Supported (4.7 stable)` |
| Linux | `Supported pending 4.7 stable revalidation` |
| Windows | `Supported pending 4.7 stable revalidation` |
| Android | `Experimental; device-validated on 4.7 stable (Pixel 7, OpenGL Compatibility)` |
| iOS | `Experimental (Kotlin/Native); device-validated on 4.7 stable (iPhone 12 / 15 Pro)` |
| Web | `Not planned` |

(Change from today: Android drops "emulator-validated … (Pixel 7 pending)" →
"device-validated … (Pixel 7, OpenGL Compatibility)". macOS "Validated" →
"Supported" only if the human adopts the Tier-1 "Supported" label; otherwise keep
"Validated (4.7 stable)".)

### `docs/reference/version-support.md` — Current Support Claims table

- **Android row** — replace `Emulator-validated (4.7 stable); Pixel 7 pending`
  with:
  `Device-validated (4.7 stable): Pixel 7 debug demo matrix + R8-minified Match3
  release APK (2026-06-26); OpenGL Compatibility renderer. Still experimental.`
  Keep the SDK/NDK detail and the note that the R8 claim is tied to the PanamaPort
  fork `com.github.falcon4ever.PanamaPort:Core:0.1.3-kanama-r8.2`, not upstream.
- **iOS row** — keep "Experimental (Kotlin/Native backend); not a supported
  export", but update the device detail to name both validated devices:
  `Full device gate on iPhone 12 (iOS 26.5); playable demo corpus + self-tests on
  iPhone 15 Pro; both on 4.7 stable iOS templates.`
- Update the "Pixel 7 hardware remains the Android gate" sentence in **API
  Baseline** — that gate has now passed; reword to "Pixel 7 hardware smoke has
  passed; Linux/Windows remain pending 4.7-stable revalidation."

### `docs/exporting/android.md`

Already accurate (it documents the passed Pixel 7 debug matrix and the R8 Match3
gate). Recommended tweak only: make the opening status line say
`experimental, device-validated on Pixel 7` so it reads consistently with the
index/version-support wording. Keep every renderer/dependency caveat.

### `docs/exporting/ios.md`

Already accurate and appropriately conservative ("experimental, not a supported
export"). Recommended tweak: in **Current Status**, distinguish the two devices
explicitly — the *full device gate* is iPhone 12; iPhone 15 Pro has *playable
corpus runs* — so the doc doesn't read as one device covering everything. Keep the
FPS Audio autoload follow-up noted as a non-blocking limitation.

---

## 5. Known limitations to document (not blockers)

These stay documented limitations for the 0.2.x line. None blocks the current
tiering; each is a bounded, disclosed gap.

### Cross-platform

- **No hot reload on mobile.** Hot reload is out of scope for both the iOS backend
  and Android (`Android hot reload is not designed and should be considered
  disabled`).
- **Virtual-return long tail deferred.** Other `Packed*` arrays and Dictionary
  `@OverrideVirtual` returns are not in the audited return set; iOS Variant
  returns are limited to the audited inner-type set (unaudited → `nil`). The
  Kotlin-lambda→Callable custom-callable path and Callable-as-Variant-arg remain
  deferred by design.

### iOS

- **Heavy 3D / mobile multiplayer is not supported on iOS.** The heaviest demo,
  `tps-demo-kanama`, does **not** run on iOS. It is blocked by ~13 un-audited iOS
  wrapper classes (e.g. `CPUParticles3D`, `DisplayServer`, `ShaderMaterial`,
  `MultiplayerSynchronizer`, `SceneMultiplayer`, `AnimationTree`, `ConfigFile`,
  `LightmapGI`, `OmniLight3D`/`SpotLight3D`, `BoneAttachment3D`, `FastNoiseLite`),
  method gaps (`SceneTree.createTimer`, `AnimationTree.getRootMotion*`, threaded
  `ResourceLoader`), **and** JVM-only stdlib usage in the demo
  (`java.io.File`/`readText`/`print`, `ConfigFile`-to-disk) that is not
  Kotlin/Native-portable. `@Rpc` config delivery is wired on the iOS C-shim path,
  but full launch and *mobile playability* (touch controls, multiplayer UI) are
  deferred — tracked in `kanama-tasks/19-tps-mobile-virtual-joysticks.md`. See the
  `tps-demo-kanama` row in [ios-demo-port-tracker.md](./active/ios-demo-port-tracker.md).
- **Device matrix breadth.** The repeatable full 9-demo gate is validated on
  iPhone 12 only; iPhone 15 Pro covers the playable corpus + self-tests but is not
  the full-gate elapsed baseline. "Recent iPhones" is not yet a proven claim.
- **FPS Audio autoload follow-up.** Starter-Kit-FPS is playable but has an
  intermittent Audio autoload follow-up; documented as non-blocking.
- **Audited type set only.** The iOS type/KSP-registration set covers the current
  demo corpus; APIs outside it are not guaranteed on iOS.
- **`Starter-Kit-City-Builder` is desktop-only by design** (GridMap/custom-list,
  desktop-focused controls); not a mobile target.

### Android

- **OpenGL Compatibility renderer only.** The validated Android path uses Godot's
  OpenGL Compatibility renderer. **Vulkan/Mobile-renderer validation is a separate,
  unproven support claim.**
- **R8/minification depends on the PanamaPort fork.** Minified release APKs are
  validated only with `com.github.falcon4ever.PanamaPort:Core:0.1.3-kanama-r8.2`.
  Upstream PanamaPort `v0.1.3` crashes in the FFI bootstrap under R8 (root-caused
  on Pixel 7, 2026-06-26) and is **not** an R8-supported path.
- **Per-demo mobile polish.** Orientation, screen size, touch controls, and UI
  scaling are per-demo, not a platform guarantee. First-use hitches on physical
  devices are expected without preloading.
- **Emulator rendering differs.** OpenGL Compat can differ from desktop Forward+
  (e.g. darker skybox in the 3D Platformer smoke); some ASTC textures may be
  converted at runtime.

### Desktop

- **Linux/Windows pending 4.7-stable revalidation.** Last full validation was on
  4.7 beta 2; the metadata-only 4.7 rc2→stable bump carries over for API/wrapper
  shape, but the runtime/demo smoke has not been re-run on stable Linux/Windows
  binaries. This is a release-gate item, not a limitation of the design.
- **Packaged desktop exports** are a separate release-readiness track from the
  editor/runtime smoke; package zips are not proof of exported-game support
  (AGENTS.md).

---

## 6. Release-gate checklist

Everything below must be green (or explicitly waived by the human with a
documented reason) before a release tag. This documents what is required; it does
**not** add new gates or run them (task 18 runs the release pass).

### Engine + version pins

- [ ] `python3 scripts/check_godot_version_pin.py` passes; `kanamaGodotVersion`
      is `4.7.stable` and the CI dash form matches.
- [ ] Gradle artifact version, docs snippets, demo project versions, badges, and
      changelog heading all match the tagged version.

### Desktop (Tier 1)

- [ ] `scripts/runtime_smoke.sh` green on macOS arm64 vs the 4.7 stable binary.
- [ ] **Linux + Windows revalidated on 4.7 stable** (currently pending) — desktop
      + demo smoke on matching 4.7-stable binaries.
- [ ] `scripts/fresh_clone_smoke.sh <godot-4.7-stable>` green from a clean temp
      clone.
- [ ] `./gradlew packageDistributions` + `scripts/package_install_smoke.sh`
      (`--desktop-kit` and `--store-addon`) green.

### Android (Tier 2)

- [ ] `scripts/android_smoke.sh` green on the API 36 emulator.
- [ ] **Pixel 7 (current physical device) debug demo matrix** green on 4.7 stable.
- [ ] `scripts/android_export_minified.sh` green — R8-minified Match3 release APK
      on Pixel 7 with the pinned PanamaPort fork (not upstream).

### iOS (Tier 2)

- [ ] `scripts/ios_device_gate.sh` full 9-demo matrix + fresh-project install path
      green on a recent physical iPhone (private device/signing values from
      maintainer notes, never committed).
- [ ] `./gradlew compileKotlinIosArm64` green.
- [ ] On-device self-test matrices at **0 failures** (PTRCALL / OBJECTCALLS).

### Generator + reports

- [ ] `check_full_drift_gate` green (committed == fresh regen for all three
      platform wrapper trees).
- [ ] `scripts/check_ios_no_silent_stubs.py` green (0 STUB / 0 SUGAR).
- [ ] Refreshed and committed:
      `ios_handwritten_report.py`, `api_wrapper_generator_report.py`,
      `api_wrapper_coverage.py`.

### Docs + hygiene

- [ ] `mkdocs build --strict` passes.
- [ ] `scripts/local_ci.sh <godot-4.7-stable> [<second-binary>]` green.
- [ ] **No private values** in public docs or committed scripts — device UDIDs,
      Apple team IDs, provisioning profile names, and workstation paths stay in
      maintainer notes. Verify with:
      `rg -n "KANAMA_IOS_DEVICE=[0-9A-Fa-f]|KANAMA_IOS_TEAM=[A-Z0-9]{10}" docs/ scripts/`

---

## Out of scope

Per the task spec, this document only **recommends**. It does not:

- Apply the §4 wording to `docs/index.md`, `docs/exporting/*`, or
  `docs/reference/version-support.md` (post-sign-off, likely task 18).
- Tag a release or decide the version number (task 18).
- Run or add platform validation gates (tasks 02, 05 own those).

## References

- [iOS Backend Roadmap](./active/ios-backend-roadmap.md) — step 5 (this decision) + mobile-parity bar
- [iOS Demo Port Tracker](./active/ios-demo-port-tracker.md) — iOS device gate baselines + demo matrix
- [Wrapper Coverage Tracker](./active/wrapper-coverage-tracker.md) — generator/coverage state
- [Architecture Review 2026-06](./reference/architecture-review-2026-06.md) — Android R8 root-cause (F-items), F3 perf deferral
- [Version Support](../reference/version-support.md) — current public support claims
- [API Coverage](../contributing/api-coverage.md) — 99.6% class / 99.3% method promoted coverage
- [exporting/android.md](../exporting/android.md) · [exporting/ios.md](../exporting/ios.md) — user-facing export workflows
