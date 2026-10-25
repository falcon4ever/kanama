package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Generated from Godot docs: GLTFAnimation
 */
class GLTFAnimation(handle: GodotHandle) : Resource(handle) {
    var originalName: String
        @JvmName("originalNameProperty")
        get() = getOriginalName()
        @JvmName("setOriginalNameProperty")
        set(value) = setOriginalName(value)

    var loop: Boolean
        @JvmName("loopProperty")
        get() = getLoop()
        @JvmName("setLoopProperty")
        set(value) = setLoop(value)

    /**
     * The original name of the animation.
     *
     * Generated from Godot docs: GLTFAnimation.get_original_name
     */
    fun getOriginalName(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getOriginalNameBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The original name of the animation.
     *
     * Generated from Godot docs: GLTFAnimation.set_original_name
     */
    fun setOriginalName(originalName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setOriginalNameBind, segment, originalName)
        ObjectCalls.keepReachable(this)
    }

    fun getLoop(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.getLoopBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    fun setLoop(loop: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setLoopBind, segment, loop)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Gets additional arbitrary data in this `GLTFAnimation` instance. This can be used to keep
     * per-node state data in `GLTFDocumentExtension` classes, which is important because they are
     * stateless. The argument should be the `GLTFDocumentExtension` name (does not have to match the
     * extension name in the glTF file), and the return value can be anything you set. If nothing was
     * set, the return value is `null`.
     *
     * Generated from Godot docs: GLTFAnimation.get_additional_data
     */
    fun getAdditionalData(extensionName: String): Any? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(Binds.getAdditionalDataBind, segment, extensionName)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets additional arbitrary data in this `GLTFAnimation` instance. This can be used to keep
     * per-node state data in `GLTFDocumentExtension` classes, which is important because they are
     * stateless. The first argument should be the `GLTFDocumentExtension` name (does not have to match
     * the extension name in the glTF file), and the second argument can be anything you want.
     *
     * Generated from Godot docs: GLTFAnimation.set_additional_data
     */
    fun setAdditionalData(extensionName: String, additionalData: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameAndVariantArg(Binds.setAdditionalDataBind, segment, extensionName, additionalData)
        ObjectCalls.keepReachable(this)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GLTFAnimation? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GLTFAnimation? =
            if (handle.address() == 0L) null else RefCounted.owned(GLTFAnimation(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GLTFAnimation? =
            if (handle.address() == 0L) null else GLTFAnimation(GodotHandle(handle))

        /** A new GLTFAnimation, GDScript's `GLTFAnimation.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): GLTFAnimation =
            RefCounted.owned(GLTFAnimation(GodotHandle(requireConstructed(ObjectCalls.constructObject("GLTFAnimation"), "GLTFAnimation"))))
    }

    private object Binds {
        private const val GET_ORIGINAL_NAME_HASH = 2841200299L
        @JvmField
        val getOriginalNameBind =
            ObjectCalls.getMethodBind("GLTFAnimation", "get_original_name", GET_ORIGINAL_NAME_HASH)

        private const val SET_ORIGINAL_NAME_HASH = 83702148L
        @JvmField
        val setOriginalNameBind =
            ObjectCalls.getMethodBind("GLTFAnimation", "set_original_name", SET_ORIGINAL_NAME_HASH)

        private const val GET_LOOP_HASH = 36873697L
        @JvmField
        val getLoopBind =
            ObjectCalls.getMethodBind("GLTFAnimation", "get_loop", GET_LOOP_HASH)

        private const val SET_LOOP_HASH = 2586408642L
        @JvmField
        val setLoopBind =
            ObjectCalls.getMethodBind("GLTFAnimation", "set_loop", SET_LOOP_HASH)

        private const val GET_ADDITIONAL_DATA_HASH = 2138907829L
        @JvmField
        val getAdditionalDataBind =
            ObjectCalls.getMethodBind("GLTFAnimation", "get_additional_data", GET_ADDITIONAL_DATA_HASH)

        private const val SET_ADDITIONAL_DATA_HASH = 3776071444L
        @JvmField
        val setAdditionalDataBind =
            ObjectCalls.getMethodBind("GLTFAnimation", "set_additional_data", SET_ADDITIONAL_DATA_HASH)
    }
}
