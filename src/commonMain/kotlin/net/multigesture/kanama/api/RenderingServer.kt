package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.Vector3i

/**
 * Server for anything visible.
 *
 * Generated from Godot docs: RenderingServer
 */
object RenderingServer {
    private val singleton: RawSegment by lazy {
        ObjectCalls.getSingleton("RenderingServer")
    }

    const val NO_INDEX_ARRAY: Long = -1L
    const val ARRAY_WEIGHTS_SIZE: Long = 4L
    const val CANVAS_ITEM_Z_MIN: Long = -4096L
    const val CANVAS_ITEM_Z_MAX: Long = 4096L
    const val CANVAS_LAYER_MIN: Long = -2147483648L
    const val CANVAS_LAYER_MAX: Long = 2147483647L
    const val MAX_GLOW_LEVELS: Long = 7L
    const val MAX_CURSORS: Long = 8L
    const val MAX_2D_DIRECTIONAL_LIGHTS: Long = 8L
    const val MAX_MESH_SURFACES: Long = 256L
    const val MATERIAL_RENDER_PRIORITY_MIN: Long = -128L
    const val MATERIAL_RENDER_PRIORITY_MAX: Long = 127L
    const val ARRAY_CUSTOM_COUNT: Long = 4L
    const val PARTICLES_EMIT_FLAG_POSITION: Long = 1L
    const val PARTICLES_EMIT_FLAG_ROTATION_SCALE: Long = 2L
    const val PARTICLES_EMIT_FLAG_VELOCITY: Long = 4L
    const val PARTICLES_EMIT_FLAG_COLOR: Long = 8L
    const val PARTICLES_EMIT_FLAG_CUSTOM: Long = 16L

    var renderLoopEnabled: Boolean
        @JvmName("renderLoopEnabledProperty")
        get() = isRenderLoopEnabled()
        @JvmName("setRenderLoopEnabledProperty")
        set(value) = setRenderLoopEnabled(value)

    /**
     * Creates a 2-dimensional texture and adds it to the RenderingServer. It can be accessed with the
     * RID that is returned. This RID will be used in all `texture_2d_*` RenderingServer functions.
     * Once finished with your RID, you will want to free the RID using the RenderingServer's
     * `free_rid` method. Note: The equivalent resource is `Texture2D`. Note: Not to be confused with
     * `RenderingDevice.texture_create`, which creates the graphics API's own texture type as opposed
     * to the Godot-specific `Texture2D` resource.
     *
     * Generated from Godot docs: RenderingServer.texture_2d_create
     */
    @JvmStatic
    fun texture2dCreate(image: Image?): RID {
        return ObjectCalls.ptrcallWithObjectArgRetRID(texture2dCreateBind, singleton, image?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Creates a 2-dimensional layered texture and adds it to the RenderingServer. It can be accessed
     * with the RID that is returned. This RID will be used in all `texture_2d_layered_*`
     * RenderingServer functions. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method. Note: The equivalent resource is `TextureLayered`.
     *
     * Generated from Godot docs: RenderingServer.texture_2d_layered_create
     */
    @JvmStatic
    fun texture2dLayeredCreate(layers: List<Image>, layeredType: RenderingServer.TextureLayeredType): RID {
        return ObjectCalls.ptrcallWithObjectListLongArgsRetRID(texture2dLayeredCreateBind, singleton, layers, layeredType.value)
    }

    /**
     * Note: The equivalent resource is `Texture3D`.
     *
     * Generated from Godot docs: RenderingServer.texture_3d_create
     */
    @JvmStatic
    fun texture3dCreate(format: Image.Format, width: Int, height: Int, depth: Int, mipmaps: Boolean, data: List<Image>): RID {
        return ObjectCalls.ptrcallWithLongThreeIntBoolObjectListArgsRetRID(texture3dCreateBind, singleton, format.value, width, height, depth, mipmaps, data)
    }

    /**
     * This method does nothing and always returns an invalid `RID`.
     *
     * Generated from Godot docs: RenderingServer.texture_proxy_create
     */
    @JvmStatic
    fun textureProxyCreate(base: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(textureProxyCreateBind, singleton, base)
    }

    /**
     * Creates a texture based on a native handle that was created outside of Godot's renderer. Note:
     * If using only the rendering device renderer, it's recommend to use
     * `RenderingDevice.texture_create_from_extension` together with
     * `RenderingServer.texture_rd_create`, rather than this method. This way, the texture's format and
     * usage can be controlled more effectively.
     *
     * Generated from Godot docs: RenderingServer.texture_create_from_native_handle
     */
    @JvmStatic
    fun textureCreateFromNativeHandle(type: RenderingServer.TextureType, format: Image.Format, nativeHandle: Long, width: Int, height: Int, depth: Int, layers: Int = 1, layeredType: RenderingServer.TextureLayeredType = RenderingServer.TextureLayeredType.LAYERED_2D_ARRAY): RID {
        return ObjectCalls.ptrcallWithThreeLongFourIntLongArgsRetRID(textureCreateFromNativeHandleBind, singleton, type.value, format.value, nativeHandle, width, height, depth, layers, layeredType.value)
    }

    /**
     * Creates a 2-dimensional texture and adds it to the RenderingServer. It can be accessed with the
     * RID that is returned. This RID will be used in all `texture_drawable*` RenderingServer
     * functions. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method. Note: The equivalent resource is `DrawableTexture2D`.
     *
     * Generated from Godot docs: RenderingServer.texture_drawable_create
     */
    @JvmStatic
    fun textureDrawableCreate(width: Int, height: Int, format: RenderingServer.TextureDrawableFormat, color: Color, withMipmaps: Boolean = false): RID {
        return ObjectCalls.ptrcallWithTwoIntLongColorBoolArgsRetRID(textureDrawableCreateBind, singleton, width, height, format.value, color, withMipmaps)
    }

    /**
     * Updates the texture specified by the `texture` `RID` with the data in `image`. A `layer` must
     * also be specified, which should be `0` when updating a single-layer texture (`Texture2D`). Note:
     * The `image` must have the same width, height and format as the current `texture` data.
     * Otherwise, an error will be printed and the original texture won't be modified. If you need to
     * use different width, height or format, use `texture_replace` instead.
     *
     * Generated from Godot docs: RenderingServer.texture_2d_update
     */
    @JvmStatic
    fun texture2dUpdate(texture: RID, image: Image?, layer: Int) {
        ObjectCalls.ptrcallWithRIDObjectIntArgs(texture2dUpdateBind, singleton, texture, image?.requireOpenHandle() ?: NULL_SEGMENT, layer)
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
    @JvmStatic
    fun texture3dUpdate(texture: RID, data: List<Image>) {
        ObjectCalls.ptrcallWithRIDAndObjectListArgs(texture3dUpdateBind, singleton, texture, data)
    }

    /**
     * This method does nothing.
     *
     * Generated from Godot docs: RenderingServer.texture_proxy_update
     */
    @JvmStatic
    fun textureProxyUpdate(texture: RID, proxyTo: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(textureProxyUpdateBind, singleton, texture, proxyTo)
    }

    /**
     * Draws to `rect` on up to 4 given Drawable `textures`, using a TextureBlit Shader from
     * `material`. `modulate` and up to 4 `source_textures` are uniforms for the Shader to process
     * with. `to_mipmap` can specify to perform this draw to a lower mipmap level. Note: All `textures`
     * must be the same size and format.
     *
     * Generated from Godot docs: RenderingServer.texture_drawable_blit_rect
     */
    @JvmStatic
    fun textureDrawableBlitRect(textures: List<RID>, rect: Rect2i, material: RID, modulate: Color, sourceTextures: List<RID>, toMipmap: Int = 0) {
        ObjectCalls.ptrcallWithRIDListRect2iRIDColorRIDListIntArgs(textureDrawableBlitRectBind, singleton, textures, rect, material, modulate, sourceTextures, toMipmap)
    }

    /**
     * Creates a placeholder for a 2-dimensional layered texture and adds it to the RenderingServer. It
     * can be accessed with the RID that is returned. This RID will be used in all
     * `texture_2d_layered_*` RenderingServer functions, although it does nothing when used. See also
     * `texture_2d_layered_placeholder_create`. Once finished with your RID, you will want to free the
     * RID using the RenderingServer's `free_rid` method. Note: The equivalent resource is
     * `PlaceholderTexture2D`.
     *
     * Generated from Godot docs: RenderingServer.texture_2d_placeholder_create
     */
    @JvmStatic
    fun texture2dPlaceholderCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(texture2dPlaceholderCreateBind, singleton)
    }

    /**
     * Creates a placeholder for a 2-dimensional layered texture and adds it to the RenderingServer. It
     * can be accessed with the RID that is returned. This RID will be used in all
     * `texture_2d_layered_*` RenderingServer functions, although it does nothing when used. See also
     * `texture_2d_placeholder_create`. Note: The equivalent resource is `PlaceholderTextureLayered`.
     *
     * Generated from Godot docs: RenderingServer.texture_2d_layered_placeholder_create
     */
    @JvmStatic
    fun texture2dLayeredPlaceholderCreate(layeredType: RenderingServer.TextureLayeredType): RID {
        return ObjectCalls.ptrcallWithLongArgRetRID(texture2dLayeredPlaceholderCreateBind, singleton, layeredType.value)
    }

    /**
     * Creates a placeholder for a 3-dimensional texture and adds it to the RenderingServer. It can be
     * accessed with the RID that is returned. This RID will be used in all `texture_3d_*`
     * RenderingServer functions, although it does nothing when used. Once finished with your RID, you
     * will want to free the RID using the RenderingServer's `free_rid` method. Note: The equivalent
     * resource is `PlaceholderTexture3D`.
     *
     * Generated from Godot docs: RenderingServer.texture_3d_placeholder_create
     */
    @JvmStatic
    fun texture3dPlaceholderCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(texture3dPlaceholderCreateBind, singleton)
    }

    /**
     * Returns an `Image` instance from the given `texture` `RID`.
     *
     * Generated from Godot docs: RenderingServer.texture_2d_get
     */
    @JvmStatic
    fun texture2dGet(texture: RID): Image? {
        return Image.wrap(ObjectCalls.ptrcallWithRIDArgRetObject(texture2dGetBind, singleton, texture))
    }

    /**
     * Returns an `Image` instance from the given `texture` `RID` and `layer`.
     *
     * Generated from Godot docs: RenderingServer.texture_2d_layer_get
     */
    @JvmStatic
    fun texture2dLayerGet(texture: RID, layer: Int): Image? {
        return Image.wrap(ObjectCalls.ptrcallWithRIDAndIntArgRetObject(texture2dLayerGetBind, singleton, texture, layer))
    }

    /**
     * Returns 3D texture data as an array of `Image`s for the specified texture `RID`.
     *
     * Generated from Godot docs: RenderingServer.texture_3d_get
     */
    @JvmStatic
    fun texture3dGet(texture: RID): List<Image> {
        return ObjectCalls.ptrcallWithRIDArgRetTypedObjectList(texture3dGetBind, singleton, texture, Image::wrap)
    }

    /**
     * Calculates new MipMaps for the given Drawable `texture`.
     *
     * Generated from Godot docs: RenderingServer.texture_drawable_generate_mipmaps
     */
    @JvmStatic
    fun textureDrawableGenerateMipmaps(texture: RID) {
        ObjectCalls.ptrcallWithRIDArg(textureDrawableGenerateMipmapsBind, singleton, texture)
    }

    /**
     * Returns a ShaderMaterial with the default texture_blit Shader.
     *
     * Generated from Godot docs: RenderingServer.texture_drawable_get_default_material
     */
    @JvmStatic
    fun textureDrawableGetDefaultMaterial(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(textureDrawableGetDefaultMaterialBind, singleton)
    }

    /**
     * Replaces `texture`'s texture data by the texture specified by the `by_texture` RID, without
     * changing `texture`'s RID.
     *
     * Generated from Godot docs: RenderingServer.texture_replace
     */
    @JvmStatic
    fun textureReplace(texture: RID, byTexture: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(textureReplaceBind, singleton, texture, byTexture)
    }

    /**
     * Sets the size at which the texture should be displayed in 2D, ignoring its original size. This
     * does not rescale the texture data itself, only how it is drawn in 2D. Set `width` and `height`
     * to 0 to disable the size override.
     *
     * Generated from Godot docs: RenderingServer.texture_set_size_override
     */
    @JvmStatic
    fun textureSetSizeOverride(texture: RID, width: Int, height: Int) {
        ObjectCalls.ptrcallWithRIDAndTwoIntArgs(textureSetSizeOverrideBind, singleton, texture, width, height)
    }

    /**
     * Sets the resource path for this texture RID. See also `texture_get_path`. Note: This is purely a
     * hint and does not cause the texture to be automatically saved when set to a `res://` path.
     *
     * Generated from Godot docs: RenderingServer.texture_set_path
     */
    @JvmStatic
    fun textureSetPath(texture: RID, path: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(textureSetPathBind, singleton, texture, path)
    }

    /**
     * Returns the resource path (starting with `res://` or `uid://`) for the specified texture RID.
     * Returns an empty `String` if the resource is built-in. See also `texture_set_path`.
     *
     * Generated from Godot docs: RenderingServer.texture_get_path
     */
    @JvmStatic
    fun textureGetPath(texture: RID): String {
        return ObjectCalls.ptrcallWithRIDArgRetString(textureGetPathBind, singleton, texture)
    }

    /**
     * Returns the format for the texture.
     *
     * Generated from Godot docs: RenderingServer.texture_get_format
     */
    @JvmStatic
    fun textureGetFormat(texture: RID): Image.Format {
        return Image.Format(ObjectCalls.ptrcallWithRIDArgRetLong(textureGetFormatBind, singleton, texture))
    }

    /**
     * Sets whether the texture RID should force redrawing when it's visible on screen when
     * `OS.low_processor_usage_mode` is `true`. This is used by `AnimatedTexture` to force redrawing.
     *
     * Generated from Godot docs: RenderingServer.texture_set_force_redraw_if_visible
     */
    @JvmStatic
    fun textureSetForceRedrawIfVisible(texture: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(textureSetForceRedrawIfVisibleBind, singleton, texture, enable)
    }

    /**
     * Creates a new texture object based on a texture created directly on the `RenderingDevice`. If
     * the texture contains layers, `layer_type` is used to define the layer type. Once finished with
     * your RID, you will want to free the RID using the RenderingServer's `free_rid` method. Note: The
     * RenderingServer's `free_rid` won't free the underlying `rd_texture`, you will want to free the
     * `rd_texture` using `RenderingDevice.free_rid`.
     *
     * Generated from Godot docs: RenderingServer.texture_rd_create
     */
    @JvmStatic
    fun textureRdCreate(rdTexture: RID, layerType: RenderingServer.TextureLayeredType = RenderingServer.TextureLayeredType.LAYERED_2D_ARRAY): RID {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetRID(textureRdCreateBind, singleton, rdTexture, layerType.value)
    }

    /**
     * Returns a texture `RID` that can be used with `RenderingDevice`. `srgb` should be `true` when
     * the texture uses nonlinear sRGB encoding and `false` when the texture uses linear encoding.
     *
     * Generated from Godot docs: RenderingServer.texture_get_rd_texture
     */
    @JvmStatic
    fun textureGetRdTexture(texture: RID, srgb: Boolean = false): RID {
        return ObjectCalls.ptrcallWithRIDAndBoolArgRetRID(textureGetRdTextureBind, singleton, texture, srgb)
    }

    /**
     * Returns the internal graphics handle for this texture object. For use when communicating with
     * third-party APIs mostly with GDExtension. `srgb` should be `true` when the texture uses
     * nonlinear sRGB encoding and `false` when the texture uses linear encoding. Note: This function
     * returns a `uint64_t` which internally maps to a `GLuint` (OpenGL) or `VkImage` (Vulkan).
     *
     * Generated from Godot docs: RenderingServer.texture_get_native_handle
     */
    @JvmStatic
    fun textureGetNativeHandle(texture: RID, srgb: Boolean = false): Long {
        return ObjectCalls.ptrcallWithRIDAndBoolArgRetLong(textureGetNativeHandleBind, singleton, texture, srgb)
    }

    /**
     * Creates an empty shader and adds it to the RenderingServer. It can be accessed with the RID that
     * is returned. This RID will be used in all `shader_*` RenderingServer functions. Once finished
     * with your RID, you will want to free the RID using the RenderingServer's `free_rid` method.
     * Note: The equivalent resource is `Shader`.
     *
     * Generated from Godot docs: RenderingServer.shader_create
     */
    @JvmStatic
    fun shaderCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(shaderCreateBind, singleton)
    }

    /**
     * Sets the shader's source code (which triggers recompilation after being changed).
     *
     * Generated from Godot docs: RenderingServer.shader_set_code
     */
    @JvmStatic
    fun shaderSetCode(shader: RID, code: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(shaderSetCodeBind, singleton, shader, code)
    }

    /**
     * Sets the path hint for the specified shader. This should generally match the `Shader` resource's
     * `Resource.resource_path`.
     *
     * Generated from Godot docs: RenderingServer.shader_set_path_hint
     */
    @JvmStatic
    fun shaderSetPathHint(shader: RID, path: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(shaderSetPathHintBind, singleton, shader, path)
    }

    /**
     * Returns a shader's source code as a string.
     *
     * Generated from Godot docs: RenderingServer.shader_get_code
     */
    @JvmStatic
    fun shaderGetCode(shader: RID): String {
        return ObjectCalls.ptrcallWithRIDArgRetString(shaderGetCodeBind, singleton, shader)
    }

    /**
     * Returns the parameters of a shader.
     *
     * Generated from Godot docs: RenderingServer.get_shader_parameter_list
     */
    @JvmStatic
    fun getShaderParameterList(shader: RID): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithRIDArgRetDictionaryList(getShaderParameterListBind, singleton, shader)
    }

    /**
     * Returns the default value for the specified shader uniform. This is usually the value written in
     * the shader source code.
     *
     * Generated from Godot docs: RenderingServer.shader_get_parameter_default
     */
    @JvmStatic
    fun shaderGetParameterDefault(shader: RID, name: String): Any? {
        return ObjectCalls.ptrcallWithRIDAndStringNameArgRetVariantScalar(shaderGetParameterDefaultBind, singleton, shader, name)
    }

    /**
     * Sets a shader's default texture. Overwrites the texture given by name. Note: If the sampler
     * array is used use `index` to access the specified texture.
     *
     * Generated from Godot docs: RenderingServer.shader_set_default_texture_parameter
     */
    @JvmStatic
    fun shaderSetDefaultTextureParameter(shader: RID, name: String, texture: RID, index: Int = 0) {
        ObjectCalls.ptrcallWithRIDStringNameRIDIntArgs(shaderSetDefaultTextureParameterBind, singleton, shader, name, texture, index)
    }

    /**
     * Returns a default texture from a shader searched by name. Note: If the sampler array is used use
     * `index` to access the specified texture.
     *
     * Generated from Godot docs: RenderingServer.shader_get_default_texture_parameter
     */
    @JvmStatic
    fun shaderGetDefaultTextureParameter(shader: RID, name: String, index: Int = 0): RID {
        return ObjectCalls.ptrcallWithRIDStringNameAndIntArgRetRID(shaderGetDefaultTextureParameterBind, singleton, shader, name, index)
    }

    /**
     * Creates an empty material and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID will be used in all `material_*` RenderingServer functions. Once
     * finished with your RID, you will want to free the RID using the RenderingServer's `free_rid`
     * method. Note: The equivalent resource is `Material`.
     *
     * Generated from Godot docs: RenderingServer.material_create
     */
    @JvmStatic
    fun materialCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(materialCreateBind, singleton)
    }

    /**
     * Sets a shader material's shader.
     *
     * Generated from Godot docs: RenderingServer.material_set_shader
     */
    @JvmStatic
    fun materialSetShader(shaderMaterial: RID, shader: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(materialSetShaderBind, singleton, shaderMaterial, shader)
    }

    /**
     * Sets a material's parameter.
     *
     * Generated from Godot docs: RenderingServer.material_set_param
     */
    @JvmStatic
    fun materialSetParam(material: RID, parameter: String, value: Any?) {
        ObjectCalls.ptrcallWithRIDStringNameAndVariantArgs(materialSetParamBind, singleton, material, parameter, value)
    }

    /**
     * Returns the value of a certain material's parameter.
     *
     * Generated from Godot docs: RenderingServer.material_get_param
     */
    @JvmStatic
    fun materialGetParam(material: RID, parameter: String): Any? {
        return ObjectCalls.ptrcallWithRIDAndStringNameArgRetVariantScalar(materialGetParamBind, singleton, material, parameter)
    }

    /**
     * Sets a material's render priority.
     *
     * Generated from Godot docs: RenderingServer.material_set_render_priority
     */
    @JvmStatic
    fun materialSetRenderPriority(material: RID, priority: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(materialSetRenderPriorityBind, singleton, material, priority)
    }

    /**
     * Sets an object's next material.
     *
     * Generated from Godot docs: RenderingServer.material_set_next_pass
     */
    @JvmStatic
    fun materialSetNextPass(material: RID, nextMaterial: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(materialSetNextPassBind, singleton, material, nextMaterial)
    }

    /**
     * When using the Mobile renderer, `material_set_use_debanding` can be used to enable or disable
     * the debanding feature of 3D materials (`BaseMaterial3D` and `ShaderMaterial`).
     * `material_set_use_debanding` has no effect when using the Compatibility or Forward+ renderer. In
     * Forward+, `Viewport` debanding can be used instead. See also
     * `ProjectSettings.rendering/anti_aliasing/quality/use_debanding` and
     * `RenderingServer.viewport_set_use_debanding`.
     *
     * Generated from Godot docs: RenderingServer.material_set_use_debanding
     */
    @JvmStatic
    fun materialSetUseDebanding(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(materialSetUseDebandingBind, singleton, enable)
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
    @JvmStatic
    fun meshCreateFromSurfaces(surfaces: List<Map<String, Any?>>, blendShapeCount: Int = 0): RID {
        return ObjectCalls.ptrcallWithDictionaryListIntArgsRetRID(meshCreateFromSurfacesBind, singleton, surfaces, blendShapeCount)
    }

    /**
     * Creates a new mesh and adds it to the RenderingServer. It can be accessed with the RID that is
     * returned. This RID will be used in all `mesh_*` RenderingServer functions. Once finished with
     * your RID, you will want to free the RID using the RenderingServer's `free_rid` method. To place
     * in a scene, attach this mesh to an instance using `instance_set_base` using the returned RID.
     * Note: The equivalent resource is `Mesh`.
     *
     * Generated from Godot docs: RenderingServer.mesh_create
     */
    @JvmStatic
    fun meshCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(meshCreateBind, singleton)
    }

    /**
     * Returns the offset of a given attribute by `array_index` in the start of its respective buffer.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_get_format_offset
     */
    @JvmStatic
    fun meshSurfaceGetFormatOffset(format: RenderingServer.ArrayFormat, vertexCount: Int, arrayIndex: Int): Long {
        return ObjectCalls.ptrcallWithLongAndTwoIntArgsRetUInt32(meshSurfaceGetFormatOffsetBind, singleton, format.value, vertexCount, arrayIndex)
    }

    /**
     * Returns the stride of the vertex positions for a mesh with given `format`. Note importantly that
     * vertex positions are stored consecutively and are not interleaved with the other attributes in
     * the vertex buffer (normals and tangents).
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_get_format_vertex_stride
     */
    @JvmStatic
    fun meshSurfaceGetFormatVertexStride(format: RenderingServer.ArrayFormat, vertexCount: Int): Long {
        return ObjectCalls.ptrcallWithLongAndIntArgsRetUInt32(meshSurfaceGetFormatVertexStrideBind, singleton, format.value, vertexCount)
    }

    /**
     * Returns the stride of the combined normals and tangents for a mesh with given `format`. Note
     * importantly that, while normals and tangents are in the vertex buffer with vertices, they are
     * only interleaved with each other and so have a different stride than vertex positions.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_get_format_normal_tangent_stride
     */
    @JvmStatic
    fun meshSurfaceGetFormatNormalTangentStride(format: RenderingServer.ArrayFormat, vertexCount: Int): Long {
        return ObjectCalls.ptrcallWithLongAndIntArgsRetUInt32(meshSurfaceGetFormatNormalTangentStrideBind, singleton, format.value, vertexCount)
    }

    /**
     * Returns the stride of the attribute buffer for a mesh with given `format`.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_get_format_attribute_stride
     */
    @JvmStatic
    fun meshSurfaceGetFormatAttributeStride(format: RenderingServer.ArrayFormat, vertexCount: Int): Long {
        return ObjectCalls.ptrcallWithLongAndIntArgsRetUInt32(meshSurfaceGetFormatAttributeStrideBind, singleton, format.value, vertexCount)
    }

    /**
     * Returns the stride of the skin buffer for a mesh with given `format`.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_get_format_skin_stride
     */
    @JvmStatic
    fun meshSurfaceGetFormatSkinStride(format: RenderingServer.ArrayFormat, vertexCount: Int): Long {
        return ObjectCalls.ptrcallWithLongAndIntArgsRetUInt32(meshSurfaceGetFormatSkinStrideBind, singleton, format.value, vertexCount)
    }

    /**
     * Returns the stride of the index buffer for a mesh with the given `format`.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_get_format_index_stride
     */
    @JvmStatic
    fun meshSurfaceGetFormatIndexStride(format: RenderingServer.ArrayFormat, vertexCount: Int): Long {
        return ObjectCalls.ptrcallWithLongAndIntArgsRetUInt32(meshSurfaceGetFormatIndexStrideBind, singleton, format.value, vertexCount)
    }

    /**
     * Creates a new surface on the given `mesh`. Equivalent to `mesh_add_surface_from_arrays`, but
     * takes a single `Dictionary` argument instead of separate arguments. The dictionary must follow
     * this structure:
     *
     * Generated from Godot docs: RenderingServer.mesh_add_surface
     */
    @JvmStatic
    fun meshAddSurface(mesh: RID, surface: Map<String, Any?>) {
        ObjectCalls.ptrcallWithRIDAndDictionaryArg(meshAddSurfaceBind, singleton, mesh, surface)
    }

    /**
     * Creates a new surface on the given `mesh`. `mesh_get_surface_count` will become the surface
     * index for this new surface. Surfaces are created to be rendered using a `primitive`, which may
     * be any of the values defined in `Mesh.PrimitiveType`. The `arrays` argument is an array of
     * arrays. Each of the `Mesh.ARRAY_MAX` elements contains an array with some of the mesh data for
     * this surface as described by the corresponding member of `Mesh.ArrayType` or `null` if it is not
     * used by the surface. For example, `arrays[0]` is the array of vertices. That first vertex
     * sub-array is always required; the others are optional. Adding an index array puts this surface
     * into "index mode" where the vertex and other arrays become the sources of data and the index
     * array defines the vertex order. All sub-arrays must have the same length as the vertex array (or
     * be an exact multiple of the vertex array's length, when multiple elements of a sub-array
     * correspond to a single vertex) or be empty, except for `Mesh.ARRAY_INDEX` if it is used. The
     * `blend_shapes` argument is an array of vertex data for each blend shape. Each element is an
     * array of the same structure as `arrays`, but `Mesh.ARRAY_VERTEX`, `Mesh.ARRAY_NORMAL`, and
     * `Mesh.ARRAY_TANGENT` are set if and only if they are set in `arrays` and all other entries are
     * `null`. The `lods` argument is a dictionary with `float` keys and `PackedInt32Array` values.
     * Each entry in the dictionary represents an LOD level of the surface, where the value is the
     * `Mesh.ARRAY_INDEX` array to use for the LOD level and the key is roughly proportional to the
     * distance at which the LOD stats being used. I.e., increasing the key of an LOD also increases
     * the distance that the objects has to be from the camera before the LOD is used. The
     * `compress_format` argument is the bitwise OR of, as required: One value of `ArrayFormat` left
     * shifted by `ARRAY_FORMAT_CUSTOMn_SHIFT` for each custom channel in use,
     * `ARRAY_FLAG_USE_DYNAMIC_UPDATE`, `ARRAY_FLAG_USE_8_BONE_WEIGHTS`, or
     * `ARRAY_FLAG_USES_EMPTY_VERTEX_ARRAY`. See `ArrayMesh.add_surface_from_arrays` and
     * `ImporterMesh.add_surface` for higher-level equivalents of this method. Note: When using
     * indices, it is recommended to only use points, lines, or triangles.
     *
     * Generated from Godot docs: RenderingServer.mesh_add_surface_from_arrays
     */
    @JvmStatic
    fun meshAddSurfaceFromArrays(mesh: RID, primitive: RenderingServer.PrimitiveType, arrays: List<Any?>, blendShapes: List<Any?> = emptyList(), lods: Map<String, Any?> = emptyMap(), compressFormat: RenderingServer.ArrayFormat = RenderingServer.ArrayFormat.FLAG_FORMAT_VERSION_1) {
        ObjectCalls.ptrcallWithRIDLongTwoArrayDictionaryLongArgs(meshAddSurfaceFromArraysBind, singleton, mesh, primitive.value, arrays, blendShapes, lods, compressFormat.value)
    }

    /**
     * Returns a mesh's blend shape count.
     *
     * Generated from Godot docs: RenderingServer.mesh_get_blend_shape_count
     */
    @JvmStatic
    fun meshGetBlendShapeCount(mesh: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(meshGetBlendShapeCountBind, singleton, mesh)
    }

    /**
     * Sets a mesh's blend shape mode.
     *
     * Generated from Godot docs: RenderingServer.mesh_set_blend_shape_mode
     */
    @JvmStatic
    fun meshSetBlendShapeMode(mesh: RID, mode: RenderingServer.BlendShapeMode) {
        ObjectCalls.ptrcallWithRIDAndLongArg(meshSetBlendShapeModeBind, singleton, mesh, mode.value)
    }

    /**
     * Returns a mesh's blend shape mode.
     *
     * Generated from Godot docs: RenderingServer.mesh_get_blend_shape_mode
     */
    @JvmStatic
    fun meshGetBlendShapeMode(mesh: RID): RenderingServer.BlendShapeMode {
        return RenderingServer.BlendShapeMode(ObjectCalls.ptrcallWithRIDArgRetLong(meshGetBlendShapeModeBind, singleton, mesh))
    }

    /**
     * Sets a mesh's surface's material.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_set_material
     */
    @JvmStatic
    fun meshSurfaceSetMaterial(mesh: RID, surface: Int, material: RID) {
        ObjectCalls.ptrcallWithRIDIntAndRIDArgs(meshSurfaceSetMaterialBind, singleton, mesh, surface, material)
    }

    /**
     * Returns a mesh's surface's material.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_get_material
     */
    @JvmStatic
    fun meshSurfaceGetMaterial(mesh: RID, surface: Int): RID {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetRID(meshSurfaceGetMaterialBind, singleton, mesh, surface)
    }

    /**
     * Returns a mesh's surface as a dictionary following the same structure as described in
     * `mesh_add_surface`.
     *
     * Generated from Godot docs: RenderingServer.mesh_get_surface
     */
    @JvmStatic
    fun meshGetSurface(mesh: RID, surface: Int): Map<String, Any?> {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetDictionary(meshGetSurfaceBind, singleton, mesh, surface)
    }

    /**
     * Returns a mesh's surface's buffer arrays.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_get_arrays
     */
    @JvmStatic
    fun meshSurfaceGetArrays(mesh: RID, surface: Int): List<Any?> {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetArray(meshSurfaceGetArraysBind, singleton, mesh, surface)
    }

    /**
     * Returns a mesh's surface's arrays for blend shapes.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_get_blend_shape_arrays
     */
    @JvmStatic
    fun meshSurfaceGetBlendShapeArrays(mesh: RID, surface: Int): List<List<Any?>> {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetArrayList(meshSurfaceGetBlendShapeArraysBind, singleton, mesh, surface)
    }

    /**
     * Returns a mesh's number of surfaces.
     *
     * Generated from Godot docs: RenderingServer.mesh_get_surface_count
     */
    @JvmStatic
    fun meshGetSurfaceCount(mesh: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(meshGetSurfaceCountBind, singleton, mesh)
    }

    /**
     * Sets a mesh's custom aabb.
     *
     * Generated from Godot docs: RenderingServer.mesh_set_custom_aabb
     */
    @JvmStatic
    fun meshSetCustomAabb(mesh: RID, aabb: AABB) {
        ObjectCalls.ptrcallWithRIDAndAABBArg(meshSetCustomAabbBind, singleton, mesh, aabb)
    }

    /**
     * Returns a mesh's custom aabb.
     *
     * Generated from Godot docs: RenderingServer.mesh_get_custom_aabb
     */
    @JvmStatic
    fun meshGetCustomAabb(mesh: RID): AABB {
        return ObjectCalls.ptrcallWithRIDArgRetAABB(meshGetCustomAabbBind, singleton, mesh)
    }

    /**
     * Removes the surface at the given index from the Mesh, shifting surfaces with higher index down
     * by one.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_remove
     */
    @JvmStatic
    fun meshSurfaceRemove(mesh: RID, surface: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(meshSurfaceRemoveBind, singleton, mesh, surface)
    }

    /**
     * Removes all surfaces from a mesh.
     *
     * Generated from Godot docs: RenderingServer.mesh_clear
     */
    @JvmStatic
    fun meshClear(mesh: RID) {
        ObjectCalls.ptrcallWithRIDArg(meshClearBind, singleton, mesh)
    }

    /**
     * Updates the vertex buffer of the mesh surface with the given `data`. The expected data per
     * vertex is 8 or 12 bytes (4 bytes per float, 2 floats per `Vector2`, and 3 floats per `Vector3`)
     * depending on if the mesh is using `Vector2` or `Vector3` vertices. This value can be determined
     * with `mesh_surface_get_format_vertex_stride` instead. The starting point of the updates can be
     * changed with `offset`. The value of `offset` should be a multiple of 12 bytes in most cases to
     * align to each vertex. A `PackedVector3Array` of vertex locations can be converted into a
     * `PackedByteArray` using `PackedVector3Array.to_byte_array` for use in `data`.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_update_vertex_region
     */
    @JvmStatic
    fun meshSurfaceUpdateVertexRegion(mesh: RID, surface: Int, offset: Int, data: ByteArray) {
        ObjectCalls.ptrcallWithRIDIntIntAndByteArrayArgs(meshSurfaceUpdateVertexRegionBind, singleton, mesh, surface, offset, data)
    }

    /**
     * Updates the attribute buffer of the mesh surface with the given `data`. The expected data per
     * attribute is 8 or 12 bytes (4 bytes per float, 2 floats per `Vector2`, and 3 floats per
     * `Vector3`) depending on if the mesh is using `Vector2` or `Vector3` vertices. This value can be
     * determined with `mesh_surface_get_format_attribute_stride` instead. The starting point of the
     * updates can be changed with `offset`. The value of `offset` should be a multiple of 12 bytes in
     * most cases to align to each attribute. A `PackedVector3Array` of attribute locations can be
     * converted into a `PackedByteArray` using `PackedVector3Array.to_byte_array` for use in `data`.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_update_attribute_region
     */
    @JvmStatic
    fun meshSurfaceUpdateAttributeRegion(mesh: RID, surface: Int, offset: Int, data: ByteArray) {
        ObjectCalls.ptrcallWithRIDIntIntAndByteArrayArgs(meshSurfaceUpdateAttributeRegionBind, singleton, mesh, surface, offset, data)
    }

    /**
     * Updates the skin buffer of the mesh surface with the given `data`. The expected data per skin is
     * 8 or 12 bytes (4 bytes per float, 2 floats per `Vector2`, and 3 floats per `Vector3`) depending
     * on if the mesh is using `Vector2` or `Vector3` vertices. This value can be determined with
     * `mesh_surface_get_format_skin_stride` instead. The starting point of the updates can be changed
     * with `offset`. The value of `offset` should be a multiple of 12 bytes in most cases to align to
     * each skin. A `PackedVector3Array` of skin locations can be converted into a `PackedByteArray`
     * using `PackedVector3Array.to_byte_array` for use in `data`.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_update_skin_region
     */
    @JvmStatic
    fun meshSurfaceUpdateSkinRegion(mesh: RID, surface: Int, offset: Int, data: ByteArray) {
        ObjectCalls.ptrcallWithRIDIntIntAndByteArrayArgs(meshSurfaceUpdateSkinRegionBind, singleton, mesh, surface, offset, data)
    }

    /**
     * Updates the index buffer of the mesh surface with the given `data`. The expected data are 16 or
     * 32-bit unsigned integers, which can be determined with `mesh_surface_get_format_index_stride`.
     *
     * Generated from Godot docs: RenderingServer.mesh_surface_update_index_region
     */
    @JvmStatic
    fun meshSurfaceUpdateIndexRegion(mesh: RID, surface: Int, offset: Int, data: ByteArray) {
        ObjectCalls.ptrcallWithRIDIntIntAndByteArrayArgs(meshSurfaceUpdateIndexRegionBind, singleton, mesh, surface, offset, data)
    }

    /**
     * Sets an optional second mesh which can be used for rendering shadows and the depth prepass. Can
     * be used to increase performance by supplying a mesh with fused vertices and only vertex position
     * data (without normals, UVs, colors, etc.). Note: This mesh must have exactly the same vertex
     * positions as the source mesh (including the source mesh's LODs, if present). If vertex positions
     * differ, then the mesh will not draw correctly.
     *
     * Generated from Godot docs: RenderingServer.mesh_set_shadow_mesh
     */
    @JvmStatic
    fun meshSetShadowMesh(mesh: RID, shadowMesh: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(meshSetShadowMeshBind, singleton, mesh, shadowMesh)
    }

    /**
     * Creates a new multimesh on the RenderingServer and returns an `RID` handle. This RID will be
     * used in all `multimesh_*` RenderingServer functions. Once finished with your RID, you will want
     * to free the RID using the RenderingServer's `free_rid` method. To place in a scene, attach this
     * multimesh to an instance using `instance_set_base` using the returned RID. Note: The equivalent
     * resource is `MultiMesh`.
     *
     * Generated from Godot docs: RenderingServer.multimesh_create
     */
    @JvmStatic
    fun multimeshCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(multimeshCreateBind, singleton)
    }

    /**
     * Sets up the multimesh using the specified data. The number of instances is set by `instances`.
     * The format of the instance transforms is set by `transform_format`, which should be set
     * according to whether the multimesh is meant to be rendered in 2D or 3D. If `color_format` is
     * `true`, each instance will have a color associated with it. If `custom_data_format` is `true`,
     * each instance will have a custom data vector associated with it. If `use_indirect` is `true`, an
     * indirect command buffer will be created for this multimesh, allowing the instance count to be
     * modified directly on the GPU. See also `multimesh_get_command_buffer_rd_rid`.
     *
     * Generated from Godot docs: RenderingServer.multimesh_allocate_data
     */
    @JvmStatic
    fun multimeshAllocateData(multimesh: RID, instances: Int, transformFormat: RenderingServer.MultimeshTransformFormat, colorFormat: Boolean = false, customDataFormat: Boolean = false, useIndirect: Boolean = false) {
        ObjectCalls.ptrcallWithRIDIntLongThreeBoolArgs(multimeshAllocateDataBind, singleton, multimesh, instances, transformFormat.value, colorFormat, customDataFormat, useIndirect)
    }

    /**
     * Returns the number of instances allocated for this multimesh.
     *
     * Generated from Godot docs: RenderingServer.multimesh_get_instance_count
     */
    @JvmStatic
    fun multimeshGetInstanceCount(multimesh: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(multimeshGetInstanceCountBind, singleton, multimesh)
    }

    /**
     * Sets the mesh to be drawn by the multimesh. Equivalent to `MultiMesh.mesh`.
     *
     * Generated from Godot docs: RenderingServer.multimesh_set_mesh
     */
    @JvmStatic
    fun multimeshSetMesh(multimesh: RID, mesh: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(multimeshSetMeshBind, singleton, multimesh, mesh)
    }

    /**
     * Sets the `Transform3D` for this instance. Equivalent to `MultiMesh.set_instance_transform`.
     *
     * Generated from Godot docs: RenderingServer.multimesh_instance_set_transform
     */
    @JvmStatic
    fun multimeshInstanceSetTransform(multimesh: RID, index: Int, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDIntAndTransform3DArg(multimeshInstanceSetTransformBind, singleton, multimesh, index, transform)
    }

    /**
     * Sets the `Transform2D` for this instance. For use when multimesh is used in 2D. Equivalent to
     * `MultiMesh.set_instance_transform_2d`.
     *
     * Generated from Godot docs: RenderingServer.multimesh_instance_set_transform_2d
     */
    @JvmStatic
    fun multimeshInstanceSetTransform2d(multimesh: RID, index: Int, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDIntAndTransform2DArg(multimeshInstanceSetTransform2dBind, singleton, multimesh, index, transform)
    }

    /**
     * Sets the color by which this instance will be modulated. Equivalent to
     * `MultiMesh.set_instance_color`.
     *
     * Generated from Godot docs: RenderingServer.multimesh_instance_set_color
     */
    @JvmStatic
    fun multimeshInstanceSetColor(multimesh: RID, index: Int, color: Color) {
        ObjectCalls.ptrcallWithRIDIntAndColorArgs(multimeshInstanceSetColorBind, singleton, multimesh, index, color)
    }

    /**
     * Sets the custom data for this instance. Custom data is passed as a `Color`, but is interpreted
     * as a `vec4` in the shader. Equivalent to `MultiMesh.set_instance_custom_data`.
     *
     * Generated from Godot docs: RenderingServer.multimesh_instance_set_custom_data
     */
    @JvmStatic
    fun multimeshInstanceSetCustomData(multimesh: RID, index: Int, customData: Color) {
        ObjectCalls.ptrcallWithRIDIntAndColorArgs(multimeshInstanceSetCustomDataBind, singleton, multimesh, index, customData)
    }

    /**
     * Returns the RID of the mesh that will be used in drawing this multimesh.
     *
     * Generated from Godot docs: RenderingServer.multimesh_get_mesh
     */
    @JvmStatic
    fun multimeshGetMesh(multimesh: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(multimeshGetMeshBind, singleton, multimesh)
    }

    /**
     * Calculates and returns the axis-aligned bounding box that encloses all instances within the
     * multimesh.
     *
     * Generated from Godot docs: RenderingServer.multimesh_get_aabb
     */
    @JvmStatic
    fun multimeshGetAabb(multimesh: RID): AABB {
        return ObjectCalls.ptrcallWithRIDArgRetAABB(multimeshGetAabbBind, singleton, multimesh)
    }

    /**
     * Sets the custom AABB for this MultiMesh resource.
     *
     * Generated from Godot docs: RenderingServer.multimesh_set_custom_aabb
     */
    @JvmStatic
    fun multimeshSetCustomAabb(multimesh: RID, aabb: AABB) {
        ObjectCalls.ptrcallWithRIDAndAABBArg(multimeshSetCustomAabbBind, singleton, multimesh, aabb)
    }

    /**
     * Returns the custom AABB defined for this MultiMesh resource.
     *
     * Generated from Godot docs: RenderingServer.multimesh_get_custom_aabb
     */
    @JvmStatic
    fun multimeshGetCustomAabb(multimesh: RID): AABB {
        return ObjectCalls.ptrcallWithRIDArgRetAABB(multimeshGetCustomAabbBind, singleton, multimesh)
    }

    /**
     * Returns the `Transform3D` of the specified instance.
     *
     * Generated from Godot docs: RenderingServer.multimesh_instance_get_transform
     */
    @JvmStatic
    fun multimeshInstanceGetTransform(multimesh: RID, index: Int): Transform3D {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetTransform3D(multimeshInstanceGetTransformBind, singleton, multimesh, index)
    }

    /**
     * Returns the `Transform2D` of the specified instance. For use when the multimesh is set to use 2D
     * transforms.
     *
     * Generated from Godot docs: RenderingServer.multimesh_instance_get_transform_2d
     */
    @JvmStatic
    fun multimeshInstanceGetTransform2d(multimesh: RID, index: Int): Transform2D {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetTransform2D(multimeshInstanceGetTransform2dBind, singleton, multimesh, index)
    }

    /**
     * Returns the color by which the specified instance will be modulated.
     *
     * Generated from Godot docs: RenderingServer.multimesh_instance_get_color
     */
    @JvmStatic
    fun multimeshInstanceGetColor(multimesh: RID, index: Int): Color {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetColor(multimeshInstanceGetColorBind, singleton, multimesh, index)
    }

    /**
     * Returns the custom data associated with the specified instance.
     *
     * Generated from Godot docs: RenderingServer.multimesh_instance_get_custom_data
     */
    @JvmStatic
    fun multimeshInstanceGetCustomData(multimesh: RID, index: Int): Color {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetColor(multimeshInstanceGetCustomDataBind, singleton, multimesh, index)
    }

    /**
     * Sets the number of instances visible at a given time. If -1, all instances that have been
     * allocated are drawn. Equivalent to `MultiMesh.visible_instance_count`.
     *
     * Generated from Godot docs: RenderingServer.multimesh_set_visible_instances
     */
    @JvmStatic
    fun multimeshSetVisibleInstances(multimesh: RID, visible: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(multimeshSetVisibleInstancesBind, singleton, multimesh, visible)
    }

    /**
     * Returns the number of visible instances for this multimesh.
     *
     * Generated from Godot docs: RenderingServer.multimesh_get_visible_instances
     */
    @JvmStatic
    fun multimeshGetVisibleInstances(multimesh: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(multimeshGetVisibleInstancesBind, singleton, multimesh)
    }

    /**
     * Set the entire data to use for drawing the `multimesh` at once to `buffer` (such as instance
     * transforms and colors). `buffer`'s size must match the number of instances multiplied by the
     * per-instance data size (which depends on the enabled MultiMesh fields). Otherwise, an error
     * message is printed and nothing is rendered. See also `multimesh_get_buffer`. The per-instance
     * data size and expected data order is:
     *
     * Generated from Godot docs: RenderingServer.multimesh_set_buffer
     */
    @JvmStatic
    fun multimeshSetBuffer(multimesh: RID, buffer: List<Float>) {
        ObjectCalls.ptrcallWithRIDAndPackedFloat32ListArg(multimeshSetBufferBind, singleton, multimesh, buffer)
    }

    /**
     * Returns the `RenderingDevice` `RID` handle of the `MultiMesh` command buffer. This `RID` is only
     * valid if `use_indirect` is set to `true` when allocating data through `multimesh_allocate_data`.
     * It can be used to directly modify the instance count via buffer. The data structure is dependent
     * on both how many surfaces the mesh contains and whether it is indexed or not, the buffer has 5
     * integers in it, with the last unused if the mesh is not indexed. Each of the values in the
     * buffer correspond to these options:
     *
     * Generated from Godot docs: RenderingServer.multimesh_get_command_buffer_rd_rid
     */
    @JvmStatic
    fun multimeshGetCommandBufferRdRid(multimesh: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(multimeshGetCommandBufferRdRidBind, singleton, multimesh)
    }

    /**
     * Returns the `RenderingDevice` `RID` handle of the `MultiMesh`, which can be used as any other
     * buffer on the Rendering Device.
     *
     * Generated from Godot docs: RenderingServer.multimesh_get_buffer_rd_rid
     */
    @JvmStatic
    fun multimeshGetBufferRdRid(multimesh: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(multimeshGetBufferRdRidBind, singleton, multimesh)
    }

    /**
     * Returns the MultiMesh data (such as instance transforms, colors, etc.). See
     * `multimesh_set_buffer` for details on the returned data. Note: If the buffer is in the engine's
     * internal cache, it will have to be fetched from GPU memory and possibly decompressed. This means
     * `multimesh_get_buffer` is potentially a slow operation and should be avoided whenever possible.
     *
     * Generated from Godot docs: RenderingServer.multimesh_get_buffer
     */
    @JvmStatic
    fun multimeshGetBuffer(multimesh: RID): List<Float> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedFloat32List(multimeshGetBufferBind, singleton, multimesh)
    }

    /**
     * Alternative version of `multimesh_set_buffer` for use with physics interpolation. Takes both an
     * array of current data and an array of data for the previous physics tick.
     *
     * Generated from Godot docs: RenderingServer.multimesh_set_buffer_interpolated
     */
    @JvmStatic
    fun multimeshSetBufferInterpolated(multimesh: RID, buffer: List<Float>, bufferPrevious: List<Float>) {
        ObjectCalls.ptrcallWithRIDAndTwoPackedFloat32ListArgs(multimeshSetBufferInterpolatedBind, singleton, multimesh, buffer, bufferPrevious)
    }

    /**
     * Turns on and off physics interpolation for this MultiMesh resource.
     *
     * Generated from Godot docs: RenderingServer.multimesh_set_physics_interpolated
     */
    @JvmStatic
    fun multimeshSetPhysicsInterpolated(multimesh: RID, interpolated: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(multimeshSetPhysicsInterpolatedBind, singleton, multimesh, interpolated)
    }

    /**
     * Sets the physics interpolation quality for the `MultiMesh`. A value of
     * `MULTIMESH_INTERP_QUALITY_FAST` gives fast but low quality interpolation, a value of
     * `MULTIMESH_INTERP_QUALITY_HIGH` gives slower but higher quality interpolation.
     *
     * Generated from Godot docs: RenderingServer.multimesh_set_physics_interpolation_quality
     */
    @JvmStatic
    fun multimeshSetPhysicsInterpolationQuality(multimesh: RID, quality: RenderingServer.MultimeshPhysicsInterpolationQuality) {
        ObjectCalls.ptrcallWithRIDAndLongArg(multimeshSetPhysicsInterpolationQualityBind, singleton, multimesh, quality.value)
    }

    /**
     * Prevents physics interpolation for the specified instance during the current physics tick. This
     * is useful when moving an instance to a new location, to give an instantaneous change rather than
     * interpolation from the previous location.
     *
     * Generated from Godot docs: RenderingServer.multimesh_instance_reset_physics_interpolation
     */
    @JvmStatic
    fun multimeshInstanceResetPhysicsInterpolation(multimesh: RID, index: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(multimeshInstanceResetPhysicsInterpolationBind, singleton, multimesh, index)
    }

    /**
     * Prevents physics interpolation for all instances during the current physics tick. This is useful
     * when moving all instances to new locations, to give instantaneous changes rather than
     * interpolation from the previous locations.
     *
     * Generated from Godot docs: RenderingServer.multimesh_instances_reset_physics_interpolation
     */
    @JvmStatic
    fun multimeshInstancesResetPhysicsInterpolation(multimesh: RID) {
        ObjectCalls.ptrcallWithRIDArg(multimeshInstancesResetPhysicsInterpolationBind, singleton, multimesh)
    }

    /**
     * Creates a skeleton and adds it to the RenderingServer. It can be accessed with the RID that is
     * returned. This RID will be used in all `skeleton_*` RenderingServer functions. Once finished
     * with your RID, you will want to free the RID using the RenderingServer's `free_rid` method.
     *
     * Generated from Godot docs: RenderingServer.skeleton_create
     */
    @JvmStatic
    fun skeletonCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(skeletonCreateBind, singleton)
    }

    /**
     * Allocates data for this skeleton using the number of bones specified in `bones`. If
     * `is_2d_skeleton` is `true`, the skeleton will be treated as a 2D skeleton instead of a 3D
     * skeleton. See also `skeleton_get_bone_count`.
     *
     * Generated from Godot docs: RenderingServer.skeleton_allocate_data
     */
    @JvmStatic
    fun skeletonAllocateData(skeleton: RID, bones: Int, is2dSkeleton: Boolean = false) {
        ObjectCalls.ptrcallWithRIDIntAndBoolArgs(skeletonAllocateDataBind, singleton, skeleton, bones, is2dSkeleton)
    }

    /**
     * Returns the number of bones allocated for this skeleton. See also `skeleton_allocate_data`.
     *
     * Generated from Godot docs: RenderingServer.skeleton_get_bone_count
     */
    @JvmStatic
    fun skeletonGetBoneCount(skeleton: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(skeletonGetBoneCountBind, singleton, skeleton)
    }

    /**
     * Sets the `Transform3D` for a specific bone of this skeleton.
     *
     * Generated from Godot docs: RenderingServer.skeleton_bone_set_transform
     */
    @JvmStatic
    fun skeletonBoneSetTransform(skeleton: RID, bone: Int, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDIntAndTransform3DArg(skeletonBoneSetTransformBind, singleton, skeleton, bone, transform)
    }

    /**
     * Returns the `Transform3D` set for a specific bone of this skeleton.
     *
     * Generated from Godot docs: RenderingServer.skeleton_bone_get_transform
     */
    @JvmStatic
    fun skeletonBoneGetTransform(skeleton: RID, bone: Int): Transform3D {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetTransform3D(skeletonBoneGetTransformBind, singleton, skeleton, bone)
    }

    /**
     * Sets the `Transform2D` for a specific bone of this skeleton.
     *
     * Generated from Godot docs: RenderingServer.skeleton_bone_set_transform_2d
     */
    @JvmStatic
    fun skeletonBoneSetTransform2d(skeleton: RID, bone: Int, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDIntAndTransform2DArg(skeletonBoneSetTransform2dBind, singleton, skeleton, bone, transform)
    }

    /**
     * Returns the `Transform2D` set for a specific bone of this skeleton.
     *
     * Generated from Godot docs: RenderingServer.skeleton_bone_get_transform_2d
     */
    @JvmStatic
    fun skeletonBoneGetTransform2d(skeleton: RID, bone: Int): Transform2D {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetTransform2D(skeletonBoneGetTransform2dBind, singleton, skeleton, bone)
    }

    /**
     * Sets the base `Transform2D` to use for the specified skeleton.
     *
     * Generated from Godot docs: RenderingServer.skeleton_set_base_transform_2d
     */
    @JvmStatic
    fun skeletonSetBaseTransform2d(skeleton: RID, baseTransform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(skeletonSetBaseTransform2dBind, singleton, skeleton, baseTransform)
    }

    /**
     * Creates a directional light and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID can be used in most `light_*` RenderingServer functions. Once
     * finished with your RID, you will want to free the RID using the RenderingServer's `free_rid`
     * method. To place in a scene, attach this directional light to an instance using
     * `instance_set_base` using the returned RID. Note: The equivalent node is `DirectionalLight3D`.
     *
     * Generated from Godot docs: RenderingServer.directional_light_create
     */
    @JvmStatic
    fun directionalLightCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(directionalLightCreateBind, singleton)
    }

    /**
     * Creates a new omni light and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID can be used in most `light_*` RenderingServer functions. Once
     * finished with your RID, you will want to free the RID using the RenderingServer's `free_rid`
     * method. To place in a scene, attach this omni light to an instance using `instance_set_base`
     * using the returned RID. Note: The equivalent node is `OmniLight3D`.
     *
     * Generated from Godot docs: RenderingServer.omni_light_create
     */
    @JvmStatic
    fun omniLightCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(omniLightCreateBind, singleton)
    }

    /**
     * Creates a spot light and adds it to the RenderingServer. It can be accessed with the RID that is
     * returned. This RID can be used in most `light_*` RenderingServer functions. Once finished with
     * your RID, you will want to free the RID using the RenderingServer's `free_rid` method. To place
     * in a scene, attach this spot light to an instance using `instance_set_base` using the returned
     * RID.
     *
     * Generated from Godot docs: RenderingServer.spot_light_create
     */
    @JvmStatic
    fun spotLightCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(spotLightCreateBind, singleton)
    }

    /**
     * Creates a new area light and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID can be used in most `light_*` RenderingServer functions. Once
     * finished with your RID, you will want to free the RID using the RenderingServer's `free_rid`
     * method. To place in a scene, attach this area light to an instance using `instance_set_base`
     * using the returned RID. Note: The equivalent node is `AreaLight3D`.
     *
     * Generated from Godot docs: RenderingServer.area_light_create
     */
    @JvmStatic
    fun areaLightCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(areaLightCreateBind, singleton)
    }

    /**
     * Sets the color of the light. Equivalent to `Light3D.light_color`.
     *
     * Generated from Godot docs: RenderingServer.light_set_color
     */
    @JvmStatic
    fun lightSetColor(light: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(lightSetColorBind, singleton, light, color)
    }

    /**
     * Sets the specified 3D light parameter. Equivalent to `Light3D.set_param`.
     *
     * Generated from Godot docs: RenderingServer.light_set_param
     */
    @JvmStatic
    fun lightSetParam(light: RID, param: RenderingServer.LightParam, value: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(lightSetParamBind, singleton, light, param.value, value)
    }

    /**
     * If `true`, light will cast shadows. Equivalent to `Light3D.shadow_enabled`.
     *
     * Generated from Godot docs: RenderingServer.light_set_shadow
     */
    @JvmStatic
    fun lightSetShadow(light: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(lightSetShadowBind, singleton, light, enabled)
    }

    /**
     * Sets the projector texture to use for the specified 3D light. Equivalent to
     * `Light3D.light_projector`.
     *
     * Generated from Godot docs: RenderingServer.light_set_projector
     */
    @JvmStatic
    fun lightSetProjector(light: RID, texture: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(lightSetProjectorBind, singleton, light, texture)
    }

    /**
     * If `true`, the 3D light will subtract light instead of adding light. Equivalent to
     * `Light3D.light_negative`.
     *
     * Generated from Godot docs: RenderingServer.light_set_negative
     */
    @JvmStatic
    fun lightSetNegative(light: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(lightSetNegativeBind, singleton, light, enable)
    }

    /**
     * Sets the cull mask for this 3D light. Lights only affect objects in the selected layers.
     * Equivalent to `Light3D.light_cull_mask`.
     *
     * Generated from Godot docs: RenderingServer.light_set_cull_mask
     */
    @JvmStatic
    fun lightSetCullMask(light: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(lightSetCullMaskBind, singleton, light, mask)
    }

    /**
     * Sets the distance fade for this 3D light. This acts as a form of level of detail (LOD) and can
     * be used to improve performance. Equivalent to `Light3D.distance_fade_enabled`,
     * `Light3D.distance_fade_begin`, `Light3D.distance_fade_shadow`, and
     * `Light3D.distance_fade_length`.
     *
     * Generated from Godot docs: RenderingServer.light_set_distance_fade
     */
    @JvmStatic
    fun lightSetDistanceFade(decal: RID, enabled: Boolean, begin: Double, shadow: Double, length: Double) {
        ObjectCalls.ptrcallWithRIDBoolThreeDoubleArgs(lightSetDistanceFadeBind, singleton, decal, enabled, begin, shadow, length)
    }

    /**
     * If `true`, reverses the backface culling of the mesh. This can be useful when you have a flat
     * mesh that has a light behind it. If you need to cast a shadow on both sides of the mesh, set the
     * mesh to use double-sided shadows with `instance_geometry_set_cast_shadows_setting`. Equivalent
     * to `Light3D.shadow_reverse_cull_face`.
     *
     * Generated from Godot docs: RenderingServer.light_set_reverse_cull_face_mode
     */
    @JvmStatic
    fun lightSetReverseCullFaceMode(light: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(lightSetReverseCullFaceModeBind, singleton, light, enabled)
    }

    /**
     * Sets the shadow caster mask for this 3D light. Shadows will only be cast using objects in the
     * selected layers. Equivalent to `Light3D.shadow_caster_mask`.
     *
     * Generated from Godot docs: RenderingServer.light_set_shadow_caster_mask
     */
    @JvmStatic
    fun lightSetShadowCasterMask(light: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(lightSetShadowCasterMaskBind, singleton, light, mask)
    }

    /**
     * Sets the bake mode to use for the specified 3D light. Equivalent to `Light3D.light_bake_mode`.
     *
     * Generated from Godot docs: RenderingServer.light_set_bake_mode
     */
    @JvmStatic
    fun lightSetBakeMode(light: RID, bakeMode: RenderingServer.LightBakeMode) {
        ObjectCalls.ptrcallWithRIDAndLongArg(lightSetBakeModeBind, singleton, light, bakeMode.value)
    }

    /**
     * Sets the maximum SDFGI cascade in which the 3D light's indirect lighting is rendered. Higher
     * values allow the light to be rendered in SDFGI further away from the camera.
     *
     * Generated from Godot docs: RenderingServer.light_set_max_sdfgi_cascade
     */
    @JvmStatic
    fun lightSetMaxSdfgiCascade(light: RID, cascade: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(lightSetMaxSdfgiCascadeBind, singleton, light, cascade)
    }

    /**
     * Sets whether to use a dual paraboloid or a cubemap for the shadow map. Dual paraboloid is faster
     * but may suffer from artifacts. Equivalent to `OmniLight3D.omni_shadow_mode`.
     *
     * Generated from Godot docs: RenderingServer.light_omni_set_shadow_mode
     */
    @JvmStatic
    fun lightOmniSetShadowMode(light: RID, mode: RenderingServer.LightOmniShadowMode) {
        ObjectCalls.ptrcallWithRIDAndLongArg(lightOmniSetShadowModeBind, singleton, light, mode.value)
    }

    /**
     * Sets the shadow mode for this directional light. Equivalent to
     * `DirectionalLight3D.directional_shadow_mode`.
     *
     * Generated from Godot docs: RenderingServer.light_directional_set_shadow_mode
     */
    @JvmStatic
    fun lightDirectionalSetShadowMode(light: RID, mode: RenderingServer.LightDirectionalShadowMode) {
        ObjectCalls.ptrcallWithRIDAndLongArg(lightDirectionalSetShadowModeBind, singleton, light, mode.value)
    }

    /**
     * If `true`, this directional light will blend between shadow map splits resulting in a smoother
     * transition between them. Equivalent to `DirectionalLight3D.directional_shadow_blend_splits`.
     *
     * Generated from Godot docs: RenderingServer.light_directional_set_blend_splits
     */
    @JvmStatic
    fun lightDirectionalSetBlendSplits(light: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(lightDirectionalSetBlendSplitsBind, singleton, light, enable)
    }

    /**
     * If `true`, this light will not be used for anything except sky shaders. Use this for lights that
     * impact your sky shader that you may want to hide from affecting the rest of the scene. For
     * example, you may want to enable this when the sun in your sky shader falls below the horizon.
     *
     * Generated from Godot docs: RenderingServer.light_directional_set_sky_mode
     */
    @JvmStatic
    fun lightDirectionalSetSkyMode(light: RID, mode: RenderingServer.LightDirectionalSkyMode) {
        ObjectCalls.ptrcallWithRIDAndLongArg(lightDirectionalSetSkyModeBind, singleton, light, mode.value)
    }

    /**
     * Sets the extents (width and height) in meters for this area light. Equivalent to
     * `AreaLight3D.area_size`.
     *
     * Generated from Godot docs: RenderingServer.light_area_set_size
     */
    @JvmStatic
    fun lightAreaSetSize(light: RID, size: Vector2) {
        ObjectCalls.ptrcallWithRIDAndVector2Arg(lightAreaSetSizeBind, singleton, light, size)
    }

    /**
     * Defines whether the energy of an `AreaLight3D` is normalized (divided) by its area. If set to
     * `true`, changing the size does not affect the total energy output. Equivalent to
     * `AreaLight3D.area_normalize_energy`.
     *
     * Generated from Godot docs: RenderingServer.light_area_set_normalize_energy
     */
    @JvmStatic
    fun lightAreaSetNormalizeEnergy(light: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(lightAreaSetNormalizeEnergyBind, singleton, light, enable)
    }

    /**
     * Sets the texture filter mode to use when rendering light projectors. This parameter is global
     * and cannot be set on a per-light basis.
     *
     * Generated from Godot docs: RenderingServer.light_projectors_set_filter
     */
    @JvmStatic
    fun lightProjectorsSetFilter(filter: RenderingServer.LightProjectorFilter) {
        ObjectCalls.ptrcallWithLongArg(lightProjectorsSetFilterBind, singleton, filter.value)
    }

    /**
     * Toggles whether a bicubic filter should be used when lightmaps are sampled. This smoothens their
     * appearance at a performance cost.
     *
     * Generated from Godot docs: RenderingServer.lightmaps_set_bicubic_filter
     */
    @JvmStatic
    fun lightmapsSetBicubicFilter(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(lightmapsSetBicubicFilterBind, singleton, enable)
    }

    /**
     * Sets the filter quality for omni and spot light shadows in 3D. See also
     * `ProjectSettings.rendering/lights_and_shadows/positional_shadow/soft_shadow_filter_quality`.
     * This parameter is global and cannot be set on a per-viewport basis.
     *
     * Generated from Godot docs: RenderingServer.positional_soft_shadow_filter_set_quality
     */
    @JvmStatic
    fun positionalSoftShadowFilterSetQuality(quality: RenderingServer.ShadowQuality) {
        ObjectCalls.ptrcallWithLongArg(positionalSoftShadowFilterSetQualityBind, singleton, quality.value)
    }

    /**
     * Sets the filter `quality` for directional light shadows in 3D. See also
     * `ProjectSettings.rendering/lights_and_shadows/directional_shadow/soft_shadow_filter_quality`.
     * This parameter is global and cannot be set on a per-viewport basis.
     *
     * Generated from Godot docs: RenderingServer.directional_soft_shadow_filter_set_quality
     */
    @JvmStatic
    fun directionalSoftShadowFilterSetQuality(quality: RenderingServer.ShadowQuality) {
        ObjectCalls.ptrcallWithLongArg(directionalSoftShadowFilterSetQualityBind, singleton, quality.value)
    }

    /**
     * Sets the `size` of the directional light shadows in 3D. See also
     * `ProjectSettings.rendering/lights_and_shadows/directional_shadow/size`. This parameter is global
     * and cannot be set on a per-viewport basis.
     *
     * Generated from Godot docs: RenderingServer.directional_shadow_atlas_set_size
     */
    @JvmStatic
    fun directionalShadowAtlasSetSize(size: Int, is16bits: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(directionalShadowAtlasSetSizeBind, singleton, size, is16bits)
    }

    /**
     * Creates a reflection probe and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID will be used in all `reflection_probe_*` RenderingServer functions.
     * Once finished with your RID, you will want to free the RID using the RenderingServer's
     * `free_rid` method. To place in a scene, attach this reflection probe to an instance using
     * `instance_set_base` using the returned RID. Note: The equivalent node is `ReflectionProbe`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_create
     */
    @JvmStatic
    fun reflectionProbeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(reflectionProbeCreateBind, singleton)
    }

    /**
     * Sets how often the reflection probe updates. Can either be once or every frame.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_update_mode
     */
    @JvmStatic
    fun reflectionProbeSetUpdateMode(probe: RID, mode: RenderingServer.ReflectionProbeUpdateMode) {
        ObjectCalls.ptrcallWithRIDAndLongArg(reflectionProbeSetUpdateModeBind, singleton, probe, mode.value)
    }

    /**
     * Sets the intensity of the reflection probe. Intensity modulates the strength of the reflection.
     * Equivalent to `ReflectionProbe.intensity`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_intensity
     */
    @JvmStatic
    fun reflectionProbeSetIntensity(probe: RID, intensity: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(reflectionProbeSetIntensityBind, singleton, probe, intensity)
    }

    /**
     * Sets the distance in meters over which a probe blends into the scene.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_blend_distance
     */
    @JvmStatic
    fun reflectionProbeSetBlendDistance(probe: RID, blendDistance: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(reflectionProbeSetBlendDistanceBind, singleton, probe, blendDistance)
    }

    /**
     * Sets the reflection probe's ambient light mode. Equivalent to `ReflectionProbe.ambient_mode`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_ambient_mode
     */
    @JvmStatic
    fun reflectionProbeSetAmbientMode(probe: RID, mode: RenderingServer.ReflectionProbeAmbientMode) {
        ObjectCalls.ptrcallWithRIDAndLongArg(reflectionProbeSetAmbientModeBind, singleton, probe, mode.value)
    }

    /**
     * Sets the reflection probe's custom ambient light color. Equivalent to
     * `ReflectionProbe.ambient_color`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_ambient_color
     */
    @JvmStatic
    fun reflectionProbeSetAmbientColor(probe: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(reflectionProbeSetAmbientColorBind, singleton, probe, color)
    }

    /**
     * Sets the reflection probe's custom ambient light energy. Equivalent to
     * `ReflectionProbe.ambient_color_energy`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_ambient_energy
     */
    @JvmStatic
    fun reflectionProbeSetAmbientEnergy(probe: RID, energy: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(reflectionProbeSetAmbientEnergyBind, singleton, probe, energy)
    }

    /**
     * Sets the max distance away from the probe an object can be before it is culled. Equivalent to
     * `ReflectionProbe.max_distance`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_max_distance
     */
    @JvmStatic
    fun reflectionProbeSetMaxDistance(probe: RID, distance: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(reflectionProbeSetMaxDistanceBind, singleton, probe, distance)
    }

    /**
     * Sets the size of the area that the reflection probe will capture. Equivalent to
     * `ReflectionProbe.size`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_size
     */
    @JvmStatic
    fun reflectionProbeSetSize(probe: RID, size: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(reflectionProbeSetSizeBind, singleton, probe, size)
    }

    /**
     * Sets the origin offset to be used when this reflection probe is in box project mode. Equivalent
     * to `ReflectionProbe.origin_offset`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_origin_offset
     */
    @JvmStatic
    fun reflectionProbeSetOriginOffset(probe: RID, offset: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(reflectionProbeSetOriginOffsetBind, singleton, probe, offset)
    }

    /**
     * If `true`, reflections will ignore sky contribution. Equivalent to `ReflectionProbe.interior`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_as_interior
     */
    @JvmStatic
    fun reflectionProbeSetAsInterior(probe: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(reflectionProbeSetAsInteriorBind, singleton, probe, enable)
    }

    /**
     * If `true`, uses box projection. This can make reflections look more correct in certain
     * situations. Equivalent to `ReflectionProbe.box_projection`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_enable_box_projection
     */
    @JvmStatic
    fun reflectionProbeSetEnableBoxProjection(probe: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(reflectionProbeSetEnableBoxProjectionBind, singleton, probe, enable)
    }

    /**
     * If `true`, computes shadows in the reflection probe. This makes the reflection much slower to
     * compute. Equivalent to `ReflectionProbe.enable_shadows`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_enable_shadows
     */
    @JvmStatic
    fun reflectionProbeSetEnableShadows(probe: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(reflectionProbeSetEnableShadowsBind, singleton, probe, enable)
    }

    /**
     * Sets the render cull mask for this reflection probe. Only instances with a matching layer will
     * be reflected by this probe. Equivalent to `ReflectionProbe.cull_mask`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_cull_mask
     */
    @JvmStatic
    fun reflectionProbeSetCullMask(probe: RID, layers: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(reflectionProbeSetCullMaskBind, singleton, probe, layers)
    }

    /**
     * Sets the render reflection mask for this reflection probe. Only instances with a matching layer
     * will have reflections applied from this probe. Equivalent to `ReflectionProbe.reflection_mask`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_reflection_mask
     */
    @JvmStatic
    fun reflectionProbeSetReflectionMask(probe: RID, layers: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(reflectionProbeSetReflectionMaskBind, singleton, probe, layers)
    }

    /**
     * Deprecated. This method does nothing.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_resolution
     */
    @JvmStatic
    fun reflectionProbeSetResolution(probe: RID, resolution: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(reflectionProbeSetResolutionBind, singleton, probe, resolution)
    }

    /**
     * Sets the mesh level of detail to use in the reflection probe rendering. Higher values will use
     * less detailed versions of meshes that have LOD variations generated, which can improve
     * performance. Equivalent to `ReflectionProbe.mesh_lod_threshold`.
     *
     * Generated from Godot docs: RenderingServer.reflection_probe_set_mesh_lod_threshold
     */
    @JvmStatic
    fun reflectionProbeSetMeshLodThreshold(probe: RID, pixels: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(reflectionProbeSetMeshLodThresholdBind, singleton, probe, pixels)
    }

    /**
     * Creates a decal and adds it to the RenderingServer. It can be accessed with the RID that is
     * returned. This RID will be used in all `decal_*` RenderingServer functions. Once finished with
     * your RID, you will want to free the RID using the RenderingServer's `free_rid` method. To place
     * in a scene, attach this decal to an instance using `instance_set_base` using the returned RID.
     * Note: The equivalent node is `Decal`.
     *
     * Generated from Godot docs: RenderingServer.decal_create
     */
    @JvmStatic
    fun decalCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(decalCreateBind, singleton)
    }

    /**
     * Sets the `size` of the decal specified by the `decal` RID. Equivalent to `Decal.size`.
     *
     * Generated from Godot docs: RenderingServer.decal_set_size
     */
    @JvmStatic
    fun decalSetSize(decal: RID, size: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(decalSetSizeBind, singleton, decal, size)
    }

    /**
     * Sets the `texture` in the given texture `type` slot for the specified decal. Equivalent to
     * `Decal.set_texture`.
     *
     * Generated from Godot docs: RenderingServer.decal_set_texture
     */
    @JvmStatic
    fun decalSetTexture(decal: RID, type: RenderingServer.DecalTexture, texture: RID) {
        ObjectCalls.ptrcallWithRIDLongAndRIDArgs(decalSetTextureBind, singleton, decal, type.value, texture)
    }

    /**
     * Sets the emission `energy` in the decal specified by the `decal` RID. Equivalent to
     * `Decal.emission_energy`.
     *
     * Generated from Godot docs: RenderingServer.decal_set_emission_energy
     */
    @JvmStatic
    fun decalSetEmissionEnergy(decal: RID, energy: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(decalSetEmissionEnergyBind, singleton, decal, energy)
    }

    /**
     * Sets the `albedo_mix` in the decal specified by the `decal` RID. Equivalent to
     * `Decal.albedo_mix`.
     *
     * Generated from Godot docs: RenderingServer.decal_set_albedo_mix
     */
    @JvmStatic
    fun decalSetAlbedoMix(decal: RID, albedoMix: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(decalSetAlbedoMixBind, singleton, decal, albedoMix)
    }

    /**
     * Sets the color multiplier in the decal specified by the `decal` RID to `color`. Equivalent to
     * `Decal.modulate`.
     *
     * Generated from Godot docs: RenderingServer.decal_set_modulate
     */
    @JvmStatic
    fun decalSetModulate(decal: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(decalSetModulateBind, singleton, decal, color)
    }

    /**
     * Sets the cull `mask` in the decal specified by the `decal` RID. Equivalent to `Decal.cull_mask`.
     *
     * Generated from Godot docs: RenderingServer.decal_set_cull_mask
     */
    @JvmStatic
    fun decalSetCullMask(decal: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(decalSetCullMaskBind, singleton, decal, mask)
    }

    /**
     * Sets the distance fade parameters in the decal specified by the `decal` RID. Equivalent to
     * `Decal.distance_fade_enabled`, `Decal.distance_fade_begin` and `Decal.distance_fade_length`.
     *
     * Generated from Godot docs: RenderingServer.decal_set_distance_fade
     */
    @JvmStatic
    fun decalSetDistanceFade(decal: RID, enabled: Boolean, begin: Double, length: Double) {
        ObjectCalls.ptrcallWithRIDBoolTwoDoubleArgs(decalSetDistanceFadeBind, singleton, decal, enabled, begin, length)
    }

    /**
     * Sets the upper fade (`above`) and lower fade (`below`) in the decal specified by the `decal`
     * RID. Equivalent to `Decal.upper_fade` and `Decal.lower_fade`.
     *
     * Generated from Godot docs: RenderingServer.decal_set_fade
     */
    @JvmStatic
    fun decalSetFade(decal: RID, above: Double, below: Double) {
        ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(decalSetFadeBind, singleton, decal, above, below)
    }

    /**
     * Sets the normal `fade` in the decal specified by the `decal` RID. Equivalent to
     * `Decal.normal_fade`.
     *
     * Generated from Godot docs: RenderingServer.decal_set_normal_fade
     */
    @JvmStatic
    fun decalSetNormalFade(decal: RID, fade: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(decalSetNormalFadeBind, singleton, decal, fade)
    }

    /**
     * Sets the texture `filter` mode to use when rendering decals. This parameter is global and cannot
     * be set on a per-decal basis.
     *
     * Generated from Godot docs: RenderingServer.decals_set_filter
     */
    @JvmStatic
    fun decalsSetFilter(filter: RenderingServer.DecalFilter) {
        ObjectCalls.ptrcallWithLongArg(decalsSetFilterBind, singleton, filter.value)
    }

    /**
     * If `half_resolution` is `true`, renders `VoxelGI` and SDFGI (`Environment.sdfgi_enabled`)
     * buffers at halved resolution on each axis (e.g. 960×540 when the viewport size is 1920×1080).
     * This improves performance significantly when VoxelGI or SDFGI is enabled, at the cost of
     * artifacts that may be visible on polygon edges. The loss in quality becomes less noticeable as
     * the viewport resolution increases. `LightmapGI` rendering is not affected by this setting.
     * Equivalent to `ProjectSettings.rendering/global_illumination/gi/use_half_resolution`.
     *
     * Generated from Godot docs: RenderingServer.gi_set_use_half_resolution
     */
    @JvmStatic
    fun giSetUseHalfResolution(halfResolution: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(giSetUseHalfResolutionBind, singleton, halfResolution)
    }

    /**
     * Creates a new voxel-based global illumination object and adds it to the RenderingServer. It can
     * be accessed with the RID that is returned. This RID will be used in all `voxel_gi_*`
     * RenderingServer functions. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method. Note: The equivalent node is `VoxelGI`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_create
     */
    @JvmStatic
    fun voxelGiCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(voxelGiCreateBind, singleton)
    }

    /**
     * Allocates and initializes the voxel GI data for the specified `voxel_gi` RID. `octree_cells`
     * must be a multiple of 32. `octree_cells` must be double the size of `data_cells`. The allocated
     * data can be retrieved later using the various `voxel_gi_get_*` methods.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_allocate_data
     */
    @JvmStatic
    fun voxelGiAllocateData(voxelGi: RID, toCellXform: Transform3D, aabb: AABB, octreeSize: Vector3i, octreeCells: ByteArray, dataCells: ByteArray, distanceField: ByteArray, levelCounts: List<Int>) {
        ObjectCalls.ptrcallWithRIDTransform3DAABBVector3iThreeByteArrayPackedInt32ListArgs(voxelGiAllocateDataBind, singleton, voxelGi, toCellXform, aabb, octreeSize, octreeCells, dataCells, distanceField, levelCounts)
    }

    /**
     * Returns the octree size for the specified voxel GI data instance, which corresponds to the
     * number of subdivisions per axis. This can be viewed in the editor by hovering the Bake VoxelGI
     * button at the top of the 3D editor viewport when a `VoxelGI` node is selected and looking at the
     * Subdivisions field in the tooltip.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_get_octree_size
     */
    @JvmStatic
    fun voxelGiGetOctreeSize(voxelGi: RID): Vector3i {
        return ObjectCalls.ptrcallWithRIDArgRetVector3i(voxelGiGetOctreeSizeBind, singleton, voxelGi)
    }

    /**
     * Returns the octree cell data for the specified voxel GI data instance. See also
     * `voxel_gi_allocate_data`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_get_octree_cells
     */
    @JvmStatic
    fun voxelGiGetOctreeCells(voxelGi: RID): ByteArray {
        return ObjectCalls.ptrcallWithRIDArgRetByteArray(voxelGiGetOctreeCellsBind, singleton, voxelGi)
    }

    /**
     * Returns the data cells for the specified voxel GI data instance. See also
     * `voxel_gi_allocate_data`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_get_data_cells
     */
    @JvmStatic
    fun voxelGiGetDataCells(voxelGi: RID): ByteArray {
        return ObjectCalls.ptrcallWithRIDArgRetByteArray(voxelGiGetDataCellsBind, singleton, voxelGi)
    }

    /**
     * Returns the distance field data for the specified voxel GI data instance. See also
     * `voxel_gi_allocate_data`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_get_distance_field
     */
    @JvmStatic
    fun voxelGiGetDistanceField(voxelGi: RID): ByteArray {
        return ObjectCalls.ptrcallWithRIDArgRetByteArray(voxelGiGetDistanceFieldBind, singleton, voxelGi)
    }

    /**
     * Returns the level counts for the specified voxel GI data instance. See also
     * `voxel_gi_allocate_data`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_get_level_counts
     */
    @JvmStatic
    fun voxelGiGetLevelCounts(voxelGi: RID): List<Int> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedInt32List(voxelGiGetLevelCountsBind, singleton, voxelGi)
    }

    /**
     * Returns the transform to cell space for the specified voxel GI data instance. See also
     * `voxel_gi_allocate_data`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_get_to_cell_xform
     */
    @JvmStatic
    fun voxelGiGetToCellXform(voxelGi: RID): Transform3D {
        return ObjectCalls.ptrcallWithRIDArgRetTransform3D(voxelGiGetToCellXformBind, singleton, voxelGi)
    }

    /**
     * Sets the `VoxelGIData.dynamic_range` value to use on the specified `voxel_gi`'s `RID`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_set_dynamic_range
     */
    @JvmStatic
    fun voxelGiSetDynamicRange(voxelGi: RID, range: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(voxelGiSetDynamicRangeBind, singleton, voxelGi, range)
    }

    /**
     * Sets the `VoxelGIData.propagation` value to use on the specified `voxel_gi`'s `RID`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_set_propagation
     */
    @JvmStatic
    fun voxelGiSetPropagation(voxelGi: RID, amount: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(voxelGiSetPropagationBind, singleton, voxelGi, amount)
    }

    /**
     * Sets the `VoxelGIData.energy` value to use on the specified `voxel_gi`'s `RID`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_set_energy
     */
    @JvmStatic
    fun voxelGiSetEnergy(voxelGi: RID, energy: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(voxelGiSetEnergyBind, singleton, voxelGi, energy)
    }

    /**
     * Used to inform the renderer what exposure normalization value was used while baking the voxel
     * gi. This value will be used and modulated at run time to ensure that the voxel gi maintains a
     * consistent level of exposure even if the scene-wide exposure normalization is changed at run
     * time. For more information see `camera_attributes_set_exposure`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_set_baked_exposure_normalization
     */
    @JvmStatic
    fun voxelGiSetBakedExposureNormalization(voxelGi: RID, bakedExposure: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(voxelGiSetBakedExposureNormalizationBind, singleton, voxelGi, bakedExposure)
    }

    /**
     * Sets the `VoxelGIData.bias` value to use on the specified `voxel_gi`'s `RID`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_set_bias
     */
    @JvmStatic
    fun voxelGiSetBias(voxelGi: RID, bias: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(voxelGiSetBiasBind, singleton, voxelGi, bias)
    }

    /**
     * Sets the `VoxelGIData.normal_bias` value to use on the specified `voxel_gi`'s `RID`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_set_normal_bias
     */
    @JvmStatic
    fun voxelGiSetNormalBias(voxelGi: RID, bias: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(voxelGiSetNormalBiasBind, singleton, voxelGi, bias)
    }

    /**
     * Sets the `VoxelGIData.interior` value to use on the specified `voxel_gi`'s `RID`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_set_interior
     */
    @JvmStatic
    fun voxelGiSetInterior(voxelGi: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(voxelGiSetInteriorBind, singleton, voxelGi, enable)
    }

    /**
     * Sets the `VoxelGIData.use_two_bounces` value to use on the specified `voxel_gi`'s `RID`.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_set_use_two_bounces
     */
    @JvmStatic
    fun voxelGiSetUseTwoBounces(voxelGi: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(voxelGiSetUseTwoBouncesBind, singleton, voxelGi, enable)
    }

    /**
     * Sets the `ProjectSettings.rendering/global_illumination/voxel_gi/quality` value to use when
     * rendering. This parameter is global and cannot be set on a per-VoxelGI basis.
     *
     * Generated from Godot docs: RenderingServer.voxel_gi_set_quality
     */
    @JvmStatic
    fun voxelGiSetQuality(quality: RenderingServer.VoxelGIQuality) {
        ObjectCalls.ptrcallWithLongArg(voxelGiSetQualityBind, singleton, quality.value)
    }

    /**
     * Creates a new lightmap global illumination instance and adds it to the RenderingServer. It can
     * be accessed with the RID that is returned. This RID will be used in all `lightmap_*`
     * RenderingServer functions. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method. Note: The equivalent node is `LightmapGI`.
     *
     * Generated from Godot docs: RenderingServer.lightmap_create
     */
    @JvmStatic
    fun lightmapCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(lightmapCreateBind, singleton)
    }

    /**
     * Set the textures on the given `lightmap` GI instance to the texture array pointed to by the
     * `light` RID. If the lightmap texture was baked with `LightmapGI.directional` set to `true`, then
     * `uses_sh` must also be `true`.
     *
     * Generated from Godot docs: RenderingServer.lightmap_set_textures
     */
    @JvmStatic
    fun lightmapSetTextures(lightmap: RID, light: RID, usesSh: Boolean) {
        ObjectCalls.ptrcallWithTwoRIDBoolArgs(lightmapSetTexturesBind, singleton, lightmap, light, usesSh)
    }

    /**
     * Sets the bounds that this lightmap instance should visually affect, both in terms of static
     * lightmap baking and probe-based global illumination.
     *
     * Generated from Godot docs: RenderingServer.lightmap_set_probe_bounds
     */
    @JvmStatic
    fun lightmapSetProbeBounds(lightmap: RID, bounds: AABB) {
        ObjectCalls.ptrcallWithRIDAndAABBArg(lightmapSetProbeBoundsBind, singleton, lightmap, bounds)
    }

    /**
     * Sets whether the lightmap instance should be considered as interior (when `interior` is `true`).
     * If the lightmap is marked as interior, environment lighting is ignored when baking lightmaps.
     *
     * Generated from Godot docs: RenderingServer.lightmap_set_probe_interior
     */
    @JvmStatic
    fun lightmapSetProbeInterior(lightmap: RID, interior: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(lightmapSetProbeInteriorBind, singleton, lightmap, interior)
    }

    /**
     * Sets the probe capture data for the given lightmap instance. See
     * `lightmap_get_probe_capture_points`, `lightmap_get_probe_capture_sh`,
     * `lightmap_get_probe_capture_tetrahedra`, and `lightmap_get_probe_capture_bsp_tree` for the
     * expected data formats.
     *
     * Generated from Godot docs: RenderingServer.lightmap_set_probe_capture_data
     */
    @JvmStatic
    fun lightmapSetProbeCaptureData(lightmap: RID, points: List<Vector3>, pointSh: List<Color>, tetrahedra: List<Int>, bspTree: List<Int>) {
        ObjectCalls.ptrcallWithRIDPackedVector3ListPackedColorListTwoPackedInt32ListArgs(lightmapSetProbeCaptureDataBind, singleton, lightmap, points, pointSh, tetrahedra, bspTree)
    }

    /**
     * Returns the local space positions of each lightmap probe capture point. Keep in mind the
     * lightmap instance may have a non-zero transform, which will affect the position of the probe
     * capture points. See also `lightmap_set_probe_capture_data`.
     *
     * Generated from Godot docs: RenderingServer.lightmap_get_probe_capture_points
     */
    @JvmStatic
    fun lightmapGetProbeCapturePoints(lightmap: RID): List<Vector3> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedVector3List(lightmapGetProbeCapturePointsBind, singleton, lightmap)
    }

    /**
     * Returns the L0, L1, and L2 spherical harmonics
     * (https://en.wikipedia.org/wiki/Spherical_harmonics) data for each lightmap probe capture point.
     * This is specified as 9 `Color` values per probe, which means the size of the returned data is
     * always 9 times the number of probe points. See also `lightmap_set_probe_capture_data`.
     *
     * Generated from Godot docs: RenderingServer.lightmap_get_probe_capture_sh
     */
    @JvmStatic
    fun lightmapGetProbeCaptureSh(lightmap: RID): List<Color> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedColorList(lightmapGetProbeCaptureShBind, singleton, lightmap)
    }

    /**
     * Returns the tetrahedralization data used for interpolating between lightmap probe capture
     * points. Each tetrahedron is specified as a series of 4 numbers, each being an index into the
     * probe capture points array returned by `lightmap_get_probe_capture_points`. See also
     * `lightmap_set_probe_capture_data`.
     *
     * Generated from Godot docs: RenderingServer.lightmap_get_probe_capture_tetrahedra
     */
    @JvmStatic
    fun lightmapGetProbeCaptureTetrahedra(lightmap: RID): List<Int> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedInt32List(lightmapGetProbeCaptureTetrahedraBind, singleton, lightmap)
    }

    /**
     * Returns the BSP tree data used for accelerating probe lookups. The BSP data is structured as a
     * series of six signed 32-bit values per BSP node in this order: `float plane_x`, `float plane_y`,
     * `float plane_z`, `float plane_distance`, `int32_t over`, `int32_t under`. An empty leaf is
     * denoted by the value `-2147483648` (the minimum 32-bit signed integer). See also
     * `lightmap_set_probe_capture_data`.
     *
     * Generated from Godot docs: RenderingServer.lightmap_get_probe_capture_bsp_tree
     */
    @JvmStatic
    fun lightmapGetProbeCaptureBspTree(lightmap: RID): List<Int> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedInt32List(lightmapGetProbeCaptureBspTreeBind, singleton, lightmap)
    }

    /**
     * Used to inform the renderer what exposure normalization value was used while baking the
     * lightmap. This value will be used and modulated at run time to ensure that the lightmap
     * maintains a consistent level of exposure even if the scene-wide exposure normalization is
     * changed at run time. For more information see `camera_attributes_set_exposure`.
     *
     * Generated from Godot docs: RenderingServer.lightmap_set_baked_exposure_normalization
     */
    @JvmStatic
    fun lightmapSetBakedExposureNormalization(lightmap: RID, bakedExposure: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(lightmapSetBakedExposureNormalizationBind, singleton, lightmap, bakedExposure)
    }

    /**
     * The framerate-independent update speed when representing dynamic object lighting from
     * `LightmapProbe`s. Higher values make dynamic object lighting update faster. Higher values can
     * prevent fast-moving objects from having "outdated" indirect lighting displayed on them, at the
     * cost of possible flickering when an object moves from a bright area to a shaded area. See also
     * `ProjectSettings.rendering/lightmapping/probe_capture/update_speed`.
     *
     * Generated from Godot docs: RenderingServer.lightmap_set_probe_capture_update_speed
     */
    @JvmStatic
    fun lightmapSetProbeCaptureUpdateSpeed(speed: Double) {
        ObjectCalls.ptrcallWithDoubleArg(lightmapSetProbeCaptureUpdateSpeedBind, singleton, speed)
    }

    /**
     * Creates a GPU-based particle system and adds it to the RenderingServer. It can be accessed with
     * the RID that is returned. This RID will be used in all `particles_*` RenderingServer functions.
     * Once finished with your RID, you will want to free the RID using the RenderingServer's
     * `free_rid` method. To place in a scene, attach these particles to an instance using
     * `instance_set_base` using the returned RID. Note: The equivalent nodes are `GPUParticles2D` and
     * `GPUParticles3D`. Note: All `particles_*` methods only apply to GPU-based particles, not
     * CPU-based particles. `CPUParticles2D` and `CPUParticles3D` do not have equivalent
     * RenderingServer functions available, as these use `MultiMeshInstance2D` and
     * `MultiMeshInstance3D` under the hood (see `multimesh_*` methods).
     *
     * Generated from Godot docs: RenderingServer.particles_create
     */
    @JvmStatic
    fun particlesCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(particlesCreateBind, singleton)
    }

    /**
     * Sets whether the GPU particles specified by the `particles` RID should be rendered in 2D or 3D
     * according to `mode`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_mode
     */
    @JvmStatic
    fun particlesSetMode(particles: RID, mode: RenderingServer.ParticlesMode) {
        ObjectCalls.ptrcallWithRIDAndLongArg(particlesSetModeBind, singleton, particles, mode.value)
    }

    /**
     * If `true`, particles will emit over time. Setting to `false` does not reset the particles, but
     * only stops their emission. Equivalent to `GPUParticles3D.emitting`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_emitting
     */
    @JvmStatic
    fun particlesSetEmitting(particles: RID, emitting: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(particlesSetEmittingBind, singleton, particles, emitting)
    }

    /**
     * Returns `true` if particles are currently set to emitting.
     *
     * Generated from Godot docs: RenderingServer.particles_get_emitting
     */
    @JvmStatic
    fun particlesGetEmitting(particles: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(particlesGetEmittingBind, singleton, particles)
    }

    /**
     * Sets the number of particles to be drawn and allocates the memory for them. Equivalent to
     * `GPUParticles3D.amount`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_amount
     */
    @JvmStatic
    fun particlesSetAmount(particles: RID, amount: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(particlesSetAmountBind, singleton, particles, amount)
    }

    /**
     * Sets the amount ratio for particles to be emitted. Equivalent to `GPUParticles3D.amount_ratio`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_amount_ratio
     */
    @JvmStatic
    fun particlesSetAmountRatio(particles: RID, ratio: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetAmountRatioBind, singleton, particles, ratio)
    }

    /**
     * Sets the lifetime of each particle in the system. Equivalent to `GPUParticles3D.lifetime`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_lifetime
     */
    @JvmStatic
    fun particlesSetLifetime(particles: RID, lifetime: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetLifetimeBind, singleton, particles, lifetime)
    }

    /**
     * If `true`, particles will emit once and then stop. Equivalent to `GPUParticles3D.one_shot`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_one_shot
     */
    @JvmStatic
    fun particlesSetOneShot(particles: RID, oneShot: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(particlesSetOneShotBind, singleton, particles, oneShot)
    }

    /**
     * Sets the preprocess time for the particles' animation. This lets you delay starting an animation
     * until after the particles have begun emitting. Equivalent to `GPUParticles3D.preprocess`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_pre_process_time
     */
    @JvmStatic
    fun particlesSetPreProcessTime(particles: RID, time: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetPreProcessTimeBind, singleton, particles, time)
    }

    /**
     * Requests the particles to process for extra process time during a single frame. `process_time`
     * defines the time that the particles will process while emitting is on. `process_time_residual`
     * defines the time that particles will process with emitting turned off for the simulation. When
     * combined with the particles' speed scale set to `0.0`, this is useful to be able to seek a
     * particle system timeline.
     *
     * Generated from Godot docs: RenderingServer.particles_request_process_time
     */
    @JvmStatic
    fun particlesRequestProcessTime(particles: RID, processTime: Double, processTimeResidual: Double = 0.0) {
        ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(particlesRequestProcessTimeBind, singleton, particles, processTime, processTimeResidual)
    }

    /**
     * Sets the explosiveness ratio. Equivalent to `GPUParticles3D.explosiveness`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_explosiveness_ratio
     */
    @JvmStatic
    fun particlesSetExplosivenessRatio(particles: RID, ratio: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetExplosivenessRatioBind, singleton, particles, ratio)
    }

    /**
     * Sets the emission randomness ratio. This randomizes the emission of particles within their
     * phase. Equivalent to `GPUParticles3D.randomness`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_randomness_ratio
     */
    @JvmStatic
    fun particlesSetRandomnessRatio(particles: RID, ratio: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetRandomnessRatioBind, singleton, particles, ratio)
    }

    /**
     * Sets the value that informs a `ParticleProcessMaterial` to rush all particles towards the end of
     * their lifetime.
     *
     * Generated from Godot docs: RenderingServer.particles_set_interp_to_end
     */
    @JvmStatic
    fun particlesSetInterpToEnd(particles: RID, factor: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetInterpToEndBind, singleton, particles, factor)
    }

    /**
     * Sets the velocity of a particle node, that will be used by
     * `ParticleProcessMaterial.inherit_velocity_ratio`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_emitter_velocity
     */
    @JvmStatic
    fun particlesSetEmitterVelocity(particles: RID, velocity: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(particlesSetEmitterVelocityBind, singleton, particles, velocity)
    }

    /**
     * Sets a custom axis-aligned bounding box for the particle system. Equivalent to
     * `GPUParticles3D.visibility_aabb`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_custom_aabb
     */
    @JvmStatic
    fun particlesSetCustomAabb(particles: RID, aabb: AABB) {
        ObjectCalls.ptrcallWithRIDAndAABBArg(particlesSetCustomAabbBind, singleton, particles, aabb)
    }

    /**
     * Sets the speed scale of the particle system. Equivalent to `GPUParticles3D.speed_scale`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_speed_scale
     */
    @JvmStatic
    fun particlesSetSpeedScale(particles: RID, scale: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetSpeedScaleBind, singleton, particles, scale)
    }

    /**
     * If `true`, particles use local coordinates. If `false` they use global coordinates. Equivalent
     * to `GPUParticles3D.local_coords`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_use_local_coordinates
     */
    @JvmStatic
    fun particlesSetUseLocalCoordinates(particles: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(particlesSetUseLocalCoordinatesBind, singleton, particles, enable)
    }

    /**
     * Sets the material for processing the particles. Note: This is not the material used to draw the
     * materials. Equivalent to `GPUParticles3D.process_material`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_process_material
     */
    @JvmStatic
    fun particlesSetProcessMaterial(particles: RID, material: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(particlesSetProcessMaterialBind, singleton, particles, material)
    }

    /**
     * Sets the frame rate that the particle system rendering will be fixed to. Equivalent to
     * `GPUParticles3D.fixed_fps`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_fixed_fps
     */
    @JvmStatic
    fun particlesSetFixedFps(particles: RID, fps: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(particlesSetFixedFpsBind, singleton, particles, fps)
    }

    /**
     * Sets whether particles should use interpolation between fixed steps. Equivalent to
     * `GPUParticles3D.interpolate`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_interpolate
     */
    @JvmStatic
    fun particlesSetInterpolate(particles: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(particlesSetInterpolateBind, singleton, particles, enable)
    }

    /**
     * If `true`, uses fractional delta which smooths the movement of the particles. Equivalent to
     * `GPUParticles3D.fract_delta`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_fractional_delta
     */
    @JvmStatic
    fun particlesSetFractionalDelta(particles: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(particlesSetFractionalDeltaBind, singleton, particles, enable)
    }

    /**
     * Sets the base size for particle collision. Equivalent to `GPUParticles3D.collision_base_size`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_collision_base_size
     */
    @JvmStatic
    fun particlesSetCollisionBaseSize(particles: RID, size: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesSetCollisionBaseSizeBind, singleton, particles, size)
    }

    /**
     * Sets the transform alignment for the particle system. Equivalent to
     * `GPUParticles3D.transform_align`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_transform_align
     */
    @JvmStatic
    fun particlesSetTransformAlign(particles: RID, align: RenderingServer.ParticlesTransformAlign) {
        ObjectCalls.ptrcallWithRIDAndLongArg(particlesSetTransformAlignBind, singleton, particles, align.value)
    }

    /**
     * When using Z-Billboarding, which CUSTOM channel to read from.
     *
     * Generated from Godot docs: RenderingServer.particles_set_transform_align_channel_filter
     */
    @JvmStatic
    fun particlesSetTransformAlignChannelFilter(particles: RID, channelFilter: RenderingServer.ParticlesTransformAlignCustomSrc) {
        ObjectCalls.ptrcallWithRIDAndLongArg(particlesSetTransformAlignChannelFilterBind, singleton, particles, channelFilter.value)
    }

    /**
     * Sets which axis to use for transform alignment.
     *
     * Generated from Godot docs: RenderingServer.particles_set_transform_align_axis
     */
    @JvmStatic
    fun particlesSetTransformAlignAxis(particles: RID, rotationAxis: RenderingServer.ParticlesTransformAlignAxis) {
        ObjectCalls.ptrcallWithRIDAndLongArg(particlesSetTransformAlignAxisBind, singleton, particles, rotationAxis.value)
    }

    /**
     * If `enable` is `true`, enables trails for the `particles` with the specified `length_sec` in
     * seconds. Equivalent to `GPUParticles3D.trail_enabled` and `GPUParticles3D.trail_lifetime`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_trails
     */
    @JvmStatic
    fun particlesSetTrails(particles: RID, enable: Boolean, lengthSec: Double) {
        ObjectCalls.ptrcallWithRIDBoolDoubleArgs(particlesSetTrailsBind, singleton, particles, enable, lengthSec)
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
    @JvmStatic
    fun particlesSetTrailBindPoses(particles: RID, bindPoses: List<Transform3D>) {
        ObjectCalls.ptrcallWithRIDAndTransform3DListArgs(particlesSetTrailBindPosesBind, singleton, particles, bindPoses)
    }

    /**
     * Returns `true` if particles are not emitting and particles are set to inactive.
     *
     * Generated from Godot docs: RenderingServer.particles_is_inactive
     */
    @JvmStatic
    fun particlesIsInactive(particles: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(particlesIsInactiveBind, singleton, particles)
    }

    /**
     * Add particle system to list of particle systems that need to be updated. Update will take place
     * on the next frame, or on the next call to `instances_cull_aabb`, `instances_cull_convex`, or
     * `instances_cull_ray`.
     *
     * Generated from Godot docs: RenderingServer.particles_request_process
     */
    @JvmStatic
    fun particlesRequestProcess(particles: RID) {
        ObjectCalls.ptrcallWithRIDArg(particlesRequestProcessBind, singleton, particles)
    }

    /**
     * Reset the particles on the next update. Equivalent to `GPUParticles3D.restart`.
     *
     * Generated from Godot docs: RenderingServer.particles_restart
     */
    @JvmStatic
    fun particlesRestart(particles: RID) {
        ObjectCalls.ptrcallWithRIDArg(particlesRestartBind, singleton, particles)
    }

    /**
     * Sets the subemitter particles for the particle system. Equivalent to
     * `GPUParticles3D.sub_emitter`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_subemitter
     */
    @JvmStatic
    fun particlesSetSubemitter(particles: RID, subemitterParticles: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(particlesSetSubemitterBind, singleton, particles, subemitterParticles)
    }

    /**
     * Manually emits particles from the `particles` instance.
     *
     * Generated from Godot docs: RenderingServer.particles_emit
     */
    @JvmStatic
    fun particlesEmit(particles: RID, transform: Transform3D, velocity: Vector3, color: Color, custom: Color, emitFlags: Long) {
        ObjectCalls.ptrcallWithRIDTransform3DVector3TwoColorUInt32Args(particlesEmitBind, singleton, particles, transform, velocity, color, custom, emitFlags)
    }

    /**
     * Sets the draw order of the particles. Equivalent to `GPUParticles3D.draw_order`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_draw_order
     */
    @JvmStatic
    fun particlesSetDrawOrder(particles: RID, order: RenderingServer.ParticlesDrawOrder) {
        ObjectCalls.ptrcallWithRIDAndLongArg(particlesSetDrawOrderBind, singleton, particles, order.value)
    }

    /**
     * Sets the number of draw passes to use. Equivalent to `GPUParticles3D.draw_passes`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_draw_passes
     */
    @JvmStatic
    fun particlesSetDrawPasses(particles: RID, count: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(particlesSetDrawPassesBind, singleton, particles, count)
    }

    /**
     * Sets the mesh to be used for the specified draw pass. Equivalent to
     * `GPUParticles3D.draw_pass_1`, `GPUParticles3D.draw_pass_2`, `GPUParticles3D.draw_pass_3`, and
     * `GPUParticles3D.draw_pass_4`.
     *
     * Generated from Godot docs: RenderingServer.particles_set_draw_pass_mesh
     */
    @JvmStatic
    fun particlesSetDrawPassMesh(particles: RID, pass: Int, mesh: RID) {
        ObjectCalls.ptrcallWithRIDIntAndRIDArgs(particlesSetDrawPassMeshBind, singleton, particles, pass, mesh)
    }

    /**
     * Calculates and returns the axis-aligned bounding box that contains all the particles. Equivalent
     * to `GPUParticles3D.capture_aabb`.
     *
     * Generated from Godot docs: RenderingServer.particles_get_current_aabb
     */
    @JvmStatic
    fun particlesGetCurrentAabb(particles: RID): AABB {
        return ObjectCalls.ptrcallWithRIDArgRetAABB(particlesGetCurrentAabbBind, singleton, particles)
    }

    /**
     * Sets the `Transform3D` that will be used by the particles when they first emit.
     *
     * Generated from Godot docs: RenderingServer.particles_set_emission_transform
     */
    @JvmStatic
    fun particlesSetEmissionTransform(particles: RID, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDAndTransform3DArg(particlesSetEmissionTransformBind, singleton, particles, transform)
    }

    /**
     * Creates a new 3D GPU particle collision or attractor and adds it to the RenderingServer. It can
     * be accessed with the RID that is returned. This RID can be used in most `particles_collision_*`
     * RenderingServer functions. Note: The equivalent nodes are `GPUParticlesCollision3D` and
     * `GPUParticlesAttractor3D`.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_create
     */
    @JvmStatic
    fun particlesCollisionCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(particlesCollisionCreateBind, singleton)
    }

    /**
     * Sets the collision or attractor shape `type` for the 3D GPU particles collision or attractor
     * specified by the `particles_collision` RID.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_set_collision_type
     */
    @JvmStatic
    fun particlesCollisionSetCollisionType(particlesCollision: RID, type: RenderingServer.ParticlesCollisionType) {
        ObjectCalls.ptrcallWithRIDAndLongArg(particlesCollisionSetCollisionTypeBind, singleton, particlesCollision, type.value)
    }

    /**
     * Sets the cull `mask` for the 3D GPU particles collision or attractor specified by the
     * `particles_collision` RID. Equivalent to `GPUParticlesCollision3D.cull_mask` or
     * `GPUParticlesAttractor3D.cull_mask` depending on the `particles_collision` type.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_set_cull_mask
     */
    @JvmStatic
    fun particlesCollisionSetCullMask(particlesCollision: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(particlesCollisionSetCullMaskBind, singleton, particlesCollision, mask)
    }

    /**
     * Sets the `radius` for the 3D GPU particles sphere collision or attractor specified by the
     * `particles_collision` RID. Equivalent to `GPUParticlesCollisionSphere3D.radius` or
     * `GPUParticlesAttractorSphere3D.radius` depending on the `particles_collision` type.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_set_sphere_radius
     */
    @JvmStatic
    fun particlesCollisionSetSphereRadius(particlesCollision: RID, radius: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesCollisionSetSphereRadiusBind, singleton, particlesCollision, radius)
    }

    /**
     * Sets the `extents` for the 3D GPU particles collision by the `particles_collision` RID.
     * Equivalent to `GPUParticlesCollisionBox3D.size`, `GPUParticlesCollisionSDF3D.size`,
     * `GPUParticlesCollisionHeightField3D.size`, `GPUParticlesAttractorBox3D.size` or
     * `GPUParticlesAttractorVectorField3D.size` depending on the `particles_collision` type.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_set_box_extents
     */
    @JvmStatic
    fun particlesCollisionSetBoxExtents(particlesCollision: RID, extents: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(particlesCollisionSetBoxExtentsBind, singleton, particlesCollision, extents)
    }

    /**
     * Sets the `strength` for the 3D GPU particles attractor specified by the `particles_collision`
     * RID. Only used for attractors, not colliders. Equivalent to `GPUParticlesAttractor3D.strength`.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_set_attractor_strength
     */
    @JvmStatic
    fun particlesCollisionSetAttractorStrength(particlesCollision: RID, strength: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesCollisionSetAttractorStrengthBind, singleton, particlesCollision, strength)
    }

    /**
     * Sets the directionality `amount` for the 3D GPU particles attractor specified by the
     * `particles_collision` RID. Only used for attractors, not colliders. Equivalent to
     * `GPUParticlesAttractor3D.directionality`.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_set_attractor_directionality
     */
    @JvmStatic
    fun particlesCollisionSetAttractorDirectionality(particlesCollision: RID, amount: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesCollisionSetAttractorDirectionalityBind, singleton, particlesCollision, amount)
    }

    /**
     * Sets the attenuation `curve` for the 3D GPU particles attractor specified by the
     * `particles_collision` RID. Only used for attractors, not colliders. Equivalent to
     * `GPUParticlesAttractor3D.attenuation`.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_set_attractor_attenuation
     */
    @JvmStatic
    fun particlesCollisionSetAttractorAttenuation(particlesCollision: RID, curve: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(particlesCollisionSetAttractorAttenuationBind, singleton, particlesCollision, curve)
    }

    /**
     * Sets the signed distance field `texture` for the 3D GPU particles collision specified by the
     * `particles_collision` RID. Equivalent to `GPUParticlesCollisionSDF3D.texture` or
     * `GPUParticlesAttractorVectorField3D.texture` depending on the `particles_collision` type.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_set_field_texture
     */
    @JvmStatic
    fun particlesCollisionSetFieldTexture(particlesCollision: RID, texture: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(particlesCollisionSetFieldTextureBind, singleton, particlesCollision, texture)
    }

    /**
     * Requests an update for the 3D GPU particle collision heightfield. This may be automatically
     * called by the 3D GPU particle collision heightfield depending on its
     * `GPUParticlesCollisionHeightField3D.update_mode`.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_height_field_update
     */
    @JvmStatic
    fun particlesCollisionHeightFieldUpdate(particlesCollision: RID) {
        ObjectCalls.ptrcallWithRIDArg(particlesCollisionHeightFieldUpdateBind, singleton, particlesCollision)
    }

    /**
     * Sets the heightmap `resolution` for the 3D GPU particles heightfield collision specified by the
     * `particles_collision` RID. Equivalent to `GPUParticlesCollisionHeightField3D.resolution`.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_set_height_field_resolution
     */
    @JvmStatic
    fun particlesCollisionSetHeightFieldResolution(particlesCollision: RID, resolution: RenderingServer.ParticlesCollisionHeightfieldResolution) {
        ObjectCalls.ptrcallWithRIDAndLongArg(particlesCollisionSetHeightFieldResolutionBind, singleton, particlesCollision, resolution.value)
    }

    /**
     * Sets the heightfield `mask` for the 3D GPU particles heightfield collision specified by the
     * `particles_collision` RID. Equivalent to `GPUParticlesCollisionHeightField3D.heightfield_mask`.
     *
     * Generated from Godot docs: RenderingServer.particles_collision_set_height_field_mask
     */
    @JvmStatic
    fun particlesCollisionSetHeightFieldMask(particlesCollision: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(particlesCollisionSetHeightFieldMaskBind, singleton, particlesCollision, mask)
    }

    /**
     * Creates a new fog volume and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID will be used in all `fog_volume_*` RenderingServer functions. Once
     * finished with your RID, you will want to free the RID using the RenderingServer's `free_rid`
     * method. Note: The equivalent node is `FogVolume`.
     *
     * Generated from Godot docs: RenderingServer.fog_volume_create
     */
    @JvmStatic
    fun fogVolumeCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(fogVolumeCreateBind, singleton)
    }

    /**
     * Sets the shape of the fog volume to either `RenderingServer.FOG_VOLUME_SHAPE_ELLIPSOID`,
     * `RenderingServer.FOG_VOLUME_SHAPE_CONE`, `RenderingServer.FOG_VOLUME_SHAPE_CYLINDER`,
     * `RenderingServer.FOG_VOLUME_SHAPE_BOX` or `RenderingServer.FOG_VOLUME_SHAPE_WORLD`.
     *
     * Generated from Godot docs: RenderingServer.fog_volume_set_shape
     */
    @JvmStatic
    fun fogVolumeSetShape(fogVolume: RID, shape: RenderingServer.FogVolumeShape) {
        ObjectCalls.ptrcallWithRIDAndLongArg(fogVolumeSetShapeBind, singleton, fogVolume, shape.value)
    }

    /**
     * Sets the size of the fog volume when shape is `RenderingServer.FOG_VOLUME_SHAPE_ELLIPSOID`,
     * `RenderingServer.FOG_VOLUME_SHAPE_CONE`, `RenderingServer.FOG_VOLUME_SHAPE_CYLINDER` or
     * `RenderingServer.FOG_VOLUME_SHAPE_BOX`.
     *
     * Generated from Godot docs: RenderingServer.fog_volume_set_size
     */
    @JvmStatic
    fun fogVolumeSetSize(fogVolume: RID, size: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(fogVolumeSetSizeBind, singleton, fogVolume, size)
    }

    /**
     * Sets the `Material` of the fog volume. Can be either a `FogMaterial` or a custom
     * `ShaderMaterial`.
     *
     * Generated from Godot docs: RenderingServer.fog_volume_set_material
     */
    @JvmStatic
    fun fogVolumeSetMaterial(fogVolume: RID, material: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(fogVolumeSetMaterialBind, singleton, fogVolume, material)
    }

    /**
     * Creates a new 3D visibility notifier object and adds it to the RenderingServer. It can be
     * accessed with the RID that is returned. This RID will be used in all `visibility_notifier_*`
     * RenderingServer functions. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method. To place in a scene, attach this notifier to an instance
     * using `instance_set_base` using the returned RID. Note: The equivalent node is
     * `VisibleOnScreenNotifier3D`.
     *
     * Generated from Godot docs: RenderingServer.visibility_notifier_create
     */
    @JvmStatic
    fun visibilityNotifierCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(visibilityNotifierCreateBind, singleton)
    }

    /**
     * Sets the AABB of the specified visibility notifier.
     *
     * Generated from Godot docs: RenderingServer.visibility_notifier_set_aabb
     */
    @JvmStatic
    fun visibilityNotifierSetAabb(notifier: RID, aabb: AABB) {
        ObjectCalls.ptrcallWithRIDAndAABBArg(visibilityNotifierSetAabbBind, singleton, notifier, aabb)
    }

    /**
     * Sets the methods to be called when the notifier enters or exits the view.
     *
     * Generated from Godot docs: RenderingServer.visibility_notifier_set_callbacks
     */
    @JvmStatic
    fun visibilityNotifierSetCallbacks(notifier: RID, enterCallable: GodotCallable, exitCallable: GodotCallable) {
        ObjectCalls.ptrcallWithRIDTwoCallableArgs(visibilityNotifierSetCallbacksBind, singleton, notifier, enterCallable.target.segment, enterCallable.method, exitCallable.target.segment, exitCallable.method)
    }

    /**
     * Creates an occluder instance and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID will be used in all `occluder_*` RenderingServer functions. Once
     * finished with your RID, you will want to free the RID using the RenderingServer's `free_rid`
     * method. Note: The equivalent resource is `Occluder3D` (not to be confused with the
     * `OccluderInstance3D` node).
     *
     * Generated from Godot docs: RenderingServer.occluder_create
     */
    @JvmStatic
    fun occluderCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(occluderCreateBind, singleton)
    }

    /**
     * Sets the mesh data for the given occluder RID, which controls the shape of the occlusion culling
     * that will be performed.
     *
     * Generated from Godot docs: RenderingServer.occluder_set_mesh
     */
    @JvmStatic
    fun occluderSetMesh(occluder: RID, vertices: List<Vector3>, indices: List<Int>) {
        ObjectCalls.ptrcallWithRIDPackedVector3ListPackedInt32ListArgs(occluderSetMeshBind, singleton, occluder, vertices, indices)
    }

    /**
     * Creates a 3D camera and adds it to the RenderingServer. It can be accessed with the RID that is
     * returned. This RID will be used in all `camera_*` RenderingServer functions. Once finished with
     * your RID, you will want to free the RID using the RenderingServer's `free_rid` method. Note: The
     * equivalent node is `Camera3D`.
     *
     * Generated from Godot docs: RenderingServer.camera_create
     */
    @JvmStatic
    fun cameraCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(cameraCreateBind, singleton)
    }

    /**
     * Sets camera to use perspective projection. Objects on the screen becomes smaller when they are
     * far away.
     *
     * Generated from Godot docs: RenderingServer.camera_set_perspective
     */
    @JvmStatic
    fun cameraSetPerspective(camera: RID, fovyDegrees: Double, zNear: Double, zFar: Double) {
        ObjectCalls.ptrcallWithRIDAndThreeDoubleArgs(cameraSetPerspectiveBind, singleton, camera, fovyDegrees, zNear, zFar)
    }

    /**
     * Sets camera to use orthogonal projection, also known as orthographic projection. Objects remain
     * the same size on the screen no matter how far away they are.
     *
     * Generated from Godot docs: RenderingServer.camera_set_orthogonal
     */
    @JvmStatic
    fun cameraSetOrthogonal(camera: RID, size: Double, zNear: Double, zFar: Double) {
        ObjectCalls.ptrcallWithRIDAndThreeDoubleArgs(cameraSetOrthogonalBind, singleton, camera, size, zNear, zFar)
    }

    /**
     * Sets camera to use frustum projection. This mode allows adjusting the `offset` argument to
     * create "tilted frustum" effects.
     *
     * Generated from Godot docs: RenderingServer.camera_set_frustum
     */
    @JvmStatic
    fun cameraSetFrustum(camera: RID, size: Double, offset: Vector2, zNear: Double, zFar: Double) {
        ObjectCalls.ptrcallWithRIDDoubleVector2TwoDoubleArgs(cameraSetFrustumBind, singleton, camera, size, offset, zNear, zFar)
    }

    /**
     * Sets `Transform3D` of camera.
     *
     * Generated from Godot docs: RenderingServer.camera_set_transform
     */
    @JvmStatic
    fun cameraSetTransform(camera: RID, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDAndTransform3DArg(cameraSetTransformBind, singleton, camera, transform)
    }

    /**
     * Sets the cull mask associated with this camera. The cull mask describes which 3D layers are
     * rendered by this camera. Equivalent to `Camera3D.cull_mask`.
     *
     * Generated from Godot docs: RenderingServer.camera_set_cull_mask
     */
    @JvmStatic
    fun cameraSetCullMask(camera: RID, layers: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(cameraSetCullMaskBind, singleton, camera, layers)
    }

    /**
     * Sets the environment used by this camera. Equivalent to `Camera3D.environment`.
     *
     * Generated from Godot docs: RenderingServer.camera_set_environment
     */
    @JvmStatic
    fun cameraSetEnvironment(camera: RID, env: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(cameraSetEnvironmentBind, singleton, camera, env)
    }

    /**
     * Sets the camera_attributes created with `camera_attributes_create` to the given camera.
     *
     * Generated from Godot docs: RenderingServer.camera_set_camera_attributes
     */
    @JvmStatic
    fun cameraSetCameraAttributes(camera: RID, effects: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(cameraSetCameraAttributesBind, singleton, camera, effects)
    }

    /**
     * Sets the compositor used by this camera. Equivalent to `Camera3D.compositor`.
     *
     * Generated from Godot docs: RenderingServer.camera_set_compositor
     */
    @JvmStatic
    fun cameraSetCompositor(camera: RID, compositor: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(cameraSetCompositorBind, singleton, camera, compositor)
    }

    /**
     * If `true`, preserves the horizontal aspect ratio which is equivalent to `Camera3D.KEEP_WIDTH`.
     * If `false`, preserves the vertical aspect ratio which is equivalent to `Camera3D.KEEP_HEIGHT`.
     *
     * Generated from Godot docs: RenderingServer.camera_set_use_vertical_aspect
     */
    @JvmStatic
    fun cameraSetUseVerticalAspect(camera: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(cameraSetUseVerticalAspectBind, singleton, camera, enable)
    }

    /**
     * Creates an empty viewport and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID will be used in all `viewport_*` RenderingServer functions. Once
     * finished with your RID, you will want to free the RID using the RenderingServer's `free_rid`
     * method. Note: The equivalent node is `Viewport`.
     *
     * Generated from Godot docs: RenderingServer.viewport_create
     */
    @JvmStatic
    fun viewportCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(viewportCreateBind, singleton)
    }

    /**
     * If `true`, the viewport uses augmented or virtual reality technologies. See `XRInterface`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_use_xr
     */
    @JvmStatic
    fun viewportSetUseXr(viewport: RID, useXr: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetUseXrBind, singleton, viewport, useXr)
    }

    /**
     * Sets the viewport's `width` and `height` in pixels. Optionally the `view_count` can be set to
     * increase the number of view layers for stereo rendering.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_size
     */
    @JvmStatic
    fun viewportSetSize(viewport: RID, width: Int, height: Int, viewCount: Int = 1) {
        ObjectCalls.ptrcallWithRIDAndThreeIntArgs(viewportSetSizeBind, singleton, viewport, width, height, viewCount)
    }

    /**
     * If `true`, sets the viewport active, else sets it inactive.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_active
     */
    @JvmStatic
    fun viewportSetActive(viewport: RID, active: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetActiveBind, singleton, viewport, active)
    }

    /**
     * Sets the viewport's parent to the viewport specified by the `parent_viewport` RID.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_parent_viewport
     */
    @JvmStatic
    fun viewportSetParentViewport(viewport: RID, parentViewport: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(viewportSetParentViewportBind, singleton, viewport, parentViewport)
    }

    /**
     * Copies the viewport to a region of the screen specified by `rect`. If
     * `viewport_set_render_direct_to_screen` is `true`, then the viewport does not use a framebuffer
     * and the contents of the viewport are rendered directly to screen. However, note that the root
     * viewport is drawn last, therefore it will draw over the screen. Accordingly, you must set the
     * root viewport to an area that does not cover the area that you have attached this viewport to.
     * For example, you can set the root viewport to not render at all with the following code:
     *
     * Generated from Godot docs: RenderingServer.viewport_attach_to_screen
     */
    @JvmStatic
    fun viewportAttachToScreen(viewport: RID, rect: Rect2, screen: Int = 0) {
        ObjectCalls.ptrcallWithRIDRect2IntArgs(viewportAttachToScreenBind, singleton, viewport, rect, screen)
    }

    /**
     * If `true`, render the contents of the viewport directly to screen. This allows a low-level
     * optimization where you can skip drawing a viewport to the root viewport. While this optimization
     * can result in a significant increase in speed (especially on older devices), it comes at a cost
     * of usability. When this is enabled, you cannot read from the viewport or from the
     * screen_texture. You also lose the benefit of certain window settings, such as the various
     * stretch modes. Another consequence to be aware of is that in 2D the rendering happens in window
     * coordinates, so if you have a viewport that is double the size of the window, and you set this,
     * then only the portion that fits within the window will be drawn, no automatic scaling is
     * possible, even if your game scene is significantly larger than the window size.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_render_direct_to_screen
     */
    @JvmStatic
    fun viewportSetRenderDirectToScreen(viewport: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetRenderDirectToScreenBind, singleton, viewport, enabled)
    }

    /**
     * Sets the rendering mask associated with this `Viewport`. Only `CanvasItem` nodes with a matching
     * rendering visibility layer will be rendered by this `Viewport`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_canvas_cull_mask
     */
    @JvmStatic
    fun viewportSetCanvasCullMask(viewport: RID, canvasCullMask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(viewportSetCanvasCullMaskBind, singleton, viewport, canvasCullMask)
    }

    /**
     * Sets the 3D resolution scaling mode. Bilinear scaling renders at different resolution to either
     * undersample or supersample the viewport. FidelityFX Super Resolution 1.0, abbreviated to FSR, is
     * an upscaling technology that produces high quality images at fast framerates by using a
     * spatially aware upscaling algorithm. FSR is slightly more expensive than bilinear, but it
     * produces significantly higher image quality. FSR should be used where possible.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_scaling_3d_mode
     */
    @JvmStatic
    fun viewportSetScaling3dMode(viewport: RID, scaling3dMode: RenderingServer.ViewportScaling3DMode) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetScaling3dModeBind, singleton, viewport, scaling3dMode.value)
    }

    /**
     * Scales the 3D render buffer based on the viewport size uses an image filter specified in
     * `ViewportScaling3DMode` to scale the output image to the full viewport size. Values lower than
     * `1.0` can be used to speed up 3D rendering at the cost of quality (undersampling). Values
     * greater than `1.0` are only valid for bilinear mode and can be used to improve 3D rendering
     * quality at a high performance cost (supersampling). See also `ViewportMSAA` for multi-sample
     * antialiasing, which is significantly cheaper but only smoothens the edges of polygons. When
     * using FSR upscaling, AMD recommends exposing the following values as preset options to users
     * "Ultra Quality: 0.77", "Quality: 0.67", "Balanced: 0.59", "Performance: 0.5" instead of exposing
     * the entire scale.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_scaling_3d_scale
     */
    @JvmStatic
    fun viewportSetScaling3dScale(viewport: RID, scale: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(viewportSetScaling3dScaleBind, singleton, viewport, scale)
    }

    /**
     * Determines how sharp the upscaled image will be when using the FSR upscaling mode. Sharpness
     * halves with every whole number. Values go from 0.0 (sharpest) to 2.0. Values above 2.0 won't
     * make a visible difference.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_fsr_sharpness
     */
    @JvmStatic
    fun viewportSetFsrSharpness(viewport: RID, sharpness: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(viewportSetFsrSharpnessBind, singleton, viewport, sharpness)
    }

    /**
     * Affects the final texture sharpness by reading from a lower or higher mipmap (also called
     * "texture LOD bias"). Negative values make mipmapped textures sharper but grainier when viewed at
     * a distance, while positive values make mipmapped textures blurrier (even when up close). To get
     * sharper textures at a distance without introducing too much graininess, set this between `-0.75`
     * and `0.0`. Enabling temporal antialiasing
     * (`ProjectSettings.rendering/anti_aliasing/quality/use_taa`) can help reduce the graininess
     * visible when using negative mipmap bias. Note: When the 3D scaling mode is set to FSR 1.0, this
     * value is used to adjust the automatic mipmap bias which is calculated internally based on the
     * scale factor. The formula for this is `-log2(1.0 / scale) + mipmap_bias`. Note: This method is
     * only supported in the Forward+ and Mobile renderers, not Compatibility. In Compatibility, this
     * method is always treated as if `mipmap_bias` was set to `0.0`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_texture_mipmap_bias
     */
    @JvmStatic
    fun viewportSetTextureMipmapBias(viewport: RID, mipmapBias: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(viewportSetTextureMipmapBiasBind, singleton, viewport, mipmapBias)
    }

    /**
     * Sets the maximum number of samples to take when using anisotropic filtering on textures (as a
     * power of two). A higher sample count will result in sharper textures at oblique angles, but is
     * more expensive to compute. A value of `0` forcibly disables anisotropic filtering, even on
     * materials where it is enabled. The anisotropic filtering level also affects decals and light
     * projectors if they are configured to use anisotropic filtering. See
     * `ProjectSettings.rendering/textures/decals/filter` and
     * `ProjectSettings.rendering/textures/light_projectors/filter`. Note: In 3D, for this setting to
     * have an effect, set `BaseMaterial3D.texture_filter` to
     * `BaseMaterial3D.TEXTURE_FILTER_LINEAR_WITH_MIPMAPS_ANISOTROPIC` or
     * `BaseMaterial3D.TEXTURE_FILTER_NEAREST_WITH_MIPMAPS_ANISOTROPIC` on materials. Note: In 2D, for
     * this setting to have an effect, set `CanvasItem.texture_filter` to
     * `CanvasItem.TEXTURE_FILTER_LINEAR_WITH_MIPMAPS_ANISOTROPIC` or
     * `CanvasItem.TEXTURE_FILTER_NEAREST_WITH_MIPMAPS_ANISOTROPIC` on the `CanvasItem` node displaying
     * the texture (or in `CanvasTexture`). However, anisotropic filtering is rarely useful in 2D, so
     * only enable it for textures in 2D if it makes a meaningful visual difference.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_anisotropic_filtering_level
     */
    @JvmStatic
    fun viewportSetAnisotropicFilteringLevel(viewport: RID, anisotropicFilteringLevel: RenderingServer.ViewportAnisotropicFiltering) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetAnisotropicFilteringLevelBind, singleton, viewport, anisotropicFilteringLevel.value)
    }

    /**
     * Sets when the viewport should be updated.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_update_mode
     */
    @JvmStatic
    fun viewportSetUpdateMode(viewport: RID, updateMode: RenderingServer.ViewportUpdateMode) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetUpdateModeBind, singleton, viewport, updateMode.value)
    }

    /**
     * Returns the viewport's update mode. Warning: Calling this from any thread other than the
     * rendering thread will be detrimental to performance.
     *
     * Generated from Godot docs: RenderingServer.viewport_get_update_mode
     */
    @JvmStatic
    fun viewportGetUpdateMode(viewport: RID): RenderingServer.ViewportUpdateMode {
        return RenderingServer.ViewportUpdateMode(ObjectCalls.ptrcallWithRIDArgRetLong(viewportGetUpdateModeBind, singleton, viewport))
    }

    /**
     * Sets the clear mode of a viewport.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_clear_mode
     */
    @JvmStatic
    fun viewportSetClearMode(viewport: RID, clearMode: RenderingServer.ViewportClearMode) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetClearModeBind, singleton, viewport, clearMode.value)
    }

    /**
     * Returns the render target for the viewport.
     *
     * Generated from Godot docs: RenderingServer.viewport_get_render_target
     */
    @JvmStatic
    fun viewportGetRenderTarget(viewport: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(viewportGetRenderTargetBind, singleton, viewport)
    }

    /**
     * Returns the viewport's last rendered frame.
     *
     * Generated from Godot docs: RenderingServer.viewport_get_texture
     */
    @JvmStatic
    fun viewportGetTexture(viewport: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(viewportGetTextureBind, singleton, viewport)
    }

    /**
     * If `true`, the viewport's 3D elements are not rendered.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_disable_3d
     */
    @JvmStatic
    fun viewportSetDisable3d(viewport: RID, disable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetDisable3dBind, singleton, viewport, disable)
    }

    /**
     * If `true`, the viewport's canvas (i.e. 2D and GUI elements) is not rendered.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_disable_2d
     */
    @JvmStatic
    fun viewportSetDisable2d(viewport: RID, disable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetDisable2dBind, singleton, viewport, disable)
    }

    /**
     * Sets the viewport's environment mode which allows enabling or disabling rendering of 3D
     * environment over 2D canvas. When disabled, 2D will not be affected by the environment. When
     * enabled, 2D will be affected by the environment if the environment background mode is
     * `ENV_BG_CANVAS`. The default behavior is to inherit the setting from the viewport's parent. If
     * the topmost parent is also set to `VIEWPORT_ENVIRONMENT_INHERIT`, then the behavior will be the
     * same as if it was set to `VIEWPORT_ENVIRONMENT_ENABLED`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_environment_mode
     */
    @JvmStatic
    fun viewportSetEnvironmentMode(viewport: RID, mode: RenderingServer.ViewportEnvironmentMode) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetEnvironmentModeBind, singleton, viewport, mode.value)
    }

    /**
     * Sets a viewport's camera.
     *
     * Generated from Godot docs: RenderingServer.viewport_attach_camera
     */
    @JvmStatic
    fun viewportAttachCamera(viewport: RID, camera: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(viewportAttachCameraBind, singleton, viewport, camera)
    }

    /**
     * Sets a viewport's scenario. The scenario contains information about environment information,
     * reflection atlas, etc.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_scenario
     */
    @JvmStatic
    fun viewportSetScenario(viewport: RID, scenario: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(viewportSetScenarioBind, singleton, viewport, scenario)
    }

    /**
     * Sets a viewport's canvas.
     *
     * Generated from Godot docs: RenderingServer.viewport_attach_canvas
     */
    @JvmStatic
    fun viewportAttachCanvas(viewport: RID, canvas: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(viewportAttachCanvasBind, singleton, viewport, canvas)
    }

    /**
     * Detaches a viewport from a canvas.
     *
     * Generated from Godot docs: RenderingServer.viewport_remove_canvas
     */
    @JvmStatic
    fun viewportRemoveCanvas(viewport: RID, canvas: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(viewportRemoveCanvasBind, singleton, viewport, canvas)
    }

    /**
     * If `true`, canvas item transforms (i.e. origin position) are snapped to the nearest pixel when
     * rendering. This can lead to a crisper appearance at the cost of less smooth movement, especially
     * when `Camera2D` smoothing is enabled. Equivalent to
     * `ProjectSettings.rendering/2d/snap/snap_2d_transforms_to_pixel`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_snap_2d_transforms_to_pixel
     */
    @JvmStatic
    fun viewportSetSnap2dTransformsToPixel(viewport: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetSnap2dTransformsToPixelBind, singleton, viewport, enabled)
    }

    /**
     * If `true`, canvas item vertices (i.e. polygon points) are snapped to the nearest pixel when
     * rendering. This can lead to a crisper appearance at the cost of less smooth movement, especially
     * when `Camera2D` smoothing is enabled. Equivalent to
     * `ProjectSettings.rendering/2d/snap/snap_2d_vertices_to_pixel`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_snap_2d_vertices_to_pixel
     */
    @JvmStatic
    fun viewportSetSnap2dVerticesToPixel(viewport: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetSnap2dVerticesToPixelBind, singleton, viewport, enabled)
    }

    /**
     * Sets the default texture filtering mode for the specified `viewport` RID.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_default_canvas_item_texture_filter
     */
    @JvmStatic
    fun viewportSetDefaultCanvasItemTextureFilter(viewport: RID, filter: RenderingServer.CanvasItemTextureFilter) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetDefaultCanvasItemTextureFilterBind, singleton, viewport, filter.value)
    }

    /**
     * Sets the default texture repeat mode for the specified `viewport` RID.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_default_canvas_item_texture_repeat
     */
    @JvmStatic
    fun viewportSetDefaultCanvasItemTextureRepeat(viewport: RID, repeat: RenderingServer.CanvasItemTextureRepeat) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetDefaultCanvasItemTextureRepeatBind, singleton, viewport, repeat.value)
    }

    /**
     * Sets the transformation of a viewport's canvas.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_canvas_transform
     */
    @JvmStatic
    fun viewportSetCanvasTransform(viewport: RID, canvas: RID, offset: Transform2D) {
        ObjectCalls.ptrcallWithTwoRIDAndTransform2DArg(viewportSetCanvasTransformBind, singleton, viewport, canvas, offset)
    }

    /**
     * Sets the stacking order for a viewport's canvas. `layer` is the actual canvas layer, while
     * `sublayer` specifies the stacking order of the canvas among those in the same layer. Note:
     * `layer` should be between `CANVAS_LAYER_MIN` and `CANVAS_LAYER_MAX` (inclusive). Any other value
     * will wrap around.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_canvas_stacking
     */
    @JvmStatic
    fun viewportSetCanvasStacking(viewport: RID, canvas: RID, layer: Int, sublayer: Int) {
        ObjectCalls.ptrcallWithTwoRIDTwoIntArgs(viewportSetCanvasStackingBind, singleton, viewport, canvas, layer, sublayer)
    }

    /**
     * If `true`, the viewport renders its background as transparent.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_transparent_background
     */
    @JvmStatic
    fun viewportSetTransparentBackground(viewport: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetTransparentBackgroundBind, singleton, viewport, enabled)
    }

    /**
     * Sets the viewport's global transformation matrix.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_global_canvas_transform
     */
    @JvmStatic
    fun viewportSetGlobalCanvasTransform(viewport: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(viewportSetGlobalCanvasTransformBind, singleton, viewport, transform)
    }

    /**
     * Sets the viewport's 2D signed distance field `ProjectSettings.rendering/2d/sdf/oversize` and
     * `ProjectSettings.rendering/2d/sdf/scale`. This is used when sampling the signed distance field
     * in `CanvasItem` shaders as well as `GPUParticles2D` collision. This is not used by SDFGI in 3D
     * rendering.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_sdf_oversize_and_scale
     */
    @JvmStatic
    fun viewportSetSdfOversizeAndScale(viewport: RID, oversize: RenderingServer.ViewportSDFOversize, scale: RenderingServer.ViewportSDFScale) {
        ObjectCalls.ptrcallWithRIDAndTwoLongArgs(viewportSetSdfOversizeAndScaleBind, singleton, viewport, oversize.value, scale.value)
    }

    /**
     * Sets the `size` of the shadow atlas's images (used for omni and spot lights) on the viewport
     * specified by the `viewport` RID. The value is rounded up to the nearest power of 2. If
     * `use_16_bits` is `true`, use 16 bits for the omni/spot shadow depth map. Enabling this results
     * in shadows having less precision and may result in shadow acne, but can lead to performance
     * improvements on some devices. Note: If this is set to `0`, no positional shadows will be visible
     * at all. This can improve performance significantly on low-end systems by reducing both the CPU
     * and GPU load (as fewer draw calls are needed to draw the scene without shadows).
     *
     * Generated from Godot docs: RenderingServer.viewport_set_positional_shadow_atlas_size
     */
    @JvmStatic
    fun viewportSetPositionalShadowAtlasSize(viewport: RID, size: Int, use16Bits: Boolean = false) {
        ObjectCalls.ptrcallWithRIDIntAndBoolArgs(viewportSetPositionalShadowAtlasSizeBind, singleton, viewport, size, use16Bits)
    }

    /**
     * Sets the number of subdivisions to use in the specified shadow atlas `quadrant` for omni and
     * spot shadows. See also `Viewport.set_positional_shadow_atlas_quadrant_subdiv`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_positional_shadow_atlas_quadrant_subdivision
     */
    @JvmStatic
    fun viewportSetPositionalShadowAtlasQuadrantSubdivision(viewport: RID, quadrant: Int, subdivision: Int) {
        ObjectCalls.ptrcallWithRIDAndTwoIntArgs(viewportSetPositionalShadowAtlasQuadrantSubdivisionBind, singleton, viewport, quadrant, subdivision)
    }

    /**
     * Sets the multisample antialiasing mode for 3D on the specified `viewport` RID. Equivalent to
     * `ProjectSettings.rendering/anti_aliasing/quality/msaa_3d` or `Viewport.msaa_3d`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_msaa_3d
     */
    @JvmStatic
    fun viewportSetMsaa3d(viewport: RID, msaa: RenderingServer.ViewportMSAA) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetMsaa3dBind, singleton, viewport, msaa.value)
    }

    /**
     * Sets the multisample antialiasing mode for 2D/Canvas on the specified `viewport` RID. Equivalent
     * to `ProjectSettings.rendering/anti_aliasing/quality/msaa_2d` or `Viewport.msaa_2d`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_msaa_2d
     */
    @JvmStatic
    fun viewportSetMsaa2d(viewport: RID, msaa: RenderingServer.ViewportMSAA) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetMsaa2dBind, singleton, viewport, msaa.value)
    }

    /**
     * If `true`, 2D rendering will use a high dynamic range (HDR) `RGBA16` format framebuffer.
     * Additionally, 2D rendering will be performed on linear values and will be converted using the
     * appropriate transfer function immediately before blitting to the screen (if the Viewport is
     * attached to the screen). Practically speaking, this means that the end result of the Viewport
     * will not be clamped to the `0-1` range and can be used in 3D rendering without color encoding
     * adjustments. This allows 2D rendering to take advantage of effects requiring high dynamic range
     * (e.g. 2D glow) as well as substantially improves the appearance of effects requiring highly
     * detailed gradients. This setting has the same effect as `Viewport.use_hdr_2d`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_use_hdr_2d
     */
    @JvmStatic
    fun viewportSetUseHdr2d(viewport: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetUseHdr2dBind, singleton, viewport, enabled)
    }

    /**
     * Sets the viewport's screen-space antialiasing mode. Equivalent to
     * `ProjectSettings.rendering/anti_aliasing/quality/screen_space_aa` or `Viewport.screen_space_aa`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_screen_space_aa
     */
    @JvmStatic
    fun viewportSetScreenSpaceAa(viewport: RID, mode: RenderingServer.ViewportScreenSpaceAA) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetScreenSpaceAaBind, singleton, viewport, mode.value)
    }

    /**
     * If `true`, use temporal antialiasing. Equivalent to
     * `ProjectSettings.rendering/anti_aliasing/quality/use_taa` or `Viewport.use_taa`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_use_taa
     */
    @JvmStatic
    fun viewportSetUseTaa(viewport: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetUseTaaBind, singleton, viewport, enable)
    }

    /**
     * Equivalent to `Viewport.use_debanding`. See also
     * `ProjectSettings.rendering/anti_aliasing/quality/use_debanding`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_use_debanding
     */
    @JvmStatic
    fun viewportSetUseDebanding(viewport: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetUseDebandingBind, singleton, viewport, enable)
    }

    /**
     * If `true`, enables occlusion culling on the specified viewport. Equivalent to
     * `ProjectSettings.rendering/occlusion_culling/use_occlusion_culling`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_use_occlusion_culling
     */
    @JvmStatic
    fun viewportSetUseOcclusionCulling(viewport: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetUseOcclusionCullingBind, singleton, viewport, enable)
    }

    /**
     * Sets the `ProjectSettings.rendering/occlusion_culling/occlusion_rays_per_thread` to use for
     * occlusion culling. This parameter is global and cannot be set on a per-viewport basis.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_occlusion_rays_per_thread
     */
    @JvmStatic
    fun viewportSetOcclusionRaysPerThread(raysPerThread: Int) {
        ObjectCalls.ptrcallWithIntArg(viewportSetOcclusionRaysPerThreadBind, singleton, raysPerThread)
    }

    /**
     * Sets the `ProjectSettings.rendering/occlusion_culling/bvh_build_quality` to use for occlusion
     * culling. This parameter is global and cannot be set on a per-viewport basis.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_occlusion_culling_build_quality
     */
    @JvmStatic
    fun viewportSetOcclusionCullingBuildQuality(quality: RenderingServer.ViewportOcclusionCullingBuildQuality) {
        ObjectCalls.ptrcallWithLongArg(viewportSetOcclusionCullingBuildQualityBind, singleton, quality.value)
    }

    /**
     * Returns a statistic about the rendering engine which can be used for performance profiling. This
     * is separated into render pass `type`s, each of them having the same `info`s you can query
     * (different passes will return different values). See also `get_rendering_info`, which returns
     * global information across all viewports. Note: Viewport rendering information is not available
     * until at least 2 frames have been rendered by the engine. If rendering information is not
     * available, `viewport_get_render_info` returns `0`. To print rendering information in `_ready()`
     * successfully, use the following:
     *
     * Generated from Godot docs: RenderingServer.viewport_get_render_info
     */
    @JvmStatic
    fun viewportGetRenderInfo(viewport: RID, type: RenderingServer.ViewportRenderInfoType, info: RenderingServer.ViewportRenderInfo): Int {
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetInt(viewportGetRenderInfoBind, singleton, viewport, type.value, info.value)
    }

    /**
     * Sets the debug draw mode of a viewport.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_debug_draw
     */
    @JvmStatic
    fun viewportSetDebugDraw(viewport: RID, draw: RenderingServer.ViewportDebugDraw) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetDebugDrawBind, singleton, viewport, draw.value)
    }

    /**
     * Sets the measurement for the given `viewport` RID (obtained using `Viewport.get_viewport_rid`).
     * Once enabled, `viewport_get_measured_render_time_cpu` and
     * `viewport_get_measured_render_time_gpu` will return values greater than `0.0` when queried with
     * the given `viewport`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_measure_render_time
     */
    @JvmStatic
    fun viewportSetMeasureRenderTime(viewport: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(viewportSetMeasureRenderTimeBind, singleton, viewport, enable)
    }

    /**
     * Returns the CPU time taken to render the last frame in milliseconds. This only includes time
     * spent in rendering-related operations; scripts' `_process` functions and other engine subsystems
     * are not included in this readout. To get a complete readout of CPU time spent to render the
     * scene, sum the render times of all viewports that are drawn every frame plus
     * `get_frame_setup_time_cpu`. Unlike `Engine.get_frames_per_second`, this method will accurately
     * reflect CPU utilization even if framerate is capped via V-Sync or `Engine.max_fps`. See also
     * `viewport_get_measured_render_time_gpu`. Note: Requires measurements to be enabled on the
     * specified `viewport` using `viewport_set_measure_render_time`. Otherwise, this method returns
     * `0.0`.
     *
     * Generated from Godot docs: RenderingServer.viewport_get_measured_render_time_cpu
     */
    @JvmStatic
    fun viewportGetMeasuredRenderTimeCpu(viewport: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(viewportGetMeasuredRenderTimeCpuBind, singleton, viewport)
    }

    /**
     * Returns the GPU time taken to render the last frame in milliseconds. To get a complete readout
     * of GPU time spent to render the scene, sum the render times of all viewports that are drawn
     * every frame. Unlike `Engine.get_frames_per_second`, this method accurately reflects GPU
     * utilization even if framerate is capped via V-Sync or `Engine.max_fps`. See also
     * `viewport_get_measured_render_time_cpu`. Note: Requires measurements to be enabled on the
     * specified `viewport` using `viewport_set_measure_render_time`. Otherwise, this method returns
     * `0.0`. Note: When GPU utilization is low enough during a certain period of time, GPUs will
     * decrease their power state (which in turn decreases core and memory clock speeds). This can
     * cause the reported GPU time to increase if GPU utilization is kept low enough by a framerate cap
     * (compared to what it would be at the GPU's highest power state). Keep this in mind when
     * benchmarking using `viewport_get_measured_render_time_gpu`. This behavior can be overridden in
     * the graphics driver settings at the cost of higher power usage.
     *
     * Generated from Godot docs: RenderingServer.viewport_get_measured_render_time_gpu
     */
    @JvmStatic
    fun viewportGetMeasuredRenderTimeGpu(viewport: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(viewportGetMeasuredRenderTimeGpuBind, singleton, viewport)
    }

    /**
     * Sets the Variable Rate Shading (VRS) mode for the viewport. If the GPU does not support VRS,
     * this property is ignored. Equivalent to `ProjectSettings.rendering/vrs/mode`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_vrs_mode
     */
    @JvmStatic
    fun viewportSetVrsMode(viewport: RID, mode: RenderingServer.ViewportVRSMode) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetVrsModeBind, singleton, viewport, mode.value)
    }

    /**
     * Sets the update mode for Variable Rate Shading (VRS) for the viewport. VRS requires the input
     * texture to be converted to the format usable by the VRS method supported by the hardware. The
     * update mode defines how often this happens. If the GPU does not support VRS, or VRS is not
     * enabled, this property is ignored. If set to `RenderingServer.VIEWPORT_VRS_UPDATE_ONCE`, the
     * input texture is copied once and the mode is changed to
     * `RenderingServer.VIEWPORT_VRS_UPDATE_DISABLED`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_vrs_update_mode
     */
    @JvmStatic
    fun viewportSetVrsUpdateMode(viewport: RID, mode: RenderingServer.ViewportVRSUpdateMode) {
        ObjectCalls.ptrcallWithRIDAndLongArg(viewportSetVrsUpdateModeBind, singleton, viewport, mode.value)
    }

    /**
     * The texture to use when the VRS mode is set to `RenderingServer.VIEWPORT_VRS_TEXTURE`.
     * Equivalent to `ProjectSettings.rendering/vrs/texture`.
     *
     * Generated from Godot docs: RenderingServer.viewport_set_vrs_texture
     */
    @JvmStatic
    fun viewportSetVrsTexture(viewport: RID, texture: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(viewportSetVrsTextureBind, singleton, viewport, texture)
    }

    /**
     * Creates an empty sky and adds it to the RenderingServer. It can be accessed with the RID that is
     * returned. This RID will be used in all `sky_*` RenderingServer functions. Once finished with
     * your RID, you will want to free the RID using the RenderingServer's `free_rid` method.
     *
     * Generated from Godot docs: RenderingServer.sky_create
     */
    @JvmStatic
    fun skyCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(skyCreateBind, singleton)
    }

    /**
     * Sets the `radiance_size` of the sky specified by the `sky` RID (in pixels). Equivalent to
     * `Sky.radiance_size`.
     *
     * Generated from Godot docs: RenderingServer.sky_set_radiance_size
     */
    @JvmStatic
    fun skySetRadianceSize(sky: RID, radianceSize: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(skySetRadianceSizeBind, singleton, sky, radianceSize)
    }

    /**
     * Sets the process `mode` of the sky specified by the `sky` RID. Equivalent to `Sky.process_mode`.
     *
     * Generated from Godot docs: RenderingServer.sky_set_mode
     */
    @JvmStatic
    fun skySetMode(sky: RID, mode: RenderingServer.SkyMode) {
        ObjectCalls.ptrcallWithRIDAndLongArg(skySetModeBind, singleton, sky, mode.value)
    }

    /**
     * Sets the material that the sky uses to render the background, ambient and reflection maps.
     *
     * Generated from Godot docs: RenderingServer.sky_set_material
     */
    @JvmStatic
    fun skySetMaterial(sky: RID, material: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(skySetMaterialBind, singleton, sky, material)
    }

    /**
     * Generates and returns an `Image` containing the radiance map for the specified `sky` RID. This
     * supports built-in sky material and custom sky shaders. If `bake_irradiance` is `true`, the
     * irradiance map is saved instead of the radiance map. The radiance map is used to render
     * reflected light, while the irradiance map is used to render ambient light. See also
     * `environment_bake_panorama`. Note: The image is saved using linear encoding without any
     * tonemapping performed, which means it will look too dark if viewed directly in an image editor.
     * `energy` values above `1.0` can be used to brighten the resulting image. Note: `size` should be
     * a 2:1 aspect ratio for the generated panorama to have square pixels. For radiance maps, there is
     * no point in using a height greater than `Sky.radiance_size`, as it won't increase detail.
     * Irradiance maps only contain low-frequency data, so there is usually no point in going past a
     * size of 128×64 pixels when saving an irradiance map.
     *
     * Generated from Godot docs: RenderingServer.sky_bake_panorama
     */
    @JvmStatic
    fun skyBakePanorama(sky: RID, energy: Double, bakeIrradiance: Boolean, size: Vector2i): Image? {
        return Image.wrap(ObjectCalls.ptrcallWithRIDDoubleBoolVector2iArgsRetObject(skyBakePanoramaBind, singleton, sky, energy, bakeIrradiance, size))
    }

    /**
     * Creates a new rendering effect and adds it to the RenderingServer. It can be accessed with the
     * RID that is returned. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method.
     *
     * Generated from Godot docs: RenderingServer.compositor_effect_create
     */
    @JvmStatic
    fun compositorEffectCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(compositorEffectCreateBind, singleton)
    }

    /**
     * Enables/disables this rendering effect.
     *
     * Generated from Godot docs: RenderingServer.compositor_effect_set_enabled
     */
    @JvmStatic
    fun compositorEffectSetEnabled(effect: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(compositorEffectSetEnabledBind, singleton, effect, enabled)
    }

    /**
     * Sets the callback type (`callback_type`) and callback method(`callback`) for this rendering
     * effect.
     *
     * Generated from Godot docs: RenderingServer.compositor_effect_set_callback
     */
    @JvmStatic
    fun compositorEffectSetCallback(effect: RID, callbackType: RenderingServer.CompositorEffectCallbackType, callback: GodotCallable) {
        ObjectCalls.ptrcallWithRIDLongCallableArgs(compositorEffectSetCallbackBind, singleton, effect, callbackType.value, callback.target.segment, callback.method)
    }

    /**
     * Sets the flag (`flag`) for this rendering effect to `true` or `false` (`set`).
     *
     * Generated from Godot docs: RenderingServer.compositor_effect_set_flag
     */
    @JvmStatic
    fun compositorEffectSetFlag(effect: RID, flag: RenderingServer.CompositorEffectFlags, set: Boolean) {
        ObjectCalls.ptrcallWithRIDLongAndBoolArgs(compositorEffectSetFlagBind, singleton, effect, flag.value, set)
    }

    /**
     * Creates a new compositor and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method.
     *
     * Generated from Godot docs: RenderingServer.compositor_create
     */
    @JvmStatic
    fun compositorCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(compositorCreateBind, singleton)
    }

    /**
     * Sets the compositor effects for the specified compositor RID. `effects` should be an array
     * containing RIDs created with `compositor_effect_create`.
     *
     * Generated from Godot docs: RenderingServer.compositor_set_compositor_effects
     */
    @JvmStatic
    fun compositorSetCompositorEffects(compositor: RID, effects: List<RID>) {
        ObjectCalls.ptrcallWithRIDAndRIDListArgs(compositorSetCompositorEffectsBind, singleton, compositor, effects)
    }

    /**
     * Creates an environment and adds it to the RenderingServer. It can be accessed with the RID that
     * is returned. This RID will be used in all `environment_*` RenderingServer functions. Once
     * finished with your RID, you will want to free the RID using the RenderingServer's `free_rid`
     * method. Note: The equivalent resource is `Environment`.
     *
     * Generated from Godot docs: RenderingServer.environment_create
     */
    @JvmStatic
    fun environmentCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(environmentCreateBind, singleton)
    }

    /**
     * Sets the environment's background mode. Equivalent to `Environment.background_mode`.
     *
     * Generated from Godot docs: RenderingServer.environment_set_background
     */
    @JvmStatic
    fun environmentSetBackground(env: RID, bg: RenderingServer.EnvironmentBG) {
        ObjectCalls.ptrcallWithRIDAndLongArg(environmentSetBackgroundBind, singleton, env, bg.value)
    }

    /**
     * Sets the camera ID to be used as environment background.
     *
     * Generated from Godot docs: RenderingServer.environment_set_camera_id
     */
    @JvmStatic
    fun environmentSetCameraId(env: RID, id: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(environmentSetCameraIdBind, singleton, env, id)
    }

    /**
     * Sets the `Sky` to be used as the environment's background when using BGMode sky. Equivalent to
     * `Environment.sky`.
     *
     * Generated from Godot docs: RenderingServer.environment_set_sky
     */
    @JvmStatic
    fun environmentSetSky(env: RID, sky: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(environmentSetSkyBind, singleton, env, sky)
    }

    /**
     * Sets a custom field of view for the background `Sky`. Equivalent to
     * `Environment.sky_custom_fov`.
     *
     * Generated from Godot docs: RenderingServer.environment_set_sky_custom_fov
     */
    @JvmStatic
    fun environmentSetSkyCustomFov(env: RID, scale: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(environmentSetSkyCustomFovBind, singleton, env, scale)
    }

    /**
     * Sets the rotation of the background `Sky` expressed as a `Basis`. Equivalent to
     * `Environment.sky_rotation`, where the rotation vector is used to construct the `Basis`.
     *
     * Generated from Godot docs: RenderingServer.environment_set_sky_orientation
     */
    @JvmStatic
    fun environmentSetSkyOrientation(env: RID, orientation: Basis) {
        ObjectCalls.ptrcallWithRIDAndBasisArg(environmentSetSkyOrientationBind, singleton, env, orientation)
    }

    /**
     * Color displayed for clear areas of the scene. Only effective if using the `ENV_BG_COLOR`
     * background mode.
     *
     * Generated from Godot docs: RenderingServer.environment_set_bg_color
     */
    @JvmStatic
    fun environmentSetBgColor(env: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(environmentSetBgColorBind, singleton, env, color)
    }

    /**
     * Sets the intensity of the background color.
     *
     * Generated from Godot docs: RenderingServer.environment_set_bg_energy
     */
    @JvmStatic
    fun environmentSetBgEnergy(env: RID, multiplier: Double, exposureValue: Double) {
        ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(environmentSetBgEnergyBind, singleton, env, multiplier, exposureValue)
    }

    /**
     * Sets the maximum layer to use if using Canvas background mode.
     *
     * Generated from Godot docs: RenderingServer.environment_set_canvas_max_layer
     */
    @JvmStatic
    fun environmentSetCanvasMaxLayer(env: RID, maxLayer: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(environmentSetCanvasMaxLayerBind, singleton, env, maxLayer)
    }

    /**
     * Sets the values to be used for ambient light rendering. See `Environment` for more details.
     *
     * Generated from Godot docs: RenderingServer.environment_set_ambient_light
     */
    @JvmStatic
    fun environmentSetAmbientLight(env: RID, color: Color, ambient: RenderingServer.EnvironmentAmbientSource = RenderingServer.EnvironmentAmbientSource.BG, energy: Double = 1.0, skyContribution: Double = 0.0, reflectionSource: RenderingServer.EnvironmentReflectionSource = RenderingServer.EnvironmentReflectionSource.BG) {
        ObjectCalls.ptrcallWithRIDColorLongTwoDoubleLongArgs(environmentSetAmbientLightBind, singleton, env, color, ambient.value, energy, skyContribution, reflectionSource.value)
    }

    /**
     * Configures glow for the specified environment RID. See `glow_*` properties in `Environment` for
     * more information.
     *
     * Generated from Godot docs: RenderingServer.environment_set_glow
     */
    @JvmStatic
    fun environmentSetGlow(env: RID, enable: Boolean, levels: List<Float>, intensity: Double, strength: Double, mix: Double, bloomThreshold: Double, blendMode: RenderingServer.EnvironmentGlowBlendMode, hdrBleedThreshold: Double, hdrBleedScale: Double, hdrLuminanceCap: Double, glowMapStrength: Double, glowMap: RID) {
        ObjectCalls.ptrcallWithRIDBoolPackedFloat32ListFourDoubleLongFourDoubleRIDArgs(environmentSetGlowBind, singleton, env, enable, levels, intensity, strength, mix, bloomThreshold, blendMode.value, hdrBleedThreshold, hdrBleedScale, hdrLuminanceCap, glowMapStrength, glowMap)
    }

    /**
     * Sets the variables to be used with the "tonemap" post-process effect. See `Environment` for more
     * details.
     *
     * Generated from Godot docs: RenderingServer.environment_set_tonemap
     */
    @JvmStatic
    fun environmentSetTonemap(env: RID, toneMapper: RenderingServer.EnvironmentToneMapper, exposure: Double, white: Double) {
        ObjectCalls.ptrcallWithRIDLongTwoDoubleArgs(environmentSetTonemapBind, singleton, env, toneMapper.value, exposure, white)
    }

    /**
     * See `Environment.tonemap_agx_contrast` for more details.
     *
     * Generated from Godot docs: RenderingServer.environment_set_tonemap_agx_contrast
     */
    @JvmStatic
    fun environmentSetTonemapAgxContrast(env: RID, agxContrast: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(environmentSetTonemapAgxContrastBind, singleton, env, agxContrast)
    }

    /**
     * Sets the values to be used with the "adjustments" post-process effect. See `Environment` for
     * more details.
     *
     * Generated from Godot docs: RenderingServer.environment_set_adjustment
     */
    @JvmStatic
    fun environmentSetAdjustment(env: RID, enable: Boolean, brightness: Double, contrast: Double, saturation: Double, use1dColorCorrection: Boolean, colorCorrection: RID) {
        ObjectCalls.ptrcallWithRIDBoolThreeDoubleBoolRIDArgs(environmentSetAdjustmentBind, singleton, env, enable, brightness, contrast, saturation, use1dColorCorrection, colorCorrection)
    }

    /**
     * Sets the variables to be used with the screen-space reflections (SSR) post-process effect. See
     * `Environment` for more details.
     *
     * Generated from Godot docs: RenderingServer.environment_set_ssr
     */
    @JvmStatic
    fun environmentSetSsr(env: RID, enable: Boolean, maxSteps: Int, fadeIn: Double, fadeOut: Double, depthTolerance: Double) {
        ObjectCalls.ptrcallWithRIDBoolIntThreeDoubleArgs(environmentSetSsrBind, singleton, env, enable, maxSteps, fadeIn, fadeOut, depthTolerance)
    }

    /**
     * Sets the variables to be used with the screen-space ambient occlusion (SSAO) post-process
     * effect. See `Environment` for more details.
     *
     * Generated from Godot docs: RenderingServer.environment_set_ssao
     */
    @JvmStatic
    fun environmentSetSsao(env: RID, enable: Boolean, radius: Double, intensity: Double, power: Double, detail: Double, horizon: Double, sharpness: Double, lightAffect: Double, aoChannelAffect: Double) {
        ObjectCalls.ptrcallWithRIDBoolDoubleDoubleDoubleDoubleDoubleDoubleDoubleDoubleArgs(environmentSetSsaoBind, singleton, env, enable, radius, intensity, power, detail, horizon, sharpness, lightAffect, aoChannelAffect)
    }

    /**
     * Configures fog for the specified environment RID. See `fog_*` properties in `Environment` for
     * more information.
     *
     * Generated from Godot docs: RenderingServer.environment_set_fog
     */
    @JvmStatic
    fun environmentSetFog(env: RID, enable: Boolean, lightColor: Color, lightEnergy: Double, sunScatter: Double, density: Double, height: Double, heightDensity: Double, aerialPerspective: Double, skyAffect: Double, fogMode: RenderingServer.EnvironmentFogMode = RenderingServer.EnvironmentFogMode.EXPONENTIAL) {
        ObjectCalls.ptrcallWithRIDBoolColorDoubleDoubleDoubleDoubleDoubleDoubleDoubleLongArgs(environmentSetFogBind, singleton, env, enable, lightColor, lightEnergy, sunScatter, density, height, heightDensity, aerialPerspective, skyAffect, fogMode.value)
    }

    /**
     * Configures fog depth for the specified environment RID. Only has an effect when the fog mode of
     * the environment is `ENV_FOG_MODE_DEPTH`. See `fog_depth_*` properties in `Environment` for more
     * information.
     *
     * Generated from Godot docs: RenderingServer.environment_set_fog_depth
     */
    @JvmStatic
    fun environmentSetFogDepth(env: RID, curve: Double, begin: Double, end: Double) {
        ObjectCalls.ptrcallWithRIDAndThreeDoubleArgs(environmentSetFogDepthBind, singleton, env, curve, begin, end)
    }

    /**
     * Configures signed distance field global illumination for the specified environment RID. See
     * `sdfgi_*` properties in `Environment` for more information.
     *
     * Generated from Godot docs: RenderingServer.environment_set_sdfgi
     */
    @JvmStatic
    fun environmentSetSdfgi(env: RID, enable: Boolean, cascades: Int, minCellSize: Double, yScale: RenderingServer.EnvironmentSDFGIYScale, useOcclusion: Boolean, bounceFeedback: Double, readSky: Boolean, energy: Double, normalBias: Double, probeBias: Double) {
        ObjectCalls.ptrcallWithRIDBoolIntDoubleLongBoolDoubleBoolThreeDoubleArgs(environmentSetSdfgiBind, singleton, env, enable, cascades, minCellSize, yScale.value, useOcclusion, bounceFeedback, readSky, energy, normalBias, probeBias)
    }

    /**
     * Sets the variables to be used with the volumetric fog post-process effect. See `Environment` for
     * more details.
     *
     * Generated from Godot docs: RenderingServer.environment_set_volumetric_fog
     */
    @JvmStatic
    fun environmentSetVolumetricFog(env: RID, enable: Boolean, density: Double, albedo: Color, emission: Color, emissionEnergy: Double, anisotropy: Double, length: Double, detailSpread: Double, giInject: Double, temporalReprojection: Boolean, temporalReprojectionAmount: Double, ambientInject: Double, skyAffect: Double) {
        ObjectCalls.ptrcallWithRIDBoolDoubleTwoColorDoubleDoubleDoubleDoubleDoubleBoolThreeDoubleArgs(environmentSetVolumetricFogBind, singleton, env, enable, density, albedo, emission, emissionEnergy, anisotropy, length, detailSpread, giInject, temporalReprojection, temporalReprojectionAmount, ambientInject, skyAffect)
    }

    /**
     * If `enable` is `true`, enables bicubic upscaling for glow which improves quality at the cost of
     * performance. Equivalent to `ProjectSettings.rendering/environment/glow/upscale_mode`. Note: This
     * setting is only effective when using the Forward+ or Mobile rendering methods, as Compatibility
     * uses a different glow implementation.
     *
     * Generated from Godot docs: RenderingServer.environment_glow_set_use_bicubic_upscale
     */
    @JvmStatic
    fun environmentGlowSetUseBicubicUpscale(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(environmentGlowSetUseBicubicUpscaleBind, singleton, enable)
    }

    /**
     * Sets whether screen-space reflections will be rendered at full or half size. Half size is
     * faster, but may look pixelated or cause flickering.
     *
     * Generated from Godot docs: RenderingServer.environment_set_ssr_half_size
     */
    @JvmStatic
    fun environmentSetSsrHalfSize(halfSize: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(environmentSetSsrHalfSizeBind, singleton, halfSize)
    }

    @JvmStatic
    fun environmentSetSsrRoughnessQuality(quality: RenderingServer.EnvironmentSSRRoughnessQuality) {
        ObjectCalls.ptrcallWithLongArg(environmentSetSsrRoughnessQualityBind, singleton, quality.value)
    }

    /**
     * Sets the quality level of the screen-space ambient occlusion (SSAO) post-process effect. See
     * `Environment` for more details.
     *
     * Generated from Godot docs: RenderingServer.environment_set_ssao_quality
     */
    @JvmStatic
    fun environmentSetSsaoQuality(quality: RenderingServer.EnvironmentSSAOQuality, halfSize: Boolean, adaptiveTarget: Double, blurPasses: Int, fadeoutFrom: Double, fadeoutTo: Double) {
        ObjectCalls.ptrcallWithLongBoolDoubleIntAndTwoDoubleArgs(environmentSetSsaoQualityBind, singleton, quality.value, halfSize, adaptiveTarget, blurPasses, fadeoutFrom, fadeoutTo)
    }

    /**
     * Sets the quality level of the screen-space indirect lighting (SSIL) post-process effect. See
     * `Environment` for more details.
     *
     * Generated from Godot docs: RenderingServer.environment_set_ssil_quality
     */
    @JvmStatic
    fun environmentSetSsilQuality(quality: RenderingServer.EnvironmentSSILQuality, halfSize: Boolean, adaptiveTarget: Double, blurPasses: Int, fadeoutFrom: Double, fadeoutTo: Double) {
        ObjectCalls.ptrcallWithLongBoolDoubleIntAndTwoDoubleArgs(environmentSetSsilQualityBind, singleton, quality.value, halfSize, adaptiveTarget, blurPasses, fadeoutFrom, fadeoutTo)
    }

    /**
     * Sets the number of rays to throw per frame when computing signed distance field global
     * illumination. Equivalent to
     * `ProjectSettings.rendering/global_illumination/sdfgi/probe_ray_count`.
     *
     * Generated from Godot docs: RenderingServer.environment_set_sdfgi_ray_count
     */
    @JvmStatic
    fun environmentSetSdfgiRayCount(rayCount: RenderingServer.EnvironmentSDFGIRayCount) {
        ObjectCalls.ptrcallWithLongArg(environmentSetSdfgiRayCountBind, singleton, rayCount.value)
    }

    /**
     * Sets the number of frames to use for converging signed distance field global illumination.
     * Equivalent to `ProjectSettings.rendering/global_illumination/sdfgi/frames_to_converge`.
     *
     * Generated from Godot docs: RenderingServer.environment_set_sdfgi_frames_to_converge
     */
    @JvmStatic
    fun environmentSetSdfgiFramesToConverge(frames: RenderingServer.EnvironmentSDFGIFramesToConverge) {
        ObjectCalls.ptrcallWithLongArg(environmentSetSdfgiFramesToConvergeBind, singleton, frames.value)
    }

    /**
     * Sets the update speed for dynamic lights' indirect lighting when computing signed distance field
     * global illumination. Equivalent to
     * `ProjectSettings.rendering/global_illumination/sdfgi/frames_to_update_lights`.
     *
     * Generated from Godot docs: RenderingServer.environment_set_sdfgi_frames_to_update_light
     */
    @JvmStatic
    fun environmentSetSdfgiFramesToUpdateLight(frames: RenderingServer.EnvironmentSDFGIFramesToUpdateLight) {
        ObjectCalls.ptrcallWithLongArg(environmentSetSdfgiFramesToUpdateLightBind, singleton, frames.value)
    }

    /**
     * Sets the resolution of the volumetric fog's froxel buffer. `size` is modified by the screen's
     * aspect ratio and then used to set the width and height of the buffer. While `depth` is directly
     * used to set the depth of the buffer.
     *
     * Generated from Godot docs: RenderingServer.environment_set_volumetric_fog_volume_size
     */
    @JvmStatic
    fun environmentSetVolumetricFogVolumeSize(size: Int, depth: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(environmentSetVolumetricFogVolumeSizeBind, singleton, size, depth)
    }

    /**
     * Enables filtering of the volumetric fog scattering buffer. This results in much smoother volumes
     * with very few under-sampling artifacts.
     *
     * Generated from Godot docs: RenderingServer.environment_set_volumetric_fog_filter_active
     */
    @JvmStatic
    fun environmentSetVolumetricFogFilterActive(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(environmentSetVolumetricFogFilterActiveBind, singleton, active)
    }

    /**
     * Generates and returns an `Image` containing the radiance map for the specified `environment`
     * RID's sky. This supports built-in sky material and custom sky shaders. If `bake_irradiance` is
     * `true`, the irradiance map is saved instead of the radiance map. The radiance map is used to
     * render reflected light, while the irradiance map is used to render ambient light. See also
     * `sky_bake_panorama`. Note: The image is saved using linear encoding without any tonemapping
     * performed, which means it will look too dark if viewed directly in an image editor. Note: `size`
     * should be a 2:1 aspect ratio for the generated panorama to have square pixels. For radiance
     * maps, there is no point in using a height greater than `Sky.radiance_size`, as it won't increase
     * detail. Irradiance maps only contain low-frequency data, so there is usually no point in going
     * past a size of 128×64 pixels when saving an irradiance map.
     *
     * Generated from Godot docs: RenderingServer.environment_bake_panorama
     */
    @JvmStatic
    fun environmentBakePanorama(environment: RID, bakeIrradiance: Boolean, size: Vector2i): Image? {
        return Image.wrap(ObjectCalls.ptrcallWithRIDBoolVector2iArgsRetObject(environmentBakePanoramaBind, singleton, environment, bakeIrradiance, size))
    }

    /**
     * Sets the screen-space roughness limiter parameters, such as whether it should be enabled and its
     * thresholds. Equivalent to
     * `ProjectSettings.rendering/anti_aliasing/screen_space_roughness_limiter/enabled`,
     * `ProjectSettings.rendering/anti_aliasing/screen_space_roughness_limiter/amount` and
     * `ProjectSettings.rendering/anti_aliasing/screen_space_roughness_limiter/limit`.
     *
     * Generated from Godot docs: RenderingServer.screen_space_roughness_limiter_set_active
     */
    @JvmStatic
    fun screenSpaceRoughnessLimiterSetActive(enable: Boolean, amount: Double, limit: Double) {
        ObjectCalls.ptrcallWithBoolTwoDoubleArgs(screenSpaceRoughnessLimiterSetActiveBind, singleton, enable, amount, limit)
    }

    /**
     * Sets `ProjectSettings.rendering/environment/subsurface_scattering/subsurface_scattering_quality`
     * to use when rendering materials that have subsurface scattering enabled.
     *
     * Generated from Godot docs: RenderingServer.sub_surface_scattering_set_quality
     */
    @JvmStatic
    fun subSurfaceScatteringSetQuality(quality: RenderingServer.SubSurfaceScatteringQuality) {
        ObjectCalls.ptrcallWithLongArg(subSurfaceScatteringSetQualityBind, singleton, quality.value)
    }

    /**
     * Sets the
     * `ProjectSettings.rendering/environment/subsurface_scattering/subsurface_scattering_scale` and
     * `ProjectSettings.rendering/environment/subsurface_scattering/subsurface_scattering_depth_scale`
     * to use when rendering materials that have subsurface scattering enabled.
     *
     * Generated from Godot docs: RenderingServer.sub_surface_scattering_set_scale
     */
    @JvmStatic
    fun subSurfaceScatteringSetScale(scale: Double, depthScale: Double) {
        ObjectCalls.ptrcallWithTwoDoubleArgs(subSurfaceScatteringSetScaleBind, singleton, scale, depthScale)
    }

    /**
     * Creates a camera attributes object and adds it to the RenderingServer. It can be accessed with
     * the RID that is returned. This RID will be used in all `camera_attributes_` RenderingServer
     * functions. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method. Note: The equivalent resource is `CameraAttributes`.
     *
     * Generated from Godot docs: RenderingServer.camera_attributes_create
     */
    @JvmStatic
    fun cameraAttributesCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(cameraAttributesCreateBind, singleton)
    }

    /**
     * Sets the quality level of the DOF blur effect to `quality`. `use_jitter` can be used to jitter
     * samples taken during the blur pass to hide artifacts at the cost of looking more fuzzy.
     *
     * Generated from Godot docs: RenderingServer.camera_attributes_set_dof_blur_quality
     */
    @JvmStatic
    fun cameraAttributesSetDofBlurQuality(quality: RenderingServer.DOFBlurQuality, useJitter: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(cameraAttributesSetDofBlurQualityBind, singleton, quality.value, useJitter)
    }

    /**
     * Sets the shape of the DOF bokeh pattern to `shape`. Different shapes may be used to achieve
     * artistic effect, or to meet performance targets.
     *
     * Generated from Godot docs: RenderingServer.camera_attributes_set_dof_blur_bokeh_shape
     */
    @JvmStatic
    fun cameraAttributesSetDofBlurBokehShape(shape: RenderingServer.DOFBokehShape) {
        ObjectCalls.ptrcallWithLongArg(cameraAttributesSetDofBlurBokehShapeBind, singleton, shape.value)
    }

    /**
     * Sets the parameters to use with the DOF blur effect. These parameters take on the same meaning
     * as their counterparts in `CameraAttributesPractical`.
     *
     * Generated from Godot docs: RenderingServer.camera_attributes_set_dof_blur
     */
    @JvmStatic
    fun cameraAttributesSetDofBlur(cameraAttributes: RID, farEnable: Boolean, farDistance: Double, farTransition: Double, nearEnable: Boolean, nearDistance: Double, nearTransition: Double, amount: Double) {
        ObjectCalls.ptrcallWithRIDBoolTwoDoubleBoolThreeDoubleArgs(cameraAttributesSetDofBlurBind, singleton, cameraAttributes, farEnable, farDistance, farTransition, nearEnable, nearDistance, nearTransition, amount)
    }

    /**
     * Sets the exposure values that will be used by the renderers. The normalization amount is used to
     * bake a given Exposure Value (EV) into rendering calculations to reduce the dynamic range of the
     * scene. The normalization factor can be calculated from exposure value (EV100) as follows:
     *
     * Generated from Godot docs: RenderingServer.camera_attributes_set_exposure
     */
    @JvmStatic
    fun cameraAttributesSetExposure(cameraAttributes: RID, multiplier: Double, normalization: Double) {
        ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(cameraAttributesSetExposureBind, singleton, cameraAttributes, multiplier, normalization)
    }

    /**
     * Sets the parameters to use with the auto-exposure effect. These parameters take on the same
     * meaning as their counterparts in `CameraAttributes` and `CameraAttributesPractical`.
     *
     * Generated from Godot docs: RenderingServer.camera_attributes_set_auto_exposure
     */
    @JvmStatic
    fun cameraAttributesSetAutoExposure(cameraAttributes: RID, enable: Boolean, minSensitivity: Double, maxSensitivity: Double, speed: Double, scale: Double) {
        ObjectCalls.ptrcallWithRIDBoolFourDoubleArgs(cameraAttributesSetAutoExposureBind, singleton, cameraAttributes, enable, minSensitivity, maxSensitivity, speed, scale)
    }

    /**
     * Creates a scenario and adds it to the RenderingServer. It can be accessed with the RID that is
     * returned. This RID will be used in all `scenario_*` RenderingServer functions. Once finished
     * with your RID, you will want to free the RID using the RenderingServer's `free_rid` method. The
     * scenario is the 3D world that all the visual instances exist in.
     *
     * Generated from Godot docs: RenderingServer.scenario_create
     */
    @JvmStatic
    fun scenarioCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(scenarioCreateBind, singleton)
    }

    /**
     * Sets the environment that will be used with this scenario. See also `Environment`.
     *
     * Generated from Godot docs: RenderingServer.scenario_set_environment
     */
    @JvmStatic
    fun scenarioSetEnvironment(scenario: RID, environment: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(scenarioSetEnvironmentBind, singleton, scenario, environment)
    }

    /**
     * Sets the fallback environment to be used by this scenario. The fallback environment is used if
     * no environment is set. Internally, this is used by the editor to provide a default environment.
     *
     * Generated from Godot docs: RenderingServer.scenario_set_fallback_environment
     */
    @JvmStatic
    fun scenarioSetFallbackEnvironment(scenario: RID, environment: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(scenarioSetFallbackEnvironmentBind, singleton, scenario, environment)
    }

    /**
     * Sets the camera attributes (`effects`) that will be used with this scenario. See also
     * `CameraAttributes`.
     *
     * Generated from Godot docs: RenderingServer.scenario_set_camera_attributes
     */
    @JvmStatic
    fun scenarioSetCameraAttributes(scenario: RID, effects: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(scenarioSetCameraAttributesBind, singleton, scenario, effects)
    }

    /**
     * Sets the compositor (`compositor`) that will be used with this scenario. See also `Compositor`.
     *
     * Generated from Godot docs: RenderingServer.scenario_set_compositor
     */
    @JvmStatic
    fun scenarioSetCompositor(scenario: RID, compositor: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(scenarioSetCompositorBind, singleton, scenario, compositor)
    }

    /**
     * Creates a visual instance, adds it to the RenderingServer, and sets both base and scenario. It
     * can be accessed with the RID that is returned. This RID will be used in all `instance_*`
     * RenderingServer functions. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method. This is a shorthand for using `instance_create` and setting
     * the base and scenario manually.
     *
     * Generated from Godot docs: RenderingServer.instance_create2
     */
    @JvmStatic
    fun instanceCreate2(base: RID, scenario: RID): RID {
        return ObjectCalls.ptrcallWithTwoRIDArgsRetRID(instanceCreate2Bind, singleton, base, scenario)
    }

    /**
     * Creates a visual instance and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID will be used in all `instance_*` RenderingServer functions. Once
     * finished with your RID, you will want to free the RID using the RenderingServer's `free_rid`
     * method. An instance is a way of placing a 3D object in the scenario. Objects like particles,
     * meshes, reflection probes and decals need to be associated with an instance to be visible in the
     * scenario using `instance_set_base`. Note: The equivalent node is `VisualInstance3D`.
     *
     * Generated from Godot docs: RenderingServer.instance_create
     */
    @JvmStatic
    fun instanceCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(instanceCreateBind, singleton)
    }

    /**
     * Sets the base of the instance. A base can be any of the 3D objects that are created in the
     * RenderingServer that can be displayed. For example, any of the light types, mesh, multimesh,
     * particle system, reflection probe, decal, lightmap, voxel GI and visibility notifiers are all
     * types that can be set as the base of an instance in order to be displayed in the scenario.
     *
     * Generated from Godot docs: RenderingServer.instance_set_base
     */
    @JvmStatic
    fun instanceSetBase(instance: RID, base: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(instanceSetBaseBind, singleton, instance, base)
    }

    /**
     * Sets the scenario that the instance is in. The scenario is the 3D world that the objects will be
     * displayed in.
     *
     * Generated from Godot docs: RenderingServer.instance_set_scenario
     */
    @JvmStatic
    fun instanceSetScenario(instance: RID, scenario: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(instanceSetScenarioBind, singleton, instance, scenario)
    }

    /**
     * Sets the render layers that this instance will be drawn to. Equivalent to
     * `VisualInstance3D.layers`.
     *
     * Generated from Godot docs: RenderingServer.instance_set_layer_mask
     */
    @JvmStatic
    fun instanceSetLayerMask(instance: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(instanceSetLayerMaskBind, singleton, instance, mask)
    }

    /**
     * Sets the sorting offset and switches between using the bounding box or instance origin for depth
     * sorting.
     *
     * Generated from Godot docs: RenderingServer.instance_set_pivot_data
     */
    @JvmStatic
    fun instanceSetPivotData(instance: RID, sortingOffset: Double, useAabbCenter: Boolean) {
        ObjectCalls.ptrcallWithRIDDoubleBoolArgs(instanceSetPivotDataBind, singleton, instance, sortingOffset, useAabbCenter)
    }

    /**
     * Sets the world space transform of the instance. Equivalent to `Node3D.global_transform`.
     *
     * Generated from Godot docs: RenderingServer.instance_set_transform
     */
    @JvmStatic
    fun instanceSetTransform(instance: RID, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDAndTransform3DArg(instanceSetTransformBind, singleton, instance, transform)
    }

    /**
     * Attaches a unique Object ID to instance. Object ID must be attached to instance for proper
     * culling with `instances_cull_aabb`, `instances_cull_convex`, and `instances_cull_ray`.
     *
     * Generated from Godot docs: RenderingServer.instance_attach_object_instance_id
     */
    @JvmStatic
    fun instanceAttachObjectInstanceId(instance: RID, id: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(instanceAttachObjectInstanceIdBind, singleton, instance, id)
    }

    /**
     * Sets the weight for a given blend shape associated with this instance.
     *
     * Generated from Godot docs: RenderingServer.instance_set_blend_shape_weight
     */
    @JvmStatic
    fun instanceSetBlendShapeWeight(instance: RID, shape: Int, weight: Double) {
        ObjectCalls.ptrcallWithRIDIntDoubleArgs(instanceSetBlendShapeWeightBind, singleton, instance, shape, weight)
    }

    /**
     * Sets the override material of a specific surface. Equivalent to
     * `MeshInstance3D.set_surface_override_material`.
     *
     * Generated from Godot docs: RenderingServer.instance_set_surface_override_material
     */
    @JvmStatic
    fun instanceSetSurfaceOverrideMaterial(instance: RID, surface: Int, material: RID) {
        ObjectCalls.ptrcallWithRIDIntAndRIDArgs(instanceSetSurfaceOverrideMaterialBind, singleton, instance, surface, material)
    }

    /**
     * Sets whether an instance is drawn or not. Equivalent to `Node3D.visible`.
     *
     * Generated from Godot docs: RenderingServer.instance_set_visible
     */
    @JvmStatic
    fun instanceSetVisible(instance: RID, visible: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(instanceSetVisibleBind, singleton, instance, visible)
    }

    /**
     * Sets the transparency for the given geometry instance. Equivalent to
     * `GeometryInstance3D.transparency`. A transparency of `0.0` is fully opaque, while `1.0` is fully
     * transparent. Values greater than `0.0` (exclusive) will force the geometry's materials to go
     * through the transparent pipeline, which is slower to render and can exhibit rendering issues due
     * to incorrect transparency sorting. However, unlike using a transparent material, setting
     * `transparency` to a value greater than `0.0` (exclusive) will not disable shadow rendering. In
     * spatial shaders, `1.0 - transparency` is set as the default value of the `ALPHA` built-in. Note:
     * `transparency` is clamped between `0.0` and `1.0`, so this property cannot be used to make
     * transparent materials more opaque than they originally are.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_set_transparency
     */
    @JvmStatic
    fun instanceGeometrySetTransparency(instance: RID, transparency: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(instanceGeometrySetTransparencyBind, singleton, instance, transparency)
    }

    /**
     * Resets motion vectors and other interpolated values. Use this after teleporting a mesh from one
     * position to another to avoid ghosting artifacts.
     *
     * Generated from Godot docs: RenderingServer.instance_teleport
     */
    @JvmStatic
    fun instanceTeleport(instance: RID) {
        ObjectCalls.ptrcallWithRIDArg(instanceTeleportBind, singleton, instance)
    }

    /**
     * Sets a custom AABB to use when culling objects from the view frustum. Equivalent to setting
     * `GeometryInstance3D.custom_aabb`.
     *
     * Generated from Godot docs: RenderingServer.instance_set_custom_aabb
     */
    @JvmStatic
    fun instanceSetCustomAabb(instance: RID, aabb: AABB) {
        ObjectCalls.ptrcallWithRIDAndAABBArg(instanceSetCustomAabbBind, singleton, instance, aabb)
    }

    /**
     * Attaches a skeleton to an instance. Removes the previous skeleton from the instance.
     *
     * Generated from Godot docs: RenderingServer.instance_attach_skeleton
     */
    @JvmStatic
    fun instanceAttachSkeleton(instance: RID, skeleton: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(instanceAttachSkeletonBind, singleton, instance, skeleton)
    }

    /**
     * Sets a margin to increase the size of the AABB when culling objects from the view frustum. This
     * allows you to avoid culling objects that fall outside the view frustum. Equivalent to
     * `GeometryInstance3D.extra_cull_margin`.
     *
     * Generated from Godot docs: RenderingServer.instance_set_extra_visibility_margin
     */
    @JvmStatic
    fun instanceSetExtraVisibilityMargin(instance: RID, margin: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(instanceSetExtraVisibilityMarginBind, singleton, instance, margin)
    }

    /**
     * Sets the visibility parent for the given instance. Equivalent to `Node3D.visibility_parent`.
     *
     * Generated from Godot docs: RenderingServer.instance_set_visibility_parent
     */
    @JvmStatic
    fun instanceSetVisibilityParent(instance: RID, parent: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(instanceSetVisibilityParentBind, singleton, instance, parent)
    }

    /**
     * If `true`, ignores all culling on the specified 3D geometry instance, including frustum culling,
     * occlusion culling, and layer culling. This is not the same as
     * `GeometryInstance3D.ignore_occlusion_culling`, which only ignores occlusion culling but leaves
     * frustum and layer culling intact.
     *
     * Generated from Godot docs: RenderingServer.instance_set_ignore_culling
     */
    @JvmStatic
    fun instanceSetIgnoreCulling(instance: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(instanceSetIgnoreCullingBind, singleton, instance, enabled)
    }

    /**
     * Sets the `flag` for a given `instance` to `enabled`.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_set_flag
     */
    @JvmStatic
    fun instanceGeometrySetFlag(instance: RID, flag: RenderingServer.InstanceFlags, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDLongAndBoolArgs(instanceGeometrySetFlagBind, singleton, instance, flag.value, enabled)
    }

    /**
     * Sets the shadow casting setting. Equivalent to `GeometryInstance3D.cast_shadow`.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_set_cast_shadows_setting
     */
    @JvmStatic
    fun instanceGeometrySetCastShadowsSetting(instance: RID, shadowCastingSetting: RenderingServer.ShadowCastingSetting) {
        ObjectCalls.ptrcallWithRIDAndLongArg(instanceGeometrySetCastShadowsSettingBind, singleton, instance, shadowCastingSetting.value)
    }

    /**
     * Sets a material that will override the material for all surfaces on the mesh associated with
     * this instance. Equivalent to `GeometryInstance3D.material_override`.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_set_material_override
     */
    @JvmStatic
    fun instanceGeometrySetMaterialOverride(instance: RID, material: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(instanceGeometrySetMaterialOverrideBind, singleton, instance, material)
    }

    /**
     * Sets a material that will be rendered for all surfaces on top of active materials for the mesh
     * associated with this instance. Equivalent to `GeometryInstance3D.material_overlay`.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_set_material_overlay
     */
    @JvmStatic
    fun instanceGeometrySetMaterialOverlay(instance: RID, material: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(instanceGeometrySetMaterialOverlayBind, singleton, instance, material)
    }

    /**
     * Sets the visibility range values for the given geometry instance. Equivalent to
     * `GeometryInstance3D.visibility_range_begin` and related properties.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_set_visibility_range
     */
    @JvmStatic
    fun instanceGeometrySetVisibilityRange(instance: RID, min: Double, max: Double, minMargin: Double, maxMargin: Double, fadeMode: RenderingServer.VisibilityRangeFadeMode) {
        ObjectCalls.ptrcallWithRIDFourDoubleLongArgs(instanceGeometrySetVisibilityRangeBind, singleton, instance, min, max, minMargin, maxMargin, fadeMode.value)
    }

    /**
     * Sets the lightmap GI instance to use for the specified 3D geometry instance. The lightmap UV
     * scale for the specified instance (equivalent to `GeometryInstance3D.gi_lightmap_scale`) and
     * lightmap atlas slice must also be specified.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_set_lightmap
     */
    @JvmStatic
    fun instanceGeometrySetLightmap(instance: RID, lightmap: RID, lightmapUvScale: Rect2, lightmapSlice: Int) {
        ObjectCalls.ptrcallWithTwoRIDRect2IntArgs(instanceGeometrySetLightmapBind, singleton, instance, lightmap, lightmapUvScale, lightmapSlice)
    }

    /**
     * Sets the level of detail bias to use when rendering the specified 3D geometry instance. Higher
     * values result in higher detail from further away. Equivalent to `GeometryInstance3D.lod_bias`.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_set_lod_bias
     */
    @JvmStatic
    fun instanceGeometrySetLodBias(instance: RID, lodBias: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(instanceGeometrySetLodBiasBind, singleton, instance, lodBias)
    }

    /**
     * Sets the per-instance shader uniform on the specified 3D geometry instance. Equivalent to
     * `GeometryInstance3D.set_instance_shader_parameter`.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_set_shader_parameter
     */
    @JvmStatic
    fun instanceGeometrySetShaderParameter(instance: RID, parameter: String, value: Any?) {
        ObjectCalls.ptrcallWithRIDStringNameAndVariantArgs(instanceGeometrySetShaderParameterBind, singleton, instance, parameter, value)
    }

    /**
     * Returns the value of the per-instance shader uniform from the specified 3D geometry instance.
     * Equivalent to `GeometryInstance3D.get_instance_shader_parameter`. Note: Per-instance shader
     * parameter names are case-sensitive.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_get_shader_parameter
     */
    @JvmStatic
    fun instanceGeometryGetShaderParameter(instance: RID, parameter: String): Any? {
        return ObjectCalls.ptrcallWithRIDAndStringNameArgRetVariantScalar(instanceGeometryGetShaderParameterBind, singleton, instance, parameter)
    }

    /**
     * Returns the default value of the per-instance shader uniform from the specified 3D geometry
     * instance. Equivalent to `GeometryInstance3D.get_instance_shader_parameter`.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_get_shader_parameter_default_value
     */
    @JvmStatic
    fun instanceGeometryGetShaderParameterDefaultValue(instance: RID, parameter: String): Any? {
        return ObjectCalls.ptrcallWithRIDAndStringNameArgRetVariantScalar(instanceGeometryGetShaderParameterDefaultValueBind, singleton, instance, parameter)
    }

    /**
     * Returns a dictionary of per-instance shader uniform names of the per-instance shader uniform
     * from the specified 3D geometry instance. The returned dictionary is in PropertyInfo format, with
     * the keys `name`, `class_name`, `type`, `hint`, `hint_string` and `usage`. Equivalent to
     * `GeometryInstance3D.get_instance_shader_parameter`.
     *
     * Generated from Godot docs: RenderingServer.instance_geometry_get_shader_parameter_list
     */
    @JvmStatic
    fun instanceGeometryGetShaderParameterList(instance: RID): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithRIDArgRetDictionaryList(instanceGeometryGetShaderParameterListBind, singleton, instance)
    }

    /**
     * Returns an array of object IDs intersecting with the provided AABB. Only 3D nodes that inherit
     * from `VisualInstance3D` are considered, such as `MeshInstance3D` or `DirectionalLight3D`. Use
     * `@GlobalScope.instance_from_id` to obtain the actual nodes. A scenario RID must be provided,
     * which is available in the `World3D` you want to query. This forces an update for all resources
     * queued to update. Warning: This function is primarily intended for editor usage. For in-game use
     * cases, prefer physics collision.
     *
     * Generated from Godot docs: RenderingServer.instances_cull_aabb
     */
    @JvmStatic
    fun instancesCullAabb(aabb: AABB, scenario: RID): List<Long> {
        return ObjectCalls.ptrcallWithAABBRIDArgsRetPackedInt64List(instancesCullAabbBind, singleton, aabb, scenario)
    }

    /**
     * Returns an array of object IDs intersecting with the provided 3D ray. Only 3D nodes that inherit
     * from `VisualInstance3D` are considered, such as `MeshInstance3D` or `DirectionalLight3D`. Use
     * `@GlobalScope.instance_from_id` to obtain the actual nodes. A scenario RID must be provided,
     * which is available in the `World3D` you want to query. This forces an update for all resources
     * queued to update. Warning: This function is primarily intended for editor usage. For in-game use
     * cases, prefer physics collision.
     *
     * Generated from Godot docs: RenderingServer.instances_cull_ray
     */
    @JvmStatic
    fun instancesCullRay(from: Vector3, to: Vector3, scenario: RID): List<Long> {
        return ObjectCalls.ptrcallWithTwoVector3RIDArgsRetPackedInt64List(instancesCullRayBind, singleton, from, to, scenario)
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
    @JvmStatic
    fun instancesCullConvex(convex: List<Plane>, scenario: RID): List<Long> {
        return ObjectCalls.ptrcallWithPlaneListAndRIDArgsRetPackedInt64List(instancesCullConvexBind, singleton, convex, scenario)
    }

    /**
     * Bakes the material data of the Mesh passed in the `base` parameter with optional
     * `material_overrides` to a set of `Image`s of size `image_size`. Returns an array of `Image`s
     * containing material properties as specified in `BakeChannels`.
     *
     * Generated from Godot docs: RenderingServer.bake_render_uv2
     */
    @JvmStatic
    fun bakeRenderUv2(base: RID, materialOverrides: List<RID>, imageSize: Vector2i): List<Image> {
        return ObjectCalls.ptrcallWithRIDRIDListVector2iArgsRetTypedObjectList(bakeRenderUv2Bind, singleton, base, materialOverrides, imageSize, Image::wrap)
    }

    /**
     * Creates a canvas and returns the assigned `RID`. It can be accessed with the RID that is
     * returned. This RID will be used in all `canvas_*` RenderingServer functions. Once finished with
     * your RID, you will want to free the RID using the RenderingServer's `free_rid` method. Canvas
     * has no `Resource` or `Node` equivalent.
     *
     * Generated from Godot docs: RenderingServer.canvas_create
     */
    @JvmStatic
    fun canvasCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(canvasCreateBind, singleton)
    }

    /**
     * A copy of the canvas item will be drawn with a local offset of the `mirroring`. Note: This is
     * equivalent to calling `canvas_set_item_repeat` like `canvas_set_item_repeat(item, mirroring,
     * 1)`, with an additional check ensuring `canvas` is a parent of `item`.
     *
     * Generated from Godot docs: RenderingServer.canvas_set_item_mirroring
     */
    @JvmStatic
    fun canvasSetItemMirroring(canvas: RID, item: RID, mirroring: Vector2) {
        ObjectCalls.ptrcallWithTwoRIDAndVector2Arg(canvasSetItemMirroringBind, singleton, canvas, item, mirroring)
    }

    /**
     * A copy of the canvas item will be drawn with a local offset of the `repeat_size` by the number
     * of times of the `repeat_times`. As the `repeat_times` increases, the copies will spread away
     * from the origin texture.
     *
     * Generated from Godot docs: RenderingServer.canvas_set_item_repeat
     */
    @JvmStatic
    fun canvasSetItemRepeat(item: RID, repeatSize: Vector2, repeatTimes: Int) {
        ObjectCalls.ptrcallWithRIDVector2IntArgs(canvasSetItemRepeatBind, singleton, item, repeatSize, repeatTimes)
    }

    /**
     * Modulates all colors in the given canvas.
     *
     * Generated from Godot docs: RenderingServer.canvas_set_modulate
     */
    @JvmStatic
    fun canvasSetModulate(canvas: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(canvasSetModulateBind, singleton, canvas, color)
    }

    /**
     * If `disable` is `true`, makes 2D rendering ignore the canvas scale defined for each canvas
     * layer. This affects `CanvasLayer`s with the `CanvasLayer.follow_viewport_enabled` property set
     * to `true`. In the editor, this is set to `true` by default, and set to `false` when View >
     * Preview Canvas Scale is enabled at the top of the 2D editor viewport. Note: Setting this to
     * `true` does not impact the behavior of `CanvasLayer.scale`, `Node2D.scale`, or `Control.scale`.
     *
     * Generated from Godot docs: RenderingServer.canvas_set_disable_scale
     */
    @JvmStatic
    fun canvasSetDisableScale(disable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(canvasSetDisableScaleBind, singleton, disable)
    }

    /**
     * Creates a canvas texture and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID will be used in all `canvas_texture_*` RenderingServer functions.
     * Once finished with your RID, you will want to free the RID using the RenderingServer's
     * `free_rid` method. See also `texture_2d_create`. Note: The equivalent resource is
     * `CanvasTexture` and is only meant to be used in 2D rendering, not 3D.
     *
     * Generated from Godot docs: RenderingServer.canvas_texture_create
     */
    @JvmStatic
    fun canvasTextureCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(canvasTextureCreateBind, singleton)
    }

    /**
     * Sets the `channel`'s `texture` for the canvas texture specified by the `canvas_texture` RID.
     * Equivalent to `CanvasTexture.diffuse_texture`, `CanvasTexture.normal_texture` and
     * `CanvasTexture.specular_texture`.
     *
     * Generated from Godot docs: RenderingServer.canvas_texture_set_channel
     */
    @JvmStatic
    fun canvasTextureSetChannel(canvasTexture: RID, channel: RenderingServer.CanvasTextureChannel, texture: RID) {
        ObjectCalls.ptrcallWithRIDLongAndRIDArgs(canvasTextureSetChannelBind, singleton, canvasTexture, channel.value, texture)
    }

    /**
     * Sets the `base_color` and `shininess` to use for the canvas texture specified by the
     * `canvas_texture` RID. Equivalent to `CanvasTexture.specular_color` and
     * `CanvasTexture.specular_shininess`.
     *
     * Generated from Godot docs: RenderingServer.canvas_texture_set_shading_parameters
     */
    @JvmStatic
    fun canvasTextureSetShadingParameters(canvasTexture: RID, baseColor: Color, shininess: Double) {
        ObjectCalls.ptrcallWithRIDColorDoubleArgs(canvasTextureSetShadingParametersBind, singleton, canvasTexture, baseColor, shininess)
    }

    /**
     * Sets the texture `filter` mode to use for the canvas texture specified by the `canvas_texture`
     * RID.
     *
     * Generated from Godot docs: RenderingServer.canvas_texture_set_texture_filter
     */
    @JvmStatic
    fun canvasTextureSetTextureFilter(canvasTexture: RID, filter: RenderingServer.CanvasItemTextureFilter) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasTextureSetTextureFilterBind, singleton, canvasTexture, filter.value)
    }

    /**
     * Sets the texture `repeat` mode to use for the canvas texture specified by the `canvas_texture`
     * RID.
     *
     * Generated from Godot docs: RenderingServer.canvas_texture_set_texture_repeat
     */
    @JvmStatic
    fun canvasTextureSetTextureRepeat(canvasTexture: RID, repeat: RenderingServer.CanvasItemTextureRepeat) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasTextureSetTextureRepeatBind, singleton, canvasTexture, repeat.value)
    }

    /**
     * Creates a new CanvasItem instance and returns its `RID`. It can be accessed with the RID that is
     * returned. This RID will be used in all `canvas_item_*` RenderingServer functions. Once finished
     * with your RID, you will want to free the RID using the RenderingServer's `free_rid` method.
     * Note: The equivalent node is `CanvasItem`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_create
     */
    @JvmStatic
    fun canvasItemCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(canvasItemCreateBind, singleton)
    }

    /**
     * Sets a parent `CanvasItem` to the `CanvasItem`. The item will inherit transform, modulation and
     * visibility from its parent, like `CanvasItem` nodes in the scene tree.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_parent
     */
    @JvmStatic
    fun canvasItemSetParent(item: RID, parent: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(canvasItemSetParentBind, singleton, item, parent)
    }

    /**
     * Sets the default texture filter mode for the canvas item specified by the `item` RID. Equivalent
     * to `CanvasItem.texture_filter`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_default_texture_filter
     */
    @JvmStatic
    fun canvasItemSetDefaultTextureFilter(item: RID, filter: RenderingServer.CanvasItemTextureFilter) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasItemSetDefaultTextureFilterBind, singleton, item, filter.value)
    }

    /**
     * Sets the default texture repeat mode for the canvas item specified by the `item` RID. Equivalent
     * to `CanvasItem.texture_repeat`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_default_texture_repeat
     */
    @JvmStatic
    fun canvasItemSetDefaultTextureRepeat(item: RID, repeat: RenderingServer.CanvasItemTextureRepeat) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasItemSetDefaultTextureRepeatBind, singleton, item, repeat.value)
    }

    /**
     * Sets the visibility of the `CanvasItem`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_visible
     */
    @JvmStatic
    fun canvasItemSetVisible(item: RID, visible: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetVisibleBind, singleton, item, visible)
    }

    /**
     * Sets the light `mask` for the canvas item specified by the `item` RID. Equivalent to
     * `CanvasItem.light_mask`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_light_mask
     */
    @JvmStatic
    fun canvasItemSetLightMask(item: RID, mask: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(canvasItemSetLightMaskBind, singleton, item, mask)
    }

    /**
     * Sets the rendering visibility layer associated with this `CanvasItem`. Only `Viewport` nodes
     * with a matching rendering mask will render this `CanvasItem`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_visibility_layer
     */
    @JvmStatic
    fun canvasItemSetVisibilityLayer(item: RID, visibilityLayer: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(canvasItemSetVisibilityLayerBind, singleton, item, visibilityLayer)
    }

    /**
     * Sets the `transform` of the canvas item specified by the `item` RID. This affects where and how
     * the item will be drawn. Child canvas items' transforms are multiplied by their parent's
     * transform. Equivalent to `Node2D.transform`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_transform
     */
    @JvmStatic
    fun canvasItemSetTransform(item: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(canvasItemSetTransformBind, singleton, item, transform)
    }

    /**
     * If `clip` is `true`, makes the canvas item specified by the `item` RID not draw anything outside
     * of its rect's coordinates. This clipping is fast, but works only with axis-aligned rectangles.
     * This means that rotation is ignored by the clipping rectangle. For more advanced clipping
     * shapes, use `canvas_item_set_canvas_group_mode` instead. Note: The equivalent node functionality
     * is found in `Label.clip_text`, `RichTextLabel` (always enabled) and more.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_clip
     */
    @JvmStatic
    fun canvasItemSetClip(item: RID, clip: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetClipBind, singleton, item, clip)
    }

    /**
     * If `enabled` is `true`, enables multichannel signed distance field rendering mode for the canvas
     * item specified by the `item` RID. This is meant to be used for font rendering, or with specially
     * generated images using msdfgen (https://github.com/Chlumsky/msdfgen).
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_distance_field_mode
     */
    @JvmStatic
    fun canvasItemSetDistanceFieldMode(item: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetDistanceFieldModeBind, singleton, item, enabled)
    }

    /**
     * If `use_custom_rect` is `true`, sets the custom visibility rectangle (used for culling) to
     * `rect` for the canvas item specified by `item`. Setting a custom visibility rect can reduce CPU
     * load when drawing lots of 2D instances. If `use_custom_rect` is `false`, automatically computes
     * a visibility rectangle based on the canvas item's draw commands.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_custom_rect
     */
    @JvmStatic
    fun canvasItemSetCustomRect(item: RID, useCustomRect: Boolean, rect: Rect2) {
        ObjectCalls.ptrcallWithRIDBoolRect2Args(canvasItemSetCustomRectBind, singleton, item, useCustomRect, rect)
    }

    /**
     * Multiplies the color of the canvas item specified by the `item` RID, while affecting its
     * children. See also `canvas_item_set_self_modulate`. Equivalent to `CanvasItem.modulate`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_modulate
     */
    @JvmStatic
    fun canvasItemSetModulate(item: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(canvasItemSetModulateBind, singleton, item, color)
    }

    /**
     * Multiplies the color of the canvas item specified by the `item` RID, without affecting its
     * children. See also `canvas_item_set_modulate`. Equivalent to `CanvasItem.self_modulate`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_self_modulate
     */
    @JvmStatic
    fun canvasItemSetSelfModulate(item: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(canvasItemSetSelfModulateBind, singleton, item, color)
    }

    /**
     * If `enabled` is `true`, draws the canvas item specified by the `item` RID behind its parent.
     * Equivalent to `CanvasItem.show_behind_parent`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_draw_behind_parent
     */
    @JvmStatic
    fun canvasItemSetDrawBehindParent(item: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetDrawBehindParentBind, singleton, item, enabled)
    }

    /**
     * If `interpolated` is `true`, turns on physics interpolation for the canvas item.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_interpolated
     */
    @JvmStatic
    fun canvasItemSetInterpolated(item: RID, interpolated: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetInterpolatedBind, singleton, item, interpolated)
    }

    /**
     * Prevents physics interpolation for the current physics tick. This is useful when moving a canvas
     * item to a new location, to give an instantaneous change rather than interpolation from the
     * previous location.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_reset_physics_interpolation
     */
    @JvmStatic
    fun canvasItemResetPhysicsInterpolation(item: RID) {
        ObjectCalls.ptrcallWithRIDArg(canvasItemResetPhysicsInterpolationBind, singleton, item)
    }

    /**
     * Transforms both the current and previous stored transform for a canvas item. This allows
     * transforming a canvas item without creating a "glitch" in the interpolation, which is
     * particularly useful for large worlds utilizing a shifting origin.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_transform_physics_interpolation
     */
    @JvmStatic
    fun canvasItemTransformPhysicsInterpolation(item: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(canvasItemTransformPhysicsInterpolationBind, singleton, item, transform)
    }

    /**
     * Draws a line on the `CanvasItem` pointed to by the `item` `RID`. See also
     * `CanvasItem.draw_line`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_line
     */
    @JvmStatic
    fun canvasItemAddLine(item: RID, from: Vector2, to: Vector2, color: Color, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithRIDTwoVector2ColorDoubleBoolArgs(canvasItemAddLineBind, singleton, item, from, to, color, width, antialiased)
    }

    /**
     * Draws a 2D polyline on the `CanvasItem` pointed to by the `item` `RID`. See also
     * `CanvasItem.draw_polyline` and `CanvasItem.draw_polyline_colors`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_polyline
     */
    @JvmStatic
    fun canvasItemAddPolyline(item: RID, points: List<Vector2>, colors: List<Color>, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithRIDPackedVector2ListPackedColorListDoubleAndBoolArgs(canvasItemAddPolylineBind, singleton, item, points, colors, width, antialiased)
    }

    /**
     * Draws a 2D multiline on the `CanvasItem` pointed to by the `item` `RID`. See also
     * `CanvasItem.draw_multiline` and `CanvasItem.draw_multiline_colors`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_multiline
     */
    @JvmStatic
    fun canvasItemAddMultiline(item: RID, points: List<Vector2>, colors: List<Color>, width: Double = -1.0, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithRIDPackedVector2ListPackedColorListDoubleAndBoolArgs(canvasItemAddMultilineBind, singleton, item, points, colors, width, antialiased)
    }

    /**
     * Draws a rectangle on the `CanvasItem` pointed to by the `item` `RID`. See also
     * `CanvasItem.draw_rect`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_rect
     */
    @JvmStatic
    fun canvasItemAddRect(item: RID, rect: Rect2, color: Color, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithRIDRect2ColorBoolArgs(canvasItemAddRectBind, singleton, item, rect, color, antialiased)
    }

    /**
     * Draws a circle on the `CanvasItem` pointed to by the `item` `RID`. See also
     * `CanvasItem.draw_circle`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_circle
     */
    @JvmStatic
    fun canvasItemAddCircle(item: RID, pos: Vector2, radius: Double, color: Color, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithRIDVector2DoubleColorBoolArgs(canvasItemAddCircleBind, singleton, item, pos, radius, color, antialiased)
    }

    /**
     * Draws an ellipse with semi-major axis `major` and semi-minor axis `minor` on the `CanvasItem`
     * pointed to by the `item` `RID`. See also `CanvasItem.draw_ellipse`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_ellipse
     */
    @JvmStatic
    fun canvasItemAddEllipse(item: RID, pos: Vector2, major: Double, minor: Double, color: Color, antialiased: Boolean = false) {
        ObjectCalls.ptrcallWithRIDVector2TwoDoubleColorBoolArgs(canvasItemAddEllipseBind, singleton, item, pos, major, minor, color, antialiased)
    }

    /**
     * Draws a 2D textured rectangle on the `CanvasItem` pointed to by the `item` `RID`. See also
     * `CanvasItem.draw_texture_rect` and `Texture2D.draw_rect`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_texture_rect
     */
    @JvmStatic
    fun canvasItemAddTextureRect(item: RID, rect: Rect2, texture: RID, tile: Boolean = false, modulate: Color, transpose: Boolean = false) {
        ObjectCalls.ptrcallWithRIDRect2RIDBoolColorBoolArgs(canvasItemAddTextureRectBind, singleton, item, rect, texture, tile, modulate, transpose)
    }

    /**
     * See also `CanvasItem.draw_msdf_texture_rect_region`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_msdf_texture_rect_region
     */
    @JvmStatic
    fun canvasItemAddMsdfTextureRectRegion(item: RID, rect: Rect2, texture: RID, srcRect: Rect2, modulate: Color, outlineSize: Int = 0, pxRange: Double = 1.0, scale: Double = 1.0) {
        ObjectCalls.ptrcallWithRIDRect2RIDRect2ColorIntTwoDoubleArgs(canvasItemAddMsdfTextureRectRegionBind, singleton, item, rect, texture, srcRect, modulate, outlineSize, pxRange, scale)
    }

    /**
     * See also `CanvasItem.draw_lcd_texture_rect_region`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_lcd_texture_rect_region
     */
    @JvmStatic
    fun canvasItemAddLcdTextureRectRegion(item: RID, rect: Rect2, texture: RID, srcRect: Rect2, modulate: Color) {
        ObjectCalls.ptrcallWithRIDRect2RIDRect2ColorArgs(canvasItemAddLcdTextureRectRegionBind, singleton, item, rect, texture, srcRect, modulate)
    }

    /**
     * Draws the specified region of a 2D textured rectangle on the `CanvasItem` pointed to by the
     * `item` `RID`. See also `CanvasItem.draw_texture_rect_region` and `Texture2D.draw_rect_region`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_texture_rect_region
     */
    @JvmStatic
    fun canvasItemAddTextureRectRegion(item: RID, rect: Rect2, texture: RID, srcRect: Rect2, modulate: Color, transpose: Boolean = false, clipUv: Boolean = true) {
        ObjectCalls.ptrcallWithRIDRect2RIDRect2ColorTwoBoolArgs(canvasItemAddTextureRectRegionBind, singleton, item, rect, texture, srcRect, modulate, transpose, clipUv)
    }

    /**
     * Draws a nine-patch rectangle on the `CanvasItem` pointed to by the `item` `RID`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_nine_patch
     */
    @JvmStatic
    fun canvasItemAddNinePatch(item: RID, rect: Rect2, source: Rect2, texture: RID, topleft: Vector2, bottomright: Vector2, xAxisMode: RenderingServer.NinePatchAxisMode = RenderingServer.NinePatchAxisMode.STRETCH, yAxisMode: RenderingServer.NinePatchAxisMode = RenderingServer.NinePatchAxisMode.STRETCH, drawCenter: Boolean = true, modulate: Color) {
        ObjectCalls.ptrcallWithRIDTwoRect2RIDTwoVector2TwoLongBoolColorArgs(canvasItemAddNinePatchBind, singleton, item, rect, source, texture, topleft, bottomright, xAxisMode.value, yAxisMode.value, drawCenter, modulate)
    }

    /**
     * Draws a 2D primitive on the `CanvasItem` pointed to by the `item` `RID`. See also
     * `CanvasItem.draw_primitive`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_primitive
     */
    @JvmStatic
    fun canvasItemAddPrimitive(item: RID, points: List<Vector2>, colors: List<Color>, uvs: List<Vector2>, texture: RID) {
        ObjectCalls.ptrcallWithRIDPackedVector2ListPackedColorListPackedVector2ListAndRIDArgs(canvasItemAddPrimitiveBind, singleton, item, points, colors, uvs, texture)
    }

    /**
     * Draws a 2D polygon on the `CanvasItem` pointed to by the `item` `RID`. If you need more
     * flexibility (such as being able to use bones), use `canvas_item_add_triangle_array` instead. See
     * also `CanvasItem.draw_polygon`. Note: If you frequently redraw the same polygon with a large
     * number of vertices, consider pre-calculating the triangulation with
     * `Geometry2D.triangulate_polygon` and using `CanvasItem.draw_mesh`, `CanvasItem.draw_multimesh`,
     * or `canvas_item_add_triangle_array`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_polygon
     */
    @JvmStatic
    fun canvasItemAddPolygon(item: RID, points: List<Vector2>, colors: List<Color>, uvs: List<Vector2>, texture: RID) {
        ObjectCalls.ptrcallWithRIDPackedVector2ListPackedColorListPackedVector2ListAndRIDArgs(canvasItemAddPolygonBind, singleton, item, points, colors, uvs, texture)
    }

    /**
     * Draws a triangle array on the `CanvasItem` pointed to by the `item` `RID`. This is internally
     * used by `Line2D` and `StyleBoxFlat` for rendering. `canvas_item_add_triangle_array` is highly
     * flexible, but more complex to use than `canvas_item_add_polygon`. Note: If `count` is set to a
     * non-negative value, only the first `count * 3` indices (corresponding to `count` triangles) will
     * be drawn. Otherwise, all indices are drawn.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_triangle_array
     */
    @JvmStatic
    fun canvasItemAddTriangleArray(item: RID, indices: List<Int>, points: List<Vector2>, colors: List<Color>, uvs: List<Vector2>, bones: List<Int>, weights: List<Float>, texture: RID, count: Int = -1) {
        ObjectCalls.ptrcallWithRIDPackedInt32ListPackedVector2ListPackedColorListPackedVector2ListPackedInt32ListPackedFloat32ListRIDIntArgs(canvasItemAddTriangleArrayBind, singleton, item, indices, points, colors, uvs, bones, weights, texture, count)
    }

    /**
     * Draws a mesh created with `mesh_create` with given `transform`, `modulate` color, and `texture`.
     * This is used internally by `MeshInstance2D`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_mesh
     */
    @JvmStatic
    fun canvasItemAddMesh(item: RID, mesh: RID, transform: Transform2D, modulate: Color, texture: RID) {
        ObjectCalls.ptrcallWithTwoRIDTransform2DColorRIDArgs(canvasItemAddMeshBind, singleton, item, mesh, transform, modulate, texture)
    }

    /**
     * Draws a 2D `MultiMesh` on the `CanvasItem` pointed to by the `item` `RID`. See also
     * `CanvasItem.draw_multimesh`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_multimesh
     */
    @JvmStatic
    fun canvasItemAddMultimesh(item: RID, mesh: RID, texture: RID) {
        ObjectCalls.ptrcallWithThreeRIDArgs(canvasItemAddMultimeshBind, singleton, item, mesh, texture)
    }

    /**
     * Draws particles on the `CanvasItem` pointed to by the `item` `RID`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_particles
     */
    @JvmStatic
    fun canvasItemAddParticles(item: RID, particles: RID, texture: RID) {
        ObjectCalls.ptrcallWithThreeRIDArgs(canvasItemAddParticlesBind, singleton, item, particles, texture)
    }

    /**
     * Sets a `Transform2D` that will be used to transform subsequent canvas item commands.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_set_transform
     */
    @JvmStatic
    fun canvasItemAddSetTransform(item: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(canvasItemAddSetTransformBind, singleton, item, transform)
    }

    /**
     * If `ignore` is `true`, ignore clipping on items drawn with this canvas item until this is called
     * again with `ignore` set to `false`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_clip_ignore
     */
    @JvmStatic
    fun canvasItemAddClipIgnore(item: RID, ignore: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemAddClipIgnoreBind, singleton, item, ignore)
    }

    /**
     * Subsequent drawing commands will be ignored unless they fall within the specified animation
     * slice. This is a faster way to implement animations that loop on background rather than
     * redrawing constantly.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_add_animation_slice
     */
    @JvmStatic
    fun canvasItemAddAnimationSlice(item: RID, animationLength: Double, sliceBegin: Double, sliceEnd: Double, offset: Double = 0.0) {
        ObjectCalls.ptrcallWithRIDFourDoubleArgs(canvasItemAddAnimationSliceBind, singleton, item, animationLength, sliceBegin, sliceEnd, offset)
    }

    /**
     * If `enabled` is `true`, child nodes with the lowest Y position are drawn before those with a
     * higher Y position. Y-sorting only affects children that inherit from the canvas item specified
     * by the `item` RID, not the canvas item itself. Equivalent to `CanvasItem.y_sort_enabled`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_sort_children_by_y
     */
    @JvmStatic
    fun canvasItemSetSortChildrenByY(item: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetSortChildrenByYBind, singleton, item, enabled)
    }

    /**
     * Sets the `CanvasItem`'s Z index, i.e. its draw order (lower indexes are drawn first).
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_z_index
     */
    @JvmStatic
    fun canvasItemSetZIndex(item: RID, zIndex: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(canvasItemSetZIndexBind, singleton, item, zIndex)
    }

    /**
     * If this is enabled, the Z index of the parent will be added to the children's Z index.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_z_as_relative_to_parent
     */
    @JvmStatic
    fun canvasItemSetZAsRelativeToParent(item: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetZAsRelativeToParentBind, singleton, item, enabled)
    }

    /**
     * Sets the `CanvasItem` to copy a rect to the backbuffer.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_copy_to_backbuffer
     */
    @JvmStatic
    fun canvasItemSetCopyToBackbuffer(item: RID, enabled: Boolean, rect: Rect2) {
        ObjectCalls.ptrcallWithRIDBoolRect2Args(canvasItemSetCopyToBackbufferBind, singleton, item, enabled, rect)
    }

    /**
     * Attaches a skeleton to the `CanvasItem`. Removes the previous skeleton.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_attach_skeleton
     */
    @JvmStatic
    fun canvasItemAttachSkeleton(item: RID, skeleton: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(canvasItemAttachSkeletonBind, singleton, item, skeleton)
    }

    /**
     * Clears the `CanvasItem` and removes all commands in it.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_clear
     */
    @JvmStatic
    fun canvasItemClear(item: RID) {
        ObjectCalls.ptrcallWithRIDArg(canvasItemClearBind, singleton, item)
    }

    /**
     * Sets the index for the `CanvasItem`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_draw_index
     */
    @JvmStatic
    fun canvasItemSetDrawIndex(item: RID, index: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(canvasItemSetDrawIndexBind, singleton, item, index)
    }

    /**
     * Sets a new `material` to the canvas item specified by the `item` RID. Equivalent to
     * `CanvasItem.material`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_material
     */
    @JvmStatic
    fun canvasItemSetMaterial(item: RID, material: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(canvasItemSetMaterialBind, singleton, item, material)
    }

    /**
     * Sets if the `CanvasItem` uses its parent's material.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_use_parent_material
     */
    @JvmStatic
    fun canvasItemSetUseParentMaterial(item: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasItemSetUseParentMaterialBind, singleton, item, enabled)
    }

    /**
     * Sets the per-instance shader uniform on the specified canvas item instance. Equivalent to
     * `CanvasItem.set_instance_shader_parameter`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_instance_shader_parameter
     */
    @JvmStatic
    fun canvasItemSetInstanceShaderParameter(instance: RID, parameter: String, value: Any?) {
        ObjectCalls.ptrcallWithRIDStringNameAndVariantArgs(canvasItemSetInstanceShaderParameterBind, singleton, instance, parameter, value)
    }

    /**
     * Returns the value of the per-instance shader uniform from the specified canvas item instance.
     * Equivalent to `CanvasItem.get_instance_shader_parameter`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_get_instance_shader_parameter
     */
    @JvmStatic
    fun canvasItemGetInstanceShaderParameter(instance: RID, parameter: String): Any? {
        return ObjectCalls.ptrcallWithRIDAndStringNameArgRetVariantScalar(canvasItemGetInstanceShaderParameterBind, singleton, instance, parameter)
    }

    /**
     * Returns the default value of the per-instance shader uniform from the specified canvas item
     * instance. Equivalent to `CanvasItem.get_instance_shader_parameter`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_get_instance_shader_parameter_default_value
     */
    @JvmStatic
    fun canvasItemGetInstanceShaderParameterDefaultValue(instance: RID, parameter: String): Any? {
        return ObjectCalls.ptrcallWithRIDAndStringNameArgRetVariantScalar(canvasItemGetInstanceShaderParameterDefaultValueBind, singleton, instance, parameter)
    }

    /**
     * Returns a dictionary of per-instance shader uniform names of the per-instance shader uniform
     * from the specified canvas item instance. The returned dictionary is in PropertyInfo format, with
     * the keys `name`, `class_name`, `type`, `hint`, `hint_string`, and `usage`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_get_instance_shader_parameter_list
     */
    @JvmStatic
    fun canvasItemGetInstanceShaderParameterList(instance: RID): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithRIDArgRetDictionaryList(canvasItemGetInstanceShaderParameterListBind, singleton, instance)
    }

    /**
     * Sets the given `CanvasItem` as visibility notifier. `area` defines the area of detecting
     * visibility. `enter_callable` is called when the `CanvasItem` enters the screen, `exit_callable`
     * is called when the `CanvasItem` exits the screen. If `enable` is `false`, the item will no
     * longer function as notifier. This method can be used to manually mimic
     * `VisibleOnScreenNotifier2D`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_visibility_notifier
     */
    @JvmStatic
    fun canvasItemSetVisibilityNotifier(item: RID, enable: Boolean, area: Rect2, enterCallable: GodotCallable, exitCallable: GodotCallable) {
        ObjectCalls.ptrcallWithRIDBoolRect2TwoCallableArgs(canvasItemSetVisibilityNotifierBind, singleton, item, enable, area, enterCallable.target.segment, enterCallable.method, exitCallable.target.segment, exitCallable.method)
    }

    /**
     * Sets the canvas group mode used during 2D rendering for the canvas item specified by the `item`
     * RID. For faster but more limited clipping, use `canvas_item_set_clip` instead. Note: The
     * equivalent node functionality is found in `CanvasGroup` and `CanvasItem.clip_children`.
     *
     * Generated from Godot docs: RenderingServer.canvas_item_set_canvas_group_mode
     */
    @JvmStatic
    fun canvasItemSetCanvasGroupMode(item: RID, mode: RenderingServer.CanvasGroupMode, clearMargin: Double = 5.0, fitEmpty: Boolean = false, fitMargin: Double = 0.0, blurMipmaps: Boolean = false) {
        ObjectCalls.ptrcallWithRIDLongDoubleBoolDoubleBoolArgs(canvasItemSetCanvasGroupModeBind, singleton, item, mode.value, clearMargin, fitEmpty, fitMargin, blurMipmaps)
    }

    /**
     * Returns the bounding rectangle for a canvas item in local space, as calculated by the renderer.
     * This bound is used internally for culling. Warning: This function is intended for debugging in
     * the editor, and will pass through and return a zero `Rect2` in exported projects.
     *
     * Generated from Godot docs: RenderingServer.debug_canvas_item_get_rect
     */
    @JvmStatic
    fun debugCanvasItemGetRect(item: RID): Rect2 {
        return ObjectCalls.ptrcallWithRIDArgRetRect2(debugCanvasItemGetRectBind, singleton, item)
    }

    /**
     * Creates a canvas light and adds it to the RenderingServer. It can be accessed with the RID that
     * is returned. This RID will be used in all `canvas_light_*` RenderingServer functions. Once
     * finished with your RID, you will want to free the RID using the RenderingServer's `free_rid`
     * method. Note: The equivalent node is `Light2D`.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_create
     */
    @JvmStatic
    fun canvasLightCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(canvasLightCreateBind, singleton)
    }

    /**
     * Attaches the canvas light to the canvas. Removes it from its previous canvas.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_attach_to_canvas
     */
    @JvmStatic
    fun canvasLightAttachToCanvas(light: RID, canvas: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(canvasLightAttachToCanvasBind, singleton, light, canvas)
    }

    /**
     * Enables or disables a canvas light.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_enabled
     */
    @JvmStatic
    fun canvasLightSetEnabled(light: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasLightSetEnabledBind, singleton, light, enabled)
    }

    /**
     * Sets the scale factor of a `PointLight2D`'s texture. Equivalent to `PointLight2D.texture_scale`.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_texture_scale
     */
    @JvmStatic
    fun canvasLightSetTextureScale(light: RID, scale: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(canvasLightSetTextureScaleBind, singleton, light, scale)
    }

    /**
     * Sets the canvas light's `Transform2D`.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_transform
     */
    @JvmStatic
    fun canvasLightSetTransform(light: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(canvasLightSetTransformBind, singleton, light, transform)
    }

    /**
     * Sets the texture to be used by a `PointLight2D`. Equivalent to `PointLight2D.texture`.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_texture
     */
    @JvmStatic
    fun canvasLightSetTexture(light: RID, texture: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(canvasLightSetTextureBind, singleton, light, texture)
    }

    /**
     * Sets the offset of a `PointLight2D`'s texture. Equivalent to `PointLight2D.offset`.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_texture_offset
     */
    @JvmStatic
    fun canvasLightSetTextureOffset(light: RID, offset: Vector2) {
        ObjectCalls.ptrcallWithRIDAndVector2Arg(canvasLightSetTextureOffsetBind, singleton, light, offset)
    }

    /**
     * Sets the color for a light.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_color
     */
    @JvmStatic
    fun canvasLightSetColor(light: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(canvasLightSetColorBind, singleton, light, color)
    }

    /**
     * Sets a canvas light's height.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_height
     */
    @JvmStatic
    fun canvasLightSetHeight(light: RID, height: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(canvasLightSetHeightBind, singleton, light, height)
    }

    /**
     * Sets a canvas light's energy.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_energy
     */
    @JvmStatic
    fun canvasLightSetEnergy(light: RID, energy: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(canvasLightSetEnergyBind, singleton, light, energy)
    }

    /**
     * Sets the Z range of objects that will be affected by this light. Equivalent to
     * `Light2D.range_z_min` and `Light2D.range_z_max`.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_z_range
     */
    @JvmStatic
    fun canvasLightSetZRange(light: RID, minZ: Int, maxZ: Int) {
        ObjectCalls.ptrcallWithRIDAndTwoIntArgs(canvasLightSetZRangeBind, singleton, light, minZ, maxZ)
    }

    /**
     * The layer range that gets rendered with this light.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_layer_range
     */
    @JvmStatic
    fun canvasLightSetLayerRange(light: RID, minLayer: Int, maxLayer: Int) {
        ObjectCalls.ptrcallWithRIDAndTwoIntArgs(canvasLightSetLayerRangeBind, singleton, light, minLayer, maxLayer)
    }

    /**
     * The light mask. See `LightOccluder2D` for more information on light masks.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_item_cull_mask
     */
    @JvmStatic
    fun canvasLightSetItemCullMask(light: RID, mask: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(canvasLightSetItemCullMaskBind, singleton, light, mask)
    }

    /**
     * The binary mask used to determine which layers this canvas light's shadows affects. See
     * `LightOccluder2D` for more information on light masks.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_item_shadow_cull_mask
     */
    @JvmStatic
    fun canvasLightSetItemShadowCullMask(light: RID, mask: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(canvasLightSetItemShadowCullMaskBind, singleton, light, mask)
    }

    /**
     * Sets the mode of the canvas light.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_mode
     */
    @JvmStatic
    fun canvasLightSetMode(light: RID, mode: RenderingServer.CanvasLightMode) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasLightSetModeBind, singleton, light, mode.value)
    }

    /**
     * Enables or disables the canvas light's shadow.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_shadow_enabled
     */
    @JvmStatic
    fun canvasLightSetShadowEnabled(light: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasLightSetShadowEnabledBind, singleton, light, enabled)
    }

    /**
     * Sets the canvas light's shadow's filter.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_shadow_filter
     */
    @JvmStatic
    fun canvasLightSetShadowFilter(light: RID, filter: RenderingServer.CanvasLightShadowFilter) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasLightSetShadowFilterBind, singleton, light, filter.value)
    }

    /**
     * Sets the color of the canvas light's shadow.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_shadow_color
     */
    @JvmStatic
    fun canvasLightSetShadowColor(light: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(canvasLightSetShadowColorBind, singleton, light, color)
    }

    /**
     * Smoothens the shadow. The lower, the smoother.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_shadow_smooth
     */
    @JvmStatic
    fun canvasLightSetShadowSmooth(light: RID, smooth: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(canvasLightSetShadowSmoothBind, singleton, light, smooth)
    }

    /**
     * Sets the blend mode for the given canvas light to `mode`. Equivalent to `Light2D.blend_mode`.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_blend_mode
     */
    @JvmStatic
    fun canvasLightSetBlendMode(light: RID, mode: RenderingServer.CanvasLightBlendMode) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasLightSetBlendModeBind, singleton, light, mode.value)
    }

    /**
     * If `interpolated` is `true`, turns on physics interpolation for the canvas light.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_set_interpolated
     */
    @JvmStatic
    fun canvasLightSetInterpolated(light: RID, interpolated: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasLightSetInterpolatedBind, singleton, light, interpolated)
    }

    /**
     * Prevents physics interpolation for the current physics tick. This is useful when moving a canvas
     * item to a new location, to give an instantaneous change rather than interpolation from the
     * previous location.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_reset_physics_interpolation
     */
    @JvmStatic
    fun canvasLightResetPhysicsInterpolation(light: RID) {
        ObjectCalls.ptrcallWithRIDArg(canvasLightResetPhysicsInterpolationBind, singleton, light)
    }

    /**
     * Transforms both the current and previous stored transform for a canvas light. This allows
     * transforming a light without creating a "glitch" in the interpolation, which is particularly
     * useful for large worlds utilizing a shifting origin.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_transform_physics_interpolation
     */
    @JvmStatic
    fun canvasLightTransformPhysicsInterpolation(light: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(canvasLightTransformPhysicsInterpolationBind, singleton, light, transform)
    }

    /**
     * Creates a light occluder and adds it to the RenderingServer. It can be accessed with the RID
     * that is returned. This RID will be used in all `canvas_light_occluder_*` RenderingServer
     * functions. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method. Note: The equivalent node is `LightOccluder2D`.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_occluder_create
     */
    @JvmStatic
    fun canvasLightOccluderCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(canvasLightOccluderCreateBind, singleton)
    }

    /**
     * Attaches a light occluder to the canvas. Removes it from its previous canvas.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_occluder_attach_to_canvas
     */
    @JvmStatic
    fun canvasLightOccluderAttachToCanvas(occluder: RID, canvas: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(canvasLightOccluderAttachToCanvasBind, singleton, occluder, canvas)
    }

    /**
     * Enables or disables light occluder.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_occluder_set_enabled
     */
    @JvmStatic
    fun canvasLightOccluderSetEnabled(occluder: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasLightOccluderSetEnabledBind, singleton, occluder, enabled)
    }

    /**
     * Sets a light occluder's polygon.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_occluder_set_polygon
     */
    @JvmStatic
    fun canvasLightOccluderSetPolygon(occluder: RID, polygon: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(canvasLightOccluderSetPolygonBind, singleton, occluder, polygon)
    }

    /**
     * Enables or disables using the light occluder as a signed distance field for 2D particle
     * collision.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_occluder_set_as_sdf_collision
     */
    @JvmStatic
    fun canvasLightOccluderSetAsSdfCollision(occluder: RID, enable: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasLightOccluderSetAsSdfCollisionBind, singleton, occluder, enable)
    }

    /**
     * Sets a light occluder's `Transform2D`.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_occluder_set_transform
     */
    @JvmStatic
    fun canvasLightOccluderSetTransform(occluder: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(canvasLightOccluderSetTransformBind, singleton, occluder, transform)
    }

    /**
     * The light mask. See `LightOccluder2D` for more information on light masks.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_occluder_set_light_mask
     */
    @JvmStatic
    fun canvasLightOccluderSetLightMask(occluder: RID, mask: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(canvasLightOccluderSetLightMaskBind, singleton, occluder, mask)
    }

    /**
     * If `interpolated` is `true`, turns on physics interpolation for the light occluder.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_occluder_set_interpolated
     */
    @JvmStatic
    fun canvasLightOccluderSetInterpolated(occluder: RID, interpolated: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(canvasLightOccluderSetInterpolatedBind, singleton, occluder, interpolated)
    }

    /**
     * Prevents physics interpolation for the current physics tick. This is useful when moving an
     * occluder to a new location, to give an instantaneous change rather than interpolation from the
     * previous location.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_occluder_reset_physics_interpolation
     */
    @JvmStatic
    fun canvasLightOccluderResetPhysicsInterpolation(occluder: RID) {
        ObjectCalls.ptrcallWithRIDArg(canvasLightOccluderResetPhysicsInterpolationBind, singleton, occluder)
    }

    /**
     * Transforms both the current and previous stored transform for a light occluder. This allows
     * transforming an occluder without creating a "glitch" in the interpolation, which is particularly
     * useful for large worlds utilizing a shifting origin.
     *
     * Generated from Godot docs: RenderingServer.canvas_light_occluder_transform_physics_interpolation
     */
    @JvmStatic
    fun canvasLightOccluderTransformPhysicsInterpolation(occluder: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(canvasLightOccluderTransformPhysicsInterpolationBind, singleton, occluder, transform)
    }

    /**
     * Creates a new light occluder polygon and adds it to the RenderingServer. It can be accessed with
     * the RID that is returned. This RID will be used in all `canvas_occluder_polygon_*`
     * RenderingServer functions. Once finished with your RID, you will want to free the RID using the
     * RenderingServer's `free_rid` method. Note: The equivalent resource is `OccluderPolygon2D`.
     *
     * Generated from Godot docs: RenderingServer.canvas_occluder_polygon_create
     */
    @JvmStatic
    fun canvasOccluderPolygonCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(canvasOccluderPolygonCreateBind, singleton)
    }

    /**
     * Sets the shape of the occluder polygon.
     *
     * Generated from Godot docs: RenderingServer.canvas_occluder_polygon_set_shape
     */
    @JvmStatic
    fun canvasOccluderPolygonSetShape(occluderPolygon: RID, shape: List<Vector2>, closed: Boolean) {
        ObjectCalls.ptrcallWithRIDPackedVector2ListAndBoolArg(canvasOccluderPolygonSetShapeBind, singleton, occluderPolygon, shape, closed)
    }

    /**
     * Sets an occluder polygon's cull mode.
     *
     * Generated from Godot docs: RenderingServer.canvas_occluder_polygon_set_cull_mode
     */
    @JvmStatic
    fun canvasOccluderPolygonSetCullMode(occluderPolygon: RID, mode: RenderingServer.CanvasOccluderPolygonCullMode) {
        ObjectCalls.ptrcallWithRIDAndLongArg(canvasOccluderPolygonSetCullModeBind, singleton, occluderPolygon, mode.value)
    }

    /**
     * Sets the `ProjectSettings.rendering/2d/shadow_atlas/size` to use for `Light2D` shadow rendering
     * (in pixels). The value is rounded up to the nearest power of 2.
     *
     * Generated from Godot docs: RenderingServer.canvas_set_shadow_texture_size
     */
    @JvmStatic
    fun canvasSetShadowTextureSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(canvasSetShadowTextureSizeBind, singleton, size)
    }

    /**
     * Creates a new global shader uniform. Note: Global shader parameter names are case-sensitive.
     *
     * Generated from Godot docs: RenderingServer.global_shader_parameter_add
     */
    @JvmStatic
    fun globalShaderParameterAdd(name: String, type: RenderingServer.GlobalShaderParameterType, defaultValue: Any?) {
        ObjectCalls.ptrcallWithStringNameLongVariantArgs(globalShaderParameterAddBind, singleton, name, type.value, defaultValue)
    }

    /**
     * Removes the global shader uniform specified by `name`.
     *
     * Generated from Godot docs: RenderingServer.global_shader_parameter_remove
     */
    @JvmStatic
    fun globalShaderParameterRemove(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(globalShaderParameterRemoveBind, singleton, name)
    }

    /**
     * Returns the list of global shader uniform names. Note: `global_shader_parameter_get` has a large
     * performance penalty as the rendering thread needs to synchronize with the calling thread, which
     * is slow. Do not use this method during gameplay to avoid stuttering. If you need to read values
     * in a script after setting them, consider creating an autoload where you store the values you
     * need to query at the same time you're setting them as global parameters.
     *
     * Generated from Godot docs: RenderingServer.global_shader_parameter_get_list
     */
    @JvmStatic
    fun globalShaderParameterGetList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetStringNameList(globalShaderParameterGetListBind, singleton)
    }

    /**
     * Sets the global shader uniform `name` to `value`.
     *
     * Generated from Godot docs: RenderingServer.global_shader_parameter_set
     */
    @JvmStatic
    fun globalShaderParameterSet(name: String, value: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(globalShaderParameterSetBind, singleton, name, value)
    }

    /**
     * Overrides the global shader uniform `name` with `value`. Equivalent to the
     * `ShaderGlobalsOverride` node.
     *
     * Generated from Godot docs: RenderingServer.global_shader_parameter_set_override
     */
    @JvmStatic
    fun globalShaderParameterSetOverride(name: String, value: Any?) {
        ObjectCalls.ptrcallWithStringNameAndVariantArg(globalShaderParameterSetOverrideBind, singleton, name, value)
    }

    /**
     * Returns the value of the global shader uniform specified by `name`. Note:
     * `global_shader_parameter_get` has a large performance penalty as the rendering thread needs to
     * synchronize with the calling thread, which is slow. Do not use this method during gameplay to
     * avoid stuttering. If you need to read values in a script after setting them, consider creating
     * an autoload where you store the values you need to query at the same time you're setting them as
     * global parameters.
     *
     * Generated from Godot docs: RenderingServer.global_shader_parameter_get
     */
    @JvmStatic
    fun globalShaderParameterGet(name: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(globalShaderParameterGetBind, singleton, name)
    }

    /**
     * Returns the type associated to the global shader uniform specified by `name`. Note:
     * `global_shader_parameter_get` has a large performance penalty as the rendering thread needs to
     * synchronize with the calling thread, which is slow. Do not use this method during gameplay to
     * avoid stuttering. If you need to read values in a script after setting them, consider creating
     * an autoload where you store the values you need to query at the same time you're setting them as
     * global parameters.
     *
     * Generated from Godot docs: RenderingServer.global_shader_parameter_get_type
     */
    @JvmStatic
    fun globalShaderParameterGetType(name: String): RenderingServer.GlobalShaderParameterType {
        return RenderingServer.GlobalShaderParameterType(ObjectCalls.ptrcallWithStringNameArgRetLong(globalShaderParameterGetTypeBind, singleton, name))
    }

    /**
     * Tries to free an object in the RenderingServer. To avoid memory leaks, this should be called
     * after using an object as memory management does not occur automatically when using
     * RenderingServer directly.
     *
     * Generated from Godot docs: RenderingServer.free_rid
     */
    @JvmStatic
    fun freeRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(freeRidBind, singleton, rid)
    }

    /**
     * Schedules a callback to the given callable after a frame has been drawn.
     *
     * Generated from Godot docs: RenderingServer.request_frame_drawn_callback
     */
    @JvmStatic
    fun requestFrameDrawnCallback(callable: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(requestFrameDrawnCallbackBind, singleton, callable.target.segment, callable.method)
    }

    /**
     * Returns `true` if changes have been made to the RenderingServer's data. `force_draw` is usually
     * called if this happens.
     *
     * Generated from Godot docs: RenderingServer.has_changed
     */
    @JvmStatic
    fun hasChanged(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasChangedBind, singleton)
    }

    /**
     * Returns a statistic about the rendering engine which can be used for performance profiling. See
     * also `viewport_get_render_info`, which returns information specific to a viewport. Note: Only 3D
     * rendering is currently taken into account by some of these values, such as the number of draw
     * calls. Note: Rendering information is not available until at least 2 frames have been rendered
     * by the engine. If rendering information is not available, `get_rendering_info` returns `0`. To
     * print rendering information in `_ready()` successfully, use the following:
     *
     * Generated from Godot docs: RenderingServer.get_rendering_info
     */
    @JvmStatic
    fun getRenderingInfo(info: RenderingServer.RenderingInfo): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(getRenderingInfoBind, singleton, info.value)
    }

    /**
     * Returns the name of the video adapter (e.g. "GeForce GTX 1080/PCIe/SSE2"). Note: When running a
     * headless or server binary, this function returns an empty string. Note: On the web platform,
     * some browsers such as Firefox may report a different, fixed GPU name such as "GeForce GTX 980"
     * (regardless of the user's actual GPU model). This is done to make fingerprinting more difficult.
     *
     * Generated from Godot docs: RenderingServer.get_video_adapter_name
     */
    @JvmStatic
    fun getVideoAdapterName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getVideoAdapterNameBind, singleton)
    }

    /**
     * Returns the vendor of the video adapter (e.g. "NVIDIA Corporation"). Note: When running a
     * headless or server binary, this function returns an empty string.
     *
     * Generated from Godot docs: RenderingServer.get_video_adapter_vendor
     */
    @JvmStatic
    fun getVideoAdapterVendor(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getVideoAdapterVendorBind, singleton)
    }

    /**
     * Returns the type of the video adapter. Since dedicated graphics cards from a given generation
     * will usually be significantly faster than integrated graphics made in the same generation, the
     * device type can be used as a basis for automatic graphics settings adjustment. However, this is
     * not always true, so make sure to provide users with a way to manually override graphics
     * settings. Note: When using the OpenGL rendering driver or when running in headless mode, this
     * function always returns `RenderingDevice.DEVICE_TYPE_OTHER`.
     *
     * Generated from Godot docs: RenderingServer.get_video_adapter_type
     */
    @JvmStatic
    fun getVideoAdapterType(): RenderingDevice.DeviceType {
        return RenderingDevice.DeviceType(ObjectCalls.ptrcallNoArgsRetLong(getVideoAdapterTypeBind, singleton))
    }

    /**
     * Returns the version of the graphics video adapter currently in use (e.g. "1.2.189" for Vulkan,
     * "3.3.0 NVIDIA 510.60.02" for OpenGL). This version may be different from the actual latest
     * version supported by the hardware, as Godot may not always request the latest version. See also
     * `OS.get_video_adapter_driver_info`. Note: When running a headless or server binary, this
     * function returns an empty string.
     *
     * Generated from Godot docs: RenderingServer.get_video_adapter_api_version
     */
    @JvmStatic
    fun getVideoAdapterApiVersion(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getVideoAdapterApiVersionBind, singleton)
    }

    /**
     * Returns the name of the current rendering driver. This can be `vulkan`, `d3d12`, `metal`,
     * `opengl3`, `opengl3_es`, or `opengl3_angle`. See also `get_current_rendering_method`. When
     * `ProjectSettings.rendering/renderer/rendering_method` is `forward_plus` or `mobile`, the
     * rendering driver is determined by `ProjectSettings.rendering/rendering_device/driver`. When
     * `ProjectSettings.rendering/renderer/rendering_method` is `gl_compatibility`, the rendering
     * driver is determined by `ProjectSettings.rendering/gl_compatibility/driver`. The rendering
     * driver is also determined by the `--rendering-driver` command line argument that overrides this
     * project setting, or an automatic fallback that is applied depending on the hardware.
     *
     * Generated from Godot docs: RenderingServer.get_current_rendering_driver_name
     */
    @JvmStatic
    fun getCurrentRenderingDriverName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCurrentRenderingDriverNameBind, singleton)
    }

    /**
     * Returns the name of the current rendering method. This can be `forward_plus`, `mobile`, or
     * `gl_compatibility`. See also `get_current_rendering_driver_name`. The rendering method is
     * determined by `ProjectSettings.rendering/renderer/rendering_method`, the `--rendering-method`
     * command line argument that overrides this project setting, or an automatic fallback that is
     * applied depending on the hardware.
     *
     * Generated from Godot docs: RenderingServer.get_current_rendering_method
     */
    @JvmStatic
    fun getCurrentRenderingMethod(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCurrentRenderingMethodBind, singleton)
    }

    /**
     * Returns a mesh of a sphere with the given number of horizontal subdivisions, vertical
     * subdivisions and radius. See also `get_test_cube`.
     *
     * Generated from Godot docs: RenderingServer.make_sphere_mesh
     */
    @JvmStatic
    fun makeSphereMesh(latitudes: Int, longitudes: Int, radius: Double): RID {
        return ObjectCalls.ptrcallWithTwoIntDoubleArgsRetRID(makeSphereMeshBind, singleton, latitudes, longitudes, radius)
    }

    /**
     * Returns the RID of the test cube. This mesh will be created and returned on the first call to
     * `get_test_cube`, then it will be cached for subsequent calls. See also `make_sphere_mesh`.
     *
     * Generated from Godot docs: RenderingServer.get_test_cube
     */
    @JvmStatic
    fun getTestCube(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getTestCubeBind, singleton)
    }

    /**
     * Returns the RID of a 256×256 texture with a testing pattern on it (in `Image.FORMAT_RGB8`
     * format). This texture will be created and returned on the first call to `get_test_texture`, then
     * it will be cached for subsequent calls. See also `get_white_texture`.
     *
     * Generated from Godot docs: RenderingServer.get_test_texture
     */
    @JvmStatic
    fun getTestTexture(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getTestTextureBind, singleton)
    }

    /**
     * Returns the ID of a 4×4 white texture (in `Image.FORMAT_RGB8` format). This texture will be
     * created and returned on the first call to `get_white_texture`, then it will be cached for
     * subsequent calls. See also `get_test_texture`.
     *
     * Generated from Godot docs: RenderingServer.get_white_texture
     */
    @JvmStatic
    fun getWhiteTexture(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getWhiteTextureBind, singleton)
    }

    /**
     * Sets a boot image. The `color` defines the background color. The value of `stretch_mode`
     * indicates how the image will be stretched (see `SplashStretchMode` for possible values). If
     * `use_filter` is `true`, the image will be scaled with linear interpolation. If `use_filter` is
     * `false`, the image will be scaled with nearest-neighbor interpolation.
     *
     * Generated from Godot docs: RenderingServer.set_boot_image_with_stretch
     */
    @JvmStatic
    fun setBootImageWithStretch(image: Image?, color: Color, stretchMode: RenderingServer.SplashStretchMode, useFilter: Boolean = true) {
        ObjectCalls.ptrcallWithObjectColorLongBoolArgs(setBootImageWithStretchBind, singleton, image?.requireOpenHandle() ?: NULL_SEGMENT, color, stretchMode.value, useFilter)
    }

    /**
     * Sets a boot image. The `color` defines the background color. The value of `scale` indicates if
     * the image will be scaled to fit the screen size. If `use_filter` is `true`, the image will be
     * scaled with linear interpolation. If `use_filter` is `false`, the image will be scaled with
     * nearest-neighbor interpolation.
     *
     * Generated from Godot docs: RenderingServer.set_boot_image
     */
    @JvmStatic
    fun setBootImage(image: Image?, color: Color, scale: Boolean, useFilter: Boolean = true) {
        ObjectCalls.ptrcallWithObjectColorTwoBoolArgs(setBootImageBind, singleton, image?.requireOpenHandle() ?: NULL_SEGMENT, color, scale, useFilter)
    }

    /**
     * Returns the default clear color which is used when a specific clear color has not been selected.
     * See also `set_default_clear_color`.
     *
     * Generated from Godot docs: RenderingServer.get_default_clear_color
     */
    @JvmStatic
    fun getDefaultClearColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getDefaultClearColorBind, singleton)
    }

    /**
     * Sets the default clear color which is used when a specific clear color has not been selected.
     * See also `get_default_clear_color`.
     *
     * Generated from Godot docs: RenderingServer.set_default_clear_color
     */
    @JvmStatic
    fun setDefaultClearColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setDefaultClearColorBind, singleton, color)
    }

    /**
     * Returns `true` if the OS supports a certain `feature`. Features might be `s3tc`, `etc`, and
     * `etc2`.
     *
     * Generated from Godot docs: RenderingServer.has_os_feature
     */
    @JvmStatic
    fun hasOsFeature(feature: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasOsFeatureBind, singleton, feature)
    }

    /**
     * If `generate` is `true`, generates debug wireframes for all meshes that are loaded when using
     * the Compatibility renderer. By default, the engine does not generate debug wireframes at
     * runtime, since they slow down loading of assets and take up VRAM. Note: You must call this
     * method before loading any meshes when using the Compatibility renderer. Otherwise, wireframes
     * will not be used.
     *
     * Generated from Godot docs: RenderingServer.set_debug_generate_wireframes
     */
    @JvmStatic
    fun setDebugGenerateWireframes(generate: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDebugGenerateWireframesBind, singleton, generate)
    }

    /**
     * If `false`, disables rendering completely, but the engine logic is still being processed. You
     * can call `force_draw` to draw a frame even with rendering disabled.
     *
     * Generated from Godot docs: RenderingServer.is_render_loop_enabled
     */
    @JvmStatic
    fun isRenderLoopEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRenderLoopEnabledBind, singleton)
    }

    /**
     * If `false`, disables rendering completely, but the engine logic is still being processed. You
     * can call `force_draw` to draw a frame even with rendering disabled.
     *
     * Generated from Godot docs: RenderingServer.set_render_loop_enabled
     */
    @JvmStatic
    fun setRenderLoopEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRenderLoopEnabledBind, singleton, enabled)
    }

    /**
     * Returns the time taken to setup rendering on the CPU in milliseconds. This value is shared
     * across all viewports and does not require `viewport_set_measure_render_time` to be enabled on a
     * viewport to be queried. See also `viewport_get_measured_render_time_cpu`.
     *
     * Generated from Godot docs: RenderingServer.get_frame_setup_time_cpu
     */
    @JvmStatic
    fun getFrameSetupTimeCpu(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFrameSetupTimeCpuBind, singleton)
    }

    /**
     * Forces a synchronization between the CPU and GPU, which may be required in certain cases. Only
     * call this when needed, as CPU-GPU synchronization has a performance cost.
     *
     * Generated from Godot docs: RenderingServer.force_sync
     */
    @JvmStatic
    fun forceSync() {
        ObjectCalls.ptrcallNoArgs(forceSyncBind, singleton)
    }

    /**
     * Forces redrawing of all viewports at once. Must be called from the main thread.
     *
     * Generated from Godot docs: RenderingServer.force_draw
     */
    @JvmStatic
    fun forceDraw(swapBuffers: Boolean = true, frameStep: Double = 0.0) {
        ObjectCalls.ptrcallWithBoolAndDoubleArgs(forceDrawBind, singleton, swapBuffers, frameStep)
    }

    /**
     * Returns the global RenderingDevice. Note: When using the OpenGL rendering driver or when running
     * in headless mode, this function always returns `null`.
     *
     * Generated from Godot docs: RenderingServer.get_rendering_device
     */
    @JvmStatic
    fun getRenderingDevice(): RenderingDevice? {
        return RenderingDevice.wrap(ObjectCalls.ptrcallNoArgsRetObject(getRenderingDeviceBind, singleton))
    }

    /**
     * Creates a RenderingDevice that can be used to do draw and compute operations on a separate
     * thread. Cannot draw to the screen nor share data with the global RenderingDevice. Note: When
     * using the OpenGL rendering driver or when running in headless mode, this function always returns
     * `null`.
     *
     * Generated from Godot docs: RenderingServer.create_local_rendering_device
     */
    @JvmStatic
    fun createLocalRenderingDevice(): RenderingDevice? {
        return RenderingDevice.wrap(ObjectCalls.ptrcallNoArgsRetObject(createLocalRenderingDeviceBind, singleton))
    }

    /**
     * Returns `true` if our code is currently executing on the rendering thread.
     *
     * Generated from Godot docs: RenderingServer.is_on_render_thread
     */
    @JvmStatic
    fun isOnRenderThread(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOnRenderThreadBind, singleton)
    }

    /**
     * As the RenderingServer actual logic may run on a separate thread, accessing its internals from
     * the main (or any other) thread will result in errors. To make it easier to run code that can
     * safely access the rendering internals (such as `RenderingDevice` and similar RD classes), push a
     * callable via this function so it will be executed on the render thread.
     *
     * Generated from Godot docs: RenderingServer.call_on_render_thread
     */
    @JvmStatic
    fun callOnRenderThread(callable: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(callOnRenderThreadBind, singleton, callable.target.segment, callable.method)
    }

    /**
     * This method does nothing and always returns `false`.
     *
     * Generated from Godot docs: RenderingServer.has_feature
     */
    @JvmStatic
    fun hasFeature(feature: RenderingServer.Features): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(hasFeatureBind, singleton, feature.value)
    }

    object Signals {
        const val framePreDraw: String = "frame_pre_draw"
        const val framePostDraw: String = "frame_post_draw"
    }

    @JvmInline
    value class TextureType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * 2D texture.
             *
             * Generated from Godot docs: RenderingServer.TEXTURE_TYPE_2D
             */
            val TYPE_2D: TextureType get() = TextureType(0L)
            /**
             * Layered texture.
             *
             * Generated from Godot docs: RenderingServer.TEXTURE_TYPE_LAYERED
             */
            val LAYERED: TextureType get() = TextureType(1L)
            /**
             * 3D texture.
             *
             * Generated from Godot docs: RenderingServer.TEXTURE_TYPE_3D
             */
            val TYPE_3D: TextureType get() = TextureType(2L)
        }
    }

    @JvmInline
    value class TextureLayeredType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Array of 2-dimensional textures (see `Texture2DArray`).
             *
             * Generated from Godot docs: RenderingServer.TEXTURE_LAYERED_2D_ARRAY
             */
            val LAYERED_2D_ARRAY: TextureLayeredType get() = TextureLayeredType(0L)
            /**
             * Cubemap texture (see `Cubemap`).
             *
             * Generated from Godot docs: RenderingServer.TEXTURE_LAYERED_CUBEMAP
             */
            val CUBEMAP: TextureLayeredType get() = TextureLayeredType(1L)
            /**
             * Array of cubemap textures (see `CubemapArray`).
             *
             * Generated from Godot docs: RenderingServer.TEXTURE_LAYERED_CUBEMAP_ARRAY
             */
            val CUBEMAP_ARRAY: TextureLayeredType get() = TextureLayeredType(2L)
        }
    }

    @JvmInline
    value class CubeMapLayer(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Left face of a `Cubemap`.
             *
             * Generated from Godot docs: RenderingServer.CUBEMAP_LAYER_LEFT
             */
            val LEFT: CubeMapLayer get() = CubeMapLayer(0L)
            /**
             * Right face of a `Cubemap`.
             *
             * Generated from Godot docs: RenderingServer.CUBEMAP_LAYER_RIGHT
             */
            val RIGHT: CubeMapLayer get() = CubeMapLayer(1L)
            /**
             * Bottom face of a `Cubemap`.
             *
             * Generated from Godot docs: RenderingServer.CUBEMAP_LAYER_BOTTOM
             */
            val BOTTOM: CubeMapLayer get() = CubeMapLayer(2L)
            /**
             * Top face of a `Cubemap`.
             *
             * Generated from Godot docs: RenderingServer.CUBEMAP_LAYER_TOP
             */
            val TOP: CubeMapLayer get() = CubeMapLayer(3L)
            /**
             * Front face of a `Cubemap`.
             *
             * Generated from Godot docs: RenderingServer.CUBEMAP_LAYER_FRONT
             */
            val FRONT: CubeMapLayer get() = CubeMapLayer(4L)
            /**
             * Back face of a `Cubemap`.
             *
             * Generated from Godot docs: RenderingServer.CUBEMAP_LAYER_BACK
             */
            val BACK: CubeMapLayer get() = CubeMapLayer(5L)
        }
    }

    @JvmInline
    value class TextureDrawableFormat(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * OpenGL texture format RGBA with four components, each with a bitdepth of 8.
             *
             * Generated from Godot docs: RenderingServer.TEXTURE_DRAWABLE_FORMAT_RGBA8
             */
            val RGBA8: TextureDrawableFormat get() = TextureDrawableFormat(0L)
            /**
             * OpenGL texture format RGBA with four components, each with a bitdepth of 8. When drawn to, an
             * sRGB to linear color space conversion is performed.
             *
             * Generated from Godot docs: RenderingServer.TEXTURE_DRAWABLE_FORMAT_RGBA8_SRGB
             */
            val RGBA8_SRGB: TextureDrawableFormat get() = TextureDrawableFormat(1L)
            /**
             * OpenGL texture format GL_RGBA16F where there are four components, each a 16-bit "half-precision"
             * floating-point value.
             *
             * Generated from Godot docs: RenderingServer.TEXTURE_DRAWABLE_FORMAT_RGBAH
             */
            val RGBAH: TextureDrawableFormat get() = TextureDrawableFormat(2L)
            /**
             * OpenGL texture format GL_RGBA32F where there are four components, each a 32-bit floating-point
             * value.
             *
             * Generated from Godot docs: RenderingServer.TEXTURE_DRAWABLE_FORMAT_RGBAF
             */
            val RGBAF: TextureDrawableFormat get() = TextureDrawableFormat(3L)
        }
    }

    @JvmInline
    value class ShaderMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Shader is a 3D shader.
             *
             * Generated from Godot docs: RenderingServer.SHADER_SPATIAL
             */
            val SPATIAL: ShaderMode get() = ShaderMode(0L)
            /**
             * Shader is a 2D shader.
             *
             * Generated from Godot docs: RenderingServer.SHADER_CANVAS_ITEM
             */
            val CANVAS_ITEM: ShaderMode get() = ShaderMode(1L)
            /**
             * Shader is a particle shader (can be used in both 2D and 3D).
             *
             * Generated from Godot docs: RenderingServer.SHADER_PARTICLES
             */
            val PARTICLES: ShaderMode get() = ShaderMode(2L)
            /**
             * Shader is a 3D sky shader.
             *
             * Generated from Godot docs: RenderingServer.SHADER_SKY
             */
            val SKY: ShaderMode get() = ShaderMode(3L)
            /**
             * Shader is a 3D fog shader.
             *
             * Generated from Godot docs: RenderingServer.SHADER_FOG
             */
            val FOG: ShaderMode get() = ShaderMode(4L)
            /**
             * Shader is a texture_blit shader.
             *
             * Generated from Godot docs: RenderingServer.SHADER_TEXTURE_BLIT
             */
            val TEXTURE_BLIT: ShaderMode get() = ShaderMode(5L)
            /**
             * Represents the size of the `ShaderMode` enum.
             *
             * Generated from Godot docs: RenderingServer.SHADER_MAX
             */
            val MAX: ShaderMode get() = ShaderMode(6L)
        }
    }

    @JvmInline
    value class ArrayType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Array is a vertex position array.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_VERTEX
             */
            val VERTEX: ArrayType get() = ArrayType(0L)
            /**
             * Array is a normal array.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_NORMAL
             */
            val NORMAL: ArrayType get() = ArrayType(1L)
            /**
             * Array is a tangent array.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_TANGENT
             */
            val TANGENT: ArrayType get() = ArrayType(2L)
            /**
             * Array is a vertex color array.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_COLOR
             */
            val COLOR: ArrayType get() = ArrayType(3L)
            /**
             * Array is a UV coordinates array.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_TEX_UV
             */
            val TEX_UV: ArrayType get() = ArrayType(4L)
            /**
             * Array is a UV coordinates array for the second set of UV coordinates.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_TEX_UV2
             */
            val TEX_UV2: ArrayType get() = ArrayType(5L)
            /**
             * Array is a custom data array for the first set of custom data.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_CUSTOM0
             */
            val CUSTOM0: ArrayType get() = ArrayType(6L)
            /**
             * Array is a custom data array for the second set of custom data.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_CUSTOM1
             */
            val CUSTOM1: ArrayType get() = ArrayType(7L)
            /**
             * Array is a custom data array for the third set of custom data.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_CUSTOM2
             */
            val CUSTOM2: ArrayType get() = ArrayType(8L)
            /**
             * Array is a custom data array for the fourth set of custom data.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_CUSTOM3
             */
            val CUSTOM3: ArrayType get() = ArrayType(9L)
            /**
             * Array contains bone information.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_BONES
             */
            val BONES: ArrayType get() = ArrayType(10L)
            /**
             * Array is weight information.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_WEIGHTS
             */
            val WEIGHTS: ArrayType get() = ArrayType(11L)
            /**
             * Array is an index array.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_INDEX
             */
            val INDEX: ArrayType get() = ArrayType(12L)
            /**
             * Represents the size of the `ArrayType` enum.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_MAX
             */
            val MAX: ArrayType get() = ArrayType(13L)
        }
    }

    @JvmInline
    value class ArrayCustomFormat(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Custom data array contains 8-bit-per-channel red/green/blue/alpha color data. Values are
             * normalized, unsigned floating-point in the `[0.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_CUSTOM_RGBA8_UNORM
             */
            val RGBA8_UNORM: ArrayCustomFormat get() = ArrayCustomFormat(0L)
            /**
             * Custom data array contains 8-bit-per-channel red/green/blue/alpha color data. Values are
             * normalized, signed floating-point in the `[-1.0, 1.0]` range.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_CUSTOM_RGBA8_SNORM
             */
            val RGBA8_SNORM: ArrayCustomFormat get() = ArrayCustomFormat(1L)
            /**
             * Custom data array contains 16-bit-per-channel red/green color data. Values are floating-point in
             * half precision.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_CUSTOM_RG_HALF
             */
            val RG_HALF: ArrayCustomFormat get() = ArrayCustomFormat(2L)
            /**
             * Custom data array contains 16-bit-per-channel red/green/blue/alpha color data. Values are
             * floating-point in half precision.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_CUSTOM_RGBA_HALF
             */
            val RGBA_HALF: ArrayCustomFormat get() = ArrayCustomFormat(3L)
            /**
             * Custom data array contains 32-bit-per-channel red color data. Values are floating-point in
             * single precision.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_CUSTOM_R_FLOAT
             */
            val R_FLOAT: ArrayCustomFormat get() = ArrayCustomFormat(4L)
            /**
             * Custom data array contains 32-bit-per-channel red/green color data. Values are floating-point in
             * single precision.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_CUSTOM_RG_FLOAT
             */
            val RG_FLOAT: ArrayCustomFormat get() = ArrayCustomFormat(5L)
            /**
             * Custom data array contains 32-bit-per-channel red/green/blue color data. Values are
             * floating-point in single precision.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_CUSTOM_RGB_FLOAT
             */
            val RGB_FLOAT: ArrayCustomFormat get() = ArrayCustomFormat(6L)
            /**
             * Custom data array contains 32-bit-per-channel red/green/blue/alpha color data. Values are
             * floating-point in single precision.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_CUSTOM_RGBA_FLOAT
             */
            val RGBA_FLOAT: ArrayCustomFormat get() = ArrayCustomFormat(7L)
            /**
             * Represents the size of the `ArrayCustomFormat` enum.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_CUSTOM_MAX
             */
            val MAX: ArrayCustomFormat get() = ArrayCustomFormat(8L)
        }
    }

    @JvmInline
    value class ArrayFormat(override val value: Long) : GodotEnumValue {
        infix fun or(other: ArrayFormat): ArrayFormat = ArrayFormat(value or other.value)

        infix fun and(other: ArrayFormat): ArrayFormat = ArrayFormat(value and other.value)

        infix fun xor(other: ArrayFormat): ArrayFormat = ArrayFormat(value xor other.value)

        fun inv(): ArrayFormat = ArrayFormat(value.inv())

        operator fun contains(other: ArrayFormat): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * Flag used to mark a vertex position array.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FORMAT_VERTEX
             */
            val FORMAT_VERTEX: ArrayFormat get() = ArrayFormat(1L)
            /**
             * Flag used to mark a normal array.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FORMAT_NORMAL
             */
            val FORMAT_NORMAL: ArrayFormat get() = ArrayFormat(2L)
            /**
             * Flag used to mark a tangent array.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FORMAT_TANGENT
             */
            val FORMAT_TANGENT: ArrayFormat get() = ArrayFormat(4L)
            /**
             * Flag used to mark a vertex color array.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FORMAT_COLOR
             */
            val FORMAT_COLOR: ArrayFormat get() = ArrayFormat(8L)
            /**
             * Flag used to mark a UV coordinates array.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FORMAT_TEX_UV
             */
            val FORMAT_TEX_UV: ArrayFormat get() = ArrayFormat(16L)
            /**
             * Flag used to mark a UV coordinates array for the second UV coordinates.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FORMAT_TEX_UV2
             */
            val FORMAT_TEX_UV2: ArrayFormat get() = ArrayFormat(32L)
            /**
             * Flag used to mark an array of custom per-vertex data for the first set of custom data.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FORMAT_CUSTOM0
             */
            val FORMAT_CUSTOM0: ArrayFormat get() = ArrayFormat(64L)
            /**
             * Flag used to mark an array of custom per-vertex data for the second set of custom data.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FORMAT_CUSTOM1
             */
            val FORMAT_CUSTOM1: ArrayFormat get() = ArrayFormat(128L)
            /**
             * Flag used to mark an array of custom per-vertex data for the third set of custom data.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FORMAT_CUSTOM2
             */
            val FORMAT_CUSTOM2: ArrayFormat get() = ArrayFormat(256L)
            /**
             * Flag used to mark an array of custom per-vertex data for the fourth set of custom data.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FORMAT_CUSTOM3
             */
            val FORMAT_CUSTOM3: ArrayFormat get() = ArrayFormat(512L)
            /**
             * Flag used to mark a bone information array.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FORMAT_BONES
             */
            val FORMAT_BONES: ArrayFormat get() = ArrayFormat(1024L)
            /**
             * Flag used to mark a weights array.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FORMAT_WEIGHTS
             */
            val FORMAT_WEIGHTS: ArrayFormat get() = ArrayFormat(2048L)
            /**
             * Flag used to mark an index array.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FORMAT_INDEX
             */
            val FORMAT_INDEX: ArrayFormat get() = ArrayFormat(4096L)
            /**
             * Mask of mesh channels permitted in blend shapes.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FORMAT_BLEND_SHAPE_MASK
             */
            val FORMAT_BLEND_SHAPE_MASK: ArrayFormat get() = ArrayFormat(7L)
            /**
             * Shift of first custom channel.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FORMAT_CUSTOM_BASE
             */
            val FORMAT_CUSTOM_BASE: ArrayFormat get() = ArrayFormat(13L)
            /**
             * Number of format bits per custom channel. See `ArrayCustomFormat`.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FORMAT_CUSTOM_BITS
             */
            val FORMAT_CUSTOM_BITS: ArrayFormat get() = ArrayFormat(3L)
            /**
             * Amount to shift `ArrayCustomFormat` for custom channel index 0.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FORMAT_CUSTOM0_SHIFT
             */
            val FORMAT_CUSTOM0_SHIFT: ArrayFormat get() = ArrayFormat(13L)
            /**
             * Amount to shift `ArrayCustomFormat` for custom channel index 1.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FORMAT_CUSTOM1_SHIFT
             */
            val FORMAT_CUSTOM1_SHIFT: ArrayFormat get() = ArrayFormat(16L)
            /**
             * Amount to shift `ArrayCustomFormat` for custom channel index 2.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FORMAT_CUSTOM2_SHIFT
             */
            val FORMAT_CUSTOM2_SHIFT: ArrayFormat get() = ArrayFormat(19L)
            /**
             * Amount to shift `ArrayCustomFormat` for custom channel index 3.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FORMAT_CUSTOM3_SHIFT
             */
            val FORMAT_CUSTOM3_SHIFT: ArrayFormat get() = ArrayFormat(22L)
            /**
             * Mask of custom format bits per custom channel. Must be shifted by one of the SHIFT constants.
             * See `ArrayCustomFormat`.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FORMAT_CUSTOM_MASK
             */
            val FORMAT_CUSTOM_MASK: ArrayFormat get() = ArrayFormat(7L)
            /**
             * Shift of first compress flag. Compress flags should be passed to
             * `ArrayMesh.add_surface_from_arrays` and `SurfaceTool.commit`.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_COMPRESS_FLAGS_BASE
             */
            val COMPRESS_FLAGS_BASE: ArrayFormat get() = ArrayFormat(25L)
            /**
             * Flag used to mark that the array contains 2D vertices.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FLAG_USE_2D_VERTICES
             */
            val FLAG_USE_2D_VERTICES: ArrayFormat get() = ArrayFormat(33554432L)
            /**
             * Flag used to mark that the mesh data will use `GL_DYNAMIC_DRAW` on GLES. Unused on Vulkan.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FLAG_USE_DYNAMIC_UPDATE
             */
            val FLAG_USE_DYNAMIC_UPDATE: ArrayFormat get() = ArrayFormat(67108864L)
            /**
             * Flag used to mark that the array uses 8 bone weights instead of 4.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FLAG_USE_8_BONE_WEIGHTS
             */
            val FLAG_USE_8_BONE_WEIGHTS: ArrayFormat get() = ArrayFormat(134217728L)
            /**
             * Flag used to mark that the mesh does not have a vertex array and instead will infer vertex
             * positions in the shader using indices and other information.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FLAG_USES_EMPTY_VERTEX_ARRAY
             */
            val FLAG_USES_EMPTY_VERTEX_ARRAY: ArrayFormat get() = ArrayFormat(268435456L)
            /**
             * Flag used to mark that a mesh is using compressed attributes (vertices, normals, tangents, UVs).
             * When this form of compression is enabled, vertex positions will be packed into an RGBA16UNORM
             * attribute and scaled in the vertex shader. The normal and tangent will be packed into an
             * RG16UNORM representing an axis, and a 16-bit float stored in the A-channel of the vertex. UVs
             * will use 16-bit normalized floats instead of full 32-bit signed floats. When using this
             * compression mode you must use either vertices, normals, and tangents or only vertices. You
             * cannot use normals without tangents. Importers will automatically enable this compression if
             * they can.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FLAG_COMPRESS_ATTRIBUTES
             */
            val FLAG_COMPRESS_ATTRIBUTES: ArrayFormat get() = ArrayFormat(536870912L)
            /**
             * Flag used to mark the start of the bits used to store the mesh version.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FLAG_FORMAT_VERSION_BASE
             */
            val FLAG_FORMAT_VERSION_BASE: ArrayFormat get() = ArrayFormat(35L)
            /**
             * Flag used to shift a mesh format int to bring the version into the lowest digits.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FLAG_FORMAT_VERSION_SHIFT
             */
            val FLAG_FORMAT_VERSION_SHIFT: ArrayFormat get() = ArrayFormat(35L)
            /**
             * Flag used to record the format used by prior mesh versions before the introduction of a version.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FLAG_FORMAT_VERSION_1
             */
            val FLAG_FORMAT_VERSION_1: ArrayFormat get() = ArrayFormat(0L)
            /**
             * Flag used to record the second iteration of the mesh version flag. The primary difference
             * between this and `ARRAY_FLAG_FORMAT_VERSION_1` is that this version supports
             * `ARRAY_FLAG_COMPRESS_ATTRIBUTES` and in this version vertex positions are de-interleaved from
             * normals and tangents.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FLAG_FORMAT_VERSION_2
             */
            val FLAG_FORMAT_VERSION_2: ArrayFormat get() = ArrayFormat(34359738368L)
            /**
             * Flag used to record the current version that the engine expects. Currently this is the same as
             * `ARRAY_FLAG_FORMAT_VERSION_2`.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FLAG_FORMAT_CURRENT_VERSION
             */
            val FLAG_FORMAT_CURRENT_VERSION: ArrayFormat get() = ArrayFormat(34359738368L)
            /**
             * Flag used to isolate the bits used for mesh version after using
             * `ARRAY_FLAG_FORMAT_VERSION_SHIFT` to shift them into place.
             *
             * Generated from Godot docs: RenderingServer.ARRAY_FLAG_FORMAT_VERSION_MASK
             */
            val FLAG_FORMAT_VERSION_MASK: ArrayFormat get() = ArrayFormat(255L)
        }
    }

    @JvmInline
    value class PrimitiveType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Primitive to draw consists of points.
             *
             * Generated from Godot docs: RenderingServer.PRIMITIVE_POINTS
             */
            val POINTS: PrimitiveType get() = PrimitiveType(0L)
            /**
             * Primitive to draw consists of lines.
             *
             * Generated from Godot docs: RenderingServer.PRIMITIVE_LINES
             */
            val LINES: PrimitiveType get() = PrimitiveType(1L)
            /**
             * Primitive to draw consists of a line strip from start to end.
             *
             * Generated from Godot docs: RenderingServer.PRIMITIVE_LINE_STRIP
             */
            val LINE_STRIP: PrimitiveType get() = PrimitiveType(2L)
            /**
             * Primitive to draw consists of triangles.
             *
             * Generated from Godot docs: RenderingServer.PRIMITIVE_TRIANGLES
             */
            val TRIANGLES: PrimitiveType get() = PrimitiveType(3L)
            /**
             * Primitive to draw consists of a triangle strip (the last 3 vertices are always combined to make
             * a triangle).
             *
             * Generated from Godot docs: RenderingServer.PRIMITIVE_TRIANGLE_STRIP
             */
            val TRIANGLE_STRIP: PrimitiveType get() = PrimitiveType(4L)
            /**
             * Represents the size of the `PrimitiveType` enum.
             *
             * Generated from Godot docs: RenderingServer.PRIMITIVE_MAX
             */
            val MAX: PrimitiveType get() = PrimitiveType(5L)
        }
    }

    @JvmInline
    value class BlendShapeMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Blend shapes are normalized.
             *
             * Generated from Godot docs: RenderingServer.BLEND_SHAPE_MODE_NORMALIZED
             */
            val NORMALIZED: BlendShapeMode get() = BlendShapeMode(0L)
            /**
             * Blend shapes are relative to base weight.
             *
             * Generated from Godot docs: RenderingServer.BLEND_SHAPE_MODE_RELATIVE
             */
            val RELATIVE: BlendShapeMode get() = BlendShapeMode(1L)
        }
    }

    @JvmInline
    value class MultimeshTransformFormat(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use `Transform2D` to store MultiMesh transform.
             *
             * Generated from Godot docs: RenderingServer.MULTIMESH_TRANSFORM_2D
             */
            val TRANSFORM_2D: MultimeshTransformFormat get() = MultimeshTransformFormat(0L)
            /**
             * Use `Transform3D` to store MultiMesh transform.
             *
             * Generated from Godot docs: RenderingServer.MULTIMESH_TRANSFORM_3D
             */
            val TRANSFORM_3D: MultimeshTransformFormat get() = MultimeshTransformFormat(1L)
        }
    }

    @JvmInline
    value class MultimeshPhysicsInterpolationQuality(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * MultiMesh physics interpolation favors speed over quality.
             *
             * Generated from Godot docs: RenderingServer.MULTIMESH_INTERP_QUALITY_FAST
             */
            val FAST: MultimeshPhysicsInterpolationQuality get() = MultimeshPhysicsInterpolationQuality(0L)
            /**
             * MultiMesh physics interpolation favors quality over speed.
             *
             * Generated from Godot docs: RenderingServer.MULTIMESH_INTERP_QUALITY_HIGH
             */
            val HIGH: MultimeshPhysicsInterpolationQuality get() = MultimeshPhysicsInterpolationQuality(1L)
        }
    }

    @JvmInline
    value class LightProjectorFilter(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Nearest-neighbor filter for light projectors (use for pixel art light projectors). No mipmaps
             * are used for rendering, which means light projectors at a distance will look sharp but grainy.
             * This has roughly the same performance cost as using mipmaps.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PROJECTOR_FILTER_NEAREST
             */
            val NEAREST: LightProjectorFilter get() = LightProjectorFilter(0L)
            /**
             * Linear filter for light projectors (use for non-pixel art light projectors). No mipmaps are used
             * for rendering, which means light projectors at a distance will look smooth but blurry. This has
             * roughly the same performance cost as using mipmaps.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PROJECTOR_FILTER_LINEAR
             */
            val LINEAR: LightProjectorFilter get() = LightProjectorFilter(1L)
            /**
             * Nearest-neighbor filter for light projectors (use for pixel art light projectors). Isotropic
             * mipmaps are used for rendering, which means light projectors at a distance will look smooth but
             * blurry. This has roughly the same performance cost as not using mipmaps.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PROJECTOR_FILTER_NEAREST_MIPMAPS
             */
            val NEAREST_MIPMAPS: LightProjectorFilter get() = LightProjectorFilter(2L)
            /**
             * Linear filter for light projectors (use for non-pixel art light projectors). Isotropic mipmaps
             * are used for rendering, which means light projectors at a distance will look smooth but blurry.
             * This has roughly the same performance cost as not using mipmaps.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PROJECTOR_FILTER_LINEAR_MIPMAPS
             */
            val LINEAR_MIPMAPS: LightProjectorFilter get() = LightProjectorFilter(3L)
            /**
             * Nearest-neighbor filter for light projectors (use for pixel art light projectors). Anisotropic
             * mipmaps are used for rendering, which means light projectors at a distance will look smooth and
             * sharp when viewed from oblique angles. This looks better compared to isotropic mipmaps, but is
             * slower. The level of anisotropic filtering is defined by
             * `ProjectSettings.rendering/textures/default_filters/anisotropic_filtering_level`.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PROJECTOR_FILTER_NEAREST_MIPMAPS_ANISOTROPIC
             */
            val NEAREST_MIPMAPS_ANISOTROPIC: LightProjectorFilter get() = LightProjectorFilter(4L)
            /**
             * Linear filter for light projectors (use for non-pixel art light projectors). Anisotropic mipmaps
             * are used for rendering, which means light projectors at a distance will look smooth and sharp
             * when viewed from oblique angles. This looks better compared to isotropic mipmaps, but is slower.
             * The level of anisotropic filtering is defined by
             * `ProjectSettings.rendering/textures/default_filters/anisotropic_filtering_level`.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PROJECTOR_FILTER_LINEAR_MIPMAPS_ANISOTROPIC
             */
            val LINEAR_MIPMAPS_ANISOTROPIC: LightProjectorFilter get() = LightProjectorFilter(5L)
        }
    }

    @JvmInline
    value class LightType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Directional (sun/moon) light (see `DirectionalLight3D`).
             *
             * Generated from Godot docs: RenderingServer.LIGHT_DIRECTIONAL
             */
            val DIRECTIONAL: LightType get() = LightType(0L)
            /**
             * Omni light (see `OmniLight3D`).
             *
             * Generated from Godot docs: RenderingServer.LIGHT_OMNI
             */
            val OMNI: LightType get() = LightType(1L)
            /**
             * Spot light (see `SpotLight3D`).
             *
             * Generated from Godot docs: RenderingServer.LIGHT_SPOT
             */
            val SPOT: LightType get() = LightType(2L)
            /**
             * Area light (see `AreaLight3D`).
             *
             * Generated from Godot docs: RenderingServer.LIGHT_AREA
             */
            val AREA: LightType get() = LightType(3L)
        }
    }

    @JvmInline
    value class LightParam(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The light's energy multiplier.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PARAM_ENERGY
             */
            val ENERGY: LightParam get() = LightParam(0L)
            /**
             * The light's indirect energy multiplier (final indirect energy is `LIGHT_PARAM_ENERGY` *
             * `LIGHT_PARAM_INDIRECT_ENERGY`).
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PARAM_INDIRECT_ENERGY
             */
            val INDIRECT_ENERGY: LightParam get() = LightParam(1L)
            /**
             * The light's volumetric fog energy multiplier (final volumetric fog energy is
             * `LIGHT_PARAM_ENERGY` * `LIGHT_PARAM_VOLUMETRIC_FOG_ENERGY`).
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PARAM_VOLUMETRIC_FOG_ENERGY
             */
            val VOLUMETRIC_FOG_ENERGY: LightParam get() = LightParam(2L)
            /**
             * The light's influence on specularity.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PARAM_SPECULAR
             */
            val SPECULAR: LightParam get() = LightParam(3L)
            /**
             * The light's range.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PARAM_RANGE
             */
            val RANGE: LightParam get() = LightParam(4L)
            /**
             * The size of the light when using spot light or omni light. The angular size of the light when
             * using directional light.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PARAM_SIZE
             */
            val SIZE: LightParam get() = LightParam(5L)
            /**
             * The light's attenuation.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PARAM_ATTENUATION
             */
            val ATTENUATION: LightParam get() = LightParam(6L)
            /**
             * The spotlight's angle.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PARAM_SPOT_ANGLE
             */
            val SPOT_ANGLE: LightParam get() = LightParam(7L)
            /**
             * The spotlight's attenuation.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PARAM_SPOT_ATTENUATION
             */
            val SPOT_ATTENUATION: LightParam get() = LightParam(8L)
            /**
             * The maximum distance for shadow splits. Increasing this value will make directional shadows
             * visible from further away, at the cost of lower overall shadow detail and performance (since
             * more objects need to be included in the directional shadow rendering).
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PARAM_SHADOW_MAX_DISTANCE
             */
            val SHADOW_MAX_DISTANCE: LightParam get() = LightParam(9L)
            /**
             * Proportion of shadow atlas occupied by the first split.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PARAM_SHADOW_SPLIT_1_OFFSET
             */
            val SHADOW_SPLIT_1_OFFSET: LightParam get() = LightParam(10L)
            /**
             * Proportion of shadow atlas occupied by the second split.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PARAM_SHADOW_SPLIT_2_OFFSET
             */
            val SHADOW_SPLIT_2_OFFSET: LightParam get() = LightParam(11L)
            /**
             * Proportion of shadow atlas occupied by the third split. The fourth split occupies the rest.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PARAM_SHADOW_SPLIT_3_OFFSET
             */
            val SHADOW_SPLIT_3_OFFSET: LightParam get() = LightParam(12L)
            /**
             * Proportion of shadow max distance where the shadow will start to fade out.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PARAM_SHADOW_FADE_START
             */
            val SHADOW_FADE_START: LightParam get() = LightParam(13L)
            /**
             * Normal bias used to offset shadow lookup by object normal. Can be used to fix self-shadowing
             * artifacts.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PARAM_SHADOW_NORMAL_BIAS
             */
            val SHADOW_NORMAL_BIAS: LightParam get() = LightParam(14L)
            /**
             * Bias for the shadow lookup to fix self-shadowing artifacts.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PARAM_SHADOW_BIAS
             */
            val SHADOW_BIAS: LightParam get() = LightParam(15L)
            /**
             * Sets the size of the directional shadow pancake. The pancake offsets the start of the shadow's
             * camera frustum to provide a higher effective depth resolution for the shadow. However, a high
             * pancake size can cause artifacts in the shadows of large objects that are close to the edge of
             * the frustum. Reducing the pancake size can help. Setting the size to `0` turns off the pancaking
             * effect.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PARAM_SHADOW_PANCAKE_SIZE
             */
            val SHADOW_PANCAKE_SIZE: LightParam get() = LightParam(16L)
            /**
             * The light's shadow opacity. Values lower than `1.0` make the light appear through shadows. This
             * can be used to fake global illumination at a low performance cost.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PARAM_SHADOW_OPACITY
             */
            val SHADOW_OPACITY: LightParam get() = LightParam(17L)
            /**
             * Blurs the edges of the shadow. Can be used to hide pixel artifacts in low resolution shadow
             * maps. A high value can make shadows appear grainy and can cause other unwanted artifacts. Try to
             * keep as near default as possible.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PARAM_SHADOW_BLUR
             */
            val SHADOW_BLUR: LightParam get() = LightParam(18L)
            val TRANSMITTANCE_BIAS: LightParam get() = LightParam(19L)
            /**
             * Constant representing the intensity of the light, measured in Lumens when dealing with a
             * `SpotLight3D` or `OmniLight3D`, or measured in Lux with a `DirectionalLight3D`. Only used when
             * `ProjectSettings.rendering/lights_and_shadows/use_physical_light_units` is `true`.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PARAM_INTENSITY
             */
            val INTENSITY: LightParam get() = LightParam(20L)
            /**
             * Represents the size of the `LightParam` enum.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_PARAM_MAX
             */
            val MAX: LightParam get() = LightParam(21L)
        }
    }

    @JvmInline
    value class LightBakeMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Light is ignored when baking. This is the fastest mode, but the light will be taken into account
             * when baking global illumination. This mode should generally be used for dynamic lights that
             * change quickly, as the effect of global illumination is less noticeable on those lights.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_BAKE_DISABLED
             */
            val DISABLED: LightBakeMode get() = LightBakeMode(0L)
            /**
             * Light is taken into account in static baking (`VoxelGI`, `LightmapGI`, SDFGI
             * (`Environment.sdfgi_enabled`)). The light can be moved around or modified, but its global
             * illumination will not update in real-time. This is suitable for subtle changes (such as
             * flickering torches), but generally not large changes such as toggling a light on and off.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_BAKE_STATIC
             */
            val STATIC: LightBakeMode get() = LightBakeMode(1L)
            /**
             * Light is taken into account in dynamic baking (`VoxelGI` and SDFGI (`Environment.sdfgi_enabled`)
             * only). The light can be moved around or modified with global illumination updating in real-time.
             * The light's global illumination appearance will be slightly different compared to
             * `LIGHT_BAKE_STATIC`. This has a greater performance cost compared to `LIGHT_BAKE_STATIC`. When
             * using SDFGI, the update speed of dynamic lights is affected by
             * `ProjectSettings.rendering/global_illumination/sdfgi/frames_to_update_lights`.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_BAKE_DYNAMIC
             */
            val DYNAMIC: LightBakeMode get() = LightBakeMode(2L)
        }
    }

    @JvmInline
    value class LightOmniShadowMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use a dual paraboloid shadow map for omni lights.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_OMNI_SHADOW_DUAL_PARABOLOID
             */
            val DUAL_PARABOLOID: LightOmniShadowMode get() = LightOmniShadowMode(0L)
            /**
             * Use a cubemap shadow map for omni lights. Slower but better quality than dual paraboloid.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_OMNI_SHADOW_CUBE
             */
            val CUBE: LightOmniShadowMode get() = LightOmniShadowMode(1L)
        }
    }

    @JvmInline
    value class LightDirectionalShadowMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use orthogonal shadow projection for directional light.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_DIRECTIONAL_SHADOW_ORTHOGONAL
             */
            val ORTHOGONAL: LightDirectionalShadowMode get() = LightDirectionalShadowMode(0L)
            /**
             * Use 2 splits for shadow projection when using directional light.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_DIRECTIONAL_SHADOW_PARALLEL_2_SPLITS
             */
            val PARALLEL_2_SPLITS: LightDirectionalShadowMode get() = LightDirectionalShadowMode(1L)
            /**
             * Use 4 splits for shadow projection when using directional light.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_DIRECTIONAL_SHADOW_PARALLEL_4_SPLITS
             */
            val PARALLEL_4_SPLITS: LightDirectionalShadowMode get() = LightDirectionalShadowMode(2L)
        }
    }

    @JvmInline
    value class LightDirectionalSkyMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use DirectionalLight3D in both sky rendering and scene lighting.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_DIRECTIONAL_SKY_MODE_LIGHT_AND_SKY
             */
            val LIGHT_AND_SKY: LightDirectionalSkyMode get() = LightDirectionalSkyMode(0L)
            /**
             * Only use DirectionalLight3D in scene lighting.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_DIRECTIONAL_SKY_MODE_LIGHT_ONLY
             */
            val LIGHT_ONLY: LightDirectionalSkyMode get() = LightDirectionalSkyMode(1L)
            /**
             * Only use DirectionalLight3D in sky rendering.
             *
             * Generated from Godot docs: RenderingServer.LIGHT_DIRECTIONAL_SKY_MODE_SKY_ONLY
             */
            val SKY_ONLY: LightDirectionalSkyMode get() = LightDirectionalSkyMode(2L)
        }
    }

    @JvmInline
    value class ShadowQuality(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Lowest shadow filtering quality (fastest). Soft shadows are not available with this quality
             * setting, which means the `Light3D.shadow_blur` property is ignored if `Light3D.light_size` and
             * `Light3D.light_angular_distance` is `0.0`. Note: The variable shadow blur performed by
             * `Light3D.light_size` and `Light3D.light_angular_distance` is still effective when using hard
             * shadow filtering. In this case, `Light3D.shadow_blur` is taken into account. However, the
             * results will not be blurred, instead the blur amount is treated as a maximum radius for the
             * penumbra.
             *
             * Generated from Godot docs: RenderingServer.SHADOW_QUALITY_HARD
             */
            val HARD: ShadowQuality get() = ShadowQuality(0L)
            /**
             * Very low shadow filtering quality (faster). When using this quality setting,
             * `Light3D.shadow_blur` is automatically multiplied by 0.75× to avoid introducing too much noise.
             * This division only applies to lights whose `Light3D.light_size` or
             * `Light3D.light_angular_distance` is `0.0`).
             *
             * Generated from Godot docs: RenderingServer.SHADOW_QUALITY_SOFT_VERY_LOW
             */
            val SOFT_VERY_LOW: ShadowQuality get() = ShadowQuality(1L)
            /**
             * Low shadow filtering quality (fast).
             *
             * Generated from Godot docs: RenderingServer.SHADOW_QUALITY_SOFT_LOW
             */
            val SOFT_LOW: ShadowQuality get() = ShadowQuality(2L)
            /**
             * Medium low shadow filtering quality (average).
             *
             * Generated from Godot docs: RenderingServer.SHADOW_QUALITY_SOFT_MEDIUM
             */
            val SOFT_MEDIUM: ShadowQuality get() = ShadowQuality(3L)
            /**
             * High low shadow filtering quality (slow). When using this quality setting, `Light3D.shadow_blur`
             * is automatically multiplied by 1.5× to better make use of the high sample count. This increased
             * blur also improves the stability of dynamic object shadows. This multiplier only applies to
             * lights whose `Light3D.light_size` or `Light3D.light_angular_distance` is `0.0`).
             *
             * Generated from Godot docs: RenderingServer.SHADOW_QUALITY_SOFT_HIGH
             */
            val SOFT_HIGH: ShadowQuality get() = ShadowQuality(4L)
            /**
             * Highest low shadow filtering quality (slowest). When using this quality setting,
             * `Light3D.shadow_blur` is automatically multiplied by 2× to better make use of the high sample
             * count. This increased blur also improves the stability of dynamic object shadows. This
             * multiplier only applies to lights whose `Light3D.light_size` or `Light3D.light_angular_distance`
             * is `0.0`).
             *
             * Generated from Godot docs: RenderingServer.SHADOW_QUALITY_SOFT_ULTRA
             */
            val SOFT_ULTRA: ShadowQuality get() = ShadowQuality(5L)
            /**
             * Represents the size of the `ShadowQuality` enum.
             *
             * Generated from Godot docs: RenderingServer.SHADOW_QUALITY_MAX
             */
            val MAX: ShadowQuality get() = ShadowQuality(6L)
        }
    }

    @JvmInline
    value class ReflectionProbeUpdateMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Reflection probe will update reflections once and then stop.
             *
             * Generated from Godot docs: RenderingServer.REFLECTION_PROBE_UPDATE_ONCE
             */
            val ONCE: ReflectionProbeUpdateMode get() = ReflectionProbeUpdateMode(0L)
            /**
             * Reflection probe will update each frame. This mode is necessary to capture moving objects.
             *
             * Generated from Godot docs: RenderingServer.REFLECTION_PROBE_UPDATE_ALWAYS
             */
            val ALWAYS: ReflectionProbeUpdateMode get() = ReflectionProbeUpdateMode(1L)
        }
    }

    @JvmInline
    value class ReflectionProbeAmbientMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Do not apply any ambient lighting inside the reflection probe's box defined by its size.
             *
             * Generated from Godot docs: RenderingServer.REFLECTION_PROBE_AMBIENT_DISABLED
             */
            val DISABLED: ReflectionProbeAmbientMode get() = ReflectionProbeAmbientMode(0L)
            /**
             * Apply automatically-sourced environment lighting inside the reflection probe's box defined by
             * its size.
             *
             * Generated from Godot docs: RenderingServer.REFLECTION_PROBE_AMBIENT_ENVIRONMENT
             */
            val ENVIRONMENT: ReflectionProbeAmbientMode get() = ReflectionProbeAmbientMode(1L)
            /**
             * Apply custom ambient lighting inside the reflection probe's box defined by its size. See
             * `reflection_probe_set_ambient_color` and `reflection_probe_set_ambient_energy`.
             *
             * Generated from Godot docs: RenderingServer.REFLECTION_PROBE_AMBIENT_COLOR
             */
            val COLOR: ReflectionProbeAmbientMode get() = ReflectionProbeAmbientMode(2L)
        }
    }

    @JvmInline
    value class DecalTexture(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Albedo texture slot in a decal (`Decal.texture_albedo`).
             *
             * Generated from Godot docs: RenderingServer.DECAL_TEXTURE_ALBEDO
             */
            val ALBEDO: DecalTexture get() = DecalTexture(0L)
            /**
             * Normal map texture slot in a decal (`Decal.texture_normal`).
             *
             * Generated from Godot docs: RenderingServer.DECAL_TEXTURE_NORMAL
             */
            val NORMAL: DecalTexture get() = DecalTexture(1L)
            /**
             * Occlusion/Roughness/Metallic texture slot in a decal (`Decal.texture_orm`).
             *
             * Generated from Godot docs: RenderingServer.DECAL_TEXTURE_ORM
             */
            val ORM: DecalTexture get() = DecalTexture(2L)
            /**
             * Emission texture slot in a decal (`Decal.texture_emission`).
             *
             * Generated from Godot docs: RenderingServer.DECAL_TEXTURE_EMISSION
             */
            val EMISSION: DecalTexture get() = DecalTexture(3L)
            /**
             * Represents the size of the `DecalTexture` enum.
             *
             * Generated from Godot docs: RenderingServer.DECAL_TEXTURE_MAX
             */
            val MAX: DecalTexture get() = DecalTexture(4L)
        }
    }

    @JvmInline
    value class DecalFilter(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Nearest-neighbor filter for decals (use for pixel art decals). No mipmaps are used for
             * rendering, which means decals at a distance will look sharp but grainy. This has roughly the
             * same performance cost as using mipmaps.
             *
             * Generated from Godot docs: RenderingServer.DECAL_FILTER_NEAREST
             */
            val NEAREST: DecalFilter get() = DecalFilter(0L)
            /**
             * Linear filter for decals (use for non-pixel art decals). No mipmaps are used for rendering,
             * which means decals at a distance will look smooth but blurry. This has roughly the same
             * performance cost as using mipmaps.
             *
             * Generated from Godot docs: RenderingServer.DECAL_FILTER_LINEAR
             */
            val LINEAR: DecalFilter get() = DecalFilter(1L)
            /**
             * Nearest-neighbor filter for decals (use for pixel art decals). Isotropic mipmaps are used for
             * rendering, which means decals at a distance will look smooth but blurry. This has roughly the
             * same performance cost as not using mipmaps.
             *
             * Generated from Godot docs: RenderingServer.DECAL_FILTER_NEAREST_MIPMAPS
             */
            val NEAREST_MIPMAPS: DecalFilter get() = DecalFilter(2L)
            /**
             * Linear filter for decals (use for non-pixel art decals). Isotropic mipmaps are used for
             * rendering, which means decals at a distance will look smooth but blurry. This has roughly the
             * same performance cost as not using mipmaps.
             *
             * Generated from Godot docs: RenderingServer.DECAL_FILTER_LINEAR_MIPMAPS
             */
            val LINEAR_MIPMAPS: DecalFilter get() = DecalFilter(3L)
            /**
             * Nearest-neighbor filter for decals (use for pixel art decals). Anisotropic mipmaps are used for
             * rendering, which means decals at a distance will look smooth and sharp when viewed from oblique
             * angles. This looks better compared to isotropic mipmaps, but is slower. The level of anisotropic
             * filtering is defined by
             * `ProjectSettings.rendering/textures/default_filters/anisotropic_filtering_level`.
             *
             * Generated from Godot docs: RenderingServer.DECAL_FILTER_NEAREST_MIPMAPS_ANISOTROPIC
             */
            val NEAREST_MIPMAPS_ANISOTROPIC: DecalFilter get() = DecalFilter(4L)
            /**
             * Linear filter for decals (use for non-pixel art decals). Anisotropic mipmaps are used for
             * rendering, which means decals at a distance will look smooth and sharp when viewed from oblique
             * angles. This looks better compared to isotropic mipmaps, but is slower. The level of anisotropic
             * filtering is defined by
             * `ProjectSettings.rendering/textures/default_filters/anisotropic_filtering_level`.
             *
             * Generated from Godot docs: RenderingServer.DECAL_FILTER_LINEAR_MIPMAPS_ANISOTROPIC
             */
            val LINEAR_MIPMAPS_ANISOTROPIC: DecalFilter get() = DecalFilter(5L)
        }
    }

    @JvmInline
    value class VoxelGIQuality(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Low `VoxelGI` rendering quality using 4 cones.
             *
             * Generated from Godot docs: RenderingServer.VOXEL_GI_QUALITY_LOW
             */
            val LOW: VoxelGIQuality get() = VoxelGIQuality(0L)
            /**
             * High `VoxelGI` rendering quality using 6 cones.
             *
             * Generated from Godot docs: RenderingServer.VOXEL_GI_QUALITY_HIGH
             */
            val HIGH: VoxelGIQuality get() = VoxelGIQuality(1L)
        }
    }

    @JvmInline
    value class ParticlesMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * 2D particles.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_MODE_2D
             */
            val MODE_2D: ParticlesMode get() = ParticlesMode(0L)
            /**
             * 3D particles.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_MODE_3D
             */
            val MODE_3D: ParticlesMode get() = ParticlesMode(1L)
        }
    }

    @JvmInline
    value class ParticlesTransformAlign(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Do not align particle transforms relative to the camera or velocity.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_TRANSFORM_ALIGN_DISABLED
             */
            val DISABLED: ParticlesTransformAlign get() = ParticlesTransformAlign(0L)
            /**
             * Align each particle's Z axis to face the camera.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_TRANSFORM_ALIGN_Z_BILLBOARD
             */
            val Z_BILLBOARD: ParticlesTransformAlign get() = ParticlesTransformAlign(1L)
            /**
             * Align each particle's Y axis to the velocity vector.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_TRANSFORM_ALIGN_Y_TO_VELOCITY
             */
            val Y_TO_VELOCITY: ParticlesTransformAlign get() = ParticlesTransformAlign(2L)
            /**
             * Align each particle's Z axis to face the camera and Y axis to the velocity vector.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_TRANSFORM_ALIGN_Z_BILLBOARD_Y_TO_VELOCITY
             */
            val Z_BILLBOARD_Y_TO_VELOCITY: ParticlesTransformAlign get() = ParticlesTransformAlign(3L)
            /**
             * Billboard each particles around a local axis.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_TRANSFORM_ALIGN_LOCAL_BILLBOARD
             */
            val LOCAL_BILLBOARD: ParticlesTransformAlign get() = ParticlesTransformAlign(4L)
        }
    }

    @JvmInline
    value class ParticlesTransformAlignCustomSrc(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Do not read from CUSTOM when performing billboarding.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_ALIGN_CHANNEL_FILTER_DISABLED
             */
            val DISABLED: ParticlesTransformAlignCustomSrc get() = ParticlesTransformAlignCustomSrc(0L)
            /**
             * Read from `CUSTOM.x` when performing billboarding and use it as an angle, in radians.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_ALIGN_CHANNEL_FILTER_X
             */
            val X: ParticlesTransformAlignCustomSrc get() = ParticlesTransformAlignCustomSrc(1L)
            /**
             * Read from `CUSTOM.y` when performing billboarding and use it as an angle, in radians.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_ALIGN_CHANNEL_FILTER_Y
             */
            val Y: ParticlesTransformAlignCustomSrc get() = ParticlesTransformAlignCustomSrc(2L)
            /**
             * Read from `CUSTOM.z` when performing billboarding and use it as an angle, in radians.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_ALIGN_CHANNEL_FILTER_Z
             */
            val Z: ParticlesTransformAlignCustomSrc get() = ParticlesTransformAlignCustomSrc(3L)
            /**
             * Read from `CUSTOM.w` when performing billboarding and use it as an angle, in radians.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_ALIGN_CHANNEL_FILTER_W
             */
            val W: ParticlesTransformAlignCustomSrc get() = ParticlesTransformAlignCustomSrc(4L)
        }
    }

    @JvmInline
    value class ParticlesTransformAlignAxis(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use the X axis for local billboarding.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_ALIGN_AXIS_X
             */
            val X: ParticlesTransformAlignAxis get() = ParticlesTransformAlignAxis(0L)
            /**
             * Use the Y axis for local billboarding.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_ALIGN_AXIS_Y
             */
            val Y: ParticlesTransformAlignAxis get() = ParticlesTransformAlignAxis(1L)
        }
    }

    @JvmInline
    value class ParticlesDrawOrder(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Draw particles in the order that they appear in the particles array.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_DRAW_ORDER_INDEX
             */
            val INDEX: ParticlesDrawOrder get() = ParticlesDrawOrder(0L)
            /**
             * Sort particles based on their lifetime. In other words, the particle with the highest lifetime
             * is drawn at the front.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_DRAW_ORDER_LIFETIME
             */
            val LIFETIME: ParticlesDrawOrder get() = ParticlesDrawOrder(1L)
            /**
             * Sort particles based on the inverse of their lifetime. In other words, the particle with the
             * lowest lifetime is drawn at the front.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_DRAW_ORDER_REVERSE_LIFETIME
             */
            val REVERSE_LIFETIME: ParticlesDrawOrder get() = ParticlesDrawOrder(2L)
            /**
             * Sort particles based on their distance to the camera.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_DRAW_ORDER_VIEW_DEPTH
             */
            val VIEW_DEPTH: ParticlesDrawOrder get() = ParticlesDrawOrder(3L)
        }
    }

    @JvmInline
    value class ParticlesCollisionType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Sphere attractor type for `GPUParticles3D` (see `GPUParticlesAttractorSphere3D`).
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_COLLISION_TYPE_SPHERE_ATTRACT
             */
            val SPHERE_ATTRACT: ParticlesCollisionType get() = ParticlesCollisionType(0L)
            /**
             * Box attractor type for `GPUParticles3D` (see `GPUParticlesAttractorBox3D`).
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_COLLISION_TYPE_BOX_ATTRACT
             */
            val BOX_ATTRACT: ParticlesCollisionType get() = ParticlesCollisionType(1L)
            /**
             * Vector field attractor type for `GPUParticles3D` (see `GPUParticlesAttractorVectorField3D`).
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_COLLISION_TYPE_VECTOR_FIELD_ATTRACT
             */
            val VECTOR_FIELD_ATTRACT: ParticlesCollisionType get() = ParticlesCollisionType(2L)
            /**
             * Sphere collision type for `GPUParticles3D` (see `GPUParticlesCollisionSphere3D`).
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_COLLISION_TYPE_SPHERE_COLLIDE
             */
            val SPHERE_COLLIDE: ParticlesCollisionType get() = ParticlesCollisionType(3L)
            /**
             * Box collision type for `GPUParticles3D` (see `GPUParticlesCollisionBox3D`).
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_COLLISION_TYPE_BOX_COLLIDE
             */
            val BOX_COLLIDE: ParticlesCollisionType get() = ParticlesCollisionType(4L)
            /**
             * Signed distance field collision type for `GPUParticles3D` (see `GPUParticlesCollisionSDF3D`).
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_COLLISION_TYPE_SDF_COLLIDE
             */
            val SDF_COLLIDE: ParticlesCollisionType get() = ParticlesCollisionType(5L)
            /**
             * Heightfield collision type for `GPUParticles3D` (see `GPUParticlesCollisionHeightField3D`).
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_COLLISION_TYPE_HEIGHTFIELD_COLLIDE
             */
            val HEIGHTFIELD_COLLIDE: ParticlesCollisionType get() = ParticlesCollisionType(6L)
        }
    }

    @JvmInline
    value class ParticlesCollisionHeightfieldResolution(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * 256×256 heightfield resolution for `GPUParticlesCollisionHeightField3D`.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_COLLISION_HEIGHTFIELD_RESOLUTION_256
             */
            val RESOLUTION_256: ParticlesCollisionHeightfieldResolution get() = ParticlesCollisionHeightfieldResolution(0L)
            /**
             * 512×512 heightfield resolution for `GPUParticlesCollisionHeightField3D`.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_COLLISION_HEIGHTFIELD_RESOLUTION_512
             */
            val RESOLUTION_512: ParticlesCollisionHeightfieldResolution get() = ParticlesCollisionHeightfieldResolution(1L)
            /**
             * 1024×1024 heightfield resolution for `GPUParticlesCollisionHeightField3D`.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_COLLISION_HEIGHTFIELD_RESOLUTION_1024
             */
            val RESOLUTION_1024: ParticlesCollisionHeightfieldResolution get() = ParticlesCollisionHeightfieldResolution(2L)
            /**
             * 2048×2048 heightfield resolution for `GPUParticlesCollisionHeightField3D`.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_COLLISION_HEIGHTFIELD_RESOLUTION_2048
             */
            val RESOLUTION_2048: ParticlesCollisionHeightfieldResolution get() = ParticlesCollisionHeightfieldResolution(3L)
            /**
             * 4096×4096 heightfield resolution for `GPUParticlesCollisionHeightField3D`.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_COLLISION_HEIGHTFIELD_RESOLUTION_4096
             */
            val RESOLUTION_4096: ParticlesCollisionHeightfieldResolution get() = ParticlesCollisionHeightfieldResolution(4L)
            /**
             * 8192×8192 heightfield resolution for `GPUParticlesCollisionHeightField3D`.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_COLLISION_HEIGHTFIELD_RESOLUTION_8192
             */
            val RESOLUTION_8192: ParticlesCollisionHeightfieldResolution get() = ParticlesCollisionHeightfieldResolution(5L)
            /**
             * Represents the size of the `ParticlesCollisionHeightfieldResolution` enum.
             *
             * Generated from Godot docs: RenderingServer.PARTICLES_COLLISION_HEIGHTFIELD_RESOLUTION_MAX
             */
            val MAX: ParticlesCollisionHeightfieldResolution get() = ParticlesCollisionHeightfieldResolution(6L)
        }
    }

    @JvmInline
    value class FogVolumeShape(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * `FogVolume` will be shaped like an ellipsoid (stretched sphere).
             *
             * Generated from Godot docs: RenderingServer.FOG_VOLUME_SHAPE_ELLIPSOID
             */
            val ELLIPSOID: FogVolumeShape get() = FogVolumeShape(0L)
            /**
             * `FogVolume` will be shaped like a cone pointing upwards (in local coordinates). The cone's angle
             * is set automatically to fill the size. The cone will be adjusted to fit within the size. Rotate
             * the `FogVolume` node to reorient the cone. Non-uniform scaling via size is not supported (scale
             * the `FogVolume` node instead).
             *
             * Generated from Godot docs: RenderingServer.FOG_VOLUME_SHAPE_CONE
             */
            val CONE: FogVolumeShape get() = FogVolumeShape(1L)
            /**
             * `FogVolume` will be shaped like an upright cylinder (in local coordinates). Rotate the
             * `FogVolume` node to reorient the cylinder. The cylinder will be adjusted to fit within the size.
             * Non-uniform scaling via size is not supported (scale the `FogVolume` node instead).
             *
             * Generated from Godot docs: RenderingServer.FOG_VOLUME_SHAPE_CYLINDER
             */
            val CYLINDER: FogVolumeShape get() = FogVolumeShape(2L)
            /**
             * `FogVolume` will be shaped like a box.
             *
             * Generated from Godot docs: RenderingServer.FOG_VOLUME_SHAPE_BOX
             */
            val BOX: FogVolumeShape get() = FogVolumeShape(3L)
            /**
             * `FogVolume` will have no shape, will cover the whole world and will not be culled.
             *
             * Generated from Godot docs: RenderingServer.FOG_VOLUME_SHAPE_WORLD
             */
            val WORLD: FogVolumeShape get() = FogVolumeShape(4L)
            /**
             * Represents the size of the `FogVolumeShape` enum.
             *
             * Generated from Godot docs: RenderingServer.FOG_VOLUME_SHAPE_MAX
             */
            val MAX: FogVolumeShape get() = FogVolumeShape(5L)
        }
    }

    @JvmInline
    value class ViewportScaling3DMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use bilinear scaling for the viewport's 3D buffer. The amount of scaling can be set using
             * `Viewport.scaling_3d_scale`. Values less than `1.0` will result in undersampling while values
             * greater than `1.0` will result in supersampling. A value of `1.0` disables scaling.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_SCALING_3D_MODE_BILINEAR
             */
            val BILINEAR: ViewportScaling3DMode get() = ViewportScaling3DMode(0L)
            /**
             * Use AMD FidelityFX Super Resolution 1.0 upscaling for the viewport's 3D buffer. The amount of
             * scaling can be set using `Viewport.scaling_3d_scale`. Values less than `1.0` will result in the
             * viewport being upscaled using FSR. Values greater than `1.0` are not supported and bilinear
             * downsampling will be used instead. A value of `1.0` disables scaling.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_SCALING_3D_MODE_FSR
             */
            val FSR: ViewportScaling3DMode get() = ViewportScaling3DMode(1L)
            /**
             * Use AMD FidelityFX Super Resolution 2.2 upscaling for the viewport's 3D buffer. The amount of
             * scaling can be set using `Viewport.scaling_3d_scale`. Values less than `1.0` will result in the
             * viewport being upscaled using FSR2. Values greater than `1.0` are not supported and bilinear
             * downsampling will be used instead. A value of `1.0` will use FSR2 at native resolution as a TAA
             * solution.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_SCALING_3D_MODE_FSR2
             */
            val FSR2: ViewportScaling3DMode get() = ViewportScaling3DMode(2L)
            /**
             * Use MetalFX spatial upscaling for the viewport's 3D buffer. The amount of scaling can be set
             * using `Viewport.scaling_3d_scale`. Values less than `1.0` will result in the viewport being
             * upscaled using MetalFX. Values greater than `1.0` are not supported and bilinear downsampling
             * will be used instead. A value of `1.0` disables scaling. Note: Only supported when the Metal
             * rendering driver is in use, which limits this scaling mode to macOS and iOS.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_SCALING_3D_MODE_METALFX_SPATIAL
             */
            val METALFX_SPATIAL: ViewportScaling3DMode get() = ViewportScaling3DMode(3L)
            /**
             * Use MetalFX temporal upscaling for the viewport's 3D buffer. The amount of scaling can be set
             * using `Viewport.scaling_3d_scale`. Values less than `1.0` will result in the viewport being
             * upscaled using MetalFX. Values greater than `1.0` are not supported and bilinear downsampling
             * will be used instead. A value of `1.0` will use MetalFX at native resolution as a TAA solution.
             * Note: Only supported when the Metal rendering driver is in use, which limits this scaling mode
             * to macOS and iOS.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_SCALING_3D_MODE_METALFX_TEMPORAL
             */
            val METALFX_TEMPORAL: ViewportScaling3DMode get() = ViewportScaling3DMode(4L)
            /**
             * Use nearest-neighbor filtering for the viewport's 3D buffer. This looks crisper than
             * `VIEWPORT_SCALING_3D_MODE_BILINEAR` and has no additional rendering cost. The amount of scaling
             * can be set using `Viewport.scaling_3d_scale`. Values greater than `1.0` are not supported and
             * bilinear downsampling will be used instead. A value of `1.0` disables scaling. Note: When using
             * the Nearest scaling mode, to avoid uneven pixel scaling, it's highly recommended to use a value
             * equal to an integer divisor with a dividend of `1`. For example, it's best to use a scale of
             * `0.5` (1/2), `0.3333` (1/3), `0.25` (1/4), `0.2` (1/5), and so on.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_SCALING_3D_MODE_NEAREST
             */
            val NEAREST: ViewportScaling3DMode get() = ViewportScaling3DMode(5L)
            /**
             * Represents the size of the `ViewportScaling3DMode` enum.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_SCALING_3D_MODE_MAX
             */
            val MAX: ViewportScaling3DMode get() = ViewportScaling3DMode(6L)
        }
    }

    @JvmInline
    value class ViewportUpdateMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Do not update the viewport's render target.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_UPDATE_DISABLED
             */
            val DISABLED: ViewportUpdateMode get() = ViewportUpdateMode(0L)
            /**
             * Update the viewport's render target once, then switch to `VIEWPORT_UPDATE_DISABLED`.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_UPDATE_ONCE
             */
            val ONCE: ViewportUpdateMode get() = ViewportUpdateMode(1L)
            /**
             * Update the viewport's render target only when it is visible. This is the default value.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_UPDATE_WHEN_VISIBLE
             */
            val WHEN_VISIBLE: ViewportUpdateMode get() = ViewportUpdateMode(2L)
            /**
             * Update the viewport's render target only when its parent is visible.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_UPDATE_WHEN_PARENT_VISIBLE
             */
            val WHEN_PARENT_VISIBLE: ViewportUpdateMode get() = ViewportUpdateMode(3L)
            /**
             * Always update the viewport's render target.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_UPDATE_ALWAYS
             */
            val ALWAYS: ViewportUpdateMode get() = ViewportUpdateMode(4L)
        }
    }

    @JvmInline
    value class ViewportClearMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Always clear the viewport's render target before drawing.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_CLEAR_ALWAYS
             */
            val ALWAYS: ViewportClearMode get() = ViewportClearMode(0L)
            /**
             * Never clear the viewport's render target.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_CLEAR_NEVER
             */
            val NEVER: ViewportClearMode get() = ViewportClearMode(1L)
            /**
             * Clear the viewport's render target on the next frame, then switch to `VIEWPORT_CLEAR_NEVER`.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_CLEAR_ONLY_NEXT_FRAME
             */
            val ONLY_NEXT_FRAME: ViewportClearMode get() = ViewportClearMode(2L)
        }
    }

    @JvmInline
    value class ViewportEnvironmentMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Disable rendering of 3D environment over 2D canvas.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_ENVIRONMENT_DISABLED
             */
            val DISABLED: ViewportEnvironmentMode get() = ViewportEnvironmentMode(0L)
            /**
             * Enable rendering of 3D environment over 2D canvas.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_ENVIRONMENT_ENABLED
             */
            val ENABLED: ViewportEnvironmentMode get() = ViewportEnvironmentMode(1L)
            /**
             * Inherit enable/disable value from parent. If the topmost parent is also set to
             * `VIEWPORT_ENVIRONMENT_INHERIT`, then this has the same behavior as
             * `VIEWPORT_ENVIRONMENT_ENABLED`.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_ENVIRONMENT_INHERIT
             */
            val INHERIT: ViewportEnvironmentMode get() = ViewportEnvironmentMode(2L)
            /**
             * Represents the size of the `ViewportEnvironmentMode` enum.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_ENVIRONMENT_MAX
             */
            val MAX: ViewportEnvironmentMode get() = ViewportEnvironmentMode(3L)
        }
    }

    @JvmInline
    value class ViewportSDFOversize(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Do not oversize the 2D signed distance field. Occluders may disappear when touching the
             * viewport's edges, and `GPUParticles3D` collision may stop working earlier than intended. This
             * has the lowest GPU requirements.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_SDF_OVERSIZE_100_PERCENT
             */
            val OVERSIZE_100_PERCENT: ViewportSDFOversize get() = ViewportSDFOversize(0L)
            /**
             * 2D signed distance field covers 20% of the viewport's size outside the viewport on each side
             * (top, right, bottom, left).
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_SDF_OVERSIZE_120_PERCENT
             */
            val OVERSIZE_120_PERCENT: ViewportSDFOversize get() = ViewportSDFOversize(1L)
            /**
             * 2D signed distance field covers 50% of the viewport's size outside the viewport on each side
             * (top, right, bottom, left).
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_SDF_OVERSIZE_150_PERCENT
             */
            val OVERSIZE_150_PERCENT: ViewportSDFOversize get() = ViewportSDFOversize(2L)
            /**
             * 2D signed distance field covers 100% of the viewport's size outside the viewport on each side
             * (top, right, bottom, left). This has the highest GPU requirements.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_SDF_OVERSIZE_200_PERCENT
             */
            val OVERSIZE_200_PERCENT: ViewportSDFOversize get() = ViewportSDFOversize(3L)
            /**
             * Represents the size of the `ViewportSDFOversize` enum.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_SDF_OVERSIZE_MAX
             */
            val MAX: ViewportSDFOversize get() = ViewportSDFOversize(4L)
        }
    }

    @JvmInline
    value class ViewportSDFScale(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Full resolution 2D signed distance field scale. This has the highest GPU requirements.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_SDF_SCALE_100_PERCENT
             */
            val SCALE_100_PERCENT: ViewportSDFScale get() = ViewportSDFScale(0L)
            /**
             * Half resolution 2D signed distance field scale on each axis (25% of the viewport pixel count).
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_SDF_SCALE_50_PERCENT
             */
            val SCALE_50_PERCENT: ViewportSDFScale get() = ViewportSDFScale(1L)
            /**
             * Quarter resolution 2D signed distance field scale on each axis (6.25% of the viewport pixel
             * count). This has the lowest GPU requirements.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_SDF_SCALE_25_PERCENT
             */
            val SCALE_25_PERCENT: ViewportSDFScale get() = ViewportSDFScale(2L)
            /**
             * Represents the size of the `ViewportSDFScale` enum.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_SDF_SCALE_MAX
             */
            val MAX: ViewportSDFScale get() = ViewportSDFScale(3L)
        }
    }

    @JvmInline
    value class ViewportMSAA(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Multisample antialiasing for 3D is disabled. This is the default value, and also the fastest
             * setting.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_MSAA_DISABLED
             */
            val DISABLED: ViewportMSAA get() = ViewportMSAA(0L)
            /**
             * Multisample antialiasing uses 2 samples per pixel for 3D. This has a moderate impact on
             * performance.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_MSAA_2X
             */
            val MSAA_2X: ViewportMSAA get() = ViewportMSAA(1L)
            /**
             * Multisample antialiasing uses 4 samples per pixel for 3D. This has a high impact on performance.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_MSAA_4X
             */
            val MSAA_4X: ViewportMSAA get() = ViewportMSAA(2L)
            /**
             * Multisample antialiasing uses 8 samples per pixel for 3D. This has a very high impact on
             * performance. Likely unsupported on low-end and older hardware.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_MSAA_8X
             */
            val MSAA_8X: ViewportMSAA get() = ViewportMSAA(3L)
            /**
             * Represents the size of the `ViewportMSAA` enum.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_MSAA_MAX
             */
            val MAX: ViewportMSAA get() = ViewportMSAA(4L)
        }
    }

    @JvmInline
    value class ViewportAnisotropicFiltering(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Anisotropic filtering is disabled.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_ANISOTROPY_DISABLED
             */
            val DISABLED: ViewportAnisotropicFiltering get() = ViewportAnisotropicFiltering(0L)
            /**
             * Use 2× anisotropic filtering.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_ANISOTROPY_2X
             */
            val ANISOTROPY_2X: ViewportAnisotropicFiltering get() = ViewportAnisotropicFiltering(1L)
            /**
             * Use 4× anisotropic filtering. This is the default value.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_ANISOTROPY_4X
             */
            val ANISOTROPY_4X: ViewportAnisotropicFiltering get() = ViewportAnisotropicFiltering(2L)
            /**
             * Use 8× anisotropic filtering.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_ANISOTROPY_8X
             */
            val ANISOTROPY_8X: ViewportAnisotropicFiltering get() = ViewportAnisotropicFiltering(3L)
            /**
             * Use 16× anisotropic filtering.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_ANISOTROPY_16X
             */
            val ANISOTROPY_16X: ViewportAnisotropicFiltering get() = ViewportAnisotropicFiltering(4L)
            /**
             * Represents the size of the `ViewportAnisotropicFiltering` enum.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_ANISOTROPY_MAX
             */
            val MAX: ViewportAnisotropicFiltering get() = ViewportAnisotropicFiltering(5L)
        }
    }

    @JvmInline
    value class ViewportScreenSpaceAA(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Do not perform any antialiasing in the full screen post-process.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_SCREEN_SPACE_AA_DISABLED
             */
            val DISABLED: ViewportScreenSpaceAA get() = ViewportScreenSpaceAA(0L)
            /**
             * Use fast approximate antialiasing. FXAA is a popular screen-space antialiasing method, which is
             * fast but will make the image look blurry, especially at lower resolutions. It can still work
             * relatively well at large resolutions such as 1440p and 4K.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_SCREEN_SPACE_AA_FXAA
             */
            val FXAA: ViewportScreenSpaceAA get() = ViewportScreenSpaceAA(1L)
            /**
             * Use subpixel morphological antialiasing. SMAA may produce clearer results than FXAA, but at a
             * slightly higher performance cost.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_SCREEN_SPACE_AA_SMAA
             */
            val SMAA: ViewportScreenSpaceAA get() = ViewportScreenSpaceAA(2L)
            /**
             * Represents the size of the `ViewportScreenSpaceAA` enum.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_SCREEN_SPACE_AA_MAX
             */
            val MAX: ViewportScreenSpaceAA get() = ViewportScreenSpaceAA(3L)
        }
    }

    @JvmInline
    value class ViewportOcclusionCullingBuildQuality(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Low occlusion culling BVH build quality (as defined by Embree). Results in the lowest CPU usage,
             * but least effective culling.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_OCCLUSION_BUILD_QUALITY_LOW
             */
            val LOW: ViewportOcclusionCullingBuildQuality get() = ViewportOcclusionCullingBuildQuality(0L)
            /**
             * Medium occlusion culling BVH build quality (as defined by Embree).
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_OCCLUSION_BUILD_QUALITY_MEDIUM
             */
            val MEDIUM: ViewportOcclusionCullingBuildQuality get() = ViewportOcclusionCullingBuildQuality(1L)
            /**
             * High occlusion culling BVH build quality (as defined by Embree). Results in the highest CPU
             * usage, but most effective culling.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_OCCLUSION_BUILD_QUALITY_HIGH
             */
            val HIGH: ViewportOcclusionCullingBuildQuality get() = ViewportOcclusionCullingBuildQuality(2L)
        }
    }

    @JvmInline
    value class ViewportRenderInfo(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Number of objects drawn in a single frame.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_RENDER_INFO_OBJECTS_IN_FRAME
             */
            val OBJECTS_IN_FRAME: ViewportRenderInfo get() = ViewportRenderInfo(0L)
            /**
             * Number of points, lines, or triangles drawn in a single frame.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_RENDER_INFO_PRIMITIVES_IN_FRAME
             */
            val PRIMITIVES_IN_FRAME: ViewportRenderInfo get() = ViewportRenderInfo(1L)
            /**
             * Number of draw calls during this frame.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_RENDER_INFO_DRAW_CALLS_IN_FRAME
             */
            val DRAW_CALLS_IN_FRAME: ViewportRenderInfo get() = ViewportRenderInfo(2L)
            /**
             * Represents the size of the `ViewportRenderInfo` enum.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_RENDER_INFO_MAX
             */
            val MAX: ViewportRenderInfo get() = ViewportRenderInfo(3L)
        }
    }

    @JvmInline
    value class ViewportRenderInfoType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Visible render pass (excluding shadows).
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_RENDER_INFO_TYPE_VISIBLE
             */
            val VISIBLE: ViewportRenderInfoType get() = ViewportRenderInfoType(0L)
            /**
             * Shadow render pass. Objects will be rendered several times depending on the number of amounts of
             * lights with shadows and the number of directional shadow splits.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_RENDER_INFO_TYPE_SHADOW
             */
            val SHADOW: ViewportRenderInfoType get() = ViewportRenderInfoType(1L)
            /**
             * Canvas item rendering. This includes all 2D rendering.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_RENDER_INFO_TYPE_CANVAS
             */
            val CANVAS: ViewportRenderInfoType get() = ViewportRenderInfoType(2L)
            /**
             * Represents the size of the `ViewportRenderInfoType` enum.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_RENDER_INFO_TYPE_MAX
             */
            val MAX: ViewportRenderInfoType get() = ViewportRenderInfoType(3L)
        }
    }

    @JvmInline
    value class ViewportDebugDraw(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Debug draw is disabled. Default setting.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_DISABLED
             */
            val DISABLED: ViewportDebugDraw get() = ViewportDebugDraw(0L)
            /**
             * Objects are displayed without light information.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_UNSHADED
             */
            val UNSHADED: ViewportDebugDraw get() = ViewportDebugDraw(1L)
            /**
             * Objects are displayed with only light information. Note: When using this debug draw mode, custom
             * shaders are ignored since all materials in the scene temporarily use a debug material. This
             * means the result from custom shader functions (such as vertex displacement) won't be visible
             * anymore when using this debug draw mode.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_LIGHTING
             */
            val LIGHTING: ViewportDebugDraw get() = ViewportDebugDraw(2L)
            /**
             * Objects are displayed semi-transparent with additive blending so you can see where they are
             * drawing over top of one another. A higher overdraw (represented by brighter colors) means you
             * are wasting performance on drawing pixels that are being hidden behind others. Note: When using
             * this debug draw mode, custom shaders are ignored since all materials in the scene temporarily
             * use a debug material. This means the result from custom shader functions (such as vertex
             * displacement) won't be visible anymore when using this debug draw mode.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_OVERDRAW
             */
            val OVERDRAW: ViewportDebugDraw get() = ViewportDebugDraw(3L)
            /**
             * Debug draw draws objects in wireframe. Note: `set_debug_generate_wireframes` must be called
             * before loading any meshes for wireframes to be visible when using the Compatibility renderer.
             * Note: In the Compatibility renderer, backfaces are always visible when using wireframe
             * rendering. In the Forward+ and Mobile renderers, wireframes follow the material's backface
             * culling properties instead.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_WIREFRAME
             */
            val WIREFRAME: ViewportDebugDraw get() = ViewportDebugDraw(4L)
            /**
             * Normal buffer is drawn instead of regular scene so you can see the per-pixel normals that will
             * be used by post-processing effects.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_NORMAL_BUFFER
             */
            val NORMAL_BUFFER: ViewportDebugDraw get() = ViewportDebugDraw(5L)
            /**
             * Objects are displayed with only the albedo value from `VoxelGI`s. Requires at least one visible
             * `VoxelGI` node that has been baked to have a visible effect. Note: Only supported when using the
             * Forward+ rendering method.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_VOXEL_GI_ALBEDO
             */
            val VOXEL_GI_ALBEDO: ViewportDebugDraw get() = ViewportDebugDraw(6L)
            /**
             * Objects are displayed with only the lighting value from `VoxelGI`s. Requires at least one
             * visible `VoxelGI` node that has been baked to have a visible effect. Note: Only supported when
             * using the Forward+ rendering method.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_VOXEL_GI_LIGHTING
             */
            val VOXEL_GI_LIGHTING: ViewportDebugDraw get() = ViewportDebugDraw(7L)
            /**
             * Objects are displayed with only the emission color from `VoxelGI`s. Requires at least one
             * visible `VoxelGI` node that has been baked to have a visible effect. Note: Only supported when
             * using the Forward+ rendering method.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_VOXEL_GI_EMISSION
             */
            val VOXEL_GI_EMISSION: ViewportDebugDraw get() = ViewportDebugDraw(8L)
            /**
             * Draws the shadow atlas that stores shadows from `OmniLight3D`s and `SpotLight3D`s in the upper
             * left quadrant of the `Viewport`.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_SHADOW_ATLAS
             */
            val SHADOW_ATLAS: ViewportDebugDraw get() = ViewportDebugDraw(9L)
            /**
             * Draws the shadow atlas that stores shadows from `DirectionalLight3D`s in the upper left quadrant
             * of the `Viewport`. The slice of the camera frustum related to the shadow map cascade is
             * superimposed to visualize coverage. The color of each slice matches the colors used for
             * `VIEWPORT_DEBUG_DRAW_PSSM_SPLITS`. When shadow cascades are blended the overlap is taken into
             * account when drawing the frustum slices. The last cascade shows all frustum slices to illustrate
             * the coverage of all slices.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_DIRECTIONAL_SHADOW_ATLAS
             */
            val DIRECTIONAL_SHADOW_ATLAS: ViewportDebugDraw get() = ViewportDebugDraw(10L)
            /**
             * Draws the estimated scene luminance. This is a 1×1 texture that is generated when autoexposure
             * is enabled to control the scene's exposure. Note: Only supported when using the Forward+ or
             * Mobile rendering methods.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_SCENE_LUMINANCE
             */
            val SCENE_LUMINANCE: ViewportDebugDraw get() = ViewportDebugDraw(11L)
            /**
             * Draws the screen space ambient occlusion texture instead of the scene so that you can clearly
             * see how it is affecting objects. In order for this display mode to work, you must have
             * `Environment.ssao_enabled` set in your `WorldEnvironment`. Note: Only supported when using the
             * Forward+ rendering method.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_SSAO
             */
            val SSAO: ViewportDebugDraw get() = ViewportDebugDraw(12L)
            /**
             * Draws the screen space indirect lighting texture instead of the scene so that you can clearly
             * see how it is affecting objects. In order for this display mode to work, you must have
             * `Environment.ssil_enabled` set in your `WorldEnvironment`. Note: Only supported when using the
             * Forward+ rendering method.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_SSIL
             */
            val SSIL: ViewportDebugDraw get() = ViewportDebugDraw(13L)
            /**
             * Colors each PSSM split for the `DirectionalLight3D`s in the scene a different color so you can
             * see where the splits are. In order (from closest to furthest from the camera), they are colored
             * red, green, blue, and yellow. Note: When using this debug draw mode, custom shaders are ignored
             * since all materials in the scene temporarily use a debug material. This means the result from
             * custom shader functions (such as vertex displacement) won't be visible anymore when using this
             * debug draw mode. Note: Only supported when using the Forward+ or Mobile rendering methods.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_PSSM_SPLITS
             */
            val PSSM_SPLITS: ViewportDebugDraw get() = ViewportDebugDraw(14L)
            /**
             * Draws the decal atlas that stores decal textures from `Decal`s. Note: Only supported when using
             * the Forward+ or Mobile rendering methods.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_DECAL_ATLAS
             */
            val DECAL_ATLAS: ViewportDebugDraw get() = ViewportDebugDraw(15L)
            /**
             * Draws SDFGI cascade data. This is the data structure that is used to bounce lighting against and
             * create reflections. Note: Only supported when using the Forward+ rendering method.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_SDFGI
             */
            val SDFGI: ViewportDebugDraw get() = ViewportDebugDraw(16L)
            /**
             * Draws SDFGI probe data. This is the data structure that is used to give indirect lighting
             * dynamic objects moving within the scene. When in the editor, left-clicking a probe will display
             * additional bright dots that show its occlusion information. A white dot means the light is not
             * occluded at all at the dot's position, while a red dot means the light is fully occluded.
             * Intermediate values are possible. Note: Only supported when using the Forward+ rendering method.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_SDFGI_PROBES
             */
            val SDFGI_PROBES: ViewportDebugDraw get() = ViewportDebugDraw(17L)
            /**
             * Draws the global illumination buffer from `VoxelGI` or SDFGI. Requires `VoxelGI` (at least one
             * visible baked VoxelGI node) or SDFGI (`Environment.sdfgi_enabled`) to be enabled to have a
             * visible effect. Note: Only supported when using the Forward+ rendering method.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_GI_BUFFER
             */
            val GI_BUFFER: ViewportDebugDraw get() = ViewportDebugDraw(18L)
            /**
             * Disable mesh LOD. All meshes are drawn with full detail, which can be used to compare
             * performance.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_DISABLE_LOD
             */
            val DISABLE_LOD: ViewportDebugDraw get() = ViewportDebugDraw(19L)
            /**
             * Draws the `OmniLight3D` cluster. Clustering determines where lights are positioned in
             * screen-space, which allows the engine to only process these portions of the screen for lighting.
             * Note: Only supported when using the Forward+ rendering method.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_CLUSTER_OMNI_LIGHTS
             */
            val CLUSTER_OMNI_LIGHTS: ViewportDebugDraw get() = ViewportDebugDraw(20L)
            /**
             * Draws the `SpotLight3D` cluster. Clustering determines where lights are positioned in
             * screen-space, which allows the engine to only process these portions of the screen for lighting.
             * Note: Only supported when using the Forward+ rendering method.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_CLUSTER_SPOT_LIGHTS
             */
            val CLUSTER_SPOT_LIGHTS: ViewportDebugDraw get() = ViewportDebugDraw(21L)
            /**
             * Draws the `Decal` cluster. Clustering determines where decals are positioned in screen-space,
             * which allows the engine to only process these portions of the screen for decals. Note: Only
             * supported when using the Forward+ rendering method.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_CLUSTER_DECALS
             */
            val CLUSTER_DECALS: ViewportDebugDraw get() = ViewportDebugDraw(22L)
            /**
             * Draws the `ReflectionProbe` cluster. Clustering determines where reflection probes are
             * positioned in screen-space, which allows the engine to only process these portions of the screen
             * for reflection probes. Note: Only supported when using the Forward+ rendering method.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_CLUSTER_REFLECTION_PROBES
             */
            val CLUSTER_REFLECTION_PROBES: ViewportDebugDraw get() = ViewportDebugDraw(23L)
            /**
             * Draws the occlusion culling buffer. This low-resolution occlusion culling buffer is rasterized
             * on the CPU and is used to check whether instances are occluded by other objects. Note: Only
             * supported when using the Forward+ or Mobile rendering methods.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_OCCLUDERS
             */
            val OCCLUDERS: ViewportDebugDraw get() = ViewportDebugDraw(24L)
            /**
             * Draws the motion vectors buffer. This is used by temporal antialiasing to correct for motion
             * that occurs during gameplay. Note: Only supported when using the Forward+ rendering method.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_MOTION_VECTORS
             */
            val MOTION_VECTORS: ViewportDebugDraw get() = ViewportDebugDraw(25L)
            /**
             * Internal buffer is drawn instead of regular scene so you can see the per-pixel output that will
             * be used by post-processing effects. Note: Only supported when using the Forward+ or Mobile
             * rendering methods.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_DEBUG_DRAW_INTERNAL_BUFFER
             */
            val INTERNAL_BUFFER: ViewportDebugDraw get() = ViewportDebugDraw(26L)
        }
    }

    @JvmInline
    value class ViewportVRSMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Variable rate shading is disabled.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_VRS_DISABLED
             */
            val DISABLED: ViewportVRSMode get() = ViewportVRSMode(0L)
            /**
             * Variable rate shading uses a texture. Note, for stereoscopic use a texture atlas with a texture
             * for each view.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_VRS_TEXTURE
             */
            val TEXTURE: ViewportVRSMode get() = ViewportVRSMode(1L)
            /**
             * Variable rate shading texture is supplied by the primary `XRInterface`. Note that this may
             * override the update mode.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_VRS_XR
             */
            val XR: ViewportVRSMode get() = ViewportVRSMode(2L)
            /**
             * Represents the size of the `ViewportVRSMode` enum.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_VRS_MAX
             */
            val MAX: ViewportVRSMode get() = ViewportVRSMode(3L)
        }
    }

    @JvmInline
    value class ViewportVRSUpdateMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The input texture for variable rate shading will not be processed.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_VRS_UPDATE_DISABLED
             */
            val DISABLED: ViewportVRSUpdateMode get() = ViewportVRSUpdateMode(0L)
            /**
             * The input texture for variable rate shading will be processed once.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_VRS_UPDATE_ONCE
             */
            val ONCE: ViewportVRSUpdateMode get() = ViewportVRSUpdateMode(1L)
            /**
             * The input texture for variable rate shading will be processed each frame.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_VRS_UPDATE_ALWAYS
             */
            val ALWAYS: ViewportVRSUpdateMode get() = ViewportVRSUpdateMode(2L)
            /**
             * Represents the size of the `ViewportVRSUpdateMode` enum.
             *
             * Generated from Godot docs: RenderingServer.VIEWPORT_VRS_UPDATE_MAX
             */
            val MAX: ViewportVRSUpdateMode get() = ViewportVRSUpdateMode(3L)
        }
    }

    @JvmInline
    value class SkyMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Automatically selects the appropriate process mode based on your sky shader. If your shader uses
             * `TIME` or `POSITION`, this will use `SKY_MODE_REALTIME`. If your shader uses any of the
             * `LIGHT_*` variables or any custom uniforms, this uses `SKY_MODE_INCREMENTAL`. Otherwise, this
             * defaults to `SKY_MODE_QUALITY`.
             *
             * Generated from Godot docs: RenderingServer.SKY_MODE_AUTOMATIC
             */
            val AUTOMATIC: SkyMode get() = SkyMode(0L)
            /**
             * Uses high quality importance sampling to process the radiance map. In general, this results in
             * much higher quality than `SKY_MODE_REALTIME` but takes much longer to generate. This should not
             * be used if you plan on changing the sky at runtime. If you are finding that the reflection is
             * not blurry enough and is showing sparkles or fireflies, try increasing
             * `ProjectSettings.rendering/reflections/sky_reflections/ggx_samples`.
             *
             * Generated from Godot docs: RenderingServer.SKY_MODE_QUALITY
             */
            val QUALITY: SkyMode get() = SkyMode(1L)
            /**
             * Uses the same high quality importance sampling to process the radiance map as
             * `SKY_MODE_QUALITY`, but updates over several frames. The number of frames is determined by
             * `ProjectSettings.rendering/reflections/sky_reflections/roughness_layers`. Use this when you need
             * highest quality radiance maps, but have a sky that updates slowly.
             *
             * Generated from Godot docs: RenderingServer.SKY_MODE_INCREMENTAL
             */
            val INCREMENTAL: SkyMode get() = SkyMode(2L)
            /**
             * Uses the fast filtering algorithm to process the radiance map. In general this results in lower
             * quality, but substantially faster run times. If you need better quality, but still need to
             * update the sky every frame, consider turning on
             * `ProjectSettings.rendering/reflections/sky_reflections/fast_filter_high_quality`. Note: The fast
             * filtering algorithm is limited to 256×256 cubemaps, so `sky_set_radiance_size` must be set to
             * `256`. Otherwise, a warning is printed and the overridden radiance size is ignored.
             *
             * Generated from Godot docs: RenderingServer.SKY_MODE_REALTIME
             */
            val REALTIME: SkyMode get() = SkyMode(3L)
        }
    }

    @JvmInline
    value class CompositorEffectFlags(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The rendering effect requires the color buffer to be resolved if MSAA is enabled.
             *
             * Generated from Godot docs: RenderingServer.COMPOSITOR_EFFECT_FLAG_ACCESS_RESOLVED_COLOR
             */
            val ACCESS_RESOLVED_COLOR: CompositorEffectFlags get() = CompositorEffectFlags(1L)
            /**
             * The rendering effect requires the depth buffer to be resolved if MSAA is enabled.
             *
             * Generated from Godot docs: RenderingServer.COMPOSITOR_EFFECT_FLAG_ACCESS_RESOLVED_DEPTH
             */
            val ACCESS_RESOLVED_DEPTH: CompositorEffectFlags get() = CompositorEffectFlags(2L)
            /**
             * The rendering effect requires motion vectors to be produced.
             *
             * Generated from Godot docs: RenderingServer.COMPOSITOR_EFFECT_FLAG_NEEDS_MOTION_VECTORS
             */
            val NEEDS_MOTION_VECTORS: CompositorEffectFlags get() = CompositorEffectFlags(4L)
            /**
             * The rendering effect requires normals and roughness g-buffer to be produced (Forward+ only).
             *
             * Generated from Godot docs: RenderingServer.COMPOSITOR_EFFECT_FLAG_NEEDS_ROUGHNESS
             */
            val NEEDS_ROUGHNESS: CompositorEffectFlags get() = CompositorEffectFlags(8L)
            /**
             * The rendering effect requires specular data to be separated out (Forward+ only).
             *
             * Generated from Godot docs: RenderingServer.COMPOSITOR_EFFECT_FLAG_NEEDS_SEPARATE_SPECULAR
             */
            val NEEDS_SEPARATE_SPECULAR: CompositorEffectFlags get() = CompositorEffectFlags(16L)
        }
    }

    @JvmInline
    value class CompositorEffectCallbackType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The callback is called before our opaque rendering pass, but after depth prepass (if
             * applicable).
             *
             * Generated from Godot docs: RenderingServer.COMPOSITOR_EFFECT_CALLBACK_TYPE_PRE_OPAQUE
             */
            val PRE_OPAQUE: CompositorEffectCallbackType get() = CompositorEffectCallbackType(0L)
            /**
             * The callback is called after our opaque rendering pass, but before our sky is rendered.
             *
             * Generated from Godot docs: RenderingServer.COMPOSITOR_EFFECT_CALLBACK_TYPE_POST_OPAQUE
             */
            val POST_OPAQUE: CompositorEffectCallbackType get() = CompositorEffectCallbackType(1L)
            /**
             * The callback is called after our sky is rendered, but before our back buffers are created (and
             * if enabled, before subsurface scattering and/or screen space reflections).
             *
             * Generated from Godot docs: RenderingServer.COMPOSITOR_EFFECT_CALLBACK_TYPE_POST_SKY
             */
            val POST_SKY: CompositorEffectCallbackType get() = CompositorEffectCallbackType(2L)
            /**
             * The callback is called before our transparent rendering pass, but after our sky is rendered and
             * we've created our back buffers.
             *
             * Generated from Godot docs: RenderingServer.COMPOSITOR_EFFECT_CALLBACK_TYPE_PRE_TRANSPARENT
             */
            val PRE_TRANSPARENT: CompositorEffectCallbackType get() = CompositorEffectCallbackType(3L)
            /**
             * The callback is called after our transparent rendering pass, but before any built-in
             * post-processing effects and output to our render target.
             *
             * Generated from Godot docs: RenderingServer.COMPOSITOR_EFFECT_CALLBACK_TYPE_POST_TRANSPARENT
             */
            val POST_TRANSPARENT: CompositorEffectCallbackType get() = CompositorEffectCallbackType(4L)
            val ANY: CompositorEffectCallbackType get() = CompositorEffectCallbackType(-1L)
        }
    }

    @JvmInline
    value class EnvironmentBG(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use the clear color as background.
             *
             * Generated from Godot docs: RenderingServer.ENV_BG_CLEAR_COLOR
             */
            val CLEAR_COLOR: EnvironmentBG get() = EnvironmentBG(0L)
            /**
             * Use a specified color as the background.
             *
             * Generated from Godot docs: RenderingServer.ENV_BG_COLOR
             */
            val COLOR: EnvironmentBG get() = EnvironmentBG(1L)
            /**
             * Use a sky resource for the background.
             *
             * Generated from Godot docs: RenderingServer.ENV_BG_SKY
             */
            val SKY: EnvironmentBG get() = EnvironmentBG(2L)
            /**
             * Use a specified canvas layer as the background. This can be useful for instantiating a 2D scene
             * in a 3D world.
             *
             * Generated from Godot docs: RenderingServer.ENV_BG_CANVAS
             */
            val CANVAS: EnvironmentBG get() = EnvironmentBG(3L)
            /**
             * Do not clear the background, use whatever was rendered last frame as the background.
             *
             * Generated from Godot docs: RenderingServer.ENV_BG_KEEP
             */
            val KEEP: EnvironmentBG get() = EnvironmentBG(4L)
            /**
             * Displays a camera feed in the background.
             *
             * Generated from Godot docs: RenderingServer.ENV_BG_CAMERA_FEED
             */
            val CAMERA_FEED: EnvironmentBG get() = EnvironmentBG(5L)
            /**
             * Represents the size of the `EnvironmentBG` enum.
             *
             * Generated from Godot docs: RenderingServer.ENV_BG_MAX
             */
            val MAX: EnvironmentBG get() = EnvironmentBG(6L)
        }
    }

    @JvmInline
    value class EnvironmentAmbientSource(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Gather ambient light from whichever source is specified as the background.
             *
             * Generated from Godot docs: RenderingServer.ENV_AMBIENT_SOURCE_BG
             */
            val BG: EnvironmentAmbientSource get() = EnvironmentAmbientSource(0L)
            /**
             * Disable ambient light.
             *
             * Generated from Godot docs: RenderingServer.ENV_AMBIENT_SOURCE_DISABLED
             */
            val DISABLED: EnvironmentAmbientSource get() = EnvironmentAmbientSource(1L)
            /**
             * Specify a specific `Color` for ambient light.
             *
             * Generated from Godot docs: RenderingServer.ENV_AMBIENT_SOURCE_COLOR
             */
            val COLOR: EnvironmentAmbientSource get() = EnvironmentAmbientSource(2L)
            /**
             * Gather ambient light from the `Sky` regardless of what the background is.
             *
             * Generated from Godot docs: RenderingServer.ENV_AMBIENT_SOURCE_SKY
             */
            val SKY: EnvironmentAmbientSource get() = EnvironmentAmbientSource(3L)
        }
    }

    @JvmInline
    value class EnvironmentReflectionSource(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use the background for reflections.
             *
             * Generated from Godot docs: RenderingServer.ENV_REFLECTION_SOURCE_BG
             */
            val BG: EnvironmentReflectionSource get() = EnvironmentReflectionSource(0L)
            /**
             * Disable reflections.
             *
             * Generated from Godot docs: RenderingServer.ENV_REFLECTION_SOURCE_DISABLED
             */
            val DISABLED: EnvironmentReflectionSource get() = EnvironmentReflectionSource(1L)
            /**
             * Use the `Sky` for reflections regardless of what the background is.
             *
             * Generated from Godot docs: RenderingServer.ENV_REFLECTION_SOURCE_SKY
             */
            val SKY: EnvironmentReflectionSource get() = EnvironmentReflectionSource(2L)
        }
    }

    @JvmInline
    value class EnvironmentGlowBlendMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Adds the glow effect to the scene.
             *
             * Generated from Godot docs: RenderingServer.ENV_GLOW_BLEND_MODE_ADDITIVE
             */
            val ADDITIVE: EnvironmentGlowBlendMode get() = EnvironmentGlowBlendMode(0L)
            /**
             * Adds the glow effect to the scene after modifying the glow influence based on the scene value;
             * dark values will be highly influenced by glow and bright values will not be influenced by glow.
             * This approach avoids bright values becoming overly bright from the glow effect.
             * `Environment.tonemap_white` is used to determine the maximum scene value where the glow should
             * have no influence. When `Environment.tonemap_mode` is set to `Environment.TONE_MAPPER_LINEAR`
             * and `Viewport.use_hdr_2d` is `true`, the parent window's `Window.get_output_max_linear_value`
             * will be used as the maximum scene value.
             *
             * Generated from Godot docs: RenderingServer.ENV_GLOW_BLEND_MODE_SCREEN
             */
            val SCREEN: EnvironmentGlowBlendMode get() = EnvironmentGlowBlendMode(1L)
            /**
             * Adds the glow effect to the tonemapped image after modifying the glow influence based on the
             * image value; dark values and bright values will not be influenced by glow and mid-range values
             * will be highly influenced by glow. This approach avoids bright values becoming overly bright
             * from the glow effect. The glow will have the largest influence on image values of `0.25` and
             * will have no influence when applied to image values greater than `1.0`. Note: This blend mode
             * does not support HDR output because expects a maximum output value of `1.0`. It is recommended
             * to use a different blend mode when rendering to an HDR screen.
             *
             * Generated from Godot docs: RenderingServer.ENV_GLOW_BLEND_MODE_SOFTLIGHT
             */
            val SOFTLIGHT: EnvironmentGlowBlendMode get() = EnvironmentGlowBlendMode(2L)
            /**
             * Replaces all pixels' color by the glow effect. This can be used to simulate a full-screen blur
             * effect by tweaking the glow parameters to match the original image's brightness or to preview
             * glow configuration in the editor.
             *
             * Generated from Godot docs: RenderingServer.ENV_GLOW_BLEND_MODE_REPLACE
             */
            val REPLACE: EnvironmentGlowBlendMode get() = EnvironmentGlowBlendMode(3L)
            /**
             * Mixes the glow image with the scene image. Best used with `Environment.glow_bloom` to avoid
             * darkening the scene.
             *
             * Generated from Godot docs: RenderingServer.ENV_GLOW_BLEND_MODE_MIX
             */
            val MIX: EnvironmentGlowBlendMode get() = EnvironmentGlowBlendMode(4L)
        }
    }

    @JvmInline
    value class EnvironmentFogMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use a physically-based fog model defined primarily by fog density.
             *
             * Generated from Godot docs: RenderingServer.ENV_FOG_MODE_EXPONENTIAL
             */
            val EXPONENTIAL: EnvironmentFogMode get() = EnvironmentFogMode(0L)
            /**
             * Use a simple fog model defined by start and end positions and a custom curve. While not
             * physically accurate, this model can be useful when you need more artistic control.
             *
             * Generated from Godot docs: RenderingServer.ENV_FOG_MODE_DEPTH
             */
            val DEPTH: EnvironmentFogMode get() = EnvironmentFogMode(1L)
        }
    }

    @JvmInline
    value class EnvironmentToneMapper(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Does not modify color data, resulting in a linear tonemapping curve which unnaturally clips
             * bright values, causing bright lighting to look blown out. The simplest and fastest tonemapper.
             *
             * Generated from Godot docs: RenderingServer.ENV_TONE_MAPPER_LINEAR
             */
            val LINEAR: EnvironmentToneMapper get() = EnvironmentToneMapper(0L)
            /**
             * A simple tonemapping curve that rolls off bright values to prevent clipping. This results in an
             * image that can appear dull and low contrast. Slower than `ENV_TONE_MAPPER_LINEAR`. Note: When
             * `Environment.tonemap_white` is left at the default value of `1.0`, `ENV_TONE_MAPPER_REINHARD`
             * produces an identical image to `ENV_TONE_MAPPER_LINEAR`.
             *
             * Generated from Godot docs: RenderingServer.ENV_TONE_MAPPER_REINHARD
             */
            val REINHARD: EnvironmentToneMapper get() = EnvironmentToneMapper(1L)
            /**
             * Uses a film-like tonemapping curve to prevent clipping of bright values and provide better
             * contrast than `ENV_TONE_MAPPER_REINHARD`. Slightly slower than `ENV_TONE_MAPPER_REINHARD`. Note:
             * This tonemapper does not support HDR output because it produces output in the SDR range. It is
             * recommended to use a different tonemapper when rendering to an HDR screen.
             *
             * Generated from Godot docs: RenderingServer.ENV_TONE_MAPPER_FILMIC
             */
            val FILMIC: EnvironmentToneMapper get() = EnvironmentToneMapper(2L)
            /**
             * Uses a high-contrast film-like tonemapping curve and desaturates bright values for a more
             * realistic appearance. Slightly slower than `ENV_TONE_MAPPER_FILMIC`. Note: This tonemapping
             * operator is called "ACES Fitted" in Godot 3.x. Note: This tonemapper does not support HDR output
             * because it produces output in the SDR range. It is recommended to use a different tonemapper
             * when rendering to an HDR screen.
             *
             * Generated from Godot docs: RenderingServer.ENV_TONE_MAPPER_ACES
             */
            val ACES: EnvironmentToneMapper get() = EnvironmentToneMapper(3L)
            /**
             * Uses an adjustable film-like tonemapping curve and desaturates bright values for a more
             * realistic appearance. Better than other tonemappers at maintaining the hue of colors as they
             * become brighter. The slowest tonemapping option.
             *
             * Generated from Godot docs: RenderingServer.ENV_TONE_MAPPER_AGX
             */
            val AGX: EnvironmentToneMapper get() = EnvironmentToneMapper(4L)
        }
    }

    @JvmInline
    value class EnvironmentSSRRoughnessQuality(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Lowest quality of roughness filter for screen-space reflections. Rough materials will not have
             * blurrier screen-space reflections compared to smooth (non-rough) materials. This is the fastest
             * option.
             *
             * Generated from Godot docs: RenderingServer.ENV_SSR_ROUGHNESS_QUALITY_DISABLED
             */
            val DISABLED: EnvironmentSSRRoughnessQuality get() = EnvironmentSSRRoughnessQuality(0L)
            /**
             * Low quality of roughness filter for screen-space reflections.
             *
             * Generated from Godot docs: RenderingServer.ENV_SSR_ROUGHNESS_QUALITY_LOW
             */
            val LOW: EnvironmentSSRRoughnessQuality get() = EnvironmentSSRRoughnessQuality(1L)
            /**
             * Medium quality of roughness filter for screen-space reflections.
             *
             * Generated from Godot docs: RenderingServer.ENV_SSR_ROUGHNESS_QUALITY_MEDIUM
             */
            val MEDIUM: EnvironmentSSRRoughnessQuality get() = EnvironmentSSRRoughnessQuality(2L)
            /**
             * High quality of roughness filter for screen-space reflections. This is the slowest option.
             *
             * Generated from Godot docs: RenderingServer.ENV_SSR_ROUGHNESS_QUALITY_HIGH
             */
            val HIGH: EnvironmentSSRRoughnessQuality get() = EnvironmentSSRRoughnessQuality(3L)
        }
    }

    @JvmInline
    value class EnvironmentSSAOQuality(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Lowest quality of screen-space ambient occlusion.
             *
             * Generated from Godot docs: RenderingServer.ENV_SSAO_QUALITY_VERY_LOW
             */
            val VERY_LOW: EnvironmentSSAOQuality get() = EnvironmentSSAOQuality(0L)
            /**
             * Low quality screen-space ambient occlusion.
             *
             * Generated from Godot docs: RenderingServer.ENV_SSAO_QUALITY_LOW
             */
            val LOW: EnvironmentSSAOQuality get() = EnvironmentSSAOQuality(1L)
            /**
             * Medium quality screen-space ambient occlusion.
             *
             * Generated from Godot docs: RenderingServer.ENV_SSAO_QUALITY_MEDIUM
             */
            val MEDIUM: EnvironmentSSAOQuality get() = EnvironmentSSAOQuality(2L)
            /**
             * High quality screen-space ambient occlusion.
             *
             * Generated from Godot docs: RenderingServer.ENV_SSAO_QUALITY_HIGH
             */
            val HIGH: EnvironmentSSAOQuality get() = EnvironmentSSAOQuality(3L)
            /**
             * Highest quality screen-space ambient occlusion. Uses the adaptive target setting which can be
             * dynamically adjusted to smoothly balance performance and visual quality.
             *
             * Generated from Godot docs: RenderingServer.ENV_SSAO_QUALITY_ULTRA
             */
            val ULTRA: EnvironmentSSAOQuality get() = EnvironmentSSAOQuality(4L)
        }
    }

    @JvmInline
    value class EnvironmentSSILQuality(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Lowest quality of screen-space indirect lighting.
             *
             * Generated from Godot docs: RenderingServer.ENV_SSIL_QUALITY_VERY_LOW
             */
            val VERY_LOW: EnvironmentSSILQuality get() = EnvironmentSSILQuality(0L)
            /**
             * Low quality screen-space indirect lighting.
             *
             * Generated from Godot docs: RenderingServer.ENV_SSIL_QUALITY_LOW
             */
            val LOW: EnvironmentSSILQuality get() = EnvironmentSSILQuality(1L)
            /**
             * High quality screen-space indirect lighting.
             *
             * Generated from Godot docs: RenderingServer.ENV_SSIL_QUALITY_MEDIUM
             */
            val MEDIUM: EnvironmentSSILQuality get() = EnvironmentSSILQuality(2L)
            /**
             * High quality screen-space indirect lighting.
             *
             * Generated from Godot docs: RenderingServer.ENV_SSIL_QUALITY_HIGH
             */
            val HIGH: EnvironmentSSILQuality get() = EnvironmentSSILQuality(3L)
            /**
             * Highest quality screen-space indirect lighting. Uses the adaptive target setting which can be
             * dynamically adjusted to smoothly balance performance and visual quality.
             *
             * Generated from Godot docs: RenderingServer.ENV_SSIL_QUALITY_ULTRA
             */
            val ULTRA: EnvironmentSSILQuality get() = EnvironmentSSILQuality(4L)
        }
    }

    @JvmInline
    value class EnvironmentSDFGIYScale(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use 50% scale for SDFGI on the Y (vertical) axis. SDFGI cells will be twice as short as they are
             * wide. This allows providing increased GI detail and reduced light leaking with thin floors and
             * ceilings. This is usually the best choice for scenes that don't feature much verticality.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_Y_SCALE_50_PERCENT
             */
            val SCALE_50_PERCENT: EnvironmentSDFGIYScale get() = EnvironmentSDFGIYScale(0L)
            /**
             * Use 75% scale for SDFGI on the Y (vertical) axis. This is a balance between the 50% and 100%
             * SDFGI Y scales.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_Y_SCALE_75_PERCENT
             */
            val SCALE_75_PERCENT: EnvironmentSDFGIYScale get() = EnvironmentSDFGIYScale(1L)
            /**
             * Use 100% scale for SDFGI on the Y (vertical) axis. SDFGI cells will be as tall as they are wide.
             * This is usually the best choice for highly vertical scenes. The downside is that light leaking
             * may become more noticeable with thin floors and ceilings.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_Y_SCALE_100_PERCENT
             */
            val SCALE_100_PERCENT: EnvironmentSDFGIYScale get() = EnvironmentSDFGIYScale(2L)
        }
    }

    @JvmInline
    value class EnvironmentSDFGIRayCount(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Throw 4 rays per frame when converging SDFGI. This has the lowest GPU requirements, but creates
             * the most noisy result.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_RAY_COUNT_4
             */
            val COUNT_4: EnvironmentSDFGIRayCount get() = EnvironmentSDFGIRayCount(0L)
            /**
             * Throw 8 rays per frame when converging SDFGI.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_RAY_COUNT_8
             */
            val COUNT_8: EnvironmentSDFGIRayCount get() = EnvironmentSDFGIRayCount(1L)
            /**
             * Throw 16 rays per frame when converging SDFGI.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_RAY_COUNT_16
             */
            val COUNT_16: EnvironmentSDFGIRayCount get() = EnvironmentSDFGIRayCount(2L)
            /**
             * Throw 32 rays per frame when converging SDFGI.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_RAY_COUNT_32
             */
            val COUNT_32: EnvironmentSDFGIRayCount get() = EnvironmentSDFGIRayCount(3L)
            /**
             * Throw 64 rays per frame when converging SDFGI.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_RAY_COUNT_64
             */
            val COUNT_64: EnvironmentSDFGIRayCount get() = EnvironmentSDFGIRayCount(4L)
            /**
             * Throw 96 rays per frame when converging SDFGI. This has high GPU requirements.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_RAY_COUNT_96
             */
            val COUNT_96: EnvironmentSDFGIRayCount get() = EnvironmentSDFGIRayCount(5L)
            /**
             * Throw 128 rays per frame when converging SDFGI. This has very high GPU requirements, but creates
             * the least noisy result.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_RAY_COUNT_128
             */
            val COUNT_128: EnvironmentSDFGIRayCount get() = EnvironmentSDFGIRayCount(6L)
            /**
             * Represents the size of the `EnvironmentSDFGIRayCount` enum.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_RAY_COUNT_MAX
             */
            val MAX: EnvironmentSDFGIRayCount get() = EnvironmentSDFGIRayCount(7L)
        }
    }

    @JvmInline
    value class EnvironmentSDFGIFramesToConverge(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Converge SDFGI over 5 frames. This is the most responsive, but creates the most noisy result
             * with a given ray count.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_CONVERGE_IN_5_FRAMES
             */
            val IN_5_FRAMES: EnvironmentSDFGIFramesToConverge get() = EnvironmentSDFGIFramesToConverge(0L)
            /**
             * Configure SDFGI to fully converge over 10 frames.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_CONVERGE_IN_10_FRAMES
             */
            val IN_10_FRAMES: EnvironmentSDFGIFramesToConverge get() = EnvironmentSDFGIFramesToConverge(1L)
            /**
             * Configure SDFGI to fully converge over 15 frames.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_CONVERGE_IN_15_FRAMES
             */
            val IN_15_FRAMES: EnvironmentSDFGIFramesToConverge get() = EnvironmentSDFGIFramesToConverge(2L)
            /**
             * Configure SDFGI to fully converge over 20 frames.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_CONVERGE_IN_20_FRAMES
             */
            val IN_20_FRAMES: EnvironmentSDFGIFramesToConverge get() = EnvironmentSDFGIFramesToConverge(3L)
            /**
             * Configure SDFGI to fully converge over 25 frames.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_CONVERGE_IN_25_FRAMES
             */
            val IN_25_FRAMES: EnvironmentSDFGIFramesToConverge get() = EnvironmentSDFGIFramesToConverge(4L)
            /**
             * Configure SDFGI to fully converge over 30 frames. This is the least responsive, but creates the
             * least noisy result with a given ray count.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_CONVERGE_IN_30_FRAMES
             */
            val IN_30_FRAMES: EnvironmentSDFGIFramesToConverge get() = EnvironmentSDFGIFramesToConverge(5L)
            /**
             * Represents the size of the `EnvironmentSDFGIFramesToConverge` enum.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_CONVERGE_MAX
             */
            val MAX: EnvironmentSDFGIFramesToConverge get() = EnvironmentSDFGIFramesToConverge(6L)
        }
    }

    @JvmInline
    value class EnvironmentSDFGIFramesToUpdateLight(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Update indirect light from dynamic lights in SDFGI over 1 frame. This is the most responsive,
             * but has the highest GPU requirements.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_UPDATE_LIGHT_IN_1_FRAME
             */
            val IN_1_FRAME: EnvironmentSDFGIFramesToUpdateLight get() = EnvironmentSDFGIFramesToUpdateLight(0L)
            /**
             * Update indirect light from dynamic lights in SDFGI over 2 frames.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_UPDATE_LIGHT_IN_2_FRAMES
             */
            val IN_2_FRAMES: EnvironmentSDFGIFramesToUpdateLight get() = EnvironmentSDFGIFramesToUpdateLight(1L)
            /**
             * Update indirect light from dynamic lights in SDFGI over 4 frames.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_UPDATE_LIGHT_IN_4_FRAMES
             */
            val IN_4_FRAMES: EnvironmentSDFGIFramesToUpdateLight get() = EnvironmentSDFGIFramesToUpdateLight(2L)
            /**
             * Update indirect light from dynamic lights in SDFGI over 8 frames.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_UPDATE_LIGHT_IN_8_FRAMES
             */
            val IN_8_FRAMES: EnvironmentSDFGIFramesToUpdateLight get() = EnvironmentSDFGIFramesToUpdateLight(3L)
            /**
             * Update indirect light from dynamic lights in SDFGI over 16 frames. This is the least responsive,
             * but has the lowest GPU requirements.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_UPDATE_LIGHT_IN_16_FRAMES
             */
            val IN_16_FRAMES: EnvironmentSDFGIFramesToUpdateLight get() = EnvironmentSDFGIFramesToUpdateLight(4L)
            /**
             * Represents the size of the `EnvironmentSDFGIFramesToUpdateLight` enum.
             *
             * Generated from Godot docs: RenderingServer.ENV_SDFGI_UPDATE_LIGHT_MAX
             */
            val MAX: EnvironmentSDFGIFramesToUpdateLight get() = EnvironmentSDFGIFramesToUpdateLight(5L)
        }
    }

    @JvmInline
    value class SubSurfaceScatteringQuality(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Disables subsurface scattering entirely, even on materials that have
             * `BaseMaterial3D.subsurf_scatter_enabled` set to `true`. This has the lowest GPU requirements.
             *
             * Generated from Godot docs: RenderingServer.SUB_SURFACE_SCATTERING_QUALITY_DISABLED
             */
            val DISABLED: SubSurfaceScatteringQuality get() = SubSurfaceScatteringQuality(0L)
            /**
             * Low subsurface scattering quality.
             *
             * Generated from Godot docs: RenderingServer.SUB_SURFACE_SCATTERING_QUALITY_LOW
             */
            val LOW: SubSurfaceScatteringQuality get() = SubSurfaceScatteringQuality(1L)
            /**
             * Medium subsurface scattering quality.
             *
             * Generated from Godot docs: RenderingServer.SUB_SURFACE_SCATTERING_QUALITY_MEDIUM
             */
            val MEDIUM: SubSurfaceScatteringQuality get() = SubSurfaceScatteringQuality(2L)
            /**
             * High subsurface scattering quality. This has the highest GPU requirements.
             *
             * Generated from Godot docs: RenderingServer.SUB_SURFACE_SCATTERING_QUALITY_HIGH
             */
            val HIGH: SubSurfaceScatteringQuality get() = SubSurfaceScatteringQuality(3L)
        }
    }

    @JvmInline
    value class DOFBokehShape(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Calculate the DOF blur using a box filter. The fastest option, but results in obvious lines in
             * blur pattern.
             *
             * Generated from Godot docs: RenderingServer.DOF_BOKEH_BOX
             */
            val BOX: DOFBokehShape get() = DOFBokehShape(0L)
            /**
             * Calculates DOF blur using a hexagon shaped filter.
             *
             * Generated from Godot docs: RenderingServer.DOF_BOKEH_HEXAGON
             */
            val HEXAGON: DOFBokehShape get() = DOFBokehShape(1L)
            /**
             * Calculates DOF blur using a circle shaped filter. Best quality and most realistic, but slowest.
             * Use only for areas where a lot of performance can be dedicated to post-processing (e.g.
             * cutscenes).
             *
             * Generated from Godot docs: RenderingServer.DOF_BOKEH_CIRCLE
             */
            val CIRCLE: DOFBokehShape get() = DOFBokehShape(2L)
        }
    }

    @JvmInline
    value class DOFBlurQuality(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Lowest quality DOF blur. This is the fastest setting, but you may be able to see filtering
             * artifacts.
             *
             * Generated from Godot docs: RenderingServer.DOF_BLUR_QUALITY_VERY_LOW
             */
            val VERY_LOW: DOFBlurQuality get() = DOFBlurQuality(0L)
            /**
             * Low quality DOF blur.
             *
             * Generated from Godot docs: RenderingServer.DOF_BLUR_QUALITY_LOW
             */
            val LOW: DOFBlurQuality get() = DOFBlurQuality(1L)
            /**
             * Medium quality DOF blur.
             *
             * Generated from Godot docs: RenderingServer.DOF_BLUR_QUALITY_MEDIUM
             */
            val MEDIUM: DOFBlurQuality get() = DOFBlurQuality(2L)
            /**
             * Highest quality DOF blur. Results in the smoothest looking blur by taking the most samples, but
             * is also significantly slower.
             *
             * Generated from Godot docs: RenderingServer.DOF_BLUR_QUALITY_HIGH
             */
            val HIGH: DOFBlurQuality get() = DOFBlurQuality(3L)
        }
    }

    @JvmInline
    value class InstanceType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The instance does not have a type.
             *
             * Generated from Godot docs: RenderingServer.INSTANCE_NONE
             */
            val NONE: InstanceType get() = InstanceType(0L)
            /**
             * The instance is a mesh.
             *
             * Generated from Godot docs: RenderingServer.INSTANCE_MESH
             */
            val MESH: InstanceType get() = InstanceType(1L)
            /**
             * The instance is a multimesh.
             *
             * Generated from Godot docs: RenderingServer.INSTANCE_MULTIMESH
             */
            val MULTIMESH: InstanceType get() = InstanceType(2L)
            /**
             * The instance is a particle emitter.
             *
             * Generated from Godot docs: RenderingServer.INSTANCE_PARTICLES
             */
            val PARTICLES: InstanceType get() = InstanceType(3L)
            /**
             * The instance is a GPUParticles collision shape.
             *
             * Generated from Godot docs: RenderingServer.INSTANCE_PARTICLES_COLLISION
             */
            val PARTICLES_COLLISION: InstanceType get() = InstanceType(4L)
            /**
             * The instance is a light.
             *
             * Generated from Godot docs: RenderingServer.INSTANCE_LIGHT
             */
            val LIGHT: InstanceType get() = InstanceType(5L)
            /**
             * The instance is a reflection probe.
             *
             * Generated from Godot docs: RenderingServer.INSTANCE_REFLECTION_PROBE
             */
            val REFLECTION_PROBE: InstanceType get() = InstanceType(6L)
            /**
             * The instance is a decal.
             *
             * Generated from Godot docs: RenderingServer.INSTANCE_DECAL
             */
            val DECAL: InstanceType get() = InstanceType(7L)
            /**
             * The instance is a VoxelGI.
             *
             * Generated from Godot docs: RenderingServer.INSTANCE_VOXEL_GI
             */
            val VOXEL_GI: InstanceType get() = InstanceType(8L)
            /**
             * The instance is a lightmap.
             *
             * Generated from Godot docs: RenderingServer.INSTANCE_LIGHTMAP
             */
            val LIGHTMAP: InstanceType get() = InstanceType(9L)
            /**
             * The instance is an occlusion culling occluder.
             *
             * Generated from Godot docs: RenderingServer.INSTANCE_OCCLUDER
             */
            val OCCLUDER: InstanceType get() = InstanceType(10L)
            /**
             * The instance is a visible on-screen notifier.
             *
             * Generated from Godot docs: RenderingServer.INSTANCE_VISIBLITY_NOTIFIER
             */
            val VISIBLITY_NOTIFIER: InstanceType get() = InstanceType(11L)
            /**
             * The instance is a fog volume.
             *
             * Generated from Godot docs: RenderingServer.INSTANCE_FOG_VOLUME
             */
            val FOG_VOLUME: InstanceType get() = InstanceType(12L)
            /**
             * Represents the size of the `InstanceType` enum.
             *
             * Generated from Godot docs: RenderingServer.INSTANCE_MAX
             */
            val MAX: InstanceType get() = InstanceType(13L)
            /**
             * A combination of the flags of geometry instances (mesh, multimesh, immediate and particles).
             *
             * Generated from Godot docs: RenderingServer.INSTANCE_GEOMETRY_MASK
             */
            val GEOMETRY_MASK: InstanceType get() = InstanceType(14L)
        }
    }

    @JvmInline
    value class InstanceFlags(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Allows the instance to be used in baked lighting.
             *
             * Generated from Godot docs: RenderingServer.INSTANCE_FLAG_USE_BAKED_LIGHT
             */
            val USE_BAKED_LIGHT: InstanceFlags get() = InstanceFlags(0L)
            /**
             * Allows the instance to be used with dynamic global illumination.
             *
             * Generated from Godot docs: RenderingServer.INSTANCE_FLAG_USE_DYNAMIC_GI
             */
            val USE_DYNAMIC_GI: InstanceFlags get() = InstanceFlags(1L)
            /**
             * When set, manually requests to draw geometry on next frame.
             *
             * Generated from Godot docs: RenderingServer.INSTANCE_FLAG_DRAW_NEXT_FRAME_IF_VISIBLE
             */
            val DRAW_NEXT_FRAME_IF_VISIBLE: InstanceFlags get() = InstanceFlags(2L)
            /**
             * Always draw, even if the instance would be culled by occlusion culling. Does not affect view
             * frustum culling.
             *
             * Generated from Godot docs: RenderingServer.INSTANCE_FLAG_IGNORE_OCCLUSION_CULLING
             */
            val IGNORE_OCCLUSION_CULLING: InstanceFlags get() = InstanceFlags(3L)
            /**
             * Represents the size of the `InstanceFlags` enum.
             *
             * Generated from Godot docs: RenderingServer.INSTANCE_FLAG_MAX
             */
            val MAX: InstanceFlags get() = InstanceFlags(4L)
        }
    }

    @JvmInline
    value class ShadowCastingSetting(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Disable shadows from this instance.
             *
             * Generated from Godot docs: RenderingServer.SHADOW_CASTING_SETTING_OFF
             */
            val OFF: ShadowCastingSetting get() = ShadowCastingSetting(0L)
            /**
             * Cast shadows from this instance.
             *
             * Generated from Godot docs: RenderingServer.SHADOW_CASTING_SETTING_ON
             */
            val ON: ShadowCastingSetting get() = ShadowCastingSetting(1L)
            /**
             * Disable backface culling when rendering the shadow of the object. This is slightly slower but
             * may result in more correct shadows.
             *
             * Generated from Godot docs: RenderingServer.SHADOW_CASTING_SETTING_DOUBLE_SIDED
             */
            val DOUBLE_SIDED: ShadowCastingSetting get() = ShadowCastingSetting(2L)
            /**
             * Only render the shadows from the object. The object itself will not be drawn.
             *
             * Generated from Godot docs: RenderingServer.SHADOW_CASTING_SETTING_SHADOWS_ONLY
             */
            val SHADOWS_ONLY: ShadowCastingSetting get() = ShadowCastingSetting(3L)
        }
    }

    @JvmInline
    value class VisibilityRangeFadeMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Disable visibility range fading for the given instance.
             *
             * Generated from Godot docs: RenderingServer.VISIBILITY_RANGE_FADE_DISABLED
             */
            val DISABLED: VisibilityRangeFadeMode get() = VisibilityRangeFadeMode(0L)
            /**
             * Fade-out the given instance when it approaches its visibility range limits.
             *
             * Generated from Godot docs: RenderingServer.VISIBILITY_RANGE_FADE_SELF
             */
            val SELF: VisibilityRangeFadeMode get() = VisibilityRangeFadeMode(1L)
            /**
             * Fade-in the given instance's dependencies when reaching its visibility range limits.
             *
             * Generated from Godot docs: RenderingServer.VISIBILITY_RANGE_FADE_DEPENDENCIES
             */
            val DEPENDENCIES: VisibilityRangeFadeMode get() = VisibilityRangeFadeMode(2L)
        }
    }

    @JvmInline
    value class BakeChannels(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Index of `Image` in array of `Image`s returned by `bake_render_uv2`. Image uses
             * `Image.FORMAT_RGBA8` and contains albedo color in the `.rgb` channels and alpha in the `.a`
             * channel.
             *
             * Generated from Godot docs: RenderingServer.BAKE_CHANNEL_ALBEDO_ALPHA
             */
            val ALBEDO_ALPHA: BakeChannels get() = BakeChannels(0L)
            /**
             * Index of `Image` in array of `Image`s returned by `bake_render_uv2`. Image uses
             * `Image.FORMAT_RGBA8` and contains the per-pixel normal of the object in the `.rgb` channels and
             * nothing in the `.a` channel. The per-pixel normal is encoded as `normal * 0.5 + 0.5`.
             *
             * Generated from Godot docs: RenderingServer.BAKE_CHANNEL_NORMAL
             */
            val NORMAL: BakeChannels get() = BakeChannels(1L)
            /**
             * Index of `Image` in array of `Image`s returned by `bake_render_uv2`. Image uses
             * `Image.FORMAT_RGBA8` and contains ambient occlusion (from material and decals only) in the `.r`
             * channel, roughness in the `.g` channel, metallic in the `.b` channel and sub surface scattering
             * amount in the `.a` channel.
             *
             * Generated from Godot docs: RenderingServer.BAKE_CHANNEL_ORM
             */
            val ORM: BakeChannels get() = BakeChannels(2L)
            /**
             * Index of `Image` in array of `Image`s returned by `bake_render_uv2`. Image uses
             * `Image.FORMAT_RGBAH` and contains emission color in the `.rgb` channels and nothing in the `.a`
             * channel.
             *
             * Generated from Godot docs: RenderingServer.BAKE_CHANNEL_EMISSION
             */
            val EMISSION: BakeChannels get() = BakeChannels(3L)
        }
    }

    @JvmInline
    value class CanvasTextureChannel(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Diffuse canvas texture (`CanvasTexture.diffuse_texture`).
             *
             * Generated from Godot docs: RenderingServer.CANVAS_TEXTURE_CHANNEL_DIFFUSE
             */
            val DIFFUSE: CanvasTextureChannel get() = CanvasTextureChannel(0L)
            /**
             * Normal map canvas texture (`CanvasTexture.normal_texture`).
             *
             * Generated from Godot docs: RenderingServer.CANVAS_TEXTURE_CHANNEL_NORMAL
             */
            val NORMAL: CanvasTextureChannel get() = CanvasTextureChannel(1L)
            /**
             * Specular map canvas texture (`CanvasTexture.specular_texture`).
             *
             * Generated from Godot docs: RenderingServer.CANVAS_TEXTURE_CHANNEL_SPECULAR
             */
            val SPECULAR: CanvasTextureChannel get() = CanvasTextureChannel(2L)
        }
    }

    @JvmInline
    value class NinePatchAxisMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The nine patch gets stretched where needed.
             *
             * Generated from Godot docs: RenderingServer.NINE_PATCH_STRETCH
             */
            val STRETCH: NinePatchAxisMode get() = NinePatchAxisMode(0L)
            /**
             * The nine patch gets filled with tiles where needed.
             *
             * Generated from Godot docs: RenderingServer.NINE_PATCH_TILE
             */
            val TILE: NinePatchAxisMode get() = NinePatchAxisMode(1L)
            /**
             * The nine patch gets filled with tiles where needed and stretches them a bit if needed.
             *
             * Generated from Godot docs: RenderingServer.NINE_PATCH_TILE_FIT
             */
            val TILE_FIT: NinePatchAxisMode get() = NinePatchAxisMode(2L)
        }
    }

    @JvmInline
    value class CanvasItemTextureFilter(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Uses the default filter mode for this `Viewport`.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_ITEM_TEXTURE_FILTER_DEFAULT
             */
            val DEFAULT: CanvasItemTextureFilter get() = CanvasItemTextureFilter(0L)
            /**
             * The texture filter reads from the nearest pixel only. This makes the texture look pixelated from
             * up close, and grainy from a distance (due to mipmaps not being sampled).
             *
             * Generated from Godot docs: RenderingServer.CANVAS_ITEM_TEXTURE_FILTER_NEAREST
             */
            val NEAREST: CanvasItemTextureFilter get() = CanvasItemTextureFilter(1L)
            /**
             * The texture filter blends between the nearest 4 pixels. This makes the texture look smooth from
             * up close, and grainy from a distance (due to mipmaps not being sampled).
             *
             * Generated from Godot docs: RenderingServer.CANVAS_ITEM_TEXTURE_FILTER_LINEAR
             */
            val LINEAR: CanvasItemTextureFilter get() = CanvasItemTextureFilter(2L)
            /**
             * The texture filter reads from the nearest pixel and blends between the nearest 2 mipmaps (or
             * uses the nearest mipmap if
             * `ProjectSettings.rendering/textures/default_filters/use_nearest_mipmap_filter` is `true`). This
             * makes the texture look pixelated from up close, and smooth from a distance. Use this for
             * non-pixel art textures that may be viewed at a low scale (e.g. due to `Camera2D` zoom or sprite
             * scaling), as mipmaps are important to smooth out pixels that are smaller than on-screen pixels.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_ITEM_TEXTURE_FILTER_NEAREST_WITH_MIPMAPS
             */
            val NEAREST_WITH_MIPMAPS: CanvasItemTextureFilter get() = CanvasItemTextureFilter(3L)
            /**
             * The texture filter blends between the nearest 4 pixels and between the nearest 2 mipmaps (or
             * uses the nearest mipmap if
             * `ProjectSettings.rendering/textures/default_filters/use_nearest_mipmap_filter` is `true`). This
             * makes the texture look smooth from up close, and smooth from a distance. Use this for non-pixel
             * art textures that may be viewed at a low scale (e.g. due to `Camera2D` zoom or sprite scaling),
             * as mipmaps are important to smooth out pixels that are smaller than on-screen pixels.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_ITEM_TEXTURE_FILTER_LINEAR_WITH_MIPMAPS
             */
            val LINEAR_WITH_MIPMAPS: CanvasItemTextureFilter get() = CanvasItemTextureFilter(4L)
            /**
             * The texture filter reads from the nearest pixel and blends between 2 mipmaps (or uses the
             * nearest mipmap if `ProjectSettings.rendering/textures/default_filters/use_nearest_mipmap_filter`
             * is `true`) based on the angle between the surface and the camera view. This makes the texture
             * look pixelated from up close, and smooth from a distance. Anisotropic filtering improves texture
             * quality on surfaces that are almost in line with the camera, but is slightly slower. The
             * anisotropic filtering level can be changed by adjusting
             * `ProjectSettings.rendering/textures/default_filters/anisotropic_filtering_level`. Note: This
             * texture filter is rarely useful in 2D projects.
             * `CANVAS_ITEM_TEXTURE_FILTER_NEAREST_WITH_MIPMAPS` is usually more appropriate in this case.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_ITEM_TEXTURE_FILTER_NEAREST_WITH_MIPMAPS_ANISOTROPIC
             */
            val NEAREST_WITH_MIPMAPS_ANISOTROPIC: CanvasItemTextureFilter get() = CanvasItemTextureFilter(5L)
            /**
             * The texture filter blends between the nearest 4 pixels and blends between 2 mipmaps (or uses the
             * nearest mipmap if `ProjectSettings.rendering/textures/default_filters/use_nearest_mipmap_filter`
             * is `true`) based on the angle between the surface and the camera view. This makes the texture
             * look smooth from up close, and smooth from a distance. Anisotropic filtering improves texture
             * quality on surfaces that are almost in line with the camera, but is slightly slower. The
             * anisotropic filtering level can be changed by adjusting
             * `ProjectSettings.rendering/textures/default_filters/anisotropic_filtering_level`. Note: This
             * texture filter is rarely useful in 2D projects. `CANVAS_ITEM_TEXTURE_FILTER_LINEAR_WITH_MIPMAPS`
             * is usually more appropriate in this case.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_ITEM_TEXTURE_FILTER_LINEAR_WITH_MIPMAPS_ANISOTROPIC
             */
            val LINEAR_WITH_MIPMAPS_ANISOTROPIC: CanvasItemTextureFilter get() = CanvasItemTextureFilter(6L)
            /**
             * Max value for `CanvasItemTextureFilter` enum.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_ITEM_TEXTURE_FILTER_MAX
             */
            val MAX: CanvasItemTextureFilter get() = CanvasItemTextureFilter(7L)
        }
    }

    @JvmInline
    value class CanvasItemTextureRepeat(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Uses the default repeat mode for this `Viewport`.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_ITEM_TEXTURE_REPEAT_DEFAULT
             */
            val DEFAULT: CanvasItemTextureRepeat get() = CanvasItemTextureRepeat(0L)
            /**
             * Disables textures repeating. Instead, when reading UVs outside the 0-1 range, the value will be
             * clamped to the edge of the texture, resulting in a stretched out look at the borders of the
             * texture.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_ITEM_TEXTURE_REPEAT_DISABLED
             */
            val DISABLED: CanvasItemTextureRepeat get() = CanvasItemTextureRepeat(1L)
            /**
             * Enables the texture to repeat when UV coordinates are outside the 0-1 range. If using one of the
             * linear filtering modes, this can result in artifacts at the edges of a texture when the sampler
             * filters across the edges of the texture.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_ITEM_TEXTURE_REPEAT_ENABLED
             */
            val ENABLED: CanvasItemTextureRepeat get() = CanvasItemTextureRepeat(2L)
            /**
             * Flip the texture when repeating so that the edge lines up instead of abruptly changing.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_ITEM_TEXTURE_REPEAT_MIRROR
             */
            val MIRROR: CanvasItemTextureRepeat get() = CanvasItemTextureRepeat(3L)
            /**
             * Max value for `CanvasItemTextureRepeat` enum.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_ITEM_TEXTURE_REPEAT_MAX
             */
            val MAX: CanvasItemTextureRepeat get() = CanvasItemTextureRepeat(4L)
        }
    }

    @JvmInline
    value class CanvasGroupMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Child draws over parent and is not clipped.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_GROUP_MODE_DISABLED
             */
            val DISABLED: CanvasGroupMode get() = CanvasGroupMode(0L)
            /**
             * Parent is used for the purposes of clipping only. Child is clipped to the parent's visible area,
             * parent is not drawn.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_GROUP_MODE_CLIP_ONLY
             */
            val CLIP_ONLY: CanvasGroupMode get() = CanvasGroupMode(1L)
            /**
             * Parent is used for clipping child, but parent is also drawn underneath child as normal before
             * clipping child to its visible area.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_GROUP_MODE_CLIP_AND_DRAW
             */
            val CLIP_AND_DRAW: CanvasGroupMode get() = CanvasGroupMode(2L)
            val TRANSPARENT: CanvasGroupMode get() = CanvasGroupMode(3L)
        }
    }

    @JvmInline
    value class CanvasLightMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * 2D point light (see `PointLight2D`).
             *
             * Generated from Godot docs: RenderingServer.CANVAS_LIGHT_MODE_POINT
             */
            val POINT: CanvasLightMode get() = CanvasLightMode(0L)
            /**
             * 2D directional (sun/moon) light (see `DirectionalLight2D`).
             *
             * Generated from Godot docs: RenderingServer.CANVAS_LIGHT_MODE_DIRECTIONAL
             */
            val DIRECTIONAL: CanvasLightMode get() = CanvasLightMode(1L)
        }
    }

    @JvmInline
    value class CanvasLightBlendMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Adds light color additive to the canvas.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_LIGHT_BLEND_MODE_ADD
             */
            val ADD: CanvasLightBlendMode get() = CanvasLightBlendMode(0L)
            /**
             * Adds light color subtractive to the canvas.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_LIGHT_BLEND_MODE_SUB
             */
            val SUB: CanvasLightBlendMode get() = CanvasLightBlendMode(1L)
            /**
             * The light adds color depending on transparency.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_LIGHT_BLEND_MODE_MIX
             */
            val MIX: CanvasLightBlendMode get() = CanvasLightBlendMode(2L)
        }
    }

    @JvmInline
    value class CanvasLightShadowFilter(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Do not apply a filter to canvas light shadows.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_LIGHT_FILTER_NONE
             */
            val NONE: CanvasLightShadowFilter get() = CanvasLightShadowFilter(0L)
            /**
             * Use PCF5 filtering to filter canvas light shadows.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_LIGHT_FILTER_PCF5
             */
            val PCF5: CanvasLightShadowFilter get() = CanvasLightShadowFilter(1L)
            /**
             * Use PCF13 filtering to filter canvas light shadows.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_LIGHT_FILTER_PCF13
             */
            val PCF13: CanvasLightShadowFilter get() = CanvasLightShadowFilter(2L)
            /**
             * Max value of the `CanvasLightShadowFilter` enum.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_LIGHT_FILTER_MAX
             */
            val MAX: CanvasLightShadowFilter get() = CanvasLightShadowFilter(3L)
        }
    }

    @JvmInline
    value class CanvasOccluderPolygonCullMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Culling of the canvas occluder is disabled.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_OCCLUDER_POLYGON_CULL_DISABLED
             */
            val DISABLED: CanvasOccluderPolygonCullMode get() = CanvasOccluderPolygonCullMode(0L)
            /**
             * Culling of the canvas occluder is clockwise.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_OCCLUDER_POLYGON_CULL_CLOCKWISE
             */
            val CLOCKWISE: CanvasOccluderPolygonCullMode get() = CanvasOccluderPolygonCullMode(1L)
            /**
             * Culling of the canvas occluder is counterclockwise.
             *
             * Generated from Godot docs: RenderingServer.CANVAS_OCCLUDER_POLYGON_CULL_COUNTER_CLOCKWISE
             */
            val COUNTER_CLOCKWISE: CanvasOccluderPolygonCullMode get() = CanvasOccluderPolygonCullMode(2L)
        }
    }

    @JvmInline
    value class GlobalShaderParameterType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Boolean global shader parameter (`global uniform bool ...`).
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_BOOL
             */
            val BOOL: GlobalShaderParameterType get() = GlobalShaderParameterType(0L)
            /**
             * 2-dimensional boolean vector global shader parameter (`global uniform bvec2 ...`).
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_BVEC2
             */
            val BVEC2: GlobalShaderParameterType get() = GlobalShaderParameterType(1L)
            /**
             * 3-dimensional boolean vector global shader parameter (`global uniform bvec3 ...`).
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_BVEC3
             */
            val BVEC3: GlobalShaderParameterType get() = GlobalShaderParameterType(2L)
            /**
             * 4-dimensional boolean vector global shader parameter (`global uniform bvec4 ...`).
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_BVEC4
             */
            val BVEC4: GlobalShaderParameterType get() = GlobalShaderParameterType(3L)
            /**
             * Integer global shader parameter (`global uniform int ...`).
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_INT
             */
            val INT: GlobalShaderParameterType get() = GlobalShaderParameterType(4L)
            /**
             * 2-dimensional integer vector global shader parameter (`global uniform ivec2 ...`).
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_IVEC2
             */
            val IVEC2: GlobalShaderParameterType get() = GlobalShaderParameterType(5L)
            /**
             * 3-dimensional integer vector global shader parameter (`global uniform ivec3 ...`).
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_IVEC3
             */
            val IVEC3: GlobalShaderParameterType get() = GlobalShaderParameterType(6L)
            /**
             * 4-dimensional integer vector global shader parameter (`global uniform ivec4 ...`).
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_IVEC4
             */
            val IVEC4: GlobalShaderParameterType get() = GlobalShaderParameterType(7L)
            /**
             * 2-dimensional integer rectangle global shader parameter (`global uniform ivec4 ...`). Equivalent
             * to `GLOBAL_VAR_TYPE_IVEC4` in shader code, but exposed as a `Rect2i` in the editor UI.
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_RECT2I
             */
            val RECT2I: GlobalShaderParameterType get() = GlobalShaderParameterType(8L)
            /**
             * Unsigned integer global shader parameter (`global uniform uint ...`).
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_UINT
             */
            val UINT: GlobalShaderParameterType get() = GlobalShaderParameterType(9L)
            /**
             * 2-dimensional unsigned integer vector global shader parameter (`global uniform uvec2 ...`).
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_UVEC2
             */
            val UVEC2: GlobalShaderParameterType get() = GlobalShaderParameterType(10L)
            /**
             * 3-dimensional unsigned integer vector global shader parameter (`global uniform uvec3 ...`).
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_UVEC3
             */
            val UVEC3: GlobalShaderParameterType get() = GlobalShaderParameterType(11L)
            /**
             * 4-dimensional unsigned integer vector global shader parameter (`global uniform uvec4 ...`).
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_UVEC4
             */
            val UVEC4: GlobalShaderParameterType get() = GlobalShaderParameterType(12L)
            /**
             * Single-precision floating-point global shader parameter (`global uniform float ...`).
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_FLOAT
             */
            val FLOAT: GlobalShaderParameterType get() = GlobalShaderParameterType(13L)
            /**
             * 2-dimensional floating-point vector global shader parameter (`global uniform vec2 ...`).
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_VEC2
             */
            val VEC2: GlobalShaderParameterType get() = GlobalShaderParameterType(14L)
            /**
             * 3-dimensional floating-point vector global shader parameter (`global uniform vec3 ...`).
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_VEC3
             */
            val VEC3: GlobalShaderParameterType get() = GlobalShaderParameterType(15L)
            /**
             * 4-dimensional floating-point vector global shader parameter (`global uniform vec4 ...`).
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_VEC4
             */
            val VEC4: GlobalShaderParameterType get() = GlobalShaderParameterType(16L)
            /**
             * Color global shader parameter (`global uniform vec4 ...`). Equivalent to `GLOBAL_VAR_TYPE_VEC4`
             * in shader code, but exposed as a `Color` in the editor UI.
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_COLOR
             */
            val COLOR: GlobalShaderParameterType get() = GlobalShaderParameterType(17L)
            /**
             * 2-dimensional floating-point rectangle global shader parameter (`global uniform vec4 ...`).
             * Equivalent to `GLOBAL_VAR_TYPE_VEC4` in shader code, but exposed as a `Rect2` in the editor UI.
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_RECT2
             */
            val RECT2: GlobalShaderParameterType get() = GlobalShaderParameterType(18L)
            /**
             * 2×2 matrix global shader parameter (`global uniform mat2 ...`). Exposed as a `PackedInt32Array`
             * in the editor UI.
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_MAT2
             */
            val MAT2: GlobalShaderParameterType get() = GlobalShaderParameterType(19L)
            /**
             * 3×3 matrix global shader parameter (`global uniform mat3 ...`). Exposed as a `Basis` in the
             * editor UI.
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_MAT3
             */
            val MAT3: GlobalShaderParameterType get() = GlobalShaderParameterType(20L)
            /**
             * 4×4 matrix global shader parameter (`global uniform mat4 ...`). Exposed as a `Projection` in the
             * editor UI.
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_MAT4
             */
            val MAT4: GlobalShaderParameterType get() = GlobalShaderParameterType(21L)
            /**
             * 2-dimensional transform global shader parameter (`global uniform mat2x3 ...`). Exposed as a
             * `Transform2D` in the editor UI.
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_TRANSFORM_2D
             */
            val TRANSFORM_2D: GlobalShaderParameterType get() = GlobalShaderParameterType(22L)
            /**
             * 3-dimensional transform global shader parameter (`global uniform mat3x4 ...`). Exposed as a
             * `Transform3D` in the editor UI.
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_TRANSFORM
             */
            val TRANSFORM: GlobalShaderParameterType get() = GlobalShaderParameterType(23L)
            /**
             * 2D sampler global shader parameter (`global uniform sampler2D ...`). Exposed as a `Texture2D` in
             * the editor UI.
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_SAMPLER2D
             */
            val SAMPLER2D: GlobalShaderParameterType get() = GlobalShaderParameterType(24L)
            /**
             * 2D sampler array global shader parameter (`global uniform sampler2DArray ...`). Exposed as a
             * `Texture2DArray` in the editor UI.
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_SAMPLER2DARRAY
             */
            val SAMPLER2DARRAY: GlobalShaderParameterType get() = GlobalShaderParameterType(25L)
            /**
             * 3D sampler global shader parameter (`global uniform sampler3D ...`). Exposed as a `Texture3D` in
             * the editor UI.
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_SAMPLER3D
             */
            val SAMPLER3D: GlobalShaderParameterType get() = GlobalShaderParameterType(26L)
            /**
             * Cubemap sampler global shader parameter (`global uniform samplerCube ...`). Exposed as a
             * `Cubemap` in the editor UI.
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_SAMPLERCUBE
             */
            val SAMPLERCUBE: GlobalShaderParameterType get() = GlobalShaderParameterType(27L)
            /**
             * External sampler global shader parameter (`global uniform samplerExternalOES ...`). Exposed as
             * an `ExternalTexture` in the editor UI.
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_SAMPLEREXT
             */
            val SAMPLEREXT: GlobalShaderParameterType get() = GlobalShaderParameterType(28L)
            /**
             * Represents the size of the `GlobalShaderParameterType` enum.
             *
             * Generated from Godot docs: RenderingServer.GLOBAL_VAR_TYPE_MAX
             */
            val MAX: GlobalShaderParameterType get() = GlobalShaderParameterType(29L)
        }
    }

    @JvmInline
    value class RenderingInfo(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Number of objects rendered in the current 3D scene. This varies depending on camera position and
             * rotation.
             *
             * Generated from Godot docs: RenderingServer.RENDERING_INFO_TOTAL_OBJECTS_IN_FRAME
             */
            val TOTAL_OBJECTS_IN_FRAME: RenderingInfo get() = RenderingInfo(0L)
            /**
             * Number of points, lines, or triangles rendered in the current 3D scene. This varies depending on
             * camera position and rotation.
             *
             * Generated from Godot docs: RenderingServer.RENDERING_INFO_TOTAL_PRIMITIVES_IN_FRAME
             */
            val TOTAL_PRIMITIVES_IN_FRAME: RenderingInfo get() = RenderingInfo(1L)
            /**
             * Number of draw calls performed to render in the current 3D scene. This varies depending on
             * camera position and rotation.
             *
             * Generated from Godot docs: RenderingServer.RENDERING_INFO_TOTAL_DRAW_CALLS_IN_FRAME
             */
            val TOTAL_DRAW_CALLS_IN_FRAME: RenderingInfo get() = RenderingInfo(2L)
            /**
             * Texture memory used (in bytes).
             *
             * Generated from Godot docs: RenderingServer.RENDERING_INFO_TEXTURE_MEM_USED
             */
            val TEXTURE_MEM_USED: RenderingInfo get() = RenderingInfo(3L)
            /**
             * Buffer memory used (in bytes). This includes vertex data, uniform buffers, and many
             * miscellaneous buffer types used internally.
             *
             * Generated from Godot docs: RenderingServer.RENDERING_INFO_BUFFER_MEM_USED
             */
            val BUFFER_MEM_USED: RenderingInfo get() = RenderingInfo(4L)
            /**
             * Video memory used (in bytes). When using the Forward+ or Mobile renderers, this is always
             * greater than the sum of `RENDERING_INFO_TEXTURE_MEM_USED` and `RENDERING_INFO_BUFFER_MEM_USED`,
             * since there is miscellaneous data not accounted for by those two metrics. When using the
             * Compatibility renderer, this is equal to the sum of `RENDERING_INFO_TEXTURE_MEM_USED` and
             * `RENDERING_INFO_BUFFER_MEM_USED`.
             *
             * Generated from Godot docs: RenderingServer.RENDERING_INFO_VIDEO_MEM_USED
             */
            val VIDEO_MEM_USED: RenderingInfo get() = RenderingInfo(5L)
            /**
             * Number of pipeline compilations that were triggered by the 2D canvas renderer.
             *
             * Generated from Godot docs: RenderingServer.RENDERING_INFO_PIPELINE_COMPILATIONS_CANVAS
             */
            val PIPELINE_COMPILATIONS_CANVAS: RenderingInfo get() = RenderingInfo(6L)
            /**
             * Number of pipeline compilations that were triggered by loading meshes. These compilations will
             * show up as longer loading times the first time a user runs the game and the pipeline is
             * required.
             *
             * Generated from Godot docs: RenderingServer.RENDERING_INFO_PIPELINE_COMPILATIONS_MESH
             */
            val PIPELINE_COMPILATIONS_MESH: RenderingInfo get() = RenderingInfo(7L)
            /**
             * Number of pipeline compilations that were triggered by building the surface cache before
             * rendering the scene. These compilations will show up as a stutter when loading a scene the first
             * time a user runs the game and the pipeline is required.
             *
             * Generated from Godot docs: RenderingServer.RENDERING_INFO_PIPELINE_COMPILATIONS_SURFACE
             */
            val PIPELINE_COMPILATIONS_SURFACE: RenderingInfo get() = RenderingInfo(8L)
            /**
             * Number of pipeline compilations that were triggered while drawing the scene. These compilations
             * will show up as stutters during gameplay the first time a user runs the game and the pipeline is
             * required.
             *
             * Generated from Godot docs: RenderingServer.RENDERING_INFO_PIPELINE_COMPILATIONS_DRAW
             */
            val PIPELINE_COMPILATIONS_DRAW: RenderingInfo get() = RenderingInfo(9L)
            /**
             * Number of pipeline compilations that were triggered to optimize the current scene. These
             * compilations are done in the background and should not cause any stutters whatsoever.
             *
             * Generated from Godot docs: RenderingServer.RENDERING_INFO_PIPELINE_COMPILATIONS_SPECIALIZATION
             */
            val PIPELINE_COMPILATIONS_SPECIALIZATION: RenderingInfo get() = RenderingInfo(10L)
        }
    }

    @JvmInline
    value class PipelineSource(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Pipeline compilation that was triggered by the 2D canvas renderer.
             *
             * Generated from Godot docs: RenderingServer.PIPELINE_SOURCE_CANVAS
             */
            val CANVAS: PipelineSource get() = PipelineSource(0L)
            /**
             * Pipeline compilation that was triggered by loading a mesh.
             *
             * Generated from Godot docs: RenderingServer.PIPELINE_SOURCE_MESH
             */
            val MESH: PipelineSource get() = PipelineSource(1L)
            /**
             * Pipeline compilation that was triggered by building the surface cache before rendering the
             * scene.
             *
             * Generated from Godot docs: RenderingServer.PIPELINE_SOURCE_SURFACE
             */
            val SURFACE: PipelineSource get() = PipelineSource(2L)
            /**
             * Pipeline compilation that was triggered while drawing the scene.
             *
             * Generated from Godot docs: RenderingServer.PIPELINE_SOURCE_DRAW
             */
            val DRAW: PipelineSource get() = PipelineSource(3L)
            /**
             * Pipeline compilation that was triggered to optimize the current scene.
             *
             * Generated from Godot docs: RenderingServer.PIPELINE_SOURCE_SPECIALIZATION
             */
            val SPECIALIZATION: PipelineSource get() = PipelineSource(4L)
            /**
             * Represents the size of the `PipelineSource` enum.
             *
             * Generated from Godot docs: RenderingServer.PIPELINE_SOURCE_MAX
             */
            val MAX: PipelineSource get() = PipelineSource(5L)
        }
    }

    @JvmInline
    value class SplashStretchMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * No stretching is applied.
             *
             * Generated from Godot docs: RenderingServer.SPLASH_STRETCH_MODE_DISABLED
             */
            val DISABLED: SplashStretchMode get() = SplashStretchMode(0L)
            /**
             * Stretches image to fullscreen while preserving aspect ratio.
             *
             * Generated from Godot docs: RenderingServer.SPLASH_STRETCH_MODE_KEEP
             */
            val KEEP: SplashStretchMode get() = SplashStretchMode(1L)
            /**
             * Stretches the height of the image based on the width of the screen.
             *
             * Generated from Godot docs: RenderingServer.SPLASH_STRETCH_MODE_KEEP_WIDTH
             */
            val KEEP_WIDTH: SplashStretchMode get() = SplashStretchMode(2L)
            /**
             * Stretches the width of the image based on the height of the screen.
             *
             * Generated from Godot docs: RenderingServer.SPLASH_STRETCH_MODE_KEEP_HEIGHT
             */
            val KEEP_HEIGHT: SplashStretchMode get() = SplashStretchMode(3L)
            /**
             * Stretches the image to cover the entire screen while preserving aspect ratio.
             *
             * Generated from Godot docs: RenderingServer.SPLASH_STRETCH_MODE_COVER
             */
            val COVER: SplashStretchMode get() = SplashStretchMode(4L)
            /**
             * Stretches the image to cover the entire screen but doesn't preserve aspect ratio.
             *
             * Generated from Godot docs: RenderingServer.SPLASH_STRETCH_MODE_IGNORE
             */
            val IGNORE: SplashStretchMode get() = SplashStretchMode(5L)
        }
    }

    @JvmInline
    value class Features(override val value: Long) : GodotEnumValue {
        companion object {
            val SHADERS: Features get() = Features(0L)
            val MULTITHREADED: Features get() = Features(1L)
        }
    }

    @JvmStatic
    fun fromHandle(handle: GodotHandle): RenderingServer? =
        wrap(handle.segment)

    internal fun wrap(handle: RawSegment): RenderingServer? =
        if (handle.address() == 0L) null else this

    private const val TEXTURE_2D_CREATE_HASH = 2010018390L
    private val texture2dCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_2d_create", TEXTURE_2D_CREATE_HASH)
    }

    private const val TEXTURE_2D_LAYERED_CREATE_HASH = 913689023L
    private val texture2dLayeredCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_2d_layered_create", TEXTURE_2D_LAYERED_CREATE_HASH)
    }

    private const val TEXTURE_3D_CREATE_HASH = 4036838706L
    private val texture3dCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_3d_create", TEXTURE_3D_CREATE_HASH)
    }

    private const val TEXTURE_PROXY_CREATE_HASH = 41030802L
    private val textureProxyCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_proxy_create", TEXTURE_PROXY_CREATE_HASH)
    }

    private const val TEXTURE_CREATE_FROM_NATIVE_HANDLE_HASH = 1682977582L
    private val textureCreateFromNativeHandleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_create_from_native_handle", TEXTURE_CREATE_FROM_NATIVE_HANDLE_HASH)
    }

    private const val TEXTURE_DRAWABLE_CREATE_HASH = 1993613667L
    private val textureDrawableCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_drawable_create", TEXTURE_DRAWABLE_CREATE_HASH)
    }

    private const val TEXTURE_2D_UPDATE_HASH = 999539803L
    private val texture2dUpdateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_2d_update", TEXTURE_2D_UPDATE_HASH)
    }

    private const val TEXTURE_3D_UPDATE_HASH = 684822712L
    private val texture3dUpdateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_3d_update", TEXTURE_3D_UPDATE_HASH)
    }

    private const val TEXTURE_PROXY_UPDATE_HASH = 395945892L
    private val textureProxyUpdateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_proxy_update", TEXTURE_PROXY_UPDATE_HASH)
    }

    private const val TEXTURE_DRAWABLE_BLIT_RECT_HASH = 4077763890L
    private val textureDrawableBlitRectBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_drawable_blit_rect", TEXTURE_DRAWABLE_BLIT_RECT_HASH)
    }

    private const val TEXTURE_2D_PLACEHOLDER_CREATE_HASH = 529393457L
    private val texture2dPlaceholderCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_2d_placeholder_create", TEXTURE_2D_PLACEHOLDER_CREATE_HASH)
    }

    private const val TEXTURE_2D_LAYERED_PLACEHOLDER_CREATE_HASH = 1394585590L
    private val texture2dLayeredPlaceholderCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_2d_layered_placeholder_create", TEXTURE_2D_LAYERED_PLACEHOLDER_CREATE_HASH)
    }

    private const val TEXTURE_3D_PLACEHOLDER_CREATE_HASH = 529393457L
    private val texture3dPlaceholderCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_3d_placeholder_create", TEXTURE_3D_PLACEHOLDER_CREATE_HASH)
    }

    private const val TEXTURE_2D_GET_HASH = 4206205781L
    private val texture2dGetBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_2d_get", TEXTURE_2D_GET_HASH)
    }

    private const val TEXTURE_2D_LAYER_GET_HASH = 2705440895L
    private val texture2dLayerGetBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_2d_layer_get", TEXTURE_2D_LAYER_GET_HASH)
    }

    private const val TEXTURE_3D_GET_HASH = 2684255073L
    private val texture3dGetBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_3d_get", TEXTURE_3D_GET_HASH)
    }

    private const val TEXTURE_DRAWABLE_GENERATE_MIPMAPS_HASH = 2722037293L
    private val textureDrawableGenerateMipmapsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_drawable_generate_mipmaps", TEXTURE_DRAWABLE_GENERATE_MIPMAPS_HASH)
    }

    private const val TEXTURE_DRAWABLE_GET_DEFAULT_MATERIAL_HASH = 2944877500L
    private val textureDrawableGetDefaultMaterialBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_drawable_get_default_material", TEXTURE_DRAWABLE_GET_DEFAULT_MATERIAL_HASH)
    }

    private const val TEXTURE_REPLACE_HASH = 395945892L
    private val textureReplaceBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_replace", TEXTURE_REPLACE_HASH)
    }

    private const val TEXTURE_SET_SIZE_OVERRIDE_HASH = 4288446313L
    private val textureSetSizeOverrideBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_set_size_override", TEXTURE_SET_SIZE_OVERRIDE_HASH)
    }

    private const val TEXTURE_SET_PATH_HASH = 2726140452L
    private val textureSetPathBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_set_path", TEXTURE_SET_PATH_HASH)
    }

    private const val TEXTURE_GET_PATH_HASH = 642473191L
    private val textureGetPathBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_get_path", TEXTURE_GET_PATH_HASH)
    }

    private const val TEXTURE_GET_FORMAT_HASH = 1932918979L
    private val textureGetFormatBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_get_format", TEXTURE_GET_FORMAT_HASH)
    }

    private const val TEXTURE_SET_FORCE_REDRAW_IF_VISIBLE_HASH = 1265174801L
    private val textureSetForceRedrawIfVisibleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_set_force_redraw_if_visible", TEXTURE_SET_FORCE_REDRAW_IF_VISIBLE_HASH)
    }

    private const val TEXTURE_RD_CREATE_HASH = 1434128712L
    private val textureRdCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_rd_create", TEXTURE_RD_CREATE_HASH)
    }

    private const val TEXTURE_GET_RD_TEXTURE_HASH = 2790148051L
    private val textureGetRdTextureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_get_rd_texture", TEXTURE_GET_RD_TEXTURE_HASH)
    }

    private const val TEXTURE_GET_NATIVE_HANDLE_HASH = 1834114100L
    private val textureGetNativeHandleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "texture_get_native_handle", TEXTURE_GET_NATIVE_HANDLE_HASH)
    }

    private const val SHADER_CREATE_HASH = 529393457L
    private val shaderCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "shader_create", SHADER_CREATE_HASH)
    }

    private const val SHADER_SET_CODE_HASH = 2726140452L
    private val shaderSetCodeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "shader_set_code", SHADER_SET_CODE_HASH)
    }

    private const val SHADER_SET_PATH_HINT_HASH = 2726140452L
    private val shaderSetPathHintBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "shader_set_path_hint", SHADER_SET_PATH_HINT_HASH)
    }

    private const val SHADER_GET_CODE_HASH = 642473191L
    private val shaderGetCodeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "shader_get_code", SHADER_GET_CODE_HASH)
    }

    private const val GET_SHADER_PARAMETER_LIST_HASH = 2684255073L
    private val getShaderParameterListBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_shader_parameter_list", GET_SHADER_PARAMETER_LIST_HASH)
    }

    private const val SHADER_GET_PARAMETER_DEFAULT_HASH = 2621281810L
    private val shaderGetParameterDefaultBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "shader_get_parameter_default", SHADER_GET_PARAMETER_DEFAULT_HASH)
    }

    private const val SHADER_SET_DEFAULT_TEXTURE_PARAMETER_HASH = 4094001817L
    private val shaderSetDefaultTextureParameterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "shader_set_default_texture_parameter", SHADER_SET_DEFAULT_TEXTURE_PARAMETER_HASH)
    }

    private const val SHADER_GET_DEFAULT_TEXTURE_PARAMETER_HASH = 1464608890L
    private val shaderGetDefaultTextureParameterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "shader_get_default_texture_parameter", SHADER_GET_DEFAULT_TEXTURE_PARAMETER_HASH)
    }

    private const val MATERIAL_CREATE_HASH = 529393457L
    private val materialCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "material_create", MATERIAL_CREATE_HASH)
    }

    private const val MATERIAL_SET_SHADER_HASH = 395945892L
    private val materialSetShaderBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "material_set_shader", MATERIAL_SET_SHADER_HASH)
    }

    private const val MATERIAL_SET_PARAM_HASH = 3477296213L
    private val materialSetParamBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "material_set_param", MATERIAL_SET_PARAM_HASH)
    }

    private const val MATERIAL_GET_PARAM_HASH = 2621281810L
    private val materialGetParamBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "material_get_param", MATERIAL_GET_PARAM_HASH)
    }

    private const val MATERIAL_SET_RENDER_PRIORITY_HASH = 3411492887L
    private val materialSetRenderPriorityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "material_set_render_priority", MATERIAL_SET_RENDER_PRIORITY_HASH)
    }

    private const val MATERIAL_SET_NEXT_PASS_HASH = 395945892L
    private val materialSetNextPassBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "material_set_next_pass", MATERIAL_SET_NEXT_PASS_HASH)
    }

    private const val MATERIAL_SET_USE_DEBANDING_HASH = 2586408642L
    private val materialSetUseDebandingBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "material_set_use_debanding", MATERIAL_SET_USE_DEBANDING_HASH)
    }

    private const val MESH_CREATE_FROM_SURFACES_HASH = 4291747531L
    private val meshCreateFromSurfacesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_create_from_surfaces", MESH_CREATE_FROM_SURFACES_HASH)
    }

    private const val MESH_CREATE_HASH = 529393457L
    private val meshCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_create", MESH_CREATE_HASH)
    }

    private const val MESH_SURFACE_GET_FORMAT_OFFSET_HASH = 2981368685L
    private val meshSurfaceGetFormatOffsetBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_format_offset", MESH_SURFACE_GET_FORMAT_OFFSET_HASH)
    }

    private const val MESH_SURFACE_GET_FORMAT_VERTEX_STRIDE_HASH = 3188363337L
    private val meshSurfaceGetFormatVertexStrideBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_format_vertex_stride", MESH_SURFACE_GET_FORMAT_VERTEX_STRIDE_HASH)
    }

    private const val MESH_SURFACE_GET_FORMAT_NORMAL_TANGENT_STRIDE_HASH = 3188363337L
    private val meshSurfaceGetFormatNormalTangentStrideBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_format_normal_tangent_stride", MESH_SURFACE_GET_FORMAT_NORMAL_TANGENT_STRIDE_HASH)
    }

    private const val MESH_SURFACE_GET_FORMAT_ATTRIBUTE_STRIDE_HASH = 3188363337L
    private val meshSurfaceGetFormatAttributeStrideBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_format_attribute_stride", MESH_SURFACE_GET_FORMAT_ATTRIBUTE_STRIDE_HASH)
    }

    private const val MESH_SURFACE_GET_FORMAT_SKIN_STRIDE_HASH = 3188363337L
    private val meshSurfaceGetFormatSkinStrideBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_format_skin_stride", MESH_SURFACE_GET_FORMAT_SKIN_STRIDE_HASH)
    }

    private const val MESH_SURFACE_GET_FORMAT_INDEX_STRIDE_HASH = 3188363337L
    private val meshSurfaceGetFormatIndexStrideBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_format_index_stride", MESH_SURFACE_GET_FORMAT_INDEX_STRIDE_HASH)
    }

    private const val MESH_ADD_SURFACE_HASH = 1217542888L
    private val meshAddSurfaceBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_add_surface", MESH_ADD_SURFACE_HASH)
    }

    private const val MESH_ADD_SURFACE_FROM_ARRAYS_HASH = 2342446560L
    private val meshAddSurfaceFromArraysBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_add_surface_from_arrays", MESH_ADD_SURFACE_FROM_ARRAYS_HASH)
    }

    private const val MESH_GET_BLEND_SHAPE_COUNT_HASH = 2198884583L
    private val meshGetBlendShapeCountBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_get_blend_shape_count", MESH_GET_BLEND_SHAPE_COUNT_HASH)
    }

    private const val MESH_SET_BLEND_SHAPE_MODE_HASH = 1294662092L
    private val meshSetBlendShapeModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_set_blend_shape_mode", MESH_SET_BLEND_SHAPE_MODE_HASH)
    }

    private const val MESH_GET_BLEND_SHAPE_MODE_HASH = 4282291819L
    private val meshGetBlendShapeModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_get_blend_shape_mode", MESH_GET_BLEND_SHAPE_MODE_HASH)
    }

    private const val MESH_SURFACE_SET_MATERIAL_HASH = 2310537182L
    private val meshSurfaceSetMaterialBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_set_material", MESH_SURFACE_SET_MATERIAL_HASH)
    }

    private const val MESH_SURFACE_GET_MATERIAL_HASH = 1066463050L
    private val meshSurfaceGetMaterialBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_material", MESH_SURFACE_GET_MATERIAL_HASH)
    }

    private const val MESH_GET_SURFACE_HASH = 186674697L
    private val meshGetSurfaceBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_get_surface", MESH_GET_SURFACE_HASH)
    }

    private const val MESH_SURFACE_GET_ARRAYS_HASH = 1778388067L
    private val meshSurfaceGetArraysBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_arrays", MESH_SURFACE_GET_ARRAYS_HASH)
    }

    private const val MESH_SURFACE_GET_BLEND_SHAPE_ARRAYS_HASH = 1778388067L
    private val meshSurfaceGetBlendShapeArraysBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_get_blend_shape_arrays", MESH_SURFACE_GET_BLEND_SHAPE_ARRAYS_HASH)
    }

    private const val MESH_GET_SURFACE_COUNT_HASH = 2198884583L
    private val meshGetSurfaceCountBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_get_surface_count", MESH_GET_SURFACE_COUNT_HASH)
    }

    private const val MESH_SET_CUSTOM_AABB_HASH = 3696536120L
    private val meshSetCustomAabbBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_set_custom_aabb", MESH_SET_CUSTOM_AABB_HASH)
    }

    private const val MESH_GET_CUSTOM_AABB_HASH = 974181306L
    private val meshGetCustomAabbBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_get_custom_aabb", MESH_GET_CUSTOM_AABB_HASH)
    }

    private const val MESH_SURFACE_REMOVE_HASH = 3411492887L
    private val meshSurfaceRemoveBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_remove", MESH_SURFACE_REMOVE_HASH)
    }

    private const val MESH_CLEAR_HASH = 2722037293L
    private val meshClearBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_clear", MESH_CLEAR_HASH)
    }

    private const val MESH_SURFACE_UPDATE_VERTEX_REGION_HASH = 2900195149L
    private val meshSurfaceUpdateVertexRegionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_update_vertex_region", MESH_SURFACE_UPDATE_VERTEX_REGION_HASH)
    }

    private const val MESH_SURFACE_UPDATE_ATTRIBUTE_REGION_HASH = 2900195149L
    private val meshSurfaceUpdateAttributeRegionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_update_attribute_region", MESH_SURFACE_UPDATE_ATTRIBUTE_REGION_HASH)
    }

    private const val MESH_SURFACE_UPDATE_SKIN_REGION_HASH = 2900195149L
    private val meshSurfaceUpdateSkinRegionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_update_skin_region", MESH_SURFACE_UPDATE_SKIN_REGION_HASH)
    }

    private const val MESH_SURFACE_UPDATE_INDEX_REGION_HASH = 2900195149L
    private val meshSurfaceUpdateIndexRegionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_surface_update_index_region", MESH_SURFACE_UPDATE_INDEX_REGION_HASH)
    }

    private const val MESH_SET_SHADOW_MESH_HASH = 395945892L
    private val meshSetShadowMeshBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "mesh_set_shadow_mesh", MESH_SET_SHADOW_MESH_HASH)
    }

    private const val MULTIMESH_CREATE_HASH = 529393457L
    private val multimeshCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_create", MULTIMESH_CREATE_HASH)
    }

    private const val MULTIMESH_ALLOCATE_DATA_HASH = 557240154L
    private val multimeshAllocateDataBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_allocate_data", MULTIMESH_ALLOCATE_DATA_HASH)
    }

    private const val MULTIMESH_GET_INSTANCE_COUNT_HASH = 2198884583L
    private val multimeshGetInstanceCountBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_get_instance_count", MULTIMESH_GET_INSTANCE_COUNT_HASH)
    }

    private const val MULTIMESH_SET_MESH_HASH = 395945892L
    private val multimeshSetMeshBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_set_mesh", MULTIMESH_SET_MESH_HASH)
    }

    private const val MULTIMESH_INSTANCE_SET_TRANSFORM_HASH = 675327471L
    private val multimeshInstanceSetTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_instance_set_transform", MULTIMESH_INSTANCE_SET_TRANSFORM_HASH)
    }

    private const val MULTIMESH_INSTANCE_SET_TRANSFORM_2D_HASH = 736082694L
    private val multimeshInstanceSetTransform2dBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_instance_set_transform_2d", MULTIMESH_INSTANCE_SET_TRANSFORM_2D_HASH)
    }

    private const val MULTIMESH_INSTANCE_SET_COLOR_HASH = 176975443L
    private val multimeshInstanceSetColorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_instance_set_color", MULTIMESH_INSTANCE_SET_COLOR_HASH)
    }

    private const val MULTIMESH_INSTANCE_SET_CUSTOM_DATA_HASH = 176975443L
    private val multimeshInstanceSetCustomDataBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_instance_set_custom_data", MULTIMESH_INSTANCE_SET_CUSTOM_DATA_HASH)
    }

    private const val MULTIMESH_GET_MESH_HASH = 3814569979L
    private val multimeshGetMeshBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_get_mesh", MULTIMESH_GET_MESH_HASH)
    }

    private const val MULTIMESH_GET_AABB_HASH = 974181306L
    private val multimeshGetAabbBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_get_aabb", MULTIMESH_GET_AABB_HASH)
    }

    private const val MULTIMESH_SET_CUSTOM_AABB_HASH = 3696536120L
    private val multimeshSetCustomAabbBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_set_custom_aabb", MULTIMESH_SET_CUSTOM_AABB_HASH)
    }

    private const val MULTIMESH_GET_CUSTOM_AABB_HASH = 974181306L
    private val multimeshGetCustomAabbBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_get_custom_aabb", MULTIMESH_GET_CUSTOM_AABB_HASH)
    }

    private const val MULTIMESH_INSTANCE_GET_TRANSFORM_HASH = 1050775521L
    private val multimeshInstanceGetTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_instance_get_transform", MULTIMESH_INSTANCE_GET_TRANSFORM_HASH)
    }

    private const val MULTIMESH_INSTANCE_GET_TRANSFORM_2D_HASH = 1324854622L
    private val multimeshInstanceGetTransform2dBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_instance_get_transform_2d", MULTIMESH_INSTANCE_GET_TRANSFORM_2D_HASH)
    }

    private const val MULTIMESH_INSTANCE_GET_COLOR_HASH = 2946315076L
    private val multimeshInstanceGetColorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_instance_get_color", MULTIMESH_INSTANCE_GET_COLOR_HASH)
    }

    private const val MULTIMESH_INSTANCE_GET_CUSTOM_DATA_HASH = 2946315076L
    private val multimeshInstanceGetCustomDataBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_instance_get_custom_data", MULTIMESH_INSTANCE_GET_CUSTOM_DATA_HASH)
    }

    private const val MULTIMESH_SET_VISIBLE_INSTANCES_HASH = 3411492887L
    private val multimeshSetVisibleInstancesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_set_visible_instances", MULTIMESH_SET_VISIBLE_INSTANCES_HASH)
    }

    private const val MULTIMESH_GET_VISIBLE_INSTANCES_HASH = 2198884583L
    private val multimeshGetVisibleInstancesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_get_visible_instances", MULTIMESH_GET_VISIBLE_INSTANCES_HASH)
    }

    private const val MULTIMESH_SET_BUFFER_HASH = 2960552364L
    private val multimeshSetBufferBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_set_buffer", MULTIMESH_SET_BUFFER_HASH)
    }

    private const val MULTIMESH_GET_COMMAND_BUFFER_RD_RID_HASH = 3814569979L
    private val multimeshGetCommandBufferRdRidBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_get_command_buffer_rd_rid", MULTIMESH_GET_COMMAND_BUFFER_RD_RID_HASH)
    }

    private const val MULTIMESH_GET_BUFFER_RD_RID_HASH = 3814569979L
    private val multimeshGetBufferRdRidBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_get_buffer_rd_rid", MULTIMESH_GET_BUFFER_RD_RID_HASH)
    }

    private const val MULTIMESH_GET_BUFFER_HASH = 3964669176L
    private val multimeshGetBufferBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_get_buffer", MULTIMESH_GET_BUFFER_HASH)
    }

    private const val MULTIMESH_SET_BUFFER_INTERPOLATED_HASH = 659844711L
    private val multimeshSetBufferInterpolatedBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_set_buffer_interpolated", MULTIMESH_SET_BUFFER_INTERPOLATED_HASH)
    }

    private const val MULTIMESH_SET_PHYSICS_INTERPOLATED_HASH = 1265174801L
    private val multimeshSetPhysicsInterpolatedBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_set_physics_interpolated", MULTIMESH_SET_PHYSICS_INTERPOLATED_HASH)
    }

    private const val MULTIMESH_SET_PHYSICS_INTERPOLATION_QUALITY_HASH = 3934808223L
    private val multimeshSetPhysicsInterpolationQualityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_set_physics_interpolation_quality", MULTIMESH_SET_PHYSICS_INTERPOLATION_QUALITY_HASH)
    }

    private const val MULTIMESH_INSTANCE_RESET_PHYSICS_INTERPOLATION_HASH = 3411492887L
    private val multimeshInstanceResetPhysicsInterpolationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_instance_reset_physics_interpolation", MULTIMESH_INSTANCE_RESET_PHYSICS_INTERPOLATION_HASH)
    }

    private const val MULTIMESH_INSTANCES_RESET_PHYSICS_INTERPOLATION_HASH = 2722037293L
    private val multimeshInstancesResetPhysicsInterpolationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "multimesh_instances_reset_physics_interpolation", MULTIMESH_INSTANCES_RESET_PHYSICS_INTERPOLATION_HASH)
    }

    private const val SKELETON_CREATE_HASH = 529393457L
    private val skeletonCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "skeleton_create", SKELETON_CREATE_HASH)
    }

    private const val SKELETON_ALLOCATE_DATA_HASH = 1904426712L
    private val skeletonAllocateDataBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "skeleton_allocate_data", SKELETON_ALLOCATE_DATA_HASH)
    }

    private const val SKELETON_GET_BONE_COUNT_HASH = 2198884583L
    private val skeletonGetBoneCountBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "skeleton_get_bone_count", SKELETON_GET_BONE_COUNT_HASH)
    }

    private const val SKELETON_BONE_SET_TRANSFORM_HASH = 675327471L
    private val skeletonBoneSetTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "skeleton_bone_set_transform", SKELETON_BONE_SET_TRANSFORM_HASH)
    }

    private const val SKELETON_BONE_GET_TRANSFORM_HASH = 1050775521L
    private val skeletonBoneGetTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "skeleton_bone_get_transform", SKELETON_BONE_GET_TRANSFORM_HASH)
    }

    private const val SKELETON_BONE_SET_TRANSFORM_2D_HASH = 736082694L
    private val skeletonBoneSetTransform2dBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "skeleton_bone_set_transform_2d", SKELETON_BONE_SET_TRANSFORM_2D_HASH)
    }

    private const val SKELETON_BONE_GET_TRANSFORM_2D_HASH = 1324854622L
    private val skeletonBoneGetTransform2dBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "skeleton_bone_get_transform_2d", SKELETON_BONE_GET_TRANSFORM_2D_HASH)
    }

    private const val SKELETON_SET_BASE_TRANSFORM_2D_HASH = 1246044741L
    private val skeletonSetBaseTransform2dBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "skeleton_set_base_transform_2d", SKELETON_SET_BASE_TRANSFORM_2D_HASH)
    }

    private const val DIRECTIONAL_LIGHT_CREATE_HASH = 529393457L
    private val directionalLightCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "directional_light_create", DIRECTIONAL_LIGHT_CREATE_HASH)
    }

    private const val OMNI_LIGHT_CREATE_HASH = 529393457L
    private val omniLightCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "omni_light_create", OMNI_LIGHT_CREATE_HASH)
    }

    private const val SPOT_LIGHT_CREATE_HASH = 529393457L
    private val spotLightCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "spot_light_create", SPOT_LIGHT_CREATE_HASH)
    }

    private const val AREA_LIGHT_CREATE_HASH = 529393457L
    private val areaLightCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "area_light_create", AREA_LIGHT_CREATE_HASH)
    }

    private const val LIGHT_SET_COLOR_HASH = 2948539648L
    private val lightSetColorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_color", LIGHT_SET_COLOR_HASH)
    }

    private const val LIGHT_SET_PARAM_HASH = 501936875L
    private val lightSetParamBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_param", LIGHT_SET_PARAM_HASH)
    }

    private const val LIGHT_SET_SHADOW_HASH = 1265174801L
    private val lightSetShadowBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_shadow", LIGHT_SET_SHADOW_HASH)
    }

    private const val LIGHT_SET_PROJECTOR_HASH = 395945892L
    private val lightSetProjectorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_projector", LIGHT_SET_PROJECTOR_HASH)
    }

    private const val LIGHT_SET_NEGATIVE_HASH = 1265174801L
    private val lightSetNegativeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_negative", LIGHT_SET_NEGATIVE_HASH)
    }

    private const val LIGHT_SET_CULL_MASK_HASH = 3411492887L
    private val lightSetCullMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_cull_mask", LIGHT_SET_CULL_MASK_HASH)
    }

    private const val LIGHT_SET_DISTANCE_FADE_HASH = 1622292572L
    private val lightSetDistanceFadeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_distance_fade", LIGHT_SET_DISTANCE_FADE_HASH)
    }

    private const val LIGHT_SET_REVERSE_CULL_FACE_MODE_HASH = 1265174801L
    private val lightSetReverseCullFaceModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_reverse_cull_face_mode", LIGHT_SET_REVERSE_CULL_FACE_MODE_HASH)
    }

    private const val LIGHT_SET_SHADOW_CASTER_MASK_HASH = 3411492887L
    private val lightSetShadowCasterMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_shadow_caster_mask", LIGHT_SET_SHADOW_CASTER_MASK_HASH)
    }

    private const val LIGHT_SET_BAKE_MODE_HASH = 1048525260L
    private val lightSetBakeModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_bake_mode", LIGHT_SET_BAKE_MODE_HASH)
    }

    private const val LIGHT_SET_MAX_SDFGI_CASCADE_HASH = 3411492887L
    private val lightSetMaxSdfgiCascadeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_set_max_sdfgi_cascade", LIGHT_SET_MAX_SDFGI_CASCADE_HASH)
    }

    private const val LIGHT_OMNI_SET_SHADOW_MODE_HASH = 2552677200L
    private val lightOmniSetShadowModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_omni_set_shadow_mode", LIGHT_OMNI_SET_SHADOW_MODE_HASH)
    }

    private const val LIGHT_DIRECTIONAL_SET_SHADOW_MODE_HASH = 380462970L
    private val lightDirectionalSetShadowModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_directional_set_shadow_mode", LIGHT_DIRECTIONAL_SET_SHADOW_MODE_HASH)
    }

    private const val LIGHT_DIRECTIONAL_SET_BLEND_SPLITS_HASH = 1265174801L
    private val lightDirectionalSetBlendSplitsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_directional_set_blend_splits", LIGHT_DIRECTIONAL_SET_BLEND_SPLITS_HASH)
    }

    private const val LIGHT_DIRECTIONAL_SET_SKY_MODE_HASH = 2559740754L
    private val lightDirectionalSetSkyModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_directional_set_sky_mode", LIGHT_DIRECTIONAL_SET_SKY_MODE_HASH)
    }

    private const val LIGHT_AREA_SET_SIZE_HASH = 3201125042L
    private val lightAreaSetSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_area_set_size", LIGHT_AREA_SET_SIZE_HASH)
    }

    private const val LIGHT_AREA_SET_NORMALIZE_ENERGY_HASH = 1265174801L
    private val lightAreaSetNormalizeEnergyBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_area_set_normalize_energy", LIGHT_AREA_SET_NORMALIZE_ENERGY_HASH)
    }

    private const val LIGHT_PROJECTORS_SET_FILTER_HASH = 43944325L
    private val lightProjectorsSetFilterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "light_projectors_set_filter", LIGHT_PROJECTORS_SET_FILTER_HASH)
    }

    private const val LIGHTMAPS_SET_BICUBIC_FILTER_HASH = 2586408642L
    private val lightmapsSetBicubicFilterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmaps_set_bicubic_filter", LIGHTMAPS_SET_BICUBIC_FILTER_HASH)
    }

    private const val POSITIONAL_SOFT_SHADOW_FILTER_SET_QUALITY_HASH = 3613045266L
    private val positionalSoftShadowFilterSetQualityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "positional_soft_shadow_filter_set_quality", POSITIONAL_SOFT_SHADOW_FILTER_SET_QUALITY_HASH)
    }

    private const val DIRECTIONAL_SOFT_SHADOW_FILTER_SET_QUALITY_HASH = 3613045266L
    private val directionalSoftShadowFilterSetQualityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "directional_soft_shadow_filter_set_quality", DIRECTIONAL_SOFT_SHADOW_FILTER_SET_QUALITY_HASH)
    }

    private const val DIRECTIONAL_SHADOW_ATLAS_SET_SIZE_HASH = 300928843L
    private val directionalShadowAtlasSetSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "directional_shadow_atlas_set_size", DIRECTIONAL_SHADOW_ATLAS_SET_SIZE_HASH)
    }

    private const val REFLECTION_PROBE_CREATE_HASH = 529393457L
    private val reflectionProbeCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_create", REFLECTION_PROBE_CREATE_HASH)
    }

    private const val REFLECTION_PROBE_SET_UPDATE_MODE_HASH = 3853670147L
    private val reflectionProbeSetUpdateModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_update_mode", REFLECTION_PROBE_SET_UPDATE_MODE_HASH)
    }

    private const val REFLECTION_PROBE_SET_INTENSITY_HASH = 1794382983L
    private val reflectionProbeSetIntensityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_intensity", REFLECTION_PROBE_SET_INTENSITY_HASH)
    }

    private const val REFLECTION_PROBE_SET_BLEND_DISTANCE_HASH = 1794382983L
    private val reflectionProbeSetBlendDistanceBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_blend_distance", REFLECTION_PROBE_SET_BLEND_DISTANCE_HASH)
    }

    private const val REFLECTION_PROBE_SET_AMBIENT_MODE_HASH = 184163074L
    private val reflectionProbeSetAmbientModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_ambient_mode", REFLECTION_PROBE_SET_AMBIENT_MODE_HASH)
    }

    private const val REFLECTION_PROBE_SET_AMBIENT_COLOR_HASH = 2948539648L
    private val reflectionProbeSetAmbientColorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_ambient_color", REFLECTION_PROBE_SET_AMBIENT_COLOR_HASH)
    }

    private const val REFLECTION_PROBE_SET_AMBIENT_ENERGY_HASH = 1794382983L
    private val reflectionProbeSetAmbientEnergyBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_ambient_energy", REFLECTION_PROBE_SET_AMBIENT_ENERGY_HASH)
    }

    private const val REFLECTION_PROBE_SET_MAX_DISTANCE_HASH = 1794382983L
    private val reflectionProbeSetMaxDistanceBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_max_distance", REFLECTION_PROBE_SET_MAX_DISTANCE_HASH)
    }

    private const val REFLECTION_PROBE_SET_SIZE_HASH = 3227306858L
    private val reflectionProbeSetSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_size", REFLECTION_PROBE_SET_SIZE_HASH)
    }

    private const val REFLECTION_PROBE_SET_ORIGIN_OFFSET_HASH = 3227306858L
    private val reflectionProbeSetOriginOffsetBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_origin_offset", REFLECTION_PROBE_SET_ORIGIN_OFFSET_HASH)
    }

    private const val REFLECTION_PROBE_SET_AS_INTERIOR_HASH = 1265174801L
    private val reflectionProbeSetAsInteriorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_as_interior", REFLECTION_PROBE_SET_AS_INTERIOR_HASH)
    }

    private const val REFLECTION_PROBE_SET_ENABLE_BOX_PROJECTION_HASH = 1265174801L
    private val reflectionProbeSetEnableBoxProjectionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_enable_box_projection", REFLECTION_PROBE_SET_ENABLE_BOX_PROJECTION_HASH)
    }

    private const val REFLECTION_PROBE_SET_ENABLE_SHADOWS_HASH = 1265174801L
    private val reflectionProbeSetEnableShadowsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_enable_shadows", REFLECTION_PROBE_SET_ENABLE_SHADOWS_HASH)
    }

    private const val REFLECTION_PROBE_SET_CULL_MASK_HASH = 3411492887L
    private val reflectionProbeSetCullMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_cull_mask", REFLECTION_PROBE_SET_CULL_MASK_HASH)
    }

    private const val REFLECTION_PROBE_SET_REFLECTION_MASK_HASH = 3411492887L
    private val reflectionProbeSetReflectionMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_reflection_mask", REFLECTION_PROBE_SET_REFLECTION_MASK_HASH)
    }

    private const val REFLECTION_PROBE_SET_RESOLUTION_HASH = 3411492887L
    private val reflectionProbeSetResolutionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_resolution", REFLECTION_PROBE_SET_RESOLUTION_HASH)
    }

    private const val REFLECTION_PROBE_SET_MESH_LOD_THRESHOLD_HASH = 1794382983L
    private val reflectionProbeSetMeshLodThresholdBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "reflection_probe_set_mesh_lod_threshold", REFLECTION_PROBE_SET_MESH_LOD_THRESHOLD_HASH)
    }

    private const val DECAL_CREATE_HASH = 529393457L
    private val decalCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decal_create", DECAL_CREATE_HASH)
    }

    private const val DECAL_SET_SIZE_HASH = 3227306858L
    private val decalSetSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decal_set_size", DECAL_SET_SIZE_HASH)
    }

    private const val DECAL_SET_TEXTURE_HASH = 3953344054L
    private val decalSetTextureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decal_set_texture", DECAL_SET_TEXTURE_HASH)
    }

    private const val DECAL_SET_EMISSION_ENERGY_HASH = 1794382983L
    private val decalSetEmissionEnergyBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decal_set_emission_energy", DECAL_SET_EMISSION_ENERGY_HASH)
    }

    private const val DECAL_SET_ALBEDO_MIX_HASH = 1794382983L
    private val decalSetAlbedoMixBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decal_set_albedo_mix", DECAL_SET_ALBEDO_MIX_HASH)
    }

    private const val DECAL_SET_MODULATE_HASH = 2948539648L
    private val decalSetModulateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decal_set_modulate", DECAL_SET_MODULATE_HASH)
    }

    private const val DECAL_SET_CULL_MASK_HASH = 3411492887L
    private val decalSetCullMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decal_set_cull_mask", DECAL_SET_CULL_MASK_HASH)
    }

    private const val DECAL_SET_DISTANCE_FADE_HASH = 2972769666L
    private val decalSetDistanceFadeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decal_set_distance_fade", DECAL_SET_DISTANCE_FADE_HASH)
    }

    private const val DECAL_SET_FADE_HASH = 2513314492L
    private val decalSetFadeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decal_set_fade", DECAL_SET_FADE_HASH)
    }

    private const val DECAL_SET_NORMAL_FADE_HASH = 1794382983L
    private val decalSetNormalFadeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decal_set_normal_fade", DECAL_SET_NORMAL_FADE_HASH)
    }

    private const val DECALS_SET_FILTER_HASH = 3519875702L
    private val decalsSetFilterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "decals_set_filter", DECALS_SET_FILTER_HASH)
    }

    private const val GI_SET_USE_HALF_RESOLUTION_HASH = 2586408642L
    private val giSetUseHalfResolutionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "gi_set_use_half_resolution", GI_SET_USE_HALF_RESOLUTION_HASH)
    }

    private const val VOXEL_GI_CREATE_HASH = 529393457L
    private val voxelGiCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_create", VOXEL_GI_CREATE_HASH)
    }

    private const val VOXEL_GI_ALLOCATE_DATA_HASH = 4108223027L
    private val voxelGiAllocateDataBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_allocate_data", VOXEL_GI_ALLOCATE_DATA_HASH)
    }

    private const val VOXEL_GI_GET_OCTREE_SIZE_HASH = 2607699645L
    private val voxelGiGetOctreeSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_get_octree_size", VOXEL_GI_GET_OCTREE_SIZE_HASH)
    }

    private const val VOXEL_GI_GET_OCTREE_CELLS_HASH = 3348040486L
    private val voxelGiGetOctreeCellsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_get_octree_cells", VOXEL_GI_GET_OCTREE_CELLS_HASH)
    }

    private const val VOXEL_GI_GET_DATA_CELLS_HASH = 3348040486L
    private val voxelGiGetDataCellsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_get_data_cells", VOXEL_GI_GET_DATA_CELLS_HASH)
    }

    private const val VOXEL_GI_GET_DISTANCE_FIELD_HASH = 3348040486L
    private val voxelGiGetDistanceFieldBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_get_distance_field", VOXEL_GI_GET_DISTANCE_FIELD_HASH)
    }

    private const val VOXEL_GI_GET_LEVEL_COUNTS_HASH = 788230395L
    private val voxelGiGetLevelCountsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_get_level_counts", VOXEL_GI_GET_LEVEL_COUNTS_HASH)
    }

    private const val VOXEL_GI_GET_TO_CELL_XFORM_HASH = 1128465797L
    private val voxelGiGetToCellXformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_get_to_cell_xform", VOXEL_GI_GET_TO_CELL_XFORM_HASH)
    }

    private const val VOXEL_GI_SET_DYNAMIC_RANGE_HASH = 1794382983L
    private val voxelGiSetDynamicRangeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_set_dynamic_range", VOXEL_GI_SET_DYNAMIC_RANGE_HASH)
    }

    private const val VOXEL_GI_SET_PROPAGATION_HASH = 1794382983L
    private val voxelGiSetPropagationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_set_propagation", VOXEL_GI_SET_PROPAGATION_HASH)
    }

    private const val VOXEL_GI_SET_ENERGY_HASH = 1794382983L
    private val voxelGiSetEnergyBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_set_energy", VOXEL_GI_SET_ENERGY_HASH)
    }

    private const val VOXEL_GI_SET_BAKED_EXPOSURE_NORMALIZATION_HASH = 1794382983L
    private val voxelGiSetBakedExposureNormalizationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_set_baked_exposure_normalization", VOXEL_GI_SET_BAKED_EXPOSURE_NORMALIZATION_HASH)
    }

    private const val VOXEL_GI_SET_BIAS_HASH = 1794382983L
    private val voxelGiSetBiasBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_set_bias", VOXEL_GI_SET_BIAS_HASH)
    }

    private const val VOXEL_GI_SET_NORMAL_BIAS_HASH = 1794382983L
    private val voxelGiSetNormalBiasBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_set_normal_bias", VOXEL_GI_SET_NORMAL_BIAS_HASH)
    }

    private const val VOXEL_GI_SET_INTERIOR_HASH = 1265174801L
    private val voxelGiSetInteriorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_set_interior", VOXEL_GI_SET_INTERIOR_HASH)
    }

    private const val VOXEL_GI_SET_USE_TWO_BOUNCES_HASH = 1265174801L
    private val voxelGiSetUseTwoBouncesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_set_use_two_bounces", VOXEL_GI_SET_USE_TWO_BOUNCES_HASH)
    }

    private const val VOXEL_GI_SET_QUALITY_HASH = 1538689978L
    private val voxelGiSetQualityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "voxel_gi_set_quality", VOXEL_GI_SET_QUALITY_HASH)
    }

    private const val LIGHTMAP_CREATE_HASH = 529393457L
    private val lightmapCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_create", LIGHTMAP_CREATE_HASH)
    }

    private const val LIGHTMAP_SET_TEXTURES_HASH = 2646464759L
    private val lightmapSetTexturesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_set_textures", LIGHTMAP_SET_TEXTURES_HASH)
    }

    private const val LIGHTMAP_SET_PROBE_BOUNDS_HASH = 3696536120L
    private val lightmapSetProbeBoundsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_set_probe_bounds", LIGHTMAP_SET_PROBE_BOUNDS_HASH)
    }

    private const val LIGHTMAP_SET_PROBE_INTERIOR_HASH = 1265174801L
    private val lightmapSetProbeInteriorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_set_probe_interior", LIGHTMAP_SET_PROBE_INTERIOR_HASH)
    }

    private const val LIGHTMAP_SET_PROBE_CAPTURE_DATA_HASH = 3217845880L
    private val lightmapSetProbeCaptureDataBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_set_probe_capture_data", LIGHTMAP_SET_PROBE_CAPTURE_DATA_HASH)
    }

    private const val LIGHTMAP_GET_PROBE_CAPTURE_POINTS_HASH = 808965560L
    private val lightmapGetProbeCapturePointsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_get_probe_capture_points", LIGHTMAP_GET_PROBE_CAPTURE_POINTS_HASH)
    }

    private const val LIGHTMAP_GET_PROBE_CAPTURE_SH_HASH = 1569415609L
    private val lightmapGetProbeCaptureShBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_get_probe_capture_sh", LIGHTMAP_GET_PROBE_CAPTURE_SH_HASH)
    }

    private const val LIGHTMAP_GET_PROBE_CAPTURE_TETRAHEDRA_HASH = 788230395L
    private val lightmapGetProbeCaptureTetrahedraBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_get_probe_capture_tetrahedra", LIGHTMAP_GET_PROBE_CAPTURE_TETRAHEDRA_HASH)
    }

    private const val LIGHTMAP_GET_PROBE_CAPTURE_BSP_TREE_HASH = 788230395L
    private val lightmapGetProbeCaptureBspTreeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_get_probe_capture_bsp_tree", LIGHTMAP_GET_PROBE_CAPTURE_BSP_TREE_HASH)
    }

    private const val LIGHTMAP_SET_BAKED_EXPOSURE_NORMALIZATION_HASH = 1794382983L
    private val lightmapSetBakedExposureNormalizationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_set_baked_exposure_normalization", LIGHTMAP_SET_BAKED_EXPOSURE_NORMALIZATION_HASH)
    }

    private const val LIGHTMAP_SET_PROBE_CAPTURE_UPDATE_SPEED_HASH = 373806689L
    private val lightmapSetProbeCaptureUpdateSpeedBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "lightmap_set_probe_capture_update_speed", LIGHTMAP_SET_PROBE_CAPTURE_UPDATE_SPEED_HASH)
    }

    private const val PARTICLES_CREATE_HASH = 529393457L
    private val particlesCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_create", PARTICLES_CREATE_HASH)
    }

    private const val PARTICLES_SET_MODE_HASH = 3492270028L
    private val particlesSetModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_mode", PARTICLES_SET_MODE_HASH)
    }

    private const val PARTICLES_SET_EMITTING_HASH = 1265174801L
    private val particlesSetEmittingBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_emitting", PARTICLES_SET_EMITTING_HASH)
    }

    private const val PARTICLES_GET_EMITTING_HASH = 3521089500L
    private val particlesGetEmittingBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_get_emitting", PARTICLES_GET_EMITTING_HASH)
    }

    private const val PARTICLES_SET_AMOUNT_HASH = 3411492887L
    private val particlesSetAmountBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_amount", PARTICLES_SET_AMOUNT_HASH)
    }

    private const val PARTICLES_SET_AMOUNT_RATIO_HASH = 1794382983L
    private val particlesSetAmountRatioBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_amount_ratio", PARTICLES_SET_AMOUNT_RATIO_HASH)
    }

    private const val PARTICLES_SET_LIFETIME_HASH = 1794382983L
    private val particlesSetLifetimeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_lifetime", PARTICLES_SET_LIFETIME_HASH)
    }

    private const val PARTICLES_SET_ONE_SHOT_HASH = 1265174801L
    private val particlesSetOneShotBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_one_shot", PARTICLES_SET_ONE_SHOT_HASH)
    }

    private const val PARTICLES_SET_PRE_PROCESS_TIME_HASH = 1794382983L
    private val particlesSetPreProcessTimeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_pre_process_time", PARTICLES_SET_PRE_PROCESS_TIME_HASH)
    }

    private const val PARTICLES_REQUEST_PROCESS_TIME_HASH = 1515254041L
    private val particlesRequestProcessTimeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_request_process_time", PARTICLES_REQUEST_PROCESS_TIME_HASH)
    }

    private const val PARTICLES_SET_EXPLOSIVENESS_RATIO_HASH = 1794382983L
    private val particlesSetExplosivenessRatioBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_explosiveness_ratio", PARTICLES_SET_EXPLOSIVENESS_RATIO_HASH)
    }

    private const val PARTICLES_SET_RANDOMNESS_RATIO_HASH = 1794382983L
    private val particlesSetRandomnessRatioBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_randomness_ratio", PARTICLES_SET_RANDOMNESS_RATIO_HASH)
    }

    private const val PARTICLES_SET_INTERP_TO_END_HASH = 1794382983L
    private val particlesSetInterpToEndBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_interp_to_end", PARTICLES_SET_INTERP_TO_END_HASH)
    }

    private const val PARTICLES_SET_EMITTER_VELOCITY_HASH = 3227306858L
    private val particlesSetEmitterVelocityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_emitter_velocity", PARTICLES_SET_EMITTER_VELOCITY_HASH)
    }

    private const val PARTICLES_SET_CUSTOM_AABB_HASH = 3696536120L
    private val particlesSetCustomAabbBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_custom_aabb", PARTICLES_SET_CUSTOM_AABB_HASH)
    }

    private const val PARTICLES_SET_SPEED_SCALE_HASH = 1794382983L
    private val particlesSetSpeedScaleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_speed_scale", PARTICLES_SET_SPEED_SCALE_HASH)
    }

    private const val PARTICLES_SET_USE_LOCAL_COORDINATES_HASH = 1265174801L
    private val particlesSetUseLocalCoordinatesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_use_local_coordinates", PARTICLES_SET_USE_LOCAL_COORDINATES_HASH)
    }

    private const val PARTICLES_SET_PROCESS_MATERIAL_HASH = 395945892L
    private val particlesSetProcessMaterialBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_process_material", PARTICLES_SET_PROCESS_MATERIAL_HASH)
    }

    private const val PARTICLES_SET_FIXED_FPS_HASH = 3411492887L
    private val particlesSetFixedFpsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_fixed_fps", PARTICLES_SET_FIXED_FPS_HASH)
    }

    private const val PARTICLES_SET_INTERPOLATE_HASH = 1265174801L
    private val particlesSetInterpolateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_interpolate", PARTICLES_SET_INTERPOLATE_HASH)
    }

    private const val PARTICLES_SET_FRACTIONAL_DELTA_HASH = 1265174801L
    private val particlesSetFractionalDeltaBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_fractional_delta", PARTICLES_SET_FRACTIONAL_DELTA_HASH)
    }

    private const val PARTICLES_SET_COLLISION_BASE_SIZE_HASH = 1794382983L
    private val particlesSetCollisionBaseSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_collision_base_size", PARTICLES_SET_COLLISION_BASE_SIZE_HASH)
    }

    private const val PARTICLES_SET_TRANSFORM_ALIGN_HASH = 3264971368L
    private val particlesSetTransformAlignBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_transform_align", PARTICLES_SET_TRANSFORM_ALIGN_HASH)
    }

    private const val PARTICLES_SET_TRANSFORM_ALIGN_CHANNEL_FILTER_HASH = 1303285813L
    private val particlesSetTransformAlignChannelFilterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_transform_align_channel_filter", PARTICLES_SET_TRANSFORM_ALIGN_CHANNEL_FILTER_HASH)
    }

    private const val PARTICLES_SET_TRANSFORM_ALIGN_AXIS_HASH = 3065310065L
    private val particlesSetTransformAlignAxisBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_transform_align_axis", PARTICLES_SET_TRANSFORM_ALIGN_AXIS_HASH)
    }

    private const val PARTICLES_SET_TRAILS_HASH = 2010054925L
    private val particlesSetTrailsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_trails", PARTICLES_SET_TRAILS_HASH)
    }

    private const val PARTICLES_SET_TRAIL_BIND_POSES_HASH = 684822712L
    private val particlesSetTrailBindPosesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_trail_bind_poses", PARTICLES_SET_TRAIL_BIND_POSES_HASH)
    }

    private const val PARTICLES_IS_INACTIVE_HASH = 3521089500L
    private val particlesIsInactiveBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_is_inactive", PARTICLES_IS_INACTIVE_HASH)
    }

    private const val PARTICLES_REQUEST_PROCESS_HASH = 2722037293L
    private val particlesRequestProcessBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_request_process", PARTICLES_REQUEST_PROCESS_HASH)
    }

    private const val PARTICLES_RESTART_HASH = 2722037293L
    private val particlesRestartBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_restart", PARTICLES_RESTART_HASH)
    }

    private const val PARTICLES_SET_SUBEMITTER_HASH = 395945892L
    private val particlesSetSubemitterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_subemitter", PARTICLES_SET_SUBEMITTER_HASH)
    }

    private const val PARTICLES_EMIT_HASH = 4043136117L
    private val particlesEmitBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_emit", PARTICLES_EMIT_HASH)
    }

    private const val PARTICLES_SET_DRAW_ORDER_HASH = 935028487L
    private val particlesSetDrawOrderBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_draw_order", PARTICLES_SET_DRAW_ORDER_HASH)
    }

    private const val PARTICLES_SET_DRAW_PASSES_HASH = 3411492887L
    private val particlesSetDrawPassesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_draw_passes", PARTICLES_SET_DRAW_PASSES_HASH)
    }

    private const val PARTICLES_SET_DRAW_PASS_MESH_HASH = 2310537182L
    private val particlesSetDrawPassMeshBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_draw_pass_mesh", PARTICLES_SET_DRAW_PASS_MESH_HASH)
    }

    private const val PARTICLES_GET_CURRENT_AABB_HASH = 3952830260L
    private val particlesGetCurrentAabbBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_get_current_aabb", PARTICLES_GET_CURRENT_AABB_HASH)
    }

    private const val PARTICLES_SET_EMISSION_TRANSFORM_HASH = 3935195649L
    private val particlesSetEmissionTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_set_emission_transform", PARTICLES_SET_EMISSION_TRANSFORM_HASH)
    }

    private const val PARTICLES_COLLISION_CREATE_HASH = 529393457L
    private val particlesCollisionCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_create", PARTICLES_COLLISION_CREATE_HASH)
    }

    private const val PARTICLES_COLLISION_SET_COLLISION_TYPE_HASH = 1497044930L
    private val particlesCollisionSetCollisionTypeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_set_collision_type", PARTICLES_COLLISION_SET_COLLISION_TYPE_HASH)
    }

    private const val PARTICLES_COLLISION_SET_CULL_MASK_HASH = 3411492887L
    private val particlesCollisionSetCullMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_set_cull_mask", PARTICLES_COLLISION_SET_CULL_MASK_HASH)
    }

    private const val PARTICLES_COLLISION_SET_SPHERE_RADIUS_HASH = 1794382983L
    private val particlesCollisionSetSphereRadiusBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_set_sphere_radius", PARTICLES_COLLISION_SET_SPHERE_RADIUS_HASH)
    }

    private const val PARTICLES_COLLISION_SET_BOX_EXTENTS_HASH = 3227306858L
    private val particlesCollisionSetBoxExtentsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_set_box_extents", PARTICLES_COLLISION_SET_BOX_EXTENTS_HASH)
    }

    private const val PARTICLES_COLLISION_SET_ATTRACTOR_STRENGTH_HASH = 1794382983L
    private val particlesCollisionSetAttractorStrengthBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_set_attractor_strength", PARTICLES_COLLISION_SET_ATTRACTOR_STRENGTH_HASH)
    }

    private const val PARTICLES_COLLISION_SET_ATTRACTOR_DIRECTIONALITY_HASH = 1794382983L
    private val particlesCollisionSetAttractorDirectionalityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_set_attractor_directionality", PARTICLES_COLLISION_SET_ATTRACTOR_DIRECTIONALITY_HASH)
    }

    private const val PARTICLES_COLLISION_SET_ATTRACTOR_ATTENUATION_HASH = 1794382983L
    private val particlesCollisionSetAttractorAttenuationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_set_attractor_attenuation", PARTICLES_COLLISION_SET_ATTRACTOR_ATTENUATION_HASH)
    }

    private const val PARTICLES_COLLISION_SET_FIELD_TEXTURE_HASH = 395945892L
    private val particlesCollisionSetFieldTextureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_set_field_texture", PARTICLES_COLLISION_SET_FIELD_TEXTURE_HASH)
    }

    private const val PARTICLES_COLLISION_HEIGHT_FIELD_UPDATE_HASH = 2722037293L
    private val particlesCollisionHeightFieldUpdateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_height_field_update", PARTICLES_COLLISION_HEIGHT_FIELD_UPDATE_HASH)
    }

    private const val PARTICLES_COLLISION_SET_HEIGHT_FIELD_RESOLUTION_HASH = 962977297L
    private val particlesCollisionSetHeightFieldResolutionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_set_height_field_resolution", PARTICLES_COLLISION_SET_HEIGHT_FIELD_RESOLUTION_HASH)
    }

    private const val PARTICLES_COLLISION_SET_HEIGHT_FIELD_MASK_HASH = 3411492887L
    private val particlesCollisionSetHeightFieldMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "particles_collision_set_height_field_mask", PARTICLES_COLLISION_SET_HEIGHT_FIELD_MASK_HASH)
    }

    private const val FOG_VOLUME_CREATE_HASH = 529393457L
    private val fogVolumeCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "fog_volume_create", FOG_VOLUME_CREATE_HASH)
    }

    private const val FOG_VOLUME_SET_SHAPE_HASH = 3818703106L
    private val fogVolumeSetShapeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "fog_volume_set_shape", FOG_VOLUME_SET_SHAPE_HASH)
    }

    private const val FOG_VOLUME_SET_SIZE_HASH = 3227306858L
    private val fogVolumeSetSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "fog_volume_set_size", FOG_VOLUME_SET_SIZE_HASH)
    }

    private const val FOG_VOLUME_SET_MATERIAL_HASH = 395945892L
    private val fogVolumeSetMaterialBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "fog_volume_set_material", FOG_VOLUME_SET_MATERIAL_HASH)
    }

    private const val VISIBILITY_NOTIFIER_CREATE_HASH = 529393457L
    private val visibilityNotifierCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "visibility_notifier_create", VISIBILITY_NOTIFIER_CREATE_HASH)
    }

    private const val VISIBILITY_NOTIFIER_SET_AABB_HASH = 3696536120L
    private val visibilityNotifierSetAabbBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "visibility_notifier_set_aabb", VISIBILITY_NOTIFIER_SET_AABB_HASH)
    }

    private const val VISIBILITY_NOTIFIER_SET_CALLBACKS_HASH = 2689735388L
    private val visibilityNotifierSetCallbacksBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "visibility_notifier_set_callbacks", VISIBILITY_NOTIFIER_SET_CALLBACKS_HASH)
    }

    private const val OCCLUDER_CREATE_HASH = 529393457L
    private val occluderCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "occluder_create", OCCLUDER_CREATE_HASH)
    }

    private const val OCCLUDER_SET_MESH_HASH = 3854404263L
    private val occluderSetMeshBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "occluder_set_mesh", OCCLUDER_SET_MESH_HASH)
    }

    private const val CAMERA_CREATE_HASH = 529393457L
    private val cameraCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_create", CAMERA_CREATE_HASH)
    }

    private const val CAMERA_SET_PERSPECTIVE_HASH = 157498339L
    private val cameraSetPerspectiveBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_set_perspective", CAMERA_SET_PERSPECTIVE_HASH)
    }

    private const val CAMERA_SET_ORTHOGONAL_HASH = 157498339L
    private val cameraSetOrthogonalBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_set_orthogonal", CAMERA_SET_ORTHOGONAL_HASH)
    }

    private const val CAMERA_SET_FRUSTUM_HASH = 1889878953L
    private val cameraSetFrustumBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_set_frustum", CAMERA_SET_FRUSTUM_HASH)
    }

    private const val CAMERA_SET_TRANSFORM_HASH = 3935195649L
    private val cameraSetTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_set_transform", CAMERA_SET_TRANSFORM_HASH)
    }

    private const val CAMERA_SET_CULL_MASK_HASH = 3411492887L
    private val cameraSetCullMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_set_cull_mask", CAMERA_SET_CULL_MASK_HASH)
    }

    private const val CAMERA_SET_ENVIRONMENT_HASH = 395945892L
    private val cameraSetEnvironmentBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_set_environment", CAMERA_SET_ENVIRONMENT_HASH)
    }

    private const val CAMERA_SET_CAMERA_ATTRIBUTES_HASH = 395945892L
    private val cameraSetCameraAttributesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_set_camera_attributes", CAMERA_SET_CAMERA_ATTRIBUTES_HASH)
    }

    private const val CAMERA_SET_COMPOSITOR_HASH = 395945892L
    private val cameraSetCompositorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_set_compositor", CAMERA_SET_COMPOSITOR_HASH)
    }

    private const val CAMERA_SET_USE_VERTICAL_ASPECT_HASH = 1265174801L
    private val cameraSetUseVerticalAspectBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_set_use_vertical_aspect", CAMERA_SET_USE_VERTICAL_ASPECT_HASH)
    }

    private const val VIEWPORT_CREATE_HASH = 529393457L
    private val viewportCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_create", VIEWPORT_CREATE_HASH)
    }

    private const val VIEWPORT_SET_USE_XR_HASH = 1265174801L
    private val viewportSetUseXrBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_use_xr", VIEWPORT_SET_USE_XR_HASH)
    }

    private const val VIEWPORT_SET_SIZE_HASH = 3313592705L
    private val viewportSetSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_size", VIEWPORT_SET_SIZE_HASH)
    }

    private const val VIEWPORT_SET_ACTIVE_HASH = 1265174801L
    private val viewportSetActiveBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_active", VIEWPORT_SET_ACTIVE_HASH)
    }

    private const val VIEWPORT_SET_PARENT_VIEWPORT_HASH = 395945892L
    private val viewportSetParentViewportBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_parent_viewport", VIEWPORT_SET_PARENT_VIEWPORT_HASH)
    }

    private const val VIEWPORT_ATTACH_TO_SCREEN_HASH = 1062245816L
    private val viewportAttachToScreenBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_attach_to_screen", VIEWPORT_ATTACH_TO_SCREEN_HASH)
    }

    private const val VIEWPORT_SET_RENDER_DIRECT_TO_SCREEN_HASH = 1265174801L
    private val viewportSetRenderDirectToScreenBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_render_direct_to_screen", VIEWPORT_SET_RENDER_DIRECT_TO_SCREEN_HASH)
    }

    private const val VIEWPORT_SET_CANVAS_CULL_MASK_HASH = 3411492887L
    private val viewportSetCanvasCullMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_canvas_cull_mask", VIEWPORT_SET_CANVAS_CULL_MASK_HASH)
    }

    private const val VIEWPORT_SET_SCALING_3D_MODE_HASH = 2386524376L
    private val viewportSetScaling3dModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_scaling_3d_mode", VIEWPORT_SET_SCALING_3D_MODE_HASH)
    }

    private const val VIEWPORT_SET_SCALING_3D_SCALE_HASH = 1794382983L
    private val viewportSetScaling3dScaleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_scaling_3d_scale", VIEWPORT_SET_SCALING_3D_SCALE_HASH)
    }

    private const val VIEWPORT_SET_FSR_SHARPNESS_HASH = 1794382983L
    private val viewportSetFsrSharpnessBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_fsr_sharpness", VIEWPORT_SET_FSR_SHARPNESS_HASH)
    }

    private const val VIEWPORT_SET_TEXTURE_MIPMAP_BIAS_HASH = 1794382983L
    private val viewportSetTextureMipmapBiasBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_texture_mipmap_bias", VIEWPORT_SET_TEXTURE_MIPMAP_BIAS_HASH)
    }

    private const val VIEWPORT_SET_ANISOTROPIC_FILTERING_LEVEL_HASH = 3953214029L
    private val viewportSetAnisotropicFilteringLevelBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_anisotropic_filtering_level", VIEWPORT_SET_ANISOTROPIC_FILTERING_LEVEL_HASH)
    }

    private const val VIEWPORT_SET_UPDATE_MODE_HASH = 3161116010L
    private val viewportSetUpdateModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_update_mode", VIEWPORT_SET_UPDATE_MODE_HASH)
    }

    private const val VIEWPORT_GET_UPDATE_MODE_HASH = 3803901472L
    private val viewportGetUpdateModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_get_update_mode", VIEWPORT_GET_UPDATE_MODE_HASH)
    }

    private const val VIEWPORT_SET_CLEAR_MODE_HASH = 3628367896L
    private val viewportSetClearModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_clear_mode", VIEWPORT_SET_CLEAR_MODE_HASH)
    }

    private const val VIEWPORT_GET_RENDER_TARGET_HASH = 3814569979L
    private val viewportGetRenderTargetBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_get_render_target", VIEWPORT_GET_RENDER_TARGET_HASH)
    }

    private const val VIEWPORT_GET_TEXTURE_HASH = 3814569979L
    private val viewportGetTextureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_get_texture", VIEWPORT_GET_TEXTURE_HASH)
    }

    private const val VIEWPORT_SET_DISABLE_3D_HASH = 1265174801L
    private val viewportSetDisable3dBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_disable_3d", VIEWPORT_SET_DISABLE_3D_HASH)
    }

    private const val VIEWPORT_SET_DISABLE_2D_HASH = 1265174801L
    private val viewportSetDisable2dBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_disable_2d", VIEWPORT_SET_DISABLE_2D_HASH)
    }

    private const val VIEWPORT_SET_ENVIRONMENT_MODE_HASH = 2196892182L
    private val viewportSetEnvironmentModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_environment_mode", VIEWPORT_SET_ENVIRONMENT_MODE_HASH)
    }

    private const val VIEWPORT_ATTACH_CAMERA_HASH = 395945892L
    private val viewportAttachCameraBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_attach_camera", VIEWPORT_ATTACH_CAMERA_HASH)
    }

    private const val VIEWPORT_SET_SCENARIO_HASH = 395945892L
    private val viewportSetScenarioBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_scenario", VIEWPORT_SET_SCENARIO_HASH)
    }

    private const val VIEWPORT_ATTACH_CANVAS_HASH = 395945892L
    private val viewportAttachCanvasBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_attach_canvas", VIEWPORT_ATTACH_CANVAS_HASH)
    }

    private const val VIEWPORT_REMOVE_CANVAS_HASH = 395945892L
    private val viewportRemoveCanvasBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_remove_canvas", VIEWPORT_REMOVE_CANVAS_HASH)
    }

    private const val VIEWPORT_SET_SNAP_2D_TRANSFORMS_TO_PIXEL_HASH = 1265174801L
    private val viewportSetSnap2dTransformsToPixelBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_snap_2d_transforms_to_pixel", VIEWPORT_SET_SNAP_2D_TRANSFORMS_TO_PIXEL_HASH)
    }

    private const val VIEWPORT_SET_SNAP_2D_VERTICES_TO_PIXEL_HASH = 1265174801L
    private val viewportSetSnap2dVerticesToPixelBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_snap_2d_vertices_to_pixel", VIEWPORT_SET_SNAP_2D_VERTICES_TO_PIXEL_HASH)
    }

    private const val VIEWPORT_SET_DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_HASH = 1155129294L
    private val viewportSetDefaultCanvasItemTextureFilterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_default_canvas_item_texture_filter", VIEWPORT_SET_DEFAULT_CANVAS_ITEM_TEXTURE_FILTER_HASH)
    }

    private const val VIEWPORT_SET_DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_HASH = 1652956681L
    private val viewportSetDefaultCanvasItemTextureRepeatBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_default_canvas_item_texture_repeat", VIEWPORT_SET_DEFAULT_CANVAS_ITEM_TEXTURE_REPEAT_HASH)
    }

    private const val VIEWPORT_SET_CANVAS_TRANSFORM_HASH = 3608606053L
    private val viewportSetCanvasTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_canvas_transform", VIEWPORT_SET_CANVAS_TRANSFORM_HASH)
    }

    private const val VIEWPORT_SET_CANVAS_STACKING_HASH = 3713930247L
    private val viewportSetCanvasStackingBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_canvas_stacking", VIEWPORT_SET_CANVAS_STACKING_HASH)
    }

    private const val VIEWPORT_SET_TRANSPARENT_BACKGROUND_HASH = 1265174801L
    private val viewportSetTransparentBackgroundBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_transparent_background", VIEWPORT_SET_TRANSPARENT_BACKGROUND_HASH)
    }

    private const val VIEWPORT_SET_GLOBAL_CANVAS_TRANSFORM_HASH = 1246044741L
    private val viewportSetGlobalCanvasTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_global_canvas_transform", VIEWPORT_SET_GLOBAL_CANVAS_TRANSFORM_HASH)
    }

    private const val VIEWPORT_SET_SDF_OVERSIZE_AND_SCALE_HASH = 1329198632L
    private val viewportSetSdfOversizeAndScaleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_sdf_oversize_and_scale", VIEWPORT_SET_SDF_OVERSIZE_AND_SCALE_HASH)
    }

    private const val VIEWPORT_SET_POSITIONAL_SHADOW_ATLAS_SIZE_HASH = 1904426712L
    private val viewportSetPositionalShadowAtlasSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_positional_shadow_atlas_size", VIEWPORT_SET_POSITIONAL_SHADOW_ATLAS_SIZE_HASH)
    }

    private const val VIEWPORT_SET_POSITIONAL_SHADOW_ATLAS_QUADRANT_SUBDIVISION_HASH = 4288446313L
    private val viewportSetPositionalShadowAtlasQuadrantSubdivisionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_positional_shadow_atlas_quadrant_subdivision", VIEWPORT_SET_POSITIONAL_SHADOW_ATLAS_QUADRANT_SUBDIVISION_HASH)
    }

    private const val VIEWPORT_SET_MSAA_3D_HASH = 3764433340L
    private val viewportSetMsaa3dBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_msaa_3d", VIEWPORT_SET_MSAA_3D_HASH)
    }

    private const val VIEWPORT_SET_MSAA_2D_HASH = 3764433340L
    private val viewportSetMsaa2dBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_msaa_2d", VIEWPORT_SET_MSAA_2D_HASH)
    }

    private const val VIEWPORT_SET_USE_HDR_2D_HASH = 1265174801L
    private val viewportSetUseHdr2dBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_use_hdr_2d", VIEWPORT_SET_USE_HDR_2D_HASH)
    }

    private const val VIEWPORT_SET_SCREEN_SPACE_AA_HASH = 1447279591L
    private val viewportSetScreenSpaceAaBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_screen_space_aa", VIEWPORT_SET_SCREEN_SPACE_AA_HASH)
    }

    private const val VIEWPORT_SET_USE_TAA_HASH = 1265174801L
    private val viewportSetUseTaaBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_use_taa", VIEWPORT_SET_USE_TAA_HASH)
    }

    private const val VIEWPORT_SET_USE_DEBANDING_HASH = 1265174801L
    private val viewportSetUseDebandingBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_use_debanding", VIEWPORT_SET_USE_DEBANDING_HASH)
    }

    private const val VIEWPORT_SET_USE_OCCLUSION_CULLING_HASH = 1265174801L
    private val viewportSetUseOcclusionCullingBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_use_occlusion_culling", VIEWPORT_SET_USE_OCCLUSION_CULLING_HASH)
    }

    private const val VIEWPORT_SET_OCCLUSION_RAYS_PER_THREAD_HASH = 1286410249L
    private val viewportSetOcclusionRaysPerThreadBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_occlusion_rays_per_thread", VIEWPORT_SET_OCCLUSION_RAYS_PER_THREAD_HASH)
    }

    private const val VIEWPORT_SET_OCCLUSION_CULLING_BUILD_QUALITY_HASH = 2069725696L
    private val viewportSetOcclusionCullingBuildQualityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_occlusion_culling_build_quality", VIEWPORT_SET_OCCLUSION_CULLING_BUILD_QUALITY_HASH)
    }

    private const val VIEWPORT_GET_RENDER_INFO_HASH = 2041262392L
    private val viewportGetRenderInfoBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_get_render_info", VIEWPORT_GET_RENDER_INFO_HASH)
    }

    private const val VIEWPORT_SET_DEBUG_DRAW_HASH = 2089420930L
    private val viewportSetDebugDrawBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_debug_draw", VIEWPORT_SET_DEBUG_DRAW_HASH)
    }

    private const val VIEWPORT_SET_MEASURE_RENDER_TIME_HASH = 1265174801L
    private val viewportSetMeasureRenderTimeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_measure_render_time", VIEWPORT_SET_MEASURE_RENDER_TIME_HASH)
    }

    private const val VIEWPORT_GET_MEASURED_RENDER_TIME_CPU_HASH = 866169185L
    private val viewportGetMeasuredRenderTimeCpuBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_get_measured_render_time_cpu", VIEWPORT_GET_MEASURED_RENDER_TIME_CPU_HASH)
    }

    private const val VIEWPORT_GET_MEASURED_RENDER_TIME_GPU_HASH = 866169185L
    private val viewportGetMeasuredRenderTimeGpuBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_get_measured_render_time_gpu", VIEWPORT_GET_MEASURED_RENDER_TIME_GPU_HASH)
    }

    private const val VIEWPORT_SET_VRS_MODE_HASH = 398809874L
    private val viewportSetVrsModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_vrs_mode", VIEWPORT_SET_VRS_MODE_HASH)
    }

    private const val VIEWPORT_SET_VRS_UPDATE_MODE_HASH = 2696154815L
    private val viewportSetVrsUpdateModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_vrs_update_mode", VIEWPORT_SET_VRS_UPDATE_MODE_HASH)
    }

    private const val VIEWPORT_SET_VRS_TEXTURE_HASH = 395945892L
    private val viewportSetVrsTextureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "viewport_set_vrs_texture", VIEWPORT_SET_VRS_TEXTURE_HASH)
    }

    private const val SKY_CREATE_HASH = 529393457L
    private val skyCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "sky_create", SKY_CREATE_HASH)
    }

    private const val SKY_SET_RADIANCE_SIZE_HASH = 3411492887L
    private val skySetRadianceSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "sky_set_radiance_size", SKY_SET_RADIANCE_SIZE_HASH)
    }

    private const val SKY_SET_MODE_HASH = 3279019937L
    private val skySetModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "sky_set_mode", SKY_SET_MODE_HASH)
    }

    private const val SKY_SET_MATERIAL_HASH = 395945892L
    private val skySetMaterialBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "sky_set_material", SKY_SET_MATERIAL_HASH)
    }

    private const val SKY_BAKE_PANORAMA_HASH = 3875285818L
    private val skyBakePanoramaBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "sky_bake_panorama", SKY_BAKE_PANORAMA_HASH)
    }

    private const val COMPOSITOR_EFFECT_CREATE_HASH = 529393457L
    private val compositorEffectCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "compositor_effect_create", COMPOSITOR_EFFECT_CREATE_HASH)
    }

    private const val COMPOSITOR_EFFECT_SET_ENABLED_HASH = 1265174801L
    private val compositorEffectSetEnabledBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "compositor_effect_set_enabled", COMPOSITOR_EFFECT_SET_ENABLED_HASH)
    }

    private const val COMPOSITOR_EFFECT_SET_CALLBACK_HASH = 487412485L
    private val compositorEffectSetCallbackBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "compositor_effect_set_callback", COMPOSITOR_EFFECT_SET_CALLBACK_HASH)
    }

    private const val COMPOSITOR_EFFECT_SET_FLAG_HASH = 3659527075L
    private val compositorEffectSetFlagBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "compositor_effect_set_flag", COMPOSITOR_EFFECT_SET_FLAG_HASH)
    }

    private const val COMPOSITOR_CREATE_HASH = 529393457L
    private val compositorCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "compositor_create", COMPOSITOR_CREATE_HASH)
    }

    private const val COMPOSITOR_SET_COMPOSITOR_EFFECTS_HASH = 684822712L
    private val compositorSetCompositorEffectsBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "compositor_set_compositor_effects", COMPOSITOR_SET_COMPOSITOR_EFFECTS_HASH)
    }

    private const val ENVIRONMENT_CREATE_HASH = 529393457L
    private val environmentCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_create", ENVIRONMENT_CREATE_HASH)
    }

    private const val ENVIRONMENT_SET_BACKGROUND_HASH = 3937328877L
    private val environmentSetBackgroundBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_background", ENVIRONMENT_SET_BACKGROUND_HASH)
    }

    private const val ENVIRONMENT_SET_CAMERA_ID_HASH = 3411492887L
    private val environmentSetCameraIdBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_camera_id", ENVIRONMENT_SET_CAMERA_ID_HASH)
    }

    private const val ENVIRONMENT_SET_SKY_HASH = 395945892L
    private val environmentSetSkyBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_sky", ENVIRONMENT_SET_SKY_HASH)
    }

    private const val ENVIRONMENT_SET_SKY_CUSTOM_FOV_HASH = 1794382983L
    private val environmentSetSkyCustomFovBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_sky_custom_fov", ENVIRONMENT_SET_SKY_CUSTOM_FOV_HASH)
    }

    private const val ENVIRONMENT_SET_SKY_ORIENTATION_HASH = 1735850857L
    private val environmentSetSkyOrientationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_sky_orientation", ENVIRONMENT_SET_SKY_ORIENTATION_HASH)
    }

    private const val ENVIRONMENT_SET_BG_COLOR_HASH = 2948539648L
    private val environmentSetBgColorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_bg_color", ENVIRONMENT_SET_BG_COLOR_HASH)
    }

    private const val ENVIRONMENT_SET_BG_ENERGY_HASH = 2513314492L
    private val environmentSetBgEnergyBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_bg_energy", ENVIRONMENT_SET_BG_ENERGY_HASH)
    }

    private const val ENVIRONMENT_SET_CANVAS_MAX_LAYER_HASH = 3411492887L
    private val environmentSetCanvasMaxLayerBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_canvas_max_layer", ENVIRONMENT_SET_CANVAS_MAX_LAYER_HASH)
    }

    private const val ENVIRONMENT_SET_AMBIENT_LIGHT_HASH = 1214961493L
    private val environmentSetAmbientLightBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_ambient_light", ENVIRONMENT_SET_AMBIENT_LIGHT_HASH)
    }

    private const val ENVIRONMENT_SET_GLOW_HASH = 2421724940L
    private val environmentSetGlowBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_glow", ENVIRONMENT_SET_GLOW_HASH)
    }

    private const val ENVIRONMENT_SET_TONEMAP_HASH = 2914312638L
    private val environmentSetTonemapBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_tonemap", ENVIRONMENT_SET_TONEMAP_HASH)
    }

    private const val ENVIRONMENT_SET_TONEMAP_AGX_CONTRAST_HASH = 1794382983L
    private val environmentSetTonemapAgxContrastBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_tonemap_agx_contrast", ENVIRONMENT_SET_TONEMAP_AGX_CONTRAST_HASH)
    }

    private const val ENVIRONMENT_SET_ADJUSTMENT_HASH = 876799838L
    private val environmentSetAdjustmentBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_adjustment", ENVIRONMENT_SET_ADJUSTMENT_HASH)
    }

    private const val ENVIRONMENT_SET_SSR_HASH = 3607294374L
    private val environmentSetSsrBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_ssr", ENVIRONMENT_SET_SSR_HASH)
    }

    private const val ENVIRONMENT_SET_SSAO_HASH = 3994732740L
    private val environmentSetSsaoBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_ssao", ENVIRONMENT_SET_SSAO_HASH)
    }

    private const val ENVIRONMENT_SET_FOG_HASH = 105051629L
    private val environmentSetFogBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_fog", ENVIRONMENT_SET_FOG_HASH)
    }

    private const val ENVIRONMENT_SET_FOG_DEPTH_HASH = 157498339L
    private val environmentSetFogDepthBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_fog_depth", ENVIRONMENT_SET_FOG_DEPTH_HASH)
    }

    private const val ENVIRONMENT_SET_SDFGI_HASH = 3519144388L
    private val environmentSetSdfgiBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_sdfgi", ENVIRONMENT_SET_SDFGI_HASH)
    }

    private const val ENVIRONMENT_SET_VOLUMETRIC_FOG_HASH = 1553633833L
    private val environmentSetVolumetricFogBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_volumetric_fog", ENVIRONMENT_SET_VOLUMETRIC_FOG_HASH)
    }

    private const val ENVIRONMENT_GLOW_SET_USE_BICUBIC_UPSCALE_HASH = 2586408642L
    private val environmentGlowSetUseBicubicUpscaleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_glow_set_use_bicubic_upscale", ENVIRONMENT_GLOW_SET_USE_BICUBIC_UPSCALE_HASH)
    }

    private const val ENVIRONMENT_SET_SSR_HALF_SIZE_HASH = 2586408642L
    private val environmentSetSsrHalfSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_ssr_half_size", ENVIRONMENT_SET_SSR_HALF_SIZE_HASH)
    }

    private const val ENVIRONMENT_SET_SSR_ROUGHNESS_QUALITY_HASH = 1190026788L
    private val environmentSetSsrRoughnessQualityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_ssr_roughness_quality", ENVIRONMENT_SET_SSR_ROUGHNESS_QUALITY_HASH)
    }

    private const val ENVIRONMENT_SET_SSAO_QUALITY_HASH = 189753569L
    private val environmentSetSsaoQualityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_ssao_quality", ENVIRONMENT_SET_SSAO_QUALITY_HASH)
    }

    private const val ENVIRONMENT_SET_SSIL_QUALITY_HASH = 1713836683L
    private val environmentSetSsilQualityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_ssil_quality", ENVIRONMENT_SET_SSIL_QUALITY_HASH)
    }

    private const val ENVIRONMENT_SET_SDFGI_RAY_COUNT_HASH = 340137951L
    private val environmentSetSdfgiRayCountBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_sdfgi_ray_count", ENVIRONMENT_SET_SDFGI_RAY_COUNT_HASH)
    }

    private const val ENVIRONMENT_SET_SDFGI_FRAMES_TO_CONVERGE_HASH = 2182444374L
    private val environmentSetSdfgiFramesToConvergeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_sdfgi_frames_to_converge", ENVIRONMENT_SET_SDFGI_FRAMES_TO_CONVERGE_HASH)
    }

    private const val ENVIRONMENT_SET_SDFGI_FRAMES_TO_UPDATE_LIGHT_HASH = 1251144068L
    private val environmentSetSdfgiFramesToUpdateLightBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_sdfgi_frames_to_update_light", ENVIRONMENT_SET_SDFGI_FRAMES_TO_UPDATE_LIGHT_HASH)
    }

    private const val ENVIRONMENT_SET_VOLUMETRIC_FOG_VOLUME_SIZE_HASH = 3937882851L
    private val environmentSetVolumetricFogVolumeSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_volumetric_fog_volume_size", ENVIRONMENT_SET_VOLUMETRIC_FOG_VOLUME_SIZE_HASH)
    }

    private const val ENVIRONMENT_SET_VOLUMETRIC_FOG_FILTER_ACTIVE_HASH = 2586408642L
    private val environmentSetVolumetricFogFilterActiveBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_set_volumetric_fog_filter_active", ENVIRONMENT_SET_VOLUMETRIC_FOG_FILTER_ACTIVE_HASH)
    }

    private const val ENVIRONMENT_BAKE_PANORAMA_HASH = 2452908646L
    private val environmentBakePanoramaBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "environment_bake_panorama", ENVIRONMENT_BAKE_PANORAMA_HASH)
    }

    private const val SCREEN_SPACE_ROUGHNESS_LIMITER_SET_ACTIVE_HASH = 916716790L
    private val screenSpaceRoughnessLimiterSetActiveBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "screen_space_roughness_limiter_set_active", SCREEN_SPACE_ROUGHNESS_LIMITER_SET_ACTIVE_HASH)
    }

    private const val SUB_SURFACE_SCATTERING_SET_QUALITY_HASH = 64571803L
    private val subSurfaceScatteringSetQualityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "sub_surface_scattering_set_quality", SUB_SURFACE_SCATTERING_SET_QUALITY_HASH)
    }

    private const val SUB_SURFACE_SCATTERING_SET_SCALE_HASH = 1017552074L
    private val subSurfaceScatteringSetScaleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "sub_surface_scattering_set_scale", SUB_SURFACE_SCATTERING_SET_SCALE_HASH)
    }

    private const val CAMERA_ATTRIBUTES_CREATE_HASH = 529393457L
    private val cameraAttributesCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_attributes_create", CAMERA_ATTRIBUTES_CREATE_HASH)
    }

    private const val CAMERA_ATTRIBUTES_SET_DOF_BLUR_QUALITY_HASH = 2220136795L
    private val cameraAttributesSetDofBlurQualityBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_attributes_set_dof_blur_quality", CAMERA_ATTRIBUTES_SET_DOF_BLUR_QUALITY_HASH)
    }

    private const val CAMERA_ATTRIBUTES_SET_DOF_BLUR_BOKEH_SHAPE_HASH = 1205058394L
    private val cameraAttributesSetDofBlurBokehShapeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_attributes_set_dof_blur_bokeh_shape", CAMERA_ATTRIBUTES_SET_DOF_BLUR_BOKEH_SHAPE_HASH)
    }

    private const val CAMERA_ATTRIBUTES_SET_DOF_BLUR_HASH = 316272616L
    private val cameraAttributesSetDofBlurBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_attributes_set_dof_blur", CAMERA_ATTRIBUTES_SET_DOF_BLUR_HASH)
    }

    private const val CAMERA_ATTRIBUTES_SET_EXPOSURE_HASH = 2513314492L
    private val cameraAttributesSetExposureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_attributes_set_exposure", CAMERA_ATTRIBUTES_SET_EXPOSURE_HASH)
    }

    private const val CAMERA_ATTRIBUTES_SET_AUTO_EXPOSURE_HASH = 4266986332L
    private val cameraAttributesSetAutoExposureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "camera_attributes_set_auto_exposure", CAMERA_ATTRIBUTES_SET_AUTO_EXPOSURE_HASH)
    }

    private const val SCENARIO_CREATE_HASH = 529393457L
    private val scenarioCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "scenario_create", SCENARIO_CREATE_HASH)
    }

    private const val SCENARIO_SET_ENVIRONMENT_HASH = 395945892L
    private val scenarioSetEnvironmentBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "scenario_set_environment", SCENARIO_SET_ENVIRONMENT_HASH)
    }

    private const val SCENARIO_SET_FALLBACK_ENVIRONMENT_HASH = 395945892L
    private val scenarioSetFallbackEnvironmentBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "scenario_set_fallback_environment", SCENARIO_SET_FALLBACK_ENVIRONMENT_HASH)
    }

    private const val SCENARIO_SET_CAMERA_ATTRIBUTES_HASH = 395945892L
    private val scenarioSetCameraAttributesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "scenario_set_camera_attributes", SCENARIO_SET_CAMERA_ATTRIBUTES_HASH)
    }

    private const val SCENARIO_SET_COMPOSITOR_HASH = 395945892L
    private val scenarioSetCompositorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "scenario_set_compositor", SCENARIO_SET_COMPOSITOR_HASH)
    }

    private const val INSTANCE_CREATE2_HASH = 746547085L
    private val instanceCreate2Bind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_create2", INSTANCE_CREATE2_HASH)
    }

    private const val INSTANCE_CREATE_HASH = 529393457L
    private val instanceCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_create", INSTANCE_CREATE_HASH)
    }

    private const val INSTANCE_SET_BASE_HASH = 395945892L
    private val instanceSetBaseBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_base", INSTANCE_SET_BASE_HASH)
    }

    private const val INSTANCE_SET_SCENARIO_HASH = 395945892L
    private val instanceSetScenarioBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_scenario", INSTANCE_SET_SCENARIO_HASH)
    }

    private const val INSTANCE_SET_LAYER_MASK_HASH = 3411492887L
    private val instanceSetLayerMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_layer_mask", INSTANCE_SET_LAYER_MASK_HASH)
    }

    private const val INSTANCE_SET_PIVOT_DATA_HASH = 1280615259L
    private val instanceSetPivotDataBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_pivot_data", INSTANCE_SET_PIVOT_DATA_HASH)
    }

    private const val INSTANCE_SET_TRANSFORM_HASH = 3935195649L
    private val instanceSetTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_transform", INSTANCE_SET_TRANSFORM_HASH)
    }

    private const val INSTANCE_ATTACH_OBJECT_INSTANCE_ID_HASH = 3411492887L
    private val instanceAttachObjectInstanceIdBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_attach_object_instance_id", INSTANCE_ATTACH_OBJECT_INSTANCE_ID_HASH)
    }

    private const val INSTANCE_SET_BLEND_SHAPE_WEIGHT_HASH = 1892459533L
    private val instanceSetBlendShapeWeightBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_blend_shape_weight", INSTANCE_SET_BLEND_SHAPE_WEIGHT_HASH)
    }

    private const val INSTANCE_SET_SURFACE_OVERRIDE_MATERIAL_HASH = 2310537182L
    private val instanceSetSurfaceOverrideMaterialBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_surface_override_material", INSTANCE_SET_SURFACE_OVERRIDE_MATERIAL_HASH)
    }

    private const val INSTANCE_SET_VISIBLE_HASH = 1265174801L
    private val instanceSetVisibleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_visible", INSTANCE_SET_VISIBLE_HASH)
    }

    private const val INSTANCE_GEOMETRY_SET_TRANSPARENCY_HASH = 1794382983L
    private val instanceGeometrySetTransparencyBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_set_transparency", INSTANCE_GEOMETRY_SET_TRANSPARENCY_HASH)
    }

    private const val INSTANCE_TELEPORT_HASH = 2722037293L
    private val instanceTeleportBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_teleport", INSTANCE_TELEPORT_HASH)
    }

    private const val INSTANCE_SET_CUSTOM_AABB_HASH = 3696536120L
    private val instanceSetCustomAabbBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_custom_aabb", INSTANCE_SET_CUSTOM_AABB_HASH)
    }

    private const val INSTANCE_ATTACH_SKELETON_HASH = 395945892L
    private val instanceAttachSkeletonBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_attach_skeleton", INSTANCE_ATTACH_SKELETON_HASH)
    }

    private const val INSTANCE_SET_EXTRA_VISIBILITY_MARGIN_HASH = 1794382983L
    private val instanceSetExtraVisibilityMarginBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_extra_visibility_margin", INSTANCE_SET_EXTRA_VISIBILITY_MARGIN_HASH)
    }

    private const val INSTANCE_SET_VISIBILITY_PARENT_HASH = 395945892L
    private val instanceSetVisibilityParentBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_visibility_parent", INSTANCE_SET_VISIBILITY_PARENT_HASH)
    }

    private const val INSTANCE_SET_IGNORE_CULLING_HASH = 1265174801L
    private val instanceSetIgnoreCullingBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_set_ignore_culling", INSTANCE_SET_IGNORE_CULLING_HASH)
    }

    private const val INSTANCE_GEOMETRY_SET_FLAG_HASH = 1014989537L
    private val instanceGeometrySetFlagBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_set_flag", INSTANCE_GEOMETRY_SET_FLAG_HASH)
    }

    private const val INSTANCE_GEOMETRY_SET_CAST_SHADOWS_SETTING_HASH = 3768836020L
    private val instanceGeometrySetCastShadowsSettingBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_set_cast_shadows_setting", INSTANCE_GEOMETRY_SET_CAST_SHADOWS_SETTING_HASH)
    }

    private const val INSTANCE_GEOMETRY_SET_MATERIAL_OVERRIDE_HASH = 395945892L
    private val instanceGeometrySetMaterialOverrideBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_set_material_override", INSTANCE_GEOMETRY_SET_MATERIAL_OVERRIDE_HASH)
    }

    private const val INSTANCE_GEOMETRY_SET_MATERIAL_OVERLAY_HASH = 395945892L
    private val instanceGeometrySetMaterialOverlayBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_set_material_overlay", INSTANCE_GEOMETRY_SET_MATERIAL_OVERLAY_HASH)
    }

    private const val INSTANCE_GEOMETRY_SET_VISIBILITY_RANGE_HASH = 4263925858L
    private val instanceGeometrySetVisibilityRangeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_set_visibility_range", INSTANCE_GEOMETRY_SET_VISIBILITY_RANGE_HASH)
    }

    private const val INSTANCE_GEOMETRY_SET_LIGHTMAP_HASH = 536974962L
    private val instanceGeometrySetLightmapBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_set_lightmap", INSTANCE_GEOMETRY_SET_LIGHTMAP_HASH)
    }

    private const val INSTANCE_GEOMETRY_SET_LOD_BIAS_HASH = 1794382983L
    private val instanceGeometrySetLodBiasBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_set_lod_bias", INSTANCE_GEOMETRY_SET_LOD_BIAS_HASH)
    }

    private const val INSTANCE_GEOMETRY_SET_SHADER_PARAMETER_HASH = 3477296213L
    private val instanceGeometrySetShaderParameterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_set_shader_parameter", INSTANCE_GEOMETRY_SET_SHADER_PARAMETER_HASH)
    }

    private const val INSTANCE_GEOMETRY_GET_SHADER_PARAMETER_HASH = 2621281810L
    private val instanceGeometryGetShaderParameterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_get_shader_parameter", INSTANCE_GEOMETRY_GET_SHADER_PARAMETER_HASH)
    }

    private const val INSTANCE_GEOMETRY_GET_SHADER_PARAMETER_DEFAULT_VALUE_HASH = 2621281810L
    private val instanceGeometryGetShaderParameterDefaultValueBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_get_shader_parameter_default_value", INSTANCE_GEOMETRY_GET_SHADER_PARAMETER_DEFAULT_VALUE_HASH)
    }

    private const val INSTANCE_GEOMETRY_GET_SHADER_PARAMETER_LIST_HASH = 2684255073L
    private val instanceGeometryGetShaderParameterListBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instance_geometry_get_shader_parameter_list", INSTANCE_GEOMETRY_GET_SHADER_PARAMETER_LIST_HASH)
    }

    private const val INSTANCES_CULL_AABB_HASH = 2570105777L
    private val instancesCullAabbBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instances_cull_aabb", INSTANCES_CULL_AABB_HASH)
    }

    private const val INSTANCES_CULL_RAY_HASH = 2208759584L
    private val instancesCullRayBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instances_cull_ray", INSTANCES_CULL_RAY_HASH)
    }

    private const val INSTANCES_CULL_CONVEX_HASH = 2488539944L
    private val instancesCullConvexBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "instances_cull_convex", INSTANCES_CULL_CONVEX_HASH)
    }

    private const val BAKE_RENDER_UV2_HASH = 1904608558L
    private val bakeRenderUv2Bind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "bake_render_uv2", BAKE_RENDER_UV2_HASH)
    }

    private const val CANVAS_CREATE_HASH = 529393457L
    private val canvasCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_create", CANVAS_CREATE_HASH)
    }

    private const val CANVAS_SET_ITEM_MIRRORING_HASH = 2343975398L
    private val canvasSetItemMirroringBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_set_item_mirroring", CANVAS_SET_ITEM_MIRRORING_HASH)
    }

    private const val CANVAS_SET_ITEM_REPEAT_HASH = 1739512717L
    private val canvasSetItemRepeatBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_set_item_repeat", CANVAS_SET_ITEM_REPEAT_HASH)
    }

    private const val CANVAS_SET_MODULATE_HASH = 2948539648L
    private val canvasSetModulateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_set_modulate", CANVAS_SET_MODULATE_HASH)
    }

    private const val CANVAS_SET_DISABLE_SCALE_HASH = 2586408642L
    private val canvasSetDisableScaleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_set_disable_scale", CANVAS_SET_DISABLE_SCALE_HASH)
    }

    private const val CANVAS_TEXTURE_CREATE_HASH = 529393457L
    private val canvasTextureCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_texture_create", CANVAS_TEXTURE_CREATE_HASH)
    }

    private const val CANVAS_TEXTURE_SET_CHANNEL_HASH = 3822119138L
    private val canvasTextureSetChannelBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_texture_set_channel", CANVAS_TEXTURE_SET_CHANNEL_HASH)
    }

    private const val CANVAS_TEXTURE_SET_SHADING_PARAMETERS_HASH = 2124967469L
    private val canvasTextureSetShadingParametersBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_texture_set_shading_parameters", CANVAS_TEXTURE_SET_SHADING_PARAMETERS_HASH)
    }

    private const val CANVAS_TEXTURE_SET_TEXTURE_FILTER_HASH = 1155129294L
    private val canvasTextureSetTextureFilterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_texture_set_texture_filter", CANVAS_TEXTURE_SET_TEXTURE_FILTER_HASH)
    }

    private const val CANVAS_TEXTURE_SET_TEXTURE_REPEAT_HASH = 1652956681L
    private val canvasTextureSetTextureRepeatBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_texture_set_texture_repeat", CANVAS_TEXTURE_SET_TEXTURE_REPEAT_HASH)
    }

    private const val CANVAS_ITEM_CREATE_HASH = 529393457L
    private val canvasItemCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_create", CANVAS_ITEM_CREATE_HASH)
    }

    private const val CANVAS_ITEM_SET_PARENT_HASH = 395945892L
    private val canvasItemSetParentBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_parent", CANVAS_ITEM_SET_PARENT_HASH)
    }

    private const val CANVAS_ITEM_SET_DEFAULT_TEXTURE_FILTER_HASH = 1155129294L
    private val canvasItemSetDefaultTextureFilterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_default_texture_filter", CANVAS_ITEM_SET_DEFAULT_TEXTURE_FILTER_HASH)
    }

    private const val CANVAS_ITEM_SET_DEFAULT_TEXTURE_REPEAT_HASH = 1652956681L
    private val canvasItemSetDefaultTextureRepeatBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_default_texture_repeat", CANVAS_ITEM_SET_DEFAULT_TEXTURE_REPEAT_HASH)
    }

    private const val CANVAS_ITEM_SET_VISIBLE_HASH = 1265174801L
    private val canvasItemSetVisibleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_visible", CANVAS_ITEM_SET_VISIBLE_HASH)
    }

    private const val CANVAS_ITEM_SET_LIGHT_MASK_HASH = 3411492887L
    private val canvasItemSetLightMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_light_mask", CANVAS_ITEM_SET_LIGHT_MASK_HASH)
    }

    private const val CANVAS_ITEM_SET_VISIBILITY_LAYER_HASH = 3411492887L
    private val canvasItemSetVisibilityLayerBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_visibility_layer", CANVAS_ITEM_SET_VISIBILITY_LAYER_HASH)
    }

    private const val CANVAS_ITEM_SET_TRANSFORM_HASH = 1246044741L
    private val canvasItemSetTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_transform", CANVAS_ITEM_SET_TRANSFORM_HASH)
    }

    private const val CANVAS_ITEM_SET_CLIP_HASH = 1265174801L
    private val canvasItemSetClipBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_clip", CANVAS_ITEM_SET_CLIP_HASH)
    }

    private const val CANVAS_ITEM_SET_DISTANCE_FIELD_MODE_HASH = 1265174801L
    private val canvasItemSetDistanceFieldModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_distance_field_mode", CANVAS_ITEM_SET_DISTANCE_FIELD_MODE_HASH)
    }

    private const val CANVAS_ITEM_SET_CUSTOM_RECT_HASH = 1333997032L
    private val canvasItemSetCustomRectBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_custom_rect", CANVAS_ITEM_SET_CUSTOM_RECT_HASH)
    }

    private const val CANVAS_ITEM_SET_MODULATE_HASH = 2948539648L
    private val canvasItemSetModulateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_modulate", CANVAS_ITEM_SET_MODULATE_HASH)
    }

    private const val CANVAS_ITEM_SET_SELF_MODULATE_HASH = 2948539648L
    private val canvasItemSetSelfModulateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_self_modulate", CANVAS_ITEM_SET_SELF_MODULATE_HASH)
    }

    private const val CANVAS_ITEM_SET_DRAW_BEHIND_PARENT_HASH = 1265174801L
    private val canvasItemSetDrawBehindParentBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_draw_behind_parent", CANVAS_ITEM_SET_DRAW_BEHIND_PARENT_HASH)
    }

    private const val CANVAS_ITEM_SET_INTERPOLATED_HASH = 1265174801L
    private val canvasItemSetInterpolatedBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_interpolated", CANVAS_ITEM_SET_INTERPOLATED_HASH)
    }

    private const val CANVAS_ITEM_RESET_PHYSICS_INTERPOLATION_HASH = 2722037293L
    private val canvasItemResetPhysicsInterpolationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_reset_physics_interpolation", CANVAS_ITEM_RESET_PHYSICS_INTERPOLATION_HASH)
    }

    private const val CANVAS_ITEM_TRANSFORM_PHYSICS_INTERPOLATION_HASH = 1246044741L
    private val canvasItemTransformPhysicsInterpolationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_transform_physics_interpolation", CANVAS_ITEM_TRANSFORM_PHYSICS_INTERPOLATION_HASH)
    }

    private const val CANVAS_ITEM_ADD_LINE_HASH = 1819681853L
    private val canvasItemAddLineBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_line", CANVAS_ITEM_ADD_LINE_HASH)
    }

    private const val CANVAS_ITEM_ADD_POLYLINE_HASH = 3098767073L
    private val canvasItemAddPolylineBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_polyline", CANVAS_ITEM_ADD_POLYLINE_HASH)
    }

    private const val CANVAS_ITEM_ADD_MULTILINE_HASH = 3098767073L
    private val canvasItemAddMultilineBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_multiline", CANVAS_ITEM_ADD_MULTILINE_HASH)
    }

    private const val CANVAS_ITEM_ADD_RECT_HASH = 3523446176L
    private val canvasItemAddRectBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_rect", CANVAS_ITEM_ADD_RECT_HASH)
    }

    private const val CANVAS_ITEM_ADD_CIRCLE_HASH = 333077949L
    private val canvasItemAddCircleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_circle", CANVAS_ITEM_ADD_CIRCLE_HASH)
    }

    private const val CANVAS_ITEM_ADD_ELLIPSE_HASH = 4188642757L
    private val canvasItemAddEllipseBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_ellipse", CANVAS_ITEM_ADD_ELLIPSE_HASH)
    }

    private const val CANVAS_ITEM_ADD_TEXTURE_RECT_HASH = 324864032L
    private val canvasItemAddTextureRectBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_texture_rect", CANVAS_ITEM_ADD_TEXTURE_RECT_HASH)
    }

    private const val CANVAS_ITEM_ADD_MSDF_TEXTURE_RECT_REGION_HASH = 97408773L
    private val canvasItemAddMsdfTextureRectRegionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_msdf_texture_rect_region", CANVAS_ITEM_ADD_MSDF_TEXTURE_RECT_REGION_HASH)
    }

    private const val CANVAS_ITEM_ADD_LCD_TEXTURE_RECT_REGION_HASH = 359793297L
    private val canvasItemAddLcdTextureRectRegionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_lcd_texture_rect_region", CANVAS_ITEM_ADD_LCD_TEXTURE_RECT_REGION_HASH)
    }

    private const val CANVAS_ITEM_ADD_TEXTURE_RECT_REGION_HASH = 485157892L
    private val canvasItemAddTextureRectRegionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_texture_rect_region", CANVAS_ITEM_ADD_TEXTURE_RECT_REGION_HASH)
    }

    private const val CANVAS_ITEM_ADD_NINE_PATCH_HASH = 389957886L
    private val canvasItemAddNinePatchBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_nine_patch", CANVAS_ITEM_ADD_NINE_PATCH_HASH)
    }

    private const val CANVAS_ITEM_ADD_PRIMITIVE_HASH = 3731601077L
    private val canvasItemAddPrimitiveBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_primitive", CANVAS_ITEM_ADD_PRIMITIVE_HASH)
    }

    private const val CANVAS_ITEM_ADD_POLYGON_HASH = 3580000528L
    private val canvasItemAddPolygonBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_polygon", CANVAS_ITEM_ADD_POLYGON_HASH)
    }

    private const val CANVAS_ITEM_ADD_TRIANGLE_ARRAY_HASH = 660261329L
    private val canvasItemAddTriangleArrayBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_triangle_array", CANVAS_ITEM_ADD_TRIANGLE_ARRAY_HASH)
    }

    private const val CANVAS_ITEM_ADD_MESH_HASH = 316450961L
    private val canvasItemAddMeshBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_mesh", CANVAS_ITEM_ADD_MESH_HASH)
    }

    private const val CANVAS_ITEM_ADD_MULTIMESH_HASH = 2131855138L
    private val canvasItemAddMultimeshBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_multimesh", CANVAS_ITEM_ADD_MULTIMESH_HASH)
    }

    private const val CANVAS_ITEM_ADD_PARTICLES_HASH = 2575754278L
    private val canvasItemAddParticlesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_particles", CANVAS_ITEM_ADD_PARTICLES_HASH)
    }

    private const val CANVAS_ITEM_ADD_SET_TRANSFORM_HASH = 1246044741L
    private val canvasItemAddSetTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_set_transform", CANVAS_ITEM_ADD_SET_TRANSFORM_HASH)
    }

    private const val CANVAS_ITEM_ADD_CLIP_IGNORE_HASH = 1265174801L
    private val canvasItemAddClipIgnoreBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_clip_ignore", CANVAS_ITEM_ADD_CLIP_IGNORE_HASH)
    }

    private const val CANVAS_ITEM_ADD_ANIMATION_SLICE_HASH = 2646834499L
    private val canvasItemAddAnimationSliceBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_add_animation_slice", CANVAS_ITEM_ADD_ANIMATION_SLICE_HASH)
    }

    private const val CANVAS_ITEM_SET_SORT_CHILDREN_BY_Y_HASH = 1265174801L
    private val canvasItemSetSortChildrenByYBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_sort_children_by_y", CANVAS_ITEM_SET_SORT_CHILDREN_BY_Y_HASH)
    }

    private const val CANVAS_ITEM_SET_Z_INDEX_HASH = 3411492887L
    private val canvasItemSetZIndexBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_z_index", CANVAS_ITEM_SET_Z_INDEX_HASH)
    }

    private const val CANVAS_ITEM_SET_Z_AS_RELATIVE_TO_PARENT_HASH = 1265174801L
    private val canvasItemSetZAsRelativeToParentBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_z_as_relative_to_parent", CANVAS_ITEM_SET_Z_AS_RELATIVE_TO_PARENT_HASH)
    }

    private const val CANVAS_ITEM_SET_COPY_TO_BACKBUFFER_HASH = 2429202503L
    private val canvasItemSetCopyToBackbufferBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_copy_to_backbuffer", CANVAS_ITEM_SET_COPY_TO_BACKBUFFER_HASH)
    }

    private const val CANVAS_ITEM_ATTACH_SKELETON_HASH = 395945892L
    private val canvasItemAttachSkeletonBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_attach_skeleton", CANVAS_ITEM_ATTACH_SKELETON_HASH)
    }

    private const val CANVAS_ITEM_CLEAR_HASH = 2722037293L
    private val canvasItemClearBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_clear", CANVAS_ITEM_CLEAR_HASH)
    }

    private const val CANVAS_ITEM_SET_DRAW_INDEX_HASH = 3411492887L
    private val canvasItemSetDrawIndexBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_draw_index", CANVAS_ITEM_SET_DRAW_INDEX_HASH)
    }

    private const val CANVAS_ITEM_SET_MATERIAL_HASH = 395945892L
    private val canvasItemSetMaterialBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_material", CANVAS_ITEM_SET_MATERIAL_HASH)
    }

    private const val CANVAS_ITEM_SET_USE_PARENT_MATERIAL_HASH = 1265174801L
    private val canvasItemSetUseParentMaterialBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_use_parent_material", CANVAS_ITEM_SET_USE_PARENT_MATERIAL_HASH)
    }

    private const val CANVAS_ITEM_SET_INSTANCE_SHADER_PARAMETER_HASH = 3477296213L
    private val canvasItemSetInstanceShaderParameterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_instance_shader_parameter", CANVAS_ITEM_SET_INSTANCE_SHADER_PARAMETER_HASH)
    }

    private const val CANVAS_ITEM_GET_INSTANCE_SHADER_PARAMETER_HASH = 2621281810L
    private val canvasItemGetInstanceShaderParameterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_get_instance_shader_parameter", CANVAS_ITEM_GET_INSTANCE_SHADER_PARAMETER_HASH)
    }

    private const val CANVAS_ITEM_GET_INSTANCE_SHADER_PARAMETER_DEFAULT_VALUE_HASH = 2621281810L
    private val canvasItemGetInstanceShaderParameterDefaultValueBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_get_instance_shader_parameter_default_value", CANVAS_ITEM_GET_INSTANCE_SHADER_PARAMETER_DEFAULT_VALUE_HASH)
    }

    private const val CANVAS_ITEM_GET_INSTANCE_SHADER_PARAMETER_LIST_HASH = 2684255073L
    private val canvasItemGetInstanceShaderParameterListBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_get_instance_shader_parameter_list", CANVAS_ITEM_GET_INSTANCE_SHADER_PARAMETER_LIST_HASH)
    }

    private const val CANVAS_ITEM_SET_VISIBILITY_NOTIFIER_HASH = 3568945579L
    private val canvasItemSetVisibilityNotifierBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_visibility_notifier", CANVAS_ITEM_SET_VISIBILITY_NOTIFIER_HASH)
    }

    private const val CANVAS_ITEM_SET_CANVAS_GROUP_MODE_HASH = 3973586316L
    private val canvasItemSetCanvasGroupModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_item_set_canvas_group_mode", CANVAS_ITEM_SET_CANVAS_GROUP_MODE_HASH)
    }

    private const val DEBUG_CANVAS_ITEM_GET_RECT_HASH = 624227424L
    private val debugCanvasItemGetRectBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "debug_canvas_item_get_rect", DEBUG_CANVAS_ITEM_GET_RECT_HASH)
    }

    private const val CANVAS_LIGHT_CREATE_HASH = 529393457L
    private val canvasLightCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_create", CANVAS_LIGHT_CREATE_HASH)
    }

    private const val CANVAS_LIGHT_ATTACH_TO_CANVAS_HASH = 395945892L
    private val canvasLightAttachToCanvasBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_attach_to_canvas", CANVAS_LIGHT_ATTACH_TO_CANVAS_HASH)
    }

    private const val CANVAS_LIGHT_SET_ENABLED_HASH = 1265174801L
    private val canvasLightSetEnabledBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_enabled", CANVAS_LIGHT_SET_ENABLED_HASH)
    }

    private const val CANVAS_LIGHT_SET_TEXTURE_SCALE_HASH = 1794382983L
    private val canvasLightSetTextureScaleBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_texture_scale", CANVAS_LIGHT_SET_TEXTURE_SCALE_HASH)
    }

    private const val CANVAS_LIGHT_SET_TRANSFORM_HASH = 1246044741L
    private val canvasLightSetTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_transform", CANVAS_LIGHT_SET_TRANSFORM_HASH)
    }

    private const val CANVAS_LIGHT_SET_TEXTURE_HASH = 395945892L
    private val canvasLightSetTextureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_texture", CANVAS_LIGHT_SET_TEXTURE_HASH)
    }

    private const val CANVAS_LIGHT_SET_TEXTURE_OFFSET_HASH = 3201125042L
    private val canvasLightSetTextureOffsetBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_texture_offset", CANVAS_LIGHT_SET_TEXTURE_OFFSET_HASH)
    }

    private const val CANVAS_LIGHT_SET_COLOR_HASH = 2948539648L
    private val canvasLightSetColorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_color", CANVAS_LIGHT_SET_COLOR_HASH)
    }

    private const val CANVAS_LIGHT_SET_HEIGHT_HASH = 1794382983L
    private val canvasLightSetHeightBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_height", CANVAS_LIGHT_SET_HEIGHT_HASH)
    }

    private const val CANVAS_LIGHT_SET_ENERGY_HASH = 1794382983L
    private val canvasLightSetEnergyBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_energy", CANVAS_LIGHT_SET_ENERGY_HASH)
    }

    private const val CANVAS_LIGHT_SET_Z_RANGE_HASH = 4288446313L
    private val canvasLightSetZRangeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_z_range", CANVAS_LIGHT_SET_Z_RANGE_HASH)
    }

    private const val CANVAS_LIGHT_SET_LAYER_RANGE_HASH = 4288446313L
    private val canvasLightSetLayerRangeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_layer_range", CANVAS_LIGHT_SET_LAYER_RANGE_HASH)
    }

    private const val CANVAS_LIGHT_SET_ITEM_CULL_MASK_HASH = 3411492887L
    private val canvasLightSetItemCullMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_item_cull_mask", CANVAS_LIGHT_SET_ITEM_CULL_MASK_HASH)
    }

    private const val CANVAS_LIGHT_SET_ITEM_SHADOW_CULL_MASK_HASH = 3411492887L
    private val canvasLightSetItemShadowCullMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_item_shadow_cull_mask", CANVAS_LIGHT_SET_ITEM_SHADOW_CULL_MASK_HASH)
    }

    private const val CANVAS_LIGHT_SET_MODE_HASH = 2957564891L
    private val canvasLightSetModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_mode", CANVAS_LIGHT_SET_MODE_HASH)
    }

    private const val CANVAS_LIGHT_SET_SHADOW_ENABLED_HASH = 1265174801L
    private val canvasLightSetShadowEnabledBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_shadow_enabled", CANVAS_LIGHT_SET_SHADOW_ENABLED_HASH)
    }

    private const val CANVAS_LIGHT_SET_SHADOW_FILTER_HASH = 393119659L
    private val canvasLightSetShadowFilterBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_shadow_filter", CANVAS_LIGHT_SET_SHADOW_FILTER_HASH)
    }

    private const val CANVAS_LIGHT_SET_SHADOW_COLOR_HASH = 2948539648L
    private val canvasLightSetShadowColorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_shadow_color", CANVAS_LIGHT_SET_SHADOW_COLOR_HASH)
    }

    private const val CANVAS_LIGHT_SET_SHADOW_SMOOTH_HASH = 1794382983L
    private val canvasLightSetShadowSmoothBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_shadow_smooth", CANVAS_LIGHT_SET_SHADOW_SMOOTH_HASH)
    }

    private const val CANVAS_LIGHT_SET_BLEND_MODE_HASH = 804895945L
    private val canvasLightSetBlendModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_blend_mode", CANVAS_LIGHT_SET_BLEND_MODE_HASH)
    }

    private const val CANVAS_LIGHT_SET_INTERPOLATED_HASH = 1265174801L
    private val canvasLightSetInterpolatedBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_set_interpolated", CANVAS_LIGHT_SET_INTERPOLATED_HASH)
    }

    private const val CANVAS_LIGHT_RESET_PHYSICS_INTERPOLATION_HASH = 2722037293L
    private val canvasLightResetPhysicsInterpolationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_reset_physics_interpolation", CANVAS_LIGHT_RESET_PHYSICS_INTERPOLATION_HASH)
    }

    private const val CANVAS_LIGHT_TRANSFORM_PHYSICS_INTERPOLATION_HASH = 1246044741L
    private val canvasLightTransformPhysicsInterpolationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_transform_physics_interpolation", CANVAS_LIGHT_TRANSFORM_PHYSICS_INTERPOLATION_HASH)
    }

    private const val CANVAS_LIGHT_OCCLUDER_CREATE_HASH = 529393457L
    private val canvasLightOccluderCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_occluder_create", CANVAS_LIGHT_OCCLUDER_CREATE_HASH)
    }

    private const val CANVAS_LIGHT_OCCLUDER_ATTACH_TO_CANVAS_HASH = 395945892L
    private val canvasLightOccluderAttachToCanvasBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_occluder_attach_to_canvas", CANVAS_LIGHT_OCCLUDER_ATTACH_TO_CANVAS_HASH)
    }

    private const val CANVAS_LIGHT_OCCLUDER_SET_ENABLED_HASH = 1265174801L
    private val canvasLightOccluderSetEnabledBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_occluder_set_enabled", CANVAS_LIGHT_OCCLUDER_SET_ENABLED_HASH)
    }

    private const val CANVAS_LIGHT_OCCLUDER_SET_POLYGON_HASH = 395945892L
    private val canvasLightOccluderSetPolygonBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_occluder_set_polygon", CANVAS_LIGHT_OCCLUDER_SET_POLYGON_HASH)
    }

    private const val CANVAS_LIGHT_OCCLUDER_SET_AS_SDF_COLLISION_HASH = 1265174801L
    private val canvasLightOccluderSetAsSdfCollisionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_occluder_set_as_sdf_collision", CANVAS_LIGHT_OCCLUDER_SET_AS_SDF_COLLISION_HASH)
    }

    private const val CANVAS_LIGHT_OCCLUDER_SET_TRANSFORM_HASH = 1246044741L
    private val canvasLightOccluderSetTransformBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_occluder_set_transform", CANVAS_LIGHT_OCCLUDER_SET_TRANSFORM_HASH)
    }

    private const val CANVAS_LIGHT_OCCLUDER_SET_LIGHT_MASK_HASH = 3411492887L
    private val canvasLightOccluderSetLightMaskBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_occluder_set_light_mask", CANVAS_LIGHT_OCCLUDER_SET_LIGHT_MASK_HASH)
    }

    private const val CANVAS_LIGHT_OCCLUDER_SET_INTERPOLATED_HASH = 1265174801L
    private val canvasLightOccluderSetInterpolatedBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_occluder_set_interpolated", CANVAS_LIGHT_OCCLUDER_SET_INTERPOLATED_HASH)
    }

    private const val CANVAS_LIGHT_OCCLUDER_RESET_PHYSICS_INTERPOLATION_HASH = 2722037293L
    private val canvasLightOccluderResetPhysicsInterpolationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_occluder_reset_physics_interpolation", CANVAS_LIGHT_OCCLUDER_RESET_PHYSICS_INTERPOLATION_HASH)
    }

    private const val CANVAS_LIGHT_OCCLUDER_TRANSFORM_PHYSICS_INTERPOLATION_HASH = 1246044741L
    private val canvasLightOccluderTransformPhysicsInterpolationBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_light_occluder_transform_physics_interpolation", CANVAS_LIGHT_OCCLUDER_TRANSFORM_PHYSICS_INTERPOLATION_HASH)
    }

    private const val CANVAS_OCCLUDER_POLYGON_CREATE_HASH = 529393457L
    private val canvasOccluderPolygonCreateBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_occluder_polygon_create", CANVAS_OCCLUDER_POLYGON_CREATE_HASH)
    }

    private const val CANVAS_OCCLUDER_POLYGON_SET_SHAPE_HASH = 2103882027L
    private val canvasOccluderPolygonSetShapeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_occluder_polygon_set_shape", CANVAS_OCCLUDER_POLYGON_SET_SHAPE_HASH)
    }

    private const val CANVAS_OCCLUDER_POLYGON_SET_CULL_MODE_HASH = 1839404663L
    private val canvasOccluderPolygonSetCullModeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_occluder_polygon_set_cull_mode", CANVAS_OCCLUDER_POLYGON_SET_CULL_MODE_HASH)
    }

    private const val CANVAS_SET_SHADOW_TEXTURE_SIZE_HASH = 1286410249L
    private val canvasSetShadowTextureSizeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "canvas_set_shadow_texture_size", CANVAS_SET_SHADOW_TEXTURE_SIZE_HASH)
    }

    private const val GLOBAL_SHADER_PARAMETER_ADD_HASH = 463390080L
    private val globalShaderParameterAddBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "global_shader_parameter_add", GLOBAL_SHADER_PARAMETER_ADD_HASH)
    }

    private const val GLOBAL_SHADER_PARAMETER_REMOVE_HASH = 3304788590L
    private val globalShaderParameterRemoveBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "global_shader_parameter_remove", GLOBAL_SHADER_PARAMETER_REMOVE_HASH)
    }

    private const val GLOBAL_SHADER_PARAMETER_GET_LIST_HASH = 3995934104L
    private val globalShaderParameterGetListBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "global_shader_parameter_get_list", GLOBAL_SHADER_PARAMETER_GET_LIST_HASH)
    }

    private const val GLOBAL_SHADER_PARAMETER_SET_HASH = 3776071444L
    private val globalShaderParameterSetBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "global_shader_parameter_set", GLOBAL_SHADER_PARAMETER_SET_HASH)
    }

    private const val GLOBAL_SHADER_PARAMETER_SET_OVERRIDE_HASH = 3776071444L
    private val globalShaderParameterSetOverrideBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "global_shader_parameter_set_override", GLOBAL_SHADER_PARAMETER_SET_OVERRIDE_HASH)
    }

    private const val GLOBAL_SHADER_PARAMETER_GET_HASH = 2760726917L
    private val globalShaderParameterGetBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "global_shader_parameter_get", GLOBAL_SHADER_PARAMETER_GET_HASH)
    }

    private const val GLOBAL_SHADER_PARAMETER_GET_TYPE_HASH = 1601414142L
    private val globalShaderParameterGetTypeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "global_shader_parameter_get_type", GLOBAL_SHADER_PARAMETER_GET_TYPE_HASH)
    }

    private const val FREE_RID_HASH = 2722037293L
    private val freeRidBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "free_rid", FREE_RID_HASH)
    }

    private const val REQUEST_FRAME_DRAWN_CALLBACK_HASH = 1611583062L
    private val requestFrameDrawnCallbackBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "request_frame_drawn_callback", REQUEST_FRAME_DRAWN_CALLBACK_HASH)
    }

    private const val HAS_CHANGED_HASH = 36873697L
    private val hasChangedBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "has_changed", HAS_CHANGED_HASH)
    }

    private const val GET_RENDERING_INFO_HASH = 3763192241L
    private val getRenderingInfoBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_rendering_info", GET_RENDERING_INFO_HASH)
    }

    private const val GET_VIDEO_ADAPTER_NAME_HASH = 201670096L
    private val getVideoAdapterNameBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_video_adapter_name", GET_VIDEO_ADAPTER_NAME_HASH)
    }

    private const val GET_VIDEO_ADAPTER_VENDOR_HASH = 201670096L
    private val getVideoAdapterVendorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_video_adapter_vendor", GET_VIDEO_ADAPTER_VENDOR_HASH)
    }

    private const val GET_VIDEO_ADAPTER_TYPE_HASH = 3099547011L
    private val getVideoAdapterTypeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_video_adapter_type", GET_VIDEO_ADAPTER_TYPE_HASH)
    }

    private const val GET_VIDEO_ADAPTER_API_VERSION_HASH = 201670096L
    private val getVideoAdapterApiVersionBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_video_adapter_api_version", GET_VIDEO_ADAPTER_API_VERSION_HASH)
    }

    private const val GET_CURRENT_RENDERING_DRIVER_NAME_HASH = 201670096L
    private val getCurrentRenderingDriverNameBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_current_rendering_driver_name", GET_CURRENT_RENDERING_DRIVER_NAME_HASH)
    }

    private const val GET_CURRENT_RENDERING_METHOD_HASH = 201670096L
    private val getCurrentRenderingMethodBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_current_rendering_method", GET_CURRENT_RENDERING_METHOD_HASH)
    }

    private const val MAKE_SPHERE_MESH_HASH = 2251015897L
    private val makeSphereMeshBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "make_sphere_mesh", MAKE_SPHERE_MESH_HASH)
    }

    private const val GET_TEST_CUBE_HASH = 529393457L
    private val getTestCubeBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_test_cube", GET_TEST_CUBE_HASH)
    }

    private const val GET_TEST_TEXTURE_HASH = 529393457L
    private val getTestTextureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_test_texture", GET_TEST_TEXTURE_HASH)
    }

    private const val GET_WHITE_TEXTURE_HASH = 529393457L
    private val getWhiteTextureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_white_texture", GET_WHITE_TEXTURE_HASH)
    }

    private const val SET_BOOT_IMAGE_WITH_STRETCH_HASH = 1104470771L
    private val setBootImageWithStretchBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "set_boot_image_with_stretch", SET_BOOT_IMAGE_WITH_STRETCH_HASH)
    }

    private const val SET_BOOT_IMAGE_HASH = 3759744527L
    private val setBootImageBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "set_boot_image", SET_BOOT_IMAGE_HASH)
    }

    private const val GET_DEFAULT_CLEAR_COLOR_HASH = 3200896285L
    private val getDefaultClearColorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_default_clear_color", GET_DEFAULT_CLEAR_COLOR_HASH)
    }

    private const val SET_DEFAULT_CLEAR_COLOR_HASH = 2920490490L
    private val setDefaultClearColorBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "set_default_clear_color", SET_DEFAULT_CLEAR_COLOR_HASH)
    }

    private const val HAS_OS_FEATURE_HASH = 3927539163L
    private val hasOsFeatureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "has_os_feature", HAS_OS_FEATURE_HASH)
    }

    private const val SET_DEBUG_GENERATE_WIREFRAMES_HASH = 2586408642L
    private val setDebugGenerateWireframesBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "set_debug_generate_wireframes", SET_DEBUG_GENERATE_WIREFRAMES_HASH)
    }

    private const val IS_RENDER_LOOP_ENABLED_HASH = 36873697L
    private val isRenderLoopEnabledBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "is_render_loop_enabled", IS_RENDER_LOOP_ENABLED_HASH)
    }

    private const val SET_RENDER_LOOP_ENABLED_HASH = 2586408642L
    private val setRenderLoopEnabledBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "set_render_loop_enabled", SET_RENDER_LOOP_ENABLED_HASH)
    }

    private const val GET_FRAME_SETUP_TIME_CPU_HASH = 1740695150L
    private val getFrameSetupTimeCpuBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_frame_setup_time_cpu", GET_FRAME_SETUP_TIME_CPU_HASH)
    }

    private const val FORCE_SYNC_HASH = 3218959716L
    private val forceSyncBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "force_sync", FORCE_SYNC_HASH)
    }

    private const val FORCE_DRAW_HASH = 1076185472L
    private val forceDrawBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "force_draw", FORCE_DRAW_HASH)
    }

    private const val GET_RENDERING_DEVICE_HASH = 1405107940L
    private val getRenderingDeviceBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "get_rendering_device", GET_RENDERING_DEVICE_HASH)
    }

    private const val CREATE_LOCAL_RENDERING_DEVICE_HASH = 1405107940L
    private val createLocalRenderingDeviceBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "create_local_rendering_device", CREATE_LOCAL_RENDERING_DEVICE_HASH)
    }

    private const val IS_ON_RENDER_THREAD_HASH = 2240911060L
    private val isOnRenderThreadBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "is_on_render_thread", IS_ON_RENDER_THREAD_HASH)
    }

    private const val CALL_ON_RENDER_THREAD_HASH = 1611583062L
    private val callOnRenderThreadBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "call_on_render_thread", CALL_ON_RENDER_THREAD_HASH)
    }

    private const val HAS_FEATURE_HASH = 598462696L
    private val hasFeatureBind by lazy {
        ObjectCalls.getMethodBind("RenderingServer", "has_feature", HAS_FEATURE_HASH)
    }
}
