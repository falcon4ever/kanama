package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * The origin point in AR/VR.
 *
 * Generated from Godot docs: XROrigin3D
 */
class XROrigin3D(handle: MemorySegment) : Node3D(handle) {
    var worldScale: Double
        @JvmName("worldScaleProperty")
        get() = getWorldScale()
        @JvmName("setWorldScaleProperty")
        set(value) = setWorldScale(value)

    var current: Boolean
        @JvmName("currentProperty")
        get() = isCurrent()
        @JvmName("setCurrentProperty")
        set(value) = setCurrent(value)

    fun setWorldScale(worldScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setWorldScaleBind, handle, worldScale)
    }

    fun getWorldScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getWorldScaleBind, handle)
    }

    fun setCurrent(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCurrentBind, handle, enabled)
    }

    fun isCurrent(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCurrentBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): XROrigin3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): XROrigin3D? =
            if (handle.address() == 0L) null else XROrigin3D(handle)

        private const val SET_WORLD_SCALE_HASH = 373806689L
        private val setWorldScaleBind by lazy {
            ObjectCalls.getMethodBind("XROrigin3D", "set_world_scale", SET_WORLD_SCALE_HASH)
        }

        private const val GET_WORLD_SCALE_HASH = 1740695150L
        private val getWorldScaleBind by lazy {
            ObjectCalls.getMethodBind("XROrigin3D", "get_world_scale", GET_WORLD_SCALE_HASH)
        }

        private const val SET_CURRENT_HASH = 2586408642L
        private val setCurrentBind by lazy {
            ObjectCalls.getMethodBind("XROrigin3D", "set_current", SET_CURRENT_HASH)
        }

        private const val IS_CURRENT_HASH = 36873697L
        private val isCurrentBind by lazy {
            ObjectCalls.getMethodBind("XROrigin3D", "is_current", IS_CURRENT_HASH)
        }
    }
}
