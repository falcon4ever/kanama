package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/**
 * Boolean matrix.
 *
 * Generated from Godot docs: BitMap
 */
class BitMap(handle: MemorySegment) : Resource(handle) {
    /**
     * Creates a bitmap with the specified size, filled with `false`.
     *
     * Generated from Godot docs: BitMap.create
     */
    fun create(size: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(createBind, handle, size)
    }

    fun createFromImageAlpha(image: Image?, threshold: Double = 0.1) {
        ObjectCalls.ptrcallWithObjectAndDoubleArg(createFromImageAlphaBind, handle, image?.requireOpenHandle() ?: MemorySegment.NULL, threshold)
    }

    fun setBitv(position: Vector2i, bit: Boolean) {
        ObjectCalls.ptrcallWithVector2iAndBoolArg(setBitvBind, handle, position, bit)
    }

    fun setBit(x: Int, y: Int, bit: Boolean) {
        ObjectCalls.ptrcallWithTwoIntAndBoolArgs(setBitBind, handle, x, y, bit)
    }

    fun getBitv(position: Vector2i): Boolean {
        return ObjectCalls.ptrcallWithVector2iArgRetBool(getBitvBind, handle, position)
    }

    fun getBit(x: Int, y: Int): Boolean {
        return ObjectCalls.ptrcallWithTwoIntArgsRetBool(getBitBind, handle, x, y)
    }

    fun setBitRect(rect: Rect2i, bit: Boolean) {
        ObjectCalls.ptrcallWithRect2iAndBoolArg(setBitRectBind, handle, rect, bit)
    }

    fun getTrueBitCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTrueBitCountBind, handle)
    }

    fun getSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getSizeBind, handle)
    }

    /**
     * Resizes the image to `new_size`.
     *
     * Generated from Godot docs: BitMap.resize
     */
    fun resize(newSize: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(resizeBind, handle, newSize)
    }

    fun growMask(pixels: Int, rect: Rect2i) {
        ObjectCalls.ptrcallWithIntAndRect2iArg(growMaskBind, handle, pixels, rect)
    }

    fun convertToImage(): Image? {
        return Image.wrap(ObjectCalls.ptrcallNoArgsRetObject(convertToImageBind, handle))
    }

    fun opaqueToPolygons(rect: Rect2i, epsilon: Double = 2.0): List<List<Vector2>> {
        return ObjectCalls.ptrcallWithRect2iAndDoubleArgsRetPackedVector2ListList(opaqueToPolygonsBind, handle, rect, epsilon)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): BitMap? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): BitMap? =
            if (handle.address() == 0L) null else BitMap(handle)

        private const val CREATE_HASH = 1130785943L
        private val createBind by lazy {
            ObjectCalls.getMethodBind("BitMap", "create", CREATE_HASH)
        }

        private const val CREATE_FROM_IMAGE_ALPHA_HASH = 106271684L
        private val createFromImageAlphaBind by lazy {
            ObjectCalls.getMethodBind("BitMap", "create_from_image_alpha", CREATE_FROM_IMAGE_ALPHA_HASH)
        }

        private const val SET_BITV_HASH = 4153096796L
        private val setBitvBind by lazy {
            ObjectCalls.getMethodBind("BitMap", "set_bitv", SET_BITV_HASH)
        }

        private const val SET_BIT_HASH = 1383440665L
        private val setBitBind by lazy {
            ObjectCalls.getMethodBind("BitMap", "set_bit", SET_BIT_HASH)
        }

        private const val GET_BITV_HASH = 3900751641L
        private val getBitvBind by lazy {
            ObjectCalls.getMethodBind("BitMap", "get_bitv", GET_BITV_HASH)
        }

        private const val GET_BIT_HASH = 2522259332L
        private val getBitBind by lazy {
            ObjectCalls.getMethodBind("BitMap", "get_bit", GET_BIT_HASH)
        }

        private const val SET_BIT_RECT_HASH = 472162941L
        private val setBitRectBind by lazy {
            ObjectCalls.getMethodBind("BitMap", "set_bit_rect", SET_BIT_RECT_HASH)
        }

        private const val GET_TRUE_BIT_COUNT_HASH = 3905245786L
        private val getTrueBitCountBind by lazy {
            ObjectCalls.getMethodBind("BitMap", "get_true_bit_count", GET_TRUE_BIT_COUNT_HASH)
        }

        private const val GET_SIZE_HASH = 3690982128L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("BitMap", "get_size", GET_SIZE_HASH)
        }

        private const val RESIZE_HASH = 1130785943L
        private val resizeBind by lazy {
            ObjectCalls.getMethodBind("BitMap", "resize", RESIZE_HASH)
        }

        private const val GROW_MASK_HASH = 3317281434L
        private val growMaskBind by lazy {
            ObjectCalls.getMethodBind("BitMap", "grow_mask", GROW_MASK_HASH)
        }

        private const val CONVERT_TO_IMAGE_HASH = 4190603485L
        private val convertToImageBind by lazy {
            ObjectCalls.getMethodBind("BitMap", "convert_to_image", CONVERT_TO_IMAGE_HASH)
        }

        private const val OPAQUE_TO_POLYGONS_HASH = 48478126L
        private val opaqueToPolygonsBind by lazy {
            ObjectCalls.getMethodBind("BitMap", "opaque_to_polygons", OPAQUE_TO_POLYGONS_HASH)
        }
    }
}
