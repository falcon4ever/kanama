package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRAndroidThreadSettingsExtension
 */
class OpenXRAndroidThreadSettingsExtension(handle: GodotHandle) : OpenXRExtensionWrapper(handle) {
    fun setApplicationThreadType(threadType: OpenXRAndroidThreadSettingsExtension.ThreadType, threadId: Long = 0L): Boolean {
        return ObjectCalls.ptrcallWithLongAndUInt32ArgRetBool(setApplicationThreadTypeBind, segment, threadType.value, threadId)
    }

    @JvmInline
    value class ThreadType(override val value: Long) : GodotEnumValue {
        companion object {
            val APPLICATION_MAIN: ThreadType get() = ThreadType(0L)
            val APPLICATION_WORKER: ThreadType get() = ThreadType(1L)
            val RENDERER_MAIN: ThreadType get() = ThreadType(2L)
            val RENDERER_WORKER: ThreadType get() = ThreadType(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRAndroidThreadSettingsExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRAndroidThreadSettingsExtension? =
            if (handle.address() == 0L) null else OpenXRAndroidThreadSettingsExtension(GodotHandle(handle))

        private const val SET_APPLICATION_THREAD_TYPE_HASH = 1558751158L
        private val setApplicationThreadTypeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRAndroidThreadSettingsExtension", "set_application_thread_type", SET_APPLICATION_THREAD_TYPE_HASH)
        }
    }
}
