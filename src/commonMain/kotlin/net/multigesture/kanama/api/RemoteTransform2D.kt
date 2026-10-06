package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * RemoteTransform2D pushes its own `Transform2D` to another `Node2D` derived node in the scene.
 *
 * Generated from Godot docs: RemoteTransform2D
 */
class RemoteTransform2D(handle: GodotHandle) : Node2D(handle) {
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
     * The `NodePath` to the remote node, relative to the RemoteTransform2D's position in the scene.
     *
     * Generated from Godot docs: RemoteTransform2D.set_remote_node
     */
    fun setRemoteNode(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(Binds.setRemoteNodeBind, segment, path)
    }

    /**
     * The `NodePath` to the remote node, relative to the RemoteTransform2D's position in the scene.
     *
     * Generated from Godot docs: RemoteTransform2D.get_remote_node
     */
    fun getRemoteNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getRemoteNodeBind, segment)
    }

    /**
     * `RemoteTransform2D` caches the remote node. It may not notice if the remote node disappears;
     * `force_update_cache` forces it to update the cache again.
     *
     * Generated from Godot docs: RemoteTransform2D.force_update_cache
     */
    fun forceUpdateCache() {
        ObjectCalls.ptrcallNoArgs(Binds.forceUpdateCacheBind, segment)
    }

    /**
     * If `true`, global coordinates are used. If `false`, local coordinates are used.
     *
     * Generated from Godot docs: RemoteTransform2D.set_use_global_coordinates
     */
    fun setUseGlobalCoordinates(useGlobalCoordinates: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseGlobalCoordinatesBind, segment, useGlobalCoordinates)
    }

    /**
     * If `true`, global coordinates are used. If `false`, local coordinates are used.
     *
     * Generated from Godot docs: RemoteTransform2D.get_use_global_coordinates
     */
    fun getUseGlobalCoordinates(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getUseGlobalCoordinatesBind, segment)
    }

    /**
     * If `true`, the remote node's position is updated.
     *
     * Generated from Godot docs: RemoteTransform2D.set_update_position
     */
    fun setUpdatePosition(updateRemotePosition: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUpdatePositionBind, segment, updateRemotePosition)
    }

    /**
     * If `true`, the remote node's position is updated.
     *
     * Generated from Godot docs: RemoteTransform2D.get_update_position
     */
    fun getUpdatePosition(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getUpdatePositionBind, segment)
    }

    /**
     * If `true`, the remote node's rotation is updated.
     *
     * Generated from Godot docs: RemoteTransform2D.set_update_rotation
     */
    fun setUpdateRotation(updateRemoteRotation: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUpdateRotationBind, segment, updateRemoteRotation)
    }

    /**
     * If `true`, the remote node's rotation is updated.
     *
     * Generated from Godot docs: RemoteTransform2D.get_update_rotation
     */
    fun getUpdateRotation(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getUpdateRotationBind, segment)
    }

    /**
     * If `true`, the remote node's scale is updated.
     *
     * Generated from Godot docs: RemoteTransform2D.set_update_scale
     */
    fun setUpdateScale(updateRemoteScale: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUpdateScaleBind, segment, updateRemoteScale)
    }

    /**
     * If `true`, the remote node's scale is updated.
     *
     * Generated from Godot docs: RemoteTransform2D.get_update_scale
     */
    fun getUpdateScale(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getUpdateScaleBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RemoteTransform2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): RemoteTransform2D? =
            if (handle.address() == 0L) null else RemoteTransform2D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_REMOTE_NODE_HASH = 1348162250L
        @JvmField
        val setRemoteNodeBind =
            ObjectCalls.getMethodBind("RemoteTransform2D", "set_remote_node", SET_REMOTE_NODE_HASH)

        private const val GET_REMOTE_NODE_HASH = 4075236667L
        @JvmField
        val getRemoteNodeBind =
            ObjectCalls.getMethodBind("RemoteTransform2D", "get_remote_node", GET_REMOTE_NODE_HASH)

        private const val FORCE_UPDATE_CACHE_HASH = 3218959716L
        @JvmField
        val forceUpdateCacheBind =
            ObjectCalls.getMethodBind("RemoteTransform2D", "force_update_cache", FORCE_UPDATE_CACHE_HASH)

        private const val SET_USE_GLOBAL_COORDINATES_HASH = 2586408642L
        @JvmField
        val setUseGlobalCoordinatesBind =
            ObjectCalls.getMethodBind("RemoteTransform2D", "set_use_global_coordinates", SET_USE_GLOBAL_COORDINATES_HASH)

        private const val GET_USE_GLOBAL_COORDINATES_HASH = 36873697L
        @JvmField
        val getUseGlobalCoordinatesBind =
            ObjectCalls.getMethodBind("RemoteTransform2D", "get_use_global_coordinates", GET_USE_GLOBAL_COORDINATES_HASH)

        private const val SET_UPDATE_POSITION_HASH = 2586408642L
        @JvmField
        val setUpdatePositionBind =
            ObjectCalls.getMethodBind("RemoteTransform2D", "set_update_position", SET_UPDATE_POSITION_HASH)

        private const val GET_UPDATE_POSITION_HASH = 36873697L
        @JvmField
        val getUpdatePositionBind =
            ObjectCalls.getMethodBind("RemoteTransform2D", "get_update_position", GET_UPDATE_POSITION_HASH)

        private const val SET_UPDATE_ROTATION_HASH = 2586408642L
        @JvmField
        val setUpdateRotationBind =
            ObjectCalls.getMethodBind("RemoteTransform2D", "set_update_rotation", SET_UPDATE_ROTATION_HASH)

        private const val GET_UPDATE_ROTATION_HASH = 36873697L
        @JvmField
        val getUpdateRotationBind =
            ObjectCalls.getMethodBind("RemoteTransform2D", "get_update_rotation", GET_UPDATE_ROTATION_HASH)

        private const val SET_UPDATE_SCALE_HASH = 2586408642L
        @JvmField
        val setUpdateScaleBind =
            ObjectCalls.getMethodBind("RemoteTransform2D", "set_update_scale", SET_UPDATE_SCALE_HASH)

        private const val GET_UPDATE_SCALE_HASH = 36873697L
        @JvmField
        val getUpdateScaleBind =
            ObjectCalls.getMethodBind("RemoteTransform2D", "get_update_scale", GET_UPDATE_SCALE_HASH)
    }
}
