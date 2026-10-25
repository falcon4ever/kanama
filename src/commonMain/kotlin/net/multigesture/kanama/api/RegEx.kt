package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Class for searching text for patterns using regular expressions.
 *
 * Generated from Godot docs: RegEx
 */
class RegEx(handle: GodotHandle) : RefCounted(handle) {
    /**
     * This method resets the state of the object, as if it was freshly created. Namely, it unassigns
     * the regular expression of this object.
     *
     * Generated from Godot docs: RegEx.clear
     */
    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearBind, segment)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Compiles and assign the search pattern to use. Returns `GodotError.OK` if the compilation is
     * successful. If compilation fails, returns `GodotError.FAILED` and when `show_error` is `true`,
     * details are printed to standard output.
     *
     * Generated from Godot docs: RegEx.compile
     */
    fun compile(pattern: String, showError: Boolean = true): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithStringAndBoolArgRetLong(Binds.compileBind, segment, pattern, showError))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Searches the text for the compiled pattern. Returns a `RegExMatch` container of the first
     * matching result if found, otherwise `null`. The region to search within can be specified with
     * `offset` and `end`. This is useful when searching for another match in the same `subject` by
     * calling this method again after a previous success. Note that setting these parameters differs
     * from passing over a shortened string. For example, the start anchor `^` is not affected by
     * `offset`, and the character before `offset` will be checked for the word boundary `\b`.
     *
     * Generated from Godot docs: RegEx.search
     */
    fun search(subject: String, offset: Int = 0, end: Int = -1): RegExMatch? {
        checkOpen()
        val ret = RegExMatch.wrapOwned(ObjectCalls.ptrcallWithStringAndTwoIntArgsRetObject(Binds.searchBind, segment, subject, offset, end))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Searches the text for the compiled pattern. Returns an array of `RegExMatch` containers for each
     * non-overlapping result. If no results were found, an empty array is returned instead. The region
     * to search within can be specified with `offset` and `end`. This is useful when searching for
     * another match in the same `subject` by calling this method again after a previous success. Note
     * that setting these parameters differs from passing over a shortened string. For example, the
     * start anchor `^` is not affected by `offset`, and the character before `offset` will be checked
     * for the word boundary `\b`.
     *
     * Generated from Godot docs: RegEx.search_all
     */
    fun searchAll(subject: String, offset: Int = 0, end: Int = -1): List<RegExMatch> {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringTwoIntArgsRetTypedObjectList(Binds.searchAllBind, segment, subject, offset, end, RegExMatch::wrapBorrowed)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Searches the text for the compiled pattern and replaces it with the specified string. Escapes
     * and backreferences such as `$1` and `$name` are expanded and resolved. By default, only the
     * first instance is replaced, but it can be changed for all instances (global replacement). The
     * region to search within can be specified with `offset` and `end`. This is useful when searching
     * for another match in the same `subject` by calling this method again after a previous success.
     * Note that setting these parameters differs from passing over a shortened string. For example,
     * the start anchor `^` is not affected by `offset`, and the character before `offset` will be
     * checked for the word boundary `\b`.
     *
     * Generated from Godot docs: RegEx.sub
     */
    fun sub(subject: String, replacement: String, all: Boolean = false, offset: Int = 0, end: Int = -1): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithTwoStringBoolTwoIntArgsRetString(Binds.subBind, segment, subject, replacement, all, offset, end)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns whether this object has a valid search pattern assigned.
     *
     * Generated from Godot docs: RegEx.is_valid
     */
    fun isValid(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isValidBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the original search pattern that was compiled.
     *
     * Generated from Godot docs: RegEx.get_pattern
     */
    fun getPattern(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getPatternBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the number of capturing groups in compiled pattern.
     *
     * Generated from Godot docs: RegEx.get_group_count
     */
    fun getGroupCount(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getGroupCountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns an array of names of named capturing groups in the compiled pattern. They are ordered by
     * appearance.
     *
     * Generated from Godot docs: RegEx.get_names
     */
    fun getNames(): List<String> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getNamesBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        /**
         * Creates and compiles a new `RegEx` object. See also `compile`.
         *
         * Generated from Godot docs: RegEx.create_from_string
         */
        fun createFromString(pattern: String, showError: Boolean = true): RegEx? {
            return RegEx.wrapOwned(ObjectCalls.ptrcallWithStringAndBoolArgRetObject(Binds.createFromStringBind, NULL_SEGMENT, pattern, showError))
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): RegEx? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): RegEx? =
            if (handle.address() == 0L) null else RefCounted.owned(RegEx(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): RegEx? =
            if (handle.address() == 0L) null else RegEx(GodotHandle(handle))

        /** A new RegEx, GDScript's `RegEx.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): RegEx =
            RefCounted.owned(RegEx(GodotHandle(ObjectCalls.constructObject("RegEx"))))
    }

    private object Binds {
        private const val CREATE_FROM_STRING_HASH = 4249111514L
        @JvmField
        val createFromStringBind =
            ObjectCalls.getMethodBind("RegEx", "create_from_string", CREATE_FROM_STRING_HASH)

        private const val CLEAR_HASH = 3218959716L
        @JvmField
        val clearBind =
            ObjectCalls.getMethodBind("RegEx", "clear", CLEAR_HASH)

        private const val COMPILE_HASH = 3565188097L
        @JvmField
        val compileBind =
            ObjectCalls.getMethodBind("RegEx", "compile", COMPILE_HASH)

        private const val SEARCH_HASH = 3365977994L
        @JvmField
        val searchBind =
            ObjectCalls.getMethodBind("RegEx", "search", SEARCH_HASH)

        private const val SEARCH_ALL_HASH = 849021363L
        @JvmField
        val searchAllBind =
            ObjectCalls.getMethodBind("RegEx", "search_all", SEARCH_ALL_HASH)

        private const val SUB_HASH = 54019702L
        @JvmField
        val subBind =
            ObjectCalls.getMethodBind("RegEx", "sub", SUB_HASH)

        private const val IS_VALID_HASH = 36873697L
        @JvmField
        val isValidBind =
            ObjectCalls.getMethodBind("RegEx", "is_valid", IS_VALID_HASH)

        private const val GET_PATTERN_HASH = 201670096L
        @JvmField
        val getPatternBind =
            ObjectCalls.getMethodBind("RegEx", "get_pattern", GET_PATTERN_HASH)

        private const val GET_GROUP_COUNT_HASH = 3905245786L
        @JvmField
        val getGroupCountBind =
            ObjectCalls.getMethodBind("RegEx", "get_group_count", GET_GROUP_COUNT_HASH)

        private const val GET_NAMES_HASH = 1139954409L
        @JvmField
        val getNamesBind =
            ObjectCalls.getMethodBind("RegEx", "get_names", GET_NAMES_HASH)
    }
}
