package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.RID

/**
 * Generated from Godot docs: Resource
 */
open class Resource(handle: MemorySegment) : RefCounted(handle) {
    var resourceLocalToScene: Boolean
        @JvmName("resourceLocalToSceneProperty")
        get() = isLocalToScene()
        @JvmName("setResourceLocalToSceneProperty")
        set(value) = setLocalToScene(value)

    var resourcePath: String
        @JvmName("resourcePathProperty")
        get() = getPath()
        @JvmName("setResourcePathProperty")
        set(value) = setPath(value)

    var resourceName: String
        @JvmName("resourceNameProperty")
        get() = getName()
        @JvmName("setResourceNameProperty")
        set(value) = setName(value)

    var resourceSceneUniqueId: String
        @JvmName("resourceSceneUniqueIdProperty")
        get() = getSceneUniqueId()
        @JvmName("setResourceSceneUniqueIdProperty")
        set(value) = setSceneUniqueId(value)

    fun setPath(path: String) {
        ObjectCalls.ptrcallWithStringArg(setPathBind, handle, path)
    }

    fun takeOverPath(path: String) {
        ObjectCalls.ptrcallWithStringArg(takeOverPathBind, handle, path)
    }

    fun getPath(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getPathBind, handle)
    }

    fun setPathCache(path: String) {
        ObjectCalls.ptrcallWithStringArg(setPathCacheBind, handle, path)
    }

    fun setName(name: String) {
        ObjectCalls.ptrcallWithStringArg(setNameBind, handle, name)
    }

    fun getName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getNameBind, handle)
    }

    fun getRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getRidBind, handle)
    }

    fun setLocalToScene(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setLocalToSceneBind, handle, enable)
    }

    fun isLocalToScene(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLocalToSceneBind, handle)
    }

    fun getLocalScene(): Node? {
        return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(getLocalSceneBind, handle))
    }

    fun setupLocalToScene() {
        ObjectCalls.ptrcallNoArgs(setupLocalToSceneBind, handle)
    }

    fun resetState() {
        ObjectCalls.ptrcallNoArgs(resetStateBind, handle)
    }

    fun setIdForPath(path: String, id: String) {
        ObjectCalls.ptrcallWithTwoStringArgs(setIdForPathBind, handle, path, id)
    }

    fun getIdForPath(path: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(getIdForPathBind, handle, path)
    }

    fun isBuiltIn(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isBuiltInBind, handle)
    }

    fun setSceneUniqueId(id: String) {
        ObjectCalls.ptrcallWithStringArg(setSceneUniqueIdBind, handle, id)
    }

    fun getSceneUniqueId(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSceneUniqueIdBind, handle)
    }

    fun emitChanged() {
        ObjectCalls.ptrcallNoArgs(emitChangedBind, handle)
    }

    fun duplicate(deep: Boolean = false): Resource? {
        return Resource.wrap(ObjectCalls.ptrcallWithBoolArgRetObject(duplicateBind, handle, deep))
    }

    fun duplicateDeep(deepSubresourcesMode: Long = 1L): Resource? {
        return Resource.wrap(ObjectCalls.ptrcallWithLongArgRetObject(duplicateDeepBind, handle, deepSubresourcesMode))
    }

    fun copyFromResource(resource: Resource?): Long {
        return ObjectCalls.ptrcallWithObjectArgRetLong(copyFromResourceBind, handle, resource?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    object Signals {
        const val changed: String = "changed"
        const val setupLocalToSceneRequested: String = "setup_local_to_scene_requested"
    }

    companion object {
        fun generateSceneUniqueId(): String {
            return ObjectCalls.ptrcallNoArgsRetString(generateSceneUniqueIdBind, MemorySegment.NULL)
        }

        const val DEEP_DUPLICATE_NONE: Long = 0L
        const val DEEP_DUPLICATE_INTERNAL: Long = 1L
        const val DEEP_DUPLICATE_ALL: Long = 2L

        fun fromHandle(handle: MemorySegment): Resource =
            Resource(handle)

        internal fun wrap(handle: MemorySegment): Resource? =
            if (handle.address() == 0L) null else Resource(handle)

        private const val SET_PATH_HASH = 83702148L
        private val setPathBind by lazy {
            ObjectCalls.getMethodBind("Resource", "set_path", SET_PATH_HASH)
        }

        private const val TAKE_OVER_PATH_HASH = 83702148L
        private val takeOverPathBind by lazy {
            ObjectCalls.getMethodBind("Resource", "take_over_path", TAKE_OVER_PATH_HASH)
        }

        private const val GET_PATH_HASH = 201670096L
        private val getPathBind by lazy {
            ObjectCalls.getMethodBind("Resource", "get_path", GET_PATH_HASH)
        }

        private const val SET_PATH_CACHE_HASH = 83702148L
        private val setPathCacheBind by lazy {
            ObjectCalls.getMethodBind("Resource", "set_path_cache", SET_PATH_CACHE_HASH)
        }

        private const val SET_NAME_HASH = 83702148L
        private val setNameBind by lazy {
            ObjectCalls.getMethodBind("Resource", "set_name", SET_NAME_HASH)
        }

        private const val GET_NAME_HASH = 201670096L
        private val getNameBind by lazy {
            ObjectCalls.getMethodBind("Resource", "get_name", GET_NAME_HASH)
        }

        private const val GET_RID_HASH = 2944877500L
        private val getRidBind by lazy {
            ObjectCalls.getMethodBind("Resource", "get_rid", GET_RID_HASH)
        }

        private const val SET_LOCAL_TO_SCENE_HASH = 2586408642L
        private val setLocalToSceneBind by lazy {
            ObjectCalls.getMethodBind("Resource", "set_local_to_scene", SET_LOCAL_TO_SCENE_HASH)
        }

        private const val IS_LOCAL_TO_SCENE_HASH = 36873697L
        private val isLocalToSceneBind by lazy {
            ObjectCalls.getMethodBind("Resource", "is_local_to_scene", IS_LOCAL_TO_SCENE_HASH)
        }

        private const val GET_LOCAL_SCENE_HASH = 3160264692L
        private val getLocalSceneBind by lazy {
            ObjectCalls.getMethodBind("Resource", "get_local_scene", GET_LOCAL_SCENE_HASH)
        }

        private const val SETUP_LOCAL_TO_SCENE_HASH = 3218959716L
        private val setupLocalToSceneBind by lazy {
            ObjectCalls.getMethodBind("Resource", "setup_local_to_scene", SETUP_LOCAL_TO_SCENE_HASH)
        }

        private const val RESET_STATE_HASH = 3218959716L
        private val resetStateBind by lazy {
            ObjectCalls.getMethodBind("Resource", "reset_state", RESET_STATE_HASH)
        }

        private const val SET_ID_FOR_PATH_HASH = 3186203200L
        private val setIdForPathBind by lazy {
            ObjectCalls.getMethodBind("Resource", "set_id_for_path", SET_ID_FOR_PATH_HASH)
        }

        private const val GET_ID_FOR_PATH_HASH = 3135753539L
        private val getIdForPathBind by lazy {
            ObjectCalls.getMethodBind("Resource", "get_id_for_path", GET_ID_FOR_PATH_HASH)
        }

        private const val IS_BUILT_IN_HASH = 36873697L
        private val isBuiltInBind by lazy {
            ObjectCalls.getMethodBind("Resource", "is_built_in", IS_BUILT_IN_HASH)
        }

        private const val GENERATE_SCENE_UNIQUE_ID_HASH = 2841200299L
        private val generateSceneUniqueIdBind by lazy {
            ObjectCalls.getMethodBind("Resource", "generate_scene_unique_id", GENERATE_SCENE_UNIQUE_ID_HASH)
        }

        private const val SET_SCENE_UNIQUE_ID_HASH = 83702148L
        private val setSceneUniqueIdBind by lazy {
            ObjectCalls.getMethodBind("Resource", "set_scene_unique_id", SET_SCENE_UNIQUE_ID_HASH)
        }

        private const val GET_SCENE_UNIQUE_ID_HASH = 201670096L
        private val getSceneUniqueIdBind by lazy {
            ObjectCalls.getMethodBind("Resource", "get_scene_unique_id", GET_SCENE_UNIQUE_ID_HASH)
        }

        private const val EMIT_CHANGED_HASH = 3218959716L
        private val emitChangedBind by lazy {
            ObjectCalls.getMethodBind("Resource", "emit_changed", EMIT_CHANGED_HASH)
        }

        private const val DUPLICATE_HASH = 482882304L
        private val duplicateBind by lazy {
            ObjectCalls.getMethodBind("Resource", "duplicate", DUPLICATE_HASH)
        }

        private const val DUPLICATE_DEEP_HASH = 905779109L
        private val duplicateDeepBind by lazy {
            ObjectCalls.getMethodBind("Resource", "duplicate_deep", DUPLICATE_DEEP_HASH)
        }

        private const val COPY_FROM_RESOURCE_HASH = 3338311164L
        private val copyFromResourceBind by lazy {
            ObjectCalls.getMethodBind("Resource", "copy_from_resource", COPY_FROM_RESOURCE_HASH)
        }
    }
}
