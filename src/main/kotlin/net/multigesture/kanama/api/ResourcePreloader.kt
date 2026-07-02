package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A node used to preload sub-resources inside a scene.
 *
 * Generated from Godot docs: ResourcePreloader
 */
class ResourcePreloader(handle: MemorySegment) : Node(handle) {
    fun addResource(name: String, resource: Resource?) {
        ObjectCalls.ptrcallWithStringNameAndObjectArg(addResourceBind, handle, name, resource?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun removeResource(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeResourceBind, handle, name)
    }

    fun renameResource(name: String, newname: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(renameResourceBind, handle, name, newname)
    }

    fun hasResource(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasResourceBind, handle, name)
    }

    fun getResource(name: String): Resource? {
        return Resource.wrap(ObjectCalls.ptrcallWithStringNameArgRetObject(getResourceBind, handle, name))
    }

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
