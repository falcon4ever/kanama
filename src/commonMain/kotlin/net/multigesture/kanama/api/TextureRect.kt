package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A control that displays a texture.
 *
 * Generated from Godot docs: TextureRect
 */
class TextureRect(handle: GodotHandle) : Control(handle) {
    var texture: Texture2D?
        @JvmName("textureProperty")
        get() = getTexture()
        @JvmName("setTextureProperty")
        set(value) = setTexture(value)

    var expandMode: TextureRect.ExpandMode
        @JvmName("expandModeProperty")
        get() = getExpandMode()
        @JvmName("setExpandModeProperty")
        set(value) = setExpandMode(value)

    var stretchMode: TextureRect.StretchMode
        @JvmName("stretchModeProperty")
        get() = getStretchMode()
        @JvmName("setStretchModeProperty")
        set(value) = setStretchMode(value)

    var flipH: Boolean
        @JvmName("flipHProperty")
        get() = isFlippedH()
        @JvmName("setFlipHProperty")
        set(value) = setFlipH(value)

    var flipV: Boolean
        @JvmName("flipVProperty")
        get() = isFlippedV()
        @JvmName("setFlipVProperty")
        set(value) = setFlipV(value)

    /**
     * The node's `Texture2D` resource.
     *
     * Generated from Godot docs: TextureRect.set_texture
     */
    fun setTexture(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTextureBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The node's `Texture2D` resource.
     *
     * Generated from Godot docs: TextureRect.get_texture
     */
    fun getTexture(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureBind, segment))
    }

    /**
     * Defines how minimum size is determined based on the texture's size.
     *
     * Generated from Godot docs: TextureRect.set_expand_mode
     */
    fun setExpandMode(expandMode: TextureRect.ExpandMode) {
        ObjectCalls.ptrcallWithLongArg(setExpandModeBind, segment, expandMode.value)
    }

    /**
     * Defines how minimum size is determined based on the texture's size.
     *
     * Generated from Godot docs: TextureRect.get_expand_mode
     */
    fun getExpandMode(): TextureRect.ExpandMode {
        return TextureRect.ExpandMode(ObjectCalls.ptrcallNoArgsRetLong(getExpandModeBind, segment))
    }

    /**
     * If `true`, texture is flipped horizontally.
     *
     * Generated from Godot docs: TextureRect.set_flip_h
     */
    fun setFlipH(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipHBind, segment, enable)
    }

    /**
     * If `true`, texture is flipped horizontally.
     *
     * Generated from Godot docs: TextureRect.is_flipped_h
     */
    fun isFlippedH(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFlippedHBind, segment)
    }

    /**
     * If `true`, texture is flipped vertically.
     *
     * Generated from Godot docs: TextureRect.set_flip_v
     */
    fun setFlipV(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlipVBind, segment, enable)
    }

    /**
     * If `true`, texture is flipped vertically.
     *
     * Generated from Godot docs: TextureRect.is_flipped_v
     */
    fun isFlippedV(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFlippedVBind, segment)
    }

    /**
     * Controls the texture's behavior when resizing the node's bounding rectangle.
     *
     * Generated from Godot docs: TextureRect.set_stretch_mode
     */
    fun setStretchMode(stretchMode: TextureRect.StretchMode) {
        ObjectCalls.ptrcallWithLongArg(setStretchModeBind, segment, stretchMode.value)
    }

    /**
     * Controls the texture's behavior when resizing the node's bounding rectangle.
     *
     * Generated from Godot docs: TextureRect.get_stretch_mode
     */
    fun getStretchMode(): TextureRect.StretchMode {
        return TextureRect.StretchMode(ObjectCalls.ptrcallNoArgsRetLong(getStretchModeBind, segment))
    }

    /**
     * Godot's `TextureRect.ExpandMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`TextureRect.ExpandMode.<NAME>`).
     *
     * Generated from Godot docs: TextureRect.ExpandMode
     */
    @JvmInline
    value class ExpandMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The minimum size will be equal to texture size, i.e. `TextureRect` can't be smaller than the
             * texture.
             *
             * Generated from Godot docs: TextureRect.EXPAND_KEEP_SIZE
             */
            val KEEP_SIZE: ExpandMode get() = ExpandMode(0L)
            /**
             * The size of the texture won't be considered for minimum size calculation, so the `TextureRect`
             * can be shrunk down past the texture size.
             *
             * Generated from Godot docs: TextureRect.EXPAND_IGNORE_SIZE
             */
            val IGNORE_SIZE: ExpandMode get() = ExpandMode(1L)
            /**
             * The height of the texture will be ignored. Minimum width will be equal to the current height.
             * Useful for horizontal layouts, e.g. inside `HBoxContainer`.
             *
             * Generated from Godot docs: TextureRect.EXPAND_FIT_WIDTH
             */
            val FIT_WIDTH: ExpandMode get() = ExpandMode(2L)
            /**
             * Same as `ExpandMode.FIT_WIDTH`, but keeps texture's aspect ratio.
             *
             * Generated from Godot docs: TextureRect.EXPAND_FIT_WIDTH_PROPORTIONAL
             */
            val FIT_WIDTH_PROPORTIONAL: ExpandMode get() = ExpandMode(3L)
            /**
             * The width of the texture will be ignored. Minimum height will be equal to the current width.
             * Useful for vertical layouts, e.g. inside `VBoxContainer`.
             *
             * Generated from Godot docs: TextureRect.EXPAND_FIT_HEIGHT
             */
            val FIT_HEIGHT: ExpandMode get() = ExpandMode(4L)
            /**
             * Same as `ExpandMode.FIT_HEIGHT`, but keeps texture's aspect ratio.
             *
             * Generated from Godot docs: TextureRect.EXPAND_FIT_HEIGHT_PROPORTIONAL
             */
            val FIT_HEIGHT_PROPORTIONAL: ExpandMode get() = ExpandMode(5L)
        }
    }

    /**
     * Godot's `TextureRect.StretchMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`TextureRect.StretchMode.<NAME>`).
     *
     * Generated from Godot docs: TextureRect.StretchMode
     */
    @JvmInline
    value class StretchMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Scale to fit the node's bounding rectangle.
             *
             * Generated from Godot docs: TextureRect.STRETCH_SCALE
             */
            val SCALE: StretchMode get() = StretchMode(0L)
            /**
             * Tile inside the node's bounding rectangle. Note: `StretchMode.TILE` mode is not supported for
             * `texture` set to an `AtlasTexture` with non-zero `AtlasTexture.margin`.
             *
             * Generated from Godot docs: TextureRect.STRETCH_TILE
             */
            val TILE: StretchMode get() = StretchMode(1L)
            /**
             * The texture keeps its original size and stays in the bounding rectangle's top-left corner.
             *
             * Generated from Godot docs: TextureRect.STRETCH_KEEP
             */
            val KEEP: StretchMode get() = StretchMode(2L)
            /**
             * The texture keeps its original size and stays centered in the node's bounding rectangle.
             *
             * Generated from Godot docs: TextureRect.STRETCH_KEEP_CENTERED
             */
            val KEEP_CENTERED: StretchMode get() = StretchMode(3L)
            /**
             * Scale the texture to fit the node's bounding rectangle, but maintain the texture's aspect ratio.
             *
             * Generated from Godot docs: TextureRect.STRETCH_KEEP_ASPECT
             */
            val KEEP_ASPECT: StretchMode get() = StretchMode(4L)
            /**
             * Scale the texture to fit the node's bounding rectangle, center it and maintain its aspect ratio.
             *
             * Generated from Godot docs: TextureRect.STRETCH_KEEP_ASPECT_CENTERED
             */
            val KEEP_ASPECT_CENTERED: StretchMode get() = StretchMode(5L)
            /**
             * Scale the texture so that the shorter side fits the bounding rectangle. The other side clips to
             * the node's limits.
             *
             * Generated from Godot docs: TextureRect.STRETCH_KEEP_ASPECT_COVERED
             */
            val KEEP_ASPECT_COVERED: StretchMode get() = StretchMode(6L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TextureRect? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): TextureRect? =
            if (handle.address() == 0L) null else TextureRect(GodotHandle(handle))

        private const val SET_TEXTURE_HASH = 4051416890L
        private val setTextureBind by lazy {
            ObjectCalls.getMethodBind("TextureRect", "set_texture", SET_TEXTURE_HASH)
        }

        private const val GET_TEXTURE_HASH = 3635182373L
        private val getTextureBind by lazy {
            ObjectCalls.getMethodBind("TextureRect", "get_texture", GET_TEXTURE_HASH)
        }

        private const val SET_EXPAND_MODE_HASH = 1870766882L
        private val setExpandModeBind by lazy {
            ObjectCalls.getMethodBind("TextureRect", "set_expand_mode", SET_EXPAND_MODE_HASH)
        }

        private const val GET_EXPAND_MODE_HASH = 3863824733L
        private val getExpandModeBind by lazy {
            ObjectCalls.getMethodBind("TextureRect", "get_expand_mode", GET_EXPAND_MODE_HASH)
        }

        private const val SET_FLIP_H_HASH = 2586408642L
        private val setFlipHBind by lazy {
            ObjectCalls.getMethodBind("TextureRect", "set_flip_h", SET_FLIP_H_HASH)
        }

        private const val IS_FLIPPED_H_HASH = 36873697L
        private val isFlippedHBind by lazy {
            ObjectCalls.getMethodBind("TextureRect", "is_flipped_h", IS_FLIPPED_H_HASH)
        }

        private const val SET_FLIP_V_HASH = 2586408642L
        private val setFlipVBind by lazy {
            ObjectCalls.getMethodBind("TextureRect", "set_flip_v", SET_FLIP_V_HASH)
        }

        private const val IS_FLIPPED_V_HASH = 36873697L
        private val isFlippedVBind by lazy {
            ObjectCalls.getMethodBind("TextureRect", "is_flipped_v", IS_FLIPPED_V_HASH)
        }

        private const val SET_STRETCH_MODE_HASH = 58788729L
        private val setStretchModeBind by lazy {
            ObjectCalls.getMethodBind("TextureRect", "set_stretch_mode", SET_STRETCH_MODE_HASH)
        }

        private const val GET_STRETCH_MODE_HASH = 346396079L
        private val getStretchModeBind by lazy {
            ObjectCalls.getMethodBind("TextureRect", "get_stretch_mode", GET_STRETCH_MODE_HASH)
        }
    }
}
