package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment

/**
 * Instance handle returned by DirAccess factory methods.
 */
class DirAccessHandle internal constructor(handle: GodotHandle) : RefCounted(handle) {
    fun fileExists(path: String): Boolean {
        checkOpen()
        return DirAccess.fileExistsHandle(segment, path)
    }

    fun dirExists(path: String): Boolean {
        checkOpen()
        return DirAccess.dirExistsHandle(segment, path)
    }

    fun getCurrentDir(includeDrive: Boolean = true): String {
        checkOpen()
        return DirAccess.getCurrentDirHandle(segment, includeDrive)
    }

    fun getFiles(): List<String> {
        checkOpen()
        return DirAccess.getFilesHandle(segment)
    }

    fun getDirectories(): List<String> {
        checkOpen()
        return DirAccess.getDirectoriesHandle(segment)
    }

    fun createLink(source: String, target: String): Long {
        checkOpen()
        return DirAccess.createLinkHandle(segment, source, target)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): DirAccessHandle? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): DirAccessHandle? =
            if (handle.address() == 0L) null else DirAccessHandle(GodotHandle(handle))
    }
}
