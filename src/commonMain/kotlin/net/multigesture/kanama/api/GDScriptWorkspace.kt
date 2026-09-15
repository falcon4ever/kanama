package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: GDScriptWorkspace
 */
class GDScriptWorkspace(handle: GodotHandle) : RefCounted(handle) {
    fun applyNewSignal(obj: GodotObject, function: String, args: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectStringAndPackedStringListArgs(applyNewSignalBind, segment, obj.segment, function, args)
    }

    fun getFilePath(uri: String): String {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetString(getFilePathBind, segment, uri)
    }

    fun getFileUri(path: String): String {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetString(getFileUriBind, segment, path)
    }

    fun generateScriptApi(path: String): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetDictionary(generateScriptApiBind, segment, path)
    }

    fun didDeleteFiles(params: Map<String, Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithDictionaryArg(didDeleteFilesBind, segment, params)
    }

    fun parseScript(path: String, content: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoStringArgsRetLong(parseScriptBind, segment, path, content)
    }

    fun parseLocalScript(path: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(parseLocalScriptBind, segment, path)
    }

    fun publishDiagnostics(path: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(publishDiagnosticsBind, segment, path)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GDScriptWorkspace? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GDScriptWorkspace? =
            if (handle.address() == 0L) null else GDScriptWorkspace(GodotHandle(handle))

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
