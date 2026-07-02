package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.Vector3i

/**
 * Generated from Godot docs: GridMap
 */
class GridMap(handle: MemorySegment) : Node3D(handle) {
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

    var collisionVisibilityMode: Long
        @JvmName("collisionVisibilityModeProperty")
        get() = getCollisionVisibilityMode()
        @JvmName("setCollisionVisibilityModeProperty")
        set(value) = setCollisionVisibilityMode(value)

    var bakeNavigation: Boolean
        @JvmName("bakeNavigationProperty")
        get() = isBakingNavigation()
        @JvmName("setBakeNavigationProperty")
        set(value) = setBakeNavigation(value)

    fun setCollisionLayer(layer: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionLayerBind, handle, layer)
    }

    fun getCollisionLayer(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionLayerBind, handle)
    }

    fun setCollisionMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionMaskBind, handle, mask)
    }

    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionMaskBind, handle)
    }

    fun setCollisionMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCollisionMaskValueBind, handle, layerNumber, value)
    }

    fun getCollisionMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getCollisionMaskValueBind, handle, layerNumber)
    }

    fun setCollisionLayerValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCollisionLayerValueBind, handle, layerNumber, value)
    }

    fun getCollisionLayerValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getCollisionLayerValueBind, handle, layerNumber)
    }

    fun setCollisionPriority(priority: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCollisionPriorityBind, handle, priority)
    }

    fun getCollisionPriority(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCollisionPriorityBind, handle)
    }

    fun setCollisionVisibilityMode(visibilityMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setCollisionVisibilityModeBind, handle, visibilityMode)
    }

    fun getCollisionVisibilityMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCollisionVisibilityModeBind, handle)
    }

    fun setPhysicsMaterial(material: PhysicsMaterial?) {
        ObjectCalls.ptrcallWithObjectArgs(setPhysicsMaterialBind, handle, listOf(material?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getPhysicsMaterial(): PhysicsMaterial? {
        return PhysicsMaterial.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPhysicsMaterialBind, handle))
    }

    fun setBakeNavigation(bakeNavigation: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setBakeNavigationBind, handle, bakeNavigation)
    }

    fun isBakingNavigation(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isBakingNavigationBind, handle)
    }

    fun setNavigationMap(navigationMap: RID) {
        ObjectCalls.ptrcallWithRIDArg(setNavigationMapBind, handle, navigationMap)
    }

    fun getNavigationMap(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getNavigationMapBind, handle)
    }

    fun setMeshLibrary(meshLibrary: MeshLibrary?) {
        ObjectCalls.ptrcallWithObjectArgs(setMeshLibraryBind, handle, listOf(meshLibrary?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getMeshLibrary(): MeshLibrary? {
        return MeshLibrary.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMeshLibraryBind, handle))
    }

    fun setCellSize(size: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setCellSizeBind, handle, size)
    }

    fun getCellSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getCellSizeBind, handle)
    }

    fun setCellScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCellScaleBind, handle, scale)
    }

    fun getCellScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCellScaleBind, handle)
    }

    fun setOctantSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setOctantSizeBind, handle, size)
    }

    fun getOctantSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getOctantSizeBind, handle)
    }

    fun setCellItem(position: Vector3i, item: Int, orientation: Int = 0) {
        ObjectCalls.ptrcallWithVector3iIntIntArgs(setCellItemBind, handle, position, item, orientation)
    }

    fun getCellItem(position: Vector3i): Int {
        return ObjectCalls.ptrcallWithVector3iArgRetInt(getCellItemBind, handle, position)
    }

    fun getCellItemOrientation(position: Vector3i): Int {
        return ObjectCalls.ptrcallWithVector3iArgRetInt(getCellItemOrientationBind, handle, position)
    }

    fun getCellItemBasis(position: Vector3i): Basis {
        return ObjectCalls.ptrcallWithVector3iArgRetBasis(getCellItemBasisBind, handle, position)
    }

    fun getBasisWithOrthogonalIndex(index: Int): Basis {
        return ObjectCalls.ptrcallWithIntArgRetBasis(getBasisWithOrthogonalIndexBind, handle, index)
    }

    fun getOrthogonalIndexFromBasis(basis: Basis): Int {
        return ObjectCalls.ptrcallWithBasisArgRetInt(getOrthogonalIndexFromBasisBind, handle, basis)
    }

    fun localToMap(localPosition: Vector3): Vector3i {
        return ObjectCalls.ptrcallWithVector3ArgRetVector3i(localToMapBind, handle, localPosition)
    }

    fun mapToLocal(mapPosition: Vector3i): Vector3 {
        return ObjectCalls.ptrcallWithVector3iArgRetVector3(mapToLocalBind, handle, mapPosition)
    }

    fun resourceChanged(resource: Resource?) {
        ObjectCalls.ptrcallWithObjectArgs(resourceChangedBind, handle, listOf(resource?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun setCenterX(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCenterXBind, handle, enable)
    }

    fun getCenterX(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getCenterXBind, handle)
    }

    fun setCenterY(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCenterYBind, handle, enable)
    }

    fun getCenterY(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getCenterYBind, handle)
    }

    fun setCenterZ(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCenterZBind, handle, enable)
    }

    fun getCenterZ(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getCenterZBind, handle)
    }

    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun getUsedCells(): List<Vector3i> {
        return ObjectCalls.ptrcallNoArgsRetVector3iList(getUsedCellsBind, handle)
    }

    fun getUsedCellsByItem(item: Int): List<Vector3i> {
        return ObjectCalls.ptrcallWithIntArgRetVector3iList(getUsedCellsByItemBind, handle, item)
    }

    fun getUsedOctants(): List<Vector3i> {
        return ObjectCalls.ptrcallNoArgsRetVector3iList(getUsedOctantsBind, handle)
    }

    fun getUsedOctantsByItem(item: Int): List<Vector3i> {
        return ObjectCalls.ptrcallWithIntArgRetVector3iList(getUsedOctantsByItemBind, handle, item)
    }

    fun getUsedCellsInOctant(octantCoords: Vector3i): List<Vector3i> {
        return ObjectCalls.ptrcallWithVector3iArgRetVector3iList(getUsedCellsInOctantBind, handle, octantCoords)
    }

    fun getUsedCellsInOctantByItem(octantCoords: Vector3i, item: Int): List<Vector3i> {
        return ObjectCalls.ptrcallWithVector3iAndIntArgRetVector3iList(getUsedCellsInOctantByItemBind, handle, octantCoords, item)
    }

    fun getOctantsInBounds(bounds: AABB): List<Vector3i> {
        return ObjectCalls.ptrcallWithAABBArgRetVector3iList(getOctantsInBoundsBind, handle, bounds)
    }

    fun getUsedOctantsInBounds(bounds: AABB): List<Vector3i> {
        return ObjectCalls.ptrcallWithAABBArgRetVector3iList(getUsedOctantsInBoundsBind, handle, bounds)
    }

    fun getOctantCoordsFromCellCoords(cellCoords: Vector3i): Vector3i {
        return ObjectCalls.ptrcallWithVector3iArgRetVector3i(getOctantCoordsFromCellCoordsBind, handle, cellCoords)
    }

    fun getMeshes(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getMeshesBind, handle)
    }

    fun getBakeMeshes(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getBakeMeshesBind, handle)
    }

    fun getBakeMeshInstance(idx: Int): RID {
        return ObjectCalls.ptrcallWithIntArgRetRID(getBakeMeshInstanceBind, handle, idx)
    }

    fun clearBakedMeshes() {
        ObjectCalls.ptrcallNoArgs(clearBakedMeshesBind, handle)
    }

    fun makeBakedMeshes(genLightmapUv: Boolean = false, lightmapUvTexelSize: Double = 0.1) {
        ObjectCalls.ptrcallWithBoolAndDoubleArgs(makeBakedMeshesBind, handle, genLightmapUv, lightmapUvTexelSize)
    }

    object Signals {
        const val cellSizeChanged: String = "cell_size_changed"
        const val changed: String = "changed"
    }

    companion object {
        const val INVALID_CELL_ITEM: Long = -1L
        const val DEBUG_VISIBILITY_MODE_DEFAULT: Long = 0L
        const val DEBUG_VISIBILITY_MODE_FORCE_SHOW: Long = 1L
        const val DEBUG_VISIBILITY_MODE_FORCE_HIDE: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): GridMap? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GridMap? =
            if (handle.address() == 0L) null else GridMap(handle)

        private const val SET_COLLISION_LAYER_HASH = 1286410249L
        private val setCollisionLayerBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "set_collision_layer", SET_COLLISION_LAYER_HASH)
        }

        private const val GET_COLLISION_LAYER_HASH = 3905245786L
        private val getCollisionLayerBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_collision_layer", GET_COLLISION_LAYER_HASH)
        }

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        private val setCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "set_collision_mask", SET_COLLISION_MASK_HASH)
        }

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        private val getCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_collision_mask", GET_COLLISION_MASK_HASH)
        }

        private const val SET_COLLISION_MASK_VALUE_HASH = 300928843L
        private val setCollisionMaskValueBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "set_collision_mask_value", SET_COLLISION_MASK_VALUE_HASH)
        }

        private const val GET_COLLISION_MASK_VALUE_HASH = 1116898809L
        private val getCollisionMaskValueBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_collision_mask_value", GET_COLLISION_MASK_VALUE_HASH)
        }

        private const val SET_COLLISION_LAYER_VALUE_HASH = 300928843L
        private val setCollisionLayerValueBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "set_collision_layer_value", SET_COLLISION_LAYER_VALUE_HASH)
        }

        private const val GET_COLLISION_LAYER_VALUE_HASH = 1116898809L
        private val getCollisionLayerValueBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_collision_layer_value", GET_COLLISION_LAYER_VALUE_HASH)
        }

        private const val SET_COLLISION_PRIORITY_HASH = 373806689L
        private val setCollisionPriorityBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "set_collision_priority", SET_COLLISION_PRIORITY_HASH)
        }

        private const val GET_COLLISION_PRIORITY_HASH = 1740695150L
        private val getCollisionPriorityBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_collision_priority", GET_COLLISION_PRIORITY_HASH)
        }

        private const val SET_COLLISION_VISIBILITY_MODE_HASH = 4160694578L
        private val setCollisionVisibilityModeBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "set_collision_visibility_mode", SET_COLLISION_VISIBILITY_MODE_HASH)
        }

        private const val GET_COLLISION_VISIBILITY_MODE_HASH = 3729798365L
        private val getCollisionVisibilityModeBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_collision_visibility_mode", GET_COLLISION_VISIBILITY_MODE_HASH)
        }

        private const val SET_PHYSICS_MATERIAL_HASH = 1784508650L
        private val setPhysicsMaterialBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "set_physics_material", SET_PHYSICS_MATERIAL_HASH)
        }

        private const val GET_PHYSICS_MATERIAL_HASH = 2521850424L
        private val getPhysicsMaterialBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_physics_material", GET_PHYSICS_MATERIAL_HASH)
        }

        private const val SET_BAKE_NAVIGATION_HASH = 2586408642L
        private val setBakeNavigationBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "set_bake_navigation", SET_BAKE_NAVIGATION_HASH)
        }

        private const val IS_BAKING_NAVIGATION_HASH = 2240911060L
        private val isBakingNavigationBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "is_baking_navigation", IS_BAKING_NAVIGATION_HASH)
        }

        private const val SET_NAVIGATION_MAP_HASH = 2722037293L
        private val setNavigationMapBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "set_navigation_map", SET_NAVIGATION_MAP_HASH)
        }

        private const val GET_NAVIGATION_MAP_HASH = 2944877500L
        private val getNavigationMapBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_navigation_map", GET_NAVIGATION_MAP_HASH)
        }

        private const val SET_MESH_LIBRARY_HASH = 1488083439L
        private val setMeshLibraryBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "set_mesh_library", SET_MESH_LIBRARY_HASH)
        }

        private const val GET_MESH_LIBRARY_HASH = 3350993772L
        private val getMeshLibraryBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_mesh_library", GET_MESH_LIBRARY_HASH)
        }

        private const val SET_CELL_SIZE_HASH = 3460891852L
        private val setCellSizeBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "set_cell_size", SET_CELL_SIZE_HASH)
        }

        private const val GET_CELL_SIZE_HASH = 3360562783L
        private val getCellSizeBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_cell_size", GET_CELL_SIZE_HASH)
        }

        private const val SET_CELL_SCALE_HASH = 373806689L
        private val setCellScaleBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "set_cell_scale", SET_CELL_SCALE_HASH)
        }

        private const val GET_CELL_SCALE_HASH = 1740695150L
        private val getCellScaleBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_cell_scale", GET_CELL_SCALE_HASH)
        }

        private const val SET_OCTANT_SIZE_HASH = 1286410249L
        private val setOctantSizeBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "set_octant_size", SET_OCTANT_SIZE_HASH)
        }

        private const val GET_OCTANT_SIZE_HASH = 3905245786L
        private val getOctantSizeBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_octant_size", GET_OCTANT_SIZE_HASH)
        }

        private const val SET_CELL_ITEM_HASH = 3449088946L
        private val setCellItemBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "set_cell_item", SET_CELL_ITEM_HASH)
        }

        private const val GET_CELL_ITEM_HASH = 3724960147L
        private val getCellItemBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_cell_item", GET_CELL_ITEM_HASH)
        }

        private const val GET_CELL_ITEM_ORIENTATION_HASH = 3724960147L
        private val getCellItemOrientationBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_cell_item_orientation", GET_CELL_ITEM_ORIENTATION_HASH)
        }

        private const val GET_CELL_ITEM_BASIS_HASH = 3493604918L
        private val getCellItemBasisBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_cell_item_basis", GET_CELL_ITEM_BASIS_HASH)
        }

        private const val GET_BASIS_WITH_ORTHOGONAL_INDEX_HASH = 2816196998L
        private val getBasisWithOrthogonalIndexBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_basis_with_orthogonal_index", GET_BASIS_WITH_ORTHOGONAL_INDEX_HASH)
        }

        private const val GET_ORTHOGONAL_INDEX_FROM_BASIS_HASH = 4210359952L
        private val getOrthogonalIndexFromBasisBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_orthogonal_index_from_basis", GET_ORTHOGONAL_INDEX_FROM_BASIS_HASH)
        }

        private const val LOCAL_TO_MAP_HASH = 1257687843L
        private val localToMapBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "local_to_map", LOCAL_TO_MAP_HASH)
        }

        private const val MAP_TO_LOCAL_HASH = 1088329196L
        private val mapToLocalBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "map_to_local", MAP_TO_LOCAL_HASH)
        }

        private const val RESOURCE_CHANGED_HASH = 968641751L
        private val resourceChangedBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "resource_changed", RESOURCE_CHANGED_HASH)
        }

        private const val SET_CENTER_X_HASH = 2586408642L
        private val setCenterXBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "set_center_x", SET_CENTER_X_HASH)
        }

        private const val GET_CENTER_X_HASH = 36873697L
        private val getCenterXBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_center_x", GET_CENTER_X_HASH)
        }

        private const val SET_CENTER_Y_HASH = 2586408642L
        private val setCenterYBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "set_center_y", SET_CENTER_Y_HASH)
        }

        private const val GET_CENTER_Y_HASH = 36873697L
        private val getCenterYBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_center_y", GET_CENTER_Y_HASH)
        }

        private const val SET_CENTER_Z_HASH = 2586408642L
        private val setCenterZBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "set_center_z", SET_CENTER_Z_HASH)
        }

        private const val GET_CENTER_Z_HASH = 36873697L
        private val getCenterZBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_center_z", GET_CENTER_Z_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "clear", CLEAR_HASH)
        }

        private const val GET_USED_CELLS_HASH = 3995934104L
        private val getUsedCellsBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_used_cells", GET_USED_CELLS_HASH)
        }

        private const val GET_USED_CELLS_BY_ITEM_HASH = 663333327L
        private val getUsedCellsByItemBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_used_cells_by_item", GET_USED_CELLS_BY_ITEM_HASH)
        }

        private const val GET_USED_OCTANTS_HASH = 3995934104L
        private val getUsedOctantsBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_used_octants", GET_USED_OCTANTS_HASH)
        }

        private const val GET_USED_OCTANTS_BY_ITEM_HASH = 663333327L
        private val getUsedOctantsByItemBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_used_octants_by_item", GET_USED_OCTANTS_BY_ITEM_HASH)
        }

        private const val GET_USED_CELLS_IN_OCTANT_HASH = 2658725580L
        private val getUsedCellsInOctantBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_used_cells_in_octant", GET_USED_CELLS_IN_OCTANT_HASH)
        }

        private const val GET_USED_CELLS_IN_OCTANT_BY_ITEM_HASH = 2384667821L
        private val getUsedCellsInOctantByItemBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_used_cells_in_octant_by_item", GET_USED_CELLS_IN_OCTANT_BY_ITEM_HASH)
        }

        private const val GET_OCTANTS_IN_BOUNDS_HASH = 2489849902L
        private val getOctantsInBoundsBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_octants_in_bounds", GET_OCTANTS_IN_BOUNDS_HASH)
        }

        private const val GET_USED_OCTANTS_IN_BOUNDS_HASH = 2489849902L
        private val getUsedOctantsInBoundsBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_used_octants_in_bounds", GET_USED_OCTANTS_IN_BOUNDS_HASH)
        }

        private const val GET_OCTANT_COORDS_FROM_CELL_COORDS_HASH = 2075501597L
        private val getOctantCoordsFromCellCoordsBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_octant_coords_from_cell_coords", GET_OCTANT_COORDS_FROM_CELL_COORDS_HASH)
        }

        private const val GET_MESHES_HASH = 3995934104L
        private val getMeshesBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_meshes", GET_MESHES_HASH)
        }

        private const val GET_BAKE_MESHES_HASH = 2915620761L
        private val getBakeMeshesBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_bake_meshes", GET_BAKE_MESHES_HASH)
        }

        private const val GET_BAKE_MESH_INSTANCE_HASH = 937000113L
        private val getBakeMeshInstanceBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "get_bake_mesh_instance", GET_BAKE_MESH_INSTANCE_HASH)
        }

        private const val CLEAR_BAKED_MESHES_HASH = 3218959716L
        private val clearBakedMeshesBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "clear_baked_meshes", CLEAR_BAKED_MESHES_HASH)
        }

        private const val MAKE_BAKED_MESHES_HASH = 3609286057L
        private val makeBakedMeshesBind by lazy {
            ObjectCalls.getMethodBind("GridMap", "make_baked_meshes", MAKE_BAKED_MESHES_HASH)
        }
    }
}
