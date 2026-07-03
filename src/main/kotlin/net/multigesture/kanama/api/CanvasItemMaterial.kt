package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A material for `CanvasItem`s.
 *
 * Generated from Godot docs: CanvasItemMaterial
 */
class CanvasItemMaterial(handle: MemorySegment) : Material(handle) {
    var blendMode: Long
        @JvmName("blendModeProperty")
        get() = getBlendMode()
        @JvmName("setBlendModeProperty")
        set(value) = setBlendMode(value)

    var lightMode: Long
        @JvmName("lightModeProperty")
        get() = getLightMode()
        @JvmName("setLightModeProperty")
        set(value) = setLightMode(value)

    var particlesAnimation: Boolean
        @JvmName("particlesAnimationProperty")
        get() = getParticlesAnimation()
        @JvmName("setParticlesAnimationProperty")
        set(value) = setParticlesAnimation(value)

    var particlesAnimHFrames: Int
        @JvmName("particlesAnimHFramesProperty")
        get() = getParticlesAnimHFrames()
        @JvmName("setParticlesAnimHFramesProperty")
        set(value) = setParticlesAnimHFrames(value)

    var particlesAnimVFrames: Int
        @JvmName("particlesAnimVFramesProperty")
        get() = getParticlesAnimVFrames()
        @JvmName("setParticlesAnimVFramesProperty")
        set(value) = setParticlesAnimVFrames(value)

    var particlesAnimLoop: Boolean
        @JvmName("particlesAnimLoopProperty")
        get() = getParticlesAnimLoop()
        @JvmName("setParticlesAnimLoopProperty")
        set(value) = setParticlesAnimLoop(value)

