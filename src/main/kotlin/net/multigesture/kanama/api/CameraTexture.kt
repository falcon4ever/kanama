package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Texture provided by a `CameraFeed`.
 *
 * Generated from Godot docs: CameraTexture
 */
class CameraTexture(handle: MemorySegment) : Texture2D(handle) {
    var cameraFeedId: Int
        @JvmName("cameraFeedIdProperty")
        get() = getCameraFeedId()
        @JvmName("setCameraFeedIdProperty")
        set(value) = setCameraFeedId(value)

    var whichFeed: Long
        @JvmName("whichFeedProperty")
        get() = getWhichFeed()
        @JvmName("setWhichFeedProperty")
        set(value) = setWhichFeed(value)

    var cameraIsActive: Boolean
        @JvmName("cameraIsActiveProperty")
        get() = getCameraActive()
        @JvmName("setCameraIsActiveProperty")
        set(value) = setCameraActive(value)

    /**
     * The ID of the `CameraFeed` for which we want to display the image.
     *
     * Generated from Godot docs: CameraTexture.set_camera_feed_id
     */
    fun setCameraFeedId(feedId: Int) {
        ObjectCalls.ptrcallWithIntArg(setCameraFeedIdBind, handle, feedId)
    }

    /**
     * The ID of the `CameraFeed` for which we want to display the image.
     *
     * Generated from Godot docs: CameraTexture.get_camera_feed_id
     */
    fun getCameraFeedId(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCameraFeedIdBind, handle)
    }

    /**
     * Which image within the `CameraFeed` we want access to, important if the camera image is split in
     * a Y and CbCr component.
     *
     * Generated from Godot docs: CameraTexture.set_which_feed
     */
    fun setWhichFeed(whichFeed: Long) {
        ObjectCalls.ptrcallWithLongArg(setWhichFeedBind, handle, whichFeed)
    }

    /**
     * Which image within the `CameraFeed` we want access to, important if the camera image is split in
     * a Y and CbCr component.
     *
     * Generated from Godot docs: CameraTexture.get_which_feed
     */
    fun getWhichFeed(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getWhichFeedBind, handle)
    }

    /**
     * Convenience property that gives access to the active property of the `CameraFeed`.
     *
     * Generated from Godot docs: CameraTexture.set_camera_active
     */
    fun setCameraActive(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCameraActiveBind, handle, active)
    }

    /**
     * Convenience property that gives access to the active property of the `CameraFeed`.
     *
     * Generated from Godot docs: CameraTexture.get_camera_active
     */
    fun getCameraActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getCameraActiveBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CameraTexture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CameraTexture? =
            if (handle.address() == 0L) null else CameraTexture(handle)

        private const val SET_CAMERA_FEED_ID_HASH = 1286410249L
        private val setCameraFeedIdBind by lazy {
            ObjectCalls.getMethodBind("CameraTexture", "set_camera_feed_id", SET_CAMERA_FEED_ID_HASH)
        }

        private const val GET_CAMERA_FEED_ID_HASH = 3905245786L
        private val getCameraFeedIdBind by lazy {
            ObjectCalls.getMethodBind("CameraTexture", "get_camera_feed_id", GET_CAMERA_FEED_ID_HASH)
        }

        private const val SET_WHICH_FEED_HASH = 1595299230L
        private val setWhichFeedBind by lazy {
            ObjectCalls.getMethodBind("CameraTexture", "set_which_feed", SET_WHICH_FEED_HASH)
        }

        private const val GET_WHICH_FEED_HASH = 91039457L
        private val getWhichFeedBind by lazy {
            ObjectCalls.getMethodBind("CameraTexture", "get_which_feed", GET_WHICH_FEED_HASH)
        }

        private const val SET_CAMERA_ACTIVE_HASH = 2586408642L
        private val setCameraActiveBind by lazy {
            ObjectCalls.getMethodBind("CameraTexture", "set_camera_active", SET_CAMERA_ACTIVE_HASH)
        }

        private const val GET_CAMERA_ACTIVE_HASH = 36873697L
        private val getCameraActiveBind by lazy {
            ObjectCalls.getMethodBind("CameraTexture", "get_camera_active", GET_CAMERA_ACTIVE_HASH)
        }
    }
}
