package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2

/**
 * An internal control for a single item inside `Tree`.
 *
 * Generated from Godot docs: TreeItem
 */
class TreeItem(handle: MemorySegment) : GodotObject(handle) {
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

    fun setCellMode(column: Int, mode: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setCellModeBind, handle, column, mode)
    }

    fun getCellMode(column: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getCellModeBind, handle, column)
    }

    fun setAutoTranslateMode(column: Int, mode: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setAutoTranslateModeBind, handle, column, mode)
    }

    fun getAutoTranslateMode(column: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getAutoTranslateModeBind, handle, column)
    }

    fun setEditMultiline(column: Int, multiline: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setEditMultilineBind, handle, column, multiline)
    }

    fun isEditMultiline(column: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isEditMultilineBind, handle, column)
    }

    fun setChecked(column: Int, checked: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCheckedBind, handle, column, checked)
    }

    fun setIndeterminate(column: Int, indeterminate: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setIndeterminateBind, handle, column, indeterminate)
    }

    fun isChecked(column: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isCheckedBind, handle, column)
    }

    fun isIndeterminate(column: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isIndeterminateBind, handle, column)
    }

    fun propagateCheck(column: Int, emitSignal: Boolean = true) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(propagateCheckBind, handle, column, emitSignal)
    }

    fun setText(column: Int, text: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setTextBind, handle, column, text)
    }

    fun getText(column: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getTextBind, handle, column)
    }

    fun setDescription(column: Int, description: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setDescriptionBind, handle, column, description)
    }

    fun getDescription(column: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getDescriptionBind, handle, column)
    }

    fun setTextDirection(column: Int, direction: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setTextDirectionBind, handle, column, direction)
    }

    fun getTextDirection(column: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getTextDirectionBind, handle, column)
    }

    fun setAutowrapMode(column: Int, autowrapMode: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setAutowrapModeBind, handle, column, autowrapMode)
    }

    fun getAutowrapMode(column: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getAutowrapModeBind, handle, column)
    }

    fun setAutowrapTrimFlags(column: Int, flags: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setAutowrapTrimFlagsBind, handle, column, flags)
    }

    fun getAutowrapTrimFlags(column: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getAutowrapTrimFlagsBind, handle, column)
    }

    fun setTextOverrunBehavior(column: Int, overrunBehavior: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setTextOverrunBehaviorBind, handle, column, overrunBehavior)
    }

    fun getTextOverrunBehavior(column: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getTextOverrunBehaviorBind, handle, column)
    }

    fun setStructuredTextBidiOverride(column: Int, parser: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setStructuredTextBidiOverrideBind, handle, column, parser)
    }

    fun getStructuredTextBidiOverride(column: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getStructuredTextBidiOverrideBind, handle, column)
    }

    fun setStructuredTextBidiOverrideOptions(column: Int, args: List<Any?>) {
        ObjectCalls.ptrcallWithIntAndArrayArg(setStructuredTextBidiOverrideOptionsBind, handle, column, args)
    }

    fun getStructuredTextBidiOverrideOptions(column: Int): List<Any?> {
        return ObjectCalls.ptrcallWithIntArgRetArray(getStructuredTextBidiOverrideOptionsBind, handle, column)
    }

    fun setLanguage(column: Int, language: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setLanguageBind, handle, column, language)
    }

    fun getLanguage(column: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getLanguageBind, handle, column)
    }

    fun setSuffix(column: Int, text: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setSuffixBind, handle, column, text)
    }

    fun getSuffix(column: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getSuffixBind, handle, column)
    }

    fun setIcon(column: Int, texture: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setIconBind, handle, column, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getIcon(column: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getIconBind, handle, column))
    }

    fun setIconOverlay(column: Int, texture: Texture2D?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setIconOverlayBind, handle, column, texture?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getIconOverlay(column: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getIconOverlayBind, handle, column))
    }

    fun setIconRegion(column: Int, region: Rect2) {
        ObjectCalls.ptrcallWithIntAndRect2Arg(setIconRegionBind, handle, column, region)
    }

    fun getIconRegion(column: Int): Rect2 {
        return ObjectCalls.ptrcallWithIntArgRetRect2(getIconRegionBind, handle, column)
    }

    fun setIconMaxWidth(column: Int, width: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setIconMaxWidthBind, handle, column, width)
    }

    fun getIconMaxWidth(column: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getIconMaxWidthBind, handle, column)
    }

    fun setIconModulate(column: Int, modulate: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setIconModulateBind, handle, column, modulate)
    }

    fun getIconModulate(column: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getIconModulateBind, handle, column)
    }

    fun setRange(column: Int, value: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setRangeBind, handle, column, value)
    }

    fun getRange(column: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getRangeBind, handle, column)
    }

    fun setRangeConfig(column: Int, min: Double, max: Double, step: Double, expr: Boolean = false) {
        ObjectCalls.ptrcallWithIntThreeDoubleBoolArgs(setRangeConfigBind, handle, column, min, max, step, expr)
    }

    fun getRangeConfig(column: Int): Map<String, Any?> {
        return ObjectCalls.ptrcallWithIntArgRetDictionary(getRangeConfigBind, handle, column)
    }

    fun setMetadata(column: Int, meta: Any?) {
        ObjectCalls.ptrcallWithIntAndVariantArg(setMetadataBind, handle, column, meta)
    }

    fun getMetadata(column: Int): Any? {
        return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getMetadataBind, handle, column)
    }

    fun setCustomDraw(column: Int, objectValue: GodotObject, callback: String) {
        ObjectCalls.ptrcallWithIntObjectStringNameArgs(setCustomDrawBind, handle, column, objectValue.handle, callback)
    }

    fun setCustomDrawCallback(column: Int, callback: GodotCallable) {
        ObjectCalls.ptrcallWithIntCallableArgs(setCustomDrawCallbackBind, handle, column, callback.target.handle, callback.method)
    }

    fun getCustomDrawCallback(column: Int): GodotCallable? {
        return ObjectCalls.ptrcallWithIntArgRetCallable(getCustomDrawCallbackBind, handle, column)
    }

    fun setCustomStylebox(column: Int, stylebox: StyleBox?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setCustomStyleboxBind, handle, column, stylebox?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getCustomStylebox(column: Int): StyleBox? {
        return StyleBox.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getCustomStyleboxBind, handle, column))
    }

    fun setCollapsed(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollapsedBind, handle, enable)
    }

    fun isCollapsed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollapsedBind, handle)
    }

    fun setCollapsedRecursive(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollapsedRecursiveBind, handle, enable)
    }

    fun isAnyCollapsed(onlyVisible: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithBoolArgRetBool(isAnyCollapsedBind, handle, onlyVisible)
    }

    fun setVisible(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVisibleBind, handle, enable)
    }

    fun isVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVisibleBind, handle)
    }

    fun isVisibleInTree(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVisibleInTreeBind, handle)
    }

    fun uncollapseTree() {
        ObjectCalls.ptrcallNoArgs(uncollapseTreeBind, handle)
    }

    fun setCustomMinimumHeight(height: Int) {
        ObjectCalls.ptrcallWithIntArg(setCustomMinimumHeightBind, handle, height)
    }

    fun getCustomMinimumHeight(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCustomMinimumHeightBind, handle)
    }

    fun setSelectable(column: Int, selectable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setSelectableBind, handle, column, selectable)
    }

    fun isSelectable(column: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isSelectableBind, handle, column)
    }

    fun isSelected(column: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isSelectedBind, handle, column)
    }

    /**
     * Selects the given `column`. If `set_as_cursor` is `true`, the `Tree`'s cursor will be moved to
     * this item (only matters if `Tree.select_mode` is set to `Tree.SELECT_MULTI`).
     *
     * Generated from Godot docs: TreeItem.select
     */
    fun select(column: Int, setAsCursor: Boolean = true) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(selectBind, handle, column, setAsCursor)
    }

    /**
     * Deselects the given column.
     *
     * Generated from Godot docs: TreeItem.deselect
     */
    fun deselect(column: Int) {
        ObjectCalls.ptrcallWithIntArg(deselectBind, handle, column)
    }

    fun setEditable(column: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setEditableBind, handle, column, enabled)
    }

    fun isEditable(column: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isEditableBind, handle, column)
    }

    fun setCustomColor(column: Int, color: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setCustomColorBind, handle, column, color)
    }

    fun getCustomColor(column: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getCustomColorBind, handle, column)
    }

    fun clearCustomColor(column: Int) {
        ObjectCalls.ptrcallWithIntArg(clearCustomColorBind, handle, column)
    }

    fun setCustomFont(column: Int, font: Font?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setCustomFontBind, handle, column, font?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getCustomFont(column: Int): Font? {
        return Font.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getCustomFontBind, handle, column))
    }

    fun setCustomFontSize(column: Int, fontSize: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setCustomFontSizeBind, handle, column, fontSize)
    }

    fun getCustomFontSize(column: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getCustomFontSizeBind, handle, column)
    }

    fun setCustomBgColor(column: Int, color: Color, justOutline: Boolean = false) {
        ObjectCalls.ptrcallWithIntColorBoolArgs(setCustomBgColorBind, handle, column, color, justOutline)
    }

    fun clearCustomBgColor(column: Int) {
        ObjectCalls.ptrcallWithIntArg(clearCustomBgColorBind, handle, column)
    }

    fun getCustomBgColor(column: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getCustomBgColorBind, handle, column)
    }

    fun setCustomAsButton(column: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setCustomAsButtonBind, handle, column, enable)
    }

    fun isCustomSetAsButton(column: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isCustomSetAsButtonBind, handle, column)
    }

    fun clearButtons() {
        ObjectCalls.ptrcallNoArgs(clearButtonsBind, handle)
    }

    fun addButton(column: Int, button: Texture2D?, id: Int = -1, disabled: Boolean = false, tooltipText: String = "", description: String = "") {
        ObjectCalls.ptrcallWithIntObjectIntBoolTwoStringArgs(addButtonBind, handle, column, button?.requireOpenHandle() ?: MemorySegment.NULL, id, disabled, tooltipText, description)
    }

    fun getButtonCount(column: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getButtonCountBind, handle, column)
    }

    fun getButtonTooltipText(column: Int, buttonIndex: Int): String {
        return ObjectCalls.ptrcallWithTwoIntArgsRetString(getButtonTooltipTextBind, handle, column, buttonIndex)
    }

    fun getButtonId(column: Int, buttonIndex: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getButtonIdBind, handle, column, buttonIndex)
    }

    fun getButtonById(column: Int, id: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getButtonByIdBind, handle, column, id)
    }

    fun getButtonColor(column: Int, id: Int): Color {
        return ObjectCalls.ptrcallWithTwoIntArgsRetColor(getButtonColorBind, handle, column, id)
    }

    fun getButton(column: Int, buttonIndex: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithTwoIntArgsRetObject(getButtonBind, handle, column, buttonIndex))
    }

    fun setButtonTooltipText(column: Int, buttonIndex: Int, tooltip: String) {
        ObjectCalls.ptrcallWithTwoIntAndStringArgs(setButtonTooltipTextBind, handle, column, buttonIndex, tooltip)
    }

    fun setButton(column: Int, buttonIndex: Int, button: Texture2D?) {
        ObjectCalls.ptrcallWithTwoIntAndObjectArg(setButtonBind, handle, column, buttonIndex, button?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun eraseButton(column: Int, buttonIndex: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(eraseButtonBind, handle, column, buttonIndex)
    }

    fun setButtonDescription(column: Int, buttonIndex: Int, description: String) {
        ObjectCalls.ptrcallWithTwoIntAndStringArgs(setButtonDescriptionBind, handle, column, buttonIndex, description)
    }

    fun setButtonDisabled(column: Int, buttonIndex: Int, disabled: Boolean) {
        ObjectCalls.ptrcallWithTwoIntAndBoolArgs(setButtonDisabledBind, handle, column, buttonIndex, disabled)
    }

    fun setButtonColor(column: Int, buttonIndex: Int, color: Color) {
        ObjectCalls.ptrcallWithTwoIntAndColorArg(setButtonColorBind, handle, column, buttonIndex, color)
    }

    fun isButtonDisabled(column: Int, buttonIndex: Int): Boolean {
        return ObjectCalls.ptrcallWithTwoIntArgsRetBool(isButtonDisabledBind, handle, column, buttonIndex)
    }

    fun setTooltipText(column: Int, tooltip: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setTooltipTextBind, handle, column, tooltip)
    }

    fun getTooltipText(column: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getTooltipTextBind, handle, column)
    }

    fun setTextAlignment(column: Int, textAlignment: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setTextAlignmentBind, handle, column, textAlignment)
    }

    fun getTextAlignment(column: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getTextAlignmentBind, handle, column)
    }

    fun setExpandRight(column: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setExpandRightBind, handle, column, enable)
    }

    fun getExpandRight(column: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getExpandRightBind, handle, column)
    }

    fun setDisableFolding(disable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDisableFoldingBind, handle, disable)
    }

    fun isFoldingDisabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFoldingDisabledBind, handle)
    }

    fun setAcceptChildren(allowed: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAcceptChildrenBind, handle, allowed)
    }

    fun isAcceptingChildren(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAcceptingChildrenBind, handle)
    }

    fun createChild(index: Int = -1): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallWithIntArgRetObject(createChildBind, handle, index))
    }

    fun addChild(child: TreeItem) {
        ObjectCalls.ptrcallWithObjectArgs(addChildBind, handle, listOf(child.handle))
    }

    fun removeChild(child: TreeItem) {
        ObjectCalls.ptrcallWithObjectArgs(removeChildBind, handle, listOf(child.handle))
    }

    fun getTree(): Tree? {
        return Tree.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTreeBind, handle))
    }

    fun getNext(): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallNoArgsRetObject(getNextBind, handle))
    }

    fun getPrev(): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPrevBind, handle))
    }

    fun getParent(): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallNoArgsRetObject(getParentBind, handle))
    }

    fun getFirstChild(): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallNoArgsRetObject(getFirstChildBind, handle))
    }

    fun getNextInTree(wrap: Boolean = false): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallWithBoolArgRetObject(getNextInTreeBind, handle, wrap))
    }

    fun getPrevInTree(wrap: Boolean = false): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallWithBoolArgRetObject(getPrevInTreeBind, handle, wrap))
    }

    fun getNextVisible(wrap: Boolean = false): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallWithBoolArgRetObject(getNextVisibleBind, handle, wrap))
    }

    fun getPrevVisible(wrap: Boolean = false): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallWithBoolArgRetObject(getPrevVisibleBind, handle, wrap))
    }

    fun getChild(index: Int): TreeItem? {
        return TreeItem.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getChildBind, handle, index))
    }

    fun getChildCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getChildCountBind, handle)
    }

    fun getChildren(): List<TreeItem> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getChildrenBind, handle, TreeItem::fromHandle)
    }

    fun getIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getIndexBind, handle)
    }

    fun moveBefore(item: TreeItem) {
        ObjectCalls.ptrcallWithObjectArgs(moveBeforeBind, handle, listOf(item.handle))
    }

    fun moveAfter(item: TreeItem) {
        ObjectCalls.ptrcallWithObjectArgs(moveAfterBind, handle, listOf(item.handle))
    }

    fun callRecursive(method: String, vararg extraArgs: Any?) {
        ObjectCalls.callWithVariantArgs(callRecursiveBind, handle, listOf(method, *extraArgs))
    }

    companion object {
        const val CELL_MODE_STRING: Long = 0L
        const val CELL_MODE_CHECK: Long = 1L
        const val CELL_MODE_RANGE: Long = 2L
        const val CELL_MODE_ICON: Long = 3L
        const val CELL_MODE_CUSTOM: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): TreeItem? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TreeItem? =
            if (handle.address() == 0L) null else TreeItem(handle)

        private const val SET_CELL_MODE_HASH = 289920701L
        private val setCellModeBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_cell_mode", SET_CELL_MODE_HASH)
        }

        private const val GET_CELL_MODE_HASH = 3406114978L
        private val getCellModeBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_cell_mode", GET_CELL_MODE_HASH)
        }

        private const val SET_AUTO_TRANSLATE_MODE_HASH = 287402019L
        private val setAutoTranslateModeBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_auto_translate_mode", SET_AUTO_TRANSLATE_MODE_HASH)
        }

        private const val GET_AUTO_TRANSLATE_MODE_HASH = 906302372L
        private val getAutoTranslateModeBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_auto_translate_mode", GET_AUTO_TRANSLATE_MODE_HASH)
        }

        private const val SET_EDIT_MULTILINE_HASH = 300928843L
        private val setEditMultilineBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_edit_multiline", SET_EDIT_MULTILINE_HASH)
        }

        private const val IS_EDIT_MULTILINE_HASH = 1116898809L
        private val isEditMultilineBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "is_edit_multiline", IS_EDIT_MULTILINE_HASH)
        }

        private const val SET_CHECKED_HASH = 300928843L
        private val setCheckedBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_checked", SET_CHECKED_HASH)
        }

        private const val SET_INDETERMINATE_HASH = 300928843L
        private val setIndeterminateBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_indeterminate", SET_INDETERMINATE_HASH)
        }

        private const val IS_CHECKED_HASH = 1116898809L
        private val isCheckedBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "is_checked", IS_CHECKED_HASH)
        }

        private const val IS_INDETERMINATE_HASH = 1116898809L
        private val isIndeterminateBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "is_indeterminate", IS_INDETERMINATE_HASH)
        }

        private const val PROPAGATE_CHECK_HASH = 972357352L
        private val propagateCheckBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "propagate_check", PROPAGATE_CHECK_HASH)
        }

        private const val SET_TEXT_HASH = 501894301L
        private val setTextBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_text", SET_TEXT_HASH)
        }

        private const val GET_TEXT_HASH = 844755477L
        private val getTextBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_text", GET_TEXT_HASH)
        }

        private const val SET_DESCRIPTION_HASH = 501894301L
        private val setDescriptionBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_description", SET_DESCRIPTION_HASH)
        }

        private const val GET_DESCRIPTION_HASH = 844755477L
        private val getDescriptionBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_description", GET_DESCRIPTION_HASH)
        }

        private const val SET_TEXT_DIRECTION_HASH = 1707680378L
        private val setTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_text_direction", SET_TEXT_DIRECTION_HASH)
        }

        private const val GET_TEXT_DIRECTION_HASH = 4235602388L
        private val getTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_text_direction", GET_TEXT_DIRECTION_HASH)
        }

        private const val SET_AUTOWRAP_MODE_HASH = 3633006561L
        private val setAutowrapModeBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_autowrap_mode", SET_AUTOWRAP_MODE_HASH)
        }

        private const val GET_AUTOWRAP_MODE_HASH = 2902757236L
        private val getAutowrapModeBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_autowrap_mode", GET_AUTOWRAP_MODE_HASH)
        }

        private const val SET_AUTOWRAP_TRIM_FLAGS_HASH = 2186029660L
        private val setAutowrapTrimFlagsBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_autowrap_trim_flags", SET_AUTOWRAP_TRIM_FLAGS_HASH)
        }

        private const val GET_AUTOWRAP_TRIM_FLAGS_HASH = 3513056523L
        private val getAutowrapTrimFlagsBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_autowrap_trim_flags", GET_AUTOWRAP_TRIM_FLAGS_HASH)
        }

        private const val SET_TEXT_OVERRUN_BEHAVIOR_HASH = 1940772195L
        private val setTextOverrunBehaviorBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_text_overrun_behavior", SET_TEXT_OVERRUN_BEHAVIOR_HASH)
        }

        private const val GET_TEXT_OVERRUN_BEHAVIOR_HASH = 3782727860L
        private val getTextOverrunBehaviorBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_text_overrun_behavior", GET_TEXT_OVERRUN_BEHAVIOR_HASH)
        }

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 868756907L
        private val setStructuredTextBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_structured_text_bidi_override", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)
        }

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 3377823772L
        private val getStructuredTextBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_structured_text_bidi_override", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)
        }

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 537221740L
        private val setStructuredTextBidiOverrideOptionsBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_structured_text_bidi_override_options", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
        }

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 663333327L
        private val getStructuredTextBidiOverrideOptionsBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_structured_text_bidi_override_options", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
        }

        private const val SET_LANGUAGE_HASH = 501894301L
        private val setLanguageBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_language", SET_LANGUAGE_HASH)
        }

        private const val GET_LANGUAGE_HASH = 844755477L
        private val getLanguageBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_language", GET_LANGUAGE_HASH)
        }

        private const val SET_SUFFIX_HASH = 501894301L
        private val setSuffixBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_suffix", SET_SUFFIX_HASH)
        }

        private const val GET_SUFFIX_HASH = 844755477L
        private val getSuffixBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_suffix", GET_SUFFIX_HASH)
        }

        private const val SET_ICON_HASH = 666127730L
        private val setIconBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_icon", SET_ICON_HASH)
        }

        private const val GET_ICON_HASH = 3536238170L
        private val getIconBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_icon", GET_ICON_HASH)
        }

        private const val SET_ICON_OVERLAY_HASH = 666127730L
        private val setIconOverlayBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_icon_overlay", SET_ICON_OVERLAY_HASH)
        }

        private const val GET_ICON_OVERLAY_HASH = 3536238170L
        private val getIconOverlayBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_icon_overlay", GET_ICON_OVERLAY_HASH)
        }

        private const val SET_ICON_REGION_HASH = 1356297692L
        private val setIconRegionBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_icon_region", SET_ICON_REGION_HASH)
        }

        private const val GET_ICON_REGION_HASH = 3327874267L
        private val getIconRegionBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_icon_region", GET_ICON_REGION_HASH)
        }

        private const val SET_ICON_MAX_WIDTH_HASH = 3937882851L
        private val setIconMaxWidthBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_icon_max_width", SET_ICON_MAX_WIDTH_HASH)
        }

        private const val GET_ICON_MAX_WIDTH_HASH = 923996154L
        private val getIconMaxWidthBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_icon_max_width", GET_ICON_MAX_WIDTH_HASH)
        }

        private const val SET_ICON_MODULATE_HASH = 2878471219L
        private val setIconModulateBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_icon_modulate", SET_ICON_MODULATE_HASH)
        }

        private const val GET_ICON_MODULATE_HASH = 3457211756L
        private val getIconModulateBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_icon_modulate", GET_ICON_MODULATE_HASH)
        }

        private const val SET_RANGE_HASH = 1602489585L
        private val setRangeBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_range", SET_RANGE_HASH)
        }

        private const val GET_RANGE_HASH = 2339986948L
        private val getRangeBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_range", GET_RANGE_HASH)
        }

        private const val SET_RANGE_CONFIG_HASH = 1547181014L
        private val setRangeConfigBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_range_config", SET_RANGE_CONFIG_HASH)
        }

        private const val GET_RANGE_CONFIG_HASH = 3554694381L
        private val getRangeConfigBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_range_config", GET_RANGE_CONFIG_HASH)
        }

        private const val SET_METADATA_HASH = 2152698145L
        private val setMetadataBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_metadata", SET_METADATA_HASH)
        }

        private const val GET_METADATA_HASH = 4227898402L
        private val getMetadataBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_metadata", GET_METADATA_HASH)
        }

        private const val SET_CUSTOM_DRAW_HASH = 272420368L
        private val setCustomDrawBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_custom_draw", SET_CUSTOM_DRAW_HASH)
        }

        private const val SET_CUSTOM_DRAW_CALLBACK_HASH = 957362965L
        private val setCustomDrawCallbackBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_custom_draw_callback", SET_CUSTOM_DRAW_CALLBACK_HASH)
        }

        private const val GET_CUSTOM_DRAW_CALLBACK_HASH = 1317077508L
        private val getCustomDrawCallbackBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_custom_draw_callback", GET_CUSTOM_DRAW_CALLBACK_HASH)
        }

        private const val SET_CUSTOM_STYLEBOX_HASH = 1433009359L
        private val setCustomStyleboxBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_custom_stylebox", SET_CUSTOM_STYLEBOX_HASH)
        }

        private const val GET_CUSTOM_STYLEBOX_HASH = 3362509644L
        private val getCustomStyleboxBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_custom_stylebox", GET_CUSTOM_STYLEBOX_HASH)
        }

        private const val SET_COLLAPSED_HASH = 2586408642L
        private val setCollapsedBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_collapsed", SET_COLLAPSED_HASH)
        }

        private const val IS_COLLAPSED_HASH = 2240911060L
        private val isCollapsedBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "is_collapsed", IS_COLLAPSED_HASH)
        }

        private const val SET_COLLAPSED_RECURSIVE_HASH = 2586408642L
        private val setCollapsedRecursiveBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_collapsed_recursive", SET_COLLAPSED_RECURSIVE_HASH)
        }

        private const val IS_ANY_COLLAPSED_HASH = 2595650253L
        private val isAnyCollapsedBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "is_any_collapsed", IS_ANY_COLLAPSED_HASH)
        }

        private const val SET_VISIBLE_HASH = 2586408642L
        private val setVisibleBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_visible", SET_VISIBLE_HASH)
        }

        private const val IS_VISIBLE_HASH = 2240911060L
        private val isVisibleBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "is_visible", IS_VISIBLE_HASH)
        }

        private const val IS_VISIBLE_IN_TREE_HASH = 36873697L
        private val isVisibleInTreeBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "is_visible_in_tree", IS_VISIBLE_IN_TREE_HASH)
        }

        private const val UNCOLLAPSE_TREE_HASH = 3218959716L
        private val uncollapseTreeBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "uncollapse_tree", UNCOLLAPSE_TREE_HASH)
        }

        private const val SET_CUSTOM_MINIMUM_HEIGHT_HASH = 1286410249L
        private val setCustomMinimumHeightBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_custom_minimum_height", SET_CUSTOM_MINIMUM_HEIGHT_HASH)
        }

        private const val GET_CUSTOM_MINIMUM_HEIGHT_HASH = 3905245786L
        private val getCustomMinimumHeightBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_custom_minimum_height", GET_CUSTOM_MINIMUM_HEIGHT_HASH)
        }

        private const val SET_SELECTABLE_HASH = 300928843L
        private val setSelectableBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_selectable", SET_SELECTABLE_HASH)
        }

        private const val IS_SELECTABLE_HASH = 1116898809L
        private val isSelectableBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "is_selectable", IS_SELECTABLE_HASH)
        }

        private const val IS_SELECTED_HASH = 3067735520L
        private val isSelectedBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "is_selected", IS_SELECTED_HASH)
        }

        private const val SELECT_HASH = 972357352L
        private val selectBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "select", SELECT_HASH)
        }

        private const val DESELECT_HASH = 1286410249L
        private val deselectBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "deselect", DESELECT_HASH)
        }

        private const val SET_EDITABLE_HASH = 300928843L
        private val setEditableBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_editable", SET_EDITABLE_HASH)
        }

        private const val IS_EDITABLE_HASH = 3067735520L
        private val isEditableBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "is_editable", IS_EDITABLE_HASH)
        }

        private const val SET_CUSTOM_COLOR_HASH = 2878471219L
        private val setCustomColorBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_custom_color", SET_CUSTOM_COLOR_HASH)
        }

        private const val GET_CUSTOM_COLOR_HASH = 3457211756L
        private val getCustomColorBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_custom_color", GET_CUSTOM_COLOR_HASH)
        }

        private const val CLEAR_CUSTOM_COLOR_HASH = 1286410249L
        private val clearCustomColorBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "clear_custom_color", CLEAR_CUSTOM_COLOR_HASH)
        }

        private const val SET_CUSTOM_FONT_HASH = 2637609184L
        private val setCustomFontBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_custom_font", SET_CUSTOM_FONT_HASH)
        }

        private const val GET_CUSTOM_FONT_HASH = 4244553094L
        private val getCustomFontBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_custom_font", GET_CUSTOM_FONT_HASH)
        }

        private const val SET_CUSTOM_FONT_SIZE_HASH = 3937882851L
        private val setCustomFontSizeBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_custom_font_size", SET_CUSTOM_FONT_SIZE_HASH)
        }

        private const val GET_CUSTOM_FONT_SIZE_HASH = 923996154L
        private val getCustomFontSizeBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_custom_font_size", GET_CUSTOM_FONT_SIZE_HASH)
        }

        private const val SET_CUSTOM_BG_COLOR_HASH = 894174518L
        private val setCustomBgColorBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_custom_bg_color", SET_CUSTOM_BG_COLOR_HASH)
        }

        private const val CLEAR_CUSTOM_BG_COLOR_HASH = 1286410249L
        private val clearCustomBgColorBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "clear_custom_bg_color", CLEAR_CUSTOM_BG_COLOR_HASH)
        }

        private const val GET_CUSTOM_BG_COLOR_HASH = 3457211756L
        private val getCustomBgColorBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_custom_bg_color", GET_CUSTOM_BG_COLOR_HASH)
        }

        private const val SET_CUSTOM_AS_BUTTON_HASH = 300928843L
        private val setCustomAsButtonBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_custom_as_button", SET_CUSTOM_AS_BUTTON_HASH)
        }

        private const val IS_CUSTOM_SET_AS_BUTTON_HASH = 1116898809L
        private val isCustomSetAsButtonBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "is_custom_set_as_button", IS_CUSTOM_SET_AS_BUTTON_HASH)
        }

        private const val CLEAR_BUTTONS_HASH = 3218959716L
        private val clearButtonsBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "clear_buttons", CLEAR_BUTTONS_HASH)
        }

        private const val ADD_BUTTON_HASH = 973481897L
        private val addButtonBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "add_button", ADD_BUTTON_HASH)
        }

        private const val GET_BUTTON_COUNT_HASH = 923996154L
        private val getButtonCountBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_button_count", GET_BUTTON_COUNT_HASH)
        }

        private const val GET_BUTTON_TOOLTIP_TEXT_HASH = 1391810591L
        private val getButtonTooltipTextBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_button_tooltip_text", GET_BUTTON_TOOLTIP_TEXT_HASH)
        }

        private const val GET_BUTTON_ID_HASH = 3175239445L
        private val getButtonIdBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_button_id", GET_BUTTON_ID_HASH)
        }

        private const val GET_BUTTON_BY_ID_HASH = 3175239445L
        private val getButtonByIdBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_button_by_id", GET_BUTTON_BY_ID_HASH)
        }

        private const val GET_BUTTON_COLOR_HASH = 2165839948L
        private val getButtonColorBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_button_color", GET_BUTTON_COLOR_HASH)
        }

        private const val GET_BUTTON_HASH = 2584904275L
        private val getButtonBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_button", GET_BUTTON_HASH)
        }

        private const val SET_BUTTON_TOOLTIP_TEXT_HASH = 2285447957L
        private val setButtonTooltipTextBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_button_tooltip_text", SET_BUTTON_TOOLTIP_TEXT_HASH)
        }

        private const val SET_BUTTON_HASH = 176101966L
        private val setButtonBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_button", SET_BUTTON_HASH)
        }

        private const val ERASE_BUTTON_HASH = 3937882851L
        private val eraseButtonBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "erase_button", ERASE_BUTTON_HASH)
        }

        private const val SET_BUTTON_DESCRIPTION_HASH = 2285447957L
        private val setButtonDescriptionBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_button_description", SET_BUTTON_DESCRIPTION_HASH)
        }

        private const val SET_BUTTON_DISABLED_HASH = 1383440665L
        private val setButtonDisabledBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_button_disabled", SET_BUTTON_DISABLED_HASH)
        }

        private const val SET_BUTTON_COLOR_HASH = 3733378741L
        private val setButtonColorBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_button_color", SET_BUTTON_COLOR_HASH)
        }

        private const val IS_BUTTON_DISABLED_HASH = 2522259332L
        private val isButtonDisabledBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "is_button_disabled", IS_BUTTON_DISABLED_HASH)
        }

        private const val SET_TOOLTIP_TEXT_HASH = 501894301L
        private val setTooltipTextBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_tooltip_text", SET_TOOLTIP_TEXT_HASH)
        }

        private const val GET_TOOLTIP_TEXT_HASH = 844755477L
        private val getTooltipTextBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_tooltip_text", GET_TOOLTIP_TEXT_HASH)
        }

        private const val SET_TEXT_ALIGNMENT_HASH = 3276431499L
        private val setTextAlignmentBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_text_alignment", SET_TEXT_ALIGNMENT_HASH)
        }

        private const val GET_TEXT_ALIGNMENT_HASH = 4171562184L
        private val getTextAlignmentBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_text_alignment", GET_TEXT_ALIGNMENT_HASH)
        }

        private const val SET_EXPAND_RIGHT_HASH = 300928843L
        private val setExpandRightBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_expand_right", SET_EXPAND_RIGHT_HASH)
        }

        private const val GET_EXPAND_RIGHT_HASH = 1116898809L
        private val getExpandRightBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_expand_right", GET_EXPAND_RIGHT_HASH)
        }

        private const val SET_DISABLE_FOLDING_HASH = 2586408642L
        private val setDisableFoldingBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_disable_folding", SET_DISABLE_FOLDING_HASH)
        }

        private const val IS_FOLDING_DISABLED_HASH = 36873697L
        private val isFoldingDisabledBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "is_folding_disabled", IS_FOLDING_DISABLED_HASH)
        }

        private const val SET_ACCEPT_CHILDREN_HASH = 2586408642L
        private val setAcceptChildrenBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "set_accept_children", SET_ACCEPT_CHILDREN_HASH)
        }

        private const val IS_ACCEPTING_CHILDREN_HASH = 36873697L
        private val isAcceptingChildrenBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "is_accepting_children", IS_ACCEPTING_CHILDREN_HASH)
        }

        private const val CREATE_CHILD_HASH = 954243986L
        private val createChildBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "create_child", CREATE_CHILD_HASH)
        }

        private const val ADD_CHILD_HASH = 1819951137L
        private val addChildBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "add_child", ADD_CHILD_HASH)
        }

        private const val REMOVE_CHILD_HASH = 1819951137L
        private val removeChildBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "remove_child", REMOVE_CHILD_HASH)
        }

        private const val GET_TREE_HASH = 2243340556L
        private val getTreeBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_tree", GET_TREE_HASH)
        }

        private const val GET_NEXT_HASH = 1514277247L
        private val getNextBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_next", GET_NEXT_HASH)
        }

        private const val GET_PREV_HASH = 2768121250L
        private val getPrevBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_prev", GET_PREV_HASH)
        }

        private const val GET_PARENT_HASH = 1514277247L
        private val getParentBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_parent", GET_PARENT_HASH)
        }

        private const val GET_FIRST_CHILD_HASH = 1514277247L
        private val getFirstChildBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_first_child", GET_FIRST_CHILD_HASH)
        }

        private const val GET_NEXT_IN_TREE_HASH = 1666920593L
        private val getNextInTreeBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_next_in_tree", GET_NEXT_IN_TREE_HASH)
        }

        private const val GET_PREV_IN_TREE_HASH = 1666920593L
        private val getPrevInTreeBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_prev_in_tree", GET_PREV_IN_TREE_HASH)
        }

        private const val GET_NEXT_VISIBLE_HASH = 1666920593L
        private val getNextVisibleBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_next_visible", GET_NEXT_VISIBLE_HASH)
        }

        private const val GET_PREV_VISIBLE_HASH = 1666920593L
        private val getPrevVisibleBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_prev_visible", GET_PREV_VISIBLE_HASH)
        }

        private const val GET_CHILD_HASH = 306700752L
        private val getChildBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_child", GET_CHILD_HASH)
        }

        private const val GET_CHILD_COUNT_HASH = 2455072627L
        private val getChildCountBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_child_count", GET_CHILD_COUNT_HASH)
        }

        private const val GET_CHILDREN_HASH = 2915620761L
        private val getChildrenBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_children", GET_CHILDREN_HASH)
        }

        private const val GET_INDEX_HASH = 2455072627L
        private val getIndexBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "get_index", GET_INDEX_HASH)
        }

        private const val MOVE_BEFORE_HASH = 1819951137L
        private val moveBeforeBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "move_before", MOVE_BEFORE_HASH)
        }

        private const val MOVE_AFTER_HASH = 1819951137L
        private val moveAfterBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "move_after", MOVE_AFTER_HASH)
        }

        private const val CALL_RECURSIVE_HASH = 2866548813L
        private val callRecursiveBind by lazy {
            ObjectCalls.getMethodBind("TreeItem", "call_recursive", CALL_RECURSIVE_HASH)
        }
    }
}
