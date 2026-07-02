package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: GLTFNode
 */
class GLTFNode(handle: MemorySegment) : Resource(handle) {
    var originalName: String
        @JvmName("originalNameProperty")
        get() = getOriginalName()
        @JvmName("setOriginalNameProperty")
        set(value) = setOriginalName(value)

    var parent: Int
        @JvmName("parentProperty")
        get() = getParent()
        @JvmName("setParentProperty")
        set(value) = setParent(value)

    var height: Int
        @JvmName("heightProperty")
        get() = getHeight()
        @JvmName("setHeightProperty")
        set(value) = setHeight(value)

    var xform: Transform3D
        @JvmName("xformProperty")
        get() = getXform()
        @JvmName("setXformProperty")
        set(value) = setXform(value)

    var mesh: Int
        @JvmName("meshProperty")
        get() = getMesh()
        @JvmName("setMeshProperty")
        set(value) = setMesh(value)

    var camera: Int
        @JvmName("cameraProperty")
        get() = getCamera()
        @JvmName("setCameraProperty")
        set(value) = setCamera(value)

    var skin: Int
        @JvmName("skinProperty")
        get() = getSkin()
        @JvmName("setSkinProperty")
        set(value) = setSkin(value)

    var skeleton: Int
        @JvmName("skeletonProperty")
        get() = getSkeleton()
        @JvmName("setSkeletonProperty")
        set(value) = setSkeleton(value)

    var position: Vector3
        @JvmName("positionProperty")
        get() = getPosition()
        @JvmName("setPositionProperty")
        set(value) = setPosition(value)

    var rotation: Quaternion
        @JvmName("rotationProperty")
        get() = getRotation()
        @JvmName("setRotationProperty")
        set(value) = setRotation(value)

    var scale: Vector3
        @JvmName("scaleProperty")
        get() = getScale()
        @JvmName("setScaleProperty")
        set(value) = setScale(value)

    var children: List<Int>
        @JvmName("childrenProperty")
        get() = getChildren()
        @JvmName("setChildrenProperty")
        set(value) = setChildren(value)

    var light: Int
        @JvmName("lightProperty")
        get() = getLight()
        @JvmName("setLightProperty")
        set(value) = setLight(value)

    var visible: Boolean
        @JvmName("visibleProperty")
        get() = getVisible()
        @JvmName("setVisibleProperty")
        set(value) = setVisible(value)

