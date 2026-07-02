package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: GLTFPhysicsShape
 */
class GLTFPhysicsShape(handle: MemorySegment) : Resource(handle) {
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
        return CollisionShape3D.wrap(ObjectCalls.ptrcallWithBoolArgRetObject(toNodeBind, handle, cacheShapes))
    }

    fun toResource(cacheShapes: Boolean = false): Shape3D? {
        return Shape3D.wrap(ObjectCalls.ptrcallWithBoolArgRetObject(toResourceBind, handle, cacheShapes))
    }

    fun toDictionary(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(toDictionaryBind, handle)
    }

    fun getShapeType(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getShapeTypeBind, handle)
    }

    fun setShapeType(shapeType: String) {
        ObjectCalls.ptrcallWithStringArg(setShapeTypeBind, handle, shapeType)
    }

    fun getSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getSizeBind, handle)
    }

    fun setSize(size: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setSizeBind, handle, size)
    }

    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, handle)
    }

    fun setRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, handle, radius)
    }

    fun getHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHeightBind, handle)
    }

    fun setHeight(height: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHeightBind, handle, height)
    }

    fun getIsTrigger(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getIsTriggerBind, handle)
    }

    fun setIsTrigger(isTrigger: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIsTriggerBind, handle, isTrigger)
    }

    fun getMeshIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMeshIndexBind, handle)
    }

    fun setMeshIndex(meshIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(setMeshIndexBind, handle, meshIndex)
    }

    fun getImporterMesh(): ImporterMesh? {
        return ImporterMesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(getImporterMeshBind, handle))
    }

    fun setImporterMesh(importerMesh: ImporterMesh?) {
        ObjectCalls.ptrcallWithObjectArgs(setImporterMeshBind, handle, listOf(importerMesh?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    companion object {
        fun fromNode(shapeNode: CollisionShape3D): GLTFPhysicsShape? {
            return GLTFPhysicsShape.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(fromNodeBind, MemorySegment.NULL, shapeNode.handle))
        }

        fun fromResource(shapeResource: Shape3D?): GLTFPhysicsShape? {
            return GLTFPhysicsShape.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(fromResourceBind, MemorySegment.NULL, shapeResource?.requireOpenHandle() ?: MemorySegment.NULL))
        }

        fun fromDictionary(dictionary: Map<String, Any?>): GLTFPhysicsShape? {
            return GLTFPhysicsShape.wrap(ObjectCalls.ptrcallWithDictionaryArgRetObject(fromDictionaryBind, MemorySegment.NULL, dictionary))
        }

        @JvmStatic
        fun fromHandle(handle: MemorySegment): GLTFPhysicsShape? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GLTFPhysicsShape? =
            if (handle.address() == 0L) null else GLTFPhysicsShape(handle)

        private const val FROM_NODE_HASH = 3613751275L
        private val fromNodeBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "from_node", FROM_NODE_HASH)
        }

        private const val TO_NODE_HASH = 563689933L
        private val toNodeBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "to_node", TO_NODE_HASH)
        }

        private const val FROM_RESOURCE_HASH = 3845569786L
        private val fromResourceBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "from_resource", FROM_RESOURCE_HASH)
        }

        private const val TO_RESOURCE_HASH = 1913542110L
        private val toResourceBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "to_resource", TO_RESOURCE_HASH)
        }

        private const val FROM_DICTIONARY_HASH = 2390691823L
        private val fromDictionaryBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "from_dictionary", FROM_DICTIONARY_HASH)
        }

        private const val TO_DICTIONARY_HASH = 3102165223L
        private val toDictionaryBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "to_dictionary", TO_DICTIONARY_HASH)
        }

        private const val GET_SHAPE_TYPE_HASH = 201670096L
        private val getShapeTypeBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "get_shape_type", GET_SHAPE_TYPE_HASH)
        }

        private const val SET_SHAPE_TYPE_HASH = 83702148L
        private val setShapeTypeBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "set_shape_type", SET_SHAPE_TYPE_HASH)
        }

        private const val GET_SIZE_HASH = 3360562783L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "get_size", GET_SIZE_HASH)
        }

        private const val SET_SIZE_HASH = 3460891852L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "set_size", SET_SIZE_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "get_radius", GET_RADIUS_HASH)
        }

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_HEIGHT_HASH = 1740695150L
        private val getHeightBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "get_height", GET_HEIGHT_HASH)
        }

        private const val SET_HEIGHT_HASH = 373806689L
        private val setHeightBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "set_height", SET_HEIGHT_HASH)
        }

        private const val GET_IS_TRIGGER_HASH = 36873697L
        private val getIsTriggerBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "get_is_trigger", GET_IS_TRIGGER_HASH)
        }

        private const val SET_IS_TRIGGER_HASH = 2586408642L
        private val setIsTriggerBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "set_is_trigger", SET_IS_TRIGGER_HASH)
        }

        private const val GET_MESH_INDEX_HASH = 3905245786L
        private val getMeshIndexBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "get_mesh_index", GET_MESH_INDEX_HASH)
        }

        private const val SET_MESH_INDEX_HASH = 1286410249L
        private val setMeshIndexBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "set_mesh_index", SET_MESH_INDEX_HASH)
        }

        private const val GET_IMPORTER_MESH_HASH = 3161779525L
        private val getImporterMeshBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "get_importer_mesh", GET_IMPORTER_MESH_HASH)
        }

        private const val SET_IMPORTER_MESH_HASH = 2255166972L
        private val setImporterMeshBind by lazy {
            ObjectCalls.getMethodBind("GLTFPhysicsShape", "set_importer_mesh", SET_IMPORTER_MESH_HASH)
        }
    }
}
