package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
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
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getOriginalNameBind, segment)
    }

    fun setOriginalName(originalName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setOriginalNameBind, segment, originalName)
    }

    fun getParent(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getParentBind, segment)
    }

    fun setParent(parent: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setParentBind, segment, parent)
    }

    fun getHeight(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getHeightBind, segment)
    }

    fun setHeight(height: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setHeightBind, segment, height)
    }

    fun getXform(): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTransform3D(Binds.getXformBind, segment)
    }

    fun setXform(xform: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithTransform3DArg(Binds.setXformBind, segment, xform)
    }

    fun getMesh(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMeshBind, segment)
    }

    fun setMesh(mesh: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMeshBind, segment, mesh)
    }

    fun getCamera(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getCameraBind, segment)
    }

    fun setCamera(camera: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setCameraBind, segment, camera)
    }

    fun getSkin(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSkinBind, segment)
    }

    fun setSkin(skin: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSkinBind, segment, skin)
    }

    fun getSkeleton(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSkeletonBind, segment)
    }

    fun setSkeleton(skeleton: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSkeletonBind, segment, skeleton)
    }

    fun getPosition(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getPositionBind, segment)
    }

    fun setPosition(position: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setPositionBind, segment, position)
    }

    fun getRotation(): Quaternion {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetQuaternion(Binds.getRotationBind, segment)
    }

    fun setRotation(rotation: Quaternion) {
        checkOpen()
        ObjectCalls.ptrcallWithQuaternionArg(Binds.setRotationBind, segment, rotation)
    }

    fun getScale(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getScaleBind, segment)
    }

    fun setScale(scale: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setScaleBind, segment, scale)
    }

    fun getChildren(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(Binds.getChildrenBind, segment)
    }

    fun setChildren(children: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt32ListArg(Binds.setChildrenBind, segment, children)
    }

    fun appendChildIndex(childIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.appendChildIndexBind, segment, childIndex)
    }

    fun getLight(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getLightBind, segment)
    }

    fun setLight(light: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setLightBind, segment, light)
    }

    fun getVisible(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getVisibleBind, segment)
    }

    fun setVisible(visible: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setVisibleBind, segment, visible)
    }

    fun getAdditionalData(extensionName: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(Binds.getAdditionalDataBind, segment, extensionName)
    }

    fun setAdditionalData(extensionName: String, additionalData: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameAndVariantArg(Binds.setAdditionalDataBind, segment, extensionName, additionalData)
    }

    fun getSceneNodePath(gltfState: GLTFState?, handleSkeletons: Boolean = true): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectAndBoolArgRetNodePath(Binds.getSceneNodePathBind, segment, gltfState?.requireOpenHandle() ?: NULL_SEGMENT, handleSkeletons)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFNode? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GLTFNode? =
            if (handle.address() == 0L) null else RefCounted.owned(GLTFNode(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GLTFNode? =
            if (handle.address() == 0L) null else GLTFNode(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_ORIGINAL_NAME_HASH = 2841200299L
        @JvmField
        val getOriginalNameBind =
            ObjectCalls.getMethodBind("GLTFNode", "get_original_name", GET_ORIGINAL_NAME_HASH)

        private const val SET_ORIGINAL_NAME_HASH = 83702148L
        @JvmField
        val setOriginalNameBind =
            ObjectCalls.getMethodBind("GLTFNode", "set_original_name", SET_ORIGINAL_NAME_HASH)

        private const val GET_PARENT_HASH = 2455072627L
        @JvmField
        val getParentBind =
            ObjectCalls.getMethodBind("GLTFNode", "get_parent", GET_PARENT_HASH)

        private const val SET_PARENT_HASH = 1286410249L
        @JvmField
        val setParentBind =
            ObjectCalls.getMethodBind("GLTFNode", "set_parent", SET_PARENT_HASH)

        private const val GET_HEIGHT_HASH = 2455072627L
        @JvmField
        val getHeightBind =
            ObjectCalls.getMethodBind("GLTFNode", "get_height", GET_HEIGHT_HASH)

        private const val SET_HEIGHT_HASH = 1286410249L
        @JvmField
        val setHeightBind =
            ObjectCalls.getMethodBind("GLTFNode", "set_height", SET_HEIGHT_HASH)

        private const val GET_XFORM_HASH = 4183770049L
        @JvmField
        val getXformBind =
            ObjectCalls.getMethodBind("GLTFNode", "get_xform", GET_XFORM_HASH)

        private const val SET_XFORM_HASH = 2952846383L
        @JvmField
        val setXformBind =
            ObjectCalls.getMethodBind("GLTFNode", "set_xform", SET_XFORM_HASH)

        private const val GET_MESH_HASH = 2455072627L
        @JvmField
        val getMeshBind =
            ObjectCalls.getMethodBind("GLTFNode", "get_mesh", GET_MESH_HASH)

        private const val SET_MESH_HASH = 1286410249L
        @JvmField
        val setMeshBind =
            ObjectCalls.getMethodBind("GLTFNode", "set_mesh", SET_MESH_HASH)

        private const val GET_CAMERA_HASH = 2455072627L
        @JvmField
        val getCameraBind =
            ObjectCalls.getMethodBind("GLTFNode", "get_camera", GET_CAMERA_HASH)

        private const val SET_CAMERA_HASH = 1286410249L
        @JvmField
        val setCameraBind =
            ObjectCalls.getMethodBind("GLTFNode", "set_camera", SET_CAMERA_HASH)

        private const val GET_SKIN_HASH = 2455072627L
        @JvmField
        val getSkinBind =
            ObjectCalls.getMethodBind("GLTFNode", "get_skin", GET_SKIN_HASH)

        private const val SET_SKIN_HASH = 1286410249L
        @JvmField
        val setSkinBind =
            ObjectCalls.getMethodBind("GLTFNode", "set_skin", SET_SKIN_HASH)

        private const val GET_SKELETON_HASH = 2455072627L
        @JvmField
        val getSkeletonBind =
            ObjectCalls.getMethodBind("GLTFNode", "get_skeleton", GET_SKELETON_HASH)

        private const val SET_SKELETON_HASH = 1286410249L
        @JvmField
        val setSkeletonBind =
            ObjectCalls.getMethodBind("GLTFNode", "set_skeleton", SET_SKELETON_HASH)

        private const val GET_POSITION_HASH = 3783033775L
        @JvmField
        val getPositionBind =
            ObjectCalls.getMethodBind("GLTFNode", "get_position", GET_POSITION_HASH)

        private const val SET_POSITION_HASH = 3460891852L
        @JvmField
        val setPositionBind =
            ObjectCalls.getMethodBind("GLTFNode", "set_position", SET_POSITION_HASH)

        private const val GET_ROTATION_HASH = 2916281908L
        @JvmField
        val getRotationBind =
            ObjectCalls.getMethodBind("GLTFNode", "get_rotation", GET_ROTATION_HASH)

        private const val SET_ROTATION_HASH = 1727505552L
        @JvmField
        val setRotationBind =
            ObjectCalls.getMethodBind("GLTFNode", "set_rotation", SET_ROTATION_HASH)

        private const val GET_SCALE_HASH = 3783033775L
        @JvmField
        val getScaleBind =
            ObjectCalls.getMethodBind("GLTFNode", "get_scale", GET_SCALE_HASH)

        private const val SET_SCALE_HASH = 3460891852L
        @JvmField
        val setScaleBind =
            ObjectCalls.getMethodBind("GLTFNode", "set_scale", SET_SCALE_HASH)

        private const val GET_CHILDREN_HASH = 969006518L
        @JvmField
        val getChildrenBind =
            ObjectCalls.getMethodBind("GLTFNode", "get_children", GET_CHILDREN_HASH)

        private const val SET_CHILDREN_HASH = 3614634198L
        @JvmField
        val setChildrenBind =
            ObjectCalls.getMethodBind("GLTFNode", "set_children", SET_CHILDREN_HASH)

        private const val APPEND_CHILD_INDEX_HASH = 1286410249L
        @JvmField
        val appendChildIndexBind =
            ObjectCalls.getMethodBind("GLTFNode", "append_child_index", APPEND_CHILD_INDEX_HASH)

        private const val GET_LIGHT_HASH = 2455072627L
        @JvmField
        val getLightBind =
            ObjectCalls.getMethodBind("GLTFNode", "get_light", GET_LIGHT_HASH)

        private const val SET_LIGHT_HASH = 1286410249L
        @JvmField
        val setLightBind =
            ObjectCalls.getMethodBind("GLTFNode", "set_light", SET_LIGHT_HASH)

        private const val GET_VISIBLE_HASH = 2240911060L
        @JvmField
        val getVisibleBind =
            ObjectCalls.getMethodBind("GLTFNode", "get_visible", GET_VISIBLE_HASH)

        private const val SET_VISIBLE_HASH = 2586408642L
        @JvmField
        val setVisibleBind =
            ObjectCalls.getMethodBind("GLTFNode", "set_visible", SET_VISIBLE_HASH)

        private const val GET_ADDITIONAL_DATA_HASH = 2138907829L
        @JvmField
        val getAdditionalDataBind =
            ObjectCalls.getMethodBind("GLTFNode", "get_additional_data", GET_ADDITIONAL_DATA_HASH)

        private const val SET_ADDITIONAL_DATA_HASH = 3776071444L
        @JvmField
        val setAdditionalDataBind =
            ObjectCalls.getMethodBind("GLTFNode", "set_additional_data", SET_ADDITIONAL_DATA_HASH)

        private const val GET_SCENE_NODE_PATH_HASH = 573359477L
        @JvmField
        val getSceneNodePathBind =
            ObjectCalls.getMethodBind("GLTFNode", "get_scene_node_path", GET_SCENE_NODE_PATH_HASH)
    }
}
