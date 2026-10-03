package net.multigesture.kanama.binding.runtime

import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_LONG
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
   * on the `instance_from_id_probe` shape prewarmed by `NativeCallSurface`.
   */
  private val objectGetInstanceFromId by lazy {
    GodotFFI.lookup("object_get_instance_from_id", FunctionDescriptor.of(ADDRESS, JAVA_LONG))
  }

  /** Test seam: answers [isLive] in JVM unit tests, which have no engine to ask. */
  @Volatile internal var isLiveOverride: ((RawSegment, Long) -> Boolean)? = null

  actual fun isLive(segment: RawSegment, instanceId: Long): Boolean {
    isLiveOverride?.let {
      return it(segment, instanceId)
    }
    val live = objectGetInstanceFromId.invoke(instanceId) as MemorySegment
    return live.address() == segment.address()
  }

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
