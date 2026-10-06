package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Texture provided by a `CameraFeed`.
 *
 * Generated from Godot docs: CameraTexture
 */
class CameraTexture(handle: GodotHandle) : Texture2D(handle) {
    var cameraFeedId: Int
        @JvmName("cameraFeedIdProperty")
        get() = getCameraFeedId()
        @JvmName("setCameraFeedIdProperty")
        set(value) = setCameraFeedId(value)

    var whichFeed: CameraServer.FeedImage
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
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setCameraFeedIdBind, segment, feedId)
    }

    /**
     * The ID of the `CameraFeed` for which we want to display the image.
     *
     * Generated from Godot docs: CameraTexture.get_camera_feed_id
     */
    fun getCameraFeedId(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getCameraFeedIdBind, segment)
    }

    /**
     * Which image within the `CameraFeed` we want access to, important if the camera image is split in
     * a Y and CbCr component.
     *
     * Generated from Godot docs: CameraTexture.set_which_feed
     */
    fun setWhichFeed(whichFeed: CameraServer.FeedImage) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setWhichFeedBind, segment, whichFeed.value)
    }

    /**
     * Which image within the `CameraFeed` we want access to, important if the camera image is split in
     * a Y and CbCr component.
     *
     * Generated from Godot docs: CameraTexture.get_which_feed
     */
    fun getWhichFeed(): CameraServer.FeedImage {
        checkOpen()
        return CameraServer.FeedImage(ObjectCalls.ptrcallNoArgsRetLong(Binds.getWhichFeedBind, segment))
    }

    /**
     * Convenience property that gives access to the active property of the `CameraFeed`.
     *
     * Generated from Godot docs: CameraTexture.set_camera_active
     */
    fun setCameraActive(active: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setCameraActiveBind, segment, active)
    }

    /**
     * Convenience property that gives access to the active property of the `CameraFeed`.
     *
     * Generated from Godot docs: CameraTexture.get_camera_active
     */
    fun getCameraActive(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getCameraActiveBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CameraTexture? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): CameraTexture? =
            if (handle.address() == 0L) null else RefCounted.owned(CameraTexture(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): CameraTexture? =
            if (handle.address() == 0L) null else CameraTexture(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_CAMERA_FEED_ID_HASH = 1286410249L
        @JvmField
        val setCameraFeedIdBind =
            ObjectCalls.getMethodBind("CameraTexture", "set_camera_feed_id", SET_CAMERA_FEED_ID_HASH)

        private const val GET_CAMERA_FEED_ID_HASH = 3905245786L
        @JvmField
        val getCameraFeedIdBind =
            ObjectCalls.getMethodBind("CameraTexture", "get_camera_feed_id", GET_CAMERA_FEED_ID_HASH)

        private const val SET_WHICH_FEED_HASH = 1595299230L
        @JvmField
        val setWhichFeedBind =
            ObjectCalls.getMethodBind("CameraTexture", "set_which_feed", SET_WHICH_FEED_HASH)

        private const val GET_WHICH_FEED_HASH = 91039457L
        @JvmField
        val getWhichFeedBind =
            ObjectCalls.getMethodBind("CameraTexture", "get_which_feed", GET_WHICH_FEED_HASH)

        private const val SET_CAMERA_ACTIVE_HASH = 2586408642L
        @JvmField
        val setCameraActiveBind =
            ObjectCalls.getMethodBind("CameraTexture", "set_camera_active", SET_CAMERA_ACTIVE_HASH)

        private const val GET_CAMERA_ACTIVE_HASH = 36873697L
        @JvmField
        val getCameraActiveBind =
            ObjectCalls.getMethodBind("CameraTexture", "get_camera_active", GET_CAMERA_ACTIVE_HASH)
    }
}
