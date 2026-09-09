package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: RegEx
 */
class RegEx(handle: MemorySegment) : RefCounted(handle) {
    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun compile(pattern: String, showError: Boolean = true): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndBoolArgRetLong(compileBind, handle, pattern, showError)
    }

    fun search(subject: String, offset: Int = 0, end: Int = -1): RegExMatch? {
        checkOpen()
        return RegExMatch.wrap(ObjectCalls.ptrcallWithStringAndTwoIntArgsRetObject(searchBind, handle, subject, offset, end))
    }

    fun isValid(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isValidBind, handle)
    }

    fun getPattern(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getPatternBind, handle)
    }

    fun getGroupCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getGroupCountBind, handle)
    }

    fun getNames(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getNamesBind, handle)
    }

    companion object {
        fun createFromString(pattern: String, showError: Boolean = true): RegEx? {
            return RegEx.wrap(ObjectCalls.ptrcallWithStringAndBoolArgRetObject(createFromStringBind, MemorySegment.NULL, pattern, showError))
        }

        fun fromHandle(handle: MemorySegment): RegEx? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RegEx? =
            if (handle.address() == 0L) null else RegEx(handle)

        private const val CREATE_FROM_STRING_HASH = 4249111514L
        private val createFromStringBind by lazy {
            ObjectCalls.getMethodBind("RegEx", "create_from_string", CREATE_FROM_STRING_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("RegEx", "clear", CLEAR_HASH)
        }

        private const val COMPILE_HASH = 3565188097L
        private val compileBind by lazy {
            ObjectCalls.getMethodBind("RegEx", "compile", COMPILE_HASH)
        }

        private const val SEARCH_HASH = 3365977994L
        private val searchBind by lazy {
            ObjectCalls.getMethodBind("RegEx", "search", SEARCH_HASH)
        }

        private const val IS_VALID_HASH = 36873697L
        private val isValidBind by lazy {
            ObjectCalls.getMethodBind("RegEx", "is_valid", IS_VALID_HASH)
        }

        private const val GET_PATTERN_HASH = 201670096L
        private val getPatternBind by lazy {
            ObjectCalls.getMethodBind("RegEx", "get_pattern", GET_PATTERN_HASH)
        }

        private const val GET_GROUP_COUNT_HASH = 3905245786L
        private val getGroupCountBind by lazy {
            ObjectCalls.getMethodBind("RegEx", "get_group_count", GET_GROUP_COUNT_HASH)
        }

        private const val GET_NAMES_HASH = 1139954409L
        private val getNamesBind by lazy {
            ObjectCalls.getMethodBind("RegEx", "get_names", GET_NAMES_HASH)
        }
    }
}
