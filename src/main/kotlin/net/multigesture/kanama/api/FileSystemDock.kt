package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Godot editor's dock for managing files in the project.
 *
 * Generated from Godot docs: FileSystemDock
 */
class FileSystemDock(handle: MemorySegment) : EditorDock(handle) {
    fun navigateToPath(path: String) {
        ObjectCalls.ptrcallWithStringArg(navigateToPathBind, handle, path)
    }

    fun addResourceTooltipPlugin(plugin: EditorResourceTooltipPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(addResourceTooltipPluginBind, handle, listOf(plugin?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun removeResourceTooltipPlugin(plugin: EditorResourceTooltipPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(removeResourceTooltipPluginBind, handle, listOf(plugin?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    object Signals {
        const val inherit: String = "inherit"
        const val instantiate: String = "instantiate"
        const val resourceRemoved: String = "resource_removed"
        const val fileRemoved: String = "file_removed"
        const val folderRemoved: String = "folder_removed"
        const val filesMoved: String = "files_moved"
        const val folderMoved: String = "folder_moved"
        const val folderColorChanged: String = "folder_color_changed"
        const val selectionChanged: String = "selection_changed"
        const val displayModeChanged: String = "display_mode_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): FileSystemDock? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): FileSystemDock? =
            if (handle.address() == 0L) null else FileSystemDock(handle)

        private const val NAVIGATE_TO_PATH_HASH = 83702148L
        private val navigateToPathBind by lazy {
            ObjectCalls.getMethodBind("FileSystemDock", "navigate_to_path", NAVIGATE_TO_PATH_HASH)
        }

        private const val ADD_RESOURCE_TOOLTIP_PLUGIN_HASH = 2258356838L
        private val addResourceTooltipPluginBind by lazy {
            ObjectCalls.getMethodBind("FileSystemDock", "add_resource_tooltip_plugin", ADD_RESOURCE_TOOLTIP_PLUGIN_HASH)
        }

        private const val REMOVE_RESOURCE_TOOLTIP_PLUGIN_HASH = 2258356838L
        private val removeResourceTooltipPluginBind by lazy {
            ObjectCalls.getMethodBind("FileSystemDock", "remove_resource_tooltip_plugin", REMOVE_RESOURCE_TOOLTIP_PLUGIN_HASH)
        }
    }
}
