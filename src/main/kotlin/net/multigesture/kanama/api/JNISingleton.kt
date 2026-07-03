package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Singleton that connects the engine with Android plugins to interface with native Android code.
 *
 * Generated from Godot docs: JNISingleton
 */
class JNISingleton(handle: MemorySegment) : GodotObject(handle) {
    /**
     * Returns `true` if the given `method` name exists in the JNISingleton's Java methods.
     *
     * Generated from Godot docs: JNISingleton.has_java_method
     */
    fun hasJavaMethod(method: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasJavaMethodBind, handle, method)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): JNISingleton? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): JNISingleton? =
            if (handle.address() == 0L) null else JNISingleton(handle)

        private const val HAS_JAVA_METHOD_HASH = 2619796661L
        private val hasJavaMethodBind by lazy {
            ObjectCalls.getMethodBind("JNISingleton", "has_java_method", HAS_JAVA_METHOD_HASH)
        }
    }
}
