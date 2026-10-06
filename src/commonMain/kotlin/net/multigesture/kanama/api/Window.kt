package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/**
 * Base class for all windows, dialogs, and popups.
 *
 * Generated from Godot docs: Window
 */
open class Window(handle: GodotHandle) : Viewport(handle) {
    var mode: Window.Mode
        @JvmName("modeProperty")
        get() = getMode()
        @JvmName("setModeProperty")
        set(value) = setMode(value)

    var title: String
        @JvmName("titleProperty")
        get() = getTitle()
        @JvmName("setTitleProperty")
        set(value) = setTitle(value)

    var initialPosition: Window.WindowInitialPosition
        @JvmName("initialPositionProperty")
        get() = getInitialPosition()
        @JvmName("setInitialPositionProperty")
        set(value) = setInitialPosition(value)

    var position: Vector2i
        @JvmName("positionProperty")
        get() = getPosition()
        @JvmName("setPositionProperty")
        set(value) = setPosition(value)

    var size: Vector2i
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    var currentScreen: Int
        @JvmName("currentScreenProperty")
        get() = getCurrentScreen()
        @JvmName("setCurrentScreenProperty")
        set(value) = setCurrentScreen(value)

    var nonclientArea: Rect2i
        @JvmName("nonclientAreaProperty")
        get() = getNonclientArea()
        @JvmName("setNonclientAreaProperty")
        set(value) = setNonclientArea(value)

    var mousePassthroughPolygon: List<Vector2>
        @JvmName("mousePassthroughPolygonProperty")
        get() = getMousePassthroughPolygon()
        @JvmName("setMousePassthroughPolygonProperty")
        set(value) = setMousePassthroughPolygon(value)

    var visible: Boolean
        @JvmName("visibleProperty")
        get() = isVisible()
        @JvmName("setVisibleProperty")
        set(value) = setVisible(value)

    var wrapControls: Boolean
        @JvmName("wrapControlsProperty")
        get() = isWrappingControls()
        @JvmName("setWrapControlsProperty")
        set(value) = setWrapControls(value)

    var transient: Boolean
        @JvmName("transientProperty")
        get() = isTransient()
        @JvmName("setTransientProperty")
        set(value) = setTransient(value)

    var transientToFocused: Boolean
        @JvmName("transientToFocusedProperty")
        get() = isTransientToFocused()
        @JvmName("setTransientToFocusedProperty")
        set(value) = setTransientToFocused(value)

    var exclusive: Boolean
        @JvmName("exclusiveProperty")
        get() = isExclusive()
        @JvmName("setExclusiveProperty")
        set(value) = setExclusive(value)

    var unresizable: Boolean
        @JvmName("unresizableProperty")
        get() = getFlag(Window.Flags.RESIZE_DISABLED)
        @JvmName("setUnresizableProperty")
        set(value) = setFlag(Window.Flags.RESIZE_DISABLED, value)

    var borderless: Boolean
        @JvmName("borderlessProperty")
        get() = getFlag(Window.Flags.BORDERLESS)
        @JvmName("setBorderlessProperty")
        set(value) = setFlag(Window.Flags.BORDERLESS, value)

    var alwaysOnTop: Boolean
        @JvmName("alwaysOnTopProperty")
        get() = getFlag(Window.Flags.ALWAYS_ON_TOP)
        @JvmName("setAlwaysOnTopProperty")
        set(value) = setFlag(Window.Flags.ALWAYS_ON_TOP, value)

    var transparent: Boolean
        @JvmName("transparentProperty")
        get() = getFlag(Window.Flags.TRANSPARENT)
        @JvmName("setTransparentProperty")
        set(value) = setFlag(Window.Flags.TRANSPARENT, value)

    var unfocusable: Boolean
        @JvmName("unfocusableProperty")
        get() = getFlag(Window.Flags.NO_FOCUS)
        @JvmName("setUnfocusableProperty")
        set(value) = setFlag(Window.Flags.NO_FOCUS, value)

    var popupWindow: Boolean
        @JvmName("popupWindowProperty")
        get() = getFlag(Window.Flags.POPUP)
        @JvmName("setPopupWindowProperty")
        set(value) = setFlag(Window.Flags.POPUP, value)

    var extendToTitle: Boolean
        @JvmName("extendToTitleProperty")
        get() = getFlag(Window.Flags.EXTEND_TO_TITLE)
        @JvmName("setExtendToTitleProperty")
        set(value) = setFlag(Window.Flags.EXTEND_TO_TITLE, value)

    var mousePassthrough: Boolean
        @JvmName("mousePassthroughProperty")
        get() = getFlag(Window.Flags.MOUSE_PASSTHROUGH)
        @JvmName("setMousePassthroughProperty")
        set(value) = setFlag(Window.Flags.MOUSE_PASSTHROUGH, value)

    var sharpCorners: Boolean
        @JvmName("sharpCornersProperty")
        get() = getFlag(Window.Flags.SHARP_CORNERS)
        @JvmName("setSharpCornersProperty")
        set(value) = setFlag(Window.Flags.SHARP_CORNERS, value)

    var excludeFromCapture: Boolean
        @JvmName("excludeFromCaptureProperty")
        get() = getFlag(Window.Flags.EXCLUDE_FROM_CAPTURE)
        @JvmName("setExcludeFromCaptureProperty")
        set(value) = setFlag(Window.Flags.EXCLUDE_FROM_CAPTURE, value)

    var popupWmHint: Boolean
        @JvmName("popupWmHintProperty")
        get() = getFlag(Window.Flags.POPUP_WM_HINT)
        @JvmName("setPopupWmHintProperty")
        set(value) = setFlag(Window.Flags.POPUP_WM_HINT, value)

    var minimizeDisabled: Boolean
        @JvmName("minimizeDisabledProperty")
        get() = getFlag(Window.Flags.MINIMIZE_DISABLED)
        @JvmName("setMinimizeDisabledProperty")
        set(value) = setFlag(Window.Flags.MINIMIZE_DISABLED, value)

    var maximizeDisabled: Boolean
        @JvmName("maximizeDisabledProperty")
        get() = getFlag(Window.Flags.MAXIMIZE_DISABLED)
        @JvmName("setMaximizeDisabledProperty")
        set(value) = setFlag(Window.Flags.MAXIMIZE_DISABLED, value)

    var forceNative: Boolean
        @JvmName("forceNativeProperty")
        get() = getForceNative()
        @JvmName("setForceNativeProperty")
        set(value) = setForceNative(value)

    var minSize: Vector2i
        @JvmName("minSizeProperty")
        get() = getMinSize()
        @JvmName("setMinSizeProperty")
        set(value) = setMinSize(value)

    var maxSize: Vector2i
        @JvmName("maxSizeProperty")
        get() = getMaxSize()
        @JvmName("setMaxSizeProperty")
        set(value) = setMaxSize(value)

    var keepTitleVisible: Boolean
        @JvmName("keepTitleVisibleProperty")
        get() = getKeepTitleVisible()
        @JvmName("setKeepTitleVisibleProperty")
        set(value) = setKeepTitleVisible(value)

    var contentScaleSize: Vector2i
        @JvmName("contentScaleSizeProperty")
        get() = getContentScaleSize()
        @JvmName("setContentScaleSizeProperty")
        set(value) = setContentScaleSize(value)

    var contentScaleMode: Window.ContentScaleMode
        @JvmName("contentScaleModeProperty")
        get() = getContentScaleMode()
        @JvmName("setContentScaleModeProperty")
        set(value) = setContentScaleMode(value)

    var contentScaleAspect: Window.ContentScaleAspect
        @JvmName("contentScaleAspectProperty")
        get() = getContentScaleAspect()
        @JvmName("setContentScaleAspectProperty")
        set(value) = setContentScaleAspect(value)

    var contentScaleStretch: Window.ContentScaleStretch
        @JvmName("contentScaleStretchProperty")
        get() = getContentScaleStretch()
        @JvmName("setContentScaleStretchProperty")
        set(value) = setContentScaleStretch(value)

    var contentScaleFactor: Double
        @JvmName("contentScaleFactorProperty")
        get() = getContentScaleFactor()
        @JvmName("setContentScaleFactorProperty")
        set(value) = setContentScaleFactor(value)

    var hdrOutputRequested: Boolean
        @JvmName("hdrOutputRequestedProperty")
        get() = isHdrOutputRequested()
        @JvmName("setHdrOutputRequestedProperty")
        set(value) = setHdrOutputRequested(value)

    var autoTranslate: Boolean
        @JvmName("autoTranslateProperty")
        get() = isAutoTranslating()
        @JvmName("setAutoTranslateProperty")
        set(value) = setAutoTranslate(value)

    var accessibilityName: String
        @JvmName("accessibilityNameProperty")
        get() = getAccessibilityName()
        @JvmName("setAccessibilityNameProperty")
        set(value) = setAccessibilityName(value)

    var accessibilityDescription: String
        @JvmName("accessibilityDescriptionProperty")
        get() = getAccessibilityDescription()
        @JvmName("setAccessibilityDescriptionProperty")
        set(value) = setAccessibilityDescription(value)

    var theme: Theme?
        @JvmName("themeProperty")
        get() = getTheme()
        @JvmName("setThemeProperty")
        set(value) = setTheme(value)

    var themeTypeVariation: String
        @JvmName("themeTypeVariationProperty")
        get() = getThemeTypeVariation()
        @JvmName("setThemeTypeVariationProperty")
        set(value) = setThemeTypeVariation(value)