    fun getOriginalName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getOriginalNameBind, handle)
    }

    fun setOriginalName(originalName: String) {
        ObjectCalls.ptrcallWithStringArg(setOriginalNameBind, handle, originalName)
    }

    fun getParent(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getParentBind, handle)
    }

    fun setParent(parent: Int) {
        ObjectCalls.ptrcallWithIntArg(setParentBind, handle, parent)
    }

    fun getHeight(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getHeightBind, handle)
    }

    fun setHeight(height: Int) {
        ObjectCalls.ptrcallWithIntArg(setHeightBind, handle, height)
    }

    fun getXform(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getXformBind, handle)
    }

    fun setXform(xform: Transform3D) {
        ObjectCalls.ptrcallWithTransform3DArg(setXformBind, handle, xform)
    }

    fun getMesh(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMeshBind, handle)
    }

    fun setMesh(mesh: Int) {
        ObjectCalls.ptrcallWithIntArg(setMeshBind, handle, mesh)
    }

    fun getCamera(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCameraBind, handle)
    }

    fun setCamera(camera: Int) {
        ObjectCalls.ptrcallWithIntArg(setCameraBind, handle, camera)
    }

    fun getSkin(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSkinBind, handle)
    }

    fun setSkin(skin: Int) {
        ObjectCalls.ptrcallWithIntArg(setSkinBind, handle, skin)
    }

    fun getSkeleton(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSkeletonBind, handle)
    }

    fun setSkeleton(skeleton: Int) {
        ObjectCalls.ptrcallWithIntArg(setSkeletonBind, handle, skeleton)
    }

    fun getPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getPositionBind, handle)
    }

    fun setPosition(position: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setPositionBind, handle, position)
    }

    fun getRotation(): Quaternion {
        return ObjectCalls.ptrcallNoArgsRetQuaternion(getRotationBind, handle)
    }

    fun setRotation(rotation: Quaternion) {
        ObjectCalls.ptrcallWithQuaternionArg(setRotationBind, handle, rotation)
    }

    fun getScale(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getScaleBind, handle)
    }

    fun setScale(scale: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setScaleBind, handle, scale)
    }

    fun getChildren(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getChildrenBind, handle)
    }

    fun setChildren(children: List<Int>) {
        ObjectCalls.ptrcallWithPackedInt32ListArg(setChildrenBind, handle, children)
    }

    fun appendChildIndex(childIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(appendChildIndexBind, handle, childIndex)
    }

    fun getLight(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLightBind, handle)
    }

    fun setLight(light: Int) {
        ObjectCalls.ptrcallWithIntArg(setLightBind, handle, light)
    }

    fun getVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getVisibleBind, handle)
    }

    fun setVisible(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVisibleBind, handle, visible)
    }

    fun getAdditionalData(extensionName: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getAdditionalDataBind, handle, extensionName)
    }

    fun setAdditionalData(extensionName: String, additionalData: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setAdditionalDataBind, handle, extensionName, additionalData)
    }

    fun getSceneNodePath(gltfState: GLTFState?, handleSkeletons: Boolean = true): NodePath {
        return ObjectCalls.ptrcallWithObjectAndBoolArgRetNodePath(getSceneNodePathBind, handle, gltfState?.requireOpenHandle() ?: MemorySegment.NULL, handleSkeletons)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GLTFNode? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GLTFNode? =
            if (handle.address() == 0L) null else GLTFNode(handle)

        private const val GET_ORIGINAL_NAME_HASH = 2841200299L
        private val getOriginalNameBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "get_original_name", GET_ORIGINAL_NAME_HASH)
        }

        private const val SET_ORIGINAL_NAME_HASH = 83702148L
        private val setOriginalNameBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "set_original_name", SET_ORIGINAL_NAME_HASH)
        }

        private const val GET_PARENT_HASH = 2455072627L
        private val getParentBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "get_parent", GET_PARENT_HASH)
        }

        private const val SET_PARENT_HASH = 1286410249L
        private val setParentBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "set_parent", SET_PARENT_HASH)
        }

        private const val GET_HEIGHT_HASH = 2455072627L
        private val getHeightBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "get_height", GET_HEIGHT_HASH)
        }

        private const val SET_HEIGHT_HASH = 1286410249L
        private val setHeightBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "set_height", SET_HEIGHT_HASH)
        }

        private const val GET_XFORM_HASH = 4183770049L
        private val getXformBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "get_xform", GET_XFORM_HASH)
        }

        private const val SET_XFORM_HASH = 2952846383L
        private val setXformBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "set_xform", SET_XFORM_HASH)
        }

        private const val GET_MESH_HASH = 2455072627L
        private val getMeshBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "get_mesh", GET_MESH_HASH)
        }

        private const val SET_MESH_HASH = 1286410249L
        private val setMeshBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "set_mesh", SET_MESH_HASH)
        }

        private const val GET_CAMERA_HASH = 2455072627L
        private val getCameraBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "get_camera", GET_CAMERA_HASH)
        }

        private const val SET_CAMERA_HASH = 1286410249L
        private val setCameraBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "set_camera", SET_CAMERA_HASH)
        }

        private const val GET_SKIN_HASH = 2455072627L
        private val getSkinBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "get_skin", GET_SKIN_HASH)
        }

        private const val SET_SKIN_HASH = 1286410249L
        private val setSkinBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "set_skin", SET_SKIN_HASH)
        }

        private const val GET_SKELETON_HASH = 2455072627L
        private val getSkeletonBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "get_skeleton", GET_SKELETON_HASH)
        }

        private const val SET_SKELETON_HASH = 1286410249L
        private val setSkeletonBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "set_skeleton", SET_SKELETON_HASH)
        }

        private const val GET_POSITION_HASH = 3783033775L
        private val getPositionBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "get_position", GET_POSITION_HASH)
        }

        private const val SET_POSITION_HASH = 3460891852L
        private val setPositionBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "set_position", SET_POSITION_HASH)
        }

        private const val GET_ROTATION_HASH = 2916281908L
        private val getRotationBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "get_rotation", GET_ROTATION_HASH)
        }

        private const val SET_ROTATION_HASH = 1727505552L
        private val setRotationBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "set_rotation", SET_ROTATION_HASH)
        }

        private const val GET_SCALE_HASH = 3783033775L
        private val getScaleBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "get_scale", GET_SCALE_HASH)
        }

        private const val SET_SCALE_HASH = 3460891852L
        private val setScaleBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "set_scale", SET_SCALE_HASH)
        }

        private const val GET_CHILDREN_HASH = 969006518L
        private val getChildrenBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "get_children", GET_CHILDREN_HASH)
        }

        private const val SET_CHILDREN_HASH = 3614634198L
        private val setChildrenBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "set_children", SET_CHILDREN_HASH)
        }

        private const val APPEND_CHILD_INDEX_HASH = 1286410249L
        private val appendChildIndexBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "append_child_index", APPEND_CHILD_INDEX_HASH)
        }

        private const val GET_LIGHT_HASH = 2455072627L
        private val getLightBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "get_light", GET_LIGHT_HASH)
        }

        private const val SET_LIGHT_HASH = 1286410249L
        private val setLightBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "set_light", SET_LIGHT_HASH)
        }

        private const val GET_VISIBLE_HASH = 2240911060L
        private val getVisibleBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "get_visible", GET_VISIBLE_HASH)
        }

        private const val SET_VISIBLE_HASH = 2586408642L
        private val setVisibleBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "set_visible", SET_VISIBLE_HASH)
        }

        private const val GET_ADDITIONAL_DATA_HASH = 2138907829L
        private val getAdditionalDataBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "get_additional_data", GET_ADDITIONAL_DATA_HASH)
        }

        private const val SET_ADDITIONAL_DATA_HASH = 3776071444L
        private val setAdditionalDataBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "set_additional_data", SET_ADDITIONAL_DATA_HASH)
        }

        private const val GET_SCENE_NODE_PATH_HASH = 573359477L
        private val getSceneNodePathBind by lazy {
            ObjectCalls.getMethodBind("GLTFNode", "get_scene_node_path", GET_SCENE_NODE_PATH_HASH)
        }
    }
}
