package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Provides access to the Java Native Interface.
 *
 * Generated from Godot docs: JavaClassWrapper
 */
object JavaClassWrapper {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("JavaClassWrapper")
    }

    @JvmStatic
    /**
     * Wraps a class defined in Java, and returns it as a `JavaClass` `Object` type that Godot can
     * interact with. When wrapping inner (nested) classes, use `$` instead of `.` to separate them.
     * For example, `JavaClassWrapper.wrap("android.view.WindowManager$LayoutParams")` wraps the
     * WindowManager.LayoutParams class. Note: To invoke a constructor, call a method with the same
     * name as the class. For example:
     *
     * Generated from Godot docs: JavaClassWrapper.wrap
     */
    fun wrap(name: String): JavaClass? {
        return JavaClass.wrap(ObjectCalls.ptrcallWithStringArgRetObject(wrapBind, singleton, name))
    }

    @JvmStatic
    fun getException(): JavaObject? {
        return JavaObject.wrap(ObjectCalls.ptrcallNoArgsRetObject(getExceptionBind, singleton))
    }

    @JvmStatic
    fun createSamCallback(samInterface: String, callable: GodotCallable): JavaObject? {
        return JavaObject.wrap(ObjectCalls.ptrcallWithStringCallableArgsRetObject(createSamCallbackBind, singleton, samInterface, callable.target.handle, callable.method))
    }

    @JvmStatic
    fun createProxy(objectValue: GodotObject, interfaces: List<String>): JavaObject? {
        return JavaObject.wrap(ObjectCalls.ptrcallWithObjectAndPackedStringListArgsRetObject(createProxyBind, singleton, objectValue.handle, interfaces))
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): JavaClassWrapper? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): JavaClassWrapper? =
        if (handle.address() == 0L) null else this

    private const val WRAP_HASH = 1124367868L
    private val wrapBind by lazy {
        ObjectCalls.getMethodBind("JavaClassWrapper", "wrap", WRAP_HASH)
    }

    private const val GET_EXCEPTION_HASH = 3277089691L
    private val getExceptionBind by lazy {
        ObjectCalls.getMethodBind("JavaClassWrapper", "get_exception", GET_EXCEPTION_HASH)
    }

    private const val CREATE_SAM_CALLBACK_HASH = 2479014754L
    private val createSamCallbackBind by lazy {
        ObjectCalls.getMethodBind("JavaClassWrapper", "create_sam_callback", CREATE_SAM_CALLBACK_HASH)
    }

    private const val CREATE_PROXY_HASH = 2694931752L
    private val createProxyBind by lazy {
        ObjectCalls.getMethodBind("JavaClassWrapper", "create_proxy", CREATE_PROXY_HASH)
    }
}