    /**
     * The manner in which a material's rendering is applied to underlying textures.
     *
     * Generated from Godot docs: CanvasItemMaterial.set_blend_mode
     */
    fun setBlendMode(blendMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setBlendModeBind, handle, blendMode)
    }

    /**
     * The manner in which a material's rendering is applied to underlying textures.
     *
     * Generated from Godot docs: CanvasItemMaterial.get_blend_mode
     */
    fun getBlendMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBlendModeBind, handle)
    }

    /**
     * The manner in which material reacts to lighting.
     *
     * Generated from Godot docs: CanvasItemMaterial.set_light_mode
     */
    fun setLightMode(lightMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setLightModeBind, handle, lightMode)
    }

    /**
     * The manner in which material reacts to lighting.
     *
     * Generated from Godot docs: CanvasItemMaterial.get_light_mode
     */
    fun getLightMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getLightModeBind, handle)
    }

    /**
     * If `true`, enable spritesheet-based animation features when assigned to `GPUParticles2D` and
     * `CPUParticles2D` nodes. The `ParticleProcessMaterial.anim_speed_max` or
     * `CPUParticles2D.anim_speed_max` should also be set to a positive value for the animation to
     * play. This property (and other `particles_anim_*` properties that depend on it) has no effect on
     * other types of nodes.
     *
     * Generated from Godot docs: CanvasItemMaterial.set_particles_animation
     */
    fun setParticlesAnimation(particlesAnim: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setParticlesAnimationBind, handle, particlesAnim)
    }

    /**
     * If `true`, enable spritesheet-based animation features when assigned to `GPUParticles2D` and
     * `CPUParticles2D` nodes. The `ParticleProcessMaterial.anim_speed_max` or
     * `CPUParticles2D.anim_speed_max` should also be set to a positive value for the animation to
     * play. This property (and other `particles_anim_*` properties that depend on it) has no effect on
     * other types of nodes.
     *
     * Generated from Godot docs: CanvasItemMaterial.get_particles_animation
     */
    fun getParticlesAnimation(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getParticlesAnimationBind, handle)
    }

    /**
     * The number of columns in the spritesheet assigned as `Texture2D` for a `GPUParticles2D` or
     * `CPUParticles2D`. Note: This property is only used and visible in the editor if
     * `particles_animation` is `true`.
     *
     * Generated from Godot docs: CanvasItemMaterial.set_particles_anim_h_frames
     */
    fun setParticlesAnimHFrames(frames: Int) {
        ObjectCalls.ptrcallWithIntArg(setParticlesAnimHFramesBind, handle, frames)
    }

    /**
     * The number of columns in the spritesheet assigned as `Texture2D` for a `GPUParticles2D` or
     * `CPUParticles2D`. Note: This property is only used and visible in the editor if
     * `particles_animation` is `true`.
     *
     * Generated from Godot docs: CanvasItemMaterial.get_particles_anim_h_frames
     */
    fun getParticlesAnimHFrames(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getParticlesAnimHFramesBind, handle)
    }

    /**
     * The number of rows in the spritesheet assigned as `Texture2D` for a `GPUParticles2D` or
     * `CPUParticles2D`. Note: This property is only used and visible in the editor if
     * `particles_animation` is `true`.
     *
     * Generated from Godot docs: CanvasItemMaterial.set_particles_anim_v_frames
     */
    fun setParticlesAnimVFrames(frames: Int) {
        ObjectCalls.ptrcallWithIntArg(setParticlesAnimVFramesBind, handle, frames)
    }

    /**
     * The number of rows in the spritesheet assigned as `Texture2D` for a `GPUParticles2D` or
     * `CPUParticles2D`. Note: This property is only used and visible in the editor if
     * `particles_animation` is `true`.
     *
     * Generated from Godot docs: CanvasItemMaterial.get_particles_anim_v_frames
     */
    fun getParticlesAnimVFrames(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getParticlesAnimVFramesBind, handle)
    }

    /**
     * If `true`, the particles animation will loop. Note: This property is only used and visible in
     * the editor if `particles_animation` is `true`.
     *
     * Generated from Godot docs: CanvasItemMaterial.set_particles_anim_loop
     */
    fun setParticlesAnimLoop(loop: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setParticlesAnimLoopBind, handle, loop)
    }

    /**
     * If `true`, the particles animation will loop. Note: This property is only used and visible in
     * the editor if `particles_animation` is `true`.
     *
     * Generated from Godot docs: CanvasItemMaterial.get_particles_anim_loop
     */
    fun getParticlesAnimLoop(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getParticlesAnimLoopBind, handle)
    }

    companion object {
        const val BLEND_MODE_MIX: Long = 0L
        const val BLEND_MODE_ADD: Long = 1L
        const val BLEND_MODE_SUB: Long = 2L
        const val BLEND_MODE_MUL: Long = 3L
        const val BLEND_MODE_PREMULT_ALPHA: Long = 4L
        const val LIGHT_MODE_NORMAL: Long = 0L
        const val LIGHT_MODE_UNSHADED: Long = 1L
        const val LIGHT_MODE_LIGHT_ONLY: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): CanvasItemMaterial? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CanvasItemMaterial? =
            if (handle.address() == 0L) null else CanvasItemMaterial(handle)

        private const val SET_BLEND_MODE_HASH = 1786054936L
        private val setBlendModeBind by lazy {
            ObjectCalls.getMethodBind("CanvasItemMaterial", "set_blend_mode", SET_BLEND_MODE_HASH)
        }

        private const val GET_BLEND_MODE_HASH = 3318684035L
        private val getBlendModeBind by lazy {
            ObjectCalls.getMethodBind("CanvasItemMaterial", "get_blend_mode", GET_BLEND_MODE_HASH)
        }

        private const val SET_LIGHT_MODE_HASH = 628074070L
        private val setLightModeBind by lazy {
            ObjectCalls.getMethodBind("CanvasItemMaterial", "set_light_mode", SET_LIGHT_MODE_HASH)
        }

        private const val GET_LIGHT_MODE_HASH = 3863292382L
        private val getLightModeBind by lazy {
            ObjectCalls.getMethodBind("CanvasItemMaterial", "get_light_mode", GET_LIGHT_MODE_HASH)
        }

        private const val SET_PARTICLES_ANIMATION_HASH = 2586408642L
        private val setParticlesAnimationBind by lazy {
            ObjectCalls.getMethodBind("CanvasItemMaterial", "set_particles_animation", SET_PARTICLES_ANIMATION_HASH)
        }

        private const val GET_PARTICLES_ANIMATION_HASH = 36873697L
        private val getParticlesAnimationBind by lazy {
            ObjectCalls.getMethodBind("CanvasItemMaterial", "get_particles_animation", GET_PARTICLES_ANIMATION_HASH)
        }

        private const val SET_PARTICLES_ANIM_H_FRAMES_HASH = 1286410249L
        private val setParticlesAnimHFramesBind by lazy {
            ObjectCalls.getMethodBind("CanvasItemMaterial", "set_particles_anim_h_frames", SET_PARTICLES_ANIM_H_FRAMES_HASH)
        }

        private const val GET_PARTICLES_ANIM_H_FRAMES_HASH = 3905245786L
        private val getParticlesAnimHFramesBind by lazy {
            ObjectCalls.getMethodBind("CanvasItemMaterial", "get_particles_anim_h_frames", GET_PARTICLES_ANIM_H_FRAMES_HASH)
        }

        private const val SET_PARTICLES_ANIM_V_FRAMES_HASH = 1286410249L
        private val setParticlesAnimVFramesBind by lazy {
            ObjectCalls.getMethodBind("CanvasItemMaterial", "set_particles_anim_v_frames", SET_PARTICLES_ANIM_V_FRAMES_HASH)
        }

        private const val GET_PARTICLES_ANIM_V_FRAMES_HASH = 3905245786L
        private val getParticlesAnimVFramesBind by lazy {
            ObjectCalls.getMethodBind("CanvasItemMaterial", "get_particles_anim_v_frames", GET_PARTICLES_ANIM_V_FRAMES_HASH)
        }

        private const val SET_PARTICLES_ANIM_LOOP_HASH = 2586408642L
        private val setParticlesAnimLoopBind by lazy {
            ObjectCalls.getMethodBind("CanvasItemMaterial", "set_particles_anim_loop", SET_PARTICLES_ANIM_LOOP_HASH)
        }

        private const val GET_PARTICLES_ANIM_LOOP_HASH = 36873697L
        private val getParticlesAnimLoopBind by lazy {
            ObjectCalls.getMethodBind("CanvasItemMaterial", "get_particles_anim_loop", GET_PARTICLES_ANIM_LOOP_HASH)
        }
    }
}
