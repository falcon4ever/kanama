package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Post-processes scenes after import.
 *
 * Generated from Godot docs: EditorScenePostImport
 */
class EditorScenePostImport(handle: MemorySegment) : RefCounted(handle) {
    fun getSourceFile(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSourceFileBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorScenePostImport? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorScenePostImport? =
            if (handle.address() == 0L) null else EditorScenePostImport(handle)

        private const val GET_SOURCE_FILE_HASH = 201670096L
        private val getSourceFileBind by lazy {
            ObjectCalls.getMethodBind("EditorScenePostImport", "get_source_file", GET_SOURCE_FILE_HASH)
        }
    }
}
