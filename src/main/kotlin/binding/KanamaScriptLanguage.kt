package net.multigesture.kanama.binding

import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_BYTE
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG
import java.lang.invoke.MethodType
import net.multigesture.kanama.api.MainThread
import net.multigesture.kanama.binding.runtime.BuiltinTypes
import net.multigesture.kanama.binding.runtime.ClassDB
import net.multigesture.kanama.binding.runtime.GodotStrings
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.Upcalls
import net.multigesture.kanama.ffi.GodotFFI

/**
 * The Kanama script language singleton.
 *
 * Registers a ClassDB extension of [ScriptLanguageExtension], constructs one instance, and adds it
 * to the engine via Engine::register_script_language. After this, Godot knows about the "Kanama"
 * language and can display/attach `.kt`-extension scripts in the editor.
 *
 * Script language registration, Script resource creation, and ScriptInstance wiring for
 * property/method dispatch.
 */
object KanamaScriptLanguage {

  /** The single live Godot object for this language (set in [register]). */
  var godotObject: MemorySegment = MemorySegment.NULL
    private set

  private lateinit var cls: ClassDB.RegisteredClass
  private var registeredWithEngine: Boolean = false
  private var singletonHandle: Long = 0L

  // Interned virtual name storage values.
  private var getNameNameValue: Long = 0L
  private var getTypeNameValue: Long = 0L
  private var getExtensionNameValue: Long = 0L
  private var createScriptNameValue: Long = 0L
  private var makeTemplateNameValue: Long = 0L
  private var isUsingTemplatesNameValue: Long = 0L
  private var hasNamedClassesNameValue: Long = 0L
  private var supportsBuiltinModeNameValue: Long = 0L
  private var supportsDocumentationNameValue: Long = 0L
  private var canInheritFromFileNameValue: Long = 0L
  private var canMakeFunctionNameValue: Long = 0L
  private var overridesExternalEditorNameValue: Long = 0L
  private var getRecognizedExtensionsNameValue: Long = 0L
  private var handlesGlobalClassTypeNameValue: Long = 0L
  private var getGlobalClassNameNameValue: Long = 0L
  private var validatePathNameValue: Long = 0L
  private var initNameValue: Long = 0L
  private var finishNameValue: Long = 0L
  private var threadEnterNameValue: Long = 0L
  private var threadExitNameValue: Long = 0L
  private var frameNameValue: Long = 0L
  private var debugGetCurrentStackInfoNameValue: Long = 0L
  private var getReservedWordsNameValue: Long = 0L
  private var isControlFlowKeywordNameValue: Long = 0L
  private var getCommentDelimitersNameValue: Long = 0L
  private var getDocCommentDelimitersNameValue: Long = 0L
  private var getStringDelimitersNameValue: Long = 0L
  private var getBuiltInTemplatesNameValue: Long = 0L
  private var validateNameValue: Long = 0L
  private var completeCodeNameValue: Long = 0L
  private var lookupCodeNameValue: Long = 0L
  private var autoIndentCodeNameValue: Long = 0L
  private var debugGetStackLevelLocalsNameValue: Long = 0L
  private var debugGetStackLevelMembersNameValue: Long = 0L
  private var debugGetStackLevelInstanceNameValue: Long = 0L
  private var debugGetGlobalsNameValue: Long = 0L
  private var getPublicFunctionsNameValue: Long = 0L
  private var getPublicConstantsNameValue: Long = 0L
  private var getPublicAnnotationsNameValue: Long = 0L
  private var profilingGetAccumulatedDataNameValue: Long = 0L
  private var profilingGetFrameDataNameValue: Long = 0L
  private var addNamedGlobalConstantNameValue: Long = 0L
  private var removeNamedGlobalConstantNameValue: Long = 0L
  private var addGlobalConstantNameValue: Long = 0L
  private var reloadAllScriptsNameValue: Long = 0L

