package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRAndroidThreadSettingsExtension
 */
class OpenXRAndroidThreadSettingsExtension(handle: MemorySegment) : OpenXRExtensionWrapper(handle) {
    fun setApplicationThreadType(threadType: Long, threadId: Long = 0L): Boolean {
        return ObjectCalls.ptrcallWithLongAndUInt32ArgRetBool(setApplicationThreadTypeBind, handle, threadType, threadId)
    }

    companion object {
        const val THREAD_TYPE_APPLICATION_MAIN: Long = 0L
        const val THREAD_TYPE_APPLICATION_WORKER: Long = 1L
        const val THREAD_TYPE_RENDERER_MAIN: Long = 2L
        const val THREAD_TYPE_RENDERER_WORKER: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRAndroidThreadSettingsExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRAndroidThreadSettingsExtension? =
            if (handle.address() == 0L) null else OpenXRAndroidThreadSettingsExtension(handle)

        private const val SET_APPLICATION_THREAD_TYPE_HASH = 1558751158L
        private val setApplicationThreadTypeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAndroidThreadSettingsExtension", "set_application_thread_type", SET_APPLICATION_THREAD_TYPE_HASH)
        }
    }
}
