package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A container that arranges its child controls in a grid layout.
 *
 * Generated from Godot docs: GridContainer
 */
class GridContainer(handle: MemorySegment) : Container(handle) {
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
        ObjectCalls.ptrcallWithIntArg(setColumnsBind, handle, columns)
    }

    /**
     * The number of columns in the `GridContainer`. If modified, `GridContainer` reorders its
     * Control-derived children to accommodate the new layout.
     *
     * Generated from Godot docs: GridContainer.get_columns
     */
    fun getColumns(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getColumnsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GridContainer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GridContainer? =
            if (handle.address() == 0L) null else GridContainer(handle)

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
