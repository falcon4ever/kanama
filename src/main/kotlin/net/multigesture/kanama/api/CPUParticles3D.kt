package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector3

/**
 * A CPU-based 3D particle emitter.
 *
 * Generated from Godot docs: CPUParticles3D
 */
class CPUParticles3D(handle: MemorySegment) : GeometryInstance3D(handle) {
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

    var visibilityAabb: AABB
        @JvmName("visibilityAabbProperty")
        get() = getVisibilityAabb()
        @JvmName("setVisibilityAabbProperty")
        set(value) = setVisibilityAabb(value)

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

    var mesh: Mesh?
        @JvmName("meshProperty")
        get() = getMesh()
        @JvmName("setMeshProperty")
        set(value) = setMesh(value)

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

    var emissionBoxExtents: Vector3
        @JvmName("emissionBoxExtentsProperty")
        get() = getEmissionBoxExtents()
        @JvmName("setEmissionBoxExtentsProperty")
        set(value) = setEmissionBoxExtents(value)

    var emissionPoints: List<Vector3>
        @JvmName("emissionPointsProperty")
        get() = getEmissionPoints()
        @JvmName("setEmissionPointsProperty")
        set(value) = setEmissionPoints(value)

    var emissionNormals: List<Vector3>
        @JvmName("emissionNormalsProperty")
        get() = getEmissionNormals()
        @JvmName("setEmissionNormalsProperty")
        set(value) = setEmissionNormals(value)

    var emissionColors: List<Color>
        @JvmName("emissionColorsProperty")
        get() = getEmissionColors()
        @JvmName("setEmissionColorsProperty")
        set(value) = setEmissionColors(value)

    var emissionRingAxis: Vector3
        @JvmName("emissionRingAxisProperty")
        get() = getEmissionRingAxis()
        @JvmName("setEmissionRingAxisProperty")
        set(value) = setEmissionRingAxis(value)

    var emissionRingHeight: Double
        @JvmName("emissionRingHeightProperty")
        get() = getEmissionRingHeight()
        @JvmName("setEmissionRingHeightProperty")
        set(value) = setEmissionRingHeight(value)

    var emissionRingRadius: Double
        @JvmName("emissionRingRadiusProperty")
        get() = getEmissionRingRadius()
        @JvmName("setEmissionRingRadiusProperty")
        set(value) = setEmissionRingRadius(value)

    var emissionRingInnerRadius: Double
        @JvmName("emissionRingInnerRadiusProperty")
        get() = getEmissionRingInnerRadius()
        @JvmName("setEmissionRingInnerRadiusProperty")
        set(value) = setEmissionRingInnerRadius(value)

    var emissionRingConeAngle: Double
        @JvmName("emissionRingConeAngleProperty")
        get() = getEmissionRingConeAngle()
        @JvmName("setEmissionRingConeAngleProperty")
        set(value) = setEmissionRingConeAngle(value)

    var direction: Vector3
        @JvmName("directionProperty")
        get() = getDirection()
        @JvmName("setDirectionProperty")
        set(value) = setDirection(value)

    var spread: Double
        @JvmName("spreadProperty")
        get() = getSpread()
        @JvmName("setSpreadProperty")
        set(value) = setSpread(value)

    var flatness: Double
        @JvmName("flatnessProperty")
        get() = getFlatness()
        @JvmName("setFlatnessProperty")
        set(value) = setFlatness(value)

    var gravity: Vector3
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

    var scaleCurveZ: Curve?
        @JvmName("scaleCurveZProperty")
        get() = getScaleCurveZ()
        @JvmName("setScaleCurveZProperty")
        set(value) = setScaleCurveZ(value)

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

