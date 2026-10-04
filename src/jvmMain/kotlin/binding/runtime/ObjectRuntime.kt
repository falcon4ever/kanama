package net.multigesture.kanama.binding.runtime

import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.ValueLayout.JAVA_LONG
import java.lang.invoke.MethodHandle
import net.multigesture.kanama.binding.ScriptBridge
import net.multigesture.kanama.ffi.GodotFFI

/**
 * Desktop/Android: the root wrappers' platform hooks (task 117 P3′, D20). The `expect` declaration
 * and what each hook promises are in
 * `src/commonMain/kotlin/net/multigesture/kanama/binding/runtime/ObjectRuntime.expect.kt`.
 */
internal actual object ObjectRuntime {
  /**
   * Test seam: answers [instanceIdOf] in JVM unit tests, which construct wrappers without an
   * engine.
   */
  @Volatile internal var instanceIdOverride: ((RawSegment) -> Long)? = null

  actual fun instanceIdOf(segment: RawSegment): Long =
    instanceIdOverride?.let { it(segment) } ?: ObjectCalls.objectGetInstanceId(segment)

  /**
   * `GDExtensionObjectPtr object_get_instance_from_id(GDObjectInstanceID)`: one interface downcall,
   * on the `instance_from_id_probe` shape prewarmed by `NativeCallSurface`. The pointer comes back
   * as a `long` (every supported target is 64-bit), so the check allocates no `MemorySegment`.
   *
   * A JVM constant (task 131 item 16): `@JvmField` in an `object` is a `static final` field, which
   * the JIT folds, and [isLive] calls it with `invokeExact` on its exact `(J)J` type -- no generic
   * invoker, no `asType`. The holder class initializes on first use, after `GodotFFI.bootstrap`.
   */
  private object InstanceLookup {
    @JvmField
    val FROM_ID: MethodHandle =
      GodotFFI.lookup("object_get_instance_from_id", FunctionDescriptor.of(JAVA_LONG, JAVA_LONG))
  }

  /**
   * Resolves `object_get_instance_from_id` once, when the freed-object check is configured (task
   * 131): false turns the check off with one log line instead of failing every wrapper call.
   */
  internal fun instanceLookupAvailable(): Boolean =
    runCatching { InstanceLookup.FROM_ID }
      .onFailure { System.err.println("[kanama:kt] object_get_instance_from_id: ${it.message}") }
      .isSuccess

  /** Test seam: answers [isLive] in JVM unit tests, which have no engine to ask. */
  @Volatile internal var isLiveOverride: ((RawSegment, Long) -> Boolean)? = null

  actual fun isLive(segment: RawSegment, instanceId: Long): Boolean {
    isLiveOverride?.let {
      return it(segment, instanceId)
    }
    return (InstanceLookup.FROM_ID.invokeExact(instanceId) as Long) == segment.address()
  }

  // Task 132 D7: the instance-binding liveness flag ([InstanceBindings]); null while the binding
  // check is off, and in JVM unit tests (the instance-id seam answers there).
  actual fun liveFlagOf(segment: RawSegment): LiveFlag? =
    if (!FreedObjectChecks.bindings || instanceIdOverride != null) null
    else InstanceBindings.liveFlagOf(segment)

  actual fun emitSignal(segment: RawSegment, signal: String, args: List<Any?>) {
    Signals.emitAny(segment, signal, args)
  }

  // A value set on a Kanama-script owner before its Kotlin instance exists is recorded and
  // replayed once the instance is created (ScriptBridge.applyOrRecordScriptPropertySet).
  actual fun onPropertySet(segment: RawSegment, property: String, value: Any?) {
    ScriptBridge.applyOrRecordScriptPropertySet(segment, property, value)
  }

  // Arms (or disarms) that buffering for the owner the script is attached to.
  actual fun onSetScript(segment: RawSegment, script: RawSegment) {
    ScriptBridge.noteSetScript(segment, script)
  }
}
