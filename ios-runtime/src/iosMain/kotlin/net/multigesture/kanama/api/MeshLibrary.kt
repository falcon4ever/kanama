package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Transform3D

/**
 * Generated from Godot docs: MeshLibrary
 */
class MeshLibrary(handle: MemorySegment) : Resource(handle) {
    fun createItem(id: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(createItemBind, handle, id)
    }

    fun setItemName(id: Int, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringArg(setItemNameBind, handle, id, name)
    }

    fun setItemMesh(id: Int, mesh: Mesh?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndObjectArg(setItemMeshBind, handle, id, mesh?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun setItemMeshTransform(id: Int, meshTransform: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndTransform3DArg(setItemMeshTransformBind, handle, id, meshTransform)
    }

    fun setItemMeshCastShadow(id: Int, shadowCastingSetting: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndLongArgs(setItemMeshCastShadowBind, handle, id, shadowCastingSetting)
    }

    fun setItemNavigationMesh(id: Int, navigationMesh: NavigationMesh?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndObjectArg(setItemNavigationMeshBind, handle, id, navigationMesh?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun setItemNavigationMeshTransform(id: Int, navigationMesh: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndTransform3DArg(setItemNavigationMeshTransformBind, handle, id, navigationMesh)
    }

    fun setItemNavigationLayers(id: Int, navigationLayers: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndUInt32Args(setItemNavigationLayersBind, handle, id, navigationLayers)
    }

    fun setItemPreview(id: Int, texture: Texture2D?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndObjectArg(setItemPreviewBind, handle, id, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getItemName(id: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetString(getItemNameBind, handle, id)
    }

    fun getItemMesh(id: Int): Mesh? {
        checkOpen()
        return Mesh.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getItemMeshBind, handle, id))
    }

    fun getItemMeshTransform(id: Int): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(getItemMeshTransformBind, handle, id)
    }

    fun getItemMeshCastShadow(id: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetLong(getItemMeshCastShadowBind, handle, id)
    }

    fun getItemNavigationMesh(id: Int): NavigationMesh? {
        checkOpen()
        return NavigationMesh.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getItemNavigationMeshBind, handle, id))
    }

    fun getItemNavigationMeshTransform(id: Int): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(getItemNavigationMeshTransformBind, handle, id)
    }

    fun getItemNavigationLayers(id: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetUInt32(getItemNavigationLayersBind, handle, id)
    }

    fun getItemPreview(id: Int): Texture2D? {
        checkOpen()
        return Texture2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getItemPreviewBind, handle, id))
    }

    fun removeItem(id: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(removeItemBind, handle, id)
    }

    fun findItemByName(name: String): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetInt(findItemByNameBind, handle, name)
    }

    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun getItemList(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getItemListBind, handle)
    }

    fun getItemCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getItemCountBind, handle)
    }

    fun getLastUnusedItemId(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getLastUnusedItemIdBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): MeshLibrary? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): MeshLibrary? =
            if (handle.address() == 0L) null else MeshLibrary(handle)

        private const val CREATE_ITEM_HASH = 1286410249L
        private val createItemBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "create_item", CREATE_ITEM_HASH)
        }

        private const val SET_ITEM_NAME_HASH = 501894301L
        private val setItemNameBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "set_item_name", SET_ITEM_NAME_HASH)
        }

        private const val SET_ITEM_MESH_HASH = 969122797L
        private val setItemMeshBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "set_item_mesh", SET_ITEM_MESH_HASH)
        }

        private const val SET_ITEM_MESH_TRANSFORM_HASH = 3616898986L
        private val setItemMeshTransformBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "set_item_mesh_transform", SET_ITEM_MESH_TRANSFORM_HASH)
        }

        private const val SET_ITEM_MESH_CAST_SHADOW_HASH = 3923400443L
        private val setItemMeshCastShadowBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "set_item_mesh_cast_shadow", SET_ITEM_MESH_CAST_SHADOW_HASH)
        }

        private const val SET_ITEM_NAVIGATION_MESH_HASH = 3483353960L
        private val setItemNavigationMeshBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "set_item_navigation_mesh", SET_ITEM_NAVIGATION_MESH_HASH)
        }

        private const val SET_ITEM_NAVIGATION_MESH_TRANSFORM_HASH = 3616898986L
        private val setItemNavigationMeshTransformBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "set_item_navigation_mesh_transform", SET_ITEM_NAVIGATION_MESH_TRANSFORM_HASH)
        }

        private const val SET_ITEM_NAVIGATION_LAYERS_HASH = 3937882851L
        private val setItemNavigationLayersBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "set_item_navigation_layers", SET_ITEM_NAVIGATION_LAYERS_HASH)
        }

        private const val SET_ITEM_PREVIEW_HASH = 666127730L
        private val setItemPreviewBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "set_item_preview", SET_ITEM_PREVIEW_HASH)
        }

        private const val GET_ITEM_NAME_HASH = 844755477L
        private val getItemNameBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_name", GET_ITEM_NAME_HASH)
        }

        private const val GET_ITEM_MESH_HASH = 1576363275L
        private val getItemMeshBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_mesh", GET_ITEM_MESH_HASH)
        }

        private const val GET_ITEM_MESH_TRANSFORM_HASH = 1965739696L
        private val getItemMeshTransformBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_mesh_transform", GET_ITEM_MESH_TRANSFORM_HASH)
        }

        private const val GET_ITEM_MESH_CAST_SHADOW_HASH = 1841766007L
        private val getItemMeshCastShadowBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_mesh_cast_shadow", GET_ITEM_MESH_CAST_SHADOW_HASH)
        }

        private const val GET_ITEM_NAVIGATION_MESH_HASH = 2729647406L
        private val getItemNavigationMeshBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_navigation_mesh", GET_ITEM_NAVIGATION_MESH_HASH)
        }

        private const val GET_ITEM_NAVIGATION_MESH_TRANSFORM_HASH = 1965739696L
        private val getItemNavigationMeshTransformBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_navigation_mesh_transform", GET_ITEM_NAVIGATION_MESH_TRANSFORM_HASH)
        }

        private const val GET_ITEM_NAVIGATION_LAYERS_HASH = 923996154L
        private val getItemNavigationLayersBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_navigation_layers", GET_ITEM_NAVIGATION_LAYERS_HASH)
        }

        private const val GET_ITEM_PREVIEW_HASH = 3536238170L
        private val getItemPreviewBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_preview", GET_ITEM_PREVIEW_HASH)
        }

        private const val REMOVE_ITEM_HASH = 1286410249L
        private val removeItemBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "remove_item", REMOVE_ITEM_HASH)
        }

        private const val FIND_ITEM_BY_NAME_HASH = 1321353865L
        private val findItemByNameBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "find_item_by_name", FIND_ITEM_BY_NAME_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "clear", CLEAR_HASH)
        }

        private const val GET_ITEM_LIST_HASH = 1930428628L
        private val getItemListBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_list", GET_ITEM_LIST_HASH)
        }

        private const val GET_ITEM_COUNT_HASH = 3905245786L
        private val getItemCountBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_count", GET_ITEM_COUNT_HASH)
        }

        private const val GET_LAST_UNUSED_ITEM_ID_HASH = 3905245786L
        private val getLastUnusedItemIdBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "get_last_unused_item_id", GET_LAST_UNUSED_ITEM_ID_HASH)
        }
    }
}
