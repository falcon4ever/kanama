package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Simple texture that uses a mesh to draw itself.
 *
 * Generated from Godot docs: MeshTexture
 */
class MeshTexture(handle: MemorySegment) : Texture2D(handle) {
    var mesh: Mesh?
        @JvmName("meshProperty")
        get() = getMesh()
        @JvmName("setMeshProperty")
        set(value) = setMesh(value)

    var baseTexture: Texture2D?
        @JvmName("baseTextureProperty")
        get() = getBaseTexture()
        @JvmName("setBaseTextureProperty")
        set(value) = setBaseTexture(value)

    var imageSize: Vector2
        @JvmName("imageSizeProperty")
        get() = getImageSize()
        @JvmName("setImageSizeProperty")
        set(value) = setImageSize(value)

    fun setMesh(mesh: Mesh?) {
        ObjectCalls.ptrcallWithObjectArgs(setMeshBind, handle, listOf(mesh?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getMesh(): Mesh? {
        return Mesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMeshBind, handle))
    }

    fun setImageSize(size: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setImageSizeBind, handle, size)
    }

    fun getImageSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getImageSizeBind, handle)
    }

    fun setBaseTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setBaseTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getBaseTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getBaseTextureBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): MeshTexture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): MeshTexture? =
            if (handle.address() == 0L) null else MeshTexture(handle)

        private const val SET_MESH_HASH = 194775623L
        private val setMeshBind by lazy {
            ObjectCalls.getMethodBind("MeshTexture", "set_mesh", SET_MESH_HASH)
        }

        private const val GET_MESH_HASH = 1808005922L
        private val getMeshBind by lazy {
            ObjectCalls.getMethodBind("MeshTexture", "get_mesh", GET_MESH_HASH)
        }

        private const val SET_IMAGE_SIZE_HASH = 743155724L
        private val setImageSizeBind by lazy {
            ObjectCalls.getMethodBind("MeshTexture", "set_image_size", SET_IMAGE_SIZE_HASH)
        }

        private const val GET_IMAGE_SIZE_HASH = 3341600327L
        private val getImageSizeBind by lazy {
            ObjectCalls.getMethodBind("MeshTexture", "get_image_size", GET_IMAGE_SIZE_HASH)
        }

        private const val SET_BASE_TEXTURE_HASH = 4051416890L
        private val setBaseTextureBind by lazy {
            ObjectCalls.getMethodBind("MeshTexture", "set_base_texture", SET_BASE_TEXTURE_HASH)
        }

        private const val GET_BASE_TEXTURE_HASH = 3635182373L
        private val getBaseTextureBind by lazy {
            ObjectCalls.getMethodBind("MeshTexture", "get_base_texture", GET_BASE_TEXTURE_HASH)
        }
    }
}
