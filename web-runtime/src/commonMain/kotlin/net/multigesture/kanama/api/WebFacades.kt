@file:OptIn(InternalKanamaBackendApi::class)

package net.multigesture.kanama.api

import net.multigesture.kanama.backend.GodotBackendCalls
import net.multigesture.kanama.backend.InitialGodotCallDescriptors as D
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2i

/**
 * Hand-shaped facades for families the browser cannot host or the compatibility renderer ignores
 * (tps-demo, task 60i), plus the import-compat aliases the ported corpus spells (the multiplayer
 * stand-ins are in `WebMultiplayer.kt`). The display/renderer settings that the compatibility
 * renderer ignores are no-ops.
 *
 * The ray query (`WebPhysicsQuery.kt`) is the one hand-shaped class that dispatches opcodes
 * directly (`WEB_HANDSHAPED`); every inert facade here is listed in `WEB_INERT_FACADES`, and the
 * generator's `--check` fails on any other hand-written dispatch or Godot class.
 */

// Liveness: `Node.isInsideTree()` and `GodotObject.isQueuedForDeletion()` are generated members
// since protocol 26 (task 64 parcel 6, opcodes 319/320) -- the earlier facades that collapsed both
// onto GD.isInstanceValid are gone.

/** Web-only erasure helper for the ported corpus (no desktop counterpart; stays an extension). */
fun GodotObject.asObject(): GodotObject = this

object Time {
  private val origin = kotlin.time.TimeSource.Monotonic.markNow()

  /** Web adaptation: a Kotlin monotonic clock (only relative time is consumed). */
  fun getTicksMsec(): Long = origin.elapsedNow().inWholeMilliseconds

  // ===== BEGIN GENERATED ENUMS: Time (scripts/generate_web_wrappers.py — do not edit) =====
  value class Month(override val value: Long) : GodotEnumValue {
    companion object {
      val JANUARY: Month get() = Month(1L)
      val FEBRUARY: Month get() = Month(2L)
      val MARCH: Month get() = Month(3L)
      val APRIL: Month get() = Month(4L)
      val MAY: Month get() = Month(5L)
      val JUNE: Month get() = Month(6L)
      val JULY: Month get() = Month(7L)
      val AUGUST: Month get() = Month(8L)
      val SEPTEMBER: Month get() = Month(9L)
      val OCTOBER: Month get() = Month(10L)
      val NOVEMBER: Month get() = Month(11L)
      val DECEMBER: Month get() = Month(12L)
    }
  }

  value class Weekday(override val value: Long) : GodotEnumValue {
    companion object {
      val SUNDAY: Weekday get() = Weekday(0L)
      val MONDAY: Weekday get() = Weekday(1L)
      val TUESDAY: Weekday get() = Weekday(2L)
      val WEDNESDAY: Weekday get() = Weekday(3L)
      val THURSDAY: Weekday get() = Weekday(4L)
      val FRIDAY: Weekday get() = Weekday(5L)
      val SATURDAY: Weekday get() = Weekday(6L)
    }
  }
  // ===== END GENERATED ENUMS: Time =====
}

/** Same owned-handle idiom for the settings menu's radio groups. */
inline fun <R> ButtonGroup.use(block: (ButtonGroup) -> R): R {
  try {
    return block(this)
  } finally {
    close()
  }
}

object IP {
  // KANAMA-BLOCKED(since:2026-07-27, webcall:IP.get_local_addresses): Web dispatches no IP call
  /** No socket surface on Web; the lobby falls back to its "this device" copy. */
  fun getLocalAddresses(): List<String> = emptyList()

  // ===== BEGIN GENERATED ENUMS: IP (scripts/generate_web_wrappers.py — do not edit) =====
  value class ResolverStatus(override val value: Long) : GodotEnumValue {
    companion object {
      val NONE: ResolverStatus get() = ResolverStatus(0L)
      val WAITING: ResolverStatus get() = ResolverStatus(1L)
      val DONE: ResolverStatus get() = ResolverStatus(2L)
      val ERROR: ResolverStatus get() = ResolverStatus(3L)
    }
  }

