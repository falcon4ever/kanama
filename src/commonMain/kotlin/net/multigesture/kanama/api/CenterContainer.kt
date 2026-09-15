package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * A container that keeps child controls in its center.
 *
 * Generated from Godot docs: CenterContainer
 */
class CenterContainer(handle: GodotHandle) : Container(handle) {
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
        ObjectCalls.ptrcallWithBoolArg(setUseTopLeftBind, segment, enable)
    }

    /**
     * If `true`, centers children relative to the `CenterContainer`'s top left corner.
     *
     * Generated from Godot docs: CenterContainer.is_using_top_left
     */
    fun isUsingTopLeft(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingTopLeftBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CenterContainer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CenterContainer? =
            if (handle.address() == 0L) null else CenterContainer(GodotHandle(handle))

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
