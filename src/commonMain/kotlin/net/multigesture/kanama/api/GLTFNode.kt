package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * glTF node class.
 *
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

    /**
     * The original name of the node.
     *
     * Generated from Godot docs: GLTFNode.get_original_name
     */
    fun getOriginalName(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getOriginalNameBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The original name of the node.
     *
     * Generated from Godot docs: GLTFNode.set_original_name
     */
    fun setOriginalName(originalName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setOriginalNameBind, segment, originalName)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The index of the parent node in the `GLTFState`. If -1, this node is a root node.
     *
     * Generated from Godot docs: GLTFNode.get_parent
     */
    fun getParent(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getParentBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The index of the parent node in the `GLTFState`. If -1, this node is a root node.
     *
     * Generated from Godot docs: GLTFNode.set_parent
     */
    fun setParent(parent: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setParentBind, segment, parent)
        ObjectCalls.keepReachable(this)
    }

    /**
     * How deep into the node hierarchy this node is. A root node will have a height of 0, its children
     * will have a height of 1, and so on. If -1, the height has not been calculated.
     *
     * Generated from Godot docs: GLTFNode.get_height
     */
    fun getHeight(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getHeightBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * How deep into the node hierarchy this node is. A root node will have a height of 0, its children
     * will have a height of 1, and so on. If -1, the height has not been calculated.
     *
     * Generated from Godot docs: GLTFNode.set_height
     */
    fun setHeight(height: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setHeightBind, segment, height)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The transform of the glTF node relative to its parent. This property is usually unused since the
     * position, rotation, and scale properties are preferred.
     *
     * Generated from Godot docs: GLTFNode.get_xform
     */
    fun getXform(): Transform3D {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetTransform3D(Binds.getXformBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The transform of the glTF node relative to its parent. This property is usually unused since the
     * position, rotation, and scale properties are preferred.
     *
     * Generated from Godot docs: GLTFNode.set_xform
     */
    fun setXform(xform: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithTransform3DArg(Binds.setXformBind, segment, xform)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If this glTF node is a mesh, the index of the `GLTFMesh` in the `GLTFState` that describes the
     * mesh's properties. If -1, this node is not a mesh.
     *
     * Generated from Godot docs: GLTFNode.get_mesh
     */
    fun getMesh(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getMeshBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If this glTF node is a mesh, the index of the `GLTFMesh` in the `GLTFState` that describes the
     * mesh's properties. If -1, this node is not a mesh.
     *
     * Generated from Godot docs: GLTFNode.set_mesh
     */
    fun setMesh(mesh: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMeshBind, segment, mesh)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If this glTF node is a camera, the index of the `GLTFCamera` in the `GLTFState` that describes
     * the camera's properties. If `-1`, this node is not a camera.
     *
     * Generated from Godot docs: GLTFNode.get_camera
     */
    fun getCamera(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getCameraBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If this glTF node is a camera, the index of the `GLTFCamera` in the `GLTFState` that describes
     * the camera's properties. If `-1`, this node is not a camera.
     *
     * Generated from Godot docs: GLTFNode.set_camera
     */
    fun setCamera(camera: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setCameraBind, segment, camera)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If this glTF node has a skin, the index of the `GLTFSkin` in the `GLTFState` that describes the
     * skin's properties. If -1, this node does not have a skin.
     *
     * Generated from Godot docs: GLTFNode.get_skin
     */
    fun getSkin(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getSkinBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If this glTF node has a skin, the index of the `GLTFSkin` in the `GLTFState` that describes the
     * skin's properties. If -1, this node does not have a skin.
     *
     * Generated from Godot docs: GLTFNode.set_skin
     */
    fun setSkin(skin: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSkinBind, segment, skin)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If this glTF node has a skeleton, the index of the `GLTFSkeleton` in the `GLTFState` that
     * describes the skeleton's properties. If -1, this node does not have a skeleton.
     *
     * Generated from Godot docs: GLTFNode.get_skeleton
     */
    fun getSkeleton(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getSkeletonBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If this glTF node has a skeleton, the index of the `GLTFSkeleton` in the `GLTFState` that
     * describes the skeleton's properties. If -1, this node does not have a skeleton.
     *
     * Generated from Godot docs: GLTFNode.set_skeleton
     */
    fun setSkeleton(skeleton: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setSkeletonBind, segment, skeleton)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The position of the glTF node relative to its parent.
     *
     * Generated from Godot docs: GLTFNode.get_position
     */
    fun getPosition(): Vector3 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector3(Binds.getPositionBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The position of the glTF node relative to its parent.
     *
     * Generated from Godot docs: GLTFNode.set_position
     */
    fun setPosition(position: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setPositionBind, segment, position)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The rotation of the glTF node relative to its parent.
     *
     * Generated from Godot docs: GLTFNode.get_rotation
     */
    fun getRotation(): Quaternion {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetQuaternion(Binds.getRotationBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The rotation of the glTF node relative to its parent.
     *
     * Generated from Godot docs: GLTFNode.set_rotation
     */
    fun setRotation(rotation: Quaternion) {
        checkOpen()
        ObjectCalls.ptrcallWithQuaternionArg(Binds.setRotationBind, segment, rotation)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The scale of the glTF node relative to its parent.
     *
     * Generated from Godot docs: GLTFNode.get_scale
     */
    fun getScale(): Vector3 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector3(Binds.getScaleBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The scale of the glTF node relative to its parent.
     *
     * Generated from Godot docs: GLTFNode.set_scale
     */
    fun setScale(scale: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(Binds.setScaleBind, segment, scale)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The indices of the child nodes in the `GLTFState`. If this glTF node has no children, this will
     * be an empty array.
     *
     * Generated from Godot docs: GLTFNode.get_children
     */
    fun getChildren(): List<Int> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedInt32List(Binds.getChildrenBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The indices of the child nodes in the `GLTFState`. If this glTF node has no children, this will
     * be an empty array.
     *
     * Generated from Godot docs: GLTFNode.set_children
     */
    fun setChildren(children: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt32ListArg(Binds.setChildrenBind, segment, children)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Appends the given child node index to the `children` array.
     *
     * Generated from Godot docs: GLTFNode.append_child_index
     */
    fun appendChildIndex(childIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.appendChildIndexBind, segment, childIndex)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If this glTF node is a light, the index of the `GLTFLight` in the `GLTFState` that describes the
     * light's properties. If -1, this node is not a light.
     *
     * Generated from Godot docs: GLTFNode.get_light
     */
    fun getLight(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getLightBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If this glTF node is a light, the index of the `GLTFLight` in the `GLTFState` that describes the
     * light's properties. If -1, this node is not a light.
     *
     * Generated from Godot docs: GLTFNode.set_light
     */
    fun setLight(light: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setLightBind, segment, light)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If `true`, the GLTF node is visible. If `false`, the GLTF node is not visible. This is converted
     * to the `Node3D.visible` property in the Godot scene, and is exported to `KHR_node_visibility`
     * when `false`.
     *
     * Generated from Godot docs: GLTFNode.get_visible
     */
    fun getVisible(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.getVisibleBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `true`, the GLTF node is visible. If `false`, the GLTF node is not visible. This is converted
     * to the `Node3D.visible` property in the Godot scene, and is exported to `KHR_node_visibility`
     * when `false`.
     *
     * Generated from Godot docs: GLTFNode.set_visible
     */
    fun setVisible(visible: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setVisibleBind, segment, visible)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Gets additional arbitrary data in this `GLTFNode` instance. This can be used to keep per-node
     * state data in `GLTFDocumentExtension` classes, which is important because they are stateless.
     * The argument should be the `GLTFDocumentExtension` name (does not have to match the extension
     * name in the glTF file), and the return value can be anything you set. If nothing was set, the
     * return value is `null`.
     *
     * Generated from Godot docs: GLTFNode.get_additional_data
     */
    fun getAdditionalData(extensionName: String): Any? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(Binds.getAdditionalDataBind, segment, extensionName)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets additional arbitrary data in this `GLTFNode` instance. This can be used to keep per-node
     * state data in `GLTFDocumentExtension` classes, which is important because they are stateless.
     * The first argument should be the `GLTFDocumentExtension` name (does not have to match the
     * extension name in the glTF file), and the second argument can be anything you want.
     *
     * Generated from Godot docs: GLTFNode.set_additional_data
     */
    fun setAdditionalData(extensionName: String, additionalData: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameAndVariantArg(Binds.setAdditionalDataBind, segment, extensionName, additionalData)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the `NodePath` that this GLTF node will have in the Godot scene tree after being
     * imported. This is useful when importing glTF object model pointers with
     * `GLTFObjectModelProperty`, for handling extensions such as `KHR_animation_pointer` or
     * `KHR_interactivity`. If `handle_skeletons` is `true`, paths to skeleton bone glTF nodes will be
     * resolved properly. For example, a path that would be `^"A/B/C/Bone1/Bone2/Bone3"` if `false`
     * will become `^"A/B/C/Skeleton3D:Bone3"`.
     *
     * Generated from Godot docs: GLTFNode.get_scene_node_path
     */
    fun getSceneNodePath(gltfState: GLTFState?, handleSkeletons: Boolean = true): NodePath {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithObjectAndBoolArgRetNodePath(Binds.getSceneNodePathBind, segment, gltfState?.requireOpenHandle() ?: NULL_SEGMENT, handleSkeletons)
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(gltfState)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFNode? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GLTFNode? =
            if (handle.address() == 0L) null else RefCounted.owned(GLTFNode(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GLTFNode? =
            if (handle.address() == 0L) null else GLTFNode(GodotHandle(handle))

        /** A new GLTFNode, GDScript's `GLTFNode.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): GLTFNode =
            RefCounted.owned(GLTFNode(GodotHandle(requireConstructed(ObjectCalls.constructObject("GLTFNode"), "GLTFNode"))))
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
