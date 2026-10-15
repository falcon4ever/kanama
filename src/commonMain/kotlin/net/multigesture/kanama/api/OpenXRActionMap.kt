package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Collection of `OpenXRActionSet` and `OpenXRInteractionProfile` resources for the OpenXR module.
 *
 * Generated from Godot docs: OpenXRActionMap
 */
class OpenXRActionMap(handle: GodotHandle) : Resource(handle) {
    var actionSets: List<Any?>
        @JvmName("actionSetsProperty")
        get() = getActionSets()
        @JvmName("setActionSetsProperty")
        set(value) = setActionSets(value)

    var interactionProfiles: List<Any?>
        @JvmName("interactionProfilesProperty")
        get() = getInteractionProfiles()
        @JvmName("setInteractionProfilesProperty")
        set(value) = setInteractionProfiles(value)

    /**
     * Collection of `OpenXRActionSet`s that are part of this action map.
     *
     * Generated from Godot docs: OpenXRActionMap.set_action_sets
     */
    fun setActionSets(actionSets: List<Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithArrayArg(Binds.setActionSetsBind, segment, actionSets)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Collection of `OpenXRActionSet`s that are part of this action map.
     *
     * Generated from Godot docs: OpenXRActionMap.get_action_sets
     */
    fun getActionSets(): List<Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetArray(Binds.getActionSetsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Retrieve the number of actions sets in our action map.
     *
     * Generated from Godot docs: OpenXRActionMap.get_action_set_count
     */
    fun getActionSetCount(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getActionSetCountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Retrieve an action set by name.
     *
     * Generated from Godot docs: OpenXRActionMap.find_action_set
     */
    fun findActionSet(name: String): OpenXRActionSet? {
        checkOpen()
        val ret = OpenXRActionSet.wrapOwned(ObjectCalls.ptrcallWithStringArgRetObject(Binds.findActionSetBind, segment, name))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Retrieve the action set at this index.
     *
     * Generated from Godot docs: OpenXRActionMap.get_action_set
     */
    fun getActionSet(idx: Int): OpenXRActionSet? {
        checkOpen()
        val ret = OpenXRActionSet.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getActionSetBind, segment, idx))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Add an action set.
     *
     * Generated from Godot docs: OpenXRActionMap.add_action_set
     */
    fun addActionSet(actionSet: OpenXRActionSet?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.addActionSetBind, segment, listOf(actionSet?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(actionSet)
    }

    /**
     * Remove an action set.
     *
     * Generated from Godot docs: OpenXRActionMap.remove_action_set
     */
    fun removeActionSet(actionSet: OpenXRActionSet?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeActionSetBind, segment, listOf(actionSet?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(actionSet)
    }

    /**
     * Collection of `OpenXRInteractionProfile`s that are part of this action map.
     *
     * Generated from Godot docs: OpenXRActionMap.set_interaction_profiles
     */
    fun setInteractionProfiles(interactionProfiles: List<Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithArrayArg(Binds.setInteractionProfilesBind, segment, interactionProfiles)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Collection of `OpenXRInteractionProfile`s that are part of this action map.
     *
     * Generated from Godot docs: OpenXRActionMap.get_interaction_profiles
     */
    fun getInteractionProfiles(): List<Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetArray(Binds.getInteractionProfilesBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Retrieve the number of interaction profiles in our action map.
     *
     * Generated from Godot docs: OpenXRActionMap.get_interaction_profile_count
     */
    fun getInteractionProfileCount(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getInteractionProfileCountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Find an interaction profile by its name (path).
     *
     * Generated from Godot docs: OpenXRActionMap.find_interaction_profile
     */
    fun findInteractionProfile(name: String): OpenXRInteractionProfile? {
        checkOpen()
        val ret = OpenXRInteractionProfile.wrapOwned(ObjectCalls.ptrcallWithStringArgRetObject(Binds.findInteractionProfileBind, segment, name))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Get the interaction profile at this index.
     *
     * Generated from Godot docs: OpenXRActionMap.get_interaction_profile
     */
    fun getInteractionProfile(idx: Int): OpenXRInteractionProfile? {
        checkOpen()
        val ret = OpenXRInteractionProfile.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getInteractionProfileBind, segment, idx))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Add an interaction profile.
     *
     * Generated from Godot docs: OpenXRActionMap.add_interaction_profile
     */
    fun addInteractionProfile(interactionProfile: OpenXRInteractionProfile?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.addInteractionProfileBind, segment, listOf(interactionProfile?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(interactionProfile)
    }

    /**
     * Remove an interaction profile.
     *
     * Generated from Godot docs: OpenXRActionMap.remove_interaction_profile
     */
    fun removeInteractionProfile(interactionProfile: OpenXRInteractionProfile?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeInteractionProfileBind, segment, listOf(interactionProfile?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(interactionProfile)
    }

    /**
     * Setup this action set with our default actions.
     *
     * Generated from Godot docs: OpenXRActionMap.create_default_action_sets
     */
    fun createDefaultActionSets() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.createDefaultActionSetsBind, segment)
        ObjectCalls.keepReachable(this)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRActionMap? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRActionMap? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRActionMap(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRActionMap? =
            if (handle.address() == 0L) null else OpenXRActionMap(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_ACTION_SETS_HASH = 381264803L
        @JvmField
        val setActionSetsBind =
            ObjectCalls.getMethodBind("OpenXRActionMap", "set_action_sets", SET_ACTION_SETS_HASH)

        private const val GET_ACTION_SETS_HASH = 3995934104L
        @JvmField
        val getActionSetsBind =
            ObjectCalls.getMethodBind("OpenXRActionMap", "get_action_sets", GET_ACTION_SETS_HASH)

        private const val GET_ACTION_SET_COUNT_HASH = 3905245786L
        @JvmField
        val getActionSetCountBind =
            ObjectCalls.getMethodBind("OpenXRActionMap", "get_action_set_count", GET_ACTION_SET_COUNT_HASH)

        private const val FIND_ACTION_SET_HASH = 1888809267L
        @JvmField
        val findActionSetBind =
            ObjectCalls.getMethodBind("OpenXRActionMap", "find_action_set", FIND_ACTION_SET_HASH)

        private const val GET_ACTION_SET_HASH = 1789580336L
        @JvmField
        val getActionSetBind =
            ObjectCalls.getMethodBind("OpenXRActionMap", "get_action_set", GET_ACTION_SET_HASH)

        private const val ADD_ACTION_SET_HASH = 2093310581L
        @JvmField
        val addActionSetBind =
            ObjectCalls.getMethodBind("OpenXRActionMap", "add_action_set", ADD_ACTION_SET_HASH)

        private const val REMOVE_ACTION_SET_HASH = 2093310581L
        @JvmField
        val removeActionSetBind =
            ObjectCalls.getMethodBind("OpenXRActionMap", "remove_action_set", REMOVE_ACTION_SET_HASH)

        private const val SET_INTERACTION_PROFILES_HASH = 381264803L
        @JvmField
        val setInteractionProfilesBind =
            ObjectCalls.getMethodBind("OpenXRActionMap", "set_interaction_profiles", SET_INTERACTION_PROFILES_HASH)

        private const val GET_INTERACTION_PROFILES_HASH = 3995934104L
        @JvmField
        val getInteractionProfilesBind =
            ObjectCalls.getMethodBind("OpenXRActionMap", "get_interaction_profiles", GET_INTERACTION_PROFILES_HASH)

        private const val GET_INTERACTION_PROFILE_COUNT_HASH = 3905245786L
        @JvmField
        val getInteractionProfileCountBind =
            ObjectCalls.getMethodBind("OpenXRActionMap", "get_interaction_profile_count", GET_INTERACTION_PROFILE_COUNT_HASH)

        private const val FIND_INTERACTION_PROFILE_HASH = 3095875538L
        @JvmField
        val findInteractionProfileBind =
            ObjectCalls.getMethodBind("OpenXRActionMap", "find_interaction_profile", FIND_INTERACTION_PROFILE_HASH)

        private const val GET_INTERACTION_PROFILE_HASH = 2546151210L
        @JvmField
        val getInteractionProfileBind =
            ObjectCalls.getMethodBind("OpenXRActionMap", "get_interaction_profile", GET_INTERACTION_PROFILE_HASH)

        private const val ADD_INTERACTION_PROFILE_HASH = 2697953512L
        @JvmField
        val addInteractionProfileBind =
            ObjectCalls.getMethodBind("OpenXRActionMap", "add_interaction_profile", ADD_INTERACTION_PROFILE_HASH)

        private const val REMOVE_INTERACTION_PROFILE_HASH = 2697953512L
        @JvmField
        val removeInteractionProfileBind =
            ObjectCalls.getMethodBind("OpenXRActionMap", "remove_interaction_profile", REMOVE_INTERACTION_PROFILE_HASH)

        private const val CREATE_DEFAULT_ACTION_SETS_HASH = 3218959716L
        @JvmField
        val createDefaultActionSetsBind =
            ObjectCalls.getMethodBind("OpenXRActionMap", "create_default_action_sets", CREATE_DEFAULT_ACTION_SETS_HASH)
    }
}
