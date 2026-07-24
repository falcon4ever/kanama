package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName

/**
 * Represents a key on a keyboard being pressed or released.
 *
 * Generated from Godot docs: InputEventKey
 */
class InputEventKey(handle: MemorySegment) : InputEventWithModifiers(handle) {
    var keycode: Long
        @JvmName("keycodeProperty")
        get() = getKeycode()
        @JvmName("setKeycodeProperty")
        set(value) = setKeycode(value)

    var physicalKeycode: Long
        @JvmName("physicalKeycodeProperty")
        get() = getPhysicalKeycode()
        @JvmName("setPhysicalKeycodeProperty")
        set(value) = setPhysicalKeycode(value)

    var keyLabel: Long
        @JvmName("keyLabelProperty")
        get() = getKeyLabel()
        @JvmName("setKeyLabelProperty")
        set(value) = setKeyLabel(value)

    var unicode: Int
        @JvmName("unicodeProperty")
        get() = getUnicode()
        @JvmName("setUnicodeProperty")
        set(value) = setUnicode(value)

    var location: Long
        @JvmName("locationProperty")
        get() = getLocation()
        @JvmName("setLocationProperty")
        set(value) = setLocation(value)

    /**
     * If `true`, the key's state is pressed. If `false`, the key's state is released.
     *
     * Generated from Godot docs: InputEventKey.set_pressed
     */
    fun setPressed(pressed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPressedBind, handle, pressed)
    }

    /**
     * Latin label printed on the key in the current keyboard layout, which corresponds to one of the
     * `Key` constants. Key codes are meant for shortcuts expressed with a standard Latin keyboard,
     * such as Ctrl + S for a "Save" shortcut. To get a human-readable representation of the
     * `InputEventKey`, use `OS.get_keycode_string(event.keycode)` where `event` is the
     * `InputEventKey`.
     *
     * Generated from Godot docs: InputEventKey.set_keycode
     */
    fun setKeycode(keycode: Long) {
        ObjectCalls.ptrcallWithLongArg(setKeycodeBind, handle, keycode)
    }

    /**
     * Latin label printed on the key in the current keyboard layout, which corresponds to one of the
     * `Key` constants. Key codes are meant for shortcuts expressed with a standard Latin keyboard,
     * such as Ctrl + S for a "Save" shortcut. To get a human-readable representation of the
     * `InputEventKey`, use `OS.get_keycode_string(event.keycode)` where `event` is the
     * `InputEventKey`.
     *
     * Generated from Godot docs: InputEventKey.get_keycode
     */
    fun getKeycode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getKeycodeBind, handle)
    }

    /**
     * Represents the physical location of a key on the 101/102-key US QWERTY keyboard, which
     * corresponds to one of the `Key` constants. Physical key codes meant for game input, such as WASD
     * movement, where only the location of the keys is important. To get a human-readable
     * representation of the `InputEventKey`, use `OS.get_keycode_string` in combination with
     * `DisplayServer.keyboard_get_keycode_from_physical` or
     * `DisplayServer.keyboard_get_label_from_physical`:
     *
     * Generated from Godot docs: InputEventKey.set_physical_keycode
     */
    fun setPhysicalKeycode(physicalKeycode: Long) {
        ObjectCalls.ptrcallWithLongArg(setPhysicalKeycodeBind, handle, physicalKeycode)
    }

    /**
     * Represents the physical location of a key on the 101/102-key US QWERTY keyboard, which
     * corresponds to one of the `Key` constants. Physical key codes meant for game input, such as WASD
     * movement, where only the location of the keys is important. To get a human-readable
     * representation of the `InputEventKey`, use `OS.get_keycode_string` in combination with
     * `DisplayServer.keyboard_get_keycode_from_physical` or
     * `DisplayServer.keyboard_get_label_from_physical`:
     *
     * Generated from Godot docs: InputEventKey.get_physical_keycode
     */
    fun getPhysicalKeycode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPhysicalKeycodeBind, handle)
    }

    /**
     * Represents the localized label printed on the key in the current keyboard layout, which
     * corresponds to one of the `Key` constants or any valid Unicode character. Key labels are meant
     * for key prompts. For keyboard layouts with a single label on the key, it is equivalent to
     * `keycode`. To get a human-readable representation of the `InputEventKey`, use
     * `OS.get_keycode_string(event.key_label)` where `event` is the `InputEventKey`.
     *
     * Generated from Godot docs: InputEventKey.set_key_label
     */
    fun setKeyLabel(keyLabel: Long) {
        ObjectCalls.ptrcallWithLongArg(setKeyLabelBind, handle, keyLabel)
    }

    /**
     * Represents the localized label printed on the key in the current keyboard layout, which
     * corresponds to one of the `Key` constants or any valid Unicode character. Key labels are meant
     * for key prompts. For keyboard layouts with a single label on the key, it is equivalent to
     * `keycode`. To get a human-readable representation of the `InputEventKey`, use
     * `OS.get_keycode_string(event.key_label)` where `event` is the `InputEventKey`.
     *
     * Generated from Godot docs: InputEventKey.get_key_label
     */
    fun getKeyLabel(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getKeyLabelBind, handle)
    }

    /**
     * The key Unicode character code (when relevant), shifted by modifier keys. Unicode character
     * codes for composite characters and complex scripts may not be available unless IME input mode is
     * active. See `Window.set_ime_active` for more information. Unicode character codes are meant for
     * text input. Note: This property is set by the engine only for a pressed event. If the event is
     * sent by an IME or a virtual keyboard, no corresponding key released event is sent.
     *
     * Generated from Godot docs: InputEventKey.set_unicode
     */
    fun setUnicode(unicode: Int) {
        ObjectCalls.ptrcallWithIntArg(setUnicodeBind, handle, unicode)
    }

    /**
     * The key Unicode character code (when relevant), shifted by modifier keys. Unicode character
     * codes for composite characters and complex scripts may not be available unless IME input mode is
     * active. See `Window.set_ime_active` for more information. Unicode character codes are meant for
     * text input. Note: This property is set by the engine only for a pressed event. If the event is
     * sent by an IME or a virtual keyboard, no corresponding key released event is sent.
     *
     * Generated from Godot docs: InputEventKey.get_unicode
     */
    fun getUnicode(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getUnicodeBind, handle)
    }

    /**
     * Represents the location of a key which has both left and right versions, such as Shift or Alt.
     *
     * Generated from Godot docs: InputEventKey.set_location
     */
    fun setLocation(location: Long) {
        ObjectCalls.ptrcallWithLongArg(setLocationBind, handle, location)
    }

    /**
     * Represents the location of a key which has both left and right versions, such as Shift or Alt.
     *
     * Generated from Godot docs: InputEventKey.get_location
     */
    fun getLocation(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getLocationBind, handle)
    }

    /**
     * If `true`, the key was already pressed before this event. An echo event is a repeated key event
     * sent when the user is holding down the key. Note: The rate at which echo events are sent is
     * typically around 20 events per second (after holding down the key for roughly half a second).
     * However, the key repeat delay/speed can be changed by the user or disabled entirely in the
     * operating system settings. To ensure your project works correctly on all configurations, do not
     * assume the user has a specific key repeat configuration in your project's behavior.
     *
     * Generated from Godot docs: InputEventKey.set_echo
     */
    fun setEcho(echo: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEchoBind, handle, echo)
    }

    /**
     * Returns the Latin keycode combined with modifier keys such as Shift or Alt. See also
     * `InputEventWithModifiers`. To get a human-readable representation of the `InputEventKey` with
     * modifiers, use `OS.get_keycode_string(event.get_keycode_with_modifiers())` where `event` is the
     * `InputEventKey`.
     *
     * Generated from Godot docs: InputEventKey.get_keycode_with_modifiers
     */
    fun getKeycodeWithModifiers(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getKeycodeWithModifiersBind, handle)
    }

    /**
     * Returns the physical keycode combined with modifier keys such as Shift or Alt. See also
     * `InputEventWithModifiers`. To get a human-readable representation of the `InputEventKey` with
     * modifiers, use `OS.get_keycode_string(event.get_physical_keycode_with_modifiers())` where
     * `event` is the `InputEventKey`.
     *
     * Generated from Godot docs: InputEventKey.get_physical_keycode_with_modifiers
     */
    fun getPhysicalKeycodeWithModifiers(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPhysicalKeycodeWithModifiersBind, handle)
    }

    /**
     * Returns the localized key label combined with modifier keys such as Shift or Alt. See also
     * `InputEventWithModifiers`. To get a human-readable representation of the `InputEventKey` with
     * modifiers, use `OS.get_keycode_string(event.get_key_label_with_modifiers())` where `event` is
     * the `InputEventKey`.
     *
     * Generated from Godot docs: InputEventKey.get_key_label_with_modifiers
     */
    fun getKeyLabelWithModifiers(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getKeyLabelWithModifiersBind, handle)
    }

    /**
     * Returns a `String` representation of the event's `keycode` and modifiers.
     *
     * Generated from Godot docs: InputEventKey.as_text_keycode
     */
    fun asTextKeycode(): String {
        return ObjectCalls.ptrcallNoArgsRetString(asTextKeycodeBind, handle)
    }

    /**
     * Returns a `String` representation of the event's `physical_keycode` and modifiers.
     *
     * Generated from Godot docs: InputEventKey.as_text_physical_keycode
     */
    fun asTextPhysicalKeycode(): String {
        return ObjectCalls.ptrcallNoArgsRetString(asTextPhysicalKeycodeBind, handle)
    }

    /**
     * Returns a `String` representation of the event's `key_label` and modifiers.
     *
     * Generated from Godot docs: InputEventKey.as_text_key_label
     */
    fun asTextKeyLabel(): String {
        return ObjectCalls.ptrcallNoArgsRetString(asTextKeyLabelBind, handle)
    }

    /**
     * Returns a `String` representation of the event's `location`. This will be a blank string if the
     * event is not specific to a location.
     *
     * Generated from Godot docs: InputEventKey.as_text_location
     */
    fun asTextLocation(): String {
        return ObjectCalls.ptrcallNoArgsRetString(asTextLocationBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): InputEventKey? =
            wrap(handle)

        @JvmStatic
        fun from(value: GodotObject): InputEventKey? =
            if (value.isClass("InputEventKey")) InputEventKey(value.handle) else null

        @JvmStatic
        fun create(): InputEventKey =
            InputEventKey(ObjectCalls.constructObject("InputEventKey"))

        internal fun wrap(handle: MemorySegment): InputEventKey? =
            if (handle.address() == 0L) null else InputEventKey(handle)

        const val KEY_ESCAPE = 4194305L
        const val KEY_TAB = 4194306L
        const val KEY_ENTER = 4194309L
        const val KEY_F10 = 4194341L
        const val KEY_F11 = 4194342L
        const val KEY_SPACE = 32L
        const val KEY_A = 65L
        const val KEY_D = 68L
        const val KEY_E = 69L
        const val KEY_F = 70L
        const val KEY_Q = 81L
        const val KEY_R = 82L
        const val KEY_S = 83L
        const val KEY_W = 87L

        private const val SET_PRESSED_HASH = 2586408642L
        private val setPressedBind by lazy {
            ObjectCalls.getMethodBind("InputEventKey", "set_pressed", SET_PRESSED_HASH)
        }

        private const val SET_KEYCODE_HASH = 888074362L
        private val setKeycodeBind by lazy {
            ObjectCalls.getMethodBind("InputEventKey", "set_keycode", SET_KEYCODE_HASH)
        }

        private const val GET_KEYCODE_HASH = 1585896689L
        private val getKeycodeBind by lazy {
            ObjectCalls.getMethodBind("InputEventKey", "get_keycode", GET_KEYCODE_HASH)
        }

        private const val SET_PHYSICAL_KEYCODE_HASH = 888074362L
        private val setPhysicalKeycodeBind by lazy {
            ObjectCalls.getMethodBind("InputEventKey", "set_physical_keycode", SET_PHYSICAL_KEYCODE_HASH)
        }

        private const val GET_PHYSICAL_KEYCODE_HASH = 1585896689L
        private val getPhysicalKeycodeBind by lazy {
            ObjectCalls.getMethodBind("InputEventKey", "get_physical_keycode", GET_PHYSICAL_KEYCODE_HASH)
        }

        private const val SET_KEY_LABEL_HASH = 888074362L
        private val setKeyLabelBind by lazy {
            ObjectCalls.getMethodBind("InputEventKey", "set_key_label", SET_KEY_LABEL_HASH)
        }

        private const val GET_KEY_LABEL_HASH = 1585896689L
        private val getKeyLabelBind by lazy {
            ObjectCalls.getMethodBind("InputEventKey", "get_key_label", GET_KEY_LABEL_HASH)
        }

        private const val SET_UNICODE_HASH = 1286410249L
        private val setUnicodeBind by lazy {
            ObjectCalls.getMethodBind("InputEventKey", "set_unicode", SET_UNICODE_HASH)
        }

        private const val GET_UNICODE_HASH = 3905245786L
        private val getUnicodeBind by lazy {
            ObjectCalls.getMethodBind("InputEventKey", "get_unicode", GET_UNICODE_HASH)
        }

        private const val SET_LOCATION_HASH = 634453155L
        private val setLocationBind by lazy {
            ObjectCalls.getMethodBind("InputEventKey", "set_location", SET_LOCATION_HASH)
        }

        private const val GET_LOCATION_HASH = 211810873L
        private val getLocationBind by lazy {
            ObjectCalls.getMethodBind("InputEventKey", "get_location", GET_LOCATION_HASH)
        }

        private const val SET_ECHO_HASH = 2586408642L
        private val setEchoBind by lazy {
            ObjectCalls.getMethodBind("InputEventKey", "set_echo", SET_ECHO_HASH)
        }

        private const val GET_KEYCODE_WITH_MODIFIERS_HASH = 1585896689L
        private val getKeycodeWithModifiersBind by lazy {
            ObjectCalls.getMethodBind("InputEventKey", "get_keycode_with_modifiers", GET_KEYCODE_WITH_MODIFIERS_HASH)
        }

        private const val GET_PHYSICAL_KEYCODE_WITH_MODIFIERS_HASH = 1585896689L
        private val getPhysicalKeycodeWithModifiersBind by lazy {
            ObjectCalls.getMethodBind("InputEventKey", "get_physical_keycode_with_modifiers", GET_PHYSICAL_KEYCODE_WITH_MODIFIERS_HASH)
        }

        private const val GET_KEY_LABEL_WITH_MODIFIERS_HASH = 1585896689L
        private val getKeyLabelWithModifiersBind by lazy {
            ObjectCalls.getMethodBind("InputEventKey", "get_key_label_with_modifiers", GET_KEY_LABEL_WITH_MODIFIERS_HASH)
        }

        private const val AS_TEXT_KEYCODE_HASH = 201670096L
        private val asTextKeycodeBind by lazy {
            ObjectCalls.getMethodBind("InputEventKey", "as_text_keycode", AS_TEXT_KEYCODE_HASH)
        }

        private const val AS_TEXT_PHYSICAL_KEYCODE_HASH = 201670096L
        private val asTextPhysicalKeycodeBind by lazy {
            ObjectCalls.getMethodBind("InputEventKey", "as_text_physical_keycode", AS_TEXT_PHYSICAL_KEYCODE_HASH)
        }

        private const val AS_TEXT_KEY_LABEL_HASH = 201670096L
        private val asTextKeyLabelBind by lazy {
            ObjectCalls.getMethodBind("InputEventKey", "as_text_key_label", AS_TEXT_KEY_LABEL_HASH)
        }

        private const val AS_TEXT_LOCATION_HASH = 201670096L
        private val asTextLocationBind by lazy {
            ObjectCalls.getMethodBind("InputEventKey", "as_text_location", AS_TEXT_LOCATION_HASH)
        }
    }
}
