package net.multigesture.kanama.api

/**
 * Godot/C#-style math utility facade.
 *
 * The implementations delegate to Godot utility functions through [GD] so
 * behavior stays aligned with the engine.
 */
object Mathf {
    const val PI: Double = kotlin.math.PI
    const val TAU: Double = kotlin.math.PI * 2.0
    const val E: Double = kotlin.math.E

    @JvmStatic
    fun degToRad(value: Double): Double = GD.degToRad(value)

    @JvmStatic
    fun radToDeg(value: Double): Double = GD.radToDeg(value)

    @JvmStatic
    fun lerp(from: Double, to: Double, weight: Double): Double = GD.lerpf(from, to, weight)

    @JvmStatic
    fun inverseLerp(from: Double, to: Double, weight: Double): Double = GD.inverseLerp(from, to, weight)

    @JvmStatic
    fun lerpAngle(from: Double, to: Double, weight: Double): Double = GD.lerpAngle(from, to, weight)

    @JvmStatic
    fun moveToward(from: Double, to: Double, delta: Double): Double = GD.moveToward(from, to, delta)

    @JvmStatic
    fun rotateToward(from: Double, to: Double, delta: Double): Double = GD.rotateToward(from, to, delta)

    @JvmStatic
    fun smoothStep(from: Double, to: Double, x: Double): Double = GD.smoothstep(from, to, x)

    @JvmStatic
    fun ease(x: Double, curve: Double): Double = GD.ease(x, curve)

    @JvmStatic
    fun remap(value: Double, istart: Double, istop: Double, ostart: Double, ostop: Double): Double =
        GD.remap(value, istart, istop, ostart, ostop)

    @JvmStatic
    fun clamp(value: Double, min: Double, max: Double): Double = GD.clampf(value, min, max)

    @JvmStatic
    fun clamp(value: Long, min: Long, max: Long): Long = GD.clampi(value, min, max)

    @JvmStatic
    fun min(a: Double, b: Double): Double = GD.minf(a, b)

    @JvmStatic
    fun min(a: Long, b: Long): Long = GD.mini(a, b)

    @JvmStatic
    fun max(a: Double, b: Double): Double = GD.maxf(a, b)

    @JvmStatic
    fun max(a: Long, b: Long): Long = GD.maxi(a, b)

    @JvmStatic
    fun snapped(value: Double, step: Double): Double = GD.snappedf(value, step)

    @JvmStatic
    fun snapped(value: Double, step: Long): Long = GD.snappedi(value, step)

    @JvmStatic
    fun wrap(value: Double, min: Double, max: Double): Double = GD.wrapf(value, min, max)

    @JvmStatic
    fun wrap(value: Long, min: Long, max: Long): Long = GD.wrapi(value, min, max)

    @JvmStatic
    fun isEqualApprox(a: Double, b: Double): Boolean = GD.isEqualApprox(a, b)

    @JvmStatic
    fun isZeroApprox(value: Double): Boolean = GD.isZeroApprox(value)

    @JvmStatic
    fun isFinite(value: Double): Boolean = GD.isFinite(value)

    @JvmStatic
    fun isNaN(value: Double): Boolean = GD.isNaN(value)

    @JvmStatic
    fun isInf(value: Double): Boolean = GD.isInf(value)

    @JvmStatic
    fun sin(value: Double): Double = GD.sin(value)

    @JvmStatic
    fun cos(value: Double): Double = GD.cos(value)

    @JvmStatic
    fun tan(value: Double): Double = GD.tan(value)

    @JvmStatic
    fun asin(value: Double): Double = GD.asin(value)

    @JvmStatic
    fun acos(value: Double): Double = GD.acos(value)

    @JvmStatic
    fun atan(value: Double): Double = GD.atan(value)

    @JvmStatic
    fun atan2(y: Double, x: Double): Double = GD.atan2(y, x)

    @JvmStatic
    fun sqrt(value: Double): Double = GD.sqrt(value)

    @JvmStatic
    fun pow(x: Double, y: Double): Double = GD.pow(x, y)

    @JvmStatic
    fun fmod(x: Double, y: Double): Double = GD.fmod(x, y)

    @JvmStatic
    fun fposmod(x: Double, y: Double): Double = GD.fposmod(x, y)

    @JvmStatic
    fun posmod(x: Long, y: Long): Long = GD.posmod(x, y)

    @JvmStatic
    fun log(value: Double): Double = GD.log(value)

