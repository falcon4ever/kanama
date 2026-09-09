package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: Script
 */
open class Script(handle: MemorySegment) : Resource(handle) {
    var sourceCode: String
        @JvmName("sourceCodeProperty")
        get() = getSourceCode()
        @JvmName("setSourceCodeProperty")
        set(value) = setSourceCode(value)

    fun canInstantiate(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(canInstantiateBind, handle)
    }

    fun hasSourceCode(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(hasSourceCodeBind, handle)
    }

    fun getSourceCode(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getSourceCodeBind, handle)
    }

    fun setSourceCode(source: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setSourceCodeBind, handle, source)
    }

    fun reload(keepState: Boolean = false): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithBoolArgRetLong(reloadBind, handle, keepState)
    }

    fun getBaseScript(): Script? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(getBaseScriptBind, handle)
        if (ret.address() == handle.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Script.wrap(ret)
    }

    fun getInstanceBaseType(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetStringName(getInstanceBaseTypeBind, handle)
    }

    fun getGlobalName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetStringName(getGlobalNameBind, handle)
    }

    fun hasScriptMethod(methodName: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasScriptMethodBind, handle, methodName)
    }

    fun hasScriptSignal(signalName: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasScriptSignalBind, handle, signalName)
    }

    fun getPropertyDefaultValue(property: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getPropertyDefaultValueBind, handle, property)
    }

    fun isTool(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isToolBind, handle)
    }

    fun isAbstract(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isAbstractBind, handle)
    }

    fun getRpcConfig(): Any? {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(getRpcConfigBind, handle)
    }

    fun instanceHas(baseObject: GodotObject): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectArgRetBool(instanceHasBind, handle, baseObject.handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): Script? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Script? =
            if (handle.address() == 0L) null else Script(handle)

        private const val CAN_INSTANTIATE_HASH = 36873697L
        private val canInstantiateBind by lazy {
            ObjectCalls.getMethodBind("Script", "can_instantiate", CAN_INSTANTIATE_HASH)
        }

        private const val HAS_SOURCE_CODE_HASH = 36873697L
        private val hasSourceCodeBind by lazy {
            ObjectCalls.getMethodBind("Script", "has_source_code", HAS_SOURCE_CODE_HASH)
        }

        private const val GET_SOURCE_CODE_HASH = 201670096L
        private val getSourceCodeBind by lazy {
            ObjectCalls.getMethodBind("Script", "get_source_code", GET_SOURCE_CODE_HASH)
        }

        private const val SET_SOURCE_CODE_HASH = 83702148L
        private val setSourceCodeBind by lazy {
            ObjectCalls.getMethodBind("Script", "set_source_code", SET_SOURCE_CODE_HASH)
        }

        private const val RELOAD_HASH = 1633102583L
        private val reloadBind by lazy {
            ObjectCalls.getMethodBind("Script", "reload", RELOAD_HASH)
        }

        private const val GET_BASE_SCRIPT_HASH = 278624046L
        private val getBaseScriptBind by lazy {
            ObjectCalls.getMethodBind("Script", "get_base_script", GET_BASE_SCRIPT_HASH)
        }

        private const val GET_INSTANCE_BASE_TYPE_HASH = 2002593661L
        private val getInstanceBaseTypeBind by lazy {
            ObjectCalls.getMethodBind("Script", "get_instance_base_type", GET_INSTANCE_BASE_TYPE_HASH)
        }

        private const val GET_GLOBAL_NAME_HASH = 2002593661L
        private val getGlobalNameBind by lazy {
            ObjectCalls.getMethodBind("Script", "get_global_name", GET_GLOBAL_NAME_HASH)
        }

        private const val HAS_SCRIPT_METHOD_HASH = 2619796661L
        private val hasScriptMethodBind by lazy {
            ObjectCalls.getMethodBind("Script", "has_script_method", HAS_SCRIPT_METHOD_HASH)
        }

        private const val HAS_SCRIPT_SIGNAL_HASH = 2619796661L
        private val hasScriptSignalBind by lazy {
            ObjectCalls.getMethodBind("Script", "has_script_signal", HAS_SCRIPT_SIGNAL_HASH)
        }

        private const val GET_PROPERTY_DEFAULT_VALUE_HASH = 2138907829L
        private val getPropertyDefaultValueBind by lazy {
            ObjectCalls.getMethodBind("Script", "get_property_default_value", GET_PROPERTY_DEFAULT_VALUE_HASH)
        }

        private const val IS_TOOL_HASH = 36873697L
        private val isToolBind by lazy {
            ObjectCalls.getMethodBind("Script", "is_tool", IS_TOOL_HASH)
        }

        private const val IS_ABSTRACT_HASH = 36873697L
        private val isAbstractBind by lazy {
            ObjectCalls.getMethodBind("Script", "is_abstract", IS_ABSTRACT_HASH)
        }

        private const val GET_RPC_CONFIG_HASH = 1214101251L
        private val getRpcConfigBind by lazy {
            ObjectCalls.getMethodBind("Script", "get_rpc_config", GET_RPC_CONFIG_HASH)
        }

        private const val INSTANCE_HAS_HASH = 397768994L
        private val instanceHasBind by lazy {
            ObjectCalls.getMethodBind("Script", "instance_has", INSTANCE_HAS_HASH)
        }
    }
}
