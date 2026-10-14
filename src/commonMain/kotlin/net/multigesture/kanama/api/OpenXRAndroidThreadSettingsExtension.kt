package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Wraps the XR_KHR_android_thread_settings
 * (https://registry.khronos.org/OpenXR/specs/1.1/html/xrspec.html#XR_KHR_android_thread_settings)
 * extension.
 *
 * Generated from Godot docs: OpenXRAndroidThreadSettingsExtension
 */
class OpenXRAndroidThreadSettingsExtension(handle: GodotHandle) : OpenXRExtensionWrapper(handle) {
    /**
     * Sets the thread type of the given thread, so that the XR runtime can adjust its scheduling
     * priority accordingly. `thread_id` refers to the OS thread id (ie from `gettid()`). When
     * `thread_id` is `0`, it will set the thread type of the current thread. NOTE: The id returned by
     * `Thread.get_id` is incompatible with `thread_id`.
     *
     * Generated from Godot docs: OpenXRAndroidThreadSettingsExtension.set_application_thread_type
     */
    fun setApplicationThreadType(threadType: OpenXRAndroidThreadSettingsExtension.ThreadType, threadId: Long = 0L): Boolean {
        return ObjectCalls.ptrcallWithLongAndUInt32ArgRetBool(Binds.setApplicationThreadTypeBind, segment, threadType.value, threadId)
    }

    /**
     * Godot's `OpenXRAndroidThreadSettingsExtension.ThreadType` enum as a typed value: `.value` is the
     * raw number Godot uses, and the companion holds the named values
     * (`OpenXRAndroidThreadSettingsExtension.ThreadType.<NAME>`).
     *
     * Generated from Godot docs: OpenXRAndroidThreadSettingsExtension.ThreadType
     */
    @JvmInline
    value class ThreadType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Hints to the XR runtime that the thread is doing time critical CPU tasks.
             *
             * Generated from Godot docs: OpenXRAndroidThreadSettingsExtension.THREAD_TYPE_APPLICATION_MAIN
             */
            val APPLICATION_MAIN: ThreadType get() = ThreadType(0L)
            /**
             * Hints to the XR runtime that the thread is doing background CPU tasks.
             *
             * Generated from Godot docs: OpenXRAndroidThreadSettingsExtension.THREAD_TYPE_APPLICATION_WORKER
             */
            val APPLICATION_WORKER: ThreadType get() = ThreadType(1L)
            /**
             * Hints to the XR runtime that the thread is doing time critical graphics device tasks.
             *
             * Generated from Godot docs: OpenXRAndroidThreadSettingsExtension.THREAD_TYPE_RENDERER_MAIN
             */
            val RENDERER_MAIN: ThreadType get() = ThreadType(2L)
            /**
             * Hints to the XR runtime that the thread is doing background graphics device tasks.
             *
             * Generated from Godot docs: OpenXRAndroidThreadSettingsExtension.THREAD_TYPE_RENDERER_WORKER
             */
            val RENDERER_WORKER: ThreadType get() = ThreadType(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRAndroidThreadSettingsExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRAndroidThreadSettingsExtension? =
            if (handle.address() == 0L) null else OpenXRAndroidThreadSettingsExtension(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_APPLICATION_THREAD_TYPE_HASH = 1558751158L
        @JvmField
        val setApplicationThreadTypeBind =
            ObjectCalls.getMethodBind("OpenXRAndroidThreadSettingsExtension", "set_application_thread_type", SET_APPLICATION_THREAD_TYPE_HASH)
    }
}
