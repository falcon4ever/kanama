# iOS backend — hand-written / stub registry

**GENERATED — do not edit. Run `python3 scripts/ios_handwritten_report.py`.**

Every hand-written, stubbed, or sugar site in the iOS backend, from the in-code
`// KANAMA-IOS-{STUB,HANDWRITTEN,SUGAR}` markers. The point: nothing silently no-ops
without being listed here, so we don't repeat the deep-dive bugs from unwired
annotations/signals. `scripts/check_ios_no_silent_stubs.py` fails CI on an
un-annotated bare-default return.

Totals: **12 STUB** · **10 HANDWRITTEN** · **5 SUGAR** (27 marked sites).

## STUB

_Silent stubs — should call real Godot but don't yet (follow up to avoid silent-no-op bugs)._

| Location | Note |
|---|---|
| `ios/bootstrap/kanama_ios_shim.c:390` | PT_STRING / PT_NODE_PATH arg construction not implemented in the |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/AnimationPlayer.kt:197` | getCurrentAnimation returns the last play() name, not Godot's live |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/CanvasItem.kt:538` | get_modulate not read (Color return not audited); set is real. Backlog. |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:143` | should dispatch via the Variant Object.call path; not wired yet. Backlog. |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:147` | should call Object.set_deferred via Variant dispatch; not wired yet. Backlog. |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:154` | should call Object.disconnect; not wired yet. Backlog. |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:158` | connectBound should call Object.connect with bound args Callable; not wired yet. Backlog. |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:167` | should call Object.disconnect; not wired yet. Backlog. |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:249` | should carry the real connect() return Error; hardcoded OK. Backlog. |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:252` | close() should disconnect the connection; not wired yet. Backlog. |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:390` | cosmetic; needs Texture2D arg marshalling to call set_custom_mouse_cursor. Backlog. |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/Label.kt:420` | get_text not read (String-return ptrcall not wired); set is real. Backlog. |

## HANDWRITTEN

_Intentionally bespoke — not generatable from extension_api.json; correct as-is._

| Location | Note |
|---|---|
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:107` | KanamaScope bridges Godot's main thread to Kotlin coroutines; not generatable from extension_api.json. |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:121` | MainThread.post is a no-op shim; on iOS main thread dispatch is handled by Godot's frame loop, not a JVM executor. |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:138` | signal/connect/emitSignal/await use the custom GDExtension |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:189` | AutoCloseable no-op base; Godot object lifetime is managed externally, not by Kotlin's close(). |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:313` | Tweener/PropertyTweener/Tween use the Variant tween_property path |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:381` | Input is the bespoke singleton glue (getSingleton + cached binds); |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:401` | pure-Kotlin math helpers (no Godot call). Bespoke utility. |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:423` | ResourceLoader singleton glue over the C shim resource loader. |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:436` | GD global helpers (rand*, print) — Kotlin/native impls, bespoke. |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:461` | thin cinterop facade over the C shim helpers used by the bespoke |

## SUGAR

_Hand-added inside a GENERATED wrapper file — regeneration overwrites it; re-add after._

| Location | Note |
|---|---|
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/AnimationPlayer.kt:194` | hand-added to a generated file; re-add after regeneration. |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/CanvasItem.kt:531` | hand-added to a generated file; re-add after regeneration. |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/Label.kt:416` | hand-added to a generated file; re-add after regeneration. |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/Node.kt:306` | hand-added to a generated file; re-add after regeneration. |
| `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/Viewport.kt:1310` | hand-added to a generated file; re-add after regeneration. |