  // Upcall stubs.
  private lateinit var getNameStub: MemorySegment
  private lateinit var getTypeStub: MemorySegment
  private lateinit var getExtensionStub: MemorySegment
  private lateinit var createScriptStub: MemorySegment
  private lateinit var makeTemplateStub: MemorySegment
  private lateinit var boolFalseStub: MemorySegment
  private lateinit var hasNamedClassesStub: MemorySegment
  private lateinit var handlesGlobalClassTypeStub: MemorySegment
  private lateinit var getGlobalClassNameStub: MemorySegment
  private lateinit var getRecognizedExtensionsStub: MemorySegment
  private lateinit var validatePathStub: MemorySegment
  private lateinit var noopStub: MemorySegment
  private lateinit var frameStub: MemorySegment
  private lateinit var debugGetCurrentStackInfoStub: MemorySegment
  private lateinit var reservedWordsStub: MemorySegment
  private lateinit var isControlFlowKeywordStub: MemorySegment
  private lateinit var emptyPackedStringArrayStub: MemorySegment
  private lateinit var commentDelimitersStub: MemorySegment
  private lateinit var docCommentDelimitersStub: MemorySegment
  private lateinit var stringDelimitersStub: MemorySegment
  private lateinit var emptyArrayStub: MemorySegment
  private lateinit var emptyDictionaryStub: MemorySegment
  private lateinit var validateStub: MemorySegment
  private lateinit var completeCodeStub: MemorySegment
  private lateinit var lookupCodeStub: MemorySegment
  private lateinit var autoIndentCodeStub: MemorySegment
  private lateinit var nullPointerStub: MemorySegment
  private lateinit var intZeroStub: MemorySegment

  private const val ENGINE_REGISTER_SCRIPT_LANGUAGE_HASH = 1850254898L
  private const val ENGINE_UNREGISTER_SCRIPT_LANGUAGE_HASH = 1850254898L
  private const val ERROR_UNAVAILABLE = 2L
  private val KOTLIN_RESERVED_WORDS =
    listOf(
      "as",
      "break",
      "class",
      "continue",
      "do",
      "else",
      "false",
      "for",
      "fun",
      "if",
      "in",
      "interface",
      "is",
      "null",
      "object",
      "package",
      "return",
      "super",
      "this",
      "throw",
      "true",
      "try",
      "typealias",
      "typeof",
      "val",
      "var",
      "when",
      "while",
      "by",
      "catch",
      "constructor",
      "delegate",
      "dynamic",
      "field",
      "file",
      "finally",
      "get",
      "import",
      "init",
      "param",
      "property",
      "receiver",
      "set",
      "setparam",
      "value",
      "where",
      "abstract",
      "actual",
      "annotation",
      "companion",
      "const",
      "crossinline",
      "data",
      "enum",
      "expect",
      "external",
      "final",
      "infix",
      "inline",
      "inner",
      "internal",
      "lateinit",
      "noinline",
      "open",
      "operator",
      "out",
      "override",
      "private",
      "protected",
      "public",
      "reified",
      "sealed",
      "suspend",
      "tailrec",
      "vararg",
    )
  private val KOTLIN_CONTROL_FLOW_WORDS =
    setOf("break", "continue", "do", "else", "for", "if", "return", "throw", "try", "when", "while")

