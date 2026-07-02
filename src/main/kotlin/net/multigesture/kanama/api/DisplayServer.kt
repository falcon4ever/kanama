package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3i

/**
 * A server interface for low-level window management.
 *
 * Generated from Godot docs: DisplayServer
 */
object DisplayServer {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("DisplayServer")
    }

    const val INVALID_SCREEN: Long = -1L
    const val SCREEN_WITH_MOUSE_FOCUS: Long = -4L
    const val SCREEN_WITH_KEYBOARD_FOCUS: Long = -3L
    const val SCREEN_PRIMARY: Long = -2L
    const val SCREEN_OF_MAIN_WINDOW: Long = -1L
    const val MAIN_WINDOW_ID: Long = 0L
    const val INVALID_WINDOW_ID: Long = -1L
    const val INVALID_INDICATOR_ID: Long = -1L
    const val FEATURE_GLOBAL_MENU: Long = 0L
    const val FEATURE_SUBWINDOWS: Long = 1L
    const val FEATURE_TOUCHSCREEN: Long = 2L
    const val FEATURE_MOUSE: Long = 3L
    const val FEATURE_MOUSE_WARP: Long = 4L
    const val FEATURE_CLIPBOARD: Long = 5L
    const val FEATURE_VIRTUAL_KEYBOARD: Long = 6L
    const val FEATURE_CURSOR_SHAPE: Long = 7L
    const val FEATURE_CUSTOM_CURSOR_SHAPE: Long = 8L
    const val FEATURE_NATIVE_DIALOG: Long = 9L
    const val FEATURE_IME: Long = 10L
    const val FEATURE_WINDOW_TRANSPARENCY: Long = 11L
    const val FEATURE_HIDPI: Long = 12L
    const val FEATURE_ICON: Long = 13L
    const val FEATURE_NATIVE_ICON: Long = 14L
    const val FEATURE_ORIENTATION: Long = 15L
    const val FEATURE_SWAP_BUFFERS: Long = 16L
    const val FEATURE_CLIPBOARD_PRIMARY: Long = 18L
    const val FEATURE_TEXT_TO_SPEECH: Long = 19L
    const val FEATURE_EXTEND_TO_TITLE: Long = 20L
    const val FEATURE_SCREEN_CAPTURE: Long = 21L
    const val FEATURE_STATUS_INDICATOR: Long = 22L
    const val FEATURE_NATIVE_HELP: Long = 23L
    const val FEATURE_NATIVE_DIALOG_INPUT: Long = 24L
    const val FEATURE_NATIVE_DIALOG_FILE: Long = 25L
    const val FEATURE_NATIVE_DIALOG_FILE_EXTRA: Long = 26L
    const val FEATURE_WINDOW_DRAG: Long = 27L
    const val FEATURE_SCREEN_EXCLUDE_FROM_CAPTURE: Long = 28L
    const val FEATURE_WINDOW_EMBEDDING: Long = 29L
    const val FEATURE_NATIVE_DIALOG_FILE_MIME: Long = 30L
    const val FEATURE_EMOJI_AND_SYMBOL_PICKER: Long = 31L
    const val FEATURE_NATIVE_COLOR_PICKER: Long = 32L
    const val FEATURE_SELF_FITTING_WINDOWS: Long = 33L
    const val FEATURE_ACCESSIBILITY_SCREEN_READER: Long = 34L
    const val FEATURE_HDR_OUTPUT: Long = 35L
    const val FEATURE_PIP_MODE: Long = 36L
    const val ROLE_UNKNOWN: Long = 0L
    const val ROLE_DEFAULT_BUTTON: Long = 1L
    const val ROLE_AUDIO: Long = 2L
    const val ROLE_VIDEO: Long = 3L
    const val ROLE_STATIC_TEXT: Long = 4L
    const val ROLE_CONTAINER: Long = 5L
    const val ROLE_PANEL: Long = 6L
    const val ROLE_BUTTON: Long = 7L
    const val ROLE_LINK: Long = 8L
    const val ROLE_CHECK_BOX: Long = 9L
    const val ROLE_RADIO_BUTTON: Long = 10L
    const val ROLE_CHECK_BUTTON: Long = 11L
    const val ROLE_SCROLL_BAR: Long = 12L
    const val ROLE_SCROLL_VIEW: Long = 13L
    const val ROLE_SPLITTER: Long = 14L
    const val ROLE_SLIDER: Long = 15L
    const val ROLE_SPIN_BUTTON: Long = 16L
    const val ROLE_PROGRESS_INDICATOR: Long = 17L
    const val ROLE_TEXT_FIELD: Long = 18L
    const val ROLE_MULTILINE_TEXT_FIELD: Long = 19L
    const val ROLE_COLOR_PICKER: Long = 20L
    const val ROLE_TABLE: Long = 21L
    const val ROLE_CELL: Long = 22L
    const val ROLE_ROW: Long = 23L
    const val ROLE_ROW_GROUP: Long = 24L
    const val ROLE_ROW_HEADER: Long = 25L
    const val ROLE_COLUMN_HEADER: Long = 26L
    const val ROLE_TREE: Long = 27L
    const val ROLE_TREE_ITEM: Long = 28L
    const val ROLE_LIST: Long = 29L
    const val ROLE_LIST_ITEM: Long = 30L
    const val ROLE_LIST_BOX: Long = 31L
    const val ROLE_LIST_BOX_OPTION: Long = 32L
    const val ROLE_TAB_BAR: Long = 33L
    const val ROLE_TAB: Long = 34L
    const val ROLE_TAB_PANEL: Long = 35L
    const val ROLE_MENU_BAR: Long = 36L
    const val ROLE_MENU: Long = 37L
    const val ROLE_MENU_ITEM: Long = 38L
    const val ROLE_MENU_ITEM_CHECK_BOX: Long = 39L
    const val ROLE_MENU_ITEM_RADIO: Long = 40L
    const val ROLE_IMAGE: Long = 41L
    const val ROLE_WINDOW: Long = 42L
    const val ROLE_TITLE_BAR: Long = 43L
    const val ROLE_DIALOG: Long = 44L
    const val ROLE_TOOLTIP: Long = 45L
    const val ROLE_REGION: Long = 46L
    const val ROLE_TEXT_RUN: Long = 47L
    const val POPUP_MENU: Long = 0L
    const val POPUP_LIST: Long = 1L
    const val POPUP_TREE: Long = 2L
    const val POPUP_DIALOG: Long = 3L
    const val FLAG_HIDDEN: Long = 0L
    const val FLAG_MULTISELECTABLE: Long = 1L
    const val FLAG_REQUIRED: Long = 2L
    const val FLAG_VISITED: Long = 3L
    const val FLAG_BUSY: Long = 4L
    const val FLAG_MODAL: Long = 5L
    const val FLAG_TOUCH_PASSTHROUGH: Long = 6L
    const val FLAG_READONLY: Long = 7L
    const val FLAG_DISABLED: Long = 8L
    const val FLAG_CLIPS_CHILDREN: Long = 9L
    const val ACTION_CLICK: Long = 0L
    const val ACTION_FOCUS: Long = 1L
    const val ACTION_BLUR: Long = 2L
    const val ACTION_COLLAPSE: Long = 3L
    const val ACTION_EXPAND: Long = 4L
    const val ACTION_DECREMENT: Long = 5L
    const val ACTION_INCREMENT: Long = 6L
    const val ACTION_HIDE_TOOLTIP: Long = 7L
    const val ACTION_SHOW_TOOLTIP: Long = 8L
    const val ACTION_SET_TEXT_SELECTION: Long = 9L
    const val ACTION_REPLACE_SELECTED_TEXT: Long = 10L
    const val ACTION_SCROLL_BACKWARD: Long = 11L
    const val ACTION_SCROLL_DOWN: Long = 12L
    const val ACTION_SCROLL_FORWARD: Long = 13L
    const val ACTION_SCROLL_LEFT: Long = 14L
    const val ACTION_SCROLL_RIGHT: Long = 15L
    const val ACTION_SCROLL_UP: Long = 16L
    const val ACTION_SCROLL_INTO_VIEW: Long = 17L
    const val ACTION_SCROLL_TO_POINT: Long = 18L
    const val ACTION_SET_SCROLL_OFFSET: Long = 19L
    const val ACTION_SET_VALUE: Long = 20L
    const val ACTION_SHOW_CONTEXT_MENU: Long = 21L
    const val ACTION_CUSTOM: Long = 22L
    const val LIVE_OFF: Long = 0L
    const val LIVE_POLITE: Long = 1L
    const val LIVE_ASSERTIVE: Long = 2L
    const val SCROLL_UNIT_ITEM: Long = 0L
    const val SCROLL_UNIT_PAGE: Long = 1L
    const val SCROLL_HINT_TOP_LEFT: Long = 0L
    const val SCROLL_HINT_BOTTOM_RIGHT: Long = 1L
    const val SCROLL_HINT_TOP_EDGE: Long = 2L
    const val SCROLL_HINT_BOTTOM_EDGE: Long = 3L
    const val SCROLL_HINT_LEFT_EDGE: Long = 4L
    const val SCROLL_HINT_RIGHT_EDGE: Long = 5L
    const val MOUSE_MODE_VISIBLE: Long = 0L
    const val MOUSE_MODE_HIDDEN: Long = 1L
    const val MOUSE_MODE_CAPTURED: Long = 2L
    const val MOUSE_MODE_CONFINED: Long = 3L
    const val MOUSE_MODE_CONFINED_HIDDEN: Long = 4L
    const val MOUSE_MODE_MAX: Long = 5L
    const val SCREEN_LANDSCAPE: Long = 0L
    const val SCREEN_PORTRAIT: Long = 1L
    const val SCREEN_REVERSE_LANDSCAPE: Long = 2L
    const val SCREEN_REVERSE_PORTRAIT: Long = 3L
    const val SCREEN_SENSOR_LANDSCAPE: Long = 4L
    const val SCREEN_SENSOR_PORTRAIT: Long = 5L
    const val SCREEN_SENSOR: Long = 6L
    const val KEYBOARD_TYPE_DEFAULT: Long = 0L
    const val KEYBOARD_TYPE_MULTILINE: Long = 1L
    const val KEYBOARD_TYPE_NUMBER: Long = 2L
    const val KEYBOARD_TYPE_NUMBER_DECIMAL: Long = 3L
    const val KEYBOARD_TYPE_PHONE: Long = 4L
    const val KEYBOARD_TYPE_EMAIL_ADDRESS: Long = 5L
    const val KEYBOARD_TYPE_PASSWORD: Long = 6L
    const val KEYBOARD_TYPE_URL: Long = 7L
    const val CURSOR_ARROW: Long = 0L
    const val CURSOR_IBEAM: Long = 1L
    const val CURSOR_POINTING_HAND: Long = 2L
    const val CURSOR_CROSS: Long = 3L
    const val CURSOR_WAIT: Long = 4L
    const val CURSOR_BUSY: Long = 5L
    const val CURSOR_DRAG: Long = 6L
    const val CURSOR_CAN_DROP: Long = 7L
    const val CURSOR_FORBIDDEN: Long = 8L
    const val CURSOR_VSIZE: Long = 9L
    const val CURSOR_HSIZE: Long = 10L
    const val CURSOR_BDIAGSIZE: Long = 11L
    const val CURSOR_FDIAGSIZE: Long = 12L
    const val CURSOR_MOVE: Long = 13L
    const val CURSOR_VSPLIT: Long = 14L
    const val CURSOR_HSPLIT: Long = 15L
    const val CURSOR_HELP: Long = 16L
    const val CURSOR_MAX: Long = 17L
    const val FILE_DIALOG_MODE_OPEN_FILE: Long = 0L
    const val FILE_DIALOG_MODE_OPEN_FILES: Long = 1L
    const val FILE_DIALOG_MODE_OPEN_DIR: Long = 2L
    const val FILE_DIALOG_MODE_OPEN_ANY: Long = 3L
    const val FILE_DIALOG_MODE_SAVE_FILE: Long = 4L
    const val WINDOW_MODE_WINDOWED: Long = 0L
    const val WINDOW_MODE_MINIMIZED: Long = 1L
    const val WINDOW_MODE_MAXIMIZED: Long = 2L
    const val WINDOW_MODE_FULLSCREEN: Long = 3L
    const val WINDOW_MODE_EXCLUSIVE_FULLSCREEN: Long = 4L
    const val PROGRESS_STATE_NOPROGRESS: Long = 0L
    const val PROGRESS_STATE_INDETERMINATE: Long = 1L
    const val PROGRESS_STATE_NORMAL: Long = 2L
    const val PROGRESS_STATE_ERROR: Long = 3L
    const val PROGRESS_STATE_PAUSED: Long = 4L
    const val WINDOW_FLAG_RESIZE_DISABLED: Long = 0L
    const val WINDOW_FLAG_BORDERLESS: Long = 1L
    const val WINDOW_FLAG_ALWAYS_ON_TOP: Long = 2L
    const val WINDOW_FLAG_TRANSPARENT: Long = 3L
    const val WINDOW_FLAG_NO_FOCUS: Long = 4L
    const val WINDOW_FLAG_POPUP: Long = 5L
    const val WINDOW_FLAG_EXTEND_TO_TITLE: Long = 6L
    const val WINDOW_FLAG_MOUSE_PASSTHROUGH: Long = 7L
    const val WINDOW_FLAG_SHARP_CORNERS: Long = 8L
    const val WINDOW_FLAG_EXCLUDE_FROM_CAPTURE: Long = 9L
    const val WINDOW_FLAG_POPUP_WM_HINT: Long = 10L
    const val WINDOW_FLAG_MINIMIZE_DISABLED: Long = 11L
    const val WINDOW_FLAG_MAXIMIZE_DISABLED: Long = 12L
    const val WINDOW_FLAG_MAX: Long = 13L
    const val WINDOW_EVENT_MOUSE_ENTER: Long = 0L
    const val WINDOW_EVENT_MOUSE_EXIT: Long = 1L
    const val WINDOW_EVENT_FOCUS_IN: Long = 2L
    const val WINDOW_EVENT_FOCUS_OUT: Long = 3L
    const val WINDOW_EVENT_CLOSE_REQUEST: Long = 4L
    const val WINDOW_EVENT_GO_BACK_REQUEST: Long = 5L
    const val WINDOW_EVENT_DPI_CHANGE: Long = 6L
    const val WINDOW_EVENT_TITLEBAR_CHANGE: Long = 7L
    const val WINDOW_EVENT_FORCE_CLOSE: Long = 8L
    const val WINDOW_EVENT_OUTPUT_MAX_LINEAR_VALUE_CHANGED: Long = 9L
    const val WINDOW_EDGE_TOP_LEFT: Long = 0L
    const val WINDOW_EDGE_TOP: Long = 1L
    const val WINDOW_EDGE_TOP_RIGHT: Long = 2L
    const val WINDOW_EDGE_LEFT: Long = 3L
    const val WINDOW_EDGE_RIGHT: Long = 4L
    const val WINDOW_EDGE_BOTTOM_LEFT: Long = 5L
    const val WINDOW_EDGE_BOTTOM: Long = 6L
    const val WINDOW_EDGE_BOTTOM_RIGHT: Long = 7L
    const val WINDOW_EDGE_MAX: Long = 8L
    const val VSYNC_DISABLED: Long = 0L
    const val VSYNC_ENABLED: Long = 1L
    const val VSYNC_ADAPTIVE: Long = 2L
    const val VSYNC_MAILBOX: Long = 3L
    const val DISPLAY_HANDLE: Long = 0L
    const val WINDOW_HANDLE: Long = 1L
    const val WINDOW_VIEW: Long = 2L
    const val OPENGL_CONTEXT: Long = 3L
    const val EGL_DISPLAY: Long = 4L
    const val EGL_CONFIG: Long = 5L
    const val GLX_VISUALID: Long = 6L
    const val GLX_FBCONFIG: Long = 7L
    const val TTS_UTTERANCE_STARTED: Long = 0L
    const val TTS_UTTERANCE_ENDED: Long = 1L
    const val TTS_UTTERANCE_CANCELED: Long = 2L
    const val TTS_UTTERANCE_BOUNDARY: Long = 3L

    @JvmStatic
    fun hasFeature(feature: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(hasFeatureBind, singleton, feature)
    }

    @JvmStatic
    fun getName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getNameBind, singleton)
    }

    @JvmStatic
    fun helpSetSearchCallbacks(searchCallback: GodotCallable, actionCallback: GodotCallable) {
        ObjectCalls.ptrcallWithTwoCallableArgs(helpSetSearchCallbacksBind, singleton, searchCallback.target.handle, searchCallback.method, actionCallback.target.handle, actionCallback.method)
    }

    @JvmStatic
    fun globalMenuSetPopupCallbacks(menuRoot: String, openCallback: GodotCallable, closeCallback: GodotCallable) {
        ObjectCalls.ptrcallWithStringTwoCallableArgs(globalMenuSetPopupCallbacksBind, singleton, menuRoot, openCallback.target.handle, openCallback.method, closeCallback.target.handle, closeCallback.method)
    }

    @JvmStatic
    fun globalMenuAddSubmenuItem(menuRoot: String, label: String, submenu: String, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithThreeStringAndIntArgRetInt(globalMenuAddSubmenuItemBind, singleton, menuRoot, label, submenu, index)
    }

    @JvmStatic
    fun globalMenuAddItem(menuRoot: String, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithTwoStringTwoCallableVariantLongIntArgsRetInt(globalMenuAddItemBind, singleton, menuRoot, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
    }

    @JvmStatic
    fun globalMenuAddCheckItem(menuRoot: String, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithTwoStringTwoCallableVariantLongIntArgsRetInt(globalMenuAddCheckItemBind, singleton, menuRoot, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
    }

    @JvmStatic
    fun globalMenuAddIconItem(menuRoot: String, icon: Texture2D?, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithStringObjectStringTwoCallableVariantLongIntArgsRetInt(globalMenuAddIconItemBind, singleton, menuRoot, icon?.requireOpenHandle() ?: MemorySegment.NULL, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
    }

    @JvmStatic
    fun globalMenuAddIconCheckItem(menuRoot: String, icon: Texture2D?, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithStringObjectStringTwoCallableVariantLongIntArgsRetInt(globalMenuAddIconCheckItemBind, singleton, menuRoot, icon?.requireOpenHandle() ?: MemorySegment.NULL, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
    }

    @JvmStatic
    fun globalMenuAddRadioCheckItem(menuRoot: String, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithTwoStringTwoCallableVariantLongIntArgsRetInt(globalMenuAddRadioCheckItemBind, singleton, menuRoot, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
    }

    @JvmStatic
    fun globalMenuAddIconRadioCheckItem(menuRoot: String, icon: Texture2D?, label: String, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithStringObjectStringTwoCallableVariantLongIntArgsRetInt(globalMenuAddIconRadioCheckItemBind, singleton, menuRoot, icon?.requireOpenHandle() ?: MemorySegment.NULL, label, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
    }

    @JvmStatic
    fun globalMenuAddMultistateItem(menuRoot: String, label: String, maxStates: Int, defaultState: Int, callback: GodotCallable, keyCallback: GodotCallable, tag: Any? = null, accelerator: Long = 0L, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithTwoStringTwoIntTwoCallableVariantLongIntArgsRetInt(globalMenuAddMultistateItemBind, singleton, menuRoot, label, maxStates, defaultState, callback.target.handle, callback.method, keyCallback.target.handle, keyCallback.method, tag, accelerator, index)
    }

    @JvmStatic
    fun globalMenuAddSeparator(menuRoot: String, index: Int = -1): Int {
        return ObjectCalls.ptrcallWithStringAndIntArgRetInt(globalMenuAddSeparatorBind, singleton, menuRoot, index)
    }

    @JvmStatic
    fun globalMenuGetItemIndexFromText(menuRoot: String, text: String): Int {
        return ObjectCalls.ptrcallWithTwoStringArgsRetInt(globalMenuGetItemIndexFromTextBind, singleton, menuRoot, text)
    }

    @JvmStatic
    fun globalMenuGetItemIndexFromTag(menuRoot: String, tag: Any?): Int {
        return ObjectCalls.ptrcallWithStringAndVariantArgRetInt(globalMenuGetItemIndexFromTagBind, singleton, menuRoot, tag)
    }

    @JvmStatic
    fun globalMenuIsItemChecked(menuRoot: String, idx: Int): Boolean {
        return ObjectCalls.ptrcallWithStringAndIntArgRetBool(globalMenuIsItemCheckedBind, singleton, menuRoot, idx)
    }

    @JvmStatic
    fun globalMenuIsItemCheckable(menuRoot: String, idx: Int): Boolean {
        return ObjectCalls.ptrcallWithStringAndIntArgRetBool(globalMenuIsItemCheckableBind, singleton, menuRoot, idx)
    }

    @JvmStatic
    fun globalMenuIsItemRadioCheckable(menuRoot: String, idx: Int): Boolean {
        return ObjectCalls.ptrcallWithStringAndIntArgRetBool(globalMenuIsItemRadioCheckableBind, singleton, menuRoot, idx)
    }

    @JvmStatic
    fun globalMenuGetItemCallback(menuRoot: String, idx: Int): GodotCallable? {
        return ObjectCalls.ptrcallWithStringIntArgsRetCallable(globalMenuGetItemCallbackBind, singleton, menuRoot, idx)
    }

    @JvmStatic
    fun globalMenuGetItemKeyCallback(menuRoot: String, idx: Int): GodotCallable? {
        return ObjectCalls.ptrcallWithStringIntArgsRetCallable(globalMenuGetItemKeyCallbackBind, singleton, menuRoot, idx)
    }

    @JvmStatic
    fun globalMenuGetItemTag(menuRoot: String, idx: Int): Any? {
        return ObjectCalls.ptrcallWithStringAndIntArgRetVariantScalar(globalMenuGetItemTagBind, singleton, menuRoot, idx)
    }

    @JvmStatic
    fun globalMenuGetItemText(menuRoot: String, idx: Int): String {
        return ObjectCalls.ptrcallWithStringAndIntArgRetString(globalMenuGetItemTextBind, singleton, menuRoot, idx)
    }

    @JvmStatic
    fun globalMenuGetItemSubmenu(menuRoot: String, idx: Int): String {
        return ObjectCalls.ptrcallWithStringAndIntArgRetString(globalMenuGetItemSubmenuBind, singleton, menuRoot, idx)
    }

    @JvmStatic
    fun globalMenuGetItemAccelerator(menuRoot: String, idx: Int): Long {
        return ObjectCalls.ptrcallWithStringAndIntArgRetLong(globalMenuGetItemAcceleratorBind, singleton, menuRoot, idx)
    }

    @JvmStatic
    fun globalMenuIsItemDisabled(menuRoot: String, idx: Int): Boolean {
        return ObjectCalls.ptrcallWithStringAndIntArgRetBool(globalMenuIsItemDisabledBind, singleton, menuRoot, idx)
    }

    @JvmStatic
    fun globalMenuIsItemHidden(menuRoot: String, idx: Int): Boolean {
        return ObjectCalls.ptrcallWithStringAndIntArgRetBool(globalMenuIsItemHiddenBind, singleton, menuRoot, idx)
    }

    @JvmStatic
    fun globalMenuGetItemTooltip(menuRoot: String, idx: Int): String {
        return ObjectCalls.ptrcallWithStringAndIntArgRetString(globalMenuGetItemTooltipBind, singleton, menuRoot, idx)
    }

    @JvmStatic
    fun globalMenuGetItemState(menuRoot: String, idx: Int): Int {
        return ObjectCalls.ptrcallWithStringAndIntArgRetInt(globalMenuGetItemStateBind, singleton, menuRoot, idx)
    }

    @JvmStatic
    fun globalMenuGetItemMaxStates(menuRoot: String, idx: Int): Int {
        return ObjectCalls.ptrcallWithStringAndIntArgRetInt(globalMenuGetItemMaxStatesBind, singleton, menuRoot, idx)
    }

    @JvmStatic
    fun globalMenuGetItemIcon(menuRoot: String, idx: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithStringAndIntArgRetObject(globalMenuGetItemIconBind, singleton, menuRoot, idx))
    }

    @JvmStatic
    fun globalMenuGetItemIndentationLevel(menuRoot: String, idx: Int): Int {
        return ObjectCalls.ptrcallWithStringAndIntArgRetInt(globalMenuGetItemIndentationLevelBind, singleton, menuRoot, idx)
    }

    @JvmStatic
    fun globalMenuSetItemChecked(menuRoot: String, idx: Int, checked: Boolean) {
        ObjectCalls.ptrcallWithStringIntAndBoolArgs(globalMenuSetItemCheckedBind, singleton, menuRoot, idx, checked)
    }

    @JvmStatic
    fun globalMenuSetItemCheckable(menuRoot: String, idx: Int, checkable: Boolean) {
        ObjectCalls.ptrcallWithStringIntAndBoolArgs(globalMenuSetItemCheckableBind, singleton, menuRoot, idx, checkable)
    }

    @JvmStatic
    fun globalMenuSetItemRadioCheckable(menuRoot: String, idx: Int, checkable: Boolean) {
        ObjectCalls.ptrcallWithStringIntAndBoolArgs(globalMenuSetItemRadioCheckableBind, singleton, menuRoot, idx, checkable)
    }

    @JvmStatic
    fun globalMenuSetItemCallback(menuRoot: String, idx: Int, callback: GodotCallable) {
        ObjectCalls.ptrcallWithStringIntCallableArgs(globalMenuSetItemCallbackBind, singleton, menuRoot, idx, callback.target.handle, callback.method)
    }

    @JvmStatic
    fun globalMenuSetItemHoverCallbacks(menuRoot: String, idx: Int, callback: GodotCallable) {
        ObjectCalls.ptrcallWithStringIntCallableArgs(globalMenuSetItemHoverCallbacksBind, singleton, menuRoot, idx, callback.target.handle, callback.method)
    }

    @JvmStatic
    fun globalMenuSetItemKeyCallback(menuRoot: String, idx: Int, keyCallback: GodotCallable) {
        ObjectCalls.ptrcallWithStringIntCallableArgs(globalMenuSetItemKeyCallbackBind, singleton, menuRoot, idx, keyCallback.target.handle, keyCallback.method)
    }

    @JvmStatic
    fun globalMenuSetItemTag(menuRoot: String, idx: Int, tag: Any?) {
        ObjectCalls.ptrcallWithStringIntAndVariantArg(globalMenuSetItemTagBind, singleton, menuRoot, idx, tag)
    }

    @JvmStatic
    fun globalMenuSetItemText(menuRoot: String, idx: Int, text: String) {
        ObjectCalls.ptrcallWithStringIntAndStringArgs(globalMenuSetItemTextBind, singleton, menuRoot, idx, text)
    }

    @JvmStatic
    fun globalMenuSetItemSubmenu(menuRoot: String, idx: Int, submenu: String) {
        ObjectCalls.ptrcallWithStringIntAndStringArgs(globalMenuSetItemSubmenuBind, singleton, menuRoot, idx, submenu)
    }

    @JvmStatic
    fun globalMenuSetItemAccelerator(menuRoot: String, idx: Int, keycode: Long) {
        ObjectCalls.ptrcallWithStringIntAndLongArgs(globalMenuSetItemAcceleratorBind, singleton, menuRoot, idx, keycode)
    }

    @JvmStatic
    fun globalMenuSetItemDisabled(menuRoot: String, idx: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithStringIntAndBoolArgs(globalMenuSetItemDisabledBind, singleton, menuRoot, idx, disabled)
    }

    @JvmStatic
    fun globalMenuSetItemHidden(menuRoot: String, idx: Int, hidden: Boolean) {
        ObjectCalls.ptrcallWithStringIntAndBoolArgs(globalMenuSetItemHiddenBind, singleton, menuRoot, idx, hidden)
    }

    @JvmStatic
    fun globalMenuSetItemTooltip(menuRoot: String, idx: Int, tooltip: String) {
        ObjectCalls.ptrcallWithStringIntAndStringArgs(globalMenuSetItemTooltipBind, singleton, menuRoot, idx, tooltip)
    }

    @JvmStatic
    fun globalMenuSetItemState(menuRoot: String, idx: Int, state: Int) {
        ObjectCalls.ptrcallWithStringIntAndIntArgs(globalMenuSetItemStateBind, singleton, menuRoot, idx, state)
    }

    @JvmStatic
    fun globalMenuSetItemMaxStates(menuRoot: String, idx: Int, maxStates: Int) {
        ObjectCalls.ptrcallWithStringIntAndIntArgs(globalMenuSetItemMaxStatesBind, singleton, menuRoot, idx, maxStates)
    }

    @JvmStatic
    fun globalMenuSetItemIcon(menuRoot: String, idx: Int, icon: Texture2D?) {
        ObjectCalls.ptrcallWithStringIntAndObjectArgs(globalMenuSetItemIconBind, singleton, menuRoot, idx, icon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    @JvmStatic
    fun globalMenuSetItemIndentationLevel(menuRoot: String, idx: Int, level: Int) {
        ObjectCalls.ptrcallWithStringIntAndIntArgs(globalMenuSetItemIndentationLevelBind, singleton, menuRoot, idx, level)
    }

    @JvmStatic
    fun globalMenuGetItemCount(menuRoot: String): Int {
        return ObjectCalls.ptrcallWithStringArgRetInt(globalMenuGetItemCountBind, singleton, menuRoot)
    }

    @JvmStatic
    fun globalMenuRemoveItem(menuRoot: String, idx: Int) {
        ObjectCalls.ptrcallWithStringAndIntArg(globalMenuRemoveItemBind, singleton, menuRoot, idx)
    }

    @JvmStatic
    fun globalMenuClear(menuRoot: String) {
        ObjectCalls.ptrcallWithStringArg(globalMenuClearBind, singleton, menuRoot)
    }

    @JvmStatic
    fun globalMenuGetSystemMenuRoots(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(globalMenuGetSystemMenuRootsBind, singleton)
    }

    @JvmStatic
    fun ttsIsSpeaking(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(ttsIsSpeakingBind, singleton)
    }

    @JvmStatic
    fun ttsIsPaused(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(ttsIsPausedBind, singleton)
    }

    @JvmStatic
    fun ttsGetVoices(): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallNoArgsRetDictionaryList(ttsGetVoicesBind, singleton)
    }

    @JvmStatic
    fun ttsGetVoicesForLanguage(language: String): List<String> {
        return ObjectCalls.ptrcallWithStringArgRetPackedStringList(ttsGetVoicesForLanguageBind, singleton, language)
    }

    @JvmStatic
    fun ttsSpeak(text: String, voice: String, volume: Int = 50, pitch: Double = 1.0, rate: Double = 1.0, utteranceId: Long = 0L, interrupt: Boolean = false) {
        ObjectCalls.ptrcallWithTwoStringIntTwoDoubleLongBoolArgs(ttsSpeakBind, singleton, text, voice, volume, pitch, rate, utteranceId, interrupt)
    }

    @JvmStatic
    fun ttsPause() {
        ObjectCalls.ptrcallNoArgs(ttsPauseBind, singleton)
    }

    @JvmStatic
    fun ttsResume() {
        ObjectCalls.ptrcallNoArgs(ttsResumeBind, singleton)
    }

    @JvmStatic
    fun ttsStop() {
        ObjectCalls.ptrcallNoArgs(ttsStopBind, singleton)
    }

    @JvmStatic
    fun ttsSetUtteranceCallback(event: Long, callable: GodotCallable) {
        ObjectCalls.ptrcallWithLongCallableArgs(ttsSetUtteranceCallbackBind, singleton, event, callable.target.handle, callable.method)
    }

    @JvmStatic
    fun isDarkModeSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDarkModeSupportedBind, singleton)
    }

    @JvmStatic
    fun isDarkMode(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDarkModeBind, singleton)
    }

    @JvmStatic
    fun getAccentColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getAccentColorBind, singleton)
    }

    @JvmStatic
    fun getBaseColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getBaseColorBind, singleton)
    }

    @JvmStatic
    fun setSystemThemeChangeCallback(callable: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(setSystemThemeChangeCallbackBind, singleton, callable.target.handle, callable.method)
    }

    @JvmStatic
    fun mouseSetMode(mouseMode: Long) {
        ObjectCalls.ptrcallWithLongArg(mouseSetModeBind, singleton, mouseMode)
    }

    @JvmStatic
    fun mouseGetMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(mouseGetModeBind, singleton)
    }

    @JvmStatic
    fun warpMouse(position: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(warpMouseBind, singleton, position)
    }

    @JvmStatic
    fun mouseGetPosition(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(mouseGetPositionBind, singleton)
    }

    @JvmStatic
    fun mouseGetButtonState(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(mouseGetButtonStateBind, singleton)
    }

    @JvmStatic
    fun clipboardSet(clipboard: String) {
        ObjectCalls.ptrcallWithStringArg(clipboardSetBind, singleton, clipboard)
    }

    @JvmStatic
    fun clipboardGet(): String {
        return ObjectCalls.ptrcallNoArgsRetString(clipboardGetBind, singleton)
    }

    @JvmStatic
    fun clipboardGetImage(): Image? {
        return Image.wrap(ObjectCalls.ptrcallNoArgsRetObject(clipboardGetImageBind, singleton))
    }

    @JvmStatic
    fun clipboardHas(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(clipboardHasBind, singleton)
    }

    @JvmStatic
    fun clipboardHasImage(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(clipboardHasImageBind, singleton)
    }

    @JvmStatic
    fun clipboardSetPrimary(clipboardPrimary: String) {
        ObjectCalls.ptrcallWithStringArg(clipboardSetPrimaryBind, singleton, clipboardPrimary)
    }

    @JvmStatic
    fun clipboardGetPrimary(): String {
        return ObjectCalls.ptrcallNoArgsRetString(clipboardGetPrimaryBind, singleton)
    }

    @JvmStatic
    fun getDisplayCutouts(): List<Rect2> {
        return ObjectCalls.ptrcallNoArgsRetRect2List(getDisplayCutoutsBind, singleton)
    }

    @JvmStatic
    fun getDisplaySafeArea(): Rect2i {
        return ObjectCalls.ptrcallNoArgsRetRect2i(getDisplaySafeAreaBind, singleton)
    }

    @JvmStatic
    fun getScreenCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getScreenCountBind, singleton)
    }

    @JvmStatic
    fun getPrimaryScreen(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPrimaryScreenBind, singleton)
    }

    @JvmStatic
    fun getKeyboardFocusScreen(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getKeyboardFocusScreenBind, singleton)
    }

    @JvmStatic
    fun getScreenFromRect(rect: Rect2): Int {
        return ObjectCalls.ptrcallWithRect2ArgRetInt(getScreenFromRectBind, singleton, rect)
    }

    @JvmStatic
    fun screenGetPosition(screen: Int = -1): Vector2i {
        return ObjectCalls.ptrcallWithIntArgRetVector2i(screenGetPositionBind, singleton, screen)
    }

    @JvmStatic
    fun screenGetSize(screen: Int = -1): Vector2i {
        return ObjectCalls.ptrcallWithIntArgRetVector2i(screenGetSizeBind, singleton, screen)
    }

    @JvmStatic
    fun screenGetUsableRect(screen: Int = -1): Rect2i {
        return ObjectCalls.ptrcallWithIntArgRetRect2i(screenGetUsableRectBind, singleton, screen)
    }

    @JvmStatic
    fun screenGetDpi(screen: Int = -1): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(screenGetDpiBind, singleton, screen)
    }

    @JvmStatic
    fun screenGetScale(screen: Int = -1): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(screenGetScaleBind, singleton, screen)
    }

    @JvmStatic
    fun isTouchscreenAvailable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTouchscreenAvailableBind, singleton)
    }

    @JvmStatic
    fun screenGetMaxScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(screenGetMaxScaleBind, singleton)
    }

    @JvmStatic
    fun screenGetRefreshRate(screen: Int = -1): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(screenGetRefreshRateBind, singleton, screen)
    }

    @JvmStatic
    fun screenGetPixel(position: Vector2i): Color {
        return ObjectCalls.ptrcallWithVector2iArgRetColor(screenGetPixelBind, singleton, position)
    }

    @JvmStatic
    fun screenGetImage(screen: Int = -1): Image? {
        return Image.wrap(ObjectCalls.ptrcallWithIntArgRetObject(screenGetImageBind, singleton, screen))
    }

    @JvmStatic
    fun screenGetImageRect(rect: Rect2i): Image? {
        return Image.wrap(ObjectCalls.ptrcallWithRect2iArgRetObject(screenGetImageRectBind, singleton, rect))
    }

    @JvmStatic
    fun screenSetOrientation(orientation: Long, screen: Int = -1) {
        ObjectCalls.ptrcallWithLongAndIntArgs(screenSetOrientationBind, singleton, orientation, screen)
    }

    @JvmStatic
    fun screenGetOrientation(screen: Int = -1): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(screenGetOrientationBind, singleton, screen)
    }

    @JvmStatic
    fun screenSetKeepOn(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(screenSetKeepOnBind, singleton, enable)
    }

    @JvmStatic
    fun screenIsKeptOn(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(screenIsKeptOnBind, singleton)
    }

    @JvmStatic
    fun getWindowList(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getWindowListBind, singleton)
    }

    @JvmStatic
    fun getWindowAtScreenPosition(position: Vector2i): Int {
        return ObjectCalls.ptrcallWithVector2iArgRetInt(getWindowAtScreenPositionBind, singleton, position)
    }

    @JvmStatic
    fun windowGetNativeHandle(handleType: Long, windowId: Int = 0): Long {
        return ObjectCalls.ptrcallWithLongAndIntArgsRetLong(windowGetNativeHandleBind, singleton, handleType, windowId)
    }

    @JvmStatic
    fun windowGetActivePopup(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(windowGetActivePopupBind, singleton)
    }

    @JvmStatic
    fun windowSetPopupSafeRect(window: Int, rect: Rect2i) {
        ObjectCalls.ptrcallWithIntAndRect2iArg(windowSetPopupSafeRectBind, singleton, window, rect)
    }

    @JvmStatic
    fun windowGetPopupSafeRect(window: Int): Rect2i {
        return ObjectCalls.ptrcallWithIntArgRetRect2i(windowGetPopupSafeRectBind, singleton, window)
    }

    @JvmStatic
    fun windowSetTitle(title: String, windowId: Int = 0) {
        ObjectCalls.ptrcallWithStringAndIntArg(windowSetTitleBind, singleton, title, windowId)
    }

    @JvmStatic
    fun windowGetTitleSize(title: String, windowId: Int = 0): Vector2i {
        return ObjectCalls.ptrcallWithStringAndIntArgRetVector2i(windowGetTitleSizeBind, singleton, title, windowId)
    }

    @JvmStatic
    fun windowSetMousePassthrough(region: List<Vector2>, windowId: Int = 0) {
        ObjectCalls.ptrcallWithPackedVector2ListAndIntArgs(windowSetMousePassthroughBind, singleton, region, windowId)
    }

    @JvmStatic
    fun windowGetCurrentScreen(windowId: Int = 0): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(windowGetCurrentScreenBind, singleton, windowId)
    }

    @JvmStatic
    fun windowSetCurrentScreen(screen: Int, windowId: Int = 0) {
        ObjectCalls.ptrcallWithTwoIntArgs(windowSetCurrentScreenBind, singleton, screen, windowId)
    }

    @JvmStatic
    fun windowGetPosition(windowId: Int = 0): Vector2i {
        return ObjectCalls.ptrcallWithIntArgRetVector2i(windowGetPositionBind, singleton, windowId)
    }

    @JvmStatic
    fun windowGetPositionWithDecorations(windowId: Int = 0): Vector2i {
        return ObjectCalls.ptrcallWithIntArgRetVector2i(windowGetPositionWithDecorationsBind, singleton, windowId)
    }

    @JvmStatic
    fun windowSetPosition(position: Vector2i, windowId: Int = 0) {
        ObjectCalls.ptrcallWithVector2iAndIntArg(windowSetPositionBind, singleton, position, windowId)
    }

    @JvmStatic
    fun windowGetSize(windowId: Int = 0): Vector2i {
        return ObjectCalls.ptrcallWithIntArgRetVector2i(windowGetSizeBind, singleton, windowId)
    }

    @JvmStatic
    fun windowSetSize(size: Vector2i, windowId: Int = 0) {
        ObjectCalls.ptrcallWithVector2iAndIntArg(windowSetSizeBind, singleton, size, windowId)
    }

    @JvmStatic
    fun windowSetRectChangedCallback(callback: GodotCallable, windowId: Int = 0) {
        ObjectCalls.ptrcallWithCallableIntArgs(windowSetRectChangedCallbackBind, singleton, callback.target.handle, callback.method, windowId)
    }

    @JvmStatic
    fun windowSetWindowEventCallback(callback: GodotCallable, windowId: Int = 0) {
        ObjectCalls.ptrcallWithCallableIntArgs(windowSetWindowEventCallbackBind, singleton, callback.target.handle, callback.method, windowId)
    }

    @JvmStatic
    fun windowSetInputEventCallback(callback: GodotCallable, windowId: Int = 0) {
        ObjectCalls.ptrcallWithCallableIntArgs(windowSetInputEventCallbackBind, singleton, callback.target.handle, callback.method, windowId)
    }

    @JvmStatic
    fun windowSetInputTextCallback(callback: GodotCallable, windowId: Int = 0) {
        ObjectCalls.ptrcallWithCallableIntArgs(windowSetInputTextCallbackBind, singleton, callback.target.handle, callback.method, windowId)
    }

    @JvmStatic
    fun windowSetDropFilesCallback(callback: GodotCallable, windowId: Int = 0) {
        ObjectCalls.ptrcallWithCallableIntArgs(windowSetDropFilesCallbackBind, singleton, callback.target.handle, callback.method, windowId)
    }

    @JvmStatic
    fun windowGetAttachedInstanceId(windowId: Int = 0): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(windowGetAttachedInstanceIdBind, singleton, windowId)
    }

    @JvmStatic
    fun windowGetMaxSize(windowId: Int = 0): Vector2i {
        return ObjectCalls.ptrcallWithIntArgRetVector2i(windowGetMaxSizeBind, singleton, windowId)
    }

    @JvmStatic
    fun windowSetMaxSize(maxSize: Vector2i, windowId: Int = 0) {
        ObjectCalls.ptrcallWithVector2iAndIntArg(windowSetMaxSizeBind, singleton, maxSize, windowId)
    }

    @JvmStatic
    fun windowGetMinSize(windowId: Int = 0): Vector2i {
        return ObjectCalls.ptrcallWithIntArgRetVector2i(windowGetMinSizeBind, singleton, windowId)
    }

    @JvmStatic
    fun windowSetMinSize(minSize: Vector2i, windowId: Int = 0) {
        ObjectCalls.ptrcallWithVector2iAndIntArg(windowSetMinSizeBind, singleton, minSize, windowId)
    }

    @JvmStatic
    fun windowGetSizeWithDecorations(windowId: Int = 0): Vector2i {
        return ObjectCalls.ptrcallWithIntArgRetVector2i(windowGetSizeWithDecorationsBind, singleton, windowId)
    }

    @JvmStatic
    fun windowGetMode(windowId: Int = 0): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(windowGetModeBind, singleton, windowId)
    }

    @JvmStatic
    fun windowSetMode(mode: Long, windowId: Int = 0) {
        ObjectCalls.ptrcallWithLongAndIntArgs(windowSetModeBind, singleton, mode, windowId)
    }

    @JvmStatic
    fun windowSetFlag(flag: Long, enabled: Boolean, windowId: Int = 0) {
        ObjectCalls.ptrcallWithLongBoolIntArgs(windowSetFlagBind, singleton, flag, enabled, windowId)
    }

    @JvmStatic
    fun windowGetFlag(flag: Long, windowId: Int = 0): Boolean {
        return ObjectCalls.ptrcallWithLongAndIntArgsRetBool(windowGetFlagBind, singleton, flag, windowId)
    }

    @JvmStatic
    fun windowSetIcon(icon: Image?, windowId: Int = 0) {
        ObjectCalls.ptrcallWithObjectAndIntArg(windowSetIconBind, singleton, icon?.requireOpenHandle() ?: MemorySegment.NULL, windowId)
    }

    @JvmStatic
    fun windowSetWindowButtonsOffset(offset: Vector2i, windowId: Int = 0) {
        ObjectCalls.ptrcallWithVector2iAndIntArg(windowSetWindowButtonsOffsetBind, singleton, offset, windowId)
    }

    @JvmStatic
    fun windowGetSafeTitleMargins(windowId: Int = 0): Vector3i {
        return ObjectCalls.ptrcallWithIntArgRetVector3i(windowGetSafeTitleMarginsBind, singleton, windowId)
    }

    @JvmStatic
    fun windowRequestAttention(windowId: Int = 0) {
        ObjectCalls.ptrcallWithIntArg(windowRequestAttentionBind, singleton, windowId)
    }

    @JvmStatic
    fun windowSetTaskbarProgressValue(value: Double, windowId: Int = 0) {
        ObjectCalls.ptrcallWithDoubleAndIntArgs(windowSetTaskbarProgressValueBind, singleton, value, windowId)
    }

    @JvmStatic
    fun windowSetTaskbarProgressState(state: Long, windowId: Int = 0) {
        ObjectCalls.ptrcallWithLongAndIntArgs(windowSetTaskbarProgressStateBind, singleton, state, windowId)
    }

    @JvmStatic
    fun windowMoveToForeground(windowId: Int = 0) {
        ObjectCalls.ptrcallWithIntArg(windowMoveToForegroundBind, singleton, windowId)
    }

    @JvmStatic
    fun windowIsFocused(windowId: Int = 0): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(windowIsFocusedBind, singleton, windowId)
    }

    @JvmStatic
    fun windowCanDraw(windowId: Int = 0): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(windowCanDrawBind, singleton, windowId)
    }

    @JvmStatic
    fun windowSetTransient(windowId: Int, parentWindowId: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(windowSetTransientBind, singleton, windowId, parentWindowId)
    }

    @JvmStatic
    fun windowSetExclusive(windowId: Int, exclusive: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(windowSetExclusiveBind, singleton, windowId, exclusive)
    }

    @JvmStatic
    fun windowSetImeActive(active: Boolean, windowId: Int = 0) {
        ObjectCalls.ptrcallWithBoolAndIntArgs(windowSetImeActiveBind, singleton, active, windowId)
    }

    @JvmStatic
    fun windowSetImePosition(position: Vector2i, windowId: Int = 0) {
        ObjectCalls.ptrcallWithVector2iAndIntArg(windowSetImePositionBind, singleton, position, windowId)
    }

    @JvmStatic
    fun windowSetVsyncMode(vsyncMode: Long, windowId: Int = 0) {
        ObjectCalls.ptrcallWithLongAndIntArgs(windowSetVsyncModeBind, singleton, vsyncMode, windowId)
    }

    @JvmStatic
    fun windowGetVsyncMode(windowId: Int = 0): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(windowGetVsyncModeBind, singleton, windowId)
    }

    @JvmStatic
    fun windowIsHdrOutputSupported(windowId: Int = 0): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(windowIsHdrOutputSupportedBind, singleton, windowId)
    }

    @JvmStatic
    fun windowRequestHdrOutput(enable: Boolean, windowId: Int = 0) {
        ObjectCalls.ptrcallWithBoolAndIntArgs(windowRequestHdrOutputBind, singleton, enable, windowId)
    }

    @JvmStatic
    fun windowIsHdrOutputRequested(windowId: Int = 0): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(windowIsHdrOutputRequestedBind, singleton, windowId)
    }

    @JvmStatic
    fun windowIsHdrOutputEnabled(windowId: Int = 0): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(windowIsHdrOutputEnabledBind, singleton, windowId)
    }

    @JvmStatic
    fun windowSetHdrOutputReferenceLuminance(referenceLuminance: Double, windowId: Int = 0) {
        ObjectCalls.ptrcallWithDoubleAndIntArgs(windowSetHdrOutputReferenceLuminanceBind, singleton, referenceLuminance, windowId)
    }

    @JvmStatic
    fun windowGetHdrOutputReferenceLuminance(windowId: Int = 0): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(windowGetHdrOutputReferenceLuminanceBind, singleton, windowId)
    }

    @JvmStatic
    fun windowGetHdrOutputCurrentReferenceLuminance(windowId: Int = 0): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(windowGetHdrOutputCurrentReferenceLuminanceBind, singleton, windowId)
    }

    @JvmStatic
    fun windowSetHdrOutputMaxLuminance(maxLuminance: Double, windowId: Int = 0) {
        ObjectCalls.ptrcallWithDoubleAndIntArgs(windowSetHdrOutputMaxLuminanceBind, singleton, maxLuminance, windowId)
    }

    @JvmStatic
    fun windowGetHdrOutputMaxLuminance(windowId: Int = 0): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(windowGetHdrOutputMaxLuminanceBind, singleton, windowId)
    }

    @JvmStatic
    fun windowGetHdrOutputCurrentMaxLuminance(windowId: Int = 0): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(windowGetHdrOutputCurrentMaxLuminanceBind, singleton, windowId)
    }

    @JvmStatic
    fun windowGetOutputMaxLinearValue(windowId: Int = 0): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(windowGetOutputMaxLinearValueBind, singleton, windowId)
    }

    @JvmStatic
    fun windowIsMaximizeAllowed(windowId: Int = 0): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(windowIsMaximizeAllowedBind, singleton, windowId)
    }

    @JvmStatic
    fun windowMaximizeOnTitleDblClick(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(windowMaximizeOnTitleDblClickBind, singleton)
    }

    @JvmStatic
    fun windowMinimizeOnTitleDblClick(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(windowMinimizeOnTitleDblClickBind, singleton)
    }

    @JvmStatic
    fun windowStartDrag(windowId: Int = 0) {
        ObjectCalls.ptrcallWithIntArg(windowStartDragBind, singleton, windowId)
    }

    @JvmStatic
    fun windowStartResize(edge: Long, windowId: Int = 0) {
        ObjectCalls.ptrcallWithLongAndIntArgs(windowStartResizeBind, singleton, edge, windowId)
    }

    @JvmStatic
    fun windowSetColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(windowSetColorBind, singleton, color)
    }

    @JvmStatic
    fun accessibilityShouldIncreaseContrast(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(accessibilityShouldIncreaseContrastBind, singleton)
    }

    @JvmStatic
    fun accessibilityShouldReduceAnimation(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(accessibilityShouldReduceAnimationBind, singleton)
    }

    @JvmStatic
    fun accessibilityShouldReduceTransparency(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(accessibilityShouldReduceTransparencyBind, singleton)
    }

    @JvmStatic
    fun accessibilityScreenReaderActive(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(accessibilityScreenReaderActiveBind, singleton)
    }

    @JvmStatic
    fun accessibilityCreateElement(windowId: Int, role: Long): RID {
        return ObjectCalls.ptrcallWithIntAndLongArgsRetRID(accessibilityCreateElementBind, singleton, windowId, role)
    }

    @JvmStatic
    fun accessibilityCreateSubElement(parentRid: RID, role: Long, insertPos: Int = -1): RID {
        return ObjectCalls.ptrcallWithRIDLongIntArgsRetRID(accessibilityCreateSubElementBind, singleton, parentRid, role, insertPos)
    }

    @JvmStatic
    fun accessibilityCreateSubTextEditElements(parentRid: RID, shapedText: RID, minHeight: Double, insertPos: Int = -1, isLastLine: Boolean = false): RID {
        return ObjectCalls.ptrcallWithTwoRIDDoubleIntBoolArgsRetRID(accessibilityCreateSubTextEditElementsBind, singleton, parentRid, shapedText, minHeight, insertPos, isLastLine)
    }

    @JvmStatic
    fun accessibilityHasElement(id: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(accessibilityHasElementBind, singleton, id)
    }

    @JvmStatic
    fun accessibilityFreeElement(id: RID) {
        ObjectCalls.ptrcallWithRIDArg(accessibilityFreeElementBind, singleton, id)
    }

    @JvmStatic
    fun accessibilityElementSetMeta(id: RID, meta: Any?) {
        ObjectCalls.ptrcallWithRIDAndVariantArg(accessibilityElementSetMetaBind, singleton, id, meta)
    }

    @JvmStatic
    fun accessibilityElementGetMeta(id: RID): Any? {
        return ObjectCalls.ptrcallWithRIDArgRetVariantScalar(accessibilityElementGetMetaBind, singleton, id)
    }

    @JvmStatic
    fun accessibilitySetWindowRect(windowId: Int, rectOut: Rect2, rectIn: Rect2) {
        ObjectCalls.ptrcallWithIntRect2Rect2Args(accessibilitySetWindowRectBind, singleton, windowId, rectOut, rectIn)
    }

    @JvmStatic
    fun accessibilitySetWindowFocused(windowId: Int, focused: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(accessibilitySetWindowFocusedBind, singleton, windowId, focused)
    }

    @JvmStatic
    fun accessibilityUpdateSetFocus(id: RID) {
        ObjectCalls.ptrcallWithRIDArg(accessibilityUpdateSetFocusBind, singleton, id)
    }

    @JvmStatic
    fun accessibilityGetWindowRoot(windowId: Int): RID {
        return ObjectCalls.ptrcallWithIntArgRetRID(accessibilityGetWindowRootBind, singleton, windowId)
    }

    @JvmStatic
    fun accessibilityUpdateSetRole(id: RID, role: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(accessibilityUpdateSetRoleBind, singleton, id, role)
    }

    @JvmStatic
    fun accessibilityUpdateSetName(id: RID, name: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetNameBind, singleton, id, name)
    }

    @JvmStatic
    fun accessibilityUpdateSetExtraInfo(id: RID, name: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetExtraInfoBind, singleton, id, name)
    }

    @JvmStatic
    fun accessibilityUpdateSetDescription(id: RID, description: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetDescriptionBind, singleton, id, description)
    }

    @JvmStatic
    fun accessibilityUpdateSetValue(id: RID, value: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetValueBind, singleton, id, value)
    }

    @JvmStatic
    fun accessibilityUpdateSetTooltip(id: RID, tooltip: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetTooltipBind, singleton, id, tooltip)
    }

    @JvmStatic
    fun accessibilityUpdateSetBounds(id: RID, rect: Rect2) {
        ObjectCalls.ptrcallWithRIDAndRect2Arg(accessibilityUpdateSetBoundsBind, singleton, id, rect)
    }

    @JvmStatic
    fun accessibilityUpdateSetTransform(id: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(accessibilityUpdateSetTransformBind, singleton, id, transform)
    }

    @JvmStatic
    fun accessibilityUpdateAddChild(id: RID, childId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateAddChildBind, singleton, id, childId)
    }

    @JvmStatic
    fun accessibilityUpdateAddRelatedControls(id: RID, relatedId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateAddRelatedControlsBind, singleton, id, relatedId)
    }

    @JvmStatic
    fun accessibilityUpdateAddRelatedDetails(id: RID, relatedId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateAddRelatedDetailsBind, singleton, id, relatedId)
    }

    @JvmStatic
    fun accessibilityUpdateAddRelatedDescribedBy(id: RID, relatedId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateAddRelatedDescribedByBind, singleton, id, relatedId)
    }

    @JvmStatic
    fun accessibilityUpdateAddRelatedFlowTo(id: RID, relatedId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateAddRelatedFlowToBind, singleton, id, relatedId)
    }

    @JvmStatic
    fun accessibilityUpdateAddRelatedLabeledBy(id: RID, relatedId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateAddRelatedLabeledByBind, singleton, id, relatedId)
    }

    @JvmStatic
    fun accessibilityUpdateAddRelatedRadioGroup(id: RID, relatedId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateAddRelatedRadioGroupBind, singleton, id, relatedId)
    }

    @JvmStatic
    fun accessibilityUpdateSetActiveDescendant(id: RID, otherId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateSetActiveDescendantBind, singleton, id, otherId)
    }

    @JvmStatic
    fun accessibilityUpdateSetNextOnLine(id: RID, otherId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateSetNextOnLineBind, singleton, id, otherId)
    }

    @JvmStatic
    fun accessibilityUpdateSetPreviousOnLine(id: RID, otherId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateSetPreviousOnLineBind, singleton, id, otherId)
    }

    @JvmStatic
    fun accessibilityUpdateSetMemberOf(id: RID, groupId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateSetMemberOfBind, singleton, id, groupId)
    }

    @JvmStatic
    fun accessibilityUpdateSetInPageLinkTarget(id: RID, otherId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateSetInPageLinkTargetBind, singleton, id, otherId)
    }

    @JvmStatic
    fun accessibilityUpdateSetErrorMessage(id: RID, otherId: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(accessibilityUpdateSetErrorMessageBind, singleton, id, otherId)
    }

    @JvmStatic
    fun accessibilityUpdateSetLive(id: RID, live: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(accessibilityUpdateSetLiveBind, singleton, id, live)
    }

    @JvmStatic
    fun accessibilityUpdateAddAction(id: RID, action: Long, callable: GodotCallable) {
        ObjectCalls.ptrcallWithRIDLongCallableArgs(accessibilityUpdateAddActionBind, singleton, id, action, callable.target.handle, callable.method)
    }

    @JvmStatic
    fun accessibilityUpdateAddCustomAction(id: RID, actionId: Int, actionDescription: String) {
        ObjectCalls.ptrcallWithRIDIntAndStringArgs(accessibilityUpdateAddCustomActionBind, singleton, id, actionId, actionDescription)
    }

    @JvmStatic
    fun accessibilityUpdateSetTableRowCount(id: RID, count: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(accessibilityUpdateSetTableRowCountBind, singleton, id, count)
    }

    @JvmStatic
    fun accessibilityUpdateSetTableColumnCount(id: RID, count: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(accessibilityUpdateSetTableColumnCountBind, singleton, id, count)
    }

    @JvmStatic
    fun accessibilityUpdateSetTableRowIndex(id: RID, index: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(accessibilityUpdateSetTableRowIndexBind, singleton, id, index)
    }

    @JvmStatic
    fun accessibilityUpdateSetTableColumnIndex(id: RID, index: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(accessibilityUpdateSetTableColumnIndexBind, singleton, id, index)
    }

    @JvmStatic
    fun accessibilityUpdateSetTableCellPosition(id: RID, rowIndex: Int, columnIndex: Int) {
        ObjectCalls.ptrcallWithRIDAndTwoIntArgs(accessibilityUpdateSetTableCellPositionBind, singleton, id, rowIndex, columnIndex)
    }

    @JvmStatic
    fun accessibilityUpdateSetTableCellSpan(id: RID, rowSpan: Int, columnSpan: Int) {
        ObjectCalls.ptrcallWithRIDAndTwoIntArgs(accessibilityUpdateSetTableCellSpanBind, singleton, id, rowSpan, columnSpan)
    }

    @JvmStatic
    fun accessibilityUpdateSetListItemCount(id: RID, size: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(accessibilityUpdateSetListItemCountBind, singleton, id, size)
    }

    @JvmStatic
    fun accessibilityUpdateSetListItemIndex(id: RID, index: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(accessibilityUpdateSetListItemIndexBind, singleton, id, index)
    }

    @JvmStatic
    fun accessibilityUpdateSetListItemLevel(id: RID, level: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(accessibilityUpdateSetListItemLevelBind, singleton, id, level)
    }

    @JvmStatic
    fun accessibilityUpdateSetListItemSelected(id: RID, selected: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(accessibilityUpdateSetListItemSelectedBind, singleton, id, selected)
    }

    @JvmStatic
    fun accessibilityUpdateSetListItemExpanded(id: RID, expanded: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(accessibilityUpdateSetListItemExpandedBind, singleton, id, expanded)
    }

    @JvmStatic
    fun accessibilityUpdateSetPopupType(id: RID, popup: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(accessibilityUpdateSetPopupTypeBind, singleton, id, popup)
    }

    @JvmStatic
    fun accessibilityUpdateSetChecked(id: RID, checekd: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(accessibilityUpdateSetCheckedBind, singleton, id, checekd)
    }

    @JvmStatic
    fun accessibilityUpdateSetNumValue(id: RID, position: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(accessibilityUpdateSetNumValueBind, singleton, id, position)
    }

    @JvmStatic
    fun accessibilityUpdateSetNumRange(id: RID, min: Double, max: Double) {
        ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(accessibilityUpdateSetNumRangeBind, singleton, id, min, max)
    }

    @JvmStatic
    fun accessibilityUpdateSetNumStep(id: RID, step: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(accessibilityUpdateSetNumStepBind, singleton, id, step)
    }

    @JvmStatic
    fun accessibilityUpdateSetNumJump(id: RID, jump: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(accessibilityUpdateSetNumJumpBind, singleton, id, jump)
    }

    @JvmStatic
    fun accessibilityUpdateSetScrollX(id: RID, position: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(accessibilityUpdateSetScrollXBind, singleton, id, position)
    }

    @JvmStatic
    fun accessibilityUpdateSetScrollXRange(id: RID, min: Double, max: Double) {
        ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(accessibilityUpdateSetScrollXRangeBind, singleton, id, min, max)
    }

    @JvmStatic
    fun accessibilityUpdateSetScrollY(id: RID, position: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(accessibilityUpdateSetScrollYBind, singleton, id, position)
    }

    @JvmStatic
    fun accessibilityUpdateSetScrollYRange(id: RID, min: Double, max: Double) {
        ObjectCalls.ptrcallWithRIDAndTwoDoubleArgs(accessibilityUpdateSetScrollYRangeBind, singleton, id, min, max)
    }

    @JvmStatic
    fun accessibilityUpdateSetTextDecorations(id: RID, underline: Boolean, strikethrough: Boolean, overline: Boolean) {
        ObjectCalls.ptrcallWithRIDAndThreeBoolArgs(accessibilityUpdateSetTextDecorationsBind, singleton, id, underline, strikethrough, overline)
    }

    @JvmStatic
    fun accessibilityUpdateSetTextAlign(id: RID, align: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(accessibilityUpdateSetTextAlignBind, singleton, id, align)
    }

    @JvmStatic
    fun accessibilityUpdateSetTextSelection(id: RID, textStartId: RID, startChar: Int, textEndId: RID, endChar: Int) {
        ObjectCalls.ptrcallWithTwoRIDIntRIDIntArgs(accessibilityUpdateSetTextSelectionBind, singleton, id, textStartId, startChar, textEndId, endChar)
    }

    @JvmStatic
    fun accessibilityUpdateSetFlag(id: RID, flag: Long, value: Boolean) {
        ObjectCalls.ptrcallWithRIDLongAndBoolArgs(accessibilityUpdateSetFlagBind, singleton, id, flag, value)
    }

    @JvmStatic
    fun accessibilityUpdateSetClassname(id: RID, classname: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetClassnameBind, singleton, id, classname)
    }

    @JvmStatic
    fun accessibilityUpdateSetPlaceholder(id: RID, placeholder: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetPlaceholderBind, singleton, id, placeholder)
    }

    @JvmStatic
    fun accessibilityUpdateSetLanguage(id: RID, language: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetLanguageBind, singleton, id, language)
    }

    @JvmStatic
    fun accessibilityUpdateSetTextOrientation(id: RID, vertical: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(accessibilityUpdateSetTextOrientationBind, singleton, id, vertical)
    }

    @JvmStatic
    fun accessibilityUpdateSetListOrientation(id: RID, vertical: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(accessibilityUpdateSetListOrientationBind, singleton, id, vertical)
    }

    @JvmStatic
    fun accessibilityUpdateSetShortcut(id: RID, shortcut: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetShortcutBind, singleton, id, shortcut)
    }

    @JvmStatic
    fun accessibilityUpdateSetUrl(id: RID, url: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetUrlBind, singleton, id, url)
    }

    @JvmStatic
    fun accessibilityUpdateSetRoleDescription(id: RID, description: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetRoleDescriptionBind, singleton, id, description)
    }

    @JvmStatic
    fun accessibilityUpdateSetStateDescription(id: RID, description: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(accessibilityUpdateSetStateDescriptionBind, singleton, id, description)
    }

    @JvmStatic
    fun accessibilityUpdateSetColorValue(id: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(accessibilityUpdateSetColorValueBind, singleton, id, color)
    }

    @JvmStatic
    fun accessibilityUpdateSetBackgroundColor(id: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(accessibilityUpdateSetBackgroundColorBind, singleton, id, color)
    }

    @JvmStatic
    fun accessibilityUpdateSetForegroundColor(id: RID, color: Color) {
        ObjectCalls.ptrcallWithRIDAndColorArg(accessibilityUpdateSetForegroundColorBind, singleton, id, color)
    }

    @JvmStatic
    fun imeGetSelection(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(imeGetSelectionBind, singleton)
    }

    @JvmStatic
    fun imeGetText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(imeGetTextBind, singleton)
    }

    @JvmStatic
    fun virtualKeyboardShow(existingText: String, position: Rect2, type: Long = 0L, maxLength: Int = -1, cursorStart: Int = -1, cursorEnd: Int = -1) {
        ObjectCalls.ptrcallWithStringRect2LongThreeIntArgs(virtualKeyboardShowBind, singleton, existingText, position, type, maxLength, cursorStart, cursorEnd)
    }

    @JvmStatic
    fun virtualKeyboardHide() {
        ObjectCalls.ptrcallNoArgs(virtualKeyboardHideBind, singleton)
    }

    @JvmStatic
    fun virtualKeyboardGetHeight(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(virtualKeyboardGetHeightBind, singleton)
    }

    @JvmStatic
    fun hasHardwareKeyboard(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasHardwareKeyboardBind, singleton)
    }

    @JvmStatic
    fun setHardwareKeyboardConnectionChangeCallback(callable: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(setHardwareKeyboardConnectionChangeCallbackBind, singleton, callable.target.handle, callable.method)
    }

    @JvmStatic
    fun cursorSetShape(shape: Long) {
        ObjectCalls.ptrcallWithLongArg(cursorSetShapeBind, singleton, shape)
    }

    @JvmStatic
    fun cursorGetShape(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(cursorGetShapeBind, singleton)
    }

    @JvmStatic
    fun cursorSetCustomImage(cursor: Resource?, shape: Long = 0L, hotspot: Vector2 = Vector2(0f, 0f)) {
        ObjectCalls.ptrcallWithObjectLongAndVector2Arg(cursorSetCustomImageBind, singleton, cursor?.requireOpenHandle() ?: MemorySegment.NULL, shape, hotspot)
    }

    @JvmStatic
    fun getSwapCancelOk(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSwapCancelOkBind, singleton)
    }

    @JvmStatic
    fun enableForStealingFocus(processId: Long) {
        ObjectCalls.ptrcallWithLongArg(enableForStealingFocusBind, singleton, processId)
    }

    @JvmStatic
    fun dialogShow(title: String, description: String, buttons: List<String>, callback: GodotCallable): Long {
        return ObjectCalls.ptrcallWithTwoStringPackedStringListCallableArgsRetLong(dialogShowBind, singleton, title, description, buttons, callback.target.handle, callback.method)
    }

    @JvmStatic
    fun dialogInputText(title: String, description: String, existingText: String, callback: GodotCallable): Long {
        return ObjectCalls.ptrcallWithThreeStringCallableArgsRetLong(dialogInputTextBind, singleton, title, description, existingText, callback.target.handle, callback.method)
    }

    @JvmStatic
    fun fileDialogShow(title: String, currentDirectory: String, filename: String, showHidden: Boolean, mode: Long, filters: List<String>, callback: GodotCallable, parentWindowId: Int = 0): Long {
        return ObjectCalls.ptrcallWithThreeStringBoolLongPackedStringListCallableIntArgsRetLong(fileDialogShowBind, singleton, title, currentDirectory, filename, showHidden, mode, filters, callback.target.handle, callback.method, parentWindowId)
    }

    @JvmStatic
    fun fileDialogWithOptionsShow(title: String, currentDirectory: String, root: String, filename: String, showHidden: Boolean, mode: Long, filters: List<String>, options: List<Map<String, Any?>>, callback: GodotCallable, parentWindowId: Int = 0): Long {
        return ObjectCalls.ptrcallWithFourStringBoolLongPackedStringListDictionaryListCallableIntArgsRetLong(fileDialogWithOptionsShowBind, singleton, title, currentDirectory, root, filename, showHidden, mode, filters, options, callback.target.handle, callback.method, parentWindowId)
    }

    @JvmStatic
    /**
     * Plays the beep sound from the operative system, if possible. Because it comes from the OS, the
     * beep sound will be audible even if the application is muted. It may also be disabled for the
     * entire OS by the user. Note: This method is implemented on macOS, Linux (X11/Wayland), and
     * Windows.
     *
     * Generated from Godot docs: DisplayServer.beep
     */
    fun beep() {
        ObjectCalls.ptrcallNoArgs(beepBind, singleton)
    }

    @JvmStatic
    fun keyboardGetLayoutCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(keyboardGetLayoutCountBind, singleton)
    }

    @JvmStatic
    fun keyboardGetCurrentLayout(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(keyboardGetCurrentLayoutBind, singleton)
    }

    @JvmStatic
    fun keyboardSetCurrentLayout(index: Int) {
        ObjectCalls.ptrcallWithIntArg(keyboardSetCurrentLayoutBind, singleton, index)
    }

    @JvmStatic
    fun keyboardGetLayoutLanguage(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(keyboardGetLayoutLanguageBind, singleton, index)
    }

    @JvmStatic
    fun keyboardGetLayoutName(index: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(keyboardGetLayoutNameBind, singleton, index)
    }

    @JvmStatic
    fun keyboardGetKeycodeFromPhysical(keycode: Long): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(keyboardGetKeycodeFromPhysicalBind, singleton, keycode)
    }

    @JvmStatic
    fun keyboardGetLabelFromPhysical(keycode: Long): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(keyboardGetLabelFromPhysicalBind, singleton, keycode)
    }

    @JvmStatic
    fun showEmojiAndSymbolPicker() {
        ObjectCalls.ptrcallNoArgs(showEmojiAndSymbolPickerBind, singleton)
    }

    @JvmStatic
    fun colorPicker(callback: GodotCallable): Boolean {
        return ObjectCalls.ptrcallWithCallableArgRetBool(colorPickerBind, singleton, callback.target.handle, callback.method)
    }

    @JvmStatic
    fun processEvents() {
        ObjectCalls.ptrcallNoArgs(processEventsBind, singleton)
    }

    @JvmStatic
    fun forceProcessAndDropEvents() {
        ObjectCalls.ptrcallNoArgs(forceProcessAndDropEventsBind, singleton)
    }

    @JvmStatic
    fun setNativeIcon(filename: String) {
        ObjectCalls.ptrcallWithStringArg(setNativeIconBind, singleton, filename)
    }

    @JvmStatic
    fun setIcon(image: Image?) {
        ObjectCalls.ptrcallWithObjectArgs(setIconBind, singleton, listOf(image?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    @JvmStatic
    fun createStatusIndicator(icon: Texture2D?, tooltip: String, callback: GodotCallable): Int {
        return ObjectCalls.ptrcallWithObjectStringCallableArgsRetInt(createStatusIndicatorBind, singleton, icon?.requireOpenHandle() ?: MemorySegment.NULL, tooltip, callback.target.handle, callback.method)
    }

    @JvmStatic
    fun statusIndicatorSetIcon(id: Int, icon: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(statusIndicatorSetIconBind, singleton, id, icon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    @JvmStatic
    fun statusIndicatorSetTooltip(id: Int, tooltip: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(statusIndicatorSetTooltipBind, singleton, id, tooltip)
    }

    @JvmStatic
    fun statusIndicatorSetMenu(id: Int, menuRid: RID) {
        ObjectCalls.ptrcallWithIntAndRIDArg(statusIndicatorSetMenuBind, singleton, id, menuRid)
    }

    @JvmStatic
    fun statusIndicatorSetCallback(id: Int, callback: GodotCallable) {
        ObjectCalls.ptrcallWithIntCallableArgs(statusIndicatorSetCallbackBind, singleton, id, callback.target.handle, callback.method)
    }

    @JvmStatic
    fun statusIndicatorGetRect(id: Int): Rect2 {
        return ObjectCalls.ptrcallWithIntArgRetRect2(statusIndicatorGetRectBind, singleton, id)
    }

    @JvmStatic
    fun deleteStatusIndicator(id: Int) {
        ObjectCalls.ptrcallWithIntArg(deleteStatusIndicatorBind, singleton, id)
    }

    @JvmStatic
    fun tabletGetDriverCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(tabletGetDriverCountBind, singleton)
    }

    @JvmStatic
    fun tabletGetDriverName(idx: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(tabletGetDriverNameBind, singleton, idx)
    }

    @JvmStatic
    fun tabletGetCurrentDriver(): String {
        return ObjectCalls.ptrcallNoArgsRetString(tabletGetCurrentDriverBind, singleton)
    }

    @JvmStatic
    fun tabletSetCurrentDriver(name: String) {
        ObjectCalls.ptrcallWithStringArg(tabletSetCurrentDriverBind, singleton, name)
    }

    @JvmStatic
    fun isWindowTransparencyAvailable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isWindowTransparencyAvailableBind, singleton)
    }

    @JvmStatic
    fun registerAdditionalOutput(objectValue: GodotObject) {
        ObjectCalls.ptrcallWithObjectArgs(registerAdditionalOutputBind, singleton, listOf(objectValue.handle))
    }

    @JvmStatic
    fun unregisterAdditionalOutput(objectValue: GodotObject) {
        ObjectCalls.ptrcallWithObjectArgs(unregisterAdditionalOutputBind, singleton, listOf(objectValue.handle))
    }

    @JvmStatic
    fun hasAdditionalOutputs(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasAdditionalOutputsBind, singleton)
    }

    @JvmStatic
    fun isInPipMode(windowId: Int = 0): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isInPipModeBind, singleton, windowId)
    }

    @JvmStatic
    fun pipModeEnter(windowId: Int = 0) {
        ObjectCalls.ptrcallWithIntArg(pipModeEnterBind, singleton, windowId)
    }

    @JvmStatic
    fun pipModeSetAspectRatio(numerator: Int, denominator: Int, windowId: Int = 0) {
        ObjectCalls.ptrcallWithThreeIntArgs(pipModeSetAspectRatioBind, singleton, numerator, denominator, windowId)
    }

    @JvmStatic
    fun pipModeSetAutoEnterOnBackground(autoEnterOnBackground: Boolean, windowId: Int = 0) {
        ObjectCalls.ptrcallWithBoolAndIntArgs(pipModeSetAutoEnterOnBackgroundBind, singleton, autoEnterOnBackground, windowId)
    }

    object Signals {
        const val orientationChanged: String = "orientation_changed"
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): DisplayServer? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): DisplayServer? =
        if (handle.address() == 0L) null else this

    private const val HAS_FEATURE_HASH = 334065950L
    private val hasFeatureBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "has_feature", HAS_FEATURE_HASH)
    }

    private const val GET_NAME_HASH = 201670096L
    private val getNameBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_name", GET_NAME_HASH)
    }

    private const val HELP_SET_SEARCH_CALLBACKS_HASH = 1687350599L
    private val helpSetSearchCallbacksBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "help_set_search_callbacks", HELP_SET_SEARCH_CALLBACKS_HASH)
    }

    private const val GLOBAL_MENU_SET_POPUP_CALLBACKS_HASH = 3893727526L
    private val globalMenuSetPopupCallbacksBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_popup_callbacks", GLOBAL_MENU_SET_POPUP_CALLBACKS_HASH)
    }

    private const val GLOBAL_MENU_ADD_SUBMENU_ITEM_HASH = 2828985934L
    private val globalMenuAddSubmenuItemBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_submenu_item", GLOBAL_MENU_ADD_SUBMENU_ITEM_HASH)
    }

    private const val GLOBAL_MENU_ADD_ITEM_HASH = 3616842746L
    private val globalMenuAddItemBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_item", GLOBAL_MENU_ADD_ITEM_HASH)
    }

    private const val GLOBAL_MENU_ADD_CHECK_ITEM_HASH = 3616842746L
    private val globalMenuAddCheckItemBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_check_item", GLOBAL_MENU_ADD_CHECK_ITEM_HASH)
    }

    private const val GLOBAL_MENU_ADD_ICON_ITEM_HASH = 3867083847L
    private val globalMenuAddIconItemBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_icon_item", GLOBAL_MENU_ADD_ICON_ITEM_HASH)
    }

    private const val GLOBAL_MENU_ADD_ICON_CHECK_ITEM_HASH = 3867083847L
    private val globalMenuAddIconCheckItemBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_icon_check_item", GLOBAL_MENU_ADD_ICON_CHECK_ITEM_HASH)
    }

    private const val GLOBAL_MENU_ADD_RADIO_CHECK_ITEM_HASH = 3616842746L
    private val globalMenuAddRadioCheckItemBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_radio_check_item", GLOBAL_MENU_ADD_RADIO_CHECK_ITEM_HASH)
    }

    private const val GLOBAL_MENU_ADD_ICON_RADIO_CHECK_ITEM_HASH = 3867083847L
    private val globalMenuAddIconRadioCheckItemBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_icon_radio_check_item", GLOBAL_MENU_ADD_ICON_RADIO_CHECK_ITEM_HASH)
    }

    private const val GLOBAL_MENU_ADD_MULTISTATE_ITEM_HASH = 3297554655L
    private val globalMenuAddMultistateItemBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_multistate_item", GLOBAL_MENU_ADD_MULTISTATE_ITEM_HASH)
    }

    private const val GLOBAL_MENU_ADD_SEPARATOR_HASH = 3214812433L
    private val globalMenuAddSeparatorBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_add_separator", GLOBAL_MENU_ADD_SEPARATOR_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_INDEX_FROM_TEXT_HASH = 2878152881L
    private val globalMenuGetItemIndexFromTextBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_index_from_text", GLOBAL_MENU_GET_ITEM_INDEX_FROM_TEXT_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_INDEX_FROM_TAG_HASH = 2941063483L
    private val globalMenuGetItemIndexFromTagBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_index_from_tag", GLOBAL_MENU_GET_ITEM_INDEX_FROM_TAG_HASH)
    }

    private const val GLOBAL_MENU_IS_ITEM_CHECKED_HASH = 3511468594L
    private val globalMenuIsItemCheckedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_is_item_checked", GLOBAL_MENU_IS_ITEM_CHECKED_HASH)
    }

    private const val GLOBAL_MENU_IS_ITEM_CHECKABLE_HASH = 3511468594L
    private val globalMenuIsItemCheckableBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_is_item_checkable", GLOBAL_MENU_IS_ITEM_CHECKABLE_HASH)
    }

    private const val GLOBAL_MENU_IS_ITEM_RADIO_CHECKABLE_HASH = 3511468594L
    private val globalMenuIsItemRadioCheckableBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_is_item_radio_checkable", GLOBAL_MENU_IS_ITEM_RADIO_CHECKABLE_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_CALLBACK_HASH = 748666903L
    private val globalMenuGetItemCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_callback", GLOBAL_MENU_GET_ITEM_CALLBACK_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_KEY_CALLBACK_HASH = 748666903L
    private val globalMenuGetItemKeyCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_key_callback", GLOBAL_MENU_GET_ITEM_KEY_CALLBACK_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_TAG_HASH = 330672633L
    private val globalMenuGetItemTagBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_tag", GLOBAL_MENU_GET_ITEM_TAG_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_TEXT_HASH = 591067909L
    private val globalMenuGetItemTextBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_text", GLOBAL_MENU_GET_ITEM_TEXT_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_SUBMENU_HASH = 591067909L
    private val globalMenuGetItemSubmenuBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_submenu", GLOBAL_MENU_GET_ITEM_SUBMENU_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_ACCELERATOR_HASH = 936065394L
    private val globalMenuGetItemAcceleratorBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_accelerator", GLOBAL_MENU_GET_ITEM_ACCELERATOR_HASH)
    }

    private const val GLOBAL_MENU_IS_ITEM_DISABLED_HASH = 3511468594L
    private val globalMenuIsItemDisabledBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_is_item_disabled", GLOBAL_MENU_IS_ITEM_DISABLED_HASH)
    }

    private const val GLOBAL_MENU_IS_ITEM_HIDDEN_HASH = 3511468594L
    private val globalMenuIsItemHiddenBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_is_item_hidden", GLOBAL_MENU_IS_ITEM_HIDDEN_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_TOOLTIP_HASH = 591067909L
    private val globalMenuGetItemTooltipBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_tooltip", GLOBAL_MENU_GET_ITEM_TOOLTIP_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_STATE_HASH = 3422818498L
    private val globalMenuGetItemStateBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_state", GLOBAL_MENU_GET_ITEM_STATE_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_MAX_STATES_HASH = 3422818498L
    private val globalMenuGetItemMaxStatesBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_max_states", GLOBAL_MENU_GET_ITEM_MAX_STATES_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_ICON_HASH = 3591713183L
    private val globalMenuGetItemIconBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_icon", GLOBAL_MENU_GET_ITEM_ICON_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_INDENTATION_LEVEL_HASH = 3422818498L
    private val globalMenuGetItemIndentationLevelBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_indentation_level", GLOBAL_MENU_GET_ITEM_INDENTATION_LEVEL_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_CHECKED_HASH = 4108344793L
    private val globalMenuSetItemCheckedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_checked", GLOBAL_MENU_SET_ITEM_CHECKED_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_CHECKABLE_HASH = 4108344793L
    private val globalMenuSetItemCheckableBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_checkable", GLOBAL_MENU_SET_ITEM_CHECKABLE_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_RADIO_CHECKABLE_HASH = 4108344793L
    private val globalMenuSetItemRadioCheckableBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_radio_checkable", GLOBAL_MENU_SET_ITEM_RADIO_CHECKABLE_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_CALLBACK_HASH = 3809915389L
    private val globalMenuSetItemCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_callback", GLOBAL_MENU_SET_ITEM_CALLBACK_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_HOVER_CALLBACKS_HASH = 3809915389L
    private val globalMenuSetItemHoverCallbacksBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_hover_callbacks", GLOBAL_MENU_SET_ITEM_HOVER_CALLBACKS_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_KEY_CALLBACK_HASH = 3809915389L
    private val globalMenuSetItemKeyCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_key_callback", GLOBAL_MENU_SET_ITEM_KEY_CALLBACK_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_TAG_HASH = 453659863L
    private val globalMenuSetItemTagBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_tag", GLOBAL_MENU_SET_ITEM_TAG_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_TEXT_HASH = 965966136L
    private val globalMenuSetItemTextBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_text", GLOBAL_MENU_SET_ITEM_TEXT_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_SUBMENU_HASH = 965966136L
    private val globalMenuSetItemSubmenuBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_submenu", GLOBAL_MENU_SET_ITEM_SUBMENU_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_ACCELERATOR_HASH = 566943293L
    private val globalMenuSetItemAcceleratorBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_accelerator", GLOBAL_MENU_SET_ITEM_ACCELERATOR_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_DISABLED_HASH = 4108344793L
    private val globalMenuSetItemDisabledBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_disabled", GLOBAL_MENU_SET_ITEM_DISABLED_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_HIDDEN_HASH = 4108344793L
    private val globalMenuSetItemHiddenBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_hidden", GLOBAL_MENU_SET_ITEM_HIDDEN_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_TOOLTIP_HASH = 965966136L
    private val globalMenuSetItemTooltipBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_tooltip", GLOBAL_MENU_SET_ITEM_TOOLTIP_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_STATE_HASH = 3474840532L
    private val globalMenuSetItemStateBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_state", GLOBAL_MENU_SET_ITEM_STATE_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_MAX_STATES_HASH = 3474840532L
    private val globalMenuSetItemMaxStatesBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_max_states", GLOBAL_MENU_SET_ITEM_MAX_STATES_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_ICON_HASH = 3201338066L
    private val globalMenuSetItemIconBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_icon", GLOBAL_MENU_SET_ITEM_ICON_HASH)
    }

    private const val GLOBAL_MENU_SET_ITEM_INDENTATION_LEVEL_HASH = 3474840532L
    private val globalMenuSetItemIndentationLevelBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_set_item_indentation_level", GLOBAL_MENU_SET_ITEM_INDENTATION_LEVEL_HASH)
    }

    private const val GLOBAL_MENU_GET_ITEM_COUNT_HASH = 1321353865L
    private val globalMenuGetItemCountBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_count", GLOBAL_MENU_GET_ITEM_COUNT_HASH)
    }

    private const val GLOBAL_MENU_REMOVE_ITEM_HASH = 2956805083L
    private val globalMenuRemoveItemBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_remove_item", GLOBAL_MENU_REMOVE_ITEM_HASH)
    }

    private const val GLOBAL_MENU_CLEAR_HASH = 83702148L
    private val globalMenuClearBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_clear", GLOBAL_MENU_CLEAR_HASH)
    }

    private const val GLOBAL_MENU_GET_SYSTEM_MENU_ROOTS_HASH = 3102165223L
    private val globalMenuGetSystemMenuRootsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_system_menu_roots", GLOBAL_MENU_GET_SYSTEM_MENU_ROOTS_HASH)
    }

    private const val TTS_IS_SPEAKING_HASH = 36873697L
    private val ttsIsSpeakingBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tts_is_speaking", TTS_IS_SPEAKING_HASH)
    }

    private const val TTS_IS_PAUSED_HASH = 36873697L
    private val ttsIsPausedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tts_is_paused", TTS_IS_PAUSED_HASH)
    }

    private const val TTS_GET_VOICES_HASH = 3995934104L
    private val ttsGetVoicesBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tts_get_voices", TTS_GET_VOICES_HASH)
    }

    private const val TTS_GET_VOICES_FOR_LANGUAGE_HASH = 4291131558L
    private val ttsGetVoicesForLanguageBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tts_get_voices_for_language", TTS_GET_VOICES_FOR_LANGUAGE_HASH)
    }

    private const val TTS_SPEAK_HASH = 903992738L
    private val ttsSpeakBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tts_speak", TTS_SPEAK_HASH)
    }

    private const val TTS_PAUSE_HASH = 3218959716L
    private val ttsPauseBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tts_pause", TTS_PAUSE_HASH)
    }

    private const val TTS_RESUME_HASH = 3218959716L
    private val ttsResumeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tts_resume", TTS_RESUME_HASH)
    }

    private const val TTS_STOP_HASH = 3218959716L
    private val ttsStopBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tts_stop", TTS_STOP_HASH)
    }

    private const val TTS_SET_UTTERANCE_CALLBACK_HASH = 109679083L
    private val ttsSetUtteranceCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tts_set_utterance_callback", TTS_SET_UTTERANCE_CALLBACK_HASH)
    }

    private const val IS_DARK_MODE_SUPPORTED_HASH = 36873697L
    private val isDarkModeSupportedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "is_dark_mode_supported", IS_DARK_MODE_SUPPORTED_HASH)
    }

    private const val IS_DARK_MODE_HASH = 36873697L
    private val isDarkModeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "is_dark_mode", IS_DARK_MODE_HASH)
    }

    private const val GET_ACCENT_COLOR_HASH = 3444240500L
    private val getAccentColorBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_accent_color", GET_ACCENT_COLOR_HASH)
    }

    private const val GET_BASE_COLOR_HASH = 3444240500L
    private val getBaseColorBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_base_color", GET_BASE_COLOR_HASH)
    }

    private const val SET_SYSTEM_THEME_CHANGE_CALLBACK_HASH = 1611583062L
    private val setSystemThemeChangeCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "set_system_theme_change_callback", SET_SYSTEM_THEME_CHANGE_CALLBACK_HASH)
    }

    private const val MOUSE_SET_MODE_HASH = 348288463L
    private val mouseSetModeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "mouse_set_mode", MOUSE_SET_MODE_HASH)
    }

    private const val MOUSE_GET_MODE_HASH = 1353961651L
    private val mouseGetModeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "mouse_get_mode", MOUSE_GET_MODE_HASH)
    }

    private const val WARP_MOUSE_HASH = 1130785943L
    private val warpMouseBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "warp_mouse", WARP_MOUSE_HASH)
    }

    private const val MOUSE_GET_POSITION_HASH = 3690982128L
    private val mouseGetPositionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "mouse_get_position", MOUSE_GET_POSITION_HASH)
    }

    private const val MOUSE_GET_BUTTON_STATE_HASH = 2512161324L
    private val mouseGetButtonStateBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "mouse_get_button_state", MOUSE_GET_BUTTON_STATE_HASH)
    }

    private const val CLIPBOARD_SET_HASH = 83702148L
    private val clipboardSetBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "clipboard_set", CLIPBOARD_SET_HASH)
    }

    private const val CLIPBOARD_GET_HASH = 201670096L
    private val clipboardGetBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "clipboard_get", CLIPBOARD_GET_HASH)
    }

    private const val CLIPBOARD_GET_IMAGE_HASH = 4190603485L
    private val clipboardGetImageBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "clipboard_get_image", CLIPBOARD_GET_IMAGE_HASH)
    }

    private const val CLIPBOARD_HAS_HASH = 36873697L
    private val clipboardHasBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "clipboard_has", CLIPBOARD_HAS_HASH)
    }

    private const val CLIPBOARD_HAS_IMAGE_HASH = 36873697L
    private val clipboardHasImageBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "clipboard_has_image", CLIPBOARD_HAS_IMAGE_HASH)
    }

    private const val CLIPBOARD_SET_PRIMARY_HASH = 83702148L
    private val clipboardSetPrimaryBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "clipboard_set_primary", CLIPBOARD_SET_PRIMARY_HASH)
    }

    private const val CLIPBOARD_GET_PRIMARY_HASH = 201670096L
    private val clipboardGetPrimaryBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "clipboard_get_primary", CLIPBOARD_GET_PRIMARY_HASH)
    }

    private const val GET_DISPLAY_CUTOUTS_HASH = 3995934104L
    private val getDisplayCutoutsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_display_cutouts", GET_DISPLAY_CUTOUTS_HASH)
    }

    private const val GET_DISPLAY_SAFE_AREA_HASH = 410525958L
    private val getDisplaySafeAreaBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_display_safe_area", GET_DISPLAY_SAFE_AREA_HASH)
    }

    private const val GET_SCREEN_COUNT_HASH = 3905245786L
    private val getScreenCountBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_screen_count", GET_SCREEN_COUNT_HASH)
    }

    private const val GET_PRIMARY_SCREEN_HASH = 3905245786L
    private val getPrimaryScreenBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_primary_screen", GET_PRIMARY_SCREEN_HASH)
    }

    private const val GET_KEYBOARD_FOCUS_SCREEN_HASH = 3905245786L
    private val getKeyboardFocusScreenBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_keyboard_focus_screen", GET_KEYBOARD_FOCUS_SCREEN_HASH)
    }

    private const val GET_SCREEN_FROM_RECT_HASH = 741354659L
    private val getScreenFromRectBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_screen_from_rect", GET_SCREEN_FROM_RECT_HASH)
    }

    private const val SCREEN_GET_POSITION_HASH = 1725937825L
    private val screenGetPositionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_position", SCREEN_GET_POSITION_HASH)
    }

    private const val SCREEN_GET_SIZE_HASH = 1725937825L
    private val screenGetSizeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_size", SCREEN_GET_SIZE_HASH)
    }

    private const val SCREEN_GET_USABLE_RECT_HASH = 2439012528L
    private val screenGetUsableRectBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_usable_rect", SCREEN_GET_USABLE_RECT_HASH)
    }

    private const val SCREEN_GET_DPI_HASH = 181039630L
    private val screenGetDpiBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_dpi", SCREEN_GET_DPI_HASH)
    }

    private const val SCREEN_GET_SCALE_HASH = 909105437L
    private val screenGetScaleBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_scale", SCREEN_GET_SCALE_HASH)
    }

    private const val IS_TOUCHSCREEN_AVAILABLE_HASH = 36873697L
    private val isTouchscreenAvailableBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "is_touchscreen_available", IS_TOUCHSCREEN_AVAILABLE_HASH)
    }

    private const val SCREEN_GET_MAX_SCALE_HASH = 1740695150L
    private val screenGetMaxScaleBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_max_scale", SCREEN_GET_MAX_SCALE_HASH)
    }

    private const val SCREEN_GET_REFRESH_RATE_HASH = 909105437L
    private val screenGetRefreshRateBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_refresh_rate", SCREEN_GET_REFRESH_RATE_HASH)
    }

    private const val SCREEN_GET_PIXEL_HASH = 1532707496L
    private val screenGetPixelBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_pixel", SCREEN_GET_PIXEL_HASH)
    }

    private const val SCREEN_GET_IMAGE_HASH = 3813388802L
    private val screenGetImageBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_image", SCREEN_GET_IMAGE_HASH)
    }

    private const val SCREEN_GET_IMAGE_RECT_HASH = 2601441065L
    private val screenGetImageRectBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_image_rect", SCREEN_GET_IMAGE_RECT_HASH)
    }

    private const val SCREEN_SET_ORIENTATION_HASH = 2211511631L
    private val screenSetOrientationBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_set_orientation", SCREEN_SET_ORIENTATION_HASH)
    }

    private const val SCREEN_GET_ORIENTATION_HASH = 133818562L
    private val screenGetOrientationBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_get_orientation", SCREEN_GET_ORIENTATION_HASH)
    }

    private const val SCREEN_SET_KEEP_ON_HASH = 2586408642L
    private val screenSetKeepOnBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_set_keep_on", SCREEN_SET_KEEP_ON_HASH)
    }

    private const val SCREEN_IS_KEPT_ON_HASH = 36873697L
    private val screenIsKeptOnBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "screen_is_kept_on", SCREEN_IS_KEPT_ON_HASH)
    }

    private const val GET_WINDOW_LIST_HASH = 1930428628L
    private val getWindowListBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_window_list", GET_WINDOW_LIST_HASH)
    }

    private const val GET_WINDOW_AT_SCREEN_POSITION_HASH = 2485466453L
    private val getWindowAtScreenPositionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_window_at_screen_position", GET_WINDOW_AT_SCREEN_POSITION_HASH)
    }

    private const val WINDOW_GET_NATIVE_HANDLE_HASH = 1096425680L
    private val windowGetNativeHandleBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_native_handle", WINDOW_GET_NATIVE_HANDLE_HASH)
    }

    private const val WINDOW_GET_ACTIVE_POPUP_HASH = 3905245786L
    private val windowGetActivePopupBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_active_popup", WINDOW_GET_ACTIVE_POPUP_HASH)
    }

    private const val WINDOW_SET_POPUP_SAFE_RECT_HASH = 3317281434L
    private val windowSetPopupSafeRectBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_popup_safe_rect", WINDOW_SET_POPUP_SAFE_RECT_HASH)
    }

    private const val WINDOW_GET_POPUP_SAFE_RECT_HASH = 2161169500L
    private val windowGetPopupSafeRectBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_popup_safe_rect", WINDOW_GET_POPUP_SAFE_RECT_HASH)
    }

    private const val WINDOW_SET_TITLE_HASH = 441246282L
    private val windowSetTitleBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_title", WINDOW_SET_TITLE_HASH)
    }

    private const val WINDOW_GET_TITLE_SIZE_HASH = 2925301799L
    private val windowGetTitleSizeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_title_size", WINDOW_GET_TITLE_SIZE_HASH)
    }

    private const val WINDOW_SET_MOUSE_PASSTHROUGH_HASH = 1993637420L
    private val windowSetMousePassthroughBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_mouse_passthrough", WINDOW_SET_MOUSE_PASSTHROUGH_HASH)
    }

    private const val WINDOW_GET_CURRENT_SCREEN_HASH = 1591665591L
    private val windowGetCurrentScreenBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_current_screen", WINDOW_GET_CURRENT_SCREEN_HASH)
    }

    private const val WINDOW_SET_CURRENT_SCREEN_HASH = 2230941749L
    private val windowSetCurrentScreenBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_current_screen", WINDOW_SET_CURRENT_SCREEN_HASH)
    }

    private const val WINDOW_GET_POSITION_HASH = 763922886L
    private val windowGetPositionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_position", WINDOW_GET_POSITION_HASH)
    }

    private const val WINDOW_GET_POSITION_WITH_DECORATIONS_HASH = 763922886L
    private val windowGetPositionWithDecorationsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_position_with_decorations", WINDOW_GET_POSITION_WITH_DECORATIONS_HASH)
    }

    private const val WINDOW_SET_POSITION_HASH = 2019273902L
    private val windowSetPositionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_position", WINDOW_SET_POSITION_HASH)
    }

    private const val WINDOW_GET_SIZE_HASH = 763922886L
    private val windowGetSizeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_size", WINDOW_GET_SIZE_HASH)
    }

    private const val WINDOW_SET_SIZE_HASH = 2019273902L
    private val windowSetSizeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_size", WINDOW_SET_SIZE_HASH)
    }

    private const val WINDOW_SET_RECT_CHANGED_CALLBACK_HASH = 1091192925L
    private val windowSetRectChangedCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_rect_changed_callback", WINDOW_SET_RECT_CHANGED_CALLBACK_HASH)
    }

    private const val WINDOW_SET_WINDOW_EVENT_CALLBACK_HASH = 1091192925L
    private val windowSetWindowEventCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_window_event_callback", WINDOW_SET_WINDOW_EVENT_CALLBACK_HASH)
    }

    private const val WINDOW_SET_INPUT_EVENT_CALLBACK_HASH = 1091192925L
    private val windowSetInputEventCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_input_event_callback", WINDOW_SET_INPUT_EVENT_CALLBACK_HASH)
    }

    private const val WINDOW_SET_INPUT_TEXT_CALLBACK_HASH = 1091192925L
    private val windowSetInputTextCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_input_text_callback", WINDOW_SET_INPUT_TEXT_CALLBACK_HASH)
    }

    private const val WINDOW_SET_DROP_FILES_CALLBACK_HASH = 1091192925L
    private val windowSetDropFilesCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_drop_files_callback", WINDOW_SET_DROP_FILES_CALLBACK_HASH)
    }

    private const val WINDOW_GET_ATTACHED_INSTANCE_ID_HASH = 1591665591L
    private val windowGetAttachedInstanceIdBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_attached_instance_id", WINDOW_GET_ATTACHED_INSTANCE_ID_HASH)
    }

    private const val WINDOW_GET_MAX_SIZE_HASH = 763922886L
    private val windowGetMaxSizeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_max_size", WINDOW_GET_MAX_SIZE_HASH)
    }

    private const val WINDOW_SET_MAX_SIZE_HASH = 2019273902L
    private val windowSetMaxSizeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_max_size", WINDOW_SET_MAX_SIZE_HASH)
    }

    private const val WINDOW_GET_MIN_SIZE_HASH = 763922886L
    private val windowGetMinSizeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_min_size", WINDOW_GET_MIN_SIZE_HASH)
    }

    private const val WINDOW_SET_MIN_SIZE_HASH = 2019273902L
    private val windowSetMinSizeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_min_size", WINDOW_SET_MIN_SIZE_HASH)
    }

    private const val WINDOW_GET_SIZE_WITH_DECORATIONS_HASH = 763922886L
    private val windowGetSizeWithDecorationsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_size_with_decorations", WINDOW_GET_SIZE_WITH_DECORATIONS_HASH)
    }

    private const val WINDOW_GET_MODE_HASH = 2185728461L
    private val windowGetModeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_mode", WINDOW_GET_MODE_HASH)
    }

    private const val WINDOW_SET_MODE_HASH = 1319965401L
    private val windowSetModeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_mode", WINDOW_SET_MODE_HASH)
    }

    private const val WINDOW_SET_FLAG_HASH = 254894155L
    private val windowSetFlagBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_flag", WINDOW_SET_FLAG_HASH)
    }

    private const val WINDOW_GET_FLAG_HASH = 802816991L
    private val windowGetFlagBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_flag", WINDOW_GET_FLAG_HASH)
    }

    private const val WINDOW_SET_ICON_HASH = 2457502155L
    private val windowSetIconBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_icon", WINDOW_SET_ICON_HASH)
    }

    private const val WINDOW_SET_WINDOW_BUTTONS_OFFSET_HASH = 2019273902L
    private val windowSetWindowButtonsOffsetBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_window_buttons_offset", WINDOW_SET_WINDOW_BUTTONS_OFFSET_HASH)
    }

    private const val WINDOW_GET_SAFE_TITLE_MARGINS_HASH = 2295066620L
    private val windowGetSafeTitleMarginsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_safe_title_margins", WINDOW_GET_SAFE_TITLE_MARGINS_HASH)
    }

    private const val WINDOW_REQUEST_ATTENTION_HASH = 1995695955L
    private val windowRequestAttentionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_request_attention", WINDOW_REQUEST_ATTENTION_HASH)
    }

    private const val WINDOW_SET_TASKBAR_PROGRESS_VALUE_HASH = 3506631519L
    private val windowSetTaskbarProgressValueBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_taskbar_progress_value", WINDOW_SET_TASKBAR_PROGRESS_VALUE_HASH)
    }

    private const val WINDOW_SET_TASKBAR_PROGRESS_STATE_HASH = 4119882768L
    private val windowSetTaskbarProgressStateBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_taskbar_progress_state", WINDOW_SET_TASKBAR_PROGRESS_STATE_HASH)
    }

    private const val WINDOW_MOVE_TO_FOREGROUND_HASH = 1995695955L
    private val windowMoveToForegroundBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_move_to_foreground", WINDOW_MOVE_TO_FOREGROUND_HASH)
    }

    private const val WINDOW_IS_FOCUSED_HASH = 1051549951L
    private val windowIsFocusedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_is_focused", WINDOW_IS_FOCUSED_HASH)
    }

    private const val WINDOW_CAN_DRAW_HASH = 1051549951L
    private val windowCanDrawBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_can_draw", WINDOW_CAN_DRAW_HASH)
    }

    private const val WINDOW_SET_TRANSIENT_HASH = 3937882851L
    private val windowSetTransientBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_transient", WINDOW_SET_TRANSIENT_HASH)
    }

    private const val WINDOW_SET_EXCLUSIVE_HASH = 300928843L
    private val windowSetExclusiveBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_exclusive", WINDOW_SET_EXCLUSIVE_HASH)
    }

    private const val WINDOW_SET_IME_ACTIVE_HASH = 1661950165L
    private val windowSetImeActiveBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_ime_active", WINDOW_SET_IME_ACTIVE_HASH)
    }

    private const val WINDOW_SET_IME_POSITION_HASH = 2019273902L
    private val windowSetImePositionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_ime_position", WINDOW_SET_IME_POSITION_HASH)
    }

    private const val WINDOW_SET_VSYNC_MODE_HASH = 2179333492L
    private val windowSetVsyncModeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_vsync_mode", WINDOW_SET_VSYNC_MODE_HASH)
    }

    private const val WINDOW_GET_VSYNC_MODE_HASH = 578873795L
    private val windowGetVsyncModeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_vsync_mode", WINDOW_GET_VSYNC_MODE_HASH)
    }

    private const val WINDOW_IS_HDR_OUTPUT_SUPPORTED_HASH = 1051549951L
    private val windowIsHdrOutputSupportedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_is_hdr_output_supported", WINDOW_IS_HDR_OUTPUT_SUPPORTED_HASH)
    }

    private const val WINDOW_REQUEST_HDR_OUTPUT_HASH = 1661950165L
    private val windowRequestHdrOutputBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_request_hdr_output", WINDOW_REQUEST_HDR_OUTPUT_HASH)
    }

    private const val WINDOW_IS_HDR_OUTPUT_REQUESTED_HASH = 1051549951L
    private val windowIsHdrOutputRequestedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_is_hdr_output_requested", WINDOW_IS_HDR_OUTPUT_REQUESTED_HASH)
    }

    private const val WINDOW_IS_HDR_OUTPUT_ENABLED_HASH = 1051549951L
    private val windowIsHdrOutputEnabledBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_is_hdr_output_enabled", WINDOW_IS_HDR_OUTPUT_ENABLED_HASH)
    }

    private const val WINDOW_SET_HDR_OUTPUT_REFERENCE_LUMINANCE_HASH = 3506631519L
    private val windowSetHdrOutputReferenceLuminanceBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_hdr_output_reference_luminance", WINDOW_SET_HDR_OUTPUT_REFERENCE_LUMINANCE_HASH)
    }

    private const val WINDOW_GET_HDR_OUTPUT_REFERENCE_LUMINANCE_HASH = 218038398L
    private val windowGetHdrOutputReferenceLuminanceBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_hdr_output_reference_luminance", WINDOW_GET_HDR_OUTPUT_REFERENCE_LUMINANCE_HASH)
    }

    private const val WINDOW_GET_HDR_OUTPUT_CURRENT_REFERENCE_LUMINANCE_HASH = 218038398L
    private val windowGetHdrOutputCurrentReferenceLuminanceBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_hdr_output_current_reference_luminance", WINDOW_GET_HDR_OUTPUT_CURRENT_REFERENCE_LUMINANCE_HASH)
    }

    private const val WINDOW_SET_HDR_OUTPUT_MAX_LUMINANCE_HASH = 3506631519L
    private val windowSetHdrOutputMaxLuminanceBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_hdr_output_max_luminance", WINDOW_SET_HDR_OUTPUT_MAX_LUMINANCE_HASH)
    }

    private const val WINDOW_GET_HDR_OUTPUT_MAX_LUMINANCE_HASH = 218038398L
    private val windowGetHdrOutputMaxLuminanceBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_hdr_output_max_luminance", WINDOW_GET_HDR_OUTPUT_MAX_LUMINANCE_HASH)
    }

    private const val WINDOW_GET_HDR_OUTPUT_CURRENT_MAX_LUMINANCE_HASH = 218038398L
    private val windowGetHdrOutputCurrentMaxLuminanceBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_hdr_output_current_max_luminance", WINDOW_GET_HDR_OUTPUT_CURRENT_MAX_LUMINANCE_HASH)
    }

    private const val WINDOW_GET_OUTPUT_MAX_LINEAR_VALUE_HASH = 218038398L
    private val windowGetOutputMaxLinearValueBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_get_output_max_linear_value", WINDOW_GET_OUTPUT_MAX_LINEAR_VALUE_HASH)
    }

    private const val WINDOW_IS_MAXIMIZE_ALLOWED_HASH = 1051549951L
    private val windowIsMaximizeAllowedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_is_maximize_allowed", WINDOW_IS_MAXIMIZE_ALLOWED_HASH)
    }

    private const val WINDOW_MAXIMIZE_ON_TITLE_DBL_CLICK_HASH = 36873697L
    private val windowMaximizeOnTitleDblClickBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_maximize_on_title_dbl_click", WINDOW_MAXIMIZE_ON_TITLE_DBL_CLICK_HASH)
    }

    private const val WINDOW_MINIMIZE_ON_TITLE_DBL_CLICK_HASH = 36873697L
    private val windowMinimizeOnTitleDblClickBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_minimize_on_title_dbl_click", WINDOW_MINIMIZE_ON_TITLE_DBL_CLICK_HASH)
    }

    private const val WINDOW_START_DRAG_HASH = 1995695955L
    private val windowStartDragBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_start_drag", WINDOW_START_DRAG_HASH)
    }

    private const val WINDOW_START_RESIZE_HASH = 4009722312L
    private val windowStartResizeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_start_resize", WINDOW_START_RESIZE_HASH)
    }

    private const val WINDOW_SET_COLOR_HASH = 2920490490L
    private val windowSetColorBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "window_set_color", WINDOW_SET_COLOR_HASH)
    }

    private const val ACCESSIBILITY_SHOULD_INCREASE_CONTRAST_HASH = 3905245786L
    private val accessibilityShouldIncreaseContrastBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_should_increase_contrast", ACCESSIBILITY_SHOULD_INCREASE_CONTRAST_HASH)
    }

    private const val ACCESSIBILITY_SHOULD_REDUCE_ANIMATION_HASH = 3905245786L
    private val accessibilityShouldReduceAnimationBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_should_reduce_animation", ACCESSIBILITY_SHOULD_REDUCE_ANIMATION_HASH)
    }

    private const val ACCESSIBILITY_SHOULD_REDUCE_TRANSPARENCY_HASH = 3905245786L
    private val accessibilityShouldReduceTransparencyBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_should_reduce_transparency", ACCESSIBILITY_SHOULD_REDUCE_TRANSPARENCY_HASH)
    }

    private const val ACCESSIBILITY_SCREEN_READER_ACTIVE_HASH = 3905245786L
    private val accessibilityScreenReaderActiveBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_screen_reader_active", ACCESSIBILITY_SCREEN_READER_ACTIVE_HASH)
    }

    private const val ACCESSIBILITY_CREATE_ELEMENT_HASH = 2968347744L
    private val accessibilityCreateElementBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_create_element", ACCESSIBILITY_CREATE_ELEMENT_HASH)
    }

    private const val ACCESSIBILITY_CREATE_SUB_ELEMENT_HASH = 1949948826L
    private val accessibilityCreateSubElementBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_create_sub_element", ACCESSIBILITY_CREATE_SUB_ELEMENT_HASH)
    }

    private const val ACCESSIBILITY_CREATE_SUB_TEXT_EDIT_ELEMENTS_HASH = 2702009895L
    private val accessibilityCreateSubTextEditElementsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_create_sub_text_edit_elements", ACCESSIBILITY_CREATE_SUB_TEXT_EDIT_ELEMENTS_HASH)
    }

    private const val ACCESSIBILITY_HAS_ELEMENT_HASH = 4155700596L
    private val accessibilityHasElementBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_has_element", ACCESSIBILITY_HAS_ELEMENT_HASH)
    }

    private const val ACCESSIBILITY_FREE_ELEMENT_HASH = 2722037293L
    private val accessibilityFreeElementBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_free_element", ACCESSIBILITY_FREE_ELEMENT_HASH)
    }

    private const val ACCESSIBILITY_ELEMENT_SET_META_HASH = 3175752987L
    private val accessibilityElementSetMetaBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_element_set_meta", ACCESSIBILITY_ELEMENT_SET_META_HASH)
    }

    private const val ACCESSIBILITY_ELEMENT_GET_META_HASH = 4171304767L
    private val accessibilityElementGetMetaBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_element_get_meta", ACCESSIBILITY_ELEMENT_GET_META_HASH)
    }

    private const val ACCESSIBILITY_SET_WINDOW_RECT_HASH = 2386961724L
    private val accessibilitySetWindowRectBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_set_window_rect", ACCESSIBILITY_SET_WINDOW_RECT_HASH)
    }

    private const val ACCESSIBILITY_SET_WINDOW_FOCUSED_HASH = 300928843L
    private val accessibilitySetWindowFocusedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_set_window_focused", ACCESSIBILITY_SET_WINDOW_FOCUSED_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_FOCUS_HASH = 2722037293L
    private val accessibilityUpdateSetFocusBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_focus", ACCESSIBILITY_UPDATE_SET_FOCUS_HASH)
    }

    private const val ACCESSIBILITY_GET_WINDOW_ROOT_HASH = 495598643L
    private val accessibilityGetWindowRootBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_get_window_root", ACCESSIBILITY_GET_WINDOW_ROOT_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_ROLE_HASH = 3352768215L
    private val accessibilityUpdateSetRoleBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_role", ACCESSIBILITY_UPDATE_SET_ROLE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_NAME_HASH = 2726140452L
    private val accessibilityUpdateSetNameBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_name", ACCESSIBILITY_UPDATE_SET_NAME_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_EXTRA_INFO_HASH = 2726140452L
    private val accessibilityUpdateSetExtraInfoBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_extra_info", ACCESSIBILITY_UPDATE_SET_EXTRA_INFO_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_DESCRIPTION_HASH = 2726140452L
    private val accessibilityUpdateSetDescriptionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_description", ACCESSIBILITY_UPDATE_SET_DESCRIPTION_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_VALUE_HASH = 2726140452L
    private val accessibilityUpdateSetValueBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_value", ACCESSIBILITY_UPDATE_SET_VALUE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TOOLTIP_HASH = 2726140452L
    private val accessibilityUpdateSetTooltipBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_tooltip", ACCESSIBILITY_UPDATE_SET_TOOLTIP_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_BOUNDS_HASH = 1378122625L
    private val accessibilityUpdateSetBoundsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_bounds", ACCESSIBILITY_UPDATE_SET_BOUNDS_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TRANSFORM_HASH = 1246044741L
    private val accessibilityUpdateSetTransformBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_transform", ACCESSIBILITY_UPDATE_SET_TRANSFORM_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_ADD_CHILD_HASH = 395945892L
    private val accessibilityUpdateAddChildBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_add_child", ACCESSIBILITY_UPDATE_ADD_CHILD_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_ADD_RELATED_CONTROLS_HASH = 395945892L
    private val accessibilityUpdateAddRelatedControlsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_add_related_controls", ACCESSIBILITY_UPDATE_ADD_RELATED_CONTROLS_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_ADD_RELATED_DETAILS_HASH = 395945892L
    private val accessibilityUpdateAddRelatedDetailsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_add_related_details", ACCESSIBILITY_UPDATE_ADD_RELATED_DETAILS_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_ADD_RELATED_DESCRIBED_BY_HASH = 395945892L
    private val accessibilityUpdateAddRelatedDescribedByBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_add_related_described_by", ACCESSIBILITY_UPDATE_ADD_RELATED_DESCRIBED_BY_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_ADD_RELATED_FLOW_TO_HASH = 395945892L
    private val accessibilityUpdateAddRelatedFlowToBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_add_related_flow_to", ACCESSIBILITY_UPDATE_ADD_RELATED_FLOW_TO_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_ADD_RELATED_LABELED_BY_HASH = 395945892L
    private val accessibilityUpdateAddRelatedLabeledByBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_add_related_labeled_by", ACCESSIBILITY_UPDATE_ADD_RELATED_LABELED_BY_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_ADD_RELATED_RADIO_GROUP_HASH = 395945892L
    private val accessibilityUpdateAddRelatedRadioGroupBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_add_related_radio_group", ACCESSIBILITY_UPDATE_ADD_RELATED_RADIO_GROUP_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_ACTIVE_DESCENDANT_HASH = 395945892L
    private val accessibilityUpdateSetActiveDescendantBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_active_descendant", ACCESSIBILITY_UPDATE_SET_ACTIVE_DESCENDANT_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_NEXT_ON_LINE_HASH = 395945892L
    private val accessibilityUpdateSetNextOnLineBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_next_on_line", ACCESSIBILITY_UPDATE_SET_NEXT_ON_LINE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_PREVIOUS_ON_LINE_HASH = 395945892L
    private val accessibilityUpdateSetPreviousOnLineBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_previous_on_line", ACCESSIBILITY_UPDATE_SET_PREVIOUS_ON_LINE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_MEMBER_OF_HASH = 395945892L
    private val accessibilityUpdateSetMemberOfBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_member_of", ACCESSIBILITY_UPDATE_SET_MEMBER_OF_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_IN_PAGE_LINK_TARGET_HASH = 395945892L
    private val accessibilityUpdateSetInPageLinkTargetBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_in_page_link_target", ACCESSIBILITY_UPDATE_SET_IN_PAGE_LINK_TARGET_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_ERROR_MESSAGE_HASH = 395945892L
    private val accessibilityUpdateSetErrorMessageBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_error_message", ACCESSIBILITY_UPDATE_SET_ERROR_MESSAGE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_LIVE_HASH = 2683302212L
    private val accessibilityUpdateSetLiveBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_live", ACCESSIBILITY_UPDATE_SET_LIVE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_ADD_ACTION_HASH = 2898696987L
    private val accessibilityUpdateAddActionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_add_action", ACCESSIBILITY_UPDATE_ADD_ACTION_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_ADD_CUSTOM_ACTION_HASH = 4153150897L
    private val accessibilityUpdateAddCustomActionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_add_custom_action", ACCESSIBILITY_UPDATE_ADD_CUSTOM_ACTION_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TABLE_ROW_COUNT_HASH = 3411492887L
    private val accessibilityUpdateSetTableRowCountBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_table_row_count", ACCESSIBILITY_UPDATE_SET_TABLE_ROW_COUNT_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TABLE_COLUMN_COUNT_HASH = 3411492887L
    private val accessibilityUpdateSetTableColumnCountBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_table_column_count", ACCESSIBILITY_UPDATE_SET_TABLE_COLUMN_COUNT_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TABLE_ROW_INDEX_HASH = 3411492887L
    private val accessibilityUpdateSetTableRowIndexBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_table_row_index", ACCESSIBILITY_UPDATE_SET_TABLE_ROW_INDEX_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TABLE_COLUMN_INDEX_HASH = 3411492887L
    private val accessibilityUpdateSetTableColumnIndexBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_table_column_index", ACCESSIBILITY_UPDATE_SET_TABLE_COLUMN_INDEX_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TABLE_CELL_POSITION_HASH = 4288446313L
    private val accessibilityUpdateSetTableCellPositionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_table_cell_position", ACCESSIBILITY_UPDATE_SET_TABLE_CELL_POSITION_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TABLE_CELL_SPAN_HASH = 4288446313L
    private val accessibilityUpdateSetTableCellSpanBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_table_cell_span", ACCESSIBILITY_UPDATE_SET_TABLE_CELL_SPAN_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_LIST_ITEM_COUNT_HASH = 3411492887L
    private val accessibilityUpdateSetListItemCountBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_list_item_count", ACCESSIBILITY_UPDATE_SET_LIST_ITEM_COUNT_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_LIST_ITEM_INDEX_HASH = 3411492887L
    private val accessibilityUpdateSetListItemIndexBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_list_item_index", ACCESSIBILITY_UPDATE_SET_LIST_ITEM_INDEX_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_LIST_ITEM_LEVEL_HASH = 3411492887L
    private val accessibilityUpdateSetListItemLevelBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_list_item_level", ACCESSIBILITY_UPDATE_SET_LIST_ITEM_LEVEL_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_LIST_ITEM_SELECTED_HASH = 1265174801L
    private val accessibilityUpdateSetListItemSelectedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_list_item_selected", ACCESSIBILITY_UPDATE_SET_LIST_ITEM_SELECTED_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_LIST_ITEM_EXPANDED_HASH = 1265174801L
    private val accessibilityUpdateSetListItemExpandedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_list_item_expanded", ACCESSIBILITY_UPDATE_SET_LIST_ITEM_EXPANDED_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_POPUP_TYPE_HASH = 2040885448L
    private val accessibilityUpdateSetPopupTypeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_popup_type", ACCESSIBILITY_UPDATE_SET_POPUP_TYPE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_CHECKED_HASH = 1265174801L
    private val accessibilityUpdateSetCheckedBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_checked", ACCESSIBILITY_UPDATE_SET_CHECKED_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_NUM_VALUE_HASH = 1794382983L
    private val accessibilityUpdateSetNumValueBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_num_value", ACCESSIBILITY_UPDATE_SET_NUM_VALUE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_NUM_RANGE_HASH = 2513314492L
    private val accessibilityUpdateSetNumRangeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_num_range", ACCESSIBILITY_UPDATE_SET_NUM_RANGE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_NUM_STEP_HASH = 1794382983L
    private val accessibilityUpdateSetNumStepBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_num_step", ACCESSIBILITY_UPDATE_SET_NUM_STEP_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_NUM_JUMP_HASH = 1794382983L
    private val accessibilityUpdateSetNumJumpBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_num_jump", ACCESSIBILITY_UPDATE_SET_NUM_JUMP_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_SCROLL_X_HASH = 1794382983L
    private val accessibilityUpdateSetScrollXBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_scroll_x", ACCESSIBILITY_UPDATE_SET_SCROLL_X_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_SCROLL_X_RANGE_HASH = 2513314492L
    private val accessibilityUpdateSetScrollXRangeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_scroll_x_range", ACCESSIBILITY_UPDATE_SET_SCROLL_X_RANGE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_SCROLL_Y_HASH = 1794382983L
    private val accessibilityUpdateSetScrollYBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_scroll_y", ACCESSIBILITY_UPDATE_SET_SCROLL_Y_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_SCROLL_Y_RANGE_HASH = 2513314492L
    private val accessibilityUpdateSetScrollYRangeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_scroll_y_range", ACCESSIBILITY_UPDATE_SET_SCROLL_Y_RANGE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TEXT_DECORATIONS_HASH = 1672422386L
    private val accessibilityUpdateSetTextDecorationsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_text_decorations", ACCESSIBILITY_UPDATE_SET_TEXT_DECORATIONS_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TEXT_ALIGN_HASH = 3725995085L
    private val accessibilityUpdateSetTextAlignBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_text_align", ACCESSIBILITY_UPDATE_SET_TEXT_ALIGN_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TEXT_SELECTION_HASH = 3119144029L
    private val accessibilityUpdateSetTextSelectionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_text_selection", ACCESSIBILITY_UPDATE_SET_TEXT_SELECTION_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_FLAG_HASH = 3758675396L
    private val accessibilityUpdateSetFlagBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_flag", ACCESSIBILITY_UPDATE_SET_FLAG_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_CLASSNAME_HASH = 2726140452L
    private val accessibilityUpdateSetClassnameBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_classname", ACCESSIBILITY_UPDATE_SET_CLASSNAME_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_PLACEHOLDER_HASH = 2726140452L
    private val accessibilityUpdateSetPlaceholderBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_placeholder", ACCESSIBILITY_UPDATE_SET_PLACEHOLDER_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_LANGUAGE_HASH = 2726140452L
    private val accessibilityUpdateSetLanguageBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_language", ACCESSIBILITY_UPDATE_SET_LANGUAGE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_TEXT_ORIENTATION_HASH = 1265174801L
    private val accessibilityUpdateSetTextOrientationBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_text_orientation", ACCESSIBILITY_UPDATE_SET_TEXT_ORIENTATION_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_LIST_ORIENTATION_HASH = 1265174801L
    private val accessibilityUpdateSetListOrientationBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_list_orientation", ACCESSIBILITY_UPDATE_SET_LIST_ORIENTATION_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_SHORTCUT_HASH = 2726140452L
    private val accessibilityUpdateSetShortcutBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_shortcut", ACCESSIBILITY_UPDATE_SET_SHORTCUT_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_URL_HASH = 2726140452L
    private val accessibilityUpdateSetUrlBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_url", ACCESSIBILITY_UPDATE_SET_URL_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_ROLE_DESCRIPTION_HASH = 2726140452L
    private val accessibilityUpdateSetRoleDescriptionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_role_description", ACCESSIBILITY_UPDATE_SET_ROLE_DESCRIPTION_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_STATE_DESCRIPTION_HASH = 2726140452L
    private val accessibilityUpdateSetStateDescriptionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_state_description", ACCESSIBILITY_UPDATE_SET_STATE_DESCRIPTION_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_COLOR_VALUE_HASH = 2948539648L
    private val accessibilityUpdateSetColorValueBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_color_value", ACCESSIBILITY_UPDATE_SET_COLOR_VALUE_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_BACKGROUND_COLOR_HASH = 2948539648L
    private val accessibilityUpdateSetBackgroundColorBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_background_color", ACCESSIBILITY_UPDATE_SET_BACKGROUND_COLOR_HASH)
    }

    private const val ACCESSIBILITY_UPDATE_SET_FOREGROUND_COLOR_HASH = 2948539648L
    private val accessibilityUpdateSetForegroundColorBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "accessibility_update_set_foreground_color", ACCESSIBILITY_UPDATE_SET_FOREGROUND_COLOR_HASH)
    }

    private const val IME_GET_SELECTION_HASH = 3690982128L
    private val imeGetSelectionBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "ime_get_selection", IME_GET_SELECTION_HASH)
    }

    private const val IME_GET_TEXT_HASH = 201670096L
    private val imeGetTextBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "ime_get_text", IME_GET_TEXT_HASH)
    }

    private const val VIRTUAL_KEYBOARD_SHOW_HASH = 3042891259L
    private val virtualKeyboardShowBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "virtual_keyboard_show", VIRTUAL_KEYBOARD_SHOW_HASH)
    }

    private const val VIRTUAL_KEYBOARD_HIDE_HASH = 3218959716L
    private val virtualKeyboardHideBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "virtual_keyboard_hide", VIRTUAL_KEYBOARD_HIDE_HASH)
    }

    private const val VIRTUAL_KEYBOARD_GET_HEIGHT_HASH = 3905245786L
    private val virtualKeyboardGetHeightBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "virtual_keyboard_get_height", VIRTUAL_KEYBOARD_GET_HEIGHT_HASH)
    }

    private const val HAS_HARDWARE_KEYBOARD_HASH = 36873697L
    private val hasHardwareKeyboardBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "has_hardware_keyboard", HAS_HARDWARE_KEYBOARD_HASH)
    }

    private const val SET_HARDWARE_KEYBOARD_CONNECTION_CHANGE_CALLBACK_HASH = 1611583062L
    private val setHardwareKeyboardConnectionChangeCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "set_hardware_keyboard_connection_change_callback", SET_HARDWARE_KEYBOARD_CONNECTION_CHANGE_CALLBACK_HASH)
    }

    private const val CURSOR_SET_SHAPE_HASH = 2026291549L
    private val cursorSetShapeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "cursor_set_shape", CURSOR_SET_SHAPE_HASH)
    }

    private const val CURSOR_GET_SHAPE_HASH = 1087724927L
    private val cursorGetShapeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "cursor_get_shape", CURSOR_GET_SHAPE_HASH)
    }

    private const val CURSOR_SET_CUSTOM_IMAGE_HASH = 1816663697L
    private val cursorSetCustomImageBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "cursor_set_custom_image", CURSOR_SET_CUSTOM_IMAGE_HASH)
    }

    private const val GET_SWAP_CANCEL_OK_HASH = 2240911060L
    private val getSwapCancelOkBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "get_swap_cancel_ok", GET_SWAP_CANCEL_OK_HASH)
    }

    private const val ENABLE_FOR_STEALING_FOCUS_HASH = 1286410249L
    private val enableForStealingFocusBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "enable_for_stealing_focus", ENABLE_FOR_STEALING_FOCUS_HASH)
    }

    private const val DIALOG_SHOW_HASH = 4115553226L
    private val dialogShowBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "dialog_show", DIALOG_SHOW_HASH)
    }

    private const val DIALOG_INPUT_TEXT_HASH = 3088703427L
    private val dialogInputTextBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "dialog_input_text", DIALOG_INPUT_TEXT_HASH)
    }

    private const val FILE_DIALOG_SHOW_HASH = 1386825884L
    private val fileDialogShowBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "file_dialog_show", FILE_DIALOG_SHOW_HASH)
    }

    private const val FILE_DIALOG_WITH_OPTIONS_SHOW_HASH = 1448789813L
    private val fileDialogWithOptionsShowBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "file_dialog_with_options_show", FILE_DIALOG_WITH_OPTIONS_SHOW_HASH)
    }

    private const val BEEP_HASH = 4051624405L
    private val beepBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "beep", BEEP_HASH)
    }

    private const val KEYBOARD_GET_LAYOUT_COUNT_HASH = 3905245786L
    private val keyboardGetLayoutCountBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "keyboard_get_layout_count", KEYBOARD_GET_LAYOUT_COUNT_HASH)
    }

    private const val KEYBOARD_GET_CURRENT_LAYOUT_HASH = 3905245786L
    private val keyboardGetCurrentLayoutBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "keyboard_get_current_layout", KEYBOARD_GET_CURRENT_LAYOUT_HASH)
    }

    private const val KEYBOARD_SET_CURRENT_LAYOUT_HASH = 1286410249L
    private val keyboardSetCurrentLayoutBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "keyboard_set_current_layout", KEYBOARD_SET_CURRENT_LAYOUT_HASH)
    }

    private const val KEYBOARD_GET_LAYOUT_LANGUAGE_HASH = 844755477L
    private val keyboardGetLayoutLanguageBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "keyboard_get_layout_language", KEYBOARD_GET_LAYOUT_LANGUAGE_HASH)
    }

    private const val KEYBOARD_GET_LAYOUT_NAME_HASH = 844755477L
    private val keyboardGetLayoutNameBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "keyboard_get_layout_name", KEYBOARD_GET_LAYOUT_NAME_HASH)
    }

    private const val KEYBOARD_GET_KEYCODE_FROM_PHYSICAL_HASH = 3447613187L
    private val keyboardGetKeycodeFromPhysicalBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "keyboard_get_keycode_from_physical", KEYBOARD_GET_KEYCODE_FROM_PHYSICAL_HASH)
    }

    private const val KEYBOARD_GET_LABEL_FROM_PHYSICAL_HASH = 3447613187L
    private val keyboardGetLabelFromPhysicalBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "keyboard_get_label_from_physical", KEYBOARD_GET_LABEL_FROM_PHYSICAL_HASH)
    }

    private const val SHOW_EMOJI_AND_SYMBOL_PICKER_HASH = 4051624405L
    private val showEmojiAndSymbolPickerBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "show_emoji_and_symbol_picker", SHOW_EMOJI_AND_SYMBOL_PICKER_HASH)
    }

    private const val COLOR_PICKER_HASH = 151643214L
    private val colorPickerBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "color_picker", COLOR_PICKER_HASH)
    }

    private const val PROCESS_EVENTS_HASH = 3218959716L
    private val processEventsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "process_events", PROCESS_EVENTS_HASH)
    }

    private const val FORCE_PROCESS_AND_DROP_EVENTS_HASH = 3218959716L
    private val forceProcessAndDropEventsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "force_process_and_drop_events", FORCE_PROCESS_AND_DROP_EVENTS_HASH)
    }

    private const val SET_NATIVE_ICON_HASH = 83702148L
    private val setNativeIconBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "set_native_icon", SET_NATIVE_ICON_HASH)
    }

    private const val SET_ICON_HASH = 532598488L
    private val setIconBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "set_icon", SET_ICON_HASH)
    }

    private const val CREATE_STATUS_INDICATOR_HASH = 1904285171L
    private val createStatusIndicatorBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "create_status_indicator", CREATE_STATUS_INDICATOR_HASH)
    }

    private const val STATUS_INDICATOR_SET_ICON_HASH = 666127730L
    private val statusIndicatorSetIconBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "status_indicator_set_icon", STATUS_INDICATOR_SET_ICON_HASH)
    }

    private const val STATUS_INDICATOR_SET_TOOLTIP_HASH = 501894301L
    private val statusIndicatorSetTooltipBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "status_indicator_set_tooltip", STATUS_INDICATOR_SET_TOOLTIP_HASH)
    }

    private const val STATUS_INDICATOR_SET_MENU_HASH = 4040184819L
    private val statusIndicatorSetMenuBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "status_indicator_set_menu", STATUS_INDICATOR_SET_MENU_HASH)
    }

    private const val STATUS_INDICATOR_SET_CALLBACK_HASH = 957362965L
    private val statusIndicatorSetCallbackBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "status_indicator_set_callback", STATUS_INDICATOR_SET_CALLBACK_HASH)
    }

    private const val STATUS_INDICATOR_GET_RECT_HASH = 3327874267L
    private val statusIndicatorGetRectBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "status_indicator_get_rect", STATUS_INDICATOR_GET_RECT_HASH)
    }

    private const val DELETE_STATUS_INDICATOR_HASH = 1286410249L
    private val deleteStatusIndicatorBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "delete_status_indicator", DELETE_STATUS_INDICATOR_HASH)
    }

    private const val TABLET_GET_DRIVER_COUNT_HASH = 3905245786L
    private val tabletGetDriverCountBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tablet_get_driver_count", TABLET_GET_DRIVER_COUNT_HASH)
    }

    private const val TABLET_GET_DRIVER_NAME_HASH = 844755477L
    private val tabletGetDriverNameBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tablet_get_driver_name", TABLET_GET_DRIVER_NAME_HASH)
    }

    private const val TABLET_GET_CURRENT_DRIVER_HASH = 201670096L
    private val tabletGetCurrentDriverBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tablet_get_current_driver", TABLET_GET_CURRENT_DRIVER_HASH)
    }

    private const val TABLET_SET_CURRENT_DRIVER_HASH = 83702148L
    private val tabletSetCurrentDriverBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "tablet_set_current_driver", TABLET_SET_CURRENT_DRIVER_HASH)
    }

    private const val IS_WINDOW_TRANSPARENCY_AVAILABLE_HASH = 36873697L
    private val isWindowTransparencyAvailableBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "is_window_transparency_available", IS_WINDOW_TRANSPARENCY_AVAILABLE_HASH)
    }

    private const val REGISTER_ADDITIONAL_OUTPUT_HASH = 3975164845L
    private val registerAdditionalOutputBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "register_additional_output", REGISTER_ADDITIONAL_OUTPUT_HASH)
    }

    private const val UNREGISTER_ADDITIONAL_OUTPUT_HASH = 3975164845L
    private val unregisterAdditionalOutputBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "unregister_additional_output", UNREGISTER_ADDITIONAL_OUTPUT_HASH)
    }

    private const val HAS_ADDITIONAL_OUTPUTS_HASH = 36873697L
    private val hasAdditionalOutputsBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "has_additional_outputs", HAS_ADDITIONAL_OUTPUTS_HASH)
    }

    private const val IS_IN_PIP_MODE_HASH = 1885608816L
    private val isInPipModeBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "is_in_pip_mode", IS_IN_PIP_MODE_HASH)
    }

    private const val PIP_MODE_ENTER_HASH = 1995695955L
    private val pipModeEnterBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "pip_mode_enter", PIP_MODE_ENTER_HASH)
    }

    private const val PIP_MODE_SET_ASPECT_RATIO_HASH = 3471927553L
    private val pipModeSetAspectRatioBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "pip_mode_set_aspect_ratio", PIP_MODE_SET_ASPECT_RATIO_HASH)
    }

    private const val PIP_MODE_SET_AUTO_ENTER_ON_BACKGROUND_HASH = 1661950165L
    private val pipModeSetAutoEnterOnBackgroundBind by lazy {
        ObjectCalls.getMethodBind("DisplayServer", "pip_mode_set_auto_enter_on_background", PIP_MODE_SET_AUTO_ENTER_ON_BACKGROUND_HASH)
    }
}
