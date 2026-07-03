package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A class stored as a resource.
 *
 * Generated from Godot docs: Script
 */
open class Script(handle: MemorySegment) : Resource(handle) {
    var sourceCode: String
        @JvmName("sourceCodeProperty")
        get() = getSourceCode()
        @JvmName("setSourceCodeProperty")
        set(value) = setSourceCode(value)

    /**
     * Returns `true` if the script can be instantiated.
     *
     * Generated from Godot docs: Script.can_instantiate
     */
    fun canInstantiate(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(canInstantiateBind, handle)
    }

    /**
     * Returns `true` if the script contains non-empty source code. Note: If a script does not have
     * source code, this does not mean that it is invalid or unusable. For example, a `GDScript` that
     * was exported with binary tokenization has no source code, but still behaves as expected and
     * could be instantiated. This can be checked with `can_instantiate`.
     *
     * Generated from Godot docs: Script.has_source_code
     */
    fun hasSourceCode(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasSourceCodeBind, handle)
    }

    /**
     * The script source code or an empty string if source code is not available. When set, does not
     * reload the class implementation automatically.
     *
     * Generated from Godot docs: Script.get_source_code
     */
    fun getSourceCode(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSourceCodeBind, handle)
    }

    /**
     * The script source code or an empty string if source code is not available. When set, does not
     * reload the class implementation automatically.
     *
     * Generated from Godot docs: Script.set_source_code
     */
    fun setSourceCode(source: String) {
        ObjectCalls.ptrcallWithStringArg(setSourceCodeBind, handle, source)
    }

    /**
     * Reloads the script's class implementation. Returns an error code.
     *
     * Generated from Godot docs: Script.reload
     */
    fun reload(keepState: Boolean = false): Long {
        return ObjectCalls.ptrcallWithBoolArgRetLong(reloadBind, handle, keepState)
    }

    /**
     * Returns the script directly inherited by this script.
     *
     * Generated from Godot docs: Script.get_base_script
     */
    fun getBaseScript(): Script? {
        return Script.wrap(ObjectCalls.ptrcallNoArgsRetObject(getBaseScriptBind, handle))
    }

    /**
     * Returns the script's base type.
     *
     * Generated from Godot docs: Script.get_instance_base_type
     */
    fun getInstanceBaseType(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getInstanceBaseTypeBind, handle)
    }

    /**
     * Returns the class name associated with the script, if there is one. Returns an empty string
     * otherwise. To give the script a global name, you can use the `class_name` keyword in GDScript
     * and the ``GlobalClass`` attribute in C#.
     *
     * Generated from Godot docs: Script.get_global_name
     */
    fun getGlobalName(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getGlobalNameBind, handle)
    }

    /**
     * Returns `true` if the script, or a base class, defines a method with the given name.
     *
     * Generated from Godot docs: Script.has_script_method
     */
    fun hasScriptMethod(methodName: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasScriptMethodBind, handle, methodName)
    }

    /**
     * Returns `true` if the script, or a base class, defines a signal with the given name.
     *
     * Generated from Godot docs: Script.has_script_signal
     */
    fun hasScriptSignal(signalName: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasScriptSignalBind, handle, signalName)
    }

    /**
     * Returns the list of properties in this `Script`. Note: The dictionaries returned by this method
     * are formatted identically to those returned by `Object.get_property_list`.
     *
     * Generated from Godot docs: Script.get_script_property_list
     */
    fun getScriptPropertyList(): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallNoArgsRetDictionaryList(getScriptPropertyListBind, handle)
    }

    /**
     * Returns the list of methods in this `Script`. Note: The dictionaries returned by this method are
     * formatted identically to those returned by `Object.get_method_list`.
     *
     * Generated from Godot docs: Script.get_script_method_list
     */
    fun getScriptMethodList(): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallNoArgsRetDictionaryList(getScriptMethodListBind, handle)
    }

    /**
     * Returns the list of signals defined in this `Script`. Note: The dictionaries returned by this
     * method are formatted identically to those returned by `Object.get_signal_list`.
     *
     * Generated from Godot docs: Script.get_script_signal_list
     */
    fun getScriptSignalList(): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallNoArgsRetDictionaryList(getScriptSignalListBind, handle)
    }

    /**
     * Returns a dictionary containing constant names and their values.
     *
     * Generated from Godot docs: Script.get_script_constant_map
     */
    fun getScriptConstantMap(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getScriptConstantMapBind, handle)
    }

    /**
     * Returns the default value of the specified property.
     *
     * Generated from Godot docs: Script.get_property_default_value
     */
    fun getPropertyDefaultValue(property: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(getPropertyDefaultValueBind, handle, property)
    }

    /**
     * Returns `true` if the script is a tool script. A tool script can run in the editor.
     *
     * Generated from Godot docs: Script.is_tool
     */
    fun isTool(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isToolBind, handle)
    }

    /**
     * Returns `true` if the script is an abstract script. An abstract script does not have a
     * constructor and cannot be instantiated.
     *
     * Generated from Godot docs: Script.is_abstract
     */
    fun isAbstract(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAbstractBind, handle)
    }

    /**
     * Returns a `Dictionary` mapping method names to their RPC configuration defined by this script.
     *
     * Generated from Godot docs: Script.get_rpc_config
     */
    fun getRpcConfig(): Any? {
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(getRpcConfigBind, handle)
    }

    /**
     * Returns `true` if `base_object` is an instance of this script.
     *
     * Generated from Godot docs: Script.instance_has
     */
    fun instanceHas(baseObject: GodotObject): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(instanceHasBind, handle, baseObject.handle)
    }

    companion object {
        @JvmStatic
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

        private const val GET_SCRIPT_PROPERTY_LIST_HASH = 2915620761L
        private val getScriptPropertyListBind by lazy {
            ObjectCalls.getMethodBind("Script", "get_script_property_list", GET_SCRIPT_PROPERTY_LIST_HASH)
        }

        private const val GET_SCRIPT_METHOD_LIST_HASH = 2915620761L
        private val getScriptMethodListBind by lazy {
            ObjectCalls.getMethodBind("Script", "get_script_method_list", GET_SCRIPT_METHOD_LIST_HASH)
        }

        private const val GET_SCRIPT_SIGNAL_LIST_HASH = 2915620761L
        private val getScriptSignalListBind by lazy {
            ObjectCalls.getMethodBind("Script", "get_script_signal_list", GET_SCRIPT_SIGNAL_LIST_HASH)
        }

        private const val GET_SCRIPT_CONSTANT_MAP_HASH = 2382534195L
        private val getScriptConstantMapBind by lazy {
            ObjectCalls.getMethodBind("Script", "get_script_constant_map", GET_SCRIPT_CONSTANT_MAP_HASH)
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
