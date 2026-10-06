package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Custom control for editing properties that can be added to the `EditorInspector`.
 *
 * Generated from Godot docs: EditorProperty
 */
class EditorProperty(handle: GodotHandle) : Container(handle) {
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

    /**
     * Set this property to change the label (if you want to show one).
     *
     * Generated from Godot docs: EditorProperty.set_label
     */
    fun setLabel(text: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setLabelBind, segment, text)
    }

    /**
     * Set this property to change the label (if you want to show one).
     *
     * Generated from Godot docs: EditorProperty.get_label
     */
    fun getLabel(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getLabelBind, segment)
    }

    /**
     * Used by the inspector, set to `true` when the property is read-only.
     *
     * Generated from Godot docs: EditorProperty.set_read_only
     */
    fun setReadOnly(readOnly: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setReadOnlyBind, segment, readOnly)
    }

    /**
     * Used by the inspector, set to `true` when the property is read-only.
     *
     * Generated from Godot docs: EditorProperty.is_read_only
     */
    fun isReadOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isReadOnlyBind, segment)
    }

    /**
     * Used by the inspector, set to `true` when the property label is drawn.
     *
     * Generated from Godot docs: EditorProperty.set_draw_label
     */
    fun setDrawLabel(drawLabel: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDrawLabelBind, segment, drawLabel)
    }

    /**
     * Used by the inspector, set to `true` when the property label is drawn.
     *
     * Generated from Godot docs: EditorProperty.is_draw_label
     */
    fun isDrawLabel(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDrawLabelBind, segment)
    }

    /**
     * Used by the inspector, set to `true` when the property background is drawn.
     *
     * Generated from Godot docs: EditorProperty.set_draw_background
     */
    fun setDrawBackground(drawBackground: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDrawBackgroundBind, segment, drawBackground)
    }

    /**
     * Used by the inspector, set to `true` when the property background is drawn.
     *
     * Generated from Godot docs: EditorProperty.is_draw_background
     */
    fun isDrawBackground(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDrawBackgroundBind, segment)
    }

    /**
     * Used by the inspector, set to `true` when the property is checkable.
     *
     * Generated from Godot docs: EditorProperty.set_checkable
     */
    fun setCheckable(checkable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCheckableBind, segment, checkable)
    }

    /**
     * Used by the inspector, set to `true` when the property is checkable.
     *
     * Generated from Godot docs: EditorProperty.is_checkable
     */
    fun isCheckable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCheckableBind, segment)
    }

    /**
     * Used by the inspector, set to `true` when the property is checked.
     *
     * Generated from Godot docs: EditorProperty.set_checked
     */
    fun setChecked(checked: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCheckedBind, segment, checked)
    }

    /**
     * Used by the inspector, set to `true` when the property is checked.
     *
     * Generated from Godot docs: EditorProperty.is_checked
     */
    fun isChecked(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCheckedBind, segment)
    }

    /**
     * Used by the inspector, set to `true` when the property is drawn with the editor theme's warning
     * color. This is used for editable children's properties.
     *
     * Generated from Godot docs: EditorProperty.set_draw_warning
     */
    fun setDrawWarning(drawWarning: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDrawWarningBind, segment, drawWarning)
    }

    /**
     * Used by the inspector, set to `true` when the property is drawn with the editor theme's warning
     * color. This is used for editable children's properties.
     *
     * Generated from Godot docs: EditorProperty.is_draw_warning
     */
    fun isDrawWarning(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDrawWarningBind, segment)
    }

    /**
     * Used by the inspector, set to `true` when the property can add keys for animation.
     *
     * Generated from Godot docs: EditorProperty.set_keying
     */
    fun setKeying(keying: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setKeyingBind, segment, keying)
    }

    /**
     * Used by the inspector, set to `true` when the property can add keys for animation.
     *
     * Generated from Godot docs: EditorProperty.is_keying
     */
    fun isKeying(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isKeyingBind, segment)
    }

    /**
     * Used by the inspector, set to `true` when the property can be deleted by the user.
     *
     * Generated from Godot docs: EditorProperty.set_deletable
     */
    fun setDeletable(deletable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDeletableBind, segment, deletable)
    }

    /**
     * Used by the inspector, set to `true` when the property can be deleted by the user.
     *
     * Generated from Godot docs: EditorProperty.is_deletable
     */
    fun isDeletable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDeletableBind, segment)
    }

    /**
     * Returns the edited property. If your editor is for a single property (added via
     * `EditorInspectorPlugin._parse_property`), then this will return the property. Note: This method
     * could return `null` if the editor has not yet been associated with a property. However, in
     * `_update_property` and `_set_read_only`, this value is guaranteed to be non-`null`.
     *
     * Generated from Godot docs: EditorProperty.get_edited_property
     */
    fun getEditedProperty(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(Binds.getEditedPropertyBind, segment)
    }

    /**
     * Returns the edited object. Note: This method could return `null` if the editor has not yet been
     * associated with a property. However, in `_update_property` and `_set_read_only`, this value is
     * guaranteed to be non-`null`.
     *
     * Generated from Godot docs: EditorProperty.get_edited_object
     */
    fun getEditedObject(): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getEditedObjectBind, segment))
    }

    /**
     * Forces a refresh of the property display.
     *
     * Generated from Godot docs: EditorProperty.update_property
     */
    fun updateProperty() {
        ObjectCalls.ptrcallNoArgs(Binds.updatePropertyBind, segment)
    }

    /**
     * If any of the controls added can gain keyboard focus, add it here. This ensures that focus will
     * be restored if the inspector is refreshed.
     *
     * Generated from Godot docs: EditorProperty.add_focusable
     */
    fun addFocusable(control: Control) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.addFocusableBind, segment, listOf(control.segment))
    }

    /**
     * Puts the `editor` control below the property label. The control must be previously added using
     * `Node.add_child`.
     *
     * Generated from Godot docs: EditorProperty.set_bottom_editor
     */
    fun setBottomEditor(editor: Control) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setBottomEditorBind, segment, listOf(editor.segment))
    }

    /**
     * Used by the inspector, set to `true` when the property is selectable.
     *
     * Generated from Godot docs: EditorProperty.set_selectable
     */
    fun setSelectable(selectable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setSelectableBind, segment, selectable)
    }

    /**
     * Used by the inspector, set to `true` when the property is selectable.
     *
     * Generated from Godot docs: EditorProperty.is_selectable
     */
    fun isSelectable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSelectableBind, segment)
    }

    /**
     * Used by the inspector, set to `true` when the property is using folding.
     *
     * Generated from Godot docs: EditorProperty.set_use_folding
     */
    fun setUseFolding(useFolding: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseFoldingBind, segment, useFolding)
    }

    /**
     * Used by the inspector, set to `true` when the property is using folding.
     *
     * Generated from Godot docs: EditorProperty.is_using_folding
     */
    fun isUsingFolding(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUsingFoldingBind, segment)
    }

    /**
     * Space distribution ratio between the label and the editing field.
     *
     * Generated from Godot docs: EditorProperty.set_name_split_ratio
     */
    fun setNameSplitRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setNameSplitRatioBind, segment, ratio)
    }

    /**
     * Space distribution ratio between the label and the editing field.
     *
     * Generated from Godot docs: EditorProperty.get_name_split_ratio
     */
    fun getNameSplitRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getNameSplitRatioBind, segment)
    }

    /**
     * Draw property as not selected. Used by the inspector.
     *
     * Generated from Godot docs: EditorProperty.deselect
     */
    fun deselect() {
        ObjectCalls.ptrcallNoArgs(Binds.deselectBind, segment)
    }

    /**
     * Returns `true` if property is drawn as selected. Used by the inspector.
     *
     * Generated from Godot docs: EditorProperty.is_selected
     */
    fun isSelected(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSelectedBind, segment)
    }

    /**
     * Draw property as selected. Used by the inspector.
     *
     * Generated from Godot docs: EditorProperty.select
     */
    fun select(focusable: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(Binds.selectBind, segment, focusable)
    }

    /**
     * Assigns object and property to edit.
     *
     * Generated from Godot docs: EditorProperty.set_object_and_property
     */
    fun setObjectAndProperty(objectValue: GodotObject, property: String) {
        ObjectCalls.ptrcallWithObjectAndStringNameArg(Binds.setObjectAndPropertyBind, segment, objectValue.segment, property)
    }

    /**
     * Used by the inspector, set to a control that will be used as a reference to calculate the size
     * of the label.
     *
     * Generated from Godot docs: EditorProperty.set_label_reference
     */
    fun setLabelReference(control: Control) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setLabelReferenceBind, segment, listOf(control.segment))
    }

    /**
     * If one or several properties have changed, this must be called. `field` is used in case your
     * editor can modify fields separately (as an example, Vector3.x). The `changing` argument avoids
     * the editor requesting this property to be refreshed (leave as `false` if unsure).
     *
     * Generated from Godot docs: EditorProperty.emit_changed
     */
    fun emitChanged(property: String, value: Any?, field: String = "", changing: Boolean = false) {
        ObjectCalls.ptrcallWithStringNameVariantStringNameBoolArgs(Binds.emitChangedBind, segment, property, value, field, changing)
    }

    /** Signal `property_changed(property: StringName, value: Variant, field: StringName, changing: bool)`; see [TypedSignal]. */
    val propertyChanged: Signal4<String, Any?, String, Boolean>
        @JvmName("propertyChangedTypedSignal")
        get() = Signal4(this, "property_changed", SignalArgType.STRING, SignalArgType.VARIANT, SignalArgType.STRING, SignalArgType.BOOLEAN)

    /** Signal `multiple_properties_changed(properties: PackedStringArray, value: Array)`; see [TypedSignal]. On iOS a Array/PackedStringArray argument is not delivered yet: a connection reports a script error. */
    val multiplePropertiesChanged: Signal2<List<String>, List<Any?>>
        @JvmName("multiplePropertiesChangedTypedSignal")
        get() = Signal2(this, "multiple_properties_changed", SignalArgType.valueOf<List<String>>("PackedStringArray", List::class), SignalArgType.valueOf<List<Any?>>("Array", List::class))

    /** Signal `property_keyed(property: StringName)`; see [TypedSignal]. */
    val propertyKeyed: Signal1<String>
        @JvmName("propertyKeyedTypedSignal")
        get() = Signal1(this, "property_keyed", SignalArgType.STRING)

    /** Signal `property_deleted(property: StringName)`; see [TypedSignal]. */
    val propertyDeleted: Signal1<String>
        @JvmName("propertyDeletedTypedSignal")
        get() = Signal1(this, "property_deleted", SignalArgType.STRING)

    /** Signal `property_keyed_with_value(property: StringName, value: Variant)`; see [TypedSignal]. */
    val propertyKeyedWithValue: Signal2<String, Any?>
        @JvmName("propertyKeyedWithValueTypedSignal")
        get() = Signal2(this, "property_keyed_with_value", SignalArgType.STRING, SignalArgType.VARIANT)

    /** Signal `property_checked(property: StringName, checked: bool)`; see [TypedSignal]. */
    val propertyChecked: Signal2<String, Boolean>
        @JvmName("propertyCheckedTypedSignal")
        get() = Signal2(this, "property_checked", SignalArgType.STRING, SignalArgType.BOOLEAN)

    /** Signal `property_overridden()`; see [TypedSignal]. */
    val propertyOverridden: Signal0
        @JvmName("propertyOverriddenTypedSignal")
        get() = Signal0(this, "property_overridden")

    /** Signal `property_favorited(property: StringName, favorited: bool)`; see [TypedSignal]. */
    val propertyFavorited: Signal2<String, Boolean>
        @JvmName("propertyFavoritedTypedSignal")
        get() = Signal2(this, "property_favorited", SignalArgType.STRING, SignalArgType.BOOLEAN)

    /** Signal `property_pinned(property: StringName, pinned: bool)`; see [TypedSignal]. */
    val propertyPinned: Signal2<String, Boolean>
        @JvmName("propertyPinnedTypedSignal")
        get() = Signal2(this, "property_pinned", SignalArgType.STRING, SignalArgType.BOOLEAN)

    /** Signal `property_can_revert_changed(property: StringName, can_revert: bool)`; see [TypedSignal]. */
    val propertyCanRevertChanged: Signal2<String, Boolean>
        @JvmName("propertyCanRevertChangedTypedSignal")
        get() = Signal2(this, "property_can_revert_changed", SignalArgType.STRING, SignalArgType.BOOLEAN)

    /** Signal `resource_selected(path: String, resource: Resource)`; see [TypedSignal]. */
    val resourceSelected: Signal2<String, Resource?>
        @JvmName("resourceSelectedTypedSignal")
        get() = Signal2(this, "resource_selected", SignalArgType.STRING, SignalArgType.nullableObjectOf("Resource") { Resource(it) })

    /** Signal `object_id_selected(property: StringName, id: int)`; see [TypedSignal]. */
    val objectIdSelected: Signal2<String, Long>
        @JvmName("objectIdSelectedTypedSignal")
        get() = Signal2(this, "object_id_selected", SignalArgType.STRING, SignalArgType.LONG)

    /** Signal `selected(path: String, focusable_idx: int)`; see [TypedSignal]. */
    val selected: Signal2<String, Long>
        @JvmName("selectedTypedSignal")
        get() = Signal2(this, "selected", SignalArgType.STRING, SignalArgType.LONG)

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
        fun fromHandle(handle: GodotHandle): EditorProperty? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): EditorProperty? =
            if (handle.address() == 0L) null else EditorProperty(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_LABEL_HASH = 83702148L
        @JvmField
        val setLabelBind =
            ObjectCalls.getMethodBind("EditorProperty", "set_label", SET_LABEL_HASH)

        private const val GET_LABEL_HASH = 201670096L
        @JvmField
        val getLabelBind =
            ObjectCalls.getMethodBind("EditorProperty", "get_label", GET_LABEL_HASH)

        private const val SET_READ_ONLY_HASH = 2586408642L
        @JvmField
        val setReadOnlyBind =
            ObjectCalls.getMethodBind("EditorProperty", "set_read_only", SET_READ_ONLY_HASH)

        private const val IS_READ_ONLY_HASH = 36873697L
        @JvmField
        val isReadOnlyBind =
            ObjectCalls.getMethodBind("EditorProperty", "is_read_only", IS_READ_ONLY_HASH)

        private const val SET_DRAW_LABEL_HASH = 2586408642L
        @JvmField
        val setDrawLabelBind =
            ObjectCalls.getMethodBind("EditorProperty", "set_draw_label", SET_DRAW_LABEL_HASH)

        private const val IS_DRAW_LABEL_HASH = 36873697L
        @JvmField
        val isDrawLabelBind =
            ObjectCalls.getMethodBind("EditorProperty", "is_draw_label", IS_DRAW_LABEL_HASH)

        private const val SET_DRAW_BACKGROUND_HASH = 2586408642L
        @JvmField
        val setDrawBackgroundBind =
            ObjectCalls.getMethodBind("EditorProperty", "set_draw_background", SET_DRAW_BACKGROUND_HASH)

        private const val IS_DRAW_BACKGROUND_HASH = 36873697L
        @JvmField
        val isDrawBackgroundBind =
            ObjectCalls.getMethodBind("EditorProperty", "is_draw_background", IS_DRAW_BACKGROUND_HASH)

        private const val SET_CHECKABLE_HASH = 2586408642L
        @JvmField
        val setCheckableBind =
            ObjectCalls.getMethodBind("EditorProperty", "set_checkable", SET_CHECKABLE_HASH)

        private const val IS_CHECKABLE_HASH = 36873697L
        @JvmField
        val isCheckableBind =
            ObjectCalls.getMethodBind("EditorProperty", "is_checkable", IS_CHECKABLE_HASH)

        private const val SET_CHECKED_HASH = 2586408642L
        @JvmField
        val setCheckedBind =
            ObjectCalls.getMethodBind("EditorProperty", "set_checked", SET_CHECKED_HASH)

        private const val IS_CHECKED_HASH = 36873697L
        @JvmField
        val isCheckedBind =
            ObjectCalls.getMethodBind("EditorProperty", "is_checked", IS_CHECKED_HASH)

        private const val SET_DRAW_WARNING_HASH = 2586408642L
        @JvmField
        val setDrawWarningBind =
            ObjectCalls.getMethodBind("EditorProperty", "set_draw_warning", SET_DRAW_WARNING_HASH)

        private const val IS_DRAW_WARNING_HASH = 36873697L
        @JvmField
        val isDrawWarningBind =
            ObjectCalls.getMethodBind("EditorProperty", "is_draw_warning", IS_DRAW_WARNING_HASH)

        private const val SET_KEYING_HASH = 2586408642L
        @JvmField
        val setKeyingBind =
            ObjectCalls.getMethodBind("EditorProperty", "set_keying", SET_KEYING_HASH)

        private const val IS_KEYING_HASH = 36873697L
        @JvmField
        val isKeyingBind =
            ObjectCalls.getMethodBind("EditorProperty", "is_keying", IS_KEYING_HASH)

        private const val SET_DELETABLE_HASH = 2586408642L
        @JvmField
        val setDeletableBind =
            ObjectCalls.getMethodBind("EditorProperty", "set_deletable", SET_DELETABLE_HASH)

        private const val IS_DELETABLE_HASH = 36873697L
        @JvmField
        val isDeletableBind =
            ObjectCalls.getMethodBind("EditorProperty", "is_deletable", IS_DELETABLE_HASH)

        private const val GET_EDITED_PROPERTY_HASH = 2002593661L
        @JvmField
        val getEditedPropertyBind =
            ObjectCalls.getMethodBind("EditorProperty", "get_edited_property", GET_EDITED_PROPERTY_HASH)

        private const val GET_EDITED_OBJECT_HASH = 2050059866L
        @JvmField
        val getEditedObjectBind =
            ObjectCalls.getMethodBind("EditorProperty", "get_edited_object", GET_EDITED_OBJECT_HASH)

        private const val UPDATE_PROPERTY_HASH = 3218959716L
        @JvmField
        val updatePropertyBind =
            ObjectCalls.getMethodBind("EditorProperty", "update_property", UPDATE_PROPERTY_HASH)

        private const val ADD_FOCUSABLE_HASH = 1496901182L
        @JvmField
        val addFocusableBind =
            ObjectCalls.getMethodBind("EditorProperty", "add_focusable", ADD_FOCUSABLE_HASH)

        private const val SET_BOTTOM_EDITOR_HASH = 1496901182L
        @JvmField
        val setBottomEditorBind =
            ObjectCalls.getMethodBind("EditorProperty", "set_bottom_editor", SET_BOTTOM_EDITOR_HASH)

        private const val SET_SELECTABLE_HASH = 2586408642L
        @JvmField
        val setSelectableBind =
            ObjectCalls.getMethodBind("EditorProperty", "set_selectable", SET_SELECTABLE_HASH)

        private const val IS_SELECTABLE_HASH = 36873697L
        @JvmField
        val isSelectableBind =
            ObjectCalls.getMethodBind("EditorProperty", "is_selectable", IS_SELECTABLE_HASH)

        private const val SET_USE_FOLDING_HASH = 2586408642L
        @JvmField
        val setUseFoldingBind =
            ObjectCalls.getMethodBind("EditorProperty", "set_use_folding", SET_USE_FOLDING_HASH)

        private const val IS_USING_FOLDING_HASH = 36873697L
        @JvmField
        val isUsingFoldingBind =
            ObjectCalls.getMethodBind("EditorProperty", "is_using_folding", IS_USING_FOLDING_HASH)

        private const val SET_NAME_SPLIT_RATIO_HASH = 373806689L
        @JvmField
        val setNameSplitRatioBind =
            ObjectCalls.getMethodBind("EditorProperty", "set_name_split_ratio", SET_NAME_SPLIT_RATIO_HASH)

        private const val GET_NAME_SPLIT_RATIO_HASH = 1740695150L
        @JvmField
        val getNameSplitRatioBind =
            ObjectCalls.getMethodBind("EditorProperty", "get_name_split_ratio", GET_NAME_SPLIT_RATIO_HASH)

        private const val DESELECT_HASH = 3218959716L
        @JvmField
        val deselectBind =
            ObjectCalls.getMethodBind("EditorProperty", "deselect", DESELECT_HASH)

        private const val IS_SELECTED_HASH = 36873697L
        @JvmField
        val isSelectedBind =
            ObjectCalls.getMethodBind("EditorProperty", "is_selected", IS_SELECTED_HASH)

        private const val SELECT_HASH = 1025054187L
        @JvmField
        val selectBind =
            ObjectCalls.getMethodBind("EditorProperty", "select", SELECT_HASH)

        private const val SET_OBJECT_AND_PROPERTY_HASH = 4157606280L
        @JvmField
        val setObjectAndPropertyBind =
            ObjectCalls.getMethodBind("EditorProperty", "set_object_and_property", SET_OBJECT_AND_PROPERTY_HASH)

        private const val SET_LABEL_REFERENCE_HASH = 1496901182L
        @JvmField
        val setLabelReferenceBind =
            ObjectCalls.getMethodBind("EditorProperty", "set_label_reference", SET_LABEL_REFERENCE_HASH)

        private const val EMIT_CHANGED_HASH = 1822500399L
        @JvmField
        val emitChangedBind =
            ObjectCalls.getMethodBind("EditorProperty", "emit_changed", EMIT_CHANGED_HASH)
    }
}
