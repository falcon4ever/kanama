package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Vector3i

/**
 * Generated from Godot docs: GridMapEditorPlugin
 */
class GridMapEditorPlugin(handle: MemorySegment) : EditorPlugin(handle) {
    fun getCurrentGridMap(): GridMap? {
        return GridMap.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCurrentGridMapBind, handle))
    }

    fun setSelection(begin: Vector3i, end: Vector3i) {
        ObjectCalls.ptrcallWithTwoVector3iArgs(setSelectionBind, handle, begin, end)
    }

    fun clearSelection() {
        ObjectCalls.ptrcallNoArgs(clearSelectionBind, handle)
    }

    fun getSelection(): AABB {
        return ObjectCalls.ptrcallNoArgsRetAABB(getSelectionBind, handle)
    }

    fun hasSelection(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasSelectionBind, handle)
    }

    fun getSelectedCells(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getSelectedCellsBind, handle)
    }

    fun setSelectedPaletteItem(item: Int) {
        ObjectCalls.ptrcallWithIntArg(setSelectedPaletteItemBind, handle, item)
    }

    fun getSelectedPaletteItem(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSelectedPaletteItemBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GridMapEditorPlugin? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GridMapEditorPlugin? =
            if (handle.address() == 0L) null else GridMapEditorPlugin(handle)

        private const val GET_CURRENT_GRID_MAP_HASH = 1184264483L
        private val getCurrentGridMapBind by lazy {
            ObjectCalls.getMethodBind("GridMapEditorPlugin", "get_current_grid_map", GET_CURRENT_GRID_MAP_HASH)
        }

        private const val SET_SELECTION_HASH = 3659408297L
        private val setSelectionBind by lazy {
            ObjectCalls.getMethodBind("GridMapEditorPlugin", "set_selection", SET_SELECTION_HASH)
        }

        private const val CLEAR_SELECTION_HASH = 3218959716L
        private val clearSelectionBind by lazy {
            ObjectCalls.getMethodBind("GridMapEditorPlugin", "clear_selection", CLEAR_SELECTION_HASH)
        }

        private const val GET_SELECTION_HASH = 1068685055L
        private val getSelectionBind by lazy {
            ObjectCalls.getMethodBind("GridMapEditorPlugin", "get_selection", GET_SELECTION_HASH)
        }

        private const val HAS_SELECTION_HASH = 36873697L
        private val hasSelectionBind by lazy {
            ObjectCalls.getMethodBind("GridMapEditorPlugin", "has_selection", HAS_SELECTION_HASH)
        }

        private const val GET_SELECTED_CELLS_HASH = 3995934104L
        private val getSelectedCellsBind by lazy {
            ObjectCalls.getMethodBind("GridMapEditorPlugin", "get_selected_cells", GET_SELECTED_CELLS_HASH)
        }

        private const val SET_SELECTED_PALETTE_ITEM_HASH = 998575451L
        private val setSelectedPaletteItemBind by lazy {
            ObjectCalls.getMethodBind("GridMapEditorPlugin", "set_selected_palette_item", SET_SELECTED_PALETTE_ITEM_HASH)
        }

        private const val GET_SELECTED_PALETTE_ITEM_HASH = 3905245786L
        private val getSelectedPaletteItemBind by lazy {
            ObjectCalls.getMethodBind("GridMapEditorPlugin", "get_selected_palette_item", GET_SELECTED_PALETTE_ITEM_HASH)
        }
    }
}
