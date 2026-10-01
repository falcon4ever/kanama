package net.multigesture.kanama.binding.runtime

import net.multigesture.kanama.binding.ScriptBridge

/**
 * Desktop/Android: the root wrappers' platform hooks (task 117 P3′, D20). The `expect` declaration
 * and what each hook promises are in
 * `src/commonMain/kotlin/net/multigesture/kanama/binding/runtime/ObjectRuntime.expect.kt`.
 */
internal actual object ObjectRuntime {
  actual fun instanceIdOf(segment: RawSegment): Long = ObjectCalls.objectGetInstanceId(segment)

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
