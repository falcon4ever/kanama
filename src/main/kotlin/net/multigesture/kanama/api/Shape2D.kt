package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2

/**
 * Abstract base class for 2D shapes used for physics collision.
 *
 * Generated from Godot docs: Shape2D
 */
open class Shape2D(handle: MemorySegment) : Resource(handle) {
    var customSolverBias: Double
        @JvmName("customSolverBiasProperty")
        get() = getCustomSolverBias()
        @JvmName("setCustomSolverBiasProperty")
        set(value) = setCustomSolverBias(value)

    /**
     * The shape's custom solver bias. Defines how much bodies react to enforce contact separation when
     * this shape is involved. When set to `0`, the default value from
     * `ProjectSettings.physics/2d/solver/default_contact_bias` is used.
     *
     * Generated from Godot docs: Shape2D.set_custom_solver_bias
     */
    fun setCustomSolverBias(bias: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCustomSolverBiasBind, handle, bias)
    }

    /**
     * The shape's custom solver bias. Defines how much bodies react to enforce contact separation when
     * this shape is involved. When set to `0`, the default value from
     * `ProjectSettings.physics/2d/solver/default_contact_bias` is used.
     *
     * Generated from Godot docs: Shape2D.get_custom_solver_bias
     */
    fun getCustomSolverBias(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCustomSolverBiasBind, handle)
    }

    /**
     * Returns `true` if this shape is colliding with another. This method needs the transformation
     * matrix for this shape (`local_xform`), the shape to check collisions with (`with_shape`), and
     * the transformation matrix of that shape (`shape_xform`).
     *
     * Generated from Godot docs: Shape2D.collide
     */
    fun collide(localXform: Transform2D, withShape: Shape2D, shapeXform: Transform2D): Boolean {
        return ObjectCalls.ptrcallWithTransform2DObjectTransform2DArgsRetBool(collideBind, handle, localXform, withShape.requireOpenHandle(), shapeXform)
    }

    /**
     * Returns whether this shape would collide with another, if a given movement was applied. This
     * method needs the transformation matrix for this shape (`local_xform`), the movement to test on
     * this shape (`local_motion`), the shape to check collisions with (`with_shape`), the
     * transformation matrix of that shape (`shape_xform`), and the movement to test onto the other
     * object (`shape_motion`).
     *
     * Generated from Godot docs: Shape2D.collide_with_motion
     */
    fun collideWithMotion(localXform: Transform2D, localMotion: Vector2, withShape: Shape2D, shapeXform: Transform2D, shapeMotion: Vector2): Boolean {
        return ObjectCalls.ptrcallWithTransform2DVector2ObjectTransform2DVector2ArgsRetBool(collideWithMotionBind, handle, localXform, localMotion, withShape.requireOpenHandle(), shapeXform, shapeMotion)
    }

    /**
     * Returns a list of contact point pairs where this shape touches another. If there are no
     * collisions, the returned list is empty. Otherwise, the returned list contains contact points
     * arranged in pairs, with entries alternating between points on the boundary of this shape and
     * points on the boundary of `with_shape`. A collision pair A, B can be used to calculate the
     * collision normal with `(B - A).normalized()`, and the collision depth with `(B - A).length()`.
     * This information is typically used to separate shapes, particularly in collision solvers. This
     * method needs the transformation matrix for this shape (`local_xform`), the shape to check
     * collisions with (`with_shape`), and the transformation matrix of that shape (`shape_xform`).
     *
     * Generated from Godot docs: Shape2D.collide_and_get_contacts
     */
    fun collideAndGetContacts(localXform: Transform2D, withShape: Shape2D, shapeXform: Transform2D): List<Vector2> {
        return ObjectCalls.ptrcallWithTransform2DObjectTransform2DArgsRetPackedVector2List(collideAndGetContactsBind, handle, localXform, withShape.requireOpenHandle(), shapeXform)
    }

    /**
     * Returns a list of contact point pairs where this shape would touch another, if a given movement
     * was applied. If there would be no collisions, the returned list is empty. Otherwise, the
     * returned list contains contact points arranged in pairs, with entries alternating between points
     * on the boundary of this shape and points on the boundary of `with_shape`. A collision pair A, B
     * can be used to calculate the collision normal with `(B - A).normalized()`, and the collision
     * depth with `(B - A).length()`. This information is typically used to separate shapes,
     * particularly in collision solvers. This method needs the transformation matrix for this shape
     * (`local_xform`), the movement to test on this shape (`local_motion`), the shape to check
     * collisions with (`with_shape`), the transformation matrix of that shape (`shape_xform`), and the
     * movement to test onto the other object (`shape_motion`).
     *
     * Generated from Godot docs: Shape2D.collide_with_motion_and_get_contacts
     */
    fun collideWithMotionAndGetContacts(localXform: Transform2D, localMotion: Vector2, withShape: Shape2D, shapeXform: Transform2D, shapeMotion: Vector2): List<Vector2> {
        return ObjectCalls.ptrcallWithTransform2DVector2ObjectTransform2DVector2ArgsRetPackedVector2List(collideWithMotionAndGetContactsBind, handle, localXform, localMotion, withShape.requireOpenHandle(), shapeXform, shapeMotion)
    }

    /**
     * Draws a solid shape onto a `CanvasItem` with the `RenderingServer` API filled with the specified
     * `color`. The exact drawing method is specific for each shape and cannot be configured.
     *
     * Generated from Godot docs: Shape2D.draw
     */
    fun draw(canvasItem: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(drawBind, handle, canvasItem, color)
    }

    /**
     * Returns a `Rect2` representing the shapes boundary.
     *
     * Generated from Godot docs: Shape2D.get_rect
     */
    fun getRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getRectBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Shape2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Shape2D? =
            if (handle.address() == 0L) null else Shape2D(handle)

        private const val SET_CUSTOM_SOLVER_BIAS_HASH = 373806689L
        private val setCustomSolverBiasBind by lazy {
            ObjectCalls.getMethodBind("Shape2D", "set_custom_solver_bias", SET_CUSTOM_SOLVER_BIAS_HASH)
        }

        private const val GET_CUSTOM_SOLVER_BIAS_HASH = 1740695150L
        private val getCustomSolverBiasBind by lazy {
            ObjectCalls.getMethodBind("Shape2D", "get_custom_solver_bias", GET_CUSTOM_SOLVER_BIAS_HASH)
        }

        private const val COLLIDE_HASH = 3709843132L
        private val collideBind by lazy {
            ObjectCalls.getMethodBind("Shape2D", "collide", COLLIDE_HASH)
        }

        private const val COLLIDE_WITH_MOTION_HASH = 2869556801L
        private val collideWithMotionBind by lazy {
            ObjectCalls.getMethodBind("Shape2D", "collide_with_motion", COLLIDE_WITH_MOTION_HASH)
        }

        private const val COLLIDE_AND_GET_CONTACTS_HASH = 3056932662L
        private val collideAndGetContactsBind by lazy {
            ObjectCalls.getMethodBind("Shape2D", "collide_and_get_contacts", COLLIDE_AND_GET_CONTACTS_HASH)
        }

        private const val COLLIDE_WITH_MOTION_AND_GET_CONTACTS_HASH = 3620351573L
        private val collideWithMotionAndGetContactsBind by lazy {
            ObjectCalls.getMethodBind("Shape2D", "collide_with_motion_and_get_contacts", COLLIDE_WITH_MOTION_AND_GET_CONTACTS_HASH)
        }

        private const val DRAW_HASH = 2948539648L
        private val drawBind by lazy {
            ObjectCalls.getMethodBind("Shape2D", "draw", DRAW_HASH)
        }

        private const val GET_RECT_HASH = 1639390495L
        private val getRectBind by lazy {
            ObjectCalls.getMethodBind("Shape2D", "get_rect", GET_RECT_HASH)
        }
    }
}
