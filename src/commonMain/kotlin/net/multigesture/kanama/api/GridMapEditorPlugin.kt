package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Vector3i

/**
 * Editor for `GridMap` nodes.
 *
 * Generated from Godot docs: GridMapEditorPlugin
 */
class GridMapEditorPlugin(handle: GodotHandle) : EditorPlugin(handle) {
    /**
     * Returns the `GridMap` node currently edited by the grid map editor.
     *
     * Generated from Godot docs: GridMapEditorPlugin.get_current_grid_map
     */
    fun getCurrentGridMap(): GridMap? {
        return GridMap.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getCurrentGridMapBind, segment))
    }

    /**
     * Selects the cells inside the given bounds from `begin` to `end`.
     *
     * Generated from Godot docs: GridMapEditorPlugin.set_selection
     */
    fun setSelection(begin: Vector3i, end: Vector3i) {
        ObjectCalls.ptrcallWithTwoVector3iArgs(Binds.setSelectionBind, segment, begin, end)
    }

    /**
     * Deselects any currently selected cells.
     *
     * Generated from Godot docs: GridMapEditorPlugin.clear_selection
     */
    fun clearSelection() {
        ObjectCalls.ptrcallNoArgs(Binds.clearSelectionBind, segment)
    }

    /**
     * Returns the cell coordinate bounds of the current selection. Use `has_selection` to check if
     * there is an active selection.
     *
     * Generated from Godot docs: GridMapEditorPlugin.get_selection
     */
    fun getSelection(): AABB {
        return ObjectCalls.ptrcallNoArgsRetAABB(Binds.getSelectionBind, segment)
    }

    /**
     * Returns `true` if there are selected cells.
     *
     * Generated from Godot docs: GridMapEditorPlugin.has_selection
     */
    fun hasSelection(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasSelectionBind, segment)
    }

    /**
     * Returns an array of `Vector3i`s with the selected cells' coordinates.
     *
     * Generated from Godot docs: GridMapEditorPlugin.get_selected_cells
     */
    fun getSelectedCells(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getSelectedCellsBind, segment)
    }

    /**
     * Selects the `MeshLibrary` item with the given index in the grid map editor's palette. If a
     * negative index is given, no item will be selected. If a value greater than the last index is
     * given, the last item will be selected. Note: The indices might not be in the same order as they
     * appear in the editor's interface.
     *
     * Generated from Godot docs: GridMapEditorPlugin.set_selected_palette_item
     */
    fun setSelectedPaletteItem(item: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setSelectedPaletteItemBind, segment, item)
    }

    /**
     * Returns the index of the selected `MeshLibrary` item in the grid map editor's palette or `-1` if
     * no item is selected. Note: The indices might not be in the same order as they appear in the
     * editor's interface.
     *
     * Generated from Godot docs: GridMapEditorPlugin.get_selected_palette_item
     */
    fun getSelectedPaletteItem(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSelectedPaletteItemBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GridMapEditorPlugin? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GridMapEditorPlugin? =
            if (handle.address() == 0L) null else GridMapEditorPlugin(GodotHandle(handle))

        /** A new GridMapEditorPlugin, GDScript's `GridMapEditorPlugin.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): GridMapEditorPlugin =
            GridMapEditorPlugin(GodotHandle(requireConstructed(ObjectCalls.constructObject("GridMapEditorPlugin"), "GridMapEditorPlugin")))
    }

    private object Binds {
        private const val GET_CURRENT_GRID_MAP_HASH = 1184264483L
        @JvmField
        val getCurrentGridMapBind =
            ObjectCalls.getMethodBind("GridMapEditorPlugin", "get_current_grid_map", GET_CURRENT_GRID_MAP_HASH)

        private const val SET_SELECTION_HASH = 3659408297L
        @JvmField
        val setSelectionBind =
            ObjectCalls.getMethodBind("GridMapEditorPlugin", "set_selection", SET_SELECTION_HASH)

        private const val CLEAR_SELECTION_HASH = 3218959716L
        @JvmField
        val clearSelectionBind =
            ObjectCalls.getMethodBind("GridMapEditorPlugin", "clear_selection", CLEAR_SELECTION_HASH)

        private const val GET_SELECTION_HASH = 1068685055L
        @JvmField
        val getSelectionBind =
            ObjectCalls.getMethodBind("GridMapEditorPlugin", "get_selection", GET_SELECTION_HASH)

        private const val HAS_SELECTION_HASH = 36873697L
        @JvmField
        val hasSelectionBind =
            ObjectCalls.getMethodBind("GridMapEditorPlugin", "has_selection", HAS_SELECTION_HASH)

        private const val GET_SELECTED_CELLS_HASH = 3995934104L
        @JvmField
        val getSelectedCellsBind =
            ObjectCalls.getMethodBind("GridMapEditorPlugin", "get_selected_cells", GET_SELECTED_CELLS_HASH)

        private const val SET_SELECTED_PALETTE_ITEM_HASH = 998575451L
        @JvmField
        val setSelectedPaletteItemBind =
            ObjectCalls.getMethodBind("GridMapEditorPlugin", "set_selected_palette_item", SET_SELECTED_PALETTE_ITEM_HASH)

        private const val GET_SELECTED_PALETTE_ITEM_HASH = 3905245786L
        @JvmField
        val getSelectedPaletteItemBind =
            ObjectCalls.getMethodBind("GridMapEditorPlugin", "get_selected_palette_item", GET_SELECTED_PALETTE_ITEM_HASH)
    }
}
