package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

/**
 * A CPU-based 2D particle emitter.
 *
 * Generated from Godot docs: CPUParticles2D
 */
class CPUParticles2D(handle: MemorySegment) : Node2D(handle) {
    var emitting: Boolean
        @JvmName("emittingProperty")
        get() = isEmitting()
        @JvmName("setEmittingProperty")
        set(value) = setEmitting(value)

    var amount: Int
        @JvmName("amountProperty")
        get() = getAmount()
        @JvmName("setAmountProperty")
        set(value) = setAmount(value)

    var texture: Texture2D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    var lifetime: Double
        @JvmName("lifetimeProperty")
        get() = getLifetime()
        @JvmName("setLifetimeProperty")
        set(value) = setLifetime(value)

    var oneShot: Boolean
        @JvmName("oneShotProperty")
        get() = getOneShot()
        @JvmName("setOneShotProperty")
        set(value) = setOneShot(value)

    var preprocess: Double
        @JvmName("preprocessProperty")
        get() = getPreProcessTime()
        @JvmName("setPreprocessProperty")
        set(value) = setPreProcessTime(value)

    var speedScale: Double
        @JvmName("speedScaleProperty")
        get() = getSpeedScale()
        @JvmName("setSpeedScaleProperty")
        set(value) = setSpeedScale(value)

    var explosiveness: Double
        @JvmName("explosivenessProperty")
        get() = getExplosivenessRatio()
        @JvmName("setExplosivenessProperty")
        set(value) = setExplosivenessRatio(value)

    var randomness: Double
        @JvmName("randomnessProperty")
        get() = getRandomnessRatio()
        @JvmName("setRandomnessProperty")
        set(value) = setRandomnessRatio(value)

    var useFixedSeed: Boolean
        @JvmName("useFixedSeedProperty")
        get() = getUseFixedSeed()
        @JvmName("setUseFixedSeedProperty")
        set(value) = setUseFixedSeed(value)

    var seed: Long
        @JvmName("seedProperty")
        get() = getSeed()
        @JvmName("setSeedProperty")
        set(value) = setSeed(value)

    var lifetimeRandomness: Double
        @JvmName("lifetimeRandomnessProperty")
        get() = getLifetimeRandomness()
        @JvmName("setLifetimeRandomnessProperty")
        set(value) = setLifetimeRandomness(value)

    var fixedFps: Int
        @JvmName("fixedFpsProperty")
        get() = getFixedFps()
        @JvmName("setFixedFpsProperty")
        set(value) = setFixedFps(value)

    var fractDelta: Boolean
        @JvmName("fractDeltaProperty")
        get() = getFractionalDelta()
        @JvmName("setFractDeltaProperty")
        set(value) = setFractionalDelta(value)

    var localCoords: Boolean
        @JvmName("localCoordsProperty")
        get() = getUseLocalCoordinates()
        @JvmName("setLocalCoordsProperty")
        set(value) = setUseLocalCoordinates(value)

    var drawOrder: Long
        @JvmName("drawOrderProperty")
        get() = getDrawOrder()
        @JvmName("setDrawOrderProperty")
        set(value) = setDrawOrder(value)

    var emissionShape: Long
        @JvmName("emissionShapeProperty")
        get() = getEmissionShape()
        @JvmName("setEmissionShapeProperty")
        set(value) = setEmissionShape(value)

    var emissionSphereRadius: Double
        @JvmName("emissionSphereRadiusProperty")
        get() = getEmissionSphereRadius()
        @JvmName("setEmissionSphereRadiusProperty")
        set(value) = setEmissionSphereRadius(value)

    var emissionRectExtents: Vector2
        @JvmName("emissionRectExtentsProperty")
        get() = getEmissionRectExtents()
        @JvmName("setEmissionRectExtentsProperty")
        set(value) = setEmissionRectExtents(value)

    var emissionPoints: List<Vector2>
        @JvmName("emissionPointsProperty")
        get() = getEmissionPoints()
        @JvmName("setEmissionPointsProperty")
        set(value) = setEmissionPoints(value)

    var emissionNormals: List<Vector2>
        @JvmName("emissionNormalsProperty")
        get() = getEmissionNormals()
        @JvmName("setEmissionNormalsProperty")
        set(value) = setEmissionNormals(value)

