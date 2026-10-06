package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: GLTFPhysicsShape
 */
class GLTFPhysicsShape(handle: GodotHandle) : Resource(handle) {
    var shapeType: String
        @JvmName("shapeTypeProperty")
        get() = getShapeType()
        @JvmName("setShapeTypeProperty")
        set(value) = setShapeType(value)

    var size: Vector3
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    var radius: Double
        @JvmName("radiusProperty")
        get() = getRadius()
        @JvmName("setRadiusProperty")
        set(value) = setRadius(value)

    var height: Double
        @JvmName("heightProperty")
        get() = getHeight()
        @JvmName("setHeightProperty")
        set(value) = setHeight(value)

    var isTrigger: Boolean
        @JvmName("isTriggerProperty")
        get() = getIsTrigger()
        @JvmName("setIsTriggerProperty")
        set(value) = setIsTrigger(value)

    var meshIndex: Int
        @JvmName("meshIndexProperty")
        get() = getMeshIndex()
        @JvmName("setMeshIndexProperty")
        set(value) = setMeshIndex(value)

    var importerMesh: ImporterMesh?
        @JvmName("importerMeshProperty")
        get() = getImporterMesh()
        @JvmName("setImporterMeshProperty")
        set(value) = setImporterMesh(value)

    fun toNode(cacheShapes: Boolean = false): CollisionShape3D? {
        checkOpen()
        return CollisionShape3D.wrap(ObjectCalls.ptrcallWithBoolArgRetObject(Binds.toNodeBind, segment, cacheShapes))
    }

    fun toResource(cacheShapes: Boolean = false): Shape3D? {
        checkOpen()
        return Shape3D.wrapOwned(ObjectCalls.ptrcallWithBoolArgRetObject(Binds.toResourceBind, segment, cacheShapes))
    }

    fun toDictionary(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(Binds.toDictionaryBind, segment)
    }

    fun getShapeType(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getShapeTypeBind, segment)
    }

    fun setShapeType(shapeType: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setShapeTypeBind, segment, shapeType)
    }

    fun getSize(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getSizeBind, segment)
    }

    fun setSize(size: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setSizeBind, segment, size)
    }

    fun getRadius(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRadiusBind, segment)
    }

    fun setRadius(radius: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRadiusBind, segment, radius)
    }

    fun getHeight(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getHeightBind, segment)
    }

    fun setHeight(height: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setHeightBind, segment, height)
    }

    fun getIsTrigger(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getIsTriggerBind, segment)
    }

    fun setIsTrigger(isTrigger: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setIsTriggerBind, segment, isTrigger)
    }

    fun getMeshIndex(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMeshIndexBind, segment)
    }

    fun setMeshIndex(meshIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMeshIndexBind, segment, meshIndex)
    }

    fun getImporterMesh(): ImporterMesh? {
        checkOpen()
        return ImporterMesh.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getImporterMeshBind, segment))
    }

    fun setImporterMesh(importerMesh: ImporterMesh?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setImporterMeshBind, segment, listOf(importerMesh?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    companion object {
        fun fromNode(shapeNode: CollisionShape3D): GLTFPhysicsShape? {
            return GLTFPhysicsShape.wrapOwned(ObjectCalls.ptrcallWithObjectArgRetObject(Binds.fromNodeBind, NULL_SEGMENT, shapeNode.segment))
        }

        fun fromResource(shapeResource: Shape3D?): GLTFPhysicsShape? {
            return GLTFPhysicsShape.wrapOwned(ObjectCalls.ptrcallWithObjectArgRetObject(Binds.fromResourceBind, NULL_SEGMENT, shapeResource?.requireOpenHandle() ?: NULL_SEGMENT))
        }

        fun fromDictionary(dictionary: Map<String, Any?>): GLTFPhysicsShape? {
            return GLTFPhysicsShape.wrapOwned(ObjectCalls.ptrcallWithDictionaryArgRetObject(Binds.fromDictionaryBind, NULL_SEGMENT, dictionary))
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFPhysicsShape? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GLTFPhysicsShape? =
            if (handle.address() == 0L) null else RefCounted.owned(GLTFPhysicsShape(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GLTFPhysicsShape? =
            if (handle.address() == 0L) null else GLTFPhysicsShape(GodotHandle(handle))
    }

    private object Binds {
        private const val FROM_NODE_HASH = 3613751275L
        @JvmField
        val fromNodeBind =
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "from_node", FROM_NODE_HASH)

        private const val TO_NODE_HASH = 563689933L
        @JvmField
        val toNodeBind =
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "to_node", TO_NODE_HASH)

        private const val FROM_RESOURCE_HASH = 3845569786L
        @JvmField
        val fromResourceBind =
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "from_resource", FROM_RESOURCE_HASH)

        private const val TO_RESOURCE_HASH = 1913542110L
        @JvmField
        val toResourceBind =
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "to_resource", TO_RESOURCE_HASH)

        private const val FROM_DICTIONARY_HASH = 2390691823L
        @JvmField
        val fromDictionaryBind =
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "from_dictionary", FROM_DICTIONARY_HASH)

        private const val TO_DICTIONARY_HASH = 3102165223L
        @JvmField
        val toDictionaryBind =
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "to_dictionary", TO_DICTIONARY_HASH)

        private const val GET_SHAPE_TYPE_HASH = 201670096L
        @JvmField
        val getShapeTypeBind =
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "get_shape_type", GET_SHAPE_TYPE_HASH)

        private const val SET_SHAPE_TYPE_HASH = 83702148L
        @JvmField
        val setShapeTypeBind =
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "set_shape_type", SET_SHAPE_TYPE_HASH)

        private const val GET_SIZE_HASH = 3360562783L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "get_size", GET_SIZE_HASH)

        private const val SET_SIZE_HASH = 3460891852L
        @JvmField
        val setSizeBind =
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "set_size", SET_SIZE_HASH)

        private const val GET_RADIUS_HASH = 1740695150L
        @JvmField
        val getRadiusBind =
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "get_radius", GET_RADIUS_HASH)

        private const val SET_RADIUS_HASH = 373806689L
        @JvmField
        val setRadiusBind =
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "set_radius", SET_RADIUS_HASH)

        private const val GET_HEIGHT_HASH = 1740695150L
        @JvmField
        val getHeightBind =
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "get_height", GET_HEIGHT_HASH)

        private const val SET_HEIGHT_HASH = 373806689L
        @JvmField
        val setHeightBind =
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "set_height", SET_HEIGHT_HASH)

        private const val GET_IS_TRIGGER_HASH = 36873697L
        @JvmField
        val getIsTriggerBind =
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "get_is_trigger", GET_IS_TRIGGER_HASH)

        private const val SET_IS_TRIGGER_HASH = 2586408642L
        @JvmField
        val setIsTriggerBind =
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "set_is_trigger", SET_IS_TRIGGER_HASH)

        private const val GET_MESH_INDEX_HASH = 3905245786L
        @JvmField
        val getMeshIndexBind =
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "get_mesh_index", GET_MESH_INDEX_HASH)

        private const val SET_MESH_INDEX_HASH = 1286410249L
        @JvmField
        val setMeshIndexBind =
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "set_mesh_index", SET_MESH_INDEX_HASH)

        private const val GET_IMPORTER_MESH_HASH = 3161779525L
        @JvmField
        val getImporterMeshBind =
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "get_importer_mesh", GET_IMPORTER_MESH_HASH)

        private const val SET_IMPORTER_MESH_HASH = 2255166972L
        @JvmField
        val setImporterMeshBind =
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "set_importer_mesh", SET_IMPORTER_MESH_HASH)
    }
}