    /**
     * The window's title. If the `Window` is native, title styles set in `Theme` will have no effect.
     *
     * Generated from Godot docs: Window.set_title
     */
    fun setTitle(title: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setTitleBind, segment, title)
    }

    /**
     * The window's title. If the `Window` is native, title styles set in `Theme` will have no effect.
     *
     * Generated from Godot docs: Window.get_title
     */
    fun getTitle(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getTitleBind, segment)
    }

    /**
     * Specifies the initial type of position for the `Window`.
     *
     * Generated from Godot docs: Window.set_initial_position
     */
    fun setInitialPosition(initialPosition: Window.WindowInitialPosition) {
        ObjectCalls.ptrcallWithLongArg(Binds.setInitialPositionBind, segment, initialPosition.value)
    }

    /**
     * Specifies the initial type of position for the `Window`.
     *
     * Generated from Godot docs: Window.get_initial_position
     */
    fun getInitialPosition(): Window.WindowInitialPosition {
        return Window.WindowInitialPosition(ObjectCalls.ptrcallNoArgsRetLong(Binds.getInitialPositionBind, segment))
    }

    /**
     * The screen the window is currently on.
     *
     * Generated from Godot docs: Window.set_current_screen
     */
    fun setCurrentScreen(index: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setCurrentScreenBind, segment, index)
    }

    /**
     * The screen the window is currently on.
     *
     * Generated from Godot docs: Window.get_current_screen
     */
    fun getCurrentScreen(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getCurrentScreenBind, segment)
    }

    /**
     * The window's position in pixels. If `ProjectSettings.display/window/subwindows/embed_subwindows`
     * is `false`, the position is in absolute screen coordinates. This typically applies to editor
     * plugins. If the setting is `true`, the window's position is in the coordinates of its parent
     * `Viewport`. Note: This property only works if `initial_position` is set to
     * `WindowInitialPosition.ABSOLUTE`.
     *
     * Generated from Godot docs: Window.set_position
     */
    fun setPosition(position: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(Binds.setPositionBind, segment, position)
    }

    /**
     * The window's position in pixels. If `ProjectSettings.display/window/subwindows/embed_subwindows`
     * is `false`, the position is in absolute screen coordinates. This typically applies to editor
     * plugins. If the setting is `true`, the window's position is in the coordinates of its parent
     * `Viewport`. Note: This property only works if `initial_position` is set to
     * `WindowInitialPosition.ABSOLUTE`.
     *
     * Generated from Godot docs: Window.get_position
     */
    fun getPosition(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(Binds.getPositionBind, segment)
    }

    /**
     * Centers the window in the current screen. If the window is embedded, it is centered in the
     * embedder `Viewport` instead.
     *
     * Generated from Godot docs: Window.move_to_center
     */
    fun moveToCenter() {
        ObjectCalls.ptrcallNoArgs(Binds.moveToCenterBind, segment)
    }

    /**
     * The window's size in pixels. See also `content_scale_size`, which doesn't set the window's
     * physical size but affects how scaling works relative to the current `content_scale_mode`.
     *
     * Generated from Godot docs: Window.set_size
     */
    fun setSize(size: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(Binds.setSizeBind, segment, size)
    }

    /**
     * The window's size in pixels. See also `content_scale_size`, which doesn't set the window's
     * physical size but affects how scaling works relative to the current `content_scale_mode`.
     *
     * Generated from Godot docs: Window.get_size
     */
    fun getSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(Binds.getSizeBind, segment)
    }

    /**
     * Resets the size to the minimum size, which is the max of `min_size` and (if `wrap_controls` is
     * enabled) `get_contents_minimum_size`. This is equivalent to calling `set_size(Vector2i())` (or
     * any size below the minimum).
     *
     * Generated from Godot docs: Window.reset_size
     */
    fun resetSize() {
        ObjectCalls.ptrcallNoArgs(Binds.resetSizeBind, segment)
    }

    /**
     * Returns the window's position including its border. Note: If `visible` is `false`, this method
     * returns the same value as `position`.
     *
     * Generated from Godot docs: Window.get_position_with_decorations
     */
    fun getPositionWithDecorations(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(Binds.getPositionWithDecorationsBind, segment)
    }

    /**
     * Returns the window's size including its border. Note: If `visible` is `false`, this method
     * returns the same value as `size`.
     *
     * Generated from Godot docs: Window.get_size_with_decorations
     */
    fun getSizeWithDecorations(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(Binds.getSizeWithDecorationsBind, segment)
    }

    /**
     * If non-zero, the `Window` can't be resized to be bigger than this size. Note: This property will
     * be ignored if the value is lower than `min_size`.
     *
     * Generated from Godot docs: Window.set_max_size
     */
    fun setMaxSize(maxSize: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(Binds.setMaxSizeBind, segment, maxSize)
    }

    /**
     * If non-zero, the `Window` can't be resized to be bigger than this size. Note: This property will
     * be ignored if the value is lower than `min_size`.
     *
     * Generated from Godot docs: Window.get_max_size
     */
    fun getMaxSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(Binds.getMaxSizeBind, segment)
    }

    /**
     * If non-zero, the `Window` can't be resized to be smaller than this size. Note: This property
     * will be ignored in favor of `get_contents_minimum_size` if `wrap_controls` is enabled and if its
     * size is bigger.
     *
     * Generated from Godot docs: Window.set_min_size
     */
    fun setMinSize(minSize: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(Binds.setMinSizeBind, segment, minSize)
    }

    /**
     * If non-zero, the `Window` can't be resized to be smaller than this size. Note: This property
     * will be ignored in favor of `get_contents_minimum_size` if `wrap_controls` is enabled and if its
     * size is bigger.
     *
     * Generated from Godot docs: Window.get_min_size
     */
    fun getMinSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(Binds.getMinSizeBind, segment)
    }

    /**
     * Set's the window's current mode. Note: Fullscreen mode is not exclusive full screen on Windows
     * and Linux. Note: This method only works with native windows, i.e. the main window and
     * `Window`-derived nodes when `Viewport.gui_embed_subwindows` is disabled in the main viewport.
     *
     * Generated from Godot docs: Window.set_mode
     */
    fun setMode(mode: Window.Mode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setModeBind, segment, mode.value)
    }

    /**
     * Set's the window's current mode. Note: Fullscreen mode is not exclusive full screen on Windows
     * and Linux. Note: This method only works with native windows, i.e. the main window and
     * `Window`-derived nodes when `Viewport.gui_embed_subwindows` is disabled in the main viewport.
     *
     * Generated from Godot docs: Window.get_mode
     */
    fun getMode(): Window.Mode {
        return Window.Mode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getModeBind, segment))
    }

    /**
     * If `true`, the window can't be resized.
     *
     * Generated from Godot docs: Window.set_flag
     */
    fun setFlag(flag: Window.Flags, enabled: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(Binds.setFlagBind, segment, flag.value, enabled)
    }

    /**
     * If `true`, the window can't be resized.
     *
     * Generated from Godot docs: Window.get_flag
     */
    fun getFlag(flag: Window.Flags): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(Binds.getFlagBind, segment, flag.value)
    }

    /**
     * If `true`, requests HDR output for the `Window`, falling back to SDR if not supported, and
     * automatically switching between HDR and SDR as the window moves between screens, screen
     * capabilities change, or system settings are modified. This will internally force
     * `Viewport.use_hdr_2d` to be enabled on the main `Viewport`. All other `SubViewport` of this
     * `Window` must have their `Viewport.use_hdr_2d` property enabled to produce HDR output.
     *
     * Generated from Godot docs: Window.set_hdr_output_requested
     */
    fun setHdrOutputRequested(requested: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setHdrOutputRequestedBind, segment, requested)
    }

    /**
     * If `true`, requests HDR output for the `Window`, falling back to SDR if not supported, and
     * automatically switching between HDR and SDR as the window moves between screens, screen
     * capabilities change, or system settings are modified. This will internally force
     * `Viewport.use_hdr_2d` to be enabled on the main `Viewport`. All other `SubViewport` of this
     * `Window` must have their `Viewport.use_hdr_2d` property enabled to produce HDR output.
     *
     * Generated from Godot docs: Window.is_hdr_output_requested
     */
    fun isHdrOutputRequested(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isHdrOutputRequestedBind, segment)
    }

    /**
     * Returns the maximum value for linear color components that can be displayed in this window,
     * regardless of SDR or HDR output. Returns `1.0` if HDR is not enabled or not supported. The
     * `output_max_linear_value_changed` signal will be emitted whenever this value changes. This value
     * is used by tonemapping and other `Environment` effects to ensure that bright colors are
     * presented in the range that can be displayed by this window. When using this maximum linear
     * value in your project, it should only be used to present colors directly to the screen without
     * tonemapping and without influencing lighting, post-processing effects, or surrounding color.
     *
     * Generated from Godot docs: Window.get_output_max_linear_value
     */
    fun getOutputMaxLinearValue(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getOutputMaxLinearValueBind, segment)
    }

    /**
     * Returns `true` if the window can be maximized (the maximize button is enabled).
     *
     * Generated from Godot docs: Window.is_maximize_allowed
     */
    fun isMaximizeAllowed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isMaximizeAllowedBind, segment)
    }

    /**
     * Tells the OS that the `Window` needs an attention. This makes the window stand out in some way
     * depending on the system, e.g. it might blink on the task bar.
     *
     * Generated from Godot docs: Window.request_attention
     */
    fun requestAttention() {
        ObjectCalls.ptrcallNoArgs(Binds.requestAttentionBind, segment)
    }

    /**
     * Creates a progress bar on the taskbar/dock icon of the `Window` if it does not exist, sets the
     * progress of the icon. `value` acts as a relative percentage value, ranges from `0.0` (lowest) to
     * `1.0` (highest). Note: This method is implemented only on Windows and macOS.
     *
     * Generated from Godot docs: Window.set_taskbar_progress_value
     */
    fun setTaskbarProgressValue(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setTaskbarProgressValueBind, segment, value)
    }

    /**
     * Sets the type and state of the progress bar on the taskbar/dock icon of the `Window`. See
     * `DisplayServer.ProgressState` for possible values and how each mode behaves. Note: This method
     * is implemented only on Windows and macOS.
     *
     * Generated from Godot docs: Window.set_taskbar_progress_state
     */
    fun setTaskbarProgressState(state: DisplayServer.ProgressState) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTaskbarProgressStateBind, segment, state.value)
    }

    /**
     * Causes the window to grab focus, allowing it to receive user input.
     *
     * Generated from Godot docs: Window.move_to_foreground
     */
    fun moveToForeground() {
        ObjectCalls.ptrcallNoArgs(Binds.moveToForegroundBind, segment)
    }

    /**
     * If `true`, the window is visible.
     *
     * Generated from Godot docs: Window.set_visible
     */
    fun setVisible(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setVisibleBind, segment, visible)
    }

    /**
     * If `true`, the window is visible.
     *
     * Generated from Godot docs: Window.is_visible
     */
    fun isVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isVisibleBind, segment)
    }

    /**
     * Hides the window. This is not the same as minimized state. Hidden window can't be interacted
     * with and needs to be made visible with `show`.
     *
     * Generated from Godot docs: Window.hide
     */
    fun hide() {
        ObjectCalls.ptrcallNoArgs(Binds.hideBind, segment)
    }

    /**
     * Makes the `Window` appear. This enables interactions with the `Window` and doesn't change any of
     * its property other than visibility (unlike e.g. `popup`).
     *
     * Generated from Godot docs: Window.show
     */
    fun show() {
        ObjectCalls.ptrcallNoArgs(Binds.showBind, segment)
    }

    /**
     * If `true`, the `Window` is transient, i.e. it's considered a child of another `Window`. The
     * transient window will be destroyed with its transient parent and will return focus to their
     * parent when closed. The transient window is displayed on top of a non-exclusive full-screen
     * parent window. Transient windows can't enter full-screen mode. Note that behavior might be
     * different depending on the platform.
     *
     * Generated from Godot docs: Window.set_transient
     */
    fun setTransient(transient: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setTransientBind, segment, transient)
    }

    /**
     * If `true`, the `Window` is transient, i.e. it's considered a child of another `Window`. The
     * transient window will be destroyed with its transient parent and will return focus to their
     * parent when closed. The transient window is displayed on top of a non-exclusive full-screen
     * parent window. Transient windows can't enter full-screen mode. Note that behavior might be
     * different depending on the platform.
     *
     * Generated from Godot docs: Window.is_transient
     */
    fun isTransient(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isTransientBind, segment)
    }

    /**
     * If `true`, and the `Window` is `transient`, this window will (at the time of becoming visible)
     * become transient to the currently focused window instead of the immediate parent window in the
     * hierarchy. Note that the transient parent is assigned at the time this window becomes visible,
     * so changing it afterwards has no effect until re-shown.
     *
     * Generated from Godot docs: Window.set_transient_to_focused
     */
    fun setTransientToFocused(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setTransientToFocusedBind, segment, enable)
    }

    /**
     * If `true`, and the `Window` is `transient`, this window will (at the time of becoming visible)
     * become transient to the currently focused window instead of the immediate parent window in the
     * hierarchy. Note that the transient parent is assigned at the time this window becomes visible,
     * so changing it afterwards has no effect until re-shown.
     *
     * Generated from Godot docs: Window.is_transient_to_focused
     */
    fun isTransientToFocused(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isTransientToFocusedBind, segment)
    }

    /**
     * If `true`, the `Window` will be in exclusive mode. Exclusive windows are always on top of their
     * parent and will block all input going to the parent `Window`. Needs `transient` enabled to work.
     *
     * Generated from Godot docs: Window.set_exclusive
     */
    fun setExclusive(exclusive: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setExclusiveBind, segment, exclusive)
    }

    /**
     * If `true`, the `Window` will be in exclusive mode. Exclusive windows are always on top of their
     * parent and will block all input going to the parent `Window`. Needs `transient` enabled to work.
     *
     * Generated from Godot docs: Window.is_exclusive
     */
    fun isExclusive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isExclusiveBind, segment)
    }

    /**
     * If `unparent` is `true`, the window is automatically unparented when going invisible. Note: Make
     * sure to keep a reference to the node, otherwise it will be orphaned. You also need to manually
     * call `Node.queue_free` to free the window if it's not parented.
     *
     * Generated from Godot docs: Window.set_unparent_when_invisible
     */
    fun setUnparentWhenInvisible(unparent: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUnparentWhenInvisibleBind, segment, unparent)
    }

    /**
     * Returns whether the window is being drawn to the screen.
     *
     * Generated from Godot docs: Window.can_draw
     */
    fun canDraw(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.canDrawBind, segment)
    }

    /**
     * Returns `true` if the window is focused.
     *
     * Generated from Godot docs: Window.has_focus
     */
    fun hasFocus(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasFocusBind, segment)
    }

    /**
     * Causes the window to grab focus, allowing it to receive user input.
     *
     * Generated from Godot docs: Window.grab_focus
     */
    fun grabFocus() {
        ObjectCalls.ptrcallNoArgs(Binds.grabFocusBind, segment)
    }

    /**
     * Starts an interactive drag operation on the window, using the current mouse position. Call this
     * method when handling a mouse button being pressed to simulate a pressed event on the window's
     * title bar. Using this method allows the window to participate in space switching, tiling, and
     * other system features.
     *
     * Generated from Godot docs: Window.start_drag
     */
    fun startDrag() {
        ObjectCalls.ptrcallNoArgs(Binds.startDragBind, segment)
    }

    /**
     * Starts an interactive resize operation on the window, using the current mouse position. Call
     * this method when handling a mouse button being pressed to simulate a pressed event on the
     * window's edge.
     *
     * Generated from Godot docs: Window.start_resize
     */
    fun startResize(edge: DisplayServer.WindowResizeEdge) {
        ObjectCalls.ptrcallWithLongArg(Binds.startResizeBind, segment, edge.value)
    }

    /**
     * If `active` is `true`, enables system's native IME (Input Method Editor).
     *
     * Generated from Godot docs: Window.set_ime_active
     */
    fun setImeActive(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setImeActiveBind, segment, active)
    }

    /**
     * Moves IME to the given position.
     *
     * Generated from Godot docs: Window.set_ime_position
     */
    fun setImePosition(position: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(Binds.setImePositionBind, segment, position)
    }

    /**
     * Returns `true` if the window is currently embedded in another window.
     *
     * Generated from Godot docs: Window.is_embedded
     */
    fun isEmbedded(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEmbeddedBind, segment)
    }

    /**
     * Returns the combined minimum size from the child `Control` nodes of the window. Use
     * `child_controls_changed` to update it when child nodes have changed. The value returned by this
     * method can be overridden with `_get_contents_minimum_size`.
     *
     * Generated from Godot docs: Window.get_contents_minimum_size
     */
    fun getContentsMinimumSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getContentsMinimumSizeBind, segment)
    }

    /**
     * If `true`, native window will be used regardless of parent viewport and project settings.
     *
     * Generated from Godot docs: Window.set_force_native
     */
    fun setForceNative(forceNative: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setForceNativeBind, segment, forceNative)
    }

    /**
     * If `true`, native window will be used regardless of parent viewport and project settings.
     *
     * Generated from Godot docs: Window.get_force_native
     */
    fun getForceNative(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getForceNativeBind, segment)
    }

    /**
     * The content's base size in "virtual" pixels. Not to be confused with `size`, which sets the
     * actual window's physical size in pixels. If set to a value greater than `0` and
     * `content_scale_mode` is set to a value other than `ContentScaleMode.DISABLED`, the `Window`'s
     * content will be scaled when the window is resized to a different size. Higher values will make
     * the content appear smaller, as it will be able to fit more of the project in view. On the root
     * `Window`, this is set to match `ProjectSettings.display/window/size/viewport_width` and
     * `ProjectSettings.display/window/size/viewport_height` by default. For example, when using
     * `ContentScaleMode.CANVAS_ITEMS` and `content_scale_size` set to `Vector2i(1280, 720)`, using a
     * window size of `2560×1440` will make 2D elements appear at double their original size, as the
     * content is scaled by a factor of `2.0` (`2560.0 / 1280.0 = 2.0`, `1440.0 / 720.0 = 2.0`). See
     * the Base size section of the Multiple resolutions documentation
     * ($DOCS_URL/tutorials/rendering/multiple_resolutions.html#base-size) for details.
     *
     * Generated from Godot docs: Window.set_content_scale_size
     */
    fun setContentScaleSize(size: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(Binds.setContentScaleSizeBind, segment, size)
    }

    /**
     * The content's base size in "virtual" pixels. Not to be confused with `size`, which sets the
     * actual window's physical size in pixels. If set to a value greater than `0` and
     * `content_scale_mode` is set to a value other than `ContentScaleMode.DISABLED`, the `Window`'s
     * content will be scaled when the window is resized to a different size. Higher values will make
     * the content appear smaller, as it will be able to fit more of the project in view. On the root
     * `Window`, this is set to match `ProjectSettings.display/window/size/viewport_width` and
     * `ProjectSettings.display/window/size/viewport_height` by default. For example, when using
     * `ContentScaleMode.CANVAS_ITEMS` and `content_scale_size` set to `Vector2i(1280, 720)`, using a
     * window size of `2560×1440` will make 2D elements appear at double their original size, as the
     * content is scaled by a factor of `2.0` (`2560.0 / 1280.0 = 2.0`, `1440.0 / 720.0 = 2.0`). See
     * the Base size section of the Multiple resolutions documentation
     * ($DOCS_URL/tutorials/rendering/multiple_resolutions.html#base-size) for details.
     *
     * Generated from Godot docs: Window.get_content_scale_size
     */
    fun getContentScaleSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(Binds.getContentScaleSizeBind, segment)
    }

    /**
     * Specifies how the content is scaled when the `Window` is resized.
     *
     * Generated from Godot docs: Window.set_content_scale_mode
     */
    fun setContentScaleMode(mode: Window.ContentScaleMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setContentScaleModeBind, segment, mode.value)
    }

    /**
     * Specifies how the content is scaled when the `Window` is resized.
     *
     * Generated from Godot docs: Window.get_content_scale_mode
     */
    fun getContentScaleMode(): Window.ContentScaleMode {
        return Window.ContentScaleMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getContentScaleModeBind, segment))
    }

    /**
     * Specifies how the content's aspect behaves when the `Window` is resized. The base aspect is
     * determined by `content_scale_size`.
     *
     * Generated from Godot docs: Window.set_content_scale_aspect
     */
    fun setContentScaleAspect(aspect: Window.ContentScaleAspect) {
        ObjectCalls.ptrcallWithLongArg(Binds.setContentScaleAspectBind, segment, aspect.value)
    }

    /**
     * Specifies how the content's aspect behaves when the `Window` is resized. The base aspect is
     * determined by `content_scale_size`.
     *
     * Generated from Godot docs: Window.get_content_scale_aspect
     */
    fun getContentScaleAspect(): Window.ContentScaleAspect {
        return Window.ContentScaleAspect(ObjectCalls.ptrcallNoArgsRetLong(Binds.getContentScaleAspectBind, segment))
    }

    /**
     * The policy to use to determine the final scale factor for 2D elements. This affects how
     * `content_scale_factor` is applied, in addition to the automatic scale factor determined by
     * `content_scale_size`.
     *
     * Generated from Godot docs: Window.set_content_scale_stretch
     */
    fun setContentScaleStretch(stretch: Window.ContentScaleStretch) {
        ObjectCalls.ptrcallWithLongArg(Binds.setContentScaleStretchBind, segment, stretch.value)
    }

    /**
     * The policy to use to determine the final scale factor for 2D elements. This affects how
     * `content_scale_factor` is applied, in addition to the automatic scale factor determined by
     * `content_scale_size`.
     *
     * Generated from Godot docs: Window.get_content_scale_stretch
     */
    fun getContentScaleStretch(): Window.ContentScaleStretch {
        return Window.ContentScaleStretch(ObjectCalls.ptrcallNoArgsRetLong(Binds.getContentScaleStretchBind, segment))
    }

    /**
     * If set, defines the window's custom decoration area which will receive mouse input, even if
     * normal input to the window is blocked (such as when it has an exclusive child opened). See also
     * `nonclient_window_input`.
     *
     * Generated from Godot docs: Window.set_nonclient_area
     */
    fun setNonclientArea(area: Rect2i) {
        ObjectCalls.ptrcallWithRect2iArg(Binds.setNonclientAreaBind, segment, area)
    }

    /**
     * If set, defines the window's custom decoration area which will receive mouse input, even if
     * normal input to the window is blocked (such as when it has an exclusive child opened). See also
     * `nonclient_window_input`.
     *
     * Generated from Godot docs: Window.get_nonclient_area
     */
    fun getNonclientArea(): Rect2i {
        return ObjectCalls.ptrcallNoArgsRetRect2i(Binds.getNonclientAreaBind, segment)
    }

    /**
     * If `true`, the `Window` width is expanded to keep the title bar text fully visible.
     *
     * Generated from Godot docs: Window.set_keep_title_visible
     */
    fun setKeepTitleVisible(titleVisible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setKeepTitleVisibleBind, segment, titleVisible)
    }

    /**
     * If `true`, the `Window` width is expanded to keep the title bar text fully visible.
     *
     * Generated from Godot docs: Window.get_keep_title_visible
     */
    fun getKeepTitleVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getKeepTitleVisibleBind, segment)
    }

    /**
     * Specifies the base scale of `Window`'s content when its `size` is equal to `content_scale_size`.
     * See also `Viewport.get_stretch_transform`.
     *
     * Generated from Godot docs: Window.set_content_scale_factor
     */
    fun setContentScaleFactor(factor: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setContentScaleFactorBind, segment, factor)
    }

    /**
     * Specifies the base scale of `Window`'s content when its `size` is equal to `content_scale_size`.
     * See also `Viewport.get_stretch_transform`.
     *
     * Generated from Godot docs: Window.get_content_scale_factor
     */
    fun getContentScaleFactor(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getContentScaleFactorBind, segment)
    }

    /**
     * Sets a polygonal region of the window which accepts mouse events. Mouse events outside the
     * region will be passed through. Passing an empty array will disable passthrough support (all
     * mouse events will be intercepted by the window, which is the default behavior).
     *
     * Generated from Godot docs: Window.set_mouse_passthrough_polygon
     */
    fun setMousePassthroughPolygon(polygon: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(Binds.setMousePassthroughPolygonBind, segment, polygon)
    }

    /**
     * Sets a polygonal region of the window which accepts mouse events. Mouse events outside the
     * region will be passed through. Passing an empty array will disable passthrough support (all
     * mouse events will be intercepted by the window, which is the default behavior).
     *
     * Generated from Godot docs: Window.get_mouse_passthrough_polygon
     */
    fun getMousePassthroughPolygon(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(Binds.getMousePassthroughPolygonBind, segment)
    }

    /**
     * If `true`, the window's size will automatically update when a child node is added or removed,
     * ignoring `min_size` if the new size is bigger. If `false`, you need to call
     * `child_controls_changed` manually.
     *
     * Generated from Godot docs: Window.set_wrap_controls
     */
    fun setWrapControls(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setWrapControlsBind, segment, enable)
    }

    /**
     * If `true`, the window's size will automatically update when a child node is added or removed,
     * ignoring `min_size` if the new size is bigger. If `false`, you need to call
     * `child_controls_changed` manually.
     *
     * Generated from Godot docs: Window.is_wrapping_controls
     */
    fun isWrappingControls(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isWrappingControlsBind, segment)
    }

    /**
     * Requests an update of the `Window` size to fit underlying `Control` nodes.
     *
     * Generated from Godot docs: Window.child_controls_changed
     */
    fun childControlsChanged() {
        ObjectCalls.ptrcallNoArgs(Binds.childControlsChangedBind, segment)
    }

    /**
     * The `Theme` resource this node and all its `Control` and `Window` children use. If a child node
     * has its own `Theme` resource set, theme items are merged with child's definitions having higher
     * priority. Note: `Window` styles will have no effect unless the window is embedded.
     *
     * Generated from Godot docs: Window.set_theme
     */
    fun setTheme(theme: Theme?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setThemeBind, segment, listOf(theme?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `Theme` resource this node and all its `Control` and `Window` children use. If a child node
     * has its own `Theme` resource set, theme items are merged with child's definitions having higher
     * priority. Note: `Window` styles will have no effect unless the window is embedded.
     *
     * Generated from Godot docs: Window.get_theme
     */
    fun getTheme(): Theme? {
        return Theme.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getThemeBind, segment))
    }

    /**
     * The name of a theme type variation used by this `Window` to look up its own theme items. See
     * `Control.theme_type_variation` for more details.
     *
     * Generated from Godot docs: Window.set_theme_type_variation
     */
    fun setThemeTypeVariation(themeType: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.setThemeTypeVariationBind, segment, themeType)
    }

    /**
     * The name of a theme type variation used by this `Window` to look up its own theme items. See
     * `Control.theme_type_variation` for more details.
     *
     * Generated from Godot docs: Window.get_theme_type_variation
     */
    fun getThemeTypeVariation(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(Binds.getThemeTypeVariationBind, segment)
    }

    /**
     * Prevents `*_theme_*_override` methods from emitting `NOTIFICATION_THEME_CHANGED` until
     * `end_bulk_theme_override` is called.
     *
     * Generated from Godot docs: Window.begin_bulk_theme_override
     */
    fun beginBulkThemeOverride() {
        ObjectCalls.ptrcallNoArgs(Binds.beginBulkThemeOverrideBind, segment)
    }

    /**
     * Ends a bulk theme override update. See `begin_bulk_theme_override`.
     *
     * Generated from Godot docs: Window.end_bulk_theme_override
     */
    fun endBulkThemeOverride() {
        ObjectCalls.ptrcallNoArgs(Binds.endBulkThemeOverrideBind, segment)
    }

    /**
     * Creates a local override for a theme icon with the specified `name`. Local overrides always take
     * precedence when fetching theme items for the control. An override can be removed with
     * `remove_theme_icon_override`. See also `get_theme_icon`.
     *
     * Generated from Godot docs: Window.add_theme_icon_override
     */
    fun addThemeIconOverride(name: String, texture: Texture2D?) {
        ObjectCalls.ptrcallWithStringNameAndObjectArg(Binds.addThemeIconOverrideBind, segment, name, texture?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Creates a local override for a theme `StyleBox` with the specified `name`. Local overrides
     * always take precedence when fetching theme items for the control. An override can be removed
     * with `remove_theme_stylebox_override`. See also `get_theme_stylebox` and
     * `Control.add_theme_stylebox_override` for more details.
     *
     * Generated from Godot docs: Window.add_theme_stylebox_override
     */
    fun addThemeStyleboxOverride(name: String, stylebox: StyleBox?) {
        ObjectCalls.ptrcallWithStringNameAndObjectArg(Binds.addThemeStyleboxOverrideBind, segment, name, stylebox?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Creates a local override for a theme `Font` with the specified `name`. Local overrides always
     * take precedence when fetching theme items for the control. An override can be removed with
     * `remove_theme_font_override`. See also `get_theme_font`.
     *
     * Generated from Godot docs: Window.add_theme_font_override
     */
    fun addThemeFontOverride(name: String, font: Font?) {
        ObjectCalls.ptrcallWithStringNameAndObjectArg(Binds.addThemeFontOverrideBind, segment, name, font?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Creates a local override for a theme font size with the specified `name`. Local overrides always
     * take precedence when fetching theme items for the control. An override can be removed with
     * `remove_theme_font_size_override`. See also `get_theme_font_size`.
     *
     * Generated from Godot docs: Window.add_theme_font_size_override
     */
    fun addThemeFontSizeOverride(name: String, fontSize: Int) {
        ObjectCalls.ptrcallWithStringNameAndIntArg(Binds.addThemeFontSizeOverrideBind, segment, name, fontSize)
    }

    /**
     * Creates a local override for a theme `Color` with the specified `name`. Local overrides always
     * take precedence when fetching theme items for the control. An override can be removed with
     * `remove_theme_color_override`. See also `get_theme_color` and `Control.add_theme_color_override`
     * for more details.
     *
     * Generated from Godot docs: Window.add_theme_color_override
     */
    fun addThemeColorOverride(name: String, color: Color) {
        ObjectCalls.ptrcallWithStringNameAndColorArg(Binds.addThemeColorOverrideBind, segment, name, color)
    }

    /**
     * Creates a local override for a theme constant with the specified `name`. Local overrides always
     * take precedence when fetching theme items for the control. An override can be removed with
     * `remove_theme_constant_override`. See also `get_theme_constant`.
     *
     * Generated from Godot docs: Window.add_theme_constant_override
     */
    fun addThemeConstantOverride(name: String, constant: Int) {
        ObjectCalls.ptrcallWithStringNameAndIntArg(Binds.addThemeConstantOverrideBind, segment, name, constant)
    }

    /**
     * Removes a local override for a theme icon with the specified `name` previously added by
     * `add_theme_icon_override` or via the Inspector dock.
     *
     * Generated from Godot docs: Window.remove_theme_icon_override
     */
    fun removeThemeIconOverride(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.removeThemeIconOverrideBind, segment, name)
    }

    /**
     * Removes a local override for a theme `StyleBox` with the specified `name` previously added by
     * `add_theme_stylebox_override` or via the Inspector dock.
     *
     * Generated from Godot docs: Window.remove_theme_stylebox_override
     */
    fun removeThemeStyleboxOverride(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.removeThemeStyleboxOverrideBind, segment, name)
    }

    /**
     * Removes a local override for a theme `Font` with the specified `name` previously added by
     * `add_theme_font_override` or via the Inspector dock.
     *
     * Generated from Godot docs: Window.remove_theme_font_override
     */
    fun removeThemeFontOverride(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.removeThemeFontOverrideBind, segment, name)
    }

    /**
     * Removes a local override for a theme font size with the specified `name` previously added by
     * `add_theme_font_size_override` or via the Inspector dock.
     *
     * Generated from Godot docs: Window.remove_theme_font_size_override
     */
    fun removeThemeFontSizeOverride(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.removeThemeFontSizeOverrideBind, segment, name)
    }

    /**
     * Removes a local override for a theme `Color` with the specified `name` previously added by
     * `add_theme_color_override` or via the Inspector dock.
     *
     * Generated from Godot docs: Window.remove_theme_color_override
     */
    fun removeThemeColorOverride(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.removeThemeColorOverrideBind, segment, name)
    }

    /**
     * Removes a local override for a theme constant with the specified `name` previously added by
     * `add_theme_constant_override` or via the Inspector dock.
     *
     * Generated from Godot docs: Window.remove_theme_constant_override
     */
    fun removeThemeConstantOverride(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.removeThemeConstantOverrideBind, segment, name)
    }

    /**
     * Returns an icon from the first matching `Theme` in the tree if that `Theme` has an icon item
     * with the specified `name` and `theme_type`. See `Control.get_theme_color` for details.
     *
     * Generated from Godot docs: Window.get_theme_icon
     */
    fun getThemeIcon(name: String, themeType: String = ""): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallWithTwoStringNameArgsRetObject(Binds.getThemeIconBind, segment, name, themeType))
    }

    /**
     * Returns a `StyleBox` from the first matching `Theme` in the tree if that `Theme` has a stylebox
     * item with the specified `name` and `theme_type`. See `Control.get_theme_color` for details.
     *
     * Generated from Godot docs: Window.get_theme_stylebox
     */
    fun getThemeStylebox(name: String, themeType: String = ""): StyleBox? {
        return StyleBox.wrapOwned(ObjectCalls.ptrcallWithTwoStringNameArgsRetObject(Binds.getThemeStyleboxBind, segment, name, themeType))
    }

    /**
     * Returns a `Font` from the first matching `Theme` in the tree if that `Theme` has a font item
     * with the specified `name` and `theme_type`. See `Control.get_theme_color` for details.
     *
     * Generated from Godot docs: Window.get_theme_font
     */
    fun getThemeFont(name: String, themeType: String = ""): Font? {
        return Font.wrapOwned(ObjectCalls.ptrcallWithTwoStringNameArgsRetObject(Binds.getThemeFontBind, segment, name, themeType))
    }

    /**
     * Returns a font size from the first matching `Theme` in the tree if that `Theme` has a font size
     * item with the specified `name` and `theme_type`. See `Control.get_theme_color` for details.
     *
     * Generated from Godot docs: Window.get_theme_font_size
     */
    fun getThemeFontSize(name: String, themeType: String = ""): Int {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetInt(Binds.getThemeFontSizeBind, segment, name, themeType)
    }

    /**
     * Returns a `Color` from the first matching `Theme` in the tree if that `Theme` has a color item
     * with the specified `name` and `theme_type`. See `Control.get_theme_color` for more details.
     *
     * Generated from Godot docs: Window.get_theme_color
     */
    fun getThemeColor(name: String, themeType: String = ""): Color {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetColor(Binds.getThemeColorBind, segment, name, themeType)
    }

    /**
     * Returns a constant from the first matching `Theme` in the tree if that `Theme` has a constant
     * item with the specified `name` and `theme_type`. See `Control.get_theme_color` for more details.
     *
     * Generated from Godot docs: Window.get_theme_constant
     */
    fun getThemeConstant(name: String, themeType: String = ""): Int {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetInt(Binds.getThemeConstantBind, segment, name, themeType)
    }

    /**
     * Returns `true` if there is a local override for a theme icon with the specified `name` in this
     * `Control` node. See `add_theme_icon_override`.
     *
     * Generated from Godot docs: Window.has_theme_icon_override
     */
    fun hasThemeIconOverride(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.hasThemeIconOverrideBind, segment, name)
    }

    /**
     * Returns `true` if there is a local override for a theme `StyleBox` with the specified `name` in
     * this `Control` node. See `add_theme_stylebox_override`.
     *
     * Generated from Godot docs: Window.has_theme_stylebox_override
     */
    fun hasThemeStyleboxOverride(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.hasThemeStyleboxOverrideBind, segment, name)
    }

    /**
     * Returns `true` if there is a local override for a theme `Font` with the specified `name` in this
     * `Control` node. See `add_theme_font_override`.
     *
     * Generated from Godot docs: Window.has_theme_font_override
     */
    fun hasThemeFontOverride(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.hasThemeFontOverrideBind, segment, name)
    }

    /**
     * Returns `true` if there is a local override for a theme font size with the specified `name` in
     * this `Control` node. See `add_theme_font_size_override`.
     *
     * Generated from Godot docs: Window.has_theme_font_size_override
     */
    fun hasThemeFontSizeOverride(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.hasThemeFontSizeOverrideBind, segment, name)
    }

    /**
     * Returns `true` if there is a local override for a theme `Color` with the specified `name` in
     * this `Control` node. See `add_theme_color_override`.
     *
     * Generated from Godot docs: Window.has_theme_color_override
     */
    fun hasThemeColorOverride(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.hasThemeColorOverrideBind, segment, name)
    }

    /**
     * Returns `true` if there is a local override for a theme constant with the specified `name` in
     * this `Control` node. See `add_theme_constant_override`.
     *
     * Generated from Godot docs: Window.has_theme_constant_override
     */
    fun hasThemeConstantOverride(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.hasThemeConstantOverrideBind, segment, name)
    }

    /**
     * Returns `true` if there is a matching `Theme` in the tree that has an icon item with the
     * specified `name` and `theme_type`. See `Control.get_theme_color` for details.
     *
     * Generated from Godot docs: Window.has_theme_icon
     */
    fun hasThemeIcon(name: String, themeType: String = ""): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(Binds.hasThemeIconBind, segment, name, themeType)
    }

    /**
     * Returns `true` if there is a matching `Theme` in the tree that has a stylebox item with the
     * specified `name` and `theme_type`. See `Control.get_theme_color` for details.
     *
     * Generated from Godot docs: Window.has_theme_stylebox
     */
    fun hasThemeStylebox(name: String, themeType: String = ""): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(Binds.hasThemeStyleboxBind, segment, name, themeType)
    }

    /**
     * Returns `true` if there is a matching `Theme` in the tree that has a font item with the
     * specified `name` and `theme_type`. See `Control.get_theme_color` for details.
     *
     * Generated from Godot docs: Window.has_theme_font
     */
    fun hasThemeFont(name: String, themeType: String = ""): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(Binds.hasThemeFontBind, segment, name, themeType)
    }

    /**
     * Returns `true` if there is a matching `Theme` in the tree that has a font size item with the
     * specified `name` and `theme_type`. See `Control.get_theme_color` for details.
     *
     * Generated from Godot docs: Window.has_theme_font_size
     */
    fun hasThemeFontSize(name: String, themeType: String = ""): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(Binds.hasThemeFontSizeBind, segment, name, themeType)
    }

    /**
     * Returns `true` if there is a matching `Theme` in the tree that has a color item with the
     * specified `name` and `theme_type`. See `Control.get_theme_color` for details.
     *
     * Generated from Godot docs: Window.has_theme_color
     */
    fun hasThemeColor(name: String, themeType: String = ""): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(Binds.hasThemeColorBind, segment, name, themeType)
    }

    /**
     * Returns `true` if there is a matching `Theme` in the tree that has a constant item with the
     * specified `name` and `theme_type`. See `Control.get_theme_color` for details.
     *
     * Generated from Godot docs: Window.has_theme_constant
     */
    fun hasThemeConstant(name: String, themeType: String = ""): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(Binds.hasThemeConstantBind, segment, name, themeType)
    }

    /**
     * Returns the default base scale value from the first matching `Theme` in the tree if that `Theme`
     * has a valid `Theme.default_base_scale` value. See `Control.get_theme_color` for details.
     *
     * Generated from Godot docs: Window.get_theme_default_base_scale
     */
    fun getThemeDefaultBaseScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getThemeDefaultBaseScaleBind, segment)
    }

    /**
     * Returns the default font from the first matching `Theme` in the tree if that `Theme` has a valid
     * `Theme.default_font` value. See `Control.get_theme_color` for details.
     *
     * Generated from Godot docs: Window.get_theme_default_font
     */
    fun getThemeDefaultFont(): Font? {
        return Font.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getThemeDefaultFontBind, segment))
    }

    /**
     * Returns the default font size value from the first matching `Theme` in the tree if that `Theme`
     * has a valid `Theme.default_font_size` value. See `Control.get_theme_color` for details.
     *
     * Generated from Godot docs: Window.get_theme_default_font_size
     */
    fun getThemeDefaultFontSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getThemeDefaultFontSizeBind, segment)
    }

    /**
     * Returns the ID of the window.
     *
     * Generated from Godot docs: Window.get_window_id
     */
    fun getWindowId(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getWindowIdBind, segment)
    }

    /**
     * The human-readable node name that is reported to assistive apps.
     *
     * Generated from Godot docs: Window.set_accessibility_name
     */
    fun setAccessibilityName(name: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setAccessibilityNameBind, segment, name)
    }

    /**
     * The human-readable node name that is reported to assistive apps.
     *
     * Generated from Godot docs: Window.get_accessibility_name
     */
    fun getAccessibilityName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getAccessibilityNameBind, segment)
    }

    /**
     * The human-readable node description that is reported to assistive apps.
     *
     * Generated from Godot docs: Window.set_accessibility_description
     */
    fun setAccessibilityDescription(description: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setAccessibilityDescriptionBind, segment, description)
    }

    /**
     * The human-readable node description that is reported to assistive apps.
     *
     * Generated from Godot docs: Window.get_accessibility_description
     */
    fun getAccessibilityDescription(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getAccessibilityDescriptionBind, segment)
    }

    /**
     * Sets layout direction and text writing direction. Right-to-left layouts are necessary for
     * certain languages (e.g. Arabic and Hebrew).
     *
     * Generated from Godot docs: Window.set_layout_direction
     */
    fun setLayoutDirection(direction: Window.LayoutDirection) {
        ObjectCalls.ptrcallWithLongArg(Binds.setLayoutDirectionBind, segment, direction.value)
    }

    /**
     * Returns layout direction and text writing direction.
     *
     * Generated from Godot docs: Window.get_layout_direction
     */
    fun getLayoutDirection(): Window.LayoutDirection {
        return Window.LayoutDirection(ObjectCalls.ptrcallNoArgsRetLong(Binds.getLayoutDirectionBind, segment))
    }

    /**
     * Returns `true` if the layout is right-to-left.
     *
     * Generated from Godot docs: Window.is_layout_rtl
     */
    fun isLayoutRtl(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isLayoutRtlBind, segment)
    }

    /**
     * Toggles if any text should automatically change to its translated version depending on the
     * current locale.
     *
     * Generated from Godot docs: Window.set_auto_translate
     */
    fun setAutoTranslate(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAutoTranslateBind, segment, enable)
    }

    /**
     * Toggles if any text should automatically change to its translated version depending on the
     * current locale.
     *
     * Generated from Godot docs: Window.is_auto_translating
     */
    fun isAutoTranslating(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAutoTranslatingBind, segment)
    }

    /**
     * Enables font oversampling. This makes fonts look better when they are scaled up.
     *
     * Generated from Godot docs: Window.set_use_font_oversampling
     */
    fun setUseFontOversampling(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseFontOversamplingBind, segment, enable)
    }

    /**
     * Returns `true` if font oversampling is enabled. See `set_use_font_oversampling`.
     *
     * Generated from Godot docs: Window.is_using_font_oversampling
     */
    fun isUsingFontOversampling(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUsingFontOversamplingBind, segment)
    }

    /**
     * Shows the `Window` and makes it transient (see `transient`). If `rect` is provided, it will be
     * set as the `Window`'s size. Fails if called on the main window. If
     * `ProjectSettings.display/window/subwindows/embed_subwindows` is `true` (single-window mode),
     * `rect`'s coordinates are global and relative to the main window's top-left corner (excluding
     * window decorations). If `rect`'s position coordinates are negative, the window will be located
     * outside the main window and may not be visible as a result. If
     * `ProjectSettings.display/window/subwindows/embed_subwindows` is `false` (multi-window mode),
     * `rect`'s coordinates are global and relative to the top-left corner of the leftmost screen. If
     * `rect`'s position coordinates are negative, the window will be placed at the top-left corner of
     * the screen. Note: `rect` must be in global coordinates if specified.
     *
     * Generated from Godot docs: Window.popup
     */
    fun popup(rect: Rect2i) {
        ObjectCalls.ptrcallWithRect2iArg(Binds.popupBind, segment, rect)
    }

    /**
     * Popups the `Window` with a position shifted by parent `Window`'s position. If the `Window` is
     * embedded, has the same effect as `popup`.
     *
     * Generated from Godot docs: Window.popup_on_parent
     */
    fun popupOnParent(parentRect: Rect2i) {
        ObjectCalls.ptrcallWithRect2iArg(Binds.popupOnParentBind, segment, parentRect)
    }

    /**
     * Popups the `Window` at the center of the current screen, with optionally given minimum size. If
     * the `Window` is embedded, it will be centered in the parent `Viewport` instead. Note: Calling it
     * with the default value of `minsize` is equivalent to calling it with `size`.
     *
     * Generated from Godot docs: Window.popup_centered
     */
    fun popupCentered(minsize: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(Binds.popupCenteredBind, segment, minsize)
    }

    /**
     * If `Window` is embedded, popups the `Window` centered inside its embedder and sets its size as a
     * `ratio` of embedder's size. If `Window` is a native window, popups the `Window` centered inside
     * the screen of its parent `Window` and sets its size as a `ratio` of the screen size.
     *
     * Generated from Godot docs: Window.popup_centered_ratio
     */
    fun popupCenteredRatio(ratio: Double = 0.8) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.popupCenteredRatioBind, segment, ratio)
    }

    /**
     * Popups the `Window` centered inside its parent `Window`. `fallback_ratio` determines the maximum
     * size of the `Window`, in relation to its parent. Note: Calling it with the default value of
     * `minsize` is equivalent to calling it with `size`.
     *
     * Generated from Godot docs: Window.popup_centered_clamped
     */
    fun popupCenteredClamped(minsize: Vector2i, fallbackRatio: Double = 0.75) {
        ObjectCalls.ptrcallWithVector2iAndDoubleArg(Binds.popupCenteredClampedBind, segment, minsize, fallbackRatio)
    }

    /**
     * Attempts to parent this dialog to the last exclusive window relative to `from_node`, and then
     * calls `Window.popup` on it. The dialog must have no current parent, otherwise the method fails.
     * See also `set_unparent_when_invisible` and `Node.get_last_exclusive_window`.
     *
     * Generated from Godot docs: Window.popup_exclusive
     */
    fun popupExclusive(fromNode: Node, rect: Rect2i) {
        ObjectCalls.ptrcallWithObjectAndRect2iArg(Binds.popupExclusiveBind, segment, fromNode.segment, rect)
    }

    /**
     * Attempts to parent this dialog to the last exclusive window relative to `from_node`, and then
     * calls `Window.popup_on_parent` on it. The dialog must have no current parent, otherwise the
     * method fails. See also `set_unparent_when_invisible` and `Node.get_last_exclusive_window`.
     *
     * Generated from Godot docs: Window.popup_exclusive_on_parent
     */
    fun popupExclusiveOnParent(fromNode: Node, parentRect: Rect2i) {
        ObjectCalls.ptrcallWithObjectAndRect2iArg(Binds.popupExclusiveOnParentBind, segment, fromNode.segment, parentRect)
    }

    /**
     * Attempts to parent this dialog to the last exclusive window relative to `from_node`, and then
     * calls `Window.popup_centered` on it. The dialog must have no current parent, otherwise the
     * method fails. See also `set_unparent_when_invisible` and `Node.get_last_exclusive_window`.
     *
     * Generated from Godot docs: Window.popup_exclusive_centered
     */
    fun popupExclusiveCentered(fromNode: Node, minsize: Vector2i) {
        ObjectCalls.ptrcallWithObjectAndVector2iArg(Binds.popupExclusiveCenteredBind, segment, fromNode.segment, minsize)
    }

    /**
     * Attempts to parent this dialog to the last exclusive window relative to `from_node`, and then
     * calls `Window.popup_centered_ratio` on it. The dialog must have no current parent, otherwise the
     * method fails. See also `set_unparent_when_invisible` and `Node.get_last_exclusive_window`.
     *
     * Generated from Godot docs: Window.popup_exclusive_centered_ratio
     */
    fun popupExclusiveCenteredRatio(fromNode: Node, ratio: Double = 0.8) {
        ObjectCalls.ptrcallWithObjectAndDoubleArg(Binds.popupExclusiveCenteredRatioBind, segment, fromNode.segment, ratio)
    }

    /**
     * Attempts to parent this dialog to the last exclusive window relative to `from_node`, and then
     * calls `Window.popup_centered_clamped` on it. The dialog must have no current parent, otherwise
     * the method fails. See also `set_unparent_when_invisible` and `Node.get_last_exclusive_window`.
     *
     * Generated from Godot docs: Window.popup_exclusive_centered_clamped
     */
    fun popupExclusiveCenteredClamped(fromNode: Node, minsize: Vector2i, fallbackRatio: Double = 0.75) {
        ObjectCalls.ptrcallWithObjectVector2iAndDoubleArg(Binds.popupExclusiveCenteredClampedBind, segment, fromNode.segment, minsize, fallbackRatio)
    }

    /** Signal `window_input(event: InputEvent)`; see [TypedSignal]. */
    val windowInput: Signal1<InputEvent>
        @JvmName("windowInputTypedSignal")
        get() = Signal1(this, "window_input", SignalArgType.objectOf("InputEvent") { InputEvent(it) })

    /** Signal `nonclient_window_input(event: InputEvent)`; see [TypedSignal]. */
    val nonclientWindowInput: Signal1<InputEvent>
        @JvmName("nonclientWindowInputTypedSignal")
        get() = Signal1(this, "nonclient_window_input", SignalArgType.objectOf("InputEvent") { InputEvent(it) })

    /** Signal `files_dropped(files: PackedStringArray)`; see [TypedSignal]. On iOS a PackedStringArray argument is not delivered yet: a connection reports a script error. */
    val filesDropped: Signal1<List<String>>
        @JvmName("filesDroppedTypedSignal")
        get() = Signal1(this, "files_dropped", SignalArgType.valueOf<List<String>>("PackedStringArray", List::class))

    /** Signal `mouse_entered()`; see [TypedSignal]. */
    val mouseEntered: Signal0
        @JvmName("mouseEnteredTypedSignal")
        get() = Signal0(this, "mouse_entered")

    /** Signal `mouse_exited()`; see [TypedSignal]. */
    val mouseExited: Signal0
        @JvmName("mouseExitedTypedSignal")
        get() = Signal0(this, "mouse_exited")

    /** Signal `focus_entered()`; see [TypedSignal]. */
    val focusEntered: Signal0
        @JvmName("focusEnteredTypedSignal")
        get() = Signal0(this, "focus_entered")

    /** Signal `focus_exited()`; see [TypedSignal]. */
    val focusExited: Signal0
        @JvmName("focusExitedTypedSignal")
        get() = Signal0(this, "focus_exited")

    /** Signal `close_requested()`; see [TypedSignal]. */
    val closeRequested: Signal0
        @JvmName("closeRequestedTypedSignal")
        get() = Signal0(this, "close_requested")

    /** Signal `go_back_requested()`; see [TypedSignal]. */
    val goBackRequested: Signal0
        @JvmName("goBackRequestedTypedSignal")
        get() = Signal0(this, "go_back_requested")

    /** Signal `visibility_changed()`; see [TypedSignal]. */
    val visibilityChanged: Signal0
        @JvmName("visibilityChangedTypedSignal")
        get() = Signal0(this, "visibility_changed")

    /** Signal `about_to_popup()`; see [TypedSignal]. */
    val aboutToPopup: Signal0
        @JvmName("aboutToPopupTypedSignal")
        get() = Signal0(this, "about_to_popup")

    /** Signal `theme_changed()`; see [TypedSignal]. */
    val themeChanged: Signal0
        @JvmName("themeChangedTypedSignal")
        get() = Signal0(this, "theme_changed")

    /** Signal `dpi_changed()`; see [TypedSignal]. */
    val dpiChanged: Signal0
        @JvmName("dpiChangedTypedSignal")
        get() = Signal0(this, "dpi_changed")

    /** Signal `titlebar_changed()`; see [TypedSignal]. */
    val titlebarChanged: Signal0
        @JvmName("titlebarChangedTypedSignal")
        get() = Signal0(this, "titlebar_changed")

    /** Signal `title_changed()`; see [TypedSignal]. */
    val titleChanged: Signal0
        @JvmName("titleChangedTypedSignal")
        get() = Signal0(this, "title_changed")

    /** Signal `output_max_linear_value_changed(output_max_linear_value: float)`; see [TypedSignal]. */
    val outputMaxLinearValueChanged: Signal1<Double>
        @JvmName("outputMaxLinearValueChangedTypedSignal")
        get() = Signal1(this, "output_max_linear_value_changed", SignalArgType.DOUBLE)

    object Signals {
        const val windowInput: String = "window_input"
        const val nonclientWindowInput: String = "nonclient_window_input"
        const val filesDropped: String = "files_dropped"
        const val mouseEntered: String = "mouse_entered"
        const val mouseExited: String = "mouse_exited"
        const val focusEntered: String = "focus_entered"
        const val focusExited: String = "focus_exited"
        const val closeRequested: String = "close_requested"
        const val goBackRequested: String = "go_back_requested"
        const val visibilityChanged: String = "visibility_changed"
        const val aboutToPopup: String = "about_to_popup"
        const val themeChanged: String = "theme_changed"
        const val dpiChanged: String = "dpi_changed"
        const val titlebarChanged: String = "titlebar_changed"
        const val titleChanged: String = "title_changed"
        const val outputMaxLinearValueChanged: String = "output_max_linear_value_changed"
    }

    /**
     * Godot's `Window.Mode` enum as a typed value: `.value` is the raw number Godot uses, and the
     * companion holds the named values (`Window.Mode.<NAME>`).
     *
     * Generated from Godot docs: Window.Mode
     */
    @JvmInline
    value class Mode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Windowed mode, i.e. `Window` doesn't occupy the whole screen (unless set to the size of the
             * screen).
             *
             * Generated from Godot docs: Window.MODE_WINDOWED
             */
            val WINDOWED: Mode get() = Mode(0L)
            /**
             * Minimized window mode, i.e. `Window` is not visible and available on window manager's window
             * list. Normally happens when the minimize button is pressed.
             *
             * Generated from Godot docs: Window.MODE_MINIMIZED
             */
            val MINIMIZED: Mode get() = Mode(1L)
            /**
             * Maximized window mode, i.e. `Window` will occupy whole screen area except task bar and still
             * display its borders. Normally happens when the maximize button is pressed.
             *
             * Generated from Godot docs: Window.MODE_MAXIMIZED
             */
            val MAXIMIZED: Mode get() = Mode(2L)
            /**
             * Full screen mode with full multi-window support. Full screen window covers the entire display
             * area of a screen and has no decorations. The display's video mode is not changed. On Android:
             * This enables immersive mode. On macOS: A new desktop is used to display the running project.
             * Note: Regardless of the platform, enabling full screen will change the window size to match the
             * monitor's size. Therefore, make sure your project supports multiple resolutions
             * ($DOCS_URL/tutorials/rendering/multiple_resolutions.html) when enabling full screen mode.
             *
             * Generated from Godot docs: Window.MODE_FULLSCREEN
             */
            val FULLSCREEN: Mode get() = Mode(3L)
            /**
             * A single window full screen mode. This mode has less overhead, but only one window can be open
             * on a given screen at a time (opening a child window or application switching will trigger a full
             * screen transition). Full screen window covers the entire display area of a screen and has no
             * border or decorations. The display's video mode is not changed. Note: This mode might not work
             * with screen recording software. On Android: This enables immersive mode. On Windows: Depending
             * on video driver, full screen transition might cause screens to go black for a moment. On macOS:
             * A new desktop is used to display the running project. Exclusive full screen mode prevents Dock
             * and Menu from showing up when the mouse pointer is hovering the edge of the screen. On Linux
             * (X11): Exclusive full screen mode bypasses compositor. On Linux (Wayland): Equivalent to
             * `Mode.FULLSCREEN`. Note: Regardless of the platform, enabling full screen will change the window
             * size to match the monitor's size. Therefore, make sure your project supports multiple
             * resolutions ($DOCS_URL/tutorials/rendering/multiple_resolutions.html) when enabling full screen
             * mode.
             *
             * Generated from Godot docs: Window.MODE_EXCLUSIVE_FULLSCREEN
             */
            val EXCLUSIVE_FULLSCREEN: Mode get() = Mode(4L)
        }
    }

    /**
     * Godot's `Window.Flags` enum as a typed value: `.value` is the raw number Godot uses, and the
     * companion holds the named values (`Window.Flags.<NAME>`).
     *
     * Generated from Godot docs: Window.Flags
     */
    @JvmInline
    value class Flags(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The window can't be resized by dragging its resize grip. It's still possible to resize the
             * window using `size`. This flag is ignored for full screen windows. Set with `unresizable`. Note:
             * This flag is implemented on Linux (X11), macOS, Windows, and embedded windows.
             *
             * Generated from Godot docs: Window.FLAG_RESIZE_DISABLED
             */
            val RESIZE_DISABLED: Flags get() = Flags(0L)
            /**
             * The window do not have native title bar and other decorations. This flag is ignored for
             * full-screen windows. Set with `borderless`. Note: This flag is implemented on Linux
             * (X11/Wayland), macOS, Windows, and embedded windows.
             *
             * Generated from Godot docs: Window.FLAG_BORDERLESS
             */
            val BORDERLESS: Flags get() = Flags(1L)
            /**
             * The window is floating on top of all other windows. This flag is ignored for full-screen
             * windows. Set with `always_on_top`. Note: This flag is implemented on Linux (X11), macOS,
             * Windows, and embedded windows.
             *
             * Generated from Godot docs: Window.FLAG_ALWAYS_ON_TOP
             */
            val ALWAYS_ON_TOP: Flags get() = Flags(2L)
            /**
             * The window background can be transparent. Set with `transparent`. Note: This flag has no effect
             * if either `ProjectSettings.display/window/per_pixel_transparency/allowed`, or the window's
             * `Viewport.transparent_bg` is set to `false`. Note: Transparency support is implemented on Linux
             * (X11/Wayland), macOS, Windows, and embedded windows.
             *
             * Generated from Godot docs: Window.FLAG_TRANSPARENT
             */
            val TRANSPARENT: Flags get() = Flags(3L)
            /**
             * The window can't be focused. No-focus window will ignore all input, except mouse clicks. Set
             * with `unfocusable`. Note: This flag is implemented on Linux (X11), macOS, Windows, and embedded
             * windows.
             *
             * Generated from Godot docs: Window.FLAG_NO_FOCUS
             */
            val NO_FOCUS: Flags get() = Flags(4L)
            /**
             * Window is part of menu or `OptionButton` dropdown. This flag can't be changed when the window is
             * visible. An active popup window will exclusively receive all input, without stealing focus from
             * its parent. Popup windows are automatically closed when uses click outside it, or when an
             * application is switched. Popup window must have transient parent set (see `transient`). Note:
             * This flag is implemented on Linux (X11/Wayland), macOS, Windows, and embedded `Popup` windows.
             *
             * Generated from Godot docs: Window.FLAG_POPUP
             */
            val POPUP: Flags get() = Flags(5L)
            /**
             * Window content is expanded to the full size of the window. Unlike borderless window, the frame
             * is left intact and can be used to resize the window, title bar is transparent, but have
             * minimize/maximize/close buttons. Set with `extend_to_title`. Note: This flag has no effect in
             * embedded windows. Note: This flag is implemented only on macOS.
             *
             * Generated from Godot docs: Window.FLAG_EXTEND_TO_TITLE
             */
            val EXTEND_TO_TITLE: Flags get() = Flags(6L)
            /**
             * All mouse events are passed to the underlying window of the same application. Note: This flag
             * has no effect in embedded windows. Note: This flag is implemented on Linux (X11), macOS,
             * Windows.
             *
             * Generated from Godot docs: Window.FLAG_MOUSE_PASSTHROUGH
             */
            val MOUSE_PASSTHROUGH: Flags get() = Flags(7L)
            /**
             * Window style is overridden, forcing sharp corners. Note: This flag has no effect in embedded
             * windows. Note: This flag is implemented only on Windows (11).
             *
             * Generated from Godot docs: Window.FLAG_SHARP_CORNERS
             */
            val SHARP_CORNERS: Flags get() = Flags(8L)
            /**
             * Windows is excluded from screenshots taken by `DisplayServer.screen_get_image`,
             * `DisplayServer.screen_get_image_rect`, and `DisplayServer.screen_get_pixel`. Note: This flag has
             * no effect in embedded windows. Note: This flag is implemented on macOS and Windows (10, 20H1).
             * Note: Setting this flag will prevent standard screenshot methods from capturing a window image,
             * but does NOT guarantee that other apps won't be able to capture an image. It should not be used
             * as a DRM or security measure.
             *
             * Generated from Godot docs: Window.FLAG_EXCLUDE_FROM_CAPTURE
             */
            val EXCLUDE_FROM_CAPTURE: Flags get() = Flags(9L)
            /**
             * Signals the window manager that this window is supposed to be an implementation-defined "popup"
             * (usually a floating, borderless, untileable and immovable child window). Note: This flag has no
             * effect in embedded windows. Note: This flag is implemented on Linux (Wayland).
             *
             * Generated from Godot docs: Window.FLAG_POPUP_WM_HINT
             */
            val POPUP_WM_HINT: Flags get() = Flags(10L)
            /**
             * Window minimize button is disabled. Note: This flag has no effect in embedded windows. Note:
             * This flag is implemented on Linux (X11), macOS, and Windows.
             *
             * Generated from Godot docs: Window.FLAG_MINIMIZE_DISABLED
             */
            val MINIMIZE_DISABLED: Flags get() = Flags(11L)
            /**
             * Window maximize button is disabled. Note: This flag has no effect in embedded windows. Note:
             * This flag is implemented on Linux (X11), macOS, and Windows.
             *
             * Generated from Godot docs: Window.FLAG_MAXIMIZE_DISABLED
             */
            val MAXIMIZE_DISABLED: Flags get() = Flags(12L)
            /**
             * Max value of the `Flags`.
             *
             * Generated from Godot docs: Window.FLAG_MAX
             */
            val MAX: Flags get() = Flags(13L)
        }
    }

    /**
     * Godot's `Window.ContentScaleMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`Window.ContentScaleMode.<NAME>`).
     *
     * Generated from Godot docs: Window.ContentScaleMode
     */
    @JvmInline
    value class ContentScaleMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The content will not be scaled to match the `Window`'s size (`content_scale_size` is ignored).
             *
             * Generated from Godot docs: Window.CONTENT_SCALE_MODE_DISABLED
             */
            val DISABLED: ContentScaleMode get() = ContentScaleMode(0L)
            /**
             * The content will be rendered at the target size. This is more performance-expensive than
             * `ContentScaleMode.VIEWPORT`, but provides better results.
             *
             * Generated from Godot docs: Window.CONTENT_SCALE_MODE_CANVAS_ITEMS
             */
            val CANVAS_ITEMS: ContentScaleMode get() = ContentScaleMode(1L)
            /**
             * The content will be rendered at the base size and then scaled to the target size. More
             * performant than `ContentScaleMode.CANVAS_ITEMS`, but results in pixelated image.
             *
             * Generated from Godot docs: Window.CONTENT_SCALE_MODE_VIEWPORT
             */
            val VIEWPORT: ContentScaleMode get() = ContentScaleMode(2L)
        }
    }

    /**
     * Godot's `Window.ContentScaleAspect` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`Window.ContentScaleAspect.<NAME>`).
     *
     * Generated from Godot docs: Window.ContentScaleAspect
     */
    @JvmInline
    value class ContentScaleAspect(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The aspect will be ignored. Scaling will simply stretch the content to fit the target size.
             *
             * Generated from Godot docs: Window.CONTENT_SCALE_ASPECT_IGNORE
             */
            val IGNORE: ContentScaleAspect get() = ContentScaleAspect(0L)
            /**
             * The content's aspect will be preserved. If the target size has different aspect from the base
             * one, the image will be centered and black bars will appear on left and right sides.
             *
             * Generated from Godot docs: Window.CONTENT_SCALE_ASPECT_KEEP
             */
            val KEEP: ContentScaleAspect get() = ContentScaleAspect(1L)
            /**
             * The content can be expanded vertically. Scaling horizontally will result in keeping the width
             * ratio and then black bars on left and right sides.
             *
             * Generated from Godot docs: Window.CONTENT_SCALE_ASPECT_KEEP_WIDTH
             */
            val KEEP_WIDTH: ContentScaleAspect get() = ContentScaleAspect(2L)
            /**
             * The content can be expanded horizontally. Scaling vertically will result in keeping the height
             * ratio and then black bars on top and bottom sides.
             *
             * Generated from Godot docs: Window.CONTENT_SCALE_ASPECT_KEEP_HEIGHT
             */
            val KEEP_HEIGHT: ContentScaleAspect get() = ContentScaleAspect(3L)
            /**
             * The content's aspect will be preserved. If the target size has different aspect from the base
             * one, the content will stay in the top-left corner and add an extra visible area in the stretched
             * space.
             *
             * Generated from Godot docs: Window.CONTENT_SCALE_ASPECT_EXPAND
             */
            val EXPAND: ContentScaleAspect get() = ContentScaleAspect(4L)
        }
    }

    /**
     * Godot's `Window.ContentScaleStretch` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`Window.ContentScaleStretch.<NAME>`).
     *
     * Generated from Godot docs: Window.ContentScaleStretch
     */
    @JvmInline
    value class ContentScaleStretch(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The content will be stretched according to a fractional factor. This fills all the space
             * available in the window, but allows "pixel wobble" to occur due to uneven pixel scaling.
             *
             * Generated from Godot docs: Window.CONTENT_SCALE_STRETCH_FRACTIONAL
             */
            val FRACTIONAL: ContentScaleStretch get() = ContentScaleStretch(0L)
            /**
             * The content will be stretched only according to an integer factor, preserving sharp pixels. This
             * may leave a black background visible on the window's edges depending on the window size.
             *
             * Generated from Godot docs: Window.CONTENT_SCALE_STRETCH_INTEGER
             */
            val INTEGER: ContentScaleStretch get() = ContentScaleStretch(1L)
        }
    }

    /**
     * Godot's `Window.LayoutDirection` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`Window.LayoutDirection.<NAME>`).
     *
     * Generated from Godot docs: Window.LayoutDirection
     */
    @JvmInline
    value class LayoutDirection(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Automatic layout direction, determined from the parent window layout direction.
             *
             * Generated from Godot docs: Window.LAYOUT_DIRECTION_INHERITED
             */
            val INHERITED: LayoutDirection get() = LayoutDirection(0L)
            /**
             * Automatic layout direction, determined from the current locale.
             *
             * Generated from Godot docs: Window.LAYOUT_DIRECTION_APPLICATION_LOCALE
             */
            val APPLICATION_LOCALE: LayoutDirection get() = LayoutDirection(1L)
            /**
             * Left-to-right layout direction.
             *
             * Generated from Godot docs: Window.LAYOUT_DIRECTION_LTR
             */
            val LTR: LayoutDirection get() = LayoutDirection(2L)
            /**
             * Right-to-left layout direction.
             *
             * Generated from Godot docs: Window.LAYOUT_DIRECTION_RTL
             */
            val RTL: LayoutDirection get() = LayoutDirection(3L)
            /**
             * Automatic layout direction, determined from the system locale.
             *
             * Generated from Godot docs: Window.LAYOUT_DIRECTION_SYSTEM_LOCALE
             */
            val SYSTEM_LOCALE: LayoutDirection get() = LayoutDirection(4L)
            /**
             * Represents the size of the `LayoutDirection` enum.
             *
             * Generated from Godot docs: Window.LAYOUT_DIRECTION_MAX
             */
            val MAX: LayoutDirection get() = LayoutDirection(5L)
            val LOCALE: LayoutDirection get() = LayoutDirection(1L)
        }
    }

    /**
     * Godot's `Window.WindowInitialPosition` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`Window.WindowInitialPosition.<NAME>`).
     *
     * Generated from Godot docs: Window.WindowInitialPosition
     */
    @JvmInline
    value class WindowInitialPosition(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Initial window position is determined by `position`.
             *
             * Generated from Godot docs: Window.WINDOW_INITIAL_POSITION_ABSOLUTE
             */
            val ABSOLUTE: WindowInitialPosition get() = WindowInitialPosition(0L)
            /**
             * Initial window position is the center of the primary screen.
             *
             * Generated from Godot docs: Window.WINDOW_INITIAL_POSITION_CENTER_PRIMARY_SCREEN
             */
            val CENTER_PRIMARY_SCREEN: WindowInitialPosition get() = WindowInitialPosition(1L)
            /**
             * Initial window position is the center of the main window screen.
             *
             * Generated from Godot docs: Window.WINDOW_INITIAL_POSITION_CENTER_MAIN_WINDOW_SCREEN
             */
            val CENTER_MAIN_WINDOW_SCREEN: WindowInitialPosition get() = WindowInitialPosition(2L)
            /**
             * Initial window position is the center of `current_screen` screen.
             *
             * Generated from Godot docs: Window.WINDOW_INITIAL_POSITION_CENTER_OTHER_SCREEN
             */
            val CENTER_OTHER_SCREEN: WindowInitialPosition get() = WindowInitialPosition(3L)
            /**
             * Initial window position is the center of the screen containing the mouse pointer.
             *
             * Generated from Godot docs: Window.WINDOW_INITIAL_POSITION_CENTER_SCREEN_WITH_MOUSE_FOCUS
             */
            val CENTER_SCREEN_WITH_MOUSE_FOCUS: WindowInitialPosition get() = WindowInitialPosition(4L)
            /**
             * Initial window position is the center of the screen containing the window with the keyboard
             * focus.
             *
             * Generated from Godot docs: Window.WINDOW_INITIAL_POSITION_CENTER_SCREEN_WITH_KEYBOARD_FOCUS
             */
            val CENTER_SCREEN_WITH_KEYBOARD_FOCUS: WindowInitialPosition get() = WindowInitialPosition(5L)
        }
    }

    companion object {
        /**
         * Returns the focused window.
         *
         * Generated from Godot docs: Window.get_focused_window
         */
        fun getFocusedWindow(): Window? {
            return Window.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getFocusedWindowBind, NULL_SEGMENT))
        }

        const val NOTIFICATION_VISIBILITY_CHANGED: Long = 30L
        const val NOTIFICATION_THEME_CHANGED: Long = 32L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): Window? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Window? =
            if (handle.address() == 0L) null else Window(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_TITLE_HASH = 83702148L
        @JvmField
        val setTitleBind =
            ObjectCalls.getMethodBind("Window", "set_title", SET_TITLE_HASH)

        private const val GET_TITLE_HASH = 201670096L
        @JvmField
        val getTitleBind =
            ObjectCalls.getMethodBind("Window", "get_title", GET_TITLE_HASH)

        private const val SET_INITIAL_POSITION_HASH = 4084468099L
        @JvmField
        val setInitialPositionBind =
            ObjectCalls.getMethodBind("Window", "set_initial_position", SET_INITIAL_POSITION_HASH)

        private const val GET_INITIAL_POSITION_HASH = 4294066647L
        @JvmField
        val getInitialPositionBind =
            ObjectCalls.getMethodBind("Window", "get_initial_position", GET_INITIAL_POSITION_HASH)

        private const val SET_CURRENT_SCREEN_HASH = 1286410249L
        @JvmField
        val setCurrentScreenBind =
            ObjectCalls.getMethodBind("Window", "set_current_screen", SET_CURRENT_SCREEN_HASH)

        private const val GET_CURRENT_SCREEN_HASH = 3905245786L
        @JvmField
        val getCurrentScreenBind =
            ObjectCalls.getMethodBind("Window", "get_current_screen", GET_CURRENT_SCREEN_HASH)

        private const val SET_POSITION_HASH = 1130785943L
        @JvmField
        val setPositionBind =
            ObjectCalls.getMethodBind("Window", "set_position", SET_POSITION_HASH)

        private const val GET_POSITION_HASH = 3690982128L
        @JvmField
        val getPositionBind =
            ObjectCalls.getMethodBind("Window", "get_position", GET_POSITION_HASH)

        private const val MOVE_TO_CENTER_HASH = 3218959716L
        @JvmField
        val moveToCenterBind =
            ObjectCalls.getMethodBind("Window", "move_to_center", MOVE_TO_CENTER_HASH)

        private const val SET_SIZE_HASH = 1130785943L
        @JvmField
        val setSizeBind =
            ObjectCalls.getMethodBind("Window", "set_size", SET_SIZE_HASH)

        private const val GET_SIZE_HASH = 3690982128L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("Window", "get_size", GET_SIZE_HASH)

        private const val RESET_SIZE_HASH = 3218959716L
        @JvmField
        val resetSizeBind =
            ObjectCalls.getMethodBind("Window", "reset_size", RESET_SIZE_HASH)

        private const val GET_POSITION_WITH_DECORATIONS_HASH = 3690982128L
        @JvmField
        val getPositionWithDecorationsBind =
            ObjectCalls.getMethodBind("Window", "get_position_with_decorations", GET_POSITION_WITH_DECORATIONS_HASH)

        private const val GET_SIZE_WITH_DECORATIONS_HASH = 3690982128L
        @JvmField
        val getSizeWithDecorationsBind =
            ObjectCalls.getMethodBind("Window", "get_size_with_decorations", GET_SIZE_WITH_DECORATIONS_HASH)

        private const val SET_MAX_SIZE_HASH = 1130785943L
        @JvmField
        val setMaxSizeBind =
            ObjectCalls.getMethodBind("Window", "set_max_size", SET_MAX_SIZE_HASH)

        private const val GET_MAX_SIZE_HASH = 3690982128L
        @JvmField
        val getMaxSizeBind =
            ObjectCalls.getMethodBind("Window", "get_max_size", GET_MAX_SIZE_HASH)

        private const val SET_MIN_SIZE_HASH = 1130785943L
        @JvmField
        val setMinSizeBind =
            ObjectCalls.getMethodBind("Window", "set_min_size", SET_MIN_SIZE_HASH)

        private const val GET_MIN_SIZE_HASH = 3690982128L
        @JvmField
        val getMinSizeBind =
            ObjectCalls.getMethodBind("Window", "get_min_size", GET_MIN_SIZE_HASH)

        private const val SET_MODE_HASH = 3095236531L
        @JvmField
        val setModeBind =
            ObjectCalls.getMethodBind("Window", "set_mode", SET_MODE_HASH)

        private const val GET_MODE_HASH = 2566346114L
        @JvmField
        val getModeBind =
            ObjectCalls.getMethodBind("Window", "get_mode", GET_MODE_HASH)

        private const val SET_FLAG_HASH = 3426449779L
        @JvmField
        val setFlagBind =
            ObjectCalls.getMethodBind("Window", "set_flag", SET_FLAG_HASH)

        private const val GET_FLAG_HASH = 3062752289L
        @JvmField
        val getFlagBind =
            ObjectCalls.getMethodBind("Window", "get_flag", GET_FLAG_HASH)

        private const val SET_HDR_OUTPUT_REQUESTED_HASH = 2586408642L
        @JvmField
        val setHdrOutputRequestedBind =
            ObjectCalls.getMethodBind("Window", "set_hdr_output_requested", SET_HDR_OUTPUT_REQUESTED_HASH)

        private const val IS_HDR_OUTPUT_REQUESTED_HASH = 36873697L
        @JvmField
        val isHdrOutputRequestedBind =
            ObjectCalls.getMethodBind("Window", "is_hdr_output_requested", IS_HDR_OUTPUT_REQUESTED_HASH)

        private const val GET_OUTPUT_MAX_LINEAR_VALUE_HASH = 1740695150L
        @JvmField
        val getOutputMaxLinearValueBind =
            ObjectCalls.getMethodBind("Window", "get_output_max_linear_value", GET_OUTPUT_MAX_LINEAR_VALUE_HASH)

        private const val IS_MAXIMIZE_ALLOWED_HASH = 36873697L
        @JvmField
        val isMaximizeAllowedBind =
            ObjectCalls.getMethodBind("Window", "is_maximize_allowed", IS_MAXIMIZE_ALLOWED_HASH)

        private const val REQUEST_ATTENTION_HASH = 3218959716L
        @JvmField
        val requestAttentionBind =
            ObjectCalls.getMethodBind("Window", "request_attention", REQUEST_ATTENTION_HASH)

        private const val SET_TASKBAR_PROGRESS_VALUE_HASH = 373806689L
        @JvmField
        val setTaskbarProgressValueBind =
            ObjectCalls.getMethodBind("Window", "set_taskbar_progress_value", SET_TASKBAR_PROGRESS_VALUE_HASH)

        private const val SET_TASKBAR_PROGRESS_STATE_HASH = 824071031L
        @JvmField
        val setTaskbarProgressStateBind =
            ObjectCalls.getMethodBind("Window", "set_taskbar_progress_state", SET_TASKBAR_PROGRESS_STATE_HASH)

        private const val MOVE_TO_FOREGROUND_HASH = 3218959716L
        @JvmField
        val moveToForegroundBind =
            ObjectCalls.getMethodBind("Window", "move_to_foreground", MOVE_TO_FOREGROUND_HASH)

        private const val SET_VISIBLE_HASH = 2586408642L
        @JvmField
        val setVisibleBind =
            ObjectCalls.getMethodBind("Window", "set_visible", SET_VISIBLE_HASH)

        private const val IS_VISIBLE_HASH = 36873697L
        @JvmField
        val isVisibleBind =
            ObjectCalls.getMethodBind("Window", "is_visible", IS_VISIBLE_HASH)

        private const val HIDE_HASH = 3218959716L
        @JvmField
        val hideBind =
            ObjectCalls.getMethodBind("Window", "hide", HIDE_HASH)

        private const val SHOW_HASH = 3218959716L
        @JvmField
        val showBind =
            ObjectCalls.getMethodBind("Window", "show", SHOW_HASH)

        private const val SET_TRANSIENT_HASH = 2586408642L
        @JvmField
        val setTransientBind =
            ObjectCalls.getMethodBind("Window", "set_transient", SET_TRANSIENT_HASH)

        private const val IS_TRANSIENT_HASH = 36873697L
        @JvmField
        val isTransientBind =
            ObjectCalls.getMethodBind("Window", "is_transient", IS_TRANSIENT_HASH)

        private const val SET_TRANSIENT_TO_FOCUSED_HASH = 2586408642L
        @JvmField
        val setTransientToFocusedBind =
            ObjectCalls.getMethodBind("Window", "set_transient_to_focused", SET_TRANSIENT_TO_FOCUSED_HASH)

        private const val IS_TRANSIENT_TO_FOCUSED_HASH = 36873697L
        @JvmField
        val isTransientToFocusedBind =
            ObjectCalls.getMethodBind("Window", "is_transient_to_focused", IS_TRANSIENT_TO_FOCUSED_HASH)

        private const val SET_EXCLUSIVE_HASH = 2586408642L
        @JvmField
        val setExclusiveBind =
            ObjectCalls.getMethodBind("Window", "set_exclusive", SET_EXCLUSIVE_HASH)

        private const val IS_EXCLUSIVE_HASH = 36873697L
        @JvmField
        val isExclusiveBind =
            ObjectCalls.getMethodBind("Window", "is_exclusive", IS_EXCLUSIVE_HASH)

        private const val SET_UNPARENT_WHEN_INVISIBLE_HASH = 2586408642L
        @JvmField
        val setUnparentWhenInvisibleBind =
            ObjectCalls.getMethodBind("Window", "set_unparent_when_invisible", SET_UNPARENT_WHEN_INVISIBLE_HASH)

        private const val CAN_DRAW_HASH = 36873697L
        @JvmField
        val canDrawBind =
            ObjectCalls.getMethodBind("Window", "can_draw", CAN_DRAW_HASH)

        private const val HAS_FOCUS_HASH = 36873697L
        @JvmField
        val hasFocusBind =
            ObjectCalls.getMethodBind("Window", "has_focus", HAS_FOCUS_HASH)

        private const val GRAB_FOCUS_HASH = 3218959716L
        @JvmField
        val grabFocusBind =
            ObjectCalls.getMethodBind("Window", "grab_focus", GRAB_FOCUS_HASH)

        private const val START_DRAG_HASH = 3218959716L
        @JvmField
        val startDragBind =
            ObjectCalls.getMethodBind("Window", "start_drag", START_DRAG_HASH)

        private const val START_RESIZE_HASH = 122288853L
        @JvmField
        val startResizeBind =
            ObjectCalls.getMethodBind("Window", "start_resize", START_RESIZE_HASH)

        private const val SET_IME_ACTIVE_HASH = 2586408642L
        @JvmField
        val setImeActiveBind =
            ObjectCalls.getMethodBind("Window", "set_ime_active", SET_IME_ACTIVE_HASH)

        private const val SET_IME_POSITION_HASH = 1130785943L
        @JvmField
        val setImePositionBind =
            ObjectCalls.getMethodBind("Window", "set_ime_position", SET_IME_POSITION_HASH)

        private const val IS_EMBEDDED_HASH = 36873697L
        @JvmField
        val isEmbeddedBind =
            ObjectCalls.getMethodBind("Window", "is_embedded", IS_EMBEDDED_HASH)

        private const val GET_CONTENTS_MINIMUM_SIZE_HASH = 3341600327L
        @JvmField
        val getContentsMinimumSizeBind =
            ObjectCalls.getMethodBind("Window", "get_contents_minimum_size", GET_CONTENTS_MINIMUM_SIZE_HASH)

        private const val SET_FORCE_NATIVE_HASH = 2586408642L
        @JvmField
        val setForceNativeBind =
            ObjectCalls.getMethodBind("Window", "set_force_native", SET_FORCE_NATIVE_HASH)

        private const val GET_FORCE_NATIVE_HASH = 36873697L
        @JvmField
        val getForceNativeBind =
            ObjectCalls.getMethodBind("Window", "get_force_native", GET_FORCE_NATIVE_HASH)

        private const val SET_CONTENT_SCALE_SIZE_HASH = 1130785943L
        @JvmField
        val setContentScaleSizeBind =
            ObjectCalls.getMethodBind("Window", "set_content_scale_size", SET_CONTENT_SCALE_SIZE_HASH)

        private const val GET_CONTENT_SCALE_SIZE_HASH = 3690982128L
        @JvmField
        val getContentScaleSizeBind =
            ObjectCalls.getMethodBind("Window", "get_content_scale_size", GET_CONTENT_SCALE_SIZE_HASH)

        private const val SET_CONTENT_SCALE_MODE_HASH = 2937716473L
        @JvmField
        val setContentScaleModeBind =
            ObjectCalls.getMethodBind("Window", "set_content_scale_mode", SET_CONTENT_SCALE_MODE_HASH)

        private const val GET_CONTENT_SCALE_MODE_HASH = 161585230L
        @JvmField
        val getContentScaleModeBind =
            ObjectCalls.getMethodBind("Window", "get_content_scale_mode", GET_CONTENT_SCALE_MODE_HASH)

        private const val SET_CONTENT_SCALE_ASPECT_HASH = 2370399418L
        @JvmField
        val setContentScaleAspectBind =
            ObjectCalls.getMethodBind("Window", "set_content_scale_aspect", SET_CONTENT_SCALE_ASPECT_HASH)

        private const val GET_CONTENT_SCALE_ASPECT_HASH = 4158790715L
        @JvmField
        val getContentScaleAspectBind =
            ObjectCalls.getMethodBind("Window", "get_content_scale_aspect", GET_CONTENT_SCALE_ASPECT_HASH)

        private const val SET_CONTENT_SCALE_STRETCH_HASH = 349355940L
        @JvmField
        val setContentScaleStretchBind =
            ObjectCalls.getMethodBind("Window", "set_content_scale_stretch", SET_CONTENT_SCALE_STRETCH_HASH)

        private const val GET_CONTENT_SCALE_STRETCH_HASH = 536857316L
        @JvmField
        val getContentScaleStretchBind =
            ObjectCalls.getMethodBind("Window", "get_content_scale_stretch", GET_CONTENT_SCALE_STRETCH_HASH)

        private const val SET_NONCLIENT_AREA_HASH = 1763793166L
        @JvmField
        val setNonclientAreaBind =
            ObjectCalls.getMethodBind("Window", "set_nonclient_area", SET_NONCLIENT_AREA_HASH)

        private const val GET_NONCLIENT_AREA_HASH = 410525958L
        @JvmField
        val getNonclientAreaBind =
            ObjectCalls.getMethodBind("Window", "get_nonclient_area", GET_NONCLIENT_AREA_HASH)

        private const val SET_KEEP_TITLE_VISIBLE_HASH = 2586408642L
        @JvmField
        val setKeepTitleVisibleBind =
            ObjectCalls.getMethodBind("Window", "set_keep_title_visible", SET_KEEP_TITLE_VISIBLE_HASH)

        private const val GET_KEEP_TITLE_VISIBLE_HASH = 36873697L
        @JvmField
        val getKeepTitleVisibleBind =
            ObjectCalls.getMethodBind("Window", "get_keep_title_visible", GET_KEEP_TITLE_VISIBLE_HASH)

        private const val SET_CONTENT_SCALE_FACTOR_HASH = 373806689L
        @JvmField
        val setContentScaleFactorBind =
            ObjectCalls.getMethodBind("Window", "set_content_scale_factor", SET_CONTENT_SCALE_FACTOR_HASH)

        private const val GET_CONTENT_SCALE_FACTOR_HASH = 1740695150L
        @JvmField
        val getContentScaleFactorBind =
            ObjectCalls.getMethodBind("Window", "get_content_scale_factor", GET_CONTENT_SCALE_FACTOR_HASH)

        private const val SET_MOUSE_PASSTHROUGH_POLYGON_HASH = 1509147220L
        @JvmField
        val setMousePassthroughPolygonBind =
            ObjectCalls.getMethodBind("Window", "set_mouse_passthrough_polygon", SET_MOUSE_PASSTHROUGH_POLYGON_HASH)

        private const val GET_MOUSE_PASSTHROUGH_POLYGON_HASH = 2961356807L
        @JvmField
        val getMousePassthroughPolygonBind =
            ObjectCalls.getMethodBind("Window", "get_mouse_passthrough_polygon", GET_MOUSE_PASSTHROUGH_POLYGON_HASH)

        private const val SET_WRAP_CONTROLS_HASH = 2586408642L
        @JvmField
        val setWrapControlsBind =
            ObjectCalls.getMethodBind("Window", "set_wrap_controls", SET_WRAP_CONTROLS_HASH)

        private const val IS_WRAPPING_CONTROLS_HASH = 36873697L
        @JvmField
        val isWrappingControlsBind =
            ObjectCalls.getMethodBind("Window", "is_wrapping_controls", IS_WRAPPING_CONTROLS_HASH)

        private const val CHILD_CONTROLS_CHANGED_HASH = 3218959716L
        @JvmField
        val childControlsChangedBind =
            ObjectCalls.getMethodBind("Window", "child_controls_changed", CHILD_CONTROLS_CHANGED_HASH)

        private const val SET_THEME_HASH = 2326690814L
        @JvmField
        val setThemeBind =
            ObjectCalls.getMethodBind("Window", "set_theme", SET_THEME_HASH)

        private const val GET_THEME_HASH = 3846893731L
        @JvmField
        val getThemeBind =
            ObjectCalls.getMethodBind("Window", "get_theme", GET_THEME_HASH)

        private const val SET_THEME_TYPE_VARIATION_HASH = 3304788590L
        @JvmField
        val setThemeTypeVariationBind =
            ObjectCalls.getMethodBind("Window", "set_theme_type_variation", SET_THEME_TYPE_VARIATION_HASH)

        private const val GET_THEME_TYPE_VARIATION_HASH = 2002593661L
        @JvmField
        val getThemeTypeVariationBind =
            ObjectCalls.getMethodBind("Window", "get_theme_type_variation", GET_THEME_TYPE_VARIATION_HASH)

        private const val BEGIN_BULK_THEME_OVERRIDE_HASH = 3218959716L
        @JvmField
        val beginBulkThemeOverrideBind =
            ObjectCalls.getMethodBind("Window", "begin_bulk_theme_override", BEGIN_BULK_THEME_OVERRIDE_HASH)

        private const val END_BULK_THEME_OVERRIDE_HASH = 3218959716L
        @JvmField
        val endBulkThemeOverrideBind =
            ObjectCalls.getMethodBind("Window", "end_bulk_theme_override", END_BULK_THEME_OVERRIDE_HASH)

        private const val ADD_THEME_ICON_OVERRIDE_HASH = 1373065600L
        @JvmField
        val addThemeIconOverrideBind =
            ObjectCalls.getMethodBind("Window", "add_theme_icon_override", ADD_THEME_ICON_OVERRIDE_HASH)

        private const val ADD_THEME_STYLEBOX_OVERRIDE_HASH = 4188838905L
        @JvmField
        val addThemeStyleboxOverrideBind =
            ObjectCalls.getMethodBind("Window", "add_theme_stylebox_override", ADD_THEME_STYLEBOX_OVERRIDE_HASH)

        private const val ADD_THEME_FONT_OVERRIDE_HASH = 3518018674L
        @JvmField
        val addThemeFontOverrideBind =
            ObjectCalls.getMethodBind("Window", "add_theme_font_override", ADD_THEME_FONT_OVERRIDE_HASH)

        private const val ADD_THEME_FONT_SIZE_OVERRIDE_HASH = 2415702435L
        @JvmField
        val addThemeFontSizeOverrideBind =
            ObjectCalls.getMethodBind("Window", "add_theme_font_size_override", ADD_THEME_FONT_SIZE_OVERRIDE_HASH)

        private const val ADD_THEME_COLOR_OVERRIDE_HASH = 4260178595L
        @JvmField
        val addThemeColorOverrideBind =
            ObjectCalls.getMethodBind("Window", "add_theme_color_override", ADD_THEME_COLOR_OVERRIDE_HASH)

        private const val ADD_THEME_CONSTANT_OVERRIDE_HASH = 2415702435L
        @JvmField
        val addThemeConstantOverrideBind =
            ObjectCalls.getMethodBind("Window", "add_theme_constant_override", ADD_THEME_CONSTANT_OVERRIDE_HASH)

        private const val REMOVE_THEME_ICON_OVERRIDE_HASH = 3304788590L
        @JvmField
        val removeThemeIconOverrideBind =
            ObjectCalls.getMethodBind("Window", "remove_theme_icon_override", REMOVE_THEME_ICON_OVERRIDE_HASH)

        private const val REMOVE_THEME_STYLEBOX_OVERRIDE_HASH = 3304788590L
        @JvmField
        val removeThemeStyleboxOverrideBind =
            ObjectCalls.getMethodBind("Window", "remove_theme_stylebox_override", REMOVE_THEME_STYLEBOX_OVERRIDE_HASH)

        private const val REMOVE_THEME_FONT_OVERRIDE_HASH = 3304788590L
        @JvmField
        val removeThemeFontOverrideBind =
            ObjectCalls.getMethodBind("Window", "remove_theme_font_override", REMOVE_THEME_FONT_OVERRIDE_HASH)

        private const val REMOVE_THEME_FONT_SIZE_OVERRIDE_HASH = 3304788590L
        @JvmField
        val removeThemeFontSizeOverrideBind =
            ObjectCalls.getMethodBind("Window", "remove_theme_font_size_override", REMOVE_THEME_FONT_SIZE_OVERRIDE_HASH)

        private const val REMOVE_THEME_COLOR_OVERRIDE_HASH = 3304788590L
        @JvmField
        val removeThemeColorOverrideBind =
            ObjectCalls.getMethodBind("Window", "remove_theme_color_override", REMOVE_THEME_COLOR_OVERRIDE_HASH)

        private const val REMOVE_THEME_CONSTANT_OVERRIDE_HASH = 3304788590L
        @JvmField
        val removeThemeConstantOverrideBind =
            ObjectCalls.getMethodBind("Window", "remove_theme_constant_override", REMOVE_THEME_CONSTANT_OVERRIDE_HASH)

        private const val GET_THEME_ICON_HASH = 3163973443L
        @JvmField
        val getThemeIconBind =
            ObjectCalls.getMethodBind("Window", "get_theme_icon", GET_THEME_ICON_HASH)

        private const val GET_THEME_STYLEBOX_HASH = 604739069L
        @JvmField
        val getThemeStyleboxBind =
            ObjectCalls.getMethodBind("Window", "get_theme_stylebox", GET_THEME_STYLEBOX_HASH)

        private const val GET_THEME_FONT_HASH = 2826986490L
        @JvmField
        val getThemeFontBind =
            ObjectCalls.getMethodBind("Window", "get_theme_font", GET_THEME_FONT_HASH)

        private const val GET_THEME_FONT_SIZE_HASH = 1327056374L
        @JvmField
        val getThemeFontSizeBind =
            ObjectCalls.getMethodBind("Window", "get_theme_font_size", GET_THEME_FONT_SIZE_HASH)

        private const val GET_THEME_COLOR_HASH = 2798751242L
        @JvmField
        val getThemeColorBind =
            ObjectCalls.getMethodBind("Window", "get_theme_color", GET_THEME_COLOR_HASH)

        private const val GET_THEME_CONSTANT_HASH = 1327056374L
        @JvmField
        val getThemeConstantBind =
            ObjectCalls.getMethodBind("Window", "get_theme_constant", GET_THEME_CONSTANT_HASH)

        private const val HAS_THEME_ICON_OVERRIDE_HASH = 2619796661L
        @JvmField
        val hasThemeIconOverrideBind =
            ObjectCalls.getMethodBind("Window", "has_theme_icon_override", HAS_THEME_ICON_OVERRIDE_HASH)

        private const val HAS_THEME_STYLEBOX_OVERRIDE_HASH = 2619796661L
        @JvmField
        val hasThemeStyleboxOverrideBind =
            ObjectCalls.getMethodBind("Window", "has_theme_stylebox_override", HAS_THEME_STYLEBOX_OVERRIDE_HASH)

        private const val HAS_THEME_FONT_OVERRIDE_HASH = 2619796661L
        @JvmField
        val hasThemeFontOverrideBind =
            ObjectCalls.getMethodBind("Window", "has_theme_font_override", HAS_THEME_FONT_OVERRIDE_HASH)

        private const val HAS_THEME_FONT_SIZE_OVERRIDE_HASH = 2619796661L
        @JvmField
        val hasThemeFontSizeOverrideBind =
            ObjectCalls.getMethodBind("Window", "has_theme_font_size_override", HAS_THEME_FONT_SIZE_OVERRIDE_HASH)

        private const val HAS_THEME_COLOR_OVERRIDE_HASH = 2619796661L
        @JvmField
        val hasThemeColorOverrideBind =
            ObjectCalls.getMethodBind("Window", "has_theme_color_override", HAS_THEME_COLOR_OVERRIDE_HASH)

        private const val HAS_THEME_CONSTANT_OVERRIDE_HASH = 2619796661L
        @JvmField
        val hasThemeConstantOverrideBind =
            ObjectCalls.getMethodBind("Window", "has_theme_constant_override", HAS_THEME_CONSTANT_OVERRIDE_HASH)

        private const val HAS_THEME_ICON_HASH = 866386512L
        @JvmField
        val hasThemeIconBind =
            ObjectCalls.getMethodBind("Window", "has_theme_icon", HAS_THEME_ICON_HASH)

        private const val HAS_THEME_STYLEBOX_HASH = 866386512L
        @JvmField
        val hasThemeStyleboxBind =
            ObjectCalls.getMethodBind("Window", "has_theme_stylebox", HAS_THEME_STYLEBOX_HASH)

        private const val HAS_THEME_FONT_HASH = 866386512L
        @JvmField
        val hasThemeFontBind =
            ObjectCalls.getMethodBind("Window", "has_theme_font", HAS_THEME_FONT_HASH)

        private const val HAS_THEME_FONT_SIZE_HASH = 866386512L
        @JvmField
        val hasThemeFontSizeBind =
            ObjectCalls.getMethodBind("Window", "has_theme_font_size", HAS_THEME_FONT_SIZE_HASH)

        private const val HAS_THEME_COLOR_HASH = 866386512L
        @JvmField
        val hasThemeColorBind =
            ObjectCalls.getMethodBind("Window", "has_theme_color", HAS_THEME_COLOR_HASH)

        private const val HAS_THEME_CONSTANT_HASH = 866386512L
        @JvmField
        val hasThemeConstantBind =
            ObjectCalls.getMethodBind("Window", "has_theme_constant", HAS_THEME_CONSTANT_HASH)

        private const val GET_THEME_DEFAULT_BASE_SCALE_HASH = 1740695150L
        @JvmField
        val getThemeDefaultBaseScaleBind =
            ObjectCalls.getMethodBind("Window", "get_theme_default_base_scale", GET_THEME_DEFAULT_BASE_SCALE_HASH)

        private const val GET_THEME_DEFAULT_FONT_HASH = 3229501585L
        @JvmField
        val getThemeDefaultFontBind =
            ObjectCalls.getMethodBind("Window", "get_theme_default_font", GET_THEME_DEFAULT_FONT_HASH)

        private const val GET_THEME_DEFAULT_FONT_SIZE_HASH = 3905245786L
        @JvmField
        val getThemeDefaultFontSizeBind =
            ObjectCalls.getMethodBind("Window", "get_theme_default_font_size", GET_THEME_DEFAULT_FONT_SIZE_HASH)

        private const val GET_WINDOW_ID_HASH = 3905245786L
        @JvmField
        val getWindowIdBind =
            ObjectCalls.getMethodBind("Window", "get_window_id", GET_WINDOW_ID_HASH)

        private const val SET_ACCESSIBILITY_NAME_HASH = 83702148L
        @JvmField
        val setAccessibilityNameBind =
            ObjectCalls.getMethodBind("Window", "set_accessibility_name", SET_ACCESSIBILITY_NAME_HASH)

        private const val GET_ACCESSIBILITY_NAME_HASH = 201670096L
        @JvmField
        val getAccessibilityNameBind =
            ObjectCalls.getMethodBind("Window", "get_accessibility_name", GET_ACCESSIBILITY_NAME_HASH)

        private const val SET_ACCESSIBILITY_DESCRIPTION_HASH = 83702148L
        @JvmField
        val setAccessibilityDescriptionBind =
            ObjectCalls.getMethodBind("Window", "set_accessibility_description", SET_ACCESSIBILITY_DESCRIPTION_HASH)

        private const val GET_ACCESSIBILITY_DESCRIPTION_HASH = 201670096L
        @JvmField
        val getAccessibilityDescriptionBind =
            ObjectCalls.getMethodBind("Window", "get_accessibility_description", GET_ACCESSIBILITY_DESCRIPTION_HASH)

        private const val GET_FOCUSED_WINDOW_HASH = 1835468782L
        @JvmField
        val getFocusedWindowBind =
            ObjectCalls.getMethodBind("Window", "get_focused_window", GET_FOCUSED_WINDOW_HASH)

        private const val SET_LAYOUT_DIRECTION_HASH = 3094704184L
        @JvmField
        val setLayoutDirectionBind =
            ObjectCalls.getMethodBind("Window", "set_layout_direction", SET_LAYOUT_DIRECTION_HASH)

        private const val GET_LAYOUT_DIRECTION_HASH = 3909617982L
        @JvmField
        val getLayoutDirectionBind =
            ObjectCalls.getMethodBind("Window", "get_layout_direction", GET_LAYOUT_DIRECTION_HASH)

        private const val IS_LAYOUT_RTL_HASH = 36873697L
        @JvmField
        val isLayoutRtlBind =
            ObjectCalls.getMethodBind("Window", "is_layout_rtl", IS_LAYOUT_RTL_HASH)

        private const val SET_AUTO_TRANSLATE_HASH = 2586408642L
        @JvmField
        val setAutoTranslateBind =
            ObjectCalls.getMethodBind("Window", "set_auto_translate", SET_AUTO_TRANSLATE_HASH)

        private const val IS_AUTO_TRANSLATING_HASH = 36873697L
        @JvmField
        val isAutoTranslatingBind =
            ObjectCalls.getMethodBind("Window", "is_auto_translating", IS_AUTO_TRANSLATING_HASH)

        private const val SET_USE_FONT_OVERSAMPLING_HASH = 2586408642L
        @JvmField
        val setUseFontOversamplingBind =
            ObjectCalls.getMethodBind("Window", "set_use_font_oversampling", SET_USE_FONT_OVERSAMPLING_HASH)

        private const val IS_USING_FONT_OVERSAMPLING_HASH = 36873697L
        @JvmField
        val isUsingFontOversamplingBind =
            ObjectCalls.getMethodBind("Window", "is_using_font_oversampling", IS_USING_FONT_OVERSAMPLING_HASH)

        private const val POPUP_HASH = 1680304321L
        @JvmField
        val popupBind =
            ObjectCalls.getMethodBind("Window", "popup", POPUP_HASH)

        private const val POPUP_ON_PARENT_HASH = 1763793166L
        @JvmField
        val popupOnParentBind =
            ObjectCalls.getMethodBind("Window", "popup_on_parent", POPUP_ON_PARENT_HASH)

        private const val POPUP_CENTERED_HASH = 3447975422L
        @JvmField
        val popupCenteredBind =
            ObjectCalls.getMethodBind("Window", "popup_centered", POPUP_CENTERED_HASH)

        private const val POPUP_CENTERED_RATIO_HASH = 1014814997L
        @JvmField
        val popupCenteredRatioBind =
            ObjectCalls.getMethodBind("Window", "popup_centered_ratio", POPUP_CENTERED_RATIO_HASH)

        private const val POPUP_CENTERED_CLAMPED_HASH = 2613752477L
        @JvmField
        val popupCenteredClampedBind =
            ObjectCalls.getMethodBind("Window", "popup_centered_clamped", POPUP_CENTERED_CLAMPED_HASH)

        private const val POPUP_EXCLUSIVE_HASH = 2134721627L
        @JvmField
        val popupExclusiveBind =
            ObjectCalls.getMethodBind("Window", "popup_exclusive", POPUP_EXCLUSIVE_HASH)

        private const val POPUP_EXCLUSIVE_ON_PARENT_HASH = 2344671043L
        @JvmField
        val popupExclusiveOnParentBind =
            ObjectCalls.getMethodBind("Window", "popup_exclusive_on_parent", POPUP_EXCLUSIVE_ON_PARENT_HASH)

        private const val POPUP_EXCLUSIVE_CENTERED_HASH = 3357594017L
        @JvmField
        val popupExclusiveCenteredBind =
            ObjectCalls.getMethodBind("Window", "popup_exclusive_centered", POPUP_EXCLUSIVE_CENTERED_HASH)

        private const val POPUP_EXCLUSIVE_CENTERED_RATIO_HASH = 2284776287L
        @JvmField
        val popupExclusiveCenteredRatioBind =
            ObjectCalls.getMethodBind("Window", "popup_exclusive_centered_ratio", POPUP_EXCLUSIVE_CENTERED_RATIO_HASH)

        private const val POPUP_EXCLUSIVE_CENTERED_CLAMPED_HASH = 2612708785L
        @JvmField
        val popupExclusiveCenteredClampedBind =
            ObjectCalls.getMethodBind("Window", "popup_exclusive_centered_clamped", POPUP_EXCLUSIVE_CENTERED_CLAMPED_HASH)
    }
}
