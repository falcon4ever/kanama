package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2i

// GENERATED desktop/Android companion for RenderingServer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP RenderingServer waits on: ptrcallWithDictionaryListIntArgsRetRID,
//   ptrcallWithLongThreeIntBoolObjectListArgsRetRID, ptrcallWithObjectListLongArgsRetRID,
//   ptrcallWithPlaneListAndRIDArgsRetPackedInt64List, ptrcallWithRIDAndIntArgRetArrayList,
//   ptrcallWithRIDAndObjectListArgs, ptrcallWithRIDAndRIDListArgs,
//   ptrcallWithRIDAndTransform3DListArgs, ptrcallWithRIDArgRetTypedObjectList,
//   ptrcallWithRIDListRect2iRIDColorRIDListIntArgs,
//   ptrcallWithRIDRIDListVector2iArgsRetTypedObjectList
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Creates a 2-dimensional layered texture and adds it to the RenderingServer. It can be accessed
 * with the RID that is returned. This RID will be used in all `texture_2d_layered_*`
 * RenderingServer functions. Once finished with your RID, you will want to free the RID using the
 * RenderingServer's `free_rid` method. Note: The equivalent resource is `TextureLayered`.
 *
 * Generated from Godot docs: RenderingServer.texture_2d_layered_create
 */
fun RenderingServer.texture2dLayeredCreate(layers: List<Image>, layeredType: Long): RID {
    return ObjectCalls.ptrcallWithObjectListLongArgsRetRID(texture2dLayeredCreateBind, renderingServerSingleton, layers, layeredType)
}

/**
 * Note: The equivalent resource is `Texture3D`.
 *
 * Generated from Godot docs: RenderingServer.texture_3d_create
 */
fun RenderingServer.texture3dCreate(format: Long, width: Int, height: Int, depth: Int, mipmaps: Boolean, data: List<Image>): RID {
    return ObjectCalls.ptrcallWithLongThreeIntBoolObjectListArgsRetRID(texture3dCreateBind, renderingServerSingleton, format, width, height, depth, mipmaps, data)
}

/**
 * Updates the texture specified by the `texture` `RID`'s data with the data in `data`. All the
 * texture's layers must be replaced at once. Note: The `texture` must have the same width, height,
 * depth and format as the current texture data. Otherwise, an error will be printed and the
 * original texture won't be modified. If you need to use different width, height, depth or format,
 * use `texture_replace` instead.
 *
 * Generated from Godot docs: RenderingServer.texture_3d_update
 */
fun RenderingServer.texture3dUpdate(texture: RID, data: List<Image>) {
    ObjectCalls.ptrcallWithRIDAndObjectListArgs(texture3dUpdateBind, renderingServerSingleton, texture, data)
}

/**
 * Draws to `rect` on up to 4 given Drawable `textures`, using a TextureBlit Shader from
 * `material`. `modulate` and up to 4 `source_textures` are uniforms for the Shader to process
 * with. `to_mipmap` can specify to perform this draw to a lower mipmap level. Note: All `textures`
 * must be the same size and format.
 *
 * Generated from Godot docs: RenderingServer.texture_drawable_blit_rect
 */
fun RenderingServer.textureDrawableBlitRect(textures: List<RID>, rect: Rect2i, material: RID, modulate: Color, sourceTextures: List<RID>, toMipmap: Int = 0) {
    ObjectCalls.ptrcallWithRIDListRect2iRIDColorRIDListIntArgs(textureDrawableBlitRectBind, renderingServerSingleton, textures, rect, material, modulate, sourceTextures, toMipmap)
}

/**
 * Returns 3D texture data as an array of `Image`s for the specified texture `RID`.
 *
 * Generated from Godot docs: RenderingServer.texture_3d_get
 */
fun RenderingServer.texture3dGet(texture: RID): List<Image> {
    return ObjectCalls.ptrcallWithRIDArgRetTypedObjectList(texture3dGetBind, renderingServerSingleton, texture, Image::fromHandle)
}

/**
 * Creates a new mesh with predefined surfaces for it and adds the mesh to the RenderingServer. It
 * can be accessed with the RID that is returned. This RID will be used in all `mesh_*`
 * RenderingServer functions. This method is more efficient for creating meshes with multiple
 * surfaces compared to creating an empty mesh with `mesh_create` and adding surfaces one by one
 * with `mesh_add_surface`. Each element in the `surfaces` array must follow the same structure as
 * described in `mesh_add_surface`. The `blend_shape_count` parameter must match the blend shape
 * data defined in all surfaces. Once finished with your RID, you will want to free the RID using
 * the RenderingServer's `free_rid` method. To place in a scene, attach this mesh to an instance
 * using `instance_set_base` using the returned RID. Note: The equivalent resource is `Mesh`.
 *
 * Generated from Godot docs: RenderingServer.mesh_create_from_surfaces
 */
fun RenderingServer.meshCreateFromSurfaces(surfaces: List<Map<String, Any?>>, blendShapeCount: Int = 0): RID {
    return ObjectCalls.ptrcallWithDictionaryListIntArgsRetRID(meshCreateFromSurfacesBind, renderingServerSingleton, surfaces, blendShapeCount)
}

/**
 * Returns a mesh's surface's arrays for blend shapes.
 *
 * Generated from Godot docs: RenderingServer.mesh_surface_get_blend_shape_arrays
 */
fun RenderingServer.meshSurfaceGetBlendShapeArrays(mesh: RID, surface: Int): List<List<Any?>> {
    return ObjectCalls.ptrcallWithRIDAndIntArgRetArrayList(meshSurfaceGetBlendShapeArraysBind, renderingServerSingleton, mesh, surface)
}

