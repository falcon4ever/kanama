package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Button for touch screen devices for gameplay use.
 *
 * Generated from Godot docs: TouchScreenButton
 */
class TouchScreenButton(handle: MemorySegment) : Node2D(handle) {
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

    var visibilityMode: Long
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
        ObjectCalls.ptrcallWithObjectArgs(setTextureNormalBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The button's texture for the normal state.
     *
     * Generated from Godot docs: TouchScreenButton.get_texture_normal
     */
    fun getTextureNormal(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextureNormalBind, handle))
    }

    /**
     * The button's texture for the pressed state.
     *
     * Generated from Godot docs: TouchScreenButton.set_texture_pressed
     */
    fun setTexturePressed(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setTexturePressedBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The button's texture for the pressed state.
     *
     * Generated from Godot docs: TouchScreenButton.get_texture_pressed
     */
    fun getTexturePressed(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTexturePressedBind, handle))
    }

    /**
     * The button's bitmask.
     *
     * Generated from Godot docs: TouchScreenButton.set_bitmask
     */
    fun setBitmask(bitmask: BitMap?) {
        ObjectCalls.ptrcallWithObjectArgs(setBitmaskBind, handle, listOf(bitmask?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The button's bitmask.
     *
     * Generated from Godot docs: TouchScreenButton.get_bitmask
     */
    fun getBitmask(): BitMap? {
        return BitMap.wrap(ObjectCalls.ptrcallNoArgsRetObject(getBitmaskBind, handle))
    }

    /**
     * The button's shape.
     *
     * Generated from Godot docs: TouchScreenButton.set_shape
     */
    fun setShape(shape: Shape2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setShapeBind, handle, listOf(shape?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The button's shape.
     *
     * Generated from Godot docs: TouchScreenButton.get_shape
     */
    fun getShape(): Shape2D? {
        return Shape2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getShapeBind, handle))
    }

    /**
     * If `true`, the button's shape is centered in the provided texture. If no texture is used, this
     * property has no effect.
     *
     * Generated from Godot docs: TouchScreenButton.set_shape_centered
     */
    fun setShapeCentered(bool: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShapeCenteredBind, handle, bool)
    }

    /**
     * If `true`, the button's shape is centered in the provided texture. If no texture is used, this
     * property has no effect.
     *
     * Generated from Godot docs: TouchScreenButton.is_shape_centered
     */
    fun isShapeCentered(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isShapeCenteredBind, handle)
    }

    /**
     * If `true`, the button's shape is visible in the editor.
     *
     * Generated from Godot docs: TouchScreenButton.set_shape_visible
     */
    fun setShapeVisible(bool: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShapeVisibleBind, handle, bool)
    }

    /**
     * If `true`, the button's shape is visible in the editor.
     *
     * Generated from Godot docs: TouchScreenButton.is_shape_visible
     */
    fun isShapeVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isShapeVisibleBind, handle)
    }

    /**
     * The button's action. Actions can be handled with `InputEventAction`.
     *
     * Generated from Godot docs: TouchScreenButton.set_action
     */
    fun setAction(action: String) {
        ObjectCalls.ptrcallWithStringArg(setActionBind, handle, action)
    }

    /**
     * The button's action. Actions can be handled with `InputEventAction`.
     *
     * Generated from Godot docs: TouchScreenButton.get_action
     */
    fun getAction(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getActionBind, handle)
    }

    /**
     * The button's visibility mode.
     *
     * Generated from Godot docs: TouchScreenButton.set_visibility_mode
     */
    fun setVisibilityMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setVisibilityModeBind, handle, mode)
    }

    /**
     * The button's visibility mode.
     *
     * Generated from Godot docs: TouchScreenButton.get_visibility_mode
     */
    fun getVisibilityMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVisibilityModeBind, handle)
    }

    /**
     * If `true`, the `pressed` and `released` signals are emitted whenever a pressed finger goes in
     * and out of the button, even if the pressure started outside the active area of the button. Note:
     * This is a "pass-by" (not "bypass") press mode.
     *
     * Generated from Godot docs: TouchScreenButton.set_passby_press
     */
    fun setPassbyPress(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPassbyPressBind, handle, enabled)
    }

    /**
     * If `true`, the `pressed` and `released` signals are emitted whenever a pressed finger goes in
     * and out of the button, even if the pressure started outside the active area of the button. Note:
     * This is a "pass-by" (not "bypass") press mode.
     *
     * Generated from Godot docs: TouchScreenButton.is_passby_press_enabled
     */
    fun isPassbyPressEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPassbyPressEnabledBind, handle)
    }

    /**
     * Returns `true` if this button is currently pressed.
     *
     * Generated from Godot docs: TouchScreenButton.is_pressed
     */
    fun isPressed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPressedBind, handle)
    }

    object Signals {
        const val pressed: String = "pressed"
        const val released: String = "released"
    }

    companion object {
        const val VISIBILITY_ALWAYS: Long = 0L
        const val VISIBILITY_TOUCHSCREEN_ONLY: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): TouchScreenButton? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TouchScreenButton? =
            if (handle.address() == 0L) null else TouchScreenButton(handle)

        private const val SET_TEXTURE_NORMAL_HASH = 4051416890L
        private val setTextureNormalBind by lazy {
            ObjectCalls.getMethodBind("TouchScreenButton", "set_texture_normal", SET_TEXTURE_NORMAL_HASH)
        }

        private const val GET_TEXTURE_NORMAL_HASH = 3635182373L
        private val getTextureNormalBind by lazy {
            ObjectCalls.getMethodBind("TouchScreenButton", "get_texture_normal", GET_TEXTURE_NORMAL_HASH)
        }

        private const val SET_TEXTURE_PRESSED_HASH = 4051416890L
        private val setTexturePressedBind by lazy {
            ObjectCalls.getMethodBind("TouchScreenButton", "set_texture_pressed", SET_TEXTURE_PRESSED_HASH)
        }

        private const val GET_TEXTURE_PRESSED_HASH = 3635182373L
        private val getTexturePressedBind by lazy {
            ObjectCalls.getMethodBind("TouchScreenButton", "get_texture_pressed", GET_TEXTURE_PRESSED_HASH)
        }

        private const val SET_BITMASK_HASH = 698588216L
        private val setBitmaskBind by lazy {
            ObjectCalls.getMethodBind("TouchScreenButton", "set_bitmask", SET_BITMASK_HASH)
        }

        private const val GET_BITMASK_HASH = 2459671998L
        private val getBitmaskBind by lazy {
            ObjectCalls.getMethodBind("TouchScreenButton", "get_bitmask", GET_BITMASK_HASH)
        }

        private const val SET_SHAPE_HASH = 771364740L
        private val setShapeBind by lazy {
            ObjectCalls.getMethodBind("TouchScreenButton", "set_shape", SET_SHAPE_HASH)
        }

        private const val GET_SHAPE_HASH = 522005891L
        private val getShapeBind by lazy {
            ObjectCalls.getMethodBind("TouchScreenButton", "get_shape", GET_SHAPE_HASH)
        }

        private const val SET_SHAPE_CENTERED_HASH = 2586408642L
        private val setShapeCenteredBind by lazy {
            ObjectCalls.getMethodBind("TouchScreenButton", "set_shape_centered", SET_SHAPE_CENTERED_HASH)
        }

        private const val IS_SHAPE_CENTERED_HASH = 36873697L
        private val isShapeCenteredBind by lazy {
            ObjectCalls.getMethodBind("TouchScreenButton", "is_shape_centered", IS_SHAPE_CENTERED_HASH)
        }

        private const val SET_SHAPE_VISIBLE_HASH = 2586408642L
        private val setShapeVisibleBind by lazy {
            ObjectCalls.getMethodBind("TouchScreenButton", "set_shape_visible", SET_SHAPE_VISIBLE_HASH)
        }

        private const val IS_SHAPE_VISIBLE_HASH = 36873697L
        private val isShapeVisibleBind by lazy {
            ObjectCalls.getMethodBind("TouchScreenButton", "is_shape_visible", IS_SHAPE_VISIBLE_HASH)
        }

        private const val SET_ACTION_HASH = 83702148L
        private val setActionBind by lazy {
            ObjectCalls.getMethodBind("TouchScreenButton", "set_action", SET_ACTION_HASH)
        }

        private const val GET_ACTION_HASH = 201670096L
        private val getActionBind by lazy {
            ObjectCalls.getMethodBind("TouchScreenButton", "get_action", GET_ACTION_HASH)
        }

        private const val SET_VISIBILITY_MODE_HASH = 3031128463L
        private val setVisibilityModeBind by lazy {
            ObjectCalls.getMethodBind("TouchScreenButton", "set_visibility_mode", SET_VISIBILITY_MODE_HASH)
        }

        private const val GET_VISIBILITY_MODE_HASH = 2558996468L
        private val getVisibilityModeBind by lazy {
            ObjectCalls.getMethodBind("TouchScreenButton", "get_visibility_mode", GET_VISIBILITY_MODE_HASH)
        }

        private const val SET_PASSBY_PRESS_HASH = 2586408642L
        private val setPassbyPressBind by lazy {
            ObjectCalls.getMethodBind("TouchScreenButton", "set_passby_press", SET_PASSBY_PRESS_HASH)
        }

        private const val IS_PASSBY_PRESS_ENABLED_HASH = 36873697L
        private val isPassbyPressEnabledBind by lazy {
            ObjectCalls.getMethodBind("TouchScreenButton", "is_passby_press_enabled", IS_PASSBY_PRESS_ENABLED_HASH)
        }

        private const val IS_PRESSED_HASH = 36873697L
        private val isPressedBind by lazy {
            ObjectCalls.getMethodBind("TouchScreenButton", "is_pressed", IS_PRESSED_HASH)
        }
    }
}
