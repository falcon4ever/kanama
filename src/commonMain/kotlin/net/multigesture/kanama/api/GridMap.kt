package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.Vector3i

/**
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

    fun setCollisionLayer(layer: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCollisionLayerBind, segment, layer)
    }

    fun getCollisionLayer(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getCollisionLayerBind, segment)
    }

    fun setCollisionMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCollisionMaskBind, segment, mask)
    }

    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getCollisionMaskBind, segment)
    }

    fun setCollisionMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setCollisionMaskValueBind, segment, layerNumber, value)
    }

    fun getCollisionMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getCollisionMaskValueBind, segment, layerNumber)
    }

    fun setCollisionLayerValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setCollisionLayerValueBind, segment, layerNumber, value)
    }

    fun getCollisionLayerValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getCollisionLayerValueBind, segment, layerNumber)
    }

    fun setCollisionPriority(priority: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setCollisionPriorityBind, segment, priority)
    }

    fun getCollisionPriority(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCollisionPriorityBind, segment)
    }

    fun setCollisionVisibilityMode(visibilityMode: GridMap.DebugVisibilityMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setCollisionVisibilityModeBind, segment, visibilityMode.value)
    }

    fun getCollisionVisibilityMode(): GridMap.DebugVisibilityMode {
        return GridMap.DebugVisibilityMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getCollisionVisibilityModeBind, segment))
    }

    fun setPhysicsMaterial(material: PhysicsMaterial?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setPhysicsMaterialBind, segment, listOf(material?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getPhysicsMaterial(): PhysicsMaterial? {
        return PhysicsMaterial.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getPhysicsMaterialBind, segment))
    }

    fun setBakeNavigation(bakeNavigation: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setBakeNavigationBind, segment, bakeNavigation)
    }

    fun isBakingNavigation(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isBakingNavigationBind, segment)
    }

    fun setNavigationMap(navigationMap: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.setNavigationMapBind, segment, navigationMap)
    }

    fun getNavigationMap(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getNavigationMapBind, segment)
    }

    fun setMeshLibrary(meshLibrary: MeshLibrary?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setMeshLibraryBind, segment, listOf(meshLibrary?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getMeshLibrary(): MeshLibrary? {
        return MeshLibrary.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMeshLibraryBind, segment))
    }

    fun setCellSize(size: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.setCellSizeBind, segment, size)
    }

    fun getCellSize(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getCellSizeBind, segment)
    }

    fun setCellScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setCellScaleBind, segment, scale)
    }

    fun getCellScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCellScaleBind, segment)
    }

    fun setOctantSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setOctantSizeBind, segment, size)
    }

    fun getOctantSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getOctantSizeBind, segment)
    }

    fun setCellItem(position: Vector3i, item: Int, orientation: Int = 0) {
        ObjectCalls.ptrcallWithVector3iIntIntArgs(Binds.setCellItemBind, segment, position, item, orientation)
    }

    fun getCellItem(position: Vector3i): Int {
        return ObjectCalls.ptrcallWithVector3iArgRetInt(Binds.getCellItemBind, segment, position)
    }

    fun getCellItemOrientation(position: Vector3i): Int {
        return ObjectCalls.ptrcallWithVector3iArgRetInt(Binds.getCellItemOrientationBind, segment, position)
    }

    fun getCellItemBasis(position: Vector3i): Basis {
        return ObjectCalls.ptrcallWithVector3iArgRetBasis(Binds.getCellItemBasisBind, segment, position)
    }

    fun getBasisWithOrthogonalIndex(index: Int): Basis {
        return ObjectCalls.ptrcallWithIntArgRetBasis(Binds.getBasisWithOrthogonalIndexBind, segment, index)
    }

    fun getOrthogonalIndexFromBasis(basis: Basis): Int {
        return ObjectCalls.ptrcallWithBasisArgRetInt(Binds.getOrthogonalIndexFromBasisBind, segment, basis)
    }

    fun localToMap(localPosition: Vector3): Vector3i {
        return ObjectCalls.ptrcallWithVector3ArgRetVector3i(Binds.localToMapBind, segment, localPosition)
    }

    fun mapToLocal(mapPosition: Vector3i): Vector3 {
        return ObjectCalls.ptrcallWithVector3iArgRetVector3(Binds.mapToLocalBind, segment, mapPosition)
    }

    fun resourceChanged(resource: Resource?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.resourceChangedBind, segment, listOf(resource?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun setCenterX(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCenterXBind, segment, enable)
    }

    fun getCenterX(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getCenterXBind, segment)
    }

    fun setCenterY(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCenterYBind, segment, enable)
    }

    fun getCenterY(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getCenterYBind, segment)
    }

    fun setCenterZ(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCenterZBind, segment, enable)
    }

    fun getCenterZ(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getCenterZBind, segment)
    }

    fun clear() {
        ObjectCalls.ptrcallNoArgs(Binds.clearBind, segment)
    }

    fun getUsedCells(): List<Vector3i> {
        return ObjectCalls.ptrcallNoArgsRetVector3iList(Binds.getUsedCellsBind, segment)
    }

    fun getUsedCellsByItem(item: Int): List<Vector3i> {
        return ObjectCalls.ptrcallWithIntArgRetVector3iList(Binds.getUsedCellsByItemBind, segment, item)
    }

    fun getUsedOctants(): List<Vector3i> {
        return ObjectCalls.ptrcallNoArgsRetVector3iList(Binds.getUsedOctantsBind, segment)
    }

    fun getUsedOctantsByItem(item: Int): List<Vector3i> {
        return ObjectCalls.ptrcallWithIntArgRetVector3iList(Binds.getUsedOctantsByItemBind, segment, item)
    }

    fun getUsedCellsInOctant(octantCoords: Vector3i): List<Vector3i> {
        return ObjectCalls.ptrcallWithVector3iArgRetVector3iList(Binds.getUsedCellsInOctantBind, segment, octantCoords)
    }

    fun getUsedCellsInOctantByItem(octantCoords: Vector3i, item: Int): List<Vector3i> {
        return ObjectCalls.ptrcallWithVector3iAndIntArgRetVector3iList(Binds.getUsedCellsInOctantByItemBind, segment, octantCoords, item)
    }

    fun getOctantsInBounds(bounds: AABB): List<Vector3i> {
        return ObjectCalls.ptrcallWithAABBArgRetVector3iList(Binds.getOctantsInBoundsBind, segment, bounds)
    }

    fun getUsedOctantsInBounds(bounds: AABB): List<Vector3i> {
        return ObjectCalls.ptrcallWithAABBArgRetVector3iList(Binds.getUsedOctantsInBoundsBind, segment, bounds)
    }

    fun getOctantCoordsFromCellCoords(cellCoords: Vector3i): Vector3i {
        return ObjectCalls.ptrcallWithVector3iArgRetVector3i(Binds.getOctantCoordsFromCellCoordsBind, segment, cellCoords)
    }

    fun getMeshes(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getMeshesBind, segment)
    }

    fun getBakeMeshes(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getBakeMeshesBind, segment)
    }

    fun getBakeMeshInstance(idx: Int): RID {
        return ObjectCalls.ptrcallWithIntArgRetRID(Binds.getBakeMeshInstanceBind, segment, idx)
    }

    fun clearBakedMeshes() {
        ObjectCalls.ptrcallNoArgs(Binds.clearBakedMeshesBind, segment)
    }

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

    @JvmInline
    value class DebugVisibilityMode(override val value: Long) : GodotEnumValue {
        companion object {
            val DEFAULT: DebugVisibilityMode get() = DebugVisibilityMode(0L)
            val FORCE_SHOW: DebugVisibilityMode get() = DebugVisibilityMode(1L)
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
