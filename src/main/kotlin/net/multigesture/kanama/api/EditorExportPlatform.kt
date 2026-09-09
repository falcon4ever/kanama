package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import java.lang.foreign.MemorySegment

/**
 * Identifies a supported export platform, and internally provides the functionality of exporting
 * to that platform.
 *
 * Generated from Godot docs: EditorExportPlatform
 */
open class EditorExportPlatform(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Returns the name of the export operating system handled by this `EditorExportPlatform` class, as
     * a friendly string. Possible return values are `Windows`, `Linux`, `macOS`, `Android`, `iOS`, and
     * `Web`.
     *
     * Generated from Godot docs: EditorExportPlatform.get_os_name
     */
    fun getOsName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getOsNameBind, handle)
    }

    /**
     * Create a new preset for this platform.
     *
     * Generated from Godot docs: EditorExportPlatform.create_preset
     */
    fun createPreset(): EditorExportPreset? {
        checkOpen()
        return EditorExportPreset.wrap(ObjectCalls.ptrcallNoArgsRetObject(createPresetBind, handle))
    }

    /**
     * Locates export template for the platform, and returns `Dictionary` with the following keys:
     * `path: String` and `error: String`. This method is provided for convenience and custom export
     * platforms aren't required to use it or keep export templates stored in the same way official
     * templates are.
     *
     * Generated from Godot docs: EditorExportPlatform.find_export_template
     */
    fun findExportTemplate(templateFileName: String): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetDictionary(findExportTemplateBind, handle, templateFileName)
    }

    /**
     * Returns array of `EditorExportPreset`s for this platform.
     *
     * Generated from Godot docs: EditorExportPlatform.get_current_presets
     */
    fun getCurrentPresets(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(getCurrentPresetsBind, handle)
    }

    /**
     * Saves PCK archive and returns `Dictionary` with the following keys: `result: Error`, `so_files:
     * Array` (array of the shared/static objects which contains dictionaries with the following keys:
     * `path: String`, `tags: PackedStringArray`, and `target_folder: String`). If `embed` is `true`,
     * PCK content is appended to the end of `path` file and return `Dictionary` additionally include
     * following keys: `embedded_start: int` (embedded PCK offset) and `embedded_size: int` (embedded
     * PCK size).
     *
     * Generated from Godot docs: EditorExportPlatform.save_pack
     */
    fun savePack(preset: EditorExportPreset?, debug: Boolean, path: String, embed: Boolean = false): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectBoolStringBoolArgsRetDictionary(savePackBind, handle, preset?.requireOpenHandle() ?: MemorySegment.NULL, debug, path, embed)
    }

    /**
     * Saves ZIP archive and returns `Dictionary` with the following keys: `result: Error`, `so_files:
     * Array` (array of the shared/static objects which contains dictionaries with the following keys:
     * `path: String`, `tags: PackedStringArray`, and `target_folder: String`).
     *
     * Generated from Godot docs: EditorExportPlatform.save_zip
     */
    fun saveZip(preset: EditorExportPreset?, debug: Boolean, path: String): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectBoolStringArgsRetDictionary(saveZipBind, handle, preset?.requireOpenHandle() ?: MemorySegment.NULL, debug, path)
    }

    /**
     * Saves patch PCK archive and returns `Dictionary` with the following keys: `result: Error`,
     * `so_files: Array` (array of the shared/static objects which contains dictionaries with the
     * following keys: `path: String`, `tags: PackedStringArray`, and `target_folder: String`).
     *
     * Generated from Godot docs: EditorExportPlatform.save_pack_patch
     */
    fun savePackPatch(preset: EditorExportPreset?, debug: Boolean, path: String): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectBoolStringArgsRetDictionary(savePackPatchBind, handle, preset?.requireOpenHandle() ?: MemorySegment.NULL, debug, path)
    }

    /**
     * Saves patch ZIP archive and returns `Dictionary` with the following keys: `result: Error`,
     * `so_files: Array` (array of the shared/static objects which contains dictionaries with the
     * following keys: `path: String`, `tags: PackedStringArray`, and `target_folder: String`).
     *
     * Generated from Godot docs: EditorExportPlatform.save_zip_patch
     */
    fun saveZipPatch(preset: EditorExportPreset?, debug: Boolean, path: String): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectBoolStringArgsRetDictionary(saveZipPatchBind, handle, preset?.requireOpenHandle() ?: MemorySegment.NULL, debug, path)
    }

    /**
     * Generates array of command line arguments for the default export templates for the debug flags
     * and editor settings.
     *
     * Generated from Godot docs: EditorExportPlatform.gen_export_flags
     */
    fun genExportFlags(flags: Long): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetPackedStringList(genExportFlagsBind, handle, flags)
    }

    /**
     * Exports project files for the specified preset. This method can be used to implement custom
     * export format, other than PCK and ZIP. One of the callbacks is called for each exported file.
     * `save_cb` is called for all exported files and have the following arguments: `file_path:
     * String`, `file_data: PackedByteArray`, `file_index: int`, `file_count: int`,
     * `encryption_include_filters: PackedStringArray`, `encryption_exclude_filters:
     * PackedStringArray`, `encryption_key: PackedByteArray`. `shared_cb` is called for exported native
     * shared/static libraries and have the following arguments: `file_path: String`, `tags:
     * PackedStringArray`, `target_folder: String`. Note: `file_index` and `file_count` are intended
     * for progress tracking only and aren't necessarily unique and precise.
     *
     * Generated from Godot docs: EditorExportPlatform.export_project_files
     */
    fun exportProjectFiles(preset: EditorExportPreset?, debug: Boolean, saveCb: GodotCallable, sharedCb: GodotCallable): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectBoolTwoCallableArgsRetLong(exportProjectFilesBind, handle, preset?.requireOpenHandle() ?: MemorySegment.NULL, debug, saveCb.target.handle, saveCb.method, sharedCb.target.handle, sharedCb.method)
    }

    /**
     * Creates a full project at `path` for the specified `preset`. If `notify` is `true`, plugins
     * using `EditorExportPlugin._export_begin` will be called during the process.
     *
     * Generated from Godot docs: EditorExportPlatform.export_project
     */
    fun exportProject(preset: EditorExportPreset?, debug: Boolean, path: String, flags: Long = 0L, notify: Boolean = true): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectBoolStringLongBoolArgsRetLong(exportProjectBind, handle, preset?.requireOpenHandle() ?: MemorySegment.NULL, debug, path, flags, notify)
    }

    /**
     * Creates a PCK archive at `path` for the specified `preset`.
     *
     * Generated from Godot docs: EditorExportPlatform.export_pack
     */
    fun exportPack(preset: EditorExportPreset?, debug: Boolean, path: String, flags: Long = 0L): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectBoolStringLongArgsRetLong(exportPackBind, handle, preset?.requireOpenHandle() ?: MemorySegment.NULL, debug, path, flags)
    }

    /**
     * Create a ZIP archive at `path` for the specified `preset`.
     *
     * Generated from Godot docs: EditorExportPlatform.export_zip
     */
    fun exportZip(preset: EditorExportPreset?, debug: Boolean, path: String, flags: Long = 0L): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectBoolStringLongArgsRetLong(exportZipBind, handle, preset?.requireOpenHandle() ?: MemorySegment.NULL, debug, path, flags)
    }

    /**
     * Creates a patch PCK archive at `path` for the specified `preset`, containing only the files that
     * have changed since the last patch. Note: `patches` is an optional override of the set of patches
     * defined in the export preset. When empty the patches defined in the export preset will be used
     * instead.
     *
     * Generated from Godot docs: EditorExportPlatform.export_pack_patch
     */
    fun exportPackPatch(preset: EditorExportPreset?, debug: Boolean, path: String, patches: List<String>, flags: Long = 0L): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectBoolStringPackedStringListLongArgsRetLong(exportPackPatchBind, handle, preset?.requireOpenHandle() ?: MemorySegment.NULL, debug, path, patches, flags)
    }

    /**
     * Create a patch ZIP archive at `path` for the specified `preset`, containing only the files that
     * have changed since the last patch. Note: `patches` is an optional override of the set of patches
     * defined in the export preset. When empty the patches defined in the export preset will be used
     * instead.
     *
     * Generated from Godot docs: EditorExportPlatform.export_zip_patch
     */
    fun exportZipPatch(preset: EditorExportPreset?, debug: Boolean, path: String, patches: List<String>, flags: Long = 0L): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectBoolStringPackedStringListLongArgsRetLong(exportZipPatchBind, handle, preset?.requireOpenHandle() ?: MemorySegment.NULL, debug, path, patches, flags)
    }

    /**
     * Clears the export log.
     *
     * Generated from Godot docs: EditorExportPlatform.clear_messages
     */
    fun clearMessages() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearMessagesBind, handle)
    }

    /**
     * Adds a message to the export log that will be displayed when exporting ends.
     *
     * Generated from Godot docs: EditorExportPlatform.add_message
     */
    fun addMessage(type: Long, category: String, message: String) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndTwoStringArgs(addMessageBind, handle, type, category, message)
    }

    /**
     * Returns the number of messages in the export log.
     *
     * Generated from Godot docs: EditorExportPlatform.get_message_count
     */
    fun getMessageCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getMessageCountBind, handle)
    }

    /**
     * Returns the type for the message with the given `index`.
     *
     * Generated from Godot docs: EditorExportPlatform.get_message_type
     */
    fun getMessageType(index: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetLong(getMessageTypeBind, handle, index)
    }

    /**
     * Returns the message category for the message with the given `index`.
     *
     * Generated from Godot docs: EditorExportPlatform.get_message_category
     */
    fun getMessageCategory(index: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetString(getMessageCategoryBind, handle, index)
    }

    /**
     * Returns the text for the message with the given `index`.
     *
     * Generated from Godot docs: EditorExportPlatform.get_message_text
     */
    fun getMessageText(index: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetString(getMessageTextBind, handle, index)
    }

    /**
     * Returns most severe message type currently present in the export log.
     *
     * Generated from Godot docs: EditorExportPlatform.get_worst_message_type
     */
    fun getWorstMessageType(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getWorstMessageTypeBind, handle)
    }

    /**
     * Executes specified command on the remote host via SSH protocol and returns command output in the
     * `output`.
     *
     * Generated from Godot docs: EditorExportPlatform.ssh_run_on_remote
     */
    fun sshRunOnRemote(host: String, port: String, sshArg: List<String>, cmdArgs: String, output: List<Any?> = emptyList(), portFwd: Int = -1): Long {
        return ObjectCalls.ptrcallWithTwoStringPackedStringListStringArrayIntArgsRetLong(sshRunOnRemoteBind, handle, host, port, sshArg, cmdArgs, output, portFwd)
    }

    /**
     * Executes specified command on the remote host via SSH protocol and returns process ID (on the
     * remote host) without waiting for command to finish.
     *
     * Generated from Godot docs: EditorExportPlatform.ssh_run_on_remote_no_wait
     */
    fun sshRunOnRemoteNoWait(host: String, port: String, sshArgs: List<String>, cmdArgs: String, portFwd: Int = -1): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoStringPackedStringListStringIntArgsRetLong(sshRunOnRemoteNoWaitBind, handle, host, port, sshArgs, cmdArgs, portFwd)
    }

    /**
     * Uploads specified file over SCP protocol to the remote host.
     *
     * Generated from Godot docs: EditorExportPlatform.ssh_push_to_remote
     */
    fun sshPushToRemote(host: String, port: String, scpArgs: List<String>, srcFile: String, dstFile: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoStringPackedStringListAndTwoStringArgsRetLong(sshPushToRemoteBind, handle, host, port, scpArgs, srcFile, dstFile)
    }

    /**
     * Returns additional files that should always be exported regardless of preset configuration, and
     * are not part of the project source. The returned `Dictionary` contains filename keys (`String`)
     * and their corresponding raw data (`PackedByteArray`).
     *
     * Generated from Godot docs: EditorExportPlatform.get_internal_export_files
     */
    fun getInternalExportFiles(preset: EditorExportPreset?, debug: Boolean): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectAndBoolArgRetDictionary(getInternalExportFilesBind, handle, preset?.requireOpenHandle() ?: MemorySegment.NULL, debug)
    }

    companion object {
        /**
         * Returns array of core file names that always should be exported regardless of preset config.
         *
         * Generated from Godot docs: EditorExportPlatform.get_forced_export_files
         */
        fun getForcedExportFiles(preset: EditorExportPreset?): List<String> {
            return ObjectCalls.ptrcallWithObjectArgRetPackedStringList(getForcedExportFilesBind, MemorySegment.NULL, preset?.requireOpenHandle() ?: MemorySegment.NULL)
        }

        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorExportPlatform? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorExportPlatform? =
            if (handle.address() == 0L) null else EditorExportPlatform(handle)

        private const val GET_OS_NAME_HASH = 201670096L
        private val getOsNameBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "get_os_name", GET_OS_NAME_HASH)
        }

        private const val CREATE_PRESET_HASH = 2572397818L
        private val createPresetBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "create_preset", CREATE_PRESET_HASH)
        }

        private const val FIND_EXPORT_TEMPLATE_HASH = 2248993622L
        private val findExportTemplateBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "find_export_template", FIND_EXPORT_TEMPLATE_HASH)
        }

        private const val GET_CURRENT_PRESETS_HASH = 3995934104L
        private val getCurrentPresetsBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "get_current_presets", GET_CURRENT_PRESETS_HASH)
        }

        private const val SAVE_PACK_HASH = 3420080977L
        private val savePackBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "save_pack", SAVE_PACK_HASH)
        }

        private const val SAVE_ZIP_HASH = 1485052307L
        private val saveZipBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "save_zip", SAVE_ZIP_HASH)
        }

        private const val SAVE_PACK_PATCH_HASH = 1485052307L
        private val savePackPatchBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "save_pack_patch", SAVE_PACK_PATCH_HASH)
        }

        private const val SAVE_ZIP_PATCH_HASH = 1485052307L
        private val saveZipPatchBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "save_zip_patch", SAVE_ZIP_PATCH_HASH)
        }

        private const val GEN_EXPORT_FLAGS_HASH = 2976483270L
        private val genExportFlagsBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "gen_export_flags", GEN_EXPORT_FLAGS_HASH)
        }

        private const val EXPORT_PROJECT_FILES_HASH = 1063735070L
        private val exportProjectFilesBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "export_project_files", EXPORT_PROJECT_FILES_HASH)
        }

        private const val EXPORT_PROJECT_HASH = 1201906210L
        private val exportProjectBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "export_project", EXPORT_PROJECT_HASH)
        }

        private const val EXPORT_PACK_HASH = 3879521245L
        private val exportPackBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "export_pack", EXPORT_PACK_HASH)
        }

        private const val EXPORT_ZIP_HASH = 3879521245L
        private val exportZipBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "export_zip", EXPORT_ZIP_HASH)
        }

        private const val EXPORT_PACK_PATCH_HASH = 608021658L
        private val exportPackPatchBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "export_pack_patch", EXPORT_PACK_PATCH_HASH)
        }

        private const val EXPORT_ZIP_PATCH_HASH = 608021658L
        private val exportZipPatchBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "export_zip_patch", EXPORT_ZIP_PATCH_HASH)
        }

        private const val CLEAR_MESSAGES_HASH = 3218959716L
        private val clearMessagesBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "clear_messages", CLEAR_MESSAGES_HASH)
        }

        private const val ADD_MESSAGE_HASH = 782767225L
        private val addMessageBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "add_message", ADD_MESSAGE_HASH)
        }

        private const val GET_MESSAGE_COUNT_HASH = 3905245786L
        private val getMessageCountBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "get_message_count", GET_MESSAGE_COUNT_HASH)
        }

        private const val GET_MESSAGE_TYPE_HASH = 2667287293L
        private val getMessageTypeBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "get_message_type", GET_MESSAGE_TYPE_HASH)
        }

        private const val GET_MESSAGE_CATEGORY_HASH = 844755477L
        private val getMessageCategoryBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "get_message_category", GET_MESSAGE_CATEGORY_HASH)
        }

        private const val GET_MESSAGE_TEXT_HASH = 844755477L
        private val getMessageTextBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "get_message_text", GET_MESSAGE_TEXT_HASH)
        }

        private const val GET_WORST_MESSAGE_TYPE_HASH = 2580557466L
        private val getWorstMessageTypeBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "get_worst_message_type", GET_WORST_MESSAGE_TYPE_HASH)
        }

        private const val SSH_RUN_ON_REMOTE_HASH = 3163734797L
        private val sshRunOnRemoteBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "ssh_run_on_remote", SSH_RUN_ON_REMOTE_HASH)
        }

        private const val SSH_RUN_ON_REMOTE_NO_WAIT_HASH = 3606362233L
        private val sshRunOnRemoteNoWaitBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "ssh_run_on_remote_no_wait", SSH_RUN_ON_REMOTE_NO_WAIT_HASH)
        }

        private const val SSH_PUSH_TO_REMOTE_HASH = 218756989L
        private val sshPushToRemoteBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "ssh_push_to_remote", SSH_PUSH_TO_REMOTE_HASH)
        }

        private const val GET_INTERNAL_EXPORT_FILES_HASH = 89550086L
        private val getInternalExportFilesBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "get_internal_export_files", GET_INTERNAL_EXPORT_FILES_HASH)
        }

        private const val GET_FORCED_EXPORT_FILES_HASH = 1939331020L
        private val getForcedExportFilesBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "get_forced_export_files", GET_FORCED_EXPORT_FILES_HASH)
        }
    }
}
