package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2

/**
 * Container for parsed source geometry data used in navigation mesh baking.
 *
 * Generated from Godot docs: NavigationMeshSourceGeometryData2D
 */
class NavigationMeshSourceGeometryData2D(handle: MemorySegment) : Resource(handle) {
    var traversableOutlines: List<List<Vector2>>
        @JvmName("traversableOutlinesProperty")
        get() = getTraversableOutlines()
        @JvmName("setTraversableOutlinesProperty")
        set(value) = setTraversableOutlines(value)

    var obstructionOutlines: List<List<Vector2>>
        @JvmName("obstructionOutlinesProperty")
        get() = getObstructionOutlines()
        @JvmName("setObstructionOutlinesProperty")
        set(value) = setObstructionOutlines(value)

    var projectedObstructions: List<Any?>
        @JvmName("projectedObstructionsProperty")
        get() = getProjectedObstructions()
        @JvmName("setProjectedObstructionsProperty")
        set(value) = setProjectedObstructions(value)

    /**
     * Clears the internal data.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData2D.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun hasData(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasDataBind, handle)
    }

    fun setTraversableOutlines(traversableOutlines: List<List<Vector2>>) {
        ObjectCalls.ptrcallWithPackedVector2ListListArg(setTraversableOutlinesBind, handle, traversableOutlines)
    }

    fun getTraversableOutlines(): List<List<Vector2>> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2ListList(getTraversableOutlinesBind, handle)
    }

    fun setObstructionOutlines(obstructionOutlines: List<List<Vector2>>) {
        ObjectCalls.ptrcallWithPackedVector2ListListArg(setObstructionOutlinesBind, handle, obstructionOutlines)
    }

    fun getObstructionOutlines(): List<List<Vector2>> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2ListList(getObstructionOutlinesBind, handle)
    }

    fun appendTraversableOutlines(traversableOutlines: List<List<Vector2>>) {
        ObjectCalls.ptrcallWithPackedVector2ListListArg(appendTraversableOutlinesBind, handle, traversableOutlines)
    }

    fun appendObstructionOutlines(obstructionOutlines: List<List<Vector2>>) {
        ObjectCalls.ptrcallWithPackedVector2ListListArg(appendObstructionOutlinesBind, handle, obstructionOutlines)
    }

    fun addTraversableOutline(shapeOutline: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(addTraversableOutlineBind, handle, shapeOutline)
    }

    fun addObstructionOutline(shapeOutline: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(addObstructionOutlineBind, handle, shapeOutline)
    }

    /**
     * Adds the geometry data of another `NavigationMeshSourceGeometryData2D` to the navigation mesh
     * baking data.
     *
     * Generated from Godot docs: NavigationMeshSourceGeometryData2D.merge
     */
    fun merge(otherGeometry: NavigationMeshSourceGeometryData2D?) {
        ObjectCalls.ptrcallWithObjectArgs(mergeBind, handle, listOf(otherGeometry?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun addProjectedObstruction(vertices: List<Vector2>, carve: Boolean) {
        ObjectCalls.ptrcallWithPackedVector2ListAndBoolArg(addProjectedObstructionBind, handle, vertices, carve)
    }

    fun clearProjectedObstructions() {
        ObjectCalls.ptrcallNoArgs(clearProjectedObstructionsBind, handle)
    }

    fun setProjectedObstructions(projectedObstructions: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setProjectedObstructionsBind, handle, projectedObstructions)
    }

    fun getProjectedObstructions(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getProjectedObstructionsBind, handle)
    }

    fun getBounds(): Rect2 {
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

        private const val SET_TRAVERSABLE_OUTLINES_HASH = 381264803L
        private val setTraversableOutlinesBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "set_traversable_outlines", SET_TRAVERSABLE_OUTLINES_HASH)
        }

        private const val GET_TRAVERSABLE_OUTLINES_HASH = 3995934104L
        private val getTraversableOutlinesBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "get_traversable_outlines", GET_TRAVERSABLE_OUTLINES_HASH)
        }

        private const val SET_OBSTRUCTION_OUTLINES_HASH = 381264803L
        private val setObstructionOutlinesBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "set_obstruction_outlines", SET_OBSTRUCTION_OUTLINES_HASH)
        }

        private const val GET_OBSTRUCTION_OUTLINES_HASH = 3995934104L
        private val getObstructionOutlinesBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "get_obstruction_outlines", GET_OBSTRUCTION_OUTLINES_HASH)
        }

        private const val APPEND_TRAVERSABLE_OUTLINES_HASH = 381264803L
        private val appendTraversableOutlinesBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "append_traversable_outlines", APPEND_TRAVERSABLE_OUTLINES_HASH)
        }

        private const val APPEND_OBSTRUCTION_OUTLINES_HASH = 381264803L
        private val appendObstructionOutlinesBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "append_obstruction_outlines", APPEND_OBSTRUCTION_OUTLINES_HASH)
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

        private const val SET_PROJECTED_OBSTRUCTIONS_HASH = 381264803L
        private val setProjectedObstructionsBind by lazy {
            ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "set_projected_obstructions", SET_PROJECTED_OBSTRUCTIONS_HASH)
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
