package net.multigesture.kanama.types

import kotlin.math.abs

/**
 * Godot's fuzzy float comparison, replicated locally (exactly how Godot implements
 * `Math::is_equal_approx` / `is_zero_approx` in `core/math/math_funcs.h`) so the value-type
 * `isEqualApprox`/`isZeroApprox` helpers match the engine without a per-component engine
 * round-trip. `CMP_EPSILON` is Godot's `0.00001` (`core/math/math_defs.h`), the same for single and
 * double.
 *
 * Use these (and the per-type `isEqualApprox`/`isZeroApprox`) for geometry comparisons instead of
 * exact `==`: floating-point results from the engine (transforms, normalized vectors, `looking_at`,
 * …) rarely land on an exact literal.
 */
internal val CMP_EPSILON: real_t = GodotReal.fromNumber(0.00001)

internal fun isEqualApprox(a: real_t, b: real_t): Boolean {
  if (a == b) return true // includes -0.0 == 0.0; also short-circuits infinities
  var tolerance = CMP_EPSILON * abs(a)
  if (tolerance < CMP_EPSILON) tolerance = CMP_EPSILON
  return abs(a - b) < tolerance
}

internal fun isZeroApprox(value: real_t): Boolean = abs(value) < CMP_EPSILON
