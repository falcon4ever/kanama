package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform3D

/**
 * Abstract base class for 3D physics objects.
 *
 * Generated from Godot docs: CollisionObject3D
 */
open class CollisionObject3D(handle: MemorySegment) : Node3D(handle) {
    var disableMode: Long
        @JvmName("disableModeProperty")
        get() = getDisableMode()
        @JvmName("setDisableModeProperty")
        set(value) = setDisableMode(value)

    var collisionLayer: Long
        @JvmName("collisionLayerProperty")
        get() = getCollisionLayer()
        @JvmName("setCollisionLayerProperty")
        set(value) = setCollisionLayer(value)

    var collisionMask: Long
        @JvmName("collisionMaskProperty")
        get() = getCollisionMask()
        @JvmName("setCollisionMaskProperty")
        set(value) = setCollisionMask(value)

    var collisionPriority: Double
        @JvmName("collisionPriorityProperty")
        get() = getCollisionPriority()
        @JvmName("setCollisionPriorityProperty")
        set(value) = setCollisionPriority(value)

    var inputRayPickable: Boolean
        @JvmName("inputRayPickableProperty")
        get() = isRayPickable()
        @JvmName("setInputRayPickableProperty")
        set(value) = setRayPickable(value)

    var inputCaptureOnDrag: Boolean
        @JvmName("inputCaptureOnDragProperty")
        get() = getCaptureInputOnDrag()
        @JvmName("setInputCaptureOnDragProperty")
        set(value) = setCaptureInputOnDrag(value)

