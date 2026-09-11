package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GLTFState (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GLTFState waits on: ptrcallWithByteArrayListArg, ptrcallWithDictionaryArg,
//   ptrcallWithObjectListArg, ptrcallWithStringNameAndVariantArg, ptrcallWithTypedMaterialListArg,
//   ptrcallWithTypedStringListArg
// Index: docs/reference/generated/ios-shape-gap.md

fun GLTFState.setJson(json: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithDictionaryArg(setJsonBind, handle, json)
}

fun GLTFState.setNodes(nodes: List<GLTFNode>) {
    checkOpen()
    ObjectCalls.ptrcallWithObjectListArg(setNodesBind, handle, nodes)
}

fun GLTFState.setBuffers(buffers: List<ByteArray>) {
    checkOpen()
    ObjectCalls.ptrcallWithByteArrayListArg(setBuffersBind, handle, buffers)
}

fun GLTFState.setBufferViews(bufferViews: List<GLTFBufferView>) {
    checkOpen()
    ObjectCalls.ptrcallWithObjectListArg(setBufferViewsBind, handle, bufferViews)
}

fun GLTFState.setAccessors(accessors: List<GLTFAccessor>) {
    checkOpen()
    ObjectCalls.ptrcallWithObjectListArg(setAccessorsBind, handle, accessors)
}

fun GLTFState.setMeshes(meshes: List<GLTFMesh>) {
    checkOpen()
    ObjectCalls.ptrcallWithObjectListArg(setMeshesBind, handle, meshes)
}

fun GLTFState.setMaterials(materials: List<Material>) {
    checkOpen()
    ObjectCalls.ptrcallWithTypedMaterialListArg(setMaterialsBind, handle, materials)
}

fun GLTFState.setTextures(textures: List<GLTFTexture>) {
    checkOpen()
    ObjectCalls.ptrcallWithObjectListArg(setTexturesBind, handle, textures)
}

fun GLTFState.setTextureSamplers(textureSamplers: List<GLTFTextureSampler>) {
    checkOpen()
    ObjectCalls.ptrcallWithObjectListArg(setTextureSamplersBind, handle, textureSamplers)
}

fun GLTFState.setImages(images: List<Texture2D>) {
    checkOpen()
    ObjectCalls.ptrcallWithObjectListArg(setImagesBind, handle, images)
}

fun GLTFState.setSkins(skins: List<GLTFSkin>) {
    checkOpen()
    ObjectCalls.ptrcallWithObjectListArg(setSkinsBind, handle, skins)
}

fun GLTFState.setCameras(cameras: List<GLTFCamera>) {
    checkOpen()
    ObjectCalls.ptrcallWithObjectListArg(setCamerasBind, handle, cameras)
}

fun GLTFState.setLights(lights: List<GLTFLight>) {
    checkOpen()
    ObjectCalls.ptrcallWithObjectListArg(setLightsBind, handle, lights)
}

fun GLTFState.setUniqueNames(uniqueNames: List<String>) {
    checkOpen()
    ObjectCalls.ptrcallWithTypedStringListArg(setUniqueNamesBind, handle, uniqueNames)
}

fun GLTFState.setUniqueAnimationNames(uniqueAnimationNames: List<String>) {
    checkOpen()
    ObjectCalls.ptrcallWithTypedStringListArg(setUniqueAnimationNamesBind, handle, uniqueAnimationNames)
}

fun GLTFState.setSkeletons(skeletons: List<GLTFSkeleton>) {
    checkOpen()
    ObjectCalls.ptrcallWithObjectListArg(setSkeletonsBind, handle, skeletons)
}

fun GLTFState.setAnimations(animations: List<GLTFAnimation>) {
    checkOpen()
    ObjectCalls.ptrcallWithObjectListArg(setAnimationsBind, handle, animations)
}

fun GLTFState.setAdditionalData(extensionName: String, additionalData: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithStringNameAndVariantArg(setAdditionalDataBind, handle, extensionName, additionalData)
}

private const val SET_JSON_HASH = 4155329257L
private val setJsonBind by lazy {
    ObjectCalls.getMethodBind("GLTFState", "set_json", SET_JSON_HASH)
}

private const val SET_NODES_HASH = 381264803L
private val setNodesBind by lazy {
    ObjectCalls.getMethodBind("GLTFState", "set_nodes", SET_NODES_HASH)
}

private const val SET_BUFFERS_HASH = 381264803L
private val setBuffersBind by lazy {
    ObjectCalls.getMethodBind("GLTFState", "set_buffers", SET_BUFFERS_HASH)
}

private const val SET_BUFFER_VIEWS_HASH = 381264803L
private val setBufferViewsBind by lazy {
    ObjectCalls.getMethodBind("GLTFState", "set_buffer_views", SET_BUFFER_VIEWS_HASH)
}

private const val SET_ACCESSORS_HASH = 381264803L
private val setAccessorsBind by lazy {
    ObjectCalls.getMethodBind("GLTFState", "set_accessors", SET_ACCESSORS_HASH)
}

private const val SET_MESHES_HASH = 381264803L
private val setMeshesBind by lazy {
    ObjectCalls.getMethodBind("GLTFState", "set_meshes", SET_MESHES_HASH)
}

private const val SET_MATERIALS_HASH = 381264803L
private val setMaterialsBind by lazy {
    ObjectCalls.getMethodBind("GLTFState", "set_materials", SET_MATERIALS_HASH)
}

private const val SET_TEXTURES_HASH = 381264803L
private val setTexturesBind by lazy {
    ObjectCalls.getMethodBind("GLTFState", "set_textures", SET_TEXTURES_HASH)
}

private const val SET_TEXTURE_SAMPLERS_HASH = 381264803L
private val setTextureSamplersBind by lazy {
    ObjectCalls.getMethodBind("GLTFState", "set_texture_samplers", SET_TEXTURE_SAMPLERS_HASH)
}

private const val SET_IMAGES_HASH = 381264803L
private val setImagesBind by lazy {
    ObjectCalls.getMethodBind("GLTFState", "set_images", SET_IMAGES_HASH)
}

private const val SET_SKINS_HASH = 381264803L
private val setSkinsBind by lazy {
    ObjectCalls.getMethodBind("GLTFState", "set_skins", SET_SKINS_HASH)
}

private const val SET_CAMERAS_HASH = 381264803L
private val setCamerasBind by lazy {
    ObjectCalls.getMethodBind("GLTFState", "set_cameras", SET_CAMERAS_HASH)
}

private const val SET_LIGHTS_HASH = 381264803L
private val setLightsBind by lazy {
    ObjectCalls.getMethodBind("GLTFState", "set_lights", SET_LIGHTS_HASH)
}

private const val SET_UNIQUE_NAMES_HASH = 381264803L
private val setUniqueNamesBind by lazy {
    ObjectCalls.getMethodBind("GLTFState", "set_unique_names", SET_UNIQUE_NAMES_HASH)
}

private const val SET_UNIQUE_ANIMATION_NAMES_HASH = 381264803L
private val setUniqueAnimationNamesBind by lazy {
    ObjectCalls.getMethodBind("GLTFState", "set_unique_animation_names", SET_UNIQUE_ANIMATION_NAMES_HASH)
}

private const val SET_SKELETONS_HASH = 381264803L
private val setSkeletonsBind by lazy {
    ObjectCalls.getMethodBind("GLTFState", "set_skeletons", SET_SKELETONS_HASH)
}

private const val SET_ANIMATIONS_HASH = 381264803L
private val setAnimationsBind by lazy {
    ObjectCalls.getMethodBind("GLTFState", "set_animations", SET_ANIMATIONS_HASH)
}

private const val SET_ADDITIONAL_DATA_HASH = 3776071444L
private val setAdditionalDataBind by lazy {
    ObjectCalls.getMethodBind("GLTFState", "set_additional_data", SET_ADDITIONAL_DATA_HASH)
}
