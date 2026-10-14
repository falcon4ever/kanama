package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Allows implementing OpenXR extensions with GDExtension.
 *
 * Generated from Godot docs: OpenXRExtensionWrapper
 */
open class OpenXRExtensionWrapper(handle: GodotHandle) : GodotObject(handle) {
    /**
     * Returns the created `OpenXRAPIExtension`, which can be used to access the OpenXR API.
     *
     * Generated from Godot docs: OpenXRExtensionWrapper.get_openxr_api
     */
    fun getOpenxrApi(): OpenXRAPIExtension? {
        return OpenXRAPIExtension.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getOpenxrApiBind, segment))
    }

    /**
     * Registers the extension. This should happen at core module initialization level. Note: This
     * cannot be called once OpenXR has been initialized.
     *
     * Generated from Godot docs: OpenXRExtensionWrapper.register_extension_wrapper
     */
    fun registerExtensionWrapper() {
        ObjectCalls.ptrcallNoArgs(Binds.registerExtensionWrapperBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRExtensionWrapper? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRExtensionWrapper? =
            if (handle.address() == 0L) null else OpenXRExtensionWrapper(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_OPENXR_API_HASH = 1637791613L
        @JvmField
        val getOpenxrApiBind =
            ObjectCalls.getMethodBind("OpenXRExtensionWrapper", "get_openxr_api", GET_OPENXR_API_HASH)

        private const val REGISTER_EXTENSION_WRAPPER_HASH = 3218959716L
        @JvmField
        val registerExtensionWrapperBind =
            ObjectCalls.getMethodBind("OpenXRExtensionWrapper", "register_extension_wrapper", REGISTER_EXTENSION_WRAPPER_HASH)
    }
}
