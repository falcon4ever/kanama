package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName

/**
 * A node that provides a `Shape3D` to a `CollisionObject3D` parent.
 *
 * Generated from Godot docs: CollisionShape3D
 */
class CollisionShape3D(handle: MemorySegment) : Node3D(handle) {
    var disabled: Boolean
        @JvmName("disabledProperty")
        get() = isDisabled()
        @JvmName("setDisabledProperty")
        set(value) = setDisabled(value)

    /**
     * This method does nothing.
     *
     * Generated from Godot docs: CollisionShape3D.resource_changed
     */
    fun resourceChanged(resource: Resource?) {
        ObjectCalls.ptrcallWithObjectArgs(resourceChangedBind, handle, listOf(resource?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The actual shape owned by this collision shape.
     *
     * Generated from Godot docs: CollisionShape3D.set_shape
     */
    fun setShape(shape: Shape3D?) {
        ObjectCalls.ptrcallWithObjectArgs(setShapeBind, handle, listOf(shape?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The actual shape owned by this collision shape.
     *
     * Generated from Godot docs: CollisionShape3D.get_shape
     */
    fun getShape(): Shape3D? =
        Shape3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getShapeBind, handle))

    /**
     * A disabled collision shape has no effect in the world. This property should be changed with
     * `Object.set_deferred`.
     *
     * Generated from Godot docs: CollisionShape3D.set_disabled
     */
    fun setDisabled(disabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisabledBind, handle, disabled)
    }

    /**
     * A disabled collision shape has no effect in the world. This property should be changed with
     * `Object.set_deferred`.
     *
     * Generated from Godot docs: CollisionShape3D.is_disabled
     */
    fun isDisabled(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isDisabledBind, handle)

    /**
     * Sets the collision shape's shape to the addition of all its convexed `MeshInstance3D` siblings
     * geometry.
     *
     * Generated from Godot docs: CollisionShape3D.make_convex_from_siblings
     */
    fun makeConvexFromSiblings() {
        ObjectCalls.ptrcallNoArgs(makeConvexFromSiblingsBind, handle)
    }

    /**
     * The collision shape color that is displayed in the editor, or in the running project if Debug >
     * Visible Collision Shapes is checked at the top of the editor. Note: The default value is
     * `ProjectSettings.debug/shapes/collision/shape_color`. The `Color(0, 0, 0, 0)` value documented
     * here is a placeholder, and not the actual default debug color.
     *
     * Generated from Godot docs: CollisionShape3D.set_debug_color
     */
    fun setDebugColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setDebugColorBind, handle, color)
    }

    /**
     * The collision shape color that is displayed in the editor, or in the running project if Debug >
     * Visible Collision Shapes is checked at the top of the editor. Note: The default value is
     * `ProjectSettings.debug/shapes/collision/shape_color`. The `Color(0, 0, 0, 0)` value documented
     * here is a placeholder, and not the actual default debug color.
     *
     * Generated from Godot docs: CollisionShape3D.get_debug_color
     */
    fun getDebugColor(): Color =
        ObjectCalls.ptrcallNoArgsRetColor(getDebugColorBind, handle)

    /**
     * If `true`, when the shape is displayed, it will show a solid fill color in addition to its
     * wireframe.
     *
     * Generated from Godot docs: CollisionShape3D.set_enable_debug_fill
     */
    fun setEnableDebugFill(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnableDebugFillBind, handle, enable)
    }

    /**
     * If `true`, when the shape is displayed, it will show a solid fill color in addition to its
     * wireframe.
     *
     * Generated from Godot docs: CollisionShape3D.get_enable_debug_fill
     */
    fun getEnableDebugFill(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(getEnableDebugFillBind, handle)

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CollisionShape3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CollisionShape3D? =
            if (handle.address() == 0L) null else CollisionShape3D(handle)

        private const val BOOL_VOID_HASH = 2586408642L
        private const val NOARGS_BOOL_HASH = 36873697L
        private const val SET_SHAPE_HASH = 1549710052L
        private const val GET_SHAPE_HASH = 3214262478L
        private const val COLOR_VOID_HASH = 2920490490L
        private const val NOARGS_COLOR_HASH = 3444240500L
        private const val RESOURCE_CHANGED_HASH = 968641751L
        private const val MAKE_CONVEX_FROM_SIBLINGS_HASH = 3218959716L

        private val resourceChangedBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape3D", "resource_changed", RESOURCE_CHANGED_HASH)
        }

        private val setShapeBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape3D", "set_shape", SET_SHAPE_HASH)
        }

        private val getShapeBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape3D", "get_shape", GET_SHAPE_HASH)
        }

        private val setDisabledBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape3D", "set_disabled", BOOL_VOID_HASH)
        }

        private val isDisabledBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape3D", "is_disabled", NOARGS_BOOL_HASH)
        }

        private val makeConvexFromSiblingsBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape3D", "make_convex_from_siblings", MAKE_CONVEX_FROM_SIBLINGS_HASH)
        }

        private val setDebugColorBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape3D", "set_debug_color", COLOR_VOID_HASH)
        }

        private val getDebugColorBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape3D", "get_debug_color", NOARGS_COLOR_HASH)
        }

        private val setEnableDebugFillBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape3D", "set_enable_debug_fill", BOOL_VOID_HASH)
        }

        private val getEnableDebugFillBind by lazy {
            ObjectCalls.getMethodBind("CollisionShape3D", "get_enable_debug_fill", NOARGS_BOOL_HASH)
        }
    }
}
