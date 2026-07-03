package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Node that instances a `MultiMesh`.
 *
 * Generated from Godot docs: MultiMeshInstance3D
 */
class MultiMeshInstance3D(handle: MemorySegment) : GeometryInstance3D(handle) {
    var multimesh: MultiMesh?
        @JvmName("multimeshProperty")
        get() = getMultimesh()
        @JvmName("setMultimeshProperty")
        set(value) = setMultimesh(value)

    /**
     * The `MultiMesh` resource that will be used and shared among all instances of the
     * `MultiMeshInstance3D`.
     *
     * Generated from Godot docs: MultiMeshInstance3D.set_multimesh
     */
    fun setMultimesh(multimesh: MultiMesh?) {
        ObjectCalls.ptrcallWithObjectArgs(setMultimeshBind, handle, listOf(multimesh?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The `MultiMesh` resource that will be used and shared among all instances of the
     * `MultiMeshInstance3D`.
     *
     * Generated from Godot docs: MultiMeshInstance3D.get_multimesh
     */
    fun getMultimesh(): MultiMesh? {
        return MultiMesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMultimeshBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): MultiMeshInstance3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): MultiMeshInstance3D? =
            if (handle.address() == 0L) null else MultiMeshInstance3D(handle)

        private const val SET_MULTIMESH_HASH = 2246127404L
        private val setMultimeshBind by lazy {
            ObjectCalls.getMethodBind("MultiMeshInstance3D", "set_multimesh", SET_MULTIMESH_HASH)
        }

        private const val GET_MULTIMESH_HASH = 1385450523L
        private val getMultimeshBind by lazy {
            ObjectCalls.getMethodBind("MultiMeshInstance3D", "get_multimesh", GET_MULTIMESH_HASH)
        }
    }
}