  value class Type(override val value: Long) : GodotEnumValue {
    companion object {
      val NONE: Type get() = Type(0L)
      val IPV4: Type get() = Type(1L)
      val IPV6: Type get() = Type(2L)
      val ANY: Type get() = Type(3L)
    }
  }
  // ===== END GENERATED ENUMS: IP =====
}

// ---------------------------------------------------------------------------
// Display / renderer settings. The compatibility renderer ignores most of the
// tps graphics menu, so those writes are no-ops rather than fake successes.
// ---------------------------------------------------------------------------

object DisplayServer {
  /** Web adaptation: the demo branches on "headless"; the browser is a real display. */
  fun getName(): String = "Web"

  fun windowSetVsyncMode(@Suppress("UNUSED_PARAMETER") mode: DisplayServer.VSyncMode) = Unit

  fun windowGetVsyncMode(): DisplayServer.VSyncMode = DisplayServer.VSyncMode.ENABLED

  /**
   * Structural limit (task 64 parcel 8): a browser page has no OS window to measure and no notch
   * to route around -- the canvas IS the usable area, and Godot's Web DisplayServer answers the
   * whole window for both. The one caller in the corpus (tps-demo's `SafeArea`, a mobile
   * notch/cutout helper) returns early on `OS.hasFeature("web")`, so these are never reached on
   * Web; they fail loud rather than inventing a rectangle a caller would act on.
   */
  fun windowGetSize(@Suppress("UNUSED_PARAMETER") windowId: Int = 0): Vector2i =
    unsupportedWebGameplayFamily("DisplayServer.window_get_size")

  fun getDisplaySafeArea(): Rect2i = unsupportedWebGameplayFamily("DisplayServer.get_display_safe_area")

  // ===== BEGIN GENERATED ENUMS: DisplayServer (scripts/generate_web_wrappers.py — do not edit) =====
  value class Feature(override val value: Long) : GodotEnumValue {
    companion object {
      val GLOBAL_MENU: Feature get() = Feature(0L)
      val SUBWINDOWS: Feature get() = Feature(1L)
      val TOUCHSCREEN: Feature get() = Feature(2L)
      val MOUSE: Feature get() = Feature(3L)
      val MOUSE_WARP: Feature get() = Feature(4L)
      val CLIPBOARD: Feature get() = Feature(5L)
      val VIRTUAL_KEYBOARD: Feature get() = Feature(6L)
      val CURSOR_SHAPE: Feature get() = Feature(7L)
      val CUSTOM_CURSOR_SHAPE: Feature get() = Feature(8L)
      val NATIVE_DIALOG: Feature get() = Feature(9L)
      val IME: Feature get() = Feature(10L)
      val WINDOW_TRANSPARENCY: Feature get() = Feature(11L)
      val HIDPI: Feature get() = Feature(12L)
      val ICON: Feature get() = Feature(13L)
      val NATIVE_ICON: Feature get() = Feature(14L)
      val ORIENTATION: Feature get() = Feature(15L)
      val SWAP_BUFFERS: Feature get() = Feature(16L)
      val CLIPBOARD_PRIMARY: Feature get() = Feature(18L)
      val TEXT_TO_SPEECH: Feature get() = Feature(19L)
      val EXTEND_TO_TITLE: Feature get() = Feature(20L)
      val SCREEN_CAPTURE: Feature get() = Feature(21L)
      val STATUS_INDICATOR: Feature get() = Feature(22L)
      val NATIVE_HELP: Feature get() = Feature(23L)
      val NATIVE_DIALOG_INPUT: Feature get() = Feature(24L)
      val NATIVE_DIALOG_FILE: Feature get() = Feature(25L)
      val NATIVE_DIALOG_FILE_EXTRA: Feature get() = Feature(26L)
      val WINDOW_DRAG: Feature get() = Feature(27L)
      val SCREEN_EXCLUDE_FROM_CAPTURE: Feature get() = Feature(28L)
      val WINDOW_EMBEDDING: Feature get() = Feature(29L)
      val NATIVE_DIALOG_FILE_MIME: Feature get() = Feature(30L)
      val EMOJI_AND_SYMBOL_PICKER: Feature get() = Feature(31L)
      val NATIVE_COLOR_PICKER: Feature get() = Feature(32L)
      val SELF_FITTING_WINDOWS: Feature get() = Feature(33L)
      val ACCESSIBILITY_SCREEN_READER: Feature get() = Feature(34L)
      val HDR_OUTPUT: Feature get() = Feature(35L)
      val PIP_MODE: Feature get() = Feature(36L)
    }
  }

