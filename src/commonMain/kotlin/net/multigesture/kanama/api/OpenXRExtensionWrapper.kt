package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRExtensionWrapper
 */
open class OpenXRExtensionWrapper(handle: GodotHandle) : GodotObject(handle) {
    fun getOpenxrApi(): OpenXRAPIExtension? {
        return OpenXRAPIExtension.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getOpenxrApiBind, segment))
    }

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
