package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: GLTFState
 */
open class GLTFState(handle: MemorySegment) : Resource(handle) {
    var majorVersion: Int
        @JvmName("majorVersionProperty")
        get() = getMajorVersion()
        @JvmName("setMajorVersionProperty")
        set(value) = setMajorVersion(value)

    var minorVersion: Int
        @JvmName("minorVersionProperty")
        get() = getMinorVersion()
        @JvmName("setMinorVersionProperty")
        set(value) = setMinorVersion(value)

    var copyright: String
        @JvmName("copyrightProperty")
        get() = getCopyright()
        @JvmName("setCopyrightProperty")
        set(value) = setCopyright(value)

    var useNamedSkinBinds: Boolean
        @JvmName("useNamedSkinBindsProperty")
        get() = getUseNamedSkinBinds()
        @JvmName("setUseNamedSkinBindsProperty")
        set(value) = setUseNamedSkinBinds(value)

    val nodes: List<GLTFNode>
        @JvmName("nodesProperty")
        get() = getNodes()

    val bufferViews: List<GLTFBufferView>
        @JvmName("bufferViewsProperty")
        get() = getBufferViews()

    val accessors: List<GLTFAccessor>
        @JvmName("accessorsProperty")
        get() = getAccessors()

    val meshes: List<GLTFMesh>
        @JvmName("meshesProperty")
        get() = getMeshes()

    val materials: List<Material>
        @JvmName("materialsProperty")
        get() = getMaterials()

    var sceneName: String
        @JvmName("sceneNameProperty")
        get() = getSceneName()
        @JvmName("setSceneNameProperty")
        set(value) = setSceneName(value)

    var basePath: String
        @JvmName("basePathProperty")
        get() = getBasePath()
        @JvmName("setBasePathProperty")
        set(value) = setBasePath(value)

    var filename: String
        @JvmName("filenameProperty")
        get() = getFilename()
        @JvmName("setFilenameProperty")
        set(value) = setFilename(value)

    val rootNodes: List<Int>
        @JvmName("rootNodesProperty")
        get() = getRootNodes()

    val textures: List<GLTFTexture>
        @JvmName("texturesProperty")
        get() = getTextures()

    val textureSamplers: List<GLTFTextureSampler>
        @JvmName("textureSamplersProperty")
        get() = getTextureSamplers()

    val images: List<Texture2D>
        @JvmName("imagesProperty")
        get() = getImages()

    val skins: List<GLTFSkin>
        @JvmName("skinsProperty")
        get() = getSkins()

    val cameras: List<GLTFCamera>
        @JvmName("camerasProperty")
        get() = getCameras()

    val lights: List<GLTFLight>
        @JvmName("lightsProperty")
        get() = getLights()

    val skeletons: List<GLTFSkeleton>
        @JvmName("skeletonsProperty")
        get() = getSkeletons()

    var createAnimations: Boolean
        @JvmName("createAnimationsProperty")
        get() = getCreateAnimations()
        @JvmName("setCreateAnimationsProperty")
        set(value) = setCreateAnimations(value)

    var importAsSkeletonBones: Boolean
        @JvmName("importAsSkeletonBonesProperty")
        get() = getImportAsSkeletonBones()
        @JvmName("setImportAsSkeletonBonesProperty")
        set(value) = setImportAsSkeletonBones(value)

    val animations: List<GLTFAnimation>
        @JvmName("animationsProperty")
        get() = getAnimations()

    var handleBinaryImageMode: Long
        @JvmName("handleBinaryImageModeProperty")
        get() = getHandleBinaryImageMode()
        @JvmName("setHandleBinaryImageModeProperty")
        set(value) = setHandleBinaryImageMode(value)

    var bakeFps: Double
        @JvmName("bakeFpsProperty")
        get() = getBakeFps()
        @JvmName("setBakeFpsProperty")
        set(value) = setBakeFps(value)

    var handleBinaryImage: Int
        @JvmName("handleBinaryImageProperty")
        get() = getHandleBinaryImage()
        @JvmName("setHandleBinaryImageProperty")
        set(value) = setHandleBinaryImage(value)

    fun addUsedExtension(extensionName: String, required: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithStringAndBoolArg(addUsedExtensionBind, handle, extensionName, required)
    }

    fun appendGltfNode(gltfNode: GLTFNode?, godotSceneNode: Node, parentNodeIndex: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoObjectIntArgsRetInt(appendGltfNodeBind, handle, gltfNode?.requireOpenHandle() ?: MemorySegment.NULL, godotSceneNode.handle, parentNodeIndex)
    }

