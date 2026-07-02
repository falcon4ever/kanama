package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: GLTFState
 */
open class GLTFState(handle: MemorySegment) : Resource(handle) {
    var json: Map<String, Any?>
        @JvmName("jsonProperty")
        get() = getJson()
        @JvmName("setJsonProperty")
        set(value) = setJson(value)

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

    var glbData: ByteArray
        @JvmName("glbDataProperty")
        get() = getGlbData()
        @JvmName("setGlbDataProperty")
        set(value) = setGlbData(value)

    var useNamedSkinBinds: Boolean
        @JvmName("useNamedSkinBindsProperty")
        get() = getUseNamedSkinBinds()
        @JvmName("setUseNamedSkinBindsProperty")
        set(value) = setUseNamedSkinBinds(value)

    var nodes: List<GLTFNode>
        @JvmName("nodesProperty")
        get() = getNodes()
        @JvmName("setNodesProperty")
        set(value) = setNodes(value)

    var buffers: List<ByteArray>
        @JvmName("buffersProperty")
        get() = getBuffers()
        @JvmName("setBuffersProperty")
        set(value) = setBuffers(value)

    var bufferViews: List<GLTFBufferView>
        @JvmName("bufferViewsProperty")
        get() = getBufferViews()
        @JvmName("setBufferViewsProperty")
        set(value) = setBufferViews(value)

    var accessors: List<GLTFAccessor>
        @JvmName("accessorsProperty")
        get() = getAccessors()
        @JvmName("setAccessorsProperty")
        set(value) = setAccessors(value)

    var meshes: List<GLTFMesh>
        @JvmName("meshesProperty")
        get() = getMeshes()
        @JvmName("setMeshesProperty")
        set(value) = setMeshes(value)

    var materials: List<Material>
        @JvmName("materialsProperty")
        get() = getMaterials()
        @JvmName("setMaterialsProperty")
        set(value) = setMaterials(value)

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

    var rootNodes: List<Int>
        @JvmName("rootNodesProperty")
        get() = getRootNodes()
        @JvmName("setRootNodesProperty")
        set(value) = setRootNodes(value)

    var textures: List<GLTFTexture>
        @JvmName("texturesProperty")
        get() = getTextures()
        @JvmName("setTexturesProperty")
        set(value) = setTextures(value)

    var textureSamplers: List<GLTFTextureSampler>
        @JvmName("textureSamplersProperty")
        get() = getTextureSamplers()
        @JvmName("setTextureSamplersProperty")
        set(value) = setTextureSamplers(value)

    var images: List<Texture2D>
        @JvmName("imagesProperty")
        get() = getImages()
        @JvmName("setImagesProperty")
        set(value) = setImages(value)

    var skins: List<GLTFSkin>
        @JvmName("skinsProperty")
        get() = getSkins()
        @JvmName("setSkinsProperty")
        set(value) = setSkins(value)

    var cameras: List<GLTFCamera>
        @JvmName("camerasProperty")
        get() = getCameras()
        @JvmName("setCamerasProperty")
        set(value) = setCameras(value)

    var lights: List<GLTFLight>
        @JvmName("lightsProperty")
        get() = getLights()
        @JvmName("setLightsProperty")
        set(value) = setLights(value)

    var uniqueNames: List<String>
        @JvmName("uniqueNamesProperty")
        get() = getUniqueNames()
        @JvmName("setUniqueNamesProperty")
        set(value) = setUniqueNames(value)

    var uniqueAnimationNames: List<String>
        @JvmName("uniqueAnimationNamesProperty")
        get() = getUniqueAnimationNames()
        @JvmName("setUniqueAnimationNamesProperty")
        set(value) = setUniqueAnimationNames(value)

    var skeletons: List<GLTFSkeleton>
        @JvmName("skeletonsProperty")
        get() = getSkeletons()
        @JvmName("setSkeletonsProperty")
        set(value) = setSkeletons(value)

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

