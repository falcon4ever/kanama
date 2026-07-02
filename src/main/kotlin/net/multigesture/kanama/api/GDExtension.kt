package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A native library for GDExtension.
 *
 * Generated from Godot docs: GDExtension
 */
class GDExtension(handle: MemorySegment) : Resource(handle) {
    fun isLibraryOpen(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLibraryOpenBind, handle)
    }

    fun getMinimumLibraryInitializationLevel(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMinimumLibraryInitializationLevelBind, handle)
    }

    companion object {
        const val INITIALIZATION_LEVEL_CORE: Long = 0L
        const val INITIALIZATION_LEVEL_SERVERS: Long = 1L
        const val INITIALIZATION_LEVEL_SCENE: Long = 2L
        const val INITIALIZATION_LEVEL_EDITOR: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): GDExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GDExtension? =
            if (handle.address() == 0L) null else GDExtension(handle)

        private const val IS_LIBRARY_OPEN_HASH = 36873697L
        private val isLibraryOpenBind by lazy {
            ObjectCalls.getMethodBind("GDExtension", "is_library_open", IS_LIBRARY_OPEN_HASH)
        }

        private const val GET_MINIMUM_LIBRARY_INITIALIZATION_LEVEL_HASH = 964858755L
        private val getMinimumLibraryInitializationLevelBind by lazy {
            ObjectCalls.getMethodBind("GDExtension", "get_minimum_library_initialization_level", GET_MINIMUM_LIBRARY_INITIALIZATION_LEVEL_HASH)
        }
    }
}
