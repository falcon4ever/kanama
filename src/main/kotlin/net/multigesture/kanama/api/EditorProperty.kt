package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Custom control for editing properties that can be added to the `EditorInspector`.
 *
 * Generated from Godot docs: EditorProperty
 */
class EditorProperty(handle: MemorySegment) : Container(handle) {
    var label: String
        @JvmName("labelProperty")
        get() = getLabel()
        @JvmName("setLabelProperty")
        set(value) = setLabel(value)

    var readOnly: Boolean
        @JvmName("readOnlyProperty")
        get() = isReadOnly()
        @JvmName("setReadOnlyProperty")
        set(value) = setReadOnly(value)

    var drawLabel: Boolean
        @JvmName("drawLabelProperty")
        get() = isDrawLabel()
        @JvmName("setDrawLabelProperty")
        set(value) = setDrawLabel(value)

    var drawBackground: Boolean
        @JvmName("drawBackgroundProperty")
        get() = isDrawBackground()
        @JvmName("setDrawBackgroundProperty")
        set(value) = setDrawBackground(value)

    var checkable: Boolean
        @JvmName("checkableProperty")
        get() = isCheckable()
        @JvmName("setCheckableProperty")
        set(value) = setCheckable(value)

    var checked: Boolean
        @JvmName("checkedProperty")
        get() = isChecked()
        @JvmName("setCheckedProperty")
        set(value) = setChecked(value)

    var drawWarning: Boolean
        @JvmName("drawWarningProperty")
        get() = isDrawWarning()
        @JvmName("setDrawWarningProperty")
        set(value) = setDrawWarning(value)

    var keying: Boolean
        @JvmName("keyingProperty")
        get() = isKeying()
        @JvmName("setKeyingProperty")
        set(value) = setKeying(value)

    var deletable: Boolean
        @JvmName("deletableProperty")
        get() = isDeletable()
        @JvmName("setDeletableProperty")
        set(value) = setDeletable(value)

    var selectable: Boolean
        @JvmName("selectableProperty")
        get() = isSelectable()
        @JvmName("setSelectableProperty")
        set(value) = setSelectable(value)

    var useFolding: Boolean
        @JvmName("useFoldingProperty")
        get() = isUsingFolding()
        @JvmName("setUseFoldingProperty")
        set(value) = setUseFolding(value)

    var nameSplitRatio: Double
        @JvmName("nameSplitRatioProperty")
        get() = getNameSplitRatio()
        @JvmName("setNameSplitRatioProperty")
        set(value) = setNameSplitRatio(value)

    fun setLabel(text: String) {
        ObjectCalls.ptrcallWithStringArg(setLabelBind, handle, text)
    }

