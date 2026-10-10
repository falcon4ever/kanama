package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Represents a key on a keyboard being pressed or released.
 *
 * Generated from Godot docs: InputEventKey
 */
class InputEventKey(handle: GodotHandle) : InputEventWithModifiers(handle) {
    var keycode: Key
        @JvmName("keycodeProperty")
        get() = getKeycode()
        @JvmName("setKeycodeProperty")
        set(value) = setKeycode(value)

    var physicalKeycode: Key
        @JvmName("physicalKeycodeProperty")
        get() = getPhysicalKeycode()
        @JvmName("setPhysicalKeycodeProperty")
        set(value) = setPhysicalKeycode(value)

    var keyLabel: Key
        @JvmName("keyLabelProperty")
        get() = getKeyLabel()
        @JvmName("setKeyLabelProperty")
        set(value) = setKeyLabel(value)

    var unicode: Int
        @JvmName("unicodeProperty")
        get() = getUnicode()
        @JvmName("setUnicodeProperty")
        set(value) = setUnicode(value)

    var location: KeyLocation
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
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setPressedBind, segment, pressed)
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
    fun setKeycode(keycode: Key) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setKeycodeBind, segment, keycode.value)
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
    fun getKeycode(): Key {
        checkOpen()
        return Key(ObjectCalls.ptrcallNoArgsRetLong(Binds.getKeycodeBind, segment))
    }

    /**
     * Represents the physical location of a key on the 101/102-key US QWERTY keyboard, which
     * corresponds to one of the `Key` constants. Physical key codes meant for game input, such as WASD
     * movement, where only the location of the keys is important.
     *
     * Generated from Godot docs: InputEventKey.set_physical_keycode
     */
    fun setPhysicalKeycode(physicalKeycode: Key) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setPhysicalKeycodeBind, segment, physicalKeycode.value)
    }

    /**
     * Represents the physical location of a key on the 101/102-key US QWERTY keyboard, which
     * corresponds to one of the `Key` constants. Physical key codes meant for game input, such as WASD
     * movement, where only the location of the keys is important.
     *
     * Generated from Godot docs: InputEventKey.get_physical_keycode
     */
    fun getPhysicalKeycode(): Key {
        checkOpen()
        return Key(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPhysicalKeycodeBind, segment))
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
    fun setKeyLabel(keyLabel: Key) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setKeyLabelBind, segment, keyLabel.value)
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
    fun getKeyLabel(): Key {
        checkOpen()
        return Key(ObjectCalls.ptrcallNoArgsRetLong(Binds.getKeyLabelBind, segment))
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
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setUnicodeBind, segment, unicode)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getUnicodeBind, segment)
    }

    /**
     * Represents the location of a key which has both left and right versions, such as Shift or Alt.
     *
     * Generated from Godot docs: InputEventKey.set_location
     */
    fun setLocation(location: KeyLocation) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setLocationBind, segment, location.value)
    }

    /**
     * Represents the location of a key which has both left and right versions, such as Shift or Alt.
     *
     * Generated from Godot docs: InputEventKey.get_location
     */
    fun getLocation(): KeyLocation {
        checkOpen()
        return KeyLocation(ObjectCalls.ptrcallNoArgsRetLong(Binds.getLocationBind, segment))
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
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setEchoBind, segment, echo)
    }

    /**
     * Returns the Latin keycode combined with modifier keys such as Shift or Alt. See also
     * `InputEventWithModifiers`. To get a human-readable representation of the `InputEventKey` with
     * modifiers, use `OS.get_keycode_string(event.get_keycode_with_modifiers())` where `event` is the
     * `InputEventKey`.
     *
     * Generated from Godot docs: InputEventKey.get_keycode_with_modifiers
     */
    fun getKeycodeWithModifiers(): Key {
        checkOpen()
        return Key(ObjectCalls.ptrcallNoArgsRetLong(Binds.getKeycodeWithModifiersBind, segment))
    }

    /**
     * Returns the physical keycode combined with modifier keys such as Shift or Alt. See also
     * `InputEventWithModifiers`. To get a human-readable representation of the `InputEventKey` with
     * modifiers, use `OS.get_keycode_string(event.get_physical_keycode_with_modifiers())` where
     * `event` is the `InputEventKey`.
     *
     * Generated from Godot docs: InputEventKey.get_physical_keycode_with_modifiers
     */
    fun getPhysicalKeycodeWithModifiers(): Key {
        checkOpen()
        return Key(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPhysicalKeycodeWithModifiersBind, segment))
    }

    /**
     * Returns the localized key label combined with modifier keys such as Shift or Alt. See also
     * `InputEventWithModifiers`. To get a human-readable representation of the `InputEventKey` with
     * modifiers, use `OS.get_keycode_string(event.get_key_label_with_modifiers())` where `event` is
     * the `InputEventKey`.
     *
     * Generated from Godot docs: InputEventKey.get_key_label_with_modifiers
     */
    fun getKeyLabelWithModifiers(): Key {
        checkOpen()
        return Key(ObjectCalls.ptrcallNoArgsRetLong(Binds.getKeyLabelWithModifiersBind, segment))
    }

    /**
     * Returns a `String` representation of the event's `keycode` and modifiers.
     *
     * Generated from Godot docs: InputEventKey.as_text_keycode
     */
    fun asTextKeycode(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.asTextKeycodeBind, segment)
    }

    /**
     * Returns a `String` representation of the event's `physical_keycode` and modifiers.
     *
     * Generated from Godot docs: InputEventKey.as_text_physical_keycode
     */
    fun asTextPhysicalKeycode(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.asTextPhysicalKeycodeBind, segment)
    }

    /**
     * Returns a `String` representation of the event's `key_label` and modifiers.
     *
     * Generated from Godot docs: InputEventKey.as_text_key_label
     */
    fun asTextKeyLabel(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.asTextKeyLabelBind, segment)
    }

    /**
     * Returns a `String` representation of the event's `location`. This will be a blank string if the
     * event is not specific to a location.
     *
     * Generated from Godot docs: InputEventKey.as_text_location
     */
    fun asTextLocation(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.asTextLocationBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): InputEventKey? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): InputEventKey? =
            if (handle.address() == 0L) null else RefCounted.owned(InputEventKey(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): InputEventKey? =
            if (handle.address() == 0L) null else InputEventKey(GodotHandle(handle))

        // Instantiate an InputEventKey.
        @JvmStatic
        fun create(): InputEventKey =
            RefCounted.owned(InputEventKey(GodotHandle(ObjectCalls.constructObject("InputEventKey"))))

        // Downcast a GodotObject to InputEventKey (null if not).
        @JvmStatic
        fun from(value: GodotObject): InputEventKey? =
            if (value.isClass("InputEventKey")) RefCounted.retained(InputEventKey(value.handle)) else null
    }

    private object Binds {
        private const val SET_PRESSED_HASH = 2586408642L
        @JvmField
        val setPressedBind =
            ObjectCalls.getMethodBind("InputEventKey", "set_pressed", SET_PRESSED_HASH)

        private const val SET_KEYCODE_HASH = 888074362L
        @JvmField
        val setKeycodeBind =
            ObjectCalls.getMethodBind("InputEventKey", "set_keycode", SET_KEYCODE_HASH)

        private const val GET_KEYCODE_HASH = 1585896689L
        @JvmField
        val getKeycodeBind =
            ObjectCalls.getMethodBind("InputEventKey", "get_keycode", GET_KEYCODE_HASH)

        private const val SET_PHYSICAL_KEYCODE_HASH = 888074362L
        @JvmField
        val setPhysicalKeycodeBind =
            ObjectCalls.getMethodBind("InputEventKey", "set_physical_keycode", SET_PHYSICAL_KEYCODE_HASH)

        private const val GET_PHYSICAL_KEYCODE_HASH = 1585896689L
        @JvmField
        val getPhysicalKeycodeBind =
            ObjectCalls.getMethodBind("InputEventKey", "get_physical_keycode", GET_PHYSICAL_KEYCODE_HASH)

        private const val SET_KEY_LABEL_HASH = 888074362L
        @JvmField
        val setKeyLabelBind =
            ObjectCalls.getMethodBind("InputEventKey", "set_key_label", SET_KEY_LABEL_HASH)

        private const val GET_KEY_LABEL_HASH = 1585896689L
        @JvmField
        val getKeyLabelBind =
            ObjectCalls.getMethodBind("InputEventKey", "get_key_label", GET_KEY_LABEL_HASH)

        private const val SET_UNICODE_HASH = 1286410249L
        @JvmField
        val setUnicodeBind =
            ObjectCalls.getMethodBind("InputEventKey", "set_unicode", SET_UNICODE_HASH)

        private const val GET_UNICODE_HASH = 3905245786L
        @JvmField
        val getUnicodeBind =
            ObjectCalls.getMethodBind("InputEventKey", "get_unicode", GET_UNICODE_HASH)

        private const val SET_LOCATION_HASH = 634453155L
        @JvmField
        val setLocationBind =
            ObjectCalls.getMethodBind("InputEventKey", "set_location", SET_LOCATION_HASH)

        private const val GET_LOCATION_HASH = 211810873L
        @JvmField
        val getLocationBind =
            ObjectCalls.getMethodBind("InputEventKey", "get_location", GET_LOCATION_HASH)

        private const val SET_ECHO_HASH = 2586408642L
        @JvmField
        val setEchoBind =
            ObjectCalls.getMethodBind("InputEventKey", "set_echo", SET_ECHO_HASH)

        private const val GET_KEYCODE_WITH_MODIFIERS_HASH = 1585896689L
        @JvmField
        val getKeycodeWithModifiersBind =
            ObjectCalls.getMethodBind("InputEventKey", "get_keycode_with_modifiers", GET_KEYCODE_WITH_MODIFIERS_HASH)

        private const val GET_PHYSICAL_KEYCODE_WITH_MODIFIERS_HASH = 1585896689L
        @JvmField
        val getPhysicalKeycodeWithModifiersBind =
            ObjectCalls.getMethodBind("InputEventKey", "get_physical_keycode_with_modifiers", GET_PHYSICAL_KEYCODE_WITH_MODIFIERS_HASH)

        private const val GET_KEY_LABEL_WITH_MODIFIERS_HASH = 1585896689L
        @JvmField
        val getKeyLabelWithModifiersBind =
            ObjectCalls.getMethodBind("InputEventKey", "get_key_label_with_modifiers", GET_KEY_LABEL_WITH_MODIFIERS_HASH)

        private const val AS_TEXT_KEYCODE_HASH = 201670096L
        @JvmField
        val asTextKeycodeBind =
            ObjectCalls.getMethodBind("InputEventKey", "as_text_keycode", AS_TEXT_KEYCODE_HASH)

        private const val AS_TEXT_PHYSICAL_KEYCODE_HASH = 201670096L
        @JvmField
        val asTextPhysicalKeycodeBind =
            ObjectCalls.getMethodBind("InputEventKey", "as_text_physical_keycode", AS_TEXT_PHYSICAL_KEYCODE_HASH)

        private const val AS_TEXT_KEY_LABEL_HASH = 201670096L
        @JvmField
        val asTextKeyLabelBind =
            ObjectCalls.getMethodBind("InputEventKey", "as_text_key_label", AS_TEXT_KEY_LABEL_HASH)

        private const val AS_TEXT_LOCATION_HASH = 201670096L
        @JvmField
        val asTextLocationBind =
            ObjectCalls.getMethodBind("InputEventKey", "as_text_location", AS_TEXT_LOCATION_HASH)
    }
}
