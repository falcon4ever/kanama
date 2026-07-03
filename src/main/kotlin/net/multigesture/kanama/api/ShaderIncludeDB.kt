package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Internal database of built in shader include files.
 *
 * Generated from Godot docs: ShaderIncludeDB
 */
class ShaderIncludeDB(handle: MemorySegment) : GodotObject(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        /**
         * Returns a list of built-in include files that are currently registered.
         *
         * Generated from Godot docs: ShaderIncludeDB.list_built_in_include_files
         */
        fun listBuiltInIncludeFiles(): List<String> {
            return ObjectCalls.ptrcallNoArgsRetPackedStringList(listBuiltInIncludeFilesBind, MemorySegment.NULL)
        }

        /**
         * Returns `true` if an include file with this name exists.
         *
         * Generated from Godot docs: ShaderIncludeDB.has_built_in_include_file
         */
        fun hasBuiltInIncludeFile(filename: String): Boolean {
            return ObjectCalls.ptrcallWithStringArgRetBool(hasBuiltInIncludeFileBind, MemorySegment.NULL, filename)
        }

        /**
         * Returns the code for the built-in shader fragment. You can also access this in your shader code
         * through `#include "filename"`.
         *
         * Generated from Godot docs: ShaderIncludeDB.get_built_in_include_file
         */
        fun getBuiltInIncludeFile(filename: String): String {
            return ObjectCalls.ptrcallWithStringArgRetString(getBuiltInIncludeFileBind, MemorySegment.NULL, filename)
        }

        @JvmStatic
        fun fromHandle(handle: MemorySegment): ShaderIncludeDB? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ShaderIncludeDB? =
            if (handle.address() == 0L) null else ShaderIncludeDB(handle)

        private const val LIST_BUILT_IN_INCLUDE_FILES_HASH = 2981934095L
        private val listBuiltInIncludeFilesBind by lazy {
            ObjectCalls.getMethodBind("ShaderIncludeDB", "list_built_in_include_files", LIST_BUILT_IN_INCLUDE_FILES_HASH)
        }

        private const val HAS_BUILT_IN_INCLUDE_FILE_HASH = 2323990056L
        private val hasBuiltInIncludeFileBind by lazy {
            ObjectCalls.getMethodBind("ShaderIncludeDB", "has_built_in_include_file", HAS_BUILT_IN_INCLUDE_FILE_HASH)
        }

        private const val GET_BUILT_IN_INCLUDE_FILE_HASH = 1703090593L
        private val getBuiltInIncludeFileBind by lazy {
            ObjectCalls.getMethodBind("ShaderIncludeDB", "get_built_in_include_file", GET_BUILT_IN_INCLUDE_FILE_HASH)
        }
    }
}
