package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * Abstract base class for noise generators.
 *
 * Generated from Godot docs: Noise
 */
open class Noise(handle: GodotHandle) : Resource(handle) {
    /**
     * Returns the 1D noise value at the given (x) coordinate.
     *
     * Generated from Godot docs: Noise.get_noise_1d
     */
    fun getNoise1d(x: Double): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithFloatArgRetFloat(Binds.getNoise1dBind, segment, x)
    }

    /**
     * Returns the 2D noise value at the given position.
     *
     * Generated from Godot docs: Noise.get_noise_2d
     */
    fun getNoise2d(x: Double, y: Double): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoDoubleArgsRetDouble(Binds.getNoise2dBind, segment, x, y)
    }

    /**
     * Returns the 2D noise value at the given position.
     *
     * Generated from Godot docs: Noise.get_noise_2dv
     */
    fun getNoise2dv(v: Vector2): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithVector2ArgRetDouble(Binds.getNoise2dvBind, segment, v)
    }

    /**
     * Returns the 3D noise value at the given position.
     *
     * Generated from Godot docs: Noise.get_noise_3d
     */
    fun getNoise3d(x: Double, y: Double, z: Double): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithThreeDoubleArgsRetDouble(Binds.getNoise3dBind, segment, x, y, z)
    }

    /**
     * Returns the 3D noise value at the given position.
     *
     * Generated from Godot docs: Noise.get_noise_3dv
     */
    fun getNoise3dv(v: Vector3): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithVector3ArgRetDouble(Binds.getNoise3dvBind, segment, v)
    }

    /**
     * Returns an `Image` containing 2D noise values. Note: With `normalize` set to `false`, the
     * default implementation expects the noise generator to return values in the range `-1.0` to
     * `1.0`.
     *
     * Generated from Godot docs: Noise.get_image
     */
    fun getImage(width: Int, height: Int, invert: Boolean = false, in3dSpace: Boolean = false, normalize: Boolean = true): Image? {
        checkOpen()
        return Image.wrapOwned(ObjectCalls.ptrcallWithTwoIntAndThreeBoolArgsRetObject(Binds.getImageBind, segment, width, height, invert, in3dSpace, normalize))
    }

    /**
     * Returns an `Image` containing seamless 2D noise values. Note: With `normalize` set to `false`,
     * the default implementation expects the noise generator to return values in the range `-1.0` to
     * `1.0`.
     *
     * Generated from Godot docs: Noise.get_seamless_image
     */
    fun getSeamlessImage(width: Int, height: Int, invert: Boolean = false, in3dSpace: Boolean = false, skirt: Double = 0.1, normalize: Boolean = true): Image? {
        checkOpen()
        return Image.wrapOwned(ObjectCalls.ptrcallWithTwoIntTwoBoolDoubleBoolArgsRetObject(Binds.getSeamlessImageBind, segment, width, height, invert, in3dSpace, skirt, normalize))
    }

    /**
     * Returns an `Array` of `Image`s containing 3D noise values for use with `ImageTexture3D.create`.
     * Note: With `normalize` set to `false`, the default implementation expects the noise generator to
     * return values in the range `-1.0` to `1.0`.
     *
     * Generated from Godot docs: Noise.get_image_3d
     */
    fun getImage3d(width: Int, height: Int, depth: Int, invert: Boolean = false, normalize: Boolean = true): List<Image> {
        checkOpen()
        return ObjectCalls.ptrcallWithThreeIntTwoBoolArgsRetTypedObjectList(Binds.getImage3dBind, segment, width, height, depth, invert, normalize, Image::wrapBorrowed)
    }

    /**
     * Returns an `Array` of `Image`s containing seamless 3D noise values for use with
     * `ImageTexture3D.create`. Note: With `normalize` set to `false`, the default implementation
     * expects the noise generator to return values in the range `-1.0` to `1.0`.
     *
     * Generated from Godot docs: Noise.get_seamless_image_3d
     */
    fun getSeamlessImage3d(width: Int, height: Int, depth: Int, invert: Boolean = false, skirt: Double = 0.1, normalize: Boolean = true): List<Image> {
        checkOpen()
        return ObjectCalls.ptrcallWithThreeIntBoolDoubleBoolArgsRetTypedObjectList(Binds.getSeamlessImage3dBind, segment, width, height, depth, invert, skirt, normalize, Image::wrapBorrowed)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Noise? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Noise? =
            if (handle.address() == 0L) null else RefCounted.owned(Noise(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Noise? =
            if (handle.address() == 0L) null else Noise(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_NOISE_1D_HASH = 3919130443L
        @JvmField
        val getNoise1dBind =
            ObjectCalls.getMethodBind("Noise", "get_noise_1d", GET_NOISE_1D_HASH)

        private const val GET_NOISE_2D_HASH = 2753205203L
        @JvmField
        val getNoise2dBind =
            ObjectCalls.getMethodBind("Noise", "get_noise_2d", GET_NOISE_2D_HASH)

        private const val GET_NOISE_2DV_HASH = 2276447920L
        @JvmField
        val getNoise2dvBind =
            ObjectCalls.getMethodBind("Noise", "get_noise_2dv", GET_NOISE_2DV_HASH)

        private const val GET_NOISE_3D_HASH = 973811851L
        @JvmField
        val getNoise3dBind =
            ObjectCalls.getMethodBind("Noise", "get_noise_3d", GET_NOISE_3D_HASH)

        private const val GET_NOISE_3DV_HASH = 1109078154L
        @JvmField
        val getNoise3dvBind =
            ObjectCalls.getMethodBind("Noise", "get_noise_3dv", GET_NOISE_3DV_HASH)

        private const val GET_IMAGE_HASH = 3180683109L
        @JvmField
        val getImageBind =
            ObjectCalls.getMethodBind("Noise", "get_image", GET_IMAGE_HASH)

        private const val GET_SEAMLESS_IMAGE_HASH = 2770743602L
        @JvmField
        val getSeamlessImageBind =
            ObjectCalls.getMethodBind("Noise", "get_seamless_image", GET_SEAMLESS_IMAGE_HASH)

        private const val GET_IMAGE_3D_HASH = 3977814329L
        @JvmField
        val getImage3dBind =
            ObjectCalls.getMethodBind("Noise", "get_image_3d", GET_IMAGE_3D_HASH)

        private const val GET_SEAMLESS_IMAGE_3D_HASH = 451006340L
        @JvmField
        val getSeamlessImage3dBind =
            ObjectCalls.getMethodBind("Noise", "get_seamless_image_3d", GET_SEAMLESS_IMAGE_3D_HASH)
    }
}
