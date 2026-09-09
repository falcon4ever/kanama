package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Rect2

/**
 * Generated from Godot docs: NavigationMeshSourceGeometryData2D
 */
class NavigationMeshSourceGeometryData2D(handle: MemorySegment) : Resource(handle) {
    val projectedObstructions: List<Any?>
        @JvmName("projectedObstructionsProperty")
        get() = getProjectedObstructions()

    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun hasData(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(hasDataBind, handle)
    }

    fun merge(otherGeometry: NavigationMeshSourceGeometryData2D?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(mergeBind, handle, listOf(otherGeometry?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun clearProjectedObstructions() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearProjectedObstructionsBind, handle)
    }

    fun getProjectedObstructions(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(getProjectedObstructionsBind, handle)
    }

    fun getBounds(): Rect2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRect2(getBoundsBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): NavigationMeshSourceGeometryData2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): NavigationMeshSourceGeometryData2D? =
            if (handle.address() == 0L) null else NavigationMeshSourceGeometryData2D(handle)

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "clear", CLEAR_HASH)
        }

        private const val HAS_DATA_HASH = 2240911060L
        private val hasDataBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "has_data", HAS_DATA_HASH)
        }

        private const val MERGE_HASH = 742424872L
        private val mergeBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "merge", MERGE_HASH)
        }

        private const val CLEAR_PROJECTED_OBSTRUCTIONS_HASH = 3218959716L
        private val clearProjectedObstructionsBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "clear_projected_obstructions", CLEAR_PROJECTED_OBSTRUCTIONS_HASH)
        }

        private const val GET_PROJECTED_OBSTRUCTIONS_HASH = 3995934104L
        private val getProjectedObstructionsBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "get_projected_obstructions", GET_PROJECTED_OBSTRUCTIONS_HASH)
        }

        private const val GET_BOUNDS_HASH = 3248174L
        private val getBoundsBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "get_bounds", GET_BOUNDS_HASH)
        }
    }
}
