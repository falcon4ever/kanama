package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * RemoteTransform2D pushes its own `Transform2D` to another `Node2D` derived node in the scene.
 *
 * Generated from Godot docs: RemoteTransform2D
 */
class RemoteTransform2D(handle: MemorySegment) : Node2D(handle) {
    var remotePath: NodePath
        @JvmName("remotePathProperty")
        get() = getRemoteNode()
        @JvmName("setRemotePathProperty")
        set(value) = setRemoteNode(value)

    var useGlobalCoordinates: Boolean
        @JvmName("useGlobalCoordinatesProperty")
        get() = getUseGlobalCoordinates()
        @JvmName("setUseGlobalCoordinatesProperty")
        set(value) = setUseGlobalCoordinates(value)

    var updatePosition: Boolean
        @JvmName("updatePositionProperty")
        get() = getUpdatePosition()
        @JvmName("setUpdatePositionProperty")
        set(value) = setUpdatePosition(value)

    var updateRotation: Boolean
        @JvmName("updateRotationProperty")
        get() = getUpdateRotation()
        @JvmName("setUpdateRotationProperty")
        set(value) = setUpdateRotation(value)

    var updateScale: Boolean
        @JvmName("updateScaleProperty")
        get() = getUpdateScale()
        @JvmName("setUpdateScaleProperty")
        set(value) = setUpdateScale(value)

    fun setRemoteNode(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setRemoteNodeBind, handle, path)
    }

    fun getRemoteNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getRemoteNodeBind, handle)
    }

    fun forceUpdateCache() {
        ObjectCalls.ptrcallNoArgs(forceUpdateCacheBind, handle)
    }

    fun setUseGlobalCoordinates(useGlobalCoordinates: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseGlobalCoordinatesBind, handle, useGlobalCoordinates)
    }

    fun getUseGlobalCoordinates(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseGlobalCoordinatesBind, handle)
    }

    fun setUpdatePosition(updateRemotePosition: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUpdatePositionBind, handle, updateRemotePosition)
    }

    fun getUpdatePosition(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUpdatePositionBind, handle)
    }

    fun setUpdateRotation(updateRemoteRotation: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUpdateRotationBind, handle, updateRemoteRotation)
    }

    fun getUpdateRotation(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUpdateRotationBind, handle)
    }

    fun setUpdateScale(updateRemoteScale: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUpdateScaleBind, handle, updateRemoteScale)
    }

    fun getUpdateScale(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUpdateScaleBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RemoteTransform2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RemoteTransform2D? =
            if (handle.address() == 0L) null else RemoteTransform2D(handle)

        private const val SET_REMOTE_NODE_HASH = 1348162250L
        private val setRemoteNodeBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform2D", "set_remote_node", SET_REMOTE_NODE_HASH)
        }

        private const val GET_REMOTE_NODE_HASH = 4075236667L
        private val getRemoteNodeBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform2D", "get_remote_node", GET_REMOTE_NODE_HASH)
        }

        private const val FORCE_UPDATE_CACHE_HASH = 3218959716L
        private val forceUpdateCacheBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform2D", "force_update_cache", FORCE_UPDATE_CACHE_HASH)
        }

        private const val SET_USE_GLOBAL_COORDINATES_HASH = 2586408642L
        private val setUseGlobalCoordinatesBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform2D", "set_use_global_coordinates", SET_USE_GLOBAL_COORDINATES_HASH)
        }

        private const val GET_USE_GLOBAL_COORDINATES_HASH = 36873697L
        private val getUseGlobalCoordinatesBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform2D", "get_use_global_coordinates", GET_USE_GLOBAL_COORDINATES_HASH)
        }

        private const val SET_UPDATE_POSITION_HASH = 2586408642L
        private val setUpdatePositionBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform2D", "set_update_position", SET_UPDATE_POSITION_HASH)
        }

        private const val GET_UPDATE_POSITION_HASH = 36873697L
        private val getUpdatePositionBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform2D", "get_update_position", GET_UPDATE_POSITION_HASH)
        }

        private const val SET_UPDATE_ROTATION_HASH = 2586408642L
        private val setUpdateRotationBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform2D", "set_update_rotation", SET_UPDATE_ROTATION_HASH)
        }

        private const val GET_UPDATE_ROTATION_HASH = 36873697L
        private val getUpdateRotationBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform2D", "get_update_rotation", GET_UPDATE_ROTATION_HASH)
        }

        private const val SET_UPDATE_SCALE_HASH = 2586408642L
        private val setUpdateScaleBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform2D", "set_update_scale", SET_UPDATE_SCALE_HASH)
        }

        private const val GET_UPDATE_SCALE_HASH = 36873697L
        private val getUpdateScaleBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform2D", "get_update_scale", GET_UPDATE_SCALE_HASH)
        }
    }
}
