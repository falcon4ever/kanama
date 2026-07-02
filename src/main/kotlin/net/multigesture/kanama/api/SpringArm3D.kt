package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

/**
 * A 3D raycast that dynamically moves its children near the collision point.
 *
 * Generated from Godot docs: SpringArm3D
 */
class SpringArm3D(handle: MemorySegment) : Node3D(handle) {
    var collisionMask: Long
        @JvmName("collisionMaskProperty")
        get() = getCollisionMask()
        @JvmName("setCollisionMaskProperty")
        set(value) = setCollisionMask(value)

    var shape: Shape3D?
        @JvmName("shapeProperty")
        get() = getShape()
        @JvmName("setShapeProperty")
        set(value) = setShape(value)

    var springLength: Double
        @JvmName("springLengthProperty")
        get() = getLength()
        @JvmName("setSpringLengthProperty")
        set(value) = setLength(value)

    var margin: Double
        @JvmName("marginProperty")
        get() = getMargin()
        @JvmName("setMarginProperty")
        set(value) = setMargin(value)

    fun getHitLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHitLengthBind, handle)
    }

    fun setLength(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLengthBind, handle, length)
    }

    fun getLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLengthBind, handle)
    }

    fun setShape(shape: Shape3D?) {
        ObjectCalls.ptrcallWithObjectArgs(setShapeBind, handle, listOf(shape?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getShape(): Shape3D? {
        return Shape3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getShapeBind, handle))
    }

    fun addExcludedObject(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(addExcludedObjectBind, handle, rid)
    }

    fun removeExcludedObject(rid: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(removeExcludedObjectBind, handle, rid)
    }

    fun clearExcludedObjects() {
        ObjectCalls.ptrcallNoArgs(clearExcludedObjectsBind, handle)
    }

    fun setCollisionMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionMaskBind, handle, mask)
    }

    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionMaskBind, handle)
    }

    fun setMargin(margin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMarginBind, handle, margin)
    }

    fun getMargin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMarginBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SpringArm3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SpringArm3D? =
            if (handle.address() == 0L) null else SpringArm3D(handle)

        private const val GET_HIT_LENGTH_HASH = 191475506L
        private val getHitLengthBind by lazy {
            ObjectCalls.getMethodBind("SpringArm3D", "get_hit_length", GET_HIT_LENGTH_HASH)
        }

        private const val SET_LENGTH_HASH = 373806689L
        private val setLengthBind by lazy {
            ObjectCalls.getMethodBind("SpringArm3D", "set_length", SET_LENGTH_HASH)
        }

        private const val GET_LENGTH_HASH = 1740695150L
        private val getLengthBind by lazy {
            ObjectCalls.getMethodBind("SpringArm3D", "get_length", GET_LENGTH_HASH)
        }

        private const val SET_SHAPE_HASH = 1549710052L
        private val setShapeBind by lazy {
            ObjectCalls.getMethodBind("SpringArm3D", "set_shape", SET_SHAPE_HASH)
        }

        private const val GET_SHAPE_HASH = 3214262478L
        private val getShapeBind by lazy {
            ObjectCalls.getMethodBind("SpringArm3D", "get_shape", GET_SHAPE_HASH)
        }

        private const val ADD_EXCLUDED_OBJECT_HASH = 2722037293L
        private val addExcludedObjectBind by lazy {
            ObjectCalls.getMethodBind("SpringArm3D", "add_excluded_object", ADD_EXCLUDED_OBJECT_HASH)
        }

        private const val REMOVE_EXCLUDED_OBJECT_HASH = 3521089500L
        private val removeExcludedObjectBind by lazy {
            ObjectCalls.getMethodBind("SpringArm3D", "remove_excluded_object", REMOVE_EXCLUDED_OBJECT_HASH)
        }

        private const val CLEAR_EXCLUDED_OBJECTS_HASH = 3218959716L
        private val clearExcludedObjectsBind by lazy {
            ObjectCalls.getMethodBind("SpringArm3D", "clear_excluded_objects", CLEAR_EXCLUDED_OBJECTS_HASH)
        }

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        private val setCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("SpringArm3D", "set_collision_mask", SET_COLLISION_MASK_HASH)
        }

        private const val GET_COLLISION_MASK_HASH = 2455072627L
        private val getCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("SpringArm3D", "get_collision_mask", GET_COLLISION_MASK_HASH)
        }

        private const val SET_MARGIN_HASH = 373806689L
        private val setMarginBind by lazy {
            ObjectCalls.getMethodBind("SpringArm3D", "set_margin", SET_MARGIN_HASH)
        }

        private const val GET_MARGIN_HASH = 191475506L
        private val getMarginBind by lazy {
            ObjectCalls.getMethodBind("SpringArm3D", "get_margin", GET_MARGIN_HASH)
        }
    }
}
