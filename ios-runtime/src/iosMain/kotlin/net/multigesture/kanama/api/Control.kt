package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: Control
 */
open class Control(handle: MemorySegment) : CanvasItem(handle) {
    var customMinimumSize: Vector2
        @JvmName("customMinimumSizeProperty")
        get() = getCustomMinimumSize()
        @JvmName("setCustomMinimumSizeProperty")
        set(value) = setCustomMinimumSize(value)

    var customMaximumSize: Vector2
        @JvmName("customMaximumSizeProperty")
        get() = getCustomMaximumSize()
        @JvmName("setCustomMaximumSizeProperty")
        set(value) = setCustomMaximumSize(value)

    var propagateMaximumSize: Boolean
        @JvmName("propagateMaximumSizeProperty")
        get() = isPropagatingMaximumSize()
        @JvmName("setPropagateMaximumSizeProperty")
        set(value) = setPropagateMaximumSize(value)

    var clipContents: Boolean
        @JvmName("clipContentsProperty")
        get() = isClippingContents()
        @JvmName("setClipContentsProperty")
        set(value) = setClipContents(value)

    var growHorizontal: Long
        @JvmName("growHorizontalProperty")
        get() = getHGrowDirection()
        @JvmName("setGrowHorizontalProperty")
        set(value) = setHGrowDirection(value)

    var growVertical: Long
        @JvmName("growVerticalProperty")
        get() = getVGrowDirection()
        @JvmName("setGrowVerticalProperty")
        set(value) = setVGrowDirection(value)

    var size: Vector2
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    var position: Vector2
        @JvmName("positionProperty")
        get() = getPosition()
        @JvmName("setPositionProperty")
        set(value) = setPosition(value)

    var globalPosition: Vector2
        @JvmName("globalPositionProperty")
        get() = getGlobalPosition()
        @JvmName("setGlobalPositionProperty")
        set(value) = setGlobalPosition(value)

    var rotation: Double
        @JvmName("rotationProperty")
        get() = getRotation()
        @JvmName("setRotationProperty")
        set(value) = setRotation(value)

    var rotationDegrees: Double
        @JvmName("rotationDegreesProperty")
        get() = getRotationDegrees()
        @JvmName("setRotationDegreesProperty")
        set(value) = setRotationDegrees(value)

    var scale: Vector2
        @JvmName("scaleProperty")
        get() = getScale()
        @JvmName("setScaleProperty")
        set(value) = setScale(value)

    var pivotOffset: Vector2
        @JvmName("pivotOffsetProperty")
        get() = getPivotOffset()
        @JvmName("setPivotOffsetProperty")
        set(value) = setPivotOffset(value)

    var pivotOffsetRatio: Vector2
        @JvmName("pivotOffsetRatioProperty")
        get() = getPivotOffsetRatio()
        @JvmName("setPivotOffsetRatioProperty")
        set(value) = setPivotOffsetRatio(value)

    var sizeFlagsHorizontal: Long
        @JvmName("sizeFlagsHorizontalProperty")
        get() = getHSizeFlags()
        @JvmName("setSizeFlagsHorizontalProperty")
        set(value) = setHSizeFlags(value)

    var sizeFlagsVertical: Long
        @JvmName("sizeFlagsVerticalProperty")
        get() = getVSizeFlags()
        @JvmName("setSizeFlagsVerticalProperty")
        set(value) = setVSizeFlags(value)

    var sizeFlagsStretchRatio: Double
        @JvmName("sizeFlagsStretchRatioProperty")
        get() = getStretchRatio()
        @JvmName("setSizeFlagsStretchRatioProperty")
        set(value) = setStretchRatio(value)

    var offsetTransformEnabled: Boolean
        @JvmName("offsetTransformEnabledProperty")
        get() = isOffsetTransformEnabled()
        @JvmName("setOffsetTransformEnabledProperty")
        set(value) = setOffsetTransformEnabled(value)

    var offsetTransformPosition: Vector2
        @JvmName("offsetTransformPositionProperty")
        get() = getOffsetTransformPosition()
        @JvmName("setOffsetTransformPositionProperty")
        set(value) = setOffsetTransformPosition(value)

    var offsetTransformPositionRatio: Vector2
        @JvmName("offsetTransformPositionRatioProperty")
        get() = getOffsetTransformPositionRatio()
        @JvmName("setOffsetTransformPositionRatioProperty")
        set(value) = setOffsetTransformPositionRatio(value)

    var offsetTransformScale: Vector2
        @JvmName("offsetTransformScaleProperty")
        get() = getOffsetTransformScale()
        @JvmName("setOffsetTransformScaleProperty")
        set(value) = setOffsetTransformScale(value)

    var offsetTransformRotation: Double
        @JvmName("offsetTransformRotationProperty")
        get() = getOffsetTransformRotation()
        @JvmName("setOffsetTransformRotationProperty")
        set(value) = setOffsetTransformRotation(value)

    var offsetTransformPivot: Vector2
        @JvmName("offsetTransformPivotProperty")
        get() = getOffsetTransformPivot()
        @JvmName("setOffsetTransformPivotProperty")
        set(value) = setOffsetTransformPivot(value)

    var offsetTransformPivotRatio: Vector2
        @JvmName("offsetTransformPivotRatioProperty")
        get() = getOffsetTransformPivotRatio()
        @JvmName("setOffsetTransformPivotRatioProperty")
        set(value) = setOffsetTransformPivotRatio(value)

    var offsetTransformVisualOnly: Boolean
        @JvmName("offsetTransformVisualOnlyProperty")
        get() = isOffsetTransformVisualOnly()
        @JvmName("setOffsetTransformVisualOnlyProperty")
        set(value) = setOffsetTransformVisualOnly(value)

    var localizeNumeralSystem: Boolean
        @JvmName("localizeNumeralSystemProperty")
        get() = isLocalizingNumeralSystem()
        @JvmName("setLocalizeNumeralSystemProperty")
        set(value) = setLocalizeNumeralSystem(value)

    var layoutDirection: Long
        @JvmName("layoutDirectionProperty")
        get() = getLayoutDirection()
        @JvmName("setLayoutDirectionProperty")
        set(value) = setLayoutDirection(value)

    var translationContext: String
        @JvmName("translationContextProperty")
        get() = getTranslationContext()
        @JvmName("setTranslationContextProperty")
        set(value) = setTranslationContext(value)

    var autoTranslate: Boolean
        @JvmName("autoTranslateProperty")
        get() = isAutoTranslating()
        @JvmName("setAutoTranslateProperty")
        set(value) = setAutoTranslate(value)

    var tooltipText: String
        @JvmName("tooltipTextProperty")
        get() = getTooltipText()
        @JvmName("setTooltipTextProperty")
        set(value) = setTooltipText(value)

    var tooltipAutoTranslateMode: Long
        @JvmName("tooltipAutoTranslateModeProperty")
        get() = getTooltipAutoTranslateMode()
        @JvmName("setTooltipAutoTranslateModeProperty")
        set(value) = setTooltipAutoTranslateMode(value)

    var focusNext: NodePath
        @JvmName("focusNextProperty")
        get() = getFocusNext()
        @JvmName("setFocusNextProperty")
        set(value) = setFocusNext(value)

    var focusPrevious: NodePath
        @JvmName("focusPreviousProperty")
        get() = getFocusPrevious()
        @JvmName("setFocusPreviousProperty")
        set(value) = setFocusPrevious(value)

    var focusMode: Long
        @JvmName("focusModeProperty")
        get() = getFocusMode()
        @JvmName("setFocusModeProperty")
        set(value) = setFocusMode(value)

    var focusBehaviorRecursive: Long
        @JvmName("focusBehaviorRecursiveProperty")
        get() = getFocusBehaviorRecursive()
        @JvmName("setFocusBehaviorRecursiveProperty")
        set(value) = setFocusBehaviorRecursive(value)

    var mouseFilter: Long
        @JvmName("mouseFilterProperty")
        get() = getMouseFilter()
        @JvmName("setMouseFilterProperty")
        set(value) = setMouseFilter(value)

    var mouseBehaviorRecursive: Long
        @JvmName("mouseBehaviorRecursiveProperty")
        get() = getMouseBehaviorRecursive()
        @JvmName("setMouseBehaviorRecursiveProperty")
        set(value) = setMouseBehaviorRecursive(value)

