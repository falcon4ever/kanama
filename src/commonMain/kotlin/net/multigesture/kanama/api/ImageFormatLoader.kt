package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class to add support for specific image formats.
 *
 * Generated from Godot docs: ImageFormatLoader
 */
open class ImageFormatLoader(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    /**
     * Godot's `ImageFormatLoader.LoaderFlags` bitfield as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`ImageFormatLoader.LoaderFlags.<NAME>`).
     *
     * Generated from Godot docs: ImageFormatLoader.LoaderFlags
     */
    @JvmInline
    value class LoaderFlags(override val value: Long) : GodotEnumValue {
        infix fun or(other: LoaderFlags): LoaderFlags = LoaderFlags(value or other.value)

        infix fun and(other: LoaderFlags): LoaderFlags = LoaderFlags(value and other.value)

        infix fun xor(other: LoaderFlags): LoaderFlags = LoaderFlags(value xor other.value)

        fun inv(): LoaderFlags = LoaderFlags(value.inv())

        operator fun contains(other: LoaderFlags): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * Default loading behavior. No processing is applied to the image.
             *
             * Generated from Godot docs: ImageFormatLoader.FLAG_NONE
             */
            val NONE: LoaderFlags get() = LoaderFlags(0L)
            /**
             * If set, the image is converted from sRGB to linear encoding.
             *
             * Generated from Godot docs: ImageFormatLoader.FLAG_FORCE_LINEAR
             */
            val FORCE_LINEAR: LoaderFlags get() = LoaderFlags(1L)
            /**
             * If set, a predefined color map is applied to the image. Used when
             * `ResourceImporterTexture.editor/convert_colors_with_editor_theme` is `true`.
             *
             * Generated from Godot docs: ImageFormatLoader.FLAG_CONVERT_COLORS
             */
            val CONVERT_COLORS: LoaderFlags get() = LoaderFlags(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ImageFormatLoader? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ImageFormatLoader? =
            if (handle.address() == 0L) null else ImageFormatLoader(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
