package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: ConfigFile
 */
class ConfigFile(handle: MemorySegment) : RefCounted(handle) {
    fun hasSection(section: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasSectionBind, handle, section)
    }

    fun hasSectionKey(section: String, key: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringArgsRetBool(hasSectionKeyBind, handle, section, key)
    }

    fun getSections(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getSectionsBind, handle)
    }

    fun eraseSection(section: String) {
        ObjectCalls.ptrcallWithStringArg(eraseSectionBind, handle, section)
    }

    fun eraseSectionKey(section: String, key: String) {
        ObjectCalls.ptrcallWithTwoStringArgs(eraseSectionKeyBind, handle, section, key)
    }

    fun load(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(loadBind, handle, path)
    }

    fun parse(data: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(parseBind, handle, data)
    }

    fun save(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(saveBind, handle, path)
    }

    fun encodeToText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(encodeToTextBind, handle)
    }

    fun loadEncryptedPass(path: String, password: String): Long {
        return ObjectCalls.ptrcallWithTwoStringArgsRetLong(loadEncryptedPassBind, handle, path, password)
    }

    fun saveEncryptedPass(path: String, password: String): Long {
        return ObjectCalls.ptrcallWithTwoStringArgsRetLong(saveEncryptedPassBind, handle, path, password)
    }

    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    // ConfigFile.set_value / get_value are (StringName, StringName, Variant) methods the generator
    // skips on iOS (Variant value); route through the call() Variant path. Matches desktop's
    // hand-written Variant-coercion helpers.
    fun setValue(section: String, key: String, value: Any?) {
        call("set_value", section, key, value)
    }

    fun getValue(section: String, key: String, default: Any? = null): Any? =
        call("get_value", section, key, default)

    companion object {
        fun fromHandle(handle: MemorySegment): ConfigFile? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ConfigFile? =
            if (handle.address() == 0L) null else ConfigFile(handle)

        // Instantiate a ConfigFile (RefCounted key/value store).
        fun create(): ConfigFile =
            ConfigFile(MemorySegment.ofAddress(IosGodot.constructObject("ConfigFile")))

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

        private const val LOAD_ENCRYPTED_PASS_HASH = 852856452L
        private val loadEncryptedPassBind by lazy {
            ObjectCalls.getMethodBind("ConfigFile", "load_encrypted_pass", LOAD_ENCRYPTED_PASS_HASH)
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
