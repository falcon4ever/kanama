package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: GDScriptWorkspace
 */
class GDScriptWorkspace(handle: MemorySegment) : RefCounted(handle) {
    fun getFilePath(uri: String): String {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetString(getFilePathBind, handle, uri)
    }

    fun getFileUri(path: String): String {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetString(getFileUriBind, handle, path)
    }

    fun parseScript(path: String, content: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoStringArgsRetLong(parseScriptBind, handle, path, content)
    }

    fun parseLocalScript(path: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(parseLocalScriptBind, handle, path)
    }

    fun publishDiagnostics(path: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(publishDiagnosticsBind, handle, path)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): GDScriptWorkspace? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GDScriptWorkspace? =
            if (handle.address() == 0L) null else GDScriptWorkspace(handle)

        private const val GET_FILE_PATH_HASH = 1703090593L
        private val getFilePathBind by lazy {
            ObjectCalls.getMethodBind("GDScriptWorkspace", "get_file_path", GET_FILE_PATH_HASH)
        }

        private const val GET_FILE_URI_HASH = 3135753539L
        private val getFileUriBind by lazy {
            ObjectCalls.getMethodBind("GDScriptWorkspace", "get_file_uri", GET_FILE_URI_HASH)
        }

        private const val PARSE_SCRIPT_HASH = 852856452L
        private val parseScriptBind by lazy {
            ObjectCalls.getMethodBind("GDScriptWorkspace", "parse_script", PARSE_SCRIPT_HASH)
        }

        private const val PARSE_LOCAL_SCRIPT_HASH = 166001499L
        private val parseLocalScriptBind by lazy {
            ObjectCalls.getMethodBind("GDScriptWorkspace", "parse_local_script", PARSE_LOCAL_SCRIPT_HASH)
        }

        private const val PUBLISH_DIAGNOSTICS_HASH = 83702148L
        private val publishDiagnosticsBind by lazy {
            ObjectCalls.getMethodBind("GDScriptWorkspace", "publish_diagnostics", PUBLISH_DIAGNOSTICS_HASH)
        }
    }
}
