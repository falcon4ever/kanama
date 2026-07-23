package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
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

    fun setPressed(pressed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPressedBind, handle, pressed)
    }

    fun setKeycode(keycode: Long) {
        ObjectCalls.ptrcallWithLongArg(setKeycodeBind, handle, keycode)
    }

    fun getKeycode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getKeycodeBind, handle)
    }

    fun setPhysicalKeycode(physicalKeycode: Long) {
        ObjectCalls.ptrcallWithLongArg(setPhysicalKeycodeBind, handle, physicalKeycode)
    }

    fun getPhysicalKeycode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPhysicalKeycodeBind, handle)
    }

    fun setKeyLabel(keyLabel: Long) {
        ObjectCalls.ptrcallWithLongArg(setKeyLabelBind, handle, keyLabel)
    }

    fun getKeyLabel(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getKeyLabelBind, handle)
    }

    fun setUnicode(unicode: Int) {
        ObjectCalls.ptrcallWithIntArg(setUnicodeBind, handle, unicode)
    }

    fun getUnicode(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getUnicodeBind, handle)
    }

    fun setLocation(location: Long) {
        ObjectCalls.ptrcallWithLongArg(setLocationBind, handle, location)
    }

    fun getLocation(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getLocationBind, handle)
    }

    fun setEcho(echo: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEchoBind, handle, echo)
    }

    fun getKeycodeWithModifiers(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getKeycodeWithModifiersBind, handle)
    }

    fun getPhysicalKeycodeWithModifiers(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPhysicalKeycodeWithModifiersBind, handle)
    }

    fun getKeyLabelWithModifiers(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getKeyLabelWithModifiersBind, handle)
    }

    fun asTextKeycode(): String {
        return ObjectCalls.ptrcallNoArgsRetString(asTextKeycodeBind, handle)
    }

    fun asTextPhysicalKeycode(): String {
        return ObjectCalls.ptrcallNoArgsRetString(asTextPhysicalKeycodeBind, handle)
    }

    fun asTextKeyLabel(): String {
        return ObjectCalls.ptrcallNoArgsRetString(asTextKeyLabelBind, handle)
    }

    fun asTextLocation(): String {
        return ObjectCalls.ptrcallNoArgsRetString(asTextLocationBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): InputEventKey? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): InputEventKey? =
            if (handle.address() == 0L) null else InputEventKey(handle)

        // Godot Key enum constants (subset used by gameplay code; values match @GlobalScope.Key).
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

        // Instantiate a blank InputEventKey (for synthesizing input events / InputMap actions).
        fun create(): InputEventKey =
            InputEventKey(MemorySegment.ofAddress(IosGodot.constructObject("InputEventKey")))

        // Cast a generic event to InputEventKey (null if not), mirroring the desktop helper.
        fun from(value: GodotObject): InputEventKey? =
            if (value.isClass("InputEventKey")) InputEventKey(value.handle) else null

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
