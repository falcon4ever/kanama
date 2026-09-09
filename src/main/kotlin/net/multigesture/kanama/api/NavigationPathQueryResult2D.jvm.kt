package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for NavigationPathQueryResult2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NavigationPathQueryResult2D waits on: ptrcallNoArgsRetPackedInt64List,
//   ptrcallNoArgsRetRIDList, ptrcallWithPackedInt32ListArg, ptrcallWithPackedInt64ListArg,
//   ptrcallWithPackedVector2ListArg, ptrcallWithRIDListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * The resulting path array from the navigation query. All path array positions are in global
 * coordinates. Without customized query parameters this is the same path as returned by
 * `NavigationServer2D.map_get_path`.
 *
 * Generated from Godot docs: NavigationPathQueryResult2D.set_path
 */
fun NavigationPathQueryResult2D.setPath(path: List<Vector2>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector2ListArg(setPathBind, handle, path)
}

/**
 * The type of navigation primitive (region or link) that each point of the path goes through.
 *
 * Generated from Godot docs: NavigationPathQueryResult2D.set_path_types
 */
fun NavigationPathQueryResult2D.setPathTypes(pathTypes: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedInt32ListArg(setPathTypesBind, handle, pathTypes)
}

/**
 * The `RID`s of the regions and links that each point of the path goes through.
 *
 * Generated from Godot docs: NavigationPathQueryResult2D.set_path_rids
 */
fun NavigationPathQueryResult2D.setPathRids(pathRids: List<RID>) {
    checkOpen()
    ObjectCalls.ptrcallWithRIDListArg(setPathRidsBind, handle, pathRids)
}

/**
 * The `RID`s of the regions and links that each point of the path goes through.
 *
 * Generated from Godot docs: NavigationPathQueryResult2D.get_path_rids
 */
fun NavigationPathQueryResult2D.getPathRids(): List<RID> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetRIDList(getPathRidsBind, handle)
}

/**
 * The `ObjectID`s of the `Object`s which manage the regions and links each point of the path goes
 * through.
 *
 * Generated from Godot docs: NavigationPathQueryResult2D.set_path_owner_ids
 */
fun NavigationPathQueryResult2D.setPathOwnerIds(pathOwnerIds: List<Long>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedInt64ListArg(setPathOwnerIdsBind, handle, pathOwnerIds)
}

/**
 * The `ObjectID`s of the `Object`s which manage the regions and links each point of the path goes
 * through.
 *
 * Generated from Godot docs: NavigationPathQueryResult2D.get_path_owner_ids
 */
fun NavigationPathQueryResult2D.getPathOwnerIds(): List<Long> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetPackedInt64List(getPathOwnerIdsBind, handle)
}

var NavigationPathQueryResult2D.pathRids: List<RID>
    @JvmName("pathRidsProperty")
    get() = getPathRids()
    @JvmName("setPathRidsProperty")
    set(value) = setPathRids(value)

var NavigationPathQueryResult2D.pathOwnerIds: List<Long>
    @JvmName("pathOwnerIdsProperty")
    get() = getPathOwnerIds()
    @JvmName("setPathOwnerIdsProperty")
    set(value) = setPathOwnerIds(value)

private const val SET_PATH_HASH = 1509147220L
private val setPathBind by lazy {
    ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "set_path", SET_PATH_HASH)
}

private const val SET_PATH_TYPES_HASH = 3614634198L
private val setPathTypesBind by lazy {
    ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "set_path_types", SET_PATH_TYPES_HASH)
}

private const val SET_PATH_RIDS_HASH = 381264803L
private val setPathRidsBind by lazy {
    ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "set_path_rids", SET_PATH_RIDS_HASH)
}

private const val GET_PATH_RIDS_HASH = 3995934104L
private val getPathRidsBind by lazy {
    ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "get_path_rids", GET_PATH_RIDS_HASH)
}

private const val SET_PATH_OWNER_IDS_HASH = 3709968205L
private val setPathOwnerIdsBind by lazy {
    ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "set_path_owner_ids", SET_PATH_OWNER_IDS_HASH)
}

private const val GET_PATH_OWNER_IDS_HASH = 235988956L
private val getPathOwnerIdsBind by lazy {
    ObjectCalls.getMethodBind("NavigationPathQueryResult2D", "get_path_owner_ids", GET_PATH_OWNER_IDS_HASH)
}
