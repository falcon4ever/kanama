package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

/**
 * A CPU-based 2D particle emitter.
 *
 * Generated from Godot docs: CPUParticles2D
 */
class CPUParticles2D(handle: GodotHandle) : Node2D(handle) {
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

    var drawOrder: CPUParticles2D.DrawOrder
        @JvmName("drawOrderProperty")
        get() = getDrawOrder()
        @JvmName("setDrawOrderProperty")
        set(value) = setDrawOrder(value)

    var emissionShape: CPUParticles2D.EmissionShape
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

    var particleFlagAlignY: Boolean
        @JvmName("particleFlagAlignYProperty")
        get() = getParticleFlag(CPUParticles2D.ParticleFlags.ALIGN_Y_TO_VELOCITY)
        @JvmName("setParticleFlagAlignYProperty")
        set(value) = setParticleFlag(CPUParticles2D.ParticleFlags.ALIGN_Y_TO_VELOCITY, value)

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

    var initialVelocityMin: Double
        @JvmName("initialVelocityMinProperty")
        get() = getParamMin(CPUParticles2D.Parameter.INITIAL_LINEAR_VELOCITY)
        @JvmName("setInitialVelocityMinProperty")
        set(value) = setParamMin(CPUParticles2D.Parameter.INITIAL_LINEAR_VELOCITY, value)

    var initialVelocityMax: Double
        @JvmName("initialVelocityMaxProperty")
        get() = getParamMax(CPUParticles2D.Parameter.INITIAL_LINEAR_VELOCITY)
        @JvmName("setInitialVelocityMaxProperty")
        set(value) = setParamMax(CPUParticles2D.Parameter.INITIAL_LINEAR_VELOCITY, value)

    var angularVelocityMin: Double
        @JvmName("angularVelocityMinProperty")
        get() = getParamMin(CPUParticles2D.Parameter.ANGULAR_VELOCITY)
        @JvmName("setAngularVelocityMinProperty")
        set(value) = setParamMin(CPUParticles2D.Parameter.ANGULAR_VELOCITY, value)

    var angularVelocityMax: Double
        @JvmName("angularVelocityMaxProperty")
        get() = getParamMax(CPUParticles2D.Parameter.ANGULAR_VELOCITY)
        @JvmName("setAngularVelocityMaxProperty")
        set(value) = setParamMax(CPUParticles2D.Parameter.ANGULAR_VELOCITY, value)

    var angularVelocityCurve: Curve?
        @JvmName("angularVelocityCurveProperty")
        get() = getParamCurve(CPUParticles2D.Parameter.ANGULAR_VELOCITY)
        @JvmName("setAngularVelocityCurveProperty")
        set(value) = setParamCurve(CPUParticles2D.Parameter.ANGULAR_VELOCITY, value)

    var orbitVelocityMin: Double
        @JvmName("orbitVelocityMinProperty")
        get() = getParamMin(CPUParticles2D.Parameter.ORBIT_VELOCITY)
        @JvmName("setOrbitVelocityMinProperty")
        set(value) = setParamMin(CPUParticles2D.Parameter.ORBIT_VELOCITY, value)

    var orbitVelocityMax: Double
        @JvmName("orbitVelocityMaxProperty")
        get() = getParamMax(CPUParticles2D.Parameter.ORBIT_VELOCITY)
        @JvmName("setOrbitVelocityMaxProperty")
        set(value) = setParamMax(CPUParticles2D.Parameter.ORBIT_VELOCITY, value)

    var orbitVelocityCurve: Curve?
        @JvmName("orbitVelocityCurveProperty")
        get() = getParamCurve(CPUParticles2D.Parameter.ORBIT_VELOCITY)
        @JvmName("setOrbitVelocityCurveProperty")
        set(value) = setParamCurve(CPUParticles2D.Parameter.ORBIT_VELOCITY, value)

    var linearAccelMin: Double
        @JvmName("linearAccelMinProperty")
        get() = getParamMin(CPUParticles2D.Parameter.LINEAR_ACCEL)
        @JvmName("setLinearAccelMinProperty")
        set(value) = setParamMin(CPUParticles2D.Parameter.LINEAR_ACCEL, value)

    var linearAccelMax: Double
        @JvmName("linearAccelMaxProperty")
        get() = getParamMax(CPUParticles2D.Parameter.LINEAR_ACCEL)
        @JvmName("setLinearAccelMaxProperty")
        set(value) = setParamMax(CPUParticles2D.Parameter.LINEAR_ACCEL, value)

    var linearAccelCurve: Curve?
        @JvmName("linearAccelCurveProperty")
        get() = getParamCurve(CPUParticles2D.Parameter.LINEAR_ACCEL)
        @JvmName("setLinearAccelCurveProperty")
        set(value) = setParamCurve(CPUParticles2D.Parameter.LINEAR_ACCEL, value)

    var radialAccelMin: Double
        @JvmName("radialAccelMinProperty")
        get() = getParamMin(CPUParticles2D.Parameter.RADIAL_ACCEL)
        @JvmName("setRadialAccelMinProperty")
        set(value) = setParamMin(CPUParticles2D.Parameter.RADIAL_ACCEL, value)

    var radialAccelMax: Double
        @JvmName("radialAccelMaxProperty")
        get() = getParamMax(CPUParticles2D.Parameter.RADIAL_ACCEL)
        @JvmName("setRadialAccelMaxProperty")
        set(value) = setParamMax(CPUParticles2D.Parameter.RADIAL_ACCEL, value)