  value class AccessibilityRole(override val value: Long) : GodotEnumValue {
    companion object {
      val UNKNOWN: AccessibilityRole get() = AccessibilityRole(0L)
      val DEFAULT_BUTTON: AccessibilityRole get() = AccessibilityRole(1L)
      val AUDIO: AccessibilityRole get() = AccessibilityRole(2L)
      val VIDEO: AccessibilityRole get() = AccessibilityRole(3L)
      val STATIC_TEXT: AccessibilityRole get() = AccessibilityRole(4L)
      val CONTAINER: AccessibilityRole get() = AccessibilityRole(5L)
      val PANEL: AccessibilityRole get() = AccessibilityRole(6L)
      val BUTTON: AccessibilityRole get() = AccessibilityRole(7L)
      val LINK: AccessibilityRole get() = AccessibilityRole(8L)
      val CHECK_BOX: AccessibilityRole get() = AccessibilityRole(9L)
      val RADIO_BUTTON: AccessibilityRole get() = AccessibilityRole(10L)
      val CHECK_BUTTON: AccessibilityRole get() = AccessibilityRole(11L)
      val SCROLL_BAR: AccessibilityRole get() = AccessibilityRole(12L)
      val SCROLL_VIEW: AccessibilityRole get() = AccessibilityRole(13L)
      val SPLITTER: AccessibilityRole get() = AccessibilityRole(14L)
      val SLIDER: AccessibilityRole get() = AccessibilityRole(15L)
      val SPIN_BUTTON: AccessibilityRole get() = AccessibilityRole(16L)
      val PROGRESS_INDICATOR: AccessibilityRole get() = AccessibilityRole(17L)
      val TEXT_FIELD: AccessibilityRole get() = AccessibilityRole(18L)
      val MULTILINE_TEXT_FIELD: AccessibilityRole get() = AccessibilityRole(19L)
      val COLOR_PICKER: AccessibilityRole get() = AccessibilityRole(20L)
      val TABLE: AccessibilityRole get() = AccessibilityRole(21L)
      val CELL: AccessibilityRole get() = AccessibilityRole(22L)
      val ROW: AccessibilityRole get() = AccessibilityRole(23L)
      val ROW_GROUP: AccessibilityRole get() = AccessibilityRole(24L)
      val ROW_HEADER: AccessibilityRole get() = AccessibilityRole(25L)
      val COLUMN_HEADER: AccessibilityRole get() = AccessibilityRole(26L)
      val TREE: AccessibilityRole get() = AccessibilityRole(27L)
      val TREE_ITEM: AccessibilityRole get() = AccessibilityRole(28L)
      val LIST: AccessibilityRole get() = AccessibilityRole(29L)
      val LIST_ITEM: AccessibilityRole get() = AccessibilityRole(30L)
      val LIST_BOX: AccessibilityRole get() = AccessibilityRole(31L)
      val LIST_BOX_OPTION: AccessibilityRole get() = AccessibilityRole(32L)
      val TAB_BAR: AccessibilityRole get() = AccessibilityRole(33L)
      val TAB: AccessibilityRole get() = AccessibilityRole(34L)
      val TAB_PANEL: AccessibilityRole get() = AccessibilityRole(35L)
      val MENU_BAR: AccessibilityRole get() = AccessibilityRole(36L)
      val MENU: AccessibilityRole get() = AccessibilityRole(37L)
      val MENU_ITEM: AccessibilityRole get() = AccessibilityRole(38L)
      val MENU_ITEM_CHECK_BOX: AccessibilityRole get() = AccessibilityRole(39L)
      val MENU_ITEM_RADIO: AccessibilityRole get() = AccessibilityRole(40L)
      val IMAGE: AccessibilityRole get() = AccessibilityRole(41L)
      val WINDOW: AccessibilityRole get() = AccessibilityRole(42L)
      val TITLE_BAR: AccessibilityRole get() = AccessibilityRole(43L)
      val DIALOG: AccessibilityRole get() = AccessibilityRole(44L)
      val TOOLTIP: AccessibilityRole get() = AccessibilityRole(45L)
      val REGION: AccessibilityRole get() = AccessibilityRole(46L)
      val TEXT_RUN: AccessibilityRole get() = AccessibilityRole(47L)
    }
  }

