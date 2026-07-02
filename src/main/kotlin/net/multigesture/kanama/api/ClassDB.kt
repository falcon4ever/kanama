package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

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

    @JvmStatic
    fun getClassList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getClassListBind, singleton)
    }

    @JvmStatic
    fun getInheritersFromClass(classValue: String): List<String> {
        return ObjectCalls.ptrcallWithStringNameArgRetPackedStringList(getInheritersFromClassBind, singleton, classValue)
    }

    @JvmStatic
    fun getParentClass(classValue: String): String {
        return ObjectCalls.ptrcallWithStringNameArgRetStringName(getParentClassBind, singleton, classValue)
    }

    @JvmStatic
    fun classExists(classValue: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(classExistsBind, singleton, classValue)
    }

    @JvmStatic
    fun isParentClass(classValue: String, inherits: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(isParentClassBind, singleton, classValue, inherits)
    }

    @JvmStatic
    fun canInstantiate(classValue: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(canInstantiateBind, singleton, classValue)
    }

    @JvmStatic
    /**
     * Creates an instance of `class`.
     *
     * Generated from Godot docs: ClassDB.instantiate
     */
    fun instantiate(classValue: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalar(instantiateBind, singleton, classValue)
    }

    @JvmStatic
    fun classGetApiType(classValue: String): Long {
        return ObjectCalls.ptrcallWithStringNameArgRetLong(classGetApiTypeBind, singleton, classValue)
    }

    @JvmStatic
    fun classHasSignal(classValue: String, signal: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(classHasSignalBind, singleton, classValue, signal)
    }

    @JvmStatic
    fun classGetSignal(classValue: String, signal: String): Map<String, Any?> {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetDictionary(classGetSignalBind, singleton, classValue, signal)
    }

    @JvmStatic
    fun classGetSignalList(classValue: String, noInheritance: Boolean = false): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetDictionaryList(classGetSignalListBind, singleton, classValue, noInheritance)
    }

    @JvmStatic
    fun classGetPropertyList(classValue: String, noInheritance: Boolean = false): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetDictionaryList(classGetPropertyListBind, singleton, classValue, noInheritance)
    }

    @JvmStatic
    fun classGetPropertyGetter(classValue: String, property: String): String {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetStringName(classGetPropertyGetterBind, singleton, classValue, property)
    }

    @JvmStatic
    fun classGetPropertySetter(classValue: String, property: String): String {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetStringName(classGetPropertySetterBind, singleton, classValue, property)
    }

    @JvmStatic
    fun classGetProperty(objectValue: GodotObject, property: String): Any? {
        return ObjectCalls.ptrcallWithObjectStringNameArgRetVariantScalar(classGetPropertyBind, singleton, objectValue.handle, property)
    }

    @JvmStatic
    fun classSetProperty(objectValue: GodotObject, property: String, value: Any?): Long {
        return ObjectCalls.ptrcallWithObjectStringNameAndVariantArgRetLong(classSetPropertyBind, singleton, objectValue.handle, property, value)
    }

    @JvmStatic
    fun classGetPropertyDefaultValue(classValue: String, property: String): Any? {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetVariantScalar(classGetPropertyDefaultValueBind, singleton, classValue, property)
    }

    @JvmStatic
    fun classHasMethod(classValue: String, method: String, noInheritance: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetBool(classHasMethodBind, singleton, classValue, method, noInheritance)
    }

    @JvmStatic
    fun classGetMethodArgumentCount(classValue: String, method: String, noInheritance: Boolean = false): Int {
        return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetInt(classGetMethodArgumentCountBind, singleton, classValue, method, noInheritance)
    }

    @JvmStatic
    fun classGetMethodList(classValue: String, noInheritance: Boolean = false): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetDictionaryList(classGetMethodListBind, singleton, classValue, noInheritance)
    }

    @JvmStatic
    fun classCallStatic(classValue: String, method: String, vararg extraArgs: Any?): Any? {
        return ObjectCalls.callWithVariantArgs(classCallStaticBind, singleton, listOf(classValue, method, *extraArgs))
    }

    @JvmStatic
    fun classGetIntegerConstantList(classValue: String, noInheritance: Boolean = false): List<String> {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetPackedStringList(classGetIntegerConstantListBind, singleton, classValue, noInheritance)
    }

    @JvmStatic
    fun classHasIntegerConstant(classValue: String, name: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(classHasIntegerConstantBind, singleton, classValue, name)
    }

    @JvmStatic
    fun classGetIntegerConstant(classValue: String, name: String): Long {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetLong(classGetIntegerConstantBind, singleton, classValue, name)
    }

    @JvmStatic
    fun classHasEnum(classValue: String, name: String, noInheritance: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetBool(classHasEnumBind, singleton, classValue, name, noInheritance)
    }

    @JvmStatic
    fun classGetEnumList(classValue: String, noInheritance: Boolean = false): List<String> {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetPackedStringList(classGetEnumListBind, singleton, classValue, noInheritance)
    }

    @JvmStatic
    fun classGetEnumConstants(classValue: String, enum: String, noInheritance: Boolean = false): List<String> {
        return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetPackedStringList(classGetEnumConstantsBind, singleton, classValue, enum, noInheritance)
    }

    @JvmStatic
    fun classGetIntegerConstantEnum(classValue: String, name: String, noInheritance: Boolean = false): String {
        return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetStringName(classGetIntegerConstantEnumBind, singleton, classValue, name, noInheritance)
    }

    @JvmStatic
    fun isClassEnumBitfield(classValue: String, enum: String, noInheritance: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetBool(isClassEnumBitfieldBind, singleton, classValue, enum, noInheritance)
    }

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

    private const val GET_INHERITERS_FROM_CLASS_HASH = 1761182771L
    private val getInheritersFromClassBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "get_inheriters_from_class", GET_INHERITERS_FROM_CLASS_HASH)
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

    private const val CLASS_GET_SIGNAL_HASH = 3061114238L
    private val classGetSignalBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_get_signal", CLASS_GET_SIGNAL_HASH)
    }

    private const val CLASS_GET_SIGNAL_LIST_HASH = 3504980660L
    private val classGetSignalListBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_get_signal_list", CLASS_GET_SIGNAL_LIST_HASH)
    }

    private const val CLASS_GET_PROPERTY_LIST_HASH = 3504980660L
    private val classGetPropertyListBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_get_property_list", CLASS_GET_PROPERTY_LIST_HASH)
    }

    private const val CLASS_GET_PROPERTY_GETTER_HASH = 3770832642L
    private val classGetPropertyGetterBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_get_property_getter", CLASS_GET_PROPERTY_GETTER_HASH)
    }

    private const val CLASS_GET_PROPERTY_SETTER_HASH = 3770832642L
    private val classGetPropertySetterBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_get_property_setter", CLASS_GET_PROPERTY_SETTER_HASH)
    }

    private const val CLASS_GET_PROPERTY_HASH = 2498641674L
    private val classGetPropertyBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_get_property", CLASS_GET_PROPERTY_HASH)
    }

    private const val CLASS_SET_PROPERTY_HASH = 1690314931L
    private val classSetPropertyBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_set_property", CLASS_SET_PROPERTY_HASH)
    }

    private const val CLASS_GET_PROPERTY_DEFAULT_VALUE_HASH = 2718203076L
    private val classGetPropertyDefaultValueBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_get_property_default_value", CLASS_GET_PROPERTY_DEFAULT_VALUE_HASH)
    }

    private const val CLASS_HAS_METHOD_HASH = 3860701026L
    private val classHasMethodBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_has_method", CLASS_HAS_METHOD_HASH)
    }

    private const val CLASS_GET_METHOD_ARGUMENT_COUNT_HASH = 3885694822L
    private val classGetMethodArgumentCountBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_get_method_argument_count", CLASS_GET_METHOD_ARGUMENT_COUNT_HASH)
    }

    private const val CLASS_GET_METHOD_LIST_HASH = 3504980660L
    private val classGetMethodListBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_get_method_list", CLASS_GET_METHOD_LIST_HASH)
    }

    private const val CLASS_CALL_STATIC_HASH = 3344196419L
    private val classCallStaticBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_call_static", CLASS_CALL_STATIC_HASH)
    }

    private const val CLASS_GET_INTEGER_CONSTANT_LIST_HASH = 3031669221L
    private val classGetIntegerConstantListBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_get_integer_constant_list", CLASS_GET_INTEGER_CONSTANT_LIST_HASH)
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

    private const val CLASS_GET_ENUM_LIST_HASH = 3031669221L
    private val classGetEnumListBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_get_enum_list", CLASS_GET_ENUM_LIST_HASH)
    }

    private const val CLASS_GET_ENUM_CONSTANTS_HASH = 661528303L
    private val classGetEnumConstantsBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "class_get_enum_constants", CLASS_GET_ENUM_CONSTANTS_HASH)
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
