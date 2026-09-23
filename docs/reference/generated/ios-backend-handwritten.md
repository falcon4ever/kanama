# iOS backend — hand-written / stub registry

**GENERATED — do not edit. Run `python3 scripts/ios_handwritten_report.py`.**

Every hand-written, stubbed, or sugar site in the iOS backend, from the in-code
`// KANAMA-IOS-{STUB,HANDWRITTEN,SUGAR}` markers. The point: nothing silently no-ops
without being listed here, so we don't repeat the deep-dive bugs from unwired
annotations/signals. `scripts/check_ios_no_silent_stubs.py` fails CI on an
un-annotated bare-default return.

Totals: **0 STUB** · **11 HANDWRITTEN** · **5 SUGAR** (16 marked sites).

## STUB

_Silent stubs — should call real Godot but don't yet (follow up to avoid silent-no-op bugs)._

_(none)_

## HANDWRITTEN

_Intentionally bespoke — not generatable from extension_api.json; correct as-is._

| Location | Note |
|---|---|
| `src/iosMain/kotlin/net/multigesture/kanama/api/Engine.kt:6` | [glue] Engine singleton. Not retired to the generated wrapper because |
| `src/iosMain/kotlin/net/multigesture/kanama/api/FileAccess.kt:7` | [glue] FileAccess static facade. The desktop shape is hand-shaped |
| `src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:123` | [platform] KanamaScope bridges Godot's main thread to Kotlin coroutines; not generatable from extension_api.json. |
| `src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:137` | [platform] MainThread.post is a no-op shim; on iOS main thread dispatch is handled by Godot's frame loop, not a JVM executor. |
| `src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:220` | [runtime] signal/connect/emitSignal/await use the custom GDExtension |
| `src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:474` | [runtime] Tween uses the Variant tween_property path (final-value is a |
| `src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:587` | [platform] pure-Kotlin math helpers (no Godot call). Bespoke utility, |
| `src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:655` | [glue] ResourceLoader singleton. Not retired to the generated wrapper: |
| `src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:731` | [platform] GD global helpers (rand*, print) — Kotlin/native impls, bespoke. |
| `src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:820` | [glue] thin cinterop facade over the C shim helpers used by the bespoke |
| `src/iosMain/kotlin/net/multigesture/kanama/api/ProjectSettings.kt:6` | [glue] ProjectSettings singleton. Not retired to the generated wrapper: |

## SUGAR

_Hand-added inside a GENERATED wrapper file — regeneration overwrites it; re-add after._

| Location | Note |
|---|---|
| `src/iosMain/kotlin/net/multigesture/kanama/api/BoxMesh.kt:78` | [glue] desktop-parity constructor sugar (the desktop wrapper's |
| `src/iosMain/kotlin/net/multigesture/kanama/api/BoxShape3D.kt:30` | [glue] desktop-parity constructor sugar (the desktop wrapper's |
| `src/iosMain/kotlin/net/multigesture/kanama/api/ImageTexture.kt:28` | [runtime] create_from_image is STATIC — the instance dispatch |
| `src/iosMain/kotlin/net/multigesture/kanama/api/ParticleProcessMaterial.kt:918` | [glue] downcast a Resource (null if not), mirroring the desktop |
| `src/iosMain/kotlin/net/multigesture/kanama/api/ProceduralSkyMaterial.kt:228` | [glue] downcast a Resource (null if not), mirroring the desktop |

