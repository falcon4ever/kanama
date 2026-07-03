package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * A 3D particle emitter.
 *
 * Generated from Godot docs: GPUParticles3D
 */
class GPUParticles3D(handle: MemorySegment) : GeometryInstance3D(handle) {
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

    var amountRatio: Double
        @JvmName("amountRatioProperty")
        get() = getAmountRatio()
        @JvmName("setAmountRatioProperty")
        set(value) = setAmountRatio(value)

    var subEmitter: NodePath
        @JvmName("subEmitterProperty")
        get() = getSubEmitter()
        @JvmName("setSubEmitterProperty")
        set(value) = setSubEmitter(value)

    var lifetime: Double
        @JvmName("lifetimeProperty")
        get() = getLifetime()
        @JvmName("setLifetimeProperty")
        set(value) = setLifetime(value)

    var interpToEnd: Double
        @JvmName("interpToEndProperty")
        get() = getInterpToEnd()
        @JvmName("setInterpToEndProperty")
        set(value) = setInterpToEnd(value)

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

    var fixedFps: Int
        @JvmName("fixedFpsProperty")
        get() = getFixedFps()
        @JvmName("setFixedFpsProperty")
        set(value) = setFixedFps(value)

    var interpolate: Boolean
        @JvmName("interpolateProperty")
        get() = getInterpolate()
        @JvmName("setInterpolateProperty")
        set(value) = setInterpolate(value)

    var fractDelta: Boolean
        @JvmName("fractDeltaProperty")
        get() = getFractionalDelta()
        @JvmName("setFractDeltaProperty")
        set(value) = setFractionalDelta(value)

    var collisionBaseSize: Double
        @JvmName("collisionBaseSizeProperty")
        get() = getCollisionBaseSize()
        @JvmName("setCollisionBaseSizeProperty")
        set(value) = setCollisionBaseSize(value)

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

    var transformAlign: Long
        @JvmName("transformAlignProperty")
        get() = getTransformAlign()
        @JvmName("setTransformAlignProperty")
        set(value) = setTransformAlign(value)

    var transformAlignAxis: Long
        @JvmName("transformAlignAxisProperty")
        get() = getTransformAlignAxis()
        @JvmName("setTransformAlignAxisProperty")
        set(value) = setTransformAlignAxis(value)

    var transformAlignChannelFilter: Long
        @JvmName("transformAlignChannelFilterProperty")
        get() = getTransformAlignChannelFilter()
        @JvmName("setTransformAlignChannelFilterProperty")
        set(value) = setTransformAlignChannelFilter(value)

    var trailEnabled: Boolean
        @JvmName("trailEnabledProperty")
        get() = isTrailEnabled()
        @JvmName("setTrailEnabledProperty")
        set(value) = setTrailEnabled(value)

    var trailLifetime: Double
        @JvmName("trailLifetimeProperty")
        get() = getTrailLifetime()
        @JvmName("setTrailLifetimeProperty")
        set(value) = setTrailLifetime(value)

    var processMaterial: Material?
        @JvmName("processMaterialProperty")
        get() = getProcessMaterial()
        @JvmName("setProcessMaterialProperty")
        set(value) = setProcessMaterial(value)

    var drawPasses: Int
        @JvmName("drawPassesProperty")
        get() = getDrawPasses()
        @JvmName("setDrawPassesProperty")
        set(value) = setDrawPasses(value)

    var drawSkin: Skin?
        @JvmName("drawSkinProperty")
        get() = getSkin()
        @JvmName("setDrawSkinProperty")
        set(value) = setSkin(value)

