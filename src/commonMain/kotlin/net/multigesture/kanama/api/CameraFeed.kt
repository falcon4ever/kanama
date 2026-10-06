package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Transform2D

/**
 * A camera feed gives you access to a single physical camera attached to your device.
 *
 * Generated from Godot docs: CameraFeed
 */
class CameraFeed(handle: GodotHandle) : RefCounted(handle) {
    var feedIsActive: Boolean
        @JvmName("feedIsActiveProperty")
        get() = isActive()
        @JvmName("setFeedIsActiveProperty")
        set(value) = setActive(value)

    var feedTransform: Transform2D
        @JvmName("feedTransformProperty")
        get() = getTransform()
        @JvmName("setFeedTransformProperty")
        set(value) = setTransform(value)

    val formats: List<Any?>
        @JvmName("formatsProperty")
        get() = getFormats()

    /**
     * Returns the unique ID for this feed.
     *
     * Generated from Godot docs: CameraFeed.get_id
     */
    fun getId(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getIdBind, segment)
    }

    /**
     * If `true`, the feed is active.
     *
     * Generated from Godot docs: CameraFeed.is_active
     */
    fun isActive(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isActiveBind, segment)
    }

    /**
     * If `true`, the feed is active.
     *
     * Generated from Godot docs: CameraFeed.set_active
     */
    fun setActive(active: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setActiveBind, segment, active)
    }

    /**
     * Returns the camera's name.
     *
     * Generated from Godot docs: CameraFeed.get_name
     */
    fun getName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getNameBind, segment)
    }

    /**
     * Sets the camera's name.
     *
     * Generated from Godot docs: CameraFeed.set_name
     */
    fun setName(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setNameBind, segment, name)
    }

    /**
     * Returns the position of camera on the device.
     *
     * Generated from Godot docs: CameraFeed.get_position
     */
    fun getPosition(): CameraFeed.FeedPosition {
        checkOpen()
        return CameraFeed.FeedPosition(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPositionBind, segment))
    }

    /**
     * Sets the position of this camera.
     *
     * Generated from Godot docs: CameraFeed.set_position
     */
    fun setPosition(position: CameraFeed.FeedPosition) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setPositionBind, segment, position.value)
    }

    /**
     * The transform applied to the camera's image.
     *
     * Generated from Godot docs: CameraFeed.get_transform
     */
    fun getTransform(): Transform2D {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTransform2D(Binds.getTransformBind, segment)
    }

    /**
     * The transform applied to the camera's image.
     *
     * Generated from Godot docs: CameraFeed.set_transform
     */
    fun setTransform(transform: Transform2D) {
        checkOpen()
        ObjectCalls.ptrcallWithTransform2DArg(Binds.setTransformBind, segment, transform)
    }

    /**
     * Sets RGB image for this feed.
     *
     * Generated from Godot docs: CameraFeed.set_rgb_image
     */
    fun setRgbImage(rgbImage: Image?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setRgbImageBind, segment, listOf(rgbImage?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Sets YCbCr image for this feed.
     *
     * Generated from Godot docs: CameraFeed.set_ycbcr_image
     */
    fun setYcbcrImage(ycbcrImage: Image?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setYcbcrImageBind, segment, listOf(ycbcrImage?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Sets Y and CbCr images for this feed.
     *
     * Generated from Godot docs: CameraFeed.set_ycbcr_images
     */
    fun setYcbcrImages(yImage: Image?, cbcrImage: Image?) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoObjectArgs(Binds.setYcbcrImagesBind, segment, yImage?.requireOpenHandle() ?: NULL_SEGMENT, cbcrImage?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Sets the feed as external feed provided by another library.
     *
     * Generated from Godot docs: CameraFeed.set_external
     */
    fun setExternal(width: Int, height: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setExternalBind, segment, width, height)
    }

    /**
     * Returns the texture backend ID (usable by some external libraries that need a handle to a
     * texture to write data).
     *
     * Generated from Godot docs: CameraFeed.get_texture_tex_id
     */
    fun getTextureTexId(feedImageType: CameraServer.FeedImage): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetLong(Binds.getTextureTexIdBind, segment, feedImageType.value)
    }

    /**
     * Returns feed image data type.
     *
     * Generated from Godot docs: CameraFeed.get_datatype
     */
    fun getDatatype(): CameraFeed.FeedDataType {
        checkOpen()
        return CameraFeed.FeedDataType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getDatatypeBind, segment))
    }

    /**
     * Formats supported by the feed. Each entry is a `Dictionary` describing format parameters.
     *
     * Generated from Godot docs: CameraFeed.get_formats
     */
    fun getFormats(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getFormatsBind, segment)
    }

    /**
     * Sets the feed format parameters for the given `index` in the `formats` array. Returns `true` on
     * success. By default, the YUYV encoded stream is transformed to `FeedDataType.RGB`. The YUYV
     * encoded stream output format can be changed by setting `parameters`'s `output` entry to one of
     * the following: - `"separate"` will result in `FeedDataType.YCBCR_SEP`; - `"grayscale"` will
     * result in desaturated `FeedDataType.RGB`; - `"copy"` will result in `FeedDataType.YCBCR`.
     *
     * Generated from Godot docs: CameraFeed.set_format
     */
    fun setFormat(index: Int, parameters: Map<String, Any?>): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntAndDictionaryArgRetBool(Binds.setFormatBind, segment, index, parameters)
    }

    /** Signal `frame_changed()`; see [TypedSignal]. */
    val frameChanged: Signal0
        @JvmName("frameChangedTypedSignal")
        get() = Signal0(this, "frame_changed")

    /** Signal `format_changed()`; see [TypedSignal]. */
    val formatChanged: Signal0
        @JvmName("formatChangedTypedSignal")
        get() = Signal0(this, "format_changed")

    object Signals {
        const val frameChanged: String = "frame_changed"
        const val formatChanged: String = "format_changed"
    }

    /**
     * Godot's `CameraFeed.FeedDataType` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`CameraFeed.FeedDataType.<NAME>`).
     *
     * Generated from Godot docs: CameraFeed.FeedDataType
     */
    @JvmInline
    value class FeedDataType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * No image set for the feed.
             *
             * Generated from Godot docs: CameraFeed.FEED_NOIMAGE
             */
            val NOIMAGE: FeedDataType get() = FeedDataType(0L)
            /**
             * Feed supplies RGB images.
             *
             * Generated from Godot docs: CameraFeed.FEED_RGB
             */
            val RGB: FeedDataType get() = FeedDataType(1L)
            /**
             * Feed supplies YCbCr images that need to be converted to RGB.
             *
             * Generated from Godot docs: CameraFeed.FEED_YCBCR
             */
            val YCBCR: FeedDataType get() = FeedDataType(2L)
            /**
             * Feed supplies separate Y and CbCr images that need to be combined and converted to RGB.
             *
             * Generated from Godot docs: CameraFeed.FEED_YCBCR_SEP
             */
            val YCBCR_SEP: FeedDataType get() = FeedDataType(3L)
            /**
             * Feed supplies external image.
             *
             * Generated from Godot docs: CameraFeed.FEED_EXTERNAL
             */
            val EXTERNAL: FeedDataType get() = FeedDataType(4L)
        }
    }

    /**
     * Godot's `CameraFeed.FeedPosition` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`CameraFeed.FeedPosition.<NAME>`).
     *
     * Generated from Godot docs: CameraFeed.FeedPosition
     */
    @JvmInline
    value class FeedPosition(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Unspecified position.
             *
             * Generated from Godot docs: CameraFeed.FEED_UNSPECIFIED
             */
            val UNSPECIFIED: FeedPosition get() = FeedPosition(0L)
            /**
             * Camera is mounted at the front of the device.
             *
             * Generated from Godot docs: CameraFeed.FEED_FRONT
             */
            val FRONT: FeedPosition get() = FeedPosition(1L)
            /**
             * Camera is mounted at the back of the device.
             *
             * Generated from Godot docs: CameraFeed.FEED_BACK
             */
            val BACK: FeedPosition get() = FeedPosition(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CameraFeed? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): CameraFeed? =
            if (handle.address() == 0L) null else RefCounted.owned(CameraFeed(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): CameraFeed? =
            if (handle.address() == 0L) null else CameraFeed(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_ID_HASH = 3905245786L
        @JvmField
        val getIdBind =
            ObjectCalls.getMethodBind("CameraFeed", "get_id", GET_ID_HASH)

        private const val IS_ACTIVE_HASH = 36873697L
        @JvmField
        val isActiveBind =
            ObjectCalls.getMethodBind("CameraFeed", "is_active", IS_ACTIVE_HASH)

        private const val SET_ACTIVE_HASH = 2586408642L
        @JvmField
        val setActiveBind =
            ObjectCalls.getMethodBind("CameraFeed", "set_active", SET_ACTIVE_HASH)

        private const val GET_NAME_HASH = 201670096L
        @JvmField
        val getNameBind =
            ObjectCalls.getMethodBind("CameraFeed", "get_name", GET_NAME_HASH)

        private const val SET_NAME_HASH = 83702148L
        @JvmField
        val setNameBind =
            ObjectCalls.getMethodBind("CameraFeed", "set_name", SET_NAME_HASH)

        private const val GET_POSITION_HASH = 2711679033L
        @JvmField
        val getPositionBind =
            ObjectCalls.getMethodBind("CameraFeed", "get_position", GET_POSITION_HASH)

        private const val SET_POSITION_HASH = 611162623L
        @JvmField
        val setPositionBind =
            ObjectCalls.getMethodBind("CameraFeed", "set_position", SET_POSITION_HASH)

        private const val GET_TRANSFORM_HASH = 3814499831L
        @JvmField
        val getTransformBind =
            ObjectCalls.getMethodBind("CameraFeed", "get_transform", GET_TRANSFORM_HASH)

        private const val SET_TRANSFORM_HASH = 2761652528L
        @JvmField
        val setTransformBind =
            ObjectCalls.getMethodBind("CameraFeed", "set_transform", SET_TRANSFORM_HASH)

        private const val SET_RGB_IMAGE_HASH = 532598488L
        @JvmField
        val setRgbImageBind =
            ObjectCalls.getMethodBind("CameraFeed", "set_rgb_image", SET_RGB_IMAGE_HASH)

        private const val SET_YCBCR_IMAGE_HASH = 532598488L
        @JvmField
        val setYcbcrImageBind =
            ObjectCalls.getMethodBind("CameraFeed", "set_ycbcr_image", SET_YCBCR_IMAGE_HASH)

        private const val SET_YCBCR_IMAGES_HASH = 1986484629L
        @JvmField
        val setYcbcrImagesBind =
            ObjectCalls.getMethodBind("CameraFeed", "set_ycbcr_images", SET_YCBCR_IMAGES_HASH)

        private const val SET_EXTERNAL_HASH = 3937882851L
        @JvmField
        val setExternalBind =
            ObjectCalls.getMethodBind("CameraFeed", "set_external", SET_EXTERNAL_HASH)

        private const val GET_TEXTURE_TEX_ID_HASH = 1135699418L
        @JvmField
        val getTextureTexIdBind =
            ObjectCalls.getMethodBind("CameraFeed", "get_texture_tex_id", GET_TEXTURE_TEX_ID_HASH)

        private const val GET_DATATYPE_HASH = 1477782850L
        @JvmField
        val getDatatypeBind =
            ObjectCalls.getMethodBind("CameraFeed", "get_datatype", GET_DATATYPE_HASH)

        private const val GET_FORMATS_HASH = 3995934104L
        @JvmField
        val getFormatsBind =
            ObjectCalls.getMethodBind("CameraFeed", "get_formats", GET_FORMATS_HASH)

        private const val SET_FORMAT_HASH = 31872775L
        @JvmField
        val setFormatBind =
            ObjectCalls.getMethodBind("CameraFeed", "set_format", SET_FORMAT_HASH)
    }
}
