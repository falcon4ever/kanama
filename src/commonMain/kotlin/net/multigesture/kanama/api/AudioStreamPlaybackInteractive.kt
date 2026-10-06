package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: AudioStreamPlaybackInteractive
 */
class AudioStreamPlaybackInteractive(handle: GodotHandle) : AudioStreamPlayback(handle) {
    fun switchToClipByName(clipName: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameArg(Binds.switchToClipByNameBind, segment, clipName)
    }

    fun switchToClip(clipIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.switchToClipBind, segment, clipIndex)
    }

    fun getCurrentClipIndex(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getCurrentClipIndexBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamPlaybackInteractive? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioStreamPlaybackInteractive? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioStreamPlaybackInteractive(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioStreamPlaybackInteractive? =
            if (handle.address() == 0L) null else AudioStreamPlaybackInteractive(GodotHandle(handle))
    }

    private object Binds {
        private const val SWITCH_TO_CLIP_BY_NAME_HASH = 3304788590L
        @JvmField
        val switchToClipByNameBind =
            ObjectCalls.getMethodBind("AudioStreamPlaybackInteractive", "switch_to_clip_by_name", SWITCH_TO_CLIP_BY_NAME_HASH)

        private const val SWITCH_TO_CLIP_HASH = 1286410249L
        @JvmField
        val switchToClipBind =
            ObjectCalls.getMethodBind("AudioStreamPlaybackInteractive", "switch_to_clip", SWITCH_TO_CLIP_HASH)

        private const val GET_CURRENT_CLIP_INDEX_HASH = 3905245786L
        @JvmField
        val getCurrentClipIndexBind =
            ObjectCalls.getMethodBind("AudioStreamPlaybackInteractive", "get_current_clip_index", GET_CURRENT_CLIP_INDEX_HASH)
    }
}
