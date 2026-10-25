package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * Simple texture that uses a mesh to draw itself.
 *
 * Generated from Godot docs: MeshTexture
 */
class MeshTexture(handle: GodotHandle) : Texture2D(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setMeshBind, segment, listOf(mesh?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(mesh)
    }

    /**
     * Sets the mesh used to draw. It must be a mesh using 2D vertices.
     *
     * Generated from Godot docs: MeshTexture.get_mesh
     */
    fun getMesh(): Mesh? {
        checkOpen()
        val ret = Mesh.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMeshBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the size of the image, needed for reference.
     *
     * Generated from Godot docs: MeshTexture.set_image_size
     */
    fun setImageSize(size: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setImageSizeBind, segment, size)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the size of the image, needed for reference.
     *
     * Generated from Godot docs: MeshTexture.get_image_size
     */
    fun getImageSize(): Vector2 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector2(Binds.getImageSizeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the base texture that the Mesh will use to draw.
     *
     * Generated from Godot docs: MeshTexture.set_base_texture
     */
    fun setBaseTexture(texture: Texture2D?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setBaseTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(texture)
    }

    /**
     * Sets the base texture that the Mesh will use to draw.
     *
     * Generated from Godot docs: MeshTexture.get_base_texture
     */
    fun getBaseTexture(): Texture2D? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(Binds.getBaseTextureBind, segment)
        ObjectCalls.keepReachable(this)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Texture2D.wrapOwned(ret)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): MeshTexture? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): MeshTexture? =
            if (handle.address() == 0L) null else RefCounted.owned(MeshTexture(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): MeshTexture? =
            if (handle.address() == 0L) null else MeshTexture(GodotHandle(handle))

        /** A new MeshTexture, GDScript's `MeshTexture.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): MeshTexture =
            RefCounted.owned(MeshTexture(GodotHandle(ObjectCalls.constructObject("MeshTexture"))))
    }

    private object Binds {
        private const val SET_MESH_HASH = 194775623L
        @JvmField
        val setMeshBind =
            ObjectCalls.getMethodBind("MeshTexture", "set_mesh", SET_MESH_HASH)

        private const val GET_MESH_HASH = 1808005922L
        @JvmField
        val getMeshBind =
            ObjectCalls.getMethodBind("MeshTexture", "get_mesh", GET_MESH_HASH)

        private const val SET_IMAGE_SIZE_HASH = 743155724L
        @JvmField
        val setImageSizeBind =
            ObjectCalls.getMethodBind("MeshTexture", "set_image_size", SET_IMAGE_SIZE_HASH)

        private const val GET_IMAGE_SIZE_HASH = 3341600327L
        @JvmField
        val getImageSizeBind =
            ObjectCalls.getMethodBind("MeshTexture", "get_image_size", GET_IMAGE_SIZE_HASH)

        private const val SET_BASE_TEXTURE_HASH = 4051416890L
        @JvmField
        val setBaseTextureBind =
            ObjectCalls.getMethodBind("MeshTexture", "set_base_texture", SET_BASE_TEXTURE_HASH)

        private const val GET_BASE_TEXTURE_HASH = 3635182373L
        @JvmField
        val getBaseTextureBind =
            ObjectCalls.getMethodBind("MeshTexture", "get_base_texture", GET_BASE_TEXTURE_HASH)
    }
}
