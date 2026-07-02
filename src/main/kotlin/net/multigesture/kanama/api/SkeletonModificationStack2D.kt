package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A resource that holds a stack of `SkeletonModification2D`s.
 *
 * Generated from Godot docs: SkeletonModificationStack2D
 */
class SkeletonModificationStack2D(handle: MemorySegment) : Resource(handle) {
    var enabled: Boolean
        @JvmName("enabledProperty")
        get() = getEnabled()
        @JvmName("setEnabledProperty")
        set(value) = setEnabled(value)

    var strength: Double
        @JvmName("strengthProperty")
        get() = getStrength()
        @JvmName("setStrengthProperty")
        set(value) = setStrength(value)

    var modificationCount: Int
        @JvmName("modificationCountProperty")
        get() = getModificationCount()
        @JvmName("setModificationCountProperty")
        set(value) = setModificationCount(value)

    /**
     * Sets up the modification stack so it can execute. This function should be called by `Skeleton2D`
     * and shouldn't be manually called unless you know what you are doing.
     *
     * Generated from Godot docs: SkeletonModificationStack2D.setup
     */
    fun setup() {
        ObjectCalls.ptrcallNoArgs(setupBind, handle)
    }

    /**
     * Executes all of the `SkeletonModification2D`s in the stack that use the same execution mode as
     * the passed-in `execution_mode`, starting from index `0` to `modification_count`. Note: The order
     * of the modifications can matter depending on the modifications. For example, modifications on a
     * spine should operate before modifications on the arms in order to get proper results.
     *
     * Generated from Godot docs: SkeletonModificationStack2D.execute
     */
    fun execute(delta: Double, executionMode: Int) {
        ObjectCalls.ptrcallWithDoubleAndIntArgs(executeBind, handle, delta, executionMode)
    }

    fun enableAllModifications(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(enableAllModificationsBind, handle, enabled)
    }

    fun getModification(modIdx: Int): SkeletonModification2D? {
        return SkeletonModification2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getModificationBind, handle, modIdx))
    }

    fun addModification(modification: SkeletonModification2D?) {
        ObjectCalls.ptrcallWithObjectArgs(addModificationBind, handle, listOf(modification?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun deleteModification(modIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(deleteModificationBind, handle, modIdx)
    }

    fun setModification(modIdx: Int, modification: SkeletonModification2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setModificationBind, handle, modIdx, modification?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun setModificationCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setModificationCountBind, handle, count)
    }

    fun getModificationCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getModificationCountBind, handle)
    }

    fun getIsSetup(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getIsSetupBind, handle)
    }

    fun setEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnabledBind, handle, enabled)
    }

    fun getEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEnabledBind, handle)
    }

    fun setStrength(strength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setStrengthBind, handle, strength)
    }

    fun getStrength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getStrengthBind, handle)
    }

    fun getSkeleton(): Skeleton2D? {
        return Skeleton2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSkeletonBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SkeletonModificationStack2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SkeletonModificationStack2D? =
            if (handle.address() == 0L) null else SkeletonModificationStack2D(handle)

        private const val SETUP_HASH = 3218959716L
        private val setupBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModificationStack2D", "setup", SETUP_HASH)
        }

        private const val EXECUTE_HASH = 1005356550L
        private val executeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModificationStack2D", "execute", EXECUTE_HASH)
        }

        private const val ENABLE_ALL_MODIFICATIONS_HASH = 2586408642L
        private val enableAllModificationsBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModificationStack2D", "enable_all_modifications", ENABLE_ALL_MODIFICATIONS_HASH)
        }

        private const val GET_MODIFICATION_HASH = 2570274329L
        private val getModificationBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModificationStack2D", "get_modification", GET_MODIFICATION_HASH)
        }

        private const val ADD_MODIFICATION_HASH = 354162120L
        private val addModificationBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModificationStack2D", "add_modification", ADD_MODIFICATION_HASH)
        }

        private const val DELETE_MODIFICATION_HASH = 1286410249L
        private val deleteModificationBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModificationStack2D", "delete_modification", DELETE_MODIFICATION_HASH)
        }

        private const val SET_MODIFICATION_HASH = 1098262544L
        private val setModificationBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModificationStack2D", "set_modification", SET_MODIFICATION_HASH)
        }

        private const val SET_MODIFICATION_COUNT_HASH = 1286410249L
        private val setModificationCountBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModificationStack2D", "set_modification_count", SET_MODIFICATION_COUNT_HASH)
        }

        private const val GET_MODIFICATION_COUNT_HASH = 3905245786L
        private val getModificationCountBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModificationStack2D", "get_modification_count", GET_MODIFICATION_COUNT_HASH)
        }

        private const val GET_IS_SETUP_HASH = 36873697L
        private val getIsSetupBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModificationStack2D", "get_is_setup", GET_IS_SETUP_HASH)
        }

        private const val SET_ENABLED_HASH = 2586408642L
        private val setEnabledBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModificationStack2D", "set_enabled", SET_ENABLED_HASH)
        }

        private const val GET_ENABLED_HASH = 36873697L
        private val getEnabledBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModificationStack2D", "get_enabled", GET_ENABLED_HASH)
        }

        private const val SET_STRENGTH_HASH = 373806689L
        private val setStrengthBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModificationStack2D", "set_strength", SET_STRENGTH_HASH)
        }

        private const val GET_STRENGTH_HASH = 1740695150L
        private val getStrengthBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModificationStack2D", "get_strength", GET_STRENGTH_HASH)
        }

        private const val GET_SKELETON_HASH = 1697361217L
        private val getSkeletonBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModificationStack2D", "get_skeleton", GET_SKELETON_HASH)
        }
    }
}
