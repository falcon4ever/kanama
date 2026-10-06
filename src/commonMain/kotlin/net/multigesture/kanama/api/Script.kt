package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A class stored as a resource.
 *
 * Generated from Godot docs: Script
 */
open class Script(handle: GodotHandle) : Resource(handle) {
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.canInstantiateBind, segment)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasSourceCodeBind, segment)
    }

    /**
     * The script source code or an empty string if source code is not available. When set, does not
     * reload the class implementation automatically.
     *
     * Generated from Godot docs: Script.get_source_code
     */
    fun getSourceCode(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getSourceCodeBind, segment)
    }

    /**
     * The script source code or an empty string if source code is not available. When set, does not
     * reload the class implementation automatically.
     *
     * Generated from Godot docs: Script.set_source_code
     */
    fun setSourceCode(source: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setSourceCodeBind, segment, source)
    }

    /**
     * Reloads the script's class implementation. Returns an error code.
     *
     * Generated from Godot docs: Script.reload
     */
    fun reload(keepState: Boolean = false): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithBoolArgRetLong(Binds.reloadBind, segment, keepState))
    }

    /**
     * Returns the script directly inherited by this script.
     *
     * Generated from Godot docs: Script.get_base_script
     */
    fun getBaseScript(): Script? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(Binds.getBaseScriptBind, segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Script.wrapOwned(ret)
    }

    /**
     * Returns the script's base type.
     *
     * Generated from Godot docs: Script.get_instance_base_type
     */
    fun getInstanceBaseType(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetStringName(Binds.getInstanceBaseTypeBind, segment)
    }

    /**
     * Returns the class name associated with the script, if there is one. Returns an empty string
     * otherwise. To give the script a global name, you can use the `class_name` keyword in GDScript
     * and the ``GlobalClass`` attribute in C#.
     *
     * Generated from Godot docs: Script.get_global_name
     */
    fun getGlobalName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetStringName(Binds.getGlobalNameBind, segment)
    }

    /**
     * Returns `true` if the script, or a base class, defines a method with the given name.
     *
     * Generated from Godot docs: Script.has_script_method
     */
    fun hasScriptMethod(methodName: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.hasScriptMethodBind, segment, methodName)
    }

    /**
     * Returns `true` if the script, or a base class, defines a signal with the given name.
     *
     * Generated from Godot docs: Script.has_script_signal
     */
    fun hasScriptSignal(signalName: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.hasScriptSignalBind, segment, signalName)
    }

    /**
     * Returns the list of properties in this `Script`. Note: The dictionaries returned by this method
     * are formatted identically to those returned by `Object.get_property_list`.
     *
     * Generated from Godot docs: Script.get_script_property_list
     */
    fun getScriptPropertyList(): List<Map<String, Any?>> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionaryList(Binds.getScriptPropertyListBind, segment)
    }

    /**
     * Returns the list of methods in this `Script`. Note: The dictionaries returned by this method are
     * formatted identically to those returned by `Object.get_method_list`.
     *
     * Generated from Godot docs: Script.get_script_method_list
     */
    fun getScriptMethodList(): List<Map<String, Any?>> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionaryList(Binds.getScriptMethodListBind, segment)
    }

    /**
     * Returns the list of signals defined in this `Script`. Note: The dictionaries returned by this
     * method are formatted identically to those returned by `Object.get_signal_list`.
     *
     * Generated from Godot docs: Script.get_script_signal_list
     */
    fun getScriptSignalList(): List<Map<String, Any?>> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionaryList(Binds.getScriptSignalListBind, segment)
    }

    /**
     * Returns a dictionary containing constant names and their values.
     *
     * Generated from Godot docs: Script.get_script_constant_map
     */
    fun getScriptConstantMap(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(Binds.getScriptConstantMapBind, segment)
    }

    /**
     * Returns the default value of the specified property.
     *
     * Generated from Godot docs: Script.get_property_default_value
     */
    fun getPropertyDefaultValue(property: String): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(Binds.getPropertyDefaultValueBind, segment, property)
    }

    /**
     * Returns `true` if the script is a tool script. A tool script can run in the editor.
     *
     * Generated from Godot docs: Script.is_tool
     */
    fun isTool(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isToolBind, segment)
    }

    /**
     * Returns `true` if the script is an abstract script. An abstract script does not have a
     * constructor and cannot be instantiated.
     *
     * Generated from Godot docs: Script.is_abstract
     */
    fun isAbstract(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAbstractBind, segment)
    }

    /**
     * Returns a `Dictionary` mapping method names to their RPC configuration defined by this script.
     *
     * Generated from Godot docs: Script.get_rpc_config
     */
    fun getRpcConfig(): Any? {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(Binds.getRpcConfigBind, segment)
    }

    /**
     * Returns `true` if `base_object` is an instance of this script.
     *
     * Generated from Godot docs: Script.instance_has
     */
    fun instanceHas(baseObject: GodotObject): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithObjectArgRetBool(Binds.instanceHasBind, segment, baseObject.segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Script? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Script? =
            if (handle.address() == 0L) null else RefCounted.owned(Script(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Script? =
            if (handle.address() == 0L) null else Script(GodotHandle(handle))
    }

    private object Binds {
        private const val CAN_INSTANTIATE_HASH = 36873697L
        @JvmField
        val canInstantiateBind =
            ObjectCalls.getMethodBind("Script", "can_instantiate", CAN_INSTANTIATE_HASH)

        private const val HAS_SOURCE_CODE_HASH = 36873697L
        @JvmField
        val hasSourceCodeBind =
            ObjectCalls.getMethodBind("Script", "has_source_code", HAS_SOURCE_CODE_HASH)

        private const val GET_SOURCE_CODE_HASH = 201670096L
        @JvmField
        val getSourceCodeBind =
            ObjectCalls.getMethodBind("Script", "get_source_code", GET_SOURCE_CODE_HASH)

        private const val SET_SOURCE_CODE_HASH = 83702148L
        @JvmField
        val setSourceCodeBind =
            ObjectCalls.getMethodBind("Script", "set_source_code", SET_SOURCE_CODE_HASH)

        private const val RELOAD_HASH = 1633102583L
        @JvmField
        val reloadBind =
            ObjectCalls.getMethodBind("Script", "reload", RELOAD_HASH)

        private const val GET_BASE_SCRIPT_HASH = 278624046L
        @JvmField
        val getBaseScriptBind =
            ObjectCalls.getMethodBind("Script", "get_base_script", GET_BASE_SCRIPT_HASH)

        private const val GET_INSTANCE_BASE_TYPE_HASH = 2002593661L
        @JvmField
        val getInstanceBaseTypeBind =
            ObjectCalls.getMethodBind("Script", "get_instance_base_type", GET_INSTANCE_BASE_TYPE_HASH)

        private const val GET_GLOBAL_NAME_HASH = 2002593661L
        @JvmField
        val getGlobalNameBind =
            ObjectCalls.getMethodBind("Script", "get_global_name", GET_GLOBAL_NAME_HASH)

        private const val HAS_SCRIPT_METHOD_HASH = 2619796661L
        @JvmField
        val hasScriptMethodBind =
            ObjectCalls.getMethodBind("Script", "has_script_method", HAS_SCRIPT_METHOD_HASH)

        private const val HAS_SCRIPT_SIGNAL_HASH = 2619796661L
        @JvmField
        val hasScriptSignalBind =
            ObjectCalls.getMethodBind("Script", "has_script_signal", HAS_SCRIPT_SIGNAL_HASH)

        private const val GET_SCRIPT_PROPERTY_LIST_HASH = 2915620761L
        @JvmField
        val getScriptPropertyListBind =
            ObjectCalls.getMethodBind("Script", "get_script_property_list", GET_SCRIPT_PROPERTY_LIST_HASH)

        private const val GET_SCRIPT_METHOD_LIST_HASH = 2915620761L
        @JvmField
        val getScriptMethodListBind =
            ObjectCalls.getMethodBind("Script", "get_script_method_list", GET_SCRIPT_METHOD_LIST_HASH)

        private const val GET_SCRIPT_SIGNAL_LIST_HASH = 2915620761L
        @JvmField
        val getScriptSignalListBind =
            ObjectCalls.getMethodBind("Script", "get_script_signal_list", GET_SCRIPT_SIGNAL_LIST_HASH)

        private const val GET_SCRIPT_CONSTANT_MAP_HASH = 2382534195L
        @JvmField
        val getScriptConstantMapBind =
            ObjectCalls.getMethodBind("Script", "get_script_constant_map", GET_SCRIPT_CONSTANT_MAP_HASH)

        private const val GET_PROPERTY_DEFAULT_VALUE_HASH = 2138907829L
        @JvmField
        val getPropertyDefaultValueBind =
            ObjectCalls.getMethodBind("Script", "get_property_default_value", GET_PROPERTY_DEFAULT_VALUE_HASH)

        private const val IS_TOOL_HASH = 36873697L
        @JvmField
        val isToolBind =
            ObjectCalls.getMethodBind("Script", "is_tool", IS_TOOL_HASH)

        private const val IS_ABSTRACT_HASH = 36873697L
        @JvmField
        val isAbstractBind =
            ObjectCalls.getMethodBind("Script", "is_abstract", IS_ABSTRACT_HASH)

        private const val GET_RPC_CONFIG_HASH = 1214101251L
        @JvmField
        val getRpcConfigBind =
            ObjectCalls.getMethodBind("Script", "get_rpc_config", GET_RPC_CONFIG_HASH)

        private const val INSTANCE_HAS_HASH = 397768994L
        @JvmField
        val instanceHasBind =
            ObjectCalls.getMethodBind("Script", "instance_has", INSTANCE_HAS_HASH)
    }
}
