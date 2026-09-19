package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: InputEventKey
 */
class InputEventKey(handle: GodotHandle) : InputEventWithModifiers(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setPressedBind, segment, pressed)
    }

    fun setKeycode(keycode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setKeycodeBind, segment, keycode)
    }

    fun getKeycode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getKeycodeBind, segment)
    }

    fun setPhysicalKeycode(physicalKeycode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setPhysicalKeycodeBind, segment, physicalKeycode)
    }

    fun getPhysicalKeycode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getPhysicalKeycodeBind, segment)
    }

    fun setKeyLabel(keyLabel: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setKeyLabelBind, segment, keyLabel)
    }

    fun getKeyLabel(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getKeyLabelBind, segment)
    }

    fun setUnicode(unicode: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setUnicodeBind, segment, unicode)
    }

    fun getUnicode(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getUnicodeBind, segment)
    }

    fun setLocation(location: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setLocationBind, segment, location)
    }

    fun getLocation(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getLocationBind, segment)
    }

    fun setEcho(echo: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setEchoBind, segment, echo)
    }

    fun getKeycodeWithModifiers(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getKeycodeWithModifiersBind, segment)
    }

    fun getPhysicalKeycodeWithModifiers(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getPhysicalKeycodeWithModifiersBind, segment)
    }

    fun getKeyLabelWithModifiers(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getKeyLabelWithModifiersBind, segment)
    }

    fun asTextKeycode(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(asTextKeycodeBind, segment)
    }

    fun asTextPhysicalKeycode(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(asTextPhysicalKeycodeBind, segment)
    }

    fun asTextKeyLabel(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(asTextKeyLabelBind, segment)
    }

    fun asTextLocation(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(asTextLocationBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): InputEventKey? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): InputEventKey? =
            if (handle.address() == 0L) null else InputEventKey(GodotHandle(handle))

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

        // Instantiate an InputEventKey.
        fun create(): InputEventKey =
            InputEventKey(GodotHandle(MemorySegment.ofAddress(IosGodot.constructObject("InputEventKey"))))

        // Downcast a GodotObject to InputEventKey (null if not).
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
