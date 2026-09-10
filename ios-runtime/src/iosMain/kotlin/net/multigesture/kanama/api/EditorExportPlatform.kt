package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: EditorExportPlatform
 */
open class EditorExportPlatform(handle: MemorySegment) : RefCounted(handle) {
    fun getOsName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getOsNameBind, handle)
    }

    fun createPreset(): EditorExportPreset? {
        checkOpen()
        return EditorExportPreset.wrap(ObjectCalls.ptrcallNoArgsRetObject(createPresetBind, handle))
    }

    fun findExportTemplate(templateFileName: String): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetDictionary(findExportTemplateBind, handle, templateFileName)
    }

    fun getCurrentPresets(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(getCurrentPresetsBind, handle)
    }

    fun savePack(preset: EditorExportPreset?, debug: Boolean, path: String, embed: Boolean = false): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectBoolStringBoolArgsRetDictionary(savePackBind, handle, preset?.requireOpenHandle() ?: MemorySegment.NULL, debug, path, embed)
    }

    fun saveZip(preset: EditorExportPreset?, debug: Boolean, path: String): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectBoolStringArgsRetDictionary(saveZipBind, handle, preset?.requireOpenHandle() ?: MemorySegment.NULL, debug, path)
    }

    fun savePackPatch(preset: EditorExportPreset?, debug: Boolean, path: String): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectBoolStringArgsRetDictionary(savePackPatchBind, handle, preset?.requireOpenHandle() ?: MemorySegment.NULL, debug, path)
    }

    fun saveZipPatch(preset: EditorExportPreset?, debug: Boolean, path: String): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectBoolStringArgsRetDictionary(saveZipPatchBind, handle, preset?.requireOpenHandle() ?: MemorySegment.NULL, debug, path)
    }

    fun exportProjectFiles(preset: EditorExportPreset?, debug: Boolean, saveCb: GodotCallable, sharedCb: GodotCallable): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectBoolTwoCallableArgsRetLong(exportProjectFilesBind, handle, preset?.requireOpenHandle() ?: MemorySegment.NULL, debug, saveCb.target.handle, saveCb.method, sharedCb.target.handle, sharedCb.method)
    }

    fun exportProject(preset: EditorExportPreset?, debug: Boolean, path: String, flags: Long = 0L, notify: Boolean = true): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectBoolStringLongBoolArgsRetLong(exportProjectBind, handle, preset?.requireOpenHandle() ?: MemorySegment.NULL, debug, path, flags, notify)
    }

    fun exportPack(preset: EditorExportPreset?, debug: Boolean, path: String, flags: Long = 0L): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectBoolStringLongArgsRetLong(exportPackBind, handle, preset?.requireOpenHandle() ?: MemorySegment.NULL, debug, path, flags)
    }

    fun exportZip(preset: EditorExportPreset?, debug: Boolean, path: String, flags: Long = 0L): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectBoolStringLongArgsRetLong(exportZipBind, handle, preset?.requireOpenHandle() ?: MemorySegment.NULL, debug, path, flags)
    }

    fun clearMessages() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearMessagesBind, handle)
    }

    fun addMessage(type: Long, category: String, message: String) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndTwoStringArgs(addMessageBind, handle, type, category, message)
    }

    fun getMessageCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getMessageCountBind, handle)
    }

    fun getMessageType(index: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetLong(getMessageTypeBind, handle, index)
    }

    fun getMessageCategory(index: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetString(getMessageCategoryBind, handle, index)
    }

    fun getMessageText(index: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetString(getMessageTextBind, handle, index)
    }

    fun getWorstMessageType(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getWorstMessageTypeBind, handle)
    }

    fun getInternalExportFiles(preset: EditorExportPreset?, debug: Boolean): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectAndBoolArgRetDictionary(getInternalExportFilesBind, handle, preset?.requireOpenHandle() ?: MemorySegment.NULL, debug)
    }

    companion object {
        const val EXPORT_MESSAGE_NONE: Long = 0L
        const val EXPORT_MESSAGE_INFO: Long = 1L
        const val EXPORT_MESSAGE_WARNING: Long = 2L
        const val EXPORT_MESSAGE_ERROR: Long = 3L
        const val DEBUG_FLAG_DUMB_CLIENT: Long = 1L
        const val DEBUG_FLAG_REMOTE_DEBUG: Long = 2L
        const val DEBUG_FLAG_REMOTE_DEBUG_LOCALHOST: Long = 4L
        const val DEBUG_FLAG_VIEW_COLLISIONS: Long = 8L
        const val DEBUG_FLAG_VIEW_NAVIGATION: Long = 16L

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

        private const val GET_INTERNAL_EXPORT_FILES_HASH = 89550086L
        private val getInternalExportFilesBind by lazy {
            ObjectCalls.getMethodBind("EditorExportPlatform", "get_internal_export_files", GET_INTERNAL_EXPORT_FILES_HASH)
        }
    }
}
