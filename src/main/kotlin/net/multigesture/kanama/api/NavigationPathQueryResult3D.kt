package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

/**
 * Represents the result of a 3D pathfinding query.
 *
 * Generated from Godot docs: NavigationPathQueryResult3D
 */
class NavigationPathQueryResult3D(handle: MemorySegment) : RefCounted(handle) {
    var path: List<Vector3>
        @JvmName("pathProperty")
        get() = getPath()
        @JvmName("setPathProperty")
        set(value) = setPath(value)

    var pathTypes: List<Int>
        @JvmName("pathTypesProperty")
        get() = getPathTypes()
        @JvmName("setPathTypesProperty")
        set(value) = setPathTypes(value)

    var pathRids: List<RID>
        @JvmName("pathRidsProperty")
        get() = getPathRids()
        @JvmName("setPathRidsProperty")
        set(value) = setPathRids(value)

    var pathOwnerIds: List<Long>
        @JvmName("pathOwnerIdsProperty")
        get() = getPathOwnerIds()
        @JvmName("setPathOwnerIdsProperty")
        set(value) = setPathOwnerIds(value)

    var pathLength: Double
        @JvmName("pathLengthProperty")
        get() = getPathLength()
        @JvmName("setPathLengthProperty")
        set(value) = setPathLength(value)

    /**
     * The resulting path array from the navigation query. All path array positions are in global
     * coordinates. Without customized query parameters this is the same path as returned by
     * `NavigationServer3D.map_get_path`.
     *
     * Generated from Godot docs: NavigationPathQueryResult3D.set_path
     */
    fun setPath(path: List<Vector3>) {
        ObjectCalls.ptrcallWithPackedVector3ListArg(setPathBind, handle, path)
    }

    /**
     * The resulting path array from the navigation query. All path array positions are in global
     * coordinates. Without customized query parameters this is the same path as returned by
     * `NavigationServer3D.map_get_path`.
     *
     * Generated from Godot docs: NavigationPathQueryResult3D.get_path
     */
    fun getPath(): List<Vector3> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getPathBind, handle)
    }

    /**
     * The type of navigation primitive (region or link) that each point of the path goes through.
     *
     * Generated from Godot docs: NavigationPathQueryResult3D.set_path_types
     */
    fun setPathTypes(pathTypes: List<Int>) {
        ObjectCalls.ptrcallWithPackedInt32ListArg(setPathTypesBind, handle, pathTypes)
    }

    /**
     * The type of navigation primitive (region or link) that each point of the path goes through.
     *
     * Generated from Godot docs: NavigationPathQueryResult3D.get_path_types
     */
    fun getPathTypes(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getPathTypesBind, handle)
    }

    /**
     * The `RID`s of the regions and links that each point of the path goes through.
     *
     * Generated from Godot docs: NavigationPathQueryResult3D.set_path_rids
     */
    fun setPathRids(pathRids: List<RID>) {
        ObjectCalls.ptrcallWithRIDListArg(setPathRidsBind, handle, pathRids)
    }

    /**
     * The `RID`s of the regions and links that each point of the path goes through.
     *
     * Generated from Godot docs: NavigationPathQueryResult3D.get_path_rids
     */
    fun getPathRids(): List<RID> {
        return ObjectCalls.ptrcallNoArgsRetRIDList(getPathRidsBind, handle)
    }

    /**
     * The `ObjectID`s of the `Object`s which manage the regions and links each point of the path goes
     * through.
     *
     * Generated from Godot docs: NavigationPathQueryResult3D.set_path_owner_ids
     */
    fun setPathOwnerIds(pathOwnerIds: List<Long>) {
        ObjectCalls.ptrcallWithPackedInt64ListArg(setPathOwnerIdsBind, handle, pathOwnerIds)
    }

    /**
     * The `ObjectID`s of the `Object`s which manage the regions and links each point of the path goes
     * through.
     *
     * Generated from Godot docs: NavigationPathQueryResult3D.get_path_owner_ids
     */
    fun getPathOwnerIds(): List<Long> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt64List(getPathOwnerIdsBind, handle)
    }

    /**
     * Returns the length of the path.
     *
     * Generated from Godot docs: NavigationPathQueryResult3D.set_path_length
     */
    fun setPathLength(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPathLengthBind, handle, length)
    }

    /**
     * Returns the length of the path.
     *
     * Generated from Godot docs: NavigationPathQueryResult3D.get_path_length
     */
    fun getPathLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPathLengthBind, handle)
    }

    /**
     * Reset the result object to its initial state. This is useful to reuse the object across multiple
     * queries.
     *
     * Generated from Godot docs: NavigationPathQueryResult3D.reset
     */
    fun reset() {
        ObjectCalls.ptrcallNoArgs(resetBind, handle)
    }

    companion object {
        const val PATH_SEGMENT_TYPE_REGION: Long = 0L
        const val PATH_SEGMENT_TYPE_LINK: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): NavigationPathQueryResult3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): NavigationPathQueryResult3D? =
            if (handle.address() == 0L) null else NavigationPathQueryResult3D(handle)

        private const val SET_PATH_HASH = 334873810L
        private val setPathBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryResult3D", "set_path", SET_PATH_HASH)
        }

        private const val GET_PATH_HASH = 497664490L
        private val getPathBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryResult3D", "get_path", GET_PATH_HASH)
        }

        private const val SET_PATH_TYPES_HASH = 3614634198L
        private val setPathTypesBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryResult3D", "set_path_types", SET_PATH_TYPES_HASH)
        }

        private const val GET_PATH_TYPES_HASH = 1930428628L
        private val getPathTypesBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryResult3D", "get_path_types", GET_PATH_TYPES_HASH)
        }

        private const val SET_PATH_RIDS_HASH = 381264803L
        private val setPathRidsBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryResult3D", "set_path_rids", SET_PATH_RIDS_HASH)
        }

        private const val GET_PATH_RIDS_HASH = 3995934104L
        private val getPathRidsBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryResult3D", "get_path_rids", GET_PATH_RIDS_HASH)
        }

        private const val SET_PATH_OWNER_IDS_HASH = 3709968205L
        private val setPathOwnerIdsBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryResult3D", "set_path_owner_ids", SET_PATH_OWNER_IDS_HASH)
        }

        private const val GET_PATH_OWNER_IDS_HASH = 235988956L
        private val getPathOwnerIdsBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryResult3D", "get_path_owner_ids", GET_PATH_OWNER_IDS_HASH)
        }

        private const val SET_PATH_LENGTH_HASH = 373806689L
        private val setPathLengthBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryResult3D", "set_path_length", SET_PATH_LENGTH_HASH)
        }

        private const val GET_PATH_LENGTH_HASH = 1740695150L
        private val getPathLengthBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryResult3D", "get_path_length", GET_PATH_LENGTH_HASH)
        }

        private const val RESET_HASH = 3218959716L
        private val resetBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryResult3D", "reset", RESET_HASH)
        }
    }
}
