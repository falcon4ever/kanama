package net.multigesture.kanama.types

import kotlin.math.abs

/**
 * iOS shadow of the desktop [ApproxMath] — Godot's fuzzy float comparison (`Math::is_equal_approx`
 * / `is_zero_approx`), replicated locally so the value-type helpers match the engine without a
 * per-component engine round-trip. Components use [real_t], currently single precision on iOS.
 * `CMP_EPSILON` is Godot's `0.00001`.
 */
internal val CMP_EPSILON: real_t = GodotReal.fromNumber(0.00001)

internal fun isEqualApprox(a: real_t, b: real_t): Boolean {
  if (a == b) return true
  var tolerance = CMP_EPSILON * abs(a)
  if (tolerance < CMP_EPSILON) tolerance = CMP_EPSILON
  return abs(a - b) < tolerance
}

internal fun isZeroApprox(value: real_t): Boolean = abs(value) < CMP_EPSILON
