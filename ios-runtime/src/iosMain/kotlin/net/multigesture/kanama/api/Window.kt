package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/**
 * Generated from Godot docs: Window
 */
open class Window(handle: MemorySegment) : Viewport(handle) {
    var mode: Long
        @JvmName("modeProperty")
        get() = getMode()
        @JvmName("setModeProperty")
        set(value) = setMode(value)

    var title: String
        @JvmName("titleProperty")
        get() = getTitle()
        @JvmName("setTitleProperty")
        set(value) = setTitle(value)

    var initialPosition: Long
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

    val nonclientArea: Rect2i
        @JvmName("nonclientAreaProperty")
        get() = getNonclientArea()

    val mousePassthroughPolygon: List<Vector2>
        @JvmName("mousePassthroughPolygonProperty")
        get() = getMousePassthroughPolygon()

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
        get() = getFlag(0L)
        @JvmName("setUnresizableProperty")
        set(value) = setFlag(0L, value)

    var borderless: Boolean
        @JvmName("borderlessProperty")
        get() = getFlag(1L)
        @JvmName("setBorderlessProperty")
        set(value) = setFlag(1L, value)

    var alwaysOnTop: Boolean
        @JvmName("alwaysOnTopProperty")
        get() = getFlag(2L)
        @JvmName("setAlwaysOnTopProperty")
        set(value) = setFlag(2L, value)

    var transparent: Boolean
        @JvmName("transparentProperty")
        get() = getFlag(3L)
        @JvmName("setTransparentProperty")
        set(value) = setFlag(3L, value)

    var unfocusable: Boolean
        @JvmName("unfocusableProperty")
        get() = getFlag(4L)
        @JvmName("setUnfocusableProperty")
        set(value) = setFlag(4L, value)

    var popupWindow: Boolean
        @JvmName("popupWindowProperty")
        get() = getFlag(5L)
        @JvmName("setPopupWindowProperty")
        set(value) = setFlag(5L, value)

    var extendToTitle: Boolean
        @JvmName("extendToTitleProperty")
        get() = getFlag(6L)
        @JvmName("setExtendToTitleProperty")
        set(value) = setFlag(6L, value)

    var mousePassthrough: Boolean
        @JvmName("mousePassthroughProperty")
        get() = getFlag(7L)
        @JvmName("setMousePassthroughProperty")
        set(value) = setFlag(7L, value)

    var sharpCorners: Boolean
        @JvmName("sharpCornersProperty")
        get() = getFlag(8L)
        @JvmName("setSharpCornersProperty")
        set(value) = setFlag(8L, value)

    var excludeFromCapture: Boolean
        @JvmName("excludeFromCaptureProperty")
        get() = getFlag(9L)
        @JvmName("setExcludeFromCaptureProperty")
        set(value) = setFlag(9L, value)

    var popupWmHint: Boolean
        @JvmName("popupWmHintProperty")
        get() = getFlag(10L)
        @JvmName("setPopupWmHintProperty")
        set(value) = setFlag(10L, value)

    var minimizeDisabled: Boolean
        @JvmName("minimizeDisabledProperty")
        get() = getFlag(11L)
        @JvmName("setMinimizeDisabledProperty")
        set(value) = setFlag(11L, value)

    var maximizeDisabled: Boolean
        @JvmName("maximizeDisabledProperty")
        get() = getFlag(12L)
        @JvmName("setMaximizeDisabledProperty")
        set(value) = setFlag(12L, value)

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

    var contentScaleMode: Long
        @JvmName("contentScaleModeProperty")
        get() = getContentScaleMode()
        @JvmName("setContentScaleModeProperty")
        set(value) = setContentScaleMode(value)

    var contentScaleAspect: Long
        @JvmName("contentScaleAspectProperty")
        get() = getContentScaleAspect()
        @JvmName("setContentScaleAspectProperty")
        set(value) = setContentScaleAspect(value)

    var contentScaleStretch: Long
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

    fun setTitle(title: String) {
        ObjectCalls.ptrcallWithStringArg(setTitleBind, handle, title)
    }

