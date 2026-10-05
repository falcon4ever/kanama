package net.multigesture.kanama.api

/**
 * Godot/C#-style math helpers, written once for every platform (task 129 B).
 *
 * Where Godot's own function is plain arithmetic (lerp, clamp, wrap, snapped, move_toward,
 * smoothstep, ...) the body is Godot's formula in Godot's operand order, so the result is the same
 * double, bit for bit, as GDScript's (the runtime smoke's parity pair checks it). Anything with a
 * transcendental (sin, pow, exp, log, ...) or a float-to-int conversion is the engine's own
 * function, through [GD]. `Mathf.abs(-1)` and friends therefore behave exactly like GDScript's
 * `abs(-1)` on every platform.
 *
 * The `Float` overloads mirror the C# binding's `Mathf`: every decimal Kanama hands out is `Double`
 * (task 134), so they serve user code that keeps its own `Float`s; each computes in `Double` and
 * narrows the result.
 */
object Mathf {
    const val PI: Double = kotlin.math.PI
    const val TAU: Double = kotlin.math.PI * 2.0
    const val E: Double = kotlin.math.E

    // Godot's CMP_EPSILON (core/math/math_defs.h).
    private const val CMP_EPSILON: Double = 0.00001

    fun degToRad(value: Double): Double = value * (PI / 180.0)

    fun radToDeg(value: Double): Double = value * (180.0 / PI)

    fun lerp(from: Double, to: Double, weight: Double): Double = from + (to - from) * weight

    fun inverseLerp(from: Double, to: Double, weight: Double): Double = (weight - from) / (to - from)

    fun lerpAngle(from: Double, to: Double, weight: Double): Double = from + angleDifference(from, to) * weight

    fun moveToward(from: Double, to: Double, delta: Double): Double =
        if (kotlin.math.abs(to - from) <= delta) to else from + sign(to - from) * delta

    fun rotateToward(from: Double, to: Double, delta: Double): Double {
        val difference = angleDifference(from, to)
        val absDifference = kotlin.math.abs(difference)
        // When delta < 0, move no further than PI radians away from `to`.
        return from + clamp(delta, absDifference - PI, absDifference) * (if (difference >= 0.0) 1.0 else -1.0)
    }

    fun smoothStep(from: Double, to: Double, x: Double): Double {
        if (isEqualApprox(from, to)) {
            return if (from <= to) {
                if (x <= from) 0.0 else 1.0
            } else {
                if (x <= to) 1.0 else 0.0
            }
        }
        val s = clamp((x - from) / (to - from), 0.0, 1.0)
        return s * s * (3.0 - 2.0 * s)
    }

    fun ease(x: Double, curve: Double): Double = GD.ease(x, curve)

    fun remap(value: Double, istart: Double, istop: Double, ostart: Double, ostop: Double): Double =
        lerp(ostart, ostop, inverseLerp(istart, istop, value))

    // Godot's CLAMP / MIN / MAX: plain comparisons (no exception when min > max; NaN as Godot).
    fun clamp(value: Double, min: Double, max: Double): Double =
        if (value < min) min else if (value > max) max else value

    fun clamp(value: Long, min: Long, max: Long): Long = if (value < min) min else if (value > max) max else value

    fun min(a: Double, b: Double): Double = if (a < b) a else b

    fun min(a: Long, b: Long): Long = if (a < b) a else b

    fun max(a: Double, b: Double): Double = if (a > b) a else b

    fun max(a: Long, b: Long): Long = if (a > b) a else b

    fun snapped(value: Double, step: Double): Double =
        if (step != 0.0) kotlin.math.floor(value / step + 0.5) * step else value

    fun snapped(value: Double, step: Long): Long = GD.snappedi(value, step)

    fun wrap(value: Double, min: Double, max: Double): Double {
        val range = max - min
        if (isZeroApprox(range)) return min
        val result = value - (range * kotlin.math.floor((value - min) / range))
        return if (isEqualApprox(result, max)) min else result
    }

    fun wrap(value: Long, min: Long, max: Long): Long {
        val range = max - min
        return if (range == 0L) min else min + ((((value - min) % range) + range) % range)
    }

    fun isEqualApprox(a: Double, b: Double): Boolean {
        // Exact equality first: it handles the infinities.
        if (a == b) return true
        var tolerance = CMP_EPSILON * kotlin.math.abs(a)
        if (tolerance < CMP_EPSILON) tolerance = CMP_EPSILON
        return kotlin.math.abs(a - b) < tolerance
    }

    fun isZeroApprox(value: Double): Boolean = kotlin.math.abs(value) < CMP_EPSILON

