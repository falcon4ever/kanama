package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.Vector3i

/**
 * Node for 3D tile-based maps.
 *
 * Generated from Godot docs: GridMap
 */
class GridMap(handle: GodotHandle) : Node3D(handle) {
    var meshLibrary: MeshLibrary?
        @JvmName("meshLibraryProperty")
        get() = getMeshLibrary()
        @JvmName("setMeshLibraryProperty")
        set(value) = setMeshLibrary(value)

    var physicsMaterial: PhysicsMaterial?
        @JvmName("physicsMaterialProperty")
        get() = getPhysicsMaterial()
        @JvmName("setPhysicsMaterialProperty")
        set(value) = setPhysicsMaterial(value)

    var cellSize: Vector3
        @JvmName("cellSizeProperty")
        get() = getCellSize()
        @JvmName("setCellSizeProperty")
        set(value) = setCellSize(value)

    var cellOctantSize: Int
        @JvmName("cellOctantSizeProperty")
        get() = getOctantSize()
        @JvmName("setCellOctantSizeProperty")
        set(value) = setOctantSize(value)

    var cellCenterX: Boolean
        @JvmName("cellCenterXProperty")
        get() = getCenterX()
        @JvmName("setCellCenterXProperty")
        set(value) = setCenterX(value)

    var cellCenterY: Boolean
        @JvmName("cellCenterYProperty")
        get() = getCenterY()
        @JvmName("setCellCenterYProperty")
        set(value) = setCenterY(value)

    var cellCenterZ: Boolean
        @JvmName("cellCenterZProperty")
        get() = getCenterZ()
        @JvmName("setCellCenterZProperty")
        set(value) = setCenterZ(value)

    var cellScale: Double
        @JvmName("cellScaleProperty")
        get() = getCellScale()
        @JvmName("setCellScaleProperty")
        set(value) = setCellScale(value)

    var collisionLayer: Long
        @JvmName("collisionLayerProperty")
        get() = getCollisionLayer()
        @JvmName("setCollisionLayerProperty")
        set(value) = setCollisionLayer(value)

    var collisionMask: Long
        @JvmName("collisionMaskProperty")
        get() = getCollisionMask()
        @JvmName("setCollisionMaskProperty")
        set(value) = setCollisionMask(value)

    var collisionPriority: Double
        @JvmName("collisionPriorityProperty")
        get() = getCollisionPriority()
        @JvmName("setCollisionPriorityProperty")
        set(value) = setCollisionPriority(value)

    var collisionVisibilityMode: GridMap.DebugVisibilityMode
        @JvmName("collisionVisibilityModeProperty")
        get() = getCollisionVisibilityMode()
        @JvmName("setCollisionVisibilityModeProperty")
        set(value) = setCollisionVisibilityMode(value)

    var bakeNavigation: Boolean
        @JvmName("bakeNavigationProperty")
        get() = isBakingNavigation()
        @JvmName("setBakeNavigationProperty")
        set(value) = setBakeNavigation(value)

