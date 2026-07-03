package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A node used to preload sub-resources inside a scene.
 *
 * Generated from Godot docs: ResourcePreloader
 */
class ResourcePreloader(handle: MemorySegment) : Node(handle) {
    /**
     * Adds a resource to the preloader with the given `name`. If a resource with the given `name`
     * already exists, the new resource will be renamed to "`name` N" where N is an incrementing number
     * starting from 2.
     *
     * Generated from Godot docs: ResourcePreloader.add_resource
     */
    fun addResource(name: String, resource: Resource?) {
        ObjectCalls.ptrcallWithStringNameAndObjectArg(addResourceBind, handle, name, resource?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Removes the resource associated to `name` from the preloader.
     *
     * Generated from Godot docs: ResourcePreloader.remove_resource
     */
    fun removeResource(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeResourceBind, handle, name)
    }

    /**
     * Renames a resource inside the preloader from `name` to `newname`.
     *
     * Generated from Godot docs: ResourcePreloader.rename_resource
     */
    fun renameResource(name: String, newname: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(renameResourceBind, handle, name, newname)
    }

    /**
     * Returns `true` if the preloader contains a resource associated to `name`.
     *
     * Generated from Godot docs: ResourcePreloader.has_resource
     */
    fun hasResource(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasResourceBind, handle, name)
    }

    /**
     * Returns the resource associated to `name`.
     *
     * Generated from Godot docs: ResourcePreloader.get_resource
     */
    fun getResource(name: String): Resource? {
        return Resource.wrap(ObjectCalls.ptrcallWithStringNameArgRetObject(getResourceBind, handle, name))
    }

    /**
     * Returns the list of resources inside the preloader.
     *
     * Generated from Godot docs: ResourcePreloader.get_resource_list
     */
    fun getResourceList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getResourceListBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ResourcePreloader? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ResourcePreloader? =
            if (handle.address() == 0L) null else ResourcePreloader(handle)

        private const val ADD_RESOURCE_HASH = 1168801743L
        private val addResourceBind by lazy {
            ObjectCalls.getMethodBind("ResourcePreloader", "add_resource", ADD_RESOURCE_HASH)
        }

        private const val REMOVE_RESOURCE_HASH = 3304788590L
        private val removeResourceBind by lazy {
            ObjectCalls.getMethodBind("ResourcePreloader", "remove_resource", REMOVE_RESOURCE_HASH)
        }

        private const val RENAME_RESOURCE_HASH = 3740211285L
        private val renameResourceBind by lazy {
            ObjectCalls.getMethodBind("ResourcePreloader", "rename_resource", RENAME_RESOURCE_HASH)
        }

        private const val HAS_RESOURCE_HASH = 2619796661L
        private val hasResourceBind by lazy {
            ObjectCalls.getMethodBind("ResourcePreloader", "has_resource", HAS_RESOURCE_HASH)
        }

        private const val GET_RESOURCE_HASH = 3742749261L
        private val getResourceBind by lazy {
            ObjectCalls.getMethodBind("ResourcePreloader", "get_resource", GET_RESOURCE_HASH)
        }

        private const val GET_RESOURCE_LIST_HASH = 1139954409L
        private val getResourceListBind by lazy {
            ObjectCalls.getMethodBind("ResourcePreloader", "get_resource_list", GET_RESOURCE_LIST_HASH)
        }
    }
}
