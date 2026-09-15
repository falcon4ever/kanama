package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Node used for displaying a `Mesh` in 2D.
 *
 * Generated from Godot docs: MeshInstance2D
 */
class MeshInstance2D(handle: GodotHandle) : Node2D(handle) {
    var mesh: Mesh?
        @JvmName("meshProperty")
        get() = getMesh()
        @JvmName("setMeshProperty")
        set(value) = setMesh(value)

    var texture: Texture2D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    /**
     * The `Mesh` that will be drawn by the `MeshInstance2D`.
     *
     * Generated from Godot docs: MeshInstance2D.set_mesh
     */
    fun setMesh(mesh: Mesh?) {
        ObjectCalls.ptrcallWithObjectArgs(setMeshBind, segment, listOf(mesh?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `Mesh` that will be drawn by the `MeshInstance2D`.
     *
     * Generated from Godot docs: MeshInstance2D.get_mesh
     */
    fun getMesh(): Mesh? {
        return Mesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMeshBind, segment))
    }

    /**
     * The `Texture2D` that will be used if using the default `CanvasItemMaterial`. Can be accessed as
     * `TEXTURE` in CanvasItem shader.
     *
     * Generated from Godot docs: MeshInstance2D.set_texture
     */
    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `Texture2D` that will be used if using the default `CanvasItemMaterial`. Can be accessed as
     * `TEXTURE` in CanvasItem shader.
     *
     * Generated from Godot docs: MeshInstance2D.get_texture
     */
    fun getTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, segment))
    }

    object Signals {
        const val textureChanged: String = "texture_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): MeshInstance2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): MeshInstance2D? =
            if (handle.address() == 0L) null else MeshInstance2D(GodotHandle(handle))

        private const val SET_MESH_HASH = 194775623L
        private val setMeshBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance2D", "set_mesh", SET_MESH_HASH)
        }

        private const val GET_MESH_HASH = 1808005922L
        private val getMeshBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance2D", "get_mesh", GET_MESH_HASH)
        }

        private const val SET_TEXTURE_HASH = 4051416890L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance2D", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 3635182373L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance2D", "get_texture", GET_TEXTURE_HASH)
        }
    }
}