    var radialAccelCurve: Curve?
        @JvmName("radialAccelCurveProperty")
        get() = getParamCurve(CPUParticles2D.Parameter.RADIAL_ACCEL)
        @JvmName("setRadialAccelCurveProperty")
        set(value) = setParamCurve(CPUParticles2D.Parameter.RADIAL_ACCEL, value)

    var tangentialAccelMin: Double
        @JvmName("tangentialAccelMinProperty")
        get() = getParamMin(CPUParticles2D.Parameter.TANGENTIAL_ACCEL)
        @JvmName("setTangentialAccelMinProperty")
        set(value) = setParamMin(CPUParticles2D.Parameter.TANGENTIAL_ACCEL, value)

    var tangentialAccelMax: Double
        @JvmName("tangentialAccelMaxProperty")
        get() = getParamMax(CPUParticles2D.Parameter.TANGENTIAL_ACCEL)
        @JvmName("setTangentialAccelMaxProperty")
        set(value) = setParamMax(CPUParticles2D.Parameter.TANGENTIAL_ACCEL, value)

    var tangentialAccelCurve: Curve?
        @JvmName("tangentialAccelCurveProperty")
        get() = getParamCurve(CPUParticles2D.Parameter.TANGENTIAL_ACCEL)
        @JvmName("setTangentialAccelCurveProperty")
        set(value) = setParamCurve(CPUParticles2D.Parameter.TANGENTIAL_ACCEL, value)

    var dampingMin: Double
        @JvmName("dampingMinProperty")
        get() = getParamMin(CPUParticles2D.Parameter.DAMPING)
        @JvmName("setDampingMinProperty")
        set(value) = setParamMin(CPUParticles2D.Parameter.DAMPING, value)

    var dampingMax: Double
        @JvmName("dampingMaxProperty")
        get() = getParamMax(CPUParticles2D.Parameter.DAMPING)
        @JvmName("setDampingMaxProperty")
        set(value) = setParamMax(CPUParticles2D.Parameter.DAMPING, value)

    var dampingCurve: Curve?
        @JvmName("dampingCurveProperty")
        get() = getParamCurve(CPUParticles2D.Parameter.DAMPING)
        @JvmName("setDampingCurveProperty")
        set(value) = setParamCurve(CPUParticles2D.Parameter.DAMPING, value)

    var angleMin: Double
        @JvmName("angleMinProperty")
        get() = getParamMin(CPUParticles2D.Parameter.ANGLE)
        @JvmName("setAngleMinProperty")
        set(value) = setParamMin(CPUParticles2D.Parameter.ANGLE, value)

    var angleMax: Double
        @JvmName("angleMaxProperty")
        get() = getParamMax(CPUParticles2D.Parameter.ANGLE)
        @JvmName("setAngleMaxProperty")
        set(value) = setParamMax(CPUParticles2D.Parameter.ANGLE, value)

    var angleCurve: Curve?
        @JvmName("angleCurveProperty")
        get() = getParamCurve(CPUParticles2D.Parameter.ANGLE)
        @JvmName("setAngleCurveProperty")
        set(value) = setParamCurve(CPUParticles2D.Parameter.ANGLE, value)

    var scaleAmountMin: Double
        @JvmName("scaleAmountMinProperty")
        get() = getParamMin(CPUParticles2D.Parameter.SCALE)
        @JvmName("setScaleAmountMinProperty")
        set(value) = setParamMin(CPUParticles2D.Parameter.SCALE, value)

    var scaleAmountMax: Double
        @JvmName("scaleAmountMaxProperty")
        get() = getParamMax(CPUParticles2D.Parameter.SCALE)
        @JvmName("setScaleAmountMaxProperty")
        set(value) = setParamMax(CPUParticles2D.Parameter.SCALE, value)

    var scaleAmountCurve: Curve?
        @JvmName("scaleAmountCurveProperty")
        get() = getParamCurve(CPUParticles2D.Parameter.SCALE)
        @JvmName("setScaleAmountCurveProperty")
        set(value) = setParamCurve(CPUParticles2D.Parameter.SCALE, value)

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

    var hueVariationMin: Double
        @JvmName("hueVariationMinProperty")
        get() = getParamMin(CPUParticles2D.Parameter.HUE_VARIATION)
        @JvmName("setHueVariationMinProperty")
        set(value) = setParamMin(CPUParticles2D.Parameter.HUE_VARIATION, value)

    var hueVariationMax: Double
        @JvmName("hueVariationMaxProperty")
        get() = getParamMax(CPUParticles2D.Parameter.HUE_VARIATION)
        @JvmName("setHueVariationMaxProperty")
        set(value) = setParamMax(CPUParticles2D.Parameter.HUE_VARIATION, value)

    var hueVariationCurve: Curve?
        @JvmName("hueVariationCurveProperty")
        get() = getParamCurve(CPUParticles2D.Parameter.HUE_VARIATION)
        @JvmName("setHueVariationCurveProperty")
        set(value) = setParamCurve(CPUParticles2D.Parameter.HUE_VARIATION, value)

    var animSpeedMin: Double
        @JvmName("animSpeedMinProperty")
        get() = getParamMin(CPUParticles2D.Parameter.ANIM_SPEED)
        @JvmName("setAnimSpeedMinProperty")
        set(value) = setParamMin(CPUParticles2D.Parameter.ANIM_SPEED, value)

    var animSpeedMax: Double
        @JvmName("animSpeedMaxProperty")
        get() = getParamMax(CPUParticles2D.Parameter.ANIM_SPEED)
        @JvmName("setAnimSpeedMaxProperty")
        set(value) = setParamMax(CPUParticles2D.Parameter.ANIM_SPEED, value)

