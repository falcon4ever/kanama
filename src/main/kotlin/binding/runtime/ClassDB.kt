package net.multigesture.kanama.binding.runtime

import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_BYTE
import java.lang.foreign.ValueLayout.JAVA_INT
import net.multigesture.kanama.binding.runtime.GodotStructs.classCreationInfo
import net.multigesture.kanama.binding.runtime.GodotStructs.classMethodInfo
import net.multigesture.kanama.binding.runtime.GodotStructs.offsetOf
import net.multigesture.kanama.binding.runtime.GodotStructs.propertyInfo
import net.multigesture.kanama.ffi.GodotFFI

/**
 * High-level ClassDB registration API.
 *
 * Hides the hand-rolled struct packing, StringName interning, and empty-string dance from callers.
 * Everything below this file treats the engine as "call `registerClass`, then `registerMethod` /
 * `registerProperty` for each member" — the same shape the Phase 5 KSP generator will emit.
 *
 * Callers still own the upcall stubs (create_instance, free_instance, get_virtual, per-method
 * call/ptrcall) because those reference static Kotlin functions whose signatures must match the
 * exact ABI Godot expects. This object's job is the struct plumbing around them.
 */
object ClassDB {

  /** GDEXTENSION_METHOD_FLAG_NORMAL */
  private const val METHOD_FLAG_NORMAL = 1

  /** PROPERTY_USAGE_DEFAULT (STORAGE | EDITOR | NETWORK). */
  private const val PROPERTY_USAGE_DEFAULT = 6

  // Offsets — computed once, not per call.
  private val isExposedOff = offsetOf(classCreationInfo, "is_exposed")
  private val isRuntimeOff = offsetOf(classCreationInfo, "is_runtime")
  private val createInstanceOff = offsetOf(classCreationInfo, "create_instance_func")
  private val freeInstanceOff = offsetOf(classCreationInfo, "free_instance_func")
  private val getVirtualOff = offsetOf(classCreationInfo, "get_virtual_func")

  private val mNameOff = offsetOf(classMethodInfo, "name")
  private val mUserdataOff = offsetOf(classMethodInfo, "method_userdata")
  private val mCallOff = offsetOf(classMethodInfo, "call_func")
  private val mPtrcallOff = offsetOf(classMethodInfo, "ptrcall_func")
  private val mFlagsOff = offsetOf(classMethodInfo, "method_flags")
  private val mHasReturnOff = offsetOf(classMethodInfo, "has_return_value")
  private val mReturnInfoOff = offsetOf(classMethodInfo, "return_value_info")
  private val mReturnMetaOff = offsetOf(classMethodInfo, "return_value_metadata")
  private val mArgCountOff = offsetOf(classMethodInfo, "argument_count")
  private val mArgsInfoOff = offsetOf(classMethodInfo, "arguments_info")
  private val mArgsMetaOff = offsetOf(classMethodInfo, "arguments_metadata")
  private val mDefaultArgCountOff = offsetOf(classMethodInfo, "default_argument_count")
  private val mDefaultArgsOff = offsetOf(classMethodInfo, "default_arguments")

  private val pTypeOff = offsetOf(propertyInfo, "type")
  private val pNameOff = offsetOf(propertyInfo, "name")
  private val pClassNameOff = offsetOf(propertyInfo, "class_name")
  private val pHintOff = offsetOf(propertyInfo, "hint")
  private val pHintStringOff = offsetOf(propertyInfo, "hint_string")
  private val pUsageOff = offsetOf(propertyInfo, "usage")

