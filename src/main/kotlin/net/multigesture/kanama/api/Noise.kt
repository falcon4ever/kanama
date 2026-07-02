package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: Noise
 */
open class Noise(handle: MemorySegment) : Resource(handle) {
    fun getNoise1d(x: Double): Double {
        return ObjectCalls.ptrcallWithFloatArgRetFloat(getNoise1dBind, handle, x)
    }

    fun getNoise2d(x: Double, y: Double): Double {
        return ObjectCalls.ptrcallWithTwoDoubleArgsRetDouble(getNoise2dBind, handle, x, y)
    }

    fun getNoise2dv(v: Vector2): Double {
        return ObjectCalls.ptrcallWithVector2ArgRetDouble(getNoise2dvBind, handle, v)
    }

    fun getNoise3d(x: Double, y: Double, z: Double): Double {
        return ObjectCalls.ptrcallWithThreeDoubleArgsRetDouble(getNoise3dBind, handle, x, y, z)
    }

    fun getNoise3dv(v: Vector3): Double {
        return ObjectCalls.ptrcallWithVector3ArgRetDouble(getNoise3dvBind, handle, v)
    }

    fun getImage(width: Int, height: Int, invert: Boolean = false, in3dSpace: Boolean = false, normalize: Boolean = true): Image? {
        return Image.wrap(ObjectCalls.ptrcallWithTwoIntAndThreeBoolArgsRetObject(getImageBind, handle, width, height, invert, in3dSpace, normalize))
    }

    fun getSeamlessImage(width: Int, height: Int, invert: Boolean = false, in3dSpace: Boolean = false, skirt: Double = 0.1, normalize: Boolean = true): Image? {
        return Image.wrap(ObjectCalls.ptrcallWithTwoIntTwoBoolDoubleBoolArgsRetObject(getSeamlessImageBind, handle, width, height, invert, in3dSpace, skirt, normalize))
    }

    fun getImage3d(width: Int, height: Int, depth: Int, invert: Boolean = false, normalize: Boolean = true): List<Image> {
        return ObjectCalls.ptrcallWithThreeIntTwoBoolArgsRetTypedObjectList(getImage3dBind, handle, width, height, depth, invert, normalize, Image::fromHandle)
    }

    fun getSeamlessImage3d(width: Int, height: Int, depth: Int, invert: Boolean = false, skirt: Double = 0.1, normalize: Boolean = true): List<Image> {
        return ObjectCalls.ptrcallWithThreeIntBoolDoubleBoolArgsRetTypedObjectList(getSeamlessImage3dBind, handle, width, height, depth, invert, skirt, normalize, Image::fromHandle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Noise? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Noise? =
            if (handle.address() == 0L) null else Noise(handle)

        private const val GET_NOISE_1D_HASH = 3919130443L
        private val getNoise1dBind by lazy {
            ObjectCalls.getMethodBind("Noise", "get_noise_1d", GET_NOISE_1D_HASH)
        }

        private const val GET_NOISE_2D_HASH = 2753205203L
        private val getNoise2dBind by lazy {
            ObjectCalls.getMethodBind("Noise", "get_noise_2d", GET_NOISE_2D_HASH)
        }

        private const val GET_NOISE_2DV_HASH = 2276447920L
        private val getNoise2dvBind by lazy {
            ObjectCalls.getMethodBind("Noise", "get_noise_2dv", GET_NOISE_2DV_HASH)
        }

        private const val GET_NOISE_3D_HASH = 973811851L
        private val getNoise3dBind by lazy {
            ObjectCalls.getMethodBind("Noise", "get_noise_3d", GET_NOISE_3D_HASH)
        }

        private const val GET_NOISE_3DV_HASH = 1109078154L
        private val getNoise3dvBind by lazy {
            ObjectCalls.getMethodBind("Noise", "get_noise_3dv", GET_NOISE_3DV_HASH)
        }

        private const val GET_IMAGE_HASH = 3180683109L
        private val getImageBind by lazy {
            ObjectCalls.getMethodBind("Noise", "get_image", GET_IMAGE_HASH)
        }

        private const val GET_SEAMLESS_IMAGE_HASH = 2770743602L
        private val getSeamlessImageBind by lazy {
            ObjectCalls.getMethodBind("Noise", "get_seamless_image", GET_SEAMLESS_IMAGE_HASH)
        }

        private const val GET_IMAGE_3D_HASH = 3977814329L
        private val getImage3dBind by lazy {
            ObjectCalls.getMethodBind("Noise", "get_image_3d", GET_IMAGE_3D_HASH)
        }

        private const val GET_SEAMLESS_IMAGE_3D_HASH = 451006340L
        private val getSeamlessImage3dBind by lazy {
            ObjectCalls.getMethodBind("Noise", "get_seamless_image_3d", GET_SEAMLESS_IMAGE_3D_HASH)
        }
    }
}
