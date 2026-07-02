package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRActionMap
 */
class OpenXRActionMap(handle: MemorySegment) : Resource(handle) {
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

    fun setActionSets(actionSets: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setActionSetsBind, handle, actionSets)
    }

    fun getActionSets(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getActionSetsBind, handle)
    }

    fun getActionSetCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getActionSetCountBind, handle)
    }

    fun findActionSet(name: String): OpenXRActionSet? {
        return OpenXRActionSet.wrap(ObjectCalls.ptrcallWithStringArgRetObject(findActionSetBind, handle, name))
    }

    fun getActionSet(idx: Int): OpenXRActionSet? {
        return OpenXRActionSet.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getActionSetBind, handle, idx))
    }

    fun addActionSet(actionSet: OpenXRActionSet?) {
        ObjectCalls.ptrcallWithObjectArgs(addActionSetBind, handle, listOf(actionSet?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun removeActionSet(actionSet: OpenXRActionSet?) {
        ObjectCalls.ptrcallWithObjectArgs(removeActionSetBind, handle, listOf(actionSet?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun setInteractionProfiles(interactionProfiles: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setInteractionProfilesBind, handle, interactionProfiles)
    }

    fun getInteractionProfiles(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getInteractionProfilesBind, handle)
    }

    fun getInteractionProfileCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getInteractionProfileCountBind, handle)
    }

    fun findInteractionProfile(name: String): OpenXRInteractionProfile? {
        return OpenXRInteractionProfile.wrap(ObjectCalls.ptrcallWithStringArgRetObject(findInteractionProfileBind, handle, name))
    }

    fun getInteractionProfile(idx: Int): OpenXRInteractionProfile? {
        return OpenXRInteractionProfile.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getInteractionProfileBind, handle, idx))
    }

    fun addInteractionProfile(interactionProfile: OpenXRInteractionProfile?) {
        ObjectCalls.ptrcallWithObjectArgs(addInteractionProfileBind, handle, listOf(interactionProfile?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun removeInteractionProfile(interactionProfile: OpenXRInteractionProfile?) {
        ObjectCalls.ptrcallWithObjectArgs(removeInteractionProfileBind, handle, listOf(interactionProfile?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun createDefaultActionSets() {
        ObjectCalls.ptrcallNoArgs(createDefaultActionSetsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRActionMap? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRActionMap? =
            if (handle.address() == 0L) null else OpenXRActionMap(handle)

        private const val SET_ACTION_SETS_HASH = 381264803L
        private val setActionSetsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionMap", "set_action_sets", SET_ACTION_SETS_HASH)
        }

        private const val GET_ACTION_SETS_HASH = 3995934104L
        private val getActionSetsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionMap", "get_action_sets", GET_ACTION_SETS_HASH)
        }

        private const val GET_ACTION_SET_COUNT_HASH = 3905245786L
        private val getActionSetCountBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionMap", "get_action_set_count", GET_ACTION_SET_COUNT_HASH)
        }

        private const val FIND_ACTION_SET_HASH = 1888809267L
        private val findActionSetBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionMap", "find_action_set", FIND_ACTION_SET_HASH)
        }

        private const val GET_ACTION_SET_HASH = 1789580336L
        private val getActionSetBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionMap", "get_action_set", GET_ACTION_SET_HASH)
        }

        private const val ADD_ACTION_SET_HASH = 2093310581L
        private val addActionSetBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionMap", "add_action_set", ADD_ACTION_SET_HASH)
        }

        private const val REMOVE_ACTION_SET_HASH = 2093310581L
        private val removeActionSetBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionMap", "remove_action_set", REMOVE_ACTION_SET_HASH)
        }

        private const val SET_INTERACTION_PROFILES_HASH = 381264803L
        private val setInteractionProfilesBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionMap", "set_interaction_profiles", SET_INTERACTION_PROFILES_HASH)
        }

        private const val GET_INTERACTION_PROFILES_HASH = 3995934104L
        private val getInteractionProfilesBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionMap", "get_interaction_profiles", GET_INTERACTION_PROFILES_HASH)
        }

        private const val GET_INTERACTION_PROFILE_COUNT_HASH = 3905245786L
        private val getInteractionProfileCountBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionMap", "get_interaction_profile_count", GET_INTERACTION_PROFILE_COUNT_HASH)
        }

        private const val FIND_INTERACTION_PROFILE_HASH = 3095875538L
        private val findInteractionProfileBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionMap", "find_interaction_profile", FIND_INTERACTION_PROFILE_HASH)
        }

        private const val GET_INTERACTION_PROFILE_HASH = 2546151210L
        private val getInteractionProfileBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionMap", "get_interaction_profile", GET_INTERACTION_PROFILE_HASH)
        }

        private const val ADD_INTERACTION_PROFILE_HASH = 2697953512L
        private val addInteractionProfileBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionMap", "add_interaction_profile", ADD_INTERACTION_PROFILE_HASH)
        }

        private const val REMOVE_INTERACTION_PROFILE_HASH = 2697953512L
        private val removeInteractionProfileBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionMap", "remove_interaction_profile", REMOVE_INTERACTION_PROFILE_HASH)
        }

        private const val CREATE_DEFAULT_ACTION_SETS_HASH = 3218959716L
        private val createDefaultActionSetsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRActionMap", "create_default_action_sets", CREATE_DEFAULT_ACTION_SETS_HASH)
        }
    }
}
