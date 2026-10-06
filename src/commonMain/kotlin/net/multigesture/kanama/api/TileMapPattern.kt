package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2i

/**
 * Holds a pattern to be copied from or pasted into `TileMap`s.
 *
 * Generated from Godot docs: TileMapPattern
 */
class TileMapPattern(handle: GodotHandle) : Resource(handle) {
    /**
     * Sets the tile identifiers for the cell at coordinates `coords`. See `TileMap.set_cell`.
     *
     * Generated from Godot docs: TileMapPattern.set_cell
     */
    fun setCell(coords: Vector2i, sourceId: Int = -1, atlasCoords: Vector2i, alternativeTile: Int = -1) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iIntVector2iIntArgs(Binds.setCellBind, segment, coords, sourceId, atlasCoords, alternativeTile)
    }

    /**
     * Returns whether the pattern has a tile at the given coordinates.
     *
     * Generated from Godot docs: TileMapPattern.has_cell
     */
    fun hasCell(coords: Vector2i): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithVector2iArgRetBool(Binds.hasCellBind, segment, coords)
    }

    /**
     * Remove the cell at the given coordinates.
     *
     * Generated from Godot docs: TileMapPattern.remove_cell
     */
    fun removeCell(coords: Vector2i, updateSize: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iAndBoolArg(Binds.removeCellBind, segment, coords, updateSize)
    }

    /**
     * Returns the tile source ID of the cell at `coords`.
     *
     * Generated from Godot docs: TileMapPattern.get_cell_source_id
     */
    fun getCellSourceId(coords: Vector2i): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithVector2iArgRetInt(Binds.getCellSourceIdBind, segment, coords)
    }

    /**
     * Returns the tile atlas coordinates ID of the cell at `coords`.
     *
     * Generated from Godot docs: TileMapPattern.get_cell_atlas_coords
     */
    fun getCellAtlasCoords(coords: Vector2i): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallWithVector2iArgRetVector2i(Binds.getCellAtlasCoordsBind, segment, coords)
    }

    /**
     * Returns the tile alternative ID of the cell at `coords`.
     *
     * Generated from Godot docs: TileMapPattern.get_cell_alternative_tile
     */
    fun getCellAlternativeTile(coords: Vector2i): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithVector2iArgRetInt(Binds.getCellAlternativeTileBind, segment, coords)
    }

    /**
     * Returns the list of used cell coordinates in the pattern.
     *
     * Generated from Godot docs: TileMapPattern.get_used_cells
     */
    fun getUsedCells(): List<Vector2i> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2iList(Binds.getUsedCellsBind, segment)
    }

    /**
     * Returns the size, in cells, of the pattern.
     *
     * Generated from Godot docs: TileMapPattern.get_size
     */
    fun getSize(): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2i(Binds.getSizeBind, segment)
    }

    /**
     * Sets the size of the pattern.
     *
     * Generated from Godot docs: TileMapPattern.set_size
     */
    fun setSize(size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iArg(Binds.setSizeBind, segment, size)
    }

    /**
     * Returns whether the pattern is empty or not.
     *
     * Generated from Godot docs: TileMapPattern.is_empty
     */
    fun isEmpty(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEmptyBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TileMapPattern? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): TileMapPattern? =
            if (handle.address() == 0L) null else RefCounted.owned(TileMapPattern(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): TileMapPattern? =
            if (handle.address() == 0L) null else TileMapPattern(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_CELL_HASH = 2224802556L
        @JvmField
        val setCellBind =
            ObjectCalls.getMethodBind("TileMapPattern", "set_cell", SET_CELL_HASH)

        private const val HAS_CELL_HASH = 3900751641L
        @JvmField
        val hasCellBind =
            ObjectCalls.getMethodBind("TileMapPattern", "has_cell", HAS_CELL_HASH)

        private const val REMOVE_CELL_HASH = 4153096796L
        @JvmField
        val removeCellBind =
            ObjectCalls.getMethodBind("TileMapPattern", "remove_cell", REMOVE_CELL_HASH)

        private const val GET_CELL_SOURCE_ID_HASH = 2485466453L
        @JvmField
        val getCellSourceIdBind =
            ObjectCalls.getMethodBind("TileMapPattern", "get_cell_source_id", GET_CELL_SOURCE_ID_HASH)

        private const val GET_CELL_ATLAS_COORDS_HASH = 3050897911L
        @JvmField
        val getCellAtlasCoordsBind =
            ObjectCalls.getMethodBind("TileMapPattern", "get_cell_atlas_coords", GET_CELL_ATLAS_COORDS_HASH)

        private const val GET_CELL_ALTERNATIVE_TILE_HASH = 2485466453L
        @JvmField
        val getCellAlternativeTileBind =
            ObjectCalls.getMethodBind("TileMapPattern", "get_cell_alternative_tile", GET_CELL_ALTERNATIVE_TILE_HASH)

        private const val GET_USED_CELLS_HASH = 3995934104L
        @JvmField
        val getUsedCellsBind =
            ObjectCalls.getMethodBind("TileMapPattern", "get_used_cells", GET_USED_CELLS_HASH)

        private const val GET_SIZE_HASH = 3690982128L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("TileMapPattern", "get_size", GET_SIZE_HASH)

        private const val SET_SIZE_HASH = 1130785943L
        @JvmField
        val setSizeBind =
            ObjectCalls.getMethodBind("TileMapPattern", "set_size", SET_SIZE_HASH)

        private const val IS_EMPTY_HASH = 36873697L
        @JvmField
        val isEmptyBind =
            ObjectCalls.getMethodBind("TileMapPattern", "is_empty", IS_EMPTY_HASH)
    }
}
