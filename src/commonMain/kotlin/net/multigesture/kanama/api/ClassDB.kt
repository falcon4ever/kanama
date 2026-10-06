package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A class information repository.
 *
 * Generated from Godot docs: ClassDB
 */
object ClassDB {
    private inline val singleton: RawSegment
        get() = Binds.singleton

    /**
     * Returns the names of all engine classes available. Note: Script-defined classes with
     * `class_name` are not included in this list. Use `ProjectSettings.get_global_class_list` to get a
     * list of script-defined classes instead.
     *
     * Generated from Godot docs: ClassDB.get_class_list
     */
    @JvmStatic
    fun getClassList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getClassListBind, singleton)
    }

    /**
     * Returns the names of all engine classes that directly or indirectly inherit from `class`.
     *
     * Generated from Godot docs: ClassDB.get_inheriters_from_class
     */
    @JvmStatic
    fun getInheritersFromClass(classValue: String): List<String> {
        return ObjectCalls.ptrcallWithStringNameArgRetPackedStringList(Binds.getInheritersFromClassBind, singleton, classValue)
    }

    /**
     * Returns the parent class of `class`.
     *
     * Generated from Godot docs: ClassDB.get_parent_class
     */
    @JvmStatic
    fun getParentClass(classValue: String): String {
        return ObjectCalls.ptrcallWithStringNameArgRetStringName(Binds.getParentClassBind, singleton, classValue)
    }

    /**
     * Returns whether the specified `class` is available or not.
     *
     * Generated from Godot docs: ClassDB.class_exists
     */
    @JvmStatic
    fun classExists(classValue: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.classExistsBind, singleton, classValue)
    }

    /**
     * Returns whether `inherits` is an ancestor of `class` or not.
     *
     * Generated from Godot docs: ClassDB.is_parent_class
     */
    @JvmStatic
    fun isParentClass(classValue: String, inherits: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(Binds.isParentClassBind, singleton, classValue, inherits)
    }

    /**
     * Returns `true` if objects can be instantiated from the specified `class`, otherwise returns
     * `false`.
     *
     * Generated from Godot docs: ClassDB.can_instantiate
     */
    @JvmStatic
    fun canInstantiate(classValue: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.canInstantiateBind, singleton, classValue)
    }

    /**
     * Creates an instance of `class`.
     *
     * Generated from Godot docs: ClassDB.instantiate
     */
    @JvmStatic
    fun instantiate(classValue: String): Any? {
        return ObjectCalls.ptrcallWithStringNameArgRetVariantScalarOwned(Binds.instantiateBind, singleton, classValue)
    }

    /**
     * Returns the API type of the specified `class`.
     *
     * Generated from Godot docs: ClassDB.class_get_api_type
     */
    @JvmStatic
    fun classGetApiType(classValue: String): ClassDB.APIType {
        return ClassDB.APIType(ObjectCalls.ptrcallWithStringNameArgRetLong(Binds.classGetApiTypeBind, singleton, classValue))
    }

    /**
     * Returns whether `class` or its ancestry has a signal called `signal` or not.
     *
     * Generated from Godot docs: ClassDB.class_has_signal
     */
    @JvmStatic
    fun classHasSignal(classValue: String, signal: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(Binds.classHasSignalBind, singleton, classValue, signal)
    }

    /**
     * Returns the `signal` data of `class` or its ancestry. The returned value is a `Dictionary` with
     * the following keys: `args`, `default_args`, `flags`, `id`, `name`, `return: (class_name, hint,
     * hint_string, name, type, usage)`.
     *
     * Generated from Godot docs: ClassDB.class_get_signal
     */
    @JvmStatic
    fun classGetSignal(classValue: String, signal: String): Map<String, Any?> {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetDictionary(Binds.classGetSignalBind, singleton, classValue, signal)
    }

    /**
     * Returns an array with all the signals of `class` or its ancestry if `no_inheritance` is `false`.
     * Every element of the array is a `Dictionary` as described in `class_get_signal`.
     *
     * Generated from Godot docs: ClassDB.class_get_signal_list
     */
    @JvmStatic
    fun classGetSignalList(classValue: String, noInheritance: Boolean = false): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetDictionaryList(Binds.classGetSignalListBind, singleton, classValue, noInheritance)
    }

    /**
     * Returns an array with all the properties of `class` or its ancestry if `no_inheritance` is
     * `false`.
     *
     * Generated from Godot docs: ClassDB.class_get_property_list
     */
    @JvmStatic
    fun classGetPropertyList(classValue: String, noInheritance: Boolean = false): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetDictionaryList(Binds.classGetPropertyListBind, singleton, classValue, noInheritance)
    }

    /**
     * Returns the getter method name of `property` of `class`.
     *
     * Generated from Godot docs: ClassDB.class_get_property_getter
     */
    @JvmStatic
    fun classGetPropertyGetter(classValue: String, property: String): String {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetStringName(Binds.classGetPropertyGetterBind, singleton, classValue, property)
    }

    /**
     * Returns the setter method name of `property` of `class`.
     *
     * Generated from Godot docs: ClassDB.class_get_property_setter
     */
    @JvmStatic
    fun classGetPropertySetter(classValue: String, property: String): String {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetStringName(Binds.classGetPropertySetterBind, singleton, classValue, property)
    }

    /**
     * Returns the value of `property` of `object` or its ancestry.
     *
     * Generated from Godot docs: ClassDB.class_get_property
     */
    @JvmStatic
    fun classGetProperty(objectValue: GodotObject, property: String): Any? {
        return ObjectCalls.ptrcallWithObjectStringNameArgRetVariantScalar(Binds.classGetPropertyBind, singleton, objectValue.segment, property)
    }

    /**
     * Sets `property` value of `object` to `value`.
     *
     * Generated from Godot docs: ClassDB.class_set_property
     */
    @JvmStatic
    fun classSetProperty(objectValue: GodotObject, property: String, value: Any?): GodotError {
        return GodotError(ObjectCalls.ptrcallWithObjectStringNameAndVariantArgRetLong(Binds.classSetPropertyBind, singleton, objectValue.segment, property, value))
    }

    /**
     * Returns the default value of `property` of `class` or its ancestor classes.
     *
     * Generated from Godot docs: ClassDB.class_get_property_default_value
     */
    @JvmStatic
    fun classGetPropertyDefaultValue(classValue: String, property: String): Any? {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetVariantScalar(Binds.classGetPropertyDefaultValueBind, singleton, classValue, property)
    }

    /**
     * Returns whether `class` (or its ancestry if `no_inheritance` is `false`) has a method called
     * `method` or not.
     *
     * Generated from Godot docs: ClassDB.class_has_method
     */
    @JvmStatic
    fun classHasMethod(classValue: String, method: String, noInheritance: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetBool(Binds.classHasMethodBind, singleton, classValue, method, noInheritance)
    }

    /**
     * Returns the number of arguments of the method `method` of `class` or its ancestry if
     * `no_inheritance` is `false`.
     *
     * Generated from Godot docs: ClassDB.class_get_method_argument_count
     */
    @JvmStatic
    fun classGetMethodArgumentCount(classValue: String, method: String, noInheritance: Boolean = false): Int {
        return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetInt(Binds.classGetMethodArgumentCountBind, singleton, classValue, method, noInheritance)
    }

    /**
     * Returns an array with all the methods of `class` or its ancestry if `no_inheritance` is `false`.
     * Every element of the array is a `Dictionary` with the following keys: `args`, `default_args`,
     * `flags`, `id`, `name`, `return: (class_name, hint, hint_string, name, type, usage)`. Note: In
     * exported release builds the debug info is not available, so the returned dictionaries will
     * contain only method names.
     *
     * Generated from Godot docs: ClassDB.class_get_method_list
     */
    @JvmStatic
    fun classGetMethodList(classValue: String, noInheritance: Boolean = false): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetDictionaryList(Binds.classGetMethodListBind, singleton, classValue, noInheritance)
    }

    /**
     * Calls a static method on a class.
     *
     * Generated from Godot docs: ClassDB.class_call_static
     */
    @JvmStatic
    fun classCallStatic(classValue: String, method: String, vararg extraArgs: Any?): Any? {
        return ObjectCalls.callWithVariantArgsOwned(Binds.classCallStaticBind, singleton, listOf(classValue, method, *extraArgs))
    }

    /**
     * Returns an array with the names all the integer constants of `class` or its ancestry.
     *
     * Generated from Godot docs: ClassDB.class_get_integer_constant_list
     */
    @JvmStatic
    fun classGetIntegerConstantList(classValue: String, noInheritance: Boolean = false): List<String> {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetPackedStringList(Binds.classGetIntegerConstantListBind, singleton, classValue, noInheritance)
    }

    /**
     * Returns whether `class` or its ancestry has an integer constant called `name` or not.
     *
     * Generated from Godot docs: ClassDB.class_has_integer_constant
     */
    @JvmStatic
    fun classHasIntegerConstant(classValue: String, name: String): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetBool(Binds.classHasIntegerConstantBind, singleton, classValue, name)
    }

    /**
     * Returns the value of the integer constant `name` of `class` or its ancestry. Always returns 0
     * when the constant could not be found.
     *
     * Generated from Godot docs: ClassDB.class_get_integer_constant
     */
    @JvmStatic
    fun classGetIntegerConstant(classValue: String, name: String): Long {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetLong(Binds.classGetIntegerConstantBind, singleton, classValue, name)
    }

    /**
     * Returns whether `class` or its ancestry has an enum called `name` or not.
     *
     * Generated from Godot docs: ClassDB.class_has_enum
     */
    @JvmStatic
    fun classHasEnum(classValue: String, name: String, noInheritance: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetBool(Binds.classHasEnumBind, singleton, classValue, name, noInheritance)
    }

    /**
     * Returns an array with all the enums of `class` or its ancestry.
     *
     * Generated from Godot docs: ClassDB.class_get_enum_list
     */
    @JvmStatic
    fun classGetEnumList(classValue: String, noInheritance: Boolean = false): List<String> {
        return ObjectCalls.ptrcallWithStringNameAndBoolArgRetPackedStringList(Binds.classGetEnumListBind, singleton, classValue, noInheritance)
    }

    /**
     * Returns an array with all the keys in `enum` of `class` or its ancestry.
     *
     * Generated from Godot docs: ClassDB.class_get_enum_constants
     */
    @JvmStatic
    fun classGetEnumConstants(classValue: String, enum: String, noInheritance: Boolean = false): List<String> {
        return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetPackedStringList(Binds.classGetEnumConstantsBind, singleton, classValue, enum, noInheritance)
    }

    /**
     * Returns which enum the integer constant `name` of `class` or its ancestry belongs to.
     *
     * Generated from Godot docs: ClassDB.class_get_integer_constant_enum
     */
    @JvmStatic
    fun classGetIntegerConstantEnum(classValue: String, name: String, noInheritance: Boolean = false): String {
        return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetStringName(Binds.classGetIntegerConstantEnumBind, singleton, classValue, name, noInheritance)
    }

    /**
     * Returns whether `class` (or its ancestor classes if `no_inheritance` is `false`) has an enum
     * called `enum` that is a bitfield.
     *
     * Generated from Godot docs: ClassDB.is_class_enum_bitfield
     */
    @JvmStatic
    fun isClassEnumBitfield(classValue: String, enum: String, noInheritance: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithTwoStringNameAndBoolArgsRetBool(Binds.isClassEnumBitfieldBind, singleton, classValue, enum, noInheritance)
    }

    /**
     * Returns whether this `class` is enabled or not.
     *
     * Generated from Godot docs: ClassDB.is_class_enabled
     */
    @JvmStatic
    fun isClassEnabled(classValue: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.isClassEnabledBind, singleton, classValue)
    }

    /**
     * Godot's `ClassDB.APIType` enum as a typed value: `.value` is the raw number Godot uses, and the
     * companion holds the named values (`ClassDB.APIType.<NAME>`).
     *
     * Generated from Godot docs: ClassDB.APIType
     */
    @JvmInline
    value class APIType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Native Core class type.
             *
             * Generated from Godot docs: ClassDB.API_CORE
             */
            val CORE: APIType get() = APIType(0L)
            /**
             * Native Editor class type.
             *
             * Generated from Godot docs: ClassDB.API_EDITOR
             */
            val EDITOR: APIType get() = APIType(1L)
            /**
             * GDExtension class type.
             *
             * Generated from Godot docs: ClassDB.API_EXTENSION
             */
            val EXTENSION: APIType get() = APIType(2L)
            /**
             * GDExtension Editor class type.
             *
             * Generated from Godot docs: ClassDB.API_EDITOR_EXTENSION
             */
            val EDITOR_EXTENSION: APIType get() = APIType(3L)
            /**
             * Unknown class type.
             *
             * Generated from Godot docs: ClassDB.API_NONE
             */
            val NONE: APIType get() = APIType(4L)
        }
    }

    @JvmStatic
    fun fromHandle(handle: GodotHandle): ClassDB? =
        wrap(handle.segment)

    internal fun wrap(handle: RawSegment): ClassDB? =
        if (handle.address() == 0L) null else this

    private object Binds {
        @JvmField
        val singleton = ObjectCalls.getSingleton("ClassDB")

        private const val GET_CLASS_LIST_HASH = 1139954409L
        @JvmField
        val getClassListBind =
            ObjectCalls.getMethodBind("ClassDB", "get_class_list", GET_CLASS_LIST_HASH)

        private const val GET_INHERITERS_FROM_CLASS_HASH = 1761182771L
        @JvmField
        val getInheritersFromClassBind =
            ObjectCalls.getMethodBind("ClassDB", "get_inheriters_from_class", GET_INHERITERS_FROM_CLASS_HASH)

        private const val GET_PARENT_CLASS_HASH = 1965194235L
        @JvmField
        val getParentClassBind =
            ObjectCalls.getMethodBind("ClassDB", "get_parent_class", GET_PARENT_CLASS_HASH)

        private const val CLASS_EXISTS_HASH = 2619796661L
        @JvmField
        val classExistsBind =
            ObjectCalls.getMethodBind("ClassDB", "class_exists", CLASS_EXISTS_HASH)

        private const val IS_PARENT_CLASS_HASH = 471820014L
        @JvmField
        val isParentClassBind =
            ObjectCalls.getMethodBind("ClassDB", "is_parent_class", IS_PARENT_CLASS_HASH)

        private const val CAN_INSTANTIATE_HASH = 2619796661L
        @JvmField
        val canInstantiateBind =
            ObjectCalls.getMethodBind("ClassDB", "can_instantiate", CAN_INSTANTIATE_HASH)

        private const val INSTANTIATE_HASH = 2760726917L
        @JvmField
        val instantiateBind =
            ObjectCalls.getMethodBind("ClassDB", "instantiate", INSTANTIATE_HASH)

        private const val CLASS_GET_API_TYPE_HASH = 2475317043L
        @JvmField
        val classGetApiTypeBind =
            ObjectCalls.getMethodBind("ClassDB", "class_get_api_type", CLASS_GET_API_TYPE_HASH)

        private const val CLASS_HAS_SIGNAL_HASH = 471820014L
        @JvmField
        val classHasSignalBind =
            ObjectCalls.getMethodBind("ClassDB", "class_has_signal", CLASS_HAS_SIGNAL_HASH)

        private const val CLASS_GET_SIGNAL_HASH = 3061114238L
        @JvmField
        val classGetSignalBind =
            ObjectCalls.getMethodBind("ClassDB", "class_get_signal", CLASS_GET_SIGNAL_HASH)

        private const val CLASS_GET_SIGNAL_LIST_HASH = 3504980660L
        @JvmField
        val classGetSignalListBind =
            ObjectCalls.getMethodBind("ClassDB", "class_get_signal_list", CLASS_GET_SIGNAL_LIST_HASH)

        private const val CLASS_GET_PROPERTY_LIST_HASH = 3504980660L
        @JvmField
        val classGetPropertyListBind =
            ObjectCalls.getMethodBind("ClassDB", "class_get_property_list", CLASS_GET_PROPERTY_LIST_HASH)

        private const val CLASS_GET_PROPERTY_GETTER_HASH = 3770832642L
        @JvmField
        val classGetPropertyGetterBind =
            ObjectCalls.getMethodBind("ClassDB", "class_get_property_getter", CLASS_GET_PROPERTY_GETTER_HASH)

        private const val CLASS_GET_PROPERTY_SETTER_HASH = 3770832642L
        @JvmField
        val classGetPropertySetterBind =
            ObjectCalls.getMethodBind("ClassDB", "class_get_property_setter", CLASS_GET_PROPERTY_SETTER_HASH)

        private const val CLASS_GET_PROPERTY_HASH = 2498641674L
        @JvmField
        val classGetPropertyBind =
            ObjectCalls.getMethodBind("ClassDB", "class_get_property", CLASS_GET_PROPERTY_HASH)

        private const val CLASS_SET_PROPERTY_HASH = 1690314931L
        @JvmField
        val classSetPropertyBind =
            ObjectCalls.getMethodBind("ClassDB", "class_set_property", CLASS_SET_PROPERTY_HASH)

        private const val CLASS_GET_PROPERTY_DEFAULT_VALUE_HASH = 2718203076L
        @JvmField
        val classGetPropertyDefaultValueBind =
            ObjectCalls.getMethodBind("ClassDB", "class_get_property_default_value", CLASS_GET_PROPERTY_DEFAULT_VALUE_HASH)

        private const val CLASS_HAS_METHOD_HASH = 3860701026L
        @JvmField
        val classHasMethodBind =
            ObjectCalls.getMethodBind("ClassDB", "class_has_method", CLASS_HAS_METHOD_HASH)

        private const val CLASS_GET_METHOD_ARGUMENT_COUNT_HASH = 3885694822L
        @JvmField
        val classGetMethodArgumentCountBind =
            ObjectCalls.getMethodBind("ClassDB", "class_get_method_argument_count", CLASS_GET_METHOD_ARGUMENT_COUNT_HASH)

        private const val CLASS_GET_METHOD_LIST_HASH = 3504980660L
        @JvmField
        val classGetMethodListBind =
            ObjectCalls.getMethodBind("ClassDB", "class_get_method_list", CLASS_GET_METHOD_LIST_HASH)

        private const val CLASS_CALL_STATIC_HASH = 3344196419L
        @JvmField
        val classCallStaticBind =
            ObjectCalls.getMethodBind("ClassDB", "class_call_static", CLASS_CALL_STATIC_HASH)

        private const val CLASS_GET_INTEGER_CONSTANT_LIST_HASH = 3031669221L
        @JvmField
        val classGetIntegerConstantListBind =
            ObjectCalls.getMethodBind("ClassDB", "class_get_integer_constant_list", CLASS_GET_INTEGER_CONSTANT_LIST_HASH)

        private const val CLASS_HAS_INTEGER_CONSTANT_HASH = 471820014L
        @JvmField
        val classHasIntegerConstantBind =
            ObjectCalls.getMethodBind("ClassDB", "class_has_integer_constant", CLASS_HAS_INTEGER_CONSTANT_HASH)

        private const val CLASS_GET_INTEGER_CONSTANT_HASH = 2419549490L
        @JvmField
        val classGetIntegerConstantBind =
            ObjectCalls.getMethodBind("ClassDB", "class_get_integer_constant", CLASS_GET_INTEGER_CONSTANT_HASH)

        private const val CLASS_HAS_ENUM_HASH = 3860701026L
        @JvmField
        val classHasEnumBind =
            ObjectCalls.getMethodBind("ClassDB", "class_has_enum", CLASS_HAS_ENUM_HASH)

        private const val CLASS_GET_ENUM_LIST_HASH = 3031669221L
        @JvmField
        val classGetEnumListBind =
            ObjectCalls.getMethodBind("ClassDB", "class_get_enum_list", CLASS_GET_ENUM_LIST_HASH)

        private const val CLASS_GET_ENUM_CONSTANTS_HASH = 661528303L
        @JvmField
        val classGetEnumConstantsBind =
            ObjectCalls.getMethodBind("ClassDB", "class_get_enum_constants", CLASS_GET_ENUM_CONSTANTS_HASH)

        private const val CLASS_GET_INTEGER_CONSTANT_ENUM_HASH = 2457504236L
        @JvmField
        val classGetIntegerConstantEnumBind =
            ObjectCalls.getMethodBind("ClassDB", "class_get_integer_constant_enum", CLASS_GET_INTEGER_CONSTANT_ENUM_HASH)

        private const val IS_CLASS_ENUM_BITFIELD_HASH = 3860701026L
        @JvmField
        val isClassEnumBitfieldBind =
            ObjectCalls.getMethodBind("ClassDB", "is_class_enum_bitfield", IS_CLASS_ENUM_BITFIELD_HASH)

        private const val IS_CLASS_ENABLED_HASH = 2619796661L
        @JvmField
        val isClassEnabledBind =
            ObjectCalls.getMethodBind("ClassDB", "is_class_enabled", IS_CLASS_ENABLED_HASH)
    }
}
