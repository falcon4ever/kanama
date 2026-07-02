package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: RegExMatch
 */
class RegExMatch(handle: MemorySegment) : RefCounted(handle) {
    val subject: String
        @JvmName("subjectProperty")
        get() = getSubject()

    val names: Map<String, Any?>
        @JvmName("namesProperty")
        get() = getNames()

    val strings: List<String>
        @JvmName("stringsProperty")
        get() = getStrings()

    fun getSubject(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSubjectBind, handle)
    }

    fun getGroupCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getGroupCountBind, handle)
    }

    fun getNames(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getNamesBind, handle)
    }

    fun getStrings(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getStringsBind, handle)
    }

    fun getString(name: Any?): String {
        return ObjectCalls.ptrcallWithVariantArgRetString(getStringBind, handle, name)
    }

    fun getStart(name: Any?): Int {
        return ObjectCalls.ptrcallWithVariantArgRetInt(getStartBind, handle, name)
    }

    fun getEnd(name: Any?): Int {
        return ObjectCalls.ptrcallWithVariantArgRetInt(getEndBind, handle, name)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RegExMatch? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RegExMatch? =
            if (handle.address() == 0L) null else RegExMatch(handle)

        private const val GET_SUBJECT_HASH = 201670096L
        private val getSubjectBind by lazy {
            ObjectCalls.getMethodBind("RegExMatch", "get_subject", GET_SUBJECT_HASH)
        }

        private const val GET_GROUP_COUNT_HASH = 3905245786L
        private val getGroupCountBind by lazy {
            ObjectCalls.getMethodBind("RegExMatch", "get_group_count", GET_GROUP_COUNT_HASH)
        }

        private const val GET_NAMES_HASH = 3102165223L
        private val getNamesBind by lazy {
            ObjectCalls.getMethodBind("RegExMatch", "get_names", GET_NAMES_HASH)
        }

        private const val GET_STRINGS_HASH = 1139954409L
        private val getStringsBind by lazy {
            ObjectCalls.getMethodBind("RegExMatch", "get_strings", GET_STRINGS_HASH)
        }

        private const val GET_STRING_HASH = 687115856L
        private val getStringBind by lazy {
            ObjectCalls.getMethodBind("RegExMatch", "get_string", GET_STRING_HASH)
        }

        private const val GET_START_HASH = 490464691L
        private val getStartBind by lazy {
            ObjectCalls.getMethodBind("RegExMatch", "get_start", GET_START_HASH)
        }

        private const val GET_END_HASH = 490464691L
        private val getEndBind by lazy {
            ObjectCalls.getMethodBind("RegExMatch", "get_end", GET_END_HASH)
        }
    }
}
