package net.multigesture.kanama.binding

import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_BYTE
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG
import java.lang.invoke.MethodType
import net.multigesture.kanama.binding.runtime.BuiltinTypes
import net.multigesture.kanama.binding.runtime.ClassDB
import net.multigesture.kanama.binding.runtime.GodotStrings
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.Upcalls
import net.multigesture.kanama.ffi.GodotFFI

/**
 * Represents a single Kotlin class registered as a Godot Script resource.
 *
 * One [KanamaScript] instance exists per [@ScriptClass]-annotated Kotlin class. When Godot calls
 * [_instance_create] on it (i.e. when a node has this script attached and enters the tree), it will
 * build a [KanamaScriptInstance] wrapping the real Kotlin object; that happens in the
 * ScriptInstance bridge.
 *
 * At this point this class is just enough to make the Script resource valid so that
 * [KanamaScriptLanguage] can hand it to the engine.
 */
class KanamaScript(
  val godotObject: MemorySegment,
  var instanceBaseType: String,
  var isTool: Boolean = false,
  var kotlinClassName: String,
  var globalName: String = "",
  var factory: ((MemorySegment) -> KanamaScriptInstance)? = null,
  var hasMethod: (Long) -> Boolean = { false },
  var getMethodArgumentCount: (Long) -> Int = { -1 },
  var hasScriptSignal: (Long) -> Boolean = { false },
  var writeScriptMethodList: (MemorySegment) -> Unit = {
    BuiltinTypes.construct(net.multigesture.kanama.binding.runtime.VariantType.ARRAY, it)
  },
  var writeScriptPropertyList: (MemorySegment) -> Unit = {
    BuiltinTypes.construct(net.multigesture.kanama.binding.runtime.VariantType.ARRAY, it)
  },
  var writeScriptSignalList: (MemorySegment) -> Unit = {
    BuiltinTypes.construct(net.multigesture.kanama.binding.runtime.VariantType.ARRAY, it)
  },
  var writeRpcConfig: (MemorySegment) -> Unit = { BuiltinTypes.initNilVariant(it) },
  var writeMethodInfo: (Long, MemorySegment) -> Boolean = { _, _ -> false },
  var hasPropertyDefault: (Long) -> Boolean = { false },
  var writePropertyDefault: (Long, MemorySegment) -> Boolean = { _, _ -> false },
  var sourceCode: String = "",
  /**
   * Pre-built static property list (from KSP registrar) so the placeholder instance used in editor
   * mode can expose the script's properties to the inspector without invoking the user's factory.
   */
  var propertyListPtr: MemorySegment = MemorySegment.NULL,
  var propertyCount: Int = 0,
) {
  private val liveOwnerObjectAddresses = LinkedHashSet<Long>()
  private val liveOwnerLock = Any()

  fun trackOwnerObject(ownerObjectAddress: Long) {
    synchronized(liveOwnerLock) { liveOwnerObjectAddresses.add(ownerObjectAddress) }
  }

  fun untrackOwnerObject(ownerObjectAddress: Long) {
    synchronized(liveOwnerLock) { liveOwnerObjectAddresses.remove(ownerObjectAddress) }
  }

  fun hasOwnerObject(ownerObjectAddress: Long): Boolean =
    synchronized(liveOwnerLock) { liveOwnerObjectAddresses.contains(ownerObjectAddress) }

  fun liveOwnerObjectAddressesSnapshot(): List<Long> =
    synchronized(liveOwnerLock) { liveOwnerObjectAddresses.toList() }

  companion object Registrar {

    private lateinit var cls: ClassDB.RegisteredClass
    private val constructedScriptObjects = LinkedHashSet<Long>()
    private val scriptCatalog = LinkedHashMap<Long, KanamaScriptMeta>()
    private val objectAddressByHandle = LinkedHashMap<Long, Long>()
    private val handleByObjectAddress = LinkedHashMap<Long, Long>()
    private val templatesByName = LinkedHashMap<String, KanamaScriptTemplate>()
    private val globalNameToTemplate = LinkedHashMap<String, KanamaScriptTemplate>()

    // Owners currently being programmatically created (by [instantiateResourceScript]
    // driving Object.set_script), keyed by owner Object* address → nesting depth.
    // Godot runs _instance_create synchronously on the calling thread, so a thread-local
    // reliably scopes the whole attach. When an owner is present, callInstanceCreate
    // builds the real instance even in the editor for a non-@Tool script, so that
    // programmatic creation returns a live Kotlin object rather than a placeholder.
    //
    // Keyed by owner (not a thread-wide flag) so a reentrant instantiation of a
    // *different* script during the attach — e.g. a scene load triggered from a resource
    // constructor — is NOT force-realized; only the specific owner under construction is.
    // A depth count (not a boolean) so a script whose constructor legitimately re-enters
    // for the same owner still survives the nesting.
    private val programmaticCreateOwners = ThreadLocal.withInitial { HashMap<Long, Int>() }

    private inline fun <R> withProgrammaticCreate(ownerAddress: Long, body: () -> R): R {
      val owners = programmaticCreateOwners.get()
      owners[ownerAddress] = (owners[ownerAddress] ?: 0) + 1
      try {
        return body()
      } finally {
        val depth = (owners[ownerAddress] ?: 1) - 1
        if (depth <= 0) owners.remove(ownerAddress) else owners[ownerAddress] = depth
      }
    }

    private fun isProgrammaticCreateOwner(ownerAddress: Long): Boolean =
      (programmaticCreateOwners.get()[ownerAddress] ?: 0) > 0

    data class KanamaScriptTemplate(
      val instanceBaseType: String,
      val isTool: Boolean,
      val kotlinClassName: String,
      val globalName: String,
      val factory: (MemorySegment) -> KanamaScriptInstance,
      val hasMethod: (Long) -> Boolean,
      val getMethodArgumentCount: (Long) -> Int,
      val hasScriptSignal: (Long) -> Boolean,
      val writeScriptMethodList: (MemorySegment) -> Unit,
      val writeScriptPropertyList: (MemorySegment) -> Unit,
      val writeScriptSignalList: (MemorySegment) -> Unit,
      val writeRpcConfig: (MemorySegment) -> Unit,
      val writeMethodInfo: (Long, MemorySegment) -> Boolean,
      val hasPropertyDefault: (Long) -> Boolean,
      val writePropertyDefault: (Long, MemorySegment) -> Boolean,
      val propertyListPtr: MemorySegment = MemorySegment.NULL,
      val propertyCount: Int = 0,
    )

    data class KanamaScriptMeta(
      val objectAddress: Long,
      val instanceBaseType: String,
      val kotlinClassName: String,
      val globalName: String,
      val isTool: Boolean,
    )

    data class ScriptTemplateSnapshot(
      val templatesByName: Map<String, KanamaScriptTemplate>,
      val globalNameToTemplate: Map<String, KanamaScriptTemplate>,
    )

    // Pre-interned virtual name storage values for fast dispatch.
    private var isValidNameValue: Long = 0L
    private var editorCanReloadFromFileNameValue: Long = 0L
    private var canInstantiateNameValue: Long = 0L
    private var getBaseScriptNameValue: Long = 0L
    private var getLanguageNameValue: Long = 0L
    private var getInstanceBaseTypeNameValue: Long = 0L
    private var getGlobalNameNameValue: Long = 0L
    private var inheritsScriptNameValue: Long = 0L
    private var instanceHasNameValue: Long = 0L
    private var hasSourceCodeNameValue: Long = 0L
    private var getSourceCodeNameValue: Long = 0L
    private var setSourceCodeNameValue: Long = 0L
    private var reloadNameValue: Long = 0L
    private var placeholderInstanceCreateNameValue: Long = 0L
    private var getScriptMethodListNameValue: Long = 0L
    private var getScriptPropertyListNameValue: Long = 0L
    private var getConstantsNameValue: Long = 0L
    private var getMembersNameValue: Long = 0L
    private var isToolNameValue: Long = 0L
    private var isAbstractNameValue: Long = 0L
    private var isPlaceholderFallbackEnabledNameValue: Long = 0L
    private var instanceCreateNameValue: Long = 0L
    private var getDocClassNameNameValue: Long = 0L
    private var getDocumentationNameValue: Long = 0L
    private var getClassIconPathNameValue: Long = 0L
    private var hasMethodNameValue: Long = 0L
    private var hasStaticMethodNameValue: Long = 0L
    private var getScriptMethodArgumentCountNameValue: Long = 0L
    private var getMethodInfoNameValue: Long = 0L
    private var hasScriptSignalNameValue: Long = 0L
    private var getScriptSignalListNameValue: Long = 0L
    private var getRpcConfigNameValue: Long = 0L
    private var getMemberLineNameValue: Long = 0L
    private var hasPropertyDefaultValueNameValue: Long = 0L
    private var getPropertyDefaultValueNameValue: Long = 0L
    private var updateExportsNameValue: Long = 0L

    // Upcall stubs for each virtual — built once during register().
    private lateinit var isValidStub: MemorySegment
    private lateinit var editorCanReloadFromFileStub: MemorySegment
    private lateinit var canInstantiateStub: MemorySegment
    private lateinit var getBaseScriptStub: MemorySegment
    private lateinit var getLanguageStub: MemorySegment
    private lateinit var getInstanceBaseTypeStub: MemorySegment
    private lateinit var getGlobalNameStub: MemorySegment
    private lateinit var inheritsScriptStub: MemorySegment
    private lateinit var instanceHasStub: MemorySegment
    private lateinit var hasSourceCodeStub: MemorySegment
    private lateinit var getSourceCodeStub: MemorySegment
    private lateinit var setSourceCodeStub: MemorySegment
    private lateinit var reloadStub: MemorySegment
    private lateinit var placeholderInstanceCreateStub: MemorySegment
    private lateinit var getScriptMethodListStub: MemorySegment
    private lateinit var getScriptPropertyListStub: MemorySegment
    private lateinit var getConstantsStub: MemorySegment
    private lateinit var getMembersStub: MemorySegment
    private lateinit var instanceCreateStub: MemorySegment
    private lateinit var isToolStub: MemorySegment
    private lateinit var boolTrueStub: MemorySegment
    private lateinit var boolFalseStub:
      MemorySegment // shared by is_tool / is_abstract / is_placeholder_fallback_enabled
    private lateinit var getDocClassNameStub: MemorySegment
    private lateinit var emptyArrayStub: MemorySegment
    private lateinit var emptyDictionaryStub: MemorySegment
    private lateinit var emptyStringStub: MemorySegment
    private lateinit var nilVariantStub: MemorySegment
    private lateinit var getMemberLineStub: MemorySegment
    private lateinit var hasMethodStub: MemorySegment
    private lateinit var hasStaticMethodStub: MemorySegment
    private lateinit var getScriptMethodArgumentCountStub: MemorySegment
    private lateinit var hasScriptSignalStub: MemorySegment
    private lateinit var getMethodInfoStub: MemorySegment
    private lateinit var getScriptSignalListStub: MemorySegment
    private lateinit var getRpcConfigStub: MemorySegment
    private lateinit var hasPropertyDefaultValueStub: MemorySegment
    private lateinit var getPropertyDefaultValueStub: MemorySegment
    private lateinit var updateExportsStub: MemorySegment
    private var instanceHasTraceCount: Int = 0
    private const val INSTANCE_HAS_TRACE_LIMIT: Int = 40

    fun register(library: MemorySegment) {
      // Intern virtual names.
      isValidNameValue = GodotStrings.stringNameStorage("_is_valid")
      editorCanReloadFromFileNameValue =
        GodotStrings.stringNameStorage("_editor_can_reload_from_file")
      canInstantiateNameValue = GodotStrings.stringNameStorage("_can_instantiate")
      getBaseScriptNameValue = GodotStrings.stringNameStorage("_get_base_script")
      getLanguageNameValue = GodotStrings.stringNameStorage("_get_language")
      getInstanceBaseTypeNameValue = GodotStrings.stringNameStorage("_get_instance_base_type")
      getGlobalNameNameValue = GodotStrings.stringNameStorage("_get_global_name")
      inheritsScriptNameValue = GodotStrings.stringNameStorage("_inherits_script")
      instanceHasNameValue = GodotStrings.stringNameStorage("_instance_has")
      hasSourceCodeNameValue = GodotStrings.stringNameStorage("_has_source_code")
      getSourceCodeNameValue = GodotStrings.stringNameStorage("_get_source_code")
      setSourceCodeNameValue = GodotStrings.stringNameStorage("_set_source_code")
      reloadNameValue = GodotStrings.stringNameStorage("_reload")
      placeholderInstanceCreateNameValue =
        GodotStrings.stringNameStorage("_placeholder_instance_create")
      getScriptMethodListNameValue = GodotStrings.stringNameStorage("_get_script_method_list")
      getScriptPropertyListNameValue = GodotStrings.stringNameStorage("_get_script_property_list")
      getConstantsNameValue = GodotStrings.stringNameStorage("_get_constants")
      getMembersNameValue = GodotStrings.stringNameStorage("_get_members")
      isToolNameValue = GodotStrings.stringNameStorage("_is_tool")
      isAbstractNameValue = GodotStrings.stringNameStorage("_is_abstract")
      isPlaceholderFallbackEnabledNameValue =
        GodotStrings.stringNameStorage("_is_placeholder_fallback_enabled")
      getDocClassNameNameValue = GodotStrings.stringNameStorage("_get_doc_class_name")
      getDocumentationNameValue = GodotStrings.stringNameStorage("_get_documentation")
      getClassIconPathNameValue = GodotStrings.stringNameStorage("_get_class_icon_path")
      hasMethodNameValue = GodotStrings.stringNameStorage("_has_method")
      hasStaticMethodNameValue = GodotStrings.stringNameStorage("_has_static_method")
      getScriptMethodArgumentCountNameValue =
        GodotStrings.stringNameStorage("_get_script_method_argument_count")
      getMethodInfoNameValue = GodotStrings.stringNameStorage("_get_method_info")
      hasScriptSignalNameValue = GodotStrings.stringNameStorage("_has_script_signal")
      getScriptSignalListNameValue = GodotStrings.stringNameStorage("_get_script_signal_list")
      getRpcConfigNameValue = GodotStrings.stringNameStorage("_get_rpc_config")
      getMemberLineNameValue = GodotStrings.stringNameStorage("_get_member_line")
      hasPropertyDefaultValueNameValue =
        GodotStrings.stringNameStorage("_has_property_default_value")
      getPropertyDefaultValueNameValue =
        GodotStrings.stringNameStorage("_get_property_default_value")
      updateExportsNameValue = GodotStrings.stringNameStorage("_update_exports")

      val virtualType =
        MethodType.methodType(
          Void.TYPE,
          MemorySegment::class.java,
          MemorySegment::class.java,
          MemorySegment::class.java,
        )
      val virtualDesc = FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS)

      isValidStub = Upcalls.stub(KanamaScript::class.java, "callIsValid", virtualType, virtualDesc)
      editorCanReloadFromFileStub =
        Upcalls.stub(KanamaScript::class.java, "callBoolFalse", virtualType, virtualDesc)
      canInstantiateStub =
        Upcalls.stub(KanamaScript::class.java, "callCanInstantiate", virtualType, virtualDesc)
      getBaseScriptStub =
        Upcalls.stub(KanamaScript::class.java, "callGetBaseScript", virtualType, virtualDesc)
      getLanguageStub =
        Upcalls.stub(KanamaScript::class.java, "callGetLanguage", virtualType, virtualDesc)
      getInstanceBaseTypeStub =
        Upcalls.stub(KanamaScript::class.java, "callGetInstanceBaseType", virtualType, virtualDesc)
      getGlobalNameStub =
        Upcalls.stub(KanamaScript::class.java, "callGetGlobalName", virtualType, virtualDesc)
      inheritsScriptStub =
        Upcalls.stub(KanamaScript::class.java, "callBoolFalse", virtualType, virtualDesc)
      instanceHasStub =
        Upcalls.stub(KanamaScript::class.java, "callInstanceHas", virtualType, virtualDesc)
      hasSourceCodeStub =
        Upcalls.stub(KanamaScript::class.java, "callHasSourceCode", virtualType, virtualDesc)
      getSourceCodeStub =
        Upcalls.stub(KanamaScript::class.java, "callGetSourceCode", virtualType, virtualDesc)
      setSourceCodeStub =
        Upcalls.stub(KanamaScript::class.java, "callSetSourceCode", virtualType, virtualDesc)
      reloadStub = Upcalls.stub(KanamaScript::class.java, "callReload", virtualType, virtualDesc)
      placeholderInstanceCreateStub =
        Upcalls.stub(
          KanamaScript::class.java,
          "callPlaceholderInstanceCreate",
          virtualType,
          virtualDesc,
        )
      getScriptMethodListStub =
        Upcalls.stub(KanamaScript::class.java, "callGetScriptMethodList", virtualType, virtualDesc)
      getScriptPropertyListStub =
        Upcalls.stub(
          KanamaScript::class.java,
          "callGetScriptPropertyList",
          virtualType,
          virtualDesc,
        )
      getConstantsStub =
        Upcalls.stub(KanamaScript::class.java, "callGetConstants", virtualType, virtualDesc)
      getMembersStub =
        Upcalls.stub(KanamaScript::class.java, "callGetMembers", virtualType, virtualDesc)
      isToolStub = Upcalls.stub(KanamaScript::class.java, "callIsTool", virtualType, virtualDesc)
      boolTrueStub =
        Upcalls.stub(KanamaScript::class.java, "callBoolTrue", virtualType, virtualDesc)
      boolFalseStub =
        Upcalls.stub(KanamaScript::class.java, "callBoolFalse", virtualType, virtualDesc)
      getDocClassNameStub =
        Upcalls.stub(KanamaScript::class.java, "callGetDocClassName", virtualType, virtualDesc)
      emptyArrayStub =
        Upcalls.stub(KanamaScript::class.java, "callEmptyArray", virtualType, virtualDesc)
      emptyDictionaryStub =
        Upcalls.stub(KanamaScript::class.java, "callEmptyDictionary", virtualType, virtualDesc)
      emptyStringStub =
        Upcalls.stub(KanamaScript::class.java, "callEmptyString", virtualType, virtualDesc)
      nilVariantStub =
        Upcalls.stub(KanamaScript::class.java, "callNilVariant", virtualType, virtualDesc)
      getMemberLineStub =
        Upcalls.stub(KanamaScript::class.java, "callGetMemberLine", virtualType, virtualDesc)
      hasMethodStub =
        Upcalls.stub(KanamaScript::class.java, "callHasMethod", virtualType, virtualDesc)
      hasStaticMethodStub =
        Upcalls.stub(KanamaScript::class.java, "callHasStaticMethod", virtualType, virtualDesc)
      getScriptMethodArgumentCountStub =
        Upcalls.stub(
          KanamaScript::class.java,
          "callGetScriptMethodArgumentCount",
          virtualType,
          virtualDesc,
        )
      hasScriptSignalStub =
        Upcalls.stub(KanamaScript::class.java, "callHasScriptSignal", virtualType, virtualDesc)
      getMethodInfoStub =
        Upcalls.stub(KanamaScript::class.java, "callGetMethodInfo", virtualType, virtualDesc)
      getScriptSignalListStub =
        Upcalls.stub(KanamaScript::class.java, "callGetScriptSignalList", virtualType, virtualDesc)
      getRpcConfigStub =
        Upcalls.stub(KanamaScript::class.java, "callGetRpcConfig", virtualType, virtualDesc)
      hasPropertyDefaultValueStub =
        Upcalls.stub(
          KanamaScript::class.java,
          "callHasPropertyDefaultValue",
          virtualType,
          virtualDesc,
        )
      getPropertyDefaultValueStub =
        Upcalls.stub(
          KanamaScript::class.java,
          "callGetPropertyDefaultValue",
          virtualType,
          virtualDesc,
        )
      updateExportsStub =
        Upcalls.stub(KanamaScript::class.java, "callUpdateExports", virtualType, virtualDesc)

      // _instance_create(for_object: Object) -> void*
      instanceCreateNameValue = GodotStrings.stringNameStorage("_instance_create")
      instanceCreateStub =
        Upcalls.stub(KanamaScript::class.java, "callInstanceCreate", virtualType, virtualDesc)

      val createStub =
        Upcalls.stub(
          KanamaScript::class.java,
          "createInstance",
          MethodType.methodType(
            MemorySegment::class.java,
            MemorySegment::class.java,
            java.lang.Byte.TYPE,
          ),
          FunctionDescriptor.of(ADDRESS, ADDRESS, JAVA_BYTE),
        )
      val freeStub =
        Upcalls.stub(
          KanamaScript::class.java,
          "freeInstance",
          MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java),
          FunctionDescriptor.ofVoid(ADDRESS, ADDRESS),
        )
      val getVirtualStub =
        Upcalls.stub(
          KanamaScript::class.java,
          "getVirtual",
          MethodType.methodType(
            MemorySegment::class.java,
            MemorySegment::class.java,
            MemorySegment::class.java,
            java.lang.Integer.TYPE,
          ),
          FunctionDescriptor.of(ADDRESS, ADDRESS, ADDRESS, JAVA_INT),
        )

      cls =
        ClassDB.registerClass(
          library,
          ClassDB.ClassSpec(
            name = "KanamaScript",
            parentName = "ScriptExtension",
            isTool = true,
            createInstance = createStub,
            freeInstance = freeStub,
            getVirtual = getVirtualStub,
          ),
        )
      System.err.println("[kanama:kt] registered KanamaScript : ScriptExtension")
    }

    /** Construct a new KanamaScript Godot object for the given Kotlin class. */
    fun construct(
      instanceBaseType: String,
      isTool: Boolean = false,
      kotlinClassName: String,
      globalName: String = "",
      factory: (MemorySegment) -> KanamaScriptInstance,
      hasMethod: (Long) -> Boolean = { false },
      getMethodArgumentCount: (Long) -> Int = { -1 },
      hasScriptSignal: (Long) -> Boolean = { false },
      writeScriptMethodList: (MemorySegment) -> Unit = {
        BuiltinTypes.construct(net.multigesture.kanama.binding.runtime.VariantType.ARRAY, it)
      },
      writeScriptPropertyList: (MemorySegment) -> Unit = {
        BuiltinTypes.construct(net.multigesture.kanama.binding.runtime.VariantType.ARRAY, it)
      },
      writeScriptSignalList: (MemorySegment) -> Unit = {
        BuiltinTypes.construct(net.multigesture.kanama.binding.runtime.VariantType.ARRAY, it)
      },
      writeRpcConfig: (MemorySegment) -> Unit = { BuiltinTypes.initNilVariant(it) },
      writeMethodInfo: (Long, MemorySegment) -> Boolean = { _, _ -> false },
      hasPropertyDefault: (Long) -> Boolean = { false },
      writePropertyDefault: (Long, MemorySegment) -> Boolean = { _, _ -> false },
      propertyListPtr: MemorySegment = MemorySegment.NULL,
      propertyCount: Int = 0,
    ): MemorySegment {
      val constructObject =
        GodotFFI.lookup("classdb_construct_object2", FunctionDescriptor.of(ADDRESS, ADDRESS))
      val objectSetInstance =
        GodotFFI.lookup("object_set_instance", FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS))
      val obj = constructObject.invoke(cls.className) as MemorySegment
      val kotlinInstance =
        KanamaScript(
          obj,
          instanceBaseType,
          isTool,
          kotlinClassName,
          globalName,
          factory,
          hasMethod,
          getMethodArgumentCount,
          hasScriptSignal,
          writeScriptMethodList,
          writeScriptPropertyList,
          writeScriptSignalList,
          writeRpcConfig,
          writeMethodInfo,
          hasPropertyDefault,
          writePropertyDefault,
          propertyListPtr = propertyListPtr,
          propertyCount = propertyCount,
        )
      val handle = ObjectRegistry.register(kotlinInstance)
      objectSetInstance.invoke(obj, cls.className, MemorySegment.ofAddress(handle))
      ObjectCalls.notifyPostinitialize(obj)
      synchronized(constructedScriptObjects) { constructedScriptObjects.add(obj.address()) }
      synchronized(objectAddressByHandle) {
        objectAddressByHandle[handle] = obj.address()
        handleByObjectAddress[obj.address()] = handle
      }
      registerTemplate(
        instanceBaseType = instanceBaseType,
        isTool = isTool,
        kotlinClassName = kotlinClassName,
        globalName = globalName,
        factory = factory,
        hasMethod = hasMethod,
        getMethodArgumentCount = getMethodArgumentCount,
        hasScriptSignal = hasScriptSignal,
        writeScriptMethodList = writeScriptMethodList,
        writeScriptPropertyList = writeScriptPropertyList,
        writeScriptSignalList = writeScriptSignalList,
        writeRpcConfig = writeRpcConfig,
        writeMethodInfo = writeMethodInfo,
        hasPropertyDefault = hasPropertyDefault,
        writePropertyDefault = writePropertyDefault,
        propertyListPtr = propertyListPtr,
        propertyCount = propertyCount,
      )
      synchronized(scriptCatalog) {
        scriptCatalog[obj.address()] =
          KanamaScriptMeta(
            objectAddress = obj.address(),
            instanceBaseType = instanceBaseType,
            kotlinClassName = kotlinClassName,
            globalName = globalName,
            isTool = isTool,
          )
      }
      System.err.println(
        "[kanama:kt] KanamaScript.construct kotlinClass=$kotlinClassName " +
          "globalName=$globalName handle=$handle"
      )
      return obj
    }

    fun constructUnbound(): MemorySegment {
      val constructObject =
        GodotFFI.lookup("classdb_construct_object2", FunctionDescriptor.of(ADDRESS, ADDRESS))
      val objectSetInstance =
        GodotFFI.lookup("object_set_instance", FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS))
      val obj = constructObject.invoke(cls.className) as MemorySegment
      val kotlinInstance =
        KanamaScript(
          godotObject = obj,
          instanceBaseType = "Node",
          isTool = false,
          kotlinClassName = "",
          globalName = "",
          factory = null,
          hasMethod = { false },
          getMethodArgumentCount = { -1 },
          hasScriptSignal = { false },
          writeScriptMethodList = {
            BuiltinTypes.construct(net.multigesture.kanama.binding.runtime.VariantType.ARRAY, it)
          },
          writeScriptPropertyList = {
            BuiltinTypes.construct(net.multigesture.kanama.binding.runtime.VariantType.ARRAY, it)
          },
          writeScriptSignalList = {
            BuiltinTypes.construct(net.multigesture.kanama.binding.runtime.VariantType.ARRAY, it)
          },
          writeRpcConfig = { BuiltinTypes.initNilVariant(it) },
          writeMethodInfo = { _, _ -> false },
          hasPropertyDefault = { false },
          writePropertyDefault = { _, _ -> false },
          sourceCode = "",
        )
      val handle = ObjectRegistry.register(kotlinInstance)
      objectSetInstance.invoke(obj, cls.className, MemorySegment.ofAddress(handle))
      ObjectCalls.notifyPostinitialize(obj)
      synchronized(constructedScriptObjects) { constructedScriptObjects.add(obj.address()) }
      synchronized(objectAddressByHandle) {
        objectAddressByHandle[handle] = obj.address()
        handleByObjectAddress[obj.address()] = handle
      }
      synchronized(scriptCatalog) {
        scriptCatalog[obj.address()] =
          KanamaScriptMeta(
            objectAddress = obj.address(),
            instanceBaseType = kotlinInstance.instanceBaseType,
            kotlinClassName = kotlinInstance.kotlinClassName,
            globalName = kotlinInstance.globalName,
            isTool = kotlinInstance.isTool,
          )
      }
      return obj
    }

    private fun registerTemplate(
      instanceBaseType: String,
      isTool: Boolean,
      kotlinClassName: String,
      globalName: String,
      factory: (MemorySegment) -> KanamaScriptInstance,
      hasMethod: (Long) -> Boolean,
      getMethodArgumentCount: (Long) -> Int,
      hasScriptSignal: (Long) -> Boolean,
      writeScriptMethodList: (MemorySegment) -> Unit,
      writeScriptPropertyList: (MemorySegment) -> Unit,
      writeScriptSignalList: (MemorySegment) -> Unit,
      writeRpcConfig: (MemorySegment) -> Unit,
      writeMethodInfo: (Long, MemorySegment) -> Boolean,
      hasPropertyDefault: (Long) -> Boolean,
      writePropertyDefault: (Long, MemorySegment) -> Boolean,
      propertyListPtr: MemorySegment = MemorySegment.NULL,
      propertyCount: Int = 0,
    ) {
      val simpleName = kotlinClassName.substringAfterLast('.')
      val template =
        KanamaScriptTemplate(
          instanceBaseType = instanceBaseType,
          isTool = isTool,
          kotlinClassName = kotlinClassName,
          globalName = globalName,
          factory = factory,
          hasMethod = hasMethod,
          getMethodArgumentCount = getMethodArgumentCount,
          hasScriptSignal = hasScriptSignal,
          writeScriptMethodList = writeScriptMethodList,
          writeScriptPropertyList = writeScriptPropertyList,
          writeScriptSignalList = writeScriptSignalList,
          writeRpcConfig = writeRpcConfig,
          writeMethodInfo = writeMethodInfo,
          hasPropertyDefault = hasPropertyDefault,
          writePropertyDefault = writePropertyDefault,
          propertyListPtr = propertyListPtr,
          propertyCount = propertyCount,
        )
      synchronized(templatesByName) {
        templatesByName[simpleName] = template
        templatesByName[kotlinClassName] = template
        if (globalName.isNotEmpty()) {
          templatesByName[globalName] = template
          globalNameToTemplate[globalName] = template
        }
      }
    }

    fun bindScriptToTemplate(script: KanamaScript, classOrGlobalName: String): Boolean {
      val template =
        synchronized(templatesByName) { templatesByName[classOrGlobalName] } ?: return false
      script.instanceBaseType = template.instanceBaseType
      script.isTool = template.isTool
      script.kotlinClassName = template.kotlinClassName
      script.globalName = template.globalName
      script.factory = template.factory
      script.hasMethod = template.hasMethod
      script.getMethodArgumentCount = template.getMethodArgumentCount
      script.hasScriptSignal = template.hasScriptSignal
      script.writeScriptMethodList = template.writeScriptMethodList
      script.writeScriptPropertyList = template.writeScriptPropertyList
      script.writeScriptSignalList = template.writeScriptSignalList
      script.writeRpcConfig = template.writeRpcConfig
      script.writeMethodInfo = template.writeMethodInfo
      script.hasPropertyDefault = template.hasPropertyDefault
      script.writePropertyDefault = template.writePropertyDefault
      script.propertyListPtr = template.propertyListPtr
      script.propertyCount = template.propertyCount
      synchronized(scriptCatalog) {
        scriptCatalog[script.godotObject.address()] =
          KanamaScriptMeta(
            objectAddress = script.godotObject.address(),
            instanceBaseType = script.instanceBaseType,
            kotlinClassName = script.kotlinClassName,
            globalName = script.globalName,
            isTool = script.isTool,
          )
      }
      return true
    }

    fun bindScriptFromSource(script: KanamaScript, source: String): Boolean {
      val className = parseClassName(source) ?: return false
      return bindScriptToTemplate(script, className)
    }

    /**
     * Programmatically creates a brand-new script-backed resource from Kotlin.
     *
     * Backs the public [net.multigesture.kanama.api.newScriptInstance] helper. Constructs a fresh
     * engine `Resource`, attaches the Kanama script registered for [fqName]/[simpleName], and
     * returns the live Kotlin object. The resource is left with one owning reference (GDScript
     * `.new()` parity) so it survives a later `ResourceSaver.save` which wraps it in a transient
     * `Ref<>`.
     *
     * Currently restricted to `@ScriptClass(attachTo = "Resource")` classes.
     */
    fun instantiateResourceScript(
      fqName: String?,
      simpleName: String?,
    ): Pair<Any, net.multigesture.kanama.api.Resource> {
      val requested = fqName ?: simpleName ?: "<unknown>"
      val template =
        resolveTemplate(fqName, simpleName)
          ?: error(
            "newScriptInstance: '$requested' is not a registered @ScriptClass. " +
              "Annotate it with @ScriptClass(attachTo = \"Resource\") and name the file <ClassName>.kt."
          )
      require(template.instanceBaseType == "Resource") {
        "newScriptInstance: '${template.kotlinClassName}' attaches to '${template.instanceBaseType}', " +
          "but programmatic creation currently supports only @ScriptClass(attachTo = \"Resource\")."
      }
      val resolved =
        resolveScriptResource(template)
          ?: error(
            "newScriptInstance: could not resolve the script resource for '${template.kotlinClassName}'. " +
              "Mark it @GlobalClass and name the file after the class so its res:// path is discoverable."
          )
      val baseHandle = ObjectCalls.constructObject("Resource")
      // Own the fresh resource immediately (before attach) so the +1 is released on any error
      // path, not just success — mirrors the task-43 owned-return convention. Balanced by the
      // returned Resource's close() on the happy path, or releaseHandle in finally otherwise.
      net.multigesture.kanama.api.RefCounted.retainHandle(baseHandle)
      var success = false
      try {
        withProgrammaticCreate(baseHandle.address()) {
          net.multigesture.kanama.api.GodotObject(baseHandle).setScript(resolved.script)
        }
        val instance =
          ScriptBridge.kotlinObjectForOwner(baseHandle)
            ?: error(
              "newScriptInstance: no script instance was created for '${template.kotlinClassName}'. " +
                "The script may have failed to attach."
            )
        success = true
        return instance to net.multigesture.kanama.api.Resource.fromHandle(baseHandle)
      } finally {
        // The loaded script wrapper is our transient +1 — the base resource holds its own
        // reference via setScript, so release ours (the borrowed fallback stays untouched).
        if (resolved.owned) resolved.script.close()
        // On failure, drop the owning +1 we took above so a bad construction never leaks.
        if (!success) net.multigesture.kanama.api.RefCounted.releaseHandle(baseHandle)
      }
    }

    private fun resolveTemplate(fqName: String?, simpleName: String?): KanamaScriptTemplate? =
      synchronized(templatesByName) {
        fqName?.let { templatesByName[it] } ?: simpleName?.let { templatesByName[it] }
      }

    /**
     * Resolves the `Script` resource to attach for [template]. Prefers loading the on-disk
     * `res://<Class>.kt` (via its global-class registry path) so the saved resource references the
     * script as an `ExtResource` and reloads correctly; falls back to the registration-time script
     * object when no path is known.
     */
    /**
     * A resolved script resource plus whether the caller owns it. The `ResourceLoader.load` path
     * returns a `+1` owning wrapper that must be closed once attached; the registration fallback is
     * a borrowed `fromHandle` view that must NOT be closed.
     */
    private class ResolvedScript(
      val script: net.multigesture.kanama.api.Resource,
      val owned: Boolean,
    )

    private fun resolveScriptResource(template: KanamaScriptTemplate): ResolvedScript? {
      if (template.globalName.isNotEmpty()) {
        globalClassPath(template.globalName)?.let { path ->
          net.multigesture.kanama.api.ResourceLoader.load(path)?.let {
            return ResolvedScript(it, owned = true)
          }
        }
      }
      return registeredScriptObjectFor(template)?.let {
        ResolvedScript(net.multigesture.kanama.api.Resource.fromHandle(it), owned = false)
      }
    }

    // Match the global-class entry by its res:// path rather than the "class" key:
    // dictionary StringName values (the "class"/"base" fields) currently decode to
    // null, while the "path" String decodes cleanly. A @GlobalClass script's file is
    // required to be named after the class (<GlobalName>.kt), so the path suffix is a
    // reliable, unique key.
    private fun globalClassPath(globalName: String): String? {
      val rootPath = "res://$globalName.kt"
      val fileSuffix = "/$globalName.kt"
      return net.multigesture.kanama.api.ProjectSettings.getGlobalClassList()
        .mapNotNull { it["path"] as? String }
        .firstOrNull { it == rootPath || it.endsWith(fileSuffix) }
    }

    private fun registeredScriptObjectFor(template: KanamaScriptTemplate): MemorySegment? =
      synchronized(scriptCatalog) {
        scriptCatalog.values
          .firstOrNull { meta ->
            meta.kotlinClassName == template.kotlinClassName ||
              (template.globalName.isNotEmpty() && meta.globalName == template.globalName)
          }
          ?.let { MemorySegment.ofAddress(it.objectAddress) }
      }

    fun clearScriptTemplates() {
      synchronized(templatesByName) {
        templatesByName.clear()
        globalNameToTemplate.clear()
      }
    }

    fun snapshotScriptTemplates(): ScriptTemplateSnapshot =
      synchronized(templatesByName) {
        ScriptTemplateSnapshot(
          templatesByName = LinkedHashMap(templatesByName),
          globalNameToTemplate = LinkedHashMap(globalNameToTemplate),
        )
      }

    fun restoreScriptTemplates(snapshot: ScriptTemplateSnapshot) {
      synchronized(templatesByName) {
        templatesByName.clear()
        templatesByName.putAll(snapshot.templatesByName)
        globalNameToTemplate.clear()
        globalNameToTemplate.putAll(snapshot.globalNameToTemplate)
      }
    }

    fun rebindAllScripts(): Int {
      val scriptAddresses = synchronized(scriptCatalog) { scriptCatalog.keys.toList() }
      var rebound = 0
      scriptAddresses.forEach { objectAddress ->
        val script = byObjectAddress(objectAddress) ?: return@forEach
        val keys = LinkedHashSet<String>()
        if (script.globalName.isNotEmpty()) keys += script.globalName
        if (script.kotlinClassName.isNotEmpty()) {
          keys += script.kotlinClassName
          keys += script.kotlinClassName.substringAfterLast('.')
        }
        parseClassName(script.sourceCode)?.let { keys += it }
        val bound = keys.any { bindScriptToTemplate(script, it) }
        if (bound) rebound += 1
      }
      return rebound
    }

    fun byObjectAddress(objectAddress: Long): KanamaScript? {
      val handle =
        synchronized(objectAddressByHandle) { handleByObjectAddress[objectAddress] } ?: return null
      return ObjectRegistry.get(handle) as? KanamaScript
    }

    private val classRegex = Regex("""\bclass\s+([A-Za-z_][A-Za-z0-9_]*)""")

    private fun parseClassName(source: String): String? =
      classRegex.find(source)?.groupValues?.getOrNull(1)

    fun hasNamedClasses(): Boolean =
      synchronized(scriptCatalog) { scriptCatalog.values.any { it.globalName.isNotEmpty() } }

    fun metaByGlobalName(type: String): KanamaScriptMeta? =
      synchronized(scriptCatalog) { scriptCatalog.values.firstOrNull { it.globalName == type } }

    fun firstScriptObject(): MemorySegment =
      synchronized(scriptCatalog) {
        MemorySegment.ofAddress(scriptCatalog.keys.firstOrNull() ?: 0L)
      }

    fun inferGlobalClassNameFromPath(path: String): String? =
      synchronized(scriptCatalog) {
        val normalized = path.replace('\\', '/')
        scriptCatalog.values
          .firstOrNull { meta ->
            meta.globalName.isNotEmpty() && normalized.endsWith("/${meta.globalName}.kt")
          }
          ?.globalName
      }

    /** Destroys all ScriptExtension objects we created via [construct]. */
    fun destroyConstructedScripts() {
      val toDestroy =
        synchronized(constructedScriptObjects) {
          constructedScriptObjects.toList().also { constructedScriptObjects.clear() }
        }
      // No zombie sweep needed here anymore: since the refcount_decremented fix,
      // a scripted RefCounted dies at its last unreference(), so a live owner can
      // never sit at ref_count=0 waiting for a shutdown destroy.
      synchronized(scriptCatalog) { scriptCatalog.clear() }
      synchronized(objectAddressByHandle) {
        objectAddressByHandle.clear()
        handleByObjectAddress.clear()
      }
      clearScriptTemplates()
      System.err.println("[kanama:kt] destroyConstructedScripts count=${toDestroy.size}")
      if (toDestroy.isNotEmpty()) {
        var destroyed = 0
        toDestroy.forEach { addr ->
          try {
            ObjectCalls.destroyObject(MemorySegment.ofAddress(addr))
            destroyed++
          } catch (t: Throwable) {
            System.err.println(
              "[kanama:kt] destroyConstructedScripts failed addr=0x${addr.toString(16)}: ${t.message}"
            )
          }
        }
        System.err.println(
          "[kanama:kt] destroyed $destroyed/${toDestroy.size} tracked KanamaScript object(s)"
        )
      }
    }

    // ---- Upcall stubs ----

    @JvmStatic
    fun createInstance(userdata: MemorySegment, notifyPostinitialize: Byte): MemorySegment {
      val constructObject =
        GodotFFI.lookup("classdb_construct_object2", FunctionDescriptor.of(ADDRESS, ADDRESS))
      val objectSetInstance =
        GodotFFI.lookup("object_set_instance", FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS))
      val obj = constructObject.invoke(cls.parentName) as MemorySegment
      // Create a blank KanamaScript — caller must configure class association.
      val kotlinInstance = KanamaScript(obj, "Node", false, "")
      val handle = ObjectRegistry.register(kotlinInstance)
      objectSetInstance.invoke(obj, cls.className, MemorySegment.ofAddress(handle))
      if (notifyPostinitialize != 0.toByte()) {
        ObjectCalls.notifyPostinitialize(obj)
      }
      synchronized(objectAddressByHandle) {
        objectAddressByHandle[handle] = obj.address()
        handleByObjectAddress[obj.address()] = handle
      }
      return obj
    }

    @JvmStatic
    fun freeInstance(userdata: MemorySegment, instance: MemorySegment) {
      val handle = instance.address()
      ObjectRegistry.unregister(handle)
      val objectAddress =
        synchronized(objectAddressByHandle) {
          val address = objectAddressByHandle.remove(handle)
          if (address != null) {
            handleByObjectAddress.remove(address)
          }
          address
        }
      if (objectAddress != null) {
        synchronized(scriptCatalog) { scriptCatalog.remove(objectAddress) }
        synchronized(constructedScriptObjects) { constructedScriptObjects.remove(objectAddress) }
      }
    }

    @JvmStatic
    fun getVirtual(userdata: MemorySegment, name: MemorySegment, hash: Int): MemorySegment {
      val v = name.reinterpret(8).get(JAVA_LONG, 0)
      return when (v) {
        isValidNameValue -> isValidStub
        editorCanReloadFromFileNameValue -> editorCanReloadFromFileStub
        canInstantiateNameValue -> canInstantiateStub
        getBaseScriptNameValue -> getBaseScriptStub
        getLanguageNameValue -> getLanguageStub
        getInstanceBaseTypeNameValue -> getInstanceBaseTypeStub
        getGlobalNameNameValue -> getGlobalNameStub
        inheritsScriptNameValue -> inheritsScriptStub
        instanceHasNameValue -> instanceHasStub
        hasSourceCodeNameValue -> hasSourceCodeStub
        getSourceCodeNameValue -> getSourceCodeStub
        setSourceCodeNameValue -> setSourceCodeStub
        reloadNameValue -> reloadStub
        placeholderInstanceCreateNameValue -> placeholderInstanceCreateStub
        getScriptMethodListNameValue -> getScriptMethodListStub
        getScriptPropertyListNameValue -> getScriptPropertyListStub
        getConstantsNameValue -> getConstantsStub
        getMembersNameValue -> getMembersStub
        instanceCreateNameValue -> instanceCreateStub
        getDocClassNameNameValue -> getDocClassNameStub
        getDocumentationNameValue -> emptyArrayStub
        getClassIconPathNameValue -> emptyStringStub
        hasMethodNameValue -> hasMethodStub
        hasStaticMethodNameValue -> hasStaticMethodStub
        hasScriptSignalNameValue -> hasScriptSignalStub
        hasPropertyDefaultValueNameValue -> hasPropertyDefaultValueStub
        getScriptMethodArgumentCountNameValue -> getScriptMethodArgumentCountStub
        getRpcConfigNameValue -> getRpcConfigStub
        getPropertyDefaultValueNameValue -> getPropertyDefaultValueStub
        updateExportsNameValue -> updateExportsStub
        getMethodInfoNameValue -> getMethodInfoStub
        getScriptSignalListNameValue -> getScriptSignalListStub
        getMemberLineNameValue -> getMemberLineStub
        isToolNameValue -> isToolStub
        isAbstractNameValue -> boolFalseStub
        isPlaceholderFallbackEnabledNameValue -> boolTrueStub
        else -> MemorySegment.NULL
      }
    }

    // ---- Virtual implementations ----

    @JvmStatic
    fun callIsValid(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      rRet.reinterpret(1).set(JAVA_BYTE, 0, 1.toByte())
    }

    @JvmStatic
    fun callCanInstantiate(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      val handle = instance.address()
      val script = ObjectRegistry.get(handle) as? KanamaScript
      val can = script?.factory != null
      rRet.reinterpret(1).set(JAVA_BYTE, 0, if (can) 1.toByte() else 0.toByte())
    }

    @JvmStatic
    fun callGetBaseScript(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      rRet.reinterpret(8).set(ADDRESS, 0, MemorySegment.NULL)
    }

    @JvmStatic
    fun callGetLanguage(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      // Return the KanamaScriptLanguage Godot object pointer.
      rRet.reinterpret(8).set(ADDRESS, 0, KanamaScriptLanguage.godotObject)
    }

    @JvmStatic
    fun callGetInstanceBaseType(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      val handle = instance.address()
      val script = ObjectRegistry.get(handle) as? KanamaScript
      val baseType = script?.instanceBaseType ?: "Node"
      GodotStrings.initStringName(rRet.reinterpret(8), baseType)
    }

    @JvmStatic
    fun callGetGlobalName(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      val handle = instance.address()
      val script = ObjectRegistry.get(handle) as? KanamaScript
      val name = script?.globalName ?: ""
      GodotStrings.initStringName(rRet.reinterpret(8), name)
    }

    @JvmStatic
    fun callHasSourceCode(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      val handle = instance.address()
      val script = ObjectRegistry.get(handle) as? KanamaScript
      rRet
        .reinterpret(1)
        .set(JAVA_BYTE, 0, if (!script?.sourceCode.isNullOrEmpty()) 1.toByte() else 0.toByte())
    }

    @JvmStatic
    fun callGetSourceCode(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      val handle = instance.address()
      val script = ObjectRegistry.get(handle) as? KanamaScript
      GodotStrings.initString(rRet.reinterpret(8), script?.sourceCode ?: "")
    }

    @JvmStatic
    fun callGetDocClassName(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      val handle = instance.address()
      val script = ObjectRegistry.get(handle) as? KanamaScript
      val className =
        script?.globalName?.takeIf { it.isNotEmpty() }
          ?: script?.kotlinClassName?.substringAfterLast('.')
          ?: ""
      GodotStrings.initStringName(rRet.reinterpret(8), className)
    }

    @JvmStatic
    fun callSetSourceCode(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      val handle = instance.address()
      val script = ObjectRegistry.get(handle) as? KanamaScript ?: return
      val argsArray = args.reinterpret(8)
      val sourcePtr = argsArray.get(ADDRESS, 0)
      val source = GodotStrings.readString(sourcePtr)
      script.sourceCode = source
      System.err.println("[kanama:kt] KanamaScript._set_source_code len=${source.length}")
      if (bindScriptFromSource(script, source)) {
        System.err.println("[kanama:kt] KanamaScript.bind from source -> ${script.kotlinClassName}")
      } else {
        System.err.println("[kanama:kt] KanamaScript.bind from source: class not found")
      }
    }

    @JvmStatic
    fun callReload(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      // Error.OK = 0
      rRet.reinterpret(4).set(JAVA_INT, 0, 0)
    }

    @JvmStatic
    fun callPlaceholderInstanceCreate(
      instance: MemorySegment,
      args: MemorySegment,
      rRet: MemorySegment,
    ) {
      callCreateInternal(instance, args, rRet, allowPlaceholder = true)
    }

    @JvmStatic
    fun callGetScriptMethodList(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      val script = ObjectRegistry.get(instance.address()) as? KanamaScript
      if (script == null) {
        BuiltinTypes.construct(net.multigesture.kanama.binding.runtime.VariantType.ARRAY, rRet)
        return
      }
      script.writeScriptMethodList(rRet)
    }

    @JvmStatic
    fun callGetScriptPropertyList(
      instance: MemorySegment,
      args: MemorySegment,
      rRet: MemorySegment,
    ) {
      val script = ObjectRegistry.get(instance.address()) as? KanamaScript
      if (script == null) {
        BuiltinTypes.construct(net.multigesture.kanama.binding.runtime.VariantType.ARRAY, rRet)
        return
      }
      script.writeScriptPropertyList(rRet)
    }

    @JvmStatic
    fun callGetConstants(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      BuiltinTypes.construct(net.multigesture.kanama.binding.runtime.VariantType.DICTIONARY, rRet)
    }

    @JvmStatic
    fun callGetMembers(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      BuiltinTypes.construct(net.multigesture.kanama.binding.runtime.VariantType.ARRAY, rRet)
    }

    @JvmStatic
    fun callHasMethod(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      val script = ObjectRegistry.get(instance.address()) as? KanamaScript
      if (script == null) {
        rRet.reinterpret(1).set(JAVA_BYTE, 0, 0.toByte())
        return
      }
      val argsArray = args.reinterpret(8)
      val methodPtr = argsArray.get(ADDRESS, 0)
      val methodName = methodPtr.reinterpret(8).get(JAVA_LONG, 0)
      val has = script.hasMethod(methodName)
      rRet.reinterpret(1).set(JAVA_BYTE, 0, if (has) 1.toByte() else 0.toByte())
    }

    @JvmStatic
    fun callHasStaticMethod(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      // Static methods on script classes are not exposed yet.
      rRet.reinterpret(1).set(JAVA_BYTE, 0, 0.toByte())
    }

    @JvmStatic
    fun callGetScriptMethodArgumentCount(
      instance: MemorySegment,
      args: MemorySegment,
      rRet: MemorySegment,
    ) {
      val script = ObjectRegistry.get(instance.address()) as? KanamaScript
      if (script == null) {
        rRet.reinterpret(4).set(JAVA_INT, 0, -1)
        return
      }
      val argsArray = args.reinterpret(8)
      val methodPtr = argsArray.get(ADDRESS, 0)
      val methodName = methodPtr.reinterpret(8).get(JAVA_LONG, 0)
      val count = script.getMethodArgumentCount(methodName)
      rRet.reinterpret(4).set(JAVA_INT, 0, count)
    }

    @JvmStatic
    fun callHasScriptSignal(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      val script = ObjectRegistry.get(instance.address()) as? KanamaScript
      if (script == null) {
        rRet.reinterpret(1).set(JAVA_BYTE, 0, 0.toByte())
        return
      }
      val argsArray = args.reinterpret(8)
      val signalPtr = argsArray.get(ADDRESS, 0)
      val signalName = signalPtr.reinterpret(8).get(JAVA_LONG, 0)
      val has = script.hasScriptSignal(signalName)
      rRet.reinterpret(1).set(JAVA_BYTE, 0, if (has) 1.toByte() else 0.toByte())
    }

    @JvmStatic
    fun callGetMethodInfo(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      val script = ObjectRegistry.get(instance.address()) as? KanamaScript
      if (script == null) {
        BuiltinTypes.construct(net.multigesture.kanama.binding.runtime.VariantType.DICTIONARY, rRet)
        return
      }
      val argsArray = args.reinterpret(8)
      val methodPtr = argsArray.get(ADDRESS, 0)
      val methodName = methodPtr.reinterpret(8).get(JAVA_LONG, 0)
      if (!script.writeMethodInfo(methodName, rRet)) {
        BuiltinTypes.construct(net.multigesture.kanama.binding.runtime.VariantType.DICTIONARY, rRet)
      }
    }

    @JvmStatic
    fun callGetScriptSignalList(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      val script = ObjectRegistry.get(instance.address()) as? KanamaScript
      if (script == null) {
        BuiltinTypes.construct(net.multigesture.kanama.binding.runtime.VariantType.ARRAY, rRet)
        return
      }
      script.writeScriptSignalList(rRet)
    }

    @JvmStatic
    fun callGetRpcConfig(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      val script = ObjectRegistry.get(instance.address()) as? KanamaScript
      if (script == null) {
        BuiltinTypes.initNilVariant(rRet)
        return
      }
      script.writeRpcConfig(rRet)
    }

    @JvmStatic
    fun callUpdateExports(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      // Export descriptors are backed by generated static metadata.
      // No runtime rebuild is needed for now.
    }

    @JvmStatic
    fun callHasPropertyDefaultValue(
      instance: MemorySegment,
      args: MemorySegment,
      rRet: MemorySegment,
    ) {
      val handle = instance.address()
      val script = ObjectRegistry.get(handle) as? KanamaScript
      if (script == null) {
        rRet.reinterpret(1).set(JAVA_BYTE, 0, 0.toByte())
        return
      }
      val argsArray = args.reinterpret(8)
      val propPtr = argsArray.get(ADDRESS, 0)
      val propName = propPtr.reinterpret(8).get(JAVA_LONG, 0)
      val has = script.hasPropertyDefault(propName)
      rRet.reinterpret(1).set(JAVA_BYTE, 0, if (has) 1.toByte() else 0.toByte())
    }

    @JvmStatic
    fun callGetPropertyDefaultValue(
      instance: MemorySegment,
      args: MemorySegment,
      rRet: MemorySegment,
    ) {
      val handle = instance.address()
      val script = ObjectRegistry.get(handle) as? KanamaScript
      if (script == null) {
        BuiltinTypes.initNilVariant(rRet)
        return
      }
      val argsArray = args.reinterpret(8)
      val propPtr = argsArray.get(ADDRESS, 0)
      val propName = propPtr.reinterpret(8).get(JAVA_LONG, 0)
      if (!script.writePropertyDefault(propName, rRet)) {
        BuiltinTypes.initNilVariant(rRet)
      }
    }

    @JvmStatic
    fun callEmptyArray(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      BuiltinTypes.construct(net.multigesture.kanama.binding.runtime.VariantType.ARRAY, rRet)
    }

    @JvmStatic
    fun callEmptyDictionary(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      BuiltinTypes.construct(net.multigesture.kanama.binding.runtime.VariantType.DICTIONARY, rRet)
    }

    @JvmStatic
    fun callEmptyString(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      GodotStrings.initString(rRet.reinterpret(8), "")
    }

    @JvmStatic
    fun callNilVariant(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      BuiltinTypes.initNilVariant(rRet)
    }

    @JvmStatic
    fun callGetMemberLine(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      rRet.reinterpret(4).set(JAVA_INT, 0, -1)
    }

    @JvmStatic
    fun callIsTool(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      val handle = instance.address()
      val script = ObjectRegistry.get(handle) as? KanamaScript
      rRet.reinterpret(1).set(JAVA_BYTE, 0, if (script?.isTool == true) 1.toByte() else 0.toByte())
    }

    @JvmStatic
    fun callInstanceHas(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      val script = ObjectRegistry.get(instance.address()) as? KanamaScript
      if (script == null) {
        rRet.reinterpret(1).set(JAVA_BYTE, 0, 0.toByte())
        return
      }

      // Object* argument arrives as type-ptr (Object**); dereference twice.
      val argsArray = args.reinterpret(8)
      val objectTypePtr = argsArray.get(ADDRESS, 0)
      val ownerObject =
        if (objectTypePtr.address() != 0L) {
          objectTypePtr.reinterpret(8).get(ADDRESS, 0)
        } else {
          MemorySegment.NULL
        }
      val hasInstance =
        ownerObject.address() != 0L &&
          (script.hasOwnerObject(ownerObject.address()) ||
            ownerObjectHasScript(ownerObject, script))
      if (
        System.getenv("KANAMA_TRACE_INSTANCE_HAS") == "1" &&
          instanceHasTraceCount < INSTANCE_HAS_TRACE_LIMIT
      ) {
        instanceHasTraceCount += 1
        System.err.println(
          "[kanama:kt] _instance_has script=${script.globalName.ifEmpty { script.kotlinClassName }} " +
            "owner=0x${ownerObject.address().toString(16)} -> $hasInstance"
        )
      }
      rRet.reinterpret(1).set(JAVA_BYTE, 0, if (hasInstance) 1.toByte() else 0.toByte())
    }

    private val objectGetScriptBind by lazy {
      ObjectCalls.getMethodBind("Object", "get_script", 1214101251L)
    }

    private fun ownerObjectHasScript(ownerObject: MemorySegment, script: KanamaScript): Boolean {
      if (objectGetScriptBind.address() == 0L) return false
      val attachedScript =
        ObjectCalls.ptrcallNoArgsRetVariantObject(objectGetScriptBind, ownerObject)
      return attachedScript.address() != 0L &&
        attachedScript.address() == script.godotObject.address()
    }

    /** Shared stub for any virtual that should return bool false. */
    @JvmStatic
    fun callBoolFalse(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      rRet.reinterpret(1).set(JAVA_BYTE, 0, 0.toByte())
    }

    @JvmStatic
    fun callBoolTrue(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      rRet.reinterpret(1).set(JAVA_BYTE, 0, 1.toByte())
    }

    /**
     * `_instance_create(for_object: Object) -> void*`
     *
     * Called by Godot when a node with this script enters the tree. Invokes the factory stored on
     * the [KanamaScript] to create the Kotlin instance, wraps it in a [KanamaScriptInstance], and
     * hands Godot an opaque [ScriptBridge] pointer.
     *
     * Ptrcall convention for Object* arg: args[0] = GDExtensionConstTypePtr → pointer to the
     * Object* value
     */
    @JvmStatic
    fun callInstanceCreate(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
      // Match GDScript editor semantics: a non-@Tool script does not run
      // in the editor. Godot calls _instance_create regardless of editor
      // mode, so we make the call site here force a placeholder instance
      // when we're in the editor and the script is not @Tool. The
      // placeholder has no-op dispatch lambdas, so user _process /
      // _physics_process / _input do not fire — eliminating the
      // `if (Engine.isEditorHint()) return` boilerplate every script
      // would otherwise need.
      val script = ObjectRegistry.get(instance.address()) as? KanamaScript
      // Owner this instance is being created for (double-indirect: args[0] is a type-ptr
      // → the Object* value), matching callCreateInternal's decode. The force-real
      // override applies only when *this* owner is the one under programmatic creation, so
      // a different script instantiated reentrantly on the same thread still gets its
      // editor placeholder.
      val forObjectTypePtr = args.reinterpret(8).get(ADDRESS, 0)
      val forObject = forObjectTypePtr.reinterpret(8).get(ADDRESS, 0)
      val skipForEditor =
        script != null &&
          !script.isTool &&
          net.multigesture.kanama.api.Engine.isEditorHint() &&
          !isProgrammaticCreateOwner(forObject.address())
      callCreateInternal(instance, args, rRet, allowPlaceholder = skipForEditor)
    }

    // KANAMA_TRACE_INSTANCES=1 also emits per-stage boundary lines below (not only the
    // success line): a scene-start stall localizes to the last stage printed without
    // its "done" counterpart (added for the Linux Platformer smoke investigation).
    private val traceInstances = System.getenv("KANAMA_TRACE_INSTANCES") == "1"

    private fun traceStage(script: KanamaScript, stage: String) {
      if (traceInstances) {
        System.err.println("[kanama:kt] instance-create ${script.kotlinClassName}: $stage")
      }
    }

    private fun callCreateInternal(
      instance: MemorySegment,
      args: MemorySegment,
      rRet: MemorySegment,
      allowPlaceholder: Boolean,
    ) {
      val retPtr = MemorySegment.ofAddress(rRet.address()).reinterpret(8)
      val handle = instance.address()
      val script = ObjectRegistry.get(handle) as? KanamaScript
      if (script == null) {
        System.err.println("[kanama:kt] callCreateInternal: no KanamaScript for handle $handle")
        retPtr.set(ADDRESS, 0, MemorySegment.NULL)
        return
      }

      // Double-indirect: args[0] is type-ptr → points to the Object* value.
      val forObjectTypePtr = args.reinterpret(8).get(ADDRESS, 0)
      val forObject = forObjectTypePtr.reinterpret(8).get(ADDRESS, 0)

      // Godot calls _placeholder_instance_create on non-tool scripts in editor.
      // It expects an inert instance whose dispatch is a no-op so user code does
      // not run in editor. Always emit the placeholder shell here regardless of
      // whether a factory exists — using the factory would defeat tool-mode
      // gating and matches the GDScript editor semantic where non-@tool scripts
      // do not run.
      traceStage(
        script,
        "begin obj=0x${forObject.address().toString(16)} placeholder=$allowPlaceholder",
      )
      val si =
        if (allowPlaceholder) {
          // Carry the script's property list onto the placeholder so the
          // editor inspector renders @ScriptProperty fields even when
          // the script isn't @Tool. Dispatch lambdas stay as defaults
          // (no-ops returning false) so user code can't run; ScriptBridge
          // falls back to the script-level get/set/property-default
          // path for inspector reads of @ScriptProperty values.
          KanamaScriptInstance(
            kotlinObject = KanamaPlaceholderScriptInstanceData,
            ownerObject = forObject,
            propertyListPtr = script.propertyListPtr,
            propertyCount = script.propertyCount,
            placeholderPropertyValues = LinkedHashMap(),
          )
        } else {
          traceStage(script, "factory invoke")
          script.factory?.invoke(forObject)
            ?: run {
              System.err.println(
                "[kanama:kt] callInstanceCreate: no factory for ${script.kotlinClassName}"
              )
              retPtr.set(ADDRESS, 0, MemorySegment.NULL)
              return
            }
        }
      traceStage(script, "instance built")
      si.script = script
      traceStage(script, "applyPendingScriptPropertyValues")
      ScriptBridge.applyPendingScriptPropertyValues(forObject, si)
      traceStage(script, "configureLifecycleProcessing")
      ScriptBridge.configureLifecycleProcessing(si)
      traceStage(script, "tracking + ScriptBridge.create")
      script.trackOwnerObject(forObject.address())
      ScriptBridge.trackScriptInstance(forObject, si)
      ScriptBridge.trackKotlinObject(forObject, si.kotlinObject)
      ScriptBridge.retainScriptResource(script.godotObject)
      val siHandle = ObjectRegistry.register(si)
      val instancePtr = ScriptBridge.create(siHandle)

      retPtr.set(ADDRESS, 0, instancePtr)
      if (traceInstances) {
        System.err.println(
          "[kanama:kt] ${if (allowPlaceholder) "callPlaceholderInstanceCreate" else "callInstanceCreate"}: " +
            "${script.kotlinClassName} obj=0x${forObject.address().toString(16)} siHandle=$siHandle " +
            "placeholder=$allowPlaceholder"
        )
      }
    }
  }
}

private object KanamaPlaceholderScriptInstanceData