    /**
     * If `true`, particles are being emitted. `emitting` can be used to start and stop particles from
     * emitting. However, if `one_shot` is `true` setting `emitting` to `true` will not restart the
     * emission cycle until after all active particles finish processing. You can use the `finished`
     * signal to be notified once all active particles finish processing.
     *
     * Generated from Godot docs: CPUParticles3D.set_emitting
     */
    fun setEmitting(emitting: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEmittingBind, handle, emitting)
    }

    /**
     * Number of particles emitted in one emission cycle.
     *
     * Generated from Godot docs: CPUParticles3D.set_amount
     */
    fun setAmount(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setAmountBind, handle, amount)
    }

    /**
     * Amount of time each particle will exist.
     *
     * Generated from Godot docs: CPUParticles3D.set_lifetime
     */
    fun setLifetime(secs: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLifetimeBind, handle, secs)
    }

    /**
     * If `true`, only one emission cycle occurs. If set `true` during a cycle, emission will stop at
     * the cycle's end.
     *
     * Generated from Godot docs: CPUParticles3D.set_one_shot
     */
    fun setOneShot(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setOneShotBind, handle, enable)
    }

    /**
     * Particle system starts as if it had already run for this many seconds.
     *
     * Generated from Godot docs: CPUParticles3D.set_pre_process_time
     */
    fun setPreProcessTime(secs: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPreProcessTimeBind, handle, secs)
    }

    /**
     * How rapidly particles in an emission cycle are emitted. If greater than `0`, there will be a gap
     * in emissions before the next cycle begins.
     *
     * Generated from Godot docs: CPUParticles3D.set_explosiveness_ratio
     */
    fun setExplosivenessRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setExplosivenessRatioBind, handle, ratio)
    }

    /**
     * Emission lifetime randomness ratio.
     *
     * Generated from Godot docs: CPUParticles3D.set_randomness_ratio
     */
    fun setRandomnessRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRandomnessRatioBind, handle, ratio)
    }

    /**
     * The `AABB` that determines the node's region which needs to be visible on screen for the
     * particle system to be active. Grow the box if particles suddenly appear/disappear when the node
     * enters/exits the screen. The `AABB` can be grown via code or with the Particles → Generate AABB
     * editor tool.
     *
     * Generated from Godot docs: CPUParticles3D.set_visibility_aabb
     */
    fun setVisibilityAabb(aabb: AABB) {
        ObjectCalls.ptrcallWithAABBArg(setVisibilityAabbBind, handle, aabb)
    }

    /**
     * Particle lifetime randomness ratio.
     *
     * Generated from Godot docs: CPUParticles3D.set_lifetime_randomness
     */
    fun setLifetimeRandomness(random: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLifetimeRandomnessBind, handle, random)
    }

    /**
     * If `true`, particles use the parent node's coordinate space (known as local coordinates). This
     * will cause particles to move and rotate along the `CPUParticles3D` node (and its parents) when
     * it is moved or rotated. If `false`, particles use global coordinates; they will not move or
     * rotate along the `CPUParticles3D` node (and its parents) when it is moved or rotated.
     *
     * Generated from Godot docs: CPUParticles3D.set_use_local_coordinates
     */
    fun setUseLocalCoordinates(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseLocalCoordinatesBind, handle, enable)
    }

    /**
     * The particle system's frame rate is fixed to a value. For example, changing the value to 2 will
     * make the particles render at 2 frames per second. Note this does not slow down the particle
     * system itself.
     *
     * Generated from Godot docs: CPUParticles3D.set_fixed_fps
     */
    fun setFixedFps(fps: Int) {
        ObjectCalls.ptrcallWithIntArg(setFixedFpsBind, handle, fps)
    }

    /**
     * If `true`, results in fractional delta calculation which has a smoother particles display
     * effect.
     *
     * Generated from Godot docs: CPUParticles3D.set_fractional_delta
     */
    fun setFractionalDelta(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFractionalDeltaBind, handle, enable)
    }

    /**
     * Particle system's running speed scaling ratio. A value of `0` can be used to pause the
     * particles.
     *
     * Generated from Godot docs: CPUParticles3D.set_speed_scale
     */
    fun setSpeedScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSpeedScaleBind, handle, scale)
    }

    /**
     * If `true`, particles are being emitted. `emitting` can be used to start and stop particles from
     * emitting. However, if `one_shot` is `true` setting `emitting` to `true` will not restart the
     * emission cycle until after all active particles finish processing. You can use the `finished`
     * signal to be notified once all active particles finish processing.
     *
     * Generated from Godot docs: CPUParticles3D.is_emitting
     */
    fun isEmitting(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEmittingBind, handle)
    }

    /**
     * Number of particles emitted in one emission cycle.
     *
     * Generated from Godot docs: CPUParticles3D.get_amount
     */
    fun getAmount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getAmountBind, handle)
    }

    /**
     * Amount of time each particle will exist.
     *
     * Generated from Godot docs: CPUParticles3D.get_lifetime
     */
    fun getLifetime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLifetimeBind, handle)
    }

    /**
     * If `true`, only one emission cycle occurs. If set `true` during a cycle, emission will stop at
     * the cycle's end.
     *
     * Generated from Godot docs: CPUParticles3D.get_one_shot
     */
    fun getOneShot(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getOneShotBind, handle)
    }

    /**
     * Particle system starts as if it had already run for this many seconds.
     *
     * Generated from Godot docs: CPUParticles3D.get_pre_process_time
     */
    fun getPreProcessTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPreProcessTimeBind, handle)
    }

    /**
     * How rapidly particles in an emission cycle are emitted. If greater than `0`, there will be a gap
     * in emissions before the next cycle begins.
     *
     * Generated from Godot docs: CPUParticles3D.get_explosiveness_ratio
     */
    fun getExplosivenessRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getExplosivenessRatioBind, handle)
    }

    /**
     * Emission lifetime randomness ratio.
     *
     * Generated from Godot docs: CPUParticles3D.get_randomness_ratio
     */
    fun getRandomnessRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRandomnessRatioBind, handle)
    }

    /**
     * The `AABB` that determines the node's region which needs to be visible on screen for the
     * particle system to be active. Grow the box if particles suddenly appear/disappear when the node
     * enters/exits the screen. The `AABB` can be grown via code or with the Particles → Generate AABB
     * editor tool.
     *
     * Generated from Godot docs: CPUParticles3D.get_visibility_aabb
     */
    fun getVisibilityAabb(): AABB {
        return ObjectCalls.ptrcallNoArgsRetAABB(getVisibilityAabbBind, handle)
    }

    /**
     * Particle lifetime randomness ratio.
     *
     * Generated from Godot docs: CPUParticles3D.get_lifetime_randomness
     */
    fun getLifetimeRandomness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLifetimeRandomnessBind, handle)
    }

    /**
     * If `true`, particles use the parent node's coordinate space (known as local coordinates). This
     * will cause particles to move and rotate along the `CPUParticles3D` node (and its parents) when
     * it is moved or rotated. If `false`, particles use global coordinates; they will not move or
     * rotate along the `CPUParticles3D` node (and its parents) when it is moved or rotated.
     *
     * Generated from Godot docs: CPUParticles3D.get_use_local_coordinates
     */
    fun getUseLocalCoordinates(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseLocalCoordinatesBind, handle)
    }

    /**
     * The particle system's frame rate is fixed to a value. For example, changing the value to 2 will
     * make the particles render at 2 frames per second. Note this does not slow down the particle
     * system itself.
     *
     * Generated from Godot docs: CPUParticles3D.get_fixed_fps
     */
    fun getFixedFps(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFixedFpsBind, handle)
    }

    /**
     * If `true`, results in fractional delta calculation which has a smoother particles display
     * effect.
     *
     * Generated from Godot docs: CPUParticles3D.get_fractional_delta
     */
    fun getFractionalDelta(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFractionalDeltaBind, handle)
    }

    /**
     * Particle system's running speed scaling ratio. A value of `0` can be used to pause the
     * particles.
     *
     * Generated from Godot docs: CPUParticles3D.get_speed_scale
     */
    fun getSpeedScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSpeedScaleBind, handle)
    }

    /**
     * Particle draw order.
     *
     * Generated from Godot docs: CPUParticles3D.set_draw_order
     */
    fun setDrawOrder(order: Long) {
        ObjectCalls.ptrcallWithLongArg(setDrawOrderBind, handle, order)
    }

    /**
     * Particle draw order.
     *
     * Generated from Godot docs: CPUParticles3D.get_draw_order
     */
    fun getDrawOrder(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDrawOrderBind, handle)
    }

    /**
     * The `Mesh` used for each particle. If `null`, particles will be spheres.
     *
     * Generated from Godot docs: CPUParticles3D.set_mesh
     */
    fun setMesh(mesh: Mesh?) {
        ObjectCalls.ptrcallWithObjectArgs(setMeshBind, handle, listOf(mesh?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The `Mesh` used for each particle. If `null`, particles will be spheres.
     *
     * Generated from Godot docs: CPUParticles3D.get_mesh
     */
    fun getMesh(): Mesh? {
        return Mesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMeshBind, handle))
    }

    /**
     * If `true`, particles will use the same seed for every simulation using the seed defined in
     * `seed`. This is useful for situations where the visual outcome should be consistent across
     * replays, for example when using Movie Maker mode.
     *
     * Generated from Godot docs: CPUParticles3D.set_use_fixed_seed
     */
    fun setUseFixedSeed(useFixedSeed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseFixedSeedBind, handle, useFixedSeed)
    }

    /**
     * If `true`, particles will use the same seed for every simulation using the seed defined in
     * `seed`. This is useful for situations where the visual outcome should be consistent across
     * replays, for example when using Movie Maker mode.
     *
     * Generated from Godot docs: CPUParticles3D.get_use_fixed_seed
     */
    fun getUseFixedSeed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseFixedSeedBind, handle)
    }

    /**
     * Sets the random seed used by the particle system. Only effective if `use_fixed_seed` is `true`.
     *
     * Generated from Godot docs: CPUParticles3D.set_seed
     */
    fun setSeed(seed: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setSeedBind, handle, seed)
    }

    /**
     * Sets the random seed used by the particle system. Only effective if `use_fixed_seed` is `true`.
     *
     * Generated from Godot docs: CPUParticles3D.get_seed
     */
    fun getSeed(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getSeedBind, handle)
    }

    /**
     * Restarts the particle emitter. If `keep_seed` is `true`, the current random seed will be
     * preserved. Useful for seeking and playback.
     *
     * Generated from Godot docs: CPUParticles3D.restart
     */
    fun restart(keepSeed: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(restartBind, handle, keepSeed)
    }

    /**
     * Requests the particles to process for extra process time during a single frame. `process_time`
     * defines the time that the particles will process while emitting is on. `process_time_residual`
     * defines the time that particles will process with emitting turned off for the simulation. When
     * combined with `speed_scale` set to `0.0`, this is useful to be able to seek a particle system
     * timeline.
     *
     * Generated from Godot docs: CPUParticles3D.request_particles_process
     */
    fun requestParticlesProcess(processTime: Double, processTimeResidual: Double = 0.0) {
        ObjectCalls.ptrcallWithTwoDoubleArgs(requestParticlesProcessBind, handle, processTime, processTimeResidual)
    }

    /**
     * Returns the axis-aligned bounding box that contains all the particles that are active in the
     * current frame.
     *
     * Generated from Godot docs: CPUParticles3D.capture_aabb
     */
    fun captureAabb(): AABB {
        return ObjectCalls.ptrcallNoArgsRetAABB(captureAabbBind, handle)
    }

    /**
     * Unit vector specifying the particles' emission direction.
     *
     * Generated from Godot docs: CPUParticles3D.set_direction
     */
    fun setDirection(direction: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setDirectionBind, handle, direction)
    }

    /**
     * Unit vector specifying the particles' emission direction.
     *
     * Generated from Godot docs: CPUParticles3D.get_direction
     */
    fun getDirection(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getDirectionBind, handle)
    }

    /**
     * Each particle's initial direction range from `+spread` to `-spread` degrees. Applied to X/Z
     * plane and Y/Z planes.
     *
     * Generated from Godot docs: CPUParticles3D.set_spread
     */
    fun setSpread(degrees: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSpreadBind, handle, degrees)
    }

    /**
     * Each particle's initial direction range from `+spread` to `-spread` degrees. Applied to X/Z
     * plane and Y/Z planes.
     *
     * Generated from Godot docs: CPUParticles3D.get_spread
     */
    fun getSpread(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSpreadBind, handle)
    }

    /**
     * Amount of `spread` in Y/Z plane. A value of `1` restricts particles to X/Z plane.
     *
     * Generated from Godot docs: CPUParticles3D.set_flatness
     */
    fun setFlatness(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFlatnessBind, handle, amount)
    }

    /**
     * Amount of `spread` in Y/Z plane. A value of `1` restricts particles to X/Z plane.
     *
     * Generated from Godot docs: CPUParticles3D.get_flatness
     */
    fun getFlatness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFlatnessBind, handle)
    }

    /**
     * Minimum tangent acceleration.
     *
     * Generated from Godot docs: CPUParticles3D.set_param_min
     */
    fun setParamMin(param: Long, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamMinBind, handle, param, value)
    }

    /**
     * Minimum tangent acceleration.
     *
     * Generated from Godot docs: CPUParticles3D.get_param_min
     */
    fun getParamMin(param: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamMinBind, handle, param)
    }

    /**
     * Maximum tangent acceleration.
     *
     * Generated from Godot docs: CPUParticles3D.set_param_max
     */
    fun setParamMax(param: Long, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamMaxBind, handle, param, value)
    }

    /**
     * Maximum tangent acceleration.
     *
     * Generated from Godot docs: CPUParticles3D.get_param_max
     */
    fun getParamMax(param: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamMaxBind, handle, param)
    }

    /**
     * Each particle's tangential acceleration will vary along this `Curve`. Should be a unit `Curve`.
     *
     * Generated from Godot docs: CPUParticles3D.set_param_curve
     */
    fun setParamCurve(param: Long, curve: Curve?) {
        ObjectCalls.ptrcallWithLongAndObjectArg(setParamCurveBind, handle, param, curve?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Each particle's tangential acceleration will vary along this `Curve`. Should be a unit `Curve`.
     *
     * Generated from Godot docs: CPUParticles3D.get_param_curve
     */
    fun getParamCurve(param: Long): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallWithLongArgRetObject(getParamCurveBind, handle, param))
    }

    /**
     * Each particle's initial color. Note: `color` multiplies the particle mesh's vertex colors. To
     * have a visible effect on a `BaseMaterial3D`, `BaseMaterial3D.vertex_color_use_as_albedo` must be
     * `true`. For a `ShaderMaterial`, `ALBEDO *= COLOR.rgb;` must be inserted in the shader's
     * `fragment()` function. Otherwise, `color` will have no visible effect.
     *
     * Generated from Godot docs: CPUParticles3D.set_color
     */
    fun setColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setColorBind, handle, color)
    }

    /**
     * Each particle's initial color. Note: `color` multiplies the particle mesh's vertex colors. To
     * have a visible effect on a `BaseMaterial3D`, `BaseMaterial3D.vertex_color_use_as_albedo` must be
     * `true`. For a `ShaderMaterial`, `ALBEDO *= COLOR.rgb;` must be inserted in the shader's
     * `fragment()` function. Otherwise, `color` will have no visible effect.
     *
     * Generated from Godot docs: CPUParticles3D.get_color
     */
    fun getColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getColorBind, handle)
    }

    /**
     * Each particle's color will vary along this `Gradient` over its lifetime (multiplied with
     * `color`). Note: `color_ramp` multiplies the particle mesh's vertex colors. To have a visible
     * effect on a `BaseMaterial3D`, `BaseMaterial3D.vertex_color_use_as_albedo` must be `true`. For a
     * `ShaderMaterial`, `ALBEDO *= COLOR.rgb;` must be inserted in the shader's `fragment()` function.
     * Otherwise, `color_ramp` will have no visible effect.
     *
     * Generated from Godot docs: CPUParticles3D.set_color_ramp
     */
    fun setColorRamp(ramp: Gradient?) {
        ObjectCalls.ptrcallWithObjectArgs(setColorRampBind, handle, listOf(ramp?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Each particle's color will vary along this `Gradient` over its lifetime (multiplied with
     * `color`). Note: `color_ramp` multiplies the particle mesh's vertex colors. To have a visible
     * effect on a `BaseMaterial3D`, `BaseMaterial3D.vertex_color_use_as_albedo` must be `true`. For a
     * `ShaderMaterial`, `ALBEDO *= COLOR.rgb;` must be inserted in the shader's `fragment()` function.
     * Otherwise, `color_ramp` will have no visible effect.
     *
     * Generated from Godot docs: CPUParticles3D.get_color_ramp
     */
    fun getColorRamp(): Gradient? {
        return Gradient.wrap(ObjectCalls.ptrcallNoArgsRetObject(getColorRampBind, handle))
    }

    /**
     * Each particle's initial color will vary along this `Gradient` (multiplied with `color`). Note:
     * `color_initial_ramp` multiplies the particle mesh's vertex colors. To have a visible effect on a
     * `BaseMaterial3D`, `BaseMaterial3D.vertex_color_use_as_albedo` must be `true`. For a
     * `ShaderMaterial`, `ALBEDO *= COLOR.rgb;` must be inserted in the shader's `fragment()` function.
     * Otherwise, `color_initial_ramp` will have no visible effect.
     *
     * Generated from Godot docs: CPUParticles3D.set_color_initial_ramp
     */
    fun setColorInitialRamp(ramp: Gradient?) {
        ObjectCalls.ptrcallWithObjectArgs(setColorInitialRampBind, handle, listOf(ramp?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Each particle's initial color will vary along this `Gradient` (multiplied with `color`). Note:
     * `color_initial_ramp` multiplies the particle mesh's vertex colors. To have a visible effect on a
     * `BaseMaterial3D`, `BaseMaterial3D.vertex_color_use_as_albedo` must be `true`. For a
     * `ShaderMaterial`, `ALBEDO *= COLOR.rgb;` must be inserted in the shader's `fragment()` function.
     * Otherwise, `color_initial_ramp` will have no visible effect.
     *
     * Generated from Godot docs: CPUParticles3D.get_color_initial_ramp
     */
    fun getColorInitialRamp(): Gradient? {
        return Gradient.wrap(ObjectCalls.ptrcallNoArgsRetObject(getColorInitialRampBind, handle))
    }

    /**
     * If `true`, particles rotate around Y axis by `angle_min`.
     *
     * Generated from Godot docs: CPUParticles3D.set_particle_flag
     */
    fun setParticleFlag(particleFlag: Long, enable: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setParticleFlagBind, handle, particleFlag, enable)
    }

    /**
     * If `true`, particles rotate around Y axis by `angle_min`.
     *
     * Generated from Godot docs: CPUParticles3D.get_particle_flag
     */
    fun getParticleFlag(particleFlag: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getParticleFlagBind, handle, particleFlag)
    }

    /**
     * Particles will be emitted inside this region.
     *
     * Generated from Godot docs: CPUParticles3D.set_emission_shape
     */
    fun setEmissionShape(shape: Long) {
        ObjectCalls.ptrcallWithLongArg(setEmissionShapeBind, handle, shape)
    }

    /**
     * Particles will be emitted inside this region.
     *
     * Generated from Godot docs: CPUParticles3D.get_emission_shape
     */
    fun getEmissionShape(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getEmissionShapeBind, handle)
    }

    /**
     * The sphere's radius if `EmissionShape` is set to `EMISSION_SHAPE_SPHERE`.
     *
     * Generated from Godot docs: CPUParticles3D.set_emission_sphere_radius
     */
    fun setEmissionSphereRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionSphereRadiusBind, handle, radius)
    }

    /**
     * The sphere's radius if `EmissionShape` is set to `EMISSION_SHAPE_SPHERE`.
     *
     * Generated from Godot docs: CPUParticles3D.get_emission_sphere_radius
     */
    fun getEmissionSphereRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionSphereRadiusBind, handle)
    }

    /**
     * The rectangle's extents if `emission_shape` is set to `EMISSION_SHAPE_BOX`.
     *
     * Generated from Godot docs: CPUParticles3D.set_emission_box_extents
     */
    fun setEmissionBoxExtents(extents: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setEmissionBoxExtentsBind, handle, extents)
    }

    /**
     * The rectangle's extents if `emission_shape` is set to `EMISSION_SHAPE_BOX`.
     *
     * Generated from Godot docs: CPUParticles3D.get_emission_box_extents
     */
    fun getEmissionBoxExtents(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getEmissionBoxExtentsBind, handle)
    }

    /**
     * Sets the initial positions to spawn particles when using `EMISSION_SHAPE_POINTS` or
     * `EMISSION_SHAPE_DIRECTED_POINTS`.
     *
     * Generated from Godot docs: CPUParticles3D.set_emission_points
     */
    fun setEmissionPoints(array: List<Vector3>) {
        ObjectCalls.ptrcallWithPackedVector3ListArg(setEmissionPointsBind, handle, array)
    }

    /**
     * Sets the initial positions to spawn particles when using `EMISSION_SHAPE_POINTS` or
     * `EMISSION_SHAPE_DIRECTED_POINTS`.
     *
     * Generated from Godot docs: CPUParticles3D.get_emission_points
     */
    fun getEmissionPoints(): List<Vector3> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getEmissionPointsBind, handle)
    }

    /**
     * Sets the direction the particles will be emitted in when using `EMISSION_SHAPE_DIRECTED_POINTS`.
     *
     * Generated from Godot docs: CPUParticles3D.set_emission_normals
     */
    fun setEmissionNormals(array: List<Vector3>) {
        ObjectCalls.ptrcallWithPackedVector3ListArg(setEmissionNormalsBind, handle, array)
    }

    /**
     * Sets the direction the particles will be emitted in when using `EMISSION_SHAPE_DIRECTED_POINTS`.
     *
     * Generated from Godot docs: CPUParticles3D.get_emission_normals
     */
    fun getEmissionNormals(): List<Vector3> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getEmissionNormalsBind, handle)
    }

    /**
     * Sets the `Color`s to modulate particles by when using `EMISSION_SHAPE_POINTS` or
     * `EMISSION_SHAPE_DIRECTED_POINTS`. Note: `emission_colors` multiplies the particle mesh's vertex
     * colors. To have a visible effect on a `BaseMaterial3D`,
     * `BaseMaterial3D.vertex_color_use_as_albedo` must be `true`. For a `ShaderMaterial`, `ALBEDO *=
     * COLOR.rgb;` must be inserted in the shader's `fragment()` function. Otherwise, `emission_colors`
     * will have no visible effect.
     *
     * Generated from Godot docs: CPUParticles3D.set_emission_colors
     */
    fun setEmissionColors(array: List<Color>) {
        ObjectCalls.ptrcallWithPackedColorListArg(setEmissionColorsBind, handle, array)
    }

    /**
     * Sets the `Color`s to modulate particles by when using `EMISSION_SHAPE_POINTS` or
     * `EMISSION_SHAPE_DIRECTED_POINTS`. Note: `emission_colors` multiplies the particle mesh's vertex
     * colors. To have a visible effect on a `BaseMaterial3D`,
     * `BaseMaterial3D.vertex_color_use_as_albedo` must be `true`. For a `ShaderMaterial`, `ALBEDO *=
     * COLOR.rgb;` must be inserted in the shader's `fragment()` function. Otherwise, `emission_colors`
     * will have no visible effect.
     *
     * Generated from Godot docs: CPUParticles3D.get_emission_colors
     */
    fun getEmissionColors(): List<Color> {
        return ObjectCalls.ptrcallNoArgsRetPackedColorList(getEmissionColorsBind, handle)
    }

    /**
     * The axis of the ring when using the emitter `EMISSION_SHAPE_RING`.
     *
     * Generated from Godot docs: CPUParticles3D.set_emission_ring_axis
     */
    fun setEmissionRingAxis(axis: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setEmissionRingAxisBind, handle, axis)
    }

    /**
     * The axis of the ring when using the emitter `EMISSION_SHAPE_RING`.
     *
     * Generated from Godot docs: CPUParticles3D.get_emission_ring_axis
     */
    fun getEmissionRingAxis(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getEmissionRingAxisBind, handle)
    }

    /**
     * The height of the ring when using the emitter `EMISSION_SHAPE_RING`.
     *
     * Generated from Godot docs: CPUParticles3D.set_emission_ring_height
     */
    fun setEmissionRingHeight(height: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionRingHeightBind, handle, height)
    }

    /**
     * The height of the ring when using the emitter `EMISSION_SHAPE_RING`.
     *
     * Generated from Godot docs: CPUParticles3D.get_emission_ring_height
     */
    fun getEmissionRingHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionRingHeightBind, handle)
    }

    /**
     * The radius of the ring when using the emitter `EMISSION_SHAPE_RING`.
     *
     * Generated from Godot docs: CPUParticles3D.set_emission_ring_radius
     */
    fun setEmissionRingRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionRingRadiusBind, handle, radius)
    }

    /**
     * The radius of the ring when using the emitter `EMISSION_SHAPE_RING`.
     *
     * Generated from Godot docs: CPUParticles3D.get_emission_ring_radius
     */
    fun getEmissionRingRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionRingRadiusBind, handle)
    }

    /**
     * The inner radius of the ring when using the emitter `EMISSION_SHAPE_RING`.
     *
     * Generated from Godot docs: CPUParticles3D.set_emission_ring_inner_radius
     */
    fun setEmissionRingInnerRadius(innerRadius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionRingInnerRadiusBind, handle, innerRadius)
    }

    /**
     * The inner radius of the ring when using the emitter `EMISSION_SHAPE_RING`.
     *
     * Generated from Godot docs: CPUParticles3D.get_emission_ring_inner_radius
     */
    fun getEmissionRingInnerRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionRingInnerRadiusBind, handle)
    }

    /**
     * The angle of the cone when using the emitter `EMISSION_SHAPE_RING`. The default angle of 90
     * degrees results in a ring, while an angle of 0 degrees results in a cone. Intermediate values
     * will result in a ring where one end is larger than the other. Note: Depending on
     * `emission_ring_height`, the angle may be clamped if the ring's end is reached to form a perfect
     * cone.
     *
     * Generated from Godot docs: CPUParticles3D.set_emission_ring_cone_angle
     */
    fun setEmissionRingConeAngle(coneAngle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEmissionRingConeAngleBind, handle, coneAngle)
    }

    /**
     * The angle of the cone when using the emitter `EMISSION_SHAPE_RING`. The default angle of 90
     * degrees results in a ring, while an angle of 0 degrees results in a cone. Intermediate values
     * will result in a ring where one end is larger than the other. Note: Depending on
     * `emission_ring_height`, the angle may be clamped if the ring's end is reached to form a perfect
     * cone.
     *
     * Generated from Godot docs: CPUParticles3D.get_emission_ring_cone_angle
     */
    fun getEmissionRingConeAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEmissionRingConeAngleBind, handle)
    }

    /**
     * Gravity applied to every particle.
     *
     * Generated from Godot docs: CPUParticles3D.get_gravity
     */
    fun getGravity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGravityBind, handle)
    }

    /**
     * Gravity applied to every particle.
     *
     * Generated from Godot docs: CPUParticles3D.set_gravity
     */
    fun setGravity(accelVec: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setGravityBind, handle, accelVec)
    }

    /**
     * If set to `true`, three different scale curves can be specified, one per scale axis.
     *
     * Generated from Godot docs: CPUParticles3D.get_split_scale
     */
    fun getSplitScale(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSplitScaleBind, handle)
    }

    /**
     * If set to `true`, three different scale curves can be specified, one per scale axis.
     *
     * Generated from Godot docs: CPUParticles3D.set_split_scale
     */
    fun setSplitScale(splitScale: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSplitScaleBind, handle, splitScale)
    }

    /**
     * Curve for the scale over life, along the x axis.
     *
     * Generated from Godot docs: CPUParticles3D.get_scale_curve_x
     */
    fun getScaleCurveX(): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getScaleCurveXBind, handle))
    }

    /**
     * Curve for the scale over life, along the x axis.
     *
     * Generated from Godot docs: CPUParticles3D.set_scale_curve_x
     */
    fun setScaleCurveX(scaleCurve: Curve?) {
        ObjectCalls.ptrcallWithObjectArgs(setScaleCurveXBind, handle, listOf(scaleCurve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Curve for the scale over life, along the y axis.
     *
     * Generated from Godot docs: CPUParticles3D.get_scale_curve_y
     */
    fun getScaleCurveY(): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getScaleCurveYBind, handle))
    }

    /**
     * Curve for the scale over life, along the y axis.
     *
     * Generated from Godot docs: CPUParticles3D.set_scale_curve_y
     */
    fun setScaleCurveY(scaleCurve: Curve?) {
        ObjectCalls.ptrcallWithObjectArgs(setScaleCurveYBind, handle, listOf(scaleCurve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Curve for the scale over life, along the z axis.
     *
     * Generated from Godot docs: CPUParticles3D.get_scale_curve_z
     */
    fun getScaleCurveZ(): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getScaleCurveZBind, handle))
    }

    /**
     * Curve for the scale over life, along the z axis.
     *
     * Generated from Godot docs: CPUParticles3D.set_scale_curve_z
     */
    fun setScaleCurveZ(scaleCurve: Curve?) {
        ObjectCalls.ptrcallWithObjectArgs(setScaleCurveZBind, handle, listOf(scaleCurve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Sets this node's properties to match a given `GPUParticles3D` node with an assigned
     * `ParticleProcessMaterial`.
     *
     * Generated from Godot docs: CPUParticles3D.convert_from_particles
     */
    fun convertFromParticles(particles: Node) {
        ObjectCalls.ptrcallWithObjectArgs(convertFromParticlesBind, handle, listOf(particles.handle))
    }

    object Signals {
        const val finished: String = "finished"
    }

    companion object {
        const val DRAW_ORDER_INDEX: Long = 0L
        const val DRAW_ORDER_LIFETIME: Long = 1L
        const val DRAW_ORDER_VIEW_DEPTH: Long = 2L
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
        const val EMISSION_SHAPE_BOX: Long = 3L
        const val EMISSION_SHAPE_POINTS: Long = 4L
        const val EMISSION_SHAPE_DIRECTED_POINTS: Long = 5L
        const val EMISSION_SHAPE_RING: Long = 6L
        const val EMISSION_SHAPE_MAX: Long = 7L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): CPUParticles3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CPUParticles3D? =
            if (handle.address() == 0L) null else CPUParticles3D(handle)

        private const val SET_EMITTING_HASH = 2586408642L
        private val setEmittingBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_emitting", SET_EMITTING_HASH)
        }

        private const val SET_AMOUNT_HASH = 1286410249L
        private val setAmountBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_amount", SET_AMOUNT_HASH)
        }

        private const val SET_LIFETIME_HASH = 373806689L
        private val setLifetimeBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_lifetime", SET_LIFETIME_HASH)
        }

        private const val SET_ONE_SHOT_HASH = 2586408642L
        private val setOneShotBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_one_shot", SET_ONE_SHOT_HASH)
        }

        private const val SET_PRE_PROCESS_TIME_HASH = 373806689L
        private val setPreProcessTimeBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_pre_process_time", SET_PRE_PROCESS_TIME_HASH)
        }

        private const val SET_EXPLOSIVENESS_RATIO_HASH = 373806689L
        private val setExplosivenessRatioBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_explosiveness_ratio", SET_EXPLOSIVENESS_RATIO_HASH)
        }

        private const val SET_RANDOMNESS_RATIO_HASH = 373806689L
        private val setRandomnessRatioBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_randomness_ratio", SET_RANDOMNESS_RATIO_HASH)
        }

        private const val SET_VISIBILITY_AABB_HASH = 259215842L
        private val setVisibilityAabbBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_visibility_aabb", SET_VISIBILITY_AABB_HASH)
        }

        private const val SET_LIFETIME_RANDOMNESS_HASH = 373806689L
        private val setLifetimeRandomnessBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_lifetime_randomness", SET_LIFETIME_RANDOMNESS_HASH)
        }

        private const val SET_USE_LOCAL_COORDINATES_HASH = 2586408642L
        private val setUseLocalCoordinatesBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_use_local_coordinates", SET_USE_LOCAL_COORDINATES_HASH)
        }

        private const val SET_FIXED_FPS_HASH = 1286410249L
        private val setFixedFpsBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_fixed_fps", SET_FIXED_FPS_HASH)
        }

        private const val SET_FRACTIONAL_DELTA_HASH = 2586408642L
        private val setFractionalDeltaBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_fractional_delta", SET_FRACTIONAL_DELTA_HASH)
        }

        private const val SET_SPEED_SCALE_HASH = 373806689L
        private val setSpeedScaleBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_speed_scale", SET_SPEED_SCALE_HASH)
        }

        private const val IS_EMITTING_HASH = 36873697L
        private val isEmittingBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "is_emitting", IS_EMITTING_HASH)
        }

        private const val GET_AMOUNT_HASH = 3905245786L
        private val getAmountBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_amount", GET_AMOUNT_HASH)
        }

        private const val GET_LIFETIME_HASH = 1740695150L
        private val getLifetimeBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_lifetime", GET_LIFETIME_HASH)
        }

        private const val GET_ONE_SHOT_HASH = 36873697L
        private val getOneShotBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_one_shot", GET_ONE_SHOT_HASH)
        }

        private const val GET_PRE_PROCESS_TIME_HASH = 1740695150L
        private val getPreProcessTimeBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_pre_process_time", GET_PRE_PROCESS_TIME_HASH)
        }

        private const val GET_EXPLOSIVENESS_RATIO_HASH = 1740695150L
        private val getExplosivenessRatioBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_explosiveness_ratio", GET_EXPLOSIVENESS_RATIO_HASH)
        }

        private const val GET_RANDOMNESS_RATIO_HASH = 1740695150L
        private val getRandomnessRatioBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_randomness_ratio", GET_RANDOMNESS_RATIO_HASH)
        }

        private const val GET_VISIBILITY_AABB_HASH = 1068685055L
        private val getVisibilityAabbBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_visibility_aabb", GET_VISIBILITY_AABB_HASH)
        }

        private const val GET_LIFETIME_RANDOMNESS_HASH = 1740695150L
        private val getLifetimeRandomnessBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_lifetime_randomness", GET_LIFETIME_RANDOMNESS_HASH)
        }

        private const val GET_USE_LOCAL_COORDINATES_HASH = 36873697L
        private val getUseLocalCoordinatesBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_use_local_coordinates", GET_USE_LOCAL_COORDINATES_HASH)
        }

        private const val GET_FIXED_FPS_HASH = 3905245786L
        private val getFixedFpsBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_fixed_fps", GET_FIXED_FPS_HASH)
        }

        private const val GET_FRACTIONAL_DELTA_HASH = 36873697L
        private val getFractionalDeltaBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_fractional_delta", GET_FRACTIONAL_DELTA_HASH)
        }

        private const val GET_SPEED_SCALE_HASH = 1740695150L
        private val getSpeedScaleBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_speed_scale", GET_SPEED_SCALE_HASH)
        }

        private const val SET_DRAW_ORDER_HASH = 1427401774L
        private val setDrawOrderBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_draw_order", SET_DRAW_ORDER_HASH)
        }

        private const val GET_DRAW_ORDER_HASH = 1321900776L
        private val getDrawOrderBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_draw_order", GET_DRAW_ORDER_HASH)
        }

        private const val SET_MESH_HASH = 194775623L
        private val setMeshBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_mesh", SET_MESH_HASH)
        }

        private const val GET_MESH_HASH = 1808005922L
        private val getMeshBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_mesh", GET_MESH_HASH)
        }

        private const val SET_USE_FIXED_SEED_HASH = 2586408642L
        private val setUseFixedSeedBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_use_fixed_seed", SET_USE_FIXED_SEED_HASH)
        }

        private const val GET_USE_FIXED_SEED_HASH = 36873697L
        private val getUseFixedSeedBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_use_fixed_seed", GET_USE_FIXED_SEED_HASH)
        }

        private const val SET_SEED_HASH = 1286410249L
        private val setSeedBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_seed", SET_SEED_HASH)
        }

        private const val GET_SEED_HASH = 3905245786L
        private val getSeedBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_seed", GET_SEED_HASH)
        }

        private const val RESTART_HASH = 107499316L
        private val restartBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "restart", RESTART_HASH)
        }

        private const val REQUEST_PARTICLES_PROCESS_HASH = 66938510L
        private val requestParticlesProcessBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "request_particles_process", REQUEST_PARTICLES_PROCESS_HASH)
        }

        private const val CAPTURE_AABB_HASH = 1068685055L
        private val captureAabbBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "capture_aabb", CAPTURE_AABB_HASH)
        }

        private const val SET_DIRECTION_HASH = 3460891852L
        private val setDirectionBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_direction", SET_DIRECTION_HASH)
        }

        private const val GET_DIRECTION_HASH = 3360562783L
        private val getDirectionBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_direction", GET_DIRECTION_HASH)
        }

        private const val SET_SPREAD_HASH = 373806689L
        private val setSpreadBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_spread", SET_SPREAD_HASH)
        }

        private const val GET_SPREAD_HASH = 1740695150L
        private val getSpreadBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_spread", GET_SPREAD_HASH)
        }

        private const val SET_FLATNESS_HASH = 373806689L
        private val setFlatnessBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_flatness", SET_FLATNESS_HASH)
        }

        private const val GET_FLATNESS_HASH = 1740695150L
        private val getFlatnessBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_flatness", GET_FLATNESS_HASH)
        }

        private const val SET_PARAM_MIN_HASH = 557936109L
        private val setParamMinBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_param_min", SET_PARAM_MIN_HASH)
        }

        private const val GET_PARAM_MIN_HASH = 597646162L
        private val getParamMinBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_param_min", GET_PARAM_MIN_HASH)
        }

        private const val SET_PARAM_MAX_HASH = 557936109L
        private val setParamMaxBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_param_max", SET_PARAM_MAX_HASH)
        }

        private const val GET_PARAM_MAX_HASH = 597646162L
        private val getParamMaxBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_param_max", GET_PARAM_MAX_HASH)
        }

        private const val SET_PARAM_CURVE_HASH = 4044142537L
        private val setParamCurveBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_param_curve", SET_PARAM_CURVE_HASH)
        }

        private const val GET_PARAM_CURVE_HASH = 4132790277L
        private val getParamCurveBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_param_curve", GET_PARAM_CURVE_HASH)
        }

        private const val SET_COLOR_HASH = 2920490490L
        private val setColorBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_color", SET_COLOR_HASH)
        }

        private const val GET_COLOR_HASH = 3444240500L
        private val getColorBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_color", GET_COLOR_HASH)
        }

        private const val SET_COLOR_RAMP_HASH = 2756054477L
        private val setColorRampBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_color_ramp", SET_COLOR_RAMP_HASH)
        }

        private const val GET_COLOR_RAMP_HASH = 132272999L
        private val getColorRampBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_color_ramp", GET_COLOR_RAMP_HASH)
        }

        private const val SET_COLOR_INITIAL_RAMP_HASH = 2756054477L
        private val setColorInitialRampBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_color_initial_ramp", SET_COLOR_INITIAL_RAMP_HASH)
        }

        private const val GET_COLOR_INITIAL_RAMP_HASH = 132272999L
        private val getColorInitialRampBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_color_initial_ramp", GET_COLOR_INITIAL_RAMP_HASH)
        }

        private const val SET_PARTICLE_FLAG_HASH = 3515406498L
        private val setParticleFlagBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_particle_flag", SET_PARTICLE_FLAG_HASH)
        }

        private const val GET_PARTICLE_FLAG_HASH = 2845201987L
        private val getParticleFlagBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_particle_flag", GET_PARTICLE_FLAG_HASH)
        }

        private const val SET_EMISSION_SHAPE_HASH = 491823814L
        private val setEmissionShapeBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_emission_shape", SET_EMISSION_SHAPE_HASH)
        }

        private const val GET_EMISSION_SHAPE_HASH = 2961454842L
        private val getEmissionShapeBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_emission_shape", GET_EMISSION_SHAPE_HASH)
        }

        private const val SET_EMISSION_SPHERE_RADIUS_HASH = 373806689L
        private val setEmissionSphereRadiusBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_emission_sphere_radius", SET_EMISSION_SPHERE_RADIUS_HASH)
        }

        private const val GET_EMISSION_SPHERE_RADIUS_HASH = 1740695150L
        private val getEmissionSphereRadiusBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_emission_sphere_radius", GET_EMISSION_SPHERE_RADIUS_HASH)
        }

        private const val SET_EMISSION_BOX_EXTENTS_HASH = 3460891852L
        private val setEmissionBoxExtentsBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_emission_box_extents", SET_EMISSION_BOX_EXTENTS_HASH)
        }

        private const val GET_EMISSION_BOX_EXTENTS_HASH = 3360562783L
        private val getEmissionBoxExtentsBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_emission_box_extents", GET_EMISSION_BOX_EXTENTS_HASH)
        }

        private const val SET_EMISSION_POINTS_HASH = 334873810L
        private val setEmissionPointsBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_emission_points", SET_EMISSION_POINTS_HASH)
        }

        private const val GET_EMISSION_POINTS_HASH = 497664490L
        private val getEmissionPointsBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_emission_points", GET_EMISSION_POINTS_HASH)
        }

        private const val SET_EMISSION_NORMALS_HASH = 334873810L
        private val setEmissionNormalsBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_emission_normals", SET_EMISSION_NORMALS_HASH)
        }

        private const val GET_EMISSION_NORMALS_HASH = 497664490L
        private val getEmissionNormalsBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_emission_normals", GET_EMISSION_NORMALS_HASH)
        }

        private const val SET_EMISSION_COLORS_HASH = 3546319833L
        private val setEmissionColorsBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_emission_colors", SET_EMISSION_COLORS_HASH)
        }

        private const val GET_EMISSION_COLORS_HASH = 1392750486L
        private val getEmissionColorsBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_emission_colors", GET_EMISSION_COLORS_HASH)
        }

        private const val SET_EMISSION_RING_AXIS_HASH = 3460891852L
        private val setEmissionRingAxisBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_emission_ring_axis", SET_EMISSION_RING_AXIS_HASH)
        }

        private const val GET_EMISSION_RING_AXIS_HASH = 3360562783L
        private val getEmissionRingAxisBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_emission_ring_axis", GET_EMISSION_RING_AXIS_HASH)
        }

        private const val SET_EMISSION_RING_HEIGHT_HASH = 373806689L
        private val setEmissionRingHeightBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_emission_ring_height", SET_EMISSION_RING_HEIGHT_HASH)
        }

        private const val GET_EMISSION_RING_HEIGHT_HASH = 1740695150L
        private val getEmissionRingHeightBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_emission_ring_height", GET_EMISSION_RING_HEIGHT_HASH)
        }

        private const val SET_EMISSION_RING_RADIUS_HASH = 373806689L
        private val setEmissionRingRadiusBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_emission_ring_radius", SET_EMISSION_RING_RADIUS_HASH)
        }

        private const val GET_EMISSION_RING_RADIUS_HASH = 1740695150L
        private val getEmissionRingRadiusBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_emission_ring_radius", GET_EMISSION_RING_RADIUS_HASH)
        }

        private const val SET_EMISSION_RING_INNER_RADIUS_HASH = 373806689L
        private val setEmissionRingInnerRadiusBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_emission_ring_inner_radius", SET_EMISSION_RING_INNER_RADIUS_HASH)
        }

        private const val GET_EMISSION_RING_INNER_RADIUS_HASH = 1740695150L
        private val getEmissionRingInnerRadiusBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_emission_ring_inner_radius", GET_EMISSION_RING_INNER_RADIUS_HASH)
        }

        private const val SET_EMISSION_RING_CONE_ANGLE_HASH = 373806689L
        private val setEmissionRingConeAngleBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_emission_ring_cone_angle", SET_EMISSION_RING_CONE_ANGLE_HASH)
        }

        private const val GET_EMISSION_RING_CONE_ANGLE_HASH = 1740695150L
        private val getEmissionRingConeAngleBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_emission_ring_cone_angle", GET_EMISSION_RING_CONE_ANGLE_HASH)
        }

        private const val GET_GRAVITY_HASH = 3360562783L
        private val getGravityBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_gravity", GET_GRAVITY_HASH)
        }

        private const val SET_GRAVITY_HASH = 3460891852L
        private val setGravityBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_gravity", SET_GRAVITY_HASH)
        }

        private const val GET_SPLIT_SCALE_HASH = 2240911060L
        private val getSplitScaleBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_split_scale", GET_SPLIT_SCALE_HASH)
        }

        private const val SET_SPLIT_SCALE_HASH = 2586408642L
        private val setSplitScaleBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_split_scale", SET_SPLIT_SCALE_HASH)
        }

        private const val GET_SCALE_CURVE_X_HASH = 2460114913L
        private val getScaleCurveXBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_scale_curve_x", GET_SCALE_CURVE_X_HASH)
        }

        private const val SET_SCALE_CURVE_X_HASH = 270443179L
        private val setScaleCurveXBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_scale_curve_x", SET_SCALE_CURVE_X_HASH)
        }

        private const val GET_SCALE_CURVE_Y_HASH = 2460114913L
        private val getScaleCurveYBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_scale_curve_y", GET_SCALE_CURVE_Y_HASH)
        }

        private const val SET_SCALE_CURVE_Y_HASH = 270443179L
        private val setScaleCurveYBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_scale_curve_y", SET_SCALE_CURVE_Y_HASH)
        }

        private const val GET_SCALE_CURVE_Z_HASH = 2460114913L
        private val getScaleCurveZBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "get_scale_curve_z", GET_SCALE_CURVE_Z_HASH)
        }

        private const val SET_SCALE_CURVE_Z_HASH = 270443179L
        private val setScaleCurveZBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "set_scale_curve_z", SET_SCALE_CURVE_Z_HASH)
        }

        private const val CONVERT_FROM_PARTICLES_HASH = 1078189570L
        private val convertFromParticlesBind by lazy {
            ObjectCalls.getMethodBind("CPUParticles3D", "convert_from_particles", CONVERT_FROM_PARTICLES_HASH)
        }
    }
}
