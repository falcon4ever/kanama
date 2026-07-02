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

    fun setMesh(mesh: Mesh?) {
        ObjectCalls.ptrcallWithObjectArgs(setMeshBind, handle, listOf(mesh?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getMesh(): Mesh? {
        return Mesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMeshBind, handle))
    }

    fun setSkeletonPath(skeletonPath: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setSkeletonPathBind, handle, skeletonPath)
    }

    fun getSkeletonPath(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getSkeletonPathBind, handle)
    }

    fun setSkin(skin: Skin?) {
        ObjectCalls.ptrcallWithObjectArgs(setSkinBind, handle, listOf(skin?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getSkin(): Skin? {
        return Skin.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSkinBind, handle))
    }

    fun getSkinReference(): SkinReference? {
        return SkinReference.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSkinReferenceBind, handle))
    }

    fun getSurfaceOverrideMaterialCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSurfaceOverrideMaterialCountBind, handle)
    }

    fun setSurfaceOverrideMaterial(surface: Int, material: Material?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setSurfaceOverrideMaterialBind, handle, surface, material?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getSurfaceOverrideMaterial(surface: Int): Material? {
        return Material.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getSurfaceOverrideMaterialBind, handle, surface))
    }

    fun getActiveMaterial(surface: Int): Material? {
        return Material.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getActiveMaterialBind, handle, surface))
    }

    fun createTrimeshCollision() {
        ObjectCalls.ptrcallNoArgs(createTrimeshCollisionBind, handle)
    }

    fun createConvexCollision(clean: Boolean = true, simplify: Boolean = false) {
        ObjectCalls.ptrcallWithTwoBoolArgs(createConvexCollisionBind, handle, clean, simplify)
    }

    fun createMultipleConvexCollisions(settings: MeshConvexDecompositionSettings?) {
        ObjectCalls.ptrcallWithObjectArgs(createMultipleConvexCollisionsBind, handle, listOf(settings?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getBlendShapeCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBlendShapeCountBind, handle)
    }

    fun findBlendShapeByName(name: String): Int {
        return ObjectCalls.ptrcallWithStringNameArgRetInt(findBlendShapeByNameBind, handle, name)
    }

    fun getBlendShapeValue(blendShapeIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getBlendShapeValueBind, handle, blendShapeIdx)
    }

    fun setBlendShapeValue(blendShapeIdx: Int, value: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setBlendShapeValueBind, handle, blendShapeIdx, value)
    }

    fun createDebugTangents() {
        ObjectCalls.ptrcallNoArgs(createDebugTangentsBind, handle)
    }

    fun bakeMeshFromCurrentBlendShapeMix(existing: ArrayMesh?): ArrayMesh? {
        return ArrayMesh.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(bakeMeshFromCurrentBlendShapeMixBind, handle, existing?.requireOpenHandle() ?: MemorySegment.NULL))
    }

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
