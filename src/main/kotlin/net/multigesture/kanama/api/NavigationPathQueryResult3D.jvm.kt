package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for NavigationPathQueryResult3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NavigationPathQueryResult3D waits on: ptrcallNoArgsRetPackedInt64List,
//   ptrcallNoArgsRetPackedVector3List, ptrcallNoArgsRetRIDList, ptrcallWithPackedInt32ListArg,
//   ptrcallWithPackedInt64ListArg, ptrcallWithPackedVector3ListArg, ptrcallWithRIDListArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * The resulting path array from the navigation query. All path array positions are in global
 * coordinates. Without customized query parameters this is the same path as returned by
 * `NavigationServer3D.map_get_path`.
 *
 * Generated from Godot docs: NavigationPathQueryResult3D.set_path
 */
fun NavigationPathQueryResult3D.setPath(path: List<Vector3>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector3ListArg(setPathBind, handle, path)
}

/**
 * The resulting path array from the navigation query. All path array positions are in global
 * coordinates. Without customized query parameters this is the same path as returned by
 * `NavigationServer3D.map_get_path`.
 *
 * Generated from Godot docs: NavigationPathQueryResult3D.get_path
 */
fun NavigationPathQueryResult3D.getPath(): List<Vector3> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getPathBind, handle)
}

/**
 * The type of navigation primitive (region or link) that each point of the path goes through.
 *
 * Generated from Godot docs: NavigationPathQueryResult3D.set_path_types
 */
fun NavigationPathQueryResult3D.setPathTypes(pathTypes: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedInt32ListArg(setPathTypesBind, handle, pathTypes)
}

/**
 * The `RID`s of the regions and links that each point of the path goes through.
 *
 * Generated from Godot docs: NavigationPathQueryResult3D.set_path_rids
 */
fun NavigationPathQueryResult3D.setPathRids(pathRids: List<RID>) {
    checkOpen()
    ObjectCalls.ptrcallWithRIDListArg(setPathRidsBind, handle, pathRids)
}

/**
 * The `RID`s of the regions and links that each point of the path goes through.
 *
 * Generated from Godot docs: NavigationPathQueryResult3D.get_path_rids
 */
fun NavigationPathQueryResult3D.getPathRids(): List<RID> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetRIDList(getPathRidsBind, handle)
}

/**
 * The `ObjectID`s of the `Object`s which manage the regions and links each point of the path goes
 * through.
 *
 * Generated from Godot docs: NavigationPathQueryResult3D.set_path_owner_ids
 */
fun NavigationPathQueryResult3D.setPathOwnerIds(pathOwnerIds: List<Long>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedInt64ListArg(setPathOwnerIdsBind, handle, pathOwnerIds)
}

/**
 * The `ObjectID`s of the `Object`s which manage the regions and links each point of the path goes
 * through.
 *
 * Generated from Godot docs: NavigationPathQueryResult3D.get_path_owner_ids
 */
fun NavigationPathQueryResult3D.getPathOwnerIds(): List<Long> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetPackedInt64List(getPathOwnerIdsBind, handle)
}

var NavigationPathQueryResult3D.path: List<Vector3>
    @JvmName("pathProperty")
    get() = getPath()
    @JvmName("setPathProperty")
    set(value) = setPath(value)

var NavigationPathQueryResult3D.pathRids: List<RID>
    @JvmName("pathRidsProperty")
    get() = getPathRids()
    @JvmName("setPathRidsProperty")
    set(value) = setPathRids(value)

var NavigationPathQueryResult3D.pathOwnerIds: List<Long>
    @JvmName("pathOwnerIdsProperty")
    get() = getPathOwnerIds()
    @JvmName("setPathOwnerIdsProperty")
    set(value) = setPathOwnerIds(value)

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
