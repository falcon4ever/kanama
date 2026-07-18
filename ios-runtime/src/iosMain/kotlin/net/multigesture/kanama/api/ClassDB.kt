package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
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

    fun getClassList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getClassListBind, singleton)
    }

    fun getParentClass(classValue: String): String {
        return ObjectCalls.ptrcallWithStringNameArgRetStringName(getParentClassBind, singleton, classValue)
    }

    fun classExists(classValue: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(classExistsBind, singleton, classValue)
    }

    fun isParentClass(classValue: String, inherits: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(isParentClassBind, singleton, classValue, inherits)
    }

    fun canInstantiate(classValue: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(canInstantiateBind, singleton, classValue)
    }

    fun instantiate(classValue: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalarOwned(instantiateBind, singleton, classValue)
    }

    fun classGetApiType(classValue: String): Long {
        return ObjectCalls.ptrcallWithStringNameArgRetLong(classGetApiTypeBind, singleton, classValue)
    }

    fun classHasSignal(classValue: String, signal: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(classHasSignalBind, singleton, classValue, signal)
    }

    fun classHasMethod(classValue: String, method: String, noInheritance: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetBool(classHasMethodBind, singleton, classValue, method, noInheritance)
    }

    fun classGetMethodArgumentCount(classValue: String, method: String, noInheritance: Boolean = false): Int {
        return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetInt(classGetMethodArgumentCountBind, singleton, classValue, method, noInheritance)
    }

    fun classCallStatic(classValue: String, method: String, vararg extraArgs: Any?): Any? {
        return ObjectCalls.callWithVariantArgsOwned(classCallStaticBind, singleton, listOf(classValue, method, *extraArgs))
    }

    fun classHasIntegerConstant(classValue: String, name: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(classHasIntegerConstantBind, singleton, classValue, name)
    }

    fun classGetIntegerConstant(classValue: String, name: String): Long {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetLong(classGetIntegerConstantBind, singleton, classValue, name)
    }

    fun classHasEnum(classValue: String, name: String, noInheritance: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetBool(classHasEnumBind, singleton, classValue, name, noInheritance)
    }

    fun isClassEnumBitfield(classValue: String, enum: String, noInheritance: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetBool(isClassEnumBitfieldBind, singleton, classValue, enum, noInheritance)
    }

    fun isClassEnabled(classValue: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(isClassEnabledBind, singleton, classValue)
    }

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

    private const val IS_CLASS_ENUM_BITFIELD_HASH = 3860701026L
    private val isClassEnumBitfieldBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "is_class_enum_bitfield", IS_CLASS_ENUM_BITFIELD_HASH)
    }

    private const val IS_CLASS_ENABLED_HASH = 2619796661L
    private val isClassEnabledBind by lazy {
        ObjectCalls.getMethodBind("ClassDB", "is_class_enabled", IS_CLASS_ENABLED_HASH)
    }
}
