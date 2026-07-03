package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2

/**
 * A 2D game object, inherited by all 2D-related nodes. Has a position, rotation, scale, and skew.
 *
 * Generated from Godot docs: Node2D
 */
open class Node2D(handle: MemorySegment) : CanvasItem(handle) {
    var position: Vector2
        @JvmName("positionProperty")
        get() = getPosition()
        @JvmName("setPositionProperty")
        set(value) = setPosition(value)

    var rotation: Double
        @JvmName("rotationProperty")
        get() = getRotation()
        @JvmName("setRotationProperty")
        set(value) = setRotation(value)

    var rotationDegrees: Double
        @JvmName("rotationDegreesProperty")
        get() = getRotationDegrees()
        @JvmName("setRotationDegreesProperty")
        set(value) = setRotationDegrees(value)

    var scale: Vector2
        @JvmName("scaleProperty")
        get() = getScale()
        @JvmName("setScaleProperty")
        set(value) = setScale(value)

    var skew: Double
        @JvmName("skewProperty")
        get() = getSkew()
        @JvmName("setSkewProperty")
        set(value) = setSkew(value)

    var globalPosition: Vector2
        @JvmName("globalPositionProperty")
        get() = getGlobalPosition()
        @JvmName("setGlobalPositionProperty")
        set(value) = setGlobalPosition(value)

    var globalRotation: Double
        @JvmName("globalRotationProperty")
        get() = getGlobalRotation()
        @JvmName("setGlobalRotationProperty")
        set(value) = setGlobalRotation(value)

    var globalRotationDegrees: Double
        @JvmName("globalRotationDegreesProperty")
        get() = getGlobalRotationDegrees()
        @JvmName("setGlobalRotationDegreesProperty")
        set(value) = setGlobalRotationDegrees(value)

    var globalScale: Vector2
        @JvmName("globalScaleProperty")
        get() = getGlobalScale()
        @JvmName("setGlobalScaleProperty")
        set(value) = setGlobalScale(value)

    var globalSkew: Double
        @JvmName("globalSkewProperty")
        get() = getGlobalSkew()
        @JvmName("setGlobalSkewProperty")
        set(value) = setGlobalSkew(value)

