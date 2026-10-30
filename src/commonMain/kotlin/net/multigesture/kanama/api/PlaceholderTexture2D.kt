package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
import net.multigesture.kanama.types.Vector2

/**
 * Placeholder class for a 2-dimensional texture.
 *
 * Generated from Godot docs: PlaceholderTexture2D
 */
class PlaceholderTexture2D(handle: GodotHandle) : Texture2D(handle) {
    /**
     * The texture's size (in pixels).
     *
     * Generated from Godot docs: PlaceholderTexture2D.set_size
     */
    fun setSize(size: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(Binds.setSizeBind, segment, size)
        ObjectCalls.keepReachable(this)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PlaceholderTexture2D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PlaceholderTexture2D? =
            if (handle.address() == 0L) null else RefCounted.owned(PlaceholderTexture2D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PlaceholderTexture2D? =
            if (handle.address() == 0L) null else PlaceholderTexture2D(GodotHandle(handle))

        /** A new PlaceholderTexture2D, GDScript's `PlaceholderTexture2D.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): PlaceholderTexture2D =
            RefCounted.owned(PlaceholderTexture2D(GodotHandle(requireConstructed(ObjectCalls.constructObject("PlaceholderTexture2D"), "PlaceholderTexture2D"))))
    }

    private object Binds {
        private const val SET_SIZE_HASH = 743155724L
        @JvmField
        val setSizeBind =
            ObjectCalls.getMethodBind("PlaceholderTexture2D", "set_size", SET_SIZE_HASH)
    }
}
