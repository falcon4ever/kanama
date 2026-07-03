package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * Node that instances meshes into a scenario.
 *
 * Generated from Godot docs: MeshInstance3D
 */
open class MeshInstance3D(handle: MemorySegment) : GeometryInstance3D(handle) {
    var mesh: Mesh?
        @JvmName("meshProperty")
        get() = getMesh()
        @JvmName("setMeshProperty")
        set(value) = setMesh(value)

    var skin: Skin?
        @JvmName("skinProperty")
        get() = getSkin()
        @JvmName("setSkinProperty")
        set(value) = setSkin(value)

    var skeleton: NodePath
        @JvmName("skeletonProperty")
        get() = getSkeletonPath()
        @JvmName("setSkeletonProperty")
        set(value) = setSkeletonPath(value)

    /**
     * The `Mesh` resource for the instance.
     *
     * Generated from Godot docs: MeshInstance3D.set_mesh
     */
    fun setMesh(mesh: Mesh?) {
        ObjectCalls.ptrcallWithObjectArgs(setMeshBind, handle, listOf(mesh?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The `Mesh` resource for the instance.
     *
     * Generated from Godot docs: MeshInstance3D.get_mesh
     */
    fun getMesh(): Mesh? {
        return Mesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMeshBind, handle))
    }

    /**
     * `NodePath` to the `Skeleton3D` associated with the instance. Note: The default value of this
     * property has changed in Godot 4.6. Enable
     * `ProjectSettings.animation/compatibility/default_parent_skeleton_in_mesh_instance_3d` if the old
     * behavior is needed for compatibility.
     *
     * Generated from Godot docs: MeshInstance3D.set_skeleton_path
     */
    fun setSkeletonPath(skeletonPath: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setSkeletonPathBind, handle, skeletonPath)
    }

