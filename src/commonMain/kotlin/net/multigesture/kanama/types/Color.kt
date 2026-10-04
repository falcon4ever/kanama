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
  fun toArgb32(): Long = builtinLong(ColorMethods.toArgb32, builtinArg(), emptyList())

  /**
   * Returns the color converted to a 32-bit integer in ABGR format (each component is 8 bits). ABGR
   * is the reversed version of the default RGBA format.
   *
   * Generated from Godot docs: Color.to_abgr32
   */
  fun toAbgr32(): Long = builtinLong(ColorMethods.toAbgr32, builtinArg(), emptyList())

  /**
   * Returns the color converted to a 32-bit integer in RGBA format (each component is 8 bits). RGBA
   * is Godot's default format. This method is the inverse of `hex`.
   *
   * Generated from Godot docs: Color.to_rgba32
   */
  fun toRgba32(): Long = builtinLong(ColorMethods.toRgba32, builtinArg(), emptyList())

  /**
   * Returns the color converted to a 64-bit integer in ARGB format (each component is 16 bits).
   * ARGB is more compatible with DirectX.
   *
   * Generated from Godot docs: Color.to_argb64
   */
  fun toArgb64(): Long = builtinLong(ColorMethods.toArgb64, builtinArg(), emptyList())

  /**
   * Returns the color converted to a 64-bit integer in ABGR format (each component is 16 bits).
   * ABGR is the reversed version of the default RGBA format.
   *
   * Generated from Godot docs: Color.to_abgr64
   */
  fun toAbgr64(): Long = builtinLong(ColorMethods.toAbgr64, builtinArg(), emptyList())

  /**
   * Returns the color converted to a 64-bit integer in RGBA format (each component is 16 bits).
   * RGBA is Godot's default format. This method is the inverse of `hex64`.
   *
   * Generated from Godot docs: Color.to_rgba64
   */
  fun toRgba64(): Long = builtinLong(ColorMethods.toRgba64, builtinArg(), emptyList())

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
    builtinColor(
      builtinFloat32s(
        ColorMethods.clamp,
        builtinArg(),
        4,
        listOf(min.builtinArg(), max.builtinArg()),
      )
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
  fun lerp(to: Color, weight: Double): Color =
    builtinColor(
      builtinFloat32s(ColorMethods.lerp, builtinArg(), 4, listOf(to.builtinArg(), argReal(weight)))
    )

  /**
   * Returns a new color resulting from making this color lighter by the specified `amount`, which
   * should be a ratio from 0.0 to 1.0. See also `darkened`.
   *
   * Generated from Godot docs: Color.lightened
   */
  fun lightened(amount: Double): Color =
    builtinColor(builtinFloat32s(ColorMethods.lightened, builtinArg(), 4, listOf(argReal(amount))))

  /**
   * Returns a new color resulting from making this color darker by the specified `amount` (ratio
   * from 0.0 to 1.0). See also `lightened`.
   *
   * Generated from Godot docs: Color.darkened
   */
  fun darkened(amount: Double): Color =
    builtinColor(builtinFloat32s(ColorMethods.darkened, builtinArg(), 4, listOf(argReal(amount))))

  /**
   * Returns a new color resulting from overlaying this color over the given color. In a painting
   * program, you can imagine it as the `over` color painted over this color (including alpha).
   *
   * Generated from Godot docs: Color.blend
   */
  fun blend(over: Color): Color =
    builtinColor(builtinFloat32s(ColorMethods.blend, builtinArg(), 4, listOf(over.builtinArg())))

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
  fun srgbToLinear(): Color =
    builtinColor(builtinFloat32s(ColorMethods.srgbToLinear, builtinArg(), 4, emptyList()))

  /**
   * Returns a copy of the color that is encoded using the nonlinear sRGB transfer function
   * (https://en.wikipedia.org/wiki/SRGB). This method requires the original color to use linear
   * encoding. See also `srgb_to_linear` which performs the opposite operation. Note: The color's
   * alpha channel (`a`) is not affected. The alpha channel is always stored with linear encoding,
   * regardless of the color space of the other color channels.
   *
   * Generated from Godot docs: Color.linear_to_srgb
   */
  fun linearToSrgb(): Color =
    builtinColor(builtinFloat32s(ColorMethods.linearToSrgb, builtinArg(), 4, emptyList()))

  /**
   * Returns `true` if this color and `to` are approximately equal, by running
   * `@GlobalScope.is_equal_approx` on each component.
   *
   * Generated from Godot docs: Color.is_equal_approx
   */
  fun isEqualApprox(to: Color): Boolean =
    builtinBool(ColorMethods.isEqualApprox, builtinArg(), listOf(to.builtinArg()))

  // ===== END GENERATED BUILTIN MEMBERS: Color =====

  companion object {
    // ===== BEGIN GENERATED BUILTIN STATICS: Color (generate_builtin_ops.py) =====
    /**
     * Returns the `Color` associated with the provided `hex` integer in 32-bit RGBA format (8 bits
     * per channel). This method is the inverse of `to_rgba32`. In GDScript and C#, the `int` is
     * best visualized with hexadecimal notation (`"0x"` prefix, making it `"0xRRGGBBAA"`).
     *
     * Generated from Godot docs: Color.hex
     */
    fun hex(hex: Long): Color =
      builtinColor(builtinFloat32s(ColorMethods.hex, null, 4, listOf(argLong(hex))))

    /**
     * Returns the `Color` associated with the provided `hex` integer in 64-bit RGBA format (16 bits
     * per channel). This method is the inverse of `to_rgba64`. In GDScript and C#, the `int` is
     * best visualized with hexadecimal notation (`"0x"` prefix, making it `"0xRRRRGGGGBBBBAAAA"`).
     *
     * Generated from Godot docs: Color.hex64
     */
    fun hex64(hex: Long): Color =
      builtinColor(builtinFloat32s(ColorMethods.hex64, null, 4, listOf(argLong(hex))))

    /**
     * Returns a new color from `rgba`, an HTML hexadecimal color string. `rgba` is not
     * case-sensitive, and may be prefixed by a hash sign (`#`). `rgba` must be a valid three-digit
     * or six-digit hexadecimal color string, and may contain an alpha channel value. If `rgba` does
     * not contain an alpha channel value, an alpha channel value of 1.0 is applied. If `rgba` is
     * invalid, returns an empty color.
     *
     * Generated from Godot docs: Color.html
     */
    fun html(rgba: String): Color =
      builtinColor(builtinFloat32s(ColorMethods.html, null, 4, listOf(argString(rgba))))

    /**
     * Returns `true` if `color` is a valid HTML hexadecimal color string. The string must be a
     * hexadecimal value (case-insensitive) of either 3, 4, 6 or 8 digits, and may be prefixed by a
     * hash sign (`#`). This method is identical to `String.is_valid_html_color`.
     *
     * Generated from Godot docs: Color.html_is_valid
     */
    fun htmlIsValid(color: String): Boolean =
      builtinBool(ColorMethods.htmlIsValid, null, listOf(argString(color)))

    /**
     * Creates a `Color` from the given string, which can be either an HTML color code or a named
     * color (case-insensitive). Returns `default` if the color cannot be inferred from the string.
     * If you want to create a color from String in a constant expression, use the equivalent
     * constructor instead (i.e. `Color("color string")`).
     *
     * Generated from Godot docs: Color.from_string
     */
    fun fromString(str: String, default: Color): Color =
      builtinColor(
        builtinFloat32s(
          ColorMethods.fromString,
          null,
          4,
          listOf(argString(str), default.builtinArg()),
        )
      )

    /**
     * Constructs a color from an HSV profile (https://en.wikipedia.org/wiki/HSL_and_HSV). The hue
     * (`h`), saturation (`s`), and value (`v`) are typically between 0.0 and 1.0.
     *
     * Generated from Godot docs: Color.from_hsv
     */
    fun fromHsv(h: Double, s: Double, v: Double, alpha: Double = 1.0): Color =
      builtinColor(
        builtinFloat32s(
          ColorMethods.fromHsv,
          null,
          4,
          listOf(argReal(h), argReal(s), argReal(v), argReal(alpha)),
        )
      )

    /**
     * Constructs a color from an OK HSL profile (https://bottosson.github.io/posts/colorpicker/).
     * The hue (`h`), saturation (`s`), and lightness (`l`) are typically between 0.0 and 1.0.
     *
     * Generated from Godot docs: Color.from_ok_hsl
     */
    fun fromOkHsl(h: Double, s: Double, l: Double, alpha: Double = 1.0): Color =
      builtinColor(
        builtinFloat32s(
          ColorMethods.fromOkHsl,
          null,
          4,
          listOf(argReal(h), argReal(s), argReal(l), argReal(alpha)),
        )
      )

    /**
     * Decodes a `Color` from an RGBE9995 format integer. See `Image.Format.RGBE9995`.
     *
     * Generated from Godot docs: Color.from_rgbe9995
     */
    fun fromRgbe9995(rgbe: Long): Color =
      builtinColor(builtinFloat32s(ColorMethods.fromRgbe9995, null, 4, listOf(argLong(rgbe))))

    /**
     * Returns a `Color` constructed from red (`r8`), green (`g8`), blue (`b8`), and optionally
     * alpha (`a8`) integer channels, each divided by `255.0` for their final value.
     *
     * Generated from Godot docs: Color.from_rgba8
     */
    fun fromRgba8(r8: Long, g8: Long, b8: Long, a8: Long = 255L): Color =
      builtinColor(
        builtinFloat32s(
          ColorMethods.fromRgba8,
          null,
          4,
          listOf(argLong(r8), argLong(g8), argLong(b8), argLong(a8)),
        )
      )

    // ===== END GENERATED BUILTIN STATICS: Color =====

    /** A color from float32 channels (marshalling; no conversion). */
    internal fun raw(r: Float, g: Float, b: Float, a: Float): Color = Color(r, g, b, a, RawStorage)
  }
}