    @JvmStatic
    fun exp(value: Double): Double = GD.exp(value)

    @JvmStatic
    fun floor(value: Double): Double = GD.floorf(value)

    @JvmStatic
    fun floorToInt(value: Double): Long = GD.floori(value)

    @JvmStatic
    fun ceil(value: Double): Double = GD.ceilf(value)

    @JvmStatic
    fun ceilToInt(value: Double): Long = GD.ceili(value)

    @JvmStatic
    fun round(value: Double): Double = GD.roundf(value)

    @JvmStatic
    fun roundToInt(value: Double): Long = GD.roundi(value)

    @JvmStatic
    fun abs(value: Double): Double = GD.absf(value)

    @JvmStatic
    fun abs(value: Long): Long = GD.absi(value)

    @JvmStatic
    fun sign(value: Double): Double = GD.signf(value)

    @JvmStatic
    fun sign(value: Long): Long = GD.signi(value)

    @JvmStatic
    fun dbToLinear(value: Double): Double = GD.dbToLinear(value)

    @JvmStatic
    fun linearToDb(value: Double): Double = GD.linearToDb(value)

    @JvmStatic
    fun nearestPo2(value: Long): Long = GD.nearestPo2(value)

    @JvmStatic
    fun pingPong(value: Double, length: Double): Double = GD.pingpong(value, length)

    @JvmStatic
    fun sinh(value: Double): Double = GD.sinh(value)

    @JvmStatic
    fun cosh(value: Double): Double = GD.cosh(value)

    @JvmStatic
    fun tanh(value: Double): Double = GD.tanh(value)

    @JvmStatic
    fun asinh(value: Double): Double = GD.asinh(value)

    @JvmStatic
    fun acosh(value: Double): Double = GD.acosh(value)

    @JvmStatic
    fun atanh(value: Double): Double = GD.atanh(value)

    // -------------------------------------------------------------------------
    // Float overloads
    //
    // Mirror the C# binding's Mathf, which exposes both `float` and `double`
    // signatures for every scalar method. This lets user code feed Vector
    // components (Float, matching real_t) into Mathf without a `.toFloat()`
    // call, and feed `delta` (Double, engine timing precision) without a
    // `.toDouble()` call. The Float forms delegate to the Double impls and
    // narrow on return — `kotlin.math` and Godot's GD utility surface only
    // expose Double, so we don't avoid any work, only call-site noise.
    // -------------------------------------------------------------------------

