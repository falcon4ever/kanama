package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

/**
 * RemoteTransform3D pushes its own `Transform3D` to another `Node3D` derived Node in the scene.
 *
 * Generated from Godot docs: RemoteTransform3D
 */
class RemoteTransform3D(handle: MemorySegment) : Node3D(handle) {
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

    /**
     * The `NodePath` to the remote node, relative to the RemoteTransform3D's position in the scene.
     *
     * Generated from Godot docs: RemoteTransform3D.set_remote_node
     */
    fun setRemoteNode(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setRemoteNodeBind, handle, path)
    }

    /**
     * The `NodePath` to the remote node, relative to the RemoteTransform3D's position in the scene.
     *
     * Generated from Godot docs: RemoteTransform3D.get_remote_node
     */
    fun getRemoteNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getRemoteNodeBind, handle)
    }

    /**
     * `RemoteTransform3D` caches the remote node. It may not notice if the remote node disappears;
     * `force_update_cache` forces it to update the cache again.
     *
     * Generated from Godot docs: RemoteTransform3D.force_update_cache
     */
    fun forceUpdateCache() {
        ObjectCalls.ptrcallNoArgs(forceUpdateCacheBind, handle)
    }

    /**
     * If `true`, global coordinates are used. If `false`, local coordinates are used.
     *
     * Generated from Godot docs: RemoteTransform3D.set_use_global_coordinates
     */
    fun setUseGlobalCoordinates(useGlobalCoordinates: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseGlobalCoordinatesBind, handle, useGlobalCoordinates)
    }

    /**
     * If `true`, global coordinates are used. If `false`, local coordinates are used.
     *
     * Generated from Godot docs: RemoteTransform3D.get_use_global_coordinates
     */
    fun getUseGlobalCoordinates(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseGlobalCoordinatesBind, handle)
    }

    /**
     * If `true`, the remote node's position is updated.
     *
     * Generated from Godot docs: RemoteTransform3D.set_update_position
     */
    fun setUpdatePosition(updateRemotePosition: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUpdatePositionBind, handle, updateRemotePosition)
    }

    /**
     * If `true`, the remote node's position is updated.
     *
     * Generated from Godot docs: RemoteTransform3D.get_update_position
     */
    fun getUpdatePosition(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUpdatePositionBind, handle)
    }

    /**
     * If `true`, the remote node's rotation is updated.
     *
     * Generated from Godot docs: RemoteTransform3D.set_update_rotation
     */
    fun setUpdateRotation(updateRemoteRotation: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUpdateRotationBind, handle, updateRemoteRotation)
    }

    /**
     * If `true`, the remote node's rotation is updated.
     *
     * Generated from Godot docs: RemoteTransform3D.get_update_rotation
     */
    fun getUpdateRotation(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUpdateRotationBind, handle)
    }

    /**
     * If `true`, the remote node's scale is updated.
     *
     * Generated from Godot docs: RemoteTransform3D.set_update_scale
     */
    fun setUpdateScale(updateRemoteScale: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUpdateScaleBind, handle, updateRemoteScale)
    }

    /**
     * If `true`, the remote node's scale is updated.
     *
     * Generated from Godot docs: RemoteTransform3D.get_update_scale
     */
    fun getUpdateScale(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUpdateScaleBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RemoteTransform3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RemoteTransform3D? =
            if (handle.address() == 0L) null else RemoteTransform3D(handle)

        private const val SET_REMOTE_NODE_HASH = 1348162250L
        private val setRemoteNodeBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform3D", "set_remote_node", SET_REMOTE_NODE_HASH)
        }

        private const val GET_REMOTE_NODE_HASH = 4075236667L
        private val getRemoteNodeBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform3D", "get_remote_node", GET_REMOTE_NODE_HASH)
        }

        private const val FORCE_UPDATE_CACHE_HASH = 3218959716L
        private val forceUpdateCacheBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform3D", "force_update_cache", FORCE_UPDATE_CACHE_HASH)
        }

        private const val SET_USE_GLOBAL_COORDINATES_HASH = 2586408642L
        private val setUseGlobalCoordinatesBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform3D", "set_use_global_coordinates", SET_USE_GLOBAL_COORDINATES_HASH)
        }

        private const val GET_USE_GLOBAL_COORDINATES_HASH = 36873697L
        private val getUseGlobalCoordinatesBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform3D", "get_use_global_coordinates", GET_USE_GLOBAL_COORDINATES_HASH)
        }

        private const val SET_UPDATE_POSITION_HASH = 2586408642L
        private val setUpdatePositionBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform3D", "set_update_position", SET_UPDATE_POSITION_HASH)
        }

        private const val GET_UPDATE_POSITION_HASH = 36873697L
        private val getUpdatePositionBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform3D", "get_update_position", GET_UPDATE_POSITION_HASH)
        }

        private const val SET_UPDATE_ROTATION_HASH = 2586408642L
        private val setUpdateRotationBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform3D", "set_update_rotation", SET_UPDATE_ROTATION_HASH)
        }

        private const val GET_UPDATE_ROTATION_HASH = 36873697L
        private val getUpdateRotationBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform3D", "get_update_rotation", GET_UPDATE_ROTATION_HASH)
        }

        private const val SET_UPDATE_SCALE_HASH = 2586408642L
        private val setUpdateScaleBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform3D", "set_update_scale", SET_UPDATE_SCALE_HASH)
        }

        private const val GET_UPDATE_SCALE_HASH = 36873697L
        private val getUpdateScaleBind by lazy {
            ObjectCalls.getMethodBind("RemoteTransform3D", "get_update_scale", GET_UPDATE_SCALE_HASH)
        }
    }
}
