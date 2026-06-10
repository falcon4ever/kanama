package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: CollisionShape3D
 */
class CollisionShape3D(handle: MemorySegment) : Node3D(handle) {
    var disabled: Boolean
        @JvmName("disabledProperty")
        get() = isDisabled()
        @JvmName("setDisabledProperty")
        set(value) = setDisabled(value)

    var debugFill: Boolean
        @JvmName("debugFillProperty")
        get() = getEnableDebugFill()
        @JvmName("setDebugFillProperty")
        set(value) = setEnableDebugFill(value)

    fun setDisabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisabledBind, handle, enable)
    }

    fun isDisabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDisabledBind, handle)
    }

    fun makeConvexFromSiblings() {
        ObjectCalls.ptrcallNoArgs(makeConvexFromSiblingsBind, handle)
    }

    fun setEnableDebugFill(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableDebugFillBind, handle, enable)
    }

    fun getEnableDebugFill(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableDebugFillBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): CollisionShape3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CollisionShape3D? =
            if (handle.address() == 0L) null else CollisionShape3D(handle)

        private const val SET_DISABLED_HASH = 2586408642L
        private val setDisabledBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape3D", "set_disabled", SET_DISABLED_HASH)
        }

        private const val IS_DISABLED_HASH = 36873697L
        private val isDisabledBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape3D", "is_disabled", IS_DISABLED_HASH)
        }

        private const val MAKE_CONVEX_FROM_SIBLINGS_HASH = 3218959716L
        private val makeConvexFromSiblingsBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape3D", "make_convex_from_siblings", MAKE_CONVEX_FROM_SIBLINGS_HASH)
        }

        private const val SET_ENABLE_DEBUG_FILL_HASH = 2586408642L
        private val setEnableDebugFillBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape3D", "set_enable_debug_fill", SET_ENABLE_DEBUG_FILL_HASH)
        }

        private const val GET_ENABLE_DEBUG_FILL_HASH = 36873697L
        private val getEnableDebugFillBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape3D", "get_enable_debug_fill", GET_ENABLE_DEBUG_FILL_HASH)
        }
    }
}
