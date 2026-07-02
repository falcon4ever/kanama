package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Resource filesystem, as the editor sees it.
 *
 * Generated from Godot docs: EditorFileSystem
 */
class EditorFileSystem(handle: MemorySegment) : Node(handle) {
    fun getFilesystem(): EditorFileSystemDirectory? {
        return EditorFileSystemDirectory.wrap(ObjectCalls.ptrcallNoArgsRetObject(getFilesystemBind, handle))
    }

    fun isScanning(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isScanningBind, handle)
    }

    fun isImporting(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isImportingBind, handle)
    }

    fun getScanningProgress(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getScanningProgressBind, handle)
    }

    /**
     * Scan the filesystem for changes.
     *
     * Generated from Godot docs: EditorFileSystem.scan
     */
    fun scan() {
        ObjectCalls.ptrcallNoArgs(scanBind, handle)
    }

    fun scanSources() {
        ObjectCalls.ptrcallNoArgs(scanSourcesBind, handle)
    }

    fun updateFile(path: String) {
        ObjectCalls.ptrcallWithStringArg(updateFileBind, handle, path)
    }

    fun getFilesystemPath(path: String): EditorFileSystemDirectory? {
        return EditorFileSystemDirectory.wrap(ObjectCalls.ptrcallWithStringArgRetObject(getFilesystemPathBind, handle, path))
    }

    fun getFileType(path: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(getFileTypeBind, handle, path)
    }

    fun reimportFiles(files: List<String>) {
        ObjectCalls.ptrcallWithPackedStringListArg(reimportFilesBind, handle, files)
    }

    object Signals {
        const val filesystemChanged: String = "filesystem_changed"
        const val scriptClassesUpdated: String = "script_classes_updated"
        const val sourcesChanged: String = "sources_changed"
        const val resourcesReimporting: String = "resources_reimporting"
        const val resourcesReimported: String = "resources_reimported"
        const val resourcesReload: String = "resources_reload"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorFileSystem? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorFileSystem? =
            if (handle.address() == 0L) null else EditorFileSystem(handle)

        private const val GET_FILESYSTEM_HASH = 842323275L
        private val getFilesystemBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystem", "get_filesystem", GET_FILESYSTEM_HASH)
        }

        private const val IS_SCANNING_HASH = 36873697L
        private val isScanningBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystem", "is_scanning", IS_SCANNING_HASH)
        }

        private const val IS_IMPORTING_HASH = 36873697L
        private val isImportingBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystem", "is_importing", IS_IMPORTING_HASH)
        }

        private const val GET_SCANNING_PROGRESS_HASH = 1740695150L
        private val getScanningProgressBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystem", "get_scanning_progress", GET_SCANNING_PROGRESS_HASH)
        }

        private const val SCAN_HASH = 3218959716L
        private val scanBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystem", "scan", SCAN_HASH)
        }

        private const val SCAN_SOURCES_HASH = 3218959716L
        private val scanSourcesBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystem", "scan_sources", SCAN_SOURCES_HASH)
        }

        private const val UPDATE_FILE_HASH = 83702148L
        private val updateFileBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystem", "update_file", UPDATE_FILE_HASH)
        }

        private const val GET_FILESYSTEM_PATH_HASH = 3188521125L
        private val getFilesystemPathBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystem", "get_filesystem_path", GET_FILESYSTEM_PATH_HASH)
        }

        private const val GET_FILE_TYPE_HASH = 3135753539L
        private val getFileTypeBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystem", "get_file_type", GET_FILE_TYPE_HASH)
        }

        private const val REIMPORT_FILES_HASH = 4015028928L
        private val reimportFilesBind by lazy {
            ObjectCalls.getMethodBind("EditorFileSystem", "reimport_files", REIMPORT_FILES_HASH)
        }
    }
}
