package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Base class for resources that operate on `Bone2D`s in a `Skeleton2D`.
 *
 * Generated from Godot docs: SkeletonModification2D
 */
open class SkeletonModification2D(handle: MemorySegment) : Resource(handle) {
    var enabled: Boolean
        @JvmName("enabledProperty")
        get() = getEnabled()
        @JvmName("setEnabledProperty")
        set(value) = setEnabled(value)

    var executionMode: Int
        @JvmName("executionModeProperty")
        get() = getExecutionMode()
        @JvmName("setExecutionModeProperty")
        set(value) = setExecutionMode(value)

    fun setEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnabledBind, handle, enabled)
    }

    fun getEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEnabledBind, handle)
    }

    fun getModificationStack(): SkeletonModificationStack2D? {
        return SkeletonModificationStack2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getModificationStackBind, handle))
    }

    fun setIsSetup(isSetup: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIsSetupBind, handle, isSetup)
    }

    fun getIsSetup(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getIsSetupBind, handle)
    }

    fun setExecutionMode(executionMode: Int) {
        ObjectCalls.ptrcallWithIntArg(setExecutionModeBind, handle, executionMode)
    }

    fun getExecutionMode(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getExecutionModeBind, handle)
    }

    fun clampAngle(angle: Double, min: Double, max: Double, invert: Boolean): Double {
        return ObjectCalls.ptrcallWithThreeDoubleBoolArgsRetDouble(clampAngleBind, handle, angle, min, max, invert)
    }

    fun setEditorDrawGizmo(drawGizmo: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEditorDrawGizmoBind, handle, drawGizmo)
    }

    fun getEditorDrawGizmo(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getEditorDrawGizmoBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SkeletonModification2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SkeletonModification2D? =
            if (handle.address() == 0L) null else SkeletonModification2D(handle)

        private const val SET_ENABLED_HASH = 2586408642L
        private val setEnabledBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2D", "set_enabled", SET_ENABLED_HASH)
        }

        private const val GET_ENABLED_HASH = 2240911060L
        private val getEnabledBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2D", "get_enabled", GET_ENABLED_HASH)
        }

        private const val GET_MODIFICATION_STACK_HASH = 2137761694L
        private val getModificationStackBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2D", "get_modification_stack", GET_MODIFICATION_STACK_HASH)
        }

        private const val SET_IS_SETUP_HASH = 2586408642L
        private val setIsSetupBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2D", "set_is_setup", SET_IS_SETUP_HASH)
        }

        private const val GET_IS_SETUP_HASH = 36873697L
        private val getIsSetupBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2D", "get_is_setup", GET_IS_SETUP_HASH)
        }

        private const val SET_EXECUTION_MODE_HASH = 1286410249L
        private val setExecutionModeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2D", "set_execution_mode", SET_EXECUTION_MODE_HASH)
        }

        private const val GET_EXECUTION_MODE_HASH = 3905245786L
        private val getExecutionModeBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2D", "get_execution_mode", GET_EXECUTION_MODE_HASH)
        }

        private const val CLAMP_ANGLE_HASH = 1229502682L
        private val clampAngleBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2D", "clamp_angle", CLAMP_ANGLE_HASH)
        }

        private const val SET_EDITOR_DRAW_GIZMO_HASH = 2586408642L
        private val setEditorDrawGizmoBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2D", "set_editor_draw_gizmo", SET_EDITOR_DRAW_GIZMO_HASH)
        }

        private const val GET_EDITOR_DRAW_GIZMO_HASH = 36873697L
        private val getEditorDrawGizmoBind by lazy {
            ObjectCalls.getMethodBind("SkeletonModification2D", "get_editor_draw_gizmo", GET_EDITOR_DRAW_GIZMO_HASH)
        }
    }
}
