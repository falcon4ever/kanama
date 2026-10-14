# iOS backend — hand-written / stub registry

**GENERATED — do not edit. Run `python3 scripts/ios_handwritten_report.py`.**

Every hand-written, stubbed, or sugar site in the iOS backend, from the in-code
`// KANAMA-IOS-{STUB,HANDWRITTEN,SUGAR}` markers. The point: nothing silently no-ops
without being listed here, so we don't repeat the deep-dive bugs from unwired
annotations/signals. `scripts/check_ios_no_silent_stubs.py` fails CI on an
un-annotated bare-default return.

Totals: **0 STUB** · **5 HANDWRITTEN** · **0 SUGAR** (5 marked sites).

## STUB

_Silent stubs — should call real Godot but don't yet (follow up to avoid silent-no-op bugs)._

_(none)_

## HANDWRITTEN

_Intentionally bespoke — not generatable from extension_api.json; correct as-is._

| Location | Note |
|---|---|
| `src/iosMain/kotlin/net/multigesture/kanama/api/GodotSignal.kt:5` | [runtime] signal/connect/emitSignal/await use the custom GDExtension |
| `src/iosMain/kotlin/net/multigesture/kanama/api/IosGodotApi.kt:32` | [seam] thin cinterop facade over the C shim entry points the runtime and |
| `src/iosMain/kotlin/net/multigesture/kanama/api/MainThread.kt:9` | [platform] MainThread.post/runOnMainThread run inline (Kotlin/Native scripts already run on the engine main thread); the frame queues are pumped by KanamaIosRuntime.frame(), not a JVM executor. |
| `src/iosMain/kotlin/net/multigesture/kanama/api/ScriptAccessSelfTest.kt:7` | [selftest] task 133 rows of the OBJECTCALLS SELFTEST frame-1 phase: class tokens, checked casts, script checks, tree accessors, preload errors and the script scope on the device runtime. |
| `src/iosMain/kotlin/net/multigesture/kanama/api/ScriptRuntime.kt:7` | [platform] iOS actual of the script-authoring seam (ScriptRuntime.expect.kt, task 133): the runtime's script-instance table, the main dispatcher and the script-error report. |

## SUGAR

_Hand-added inside a GENERATED wrapper file — regeneration overwrites it; re-add after._

_(none)_