  value class AccessibilityPopupType(override val value: Long) : GodotEnumValue {
    companion object {
      val MENU: AccessibilityPopupType get() = AccessibilityPopupType(0L)
      val LIST: AccessibilityPopupType get() = AccessibilityPopupType(1L)
      val TREE: AccessibilityPopupType get() = AccessibilityPopupType(2L)
      val DIALOG: AccessibilityPopupType get() = AccessibilityPopupType(3L)
    }
  }

  value class AccessibilityFlags(override val value: Long) : GodotEnumValue {
    companion object {
      val HIDDEN: AccessibilityFlags get() = AccessibilityFlags(0L)
      val MULTISELECTABLE: AccessibilityFlags get() = AccessibilityFlags(1L)
      val REQUIRED: AccessibilityFlags get() = AccessibilityFlags(2L)
      val VISITED: AccessibilityFlags get() = AccessibilityFlags(3L)
      val BUSY: AccessibilityFlags get() = AccessibilityFlags(4L)
      val MODAL: AccessibilityFlags get() = AccessibilityFlags(5L)
      val TOUCH_PASSTHROUGH: AccessibilityFlags get() = AccessibilityFlags(6L)
      val READONLY: AccessibilityFlags get() = AccessibilityFlags(7L)
      val DISABLED: AccessibilityFlags get() = AccessibilityFlags(8L)
      val CLIPS_CHILDREN: AccessibilityFlags get() = AccessibilityFlags(9L)
    }
  }

  value class AccessibilityAction(override val value: Long) : GodotEnumValue {
    companion object {
      val CLICK: AccessibilityAction get() = AccessibilityAction(0L)
      val FOCUS: AccessibilityAction get() = AccessibilityAction(1L)
      val BLUR: AccessibilityAction get() = AccessibilityAction(2L)
      val COLLAPSE: AccessibilityAction get() = AccessibilityAction(3L)
      val EXPAND: AccessibilityAction get() = AccessibilityAction(4L)
      val DECREMENT: AccessibilityAction get() = AccessibilityAction(5L)
      val INCREMENT: AccessibilityAction get() = AccessibilityAction(6L)
      val HIDE_TOOLTIP: AccessibilityAction get() = AccessibilityAction(7L)
      val SHOW_TOOLTIP: AccessibilityAction get() = AccessibilityAction(8L)
      val SET_TEXT_SELECTION: AccessibilityAction get() = AccessibilityAction(9L)
      val REPLACE_SELECTED_TEXT: AccessibilityAction get() = AccessibilityAction(10L)
      val SCROLL_BACKWARD: AccessibilityAction get() = AccessibilityAction(11L)
      val SCROLL_DOWN: AccessibilityAction get() = AccessibilityAction(12L)
      val SCROLL_FORWARD: AccessibilityAction get() = AccessibilityAction(13L)
      val SCROLL_LEFT: AccessibilityAction get() = AccessibilityAction(14L)
      val SCROLL_RIGHT: AccessibilityAction get() = AccessibilityAction(15L)
      val SCROLL_UP: AccessibilityAction get() = AccessibilityAction(16L)
      val SCROLL_INTO_VIEW: AccessibilityAction get() = AccessibilityAction(17L)
      val SCROLL_TO_POINT: AccessibilityAction get() = AccessibilityAction(18L)
      val SET_SCROLL_OFFSET: AccessibilityAction get() = AccessibilityAction(19L)
      val SET_VALUE: AccessibilityAction get() = AccessibilityAction(20L)
      val SHOW_CONTEXT_MENU: AccessibilityAction get() = AccessibilityAction(21L)
      val CUSTOM: AccessibilityAction get() = AccessibilityAction(22L)
    }
  }

