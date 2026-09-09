package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2i

/**
 * Generated from Godot docs: BitMap
 */
class BitMap(handle: MemorySegment) : Resource(handle) {
    fun create(size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iArg(createBind, handle, size)
    }

    fun createFromImageAlpha(image: Image?, threshold: Double = 0.1) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectAndDoubleArg(createFromImageAlphaBind, handle, image?.requireOpenHandle() ?: MemorySegment.NULL, threshold)
    }

    fun setBitv(position: Vector2i, bit: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iAndBoolArg(setBitvBind, handle, position, bit)
    }

    fun setBit(x: Int, y: Int, bit: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndBoolArgs(setBitBind, handle, x, y, bit)
    }

    fun getBitv(position: Vector2i): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithVector2iArgRetBool(getBitvBind, handle, position)
    }

    fun getBit(x: Int, y: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetBool(getBitBind, handle, x, y)
    }

    fun getTrueBitCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getTrueBitCountBind, handle)
    }

    fun getSize(): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2i(getSizeBind, handle)
    }

    fun resize(newSize: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iArg(resizeBind, handle, newSize)
    }

    fun convertToImage(): Image? {
        checkOpen()
        return Image.wrap(ObjectCalls.ptrcallNoArgsRetObject(convertToImageBind, handle))
    }

    companion object {
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

        private const val CONVERT_TO_IMAGE_HASH = 4190603485L
        private val convertToImageBind by lazy {
            ObjectCalls.getMethodBind("BitMap", "convert_to_image", CONVERT_TO_IMAGE_HASH)
        }
    }
}
