package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: CSGShape3D
 */
open class CSGShape3D(handle: GodotHandle) : GeometryInstance3D(handle) {
    var autosmooth: Boolean
        @JvmName("autosmoothProperty")
        get() = isAutosmooth()
        @JvmName("setAutosmoothProperty")
        set(value) = setAutosmooth(value)

    var smoothingAngle: Double
        @JvmName("smoothingAngleProperty")
        get() = getSmoothingAngle()
        @JvmName("setSmoothingAngleProperty")
        set(value) = setSmoothingAngle(value)

    var operation: CSGShape3D.Operation
        @JvmName("operationProperty")
        get() = getOperation()
        @JvmName("setOperationProperty")
        set(value) = setOperation(value)

    var snap: Double
        @JvmName("snapProperty")
        get() = getSnap()
        @JvmName("setSnapProperty")
        set(value) = setSnap(value)

    var calculateTangents: Boolean
        @JvmName("calculateTangentsProperty")
        get() = isCalculatingTangents()
        @JvmName("setCalculateTangentsProperty")
        set(value) = setCalculateTangents(value)

    var useCollision: Boolean
        @JvmName("useCollisionProperty")
        get() = isUsingCollision()
        @JvmName("setUseCollisionProperty")
        set(value) = setUseCollision(value)

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

