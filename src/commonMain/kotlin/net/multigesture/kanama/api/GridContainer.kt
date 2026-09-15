package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * A container that arranges its child controls in a grid layout.
 *
 * Generated from Godot docs: GridContainer
 */
class GridContainer(handle: GodotHandle) : Container(handle) {
    var columns: Int
        @JvmName("columnsProperty")
        get() = getColumns()
        @JvmName("setColumnsProperty")
        set(value) = setColumns(value)

    /**
     * The number of columns in the `GridContainer`. If modified, `GridContainer` reorders its
     * Control-derived children to accommodate the new layout.
     *
     * Generated from Godot docs: GridContainer.set_columns
     */
    fun setColumns(columns: Int) {
        ObjectCalls.ptrcallWithIntArg(setColumnsBind, segment, columns)
    }

    /**
     * The number of columns in the `GridContainer`. If modified, `GridContainer` reorders its
     * Control-derived children to accommodate the new layout.
     *
     * Generated from Godot docs: GridContainer.get_columns
     */
    fun getColumns(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getColumnsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GridContainer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GridContainer? =
            if (handle.address() == 0L) null else GridContainer(GodotHandle(handle))

        private const val SET_COLUMNS_HASH = 1286410249L
        private val setColumnsBind by lazy {
            ObjectCalls.getMethodBind("GridContainer", "set_columns", SET_COLUMNS_HASH)
        }

        private const val GET_COLUMNS_HASH = 3905245786L
        private val getColumnsBind by lazy {
            ObjectCalls.getMethodBind("GridContainer", "get_columns", GET_COLUMNS_HASH)
        }
    }
}
