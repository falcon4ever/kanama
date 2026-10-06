package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color

/**
 * A node that provides a `Shape3D` to a `CollisionObject3D` parent.
 *
 * Generated from Godot docs: CollisionShape3D
 */
class CollisionShape3D(handle: GodotHandle) : Node3D(handle) {
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

    /**
     * This method does nothing.
     *
     * Generated from Godot docs: CollisionShape3D.resource_changed
     */
    fun resourceChanged(resource: Resource?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.resourceChangedBind, segment, listOf(resource?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The actual shape owned by this collision shape.
     *
     * Generated from Godot docs: CollisionShape3D.set_shape
     */
    fun setShape(shape: Shape3D?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setShapeBind, segment, listOf(shape?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The actual shape owned by this collision shape.
     *
     * Generated from Godot docs: CollisionShape3D.get_shape
     */
    fun getShape(): Shape3D? {
        return Shape3D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getShapeBind, segment))
    }

    /**
     * A disabled collision shape has no effect in the world. This property should be changed with
     * `Object.set_deferred`.
     *
     * Generated from Godot docs: CollisionShape3D.set_disabled
     */
    fun setDisabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDisabledBind, segment, enable)
    }

    /**
     * A disabled collision shape has no effect in the world. This property should be changed with
     * `Object.set_deferred`.
     *
     * Generated from Godot docs: CollisionShape3D.is_disabled
     */
    fun isDisabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDisabledBind, segment)
    }

    /**
     * Sets the collision shape's shape to the addition of all its convexed `MeshInstance3D` siblings
     * geometry.
     *
     * Generated from Godot docs: CollisionShape3D.make_convex_from_siblings
     */
    fun makeConvexFromSiblings() {
        ObjectCalls.ptrcallNoArgs(Binds.makeConvexFromSiblingsBind, segment)
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
        ObjectCalls.ptrcallWithColorArg(Binds.setDebugColorBind, segment, color)
    }

    /**
     * The collision shape color that is displayed in the editor, or in the running project if Debug >
     * Visible Collision Shapes is checked at the top of the editor. Note: The default value is
     * `ProjectSettings.debug/shapes/collision/shape_color`. The `Color(0, 0, 0, 0)` value documented
     * here is a placeholder, and not the actual default debug color.
     *
     * Generated from Godot docs: CollisionShape3D.get_debug_color
     */
    fun getDebugColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getDebugColorBind, segment)
    }

    /**
     * If `true`, when the shape is displayed, it will show a solid fill color in addition to its
     * wireframe.
     *
     * Generated from Godot docs: CollisionShape3D.set_enable_debug_fill
     */
    fun setEnableDebugFill(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnableDebugFillBind, segment, enable)
    }

    /**
     * If `true`, when the shape is displayed, it will show a solid fill color in addition to its
     * wireframe.
     *
     * Generated from Godot docs: CollisionShape3D.get_enable_debug_fill
     */
    fun getEnableDebugFill(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getEnableDebugFillBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CollisionShape3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CollisionShape3D? =
            if (handle.address() == 0L) null else CollisionShape3D(GodotHandle(handle))
    }

    private object Binds {
        private const val RESOURCE_CHANGED_HASH = 968641751L
        @JvmField
        val resourceChangedBind =
            ObjectCalls.getMethodBind("CollisionShape3D", "resource_changed", RESOURCE_CHANGED_HASH)

        private const val SET_SHAPE_HASH = 1549710052L
        @JvmField
        val setShapeBind =
            ObjectCalls.getMethodBind("CollisionShape3D", "set_shape", SET_SHAPE_HASH)

        private const val GET_SHAPE_HASH = 3214262478L
        @JvmField
        val getShapeBind =
            ObjectCalls.getMethodBind("CollisionShape3D", "get_shape", GET_SHAPE_HASH)

        private const val SET_DISABLED_HASH = 2586408642L
        @JvmField
        val setDisabledBind =
            ObjectCalls.getMethodBind("CollisionShape3D", "set_disabled", SET_DISABLED_HASH)

        private const val IS_DISABLED_HASH = 36873697L
        @JvmField
        val isDisabledBind =
            ObjectCalls.getMethodBind("CollisionShape3D", "is_disabled", IS_DISABLED_HASH)

        private const val MAKE_CONVEX_FROM_SIBLINGS_HASH = 3218959716L
        @JvmField
        val makeConvexFromSiblingsBind =
            ObjectCalls.getMethodBind("CollisionShape3D", "make_convex_from_siblings", MAKE_CONVEX_FROM_SIBLINGS_HASH)

        private const val SET_DEBUG_COLOR_HASH = 2920490490L
        @JvmField
        val setDebugColorBind =
            ObjectCalls.getMethodBind("CollisionShape3D", "set_debug_color", SET_DEBUG_COLOR_HASH)

        private const val GET_DEBUG_COLOR_HASH = 3444240500L
        @JvmField
        val getDebugColorBind =
            ObjectCalls.getMethodBind("CollisionShape3D", "get_debug_color", GET_DEBUG_COLOR_HASH)

        private const val SET_ENABLE_DEBUG_FILL_HASH = 2586408642L
        @JvmField
        val setEnableDebugFillBind =
            ObjectCalls.getMethodBind("CollisionShape3D", "set_enable_debug_fill", SET_ENABLE_DEBUG_FILL_HASH)

        private const val GET_ENABLE_DEBUG_FILL_HASH = 36873697L
        @JvmField
        val getEnableDebugFillBind =
            ObjectCalls.getMethodBind("CollisionShape3D", "get_enable_debug_fill", GET_ENABLE_DEBUG_FILL_HASH)
    }
}