    /**
     * Position, relative to the node's parent. See also `global_position`.
     *
     * Generated from Godot docs: Node2D.set_position
     */
    fun setPosition(position: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setPositionBind, handle, position)
    }

    /**
     * Rotation in radians, relative to the node's parent. See also `global_rotation`. Note: This
     * property is edited in the inspector in degrees. If you want to use degrees in a script, use
     * `rotation_degrees`.
     *
     * Generated from Godot docs: Node2D.set_rotation
     */
    fun setRotation(radians: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRotationBind, handle, radians)
    }

    /**
     * Helper property to access `rotation` in degrees instead of radians. See also
     * `global_rotation_degrees`.
     *
     * Generated from Godot docs: Node2D.set_rotation_degrees
     */
    fun setRotationDegrees(degrees: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRotationDegreesBind, handle, degrees)
    }

    /**
     * If set to a non-zero value, slants the node in one direction or another. This can be used for
     * pseudo-3D effects. See also `global_skew`. Note: Skew is performed on the X axis only, and
     * between rotation and scaling. Note: This property is edited in the inspector in degrees. If you
     * want to use degrees in a script, use `skew = deg_to_rad(value_in_degrees)`.
     *
     * Generated from Godot docs: Node2D.set_skew
     */
    fun setSkew(radians: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSkewBind, handle, radians)
    }

    /**
     * The node's scale, relative to the node's parent. Unscaled value: `(1, 1)`. See also
     * `global_scale`. Note: Negative X scales in 2D are not decomposable from the transformation
     * matrix. Due to the way scale is represented with transformation matrices in Godot, negative
     * scales on the X axis will be changed to negative scales on the Y axis and a rotation of 180
     * degrees when decomposed.
     *
     * Generated from Godot docs: Node2D.set_scale
     */
    fun setScale(scale: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setScaleBind, handle, scale)
    }

    /**
     * Position, relative to the node's parent. See also `global_position`.
     *
     * Generated from Godot docs: Node2D.get_position
     */
    fun getPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getPositionBind, handle)
    }

    /**
     * Rotation in radians, relative to the node's parent. See also `global_rotation`. Note: This
     * property is edited in the inspector in degrees. If you want to use degrees in a script, use
     * `rotation_degrees`.
     *
     * Generated from Godot docs: Node2D.get_rotation
     */
    fun getRotation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRotationBind, handle)
    }

    /**
     * Helper property to access `rotation` in degrees instead of radians. See also
     * `global_rotation_degrees`.
     *
     * Generated from Godot docs: Node2D.get_rotation_degrees
     */
    fun getRotationDegrees(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRotationDegreesBind, handle)
    }

    /**
     * If set to a non-zero value, slants the node in one direction or another. This can be used for
     * pseudo-3D effects. See also `global_skew`. Note: Skew is performed on the X axis only, and
     * between rotation and scaling. Note: This property is edited in the inspector in degrees. If you
     * want to use degrees in a script, use `skew = deg_to_rad(value_in_degrees)`.
     *
     * Generated from Godot docs: Node2D.get_skew
     */
    fun getSkew(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSkewBind, handle)
    }

    /**
     * The node's scale, relative to the node's parent. Unscaled value: `(1, 1)`. See also
     * `global_scale`. Note: Negative X scales in 2D are not decomposable from the transformation
     * matrix. Due to the way scale is represented with transformation matrices in Godot, negative
     * scales on the X axis will be changed to negative scales on the Y axis and a rotation of 180
     * degrees when decomposed.
     *
     * Generated from Godot docs: Node2D.get_scale
     */
    fun getScale(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getScaleBind, handle)
    }

    /**
     * Applies a rotation to the node, in radians, starting from its current rotation. This is
     * equivalent to `rotation += radians`.
     *
     * Generated from Godot docs: Node2D.rotate
     */
    fun rotate(radians: Double) {
        ObjectCalls.ptrcallWithDoubleArg(rotateBind, handle, radians)
    }

    /**
     * Applies a local translation on the node's X axis with the amount specified in `delta`. If
     * `scaled` is `false`, normalizes the movement to occur independently of the node's `scale`.
     *
     * Generated from Godot docs: Node2D.move_local_x
     */
    fun moveLocalX(delta: Double, scaled: Boolean = false) {
        ObjectCalls.ptrcallWithDoubleAndBoolArgs(moveLocalXBind, handle, delta, scaled)
    }

    /**
     * Applies a local translation on the node's Y axis with the amount specified in `delta`. If
     * `scaled` is `false`, normalizes the movement to occur independently of the node's `scale`.
     *
     * Generated from Godot docs: Node2D.move_local_y
     */
    fun moveLocalY(delta: Double, scaled: Boolean = false) {
        ObjectCalls.ptrcallWithDoubleAndBoolArgs(moveLocalYBind, handle, delta, scaled)
    }

    /**
     * Translates the node by the given `offset` in local coordinates. This is equivalent to `position
     * += offset`.
     *
     * Generated from Godot docs: Node2D.translate
     */
    fun translate(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(translateBind, handle, offset)
    }

    /**
     * Adds the `offset` vector to the node's global position.
     *
     * Generated from Godot docs: Node2D.global_translate
     */
    fun globalTranslate(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(globalTranslateBind, handle, offset)
    }

    /**
     * Multiplies the current scale by the `ratio` vector.
     *
     * Generated from Godot docs: Node2D.apply_scale
     */
    fun applyScale(ratio: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(applyScaleBind, handle, ratio)
    }

    /**
     * Global position. See also `position`.
     *
     * Generated from Godot docs: Node2D.set_global_position
     */
    fun setGlobalPosition(position: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setGlobalPositionBind, handle, position)
    }

    /**
     * Global position. See also `position`.
     *
     * Generated from Godot docs: Node2D.get_global_position
     */
    fun getGlobalPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getGlobalPositionBind, handle)
    }

    /**
     * Global rotation in radians. See also `rotation`.
     *
     * Generated from Godot docs: Node2D.set_global_rotation
     */
    fun setGlobalRotation(radians: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlobalRotationBind, handle, radians)
    }

    /**
     * Helper property to access `global_rotation` in degrees instead of radians. See also
     * `rotation_degrees`.
     *
     * Generated from Godot docs: Node2D.set_global_rotation_degrees
     */
    fun setGlobalRotationDegrees(degrees: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlobalRotationDegreesBind, handle, degrees)
    }

    /**
     * Global rotation in radians. See also `rotation`.
     *
     * Generated from Godot docs: Node2D.get_global_rotation
     */
    fun getGlobalRotation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlobalRotationBind, handle)
    }

    /**
     * Helper property to access `global_rotation` in degrees instead of radians. See also
     * `rotation_degrees`.
     *
     * Generated from Godot docs: Node2D.get_global_rotation_degrees
     */
    fun getGlobalRotationDegrees(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlobalRotationDegreesBind, handle)
    }

    /**
     * Global skew in radians. See also `skew`.
     *
     * Generated from Godot docs: Node2D.set_global_skew
     */
    fun setGlobalSkew(radians: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGlobalSkewBind, handle, radians)
    }

    /**
     * Global skew in radians. See also `skew`.
     *
     * Generated from Godot docs: Node2D.get_global_skew
     */
    fun getGlobalSkew(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGlobalSkewBind, handle)
    }

    /**
     * Global scale. See also `scale`.
     *
     * Generated from Godot docs: Node2D.set_global_scale
     */
    fun setGlobalScale(scale: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setGlobalScaleBind, handle, scale)
    }

    /**
     * Global scale. See also `scale`.
     *
     * Generated from Godot docs: Node2D.get_global_scale
     */
    fun getGlobalScale(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getGlobalScaleBind, handle)
    }

    /**
     * The node's `Transform2D`, relative to the node's parent. See also `global_transform`.
     *
     * Generated from Godot docs: Node2D.set_transform
     */
    fun setTransform(xform: Transform2D) {
        ObjectCalls.ptrcallWithTransform2DArg(setTransformBind, handle, xform)
    }

    /**
     * Global `Transform2D`. See also `transform`.
     *
     * Generated from Godot docs: Node2D.set_global_transform
     */
    fun setGlobalTransform(xform: Transform2D) {
        ObjectCalls.ptrcallWithTransform2DArg(setGlobalTransformBind, handle, xform)
    }

    /**
     * Rotates the node so that its local +X axis points towards the `point`, which is expected to use
     * global coordinates. This method is a combination of both `rotate` and `get_angle_to`. `point`
     * should not be the same as the node's position, otherwise the node always looks to the right.
     *
     * Generated from Godot docs: Node2D.look_at
     */
    fun lookAt(point: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(lookAtBind, handle, point)
    }

    /**
     * Returns the angle between the node and the `point` in radians. See also `look_at`. Illustration
     * of the returned angle.
     * (https://raw.githubusercontent.com/godotengine/godot-docs/master/img/node2d_get_angle_to.png)
     *
     * Generated from Godot docs: Node2D.get_angle_to
     */
    fun getAngleTo(point: Vector2): Double {
        return ObjectCalls.ptrcallWithVector2ArgRetDouble(getAngleToBind, handle, point)
    }

    /**
     * Transforms the provided global position into a position in local coordinate space. The output
     * will be local relative to the `Node2D` it is called on. e.g. It is appropriate for determining
     * the positions of child nodes, but it is not appropriate for determining its own position
     * relative to its parent.
     *
     * Generated from Godot docs: Node2D.to_local
     */
    fun toLocal(globalPoint: Vector2): Vector2 {
        return ObjectCalls.ptrcallWithVector2ArgRetVector2(toLocalBind, handle, globalPoint)
    }

    /**
     * Transforms the provided local position into a position in global coordinate space. The input is
     * expected to be local relative to the `Node2D` it is called on. e.g. Applying this method to the
     * positions of child nodes will correctly transform their positions into the global coordinate
     * space, but applying it to a node's own position will give an incorrect result, as it will
     * incorporate the node's own transformation into its global position.
     *
     * Generated from Godot docs: Node2D.to_global
     */
    fun toGlobal(localPoint: Vector2): Vector2 {
        return ObjectCalls.ptrcallWithVector2ArgRetVector2(toGlobalBind, handle, localPoint)
    }

    /**
     * Returns the `Transform2D` relative to this node's parent.
     *
     * Generated from Godot docs: Node2D.get_relative_transform_to_parent
     */
    fun getRelativeTransformToParent(parent: Node): Transform2D {
        return ObjectCalls.ptrcallWithObjectArgRetTransform2D(getRelativeTransformToParentBind, handle, parent.handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Node2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Node2D? =
            if (handle.address() == 0L) null else Node2D(handle)

        private const val SET_POSITION_HASH = 743155724L
        private val setPositionBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_position", SET_POSITION_HASH)
        }

        private const val SET_ROTATION_HASH = 373806689L
        private val setRotationBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_rotation", SET_ROTATION_HASH)
        }

        private const val SET_ROTATION_DEGREES_HASH = 373806689L
        private val setRotationDegreesBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_rotation_degrees", SET_ROTATION_DEGREES_HASH)
        }

        private const val SET_SKEW_HASH = 373806689L
        private val setSkewBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_skew", SET_SKEW_HASH)
        }

        private const val SET_SCALE_HASH = 743155724L
        private val setScaleBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_scale", SET_SCALE_HASH)
        }

        private const val GET_POSITION_HASH = 3341600327L
        private val getPositionBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_position", GET_POSITION_HASH)
        }

        private const val GET_ROTATION_HASH = 1740695150L
        private val getRotationBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_rotation", GET_ROTATION_HASH)
        }

        private const val GET_ROTATION_DEGREES_HASH = 1740695150L
        private val getRotationDegreesBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_rotation_degrees", GET_ROTATION_DEGREES_HASH)
        }

        private const val GET_SKEW_HASH = 1740695150L
        private val getSkewBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_skew", GET_SKEW_HASH)
        }

        private const val GET_SCALE_HASH = 3341600327L
        private val getScaleBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_scale", GET_SCALE_HASH)
        }

        private const val ROTATE_HASH = 373806689L
        private val rotateBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "rotate", ROTATE_HASH)
        }

        private const val MOVE_LOCAL_X_HASH = 2087892650L
        private val moveLocalXBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "move_local_x", MOVE_LOCAL_X_HASH)
        }

        private const val MOVE_LOCAL_Y_HASH = 2087892650L
        private val moveLocalYBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "move_local_y", MOVE_LOCAL_Y_HASH)
        }

        private const val TRANSLATE_HASH = 743155724L
        private val translateBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "translate", TRANSLATE_HASH)
        }

        private const val GLOBAL_TRANSLATE_HASH = 743155724L
        private val globalTranslateBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "global_translate", GLOBAL_TRANSLATE_HASH)
        }

        private const val APPLY_SCALE_HASH = 743155724L
        private val applyScaleBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "apply_scale", APPLY_SCALE_HASH)
        }

        private const val SET_GLOBAL_POSITION_HASH = 743155724L
        private val setGlobalPositionBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_global_position", SET_GLOBAL_POSITION_HASH)
        }

        private const val GET_GLOBAL_POSITION_HASH = 3341600327L
        private val getGlobalPositionBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_global_position", GET_GLOBAL_POSITION_HASH)
        }

        private const val SET_GLOBAL_ROTATION_HASH = 373806689L
        private val setGlobalRotationBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_global_rotation", SET_GLOBAL_ROTATION_HASH)
        }

        private const val SET_GLOBAL_ROTATION_DEGREES_HASH = 373806689L
        private val setGlobalRotationDegreesBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_global_rotation_degrees", SET_GLOBAL_ROTATION_DEGREES_HASH)
        }

        private const val GET_GLOBAL_ROTATION_HASH = 1740695150L
        private val getGlobalRotationBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_global_rotation", GET_GLOBAL_ROTATION_HASH)
        }

        private const val GET_GLOBAL_ROTATION_DEGREES_HASH = 1740695150L
        private val getGlobalRotationDegreesBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_global_rotation_degrees", GET_GLOBAL_ROTATION_DEGREES_HASH)
        }

        private const val SET_GLOBAL_SKEW_HASH = 373806689L
        private val setGlobalSkewBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_global_skew", SET_GLOBAL_SKEW_HASH)
        }

        private const val GET_GLOBAL_SKEW_HASH = 1740695150L
        private val getGlobalSkewBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_global_skew", GET_GLOBAL_SKEW_HASH)
        }

        private const val SET_GLOBAL_SCALE_HASH = 743155724L
        private val setGlobalScaleBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_global_scale", SET_GLOBAL_SCALE_HASH)
        }

        private const val GET_GLOBAL_SCALE_HASH = 3341600327L
        private val getGlobalScaleBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_global_scale", GET_GLOBAL_SCALE_HASH)
        }

        private const val SET_TRANSFORM_HASH = 2761652528L
        private val setTransformBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_transform", SET_TRANSFORM_HASH)
        }

        private const val SET_GLOBAL_TRANSFORM_HASH = 2761652528L
        private val setGlobalTransformBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "set_global_transform", SET_GLOBAL_TRANSFORM_HASH)
        }

        private const val LOOK_AT_HASH = 743155724L
        private val lookAtBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "look_at", LOOK_AT_HASH)
        }

        private const val GET_ANGLE_TO_HASH = 2276447920L
        private val getAngleToBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_angle_to", GET_ANGLE_TO_HASH)
        }

        private const val TO_LOCAL_HASH = 2656412154L
        private val toLocalBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "to_local", TO_LOCAL_HASH)
        }

        private const val TO_GLOBAL_HASH = 2656412154L
        private val toGlobalBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "to_global", TO_GLOBAL_HASH)
        }

        private const val GET_RELATIVE_TRANSFORM_TO_PARENT_HASH = 904556875L
        private val getRelativeTransformToParentBind by lazy {
            ObjectCalls.getMethodBind("Node2D", "get_relative_transform_to_parent", GET_RELATIVE_TRANSFORM_TO_PARENT_HASH)
        }
    }
}
