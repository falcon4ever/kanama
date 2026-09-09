package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: JavaClass
 */
class JavaClass(handle: MemorySegment) : RefCounted(handle) {
    fun getJavaClassName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getJavaClassNameBind, handle)
    }

    fun getJavaParentClass(): JavaClass? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(getJavaParentClassBind, handle)
        if (ret.address() == handle.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return JavaClass.wrap(ret)
    }

    fun hasJavaMethod(method: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasJavaMethodBind, handle, method)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): JavaClass? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): JavaClass? =
            if (handle.address() == 0L) null else JavaClass(handle)

        private const val GET_JAVA_CLASS_NAME_HASH = 201670096L
        private val getJavaClassNameBind by lazy {
            ObjectCalls.getMethodBind("JavaClass", "get_java_class_name", GET_JAVA_CLASS_NAME_HASH)
        }

        private const val GET_JAVA_PARENT_CLASS_HASH = 541536347L
        private val getJavaParentClassBind by lazy {
            ObjectCalls.getMethodBind("JavaClass", "get_java_parent_class", GET_JAVA_PARENT_CLASS_HASH)
        }

        private const val HAS_JAVA_METHOD_HASH = 2619796661L
        private val hasJavaMethodBind by lazy {
            ObjectCalls.getMethodBind("JavaClass", "has_java_method", HAS_JAVA_METHOD_HASH)
        }
    }
}
