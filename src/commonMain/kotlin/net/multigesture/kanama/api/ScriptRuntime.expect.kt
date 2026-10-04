package net.multigesture.kanama.api

import kotlinx.coroutines.CoroutineScope

/**
 * The platform-bound half of the script-authoring API (task 133): `KanamaScript`, the node, script
 * and preload delegates, `castOrNull`, `isScript` and `instantiateScript` are written once in
 * common code, and these hooks are what each backend does differently:
 * - [scriptInstanceOf]: the Kotlin object behind a Kanama script attached to [owner] (desktop:
 *   `ScriptBridge`'s owner map; iOS: the runtime's script-instance table);
 * - [loadResource]: `ResourceLoader.load(path)`, an owned `+1` reference (`ResourceLoader` is a
 *   per-platform singleton);
 * - [isEditorHint]: `Engine.is_editor_hint()` (`Engine` is per-platform too);
 * - [newScriptScope]: a script's coroutine scope on the engine main thread, which reports an escaped
 *   exception as a Godot script error (the platform's `KanamaScope`);
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

  /** A new coroutine scope on the engine main thread, for one script instance. */
  fun newScriptScope(): CoroutineScope

  /** The class tokens of the wrappers this platform declares outside the shared tree. */
  fun platformClasses(): GodotClassTable
}
