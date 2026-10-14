package net.multigesture.kanama.api

import kotlinx.coroutines.CoroutineDispatcher

/**
 * The platform-bound half of the script-authoring API (task 133): `KanamaScript`, the node, script
 * and preload delegates, `castOrNull`, `isScript` and `instantiateScript` are written once in
 * common code, and these hooks are what each backend does differently:
 * - [scriptInstanceOf]: the Kotlin object behind a Kanama script attached to [owner] (desktop:
 *   `ScriptBridge`'s owner map; iOS: the runtime's script-instance table);
 * - [loadResource]: `ResourceLoader.load(path)`, an owned `+1` reference (`ResourceLoader` is a
 *   per-platform singleton);
 * - [isEditorHint]: `Engine.is_editor_hint()` (`Engine` is per-platform too);
 * - [mainDispatcher] / [reportCoroutineFailure]: what the common `KanamaScope` dispatches on and how
 *   it reports an exception that escaped a script coroutine (a Godot script error);
 * - [platformClasses]: the generated class-token table of the classes this platform hosts outside
 *   the shared tree (`PER_PLATFORM_WRAPPERS`).
 *
 * Internal: game code never calls a hook directly. Files named `*.expect.kt` hold `expect`
 * declarations only (the Android source remap skips them), and no member has a default argument.
 */
internal expect object ScriptRuntime {
  /** The Kotlin script object attached to [owner], or `null` when it has no Kanama script. */
  fun scriptInstanceOf(owner: GodotObject): Any?

  /** `ResourceLoader.load([path])`; the caller owns the returned reference. */
  fun loadResource(path: String): Resource?

  /** `Engine.is_editor_hint()`. */
  fun isEditorHint(): Boolean

  /** The coroutine dispatcher of the engine main thread (`KanamaDispatchers.Main`). */
  fun mainDispatcher(): CoroutineDispatcher

  /** Reports [throwable], which escaped a script coroutine ([where] names it), as a script error. */
  fun reportCoroutineFailure(throwable: Throwable, where: String)

  /** The class tokens of the wrappers this platform declares outside the shared tree. */
  fun platformClasses(): GodotClassTable
}