  fun register(library: MemorySegment) {
    // Intern virtual names.
    getNameNameValue = GodotStrings.stringNameStorage("_get_name")
    getTypeNameValue = GodotStrings.stringNameStorage("_get_type")
    getExtensionNameValue = GodotStrings.stringNameStorage("_get_extension")
    createScriptNameValue = GodotStrings.stringNameStorage("_create_script")
    makeTemplateNameValue = GodotStrings.stringNameStorage("_make_template")
    isUsingTemplatesNameValue = GodotStrings.stringNameStorage("_is_using_templates")
    hasNamedClassesNameValue = GodotStrings.stringNameStorage("_has_named_classes")
    supportsBuiltinModeNameValue = GodotStrings.stringNameStorage("_supports_builtin_mode")
    supportsDocumentationNameValue = GodotStrings.stringNameStorage("_supports_documentation")
    canInheritFromFileNameValue = GodotStrings.stringNameStorage("_can_inherit_from_file")
    canMakeFunctionNameValue = GodotStrings.stringNameStorage("_can_make_function")
    overridesExternalEditorNameValue = GodotStrings.stringNameStorage("_overrides_external_editor")
    getRecognizedExtensionsNameValue = GodotStrings.stringNameStorage("_get_recognized_extensions")
    handlesGlobalClassTypeNameValue = GodotStrings.stringNameStorage("_handles_global_class_type")
    getGlobalClassNameNameValue = GodotStrings.stringNameStorage("_get_global_class_name")
    validatePathNameValue = GodotStrings.stringNameStorage("_validate_path")
    initNameValue = GodotStrings.stringNameStorage("_init")
    finishNameValue = GodotStrings.stringNameStorage("_finish")
    threadEnterNameValue = GodotStrings.stringNameStorage("_thread_enter")
    threadExitNameValue = GodotStrings.stringNameStorage("_thread_exit")
    frameNameValue = GodotStrings.stringNameStorage("_frame")
    debugGetCurrentStackInfoNameValue =
      GodotStrings.stringNameStorage("_debug_get_current_stack_info")
    getReservedWordsNameValue = GodotStrings.stringNameStorage("_get_reserved_words")
    isControlFlowKeywordNameValue = GodotStrings.stringNameStorage("_is_control_flow_keyword")
    getCommentDelimitersNameValue = GodotStrings.stringNameStorage("_get_comment_delimiters")
    getDocCommentDelimitersNameValue = GodotStrings.stringNameStorage("_get_doc_comment_delimiters")
    getStringDelimitersNameValue = GodotStrings.stringNameStorage("_get_string_delimiters")
    getBuiltInTemplatesNameValue = GodotStrings.stringNameStorage("_get_built_in_templates")
    validateNameValue = GodotStrings.stringNameStorage("_validate")
    completeCodeNameValue = GodotStrings.stringNameStorage("_complete_code")
    lookupCodeNameValue = GodotStrings.stringNameStorage("_lookup_code")
    autoIndentCodeNameValue = GodotStrings.stringNameStorage("_auto_indent_code")
    debugGetStackLevelLocalsNameValue =
      GodotStrings.stringNameStorage("_debug_get_stack_level_locals")
    debugGetStackLevelMembersNameValue =
      GodotStrings.stringNameStorage("_debug_get_stack_level_members")
    debugGetStackLevelInstanceNameValue =
      GodotStrings.stringNameStorage("_debug_get_stack_level_instance")
    debugGetGlobalsNameValue = GodotStrings.stringNameStorage("_debug_get_globals")
    getPublicFunctionsNameValue = GodotStrings.stringNameStorage("_get_public_functions")
    getPublicConstantsNameValue = GodotStrings.stringNameStorage("_get_public_constants")
    getPublicAnnotationsNameValue = GodotStrings.stringNameStorage("_get_public_annotations")
    profilingGetAccumulatedDataNameValue =
      GodotStrings.stringNameStorage("_profiling_get_accumulated_data")
    profilingGetFrameDataNameValue = GodotStrings.stringNameStorage("_profiling_get_frame_data")
    addNamedGlobalConstantNameValue = GodotStrings.stringNameStorage("_add_named_global_constant")
    removeNamedGlobalConstantNameValue =
      GodotStrings.stringNameStorage("_remove_named_global_constant")
    addGlobalConstantNameValue = GodotStrings.stringNameStorage("_add_global_constant")
    reloadAllScriptsNameValue = GodotStrings.stringNameStorage("_reload_all_scripts")

    val virtualType =
      MethodType.methodType(
        Void.TYPE,
        MemorySegment::class.java,
        MemorySegment::class.java,
        MemorySegment::class.java,
      )
    val virtualDesc = FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS)

