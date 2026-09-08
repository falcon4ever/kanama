# iOS backend — hand-written / stub registry

**GENERATED — do not edit. Run `python3 scripts/ios_handwritten_report.py`.**

Every hand-written, stubbed, or sugar site in the iOS backend, from the in-code
`// KANAMA-IOS-{STUB,HANDWRITTEN,SUGAR}` markers. The point: nothing silently no-ops
without being listed here, so we don't repeat the deep-dive bugs from unwired
annotations/signals. `scripts/check_ios_no_silent_stubs.py` fails CI on an
un-annotated bare-default return.

Totals: **0 STUB** · **12 HANDWRITTEN** · **9 SUGAR** (21 marked sites).

## STUB

_Silent stubs — should call real Godot but don't yet (follow up to avoid silent-no-op bugs)._

_(none)_

## HANDWRITTEN

_Intentionally bespoke — not generatable from extension_api.json; correct as-is._

| Location | Note |
|---|---|
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/Engine.kt:6` | [glue] Engine singleton. Not retired to the generated wrapper because |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/FileAccess.kt:7` | [glue] FileAccess static facade. The desktop shape is hand-shaped |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:120` | [platform] KanamaScope bridges Godot's main thread to Kotlin coroutines; not generatable from extension_api.json. |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:134` | [platform] MainThread.post is a no-op shim; on iOS main thread dispatch is handled by Godot's frame loop, not a JVM executor. |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:200` | [runtime] signal/connect/emitSignal/await use the custom GDExtension |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:302` | [platform] AutoCloseable no-op base for non-refcounted objects |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:589` | [runtime] Tweener/PropertyTweener/Tween use the Variant tween_property path |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:728` | [platform] pure-Kotlin math helpers (no Godot call). Bespoke utility, |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:796` | [glue] ResourceLoader singleton. Not retired to the generated wrapper: |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:872` | [platform] GD global helpers (rand*, print) — Kotlin/native impls, bespoke. |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:958` | [glue] thin cinterop facade over the C shim helpers used by the bespoke |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/ProjectSettings.kt:6` | [glue] ProjectSettings singleton. Not retired to the generated wrapper: |

## SUGAR

_Hand-added inside a GENERATED wrapper file — regeneration overwrites it; re-add after._

| Location | Note |
|---|---|
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/BoxMesh.kt:70` | [glue] desktop-parity constructor sugar (the desktop wrapper's |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/BoxShape3D.kt:28` | [glue] desktop-parity constructor sugar (the desktop wrapper's |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/Image.kt:38` | [runtime] get_data / load_png_from_buffer are PackedByteArray shapes |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/Image.kt:229` | [runtime] create_from_data is STATIC + PackedByteArray — both |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/ImageTexture.kt:25` | [runtime] create_from_image is STATIC — the instance dispatch |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/ParticleProcessMaterial.kt:802` | [glue] downcast a Resource (null if not), mirroring the desktop |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/PlaneMesh.kt:95` | [glue] downcast a Resource (null if not), mirroring the desktop |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/ProceduralSkyMaterial.kt:202` | [glue] downcast a Resource (null if not), mirroring the desktop |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/StandardMaterial3D.kt:14` | [glue] desktop-parity constructor sugar (the desktop wrapper's |

