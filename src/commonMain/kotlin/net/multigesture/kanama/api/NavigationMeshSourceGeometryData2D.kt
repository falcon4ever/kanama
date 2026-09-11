package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2

/**
 * Container for parsed source geometry data used in navigation mesh baking.
 *
 * Generated from Godot docs: NavigationMeshSourceGeometryData2D
 */
class NavigationMeshSourceGeometryData2D(handle: MemorySegment) : Resource(handle) {
    val projectedObstructions: List<Any?>
        @JvmName("projectedObstructionsProperty")
        get() = getProjectedObstructions()

    /**
     * Clears the internal data.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData2D.clear
     */
    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    /**
     * Returns `true` when parsed source geometry data exists.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData2D.has_data
     */
    fun hasData(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(hasDataBind, handle)
    }

    /**
     * Adds the outline points of a shape as traversable area.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData2D.add_traversable_outline
     */
    fun addTraversableOutline(shapeOutline: List<Vector2>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedVector2ListArg(addTraversableOutlineBind, handle, shapeOutline)
    }

    /**
     * Adds the outline points of a shape as obstructed area.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData2D.add_obstruction_outline
     */
    fun addObstructionOutline(shapeOutline: List<Vector2>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedVector2ListArg(addObstructionOutlineBind, handle, shapeOutline)
    }

    /**
     * Adds the geometry data of another `NavigationMeshSourceGeometryData2D` to the navigation mesh
     * baking data.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData2D.merge
     */
    fun merge(otherGeometry: NavigationMeshSourceGeometryData2D?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(mergeBind, handle, listOf(otherGeometry?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Adds a projected obstruction shape to the source geometry. If `carve` is `true` the carved shape
     * will not be affected by additional offsets (e.g. agent radius) of the navigation mesh baking
     * process.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData2D.add_projected_obstruction
     */
    fun addProjectedObstruction(vertices: List<Vector2>, carve: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedVector2ListAndBoolArg(addProjectedObstructionBind, handle, vertices, carve)
    }

    /**
     * Clears all projected obstructions.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData2D.clear_projected_obstructions
     */
    fun clearProjectedObstructions() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearProjectedObstructionsBind, handle)
    }

    /**
     * Returns the projected obstructions as an `Array` of dictionaries. Each `Dictionary` contains the
     * following entries: - `vertices` - A `PackedFloat32Array` that defines the outline points of the
     * projected shape. - `carve` - A `bool` that defines how the projected shape affects the
     * navigation mesh baking. If `true` the projected shape will not be affected by addition offsets,
     * e.g. agent radius.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData2D.get_projected_obstructions
     */
    fun getProjectedObstructions(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(getProjectedObstructionsBind, handle)
    }

    /**
     * Returns an axis-aligned bounding box that covers all the stored geometry data. The bounds are
     * calculated when calling this function with the result cached until further geometry changes are
     * made.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData2D.get_bounds
     */
    fun getBounds(): Rect2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRect2(getBoundsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): NavigationMeshSourceGeometryData2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): NavigationMeshSourceGeometryData2D? =
            if (handle.address() == 0L) null else NavigationMeshSourceGeometryData2D(handle)

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "clear", CLEAR_HASH)
        }

        private const val HAS_DATA_HASH = 2240911060L
        private val hasDataBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "has_data", HAS_DATA_HASH)
        }

        private const val ADD_TRAVERSABLE_OUTLINE_HASH = 1509147220L
        private val addTraversableOutlineBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "add_traversable_outline", ADD_TRAVERSABLE_OUTLINE_HASH)
        }

        private const val ADD_OBSTRUCTION_OUTLINE_HASH = 1509147220L
        private val addObstructionOutlineBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "add_obstruction_outline", ADD_OBSTRUCTION_OUTLINE_HASH)
        }

        private const val MERGE_HASH = 742424872L
        private val mergeBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "merge", MERGE_HASH)
        }

        private const val ADD_PROJECTED_OBSTRUCTION_HASH = 3882407395L
        private val addProjectedObstructionBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "add_projected_obstruction", ADD_PROJECTED_OBSTRUCTION_HASH)
        }

        private const val CLEAR_PROJECTED_OBSTRUCTIONS_HASH = 3218959716L
        private val clearProjectedObstructionsBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "clear_projected_obstructions", CLEAR_PROJECTED_OBSTRUCTIONS_HASH)
        }

        private const val GET_PROJECTED_OBSTRUCTIONS_HASH = 3995934104L
        private val getProjectedObstructionsBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "get_projected_obstructions", GET_PROJECTED_OBSTRUCTIONS_HASH)
        }

        private const val GET_BOUNDS_HASH = 3248174L
        private val getBoundsBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "get_bounds", GET_BOUNDS_HASH)
        }
    }
}
