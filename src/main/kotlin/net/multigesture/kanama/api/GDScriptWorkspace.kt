package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: GDScriptWorkspace
 */
class GDScriptWorkspace(handle: MemorySegment) : RefCounted(handle) {
    fun applyNewSignal(obj: GodotObject, function: String, args: List<String>) {
        ObjectCalls.ptrcallWithObjectStringAndPackedStringListArgs(applyNewSignalBind, handle, obj.handle, function, args)
    }

    fun getFilePath(uri: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(getFilePathBind, handle, uri)
    }

    fun getFileUri(path: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(getFileUriBind, handle, path)
    }

    fun generateScriptApi(path: String): Map<String, Any?> {
        return ObjectCalls.ptrcallWithStringArgRetDictionary(generateScriptApiBind, handle, path)
    }

    fun didDeleteFiles(params: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(didDeleteFilesBind, handle, params)
    }

    fun parseScript(path: String, content: String): Long {
        return ObjectCalls.ptrcallWithTwoStringArgsRetLong(parseScriptBind, handle, path, content)
    }

    fun parseLocalScript(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(parseLocalScriptBind, handle, path)
    }

    fun publishDiagnostics(path: String) {
        ObjectCalls.ptrcallWithStringArg(publishDiagnosticsBind, handle, path)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GDScriptWorkspace? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GDScriptWorkspace? =
            if (handle.address() == 0L) null else GDScriptWorkspace(handle)

        private const val APPLY_NEW_SIGNAL_HASH = 3682583557L
        private val applyNewSignalBind by lazy {
            ObjectCalls.getMethodBind("GDScriptWorkspace", "apply_new_signal", APPLY_NEW_SIGNAL_HASH)
        }

        private const val GET_FILE_PATH_HASH = 1703090593L
        private val getFilePathBind by lazy {
            ObjectCalls.getMethodBind("GDScriptWorkspace", "get_file_path", GET_FILE_PATH_HASH)
        }

        private const val GET_FILE_URI_HASH = 3135753539L
        private val getFileUriBind by lazy {
            ObjectCalls.getMethodBind("GDScriptWorkspace", "get_file_uri", GET_FILE_URI_HASH)
        }

        private const val GENERATE_SCRIPT_API_HASH = 2786125124L
        private val generateScriptApiBind by lazy {
            ObjectCalls.getMethodBind("GDScriptWorkspace", "generate_script_api", GENERATE_SCRIPT_API_HASH)
        }

        private const val DIDDELETEFILES_HASH = 4155329257L
        private val didDeleteFilesBind by lazy {
            ObjectCalls.getMethodBind("GDScriptWorkspace", "didDeleteFiles", DIDDELETEFILES_HASH)
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
