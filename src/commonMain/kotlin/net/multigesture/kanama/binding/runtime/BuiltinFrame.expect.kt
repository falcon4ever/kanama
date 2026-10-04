package net.multigesture.kanama.binding.runtime

import net.multigesture.kanama.types.GodotRealStorage

/**
 * Value-type (builtin) method calls: the one facade the shared value-type bodies under
 * `src/commonMain/kotlin/net/multigesture/kanama/types/` call, so that `Transform3D.inverse()` and
 * friends have ONE body for every backend (task 104 step 2). Desktop/Android implement it over
 * Panama/FFM (`variant_get_ptr_builtin_method` + one unbound builtin-method downcall), iOS over the
 * C shim (`kanama_ios_godot_builtin_call`); the compiler holds both to these declarations.
 *
 * Since task 134 B a call allocates nothing: the generated member takes a [BuiltinFrame] from the
 * calling thread ([builtinFrame]), writes its base value and its arguments straight into it (fixed
 * native slots, slot 0 the base, slots 1..N the arguments, each laid out as Godot stores the type),
 * calls -- which returns the frame -- and reads the return slot back. No argument list, no boxed
 * value, no per-call arena.
 *
 * A builtin CAN re-enter Kotlin while it runs: a WARN/ERR print reaches every registered logger
 * synchronously, and a GDScript logger may call a Kotlin script that makes builtin calls of its
 * own, while the engine still reads the outer call's arguments by reference. So the frames of a
 * thread are a stack: [builtinFrame] takes the next free frame and `call` gives it back, and a
 * nested call writes a different frame (the runtime smoke's builtin re-entry row proves it).
 *
 * The `VT_*`/`PT_*` wire numbers are top-level `const val`s in [BuiltinTags]: an `expect`
 * declaration cannot carry a value, so declaring them here would have proven presence and not
 * equality.
 */
internal expect class BuiltinMethod(variantType: Int, name: String, hash: Long)

/**
 * Take the calling thread's next free call frame (see [BuiltinMethod]); its [BuiltinFrame.call] or
 * [BuiltinFrame.callStatic] returns it. Frames are created on first use and kept for the thread.
 */
internal expect fun builtinFrame(): BuiltinFrame

internal expect class BuiltinFrame {
  /** Component [index] of a `real_t` struct in [slot] (0 = the base, 1.. = the arguments). */
  fun putReal(slot: Int, index: Int, value: GodotRealStorage)

  /** Component [index] of an `int32_t` struct (Vector2i/3i/4i, Rect2i) in [slot]. */
  fun putInt32(slot: Int, index: Int, value: Int)

  /** Component [index] of a `float` struct (`Color`, float32 in every build) in [slot]. */
  fun putFloat32(slot: Int, index: Int, value: Float)

  /** A `float` argument: an 8-byte double at ptrcall, whatever the engine's `real_t`. */
  fun putDouble(slot: Int, value: Double)

  /** An `int` argument (`int64_t`). */
  fun putLong(slot: Int, value: Long)

  /** A `bool` argument (one byte). */
  fun putBool(slot: Int, value: Boolean)

  /** A `String` argument: built for the call and destroyed after it. */
  fun putString(slot: Int, value: String)

  /** Call [method] on the base in slot 0 with the first [argc] argument slots. */
  fun call(method: BuiltinMethod, argc: Int)

  /** Call the static [method] (Godot's NULL instance) with the first [argc] argument slots. */
  fun callStatic(method: BuiltinMethod, argc: Int)

  /** Component [index] of a returned `real_t` struct. */
  fun retReal(index: Int): GodotRealStorage

  /** Component [index] of a returned `int32_t` struct. */
  fun retInt32(index: Int): Int

  /** Component [index] of a returned `Color`. */
  fun retFloat32(index: Int): Float

  /** A returned `float` (8-byte double). */
  fun retDouble(): Double

  /** A returned `int` (`int64_t`). */
  fun retLong(): Long

  /** A returned `bool` (one byte). */
  fun retBool(): Boolean

  /**
   * Whether a returned `Variant` is NIL. The return slot is zeroed before every call, so a method
   * that leaves it untouched reads as NIL too.
   */
  fun retVariantIsNil(): Boolean

  /**
   * Component [index] of the POD `real_t` value type a returned `Variant` holds (its payload, at
   * offset 8); such a Variant needs no destructor.
   */
  fun retVariantReal(index: Int): GodotRealStorage
}
