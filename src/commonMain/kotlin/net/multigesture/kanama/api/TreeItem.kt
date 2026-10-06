package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2

/**
 * An internal control for a single item inside `Tree`.
 *
 * Generated from Godot docs: TreeItem
 */
class TreeItem(handle: GodotHandle) : GodotObject(handle) {
    var collapsed: Boolean
        @JvmName("collapsedProperty")
        get() = isCollapsed()
        @JvmName("setCollapsedProperty")
        set(value) = setCollapsed(value)

    var visible: Boolean
        @JvmName("visibleProperty")
        get() = isVisible()
        @JvmName("setVisibleProperty")
        set(value) = setVisible(value)

    var disableFolding: Boolean
        @JvmName("disableFoldingProperty")
        get() = isFoldingDisabled()
        @JvmName("setDisableFoldingProperty")
        set(value) = setDisableFolding(value)

    var customMinimumHeight: Int
        @JvmName("customMinimumHeightProperty")
        get() = getCustomMinimumHeight()
        @JvmName("setCustomMinimumHeightProperty")
        set(value) = setCustomMinimumHeight(value)

    /**
     * Sets the given column's cell mode to `mode`. This determines how the cell is displayed and
     * edited.
     *
     * Generated from Godot docs: TreeItem.set_cell_mode
     */
    fun setCellMode(column: Int, mode: TreeItem.TreeCellMode) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setCellModeBind, segment, column, mode.value)
    }

    /**
     * Returns the column's cell mode.
     *
     * Generated from Godot docs: TreeItem.get_cell_mode
     */
    fun getCellMode(column: Int): TreeItem.TreeCellMode {
        return TreeItem.TreeCellMode(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getCellModeBind, segment, column))
    }

    /**
     * Sets the given column's auto translate mode to `mode`. All columns use
     * `Node.AutoTranslateMode.INHERIT` by default, which uses the same auto translate mode as the
     * `Tree` itself.
     *
     * Generated from Godot docs: TreeItem.set_auto_translate_mode
     */
    fun setAutoTranslateMode(column: Int, mode: Node.AutoTranslateMode) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setAutoTranslateModeBind, segment, column, mode.value)
    }

    /**
     * Returns the column's auto translate mode.
     *
     * Generated from Godot docs: TreeItem.get_auto_translate_mode
     */
    fun getAutoTranslateMode(column: Int): Node.AutoTranslateMode {
        return Node.AutoTranslateMode(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getAutoTranslateModeBind, segment, column))
    }

    /**
     * If `multiline` is `true`, the given `column` is multiline editable. Note: This option only
     * affects the type of control (`LineEdit` or `TextEdit`) that appears when editing the column. You
     * can set multiline values with `set_text` even if the column is not multiline editable.
     *
     * Generated from Godot docs: TreeItem.set_edit_multiline
     */
    fun setEditMultiline(column: Int, multiline: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setEditMultilineBind, segment, column, multiline)
    }

    /**
     * Returns `true` if the given `column` is multiline editable.
     *
     * Generated from Godot docs: TreeItem.is_edit_multiline
     */
    fun isEditMultiline(column: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isEditMultilineBind, segment, column)
    }

    /**
     * If `checked` is `true`, the given `column` is checked. Clears column's indeterminate status.
     *
     * Generated from Godot docs: TreeItem.set_checked
     */
    fun setChecked(column: Int, checked: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setCheckedBind, segment, column, checked)
    }

    /**
     * If `indeterminate` is `true`, the given `column` is marked indeterminate. Note: If set `true`
     * from `false`, then column is cleared of checked status.
     *
     * Generated from Godot docs: TreeItem.set_indeterminate
     */
    fun setIndeterminate(column: Int, indeterminate: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setIndeterminateBind, segment, column, indeterminate)
    }

    /**
     * Returns `true` if the given `column` is checked.
     *
     * Generated from Godot docs: TreeItem.is_checked
     */
    fun isChecked(column: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isCheckedBind, segment, column)
    }

    /**
     * Returns `true` if the given `column` is indeterminate.
     *
     * Generated from Godot docs: TreeItem.is_indeterminate
     */
    fun isIndeterminate(column: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isIndeterminateBind, segment, column)
    }

    /**
     * Propagates this item's checked status to its children and parents for the given `column`. It is
     * possible to process the items affected by this method call by connecting to
     * `Tree.check_propagated_to_item`. The order that the items affected will be processed is as
     * follows: the item invoking this method, children of that item, and finally parents of that item.
     * If `emit_signal` is `false`, then `Tree.check_propagated_to_item` will not be emitted.
     *
     * Generated from Godot docs: TreeItem.propagate_check
     */
    fun propagateCheck(column: Int, emitSignal: Boolean = true) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.propagateCheckBind, segment, column, emitSignal)
    }

    /**
     * Sets the given column's text value.
     *
     * Generated from Godot docs: TreeItem.set_text
     */
    fun setText(column: Int, text: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setTextBind, segment, column, text)
    }

    /**
     * Returns the given column's text.
     *
     * Generated from Godot docs: TreeItem.get_text
     */
    fun getText(column: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getTextBind, segment, column)
    }

    /**
     * Sets the given column's description for assistive apps.
     *
     * Generated from Godot docs: TreeItem.set_description
     */
    fun setDescription(column: Int, description: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setDescriptionBind, segment, column, description)
    }

    /**
     * Returns the given column's description for assistive apps.
     *
     * Generated from Godot docs: TreeItem.get_description
     */
    fun getDescription(column: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getDescriptionBind, segment, column)
    }

    /**
     * Sets item's text base writing direction.
     *
     * Generated from Godot docs: TreeItem.set_text_direction
     */
    fun setTextDirection(column: Int, direction: Control.TextDirection) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setTextDirectionBind, segment, column, direction.value)
    }

    /**
     * Returns item's text base writing direction.
     *
     * Generated from Godot docs: TreeItem.get_text_direction
     */
    fun getTextDirection(column: Int): Control.TextDirection {
        return Control.TextDirection(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getTextDirectionBind, segment, column))
    }

    /**
     * Sets the autowrap mode in the given `column`. If set to something other than
     * `TextServer.AutowrapMode.OFF`, the text gets wrapped inside the cell's bounding rectangle.
     *
     * Generated from Godot docs: TreeItem.set_autowrap_mode
     */
    fun setAutowrapMode(column: Int, autowrapMode: TextServer.AutowrapMode) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setAutowrapModeBind, segment, column, autowrapMode.value)
    }

    /**
     * Returns the text autowrap mode in the given `column`. By default it is
     * `TextServer.AutowrapMode.OFF`.
     *
     * Generated from Godot docs: TreeItem.get_autowrap_mode
     */
    fun getAutowrapMode(column: Int): TextServer.AutowrapMode {
        return TextServer.AutowrapMode(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getAutowrapModeBind, segment, column))
    }

    /**
     * Sets the autowrap trim flags for the given `column`. These flags control whether leading and
     * trailing spaces are trimmed on wrapped lines. Set to `0` to disable all trimming.
     *
     * Generated from Godot docs: TreeItem.set_autowrap_trim_flags
     */
    fun setAutowrapTrimFlags(column: Int, flags: TextServer.LineBreakFlag) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setAutowrapTrimFlagsBind, segment, column, flags.value)
    }

    /**
     * Returns the autowrap trim flags for the given `column`. By default, both
     * `TextServer.LineBreakFlag.TRIM_START_EDGE_SPACES` and
     * `TextServer.LineBreakFlag.TRIM_END_EDGE_SPACES` are enabled.
     *
     * Generated from Godot docs: TreeItem.get_autowrap_trim_flags
     */
    fun getAutowrapTrimFlags(column: Int): TextServer.LineBreakFlag {
        return TextServer.LineBreakFlag(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getAutowrapTrimFlagsBind, segment, column))
    }

    /**
     * Sets the clipping behavior when the text exceeds the item's bounding rectangle in the given
     * `column`.
     *
     * Generated from Godot docs: TreeItem.set_text_overrun_behavior
     */
    fun setTextOverrunBehavior(column: Int, overrunBehavior: TextServer.OverrunBehavior) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setTextOverrunBehaviorBind, segment, column, overrunBehavior.value)
    }

    /**
     * Returns the clipping behavior when the text exceeds the item's bounding rectangle in the given
     * `column`. By default it is `TextServer.OverrunBehavior.TRIM_ELLIPSIS`.
     *
     * Generated from Godot docs: TreeItem.get_text_overrun_behavior
     */
    fun getTextOverrunBehavior(column: Int): TextServer.OverrunBehavior {
        return TextServer.OverrunBehavior(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getTextOverrunBehaviorBind, segment, column))
    }

    /**
     * Set BiDi algorithm override for the structured text. Has effect for cells that display text.
     *
     * Generated from Godot docs: TreeItem.set_structured_text_bidi_override
     */
    fun setStructuredTextBidiOverride(column: Int, parser: TextServer.StructuredTextParser) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setStructuredTextBidiOverrideBind, segment, column, parser.value)
    }

    /**
     * Returns the BiDi algorithm override set for this cell.
     *
     * Generated from Godot docs: TreeItem.get_structured_text_bidi_override
     */
    fun getStructuredTextBidiOverride(column: Int): TextServer.StructuredTextParser {
        return TextServer.StructuredTextParser(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getStructuredTextBidiOverrideBind, segment, column))
    }

    /**
     * Set additional options for BiDi override. Has effect for cells that display text.
     *
     * Generated from Godot docs: TreeItem.set_structured_text_bidi_override_options
     */
    fun setStructuredTextBidiOverrideOptions(column: Int, args: List<Any?>) {
        ObjectCalls.ptrcallWithIntAndArrayArg(Binds.setStructuredTextBidiOverrideOptionsBind, segment, column, args)
    }

    /**
     * Returns the additional BiDi options set for this cell.
     *
     * Generated from Godot docs: TreeItem.get_structured_text_bidi_override_options
     */
    fun getStructuredTextBidiOverrideOptions(column: Int): List<Any?> {
        return ObjectCalls.ptrcallWithIntArgRetArray(Binds.getStructuredTextBidiOverrideOptionsBind, segment, column)
    }

    /**
     * Sets the language code of the given `column`'s text to `language`. This is used for
     * line-breaking and text shaping algorithms. If `language` is empty, the current locale is used.
     *
     * Generated from Godot docs: TreeItem.set_language
     */
    fun setLanguage(column: Int, language: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setLanguageBind, segment, column, language)
    }

    /**
     * Returns item's text language code.
     *
     * Generated from Godot docs: TreeItem.get_language
     */
    fun getLanguage(column: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getLanguageBind, segment, column)
    }

    /**
     * Sets a string to be shown after a column's value (for example, a unit abbreviation).
     *
     * Generated from Godot docs: TreeItem.set_suffix
     */
    fun setSuffix(column: Int, text: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setSuffixBind, segment, column, text)
    }

    /**
     * Gets the suffix string shown after the column value.
     *
     * Generated from Godot docs: TreeItem.get_suffix
     */
    fun getSuffix(column: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getSuffixBind, segment, column)
    }

    /**
     * Sets the given cell's icon `Texture2D`. If the cell is in `TreeCellMode.ICON` mode, the icon is
     * displayed in the center of the cell. Otherwise, the icon is displayed before the cell's text.
     * `TreeCellMode.RANGE` does not display an icon.
     *
     * Generated from Godot docs: TreeItem.set_icon
     */
    fun setIcon(column: Int, texture: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setIconBind, segment, column, texture?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the given column's icon `Texture2D`. Error if no icon is set.
     *
     * Generated from Godot docs: TreeItem.get_icon
     */
    fun getIcon(column: Int): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getIconBind, segment, column))
    }

    /**
     * Sets the given cell's icon overlay `Texture2D`. The cell has to be in `TreeCellMode.ICON` mode,
     * and icon has to be set. Overlay is drawn on top of icon, in the bottom left corner.
     *
     * Generated from Godot docs: TreeItem.set_icon_overlay
     */
    fun setIconOverlay(column: Int, texture: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setIconOverlayBind, segment, column, texture?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the given column's icon overlay `Texture2D`.
     *
     * Generated from Godot docs: TreeItem.get_icon_overlay
     */
    fun getIconOverlay(column: Int): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getIconOverlayBind, segment, column))
    }

    /**
     * Sets the given column's icon's texture region.
     *
     * Generated from Godot docs: TreeItem.set_icon_region
     */
    fun setIconRegion(column: Int, region: Rect2) {
        ObjectCalls.ptrcallWithIntAndRect2Arg(Binds.setIconRegionBind, segment, column, region)
    }

    /**
     * Returns the icon `Texture2D` region as `Rect2`.
     *
     * Generated from Godot docs: TreeItem.get_icon_region
     */
    fun getIconRegion(column: Int): Rect2 {
        return ObjectCalls.ptrcallWithIntArgRetRect2(Binds.getIconRegionBind, segment, column)
    }

    /**
     * Sets the maximum allowed width of the icon in the given `column`. This limit is applied on top
     * of the default size of the icon and on top of `Tree.icon_max_width`. The height is adjusted
     * according to the icon's ratio.
     *
     * Generated from Godot docs: TreeItem.set_icon_max_width
     */
    fun setIconMaxWidth(column: Int, width: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setIconMaxWidthBind, segment, column, width)
    }

    /**
     * Returns the maximum allowed width of the icon in the given `column`.
     *
     * Generated from Godot docs: TreeItem.get_icon_max_width
     */
    fun getIconMaxWidth(column: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getIconMaxWidthBind, segment, column)
    }

    /**
     * Modulates the given column's icon with `modulate`.
     *
     * Generated from Godot docs: TreeItem.set_icon_modulate
     */
    fun setIconModulate(column: Int, modulate: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(Binds.setIconModulateBind, segment, column, modulate)
    }

    /**
     * Returns the `Color` modulating the column's icon.
     *
     * Generated from Godot docs: TreeItem.get_icon_modulate
     */
    fun getIconModulate(column: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(Binds.getIconModulateBind, segment, column)
    }

    /**
     * Sets the value of a `TreeCellMode.RANGE` column.
     *
     * Generated from Godot docs: TreeItem.set_range
     */
    fun setRange(column: Int, value: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setRangeBind, segment, column, value)
    }

    /**
     * Returns the value of a `TreeCellMode.RANGE` column.
     *
     * Generated from Godot docs: TreeItem.get_range
     */
    fun getRange(column: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getRangeBind, segment, column)
    }

    /**
     * Sets the range of accepted values for a column. The column must be in the `TreeCellMode.RANGE`
     * mode. If `expr` is `true`, the edit mode slider will use an exponential scale as with
     * `Range.exp_edit`.
     *
     * Generated from Godot docs: TreeItem.set_range_config
     */
    fun setRangeConfig(column: Int, min: Double, max: Double, step: Double, expr: Boolean = false) {
        ObjectCalls.ptrcallWithIntThreeDoubleBoolArgs(Binds.setRangeConfigBind, segment, column, min, max, step, expr)
    }

    /**
     * Returns a dictionary containing the range parameters for a given column. The keys are "min",
     * "max", "step", and "expr".
     *
     * Generated from Godot docs: TreeItem.get_range_config
     */
    fun getRangeConfig(column: Int): Map<String, Any?> {
        return ObjectCalls.ptrcallWithIntArgRetDictionary(Binds.getRangeConfigBind, segment, column)
    }

    /**
     * Sets the metadata value for the given column, which can be retrieved later using `get_metadata`.
     * This can be used, for example, to store a reference to the original data.
     *
     * Generated from Godot docs: TreeItem.set_metadata
     */
    fun setMetadata(column: Int, meta: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(Binds.setMetadataBind, segment, column, meta)
    }

    /**
     * Returns the metadata value that was set for the given column using `set_metadata`.
     *
     * Generated from Godot docs: TreeItem.get_metadata
     */
    fun getMetadata(column: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(Binds.getMetadataBind, segment, column)
    }

    /**
     * Sets the given column's custom draw callback to the `callback` method on `object`. The method
     * named `callback` should accept two arguments: the `TreeItem` that is drawn and its position and
     * size as a `Rect2`.
     *
     * Generated from Godot docs: TreeItem.set_custom_draw
     */
    fun setCustomDraw(column: Int, objectValue: GodotObject, callback: String) {
        ObjectCalls.ptrcallWithIntObjectStringNameArgs(Binds.setCustomDrawBind, segment, column, objectValue.segment, callback)
    }

    /**
     * Sets the given column's custom draw callback. Use an empty `Callable` (`Callable()`) to clear
     * the custom callback. The cell has to be in `TreeCellMode.CUSTOM` to use this feature. The
     * `callback` should accept two arguments: the `TreeItem` that is drawn and its position and size
     * as a `Rect2`. To draw custom content over the native style, please use
     * `Tree.get_custom_drawing_canvas_item`.
     *
     * Generated from Godot docs: TreeItem.set_custom_draw_callback
     */
    fun setCustomDrawCallback(column: Int, callback: GodotCallable) {
        ObjectCalls.ptrcallWithIntCallableArgs(Binds.setCustomDrawCallbackBind, segment, column, callback.target.segment, callback.method)
    }

    /**
     * Returns the custom callback of column `column`.
     *
     * Generated from Godot docs: TreeItem.get_custom_draw_callback
     */
    fun getCustomDrawCallback(column: Int): GodotCallable? {
        return ObjectCalls.ptrcallWithIntArgRetCallable(Binds.getCustomDrawCallbackBind, segment, column)
    }

    /**
     * Sets the given column's custom `StyleBox` used to draw the background. Note: If a custom
     * background color is set, the `StyleBox` will be drawn in front of it.
     *
     * Generated from Godot docs: TreeItem.set_custom_stylebox
     */
    fun setCustomStylebox(column: Int, stylebox: StyleBox?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setCustomStyleboxBind, segment, column, stylebox?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the given column's custom `StyleBox` used to draw the background.
     *
     * Generated from Godot docs: TreeItem.get_custom_stylebox
     */
    fun getCustomStylebox(column: Int): StyleBox? {
        return StyleBox.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getCustomStyleboxBind, segment, column))
    }

    /**
     * If `true`, the TreeItem is collapsed.
     *
     * Generated from Godot docs: TreeItem.set_collapsed
     */
    fun setCollapsed(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCollapsedBind, segment, enable)
    }

    /**
     * If `true`, the TreeItem is collapsed.
     *
     * Generated from Godot docs: TreeItem.is_collapsed
     */
    fun isCollapsed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCollapsedBind, segment)
    }

    /**
     * Collapses or uncollapses this `TreeItem` and all the descendants of this item.
     *
     * Generated from Godot docs: TreeItem.set_collapsed_recursive
     */
    fun setCollapsedRecursive(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCollapsedRecursiveBind, segment, enable)
    }

    /**
     * Returns `true` if this `TreeItem`, or any of its descendants, is collapsed. If `only_visible` is
     * `true` it ignores non-visible `TreeItem`s.
     *
     * Generated from Godot docs: TreeItem.is_any_collapsed
     */
    fun isAnyCollapsed(onlyVisible: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithBoolArgRetBool(Binds.isAnyCollapsedBind, segment, onlyVisible)
    }

    /**
     * If `true`, the `TreeItem` is visible (default). Note that if a `TreeItem` is set to not be
     * visible, none of its children will be visible either.
     *
     * Generated from Godot docs: TreeItem.set_visible
     */
    fun setVisible(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setVisibleBind, segment, enable)
    }

    /**
     * If `true`, the `TreeItem` is visible (default). Note that if a `TreeItem` is set to not be
     * visible, none of its children will be visible either.
     *
     * Generated from Godot docs: TreeItem.is_visible
     */
    fun isVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isVisibleBind, segment)
    }

    /**
     * Returns `true` if `visible` is `true` and all its ancestors are also visible.
     *
     * Generated from Godot docs: TreeItem.is_visible_in_tree
     */
    fun isVisibleInTree(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isVisibleInTreeBind, segment)
    }

    /**
     * Uncollapses all `TreeItem`s necessary to reveal this `TreeItem`, i.e. all ancestor `TreeItem`s.
     *
     * Generated from Godot docs: TreeItem.uncollapse_tree
     */
    fun uncollapseTree() {
        ObjectCalls.ptrcallNoArgs(Binds.uncollapseTreeBind, segment)
    }

    /**
     * The custom minimum height.
     *
     * Generated from Godot docs: TreeItem.set_custom_minimum_height
     */
    fun setCustomMinimumHeight(height: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setCustomMinimumHeightBind, segment, height)
    }

    /**
     * The custom minimum height.
     *
     * Generated from Godot docs: TreeItem.get_custom_minimum_height
     */
    fun getCustomMinimumHeight(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getCustomMinimumHeightBind, segment)
    }

    /**
     * If `selectable` is `true`, the given `column` is selectable.
     *
     * Generated from Godot docs: TreeItem.set_selectable
     */
    fun setSelectable(column: Int, selectable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setSelectableBind, segment, column, selectable)
    }

    /**
     * Returns `true` if the given `column` is selectable.
     *
     * Generated from Godot docs: TreeItem.is_selectable
     */
    fun isSelectable(column: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isSelectableBind, segment, column)
    }

    /**
     * Returns `true` if the given `column` is selected.
     *
     * Generated from Godot docs: TreeItem.is_selected
     */
    fun isSelected(column: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isSelectedBind, segment, column)
    }

    /**
     * Selects the given `column`. If `set_as_cursor` is `true`, the `Tree`'s cursor will be moved to
     * this item (only matters if `Tree.select_mode` is set to `Tree.SelectMode.MULTI`).
     *
     * Generated from Godot docs: TreeItem.select
     */
    fun select(column: Int, setAsCursor: Boolean = true) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.selectBind, segment, column, setAsCursor)
    }

    /**
     * Deselects the given column.
     *
     * Generated from Godot docs: TreeItem.deselect
     */
    fun deselect(column: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.deselectBind, segment, column)
    }

    /**
     * If `enabled` is `true`, the given `column` is editable.
     *
     * Generated from Godot docs: TreeItem.set_editable
     */
    fun setEditable(column: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setEditableBind, segment, column, enabled)
    }

    /**
     * Returns `true` if the given `column` is editable.
     *
     * Generated from Godot docs: TreeItem.is_editable
     */
    fun isEditable(column: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isEditableBind, segment, column)
    }

    /**
     * Sets the given column's custom color.
     *
     * Generated from Godot docs: TreeItem.set_custom_color
     */
    fun setCustomColor(column: Int, color: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(Binds.setCustomColorBind, segment, column, color)
    }

    /**
     * Returns the custom color of column `column`.
     *
     * Generated from Godot docs: TreeItem.get_custom_color
     */
    fun getCustomColor(column: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(Binds.getCustomColorBind, segment, column)
    }

    /**
     * Resets the color for the given column to default.
     *
     * Generated from Godot docs: TreeItem.clear_custom_color
     */
    fun clearCustomColor(column: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.clearCustomColorBind, segment, column)
    }

    /**
     * Sets custom font used to draw text in the given `column`.
     *
     * Generated from Godot docs: TreeItem.set_custom_font
     */
    fun setCustomFont(column: Int, font: Font?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setCustomFontBind, segment, column, font?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns custom font used to draw text in the column `column`.
     *
     * Generated from Godot docs: TreeItem.get_custom_font
     */
    fun getCustomFont(column: Int): Font? {
        return Font.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getCustomFontBind, segment, column))
    }

    /**
     * Sets custom font size used to draw text in the given `column`.
     *
     * Generated from Godot docs: TreeItem.set_custom_font_size
     */
    fun setCustomFontSize(column: Int, fontSize: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setCustomFontSizeBind, segment, column, fontSize)
    }

    /**
     * Returns custom font size used to draw text in the column `column`.
     *
     * Generated from Godot docs: TreeItem.get_custom_font_size
     */
    fun getCustomFontSize(column: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getCustomFontSizeBind, segment, column)
    }

    /**
     * Sets the given column's custom background color and whether to just use it as an outline. Note:
     * If a custom `StyleBox` is set, the background color will be drawn behind it.
     *
     * Generated from Godot docs: TreeItem.set_custom_bg_color
     */
    fun setCustomBgColor(column: Int, color: Color, justOutline: Boolean = false) {
        ObjectCalls.ptrcallWithIntColorBoolArgs(Binds.setCustomBgColorBind, segment, column, color, justOutline)
    }

    /**
     * Resets the background color for the given column to default.
     *
     * Generated from Godot docs: TreeItem.clear_custom_bg_color
     */
    fun clearCustomBgColor(column: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.clearCustomBgColorBind, segment, column)
    }

    /**
     * Returns the custom background color of column `column`.
     *
     * Generated from Godot docs: TreeItem.get_custom_bg_color
     */
    fun getCustomBgColor(column: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(Binds.getCustomBgColorBind, segment, column)
    }

    /**
     * Makes a cell with `TreeCellMode.CUSTOM` display as a non-flat button with a `StyleBox`.
     *
     * Generated from Godot docs: TreeItem.set_custom_as_button
     */
    fun setCustomAsButton(column: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setCustomAsButtonBind, segment, column, enable)
    }

    /**
     * Returns `true` if the cell was made into a button with `set_custom_as_button`.
     *
     * Generated from Godot docs: TreeItem.is_custom_set_as_button
     */
    fun isCustomSetAsButton(column: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isCustomSetAsButtonBind, segment, column)
    }

    /**
     * Removes all buttons from all columns of this item.
     *
     * Generated from Godot docs: TreeItem.clear_buttons
     */
    fun clearButtons() {
        ObjectCalls.ptrcallNoArgs(Binds.clearButtonsBind, segment)
    }

    /**
     * Adds a button with `Texture2D` `button` to the end of the cell at column `column`. The `id` is
     * used to identify the button in the according `Tree.button_clicked` signal and can be different
     * from the buttons index. If not specified, the next available index is used, which may be
     * retrieved by calling `get_button_count` immediately before this method. Optionally, the button
     * can be `disabled` and have a `tooltip_text`. `description` is used as the button description for
     * assistive apps.
     *
     * Generated from Godot docs: TreeItem.add_button
     */
    fun addButton(column: Int, button: Texture2D?, id: Int = -1, disabled: Boolean = false, tooltipText: String = "", description: String = "") {
        ObjectCalls.ptrcallWithIntObjectIntBoolTwoStringArgs(Binds.addButtonBind, segment, column, button?.requireOpenHandle() ?: NULL_SEGMENT, id, disabled, tooltipText, description)
    }

    /**
     * Returns the number of buttons in column `column`.
     *
     * Generated from Godot docs: TreeItem.get_button_count
     */
    fun getButtonCount(column: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getButtonCountBind, segment, column)
    }

    /**
     * Returns the tooltip text for the button at index `button_index` in column `column`.
     *
     * Generated from Godot docs: TreeItem.get_button_tooltip_text
     */
    fun getButtonTooltipText(column: Int, buttonIndex: Int): String {
        return ObjectCalls.ptrcallWithTwoIntArgsRetString(Binds.getButtonTooltipTextBind, segment, column, buttonIndex)
    }

    /**
     * Returns the ID for the button at index `button_index` in column `column`.
     *
     * Generated from Godot docs: TreeItem.get_button_id
     */
    fun getButtonId(column: Int, buttonIndex: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(Binds.getButtonIdBind, segment, column, buttonIndex)
    }

    /**
     * Returns the button index if there is a button with ID `id` in column `column`, otherwise returns
     * -1.
     *
     * Generated from Godot docs: TreeItem.get_button_by_id
     */
    fun getButtonById(column: Int, id: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(Binds.getButtonByIdBind, segment, column, id)
    }

    /**
     * Returns the color of the button with ID `id` in column `column`. If the specified button does
     * not exist, returns `Color.BLACK`.
     *
     * Generated from Godot docs: TreeItem.get_button_color
     */
    fun getButtonColor(column: Int, id: Int): Color {
        return ObjectCalls.ptrcallWithTwoIntArgsRetColor(Binds.getButtonColorBind, segment, column, id)
    }

    /**
     * Returns the `Texture2D` of the button at index `button_index` in column `column`.
     *
     * Generated from Godot docs: TreeItem.get_button
     */
    fun getButton(column: Int, buttonIndex: Int): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallWithTwoIntArgsRetObject(Binds.getButtonBind, segment, column, buttonIndex))
    }

    /**
     * Sets the tooltip text for the button at index `button_index` in the given `column`.
     *
     * Generated from Godot docs: TreeItem.set_button_tooltip_text
     */
    fun setButtonTooltipText(column: Int, buttonIndex: Int, tooltip: String) {
        ObjectCalls.ptrcallWithTwoIntAndStringArgs(Binds.setButtonTooltipTextBind, segment, column, buttonIndex, tooltip)
    }

    /**
     * Sets the given column's button `Texture2D` at index `button_index` to `button`.
     *
     * Generated from Godot docs: TreeItem.set_button
     */
    fun setButton(column: Int, buttonIndex: Int, button: Texture2D?) {
        ObjectCalls.ptrcallWithTwoIntAndObjectArg(Binds.setButtonBind, segment, column, buttonIndex, button?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Removes the button at index `button_index` in column `column`.
     *
     * Generated from Godot docs: TreeItem.erase_button
     */
    fun eraseButton(column: Int, buttonIndex: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.eraseButtonBind, segment, column, buttonIndex)
    }

    /**
     * Sets the given column's button description at index `button_index` for assistive apps.
     *
     * Generated from Godot docs: TreeItem.set_button_description
     */
    fun setButtonDescription(column: Int, buttonIndex: Int, description: String) {
        ObjectCalls.ptrcallWithTwoIntAndStringArgs(Binds.setButtonDescriptionBind, segment, column, buttonIndex, description)
    }

    /**
     * If `true`, disables the button at index `button_index` in the given `column`.
     *
     * Generated from Godot docs: TreeItem.set_button_disabled
     */
    fun setButtonDisabled(column: Int, buttonIndex: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithTwoIntAndBoolArgs(Binds.setButtonDisabledBind, segment, column, buttonIndex, disabled)
    }

    /**
     * Sets the given column's button color at index `button_index` to `color`.
     *
     * Generated from Godot docs: TreeItem.set_button_color
     */
    fun setButtonColor(column: Int, buttonIndex: Int, color: Color) {
        ObjectCalls.ptrcallWithTwoIntAndColorArg(Binds.setButtonColorBind, segment, column, buttonIndex, color)
    }

    /**
     * Returns `true` if the button at index `button_index` for the given `column` is disabled.
     *
     * Generated from Godot docs: TreeItem.is_button_disabled
     */
    fun isButtonDisabled(column: Int, buttonIndex: Int): Boolean {
        return ObjectCalls.ptrcallWithTwoIntArgsRetBool(Binds.isButtonDisabledBind, segment, column, buttonIndex)
    }

    /**
     * Sets the given column's tooltip text.
     *
     * Generated from Godot docs: TreeItem.set_tooltip_text
     */
    fun setTooltipText(column: Int, tooltip: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(Binds.setTooltipTextBind, segment, column, tooltip)
    }

    /**
     * Returns the given column's tooltip text.
     *
     * Generated from Godot docs: TreeItem.get_tooltip_text
     */
    fun getTooltipText(column: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getTooltipTextBind, segment, column)
    }

    /**
     * Sets the given column's text alignment to `text_alignment`.
     *
     * Generated from Godot docs: TreeItem.set_text_alignment
     */
    fun setTextAlignment(column: Int, textAlignment: HorizontalAlignment) {
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setTextAlignmentBind, segment, column, textAlignment.value)
    }

    /**
     * Returns the given column's text alignment.
     *
     * Generated from Godot docs: TreeItem.get_text_alignment
     */
    fun getTextAlignment(column: Int): HorizontalAlignment {
        return HorizontalAlignment(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getTextAlignmentBind, segment, column))
    }

    /**
     * If `enable` is `true`, the given `column` is expanded to the right.
     *
     * Generated from Godot docs: TreeItem.set_expand_right
     */
    fun setExpandRight(column: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setExpandRightBind, segment, column, enable)
    }

    /**
     * Returns `true` if `expand_right` is set.
     *
     * Generated from Godot docs: TreeItem.get_expand_right
     */
    fun getExpandRight(column: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getExpandRightBind, segment, column)
    }

    /**
     * If `true`, folding is disabled for this TreeItem.
     *
     * Generated from Godot docs: TreeItem.set_disable_folding
     */
    fun setDisableFolding(disable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDisableFoldingBind, segment, disable)
    }

    /**
     * If `true`, folding is disabled for this TreeItem.
     *
     * Generated from Godot docs: TreeItem.is_folding_disabled
     */
    fun isFoldingDisabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isFoldingDisabledBind, segment)
    }

    /**
     * Sets `TreeItem`'s ability to accept children.
     *
     * Generated from Godot docs: TreeItem.set_accept_children
     */
    fun setAcceptChildren(allowed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAcceptChildrenBind, segment, allowed)
    }

    /**
     * Returns `true` if this `TreeItem` is allowed to accept children.
     *
     * Generated from Godot docs: TreeItem.is_accepting_children
     */
    fun isAcceptingChildren(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAcceptingChildrenBind, segment)
    }

    /**
     * Creates an item and adds it as a child. The new item will be inserted as position `index` (the
     * default value `-1` means the last position), or it will be the last child if `index` is higher
     * than the child count.
     *
     * Generated from Godot docs: TreeItem.create_child
     */
    fun createChild(index: Int = -1): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallWithIntArgRetObject(Binds.createChildBind, segment, index))
    }

    /**
     * Adds a previously unparented `TreeItem` as a direct child of this one. The `child` item must not
     * be a part of any `Tree` or parented to any `TreeItem`. See also `remove_child`.
     *
     * Generated from Godot docs: TreeItem.add_child
     */
    fun addChild(child: TreeItem) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.addChildBind, segment, listOf(child.segment))
    }

    /**
     * Removes the given child `TreeItem` and all its children from the `Tree`. Note that it doesn't
     * free the item from memory, so it can be reused later (see `add_child`). To completely remove a
     * `TreeItem` use `Object.free`. Note: If you want to move a child from one `Tree` to another, then
     * instead of removing and adding it manually you can use `move_before` or `move_after`.
     *
     * Generated from Godot docs: TreeItem.remove_child
     */
    fun removeChild(child: TreeItem) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeChildBind, segment, listOf(child.segment))
    }

    /**
     * Returns the `Tree` that owns this TreeItem.
     *
     * Generated from Godot docs: TreeItem.get_tree
     */
    fun getTree(): Tree? {
        return Tree.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getTreeBind, segment))
    }

    /**
     * Returns the next sibling TreeItem in the tree or a `null` object if there is none.
     *
     * Generated from Godot docs: TreeItem.get_next
     */
    fun getNext(): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getNextBind, segment))
    }

    /**
     * Returns the previous sibling TreeItem in the tree or a `null` object if there is none.
     *
     * Generated from Godot docs: TreeItem.get_prev
     */
    fun getPrev(): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getPrevBind, segment))
    }

    /**
     * Returns the parent TreeItem or a `null` object if there is none.
     *
     * Generated from Godot docs: TreeItem.get_parent
     */
    fun getParent(): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getParentBind, segment))
    }

    /**
     * Returns the TreeItem's first child.
     *
     * Generated from Godot docs: TreeItem.get_first_child
     */
    fun getFirstChild(): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getFirstChildBind, segment))
    }

    /**
     * Returns the next TreeItem in the tree (in the context of a depth-first search) or a `null`
     * object if there is none. If `wrap` is enabled, the method will wrap around to the first element
     * in the tree when called on the last element, otherwise it returns `null`.
     *
     * Generated from Godot docs: TreeItem.get_next_in_tree
     */
    fun getNextInTree(wrap: Boolean = false): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallWithBoolArgRetObject(Binds.getNextInTreeBind, segment, wrap))
    }

    /**
     * Returns the previous TreeItem in the tree (in the context of a depth-first search) or a `null`
     * object if there is none. If `wrap` is enabled, the method will wrap around to the last element
     * in the tree when called on the first visible element, otherwise it returns `null`.
     *
     * Generated from Godot docs: TreeItem.get_prev_in_tree
     */
    fun getPrevInTree(wrap: Boolean = false): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallWithBoolArgRetObject(Binds.getPrevInTreeBind, segment, wrap))
    }

    /**
     * Returns the next visible TreeItem in the tree (in the context of a depth-first search) or a
     * `null` object if there is none. If `wrap` is enabled, the method will wrap around to the first
     * visible element in the tree when called on the last visible element, otherwise it returns
     * `null`.
     *
     * Generated from Godot docs: TreeItem.get_next_visible
     */
    fun getNextVisible(wrap: Boolean = false): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallWithBoolArgRetObject(Binds.getNextVisibleBind, segment, wrap))
    }

    /**
     * Returns the previous visible sibling TreeItem in the tree (in the context of a depth-first
     * search) or a `null` object if there is none. If `wrap` is enabled, the method will wrap around
     * to the last visible element in the tree when called on the first visible element, otherwise it
     * returns `null`.
     *
     * Generated from Godot docs: TreeItem.get_prev_visible
     */
    fun getPrevVisible(wrap: Boolean = false): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallWithBoolArgRetObject(Binds.getPrevVisibleBind, segment, wrap))
    }

    /**
     * Returns a child item by its `index` (see `get_child_count`). This method is often used for
     * iterating all children of an item. Negative indices access the children from the last one.
     *
     * Generated from Godot docs: TreeItem.get_child
     */
    fun getChild(index: Int): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getChildBind, segment, index))
    }

    /**
     * Returns the number of child items.
     *
     * Generated from Godot docs: TreeItem.get_child_count
     */
    fun getChildCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getChildCountBind, segment)
    }

    /**
     * Returns an array of references to the item's children.
     *
     * Generated from Godot docs: TreeItem.get_children
     */
    fun getChildren(): List<TreeItem> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getChildrenBind, segment, TreeItem::wrap)
    }

    /**
     * Returns the node's order in the tree. For example, if called on the first child item the
     * position is `0`.
     *
     * Generated from Godot docs: TreeItem.get_index
     */
    fun getIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getIndexBind, segment)
    }

    /**
     * Moves this TreeItem right before the given `item`. Note: You can't move to the root or move the
     * root.
     *
     * Generated from Godot docs: TreeItem.move_before
     */
    fun moveBefore(item: TreeItem) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.moveBeforeBind, segment, listOf(item.segment))
    }

    /**
     * Moves this TreeItem right after the given `item`. Note: You can't move to the root or move the
     * root.
     *
     * Generated from Godot docs: TreeItem.move_after
     */
    fun moveAfter(item: TreeItem) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.moveAfterBind, segment, listOf(item.segment))
    }

    /**
     * Calls the `method` on the actual TreeItem and its children recursively. Pass parameters as a
     * comma separated list.
     *
     * Generated from Godot docs: TreeItem.call_recursive
     */
    fun callRecursive(method: String, vararg extraArgs: Any?) {
        ObjectCalls.callWithVariantArgs(Binds.callRecursiveBind, segment, listOf(method, *extraArgs))
    }

    /**
     * Godot's `TreeItem.TreeCellMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`TreeItem.TreeCellMode.<NAME>`).
     *
     * Generated from Godot docs: TreeItem.TreeCellMode
     */
    @JvmInline
    value class TreeCellMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Cell shows a string label, optionally with an icon. When editable, the text can be edited using
             * a `LineEdit`, or a `TextEdit` popup if `set_edit_multiline` is used.
             *
             * Generated from Godot docs: TreeItem.CELL_MODE_STRING
             */
            val STRING: TreeCellMode get() = TreeCellMode(0L)
            /**
             * Cell shows a checkbox, optionally with text and an icon. The checkbox can be pressed, released,
             * or indeterminate (via `set_indeterminate`). The checkbox can't be clicked unless the cell is
             * editable.
             *
             * Generated from Godot docs: TreeItem.CELL_MODE_CHECK
             */
            val CHECK: TreeCellMode get() = TreeCellMode(1L)
            /**
             * Cell shows a numeric range. When editable, it can be edited using a range slider. Use
             * `set_range` to set the value and `set_range_config` to configure the range. This cell can also
             * be used in a text dropdown mode when you assign a text with `set_text`. Separate options with a
             * comma, e.g. `"Option1,Option2,Option3"`.
             *
             * Generated from Godot docs: TreeItem.CELL_MODE_RANGE
             */
            val RANGE: TreeCellMode get() = TreeCellMode(2L)
            /**
             * Cell shows an icon. It can't be edited nor display text. The icon is always centered within the
             * cell.
             *
             * Generated from Godot docs: TreeItem.CELL_MODE_ICON
             */
            val ICON: TreeCellMode get() = TreeCellMode(3L)
            /**
             * Cell shows as a clickable button. It will display an arrow similar to `OptionButton`, but
             * doesn't feature a dropdown (for that you can use `TreeCellMode.RANGE`). Clicking the button
             * emits the `Tree.item_edited` signal. The button is flat by default, you can use
             * `set_custom_as_button` to display it with a `StyleBox`. This mode also supports custom drawing
             * using `set_custom_draw_callback`.
             *
             * Generated from Godot docs: TreeItem.CELL_MODE_CUSTOM
             */
            val CUSTOM: TreeCellMode get() = TreeCellMode(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TreeItem? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): TreeItem? =
            if (handle.address() == 0L) null else TreeItem(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_CELL_MODE_HASH = 289920701L
        @JvmField
        val setCellModeBind =
            ObjectCalls.getMethodBind("TreeItem", "set_cell_mode", SET_CELL_MODE_HASH)

        private const val GET_CELL_MODE_HASH = 3406114978L
        @JvmField
        val getCellModeBind =
            ObjectCalls.getMethodBind("TreeItem", "get_cell_mode", GET_CELL_MODE_HASH)

        private const val SET_AUTO_TRANSLATE_MODE_HASH = 287402019L
        @JvmField
        val setAutoTranslateModeBind =
            ObjectCalls.getMethodBind("TreeItem", "set_auto_translate_mode", SET_AUTO_TRANSLATE_MODE_HASH)

        private const val GET_AUTO_TRANSLATE_MODE_HASH = 906302372L
        @JvmField
        val getAutoTranslateModeBind =
            ObjectCalls.getMethodBind("TreeItem", "get_auto_translate_mode", GET_AUTO_TRANSLATE_MODE_HASH)

        private const val SET_EDIT_MULTILINE_HASH = 300928843L
        @JvmField
        val setEditMultilineBind =
            ObjectCalls.getMethodBind("TreeItem", "set_edit_multiline", SET_EDIT_MULTILINE_HASH)

        private const val IS_EDIT_MULTILINE_HASH = 1116898809L
        @JvmField
        val isEditMultilineBind =
            ObjectCalls.getMethodBind("TreeItem", "is_edit_multiline", IS_EDIT_MULTILINE_HASH)

        private const val SET_CHECKED_HASH = 300928843L
        @JvmField
        val setCheckedBind =
            ObjectCalls.getMethodBind("TreeItem", "set_checked", SET_CHECKED_HASH)

        private const val SET_INDETERMINATE_HASH = 300928843L
        @JvmField
        val setIndeterminateBind =
            ObjectCalls.getMethodBind("TreeItem", "set_indeterminate", SET_INDETERMINATE_HASH)

        private const val IS_CHECKED_HASH = 1116898809L
        @JvmField
        val isCheckedBind =
            ObjectCalls.getMethodBind("TreeItem", "is_checked", IS_CHECKED_HASH)

        private const val IS_INDETERMINATE_HASH = 1116898809L
        @JvmField
        val isIndeterminateBind =
            ObjectCalls.getMethodBind("TreeItem", "is_indeterminate", IS_INDETERMINATE_HASH)

        private const val PROPAGATE_CHECK_HASH = 972357352L
        @JvmField
        val propagateCheckBind =
            ObjectCalls.getMethodBind("TreeItem", "propagate_check", PROPAGATE_CHECK_HASH)

        private const val SET_TEXT_HASH = 501894301L
        @JvmField
        val setTextBind =
            ObjectCalls.getMethodBind("TreeItem", "set_text", SET_TEXT_HASH)

        private const val GET_TEXT_HASH = 844755477L
        @JvmField
        val getTextBind =
            ObjectCalls.getMethodBind("TreeItem", "get_text", GET_TEXT_HASH)

        private const val SET_DESCRIPTION_HASH = 501894301L
        @JvmField
        val setDescriptionBind =
            ObjectCalls.getMethodBind("TreeItem", "set_description", SET_DESCRIPTION_HASH)

        private const val GET_DESCRIPTION_HASH = 844755477L
        @JvmField
        val getDescriptionBind =
            ObjectCalls.getMethodBind("TreeItem", "get_description", GET_DESCRIPTION_HASH)

        private const val SET_TEXT_DIRECTION_HASH = 1707680378L
        @JvmField
        val setTextDirectionBind =
            ObjectCalls.getMethodBind("TreeItem", "set_text_direction", SET_TEXT_DIRECTION_HASH)

        private const val GET_TEXT_DIRECTION_HASH = 4235602388L
        @JvmField
        val getTextDirectionBind =
            ObjectCalls.getMethodBind("TreeItem", "get_text_direction", GET_TEXT_DIRECTION_HASH)

        private const val SET_AUTOWRAP_MODE_HASH = 3633006561L
        @JvmField
        val setAutowrapModeBind =
            ObjectCalls.getMethodBind("TreeItem", "set_autowrap_mode", SET_AUTOWRAP_MODE_HASH)

        private const val GET_AUTOWRAP_MODE_HASH = 2902757236L
        @JvmField
        val getAutowrapModeBind =
            ObjectCalls.getMethodBind("TreeItem", "get_autowrap_mode", GET_AUTOWRAP_MODE_HASH)

        private const val SET_AUTOWRAP_TRIM_FLAGS_HASH = 2186029660L
        @JvmField
        val setAutowrapTrimFlagsBind =
            ObjectCalls.getMethodBind("TreeItem", "set_autowrap_trim_flags", SET_AUTOWRAP_TRIM_FLAGS_HASH)

        private const val GET_AUTOWRAP_TRIM_FLAGS_HASH = 3513056523L
        @JvmField
        val getAutowrapTrimFlagsBind =
            ObjectCalls.getMethodBind("TreeItem", "get_autowrap_trim_flags", GET_AUTOWRAP_TRIM_FLAGS_HASH)

        private const val SET_TEXT_OVERRUN_BEHAVIOR_HASH = 1940772195L
        @JvmField
        val setTextOverrunBehaviorBind =
            ObjectCalls.getMethodBind("TreeItem", "set_text_overrun_behavior", SET_TEXT_OVERRUN_BEHAVIOR_HASH)

        private const val GET_TEXT_OVERRUN_BEHAVIOR_HASH = 3782727860L
        @JvmField
        val getTextOverrunBehaviorBind =
            ObjectCalls.getMethodBind("TreeItem", "get_text_overrun_behavior", GET_TEXT_OVERRUN_BEHAVIOR_HASH)

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 868756907L
        @JvmField
        val setStructuredTextBidiOverrideBind =
            ObjectCalls.getMethodBind("TreeItem", "set_structured_text_bidi_override", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 3377823772L
        @JvmField
        val getStructuredTextBidiOverrideBind =
            ObjectCalls.getMethodBind("TreeItem", "get_structured_text_bidi_override", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 537221740L
        @JvmField
        val setStructuredTextBidiOverrideOptionsBind =
            ObjectCalls.getMethodBind("TreeItem", "set_structured_text_bidi_override_options", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 663333327L
        @JvmField
        val getStructuredTextBidiOverrideOptionsBind =
            ObjectCalls.getMethodBind("TreeItem", "get_structured_text_bidi_override_options", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)

        private const val SET_LANGUAGE_HASH = 501894301L
        @JvmField
        val setLanguageBind =
            ObjectCalls.getMethodBind("TreeItem", "set_language", SET_LANGUAGE_HASH)

        private const val GET_LANGUAGE_HASH = 844755477L
        @JvmField
        val getLanguageBind =
            ObjectCalls.getMethodBind("TreeItem", "get_language", GET_LANGUAGE_HASH)

        private const val SET_SUFFIX_HASH = 501894301L
        @JvmField
        val setSuffixBind =
            ObjectCalls.getMethodBind("TreeItem", "set_suffix", SET_SUFFIX_HASH)

        private const val GET_SUFFIX_HASH = 844755477L
        @JvmField
        val getSuffixBind =
            ObjectCalls.getMethodBind("TreeItem", "get_suffix", GET_SUFFIX_HASH)

        private const val SET_ICON_HASH = 666127730L
        @JvmField
        val setIconBind =
            ObjectCalls.getMethodBind("TreeItem", "set_icon", SET_ICON_HASH)

        private const val GET_ICON_HASH = 3536238170L
        @JvmField
        val getIconBind =
            ObjectCalls.getMethodBind("TreeItem", "get_icon", GET_ICON_HASH)

        private const val SET_ICON_OVERLAY_HASH = 666127730L
        @JvmField
        val setIconOverlayBind =
            ObjectCalls.getMethodBind("TreeItem", "set_icon_overlay", SET_ICON_OVERLAY_HASH)

        private const val GET_ICON_OVERLAY_HASH = 3536238170L
        @JvmField
        val getIconOverlayBind =
            ObjectCalls.getMethodBind("TreeItem", "get_icon_overlay", GET_ICON_OVERLAY_HASH)

        private const val SET_ICON_REGION_HASH = 1356297692L
        @JvmField
        val setIconRegionBind =
            ObjectCalls.getMethodBind("TreeItem", "set_icon_region", SET_ICON_REGION_HASH)

        private const val GET_ICON_REGION_HASH = 3327874267L
        @JvmField
        val getIconRegionBind =
            ObjectCalls.getMethodBind("TreeItem", "get_icon_region", GET_ICON_REGION_HASH)

        private const val SET_ICON_MAX_WIDTH_HASH = 3937882851L
        @JvmField
        val setIconMaxWidthBind =
            ObjectCalls.getMethodBind("TreeItem", "set_icon_max_width", SET_ICON_MAX_WIDTH_HASH)

        private const val GET_ICON_MAX_WIDTH_HASH = 923996154L
        @JvmField
        val getIconMaxWidthBind =
            ObjectCalls.getMethodBind("TreeItem", "get_icon_max_width", GET_ICON_MAX_WIDTH_HASH)

        private const val SET_ICON_MODULATE_HASH = 2878471219L
        @JvmField
        val setIconModulateBind =
            ObjectCalls.getMethodBind("TreeItem", "set_icon_modulate", SET_ICON_MODULATE_HASH)

        private const val GET_ICON_MODULATE_HASH = 3457211756L
        @JvmField
        val getIconModulateBind =
            ObjectCalls.getMethodBind("TreeItem", "get_icon_modulate", GET_ICON_MODULATE_HASH)

        private const val SET_RANGE_HASH = 1602489585L
        @JvmField
        val setRangeBind =
            ObjectCalls.getMethodBind("TreeItem", "set_range", SET_RANGE_HASH)

        private const val GET_RANGE_HASH = 2339986948L
        @JvmField
        val getRangeBind =
            ObjectCalls.getMethodBind("TreeItem", "get_range", GET_RANGE_HASH)

        private const val SET_RANGE_CONFIG_HASH = 1547181014L
        @JvmField
        val setRangeConfigBind =
            ObjectCalls.getMethodBind("TreeItem", "set_range_config", SET_RANGE_CONFIG_HASH)

        private const val GET_RANGE_CONFIG_HASH = 3554694381L
        @JvmField
        val getRangeConfigBind =
            ObjectCalls.getMethodBind("TreeItem", "get_range_config", GET_RANGE_CONFIG_HASH)

        private const val SET_METADATA_HASH = 2152698145L
        @JvmField
        val setMetadataBind =
            ObjectCalls.getMethodBind("TreeItem", "set_metadata", SET_METADATA_HASH)

        private const val GET_METADATA_HASH = 4227898402L
        @JvmField
        val getMetadataBind =
            ObjectCalls.getMethodBind("TreeItem", "get_metadata", GET_METADATA_HASH)

        private const val SET_CUSTOM_DRAW_HASH = 272420368L
        @JvmField
        val setCustomDrawBind =
            ObjectCalls.getMethodBind("TreeItem", "set_custom_draw", SET_CUSTOM_DRAW_HASH)

        private const val SET_CUSTOM_DRAW_CALLBACK_HASH = 957362965L
        @JvmField
        val setCustomDrawCallbackBind =
            ObjectCalls.getMethodBind("TreeItem", "set_custom_draw_callback", SET_CUSTOM_DRAW_CALLBACK_HASH)

        private const val GET_CUSTOM_DRAW_CALLBACK_HASH = 1317077508L
        @JvmField
        val getCustomDrawCallbackBind =
            ObjectCalls.getMethodBind("TreeItem", "get_custom_draw_callback", GET_CUSTOM_DRAW_CALLBACK_HASH)

        private const val SET_CUSTOM_STYLEBOX_HASH = 1433009359L
        @JvmField
        val setCustomStyleboxBind =
            ObjectCalls.getMethodBind("TreeItem", "set_custom_stylebox", SET_CUSTOM_STYLEBOX_HASH)

        private const val GET_CUSTOM_STYLEBOX_HASH = 3362509644L
        @JvmField
        val getCustomStyleboxBind =
            ObjectCalls.getMethodBind("TreeItem", "get_custom_stylebox", GET_CUSTOM_STYLEBOX_HASH)

        private const val SET_COLLAPSED_HASH = 2586408642L
        @JvmField
        val setCollapsedBind =
            ObjectCalls.getMethodBind("TreeItem", "set_collapsed", SET_COLLAPSED_HASH)

        private const val IS_COLLAPSED_HASH = 2240911060L
        @JvmField
        val isCollapsedBind =
            ObjectCalls.getMethodBind("TreeItem", "is_collapsed", IS_COLLAPSED_HASH)

        private const val SET_COLLAPSED_RECURSIVE_HASH = 2586408642L
        @JvmField
        val setCollapsedRecursiveBind =
            ObjectCalls.getMethodBind("TreeItem", "set_collapsed_recursive", SET_COLLAPSED_RECURSIVE_HASH)

        private const val IS_ANY_COLLAPSED_HASH = 2595650253L
        @JvmField
        val isAnyCollapsedBind =
            ObjectCalls.getMethodBind("TreeItem", "is_any_collapsed", IS_ANY_COLLAPSED_HASH)

        private const val SET_VISIBLE_HASH = 2586408642L
        @JvmField
        val setVisibleBind =
            ObjectCalls.getMethodBind("TreeItem", "set_visible", SET_VISIBLE_HASH)

        private const val IS_VISIBLE_HASH = 2240911060L
        @JvmField
        val isVisibleBind =
            ObjectCalls.getMethodBind("TreeItem", "is_visible", IS_VISIBLE_HASH)

        private const val IS_VISIBLE_IN_TREE_HASH = 36873697L
        @JvmField
        val isVisibleInTreeBind =
            ObjectCalls.getMethodBind("TreeItem", "is_visible_in_tree", IS_VISIBLE_IN_TREE_HASH)

        private const val UNCOLLAPSE_TREE_HASH = 3218959716L
        @JvmField
        val uncollapseTreeBind =
            ObjectCalls.getMethodBind("TreeItem", "uncollapse_tree", UNCOLLAPSE_TREE_HASH)

        private const val SET_CUSTOM_MINIMUM_HEIGHT_HASH = 1286410249L
        @JvmField
        val setCustomMinimumHeightBind =
            ObjectCalls.getMethodBind("TreeItem", "set_custom_minimum_height", SET_CUSTOM_MINIMUM_HEIGHT_HASH)

        private const val GET_CUSTOM_MINIMUM_HEIGHT_HASH = 3905245786L
        @JvmField
        val getCustomMinimumHeightBind =
            ObjectCalls.getMethodBind("TreeItem", "get_custom_minimum_height", GET_CUSTOM_MINIMUM_HEIGHT_HASH)

        private const val SET_SELECTABLE_HASH = 300928843L
        @JvmField
        val setSelectableBind =
            ObjectCalls.getMethodBind("TreeItem", "set_selectable", SET_SELECTABLE_HASH)

        private const val IS_SELECTABLE_HASH = 1116898809L
        @JvmField
        val isSelectableBind =
            ObjectCalls.getMethodBind("TreeItem", "is_selectable", IS_SELECTABLE_HASH)

        private const val IS_SELECTED_HASH = 3067735520L
        @JvmField
        val isSelectedBind =
            ObjectCalls.getMethodBind("TreeItem", "is_selected", IS_SELECTED_HASH)

        private const val SELECT_HASH = 972357352L
        @JvmField
        val selectBind =
            ObjectCalls.getMethodBind("TreeItem", "select", SELECT_HASH)

        private const val DESELECT_HASH = 1286410249L
        @JvmField
        val deselectBind =
            ObjectCalls.getMethodBind("TreeItem", "deselect", DESELECT_HASH)

        private const val SET_EDITABLE_HASH = 300928843L
        @JvmField
        val setEditableBind =
            ObjectCalls.getMethodBind("TreeItem", "set_editable", SET_EDITABLE_HASH)

        private const val IS_EDITABLE_HASH = 3067735520L
        @JvmField
        val isEditableBind =
            ObjectCalls.getMethodBind("TreeItem", "is_editable", IS_EDITABLE_HASH)

        private const val SET_CUSTOM_COLOR_HASH = 2878471219L
        @JvmField
        val setCustomColorBind =
            ObjectCalls.getMethodBind("TreeItem", "set_custom_color", SET_CUSTOM_COLOR_HASH)

        private const val GET_CUSTOM_COLOR_HASH = 3457211756L
        @JvmField
        val getCustomColorBind =
            ObjectCalls.getMethodBind("TreeItem", "get_custom_color", GET_CUSTOM_COLOR_HASH)

        private const val CLEAR_CUSTOM_COLOR_HASH = 1286410249L
        @JvmField
        val clearCustomColorBind =
            ObjectCalls.getMethodBind("TreeItem", "clear_custom_color", CLEAR_CUSTOM_COLOR_HASH)

        private const val SET_CUSTOM_FONT_HASH = 2637609184L
        @JvmField
        val setCustomFontBind =
            ObjectCalls.getMethodBind("TreeItem", "set_custom_font", SET_CUSTOM_FONT_HASH)

        private const val GET_CUSTOM_FONT_HASH = 4244553094L
        @JvmField
        val getCustomFontBind =
            ObjectCalls.getMethodBind("TreeItem", "get_custom_font", GET_CUSTOM_FONT_HASH)

        private const val SET_CUSTOM_FONT_SIZE_HASH = 3937882851L
        @JvmField
        val setCustomFontSizeBind =
            ObjectCalls.getMethodBind("TreeItem", "set_custom_font_size", SET_CUSTOM_FONT_SIZE_HASH)

        private const val GET_CUSTOM_FONT_SIZE_HASH = 923996154L
        @JvmField
        val getCustomFontSizeBind =
            ObjectCalls.getMethodBind("TreeItem", "get_custom_font_size", GET_CUSTOM_FONT_SIZE_HASH)

        private const val SET_CUSTOM_BG_COLOR_HASH = 894174518L
        @JvmField
        val setCustomBgColorBind =
            ObjectCalls.getMethodBind("TreeItem", "set_custom_bg_color", SET_CUSTOM_BG_COLOR_HASH)

        private const val CLEAR_CUSTOM_BG_COLOR_HASH = 1286410249L
        @JvmField
        val clearCustomBgColorBind =
            ObjectCalls.getMethodBind("TreeItem", "clear_custom_bg_color", CLEAR_CUSTOM_BG_COLOR_HASH)

        private const val GET_CUSTOM_BG_COLOR_HASH = 3457211756L
        @JvmField
        val getCustomBgColorBind =
            ObjectCalls.getMethodBind("TreeItem", "get_custom_bg_color", GET_CUSTOM_BG_COLOR_HASH)

        private const val SET_CUSTOM_AS_BUTTON_HASH = 300928843L
        @JvmField
        val setCustomAsButtonBind =
            ObjectCalls.getMethodBind("TreeItem", "set_custom_as_button", SET_CUSTOM_AS_BUTTON_HASH)

        private const val IS_CUSTOM_SET_AS_BUTTON_HASH = 1116898809L
        @JvmField
        val isCustomSetAsButtonBind =
            ObjectCalls.getMethodBind("TreeItem", "is_custom_set_as_button", IS_CUSTOM_SET_AS_BUTTON_HASH)

        private const val CLEAR_BUTTONS_HASH = 3218959716L
        @JvmField
        val clearButtonsBind =
            ObjectCalls.getMethodBind("TreeItem", "clear_buttons", CLEAR_BUTTONS_HASH)

        private const val ADD_BUTTON_HASH = 973481897L
        @JvmField
        val addButtonBind =
            ObjectCalls.getMethodBind("TreeItem", "add_button", ADD_BUTTON_HASH)

        private const val GET_BUTTON_COUNT_HASH = 923996154L
        @JvmField
        val getButtonCountBind =
            ObjectCalls.getMethodBind("TreeItem", "get_button_count", GET_BUTTON_COUNT_HASH)

        private const val GET_BUTTON_TOOLTIP_TEXT_HASH = 1391810591L
        @JvmField
        val getButtonTooltipTextBind =
            ObjectCalls.getMethodBind("TreeItem", "get_button_tooltip_text", GET_BUTTON_TOOLTIP_TEXT_HASH)

        private const val GET_BUTTON_ID_HASH = 3175239445L
        @JvmField
        val getButtonIdBind =
            ObjectCalls.getMethodBind("TreeItem", "get_button_id", GET_BUTTON_ID_HASH)

        private const val GET_BUTTON_BY_ID_HASH = 3175239445L
        @JvmField
        val getButtonByIdBind =
            ObjectCalls.getMethodBind("TreeItem", "get_button_by_id", GET_BUTTON_BY_ID_HASH)

        private const val GET_BUTTON_COLOR_HASH = 2165839948L
        @JvmField
        val getButtonColorBind =
            ObjectCalls.getMethodBind("TreeItem", "get_button_color", GET_BUTTON_COLOR_HASH)

        private const val GET_BUTTON_HASH = 2584904275L
        @JvmField
        val getButtonBind =
            ObjectCalls.getMethodBind("TreeItem", "get_button", GET_BUTTON_HASH)

        private const val SET_BUTTON_TOOLTIP_TEXT_HASH = 2285447957L
        @JvmField
        val setButtonTooltipTextBind =
            ObjectCalls.getMethodBind("TreeItem", "set_button_tooltip_text", SET_BUTTON_TOOLTIP_TEXT_HASH)

        private const val SET_BUTTON_HASH = 176101966L
        @JvmField
        val setButtonBind =
            ObjectCalls.getMethodBind("TreeItem", "set_button", SET_BUTTON_HASH)

        private const val ERASE_BUTTON_HASH = 3937882851L
        @JvmField
        val eraseButtonBind =
            ObjectCalls.getMethodBind("TreeItem", "erase_button", ERASE_BUTTON_HASH)

        private const val SET_BUTTON_DESCRIPTION_HASH = 2285447957L
        @JvmField
        val setButtonDescriptionBind =
            ObjectCalls.getMethodBind("TreeItem", "set_button_description", SET_BUTTON_DESCRIPTION_HASH)

        private const val SET_BUTTON_DISABLED_HASH = 1383440665L
        @JvmField
        val setButtonDisabledBind =
            ObjectCalls.getMethodBind("TreeItem", "set_button_disabled", SET_BUTTON_DISABLED_HASH)

        private const val SET_BUTTON_COLOR_HASH = 3733378741L
        @JvmField
        val setButtonColorBind =
            ObjectCalls.getMethodBind("TreeItem", "set_button_color", SET_BUTTON_COLOR_HASH)

        private const val IS_BUTTON_DISABLED_HASH = 2522259332L
        @JvmField
        val isButtonDisabledBind =
            ObjectCalls.getMethodBind("TreeItem", "is_button_disabled", IS_BUTTON_DISABLED_HASH)

        private const val SET_TOOLTIP_TEXT_HASH = 501894301L
        @JvmField
        val setTooltipTextBind =
            ObjectCalls.getMethodBind("TreeItem", "set_tooltip_text", SET_TOOLTIP_TEXT_HASH)

        private const val GET_TOOLTIP_TEXT_HASH = 844755477L
        @JvmField
        val getTooltipTextBind =
            ObjectCalls.getMethodBind("TreeItem", "get_tooltip_text", GET_TOOLTIP_TEXT_HASH)

        private const val SET_TEXT_ALIGNMENT_HASH = 3276431499L
        @JvmField
        val setTextAlignmentBind =
            ObjectCalls.getMethodBind("TreeItem", "set_text_alignment", SET_TEXT_ALIGNMENT_HASH)

        private const val GET_TEXT_ALIGNMENT_HASH = 4171562184L
        @JvmField
        val getTextAlignmentBind =
            ObjectCalls.getMethodBind("TreeItem", "get_text_alignment", GET_TEXT_ALIGNMENT_HASH)

        private const val SET_EXPAND_RIGHT_HASH = 300928843L
        @JvmField
        val setExpandRightBind =
            ObjectCalls.getMethodBind("TreeItem", "set_expand_right", SET_EXPAND_RIGHT_HASH)

        private const val GET_EXPAND_RIGHT_HASH = 1116898809L
        @JvmField
        val getExpandRightBind =
            ObjectCalls.getMethodBind("TreeItem", "get_expand_right", GET_EXPAND_RIGHT_HASH)

        private const val SET_DISABLE_FOLDING_HASH = 2586408642L
        @JvmField
        val setDisableFoldingBind =
            ObjectCalls.getMethodBind("TreeItem", "set_disable_folding", SET_DISABLE_FOLDING_HASH)

        private const val IS_FOLDING_DISABLED_HASH = 36873697L
        @JvmField
        val isFoldingDisabledBind =
            ObjectCalls.getMethodBind("TreeItem", "is_folding_disabled", IS_FOLDING_DISABLED_HASH)

        private const val SET_ACCEPT_CHILDREN_HASH = 2586408642L
        @JvmField
        val setAcceptChildrenBind =
            ObjectCalls.getMethodBind("TreeItem", "set_accept_children", SET_ACCEPT_CHILDREN_HASH)

        private const val IS_ACCEPTING_CHILDREN_HASH = 36873697L
        @JvmField
        val isAcceptingChildrenBind =
            ObjectCalls.getMethodBind("TreeItem", "is_accepting_children", IS_ACCEPTING_CHILDREN_HASH)

        private const val CREATE_CHILD_HASH = 954243986L
        @JvmField
        val createChildBind =
            ObjectCalls.getMethodBind("TreeItem", "create_child", CREATE_CHILD_HASH)

        private const val ADD_CHILD_HASH = 1819951137L
        @JvmField
        val addChildBind =
            ObjectCalls.getMethodBind("TreeItem", "add_child", ADD_CHILD_HASH)

        private const val REMOVE_CHILD_HASH = 1819951137L
        @JvmField
        val removeChildBind =
            ObjectCalls.getMethodBind("TreeItem", "remove_child", REMOVE_CHILD_HASH)

        private const val GET_TREE_HASH = 2243340556L
        @JvmField
        val getTreeBind =
            ObjectCalls.getMethodBind("TreeItem", "get_tree", GET_TREE_HASH)

        private const val GET_NEXT_HASH = 1514277247L
        @JvmField
        val getNextBind =
            ObjectCalls.getMethodBind("TreeItem", "get_next", GET_NEXT_HASH)

        private const val GET_PREV_HASH = 2768121250L
        @JvmField
        val getPrevBind =
            ObjectCalls.getMethodBind("TreeItem", "get_prev", GET_PREV_HASH)

        private const val GET_PARENT_HASH = 1514277247L
        @JvmField
        val getParentBind =
            ObjectCalls.getMethodBind("TreeItem", "get_parent", GET_PARENT_HASH)

        private const val GET_FIRST_CHILD_HASH = 1514277247L
        @JvmField
        val getFirstChildBind =
            ObjectCalls.getMethodBind("TreeItem", "get_first_child", GET_FIRST_CHILD_HASH)

        private const val GET_NEXT_IN_TREE_HASH = 1666920593L
        @JvmField
        val getNextInTreeBind =
            ObjectCalls.getMethodBind("TreeItem", "get_next_in_tree", GET_NEXT_IN_TREE_HASH)

        private const val GET_PREV_IN_TREE_HASH = 1666920593L
        @JvmField
        val getPrevInTreeBind =
            ObjectCalls.getMethodBind("TreeItem", "get_prev_in_tree", GET_PREV_IN_TREE_HASH)

        private const val GET_NEXT_VISIBLE_HASH = 1666920593L
        @JvmField
        val getNextVisibleBind =
            ObjectCalls.getMethodBind("TreeItem", "get_next_visible", GET_NEXT_VISIBLE_HASH)

        private const val GET_PREV_VISIBLE_HASH = 1666920593L
        @JvmField
        val getPrevVisibleBind =
            ObjectCalls.getMethodBind("TreeItem", "get_prev_visible", GET_PREV_VISIBLE_HASH)

        private const val GET_CHILD_HASH = 306700752L
        @JvmField
        val getChildBind =
            ObjectCalls.getMethodBind("TreeItem", "get_child", GET_CHILD_HASH)

        private const val GET_CHILD_COUNT_HASH = 2455072627L
        @JvmField
        val getChildCountBind =
            ObjectCalls.getMethodBind("TreeItem", "get_child_count", GET_CHILD_COUNT_HASH)

        private const val GET_CHILDREN_HASH = 2915620761L
        @JvmField
        val getChildrenBind =
            ObjectCalls.getMethodBind("TreeItem", "get_children", GET_CHILDREN_HASH)

        private const val GET_INDEX_HASH = 2455072627L
        @JvmField
        val getIndexBind =
            ObjectCalls.getMethodBind("TreeItem", "get_index", GET_INDEX_HASH)

        private const val MOVE_BEFORE_HASH = 1819951137L
        @JvmField
        val moveBeforeBind =
            ObjectCalls.getMethodBind("TreeItem", "move_before", MOVE_BEFORE_HASH)

        private const val MOVE_AFTER_HASH = 1819951137L
        @JvmField
        val moveAfterBind =
            ObjectCalls.getMethodBind("TreeItem", "move_after", MOVE_AFTER_HASH)

        private const val CALL_RECURSIVE_HASH = 2866548813L
        @JvmField
        val callRecursiveBind =
            ObjectCalls.getMethodBind("TreeItem", "call_recursive", CALL_RECURSIVE_HASH)
    }
}
