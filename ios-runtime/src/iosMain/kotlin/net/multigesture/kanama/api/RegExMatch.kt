package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: RegExMatch
 */
class RegExMatch(handle: MemorySegment) : RefCounted(handle) {
    val subject: String
        @JvmName("subjectProperty")
        get() = getSubject()

    val strings: List<String>
        @JvmName("stringsProperty")
        get() = getStrings()

    fun getSubject(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getSubjectBind, handle)
    }

    fun getGroupCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getGroupCountBind, handle)
    }

    fun getStrings(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getStringsBind, handle)
    }

    companion object {
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

        private const val GET_STRINGS_HASH = 1139954409L
        private val getStringsBind by lazy {
            ObjectCalls.getMethodBind("RegExMatch", "get_strings", GET_STRINGS_HASH)
        }
    }
}
