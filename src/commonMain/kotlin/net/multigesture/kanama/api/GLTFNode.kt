package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: GLTFNode
 */
class GLTFNode(handle: GodotHandle) : Resource(handle) {
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getOriginalNameBind, segment)
    }

    fun setOriginalName(originalName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setOriginalNameBind, segment, originalName)
    }

    fun getParent(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getParentBind, segment)
    }

    fun setParent(parent: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setParentBind, segment, parent)
    }

    fun getHeight(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getHeightBind, segment)
    }

    fun setHeight(height: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setHeightBind, segment, height)
    }

    fun getXform(): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getXformBind, segment)
    }

    fun setXform(xform: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithTransform3DArg(setXformBind, segment, xform)
    }

    fun getMesh(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getMeshBind, segment)
    }

    fun setMesh(mesh: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setMeshBind, segment, mesh)
    }

    fun getCamera(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getCameraBind, segment)
    }

    fun setCamera(camera: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setCameraBind, segment, camera)
    }

    fun getSkin(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSkinBind, segment)
    }

    fun setSkin(skin: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setSkinBind, segment, skin)
    }

    fun getSkeleton(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getSkeletonBind, segment)
    }

    fun setSkeleton(skeleton: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setSkeletonBind, segment, skeleton)
    }

    fun getPosition(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(getPositionBind, segment)
    }

    fun setPosition(position: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(setPositionBind, segment, position)
    }

    fun getRotation(): Quaternion {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetQuaternion(getRotationBind, segment)
    }

    fun setRotation(rotation: Quaternion) {
        checkOpen()
        ObjectCalls.ptrcallWithQuaternionArg(setRotationBind, segment, rotation)
    }

    fun getScale(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(getScaleBind, segment)
    }

    fun setScale(scale: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(setScaleBind, segment, scale)
    }

    fun getChildren(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getChildrenBind, segment)
    }

    fun setChildren(children: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt32ListArg(setChildrenBind, segment, children)
    }

    fun appendChildIndex(childIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(appendChildIndexBind, segment, childIndex)
    }

    fun getLight(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getLightBind, segment)
    }

    fun setLight(light: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setLightBind, segment, light)
    }

    fun getVisible(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getVisibleBind, segment)
    }

    fun setVisible(visible: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setVisibleBind, segment, visible)
    }

    fun getAdditionalData(extensionName: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getAdditionalDataBind, segment, extensionName)
    }

    fun setAdditionalData(extensionName: String, additionalData: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setAdditionalDataBind, segment, extensionName, additionalData)
    }

    fun getSceneNodePath(gltfState: GLTFState?, handleSkeletons: Boolean = true): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectAndBoolArgRetNodePath(getSceneNodePathBind, segment, gltfState?.requireOpenHandle() ?: MemorySegment.NULL, handleSkeletons)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFNode? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): GLTFNode? =
            if (handle.address() == 0L) null else GLTFNode(GodotHandle(handle))

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
