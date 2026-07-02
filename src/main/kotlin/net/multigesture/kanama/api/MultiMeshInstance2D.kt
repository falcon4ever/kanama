package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Node that instances a `MultiMesh` in 2D.
 *
 * Generated from Godot docs: MultiMeshInstance2D
 */
class MultiMeshInstance2D(handle: MemorySegment) : Node2D(handle) {
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

    fun setMultimesh(multimesh: MultiMesh?) {
        ObjectCalls.ptrcallWithObjectArgs(setMultimeshBind, handle, listOf(multimesh?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getMultimesh(): MultiMesh? {
        return MultiMesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMultimeshBind, handle))
    }

    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, handle))
    }

    object Signals {
        const val textureChanged: String = "texture_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): MultiMeshInstance2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): MultiMeshInstance2D? =
            if (handle.address() == 0L) null else MultiMeshInstance2D(handle)

        private const val SET_MULTIMESH_HASH = 2246127404L
        private val setMultimeshBind by lazy {
            ObjectCalls.getMethodBind("MultiMeshInstance2D", "set_multimesh", SET_MULTIMESH_HASH)
        }

        private const val GET_MULTIMESH_HASH = 1385450523L
        private val getMultimeshBind by lazy {
            ObjectCalls.getMethodBind("MultiMeshInstance2D", "get_multimesh", GET_MULTIMESH_HASH)
        }

        private const val SET_TEXTURE_HASH = 4051416890L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("MultiMeshInstance2D", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 3635182373L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("MultiMeshInstance2D", "get_texture", GET_TEXTURE_HASH)
        }
    }
}
