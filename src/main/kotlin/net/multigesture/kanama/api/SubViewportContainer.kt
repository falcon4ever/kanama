package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A container used for displaying the contents of a `SubViewport`.
 *
 * Generated from Godot docs: SubViewportContainer
 */
class SubViewportContainer(handle: MemorySegment) : Container(handle) {
    var stretch: Boolean
        @JvmName("stretchProperty")
        get() = isStretchEnabled()
        @JvmName("setStretchProperty")
        set(value) = setStretch(value)

    var stretchShrink: Int
        @JvmName("stretchShrinkProperty")
        get() = getStretchShrink()
        @JvmName("setStretchShrinkProperty")
        set(value) = setStretchShrink(value)

    var mouseTarget: Boolean
        @JvmName("mouseTargetProperty")
        get() = isMouseTargetEnabled()
        @JvmName("setMouseTargetProperty")
        set(value) = setMouseTarget(value)

    fun setStretch(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setStretchBind, handle, enable)
    }

    fun isStretchEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isStretchEnabledBind, handle)
    }

    fun setStretchShrink(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setStretchShrinkBind, handle, amount)
    }

    fun getStretchShrink(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getStretchShrinkBind, handle)
    }

    fun setMouseTarget(amount: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMouseTargetBind, handle, amount)
    }

    fun isMouseTargetEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMouseTargetEnabledBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SubViewportContainer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SubViewportContainer? =
            if (handle.address() == 0L) null else SubViewportContainer(handle)

        private const val SET_STRETCH_HASH = 2586408642L
        private val setStretchBind by lazy {
            ObjectCalls.getMethodBind("SubViewportContainer", "set_stretch", SET_STRETCH_HASH)
        }

        private const val IS_STRETCH_ENABLED_HASH = 36873697L
        private val isStretchEnabledBind by lazy {
            ObjectCalls.getMethodBind("SubViewportContainer", "is_stretch_enabled", IS_STRETCH_ENABLED_HASH)
        }

        private const val SET_STRETCH_SHRINK_HASH = 1286410249L
        private val setStretchShrinkBind by lazy {
            ObjectCalls.getMethodBind("SubViewportContainer", "set_stretch_shrink", SET_STRETCH_SHRINK_HASH)
        }

        private const val GET_STRETCH_SHRINK_HASH = 3905245786L
        private val getStretchShrinkBind by lazy {
            ObjectCalls.getMethodBind("SubViewportContainer", "get_stretch_shrink", GET_STRETCH_SHRINK_HASH)
        }

        private const val SET_MOUSE_TARGET_HASH = 2586408642L
        private val setMouseTargetBind by lazy {
            ObjectCalls.getMethodBind("SubViewportContainer", "set_mouse_target", SET_MOUSE_TARGET_HASH)
        }

        private const val IS_MOUSE_TARGET_ENABLED_HASH = 2240911060L
        private val isMouseTargetEnabledBind by lazy {
            ObjectCalls.getMethodBind("SubViewportContainer", "is_mouse_target_enabled", IS_MOUSE_TARGET_ENABLED_HASH)
        }
    }
}
