package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

/**
 * A resource that holds all components of a 2D world, such as a canvas and a physics space.
 *
 * Generated from Godot docs: World2D
 */
class World2D(handle: MemorySegment) : Resource(handle) {
    val canvas: RID
        @JvmName("canvasProperty")
        get() = getCanvas()

    val navigationMap: RID
        @JvmName("navigationMapProperty")
        get() = getNavigationMap()

    val space: RID
        @JvmName("spaceProperty")
        get() = getSpace()

    val directSpaceState: PhysicsDirectSpaceState2D?
        @JvmName("directSpaceStateProperty")
        get() = getDirectSpaceState()

    /**
     * The `RID` of this world's canvas resource. Used by the `RenderingServer` for 2D drawing.
     *
     * Generated from Godot docs: World2D.get_canvas
     */
    fun getCanvas(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getCanvasBind, handle)
    }

    /**
     * The `RID` of this world's navigation map. Used by the `NavigationServer2D`.
     *
     * Generated from Godot docs: World2D.get_navigation_map
     */
    fun getNavigationMap(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getNavigationMapBind, handle)
    }

    /**
     * The `RID` of this world's physics space resource. Used by the `PhysicsServer2D` for 2D physics,
     * treating it as both a space and an area.
     *
     * Generated from Godot docs: World2D.get_space
     */
    fun getSpace(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getSpaceBind, handle)
    }

    /**
     * Direct access to the world's physics 2D space state. Used for querying current and potential
     * collisions. When using multi-threaded physics, access is limited to `Node._physics_process` in
     * the main thread.
     *
     * Generated from Godot docs: World2D.get_direct_space_state
     */
    fun getDirectSpaceState(): PhysicsDirectSpaceState2D? {
        return PhysicsDirectSpaceState2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getDirectSpaceStateBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): World2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): World2D? =
            if (handle.address() == 0L) null else World2D(handle)

        private const val GET_CANVAS_HASH = 2944877500L
        private val getCanvasBind by lazy {
            ObjectCalls.getMethodBind("World2D", "get_canvas", GET_CANVAS_HASH)
        }

        private const val GET_NAVIGATION_MAP_HASH = 2944877500L
        private val getNavigationMapBind by lazy {
            ObjectCalls.getMethodBind("World2D", "get_navigation_map", GET_NAVIGATION_MAP_HASH)
        }

        private const val GET_SPACE_HASH = 2944877500L
        private val getSpaceBind by lazy {
            ObjectCalls.getMethodBind("World2D", "get_space", GET_SPACE_HASH)
        }

        private const val GET_DIRECT_SPACE_STATE_HASH = 2506717822L
        private val getDirectSpaceStateBind by lazy {
            ObjectCalls.getMethodBind("World2D", "get_direct_space_state", GET_DIRECT_SPACE_STATE_HASH)
        }
    }
}
