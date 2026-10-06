package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for 3-dimensional textures.
 *
 * Generated from Godot docs: Texture3D
 */
open class Texture3D(handle: GodotHandle) : Texture(handle) {
    /**
     * Returns the current format being used by this texture.
     *
     * Generated from Godot docs: Texture3D.get_format
     */
    fun getFormat(): Image.Format {
        checkOpen()
        return Image.Format(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFormatBind, segment))
    }

    /**
     * Returns the `Texture3D`'s width in pixels. Width is typically represented by the X axis.
     *
     * Generated from Godot docs: Texture3D.get_width
     */
    fun getWidth(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getWidthBind, segment)
    }

    /**
     * Returns the `Texture3D`'s height in pixels. Width is typically represented by the Y axis.
     *
     * Generated from Godot docs: Texture3D.get_height
     */
    fun getHeight(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getHeightBind, segment)
    }

    /**
     * Returns the `Texture3D`'s depth in pixels. Depth is typically represented by the Z axis (a
     * dimension not present in `Texture2D`).
     *
     * Generated from Godot docs: Texture3D.get_depth
     */
    fun getDepth(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getDepthBind, segment)
    }

    /**
     * Returns `true` if the `Texture3D` has generated mipmaps.
     *
     * Generated from Godot docs: Texture3D.has_mipmaps
     */
    fun hasMipmaps(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasMipmapsBind, segment)
    }

    /**
     * Returns the `Texture3D`'s data as an array of `Image`s. Each `Image` represents a slice of the
     * `Texture3D`, with different slices mapping to different depth (Z axis) levels.
     *
     * Generated from Godot docs: Texture3D.get_data
     */
    fun getData(): List<Image> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getDataBind, segment, Image::wrapBorrowed)
    }

    /**
     * Creates a placeholder version of this resource (`PlaceholderTexture3D`).
     *
     * Generated from Godot docs: Texture3D.create_placeholder
     */
    fun createPlaceholder(): Resource? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(Binds.createPlaceholderBind, segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Resource.wrapOwned(ret)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Texture3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Texture3D? =
            if (handle.address() == 0L) null else RefCounted.owned(Texture3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Texture3D? =
            if (handle.address() == 0L) null else Texture3D(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_FORMAT_HASH = 3847873762L
        @JvmField
        val getFormatBind =
            ObjectCalls.getMethodBind("Texture3D", "get_format", GET_FORMAT_HASH)

        private const val GET_WIDTH_HASH = 3905245786L
        @JvmField
        val getWidthBind =
            ObjectCalls.getMethodBind("Texture3D", "get_width", GET_WIDTH_HASH)

        private const val GET_HEIGHT_HASH = 3905245786L
        @JvmField
        val getHeightBind =
            ObjectCalls.getMethodBind("Texture3D", "get_height", GET_HEIGHT_HASH)

        private const val GET_DEPTH_HASH = 3905245786L
        @JvmField
        val getDepthBind =
            ObjectCalls.getMethodBind("Texture3D", "get_depth", GET_DEPTH_HASH)

        private const val HAS_MIPMAPS_HASH = 36873697L
        @JvmField
        val hasMipmapsBind =
            ObjectCalls.getMethodBind("Texture3D", "has_mipmaps", HAS_MIPMAPS_HASH)

        private const val GET_DATA_HASH = 3995934104L
        @JvmField
        val getDataBind =
            ObjectCalls.getMethodBind("Texture3D", "get_data", GET_DATA_HASH)

        private const val CREATE_PLACEHOLDER_HASH = 121922552L
        @JvmField
        val createPlaceholderBind =
            ObjectCalls.getMethodBind("Texture3D", "create_placeholder", CREATE_PLACEHOLDER_HASH)
    }
}
