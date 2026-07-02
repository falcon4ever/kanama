package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

/**
 * Dockable container for the editor.
 *
 * Generated from Godot docs: EditorDock
 */
open class EditorDock(handle: MemorySegment) : MarginContainer(handle) {
    var title: String
        @JvmName("titleProperty")
        get() = getTitle()
        @JvmName("setTitleProperty")
        set(value) = setTitle(value)

    var layoutKey: String
        @JvmName("layoutKeyProperty")
        get() = getLayoutKey()
        @JvmName("setLayoutKeyProperty")
        set(value) = setLayoutKey(value)

    var global: Boolean
        @JvmName("globalProperty")
        get() = isGlobal()
        @JvmName("setGlobalProperty")
        set(value) = setGlobal(value)

    var transient: Boolean
        @JvmName("transientProperty")
        get() = isTransient()
        @JvmName("setTransientProperty")
        set(value) = setTransient(value)

    var closable: Boolean
        @JvmName("closableProperty")
        get() = isClosable()
        @JvmName("setClosableProperty")
        set(value) = setClosable(value)

    var iconName: String
        @JvmName("iconNameProperty")
        get() = getIconName()
        @JvmName("setIconNameProperty")
        set(value) = setIconName(value)

    var dockIcon: Texture2D?
        @JvmName("dockIconProperty")
        get() = getDockIcon()
        @JvmName("setDockIconProperty")
        set(value) = setDockIcon(value)

    var forceShowIcon: Boolean
        @JvmName("forceShowIconProperty")
        get() = getForceShowIcon()
        @JvmName("setForceShowIconProperty")
        set(value) = setForceShowIcon(value)

    var titleColor: Color
        @JvmName("titleColorProperty")
        get() = getTitleColor()
        @JvmName("setTitleColorProperty")
        set(value) = setTitleColor(value)

    var dockShortcut: Shortcut?
        @JvmName("dockShortcutProperty")
        get() = getDockShortcut()
        @JvmName("setDockShortcutProperty")
        set(value) = setDockShortcut(value)

    var defaultSlot: Long
        @JvmName("defaultSlotProperty")
        get() = getDefaultSlot()
        @JvmName("setDefaultSlotProperty")
        set(value) = setDefaultSlot(value)

    var availableLayouts: Long
        @JvmName("availableLayoutsProperty")
        get() = getAvailableLayouts()
        @JvmName("setAvailableLayoutsProperty")
        set(value) = setAvailableLayouts(value)

    /**
     * Opens the dock. It will appear in the last used dock slot. If the dock has no default slot, it
     * will be opened floating. Note: This does not focus the dock. If you want to open and focus the
     * dock, use `make_visible`.
     *
     * Generated from Godot docs: EditorDock.open
     */
    fun open() {
        ObjectCalls.ptrcallNoArgs(openBind, handle)
    }

    fun makeVisible() {
        ObjectCalls.ptrcallNoArgs(makeVisibleBind, handle)
    }

    fun closeDock() {
        ObjectCalls.ptrcallNoArgs(closeDockBind, handle)
    }

    fun setTitle(title: String) {
        ObjectCalls.ptrcallWithStringArg(setTitleBind, handle, title)
    }

