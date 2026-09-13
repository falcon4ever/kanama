package net.multigesture.kanama.types

/**
 * A color represented in RGBA format. Kanama value types are immutable snapshots; assign a new
 * value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Color
 */
data class Color(
  /**
   * The color's red component, typically on the range of 0 to 1.
   *
   * Generated from Godot docs: Color.r
   */
  val r: Float,
  /**
   * The color's green component, typically on the range of 0 to 1.
   *
   * Generated from Godot docs: Color.g
   */
  val g: Float,
  /**
   * The color's blue component, typically on the range of 0 to 1.
   *
   * Generated from Godot docs: Color.b
   */
  val b: Float,
  /**
   * The color's alpha component, typically on the range of 0 to 1. A value of 0 means that the
   * color is fully transparent. A value of 1 means that the color is fully opaque. Note: The alpha
   * channel is always stored with linear encoding, regardless of the encoding of the other color
   * channels. The `linear_to_srgb` and `srgb_to_linear` methods do not affect the alpha channel.
   *
   * Generated from Godot docs: Color.a
   */
  val a: Float = 1.0f,
) {
  // Match GDScript/C# `==`: signed zero equal (-0.0 == 0.0), NaN reflexive. See
  // wrapper-coverage-roadmap.md. hashCode canonicalizes signed zero so equal colors hash equal.
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is Color) return false
    return (r == other.r || (r.isNaN() && other.r.isNaN())) &&
      (g == other.g || (g.isNaN() && other.g.isNaN())) &&
      (b == other.b || (b.isNaN() && other.b.isNaN())) &&
      (a == other.a || (a.isNaN() && other.a.isNaN()))
  }

  override fun hashCode(): Int {
    var result = (r + 0.0f).hashCode()
    result = 31 * result + (g + 0.0f).hashCode()
    result = 31 * result + (b + 0.0f).hashCode()
    result = 31 * result + (a + 0.0f).hashCode()
    return result
  }
}
