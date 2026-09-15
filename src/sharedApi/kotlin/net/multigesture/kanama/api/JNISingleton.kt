package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Singleton that connects the engine with Android plugins to interface with native Android code.
 *
 * Generated from Godot docs: JNISingleton
 */
class JNISingleton(handle: GodotHandle) : GodotObject(handle) {
    /**
     * Returns `true` if the given `method` name exists in the JNISingleton's Java methods.
     *
     * Generated from Godot docs: JNISingleton.has_java_method
     */
    fun hasJavaMethod(method: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasJavaMethodBind, segment, method)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): JNISingleton? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): JNISingleton? =
            if (handle.address() == 0L) null else JNISingleton(GodotHandle(handle))

        private const val HAS_JAVA_METHOD_HASH = 2619796661L
        private val hasJavaMethodBind by lazy {
            ObjectCalls.getMethodBind("JNISingleton", "has_java_method", HAS_JAVA_METHOD_HASH)
        }
    }
}