    /**
     * If `true`, particles are being emitted. `emitting` can be used to start and stop particles from
     * emitting. However, if `one_shot` is `true` setting `emitting` to `true` will not restart the
     * emission cycle unless all active particles have finished processing. Use the `finished` signal
     * to be notified once all active particles finish processing. Note: For `one_shot` emitters, due
     * to the particles being computed on the GPU, there may be a short period after receiving the
     * `finished` signal during which setting this to `true` will not restart the emission cycle. Tip:
     * If your `one_shot` emitter needs to immediately restart emitting particles once `finished`
     * signal is received, consider calling `restart` instead of setting `emitting`.
     *
     * Generated from Godot docs: GPUParticles3D.set_emitting
     */
    fun setEmitting(emitting: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEmittingBind, handle, emitting)
    }

    /**
     * The number of particles to emit in one emission cycle. The effective emission rate is `(amount *
     * amount_ratio) / lifetime` particles per second. Higher values will increase GPU requirements,
     * even if not all particles are visible at a given time or if `amount_ratio` is decreased. Note:
     * Changing this value will cause the particle system to restart. To avoid this, change
     * `amount_ratio` instead.
     *
     * Generated from Godot docs: GPUParticles3D.set_amount
     */
    fun setAmount(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setAmountBind, handle, amount)
    }

    /**
     * The amount of time each particle will exist (in seconds). The effective emission rate is
     * `(amount * amount_ratio) / lifetime` particles per second.
     *
     * Generated from Godot docs: GPUParticles3D.set_lifetime
     */
    fun setLifetime(secs: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLifetimeBind, handle, secs)
    }

    /**
     * If `true`, only the number of particles equal to `amount` will be emitted.
     *
     * Generated from Godot docs: GPUParticles3D.set_one_shot
     */
    fun setOneShot(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setOneShotBind, handle, enable)
    }

    /**
     * Amount of time to preprocess the particles before animation starts. Lets you start the animation
     * some time after particles have started emitting. Note: This can be very expensive if set to a
     * high number as it requires running the particle shader a number of times equal to the
     * `fixed_fps` (or 30, if `fixed_fps` is 0) for every second. In extreme cases it can even lead to
     * a GPU crash due to the volume of work done in a single frame.
     *
     * Generated from Godot docs: GPUParticles3D.set_pre_process_time
     */
    fun setPreProcessTime(secs: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPreProcessTimeBind, handle, secs)
    }

    /**
     * Time ratio between each emission. If `0`, particles are emitted continuously. If `1`, all
     * particles are emitted simultaneously.
     *
     * Generated from Godot docs: GPUParticles3D.set_explosiveness_ratio
     */
    fun setExplosivenessRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setExplosivenessRatioBind, handle, ratio)
    }

    /**
     * Emission randomness ratio.
     *
     * Generated from Godot docs: GPUParticles3D.set_randomness_ratio
     */
    fun setRandomnessRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRandomnessRatioBind, handle, ratio)
    }

    /**
     * The `AABB` that determines the node's region which needs to be visible on screen for the
     * particle system to be active. `GeometryInstance3D.extra_cull_margin` is added on each of the
     * AABB's axes. Particle collisions and attraction will only occur within this area. Grow the box
     * if particles suddenly appear/disappear when the node enters/exits the screen. The `AABB` can be
     * grown via code or with the Particles → Generate AABB editor tool. Note: `visibility_aabb` is
     * overridden by `GeometryInstance3D.custom_aabb` if that property is set to a non-default value.
     *
     * Generated from Godot docs: GPUParticles3D.set_visibility_aabb
     */
    fun setVisibilityAabb(aabb: AABB) {
        ObjectCalls.ptrcallWithAABBArg(setVisibilityAabbBind, handle, aabb)
    }

    /**
     * If `true`, particles use the parent node's coordinate space (known as local coordinates). This
     * will cause particles to move and rotate along the `GPUParticles3D` node (and its parents) when
     * it is moved or rotated. If `false`, particles use global coordinates; they will not move or
     * rotate along the `GPUParticles3D` node (and its parents) when it is moved or rotated.
     *
     * Generated from Godot docs: GPUParticles3D.set_use_local_coordinates
     */
    fun setUseLocalCoordinates(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseLocalCoordinatesBind, handle, enable)
    }

    /**
     * The particle system's frame rate is fixed to a value. For example, changing the value to 2 will
     * make the particles render at 2 frames per second. Note this does not slow down the simulation of
     * the particle system itself.
     *
     * Generated from Godot docs: GPUParticles3D.set_fixed_fps
     */
    fun setFixedFps(fps: Int) {
        ObjectCalls.ptrcallWithIntArg(setFixedFpsBind, handle, fps)
    }

    /**
     * If `true`, results in fractional delta calculation which has a smoother particles display
     * effect.
     *
     * Generated from Godot docs: GPUParticles3D.set_fractional_delta
     */
    fun setFractionalDelta(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFractionalDeltaBind, handle, enable)
    }

    /**
     * Enables particle interpolation, which makes the particle movement smoother when their
     * `fixed_fps` is lower than the screen refresh rate.
     *
     * Generated from Godot docs: GPUParticles3D.set_interpolate
     */
    fun setInterpolate(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setInterpolateBind, handle, enable)
    }

    /**
     * `Material` for processing particles. Can be a `ParticleProcessMaterial` or a `ShaderMaterial`.
     *
     * Generated from Godot docs: GPUParticles3D.set_process_material
     */
    fun setProcessMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(setProcessMaterialBind, handle, listOf(material?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Speed scaling ratio. A value of `0` can be used to pause the particles.
     *
     * Generated from Godot docs: GPUParticles3D.set_speed_scale
     */
    fun setSpeedScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSpeedScaleBind, handle, scale)
    }

    /**
     * The base diameter for particle collision in meters. If particles appear to sink into the ground
     * when colliding, increase this value. If particles appear to float when colliding, decrease this
     * value. Only effective if `ParticleProcessMaterial.collision_mode` is
     * `ParticleProcessMaterial.COLLISION_RIGID` or
     * `ParticleProcessMaterial.COLLISION_HIDE_ON_CONTACT`. Note: Particles always have a spherical
     * collision shape.
     *
     * Generated from Godot docs: GPUParticles3D.set_collision_base_size
     */
    fun setCollisionBaseSize(size: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCollisionBaseSizeBind, handle, size)
    }

    /**
     * Causes all the particles in this node to interpolate towards the end of their lifetime. Note:
     * This only works when used with a `ParticleProcessMaterial`. It needs to be manually implemented
     * for custom process shaders.
     *
     * Generated from Godot docs: GPUParticles3D.set_interp_to_end
     */
    fun setInterpToEnd(interp: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setInterpToEndBind, handle, interp)
    }

    /**
     * If `true`, particles are being emitted. `emitting` can be used to start and stop particles from
     * emitting. However, if `one_shot` is `true` setting `emitting` to `true` will not restart the
     * emission cycle unless all active particles have finished processing. Use the `finished` signal
     * to be notified once all active particles finish processing. Note: For `one_shot` emitters, due
     * to the particles being computed on the GPU, there may be a short period after receiving the
     * `finished` signal during which setting this to `true` will not restart the emission cycle. Tip:
     * If your `one_shot` emitter needs to immediately restart emitting particles once `finished`
     * signal is received, consider calling `restart` instead of setting `emitting`.
     *
     * Generated from Godot docs: GPUParticles3D.is_emitting
     */
    fun isEmitting(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEmittingBind, handle)
    }

    /**
     * The number of particles to emit in one emission cycle. The effective emission rate is `(amount *
     * amount_ratio) / lifetime` particles per second. Higher values will increase GPU requirements,
     * even if not all particles are visible at a given time or if `amount_ratio` is decreased. Note:
     * Changing this value will cause the particle system to restart. To avoid this, change
     * `amount_ratio` instead.
     *
     * Generated from Godot docs: GPUParticles3D.get_amount
     */
    fun getAmount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getAmountBind, handle)
    }

    /**
     * The amount of time each particle will exist (in seconds). The effective emission rate is
     * `(amount * amount_ratio) / lifetime` particles per second.
     *
     * Generated from Godot docs: GPUParticles3D.get_lifetime
     */
    fun getLifetime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLifetimeBind, handle)
    }

    /**
     * If `true`, only the number of particles equal to `amount` will be emitted.
     *
     * Generated from Godot docs: GPUParticles3D.get_one_shot
     */
    fun getOneShot(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getOneShotBind, handle)
    }

    /**
     * Amount of time to preprocess the particles before animation starts. Lets you start the animation
     * some time after particles have started emitting. Note: This can be very expensive if set to a
     * high number as it requires running the particle shader a number of times equal to the
     * `fixed_fps` (or 30, if `fixed_fps` is 0) for every second. In extreme cases it can even lead to
     * a GPU crash due to the volume of work done in a single frame.
     *
     * Generated from Godot docs: GPUParticles3D.get_pre_process_time
     */
    fun getPreProcessTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPreProcessTimeBind, handle)
    }

    /**
     * Time ratio between each emission. If `0`, particles are emitted continuously. If `1`, all
     * particles are emitted simultaneously.
     *
     * Generated from Godot docs: GPUParticles3D.get_explosiveness_ratio
     */
    fun getExplosivenessRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getExplosivenessRatioBind, handle)
    }

    /**
     * Emission randomness ratio.
     *
     * Generated from Godot docs: GPUParticles3D.get_randomness_ratio
     */
    fun getRandomnessRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRandomnessRatioBind, handle)
    }

    /**
     * The `AABB` that determines the node's region which needs to be visible on screen for the
     * particle system to be active. `GeometryInstance3D.extra_cull_margin` is added on each of the
     * AABB's axes. Particle collisions and attraction will only occur within this area. Grow the box
     * if particles suddenly appear/disappear when the node enters/exits the screen. The `AABB` can be
     * grown via code or with the Particles → Generate AABB editor tool. Note: `visibility_aabb` is
     * overridden by `GeometryInstance3D.custom_aabb` if that property is set to a non-default value.
     *
     * Generated from Godot docs: GPUParticles3D.get_visibility_aabb
     */
    fun getVisibilityAabb(): AABB {
        return ObjectCalls.ptrcallNoArgsRetAABB(getVisibilityAabbBind, handle)
    }

    /**
     * If `true`, particles use the parent node's coordinate space (known as local coordinates). This
     * will cause particles to move and rotate along the `GPUParticles3D` node (and its parents) when
     * it is moved or rotated. If `false`, particles use global coordinates; they will not move or
     * rotate along the `GPUParticles3D` node (and its parents) when it is moved or rotated.
     *
     * Generated from Godot docs: GPUParticles3D.get_use_local_coordinates
     */
    fun getUseLocalCoordinates(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseLocalCoordinatesBind, handle)
    }

    /**
     * The particle system's frame rate is fixed to a value. For example, changing the value to 2 will
     * make the particles render at 2 frames per second. Note this does not slow down the simulation of
     * the particle system itself.
     *
     * Generated from Godot docs: GPUParticles3D.get_fixed_fps
     */
    fun getFixedFps(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFixedFpsBind, handle)
    }

    /**
     * If `true`, results in fractional delta calculation which has a smoother particles display
     * effect.
     *
     * Generated from Godot docs: GPUParticles3D.get_fractional_delta
     */
    fun getFractionalDelta(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getFractionalDeltaBind, handle)
    }

    /**
     * Enables particle interpolation, which makes the particle movement smoother when their
     * `fixed_fps` is lower than the screen refresh rate.
     *
     * Generated from Godot docs: GPUParticles3D.get_interpolate
     */
    fun getInterpolate(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getInterpolateBind, handle)
    }

    /**
     * `Material` for processing particles. Can be a `ParticleProcessMaterial` or a `ShaderMaterial`.
     *
     * Generated from Godot docs: GPUParticles3D.get_process_material
     */
    fun getProcessMaterial(): Material? {
        return Material.wrap(ObjectCalls.ptrcallNoArgsRetObject(getProcessMaterialBind, handle))
    }

    /**
     * Speed scaling ratio. A value of `0` can be used to pause the particles.
     *
     * Generated from Godot docs: GPUParticles3D.get_speed_scale
     */
    fun getSpeedScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSpeedScaleBind, handle)
    }

    /**
     * The base diameter for particle collision in meters. If particles appear to sink into the ground
     * when colliding, increase this value. If particles appear to float when colliding, decrease this
     * value. Only effective if `ParticleProcessMaterial.collision_mode` is
     * `ParticleProcessMaterial.COLLISION_RIGID` or
     * `ParticleProcessMaterial.COLLISION_HIDE_ON_CONTACT`. Note: Particles always have a spherical
     * collision shape.
     *
     * Generated from Godot docs: GPUParticles3D.get_collision_base_size
     */
    fun getCollisionBaseSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCollisionBaseSizeBind, handle)
    }

    /**
     * Causes all the particles in this node to interpolate towards the end of their lifetime. Note:
     * This only works when used with a `ParticleProcessMaterial`. It needs to be manually implemented
     * for custom process shaders.
     *
     * Generated from Godot docs: GPUParticles3D.get_interp_to_end
     */
    fun getInterpToEnd(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getInterpToEndBind, handle)
    }

    /**
     * If `true`, particles will use the same seed for every simulation using the seed defined in
     * `seed`. This is useful for situations where the visual outcome should be consistent across
     * replays, for example when using Movie Maker mode.
     *
     * Generated from Godot docs: GPUParticles3D.set_use_fixed_seed
     */
    fun setUseFixedSeed(useFixedSeed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseFixedSeedBind, handle, useFixedSeed)
    }

    /**
     * If `true`, particles will use the same seed for every simulation using the seed defined in
     * `seed`. This is useful for situations where the visual outcome should be consistent across
     * replays, for example when using Movie Maker mode.
     *
     * Generated from Godot docs: GPUParticles3D.get_use_fixed_seed
     */
    fun getUseFixedSeed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseFixedSeedBind, handle)
    }

    /**
     * Sets the random seed used by the particle system. Only effective if `use_fixed_seed` is `true`.
     *
     * Generated from Godot docs: GPUParticles3D.set_seed
     */
    fun setSeed(seed: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setSeedBind, handle, seed)
    }

    /**
     * Sets the random seed used by the particle system. Only effective if `use_fixed_seed` is `true`.
     *
     * Generated from Godot docs: GPUParticles3D.get_seed
     */
    fun getSeed(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getSeedBind, handle)
    }

    /**
     * Particle draw order. Note: `DRAW_ORDER_INDEX` is the only option that supports motion vectors
     * for effects like TAA. It is suggested to use this draw order if the particles are opaque to fix
     * ghosting artifacts.
     *
     * Generated from Godot docs: GPUParticles3D.set_draw_order
     */
    fun setDrawOrder(order: Long) {
        ObjectCalls.ptrcallWithLongArg(setDrawOrderBind, handle, order)
    }

    /**
     * Particle draw order. Note: `DRAW_ORDER_INDEX` is the only option that supports motion vectors
     * for effects like TAA. It is suggested to use this draw order if the particles are opaque to fix
     * ghosting artifacts.
     *
     * Generated from Godot docs: GPUParticles3D.get_draw_order
     */
    fun getDrawOrder(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDrawOrderBind, handle)
    }

    /**
     * The number of draw passes when rendering particles.
     *
     * Generated from Godot docs: GPUParticles3D.set_draw_passes
     */
    fun setDrawPasses(passes: Int) {
        ObjectCalls.ptrcallWithIntArg(setDrawPassesBind, handle, passes)
    }

    /**
     * `Mesh` that is drawn for the fourth draw pass.
     *
     * Generated from Godot docs: GPUParticles3D.set_draw_pass_mesh
     */
    fun setDrawPassMesh(pass: Int, mesh: Mesh?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setDrawPassMeshBind, handle, pass, mesh?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * The number of draw passes when rendering particles.
     *
     * Generated from Godot docs: GPUParticles3D.get_draw_passes
     */
    fun getDrawPasses(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDrawPassesBind, handle)
    }

    /**
     * `Mesh` that is drawn for the fourth draw pass.
     *
     * Generated from Godot docs: GPUParticles3D.get_draw_pass_mesh
     */
    fun getDrawPassMesh(pass: Int): Mesh? {
        return Mesh.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getDrawPassMeshBind, handle, pass))
    }

    fun setSkin(skin: Skin?) {
        ObjectCalls.ptrcallWithObjectArgs(setSkinBind, handle, listOf(skin?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getSkin(): Skin? {
        return Skin.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSkinBind, handle))
    }

    /**
     * Restarts the particle emission cycle, clearing existing particles. To avoid particles vanishing
     * from the viewport, wait for the `finished` signal before calling. Note: The `finished` signal is
     * only emitted by `one_shot` emitters. If `keep_seed` is `true`, the current random seed will be
     * preserved. Useful for seeking and playback.
     *
     * Generated from Godot docs: GPUParticles3D.restart
     */
    fun restart(keepSeed: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(restartBind, handle, keepSeed)
    }

    /**
     * Returns the axis-aligned bounding box that contains all the particles that are active in the
     * current frame.
     *
     * Generated from Godot docs: GPUParticles3D.capture_aabb
     */
    fun captureAabb(): AABB {
        return ObjectCalls.ptrcallNoArgsRetAABB(captureAabbBind, handle)
    }

    /**
     * Path to another `GPUParticles3D` node that will be used as a subemitter (see
     * `ParticleProcessMaterial.sub_emitter_mode`). Subemitters can be used to achieve effects such as
     * fireworks, sparks on collision, bubbles popping into water drops, and more. Note: When
     * `sub_emitter` is set, the target `GPUParticles3D` node will no longer emit particles on its own.
     *
     * Generated from Godot docs: GPUParticles3D.set_sub_emitter
     */
    fun setSubEmitter(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setSubEmitterBind, handle, path)
    }

    /**
     * Path to another `GPUParticles3D` node that will be used as a subemitter (see
     * `ParticleProcessMaterial.sub_emitter_mode`). Subemitters can be used to achieve effects such as
     * fireworks, sparks on collision, bubbles popping into water drops, and more. Note: When
     * `sub_emitter` is set, the target `GPUParticles3D` node will no longer emit particles on its own.
     *
     * Generated from Godot docs: GPUParticles3D.get_sub_emitter
     */
    fun getSubEmitter(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getSubEmitterBind, handle)
    }

    /**
     * Emits a single particle. Whether `xform`, `velocity`, `color` and `custom` are applied depends
     * on the value of `flags`. See `EmitFlags`. The default ParticleProcessMaterial will overwrite
     * `color` and use the contents of `custom` as `(rotation, age, animation, lifetime)`. Note:
     * `emit_particle` is only supported on the Forward+ and Mobile rendering methods, not
     * Compatibility.
     *
     * Generated from Godot docs: GPUParticles3D.emit_particle
     */
    fun emitParticle(xform: Transform3D, velocity: Vector3, color: Color, custom: Color, flags: Long) {
        ObjectCalls.ptrcallWithTransform3DVector3TwoColorUInt32Args(emitParticleBind, handle, xform, velocity, color, custom, flags)
    }

    /**
     * If `true`, enables particle trails using a mesh skinning system. Designed to work with
     * `RibbonTrailMesh` and `TubeTrailMesh`. Note: `BaseMaterial3D.use_particle_trails` must also be
     * enabled on the particle mesh's material. Otherwise, setting `trail_enabled` to `true` will have
     * no effect. Note: Unlike `GPUParticles2D`, the number of trail sections and subdivisions is set
     * in the `RibbonTrailMesh` or the `TubeTrailMesh`'s properties.
     *
     * Generated from Godot docs: GPUParticles3D.set_trail_enabled
     */
    fun setTrailEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTrailEnabledBind, handle, enabled)
    }

    /**
     * The amount of time the particle's trail should represent (in seconds). Only effective if
     * `trail_enabled` is `true`.
     *
     * Generated from Godot docs: GPUParticles3D.set_trail_lifetime
     */
    fun setTrailLifetime(secs: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTrailLifetimeBind, handle, secs)
    }

    /**
     * If `true`, enables particle trails using a mesh skinning system. Designed to work with
     * `RibbonTrailMesh` and `TubeTrailMesh`. Note: `BaseMaterial3D.use_particle_trails` must also be
     * enabled on the particle mesh's material. Otherwise, setting `trail_enabled` to `true` will have
     * no effect. Note: Unlike `GPUParticles2D`, the number of trail sections and subdivisions is set
     * in the `RibbonTrailMesh` or the `TubeTrailMesh`'s properties.
     *
     * Generated from Godot docs: GPUParticles3D.is_trail_enabled
     */
    fun isTrailEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTrailEnabledBind, handle)
    }

    /**
     * The amount of time the particle's trail should represent (in seconds). Only effective if
     * `trail_enabled` is `true`.
     *
     * Generated from Godot docs: GPUParticles3D.get_trail_lifetime
     */
    fun getTrailLifetime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTrailLifetimeBind, handle)
    }

    /**
     * The alignment of particles. Use this for billboarding and aligning to velocity.
     *
     * Generated from Godot docs: GPUParticles3D.set_transform_align
     */
    fun setTransformAlign(align: Long) {
        ObjectCalls.ptrcallWithLongArg(setTransformAlignBind, handle, align)
    }

    /**
     * The alignment of particles. Use this for billboarding and aligning to velocity.
     *
     * Generated from Godot docs: GPUParticles3D.get_transform_align
     */
    fun getTransformAlign(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTransformAlignBind, handle)
    }

    /**
     * In the case of billboarded particles, which custom channel to read from to calculate their
     * angle.
     *
     * Generated from Godot docs: GPUParticles3D.set_transform_align_channel_filter
     */
    fun setTransformAlignChannelFilter(channelFilter: Long) {
        ObjectCalls.ptrcallWithLongArg(setTransformAlignChannelFilterBind, handle, channelFilter)
    }

    /**
     * In the case of billboarded particles, which custom channel to read from to calculate their
     * angle.
     *
     * Generated from Godot docs: GPUParticles3D.get_transform_align_channel_filter
     */
    fun getTransformAlignChannelFilter(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTransformAlignChannelFilterBind, handle)
    }

    /**
     * When using transform align local billboard, which axis to use for the billboarding. Supports
     * only X or Y.
     *
     * Generated from Godot docs: GPUParticles3D.set_transform_align_axis
     */
    fun setTransformAlignAxis(align: Long) {
        ObjectCalls.ptrcallWithLongArg(setTransformAlignAxisBind, handle, align)
    }

    /**
     * When using transform align local billboard, which axis to use for the billboarding. Supports
     * only X or Y.
     *
     * Generated from Godot docs: GPUParticles3D.get_transform_align_axis
     */
    fun getTransformAlignAxis(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTransformAlignAxisBind, handle)
    }

    /**
     * Sets this node's properties to match a given `CPUParticles3D` node.
     *
     * Generated from Godot docs: GPUParticles3D.convert_from_particles
     */
    fun convertFromParticles(particles: Node) {
        ObjectCalls.ptrcallWithObjectArgs(convertFromParticlesBind, handle, listOf(particles.handle))
    }

    /**
     * The ratio of particles that should actually be emitted. If set to a value lower than `1.0`, this
     * will set the amount of emitted particles throughout the lifetime to `amount * amount_ratio`.
     * Unlike changing `amount`, changing `amount_ratio` while emitting does not affect already-emitted
     * particles and doesn't cause the particle system to restart. `amount_ratio` can be used to create
     * effects that make the number of emitted particles vary over time. Note: Reducing the
     * `amount_ratio` has no performance benefit, since resources need to be allocated and processed
     * for the total `amount` of particles regardless of the `amount_ratio`. If you don't intend to
     * change the number of particles emitted while the particles are emitting, make sure
     * `amount_ratio` is set to `1` and change `amount` to your liking instead.
     *
     * Generated from Godot docs: GPUParticles3D.set_amount_ratio
     */
    fun setAmountRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAmountRatioBind, handle, ratio)
    }

    /**
     * The ratio of particles that should actually be emitted. If set to a value lower than `1.0`, this
     * will set the amount of emitted particles throughout the lifetime to `amount * amount_ratio`.
     * Unlike changing `amount`, changing `amount_ratio` while emitting does not affect already-emitted
     * particles and doesn't cause the particle system to restart. `amount_ratio` can be used to create
     * effects that make the number of emitted particles vary over time. Note: Reducing the
     * `amount_ratio` has no performance benefit, since resources need to be allocated and processed
     * for the total `amount` of particles regardless of the `amount_ratio`. If you don't intend to
     * change the number of particles emitted while the particles are emitting, make sure
     * `amount_ratio` is set to `1` and change `amount` to your liking instead.
     *
     * Generated from Godot docs: GPUParticles3D.get_amount_ratio
     */
    fun getAmountRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAmountRatioBind, handle)
    }

    /**
     * Requests the particles to process for extra process time during a single frame. `process_time`
     * defines the time that the particles will process while emitting is on. `process_time_residual`
     * defines the time that particles will process with emitting turned off for the simulation. When
     * combined with `speed_scale` set to `0.0`, this is useful to be able to seek a particle system
     * timeline.
     *
     * Generated from Godot docs: GPUParticles3D.request_particles_process
     */
    fun requestParticlesProcess(processTime: Double, processTimeResidual: Double = 0.0) {
        ObjectCalls.ptrcallWithTwoDoubleArgs(requestParticlesProcessBind, handle, processTime, processTimeResidual)
    }

    object Signals {
        const val finished: String = "finished"
    }

    companion object {
        const val MAX_DRAW_PASSES: Long = 4L
        const val DRAW_ORDER_INDEX: Long = 0L
        const val DRAW_ORDER_LIFETIME: Long = 1L
        const val DRAW_ORDER_REVERSE_LIFETIME: Long = 2L
        const val DRAW_ORDER_VIEW_DEPTH: Long = 3L
        const val EMIT_FLAG_POSITION: Long = 1L
        const val EMIT_FLAG_ROTATION_SCALE: Long = 2L
        const val EMIT_FLAG_VELOCITY: Long = 4L
        const val EMIT_FLAG_COLOR: Long = 8L
        const val EMIT_FLAG_CUSTOM: Long = 16L
        const val TRANSFORM_ALIGN_DISABLED: Long = 0L
        const val TRANSFORM_ALIGN_Z_BILLBOARD: Long = 1L
        const val TRANSFORM_ALIGN_Y_TO_VELOCITY: Long = 2L
        const val TRANSFORM_ALIGN_Z_BILLBOARD_Y_TO_VELOCITY: Long = 3L
        const val TRANSFORM_ALIGN_LOCAL_BILLBOARD: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): GPUParticles3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GPUParticles3D? =
            if (handle.address() == 0L) null else GPUParticles3D(handle)

        private const val SET_EMITTING_HASH = 2586408642L
        private val setEmittingBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_emitting", SET_EMITTING_HASH)
        }

        private const val SET_AMOUNT_HASH = 1286410249L
        private val setAmountBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_amount", SET_AMOUNT_HASH)
        }

        private const val SET_LIFETIME_HASH = 373806689L
        private val setLifetimeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_lifetime", SET_LIFETIME_HASH)
        }

        private const val SET_ONE_SHOT_HASH = 2586408642L
        private val setOneShotBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_one_shot", SET_ONE_SHOT_HASH)
        }

        private const val SET_PRE_PROCESS_TIME_HASH = 373806689L
        private val setPreProcessTimeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_pre_process_time", SET_PRE_PROCESS_TIME_HASH)
        }

        private const val SET_EXPLOSIVENESS_RATIO_HASH = 373806689L
        private val setExplosivenessRatioBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_explosiveness_ratio", SET_EXPLOSIVENESS_RATIO_HASH)
        }

        private const val SET_RANDOMNESS_RATIO_HASH = 373806689L
        private val setRandomnessRatioBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_randomness_ratio", SET_RANDOMNESS_RATIO_HASH)
        }

        private const val SET_VISIBILITY_AABB_HASH = 259215842L
        private val setVisibilityAabbBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_visibility_aabb", SET_VISIBILITY_AABB_HASH)
        }

        private const val SET_USE_LOCAL_COORDINATES_HASH = 2586408642L
        private val setUseLocalCoordinatesBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_use_local_coordinates", SET_USE_LOCAL_COORDINATES_HASH)
        }

        private const val SET_FIXED_FPS_HASH = 1286410249L
        private val setFixedFpsBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_fixed_fps", SET_FIXED_FPS_HASH)
        }

        private const val SET_FRACTIONAL_DELTA_HASH = 2586408642L
        private val setFractionalDeltaBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_fractional_delta", SET_FRACTIONAL_DELTA_HASH)
        }

        private const val SET_INTERPOLATE_HASH = 2586408642L
        private val setInterpolateBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_interpolate", SET_INTERPOLATE_HASH)
        }

        private const val SET_PROCESS_MATERIAL_HASH = 2757459619L
        private val setProcessMaterialBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_process_material", SET_PROCESS_MATERIAL_HASH)
        }

        private const val SET_SPEED_SCALE_HASH = 373806689L
        private val setSpeedScaleBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_speed_scale", SET_SPEED_SCALE_HASH)
        }

        private const val SET_COLLISION_BASE_SIZE_HASH = 373806689L
        private val setCollisionBaseSizeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_collision_base_size", SET_COLLISION_BASE_SIZE_HASH)
        }

        private const val SET_INTERP_TO_END_HASH = 373806689L
        private val setInterpToEndBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_interp_to_end", SET_INTERP_TO_END_HASH)
        }

        private const val IS_EMITTING_HASH = 36873697L
        private val isEmittingBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "is_emitting", IS_EMITTING_HASH)
        }

        private const val GET_AMOUNT_HASH = 3905245786L
        private val getAmountBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_amount", GET_AMOUNT_HASH)
        }

        private const val GET_LIFETIME_HASH = 1740695150L
        private val getLifetimeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_lifetime", GET_LIFETIME_HASH)
        }

        private const val GET_ONE_SHOT_HASH = 36873697L
        private val getOneShotBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_one_shot", GET_ONE_SHOT_HASH)
        }

        private const val GET_PRE_PROCESS_TIME_HASH = 1740695150L
        private val getPreProcessTimeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_pre_process_time", GET_PRE_PROCESS_TIME_HASH)
        }

        private const val GET_EXPLOSIVENESS_RATIO_HASH = 1740695150L
        private val getExplosivenessRatioBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_explosiveness_ratio", GET_EXPLOSIVENESS_RATIO_HASH)
        }

        private const val GET_RANDOMNESS_RATIO_HASH = 1740695150L
        private val getRandomnessRatioBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_randomness_ratio", GET_RANDOMNESS_RATIO_HASH)
        }

        private const val GET_VISIBILITY_AABB_HASH = 1068685055L
        private val getVisibilityAabbBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_visibility_aabb", GET_VISIBILITY_AABB_HASH)
        }

        private const val GET_USE_LOCAL_COORDINATES_HASH = 36873697L
        private val getUseLocalCoordinatesBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_use_local_coordinates", GET_USE_LOCAL_COORDINATES_HASH)
        }

        private const val GET_FIXED_FPS_HASH = 3905245786L
        private val getFixedFpsBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_fixed_fps", GET_FIXED_FPS_HASH)
        }

        private const val GET_FRACTIONAL_DELTA_HASH = 36873697L
        private val getFractionalDeltaBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_fractional_delta", GET_FRACTIONAL_DELTA_HASH)
        }

        private const val GET_INTERPOLATE_HASH = 36873697L
        private val getInterpolateBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_interpolate", GET_INTERPOLATE_HASH)
        }

        private const val GET_PROCESS_MATERIAL_HASH = 5934680L
        private val getProcessMaterialBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_process_material", GET_PROCESS_MATERIAL_HASH)
        }

        private const val GET_SPEED_SCALE_HASH = 1740695150L
        private val getSpeedScaleBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_speed_scale", GET_SPEED_SCALE_HASH)
        }

        private const val GET_COLLISION_BASE_SIZE_HASH = 1740695150L
        private val getCollisionBaseSizeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_collision_base_size", GET_COLLISION_BASE_SIZE_HASH)
        }

        private const val GET_INTERP_TO_END_HASH = 1740695150L
        private val getInterpToEndBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_interp_to_end", GET_INTERP_TO_END_HASH)
        }

        private const val SET_USE_FIXED_SEED_HASH = 2586408642L
        private val setUseFixedSeedBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_use_fixed_seed", SET_USE_FIXED_SEED_HASH)
        }

        private const val GET_USE_FIXED_SEED_HASH = 36873697L
        private val getUseFixedSeedBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_use_fixed_seed", GET_USE_FIXED_SEED_HASH)
        }

        private const val SET_SEED_HASH = 1286410249L
        private val setSeedBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_seed", SET_SEED_HASH)
        }

        private const val GET_SEED_HASH = 3905245786L
        private val getSeedBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_seed", GET_SEED_HASH)
        }

        private const val SET_DRAW_ORDER_HASH = 1208074815L
        private val setDrawOrderBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_draw_order", SET_DRAW_ORDER_HASH)
        }

        private const val GET_DRAW_ORDER_HASH = 3770381780L
        private val getDrawOrderBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_draw_order", GET_DRAW_ORDER_HASH)
        }

        private const val SET_DRAW_PASSES_HASH = 1286410249L
        private val setDrawPassesBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_draw_passes", SET_DRAW_PASSES_HASH)
        }

        private const val SET_DRAW_PASS_MESH_HASH = 969122797L
        private val setDrawPassMeshBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_draw_pass_mesh", SET_DRAW_PASS_MESH_HASH)
        }

        private const val GET_DRAW_PASSES_HASH = 3905245786L
        private val getDrawPassesBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_draw_passes", GET_DRAW_PASSES_HASH)
        }

        private const val GET_DRAW_PASS_MESH_HASH = 1576363275L
        private val getDrawPassMeshBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_draw_pass_mesh", GET_DRAW_PASS_MESH_HASH)
        }

        private const val SET_SKIN_HASH = 3971435618L
        private val setSkinBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_skin", SET_SKIN_HASH)
        }

        private const val GET_SKIN_HASH = 2074563878L
        private val getSkinBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_skin", GET_SKIN_HASH)
        }

        private const val RESTART_HASH = 107499316L
        private val restartBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "restart", RESTART_HASH)
        }

        private const val CAPTURE_AABB_HASH = 1068685055L
        private val captureAabbBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "capture_aabb", CAPTURE_AABB_HASH)
        }

        private const val SET_SUB_EMITTER_HASH = 1348162250L
        private val setSubEmitterBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_sub_emitter", SET_SUB_EMITTER_HASH)
        }

        private const val GET_SUB_EMITTER_HASH = 4075236667L
        private val getSubEmitterBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_sub_emitter", GET_SUB_EMITTER_HASH)
        }

        private const val EMIT_PARTICLE_HASH = 992173727L
        private val emitParticleBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "emit_particle", EMIT_PARTICLE_HASH)
        }

        private const val SET_TRAIL_ENABLED_HASH = 2586408642L
        private val setTrailEnabledBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_trail_enabled", SET_TRAIL_ENABLED_HASH)
        }

        private const val SET_TRAIL_LIFETIME_HASH = 373806689L
        private val setTrailLifetimeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_trail_lifetime", SET_TRAIL_LIFETIME_HASH)
        }

        private const val IS_TRAIL_ENABLED_HASH = 36873697L
        private val isTrailEnabledBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "is_trail_enabled", IS_TRAIL_ENABLED_HASH)
        }

        private const val GET_TRAIL_LIFETIME_HASH = 1740695150L
        private val getTrailLifetimeBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_trail_lifetime", GET_TRAIL_LIFETIME_HASH)
        }

        private const val SET_TRANSFORM_ALIGN_HASH = 3892425954L
        private val setTransformAlignBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_transform_align", SET_TRANSFORM_ALIGN_HASH)
        }

        private const val GET_TRANSFORM_ALIGN_HASH = 2100992166L
        private val getTransformAlignBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_transform_align", GET_TRANSFORM_ALIGN_HASH)
        }

        private const val SET_TRANSFORM_ALIGN_CHANNEL_FILTER_HASH = 540833286L
        private val setTransformAlignChannelFilterBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_transform_align_channel_filter", SET_TRANSFORM_ALIGN_CHANNEL_FILTER_HASH)
        }

        private const val GET_TRANSFORM_ALIGN_CHANNEL_FILTER_HASH = 1664431231L
        private val getTransformAlignChannelFilterBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_transform_align_channel_filter", GET_TRANSFORM_ALIGN_CHANNEL_FILTER_HASH)
        }

        private const val SET_TRANSFORM_ALIGN_AXIS_HASH = 3781785913L
        private val setTransformAlignAxisBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_transform_align_axis", SET_TRANSFORM_ALIGN_AXIS_HASH)
        }

        private const val GET_TRANSFORM_ALIGN_AXIS_HASH = 2427180841L
        private val getTransformAlignAxisBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_transform_align_axis", GET_TRANSFORM_ALIGN_AXIS_HASH)
        }

        private const val CONVERT_FROM_PARTICLES_HASH = 1078189570L
        private val convertFromParticlesBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "convert_from_particles", CONVERT_FROM_PARTICLES_HASH)
        }

        private const val SET_AMOUNT_RATIO_HASH = 373806689L
        private val setAmountRatioBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "set_amount_ratio", SET_AMOUNT_RATIO_HASH)
        }

        private const val GET_AMOUNT_RATIO_HASH = 1740695150L
        private val getAmountRatioBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "get_amount_ratio", GET_AMOUNT_RATIO_HASH)
        }

        private const val REQUEST_PARTICLES_PROCESS_HASH = 66938510L
        private val requestParticlesProcessBind by lazy {
            ObjectCalls.getMethodBind("GPUParticles3D", "request_particles_process", REQUEST_PARTICLES_PROCESS_HASH)
        }
    }
}