    var animSpeedCurve: Curve?
        @JvmName("animSpeedCurveProperty")
        get() = getParamCurve(CPUParticles2D.Parameter.ANIM_SPEED)
        @JvmName("setAnimSpeedCurveProperty")
        set(value) = setParamCurve(CPUParticles2D.Parameter.ANIM_SPEED, value)

    var animOffsetMin: Double
        @JvmName("animOffsetMinProperty")
        get() = getParamMin(CPUParticles2D.Parameter.ANIM_OFFSET)
        @JvmName("setAnimOffsetMinProperty")
        set(value) = setParamMin(CPUParticles2D.Parameter.ANIM_OFFSET, value)

    var animOffsetMax: Double
        @JvmName("animOffsetMaxProperty")
        get() = getParamMax(CPUParticles2D.Parameter.ANIM_OFFSET)
        @JvmName("setAnimOffsetMaxProperty")
        set(value) = setParamMax(CPUParticles2D.Parameter.ANIM_OFFSET, value)

    var animOffsetCurve: Curve?
        @JvmName("animOffsetCurveProperty")
        get() = getParamCurve(CPUParticles2D.Parameter.ANIM_OFFSET)
        @JvmName("setAnimOffsetCurveProperty")
        set(value) = setParamCurve(CPUParticles2D.Parameter.ANIM_OFFSET, value)

