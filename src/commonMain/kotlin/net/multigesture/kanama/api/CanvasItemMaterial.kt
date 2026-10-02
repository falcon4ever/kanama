package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A material for `CanvasItem`s.
 *
 * Generated from Godot docs: CanvasItemMaterial
 */
class CanvasItemMaterial(handle: GodotHandle) : Material(handle) {
    var blendMode: CanvasItemMaterial.BlendMode
        @JvmName("blendModeProperty")
        get() = getBlendMode()
        @JvmName("setBlendModeProperty")
        set(value) = setBlendMode(value)

    var lightMode: CanvasItemMaterial.LightMode
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
    fun setBlendMode(blendMode: CanvasItemMaterial.BlendMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setBlendModeBind, segment, blendMode.value)
    }

    /**
     * The manner in which a material's rendering is applied to underlying textures.
     *
     * Generated from Godot docs: CanvasItemMaterial.get_blend_mode
     */
    fun getBlendMode(): CanvasItemMaterial.BlendMode {
        checkOpen()
        return CanvasItemMaterial.BlendMode(ObjectCalls.ptrcallNoArgsRetLong(getBlendModeBind, segment))
    }

    /**
     * The manner in which material reacts to lighting.
     *
     * Generated from Godot docs: CanvasItemMaterial.set_light_mode
     */
    fun setLightMode(lightMode: CanvasItemMaterial.LightMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setLightModeBind, segment, lightMode.value)
    }

    /**
     * The manner in which material reacts to lighting.
     *
     * Generated from Godot docs: CanvasItemMaterial.get_light_mode
     */
    fun getLightMode(): CanvasItemMaterial.LightMode {
        checkOpen()
        return CanvasItemMaterial.LightMode(ObjectCalls.ptrcallNoArgsRetLong(getLightModeBind, segment))
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
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setParticlesAnimationBind, segment, particlesAnim)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getParticlesAnimationBind, segment)
    }

    /**
     * The number of columns in the spritesheet assigned as `Texture2D` for a `GPUParticles2D` or
     * `CPUParticles2D`. Note: This property is only used and visible in the editor if
     * `particles_animation` is `true`.
     *
     * Generated from Godot docs: CanvasItemMaterial.set_particles_anim_h_frames
     */
    fun setParticlesAnimHFrames(frames: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setParticlesAnimHFramesBind, segment, frames)
    }

    /**
     * The number of columns in the spritesheet assigned as `Texture2D` for a `GPUParticles2D` or
     * `CPUParticles2D`. Note: This property is only used and visible in the editor if
     * `particles_animation` is `true`.
     *
     * Generated from Godot docs: CanvasItemMaterial.get_particles_anim_h_frames
     */
    fun getParticlesAnimHFrames(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getParticlesAnimHFramesBind, segment)
    }

    /**
     * The number of rows in the spritesheet assigned as `Texture2D` for a `GPUParticles2D` or
     * `CPUParticles2D`. Note: This property is only used and visible in the editor if
     * `particles_animation` is `true`.
     *
     * Generated from Godot docs: CanvasItemMaterial.set_particles_anim_v_frames
     */
    fun setParticlesAnimVFrames(frames: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setParticlesAnimVFramesBind, segment, frames)
    }

    /**
     * The number of rows in the spritesheet assigned as `Texture2D` for a `GPUParticles2D` or
     * `CPUParticles2D`. Note: This property is only used and visible in the editor if
     * `particles_animation` is `true`.
     *
     * Generated from Godot docs: CanvasItemMaterial.get_particles_anim_v_frames
     */
    fun getParticlesAnimVFrames(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getParticlesAnimVFramesBind, segment)
    }

    /**
     * If `true`, the particles animation will loop. Note: This property is only used and visible in
     * the editor if `particles_animation` is `true`.
     *
     * Generated from Godot docs: CanvasItemMaterial.set_particles_anim_loop
     */
    fun setParticlesAnimLoop(loop: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setParticlesAnimLoopBind, segment, loop)
    }

    /**
     * If `true`, the particles animation will loop. Note: This property is only used and visible in
     * the editor if `particles_animation` is `true`.
     *
     * Generated from Godot docs: CanvasItemMaterial.get_particles_anim_loop
     */
    fun getParticlesAnimLoop(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getParticlesAnimLoopBind, segment)
    }

    @JvmInline
    value class BlendMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Mix blending mode. Colors are assumed to be independent of the alpha (opacity) value.
             *
             * Generated from Godot docs: CanvasItemMaterial.BLEND_MODE_MIX
             */
            val MIX: BlendMode get() = BlendMode(0L)
            /**
             * Additive blending mode.
             *
             * Generated from Godot docs: CanvasItemMaterial.BLEND_MODE_ADD
             */
            val ADD: BlendMode get() = BlendMode(1L)
            /**
             * Subtractive blending mode.
             *
             * Generated from Godot docs: CanvasItemMaterial.BLEND_MODE_SUB
             */
            val SUB: BlendMode get() = BlendMode(2L)
            /**
             * Multiplicative blending mode.
             *
             * Generated from Godot docs: CanvasItemMaterial.BLEND_MODE_MUL
             */
            val MUL: BlendMode get() = BlendMode(3L)
            /**
             * Mix blending mode. Colors are assumed to be premultiplied by the alpha (opacity) value.
             *
             * Generated from Godot docs: CanvasItemMaterial.BLEND_MODE_PREMULT_ALPHA
             */
            val PREMULT_ALPHA: BlendMode get() = BlendMode(4L)
        }
    }

    @JvmInline
    value class LightMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Render the material using both light and non-light sensitive material properties.
             *
             * Generated from Godot docs: CanvasItemMaterial.LIGHT_MODE_NORMAL
             */
            val NORMAL: LightMode get() = LightMode(0L)
            /**
             * Render the material as if there were no light.
             *
             * Generated from Godot docs: CanvasItemMaterial.LIGHT_MODE_UNSHADED
             */
            val UNSHADED: LightMode get() = LightMode(1L)
            /**
             * Render the material as if there were only light.
             *
             * Generated from Godot docs: CanvasItemMaterial.LIGHT_MODE_LIGHT_ONLY
             */
            val LIGHT_ONLY: LightMode get() = LightMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CanvasItemMaterial? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CanvasItemMaterial? =
            if (handle.address() == 0L) null else CanvasItemMaterial(GodotHandle(handle))

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
