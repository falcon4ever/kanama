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

    /**
     * Focuses the dock's tab (or window if it's floating). If the dock was closed, it will be opened.
     * If it's a bottom dock, makes the bottom panel visible.
     *
     * Generated from Godot docs: EditorDock.make_visible
     */
    fun makeVisible() {
        ObjectCalls.ptrcallNoArgs(makeVisibleBind, handle)
    }

    fun closeDock() {
        ObjectCalls.ptrcallNoArgs(closeDockBind, handle)
    }

    /**
     * The title of the dock's tab. If empty, the dock's `Node.name` will be used. If the name is
     * auto-generated (contains `@`), the first child's name will be used instead.
     *
     * Generated from Godot docs: EditorDock.set_title
     */
    fun setTitle(title: String) {
        ObjectCalls.ptrcallWithStringArg(setTitleBind, handle, title)
    }

    /**
     * The title of the dock's tab. If empty, the dock's `Node.name` will be used. If the name is
     * auto-generated (contains `@`), the first child's name will be used instead.
     *
     * Generated from Godot docs: EditorDock.get_title
     */
    fun getTitle(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTitleBind, handle)
    }

    /**
     * The key representing this dock in the editor's layout file. If empty, the dock's displayed name
     * will be used instead.
     *
     * Generated from Godot docs: EditorDock.set_layout_key
     */
    fun setLayoutKey(layoutKey: String) {
        ObjectCalls.ptrcallWithStringArg(setLayoutKeyBind, handle, layoutKey)
    }

    /**
     * The key representing this dock in the editor's layout file. If empty, the dock's displayed name
     * will be used instead.
     *
     * Generated from Godot docs: EditorDock.get_layout_key
     */
    fun getLayoutKey(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLayoutKeyBind, handle)
    }

    /**
     * If `true`, the dock appears in the Editor > Editor Docks menu and can be closed. Non-global
     * docks can still be closed using `close` or when `closable` is `true`.
     *
     * Generated from Godot docs: EditorDock.set_global
     */
    fun setGlobal(global: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setGlobalBind, handle, global)
    }

    /**
     * If `true`, the dock appears in the Editor > Editor Docks menu and can be closed. Non-global
     * docks can still be closed using `close` or when `closable` is `true`.
     *
     * Generated from Godot docs: EditorDock.is_global
     */
    fun isGlobal(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isGlobalBind, handle)
    }

    /**
     * If `true`, the dock is not automatically opened or closed when loading an editor layout, only
     * moved. It also can't be opened using a shortcut. This is meant for docks that are opened and
     * closed in specific cases, such as when selecting a `TileMap` or `AnimationTree` node.
     *
     * Generated from Godot docs: EditorDock.set_transient
     */
    fun setTransient(transient: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTransientBind, handle, transient)
    }

    /**
     * If `true`, the dock is not automatically opened or closed when loading an editor layout, only
     * moved. It also can't be opened using a shortcut. This is meant for docks that are opened and
     * closed in specific cases, such as when selecting a `TileMap` or `AnimationTree` node.
     *
     * Generated from Godot docs: EditorDock.is_transient
     */
    fun isTransient(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTransientBind, handle)
    }

    /**
     * If `true`, the dock can be closed with the Close button in the context popup. Docks with
     * `global` enabled are always closable.
     *
     * Generated from Godot docs: EditorDock.set_closable
     */
    fun setClosable(closable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setClosableBind, handle, closable)
    }

    /**
     * If `true`, the dock can be closed with the Close button in the context popup. Docks with
     * `global` enabled are always closable.
     *
     * Generated from Godot docs: EditorDock.is_closable
     */
    fun isClosable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isClosableBind, handle)
    }

    /**
     * The icon for the dock, as a name from the `EditorIcons` theme type in the editor theme. You can
     * find the list of available icons here (https://godot-editor-icons.github.io/).
     *
     * Generated from Godot docs: EditorDock.set_icon_name
     */
    fun setIconName(iconName: String) {
        ObjectCalls.ptrcallWithStringNameArg(setIconNameBind, handle, iconName)
    }

    /**
     * The icon for the dock, as a name from the `EditorIcons` theme type in the editor theme. You can
     * find the list of available icons here (https://godot-editor-icons.github.io/).
     *
     * Generated from Godot docs: EditorDock.get_icon_name
     */
    fun getIconName(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getIconNameBind, handle)
    }

    /**
     * The icon for the dock, as a texture. If specified, it will override `icon_name`.
     *
     * Generated from Godot docs: EditorDock.set_dock_icon
     */
    fun setDockIcon(icon: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setDockIconBind, handle, listOf(icon?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The icon for the dock, as a texture. If specified, it will override `icon_name`.
     *
     * Generated from Godot docs: EditorDock.get_dock_icon
     */
    fun getDockIcon(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getDockIconBind, handle))
    }

    /**
     * If `true`, the dock will always display an icon, regardless of
     * `EditorSettings.interface/editor/docks/dock_tab_style` or
     * `EditorSettings.interface/editor/docks/bottom_dock_tab_style`.
     *
     * Generated from Godot docs: EditorDock.set_force_show_icon
     */
    fun setForceShowIcon(force: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setForceShowIconBind, handle, force)
    }

    /**
     * If `true`, the dock will always display an icon, regardless of
     * `EditorSettings.interface/editor/docks/dock_tab_style` or
     * `EditorSettings.interface/editor/docks/bottom_dock_tab_style`.
     *
     * Generated from Godot docs: EditorDock.get_force_show_icon
     */
    fun getForceShowIcon(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getForceShowIconBind, handle)
    }

    /**
     * The color of the dock tab's title. If its alpha is `0.0`, the default font color will be used.
     *
     * Generated from Godot docs: EditorDock.set_title_color
     */
    fun setTitleColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setTitleColorBind, handle, color)
    }

    /**
     * The color of the dock tab's title. If its alpha is `0.0`, the default font color will be used.
     *
     * Generated from Godot docs: EditorDock.get_title_color
     */
    fun getTitleColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getTitleColorBind, handle)
    }

    /**
     * The shortcut used to open the dock.
     *
     * Generated from Godot docs: EditorDock.set_dock_shortcut
     */
    fun setDockShortcut(shortcut: Shortcut?) {
        ObjectCalls.ptrcallWithObjectArgs(setDockShortcutBind, handle, listOf(shortcut?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The shortcut used to open the dock.
     *
     * Generated from Godot docs: EditorDock.get_dock_shortcut
     */
    fun getDockShortcut(): Shortcut? {
        return Shortcut.wrap(ObjectCalls.ptrcallNoArgsRetObject(getDockShortcutBind, handle))
    }

    /**
     * The default dock slot used when adding the dock with `EditorPlugin.add_dock`. After the dock is
     * added, it can be moved to a different slot and the editor will automatically remember its
     * position between sessions. If you remove and re-add the dock, it will be reset to default.
     *
     * Generated from Godot docs: EditorDock.set_default_slot
     */
    fun setDefaultSlot(slot: Long) {
        ObjectCalls.ptrcallWithLongArg(setDefaultSlotBind, handle, slot)
    }

    /**
     * The default dock slot used when adding the dock with `EditorPlugin.add_dock`. After the dock is
     * added, it can be moved to a different slot and the editor will automatically remember its
     * position between sessions. If you remove and re-add the dock, it will be reset to default.
     *
     * Generated from Godot docs: EditorDock.get_default_slot
     */
    fun getDefaultSlot(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDefaultSlotBind, handle)
    }

    /**
     * The available layouts for this dock, as a bitmask. By default, the dock allows vertical and
     * floating layouts.
     *
     * Generated from Godot docs: EditorDock.set_available_layouts
     */
    fun setAvailableLayouts(layouts: Long) {
        ObjectCalls.ptrcallWithLongArg(setAvailableLayoutsBind, handle, layouts)
    }

    /**
     * The available layouts for this dock, as a bitmask. By default, the dock allows vertical and
     * floating layouts.
     *
     * Generated from Godot docs: EditorDock.get_available_layouts
     */
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
