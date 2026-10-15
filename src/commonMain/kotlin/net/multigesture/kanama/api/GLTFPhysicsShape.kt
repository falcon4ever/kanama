package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector3

/**
 * Represents a glTF physics shape.
 *
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

    /**
     * Converts this GLTFPhysicsShape instance into a Godot `CollisionShape3D` node.
     *
     * Generated from Godot docs: GLTFPhysicsShape.to_node
     */
    fun toNode(cacheShapes: Boolean = false): CollisionShape3D? {
        checkOpen()
        val ret = CollisionShape3D.wrap(ObjectCalls.ptrcallWithBoolArgRetObject(Binds.toNodeBind, segment, cacheShapes))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Converts this GLTFPhysicsShape instance into a Godot `Shape3D` resource.
     *
     * Generated from Godot docs: GLTFPhysicsShape.to_resource
     */
    fun toResource(cacheShapes: Boolean = false): Shape3D? {
        checkOpen()
        val ret = Shape3D.wrapOwned(ObjectCalls.ptrcallWithBoolArgRetObject(Binds.toResourceBind, segment, cacheShapes))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Serializes this GLTFPhysicsShape instance into a `Dictionary` in the format defined by
     * `OMI_physics_shape`.
     *
     * Generated from Godot docs: GLTFPhysicsShape.to_dictionary
     */
    fun toDictionary(): Map<String, Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDictionary(Binds.toDictionaryBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The type of shape this shape represents. Valid values are `"box"`, `"capsule"`, `"cylinder"`,
     * `"sphere"`, `"hull"`, and `"trimesh"`.
     *
     * Generated from Godot docs: GLTFPhysicsShape.get_shape_type
     */
    fun getShapeType(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getShapeTypeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The type of shape this shape represents. Valid values are `"box"`, `"capsule"`, `"cylinder"`,
     * `"sphere"`, `"hull"`, and `"trimesh"`.
     *
     * Generated from Godot docs: GLTFPhysicsShape.set_shape_type
     */
    fun setShapeType(shapeType: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setShapeTypeBind, segment, shapeType)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The size of the shape, in meters. This is only used when the shape type is `"box"`, and it
     * represents the `"diameter"` of the box. This value should not be negative.
     *
     * Generated from Godot docs: GLTFPhysicsShape.get_size
     */
    fun getSize(): Vector3 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector3(Binds.getSizeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The size of the shape, in meters. This is only used when the shape type is `"box"`, and it
     * represents the `"diameter"` of the box. This value should not be negative.
     *
     * Generated from Godot docs: GLTFPhysicsShape.set_size
     */
    fun setSize(size: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setSizeBind, segment, size)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The radius of the shape, in meters. This is only used when the shape type is `"capsule"`,
     * `"cylinder"`, or `"sphere"`. This value should not be negative.
     *
     * Generated from Godot docs: GLTFPhysicsShape.get_radius
     */
    fun getRadius(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRadiusBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The radius of the shape, in meters. This is only used when the shape type is `"capsule"`,
     * `"cylinder"`, or `"sphere"`. This value should not be negative.
     *
     * Generated from Godot docs: GLTFPhysicsShape.set_radius
     */
    fun setRadius(radius: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRadiusBind, segment, radius)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The height of the shape, in meters. This is only used when the shape type is `"capsule"` or
     * `"cylinder"`. This value should not be negative, and for `"capsule"` it should be at least twice
     * the radius.
     *
     * Generated from Godot docs: GLTFPhysicsShape.get_height
     */
    fun getHeight(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getHeightBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The height of the shape, in meters. This is only used when the shape type is `"capsule"` or
     * `"cylinder"`. This value should not be negative, and for `"capsule"` it should be at least twice
     * the radius.
     *
     * Generated from Godot docs: GLTFPhysicsShape.set_height
     */
    fun setHeight(height: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setHeightBind, segment, height)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If `true`, indicates that this shape is a trigger. For Godot, this means that the shape should
     * be a child of an `Area3D` node. This is the only variable not used in the `to_node` method, it's
     * intended to be used alongside when deciding where to add the generated node as a child.
     *
     * Generated from Godot docs: GLTFPhysicsShape.get_is_trigger
     */
    fun getIsTrigger(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.getIsTriggerBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `true`, indicates that this shape is a trigger. For Godot, this means that the shape should
     * be a child of an `Area3D` node. This is the only variable not used in the `to_node` method, it's
     * intended to be used alongside when deciding where to add the generated node as a child.
     *
     * Generated from Godot docs: GLTFPhysicsShape.set_is_trigger
     */
    fun setIsTrigger(isTrigger: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setIsTriggerBind, segment, isTrigger)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The index of the shape's mesh in the glTF file. This is only used when the shape type is
     * `"hull"` (convex hull) or `"trimesh"` (concave trimesh).
     *
     * Generated from Godot docs: GLTFPhysicsShape.get_mesh_index
     */
    fun getMeshIndex(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getMeshIndexBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The index of the shape's mesh in the glTF file. This is only used when the shape type is
     * `"hull"` (convex hull) or `"trimesh"` (concave trimesh).
     *
     * Generated from Godot docs: GLTFPhysicsShape.set_mesh_index
     */
    fun setMeshIndex(meshIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMeshIndexBind, segment, meshIndex)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The `ImporterMesh` resource of the shape. This is only used when the shape type is `"hull"`
     * (convex hull) or `"trimesh"` (concave trimesh).
     *
     * Generated from Godot docs: GLTFPhysicsShape.get_importer_mesh
     */
    fun getImporterMesh(): ImporterMesh? {
        checkOpen()
        val ret = ImporterMesh.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getImporterMeshBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The `ImporterMesh` resource of the shape. This is only used when the shape type is `"hull"`
     * (convex hull) or `"trimesh"` (concave trimesh).
     *
     * Generated from Godot docs: GLTFPhysicsShape.set_importer_mesh
     */
    fun setImporterMesh(importerMesh: ImporterMesh?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setImporterMeshBind, segment, listOf(importerMesh?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(importerMesh)
    }

    companion object {
        /**
         * Creates a new GLTFPhysicsShape instance from the given Godot `CollisionShape3D` node.
         *
         * Generated from Godot docs: GLTFPhysicsShape.from_node
         */
        fun fromNode(shapeNode: CollisionShape3D): GLTFPhysicsShape? {
            val ret = GLTFPhysicsShape.wrapOwned(ObjectCalls.ptrcallWithObjectArgRetObject(Binds.fromNodeBind, NULL_SEGMENT, shapeNode.segment))
            ObjectCalls.keepReachable(shapeNode)
            return ret
        }

        /**
         * Creates a new GLTFPhysicsShape instance from the given Godot `Shape3D` resource.
         *
         * Generated from Godot docs: GLTFPhysicsShape.from_resource
         */
        fun fromResource(shapeResource: Shape3D?): GLTFPhysicsShape? {
            val ret = GLTFPhysicsShape.wrapOwned(ObjectCalls.ptrcallWithObjectArgRetObject(Binds.fromResourceBind, NULL_SEGMENT, shapeResource?.requireOpenHandle() ?: NULL_SEGMENT))
            ObjectCalls.keepReachable(shapeResource)
            return ret
        }

        /**
         * Creates a new GLTFPhysicsShape instance by parsing the given `Dictionary`.
         *
         * Generated from Godot docs: GLTFPhysicsShape.from_dictionary
         */
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
