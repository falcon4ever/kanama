package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2

/**
 * Represents the result of a 2D pathfinding query.
 *
 * Generated from Godot docs: NavigationPathQueryResult2D
 */
class NavigationPathQueryResult2D(handle: GodotHandle) : RefCounted(handle) {
    var path: List<Vector2>
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
     * `NavigationServer2D.map_get_path`.
     *
     * Generated from Godot docs: NavigationPathQueryResult2D.set_path
     */
    fun setPath(path: List<Vector2>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedVector2ListArg(Binds.setPathBind, segment, path)
    }

    /**
     * The resulting path array from the navigation query. All path array positions are in global
     * coordinates. Without customized query parameters this is the same path as returned by
     * `NavigationServer2D.map_get_path`.
     *
     * Generated from Godot docs: NavigationPathQueryResult2D.get_path
     */
    fun getPath(): List<Vector2> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(Binds.getPathBind, segment)
    }

    /**
     * The type of navigation primitive (region or link) that each point of the path goes through.
     *
     * Generated from Godot docs: NavigationPathQueryResult2D.set_path_types
     */
    fun setPathTypes(pathTypes: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt32ListArg(Binds.setPathTypesBind, segment, pathTypes)
    }

    /**
     * The type of navigation primitive (region or link) that each point of the path goes through.
     *
     * Generated from Godot docs: NavigationPathQueryResult2D.get_path_types
     */
    fun getPathTypes(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(Binds.getPathTypesBind, segment)
    }

    /**
     * The `RID`s of the regions and links that each point of the path goes through.
     *
     * Generated from Godot docs: NavigationPathQueryResult2D.set_path_rids
     */
    fun setPathRids(pathRids: List<RID>) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDListArg(Binds.setPathRidsBind, segment, pathRids)
    }

    /**
     * The `RID`s of the regions and links that each point of the path goes through.
     *
     * Generated from Godot docs: NavigationPathQueryResult2D.get_path_rids
     */
    fun getPathRids(): List<RID> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRIDList(Binds.getPathRidsBind, segment)
    }

    /**
     * The `ObjectID`s of the `Object`s which manage the regions and links each point of the path goes
     * through.
     *
     * Generated from Godot docs: NavigationPathQueryResult2D.set_path_owner_ids
     */
    fun setPathOwnerIds(pathOwnerIds: List<Long>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt64ListArg(Binds.setPathOwnerIdsBind, segment, pathOwnerIds)
    }

    /**
     * The `ObjectID`s of the `Object`s which manage the regions and links each point of the path goes
     * through.
     *
     * Generated from Godot docs: NavigationPathQueryResult2D.get_path_owner_ids
     */
    fun getPathOwnerIds(): List<Long> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt64List(Binds.getPathOwnerIdsBind, segment)
    }

    /**
     * Returns the length of the path.
     *
     * Generated from Godot docs: NavigationPathQueryResult2D.set_path_length
     */
    fun setPathLength(length: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPathLengthBind, segment, length)
    }

    /**
     * Returns the length of the path.
     *
     * Generated from Godot docs: NavigationPathQueryResult2D.get_path_length
     */
    fun getPathLength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPathLengthBind, segment)
    }

    /**
     * Reset the result object to its initial state. This is useful to reuse the object across multiple
     * queries.
     *
     * Generated from Godot docs: NavigationPathQueryResult2D.reset
     */
    fun reset() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.resetBind, segment)
    }

    /**
     * Godot's `NavigationPathQueryResult2D.PathSegmentType` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`NavigationPathQueryResult2D.PathSegmentType.<NAME>`).
     *
     * Generated from Godot docs: NavigationPathQueryResult2D.PathSegmentType
     */
    @JvmInline
    value class PathSegmentType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * This segment of the path goes through a region.
             *
             * Generated from Godot docs: NavigationPathQueryResult2D.PATH_SEGMENT_TYPE_REGION
             */
            val REGION: PathSegmentType get() = PathSegmentType(0L)
            /**
             * This segment of the path goes through a link.
             *
             * Generated from Godot docs: NavigationPathQueryResult2D.PATH_SEGMENT_TYPE_LINK
             */
            val LINK: PathSegmentType get() = PathSegmentType(1L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): NavigationPathQueryResult2D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): NavigationPathQueryResult2D? =
            if (handle.address() == 0L) null else RefCounted.owned(NavigationPathQueryResult2D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): NavigationPathQueryResult2D? =
            if (handle.address() == 0L) null else NavigationPathQueryResult2D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_PATH_HASH = 1509147220L
        @JvmField
        val setPathBind =
            ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "set_path", SET_PATH_HASH)

        private const val GET_PATH_HASH = 2961356807L
        @JvmField
        val getPathBind =
            ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "get_path", GET_PATH_HASH)

        private const val SET_PATH_TYPES_HASH = 3614634198L
        @JvmField
        val setPathTypesBind =
            ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "set_path_types", SET_PATH_TYPES_HASH)

        private const val GET_PATH_TYPES_HASH = 1930428628L
        @JvmField
        val getPathTypesBind =
            ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "get_path_types", GET_PATH_TYPES_HASH)

        private const val SET_PATH_RIDS_HASH = 381264803L
        @JvmField
        val setPathRidsBind =
            ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "set_path_rids", SET_PATH_RIDS_HASH)

        private const val GET_PATH_RIDS_HASH = 3995934104L
        @JvmField
        val getPathRidsBind =
            ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "get_path_rids", GET_PATH_RIDS_HASH)

        private const val SET_PATH_OWNER_IDS_HASH = 3709968205L
        @JvmField
        val setPathOwnerIdsBind =
            ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "set_path_owner_ids", SET_PATH_OWNER_IDS_HASH)

        private const val GET_PATH_OWNER_IDS_HASH = 235988956L
        @JvmField
        val getPathOwnerIdsBind =
            ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "get_path_owner_ids", GET_PATH_OWNER_IDS_HASH)

        private const val SET_PATH_LENGTH_HASH = 373806689L
        @JvmField
        val setPathLengthBind =
            ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "set_path_length", SET_PATH_LENGTH_HASH)

        private const val GET_PATH_LENGTH_HASH = 1740695150L
        @JvmField
        val getPathLengthBind =
            ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "get_path_length", GET_PATH_LENGTH_HASH)

        private const val RESET_HASH = 3218959716L
        @JvmField
        val resetBind =
            ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "reset", RESET_HASH)
    }
}
