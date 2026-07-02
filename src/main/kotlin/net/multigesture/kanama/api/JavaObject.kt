package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Represents an object from the Java Native Interface.
 *
 * Generated from Godot docs: JavaObject
 */
class JavaObject(handle: MemorySegment) : RefCounted(handle) {
    fun getJavaClass(): JavaClass? {
        return JavaClass.wrap(ObjectCalls.ptrcallNoArgsRetObject(getJavaClassBind, handle))
    }

    fun hasJavaMethod(method: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasJavaMethodBind, handle, method)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): JavaObject? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): JavaObject? =
            if (handle.address() == 0L) null else JavaObject(handle)

        private const val GET_JAVA_CLASS_HASH = 541536347L
        private val getJavaClassBind by lazy {
            ObjectCalls.getMethodBind("JavaObject", "get_java_class", GET_JAVA_CLASS_HASH)
        }

        private const val HAS_JAVA_METHOD_HASH = 2619796661L
        private val hasJavaMethodBind by lazy {
            ObjectCalls.getMethodBind("JavaObject", "has_java_method", HAS_JAVA_METHOD_HASH)
        }
    }
}