    fun getLabel(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLabelBind, handle)
    }

    fun setReadOnly(readOnly: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setReadOnlyBind, handle, readOnly)
    }

    fun isReadOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isReadOnlyBind, handle)
    }

    fun setDrawLabel(drawLabel: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawLabelBind, handle, drawLabel)
    }

    fun isDrawLabel(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawLabelBind, handle)
    }

    fun setDrawBackground(drawBackground: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawBackgroundBind, handle, drawBackground)
    }

    fun isDrawBackground(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawBackgroundBind, handle)
    }

    fun setCheckable(checkable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCheckableBind, handle, checkable)
    }

    fun isCheckable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCheckableBind, handle)
    }

    fun setChecked(checked: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCheckedBind, handle, checked)
    }

    fun isChecked(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCheckedBind, handle)
    }

    fun setDrawWarning(drawWarning: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawWarningBind, handle, drawWarning)
    }

    fun isDrawWarning(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawWarningBind, handle)
    }

    fun setKeying(keying: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setKeyingBind, handle, keying)
    }

    fun isKeying(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isKeyingBind, handle)
    }

    fun setDeletable(deletable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDeletableBind, handle, deletable)
    }

    fun isDeletable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDeletableBind, handle)
    }

    fun getEditedProperty(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getEditedPropertyBind, handle)
    }

    fun getEditedObject(): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallNoArgsRetObject(getEditedObjectBind, handle))
    }

    fun updateProperty() {
        ObjectCalls.ptrcallNoArgs(updatePropertyBind, handle)
    }

    fun addFocusable(control: Control) {
        ObjectCalls.ptrcallWithObjectArgs(addFocusableBind, handle, listOf(control.handle))
    }

    fun setBottomEditor(editor: Control) {
        ObjectCalls.ptrcallWithObjectArgs(setBottomEditorBind, handle, listOf(editor.handle))
    }

    fun setSelectable(selectable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSelectableBind, handle, selectable)
    }

    fun isSelectable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSelectableBind, handle)
    }

    fun setUseFolding(useFolding: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseFoldingBind, handle, useFolding)
    }

    fun isUsingFolding(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingFoldingBind, handle)
    }

    fun setNameSplitRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setNameSplitRatioBind, handle, ratio)
    }

    fun getNameSplitRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getNameSplitRatioBind, handle)
    }

    /**
     * Draw property as not selected. Used by the inspector.
     *
     * Generated from Godot docs: EditorProperty.deselect
     */
    fun deselect() {
        ObjectCalls.ptrcallNoArgs(deselectBind, handle)
    }

    fun isSelected(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSelectedBind, handle)
    }

    /**
     * Draw property as selected. Used by the inspector.
     *
     * Generated from Godot docs: EditorProperty.select
     */
    fun select(focusable: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(selectBind, handle, focusable)
    }

    fun setObjectAndProperty(objectValue: GodotObject, property: String) {
        ObjectCalls.ptrcallWithObjectAndStringNameArg(setObjectAndPropertyBind, handle, objectValue.handle, property)
    }

    fun setLabelReference(control: Control) {
        ObjectCalls.ptrcallWithObjectArgs(setLabelReferenceBind, handle, listOf(control.handle))
    }

    fun emitChanged(property: String, value: Any?, field: String = "", changing: Boolean = false) {
        ObjectCalls.ptrcallWithStringNameVariantStringNameBoolArgs(emitChangedBind, handle, property, value, field, changing)
    }

    object Signals {
        const val propertyChanged: String = "property_changed"
        const val multiplePropertiesChanged: String = "multiple_properties_changed"
        const val propertyKeyed: String = "property_keyed"
        const val propertyDeleted: String = "property_deleted"
        const val propertyKeyedWithValue: String = "property_keyed_with_value"
        const val propertyChecked: String = "property_checked"
        const val propertyOverridden: String = "property_overridden"
        const val propertyFavorited: String = "property_favorited"
        const val propertyPinned: String = "property_pinned"
        const val propertyCanRevertChanged: String = "property_can_revert_changed"
        const val resourceSelected: String = "resource_selected"
        const val objectIdSelected: String = "object_id_selected"
        const val selected: String = "selected"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorProperty? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorProperty? =
            if (handle.address() == 0L) null else EditorProperty(handle)

        private const val SET_LABEL_HASH = 83702148L
        private val setLabelBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "set_label", SET_LABEL_HASH)
        }

        private const val GET_LABEL_HASH = 201670096L
        private val getLabelBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "get_label", GET_LABEL_HASH)
        }

        private const val SET_READ_ONLY_HASH = 2586408642L
        private val setReadOnlyBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "set_read_only", SET_READ_ONLY_HASH)
        }

        private const val IS_READ_ONLY_HASH = 36873697L
        private val isReadOnlyBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "is_read_only", IS_READ_ONLY_HASH)
        }

        private const val SET_DRAW_LABEL_HASH = 2586408642L
        private val setDrawLabelBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "set_draw_label", SET_DRAW_LABEL_HASH)
        }

        private const val IS_DRAW_LABEL_HASH = 36873697L
        private val isDrawLabelBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "is_draw_label", IS_DRAW_LABEL_HASH)
        }

        private const val SET_DRAW_BACKGROUND_HASH = 2586408642L
        private val setDrawBackgroundBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "set_draw_background", SET_DRAW_BACKGROUND_HASH)
        }

        private const val IS_DRAW_BACKGROUND_HASH = 36873697L
        private val isDrawBackgroundBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "is_draw_background", IS_DRAW_BACKGROUND_HASH)
        }

        private const val SET_CHECKABLE_HASH = 2586408642L
        private val setCheckableBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "set_checkable", SET_CHECKABLE_HASH)
        }

        private const val IS_CHECKABLE_HASH = 36873697L
        private val isCheckableBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "is_checkable", IS_CHECKABLE_HASH)
        }

        private const val SET_CHECKED_HASH = 2586408642L
        private val setCheckedBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "set_checked", SET_CHECKED_HASH)
        }

        private const val IS_CHECKED_HASH = 36873697L
        private val isCheckedBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "is_checked", IS_CHECKED_HASH)
        }

        private const val SET_DRAW_WARNING_HASH = 2586408642L
        private val setDrawWarningBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "set_draw_warning", SET_DRAW_WARNING_HASH)
        }

        private const val IS_DRAW_WARNING_HASH = 36873697L
        private val isDrawWarningBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "is_draw_warning", IS_DRAW_WARNING_HASH)
        }

        private const val SET_KEYING_HASH = 2586408642L
        private val setKeyingBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "set_keying", SET_KEYING_HASH)
        }

        private const val IS_KEYING_HASH = 36873697L
        private val isKeyingBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "is_keying", IS_KEYING_HASH)
        }

        private const val SET_DELETABLE_HASH = 2586408642L
        private val setDeletableBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "set_deletable", SET_DELETABLE_HASH)
        }

        private const val IS_DELETABLE_HASH = 36873697L
        private val isDeletableBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "is_deletable", IS_DELETABLE_HASH)
        }

        private const val GET_EDITED_PROPERTY_HASH = 2002593661L
        private val getEditedPropertyBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "get_edited_property", GET_EDITED_PROPERTY_HASH)
        }

        private const val GET_EDITED_OBJECT_HASH = 2050059866L
        private val getEditedObjectBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "get_edited_object", GET_EDITED_OBJECT_HASH)
        }

        private const val UPDATE_PROPERTY_HASH = 3218959716L
        private val updatePropertyBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "update_property", UPDATE_PROPERTY_HASH)
        }

        private const val ADD_FOCUSABLE_HASH = 1496901182L
        private val addFocusableBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "add_focusable", ADD_FOCUSABLE_HASH)
        }

        private const val SET_BOTTOM_EDITOR_HASH = 1496901182L
        private val setBottomEditorBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "set_bottom_editor", SET_BOTTOM_EDITOR_HASH)
        }

        private const val SET_SELECTABLE_HASH = 2586408642L
        private val setSelectableBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "set_selectable", SET_SELECTABLE_HASH)
        }

        private const val IS_SELECTABLE_HASH = 36873697L
        private val isSelectableBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "is_selectable", IS_SELECTABLE_HASH)
        }

        private const val SET_USE_FOLDING_HASH = 2586408642L
        private val setUseFoldingBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "set_use_folding", SET_USE_FOLDING_HASH)
        }

        private const val IS_USING_FOLDING_HASH = 36873697L
        private val isUsingFoldingBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "is_using_folding", IS_USING_FOLDING_HASH)
        }

        private const val SET_NAME_SPLIT_RATIO_HASH = 373806689L
        private val setNameSplitRatioBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "set_name_split_ratio", SET_NAME_SPLIT_RATIO_HASH)
        }

        private const val GET_NAME_SPLIT_RATIO_HASH = 1740695150L
        private val getNameSplitRatioBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "get_name_split_ratio", GET_NAME_SPLIT_RATIO_HASH)
        }

        private const val DESELECT_HASH = 3218959716L
        private val deselectBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "deselect", DESELECT_HASH)
        }

        private const val IS_SELECTED_HASH = 36873697L
        private val isSelectedBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "is_selected", IS_SELECTED_HASH)
        }

        private const val SELECT_HASH = 1025054187L
        private val selectBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "select", SELECT_HASH)
        }

        private const val SET_OBJECT_AND_PROPERTY_HASH = 4157606280L
        private val setObjectAndPropertyBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "set_object_and_property", SET_OBJECT_AND_PROPERTY_HASH)
        }

        private const val SET_LABEL_REFERENCE_HASH = 1496901182L
        private val setLabelReferenceBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "set_label_reference", SET_LABEL_REFERENCE_HASH)
        }

        private const val EMIT_CHANGED_HASH = 1822500399L
        private val emitChangedBind by lazy {
            ObjectCalls.getMethodBind("EditorProperty", "emit_changed", EMIT_CHANGED_HASH)
        }
    }
}
