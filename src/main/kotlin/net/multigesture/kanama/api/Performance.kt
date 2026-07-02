package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Exposes performance-related data.
 *
 * Generated from Godot docs: Performance
 */
object Performance {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("Performance")
    }

    const val TIME_FPS: Long = 0L
    const val TIME_PROCESS: Long = 1L
    const val TIME_PHYSICS_PROCESS: Long = 2L
    const val TIME_NAVIGATION_PROCESS: Long = 3L
    const val MEMORY_STATIC: Long = 4L
    const val MEMORY_STATIC_MAX: Long = 5L
    const val MEMORY_MESSAGE_BUFFER_MAX: Long = 6L
    const val OBJECT_COUNT: Long = 7L
    const val OBJECT_RESOURCE_COUNT: Long = 8L
    const val OBJECT_NODE_COUNT: Long = 9L
    const val OBJECT_ORPHAN_NODE_COUNT: Long = 10L
    const val RENDER_TOTAL_OBJECTS_IN_FRAME: Long = 11L
    const val RENDER_TOTAL_PRIMITIVES_IN_FRAME: Long = 12L
    const val RENDER_TOTAL_DRAW_CALLS_IN_FRAME: Long = 13L
    const val RENDER_VIDEO_MEM_USED: Long = 14L
    const val RENDER_TEXTURE_MEM_USED: Long = 15L
    const val RENDER_BUFFER_MEM_USED: Long = 16L
    const val PHYSICS_2D_ACTIVE_OBJECTS: Long = 17L
    const val PHYSICS_2D_COLLISION_PAIRS: Long = 18L
    const val PHYSICS_2D_ISLAND_COUNT: Long = 19L
    const val PHYSICS_3D_ACTIVE_OBJECTS: Long = 20L
    const val PHYSICS_3D_COLLISION_PAIRS: Long = 21L
    const val PHYSICS_3D_ISLAND_COUNT: Long = 22L
    const val AUDIO_OUTPUT_LATENCY: Long = 23L
    const val NAVIGATION_ACTIVE_MAPS: Long = 24L
    const val NAVIGATION_REGION_COUNT: Long = 25L
    const val NAVIGATION_AGENT_COUNT: Long = 26L
    const val NAVIGATION_LINK_COUNT: Long = 27L
    const val NAVIGATION_POLYGON_COUNT: Long = 28L
    const val NAVIGATION_EDGE_COUNT: Long = 29L
    const val NAVIGATION_EDGE_MERGE_COUNT: Long = 30L
    const val NAVIGATION_EDGE_CONNECTION_COUNT: Long = 31L
    const val NAVIGATION_EDGE_FREE_COUNT: Long = 32L
    const val NAVIGATION_OBSTACLE_COUNT: Long = 33L
    const val PIPELINE_COMPILATIONS_CANVAS: Long = 34L
    const val PIPELINE_COMPILATIONS_MESH: Long = 35L
    const val PIPELINE_COMPILATIONS_SURFACE: Long = 36L
    const val PIPELINE_COMPILATIONS_DRAW: Long = 37L
    const val PIPELINE_COMPILATIONS_SPECIALIZATION: Long = 38L
    const val NAVIGATION_2D_ACTIVE_MAPS: Long = 39L
    const val NAVIGATION_2D_REGION_COUNT: Long = 40L
    const val NAVIGATION_2D_AGENT_COUNT: Long = 41L
    const val NAVIGATION_2D_LINK_COUNT: Long = 42L
    const val NAVIGATION_2D_POLYGON_COUNT: Long = 43L
    const val NAVIGATION_2D_EDGE_COUNT: Long = 44L
    const val NAVIGATION_2D_EDGE_MERGE_COUNT: Long = 45L
    const val NAVIGATION_2D_EDGE_CONNECTION_COUNT: Long = 46L
    const val NAVIGATION_2D_EDGE_FREE_COUNT: Long = 47L
    const val NAVIGATION_2D_OBSTACLE_COUNT: Long = 48L
    const val NAVIGATION_3D_ACTIVE_MAPS: Long = 49L
    const val NAVIGATION_3D_REGION_COUNT: Long = 50L
    const val NAVIGATION_3D_AGENT_COUNT: Long = 51L
    const val NAVIGATION_3D_LINK_COUNT: Long = 52L
    const val NAVIGATION_3D_POLYGON_COUNT: Long = 53L
    const val NAVIGATION_3D_EDGE_COUNT: Long = 54L
    const val NAVIGATION_3D_EDGE_MERGE_COUNT: Long = 55L
    const val NAVIGATION_3D_EDGE_CONNECTION_COUNT: Long = 56L
    const val NAVIGATION_3D_EDGE_FREE_COUNT: Long = 57L
    const val NAVIGATION_3D_OBSTACLE_COUNT: Long = 58L
    const val MONITOR_MAX: Long = 59L
    const val MONITOR_TYPE_QUANTITY: Long = 0L
    const val MONITOR_TYPE_MEMORY: Long = 1L
    const val MONITOR_TYPE_TIME: Long = 2L
    const val MONITOR_TYPE_PERCENTAGE: Long = 3L

    @JvmStatic
    fun getMonitor(monitor: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getMonitorBind, singleton, monitor)
    }

    @JvmStatic
    fun addCustomMonitor(id: String, callable: GodotCallable, arguments: List<Any?> = emptyList(), type: Long = 0L) {
        ObjectCalls.ptrcallWithStringNameCallableArrayLongArgs(addCustomMonitorBind, singleton, id, callable.target.handle, callable.method, arguments, type)
    }

    @JvmStatic
    fun removeCustomMonitor(id: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeCustomMonitorBind, singleton, id)
    }

    @JvmStatic
    fun hasCustomMonitor(id: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasCustomMonitorBind, singleton, id)
    }

    @JvmStatic
    fun getCustomMonitor(id: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getCustomMonitorBind, singleton, id)
    }

    @JvmStatic
    fun getMonitorModificationTime(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMonitorModificationTimeBind, singleton)
    }

    @JvmStatic
    fun getCustomMonitorNames(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetStringNameList(getCustomMonitorNamesBind, singleton)
    }

    @JvmStatic
    fun getCustomMonitorTypes(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getCustomMonitorTypesBind, singleton)
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): Performance? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): Performance? =
        if (handle.address() == 0L) null else this

    private const val GET_MONITOR_HASH = 1943275655L
    private val getMonitorBind by lazy {
        ObjectCalls.getMethodBind("Performance", "get_monitor", GET_MONITOR_HASH)
    }

    private const val ADD_CUSTOM_MONITOR_HASH = 3655788610L
    private val addCustomMonitorBind by lazy {
        ObjectCalls.getMethodBind("Performance", "add_custom_monitor", ADD_CUSTOM_MONITOR_HASH)
    }

    private const val REMOVE_CUSTOM_MONITOR_HASH = 3304788590L
    private val removeCustomMonitorBind by lazy {
        ObjectCalls.getMethodBind("Performance", "remove_custom_monitor", REMOVE_CUSTOM_MONITOR_HASH)
    }

    private const val HAS_CUSTOM_MONITOR_HASH = 2041966384L
    private val hasCustomMonitorBind by lazy {
        ObjectCalls.getMethodBind("Performance", "has_custom_monitor", HAS_CUSTOM_MONITOR_HASH)
    }

    private const val GET_CUSTOM_MONITOR_HASH = 2138907829L
    private val getCustomMonitorBind by lazy {
        ObjectCalls.getMethodBind("Performance", "get_custom_monitor", GET_CUSTOM_MONITOR_HASH)
    }

    private const val GET_MONITOR_MODIFICATION_TIME_HASH = 2455072627L
    private val getMonitorModificationTimeBind by lazy {
        ObjectCalls.getMethodBind("Performance", "get_monitor_modification_time", GET_MONITOR_MODIFICATION_TIME_HASH)
    }

    private const val GET_CUSTOM_MONITOR_NAMES_HASH = 2915620761L
    private val getCustomMonitorNamesBind by lazy {
        ObjectCalls.getMethodBind("Performance", "get_custom_monitor_names", GET_CUSTOM_MONITOR_NAMES_HASH)
    }

    private const val GET_CUSTOM_MONITOR_TYPES_HASH = 969006518L
    private val getCustomMonitorTypesBind by lazy {
        ObjectCalls.getMethodBind("Performance", "get_custom_monitor_types", GET_CUSTOM_MONITOR_TYPES_HASH)
    }
}