    /**
     * The physics layers this CollisionObject3D is in. Collision objects can exist in one or more of
     * 32 different layers. See also `collision_mask`. Note: Object A can detect a contact with object
     * B only if object B is in any of the layers that object A scans. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: CollisionObject3D.set_collision_layer
     */
    fun setCollisionLayer(layer: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionLayerBind, handle, layer)
    }

    /**
     * The physics layers this CollisionObject3D is in. Collision objects can exist in one or more of
     * 32 different layers. See also `collision_mask`. Note: Object A can detect a contact with object
     * B only if object B is in any of the layers that object A scans. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: CollisionObject3D.get_collision_layer
     */
    fun getCollisionLayer(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionLayerBind, handle)
    }

    /**
     * The physics layers this CollisionObject3D scans. Collision objects can scan one or more of 32
     * different layers. See also `collision_layer`. Note: Object A can detect a contact with object B
     * only if object B is in any of the layers that object A scans. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: CollisionObject3D.set_collision_mask
     */
    fun setCollisionMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionMaskBind, handle, mask)
    }

    /**
     * The physics layers this CollisionObject3D scans. Collision objects can scan one or more of 32
     * different layers. See also `collision_layer`. Note: Object A can detect a contact with object B
     * only if object B is in any of the layers that object A scans. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: CollisionObject3D.get_collision_mask
     */
    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionMaskBind, handle)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `collision_layer`, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: CollisionObject3D.set_collision_layer_value
     */
    fun setCollisionLayerValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCollisionLayerValueBind, handle, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `collision_layer` is enabled, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: CollisionObject3D.get_collision_layer_value
     */
    fun getCollisionLayerValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getCollisionLayerValueBind, handle, layerNumber)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `collision_mask`, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: CollisionObject3D.set_collision_mask_value
     */
    fun setCollisionMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCollisionMaskValueBind, handle, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `collision_mask` is enabled, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: CollisionObject3D.get_collision_mask_value
     */
    fun getCollisionMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getCollisionMaskValueBind, handle, layerNumber)
    }

    /**
     * The priority used to solve colliding when occurring penetration. The higher the priority is, the
     * lower the penetration into the object will be. This can for example be used to prevent the
     * player from breaking through the boundaries of a level.
     *
     * Generated from Godot docs: CollisionObject3D.set_collision_priority
     */
    fun setCollisionPriority(priority: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCollisionPriorityBind, handle, priority)
    }

    /**
     * The priority used to solve colliding when occurring penetration. The higher the priority is, the
     * lower the penetration into the object will be. This can for example be used to prevent the
     * player from breaking through the boundaries of a level.
     *
     * Generated from Godot docs: CollisionObject3D.get_collision_priority
     */
    fun getCollisionPriority(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCollisionPriorityBind, handle)
    }

    /**
     * Defines the behavior in physics when `Node.process_mode` is set to `Node.PROCESS_MODE_DISABLED`.
     *
     * Generated from Godot docs: CollisionObject3D.set_disable_mode
     */
    fun setDisableMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setDisableModeBind, handle, mode)
    }

    /**
     * Defines the behavior in physics when `Node.process_mode` is set to `Node.PROCESS_MODE_DISABLED`.
     *
     * Generated from Godot docs: CollisionObject3D.get_disable_mode
     */
    fun getDisableMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDisableModeBind, handle)
    }

    /**
     * If `true`, this object is pickable. A pickable object can detect the mouse pointer
     * entering/leaving, and if the mouse is inside it, report input events. Requires at least one
     * `collision_layer` bit to be set.
     *
     * Generated from Godot docs: CollisionObject3D.set_ray_pickable
     */
    fun setRayPickable(rayPickable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRayPickableBind, handle, rayPickable)
    }

    /**
     * If `true`, this object is pickable. A pickable object can detect the mouse pointer
     * entering/leaving, and if the mouse is inside it, report input events. Requires at least one
     * `collision_layer` bit to be set.
     *
     * Generated from Godot docs: CollisionObject3D.is_ray_pickable
     */
    fun isRayPickable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRayPickableBind, handle)
    }

    /**
     * If `true`, the `CollisionObject3D` will continue to receive input events as the mouse is dragged
     * across its shapes.
     *
     * Generated from Godot docs: CollisionObject3D.set_capture_input_on_drag
     */
    fun setCaptureInputOnDrag(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCaptureInputOnDragBind, handle, enable)
    }

    /**
     * If `true`, the `CollisionObject3D` will continue to receive input events as the mouse is dragged
     * across its shapes.
     *
     * Generated from Godot docs: CollisionObject3D.get_capture_input_on_drag
     */
    fun getCaptureInputOnDrag(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getCaptureInputOnDragBind, handle)
    }

    /**
     * Returns the object's `RID`.
     *
     * Generated from Godot docs: CollisionObject3D.get_rid
     */
    fun getRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getRidBind, handle)
    }

    /**
     * Creates a new shape owner for the given object. Returns `owner_id` of the new owner for future
     * reference.
     *
     * Generated from Godot docs: CollisionObject3D.create_shape_owner
     */
    fun createShapeOwner(owner: GodotObject): Long {
        return ObjectCalls.ptrcallWithObjectArgRetUInt32(createShapeOwnerBind, handle, owner.handle)
    }

    /**
     * Removes the given shape owner.
     *
     * Generated from Godot docs: CollisionObject3D.remove_shape_owner
     */
    fun removeShapeOwner(ownerId: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(removeShapeOwnerBind, handle, ownerId)
    }

    /**
     * Returns an `Array` of `owner_id` identifiers. You can use these ids in other methods that take
     * `owner_id` as an argument.
     *
     * Generated from Godot docs: CollisionObject3D.get_shape_owners
     */
    fun getShapeOwners(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getShapeOwnersBind, handle)
    }

    /**
     * Sets the `Transform3D` of the given shape owner.
     *
     * Generated from Godot docs: CollisionObject3D.shape_owner_set_transform
     */
    fun shapeOwnerSetTransform(ownerId: Long, transform: Transform3D) {
        ObjectCalls.ptrcallWithUInt32AndTransform3DArg(shapeOwnerSetTransformBind, handle, ownerId, transform)
    }

    /**
     * Returns the shape owner's `Transform3D`.
     *
     * Generated from Godot docs: CollisionObject3D.shape_owner_get_transform
     */
    fun shapeOwnerGetTransform(ownerId: Long): Transform3D {
        return ObjectCalls.ptrcallWithUInt32ArgRetTransform3D(shapeOwnerGetTransformBind, handle, ownerId)
    }

    /**
     * Returns the parent object of the given shape owner.
     *
     * Generated from Godot docs: CollisionObject3D.shape_owner_get_owner
     */
    fun shapeOwnerGetOwner(ownerId: Long): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallWithUInt32ArgRetObject(shapeOwnerGetOwnerBind, handle, ownerId))
    }

    /**
     * If `true`, disables the given shape owner.
     *
     * Generated from Godot docs: CollisionObject3D.shape_owner_set_disabled
     */
    fun shapeOwnerSetDisabled(ownerId: Long, disabled: Boolean) {
        ObjectCalls.ptrcallWithUInt32AndBoolArgs(shapeOwnerSetDisabledBind, handle, ownerId, disabled)
    }

    /**
     * If `true`, the shape owner and its shapes are disabled.
     *
     * Generated from Godot docs: CollisionObject3D.is_shape_owner_disabled
     */
    fun isShapeOwnerDisabled(ownerId: Long): Boolean {
        return ObjectCalls.ptrcallWithUInt32ArgRetBool(isShapeOwnerDisabledBind, handle, ownerId)
    }

    /**
     * Adds a `Shape3D` to the shape owner.
     *
     * Generated from Godot docs: CollisionObject3D.shape_owner_add_shape
     */
    fun shapeOwnerAddShape(ownerId: Long, shape: Shape3D?) {
        ObjectCalls.ptrcallWithUInt32AndObjectArg(shapeOwnerAddShapeBind, handle, ownerId, shape?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the number of shapes the given shape owner contains.
     *
     * Generated from Godot docs: CollisionObject3D.shape_owner_get_shape_count
     */
    fun shapeOwnerGetShapeCount(ownerId: Long): Int {
        return ObjectCalls.ptrcallWithUInt32ArgRetInt(shapeOwnerGetShapeCountBind, handle, ownerId)
    }

    /**
     * Returns the `Shape3D` with the given ID from the given shape owner.
     *
     * Generated from Godot docs: CollisionObject3D.shape_owner_get_shape
     */
    fun shapeOwnerGetShape(ownerId: Long, shapeId: Int): Shape3D? {
        return Shape3D.wrap(ObjectCalls.ptrcallWithUInt32AndIntArgRetObject(shapeOwnerGetShapeBind, handle, ownerId, shapeId))
    }

    /**
     * Returns the child index of the `Shape3D` with the given ID from the given shape owner.
     *
     * Generated from Godot docs: CollisionObject3D.shape_owner_get_shape_index
     */
    fun shapeOwnerGetShapeIndex(ownerId: Long, shapeId: Int): Int {
        return ObjectCalls.ptrcallWithUInt32AndIntArgRetInt(shapeOwnerGetShapeIndexBind, handle, ownerId, shapeId)
    }

    /**
     * Removes a shape from the given shape owner.
     *
     * Generated from Godot docs: CollisionObject3D.shape_owner_remove_shape
     */
    fun shapeOwnerRemoveShape(ownerId: Long, shapeId: Int) {
        ObjectCalls.ptrcallWithUInt32AndIntArg(shapeOwnerRemoveShapeBind, handle, ownerId, shapeId)
    }

    /**
     * Removes all shapes from the shape owner.
     *
     * Generated from Godot docs: CollisionObject3D.shape_owner_clear_shapes
     */
    fun shapeOwnerClearShapes(ownerId: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(shapeOwnerClearShapesBind, handle, ownerId)
    }

    /**
     * Returns the `owner_id` of the given shape.
     *
     * Generated from Godot docs: CollisionObject3D.shape_find_owner
     */
    fun shapeFindOwner(shapeIndex: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetUInt32(shapeFindOwnerBind, handle, shapeIndex)
    }

    object Signals {
        const val inputEvent: String = "input_event"
        const val mouseEntered: String = "mouse_entered"
        const val mouseExited: String = "mouse_exited"
    }

    companion object {
        const val DISABLE_MODE_REMOVE: Long = 0L
        const val DISABLE_MODE_MAKE_STATIC: Long = 1L
        const val DISABLE_MODE_KEEP_ACTIVE: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): CollisionObject3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CollisionObject3D? =
            if (handle.address() == 0L) null else CollisionObject3D(handle)

        private const val SET_COLLISION_LAYER_HASH = 1286410249L
        private val setCollisionLayerBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "set_collision_layer", SET_COLLISION_LAYER_HASH)
        }

        private const val GET_COLLISION_LAYER_HASH = 3905245786L
        private val getCollisionLayerBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "get_collision_layer", GET_COLLISION_LAYER_HASH)
        }

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        private val setCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "set_collision_mask", SET_COLLISION_MASK_HASH)
        }

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        private val getCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "get_collision_mask", GET_COLLISION_MASK_HASH)
        }

        private const val SET_COLLISION_LAYER_VALUE_HASH = 300928843L
        private val setCollisionLayerValueBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "set_collision_layer_value", SET_COLLISION_LAYER_VALUE_HASH)
        }

        private const val GET_COLLISION_LAYER_VALUE_HASH = 1116898809L
        private val getCollisionLayerValueBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "get_collision_layer_value", GET_COLLISION_LAYER_VALUE_HASH)
        }

        private const val SET_COLLISION_MASK_VALUE_HASH = 300928843L
        private val setCollisionMaskValueBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "set_collision_mask_value", SET_COLLISION_MASK_VALUE_HASH)
        }

        private const val GET_COLLISION_MASK_VALUE_HASH = 1116898809L
        private val getCollisionMaskValueBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "get_collision_mask_value", GET_COLLISION_MASK_VALUE_HASH)
        }

        private const val SET_COLLISION_PRIORITY_HASH = 373806689L
        private val setCollisionPriorityBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "set_collision_priority", SET_COLLISION_PRIORITY_HASH)
        }

        private const val GET_COLLISION_PRIORITY_HASH = 1740695150L
        private val getCollisionPriorityBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "get_collision_priority", GET_COLLISION_PRIORITY_HASH)
        }

        private const val SET_DISABLE_MODE_HASH = 1623620376L
        private val setDisableModeBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "set_disable_mode", SET_DISABLE_MODE_HASH)
        }

        private const val GET_DISABLE_MODE_HASH = 410164780L
        private val getDisableModeBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "get_disable_mode", GET_DISABLE_MODE_HASH)
        }

        private const val SET_RAY_PICKABLE_HASH = 2586408642L
        private val setRayPickableBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "set_ray_pickable", SET_RAY_PICKABLE_HASH)
        }

        private const val IS_RAY_PICKABLE_HASH = 36873697L
        private val isRayPickableBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "is_ray_pickable", IS_RAY_PICKABLE_HASH)
        }

        private const val SET_CAPTURE_INPUT_ON_DRAG_HASH = 2586408642L
        private val setCaptureInputOnDragBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "set_capture_input_on_drag", SET_CAPTURE_INPUT_ON_DRAG_HASH)
        }

        private const val GET_CAPTURE_INPUT_ON_DRAG_HASH = 36873697L
        private val getCaptureInputOnDragBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "get_capture_input_on_drag", GET_CAPTURE_INPUT_ON_DRAG_HASH)
        }

        private const val GET_RID_HASH = 2944877500L
        private val getRidBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "get_rid", GET_RID_HASH)
        }

        private const val CREATE_SHAPE_OWNER_HASH = 3429307534L
        private val createShapeOwnerBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "create_shape_owner", CREATE_SHAPE_OWNER_HASH)
        }

        private const val REMOVE_SHAPE_OWNER_HASH = 1286410249L
        private val removeShapeOwnerBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "remove_shape_owner", REMOVE_SHAPE_OWNER_HASH)
        }

        private const val GET_SHAPE_OWNERS_HASH = 969006518L
        private val getShapeOwnersBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "get_shape_owners", GET_SHAPE_OWNERS_HASH)
        }

        private const val SHAPE_OWNER_SET_TRANSFORM_HASH = 3616898986L
        private val shapeOwnerSetTransformBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "shape_owner_set_transform", SHAPE_OWNER_SET_TRANSFORM_HASH)
        }

        private const val SHAPE_OWNER_GET_TRANSFORM_HASH = 1965739696L
        private val shapeOwnerGetTransformBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "shape_owner_get_transform", SHAPE_OWNER_GET_TRANSFORM_HASH)
        }

        private const val SHAPE_OWNER_GET_OWNER_HASH = 3332903315L
        private val shapeOwnerGetOwnerBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "shape_owner_get_owner", SHAPE_OWNER_GET_OWNER_HASH)
        }

        private const val SHAPE_OWNER_SET_DISABLED_HASH = 300928843L
        private val shapeOwnerSetDisabledBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "shape_owner_set_disabled", SHAPE_OWNER_SET_DISABLED_HASH)
        }

        private const val IS_SHAPE_OWNER_DISABLED_HASH = 1116898809L
        private val isShapeOwnerDisabledBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "is_shape_owner_disabled", IS_SHAPE_OWNER_DISABLED_HASH)
        }

        private const val SHAPE_OWNER_ADD_SHAPE_HASH = 2566676345L
        private val shapeOwnerAddShapeBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "shape_owner_add_shape", SHAPE_OWNER_ADD_SHAPE_HASH)
        }

        private const val SHAPE_OWNER_GET_SHAPE_COUNT_HASH = 923996154L
        private val shapeOwnerGetShapeCountBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "shape_owner_get_shape_count", SHAPE_OWNER_GET_SHAPE_COUNT_HASH)
        }

        private const val SHAPE_OWNER_GET_SHAPE_HASH = 4015519174L
        private val shapeOwnerGetShapeBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "shape_owner_get_shape", SHAPE_OWNER_GET_SHAPE_HASH)
        }

        private const val SHAPE_OWNER_GET_SHAPE_INDEX_HASH = 3175239445L
        private val shapeOwnerGetShapeIndexBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "shape_owner_get_shape_index", SHAPE_OWNER_GET_SHAPE_INDEX_HASH)
        }

        private const val SHAPE_OWNER_REMOVE_SHAPE_HASH = 3937882851L
        private val shapeOwnerRemoveShapeBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "shape_owner_remove_shape", SHAPE_OWNER_REMOVE_SHAPE_HASH)
        }

        private const val SHAPE_OWNER_CLEAR_SHAPES_HASH = 1286410249L
        private val shapeOwnerClearShapesBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "shape_owner_clear_shapes", SHAPE_OWNER_CLEAR_SHAPES_HASH)
        }

        private const val SHAPE_FIND_OWNER_HASH = 923996154L
        private val shapeFindOwnerBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject3D", "shape_find_owner", SHAPE_FIND_OWNER_HASH)
        }
    }
}
