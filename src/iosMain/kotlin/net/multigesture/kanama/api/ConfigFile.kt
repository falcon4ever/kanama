package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: ConfigFile
 */
class ConfigFile(handle: GodotHandle) : RefCounted(handle) {
    fun setValue(section: String, key: String, value: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoStringAndVariantArg(setValueBind, segment, section, key, value)
    }

    fun getValue(section: String, key: String, default: Any? = null): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoStringAndVariantArgRetVariantScalar(getValueBind, segment, section, key, default)
    }

    fun hasSection(section: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(hasSectionBind, segment, section)
    }

    fun hasSectionKey(section: String, key: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoStringArgsRetBool(hasSectionKeyBind, segment, section, key)
    }

    fun getSections(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getSectionsBind, segment)
    }

    fun getSectionKeys(section: String): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetPackedStringList(getSectionKeysBind, segment, section)
    }

    fun eraseSection(section: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(eraseSectionBind, segment, section)
    }

    fun eraseSectionKey(section: String, key: String) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoStringArgs(eraseSectionKeyBind, segment, section, key)
    }

    fun load(path: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(loadBind, segment, path)
    }

    fun parse(data: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(parseBind, segment, data)
    }

    fun save(path: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(saveBind, segment, path)
    }

    fun encodeToText(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(encodeToTextBind, segment)
    }

    fun loadEncrypted(path: String, key: ByteArray): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndByteArrayArgRetLong(loadEncryptedBind, segment, path, key)
    }

    fun loadEncryptedPass(path: String, password: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoStringArgsRetLong(loadEncryptedPassBind, segment, path, password)
    }

    fun saveEncrypted(path: String, key: ByteArray): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndByteArrayArgRetLong(saveEncryptedBind, segment, path, key)
    }

    fun saveEncryptedPass(path: String, password: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoStringArgsRetLong(saveEncryptedPassBind, segment, path, password)
    }

    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ConfigFile? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): ConfigFile? =
            if (handle.address() == 0L) null else ConfigFile(GodotHandle(handle))

        // Instantiate a ConfigFile (RefCounted key/value store).
        fun create(): ConfigFile =
            ConfigFile(GodotHandle(MemorySegment.ofAddress(IosGodot.constructObject("ConfigFile"))))

        private const val SET_VALUE_HASH = 2504492430L
        private val setValueBind by lazy {
            ObjectCalls.getMethodBind("ConfigFile", "set_value", SET_VALUE_HASH)
        }

        private const val GET_VALUE_HASH = 89809366L
        private val getValueBind by lazy {
            ObjectCalls.getMethodBind("ConfigFile", "get_value", GET_VALUE_HASH)
        }

        private const val HAS_SECTION_HASH = 3927539163L
        private val hasSectionBind by lazy {
            ObjectCalls.getMethodBind("ConfigFile", "has_section", HAS_SECTION_HASH)
        }

        private const val HAS_SECTION_KEY_HASH = 820780508L
        private val hasSectionKeyBind by lazy {
            ObjectCalls.getMethodBind("ConfigFile", "has_section_key", HAS_SECTION_KEY_HASH)
        }

        private const val GET_SECTIONS_HASH = 1139954409L
        private val getSectionsBind by lazy {
            ObjectCalls.getMethodBind("ConfigFile", "get_sections", GET_SECTIONS_HASH)
        }

        private const val GET_SECTION_KEYS_HASH = 4291131558L
        private val getSectionKeysBind by lazy {
            ObjectCalls.getMethodBind("ConfigFile", "get_section_keys", GET_SECTION_KEYS_HASH)
        }

        private const val ERASE_SECTION_HASH = 83702148L
        private val eraseSectionBind by lazy {
            ObjectCalls.getMethodBind("ConfigFile", "erase_section", ERASE_SECTION_HASH)
        }

        private const val ERASE_SECTION_KEY_HASH = 3186203200L
        private val eraseSectionKeyBind by lazy {
            ObjectCalls.getMethodBind("ConfigFile", "erase_section_key", ERASE_SECTION_KEY_HASH)
        }

        private const val LOAD_HASH = 166001499L
        private val loadBind by lazy {
            ObjectCalls.getMethodBind("ConfigFile", "load", LOAD_HASH)
        }

        private const val PARSE_HASH = 166001499L
        private val parseBind by lazy {
            ObjectCalls.getMethodBind("ConfigFile", "parse", PARSE_HASH)
        }

        private const val SAVE_HASH = 166001499L
        private val saveBind by lazy {
            ObjectCalls.getMethodBind("ConfigFile", "save", SAVE_HASH)
        }

        private const val ENCODE_TO_TEXT_HASH = 201670096L
        private val encodeToTextBind by lazy {
            ObjectCalls.getMethodBind("ConfigFile", "encode_to_text", ENCODE_TO_TEXT_HASH)
        }

        private const val LOAD_ENCRYPTED_HASH = 887037711L
        private val loadEncryptedBind by lazy {
            ObjectCalls.getMethodBind("ConfigFile", "load_encrypted", LOAD_ENCRYPTED_HASH)
        }

        private const val LOAD_ENCRYPTED_PASS_HASH = 852856452L
        private val loadEncryptedPassBind by lazy {
            ObjectCalls.getMethodBind("ConfigFile", "load_encrypted_pass", LOAD_ENCRYPTED_PASS_HASH)
        }

        private const val SAVE_ENCRYPTED_HASH = 887037711L
        private val saveEncryptedBind by lazy {
            ObjectCalls.getMethodBind("ConfigFile", "save_encrypted", SAVE_ENCRYPTED_HASH)
        }

        private const val SAVE_ENCRYPTED_PASS_HASH = 852856452L
        private val saveEncryptedPassBind by lazy {
            ObjectCalls.getMethodBind("ConfigFile", "save_encrypted_pass", SAVE_ENCRYPTED_PASS_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("ConfigFile", "clear", CLEAR_HASH)
        }
    }
}
