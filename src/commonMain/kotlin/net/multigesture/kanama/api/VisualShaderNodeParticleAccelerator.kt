package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A visual shader node that accelerates particles.
 *
 * Generated from Godot docs: VisualShaderNodeParticleAccelerator
 */
class VisualShaderNodeParticleAccelerator(handle: GodotHandle) : VisualShaderNode(handle) {
    var mode: VisualShaderNodeParticleAccelerator.Mode
        @JvmName("modeProperty")
        get() = getMode()
        @JvmName("setModeProperty")
        set(value) = setMode(value)

    /**
     * Defines in what manner the particles will be accelerated.
     *
     * Generated from Godot docs: VisualShaderNodeParticleAccelerator.set_mode
     */
    fun setMode(mode: VisualShaderNodeParticleAccelerator.Mode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setModeBind, segment, mode.value)
    }

    /**
     * Defines in what manner the particles will be accelerated.
     *
     * Generated from Godot docs: VisualShaderNodeParticleAccelerator.get_mode
     */
    fun getMode(): VisualShaderNodeParticleAccelerator.Mode {
        checkOpen()
        return VisualShaderNodeParticleAccelerator.Mode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getModeBind, segment))
    }

    /**
     * Godot's `VisualShaderNodeParticleAccelerator.Mode` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`VisualShaderNodeParticleAccelerator.Mode.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeParticleAccelerator.Mode
     */
    @JvmInline
    value class Mode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The particles will be accelerated based on their velocity.
             *
             * Generated from Godot docs: VisualShaderNodeParticleAccelerator.MODE_LINEAR
             */
            val LINEAR: Mode get() = Mode(0L)
            /**
             * The particles will be accelerated towards or away from the center.
             *
             * Generated from Godot docs: VisualShaderNodeParticleAccelerator.MODE_RADIAL
             */
            val RADIAL: Mode get() = Mode(1L)
            /**
             * The particles will be accelerated tangentially to the radius vector from center to their
             * position.
             *
             * Generated from Godot docs: VisualShaderNodeParticleAccelerator.MODE_TANGENTIAL
             */
            val TANGENTIAL: Mode get() = Mode(2L)
            /**
             * Represents the size of the `Mode` enum.
             *
             * Generated from Godot docs: VisualShaderNodeParticleAccelerator.MODE_MAX
             */
            val MAX: Mode get() = Mode(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeParticleAccelerator? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeParticleAccelerator? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeParticleAccelerator(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeParticleAccelerator? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleAccelerator(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_MODE_HASH = 3457585749L
        @JvmField
        val setModeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParticleAccelerator", "set_mode", SET_MODE_HASH)

        private const val GET_MODE_HASH = 2660365633L
        @JvmField
        val getModeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParticleAccelerator", "get_mode", GET_MODE_HASH)
    }
}
