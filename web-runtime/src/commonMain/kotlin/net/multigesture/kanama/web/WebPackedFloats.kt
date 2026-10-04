package net.multigesture.kanama.web

/**
 * Task 133 C3: one decimal on the Web text channels (a pulled property, a packed return, a signal
 * payload). GDScript prints NaN and the infinities as `nan` / `inf` / `-inf`, which Kotlin's
 * `toDouble()` rejects, and Kotlin prints `NaN` / `Infinity`, which GDScript's `split_floats` reads
 * as `0.0`; both sides spell them GDScript's way (the proxy's `_kanama_web_float`).
 */
internal object WebPackedFloats {
  /**
   * [value] as the proxy parses it: Kotlin's shortest round-trip decimal, or `nan`/`inf`/`-inf`.
   */
  fun encode(value: Double): String =
    when {
      value.isNaN() -> "nan"
      value == Double.POSITIVE_INFINITY -> "inf"
      value == Double.NEGATIVE_INFINITY -> "-inf"
      else -> value.toString()
    }

  /** A decimal as GDScript's `str()` spells it (`nan`, `inf`, `-inf` included). */
  fun decode(text: String): Double =
    when (val trimmed = text.trim()) {
      "nan",
      "-nan" -> Double.NaN
      "inf" -> Double.POSITIVE_INFINITY
      "-inf" -> Double.NEGATIVE_INFINITY
      else -> trimmed.toDouble()
    }
}