  value class AccessibilityLiveMode(override val value: Long) : GodotEnumValue {
    companion object {
      val OFF: AccessibilityLiveMode get() = AccessibilityLiveMode(0L)
      val POLITE: AccessibilityLiveMode get() = AccessibilityLiveMode(1L)
      val ASSERTIVE: AccessibilityLiveMode get() = AccessibilityLiveMode(2L)
    }
  }

  value class AccessibilityScrollUnit(override val value: Long) : GodotEnumValue {
    companion object {
      val ITEM: AccessibilityScrollUnit get() = AccessibilityScrollUnit(0L)
      val PAGE: AccessibilityScrollUnit get() = AccessibilityScrollUnit(1L)
    }
  }

  value class AccessibilityScrollHint(override val value: Long) : GodotEnumValue {
    companion object {
      val TOP_LEFT: AccessibilityScrollHint get() = AccessibilityScrollHint(0L)
      val BOTTOM_RIGHT: AccessibilityScrollHint get() = AccessibilityScrollHint(1L)
      val TOP_EDGE: AccessibilityScrollHint get() = AccessibilityScrollHint(2L)
      val BOTTOM_EDGE: AccessibilityScrollHint get() = AccessibilityScrollHint(3L)
      val LEFT_EDGE: AccessibilityScrollHint get() = AccessibilityScrollHint(4L)
      val RIGHT_EDGE: AccessibilityScrollHint get() = AccessibilityScrollHint(5L)
    }
  }

  value class MouseMode(override val value: Long) : GodotEnumValue {
    companion object {
      val VISIBLE: MouseMode get() = MouseMode(0L)
      val HIDDEN: MouseMode get() = MouseMode(1L)
      val CAPTURED: MouseMode get() = MouseMode(2L)
      val CONFINED: MouseMode get() = MouseMode(3L)
      val CONFINED_HIDDEN: MouseMode get() = MouseMode(4L)
      val MAX: MouseMode get() = MouseMode(5L)
    }
  }

  value class ScreenOrientation(override val value: Long) : GodotEnumValue {
    companion object {
      val LANDSCAPE: ScreenOrientation get() = ScreenOrientation(0L)
      val PORTRAIT: ScreenOrientation get() = ScreenOrientation(1L)
      val REVERSE_LANDSCAPE: ScreenOrientation get() = ScreenOrientation(2L)
      val REVERSE_PORTRAIT: ScreenOrientation get() = ScreenOrientation(3L)
      val SENSOR_LANDSCAPE: ScreenOrientation get() = ScreenOrientation(4L)
      val SENSOR_PORTRAIT: ScreenOrientation get() = ScreenOrientation(5L)
      val SENSOR: ScreenOrientation get() = ScreenOrientation(6L)
    }
  }

  value class VirtualKeyboardType(override val value: Long) : GodotEnumValue {
    companion object {
      val DEFAULT: VirtualKeyboardType get() = VirtualKeyboardType(0L)
      val MULTILINE: VirtualKeyboardType get() = VirtualKeyboardType(1L)
      val NUMBER: VirtualKeyboardType get() = VirtualKeyboardType(2L)
      val NUMBER_DECIMAL: VirtualKeyboardType get() = VirtualKeyboardType(3L)
      val PHONE: VirtualKeyboardType get() = VirtualKeyboardType(4L)
      val EMAIL_ADDRESS: VirtualKeyboardType get() = VirtualKeyboardType(5L)
      val PASSWORD: VirtualKeyboardType get() = VirtualKeyboardType(6L)
      val URL: VirtualKeyboardType get() = VirtualKeyboardType(7L)
    }
  }

