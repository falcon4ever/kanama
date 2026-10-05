package net.multigesture.kanama.binding.runtime

import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.IosGodot
import net.multigesture.kanama.api.SignalArgWriter
import net.multigesture.kanama.types.Vector2i

/**
 * iOS: the root wrappers' platform hooks (task 117 P3′, D20), over the C shim. The `expect`
 * declaration and what each hook promises are in
 * `src/commonMain/kotlin/net/multigesture/kanama/binding/runtime/ObjectRuntime.expect.kt`.
 */
internal actual object ObjectRuntime {
  actual fun instanceIdOf(segment: RawSegment): Long =
    IosGodot.objectGetInstanceId(segment.address())

  actual fun isLive(segment: RawSegment, instanceId: Long): Boolean =
    IosGodot.objectIsLive(segment.address(), instanceId)

  // No instance binding on iOS yet (task 132 D7): the wrapper keeps the instance-id lookup.
  actual fun liveFlagOf(segment: RawSegment): LiveFlag? = null

  // The typed signals' emit (task 134 C review S5): iOS collects the arguments and takes the
  // emitSignal path below.
  private class ListWriter(val segment: RawSegment, val signal: String, count: Int) :
    SignalArgWriter {
    val values = arrayOfNulls<Any?>(count)

    override fun long(index: Int, value: Long) {
      values[index] = value
    }

    override fun double(index: Int, value: Double) {
      values[index] = value
    }

    override fun bool(index: Int, value: Boolean) {
      values[index] = value
    }

    override fun string(index: Int, value: String) {
      values[index] = value
    }

    override fun obj(index: Int, value: GodotObject?) {
      values[index] = value
    }

    override fun value(index: Int, value: Any?) {
      values[index] = value
    }
  }

  actual fun beginEmit(segment: RawSegment, signal: String, argumentCount: Int): SignalArgWriter =
    ListWriter(segment, signal, argumentCount)

  actual fun finishEmit(writer: SignalArgWriter, send: Boolean) {
    val list = writer as ListWriter
    if (send) emitSignal(list.segment, list.signal, list.values.asList())
  }

  actual fun emitSignal(segment: RawSegment, signal: String, args: List<Any?>) {
    val instance = segment.address()
    val single = args.singleOrNull()
    when {
      // Keep the bespoke C-shim fast paths for the single scalar args they cover (they were the
      // public `emitSignal(name, Int|Long|Vector2i)` overloads until task 117 P3′, D22)...
      args.size == 1 && single is Int ->
        IosGodot.objectEmitSignalInt(instance, signal, single.toLong())
      args.size == 1 && single is Long -> IosGodot.objectEmitSignalInt(instance, signal, single)
      args.size == 1 && single is Vector2i ->
        IosGodot.objectEmitSignalVector2i(instance, signal, single.x.toLong(), single.y.toLong())
      // ...and route everything else (no-arg signals, and any other arg shapes) through
      // Object.emit_signal via the Variant call path. The previous `when` silently dropped
      // no-arg signals (empty args matched nothing), so e.g. a no-arg @Signal never fired.
      else ->
        ObjectCalls.callWithVariantArgs(
          GodotObject.callBind,
          segment,
          listOf("emit_signal", signal) + args,
        )
    }
  }

  // Desktop also calls ScriptBridge.applyOrRecordScriptPropertySet here, which buffers a value
  // set on a Kanama-script owner *before* its Kotlin instance exists and replays it once the
  // instance is created. iOS receives engine-driven property sets directly through
  // KanamaIosScriptBridge, so the steady-state path needs no buffering; the rare "set a script
  // property before the instance is ready" case is not buffered on iOS. Deliberately a no-op.
  actual fun onPropertySet(segment: RawSegment, property: String, value: Any?) {}

  // Desktop also calls ScriptBridge.noteSetScript here to arm the pre-instance script-property
  // buffering described on [onPropertySet]; iOS does not mirror it. Deliberately a no-op.
  actual fun onSetScript(segment: RawSegment, script: RawSegment) {}
}
