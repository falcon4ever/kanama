package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRInteractionProfileMetadata
 */
class OpenXRInteractionProfileMetadata(handle: GodotHandle) : GodotObject(handle) {
    fun registerProfileRename(oldName: String, newName: String) {
        ObjectCalls.ptrcallWithTwoStringArgs(Binds.registerProfileRenameBind, segment, oldName, newName)
    }

    fun registerPathRename(oldName: String, newName: String) {
        ObjectCalls.ptrcallWithTwoStringArgs(Binds.registerPathRenameBind, segment, oldName, newName)
    }

    fun registerTopLevelPath(displayName: String, openxrPath: String, openxrExtensionNames: String) {
        ObjectCalls.ptrcallWithThreeStringArgs(Binds.registerTopLevelPathBind, segment, displayName, openxrPath, openxrExtensionNames)
    }

    fun registerInteractionProfile(displayName: String, openxrPath: String, openxrExtensionNames: String) {
        ObjectCalls.ptrcallWithThreeStringArgs(Binds.registerInteractionProfileBind, segment, displayName, openxrPath, openxrExtensionNames)
    }

    fun registerIoPath(interactionProfile: String, displayName: String, toplevelPath: String, openxrPath: String, openxrExtensionNames: String, actionType: OpenXRAction.ActionType) {
        ObjectCalls.ptrcallWithStringStringStringStringStringLongArgs(Binds.registerIoPathBind, segment, interactionProfile, displayName, toplevelPath, openxrPath, openxrExtensionNames, actionType.value)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRInteractionProfileMetadata? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRInteractionProfileMetadata? =
            if (handle.address() == 0L) null else OpenXRInteractionProfileMetadata(GodotHandle(handle))
    }

    private object Binds {
        private const val REGISTER_PROFILE_RENAME_HASH = 3186203200L
        @JvmField
        val registerProfileRenameBind =
            ObjectCalls.getMethodBind("OpenXRInteractionProfileMetadata", "register_profile_rename", REGISTER_PROFILE_RENAME_HASH)

        private const val REGISTER_PATH_RENAME_HASH = 3186203200L
        @JvmField
        val registerPathRenameBind =
            ObjectCalls.getMethodBind("OpenXRInteractionProfileMetadata", "register_path_rename", REGISTER_PATH_RENAME_HASH)

        private const val REGISTER_TOP_LEVEL_PATH_HASH = 254767734L
        @JvmField
        val registerTopLevelPathBind =
            ObjectCalls.getMethodBind("OpenXRInteractionProfileMetadata", "register_top_level_path", REGISTER_TOP_LEVEL_PATH_HASH)

        private const val REGISTER_INTERACTION_PROFILE_HASH = 254767734L
        @JvmField
        val registerInteractionProfileBind =
            ObjectCalls.getMethodBind("OpenXRInteractionProfileMetadata", "register_interaction_profile", REGISTER_INTERACTION_PROFILE_HASH)

        private const val REGISTER_IO_PATH_HASH = 3443511926L
        @JvmField
        val registerIoPathBind =
            ObjectCalls.getMethodBind("OpenXRInteractionProfileMetadata", "register_io_path", REGISTER_IO_PATH_HASH)
    }
}
