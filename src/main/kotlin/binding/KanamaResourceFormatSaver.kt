package net.multigesture.kanama.binding

import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_BYTE
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG
import java.lang.invoke.MethodType
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import net.multigesture.kanama.binding.runtime.BuiltinTypes
import net.multigesture.kanama.binding.runtime.ClassDB
import net.multigesture.kanama.binding.runtime.GodotStrings
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.Upcalls
import net.multigesture.kanama.ffi.GodotFFI

/**
 * ResourceFormatSaver for `.kt` script resources.
 *
 * Required for ScriptCreateDialog "Create" flow (writes template source to disk).
 */
object KanamaResourceFormatSaver {

  private lateinit var cls: ClassDB.RegisteredClass
  private var godotObject: MemorySegment = MemorySegment.NULL
  private var saverHandle: Long = 0L
  private var registeredWithResourceSaver = false

  private var saveNameValue: Long = 0L
  private var setUidNameValue: Long = 0L
  private var recognizeNameValue: Long = 0L
  private var getRecognizedExtensionsNameValue: Long = 0L
  private var recognizePathNameValue: Long = 0L

  private lateinit var saveStub: MemorySegment
  private lateinit var setUidStub: MemorySegment
  private lateinit var recognizeStub: MemorySegment
  private lateinit var getRecognizedExtensionsStub: MemorySegment
  private lateinit var recognizePathStub: MemorySegment

  private const val RESOURCESAVER_ADD_SAVER_HASH = 362894272L
  private const val RESOURCESAVER_REMOVE_SAVER_HASH = 3373026878L
  private const val PROJECTSETTINGS_GLOBALIZE_PATH_HASH = 3135753539L
  private const val SCRIPT_GET_SOURCE_CODE_HASH = 201670096L
  private const val RESOURCE_GET_PATH_HASH = 201670096L

  private const val ERROR_OK = 0
  private const val ERROR_CANT_CREATE = 20

  fun register(library: MemorySegment) {
    saveNameValue = GodotStrings.stringNameStorage("_save")
    setUidNameValue = GodotStrings.stringNameStorage("_set_uid")
    recognizeNameValue = GodotStrings.stringNameStorage("_recognize")
    getRecognizedExtensionsNameValue = GodotStrings.stringNameStorage("_get_recognized_extensions")
    recognizePathNameValue = GodotStrings.stringNameStorage("_recognize_path")

    val virtualType =
      MethodType.methodType(
        Void.TYPE,
        MemorySegment::class.java,
        MemorySegment::class.java,
        MemorySegment::class.java,
      )
    val virtualDesc = FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS)

    saveStub =
      Upcalls.stub(KanamaResourceFormatSaver::class.java, "callSave", virtualType, virtualDesc)
    setUidStub =
      Upcalls.stub(KanamaResourceFormatSaver::class.java, "callSetUid", virtualType, virtualDesc)
    recognizeStub =
      Upcalls.stub(KanamaResourceFormatSaver::class.java, "callRecognize", virtualType, virtualDesc)
    getRecognizedExtensionsStub =
      Upcalls.stub(
        KanamaResourceFormatSaver::class.java,
        "callGetRecognizedExtensions",
        virtualType,
        virtualDesc,
      )
    recognizePathStub =
      Upcalls.stub(
        KanamaResourceFormatSaver::class.java,
        "callRecognizePath",
        virtualType,
        virtualDesc,
      )

