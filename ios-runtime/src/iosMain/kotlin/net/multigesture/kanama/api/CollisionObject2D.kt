package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: CollisionObject2D
 */
open class CollisionObject2D(handle: MemorySegment) : Node2D(handle) {
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

    var inputPickable: Boolean
        @JvmName("inputPickableProperty")
        get() = isPickable()
        @JvmName("setInputPickableProperty")
        set(value) = setPickable(value)

    fun setCollisionLayer(layer: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionLayerBind, handle, layer)
    }

    fun getCollisionLayer(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionLayerBind, handle)
    }

    fun setCollisionMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionMaskBind, handle, mask)
    }

    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionMaskBind, handle)
    }

    fun setCollisionLayerValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCollisionLayerValueBind, handle, layerNumber, value)
    }

    fun getCollisionLayerValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getCollisionLayerValueBind, handle, layerNumber)
    }

    fun setCollisionMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCollisionMaskValueBind, handle, layerNumber, value)
    }

    fun getCollisionMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getCollisionMaskValueBind, handle, layerNumber)
    }

    fun setCollisionPriority(priority: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCollisionPriorityBind, handle, priority)
    }

    fun getCollisionPriority(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCollisionPriorityBind, handle)
    }

    fun setDisableMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setDisableModeBind, handle, mode)
    }

    fun getDisableMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDisableModeBind, handle)
    }

    fun setPickable(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPickableBind, handle, enabled)
    }

    fun isPickable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPickableBind, handle)
    }

    fun createShapeOwner(owner: GodotObject): Long {
        return ObjectCalls.ptrcallWithObjectArgRetUInt32(createShapeOwnerBind, handle, owner.handle)
    }

    fun removeShapeOwner(ownerId: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(removeShapeOwnerBind, handle, ownerId)
    }

    fun shapeOwnerGetOwner(ownerId: Long): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallWithUInt32ArgRetObject(shapeOwnerGetOwnerBind, handle, ownerId))
    }

    fun shapeOwnerSetDisabled(ownerId: Long, disabled: Boolean) {
        ObjectCalls.ptrcallWithUInt32AndBoolArgs(shapeOwnerSetDisabledBind, handle, ownerId, disabled)
    }

    fun isShapeOwnerDisabled(ownerId: Long): Boolean {
        return ObjectCalls.ptrcallWithUInt32ArgRetBool(isShapeOwnerDisabledBind, handle, ownerId)
    }

    fun shapeOwnerSetOneWayCollision(ownerId: Long, enable: Boolean) {
        ObjectCalls.ptrcallWithUInt32AndBoolArgs(shapeOwnerSetOneWayCollisionBind, handle, ownerId, enable)
    }

    fun isShapeOwnerOneWayCollisionEnabled(ownerId: Long): Boolean {
        return ObjectCalls.ptrcallWithUInt32ArgRetBool(isShapeOwnerOneWayCollisionEnabledBind, handle, ownerId)
    }

    fun shapeOwnerSetOneWayCollisionMargin(ownerId: Long, margin: Double) {
        ObjectCalls.ptrcallWithUInt32AndDoubleArg(shapeOwnerSetOneWayCollisionMarginBind, handle, ownerId, margin)
    }

    fun getShapeOwnerOneWayCollisionMargin(ownerId: Long): Double {
        return ObjectCalls.ptrcallWithUInt32ArgRetDouble(getShapeOwnerOneWayCollisionMarginBind, handle, ownerId)
    }

    fun shapeOwnerGetShapeCount(ownerId: Long): Int {
        return ObjectCalls.ptrcallWithUInt32ArgRetInt(shapeOwnerGetShapeCountBind, handle, ownerId)
    }

    fun shapeOwnerGetShapeIndex(ownerId: Long, shapeId: Int): Int {
        return ObjectCalls.ptrcallWithUInt32AndIntArgRetInt(shapeOwnerGetShapeIndexBind, handle, ownerId, shapeId)
    }

    fun shapeOwnerRemoveShape(ownerId: Long, shapeId: Int) {
        ObjectCalls.ptrcallWithUInt32AndIntArg(shapeOwnerRemoveShapeBind, handle, ownerId, shapeId)
    }

    fun shapeOwnerClearShapes(ownerId: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(shapeOwnerClearShapesBind, handle, ownerId)
    }

    fun shapeFindOwner(shapeIndex: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetUInt32(shapeFindOwnerBind, handle, shapeIndex)
    }

    object Signals {
        const val inputEvent: String = "input_event"
        const val mouseEntered: String = "mouse_entered"
        const val mouseExited: String = "mouse_exited"
        const val mouseShapeEntered: String = "mouse_shape_entered"
        const val mouseShapeExited: String = "mouse_shape_exited"
    }

    companion object {
        const val DISABLE_MODE_REMOVE: Long = 0L
        const val DISABLE_MODE_MAKE_STATIC: Long = 1L
        const val DISABLE_MODE_KEEP_ACTIVE: Long = 2L

        fun fromHandle(handle: MemorySegment): CollisionObject2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CollisionObject2D? =
            if (handle.address() == 0L) null else CollisionObject2D(handle)

        private const val SET_COLLISION_LAYER_HASH = 1286410249L
        private val setCollisionLayerBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "set_collision_layer", SET_COLLISION_LAYER_HASH)
        }

        private const val GET_COLLISION_LAYER_HASH = 3905245786L
        private val getCollisionLayerBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "get_collision_layer", GET_COLLISION_LAYER_HASH)
        }

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        private val setCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "set_collision_mask", SET_COLLISION_MASK_HASH)
        }

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        private val getCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "get_collision_mask", GET_COLLISION_MASK_HASH)
        }

        private const val SET_COLLISION_LAYER_VALUE_HASH = 300928843L
        private val setCollisionLayerValueBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "set_collision_layer_value", SET_COLLISION_LAYER_VALUE_HASH)
        }

        private const val GET_COLLISION_LAYER_VALUE_HASH = 1116898809L
        private val getCollisionLayerValueBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "get_collision_layer_value", GET_COLLISION_LAYER_VALUE_HASH)
        }

        private const val SET_COLLISION_MASK_VALUE_HASH = 300928843L
        private val setCollisionMaskValueBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "set_collision_mask_value", SET_COLLISION_MASK_VALUE_HASH)
        }

        private const val GET_COLLISION_MASK_VALUE_HASH = 1116898809L
        private val getCollisionMaskValueBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "get_collision_mask_value", GET_COLLISION_MASK_VALUE_HASH)
        }

        private const val SET_COLLISION_PRIORITY_HASH = 373806689L
        private val setCollisionPriorityBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "set_collision_priority", SET_COLLISION_PRIORITY_HASH)
        }

        private const val GET_COLLISION_PRIORITY_HASH = 1740695150L
        private val getCollisionPriorityBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "get_collision_priority", GET_COLLISION_PRIORITY_HASH)
        }

        private const val SET_DISABLE_MODE_HASH = 1919204045L
        private val setDisableModeBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "set_disable_mode", SET_DISABLE_MODE_HASH)
        }

        private const val GET_DISABLE_MODE_HASH = 3172846349L
        private val getDisableModeBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "get_disable_mode", GET_DISABLE_MODE_HASH)
        }

        private const val SET_PICKABLE_HASH = 2586408642L
        private val setPickableBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "set_pickable", SET_PICKABLE_HASH)
        }

        private const val IS_PICKABLE_HASH = 36873697L
        private val isPickableBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "is_pickable", IS_PICKABLE_HASH)
        }

        private const val CREATE_SHAPE_OWNER_HASH = 3429307534L
        private val createShapeOwnerBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "create_shape_owner", CREATE_SHAPE_OWNER_HASH)
        }

        private const val REMOVE_SHAPE_OWNER_HASH = 1286410249L
        private val removeShapeOwnerBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "remove_shape_owner", REMOVE_SHAPE_OWNER_HASH)
        }

        private const val SHAPE_OWNER_GET_OWNER_HASH = 3332903315L
        private val shapeOwnerGetOwnerBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_owner_get_owner", SHAPE_OWNER_GET_OWNER_HASH)
        }

        private const val SHAPE_OWNER_SET_DISABLED_HASH = 300928843L
        private val shapeOwnerSetDisabledBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_owner_set_disabled", SHAPE_OWNER_SET_DISABLED_HASH)
        }

        private const val IS_SHAPE_OWNER_DISABLED_HASH = 1116898809L
        private val isShapeOwnerDisabledBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "is_shape_owner_disabled", IS_SHAPE_OWNER_DISABLED_HASH)
        }

        private const val SHAPE_OWNER_SET_ONE_WAY_COLLISION_HASH = 300928843L
        private val shapeOwnerSetOneWayCollisionBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_owner_set_one_way_collision", SHAPE_OWNER_SET_ONE_WAY_COLLISION_HASH)
        }

        private const val IS_SHAPE_OWNER_ONE_WAY_COLLISION_ENABLED_HASH = 1116898809L
        private val isShapeOwnerOneWayCollisionEnabledBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "is_shape_owner_one_way_collision_enabled", IS_SHAPE_OWNER_ONE_WAY_COLLISION_ENABLED_HASH)
        }

        private const val SHAPE_OWNER_SET_ONE_WAY_COLLISION_MARGIN_HASH = 1602489585L
        private val shapeOwnerSetOneWayCollisionMarginBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_owner_set_one_way_collision_margin", SHAPE_OWNER_SET_ONE_WAY_COLLISION_MARGIN_HASH)
        }

        private const val GET_SHAPE_OWNER_ONE_WAY_COLLISION_MARGIN_HASH = 2339986948L
        private val getShapeOwnerOneWayCollisionMarginBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "get_shape_owner_one_way_collision_margin", GET_SHAPE_OWNER_ONE_WAY_COLLISION_MARGIN_HASH)
        }

        private const val SHAPE_OWNER_GET_SHAPE_COUNT_HASH = 923996154L
        private val shapeOwnerGetShapeCountBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_owner_get_shape_count", SHAPE_OWNER_GET_SHAPE_COUNT_HASH)
        }

        private const val SHAPE_OWNER_GET_SHAPE_INDEX_HASH = 3175239445L
        private val shapeOwnerGetShapeIndexBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_owner_get_shape_index", SHAPE_OWNER_GET_SHAPE_INDEX_HASH)
        }

        private const val SHAPE_OWNER_REMOVE_SHAPE_HASH = 3937882851L
        private val shapeOwnerRemoveShapeBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_owner_remove_shape", SHAPE_OWNER_REMOVE_SHAPE_HASH)
        }

        private const val SHAPE_OWNER_CLEAR_SHAPES_HASH = 1286410249L
        private val shapeOwnerClearShapesBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_owner_clear_shapes", SHAPE_OWNER_CLEAR_SHAPES_HASH)
        }

        private const val SHAPE_FIND_OWNER_HASH = 923996154L
        private val shapeFindOwnerBind by lazy {
            ObjectCalls.getMethodBind("CollisionObject2D", "shape_find_owner", SHAPE_FIND_OWNER_HASH)
        }
    }
}