    getNameStub =
      Upcalls.stub(KanamaScriptLanguage::class.java, "callGetName", virtualType, virtualDesc)
    getTypeStub =
      Upcalls.stub(KanamaScriptLanguage::class.java, "callGetType", virtualType, virtualDesc)
    getExtensionStub =
      Upcalls.stub(KanamaScriptLanguage::class.java, "callGetExtension", virtualType, virtualDesc)
    createScriptStub =
      Upcalls.stub(KanamaScriptLanguage::class.java, "callCreateScript", virtualType, virtualDesc)
    makeTemplateStub =
      Upcalls.stub(KanamaScriptLanguage::class.java, "callMakeTemplate", virtualType, virtualDesc)
    hasNamedClassesStub =
      Upcalls.stub(
        KanamaScriptLanguage::class.java,
        "callHasNamedClasses",
        virtualType,
        virtualDesc,
      )
    handlesGlobalClassTypeStub =
      Upcalls.stub(
        KanamaScriptLanguage::class.java,
        "callHandlesGlobalClassType",
        virtualType,
        virtualDesc,
      )
    getGlobalClassNameStub =
      Upcalls.stub(
        KanamaScriptLanguage::class.java,
        "callGetGlobalClassName",
        virtualType,
        virtualDesc,
      )
    getRecognizedExtensionsStub =
      Upcalls.stub(
        KanamaScriptLanguage::class.java,
        "callGetRecognizedExtensions",
        virtualType,
        virtualDesc,
      )
    validatePathStub =
      Upcalls.stub(KanamaScriptLanguage::class.java, "callValidatePath", virtualType, virtualDesc)
    boolFalseStub =
      Upcalls.stub(KanamaScriptLanguage::class.java, "callBoolFalse", virtualType, virtualDesc)
    noopStub = Upcalls.stub(KanamaScriptLanguage::class.java, "callNoop", virtualType, virtualDesc)
    frameStub =
      Upcalls.stub(KanamaScriptLanguage::class.java, "callFrame", virtualType, virtualDesc)
    debugGetCurrentStackInfoStub =
      Upcalls.stub(
        KanamaScriptLanguage::class.java,
        "callDebugGetCurrentStackInfo",
        virtualType,
        virtualDesc,
      )
    reservedWordsStub =
      Upcalls.stub(
        KanamaScriptLanguage::class.java,
        "callGetReservedWords",
        virtualType,
        virtualDesc,
      )
    isControlFlowKeywordStub =
      Upcalls.stub(
        KanamaScriptLanguage::class.java,
        "callIsControlFlowKeyword",
        virtualType,
        virtualDesc,
      )
    emptyPackedStringArrayStub =
      Upcalls.stub(
        KanamaScriptLanguage::class.java,
        "callEmptyPackedStringArray",
        virtualType,
        virtualDesc,
      )
    commentDelimitersStub =
      Upcalls.stub(
        KanamaScriptLanguage::class.java,
        "callGetCommentDelimiters",
        virtualType,
        virtualDesc,
      )
    docCommentDelimitersStub =
      Upcalls.stub(
        KanamaScriptLanguage::class.java,
        "callGetDocCommentDelimiters",
        virtualType,
        virtualDesc,
      )
    stringDelimitersStub =
      Upcalls.stub(
        KanamaScriptLanguage::class.java,
        "callGetStringDelimiters",
        virtualType,
        virtualDesc,
      )
    emptyArrayStub =
      Upcalls.stub(KanamaScriptLanguage::class.java, "callEmptyArray", virtualType, virtualDesc)
    emptyDictionaryStub =
      Upcalls.stub(
        KanamaScriptLanguage::class.java,
        "callEmptyDictionary",
        virtualType,
        virtualDesc,
      )
    validateStub =
      Upcalls.stub(KanamaScriptLanguage::class.java, "callValidate", virtualType, virtualDesc)
    completeCodeStub =
      Upcalls.stub(KanamaScriptLanguage::class.java, "callCompleteCode", virtualType, virtualDesc)
    lookupCodeStub =
      Upcalls.stub(KanamaScriptLanguage::class.java, "callLookupCode", virtualType, virtualDesc)
    autoIndentCodeStub =
      Upcalls.stub(KanamaScriptLanguage::class.java, "callAutoIndentCode", virtualType, virtualDesc)
    nullPointerStub =
      Upcalls.stub(KanamaScriptLanguage::class.java, "callNullPointer", virtualType, virtualDesc)
    intZeroStub =
      Upcalls.stub(KanamaScriptLanguage::class.java, "callIntZero", virtualType, virtualDesc)

