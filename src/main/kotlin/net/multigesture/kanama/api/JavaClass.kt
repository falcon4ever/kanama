package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Represents a class from the Java Native Interface.
 *
 * Generated from Godot docs: JavaClass
 */
class JavaClass(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Returns the Java class name.
     *
     * Generated from Godot docs: JavaClass.get_java_class_name
     */
    fun getJavaClassName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getJavaClassNameBind, handle)
    }

    /**
     * Returns the object's Java methods and their signatures as an `Array` of dictionaries, in the
     * same format as `Object.get_method_list`.
     *
     * Generated from Godot docs: JavaClass.get_java_method_list
     */
    fun getJavaMethodList(): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallNoArgsRetDictionaryList(getJavaMethodListBind, handle)
    }

    /**
     * Returns a `JavaClass` representing the Java parent class of this class.
     *
     * Generated from Godot docs: JavaClass.get_java_parent_class
     */
    fun getJavaParentClass(): JavaClass? {
        return JavaClass.wrap(ObjectCalls.ptrcallNoArgsRetObject(getJavaParentClassBind, handle))
    }

    /**
     * Returns `true` if the given `method` name exists in the object's Java methods.
     *
     * Generated from Godot docs: JavaClass.has_java_method
     */
    fun hasJavaMethod(method: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasJavaMethodBind, handle, method)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): JavaClass? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): JavaClass? =
            if (handle.address() == 0L) null else JavaClass(handle)

        private const val GET_JAVA_CLASS_NAME_HASH = 201670096L
        private val getJavaClassNameBind by lazy {
            ObjectCalls.getMethodBind("JavaClass", "get_java_class_name", GET_JAVA_CLASS_NAME_HASH)
        }

        private const val GET_JAVA_METHOD_LIST_HASH = 3995934104L
        private val getJavaMethodListBind by lazy {
            ObjectCalls.getMethodBind("JavaClass", "get_java_method_list", GET_JAVA_METHOD_LIST_HASH)
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
