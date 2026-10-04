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

  // ===== BEGIN GENERATED BUILTIN MEMBERS: Color (generate_builtin_ops.py) =====
  operator fun unaryMinus(): Color = raw(1.0f - rawR, 1.0f - rawG, 1.0f - rawB, 1.0f - rawA)

  operator fun unaryPlus(): Color = this

  operator fun times(scalar: Int): Color = times(scalar.toDouble())

  operator fun times(scalar: Long): Color = times(scalar.toDouble())

  operator fun div(scalar: Int): Color = div(scalar.toDouble())

  operator fun div(scalar: Long): Color = div(scalar.toDouble())

  operator fun times(scalar: Double): Color {
    /**
     * The HSV saturation of this color, on the range 0 to 1.
     *
     * Generated from Godot docs: Color.s
     */
    val s = scalar.toFloat()
    return raw(rawR * s, rawG * s, rawB * s, rawA * s)
  }

  operator fun div(scalar: Double): Color {
    /**
     * The HSV saturation of this color, on the range 0 to 1.
     *
     * Generated from Godot docs: Color.s
     */
    val s = scalar.toFloat()
    return raw(rawR / s, rawG / s, rawB / s, rawA / s)
  }

  operator fun plus(other: Color): Color =
    raw(rawR + other.rawR, rawG + other.rawG, rawB + other.rawB, rawA + other.rawA)

  operator fun minus(other: Color): Color =
    raw(rawR - other.rawR, rawG - other.rawG, rawB - other.rawB, rawA - other.rawA)

  operator fun times(other: Color): Color =
    raw(rawR * other.rawR, rawG * other.rawG, rawB * other.rawB, rawA * other.rawA)

  operator fun div(other: Color): Color =
    raw(rawR / other.rawR, rawG / other.rawG, rawB / other.rawB, rawA / other.rawA)

  /**
   * Returns the color converted to a 32-bit integer in ARGB format (each component is 8 bits). ARGB
   * is more compatible with DirectX.
   *
   * Generated from Godot docs: Color.to_argb32
   */
  fun toArgb32(): Long {
    val f = builtinFrame()
    f.put(0, this)
    f.call(ColorMethods.toArgb32, 0)
    return f.retLong()
  }

  /**
   * Returns the color converted to a 32-bit integer in ABGR format (each component is 8 bits). ABGR
   * is the reversed version of the default RGBA format.
   *
   * Generated from Godot docs: Color.to_abgr32
   */
  fun toAbgr32(): Long {
    val f = builtinFrame()
    f.put(0, this)
    f.call(ColorMethods.toAbgr32, 0)
    return f.retLong()
  }

  /**
   * Returns the color converted to a 32-bit integer in RGBA format (each component is 8 bits). RGBA
   * is Godot's default format. This method is the inverse of `hex`.
   *
   * Generated from Godot docs: Color.to_rgba32
   */
  fun toRgba32(): Long {
    val f = builtinFrame()
    f.put(0, this)
    f.call(ColorMethods.toRgba32, 0)
    return f.retLong()
  }

  /**
   * Returns the color converted to a 64-bit integer in ARGB format (each component is 16 bits).
   * ARGB is more compatible with DirectX.
   *
   * Generated from Godot docs: Color.to_argb64
   */
  fun toArgb64(): Long {
    val f = builtinFrame()
    f.put(0, this)
    f.call(ColorMethods.toArgb64, 0)
    return f.retLong()
  }

  /**
   * Returns the color converted to a 64-bit integer in ABGR format (each component is 16 bits).
   * ABGR is the reversed version of the default RGBA format.
   *
   * Generated from Godot docs: Color.to_abgr64
   */
  fun toAbgr64(): Long {
    val f = builtinFrame()
    f.put(0, this)
    f.call(ColorMethods.toAbgr64, 0)
    return f.retLong()
  }

  /**
   * Returns the color converted to a 64-bit integer in RGBA format (each component is 16 bits).
   * RGBA is Godot's default format. This method is the inverse of `hex64`.
   *
   * Generated from Godot docs: Color.to_rgba64
   */
  fun toRgba64(): Long {
    val f = builtinFrame()
    f.put(0, this)
    f.call(ColorMethods.toRgba64, 0)
    return f.retLong()
  }

  /**
   * Returns the color converted to an HTML hexadecimal color `String` in RGBA format, without the
   * hash (`#`) prefix. Setting `with_alpha` to `false`, excludes alpha from the hexadecimal string,
   * using RGB format instead of RGBA format.
   *
   * Generated from Godot docs: Color.to_html
   */
  fun toHtml(withAlpha: Boolean = true): String {
    val out = StringBuilder(8)
    godotHexByte(rawR, out)
    godotHexByte(rawG, out)
    godotHexByte(rawB, out)
    if (withAlpha) godotHexByte(rawA, out)
    return out.toString()
  }

  /**
   * Returns a new color with all components clamped between the components of `min` and `max`, by
   * running `@GlobalScope.clamp` on each component.
   *
   * Generated from Godot docs: Color.clamp
   */
  fun clamp(min: Color = Color(0.0, 0.0, 0.0, 0.0), max: Color = Color(1.0, 1.0, 1.0, 1.0)): Color =
    raw(
      godotClamp(rawR, min.rawR, max.rawR),
      godotClamp(rawG, min.rawG, max.rawG),
      godotClamp(rawB, min.rawB, max.rawB),
      godotClamp(rawA, min.rawA, max.rawA),
    )

  /**
   * Returns the color with its `r`, `g`, and `b` components inverted (`(1 - r, 1 - g, 1 - b, a)`).
   *
   * Generated from Godot docs: Color.inverted
   */
  fun inverted(): Color = raw(1.0f - rawR, 1.0f - rawG, 1.0f - rawB, rawA)

  /**
   * Returns the linear interpolation between this color's components and `to`'s components. The
   * interpolation factor `weight` should be between 0.0 and 1.0 (inclusive). See also
   * `@GlobalScope.lerp`.
   *
   * Generated from Godot docs: Color.lerp
   */
  fun lerp(to: Color, weight: Double): Color {
    val w = weight.toFloat()
    return raw(
      realLerpF(rawR, to.rawR, w),
      realLerpF(rawG, to.rawG, w),
      realLerpF(rawB, to.rawB, w),
      realLerpF(rawA, to.rawA, w),
    )
  }

  /**
   * Returns a new color resulting from making this color lighter by the specified `amount`, which
   * should be a ratio from 0.0 to 1.0. See also `darkened`.
   *
   * Generated from Godot docs: Color.lightened
   */
  fun lightened(amount: Double): Color {
    val f = builtinFrame()
    f.put(0, this)
    f.putDouble(1, amount)
    f.call(ColorMethods.lightened, 1)
    return f.retColor()
  }

  /**
   * Returns a new color resulting from making this color darker by the specified `amount` (ratio
   * from 0.0 to 1.0). See also `lightened`.
   *
   * Generated from Godot docs: Color.darkened
   */
  fun darkened(amount: Double): Color {
    val f = builtinFrame()
    f.put(0, this)
    f.putDouble(1, amount)
    f.call(ColorMethods.darkened, 1)
    return f.retColor()
  }

  /**
   * Returns a new color resulting from overlaying this color over the given color. In a painting
   * program, you can imagine it as the `over` color painted over this color (including alpha).
   *
   * Generated from Godot docs: Color.blend
   */
  fun blend(over: Color): Color {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, over)
    f.call(ColorMethods.blend, 1)
    return f.retColor()
  }

  /**
   * Returns the light intensity of the color, as a value between 0.0 and 1.0 (inclusive). This is
   * useful when determining light or dark color. Colors with a luminance smaller than 0.5 can be
   * generally considered dark. Note: `get_luminance` relies on the color using linear encoding to
   * return an accurate relative luminance value. If the color uses the default nonlinear sRGB
   * encoding, use `srgb_to_linear` to convert it to linear encoding first.
   *
   * Generated from Godot docs: Color.get_luminance
   */
  fun getLuminance(): Double = (0.2126f * rawR + 0.7152f * rawG + 0.0722f * rawB).toDouble()

  /**
   * Returns a copy of the color that uses linear encoding. This method requires the original color
   * to be encoded using the nonlinear sRGB transfer function (https://en.wikipedia.org/wiki/SRGB).
   * See also `linear_to_srgb` which performs the opposite operation. Note: The color's alpha
   * channel (`a`) is not affected. The alpha channel is always stored with linear encoding,
   * regardless of the color space of the other color channels.
   *
   * Generated from Godot docs: Color.srgb_to_linear
   */
  fun srgbToLinear(): Color {
    val f = builtinFrame()
    f.put(0, this)
    f.call(ColorMethods.srgbToLinear, 0)
    return f.retColor()
  }

  /**
   * Returns a copy of the color that is encoded using the nonlinear sRGB transfer function
   * (https://en.wikipedia.org/wiki/SRGB). This method requires the original color to use linear
   * encoding. See also `srgb_to_linear` which performs the opposite operation. Note: The color's
   * alpha channel (`a`) is not affected. The alpha channel is always stored with linear encoding,
   * regardless of the color space of the other color channels.
   *
   * Generated from Godot docs: Color.linear_to_srgb
   */
  fun linearToSrgb(): Color {
    val f = builtinFrame()
    f.put(0, this)
    f.call(ColorMethods.linearToSrgb, 0)
    return f.retColor()
  }

  /**
   * Returns `true` if this color and `to` are approximately equal, by running
   * `@GlobalScope.is_equal_approx` on each component.
   *
   * Generated from Godot docs: Color.is_equal_approx
   */
  fun isEqualApprox(to: Color): Boolean {
    val f = builtinFrame()
    f.put(0, this)
    f.put(1, to)
    f.call(ColorMethods.isEqualApprox, 1)
    return f.retBool()
  }

  // ===== END GENERATED BUILTIN MEMBERS: Color =====

  companion object {
    // ===== BEGIN GENERATED BUILTIN STATICS: Color (generate_builtin_ops.py) =====
    /**
     * Alice blue color.
     *
     * Generated from Godot docs: Color.ALICE_BLUE
     */
    val ALICE_BLUE: Color = raw(0.9411765f, 0.972549f, 1f, 1f)

    /**
     * Antique white color.
     *
     * Generated from Godot docs: Color.ANTIQUE_WHITE
     */
    val ANTIQUE_WHITE: Color = raw(0.98039216f, 0.92156863f, 0.84313726f, 1f)

    /**
     * Aqua color.
     *
     * Generated from Godot docs: Color.AQUA
     */
    val AQUA: Color = raw(0f, 1f, 1f, 1f)

    /**
     * Aquamarine color.
     *
     * Generated from Godot docs: Color.AQUAMARINE
     */
    val AQUAMARINE: Color = raw(0.49803922f, 1f, 0.83137256f, 1f)

    /**
     * Azure color.
     *
     * Generated from Godot docs: Color.AZURE
     */
    val AZURE: Color = raw(0.9411765f, 1f, 1f, 1f)

    /**
     * Beige color.
     *
     * Generated from Godot docs: Color.BEIGE
     */
    val BEIGE: Color = raw(0.9607843f, 0.9607843f, 0.8627451f, 1f)

    /**
     * Bisque color.
     *
     * Generated from Godot docs: Color.BISQUE
     */
    val BISQUE: Color = raw(1f, 0.89411765f, 0.76862746f, 1f)

    /**
     * Black color. In GDScript, this is the default value of any color.
     *
     * Generated from Godot docs: Color.BLACK
     */
    val BLACK: Color = raw(0f, 0f, 0f, 1f)

    /**
     * Blanched almond color.
     *
     * Generated from Godot docs: Color.BLANCHED_ALMOND
     */
    val BLANCHED_ALMOND: Color = raw(1f, 0.92156863f, 0.8039216f, 1f)

    /**
     * Blue color.
     *
     * Generated from Godot docs: Color.BLUE
     */
    val BLUE: Color = raw(0f, 0f, 1f, 1f)

    /**
     * Blue violet color.
     *
     * Generated from Godot docs: Color.BLUE_VIOLET
     */
    val BLUE_VIOLET: Color = raw(0.5411765f, 0.16862746f, 0.8862745f, 1f)

    /**
     * Brown color.
     *
     * Generated from Godot docs: Color.BROWN
     */
    val BROWN: Color = raw(0.64705884f, 0.16470589f, 0.16470589f, 1f)

    /**
     * Burlywood color.
     *
     * Generated from Godot docs: Color.BURLYWOOD
     */
    val BURLYWOOD: Color = raw(0.87058824f, 0.72156864f, 0.5294118f, 1f)

    /**
     * Cadet blue color.
     *
     * Generated from Godot docs: Color.CADET_BLUE
     */
    val CADET_BLUE: Color = raw(0.37254903f, 0.61960787f, 0.627451f, 1f)

    /**
     * Chartreuse color.
     *
     * Generated from Godot docs: Color.CHARTREUSE
     */
    val CHARTREUSE: Color = raw(0.49803922f, 1f, 0f, 1f)

    /**
     * Chocolate color.
     *
     * Generated from Godot docs: Color.CHOCOLATE
     */
    val CHOCOLATE: Color = raw(0.8235294f, 0.4117647f, 0.11764706f, 1f)

    /**
     * Coral color.
     *
     * Generated from Godot docs: Color.CORAL
     */
    val CORAL: Color = raw(1f, 0.49803922f, 0.3137255f, 1f)

    /**
     * Cornflower blue color.
     *
     * Generated from Godot docs: Color.CORNFLOWER_BLUE
     */
    val CORNFLOWER_BLUE: Color = raw(0.39215687f, 0.58431375f, 0.92941177f, 1f)

    /**
     * Cornsilk color.
     *
     * Generated from Godot docs: Color.CORNSILK
     */
    val CORNSILK: Color = raw(1f, 0.972549f, 0.8627451f, 1f)

    /**
     * Crimson color.
     *
     * Generated from Godot docs: Color.CRIMSON
     */
    val CRIMSON: Color = raw(0.8627451f, 0.078431375f, 0.23529412f, 1f)

    /**
     * Cyan color.
     *
     * Generated from Godot docs: Color.CYAN
     */
    val CYAN: Color = raw(0f, 1f, 1f, 1f)

    /**
     * Dark blue color.
     *
     * Generated from Godot docs: Color.DARK_BLUE
     */
    val DARK_BLUE: Color = raw(0f, 0f, 0.54509807f, 1f)

    /**
     * Dark cyan color.
     *
     * Generated from Godot docs: Color.DARK_CYAN
     */
    val DARK_CYAN: Color = raw(0f, 0.54509807f, 0.54509807f, 1f)

    /**
     * Dark goldenrod color.
     *
     * Generated from Godot docs: Color.DARK_GOLDENROD
     */
    val DARK_GOLDENROD: Color = raw(0.72156864f, 0.5254902f, 0.043137256f, 1f)

    /**
     * Dark gray color.
     *
     * Generated from Godot docs: Color.DARK_GRAY
     */
    val DARK_GRAY: Color = raw(0.6627451f, 0.6627451f, 0.6627451f, 1f)

    /**
     * Dark green color.
     *
     * Generated from Godot docs: Color.DARK_GREEN
     */
    val DARK_GREEN: Color = raw(0f, 0.39215687f, 0f, 1f)

    /**
     * Dark khaki color.
     *
     * Generated from Godot docs: Color.DARK_KHAKI
     */
    val DARK_KHAKI: Color = raw(0.7411765f, 0.7176471f, 0.41960785f, 1f)

    /**
     * Dark magenta color.
     *
     * Generated from Godot docs: Color.DARK_MAGENTA
     */
    val DARK_MAGENTA: Color = raw(0.54509807f, 0f, 0.54509807f, 1f)

    /**
     * Dark olive green color.
     *
     * Generated from Godot docs: Color.DARK_OLIVE_GREEN
     */
    val DARK_OLIVE_GREEN: Color = raw(0.33333334f, 0.41960785f, 0.18431373f, 1f)

    /**
     * Dark orange color.
     *
     * Generated from Godot docs: Color.DARK_ORANGE
     */
    val DARK_ORANGE: Color = raw(1f, 0.54901963f, 0f, 1f)

    /**
     * Dark orchid color.
     *
     * Generated from Godot docs: Color.DARK_ORCHID
     */
    val DARK_ORCHID: Color = raw(0.6f, 0.19607843f, 0.8f, 1f)

    /**
     * Dark red color.
     *
     * Generated from Godot docs: Color.DARK_RED
     */
    val DARK_RED: Color = raw(0.54509807f, 0f, 0f, 1f)

    /**
     * Dark salmon color.
     *
     * Generated from Godot docs: Color.DARK_SALMON
     */
    val DARK_SALMON: Color = raw(0.9137255f, 0.5882353f, 0.47843137f, 1f)

    /**
     * Dark sea green color.
     *
     * Generated from Godot docs: Color.DARK_SEA_GREEN
     */
    val DARK_SEA_GREEN: Color = raw(0.56078434f, 0.7372549f, 0.56078434f, 1f)

    /**
     * Dark slate blue color.
     *
     * Generated from Godot docs: Color.DARK_SLATE_BLUE
     */
    val DARK_SLATE_BLUE: Color = raw(0.28235295f, 0.23921569f, 0.54509807f, 1f)

    /**
     * Dark slate gray color.
     *
     * Generated from Godot docs: Color.DARK_SLATE_GRAY
     */
    val DARK_SLATE_GRAY: Color = raw(0.18431373f, 0.30980393f, 0.30980393f, 1f)

    /**
     * Dark turquoise color.
     *
     * Generated from Godot docs: Color.DARK_TURQUOISE
     */
    val DARK_TURQUOISE: Color = raw(0f, 0.80784315f, 0.81960785f, 1f)

    /**
     * Dark violet color.
     *
     * Generated from Godot docs: Color.DARK_VIOLET
     */
    val DARK_VIOLET: Color = raw(0.5803922f, 0f, 0.827451f, 1f)

    /**
     * Deep pink color.
     *
     * Generated from Godot docs: Color.DEEP_PINK
     */
    val DEEP_PINK: Color = raw(1f, 0.078431375f, 0.5764706f, 1f)

    /**
     * Deep sky blue color.
     *
     * Generated from Godot docs: Color.DEEP_SKY_BLUE
     */
    val DEEP_SKY_BLUE: Color = raw(0f, 0.7490196f, 1f, 1f)

    /**
     * Dim gray color.
     *
     * Generated from Godot docs: Color.DIM_GRAY
     */
    val DIM_GRAY: Color = raw(0.4117647f, 0.4117647f, 0.4117647f, 1f)

    /**
     * Dodger blue color.
     *
     * Generated from Godot docs: Color.DODGER_BLUE
     */
    val DODGER_BLUE: Color = raw(0.11764706f, 0.5647059f, 1f, 1f)

    /**
     * Firebrick color.
     *
     * Generated from Godot docs: Color.FIREBRICK
     */
    val FIREBRICK: Color = raw(0.69803923f, 0.13333334f, 0.13333334f, 1f)

    /**
     * Floral white color.
     *
     * Generated from Godot docs: Color.FLORAL_WHITE
     */
    val FLORAL_WHITE: Color = raw(1f, 0.98039216f, 0.9411765f, 1f)

    /**
     * Forest green color.
     *
     * Generated from Godot docs: Color.FOREST_GREEN
     */
    val FOREST_GREEN: Color = raw(0.13333334f, 0.54509807f, 0.13333334f, 1f)

    /**
     * Fuchsia color.
     *
     * Generated from Godot docs: Color.FUCHSIA
     */
    val FUCHSIA: Color = raw(1f, 0f, 1f, 1f)

    /**
     * Gainsboro color.
     *
     * Generated from Godot docs: Color.GAINSBORO
     */
    val GAINSBORO: Color = raw(0.8627451f, 0.8627451f, 0.8627451f, 1f)

    /**
     * Ghost white color.
     *
     * Generated from Godot docs: Color.GHOST_WHITE
     */
    val GHOST_WHITE: Color = raw(0.972549f, 0.972549f, 1f, 1f)

    /**
     * Gold color.
     *
     * Generated from Godot docs: Color.GOLD
     */
    val GOLD: Color = raw(1f, 0.84313726f, 0f, 1f)

    /**
     * Goldenrod color.
     *
     * Generated from Godot docs: Color.GOLDENROD
     */
    val GOLDENROD: Color = raw(0.85490197f, 0.64705884f, 0.1254902f, 1f)

    /**
     * Gray color.
     *
     * Generated from Godot docs: Color.GRAY
     */
    val GRAY: Color = raw(0.74509805f, 0.74509805f, 0.74509805f, 1f)

    /**
     * Green color.
     *
     * Generated from Godot docs: Color.GREEN
     */
    val GREEN: Color = raw(0f, 1f, 0f, 1f)

    /**
     * Green yellow color.
     *
     * Generated from Godot docs: Color.GREEN_YELLOW
     */
    val GREEN_YELLOW: Color = raw(0.6784314f, 1f, 0.18431373f, 1f)

    /**
     * Honeydew color.
     *
     * Generated from Godot docs: Color.HONEYDEW
     */
    val HONEYDEW: Color = raw(0.9411765f, 1f, 0.9411765f, 1f)

    /**
     * Hot pink color.
     *
     * Generated from Godot docs: Color.HOT_PINK
     */
    val HOT_PINK: Color = raw(1f, 0.4117647f, 0.7058824f, 1f)

    /**
     * Indian red color.
     *
     * Generated from Godot docs: Color.INDIAN_RED
     */
    val INDIAN_RED: Color = raw(0.8039216f, 0.36078432f, 0.36078432f, 1f)

    /**
     * Indigo color.
     *
     * Generated from Godot docs: Color.INDIGO
     */
    val INDIGO: Color = raw(0.29411766f, 0f, 0.50980395f, 1f)

    /**
     * Ivory color.
     *
     * Generated from Godot docs: Color.IVORY
     */
    val IVORY: Color = raw(1f, 1f, 0.9411765f, 1f)

    /**
     * Khaki color.
     *
     * Generated from Godot docs: Color.KHAKI
     */
    val KHAKI: Color = raw(0.9411765f, 0.9019608f, 0.54901963f, 1f)

    /**
     * Lavender color.
     *
     * Generated from Godot docs: Color.LAVENDER
     */
    val LAVENDER: Color = raw(0.9019608f, 0.9019608f, 0.98039216f, 1f)

    /**
     * Lavender blush color.
     *
     * Generated from Godot docs: Color.LAVENDER_BLUSH
     */
    val LAVENDER_BLUSH: Color = raw(1f, 0.9411765f, 0.9607843f, 1f)

    /**
     * Lawn green color.
     *
     * Generated from Godot docs: Color.LAWN_GREEN
     */
    val LAWN_GREEN: Color = raw(0.4862745f, 0.9882353f, 0f, 1f)

    /**
     * Lemon chiffon color.
     *
     * Generated from Godot docs: Color.LEMON_CHIFFON
     */
    val LEMON_CHIFFON: Color = raw(1f, 0.98039216f, 0.8039216f, 1f)

    /**
     * Light blue color.
     *
     * Generated from Godot docs: Color.LIGHT_BLUE
     */
    val LIGHT_BLUE: Color = raw(0.6784314f, 0.84705883f, 0.9019608f, 1f)

    /**
     * Light coral color.
     *
     * Generated from Godot docs: Color.LIGHT_CORAL
     */
    val LIGHT_CORAL: Color = raw(0.9411765f, 0.5019608f, 0.5019608f, 1f)

    /**
     * Light cyan color.
     *
     * Generated from Godot docs: Color.LIGHT_CYAN
     */
    val LIGHT_CYAN: Color = raw(0.8784314f, 1f, 1f, 1f)

    /**
     * Light goldenrod color.
     *
     * Generated from Godot docs: Color.LIGHT_GOLDENROD
     */
    val LIGHT_GOLDENROD: Color = raw(0.98039216f, 0.98039216f, 0.8235294f, 1f)

    /**
     * Light gray color.
     *
     * Generated from Godot docs: Color.LIGHT_GRAY
     */
    val LIGHT_GRAY: Color = raw(0.827451f, 0.827451f, 0.827451f, 1f)

    /**
     * Light green color.
     *
     * Generated from Godot docs: Color.LIGHT_GREEN
     */
    val LIGHT_GREEN: Color = raw(0.5647059f, 0.93333334f, 0.5647059f, 1f)

    /**
     * Light pink color.
     *
     * Generated from Godot docs: Color.LIGHT_PINK
     */
    val LIGHT_PINK: Color = raw(1f, 0.7137255f, 0.75686276f, 1f)

    /**
     * Light salmon color.
     *
     * Generated from Godot docs: Color.LIGHT_SALMON
     */
    val LIGHT_SALMON: Color = raw(1f, 0.627451f, 0.47843137f, 1f)

    /**
     * Light sea green color.
     *
     * Generated from Godot docs: Color.LIGHT_SEA_GREEN
     */
    val LIGHT_SEA_GREEN: Color = raw(0.1254902f, 0.69803923f, 0.6666667f, 1f)

    /**
     * Light sky blue color.
     *
     * Generated from Godot docs: Color.LIGHT_SKY_BLUE
     */
    val LIGHT_SKY_BLUE: Color = raw(0.5294118f, 0.80784315f, 0.98039216f, 1f)

    /**
     * Light slate gray color.
     *
     * Generated from Godot docs: Color.LIGHT_SLATE_GRAY
     */
    val LIGHT_SLATE_GRAY: Color = raw(0.46666667f, 0.53333336f, 0.6f, 1f)

    /**
     * Light steel blue color.
     *
     * Generated from Godot docs: Color.LIGHT_STEEL_BLUE
     */
    val LIGHT_STEEL_BLUE: Color = raw(0.6901961f, 0.76862746f, 0.87058824f, 1f)

    /**
     * Light yellow color.
     *
     * Generated from Godot docs: Color.LIGHT_YELLOW
     */
    val LIGHT_YELLOW: Color = raw(1f, 1f, 0.8784314f, 1f)

    /**
     * Lime color.
     *
     * Generated from Godot docs: Color.LIME
     */
    val LIME: Color = raw(0f, 1f, 0f, 1f)

    /**
     * Lime green color.
     *
     * Generated from Godot docs: Color.LIME_GREEN
     */
    val LIME_GREEN: Color = raw(0.19607843f, 0.8039216f, 0.19607843f, 1f)

    /**
     * Linen color.
     *
     * Generated from Godot docs: Color.LINEN
     */
    val LINEN: Color = raw(0.98039216f, 0.9411765f, 0.9019608f, 1f)

    /**
     * Magenta color.
     *
     * Generated from Godot docs: Color.MAGENTA
     */
    val MAGENTA: Color = raw(1f, 0f, 1f, 1f)

    /**
     * Maroon color.
     *
     * Generated from Godot docs: Color.MAROON
     */
    val MAROON: Color = raw(0.6901961f, 0.1882353f, 0.3764706f, 1f)

    /**
     * Medium aquamarine color.
     *
     * Generated from Godot docs: Color.MEDIUM_AQUAMARINE
     */
    val MEDIUM_AQUAMARINE: Color = raw(0.4f, 0.8039216f, 0.6666667f, 1f)

    /**
     * Medium blue color.
     *
     * Generated from Godot docs: Color.MEDIUM_BLUE
     */
    val MEDIUM_BLUE: Color = raw(0f, 0f, 0.8039216f, 1f)

    /**
     * Medium orchid color.
     *
     * Generated from Godot docs: Color.MEDIUM_ORCHID
     */
    val MEDIUM_ORCHID: Color = raw(0.7294118f, 0.33333334f, 0.827451f, 1f)

    /**
     * Medium purple color.
     *
     * Generated from Godot docs: Color.MEDIUM_PURPLE
     */
    val MEDIUM_PURPLE: Color = raw(0.5764706f, 0.4392157f, 0.85882354f, 1f)

    /**
     * Medium sea green color.
     *
     * Generated from Godot docs: Color.MEDIUM_SEA_GREEN
     */
    val MEDIUM_SEA_GREEN: Color = raw(0.23529412f, 0.7019608f, 0.44313726f, 1f)

    /**
     * Medium slate blue color.
     *
     * Generated from Godot docs: Color.MEDIUM_SLATE_BLUE
     */
    val MEDIUM_SLATE_BLUE: Color = raw(0.48235294f, 0.40784314f, 0.93333334f, 1f)

    /**
     * Medium spring green color.
     *
     * Generated from Godot docs: Color.MEDIUM_SPRING_GREEN
     */
    val MEDIUM_SPRING_GREEN: Color = raw(0f, 0.98039216f, 0.6039216f, 1f)

    /**
     * Medium turquoise color.
     *
     * Generated from Godot docs: Color.MEDIUM_TURQUOISE
     */
    val MEDIUM_TURQUOISE: Color = raw(0.28235295f, 0.81960785f, 0.8f, 1f)

    /**
     * Medium violet red color.
     *
     * Generated from Godot docs: Color.MEDIUM_VIOLET_RED
     */
    val MEDIUM_VIOLET_RED: Color = raw(0.78039217f, 0.08235294f, 0.52156866f, 1f)

    /**
     * Midnight blue color.
     *
     * Generated from Godot docs: Color.MIDNIGHT_BLUE
     */
    val MIDNIGHT_BLUE: Color = raw(0.09803922f, 0.09803922f, 0.4392157f, 1f)

    /**
     * Mint cream color.
     *
     * Generated from Godot docs: Color.MINT_CREAM
     */
    val MINT_CREAM: Color = raw(0.9607843f, 1f, 0.98039216f, 1f)

    /**
     * Misty rose color.
     *
     * Generated from Godot docs: Color.MISTY_ROSE
     */
    val MISTY_ROSE: Color = raw(1f, 0.89411765f, 0.88235295f, 1f)

    /**
     * Moccasin color.
     *
     * Generated from Godot docs: Color.MOCCASIN
     */
    val MOCCASIN: Color = raw(1f, 0.89411765f, 0.70980394f, 1f)

    /**
     * Navajo white color.
     *
     * Generated from Godot docs: Color.NAVAJO_WHITE
     */
    val NAVAJO_WHITE: Color = raw(1f, 0.87058824f, 0.6784314f, 1f)

    /**
     * Navy blue color.
     *
     * Generated from Godot docs: Color.NAVY_BLUE
     */
    val NAVY_BLUE: Color = raw(0f, 0f, 0.5019608f, 1f)

    /**
     * Old lace color.
     *
     * Generated from Godot docs: Color.OLD_LACE
     */
    val OLD_LACE: Color = raw(0.99215686f, 0.9607843f, 0.9019608f, 1f)

    /**
     * Olive color.
     *
     * Generated from Godot docs: Color.OLIVE
     */
    val OLIVE: Color = raw(0.5019608f, 0.5019608f, 0f, 1f)

    /**
     * Olive drab color.
     *
     * Generated from Godot docs: Color.OLIVE_DRAB
     */
    val OLIVE_DRAB: Color = raw(0.41960785f, 0.5568628f, 0.13725491f, 1f)

    /**
     * Orange color.
     *
     * Generated from Godot docs: Color.ORANGE
     */
    val ORANGE: Color = raw(1f, 0.64705884f, 0f, 1f)

    /**
     * Orange red color.
     *
     * Generated from Godot docs: Color.ORANGE_RED
     */
    val ORANGE_RED: Color = raw(1f, 0.27058825f, 0f, 1f)

    /**
     * Orchid color.
     *
     * Generated from Godot docs: Color.ORCHID
     */
    val ORCHID: Color = raw(0.85490197f, 0.4392157f, 0.8392157f, 1f)

    /**
     * Pale goldenrod color.
     *
     * Generated from Godot docs: Color.PALE_GOLDENROD
     */
    val PALE_GOLDENROD: Color = raw(0.93333334f, 0.9098039f, 0.6666667f, 1f)

    /**
     * Pale green color.
     *
     * Generated from Godot docs: Color.PALE_GREEN
     */
    val PALE_GREEN: Color = raw(0.59607846f, 0.9843137f, 0.59607846f, 1f)

    /**
     * Pale turquoise color.
     *
     * Generated from Godot docs: Color.PALE_TURQUOISE
     */
    val PALE_TURQUOISE: Color = raw(0.6862745f, 0.93333334f, 0.93333334f, 1f)

    /**
     * Pale violet red color.
     *
     * Generated from Godot docs: Color.PALE_VIOLET_RED
     */
    val PALE_VIOLET_RED: Color = raw(0.85882354f, 0.4392157f, 0.5764706f, 1f)

    /**
     * Papaya whip color.
     *
     * Generated from Godot docs: Color.PAPAYA_WHIP
     */
    val PAPAYA_WHIP: Color = raw(1f, 0.9372549f, 0.8352941f, 1f)

    /**
     * Peach puff color.
     *
     * Generated from Godot docs: Color.PEACH_PUFF
     */
    val PEACH_PUFF: Color = raw(1f, 0.85490197f, 0.7254902f, 1f)

    /**
     * Peru color.
     *
     * Generated from Godot docs: Color.PERU
     */
    val PERU: Color = raw(0.8039216f, 0.52156866f, 0.24705882f, 1f)

    /**
     * Pink color.
     *
     * Generated from Godot docs: Color.PINK
     */
    val PINK: Color = raw(1f, 0.7529412f, 0.79607844f, 1f)

    /**
     * Plum color.
     *
     * Generated from Godot docs: Color.PLUM
     */
    val PLUM: Color = raw(0.8666667f, 0.627451f, 0.8666667f, 1f)

    /**
     * Powder blue color.
     *
     * Generated from Godot docs: Color.POWDER_BLUE
     */
    val POWDER_BLUE: Color = raw(0.6901961f, 0.8784314f, 0.9019608f, 1f)

    /**
     * Purple color.
     *
     * Generated from Godot docs: Color.PURPLE
     */
    val PURPLE: Color = raw(0.627451f, 0.1254902f, 0.9411765f, 1f)

    /**
     * Rebecca purple color.
     *
     * Generated from Godot docs: Color.REBECCA_PURPLE
     */
    val REBECCA_PURPLE: Color = raw(0.4f, 0.2f, 0.6f, 1f)

    /**
     * Red color.
     *
     * Generated from Godot docs: Color.RED
     */
    val RED: Color = raw(1f, 0f, 0f, 1f)

    /**
     * Rosy brown color.
     *
     * Generated from Godot docs: Color.ROSY_BROWN
     */
    val ROSY_BROWN: Color = raw(0.7372549f, 0.56078434f, 0.56078434f, 1f)

    /**
     * Royal blue color.
     *
     * Generated from Godot docs: Color.ROYAL_BLUE
     */
    val ROYAL_BLUE: Color = raw(0.25490198f, 0.4117647f, 0.88235295f, 1f)

    /**
     * Saddle brown color.
     *
     * Generated from Godot docs: Color.SADDLE_BROWN
     */
    val SADDLE_BROWN: Color = raw(0.54509807f, 0.27058825f, 0.07450981f, 1f)

    /**
     * Salmon color.
     *
     * Generated from Godot docs: Color.SALMON
     */
    val SALMON: Color = raw(0.98039216f, 0.5019608f, 0.44705883f, 1f)

    /**
     * Sandy brown color.
     *
     * Generated from Godot docs: Color.SANDY_BROWN
     */
    val SANDY_BROWN: Color = raw(0.95686275f, 0.6431373f, 0.3764706f, 1f)

    /**
     * Sea green color.
     *
     * Generated from Godot docs: Color.SEA_GREEN
     */
    val SEA_GREEN: Color = raw(0.18039216f, 0.54509807f, 0.34117648f, 1f)

    /**
     * Seashell color.
     *
     * Generated from Godot docs: Color.SEASHELL
     */
    val SEASHELL: Color = raw(1f, 0.9607843f, 0.93333334f, 1f)

    /**
     * Sienna color.
     *
     * Generated from Godot docs: Color.SIENNA
     */
    val SIENNA: Color = raw(0.627451f, 0.32156864f, 0.1764706f, 1f)

    /**
     * Silver color.
     *
     * Generated from Godot docs: Color.SILVER
     */
    val SILVER: Color = raw(0.7529412f, 0.7529412f, 0.7529412f, 1f)

    /**
     * Sky blue color.
     *
     * Generated from Godot docs: Color.SKY_BLUE
     */
    val SKY_BLUE: Color = raw(0.5294118f, 0.80784315f, 0.92156863f, 1f)

    /**
     * Slate blue color.
     *
     * Generated from Godot docs: Color.SLATE_BLUE
     */
    val SLATE_BLUE: Color = raw(0.41568628f, 0.3529412f, 0.8039216f, 1f)

    /**
     * Slate gray color.
     *
     * Generated from Godot docs: Color.SLATE_GRAY
     */
    val SLATE_GRAY: Color = raw(0.4392157f, 0.5019608f, 0.5647059f, 1f)

    /**
     * Snow color.
     *
     * Generated from Godot docs: Color.SNOW
     */
    val SNOW: Color = raw(1f, 0.98039216f, 0.98039216f, 1f)

    /**
     * Spring green color.
     *
     * Generated from Godot docs: Color.SPRING_GREEN
     */
    val SPRING_GREEN: Color = raw(0f, 1f, 0.49803922f, 1f)

    /**
     * Steel blue color.
     *
     * Generated from Godot docs: Color.STEEL_BLUE
     */
    val STEEL_BLUE: Color = raw(0.27450982f, 0.50980395f, 0.7058824f, 1f)

    /**
     * Tan color.
     *
     * Generated from Godot docs: Color.TAN
     */
    val TAN: Color = raw(0.8235294f, 0.7058824f, 0.54901963f, 1f)

    /**
     * Teal color.
     *
     * Generated from Godot docs: Color.TEAL
     */
    val TEAL: Color = raw(0f, 0.5019608f, 0.5019608f, 1f)

    /**
     * Thistle color.
     *
     * Generated from Godot docs: Color.THISTLE
     */
    val THISTLE: Color = raw(0.84705883f, 0.7490196f, 0.84705883f, 1f)

    /**
     * Tomato color.
     *
     * Generated from Godot docs: Color.TOMATO
     */
    val TOMATO: Color = raw(1f, 0.3882353f, 0.2784314f, 1f)

    /**
     * Transparent color (white with zero alpha).
     *
     * Generated from Godot docs: Color.TRANSPARENT
     */
    val TRANSPARENT: Color = raw(1f, 1f, 1f, 0f)

    /**
     * Turquoise color.
     *
     * Generated from Godot docs: Color.TURQUOISE
     */
    val TURQUOISE: Color = raw(0.2509804f, 0.8784314f, 0.8156863f, 1f)

    /**
     * Violet color.
     *
     * Generated from Godot docs: Color.VIOLET
     */
    val VIOLET: Color = raw(0.93333334f, 0.50980395f, 0.93333334f, 1f)

    /**
     * Web gray color.
     *
     * Generated from Godot docs: Color.WEB_GRAY
     */
    val WEB_GRAY: Color = raw(0.5019608f, 0.5019608f, 0.5019608f, 1f)

    /**
     * Web green color.
     *
     * Generated from Godot docs: Color.WEB_GREEN
     */
    val WEB_GREEN: Color = raw(0f, 0.5019608f, 0f, 1f)

    /**
     * Web maroon color.
     *
     * Generated from Godot docs: Color.WEB_MAROON
     */
    val WEB_MAROON: Color = raw(0.5019608f, 0f, 0f, 1f)

    /**
     * Web purple color.
     *
     * Generated from Godot docs: Color.WEB_PURPLE
     */
    val WEB_PURPLE: Color = raw(0.5019608f, 0f, 0.5019608f, 1f)

    /**
     * Wheat color.
     *
     * Generated from Godot docs: Color.WHEAT
     */
    val WHEAT: Color = raw(0.9607843f, 0.87058824f, 0.7019608f, 1f)

    /**
     * White color.
     *
     * Generated from Godot docs: Color.WHITE
     */
    val WHITE: Color = raw(1f, 1f, 1f, 1f)

    /**
     * White smoke color.
     *
     * Generated from Godot docs: Color.WHITE_SMOKE
     */
    val WHITE_SMOKE: Color = raw(0.9607843f, 0.9607843f, 0.9607843f, 1f)

    /**
     * Yellow color.
     *
     * Generated from Godot docs: Color.YELLOW
     */
    val YELLOW: Color = raw(1f, 1f, 0f, 1f)

    /**
     * Yellow green color.
     *
     * Generated from Godot docs: Color.YELLOW_GREEN
     */
    val YELLOW_GREEN: Color = raw(0.6039216f, 0.8039216f, 0.19607843f, 1f)

    /**
     * Returns the `Color` associated with the provided `hex` integer in 32-bit RGBA format (8 bits
     * per channel). This method is the inverse of `to_rgba32`. In GDScript and C#, the `int` is
     * best visualized with hexadecimal notation (`"0x"` prefix, making it `"0xRRGGBBAA"`).
     *
     * Generated from Godot docs: Color.hex
     */
    fun hex(hex: Long): Color {
      val f = builtinFrame()
      f.putLong(1, hex)
      f.callStatic(ColorMethods.hex, 1)
      return f.retColor()
    }

    /**
     * Returns the `Color` associated with the provided `hex` integer in 64-bit RGBA format (16 bits
     * per channel). This method is the inverse of `to_rgba64`. In GDScript and C#, the `int` is
     * best visualized with hexadecimal notation (`"0x"` prefix, making it `"0xRRRRGGGGBBBBAAAA"`).
     *
     * Generated from Godot docs: Color.hex64
     */
    fun hex64(hex: Long): Color {
      val f = builtinFrame()
      f.putLong(1, hex)
      f.callStatic(ColorMethods.hex64, 1)
      return f.retColor()
    }

    /**
     * Returns a new color from `rgba`, an HTML hexadecimal color string. `rgba` is not
     * case-sensitive, and may be prefixed by a hash sign (`#`). `rgba` must be a valid three-digit
     * or six-digit hexadecimal color string, and may contain an alpha channel value. If `rgba` does
     * not contain an alpha channel value, an alpha channel value of 1.0 is applied. If `rgba` is
     * invalid, returns an empty color.
     *
     * Generated from Godot docs: Color.html
     */
    fun html(rgba: String): Color {
      val f = builtinFrame()
      f.putString(1, rgba)
      f.callStatic(ColorMethods.html, 1)
      return f.retColor()
    }

    /**
     * Returns `true` if `color` is a valid HTML hexadecimal color string. The string must be a
     * hexadecimal value (case-insensitive) of either 3, 4, 6 or 8 digits, and may be prefixed by a
     * hash sign (`#`). This method is identical to `String.is_valid_html_color`.
     *
     * Generated from Godot docs: Color.html_is_valid
     */
    fun htmlIsValid(color: String): Boolean {
      val f = builtinFrame()
      f.putString(1, color)
      f.callStatic(ColorMethods.htmlIsValid, 1)
      return f.retBool()
    }

    /**
     * Creates a `Color` from the given string, which can be either an HTML color code or a named
     * color (case-insensitive). Returns `default` if the color cannot be inferred from the string.
     * If you want to create a color from String in a constant expression, use the equivalent
     * constructor instead (i.e. `Color("color string")`).
     *
     * Generated from Godot docs: Color.from_string
     */
    fun fromString(str: String, default: Color): Color {
      val f = builtinFrame()
      f.putString(1, str)
      f.put(2, default)
      f.callStatic(ColorMethods.fromString, 2)
      return f.retColor()
    }

    /**
     * Constructs a color from an HSV profile (https://en.wikipedia.org/wiki/HSL_and_HSV). The hue
     * (`h`), saturation (`s`), and value (`v`) are typically between 0.0 and 1.0.
     *
     * Generated from Godot docs: Color.from_hsv
     */
    fun fromHsv(h: Double, s: Double, v: Double, alpha: Double = 1.0): Color {
      val f = builtinFrame()
      f.putDouble(1, h)
      f.putDouble(2, s)
      f.putDouble(3, v)
      f.putDouble(4, alpha)
      f.callStatic(ColorMethods.fromHsv, 4)
      return f.retColor()
    }

    /**
     * Constructs a color from an OK HSL profile (https://bottosson.github.io/posts/colorpicker/).
     * The hue (`h`), saturation (`s`), and lightness (`l`) are typically between 0.0 and 1.0.
     *
     * Generated from Godot docs: Color.from_ok_hsl
     */
    fun fromOkHsl(h: Double, s: Double, l: Double, alpha: Double = 1.0): Color {
      val f = builtinFrame()
      f.putDouble(1, h)
      f.putDouble(2, s)
      f.putDouble(3, l)
      f.putDouble(4, alpha)
      f.callStatic(ColorMethods.fromOkHsl, 4)
      return f.retColor()
    }

    /**
     * Decodes a `Color` from an RGBE9995 format integer. See `Image.Format.RGBE9995`.
     *
     * Generated from Godot docs: Color.from_rgbe9995
     */
    fun fromRgbe9995(rgbe: Long): Color {
      val f = builtinFrame()
      f.putLong(1, rgbe)
      f.callStatic(ColorMethods.fromRgbe9995, 1)
      return f.retColor()
    }

    /**
     * Returns a `Color` constructed from red (`r8`), green (`g8`), blue (`b8`), and optionally
     * alpha (`a8`) integer channels, each divided by `255.0` for their final value.
     *
     * Generated from Godot docs: Color.from_rgba8
     */
    fun fromRgba8(r8: Long, g8: Long, b8: Long, a8: Long = 255L): Color {
      val f = builtinFrame()
      f.putLong(1, r8)
      f.putLong(2, g8)
      f.putLong(3, b8)
      f.putLong(4, a8)
      f.callStatic(ColorMethods.fromRgba8, 4)
      return f.retColor()
    }

    // ===== END GENERATED BUILTIN STATICS: Color =====

    /** A color from float32 channels (marshalling; no conversion). */
    internal fun raw(r: Float, g: Float, b: Float, a: Float): Color = Color(r, g, b, a, RawStorage)
  }
}