    fun isFinite(value: Double): Boolean = value.isFinite()

    fun isNaN(value: Double): Boolean = value.isNaN()

    fun isInf(value: Double): Boolean = value.isInfinite()

    fun sin(value: Double): Double = GD.sin(value)

    fun cos(value: Double): Double = GD.cos(value)

    fun tan(value: Double): Double = GD.tan(value)

    fun asin(value: Double): Double = GD.asin(value)

    fun acos(value: Double): Double = GD.acos(value)

    fun atan(value: Double): Double = GD.atan(value)

    fun atan2(y: Double, x: Double): Double = GD.atan2(y, x)

    // IEEE square root and remainder are exact on every platform (Godot calls std::sqrt / std::fmod).
    fun sqrt(value: Double): Double = kotlin.math.sqrt(value)

    fun pow(x: Double, y: Double): Double = GD.pow(x, y)

    fun fmod(x: Double, y: Double): Double = x % y

    fun fposmod(x: Double, y: Double): Double {
        var value = x % y
        if ((value < 0.0 && y > 0.0) || (value > 0.0 && y < 0.0)) value += y
        return value + 0.0
    }

    // A zero divisor is the engine's: it reports the error and returns 0, as GDScript does.
    fun posmod(x: Long, y: Long): Long {
        if (y == 0L) return GD.posmod(x, y)
        var value = x % y
        if ((value < 0L && y > 0L) || (value > 0L && y < 0L)) value += y
        return value
    }

    fun log(value: Double): Double = GD.log(value)

    fun exp(value: Double): Double = GD.exp(value)

    fun floor(value: Double): Double = kotlin.math.floor(value)

    fun floorToInt(value: Double): Long = GD.floori(value)

    fun ceil(value: Double): Double = kotlin.math.ceil(value)

    fun ceilToInt(value: Double): Long = GD.ceili(value)

    // std::round: halves away from zero (kotlin.math.round rounds them to even).
    fun round(value: Double): Double {
        val truncated = kotlin.math.truncate(value)
        return if (kotlin.math.abs(value - truncated) >= 0.5) truncated + kotlin.math.sign(value) else truncated
    }

    fun roundToInt(value: Double): Long = GD.roundi(value)

    fun abs(value: Double): Double = kotlin.math.abs(value)

    fun abs(value: Long): Long = kotlin.math.abs(value)

    // Godot's SIGN: 0 for zero and NaN.
    fun sign(value: Double): Double = if (value > 0.0) 1.0 else if (value < 0.0) -1.0 else 0.0

    fun sign(value: Long): Long = if (value > 0L) 1L else if (value < 0L) -1L else 0L

    fun dbToLinear(value: Double): Double = GD.dbToLinear(value)

    fun linearToDb(value: Double): Double = GD.linearToDb(value)

    // Godot's nearest_power_of_2_templated on the uint64 of [value].
    fun nearestPo2(value: Long): Long {
        var x = value - 1L
        for (shift in intArrayOf(1, 2, 4, 8, 16, 32)) x = x or (x ushr shift)
        return x + 1L
    }

    fun pingPong(value: Double, length: Double): Double =
        if (length != 0.0) kotlin.math.abs(fract((value - length) / (length * 2.0)) * length * 2.0 - length) else 0.0

    fun sinh(value: Double): Double = GD.sinh(value)

    fun cosh(value: Double): Double = GD.cosh(value)

    fun tanh(value: Double): Double = GD.tanh(value)

    fun asinh(value: Double): Double = GD.asinh(value)

    fun acosh(value: Double): Double = GD.acosh(value)

    fun atanh(value: Double): Double = GD.atanh(value)

    private fun angleDifference(from: Double, to: Double): Double {
        val difference = (to - from) % TAU
        return (2.0 * difference) % TAU - difference
    }

    private fun fract(value: Double): Double = value - kotlin.math.floor(value)

    // Float overloads: compute in Double, narrow the result.

    fun degToRad(value: Float): Float = degToRad(value.toDouble()).toFloat()

    fun radToDeg(value: Float): Float = radToDeg(value.toDouble()).toFloat()

    fun lerp(from: Float, to: Float, weight: Float): Float =
        lerp(from.toDouble(), to.toDouble(), weight.toDouble()).toFloat()

    fun inverseLerp(from: Float, to: Float, weight: Float): Float =
        inverseLerp(from.toDouble(), to.toDouble(), weight.toDouble()).toFloat()

    fun lerpAngle(from: Float, to: Float, weight: Float): Float =
        lerpAngle(from.toDouble(), to.toDouble(), weight.toDouble()).toFloat()