    /**
     * The physics layers this GridMap is in. GridMaps act as static bodies, meaning they aren't
     * affected by gravity or other forces. They only affect other physics bodies that collide with
     * them.
     *
     * Generated from Godot docs: GridMap.set_collision_layer
     */
    fun setCollisionLayer(layer: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCollisionLayerBind, segment, layer)
    }

    /**
     * The physics layers this GridMap is in. GridMaps act as static bodies, meaning they aren't
     * affected by gravity or other forces. They only affect other physics bodies that collide with
     * them.
     *
     * Generated from Godot docs: GridMap.get_collision_layer
     */
    fun getCollisionLayer(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getCollisionLayerBind, segment)
    }

    /**
     * The physics layers this GridMap detects collisions in. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: GridMap.set_collision_mask
     */
    fun setCollisionMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCollisionMaskBind, segment, mask)
    }

    /**
     * The physics layers this GridMap detects collisions in. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: GridMap.get_collision_mask
     */
    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getCollisionMaskBind, segment)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `collision_mask`, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: GridMap.set_collision_mask_value
     */
    fun setCollisionMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setCollisionMaskValueBind, segment, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `collision_mask` is enabled, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: GridMap.get_collision_mask_value
     */
    fun getCollisionMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getCollisionMaskValueBind, segment, layerNumber)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `collision_layer`, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: GridMap.set_collision_layer_value
     */
    fun setCollisionLayerValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setCollisionLayerValueBind, segment, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `collision_layer` is enabled, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: GridMap.get_collision_layer_value
     */
    fun getCollisionLayerValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getCollisionLayerValueBind, segment, layerNumber)
    }

    /**
     * The priority used to solve colliding when occurring penetration. The higher the priority is, the
     * lower the penetration into the object will be. This can for example be used to prevent the
     * player from breaking through the boundaries of a level.
     *
     * Generated from Godot docs: GridMap.set_collision_priority
     */
    fun setCollisionPriority(priority: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setCollisionPriorityBind, segment, priority)
    }

    /**
     * The priority used to solve colliding when occurring penetration. The higher the priority is, the
     * lower the penetration into the object will be. This can for example be used to prevent the
     * player from breaking through the boundaries of a level.
     *
     * Generated from Godot docs: GridMap.get_collision_priority
     */
    fun getCollisionPriority(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCollisionPriorityBind, segment)
    }

    /**
     * Show or hide the `GridMap`'s collision shapes. If set to `DebugVisibilityMode.DEFAULT`, this
     * depends on the show collision debug settings.
     *
     * Generated from Godot docs: GridMap.set_collision_visibility_mode
     */
    fun setCollisionVisibilityMode(visibilityMode: GridMap.DebugVisibilityMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setCollisionVisibilityModeBind, segment, visibilityMode.value)
    }

    /**
     * Show or hide the `GridMap`'s collision shapes. If set to `DebugVisibilityMode.DEFAULT`, this
     * depends on the show collision debug settings.
     *
     * Generated from Godot docs: GridMap.get_collision_visibility_mode
     */
    fun getCollisionVisibilityMode(): GridMap.DebugVisibilityMode {
        return GridMap.DebugVisibilityMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getCollisionVisibilityModeBind, segment))
    }

    /**
     * Overrides the default friction and bounce physics properties for the whole `GridMap`.
     *
     * Generated from Godot docs: GridMap.set_physics_material
     */
    fun setPhysicsMaterial(material: PhysicsMaterial?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setPhysicsMaterialBind, segment, listOf(material?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(material)
    }

    /**
     * Overrides the default friction and bounce physics properties for the whole `GridMap`.
     *
     * Generated from Godot docs: GridMap.get_physics_material
     */
    fun getPhysicsMaterial(): PhysicsMaterial? {
        return PhysicsMaterial.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getPhysicsMaterialBind, segment))
    }

    /**
     * If `true`, this GridMap creates a navigation region for each cell that uses a `mesh_library`
     * item with a navigation mesh. The created navigation region will use the navigation layers
     * bitmask assigned to the `MeshLibrary`'s item.
     *
     * Generated from Godot docs: GridMap.set_bake_navigation
     */
    fun setBakeNavigation(bakeNavigation: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setBakeNavigationBind, segment, bakeNavigation)
    }

    /**
     * If `true`, this GridMap creates a navigation region for each cell that uses a `mesh_library`
     * item with a navigation mesh. The created navigation region will use the navigation layers
     * bitmask assigned to the `MeshLibrary`'s item.
     *
     * Generated from Godot docs: GridMap.is_baking_navigation
     */
    fun isBakingNavigation(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isBakingNavigationBind, segment)
    }

    /**
     * Sets the `RID` of the navigation map this GridMap node should use for its cell baked navigation
     * meshes.
     *
     * Generated from Godot docs: GridMap.set_navigation_map
     */
    fun setNavigationMap(navigationMap: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.setNavigationMapBind, segment, navigationMap)
    }

    /**
     * Returns the `RID` of the navigation map this GridMap node uses for its cell baked navigation
     * meshes. This function returns always the map set on the GridMap node and not the map on the
     * NavigationServer. If the map is changed directly with the NavigationServer API the GridMap node
     * will not be aware of the map change.
     *
     * Generated from Godot docs: GridMap.get_navigation_map
     */
    fun getNavigationMap(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getNavigationMapBind, segment)
    }

    /**
     * The assigned `MeshLibrary`.
     *
     * Generated from Godot docs: GridMap.set_mesh_library
     */
    fun setMeshLibrary(meshLibrary: MeshLibrary?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setMeshLibraryBind, segment, listOf(meshLibrary?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(meshLibrary)
    }

    /**
     * The assigned `MeshLibrary`.
     *
     * Generated from Godot docs: GridMap.get_mesh_library
     */
    fun getMeshLibrary(): MeshLibrary? {
        return MeshLibrary.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMeshLibraryBind, segment))
    }

    /**
     * The dimensions of the grid's cells. This does not affect the size of the meshes. See
     * `cell_scale`.
     *
     * Generated from Godot docs: GridMap.set_cell_size
     */
    fun setCellSize(size: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.setCellSizeBind, segment, size)
    }

    /**
     * The dimensions of the grid's cells. This does not affect the size of the meshes. See
     * `cell_scale`.
     *
     * Generated from Godot docs: GridMap.get_cell_size
     */
    fun getCellSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getCellSizeBind, segment)
    }

    /**
     * The scale of the cell items. This does not affect the size of the grid cells themselves, only
     * the items in them. This can be used to make cell items overlap their neighbors.
     *
     * Generated from Godot docs: GridMap.set_cell_scale
     */
    fun setCellScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setCellScaleBind, segment, scale)
    }

    /**
     * The scale of the cell items. This does not affect the size of the grid cells themselves, only
     * the items in them. This can be used to make cell items overlap their neighbors.
     *
     * Generated from Godot docs: GridMap.get_cell_scale
     */
    fun getCellScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCellScaleBind, segment)
    }

    /**
     * The size of each octant measured in number of cells. This applies to all three axis.
     *
     * Generated from Godot docs: GridMap.set_octant_size
     */
    fun setOctantSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setOctantSizeBind, segment, size)
    }

    /**
     * The size of each octant measured in number of cells. This applies to all three axis.
     *
     * Generated from Godot docs: GridMap.get_octant_size
     */
    fun getOctantSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getOctantSizeBind, segment)
    }

    /**
     * Sets the mesh index for the cell referenced by its grid coordinates. A negative item index such
     * as `INVALID_CELL_ITEM` will clear the cell. Optionally, the item's orientation can be passed.
     * For valid orientation values, see `get_orthogonal_index_from_basis`.
     *
     * Generated from Godot docs: GridMap.set_cell_item
     */
    fun setCellItem(position: Vector3i, item: Int, orientation: Int = 0) {
        ObjectCalls.ptrcallWithVector3iIntIntArgs(Binds.setCellItemBind, segment, position, item, orientation)
    }

    /**
     * The `MeshLibrary` item index located at the given grid coordinates. If the cell is empty,
     * `INVALID_CELL_ITEM` will be returned.
     *
     * Generated from Godot docs: GridMap.get_cell_item
     */
    fun getCellItem(position: Vector3i): Int {
        return ObjectCalls.ptrcallWithVector3iArgRetInt(Binds.getCellItemBind, segment, position)
    }

    /**
     * The orientation of the cell at the given grid coordinates. `-1` is returned if the cell is
     * empty.
     *
     * Generated from Godot docs: GridMap.get_cell_item_orientation
     */
    fun getCellItemOrientation(position: Vector3i): Int {
        return ObjectCalls.ptrcallWithVector3iArgRetInt(Binds.getCellItemOrientationBind, segment, position)
    }

    /**
     * Returns the basis that gives the specified cell its orientation.
     *
     * Generated from Godot docs: GridMap.get_cell_item_basis
     */
    fun getCellItemBasis(position: Vector3i): Basis {
        return ObjectCalls.ptrcallWithVector3iArgRetBasis(Binds.getCellItemBasisBind, segment, position)
    }

    /**
     * Returns one of 24 possible rotations that lie along the vectors (x,y,z) with each component
     * being either -1, 0, or 1. For further details, refer to the Godot source code.
     *
     * Generated from Godot docs: GridMap.get_basis_with_orthogonal_index
     */
    fun getBasisWithOrthogonalIndex(index: Int): Basis {
        return ObjectCalls.ptrcallWithIntArgRetBasis(Binds.getBasisWithOrthogonalIndexBind, segment, index)
    }

    /**
     * This function considers a discretization of rotations into 24 points on unit sphere, lying along
     * the vectors (x,y,z) with each component being either -1, 0, or 1, and returns the index (in the
     * range from 0 to 23) of the point best representing the orientation of the object. For further
     * details, refer to the Godot source code.
     *
     * Generated from Godot docs: GridMap.get_orthogonal_index_from_basis
     */
    fun getOrthogonalIndexFromBasis(basis: Basis): Int {
        return ObjectCalls.ptrcallWithBasisArgRetInt(Binds.getOrthogonalIndexFromBasisBind, segment, basis)
    }

    /**
     * Returns the map coordinates of the cell containing the given `local_position`. If
     * `local_position` is in global coordinates, consider using `Node3D.to_local` before passing it to
     * this method. See also `map_to_local`.
     *
     * Generated from Godot docs: GridMap.local_to_map
     */
    fun localToMap(localPosition: Vector3): Vector3i {
        return ObjectCalls.ptrcallWithVector3ArgRetVector3i(Binds.localToMapBind, segment, localPosition)
    }

    /**
     * Returns the position of a grid cell in the GridMap's local coordinate space. To convert the
     * returned value into global coordinates, use `Node3D.to_global`. See also `local_to_map`.
     *
     * Generated from Godot docs: GridMap.map_to_local
     */
    fun mapToLocal(mapPosition: Vector3i): Vector3 {
        return ObjectCalls.ptrcallWithVector3iArgRetVector3(Binds.mapToLocalBind, segment, mapPosition)
    }

    /**
     * This method does nothing.
     *
     * Generated from Godot docs: GridMap.resource_changed
     */
    fun resourceChanged(resource: Resource?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.resourceChangedBind, segment, listOf(resource?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(resource)
    }

    /**
     * If `true`, grid items are centered on the X axis.
     *
     * Generated from Godot docs: GridMap.set_center_x
     */
    fun setCenterX(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCenterXBind, segment, enable)
    }

    /**
     * If `true`, grid items are centered on the X axis.
     *
     * Generated from Godot docs: GridMap.get_center_x
     */
    fun getCenterX(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getCenterXBind, segment)
    }

    /**
     * If `true`, grid items are centered on the Y axis.
     *
     * Generated from Godot docs: GridMap.set_center_y
     */
    fun setCenterY(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCenterYBind, segment, enable)
    }

    /**
     * If `true`, grid items are centered on the Y axis.
     *
     * Generated from Godot docs: GridMap.get_center_y
     */
    fun getCenterY(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getCenterYBind, segment)
    }

    /**
     * If `true`, grid items are centered on the Z axis.
     *
     * Generated from Godot docs: GridMap.set_center_z
     */
    fun setCenterZ(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCenterZBind, segment, enable)
    }

    /**
     * If `true`, grid items are centered on the Z axis.
     *
     * Generated from Godot docs: GridMap.get_center_z
     */
    fun getCenterZ(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getCenterZBind, segment)
    }

    /**
     * Clear all cells.
     *
     * Generated from Godot docs: GridMap.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(Binds.clearBind, segment)
    }

    /**
     * Returns an array of `Vector3` with the non-empty cell coordinates in the grid map.
     *
     * Generated from Godot docs: GridMap.get_used_cells
     */
    fun getUsedCells(): List<Vector3i> {
        return ObjectCalls.ptrcallNoArgsRetVector3iList(Binds.getUsedCellsBind, segment)
    }

    /**
     * Returns an array of all cells with the given item index specified in `item`.
     *
     * Generated from Godot docs: GridMap.get_used_cells_by_item
     */
    fun getUsedCellsByItem(item: Int): List<Vector3i> {
        return ObjectCalls.ptrcallWithIntArgRetVector3iList(Binds.getUsedCellsByItemBind, segment, item)
    }

    /**
     * Returns an array of `Vector3i`s with the octant coordinates of the non-empty octants in the grid
     * map.
     *
     * Generated from Godot docs: GridMap.get_used_octants
     */
    fun getUsedOctants(): List<Vector3i> {
        return ObjectCalls.ptrcallNoArgsRetVector3iList(Binds.getUsedOctantsBind, segment)
    }

    /**
     * Returns an array of `Vector3i`s with the octant coordinates of the octants that use the
     * specified `item` in the grid map.
     *
     * Generated from Godot docs: GridMap.get_used_octants_by_item
     */
    fun getUsedOctantsByItem(item: Int): List<Vector3i> {
        return ObjectCalls.ptrcallWithIntArgRetVector3iList(Binds.getUsedOctantsByItemBind, segment, item)
    }

    /**
     * Returns an array of `Vector3i`s with the cell coordinates of non-empty cells inside the octant
     * at `octant_coords`.
     *
     * Generated from Godot docs: GridMap.get_used_cells_in_octant
     */
    fun getUsedCellsInOctant(octantCoords: Vector3i): List<Vector3i> {
        return ObjectCalls.ptrcallWithVector3iArgRetVector3iList(Binds.getUsedCellsInOctantBind, segment, octantCoords)
    }

    /**
     * Returns an array of `Vector3i`s with the cell coordinates of cells inside the octant at
     * `octant_coords` that use the specified cell `item`.
     *
     * Generated from Godot docs: GridMap.get_used_cells_in_octant_by_item
     */
    fun getUsedCellsInOctantByItem(octantCoords: Vector3i, item: Int): List<Vector3i> {
        return ObjectCalls.ptrcallWithVector3iAndIntArgRetVector3iList(Binds.getUsedCellsInOctantByItemBind, segment, octantCoords, item)
    }

    /**
     * Returns an array of `Vector3i` octant coordinates that are inside the given `bounds`, including
     * octants that have no cells in use.
     *
     * Generated from Godot docs: GridMap.get_octants_in_bounds
     */
    fun getOctantsInBounds(bounds: AABB): List<Vector3i> {
        return ObjectCalls.ptrcallWithAABBArgRetVector3iList(Binds.getOctantsInBoundsBind, segment, bounds)
    }

    /**
     * Returns an array of `Vector3i`s with the octant coordinates of non-empty octants that are inside
     * the local `bounds`.
     *
     * Generated from Godot docs: GridMap.get_used_octants_in_bounds
     */
    fun getUsedOctantsInBounds(bounds: AABB): List<Vector3i> {
        return ObjectCalls.ptrcallWithAABBArgRetVector3iList(Binds.getUsedOctantsInBoundsBind, segment, bounds)
    }

    /**
     * Returns the `Vector3i` octant coordinates of the octant that the cell at `cell_coords` belongs
     * to.
     *
     * Generated from Godot docs: GridMap.get_octant_coords_from_cell_coords
     */
    fun getOctantCoordsFromCellCoords(cellCoords: Vector3i): Vector3i {
        return ObjectCalls.ptrcallWithVector3iArgRetVector3i(Binds.getOctantCoordsFromCellCoordsBind, segment, cellCoords)
    }

    /**
     * Returns an array of `Transform3D` and `Mesh` references corresponding to the non-empty cells in
     * the grid. The transforms are specified in local space. Even indices contain `Transform3D`s,
     * while odd indices contain `Mesh`es related to the `Transform3D` in the index preceding it.
     *
     * Generated from Godot docs: GridMap.get_meshes
     */
    fun getMeshes(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getMeshesBind, segment)
    }

    /**
     * Returns an array of `ArrayMesh`es and `Transform3D` references of all bake meshes that exist
     * within the current GridMap. Even indices contain `ArrayMesh`es, while odd indices contain
     * `Transform3D`s that are always equal to `Transform3D.IDENTITY`. This method relies on the output
     * of `make_baked_meshes`, which will be called with `gen_lightmap_uv` set to `true` and
     * `lightmap_uv_texel_size` set to `0.1` if it hasn't been called yet.
     *
     * Generated from Godot docs: GridMap.get_bake_meshes
     */
    fun getBakeMeshes(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getBakeMeshesBind, segment)
    }

    /**
     * Returns `RID` of a baked mesh with the given `idx`.
     *
     * Generated from Godot docs: GridMap.get_bake_mesh_instance
     */
    fun getBakeMeshInstance(idx: Int): RID {
        return ObjectCalls.ptrcallWithIntArgRetRID(Binds.getBakeMeshInstanceBind, segment, idx)
    }

    /**
     * Clears all baked meshes. See `make_baked_meshes`.
     *
     * Generated from Godot docs: GridMap.clear_baked_meshes
     */
    fun clearBakedMeshes() {
        ObjectCalls.ptrcallNoArgs(Binds.clearBakedMeshesBind, segment)
    }

    /**
     * Generates a baked mesh that represents all meshes in the assigned `MeshLibrary` for use with
     * `LightmapGI`. If `gen_lightmap_uv` is `true`, UV2 data will be generated for each mesh currently
     * used in the `GridMap`. Otherwise, only meshes that already have UV2 data present will be able to
     * use baked lightmaps. When generating UV2, `lightmap_uv_texel_size` controls the texel density
     * for lightmaps, with lower values resulting in more detailed lightmaps. `lightmap_uv_texel_size`
     * is ignored if `gen_lightmap_uv` is `false`. See also `get_bake_meshes`, which relies on the
     * output of this method. Note: Calling this method will not actually bake lightmaps, as lightmap
     * baking is performed using the `LightmapGI` node.
     *
     * Generated from Godot docs: GridMap.make_baked_meshes
     */
    fun makeBakedMeshes(genLightmapUv: Boolean = false, lightmapUvTexelSize: Double = 0.1) {
        ObjectCalls.ptrcallWithBoolAndDoubleArgs(Binds.makeBakedMeshesBind, segment, genLightmapUv, lightmapUvTexelSize)
    }

    /** Signal `cell_size_changed(cell_size: Vector3)`; see [TypedSignal]. */
    val cellSizeChanged: Signal1<Vector3>
        @JvmName("cellSizeChangedTypedSignal")
        get() = Signal1(this, "cell_size_changed", SignalArgType.valueOf<Vector3>("Vector3", Vector3::class))

    /** Signal `changed()`; see [TypedSignal]. */
    val changed: Signal0
        @JvmName("changedTypedSignal")
        get() = Signal0(this, "changed")

    object Signals {
        const val cellSizeChanged: String = "cell_size_changed"
        const val changed: String = "changed"
    }

    /**
     * Godot's `GridMap.DebugVisibilityMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`GridMap.DebugVisibilityMode.<NAME>`).
     *
     * Generated from Godot docs: GridMap.DebugVisibilityMode
     */
    @JvmInline
    value class DebugVisibilityMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Hide the collisions debug shapes in the editor, and use the debug settings to determine their
             * visibility in game (i.e. `SceneTree.debug_collisions_hint` or
             * `SceneTree.debug_navigation_hint`).
             *
             * Generated from Godot docs: GridMap.DEBUG_VISIBILITY_MODE_DEFAULT
             */
            val DEFAULT: DebugVisibilityMode get() = DebugVisibilityMode(0L)
            /**
             * Always show the collisions debug shapes.
             *
             * Generated from Godot docs: GridMap.DEBUG_VISIBILITY_MODE_FORCE_SHOW
             */
            val FORCE_SHOW: DebugVisibilityMode get() = DebugVisibilityMode(1L)
            /**
             * Always hide the collisions debug shapes.
             *
             * Generated from Godot docs: GridMap.DEBUG_VISIBILITY_MODE_FORCE_HIDE
             */
            val FORCE_HIDE: DebugVisibilityMode get() = DebugVisibilityMode(2L)
        }
    }

    companion object {
        const val INVALID_CELL_ITEM: Long = -1L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): GridMap? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GridMap? =
            if (handle.address() == 0L) null else GridMap(GodotHandle(handle))

        /** A new GridMap, GDScript's `GridMap.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): GridMap =
            GridMap(GodotHandle(requireConstructed(ObjectCalls.constructObject("GridMap"), "GridMap")))
    }

    private object Binds {
        private const val SET_COLLISION_LAYER_HASH = 1286410249L
        @JvmField
        val setCollisionLayerBind =
            ObjectCalls.getMethodBind("GridMap", "set_collision_layer", SET_COLLISION_LAYER_HASH)

        private const val GET_COLLISION_LAYER_HASH = 3905245786L
        @JvmField
        val getCollisionLayerBind =
            ObjectCalls.getMethodBind("GridMap", "get_collision_layer", GET_COLLISION_LAYER_HASH)

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        @JvmField
        val setCollisionMaskBind =
            ObjectCalls.getMethodBind("GridMap", "set_collision_mask", SET_COLLISION_MASK_HASH)

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        @JvmField
        val getCollisionMaskBind =
            ObjectCalls.getMethodBind("GridMap", "get_collision_mask", GET_COLLISION_MASK_HASH)

        private const val SET_COLLISION_MASK_VALUE_HASH = 300928843L
        @JvmField
        val setCollisionMaskValueBind =
            ObjectCalls.getMethodBind("GridMap", "set_collision_mask_value", SET_COLLISION_MASK_VALUE_HASH)

        private const val GET_COLLISION_MASK_VALUE_HASH = 1116898809L
        @JvmField
        val getCollisionMaskValueBind =
            ObjectCalls.getMethodBind("GridMap", "get_collision_mask_value", GET_COLLISION_MASK_VALUE_HASH)

        private const val SET_COLLISION_LAYER_VALUE_HASH = 300928843L
        @JvmField
        val setCollisionLayerValueBind =
            ObjectCalls.getMethodBind("GridMap", "set_collision_layer_value", SET_COLLISION_LAYER_VALUE_HASH)

        private const val GET_COLLISION_LAYER_VALUE_HASH = 1116898809L
        @JvmField
        val getCollisionLayerValueBind =
            ObjectCalls.getMethodBind("GridMap", "get_collision_layer_value", GET_COLLISION_LAYER_VALUE_HASH)

        private const val SET_COLLISION_PRIORITY_HASH = 373806689L
        @JvmField
        val setCollisionPriorityBind =
            ObjectCalls.getMethodBind("GridMap", "set_collision_priority", SET_COLLISION_PRIORITY_HASH)

        private const val GET_COLLISION_PRIORITY_HASH = 1740695150L
        @JvmField
        val getCollisionPriorityBind =
            ObjectCalls.getMethodBind("GridMap", "get_collision_priority", GET_COLLISION_PRIORITY_HASH)

        private const val SET_COLLISION_VISIBILITY_MODE_HASH = 4160694578L
        @JvmField
        val setCollisionVisibilityModeBind =
            ObjectCalls.getMethodBind("GridMap", "set_collision_visibility_mode", SET_COLLISION_VISIBILITY_MODE_HASH)

        private const val GET_COLLISION_VISIBILITY_MODE_HASH = 3729798365L
        @JvmField
        val getCollisionVisibilityModeBind =
            ObjectCalls.getMethodBind("GridMap", "get_collision_visibility_mode", GET_COLLISION_VISIBILITY_MODE_HASH)

        private const val SET_PHYSICS_MATERIAL_HASH = 1784508650L
        @JvmField
        val setPhysicsMaterialBind =
            ObjectCalls.getMethodBind("GridMap", "set_physics_material", SET_PHYSICS_MATERIAL_HASH)

        private const val GET_PHYSICS_MATERIAL_HASH = 2521850424L
        @JvmField
        val getPhysicsMaterialBind =
            ObjectCalls.getMethodBind("GridMap", "get_physics_material", GET_PHYSICS_MATERIAL_HASH)

        private const val SET_BAKE_NAVIGATION_HASH = 2586408642L
        @JvmField
        val setBakeNavigationBind =
            ObjectCalls.getMethodBind("GridMap", "set_bake_navigation", SET_BAKE_NAVIGATION_HASH)

        private const val IS_BAKING_NAVIGATION_HASH = 2240911060L
        @JvmField
        val isBakingNavigationBind =
            ObjectCalls.getMethodBind("GridMap", "is_baking_navigation", IS_BAKING_NAVIGATION_HASH)

        private const val SET_NAVIGATION_MAP_HASH = 2722037293L
        @JvmField
        val setNavigationMapBind =
            ObjectCalls.getMethodBind("GridMap", "set_navigation_map", SET_NAVIGATION_MAP_HASH)

        private const val GET_NAVIGATION_MAP_HASH = 2944877500L
        @JvmField
        val getNavigationMapBind =
            ObjectCalls.getMethodBind("GridMap", "get_navigation_map", GET_NAVIGATION_MAP_HASH)

        private const val SET_MESH_LIBRARY_HASH = 1488083439L
        @JvmField
        val setMeshLibraryBind =
            ObjectCalls.getMethodBind("GridMap", "set_mesh_library", SET_MESH_LIBRARY_HASH)

        private const val GET_MESH_LIBRARY_HASH = 3350993772L
        @JvmField
        val getMeshLibraryBind =
            ObjectCalls.getMethodBind("GridMap", "get_mesh_library", GET_MESH_LIBRARY_HASH)

        private const val SET_CELL_SIZE_HASH = 3460891852L
        @JvmField
        val setCellSizeBind =
            ObjectCalls.getMethodBind("GridMap", "set_cell_size", SET_CELL_SIZE_HASH)

        private const val GET_CELL_SIZE_HASH = 3360562783L
        @JvmField
        val getCellSizeBind =
            ObjectCalls.getMethodBind("GridMap", "get_cell_size", GET_CELL_SIZE_HASH)

        private const val SET_CELL_SCALE_HASH = 373806689L
        @JvmField
        val setCellScaleBind =
            ObjectCalls.getMethodBind("GridMap", "set_cell_scale", SET_CELL_SCALE_HASH)

        private const val GET_CELL_SCALE_HASH = 1740695150L
        @JvmField
        val getCellScaleBind =
            ObjectCalls.getMethodBind("GridMap", "get_cell_scale", GET_CELL_SCALE_HASH)

        private const val SET_OCTANT_SIZE_HASH = 1286410249L
        @JvmField
        val setOctantSizeBind =
            ObjectCalls.getMethodBind("GridMap", "set_octant_size", SET_OCTANT_SIZE_HASH)

        private const val GET_OCTANT_SIZE_HASH = 3905245786L
        @JvmField
        val getOctantSizeBind =
            ObjectCalls.getMethodBind("GridMap", "get_octant_size", GET_OCTANT_SIZE_HASH)

        private const val SET_CELL_ITEM_HASH = 3449088946L
        @JvmField
        val setCellItemBind =
            ObjectCalls.getMethodBind("GridMap", "set_cell_item", SET_CELL_ITEM_HASH)

        private const val GET_CELL_ITEM_HASH = 3724960147L
        @JvmField
        val getCellItemBind =
            ObjectCalls.getMethodBind("GridMap", "get_cell_item", GET_CELL_ITEM_HASH)

        private const val GET_CELL_ITEM_ORIENTATION_HASH = 3724960147L
        @JvmField
        val getCellItemOrientationBind =
            ObjectCalls.getMethodBind("GridMap", "get_cell_item_orientation", GET_CELL_ITEM_ORIENTATION_HASH)

        private const val GET_CELL_ITEM_BASIS_HASH = 3493604918L
        @JvmField
        val getCellItemBasisBind =
            ObjectCalls.getMethodBind("GridMap", "get_cell_item_basis", GET_CELL_ITEM_BASIS_HASH)

        private const val GET_BASIS_WITH_ORTHOGONAL_INDEX_HASH = 2816196998L
        @JvmField
        val getBasisWithOrthogonalIndexBind =
            ObjectCalls.getMethodBind("GridMap", "get_basis_with_orthogonal_index", GET_BASIS_WITH_ORTHOGONAL_INDEX_HASH)

        private const val GET_ORTHOGONAL_INDEX_FROM_BASIS_HASH = 4210359952L
        @JvmField
        val getOrthogonalIndexFromBasisBind =
            ObjectCalls.getMethodBind("GridMap", "get_orthogonal_index_from_basis", GET_ORTHOGONAL_INDEX_FROM_BASIS_HASH)

        private const val LOCAL_TO_MAP_HASH = 1257687843L
        @JvmField
        val localToMapBind =
            ObjectCalls.getMethodBind("GridMap", "local_to_map", LOCAL_TO_MAP_HASH)

        private const val MAP_TO_LOCAL_HASH = 1088329196L
        @JvmField
        val mapToLocalBind =
            ObjectCalls.getMethodBind("GridMap", "map_to_local", MAP_TO_LOCAL_HASH)

        private const val RESOURCE_CHANGED_HASH = 968641751L
        @JvmField
        val resourceChangedBind =
            ObjectCalls.getMethodBind("GridMap", "resource_changed", RESOURCE_CHANGED_HASH)

        private const val SET_CENTER_X_HASH = 2586408642L
        @JvmField
        val setCenterXBind =
            ObjectCalls.getMethodBind("GridMap", "set_center_x", SET_CENTER_X_HASH)

        private const val GET_CENTER_X_HASH = 36873697L
        @JvmField
        val getCenterXBind =
            ObjectCalls.getMethodBind("GridMap", "get_center_x", GET_CENTER_X_HASH)

        private const val SET_CENTER_Y_HASH = 2586408642L
        @JvmField
        val setCenterYBind =
            ObjectCalls.getMethodBind("GridMap", "set_center_y", SET_CENTER_Y_HASH)

        private const val GET_CENTER_Y_HASH = 36873697L
        @JvmField
        val getCenterYBind =
            ObjectCalls.getMethodBind("GridMap", "get_center_y", GET_CENTER_Y_HASH)

        private const val SET_CENTER_Z_HASH = 2586408642L
        @JvmField
        val setCenterZBind =
            ObjectCalls.getMethodBind("GridMap", "set_center_z", SET_CENTER_Z_HASH)

        private const val GET_CENTER_Z_HASH = 36873697L
        @JvmField
        val getCenterZBind =
            ObjectCalls.getMethodBind("GridMap", "get_center_z", GET_CENTER_Z_HASH)

        private const val CLEAR_HASH = 3218959716L
        @JvmField
        val clearBind =
            ObjectCalls.getMethodBind("GridMap", "clear", CLEAR_HASH)

        private const val GET_USED_CELLS_HASH = 3995934104L
        @JvmField
        val getUsedCellsBind =
            ObjectCalls.getMethodBind("GridMap", "get_used_cells", GET_USED_CELLS_HASH)

        private const val GET_USED_CELLS_BY_ITEM_HASH = 663333327L
        @JvmField
        val getUsedCellsByItemBind =
            ObjectCalls.getMethodBind("GridMap", "get_used_cells_by_item", GET_USED_CELLS_BY_ITEM_HASH)

        private const val GET_USED_OCTANTS_HASH = 3995934104L
        @JvmField
        val getUsedOctantsBind =
            ObjectCalls.getMethodBind("GridMap", "get_used_octants", GET_USED_OCTANTS_HASH)

        private const val GET_USED_OCTANTS_BY_ITEM_HASH = 663333327L
        @JvmField
        val getUsedOctantsByItemBind =
            ObjectCalls.getMethodBind("GridMap", "get_used_octants_by_item", GET_USED_OCTANTS_BY_ITEM_HASH)

        private const val GET_USED_CELLS_IN_OCTANT_HASH = 2658725580L
        @JvmField
        val getUsedCellsInOctantBind =
            ObjectCalls.getMethodBind("GridMap", "get_used_cells_in_octant", GET_USED_CELLS_IN_OCTANT_HASH)

        private const val GET_USED_CELLS_IN_OCTANT_BY_ITEM_HASH = 2384667821L
        @JvmField
        val getUsedCellsInOctantByItemBind =
            ObjectCalls.getMethodBind("GridMap", "get_used_cells_in_octant_by_item", GET_USED_CELLS_IN_OCTANT_BY_ITEM_HASH)

        private const val GET_OCTANTS_IN_BOUNDS_HASH = 2489849902L
        @JvmField
        val getOctantsInBoundsBind =
            ObjectCalls.getMethodBind("GridMap", "get_octants_in_bounds", GET_OCTANTS_IN_BOUNDS_HASH)

        private const val GET_USED_OCTANTS_IN_BOUNDS_HASH = 2489849902L
        @JvmField
        val getUsedOctantsInBoundsBind =
            ObjectCalls.getMethodBind("GridMap", "get_used_octants_in_bounds", GET_USED_OCTANTS_IN_BOUNDS_HASH)

        private const val GET_OCTANT_COORDS_FROM_CELL_COORDS_HASH = 2075501597L
        @JvmField
        val getOctantCoordsFromCellCoordsBind =
            ObjectCalls.getMethodBind("GridMap", "get_octant_coords_from_cell_coords", GET_OCTANT_COORDS_FROM_CELL_COORDS_HASH)

        private const val GET_MESHES_HASH = 3995934104L
        @JvmField
        val getMeshesBind =
            ObjectCalls.getMethodBind("GridMap", "get_meshes", GET_MESHES_HASH)

        private const val GET_BAKE_MESHES_HASH = 2915620761L
        @JvmField
        val getBakeMeshesBind =
            ObjectCalls.getMethodBind("GridMap", "get_bake_meshes", GET_BAKE_MESHES_HASH)

        private const val GET_BAKE_MESH_INSTANCE_HASH = 937000113L
        @JvmField
        val getBakeMeshInstanceBind =
            ObjectCalls.getMethodBind("GridMap", "get_bake_mesh_instance", GET_BAKE_MESH_INSTANCE_HASH)

        private const val CLEAR_BAKED_MESHES_HASH = 3218959716L
        @JvmField
        val clearBakedMeshesBind =
            ObjectCalls.getMethodBind("GridMap", "clear_baked_meshes", CLEAR_BAKED_MESHES_HASH)

        private const val MAKE_BAKED_MESHES_HASH = 3609286057L
        @JvmField
        val makeBakedMeshesBind =
            ObjectCalls.getMethodBind("GridMap", "make_baked_meshes", MAKE_BAKED_MESHES_HASH)
    }
}
