package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A container that keeps child controls in its center.
 *
 * Generated from Godot docs: CenterContainer
 */
class CenterContainer(handle: MemorySegment) : Container(handle) {
    var useTopLeft: Boolean
        @JvmName("useTopLeftProperty")
        get() = isUsingTopLeft()
        @JvmName("setUseTopLeftProperty")
        set(value) = setUseTopLeft(value)

    /**
     * If `true`, centers children relative to the `CenterContainer`'s top left corner.
     *
     * Generated from Godot docs: CenterContainer.set_use_top_left
     */
    fun setUseTopLeft(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseTopLeftBind, handle, enable)
    }

    /**
     * If `true`, centers children relative to the `CenterContainer`'s top left corner.
     *
     * Generated from Godot docs: CenterContainer.is_using_top_left
     */
    fun isUsingTopLeft(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingTopLeftBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CenterContainer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CenterContainer? =
            if (handle.address() == 0L) null else CenterContainer(handle)

        private const val SET_USE_TOP_LEFT_HASH = 2586408642L
        private val setUseTopLeftBind by lazy {
            ObjectCalls.getMethodBind("CenterContainer", "set_use_top_left", SET_USE_TOP_LEFT_HASH)
        }

        private const val IS_USING_TOP_LEFT_HASH = 36873697L
        private val isUsingTopLeftBind by lazy {
            ObjectCalls.getMethodBind("CenterContainer", "is_using_top_left", IS_USING_TOP_LEFT_HASH)
        }
    }
}
