package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: Resource
 */
open class Resource(handle: MemorySegment) : RefCounted(handle) {
    var resourceLocalToScene: Boolean
        @JvmName("resourceLocalToSceneProperty")
        get() = isLocalToScene()
        @JvmName("setResourceLocalToSceneProperty")
        set(value) = setLocalToScene(value)

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

    fun isBuiltIn(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isBuiltInBind, handle)
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
        const val DEEP_DUPLICATE_NONE: Long = 0L
        const val DEEP_DUPLICATE_INTERNAL: Long = 1L
        const val DEEP_DUPLICATE_ALL: Long = 2L

        fun fromHandle(handle: MemorySegment): Resource? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Resource? =
            if (handle.address() == 0L) null else Resource(handle)

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

        private const val IS_BUILT_IN_HASH = 36873697L
        private val isBuiltInBind by lazy {
            ObjectCalls.getMethodBind("Resource", "is_built_in", IS_BUILT_IN_HASH)
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