    /**
     * `NodePath` to the `Skeleton3D` associated with the instance. Note: The default value of this
     * property has changed in Godot 4.6. Enable
     * `ProjectSettings.animation/compatibility/default_parent_skeleton_in_mesh_instance_3d` if the old
     * behavior is needed for compatibility.
     *
     * Generated from Godot docs: MeshInstance3D.get_skeleton_path
     */
    fun getSkeletonPath(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getSkeletonPathBind, handle)
    }

    /**
     * The `Skin` to be used by this instance.
     *
     * Generated from Godot docs: MeshInstance3D.set_skin
     */
    fun setSkin(skin: Skin?) {
        ObjectCalls.ptrcallWithObjectArgs(setSkinBind, handle, listOf(skin?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The `Skin` to be used by this instance.
     *
     * Generated from Godot docs: MeshInstance3D.get_skin
     */
    fun getSkin(): Skin? {
        return Skin.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSkinBind, handle))
    }

    /**
     * Returns the internal `SkinReference` containing the skeleton's `RID` attached to this RID. See
     * also `Resource.get_rid`, `SkinReference.get_skeleton`, and
     * `RenderingServer.instance_attach_skeleton`.
     *
     * Generated from Godot docs: MeshInstance3D.get_skin_reference
     */
    fun getSkinReference(): SkinReference? {
        return SkinReference.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSkinReferenceBind, handle))
    }

    /**
     * Returns the number of surface override materials. This is equivalent to
     * `Mesh.get_surface_count`. See also `get_surface_override_material`.
     *
     * Generated from Godot docs: MeshInstance3D.get_surface_override_material_count
     */
    fun getSurfaceOverrideMaterialCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSurfaceOverrideMaterialCountBind, handle)
    }

    /**
     * Sets the override `material` for the specified `surface` of the `Mesh` resource. This material
     * is associated with this `MeshInstance3D` rather than with `mesh`. Note: This assigns the
     * `Material` associated to the `MeshInstance3D`'s Surface Material Override properties, not the
     * material within the `Mesh` resource. To set the material within the `Mesh` resource, use
     * `Mesh.surface_set_material` instead.
     *
     * Generated from Godot docs: MeshInstance3D.set_surface_override_material
     */
    fun setSurfaceOverrideMaterial(surface: Int, material: Material?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setSurfaceOverrideMaterialBind, handle, surface, material?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the override `Material` for the specified `surface` of the `Mesh` resource. See also
     * `get_surface_override_material_count`. Note: This returns the `Material` associated to the
     * `MeshInstance3D`'s Surface Material Override properties, not the material within the `Mesh`
     * resource. To get the material within the `Mesh` resource, use `Mesh.surface_get_material`
     * instead.
     *
     * Generated from Godot docs: MeshInstance3D.get_surface_override_material
     */
    fun getSurfaceOverrideMaterial(surface: Int): Material? {
        return Material.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSurfaceOverrideMaterialBind, handle, surface))
    }

    /**
     * Returns the `Material` that will be used by the `Mesh` when drawing. This can return the
     * `GeometryInstance3D.material_override`, the surface override `Material` defined in this
     * `MeshInstance3D`, or the surface `Material` defined in the `mesh`. For example, if
     * `GeometryInstance3D.material_override` is used, all surfaces will return the override material.
     * Returns `null` if no material is active, including when `mesh` is `null`.
     *
     * Generated from Godot docs: MeshInstance3D.get_active_material
     */
    fun getActiveMaterial(surface: Int): Material? {
        return Material.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getActiveMaterialBind, handle, surface))
    }

    /**
     * This helper creates a `StaticBody3D` child node with a `ConcavePolygonShape3D` collision shape
     * calculated from the mesh geometry. It's mainly used for testing.
     *
     * Generated from Godot docs: MeshInstance3D.create_trimesh_collision
     */
    fun createTrimeshCollision() {
        ObjectCalls.ptrcallNoArgs(createTrimeshCollisionBind, handle)
    }

    /**
     * This helper creates a `StaticBody3D` child node with a `ConvexPolygonShape3D` collision shape
     * calculated from the mesh geometry. It's mainly used for testing. If `clean` is `true` (default),
     * duplicate and interior vertices are removed automatically. You can set it to `false` to make the
     * process faster if not needed. If `simplify` is `true`, the geometry can be further simplified to
     * reduce the number of vertices. Disabled by default.
     *
     * Generated from Godot docs: MeshInstance3D.create_convex_collision
     */
    fun createConvexCollision(clean: Boolean = true, simplify: Boolean = false) {
        ObjectCalls.ptrcallWithTwoBoolArgs(createConvexCollisionBind, handle, clean, simplify)
    }

    /**
     * This helper creates a `StaticBody3D` child node with multiple `ConvexPolygonShape3D` collision
     * shapes calculated from the mesh geometry via convex decomposition. The convex decomposition
     * operation can be controlled with parameters from the optional `settings`.
     *
     * Generated from Godot docs: MeshInstance3D.create_multiple_convex_collisions
     */
    fun createMultipleConvexCollisions(settings: MeshConvexDecompositionSettings?) {
        ObjectCalls.ptrcallWithObjectArgs(createMultipleConvexCollisionsBind, handle, listOf(settings?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Returns the number of blend shapes available. Produces an error if `mesh` is `null`.
     *
     * Generated from Godot docs: MeshInstance3D.get_blend_shape_count
     */
    fun getBlendShapeCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBlendShapeCountBind, handle)
    }

    /**
     * Returns the index of the blend shape with the given `name`. Returns `-1` if no blend shape with
     * this name exists, including when `mesh` is `null`.
     *
     * Generated from Godot docs: MeshInstance3D.find_blend_shape_by_name
     */
    fun findBlendShapeByName(name: String): Int {
        return ObjectCalls.ptrcallWithStringNameArgRetInt(findBlendShapeByNameBind, handle, name)
    }

    /**
     * Returns the value of the blend shape at the given `blend_shape_idx`. Returns `0.0` and produces
     * an error if `mesh` is `null` or doesn't have a blend shape at that index.
     *
     * Generated from Godot docs: MeshInstance3D.get_blend_shape_value
     */
    fun getBlendShapeValue(blendShapeIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getBlendShapeValueBind, handle, blendShapeIdx)
    }

    /**
     * Sets the value of the blend shape at `blend_shape_idx` to `value`. Produces an error if `mesh`
     * is `null` or doesn't have a blend shape at that index.
     *
     * Generated from Godot docs: MeshInstance3D.set_blend_shape_value
     */
    fun setBlendShapeValue(blendShapeIdx: Int, value: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setBlendShapeValueBind, handle, blendShapeIdx, value)
    }

    /**
     * This helper creates a `MeshInstance3D` child node with gizmos at every vertex calculated from
     * the mesh geometry. It's mainly used for testing.
     *
     * Generated from Godot docs: MeshInstance3D.create_debug_tangents
     */
    fun createDebugTangents() {
        ObjectCalls.ptrcallNoArgs(createDebugTangentsBind, handle)
    }

    /**
     * Takes a snapshot from the current `ArrayMesh` with all blend shapes applied according to their
     * current weights and bakes it to the provided `existing` mesh. If no `existing` mesh is provided
     * a new `ArrayMesh` is created, baked and returned. Mesh surface materials are not copied.
     * Performance: `Mesh` data needs to be received from the GPU, stalling the `RenderingServer` in
     * the process.
     *
     * Generated from Godot docs: MeshInstance3D.bake_mesh_from_current_blend_shape_mix
     */
    fun bakeMeshFromCurrentBlendShapeMix(existing: ArrayMesh?): ArrayMesh? {
        return ArrayMesh.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(bakeMeshFromCurrentBlendShapeMixBind, handle, existing?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Takes a snapshot of the current animated skeleton pose of the skinned mesh and bakes it to the
     * provided `existing` mesh. If no `existing` mesh is provided a new `ArrayMesh` is created, baked,
     * and returned. Requires a skeleton with a registered skin to work. Blendshapes are ignored. Mesh
     * surface materials are not copied. Performance: `Mesh` data needs to be retrieved from the GPU,
     * stalling the `RenderingServer` in the process.
     *
     * Generated from Godot docs: MeshInstance3D.bake_mesh_from_current_skeleton_pose
     */
    fun bakeMeshFromCurrentSkeletonPose(existing: ArrayMesh?): ArrayMesh? {
        return ArrayMesh.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(bakeMeshFromCurrentSkeletonPoseBind, handle, existing?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): MeshInstance3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): MeshInstance3D? =
            if (handle.address() == 0L) null else MeshInstance3D(handle)

        private const val SET_MESH_HASH = 194775623L
        private val setMeshBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance3D", "set_mesh", SET_MESH_HASH)
        }

        private const val GET_MESH_HASH = 1808005922L
        private val getMeshBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance3D", "get_mesh", GET_MESH_HASH)
        }

        private const val SET_SKELETON_PATH_HASH = 1348162250L
        private val setSkeletonPathBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance3D", "set_skeleton_path", SET_SKELETON_PATH_HASH)
        }

        private const val GET_SKELETON_PATH_HASH = 277076166L
        private val getSkeletonPathBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance3D", "get_skeleton_path", GET_SKELETON_PATH_HASH)
        }

        private const val SET_SKIN_HASH = 3971435618L
        private val setSkinBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance3D", "set_skin", SET_SKIN_HASH)
        }

        private const val GET_SKIN_HASH = 2074563878L
        private val getSkinBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance3D", "get_skin", GET_SKIN_HASH)
        }

        private const val GET_SKIN_REFERENCE_HASH = 2060603409L
        private val getSkinReferenceBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance3D", "get_skin_reference", GET_SKIN_REFERENCE_HASH)
        }

        private const val GET_SURFACE_OVERRIDE_MATERIAL_COUNT_HASH = 3905245786L
        private val getSurfaceOverrideMaterialCountBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance3D", "get_surface_override_material_count", GET_SURFACE_OVERRIDE_MATERIAL_COUNT_HASH)
        }

        private const val SET_SURFACE_OVERRIDE_MATERIAL_HASH = 3671737478L
        private val setSurfaceOverrideMaterialBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance3D", "set_surface_override_material", SET_SURFACE_OVERRIDE_MATERIAL_HASH)
        }

        private const val GET_SURFACE_OVERRIDE_MATERIAL_HASH = 2897466400L
        private val getSurfaceOverrideMaterialBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance3D", "get_surface_override_material", GET_SURFACE_OVERRIDE_MATERIAL_HASH)
        }

        private const val GET_ACTIVE_MATERIAL_HASH = 2897466400L
        private val getActiveMaterialBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance3D", "get_active_material", GET_ACTIVE_MATERIAL_HASH)
        }

        private const val CREATE_TRIMESH_COLLISION_HASH = 3218959716L
        private val createTrimeshCollisionBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance3D", "create_trimesh_collision", CREATE_TRIMESH_COLLISION_HASH)
        }

        private const val CREATE_CONVEX_COLLISION_HASH = 2751962654L
        private val createConvexCollisionBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance3D", "create_convex_collision", CREATE_CONVEX_COLLISION_HASH)
        }

        private const val CREATE_MULTIPLE_CONVEX_COLLISIONS_HASH = 628789669L
        private val createMultipleConvexCollisionsBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance3D", "create_multiple_convex_collisions", CREATE_MULTIPLE_CONVEX_COLLISIONS_HASH)
        }

        private const val GET_BLEND_SHAPE_COUNT_HASH = 3905245786L
        private val getBlendShapeCountBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance3D", "get_blend_shape_count", GET_BLEND_SHAPE_COUNT_HASH)
        }

        private const val FIND_BLEND_SHAPE_BY_NAME_HASH = 4150868206L
        private val findBlendShapeByNameBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance3D", "find_blend_shape_by_name", FIND_BLEND_SHAPE_BY_NAME_HASH)
        }

        private const val GET_BLEND_SHAPE_VALUE_HASH = 2339986948L
        private val getBlendShapeValueBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance3D", "get_blend_shape_value", GET_BLEND_SHAPE_VALUE_HASH)
        }

        private const val SET_BLEND_SHAPE_VALUE_HASH = 1602489585L
        private val setBlendShapeValueBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance3D", "set_blend_shape_value", SET_BLEND_SHAPE_VALUE_HASH)
        }

        private const val CREATE_DEBUG_TANGENTS_HASH = 3218959716L
        private val createDebugTangentsBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance3D", "create_debug_tangents", CREATE_DEBUG_TANGENTS_HASH)
        }

        private const val BAKE_MESH_FROM_CURRENT_BLEND_SHAPE_MIX_HASH = 1457573577L
        private val bakeMeshFromCurrentBlendShapeMixBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance3D", "bake_mesh_from_current_blend_shape_mix", BAKE_MESH_FROM_CURRENT_BLEND_SHAPE_MIX_HASH)
        }

        private const val BAKE_MESH_FROM_CURRENT_SKELETON_POSE_HASH = 1457573577L
        private val bakeMeshFromCurrentSkeletonPoseBind by lazy {
            ObjectCalls.getMethodBind("MeshInstance3D", "bake_mesh_from_current_skeleton_pose", BAKE_MESH_FROM_CURRENT_SKELETON_POSE_HASH)
        }
    }
}
