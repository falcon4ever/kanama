package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A container used for displaying the contents of a `SubViewport`.
 *
 * Generated from Godot docs: SubViewportContainer
 */
class SubViewportContainer(handle: GodotHandle) : Container(handle) {
    var stretch: Boolean
        @JvmName("stretchProperty")
        get() = isStretchEnabled()
        @JvmName("setStretchProperty")
        set(value) = setStretch(value)

    var stretchShrink: Int
        @JvmName("stretchShrinkProperty")
        get() = getStretchShrink()
        @JvmName("setStretchShrinkProperty")
        set(value) = setStretchShrink(value)

    var mouseTarget: Boolean
        @JvmName("mouseTargetProperty")
        get() = isMouseTargetEnabled()
        @JvmName("setMouseTargetProperty")
        set(value) = setMouseTarget(value)

    /**
     * If `true`, the sub-viewport will be automatically resized to the control's size. Note: If
     * `true`, this will prohibit changing `SubViewport.size` of its children manually.
     *
     * Generated from Godot docs: SubViewportContainer.set_stretch
     */
    fun setStretch(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setStretchBind, segment, enable)
    }

    /**
     * If `true`, the sub-viewport will be automatically resized to the control's size. Note: If
     * `true`, this will prohibit changing `SubViewport.size` of its children manually.
     *
     * Generated from Godot docs: SubViewportContainer.is_stretch_enabled
     */
    fun isStretchEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isStretchEnabledBind, segment)
    }

    /**
     * Divides the sub-viewport's effective resolution by this value while preserving its scale. This
     * can be used to speed up rendering. For example, a 1280×720 sub-viewport with `stretch_shrink`
     * set to `2` will be rendered at 640×360 while occupying the same size in the container. Note:
     * `stretch` must be `true` for this property to work.
     *
     * Generated from Godot docs: SubViewportContainer.set_stretch_shrink
     */
    fun setStretchShrink(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setStretchShrinkBind, segment, amount)
    }

    /**
     * Divides the sub-viewport's effective resolution by this value while preserving its scale. This
     * can be used to speed up rendering. For example, a 1280×720 sub-viewport with `stretch_shrink`
     * set to `2` will be rendered at 640×360 while occupying the same size in the container. Note:
     * `stretch` must be `true` for this property to work.
     *
     * Generated from Godot docs: SubViewportContainer.get_stretch_shrink
     */
    fun getStretchShrink(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getStretchShrinkBind, segment)
    }

    /**
     * Configure, if either the `SubViewportContainer` or alternatively the `Control` nodes of its
     * `SubViewport` children should be available as targets of mouse-related functionalities, like
     * identifying the drop target in drag-and-drop operations or cursor shape of hovered `Control`
     * node. If `false`, the `Control` nodes inside its `SubViewport` children are considered as
     * targets. If `true`, the `SubViewportContainer` itself will be considered as a target.
     *
     * Generated from Godot docs: SubViewportContainer.set_mouse_target
     */
    fun setMouseTarget(amount: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setMouseTargetBind, segment, amount)
    }

    /**
     * Configure, if either the `SubViewportContainer` or alternatively the `Control` nodes of its
     * `SubViewport` children should be available as targets of mouse-related functionalities, like
     * identifying the drop target in drag-and-drop operations or cursor shape of hovered `Control`
     * node. If `false`, the `Control` nodes inside its `SubViewport` children are considered as
     * targets. If `true`, the `SubViewportContainer` itself will be considered as a target.
     *
     * Generated from Godot docs: SubViewportContainer.is_mouse_target_enabled
     */
    fun isMouseTargetEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isMouseTargetEnabledBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SubViewportContainer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): SubViewportContainer? =
            if (handle.address() == 0L) null else SubViewportContainer(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_STRETCH_HASH = 2586408642L
        @JvmField
        val setStretchBind =
            ObjectCalls.getMethodBind("SubViewportContainer", "set_stretch", SET_STRETCH_HASH)

        private const val IS_STRETCH_ENABLED_HASH = 36873697L
        @JvmField
        val isStretchEnabledBind =
            ObjectCalls.getMethodBind("SubViewportContainer", "is_stretch_enabled", IS_STRETCH_ENABLED_HASH)

        private const val SET_STRETCH_SHRINK_HASH = 1286410249L
        @JvmField
        val setStretchShrinkBind =
            ObjectCalls.getMethodBind("SubViewportContainer", "set_stretch_shrink", SET_STRETCH_SHRINK_HASH)

        private const val GET_STRETCH_SHRINK_HASH = 3905245786L
        @JvmField
        val getStretchShrinkBind =
            ObjectCalls.getMethodBind("SubViewportContainer", "get_stretch_shrink", GET_STRETCH_SHRINK_HASH)

        private const val SET_MOUSE_TARGET_HASH = 2586408642L
        @JvmField
        val setMouseTargetBind =
            ObjectCalls.getMethodBind("SubViewportContainer", "set_mouse_target", SET_MOUSE_TARGET_HASH)

        private const val IS_MOUSE_TARGET_ENABLED_HASH = 2240911060L
        @JvmField
        val isMouseTargetEnabledBind =
            ObjectCalls.getMethodBind("SubViewportContainer", "is_mouse_target_enabled", IS_MOUSE_TARGET_ENABLED_HASH)
    }
}
