package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2

/**
 * Abstract base class for 2D physics objects.
 *
 * Generated from Godot docs: CollisionObject2D
 */
open class CollisionObject2D(handle: GodotHandle) : Node2D(handle) {
    var disableMode: CollisionObject2D.DisableMode
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

    var inputPickable: Boolean
        @JvmName("inputPickableProperty")
        get() = isPickable()
        @JvmName("setInputPickableProperty")
        set(value) = setPickable(value)

    /**
     * Returns the object's `RID`.
     *
     * Generated from Godot docs: CollisionObject2D.get_rid
     */
    fun getRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getRidBind, segment)
    }

    /**
     * The physics layers this CollisionObject2D is in. Collision objects can exist in one or more of
     * 32 different layers. See also `collision_mask`. Note: Object A can detect a contact with object
     * B only if object B is in any of the layers that object A scans. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: CollisionObject2D.set_collision_layer
     */
    fun setCollisionLayer(layer: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCollisionLayerBind, segment, layer)
    }

    /**
     * The physics layers this CollisionObject2D is in. Collision objects can exist in one or more of
     * 32 different layers. See also `collision_mask`. Note: Object A can detect a contact with object
     * B only if object B is in any of the layers that object A scans. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: CollisionObject2D.get_collision_layer
     */
    fun getCollisionLayer(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getCollisionLayerBind, segment)
    }

    /**
     * The physics layers this CollisionObject2D scans. Collision objects can scan one or more of 32
     * different layers. See also `collision_layer`. Note: Object A can detect a contact with object B
     * only if object B is in any of the layers that object A scans. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: CollisionObject2D.set_collision_mask
     */
    fun setCollisionMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCollisionMaskBind, segment, mask)
    }

    /**
     * The physics layers this CollisionObject2D scans. Collision objects can scan one or more of 32
     * different layers. See also `collision_layer`. Note: Object A can detect a contact with object B
     * only if object B is in any of the layers that object A scans. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: CollisionObject2D.get_collision_mask
     */
    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getCollisionMaskBind, segment)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `collision_layer`, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: CollisionObject2D.set_collision_layer_value
     */
    fun setCollisionLayerValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setCollisionLayerValueBind, segment, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `collision_layer` is enabled, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: CollisionObject2D.get_collision_layer_value
     */
    fun getCollisionLayerValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getCollisionLayerValueBind, segment, layerNumber)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `collision_mask`, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: CollisionObject2D.set_collision_mask_value
     */
    fun setCollisionMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setCollisionMaskValueBind, segment, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `collision_mask` is enabled, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: CollisionObject2D.get_collision_mask_value
     */
    fun getCollisionMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getCollisionMaskValueBind, segment, layerNumber)
    }

    /**
     * The priority used to solve colliding when occurring penetration. The higher the priority is, the
     * lower the penetration into the object will be. This can for example be used to prevent the
     * player from breaking through the boundaries of a level.
     *
     * Generated from Godot docs: CollisionObject2D.set_collision_priority
     */
    fun setCollisionPriority(priority: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setCollisionPriorityBind, segment, priority)
    }

    /**
     * The priority used to solve colliding when occurring penetration. The higher the priority is, the
     * lower the penetration into the object will be. This can for example be used to prevent the
     * player from breaking through the boundaries of a level.
     *
     * Generated from Godot docs: CollisionObject2D.get_collision_priority
     */
    fun getCollisionPriority(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCollisionPriorityBind, segment)
    }

    /**
     * Defines the behavior in physics when `Node.process_mode` is set to `Node.ProcessMode.DISABLED`.
     *
     * Generated from Godot docs: CollisionObject2D.set_disable_mode
     */
    fun setDisableMode(mode: CollisionObject2D.DisableMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setDisableModeBind, segment, mode.value)
    }

    /**
     * Defines the behavior in physics when `Node.process_mode` is set to `Node.ProcessMode.DISABLED`.
     *
     * Generated from Godot docs: CollisionObject2D.get_disable_mode
     */
    fun getDisableMode(): CollisionObject2D.DisableMode {
        return CollisionObject2D.DisableMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getDisableModeBind, segment))
    }

    /**
     * If `true`, this object is pickable. A pickable object can detect the mouse pointer
     * entering/leaving, and if the mouse is inside it, report input events. Requires at least one
     * `collision_layer` bit to be set.
     *
     * Generated from Godot docs: CollisionObject2D.set_pickable
     */
    fun setPickable(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setPickableBind, segment, enabled)
    }

    /**
     * If `true`, this object is pickable. A pickable object can detect the mouse pointer
     * entering/leaving, and if the mouse is inside it, report input events. Requires at least one
     * `collision_layer` bit to be set.
     *
     * Generated from Godot docs: CollisionObject2D.is_pickable
     */
    fun isPickable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPickableBind, segment)
    }

    /**
     * Creates a new shape owner for the given object. Returns `owner_id` of the new owner for future
     * reference.
     *
     * Generated from Godot docs: CollisionObject2D.create_shape_owner
     */
    fun createShapeOwner(owner: GodotObject): Long {
        return ObjectCalls.ptrcallWithObjectArgRetUInt32(Binds.createShapeOwnerBind, segment, owner.segment)
    }

    /**
     * Removes the given shape owner.
     *
     * Generated from Godot docs: CollisionObject2D.remove_shape_owner
     */
    fun removeShapeOwner(ownerId: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.removeShapeOwnerBind, segment, ownerId)
    }

    /**
     * Returns an `Array` of `owner_id` identifiers. You can use these ids in other methods that take
     * `owner_id` as an argument.
     *
     * Generated from Godot docs: CollisionObject2D.get_shape_owners
     */
    fun getShapeOwners(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(Binds.getShapeOwnersBind, segment)
    }

    /**
     * Sets the `Transform2D` of the given shape owner.
     *
     * Generated from Godot docs: CollisionObject2D.shape_owner_set_transform
     */
    fun shapeOwnerSetTransform(ownerId: Long, transform: Transform2D) {
        ObjectCalls.ptrcallWithUInt32AndTransform2DArg(Binds.shapeOwnerSetTransformBind, segment, ownerId, transform)
    }

    /**
     * Returns the shape owner's `Transform2D`.
     *
     * Generated from Godot docs: CollisionObject2D.shape_owner_get_transform
     */
    fun shapeOwnerGetTransform(ownerId: Long): Transform2D {
        return ObjectCalls.ptrcallWithUInt32ArgRetTransform2D(Binds.shapeOwnerGetTransformBind, segment, ownerId)
    }

    /**
     * Returns the parent object of the given shape owner.
     *
     * Generated from Godot docs: CollisionObject2D.shape_owner_get_owner
     */
    fun shapeOwnerGetOwner(ownerId: Long): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallWithUInt32ArgRetObject(Binds.shapeOwnerGetOwnerBind, segment, ownerId))
    }

    /**
     * If `true`, disables the given shape owner.
     *
     * Generated from Godot docs: CollisionObject2D.shape_owner_set_disabled
     */
    fun shapeOwnerSetDisabled(ownerId: Long, disabled: Boolean) {
        ObjectCalls.ptrcallWithUInt32AndBoolArgs(Binds.shapeOwnerSetDisabledBind, segment, ownerId, disabled)
    }

    /**
     * If `true`, the shape owner and its shapes are disabled.
     *
     * Generated from Godot docs: CollisionObject2D.is_shape_owner_disabled
     */
    fun isShapeOwnerDisabled(ownerId: Long): Boolean {
        return ObjectCalls.ptrcallWithUInt32ArgRetBool(Binds.isShapeOwnerDisabledBind, segment, ownerId)
    }

    /**
     * If `enable` is `true`, collisions for the shape owner originating from this `CollisionObject2D`
     * will not be reported to collided with `CollisionObject2D`s.
     *
     * Generated from Godot docs: CollisionObject2D.shape_owner_set_one_way_collision
     */
    fun shapeOwnerSetOneWayCollision(ownerId: Long, enable: Boolean) {
        ObjectCalls.ptrcallWithUInt32AndBoolArgs(Binds.shapeOwnerSetOneWayCollisionBind, segment, ownerId, enable)
    }

    /**
     * Returns `true` if collisions for the shape owner originating from this `CollisionObject2D` will
     * not be reported to collided with `CollisionObject2D`s.
     *
     * Generated from Godot docs: CollisionObject2D.is_shape_owner_one_way_collision_enabled
     */
    fun isShapeOwnerOneWayCollisionEnabled(ownerId: Long): Boolean {
        return ObjectCalls.ptrcallWithUInt32ArgRetBool(Binds.isShapeOwnerOneWayCollisionEnabledBind, segment, ownerId)
    }

    /**
     * Sets the `one_way_collision_margin` of the shape owner identified by given `owner_id` to
     * `margin` pixels.
     *
     * Generated from Godot docs: CollisionObject2D.shape_owner_set_one_way_collision_margin
     */
    fun shapeOwnerSetOneWayCollisionMargin(ownerId: Long, margin: Double) {
        ObjectCalls.ptrcallWithUInt32AndDoubleArg(Binds.shapeOwnerSetOneWayCollisionMarginBind, segment, ownerId, margin)
    }

    /**
     * Returns the `one_way_collision_margin` of the shape owner identified by given `owner_id`.
     *
     * Generated from Godot docs: CollisionObject2D.get_shape_owner_one_way_collision_margin
     */
    fun getShapeOwnerOneWayCollisionMargin(ownerId: Long): Double {
        return ObjectCalls.ptrcallWithUInt32ArgRetDouble(Binds.getShapeOwnerOneWayCollisionMarginBind, segment, ownerId)
    }

    /**
     * Returns the `one_way_collision_direction` of the shape owner identified by the given `owner_id`.
     *
     * Generated from Godot docs: CollisionObject2D.get_shape_owner_one_way_collision_direction
     */
    fun getShapeOwnerOneWayCollisionDirection(ownerId: Long): Vector2 {
        return ObjectCalls.ptrcallWithUInt32ArgRetVector2(Binds.getShapeOwnerOneWayCollisionDirectionBind, segment, ownerId)
    }

    /**
     * Sets the `one_way_collision_direction` of the shape owner identified by the given `owner_id` to
     * `direction`.
     *
     * Generated from Godot docs: CollisionObject2D.shape_owner_set_one_way_collision_direction
     */
    fun shapeOwnerSetOneWayCollisionDirection(ownerId: Long, direction: Vector2) {
        ObjectCalls.ptrcallWithUInt32AndVector2Args(Binds.shapeOwnerSetOneWayCollisionDirectionBind, segment, ownerId, direction)
    }

    /**
     * Adds a `Shape2D` to the shape owner.
     *
     * Generated from Godot docs: CollisionObject2D.shape_owner_add_shape
     */
    fun shapeOwnerAddShape(ownerId: Long, shape: Shape2D) {
        ObjectCalls.ptrcallWithUInt32AndObjectArg(Binds.shapeOwnerAddShapeBind, segment, ownerId, shape.requireOpenHandle())
    }

    /**
     * Returns the number of shapes the given shape owner contains.
     *
     * Generated from Godot docs: CollisionObject2D.shape_owner_get_shape_count
     */
    fun shapeOwnerGetShapeCount(ownerId: Long): Int {
        return ObjectCalls.ptrcallWithUInt32ArgRetInt(Binds.shapeOwnerGetShapeCountBind, segment, ownerId)
    }

    /**
     * Returns the `Shape2D` with the given ID from the given shape owner.
     *
     * Generated from Godot docs: CollisionObject2D.shape_owner_get_shape
     */
    fun shapeOwnerGetShape(ownerId: Long, shapeId: Int): Shape2D? {
        return Shape2D.wrapOwned(ObjectCalls.ptrcallWithUInt32AndIntArgRetObject(Binds.shapeOwnerGetShapeBind, segment, ownerId, shapeId))
    }

    /**
     * Returns the child index of the `Shape2D` with the given ID from the given shape owner.
     *
     * Generated from Godot docs: CollisionObject2D.shape_owner_get_shape_index
     */
    fun shapeOwnerGetShapeIndex(ownerId: Long, shapeId: Int): Int {
        return ObjectCalls.ptrcallWithUInt32AndIntArgRetInt(Binds.shapeOwnerGetShapeIndexBind, segment, ownerId, shapeId)
    }

    /**
     * Removes a shape from the given shape owner.
     *
     * Generated from Godot docs: CollisionObject2D.shape_owner_remove_shape
     */
    fun shapeOwnerRemoveShape(ownerId: Long, shapeId: Int) {
        ObjectCalls.ptrcallWithUInt32AndIntArg(Binds.shapeOwnerRemoveShapeBind, segment, ownerId, shapeId)
    }

    /**
     * Removes all shapes from the shape owner.
     *
     * Generated from Godot docs: CollisionObject2D.shape_owner_clear_shapes
     */
    fun shapeOwnerClearShapes(ownerId: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.shapeOwnerClearShapesBind, segment, ownerId)
    }

    /**
     * Returns the `owner_id` of the given shape.
     *
     * Generated from Godot docs: CollisionObject2D.shape_find_owner
     */
    fun shapeFindOwner(shapeIndex: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetUInt32(Binds.shapeFindOwnerBind, segment, shapeIndex)
    }

    /** Signal `input_event(viewport: Node, event: InputEvent, shape_idx: int)`; see [TypedSignal]. */
    val inputEvent: Signal3<Node, InputEvent, Long>
        @JvmName("inputEventTypedSignal")
        get() = Signal3(this, "input_event", SignalArgType.objectOf("Node") { Node(it) }, SignalArgType.objectOf("InputEvent") { InputEvent(it) }, SignalArgType.LONG)

    /** Signal `mouse_entered()`; see [TypedSignal]. */
    val mouseEntered: Signal0
        @JvmName("mouseEnteredTypedSignal")
        get() = Signal0(this, "mouse_entered")

    /** Signal `mouse_exited()`; see [TypedSignal]. */
    val mouseExited: Signal0
        @JvmName("mouseExitedTypedSignal")
        get() = Signal0(this, "mouse_exited")

    /** Signal `mouse_shape_entered(shape_idx: int)`; see [TypedSignal]. */
    val mouseShapeEntered: Signal1<Long>
        @JvmName("mouseShapeEnteredTypedSignal")
        get() = Signal1(this, "mouse_shape_entered", SignalArgType.LONG)

    /** Signal `mouse_shape_exited(shape_idx: int)`; see [TypedSignal]. */
    val mouseShapeExited: Signal1<Long>
        @JvmName("mouseShapeExitedTypedSignal")
        get() = Signal1(this, "mouse_shape_exited", SignalArgType.LONG)

    object Signals {
        const val inputEvent: String = "input_event"
        const val mouseEntered: String = "mouse_entered"
        const val mouseExited: String = "mouse_exited"
        const val mouseShapeEntered: String = "mouse_shape_entered"
        const val mouseShapeExited: String = "mouse_shape_exited"
    }

    /**
     * Godot's `CollisionObject2D.DisableMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`CollisionObject2D.DisableMode.<NAME>`).
     *
     * Generated from Godot docs: CollisionObject2D.DisableMode
     */
    @JvmInline
    value class DisableMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * When `Node.process_mode` is set to `Node.ProcessMode.DISABLED`, remove from the physics
             * simulation to stop all physics interactions with this `CollisionObject2D`. Automatically
             * re-added to the physics simulation when the `Node` is processed again.
             *
             * Generated from Godot docs: CollisionObject2D.DISABLE_MODE_REMOVE
             */
            val REMOVE: DisableMode get() = DisableMode(0L)
            /**
             * When `Node.process_mode` is set to `Node.ProcessMode.DISABLED`, make the body static. Doesn't
             * affect `Area2D`. `PhysicsBody2D` can't be affected by forces or other bodies while static.
             * Automatically set `PhysicsBody2D` back to its original mode when the `Node` is processed again.
             *
             * Generated from Godot docs: CollisionObject2D.DISABLE_MODE_MAKE_STATIC
             */
            val MAKE_STATIC: DisableMode get() = DisableMode(1L)
            /**
             * When `Node.process_mode` is set to `Node.ProcessMode.DISABLED`, do not affect the physics
             * simulation.
             *
             * Generated from Godot docs: CollisionObject2D.DISABLE_MODE_KEEP_ACTIVE
             */
            val KEEP_ACTIVE: DisableMode get() = DisableMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CollisionObject2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CollisionObject2D? =
            if (handle.address() == 0L) null else CollisionObject2D(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_RID_HASH = 2944877500L
        @JvmField
        val getRidBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "get_rid", GET_RID_HASH)

        private const val SET_COLLISION_LAYER_HASH = 1286410249L
        @JvmField
        val setCollisionLayerBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "set_collision_layer", SET_COLLISION_LAYER_HASH)

        private const val GET_COLLISION_LAYER_HASH = 3905245786L
        @JvmField
        val getCollisionLayerBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "get_collision_layer", GET_COLLISION_LAYER_HASH)

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        @JvmField
        val setCollisionMaskBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "set_collision_mask", SET_COLLISION_MASK_HASH)

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        @JvmField
        val getCollisionMaskBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "get_collision_mask", GET_COLLISION_MASK_HASH)

        private const val SET_COLLISION_LAYER_VALUE_HASH = 300928843L
        @JvmField
        val setCollisionLayerValueBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "set_collision_layer_value", SET_COLLISION_LAYER_VALUE_HASH)

        private const val GET_COLLISION_LAYER_VALUE_HASH = 1116898809L
        @JvmField
        val getCollisionLayerValueBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "get_collision_layer_value", GET_COLLISION_LAYER_VALUE_HASH)

        private const val SET_COLLISION_MASK_VALUE_HASH = 300928843L
        @JvmField
        val setCollisionMaskValueBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "set_collision_mask_value", SET_COLLISION_MASK_VALUE_HASH)

        private const val GET_COLLISION_MASK_VALUE_HASH = 1116898809L
        @JvmField
        val getCollisionMaskValueBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "get_collision_mask_value", GET_COLLISION_MASK_VALUE_HASH)

        private const val SET_COLLISION_PRIORITY_HASH = 373806689L
        @JvmField
        val setCollisionPriorityBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "set_collision_priority", SET_COLLISION_PRIORITY_HASH)

        private const val GET_COLLISION_PRIORITY_HASH = 1740695150L
        @JvmField
        val getCollisionPriorityBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "get_collision_priority", GET_COLLISION_PRIORITY_HASH)

        private const val SET_DISABLE_MODE_HASH = 1919204045L
        @JvmField
        val setDisableModeBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "set_disable_mode", SET_DISABLE_MODE_HASH)

        private const val GET_DISABLE_MODE_HASH = 3172846349L
        @JvmField
        val getDisableModeBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "get_disable_mode", GET_DISABLE_MODE_HASH)

        private const val SET_PICKABLE_HASH = 2586408642L
        @JvmField
        val setPickableBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "set_pickable", SET_PICKABLE_HASH)

        private const val IS_PICKABLE_HASH = 36873697L
        @JvmField
        val isPickableBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "is_pickable", IS_PICKABLE_HASH)

        private const val CREATE_SHAPE_OWNER_HASH = 3429307534L
        @JvmField
        val createShapeOwnerBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "create_shape_owner", CREATE_SHAPE_OWNER_HASH)

        private const val REMOVE_SHAPE_OWNER_HASH = 1286410249L
        @JvmField
        val removeShapeOwnerBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "remove_shape_owner", REMOVE_SHAPE_OWNER_HASH)

        private const val GET_SHAPE_OWNERS_HASH = 969006518L
        @JvmField
        val getShapeOwnersBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "get_shape_owners", GET_SHAPE_OWNERS_HASH)

        private const val SHAPE_OWNER_SET_TRANSFORM_HASH = 30160968L
        @JvmField
        val shapeOwnerSetTransformBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_owner_set_transform", SHAPE_OWNER_SET_TRANSFORM_HASH)

        private const val SHAPE_OWNER_GET_TRANSFORM_HASH = 3836996910L
        @JvmField
        val shapeOwnerGetTransformBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_owner_get_transform", SHAPE_OWNER_GET_TRANSFORM_HASH)

        private const val SHAPE_OWNER_GET_OWNER_HASH = 3332903315L
        @JvmField
        val shapeOwnerGetOwnerBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_owner_get_owner", SHAPE_OWNER_GET_OWNER_HASH)

        private const val SHAPE_OWNER_SET_DISABLED_HASH = 300928843L
        @JvmField
        val shapeOwnerSetDisabledBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_owner_set_disabled", SHAPE_OWNER_SET_DISABLED_HASH)

        private const val IS_SHAPE_OWNER_DISABLED_HASH = 1116898809L
        @JvmField
        val isShapeOwnerDisabledBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "is_shape_owner_disabled", IS_SHAPE_OWNER_DISABLED_HASH)

        private const val SHAPE_OWNER_SET_ONE_WAY_COLLISION_HASH = 300928843L
        @JvmField
        val shapeOwnerSetOneWayCollisionBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_owner_set_one_way_collision", SHAPE_OWNER_SET_ONE_WAY_COLLISION_HASH)

        private const val IS_SHAPE_OWNER_ONE_WAY_COLLISION_ENABLED_HASH = 1116898809L
        @JvmField
        val isShapeOwnerOneWayCollisionEnabledBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "is_shape_owner_one_way_collision_enabled", IS_SHAPE_OWNER_ONE_WAY_COLLISION_ENABLED_HASH)

        private const val SHAPE_OWNER_SET_ONE_WAY_COLLISION_MARGIN_HASH = 1602489585L
        @JvmField
        val shapeOwnerSetOneWayCollisionMarginBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_owner_set_one_way_collision_margin", SHAPE_OWNER_SET_ONE_WAY_COLLISION_MARGIN_HASH)

        private const val GET_SHAPE_OWNER_ONE_WAY_COLLISION_MARGIN_HASH = 2339986948L
        @JvmField
        val getShapeOwnerOneWayCollisionMarginBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "get_shape_owner_one_way_collision_margin", GET_SHAPE_OWNER_ONE_WAY_COLLISION_MARGIN_HASH)

        private const val GET_SHAPE_OWNER_ONE_WAY_COLLISION_DIRECTION_HASH = 2299179447L
        @JvmField
        val getShapeOwnerOneWayCollisionDirectionBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "get_shape_owner_one_way_collision_direction", GET_SHAPE_OWNER_ONE_WAY_COLLISION_DIRECTION_HASH)

        private const val SHAPE_OWNER_SET_ONE_WAY_COLLISION_DIRECTION_HASH = 163021252L
        @JvmField
        val shapeOwnerSetOneWayCollisionDirectionBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_owner_set_one_way_collision_direction", SHAPE_OWNER_SET_ONE_WAY_COLLISION_DIRECTION_HASH)

        private const val SHAPE_OWNER_ADD_SHAPE_HASH = 2077425081L
        @JvmField
        val shapeOwnerAddShapeBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_owner_add_shape", SHAPE_OWNER_ADD_SHAPE_HASH)

        private const val SHAPE_OWNER_GET_SHAPE_COUNT_HASH = 923996154L
        @JvmField
        val shapeOwnerGetShapeCountBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_owner_get_shape_count", SHAPE_OWNER_GET_SHAPE_COUNT_HASH)

        private const val SHAPE_OWNER_GET_SHAPE_HASH = 3106725749L
        @JvmField
        val shapeOwnerGetShapeBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_owner_get_shape", SHAPE_OWNER_GET_SHAPE_HASH)

        private const val SHAPE_OWNER_GET_SHAPE_INDEX_HASH = 3175239445L
        @JvmField
        val shapeOwnerGetShapeIndexBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_owner_get_shape_index", SHAPE_OWNER_GET_SHAPE_INDEX_HASH)

        private const val SHAPE_OWNER_REMOVE_SHAPE_HASH = 3937882851L
        @JvmField
        val shapeOwnerRemoveShapeBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_owner_remove_shape", SHAPE_OWNER_REMOVE_SHAPE_HASH)

        private const val SHAPE_OWNER_CLEAR_SHAPES_HASH = 1286410249L
        @JvmField
        val shapeOwnerClearShapesBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_owner_clear_shapes", SHAPE_OWNER_CLEAR_SHAPES_HASH)

        private const val SHAPE_FIND_OWNER_HASH = 923996154L
        @JvmField
        val shapeFindOwnerBind =
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_find_owner", SHAPE_FIND_OWNER_HASH)
    }
}
