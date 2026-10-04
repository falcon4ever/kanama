package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A material that processes blit calls to a DrawableTexture.
 *
 * Generated from Godot docs: BlitMaterial
 */
class BlitMaterial(handle: GodotHandle) : Material(handle) {
    var blendMode: BlitMaterial.BlendMode
        @JvmName("blendModeProperty")
        get() = getBlendMode()
        @JvmName("setBlendModeProperty")
        set(value) = setBlendMode(value)

    /**
     * The manner in which the newly blitted texture is blended with the original DrawableTexture.
     *
     * Generated from Godot docs: BlitMaterial.set_blend_mode
     */
    fun setBlendMode(blendMode: BlitMaterial.BlendMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setBlendModeBind, segment, blendMode.value)
    }

    /**
     * The manner in which the newly blitted texture is blended with the original DrawableTexture.
     *
     * Generated from Godot docs: BlitMaterial.get_blend_mode
     */
    fun getBlendMode(): BlitMaterial.BlendMode {
        checkOpen()
        return BlitMaterial.BlendMode(ObjectCalls.ptrcallNoArgsRetLong(getBlendModeBind, segment))
    }

    /**
     * Godot's `BlitMaterial.BlendMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`BlitMaterial.BlendMode.<NAME>`).
     *
     * Generated from Godot docs: BlitMaterial.BlendMode
     */
    @JvmInline
    value class BlendMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Mix blending mode. Colors are assumed to be independent of the alpha (opacity) value.
             *
             * Generated from Godot docs: BlitMaterial.BLEND_MODE_MIX
             */
            val MIX: BlendMode get() = BlendMode(0L)
            /**
             * Additive blending mode.
             *
             * Generated from Godot docs: BlitMaterial.BLEND_MODE_ADD
             */
            val ADD: BlendMode get() = BlendMode(1L)
            /**
             * Subtractive blending mode.
             *
             * Generated from Godot docs: BlitMaterial.BLEND_MODE_SUB
             */
            val SUB: BlendMode get() = BlendMode(2L)
            /**
             * Multiplicative blending mode.
             *
             * Generated from Godot docs: BlitMaterial.BLEND_MODE_MUL
             */
            val MUL: BlendMode get() = BlendMode(3L)
            /**
             * No blending mode, direct color copy.
             *
             * Generated from Godot docs: BlitMaterial.BLEND_MODE_DISABLED
             */
            val DISABLED: BlendMode get() = BlendMode(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): BlitMaterial? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): BlitMaterial? =
            if (handle.address() == 0L) null else RefCounted.owned(BlitMaterial(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): BlitMaterial? =
            if (handle.address() == 0L) null else BlitMaterial(GodotHandle(handle))

        private const val SET_BLEND_MODE_HASH = 80206916L
        private val setBlendModeBind by lazy {
            ObjectCalls.getMethodBind("BlitMaterial", "set_blend_mode", SET_BLEND_MODE_HASH)
        }

        private const val GET_BLEND_MODE_HASH = 4234246416L
        private val getBlendModeBind by lazy {
            ObjectCalls.getMethodBind("BlitMaterial", "get_blend_mode", GET_BLEND_MODE_HASH)
        }
    }
}