  value class CursorShape(override val value: Long) : GodotEnumValue {
    companion object {
      val ARROW: CursorShape get() = CursorShape(0L)
      val IBEAM: CursorShape get() = CursorShape(1L)
      val POINTING_HAND: CursorShape get() = CursorShape(2L)
      val CROSS: CursorShape get() = CursorShape(3L)
      val WAIT: CursorShape get() = CursorShape(4L)
      val BUSY: CursorShape get() = CursorShape(5L)
      val DRAG: CursorShape get() = CursorShape(6L)
      val CAN_DROP: CursorShape get() = CursorShape(7L)
      val FORBIDDEN: CursorShape get() = CursorShape(8L)
      val VSIZE: CursorShape get() = CursorShape(9L)
      val HSIZE: CursorShape get() = CursorShape(10L)
      val BDIAGSIZE: CursorShape get() = CursorShape(11L)
      val FDIAGSIZE: CursorShape get() = CursorShape(12L)
      val MOVE: CursorShape get() = CursorShape(13L)
      val VSPLIT: CursorShape get() = CursorShape(14L)
      val HSPLIT: CursorShape get() = CursorShape(15L)
      val HELP: CursorShape get() = CursorShape(16L)
      val MAX: CursorShape get() = CursorShape(17L)
    }
  }

  value class FileDialogMode(override val value: Long) : GodotEnumValue {
    companion object {
      val OPEN_FILE: FileDialogMode get() = FileDialogMode(0L)
      val OPEN_FILES: FileDialogMode get() = FileDialogMode(1L)
      val OPEN_DIR: FileDialogMode get() = FileDialogMode(2L)
      val OPEN_ANY: FileDialogMode get() = FileDialogMode(3L)
      val SAVE_FILE: FileDialogMode get() = FileDialogMode(4L)
    }
  }

  value class WindowMode(override val value: Long) : GodotEnumValue {
    companion object {
      val WINDOWED: WindowMode get() = WindowMode(0L)
      val MINIMIZED: WindowMode get() = WindowMode(1L)
      val MAXIMIZED: WindowMode get() = WindowMode(2L)
      val FULLSCREEN: WindowMode get() = WindowMode(3L)
      val EXCLUSIVE_FULLSCREEN: WindowMode get() = WindowMode(4L)
    }
  }

  value class ProgressState(override val value: Long) : GodotEnumValue {
    companion object {
      val NOPROGRESS: ProgressState get() = ProgressState(0L)
      val INDETERMINATE: ProgressState get() = ProgressState(1L)
      val NORMAL: ProgressState get() = ProgressState(2L)
      val ERROR: ProgressState get() = ProgressState(3L)
      val PAUSED: ProgressState get() = ProgressState(4L)
    }
  }

  value class WindowFlags(override val value: Long) : GodotEnumValue {
    companion object {
      val RESIZE_DISABLED: WindowFlags get() = WindowFlags(0L)
      val BORDERLESS: WindowFlags get() = WindowFlags(1L)
      val ALWAYS_ON_TOP: WindowFlags get() = WindowFlags(2L)
      val TRANSPARENT: WindowFlags get() = WindowFlags(3L)
      val NO_FOCUS: WindowFlags get() = WindowFlags(4L)
      val POPUP: WindowFlags get() = WindowFlags(5L)
      val EXTEND_TO_TITLE: WindowFlags get() = WindowFlags(6L)
      val MOUSE_PASSTHROUGH: WindowFlags get() = WindowFlags(7L)
      val SHARP_CORNERS: WindowFlags get() = WindowFlags(8L)
      val EXCLUDE_FROM_CAPTURE: WindowFlags get() = WindowFlags(9L)
      val POPUP_WM_HINT: WindowFlags get() = WindowFlags(10L)
      val MINIMIZE_DISABLED: WindowFlags get() = WindowFlags(11L)
      val MAXIMIZE_DISABLED: WindowFlags get() = WindowFlags(12L)
      val MAX: WindowFlags get() = WindowFlags(13L)
    }
  }

  value class WindowEvent(override val value: Long) : GodotEnumValue {
    companion object {
      val MOUSE_ENTER: WindowEvent get() = WindowEvent(0L)
      val MOUSE_EXIT: WindowEvent get() = WindowEvent(1L)
      val FOCUS_IN: WindowEvent get() = WindowEvent(2L)
      val FOCUS_OUT: WindowEvent get() = WindowEvent(3L)
      val CLOSE_REQUEST: WindowEvent get() = WindowEvent(4L)
      val GO_BACK_REQUEST: WindowEvent get() = WindowEvent(5L)
      val DPI_CHANGE: WindowEvent get() = WindowEvent(6L)
      val TITLEBAR_CHANGE: WindowEvent get() = WindowEvent(7L)
      val FORCE_CLOSE: WindowEvent get() = WindowEvent(8L)
      val OUTPUT_MAX_LINEAR_VALUE_CHANGED: WindowEvent get() = WindowEvent(9L)
    }
  }

