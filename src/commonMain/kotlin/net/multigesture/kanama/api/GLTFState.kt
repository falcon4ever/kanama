package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Represents all data of a glTF file.
 *
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

    var handleBinaryImageMode: GLTFState.HandleBinaryImageMode
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

    /**
     * Appends an extension to the list of extensions used by this glTF file during serialization. If
     * `required` is `true`, the extension will also be added to the list of required extensions. Do
     * not run this in `GLTFDocumentExtension._export_post`, as that stage is too late to add
     * extensions. The final list is sorted alphabetically.
     *
     * Generated from Godot docs: GLTFState.add_used_extension
     */
    fun addUsedExtension(extensionName: String, required: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithStringAndBoolArg(Binds.addUsedExtensionBind, segment, extensionName, required)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Appends the given byte array `data` to the buffers and creates a `GLTFBufferView` for it. The
     * index of the destination `GLTFBufferView` is returned. If `deduplication` is `true`, the buffers
     * are first searched for duplicate data, otherwise new bytes are always appended.
     *
     * Generated from Godot docs: GLTFState.append_data_to_buffers
     */
    fun appendDataToBuffers(data: ByteArray, deduplication: Boolean): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithByteArrayAndBoolArgRetInt(Binds.appendDataToBuffersBind, segment, data, deduplication)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Appends the given `GLTFNode` to the state, and returns its new index. This can be used to export
     * one Godot node as multiple glTF nodes, or inject new glTF nodes at import time. On import, this
     * must be called before `GLTFDocumentExtension._generate_scene_node` finishes for the parent node.
     * On export, this must be called before `GLTFDocumentExtension._export_node` runs for the parent
     * node. The `godot_scene_node` parameter is the Godot scene node that corresponds to this glTF
     * node. This is highly recommended to be set to a valid node, but may be `null` if there is no
     * corresponding Godot scene node. One Godot scene node may be used for multiple glTF nodes, so if
     * exporting multiple glTF nodes for one Godot scene node, use the same Godot scene node for each.
     * The `parent_node_index` parameter is the index of the parent `GLTFNode` in the state. If `-1`,
     * the node will be a root node, otherwise the new node will be added to the parent's list of
     * children. The index will also be written to the `GLTFNode.parent` property of the new node.
     *
     * Generated from Godot docs: GLTFState.append_gltf_node
     */
    fun appendGltfNode(gltfNode: GLTFNode?, godotSceneNode: Node, parentNodeIndex: Int): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithTwoObjectIntArgsRetInt(Binds.appendGltfNodeBind, segment, gltfNode?.requireOpenHandle() ?: NULL_SEGMENT, godotSceneNode.segment, parentNodeIndex)
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(gltfNode)
        ObjectCalls.keepReachable(godotSceneNode)
        return ret
    }

    /**
     * The original raw JSON document corresponding to this GLTFState.
     *
     * Generated from Godot docs: GLTFState.get_json
     */
    fun getJson(): Map<String, Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDictionary(Binds.getJsonBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The original raw JSON document corresponding to this GLTFState.
     *
     * Generated from Godot docs: GLTFState.set_json
     */
    fun setJson(json: Map<String, Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithDictionaryArg(Binds.setJsonBind, segment, json)
        ObjectCalls.keepReachable(this)
    }

    fun getMajorVersion(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getMajorVersionBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun setMajorVersion(majorVersion: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMajorVersionBind, segment, majorVersion)
        ObjectCalls.keepReachable(this)
    }

    fun getMinorVersion(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getMinorVersionBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun setMinorVersion(minorVersion: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMinorVersionBind, segment, minorVersion)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The copyright string in the asset header of the glTF file. This is set during import if present
     * and export if non-empty. See the glTF asset header documentation for more information.
     *
     * Generated from Godot docs: GLTFState.get_copyright
     */
    fun getCopyright(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getCopyrightBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The copyright string in the asset header of the glTF file. This is set during import if present
     * and export if non-empty. See the glTF asset header documentation for more information.
     *
     * Generated from Godot docs: GLTFState.set_copyright
     */
    fun setCopyright(copyright: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setCopyrightBind, segment, copyright)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The binary buffer attached to a .glb file.
     *
     * Generated from Godot docs: GLTFState.get_glb_data
     */
    fun getGlbData(): ByteArray {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetByteArray(Binds.getGlbDataBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The binary buffer attached to a .glb file.
     *
     * Generated from Godot docs: GLTFState.set_glb_data
     */
    fun setGlbData(glbData: ByteArray) {
        checkOpen()
        ObjectCalls.ptrcallWithByteArrayArg(Binds.setGlbDataBind, segment, glbData)
        ObjectCalls.keepReachable(this)
    }

    fun getUseNamedSkinBinds(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.getUseNamedSkinBindsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun setUseNamedSkinBinds(useNamedSkinBinds: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseNamedSkinBindsBind, segment, useNamedSkinBinds)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns an array of all `GLTFNode`s in the glTF file. These are the nodes that
     * `GLTFNode.children` and `root_nodes` refer to. This includes nodes that may not be generated in
     * the Godot scene, or nodes that may generate multiple Godot scene nodes.
     *
     * Generated from Godot docs: GLTFState.get_nodes
     */
    fun getNodes(): List<GLTFNode> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getNodesBind, segment, GLTFNode::wrapBorrowed)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the `GLTFNode`s in the state. These are the nodes that `GLTFNode.children` and `root_nodes`
     * refer to. Some of the nodes set here may not be generated in the Godot scene, or may generate
     * multiple Godot scene nodes.
     *
     * Generated from Godot docs: GLTFState.set_nodes
     */
    fun setNodes(nodes: List<GLTFNode>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(Binds.setNodesBind, segment, nodes)
        ObjectCalls.keepReachable(this)
    }

    fun getBuffers(): List<ByteArray> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetByteArrayList(Binds.getBuffersBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun setBuffers(buffers: List<ByteArray>) {
        checkOpen()
        ObjectCalls.ptrcallWithByteArrayListArg(Binds.setBuffersBind, segment, buffers)
        ObjectCalls.keepReachable(this)
    }

    fun getBufferViews(): List<GLTFBufferView> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getBufferViewsBind, segment, GLTFBufferView::wrapBorrowed)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun setBufferViews(bufferViews: List<GLTFBufferView>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(Binds.setBufferViewsBind, segment, bufferViews)
        ObjectCalls.keepReachable(this)
    }

    fun getAccessors(): List<GLTFAccessor> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getAccessorsBind, segment, GLTFAccessor::wrapBorrowed)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun setAccessors(accessors: List<GLTFAccessor>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(Binds.setAccessorsBind, segment, accessors)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns an array of all `GLTFMesh`es in the glTF file. These are the meshes that the
     * `GLTFNode.mesh` index refers to.
     *
     * Generated from Godot docs: GLTFState.get_meshes
     */
    fun getMeshes(): List<GLTFMesh> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getMeshesBind, segment, GLTFMesh::wrapBorrowed)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the `GLTFMesh`es in the state. These are the meshes that the `GLTFNode.mesh` index refers
     * to.
     *
     * Generated from Godot docs: GLTFState.set_meshes
     */
    fun setMeshes(meshes: List<GLTFMesh>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(Binds.setMeshesBind, segment, meshes)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the number of `AnimationPlayer` nodes in this `GLTFState`. These nodes are only used
     * during the export process when converting Godot `AnimationPlayer` nodes to glTF animations.
     *
     * Generated from Godot docs: GLTFState.get_animation_players_count
     */
    fun getAnimationPlayersCount(animPlayerIndex: Int): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetInt(Binds.getAnimationPlayersCountBind, segment, animPlayerIndex)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the `AnimationPlayer` node with the given index. These nodes are only used during the
     * export process when converting Godot `AnimationPlayer` nodes to glTF animations.
     *
     * Generated from Godot docs: GLTFState.get_animation_player
     */
    fun getAnimationPlayer(animPlayerIndex: Int): AnimationPlayer? {
        checkOpen()
        val ret = AnimationPlayer.wrap(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getAnimationPlayerBind, segment, animPlayerIndex))
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun getMaterials(): List<Material> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getMaterialsBind, segment, Material::wrapBorrowed)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun setMaterials(materials: List<Material>) {
        checkOpen()
        ObjectCalls.ptrcallWithTypedMaterialListArg(Binds.setMaterialsBind, segment, materials)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The name of the scene. When importing, if not specified, this will be the file name. When
     * exporting, if specified, the scene name will be saved to the glTF file.
     *
     * Generated from Godot docs: GLTFState.get_scene_name
     */
    fun getSceneName(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getSceneNameBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The name of the scene. When importing, if not specified, this will be the file name. When
     * exporting, if specified, the scene name will be saved to the glTF file.
     *
     * Generated from Godot docs: GLTFState.set_scene_name
     */
    fun setSceneName(sceneName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setSceneNameBind, segment, sceneName)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The folder path associated with this glTF data. This is used to find other files the glTF file
     * references, like images or binary buffers. This will be set during import when appending from a
     * file, and will be set during export when writing to a file.
     *
     * Generated from Godot docs: GLTFState.get_base_path
     */
    fun getBasePath(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getBasePathBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The folder path associated with this glTF data. This is used to find other files the glTF file
     * references, like images or binary buffers. This will be set during import when appending from a
     * file, and will be set during export when writing to a file.
     *
     * Generated from Godot docs: GLTFState.set_base_path
     */
    fun setBasePath(basePath: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setBasePathBind, segment, basePath)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The file name associated with this glTF data. If it ends with `.gltf`, this is text-based glTF,
     * otherwise this is binary GLB. This will be set during import when appending from a file, and
     * will be set during export when writing to a file. If writing to a buffer, this will be an empty
     * string.
     *
     * Generated from Godot docs: GLTFState.get_filename
     */
    fun getFilename(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getFilenameBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The file name associated with this glTF data. If it ends with `.gltf`, this is text-based glTF,
     * otherwise this is binary GLB. This will be set during import when appending from a file, and
     * will be set during export when writing to a file. If writing to a buffer, this will be an empty
     * string.
     *
     * Generated from Godot docs: GLTFState.set_filename
     */
    fun setFilename(filename: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setFilenameBind, segment, filename)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The root nodes of the glTF file. Typically, a glTF file will only have one scene, and therefore
     * one root node. However, a glTF file may have multiple scenes and therefore multiple root nodes,
     * which will be generated as siblings of each other and as children of the root node of the
     * generated Godot scene.
     *
     * Generated from Godot docs: GLTFState.get_root_nodes
     */
    fun getRootNodes(): List<Int> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedInt32List(Binds.getRootNodesBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The root nodes of the glTF file. Typically, a glTF file will only have one scene, and therefore
     * one root node. However, a glTF file may have multiple scenes and therefore multiple root nodes,
     * which will be generated as siblings of each other and as children of the root node of the
     * generated Godot scene.
     *
     * Generated from Godot docs: GLTFState.set_root_nodes
     */
    fun setRootNodes(rootNodes: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt32ListArg(Binds.setRootNodesBind, segment, rootNodes)
        ObjectCalls.keepReachable(this)
    }

    fun getTextures(): List<GLTFTexture> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getTexturesBind, segment, GLTFTexture::wrapBorrowed)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun setTextures(textures: List<GLTFTexture>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(Binds.setTexturesBind, segment, textures)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Retrieves the array of texture samplers that are used by the textures contained in the glTF.
     *
     * Generated from Godot docs: GLTFState.get_texture_samplers
     */
    fun getTextureSamplers(): List<GLTFTextureSampler> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getTextureSamplersBind, segment, GLTFTextureSampler::wrapBorrowed)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the array of texture samplers that are used by the textures contained in the glTF.
     *
     * Generated from Godot docs: GLTFState.set_texture_samplers
     */
    fun setTextureSamplers(textureSamplers: List<GLTFTextureSampler>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(Binds.setTextureSamplersBind, segment, textureSamplers)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Gets the images of the glTF file as an array of `Texture2D`s. These are the images that the
     * `GLTFTexture.src_image` index refers to.
     *
     * Generated from Godot docs: GLTFState.get_images
     */
    fun getImages(): List<Texture2D> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getImagesBind, segment, Texture2D::wrapBorrowed)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the images in the state stored as an array of `Texture2D`s. This can be used during export.
     * These are the images that the `GLTFTexture.src_image` index refers to.
     *
     * Generated from Godot docs: GLTFState.set_images
     */
    fun setImages(images: List<Texture2D>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(Binds.setImagesBind, segment, images)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns an array of all `GLTFSkin`s in the glTF file. These are the skins that the
     * `GLTFNode.skin` index refers to.
     *
     * Generated from Godot docs: GLTFState.get_skins
     */
    fun getSkins(): List<GLTFSkin> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getSkinsBind, segment, GLTFSkin::wrapBorrowed)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the `GLTFSkin`s in the state. These are the skins that the `GLTFNode.skin` index refers to.
     *
     * Generated from Godot docs: GLTFState.set_skins
     */
    fun setSkins(skins: List<GLTFSkin>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(Binds.setSkinsBind, segment, skins)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns an array of all `GLTFCamera`s in the glTF file. These are the cameras that the
     * `GLTFNode.camera` index refers to.
     *
     * Generated from Godot docs: GLTFState.get_cameras
     */
    fun getCameras(): List<GLTFCamera> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getCamerasBind, segment, GLTFCamera::wrapBorrowed)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the `GLTFCamera`s in the state. These are the cameras that the `GLTFNode.camera` index
     * refers to.
     *
     * Generated from Godot docs: GLTFState.set_cameras
     */
    fun setCameras(cameras: List<GLTFCamera>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(Binds.setCamerasBind, segment, cameras)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns an array of all `GLTFLight`s in the glTF file. These are the lights that the
     * `GLTFNode.light` index refers to.
     *
     * Generated from Godot docs: GLTFState.get_lights
     */
    fun getLights(): List<GLTFLight> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getLightsBind, segment, GLTFLight::wrapBorrowed)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the `GLTFLight`s in the state. These are the lights that the `GLTFNode.light` index refers
     * to.
     *
     * Generated from Godot docs: GLTFState.set_lights
     */
    fun setLights(lights: List<GLTFLight>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(Binds.setLightsBind, segment, lights)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns an array of unique node names. This is used in both the import process and export
     * process.
     *
     * Generated from Godot docs: GLTFState.get_unique_names
     */
    fun getUniqueNames(): List<String> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetTypedStringList(Binds.getUniqueNamesBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the unique node names in the state. This is used in both the import process and export
     * process.
     *
     * Generated from Godot docs: GLTFState.set_unique_names
     */
    fun setUniqueNames(uniqueNames: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithTypedStringListArg(Binds.setUniqueNamesBind, segment, uniqueNames)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns an array of unique animation names. This is only used during the import process.
     *
     * Generated from Godot docs: GLTFState.get_unique_animation_names
     */
    fun getUniqueAnimationNames(): List<String> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetTypedStringList(Binds.getUniqueAnimationNamesBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the unique animation names in the state. This is only used during the import process.
     *
     * Generated from Godot docs: GLTFState.set_unique_animation_names
     */
    fun setUniqueAnimationNames(uniqueAnimationNames: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithTypedStringListArg(Binds.setUniqueAnimationNamesBind, segment, uniqueAnimationNames)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns an array of all `GLTFSkeleton`s in the glTF file. These are the skeletons that the
     * `GLTFNode.skeleton` index refers to.
     *
     * Generated from Godot docs: GLTFState.get_skeletons
     */
    fun getSkeletons(): List<GLTFSkeleton> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getSkeletonsBind, segment, GLTFSkeleton::wrapBorrowed)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the `GLTFSkeleton`s in the state. These are the skeletons that the `GLTFNode.skeleton`
     * index refers to.
     *
     * Generated from Godot docs: GLTFState.set_skeletons
     */
    fun setSkeletons(skeletons: List<GLTFSkeleton>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(Binds.setSkeletonsBind, segment, skeletons)
        ObjectCalls.keepReachable(this)
    }

    fun getCreateAnimations(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.getCreateAnimationsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun setCreateAnimations(createAnimations: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setCreateAnimationsBind, segment, createAnimations)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If `true`, forces all GLTFNodes in the document to be bones of a single `Skeleton3D` Godot node.
     *
     * Generated from Godot docs: GLTFState.get_import_as_skeleton_bones
     */
    fun getImportAsSkeletonBones(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.getImportAsSkeletonBonesBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `true`, forces all GLTFNodes in the document to be bones of a single `Skeleton3D` Godot node.
     *
     * Generated from Godot docs: GLTFState.set_import_as_skeleton_bones
     */
    fun setImportAsSkeletonBones(importAsSkeletonBones: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setImportAsSkeletonBonesBind, segment, importAsSkeletonBones)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns an array of all `GLTFAnimation`s in the glTF file. When importing, these will be
     * generated as animations in an `AnimationPlayer` node. When exporting, these will be generated
     * from Godot `AnimationPlayer` nodes.
     *
     * Generated from Godot docs: GLTFState.get_animations
     */
    fun getAnimations(): List<GLTFAnimation> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getAnimationsBind, segment, GLTFAnimation::wrapBorrowed)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the `GLTFAnimation`s in the state. When importing, these will be generated as animations in
     * an `AnimationPlayer` node. When exporting, these will be generated from Godot `AnimationPlayer`
     * nodes.
     *
     * Generated from Godot docs: GLTFState.set_animations
     */
    fun setAnimations(animations: List<GLTFAnimation>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(Binds.setAnimationsBind, segment, animations)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the Godot scene node that corresponds to the same index as the `GLTFNode` it was
     * generated from. This is the inverse of `get_node_index`. Useful during the import process. Note:
     * Not every `GLTFNode` will have a scene node generated, and not every generated scene node will
     * have a corresponding `GLTFNode`. If there is no scene node for this `GLTFNode` index, `null` is
     * returned.
     *
     * Generated from Godot docs: GLTFState.get_scene_node
     */
    fun getSceneNode(gltfNodeIndex: Int): Node? {
        checkOpen()
        val ret = Node.wrap(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getSceneNodeBind, segment, gltfNodeIndex))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the index of the `GLTFNode` corresponding to this Godot scene node. This is the inverse
     * of `get_scene_node`. Useful during the export process. Note: Not every Godot scene node will
     * have a corresponding `GLTFNode`, and not every `GLTFNode` will have a scene node generated. If
     * there is no `GLTFNode` index for this scene node, `-1` is returned.
     *
     * Generated from Godot docs: GLTFState.get_node_index
     */
    fun getNodeIndex(sceneNode: Node): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithObjectArgRetInt(Binds.getNodeIndexBind, segment, sceneNode.segment)
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(sceneNode)
        return ret
    }

    /**
     * Gets additional arbitrary data in this `GLTFState` instance. This can be used to keep per-file
     * state data in `GLTFDocumentExtension` classes, which is important because they are stateless.
     * The argument should be the `GLTFDocumentExtension` name (does not have to match the extension
     * name in the glTF file), and the return value can be anything you set. If nothing was set, the
     * return value is `null`.
     *
     * Generated from Godot docs: GLTFState.get_additional_data
     */
    fun getAdditionalData(extensionName: String): Any? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(Binds.getAdditionalDataBind, segment, extensionName)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets additional arbitrary data in this `GLTFState` instance. This can be used to keep per-file
     * state data in `GLTFDocumentExtension` classes, which is important because they are stateless.
     * The first argument should be the `GLTFDocumentExtension` name (does not have to match the
     * extension name in the glTF file), and the second argument can be anything you want.
     *
     * Generated from Godot docs: GLTFState.set_additional_data
     */
    fun setAdditionalData(extensionName: String, additionalData: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameAndVariantArg(Binds.setAdditionalDataBind, segment, extensionName, additionalData)
        ObjectCalls.keepReachable(this)
    }

    /**
     * When importing a glTF file with unimported raw binary images embedded inside of binary blob
     * buffers, in data URIs, or separate files not imported by Godot, this controls how the images are
     * handled. Images can be discarded, saved as separate files, or embedded in the scene lossily or
     * losslessly. See `HandleBinaryImageMode` for options. This property does nothing for image files
     * in the `res://` folder imported by Godot, as those are handled by Godot's image importer
     * directly, and then the Godot scene generated from the glTF file will use the images as Godot
     * imported them.
     *
     * Generated from Godot docs: GLTFState.get_handle_binary_image_mode
     */
    fun getHandleBinaryImageMode(): GLTFState.HandleBinaryImageMode {
        checkOpen()
        val ret = GLTFState.HandleBinaryImageMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getHandleBinaryImageModeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * When importing a glTF file with unimported raw binary images embedded inside of binary blob
     * buffers, in data URIs, or separate files not imported by Godot, this controls how the images are
     * handled. Images can be discarded, saved as separate files, or embedded in the scene lossily or
     * losslessly. See `HandleBinaryImageMode` for options. This property does nothing for image files
     * in the `res://` folder imported by Godot, as those are handled by Godot's image importer
     * directly, and then the Godot scene generated from the glTF file will use the images as Godot
     * imported them.
     *
     * Generated from Godot docs: GLTFState.set_handle_binary_image_mode
     */
    fun setHandleBinaryImageMode(method: GLTFState.HandleBinaryImageMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setHandleBinaryImageModeBind, segment, method.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The baking fps of the animation for either import or export.
     *
     * Generated from Godot docs: GLTFState.set_bake_fps
     */
    fun setBakeFps(value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setBakeFpsBind, segment, value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The baking fps of the animation for either import or export.
     *
     * Generated from Godot docs: GLTFState.get_bake_fps
     */
    fun getBakeFps(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getBakeFpsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Deprecated untyped alias for `handle_binary_image_mode`. When importing a glTF file with
     * unimported raw binary images embedded inside of binary blob buffers, in data URIs, or separate
     * files not imported by Godot, this controls how the images are handled.
     *
     * Generated from Godot docs: GLTFState.get_handle_binary_image
     */
    fun getHandleBinaryImage(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getHandleBinaryImageBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Deprecated untyped alias for `handle_binary_image_mode`. When importing a glTF file with
     * unimported raw binary images embedded inside of binary blob buffers, in data URIs, or separate
     * files not imported by Godot, this controls how the images are handled.
     *
     * Generated from Godot docs: GLTFState.set_handle_binary_image
     */
    fun setHandleBinaryImage(method: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setHandleBinaryImageBind, segment, method)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Godot's `GLTFState.HandleBinaryImageMode` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`GLTFState.HandleBinaryImageMode.<NAME>`).
     *
     * Generated from Godot docs: GLTFState.HandleBinaryImageMode
     */
    @JvmInline
    value class HandleBinaryImageMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * When importing a glTF file with embedded binary images, discards all images and uses untextured
             * materials in their place. Images stored as separate files in the `res://` folder are not
             * affected by this; those will be used as Godot imported them.
             *
             * Generated from Godot docs: GLTFState.HANDLE_BINARY_IMAGE_MODE_DISCARD_TEXTURES
             */
            val DISCARD_TEXTURES: HandleBinaryImageMode get() = HandleBinaryImageMode(0L)
            /**
             * When importing a glTF file with embedded binary images, extracts them and saves them to their
             * own files. This allows the image to be imported by Godot's image importer, which can then have
             * their import options customized by the user, including optionally compressing the image to VRAM
             * texture formats. This will save the images's bytes exactly as-is, without recompression. For
             * image formats supplied by glTF extensions, the file will have a filename ending with the file
             * extension supplied by `GLTFDocumentExtension._get_image_file_extension` of the extension class.
             * Note: This option is editor-only. At runtime, this acts the same as
             * `HandleBinaryImageMode.EMBED_AS_UNCOMPRESSED`.
             *
             * Generated from Godot docs: GLTFState.HANDLE_BINARY_IMAGE_MODE_EXTRACT_TEXTURES
             */
            val EXTRACT_TEXTURES: HandleBinaryImageMode get() = HandleBinaryImageMode(1L)
            /**
             * When importing a glTF file with embedded binary images, embeds textures VRAM compressed with
             * Basis Universal into the generated scene. Images stored as separate files in the `res://` folder
             * are not affected by this; those will be used as Godot imported them.
             *
             * Generated from Godot docs: GLTFState.HANDLE_BINARY_IMAGE_MODE_EMBED_AS_BASISU
             */
            val EMBED_AS_BASISU: HandleBinaryImageMode get() = HandleBinaryImageMode(2L)
            /**
             * When importing a glTF file with embedded binary images, embeds textures compressed losslessly
             * into the generated scene. Images stored as separate files in the `res://` folder are not
             * affected by this; those will be used as Godot imported them.
             *
             * Generated from Godot docs: GLTFState.HANDLE_BINARY_IMAGE_MODE_EMBED_AS_UNCOMPRESSED
             */
            val EMBED_AS_UNCOMPRESSED: HandleBinaryImageMode get() = HandleBinaryImageMode(3L)
        }
    }

    companion object {
        const val HANDLE_BINARY_DISCARD_TEXTURES: Long = 0L
        const val HANDLE_BINARY_EXTRACT_TEXTURES: Long = 1L
        const val HANDLE_BINARY_EMBED_AS_BASISU: Long = 2L
        const val HANDLE_BINARY_EMBED_AS_UNCOMPRESSED: Long = 3L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFState? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GLTFState? =
            if (handle.address() == 0L) null else RefCounted.owned(GLTFState(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GLTFState? =
            if (handle.address() == 0L) null else GLTFState(GodotHandle(handle))

        /** A new GLTFState, GDScript's `GLTFState.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): GLTFState =
            RefCounted.owned(GLTFState(GodotHandle(requireConstructed(ObjectCalls.constructObject("GLTFState"), "GLTFState"))))
    }

    private object Binds {
        private const val ADD_USED_EXTENSION_HASH = 2678287736L
        @JvmField
        val addUsedExtensionBind =
            ObjectCalls.getMethodBind("GLTFState", "add_used_extension", ADD_USED_EXTENSION_HASH)

        private const val APPEND_DATA_TO_BUFFERS_HASH = 1460416665L
        @JvmField
        val appendDataToBuffersBind =
            ObjectCalls.getMethodBind("GLTFState", "append_data_to_buffers", APPEND_DATA_TO_BUFFERS_HASH)

        private const val APPEND_GLTF_NODE_HASH = 3562288551L
        @JvmField
        val appendGltfNodeBind =
            ObjectCalls.getMethodBind("GLTFState", "append_gltf_node", APPEND_GLTF_NODE_HASH)

        private const val GET_JSON_HASH = 3102165223L
        @JvmField
        val getJsonBind =
            ObjectCalls.getMethodBind("GLTFState", "get_json", GET_JSON_HASH)

        private const val SET_JSON_HASH = 4155329257L
        @JvmField
        val setJsonBind =
            ObjectCalls.getMethodBind("GLTFState", "set_json", SET_JSON_HASH)

        private const val GET_MAJOR_VERSION_HASH = 3905245786L
        @JvmField
        val getMajorVersionBind =
            ObjectCalls.getMethodBind("GLTFState", "get_major_version", GET_MAJOR_VERSION_HASH)

        private const val SET_MAJOR_VERSION_HASH = 1286410249L
        @JvmField
        val setMajorVersionBind =
            ObjectCalls.getMethodBind("GLTFState", "set_major_version", SET_MAJOR_VERSION_HASH)

        private const val GET_MINOR_VERSION_HASH = 3905245786L
        @JvmField
        val getMinorVersionBind =
            ObjectCalls.getMethodBind("GLTFState", "get_minor_version", GET_MINOR_VERSION_HASH)

        private const val SET_MINOR_VERSION_HASH = 1286410249L
        @JvmField
        val setMinorVersionBind =
            ObjectCalls.getMethodBind("GLTFState", "set_minor_version", SET_MINOR_VERSION_HASH)

        private const val GET_COPYRIGHT_HASH = 201670096L
        @JvmField
        val getCopyrightBind =
            ObjectCalls.getMethodBind("GLTFState", "get_copyright", GET_COPYRIGHT_HASH)

        private const val SET_COPYRIGHT_HASH = 83702148L
        @JvmField
        val setCopyrightBind =
            ObjectCalls.getMethodBind("GLTFState", "set_copyright", SET_COPYRIGHT_HASH)

        private const val GET_GLB_DATA_HASH = 2362200018L
        @JvmField
        val getGlbDataBind =
            ObjectCalls.getMethodBind("GLTFState", "get_glb_data", GET_GLB_DATA_HASH)

        private const val SET_GLB_DATA_HASH = 2971499966L
        @JvmField
        val setGlbDataBind =
            ObjectCalls.getMethodBind("GLTFState", "set_glb_data", SET_GLB_DATA_HASH)

        private const val GET_USE_NAMED_SKIN_BINDS_HASH = 36873697L
        @JvmField
        val getUseNamedSkinBindsBind =
            ObjectCalls.getMethodBind("GLTFState", "get_use_named_skin_binds", GET_USE_NAMED_SKIN_BINDS_HASH)

        private const val SET_USE_NAMED_SKIN_BINDS_HASH = 2586408642L
        @JvmField
        val setUseNamedSkinBindsBind =
            ObjectCalls.getMethodBind("GLTFState", "set_use_named_skin_binds", SET_USE_NAMED_SKIN_BINDS_HASH)

        private const val GET_NODES_HASH = 3995934104L
        @JvmField
        val getNodesBind =
            ObjectCalls.getMethodBind("GLTFState", "get_nodes", GET_NODES_HASH)

        private const val SET_NODES_HASH = 381264803L
        @JvmField
        val setNodesBind =
            ObjectCalls.getMethodBind("GLTFState", "set_nodes", SET_NODES_HASH)

        private const val GET_BUFFERS_HASH = 3995934104L
        @JvmField
        val getBuffersBind =
            ObjectCalls.getMethodBind("GLTFState", "get_buffers", GET_BUFFERS_HASH)

        private const val SET_BUFFERS_HASH = 381264803L
        @JvmField
        val setBuffersBind =
            ObjectCalls.getMethodBind("GLTFState", "set_buffers", SET_BUFFERS_HASH)

        private const val GET_BUFFER_VIEWS_HASH = 3995934104L
        @JvmField
        val getBufferViewsBind =
            ObjectCalls.getMethodBind("GLTFState", "get_buffer_views", GET_BUFFER_VIEWS_HASH)

        private const val SET_BUFFER_VIEWS_HASH = 381264803L
        @JvmField
        val setBufferViewsBind =
            ObjectCalls.getMethodBind("GLTFState", "set_buffer_views", SET_BUFFER_VIEWS_HASH)

        private const val GET_ACCESSORS_HASH = 3995934104L
        @JvmField
        val getAccessorsBind =
            ObjectCalls.getMethodBind("GLTFState", "get_accessors", GET_ACCESSORS_HASH)

        private const val SET_ACCESSORS_HASH = 381264803L
        @JvmField
        val setAccessorsBind =
            ObjectCalls.getMethodBind("GLTFState", "set_accessors", SET_ACCESSORS_HASH)

        private const val GET_MESHES_HASH = 3995934104L
        @JvmField
        val getMeshesBind =
            ObjectCalls.getMethodBind("GLTFState", "get_meshes", GET_MESHES_HASH)

        private const val SET_MESHES_HASH = 381264803L
        @JvmField
        val setMeshesBind =
            ObjectCalls.getMethodBind("GLTFState", "set_meshes", SET_MESHES_HASH)

        private const val GET_ANIMATION_PLAYERS_COUNT_HASH = 923996154L
        @JvmField
        val getAnimationPlayersCountBind =
            ObjectCalls.getMethodBind("GLTFState", "get_animation_players_count", GET_ANIMATION_PLAYERS_COUNT_HASH)

        private const val GET_ANIMATION_PLAYER_HASH = 1550200483L
        @JvmField
        val getAnimationPlayerBind =
            ObjectCalls.getMethodBind("GLTFState", "get_animation_player", GET_ANIMATION_PLAYER_HASH)

        private const val GET_MATERIALS_HASH = 3995934104L
        @JvmField
        val getMaterialsBind =
            ObjectCalls.getMethodBind("GLTFState", "get_materials", GET_MATERIALS_HASH)

        private const val SET_MATERIALS_HASH = 381264803L
        @JvmField
        val setMaterialsBind =
            ObjectCalls.getMethodBind("GLTFState", "set_materials", SET_MATERIALS_HASH)

        private const val GET_SCENE_NAME_HASH = 201670096L
        @JvmField
        val getSceneNameBind =
            ObjectCalls.getMethodBind("GLTFState", "get_scene_name", GET_SCENE_NAME_HASH)

        private const val SET_SCENE_NAME_HASH = 83702148L
        @JvmField
        val setSceneNameBind =
            ObjectCalls.getMethodBind("GLTFState", "set_scene_name", SET_SCENE_NAME_HASH)

        private const val GET_BASE_PATH_HASH = 201670096L
        @JvmField
        val getBasePathBind =
            ObjectCalls.getMethodBind("GLTFState", "get_base_path", GET_BASE_PATH_HASH)

        private const val SET_BASE_PATH_HASH = 83702148L
        @JvmField
        val setBasePathBind =
            ObjectCalls.getMethodBind("GLTFState", "set_base_path", SET_BASE_PATH_HASH)

        private const val GET_FILENAME_HASH = 201670096L
        @JvmField
        val getFilenameBind =
            ObjectCalls.getMethodBind("GLTFState", "get_filename", GET_FILENAME_HASH)

        private const val SET_FILENAME_HASH = 83702148L
        @JvmField
        val setFilenameBind =
            ObjectCalls.getMethodBind("GLTFState", "set_filename", SET_FILENAME_HASH)

        private const val GET_ROOT_NODES_HASH = 1930428628L
        @JvmField
        val getRootNodesBind =
            ObjectCalls.getMethodBind("GLTFState", "get_root_nodes", GET_ROOT_NODES_HASH)

        private const val SET_ROOT_NODES_HASH = 3614634198L
        @JvmField
        val setRootNodesBind =
            ObjectCalls.getMethodBind("GLTFState", "set_root_nodes", SET_ROOT_NODES_HASH)

        private const val GET_TEXTURES_HASH = 3995934104L
        @JvmField
        val getTexturesBind =
            ObjectCalls.getMethodBind("GLTFState", "get_textures", GET_TEXTURES_HASH)

        private const val SET_TEXTURES_HASH = 381264803L
        @JvmField
        val setTexturesBind =
            ObjectCalls.getMethodBind("GLTFState", "set_textures", SET_TEXTURES_HASH)

        private const val GET_TEXTURE_SAMPLERS_HASH = 3995934104L
        @JvmField
        val getTextureSamplersBind =
            ObjectCalls.getMethodBind("GLTFState", "get_texture_samplers", GET_TEXTURE_SAMPLERS_HASH)

        private const val SET_TEXTURE_SAMPLERS_HASH = 381264803L
        @JvmField
        val setTextureSamplersBind =
            ObjectCalls.getMethodBind("GLTFState", "set_texture_samplers", SET_TEXTURE_SAMPLERS_HASH)

        private const val GET_IMAGES_HASH = 3995934104L
        @JvmField
        val getImagesBind =
            ObjectCalls.getMethodBind("GLTFState", "get_images", GET_IMAGES_HASH)

        private const val SET_IMAGES_HASH = 381264803L
        @JvmField
        val setImagesBind =
            ObjectCalls.getMethodBind("GLTFState", "set_images", SET_IMAGES_HASH)

        private const val GET_SKINS_HASH = 3995934104L
        @JvmField
        val getSkinsBind =
            ObjectCalls.getMethodBind("GLTFState", "get_skins", GET_SKINS_HASH)

        private const val SET_SKINS_HASH = 381264803L
        @JvmField
        val setSkinsBind =
            ObjectCalls.getMethodBind("GLTFState", "set_skins", SET_SKINS_HASH)

        private const val GET_CAMERAS_HASH = 3995934104L
        @JvmField
        val getCamerasBind =
            ObjectCalls.getMethodBind("GLTFState", "get_cameras", GET_CAMERAS_HASH)

        private const val SET_CAMERAS_HASH = 381264803L
        @JvmField
        val setCamerasBind =
            ObjectCalls.getMethodBind("GLTFState", "set_cameras", SET_CAMERAS_HASH)

        private const val GET_LIGHTS_HASH = 3995934104L
        @JvmField
        val getLightsBind =
            ObjectCalls.getMethodBind("GLTFState", "get_lights", GET_LIGHTS_HASH)

        private const val SET_LIGHTS_HASH = 381264803L
        @JvmField
        val setLightsBind =
            ObjectCalls.getMethodBind("GLTFState", "set_lights", SET_LIGHTS_HASH)

        private const val GET_UNIQUE_NAMES_HASH = 3995934104L
        @JvmField
        val getUniqueNamesBind =
            ObjectCalls.getMethodBind("GLTFState", "get_unique_names", GET_UNIQUE_NAMES_HASH)

        private const val SET_UNIQUE_NAMES_HASH = 381264803L
        @JvmField
        val setUniqueNamesBind =
            ObjectCalls.getMethodBind("GLTFState", "set_unique_names", SET_UNIQUE_NAMES_HASH)

        private const val GET_UNIQUE_ANIMATION_NAMES_HASH = 3995934104L
        @JvmField
        val getUniqueAnimationNamesBind =
            ObjectCalls.getMethodBind("GLTFState", "get_unique_animation_names", GET_UNIQUE_ANIMATION_NAMES_HASH)

        private const val SET_UNIQUE_ANIMATION_NAMES_HASH = 381264803L
        @JvmField
        val setUniqueAnimationNamesBind =
            ObjectCalls.getMethodBind("GLTFState", "set_unique_animation_names", SET_UNIQUE_ANIMATION_NAMES_HASH)

        private const val GET_SKELETONS_HASH = 3995934104L
        @JvmField
        val getSkeletonsBind =
            ObjectCalls.getMethodBind("GLTFState", "get_skeletons", GET_SKELETONS_HASH)

        private const val SET_SKELETONS_HASH = 381264803L
        @JvmField
        val setSkeletonsBind =
            ObjectCalls.getMethodBind("GLTFState", "set_skeletons", SET_SKELETONS_HASH)

        private const val GET_CREATE_ANIMATIONS_HASH = 36873697L
        @JvmField
        val getCreateAnimationsBind =
            ObjectCalls.getMethodBind("GLTFState", "get_create_animations", GET_CREATE_ANIMATIONS_HASH)

        private const val SET_CREATE_ANIMATIONS_HASH = 2586408642L
        @JvmField
        val setCreateAnimationsBind =
            ObjectCalls.getMethodBind("GLTFState", "set_create_animations", SET_CREATE_ANIMATIONS_HASH)

        private const val GET_IMPORT_AS_SKELETON_BONES_HASH = 36873697L
        @JvmField
        val getImportAsSkeletonBonesBind =
            ObjectCalls.getMethodBind("GLTFState", "get_import_as_skeleton_bones", GET_IMPORT_AS_SKELETON_BONES_HASH)

        private const val SET_IMPORT_AS_SKELETON_BONES_HASH = 2586408642L
        @JvmField
        val setImportAsSkeletonBonesBind =
            ObjectCalls.getMethodBind("GLTFState", "set_import_as_skeleton_bones", SET_IMPORT_AS_SKELETON_BONES_HASH)

        private const val GET_ANIMATIONS_HASH = 3995934104L
        @JvmField
        val getAnimationsBind =
            ObjectCalls.getMethodBind("GLTFState", "get_animations", GET_ANIMATIONS_HASH)

        private const val SET_ANIMATIONS_HASH = 381264803L
        @JvmField
        val setAnimationsBind =
            ObjectCalls.getMethodBind("GLTFState", "set_animations", SET_ANIMATIONS_HASH)

        private const val GET_SCENE_NODE_HASH = 539202265L
        @JvmField
        val getSceneNodeBind =
            ObjectCalls.getMethodBind("GLTFState", "get_scene_node", GET_SCENE_NODE_HASH)

        private const val GET_NODE_INDEX_HASH = 3810805390L
        @JvmField
        val getNodeIndexBind =
            ObjectCalls.getMethodBind("GLTFState", "get_node_index", GET_NODE_INDEX_HASH)

        private const val GET_ADDITIONAL_DATA_HASH = 2760726917L
        @JvmField
        val getAdditionalDataBind =
            ObjectCalls.getMethodBind("GLTFState", "get_additional_data", GET_ADDITIONAL_DATA_HASH)

        private const val SET_ADDITIONAL_DATA_HASH = 3776071444L
        @JvmField
        val setAdditionalDataBind =
            ObjectCalls.getMethodBind("GLTFState", "set_additional_data", SET_ADDITIONAL_DATA_HASH)

        private const val GET_HANDLE_BINARY_IMAGE_MODE_HASH = 1363384196L
        @JvmField
        val getHandleBinaryImageModeBind =
            ObjectCalls.getMethodBind("GLTFState", "get_handle_binary_image_mode", GET_HANDLE_BINARY_IMAGE_MODE_HASH)

        private const val SET_HANDLE_BINARY_IMAGE_MODE_HASH = 854676334L
        @JvmField
        val setHandleBinaryImageModeBind =
            ObjectCalls.getMethodBind("GLTFState", "set_handle_binary_image_mode", SET_HANDLE_BINARY_IMAGE_MODE_HASH)

        private const val SET_BAKE_FPS_HASH = 373806689L
        @JvmField
        val setBakeFpsBind =
            ObjectCalls.getMethodBind("GLTFState", "set_bake_fps", SET_BAKE_FPS_HASH)

        private const val GET_BAKE_FPS_HASH = 1740695150L
        @JvmField
        val getBakeFpsBind =
            ObjectCalls.getMethodBind("GLTFState", "get_bake_fps", GET_BAKE_FPS_HASH)

        private const val GET_HANDLE_BINARY_IMAGE_HASH = 3905245786L
        @JvmField
        val getHandleBinaryImageBind =
            ObjectCalls.getMethodBind("GLTFState", "get_handle_binary_image", GET_HANDLE_BINARY_IMAGE_HASH)

        private const val SET_HANDLE_BINARY_IMAGE_HASH = 1286410249L
        @JvmField
        val setHandleBinaryImageBind =
            ObjectCalls.getMethodBind("GLTFState", "set_handle_binary_image", SET_HANDLE_BINARY_IMAGE_HASH)
    }
}
