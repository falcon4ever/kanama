package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A class information repository.
 *
 * Generated from Godot docs: ClassDB
 */
object ClassDB {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("ClassDB")
    }

    const val API_CORE: Long = 0L
    const val API_EDITOR: Long = 1L
    const val API_EXTENSION: Long = 2L
    const val API_EDITOR_EXTENSION: Long = 3L
    const val API_NONE: Long = 4L

    /**
     * Returns the names of all engine classes available. Note: Script-defined classes with
     * `class_name` are not included in this list. Use `ProjectSettings.get_global_class_list` to get a
     * list of script-defined classes instead.
     *
     * Generated from Godot docs: ClassDB.get_class_list
     */
    @JvmStatic
    fun getClassList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getClassListBind, singleton)
    }

    /**
     * Returns the parent class of `class`.
     *
     * Generated from Godot docs: ClassDB.get_parent_class
     */
    @JvmStatic
    fun getParentClass(classValue: String): String {
        return ObjectCalls.ptrcallWithStringNameArgRetStringName(getParentClassBind, singleton, classValue)
    }

    /**
     * Returns whether the specified `class` is available or not.
     *
     * Generated from Godot docs: ClassDB.class_exists
     */
    @JvmStatic
    fun classExists(classValue: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(classExistsBind, singleton, classValue)
    }

    /**
     * Returns whether `inherits` is an ancestor of `class` or not.
     *
     * Generated from Godot docs: ClassDB.is_parent_class
     */
    @JvmStatic
    fun isParentClass(classValue: String, inherits: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(isParentClassBind, singleton, classValue, inherits)
    }

    /**
     * Returns `true` if objects can be instantiated from the specified `class`, otherwise returns
     * `false`.
     *
     * Generated from Godot docs: ClassDB.can_instantiate
     */
    @JvmStatic
    fun canInstantiate(classValue: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(canInstantiateBind, singleton, classValue)
    }

    /**
     * Creates an instance of `class`.
     *
     * Generated from Godot docs: ClassDB.instantiate
     */
    @JvmStatic
    fun instantiate(classValue: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalarOwned(instantiateBind, singleton, classValue)
    }

    /**
     * Returns the API type of the specified `class`.
     *
     * Generated from Godot docs: ClassDB.class_get_api_type
     */
    @JvmStatic
    fun classGetApiType(classValue: String): Long {
        return ObjectCalls.ptrcallWithStringNameArgRetLong(classGetApiTypeBind, singleton, classValue)
    }

    /**
     * Returns whether `class` or its ancestry has a signal called `signal` or not.
     *
     * Generated from Godot docs: ClassDB.class_has_signal
     */
    @JvmStatic
    fun classHasSignal(classValue: String, signal: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(classHasSignalBind, singleton, classValue, signal)
    }

    /**
     * Returns the getter method name of `property` of `class`.
     *
     * Generated from Godot docs: ClassDB.class_get_property_getter
     */
    @JvmStatic
    fun classGetPropertyGetter(classValue: String, property: String): String {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetStringName(classGetPropertyGetterBind, singleton, classValue, property)
    }

    /**
     * Returns the setter method name of `property` of `class`.
     *
     * Generated from Godot docs: ClassDB.class_get_property_setter
     */
    @JvmStatic
    fun classGetPropertySetter(classValue: String, property: String): String {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetStringName(classGetPropertySetterBind, singleton, classValue, property)
    }

    /**
     * Returns whether `class` (or its ancestry if `no_inheritance` is `false`) has a method called
     * `method` or not.
     *
     * Generated from Godot docs: ClassDB.class_has_method
     */
    @JvmStatic
    fun classHasMethod(classValue: String, method: String, noInheritance: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetBool(classHasMethodBind, singleton, classValue, method, noInheritance)
    }

    /**
     * Returns the number of arguments of the method `method` of `class` or its ancestry if
     * `no_inheritance` is `false`.
     *
     * Generated from Godot docs: ClassDB.class_get_method_argument_count
     */
    @JvmStatic
    fun classGetMethodArgumentCount(classValue: String, method: String, noInheritance: Boolean = false): Int {
        return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetInt(classGetMethodArgumentCountBind, singleton, classValue, method, noInheritance)
    }

    /**
     * Calls a static method on a class.
     *
     * Generated from Godot docs: ClassDB.class_call_static
     */
    @JvmStatic
    fun classCallStatic(classValue: String, method: String, vararg extraArgs: Any?): Any? {
        return ObjectCalls.callWithVariantArgsOwned(classCallStaticBind, singleton, listOf(classValue, method, *extraArgs))
    }

    /**
     * Returns whether `class` or its ancestry has an integer constant called `name` or not.
     *
     * Generated from Godot docs: ClassDB.class_has_integer_constant
     */
    @JvmStatic
    fun classHasIntegerConstant(classValue: String, name: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(classHasIntegerConstantBind, singleton, classValue, name)
    }

    /**
     * Returns the value of the integer constant `name` of `class` or its ancestry. Always returns 0
     * when the constant could not be found.
     *
     * Generated from Godot docs: ClassDB.class_get_integer_constant
     */
    @JvmStatic
    fun classGetIntegerConstant(classValue: String, name: String): Long {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetLong(classGetIntegerConstantBind, singleton, classValue, name)
    }

    /**
     * Returns whether `class` or its ancestry has an enum called `name` or not.
     *
     * Generated from Godot docs: ClassDB.class_has_enum
     */
    @JvmStatic
    fun classHasEnum(classValue: String, name: String, noInheritance: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetBool(classHasEnumBind, singleton, classValue, name, noInheritance)
    }

    /**
     * Returns which enum the integer constant `name` of `class` or its ancestry belongs to.
     *
     * Generated from Godot docs: ClassDB.class_get_integer_constant_enum
     */
    @JvmStatic
    fun classGetIntegerConstantEnum(classValue: String, name: String, noInheritance: Boolean = false): String {
        return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetStringName(classGetIntegerConstantEnumBind, singleton, classValue, name, noInheritance)
    }

    /**
     * Returns whether `class` (or its ancestor classes if `no_inheritance` is `false`) has an enum
     * called `enum` that is a bitfield.
     *
     * Generated from Godot docs: ClassDB.is_class_enum_bitfield
     */
    @JvmStatic
    fun isClassEnumBitfield(classValue: String, enum: String, noInheritance: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetBool(isClassEnumBitfieldBind, singleton, classValue, enum, noInheritance)
    }

    /**
     * Returns whether this `class` is enabled or not.
     *
     * Generated from Godot docs: ClassDB.is_class_enabled
     */
    @JvmStatic
    fun isClassEnabled(classValue: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(isClassEnabledBind, singleton, classValue)
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): ClassDB? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): ClassDB? =
        if (handle.address() == 0L) null else this

    private const val GET_CLASS_LIST_HASH = 1139954409L
    private val getClassListBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "get_class_list", GET_CLASS_LIST_HASH)
    }

    private const val GET_PARENT_CLASS_HASH = 1965194235L
    private val getParentClassBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "get_parent_class", GET_PARENT_CLASS_HASH)
    }

    private const val CLASS_EXISTS_HASH = 2619796661L
    private val classExistsBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_exists", CLASS_EXISTS_HASH)
    }

    private const val IS_PARENT_CLASS_HASH = 471820014L
    private val isParentClassBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "is_parent_class", IS_PARENT_CLASS_HASH)
    }

    private const val CAN_INSTANTIATE_HASH = 2619796661L
    private val canInstantiateBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "can_instantiate", CAN_INSTANTIATE_HASH)
    }

    private const val INSTANTIATE_HASH = 2760726917L
    private val instantiateBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "instantiate", INSTANTIATE_HASH)
    }

    private const val CLASS_GET_API_TYPE_HASH = 2475317043L
    private val classGetApiTypeBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_get_api_type", CLASS_GET_API_TYPE_HASH)
    }

    private const val CLASS_HAS_SIGNAL_HASH = 471820014L
    private val classHasSignalBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_has_signal", CLASS_HAS_SIGNAL_HASH)
    }

    private const val CLASS_GET_PROPERTY_GETTER_HASH = 3770832642L
    private val classGetPropertyGetterBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_get_property_getter", CLASS_GET_PROPERTY_GETTER_HASH)
    }

    private const val CLASS_GET_PROPERTY_SETTER_HASH = 3770832642L
    private val classGetPropertySetterBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_get_property_setter", CLASS_GET_PROPERTY_SETTER_HASH)
    }

    private const val CLASS_HAS_METHOD_HASH = 3860701026L
    private val classHasMethodBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_has_method", CLASS_HAS_METHOD_HASH)
    }

    private const val CLASS_GET_METHOD_ARGUMENT_COUNT_HASH = 3885694822L
    private val classGetMethodArgumentCountBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_get_method_argument_count", CLASS_GET_METHOD_ARGUMENT_COUNT_HASH)
    }

    private const val CLASS_CALL_STATIC_HASH = 3344196419L
    private val classCallStaticBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_call_static", CLASS_CALL_STATIC_HASH)
    }

    private const val CLASS_HAS_INTEGER_CONSTANT_HASH = 471820014L
    private val classHasIntegerConstantBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_has_integer_constant", CLASS_HAS_INTEGER_CONSTANT_HASH)
    }

    private const val CLASS_GET_INTEGER_CONSTANT_HASH = 2419549490L
    private val classGetIntegerConstantBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_get_integer_constant", CLASS_GET_INTEGER_CONSTANT_HASH)
    }

    private const val CLASS_HAS_ENUM_HASH = 3860701026L
    private val classHasEnumBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_has_enum", CLASS_HAS_ENUM_HASH)
    }

    private const val CLASS_GET_INTEGER_CONSTANT_ENUM_HASH = 2457504236L
    private val classGetIntegerConstantEnumBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_get_integer_constant_enum", CLASS_GET_INTEGER_CONSTANT_ENUM_HASH)
    }

    private const val IS_CLASS_ENUM_BITFIELD_HASH = 3860701026L
    private val isClassEnumBitfieldBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "is_class_enum_bitfield", IS_CLASS_ENUM_BITFIELD_HASH)
    }

    private const val IS_CLASS_ENABLED_HASH = 2619796661L
    private val isClassEnabledBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "is_class_enabled", IS_CLASS_ENABLED_HASH)
    }
}
