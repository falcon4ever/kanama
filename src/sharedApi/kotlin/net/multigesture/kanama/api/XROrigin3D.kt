package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * The origin point in AR/VR.
 *
 * Generated from Godot docs: XROrigin3D
 */
class XROrigin3D(handle: GodotHandle) : Node3D(handle) {
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

    /**
     * The scale of the game world compared to the real world. This is the same as
     * `XRServer.world_scale`. By default, most AR/VR platforms assume that 1 game unit corresponds to
     * 1 real world meter.
     *
     * Generated from Godot docs: XROrigin3D.set_world_scale
     */
    fun setWorldScale(worldScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setWorldScaleBind, segment, worldScale)
    }

    /**
     * The scale of the game world compared to the real world. This is the same as
     * `XRServer.world_scale`. By default, most AR/VR platforms assume that 1 game unit corresponds to
     * 1 real world meter.
     *
     * Generated from Godot docs: XROrigin3D.get_world_scale
     */
    fun getWorldScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getWorldScaleBind, segment)
    }

    /**
     * If `true`, this origin node is currently being used by the `XRServer`. Only one origin point can
     * be used at a time.
     *
     * Generated from Godot docs: XROrigin3D.set_current
     */
    fun setCurrent(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCurrentBind, segment, enabled)
    }

    /**
     * If `true`, this origin node is currently being used by the `XRServer`. Only one origin point can
     * be used at a time.
     *
     * Generated from Godot docs: XROrigin3D.is_current
     */
    fun isCurrent(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCurrentBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): XROrigin3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): XROrigin3D? =
            if (handle.address() == 0L) null else XROrigin3D(GodotHandle(handle))

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
