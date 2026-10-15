package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Contains the results of a `RegEx` search.
 *
 * Generated from Godot docs: RegExMatch
 */
class RegExMatch(handle: GodotHandle) : RefCounted(handle) {
    val subject: String
        @JvmName("subjectProperty")
        get() = getSubject()

    val names: Map<String, Any?>
        @JvmName("namesProperty")
        get() = getNames()

    val strings: List<String>
        @JvmName("stringsProperty")
        get() = getStrings()

    /**
     * The source string used with the search pattern to find this matching result.
     *
     * Generated from Godot docs: RegExMatch.get_subject
     */
    fun getSubject(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getSubjectBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the number of capturing groups.
     *
     * Generated from Godot docs: RegExMatch.get_group_count
     */
    fun getGroupCount(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getGroupCountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * A dictionary of named groups and its corresponding group number. Only groups that were matched
     * are included. If multiple groups have the same name, that name would refer to the first matching
     * one.
     *
     * Generated from Godot docs: RegExMatch.get_names
     */
    fun getNames(): Map<String, Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDictionary(Binds.getNamesBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * An `Array` of the match and its capturing groups.
     *
     * Generated from Godot docs: RegExMatch.get_strings
     */
    fun getStrings(): List<String> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getStringsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the substring of the match from the source string. Capturing groups can be retrieved by
     * providing its group number as an integer or its string name (if it's a named group). The default
     * value of 0 refers to the whole pattern. Returns an empty string if the group did not match or
     * doesn't exist.
     *
     * Generated from Godot docs: RegExMatch.get_string
     */
    fun getString(name: Any?): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithVariantArgRetString(Binds.getStringBind, segment, name)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the starting position of the match within the source string. The starting position of
     * capturing groups can be retrieved by providing its group number as an integer or its string name
     * (if it's a named group). The default value of 0 refers to the whole pattern. Returns -1 if the
     * group did not match or doesn't exist.
     *
     * Generated from Godot docs: RegExMatch.get_start
     */
    fun getStart(name: Any?): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithVariantArgRetInt(Binds.getStartBind, segment, name)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the end position of the match within the source string. The end position of capturing
     * groups can be retrieved by providing its group number as an integer or its string name (if it's
     * a named group). The default value of 0 refers to the whole pattern. Returns -1 if the group did
     * not match or doesn't exist.
     *
     * Generated from Godot docs: RegExMatch.get_end
     */
    fun getEnd(name: Any?): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithVariantArgRetInt(Binds.getEndBind, segment, name)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RegExMatch? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): RegExMatch? =
            if (handle.address() == 0L) null else RefCounted.owned(RegExMatch(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): RegExMatch? =
            if (handle.address() == 0L) null else RegExMatch(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_SUBJECT_HASH = 201670096L
        @JvmField
        val getSubjectBind =
            ObjectCalls.getMethodBind("RegExMatch", "get_subject", GET_SUBJECT_HASH)

        private const val GET_GROUP_COUNT_HASH = 3905245786L
        @JvmField
        val getGroupCountBind =
            ObjectCalls.getMethodBind("RegExMatch", "get_group_count", GET_GROUP_COUNT_HASH)

        private const val GET_NAMES_HASH = 3102165223L
        @JvmField
        val getNamesBind =
            ObjectCalls.getMethodBind("RegExMatch", "get_names", GET_NAMES_HASH)

        private const val GET_STRINGS_HASH = 1139954409L
        @JvmField
        val getStringsBind =
            ObjectCalls.getMethodBind("RegExMatch", "get_strings", GET_STRINGS_HASH)

        private const val GET_STRING_HASH = 687115856L
        @JvmField
        val getStringBind =
            ObjectCalls.getMethodBind("RegExMatch", "get_string", GET_STRING_HASH)

        private const val GET_START_HASH = 490464691L
        @JvmField
        val getStartBind =
            ObjectCalls.getMethodBind("RegExMatch", "get_start", GET_START_HASH)

        private const val GET_END_HASH = 490464691L
        @JvmField
        val getEndBind =
            ObjectCalls.getMethodBind("RegExMatch", "get_end", GET_END_HASH)
    }
}
