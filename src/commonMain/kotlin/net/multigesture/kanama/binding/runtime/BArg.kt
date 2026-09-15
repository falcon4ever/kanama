package net.multigesture.kanama.binding.runtime

import net.multigesture.kanama.types.GodotRealArray

/**
 * An argument to a builtin (value-type) method call — the value-type analogue of a ptrcall arg.
 *
 * Common code, not a member of [BuiltinCalls]: an `expect` classifier may not declare a constructor
 * with property parameters, so the four variants cannot live inside the `expect object`. They carry
 * no platform type anyway ([GodotRealArray] is the generated `real_t` array alias), so one
 * declaration serves both backends and the call sites keep spelling them `BArg.Floats(...)`.
 */
sealed interface BArg {
  /**
   * A struct value laid out as `real_t` [values] (Vector3/Basis/Transform3D/…); [tag] is its PT_*.
   */
  data class Floats(val tag: Int, val values: GodotRealArray) : BArg

  /** A `bool` arg (1-byte). */
  data class Bool(val value: Boolean) : BArg

  /**
   * A scalar `float`/`double` arg. Godot's ptr-ABI encodes a Variant FLOAT argument as an 8-byte
   * `double` regardless of the engine's real_t precision, so this is never a real_t.
   */
  data class Real(val value: Double) : BArg

  /** An `int` arg (`int64_t` at ptrcall) — Basis' EulerOrder, array indices, … */
  data class Int64(val value: Long) : BArg
}
