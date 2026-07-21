package net.multigesture.kanama.binding

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG
import java.lang.invoke.MethodType
import java.nio.file.Files
import java.nio.file.Paths
import net.multigesture.kanama.binding.runtime.BuiltinTypes
import net.multigesture.kanama.binding.runtime.ClassDB
import net.multigesture.kanama.binding.runtime.GodotStrings
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.Upcalls
import net.multigesture.kanama.binding.runtime.VariantConverters
import net.multigesture.kanama.binding.runtime.VariantType
import net.multigesture.kanama.ffi.GodotFFI

/**
 * Minimal ResourceFormatLoader for `.kt` script files.
 *
 * This enables ResourceLoader to treat `.kt` files as Script resources. Resource loader
 * implementation using class-name/path binding.
 */
object KanamaResourceFormatLoader {

  private lateinit var cls: ClassDB.RegisteredClass
  private var godotObject: MemorySegment = MemorySegment.NULL
  private var loaderHandle: Long = 0L
  private var registeredWithResourceLoader = false

  private var getRecognizedExtensionsNameValue: Long = 0L
  private var handlesTypeNameValue: Long = 0L
  private var getResourceTypeNameValue: Long = 0L
  private var loadNameValue: Long = 0L

  private lateinit var getRecognizedExtensionsStub: MemorySegment
  private lateinit var handlesTypeStub: MemorySegment
  private lateinit var getResourceTypeStub: MemorySegment
  private lateinit var loadStub: MemorySegment

  private const val RESOURCELOADER_ADD_LOADER_HASH = 2896595483L
  private const val RESOURCELOADER_REMOVE_LOADER_HASH = 405397102L
  private const val PROJECTSETTINGS_GLOBALIZE_PATH_HASH = 3135753539L
  private const val RESOURCE_SET_PATH_CACHE_HASH = 83702148L
  private const val RESOURCE_SET_PATH_HASH = 83702148L
  private const val RESOURCE_TAKE_OVER_PATH_HASH = 83702148L
  private const val RESOURCE_GET_PATH_HASH = 201670096L

  fun register(library: MemorySegment) {
    getRecognizedExtensionsNameValue = GodotStrings.stringNameStorage("_get_recognized_extensions")
    handlesTypeNameValue = GodotStrings.stringNameStorage("_handles_type")
    getResourceTypeNameValue = GodotStrings.stringNameStorage("_get_resource_type")
    loadNameValue = GodotStrings.stringNameStorage("_load")

    val virtualType =
      MethodType.methodType(
        Void.TYPE,
        MemorySegment::class.java,
        MemorySegment::class.java,
        MemorySegment::class.java,
      )
    val virtualDesc = FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS)

    getRecognizedExtensionsStub =
      Upcalls.stub(
        KanamaResourceFormatLoader::class.java,
        "callGetRecognizedExtensions",
        virtualType,
        virtualDesc,
      )
    handlesTypeStub =
      Upcalls.stub(
        KanamaResourceFormatLoader::class.java,
        "callHandlesType",
        virtualType,
        virtualDesc,
      )
    getResourceTypeStub =
      Upcalls.stub(
        KanamaResourceFormatLoader::class.java,
        "callGetResourceType",
        virtualType,
        virtualDesc,
      )
    loadStub =
      Upcalls.stub(KanamaResourceFormatLoader::class.java, "callLoad", virtualType, virtualDesc)

