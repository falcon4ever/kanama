package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Texture with 3 dimensions.
 *
 * Generated from Godot docs: ImageTexture3D
 */
class ImageTexture3D(handle: GodotHandle) : Texture3D(handle) {
    /**
     * Creates the `ImageTexture3D` with specified `format`, `width`, `height`, and `depth`. If
     * `use_mipmaps` is `true`, generates mipmaps for the `ImageTexture3D`.
     *
     * Generated from Godot docs: ImageTexture3D.create
     */
    fun create(format: Image.Format, width: Int, height: Int, depth: Int, useMipmaps: Boolean, data: List<Image>): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithLongThreeIntBoolObjectListArgsRetLong(Binds.createBind, segment, format.value, width, height, depth, useMipmaps, data))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Replaces the texture's existing data with the layers specified in `data`. The size of `data`
     * must match the parameters that were used for `create`. In other words, the texture cannot be
     * resized or have its format changed by calling `update`.
     *
     * Generated from Godot docs: ImageTexture3D.update
     */
    fun update(data: List<Image>) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectListArg(Binds.updateBind, segment, data)
        ObjectCalls.keepReachable(this)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ImageTexture3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ImageTexture3D? =
            if (handle.address() == 0L) null else RefCounted.owned(ImageTexture3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ImageTexture3D? =
            if (handle.address() == 0L) null else ImageTexture3D(GodotHandle(handle))

        /** A new ImageTexture3D, GDScript's `ImageTexture3D.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): ImageTexture3D =
            RefCounted.owned(ImageTexture3D(GodotHandle(requireConstructed(ObjectCalls.constructObject("ImageTexture3D"), "ImageTexture3D"))))
    }

    private object Binds {
        private const val CREATE_HASH = 1130379827L
        @JvmField
        val createBind =
            ObjectCalls.getMethodBind("ImageTexture3D", "create", CREATE_HASH)

        private const val UPDATE_HASH = 381264803L
        @JvmField
        val updateBind =
            ObjectCalls.getMethodBind("ImageTexture3D", "update", UPDATE_HASH)
    }
}
