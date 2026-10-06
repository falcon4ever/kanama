package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Button for touch screen devices for gameplay use.
 *
 * Generated from Godot docs: TouchScreenButton
 */
class TouchScreenButton(handle: GodotHandle) : Node2D(handle) {
    var textureNormal: Texture2D?
        @JvmName("textureNormalProperty")
        get() = getTextureNormal()
        @JvmName("setTextureNormalProperty")
        set(value) = setTextureNormal(value)

    var texturePressed: Texture2D?
        @JvmName("texturePressedProperty")
        get() = getTexturePressed()
        @JvmName("setTexturePressedProperty")
        set(value) = setTexturePressed(value)

    var bitmask: BitMap?
        @JvmName("bitmaskProperty")
        get() = getBitmask()
        @JvmName("setBitmaskProperty")
        set(value) = setBitmask(value)

    var shape: Shape2D?
        @JvmName("shapeProperty")
        get() = getShape()
        @JvmName("setShapeProperty")
        set(value) = setShape(value)

    var shapeCentered: Boolean
        @JvmName("shapeCenteredProperty")
        get() = isShapeCentered()
        @JvmName("setShapeCenteredProperty")
        set(value) = setShapeCentered(value)

    var shapeVisible: Boolean
        @JvmName("shapeVisibleProperty")
        get() = isShapeVisible()
        @JvmName("setShapeVisibleProperty")
        set(value) = setShapeVisible(value)

    var passbyPress: Boolean
        @JvmName("passbyPressProperty")
        get() = isPassbyPressEnabled()
        @JvmName("setPassbyPressProperty")
        set(value) = setPassbyPress(value)

    var action: String
        @JvmName("actionProperty")
        get() = getAction()
        @JvmName("setActionProperty")
        set(value) = setAction(value)

    var visibilityMode: TouchScreenButton.VisibilityMode
        @JvmName("visibilityModeProperty")
        get() = getVisibilityMode()
        @JvmName("setVisibilityModeProperty")
        set(value) = setVisibilityMode(value)