    var mouseForcePassScrollEvents: Boolean
        @JvmName("mouseForcePassScrollEventsProperty")
        get() = isForcePassScrollEvents()
        @JvmName("setMouseForcePassScrollEventsProperty")
        set(value) = setForcePassScrollEvents(value)

    var mouseDefaultCursorShape: Long
        @JvmName("mouseDefaultCursorShapeProperty")
        get() = getDefaultCursorShape()
        @JvmName("setMouseDefaultCursorShapeProperty")
        set(value) = setDefaultCursorShape(value)

    val shortcutContext: Node?
        @JvmName("shortcutContextProperty")
        get() = getShortcutContext()

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

    var accessibilityLive: Long
        @JvmName("accessibilityLiveProperty")
        get() = getAccessibilityLive()
        @JvmName("setAccessibilityLiveProperty")
        set(value) = setAccessibilityLive(value)

    val accessibilityControlsNodes: List<NodePath>
        @JvmName("accessibilityControlsNodesProperty")
        get() = getAccessibilityControlsNodes()

    val accessibilityDescribedByNodes: List<NodePath>
        @JvmName("accessibilityDescribedByNodesProperty")
        get() = getAccessibilityDescribedByNodes()

    val accessibilityLabeledByNodes: List<NodePath>
        @JvmName("accessibilityLabeledByNodesProperty")
        get() = getAccessibilityLabeledByNodes()

    val accessibilityFlowToNodes: List<NodePath>
        @JvmName("accessibilityFlowToNodesProperty")
        get() = getAccessibilityFlowToNodes()

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

    fun acceptEvent() {
        ObjectCalls.ptrcallNoArgs(acceptEventBind, handle)
    }

