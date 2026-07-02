package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Base class for 3-dimensional textures.
 *
 * Generated from Godot docs: Texture3D
 */
open class Texture3D(handle: MemorySegment) : Texture(handle) {
    fun getFormat(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFormatBind, handle)
    }

    fun getWidth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getWidthBind, handle)
    }

    fun getHeight(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getHeightBind, handle)
    }

    fun getDepth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDepthBind, handle)
    }

    fun hasMipmaps(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasMipmapsBind, handle)
    }

    fun getData(): List<Image> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getDataBind, handle, Image::fromHandle)
    }

    fun createPlaceholder(): Resource? {
        return Resource.wrap(ObjectCalls.ptrcallNoArgsRetObject(createPlaceholderBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Texture3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Texture3D? =
            if (handle.address() == 0L) null else Texture3D(handle)

        private const val GET_FORMAT_HASH = 3847873762L
        private val getFormatBind by lazy {
            ObjectCalls.getMethodBind("Texture3D", "get_format", GET_FORMAT_HASH)
        }

        private const val GET_WIDTH_HASH = 3905245786L
        private val getWidthBind by lazy {
            ObjectCalls.getMethodBind("Texture3D", "get_width", GET_WIDTH_HASH)
        }

        private const val GET_HEIGHT_HASH = 3905245786L
        private val getHeightBind by lazy {
            ObjectCalls.getMethodBind("Texture3D", "get_height", GET_HEIGHT_HASH)
        }

        private const val GET_DEPTH_HASH = 3905245786L
        private val getDepthBind by lazy {
            ObjectCalls.getMethodBind("Texture3D", "get_depth", GET_DEPTH_HASH)
        }

        private const val HAS_MIPMAPS_HASH = 36873697L
        private val hasMipmapsBind by lazy {
            ObjectCalls.getMethodBind("Texture3D", "has_mipmaps", HAS_MIPMAPS_HASH)
        }

        private const val GET_DATA_HASH = 3995934104L
        private val getDataBind by lazy {
            ObjectCalls.getMethodBind("Texture3D", "get_data", GET_DATA_HASH)
        }

        private const val CREATE_PLACEHOLDER_HASH = 121922552L
        private val createPlaceholderBind by lazy {
            ObjectCalls.getMethodBind("Texture3D", "create_placeholder", CREATE_PLACEHOLDER_HASH)
        }
    }
}
