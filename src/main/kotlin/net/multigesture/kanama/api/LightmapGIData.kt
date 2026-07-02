package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Rect2

/**
 * Contains baked lightmap and dynamic object probe data for `LightmapGI`.
 *
 * Generated from Godot docs: LightmapGIData
 */
class LightmapGIData(handle: MemorySegment) : Resource(handle) {
    var lightmapTextures: List<TextureLayered>
        @JvmName("lightmapTexturesProperty")
        get() = getLightmapTextures()
        @JvmName("setLightmapTexturesProperty")
        set(value) = setLightmapTextures(value)

    var shadowmaskTextures: List<TextureLayered>
        @JvmName("shadowmaskTexturesProperty")
        get() = getShadowmaskTextures()
        @JvmName("setShadowmaskTexturesProperty")
        set(value) = setShadowmaskTextures(value)

    var usesSphericalHarmonics: Boolean
        @JvmName("usesSphericalHarmonicsProperty")
        get() = isUsingSphericalHarmonics()
        @JvmName("setUsesSphericalHarmonicsProperty")
        set(value) = setUsesSphericalHarmonics(value)

    var lightTexture: TextureLayered?
        @JvmName("lightTextureProperty")
        get() = getLightTexture()
        @JvmName("setLightTextureProperty")
        set(value) = setLightTexture(value)

    fun setLightmapTextures(lightTextures: List<TextureLayered>) {
        ObjectCalls.ptrcallWithObjectListArg(setLightmapTexturesBind, handle, lightTextures)
    }

    fun getLightmapTextures(): List<TextureLayered> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getLightmapTexturesBind, handle, TextureLayered::fromHandle)
    }

    fun setShadowmaskTextures(shadowmaskTextures: List<TextureLayered>) {
        ObjectCalls.ptrcallWithObjectListArg(setShadowmaskTexturesBind, handle, shadowmaskTextures)
    }

    fun getShadowmaskTextures(): List<TextureLayered> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getShadowmaskTexturesBind, handle, TextureLayered::fromHandle)
    }

    fun setUsesSphericalHarmonics(usesSphericalHarmonics: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUsesSphericalHarmonicsBind, handle, usesSphericalHarmonics)
    }

    fun isUsingSphericalHarmonics(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingSphericalHarmonicsBind, handle)
    }

    fun addUser(path: NodePath, uvScale: Rect2, sliceIndex: Int, subInstance: Int) {
        ObjectCalls.ptrcallWithNodePathRect2TwoIntArgs(addUserBind, handle, path, uvScale, sliceIndex, subInstance)
    }

    fun getUserCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getUserCountBind, handle)
    }

    fun getUserPath(userIdx: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(getUserPathBind, handle, userIdx)
    }

    fun clearUsers() {
        ObjectCalls.ptrcallNoArgs(clearUsersBind, handle)
    }

    fun setLightTexture(lightTexture: TextureLayered?) {
        ObjectCalls.ptrcallWithObjectArgs(setLightTextureBind, handle, listOf(lightTexture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getLightTexture(): TextureLayered? {
        return TextureLayered.wrap(ObjectCalls.ptrcallNoArgsRetObject(getLightTextureBind, handle))
    }

    companion object {
        const val SHADOWMASK_MODE_NONE: Long = 0L
        const val SHADOWMASK_MODE_REPLACE: Long = 1L
        const val SHADOWMASK_MODE_OVERLAY: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): LightmapGIData? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): LightmapGIData? =
            if (handle.address() == 0L) null else LightmapGIData(handle)

        private const val SET_LIGHTMAP_TEXTURES_HASH = 381264803L
        private val setLightmapTexturesBind by lazy {
            ObjectCalls.getMethodBind("LightmapGIData", "set_lightmap_textures", SET_LIGHTMAP_TEXTURES_HASH)
        }

        private const val GET_LIGHTMAP_TEXTURES_HASH = 3995934104L
        private val getLightmapTexturesBind by lazy {
            ObjectCalls.getMethodBind("LightmapGIData", "get_lightmap_textures", GET_LIGHTMAP_TEXTURES_HASH)
        }

        private const val SET_SHADOWMASK_TEXTURES_HASH = 381264803L
        private val setShadowmaskTexturesBind by lazy {
            ObjectCalls.getMethodBind("LightmapGIData", "set_shadowmask_textures", SET_SHADOWMASK_TEXTURES_HASH)
        }

        private const val GET_SHADOWMASK_TEXTURES_HASH = 3995934104L
        private val getShadowmaskTexturesBind by lazy {
            ObjectCalls.getMethodBind("LightmapGIData", "get_shadowmask_textures", GET_SHADOWMASK_TEXTURES_HASH)
        }

        private const val SET_USES_SPHERICAL_HARMONICS_HASH = 2586408642L
        private val setUsesSphericalHarmonicsBind by lazy {
            ObjectCalls.getMethodBind("LightmapGIData", "set_uses_spherical_harmonics", SET_USES_SPHERICAL_HARMONICS_HASH)
        }

        private const val IS_USING_SPHERICAL_HARMONICS_HASH = 36873697L
        private val isUsingSphericalHarmonicsBind by lazy {
            ObjectCalls.getMethodBind("LightmapGIData", "is_using_spherical_harmonics", IS_USING_SPHERICAL_HARMONICS_HASH)
        }

        private const val ADD_USER_HASH = 4272570515L
        private val addUserBind by lazy {
            ObjectCalls.getMethodBind("LightmapGIData", "add_user", ADD_USER_HASH)
        }

        private const val GET_USER_COUNT_HASH = 3905245786L
        private val getUserCountBind by lazy {
            ObjectCalls.getMethodBind("LightmapGIData", "get_user_count", GET_USER_COUNT_HASH)
        }

        private const val GET_USER_PATH_HASH = 408788394L
        private val getUserPathBind by lazy {
            ObjectCalls.getMethodBind("LightmapGIData", "get_user_path", GET_USER_PATH_HASH)
        }

        private const val CLEAR_USERS_HASH = 3218959716L
        private val clearUsersBind by lazy {
            ObjectCalls.getMethodBind("LightmapGIData", "clear_users", CLEAR_USERS_HASH)
        }

        private const val SET_LIGHT_TEXTURE_HASH = 1278366092L
        private val setLightTextureBind by lazy {
            ObjectCalls.getMethodBind("LightmapGIData", "set_light_texture", SET_LIGHT_TEXTURE_HASH)
        }

        private const val GET_LIGHT_TEXTURE_HASH = 3984243839L
        private val getLightTextureBind by lazy {
            ObjectCalls.getMethodBind("LightmapGIData", "get_light_texture", GET_LIGHT_TEXTURE_HASH)
        }
    }
}