  value class WindowResizeEdge(override val value: Long) : GodotEnumValue {
    companion object {
      val TOP_LEFT: WindowResizeEdge get() = WindowResizeEdge(0L)
      val TOP: WindowResizeEdge get() = WindowResizeEdge(1L)
      val TOP_RIGHT: WindowResizeEdge get() = WindowResizeEdge(2L)
      val LEFT: WindowResizeEdge get() = WindowResizeEdge(3L)
      val RIGHT: WindowResizeEdge get() = WindowResizeEdge(4L)
      val BOTTOM_LEFT: WindowResizeEdge get() = WindowResizeEdge(5L)
      val BOTTOM: WindowResizeEdge get() = WindowResizeEdge(6L)
      val BOTTOM_RIGHT: WindowResizeEdge get() = WindowResizeEdge(7L)
      val MAX: WindowResizeEdge get() = WindowResizeEdge(8L)
    }
  }

  value class VSyncMode(override val value: Long) : GodotEnumValue {
    companion object {
      val DISABLED: VSyncMode get() = VSyncMode(0L)
      val ENABLED: VSyncMode get() = VSyncMode(1L)
      val ADAPTIVE: VSyncMode get() = VSyncMode(2L)
      val MAILBOX: VSyncMode get() = VSyncMode(3L)
    }
  }

  value class HandleType(override val value: Long) : GodotEnumValue {
    companion object {
      val DISPLAY_HANDLE: HandleType get() = HandleType(0L)
      val WINDOW_HANDLE: HandleType get() = HandleType(1L)
      val WINDOW_VIEW: HandleType get() = HandleType(2L)
      val OPENGL_CONTEXT: HandleType get() = HandleType(3L)
      val EGL_DISPLAY: HandleType get() = HandleType(4L)
      val EGL_CONFIG: HandleType get() = HandleType(5L)
      val GLX_VISUALID: HandleType get() = HandleType(6L)
      val GLX_FBCONFIG: HandleType get() = HandleType(7L)
    }
  }

  value class TTSUtteranceEvent(override val value: Long) : GodotEnumValue {
    companion object {
      val STARTED: TTSUtteranceEvent get() = TTSUtteranceEvent(0L)
      val ENDED: TTSUtteranceEvent get() = TTSUtteranceEvent(1L)
      val CANCELED: TTSUtteranceEvent get() = TTSUtteranceEvent(2L)
      val BOUNDARY: TTSUtteranceEvent get() = TTSUtteranceEvent(3L)
    }
  }
  // ===== END GENERATED ENUMS: DisplayServer =====
}

// ---------------------------------------------------------------------------
// Resource loading facades.
// ---------------------------------------------------------------------------

/**
 * Threaded-load store behind `ResourceLoader.loadThreadedRequest` / `loadThreadedGetStatus…` /
 * `loadThreadedGet…` (generated members since task 64 parcel 8). The Web export is a `nothreads`
 * build, so a background load would never make progress: the request loads synchronously and
 * every later poll reports LOADED until `loadThreadedGet` takes the scene (as Godot, the store keeps
 * it no longer: INVALID_RESOURCE after). The loading screen just completes on the first poll.
 */
internal object ThreadedLoad {
  private val loaded = mutableMapOf<String, PackedScene?>()

  fun request(path: String) {
    loaded[path] = ResourceLoader.loadPackedScene(path)
  }

  fun status(path: String): ResourceLoader.ThreadLoadStatus =
    when {
      !loaded.containsKey(path) -> ResourceLoader.ThreadLoadStatus.INVALID_RESOURCE
      loaded[path] == null -> ResourceLoader.ThreadLoadStatus.FAILED
      else -> ResourceLoader.ThreadLoadStatus.LOADED
    }

  fun take(path: String): PackedScene? = loaded.remove(path)
}