    fun getMajorVersion(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getMajorVersionBind, handle)
    }

    fun setMajorVersion(majorVersion: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setMajorVersionBind, handle, majorVersion)
    }

    fun getMinorVersion(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getMinorVersionBind, handle)
    }

    fun setMinorVersion(minorVersion: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setMinorVersionBind, handle, minorVersion)
    }

    fun getCopyright(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getCopyrightBind, handle)
    }

    fun setCopyright(copyright: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setCopyrightBind, handle, copyright)
    }

    fun getUseNamedSkinBinds(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getUseNamedSkinBindsBind, handle)
    }

    fun setUseNamedSkinBinds(useNamedSkinBinds: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setUseNamedSkinBindsBind, handle, useNamedSkinBinds)
    }

    fun getNodes(): List<GLTFNode> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getNodesBind, handle, GLTFNode::fromHandle)
    }

    fun getBufferViews(): List<GLTFBufferView> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getBufferViewsBind, handle, GLTFBufferView::fromHandle)
    }

    fun getAccessors(): List<GLTFAccessor> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getAccessorsBind, handle, GLTFAccessor::fromHandle)
    }

    fun getMeshes(): List<GLTFMesh> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getMeshesBind, handle, GLTFMesh::fromHandle)
    }

    fun getAnimationPlayersCount(animPlayerIndex: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getAnimationPlayersCountBind, handle, animPlayerIndex)
    }

    fun getAnimationPlayer(animPlayerIndex: Int): AnimationPlayer? {
        checkOpen()
        return AnimationPlayer.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getAnimationPlayerBind, handle, animPlayerIndex))
    }

    fun getMaterials(): List<Material> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getMaterialsBind, handle, Material::fromHandle)
    }

    fun getSceneName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getSceneNameBind, handle)
    }

    fun setSceneName(sceneName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setSceneNameBind, handle, sceneName)
    }

    fun getBasePath(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getBasePathBind, handle)
    }

    fun setBasePath(basePath: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setBasePathBind, handle, basePath)
    }

    fun getFilename(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getFilenameBind, handle)
    }

    fun setFilename(filename: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setFilenameBind, handle, filename)
    }

    fun getRootNodes(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getRootNodesBind, handle)
    }

    fun getTextures(): List<GLTFTexture> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getTexturesBind, handle, GLTFTexture::fromHandle)
    }

    fun getTextureSamplers(): List<GLTFTextureSampler> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getTextureSamplersBind, handle, GLTFTextureSampler::fromHandle)
    }

    fun getImages(): List<Texture2D> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getImagesBind, handle, Texture2D::fromHandle)
    }

    fun getSkins(): List<GLTFSkin> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getSkinsBind, handle, GLTFSkin::fromHandle)
    }

    fun getCameras(): List<GLTFCamera> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getCamerasBind, handle, GLTFCamera::fromHandle)
    }

    fun getLights(): List<GLTFLight> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getLightsBind, handle, GLTFLight::fromHandle)
    }

    fun getSkeletons(): List<GLTFSkeleton> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getSkeletonsBind, handle, GLTFSkeleton::fromHandle)
    }

    fun getCreateAnimations(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getCreateAnimationsBind, handle)
    }

    fun setCreateAnimations(createAnimations: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setCreateAnimationsBind, handle, createAnimations)
    }

    fun getImportAsSkeletonBones(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getImportAsSkeletonBonesBind, handle)
    }

    fun setImportAsSkeletonBones(importAsSkeletonBones: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setImportAsSkeletonBonesBind, handle, importAsSkeletonBones)
    }

    fun getAnimations(): List<GLTFAnimation> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getAnimationsBind, handle, GLTFAnimation::fromHandle)
    }

    fun getSceneNode(gltfNodeIndex: Int): Node? {
        checkOpen()
        return Node.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSceneNodeBind, handle, gltfNodeIndex))
    }

    fun getNodeIndex(sceneNode: Node): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectArgRetInt(getNodeIndexBind, handle, sceneNode.handle)
    }

    fun getAdditionalData(extensionName: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getAdditionalDataBind, handle, extensionName)
    }

    fun getHandleBinaryImageMode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getHandleBinaryImageModeBind, handle)
    }

    fun setHandleBinaryImageMode(method: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setHandleBinaryImageModeBind, handle, method)
    }

    fun setBakeFps(value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setBakeFpsBind, handle, value)
    }

    fun getBakeFps(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getBakeFpsBind, handle)
    }

    fun getHandleBinaryImage(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getHandleBinaryImageBind, handle)
    }

    fun setHandleBinaryImage(method: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setHandleBinaryImageBind, handle, method)
    }

    companion object {
        const val HANDLE_BINARY_DISCARD_TEXTURES: Long = 0L
        const val HANDLE_BINARY_EXTRACT_TEXTURES: Long = 1L
        const val HANDLE_BINARY_EMBED_AS_BASISU: Long = 2L
        const val HANDLE_BINARY_EMBED_AS_UNCOMPRESSED: Long = 3L
        const val HANDLE_BINARY_IMAGE_MODE_DISCARD_TEXTURES: Long = 0L
        const val HANDLE_BINARY_IMAGE_MODE_EXTRACT_TEXTURES: Long = 1L
        const val HANDLE_BINARY_IMAGE_MODE_EMBED_AS_BASISU: Long = 2L
        const val HANDLE_BINARY_IMAGE_MODE_EMBED_AS_UNCOMPRESSED: Long = 3L

        fun fromHandle(handle: MemorySegment): GLTFState? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GLTFState? =
            if (handle.address() == 0L) null else GLTFState(handle)

        private const val ADD_USED_EXTENSION_HASH = 2678287736L
        private val addUsedExtensionBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "add_used_extension", ADD_USED_EXTENSION_HASH)
        }

        private const val APPEND_GLTF_NODE_HASH = 3562288551L
        private val appendGltfNodeBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "append_gltf_node", APPEND_GLTF_NODE_HASH)
        }

        private const val GET_MAJOR_VERSION_HASH = 3905245786L
        private val getMajorVersionBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_major_version", GET_MAJOR_VERSION_HASH)
        }

        private const val SET_MAJOR_VERSION_HASH = 1286410249L
        private val setMajorVersionBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_major_version", SET_MAJOR_VERSION_HASH)
        }

        private const val GET_MINOR_VERSION_HASH = 3905245786L
        private val getMinorVersionBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_minor_version", GET_MINOR_VERSION_HASH)
        }

        private const val SET_MINOR_VERSION_HASH = 1286410249L
        private val setMinorVersionBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_minor_version", SET_MINOR_VERSION_HASH)
        }

        private const val GET_COPYRIGHT_HASH = 201670096L
        private val getCopyrightBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_copyright", GET_COPYRIGHT_HASH)
        }

        private const val SET_COPYRIGHT_HASH = 83702148L
        private val setCopyrightBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_copyright", SET_COPYRIGHT_HASH)
        }

        private const val GET_USE_NAMED_SKIN_BINDS_HASH = 36873697L
        private val getUseNamedSkinBindsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_use_named_skin_binds", GET_USE_NAMED_SKIN_BINDS_HASH)
        }

        private const val SET_USE_NAMED_SKIN_BINDS_HASH = 2586408642L
        private val setUseNamedSkinBindsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_use_named_skin_binds", SET_USE_NAMED_SKIN_BINDS_HASH)
        }

        private const val GET_NODES_HASH = 3995934104L
        private val getNodesBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_nodes", GET_NODES_HASH)
        }

        private const val GET_BUFFER_VIEWS_HASH = 3995934104L
        private val getBufferViewsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_buffer_views", GET_BUFFER_VIEWS_HASH)
        }

        private const val GET_ACCESSORS_HASH = 3995934104L
        private val getAccessorsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_accessors", GET_ACCESSORS_HASH)
        }

        private const val GET_MESHES_HASH = 3995934104L
        private val getMeshesBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_meshes", GET_MESHES_HASH)
        }

        private const val GET_ANIMATION_PLAYERS_COUNT_HASH = 923996154L
        private val getAnimationPlayersCountBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_animation_players_count", GET_ANIMATION_PLAYERS_COUNT_HASH)
        }

        private const val GET_ANIMATION_PLAYER_HASH = 1550200483L
        private val getAnimationPlayerBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_animation_player", GET_ANIMATION_PLAYER_HASH)
        }

        private const val GET_MATERIALS_HASH = 3995934104L
        private val getMaterialsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_materials", GET_MATERIALS_HASH)
        }

        private const val GET_SCENE_NAME_HASH = 201670096L
        private val getSceneNameBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_scene_name", GET_SCENE_NAME_HASH)
        }

        private const val SET_SCENE_NAME_HASH = 83702148L
        private val setSceneNameBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_scene_name", SET_SCENE_NAME_HASH)
        }

        private const val GET_BASE_PATH_HASH = 201670096L
        private val getBasePathBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_base_path", GET_BASE_PATH_HASH)
        }

        private const val SET_BASE_PATH_HASH = 83702148L
        private val setBasePathBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_base_path", SET_BASE_PATH_HASH)
        }

        private const val GET_FILENAME_HASH = 201670096L
        private val getFilenameBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_filename", GET_FILENAME_HASH)
        }

        private const val SET_FILENAME_HASH = 83702148L
        private val setFilenameBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_filename", SET_FILENAME_HASH)
        }

        private const val GET_ROOT_NODES_HASH = 1930428628L
        private val getRootNodesBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_root_nodes", GET_ROOT_NODES_HASH)
        }

        private const val GET_TEXTURES_HASH = 3995934104L
        private val getTexturesBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_textures", GET_TEXTURES_HASH)
        }

        private const val GET_TEXTURE_SAMPLERS_HASH = 3995934104L
        private val getTextureSamplersBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_texture_samplers", GET_TEXTURE_SAMPLERS_HASH)
        }

        private const val GET_IMAGES_HASH = 3995934104L
        private val getImagesBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_images", GET_IMAGES_HASH)
        }

        private const val GET_SKINS_HASH = 3995934104L
        private val getSkinsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_skins", GET_SKINS_HASH)
        }

        private const val GET_CAMERAS_HASH = 3995934104L
        private val getCamerasBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_cameras", GET_CAMERAS_HASH)
        }

        private const val GET_LIGHTS_HASH = 3995934104L
        private val getLightsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_lights", GET_LIGHTS_HASH)
        }

        private const val GET_SKELETONS_HASH = 3995934104L
        private val getSkeletonsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_skeletons", GET_SKELETONS_HASH)
        }

        private const val GET_CREATE_ANIMATIONS_HASH = 36873697L
        private val getCreateAnimationsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_create_animations", GET_CREATE_ANIMATIONS_HASH)
        }

        private const val SET_CREATE_ANIMATIONS_HASH = 2586408642L
        private val setCreateAnimationsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_create_animations", SET_CREATE_ANIMATIONS_HASH)
        }

        private const val GET_IMPORT_AS_SKELETON_BONES_HASH = 36873697L
        private val getImportAsSkeletonBonesBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_import_as_skeleton_bones", GET_IMPORT_AS_SKELETON_BONES_HASH)
        }

        private const val SET_IMPORT_AS_SKELETON_BONES_HASH = 2586408642L
        private val setImportAsSkeletonBonesBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_import_as_skeleton_bones", SET_IMPORT_AS_SKELETON_BONES_HASH)
        }

        private const val GET_ANIMATIONS_HASH = 3995934104L
        private val getAnimationsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_animations", GET_ANIMATIONS_HASH)
        }

        private const val GET_SCENE_NODE_HASH = 539202265L
        private val getSceneNodeBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_scene_node", GET_SCENE_NODE_HASH)
        }

        private const val GET_NODE_INDEX_HASH = 3810805390L
        private val getNodeIndexBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_node_index", GET_NODE_INDEX_HASH)
        }

        private const val GET_ADDITIONAL_DATA_HASH = 2760726917L
        private val getAdditionalDataBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_additional_data", GET_ADDITIONAL_DATA_HASH)
        }

        private const val GET_HANDLE_BINARY_IMAGE_MODE_HASH = 1363384196L
        private val getHandleBinaryImageModeBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_handle_binary_image_mode", GET_HANDLE_BINARY_IMAGE_MODE_HASH)
        }

        private const val SET_HANDLE_BINARY_IMAGE_MODE_HASH = 854676334L
        private val setHandleBinaryImageModeBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_handle_binary_image_mode", SET_HANDLE_BINARY_IMAGE_MODE_HASH)
        }

        private const val SET_BAKE_FPS_HASH = 373806689L
        private val setBakeFpsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_bake_fps", SET_BAKE_FPS_HASH)
        }

        private const val GET_BAKE_FPS_HASH = 1740695150L
        private val getBakeFpsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_bake_fps", GET_BAKE_FPS_HASH)
        }

        private const val GET_HANDLE_BINARY_IMAGE_HASH = 3905245786L
        private val getHandleBinaryImageBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_handle_binary_image", GET_HANDLE_BINARY_IMAGE_HASH)
        }

        private const val SET_HANDLE_BINARY_IMAGE_HASH = 1286410249L
        private val setHandleBinaryImageBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_handle_binary_image", SET_HANDLE_BINARY_IMAGE_HASH)
        }
    }
}
