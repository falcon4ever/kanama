package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import java.lang.foreign.MemorySegment

/**
 * Helper class to handle INI-style files.
 *
 * Generated from Godot docs: ConfigFile
 */
class ConfigFile(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Assigns a value to the specified key of the specified section. If either the section or the key
     * do not exist, they are created. Passing a `null` value deletes the specified key if it exists,
     * and deletes the section if it ends up empty once the key has been removed.
     *
     * Generated from Godot docs: ConfigFile.set_value
     */
    fun setValue(section: String, key: String, value: Any?) {
        ObjectCalls.ptrcallWithTwoStringAndVariantArg(setValueBind, handle, section, key, value)
    }

    /**
     * Returns the current value for the specified section and key. If either the section or the key do
     * not exist, the method returns the fallback `default` value. If `default` is not specified or set
     * to `null`, an error is also raised.
     *
     * Generated from Godot docs: ConfigFile.get_value
     */
    fun getValue(section: String, key: String, default: Any? = null): Any? {
        return ObjectCalls.ptrcallWithTwoStringAndVariantArgRetVariantScalar(getValueBind, handle, section, key, default)
    }

    /**
     * Returns `true` if the specified section exists.
     *
     * Generated from Godot docs: ConfigFile.has_section
     */
    fun hasSection(section: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasSectionBind, handle, section)
    }

    /**
     * Returns `true` if the specified section-key pair exists.
     *
     * Generated from Godot docs: ConfigFile.has_section_key
     */
    fun hasSectionKey(section: String, key: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringArgsRetBool(hasSectionKeyBind, handle, section, key)
    }

    /**
     * Returns an array of all defined section identifiers.
     *
     * Generated from Godot docs: ConfigFile.get_sections
     */
    fun getSections(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getSectionsBind, handle)
    }

    /**
     * Returns an array of all defined key identifiers in the specified section. Raises an error and
     * returns an empty array if the section does not exist.
     *
     * Generated from Godot docs: ConfigFile.get_section_keys
     */
    fun getSectionKeys(section: String): List<String> {
        return ObjectCalls.ptrcallWithStringArgRetPackedStringList(getSectionKeysBind, handle, section)
    }

    /**
     * Deletes the specified section along with all the key-value pairs inside. Raises an error if the
     * section does not exist.
     *
     * Generated from Godot docs: ConfigFile.erase_section
     */
    fun eraseSection(section: String) {
        ObjectCalls.ptrcallWithStringArg(eraseSectionBind, handle, section)
    }

    /**
     * Deletes the specified key in a section. Raises an error if either the section or the key do not
     * exist.
     *
     * Generated from Godot docs: ConfigFile.erase_section_key
     */
    fun eraseSectionKey(section: String, key: String) {
        ObjectCalls.ptrcallWithTwoStringArgs(eraseSectionKeyBind, handle, section, key)
    }

    /**
     * Loads the config file specified as a parameter. The file's contents are parsed and loaded in the
     * `ConfigFile` object which the method was called on. Returns `OK` on success, or one of the other
     * `Error` values if the operation failed.
     *
     * Generated from Godot docs: ConfigFile.load
     */
    fun load(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(loadBind, handle, path)
    }

    /**
     * Parses the passed string as the contents of a config file. The string is parsed and loaded in
     * the ConfigFile object which the method was called on. Returns `OK` on success, or one of the
     * other `Error` values if the operation failed.
     *
     * Generated from Godot docs: ConfigFile.parse
     */
    fun parse(data: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(parseBind, handle, data)
    }

    /**
     * Saves the contents of the `ConfigFile` object to the file specified as a parameter. The output
     * file uses an INI-style structure. Returns `OK` on success, or one of the other `Error` values if
     * the operation failed.
     *
     * Generated from Godot docs: ConfigFile.save
     */
    fun save(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(saveBind, handle, path)
    }

    /**
     * Obtain the text version of this config file (the same text that would be written to a file).
     *
     * Generated from Godot docs: ConfigFile.encode_to_text
     */
    fun encodeToText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(encodeToTextBind, handle)
    }

    /**
     * Loads the encrypted config file specified as a parameter, using the provided `key` to decrypt
     * it. The file's contents are parsed and loaded in the `ConfigFile` object which the method was
     * called on. Returns `OK` on success, or one of the other `Error` values if the operation failed.
     *
     * Generated from Godot docs: ConfigFile.load_encrypted
     */
    fun loadEncrypted(path: String, key: ByteArray): Long {
        return ObjectCalls.ptrcallWithStringAndByteArrayArgRetLong(loadEncryptedBind, handle, path, key)
    }

    /**
     * Loads the encrypted config file specified as a parameter, using the provided `password` to
     * decrypt it. The file's contents are parsed and loaded in the `ConfigFile` object which the
     * method was called on. Returns `OK` on success, or one of the other `Error` values if the
     * operation failed.
     *
     * Generated from Godot docs: ConfigFile.load_encrypted_pass
     */
    fun loadEncryptedPass(path: String, password: String): Long {
        return ObjectCalls.ptrcallWithTwoStringArgsRetLong(loadEncryptedPassBind, handle, path, password)
    }

    /**
     * Saves the contents of the `ConfigFile` object to the AES-256 encrypted file specified as a
     * parameter, using the provided `key` to encrypt it. The output file uses an INI-style structure.
     * Returns `OK` on success, or one of the other `Error` values if the operation failed.
     *
     * Generated from Godot docs: ConfigFile.save_encrypted
     */
    fun saveEncrypted(path: String, key: ByteArray): Long {
        return ObjectCalls.ptrcallWithStringAndByteArrayArgRetLong(saveEncryptedBind, handle, path, key)
    }

    /**
     * Saves the contents of the `ConfigFile` object to the AES-256 encrypted file specified as a
     * parameter, using the provided `password` to encrypt it. The output file uses an INI-style
     * structure. Returns `OK` on success, or one of the other `Error` values if the operation failed.
     *
     * Generated from Godot docs: ConfigFile.save_encrypted_pass
     */
    fun saveEncryptedPass(path: String, password: String): Long {
        return ObjectCalls.ptrcallWithTwoStringArgsRetLong(saveEncryptedPassBind, handle, path, password)
    }

    /**
     * Removes the entire contents of the config.
     *
     * Generated from Godot docs: ConfigFile.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    companion object {
        @JvmStatic
        fun create(): ConfigFile =
            ConfigFile(ObjectCalls.constructObject("ConfigFile"))

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
