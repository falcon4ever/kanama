package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: AudioStreamPlaybackInteractive
 */
class AudioStreamPlaybackInteractive(handle: GodotHandle) : AudioStreamPlayback(handle) {
    fun switchToClipByName(clipName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameArg(switchToClipByNameBind, segment, clipName)
    }

    fun switchToClip(clipIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(switchToClipBind, segment, clipIndex)
    }

    fun getCurrentClipIndex(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getCurrentClipIndexBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamPlaybackInteractive? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioStreamPlaybackInteractive? =
            if (handle.address() == 0L) null else AudioStreamPlaybackInteractive(GodotHandle(handle))

        private const val SWITCH_TO_CLIP_BY_NAME_HASH = 3304788590L
        private val switchToClipByNameBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlaybackInteractive", "switch_to_clip_by_name", SWITCH_TO_CLIP_BY_NAME_HASH)
        }

        private const val SWITCH_TO_CLIP_HASH = 1286410249L
        private val switchToClipBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlaybackInteractive", "switch_to_clip", SWITCH_TO_CLIP_HASH)
        }

        private const val GET_CURRENT_CLIP_INDEX_HASH = 3905245786L
        private val getCurrentClipIndexBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamPlaybackInteractive", "get_current_clip_index", GET_CURRENT_CLIP_INDEX_HASH)
        }
    }
}