    fun getMaximumSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getMaximumSizeBind, handle)
    }

    fun getCombinedMaximumSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getCombinedMaximumSizeBind, handle)
    }

    fun getMinimumSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getMinimumSizeBind, handle)
    }

    fun getCombinedMinimumSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getCombinedMinimumSizeBind, handle)
    }

    fun setPropagateMaximumSize(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPropagateMaximumSizeBind, handle, enable)
    }

    fun isPropagatingMaximumSize(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPropagatingMaximumSizeBind, handle)
    }

    fun getBoundMinimumSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getBoundMinimumSizeBind, handle)
    }

    fun setAnchorsPreset(preset: Long, keepOffsets: Boolean = false) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setAnchorsPresetBind, handle, preset, keepOffsets)
    }

    fun setOffsetsPreset(preset: Long, resizeMode: Long = 0L, margin: Int = 0) {
        ObjectCalls.ptrcallWithTwoLongAndIntArgs(setOffsetsPresetBind, handle, preset, resizeMode, margin)
    }

    fun setAnchorsAndOffsetsPreset(preset: Long, resizeMode: Long = 0L, margin: Int = 0) {
        ObjectCalls.ptrcallWithTwoLongAndIntArgs(setAnchorsAndOffsetsPresetBind, handle, preset, resizeMode, margin)
    }

    fun setAnchor(side: Long, anchor: Double, keepOffset: Boolean = false, pushOppositeAnchor: Boolean = true) {
        ObjectCalls.ptrcallWithLongDoubleTwoBoolArgs(setAnchorBind, handle, side, anchor, keepOffset, pushOppositeAnchor)
    }

    fun getAnchor(side: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getAnchorBind, handle, side)
    }

    fun setOffset(side: Long, offset: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setOffsetBind, handle, side, offset)
    }

    fun getOffset(offset: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getOffsetBind, handle, offset)
    }

    fun setAnchorAndOffset(side: Long, anchor: Double, offset: Double, pushOppositeAnchor: Boolean = false) {
        ObjectCalls.ptrcallWithLongTwoDoubleAndBoolArgs(setAnchorAndOffsetBind, handle, side, anchor, offset, pushOppositeAnchor)
    }

    fun setBegin(position: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setBeginBind, handle, position)
    }

    fun setEnd(position: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setEndBind, handle, position)
    }

    fun setPosition(position: Vector2, keepOffsets: Boolean = false) {
        ObjectCalls.ptrcallWithVector2AndBoolArg(setPositionBind, handle, position, keepOffsets)
    }

    fun setSize(size: Vector2, keepOffsets: Boolean = false) {
        ObjectCalls.ptrcallWithVector2AndBoolArg(setSizeBind, handle, size, keepOffsets)
    }

    fun resetSize() {
        ObjectCalls.ptrcallNoArgs(resetSizeBind, handle)
    }

    fun setCustomMaximumSize(size: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setCustomMaximumSizeBind, handle, size)
    }

    fun setCustomMinimumSize(size: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setCustomMinimumSizeBind, handle, size)
    }

    fun setGlobalPosition(position: Vector2, keepOffsets: Boolean = false) {
        ObjectCalls.ptrcallWithVector2AndBoolArg(setGlobalPositionBind, handle, position, keepOffsets)
    }

    fun setRotation(radians: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRotationBind, handle, radians)
    }

    fun setRotationDegrees(degrees: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRotationDegreesBind, handle, degrees)
    }

    fun setScale(scale: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setScaleBind, handle, scale)
    }

    fun setPivotOffset(pivotOffset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setPivotOffsetBind, handle, pivotOffset)
    }

    fun setPivotOffsetRatio(ratio: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setPivotOffsetRatioBind, handle, ratio)
    }

    fun getBegin(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getBeginBind, handle)
    }

    fun getEnd(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getEndBind, handle)
    }

    fun getPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getPositionBind, handle)
    }

    fun getSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getSizeBind, handle)
    }

    fun getRotation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRotationBind, handle)
    }

    fun getRotationDegrees(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRotationDegreesBind, handle)
    }

    fun getScale(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getScaleBind, handle)
    }

    fun getPivotOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getPivotOffsetBind, handle)
    }

    fun getPivotOffsetRatio(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getPivotOffsetRatioBind, handle)
    }

    fun getCombinedPivotOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getCombinedPivotOffsetBind, handle)
    }

    fun getCustomMaximumSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getCustomMaximumSizeBind, handle)
    }

    fun getCustomMinimumSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getCustomMinimumSizeBind, handle)
    }

    fun getParentAreaSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getParentAreaSizeBind, handle)
    }

    fun getGlobalPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getGlobalPositionBind, handle)
    }

    fun getScreenPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getScreenPositionBind, handle)
    }

    fun getRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getRectBind, handle)
    }

    fun getGlobalRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getGlobalRectBind, handle)
    }

    fun setFocusMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setFocusModeBind, handle, mode)
    }

    fun getFocusMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFocusModeBind, handle)
    }

    fun getFocusModeWithOverride(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFocusModeWithOverrideBind, handle)
    }

    fun setFocusBehaviorRecursive(focusBehaviorRecursive: Long) {
        ObjectCalls.ptrcallWithLongArg(setFocusBehaviorRecursiveBind, handle, focusBehaviorRecursive)
    }

    fun getFocusBehaviorRecursive(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFocusBehaviorRecursiveBind, handle)
    }

    fun hasFocus(ignoreHiddenFocus: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithBoolArgRetBool(hasFocusBind, handle, ignoreHiddenFocus)
    }

    fun grabFocus(hideFocus: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(grabFocusBind, handle, hideFocus)
    }

    fun releaseFocus() {
        ObjectCalls.ptrcallNoArgs(releaseFocusBind, handle)
    }

    fun findPrevValidFocus(): Control? {
        return Control.wrap(ObjectCalls.ptrcallNoArgsRetObject(findPrevValidFocusBind, handle))
    }

    fun findNextValidFocus(): Control? {
        return Control.wrap(ObjectCalls.ptrcallNoArgsRetObject(findNextValidFocusBind, handle))
    }

    fun findValidFocusNeighbor(side: Long): Control? {
        return Control.wrap(ObjectCalls.ptrcallWithLongArgRetObject(findValidFocusNeighborBind, handle, side))
    }

    fun setHSizeFlags(flags: Long) {
        ObjectCalls.ptrcallWithLongArg(setHSizeFlagsBind, handle, flags)
    }

    fun getHSizeFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHSizeFlagsBind, handle)
    }

    fun setStretchRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setStretchRatioBind, handle, ratio)
    }

    fun getStretchRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getStretchRatioBind, handle)
    }

    fun setVSizeFlags(flags: Long) {
        ObjectCalls.ptrcallWithLongArg(setVSizeFlagsBind, handle, flags)
    }

    fun getVSizeFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVSizeFlagsBind, handle)
    }

    fun setOffsetTransformEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setOffsetTransformEnabledBind, handle, enabled)
    }

    fun isOffsetTransformEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOffsetTransformEnabledBind, handle)
    }

    fun setOffsetTransformPosition(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetTransformPositionBind, handle, offset)
    }

    fun getOffsetTransformPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetTransformPositionBind, handle)
    }

    fun setOffsetTransformPositionRatio(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetTransformPositionRatioBind, handle, offset)
    }

    fun getOffsetTransformPositionRatio(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetTransformPositionRatioBind, handle)
    }

    fun setOffsetTransformScale(scale: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetTransformScaleBind, handle, scale)
    }

    fun getOffsetTransformScale(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetTransformScaleBind, handle)
    }

    fun setOffsetTransformRotation(rotation: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setOffsetTransformRotationBind, handle, rotation)
    }

    fun getOffsetTransformRotation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getOffsetTransformRotationBind, handle)
    }

    fun setOffsetTransformPivot(pivot: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetTransformPivotBind, handle, pivot)
    }

    fun getOffsetTransformPivot(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetTransformPivotBind, handle)
    }

    fun setOffsetTransformPivotRatio(pivot: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetTransformPivotRatioBind, handle, pivot)
    }

    fun getOffsetTransformPivotRatio(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetTransformPivotRatioBind, handle)
    }

    fun setOffsetTransformVisualOnly(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setOffsetTransformVisualOnlyBind, handle, enabled)
    }

    fun isOffsetTransformVisualOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOffsetTransformVisualOnlyBind, handle)
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

    fun getParentControl(): Control? {
        return Control.wrap(ObjectCalls.ptrcallNoArgsRetObject(getParentControlBind, handle))
    }

    fun setHGrowDirection(direction: Long) {
        ObjectCalls.ptrcallWithLongArg(setHGrowDirectionBind, handle, direction)
    }

    fun getHGrowDirection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHGrowDirectionBind, handle)
    }

    fun setVGrowDirection(direction: Long) {
        ObjectCalls.ptrcallWithLongArg(setVGrowDirectionBind, handle, direction)
    }

    fun getVGrowDirection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVGrowDirectionBind, handle)
    }

    fun setTooltipAutoTranslateMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setTooltipAutoTranslateModeBind, handle, mode)
    }

    fun getTooltipAutoTranslateMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTooltipAutoTranslateModeBind, handle)
    }

    fun setTooltipText(hint: String) {
        ObjectCalls.ptrcallWithStringArg(setTooltipTextBind, handle, hint)
    }

    fun getTooltipText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTooltipTextBind, handle)
    }

    fun getTooltip(atPosition: Vector2): String {
        return ObjectCalls.ptrcallWithVector2ArgRetString(getTooltipBind, handle, atPosition)
    }

    fun setTranslationContext(context: String) {
        ObjectCalls.ptrcallWithStringNameArg(setTranslationContextBind, handle, context)
    }

    fun getTranslationContext(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getTranslationContextBind, handle)
    }

    fun setDefaultCursorShape(shape: Long) {
        ObjectCalls.ptrcallWithLongArg(setDefaultCursorShapeBind, handle, shape)
    }

    fun getDefaultCursorShape(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDefaultCursorShapeBind, handle)
    }

    fun getCursorShape(atPosition: Vector2): Long {
        return ObjectCalls.ptrcallWithVector2ArgRetLong(getCursorShapeBind, handle, atPosition)
    }

    fun setFocusNeighbor(side: Long, neighbor: NodePath) {
        ObjectCalls.ptrcallWithLongAndNodePathArg(setFocusNeighborBind, handle, side, neighbor)
    }

    fun getFocusNeighbor(side: Long): NodePath {
        return ObjectCalls.ptrcallWithLongArgRetNodePath(getFocusNeighborBind, handle, side)
    }

    fun setFocusNext(next: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setFocusNextBind, handle, next)
    }

    fun getFocusNext(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getFocusNextBind, handle)
    }

    fun setFocusPrevious(previous: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setFocusPreviousBind, handle, previous)
    }

    fun getFocusPrevious(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getFocusPreviousBind, handle)
    }

    fun accessibilityDrag() {
        ObjectCalls.ptrcallNoArgs(accessibilityDragBind, handle)
    }

    fun accessibilityDrop() {
        ObjectCalls.ptrcallNoArgs(accessibilityDropBind, handle)
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

    fun setAccessibilityLive(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAccessibilityLiveBind, handle, mode)
    }

    fun getAccessibilityLive(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAccessibilityLiveBind, handle)
    }

    fun getAccessibilityControlsNodes(): List<NodePath> {
        return ObjectCalls.ptrcallNoArgsRetNodePathList(getAccessibilityControlsNodesBind, handle)
    }

    fun getAccessibilityDescribedByNodes(): List<NodePath> {
        return ObjectCalls.ptrcallNoArgsRetNodePathList(getAccessibilityDescribedByNodesBind, handle)
    }

    fun getAccessibilityLabeledByNodes(): List<NodePath> {
        return ObjectCalls.ptrcallNoArgsRetNodePathList(getAccessibilityLabeledByNodesBind, handle)
    }

    fun getAccessibilityFlowToNodes(): List<NodePath> {
        return ObjectCalls.ptrcallNoArgsRetNodePathList(getAccessibilityFlowToNodesBind, handle)
    }

    fun setMouseFilter(filter: Long) {
        ObjectCalls.ptrcallWithLongArg(setMouseFilterBind, handle, filter)
    }

    fun getMouseFilter(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMouseFilterBind, handle)
    }

    fun getMouseFilterWithOverride(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMouseFilterWithOverrideBind, handle)
    }

    fun setMouseBehaviorRecursive(mouseBehaviorRecursive: Long) {
        ObjectCalls.ptrcallWithLongArg(setMouseBehaviorRecursiveBind, handle, mouseBehaviorRecursive)
    }

    fun getMouseBehaviorRecursive(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMouseBehaviorRecursiveBind, handle)
    }

    fun setForcePassScrollEvents(forcePassScrollEvents: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setForcePassScrollEventsBind, handle, forcePassScrollEvents)
    }

    fun isForcePassScrollEvents(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isForcePassScrollEventsBind, handle)
    }

    fun setClipContents(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setClipContentsBind, handle, enable)
    }

    fun isClippingContents(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isClippingContentsBind, handle)
    }

    fun grabClickFocus() {
        ObjectCalls.ptrcallNoArgs(grabClickFocusBind, handle)
    }

    fun setDragPreview(control: Control) {
        ObjectCalls.ptrcallWithObjectArgs(setDragPreviewBind, handle, listOf(control.handle))
    }

    fun isDragSuccessful(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDragSuccessfulBind, handle)
    }

    fun warpMouse(position: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(warpMouseBind, handle, position)
    }

    fun setShortcutContext(node: Node) {
        ObjectCalls.ptrcallWithObjectArgs(setShortcutContextBind, handle, listOf(node.handle))
    }

    fun getShortcutContext(): Node? {
        return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(getShortcutContextBind, handle))
    }

    fun updateMaximumSize() {
        ObjectCalls.ptrcallNoArgs(updateMaximumSizeBind, handle)
    }

    fun updateMinimumSize() {
        ObjectCalls.ptrcallNoArgs(updateMinimumSizeBind, handle)
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

    fun setLocalizeNumeralSystem(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setLocalizeNumeralSystemBind, handle, enable)
    }

    fun isLocalizingNumeralSystem(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLocalizingNumeralSystemBind, handle)
    }

    object Signals {
        const val resized: String = "resized"
        const val guiInput: String = "gui_input"
        const val mouseEntered: String = "mouse_entered"
        const val mouseExited: String = "mouse_exited"
        const val focusEntered: String = "focus_entered"
        const val focusExited: String = "focus_exited"
        const val sizeFlagsChanged: String = "size_flags_changed"
        const val maximumSizeChanged: String = "maximum_size_changed"
        const val minimumSizeChanged: String = "minimum_size_changed"
        const val themeChanged: String = "theme_changed"
    }

    // Callable-arg method (task 09 / Phase 1.4): object+method GodotCallable via the PT_CALLABLE
    // ptrcall path (each Callable expands to target.handle + method at the call site).
    fun setDragForwarding(dragFunc: GodotCallable, canDropFunc: GodotCallable, dropFunc: GodotCallable) {
        ObjectCalls.ptrcallWithThreeCallableArgs(setDragForwardingBind, handle, dragFunc.target.handle, dragFunc.method, canDropFunc.target.handle, canDropFunc.method, dropFunc.target.handle, dropFunc.method)
    }

    companion object {
        private const val SET_DRAG_FORWARDING_HASH = 1076571380L
        private val setDragForwardingBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_drag_forwarding", SET_DRAG_FORWARDING_HASH)
        }

        const val NOTIFICATION_RESIZED: Long = 40L
        const val NOTIFICATION_MOUSE_ENTER: Long = 41L
        const val NOTIFICATION_MOUSE_EXIT: Long = 42L
        const val NOTIFICATION_MOUSE_ENTER_SELF: Long = 60L
        const val NOTIFICATION_MOUSE_EXIT_SELF: Long = 61L
        const val NOTIFICATION_FOCUS_ENTER: Long = 43L
        const val NOTIFICATION_FOCUS_EXIT: Long = 44L
        const val NOTIFICATION_THEME_CHANGED: Long = 45L
        const val NOTIFICATION_SCROLL_BEGIN: Long = 47L
        const val NOTIFICATION_SCROLL_END: Long = 48L
        const val NOTIFICATION_LAYOUT_DIRECTION_CHANGED: Long = 49L
        const val FOCUS_NONE: Long = 0L
        const val FOCUS_CLICK: Long = 1L
        const val FOCUS_ALL: Long = 2L
        const val FOCUS_ACCESSIBILITY: Long = 3L
        const val FOCUS_BEHAVIOR_INHERITED: Long = 0L
        const val FOCUS_BEHAVIOR_DISABLED: Long = 1L
        const val FOCUS_BEHAVIOR_ENABLED: Long = 2L
        const val MOUSE_BEHAVIOR_INHERITED: Long = 0L
        const val MOUSE_BEHAVIOR_DISABLED: Long = 1L
        const val MOUSE_BEHAVIOR_ENABLED: Long = 2L
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
        const val PRESET_TOP_LEFT: Long = 0L
        const val PRESET_TOP_RIGHT: Long = 1L
        const val PRESET_BOTTOM_LEFT: Long = 2L
        const val PRESET_BOTTOM_RIGHT: Long = 3L
        const val PRESET_CENTER_LEFT: Long = 4L
        const val PRESET_CENTER_TOP: Long = 5L
        const val PRESET_CENTER_RIGHT: Long = 6L
        const val PRESET_CENTER_BOTTOM: Long = 7L
        const val PRESET_CENTER: Long = 8L
        const val PRESET_LEFT_WIDE: Long = 9L
        const val PRESET_TOP_WIDE: Long = 10L
        const val PRESET_RIGHT_WIDE: Long = 11L
        const val PRESET_BOTTOM_WIDE: Long = 12L
        const val PRESET_VCENTER_WIDE: Long = 13L
        const val PRESET_HCENTER_WIDE: Long = 14L
        const val PRESET_FULL_RECT: Long = 15L
        const val PRESET_MODE_MINSIZE: Long = 0L
        const val PRESET_MODE_KEEP_WIDTH: Long = 1L
        const val PRESET_MODE_KEEP_HEIGHT: Long = 2L
        const val PRESET_MODE_KEEP_SIZE: Long = 3L
        const val SIZE_SHRINK_BEGIN: Long = 0L
        const val SIZE_FILL: Long = 1L
        const val SIZE_EXPAND: Long = 2L
        const val SIZE_EXPAND_FILL: Long = 3L
        const val SIZE_SHRINK_CENTER: Long = 4L
        const val SIZE_SHRINK_END: Long = 8L
        const val MOUSE_FILTER_STOP: Long = 0L
        const val MOUSE_FILTER_PASS: Long = 1L
        const val MOUSE_FILTER_IGNORE: Long = 2L
        const val GROW_DIRECTION_BEGIN: Long = 0L
        const val GROW_DIRECTION_END: Long = 1L
        const val GROW_DIRECTION_BOTH: Long = 2L
        const val ANCHOR_BEGIN: Long = 0L
        const val ANCHOR_END: Long = 1L
        const val LAYOUT_DIRECTION_INHERITED: Long = 0L
        const val LAYOUT_DIRECTION_APPLICATION_LOCALE: Long = 1L
        const val LAYOUT_DIRECTION_LTR: Long = 2L
        const val LAYOUT_DIRECTION_RTL: Long = 3L
        const val LAYOUT_DIRECTION_SYSTEM_LOCALE: Long = 4L
        const val LAYOUT_DIRECTION_MAX: Long = 5L
        const val LAYOUT_DIRECTION_LOCALE: Long = 1L
        const val TEXT_DIRECTION_INHERITED: Long = 3L
        const val TEXT_DIRECTION_AUTO: Long = 0L
        const val TEXT_DIRECTION_LTR: Long = 1L
        const val TEXT_DIRECTION_RTL: Long = 2L

        fun fromHandle(handle: MemorySegment): Control? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Control? =
            if (handle.address() == 0L) null else Control(handle)

        private const val ACCEPT_EVENT_HASH = 3218959716L
        private val acceptEventBind by lazy {
            ObjectCalls.getMethodBind("Control", "accept_event", ACCEPT_EVENT_HASH)
        }

        private const val GET_MAXIMUM_SIZE_HASH = 3341600327L
        private val getMaximumSizeBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_maximum_size", GET_MAXIMUM_SIZE_HASH)
        }

        private const val GET_COMBINED_MAXIMUM_SIZE_HASH = 3341600327L
        private val getCombinedMaximumSizeBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_combined_maximum_size", GET_COMBINED_MAXIMUM_SIZE_HASH)
        }

        private const val GET_MINIMUM_SIZE_HASH = 3341600327L
        private val getMinimumSizeBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_minimum_size", GET_MINIMUM_SIZE_HASH)
        }

        private const val GET_COMBINED_MINIMUM_SIZE_HASH = 3341600327L
        private val getCombinedMinimumSizeBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_combined_minimum_size", GET_COMBINED_MINIMUM_SIZE_HASH)
        }

        private const val SET_PROPAGATE_MAXIMUM_SIZE_HASH = 2586408642L
        private val setPropagateMaximumSizeBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_propagate_maximum_size", SET_PROPAGATE_MAXIMUM_SIZE_HASH)
        }

        private const val IS_PROPAGATING_MAXIMUM_SIZE_HASH = 2240911060L
        private val isPropagatingMaximumSizeBind by lazy {
            ObjectCalls.getMethodBind("Control", "is_propagating_maximum_size", IS_PROPAGATING_MAXIMUM_SIZE_HASH)
        }

        private const val GET_BOUND_MINIMUM_SIZE_HASH = 3341600327L
        private val getBoundMinimumSizeBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_bound_minimum_size", GET_BOUND_MINIMUM_SIZE_HASH)
        }

        private const val SET_ANCHORS_PRESET_HASH = 509135270L
        private val setAnchorsPresetBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_anchors_preset", SET_ANCHORS_PRESET_HASH)
        }

        private const val SET_OFFSETS_PRESET_HASH = 3724524307L
        private val setOffsetsPresetBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_offsets_preset", SET_OFFSETS_PRESET_HASH)
        }

        private const val SET_ANCHORS_AND_OFFSETS_PRESET_HASH = 3724524307L
        private val setAnchorsAndOffsetsPresetBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_anchors_and_offsets_preset", SET_ANCHORS_AND_OFFSETS_PRESET_HASH)
        }

        private const val SET_ANCHOR_HASH = 2302782885L
        private val setAnchorBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_anchor", SET_ANCHOR_HASH)
        }

        private const val GET_ANCHOR_HASH = 2869120046L
        private val getAnchorBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_anchor", GET_ANCHOR_HASH)
        }

        private const val SET_OFFSET_HASH = 4290182280L
        private val setOffsetBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_offset", SET_OFFSET_HASH)
        }

        private const val GET_OFFSET_HASH = 2869120046L
        private val getOffsetBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_offset", GET_OFFSET_HASH)
        }

        private const val SET_ANCHOR_AND_OFFSET_HASH = 4031722181L
        private val setAnchorAndOffsetBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_anchor_and_offset", SET_ANCHOR_AND_OFFSET_HASH)
        }

        private const val SET_BEGIN_HASH = 743155724L
        private val setBeginBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_begin", SET_BEGIN_HASH)
        }

        private const val SET_END_HASH = 743155724L
        private val setEndBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_end", SET_END_HASH)
        }

        private const val SET_POSITION_HASH = 2436320129L
        private val setPositionBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_position", SET_POSITION_HASH)
        }

        private const val SET_SIZE_HASH = 2436320129L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_size", SET_SIZE_HASH)
        }

        private const val RESET_SIZE_HASH = 3218959716L
        private val resetSizeBind by lazy {
            ObjectCalls.getMethodBind("Control", "reset_size", RESET_SIZE_HASH)
        }

        private const val SET_CUSTOM_MAXIMUM_SIZE_HASH = 743155724L
        private val setCustomMaximumSizeBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_custom_maximum_size", SET_CUSTOM_MAXIMUM_SIZE_HASH)
        }

        private const val SET_CUSTOM_MINIMUM_SIZE_HASH = 743155724L
        private val setCustomMinimumSizeBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_custom_minimum_size", SET_CUSTOM_MINIMUM_SIZE_HASH)
        }

        private const val SET_GLOBAL_POSITION_HASH = 2436320129L
        private val setGlobalPositionBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_global_position", SET_GLOBAL_POSITION_HASH)
        }

        private const val SET_ROTATION_HASH = 373806689L
        private val setRotationBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_rotation", SET_ROTATION_HASH)
        }

        private const val SET_ROTATION_DEGREES_HASH = 373806689L
        private val setRotationDegreesBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_rotation_degrees", SET_ROTATION_DEGREES_HASH)
        }

        private const val SET_SCALE_HASH = 743155724L
        private val setScaleBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_scale", SET_SCALE_HASH)
        }

        private const val SET_PIVOT_OFFSET_HASH = 743155724L
        private val setPivotOffsetBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_pivot_offset", SET_PIVOT_OFFSET_HASH)
        }

        private const val SET_PIVOT_OFFSET_RATIO_HASH = 743155724L
        private val setPivotOffsetRatioBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_pivot_offset_ratio", SET_PIVOT_OFFSET_RATIO_HASH)
        }

        private const val GET_BEGIN_HASH = 3341600327L
        private val getBeginBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_begin", GET_BEGIN_HASH)
        }

        private const val GET_END_HASH = 3341600327L
        private val getEndBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_end", GET_END_HASH)
        }

        private const val GET_POSITION_HASH = 3341600327L
        private val getPositionBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_position", GET_POSITION_HASH)
        }

        private const val GET_SIZE_HASH = 3341600327L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_size", GET_SIZE_HASH)
        }

        private const val GET_ROTATION_HASH = 1740695150L
        private val getRotationBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_rotation", GET_ROTATION_HASH)
        }

        private const val GET_ROTATION_DEGREES_HASH = 1740695150L
        private val getRotationDegreesBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_rotation_degrees", GET_ROTATION_DEGREES_HASH)
        }

        private const val GET_SCALE_HASH = 3341600327L
        private val getScaleBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_scale", GET_SCALE_HASH)
        }

        private const val GET_PIVOT_OFFSET_HASH = 3341600327L
        private val getPivotOffsetBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_pivot_offset", GET_PIVOT_OFFSET_HASH)
        }

        private const val GET_PIVOT_OFFSET_RATIO_HASH = 3341600327L
        private val getPivotOffsetRatioBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_pivot_offset_ratio", GET_PIVOT_OFFSET_RATIO_HASH)
        }

        private const val GET_COMBINED_PIVOT_OFFSET_HASH = 3341600327L
        private val getCombinedPivotOffsetBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_combined_pivot_offset", GET_COMBINED_PIVOT_OFFSET_HASH)
        }

        private const val GET_CUSTOM_MAXIMUM_SIZE_HASH = 3341600327L
        private val getCustomMaximumSizeBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_custom_maximum_size", GET_CUSTOM_MAXIMUM_SIZE_HASH)
        }

        private const val GET_CUSTOM_MINIMUM_SIZE_HASH = 3341600327L
        private val getCustomMinimumSizeBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_custom_minimum_size", GET_CUSTOM_MINIMUM_SIZE_HASH)
        }

        private const val GET_PARENT_AREA_SIZE_HASH = 3341600327L
        private val getParentAreaSizeBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_parent_area_size", GET_PARENT_AREA_SIZE_HASH)
        }

        private const val GET_GLOBAL_POSITION_HASH = 3341600327L
        private val getGlobalPositionBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_global_position", GET_GLOBAL_POSITION_HASH)
        }

        private const val GET_SCREEN_POSITION_HASH = 3341600327L
        private val getScreenPositionBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_screen_position", GET_SCREEN_POSITION_HASH)
        }

        private const val GET_RECT_HASH = 1639390495L
        private val getRectBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_rect", GET_RECT_HASH)
        }

        private const val GET_GLOBAL_RECT_HASH = 1639390495L
        private val getGlobalRectBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_global_rect", GET_GLOBAL_RECT_HASH)
        }

        private const val SET_FOCUS_MODE_HASH = 3232914922L
        private val setFocusModeBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_focus_mode", SET_FOCUS_MODE_HASH)
        }

        private const val GET_FOCUS_MODE_HASH = 2132829277L
        private val getFocusModeBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_focus_mode", GET_FOCUS_MODE_HASH)
        }

        private const val GET_FOCUS_MODE_WITH_OVERRIDE_HASH = 2132829277L
        private val getFocusModeWithOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_focus_mode_with_override", GET_FOCUS_MODE_WITH_OVERRIDE_HASH)
        }

        private const val SET_FOCUS_BEHAVIOR_RECURSIVE_HASH = 4256832521L
        private val setFocusBehaviorRecursiveBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_focus_behavior_recursive", SET_FOCUS_BEHAVIOR_RECURSIVE_HASH)
        }

        private const val GET_FOCUS_BEHAVIOR_RECURSIVE_HASH = 2435707181L
        private val getFocusBehaviorRecursiveBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_focus_behavior_recursive", GET_FOCUS_BEHAVIOR_RECURSIVE_HASH)
        }

        private const val HAS_FOCUS_HASH = 3302206351L
        private val hasFocusBind by lazy {
            ObjectCalls.getMethodBind("Control", "has_focus", HAS_FOCUS_HASH)
        }

        private const val GRAB_FOCUS_HASH = 107499316L
        private val grabFocusBind by lazy {
            ObjectCalls.getMethodBind("Control", "grab_focus", GRAB_FOCUS_HASH)
        }

        private const val RELEASE_FOCUS_HASH = 3218959716L
        private val releaseFocusBind by lazy {
            ObjectCalls.getMethodBind("Control", "release_focus", RELEASE_FOCUS_HASH)
        }

        private const val FIND_PREV_VALID_FOCUS_HASH = 2783021301L
        private val findPrevValidFocusBind by lazy {
            ObjectCalls.getMethodBind("Control", "find_prev_valid_focus", FIND_PREV_VALID_FOCUS_HASH)
        }

        private const val FIND_NEXT_VALID_FOCUS_HASH = 2783021301L
        private val findNextValidFocusBind by lazy {
            ObjectCalls.getMethodBind("Control", "find_next_valid_focus", FIND_NEXT_VALID_FOCUS_HASH)
        }

        private const val FIND_VALID_FOCUS_NEIGHBOR_HASH = 1543910170L
        private val findValidFocusNeighborBind by lazy {
            ObjectCalls.getMethodBind("Control", "find_valid_focus_neighbor", FIND_VALID_FOCUS_NEIGHBOR_HASH)
        }

        private const val SET_H_SIZE_FLAGS_HASH = 394851643L
        private val setHSizeFlagsBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_h_size_flags", SET_H_SIZE_FLAGS_HASH)
        }

        private const val GET_H_SIZE_FLAGS_HASH = 3781367401L
        private val getHSizeFlagsBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_h_size_flags", GET_H_SIZE_FLAGS_HASH)
        }

        private const val SET_STRETCH_RATIO_HASH = 373806689L
        private val setStretchRatioBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_stretch_ratio", SET_STRETCH_RATIO_HASH)
        }

        private const val GET_STRETCH_RATIO_HASH = 1740695150L
        private val getStretchRatioBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_stretch_ratio", GET_STRETCH_RATIO_HASH)
        }

        private const val SET_V_SIZE_FLAGS_HASH = 394851643L
        private val setVSizeFlagsBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_v_size_flags", SET_V_SIZE_FLAGS_HASH)
        }

        private const val GET_V_SIZE_FLAGS_HASH = 3781367401L
        private val getVSizeFlagsBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_v_size_flags", GET_V_SIZE_FLAGS_HASH)
        }

        private const val SET_OFFSET_TRANSFORM_ENABLED_HASH = 2586408642L
        private val setOffsetTransformEnabledBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_offset_transform_enabled", SET_OFFSET_TRANSFORM_ENABLED_HASH)
        }

        private const val IS_OFFSET_TRANSFORM_ENABLED_HASH = 36873697L
        private val isOffsetTransformEnabledBind by lazy {
            ObjectCalls.getMethodBind("Control", "is_offset_transform_enabled", IS_OFFSET_TRANSFORM_ENABLED_HASH)
        }

        private const val SET_OFFSET_TRANSFORM_POSITION_HASH = 743155724L
        private val setOffsetTransformPositionBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_offset_transform_position", SET_OFFSET_TRANSFORM_POSITION_HASH)
        }

        private const val GET_OFFSET_TRANSFORM_POSITION_HASH = 3341600327L
        private val getOffsetTransformPositionBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_offset_transform_position", GET_OFFSET_TRANSFORM_POSITION_HASH)
        }

        private const val SET_OFFSET_TRANSFORM_POSITION_RATIO_HASH = 743155724L
        private val setOffsetTransformPositionRatioBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_offset_transform_position_ratio", SET_OFFSET_TRANSFORM_POSITION_RATIO_HASH)
        }

        private const val GET_OFFSET_TRANSFORM_POSITION_RATIO_HASH = 3341600327L
        private val getOffsetTransformPositionRatioBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_offset_transform_position_ratio", GET_OFFSET_TRANSFORM_POSITION_RATIO_HASH)
        }

        private const val SET_OFFSET_TRANSFORM_SCALE_HASH = 743155724L
        private val setOffsetTransformScaleBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_offset_transform_scale", SET_OFFSET_TRANSFORM_SCALE_HASH)
        }

        private const val GET_OFFSET_TRANSFORM_SCALE_HASH = 3341600327L
        private val getOffsetTransformScaleBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_offset_transform_scale", GET_OFFSET_TRANSFORM_SCALE_HASH)
        }

        private const val SET_OFFSET_TRANSFORM_ROTATION_HASH = 373806689L
        private val setOffsetTransformRotationBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_offset_transform_rotation", SET_OFFSET_TRANSFORM_ROTATION_HASH)
        }

        private const val GET_OFFSET_TRANSFORM_ROTATION_HASH = 1740695150L
        private val getOffsetTransformRotationBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_offset_transform_rotation", GET_OFFSET_TRANSFORM_ROTATION_HASH)
        }

        private const val SET_OFFSET_TRANSFORM_PIVOT_HASH = 743155724L
        private val setOffsetTransformPivotBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_offset_transform_pivot", SET_OFFSET_TRANSFORM_PIVOT_HASH)
        }

        private const val GET_OFFSET_TRANSFORM_PIVOT_HASH = 3341600327L
        private val getOffsetTransformPivotBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_offset_transform_pivot", GET_OFFSET_TRANSFORM_PIVOT_HASH)
        }

        private const val SET_OFFSET_TRANSFORM_PIVOT_RATIO_HASH = 743155724L
        private val setOffsetTransformPivotRatioBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_offset_transform_pivot_ratio", SET_OFFSET_TRANSFORM_PIVOT_RATIO_HASH)
        }

        private const val GET_OFFSET_TRANSFORM_PIVOT_RATIO_HASH = 3341600327L
        private val getOffsetTransformPivotRatioBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_offset_transform_pivot_ratio", GET_OFFSET_TRANSFORM_PIVOT_RATIO_HASH)
        }

        private const val SET_OFFSET_TRANSFORM_VISUAL_ONLY_HASH = 2586408642L
        private val setOffsetTransformVisualOnlyBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_offset_transform_visual_only", SET_OFFSET_TRANSFORM_VISUAL_ONLY_HASH)
        }

        private const val IS_OFFSET_TRANSFORM_VISUAL_ONLY_HASH = 36873697L
        private val isOffsetTransformVisualOnlyBind by lazy {
            ObjectCalls.getMethodBind("Control", "is_offset_transform_visual_only", IS_OFFSET_TRANSFORM_VISUAL_ONLY_HASH)
        }

        private const val SET_THEME_HASH = 2326690814L
        private val setThemeBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_theme", SET_THEME_HASH)
        }

        private const val GET_THEME_HASH = 3846893731L
        private val getThemeBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_theme", GET_THEME_HASH)
        }

        private const val SET_THEME_TYPE_VARIATION_HASH = 3304788590L
        private val setThemeTypeVariationBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_theme_type_variation", SET_THEME_TYPE_VARIATION_HASH)
        }

        private const val GET_THEME_TYPE_VARIATION_HASH = 2002593661L
        private val getThemeTypeVariationBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_theme_type_variation", GET_THEME_TYPE_VARIATION_HASH)
        }

        private const val BEGIN_BULK_THEME_OVERRIDE_HASH = 3218959716L
        private val beginBulkThemeOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "begin_bulk_theme_override", BEGIN_BULK_THEME_OVERRIDE_HASH)
        }

        private const val END_BULK_THEME_OVERRIDE_HASH = 3218959716L
        private val endBulkThemeOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "end_bulk_theme_override", END_BULK_THEME_OVERRIDE_HASH)
        }

        private const val ADD_THEME_ICON_OVERRIDE_HASH = 1373065600L
        private val addThemeIconOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "add_theme_icon_override", ADD_THEME_ICON_OVERRIDE_HASH)
        }

        private const val ADD_THEME_STYLEBOX_OVERRIDE_HASH = 4188838905L
        private val addThemeStyleboxOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "add_theme_stylebox_override", ADD_THEME_STYLEBOX_OVERRIDE_HASH)
        }

        private const val ADD_THEME_FONT_OVERRIDE_HASH = 3518018674L
        private val addThemeFontOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "add_theme_font_override", ADD_THEME_FONT_OVERRIDE_HASH)
        }

        private const val ADD_THEME_FONT_SIZE_OVERRIDE_HASH = 2415702435L
        private val addThemeFontSizeOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "add_theme_font_size_override", ADD_THEME_FONT_SIZE_OVERRIDE_HASH)
        }

        private const val ADD_THEME_COLOR_OVERRIDE_HASH = 4260178595L
        private val addThemeColorOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "add_theme_color_override", ADD_THEME_COLOR_OVERRIDE_HASH)
        }

        private const val ADD_THEME_CONSTANT_OVERRIDE_HASH = 2415702435L
        private val addThemeConstantOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "add_theme_constant_override", ADD_THEME_CONSTANT_OVERRIDE_HASH)
        }

        private const val REMOVE_THEME_ICON_OVERRIDE_HASH = 3304788590L
        private val removeThemeIconOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "remove_theme_icon_override", REMOVE_THEME_ICON_OVERRIDE_HASH)
        }

        private const val REMOVE_THEME_STYLEBOX_OVERRIDE_HASH = 3304788590L
        private val removeThemeStyleboxOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "remove_theme_stylebox_override", REMOVE_THEME_STYLEBOX_OVERRIDE_HASH)
        }

        private const val REMOVE_THEME_FONT_OVERRIDE_HASH = 3304788590L
        private val removeThemeFontOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "remove_theme_font_override", REMOVE_THEME_FONT_OVERRIDE_HASH)
        }

        private const val REMOVE_THEME_FONT_SIZE_OVERRIDE_HASH = 3304788590L
        private val removeThemeFontSizeOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "remove_theme_font_size_override", REMOVE_THEME_FONT_SIZE_OVERRIDE_HASH)
        }

        private const val REMOVE_THEME_COLOR_OVERRIDE_HASH = 3304788590L
        private val removeThemeColorOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "remove_theme_color_override", REMOVE_THEME_COLOR_OVERRIDE_HASH)
        }

        private const val REMOVE_THEME_CONSTANT_OVERRIDE_HASH = 3304788590L
        private val removeThemeConstantOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "remove_theme_constant_override", REMOVE_THEME_CONSTANT_OVERRIDE_HASH)
        }

        private const val GET_THEME_ICON_HASH = 3163973443L
        private val getThemeIconBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_theme_icon", GET_THEME_ICON_HASH)
        }

        private const val GET_THEME_STYLEBOX_HASH = 604739069L
        private val getThemeStyleboxBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_theme_stylebox", GET_THEME_STYLEBOX_HASH)
        }

        private const val GET_THEME_FONT_HASH = 2826986490L
        private val getThemeFontBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_theme_font", GET_THEME_FONT_HASH)
        }

        private const val GET_THEME_FONT_SIZE_HASH = 1327056374L
        private val getThemeFontSizeBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_theme_font_size", GET_THEME_FONT_SIZE_HASH)
        }

        private const val GET_THEME_COLOR_HASH = 2798751242L
        private val getThemeColorBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_theme_color", GET_THEME_COLOR_HASH)
        }

        private const val GET_THEME_CONSTANT_HASH = 1327056374L
        private val getThemeConstantBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_theme_constant", GET_THEME_CONSTANT_HASH)
        }

        private const val HAS_THEME_ICON_OVERRIDE_HASH = 2619796661L
        private val hasThemeIconOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "has_theme_icon_override", HAS_THEME_ICON_OVERRIDE_HASH)
        }

        private const val HAS_THEME_STYLEBOX_OVERRIDE_HASH = 2619796661L
        private val hasThemeStyleboxOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "has_theme_stylebox_override", HAS_THEME_STYLEBOX_OVERRIDE_HASH)
        }

        private const val HAS_THEME_FONT_OVERRIDE_HASH = 2619796661L
        private val hasThemeFontOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "has_theme_font_override", HAS_THEME_FONT_OVERRIDE_HASH)
        }

        private const val HAS_THEME_FONT_SIZE_OVERRIDE_HASH = 2619796661L
        private val hasThemeFontSizeOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "has_theme_font_size_override", HAS_THEME_FONT_SIZE_OVERRIDE_HASH)
        }

        private const val HAS_THEME_COLOR_OVERRIDE_HASH = 2619796661L
        private val hasThemeColorOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "has_theme_color_override", HAS_THEME_COLOR_OVERRIDE_HASH)
        }

        private const val HAS_THEME_CONSTANT_OVERRIDE_HASH = 2619796661L
        private val hasThemeConstantOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "has_theme_constant_override", HAS_THEME_CONSTANT_OVERRIDE_HASH)
        }

        private const val HAS_THEME_ICON_HASH = 866386512L
        private val hasThemeIconBind by lazy {
            ObjectCalls.getMethodBind("Control", "has_theme_icon", HAS_THEME_ICON_HASH)
        }

        private const val HAS_THEME_STYLEBOX_HASH = 866386512L
        private val hasThemeStyleboxBind by lazy {
            ObjectCalls.getMethodBind("Control", "has_theme_stylebox", HAS_THEME_STYLEBOX_HASH)
        }

        private const val HAS_THEME_FONT_HASH = 866386512L
        private val hasThemeFontBind by lazy {
            ObjectCalls.getMethodBind("Control", "has_theme_font", HAS_THEME_FONT_HASH)
        }

        private const val HAS_THEME_FONT_SIZE_HASH = 866386512L
        private val hasThemeFontSizeBind by lazy {
            ObjectCalls.getMethodBind("Control", "has_theme_font_size", HAS_THEME_FONT_SIZE_HASH)
        }

        private const val HAS_THEME_COLOR_HASH = 866386512L
        private val hasThemeColorBind by lazy {
            ObjectCalls.getMethodBind("Control", "has_theme_color", HAS_THEME_COLOR_HASH)
        }

        private const val HAS_THEME_CONSTANT_HASH = 866386512L
        private val hasThemeConstantBind by lazy {
            ObjectCalls.getMethodBind("Control", "has_theme_constant", HAS_THEME_CONSTANT_HASH)
        }

        private const val GET_THEME_DEFAULT_BASE_SCALE_HASH = 1740695150L
        private val getThemeDefaultBaseScaleBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_theme_default_base_scale", GET_THEME_DEFAULT_BASE_SCALE_HASH)
        }

        private const val GET_THEME_DEFAULT_FONT_HASH = 3229501585L
        private val getThemeDefaultFontBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_theme_default_font", GET_THEME_DEFAULT_FONT_HASH)
        }

        private const val GET_THEME_DEFAULT_FONT_SIZE_HASH = 3905245786L
        private val getThemeDefaultFontSizeBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_theme_default_font_size", GET_THEME_DEFAULT_FONT_SIZE_HASH)
        }

        private const val GET_PARENT_CONTROL_HASH = 2783021301L
        private val getParentControlBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_parent_control", GET_PARENT_CONTROL_HASH)
        }

        private const val SET_H_GROW_DIRECTION_HASH = 2022385301L
        private val setHGrowDirectionBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_h_grow_direction", SET_H_GROW_DIRECTION_HASH)
        }

        private const val GET_H_GROW_DIRECTION_HASH = 3635610155L
        private val getHGrowDirectionBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_h_grow_direction", GET_H_GROW_DIRECTION_HASH)
        }

        private const val SET_V_GROW_DIRECTION_HASH = 2022385301L
        private val setVGrowDirectionBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_v_grow_direction", SET_V_GROW_DIRECTION_HASH)
        }

        private const val GET_V_GROW_DIRECTION_HASH = 3635610155L
        private val getVGrowDirectionBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_v_grow_direction", GET_V_GROW_DIRECTION_HASH)
        }

        private const val SET_TOOLTIP_AUTO_TRANSLATE_MODE_HASH = 776149714L
        private val setTooltipAutoTranslateModeBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_tooltip_auto_translate_mode", SET_TOOLTIP_AUTO_TRANSLATE_MODE_HASH)
        }

        private const val GET_TOOLTIP_AUTO_TRANSLATE_MODE_HASH = 2498906432L
        private val getTooltipAutoTranslateModeBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_tooltip_auto_translate_mode", GET_TOOLTIP_AUTO_TRANSLATE_MODE_HASH)
        }

        private const val SET_TOOLTIP_TEXT_HASH = 83702148L
        private val setTooltipTextBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_tooltip_text", SET_TOOLTIP_TEXT_HASH)
        }

        private const val GET_TOOLTIP_TEXT_HASH = 201670096L
        private val getTooltipTextBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_tooltip_text", GET_TOOLTIP_TEXT_HASH)
        }

        private const val GET_TOOLTIP_HASH = 2895288280L
        private val getTooltipBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_tooltip", GET_TOOLTIP_HASH)
        }

        private const val SET_TRANSLATION_CONTEXT_HASH = 3304788590L
        private val setTranslationContextBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_translation_context", SET_TRANSLATION_CONTEXT_HASH)
        }

        private const val GET_TRANSLATION_CONTEXT_HASH = 2002593661L
        private val getTranslationContextBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_translation_context", GET_TRANSLATION_CONTEXT_HASH)
        }

        private const val SET_DEFAULT_CURSOR_SHAPE_HASH = 217062046L
        private val setDefaultCursorShapeBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_default_cursor_shape", SET_DEFAULT_CURSOR_SHAPE_HASH)
        }

        private const val GET_DEFAULT_CURSOR_SHAPE_HASH = 2359535750L
        private val getDefaultCursorShapeBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_default_cursor_shape", GET_DEFAULT_CURSOR_SHAPE_HASH)
        }

        private const val GET_CURSOR_SHAPE_HASH = 1395773853L
        private val getCursorShapeBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_cursor_shape", GET_CURSOR_SHAPE_HASH)
        }

        private const val SET_FOCUS_NEIGHBOR_HASH = 2024461774L
        private val setFocusNeighborBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_focus_neighbor", SET_FOCUS_NEIGHBOR_HASH)
        }

        private const val GET_FOCUS_NEIGHBOR_HASH = 2757935761L
        private val getFocusNeighborBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_focus_neighbor", GET_FOCUS_NEIGHBOR_HASH)
        }

        private const val SET_FOCUS_NEXT_HASH = 1348162250L
        private val setFocusNextBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_focus_next", SET_FOCUS_NEXT_HASH)
        }

        private const val GET_FOCUS_NEXT_HASH = 4075236667L
        private val getFocusNextBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_focus_next", GET_FOCUS_NEXT_HASH)
        }

        private const val SET_FOCUS_PREVIOUS_HASH = 1348162250L
        private val setFocusPreviousBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_focus_previous", SET_FOCUS_PREVIOUS_HASH)
        }

        private const val GET_FOCUS_PREVIOUS_HASH = 4075236667L
        private val getFocusPreviousBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_focus_previous", GET_FOCUS_PREVIOUS_HASH)
        }

        private const val ACCESSIBILITY_DRAG_HASH = 3218959716L
        private val accessibilityDragBind by lazy {
            ObjectCalls.getMethodBind("Control", "accessibility_drag", ACCESSIBILITY_DRAG_HASH)
        }

        private const val ACCESSIBILITY_DROP_HASH = 3218959716L
        private val accessibilityDropBind by lazy {
            ObjectCalls.getMethodBind("Control", "accessibility_drop", ACCESSIBILITY_DROP_HASH)
        }

        private const val SET_ACCESSIBILITY_NAME_HASH = 83702148L
        private val setAccessibilityNameBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_accessibility_name", SET_ACCESSIBILITY_NAME_HASH)
        }

        private const val GET_ACCESSIBILITY_NAME_HASH = 201670096L
        private val getAccessibilityNameBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_accessibility_name", GET_ACCESSIBILITY_NAME_HASH)
        }

        private const val SET_ACCESSIBILITY_DESCRIPTION_HASH = 83702148L
        private val setAccessibilityDescriptionBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_accessibility_description", SET_ACCESSIBILITY_DESCRIPTION_HASH)
        }

        private const val GET_ACCESSIBILITY_DESCRIPTION_HASH = 201670096L
        private val getAccessibilityDescriptionBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_accessibility_description", GET_ACCESSIBILITY_DESCRIPTION_HASH)
        }

        private const val SET_ACCESSIBILITY_LIVE_HASH = 353443434L
        private val setAccessibilityLiveBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_accessibility_live", SET_ACCESSIBILITY_LIVE_HASH)
        }

        private const val GET_ACCESSIBILITY_LIVE_HASH = 2858591811L
        private val getAccessibilityLiveBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_accessibility_live", GET_ACCESSIBILITY_LIVE_HASH)
        }

        private const val GET_ACCESSIBILITY_CONTROLS_NODES_HASH = 3995934104L
        private val getAccessibilityControlsNodesBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_accessibility_controls_nodes", GET_ACCESSIBILITY_CONTROLS_NODES_HASH)
        }

        private const val GET_ACCESSIBILITY_DESCRIBED_BY_NODES_HASH = 3995934104L
        private val getAccessibilityDescribedByNodesBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_accessibility_described_by_nodes", GET_ACCESSIBILITY_DESCRIBED_BY_NODES_HASH)
        }

        private const val GET_ACCESSIBILITY_LABELED_BY_NODES_HASH = 3995934104L
        private val getAccessibilityLabeledByNodesBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_accessibility_labeled_by_nodes", GET_ACCESSIBILITY_LABELED_BY_NODES_HASH)
        }

        private const val GET_ACCESSIBILITY_FLOW_TO_NODES_HASH = 3995934104L
        private val getAccessibilityFlowToNodesBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_accessibility_flow_to_nodes", GET_ACCESSIBILITY_FLOW_TO_NODES_HASH)
        }

        private const val SET_MOUSE_FILTER_HASH = 3891156122L
        private val setMouseFilterBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_mouse_filter", SET_MOUSE_FILTER_HASH)
        }

        private const val GET_MOUSE_FILTER_HASH = 1572545674L
        private val getMouseFilterBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_mouse_filter", GET_MOUSE_FILTER_HASH)
        }

        private const val GET_MOUSE_FILTER_WITH_OVERRIDE_HASH = 1572545674L
        private val getMouseFilterWithOverrideBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_mouse_filter_with_override", GET_MOUSE_FILTER_WITH_OVERRIDE_HASH)
        }

        private const val SET_MOUSE_BEHAVIOR_RECURSIVE_HASH = 849284636L
        private val setMouseBehaviorRecursiveBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_mouse_behavior_recursive", SET_MOUSE_BEHAVIOR_RECURSIVE_HASH)
        }

        private const val GET_MOUSE_BEHAVIOR_RECURSIVE_HASH = 3779367402L
        private val getMouseBehaviorRecursiveBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_mouse_behavior_recursive", GET_MOUSE_BEHAVIOR_RECURSIVE_HASH)
        }

        private const val SET_FORCE_PASS_SCROLL_EVENTS_HASH = 2586408642L
        private val setForcePassScrollEventsBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_force_pass_scroll_events", SET_FORCE_PASS_SCROLL_EVENTS_HASH)
        }

        private const val IS_FORCE_PASS_SCROLL_EVENTS_HASH = 36873697L
        private val isForcePassScrollEventsBind by lazy {
            ObjectCalls.getMethodBind("Control", "is_force_pass_scroll_events", IS_FORCE_PASS_SCROLL_EVENTS_HASH)
        }

        private const val SET_CLIP_CONTENTS_HASH = 2586408642L
        private val setClipContentsBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_clip_contents", SET_CLIP_CONTENTS_HASH)
        }

        private const val IS_CLIPPING_CONTENTS_HASH = 2240911060L
        private val isClippingContentsBind by lazy {
            ObjectCalls.getMethodBind("Control", "is_clipping_contents", IS_CLIPPING_CONTENTS_HASH)
        }

        private const val GRAB_CLICK_FOCUS_HASH = 3218959716L
        private val grabClickFocusBind by lazy {
            ObjectCalls.getMethodBind("Control", "grab_click_focus", GRAB_CLICK_FOCUS_HASH)
        }

        private const val SET_DRAG_PREVIEW_HASH = 1496901182L
        private val setDragPreviewBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_drag_preview", SET_DRAG_PREVIEW_HASH)
        }

        private const val IS_DRAG_SUCCESSFUL_HASH = 36873697L
        private val isDragSuccessfulBind by lazy {
            ObjectCalls.getMethodBind("Control", "is_drag_successful", IS_DRAG_SUCCESSFUL_HASH)
        }

        private const val WARP_MOUSE_HASH = 743155724L
        private val warpMouseBind by lazy {
            ObjectCalls.getMethodBind("Control", "warp_mouse", WARP_MOUSE_HASH)
        }

        private const val SET_SHORTCUT_CONTEXT_HASH = 1078189570L
        private val setShortcutContextBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_shortcut_context", SET_SHORTCUT_CONTEXT_HASH)
        }

        private const val GET_SHORTCUT_CONTEXT_HASH = 3160264692L
        private val getShortcutContextBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_shortcut_context", GET_SHORTCUT_CONTEXT_HASH)
        }

        private const val UPDATE_MAXIMUM_SIZE_HASH = 3218959716L
        private val updateMaximumSizeBind by lazy {
            ObjectCalls.getMethodBind("Control", "update_maximum_size", UPDATE_MAXIMUM_SIZE_HASH)
        }

        private const val UPDATE_MINIMUM_SIZE_HASH = 3218959716L
        private val updateMinimumSizeBind by lazy {
            ObjectCalls.getMethodBind("Control", "update_minimum_size", UPDATE_MINIMUM_SIZE_HASH)
        }

        private const val SET_LAYOUT_DIRECTION_HASH = 3310692370L
        private val setLayoutDirectionBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_layout_direction", SET_LAYOUT_DIRECTION_HASH)
        }

        private const val GET_LAYOUT_DIRECTION_HASH = 1546772008L
        private val getLayoutDirectionBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_layout_direction", GET_LAYOUT_DIRECTION_HASH)
        }

        private const val IS_LAYOUT_RTL_HASH = 36873697L
        private val isLayoutRtlBind by lazy {
            ObjectCalls.getMethodBind("Control", "is_layout_rtl", IS_LAYOUT_RTL_HASH)
        }

        private const val SET_AUTO_TRANSLATE_HASH = 2586408642L
        private val setAutoTranslateBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_auto_translate", SET_AUTO_TRANSLATE_HASH)
        }

        private const val IS_AUTO_TRANSLATING_HASH = 36873697L
        private val isAutoTranslatingBind by lazy {
            ObjectCalls.getMethodBind("Control", "is_auto_translating", IS_AUTO_TRANSLATING_HASH)
        }

        private const val SET_LOCALIZE_NUMERAL_SYSTEM_HASH = 2586408642L
        private val setLocalizeNumeralSystemBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_localize_numeral_system", SET_LOCALIZE_NUMERAL_SYSTEM_HASH)
        }

        private const val IS_LOCALIZING_NUMERAL_SYSTEM_HASH = 36873697L
        private val isLocalizingNumeralSystemBind by lazy {
            ObjectCalls.getMethodBind("Control", "is_localizing_numeral_system", IS_LOCALIZING_NUMERAL_SYSTEM_HASH)
        }
    }
}
