package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: GLTFState
 */
open class GLTFState(handle: GodotHandle) : Resource(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithStringAndBoolArg(addUsedExtensionBind, segment, extensionName, required)
    }

    fun appendDataToBuffers(data: ByteArray, deduplication: Boolean): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithByteArrayAndBoolArgRetInt(appendDataToBuffersBind, segment, data, deduplication)
    }

    fun appendGltfNode(gltfNode: GLTFNode?, godotSceneNode: Node, parentNodeIndex: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoObjectIntArgsRetInt(appendGltfNodeBind, segment, gltfNode?.requireOpenHandle() ?: NULL_SEGMENT, godotSceneNode.segment, parentNodeIndex)
    }

    fun getJson(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(getJsonBind, segment)
    }

    fun setJson(json: Map<String, Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithDictionaryArg(setJsonBind, segment, json)
    }

    fun getMajorVersion(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getMajorVersionBind, segment)
    }

    fun setMajorVersion(majorVersion: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setMajorVersionBind, segment, majorVersion)
    }

    fun getMinorVersion(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getMinorVersionBind, segment)
    }

    fun setMinorVersion(minorVersion: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setMinorVersionBind, segment, minorVersion)
    }

    fun getCopyright(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getCopyrightBind, segment)
    }

    fun setCopyright(copyright: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setCopyrightBind, segment, copyright)
    }

    fun getGlbData(): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetByteArray(getGlbDataBind, segment)
    }

    fun setGlbData(glbData: ByteArray) {
        checkOpen()
        ObjectCalls.ptrcallWithByteArrayArg(setGlbDataBind, segment, glbData)
    }

    fun getUseNamedSkinBinds(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getUseNamedSkinBindsBind, segment)
    }

    fun setUseNamedSkinBinds(useNamedSkinBinds: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setUseNamedSkinBindsBind, segment, useNamedSkinBinds)
    }

    fun getNodes(): List<GLTFNode> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getNodesBind, segment, GLTFNode::wrap)
    }

    fun setNodes(nodes: List<GLTFNode>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(setNodesBind, segment, nodes)
    }

    fun getBuffers(): List<ByteArray> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetByteArrayList(getBuffersBind, segment)
    }

    fun setBuffers(buffers: List<ByteArray>) {
        checkOpen()
        ObjectCalls.ptrcallWithByteArrayListArg(setBuffersBind, segment, buffers)
    }

    fun getBufferViews(): List<GLTFBufferView> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getBufferViewsBind, segment, GLTFBufferView::wrap)
    }

    fun setBufferViews(bufferViews: List<GLTFBufferView>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(setBufferViewsBind, segment, bufferViews)
    }

    fun getAccessors(): List<GLTFAccessor> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getAccessorsBind, segment, GLTFAccessor::wrap)
    }

    fun setAccessors(accessors: List<GLTFAccessor>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(setAccessorsBind, segment, accessors)
    }

    fun getMeshes(): List<GLTFMesh> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getMeshesBind, segment, GLTFMesh::wrap)
    }

    fun setMeshes(meshes: List<GLTFMesh>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(setMeshesBind, segment, meshes)
    }

    fun getAnimationPlayersCount(animPlayerIndex: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(getAnimationPlayersCountBind, segment, animPlayerIndex)
    }

    fun getAnimationPlayer(animPlayerIndex: Int): AnimationPlayer? {
        checkOpen()
        return AnimationPlayer.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getAnimationPlayerBind, segment, animPlayerIndex))
    }

    fun getMaterials(): List<Material> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getMaterialsBind, segment, Material::wrap)
    }

    fun setMaterials(materials: List<Material>) {
        checkOpen()
        ObjectCalls.ptrcallWithTypedMaterialListArg(setMaterialsBind, segment, materials)
    }

    fun getSceneName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getSceneNameBind, segment)
    }

    fun setSceneName(sceneName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setSceneNameBind, segment, sceneName)
    }

    fun getBasePath(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getBasePathBind, segment)
    }

    fun setBasePath(basePath: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setBasePathBind, segment, basePath)
    }

    fun getFilename(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getFilenameBind, segment)
    }

    fun setFilename(filename: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setFilenameBind, segment, filename)
    }

    fun getRootNodes(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getRootNodesBind, segment)
    }

    fun setRootNodes(rootNodes: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt32ListArg(setRootNodesBind, segment, rootNodes)
    }

    fun getTextures(): List<GLTFTexture> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getTexturesBind, segment, GLTFTexture::wrap)
    }

    fun setTextures(textures: List<GLTFTexture>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(setTexturesBind, segment, textures)
    }

    fun getTextureSamplers(): List<GLTFTextureSampler> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getTextureSamplersBind, segment, GLTFTextureSampler::wrap)
    }

    fun setTextureSamplers(textureSamplers: List<GLTFTextureSampler>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(setTextureSamplersBind, segment, textureSamplers)
    }

    fun getImages(): List<Texture2D> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getImagesBind, segment, Texture2D::wrap)
    }

    fun setImages(images: List<Texture2D>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(setImagesBind, segment, images)
    }

    fun getSkins(): List<GLTFSkin> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getSkinsBind, segment, GLTFSkin::wrap)
    }

    fun setSkins(skins: List<GLTFSkin>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(setSkinsBind, segment, skins)
    }

    fun getCameras(): List<GLTFCamera> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getCamerasBind, segment, GLTFCamera::wrap)
    }

    fun setCameras(cameras: List<GLTFCamera>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(setCamerasBind, segment, cameras)
    }

    fun getLights(): List<GLTFLight> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getLightsBind, segment, GLTFLight::wrap)
    }

    fun setLights(lights: List<GLTFLight>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(setLightsBind, segment, lights)
    }

    fun getUniqueNames(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedStringList(getUniqueNamesBind, segment)
    }

    fun setUniqueNames(uniqueNames: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithTypedStringListArg(setUniqueNamesBind, segment, uniqueNames)
    }

    fun getUniqueAnimationNames(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedStringList(getUniqueAnimationNamesBind, segment)
    }

    fun setUniqueAnimationNames(uniqueAnimationNames: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithTypedStringListArg(setUniqueAnimationNamesBind, segment, uniqueAnimationNames)
    }

    fun getSkeletons(): List<GLTFSkeleton> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getSkeletonsBind, segment, GLTFSkeleton::wrap)
    }

    fun setSkeletons(skeletons: List<GLTFSkeleton>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(setSkeletonsBind, segment, skeletons)
    }

    fun getCreateAnimations(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getCreateAnimationsBind, segment)
    }

    fun setCreateAnimations(createAnimations: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setCreateAnimationsBind, segment, createAnimations)
    }

    fun getImportAsSkeletonBones(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getImportAsSkeletonBonesBind, segment)
    }

    fun setImportAsSkeletonBones(importAsSkeletonBones: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setImportAsSkeletonBonesBind, segment, importAsSkeletonBones)
    }

    fun getAnimations(): List<GLTFAnimation> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getAnimationsBind, segment, GLTFAnimation::wrap)
    }

    fun setAnimations(animations: List<GLTFAnimation>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(setAnimationsBind, segment, animations)
    }

    fun getSceneNode(gltfNodeIndex: Int): Node? {
        checkOpen()
        return Node.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSceneNodeBind, segment, gltfNodeIndex))
    }

    fun getNodeIndex(sceneNode: Node): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectArgRetInt(getNodeIndexBind, segment, sceneNode.segment)
    }

    fun getAdditionalData(extensionName: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getAdditionalDataBind, segment, extensionName)
    }

    fun setAdditionalData(extensionName: String, additionalData: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameAndVariantArg(setAdditionalDataBind, segment, extensionName, additionalData)
    }

    fun getHandleBinaryImageMode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getHandleBinaryImageModeBind, segment)
    }

    fun setHandleBinaryImageMode(method: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setHandleBinaryImageModeBind, segment, method)
    }

    fun setBakeFps(value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setBakeFpsBind, segment, value)
    }

    fun getBakeFps(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getBakeFpsBind, segment)
    }

    fun getHandleBinaryImage(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getHandleBinaryImageBind, segment)
    }

    fun setHandleBinaryImage(method: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setHandleBinaryImageBind, segment, method)
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
        fun fromHandle(handle: GodotHandle): GLTFState? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GLTFState? =
            if (handle.address() == 0L) null else GLTFState(GodotHandle(handle))

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
