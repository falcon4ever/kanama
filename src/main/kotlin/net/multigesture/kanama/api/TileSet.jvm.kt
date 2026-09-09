package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2i

// GENERATED desktop/Android companion for TileSet (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TileSet waits on: ptrcallWithIntArgRetString, ptrcallWithIntVector2iArgsRetArray,
//   ptrcallWithIntVector2iIntArgsRetArray, ptrcallWithTwoIntArgsRetString
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns a terrain's name.
 *
 * Generated from Godot docs: TileSet.get_terrain_name
 */
fun TileSet.getTerrainName(terrainSet: Int, terrainIndex: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoIntArgsRetString(getTerrainNameBind, handle, terrainSet, terrainIndex)
}

/**
 * Returns the name of the custom data layer identified by the given index.
 *
 * Generated from Godot docs: TileSet.get_custom_data_layer_name
 */
fun TileSet.getCustomDataLayerName(layerIndex: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetString(getCustomDataLayerNameBind, handle, layerIndex)
}

/**
 * Returns the coordinate-level proxy for the given identifiers. The returned array contains the
 * two target identifiers of the proxy (source ID and atlas coordinates ID). If the TileSet has no
 * proxy for the given identifiers, returns an empty Array.
 *
 * Generated from Godot docs: TileSet.get_coords_level_tile_proxy
 */
fun TileSet.getCoordsLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithIntVector2iArgsRetArray(getCoordsLevelTileProxyBind, handle, sourceFrom, coordsFrom)
}

/**
 * Returns the alternative-level proxy for the given identifiers. The returned array contains the
 * three proxie's target identifiers (source ID, atlas coords ID and alternative tile ID). If the
 * TileSet has no proxy for the given identifiers, returns an empty Array.
 *
 * Generated from Godot docs: TileSet.get_alternative_level_tile_proxy
 */
fun TileSet.getAlternativeLevelTileProxy(sourceFrom: Int, coordsFrom: Vector2i, alternativeFrom: Int): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithIntVector2iIntArgsRetArray(getAlternativeLevelTileProxyBind, handle, sourceFrom, coordsFrom, alternativeFrom)
}

/**
 * According to the configured proxies, maps the provided identifiers to a new set of identifiers.
 * The source ID, atlas coordinates ID and alternative tile ID are returned as a 3 elements Array.
 * This function first look for matching alternative-level proxies, then coordinates-level proxies,
 * then source-level proxies. If no proxy corresponding to provided identifiers are found, returns
 * the same values the ones used as arguments.
 *
 * Generated from Godot docs: TileSet.map_tile_proxy
 */
fun TileSet.mapTileProxy(sourceFrom: Int, coordsFrom: Vector2i, alternativeFrom: Int): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithIntVector2iIntArgsRetArray(mapTileProxyBind, handle, sourceFrom, coordsFrom, alternativeFrom)
}

private const val GET_TERRAIN_NAME_HASH = 1391810591L
private val getTerrainNameBind by lazy {
    ObjectCalls.getMethodBind("TileSet", "get_terrain_name", GET_TERRAIN_NAME_HASH)
}

private const val GET_CUSTOM_DATA_LAYER_NAME_HASH = 844755477L
private val getCustomDataLayerNameBind by lazy {
    ObjectCalls.getMethodBind("TileSet", "get_custom_data_layer_name", GET_CUSTOM_DATA_LAYER_NAME_HASH)
}

private const val GET_COORDS_LEVEL_TILE_PROXY_HASH = 2856536371L
private val getCoordsLevelTileProxyBind by lazy {
    ObjectCalls.getMethodBind("TileSet", "get_coords_level_tile_proxy", GET_COORDS_LEVEL_TILE_PROXY_HASH)
}

private const val GET_ALTERNATIVE_LEVEL_TILE_PROXY_HASH = 2303761075L
private val getAlternativeLevelTileProxyBind by lazy {
    ObjectCalls.getMethodBind("TileSet", "get_alternative_level_tile_proxy", GET_ALTERNATIVE_LEVEL_TILE_PROXY_HASH)
}

private const val MAP_TILE_PROXY_HASH = 4267935328L
private val mapTileProxyBind by lazy {
    ObjectCalls.getMethodBind("TileSet", "map_tile_proxy", MAP_TILE_PROXY_HASH)
}
