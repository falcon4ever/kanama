package net.multigesture.kanama.binding.runtime

/**
 * The platform-bound half of the root wrappers (task 117 P3′, decision D20).
 *
 * `GodotObject`, `RefCounted` and `GodotCallable` are written ONCE, by hand, in
 * `src/sharedApi/kotlin/net/multigesture/kanama/api/`. Every body there is a ptrcall through
 * [ObjectCalls] except these four hooks, which each backend implements differently — so they are
 * the whole seam, and the compiler holds both backends to it the way it holds them to [ObjectCalls]
 * and [BuiltinCalls]:
 * - [instanceIdOf]: the engine instance id `GodotObject` captures once at construction (desktop:
 *   the `object_get_instance_id` interface downcall; iOS: the shim's entry of the same name);
 * - [emitSignal]: `Object.emit_signal(name, *args)` (desktop: `Signals.emitAny`, a Variant
 *   method-bind call; iOS: the shim's single-argument fast paths, else the Variant `emit_signal`);
 * - [onPropertySet] and [onSetScript]: desktop's `ScriptBridge` script-property buffering, which
 *   replays a value set on a Kanama-script owner before its Kotlin instance exists. iOS does not
 *   buffer and documents why on its no-op actuals.
 *
 * Internal: nothing outside the module calls a hook directly. Keep it this small — a hook belongs
 * here only when a root's body cannot be a ptrcall through [ObjectCalls].
 *
 * Files named `*.expect.kt` hold `expect` declarations only: the Android source remap skips them by
 * name and compiles the `src/jvmMain` actual with its `actual ` modifier stripped. That is also why
 * no member here has a default argument (the Android lane would never see it).
 */
internal expect object ObjectRuntime {
  /** The engine instance id of the live object behind [segment]. */
  fun instanceIdOf(segment: RawSegment): Long

  /** `Object.emit_signal([signal], *[args])` on the object behind [segment]. */
  fun emitSignal(segment: RawSegment, signal: String, args: List<Any?>)

  /** Called after `GodotObject.set`/`call("set", …)` wrote [property] on the object. */
  fun onPropertySet(segment: RawSegment, property: String, value: Any?)

  /** Called before `GodotObject.setScript` attaches [script] (NULL when detaching). */
  fun onSetScript(segment: RawSegment, script: RawSegment)
}
