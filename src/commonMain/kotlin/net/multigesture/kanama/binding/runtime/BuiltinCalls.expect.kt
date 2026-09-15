package net.multigesture.kanama.binding.runtime

import net.multigesture.kanama.types.GodotRealArray

/**
 * Value-type (builtin) method calls: the one facade the shared value-type bodies under
 * `src/commonMain/kotlin/net/multigesture/kanama/types/` call, so that `Transform3D.inverse()` and
 * friends have ONE body for every backend (task 104 step 2).
 *
 * This declaration IS the contract step 2 had to prove with a Python gate
 * (`scripts/check_builtin_calls_contract.py`, deleted with this commit): desktop implements it over
 * Panama/FFM (`variant_get_ptr_builtin_method` + a bound downcall handle), iOS over the C shim
 * (`kanama_ios_godot_builtin_call`), and the compiler now refuses either backend that is missing a
 * member or spells a parameter differently. Method pointers travel as `Long` on both platforms, so
 * a shared body never names a platform pointer type. An `actual object` may carry extra members —
 * both backends keep their private marshalling helpers.
 *
 * The argument variants are top-level [BArg] rather than members here, because an `expect`
 * classifier may not declare a constructor with property parameters.
 */
expect object BuiltinCalls {
  // Godot Variant type ids (Variant::Type) — must match the engine enum, and the [VariantType]
  // entries of the same name. `expect val` + `actual const val`: an `expect` declaration may not
  // carry an initializer, and the value stays a compile-time constant on both platforms.
  val VT_VECTOR2: Int
  val VT_VECTOR3: Int
  val VT_QUATERNION: Int
  val VT_BASIS: Int
  val VT_TRANSFORM3D: Int

  // Builtin arg tags (the KANAMA_IOS_PT_* enum in kanama_ios_shim.c). The desktop ptr-ABI is
  // positional and untyped — the callee knows the layout — so these are carried for the shared call
  // sites and the iOS shim, which dispatches on them.
  val PT_BOOL: Int
  val PT_INT64: Int
  val PT_FLOAT64: Int
  val PT_VECTOR2: Int
  val PT_VECTOR3: Int
  val PT_TRANSFORM3D: Int
  val PT_QUATERNION: Int

  /**
   * Resolve a builtin method of [variantType] by [method] name and signature [hash], as an opaque
   * pointer. Cache the result in a `by lazy` on the value type — resolution costs a StringName and
   * an engine lookup.
   */
  fun getBuiltinMethod(variantType: Int, method: String, hash: Long): Long

  /**
   * Call a builtin method whose base and return are value types laid out as `real_t` components
   * ([base] in, [retCount] values out), with optional [args].
   *
   * No default for [args]: an `actual` may not restate a default, so it would have to live here —
   * and the ANDROID lane compiles a copy of these sources with no common fragment at all (the
   * `*.expect.kt` files are skipped, the `actual ` modifiers stripped), where a default declared
   * here is simply gone. The five callers that pass no arguments spell `emptyList()`.
   */
  fun call(methodPtr: Long, base: GodotRealArray, retCount: Int, args: List<BArg>): GodotRealArray

  /**
   * No-arg builtin method whose base and return are the same value type laid out as `real_t`
   * components (inverse / transposed / orthonormalized / …).
   */
  fun callNoArgsFloat32(methodPtr: Long, base: GodotRealArray): GodotRealArray

  /**
   * Builtin method returning a scalar `float` (dot / length / determinant / …). Godot's GDExtension
   * ptr-ABI encodes a `float`-typed (Variant FLOAT) return as an 8-byte `double` regardless of the
   * engine's real_t precision — NOT a real_t, unlike value-type *components*.
   */
  fun callScalar(methodPtr: Long, base: GodotRealArray, args: List<BArg>): Double

  /**
   * Builtin method returning a `bool` (is_normalized / is_finite / …). Godot's ptr-ABI encodes a
   * bool return as a single `uint8_t` (`PtrToArg<bool>` = uint8), so decode one byte (≠ 0 → true).
   */
  fun callBool(methodPtr: Long, base: GodotRealArray, args: List<BArg>): Boolean

  /**
   * Builtin method returning an `int` (max_axis_index / …). Godot's ptr-ABI encodes an int return
   * as `int64_t` (`PtrToArg<int64_t>` is direct 8-byte), so decode a Long.
   */
  fun callInt(methodPtr: Long, base: GodotRealArray, args: List<BArg>): Long
}