    var animations: List<GLTFAnimation>
        @JvmName("animationsProperty")
        get() = getAnimations()
        @JvmName("setAnimationsProperty")
        set(value) = setAnimations(value)

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
        ObjectCalls.ptrcallWithStringAndBoolArg(addUsedExtensionBind, handle, extensionName, required)
    }

    fun appendDataToBuffers(data: ByteArray, deduplication: Boolean): Int {
        return ObjectCalls.ptrcallWithByteArrayAndBoolArgRetInt(appendDataToBuffersBind, handle, data, deduplication)
    }

    fun appendGltfNode(gltfNode: GLTFNode?, godotSceneNode: Node, parentNodeIndex: Int): Int {
        return ObjectCalls.ptrcallWithTwoObjectIntArgsRetInt(appendGltfNodeBind, handle, gltfNode?.requireOpenHandle() ?: MemorySegment.NULL, godotSceneNode.handle, parentNodeIndex)
    }

    fun getJson(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getJsonBind, handle)
    }

    fun setJson(json: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(setJsonBind, handle, json)
    }

    fun getMajorVersion(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMajorVersionBind, handle)
    }

    fun setMajorVersion(majorVersion: Int) {
        ObjectCalls.ptrcallWithIntArg(setMajorVersionBind, handle, majorVersion)
    }

    fun getMinorVersion(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMinorVersionBind, handle)
    }

    fun setMinorVersion(minorVersion: Int) {
        ObjectCalls.ptrcallWithIntArg(setMinorVersionBind, handle, minorVersion)
    }

    fun getCopyright(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCopyrightBind, handle)
    }

    fun setCopyright(copyright: String) {
        ObjectCalls.ptrcallWithStringArg(setCopyrightBind, handle, copyright)
    }

    fun getGlbData(): ByteArray {
        return ObjectCalls.ptrcallNoArgsRetByteArray(getGlbDataBind, handle)
    }

    fun setGlbData(glbData: ByteArray) {
        ObjectCalls.ptrcallWithByteArrayArg(setGlbDataBind, handle, glbData)
    }

    fun getUseNamedSkinBinds(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseNamedSkinBindsBind, handle)
    }

    fun setUseNamedSkinBinds(useNamedSkinBinds: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseNamedSkinBindsBind, handle, useNamedSkinBinds)
    }

    fun getNodes(): List<GLTFNode> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getNodesBind, handle, GLTFNode::fromHandle)
    }

    fun setNodes(nodes: List<GLTFNode>) {
        ObjectCalls.ptrcallWithObjectListArg(setNodesBind, handle, nodes)
    }

    fun getBuffers(): List<ByteArray> {
        return ObjectCalls.ptrcallNoArgsRetByteArrayList(getBuffersBind, handle)
    }

    fun setBuffers(buffers: List<ByteArray>) {
        ObjectCalls.ptrcallWithByteArrayListArg(setBuffersBind, handle, buffers)
    }

    fun getBufferViews(): List<GLTFBufferView> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getBufferViewsBind, handle, GLTFBufferView::fromHandle)
    }

    fun setBufferViews(bufferViews: List<GLTFBufferView>) {
        ObjectCalls.ptrcallWithObjectListArg(setBufferViewsBind, handle, bufferViews)
    }

    fun getAccessors(): List<GLTFAccessor> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getAccessorsBind, handle, GLTFAccessor::fromHandle)
    }

    fun setAccessors(accessors: List<GLTFAccessor>) {
        ObjectCalls.ptrcallWithObjectListArg(setAccessorsBind, handle, accessors)
    }

    fun getMeshes(): List<GLTFMesh> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getMeshesBind, handle, GLTFMesh::fromHandle)
    }

    fun setMeshes(meshes: List<GLTFMesh>) {
        ObjectCalls.ptrcallWithObjectListArg(setMeshesBind, handle, meshes)
    }

    fun getAnimationPlayersCount(animPlayerIndex: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getAnimationPlayersCountBind, handle, animPlayerIndex)
    }

    fun getAnimationPlayer(animPlayerIndex: Int): AnimationPlayer? {
        return AnimationPlayer.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getAnimationPlayerBind, handle, animPlayerIndex))
    }

    fun getMaterials(): List<Material> {
        return ObjectCalls.ptrcallNoArgsRetTypedMaterialList(getMaterialsBind, handle)
    }

    fun setMaterials(materials: List<Material>) {
        ObjectCalls.ptrcallWithTypedMaterialListArg(setMaterialsBind, handle, materials)
    }

    fun getSceneName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSceneNameBind, handle)
    }

    fun setSceneName(sceneName: String) {
        ObjectCalls.ptrcallWithStringArg(setSceneNameBind, handle, sceneName)
    }

    fun getBasePath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getBasePathBind, handle)
    }

    fun setBasePath(basePath: String) {
        ObjectCalls.ptrcallWithStringArg(setBasePathBind, handle, basePath)
    }

    fun getFilename(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getFilenameBind, handle)
    }

    fun setFilename(filename: String) {
        ObjectCalls.ptrcallWithStringArg(setFilenameBind, handle, filename)
    }

    fun getRootNodes(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getRootNodesBind, handle)
    }

    fun setRootNodes(rootNodes: List<Int>) {
        ObjectCalls.ptrcallWithPackedInt32ListArg(setRootNodesBind, handle, rootNodes)
    }

    fun getTextures(): List<GLTFTexture> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getTexturesBind, handle, GLTFTexture::fromHandle)
    }

    fun setTextures(textures: List<GLTFTexture>) {
        ObjectCalls.ptrcallWithObjectListArg(setTexturesBind, handle, textures)
    }

    fun getTextureSamplers(): List<GLTFTextureSampler> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getTextureSamplersBind, handle, GLTFTextureSampler::fromHandle)
    }

    fun setTextureSamplers(textureSamplers: List<GLTFTextureSampler>) {
        ObjectCalls.ptrcallWithObjectListArg(setTextureSamplersBind, handle, textureSamplers)
    }

    fun getImages(): List<Texture2D> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getImagesBind, handle, Texture2D::fromHandle)
    }

    fun setImages(images: List<Texture2D>) {
        ObjectCalls.ptrcallWithObjectListArg(setImagesBind, handle, images)
    }

    fun getSkins(): List<GLTFSkin> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getSkinsBind, handle, GLTFSkin::fromHandle)
    }

    fun setSkins(skins: List<GLTFSkin>) {
        ObjectCalls.ptrcallWithObjectListArg(setSkinsBind, handle, skins)
    }

    fun getCameras(): List<GLTFCamera> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getCamerasBind, handle, GLTFCamera::fromHandle)
    }

    fun setCameras(cameras: List<GLTFCamera>) {
        ObjectCalls.ptrcallWithObjectListArg(setCamerasBind, handle, cameras)
    }

    fun getLights(): List<GLTFLight> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getLightsBind, handle, GLTFLight::fromHandle)
    }

    fun setLights(lights: List<GLTFLight>) {
        ObjectCalls.ptrcallWithObjectListArg(setLightsBind, handle, lights)
    }

    fun getUniqueNames(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetTypedStringList(getUniqueNamesBind, handle)
    }

    fun setUniqueNames(uniqueNames: List<String>) {
        ObjectCalls.ptrcallWithTypedStringListArg(setUniqueNamesBind, handle, uniqueNames)
    }

    fun getUniqueAnimationNames(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetTypedStringList(getUniqueAnimationNamesBind, handle)
    }

    fun setUniqueAnimationNames(uniqueAnimationNames: List<String>) {
        ObjectCalls.ptrcallWithTypedStringListArg(setUniqueAnimationNamesBind, handle, uniqueAnimationNames)
    }

    fun getSkeletons(): List<GLTFSkeleton> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getSkeletonsBind, handle, GLTFSkeleton::fromHandle)
    }

    fun setSkeletons(skeletons: List<GLTFSkeleton>) {
        ObjectCalls.ptrcallWithObjectListArg(setSkeletonsBind, handle, skeletons)
    }

    fun getCreateAnimations(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getCreateAnimationsBind, handle)
    }

    fun setCreateAnimations(createAnimations: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCreateAnimationsBind, handle, createAnimations)
    }

    fun getImportAsSkeletonBones(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getImportAsSkeletonBonesBind, handle)
    }

    fun setImportAsSkeletonBones(importAsSkeletonBones: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setImportAsSkeletonBonesBind, handle, importAsSkeletonBones)
    }

    fun getAnimations(): List<GLTFAnimation> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getAnimationsBind, handle, GLTFAnimation::fromHandle)
    }

    fun setAnimations(animations: List<GLTFAnimation>) {
        ObjectCalls.ptrcallWithObjectListArg(setAnimationsBind, handle, animations)
    }

    fun getSceneNode(gltfNodeIndex: Int): Node? {
        return Node.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSceneNodeBind, handle, gltfNodeIndex))
    }

    fun getNodeIndex(sceneNode: Node): Int {
        return ObjectCalls.ptrcallWithObjectArgRetInt(getNodeIndexBind, handle, sceneNode.handle)
    }

    fun getAdditionalData(extensionName: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getAdditionalDataBind, handle, extensionName)
    }

    fun setAdditionalData(extensionName: String, additionalData: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setAdditionalDataBind, handle, extensionName, additionalData)
    }

    fun getHandleBinaryImageMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHandleBinaryImageModeBind, handle)
    }

    fun setHandleBinaryImageMode(method: Long) {
        ObjectCalls.ptrcallWithLongArg(setHandleBinaryImageModeBind, handle, method)
    }

    fun setBakeFps(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBakeFpsBind, handle, value)
    }

    fun getBakeFps(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBakeFpsBind, handle)
    }

    fun getHandleBinaryImage(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getHandleBinaryImageBind, handle)
    }

    fun setHandleBinaryImage(method: Int) {
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

        @JvmStatic
        fun fromHandle(handle: MemorySegment): GLTFState? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GLTFState? =
            if (handle.address() == 0L) null else GLTFState(handle)

        private const val ADD_USED_EXTENSION_HASH = 2678287736L
        private val addUsedExtensionBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "add_used_extension", ADD_USED_EXTENSION_HASH)
        }

        private const val APPEND_DATA_TO_BUFFERS_HASH = 1460416665L
        private val appendDataToBuffersBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "append_data_to_buffers", APPEND_DATA_TO_BUFFERS_HASH)
        }

        private const val APPEND_GLTF_NODE_HASH = 3562288551L
        private val appendGltfNodeBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "append_gltf_node", APPEND_GLTF_NODE_HASH)
        }

        private const val GET_JSON_HASH = 3102165223L
        private val getJsonBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_json", GET_JSON_HASH)
        }

        private const val SET_JSON_HASH = 4155329257L
        private val setJsonBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_json", SET_JSON_HASH)
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

        private const val GET_GLB_DATA_HASH = 2362200018L
        private val getGlbDataBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_glb_data", GET_GLB_DATA_HASH)
        }

        private const val SET_GLB_DATA_HASH = 2971499966L
        private val setGlbDataBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_glb_data", SET_GLB_DATA_HASH)
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

        private const val SET_NODES_HASH = 381264803L
        private val setNodesBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_nodes", SET_NODES_HASH)
        }

        private const val GET_BUFFERS_HASH = 3995934104L
        private val getBuffersBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_buffers", GET_BUFFERS_HASH)
        }

        private const val SET_BUFFERS_HASH = 381264803L
        private val setBuffersBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_buffers", SET_BUFFERS_HASH)
        }

        private const val GET_BUFFER_VIEWS_HASH = 3995934104L
        private val getBufferViewsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_buffer_views", GET_BUFFER_VIEWS_HASH)
        }

        private const val SET_BUFFER_VIEWS_HASH = 381264803L
        private val setBufferViewsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_buffer_views", SET_BUFFER_VIEWS_HASH)
        }

        private const val GET_ACCESSORS_HASH = 3995934104L
        private val getAccessorsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_accessors", GET_ACCESSORS_HASH)
        }

        private const val SET_ACCESSORS_HASH = 381264803L
        private val setAccessorsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_accessors", SET_ACCESSORS_HASH)
        }

        private const val GET_MESHES_HASH = 3995934104L
        private val getMeshesBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_meshes", GET_MESHES_HASH)
        }

        private const val SET_MESHES_HASH = 381264803L
        private val setMeshesBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_meshes", SET_MESHES_HASH)
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

        private const val SET_MATERIALS_HASH = 381264803L
        private val setMaterialsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_materials", SET_MATERIALS_HASH)
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

        private const val SET_ROOT_NODES_HASH = 3614634198L
        private val setRootNodesBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_root_nodes", SET_ROOT_NODES_HASH)
        }

        private const val GET_TEXTURES_HASH = 3995934104L
        private val getTexturesBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_textures", GET_TEXTURES_HASH)
        }

        private const val SET_TEXTURES_HASH = 381264803L
        private val setTexturesBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_textures", SET_TEXTURES_HASH)
        }

        private const val GET_TEXTURE_SAMPLERS_HASH = 3995934104L
        private val getTextureSamplersBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_texture_samplers", GET_TEXTURE_SAMPLERS_HASH)
        }

        private const val SET_TEXTURE_SAMPLERS_HASH = 381264803L
        private val setTextureSamplersBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_texture_samplers", SET_TEXTURE_SAMPLERS_HASH)
        }

        private const val GET_IMAGES_HASH = 3995934104L
        private val getImagesBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_images", GET_IMAGES_HASH)
        }

        private const val SET_IMAGES_HASH = 381264803L
        private val setImagesBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_images", SET_IMAGES_HASH)
        }

        private const val GET_SKINS_HASH = 3995934104L
        private val getSkinsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_skins", GET_SKINS_HASH)
        }

        private const val SET_SKINS_HASH = 381264803L
        private val setSkinsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_skins", SET_SKINS_HASH)
        }

        private const val GET_CAMERAS_HASH = 3995934104L
        private val getCamerasBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_cameras", GET_CAMERAS_HASH)
        }

        private const val SET_CAMERAS_HASH = 381264803L
        private val setCamerasBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_cameras", SET_CAMERAS_HASH)
        }

        private const val GET_LIGHTS_HASH = 3995934104L
        private val getLightsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_lights", GET_LIGHTS_HASH)
        }

        private const val SET_LIGHTS_HASH = 381264803L
        private val setLightsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_lights", SET_LIGHTS_HASH)
        }

        private const val GET_UNIQUE_NAMES_HASH = 3995934104L
        private val getUniqueNamesBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_unique_names", GET_UNIQUE_NAMES_HASH)
        }

        private const val SET_UNIQUE_NAMES_HASH = 381264803L
        private val setUniqueNamesBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_unique_names", SET_UNIQUE_NAMES_HASH)
        }

        private const val GET_UNIQUE_ANIMATION_NAMES_HASH = 3995934104L
        private val getUniqueAnimationNamesBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_unique_animation_names", GET_UNIQUE_ANIMATION_NAMES_HASH)
        }

        private const val SET_UNIQUE_ANIMATION_NAMES_HASH = 381264803L
        private val setUniqueAnimationNamesBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_unique_animation_names", SET_UNIQUE_ANIMATION_NAMES_HASH)
        }

        private const val GET_SKELETONS_HASH = 3995934104L
        private val getSkeletonsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "get_skeletons", GET_SKELETONS_HASH)
        }

        private const val SET_SKELETONS_HASH = 381264803L
        private val setSkeletonsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_skeletons", SET_SKELETONS_HASH)
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

        private const val SET_ANIMATIONS_HASH = 381264803L
        private val setAnimationsBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_animations", SET_ANIMATIONS_HASH)
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

        private const val SET_ADDITIONAL_DATA_HASH = 3776071444L
        private val setAdditionalDataBind by lazy {
            ObjectCalls.getMethodBind("GLTFState", "set_additional_data", SET_ADDITIONAL_DATA_HASH)
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