    /**
     * If `true`, particles are being emitted. `emitting` can be used to start and stop particles from
     * emitting. However, if `one_shot` is `true` setting `emitting` to `true` will not restart the
     * emission cycle until after all active particles finish processing. You can use the `finished`
     * signal to be notified once all active particles finish processing.
     *
     * Generated from Godot docs: CPUParticles2D.set_emitting
     */
    fun setEmitting(emitting: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEmittingBind, segment, emitting)
    }

    /**
     * Number of particles emitted in one emission cycle.
     *
     * Generated from Godot docs: CPUParticles2D.set_amount
     */
    fun setAmount(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setAmountBind, segment, amount)
    }

    /**
     * Amount of time each particle will exist.
     *
     * Generated from Godot docs: CPUParticles2D.set_lifetime
     */
    fun setLifetime(secs: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLifetimeBind, segment, secs)
    }

    /**
     * If `true`, only one emission cycle occurs. If set `true` during a cycle, emission will stop at
     * the cycle's end.
     *
     * Generated from Godot docs: CPUParticles2D.set_one_shot
     */
    fun setOneShot(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setOneShotBind, segment, enable)
    }

    /**
     * Particle system starts as if it had already run for this many seconds.
     *
     * Generated from Godot docs: CPUParticles2D.set_pre_process_time
     */
    fun setPreProcessTime(secs: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPreProcessTimeBind, segment, secs)
    }

    /**
     * How rapidly particles in an emission cycle are emitted. If greater than `0`, there will be a gap
     * in emissions before the next cycle begins.
     *
     * Generated from Godot docs: CPUParticles2D.set_explosiveness_ratio
     */
    fun setExplosivenessRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setExplosivenessRatioBind, segment, ratio)
    }

    /**
     * Emission lifetime randomness ratio.
     *
     * Generated from Godot docs: CPUParticles2D.set_randomness_ratio
     */
    fun setRandomnessRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRandomnessRatioBind, segment, ratio)
    }

    /**
     * Particle lifetime randomness ratio.
     *
     * Generated from Godot docs: CPUParticles2D.set_lifetime_randomness
     */
    fun setLifetimeRandomness(random: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLifetimeRandomnessBind, segment, random)
    }

    /**
     * If `true`, particles use the parent node's coordinate space (known as local coordinates). This
     * will cause particles to move and rotate along the `CPUParticles2D` node (and its parents) when
     * it is moved or rotated. If `false`, particles use global coordinates; they will not move or
     * rotate along the `CPUParticles2D` node (and its parents) when it is moved or rotated.
     *
     * Generated from Godot docs: CPUParticles2D.set_use_local_coordinates
     */
    fun setUseLocalCoordinates(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseLocalCoordinatesBind, segment, enable)
    }

    /**
     * The particle system's frame rate is fixed to a value. For example, changing the value to 2 will
     * make the particles render at 2 frames per second. Note this does not slow down the simulation of
     * the particle system itself.
     *
     * Generated from Godot docs: CPUParticles2D.set_fixed_fps
     */
    fun setFixedFps(fps: Int) {
        ObjectCalls.ptrcallWithIntArg(setFixedFpsBind, segment, fps)
    }

    /**
     * If `true`, results in fractional delta calculation which has a smoother particles display
     * effect.
     *
     * Generated from Godot docs: CPUParticles2D.set_fractional_delta
     */
    fun setFractionalDelta(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFractionalDeltaBind, segment, enable)
    }

    /**
     * Particle system's running speed scaling ratio. A value of `0` can be used to pause the
     * particles.
     *
     * Generated from Godot docs: CPUParticles2D.set_speed_scale
     */
    fun setSpeedScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSpeedScaleBind, segment, scale)
    }

    /**
     * Requests the particles to process for extra process time during a single frame. `process_time`
     * defines the time that the particles will process while emitting is on. `process_time_residual`
     * defines the time that particles will process with emitting turned off for the simulation. When
     * combined with `speed_scale` set to `0.0`, this is useful to be able to seek a particle system
     * timeline.
     *
     * Generated from Godot docs: CPUParticles2D.request_particles_process
     */
    fun requestParticlesProcess(processTime: Double, processTimeResidual: Double = 0.0) {
        ObjectCalls.ptrcallWithTwoDoubleArgs(requestParticlesProcessBind, segment, processTime, processTimeResidual)
    }

    /**
     * If `true`, particles are being emitted. `emitting` can be used to start and stop particles from
     * emitting. However, if `one_shot` is `true` setting `emitting` to `true` will not restart the
     * emission cycle until after all active particles finish processing. You can use the `finished`
     * signal to be notified once all active particles finish processing.
     *
     * Generated from Godot docs: CPUParticles2D.is_emitting
     */
    fun isEmitting(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEmittingBind, segment)
    }

    /**
     * Number of particles emitted in one emission cycle.
     *
     * Generated from Godot docs: CPUParticles2D.get_amount
     */
    fun getAmount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getAmountBind, segment)
    }

    /**
     * Amount of time each particle will exist.
     *
     * Generated from Godot docs: CPUParticles2D.get_lifetime
     */
    fun getLifetime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLifetimeBind, segment)
    }

    /**
     * If `true`, only one emission cycle occurs. If set `true` during a cycle, emission will stop at
     * the cycle's end.
     *
     * Generated from Godot docs: CPUParticles2D.get_one_shot
     */
    fun getOneShot(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getOneShotBind, segment)
    }

    /**
     * Particle system starts as if it had already run for this many seconds.
     *
     * Generated from Godot docs: CPUParticles2D.get_pre_process_time
     */
    fun getPreProcessTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPreProcessTimeBind, segment)
    }

    /**
     * How rapidly particles in an emission cycle are emitted. If greater than `0`, there will be a gap
     * in emissions before the next cycle begins.
     *
     * Generated from Godot docs: CPUParticles2D.get_explosiveness_ratio
     */
    fun getExplosivenessRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getExplosivenessRatioBind, segment)
    }

    /**
     * Emission lifetime randomness ratio.
     *
     * Generated from Godot docs: CPUParticles2D.get_randomness_ratio
     */
    fun getRandomnessRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRandomnessRatioBind, segment)
    }

    /**
     * Particle lifetime randomness ratio.
     *
     * Generated from Godot docs: CPUParticles2D.get_lifetime_randomness
     */
    fun getLifetimeRandomness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLifetimeRandomnessBind, segment)
    }

    /**
     * If `true`, particles use the parent node's coordinate space (known as local coordinates). This
     * will cause particles to move and rotate along the `CPUParticles2D` node (and its parents) when
     * it is moved or rotated. If `false`, particles use global coordinates; they will not move or
     * rotate along the `CPUParticles2D` node (and its parents) when it is moved or rotated.
     *
     * Generated from Godot docs: CPUParticles2D.get_use_local_coordinates
     */
    fun getUseLocalCoordinates(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseLocalCoordinatesBind, segment)
    }

    /**
     * The particle system's frame rate is fixed to a value. For example, changing the value to 2 will
     * make the particles render at 2 frames per second. Note this does not slow down the simulation of
     * the particle system itself.
     *
     * Generated from Godot docs: CPUParticles2D.get_fixed_fps
     */
    fun getFixedFps(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFixedFpsBind, segment)
    }

    /**
     * If `true`, results in fractional delta calculation which has a smoother particles display
     * effect.
     *
     * Generated from Godot docs: CPUParticles2D.get_fractional_delta
     */
    fun getFractionalDelta(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFractionalDeltaBind, segment)
    }

    /**
     * Particle system's running speed scaling ratio. A value of `0` can be used to pause the
     * particles.
     *
     * Generated from Godot docs: CPUParticles2D.get_speed_scale
     */
    fun getSpeedScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSpeedScaleBind, segment)
    }

    /**
     * If `true`, particles will use the same seed for every simulation using the seed defined in
     * `seed`. This is useful for situations where the visual outcome should be consistent across
     * replays, for example when using Movie Maker mode.
     *
     * Generated from Godot docs: CPUParticles2D.set_use_fixed_seed
     */
    fun setUseFixedSeed(useFixedSeed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseFixedSeedBind, segment, useFixedSeed)
    }

    /**
     * If `true`, particles will use the same seed for every simulation using the seed defined in
     * `seed`. This is useful for situations where the visual outcome should be consistent across
     * replays, for example when using Movie Maker mode.
     *
     * Generated from Godot docs: CPUParticles2D.get_use_fixed_seed
     */
    fun getUseFixedSeed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseFixedSeedBind, segment)
    }

    /**
     * Sets the random seed used by the particle system. Only effective if `use_fixed_seed` is `true`.
     *
     * Generated from Godot docs: CPUParticles2D.set_seed
     */
    fun setSeed(seed: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setSeedBind, segment, seed)
    }

    /**
     * Sets the random seed used by the particle system. Only effective if `use_fixed_seed` is `true`.
     *
     * Generated from Godot docs: CPUParticles2D.get_seed
     */
    fun getSeed(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getSeedBind, segment)
    }

    /**
     * Particle draw order.
     *
     * Generated from Godot docs: CPUParticles2D.set_draw_order
     */
    fun setDrawOrder(order: CPUParticles2D.DrawOrder) {
        ObjectCalls.ptrcallWithLongArg(setDrawOrderBind, segment, order.value)
    }

    /**
     * Particle draw order.
     *
     * Generated from Godot docs: CPUParticles2D.get_draw_order
     */
    fun getDrawOrder(): CPUParticles2D.DrawOrder {
        return CPUParticles2D.DrawOrder(ObjectCalls.ptrcallNoArgsRetLong(getDrawOrderBind, segment))
    }

    /**
     * Particle texture. If `null`, particles will be squares.
     *
     * Generated from Godot docs: CPUParticles2D.set_texture
     */
    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Particle texture. If `null`, particles will be squares.
     *
     * Generated from Godot docs: CPUParticles2D.get_texture
     */
    fun getTexture(): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, segment))
    }

    /**
     * Restarts the particle emitter. If `keep_seed` is `true`, the current random seed will be
     * preserved. Useful for seeking and playback.
     *
     * Generated from Godot docs: CPUParticles2D.restart
     */
    fun restart(keepSeed: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(restartBind, segment, keepSeed)
    }

    /**
     * Unit vector specifying the particles' emission direction.
     *
     * Generated from Godot docs: CPUParticles2D.set_direction
     */
    fun setDirection(direction: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setDirectionBind, segment, direction)
    }

    /**
     * Unit vector specifying the particles' emission direction.
     *
     * Generated from Godot docs: CPUParticles2D.get_direction
     */
    fun getDirection(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getDirectionBind, segment)
    }

    /**
     * Each particle's initial direction range from `+spread` to `-spread` degrees.
     *
     * Generated from Godot docs: CPUParticles2D.set_spread
     */
    fun setSpread(spread: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSpreadBind, segment, spread)
    }

    /**
     * Each particle's initial direction range from `+spread` to `-spread` degrees.
     *
     * Generated from Godot docs: CPUParticles2D.get_spread
     */
    fun getSpread(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSpreadBind, segment)
    }

    /**
     * Minimum equivalent of `tangential_accel_max`.
     *
     * Generated from Godot docs: CPUParticles2D.set_param_min
     */
    fun setParamMin(param: CPUParticles2D.Parameter, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamMinBind, segment, param.value, value)
    }

    /**
     * Minimum equivalent of `tangential_accel_max`.
     *
     * Generated from Godot docs: CPUParticles2D.get_param_min
     */
    fun getParamMin(param: CPUParticles2D.Parameter): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamMinBind, segment, param.value)
    }

    /**
     * Maximum tangential acceleration applied to each particle. Tangential acceleration is
     * perpendicular to the particle's velocity giving the particles a swirling motion.
     *
     * Generated from Godot docs: CPUParticles2D.set_param_max
     */
    fun setParamMax(param: CPUParticles2D.Parameter, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamMaxBind, segment, param.value, value)
    }

    /**
     * Maximum tangential acceleration applied to each particle. Tangential acceleration is
     * perpendicular to the particle's velocity giving the particles a swirling motion.
     *
     * Generated from Godot docs: CPUParticles2D.get_param_max
     */
    fun getParamMax(param: CPUParticles2D.Parameter): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamMaxBind, segment, param.value)
    }

    /**
     * Each particle's tangential acceleration will vary along this `Curve`. Should be a unit `Curve`.
     *
     * Generated from Godot docs: CPUParticles2D.set_param_curve
     */
    fun setParamCurve(param: CPUParticles2D.Parameter, curve: Curve?) {
        ObjectCalls.ptrcallWithLongAndObjectArg(setParamCurveBind, segment, param.value, curve?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Each particle's tangential acceleration will vary along this `Curve`. Should be a unit `Curve`.
     *
     * Generated from Godot docs: CPUParticles2D.get_param_curve
     */
    fun getParamCurve(param: CPUParticles2D.Parameter): Curve? {
        return Curve.wrapOwned(ObjectCalls.ptrcallWithLongArgRetObject(getParamCurveBind, segment, param.value))
    }

    /**
     * Each particle's initial color. If `texture` is defined, it will be multiplied by this color.
     *
     * Generated from Godot docs: CPUParticles2D.set_color
     */
    fun setColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setColorBind, segment, color)
    }

    /**
     * Each particle's initial color. If `texture` is defined, it will be multiplied by this color.
     *
     * Generated from Godot docs: CPUParticles2D.get_color
     */
    fun getColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getColorBind, segment)
    }

    /**
     * Each particle's color will vary along this `Gradient` over its lifetime (multiplied with
     * `color`).
     *
     * Generated from Godot docs: CPUParticles2D.set_color_ramp
     */
    fun setColorRamp(ramp: Gradient?) {
        ObjectCalls.ptrcallWithObjectArgs(setColorRampBind, segment, listOf(ramp?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Each particle's color will vary along this `Gradient` over its lifetime (multiplied with
     * `color`).
     *
     * Generated from Godot docs: CPUParticles2D.get_color_ramp
     */
    fun getColorRamp(): Gradient? {
        return Gradient.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(getColorRampBind, segment))
    }

    /**
     * Each particle's initial color will vary along this `Gradient` (multiplied with `color`).
     *
     * Generated from Godot docs: CPUParticles2D.set_color_initial_ramp
     */
    fun setColorInitialRamp(ramp: Gradient?) {
        ObjectCalls.ptrcallWithObjectArgs(setColorInitialRampBind, segment, listOf(ramp?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Each particle's initial color will vary along this `Gradient` (multiplied with `color`).
     *
     * Generated from Godot docs: CPUParticles2D.get_color_initial_ramp
     */
    fun getColorInitialRamp(): Gradient? {
        return Gradient.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(getColorInitialRampBind, segment))
    }

    /**
     * Align Y axis of particle with the direction of its velocity.
     *
     * Generated from Godot docs: CPUParticles2D.set_particle_flag
     */
    fun setParticleFlag(particleFlag: CPUParticles2D.ParticleFlags, enable: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setParticleFlagBind, segment, particleFlag.value, enable)
    }

    /**
     * Align Y axis of particle with the direction of its velocity.
     *
     * Generated from Godot docs: CPUParticles2D.get_particle_flag
     */
    fun getParticleFlag(particleFlag: CPUParticles2D.ParticleFlags): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getParticleFlagBind, segment, particleFlag.value)
    }

    /**
     * Particles will be emitted inside this region.
     *
     * Generated from Godot docs: CPUParticles2D.set_emission_shape
     */
    fun setEmissionShape(shape: CPUParticles2D.EmissionShape) {
        ObjectCalls.ptrcallWithLongArg(setEmissionShapeBind, segment, shape.value)
    }

    /**
     * Particles will be emitted inside this region.
     *
     * Generated from Godot docs: CPUParticles2D.get_emission_shape
     */
    fun getEmissionShape(): CPUParticles2D.EmissionShape {
        return CPUParticles2D.EmissionShape(ObjectCalls.ptrcallNoArgsRetLong(getEmissionShapeBind, segment))
    }

    /**
     * The sphere's radius if `emission_shape` is set to `EmissionShape.SPHERE`.
     *
     * Generated from Godot docs: CPUParticles2D.set_emission_sphere_radius
     */
    fun setEmissionSphereRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionSphereRadiusBind, segment, radius)
    }

    /**
     * The sphere's radius if `emission_shape` is set to `EmissionShape.SPHERE`.
     *
     * Generated from Godot docs: CPUParticles2D.get_emission_sphere_radius
     */
    fun getEmissionSphereRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionSphereRadiusBind, segment)
    }

    /**
     * The rectangle's extents if `emission_shape` is set to `EmissionShape.RECTANGLE`.
     *
     * Generated from Godot docs: CPUParticles2D.set_emission_rect_extents
     */
    fun setEmissionRectExtents(extents: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setEmissionRectExtentsBind, segment, extents)
    }

    /**
     * The rectangle's extents if `emission_shape` is set to `EmissionShape.RECTANGLE`.
     *
     * Generated from Godot docs: CPUParticles2D.get_emission_rect_extents
     */
    fun getEmissionRectExtents(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getEmissionRectExtentsBind, segment)
    }

    /**
     * Sets the initial positions to spawn particles when using `EmissionShape.POINTS` or
     * `EmissionShape.DIRECTED_POINTS`.
     *
     * Generated from Godot docs: CPUParticles2D.set_emission_points
     */
    fun setEmissionPoints(array: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(setEmissionPointsBind, segment, array)
    }

    /**
     * Sets the initial positions to spawn particles when using `EmissionShape.POINTS` or
     * `EmissionShape.DIRECTED_POINTS`.
     *
     * Generated from Godot docs: CPUParticles2D.get_emission_points
     */
    fun getEmissionPoints(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getEmissionPointsBind, segment)
    }

    /**
     * Sets the direction the particles will be emitted in when using `EmissionShape.DIRECTED_POINTS`.
     *
     * Generated from Godot docs: CPUParticles2D.set_emission_normals
     */
    fun setEmissionNormals(array: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(setEmissionNormalsBind, segment, array)
    }

    /**
     * Sets the direction the particles will be emitted in when using `EmissionShape.DIRECTED_POINTS`.
     *
     * Generated from Godot docs: CPUParticles2D.get_emission_normals
     */
    fun getEmissionNormals(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getEmissionNormalsBind, segment)
    }

    /**
     * Sets the `Color`s to modulate particles by when using `EmissionShape.POINTS` or
     * `EmissionShape.DIRECTED_POINTS`.
     *
     * Generated from Godot docs: CPUParticles2D.set_emission_colors
     */
    fun setEmissionColors(array: List<Color>) {
        ObjectCalls.ptrcallWithPackedColorListArg(setEmissionColorsBind, segment, array)
    }

    /**
     * Sets the `Color`s to modulate particles by when using `EmissionShape.POINTS` or
     * `EmissionShape.DIRECTED_POINTS`.
     *
     * Generated from Godot docs: CPUParticles2D.get_emission_colors
     */
    fun getEmissionColors(): List<Color> {
        return ObjectCalls.ptrcallNoArgsRetPackedColorList(getEmissionColorsBind, segment)
    }

    /**
     * The ring's inner radius if `emission_shape` is set to `EmissionShape.RING`.
     *
     * Generated from Godot docs: CPUParticles2D.set_emission_ring_inner_radius
     */
    fun setEmissionRingInnerRadius(innerRadius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionRingInnerRadiusBind, segment, innerRadius)
    }

    /**
     * The ring's inner radius if `emission_shape` is set to `EmissionShape.RING`.
     *
     * Generated from Godot docs: CPUParticles2D.get_emission_ring_inner_radius
     */
    fun getEmissionRingInnerRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionRingInnerRadiusBind, segment)
    }

    /**
     * The ring's outer radius if `emission_shape` is set to `EmissionShape.RING`.
     *
     * Generated from Godot docs: CPUParticles2D.set_emission_ring_radius
     */
    fun setEmissionRingRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionRingRadiusBind, segment, radius)
    }

    /**
     * The ring's outer radius if `emission_shape` is set to `EmissionShape.RING`.
     *
     * Generated from Godot docs: CPUParticles2D.get_emission_ring_radius
     */
    fun getEmissionRingRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionRingRadiusBind, segment)
    }

    /**
     * Gravity applied to every particle.
     *
     * Generated from Godot docs: CPUParticles2D.get_gravity
     */
    fun getGravity(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getGravityBind, segment)
    }

    /**
     * Gravity applied to every particle.
     *
     * Generated from Godot docs: CPUParticles2D.set_gravity
     */
    fun setGravity(accelVec: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setGravityBind, segment, accelVec)
    }

    /**
     * If `true`, the scale curve will be split into x and y components. See `scale_curve_x` and
     * `scale_curve_y`.
     *
     * Generated from Godot docs: CPUParticles2D.get_split_scale
     */
    fun getSplitScale(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSplitScaleBind, segment)
    }

    /**
     * If `true`, the scale curve will be split into x and y components. See `scale_curve_x` and
     * `scale_curve_y`.
     *
     * Generated from Godot docs: CPUParticles2D.set_split_scale
     */
    fun setSplitScale(splitScale: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSplitScaleBind, segment, splitScale)
    }

    /**
     * Each particle's horizontal scale will vary along this `Curve`. Should be a unit `Curve`.
     * `split_scale` must be enabled.
     *
     * Generated from Godot docs: CPUParticles2D.get_scale_curve_x
     */
    fun getScaleCurveX(): Curve? {
        return Curve.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(getScaleCurveXBind, segment))
    }

    /**
     * Each particle's horizontal scale will vary along this `Curve`. Should be a unit `Curve`.
     * `split_scale` must be enabled.
     *
     * Generated from Godot docs: CPUParticles2D.set_scale_curve_x
     */
    fun setScaleCurveX(scaleCurve: Curve?) {
        ObjectCalls.ptrcallWithObjectArgs(setScaleCurveXBind, segment, listOf(scaleCurve?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Each particle's vertical scale will vary along this `Curve`. Should be a unit `Curve`.
     * `split_scale` must be enabled.
     *
     * Generated from Godot docs: CPUParticles2D.get_scale_curve_y
     */
    fun getScaleCurveY(): Curve? {
        return Curve.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(getScaleCurveYBind, segment))
    }

    /**
     * Each particle's vertical scale will vary along this `Curve`. Should be a unit `Curve`.
     * `split_scale` must be enabled.
     *
     * Generated from Godot docs: CPUParticles2D.set_scale_curve_y
     */
    fun setScaleCurveY(scaleCurve: Curve?) {
        ObjectCalls.ptrcallWithObjectArgs(setScaleCurveYBind, segment, listOf(scaleCurve?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Sets this node's properties to match a given `GPUParticles2D` node with an assigned
     * `ParticleProcessMaterial`.
     *
     * Generated from Godot docs: CPUParticles2D.convert_from_particles
     */
    fun convertFromParticles(particles: Node) {
        ObjectCalls.ptrcallWithObjectArgs(convertFromParticlesBind, segment, listOf(particles.segment))
    }

    object Signals {
        const val finished: String = "finished"
    }

    /**
     * Godot's `CPUParticles2D.DrawOrder` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`CPUParticles2D.DrawOrder.<NAME>`).
     *
     * Generated from Godot docs: CPUParticles2D.DrawOrder
     */
    @JvmInline
    value class DrawOrder(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Particles are drawn in the order emitted.
             *
             * Generated from Godot docs: CPUParticles2D.DRAW_ORDER_INDEX
             */
            val INDEX: DrawOrder get() = DrawOrder(0L)
            /**
             * Particles are drawn in order of remaining lifetime. In other words, the particle with the
             * highest lifetime is drawn at the front.
             *
             * Generated from Godot docs: CPUParticles2D.DRAW_ORDER_LIFETIME
             */
            val LIFETIME: DrawOrder get() = DrawOrder(1L)
        }
    }

    /**
     * Godot's `CPUParticles2D.Parameter` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`CPUParticles2D.Parameter.<NAME>`).
     *
     * Generated from Godot docs: CPUParticles2D.Parameter
     */
    @JvmInline
    value class Parameter(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_curve` to set initial velocity
             * properties.
             *
             * Generated from Godot docs: CPUParticles2D.PARAM_INITIAL_LINEAR_VELOCITY
             */
            val INITIAL_LINEAR_VELOCITY: Parameter get() = Parameter(0L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_curve` to set angular velocity
             * properties.
             *
             * Generated from Godot docs: CPUParticles2D.PARAM_ANGULAR_VELOCITY
             */
            val ANGULAR_VELOCITY: Parameter get() = Parameter(1L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_curve` to set orbital velocity
             * properties.
             *
             * Generated from Godot docs: CPUParticles2D.PARAM_ORBIT_VELOCITY
             */
            val ORBIT_VELOCITY: Parameter get() = Parameter(2L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_curve` to set linear acceleration
             * properties.
             *
             * Generated from Godot docs: CPUParticles2D.PARAM_LINEAR_ACCEL
             */
            val LINEAR_ACCEL: Parameter get() = Parameter(3L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_curve` to set radial acceleration
             * properties.
             *
             * Generated from Godot docs: CPUParticles2D.PARAM_RADIAL_ACCEL
             */
            val RADIAL_ACCEL: Parameter get() = Parameter(4L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_curve` to set tangential acceleration
             * properties.
             *
             * Generated from Godot docs: CPUParticles2D.PARAM_TANGENTIAL_ACCEL
             */
            val TANGENTIAL_ACCEL: Parameter get() = Parameter(5L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_curve` to set damping properties.
             *
             * Generated from Godot docs: CPUParticles2D.PARAM_DAMPING
             */
            val DAMPING: Parameter get() = Parameter(6L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_curve` to set angle properties.
             *
             * Generated from Godot docs: CPUParticles2D.PARAM_ANGLE
             */
            val ANGLE: Parameter get() = Parameter(7L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_curve` to set scale properties.
             *
             * Generated from Godot docs: CPUParticles2D.PARAM_SCALE
             */
            val SCALE: Parameter get() = Parameter(8L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_curve` to set hue variation
             * properties.
             *
             * Generated from Godot docs: CPUParticles2D.PARAM_HUE_VARIATION
             */
            val HUE_VARIATION: Parameter get() = Parameter(9L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_curve` to set animation speed
             * properties.
             *
             * Generated from Godot docs: CPUParticles2D.PARAM_ANIM_SPEED
             */
            val ANIM_SPEED: Parameter get() = Parameter(10L)
            /**
             * Use with `set_param_min`, `set_param_max`, and `set_param_curve` to set animation offset
             * properties.
             *
             * Generated from Godot docs: CPUParticles2D.PARAM_ANIM_OFFSET
             */
            val ANIM_OFFSET: Parameter get() = Parameter(11L)
            /**
             * Represents the size of the `Parameter` enum.
             *
             * Generated from Godot docs: CPUParticles2D.PARAM_MAX
             */
            val MAX: Parameter get() = Parameter(12L)
        }
    }

    /**
     * Godot's `CPUParticles2D.ParticleFlags` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`CPUParticles2D.ParticleFlags.<NAME>`).
     *
     * Generated from Godot docs: CPUParticles2D.ParticleFlags
     */
    @JvmInline
    value class ParticleFlags(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use with `set_particle_flag` to set `particle_flag_align_y`.
             *
             * Generated from Godot docs: CPUParticles2D.PARTICLE_FLAG_ALIGN_Y_TO_VELOCITY
             */
            val ALIGN_Y_TO_VELOCITY: ParticleFlags get() = ParticleFlags(0L)
            /**
             * Present for consistency with 3D particle nodes, not used in 2D.
             *
             * Generated from Godot docs: CPUParticles2D.PARTICLE_FLAG_ROTATE_Y
             */
            val ROTATE_Y: ParticleFlags get() = ParticleFlags(1L)
            /**
             * Present for consistency with 3D particle nodes, not used in 2D.
             *
             * Generated from Godot docs: CPUParticles2D.PARTICLE_FLAG_DISABLE_Z
             */
            val DISABLE_Z: ParticleFlags get() = ParticleFlags(2L)
            /**
             * Represents the size of the `ParticleFlags` enum.
             *
             * Generated from Godot docs: CPUParticles2D.PARTICLE_FLAG_MAX
             */
            val MAX: ParticleFlags get() = ParticleFlags(3L)
        }
    }

    /**
     * Godot's `CPUParticles2D.EmissionShape` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`CPUParticles2D.EmissionShape.<NAME>`).
     *
     * Generated from Godot docs: CPUParticles2D.EmissionShape
     */
    @JvmInline
    value class EmissionShape(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * All particles will be emitted from a single point.
             *
             * Generated from Godot docs: CPUParticles2D.EMISSION_SHAPE_POINT
             */
            val POINT: EmissionShape get() = EmissionShape(0L)
            /**
             * Particles will be emitted in the volume of a sphere flattened to two dimensions.
             *
             * Generated from Godot docs: CPUParticles2D.EMISSION_SHAPE_SPHERE
             */
            val SPHERE: EmissionShape get() = EmissionShape(1L)
            /**
             * Particles will be emitted on the surface of a sphere flattened to two dimensions.
             *
             * Generated from Godot docs: CPUParticles2D.EMISSION_SHAPE_SPHERE_SURFACE
             */
            val SPHERE_SURFACE: EmissionShape get() = EmissionShape(2L)
            /**
             * Particles will be emitted in the area of a rectangle.
             *
             * Generated from Godot docs: CPUParticles2D.EMISSION_SHAPE_RECTANGLE
             */
            val RECTANGLE: EmissionShape get() = EmissionShape(3L)
            /**
             * Particles will be emitted at a position chosen randomly among `emission_points`. Particle color
             * will be modulated by `emission_colors`.
             *
             * Generated from Godot docs: CPUParticles2D.EMISSION_SHAPE_POINTS
             */
            val POINTS: EmissionShape get() = EmissionShape(4L)
            /**
             * Particles will be emitted at a position chosen randomly among `emission_points`. Particle
             * velocity and rotation will be set based on `emission_normals`. Particle color will be modulated
             * by `emission_colors`.
             *
             * Generated from Godot docs: CPUParticles2D.EMISSION_SHAPE_DIRECTED_POINTS
             */
            val DIRECTED_POINTS: EmissionShape get() = EmissionShape(5L)
            /**
             * Particles will be emitted in the area of a ring parameterized by its outer and inner radius.
             *
             * Generated from Godot docs: CPUParticles2D.EMISSION_SHAPE_RING
             */
            val RING: EmissionShape get() = EmissionShape(6L)
            /**
             * Represents the size of the `EmissionShape` enum.
             *
             * Generated from Godot docs: CPUParticles2D.EMISSION_SHAPE_MAX
             */
            val MAX: EmissionShape get() = EmissionShape(7L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CPUParticles2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CPUParticles2D? =
            if (handle.address() == 0L) null else CPUParticles2D(GodotHandle(handle))

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