    fun getTitle(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTitleBind, handle)
    }

    fun setInitialPosition(initialPosition: Long) {
        ObjectCalls.ptrcallWithLongArg(setInitialPositionBind, handle, initialPosition)
    }

    fun getInitialPosition(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getInitialPositionBind, handle)
    }

    fun setCurrentScreen(index: Int) {
        ObjectCalls.ptrcallWithIntArg(setCurrentScreenBind, handle, index)
    }

    fun getCurrentScreen(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCurrentScreenBind, handle)
    }

    fun setPosition(position: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setPositionBind, handle, position)
    }

    fun getPosition(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getPositionBind, handle)
    }

    fun moveToCenter() {
        ObjectCalls.ptrcallNoArgs(moveToCenterBind, handle)
    }

    fun setSize(size: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setSizeBind, handle, size)
    }

    fun getSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getSizeBind, handle)
    }

    fun resetSize() {
        ObjectCalls.ptrcallNoArgs(resetSizeBind, handle)
    }

    fun getPositionWithDecorations(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getPositionWithDecorationsBind, handle)
    }

    fun getSizeWithDecorations(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getSizeWithDecorationsBind, handle)
    }

    fun setMaxSize(maxSize: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setMaxSizeBind, handle, maxSize)
    }

    fun getMaxSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getMaxSizeBind, handle)
    }

    fun setMinSize(minSize: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setMinSizeBind, handle, minSize)
    }

    fun getMinSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getMinSizeBind, handle)
    }

    fun setMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setModeBind, handle, mode)
    }

    fun getMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getModeBind, handle)
    }

    fun setFlag(flag: Long, enabled: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setFlagBind, handle, flag, enabled)
    }

    fun getFlag(flag: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getFlagBind, handle, flag)
    }

    fun setHdrOutputRequested(requested: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHdrOutputRequestedBind, handle, requested)
    }

    fun isHdrOutputRequested(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHdrOutputRequestedBind, handle)
    }

    fun getOutputMaxLinearValue(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getOutputMaxLinearValueBind, handle)
    }

    fun isMaximizeAllowed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMaximizeAllowedBind, handle)
    }

    fun requestAttention() {
        ObjectCalls.ptrcallNoArgs(requestAttentionBind, handle)
    }

    fun setTaskbarProgressValue(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTaskbarProgressValueBind, handle, value)
    }

    fun setTaskbarProgressState(state: Long) {
        ObjectCalls.ptrcallWithLongArg(setTaskbarProgressStateBind, handle, state)
    }

    fun moveToForeground() {
        ObjectCalls.ptrcallNoArgs(moveToForegroundBind, handle)
    }

    fun setVisible(visible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVisibleBind, handle, visible)
    }

    fun isVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVisibleBind, handle)
    }

    fun hide() {
        ObjectCalls.ptrcallNoArgs(hideBind, handle)
    }

    fun show() {
        ObjectCalls.ptrcallNoArgs(showBind, handle)
    }

    fun setTransient(transient: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTransientBind, handle, transient)
    }

    fun isTransient(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTransientBind, handle)
    }

    fun setTransientToFocused(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTransientToFocusedBind, handle, enable)
    }

    fun isTransientToFocused(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTransientToFocusedBind, handle)
    }

    fun setExclusive(exclusive: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setExclusiveBind, handle, exclusive)
    }

    fun isExclusive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isExclusiveBind, handle)
    }

    fun setUnparentWhenInvisible(unparent: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUnparentWhenInvisibleBind, handle, unparent)
    }

    fun canDraw(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(canDrawBind, handle)
    }

    fun hasFocus(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasFocusBind, handle)
    }

    fun grabFocus() {
        ObjectCalls.ptrcallNoArgs(grabFocusBind, handle)
    }

    fun startDrag() {
        ObjectCalls.ptrcallNoArgs(startDragBind, handle)
    }

    fun startResize(edge: Long) {
        ObjectCalls.ptrcallWithLongArg(startResizeBind, handle, edge)
    }

    fun setImeActive(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setImeActiveBind, handle, active)
    }

    fun setImePosition(position: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setImePositionBind, handle, position)
    }

    fun isEmbedded(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEmbeddedBind, handle)
    }

    fun getContentsMinimumSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getContentsMinimumSizeBind, handle)
    }

    fun setForceNative(forceNative: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setForceNativeBind, handle, forceNative)
    }

    fun getForceNative(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getForceNativeBind, handle)
    }

    fun setContentScaleSize(size: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setContentScaleSizeBind, handle, size)
    }

    fun getContentScaleSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getContentScaleSizeBind, handle)
    }

    fun setContentScaleMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setContentScaleModeBind, handle, mode)
    }

    fun getContentScaleMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getContentScaleModeBind, handle)
    }

    fun setContentScaleAspect(aspect: Long) {
        ObjectCalls.ptrcallWithLongArg(setContentScaleAspectBind, handle, aspect)
    }

    fun getContentScaleAspect(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getContentScaleAspectBind, handle)
    }

    fun setContentScaleStretch(stretch: Long) {
        ObjectCalls.ptrcallWithLongArg(setContentScaleStretchBind, handle, stretch)
    }

    fun getContentScaleStretch(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getContentScaleStretchBind, handle)
    }

    fun getNonclientArea(): Rect2i {
        return ObjectCalls.ptrcallNoArgsRetRect2i(getNonclientAreaBind, handle)
    }

    fun setKeepTitleVisible(titleVisible: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setKeepTitleVisibleBind, handle, titleVisible)
    }

    fun getKeepTitleVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getKeepTitleVisibleBind, handle)
    }

    fun setContentScaleFactor(factor: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setContentScaleFactorBind, handle, factor)
    }

    fun getContentScaleFactor(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getContentScaleFactorBind, handle)
    }

    fun getMousePassthroughPolygon(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getMousePassthroughPolygonBind, handle)
    }

    fun setWrapControls(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setWrapControlsBind, handle, enable)
    }

    fun isWrappingControls(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isWrappingControlsBind, handle)
    }

    fun childControlsChanged() {
        ObjectCalls.ptrcallNoArgs(childControlsChangedBind, handle)
    }

    fun setTheme(theme: Theme?) {
        ObjectCalls.ptrcallWithObjectArgs(setThemeBind, handle, listOf(theme?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getTheme(): Theme? {
        return Theme.wrap(ObjectCalls.ptrcallNoArgsRetObject(getThemeBind, handle))
    }

    fun setThemeTypeVariation(themeType: String) {
        ObjectCalls.ptrcallWithStringNameArg(setThemeTypeVariationBind, handle, themeType)
    }

    fun getThemeTypeVariation(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getThemeTypeVariationBind, handle)
    }

    fun beginBulkThemeOverride() {
        ObjectCalls.ptrcallNoArgs(beginBulkThemeOverrideBind, handle)
    }

    fun endBulkThemeOverride() {
        ObjectCalls.ptrcallNoArgs(endBulkThemeOverrideBind, handle)
    }

    fun addThemeIconOverride(name: String, texture: Texture2D?) {
        ObjectCalls.ptrcallWithStringNameAndObjectArg(addThemeIconOverrideBind, handle, name, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun addThemeStyleboxOverride(name: String, stylebox: StyleBox?) {
        ObjectCalls.ptrcallWithStringNameAndObjectArg(addThemeStyleboxOverrideBind, handle, name, stylebox?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun addThemeFontOverride(name: String, font: Font?) {
        ObjectCalls.ptrcallWithStringNameAndObjectArg(addThemeFontOverrideBind, handle, name, font?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun addThemeFontSizeOverride(name: String, fontSize: Int) {
        ObjectCalls.ptrcallWithStringNameAndIntArg(addThemeFontSizeOverrideBind, handle, name, fontSize)
    }

    fun addThemeColorOverride(name: String, color: Color) {
        ObjectCalls.ptrcallWithStringNameAndColorArg(addThemeColorOverrideBind, handle, name, color)
    }

    fun addThemeConstantOverride(name: String, constant: Int) {
        ObjectCalls.ptrcallWithStringNameAndIntArg(addThemeConstantOverrideBind, handle, name, constant)
    }

    fun removeThemeIconOverride(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeThemeIconOverrideBind, handle, name)
    }

    fun removeThemeStyleboxOverride(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeThemeStyleboxOverrideBind, handle, name)
    }

    fun removeThemeFontOverride(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeThemeFontOverrideBind, handle, name)
    }

    fun removeThemeFontSizeOverride(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeThemeFontSizeOverrideBind, handle, name)
    }

    fun removeThemeColorOverride(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeThemeColorOverrideBind, handle, name)
    }

    fun removeThemeConstantOverride(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeThemeConstantOverrideBind, handle, name)
    }

    fun getThemeIcon(name: String, themeType: String = ""): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithTwoStringNameArgsRetObject(getThemeIconBind, handle, name, themeType))
    }

    fun getThemeStylebox(name: String, themeType: String = ""): StyleBox? {
        return StyleBox.wrap(ObjectCalls.ptrcallWithTwoStringNameArgsRetObject(getThemeStyleboxBind, handle, name, themeType))
    }

    fun getThemeFont(name: String, themeType: String = ""): Font? {
        return Font.wrap(ObjectCalls.ptrcallWithTwoStringNameArgsRetObject(getThemeFontBind, handle, name, themeType))
    }

    fun getThemeFontSize(name: String, themeType: String = ""): Int {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetInt(getThemeFontSizeBind, handle, name, themeType)
    }

    fun getThemeColor(name: String, themeType: String = ""): Color {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetColor(getThemeColorBind, handle, name, themeType)
    }

    fun getThemeConstant(name: String, themeType: String = ""): Int {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetInt(getThemeConstantBind, handle, name, themeType)
    }

    fun hasThemeIconOverride(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasThemeIconOverrideBind, handle, name)
    }

    fun hasThemeStyleboxOverride(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasThemeStyleboxOverrideBind, handle, name)
    }

    fun hasThemeFontOverride(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasThemeFontOverrideBind, handle, name)
    }

    fun hasThemeFontSizeOverride(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasThemeFontSizeOverrideBind, handle, name)
    }

    fun hasThemeColorOverride(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasThemeColorOverrideBind, handle, name)
    }

    fun hasThemeConstantOverride(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasThemeConstantOverrideBind, handle, name)
    }

    fun hasThemeIcon(name: String, themeType: String = ""): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasThemeIconBind, handle, name, themeType)
    }

    fun hasThemeStylebox(name: String, themeType: String = ""): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasThemeStyleboxBind, handle, name, themeType)
    }

    fun hasThemeFont(name: String, themeType: String = ""): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasThemeFontBind, handle, name, themeType)
    }

    fun hasThemeFontSize(name: String, themeType: String = ""): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasThemeFontSizeBind, handle, name, themeType)
    }

    fun hasThemeColor(name: String, themeType: String = ""): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasThemeColorBind, handle, name, themeType)
    }

    fun hasThemeConstant(name: String, themeType: String = ""): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasThemeConstantBind, handle, name, themeType)
    }

    fun getThemeDefaultBaseScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getThemeDefaultBaseScaleBind, handle)
    }

    fun getThemeDefaultFont(): Font? {
        return Font.wrap(ObjectCalls.ptrcallNoArgsRetObject(getThemeDefaultFontBind, handle))
    }

    fun getThemeDefaultFontSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getThemeDefaultFontSizeBind, handle)
    }

    fun getWindowId(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getWindowIdBind, handle)
    }

    fun setAccessibilityName(name: String) {
        ObjectCalls.ptrcallWithStringArg(setAccessibilityNameBind, handle, name)
    }

    fun getAccessibilityName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getAccessibilityNameBind, handle)
    }

    fun setAccessibilityDescription(description: String) {
        ObjectCalls.ptrcallWithStringArg(setAccessibilityDescriptionBind, handle, description)
    }

    fun getAccessibilityDescription(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getAccessibilityDescriptionBind, handle)
    }

    fun setLayoutDirection(direction: Long) {
        ObjectCalls.ptrcallWithLongArg(setLayoutDirectionBind, handle, direction)
    }

    fun getLayoutDirection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getLayoutDirectionBind, handle)
    }

    fun isLayoutRtl(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLayoutRtlBind, handle)
    }

    fun setAutoTranslate(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoTranslateBind, handle, enable)
    }

    fun isAutoTranslating(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAutoTranslatingBind, handle)
    }

    fun setUseFontOversampling(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseFontOversamplingBind, handle, enable)
    }

    fun isUsingFontOversampling(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingFontOversamplingBind, handle)
    }

    fun popupCentered(minsize: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(popupCenteredBind, handle, minsize)
    }

    fun popupCenteredRatio(ratio: Double = 0.8) {
        ObjectCalls.ptrcallWithDoubleArg(popupCenteredRatioBind, handle, ratio)
    }

    fun popupCenteredClamped(minsize: Vector2i, fallbackRatio: Double = 0.75) {
        ObjectCalls.ptrcallWithVector2iAndDoubleArg(popupCenteredClampedBind, handle, minsize, fallbackRatio)
    }

    fun popupExclusiveCentered(fromNode: Node, minsize: Vector2i) {
        ObjectCalls.ptrcallWithObjectAndVector2iArg(popupExclusiveCenteredBind, handle, fromNode.handle, minsize)
    }

    fun popupExclusiveCenteredRatio(fromNode: Node, ratio: Double = 0.8) {
        ObjectCalls.ptrcallWithObjectAndDoubleArg(popupExclusiveCenteredRatioBind, handle, fromNode.handle, ratio)
    }

    fun popupExclusiveCenteredClamped(fromNode: Node, minsize: Vector2i, fallbackRatio: Double = 0.75) {
        ObjectCalls.ptrcallWithObjectVector2iAndDoubleArg(popupExclusiveCenteredClampedBind, handle, fromNode.handle, minsize, fallbackRatio)
    }

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

    companion object {
        fun getFocusedWindow(): Window? {
            return Window.wrap(ObjectCalls.ptrcallNoArgsRetObject(getFocusedWindowBind, MemorySegment.NULL))
        }

        const val NOTIFICATION_VISIBILITY_CHANGED: Long = 30L
        const val NOTIFICATION_THEME_CHANGED: Long = 32L
        const val MODE_WINDOWED: Long = 0L
        const val MODE_MINIMIZED: Long = 1L
        const val MODE_MAXIMIZED: Long = 2L
        const val MODE_FULLSCREEN: Long = 3L
        const val MODE_EXCLUSIVE_FULLSCREEN: Long = 4L
        const val FLAG_RESIZE_DISABLED: Long = 0L
        const val FLAG_BORDERLESS: Long = 1L
        const val FLAG_ALWAYS_ON_TOP: Long = 2L
        const val FLAG_TRANSPARENT: Long = 3L
        const val FLAG_NO_FOCUS: Long = 4L
        const val FLAG_POPUP: Long = 5L
        const val FLAG_EXTEND_TO_TITLE: Long = 6L
        const val FLAG_MOUSE_PASSTHROUGH: Long = 7L
        const val FLAG_SHARP_CORNERS: Long = 8L
        const val FLAG_EXCLUDE_FROM_CAPTURE: Long = 9L
        const val FLAG_POPUP_WM_HINT: Long = 10L
        const val FLAG_MINIMIZE_DISABLED: Long = 11L
        const val FLAG_MAXIMIZE_DISABLED: Long = 12L
        const val FLAG_MAX: Long = 13L
        const val CONTENT_SCALE_MODE_DISABLED: Long = 0L
        const val CONTENT_SCALE_MODE_CANVAS_ITEMS: Long = 1L
        const val CONTENT_SCALE_MODE_VIEWPORT: Long = 2L
        const val CONTENT_SCALE_ASPECT_IGNORE: Long = 0L
        const val CONTENT_SCALE_ASPECT_KEEP: Long = 1L
        const val CONTENT_SCALE_ASPECT_KEEP_WIDTH: Long = 2L
        const val CONTENT_SCALE_ASPECT_KEEP_HEIGHT: Long = 3L
        const val CONTENT_SCALE_ASPECT_EXPAND: Long = 4L
        const val CONTENT_SCALE_STRETCH_FRACTIONAL: Long = 0L
        const val CONTENT_SCALE_STRETCH_INTEGER: Long = 1L
        const val LAYOUT_DIRECTION_INHERITED: Long = 0L
        const val LAYOUT_DIRECTION_APPLICATION_LOCALE: Long = 1L
        const val LAYOUT_DIRECTION_LTR: Long = 2L
        const val LAYOUT_DIRECTION_RTL: Long = 3L
        const val LAYOUT_DIRECTION_SYSTEM_LOCALE: Long = 4L
        const val LAYOUT_DIRECTION_MAX: Long = 5L
        const val LAYOUT_DIRECTION_LOCALE: Long = 1L
        const val WINDOW_INITIAL_POSITION_ABSOLUTE: Long = 0L
        const val WINDOW_INITIAL_POSITION_CENTER_PRIMARY_SCREEN: Long = 1L
        const val WINDOW_INITIAL_POSITION_CENTER_MAIN_WINDOW_SCREEN: Long = 2L
        const val WINDOW_INITIAL_POSITION_CENTER_OTHER_SCREEN: Long = 3L
        const val WINDOW_INITIAL_POSITION_CENTER_SCREEN_WITH_MOUSE_FOCUS: Long = 4L
        const val WINDOW_INITIAL_POSITION_CENTER_SCREEN_WITH_KEYBOARD_FOCUS: Long = 5L

        fun fromHandle(handle: MemorySegment): Window? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Window? =
            if (handle.address() == 0L) null else Window(handle)

        private const val SET_TITLE_HASH = 83702148L
        private val setTitleBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_title", SET_TITLE_HASH)
        }

        private const val GET_TITLE_HASH = 201670096L
        private val getTitleBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_title", GET_TITLE_HASH)
        }

        private const val SET_INITIAL_POSITION_HASH = 4084468099L
        private val setInitialPositionBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_initial_position", SET_INITIAL_POSITION_HASH)
        }

        private const val GET_INITIAL_POSITION_HASH = 4294066647L
        private val getInitialPositionBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_initial_position", GET_INITIAL_POSITION_HASH)
        }

        private const val SET_CURRENT_SCREEN_HASH = 1286410249L
        private val setCurrentScreenBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_current_screen", SET_CURRENT_SCREEN_HASH)
        }

        private const val GET_CURRENT_SCREEN_HASH = 3905245786L
        private val getCurrentScreenBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_current_screen", GET_CURRENT_SCREEN_HASH)
        }

        private const val SET_POSITION_HASH = 1130785943L
        private val setPositionBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_position", SET_POSITION_HASH)
        }

        private const val GET_POSITION_HASH = 3690982128L
        private val getPositionBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_position", GET_POSITION_HASH)
        }

        private const val MOVE_TO_CENTER_HASH = 3218959716L
        private val moveToCenterBind by lazy {
            ObjectCalls.getMethodBind("Window", "move_to_center", MOVE_TO_CENTER_HASH)
        }

        private const val SET_SIZE_HASH = 1130785943L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3690982128L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_size", GET_SIZE_HASH)
        }

        private const val RESET_SIZE_HASH = 3218959716L
        private val resetSizeBind by lazy {
            ObjectCalls.getMethodBind("Window", "reset_size", RESET_SIZE_HASH)
        }

        private const val GET_POSITION_WITH_DECORATIONS_HASH = 3690982128L
        private val getPositionWithDecorationsBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_position_with_decorations", GET_POSITION_WITH_DECORATIONS_HASH)
        }

        private const val GET_SIZE_WITH_DECORATIONS_HASH = 3690982128L
        private val getSizeWithDecorationsBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_size_with_decorations", GET_SIZE_WITH_DECORATIONS_HASH)
        }

        private const val SET_MAX_SIZE_HASH = 1130785943L
        private val setMaxSizeBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_max_size", SET_MAX_SIZE_HASH)
        }

        private const val GET_MAX_SIZE_HASH = 3690982128L
        private val getMaxSizeBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_max_size", GET_MAX_SIZE_HASH)
        }

        private const val SET_MIN_SIZE_HASH = 1130785943L
        private val setMinSizeBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_min_size", SET_MIN_SIZE_HASH)
        }

        private const val GET_MIN_SIZE_HASH = 3690982128L
        private val getMinSizeBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_min_size", GET_MIN_SIZE_HASH)
        }

        private const val SET_MODE_HASH = 3095236531L
        private val setModeBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_mode", SET_MODE_HASH)
        }

        private const val GET_MODE_HASH = 2566346114L
        private val getModeBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_mode", GET_MODE_HASH)
        }

        private const val SET_FLAG_HASH = 3426449779L
        private val setFlagBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_flag", SET_FLAG_HASH)
        }

        private const val GET_FLAG_HASH = 3062752289L
        private val getFlagBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_flag", GET_FLAG_HASH)
        }

        private const val SET_HDR_OUTPUT_REQUESTED_HASH = 2586408642L
        private val setHdrOutputRequestedBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_hdr_output_requested", SET_HDR_OUTPUT_REQUESTED_HASH)
        }

        private const val IS_HDR_OUTPUT_REQUESTED_HASH = 36873697L
        private val isHdrOutputRequestedBind by lazy {
            ObjectCalls.getMethodBind("Window", "is_hdr_output_requested", IS_HDR_OUTPUT_REQUESTED_HASH)
        }

        private const val GET_OUTPUT_MAX_LINEAR_VALUE_HASH = 1740695150L
        private val getOutputMaxLinearValueBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_output_max_linear_value", GET_OUTPUT_MAX_LINEAR_VALUE_HASH)
        }

        private const val IS_MAXIMIZE_ALLOWED_HASH = 36873697L
        private val isMaximizeAllowedBind by lazy {
            ObjectCalls.getMethodBind("Window", "is_maximize_allowed", IS_MAXIMIZE_ALLOWED_HASH)
        }

        private const val REQUEST_ATTENTION_HASH = 3218959716L
        private val requestAttentionBind by lazy {
            ObjectCalls.getMethodBind("Window", "request_attention", REQUEST_ATTENTION_HASH)
        }

        private const val SET_TASKBAR_PROGRESS_VALUE_HASH = 373806689L
        private val setTaskbarProgressValueBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_taskbar_progress_value", SET_TASKBAR_PROGRESS_VALUE_HASH)
        }

        private const val SET_TASKBAR_PROGRESS_STATE_HASH = 824071031L
        private val setTaskbarProgressStateBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_taskbar_progress_state", SET_TASKBAR_PROGRESS_STATE_HASH)
        }

        private const val MOVE_TO_FOREGROUND_HASH = 3218959716L
        private val moveToForegroundBind by lazy {
            ObjectCalls.getMethodBind("Window", "move_to_foreground", MOVE_TO_FOREGROUND_HASH)
        }

        private const val SET_VISIBLE_HASH = 2586408642L
        private val setVisibleBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_visible", SET_VISIBLE_HASH)
        }

        private const val IS_VISIBLE_HASH = 36873697L
        private val isVisibleBind by lazy {
            ObjectCalls.getMethodBind("Window", "is_visible", IS_VISIBLE_HASH)
        }

        private const val HIDE_HASH = 3218959716L
        private val hideBind by lazy {
            ObjectCalls.getMethodBind("Window", "hide", HIDE_HASH)
        }

        private const val SHOW_HASH = 3218959716L
        private val showBind by lazy {
            ObjectCalls.getMethodBind("Window", "show", SHOW_HASH)
        }

        private const val SET_TRANSIENT_HASH = 2586408642L
        private val setTransientBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_transient", SET_TRANSIENT_HASH)
        }

        private const val IS_TRANSIENT_HASH = 36873697L
        private val isTransientBind by lazy {
            ObjectCalls.getMethodBind("Window", "is_transient", IS_TRANSIENT_HASH)
        }

        private const val SET_TRANSIENT_TO_FOCUSED_HASH = 2586408642L
        private val setTransientToFocusedBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_transient_to_focused", SET_TRANSIENT_TO_FOCUSED_HASH)
        }

        private const val IS_TRANSIENT_TO_FOCUSED_HASH = 36873697L
        private val isTransientToFocusedBind by lazy {
            ObjectCalls.getMethodBind("Window", "is_transient_to_focused", IS_TRANSIENT_TO_FOCUSED_HASH)
        }

        private const val SET_EXCLUSIVE_HASH = 2586408642L
        private val setExclusiveBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_exclusive", SET_EXCLUSIVE_HASH)
        }

        private const val IS_EXCLUSIVE_HASH = 36873697L
        private val isExclusiveBind by lazy {
            ObjectCalls.getMethodBind("Window", "is_exclusive", IS_EXCLUSIVE_HASH)
        }

        private const val SET_UNPARENT_WHEN_INVISIBLE_HASH = 2586408642L
        private val setUnparentWhenInvisibleBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_unparent_when_invisible", SET_UNPARENT_WHEN_INVISIBLE_HASH)
        }

        private const val CAN_DRAW_HASH = 36873697L
        private val canDrawBind by lazy {
            ObjectCalls.getMethodBind("Window", "can_draw", CAN_DRAW_HASH)
        }

        private const val HAS_FOCUS_HASH = 36873697L
        private val hasFocusBind by lazy {
            ObjectCalls.getMethodBind("Window", "has_focus", HAS_FOCUS_HASH)
        }

        private const val GRAB_FOCUS_HASH = 3218959716L
        private val grabFocusBind by lazy {
            ObjectCalls.getMethodBind("Window", "grab_focus", GRAB_FOCUS_HASH)
        }

        private const val START_DRAG_HASH = 3218959716L
        private val startDragBind by lazy {
            ObjectCalls.getMethodBind("Window", "start_drag", START_DRAG_HASH)
        }

        private const val START_RESIZE_HASH = 122288853L
        private val startResizeBind by lazy {
            ObjectCalls.getMethodBind("Window", "start_resize", START_RESIZE_HASH)
        }

        private const val SET_IME_ACTIVE_HASH = 2586408642L
        private val setImeActiveBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_ime_active", SET_IME_ACTIVE_HASH)
        }

        private const val SET_IME_POSITION_HASH = 1130785943L
        private val setImePositionBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_ime_position", SET_IME_POSITION_HASH)
        }

        private const val IS_EMBEDDED_HASH = 36873697L
        private val isEmbeddedBind by lazy {
            ObjectCalls.getMethodBind("Window", "is_embedded", IS_EMBEDDED_HASH)
        }

        private const val GET_CONTENTS_MINIMUM_SIZE_HASH = 3341600327L
        private val getContentsMinimumSizeBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_contents_minimum_size", GET_CONTENTS_MINIMUM_SIZE_HASH)
        }

        private const val SET_FORCE_NATIVE_HASH = 2586408642L
        private val setForceNativeBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_force_native", SET_FORCE_NATIVE_HASH)
        }

        private const val GET_FORCE_NATIVE_HASH = 36873697L
        private val getForceNativeBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_force_native", GET_FORCE_NATIVE_HASH)
        }

        private const val SET_CONTENT_SCALE_SIZE_HASH = 1130785943L
        private val setContentScaleSizeBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_content_scale_size", SET_CONTENT_SCALE_SIZE_HASH)
        }

        private const val GET_CONTENT_SCALE_SIZE_HASH = 3690982128L
        private val getContentScaleSizeBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_content_scale_size", GET_CONTENT_SCALE_SIZE_HASH)
        }

        private const val SET_CONTENT_SCALE_MODE_HASH = 2937716473L
        private val setContentScaleModeBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_content_scale_mode", SET_CONTENT_SCALE_MODE_HASH)
        }

        private const val GET_CONTENT_SCALE_MODE_HASH = 161585230L
        private val getContentScaleModeBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_content_scale_mode", GET_CONTENT_SCALE_MODE_HASH)
        }

        private const val SET_CONTENT_SCALE_ASPECT_HASH = 2370399418L
        private val setContentScaleAspectBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_content_scale_aspect", SET_CONTENT_SCALE_ASPECT_HASH)
        }

        private const val GET_CONTENT_SCALE_ASPECT_HASH = 4158790715L
        private val getContentScaleAspectBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_content_scale_aspect", GET_CONTENT_SCALE_ASPECT_HASH)
        }

        private const val SET_CONTENT_SCALE_STRETCH_HASH = 349355940L
        private val setContentScaleStretchBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_content_scale_stretch", SET_CONTENT_SCALE_STRETCH_HASH)
        }

        private const val GET_CONTENT_SCALE_STRETCH_HASH = 536857316L
        private val getContentScaleStretchBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_content_scale_stretch", GET_CONTENT_SCALE_STRETCH_HASH)
        }

        private const val GET_NONCLIENT_AREA_HASH = 410525958L
        private val getNonclientAreaBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_nonclient_area", GET_NONCLIENT_AREA_HASH)
        }

        private const val SET_KEEP_TITLE_VISIBLE_HASH = 2586408642L
        private val setKeepTitleVisibleBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_keep_title_visible", SET_KEEP_TITLE_VISIBLE_HASH)
        }

        private const val GET_KEEP_TITLE_VISIBLE_HASH = 36873697L
        private val getKeepTitleVisibleBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_keep_title_visible", GET_KEEP_TITLE_VISIBLE_HASH)
        }

        private const val SET_CONTENT_SCALE_FACTOR_HASH = 373806689L
        private val setContentScaleFactorBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_content_scale_factor", SET_CONTENT_SCALE_FACTOR_HASH)
        }

        private const val GET_CONTENT_SCALE_FACTOR_HASH = 1740695150L
        private val getContentScaleFactorBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_content_scale_factor", GET_CONTENT_SCALE_FACTOR_HASH)
        }

        private const val GET_MOUSE_PASSTHROUGH_POLYGON_HASH = 2961356807L
        private val getMousePassthroughPolygonBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_mouse_passthrough_polygon", GET_MOUSE_PASSTHROUGH_POLYGON_HASH)
        }

        private const val SET_WRAP_CONTROLS_HASH = 2586408642L
        private val setWrapControlsBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_wrap_controls", SET_WRAP_CONTROLS_HASH)
        }

        private const val IS_WRAPPING_CONTROLS_HASH = 36873697L
        private val isWrappingControlsBind by lazy {
            ObjectCalls.getMethodBind("Window", "is_wrapping_controls", IS_WRAPPING_CONTROLS_HASH)
        }

        private const val CHILD_CONTROLS_CHANGED_HASH = 3218959716L
        private val childControlsChangedBind by lazy {
            ObjectCalls.getMethodBind("Window", "child_controls_changed", CHILD_CONTROLS_CHANGED_HASH)
        }

        private const val SET_THEME_HASH = 2326690814L
        private val setThemeBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_theme", SET_THEME_HASH)
        }

        private const val GET_THEME_HASH = 3846893731L
        private val getThemeBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_theme", GET_THEME_HASH)
        }

        private const val SET_THEME_TYPE_VARIATION_HASH = 3304788590L
        private val setThemeTypeVariationBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_theme_type_variation", SET_THEME_TYPE_VARIATION_HASH)
        }

        private const val GET_THEME_TYPE_VARIATION_HASH = 2002593661L
        private val getThemeTypeVariationBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_theme_type_variation", GET_THEME_TYPE_VARIATION_HASH)
        }

        private const val BEGIN_BULK_THEME_OVERRIDE_HASH = 3218959716L
        private val beginBulkThemeOverrideBind by lazy {
            ObjectCalls.getMethodBind("Window", "begin_bulk_theme_override", BEGIN_BULK_THEME_OVERRIDE_HASH)
        }

        private const val END_BULK_THEME_OVERRIDE_HASH = 3218959716L
        private val endBulkThemeOverrideBind by lazy {
            ObjectCalls.getMethodBind("Window", "end_bulk_theme_override", END_BULK_THEME_OVERRIDE_HASH)
        }

        private const val ADD_THEME_ICON_OVERRIDE_HASH = 1373065600L
        private val addThemeIconOverrideBind by lazy {
            ObjectCalls.getMethodBind("Window", "add_theme_icon_override", ADD_THEME_ICON_OVERRIDE_HASH)
        }

        private const val ADD_THEME_STYLEBOX_OVERRIDE_HASH = 4188838905L
        private val addThemeStyleboxOverrideBind by lazy {
            ObjectCalls.getMethodBind("Window", "add_theme_stylebox_override", ADD_THEME_STYLEBOX_OVERRIDE_HASH)
        }

        private const val ADD_THEME_FONT_OVERRIDE_HASH = 3518018674L
        private val addThemeFontOverrideBind by lazy {
            ObjectCalls.getMethodBind("Window", "add_theme_font_override", ADD_THEME_FONT_OVERRIDE_HASH)
        }

        private const val ADD_THEME_FONT_SIZE_OVERRIDE_HASH = 2415702435L
        private val addThemeFontSizeOverrideBind by lazy {
            ObjectCalls.getMethodBind("Window", "add_theme_font_size_override", ADD_THEME_FONT_SIZE_OVERRIDE_HASH)
        }

        private const val ADD_THEME_COLOR_OVERRIDE_HASH = 4260178595L
        private val addThemeColorOverrideBind by lazy {
            ObjectCalls.getMethodBind("Window", "add_theme_color_override", ADD_THEME_COLOR_OVERRIDE_HASH)
        }

        private const val ADD_THEME_CONSTANT_OVERRIDE_HASH = 2415702435L
        private val addThemeConstantOverrideBind by lazy {
            ObjectCalls.getMethodBind("Window", "add_theme_constant_override", ADD_THEME_CONSTANT_OVERRIDE_HASH)
        }

        private const val REMOVE_THEME_ICON_OVERRIDE_HASH = 3304788590L
        private val removeThemeIconOverrideBind by lazy {
            ObjectCalls.getMethodBind("Window", "remove_theme_icon_override", REMOVE_THEME_ICON_OVERRIDE_HASH)
        }

        private const val REMOVE_THEME_STYLEBOX_OVERRIDE_HASH = 3304788590L
        private val removeThemeStyleboxOverrideBind by lazy {
            ObjectCalls.getMethodBind("Window", "remove_theme_stylebox_override", REMOVE_THEME_STYLEBOX_OVERRIDE_HASH)
        }

        private const val REMOVE_THEME_FONT_OVERRIDE_HASH = 3304788590L
        private val removeThemeFontOverrideBind by lazy {
            ObjectCalls.getMethodBind("Window", "remove_theme_font_override", REMOVE_THEME_FONT_OVERRIDE_HASH)
        }

        private const val REMOVE_THEME_FONT_SIZE_OVERRIDE_HASH = 3304788590L
        private val removeThemeFontSizeOverrideBind by lazy {
            ObjectCalls.getMethodBind("Window", "remove_theme_font_size_override", REMOVE_THEME_FONT_SIZE_OVERRIDE_HASH)
        }

        private const val REMOVE_THEME_COLOR_OVERRIDE_HASH = 3304788590L
        private val removeThemeColorOverrideBind by lazy {
            ObjectCalls.getMethodBind("Window", "remove_theme_color_override", REMOVE_THEME_COLOR_OVERRIDE_HASH)
        }

        private const val REMOVE_THEME_CONSTANT_OVERRIDE_HASH = 3304788590L
        private val removeThemeConstantOverrideBind by lazy {
            ObjectCalls.getMethodBind("Window", "remove_theme_constant_override", REMOVE_THEME_CONSTANT_OVERRIDE_HASH)
        }

        private const val GET_THEME_ICON_HASH = 3163973443L
        private val getThemeIconBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_theme_icon", GET_THEME_ICON_HASH)
        }

        private const val GET_THEME_STYLEBOX_HASH = 604739069L
        private val getThemeStyleboxBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_theme_stylebox", GET_THEME_STYLEBOX_HASH)
        }

        private const val GET_THEME_FONT_HASH = 2826986490L
        private val getThemeFontBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_theme_font", GET_THEME_FONT_HASH)
        }

        private const val GET_THEME_FONT_SIZE_HASH = 1327056374L
        private val getThemeFontSizeBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_theme_font_size", GET_THEME_FONT_SIZE_HASH)
        }

        private const val GET_THEME_COLOR_HASH = 2798751242L
        private val getThemeColorBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_theme_color", GET_THEME_COLOR_HASH)
        }

        private const val GET_THEME_CONSTANT_HASH = 1327056374L
        private val getThemeConstantBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_theme_constant", GET_THEME_CONSTANT_HASH)
        }

        private const val HAS_THEME_ICON_OVERRIDE_HASH = 2619796661L
        private val hasThemeIconOverrideBind by lazy {
            ObjectCalls.getMethodBind("Window", "has_theme_icon_override", HAS_THEME_ICON_OVERRIDE_HASH)
        }

        private const val HAS_THEME_STYLEBOX_OVERRIDE_HASH = 2619796661L
        private val hasThemeStyleboxOverrideBind by lazy {
            ObjectCalls.getMethodBind("Window", "has_theme_stylebox_override", HAS_THEME_STYLEBOX_OVERRIDE_HASH)
        }

        private const val HAS_THEME_FONT_OVERRIDE_HASH = 2619796661L
        private val hasThemeFontOverrideBind by lazy {
            ObjectCalls.getMethodBind("Window", "has_theme_font_override", HAS_THEME_FONT_OVERRIDE_HASH)
        }

        private const val HAS_THEME_FONT_SIZE_OVERRIDE_HASH = 2619796661L
        private val hasThemeFontSizeOverrideBind by lazy {
            ObjectCalls.getMethodBind("Window", "has_theme_font_size_override", HAS_THEME_FONT_SIZE_OVERRIDE_HASH)
        }

        private const val HAS_THEME_COLOR_OVERRIDE_HASH = 2619796661L
        private val hasThemeColorOverrideBind by lazy {
            ObjectCalls.getMethodBind("Window", "has_theme_color_override", HAS_THEME_COLOR_OVERRIDE_HASH)
        }

        private const val HAS_THEME_CONSTANT_OVERRIDE_HASH = 2619796661L
        private val hasThemeConstantOverrideBind by lazy {
            ObjectCalls.getMethodBind("Window", "has_theme_constant_override", HAS_THEME_CONSTANT_OVERRIDE_HASH)
        }

        private const val HAS_THEME_ICON_HASH = 866386512L
        private val hasThemeIconBind by lazy {
            ObjectCalls.getMethodBind("Window", "has_theme_icon", HAS_THEME_ICON_HASH)
        }

        private const val HAS_THEME_STYLEBOX_HASH = 866386512L
        private val hasThemeStyleboxBind by lazy {
            ObjectCalls.getMethodBind("Window", "has_theme_stylebox", HAS_THEME_STYLEBOX_HASH)
        }

        private const val HAS_THEME_FONT_HASH = 866386512L
        private val hasThemeFontBind by lazy {
            ObjectCalls.getMethodBind("Window", "has_theme_font", HAS_THEME_FONT_HASH)
        }

        private const val HAS_THEME_FONT_SIZE_HASH = 866386512L
        private val hasThemeFontSizeBind by lazy {
            ObjectCalls.getMethodBind("Window", "has_theme_font_size", HAS_THEME_FONT_SIZE_HASH)
        }

        private const val HAS_THEME_COLOR_HASH = 866386512L
        private val hasThemeColorBind by lazy {
            ObjectCalls.getMethodBind("Window", "has_theme_color", HAS_THEME_COLOR_HASH)
        }

        private const val HAS_THEME_CONSTANT_HASH = 866386512L
        private val hasThemeConstantBind by lazy {
            ObjectCalls.getMethodBind("Window", "has_theme_constant", HAS_THEME_CONSTANT_HASH)
        }

        private const val GET_THEME_DEFAULT_BASE_SCALE_HASH = 1740695150L
        private val getThemeDefaultBaseScaleBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_theme_default_base_scale", GET_THEME_DEFAULT_BASE_SCALE_HASH)
        }

        private const val GET_THEME_DEFAULT_FONT_HASH = 3229501585L
        private val getThemeDefaultFontBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_theme_default_font", GET_THEME_DEFAULT_FONT_HASH)
        }

        private const val GET_THEME_DEFAULT_FONT_SIZE_HASH = 3905245786L
        private val getThemeDefaultFontSizeBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_theme_default_font_size", GET_THEME_DEFAULT_FONT_SIZE_HASH)
        }

        private const val GET_WINDOW_ID_HASH = 3905245786L
        private val getWindowIdBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_window_id", GET_WINDOW_ID_HASH)
        }

        private const val SET_ACCESSIBILITY_NAME_HASH = 83702148L
        private val setAccessibilityNameBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_accessibility_name", SET_ACCESSIBILITY_NAME_HASH)
        }

        private const val GET_ACCESSIBILITY_NAME_HASH = 201670096L
        private val getAccessibilityNameBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_accessibility_name", GET_ACCESSIBILITY_NAME_HASH)
        }

        private const val SET_ACCESSIBILITY_DESCRIPTION_HASH = 83702148L
        private val setAccessibilityDescriptionBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_accessibility_description", SET_ACCESSIBILITY_DESCRIPTION_HASH)
        }

        private const val GET_ACCESSIBILITY_DESCRIPTION_HASH = 201670096L
        private val getAccessibilityDescriptionBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_accessibility_description", GET_ACCESSIBILITY_DESCRIPTION_HASH)
        }

        private const val GET_FOCUSED_WINDOW_HASH = 1835468782L
        private val getFocusedWindowBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_focused_window", GET_FOCUSED_WINDOW_HASH)
        }

        private const val SET_LAYOUT_DIRECTION_HASH = 3094704184L
        private val setLayoutDirectionBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_layout_direction", SET_LAYOUT_DIRECTION_HASH)
        }

        private const val GET_LAYOUT_DIRECTION_HASH = 3909617982L
        private val getLayoutDirectionBind by lazy {
            ObjectCalls.getMethodBind("Window", "get_layout_direction", GET_LAYOUT_DIRECTION_HASH)
        }

        private const val IS_LAYOUT_RTL_HASH = 36873697L
        private val isLayoutRtlBind by lazy {
            ObjectCalls.getMethodBind("Window", "is_layout_rtl", IS_LAYOUT_RTL_HASH)
        }

        private const val SET_AUTO_TRANSLATE_HASH = 2586408642L
        private val setAutoTranslateBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_auto_translate", SET_AUTO_TRANSLATE_HASH)
        }

        private const val IS_AUTO_TRANSLATING_HASH = 36873697L
        private val isAutoTranslatingBind by lazy {
            ObjectCalls.getMethodBind("Window", "is_auto_translating", IS_AUTO_TRANSLATING_HASH)
        }

        private const val SET_USE_FONT_OVERSAMPLING_HASH = 2586408642L
        private val setUseFontOversamplingBind by lazy {
            ObjectCalls.getMethodBind("Window", "set_use_font_oversampling", SET_USE_FONT_OVERSAMPLING_HASH)
        }

        private const val IS_USING_FONT_OVERSAMPLING_HASH = 36873697L
        private val isUsingFontOversamplingBind by lazy {
            ObjectCalls.getMethodBind("Window", "is_using_font_oversampling", IS_USING_FONT_OVERSAMPLING_HASH)
        }

        private const val POPUP_CENTERED_HASH = 3447975422L
        private val popupCenteredBind by lazy {
            ObjectCalls.getMethodBind("Window", "popup_centered", POPUP_CENTERED_HASH)
        }

        private const val POPUP_CENTERED_RATIO_HASH = 1014814997L
        private val popupCenteredRatioBind by lazy {
            ObjectCalls.getMethodBind("Window", "popup_centered_ratio", POPUP_CENTERED_RATIO_HASH)
        }

        private const val POPUP_CENTERED_CLAMPED_HASH = 2613752477L
        private val popupCenteredClampedBind by lazy {
            ObjectCalls.getMethodBind("Window", "popup_centered_clamped", POPUP_CENTERED_CLAMPED_HASH)
        }

        private const val POPUP_EXCLUSIVE_CENTERED_HASH = 3357594017L
        private val popupExclusiveCenteredBind by lazy {
            ObjectCalls.getMethodBind("Window", "popup_exclusive_centered", POPUP_EXCLUSIVE_CENTERED_HASH)
        }

        private const val POPUP_EXCLUSIVE_CENTERED_RATIO_HASH = 2284776287L
        private val popupExclusiveCenteredRatioBind by lazy {
            ObjectCalls.getMethodBind("Window", "popup_exclusive_centered_ratio", POPUP_EXCLUSIVE_CENTERED_RATIO_HASH)
        }

        private const val POPUP_EXCLUSIVE_CENTERED_CLAMPED_HASH = 2612708785L
        private val popupExclusiveCenteredClampedBind by lazy {
            ObjectCalls.getMethodBind("Window", "popup_exclusive_centered_clamped", POPUP_EXCLUSIVE_CENTERED_CLAMPED_HASH)
        }
    }
}
