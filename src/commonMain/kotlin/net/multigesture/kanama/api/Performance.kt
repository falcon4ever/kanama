package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Exposes performance-related data.
 *
 * Generated from Godot docs: Performance
 */
object Performance {
    private val singleton: RawSegment by lazy {
        ObjectCalls.getSingleton("Performance")
    }

    /**
     * Returns the value of one of the available built-in monitors. You should provide one of the
     * `Monitor` constants as the argument, like this:
     *
     * Generated from Godot docs: Performance.get_monitor
     */
    @JvmStatic
    fun getMonitor(monitor: Performance.Monitor): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getMonitorBind, singleton, monitor.value)
    }

    /**
     * Adds a custom monitor with the name `id`. You can specify the category of the monitor using
     * slash delimiters in `id` (for example: `"Game/NumberOfNPCs"`). If there is more than one slash
     * delimiter, then the default category is used. The default category is `"Custom"`. Prints an
     * error if given `id` is already present.
     *
     * Generated from Godot docs: Performance.add_custom_monitor
     */
    @JvmStatic
    fun addCustomMonitor(id: String, callable: GodotCallable, arguments: List<Any?> = emptyList(), type: Performance.MonitorType = Performance.MonitorType.QUANTITY) {
        ObjectCalls.ptrcallWithStringNameCallableArrayLongArgs(addCustomMonitorBind, singleton, id, callable.target.segment, callable.method, arguments, type.value)
    }

    /**
     * Removes the custom monitor with given `id`. Prints an error if the given `id` is already absent.
     *
     * Generated from Godot docs: Performance.remove_custom_monitor
     */
    @JvmStatic
    fun removeCustomMonitor(id: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeCustomMonitorBind, singleton, id)
    }

    /**
     * Returns `true` if custom monitor with the given `id` is present, `false` otherwise.
     *
     * Generated from Godot docs: Performance.has_custom_monitor
     */
    @JvmStatic
    fun hasCustomMonitor(id: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasCustomMonitorBind, singleton, id)
    }

    /**
     * Returns the value of custom monitor with given `id`. The callable is called to get the value of
     * custom monitor. See also `has_custom_monitor`. Prints an error if the given `id` is absent.
     *
     * Generated from Godot docs: Performance.get_custom_monitor
     */
    @JvmStatic
    fun getCustomMonitor(id: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getCustomMonitorBind, singleton, id)
    }

    /**
     * Returns the last tick in which custom monitor was added/removed (in microseconds since the
     * engine started). This is set to `Time.get_ticks_usec` when the monitor is updated.
     *
     * Generated from Godot docs: Performance.get_monitor_modification_time
     */
    @JvmStatic
    fun getMonitorModificationTime(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMonitorModificationTimeBind, singleton)
    }

    /**
     * Returns the names of active custom monitors in an `Array`.
     *
     * Generated from Godot docs: Performance.get_custom_monitor_names
     */
    @JvmStatic
    fun getCustomMonitorNames(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetStringNameList(getCustomMonitorNamesBind, singleton)
    }

    /**
     * Returns the `MonitorType` values of active custom monitors in an `Array`.
     *
     * Generated from Godot docs: Performance.get_custom_monitor_types
     */
    @JvmStatic
    fun getCustomMonitorTypes(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getCustomMonitorTypesBind, singleton)
    }

    /**
     * Godot's `Performance.Monitor` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Performance.Monitor.<NAME>`).
     *
     * Generated from Godot docs: Performance.Monitor
     */
    @JvmInline
    value class Monitor(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The number of frames rendered in the last second. This metric is only updated once per second,
             * even if queried more often. Higher is better.
             *
             * Generated from Godot docs: Performance.TIME_FPS
             */
            val TIME_FPS: Monitor get() = Monitor(0L)
            /**
             * Time it took to complete one frame, in seconds. Lower is better.
             *
             * Generated from Godot docs: Performance.TIME_PROCESS
             */
            val TIME_PROCESS: Monitor get() = Monitor(1L)
            /**
             * Time it took to complete one physics frame, in seconds. Lower is better.
             *
             * Generated from Godot docs: Performance.TIME_PHYSICS_PROCESS
             */
            val TIME_PHYSICS_PROCESS: Monitor get() = Monitor(2L)
            /**
             * Time it took to complete one navigation step, in seconds. This includes navigation map updates
             * as well as agent avoidance calculations. Lower is better.
             *
             * Generated from Godot docs: Performance.TIME_NAVIGATION_PROCESS
             */
            val TIME_NAVIGATION_PROCESS: Monitor get() = Monitor(3L)
            /**
             * Static memory currently used, in bytes. Not available in release builds. Lower is better.
             *
             * Generated from Godot docs: Performance.MEMORY_STATIC
             */
            val MEMORY_STATIC: Monitor get() = Monitor(4L)
            /**
             * Available static memory. Not available in release builds. Lower is better.
             *
             * Generated from Godot docs: Performance.MEMORY_STATIC_MAX
             */
            val MEMORY_STATIC_MAX: Monitor get() = Monitor(5L)
            /**
             * Largest amount of memory the message queue buffer has used, in bytes. The message queue is used
             * for deferred functions calls and notifications. Lower is better.
             *
             * Generated from Godot docs: Performance.MEMORY_MESSAGE_BUFFER_MAX
             */
            val MEMORY_MESSAGE_BUFFER_MAX: Monitor get() = Monitor(6L)
            /**
             * Number of objects currently instantiated (including nodes). Lower is better.
             *
             * Generated from Godot docs: Performance.OBJECT_COUNT
             */
            val OBJECT_COUNT: Monitor get() = Monitor(7L)
            /**
             * Number of resources currently used. Lower is better.
             *
             * Generated from Godot docs: Performance.OBJECT_RESOURCE_COUNT
             */
            val OBJECT_RESOURCE_COUNT: Monitor get() = Monitor(8L)
            /**
             * Number of nodes currently instantiated in the scene tree. This also includes the root node.
             * Lower is better.
             *
             * Generated from Godot docs: Performance.OBJECT_NODE_COUNT
             */
            val OBJECT_NODE_COUNT: Monitor get() = Monitor(9L)
            /**
             * Number of orphan nodes, i.e. nodes which are not parented to a node of the scene tree. Lower is
             * better. Note: This is only available in debug mode and will always return `0` when used in a
             * project exported in release mode.
             *
             * Generated from Godot docs: Performance.OBJECT_ORPHAN_NODE_COUNT
             */
            val OBJECT_ORPHAN_NODE_COUNT: Monitor get() = Monitor(10L)
            /**
             * The total number of objects in the last rendered frame. This metric doesn't include culled
             * objects (either via hiding nodes, frustum culling or occlusion culling). Lower is better.
             *
             * Generated from Godot docs: Performance.RENDER_TOTAL_OBJECTS_IN_FRAME
             */
            val RENDER_TOTAL_OBJECTS_IN_FRAME: Monitor get() = Monitor(11L)
            /**
             * The total number of vertices or indices rendered in the last rendered frame. This metric doesn't
             * include primitives from culled objects (either via hiding nodes, frustum culling or occlusion
             * culling). Due to the depth prepass and shadow passes, the number of primitives is always higher
             * than the actual number of vertices in the scene (typically double or triple the original vertex
             * count). Lower is better.
             *
             * Generated from Godot docs: Performance.RENDER_TOTAL_PRIMITIVES_IN_FRAME
             */
            val RENDER_TOTAL_PRIMITIVES_IN_FRAME: Monitor get() = Monitor(12L)
            /**
             * The total number of draw calls performed in the last rendered frame. This metric doesn't include
             * culled objects (either via hiding nodes, frustum culling or occlusion culling), since they do
             * not result in draw calls. Lower is better.
             *
             * Generated from Godot docs: Performance.RENDER_TOTAL_DRAW_CALLS_IN_FRAME
             */
            val RENDER_TOTAL_DRAW_CALLS_IN_FRAME: Monitor get() = Monitor(13L)
            /**
             * The amount of video memory used (texture and vertex memory combined, in bytes). Since this
             * metric also includes miscellaneous allocations, this value is always greater than the sum of
             * `Monitor.RENDER_TEXTURE_MEM_USED` and `Monitor.RENDER_BUFFER_MEM_USED`. Lower is better.
             *
             * Generated from Godot docs: Performance.RENDER_VIDEO_MEM_USED
             */
            val RENDER_VIDEO_MEM_USED: Monitor get() = Monitor(14L)
            /**
             * The amount of texture memory used (in bytes). Lower is better.
             *
             * Generated from Godot docs: Performance.RENDER_TEXTURE_MEM_USED
             */
            val RENDER_TEXTURE_MEM_USED: Monitor get() = Monitor(15L)
            /**
             * The amount of render buffer memory used (in bytes). Lower is better.
             *
             * Generated from Godot docs: Performance.RENDER_BUFFER_MEM_USED
             */
            val RENDER_BUFFER_MEM_USED: Monitor get() = Monitor(16L)
            /**
             * Number of active `RigidBody2D` nodes in the game. Lower is better.
             *
             * Generated from Godot docs: Performance.PHYSICS_2D_ACTIVE_OBJECTS
             */
            val PHYSICS_2D_ACTIVE_OBJECTS: Monitor get() = Monitor(17L)
            /**
             * Number of collision pairs in the 2D physics engine. Lower is better.
             *
             * Generated from Godot docs: Performance.PHYSICS_2D_COLLISION_PAIRS
             */
            val PHYSICS_2D_COLLISION_PAIRS: Monitor get() = Monitor(18L)
            /**
             * Number of islands in the 2D physics engine. Lower is better.
             *
             * Generated from Godot docs: Performance.PHYSICS_2D_ISLAND_COUNT
             */
            val PHYSICS_2D_ISLAND_COUNT: Monitor get() = Monitor(19L)
            /**
             * Number of active `RigidBody3D` and `VehicleBody3D` nodes in the game. Lower is better.
             *
             * Generated from Godot docs: Performance.PHYSICS_3D_ACTIVE_OBJECTS
             */
            val PHYSICS_3D_ACTIVE_OBJECTS: Monitor get() = Monitor(20L)
            /**
             * Number of collision pairs in the 3D physics engine. Lower is better.
             *
             * Generated from Godot docs: Performance.PHYSICS_3D_COLLISION_PAIRS
             */
            val PHYSICS_3D_COLLISION_PAIRS: Monitor get() = Monitor(21L)
            /**
             * Number of islands in the 3D physics engine. Lower is better.
             *
             * Generated from Godot docs: Performance.PHYSICS_3D_ISLAND_COUNT
             */
            val PHYSICS_3D_ISLAND_COUNT: Monitor get() = Monitor(22L)
            /**
             * Output latency of the `AudioServer`. Equivalent to calling `AudioServer.get_output_latency`, it
             * is not recommended to call this every frame.
             *
             * Generated from Godot docs: Performance.AUDIO_OUTPUT_LATENCY
             */
            val AUDIO_OUTPUT_LATENCY: Monitor get() = Monitor(23L)
            /**
             * Number of active navigation maps in `NavigationServer2D` and `NavigationServer3D`. This also
             * includes the empty default navigation maps created by `World2D` and `World3D` instances.
             *
             * Generated from Godot docs: Performance.NAVIGATION_ACTIVE_MAPS
             */
            val NAVIGATION_ACTIVE_MAPS: Monitor get() = Monitor(24L)
            /**
             * Number of active navigation regions in `NavigationServer2D` and `NavigationServer3D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_REGION_COUNT
             */
            val NAVIGATION_REGION_COUNT: Monitor get() = Monitor(25L)
            /**
             * Number of active navigation agents processing avoidance in `NavigationServer2D` and
             * `NavigationServer3D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_AGENT_COUNT
             */
            val NAVIGATION_AGENT_COUNT: Monitor get() = Monitor(26L)
            /**
             * Number of active navigation links in `NavigationServer2D` and `NavigationServer3D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_LINK_COUNT
             */
            val NAVIGATION_LINK_COUNT: Monitor get() = Monitor(27L)
            /**
             * Number of navigation mesh polygons in `NavigationServer2D` and `NavigationServer3D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_POLYGON_COUNT
             */
            val NAVIGATION_POLYGON_COUNT: Monitor get() = Monitor(28L)
            /**
             * Number of navigation mesh polygon edges in `NavigationServer2D` and `NavigationServer3D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_EDGE_COUNT
             */
            val NAVIGATION_EDGE_COUNT: Monitor get() = Monitor(29L)
            /**
             * Number of navigation mesh polygon edges that were merged due to edge key overlap in
             * `NavigationServer2D` and `NavigationServer3D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_EDGE_MERGE_COUNT
             */
            val NAVIGATION_EDGE_MERGE_COUNT: Monitor get() = Monitor(30L)
            /**
             * Number of polygon edges that are considered connected by edge proximity `NavigationServer2D` and
             * `NavigationServer3D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_EDGE_CONNECTION_COUNT
             */
            val NAVIGATION_EDGE_CONNECTION_COUNT: Monitor get() = Monitor(31L)
            /**
             * Number of navigation mesh polygon edges that could not be merged in `NavigationServer2D` and
             * `NavigationServer3D`. The edges still may be connected by edge proximity or with links.
             *
             * Generated from Godot docs: Performance.NAVIGATION_EDGE_FREE_COUNT
             */
            val NAVIGATION_EDGE_FREE_COUNT: Monitor get() = Monitor(32L)
            /**
             * Number of active navigation obstacles in the `NavigationServer2D` and `NavigationServer3D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_OBSTACLE_COUNT
             */
            val NAVIGATION_OBSTACLE_COUNT: Monitor get() = Monitor(33L)
            /**
             * Number of pipeline compilations that were triggered by the 2D canvas renderer.
             *
             * Generated from Godot docs: Performance.PIPELINE_COMPILATIONS_CANVAS
             */
            val PIPELINE_COMPILATIONS_CANVAS: Monitor get() = Monitor(34L)
            /**
             * Number of pipeline compilations that were triggered by loading meshes. These compilations will
             * show up as longer loading times the first time a user runs the game and the pipeline is
             * required.
             *
             * Generated from Godot docs: Performance.PIPELINE_COMPILATIONS_MESH
             */
            val PIPELINE_COMPILATIONS_MESH: Monitor get() = Monitor(35L)
            /**
             * Number of pipeline compilations that were triggered by building the surface cache before
             * rendering the scene. These compilations will show up as a stutter when loading a scene the first
             * time a user runs the game and the pipeline is required.
             *
             * Generated from Godot docs: Performance.PIPELINE_COMPILATIONS_SURFACE
             */
            val PIPELINE_COMPILATIONS_SURFACE: Monitor get() = Monitor(36L)
            /**
             * Number of pipeline compilations that were triggered while drawing the scene. These compilations
             * will show up as stutters during gameplay the first time a user runs the game and the pipeline is
             * required.
             *
             * Generated from Godot docs: Performance.PIPELINE_COMPILATIONS_DRAW
             */
            val PIPELINE_COMPILATIONS_DRAW: Monitor get() = Monitor(37L)
            /**
             * Number of pipeline compilations that were triggered to optimize the current scene. These
             * compilations are done in the background and should not cause any stutters whatsoever.
             *
             * Generated from Godot docs: Performance.PIPELINE_COMPILATIONS_SPECIALIZATION
             */
            val PIPELINE_COMPILATIONS_SPECIALIZATION: Monitor get() = Monitor(38L)
            /**
             * Number of active navigation maps in the `NavigationServer2D`. This also includes the empty
             * default navigation maps created by `World2D` instances.
             *
             * Generated from Godot docs: Performance.NAVIGATION_2D_ACTIVE_MAPS
             */
            val NAVIGATION_2D_ACTIVE_MAPS: Monitor get() = Monitor(39L)
            /**
             * Number of active navigation regions in the `NavigationServer2D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_2D_REGION_COUNT
             */
            val NAVIGATION_2D_REGION_COUNT: Monitor get() = Monitor(40L)
            /**
             * Number of active navigation agents processing avoidance in the `NavigationServer2D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_2D_AGENT_COUNT
             */
            val NAVIGATION_2D_AGENT_COUNT: Monitor get() = Monitor(41L)
            /**
             * Number of active navigation links in the `NavigationServer2D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_2D_LINK_COUNT
             */
            val NAVIGATION_2D_LINK_COUNT: Monitor get() = Monitor(42L)
            /**
             * Number of navigation mesh polygons in the `NavigationServer2D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_2D_POLYGON_COUNT
             */
            val NAVIGATION_2D_POLYGON_COUNT: Monitor get() = Monitor(43L)
            /**
             * Number of navigation mesh polygon edges in the `NavigationServer2D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_2D_EDGE_COUNT
             */
            val NAVIGATION_2D_EDGE_COUNT: Monitor get() = Monitor(44L)
            /**
             * Number of navigation mesh polygon edges that were merged due to edge key overlap in the
             * `NavigationServer2D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_2D_EDGE_MERGE_COUNT
             */
            val NAVIGATION_2D_EDGE_MERGE_COUNT: Monitor get() = Monitor(45L)
            /**
             * Number of polygon edges that are considered connected by edge proximity `NavigationServer2D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_2D_EDGE_CONNECTION_COUNT
             */
            val NAVIGATION_2D_EDGE_CONNECTION_COUNT: Monitor get() = Monitor(46L)
            /**
             * Number of navigation mesh polygon edges that could not be merged in the `NavigationServer2D`.
             * The edges still may be connected by edge proximity or with links.
             *
             * Generated from Godot docs: Performance.NAVIGATION_2D_EDGE_FREE_COUNT
             */
            val NAVIGATION_2D_EDGE_FREE_COUNT: Monitor get() = Monitor(47L)
            /**
             * Number of active navigation obstacles in the `NavigationServer2D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_2D_OBSTACLE_COUNT
             */
            val NAVIGATION_2D_OBSTACLE_COUNT: Monitor get() = Monitor(48L)
            /**
             * Number of active navigation maps in the `NavigationServer3D`. This also includes the empty
             * default navigation maps created by `World3D` instances.
             *
             * Generated from Godot docs: Performance.NAVIGATION_3D_ACTIVE_MAPS
             */
            val NAVIGATION_3D_ACTIVE_MAPS: Monitor get() = Monitor(49L)
            /**
             * Number of active navigation regions in the `NavigationServer3D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_3D_REGION_COUNT
             */
            val NAVIGATION_3D_REGION_COUNT: Monitor get() = Monitor(50L)
            /**
             * Number of active navigation agents processing avoidance in the `NavigationServer3D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_3D_AGENT_COUNT
             */
            val NAVIGATION_3D_AGENT_COUNT: Monitor get() = Monitor(51L)
            /**
             * Number of active navigation links in the `NavigationServer3D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_3D_LINK_COUNT
             */
            val NAVIGATION_3D_LINK_COUNT: Monitor get() = Monitor(52L)
            /**
             * Number of navigation mesh polygons in the `NavigationServer3D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_3D_POLYGON_COUNT
             */
            val NAVIGATION_3D_POLYGON_COUNT: Monitor get() = Monitor(53L)
            /**
             * Number of navigation mesh polygon edges in the `NavigationServer3D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_3D_EDGE_COUNT
             */
            val NAVIGATION_3D_EDGE_COUNT: Monitor get() = Monitor(54L)
            /**
             * Number of navigation mesh polygon edges that were merged due to edge key overlap in the
             * `NavigationServer3D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_3D_EDGE_MERGE_COUNT
             */
            val NAVIGATION_3D_EDGE_MERGE_COUNT: Monitor get() = Monitor(55L)
            /**
             * Number of polygon edges that are considered connected by edge proximity `NavigationServer3D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_3D_EDGE_CONNECTION_COUNT
             */
            val NAVIGATION_3D_EDGE_CONNECTION_COUNT: Monitor get() = Monitor(56L)
            /**
             * Number of navigation mesh polygon edges that could not be merged in the `NavigationServer3D`.
             * The edges still may be connected by edge proximity or with links.
             *
             * Generated from Godot docs: Performance.NAVIGATION_3D_EDGE_FREE_COUNT
             */
            val NAVIGATION_3D_EDGE_FREE_COUNT: Monitor get() = Monitor(57L)
            /**
             * Number of active navigation obstacles in the `NavigationServer3D`.
             *
             * Generated from Godot docs: Performance.NAVIGATION_3D_OBSTACLE_COUNT
             */
            val NAVIGATION_3D_OBSTACLE_COUNT: Monitor get() = Monitor(58L)
            /**
             * Represents the size of the `Monitor` enum.
             *
             * Generated from Godot docs: Performance.MONITOR_MAX
             */
            val MONITOR_MAX: Monitor get() = Monitor(59L)
        }
    }

    /**
     * Godot's `Performance.MonitorType` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`Performance.MonitorType.<NAME>`).
     *
     * Generated from Godot docs: Performance.MonitorType
     */
    @JvmInline
    value class MonitorType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Monitor output is formatted as an integer value.
             *
             * Generated from Godot docs: Performance.MONITOR_TYPE_QUANTITY
             */
            val QUANTITY: MonitorType get() = MonitorType(0L)
            /**
             * Monitor output is formatted as computer memory. Submitted values should represent a number of
             * bytes.
             *
             * Generated from Godot docs: Performance.MONITOR_TYPE_MEMORY
             */
            val MEMORY: MonitorType get() = MonitorType(1L)
            /**
             * Monitor output is formatted as time in milliseconds. Submitted values should represent a time in
             * seconds (not milliseconds).
             *
             * Generated from Godot docs: Performance.MONITOR_TYPE_TIME
             */
            val TIME: MonitorType get() = MonitorType(2L)
            /**
             * Monitor output is formatted as a percentage. Submitted values should represent a fractional
             * value rather than the percentage directly, e.g. `0.5` for `50.00%`.
             *
             * Generated from Godot docs: Performance.MONITOR_TYPE_PERCENTAGE
             */
            val PERCENTAGE: MonitorType get() = MonitorType(3L)
        }
    }

    @JvmStatic
    fun fromHandle(handle: GodotHandle): Performance? =
        wrap(handle.segment)

    internal fun wrap(handle: RawSegment): Performance? =
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
