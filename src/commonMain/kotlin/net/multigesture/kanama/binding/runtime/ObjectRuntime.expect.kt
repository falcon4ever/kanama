package net.multigesture.kanama.binding.runtime

/**
 * The platform-bound half of the root wrappers (task 117 P3′, decision D20).
 *
 * `GodotObject`, `RefCounted` and `GodotCallable` are written ONCE, by hand, in
 * `src/commonMain/kotlin/net/multigesture/kanama/api/` (common code since task 117 P4′). Every body
 * there is a ptrcall through [ObjectCalls] except these hooks, which each backend implements
 * differently — so they are the whole seam, and the compiler holds both backends to it the way it
 * holds them to [ObjectCalls] and [BuiltinCalls]:
 * - [instanceIdOf]: the engine instance id `GodotObject` captures once at construction (desktop:
 *   the `object_get_instance_id` interface downcall; iOS: the shim's entry of the same name);
 * - [isLive]: whether the object a wrapper captured is still alive -- the freed-object check behind
 *   every wrapper call while [FreedObjectChecks.enabled] (task 131 item 2; desktop: the
 *   `object_get_instance_from_id` interface downcall; iOS: the shim's entry over the same
 *   function);
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

  /**
   * True when [instanceId] still resolves to the object at [segment]
   * (`object_get_instance_from_id`). Never dereferences [segment], so it is safe to ask about a
   * freed object.
   */
  fun isLive(segment: RawSegment, instanceId: Long): Boolean

  /**
   * The liveness flag of the object behind [segment] (task 132 D7): an instance binding whose free
   * callback marks it dead, shared by every wrapper of that object, carrying the instance id. Null
   * when the binding check is off ([FreedObjectChecks.bindings] false), and the wrapper falls back
   * to [instanceIdOf] and [isLive].
   */
  fun liveFlagOf(segment: RawSegment): LiveFlag?

  /** `Object.emit_signal([signal], *[args])` on the object behind [segment]. */
  fun emitSignal(segment: RawSegment, signal: String, args: List<Any?>)

  /**
   * Starts a typed signal emission (task 134 C review S5): the caller writes [argumentCount]
   * arguments into the returned writer and ends with [finishEmit], which emits when [send] (and
   * always releases the writer). Emissions nest (a handler may emit), so each gets its own writer.
   */
  fun beginEmit(
    segment: RawSegment,
    signal: String,
    argumentCount: Int,
  ): net.multigesture.kanama.api.SignalArgWriter

  /** Emits what [writer] holds (when [send]) and releases it. */
  fun finishEmit(writer: net.multigesture.kanama.api.SignalArgWriter, send: Boolean)

  /** Called after `GodotObject.set`/`call("set", …)` wrote [property] on the object. */
  fun onPropertySet(segment: RawSegment, property: String, value: Any?)

  /** Called before `GodotObject.setScript` attaches [script] (NULL when detaching). */
  fun onSetScript(segment: RawSegment, script: RawSegment)
}
