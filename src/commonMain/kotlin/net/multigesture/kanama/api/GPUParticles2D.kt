package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2

/**
 * A 2D particle emitter.
 *
 * Generated from Godot docs: GPUParticles2D
 */
class GPUParticles2D(handle: GodotHandle) : Node2D(handle) {
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

    var visibilityRect: Rect2
        @JvmName("visibilityRectProperty")
        get() = getVisibilityRect()
        @JvmName("setVisibilityRectProperty")
        set(value) = setVisibilityRect(value)

    var localCoords: Boolean
        @JvmName("localCoordsProperty")
        get() = getUseLocalCoordinates()
        @JvmName("setLocalCoordsProperty")
        set(value) = setUseLocalCoordinates(value)

    var drawOrder: GPUParticles2D.DrawOrder
        @JvmName("drawOrderProperty")
        get() = getDrawOrder()
        @JvmName("setDrawOrderProperty")
        set(value) = setDrawOrder(value)

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

    var trailSections: Int
        @JvmName("trailSectionsProperty")
        get() = getTrailSections()
        @JvmName("setTrailSectionsProperty")
        set(value) = setTrailSections(value)

    var trailSectionSubdivisions: Int
        @JvmName("trailSectionSubdivisionsProperty")
        get() = getTrailSectionSubdivisions()
        @JvmName("setTrailSectionSubdivisionsProperty")
        set(value) = setTrailSectionSubdivisions(value)

