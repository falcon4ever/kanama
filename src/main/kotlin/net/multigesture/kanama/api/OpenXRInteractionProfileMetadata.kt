package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRInteractionProfileMetadata
 */
class OpenXRInteractionProfileMetadata(handle: MemorySegment) : GodotObject(handle) {
    fun registerProfileRename(oldName: String, newName: String) {
        ObjectCalls.ptrcallWithTwoStringArgs(registerProfileRenameBind, handle, oldName, newName)
    }

    fun registerPathRename(oldName: String, newName: String) {
        ObjectCalls.ptrcallWithTwoStringArgs(registerPathRenameBind, handle, oldName, newName)
    }

    fun registerTopLevelPath(displayName: String, openxrPath: String, openxrExtensionNames: String) {
        ObjectCalls.ptrcallWithThreeStringArgs(registerTopLevelPathBind, handle, displayName, openxrPath, openxrExtensionNames)
    }

    fun registerInteractionProfile(displayName: String, openxrPath: String, openxrExtensionNames: String) {
        ObjectCalls.ptrcallWithThreeStringArgs(registerInteractionProfileBind, handle, displayName, openxrPath, openxrExtensionNames)
    }

    fun registerIoPath(interactionProfile: String, displayName: String, toplevelPath: String, openxrPath: String, openxrExtensionNames: String, actionType: Long) {
        ObjectCalls.ptrcallWithStringStringStringStringStringLongArgs(registerIoPathBind, handle, interactionProfile, displayName, toplevelPath, openxrPath, openxrExtensionNames, actionType)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRInteractionProfileMetadata? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRInteractionProfileMetadata? =
            if (handle.address() == 0L) null else OpenXRInteractionProfileMetadata(handle)

        private const val REGISTER_PROFILE_RENAME_HASH = 3186203200L
        private val registerProfileRenameBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInteractionProfileMetadata", "register_profile_rename", REGISTER_PROFILE_RENAME_HASH)
        }

        private const val REGISTER_PATH_RENAME_HASH = 3186203200L
        private val registerPathRenameBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInteractionProfileMetadata", "register_path_rename", REGISTER_PATH_RENAME_HASH)
        }

        private const val REGISTER_TOP_LEVEL_PATH_HASH = 254767734L
        private val registerTopLevelPathBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInteractionProfileMetadata", "register_top_level_path", REGISTER_TOP_LEVEL_PATH_HASH)
        }

        private const val REGISTER_INTERACTION_PROFILE_HASH = 254767734L
        private val registerInteractionProfileBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInteractionProfileMetadata", "register_interaction_profile", REGISTER_INTERACTION_PROFILE_HASH)
        }

        private const val REGISTER_IO_PATH_HASH = 3443511926L
        private val registerIoPathBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInteractionProfileMetadata", "register_io_path", REGISTER_IO_PATH_HASH)
        }
    }
}
