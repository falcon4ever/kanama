package net.multigesture.kanama.web

/**
 * One decimal on the Web text channels (a pulled property, a packed argument or return, a signal
 * argument, a builtin call, a generic call): the text of its IEEE-754 bits, the int64 of the
 * double. The proxy's `_kanama_web_float` / `_kanama_web_float_text` read and write the same form
 * (`PackedByteArray.encode_s64` / `decode_double`), so every value -- NaN, ±INF, -0, denormals --
 * crosses exactly in both directions. Task 134 D1 review S1: decimal text did not; Godot's
 * `String.to_float` is not correctly rounded (about half of random doubles came back wrong, by up
 * to 256 ulp; -0 became +0; 2.3e-308 became 0) and Kotlin/Wasm's `toDouble` is an ulp off in rare
 * cases.
 */
internal object WebPackedFloats {
  /** [value] as the text of its IEEE-754 bits. */
  fun encode(value: Double): String = value.toRawBits().toString()

  /** The double whose IEEE-754 bits [text] holds. */
  fun decode(text: String): Double = Double.fromBits(text.trim().toLong())
}
