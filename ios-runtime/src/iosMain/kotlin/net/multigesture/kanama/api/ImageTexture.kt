package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2i

/**
 * Generated from Godot docs: ImageTexture
 */
class ImageTexture(handle: MemorySegment) : Texture2D(handle) {
    fun setImage(image: Image?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(setImageBind, handle, listOf(image?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun update(image: Image?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(updateBind, handle, listOf(image?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun setSizeOverride(size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iArg(setSizeOverrideBind, handle, size)
    }

    companion object {
        // KANAMA-IOS-SUGAR: [runtime] create_from_image is STATIC — the instance dispatch
        // rejects a NULL instance, so it must ride ptrcallStatic* (the previous
        // ptrcallWithObjectArgRetObject(bind, NULL, ...) form silently returned null).
        fun createFromImage(image: Image?): ImageTexture? {
            return ImageTexture.wrap(ObjectCalls.ptrcallStaticWithObjectArgRetObject(createFromImageBind, image?.requireOpenHandle() ?: MemorySegment.NULL))
        }

        fun fromHandle(handle: MemorySegment): ImageTexture? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ImageTexture? =
            if (handle.address() == 0L) null else ImageTexture(handle)

        private const val CREATE_FROM_IMAGE_HASH = 2775144163L
        private val createFromImageBind by lazy {
            ObjectCalls.getMethodBind("ImageTexture", "create_from_image", CREATE_FROM_IMAGE_HASH)
        }

        private const val SET_IMAGE_HASH = 532598488L
        private val setImageBind by lazy {
            ObjectCalls.getMethodBind("ImageTexture", "set_image", SET_IMAGE_HASH)
        }

        private const val UPDATE_HASH = 532598488L
        private val updateBind by lazy {
            ObjectCalls.getMethodBind("ImageTexture", "update", UPDATE_HASH)
        }

        private const val SET_SIZE_OVERRIDE_HASH = 1130785943L
        private val setSizeOverrideBind by lazy {
            ObjectCalls.getMethodBind("ImageTexture", "set_size_override", SET_SIZE_OVERRIDE_HASH)
        }
    }
}
