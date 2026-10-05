package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Godot editor's popup dialog for creating new `Script` files.
 *
 * Generated from Godot docs: ScriptCreateDialog
 */
class ScriptCreateDialog(handle: GodotHandle) : ConfirmationDialog(handle) {
    /**
     * Prefills required fields to configure the ScriptCreateDialog for use.
     *
     * Generated from Godot docs: ScriptCreateDialog.config
     */
    fun config(inherits: String, path: String, builtInEnabled: Boolean = true, loadEnabled: Boolean = true) {
        ObjectCalls.ptrcallWithTwoStringAndTwoBoolArgs(configBind, segment, inherits, path, builtInEnabled, loadEnabled)
    }

    /** Signal `script_created(script: Script)`; see [TypedSignal]. */
    val scriptCreated: Signal1<Script?>
        @JvmName("scriptCreatedTypedSignal")
        get() = Signal1(this, "script_created", SignalArgType.nullableObjectOf("Script") { Script(it) })

    object Signals {
        const val scriptCreated: String = "script_created"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ScriptCreateDialog? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ScriptCreateDialog? =
            if (handle.address() == 0L) null else ScriptCreateDialog(GodotHandle(handle))

        private const val CONFIG_HASH = 869314288L
        private val configBind by lazy {
            ObjectCalls.getMethodBind("ScriptCreateDialog", "config", CONFIG_HASH)
        }
    }
}
