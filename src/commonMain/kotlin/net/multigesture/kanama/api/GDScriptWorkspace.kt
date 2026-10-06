package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: GDScriptWorkspace
 */
class GDScriptWorkspace(handle: GodotHandle) : RefCounted(handle) {
    fun applyNewSignal(obj: GodotObject, function: String, args: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectStringAndPackedStringListArgs(Binds.applyNewSignalBind, segment, obj.segment, function, args)
    }

    fun getFilePath(uri: String): String {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetString(Binds.getFilePathBind, segment, uri)
    }

    fun getFileUri(path: String): String {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetString(Binds.getFileUriBind, segment, path)
    }

    fun generateScriptApi(path: String): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetDictionary(Binds.generateScriptApiBind, segment, path)
    }

    fun didDeleteFiles(params: Map<String, Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithDictionaryArg(Binds.didDeleteFilesBind, segment, params)
    }

    fun parseScript(path: String, content: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithTwoStringArgsRetLong(Binds.parseScriptBind, segment, path, content))
    }

    fun parseLocalScript(path: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.parseLocalScriptBind, segment, path))
    }

    fun publishDiagnostics(path: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.publishDiagnosticsBind, segment, path)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GDScriptWorkspace? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GDScriptWorkspace? =
            if (handle.address() == 0L) null else RefCounted.owned(GDScriptWorkspace(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GDScriptWorkspace? =
            if (handle.address() == 0L) null else GDScriptWorkspace(GodotHandle(handle))
    }

    private object Binds {
        private const val APPLY_NEW_SIGNAL_HASH = 3682583557L
        @JvmField
        val applyNewSignalBind =
            ObjectCalls.getMethodBind("GDScriptWorkspace", "apply_new_signal", APPLY_NEW_SIGNAL_HASH)

        private const val GET_FILE_PATH_HASH = 1703090593L
        @JvmField
        val getFilePathBind =
            ObjectCalls.getMethodBind("GDScriptWorkspace", "get_file_path", GET_FILE_PATH_HASH)

        private const val GET_FILE_URI_HASH = 3135753539L
        @JvmField
        val getFileUriBind =
            ObjectCalls.getMethodBind("GDScriptWorkspace", "get_file_uri", GET_FILE_URI_HASH)

        private const val GENERATE_SCRIPT_API_HASH = 2786125124L
        @JvmField
        val generateScriptApiBind =
            ObjectCalls.getMethodBind("GDScriptWorkspace", "generate_script_api", GENERATE_SCRIPT_API_HASH)

        private const val DIDDELETEFILES_HASH = 4155329257L
        @JvmField
        val didDeleteFilesBind =
            ObjectCalls.getMethodBind("GDScriptWorkspace", "didDeleteFiles", DIDDELETEFILES_HASH)

        private const val PARSE_SCRIPT_HASH = 852856452L
        @JvmField
        val parseScriptBind =
            ObjectCalls.getMethodBind("GDScriptWorkspace", "parse_script", PARSE_SCRIPT_HASH)

        private const val PARSE_LOCAL_SCRIPT_HASH = 166001499L
        @JvmField
        val parseLocalScriptBind =
            ObjectCalls.getMethodBind("GDScriptWorkspace", "parse_local_script", PARSE_LOCAL_SCRIPT_HASH)

        private const val PUBLISH_DIAGNOSTICS_HASH = 83702148L
        @JvmField
        val publishDiagnosticsBind =
            ObjectCalls.getMethodBind("GDScriptWorkspace", "publish_diagnostics", PUBLISH_DIAGNOSTICS_HASH)
    }
}
