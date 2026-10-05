package net.multigesture.kanama.binding.runtime

/**
 * Godot's utility functions (`@GlobalScope`: `print`, `randf`, `lerpf`, `str`, ...): the call seam
 * behind the generated common `GD` object (task 129 B). `GD` is one file every platform compiles
 * (`scripts/generate_api_wrapper.py` renders it from `extension_api.json`'s `utility_functions`);
 * only the call mechanism below differs: desktop/Android call the pointer
 * `variant_get_ptr_utility_function` returns through FFM, iOS through the C shim
 * (`kanama_ios_godot_get_utility_function` / `kanama_ios_godot_utility_call`).
 *
 * Two paths, chosen per function by the generator:
 * * a utility whose arguments and return are all `float` / `int` / `bool` (the math, `randf`,
 *   `seed`, ...) writes its arguments into the calling thread's [BuiltinFrame] (slots 1..N, as a
 *   builtin method does) and calls [BuiltinFrame.callUtility]: no allocation;
 * * every other one (a `Variant` or `String` argument, every vararg utility, a `String` / `Variant`
 *   / `Object` / packed return) goes through [UtilityCalls.call], which converts each argument and
 *   decodes the return.
 */
internal expect class UtilityFunction(name: String, hash: Long)

internal expect object UtilityCalls {
  /**
   * Call [fn] with [args]. Argument `i` is passed as a value of Variant type `argTypes[i]` (`0`,
   * and every argument past the array -- a vararg one -- is passed as a `Variant`); the supported
   * non-Variant types are `bool`, `int`, `float`, `String` and `PackedByteArray`. [retType] is the
   * Variant type of the return: `-1` for none (the call returns `null`), `0` for a `Variant`,
   * otherwise the typed return, decoded like a Variant return of `GodotObject.call` (an object is
   * returned owned, as `RefCounted.owned` for a reference-counted one).
   */
  fun call(fn: UtilityFunction, argTypes: IntArray, args: Array<out Any?>, retType: Int): Any?

  /**
   * Call the builtin [method] on [base], a Kotlin value of Variant type [baseType] (`String` for
   * `VT_STRING`, a `NodePath` for `VT_NODE_PATH`, a `ByteArray` for `VT_PACKED_BYTE_ARRAY`), or
   * with Godot's NULL instance when [base] is null (a static method). The arguments and the return
   * convert as for [call]. The base is built for the call and destroyed after it, so only a `const`
   * method may come here: a change a non-const method makes to its base would be lost (task 134 D2:
   * the boxed path of the String, NodePath and PackedByteArray methods; the value types keep the
   * allocation-free [BuiltinFrame]).
   */
  fun callMethod(
    method: BuiltinMethod,
    baseType: Int,
    base: Any?,
    argTypes: IntArray,
    args: Array<out Any?>,
    retType: Int,
  ): Any?
}
