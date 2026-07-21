package net.multigesture.kanama.types

data class Color(val r: Float, val g: Float, val b: Float, val a: Float = 1.0f) {
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