    val createStub =
      Upcalls.stub(
        KanamaResourceFormatSaver::class.java,
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
        KanamaResourceFormatSaver::class.java,
        "freeInstance",
        MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java),
        FunctionDescriptor.ofVoid(ADDRESS, ADDRESS),
      )
    val getVirtualStub =
      Upcalls.stub(
        KanamaResourceFormatSaver::class.java,
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
          name = "KanamaResourceFormatSaver",
          parentName = "ResourceFormatSaver",
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
    val handle = ObjectRegistry.register(KanamaResourceSaverSentinel)
    objectSetInstance.invoke(obj, cls.className, MemorySegment.ofAddress(handle))
    ObjectCalls.notifyPostinitialize(obj)
    godotObject = obj
    saverHandle = handle

    val resourceSaverObj = ObjectCalls.getSingleton("ResourceSaver")
    if (resourceSaverObj.address() == 0L) return
    val addBind =
      ObjectCalls.getMethodBind(
        "ResourceSaver",
        "add_resource_format_saver",
        RESOURCESAVER_ADD_SAVER_HASH,
      )
    if (addBind.address() == 0L) return
    ObjectCalls.ptrcallWithObjectAndBoolArg(addBind, resourceSaverObj, godotObject, false)
    registeredWithResourceSaver = true
    System.err.println("[kanama:kt] registered KanamaResourceFormatSaver for .kt")
  }

  fun unregister() {
    if (registeredWithResourceSaver && godotObject.address() != 0L) {
      val resourceSaverObj = ObjectCalls.getSingleton("ResourceSaver")
      val removeBind =
        ObjectCalls.getMethodBind(
          "ResourceSaver",
          "remove_resource_format_saver",
          RESOURCESAVER_REMOVE_SAVER_HASH,
        )
      if (resourceSaverObj.address() != 0L && removeBind.address() != 0L) {
        ObjectCalls.ptrcallWithObjectArgs(removeBind, resourceSaverObj, listOf(godotObject))
      }
    }
    registeredWithResourceSaver = false
    // Destroy the explicitly constructed handler before class teardown; a
    // live instance at ClassDB unregister hangs shutdown on Linux.
    if (godotObject.address() != 0L) {
      ObjectCalls.destroyObject(godotObject)
    }
    godotObject = MemorySegment.NULL
    saverHandle = 0L
  }

  @JvmStatic
  fun createInstance(userdata: MemorySegment, notifyPostinitialize: Byte): MemorySegment {
    val constructParent =
      GodotFFI.lookup("classdb_construct_object2", FunctionDescriptor.of(ADDRESS, ADDRESS))
    val objectSetInstance =
      GodotFFI.lookup("object_set_instance", FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS))
    val obj = constructParent.invoke(cls.parentName) as MemorySegment
    val handle = ObjectRegistry.register(KanamaResourceSaverSentinel)
    objectSetInstance.invoke(obj, cls.className, MemorySegment.ofAddress(handle))
    if (notifyPostinitialize != 0.toByte()) {
      ObjectCalls.notifyPostinitialize(obj)
    }
    saverHandle = handle
    godotObject = obj
    return obj
  }

  @JvmStatic
  fun freeInstance(userdata: MemorySegment, instance: MemorySegment) {
    ObjectRegistry.unregister(instance.address())
  }

  @JvmStatic
  fun getVirtual(userdata: MemorySegment, name: MemorySegment, hash: Int): MemorySegment {
    val v = name.reinterpret(8).get(JAVA_LONG, 0)
    return when (v) {
      saveNameValue -> saveStub
      setUidNameValue -> setUidStub
      recognizeNameValue -> recognizeStub
      getRecognizedExtensionsNameValue -> getRecognizedExtensionsStub
      recognizePathNameValue -> recognizePathStub
      else -> MemorySegment.NULL
    }
  }

  @JvmStatic
  fun callSave(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    val argsArray = args.reinterpret(24)
    val resourceTypePtr = argsArray.get(ADDRESS, 0)
    val resourceObj =
      if (resourceTypePtr.address() != 0L) {
        resourceTypePtr.reinterpret(8).get(ADDRESS, 0)
      } else {
        MemorySegment.NULL
      }
    val path = GodotStrings.readString(argsArray.get(ADDRESS, 8))
    System.err.println(
      "[kanama:kt] ResourceFormatSaver._save path=$path obj=0x${resourceObj.address().toString(16)}"
    )
    if (resourceObj.address() == 0L || !path.endsWith(".kt")) {
      System.err.println(
        "[kanama:kt] ResourceFormatSaver._save reject: invalid object or extension"
      )
      rRet.reinterpret(4).set(JAVA_INT, 0, ERROR_CANT_CREATE)
      return
    }

    val getSourceBind =
      ObjectCalls.getMethodBind("Script", "get_source_code", SCRIPT_GET_SOURCE_CODE_HASH)
    if (getSourceBind.address() == 0L) {
      System.err.println(
        "[kanama:kt] ResourceFormatSaver._save reject: Script.get_source_code bind missing"
      )
      rRet.reinterpret(4).set(JAVA_INT, 0, ERROR_CANT_CREATE)
      return
    }
    val source = ObjectCalls.ptrcallNoArgsRetString(getSourceBind, resourceObj)
    val absolute = globalizeResPath(path)
    if (absolute == null) {
      System.err.println(
        "[kanama:kt] ResourceFormatSaver._save reject: globalize_path failed for $path"
      )
      rRet.reinterpret(4).set(JAVA_INT, 0, ERROR_CANT_CREATE)
      return
    }
    try {
      val target = Paths.get(absolute)
      val parent = target.parent
      if (parent != null) {
        Files.createDirectories(parent)
      }
      Files.write(
        target,
        source.toByteArray(),
        StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING,
        StandardOpenOption.WRITE,
      )
      System.err.println(
        "[kanama:kt] ResourceFormatSaver._save wrote $absolute bytes=${source.length}"
      )
      rRet.reinterpret(4).set(JAVA_INT, 0, ERROR_OK)
    } catch (t: Throwable) {
      System.err.println(
        "[kanama:kt] ResourceFormatSaver._save failed: ${t::class.qualifiedName}: ${t.message}"
      )
      rRet.reinterpret(4).set(JAVA_INT, 0, ERROR_CANT_CREATE)
    }
  }

  @JvmStatic
  fun callSetUid(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    // UIDs for .kt scripts are optional for now.
    rRet.reinterpret(4).set(JAVA_INT, 0, ERROR_OK)
  }

  @JvmStatic
  fun callRecognize(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    val argsArray = args.reinterpret(8)
    val resourceTypePtr = argsArray.get(ADDRESS, 0)
    val resourceObj =
      if (resourceTypePtr.address() != 0L) {
        resourceTypePtr.reinterpret(8).get(ADDRESS, 0)
      } else {
        MemorySegment.NULL
      }
    if (resourceObj.address() == 0L) {
      rRet.reinterpret(1).set(JAVA_BYTE, 0, 0.toByte())
      return
    }
    val getPathBind = ObjectCalls.getMethodBind("Resource", "get_path", RESOURCE_GET_PATH_HASH)
    val path =
      if (getPathBind.address() != 0L) ObjectCalls.ptrcallNoArgsRetString(getPathBind, resourceObj)
      else ""
    val recognized = path.endsWith(".kt")
    System.err.println("[kanama:kt] ResourceFormatSaver._recognize path=$path -> $recognized")
    rRet.reinterpret(1).set(JAVA_BYTE, 0, if (recognized) 1.toByte() else 0.toByte())
  }

  @JvmStatic
  fun callGetRecognizedExtensions(
    instance: MemorySegment,
    args: MemorySegment,
    rRet: MemorySegment,
  ) {
    System.err.println("[kanama:kt] ResourceFormatSaver._get_recognized_extensions")
    BuiltinTypes.initPackedStringArray(rRet, listOf("kt"))
  }

  @JvmStatic
  fun callRecognizePath(instance: MemorySegment, args: MemorySegment, rRet: MemorySegment) {
    val argsArray = args.reinterpret(16)
    val path = GodotStrings.readString(argsArray.get(ADDRESS, 8))
    val recognized = path.endsWith(".kt")
    System.err.println("[kanama:kt] ResourceFormatSaver._recognize_path path=$path -> $recognized")
    rRet.reinterpret(1).set(JAVA_BYTE, 0, if (recognized) 1.toByte() else 0.toByte())
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

private object KanamaResourceSaverSentinel
