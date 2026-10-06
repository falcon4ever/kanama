package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Godot editor's dock for managing files in the project.
 *
 * Generated from Godot docs: FileSystemDock
 */
class FileSystemDock(handle: GodotHandle) : EditorDock(handle) {
    /**
     * Sets the given `path` as currently selected, ensuring that the selected file/directory is
     * visible.
     *
     * Generated from Godot docs: FileSystemDock.navigate_to_path
     */
    fun navigateToPath(path: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.navigateToPathBind, segment, path)
    }

    /**
     * Registers a new `EditorResourceTooltipPlugin`.
     *
     * Generated from Godot docs: FileSystemDock.add_resource_tooltip_plugin
     */
    fun addResourceTooltipPlugin(plugin: EditorResourceTooltipPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.addResourceTooltipPluginBind, segment, listOf(plugin?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Removes an `EditorResourceTooltipPlugin`. Fails if the plugin wasn't previously added.
     *
     * Generated from Godot docs: FileSystemDock.remove_resource_tooltip_plugin
     */
    fun removeResourceTooltipPlugin(plugin: EditorResourceTooltipPlugin?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeResourceTooltipPluginBind, segment, listOf(plugin?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /** Signal `inherit(file: String)`; see [TypedSignal]. */
    val inherit: Signal1<String>
        @JvmName("inheritTypedSignal")
        get() = Signal1(this, "inherit", SignalArgType.STRING)

    /** Signal `instantiate(files: PackedStringArray)`; see [TypedSignal]. On iOS a PackedStringArray argument is not delivered yet: a connection reports a script error. */
    val instantiate: Signal1<List<String>>
        @JvmName("instantiateTypedSignal")
        get() = Signal1(this, "instantiate", SignalArgType.valueOf<List<String>>("PackedStringArray", List::class))

    /** Signal `resource_removed(resource: Resource)`; see [TypedSignal]. */
    val resourceRemoved: Signal1<Resource?>
        @JvmName("resourceRemovedTypedSignal")
        get() = Signal1(this, "resource_removed", SignalArgType.nullableObjectOf("Resource") { Resource(it) })

    /** Signal `file_removed(file: String)`; see [TypedSignal]. */
    val fileRemoved: Signal1<String>
        @JvmName("fileRemovedTypedSignal")
        get() = Signal1(this, "file_removed", SignalArgType.STRING)

    /** Signal `folder_removed(folder: String)`; see [TypedSignal]. */
    val folderRemoved: Signal1<String>
        @JvmName("folderRemovedTypedSignal")
        get() = Signal1(this, "folder_removed", SignalArgType.STRING)

    /** Signal `files_moved(old_file: String, new_file: String)`; see [TypedSignal]. */
    val filesMoved: Signal2<String, String>
        @JvmName("filesMovedTypedSignal")
        get() = Signal2(this, "files_moved", SignalArgType.STRING, SignalArgType.STRING)

    /** Signal `folder_moved(old_folder: String, new_folder: String)`; see [TypedSignal]. */
    val folderMoved: Signal2<String, String>
        @JvmName("folderMovedTypedSignal")
        get() = Signal2(this, "folder_moved", SignalArgType.STRING, SignalArgType.STRING)

    /** Signal `folder_color_changed()`; see [TypedSignal]. */
    val folderColorChanged: Signal0
        @JvmName("folderColorChangedTypedSignal")
        get() = Signal0(this, "folder_color_changed")

    /** Signal `selection_changed()`; see [TypedSignal]. */
    val selectionChanged: Signal0
        @JvmName("selectionChangedTypedSignal")
        get() = Signal0(this, "selection_changed")

    /** Signal `display_mode_changed()`; see [TypedSignal]. */
    val displayModeChanged: Signal0
        @JvmName("displayModeChangedTypedSignal")
        get() = Signal0(this, "display_mode_changed")

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
        fun fromHandle(handle: GodotHandle): FileSystemDock? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): FileSystemDock? =
            if (handle.address() == 0L) null else FileSystemDock(GodotHandle(handle))
    }

    private object Binds {
        private const val NAVIGATE_TO_PATH_HASH = 83702148L
        @JvmField
        val navigateToPathBind =
            ObjectCalls.getMethodBind("FileSystemDock", "navigate_to_path", NAVIGATE_TO_PATH_HASH)

        private const val ADD_RESOURCE_TOOLTIP_PLUGIN_HASH = 2258356838L
        @JvmField
        val addResourceTooltipPluginBind =
            ObjectCalls.getMethodBind("FileSystemDock", "add_resource_tooltip_plugin", ADD_RESOURCE_TOOLTIP_PLUGIN_HASH)

        private const val REMOVE_RESOURCE_TOOLTIP_PLUGIN_HASH = 2258356838L
        @JvmField
        val removeResourceTooltipPluginBind =
            ObjectCalls.getMethodBind("FileSystemDock", "remove_resource_tooltip_plugin", REMOVE_RESOURCE_TOOLTIP_PLUGIN_HASH)
    }
}
