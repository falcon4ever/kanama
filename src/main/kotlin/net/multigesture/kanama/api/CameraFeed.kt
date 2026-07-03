package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform2D

/**
 * A camera feed gives you access to a single physical camera attached to your device.
 *
 * Generated from Godot docs: CameraFeed
 */
class CameraFeed(handle: MemorySegment) : RefCounted(handle) {
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
        return ObjectCalls.ptrcallNoArgsRetInt(getIdBind, handle)
    }

    /**
     * If `true`, the feed is active.
     *
     * Generated from Godot docs: CameraFeed.is_active
     */
    fun isActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isActiveBind, handle)
    }

    /**
     * If `true`, the feed is active.
     *
     * Generated from Godot docs: CameraFeed.set_active
     */
    fun setActive(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setActiveBind, handle, active)
    }

    /**
     * Returns the camera's name.
     *
     * Generated from Godot docs: CameraFeed.get_name
     */
    fun getName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getNameBind, handle)
    }

    /**
     * Sets the camera's name.
     *
     * Generated from Godot docs: CameraFeed.set_name
     */
    fun setName(name: String) {
        ObjectCalls.ptrcallWithStringArg(setNameBind, handle, name)
    }

    /**
     * Returns the position of camera on the device.
     *
     * Generated from Godot docs: CameraFeed.get_position
     */
    fun getPosition(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPositionBind, handle)
    }

    /**
     * Sets the position of this camera.
     *
     * Generated from Godot docs: CameraFeed.set_position
     */
    fun setPosition(position: Long) {
        ObjectCalls.ptrcallWithLongArg(setPositionBind, handle, position)
    }

    /**
     * The transform applied to the camera's image.
     *
     * Generated from Godot docs: CameraFeed.get_transform
     */
    fun getTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(getTransformBind, handle)
    }

    /**
     * The transform applied to the camera's image.
     *
     * Generated from Godot docs: CameraFeed.set_transform
     */
    fun setTransform(transform: Transform2D) {
        ObjectCalls.ptrcallWithTransform2DArg(setTransformBind, handle, transform)
    }

    /**
     * Sets RGB image for this feed.
     *
     * Generated from Godot docs: CameraFeed.set_rgb_image
     */
    fun setRgbImage(rgbImage: Image?) {
        ObjectCalls.ptrcallWithObjectArgs(setRgbImageBind, handle, listOf(rgbImage?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Sets YCbCr image for this feed.
     *
     * Generated from Godot docs: CameraFeed.set_ycbcr_image
     */
    fun setYcbcrImage(ycbcrImage: Image?) {
        ObjectCalls.ptrcallWithObjectArgs(setYcbcrImageBind, handle, listOf(ycbcrImage?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Sets Y and CbCr images for this feed.
     *
     * Generated from Godot docs: CameraFeed.set_ycbcr_images
     */
    fun setYcbcrImages(yImage: Image?, cbcrImage: Image?) {
        ObjectCalls.ptrcallWithTwoObjectArgs(setYcbcrImagesBind, handle, yImage?.requireOpenHandle() ?: MemorySegment.NULL, cbcrImage?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Sets the feed as external feed provided by another library.
     *
     * Generated from Godot docs: CameraFeed.set_external
     */
    fun setExternal(width: Int, height: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setExternalBind, handle, width, height)
    }

    /**
     * Returns the texture backend ID (usable by some external libraries that need a handle to a
     * texture to write data).
     *
     * Generated from Godot docs: CameraFeed.get_texture_tex_id
     */
    fun getTextureTexId(feedImageType: Long): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(getTextureTexIdBind, handle, feedImageType)
    }

    /**
     * Returns feed image data type.
     *
     * Generated from Godot docs: CameraFeed.get_datatype
     */
    fun getDatatype(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDatatypeBind, handle)
    }

    /**
     * Formats supported by the feed. Each entry is a `Dictionary` describing format parameters.
     *
     * Generated from Godot docs: CameraFeed.get_formats
     */
    fun getFormats(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getFormatsBind, handle)
    }

    /**
     * Sets the feed format parameters for the given `index` in the `formats` array. Returns `true` on
     * success. By default, the YUYV encoded stream is transformed to `FEED_RGB`. The YUYV encoded
     * stream output format can be changed by setting `parameters`'s `output` entry to one of the
     * following: - `"separate"` will result in `FEED_YCBCR_SEP`; - `"grayscale"` will result in
     * desaturated `FEED_RGB`; - `"copy"` will result in `FEED_YCBCR`.
     *
     * Generated from Godot docs: CameraFeed.set_format
     */
    fun setFormat(index: Int, parameters: Map<String, Any?>): Boolean {
        return ObjectCalls.ptrcallWithIntAndDictionaryArgRetBool(setFormatBind, handle, index, parameters)
    }

    object Signals {
        const val frameChanged: String = "frame_changed"
        const val formatChanged: String = "format_changed"
    }

    companion object {
        const val FEED_NOIMAGE: Long = 0L
        const val FEED_RGB: Long = 1L
        const val FEED_YCBCR: Long = 2L
        const val FEED_YCBCR_SEP: Long = 3L
        const val FEED_EXTERNAL: Long = 4L
        const val FEED_UNSPECIFIED: Long = 0L
        const val FEED_FRONT: Long = 1L
        const val FEED_BACK: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): CameraFeed? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CameraFeed? =
            if (handle.address() == 0L) null else CameraFeed(handle)

        private const val GET_ID_HASH = 3905245786L
        private val getIdBind by lazy {
            ObjectCalls.getMethodBind("CameraFeed", "get_id", GET_ID_HASH)
        }

        private const val IS_ACTIVE_HASH = 36873697L
        private val isActiveBind by lazy {
            ObjectCalls.getMethodBind("CameraFeed", "is_active", IS_ACTIVE_HASH)
        }

        private const val SET_ACTIVE_HASH = 2586408642L
        private val setActiveBind by lazy {
            ObjectCalls.getMethodBind("CameraFeed", "set_active", SET_ACTIVE_HASH)
        }

        private const val GET_NAME_HASH = 201670096L
        private val getNameBind by lazy {
            ObjectCalls.getMethodBind("CameraFeed", "get_name", GET_NAME_HASH)
        }

        private const val SET_NAME_HASH = 83702148L
        private val setNameBind by lazy {
            ObjectCalls.getMethodBind("CameraFeed", "set_name", SET_NAME_HASH)
        }

        private const val GET_POSITION_HASH = 2711679033L
        private val getPositionBind by lazy {
            ObjectCalls.getMethodBind("CameraFeed", "get_position", GET_POSITION_HASH)
        }

        private const val SET_POSITION_HASH = 611162623L
        private val setPositionBind by lazy {
            ObjectCalls.getMethodBind("CameraFeed", "set_position", SET_POSITION_HASH)
        }

        private const val GET_TRANSFORM_HASH = 3814499831L
        private val getTransformBind by lazy {
            ObjectCalls.getMethodBind("CameraFeed", "get_transform", GET_TRANSFORM_HASH)
        }

        private const val SET_TRANSFORM_HASH = 2761652528L
        private val setTransformBind by lazy {
            ObjectCalls.getMethodBind("CameraFeed", "set_transform", SET_TRANSFORM_HASH)
        }

        private const val SET_RGB_IMAGE_HASH = 532598488L
        private val setRgbImageBind by lazy {
            ObjectCalls.getMethodBind("CameraFeed", "set_rgb_image", SET_RGB_IMAGE_HASH)
        }

        private const val SET_YCBCR_IMAGE_HASH = 532598488L
        private val setYcbcrImageBind by lazy {
            ObjectCalls.getMethodBind("CameraFeed", "set_ycbcr_image", SET_YCBCR_IMAGE_HASH)
        }

        private const val SET_YCBCR_IMAGES_HASH = 1986484629L
        private val setYcbcrImagesBind by lazy {
            ObjectCalls.getMethodBind("CameraFeed", "set_ycbcr_images", SET_YCBCR_IMAGES_HASH)
        }

        private const val SET_EXTERNAL_HASH = 3937882851L
        private val setExternalBind by lazy {
            ObjectCalls.getMethodBind("CameraFeed", "set_external", SET_EXTERNAL_HASH)
        }

        private const val GET_TEXTURE_TEX_ID_HASH = 1135699418L
        private val getTextureTexIdBind by lazy {
            ObjectCalls.getMethodBind("CameraFeed", "get_texture_tex_id", GET_TEXTURE_TEX_ID_HASH)
        }

        private const val GET_DATATYPE_HASH = 1477782850L
        private val getDatatypeBind by lazy {
            ObjectCalls.getMethodBind("CameraFeed", "get_datatype", GET_DATATYPE_HASH)
        }

        private const val GET_FORMATS_HASH = 3995934104L
        private val getFormatsBind by lazy {
            ObjectCalls.getMethodBind("CameraFeed", "get_formats", GET_FORMATS_HASH)
        }

        private const val SET_FORMAT_HASH = 31872775L
        private val setFormatBind by lazy {
            ObjectCalls.getMethodBind("CameraFeed", "set_format", SET_FORMAT_HASH)
        }
    }
}