/**
 * Sets the trail bind poses for the particle system. This specified as an array of `Transform3D`s
 * representing the bind pose for each draw pass. See `GPUParticles3D.draw_skin`,
 * `Skin.get_bind_count`, and `Skin.get_bind_pose`. Set the value for each draw pass to
 * `Transform3D.IDENTITY` to use the default behavior, which is what built-in trails use
 * (`RibbonTrailMesh` and `TubeTrailMesh`).
 *
 * Generated from Godot docs: RenderingServer.particles_set_trail_bind_poses
 */
fun RenderingServer.particlesSetTrailBindPoses(particles: RID, bindPoses: List<Transform3D>) {
    ObjectCalls.ptrcallWithRIDAndTransform3DListArgs(particlesSetTrailBindPosesBind, renderingServerSingleton, particles, bindPoses)
}

/**
 * Sets the compositor effects for the specified compositor RID. `effects` should be an array
 * containing RIDs created with `compositor_effect_create`.
 *
 * Generated from Godot docs: RenderingServer.compositor_set_compositor_effects
 */
fun RenderingServer.compositorSetCompositorEffects(compositor: RID, effects: List<RID>) {
    ObjectCalls.ptrcallWithRIDAndRIDListArgs(compositorSetCompositorEffectsBind, renderingServerSingleton, compositor, effects)
}

/**
 * Returns an array of object IDs intersecting with the provided convex shape. Only 3D nodes that
 * inherit from `VisualInstance3D` are considered, such as `MeshInstance3D` or
 * `DirectionalLight3D`. Use `@GlobalScope.instance_from_id` to obtain the actual nodes. A scenario
 * RID must be provided, which is available in the `World3D` you want to query. This forces an
 * update for all resources queued to update. Warning: This function is primarily intended for
 * editor usage. For in-game use cases, prefer physics collision.
 *
 * Generated from Godot docs: RenderingServer.instances_cull_convex
 */
fun RenderingServer.instancesCullConvex(convex: List<Plane>, scenario: RID): List<Long> {
    return ObjectCalls.ptrcallWithPlaneListAndRIDArgsRetPackedInt64List(instancesCullConvexBind, renderingServerSingleton, convex, scenario)
}

/**
 * Bakes the material data of the Mesh passed in the `base` parameter with optional
 * `material_overrides` to a set of `Image`s of size `image_size`. Returns an array of `Image`s
 * containing material properties as specified in `BakeChannels`.
 *
 * Generated from Godot docs: RenderingServer.bake_render_uv2
 */
fun RenderingServer.bakeRenderUv2(base: RID, materialOverrides: List<RID>, imageSize: Vector2i): List<Image> {
    return ObjectCalls.ptrcallWithRIDRIDListVector2iArgsRetTypedObjectList(bakeRenderUv2Bind, renderingServerSingleton, base, materialOverrides, imageSize, Image::fromHandle)
}

private val renderingServerSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("RenderingServer")
}

private const val TEXTURE_2D_LAYERED_CREATE_HASH = 913689023L
private val texture2dLayeredCreateBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "texture_2d_layered_create", TEXTURE_2D_LAYERED_CREATE_HASH)
}

private const val TEXTURE_3D_CREATE_HASH = 4036838706L
private val texture3dCreateBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "texture_3d_create", TEXTURE_3D_CREATE_HASH)
}

private const val TEXTURE_3D_UPDATE_HASH = 684822712L
private val texture3dUpdateBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "texture_3d_update", TEXTURE_3D_UPDATE_HASH)
}

private const val TEXTURE_DRAWABLE_BLIT_RECT_HASH = 4077763890L
private val textureDrawableBlitRectBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "texture_drawable_blit_rect", TEXTURE_DRAWABLE_BLIT_RECT_HASH)
}

private const val TEXTURE_3D_GET_HASH = 2684255073L
private val texture3dGetBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "texture_3d_get", TEXTURE_3D_GET_HASH)
}

private const val MESH_CREATE_FROM_SURFACES_HASH = 4291747531L
private val meshCreateFromSurfacesBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "mesh_create_from_surfaces", MESH_CREATE_FROM_SURFACES_HASH)
}

private const val MESH_SURFACE_GET_BLEND_SHAPE_ARRAYS_HASH = 1778388067L
private val meshSurfaceGetBlendShapeArraysBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_blend_shape_arrays", MESH_SURFACE_GET_BLEND_SHAPE_ARRAYS_HASH)
}

private const val PARTICLES_SET_TRAIL_BIND_POSES_HASH = 684822712L
private val particlesSetTrailBindPosesBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "particles_set_trail_bind_poses", PARTICLES_SET_TRAIL_BIND_POSES_HASH)
}

private const val COMPOSITOR_SET_COMPOSITOR_EFFECTS_HASH = 684822712L
private val compositorSetCompositorEffectsBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "compositor_set_compositor_effects", COMPOSITOR_SET_COMPOSITOR_EFFECTS_HASH)
}

private const val INSTANCES_CULL_CONVEX_HASH = 2488539944L
private val instancesCullConvexBind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "instances_cull_convex", INSTANCES_CULL_CONVEX_HASH)
}

private const val BAKE_RENDER_UV2_HASH = 1904608558L
private val bakeRenderUv2Bind by lazy {
    ObjectCalls.getMethodBind("RenderingServer", "bake_render_uv2", BAKE_RENDER_UV2_HASH)
}