  private val registerClassHandle by lazy {
    GodotFFI.lookup(
      "classdb_register_extension_class6",
      FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS, ADDRESS),
    )
  }

  private val registerMethodHandle by lazy {
    GodotFFI.lookup(
      "classdb_register_extension_class_method",
      FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS),
    )
  }

  private val registerPropertyHandle by lazy {
    GodotFFI.lookup(
      "classdb_register_extension_class_property",
      FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS, ADDRESS, ADDRESS),
    )
  }

  private val registerSignalHandle by lazy {
    GodotFFI.lookup(
      "classdb_register_extension_class_signal",
      FunctionDescriptor.ofVoid(
        ADDRESS,
        ADDRESS,
        ADDRESS,
        ADDRESS,
        java.lang.foreign.ValueLayout.JAVA_LONG,
      ),
    )
  }

  private val unregisterClassHandle by lazy {
    GodotFFI.lookup(
      "classdb_unregister_extension_class",
      FunctionDescriptor.ofVoid(ADDRESS, ADDRESS),
    )
  }

  private val registeredClasses = mutableListOf<RegisteredClass>()

  data class ClassSpec(
    val name: String,
    val parentName: String,
    val isExposed: Boolean = true,
    val isTool: Boolean = false,
    val createInstance: MemorySegment,
    val freeInstance: MemorySegment,
    val getVirtual: MemorySegment,
  )

  data class MethodArg(val name: String, val type: VariantType)

  data class MethodSpec(
    val name: String,
    val returnType: VariantType? = null,
    val args: List<MethodArg> = emptyList(),
    val callStub: MemorySegment,
    val ptrcallStub: MemorySegment,
  )

  /** Registered class handles. Returned from [registerClass], reused by method/property calls. */
  data class RegisteredClass(val className: MemorySegment, val parentName: MemorySegment)

  fun registerClass(library: MemorySegment, spec: ClassSpec): RegisteredClass {
    val className = GodotStrings.makeStringName(spec.name)
    val parentName = GodotStrings.makeStringName(spec.parentName)

    val info = GodotFFI.arena.allocate(classCreationInfo)
    if (spec.isExposed) info.set(JAVA_BYTE, isExposedOff, 1.toByte())
    // is_runtime=true → editor substitutes a placeholder instead of the real class.
    // @Tool maps to is_runtime=false so the class runs in the editor too.
    if (!spec.isTool) info.set(JAVA_BYTE, isRuntimeOff, 1.toByte())
    info.set(ADDRESS, createInstanceOff, spec.createInstance)
    info.set(ADDRESS, freeInstanceOff, spec.freeInstance)
    info.set(ADDRESS, getVirtualOff, spec.getVirtual)

    registerClassHandle.invoke(library, className, parentName, info)
    return RegisteredClass(className, parentName).also { registered ->
      synchronized(registeredClasses) { registeredClasses += registered }
    }
  }

  fun unregisterRegisteredClasses(library: MemorySegment) {
    val classes =
      synchronized(registeredClasses) {
        registeredClasses.asReversed().toList().also { registeredClasses.clear() }
      }
    for (cls in classes) {
      try {
        unregisterClassHandle.invoke(library, cls.className)
      } catch (t: Throwable) {
        System.err.println(
          "[kanama:kt] failed to unregister extension class at 0x${cls.className.address().toString(16)}: ${t.message}"
        )
      }
    }
    if (classes.isNotEmpty()) {
      System.err.println("[kanama:kt] unregistered ${classes.size} extension class(es)")
    }
  }

  fun registerMethod(library: MemorySegment, cls: RegisteredClass, spec: MethodSpec) {
    val methodName = GodotStrings.makeStringName(spec.name)
    val returnInfo =
      if (spec.returnType != null) {
        buildPropertyInfo(name = "", type = spec.returnType)
      } else {
        MemorySegment.NULL
      }

    val (argsInfo, argsMetadata) =
      if (spec.args.isEmpty()) {
        MemorySegment.NULL to MemorySegment.NULL
      } else {
        val argsArray = GodotFFI.arena.allocate(propertyInfo, spec.args.size.toLong())
        spec.args.forEachIndexed { i, arg ->
          val slot = argsArray.asSlice(i * propertyInfo.byteSize(), propertyInfo)
          fillPropertyInfo(slot, arg.name, arg.type)
        }
        val meta = GodotFFI.arena.allocate(JAVA_INT, spec.args.size.toLong())
        // GDEXTENSION_METHOD_ARGUMENT_METADATA_NONE = 0 — leave zero-initialized.
        argsArray to meta
      }

    val info = GodotFFI.arena.allocate(classMethodInfo)
    info.set(ADDRESS, mNameOff, methodName)
    info.set(ADDRESS, mUserdataOff, MemorySegment.NULL)
    info.set(ADDRESS, mCallOff, spec.callStub)
    info.set(ADDRESS, mPtrcallOff, spec.ptrcallStub)
    info.set(JAVA_INT, mFlagsOff, METHOD_FLAG_NORMAL)
    info.set(JAVA_BYTE, mHasReturnOff, if (spec.returnType != null) 1.toByte() else 0.toByte())
    info.set(ADDRESS, mReturnInfoOff, returnInfo)
    info.set(JAVA_INT, mReturnMetaOff, 0)
    info.set(JAVA_INT, mArgCountOff, spec.args.size)
    info.set(ADDRESS, mArgsInfoOff, argsInfo)
    info.set(ADDRESS, mArgsMetaOff, argsMetadata)
    info.set(JAVA_INT, mDefaultArgCountOff, 0)
    info.set(ADDRESS, mDefaultArgsOff, MemorySegment.NULL)

    registerMethodHandle.invoke(library, cls.className, info)
  }

  /**
   * Register a property backed by two previously-registered methods. Godot looks the setter/getter
   * up by StringName at property access time, so the methods must already be registered before this
   * call.
   */
  fun registerProperty(
    library: MemorySegment,
    cls: RegisteredClass,
    propertyName: String,
    type: VariantType,
    setterName: String,
    getterName: String,
    hint: Int = 0,
    hintString: String = "",
    usage: Int = PROPERTY_USAGE_DEFAULT,
  ) {
    val info = buildPropertyInfo(propertyName, type, hint, hintString, usage)
    val setter = GodotStrings.makeStringName(setterName)
    val getter = GodotStrings.makeStringName(getterName)
    registerPropertyHandle.invoke(library, cls.className, info, setter, getter)
  }

  /** A property descriptor used to build a static inspector list for ScriptInstance classes. */
  data class PropertySpec(
    val name: String,
    val type: VariantType,
    val hint: Int = 0,
    val hintString: String = "",
    val usage: Int = PROPERTY_USAGE_DEFAULT,
  )

  /**
   * Allocate a permanent [GDExtensionPropertyInfo] array in [GodotFFI.arena] from a list of
   * [PropertySpec]s. Returns [MemorySegment.NULL] when [specs] is empty.
   *
   * Used by KSP-generated [@ScriptClass][net.multigesture.kanama.annotations.ScriptClass]
   * registrars to build the static property list returned by the ScriptInstance.
   */
  fun buildPropertyList(specs: List<PropertySpec>): MemorySegment {
    if (specs.isEmpty()) return MemorySegment.NULL
    val arr = GodotFFI.arena.allocate(propertyInfo, specs.size.toLong())
    specs.forEachIndexed { i, spec ->
      val slot = arr.asSlice(i * propertyInfo.byteSize(), propertyInfo)
      fillPropertyInfo(slot, spec.name, spec.type, spec.hint, spec.hintString, spec.usage)
    }
    return arr
  }

  data class SignalArg(val name: String, val type: VariantType)

  fun registerSignal(
    library: MemorySegment,
    cls: RegisteredClass,
    signalName: String,
    args: List<SignalArg>,
  ) {
    val name = GodotStrings.makeStringName(signalName)
    val argsInfo =
      if (args.isEmpty()) {
        MemorySegment.NULL
      } else {
        val array = GodotFFI.arena.allocate(propertyInfo, args.size.toLong())
        args.forEachIndexed { i, arg ->
          val slot = array.asSlice(i * propertyInfo.byteSize(), propertyInfo)
          fillPropertyInfo(slot, arg.name, arg.type)
        }
        array
      }
    registerSignalHandle.invoke(library, cls.className, name, argsInfo, args.size.toLong())
  }

  private fun buildPropertyInfo(
    name: String,
    type: VariantType,
    hint: Int = 0,
    hintString: String = "",
    usage: Int = PROPERTY_USAGE_DEFAULT,
  ): MemorySegment {
    val info = GodotFFI.arena.allocate(propertyInfo)
    fillPropertyInfo(info, name, type, hint, hintString, usage)
    return info
  }

  private fun fillPropertyInfo(
    slot: MemorySegment,
    name: String,
    type: VariantType,
    hint: Int = 0,
    hintString: String = "",
    usage: Int = PROPERTY_USAGE_DEFAULT,
  ) {
    val nameSegment =
      if (name.isEmpty()) GodotStrings.emptyStringName else GodotStrings.makeStringName(name)
    val hintStringSegment =
      if (hintString.isEmpty()) GodotStrings.emptyString else GodotStrings.makeString(hintString)
    slot.set(JAVA_INT, pTypeOff, type.id)
    slot.set(ADDRESS, pNameOff, nameSegment)
    slot.set(ADDRESS, pClassNameOff, GodotStrings.emptyStringName)
    slot.set(JAVA_INT, pHintOff, hint)
    slot.set(ADDRESS, pHintStringOff, hintStringSegment)
    slot.set(JAVA_INT, pUsageOff, usage)
  }
}