    /**
     * The button's texture for the normal state.
     *
     * Generated from Godot docs: TouchScreenButton.set_texture_normal
     */
    fun setTextureNormal(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setTextureNormalBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The button's texture for the normal state.
     *
     * Generated from Godot docs: TouchScreenButton.get_texture_normal
     */
    fun getTextureNormal(): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getTextureNormalBind, segment))
    }

    /**
     * The button's texture for the pressed state.
     *
     * Generated from Godot docs: TouchScreenButton.set_texture_pressed
     */
    fun setTexturePressed(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setTexturePressedBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The button's texture for the pressed state.
     *
     * Generated from Godot docs: TouchScreenButton.get_texture_pressed
     */
    fun getTexturePressed(): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getTexturePressedBind, segment))
    }

    /**
     * The button's bitmask.
     *
     * Generated from Godot docs: TouchScreenButton.set_bitmask
     */
    fun setBitmask(bitmask: BitMap?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setBitmaskBind, segment, listOf(bitmask?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The button's bitmask.
     *
     * Generated from Godot docs: TouchScreenButton.get_bitmask
     */
    fun getBitmask(): BitMap? {
        return BitMap.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getBitmaskBind, segment))
    }

    /**
     * The button's shape.
     *
     * Generated from Godot docs: TouchScreenButton.set_shape
     */
    fun setShape(shape: Shape2D?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setShapeBind, segment, listOf(shape?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The button's shape.
     *
     * Generated from Godot docs: TouchScreenButton.get_shape
     */
    fun getShape(): Shape2D? {
        return Shape2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getShapeBind, segment))
    }

    /**
     * If `true`, the button's shape is centered in the provided texture. If no texture is used, this
     * property has no effect.
     *
     * Generated from Godot docs: TouchScreenButton.set_shape_centered
     */
    fun setShapeCentered(bool: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setShapeCenteredBind, segment, bool)
    }

    /**
     * If `true`, the button's shape is centered in the provided texture. If no texture is used, this
     * property has no effect.
     *
     * Generated from Godot docs: TouchScreenButton.is_shape_centered
     */
    fun isShapeCentered(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isShapeCenteredBind, segment)
    }

    /**
     * If `true`, the button's shape is visible in the editor.
     *
     * Generated from Godot docs: TouchScreenButton.set_shape_visible
     */
    fun setShapeVisible(bool: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setShapeVisibleBind, segment, bool)
    }

    /**
     * If `true`, the button's shape is visible in the editor.
     *
     * Generated from Godot docs: TouchScreenButton.is_shape_visible
     */
    fun isShapeVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isShapeVisibleBind, segment)
    }

    /**
     * The button's action. Actions can be handled with `InputEventAction`.
     *
     * Generated from Godot docs: TouchScreenButton.set_action
     */
    fun setAction(action: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setActionBind, segment, action)
    }

    /**
     * The button's action. Actions can be handled with `InputEventAction`.
     *
     * Generated from Godot docs: TouchScreenButton.get_action
     */
    fun getAction(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getActionBind, segment)
    }

    /**
     * The button's visibility mode.
     *
     * Generated from Godot docs: TouchScreenButton.set_visibility_mode
     */
    fun setVisibilityMode(mode: TouchScreenButton.VisibilityMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setVisibilityModeBind, segment, mode.value)
    }

    /**
     * The button's visibility mode.
     *
     * Generated from Godot docs: TouchScreenButton.get_visibility_mode
     */
    fun getVisibilityMode(): TouchScreenButton.VisibilityMode {
        return TouchScreenButton.VisibilityMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getVisibilityModeBind, segment))
    }

    /**
     * If `true`, the `pressed` and `released` signals are emitted whenever a pressed finger goes in
     * and out of the button, even if the pressure started outside the active area of the button. Note:
     * This is a "pass-by" (not "bypass") press mode.
     *
     * Generated from Godot docs: TouchScreenButton.set_passby_press
     */
    fun setPassbyPress(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setPassbyPressBind, segment, enabled)
    }

    /**
     * If `true`, the `pressed` and `released` signals are emitted whenever a pressed finger goes in
     * and out of the button, even if the pressure started outside the active area of the button. Note:
     * This is a "pass-by" (not "bypass") press mode.
     *
     * Generated from Godot docs: TouchScreenButton.is_passby_press_enabled
     */
    fun isPassbyPressEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPassbyPressEnabledBind, segment)
    }

    /**
     * Returns `true` if this button is currently pressed.
     *
     * Generated from Godot docs: TouchScreenButton.is_pressed
     */
    fun isPressed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPressedBind, segment)
    }

    /** Signal `pressed()`; see [TypedSignal]. */
    val pressed: Signal0
        @JvmName("pressedTypedSignal")
        get() = Signal0(this, "pressed")

    /** Signal `released()`; see [TypedSignal]. */
    val released: Signal0
        @JvmName("releasedTypedSignal")
        get() = Signal0(this, "released")

    object Signals {
        const val pressed: String = "pressed"
        const val released: String = "released"
    }

    /**
     * Godot's `TouchScreenButton.VisibilityMode` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`TouchScreenButton.VisibilityMode.<NAME>`).
     *
     * Generated from Godot docs: TouchScreenButton.VisibilityMode
     */
    @JvmInline
    value class VisibilityMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Always visible.
             *
             * Generated from Godot docs: TouchScreenButton.VISIBILITY_ALWAYS
             */
            val ALWAYS: VisibilityMode get() = VisibilityMode(0L)
            /**
             * Visible on touch screens only.
             *
             * Generated from Godot docs: TouchScreenButton.VISIBILITY_TOUCHSCREEN_ONLY
             */
            val TOUCHSCREEN_ONLY: VisibilityMode get() = VisibilityMode(1L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TouchScreenButton? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): TouchScreenButton? =
            if (handle.address() == 0L) null else TouchScreenButton(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_TEXTURE_NORMAL_HASH = 4051416890L
        @JvmField
        val setTextureNormalBind =
            ObjectCalls.getMethodBind("TouchScreenButton", "set_texture_normal", SET_TEXTURE_NORMAL_HASH)

        private const val GET_TEXTURE_NORMAL_HASH = 3635182373L
        @JvmField
        val getTextureNormalBind =
            ObjectCalls.getMethodBind("TouchScreenButton", "get_texture_normal", GET_TEXTURE_NORMAL_HASH)

        private const val SET_TEXTURE_PRESSED_HASH = 4051416890L
        @JvmField
        val setTexturePressedBind =
            ObjectCalls.getMethodBind("TouchScreenButton", "set_texture_pressed", SET_TEXTURE_PRESSED_HASH)

        private const val GET_TEXTURE_PRESSED_HASH = 3635182373L
        @JvmField
        val getTexturePressedBind =
            ObjectCalls.getMethodBind("TouchScreenButton", "get_texture_pressed", GET_TEXTURE_PRESSED_HASH)

        private const val SET_BITMASK_HASH = 698588216L
        @JvmField
        val setBitmaskBind =
            ObjectCalls.getMethodBind("TouchScreenButton", "set_bitmask", SET_BITMASK_HASH)

        private const val GET_BITMASK_HASH = 2459671998L
        @JvmField
        val getBitmaskBind =
            ObjectCalls.getMethodBind("TouchScreenButton", "get_bitmask", GET_BITMASK_HASH)

        private const val SET_SHAPE_HASH = 771364740L
        @JvmField
        val setShapeBind =
            ObjectCalls.getMethodBind("TouchScreenButton", "set_shape", SET_SHAPE_HASH)

        private const val GET_SHAPE_HASH = 522005891L
        @JvmField
        val getShapeBind =
            ObjectCalls.getMethodBind("TouchScreenButton", "get_shape", GET_SHAPE_HASH)

        private const val SET_SHAPE_CENTERED_HASH = 2586408642L
        @JvmField
        val setShapeCenteredBind =
            ObjectCalls.getMethodBind("TouchScreenButton", "set_shape_centered", SET_SHAPE_CENTERED_HASH)

        private const val IS_SHAPE_CENTERED_HASH = 36873697L
        @JvmField
        val isShapeCenteredBind =
            ObjectCalls.getMethodBind("TouchScreenButton", "is_shape_centered", IS_SHAPE_CENTERED_HASH)

        private const val SET_SHAPE_VISIBLE_HASH = 2586408642L
        @JvmField
        val setShapeVisibleBind =
            ObjectCalls.getMethodBind("TouchScreenButton", "set_shape_visible", SET_SHAPE_VISIBLE_HASH)

        private const val IS_SHAPE_VISIBLE_HASH = 36873697L
        @JvmField
        val isShapeVisibleBind =
            ObjectCalls.getMethodBind("TouchScreenButton", "is_shape_visible", IS_SHAPE_VISIBLE_HASH)

        private const val SET_ACTION_HASH = 83702148L
        @JvmField
        val setActionBind =
            ObjectCalls.getMethodBind("TouchScreenButton", "set_action", SET_ACTION_HASH)

        private const val GET_ACTION_HASH = 201670096L
        @JvmField
        val getActionBind =
            ObjectCalls.getMethodBind("TouchScreenButton", "get_action", GET_ACTION_HASH)

        private const val SET_VISIBILITY_MODE_HASH = 3031128463L
        @JvmField
        val setVisibilityModeBind =
            ObjectCalls.getMethodBind("TouchScreenButton", "set_visibility_mode", SET_VISIBILITY_MODE_HASH)

        private const val GET_VISIBILITY_MODE_HASH = 2558996468L
        @JvmField
        val getVisibilityModeBind =
            ObjectCalls.getMethodBind("TouchScreenButton", "get_visibility_mode", GET_VISIBILITY_MODE_HASH)

        private const val SET_PASSBY_PRESS_HASH = 2586408642L
        @JvmField
        val setPassbyPressBind =
            ObjectCalls.getMethodBind("TouchScreenButton", "set_passby_press", SET_PASSBY_PRESS_HASH)

        private const val IS_PASSBY_PRESS_ENABLED_HASH = 36873697L
        @JvmField
        val isPassbyPressEnabledBind =
            ObjectCalls.getMethodBind("TouchScreenButton", "is_passby_press_enabled", IS_PASSBY_PRESS_ENABLED_HASH)

        private const val IS_PRESSED_HASH = 36873697L
        @JvmField
        val isPressedBind =
            ObjectCalls.getMethodBind("TouchScreenButton", "is_pressed", IS_PRESSED_HASH)
    }
}
