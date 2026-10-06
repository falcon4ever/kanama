package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2i

/**
 * Application status indicator (aka notification area icon). Note: Status indicator is implemented
 * on macOS and Windows.
 *
 * Generated from Godot docs: StatusIndicator
 */
class StatusIndicator(handle: GodotHandle) : Node(handle) {
    var tooltip: String
        @JvmName("tooltipProperty")
        get() = getTooltip()
        @JvmName("setTooltipProperty")
        set(value) = setTooltip(value)

    var icon: Texture2D?
        @JvmName("iconProperty")
        get() = getIcon()
        @JvmName("setIconProperty")
        set(value) = setIcon(value)

    var menu: NodePath
        @JvmName("menuProperty")
        get() = getMenu()
        @JvmName("setMenuProperty")
        set(value) = setMenu(value)

    var visible: Boolean
        @JvmName("visibleProperty")
        get() = isVisible()
        @JvmName("setVisibleProperty")
        set(value) = setVisible(value)

    /**
     * Status indicator tooltip.
     *
     * Generated from Godot docs: StatusIndicator.set_tooltip
     */
    fun setTooltip(tooltip: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setTooltipBind, segment, tooltip)
    }

    /**
     * Status indicator tooltip.
     *
     * Generated from Godot docs: StatusIndicator.get_tooltip
     */
    fun getTooltip(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getTooltipBind, segment)
    }

    /**
     * Status indicator icon.
     *
     * Generated from Godot docs: StatusIndicator.set_icon
     */
    fun setIcon(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setIconBind, segment, listOf(texture?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Status indicator icon.
     *
     * Generated from Godot docs: StatusIndicator.get_icon
     */
    fun getIcon(): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getIconBind, segment))
    }

    /**
     * If `true`, the status indicator is visible.
     *
     * Generated from Godot docs: StatusIndicator.set_visible
     */
    fun setVisible(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setVisibleBind, segment, visible)
    }

    /**
     * If `true`, the status indicator is visible.
     *
     * Generated from Godot docs: StatusIndicator.is_visible
     */
    fun isVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isVisibleBind, segment)
    }

    /**
     * Status indicator native popup menu. If this is set, the `pressed` signal is not emitted. Note:
     * Native popup is only supported if `NativeMenu` supports `NativeMenu.Feature.POPUP_MENU` feature.
     *
     * Generated from Godot docs: StatusIndicator.set_menu
     */
    fun setMenu(menu: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(Binds.setMenuBind, segment, menu)
    }

    /**
     * Status indicator native popup menu. If this is set, the `pressed` signal is not emitted. Note:
     * Native popup is only supported if `NativeMenu` supports `NativeMenu.Feature.POPUP_MENU` feature.
     *
     * Generated from Godot docs: StatusIndicator.get_menu
     */
    fun getMenu(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getMenuBind, segment)
    }

    /**
     * Returns the status indicator rectangle in screen coordinates. If this status indicator is not
     * visible, returns an empty `Rect2`.
     *
     * Generated from Godot docs: StatusIndicator.get_rect
     */
    fun getRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(Binds.getRectBind, segment)
    }

    /** Signal `pressed(mouse_button: int, mouse_position: Vector2i)`; see [TypedSignal]. */
    val pressed: Signal2<Long, Vector2i>
        @JvmName("pressedTypedSignal")
        get() = Signal2(this, "pressed", SignalArgType.LONG, SignalArgType.valueOf<Vector2i>("Vector2i", Vector2i::class))

    object Signals {
        const val pressed: String = "pressed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): StatusIndicator? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): StatusIndicator? =
            if (handle.address() == 0L) null else StatusIndicator(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_TOOLTIP_HASH = 83702148L
        @JvmField
        val setTooltipBind =
            ObjectCalls.getMethodBind("StatusIndicator", "set_tooltip", SET_TOOLTIP_HASH)

        private const val GET_TOOLTIP_HASH = 201670096L
        @JvmField
        val getTooltipBind =
            ObjectCalls.getMethodBind("StatusIndicator", "get_tooltip", GET_TOOLTIP_HASH)

        private const val SET_ICON_HASH = 4051416890L
        @JvmField
        val setIconBind =
            ObjectCalls.getMethodBind("StatusIndicator", "set_icon", SET_ICON_HASH)

        private const val GET_ICON_HASH = 3635182373L
        @JvmField
        val getIconBind =
            ObjectCalls.getMethodBind("StatusIndicator", "get_icon", GET_ICON_HASH)

        private const val SET_VISIBLE_HASH = 2586408642L
        @JvmField
        val setVisibleBind =
            ObjectCalls.getMethodBind("StatusIndicator", "set_visible", SET_VISIBLE_HASH)

        private const val IS_VISIBLE_HASH = 36873697L
        @JvmField
        val isVisibleBind =
            ObjectCalls.getMethodBind("StatusIndicator", "is_visible", IS_VISIBLE_HASH)

        private const val SET_MENU_HASH = 1348162250L
        @JvmField
        val setMenuBind =
            ObjectCalls.getMethodBind("StatusIndicator", "set_menu", SET_MENU_HASH)

        private const val GET_MENU_HASH = 4075236667L
        @JvmField
        val getMenuBind =
            ObjectCalls.getMethodBind("StatusIndicator", "get_menu", GET_MENU_HASH)

        private const val GET_RECT_HASH = 1639390495L
        @JvmField
        val getRectBind =
            ObjectCalls.getMethodBind("StatusIndicator", "get_rect", GET_RECT_HASH)
    }
}
