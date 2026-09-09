package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: NavigationPathQueryResult2D
 */
class NavigationPathQueryResult2D(handle: MemorySegment) : RefCounted(handle) {
    val path: List<Vector2>
        @JvmName("pathProperty")
        get() = getPath()

    val pathTypes: List<Int>
        @JvmName("pathTypesProperty")
        get() = getPathTypes()

    var pathLength: Double
        @JvmName("pathLengthProperty")
        get() = getPathLength()
        @JvmName("setPathLengthProperty")
        set(value) = setPathLength(value)

    fun getPath(): List<Vector2> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getPathBind, handle)
    }

    fun getPathTypes(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getPathTypesBind, handle)
    }

    fun setPathLength(length: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setPathLengthBind, handle, length)
    }

    fun getPathLength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getPathLengthBind, handle)
    }

    fun reset() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(resetBind, handle)
    }

    companion object {
        const val PATH_SEGMENT_TYPE_REGION: Long = 0L
        const val PATH_SEGMENT_TYPE_LINK: Long = 1L

        fun fromHandle(handle: MemorySegment): NavigationPathQueryResult2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): NavigationPathQueryResult2D? =
            if (handle.address() == 0L) null else NavigationPathQueryResult2D(handle)

        private const val GET_PATH_HASH = 2961356807L
        private val getPathBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "get_path", GET_PATH_HASH)
        }

        private const val GET_PATH_TYPES_HASH = 1930428628L
        private val getPathTypesBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "get_path_types", GET_PATH_TYPES_HASH)
        }

        private const val SET_PATH_LENGTH_HASH = 373806689L
        private val setPathLengthBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "set_path_length", SET_PATH_LENGTH_HASH)
        }

        private const val GET_PATH_LENGTH_HASH = 1740695150L
        private val getPathLengthBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "get_path_length", GET_PATH_LENGTH_HASH)
        }

        private const val RESET_HASH = 3218959716L
        private val resetBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "reset", RESET_HASH)
        }
    }
}