    fun isRootShape(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isRootShapeBind, segment)
    }

    fun setOperation(operation: CSGShape3D.Operation) {
        ObjectCalls.ptrcallWithLongArg(Binds.setOperationBind, segment, operation.value)
    }

    fun getOperation(): CSGShape3D.Operation {
        return CSGShape3D.Operation(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOperationBind, segment))
    }

    fun setSnap(snap: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSnapBind, segment, snap)
    }

    fun getSnap(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSnapBind, segment)
    }

    fun setUseCollision(operation: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseCollisionBind, segment, operation)
    }

    fun isUsingCollision(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUsingCollisionBind, segment)
    }

    fun setCollisionLayer(layer: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCollisionLayerBind, segment, layer)
    }

    fun getCollisionLayer(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getCollisionLayerBind, segment)
    }

    fun setCollisionMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCollisionMaskBind, segment, mask)
    }

    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getCollisionMaskBind, segment)
    }

    fun setCollisionMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setCollisionMaskValueBind, segment, layerNumber, value)
    }

    fun getCollisionMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getCollisionMaskValueBind, segment, layerNumber)
    }

    fun setCollisionLayerValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setCollisionLayerValueBind, segment, layerNumber, value)
    }

    fun getCollisionLayerValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getCollisionLayerValueBind, segment, layerNumber)
    }

    fun setCollisionPriority(priority: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setCollisionPriorityBind, segment, priority)
    }

    fun getCollisionPriority(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCollisionPriorityBind, segment)
    }

    fun bakeCollisionShape(): ConcavePolygonShape3D? {
        return ConcavePolygonShape3D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.bakeCollisionShapeBind, segment))
    }

    fun setCalculateTangents(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCalculateTangentsBind, segment, enabled)
    }

    fun isCalculatingTangents(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCalculatingTangentsBind, segment)
    }

    fun getMeshes(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getMeshesBind, segment)
    }

    fun bakeStaticMesh(): ArrayMesh? {
        return ArrayMesh.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.bakeStaticMeshBind, segment))
    }

    fun setAutosmooth(autosmooth: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAutosmoothBind, segment, autosmooth)
    }

    fun isAutosmooth(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAutosmoothBind, segment)
    }

    fun setSmoothingAngle(smoothingAngle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSmoothingAngleBind, segment, smoothingAngle)
    }

    fun getSmoothingAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSmoothingAngleBind, segment)
    }

    @JvmInline
    value class Operation(override val value: Long) : GodotEnumValue {
        companion object {
            val UNION: Operation get() = Operation(0L)
            val INTERSECTION: Operation get() = Operation(1L)
            val SUBTRACTION: Operation get() = Operation(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CSGShape3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CSGShape3D? =
            if (handle.address() == 0L) null else CSGShape3D(GodotHandle(handle))
    }

    private object Binds {
        private const val IS_ROOT_SHAPE_HASH = 36873697L
        @JvmField
        val isRootShapeBind =
            ObjectCalls.getMethodBind("CSGShape3D", "is_root_shape", IS_ROOT_SHAPE_HASH)

        private const val SET_OPERATION_HASH = 811425055L
        @JvmField
        val setOperationBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_operation", SET_OPERATION_HASH)

        private const val GET_OPERATION_HASH = 2662425879L
        @JvmField
        val getOperationBind =
            ObjectCalls.getMethodBind("CSGShape3D", "get_operation", GET_OPERATION_HASH)

        private const val SET_SNAP_HASH = 373806689L
        @JvmField
        val setSnapBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_snap", SET_SNAP_HASH)

        private const val GET_SNAP_HASH = 1740695150L
        @JvmField
        val getSnapBind =
            ObjectCalls.getMethodBind("CSGShape3D", "get_snap", GET_SNAP_HASH)

        private const val SET_USE_COLLISION_HASH = 2586408642L
        @JvmField
        val setUseCollisionBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_use_collision", SET_USE_COLLISION_HASH)

        private const val IS_USING_COLLISION_HASH = 36873697L
        @JvmField
        val isUsingCollisionBind =
            ObjectCalls.getMethodBind("CSGShape3D", "is_using_collision", IS_USING_COLLISION_HASH)

        private const val SET_COLLISION_LAYER_HASH = 1286410249L
        @JvmField
        val setCollisionLayerBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_collision_layer", SET_COLLISION_LAYER_HASH)

        private const val GET_COLLISION_LAYER_HASH = 3905245786L
        @JvmField
        val getCollisionLayerBind =
            ObjectCalls.getMethodBind("CSGShape3D", "get_collision_layer", GET_COLLISION_LAYER_HASH)

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        @JvmField
        val setCollisionMaskBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_collision_mask", SET_COLLISION_MASK_HASH)

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        @JvmField
        val getCollisionMaskBind =
            ObjectCalls.getMethodBind("CSGShape3D", "get_collision_mask", GET_COLLISION_MASK_HASH)

        private const val SET_COLLISION_MASK_VALUE_HASH = 300928843L
        @JvmField
        val setCollisionMaskValueBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_collision_mask_value", SET_COLLISION_MASK_VALUE_HASH)

        private const val GET_COLLISION_MASK_VALUE_HASH = 1116898809L
        @JvmField
        val getCollisionMaskValueBind =
            ObjectCalls.getMethodBind("CSGShape3D", "get_collision_mask_value", GET_COLLISION_MASK_VALUE_HASH)

        private const val SET_COLLISION_LAYER_VALUE_HASH = 300928843L
        @JvmField
        val setCollisionLayerValueBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_collision_layer_value", SET_COLLISION_LAYER_VALUE_HASH)

        private const val GET_COLLISION_LAYER_VALUE_HASH = 1116898809L
        @JvmField
        val getCollisionLayerValueBind =
            ObjectCalls.getMethodBind("CSGShape3D", "get_collision_layer_value", GET_COLLISION_LAYER_VALUE_HASH)

        private const val SET_COLLISION_PRIORITY_HASH = 373806689L
        @JvmField
        val setCollisionPriorityBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_collision_priority", SET_COLLISION_PRIORITY_HASH)

        private const val GET_COLLISION_PRIORITY_HASH = 1740695150L
        @JvmField
        val getCollisionPriorityBind =
            ObjectCalls.getMethodBind("CSGShape3D", "get_collision_priority", GET_COLLISION_PRIORITY_HASH)

        private const val BAKE_COLLISION_SHAPE_HASH = 36102322L
        @JvmField
        val bakeCollisionShapeBind =
            ObjectCalls.getMethodBind("CSGShape3D", "bake_collision_shape", BAKE_COLLISION_SHAPE_HASH)

        private const val SET_CALCULATE_TANGENTS_HASH = 2586408642L
        @JvmField
        val setCalculateTangentsBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_calculate_tangents", SET_CALCULATE_TANGENTS_HASH)

        private const val IS_CALCULATING_TANGENTS_HASH = 36873697L
        @JvmField
        val isCalculatingTangentsBind =
            ObjectCalls.getMethodBind("CSGShape3D", "is_calculating_tangents", IS_CALCULATING_TANGENTS_HASH)

        private const val GET_MESHES_HASH = 3995934104L
        @JvmField
        val getMeshesBind =
            ObjectCalls.getMethodBind("CSGShape3D", "get_meshes", GET_MESHES_HASH)

        private const val BAKE_STATIC_MESH_HASH = 1605880883L
        @JvmField
        val bakeStaticMeshBind =
            ObjectCalls.getMethodBind("CSGShape3D", "bake_static_mesh", BAKE_STATIC_MESH_HASH)

        private const val SET_AUTOSMOOTH_HASH = 2586408642L
        @JvmField
        val setAutosmoothBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_autosmooth", SET_AUTOSMOOTH_HASH)

        private const val IS_AUTOSMOOTH_HASH = 36873697L
        @JvmField
        val isAutosmoothBind =
            ObjectCalls.getMethodBind("CSGShape3D", "is_autosmooth", IS_AUTOSMOOTH_HASH)

        private const val SET_SMOOTHING_ANGLE_HASH = 373806689L
        @JvmField
        val setSmoothingAngleBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_smoothing_angle", SET_SMOOTHING_ANGLE_HASH)

        private const val GET_SMOOTHING_ANGLE_HASH = 1740695150L
        @JvmField
        val getSmoothingAngleBind =
            ObjectCalls.getMethodBind("CSGShape3D", "get_smoothing_angle", GET_SMOOTHING_ANGLE_HASH)
    }
}
