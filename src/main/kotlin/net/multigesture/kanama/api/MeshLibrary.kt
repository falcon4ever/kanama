package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D
import java.lang.foreign.MemorySegment

/**
 * Library of meshes.
 *
 * Generated from Godot docs: MeshLibrary
 */
class MeshLibrary(handle: MemorySegment) : Resource(handle) {
    /**
     * Creates a new item in the library with the given ID. You can get an unused ID from
     * `get_last_unused_item_id`.
     *
     * Generated from Godot docs: MeshLibrary.create_item
     */
    fun createItem(id: Int) {
        ObjectCalls.ptrcallWithIntArg(createItemBind, handle, id)
    }

    /**
     * Sets the item's name. This name is shown in the editor. It can also be used to look up the item
     * later using `find_item_by_name`.
     *
     * Generated from Godot docs: MeshLibrary.set_item_name
     */
    fun setItemName(id: Int, name: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setItemNameBind, handle, id, name)
    }

    /**
     * Sets the item's mesh.
     *
     * Generated from Godot docs: MeshLibrary.set_item_mesh
     */
    fun setItemMesh(id: Int, mesh: Mesh?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setItemMeshBind, handle, id, mesh?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Sets the transform to apply to the item's mesh.
     *
     * Generated from Godot docs: MeshLibrary.set_item_mesh_transform
     */
    fun setItemMeshTransform(id: Int, meshTransform: Transform3D) {
        ObjectCalls.ptrcallWithIntAndTransform3DArg(setItemMeshTransformBind, handle, id, meshTransform)
    }

    /**
     * Sets the item's shadow casting mode to `shadow_casting_setting`.
     *
     * Generated from Godot docs: MeshLibrary.set_item_mesh_cast_shadow
     */
    fun setItemMeshCastShadow(id: Int, shadowCastingSetting: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setItemMeshCastShadowBind, handle, id, shadowCastingSetting)
    }

    /**
     * Sets the item's navigation mesh.
     *
     * Generated from Godot docs: MeshLibrary.set_item_navigation_mesh
     */
    fun setItemNavigationMesh(id: Int, navigationMesh: NavigationMesh?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setItemNavigationMeshBind, handle, id, navigationMesh?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Sets the transform to apply to the item's navigation mesh.
     *
     * Generated from Godot docs: MeshLibrary.set_item_navigation_mesh_transform
     */
    fun setItemNavigationMeshTransform(id: Int, navigationMesh: Transform3D) {
        ObjectCalls.ptrcallWithIntAndTransform3DArg(setItemNavigationMeshTransformBind, handle, id, navigationMesh)
    }

    /**
     * Sets the item's navigation layers bitmask.
     *
     * Generated from Godot docs: MeshLibrary.set_item_navigation_layers
     */
    fun setItemNavigationLayers(id: Int, navigationLayers: Long) {
        ObjectCalls.ptrcallWithIntAndUInt32Args(setItemNavigationLayersBind, handle, id, navigationLayers)
    }

    /**
     * Sets an item's collision shapes. The array should consist of `Shape3D` objects, each followed by
     * a `Transform3D` that will be applied to it. For shapes that should not have a transform, use
     * `Transform3D.IDENTITY`.
     *
     * Generated from Godot docs: MeshLibrary.set_item_shapes
     */
    fun setItemShapes(id: Int, shapes: List<Any?>) {
        ObjectCalls.ptrcallWithIntAndArrayArg(setItemShapesBind, handle, id, shapes)
    }

    /**
     * Sets a texture to use as the item's preview icon in the editor.
     *
     * Generated from Godot docs: MeshLibrary.set_item_preview
     */
    fun setItemPreview(id: Int, texture: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setItemPreviewBind, handle, id, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the item's name.
     *
     * Generated from Godot docs: MeshLibrary.get_item_name
     */
    fun getItemName(id: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getItemNameBind, handle, id)
    }

    /**
     * Returns the item's mesh.
     *
     * Generated from Godot docs: MeshLibrary.get_item_mesh
     */
    fun getItemMesh(id: Int): Mesh? {
        return Mesh.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getItemMeshBind, handle, id))
    }

    /**
     * Returns the transform applied to the item's mesh.
     *
     * Generated from Godot docs: MeshLibrary.get_item_mesh_transform
     */
    fun getItemMeshTransform(id: Int): Transform3D {
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(getItemMeshTransformBind, handle, id)
    }

    /**
     * Returns the item's shadow casting mode.
     *
     * Generated from Godot docs: MeshLibrary.get_item_mesh_cast_shadow
     */
    fun getItemMeshCastShadow(id: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getItemMeshCastShadowBind, handle, id)
    }

    /**
     * Returns the item's navigation mesh.
     *
     * Generated from Godot docs: MeshLibrary.get_item_navigation_mesh
     */
    fun getItemNavigationMesh(id: Int): NavigationMesh? {
        return NavigationMesh.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getItemNavigationMeshBind, handle, id))
    }

    /**
     * Returns the transform applied to the item's navigation mesh.
     *
     * Generated from Godot docs: MeshLibrary.get_item_navigation_mesh_transform
     */
    fun getItemNavigationMeshTransform(id: Int): Transform3D {
        return ObjectCalls.ptrcallWithIntArgRetTransform3D(getItemNavigationMeshTransformBind, handle, id)
    }

    /**
     * Returns the item's navigation layers bitmask.
     *
     * Generated from Godot docs: MeshLibrary.get_item_navigation_layers
     */
    fun getItemNavigationLayers(id: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetUInt32(getItemNavigationLayersBind, handle, id)
    }

    /**
     * Returns an item's collision shapes. The array consists of each `Shape3D` followed by its
     * `Transform3D`.
     *
     * Generated from Godot docs: MeshLibrary.get_item_shapes
     */
    fun getItemShapes(id: Int): List<Any?> {
        return ObjectCalls.ptrcallWithIntArgRetArray(getItemShapesBind, handle, id)
    }

    /**
     * When running in the editor, returns a generated item preview (a 3D rendering in isometric
     * perspective). When used in a running project, returns the manually-defined item preview which
     * can be set using `set_item_preview`. Returns an empty `Texture2D` if no preview was manually set
     * in a running project.
     *
     * Generated from Godot docs: MeshLibrary.get_item_preview
     */
    fun getItemPreview(id: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getItemPreviewBind, handle, id))
    }

    /**
     * Removes the item.
     *
     * Generated from Godot docs: MeshLibrary.remove_item
     */
    fun removeItem(id: Int) {
        ObjectCalls.ptrcallWithIntArg(removeItemBind, handle, id)
    }

    /**
     * Returns the first item with the given name, or `-1` if no item is found.
     *
     * Generated from Godot docs: MeshLibrary.find_item_by_name
     */
    fun findItemByName(name: String): Int {
        return ObjectCalls.ptrcallWithStringArgRetInt(findItemByNameBind, handle, name)
    }

    /**
     * Clears the library.
     *
     * Generated from Godot docs: MeshLibrary.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    /**
     * Returns the list of item IDs in use.
     *
     * Generated from Godot docs: MeshLibrary.get_item_list
     */
    fun getItemList(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getItemListBind, handle)
    }

    /**
     * Gets an unused ID for a new item.
     *
     * Generated from Godot docs: MeshLibrary.get_last_unused_item_id
     */
    fun getLastUnusedItemId(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLastUnusedItemIdBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): MeshLibrary? =
            wrap(handle)

        @JvmStatic
        fun create(): MeshLibrary =
            MeshLibrary(ObjectCalls.constructObject("MeshLibrary"))

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

        private const val SET_ITEM_SHAPES_HASH = 537221740L
        private val setItemShapesBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "set_item_shapes", SET_ITEM_SHAPES_HASH)
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

        private const val GET_ITEM_SHAPES_HASH = 663333327L
        private val getItemShapesBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_shapes", GET_ITEM_SHAPES_HASH)
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

        private const val GET_LAST_UNUSED_ITEM_ID_HASH = 3905245786L
        private val getLastUnusedItemIdBind by lazy {
            ObjectCalls.getMethodBind("MeshLibrary", "get_last_unused_item_id", GET_LAST_UNUSED_ITEM_ID_HASH)
        }
    }
}
