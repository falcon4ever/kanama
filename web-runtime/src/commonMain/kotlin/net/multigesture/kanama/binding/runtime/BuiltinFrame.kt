package net.multigesture.kanama.binding.runtime

import net.multigesture.kanama.types.GodotRealStorage

/**
 * The Web half of the builtin-call facade (task 134 D1): the shared value types
 * (`src/commonMain/.../types`, compiled into the Web build too) call value-type methods through the
 * same [BuiltinFrame] API as desktop/Android (Panama) and iOS (C shim), declared there by
 * `BuiltinFrame.expect.kt`. Web is a separate project, so these are plain declarations with the
 * same shape; compiling the shared types against them is what holds the two together.
 *
 * A frame's slots hold the components in Godot's memory layout (the generated `BuiltinFrame.put`
 * helpers write them), slot 0 the base, slots 1..N the arguments. [call] runs the method either in
 * Kotlin ([WebLocalBuiltins]: the formulas the Web value types always computed locally, kept so a
 * per-tick `basis.getRotationQuaternion()` does not become a bridge crossing) or in the engine: one
 * immediate crossing carrying the method, the base and the arguments as text (the proxy's
 * `_kanama_web_builtin_call`, `Callable.create(base, method).callv(args)`), with the argument
 * Variant types from the generated [WebBuiltinSignatures].
 */
internal class BuiltinMethod(val variantType: Int, val name: String, val hash: Long) {
  internal val local: ((BuiltinFrame, Int) -> Boolean)? = WebLocalBuiltins.find(variantType, name)

  /** The Godot `Variant.Type` of each declared argument (generated from extension_api.json). */
  internal val argumentTypes: IntArray by lazy {
    WebBuiltinSignatures.argumentTypes(variantType, name)
      ?: error("Kanama Web has no signature for builtin $variantType.$name")
  }
}

private val frames = ArrayList<BuiltinFrame>()
private var depth = 0

/** The next free frame of the (single) Web thread; [BuiltinFrame.call] gives it back. */
internal fun builtinFrame(): BuiltinFrame {
  if (depth == frames.size) frames.add(BuiltinFrame())
  return frames[depth++]
}

internal class BuiltinFrame {
  /** Each slot's components (a real/int32/float32 component or a `float`/`bool`), as Double. */
  internal val slots = Array(MAX_SLOTS) { DoubleArray(MAX_COMPONENTS) }
  internal val longs = LongArray(MAX_SLOTS)
  internal val strings = arrayOfNulls<String>(MAX_SLOTS)

  /** The returned components; [retType] is its Godot `Variant.Type` (0 = NIL). */
  internal val ret = DoubleArray(MAX_COMPONENTS)
  internal var retLongValue = 0L
  internal var retType = 0

  fun putReal(slot: Int, index: Int, value: GodotRealStorage) {
    slots[slot][index] = value.toDouble()
  }

  fun putInt32(slot: Int, index: Int, value: Int) {
    slots[slot][index] = value.toDouble()
  }

  fun putFloat32(slot: Int, index: Int, value: Float) {
    slots[slot][index] = value.toDouble()
  }

  fun putDouble(slot: Int, value: Double) {
    slots[slot][0] = value
  }

  fun putLong(slot: Int, value: Long) {
    longs[slot] = value
  }

  fun putBool(slot: Int, value: Boolean) {
    slots[slot][0] = if (value) 1.0 else 0.0
  }

  fun putString(slot: Int, value: String) {
    strings[slot] = value
  }

  fun call(method: BuiltinMethod, argc: Int) = dispatch(method, argc, static = false)

  fun callStatic(method: BuiltinMethod, argc: Int) = dispatch(method, argc, static = true)

  private fun dispatch(method: BuiltinMethod, argc: Int, static: Boolean) {
    try {
      retType = 0
      if (method.local?.invoke(this, argc) != true) webRemoteBuiltinCall(this, method, argc, static)
    } finally {
      for (i in 0..argc) strings[i] = null
      depth--
    }
  }

  fun retReal(index: Int): GodotRealStorage = ret[index].toFloat()

  fun retInt32(index: Int): Int = ret[index].toInt()

  fun retFloat32(index: Int): Float = ret[index].toFloat()

  fun retDouble(): Double = ret[0]

  fun retLong(): Long = retLongValue

  fun retBool(): Boolean = ret[0] != 0.0

  fun retVariantIsNil(): Boolean = retType == 0

  fun retVariantReal(index: Int): GodotRealStorage = ret[index].toFloat()

  internal companion object {
    /** The base plus the widest builtin method's arguments (`cubic_interpolate_in_time`: 7). */
    const val MAX_SLOTS = 9

    /** The widest value type: Projection's 16 components. */
    const val MAX_COMPONENTS = 16
  }
}
