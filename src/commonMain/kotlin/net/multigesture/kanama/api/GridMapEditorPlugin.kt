package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Vector3i

/**
 * Generated from Godot docs: GridMapEditorPlugin
 */
class GridMapEditorPlugin(handle: GodotHandle) : EditorPlugin(handle) {
    fun getCurrentGridMap(): GridMap? {
        return GridMap.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getCurrentGridMapBind, segment))
    }

    fun setSelection(begin: Vector3i, end: Vector3i) {
        ObjectCalls.ptrcallWithTwoVector3iArgs(Binds.setSelectionBind, segment, begin, end)
    }

    fun clearSelection() {
        ObjectCalls.ptrcallNoArgs(Binds.clearSelectionBind, segment)
    }

    fun getSelection(): AABB {
        return ObjectCalls.ptrcallNoArgsRetAABB(Binds.getSelectionBind, segment)
    }

    fun hasSelection(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasSelectionBind, segment)
    }

    fun getSelectedCells(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getSelectedCellsBind, segment)
    }

    fun setSelectedPaletteItem(item: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setSelectedPaletteItemBind, segment, item)
    }

    fun getSelectedPaletteItem(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSelectedPaletteItemBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GridMapEditorPlugin? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GridMapEditorPlugin? =
            if (handle.address() == 0L) null else GridMapEditorPlugin(GodotHandle(handle))
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
