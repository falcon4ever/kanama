package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: NavigationPolygon
 */
class NavigationPolygon(handle: MemorySegment) : Resource(handle) {
    val vertices: List<Vector2>
        @JvmName("verticesProperty")
        get() = getVertices()

    var samplePartitionType: Long
        @JvmName("samplePartitionTypeProperty")
        get() = getSamplePartitionType()
        @JvmName("setSamplePartitionTypeProperty")
        set(value) = setSamplePartitionType(value)

    var parsedGeometryType: Long
        @JvmName("parsedGeometryTypeProperty")
        get() = getParsedGeometryType()
        @JvmName("setParsedGeometryTypeProperty")
        set(value) = setParsedGeometryType(value)

    var parsedCollisionMask: Long
        @JvmName("parsedCollisionMaskProperty")
        get() = getParsedCollisionMask()
        @JvmName("setParsedCollisionMaskProperty")
        set(value) = setParsedCollisionMask(value)

    var sourceGeometryMode: Long
        @JvmName("sourceGeometryModeProperty")
        get() = getSourceGeometryMode()
        @JvmName("setSourceGeometryModeProperty")
        set(value) = setSourceGeometryMode(value)

    var sourceGeometryGroupName: String
        @JvmName("sourceGeometryGroupNameProperty")
        get() = getSourceGeometryGroupName()
        @JvmName("setSourceGeometryGroupNameProperty")
        set(value) = setSourceGeometryGroupName(value)

    var cellSize: Double
        @JvmName("cellSizeProperty")
        get() = getCellSize()
        @JvmName("setCellSizeProperty")
        set(value) = setCellSize(value)

    var borderSize: Double
        @JvmName("borderSizeProperty")
        get() = getBorderSize()
        @JvmName("setBorderSizeProperty")
        set(value) = setBorderSize(value)

    var agentRadius: Double
        @JvmName("agentRadiusProperty")
        get() = getAgentRadius()
        @JvmName("setAgentRadiusProperty")
        set(value) = setAgentRadius(value)

    var bakingRect: Rect2
        @JvmName("bakingRectProperty")
        get() = getBakingRect()
        @JvmName("setBakingRectProperty")
        set(value) = setBakingRect(value)

    var bakingRectOffset: Vector2
        @JvmName("bakingRectOffsetProperty")
        get() = getBakingRectOffset()
        @JvmName("setBakingRectOffsetProperty")
        set(value) = setBakingRectOffset(value)

