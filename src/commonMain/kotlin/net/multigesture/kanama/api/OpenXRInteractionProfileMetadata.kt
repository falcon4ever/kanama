package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Meta class registering supported devices in OpenXR.
 *
 * Generated from Godot docs: OpenXRInteractionProfileMetadata
 */
class OpenXRInteractionProfileMetadata(handle: GodotHandle) : GodotObject(handle) {
    /**
     * Allows for renaming old interaction profile paths to new paths in order to load and process
     * older action maps.
     *
     * Generated from Godot docs: OpenXRInteractionProfileMetadata.register_profile_rename
     */
    fun registerProfileRename(oldName: String, newName: String) {
        ObjectCalls.ptrcallWithTwoStringArgs(Binds.registerProfileRenameBind, segment, oldName, newName)
    }

    /**
     * Allows for renaming old input/output paths to new paths in order to load and process older
     * action maps.
     *
     * Generated from Godot docs: OpenXRInteractionProfileMetadata.register_path_rename
     */
    fun registerPathRename(oldName: String, newName: String) {
        ObjectCalls.ptrcallWithTwoStringArgs(Binds.registerPathRenameBind, segment, oldName, newName)
    }

    /**
     * Registers a top level path to which profiles can be bound. For instance `/user/hand/left` refers
     * to the bind point for the player's left hand. Extensions can register additional top level
     * paths, for instance a haptic vest extension might register `/user/body/vest`. `display_name` is
     * the name shown to the user. `openxr_path` is the top level path being registered.
     * `openxr_extension_names` is optional and ensures the top level path is only used if the
     * specified extension is available/enabled. When a top level path ends up being bound by OpenXR,
     * an `XRPositionalTracker` is instantiated to manage the state of the device.
     *
     * Generated from Godot docs: OpenXRInteractionProfileMetadata.register_top_level_path
     */
    fun registerTopLevelPath(displayName: String, openxrPath: String, openxrExtensionNames: String) {
        ObjectCalls.ptrcallWithThreeStringArgs(Binds.registerTopLevelPathBind, segment, displayName, openxrPath, openxrExtensionNames)
    }

    /**
     * Registers an interaction profile using its OpenXR designation (e.g.
     * `/interaction_profiles/khr/simple_controller` is the profile for OpenXR's simple controller
     * profile). `display_name` is the description shown to the user. `openxr_path` is the interaction
     * profile path being registered. `openxr_extension_names` optionally restricts this profile to the
     * given extension being enabled/available. If the extension is not available, the profile and all
     * related entries used in an action map are filtered out.
     *
     * Generated from Godot docs: OpenXRInteractionProfileMetadata.register_interaction_profile
     */
    fun registerInteractionProfile(displayName: String, openxrPath: String, openxrExtensionNames: String) {
        ObjectCalls.ptrcallWithThreeStringArgs(Binds.registerInteractionProfileBind, segment, displayName, openxrPath, openxrExtensionNames)
    }

    /**
     * Registers an input/output path for the given `interaction_profile`. The profile should
     * previously have been registered using `register_interaction_profile`. `display_name` is the
     * description shown to the user. `toplevel_path` specifies the bind path this input/output can be
     * bound to (e.g. `/user/hand/left` or `/user/hand/right`). `openxr_path` is the action
     * input/output being registered (e.g. `/user/hand/left/input/aim/pose`). `openxr_extension_names`
     * restricts this input/output to an enabled/available extension, this doesn't need to repeat the
     * extension on the profile but relates to overlapping extension (e.g. `XR_EXT_palm_pose` that
     * introduces `…/input/palm_ext/pose` input paths). `action_type` defines the type of input or
     * output provided by OpenXR.
     *
     * Generated from Godot docs: OpenXRInteractionProfileMetadata.register_io_path
     */
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
