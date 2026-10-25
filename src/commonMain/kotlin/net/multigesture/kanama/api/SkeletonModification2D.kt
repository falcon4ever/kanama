package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Base class for resources that operate on `Bone2D`s in a `Skeleton2D`.
 *
 * Generated from Godot docs: SkeletonModification2D
 */
open class SkeletonModification2D(handle: GodotHandle) : Resource(handle) {
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

    /**
     * If `true`, the modification's `_execute` function will be called by the
     * `SkeletonModificationStack2D`.
     *
     * Generated from Godot docs: SkeletonModification2D.set_enabled
     */
    fun setEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnabledBind, segment, enabled)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If `true`, the modification's `_execute` function will be called by the
     * `SkeletonModificationStack2D`.
     *
     * Generated from Godot docs: SkeletonModification2D.get_enabled
     */
    fun getEnabled(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.getEnabledBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the `SkeletonModificationStack2D` that this modification is bound to. Through the
     * modification stack, you can access the Skeleton2D the modification is operating on.
     *
     * Generated from Godot docs: SkeletonModification2D.get_modification_stack
     */
    fun getModificationStack(): SkeletonModificationStack2D? {
        checkOpen()
        val ret = SkeletonModificationStack2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getModificationStackBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Manually allows you to set the setup state of the modification. This function should only rarely
     * be used, as the `SkeletonModificationStack2D` the modification is bound to should handle setting
     * the modification up.
     *
     * Generated from Godot docs: SkeletonModification2D.set_is_setup
     */
    fun setIsSetup(isSetup: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setIsSetupBind, segment, isSetup)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns whether this modification has been successfully setup or not.
     *
     * Generated from Godot docs: SkeletonModification2D.get_is_setup
     */
    fun getIsSetup(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.getIsSetupBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The execution mode for the modification. This tells the modification stack when to execute the
     * modification. Some modifications have settings that are only available in certain execution
     * modes.
     *
     * Generated from Godot docs: SkeletonModification2D.set_execution_mode
     */
    fun setExecutionMode(executionMode: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setExecutionModeBind, segment, executionMode)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The execution mode for the modification. This tells the modification stack when to execute the
     * modification. Some modifications have settings that are only available in certain execution
     * modes.
     *
     * Generated from Godot docs: SkeletonModification2D.get_execution_mode
     */
    fun getExecutionMode(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getExecutionModeBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Takes an angle and clamps it so it is within the passed-in `min` and `max` range. `invert` will
     * inversely clamp the angle, clamping it to the range outside of the given bounds.
     *
     * Generated from Godot docs: SkeletonModification2D.clamp_angle
     */
    fun clampAngle(angle: Double, min: Double, max: Double, invert: Boolean): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithThreeDoubleBoolArgsRetDouble(Binds.clampAngleBind, segment, angle, min, max, invert)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets whether this modification will call `_draw_editor_gizmo` in the Godot editor to draw
     * modification-specific gizmos.
     *
     * Generated from Godot docs: SkeletonModification2D.set_editor_draw_gizmo
     */
    fun setEditorDrawGizmo(drawGizmo: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setEditorDrawGizmoBind, segment, drawGizmo)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns whether this modification will call `_draw_editor_gizmo` in the Godot editor to draw
     * modification-specific gizmos.
     *
     * Generated from Godot docs: SkeletonModification2D.get_editor_draw_gizmo
     */
    fun getEditorDrawGizmo(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.getEditorDrawGizmoBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SkeletonModification2D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): SkeletonModification2D? =
            if (handle.address() == 0L) null else RefCounted.owned(SkeletonModification2D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): SkeletonModification2D? =
            if (handle.address() == 0L) null else SkeletonModification2D(GodotHandle(handle))

        /** A new SkeletonModification2D, GDScript's `SkeletonModification2D.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): SkeletonModification2D =
            RefCounted.owned(SkeletonModification2D(GodotHandle(requireConstructed(ObjectCalls.constructObject("SkeletonModification2D"), "SkeletonModification2D"))))
    }

    private object Binds {
        private const val SET_ENABLED_HASH = 2586408642L
        @JvmField
        val setEnabledBind =
            ObjectCalls.getMethodBind("SkeletonModification2D", "set_enabled", SET_ENABLED_HASH)

        private const val GET_ENABLED_HASH = 2240911060L
        @JvmField
        val getEnabledBind =
            ObjectCalls.getMethodBind("SkeletonModification2D", "get_enabled", GET_ENABLED_HASH)

        private const val GET_MODIFICATION_STACK_HASH = 2137761694L
        @JvmField
        val getModificationStackBind =
            ObjectCalls.getMethodBind("SkeletonModification2D", "get_modification_stack", GET_MODIFICATION_STACK_HASH)

        private const val SET_IS_SETUP_HASH = 2586408642L
        @JvmField
        val setIsSetupBind =
            ObjectCalls.getMethodBind("SkeletonModification2D", "set_is_setup", SET_IS_SETUP_HASH)

        private const val GET_IS_SETUP_HASH = 36873697L
        @JvmField
        val getIsSetupBind =
            ObjectCalls.getMethodBind("SkeletonModification2D", "get_is_setup", GET_IS_SETUP_HASH)

        private const val SET_EXECUTION_MODE_HASH = 1286410249L
        @JvmField
        val setExecutionModeBind =
            ObjectCalls.getMethodBind("SkeletonModification2D", "set_execution_mode", SET_EXECUTION_MODE_HASH)

        private const val GET_EXECUTION_MODE_HASH = 3905245786L
        @JvmField
        val getExecutionModeBind =
            ObjectCalls.getMethodBind("SkeletonModification2D", "get_execution_mode", GET_EXECUTION_MODE_HASH)

        private const val CLAMP_ANGLE_HASH = 1229502682L
        @JvmField
        val clampAngleBind =
            ObjectCalls.getMethodBind("SkeletonModification2D", "clamp_angle", CLAMP_ANGLE_HASH)

        private const val SET_EDITOR_DRAW_GIZMO_HASH = 2586408642L
        @JvmField
        val setEditorDrawGizmoBind =
            ObjectCalls.getMethodBind("SkeletonModification2D", "set_editor_draw_gizmo", SET_EDITOR_DRAW_GIZMO_HASH)

        private const val GET_EDITOR_DRAW_GIZMO_HASH = 36873697L
        @JvmField
        val getEditorDrawGizmoBind =
            ObjectCalls.getMethodBind("SkeletonModification2D", "get_editor_draw_gizmo", GET_EDITOR_DRAW_GIZMO_HASH)
    }
}
