package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: RegEx
 */
class RegEx(handle: GodotHandle) : RefCounted(handle) {
    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearBind, segment)
    }

    fun compile(pattern: String, showError: Boolean = true): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringAndBoolArgRetLong(Binds.compileBind, segment, pattern, showError))
    }

    fun search(subject: String, offset: Int = 0, end: Int = -1): RegExMatch? {
        checkOpen()
        return RegExMatch.wrapOwned(ObjectCalls.ptrcallWithStringAndTwoIntArgsRetObject(Binds.searchBind, segment, subject, offset, end))
    }

    fun searchAll(subject: String, offset: Int = 0, end: Int = -1): List<RegExMatch> {
        checkOpen()
        return ObjectCalls.ptrcallWithStringTwoIntArgsRetTypedObjectList(Binds.searchAllBind, segment, subject, offset, end, RegExMatch::wrapBorrowed)
    }

    fun sub(subject: String, replacement: String, all: Boolean = false, offset: Int = 0, end: Int = -1): String {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoStringBoolTwoIntArgsRetString(Binds.subBind, segment, subject, replacement, all, offset, end)
    }

    fun isValid(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isValidBind, segment)
    }

    fun getPattern(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getPatternBind, segment)
    }

    fun getGroupCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getGroupCountBind, segment)
    }

    fun getNames(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getNamesBind, segment)
    }

    companion object {
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