    val createStub =
      Upcalls.stub(
        KanamaResourceFormatLoader::class.java,
        "createInstance",
        MethodType.methodType(
          MemorySegment::class.java,
          MemorySegment::class.java,
          java.lang.Byte.TYPE,
        ),
        FunctionDescriptor.of(ADDRESS, ADDRESS, java.lang.foreign.ValueLayout.JAVA_BYTE),
      )
    val freeStub =
      Upcalls.stub(
        KanamaResourceFormatLoader::class.java,
        "freeInstance",
        MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java),
        FunctionDescriptor.ofVoid(ADDRESS, ADDRESS),
      )
    val getVirtualStub =
      Upcalls.stub(
        KanamaResourceFormatLoader::class.java,
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
          name = "KanamaResourceFormatLoader",
          parentName = "ResourceFormatLoader",
          isTool = true,
          createInstance = createStub,
          freeInstance = freeStub,
          getVirtual = getVirtualStub,
        ),
      )

    val constructObject =
      GodotFFI.lookup("classdb_construct_object2", FunctionDescriptor.of(ADDRESS, ADDRESS))
    val objectSetInstance =
      GodotFFI.lookup("object_set_instance", FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS))
    val obj = constructObject.invoke(cls.parentName) as MemorySegment
    val handle = ObjectRegistry.register(KanamaResourceLoaderSentinel)
    objectSetInstance.invoke(obj, cls.className, MemorySegment.ofAddress(handle))
    ObjectCalls.notifyPostinitialize(obj)
    godotObject = obj
    loaderHandle = handle
    System.err.println(
      "[kanama:kt] constructed KanamaResourceFormatLoader obj=0x${obj.address().toString(16)} handle=$handle"
    )
    check(godotObject.address() != 0L) { "KanamaResourceFormatLoader construction failed" }

    val resourceLoaderObj = ObjectCalls.getSingleton("ResourceLoader")
    if (resourceLoaderObj.address() == 0L) {
      System.err.println(
        "[kanama:kt] ResourceLoader singleton missing; script loader not registered"
      )
      return
    }

    val addBind =
      ObjectCalls.getMethodBind(
        "ResourceLoader",
        "add_resource_format_loader",
        RESOURCELOADER_ADD_LOADER_HASH,
      )
    if (addBind.address() == 0L) {
      System.err.println("[kanama:kt] ResourceLoader.add_resource_format_loader bind missing")
      return
    }
    ObjectCalls.ptrcallWithObjectAndBoolArg(addBind, resourceLoaderObj, godotObject, true)
    registeredWithResourceLoader = true
    System.err.println("[kanama:kt] registered KanamaResourceFormatLoader for .kt")
  }

  fun unregister() {
    if (registeredWithResourceLoader && godotObject.address() != 0L) {
      val resourceLoaderObj = ObjectCalls.getSingleton("ResourceLoader")
      val removeBind =
        ObjectCalls.getMethodBind(
          "ResourceLoader",
          "remove_resource_format_loader",
          RESOURCELOADER_REMOVE_LOADER_HASH,
        )
      if (resourceLoaderObj.address() != 0L && removeBind.address() != 0L) {
        ObjectCalls.ptrcallWithObjectArgs(removeBind, resourceLoaderObj, listOf(godotObject))
      }
      registeredWithResourceLoader = false
    }
    // Destroy Kanama's explicitly constructed handler while its pointer is
    // still valid, before ClassDB unregisters its extension class. Leaving a
    // live instance behind hangs shutdown on Linux (StringName/allocator
    // corruption during class teardown).
    if (godotObject.address() != 0L) {
      ObjectCalls.destroyObject(godotObject)
    }
    godotObject = MemorySegment.NULL
    loaderHandle = 0L
  }

  @JvmStatic
  fun createInstance(userdata: MemorySegment, notifyPostinitialize: Byte): MemorySegment {
    val constructParent =
      GodotFFI.lookup("classdb_construct_object2", FunctionDescriptor.of(ADDRESS, ADDRESS))
    val objectSetInstance =
      GodotFFI.lookup("object_set_instance", FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS))
    val obj = constructParent.invoke(cls.parentName) as MemorySegment
    val handle = ObjectRegistry.register(KanamaResourceLoaderSentinel)
    objectSetInstance.invoke(obj, cls.className, MemorySegment.ofAddress(handle))
    if (notifyPostinitialize != 0.toByte()) {
      ObjectCalls.notifyPostinitialize(obj)
    }
    loaderHandle = handle
    godotObject = obj
    System.err.println(
      "[kanama:kt] KanamaResourceFormatLoader.createInstance obj=0x${obj.address().toString(16)} handle=$handle"
    )
    return obj
  }

  @JvmStatic
  fun freeInstance(userdata: MemorySegment, instance: MemorySegment) {
    System.err.println(
      "[kanama:kt] KanamaResourceFormatLoader.freeInstance handle=${instance.address()}"
    )
    ObjectRegistry.unregister(instance.address())
    if (loaderHandle == instance.address()) loaderHandle = 0L
  }

  @JvmStatic
  fun getVirtual(userdata: MemorySegment, name: MemorySegment, hash: Int): MemorySegment {
    val v = name.reinterpret(8).get(JAVA_LONG, 0)
    return when (v) {
      getRecognizedExtensionsNameValue -> getRecognizedExtensionsStub
      handlesTypeNameValue -> handlesTypeStub
      getResourceTypeNameValue -> getResourceTypeStub
      loadNameValue -> loadStub
      else -> MemorySegment.NULL
    }
  }

  @JvmStatic
  fun callGetRecognizedExtensions(
    instance: MemorySegment,
    args: MemorySegment,
    rRet: MemorySegment,
  ) {
    BuiltinTypes.initPackedStringArray(rRet, listOf("kt"))
  }

  @JvmStatic
  fun callHandlesType(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    // ResourceFormatLoader._handles_type(type: StringName) -> bool
    rRet.reinterpret(1).set(java.lang.foreign.ValueLayout.JAVA_BYTE, 0, 1.toByte())
  }

  @JvmStatic
  fun callGetResourceType(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    val path = GodotStrings.readString(args.reinterpret(8).get(ADDRESS, 0))
    val ty = if (path.endsWith(".kt")) "Script" else ""
    GodotStrings.initString(rRet.reinterpret(8), ty)
  }

  @JvmStatic
  fun callLoad(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    val argsArray = args.reinterpret(32)
    val path = GodotStrings.readString(argsArray.get(ADDRESS, 0))
    val basename = path.substringAfterLast('/').substringBeforeLast('.')
    System.err.println("[kanama:kt] ResourceFormatLoader._load path=$path")

    val scriptObj = KanamaScript.constructUnbound()
    // Make this behave like a true external script resource in editor UI.
    val setPathCacheBind =
      ObjectCalls.getMethodBind("Resource", "set_path_cache", RESOURCE_SET_PATH_CACHE_HASH)
    val setPathBind = ObjectCalls.getMethodBind("Resource", "set_path", RESOURCE_SET_PATH_HASH)
    val takeOverPathBind =
      ObjectCalls.getMethodBind("Resource", "take_over_path", RESOURCE_TAKE_OVER_PATH_HASH)
    if (setPathCacheBind.address() != 0L)
      ObjectCalls.ptrcallWithStringArg(setPathCacheBind, scriptObj, path)
    if (setPathBind.address() != 0L) ObjectCalls.ptrcallWithStringArg(setPathBind, scriptObj, path)
    if (takeOverPathBind.address() != 0L)
      ObjectCalls.ptrcallWithStringArg(takeOverPathBind, scriptObj, path)
    val getPathBind = ObjectCalls.getMethodBind("Resource", "get_path", RESOURCE_GET_PATH_HASH)
    if (getPathBind.address() != 0L) {
      val resolvedPath = ObjectCalls.ptrcallNoArgsRetString(getPathBind, scriptObj)
      System.err.println("[kanama:kt] ResourceFormatLoader._load script path=$resolvedPath")
    }
    val script = KanamaScript.byObjectAddress(scriptObj.address())
    if (script != null) {
      script.sourceCode = readScriptSource(path) ?: "class $basename"
      if (!KanamaScript.bindScriptToTemplate(script, basename)) {
        val guessed = KanamaScript.inferGlobalClassNameFromPath(path)
        if (guessed != null) {
          KanamaScript.bindScriptToTemplate(script, guessed)
        }
      }
      System.err.println(
        "[kanama:kt] ResourceFormatLoader._load bound kotlinClass=${script.kotlinClassName} " +
          "global=${script.globalName} factory=${script.factory != null}"
      )
    } else {
      System.err.println(
        "[kanama:kt] ResourceFormatLoader._load failed to resolve script backing object"
      )
    }

    // Return Variant(Object) containing the Script resource.
    Arena.ofConfined().use { arena ->
      val typed = arena.allocate(ADDRESS)
      typed.set(ADDRESS, 0, scriptObj)
      VariantConverters.variantFromType(VariantType.OBJECT).invoke(rRet, typed)
    }
  }

  private fun readScriptSource(resPath: String): String? {
    val absolute = globalizeResPath(resPath) ?: return null
    return try {
      String(Files.readAllBytes(Paths.get(absolute)))
    } catch (_: Throwable) {
      null
    }
  }

  private fun globalizeResPath(resPath: String): String? {
    if (!resPath.startsWith("res://")) return resPath
    val projectSettings = ObjectCalls.getSingleton("ProjectSettings")
    if (projectSettings.address() == 0L) return null
    val bind =
      ObjectCalls.getMethodBind(
        "ProjectSettings",
        "globalize_path",
        PROJECTSETTINGS_GLOBALIZE_PATH_HASH,
      )
    if (bind.address() == 0L) return null
    return ObjectCalls.ptrcallWithStringArgRetString(bind, projectSettings, resPath).ifBlank {
      null
    }
  }
}

private object KanamaResourceLoaderSentinel
