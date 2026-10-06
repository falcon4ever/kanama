package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/**
 * Boolean matrix.
 *
 * Generated from Godot docs: BitMap
 */
class BitMap(handle: GodotHandle) : Resource(handle) {
    /**
     * Creates a bitmap with the specified size, filled with `false`.
     *
     * Generated from Godot docs: BitMap.create
     */
    fun create(size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iArg(Binds.createBind, segment, size)
    }

    /**
     * Creates a bitmap that matches the given image dimensions, every element of the bitmap is set to
     * `false` if the alpha value of the image at that position is equal to `threshold` or less, and
     * `true` in other case.
     *
     * Generated from Godot docs: BitMap.create_from_image_alpha
     */
    fun createFromImageAlpha(image: Image?, threshold: Double = 0.1) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectAndDoubleArg(Binds.createFromImageAlphaBind, segment, image?.requireOpenHandle() ?: NULL_SEGMENT, threshold)
    }

    /**
     * Sets the bitmap's element at the specified position, to the specified value.
     *
     * Generated from Godot docs: BitMap.set_bitv
     */
    fun setBitv(position: Vector2i, bit: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iAndBoolArg(Binds.setBitvBind, segment, position, bit)
    }

    /**
     * Sets the bitmap's element at the specified position, to the specified value.
     *
     * Generated from Godot docs: BitMap.set_bit
     */
    fun setBit(x: Int, y: Int, bit: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndBoolArgs(Binds.setBitBind, segment, x, y, bit)
    }

    /**
     * Returns bitmap's value at the specified position.
     *
     * Generated from Godot docs: BitMap.get_bitv
     */
    fun getBitv(position: Vector2i): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithVector2iArgRetBool(Binds.getBitvBind, segment, position)
    }

    /**
     * Returns bitmap's value at the specified position.
     *
     * Generated from Godot docs: BitMap.get_bit
     */
    fun getBit(x: Int, y: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetBool(Binds.getBitBind, segment, x, y)
    }

    /**
     * Sets a rectangular portion of the bitmap to the specified value.
     *
     * Generated from Godot docs: BitMap.set_bit_rect
     */
    fun setBitRect(rect: Rect2i, bit: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRect2iAndBoolArg(Binds.setBitRectBind, segment, rect, bit)
    }

    /**
     * Returns the number of bitmap elements that are set to `true`.
     *
     * Generated from Godot docs: BitMap.get_true_bit_count
     */
    fun getTrueBitCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getTrueBitCountBind, segment)
    }

    /**
     * Returns bitmap's dimensions.
     *
     * Generated from Godot docs: BitMap.get_size
     */
    fun getSize(): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2i(Binds.getSizeBind, segment)
    }

    /**
     * Resizes the image to `new_size`.
     *
     * Generated from Godot docs: BitMap.resize
     */
    fun resize(newSize: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iArg(Binds.resizeBind, segment, newSize)
    }

    /**
     * Applies morphological dilation or erosion to the bitmap. If `pixels` is positive, dilation is
     * applied to the bitmap. If `pixels` is negative, erosion is applied to the bitmap. `rect` defines
     * the area where the morphological operation is applied. Pixels located outside the `rect` are
     * unaffected by `grow_mask`.
     *
     * Generated from Godot docs: BitMap.grow_mask
     */
    fun growMask(pixels: Int, rect: Rect2i) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndRect2iArg(Binds.growMaskBind, segment, pixels, rect)
    }

    /**
     * Returns an image of the same size as the bitmap and with an `Image.Format` of type
     * `Image.Format.L8`. `true` bits of the bitmap are being converted into white pixels, and `false`
     * bits into black.
     *
     * Generated from Godot docs: BitMap.convert_to_image
     */
    fun convertToImage(): Image? {
        checkOpen()
        return Image.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.convertToImageBind, segment))
    }

    /**
     * Creates an `Array` of polygons covering a rectangular portion of the bitmap. It uses a marching
     * squares algorithm, followed by Ramer-Douglas-Peucker (RDP) reduction of the number of vertices.
     * Each polygon is described as a `PackedVector2Array` of its vertices.
     *
     * Generated from Godot docs: BitMap.opaque_to_polygons
     */
    fun opaqueToPolygons(rect: Rect2i, epsilon: Double = 2.0): List<List<Vector2>> {
        checkOpen()
        return ObjectCalls.ptrcallWithRect2iAndDoubleArgsRetPackedVector2ListList(Binds.opaqueToPolygonsBind, segment, rect, epsilon)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): BitMap? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): BitMap? =
            if (handle.address() == 0L) null else RefCounted.owned(BitMap(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): BitMap? =
            if (handle.address() == 0L) null else BitMap(GodotHandle(handle))
    }

    private object Binds {
        private const val CREATE_HASH = 1130785943L
        @JvmField
        val createBind =
            ObjectCalls.getMethodBind("BitMap", "create", CREATE_HASH)

        private const val CREATE_FROM_IMAGE_ALPHA_HASH = 106271684L
        @JvmField
        val createFromImageAlphaBind =
            ObjectCalls.getMethodBind("BitMap", "create_from_image_alpha", CREATE_FROM_IMAGE_ALPHA_HASH)

        private const val SET_BITV_HASH = 4153096796L
        @JvmField
        val setBitvBind =
            ObjectCalls.getMethodBind("BitMap", "set_bitv", SET_BITV_HASH)

        private const val SET_BIT_HASH = 1383440665L
        @JvmField
        val setBitBind =
            ObjectCalls.getMethodBind("BitMap", "set_bit", SET_BIT_HASH)

        private const val GET_BITV_HASH = 3900751641L
        @JvmField
        val getBitvBind =
            ObjectCalls.getMethodBind("BitMap", "get_bitv", GET_BITV_HASH)

        private const val GET_BIT_HASH = 2522259332L
        @JvmField
        val getBitBind =
            ObjectCalls.getMethodBind("BitMap", "get_bit", GET_BIT_HASH)

        private const val SET_BIT_RECT_HASH = 472162941L
        @JvmField
        val setBitRectBind =
            ObjectCalls.getMethodBind("BitMap", "set_bit_rect", SET_BIT_RECT_HASH)

        private const val GET_TRUE_BIT_COUNT_HASH = 3905245786L
        @JvmField
        val getTrueBitCountBind =
            ObjectCalls.getMethodBind("BitMap", "get_true_bit_count", GET_TRUE_BIT_COUNT_HASH)

        private const val GET_SIZE_HASH = 3690982128L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("BitMap", "get_size", GET_SIZE_HASH)

        private const val RESIZE_HASH = 1130785943L
        @JvmField
        val resizeBind =
            ObjectCalls.getMethodBind("BitMap", "resize", RESIZE_HASH)

        private const val GROW_MASK_HASH = 3317281434L
        @JvmField
        val growMaskBind =
            ObjectCalls.getMethodBind("BitMap", "grow_mask", GROW_MASK_HASH)

        private const val CONVERT_TO_IMAGE_HASH = 4190603485L
        @JvmField
        val convertToImageBind =
            ObjectCalls.getMethodBind("BitMap", "convert_to_image", CONVERT_TO_IMAGE_HASH)

        private const val OPAQUE_TO_POLYGONS_HASH = 48478126L
        @JvmField
        val opaqueToPolygonsBind =
            ObjectCalls.getMethodBind("BitMap", "opaque_to_polygons", OPAQUE_TO_POLYGONS_HASH)
    }
}