    fun moveToward(from: Float, to: Float, delta: Float): Float =
        moveToward(from.toDouble(), to.toDouble(), delta.toDouble()).toFloat()

    fun rotateToward(from: Float, to: Float, delta: Float): Float =
        rotateToward(from.toDouble(), to.toDouble(), delta.toDouble()).toFloat()

    fun smoothStep(from: Float, to: Float, x: Float): Float =
        smoothStep(from.toDouble(), to.toDouble(), x.toDouble()).toFloat()

    fun ease(x: Float, curve: Float): Float = ease(x.toDouble(), curve.toDouble()).toFloat()

    fun remap(value: Float, istart: Float, istop: Float, ostart: Float, ostop: Float): Float =
        remap(value.toDouble(), istart.toDouble(), istop.toDouble(), ostart.toDouble(), ostop.toDouble()).toFloat()

    fun clamp(value: Float, min: Float, max: Float): Float =
        clamp(value.toDouble(), min.toDouble(), max.toDouble()).toFloat()

    fun min(a: Float, b: Float): Float = min(a.toDouble(), b.toDouble()).toFloat()

    fun max(a: Float, b: Float): Float = max(a.toDouble(), b.toDouble()).toFloat()

    fun snapped(value: Float, step: Float): Float = snapped(value.toDouble(), step.toDouble()).toFloat()

    fun wrap(value: Float, min: Float, max: Float): Float =
        wrap(value.toDouble(), min.toDouble(), max.toDouble()).toFloat()

    fun isEqualApprox(a: Float, b: Float): Boolean = isEqualApprox(a.toDouble(), b.toDouble())

    fun isZeroApprox(value: Float): Boolean = isZeroApprox(value.toDouble())

    fun isFinite(value: Float): Boolean = value.isFinite()

    fun isNaN(value: Float): Boolean = value.isNaN()

    fun isInf(value: Float): Boolean = value.isInfinite()

    fun sin(value: Float): Float = sin(value.toDouble()).toFloat()

    fun cos(value: Float): Float = cos(value.toDouble()).toFloat()

    fun tan(value: Float): Float = tan(value.toDouble()).toFloat()

    fun asin(value: Float): Float = asin(value.toDouble()).toFloat()

    fun acos(value: Float): Float = acos(value.toDouble()).toFloat()

    fun atan(value: Float): Float = atan(value.toDouble()).toFloat()

    fun atan2(y: Float, x: Float): Float = atan2(y.toDouble(), x.toDouble()).toFloat()

    fun sqrt(value: Float): Float = sqrt(value.toDouble()).toFloat()

    fun pow(x: Float, y: Float): Float = pow(x.toDouble(), y.toDouble()).toFloat()

    fun fmod(x: Float, y: Float): Float = fmod(x.toDouble(), y.toDouble()).toFloat()

    fun fposmod(x: Float, y: Float): Float = fposmod(x.toDouble(), y.toDouble()).toFloat()

    fun log(value: Float): Float = log(value.toDouble()).toFloat()

    fun exp(value: Float): Float = exp(value.toDouble()).toFloat()

    fun floor(value: Float): Float = floor(value.toDouble()).toFloat()

    fun floorToInt(value: Float): Long = floorToInt(value.toDouble())

    fun ceil(value: Float): Float = ceil(value.toDouble()).toFloat()

    fun ceilToInt(value: Float): Long = ceilToInt(value.toDouble())

    fun round(value: Float): Float = round(value.toDouble()).toFloat()

    fun roundToInt(value: Float): Long = roundToInt(value.toDouble())

    fun abs(value: Float): Float = kotlin.math.abs(value)

    fun sign(value: Float): Float = sign(value.toDouble()).toFloat()

    fun dbToLinear(value: Float): Float = dbToLinear(value.toDouble()).toFloat()

    fun linearToDb(value: Float): Float = linearToDb(value.toDouble()).toFloat()

    fun pingPong(value: Float, length: Float): Float = pingPong(value.toDouble(), length.toDouble()).toFloat()

    fun sinh(value: Float): Float = sinh(value.toDouble()).toFloat()

    fun cosh(value: Float): Float = cosh(value.toDouble()).toFloat()

    fun tanh(value: Float): Float = tanh(value.toDouble()).toFloat()

    fun asinh(value: Float): Float = asinh(value.toDouble()).toFloat()

    fun acosh(value: Float): Float = acosh(value.toDouble()).toFloat()

    fun atanh(value: Float): Float = atanh(value.toDouble()).toFloat()
}
