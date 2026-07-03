package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Rect2

/**
 * Application status indicator (aka notification area icon). Note: Status indicator is implemented
 * on macOS and Windows.
 *
 * Generated from Godot docs: StatusIndicator
 */
class StatusIndicator(handle: MemorySegment) : Node(handle) {
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
        ObjectCalls.ptrcallWithStringArg(setTooltipBind, handle, tooltip)
    }

    /**
     * Status indicator tooltip.
     *
     * Generated from Godot docs: StatusIndicator.get_tooltip
     */
    fun getTooltip(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTooltipBind, handle)
    }

    /**
     * Status indicator icon.
     *
     * Generated from Godot docs: StatusIndicator.set_icon
     */
    fun setIcon(texture: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setIconBind, handle, listOf(texture?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Status indicator icon.
     *
     * Generated from Godot docs: StatusIndicator.get_icon
     */
    fun getIcon(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getIconBind, handle))
    }

    /**
     * If `true`, the status indicator is visible.
     *
     * Generated from Godot docs: StatusIndicator.set_visible
     */
    fun setVisible(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVisibleBind, handle, visible)
    }

    /**
     * If `true`, the status indicator is visible.
     *
     * Generated from Godot docs: StatusIndicator.is_visible
     */
    fun isVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVisibleBind, handle)
    }

    /**
     * Status indicator native popup menu. If this is set, the `pressed` signal is not emitted. Note:
     * Native popup is only supported if `NativeMenu` supports `NativeMenu.FEATURE_POPUP_MENU` feature.
     *
     * Generated from Godot docs: StatusIndicator.set_menu
     */
    fun setMenu(menu: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setMenuBind, handle, menu)
    }

    /**
     * Status indicator native popup menu. If this is set, the `pressed` signal is not emitted. Note:
     * Native popup is only supported if `NativeMenu` supports `NativeMenu.FEATURE_POPUP_MENU` feature.
     *
     * Generated from Godot docs: StatusIndicator.get_menu
     */
    fun getMenu(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getMenuBind, handle)
    }

    /**
     * Returns the status indicator rectangle in screen coordinates. If this status indicator is not
     * visible, returns an empty `Rect2`.
     *
     * Generated from Godot docs: StatusIndicator.get_rect
     */
    fun getRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getRectBind, handle)
    }

    object Signals {
        const val pressed: String = "pressed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): StatusIndicator? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): StatusIndicator? =
            if (handle.address() == 0L) null else StatusIndicator(handle)

        private const val SET_TOOLTIP_HASH = 83702148L
        private val setTooltipBind by lazy {
            ObjectCalls.getMethodBind("StatusIndicator", "set_tooltip", SET_TOOLTIP_HASH)
        }

        private const val GET_TOOLTIP_HASH = 201670096L
        private val getTooltipBind by lazy {
            ObjectCalls.getMethodBind("StatusIndicator", "get_tooltip", GET_TOOLTIP_HASH)
        }

        private const val SET_ICON_HASH = 4051416890L
        private val setIconBind by lazy {
            ObjectCalls.getMethodBind("StatusIndicator", "set_icon", SET_ICON_HASH)
        }

        private const val GET_ICON_HASH = 3635182373L
        private val getIconBind by lazy {
            ObjectCalls.getMethodBind("StatusIndicator", "get_icon", GET_ICON_HASH)
        }

        private const val SET_VISIBLE_HASH = 2586408642L
        private val setVisibleBind by lazy {
            ObjectCalls.getMethodBind("StatusIndicator", "set_visible", SET_VISIBLE_HASH)
        }

        private const val IS_VISIBLE_HASH = 36873697L
        private val isVisibleBind by lazy {
            ObjectCalls.getMethodBind("StatusIndicator", "is_visible", IS_VISIBLE_HASH)
        }

        private const val SET_MENU_HASH = 1348162250L
        private val setMenuBind by lazy {
            ObjectCalls.getMethodBind("StatusIndicator", "set_menu", SET_MENU_HASH)
        }

        private const val GET_MENU_HASH = 4075236667L
        private val getMenuBind by lazy {
            ObjectCalls.getMethodBind("StatusIndicator", "get_menu", GET_MENU_HASH)
        }

        private const val GET_RECT_HASH = 1639390495L
        private val getRectBind by lazy {
            ObjectCalls.getMethodBind("StatusIndicator", "get_rect", GET_RECT_HASH)
        }
    }
}