    @JvmStatic fun degToRad(value: Float): Float = GD.degToRad(value.toDouble()).toFloat()
    @JvmStatic fun radToDeg(value: Float): Float = GD.radToDeg(value.toDouble()).toFloat()
    @JvmStatic fun lerp(from: Float, to: Float, weight: Float): Float =
        GD.lerpf(from.toDouble(), to.toDouble(), weight.toDouble()).toFloat()
    @JvmStatic fun inverseLerp(from: Float, to: Float, weight: Float): Float =
        GD.inverseLerp(from.toDouble(), to.toDouble(), weight.toDouble()).toFloat()
    @JvmStatic fun lerpAngle(from: Float, to: Float, weight: Float): Float =
        GD.lerpAngle(from.toDouble(), to.toDouble(), weight.toDouble()).toFloat()
    @JvmStatic fun moveToward(from: Float, to: Float, delta: Float): Float =
        GD.moveToward(from.toDouble(), to.toDouble(), delta.toDouble()).toFloat()
    @JvmStatic fun rotateToward(from: Float, to: Float, delta: Float): Float =
        GD.rotateToward(from.toDouble(), to.toDouble(), delta.toDouble()).toFloat()
    @JvmStatic fun smoothStep(from: Float, to: Float, x: Float): Float =
        GD.smoothstep(from.toDouble(), to.toDouble(), x.toDouble()).toFloat()
    @JvmStatic fun ease(x: Float, curve: Float): Float = GD.ease(x.toDouble(), curve.toDouble()).toFloat()
    @JvmStatic fun remap(value: Float, istart: Float, istop: Float, ostart: Float, ostop: Float): Float =
        GD.remap(value.toDouble(), istart.toDouble(), istop.toDouble(), ostart.toDouble(), ostop.toDouble()).toFloat()
    @JvmStatic fun clamp(value: Float, min: Float, max: Float): Float =
        GD.clampf(value.toDouble(), min.toDouble(), max.toDouble()).toFloat()
    @JvmStatic fun min(a: Float, b: Float): Float = GD.minf(a.toDouble(), b.toDouble()).toFloat()
    @JvmStatic fun max(a: Float, b: Float): Float = GD.maxf(a.toDouble(), b.toDouble()).toFloat()
    @JvmStatic fun snapped(value: Float, step: Float): Float =
        GD.snappedf(value.toDouble(), step.toDouble()).toFloat()
    @JvmStatic fun wrap(value: Float, min: Float, max: Float): Float =
        GD.wrapf(value.toDouble(), min.toDouble(), max.toDouble()).toFloat()
    @JvmStatic fun isEqualApprox(a: Float, b: Float): Boolean = GD.isEqualApprox(a.toDouble(), b.toDouble())
    @JvmStatic fun isZeroApprox(value: Float): Boolean = GD.isZeroApprox(value.toDouble())
    @JvmStatic fun isFinite(value: Float): Boolean = GD.isFinite(value.toDouble())
    @JvmStatic fun isNaN(value: Float): Boolean = GD.isNaN(value.toDouble())
    @JvmStatic fun isInf(value: Float): Boolean = GD.isInf(value.toDouble())
    @JvmStatic fun sin(value: Float): Float = GD.sin(value.toDouble()).toFloat()
    @JvmStatic fun cos(value: Float): Float = GD.cos(value.toDouble()).toFloat()
    @JvmStatic fun tan(value: Float): Float = GD.tan(value.toDouble()).toFloat()
    @JvmStatic fun asin(value: Float): Float = GD.asin(value.toDouble()).toFloat()
    @JvmStatic fun acos(value: Float): Float = GD.acos(value.toDouble()).toFloat()
    @JvmStatic fun atan(value: Float): Float = GD.atan(value.toDouble()).toFloat()
    @JvmStatic fun atan2(y: Float, x: Float): Float = GD.atan2(y.toDouble(), x.toDouble()).toFloat()
    @JvmStatic fun sqrt(value: Float): Float = GD.sqrt(value.toDouble()).toFloat()
    @JvmStatic fun pow(x: Float, y: Float): Float = GD.pow(x.toDouble(), y.toDouble()).toFloat()
    @JvmStatic fun fmod(x: Float, y: Float): Float = GD.fmod(x.toDouble(), y.toDouble()).toFloat()
    @JvmStatic fun fposmod(x: Float, y: Float): Float = GD.fposmod(x.toDouble(), y.toDouble()).toFloat()
    @JvmStatic fun log(value: Float): Float = GD.log(value.toDouble()).toFloat()
    @JvmStatic fun exp(value: Float): Float = GD.exp(value.toDouble()).toFloat()
    @JvmStatic fun floor(value: Float): Float = GD.floorf(value.toDouble()).toFloat()
    @JvmStatic fun floorToInt(value: Float): Long = GD.floori(value.toDouble())
    @JvmStatic fun ceil(value: Float): Float = GD.ceilf(value.toDouble()).toFloat()
    @JvmStatic fun ceilToInt(value: Float): Long = GD.ceili(value.toDouble())
    @JvmStatic fun round(value: Float): Float = GD.roundf(value.toDouble()).toFloat()
    @JvmStatic fun roundToInt(value: Float): Long = GD.roundi(value.toDouble())
    @JvmStatic fun abs(value: Float): Float = GD.absf(value.toDouble()).toFloat()
    @JvmStatic fun sign(value: Float): Float = GD.signf(value.toDouble()).toFloat()
    @JvmStatic fun dbToLinear(value: Float): Float = GD.dbToLinear(value.toDouble()).toFloat()
    @JvmStatic fun linearToDb(value: Float): Float = GD.linearToDb(value.toDouble()).toFloat()
    @JvmStatic fun pingPong(value: Float, length: Float): Float =
        GD.pingpong(value.toDouble(), length.toDouble()).toFloat()
    @JvmStatic fun sinh(value: Float): Float = GD.sinh(value.toDouble()).toFloat()
    @JvmStatic fun cosh(value: Float): Float = GD.cosh(value.toDouble()).toFloat()
    @JvmStatic fun tanh(value: Float): Float = GD.tanh(value.toDouble()).toFloat()
    @JvmStatic fun asinh(value: Float): Float = GD.asinh(value.toDouble()).toFloat()
    @JvmStatic fun acosh(value: Float): Float = GD.acosh(value.toDouble()).toFloat()
    @JvmStatic fun atanh(value: Float): Float = GD.atanh(value.toDouble()).toFloat()
}
