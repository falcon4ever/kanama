package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Represents the input shader parameter within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeInput
 */
class VisualShaderNodeInput(handle: GodotHandle) : VisualShaderNode(handle) {
    var inputName: String
        @JvmName("inputNameProperty")
        get() = getInputName()
        @JvmName("setInputNameProperty")
        set(value) = setInputName(value)

    /**
     * One of the several input constants in lower-case style like: "vertex" (`VERTEX`) or "point_size"
     * (`POINT_SIZE`).
     *
     * Generated from Godot docs: VisualShaderNodeInput.set_input_name
     */
    fun setInputName(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setInputNameBind, segment, name)
        ObjectCalls.keepReachable(this)
    }

    /**
     * One of the several input constants in lower-case style like: "vertex" (`VERTEX`) or "point_size"
     * (`POINT_SIZE`).
     *
     * Generated from Godot docs: VisualShaderNodeInput.get_input_name
     */
    fun getInputName(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getInputNameBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns a translated name of the current constant in the Godot Shader Language. E.g. `"ALBEDO"`
     * if the `input_name` equal to `"albedo"`.
     *
     * Generated from Godot docs: VisualShaderNodeInput.get_input_real_name
     */
    fun getInputRealName(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getInputRealNameBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /** Signal `input_type_changed()`; see [TypedSignal]. */
    val inputTypeChanged: Signal0
        @JvmName("inputTypeChangedTypedSignal")
        get() = Signal0(this, "input_type_changed")

    object Signals {
        const val inputTypeChanged: String = "input_type_changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeInput? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeInput? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeInput(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeInput? =
            if (handle.address() == 0L) null else VisualShaderNodeInput(GodotHandle(handle))

        /** A new VisualShaderNodeInput, GDScript's `VisualShaderNodeInput.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeInput =
            RefCounted.owned(VisualShaderNodeInput(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeInput"), "VisualShaderNodeInput"))))
    }

    private object Binds {
        private const val SET_INPUT_NAME_HASH = 83702148L
        @JvmField
        val setInputNameBind =
            ObjectCalls.getMethodBind("VisualShaderNodeInput", "set_input_name", SET_INPUT_NAME_HASH)

        private const val GET_INPUT_NAME_HASH = 201670096L
        @JvmField
        val getInputNameBind =
            ObjectCalls.getMethodBind("VisualShaderNodeInput", "get_input_name", GET_INPUT_NAME_HASH)

        private const val GET_INPUT_REAL_NAME_HASH = 201670096L
        @JvmField
        val getInputRealNameBind =
            ObjectCalls.getMethodBind("VisualShaderNodeInput", "get_input_real_name", GET_INPUT_REAL_NAME_HASH)
    }
}
