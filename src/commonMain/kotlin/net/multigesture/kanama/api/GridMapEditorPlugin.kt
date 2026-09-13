package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Vector3i

/**
 * Generated from Godot docs: GridMapEditorPlugin
 */
class GridMapEditorPlugin(handle: GodotHandle) : EditorPlugin(handle) {
    fun getCurrentGridMap(): GridMap? {
        return GridMap.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCurrentGridMapBind, segment))
    }

    fun setSelection(begin: Vector3i, end: Vector3i) {
        ObjectCalls.ptrcallWithTwoVector3iArgs(setSelectionBind, segment, begin, end)
    }

    fun clearSelection() {
        ObjectCalls.ptrcallNoArgs(clearSelectionBind, segment)
    }

    fun getSelection(): AABB {
        return ObjectCalls.ptrcallNoArgsRetAABB(getSelectionBind, segment)
    }

    fun hasSelection(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasSelectionBind, segment)
    }

    fun getSelectedCells(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getSelectedCellsBind, segment)
    }

    fun setSelectedPaletteItem(item: Int) {
        ObjectCalls.ptrcallWithIntArg(setSelectedPaletteItemBind, segment, item)
    }

    fun getSelectedPaletteItem(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSelectedPaletteItemBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GridMapEditorPlugin? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): GridMapEditorPlugin? =
            if (handle.address() == 0L) null else GridMapEditorPlugin(GodotHandle(handle))

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
