package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2

/**
 * Base class for all GUI controls. Adapts its position and size based on its parent control.
 *
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

    var accessibilityControlsNodes: List<NodePath>
        @JvmName("accessibilityControlsNodesProperty")
        get() = getAccessibilityControlsNodes()
        @JvmName("setAccessibilityControlsNodesProperty")
        set(value) = setAccessibilityControlsNodes(value)

    var accessibilityDescribedByNodes: List<NodePath>
        @JvmName("accessibilityDescribedByNodesProperty")
        get() = getAccessibilityDescribedByNodes()
        @JvmName("setAccessibilityDescribedByNodesProperty")
        set(value) = setAccessibilityDescribedByNodes(value)

    var accessibilityLabeledByNodes: List<NodePath>
        @JvmName("accessibilityLabeledByNodesProperty")
        get() = getAccessibilityLabeledByNodes()
        @JvmName("setAccessibilityLabeledByNodesProperty")
        set(value) = setAccessibilityLabeledByNodes(value)

    var accessibilityFlowToNodes: List<NodePath>
        @JvmName("accessibilityFlowToNodesProperty")
        get() = getAccessibilityFlowToNodes()
        @JvmName("setAccessibilityFlowToNodesProperty")
        set(value) = setAccessibilityFlowToNodes(value)

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
     * Marks an input event as handled. Once you accept an input event, it stops propagating, even to
     * nodes listening to `Node._unhandled_input` or `Node._unhandled_key_input`. Note: This does not
     * affect the methods in `Input`, only the way events are propagated.
     *
     * Generated from Godot docs: Control.accept_event
     */
    fun acceptEvent() {
        ObjectCalls.ptrcallNoArgs(acceptEventBind, handle)
    }

    /**
     * Returns the maximum size for this control. See `custom_maximum_size`.
     *
     * Generated from Godot docs: Control.get_maximum_size
     */
    fun getMaximumSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getMaximumSizeBind, handle)
    }

    /**
     * Returns the combined maximum size from `custom_maximum_size` and `get_maximum_size`, as well as
     * the `custom_maximum_size` of this node's parent if it is a Control node with
     * `propagate_maximum_size` set to `true`.
     *
     * Generated from Godot docs: Control.get_combined_maximum_size
     */
    fun getCombinedMaximumSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getCombinedMaximumSizeBind, handle)
    }

    /**
     * Returns the minimum size for this control. See `custom_minimum_size`.
     *
     * Generated from Godot docs: Control.get_minimum_size
     */
    fun getMinimumSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getMinimumSizeBind, handle)
    }

    /**
     * Returns the combined minimum size from `custom_minimum_size` and `get_minimum_size`.
     *
     * Generated from Godot docs: Control.get_combined_minimum_size
     */
    fun getCombinedMinimumSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getCombinedMinimumSizeBind, handle)
    }

    /**
     * If `true`, this Control's children will use the value returned by `get_combined_maximum_size` in
     * their own size calculations.
     *
     * Generated from Godot docs: Control.set_propagate_maximum_size
     */
    fun setPropagateMaximumSize(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPropagateMaximumSizeBind, handle, enable)
    }

    /**
     * If `true`, this Control's children will use the value returned by `get_combined_maximum_size` in
     * their own size calculations.
     *
     * Generated from Godot docs: Control.is_propagating_maximum_size
     */
    fun isPropagatingMaximumSize(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPropagatingMaximumSizeBind, handle)
    }

    /**
     * Returns the bound value of `get_combined_minimum_size` by `get_combined_maximum_size`. This
     * value is the true minimum size of the container, as the maximum size has priority over the
     * minimum size. For example, if the combined minimum size is (100, 100) and the combined maximum
     * size is (50, 150), the bound minimum size will be (50, 100).
     *
     * Generated from Godot docs: Control.get_bound_minimum_size
     */
    fun getBoundMinimumSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getBoundMinimumSizeBind, handle)
    }

    /**
     * Sets the anchors to a `preset` from `Control.LayoutPreset` enum. This is the code equivalent to
     * using the Layout menu in the 2D editor. If `keep_offsets` is `true`, control's position will
     * also be updated.
     *
     * Generated from Godot docs: Control.set_anchors_preset
     */
    fun setAnchorsPreset(preset: Long, keepOffsets: Boolean = false) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setAnchorsPresetBind, handle, preset, keepOffsets)
    }

    /**
     * Sets the offsets to a `preset` from `Control.LayoutPreset` enum. This is the code equivalent to
     * using the Layout menu in the 2D editor. Use parameter `resize_mode` with constants from
     * `Control.LayoutPresetMode` to better determine the resulting size of the `Control`. Constant
     * size will be ignored if used with presets that change size, e.g. `PRESET_LEFT_WIDE`. Use
     * parameter `margin` to determine the gap between the `Control` and the edges.
     *
     * Generated from Godot docs: Control.set_offsets_preset
     */
    fun setOffsetsPreset(preset: Long, resizeMode: Long = 0L, margin: Int = 0) {
        ObjectCalls.ptrcallWithTwoLongAndIntArgs(setOffsetsPresetBind, handle, preset, resizeMode, margin)
    }

    /**
     * Sets both anchor preset and offset preset. See `set_anchors_preset` and `set_offsets_preset`.
     *
     * Generated from Godot docs: Control.set_anchors_and_offsets_preset
     */
    fun setAnchorsAndOffsetsPreset(preset: Long, resizeMode: Long = 0L, margin: Int = 0) {
        ObjectCalls.ptrcallWithTwoLongAndIntArgs(setAnchorsAndOffsetsPresetBind, handle, preset, resizeMode, margin)
    }

    /**
     * Sets the anchor for the specified `Side` to `anchor`. A setter method for `anchor_bottom`,
     * `anchor_left`, `anchor_right` and `anchor_top`. If `keep_offset` is `true`, offsets aren't
     * updated after this operation. If `push_opposite_anchor` is `true` and the opposite anchor
     * overlaps this anchor, the opposite one will have its value overridden. For example, when setting
     * left anchor to 1 and the right anchor has value of 0.5, the right anchor will also get value of
     * 1. If `push_opposite_anchor` was `false`, the left anchor would get value 0.5.
     *
     * Generated from Godot docs: Control.set_anchor
     */
    fun setAnchor(side: Long, anchor: Double, keepOffset: Boolean = false, pushOppositeAnchor: Boolean = true) {
        ObjectCalls.ptrcallWithLongDoubleTwoBoolArgs(setAnchorBind, handle, side, anchor, keepOffset, pushOppositeAnchor)
    }

    /**
     * Anchors the top edge of the node to the origin, the center or the end of its parent control. It
     * changes how the top offset updates when the node moves or changes size. You can use one of the
     * `Anchor` constants for convenience.
     *
     * Generated from Godot docs: Control.get_anchor
     */
    fun getAnchor(side: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getAnchorBind, handle, side)
    }

    /**
     * Distance between the node's top edge and its parent control, based on `anchor_top`. Offsets are
     * often controlled by one or multiple parent `Container` nodes, so you should not modify them
     * manually if your node is a direct child of a `Container`. Offsets update automatically when you
     * move or resize the node.
     *
     * Generated from Godot docs: Control.set_offset
     */
    fun setOffset(side: Long, offset: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setOffsetBind, handle, side, offset)
    }

    /**
     * Distance between the node's top edge and its parent control, based on `anchor_top`. Offsets are
     * often controlled by one or multiple parent `Container` nodes, so you should not modify them
     * manually if your node is a direct child of a `Container`. Offsets update automatically when you
     * move or resize the node.
     *
     * Generated from Godot docs: Control.get_offset
     */
    fun getOffset(offset: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getOffsetBind, handle, offset)
    }

    /**
     * Works the same as `set_anchor`, but instead of `keep_offset` argument and automatic update of
     * offset, it allows to set the offset yourself (see `set_offset`).
     *
     * Generated from Godot docs: Control.set_anchor_and_offset
     */
    fun setAnchorAndOffset(side: Long, anchor: Double, offset: Double, pushOppositeAnchor: Boolean = false) {
        ObjectCalls.ptrcallWithLongTwoDoubleAndBoolArgs(setAnchorAndOffsetBind, handle, side, anchor, offset, pushOppositeAnchor)
    }

    /**
     * Sets `offset_left` and `offset_top` at the same time. Equivalent of changing `position`.
     *
     * Generated from Godot docs: Control.set_begin
     */
    fun setBegin(position: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setBeginBind, handle, position)
    }

    /**
     * Sets `offset_right` and `offset_bottom` at the same time.
     *
     * Generated from Godot docs: Control.set_end
     */
    fun setEnd(position: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setEndBind, handle, position)
    }

    /**
     * Sets the `position` to given `position`. If `keep_offsets` is `true`, control's anchors will be
     * updated instead of offsets.
     *
     * Generated from Godot docs: Control.set_position
     */
    fun setPosition(position: Vector2, keepOffsets: Boolean = false) {
        ObjectCalls.ptrcallWithVector2AndBoolArg(setPositionBind, handle, position, keepOffsets)
    }

    /**
     * Sets the size (see `size`). If `keep_offsets` is `true`, control's anchors will be updated
     * instead of offsets.
     *
     * Generated from Godot docs: Control.set_size
     */
    fun setSize(size: Vector2, keepOffsets: Boolean = false) {
        ObjectCalls.ptrcallWithVector2AndBoolArg(setSizeBind, handle, size, keepOffsets)
    }

    /**
     * Resets the size to `get_combined_minimum_size`. This is equivalent to calling
     * `set_size(Vector2())` (or any size below the minimum).
     *
     * Generated from Godot docs: Control.reset_size
     */
    fun resetSize() {
        ObjectCalls.ptrcallNoArgs(resetSizeBind, handle)
    }

    /**
     * The maximum size of this Control's bounding rectangle. If set to a value greater than or equal
     * to `(0, 0)`, the node's bounding rectangle will never exceed this size. A value below `(0, 0)`
     * means there is no maximum size. Note: The final effective maximum size may be subject to parent
     * Container sizing and propagated maximum sizes. See also: `get_combined_maximum_size`. Note: Not
     * all `Control` subtypes handle a custom maximum size gracefully, which may lead to unexpected
     * behavior if the control's contents exceed this size. Note: This value has priority over
     * `custom_minimum_size`. For example, if you set `custom_maximum_size` to `(100, 100)` and
     * `custom_minimum_size` to `(200, 200)`, the resulting size will be `(100, 100)`. Note: It is
     * recommended to use `get_bound_minimum_size` instead of `get_combined_minimum_size` when using
     * this property, as the former respects maximum size limits when calculating the minimum size,
     * while the latter does not.
     *
     * Generated from Godot docs: Control.set_custom_maximum_size
     */
    fun setCustomMaximumSize(size: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setCustomMaximumSizeBind, handle, size)
    }

    /**
     * The minimum size of the node's bounding rectangle. If you set it to a value greater than `(0,
     * 0)`, the node's bounding rectangle will always have at least this size. Note that `Control`
     * nodes have their internal minimum size returned by `get_minimum_size`. It depends on the
     * control's contents, like text, textures, or style boxes. The actual minimum size is the maximum
     * value of this property and the internal minimum size (see `get_combined_minimum_size`). Note:
     * `custom_maximum_size` has priority over this property. For example, if you set
     * `custom_minimum_size` to `(200, 200)` and `custom_maximum_size` to `(100, 100)`, the resulting
     * size will be `(100, 100)`.
     *
     * Generated from Godot docs: Control.set_custom_minimum_size
     */
    fun setCustomMinimumSize(size: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setCustomMinimumSizeBind, handle, size)
    }

    /**
     * Sets the `global_position` to given `position`. If `keep_offsets` is `true`, control's anchors
     * will be updated instead of offsets.
     *
     * Generated from Godot docs: Control.set_global_position
     */
    fun setGlobalPosition(position: Vector2, keepOffsets: Boolean = false) {
        ObjectCalls.ptrcallWithVector2AndBoolArg(setGlobalPositionBind, handle, position, keepOffsets)
    }

    /**
     * The node's rotation around its pivot, in radians. See `pivot_offset` to change the pivot's
     * position. Note: This property is edited in the inspector in degrees. If you want to use degrees
     * in a script, use `rotation_degrees`.
     *
     * Generated from Godot docs: Control.set_rotation
     */
    fun setRotation(radians: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRotationBind, handle, radians)
    }

    /**
     * Helper property to access `rotation` in degrees instead of radians.
     *
     * Generated from Godot docs: Control.set_rotation_degrees
     */
    fun setRotationDegrees(degrees: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRotationDegreesBind, handle, degrees)
    }

    /**
     * The node's scale, relative to its `size`. Change this property to scale the node around its
     * `pivot_offset`. The Control's tooltip will also scale according to this value. Note: This
     * property is mainly intended to be used for animation purposes. To support multiple resolutions
     * in your project, use an appropriate viewport stretch mode as described in the documentation
     * ($DOCS_URL/tutorials/rendering/multiple_resolutions.html) instead of scaling Controls
     * individually. Note: `FontFile.oversampling` does not take `Control` `scale` into account. This
     * means that scaling up/down will cause bitmap fonts and rasterized (non-MSDF) dynamic fonts to
     * appear blurry or pixelated. To ensure text remains crisp regardless of scale, you can enable
     * MSDF font rendering by enabling
     * `ProjectSettings.gui/theme/default_font_multichannel_signed_distance_field` (applies to the
     * default project font only), or enabling Multichannel Signed Distance Field in the import options
     * of a DynamicFont for custom fonts. On system fonts,
     * `SystemFont.multichannel_signed_distance_field` can be enabled in the inspector. Note: If the
     * Control node is a child of a `Container` node, the scale will be reset to `Vector2(1, 1)` when
     * the scene is instantiated. To set the Control's scale when it's instantiated, wait for one frame
     * using `await get_tree().process_frame` then set its `scale` property.
     *
     * Generated from Godot docs: Control.set_scale
     */
    fun setScale(scale: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setScaleBind, handle, scale)
    }

    /**
     * By default, the node's pivot is its top-left corner. When you change its `rotation` or `scale`,
     * it will rotate or scale around this pivot. The actual offset is the combined value of this
     * property and `pivot_offset_ratio`.
     *
     * Generated from Godot docs: Control.set_pivot_offset
     */
    fun setPivotOffset(pivotOffset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setPivotOffsetBind, handle, pivotOffset)
    }

    /**
     * Same as `pivot_offset`, but expressed as uniform vector, where `Vector2(0, 0)` is the top-left
     * corner of this control, and `Vector2(1, 1)` is its bottom-right corner. Set this property to
     * `Vector2(0.5, 0.5)` to pivot around this control's center. The actual offset is the combined
     * value of this property and `pivot_offset`.
     *
     * Generated from Godot docs: Control.set_pivot_offset_ratio
     */
    fun setPivotOffsetRatio(ratio: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setPivotOffsetRatioBind, handle, ratio)
    }

    /**
     * Returns `offset_left` and `offset_top`. See also `position`.
     *
     * Generated from Godot docs: Control.get_begin
     */
    fun getBegin(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getBeginBind, handle)
    }

    /**
     * Returns `offset_right` and `offset_bottom`.
     *
     * Generated from Godot docs: Control.get_end
     */
    fun getEnd(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getEndBind, handle)
    }

    /**
     * The node's position, relative to its containing node. It corresponds to the rectangle's top-left
     * corner. The property is not affected by `pivot_offset`.
     *
     * Generated from Godot docs: Control.get_position
     */
    fun getPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getPositionBind, handle)
    }

    /**
     * The size of the node's bounding rectangle, in the node's coordinate system. `Container` nodes
     * update this property automatically.
     *
     * Generated from Godot docs: Control.get_size
     */
    fun getSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getSizeBind, handle)
    }

    /**
     * The node's rotation around its pivot, in radians. See `pivot_offset` to change the pivot's
     * position. Note: This property is edited in the inspector in degrees. If you want to use degrees
     * in a script, use `rotation_degrees`.
     *
     * Generated from Godot docs: Control.get_rotation
     */
    fun getRotation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRotationBind, handle)
    }

    /**
     * Helper property to access `rotation` in degrees instead of radians.
     *
     * Generated from Godot docs: Control.get_rotation_degrees
     */
    fun getRotationDegrees(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRotationDegreesBind, handle)
    }

    /**
     * The node's scale, relative to its `size`. Change this property to scale the node around its
     * `pivot_offset`. The Control's tooltip will also scale according to this value. Note: This
     * property is mainly intended to be used for animation purposes. To support multiple resolutions
     * in your project, use an appropriate viewport stretch mode as described in the documentation
     * ($DOCS_URL/tutorials/rendering/multiple_resolutions.html) instead of scaling Controls
     * individually. Note: `FontFile.oversampling` does not take `Control` `scale` into account. This
     * means that scaling up/down will cause bitmap fonts and rasterized (non-MSDF) dynamic fonts to
     * appear blurry or pixelated. To ensure text remains crisp regardless of scale, you can enable
     * MSDF font rendering by enabling
     * `ProjectSettings.gui/theme/default_font_multichannel_signed_distance_field` (applies to the
     * default project font only), or enabling Multichannel Signed Distance Field in the import options
     * of a DynamicFont for custom fonts. On system fonts,
     * `SystemFont.multichannel_signed_distance_field` can be enabled in the inspector. Note: If the
     * Control node is a child of a `Container` node, the scale will be reset to `Vector2(1, 1)` when
     * the scene is instantiated. To set the Control's scale when it's instantiated, wait for one frame
     * using `await get_tree().process_frame` then set its `scale` property.
     *
     * Generated from Godot docs: Control.get_scale
     */
    fun getScale(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getScaleBind, handle)
    }

    /**
     * By default, the node's pivot is its top-left corner. When you change its `rotation` or `scale`,
     * it will rotate or scale around this pivot. The actual offset is the combined value of this
     * property and `pivot_offset_ratio`.
     *
     * Generated from Godot docs: Control.get_pivot_offset
     */
    fun getPivotOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getPivotOffsetBind, handle)
    }

    /**
     * Same as `pivot_offset`, but expressed as uniform vector, where `Vector2(0, 0)` is the top-left
     * corner of this control, and `Vector2(1, 1)` is its bottom-right corner. Set this property to
     * `Vector2(0.5, 0.5)` to pivot around this control's center. The actual offset is the combined
     * value of this property and `pivot_offset`.
     *
     * Generated from Godot docs: Control.get_pivot_offset_ratio
     */
    fun getPivotOffsetRatio(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getPivotOffsetRatioBind, handle)
    }

    /**
     * Returns the combined value of `pivot_offset` and `pivot_offset_ratio`, in pixels. The ratio is
     * multiplied by the control's size.
     *
     * Generated from Godot docs: Control.get_combined_pivot_offset
     */
    fun getCombinedPivotOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getCombinedPivotOffsetBind, handle)
    }

    /**
     * The maximum size of this Control's bounding rectangle. If set to a value greater than or equal
     * to `(0, 0)`, the node's bounding rectangle will never exceed this size. A value below `(0, 0)`
     * means there is no maximum size. Note: The final effective maximum size may be subject to parent
     * Container sizing and propagated maximum sizes. See also: `get_combined_maximum_size`. Note: Not
     * all `Control` subtypes handle a custom maximum size gracefully, which may lead to unexpected
     * behavior if the control's contents exceed this size. Note: This value has priority over
     * `custom_minimum_size`. For example, if you set `custom_maximum_size` to `(100, 100)` and
     * `custom_minimum_size` to `(200, 200)`, the resulting size will be `(100, 100)`. Note: It is
     * recommended to use `get_bound_minimum_size` instead of `get_combined_minimum_size` when using
     * this property, as the former respects maximum size limits when calculating the minimum size,
     * while the latter does not.
     *
     * Generated from Godot docs: Control.get_custom_maximum_size
     */
    fun getCustomMaximumSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getCustomMaximumSizeBind, handle)
    }

    /**
     * The minimum size of the node's bounding rectangle. If you set it to a value greater than `(0,
     * 0)`, the node's bounding rectangle will always have at least this size. Note that `Control`
     * nodes have their internal minimum size returned by `get_minimum_size`. It depends on the
     * control's contents, like text, textures, or style boxes. The actual minimum size is the maximum
     * value of this property and the internal minimum size (see `get_combined_minimum_size`). Note:
     * `custom_maximum_size` has priority over this property. For example, if you set
     * `custom_minimum_size` to `(200, 200)` and `custom_maximum_size` to `(100, 100)`, the resulting
     * size will be `(100, 100)`.
     *
     * Generated from Godot docs: Control.get_custom_minimum_size
     */
    fun getCustomMinimumSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getCustomMinimumSizeBind, handle)
    }

    /**
     * Returns the width/height occupied in the parent control.
     *
     * Generated from Godot docs: Control.get_parent_area_size
     */
    fun getParentAreaSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getParentAreaSizeBind, handle)
    }

    /**
     * The node's global position, relative to the world (usually to the `CanvasLayer`).
     *
     * Generated from Godot docs: Control.get_global_position
     */
    fun getGlobalPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getGlobalPositionBind, handle)
    }

    /**
     * Returns the position of this `Control` in global screen coordinates (i.e. taking window position
     * into account). Mostly useful for editor plugins. Equivalent to `get_screen_transform().origin`
     * (see `CanvasItem.get_screen_transform`).
     *
     * Generated from Godot docs: Control.get_screen_position
     */
    fun getScreenPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getScreenPositionBind, handle)
    }

    /**
     * Returns the position and size of the control in the coordinate system of the containing node.
     * See `position`, `scale` and `size`. Note: If `rotation` is not the default rotation, the
     * resulting size is not meaningful. Note: Setting `Viewport.gui_snap_controls_to_pixels` to `true`
     * can lead to rounding inaccuracies between the displayed control and the returned `Rect2`.
     *
     * Generated from Godot docs: Control.get_rect
     */
    fun getRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getRectBind, handle)
    }

    /**
     * Returns the position and size of the control relative to the containing canvas. See
     * `global_position` and `size`. Note: If the node itself or any parent `CanvasItem` between the
     * node and the canvas have a non default rotation or skew, the resulting size is likely not
     * meaningful. Note: Setting `Viewport.gui_snap_controls_to_pixels` to `true` can lead to rounding
     * inaccuracies between the displayed control and the returned `Rect2`.
     *
     * Generated from Godot docs: Control.get_global_rect
     */
    fun getGlobalRect(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getGlobalRectBind, handle)
    }

    /**
     * Determines which controls can be focused. Only one control can be focused at a time, and the
     * focused control will receive keyboard, gamepad, and mouse events in `_gui_input`. Use
     * `get_focus_mode_with_override` to determine if a control can grab focus, since
     * `focus_behavior_recursive` also affects it. See also `grab_focus`.
     *
     * Generated from Godot docs: Control.set_focus_mode
     */
    fun setFocusMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setFocusModeBind, handle, mode)
    }

    /**
     * Determines which controls can be focused. Only one control can be focused at a time, and the
     * focused control will receive keyboard, gamepad, and mouse events in `_gui_input`. Use
     * `get_focus_mode_with_override` to determine if a control can grab focus, since
     * `focus_behavior_recursive` also affects it. See also `grab_focus`.
     *
     * Generated from Godot docs: Control.get_focus_mode
     */
    fun getFocusMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFocusModeBind, handle)
    }

    /**
     * Returns the `focus_mode`, but takes the `focus_behavior_recursive` into account. If
     * `focus_behavior_recursive` is set to `FOCUS_BEHAVIOR_DISABLED`, or it is set to
     * `FOCUS_BEHAVIOR_INHERITED` and its ancestor is set to `FOCUS_BEHAVIOR_DISABLED`, then this
     * returns `FOCUS_NONE`.
     *
     * Generated from Godot docs: Control.get_focus_mode_with_override
     */
    fun getFocusModeWithOverride(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFocusModeWithOverrideBind, handle)
    }

    /**
     * Determines which controls can be focused together with `focus_mode`. See
     * `get_focus_mode_with_override`. Since the default behavior is `FOCUS_BEHAVIOR_INHERITED`, this
     * can be used to prevent all children controls from getting focused.
     *
     * Generated from Godot docs: Control.set_focus_behavior_recursive
     */
    fun setFocusBehaviorRecursive(focusBehaviorRecursive: Long) {
        ObjectCalls.ptrcallWithLongArg(setFocusBehaviorRecursiveBind, handle, focusBehaviorRecursive)
    }

    /**
     * Determines which controls can be focused together with `focus_mode`. See
     * `get_focus_mode_with_override`. Since the default behavior is `FOCUS_BEHAVIOR_INHERITED`, this
     * can be used to prevent all children controls from getting focused.
     *
     * Generated from Godot docs: Control.get_focus_behavior_recursive
     */
    fun getFocusBehaviorRecursive(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFocusBehaviorRecursiveBind, handle)
    }

    /**
     * Returns `true` if this is the current focused control. See `focus_mode`. If
     * `ignore_hidden_focus` is `true`, controls that have their focus hidden will always return
     * `false`. Hidden focus happens automatically when controls gain focus via mouse input, or
     * manually using `grab_focus` with `hide_focus` set to `true`.
     *
     * Generated from Godot docs: Control.has_focus
     */
    fun hasFocus(ignoreHiddenFocus: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithBoolArgRetBool(hasFocusBind, handle, ignoreHiddenFocus)
    }

    /**
     * Steal the focus from another control and become the focused control (see `focus_mode`). If
     * `hide_focus` is `true`, the control will not visually show its focused state. Has no effect for
     * `LineEdit` and `TextEdit` when `ProjectSettings.gui/common/show_focus_state_on_pointer_event` is
     * set to `Text Input Controls`, or for any control when it is set to `Always`. Note: Using this
     * method together with `Callable.call_deferred` makes it more reliable, especially when called
     * inside `Node._ready`.
     *
     * Generated from Godot docs: Control.grab_focus
     */
    fun grabFocus(hideFocus: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(grabFocusBind, handle, hideFocus)
    }

    /**
     * Give up the focus. No other control will be able to receive input.
     *
     * Generated from Godot docs: Control.release_focus
     */
    fun releaseFocus() {
        ObjectCalls.ptrcallNoArgs(releaseFocusBind, handle)
    }

    /**
     * Finds the previous (above in the tree) `Control` that can receive the focus.
     *
     * Generated from Godot docs: Control.find_prev_valid_focus
     */
    fun findPrevValidFocus(): Control? {
        return Control.wrap(ObjectCalls.ptrcallNoArgsRetObject(findPrevValidFocusBind, handle))
    }

    /**
     * Finds the next (below in the tree) `Control` that can receive the focus.
     *
     * Generated from Godot docs: Control.find_next_valid_focus
     */
    fun findNextValidFocus(): Control? {
        return Control.wrap(ObjectCalls.ptrcallNoArgsRetObject(findNextValidFocusBind, handle))
    }

    /**
     * Finds the next `Control` that can receive the focus on the specified `Side`. Note: This is
     * different from `get_focus_neighbor`, which returns the path of a specified focus neighbor.
     *
     * Generated from Godot docs: Control.find_valid_focus_neighbor
     */
    fun findValidFocusNeighbor(side: Long): Control? {
        return Control.wrap(ObjectCalls.ptrcallWithLongArgRetObject(findValidFocusNeighborBind, handle, side))
    }

    /**
     * Tells the parent `Container` nodes how they should resize and place the node on the X axis. Use
     * a combination of the `SizeFlags` constants to change the flags. See the constants to learn what
     * each does.
     *
     * Generated from Godot docs: Control.set_h_size_flags
     */
    fun setHSizeFlags(flags: Long) {
        ObjectCalls.ptrcallWithLongArg(setHSizeFlagsBind, handle, flags)
    }

    /**
     * Tells the parent `Container` nodes how they should resize and place the node on the X axis. Use
     * a combination of the `SizeFlags` constants to change the flags. See the constants to learn what
     * each does.
     *
     * Generated from Godot docs: Control.get_h_size_flags
     */
    fun getHSizeFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHSizeFlagsBind, handle)
    }

    /**
     * If the node and at least one of its neighbors uses the `SIZE_EXPAND` size flag, the parent
     * `Container` will let it take more or less space depending on this property. If this node has a
     * stretch ratio of 2 and its neighbor a ratio of 1, this node will take two thirds of the
     * available space.
     *
     * Generated from Godot docs: Control.set_stretch_ratio
     */
    fun setStretchRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setStretchRatioBind, handle, ratio)
    }

    /**
     * If the node and at least one of its neighbors uses the `SIZE_EXPAND` size flag, the parent
     * `Container` will let it take more or less space depending on this property. If this node has a
     * stretch ratio of 2 and its neighbor a ratio of 1, this node will take two thirds of the
     * available space.
     *
     * Generated from Godot docs: Control.get_stretch_ratio
     */
    fun getStretchRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getStretchRatioBind, handle)
    }

    /**
     * Tells the parent `Container` nodes how they should resize and place the node on the Y axis. Use
     * a combination of the `SizeFlags` constants to change the flags. See the constants to learn what
     * each does.
     *
     * Generated from Godot docs: Control.set_v_size_flags
     */
    fun setVSizeFlags(flags: Long) {
        ObjectCalls.ptrcallWithLongArg(setVSizeFlagsBind, handle, flags)
    }

    /**
     * Tells the parent `Container` nodes how they should resize and place the node on the Y axis. Use
     * a combination of the `SizeFlags` constants to change the flags. See the constants to learn what
     * each does.
     *
     * Generated from Godot docs: Control.get_v_size_flags
     */
    fun getVSizeFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVSizeFlagsBind, handle)
    }

    /**
     * If `true`, applies all offset transform properties. Otherwise, no offset transform is applied
     * and the properties have no effect.
     *
     * Generated from Godot docs: Control.set_offset_transform_enabled
     */
    fun setOffsetTransformEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setOffsetTransformEnabledBind, handle, enabled)
    }

    /**
     * If `true`, applies all offset transform properties. Otherwise, no offset transform is applied
     * and the properties have no effect.
     *
     * Generated from Godot docs: Control.is_offset_transform_enabled
     */
    fun isOffsetTransformEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOffsetTransformEnabledBind, handle)
    }

    /**
     * Position offset in absolute units. The final offset is the combined value of this property and
     * `offset_transform_position_ratio`. Has no effect unless `offset_transform_enabled` is `true`.
     *
     * Generated from Godot docs: Control.set_offset_transform_position
     */
    fun setOffsetTransformPosition(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetTransformPositionBind, handle, offset)
    }

    /**
     * Position offset in absolute units. The final offset is the combined value of this property and
     * `offset_transform_position_ratio`. Has no effect unless `offset_transform_enabled` is `true`.
     *
     * Generated from Godot docs: Control.get_offset_transform_position
     */
    fun getOffsetTransformPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetTransformPositionBind, handle)
    }

    /**
     * Same as `offset_transform_position` but expressed in units relative to the `Control` `size`
     * where `Vector2(0, 0)` is the top-left corner of this control, and `Vector2(1, 1)` is its
     * bottom-right corner. The final offset is the combined value of this property and
     * `offset_transform_position`. Has no effect unless `offset_transform_enabled` is `true`.
     *
     * Generated from Godot docs: Control.set_offset_transform_position_ratio
     */
    fun setOffsetTransformPositionRatio(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetTransformPositionRatioBind, handle, offset)
    }

    /**
     * Same as `offset_transform_position` but expressed in units relative to the `Control` `size`
     * where `Vector2(0, 0)` is the top-left corner of this control, and `Vector2(1, 1)` is its
     * bottom-right corner. The final offset is the combined value of this property and
     * `offset_transform_position`. Has no effect unless `offset_transform_enabled` is `true`.
     *
     * Generated from Godot docs: Control.get_offset_transform_position_ratio
     */
    fun getOffsetTransformPositionRatio(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetTransformPositionRatioBind, handle)
    }

    /**
     * Scale offset. The scale pivot is defined by `offset_transform_pivot` and
     * `offset_transform_pivot_ratio`. Has no effect unless `offset_transform_enabled` is `true`.
     *
     * Generated from Godot docs: Control.set_offset_transform_scale
     */
    fun setOffsetTransformScale(scale: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetTransformScaleBind, handle, scale)
    }

    /**
     * Scale offset. The scale pivot is defined by `offset_transform_pivot` and
     * `offset_transform_pivot_ratio`. Has no effect unless `offset_transform_enabled` is `true`.
     *
     * Generated from Godot docs: Control.get_offset_transform_scale
     */
    fun getOffsetTransformScale(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetTransformScaleBind, handle)
    }

    /**
     * Rotation offset. The rotation pivot is defined by `offset_transform_pivot` and
     * `offset_transform_pivot_ratio`. Has no effect unless `offset_transform_enabled` is `true`.
     *
     * Generated from Godot docs: Control.set_offset_transform_rotation
     */
    fun setOffsetTransformRotation(rotation: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setOffsetTransformRotationBind, handle, rotation)
    }

    /**
     * Rotation offset. The rotation pivot is defined by `offset_transform_pivot` and
     * `offset_transform_pivot_ratio`. Has no effect unless `offset_transform_enabled` is `true`.
     *
     * Generated from Godot docs: Control.get_offset_transform_rotation
     */
    fun getOffsetTransformRotation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getOffsetTransformRotationBind, handle)
    }

    /**
     * Pivot used by `offset_transform_rotation` and `offset_transform_scale` in absolute units. The
     * final pivot position is the combined value of this property and `offset_transform_pivot_ratio`.
     * Has no effect unless `offset_transform_enabled` is `true`.
     *
     * Generated from Godot docs: Control.set_offset_transform_pivot
     */
    fun setOffsetTransformPivot(pivot: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetTransformPivotBind, handle, pivot)
    }

    /**
     * Pivot used by `offset_transform_rotation` and `offset_transform_scale` in absolute units. The
     * final pivot position is the combined value of this property and `offset_transform_pivot_ratio`.
     * Has no effect unless `offset_transform_enabled` is `true`.
     *
     * Generated from Godot docs: Control.get_offset_transform_pivot
     */
    fun getOffsetTransformPivot(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetTransformPivotBind, handle)
    }

    /**
     * Same as `offset_transform_pivot` but expressed in units relative to the `Control` `size` where
     * `Vector2(0, 0)` is the top-left corner of this control, and `Vector2(1, 1)` is its bottom-right
     * corner. The final pivot position is the combined value of this property and
     * `offset_transform_pivot`. Has no effect unless `offset_transform_enabled` is `true`.
     *
     * Generated from Godot docs: Control.set_offset_transform_pivot_ratio
     */
    fun setOffsetTransformPivotRatio(pivot: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetTransformPivotRatioBind, handle, pivot)
    }

    /**
     * Same as `offset_transform_pivot` but expressed in units relative to the `Control` `size` where
     * `Vector2(0, 0)` is the top-left corner of this control, and `Vector2(1, 1)` is its bottom-right
     * corner. The final pivot position is the combined value of this property and
     * `offset_transform_pivot`. Has no effect unless `offset_transform_enabled` is `true`.
     *
     * Generated from Godot docs: Control.get_offset_transform_pivot_ratio
     */
    fun getOffsetTransformPivotRatio(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetTransformPivotRatioBind, handle)
    }

    /**
     * If `true`, the offset transforms is only applied visually and does not affect input. In other
     * words, this Control will still receive input events at its original location before the offset
     * transform is applied. If `false`, the entire transform of this Control is affected and input
     * events will register where the Control is visually. Has no effect unless
     * `offset_transform_enabled` is `true`.
     *
     * Generated from Godot docs: Control.set_offset_transform_visual_only
     */
    fun setOffsetTransformVisualOnly(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setOffsetTransformVisualOnlyBind, handle, enabled)
    }

    /**
     * If `true`, the offset transforms is only applied visually and does not affect input. In other
     * words, this Control will still receive input events at its original location before the offset
     * transform is applied. If `false`, the entire transform of this Control is affected and input
     * events will register where the Control is visually. Has no effect unless
     * `offset_transform_enabled` is `true`.
     *
     * Generated from Godot docs: Control.is_offset_transform_visual_only
     */
    fun isOffsetTransformVisualOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOffsetTransformVisualOnlyBind, handle)
    }

    /**
     * The `Theme` resource this node and all its `Control` and `Window` children use. If a child node
     * has its own `Theme` resource set, theme items are merged with child's definitions having higher
     * priority. Note: `Window` styles will have no effect unless the window is embedded.
     *
     * Generated from Godot docs: Control.set_theme
     */
    fun setTheme(theme: Theme?) {
        ObjectCalls.ptrcallWithObjectArgs(setThemeBind, handle, listOf(theme?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * The `Theme` resource this node and all its `Control` and `Window` children use. If a child node
     * has its own `Theme` resource set, theme items are merged with child's definitions having higher
     * priority. Note: `Window` styles will have no effect unless the window is embedded.
     *
     * Generated from Godot docs: Control.get_theme
     */
    fun getTheme(): Theme? {
        return Theme.wrap(ObjectCalls.ptrcallNoArgsRetObject(getThemeBind, handle))
    }

    /**
     * The name of a theme type variation used by this `Control` to look up its own theme items. When
     * empty, the class name of the node is used (e.g. `Button` for the `Button` control), as well as
     * the class names of all parent classes (in order of inheritance). When set, this property gives
     * the highest priority to the type of the specified name. This type can in turn extend another
     * type, forming a dependency chain. See `Theme.set_type_variation`. If the theme item cannot be
     * found using this type or its base types, lookup falls back on the class names. Note: To look up
     * `Control`'s own items use various `get_theme_*` methods without specifying `theme_type`. Note:
     * Theme items are looked for in the tree order, from branch to root, where each `Control` node is
     * checked for its `theme` property. The earliest match against any type/class name is returned.
     * The project-level Theme and the default Theme are checked last.
     *
     * Generated from Godot docs: Control.set_theme_type_variation
     */
    fun setThemeTypeVariation(themeType: String) {
        ObjectCalls.ptrcallWithStringNameArg(setThemeTypeVariationBind, handle, themeType)
    }

    /**
     * The name of a theme type variation used by this `Control` to look up its own theme items. When
     * empty, the class name of the node is used (e.g. `Button` for the `Button` control), as well as
     * the class names of all parent classes (in order of inheritance). When set, this property gives
     * the highest priority to the type of the specified name. This type can in turn extend another
     * type, forming a dependency chain. See `Theme.set_type_variation`. If the theme item cannot be
     * found using this type or its base types, lookup falls back on the class names. Note: To look up
     * `Control`'s own items use various `get_theme_*` methods without specifying `theme_type`. Note:
     * Theme items are looked for in the tree order, from branch to root, where each `Control` node is
     * checked for its `theme` property. The earliest match against any type/class name is returned.
     * The project-level Theme and the default Theme are checked last.
     *
     * Generated from Godot docs: Control.get_theme_type_variation
     */
    fun getThemeTypeVariation(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getThemeTypeVariationBind, handle)
    }

    /**
     * Prevents `*_theme_*_override` methods from emitting `NOTIFICATION_THEME_CHANGED` until
     * `end_bulk_theme_override` is called.
     *
     * Generated from Godot docs: Control.begin_bulk_theme_override
     */
    fun beginBulkThemeOverride() {
        ObjectCalls.ptrcallNoArgs(beginBulkThemeOverrideBind, handle)
    }

    /**
     * Ends a bulk theme override update. See `begin_bulk_theme_override`.
     *
     * Generated from Godot docs: Control.end_bulk_theme_override
     */
    fun endBulkThemeOverride() {
        ObjectCalls.ptrcallNoArgs(endBulkThemeOverrideBind, handle)
    }

    /**
     * Creates a local override for a theme icon with the specified `name`. Local overrides always take
     * precedence when fetching theme items for the control. An override can be removed with
     * `remove_theme_icon_override`. See also `get_theme_icon`.
     *
     * Generated from Godot docs: Control.add_theme_icon_override
     */
    fun addThemeIconOverride(name: String, texture: Texture2D) {
        ObjectCalls.ptrcallWithStringNameAndObjectArg(addThemeIconOverrideBind, handle, name, texture.requireOpenHandle())
    }

    /**
     * Creates a local override for a theme `StyleBox` with the specified `name`. Local overrides
     * always take precedence when fetching theme items for the control. An override can be removed
     * with `remove_theme_stylebox_override`. See also `get_theme_stylebox`.
     *
     * Generated from Godot docs: Control.add_theme_stylebox_override
     */
    fun addThemeStyleboxOverride(name: String, stylebox: StyleBox) {
        ObjectCalls.ptrcallWithStringNameAndObjectArg(addThemeStyleboxOverrideBind, handle, name, stylebox.requireOpenHandle())
    }

    /**
     * Creates a local override for a theme `Font` with the specified `name`. Local overrides always
     * take precedence when fetching theme items for the control. An override can be removed with
     * `remove_theme_font_override`. See also `get_theme_font`.
     *
     * Generated from Godot docs: Control.add_theme_font_override
     */
    fun addThemeFontOverride(name: String, font: Font) {
        ObjectCalls.ptrcallWithStringNameAndObjectArg(addThemeFontOverrideBind, handle, name, font.requireOpenHandle())
    }

    /**
     * Creates a local override for a theme font size with the specified `name`. Local overrides always
     * take precedence when fetching theme items for the control. An override can be removed with
     * `remove_theme_font_size_override`. See also `get_theme_font_size`.
     *
     * Generated from Godot docs: Control.add_theme_font_size_override
     */
    fun addThemeFontSizeOverride(name: String, fontSize: Int) {
        ObjectCalls.ptrcallWithStringNameAndIntArg(addThemeFontSizeOverrideBind, handle, name, fontSize)
    }

    /**
     * Creates a local override for a theme `Color` with the specified `name`. Local overrides always
     * take precedence when fetching theme items for the control. An override can be removed with
     * `remove_theme_color_override`. See also `get_theme_color`.
     *
     * Generated from Godot docs: Control.add_theme_color_override
     */
    fun addThemeColorOverride(name: String, color: Color) {
        ObjectCalls.ptrcallWithStringNameAndColorArg(addThemeColorOverrideBind, handle, name, color)
    }

    /**
     * Creates a local override for a theme constant with the specified `name`. Local overrides always
     * take precedence when fetching theme items for the control. An override can be removed with
     * `remove_theme_constant_override`. See also `get_theme_constant`.
     *
     * Generated from Godot docs: Control.add_theme_constant_override
     */
    fun addThemeConstantOverride(name: String, constant: Int) {
        ObjectCalls.ptrcallWithStringNameAndIntArg(addThemeConstantOverrideBind, handle, name, constant)
    }

    /**
     * Removes a local override for a theme icon with the specified `name` previously added by
     * `add_theme_icon_override` or via the Inspector dock.
     *
     * Generated from Godot docs: Control.remove_theme_icon_override
     */
    fun removeThemeIconOverride(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeThemeIconOverrideBind, handle, name)
    }

    /**
     * Removes a local override for a theme `StyleBox` with the specified `name` previously added by
     * `add_theme_stylebox_override` or via the Inspector dock.
     *
     * Generated from Godot docs: Control.remove_theme_stylebox_override
     */
    fun removeThemeStyleboxOverride(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeThemeStyleboxOverrideBind, handle, name)
    }

    /**
     * Removes a local override for a theme `Font` with the specified `name` previously added by
     * `add_theme_font_override` or via the Inspector dock.
     *
     * Generated from Godot docs: Control.remove_theme_font_override
     */
    fun removeThemeFontOverride(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeThemeFontOverrideBind, handle, name)
    }

    /**
     * Removes a local override for a theme font size with the specified `name` previously added by
     * `add_theme_font_size_override` or via the Inspector dock.
     *
     * Generated from Godot docs: Control.remove_theme_font_size_override
     */
    fun removeThemeFontSizeOverride(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeThemeFontSizeOverrideBind, handle, name)
    }

    /**
     * Removes a local override for a theme `Color` with the specified `name` previously added by
     * `add_theme_color_override` or via the Inspector dock.
     *
     * Generated from Godot docs: Control.remove_theme_color_override
     */
    fun removeThemeColorOverride(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeThemeColorOverrideBind, handle, name)
    }

    /**
     * Removes a local override for a theme constant with the specified `name` previously added by
     * `add_theme_constant_override` or via the Inspector dock.
     *
     * Generated from Godot docs: Control.remove_theme_constant_override
     */
    fun removeThemeConstantOverride(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeThemeConstantOverrideBind, handle, name)
    }

    /**
     * Returns an icon from the first matching `Theme` in the tree if that `Theme` has an icon item
     * with the specified `name` and `theme_type`. See `get_theme_color` for details.
     *
     * Generated from Godot docs: Control.get_theme_icon
     */
    fun getThemeIcon(name: String, themeType: String = ""): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithTwoStringNameArgsRetObject(getThemeIconBind, handle, name, themeType))
    }

    /**
     * Returns a `StyleBox` from the first matching `Theme` in the tree if that `Theme` has a stylebox
     * item with the specified `name` and `theme_type`. See `get_theme_color` for details.
     *
     * Generated from Godot docs: Control.get_theme_stylebox
     */
    fun getThemeStylebox(name: String, themeType: String = ""): StyleBox? {
        return StyleBox.wrap(ObjectCalls.ptrcallWithTwoStringNameArgsRetObject(getThemeStyleboxBind, handle, name, themeType))
    }

    /**
     * Returns a `Font` from the first matching `Theme` in the tree if that `Theme` has a font item
     * with the specified `name` and `theme_type`. See `get_theme_color` for details.
     *
     * Generated from Godot docs: Control.get_theme_font
     */
    fun getThemeFont(name: String, themeType: String = ""): Font? {
        return Font.wrap(ObjectCalls.ptrcallWithTwoStringNameArgsRetObject(getThemeFontBind, handle, name, themeType))
    }

    /**
     * Returns a font size from the first matching `Theme` in the tree if that `Theme` has a font size
     * item with the specified `name` and `theme_type`. See `get_theme_color` for details.
     *
     * Generated from Godot docs: Control.get_theme_font_size
     */
    fun getThemeFontSize(name: String, themeType: String = ""): Int {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetInt(getThemeFontSizeBind, handle, name, themeType)
    }

    /**
     * Returns a `Color` from the first matching `Theme` in the tree if that `Theme` has a color item
     * with the specified `name` and `theme_type`. If `theme_type` is omitted the class name of the
     * current control is used as the type, or `theme_type_variation` if it is defined. If the type is
     * a class name its parent classes are also checked, in order of inheritance. If the type is a
     * variation its base types are checked, in order of dependency, then the control's class name and
     * its parent classes are checked. For the current control its local overrides are considered first
     * (see `add_theme_color_override`), then its assigned `theme`. After the current control, each
     * parent control and its assigned `theme` are considered; controls without a `theme` assigned are
     * skipped. If no matching `Theme` is found in the tree, the custom project `Theme` (see
     * `ProjectSettings.gui/theme/custom`) and the default `Theme` are used (see `ThemeDB`).
     *
     * Generated from Godot docs: Control.get_theme_color
     */
    fun getThemeColor(name: String, themeType: String = ""): Color {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetColor(getThemeColorBind, handle, name, themeType)
    }

    /**
     * Returns a constant from the first matching `Theme` in the tree if that `Theme` has a constant
     * item with the specified `name` and `theme_type`. See `get_theme_color` for details.
     *
     * Generated from Godot docs: Control.get_theme_constant
     */
    fun getThemeConstant(name: String, themeType: String = ""): Int {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetInt(getThemeConstantBind, handle, name, themeType)
    }

    /**
     * Returns `true` if there is a local override for a theme icon with the specified `name` in this
     * `Control` node. See `add_theme_icon_override`.
     *
     * Generated from Godot docs: Control.has_theme_icon_override
     */
    fun hasThemeIconOverride(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasThemeIconOverrideBind, handle, name)
    }

    /**
     * Returns `true` if there is a local override for a theme `StyleBox` with the specified `name` in
     * this `Control` node. See `add_theme_stylebox_override`.
     *
     * Generated from Godot docs: Control.has_theme_stylebox_override
     */
    fun hasThemeStyleboxOverride(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasThemeStyleboxOverrideBind, handle, name)
    }

    /**
     * Returns `true` if there is a local override for a theme `Font` with the specified `name` in this
     * `Control` node. See `add_theme_font_override`.
     *
     * Generated from Godot docs: Control.has_theme_font_override
     */
    fun hasThemeFontOverride(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasThemeFontOverrideBind, handle, name)
    }

    /**
     * Returns `true` if there is a local override for a theme font size with the specified `name` in
     * this `Control` node. See `add_theme_font_size_override`.
     *
     * Generated from Godot docs: Control.has_theme_font_size_override
     */
    fun hasThemeFontSizeOverride(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasThemeFontSizeOverrideBind, handle, name)
    }

    /**
     * Returns `true` if there is a local override for a theme `Color` with the specified `name` in
     * this `Control` node. See `add_theme_color_override`.
     *
     * Generated from Godot docs: Control.has_theme_color_override
     */
    fun hasThemeColorOverride(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasThemeColorOverrideBind, handle, name)
    }

    /**
     * Returns `true` if there is a local override for a theme constant with the specified `name` in
     * this `Control` node. See `add_theme_constant_override`.
     *
     * Generated from Godot docs: Control.has_theme_constant_override
     */
    fun hasThemeConstantOverride(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasThemeConstantOverrideBind, handle, name)
    }

    /**
     * Returns `true` if there is a matching `Theme` in the tree that has an icon item with the
     * specified `name` and `theme_type`. See `get_theme_color` for details.
     *
     * Generated from Godot docs: Control.has_theme_icon
     */
    fun hasThemeIcon(name: String, themeType: String = ""): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasThemeIconBind, handle, name, themeType)
    }

    /**
     * Returns `true` if there is a matching `Theme` in the tree that has a stylebox item with the
     * specified `name` and `theme_type`. See `get_theme_color` for details.
     *
     * Generated from Godot docs: Control.has_theme_stylebox
     */
    fun hasThemeStylebox(name: String, themeType: String = ""): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasThemeStyleboxBind, handle, name, themeType)
    }

    /**
     * Returns `true` if there is a matching `Theme` in the tree that has a font item with the
     * specified `name` and `theme_type`. See `get_theme_color` for details.
     *
     * Generated from Godot docs: Control.has_theme_font
     */
    fun hasThemeFont(name: String, themeType: String = ""): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasThemeFontBind, handle, name, themeType)
    }

    /**
     * Returns `true` if there is a matching `Theme` in the tree that has a font size item with the
     * specified `name` and `theme_type`. See `get_theme_color` for details.
     *
     * Generated from Godot docs: Control.has_theme_font_size
     */
    fun hasThemeFontSize(name: String, themeType: String = ""): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasThemeFontSizeBind, handle, name, themeType)
    }

    /**
     * Returns `true` if there is a matching `Theme` in the tree that has a color item with the
     * specified `name` and `theme_type`. See `get_theme_color` for details.
     *
     * Generated from Godot docs: Control.has_theme_color
     */
    fun hasThemeColor(name: String, themeType: String = ""): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasThemeColorBind, handle, name, themeType)
    }

    /**
     * Returns `true` if there is a matching `Theme` in the tree that has a constant item with the
     * specified `name` and `theme_type`. See `get_theme_color` for details.
     *
     * Generated from Godot docs: Control.has_theme_constant
     */
    fun hasThemeConstant(name: String, themeType: String = ""): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(hasThemeConstantBind, handle, name, themeType)
    }

    /**
     * Returns the default base scale value from the first matching `Theme` in the tree if that `Theme`
     * has a valid `Theme.default_base_scale` value. See `get_theme_color` for details.
     *
     * Generated from Godot docs: Control.get_theme_default_base_scale
     */
    fun getThemeDefaultBaseScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getThemeDefaultBaseScaleBind, handle)
    }

    /**
     * Returns the default font from the first matching `Theme` in the tree if that `Theme` has a valid
     * `Theme.default_font` value. See `get_theme_color` for details.
     *
     * Generated from Godot docs: Control.get_theme_default_font
     */
    fun getThemeDefaultFont(): Font? {
        return Font.wrap(ObjectCalls.ptrcallNoArgsRetObject(getThemeDefaultFontBind, handle))
    }

    /**
     * Returns the default font size value from the first matching `Theme` in the tree if that `Theme`
     * has a valid `Theme.default_font_size` value. See `get_theme_color` for details.
     *
     * Generated from Godot docs: Control.get_theme_default_font_size
     */
    fun getThemeDefaultFontSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getThemeDefaultFontSizeBind, handle)
    }

    /**
     * Returns the parent control node.
     *
     * Generated from Godot docs: Control.get_parent_control
     */
    fun getParentControl(): Control? {
        return Control.wrap(ObjectCalls.ptrcallNoArgsRetObject(getParentControlBind, handle))
    }

    /**
     * Controls the direction on the horizontal axis in which the control should grow if its horizontal
     * minimum size is changed to be greater than its current size, as the control always has to be at
     * least the minimum size.
     *
     * Generated from Godot docs: Control.set_h_grow_direction
     */
    fun setHGrowDirection(direction: Long) {
        ObjectCalls.ptrcallWithLongArg(setHGrowDirectionBind, handle, direction)
    }

    /**
     * Controls the direction on the horizontal axis in which the control should grow if its horizontal
     * minimum size is changed to be greater than its current size, as the control always has to be at
     * least the minimum size.
     *
     * Generated from Godot docs: Control.get_h_grow_direction
     */
    fun getHGrowDirection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHGrowDirectionBind, handle)
    }

    /**
     * Controls the direction on the vertical axis in which the control should grow if its vertical
     * minimum size is changed to be greater than its current size, as the control always has to be at
     * least the minimum size.
     *
     * Generated from Godot docs: Control.set_v_grow_direction
     */
    fun setVGrowDirection(direction: Long) {
        ObjectCalls.ptrcallWithLongArg(setVGrowDirectionBind, handle, direction)
    }

    /**
     * Controls the direction on the vertical axis in which the control should grow if its vertical
     * minimum size is changed to be greater than its current size, as the control always has to be at
     * least the minimum size.
     *
     * Generated from Godot docs: Control.get_v_grow_direction
     */
    fun getVGrowDirection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVGrowDirectionBind, handle)
    }

    /**
     * Defines if tooltip text should automatically change to its translated version depending on the
     * current locale. Uses the same auto translate mode as this control when set to
     * `Node.AUTO_TRANSLATE_MODE_INHERIT`. Note: Tooltips customized using `_make_custom_tooltip` do
     * not use this auto translate mode automatically.
     *
     * Generated from Godot docs: Control.set_tooltip_auto_translate_mode
     */
    fun setTooltipAutoTranslateMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setTooltipAutoTranslateModeBind, handle, mode)
    }

    /**
     * Defines if tooltip text should automatically change to its translated version depending on the
     * current locale. Uses the same auto translate mode as this control when set to
     * `Node.AUTO_TRANSLATE_MODE_INHERIT`. Note: Tooltips customized using `_make_custom_tooltip` do
     * not use this auto translate mode automatically.
     *
     * Generated from Godot docs: Control.get_tooltip_auto_translate_mode
     */
    fun getTooltipAutoTranslateMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTooltipAutoTranslateModeBind, handle)
    }

    /**
     * The default tooltip text. The tooltip appears when the user's mouse cursor stays idle over this
     * control for a few moments, provided that the `mouse_filter` property is not
     * `MOUSE_FILTER_IGNORE`. The time required for the tooltip to appear can be changed with the
     * `ProjectSettings.gui/timers/tooltip_delay_sec` setting. This string is the default return value
     * of `get_tooltip`. Override `_get_tooltip` to generate tooltip text dynamically. Override
     * `_make_custom_tooltip` to customize the tooltip interface and behavior. The tooltip popup will
     * use either a default implementation, or a custom one that you can provide by overriding
     * `_make_custom_tooltip`. The default tooltip includes a `PopupPanel` and `Label` whose theme
     * properties can be customized using `Theme` methods with the `"TooltipPanel"` and
     * `"TooltipLabel"` respectively. For example:
     *
     * Generated from Godot docs: Control.set_tooltip_text
     */
    fun setTooltipText(hint: String) {
        ObjectCalls.ptrcallWithStringArg(setTooltipTextBind, handle, hint)
    }

    /**
     * The default tooltip text. The tooltip appears when the user's mouse cursor stays idle over this
     * control for a few moments, provided that the `mouse_filter` property is not
     * `MOUSE_FILTER_IGNORE`. The time required for the tooltip to appear can be changed with the
     * `ProjectSettings.gui/timers/tooltip_delay_sec` setting. This string is the default return value
     * of `get_tooltip`. Override `_get_tooltip` to generate tooltip text dynamically. Override
     * `_make_custom_tooltip` to customize the tooltip interface and behavior. The tooltip popup will
     * use either a default implementation, or a custom one that you can provide by overriding
     * `_make_custom_tooltip`. The default tooltip includes a `PopupPanel` and `Label` whose theme
     * properties can be customized using `Theme` methods with the `"TooltipPanel"` and
     * `"TooltipLabel"` respectively. For example:
     *
     * Generated from Godot docs: Control.get_tooltip_text
     */
    fun getTooltipText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTooltipTextBind, handle)
    }

    /**
     * Returns the tooltip text for the position `at_position` in the control's local coordinates,
     * which will typically appear when the cursor is resting over this control. By default, it returns
     * `tooltip_text`. You can override `_get_tooltip` to implement custom behavior for this method.
     * Note: If this method returns an empty `String` and `_make_custom_tooltip` is not overridden, no
     * tooltip is displayed.
     *
     * Generated from Godot docs: Control.get_tooltip
     */
    fun getTooltip(atPosition: Vector2 = Vector2(0f, 0f)): String {
        return ObjectCalls.ptrcallWithVector2ArgRetString(getTooltipBind, handle, atPosition)
    }

    /**
     * The translation context used when translating this control's displayed text, if it has any. Also
     * used when generating translation templates.
     *
     * Generated from Godot docs: Control.set_translation_context
     */
    fun setTranslationContext(context: String) {
        ObjectCalls.ptrcallWithStringNameArg(setTranslationContextBind, handle, context)
    }

    /**
     * The translation context used when translating this control's displayed text, if it has any. Also
     * used when generating translation templates.
     *
     * Generated from Godot docs: Control.get_translation_context
     */
    fun getTranslationContext(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getTranslationContextBind, handle)
    }

    /**
     * The default cursor shape for this control. Useful for Godot plugins and applications or games
     * that use the system's mouse cursors. Note: On Linux, shapes may vary depending on the cursor
     * theme of the system.
     *
     * Generated from Godot docs: Control.set_default_cursor_shape
     */
    fun setDefaultCursorShape(shape: Long) {
        ObjectCalls.ptrcallWithLongArg(setDefaultCursorShapeBind, handle, shape)
    }

    /**
     * The default cursor shape for this control. Useful for Godot plugins and applications or games
     * that use the system's mouse cursors. Note: On Linux, shapes may vary depending on the cursor
     * theme of the system.
     *
     * Generated from Godot docs: Control.get_default_cursor_shape
     */
    fun getDefaultCursorShape(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDefaultCursorShapeBind, handle)
    }

    /**
     * Returns the mouse cursor shape for this control when hovered over `at_position` in local
     * coordinates. For most controls, this is the same as `mouse_default_cursor_shape`, but some
     * built-in controls implement more complex logic. You can override `_get_cursor_shape` to
     * implement custom behavior for this method.
     *
     * Generated from Godot docs: Control.get_cursor_shape
     */
    fun getCursorShape(atPosition: Vector2 = Vector2(0f, 0f)): Long {
        return ObjectCalls.ptrcallWithVector2ArgRetLong(getCursorShapeBind, handle, atPosition)
    }

    /**
     * Tells Godot which node it should give focus to if the user presses the top arrow on the keyboard
     * or top on a gamepad by default. You can change the key by editing the
     * `ProjectSettings.input/ui_up` input action. The node must be a `Control`. If this property is
     * not set, Godot will give focus to the closest `Control` to the top of this one.
     *
     * Generated from Godot docs: Control.set_focus_neighbor
     */
    fun setFocusNeighbor(side: Long, neighbor: NodePath) {
        ObjectCalls.ptrcallWithLongAndNodePathArg(setFocusNeighborBind, handle, side, neighbor)
    }

    /**
     * Tells Godot which node it should give focus to if the user presses the top arrow on the keyboard
     * or top on a gamepad by default. You can change the key by editing the
     * `ProjectSettings.input/ui_up` input action. The node must be a `Control`. If this property is
     * not set, Godot will give focus to the closest `Control` to the top of this one.
     *
     * Generated from Godot docs: Control.get_focus_neighbor
     */
    fun getFocusNeighbor(side: Long): NodePath {
        return ObjectCalls.ptrcallWithLongArgRetNodePath(getFocusNeighborBind, handle, side)
    }

    /**
     * Tells Godot which node it should give focus to if the user presses Tab on a keyboard by default.
     * You can change the key by editing the `ProjectSettings.input/ui_focus_next` input action. If
     * this property is not set, Godot will select a "best guess" based on surrounding nodes in the
     * scene tree.
     *
     * Generated from Godot docs: Control.set_focus_next
     */
    fun setFocusNext(next: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setFocusNextBind, handle, next)
    }

    /**
     * Tells Godot which node it should give focus to if the user presses Tab on a keyboard by default.
     * You can change the key by editing the `ProjectSettings.input/ui_focus_next` input action. If
     * this property is not set, Godot will select a "best guess" based on surrounding nodes in the
     * scene tree.
     *
     * Generated from Godot docs: Control.get_focus_next
     */
    fun getFocusNext(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getFocusNextBind, handle)
    }

    /**
     * Tells Godot which node it should give focus to if the user presses Shift + Tab on a keyboard by
     * default. You can change the key by editing the `ProjectSettings.input/ui_focus_prev` input
     * action. If this property is not set, Godot will select a "best guess" based on surrounding nodes
     * in the scene tree.
     *
     * Generated from Godot docs: Control.set_focus_previous
     */
    fun setFocusPrevious(previous: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setFocusPreviousBind, handle, previous)
    }

    /**
     * Tells Godot which node it should give focus to if the user presses Shift + Tab on a keyboard by
     * default. You can change the key by editing the `ProjectSettings.input/ui_focus_prev` input
     * action. If this property is not set, Godot will select a "best guess" based on surrounding nodes
     * in the scene tree.
     *
     * Generated from Godot docs: Control.get_focus_previous
     */
    fun getFocusPrevious(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getFocusPreviousBind, handle)
    }

    /**
     * Forces drag and bypasses `_get_drag_data` and `set_drag_preview` by passing `data` and
     * `preview`. Drag will start even if the mouse is neither over nor pressed on this control. The
     * methods `_can_drop_data` and `_drop_data` must be implemented on controls that want to receive
     * drop data.
     *
     * Generated from Godot docs: Control.force_drag
     */
    fun forceDrag(data: Any?, preview: Control) {
        ObjectCalls.ptrcallWithVariantAndObjectArg(forceDragBind, handle, data, preview.handle)
    }

    /**
     * Starts drag-and-drop operation without using a mouse.
     *
     * Generated from Godot docs: Control.accessibility_drag
     */
    fun accessibilityDrag() {
        ObjectCalls.ptrcallNoArgs(accessibilityDragBind, handle)
    }

    /**
     * Ends drag-and-drop operation without using a mouse.
     *
     * Generated from Godot docs: Control.accessibility_drop
     */
    fun accessibilityDrop() {
        ObjectCalls.ptrcallNoArgs(accessibilityDropBind, handle)
    }

    /**
     * The human-readable node name that is reported to assistive apps.
     *
     * Generated from Godot docs: Control.set_accessibility_name
     */
    fun setAccessibilityName(name: String) {
        ObjectCalls.ptrcallWithStringArg(setAccessibilityNameBind, handle, name)
    }

    /**
     * The human-readable node name that is reported to assistive apps.
     *
     * Generated from Godot docs: Control.get_accessibility_name
     */
    fun getAccessibilityName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getAccessibilityNameBind, handle)
    }

    /**
     * The human-readable node description that is reported to assistive apps.
     *
     * Generated from Godot docs: Control.set_accessibility_description
     */
    fun setAccessibilityDescription(description: String) {
        ObjectCalls.ptrcallWithStringArg(setAccessibilityDescriptionBind, handle, description)
    }

    /**
     * The human-readable node description that is reported to assistive apps.
     *
     * Generated from Godot docs: Control.get_accessibility_description
     */
    fun getAccessibilityDescription(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getAccessibilityDescriptionBind, handle)
    }

    /**
     * The mode with which a live region updates. A live region is a `Node` that is updated as a result
     * of an external event when the user's focus may be elsewhere.
     *
     * Generated from Godot docs: Control.set_accessibility_live
     */
    fun setAccessibilityLive(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAccessibilityLiveBind, handle, mode)
    }

    /**
     * The mode with which a live region updates. A live region is a `Node` that is updated as a result
     * of an external event when the user's focus may be elsewhere.
     *
     * Generated from Godot docs: Control.get_accessibility_live
     */
    fun getAccessibilityLive(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAccessibilityLiveBind, handle)
    }

    /**
     * The paths to the nodes which are controlled by this node.
     *
     * Generated from Godot docs: Control.set_accessibility_controls_nodes
     */
    fun setAccessibilityControlsNodes(nodePath: List<NodePath>) {
        ObjectCalls.ptrcallWithNodePathListArg(setAccessibilityControlsNodesBind, handle, nodePath)
    }

    /**
     * The paths to the nodes which are controlled by this node.
     *
     * Generated from Godot docs: Control.get_accessibility_controls_nodes
     */
    fun getAccessibilityControlsNodes(): List<NodePath> {
        return ObjectCalls.ptrcallNoArgsRetNodePathList(getAccessibilityControlsNodesBind, handle)
    }

    /**
     * The paths to the nodes which are describing this node.
     *
     * Generated from Godot docs: Control.set_accessibility_described_by_nodes
     */
    fun setAccessibilityDescribedByNodes(nodePath: List<NodePath>) {
        ObjectCalls.ptrcallWithNodePathListArg(setAccessibilityDescribedByNodesBind, handle, nodePath)
    }

    /**
     * The paths to the nodes which are describing this node.
     *
     * Generated from Godot docs: Control.get_accessibility_described_by_nodes
     */
    fun getAccessibilityDescribedByNodes(): List<NodePath> {
        return ObjectCalls.ptrcallNoArgsRetNodePathList(getAccessibilityDescribedByNodesBind, handle)
    }

    /**
     * The paths to the nodes which label this node.
     *
     * Generated from Godot docs: Control.set_accessibility_labeled_by_nodes
     */
    fun setAccessibilityLabeledByNodes(nodePath: List<NodePath>) {
        ObjectCalls.ptrcallWithNodePathListArg(setAccessibilityLabeledByNodesBind, handle, nodePath)
    }

    /**
     * The paths to the nodes which label this node.
     *
     * Generated from Godot docs: Control.get_accessibility_labeled_by_nodes
     */
    fun getAccessibilityLabeledByNodes(): List<NodePath> {
        return ObjectCalls.ptrcallNoArgsRetNodePathList(getAccessibilityLabeledByNodesBind, handle)
    }

    /**
     * The paths to the nodes which this node flows into.
     *
     * Generated from Godot docs: Control.set_accessibility_flow_to_nodes
     */
    fun setAccessibilityFlowToNodes(nodePath: List<NodePath>) {
        ObjectCalls.ptrcallWithNodePathListArg(setAccessibilityFlowToNodesBind, handle, nodePath)
    }

    /**
     * The paths to the nodes which this node flows into.
     *
     * Generated from Godot docs: Control.get_accessibility_flow_to_nodes
     */
    fun getAccessibilityFlowToNodes(): List<NodePath> {
        return ObjectCalls.ptrcallNoArgsRetNodePathList(getAccessibilityFlowToNodesBind, handle)
    }

    /**
     * Determines which controls will be able to receive mouse button input events through `_gui_input`
     * and the `mouse_entered`, and `mouse_exited` signals. Also determines how these events should be
     * propagated. See the constants to learn what each does. Use `get_mouse_filter_with_override` to
     * determine if a control can receive mouse input, since `mouse_behavior_recursive` also affects
     * it.
     *
     * Generated from Godot docs: Control.set_mouse_filter
     */
    fun setMouseFilter(filter: Long) {
        ObjectCalls.ptrcallWithLongArg(setMouseFilterBind, handle, filter)
    }

    /**
     * Determines which controls will be able to receive mouse button input events through `_gui_input`
     * and the `mouse_entered`, and `mouse_exited` signals. Also determines how these events should be
     * propagated. See the constants to learn what each does. Use `get_mouse_filter_with_override` to
     * determine if a control can receive mouse input, since `mouse_behavior_recursive` also affects
     * it.
     *
     * Generated from Godot docs: Control.get_mouse_filter
     */
    fun getMouseFilter(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMouseFilterBind, handle)
    }

    /**
     * Returns the `mouse_filter`, but takes the `mouse_behavior_recursive` into account. If
     * `mouse_behavior_recursive` is set to `MOUSE_BEHAVIOR_DISABLED`, or it is set to
     * `MOUSE_BEHAVIOR_INHERITED` and its ancestor is set to `MOUSE_BEHAVIOR_DISABLED`, then this
     * returns `MOUSE_FILTER_IGNORE`.
     *
     * Generated from Godot docs: Control.get_mouse_filter_with_override
     */
    fun getMouseFilterWithOverride(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMouseFilterWithOverrideBind, handle)
    }

    /**
     * Determines which controls can receive mouse input together with `mouse_filter`. See
     * `get_mouse_filter_with_override`. Since the default behavior is `MOUSE_BEHAVIOR_INHERITED`, this
     * can be used to prevent all children controls from receiving mouse input.
     *
     * Generated from Godot docs: Control.set_mouse_behavior_recursive
     */
    fun setMouseBehaviorRecursive(mouseBehaviorRecursive: Long) {
        ObjectCalls.ptrcallWithLongArg(setMouseBehaviorRecursiveBind, handle, mouseBehaviorRecursive)
    }

    /**
     * Determines which controls can receive mouse input together with `mouse_filter`. See
     * `get_mouse_filter_with_override`. Since the default behavior is `MOUSE_BEHAVIOR_INHERITED`, this
     * can be used to prevent all children controls from receiving mouse input.
     *
     * Generated from Godot docs: Control.get_mouse_behavior_recursive
     */
    fun getMouseBehaviorRecursive(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMouseBehaviorRecursiveBind, handle)
    }

    /**
     * When enabled, scroll wheel events processed by `_gui_input` will be passed to the parent control
     * even if `mouse_filter` is set to `MOUSE_FILTER_STOP`. You should disable it on the root of your
     * UI if you do not want scroll events to go to the `Node._unhandled_input` processing. Note:
     * Because this property defaults to `true`, this allows nested scrollable containers to work out
     * of the box.
     *
     * Generated from Godot docs: Control.set_force_pass_scroll_events
     */
    fun setForcePassScrollEvents(forcePassScrollEvents: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setForcePassScrollEventsBind, handle, forcePassScrollEvents)
    }

    /**
     * When enabled, scroll wheel events processed by `_gui_input` will be passed to the parent control
     * even if `mouse_filter` is set to `MOUSE_FILTER_STOP`. You should disable it on the root of your
     * UI if you do not want scroll events to go to the `Node._unhandled_input` processing. Note:
     * Because this property defaults to `true`, this allows nested scrollable containers to work out
     * of the box.
     *
     * Generated from Godot docs: Control.is_force_pass_scroll_events
     */
    fun isForcePassScrollEvents(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isForcePassScrollEventsBind, handle)
    }

    /**
     * Enables whether rendering of `CanvasItem` based children should be clipped to this control's
     * rectangle. If `true`, parts of a child which would be visibly outside of this control's
     * rectangle will not be rendered and won't receive input.
     *
     * Generated from Godot docs: Control.set_clip_contents
     */
    fun setClipContents(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setClipContentsBind, handle, enable)
    }

    /**
     * Enables whether rendering of `CanvasItem` based children should be clipped to this control's
     * rectangle. If `true`, parts of a child which would be visibly outside of this control's
     * rectangle will not be rendered and won't receive input.
     *
     * Generated from Godot docs: Control.is_clipping_contents
     */
    fun isClippingContents(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isClippingContentsBind, handle)
    }

    /**
     * Creates an `InputEventMouseButton` that attempts to click the control. If the event is received,
     * the control gains focus.
     *
     * Generated from Godot docs: Control.grab_click_focus
     */
    fun grabClickFocus() {
        ObjectCalls.ptrcallNoArgs(grabClickFocusBind, handle)
    }

    /**
     * Sets the given callables to be used instead of the control's own drag-and-drop virtual methods.
     * If a callable is empty, its respective virtual method is used as normal. The arguments for each
     * callable should be exactly the same as their respective virtual methods, which would be: -
     * `drag_func` corresponds to `_get_drag_data` and requires a `Vector2`; - `can_drop_func`
     * corresponds to `_can_drop_data` and requires both a `Vector2` and a `Variant`; - `drop_func`
     * corresponds to `_drop_data` and requires both a `Vector2` and a `Variant`.
     *
     * Generated from Godot docs: Control.set_drag_forwarding
     */
    fun setDragForwarding(dragFunc: GodotCallable, canDropFunc: GodotCallable, dropFunc: GodotCallable) {
        ObjectCalls.ptrcallWithThreeCallableArgs(setDragForwardingBind, handle, dragFunc.target.handle, dragFunc.method, canDropFunc.target.handle, canDropFunc.method, dropFunc.target.handle, dropFunc.method)
    }

    /**
     * Shows the given control at the mouse pointer. A good time to call this method is in
     * `_get_drag_data`. The control must not be in the scene tree. You should not free the control,
     * and you should not keep a reference to the control beyond the duration of the drag. It will be
     * deleted automatically after the drag has ended.
     *
     * Generated from Godot docs: Control.set_drag_preview
     */
    fun setDragPreview(control: Control) {
        ObjectCalls.ptrcallWithObjectArgs(setDragPreviewBind, handle, listOf(control.handle))
    }

    /**
     * Returns `true` if a drag operation is successful. Alternative to
     * `Viewport.gui_is_drag_successful`. Best used with `Node.NOTIFICATION_DRAG_END`.
     *
     * Generated from Godot docs: Control.is_drag_successful
     */
    fun isDragSuccessful(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDragSuccessfulBind, handle)
    }

    /**
     * Moves the mouse cursor to `position`, relative to `position` of this `Control`. Note:
     * `warp_mouse` is only supported on Windows, macOS and Linux. It has no effect on Android, iOS and
     * Web.
     *
     * Generated from Godot docs: Control.warp_mouse
     */
    fun warpMouse(position: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(warpMouseBind, handle, position)
    }

    /**
     * The `Node` which must be a parent of the focused `Control` for the shortcut to be activated. If
     * `null`, the shortcut can be activated when any control is focused (a global shortcut). This
     * allows shortcuts to be accepted only when the user has a certain area of the GUI focused.
     *
     * Generated from Godot docs: Control.set_shortcut_context
     */
    fun setShortcutContext(node: Node) {
        ObjectCalls.ptrcallWithObjectArgs(setShortcutContextBind, handle, listOf(node.handle))
    }

    /**
     * The `Node` which must be a parent of the focused `Control` for the shortcut to be activated. If
     * `null`, the shortcut can be activated when any control is focused (a global shortcut). This
     * allows shortcuts to be accepted only when the user has a certain area of the GUI focused.
     *
     * Generated from Godot docs: Control.get_shortcut_context
     */
    fun getShortcutContext(): Node? {
        return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(getShortcutContextBind, handle))
    }

    /**
     * Invalidates the maximum size cache in this node and in parent nodes up to top level. Intended to
     * be used with `get_maximum_size` when the return value is changed. Setting `custom_maximum_size`
     * directly calls this method automatically. Note: Calling this method also calls
     * `update_minimum_size` since the combined minimum size may be affected by the maximum size
     * change.
     *
     * Generated from Godot docs: Control.update_maximum_size
     */
    fun updateMaximumSize() {
        ObjectCalls.ptrcallNoArgs(updateMaximumSizeBind, handle)
    }

    /**
     * Invalidates the minimum size cache in this node and in parent nodes up to top level. Intended to
     * be used with `get_minimum_size` when the return value is changed. Setting `custom_minimum_size`
     * directly calls this method automatically.
     *
     * Generated from Godot docs: Control.update_minimum_size
     */
    fun updateMinimumSize() {
        ObjectCalls.ptrcallNoArgs(updateMinimumSizeBind, handle)
    }

    /**
     * Controls layout direction and text writing direction. Right-to-left layouts are necessary for
     * certain languages (e.g. Arabic and Hebrew). See also `is_layout_rtl`.
     *
     * Generated from Godot docs: Control.set_layout_direction
     */
    fun setLayoutDirection(direction: Long) {
        ObjectCalls.ptrcallWithLongArg(setLayoutDirectionBind, handle, direction)
    }

    /**
     * Controls layout direction and text writing direction. Right-to-left layouts are necessary for
     * certain languages (e.g. Arabic and Hebrew). See also `is_layout_rtl`.
     *
     * Generated from Godot docs: Control.get_layout_direction
     */
    fun getLayoutDirection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getLayoutDirectionBind, handle)
    }

    /**
     * Returns `true` if the layout is right-to-left. See also `layout_direction`.
     *
     * Generated from Godot docs: Control.is_layout_rtl
     */
    fun isLayoutRtl(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLayoutRtlBind, handle)
    }

    /**
     * Toggles if any text should automatically change to its translated version depending on the
     * current locale.
     *
     * Generated from Godot docs: Control.set_auto_translate
     */
    fun setAutoTranslate(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoTranslateBind, handle, enable)
    }

    /**
     * Toggles if any text should automatically change to its translated version depending on the
     * current locale.
     *
     * Generated from Godot docs: Control.is_auto_translating
     */
    fun isAutoTranslating(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAutoTranslatingBind, handle)
    }

    /**
     * If `true`, automatically converts code line numbers, list indices, `SpinBox` and `ProgressBar`
     * values from the Western Arabic (0..9) to the numeral systems used in current locale. Note:
     * Numbers within the text are not automatically converted, it can be done manually, using
     * `TextServer.format_number`.
     *
     * Generated from Godot docs: Control.set_localize_numeral_system
     */
    fun setLocalizeNumeralSystem(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setLocalizeNumeralSystemBind, handle, enable)
    }

    /**
     * If `true`, automatically converts code line numbers, list indices, `SpinBox` and `ProgressBar`
     * values from the Western Arabic (0..9) to the numeral systems used in current locale. Note:
     * Numbers within the text are not automatically converted, it can be done manually, using
     * `TextServer.format_number`.
     *
     * Generated from Godot docs: Control.is_localizing_numeral_system
     */
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

    companion object {
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

        @JvmStatic
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

        private const val FORCE_DRAG_HASH = 3191844692L
        private val forceDragBind by lazy {
            ObjectCalls.getMethodBind("Control", "force_drag", FORCE_DRAG_HASH)
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

        private const val SET_ACCESSIBILITY_CONTROLS_NODES_HASH = 381264803L
        private val setAccessibilityControlsNodesBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_accessibility_controls_nodes", SET_ACCESSIBILITY_CONTROLS_NODES_HASH)
        }

        private const val GET_ACCESSIBILITY_CONTROLS_NODES_HASH = 3995934104L
        private val getAccessibilityControlsNodesBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_accessibility_controls_nodes", GET_ACCESSIBILITY_CONTROLS_NODES_HASH)
        }

        private const val SET_ACCESSIBILITY_DESCRIBED_BY_NODES_HASH = 381264803L
        private val setAccessibilityDescribedByNodesBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_accessibility_described_by_nodes", SET_ACCESSIBILITY_DESCRIBED_BY_NODES_HASH)
        }

        private const val GET_ACCESSIBILITY_DESCRIBED_BY_NODES_HASH = 3995934104L
        private val getAccessibilityDescribedByNodesBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_accessibility_described_by_nodes", GET_ACCESSIBILITY_DESCRIBED_BY_NODES_HASH)
        }

        private const val SET_ACCESSIBILITY_LABELED_BY_NODES_HASH = 381264803L
        private val setAccessibilityLabeledByNodesBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_accessibility_labeled_by_nodes", SET_ACCESSIBILITY_LABELED_BY_NODES_HASH)
        }

        private const val GET_ACCESSIBILITY_LABELED_BY_NODES_HASH = 3995934104L
        private val getAccessibilityLabeledByNodesBind by lazy {
            ObjectCalls.getMethodBind("Control", "get_accessibility_labeled_by_nodes", GET_ACCESSIBILITY_LABELED_BY_NODES_HASH)
        }

        private const val SET_ACCESSIBILITY_FLOW_TO_NODES_HASH = 381264803L
        private val setAccessibilityFlowToNodesBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_accessibility_flow_to_nodes", SET_ACCESSIBILITY_FLOW_TO_NODES_HASH)
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

        private const val SET_DRAG_FORWARDING_HASH = 1076571380L
        private val setDragForwardingBind by lazy {
            ObjectCalls.getMethodBind("Control", "set_drag_forwarding", SET_DRAG_FORWARDING_HASH)
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
