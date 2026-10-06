package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Helper class to handle INI-style files.
 *
 * Generated from Godot docs: ConfigFile
 */
class ConfigFile(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Assigns a value to the specified key of the specified section. If either the section or the key
     * do not exist, they are created. Passing a `null` value deletes the specified key if it exists,
     * and deletes the section if it ends up empty once the key has been removed.
     *
     * Generated from Godot docs: ConfigFile.set_value
     */
    fun setValue(section: String, key: String, value: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoStringAndVariantArg(Binds.setValueBind, segment, section, key, value)
    }

    /**
     * Returns the current value for the specified section and key. If either the section or the key do
     * not exist, the method returns the fallback `default` value. If `default` is not specified or set
     * to `null`, an error is also raised.
     *
     * Generated from Godot docs: ConfigFile.get_value
     */
    fun getValue(section: String, key: String, default: Any? = null): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoStringAndVariantArgRetVariantScalar(Binds.getValueBind, segment, section, key, default)
    }

    /**
     * Returns `true` if the specified section exists.
     *
     * Generated from Godot docs: ConfigFile.has_section
     */
    fun hasSection(section: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(Binds.hasSectionBind, segment, section)
    }

    /**
     * Returns `true` if the specified section-key pair exists.
     *
     * Generated from Godot docs: ConfigFile.has_section_key
     */
    fun hasSectionKey(section: String, key: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoStringArgsRetBool(Binds.hasSectionKeyBind, segment, section, key)
    }

    /**
     * Returns an array of all defined section identifiers.
     *
     * Generated from Godot docs: ConfigFile.get_sections
     */
    fun getSections(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getSectionsBind, segment)
    }

    /**
     * Returns an array of all defined key identifiers in the specified section. Raises an error and
     * returns an empty array if the section does not exist.
     *
     * Generated from Godot docs: ConfigFile.get_section_keys
     */
    fun getSectionKeys(section: String): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetPackedStringList(Binds.getSectionKeysBind, segment, section)
    }

    /**
     * Deletes the specified section along with all the key-value pairs inside. Raises an error if the
     * section does not exist.
     *
     * Generated from Godot docs: ConfigFile.erase_section
     */
    fun eraseSection(section: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.eraseSectionBind, segment, section)
    }

    /**
     * Deletes the specified key in a section. Raises an error if either the section or the key do not
     * exist.
     *
     * Generated from Godot docs: ConfigFile.erase_section_key
     */
    fun eraseSectionKey(section: String, key: String) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoStringArgs(Binds.eraseSectionKeyBind, segment, section, key)
    }

    /**
     * Loads the config file specified as a parameter. The file's contents are parsed and loaded in the
     * `ConfigFile` object which the method was called on. Returns `GodotError.OK` on success, or one
     * of the other `Error` values if the operation failed.
     *
     * Generated from Godot docs: ConfigFile.load
     */
    fun load(path: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.loadBind, segment, path))
    }

    /**
     * Parses the passed string as the contents of a config file. The string is parsed and loaded in
     * the ConfigFile object which the method was called on. Returns `GodotError.OK` on success, or one
     * of the other `Error` values if the operation failed.
     *
     * Generated from Godot docs: ConfigFile.parse
     */
    fun parse(data: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.parseBind, segment, data))
    }

    /**
     * Saves the contents of the `ConfigFile` object to the file specified as a parameter. The output
     * file uses an INI-style structure. Returns `GodotError.OK` on success, or one of the other
     * `Error` values if the operation failed.
     *
     * Generated from Godot docs: ConfigFile.save
     */
    fun save(path: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.saveBind, segment, path))
    }

    /**
     * Obtain the text version of this config file (the same text that would be written to a file).
     *
     * Generated from Godot docs: ConfigFile.encode_to_text
     */
    fun encodeToText(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.encodeToTextBind, segment)
    }

    /**
     * Loads the encrypted config file specified as a parameter, using the provided `key` to decrypt
     * it. The file's contents are parsed and loaded in the `ConfigFile` object which the method was
     * called on. Returns `GodotError.OK` on success, or one of the other `Error` values if the
     * operation failed.
     *
     * Generated from Godot docs: ConfigFile.load_encrypted
     */
    fun loadEncrypted(path: String, key: ByteArray): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringAndByteArrayArgRetLong(Binds.loadEncryptedBind, segment, path, key))
    }

    /**
     * Loads the encrypted config file specified as a parameter, using the provided `password` to
     * decrypt it. The file's contents are parsed and loaded in the `ConfigFile` object which the
     * method was called on. Returns `GodotError.OK` on success, or one of the other `Error` values if
     * the operation failed.
     *
     * Generated from Godot docs: ConfigFile.load_encrypted_pass
     */
    fun loadEncryptedPass(path: String, password: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithTwoStringArgsRetLong(Binds.loadEncryptedPassBind, segment, path, password))
    }

    /**
     * Saves the contents of the `ConfigFile` object to the AES-256 encrypted file specified as a
     * parameter, using the provided `key` to encrypt it. The output file uses an INI-style structure.
     * Returns `GodotError.OK` on success, or one of the other `Error` values if the operation failed.
     *
     * Generated from Godot docs: ConfigFile.save_encrypted
     */
    fun saveEncrypted(path: String, key: ByteArray): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringAndByteArrayArgRetLong(Binds.saveEncryptedBind, segment, path, key))
    }

    /**
     * Saves the contents of the `ConfigFile` object to the AES-256 encrypted file specified as a
     * parameter, using the provided `password` to encrypt it. The output file uses an INI-style
     * structure. Returns `GodotError.OK` on success, or one of the other `Error` values if the
     * operation failed.
     *
     * Generated from Godot docs: ConfigFile.save_encrypted_pass
     */
    fun saveEncryptedPass(path: String, password: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithTwoStringArgsRetLong(Binds.saveEncryptedPassBind, segment, path, password))
    }

    /**
     * Removes the entire contents of the config.
     *
     * Generated from Godot docs: ConfigFile.clear
     */
    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ConfigFile? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ConfigFile? =
            if (handle.address() == 0L) null else RefCounted.owned(ConfigFile(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ConfigFile? =
            if (handle.address() == 0L) null else ConfigFile(GodotHandle(handle))

        // Instantiate a ConfigFile.
        @JvmStatic
        fun create(): ConfigFile =
            RefCounted.owned(ConfigFile(GodotHandle(ObjectCalls.constructObject("ConfigFile"))))
    }

    private object Binds {
        private const val SET_VALUE_HASH = 2504492430L
        @JvmField
        val setValueBind =
            ObjectCalls.getMethodBind("ConfigFile", "set_value", SET_VALUE_HASH)

        private const val GET_VALUE_HASH = 89809366L
        @JvmField
        val getValueBind =
            ObjectCalls.getMethodBind("ConfigFile", "get_value", GET_VALUE_HASH)

        private const val HAS_SECTION_HASH = 3927539163L
        @JvmField
        val hasSectionBind =
            ObjectCalls.getMethodBind("ConfigFile", "has_section", HAS_SECTION_HASH)

        private const val HAS_SECTION_KEY_HASH = 820780508L
        @JvmField
        val hasSectionKeyBind =
            ObjectCalls.getMethodBind("ConfigFile", "has_section_key", HAS_SECTION_KEY_HASH)

        private const val GET_SECTIONS_HASH = 1139954409L
        @JvmField
        val getSectionsBind =
            ObjectCalls.getMethodBind("ConfigFile", "get_sections", GET_SECTIONS_HASH)

        private const val GET_SECTION_KEYS_HASH = 4291131558L
        @JvmField
        val getSectionKeysBind =
            ObjectCalls.getMethodBind("ConfigFile", "get_section_keys", GET_SECTION_KEYS_HASH)

        private const val ERASE_SECTION_HASH = 83702148L
        @JvmField
        val eraseSectionBind =
            ObjectCalls.getMethodBind("ConfigFile", "erase_section", ERASE_SECTION_HASH)

        private const val ERASE_SECTION_KEY_HASH = 3186203200L
        @JvmField
        val eraseSectionKeyBind =
            ObjectCalls.getMethodBind("ConfigFile", "erase_section_key", ERASE_SECTION_KEY_HASH)

        private const val LOAD_HASH = 166001499L
        @JvmField
        val loadBind =
            ObjectCalls.getMethodBind("ConfigFile", "load", LOAD_HASH)

        private const val PARSE_HASH = 166001499L
        @JvmField
        val parseBind =
            ObjectCalls.getMethodBind("ConfigFile", "parse", PARSE_HASH)

        private const val SAVE_HASH = 166001499L
        @JvmField
        val saveBind =
            ObjectCalls.getMethodBind("ConfigFile", "save", SAVE_HASH)

        private const val ENCODE_TO_TEXT_HASH = 201670096L
        @JvmField
        val encodeToTextBind =
            ObjectCalls.getMethodBind("ConfigFile", "encode_to_text", ENCODE_TO_TEXT_HASH)

        private const val LOAD_ENCRYPTED_HASH = 887037711L
        @JvmField
        val loadEncryptedBind =
            ObjectCalls.getMethodBind("ConfigFile", "load_encrypted", LOAD_ENCRYPTED_HASH)

        private const val LOAD_ENCRYPTED_PASS_HASH = 852856452L
        @JvmField
        val loadEncryptedPassBind =
            ObjectCalls.getMethodBind("ConfigFile", "load_encrypted_pass", LOAD_ENCRYPTED_PASS_HASH)

        private const val SAVE_ENCRYPTED_HASH = 887037711L
        @JvmField
        val saveEncryptedBind =
            ObjectCalls.getMethodBind("ConfigFile", "save_encrypted", SAVE_ENCRYPTED_HASH)

        private const val SAVE_ENCRYPTED_PASS_HASH = 852856452L
        @JvmField
        val saveEncryptedPassBind =
            ObjectCalls.getMethodBind("ConfigFile", "save_encrypted_pass", SAVE_ENCRYPTED_PASS_HASH)

        private const val CLEAR_HASH = 3218959716L
        @JvmField
        val clearBind =
            ObjectCalls.getMethodBind("ConfigFile", "clear", CLEAR_HASH)
    }
}