    var emissionColors: List<Color>
        @JvmName("emissionColorsProperty")
        get() = getEmissionColors()
        @JvmName("setEmissionColorsProperty")
        set(value) = setEmissionColors(value)

    var emissionRingInnerRadius: Double
        @JvmName("emissionRingInnerRadiusProperty")
        get() = getEmissionRingInnerRadius()
        @JvmName("setEmissionRingInnerRadiusProperty")
        set(value) = setEmissionRingInnerRadius(value)

    var emissionRingRadius: Double
        @JvmName("emissionRingRadiusProperty")
        get() = getEmissionRingRadius()
        @JvmName("setEmissionRingRadiusProperty")
        set(value) = setEmissionRingRadius(value)

    var direction: Vector2
        @JvmName("directionProperty")
        get() = getDirection()
        @JvmName("setDirectionProperty")
        set(value) = setDirection(value)

    var spread: Double
        @JvmName("spreadProperty")
        get() = getSpread()
        @JvmName("setSpreadProperty")
        set(value) = setSpread(value)

    var gravity: Vector2
        @JvmName("gravityProperty")
        get() = getGravity()
        @JvmName("setGravityProperty")
        set(value) = setGravity(value)

    var splitScale: Boolean
        @JvmName("splitScaleProperty")
        get() = getSplitScale()
        @JvmName("setSplitScaleProperty")
        set(value) = setSplitScale(value)

    var scaleCurveX: Curve?
        @JvmName("scaleCurveXProperty")
        get() = getScaleCurveX()
        @JvmName("setScaleCurveXProperty")
        set(value) = setScaleCurveX(value)

    var scaleCurveY: Curve?
        @JvmName("scaleCurveYProperty")
        get() = getScaleCurveY()
        @JvmName("setScaleCurveYProperty")
        set(value) = setScaleCurveY(value)

    var color: Color
        @JvmName("colorProperty")
        get() = getColor()
        @JvmName("setColorProperty")
        set(value) = setColor(value)

    var colorRamp: Gradient?
        @JvmName("colorRampProperty")
        get() = getColorRamp()
        @JvmName("setColorRampProperty")
        set(value) = setColorRamp(value)

    var colorInitialRamp: Gradient?
        @JvmName("colorInitialRampProperty")
        get() = getColorInitialRamp()
        @JvmName("setColorInitialRampProperty")
        set(value) = setColorInitialRamp(value)

