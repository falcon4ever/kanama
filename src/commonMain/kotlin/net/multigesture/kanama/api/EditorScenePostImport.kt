package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Post-processes scenes after import.
 *
 * Generated from Godot docs: EditorScenePostImport
 */
class EditorScenePostImport(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Returns the source file path which got imported (e.g. `res://scene.dae`).
     *
     * Generated from Godot docs: EditorScenePostImport.get_source_file
     */
    fun getSourceFile(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getSourceFileBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorScenePostImport? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): EditorScenePostImport? =
            if (handle.address() == 0L) null else EditorScenePostImport(GodotHandle(handle))

        private const val GET_SOURCE_FILE_HASH = 201670096L
        private val getSourceFileBind by lazy {
            ObjectCalls.getMethodBind("EditorScenePostImport", "get_source_file", GET_SOURCE_FILE_HASH)
        }
    }
}
