package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
import net.multigesture.kanama.types.Transform3D

/**
 * Library of meshes.
 *
 * Generated from Godot docs: MeshLibrary
 */
class MeshLibrary(handle: GodotHandle) : Resource(handle) {
    /**
     * Creates a new item in the library with the given ID. You can get an unused ID from
     * `get_last_unused_item_id`.
     *
     * Generated from Godot docs: MeshLibrary.create_item
     */
    fun createItem(id: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.createItemBind, segment, id)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the item's name. This name is shown in the editor. It can also be used to look up the item
     * later using `find_item_by_name`.
     *
     * Generated from Godot docs: MeshLibrary.set_item_name
     */
    fun setItemName(id: Int, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setItemNameBind, segment, id, name)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the item's mesh.
     *
     * Generated from Godot docs: MeshLibrary.set_item_mesh
     */
    fun setItemMesh(id: Int, mesh: Mesh?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setItemMeshBind, segment, id, mesh?.requireOpenHandle() ?: NULL_SEGMENT)
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(mesh)
    }

    /**
     * Sets the transform to apply to the item's mesh.
     *
     * Generated from Godot docs: MeshLibrary.set_item_mesh_transform
     */
    fun setItemMeshTransform(id: Int, meshTransform: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndTransform3DArg(Binds.setItemMeshTransformBind, segment, id, meshTransform)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the item's shadow casting mode to `shadow_casting_setting`.
     *
     * Generated from Godot docs: MeshLibrary.set_item_mesh_cast_shadow
     */
    fun setItemMeshCastShadow(id: Int, shadowCastingSetting: RenderingServer.ShadowCastingSetting) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setItemMeshCastShadowBind, segment, id, shadowCastingSetting.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the item's navigation mesh.
     *
     * Generated from Godot docs: MeshLibrary.set_item_navigation_mesh
     */
    fun setItemNavigationMesh(id: Int, navigationMesh: NavigationMesh?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setItemNavigationMeshBind, segment, id, navigationMesh?.requireOpenHandle() ?: NULL_SEGMENT)
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(navigationMesh)
    }

    /**
     * Sets the transform to apply to the item's navigation mesh.
     *
     * Generated from Godot docs: MeshLibrary.set_item_navigation_mesh_transform
     */
    fun setItemNavigationMeshTransform(id: Int, navigationMesh: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndTransform3DArg(Binds.setItemNavigationMeshTransformBind, segment, id, navigationMesh)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the item's navigation layers bitmask.
     *
     * Generated from Godot docs: MeshLibrary.set_item_navigation_layers
     */
    fun setItemNavigationLayers(id: Int, navigationLayers: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndUInt32Args(Binds.setItemNavigationLayersBind, segment, id, navigationLayers)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets an item's collision shapes. The array should consist of `Shape3D` objects, each followed by
     * a `Transform3D` that will be applied to it. For shapes that should not have a transform, use
     * `Transform3D.IDENTITY`.
     *
     * Generated from Godot docs: MeshLibrary.set_item_shapes
     */
    fun setItemShapes(id: Int, shapes: List<Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndArrayArg(Binds.setItemShapesBind, segment, id, shapes)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets a texture to use as the item's preview icon in the editor.
     *
     * Generated from Godot docs: MeshLibrary.set_item_preview
     */
    fun setItemPreview(id: Int, texture: Texture2D?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setItemPreviewBind, segment, id, texture?.requireOpenHandle() ?: NULL_SEGMENT)
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(texture)
    }

    /**
     * Returns the item's name.
     *
     * Generated from Godot docs: MeshLibrary.get_item_name
     */
    fun getItemName(id: Int): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetString(Binds.getItemNameBind, segment, id)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the item's mesh.
     *
     * Generated from Godot docs: MeshLibrary.get_item_mesh
     */
    fun getItemMesh(id: Int): Mesh? {
        checkOpen()
        val ret = Mesh.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getItemMeshBind, segment, id))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the transform applied to the item's mesh.
     *
     * Generated from Godot docs: MeshLibrary.get_item_mesh_transform
     */
    fun getItemMeshTransform(id: Int): Transform3D {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetTransform3D(Binds.getItemMeshTransformBind, segment, id)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the item's shadow casting mode.
     *
     * Generated from Godot docs: MeshLibrary.get_item_mesh_cast_shadow
     */
    fun getItemMeshCastShadow(id: Int): RenderingServer.ShadowCastingSetting {
        checkOpen()
        val ret = RenderingServer.ShadowCastingSetting(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getItemMeshCastShadowBind, segment, id))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the item's navigation mesh.
     *
     * Generated from Godot docs: MeshLibrary.get_item_navigation_mesh
     */
    fun getItemNavigationMesh(id: Int): NavigationMesh? {
        checkOpen()
        val ret = NavigationMesh.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getItemNavigationMeshBind, segment, id))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the transform applied to the item's navigation mesh.
     *
     * Generated from Godot docs: MeshLibrary.get_item_navigation_mesh_transform
     */
    fun getItemNavigationMeshTransform(id: Int): Transform3D {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetTransform3D(Binds.getItemNavigationMeshTransformBind, segment, id)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the item's navigation layers bitmask.
     *
     * Generated from Godot docs: MeshLibrary.get_item_navigation_layers
     */
    fun getItemNavigationLayers(id: Int): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetUInt32(Binds.getItemNavigationLayersBind, segment, id)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns an item's collision shapes. The array consists of each `Shape3D` followed by its
     * `Transform3D`.
     *
     * Generated from Godot docs: MeshLibrary.get_item_shapes
     */
    fun getItemShapes(id: Int): List<Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetArray(Binds.getItemShapesBind, segment, id)
        ObjectCalls.keepReachable(this)
        return ret
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
        checkOpen()
        val ret = Texture2D.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getItemPreviewBind, segment, id))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Removes the item.
     *
     * Generated from Godot docs: MeshLibrary.remove_item
     */
    fun removeItem(id: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeItemBind, segment, id)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the first item with the given name, or `-1` if no item is found.
     *
     * Generated from Godot docs: MeshLibrary.find_item_by_name
     */
    fun findItemByName(name: String): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringArgRetInt(Binds.findItemByNameBind, segment, name)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Clears the library.
     *
     * Generated from Godot docs: MeshLibrary.clear
     */
    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearBind, segment)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns the list of item IDs in use.
     *
     * Generated from Godot docs: MeshLibrary.get_item_list
     */
    fun getItemList(): List<Int> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedInt32List(Binds.getItemListBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the number of items present in the library.
     *
     * Generated from Godot docs: MeshLibrary.get_item_count
     */
    fun getItemCount(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getItemCountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Gets an unused ID for a new item.
     *
     * Generated from Godot docs: MeshLibrary.get_last_unused_item_id
     */
    fun getLastUnusedItemId(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getLastUnusedItemIdBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): MeshLibrary? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): MeshLibrary? =
            if (handle.address() == 0L) null else RefCounted.owned(MeshLibrary(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): MeshLibrary? =
            if (handle.address() == 0L) null else MeshLibrary(GodotHandle(handle))

        /** A new MeshLibrary, GDScript's `MeshLibrary.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): MeshLibrary =
            RefCounted.owned(MeshLibrary(GodotHandle(requireConstructed(ObjectCalls.constructObject("MeshLibrary"), "MeshLibrary"))))
    }

    private object Binds {
        private const val CREATE_ITEM_HASH = 1286410249L
        @JvmField
        val createItemBind =
            ObjectCalls.getMethodBind("MeshLibrary", "create_item", CREATE_ITEM_HASH)

        private const val SET_ITEM_NAME_HASH = 501894301L
        @JvmField
        val setItemNameBind =
            ObjectCalls.getMethodBind("MeshLibrary", "set_item_name", SET_ITEM_NAME_HASH)

        private const val SET_ITEM_MESH_HASH = 969122797L
        @JvmField
        val setItemMeshBind =
            ObjectCalls.getMethodBind("MeshLibrary", "set_item_mesh", SET_ITEM_MESH_HASH)

        private const val SET_ITEM_MESH_TRANSFORM_HASH = 3616898986L
        @JvmField
        val setItemMeshTransformBind =
            ObjectCalls.getMethodBind("MeshLibrary", "set_item_mesh_transform", SET_ITEM_MESH_TRANSFORM_HASH)

        private const val SET_ITEM_MESH_CAST_SHADOW_HASH = 3923400443L
        @JvmField
        val setItemMeshCastShadowBind =
            ObjectCalls.getMethodBind("MeshLibrary", "set_item_mesh_cast_shadow", SET_ITEM_MESH_CAST_SHADOW_HASH)

        private const val SET_ITEM_NAVIGATION_MESH_HASH = 3483353960L
        @JvmField
        val setItemNavigationMeshBind =
            ObjectCalls.getMethodBind("MeshLibrary", "set_item_navigation_mesh", SET_ITEM_NAVIGATION_MESH_HASH)

        private const val SET_ITEM_NAVIGATION_MESH_TRANSFORM_HASH = 3616898986L
        @JvmField
        val setItemNavigationMeshTransformBind =
            ObjectCalls.getMethodBind("MeshLibrary", "set_item_navigation_mesh_transform", SET_ITEM_NAVIGATION_MESH_TRANSFORM_HASH)

        private const val SET_ITEM_NAVIGATION_LAYERS_HASH = 3937882851L
        @JvmField
        val setItemNavigationLayersBind =
            ObjectCalls.getMethodBind("MeshLibrary", "set_item_navigation_layers", SET_ITEM_NAVIGATION_LAYERS_HASH)

        private const val SET_ITEM_SHAPES_HASH = 537221740L
        @JvmField
        val setItemShapesBind =
            ObjectCalls.getMethodBind("MeshLibrary", "set_item_shapes", SET_ITEM_SHAPES_HASH)

        private const val SET_ITEM_PREVIEW_HASH = 666127730L
        @JvmField
        val setItemPreviewBind =
            ObjectCalls.getMethodBind("MeshLibrary", "set_item_preview", SET_ITEM_PREVIEW_HASH)

        private const val GET_ITEM_NAME_HASH = 844755477L
        @JvmField
        val getItemNameBind =
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_name", GET_ITEM_NAME_HASH)

        private const val GET_ITEM_MESH_HASH = 1576363275L
        @JvmField
        val getItemMeshBind =
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_mesh", GET_ITEM_MESH_HASH)

        private const val GET_ITEM_MESH_TRANSFORM_HASH = 1965739696L
        @JvmField
        val getItemMeshTransformBind =
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_mesh_transform", GET_ITEM_MESH_TRANSFORM_HASH)

        private const val GET_ITEM_MESH_CAST_SHADOW_HASH = 1841766007L
        @JvmField
        val getItemMeshCastShadowBind =
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_mesh_cast_shadow", GET_ITEM_MESH_CAST_SHADOW_HASH)

        private const val GET_ITEM_NAVIGATION_MESH_HASH = 2729647406L
        @JvmField
        val getItemNavigationMeshBind =
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_navigation_mesh", GET_ITEM_NAVIGATION_MESH_HASH)

        private const val GET_ITEM_NAVIGATION_MESH_TRANSFORM_HASH = 1965739696L
        @JvmField
        val getItemNavigationMeshTransformBind =
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_navigation_mesh_transform", GET_ITEM_NAVIGATION_MESH_TRANSFORM_HASH)

        private const val GET_ITEM_NAVIGATION_LAYERS_HASH = 923996154L
        @JvmField
        val getItemNavigationLayersBind =
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_navigation_layers", GET_ITEM_NAVIGATION_LAYERS_HASH)

        private const val GET_ITEM_SHAPES_HASH = 663333327L
        @JvmField
        val getItemShapesBind =
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_shapes", GET_ITEM_SHAPES_HASH)

        private const val GET_ITEM_PREVIEW_HASH = 3536238170L
        @JvmField
        val getItemPreviewBind =
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_preview", GET_ITEM_PREVIEW_HASH)

        private const val REMOVE_ITEM_HASH = 1286410249L
        @JvmField
        val removeItemBind =
            ObjectCalls.getMethodBind("MeshLibrary", "remove_item", REMOVE_ITEM_HASH)

        private const val FIND_ITEM_BY_NAME_HASH = 1321353865L
        @JvmField
        val findItemByNameBind =
            ObjectCalls.getMethodBind("MeshLibrary", "find_item_by_name", FIND_ITEM_BY_NAME_HASH)

        private const val CLEAR_HASH = 3218959716L
        @JvmField
        val clearBind =
            ObjectCalls.getMethodBind("MeshLibrary", "clear", CLEAR_HASH)

        private const val GET_ITEM_LIST_HASH = 1930428628L
        @JvmField
        val getItemListBind =
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_list", GET_ITEM_LIST_HASH)

        private const val GET_ITEM_COUNT_HASH = 3905245786L
        @JvmField
        val getItemCountBind =
            ObjectCalls.getMethodBind("MeshLibrary", "get_item_count", GET_ITEM_COUNT_HASH)

        private const val GET_LAST_UNUSED_ITEM_ID_HASH = 3905245786L
        @JvmField
        val getLastUnusedItemIdBind =
            ObjectCalls.getMethodBind("MeshLibrary", "get_last_unused_item_id", GET_LAST_UNUSED_ITEM_ID_HASH)
    }
}