    val createStub =
      Upcalls.stub(
        KanamaScriptLanguage::class.java,
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
        KanamaScriptLanguage::class.java,
        "freeInstance",
        MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java),
        FunctionDescriptor.ofVoid(ADDRESS, ADDRESS),
      )
    val getVirtualStub =
      Upcalls.stub(
        KanamaScriptLanguage::class.java,
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
          name = "KanamaScriptLanguage",
          parentName = "ScriptLanguageExtension",
          isTool = true,
          createInstance = createStub,
          freeInstance = freeStub,
          getVirtual = getVirtualStub,
        ),
      )
    System.err.println("[kanama:kt] registered KanamaScriptLanguage : ScriptLanguageExtension")

    // Construct the singleton object and register it with the Engine.
    // Trigger createInstance — it constructs the parent object, registers our sentinel,
    // and stores the result in godotObject.
    val constructObject =
      GodotFFI.lookup("classdb_construct_object2", FunctionDescriptor.of(ADDRESS, ADDRESS))
    System.err.println("[kanama:kt] constructing KanamaScriptLanguage singleton")
    constructObject.invoke(cls.className)
    check(godotObject.address() != 0L) { "KanamaScriptLanguage construction failed" }

    System.err.println(
      "[kanama:kt] KanamaScriptLanguage singleton obj=0x${godotObject.address().toString(16)}"
    )
    if (System.getenv("KANAMA_SKIP_ENGINE_REGISTER") == "1") {
      System.err.println(
        "[kanama:kt] skipping Engine.register_script_language due to KANAMA_SKIP_ENGINE_REGISTER=1"
      )
      registeredWithEngine = false
      return
    }

    System.err.println("[kanama:kt] resolving Engine singleton")
    val engineObj = ObjectCalls.getSingleton("Engine")
    check(engineObj.address() != 0L) { "Engine singleton not found" }

    System.err.println("[kanama:kt] resolving Engine.register_script_language bind")
    val registerBind =
      ObjectCalls.getMethodBind(
        "Engine",
        "register_script_language",
        ENGINE_REGISTER_SCRIPT_LANGUAGE_HASH,
      )
    check(registerBind.address() != 0L) { "Engine::register_script_language method bind not found" }

    System.err.println("[kanama:kt] calling Engine.register_script_language")
    val err = ObjectCalls.ptrcallWithObjectArgsRetInt(registerBind, engineObj, listOf(godotObject))
    check(err == 0) { "Engine::register_script_language failed with Error=$err" }
    registeredWithEngine = true
    System.err.println("[kanama:kt] registered Kanama script language with Engine (Error=$err)")
  }

  fun unregister() {
    System.err.println(
      "[kanama:kt] KanamaScriptLanguage.unregister begin " +
        "registeredWithEngine=$registeredWithEngine " +
        "singletonHandle=$singletonHandle " +
        "godotObject=0x${godotObject.address().toString(16)}"
    )
    if (registeredWithEngine && godotObject.address() != 0L) {
      val engineObj = ObjectCalls.getSingleton("Engine")
      if (engineObj.address() != 0L) {
        val unregisterBind =
          ObjectCalls.getMethodBind(
            "Engine",
            "unregister_script_language",
            ENGINE_UNREGISTER_SCRIPT_LANGUAGE_HASH,
          )
        if (unregisterBind.address() != 0L) {
          val err =
            ObjectCalls.ptrcallWithObjectArgsRetInt(unregisterBind, engineObj, listOf(godotObject))
          if (err == 0) {
            registeredWithEngine = false
            System.err.println(
              "[kanama:kt] unregistered Kanama script language from Engine (Error=$err)"
            )
          } else {
            System.err.println("[kanama:kt] Engine::unregister_script_language returned Error=$err")
          }
        }
      }
    }

    if (singletonHandle != 0L && godotObject.address() != 0L) {
      try {
        System.err.println(
          "[kanama:kt] destroying KanamaScriptLanguage singleton object 0x${godotObject.address().toString(16)}"
        )
        ObjectCalls.destroyObject(godotObject)
        System.err.println("[kanama:kt] destroyed KanamaScriptLanguage singleton object")
      } catch (t: Throwable) {
        System.err.println(
          "[kanama:kt] failed destroying KanamaScriptLanguage singleton: ${t.message}"
        )
      }
    }
    singletonHandle = 0L
    godotObject = MemorySegment.NULL
    System.err.println("[kanama:kt] KanamaScriptLanguage.unregister end")
  }

  // ---- Upcall stubs ----

  @JvmStatic
  fun createInstance(userdata: MemorySegment, notifyPostinitialize: Byte): MemorySegment {
    // Godot calls this to get the Object pointer for this class.
    // Construct a base ScriptLanguageExtension object, attach our sentinel as
    // the extension instance data, and store the result as the singleton.
    val constructParent =
      GodotFFI.lookup("classdb_construct_object2", FunctionDescriptor.of(ADDRESS, ADDRESS))
    val objectSetInstance =
      GodotFFI.lookup("object_set_instance", FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS))
    val obj = constructParent.invoke(cls.parentName) as MemorySegment
    val handle = ObjectRegistry.register(KanamaScriptLanguageSentinel)
    objectSetInstance.invoke(obj, cls.className, MemorySegment.ofAddress(handle))
    if (notifyPostinitialize != 0.toByte()) {
      ObjectCalls.notifyPostinitialize(obj)
    }
    singletonHandle = handle
    godotObject = obj
    System.err.println(
      "[kanama:kt] KanamaScriptLanguage.createInstance obj=0x${obj.address().toString(16)}"
    )
    return obj
  }

  @JvmStatic
  fun freeInstance(userdata: MemorySegment, instance: MemorySegment) {
    ObjectRegistry.unregister(instance.address())
    if (singletonHandle == instance.address()) {
      singletonHandle = 0L
    }
    System.err.println("[kanama:kt] KanamaScriptLanguage.freeInstance")
  }

  @JvmStatic
  fun getVirtual(userdata: MemorySegment, name: MemorySegment, hash: Int): MemorySegment {
    val v = name.reinterpret(8).get(JAVA_LONG, 0)
    return when (v) {
      getNameNameValue -> getNameStub
      getTypeNameValue -> getTypeStub
      getExtensionNameValue -> getExtensionStub
      createScriptNameValue -> createScriptStub
      makeTemplateNameValue -> makeTemplateStub
      hasNamedClassesNameValue -> hasNamedClassesStub
      handlesGlobalClassTypeNameValue -> handlesGlobalClassTypeStub
      getGlobalClassNameNameValue -> getGlobalClassNameStub
      getRecognizedExtensionsNameValue -> getRecognizedExtensionsStub
      getReservedWordsNameValue -> reservedWordsStub
      isControlFlowKeywordNameValue -> isControlFlowKeywordStub
      getCommentDelimitersNameValue -> commentDelimitersStub
      getDocCommentDelimitersNameValue -> docCommentDelimitersStub
      getStringDelimitersNameValue -> stringDelimitersStub
      getBuiltInTemplatesNameValue,
      getPublicFunctionsNameValue,
      getPublicAnnotationsNameValue -> emptyArrayStub
      getPublicConstantsNameValue,
      debugGetStackLevelLocalsNameValue,
      debugGetStackLevelMembersNameValue,
      debugGetGlobalsNameValue -> emptyDictionaryStub
      validateNameValue -> validateStub
      completeCodeNameValue -> completeCodeStub
      lookupCodeNameValue -> lookupCodeStub
      autoIndentCodeNameValue -> autoIndentCodeStub
      debugGetStackLevelInstanceNameValue -> nullPointerStub
      profilingGetAccumulatedDataNameValue,
      profilingGetFrameDataNameValue -> intZeroStub
      validatePathNameValue -> validatePathStub
      isUsingTemplatesNameValue,
      supportsBuiltinModeNameValue,
      supportsDocumentationNameValue,
      canInheritFromFileNameValue,
      canMakeFunctionNameValue,
      overridesExternalEditorNameValue -> boolFalseStub
      // Required virtuals with nothing for Kanama to do. Godot logs a
      // non-fatal "missing required virtual" error if these aren't
      // routed; the bodies are no-ops because Kanama scripts are
      // compiled Kotlin, so there is no name-resolution table for the
      // language to maintain (autoloads/global constants are reached
      // via the scene tree, not language-level identifier lookup).
      initNameValue,
      finishNameValue,
      threadEnterNameValue,
      threadExitNameValue,
      addNamedGlobalConstantNameValue,
      removeNamedGlobalConstantNameValue,
      addGlobalConstantNameValue,
      reloadAllScriptsNameValue -> noopStub
      frameNameValue -> frameStub
      debugGetCurrentStackInfoNameValue -> debugGetCurrentStackInfoStub
      else -> MemorySegment.NULL
    }
  }

  // ---- Virtual implementations ----

  @JvmStatic
  fun callGetName(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    GodotStrings.initString(rRet.reinterpret(8), "Kotlin")
  }

  @JvmStatic
  fun callGetType(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    GodotStrings.initString(rRet.reinterpret(8), "Kanama")
  }

  @JvmStatic
  fun callGetExtension(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    GodotStrings.initString(rRet.reinterpret(8), "kt")
  }

  @JvmStatic
  fun callCreateScript(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    // Create an unbound script resource; _set_source_code binds it to a generated template.
    val scriptObj = KanamaScript.constructUnbound()
    System.err.println(
      "[kanama:kt] ScriptLanguage._create_script -> 0x${scriptObj.address().toString(16)}"
    )
    rRet.reinterpret(8).set(ADDRESS, 0, scriptObj)
  }

  @JvmStatic
  fun callMakeTemplate(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    val argsArray = args.reinterpret(24)
    val templateArg = GodotStrings.readString(argsArray.get(ADDRESS, 0))
    val classNameArg = GodotStrings.readString(argsArray.get(ADDRESS, 8))
    val inheritsArg = GodotStrings.readString(argsArray.get(ADDRESS, 16))

    val className = classNameArg.ifBlank { "NewScript" }
    val attachTo = inheritsArg.ifBlank { "Node" }
    val source = buildString {
      appendLine("import net.multigesture.kanama.annotations.ScriptClass")
      appendLine("import net.multigesture.kanama.annotations.GlobalClass")
      appendLine("import java.lang.foreign.MemorySegment")
      appendLine()
      appendLine("@ScriptClass(attachTo = \"$attachTo\")")
      appendLine("@GlobalClass")
      appendLine("class $className(val godotObject: MemorySegment) {")
      appendLine("}")
    }

    val scriptObj = KanamaScript.constructUnbound()
    val script = KanamaScript.byObjectAddress(scriptObj.address())
    if (script != null) {
      script.sourceCode = source
      KanamaScript.bindScriptFromSource(script, source)
    }
    System.err.println(
      "[kanama:kt] ScriptLanguage._make_template template=$templateArg class=$className inherits=$attachTo"
    )
    rRet.reinterpret(8).set(ADDRESS, 0, scriptObj)
  }

  @JvmStatic
  fun callHasNamedClasses(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    rRet
      .reinterpret(1)
      .set(JAVA_BYTE, 0, if (KanamaScript.hasNamedClasses()) 1.toByte() else 0.toByte())
  }

  @JvmStatic
  fun callHandlesGlobalClassType(
    instance: MemorySegment,
    args: MemorySegment,
    rRet: MemorySegment,
  ) {
    val argsArray = args.reinterpret(8)
    val typePtr = argsArray.get(ADDRESS, 0)
    val typeName = GodotStrings.readString(typePtr)
    // The argument is a script RESOURCE TYPE (what a loader's _get_resource_type reports for
    // the file), not a global class name: EditorFileSystem::_get_global_script_class routes
    // "which language owns this script file's global classes" through this check (GDScript
    // matches "GDScript", C# matches "CSharpScript"). Our loader reports "Script" for .kt.
    val handled = typeName == "Script"
    rRet.reinterpret(1).set(JAVA_BYTE, 0, if (handled) 1.toByte() else 0.toByte())
  }

  @JvmStatic
  fun callGetGlobalClassName(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    val argsArray = args.reinterpret(8)
    val pathPtr = argsArray.get(ADDRESS, 0)
    val path = GodotStrings.readString(pathPtr)
    val globalName = KanamaScript.inferGlobalClassNameFromPath(path)
    val meta = globalName?.let { KanamaScript.metaByGlobalName(it) }
    if (globalName == null || meta == null) {
      // Not a @GlobalClass script (or catalog meta missing): report no global class rather
      // than inventing one with a guessed base type.
      BuiltinTypes.construct(net.multigesture.kanama.binding.runtime.VariantType.DICTIONARY, rRet)
      return
    }
    System.err.println(
      "[kanama:kt] ScriptLanguage._get_global_class_name path=$path -> $globalName base=${meta.instanceBaseType}"
    )
    BuiltinTypes.initDictionary(
      rRet,
      mapOf(
        "name" to globalName,
        "base_type" to meta.instanceBaseType,
        "icon_path" to "",
        "is_abstract" to false,
        "is_tool" to meta.isTool,
      ),
    )
  }

  @JvmStatic
  fun callGetRecognizedExtensions(
    instance: MemorySegment,
    args: MemorySegment,
    rRet: MemorySegment,
  ) {
    System.err.println("[kanama:kt] ScriptLanguage._get_recognized_extensions")
    BuiltinTypes.initPackedStringArray(rRet, listOf("kt"))
  }

  @JvmStatic
  fun callValidatePath(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    val argsArray = args.reinterpret(8)
    val pathPtr = argsArray.get(ADDRESS, 0)
    val path = GodotStrings.readString(pathPtr)
    val validationError = if (path.endsWith(".kt")) "" else "Invalid extension."
    GodotStrings.initString(rRet.reinterpret(8), validationError)
  }

  @JvmStatic
  fun callBoolFalse(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    rRet.reinterpret(1).set(JAVA_BYTE, 0, 0.toByte())
  }

  @JvmStatic
  fun callEmptyPackedStringArray(
    instance: MemorySegment,
    args: MemorySegment,
    rRet: MemorySegment,
  ) {
    BuiltinTypes.initPackedStringArray(rRet, emptyList())
  }

  @JvmStatic
  fun callGetReservedWords(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    BuiltinTypes.initPackedStringArray(rRet, KOTLIN_RESERVED_WORDS)
  }

  @JvmStatic
  fun callIsControlFlowKeyword(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    val argsArray = args.reinterpret(8)
    val keyword = GodotStrings.readString(argsArray.get(ADDRESS, 0))
    rRet
      .reinterpret(1)
      .set(JAVA_BYTE, 0, if (keyword in KOTLIN_CONTROL_FLOW_WORDS) 1.toByte() else 0.toByte())
  }

  @JvmStatic
  fun callGetCommentDelimiters(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    BuiltinTypes.initPackedStringArray(rRet, listOf("//", "/* */"))
  }

  @JvmStatic
  fun callGetDocCommentDelimiters(
    instance: MemorySegment,
    args: MemorySegment,
    rRet: MemorySegment,
  ) {
    BuiltinTypes.initPackedStringArray(rRet, listOf("/** */"))
  }

  @JvmStatic
  fun callGetStringDelimiters(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    BuiltinTypes.initPackedStringArray(rRet, listOf("\" \"", "' '"))
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
  fun callValidate(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    BuiltinTypes.initDictionary(rRet, mapOf("valid" to true))
  }

  @JvmStatic
  fun callCompleteCode(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    BuiltinTypes.initDictionary(
      rRet,
      mapOf("result" to ERROR_UNAVAILABLE, "force" to false, "call_hint" to ""),
    )
  }

  @JvmStatic
  fun callLookupCode(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    BuiltinTypes.initDictionary(rRet, mapOf("result" to ERROR_UNAVAILABLE, "type" to 0L))
  }

  @JvmStatic
  fun callAutoIndentCode(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    val argsArray = args.reinterpret(24)
    val codePtr = argsArray.get(ADDRESS, 0)
    val code = GodotStrings.readString(codePtr)
    GodotStrings.initString(rRet.reinterpret(8), code)
  }

  @JvmStatic
  fun callNullPointer(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    rRet.reinterpret(8).set(ADDRESS, 0, MemorySegment.NULL)
  }

  @JvmStatic
  fun callIntZero(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    rRet.reinterpret(4).set(JAVA_INT, 0, 0)
  }

  @JvmStatic
  fun callNoop(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    // Intentionally empty.
  }

  @JvmStatic
  fun callFrame(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    MainThread.pump()
    KanamaHotReload.frameTick()
  }

  @JvmStatic
  fun callDebugGetCurrentStackInfo(
    instance: MemorySegment,
    args: MemorySegment,
    rRet: MemorySegment,
  ) {
    BuiltinTypes.construct(net.multigesture.kanama.binding.runtime.VariantType.ARRAY, rRet)
  }
}

/** Sentinel object stored in ObjectRegistry for the language singleton's handle. */
private object KanamaScriptLanguageSentinel
