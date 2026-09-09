package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for NavigationPathQueryParameters3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NavigationPathQueryParameters3D waits on: ptrcallNoArgsRetRIDList,
//   ptrcallWithRIDListArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * The list of region `RID`s that will be included by the path query. Use
 * `NavigationRegion3D.get_rid` to get the `RID` associated with a `NavigationRegion3D` node. If
 * left empty all regions are included. If a region ends up being both included and excluded at the
 * same time it will be excluded. Note: The returned array is copied and any changes to it will not
 * update the original property value. To update the value you need to modify the returned array,
 * and then set it to the property again.
 *
 * Generated from Godot docs: NavigationPathQueryParameters3D.set_included_regions
 */
fun NavigationPathQueryParameters3D.setIncludedRegions(regions: List<RID>) {
    checkOpen()
    ObjectCalls.ptrcallWithRIDListArg(setIncludedRegionsBind, handle, regions)
}

/**
 * The list of region `RID`s that will be included by the path query. Use
 * `NavigationRegion3D.get_rid` to get the `RID` associated with a `NavigationRegion3D` node. If
 * left empty all regions are included. If a region ends up being both included and excluded at the
 * same time it will be excluded. Note: The returned array is copied and any changes to it will not
 * update the original property value. To update the value you need to modify the returned array,
 * and then set it to the property again.
 *
 * Generated from Godot docs: NavigationPathQueryParameters3D.get_included_regions
 */
fun NavigationPathQueryParameters3D.getIncludedRegions(): List<RID> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetRIDList(getIncludedRegionsBind, handle)
}

/**
 * The list of region `RID`s that will be excluded from the path query. Use
 * `NavigationRegion3D.get_rid` to get the `RID` associated with a `NavigationRegion3D` node. Note:
 * The returned array is copied and any changes to it will not update the original property value.
 * To update the value you need to modify the returned array, and then set it to the property
 * again.
 *
 * Generated from Godot docs: NavigationPathQueryParameters3D.set_excluded_regions
 */
fun NavigationPathQueryParameters3D.setExcludedRegions(regions: List<RID>) {
    checkOpen()
    ObjectCalls.ptrcallWithRIDListArg(setExcludedRegionsBind, handle, regions)
}

/**
 * The list of region `RID`s that will be excluded from the path query. Use
 * `NavigationRegion3D.get_rid` to get the `RID` associated with a `NavigationRegion3D` node. Note:
 * The returned array is copied and any changes to it will not update the original property value.
 * To update the value you need to modify the returned array, and then set it to the property
 * again.
 *
 * Generated from Godot docs: NavigationPathQueryParameters3D.get_excluded_regions
 */
fun NavigationPathQueryParameters3D.getExcludedRegions(): List<RID> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetRIDList(getExcludedRegionsBind, handle)
}

var NavigationPathQueryParameters3D.excludedRegions: List<RID>
    @JvmName("excludedRegionsProperty")
    get() = getExcludedRegions()
    @JvmName("setExcludedRegionsProperty")
    set(value) = setExcludedRegions(value)

var NavigationPathQueryParameters3D.includedRegions: List<RID>
    @JvmName("includedRegionsProperty")
    get() = getIncludedRegions()
    @JvmName("setIncludedRegionsProperty")
    set(value) = setIncludedRegions(value)

private const val SET_INCLUDED_REGIONS_HASH = 381264803L
private val setIncludedRegionsBind by lazy {
    ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "set_included_regions", SET_INCLUDED_REGIONS_HASH)
}

private const val GET_INCLUDED_REGIONS_HASH = 3995934104L
private val getIncludedRegionsBind by lazy {
    ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "get_included_regions", GET_INCLUDED_REGIONS_HASH)
}

private const val SET_EXCLUDED_REGIONS_HASH = 381264803L
private val setExcludedRegionsBind by lazy {
    ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "set_excluded_regions", SET_EXCLUDED_REGIONS_HASH)
}

private const val GET_EXCLUDED_REGIONS_HASH = 3995934104L
private val getExcludedRegionsBind by lazy {
    ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "get_excluded_regions", GET_EXCLUDED_REGIONS_HASH)
}