    fun setEmitting(emitting: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEmittingBind, handle, emitting)
    }

    fun setAmount(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setAmountBind, handle, amount)
    }

    fun setLifetime(secs: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLifetimeBind, handle, secs)
    }

    fun setOneShot(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setOneShotBind, handle, enable)
    }

    fun setPreProcessTime(secs: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPreProcessTimeBind, handle, secs)
    }

    fun setExplosivenessRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setExplosivenessRatioBind, handle, ratio)
    }

    fun setRandomnessRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRandomnessRatioBind, handle, ratio)
    }

    fun setLifetimeRandomness(random: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLifetimeRandomnessBind, handle, random)
    }

    fun setUseLocalCoordinates(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseLocalCoordinatesBind, handle, enable)
    }

    fun setFixedFps(fps: Int) {
        ObjectCalls.ptrcallWithIntArg(setFixedFpsBind, handle, fps)
    }

    fun setFractionalDelta(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFractionalDeltaBind, handle, enable)
    }

    fun setSpeedScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSpeedScaleBind, handle, scale)
    }

    fun requestParticlesProcess(processTime: Double, processTimeResidual: Double = 0.0) {
        ObjectCalls.ptrcallWithTwoDoubleArgs(requestParticlesProcessBind, handle, processTime, processTimeResidual)
    }

    fun isEmitting(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEmittingBind, handle)
    }

    fun getAmount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getAmountBind, handle)
    }

    fun getLifetime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLifetimeBind, handle)
    }

    fun getOneShot(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getOneShotBind, handle)
    }

    fun getPreProcessTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPreProcessTimeBind, handle)
    }

    fun getExplosivenessRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getExplosivenessRatioBind, handle)
    }

    fun getRandomnessRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRandomnessRatioBind, handle)
    }

    fun getLifetimeRandomness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLifetimeRandomnessBind, handle)
    }

    fun getUseLocalCoordinates(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseLocalCoordinatesBind, handle)
    }

    fun getFixedFps(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFixedFpsBind, handle)
    }

    fun getFractionalDelta(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFractionalDeltaBind, handle)
    }

    fun getSpeedScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSpeedScaleBind, handle)
    }

    fun setUseFixedSeed(useFixedSeed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseFixedSeedBind, handle, useFixedSeed)
    }

    fun getUseFixedSeed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseFixedSeedBind, handle)
    }

    fun setSeed(seed: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setSeedBind, handle, seed)
    }

    fun getSeed(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getSeedBind, handle)
    }

    fun setDrawOrder(order: Long) {
        ObjectCalls.ptrcallWithLongArg(setDrawOrderBind, handle, order)
    }

    fun getDrawOrder(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDrawOrderBind, handle)
    }

    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, handle))
    }

    /**
     * Restarts the particle emitter. If `keep_seed` is `true`, the current random seed will be
     * preserved. Useful for seeking and playback.
     *
     * Generated from Godot docs: CPUParticles2D.restart
     */
    fun restart(keepSeed: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(restartBind, handle, keepSeed)
    }

    fun setDirection(direction: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setDirectionBind, handle, direction)
    }

    fun getDirection(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getDirectionBind, handle)
    }

    fun setSpread(spread: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSpreadBind, handle, spread)
    }

    fun getSpread(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSpreadBind, handle)
    }

    fun setParamMin(param: Long, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamMinBind, handle, param, value)
    }

    fun getParamMin(param: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamMinBind, handle, param)
    }

    fun setParamMax(param: Long, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamMaxBind, handle, param, value)
    }

    fun getParamMax(param: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamMaxBind, handle, param)
    }

    fun setParamCurve(param: Long, curve: Curve?) {
        ObjectCalls.ptrcallWithLongAndObjectArg(setParamCurveBind, handle, param, curve?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getParamCurve(param: Long): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallWithLongArgRetObject(getParamCurveBind, handle, param))
    }

    fun setColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setColorBind, handle, color)
    }

    fun getColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getColorBind, handle)
    }

    fun setColorRamp(ramp: Gradient?) {
        ObjectCalls.ptrcallWithObjectArgs(setColorRampBind, handle, listOf(ramp?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getColorRamp(): Gradient? {
        return Gradient.wrap(ObjectCalls.ptrcallNoArgsRetObject(getColorRampBind, handle))
    }

    fun setColorInitialRamp(ramp: Gradient?) {
        ObjectCalls.ptrcallWithObjectArgs(setColorInitialRampBind, handle, listOf(ramp?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getColorInitialRamp(): Gradient? {
        return Gradient.wrap(ObjectCalls.ptrcallNoArgsRetObject(getColorInitialRampBind, handle))
    }

    fun setParticleFlag(particleFlag: Long, enable: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setParticleFlagBind, handle, particleFlag, enable)
    }

    fun getParticleFlag(particleFlag: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getParticleFlagBind, handle, particleFlag)
    }

    fun setEmissionShape(shape: Long) {
        ObjectCalls.ptrcallWithLongArg(setEmissionShapeBind, handle, shape)
    }

    fun getEmissionShape(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getEmissionShapeBind, handle)
    }

    fun setEmissionSphereRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionSphereRadiusBind, handle, radius)
    }

    fun getEmissionSphereRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionSphereRadiusBind, handle)
    }

    fun setEmissionRectExtents(extents: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setEmissionRectExtentsBind, handle, extents)
    }

    fun getEmissionRectExtents(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getEmissionRectExtentsBind, handle)
    }

    fun setEmissionPoints(array: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(setEmissionPointsBind, handle, array)
    }

    fun getEmissionPoints(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getEmissionPointsBind, handle)
    }

    fun setEmissionNormals(array: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(setEmissionNormalsBind, handle, array)
    }

    fun getEmissionNormals(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getEmissionNormalsBind, handle)
    }

    fun setEmissionColors(array: List<Color>) {
        ObjectCalls.ptrcallWithPackedColorListArg(setEmissionColorsBind, handle, array)
    }

    fun getEmissionColors(): List<Color> {
        return ObjectCalls.ptrcallNoArgsRetPackedColorList(getEmissionColorsBind, handle)
    }

    fun setEmissionRingInnerRadius(innerRadius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionRingInnerRadiusBind, handle, innerRadius)
    }

    fun getEmissionRingInnerRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionRingInnerRadiusBind, handle)
    }

    fun setEmissionRingRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionRingRadiusBind, handle, radius)
    }

    fun getEmissionRingRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionRingRadiusBind, handle)
    }

    fun getGravity(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getGravityBind, handle)
    }

    fun setGravity(accelVec: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setGravityBind, handle, accelVec)
    }

    fun getSplitScale(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSplitScaleBind, handle)
    }

    fun setSplitScale(splitScale: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSplitScaleBind, handle, splitScale)
    }

    fun getScaleCurveX(): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getScaleCurveXBind, handle))
    }

    fun setScaleCurveX(scaleCurve: Curve?) {
        ObjectCalls.ptrcallWithObjectArgs(setScaleCurveXBind, handle, listOf(scaleCurve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getScaleCurveY(): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getScaleCurveYBind, handle))
    }

    fun setScaleCurveY(scaleCurve: Curve?) {
        ObjectCalls.ptrcallWithObjectArgs(setScaleCurveYBind, handle, listOf(scaleCurve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun convertFromParticles(particles: Node) {
        ObjectCalls.ptrcallWithObjectArgs(convertFromParticlesBind, handle, listOf(particles.handle))
    }

    object Signals {
        const val finished: String = "finished"
    }

    companion object {
        const val DRAW_ORDER_INDEX: Long = 0L
        const val DRAW_ORDER_LIFETIME: Long = 1L
        const val PARAM_INITIAL_LINEAR_VELOCITY: Long = 0L
        const val PARAM_ANGULAR_VELOCITY: Long = 1L
        const val PARAM_ORBIT_VELOCITY: Long = 2L
        const val PARAM_LINEAR_ACCEL: Long = 3L
        const val PARAM_RADIAL_ACCEL: Long = 4L
        const val PARAM_TANGENTIAL_ACCEL: Long = 5L
        const val PARAM_DAMPING: Long = 6L
        const val PARAM_ANGLE: Long = 7L
        const val PARAM_SCALE: Long = 8L
        const val PARAM_HUE_VARIATION: Long = 9L
        const val PARAM_ANIM_SPEED: Long = 10L
        const val PARAM_ANIM_OFFSET: Long = 11L
        const val PARAM_MAX: Long = 12L
        const val PARTICLE_FLAG_ALIGN_Y_TO_VELOCITY: Long = 0L
        const val PARTICLE_FLAG_ROTATE_Y: Long = 1L
        const val PARTICLE_FLAG_DISABLE_Z: Long = 2L
        const val PARTICLE_FLAG_MAX: Long = 3L
        const val EMISSION_SHAPE_POINT: Long = 0L
        const val EMISSION_SHAPE_SPHERE: Long = 1L
        const val EMISSION_SHAPE_SPHERE_SURFACE: Long = 2L
        const val EMISSION_SHAPE_RECTANGLE: Long = 3L
        const val EMISSION_SHAPE_POINTS: Long = 4L
        const val EMISSION_SHAPE_DIRECTED_POINTS: Long = 5L
        const val EMISSION_SHAPE_RING: Long = 6L
        const val EMISSION_SHAPE_MAX: Long = 7L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): CPUParticles2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CPUParticles2D? =
            if (handle.address() == 0L) null else CPUParticles2D(handle)

        private const val SET_EMITTING_HASH = 2586408642L
        private val setEmittingBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_emitting", SET_EMITTING_HASH)
        }

        private const val SET_AMOUNT_HASH = 1286410249L
        private val setAmountBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_amount", SET_AMOUNT_HASH)
        }

        private const val SET_LIFETIME_HASH = 373806689L
        private val setLifetimeBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_lifetime", SET_LIFETIME_HASH)
        }

        private const val SET_ONE_SHOT_HASH = 2586408642L
        private val setOneShotBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_one_shot", SET_ONE_SHOT_HASH)
        }

        private const val SET_PRE_PROCESS_TIME_HASH = 373806689L
        private val setPreProcessTimeBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_pre_process_time", SET_PRE_PROCESS_TIME_HASH)
        }

        private const val SET_EXPLOSIVENESS_RATIO_HASH = 373806689L
        private val setExplosivenessRatioBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_explosiveness_ratio", SET_EXPLOSIVENESS_RATIO_HASH)
        }

        private const val SET_RANDOMNESS_RATIO_HASH = 373806689L
        private val setRandomnessRatioBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_randomness_ratio", SET_RANDOMNESS_RATIO_HASH)
        }

        private const val SET_LIFETIME_RANDOMNESS_HASH = 373806689L
        private val setLifetimeRandomnessBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_lifetime_randomness", SET_LIFETIME_RANDOMNESS_HASH)
        }

        private const val SET_USE_LOCAL_COORDINATES_HASH = 2586408642L
        private val setUseLocalCoordinatesBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_use_local_coordinates", SET_USE_LOCAL_COORDINATES_HASH)
        }

        private const val SET_FIXED_FPS_HASH = 1286410249L
        private val setFixedFpsBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_fixed_fps", SET_FIXED_FPS_HASH)
        }

        private const val SET_FRACTIONAL_DELTA_HASH = 2586408642L
        private val setFractionalDeltaBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_fractional_delta", SET_FRACTIONAL_DELTA_HASH)
        }

        private const val SET_SPEED_SCALE_HASH = 373806689L
        private val setSpeedScaleBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_speed_scale", SET_SPEED_SCALE_HASH)
        }

        private const val REQUEST_PARTICLES_PROCESS_HASH = 66938510L
        private val requestParticlesProcessBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "request_particles_process", REQUEST_PARTICLES_PROCESS_HASH)
        }

        private const val IS_EMITTING_HASH = 36873697L
        private val isEmittingBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "is_emitting", IS_EMITTING_HASH)
        }

        private const val GET_AMOUNT_HASH = 3905245786L
        private val getAmountBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_amount", GET_AMOUNT_HASH)
        }

        private const val GET_LIFETIME_HASH = 1740695150L
        private val getLifetimeBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_lifetime", GET_LIFETIME_HASH)
        }

        private const val GET_ONE_SHOT_HASH = 36873697L
        private val getOneShotBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_one_shot", GET_ONE_SHOT_HASH)
        }

        private const val GET_PRE_PROCESS_TIME_HASH = 1740695150L
        private val getPreProcessTimeBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_pre_process_time", GET_PRE_PROCESS_TIME_HASH)
        }

        private const val GET_EXPLOSIVENESS_RATIO_HASH = 1740695150L
        private val getExplosivenessRatioBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_explosiveness_ratio", GET_EXPLOSIVENESS_RATIO_HASH)
        }

        private const val GET_RANDOMNESS_RATIO_HASH = 1740695150L
        private val getRandomnessRatioBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_randomness_ratio", GET_RANDOMNESS_RATIO_HASH)
        }

        private const val GET_LIFETIME_RANDOMNESS_HASH = 1740695150L
        private val getLifetimeRandomnessBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_lifetime_randomness", GET_LIFETIME_RANDOMNESS_HASH)
        }

        private const val GET_USE_LOCAL_COORDINATES_HASH = 36873697L
        private val getUseLocalCoordinatesBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_use_local_coordinates", GET_USE_LOCAL_COORDINATES_HASH)
        }

        private const val GET_FIXED_FPS_HASH = 3905245786L
        private val getFixedFpsBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_fixed_fps", GET_FIXED_FPS_HASH)
        }

        private const val GET_FRACTIONAL_DELTA_HASH = 36873697L
        private val getFractionalDeltaBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_fractional_delta", GET_FRACTIONAL_DELTA_HASH)
        }

        private const val GET_SPEED_SCALE_HASH = 1740695150L
        private val getSpeedScaleBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_speed_scale", GET_SPEED_SCALE_HASH)
        }

        private const val SET_USE_FIXED_SEED_HASH = 2586408642L
        private val setUseFixedSeedBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_use_fixed_seed", SET_USE_FIXED_SEED_HASH)
        }

        private const val GET_USE_FIXED_SEED_HASH = 36873697L
        private val getUseFixedSeedBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_use_fixed_seed", GET_USE_FIXED_SEED_HASH)
        }

        private const val SET_SEED_HASH = 1286410249L
        private val setSeedBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_seed", SET_SEED_HASH)
        }

        private const val GET_SEED_HASH = 3905245786L
        private val getSeedBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_seed", GET_SEED_HASH)
        }

        private const val SET_DRAW_ORDER_HASH = 4183193490L
        private val setDrawOrderBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_draw_order", SET_DRAW_ORDER_HASH)
        }

        private const val GET_DRAW_ORDER_HASH = 1668655735L
        private val getDrawOrderBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_draw_order", GET_DRAW_ORDER_HASH)
        }

        private const val SET_TEXTURE_HASH = 4051416890L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 3635182373L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_texture", GET_TEXTURE_HASH)
        }

        private const val RESTART_HASH = 107499316L
        private val restartBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "restart", RESTART_HASH)
        }

        private const val SET_DIRECTION_HASH = 743155724L
        private val setDirectionBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_direction", SET_DIRECTION_HASH)
        }

        private const val GET_DIRECTION_HASH = 3341600327L
        private val getDirectionBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_direction", GET_DIRECTION_HASH)
        }

        private const val SET_SPREAD_HASH = 373806689L
        private val setSpreadBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_spread", SET_SPREAD_HASH)
        }

        private const val GET_SPREAD_HASH = 1740695150L
        private val getSpreadBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_spread", GET_SPREAD_HASH)
        }

        private const val SET_PARAM_MIN_HASH = 3320615296L
        private val setParamMinBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_param_min", SET_PARAM_MIN_HASH)
        }

        private const val GET_PARAM_MIN_HASH = 2038050600L
        private val getParamMinBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_param_min", GET_PARAM_MIN_HASH)
        }

        private const val SET_PARAM_MAX_HASH = 3320615296L
        private val setParamMaxBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_param_max", SET_PARAM_MAX_HASH)
        }

        private const val GET_PARAM_MAX_HASH = 2038050600L
        private val getParamMaxBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_param_max", GET_PARAM_MAX_HASH)
        }

        private const val SET_PARAM_CURVE_HASH = 2959350143L
        private val setParamCurveBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_param_curve", SET_PARAM_CURVE_HASH)
        }

        private const val GET_PARAM_CURVE_HASH = 2603158474L
        private val getParamCurveBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_param_curve", GET_PARAM_CURVE_HASH)
        }

        private const val SET_COLOR_HASH = 2920490490L
        private val setColorBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_color", SET_COLOR_HASH)
        }

        private const val GET_COLOR_HASH = 3444240500L
        private val getColorBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_color", GET_COLOR_HASH)
        }

        private const val SET_COLOR_RAMP_HASH = 2756054477L
        private val setColorRampBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_color_ramp", SET_COLOR_RAMP_HASH)
        }

        private const val GET_COLOR_RAMP_HASH = 132272999L
        private val getColorRampBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_color_ramp", GET_COLOR_RAMP_HASH)
        }

        private const val SET_COLOR_INITIAL_RAMP_HASH = 2756054477L
        private val setColorInitialRampBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_color_initial_ramp", SET_COLOR_INITIAL_RAMP_HASH)
        }

        private const val GET_COLOR_INITIAL_RAMP_HASH = 132272999L
        private val getColorInitialRampBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_color_initial_ramp", GET_COLOR_INITIAL_RAMP_HASH)
        }

        private const val SET_PARTICLE_FLAG_HASH = 4178137949L
        private val setParticleFlagBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_particle_flag", SET_PARTICLE_FLAG_HASH)
        }

        private const val GET_PARTICLE_FLAG_HASH = 2829976507L
        private val getParticleFlagBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_particle_flag", GET_PARTICLE_FLAG_HASH)
        }

        private const val SET_EMISSION_SHAPE_HASH = 393763892L
        private val setEmissionShapeBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_emission_shape", SET_EMISSION_SHAPE_HASH)
        }

        private const val GET_EMISSION_SHAPE_HASH = 1740246024L
        private val getEmissionShapeBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_emission_shape", GET_EMISSION_SHAPE_HASH)
        }

        private const val SET_EMISSION_SPHERE_RADIUS_HASH = 373806689L
        private val setEmissionSphereRadiusBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_emission_sphere_radius", SET_EMISSION_SPHERE_RADIUS_HASH)
        }

        private const val GET_EMISSION_SPHERE_RADIUS_HASH = 1740695150L
        private val getEmissionSphereRadiusBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_emission_sphere_radius", GET_EMISSION_SPHERE_RADIUS_HASH)
        }

        private const val SET_EMISSION_RECT_EXTENTS_HASH = 743155724L
        private val setEmissionRectExtentsBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_emission_rect_extents", SET_EMISSION_RECT_EXTENTS_HASH)
        }

        private const val GET_EMISSION_RECT_EXTENTS_HASH = 3341600327L
        private val getEmissionRectExtentsBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_emission_rect_extents", GET_EMISSION_RECT_EXTENTS_HASH)
        }

        private const val SET_EMISSION_POINTS_HASH = 1509147220L
        private val setEmissionPointsBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_emission_points", SET_EMISSION_POINTS_HASH)
        }

        private const val GET_EMISSION_POINTS_HASH = 2961356807L
        private val getEmissionPointsBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_emission_points", GET_EMISSION_POINTS_HASH)
        }

        private const val SET_EMISSION_NORMALS_HASH = 1509147220L
        private val setEmissionNormalsBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_emission_normals", SET_EMISSION_NORMALS_HASH)
        }

        private const val GET_EMISSION_NORMALS_HASH = 2961356807L
        private val getEmissionNormalsBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_emission_normals", GET_EMISSION_NORMALS_HASH)
        }

        private const val SET_EMISSION_COLORS_HASH = 3546319833L
        private val setEmissionColorsBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_emission_colors", SET_EMISSION_COLORS_HASH)
        }

        private const val GET_EMISSION_COLORS_HASH = 1392750486L
        private val getEmissionColorsBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_emission_colors", GET_EMISSION_COLORS_HASH)
        }

        private const val SET_EMISSION_RING_INNER_RADIUS_HASH = 373806689L
        private val setEmissionRingInnerRadiusBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_emission_ring_inner_radius", SET_EMISSION_RING_INNER_RADIUS_HASH)
        }

        private const val GET_EMISSION_RING_INNER_RADIUS_HASH = 1740695150L
        private val getEmissionRingInnerRadiusBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_emission_ring_inner_radius", GET_EMISSION_RING_INNER_RADIUS_HASH)
        }

        private const val SET_EMISSION_RING_RADIUS_HASH = 373806689L
        private val setEmissionRingRadiusBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_emission_ring_radius", SET_EMISSION_RING_RADIUS_HASH)
        }

        private const val GET_EMISSION_RING_RADIUS_HASH = 1740695150L
        private val getEmissionRingRadiusBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_emission_ring_radius", GET_EMISSION_RING_RADIUS_HASH)
        }

        private const val GET_GRAVITY_HASH = 3341600327L
        private val getGravityBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_gravity", GET_GRAVITY_HASH)
        }

        private const val SET_GRAVITY_HASH = 743155724L
        private val setGravityBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_gravity", SET_GRAVITY_HASH)
        }

        private const val GET_SPLIT_SCALE_HASH = 2240911060L
        private val getSplitScaleBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_split_scale", GET_SPLIT_SCALE_HASH)
        }

        private const val SET_SPLIT_SCALE_HASH = 2586408642L
        private val setSplitScaleBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_split_scale", SET_SPLIT_SCALE_HASH)
        }

        private const val GET_SCALE_CURVE_X_HASH = 2460114913L
        private val getScaleCurveXBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_scale_curve_x", GET_SCALE_CURVE_X_HASH)
        }

        private const val SET_SCALE_CURVE_X_HASH = 270443179L
        private val setScaleCurveXBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_scale_curve_x", SET_SCALE_CURVE_X_HASH)
        }

        private const val GET_SCALE_CURVE_Y_HASH = 2460114913L
        private val getScaleCurveYBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "get_scale_curve_y", GET_SCALE_CURVE_Y_HASH)
        }

        private const val SET_SCALE_CURVE_Y_HASH = 270443179L
        private val setScaleCurveYBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "set_scale_curve_y", SET_SCALE_CURVE_Y_HASH)
        }

        private const val CONVERT_FROM_PARTICLES_HASH = 1078189570L
        private val convertFromParticlesBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles2D", "convert_from_particles", CONVERT_FROM_PARTICLES_HASH)
        }
    }
}