    fun getVertices(): List<Vector2> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getVerticesBind, handle)
    }

    fun getPolygonCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getPolygonCountBind, handle)
    }

    fun clearPolygons() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearPolygonsBind, handle)
    }

    fun getNavigationMesh(): NavigationMesh? {
        checkOpen()
        return NavigationMesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(getNavigationMeshBind, handle))
    }

    fun getOutlineCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getOutlineCountBind, handle)
    }

    fun removeOutline(idx: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(removeOutlineBind, handle, idx)
    }

    fun clearOutlines() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearOutlinesBind, handle)
    }

    fun makePolygonsFromOutlines() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(makePolygonsFromOutlinesBind, handle)
    }

    fun setCellSize(cellSize: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setCellSizeBind, handle, cellSize)
    }

    fun getCellSize(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getCellSizeBind, handle)
    }

    fun setBorderSize(borderSize: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setBorderSizeBind, handle, borderSize)
    }

    fun getBorderSize(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getBorderSizeBind, handle)
    }

    fun setSamplePartitionType(samplePartitionType: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSamplePartitionTypeBind, handle, samplePartitionType)
    }

    fun getSamplePartitionType(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getSamplePartitionTypeBind, handle)
    }

    fun setParsedGeometryType(geometryType: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setParsedGeometryTypeBind, handle, geometryType)
    }

    fun getParsedGeometryType(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getParsedGeometryTypeBind, handle)
    }

    fun setParsedCollisionMask(mask: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(setParsedCollisionMaskBind, handle, mask)
    }

    fun getParsedCollisionMask(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(getParsedCollisionMaskBind, handle)
    }

    fun setParsedCollisionMaskValue(layerNumber: Int, value: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndBoolArgs(setParsedCollisionMaskValueBind, handle, layerNumber, value)
    }

    fun getParsedCollisionMaskValue(layerNumber: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(getParsedCollisionMaskValueBind, handle, layerNumber)
    }

    fun setSourceGeometryMode(geometryMode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSourceGeometryModeBind, handle, geometryMode)
    }

    fun getSourceGeometryMode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getSourceGeometryModeBind, handle)
    }

    fun setSourceGeometryGroupName(groupName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameArg(setSourceGeometryGroupNameBind, handle, groupName)
    }

    fun getSourceGeometryGroupName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetStringName(getSourceGeometryGroupNameBind, handle)
    }

    fun setAgentRadius(agentRadius: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setAgentRadiusBind, handle, agentRadius)
    }

    fun getAgentRadius(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getAgentRadiusBind, handle)
    }

    fun setBakingRect(rect: Rect2) {
        checkOpen()
        ObjectCalls.ptrcallWithRect2Arg(setBakingRectBind, handle, rect)
    }

    fun getBakingRect(): Rect2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRect2(getBakingRectBind, handle)
    }

    fun setBakingRectOffset(rectOffset: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setBakingRectOffsetBind, handle, rectOffset)
    }

    fun getBakingRectOffset(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getBakingRectOffsetBind, handle)
    }

    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    companion object {
        const val SAMPLE_PARTITION_CONVEX_PARTITION: Long = 0L
        const val SAMPLE_PARTITION_TRIANGULATE: Long = 1L
        const val SAMPLE_PARTITION_MAX: Long = 2L
        const val PARSED_GEOMETRY_MESH_INSTANCES: Long = 0L
        const val PARSED_GEOMETRY_STATIC_COLLIDERS: Long = 1L
        const val PARSED_GEOMETRY_BOTH: Long = 2L
        const val PARSED_GEOMETRY_MAX: Long = 3L
        const val SOURCE_GEOMETRY_ROOT_NODE_CHILDREN: Long = 0L
        const val SOURCE_GEOMETRY_GROUPS_WITH_CHILDREN: Long = 1L
        const val SOURCE_GEOMETRY_GROUPS_EXPLICIT: Long = 2L
        const val SOURCE_GEOMETRY_MAX: Long = 3L

        fun fromHandle(handle: MemorySegment): NavigationPolygon? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): NavigationPolygon? =
            if (handle.address() == 0L) null else NavigationPolygon(handle)

        private const val GET_VERTICES_HASH = 2961356807L
        private val getVerticesBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "get_vertices", GET_VERTICES_HASH)
        }

        private const val GET_POLYGON_COUNT_HASH = 3905245786L
        private val getPolygonCountBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "get_polygon_count", GET_POLYGON_COUNT_HASH)
        }

        private const val CLEAR_POLYGONS_HASH = 3218959716L
        private val clearPolygonsBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "clear_polygons", CLEAR_POLYGONS_HASH)
        }

        private const val GET_NAVIGATION_MESH_HASH = 330232164L
        private val getNavigationMeshBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "get_navigation_mesh", GET_NAVIGATION_MESH_HASH)
        }

        private const val GET_OUTLINE_COUNT_HASH = 3905245786L
        private val getOutlineCountBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "get_outline_count", GET_OUTLINE_COUNT_HASH)
        }

        private const val REMOVE_OUTLINE_HASH = 1286410249L
        private val removeOutlineBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "remove_outline", REMOVE_OUTLINE_HASH)
        }

        private const val CLEAR_OUTLINES_HASH = 3218959716L
        private val clearOutlinesBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "clear_outlines", CLEAR_OUTLINES_HASH)
        }

        private const val MAKE_POLYGONS_FROM_OUTLINES_HASH = 3218959716L
        private val makePolygonsFromOutlinesBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "make_polygons_from_outlines", MAKE_POLYGONS_FROM_OUTLINES_HASH)
        }

        private const val SET_CELL_SIZE_HASH = 373806689L
        private val setCellSizeBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "set_cell_size", SET_CELL_SIZE_HASH)
        }

        private const val GET_CELL_SIZE_HASH = 1740695150L
        private val getCellSizeBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "get_cell_size", GET_CELL_SIZE_HASH)
        }

        private const val SET_BORDER_SIZE_HASH = 373806689L
        private val setBorderSizeBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "set_border_size", SET_BORDER_SIZE_HASH)
        }

        private const val GET_BORDER_SIZE_HASH = 1740695150L
        private val getBorderSizeBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "get_border_size", GET_BORDER_SIZE_HASH)
        }

        private const val SET_SAMPLE_PARTITION_TYPE_HASH = 2441478482L
        private val setSamplePartitionTypeBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "set_sample_partition_type", SET_SAMPLE_PARTITION_TYPE_HASH)
        }

        private const val GET_SAMPLE_PARTITION_TYPE_HASH = 3887422851L
        private val getSamplePartitionTypeBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "get_sample_partition_type", GET_SAMPLE_PARTITION_TYPE_HASH)
        }

        private const val SET_PARSED_GEOMETRY_TYPE_HASH = 2507971764L
        private val setParsedGeometryTypeBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "set_parsed_geometry_type", SET_PARSED_GEOMETRY_TYPE_HASH)
        }

        private const val GET_PARSED_GEOMETRY_TYPE_HASH = 1073219508L
        private val getParsedGeometryTypeBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "get_parsed_geometry_type", GET_PARSED_GEOMETRY_TYPE_HASH)
        }

        private const val SET_PARSED_COLLISION_MASK_HASH = 1286410249L
        private val setParsedCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "set_parsed_collision_mask", SET_PARSED_COLLISION_MASK_HASH)
        }

        private const val GET_PARSED_COLLISION_MASK_HASH = 3905245786L
        private val getParsedCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "get_parsed_collision_mask", GET_PARSED_COLLISION_MASK_HASH)
        }

        private const val SET_PARSED_COLLISION_MASK_VALUE_HASH = 300928843L
        private val setParsedCollisionMaskValueBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "set_parsed_collision_mask_value", SET_PARSED_COLLISION_MASK_VALUE_HASH)
        }

        private const val GET_PARSED_COLLISION_MASK_VALUE_HASH = 1116898809L
        private val getParsedCollisionMaskValueBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "get_parsed_collision_mask_value", GET_PARSED_COLLISION_MASK_VALUE_HASH)
        }

        private const val SET_SOURCE_GEOMETRY_MODE_HASH = 4002316705L
        private val setSourceGeometryModeBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "set_source_geometry_mode", SET_SOURCE_GEOMETRY_MODE_HASH)
        }

        private const val GET_SOURCE_GEOMETRY_MODE_HASH = 459686762L
        private val getSourceGeometryModeBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "get_source_geometry_mode", GET_SOURCE_GEOMETRY_MODE_HASH)
        }

        private const val SET_SOURCE_GEOMETRY_GROUP_NAME_HASH = 3304788590L
        private val setSourceGeometryGroupNameBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "set_source_geometry_group_name", SET_SOURCE_GEOMETRY_GROUP_NAME_HASH)
        }

        private const val GET_SOURCE_GEOMETRY_GROUP_NAME_HASH = 2002593661L
        private val getSourceGeometryGroupNameBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "get_source_geometry_group_name", GET_SOURCE_GEOMETRY_GROUP_NAME_HASH)
        }

        private const val SET_AGENT_RADIUS_HASH = 373806689L
        private val setAgentRadiusBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "set_agent_radius", SET_AGENT_RADIUS_HASH)
        }

        private const val GET_AGENT_RADIUS_HASH = 1740695150L
        private val getAgentRadiusBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "get_agent_radius", GET_AGENT_RADIUS_HASH)
        }

        private const val SET_BAKING_RECT_HASH = 2046264180L
        private val setBakingRectBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "set_baking_rect", SET_BAKING_RECT_HASH)
        }

        private const val GET_BAKING_RECT_HASH = 1639390495L
        private val getBakingRectBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "get_baking_rect", GET_BAKING_RECT_HASH)
        }

        private const val SET_BAKING_RECT_OFFSET_HASH = 743155724L
        private val setBakingRectOffsetBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "set_baking_rect_offset", SET_BAKING_RECT_OFFSET_HASH)
        }

        private const val GET_BAKING_RECT_OFFSET_HASH = 3341600327L
        private val getBakingRectOffsetBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "get_baking_rect_offset", GET_BAKING_RECT_OFFSET_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("NavigationPolygon", "clear", CLEAR_HASH)
        }
    }
}
