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

    /**
     * Sets the mesh used to draw. It must be a mesh using 2D vertices.
     *
     * Generated from Godot docs: MeshTexture.set_mesh
     */
    fun setMesh(mesh: Mesh?) {
        ObjectCalls.ptrcallWithObjectArgs(setMeshBind, handle, listOf(mesh?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Sets the mesh used to draw. It must be a mesh using 2D vertices.
     *
     * Generated from Godot docs: MeshTexture.get_mesh
     */
    fun getMesh(): Mesh? {
        return Mesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMeshBind, handle))
    }

    /**
     * Sets the size of the image, needed for reference.
     *
     * Generated from Godot docs: MeshTexture.set_image_size
     */
    fun setImageSize(size: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setImageSizeBind, handle, size)
    }

    /**
     * Sets the size of the image, needed for reference.
     *
     * Generated from Godot docs: MeshTexture.get_image_size
     */
    fun getImageSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getImageSizeBind, handle)
    }

    /**
     * Sets the base texture that the Mesh will use to draw.
     *
     * Generated from Godot docs: MeshTexture.set_base_texture
     */
    fun setBaseTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setBaseTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Sets the base texture that the Mesh will use to draw.
     *
     * Generated from Godot docs: MeshTexture.get_base_texture
     */
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
