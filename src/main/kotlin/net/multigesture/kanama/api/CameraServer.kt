package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Server keeping track of different cameras accessible in Godot.
 *
 * Generated from Godot docs: CameraServer
 */
object CameraServer {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("CameraServer")
    }

    const val FEED_RGBA_IMAGE: Long = 0L
    const val FEED_YCBCR_IMAGE: Long = 0L
    const val FEED_Y_IMAGE: Long = 0L
    const val FEED_CBCR_IMAGE: Long = 1L

    var monitoringFeeds: Boolean
        @JvmName("monitoringFeedsProperty")
        get() = isMonitoringFeeds()
        @JvmName("setMonitoringFeedsProperty")
        set(value) = setMonitoringFeeds(value)

    /**
     * If `true`, the server is actively monitoring available camera feeds. This has a performance
     * cost, so only set it to `true` when you're actively accessing the camera. Note: After setting it
     * to `true`, you can receive updated camera feeds through the `camera_feeds_updated` signal.
     *
     * Generated from Godot docs: CameraServer.set_monitoring_feeds
     */
    @JvmStatic
    fun setMonitoringFeeds(isMonitoringFeeds: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMonitoringFeedsBind, singleton, isMonitoringFeeds)
    }

    /**
     * If `true`, the server is actively monitoring available camera feeds. This has a performance
     * cost, so only set it to `true` when you're actively accessing the camera. Note: After setting it
     * to `true`, you can receive updated camera feeds through the `camera_feeds_updated` signal.
     *
     * Generated from Godot docs: CameraServer.is_monitoring_feeds
     */
    @JvmStatic
    fun isMonitoringFeeds(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMonitoringFeedsBind, singleton)
    }

    /**
     * Returns the `CameraFeed` corresponding to the camera with the given `index`.
     *
     * Generated from Godot docs: CameraServer.get_feed
     */
    @JvmStatic
    fun getFeed(index: Int): CameraFeed? {
        return CameraFeed.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getFeedBind, singleton, index))
    }

    /**
     * Returns the number of `CameraFeed`s registered.
     *
     * Generated from Godot docs: CameraServer.get_feed_count
     */
    @JvmStatic
    fun getFeedCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFeedCountBind, singleton)
    }

    @JvmStatic
    /**
     * Returns an array of `CameraFeed`s.
     *
     * Generated from Godot docs: CameraServer.feeds
     */
    fun feeds(): List<CameraFeed> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(feedsBind, singleton, CameraFeed::fromHandle)
    }

    /**
     * Adds the camera `feed` to the camera server.
     *
     * Generated from Godot docs: CameraServer.add_feed
     */
    @JvmStatic
    fun addFeed(feed: CameraFeed?) {
        ObjectCalls.ptrcallWithObjectArgs(addFeedBind, singleton, listOf(feed?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Removes the specified camera `feed`.
     *
     * Generated from Godot docs: CameraServer.remove_feed
     */
    @JvmStatic
    fun removeFeed(feed: CameraFeed?) {
        ObjectCalls.ptrcallWithObjectArgs(removeFeedBind, singleton, listOf(feed?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    object Signals {
        const val cameraFeedAdded: String = "camera_feed_added"
        const val cameraFeedRemoved: String = "camera_feed_removed"
        const val cameraFeedsUpdated: String = "camera_feeds_updated"
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): CameraServer? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): CameraServer? =
        if (handle.address() == 0L) null else this

    private const val SET_MONITORING_FEEDS_HASH = 2586408642L
    private val setMonitoringFeedsBind by lazy {
        ObjectCalls.getMethodBind("CameraServer", "set_monitoring_feeds", SET_MONITORING_FEEDS_HASH)
    }

    private const val IS_MONITORING_FEEDS_HASH = 36873697L
    private val isMonitoringFeedsBind by lazy {
        ObjectCalls.getMethodBind("CameraServer", "is_monitoring_feeds", IS_MONITORING_FEEDS_HASH)
    }

    private const val GET_FEED_HASH = 361927068L
    private val getFeedBind by lazy {
        ObjectCalls.getMethodBind("CameraServer", "get_feed", GET_FEED_HASH)
    }

    private const val GET_FEED_COUNT_HASH = 2455072627L
    private val getFeedCountBind by lazy {
        ObjectCalls.getMethodBind("CameraServer", "get_feed_count", GET_FEED_COUNT_HASH)
    }

    private const val FEEDS_HASH = 2915620761L
    private val feedsBind by lazy {
        ObjectCalls.getMethodBind("CameraServer", "feeds", FEEDS_HASH)
    }

    private const val ADD_FEED_HASH = 3204782488L
    private val addFeedBind by lazy {
        ObjectCalls.getMethodBind("CameraServer", "add_feed", ADD_FEED_HASH)
    }

    private const val REMOVE_FEED_HASH = 3204782488L
    private val removeFeedBind by lazy {
        ObjectCalls.getMethodBind("CameraServer", "remove_feed", REMOVE_FEED_HASH)
    }
}
