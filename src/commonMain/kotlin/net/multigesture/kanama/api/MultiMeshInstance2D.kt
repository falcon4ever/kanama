package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Node that instances a `MultiMesh` in 2D.
 *
 * Generated from Godot docs: MultiMeshInstance2D
 */
class MultiMeshInstance2D(handle: GodotHandle) : Node2D(handle) {
    var multimesh: MultiMesh?
        @JvmName("multimeshProperty")
        get() = getMultimesh()
        @JvmName("setMultimeshProperty")
        set(value) = setMultimesh(value)

    var texture: Texture2D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    /**
     * The `MultiMesh` that will be drawn by the `MultiMeshInstance2D`.
     *
     * Generated from Godot docs: MultiMeshInstance2D.set_multimesh
     */
    fun setMultimesh(multimesh: MultiMesh?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setMultimeshBind, segment, listOf(multimesh?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `MultiMesh` that will be drawn by the `MultiMeshInstance2D`.
     *
     * Generated from Godot docs: MultiMeshInstance2D.get_multimesh
     */
    fun getMultimesh(): MultiMesh? {
        return MultiMesh.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMultimeshBind, segment))
    }

    /**
     * The `Texture2D` that will be used if using the default `CanvasItemMaterial`. Can be accessed as
     * `TEXTURE` in CanvasItem shader.
     *
     * Generated from Godot docs: MultiMeshInstance2D.set_texture
     */
    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `Texture2D` that will be used if using the default `CanvasItemMaterial`. Can be accessed as
     * `TEXTURE` in CanvasItem shader.
     *
     * Generated from Godot docs: MultiMeshInstance2D.get_texture
     */
    fun getTexture(): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getTextureBind, segment))
    }

    /** Signal `texture_changed()`; see [TypedSignal]. */
    val textureChanged: Signal0
        @JvmName("textureChangedTypedSignal")
        get() = Signal0(this, "texture_changed")

    object Signals {
        const val textureChanged: String = "texture_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): MultiMeshInstance2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): MultiMeshInstance2D? =
            if (handle.address() == 0L) null else MultiMeshInstance2D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_MULTIMESH_HASH = 2246127404L
        @JvmField
        val setMultimeshBind =
            ObjectCalls.getMethodBind("MultiMeshInstance2D", "set_multimesh", SET_MULTIMESH_HASH)

        private const val GET_MULTIMESH_HASH = 1385450523L
        @JvmField
        val getMultimeshBind =
            ObjectCalls.getMethodBind("MultiMeshInstance2D", "get_multimesh", GET_MULTIMESH_HASH)

        private const val SET_TEXTURE_HASH = 4051416890L
        @JvmField
        val setTextureBind =
            ObjectCalls.getMethodBind("MultiMeshInstance2D", "set_texture", SET_TEXTURE_HASH)

        private const val GET_TEXTURE_HASH = 3635182373L
        @JvmField
        val getTextureBind =
            ObjectCalls.getMethodBind("MultiMeshInstance2D", "get_texture", GET_TEXTURE_HASH)
    }
}