    var processMaterial: Material?
        @JvmName("processMaterialProperty")
        get() = getProcessMaterial()
        @JvmName("setProcessMaterialProperty")
        set(value) = setProcessMaterial(value)

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
     * Generated from Godot docs: GPUParticles2D.set_emitting
     */
    fun setEmitting(emitting: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEmittingBind, segment, emitting)
    }

    /**
     * The number of particles to emit in one emission cycle. The effective emission rate is `(amount *
     * amount_ratio) / lifetime` particles per second. Higher values will increase GPU requirements,
     * even if not all particles are visible at a given time or if `amount_ratio` is decreased. Note:
     * Changing this value will cause the particle system to restart. To avoid this, change
     * `amount_ratio` instead.
     *
     * Generated from Godot docs: GPUParticles2D.set_amount
     */
    fun setAmount(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setAmountBind, segment, amount)
    }

    /**
     * The amount of time each particle will exist (in seconds). The effective emission rate is
     * `(amount * amount_ratio) / lifetime` particles per second.
     *
     * Generated from Godot docs: GPUParticles2D.set_lifetime
     */
    fun setLifetime(secs: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setLifetimeBind, segment, secs)
    }

    /**
     * If `true`, only one emission cycle occurs. If set `true` during a cycle, emission will stop at
     * the cycle's end.
     *
     * Generated from Godot docs: GPUParticles2D.set_one_shot
     */
    fun setOneShot(secs: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setOneShotBind, segment, secs)
    }

    /**
     * Particle system starts as if it had already run for this many seconds. Note: This can be very
     * expensive if set to a high number as it requires running the particle shader a number of times
     * equal to the `fixed_fps` (or 30, if `fixed_fps` is 0) for every second. In extreme cases it can
     * even lead to a GPU crash due to the volume of work done in a single frame.
     *
     * Generated from Godot docs: GPUParticles2D.set_pre_process_time
     */
    fun setPreProcessTime(secs: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPreProcessTimeBind, segment, secs)
    }

    /**
     * How rapidly particles in an emission cycle are emitted. If greater than `0`, there will be a gap
     * in emissions before the next cycle begins.
     *
     * Generated from Godot docs: GPUParticles2D.set_explosiveness_ratio
     */
    fun setExplosivenessRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setExplosivenessRatioBind, segment, ratio)
    }

    /**
     * Emission lifetime randomness ratio.
     *
     * Generated from Godot docs: GPUParticles2D.set_randomness_ratio
     */
    fun setRandomnessRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRandomnessRatioBind, segment, ratio)
    }

    /**
     * The `Rect2` that determines the node's region which needs to be visible on screen for the
     * particle system to be active. Grow the rect if particles suddenly appear/disappear when the node
     * enters/exits the screen. The `Rect2` can be grown via code or with the Particles → Generate
     * Visibility Rect editor tool.
     *
     * Generated from Godot docs: GPUParticles2D.set_visibility_rect
     */
    fun setVisibilityRect(visibilityRect: Rect2) {
        ObjectCalls.ptrcallWithRect2Arg(Binds.setVisibilityRectBind, segment, visibilityRect)
    }

    /**
     * If `true`, particles use the parent node's coordinate space (known as local coordinates). This
     * will cause particles to move and rotate along the `GPUParticles2D` node (and its parents) when
     * it is moved or rotated. If `false`, particles use global coordinates; they will not move or
     * rotate along the `GPUParticles2D` node (and its parents) when it is moved or rotated.
     *
     * Generated from Godot docs: GPUParticles2D.set_use_local_coordinates
     */
    fun setUseLocalCoordinates(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseLocalCoordinatesBind, segment, enable)
    }

    /**
     * The particle system's frame rate is fixed to a value. For example, changing the value to 2 will
     * make the particles render at 2 frames per second. Note this does not slow down the simulation of
     * the particle system itself.
     *
     * Generated from Godot docs: GPUParticles2D.set_fixed_fps
     */
    fun setFixedFps(fps: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setFixedFpsBind, segment, fps)
    }

    /**
     * If `true`, results in fractional delta calculation which has a smoother particles display
     * effect.
     *
     * Generated from Godot docs: GPUParticles2D.set_fractional_delta
     */
    fun setFractionalDelta(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setFractionalDeltaBind, segment, enable)
    }

    /**
     * Enables particle interpolation, which makes the particle movement smoother when their
     * `fixed_fps` is lower than the screen refresh rate.
     *
     * Generated from Godot docs: GPUParticles2D.set_interpolate
     */
    fun setInterpolate(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setInterpolateBind, segment, enable)
    }

    /**
     * `Material` for processing particles. Can be a `ParticleProcessMaterial` or a `ShaderMaterial`.
     *
     * Generated from Godot docs: GPUParticles2D.set_process_material
     */
    fun setProcessMaterial(material: Material?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setProcessMaterialBind, segment, listOf(material?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Particle system's running speed scaling ratio. A value of `0` can be used to pause the
     * particles.
     *
     * Generated from Godot docs: GPUParticles2D.set_speed_scale
     */
    fun setSpeedScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSpeedScaleBind, segment, scale)
    }

    /**
     * Multiplier for particle's collision radius. `1.0` corresponds to the size of the sprite. If
     * particles appear to sink into the ground when colliding, increase this value. If particles
     * appear to float when colliding, decrease this value. Only effective if
     * `ParticleProcessMaterial.collision_mode` is `ParticleProcessMaterial.CollisionMode.RIGID` or
     * `ParticleProcessMaterial.CollisionMode.HIDE_ON_CONTACT`. Note: Particles always have a spherical
     * collision shape.
     *
     * Generated from Godot docs: GPUParticles2D.set_collision_base_size
     */
    fun setCollisionBaseSize(size: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setCollisionBaseSizeBind, segment, size)
    }

    /**
     * Causes all the particles in this node to interpolate towards the end of their lifetime. Note:
     * This only works when used with a `ParticleProcessMaterial`. It needs to be manually implemented
     * for custom process shaders.
     *
     * Generated from Godot docs: GPUParticles2D.set_interp_to_end
     */
    fun setInterpToEnd(interp: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setInterpToEndBind, segment, interp)
    }

    /**
     * Requests the particles to process for extra process time during a single frame. `process_time`
     * defines the time that the particles will process while emitting is on. `process_time_residual`
     * defines the time that particles will process with emitting turned off for the simulation. When
     * combined with `speed_scale` set to `0.0`, this is useful to be able to seek a particle system
     * timeline.
     *
     * Generated from Godot docs: GPUParticles2D.request_particles_process
     */
    fun requestParticlesProcess(processTime: Double, processTimeResidual: Double = 0.0) {
        ObjectCalls.ptrcallWithTwoDoubleArgs(Binds.requestParticlesProcessBind, segment, processTime, processTimeResidual)
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
     * Generated from Godot docs: GPUParticles2D.is_emitting
     */
    fun isEmitting(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEmittingBind, segment)
    }

    /**
     * The number of particles to emit in one emission cycle. The effective emission rate is `(amount *
     * amount_ratio) / lifetime` particles per second. Higher values will increase GPU requirements,
     * even if not all particles are visible at a given time or if `amount_ratio` is decreased. Note:
     * Changing this value will cause the particle system to restart. To avoid this, change
     * `amount_ratio` instead.
     *
     * Generated from Godot docs: GPUParticles2D.get_amount
     */
    fun getAmount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getAmountBind, segment)
    }

    /**
     * The amount of time each particle will exist (in seconds). The effective emission rate is
     * `(amount * amount_ratio) / lifetime` particles per second.
     *
     * Generated from Godot docs: GPUParticles2D.get_lifetime
     */
    fun getLifetime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLifetimeBind, segment)
    }

    /**
     * If `true`, only one emission cycle occurs. If set `true` during a cycle, emission will stop at
     * the cycle's end.
     *
     * Generated from Godot docs: GPUParticles2D.get_one_shot
     */
    fun getOneShot(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getOneShotBind, segment)
    }

    /**
     * Particle system starts as if it had already run for this many seconds. Note: This can be very
     * expensive if set to a high number as it requires running the particle shader a number of times
     * equal to the `fixed_fps` (or 30, if `fixed_fps` is 0) for every second. In extreme cases it can
     * even lead to a GPU crash due to the volume of work done in a single frame.
     *
     * Generated from Godot docs: GPUParticles2D.get_pre_process_time
     */
    fun getPreProcessTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPreProcessTimeBind, segment)
    }

    /**
     * How rapidly particles in an emission cycle are emitted. If greater than `0`, there will be a gap
     * in emissions before the next cycle begins.
     *
     * Generated from Godot docs: GPUParticles2D.get_explosiveness_ratio
     */
    fun getExplosivenessRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getExplosivenessRatioBind, segment)
    }

    /**
     * Emission lifetime randomness ratio.
     *
     * Generated from Godot docs: GPUParticles2D.get_randomness_ratio
     */
    fun getRandomnessRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRandomnessRatioBind, segment)
    }

    /**
     * The `Rect2` that determines the node's region which needs to be visible on screen for the
     * particle system to be active. Grow the rect if particles suddenly appear/disappear when the node
     * enters/exits the screen. The `Rect2` can be grown via code or with the Particles → Generate
     * Visibility Rect editor tool.
     *
     * Generated from Godot docs: GPUParticles2D.get_visibility_rect
     */
    fun getVisibilityRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(Binds.getVisibilityRectBind, segment)
    }

    /**
     * If `true`, particles use the parent node's coordinate space (known as local coordinates). This
     * will cause particles to move and rotate along the `GPUParticles2D` node (and its parents) when
     * it is moved or rotated. If `false`, particles use global coordinates; they will not move or
     * rotate along the `GPUParticles2D` node (and its parents) when it is moved or rotated.
     *
     * Generated from Godot docs: GPUParticles2D.get_use_local_coordinates
     */
    fun getUseLocalCoordinates(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getUseLocalCoordinatesBind, segment)
    }

    /**
     * The particle system's frame rate is fixed to a value. For example, changing the value to 2 will
     * make the particles render at 2 frames per second. Note this does not slow down the simulation of
     * the particle system itself.
     *
     * Generated from Godot docs: GPUParticles2D.get_fixed_fps
     */
    fun getFixedFps(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getFixedFpsBind, segment)
    }

    /**
     * If `true`, results in fractional delta calculation which has a smoother particles display
     * effect.
     *
     * Generated from Godot docs: GPUParticles2D.get_fractional_delta
     */
    fun getFractionalDelta(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getFractionalDeltaBind, segment)
    }

    /**
     * Enables particle interpolation, which makes the particle movement smoother when their
     * `fixed_fps` is lower than the screen refresh rate.
     *
     * Generated from Godot docs: GPUParticles2D.get_interpolate
     */
    fun getInterpolate(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getInterpolateBind, segment)
    }

    /**
     * `Material` for processing particles. Can be a `ParticleProcessMaterial` or a `ShaderMaterial`.
     *
     * Generated from Godot docs: GPUParticles2D.get_process_material
     */
    fun getProcessMaterial(): Material? {
        return Material.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getProcessMaterialBind, segment))
    }

    /**
     * Particle system's running speed scaling ratio. A value of `0` can be used to pause the
     * particles.
     *
     * Generated from Godot docs: GPUParticles2D.get_speed_scale
     */
    fun getSpeedScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSpeedScaleBind, segment)
    }

    /**
     * Multiplier for particle's collision radius. `1.0` corresponds to the size of the sprite. If
     * particles appear to sink into the ground when colliding, increase this value. If particles
     * appear to float when colliding, decrease this value. Only effective if
     * `ParticleProcessMaterial.collision_mode` is `ParticleProcessMaterial.CollisionMode.RIGID` or
     * `ParticleProcessMaterial.CollisionMode.HIDE_ON_CONTACT`. Note: Particles always have a spherical
     * collision shape.
     *
     * Generated from Godot docs: GPUParticles2D.get_collision_base_size
     */
    fun getCollisionBaseSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCollisionBaseSizeBind, segment)
    }

    /**
     * Causes all the particles in this node to interpolate towards the end of their lifetime. Note:
     * This only works when used with a `ParticleProcessMaterial`. It needs to be manually implemented
     * for custom process shaders.
     *
     * Generated from Godot docs: GPUParticles2D.get_interp_to_end
     */
    fun getInterpToEnd(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getInterpToEndBind, segment)
    }

    /**
     * Particle draw order.
     *
     * Generated from Godot docs: GPUParticles2D.set_draw_order
     */
    fun setDrawOrder(order: GPUParticles2D.DrawOrder) {
        ObjectCalls.ptrcallWithLongArg(Binds.setDrawOrderBind, segment, order.value)
    }

    /**
     * Particle draw order.
     *
     * Generated from Godot docs: GPUParticles2D.get_draw_order
     */
    fun getDrawOrder(): GPUParticles2D.DrawOrder {
        return GPUParticles2D.DrawOrder(ObjectCalls.ptrcallNoArgsRetLong(Binds.getDrawOrderBind, segment))
    }

    /**
     * Particle texture. If `null`, particles will be squares with a size of 1×1 pixels. Note: To use a
     * flipbook texture, assign a new `CanvasItemMaterial` to the `GPUParticles2D`'s
     * `CanvasItem.material` property, then enable `CanvasItemMaterial.particles_animation` and set
     * `CanvasItemMaterial.particles_anim_h_frames`, `CanvasItemMaterial.particles_anim_v_frames`, and
     * `CanvasItemMaterial.particles_anim_loop` to match the flipbook texture.
     *
     * Generated from Godot docs: GPUParticles2D.set_texture
     */
    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Particle texture. If `null`, particles will be squares with a size of 1×1 pixels. Note: To use a
     * flipbook texture, assign a new `CanvasItemMaterial` to the `GPUParticles2D`'s
     * `CanvasItem.material` property, then enable `CanvasItemMaterial.particles_animation` and set
     * `CanvasItemMaterial.particles_anim_h_frames`, `CanvasItemMaterial.particles_anim_v_frames`, and
     * `CanvasItemMaterial.particles_anim_loop` to match the flipbook texture.
     *
     * Generated from Godot docs: GPUParticles2D.get_texture
     */
    fun getTexture(): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getTextureBind, segment))
    }

    /**
     * Returns a rectangle containing the positions of all existing particles. Note: When using
     * threaded rendering this method synchronizes the rendering thread. Calling it often may have a
     * negative impact on performance.
     *
     * Generated from Godot docs: GPUParticles2D.capture_rect
     */
    fun captureRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(Binds.captureRectBind, segment)
    }

    /**
     * Restarts the particle emission cycle, clearing existing particles. To avoid particles vanishing
     * from the viewport, wait for the `finished` signal before calling. Note: The `finished` signal is
     * only emitted by `one_shot` emitters. If `keep_seed` is `true`, the current random seed will be
     * preserved. Useful for seeking and playback.
     *
     * Generated from Godot docs: GPUParticles2D.restart
     */
    fun restart(keepSeed: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(Binds.restartBind, segment, keepSeed)
    }

    /**
     * Path to another `GPUParticles2D` node that will be used as a subemitter (see
     * `ParticleProcessMaterial.sub_emitter_mode`). Subemitters can be used to achieve effects such as
     * fireworks, sparks on collision, bubbles popping into water drops, and more. Note: When
     * `sub_emitter` is set, the target `GPUParticles2D` node will no longer emit particles on its own.
     *
     * Generated from Godot docs: GPUParticles2D.set_sub_emitter
     */
    fun setSubEmitter(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(Binds.setSubEmitterBind, segment, path)
    }

    /**
     * Path to another `GPUParticles2D` node that will be used as a subemitter (see
     * `ParticleProcessMaterial.sub_emitter_mode`). Subemitters can be used to achieve effects such as
     * fireworks, sparks on collision, bubbles popping into water drops, and more. Note: When
     * `sub_emitter` is set, the target `GPUParticles2D` node will no longer emit particles on its own.
     *
     * Generated from Godot docs: GPUParticles2D.get_sub_emitter
     */
    fun getSubEmitter(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getSubEmitterBind, segment)
    }

    /**
     * Emits a single particle. Whether `xform`, `velocity`, `color` and `custom` are applied depends
     * on the value of `flags`. See `EmitFlags`. The default ParticleProcessMaterial will overwrite
     * `color` and use the contents of `custom` as `(rotation, age, animation, lifetime)`. Note:
     * `emit_particle` is only supported on the Forward+ and Mobile rendering methods, not
     * Compatibility.
     *
     * Generated from Godot docs: GPUParticles2D.emit_particle
     */
    fun emitParticle(xform: Transform2D, velocity: Vector2, color: Color, custom: Color, flags: Long) {
        ObjectCalls.ptrcallWithTransform2DVector2TwoColorUInt32Args(Binds.emitParticleBind, segment, xform, velocity, color, custom, flags)
    }

    /**
     * If `true`, enables particle trails using a mesh skinning system. Note: Unlike `GPUParticles3D`,
     * the number of trail sections and subdivisions is set with the `trail_sections` and
     * `trail_section_subdivisions` properties.
     *
     * Generated from Godot docs: GPUParticles2D.set_trail_enabled
     */
    fun setTrailEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setTrailEnabledBind, segment, enabled)
    }

    /**
     * The amount of time the particle's trail should represent (in seconds). Only effective if
     * `trail_enabled` is `true`.
     *
     * Generated from Godot docs: GPUParticles2D.set_trail_lifetime
     */
    fun setTrailLifetime(secs: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setTrailLifetimeBind, segment, secs)
    }

    /**
     * If `true`, enables particle trails using a mesh skinning system. Note: Unlike `GPUParticles3D`,
     * the number of trail sections and subdivisions is set with the `trail_sections` and
     * `trail_section_subdivisions` properties.
     *
     * Generated from Godot docs: GPUParticles2D.is_trail_enabled
     */
    fun isTrailEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isTrailEnabledBind, segment)
    }

    /**
     * The amount of time the particle's trail should represent (in seconds). Only effective if
     * `trail_enabled` is `true`.
     *
     * Generated from Godot docs: GPUParticles2D.get_trail_lifetime
     */
    fun getTrailLifetime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getTrailLifetimeBind, segment)
    }

    /**
     * The number of sections to use for the particle trail rendering. Higher values can result in
     * smoother trail curves, at the cost of performance due to increased mesh complexity. See also
     * `trail_section_subdivisions`. Only effective if `trail_enabled` is `true`.
     *
     * Generated from Godot docs: GPUParticles2D.set_trail_sections
     */
    fun setTrailSections(sections: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setTrailSectionsBind, segment, sections)
    }

    /**
     * The number of sections to use for the particle trail rendering. Higher values can result in
     * smoother trail curves, at the cost of performance due to increased mesh complexity. See also
     * `trail_section_subdivisions`. Only effective if `trail_enabled` is `true`.
     *
     * Generated from Godot docs: GPUParticles2D.get_trail_sections
     */
    fun getTrailSections(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getTrailSectionsBind, segment)
    }

    /**
     * The number of subdivisions to use for the particle trail rendering. Higher values can result in
     * smoother trail curves, at the cost of performance due to increased mesh complexity. See also
     * `trail_sections`. Only effective if `trail_enabled` is `true`.
     *
     * Generated from Godot docs: GPUParticles2D.set_trail_section_subdivisions
     */
    fun setTrailSectionSubdivisions(subdivisions: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setTrailSectionSubdivisionsBind, segment, subdivisions)
    }

    /**
     * The number of subdivisions to use for the particle trail rendering. Higher values can result in
     * smoother trail curves, at the cost of performance due to increased mesh complexity. See also
     * `trail_sections`. Only effective if `trail_enabled` is `true`.
     *
     * Generated from Godot docs: GPUParticles2D.get_trail_section_subdivisions
     */
    fun getTrailSectionSubdivisions(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getTrailSectionSubdivisionsBind, segment)
    }

    /**
     * Sets this node's properties to match a given `CPUParticles2D` node.
     *
     * Generated from Godot docs: GPUParticles2D.convert_from_particles
     */
    fun convertFromParticles(particles: Node) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.convertFromParticlesBind, segment, listOf(particles.segment))
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
     * Generated from Godot docs: GPUParticles2D.set_amount_ratio
     */
    fun setAmountRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAmountRatioBind, segment, ratio)
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
     * Generated from Godot docs: GPUParticles2D.get_amount_ratio
     */
    fun getAmountRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAmountRatioBind, segment)
    }

    /**
     * If `true`, particles will use the same seed for every simulation using the seed defined in
     * `seed`. This is useful for situations where the visual outcome should be consistent across
     * replays, for example when using Movie Maker mode.
     *
     * Generated from Godot docs: GPUParticles2D.set_use_fixed_seed
     */
    fun setUseFixedSeed(useFixedSeed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseFixedSeedBind, segment, useFixedSeed)
    }

    /**
     * If `true`, particles will use the same seed for every simulation using the seed defined in
     * `seed`. This is useful for situations where the visual outcome should be consistent across
     * replays, for example when using Movie Maker mode.
     *
     * Generated from Godot docs: GPUParticles2D.get_use_fixed_seed
     */
    fun getUseFixedSeed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getUseFixedSeedBind, segment)
    }

    /**
     * Sets the random seed used by the particle system. Only effective if `use_fixed_seed` is `true`.
     *
     * Generated from Godot docs: GPUParticles2D.set_seed
     */
    fun setSeed(seed: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setSeedBind, segment, seed)
    }

    /**
     * Sets the random seed used by the particle system. Only effective if `use_fixed_seed` is `true`.
     *
     * Generated from Godot docs: GPUParticles2D.get_seed
     */
    fun getSeed(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getSeedBind, segment)
    }

    /** Signal `finished()`; see [TypedSignal]. */
    val finished: Signal0
        @JvmName("finishedTypedSignal")
        get() = Signal0(this, "finished")

    object Signals {
        const val finished: String = "finished"
    }

    /**
     * Godot's `GPUParticles2D.DrawOrder` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`GPUParticles2D.DrawOrder.<NAME>`).
     *
     * Generated from Godot docs: GPUParticles2D.DrawOrder
     */
    @JvmInline
    value class DrawOrder(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Particles are drawn in the order emitted.
             *
             * Generated from Godot docs: GPUParticles2D.DRAW_ORDER_INDEX
             */
            val INDEX: DrawOrder get() = DrawOrder(0L)
            /**
             * Particles are drawn in order of remaining lifetime. In other words, the particle with the
             * highest lifetime is drawn at the front.
             *
             * Generated from Godot docs: GPUParticles2D.DRAW_ORDER_LIFETIME
             */
            val LIFETIME: DrawOrder get() = DrawOrder(1L)
            /**
             * Particles are drawn in reverse order of remaining lifetime. In other words, the particle with
             * the lowest lifetime is drawn at the front.
             *
             * Generated from Godot docs: GPUParticles2D.DRAW_ORDER_REVERSE_LIFETIME
             */
            val REVERSE_LIFETIME: DrawOrder get() = DrawOrder(2L)
        }
    }

    /**
     * Godot's `GPUParticles2D.EmitFlags` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`GPUParticles2D.EmitFlags.<NAME>`).
     *
     * Generated from Godot docs: GPUParticles2D.EmitFlags
     */
    @JvmInline
    value class EmitFlags(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Particle starts at the specified position.
             *
             * Generated from Godot docs: GPUParticles2D.EMIT_FLAG_POSITION
             */
            val POSITION: EmitFlags get() = EmitFlags(1L)
            /**
             * Particle starts with specified rotation and scale.
             *
             * Generated from Godot docs: GPUParticles2D.EMIT_FLAG_ROTATION_SCALE
             */
            val ROTATION_SCALE: EmitFlags get() = EmitFlags(2L)
            /**
             * Particle starts with the specified velocity vector, which defines the emission direction and
             * speed.
             *
             * Generated from Godot docs: GPUParticles2D.EMIT_FLAG_VELOCITY
             */
            val VELOCITY: EmitFlags get() = EmitFlags(4L)
            /**
             * Particle starts with specified color.
             *
             * Generated from Godot docs: GPUParticles2D.EMIT_FLAG_COLOR
             */
            val COLOR: EmitFlags get() = EmitFlags(8L)
            /**
             * Particle starts with specified `CUSTOM` data.
             *
             * Generated from Godot docs: GPUParticles2D.EMIT_FLAG_CUSTOM
             */
            val CUSTOM: EmitFlags get() = EmitFlags(16L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GPUParticles2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GPUParticles2D? =
            if (handle.address() == 0L) null else GPUParticles2D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_EMITTING_HASH = 2586408642L
        @JvmField
        val setEmittingBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_emitting", SET_EMITTING_HASH)

        private const val SET_AMOUNT_HASH = 1286410249L
        @JvmField
        val setAmountBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_amount", SET_AMOUNT_HASH)

        private const val SET_LIFETIME_HASH = 373806689L
        @JvmField
        val setLifetimeBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_lifetime", SET_LIFETIME_HASH)

        private const val SET_ONE_SHOT_HASH = 2586408642L
        @JvmField
        val setOneShotBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_one_shot", SET_ONE_SHOT_HASH)

        private const val SET_PRE_PROCESS_TIME_HASH = 373806689L
        @JvmField
        val setPreProcessTimeBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_pre_process_time", SET_PRE_PROCESS_TIME_HASH)

        private const val SET_EXPLOSIVENESS_RATIO_HASH = 373806689L
        @JvmField
        val setExplosivenessRatioBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_explosiveness_ratio", SET_EXPLOSIVENESS_RATIO_HASH)

        private const val SET_RANDOMNESS_RATIO_HASH = 373806689L
        @JvmField
        val setRandomnessRatioBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_randomness_ratio", SET_RANDOMNESS_RATIO_HASH)

        private const val SET_VISIBILITY_RECT_HASH = 2046264180L
        @JvmField
        val setVisibilityRectBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_visibility_rect", SET_VISIBILITY_RECT_HASH)

        private const val SET_USE_LOCAL_COORDINATES_HASH = 2586408642L
        @JvmField
        val setUseLocalCoordinatesBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_use_local_coordinates", SET_USE_LOCAL_COORDINATES_HASH)

        private const val SET_FIXED_FPS_HASH = 1286410249L
        @JvmField
        val setFixedFpsBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_fixed_fps", SET_FIXED_FPS_HASH)

        private const val SET_FRACTIONAL_DELTA_HASH = 2586408642L
        @JvmField
        val setFractionalDeltaBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_fractional_delta", SET_FRACTIONAL_DELTA_HASH)

        private const val SET_INTERPOLATE_HASH = 2586408642L
        @JvmField
        val setInterpolateBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_interpolate", SET_INTERPOLATE_HASH)

        private const val SET_PROCESS_MATERIAL_HASH = 2757459619L
        @JvmField
        val setProcessMaterialBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_process_material", SET_PROCESS_MATERIAL_HASH)

        private const val SET_SPEED_SCALE_HASH = 373806689L
        @JvmField
        val setSpeedScaleBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_speed_scale", SET_SPEED_SCALE_HASH)

        private const val SET_COLLISION_BASE_SIZE_HASH = 373806689L
        @JvmField
        val setCollisionBaseSizeBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_collision_base_size", SET_COLLISION_BASE_SIZE_HASH)

        private const val SET_INTERP_TO_END_HASH = 373806689L
        @JvmField
        val setInterpToEndBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_interp_to_end", SET_INTERP_TO_END_HASH)

        private const val REQUEST_PARTICLES_PROCESS_HASH = 2019720106L
        @JvmField
        val requestParticlesProcessBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "request_particles_process", REQUEST_PARTICLES_PROCESS_HASH)

        private const val IS_EMITTING_HASH = 36873697L
        @JvmField
        val isEmittingBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "is_emitting", IS_EMITTING_HASH)

        private const val GET_AMOUNT_HASH = 3905245786L
        @JvmField
        val getAmountBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_amount", GET_AMOUNT_HASH)

        private const val GET_LIFETIME_HASH = 1740695150L
        @JvmField
        val getLifetimeBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_lifetime", GET_LIFETIME_HASH)

        private const val GET_ONE_SHOT_HASH = 36873697L
        @JvmField
        val getOneShotBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_one_shot", GET_ONE_SHOT_HASH)

        private const val GET_PRE_PROCESS_TIME_HASH = 1740695150L
        @JvmField
        val getPreProcessTimeBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_pre_process_time", GET_PRE_PROCESS_TIME_HASH)

        private const val GET_EXPLOSIVENESS_RATIO_HASH = 1740695150L
        @JvmField
        val getExplosivenessRatioBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_explosiveness_ratio", GET_EXPLOSIVENESS_RATIO_HASH)

        private const val GET_RANDOMNESS_RATIO_HASH = 1740695150L
        @JvmField
        val getRandomnessRatioBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_randomness_ratio", GET_RANDOMNESS_RATIO_HASH)

        private const val GET_VISIBILITY_RECT_HASH = 1639390495L
        @JvmField
        val getVisibilityRectBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_visibility_rect", GET_VISIBILITY_RECT_HASH)

        private const val GET_USE_LOCAL_COORDINATES_HASH = 36873697L
        @JvmField
        val getUseLocalCoordinatesBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_use_local_coordinates", GET_USE_LOCAL_COORDINATES_HASH)

        private const val GET_FIXED_FPS_HASH = 3905245786L
        @JvmField
        val getFixedFpsBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_fixed_fps", GET_FIXED_FPS_HASH)

        private const val GET_FRACTIONAL_DELTA_HASH = 36873697L
        @JvmField
        val getFractionalDeltaBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_fractional_delta", GET_FRACTIONAL_DELTA_HASH)

        private const val GET_INTERPOLATE_HASH = 36873697L
        @JvmField
        val getInterpolateBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_interpolate", GET_INTERPOLATE_HASH)

        private const val GET_PROCESS_MATERIAL_HASH = 5934680L
        @JvmField
        val getProcessMaterialBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_process_material", GET_PROCESS_MATERIAL_HASH)

        private const val GET_SPEED_SCALE_HASH = 1740695150L
        @JvmField
        val getSpeedScaleBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_speed_scale", GET_SPEED_SCALE_HASH)

        private const val GET_COLLISION_BASE_SIZE_HASH = 1740695150L
        @JvmField
        val getCollisionBaseSizeBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_collision_base_size", GET_COLLISION_BASE_SIZE_HASH)

        private const val GET_INTERP_TO_END_HASH = 1740695150L
        @JvmField
        val getInterpToEndBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_interp_to_end", GET_INTERP_TO_END_HASH)

        private const val SET_DRAW_ORDER_HASH = 1939677959L
        @JvmField
        val setDrawOrderBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_draw_order", SET_DRAW_ORDER_HASH)

        private const val GET_DRAW_ORDER_HASH = 941479095L
        @JvmField
        val getDrawOrderBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_draw_order", GET_DRAW_ORDER_HASH)

        private const val SET_TEXTURE_HASH = 4051416890L
        @JvmField
        val setTextureBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_texture", SET_TEXTURE_HASH)

        private const val GET_TEXTURE_HASH = 3635182373L
        @JvmField
        val getTextureBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_texture", GET_TEXTURE_HASH)

        private const val CAPTURE_RECT_HASH = 1639390495L
        @JvmField
        val captureRectBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "capture_rect", CAPTURE_RECT_HASH)

        private const val RESTART_HASH = 107499316L
        @JvmField
        val restartBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "restart", RESTART_HASH)

        private const val SET_SUB_EMITTER_HASH = 1348162250L
        @JvmField
        val setSubEmitterBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_sub_emitter", SET_SUB_EMITTER_HASH)

        private const val GET_SUB_EMITTER_HASH = 4075236667L
        @JvmField
        val getSubEmitterBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_sub_emitter", GET_SUB_EMITTER_HASH)

        private const val EMIT_PARTICLE_HASH = 2179202058L
        @JvmField
        val emitParticleBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "emit_particle", EMIT_PARTICLE_HASH)

        private const val SET_TRAIL_ENABLED_HASH = 2586408642L
        @JvmField
        val setTrailEnabledBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_trail_enabled", SET_TRAIL_ENABLED_HASH)

        private const val SET_TRAIL_LIFETIME_HASH = 373806689L
        @JvmField
        val setTrailLifetimeBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_trail_lifetime", SET_TRAIL_LIFETIME_HASH)

        private const val IS_TRAIL_ENABLED_HASH = 36873697L
        @JvmField
        val isTrailEnabledBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "is_trail_enabled", IS_TRAIL_ENABLED_HASH)

        private const val GET_TRAIL_LIFETIME_HASH = 1740695150L
        @JvmField
        val getTrailLifetimeBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_trail_lifetime", GET_TRAIL_LIFETIME_HASH)

        private const val SET_TRAIL_SECTIONS_HASH = 1286410249L
        @JvmField
        val setTrailSectionsBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_trail_sections", SET_TRAIL_SECTIONS_HASH)

        private const val GET_TRAIL_SECTIONS_HASH = 3905245786L
        @JvmField
        val getTrailSectionsBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_trail_sections", GET_TRAIL_SECTIONS_HASH)

        private const val SET_TRAIL_SECTION_SUBDIVISIONS_HASH = 1286410249L
        @JvmField
        val setTrailSectionSubdivisionsBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_trail_section_subdivisions", SET_TRAIL_SECTION_SUBDIVISIONS_HASH)

        private const val GET_TRAIL_SECTION_SUBDIVISIONS_HASH = 3905245786L
        @JvmField
        val getTrailSectionSubdivisionsBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_trail_section_subdivisions", GET_TRAIL_SECTION_SUBDIVISIONS_HASH)

        private const val CONVERT_FROM_PARTICLES_HASH = 1078189570L
        @JvmField
        val convertFromParticlesBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "convert_from_particles", CONVERT_FROM_PARTICLES_HASH)

        private const val SET_AMOUNT_RATIO_HASH = 373806689L
        @JvmField
        val setAmountRatioBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_amount_ratio", SET_AMOUNT_RATIO_HASH)

        private const val GET_AMOUNT_RATIO_HASH = 1740695150L
        @JvmField
        val getAmountRatioBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_amount_ratio", GET_AMOUNT_RATIO_HASH)

        private const val SET_USE_FIXED_SEED_HASH = 2586408642L
        @JvmField
        val setUseFixedSeedBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_use_fixed_seed", SET_USE_FIXED_SEED_HASH)

        private const val GET_USE_FIXED_SEED_HASH = 36873697L
        @JvmField
        val getUseFixedSeedBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_use_fixed_seed", GET_USE_FIXED_SEED_HASH)

        private const val SET_SEED_HASH = 1286410249L
        @JvmField
        val setSeedBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "set_seed", SET_SEED_HASH)

        private const val GET_SEED_HASH = 3905245786L
        @JvmField
        val getSeedBind =
            ObjectCalls.getMethodBind("GPUParticles2D", "get_seed", GET_SEED_HASH)
    }
}
