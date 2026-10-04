package net.multigesture.kanama.types

/**
 * A color represented in RGBA format. Kanama value types are immutable snapshots; assign a new
 * value back to the Godot property after changing components.
 *
 * Generated from Godot docs: Color
 */
class Color
private constructor(
  internal val rawR: Float,
  internal val rawG: Float,
  internal val rawB: Float,
  internal val rawA: Float,
  @Suppress("UNUSED_PARAMETER") raw: RawStorage,
) {
  /** A color stored as Godot stores it, float32 per channel (in every build): each is rounded. */
  constructor(
    r: Double,
    g: Double,
    b: Double,
    a: Double = 1.0,
  ) : this(r.toFloat(), g.toFloat(), b.toFloat(), a.toFloat(), RawStorage)

  /** GDScript's `Color(1, 1, 1)`: integer channels. */
  constructor(
    r: Int,
    g: Int,
    b: Int,
    a: Int = 1,
  ) : this(r.toDouble(), g.toDouble(), b.toDouble(), a.toDouble())

  /** GDScript's `Color(1, 1, 1, 0.72)`: integer channels with a decimal alpha. */
  constructor(r: Int, g: Int, b: Int, a: Double) : this(r.toDouble(), g.toDouble(), b.toDouble(), a)

  /**
   * The color's red component, typically on the range of 0 to 1.
   *
   * Generated from Godot docs: Color.r
   */
  val r: Double
    get() = rawR.toDouble()

  /**
   * The color's green component, typically on the range of 0 to 1.
   *
   * Generated from Godot docs: Color.g
   */
  val g: Double
    get() = rawG.toDouble()

  /**
   * The color's blue component, typically on the range of 0 to 1.
   *
   * Generated from Godot docs: Color.b
   */
  val b: Double
    get() = rawB.toDouble()

  /**
   * The color's alpha component, typically on the range of 0 to 1. A value of 0 means that the
   * color is fully transparent. A value of 1 means that the color is fully opaque. Note: The alpha
   * channel is always stored with linear encoding, regardless of the encoding of the other color
   * channels. The `linear_to_srgb` and `srgb_to_linear` methods do not affect the alpha channel.
   *
   * Generated from Godot docs: Color.a
   */
  val a: Double
    get() = rawA.toDouble()

  operator fun component1(): Double = r

  operator fun component2(): Double = g

  operator fun component3(): Double = b

  operator fun component4(): Double = a

  /** This color with some channels replaced. */
  fun copy(r: Double = this.r, g: Double = this.g, b: Double = this.b, a: Double = this.a): Color =
    Color(r, g, b, a)

  // Godot's `==` on the stored channels (signed zero equal, -0.0 == 0.0); NaN equals NaN to keep
  // the JVM equals contract reflexive. hashCode canonicalizes signed zero so equal colors hash
  // equal.
  override fun equals(other: Any?): Boolean =
    this === other ||
      (other is Color &&
        storedEquals(rawR, other.rawR) &&
        storedEquals(rawG, other.rawG) &&
        storedEquals(rawB, other.rawB) &&
        storedEquals(rawA, other.rawA))

  override fun hashCode(): Int {
    var result = storedHash(rawR)
    result = 31 * result + storedHash(rawG)
    result = 31 * result + storedHash(rawB)
    result = 31 * result + storedHash(rawA)
    return result
  }

  /** Godot's `str(c)`: four decimals at most, `(1.0, 0.5, 0.0, 1.0)`. */
  override fun toString(): String =
    "(${godotColorChannelString(rawR)}, ${godotColorChannelString(rawG)}, " +
      "${godotColorChannelString(rawB)}, ${godotColorChannelString(rawA)})"

  internal companion object {
    /** A color from float32 channels (marshalling; no conversion). */
    fun raw(r: Float, g: Float, b: Float, a: Float): Color = Color(r, g, b, a, RawStorage)
  }
}