    fun getTitle(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTitleBind, handle)
    }

    fun setLayoutKey(layoutKey: String) {
        ObjectCalls.ptrcallWithStringArg(setLayoutKeyBind, handle, layoutKey)
    }

    fun getLayoutKey(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLayoutKeyBind, handle)
    }

    fun setGlobal(global: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setGlobalBind, handle, global)
    }

    fun isGlobal(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isGlobalBind, handle)
    }

    fun setTransient(transient: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTransientBind, handle, transient)
    }

    fun isTransient(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTransientBind, handle)
    }

    fun setClosable(closable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setClosableBind, handle, closable)
    }

    fun isClosable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isClosableBind, handle)
    }

    fun setIconName(iconName: String) {
        ObjectCalls.ptrcallWithStringNameArg(setIconNameBind, handle, iconName)
    }

    fun getIconName(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getIconNameBind, handle)
    }

    fun setDockIcon(icon: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setDockIconBind, handle, listOf(icon?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getDockIcon(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getDockIconBind, handle))
    }

    fun setForceShowIcon(force: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setForceShowIconBind, handle, force)
    }

    fun getForceShowIcon(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getForceShowIconBind, handle)
    }

    fun setTitleColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setTitleColorBind, handle, color)
    }

    fun getTitleColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getTitleColorBind, handle)
    }

    fun setDockShortcut(shortcut: Shortcut?) {
        ObjectCalls.ptrcallWithObjectArgs(setDockShortcutBind, handle, listOf(shortcut?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getDockShortcut(): Shortcut? {
        return Shortcut.wrap(ObjectCalls.ptrcallNoArgsRetObject(getDockShortcutBind, handle))
    }

    fun setDefaultSlot(slot: Long) {
        ObjectCalls.ptrcallWithLongArg(setDefaultSlotBind, handle, slot)
    }

    fun getDefaultSlot(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDefaultSlotBind, handle)
    }

    fun setAvailableLayouts(layouts: Long) {
        ObjectCalls.ptrcallWithLongArg(setAvailableLayoutsBind, handle, layouts)
    }

    fun getAvailableLayouts(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAvailableLayoutsBind, handle)
    }

    object Signals {
        const val opened: String = "opened"
        const val closed: String = "closed"
    }

    companion object {
        const val DOCK_LAYOUT_VERTICAL: Long = 1L
        const val DOCK_LAYOUT_HORIZONTAL: Long = 2L
        const val DOCK_LAYOUT_FLOATING: Long = 4L
        const val DOCK_LAYOUT_ALL: Long = 7L
        const val DOCK_SLOT_NONE: Long = -1L
        const val DOCK_SLOT_LEFT_UL: Long = 0L
        const val DOCK_SLOT_LEFT_BL: Long = 1L
        const val DOCK_SLOT_LEFT_UR: Long = 2L
        const val DOCK_SLOT_LEFT_BR: Long = 3L
        const val DOCK_SLOT_RIGHT_UL: Long = 4L
        const val DOCK_SLOT_RIGHT_BL: Long = 5L
        const val DOCK_SLOT_RIGHT_UR: Long = 6L
        const val DOCK_SLOT_RIGHT_BR: Long = 7L
        const val DOCK_SLOT_BOTTOM: Long = 8L
        const val DOCK_SLOT_BOTTOM_L: Long = 9L
        const val DOCK_SLOT_BOTTOM_R: Long = 10L
        const val DOCK_SLOT_MAX: Long = 11L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorDock? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorDock? =
            if (handle.address() == 0L) null else EditorDock(handle)

        private const val OPEN_HASH = 3218959716L
        private val openBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "open", OPEN_HASH)
        }

        private const val MAKE_VISIBLE_HASH = 3218959716L
        private val makeVisibleBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "make_visible", MAKE_VISIBLE_HASH)
        }

        private const val CLOSE_HASH = 3218959716L
        private val closeDockBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "close", CLOSE_HASH)
        }

        private const val SET_TITLE_HASH = 83702148L
        private val setTitleBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "set_title", SET_TITLE_HASH)
        }

        private const val GET_TITLE_HASH = 201670096L
        private val getTitleBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "get_title", GET_TITLE_HASH)
        }

        private const val SET_LAYOUT_KEY_HASH = 83702148L
        private val setLayoutKeyBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "set_layout_key", SET_LAYOUT_KEY_HASH)
        }

        private const val GET_LAYOUT_KEY_HASH = 201670096L
        private val getLayoutKeyBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "get_layout_key", GET_LAYOUT_KEY_HASH)
        }

        private const val SET_GLOBAL_HASH = 2586408642L
        private val setGlobalBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "set_global", SET_GLOBAL_HASH)
        }

        private const val IS_GLOBAL_HASH = 36873697L
        private val isGlobalBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "is_global", IS_GLOBAL_HASH)
        }

        private const val SET_TRANSIENT_HASH = 2586408642L
        private val setTransientBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "set_transient", SET_TRANSIENT_HASH)
        }

        private const val IS_TRANSIENT_HASH = 36873697L
        private val isTransientBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "is_transient", IS_TRANSIENT_HASH)
        }

        private const val SET_CLOSABLE_HASH = 2586408642L
        private val setClosableBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "set_closable", SET_CLOSABLE_HASH)
        }

        private const val IS_CLOSABLE_HASH = 36873697L
        private val isClosableBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "is_closable", IS_CLOSABLE_HASH)
        }

        private const val SET_ICON_NAME_HASH = 3304788590L
        private val setIconNameBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "set_icon_name", SET_ICON_NAME_HASH)
        }

        private const val GET_ICON_NAME_HASH = 2002593661L
        private val getIconNameBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "get_icon_name", GET_ICON_NAME_HASH)
        }

        private const val SET_DOCK_ICON_HASH = 4051416890L
        private val setDockIconBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "set_dock_icon", SET_DOCK_ICON_HASH)
        }

        private const val GET_DOCK_ICON_HASH = 3635182373L
        private val getDockIconBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "get_dock_icon", GET_DOCK_ICON_HASH)
        }

        private const val SET_FORCE_SHOW_ICON_HASH = 2586408642L
        private val setForceShowIconBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "set_force_show_icon", SET_FORCE_SHOW_ICON_HASH)
        }

        private const val GET_FORCE_SHOW_ICON_HASH = 36873697L
        private val getForceShowIconBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "get_force_show_icon", GET_FORCE_SHOW_ICON_HASH)
        }

        private const val SET_TITLE_COLOR_HASH = 2920490490L
        private val setTitleColorBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "set_title_color", SET_TITLE_COLOR_HASH)
        }

        private const val GET_TITLE_COLOR_HASH = 3444240500L
        private val getTitleColorBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "get_title_color", GET_TITLE_COLOR_HASH)
        }

        private const val SET_DOCK_SHORTCUT_HASH = 857163497L
        private val setDockShortcutBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "set_dock_shortcut", SET_DOCK_SHORTCUT_HASH)
        }

        private const val GET_DOCK_SHORTCUT_HASH = 3415666916L
        private val getDockShortcutBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "get_dock_shortcut", GET_DOCK_SHORTCUT_HASH)
        }

        private const val SET_DEFAULT_SLOT_HASH = 4142995464L
        private val setDefaultSlotBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "set_default_slot", SET_DEFAULT_SLOT_HASH)
        }

        private const val GET_DEFAULT_SLOT_HASH = 3298961740L
        private val getDefaultSlotBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "get_default_slot", GET_DEFAULT_SLOT_HASH)
        }

        private const val SET_AVAILABLE_LAYOUTS_HASH = 3440531249L
        private val setAvailableLayoutsBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "set_available_layouts", SET_AVAILABLE_LAYOUTS_HASH)
        }

        private const val GET_AVAILABLE_LAYOUTS_HASH = 495015512L
        private val getAvailableLayoutsBind by lazy {
            ObjectCalls.getMethodBind("EditorDock", "get_available_layouts", GET_AVAILABLE_LAYOUTS_HASH)
        }
    }
}
