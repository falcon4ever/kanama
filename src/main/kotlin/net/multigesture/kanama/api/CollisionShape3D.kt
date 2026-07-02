package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * A node that provides a `Shape3D` to a `CollisionObject3D` parent.
 *
 * Generated from Godot docs: CollisionShape3D
 */
class CollisionShape3D(handle: MemorySegment) : Node3D(handle) {
    var shape: Shape3D?
        @JvmName("shapeProperty")
        get() = getShape()
        @JvmName("setShapeProperty")
        set(value) = setShape(value)

    var disabled: Boolean
        @JvmName("disabledProperty")
        get() = isDisabled()
        @JvmName("setDisabledProperty")
        set(value) = setDisabled(value)

    var debugColor: Color
        @JvmName("debugColorProperty")
        get() = getDebugColor()
        @JvmName("setDebugColorProperty")
        set(value) = setDebugColor(value)

    var debugFill: Boolean
        @JvmName("debugFillProperty")
        get() = getEnableDebugFill()
        @JvmName("setDebugFillProperty")
        set(value) = setEnableDebugFill(value)

    fun resourceChanged(resource: Resource?) {
        ObjectCalls.ptrcallWithObjectArgs(resourceChangedBind, handle, listOf(resource?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun setShape(shape: Shape3D?) {
        ObjectCalls.ptrcallWithObjectArgs(setShapeBind, handle, listOf(shape?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getShape(): Shape3D? {
        return Shape3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getShapeBind, handle))
    }

    fun setDisabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisabledBind, handle, enable)
    }

    fun isDisabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDisabledBind, handle)
    }

    fun makeConvexFromSiblings() {
        ObjectCalls.ptrcallNoArgs(makeConvexFromSiblingsBind, handle)
    }

    fun setDebugColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setDebugColorBind, handle, color)
    }

    fun getDebugColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getDebugColorBind, handle)
    }

    fun setEnableDebugFill(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableDebugFillBind, handle, enable)
    }

    fun getEnableDebugFill(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEnableDebugFillBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CollisionShape3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CollisionShape3D? =
            if (handle.address() == 0L) null else CollisionShape3D(handle)

        private const val RESOURCE_CHANGED_HASH = 968641751L
        private val resourceChangedBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape3D", "resource_changed", RESOURCE_CHANGED_HASH)
        }

        private const val SET_SHAPE_HASH = 1549710052L
        private val setShapeBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape3D", "set_shape", SET_SHAPE_HASH)
        }

        private const val GET_SHAPE_HASH = 3214262478L
        private val getShapeBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape3D", "get_shape", GET_SHAPE_HASH)
        }

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

        private const val SET_DEBUG_COLOR_HASH = 2920490490L
        private val setDebugColorBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape3D", "set_debug_color", SET_DEBUG_COLOR_HASH)
        }

        private const val GET_DEBUG_COLOR_HASH = 3444240500L
        private val getDebugColorBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape3D", "get_debug_color", GET_DEBUG_COLOR_HASH)
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
