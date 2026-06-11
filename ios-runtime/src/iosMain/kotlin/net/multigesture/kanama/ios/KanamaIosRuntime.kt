package net.multigesture.kanama.ios

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.LongVar
import kotlinx.cinterop.get
import kotlinx.cinterop.set
import kotlinx.cinterop.toKString
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_get_method_bind
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_ptrcall_string_arg
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_set_first_node_in_group_text
import kotlin.experimental.ExperimentalNativeApi
import kotlin.native.CName

internal data class KanamaIosScriptMethod(
    val name: String,
    val argumentCount: Int = 0,
)

internal data class KanamaIosScriptProperty(
    val name: String,
)

internal data class KanamaIosScriptSignal(
    val name: String,
)

internal data class KanamaIosScriptDescriptor(
    val path: String,
    val baseType: String,
    val methods: List<KanamaIosScriptMethod>,
    val properties: List<KanamaIosScriptProperty>,
    val signals: List<KanamaIosScriptSignal>,
    val factory: (Long) -> KanamaIosScriptBridge?,
)

internal interface KanamaIosScriptBridge {
    fun call(methodName: String, firstArg: Double): Boolean

    fun callObject(methodName: String, objectArg: Long): Boolean =
        false

    fun callArgs(methodName: String, arg1: Long, arg2: Long, arg3: Long): Boolean =
        false

    fun callVector2i(methodName: String, x: Long, y: Long): Boolean =
        false

    fun callLong(methodName: String, value: Long): Boolean =
        false

    fun setProperty(propertyIndex: Int, value: Long): Boolean =
        false

    fun setPropertyString(propertyIndex: Int, value: String): Boolean =
        false

    fun setPropertyObjectArray(propertyIndex: Int, values: LongArray): Boolean =
        false

    val scriptInstance: Any?
        get() = null
}

internal object KanamaIosProjectRegistry {
    private val descriptors = linkedMapOf<String, KanamaIosScriptDescriptor>()

    fun register(descriptor: KanamaIosScriptDescriptor) {
        descriptors[descriptor.path] = descriptor
    }

    fun descriptor(path: String): KanamaIosScriptDescriptor? =
        descriptors[path]
}

internal object KanamaIosRuntime {
    private const val PROBE_GROUP = "kanama_ios_probe"
    private const val PROBE_SCRIPT_PATH = "res://kanama_ios_probe.kt"
    private const val LABEL_SET_TEXT_HASH = 83702148L

    private var initialized = false
    private var frameCount = 0
    private var probeLabelUpdated = false
    private var projectRegistryLoaded = false
    private var nextHandle = 1L
    private var labelSetTextBind = 0L
    private val scriptResources = linkedMapOf<Long, IosScriptResource>()
    private val scriptInstances = linkedMapOf<Long, IosScriptInstance>()
    private val ownerObjectToInstance = linkedMapOf<Long, Long>()

    fun entry(getProcAddress: Long, library: Long, initialization: Long): Int {
        if (initialized) {
            log("re-entry: Kotlin/Native runtime already initialized")
            return 1
        }
        initialized = true
        log(
            "entry: get_proc_address=0x${getProcAddress.toULong().toString(16)} " +
                "library=0x${library.toULong().toString(16)} " +
                "initialization=0x${initialization.toULong().toString(16)}",
        )
        return 1
    }

    fun initialize(level: Int) {
        log("initialize: level=$level")
    }

    fun deinitialize(level: Int) {
        log("deinitialize: level=$level")
        if (level == 2) {
            val instances = scriptInstances.size
            val resources = scriptResources.size
            scriptInstances.clear()
            scriptResources.clear()
            ownerObjectToInstance.clear()
            log("cleared iOS script state instances=$instances resources=$resources")
        }
    }

    fun frame() {
        if (probeLabelUpdated) {
            return
        }
        frameCount += 1
        if (KanamaIosGodot.setFirstNodeInGroupText(PROBE_GROUP, "Kanama iOS Kotlin/Native frame ready")) {
            probeLabelUpdated = true
            log("updated grouped probe label on frame=$frameCount")
        } else if (frameCount == 1 || frameCount == 30 || frameCount == 120) {
            log("waiting for grouped probe label on frame=$frameCount")
        }
    }

    fun createScriptResource(path: String?): Long {
        val normalizedPath = path.orEmpty().ifBlank { PROBE_SCRIPT_PATH }
        val descriptor = descriptorForPath(normalizedPath)
        val handle = nextHandle++
        scriptResources[handle] = IosScriptResource(
            path = normalizedPath,
            descriptor = descriptor,
        )
        if (descriptor == null) {
            log("created script resource handle=$handle path=$normalizedPath without iOS descriptor")
        } else {
            log(
                "created script resource handle=$handle path=$normalizedPath " +
                    "base=${descriptor.baseType} methods=${descriptor.methods.map { it.name }}",
            )
        }
        return handle
    }

    fun freeScriptResource(handle: Long) {
        scriptResources.remove(handle)
        log("freed script resource handle=$handle")
    }

    fun scriptResourceBaseType(handle: Long): String =
        scriptResources[handle]?.descriptor?.baseType ?: "Node"

    fun scriptResourceMethodCount(handle: Long): Int =
        scriptResources[handle]?.descriptor?.methods?.size ?: 0

    fun scriptResourceMethodName(handle: Long, methodIndex: Int): String =
        scriptResources[handle]?.descriptor?.methods?.getOrNull(methodIndex)?.name.orEmpty()

    fun scriptResourceMethodArgumentCount(handle: Long, methodIndex: Int): Int =
        scriptResources[handle]?.descriptor?.methods?.getOrNull(methodIndex)?.argumentCount ?: 0

    fun scriptResourcePropertyCount(handle: Long): Int =
        scriptResources[handle]?.descriptor?.properties?.size ?: 0

    fun scriptResourcePropertyName(handle: Long, propertyIndex: Int): String =
        scriptResources[handle]?.descriptor?.properties?.getOrNull(propertyIndex)?.name.orEmpty()

    fun scriptResourceSignalCount(handle: Long): Int =
        scriptResources[handle]?.descriptor?.signals?.size ?: 0

    fun scriptResourceSignalName(handle: Long, signalIndex: Int): String =
        scriptResources[handle]?.descriptor?.signals?.getOrNull(signalIndex)?.name.orEmpty()

    fun createScriptInstance(scriptHandle: Long, ownerObject: Long): Long {
        if (ownerObject == 0L) {
            log("refusing script instance for null owner script=$scriptHandle")
            return 0
        }
        val resource = scriptResources[scriptHandle]
        if (resource?.descriptor == null) {
            log("refusing script instance for script without iOS descriptor handle=$scriptHandle")
            return 0
        }
        val bridge = resource.descriptor.factory(ownerObject)
        if (bridge == null) {
            log("refusing script instance after null bridge script=$scriptHandle path=${resource.path}")
            return 0
        }
        val handle = nextHandle++
        scriptInstances[handle] = IosScriptInstance(
            scriptHandle = scriptHandle,
            ownerObject = ownerObject,
            resource = resource,
            bridge = bridge,
        )
        ownerObjectToInstance[ownerObject] = handle
        log(
            "created script instance handle=$handle script=$scriptHandle path=${resource.path} " +
                "owner=0x${ownerObject.toULong().toString(16)}",
        )
        return handle
    }

    fun readyScriptInstance(handle: Long) {
        val instance = scriptInstances[handle]
        if (instance == null) {
            log("ready skipped for missing script instance handle=$handle")
            return
        }
        callScriptInstance(handle, "_ready", 0.0)
    }

    fun callScriptInstance(handle: Long, methodIndex: Int, firstArg: Double): Boolean {
        val instance = scriptInstances[handle]
        if (instance == null) {
            log("call skipped for missing script instance handle=$handle")
            return false
        }
        val method = instance.resource.descriptor?.methods?.getOrNull(methodIndex)
        if (method == null) {
            log("call skipped for missing method index=$methodIndex handle=$handle")
            return false
        }
        return callScriptInstance(handle, method.name, firstArg)
    }

    fun callScriptInstanceObject(handle: Long, methodIndex: Int, objectArg: Long): Boolean {
        val instance = scriptInstances[handle]
        if (instance == null) {
            log("object call skipped for missing script instance handle=$handle")
            return false
        }
        val method = instance.resource.descriptor?.methods?.getOrNull(methodIndex)
        if (method == null) {
            log("object call skipped for missing method index=$methodIndex handle=$handle")
            return false
        }
        return callScriptInstanceObject(handle, method.name, objectArg)
    }

    fun callScriptInstance(handle: Long, methodName: String, firstArg: Double): Boolean {
        val instance = scriptInstances[handle]
        if (instance == null) {
            log("call skipped for missing script instance handle=$handle")
            return false
        }
        if (methodName == "_ready") {
            if (instance.readyCalled) {
                return true
            }
            instance.readyCalled = true
        }
        val ok = instance.bridge.call(methodName, firstArg)
        if (ok && shouldLogScriptMethodCall(methodName)) {
            val kind = if (instance.resource.path == PROBE_SCRIPT_PATH) "built-in probe" else "project script"
            log("$kind method call handle=$handle path=${instance.resource.path} method=$methodName")
        }
        return ok
    }

    fun callScriptInstanceObject(handle: Long, methodName: String, objectArg: Long): Boolean {
        val instance = scriptInstances[handle]
        if (instance == null) {
            log("object call skipped for missing script instance handle=$handle")
            return false
        }
        if (objectArg == 0L) {
            log("object call skipped for null object method=$methodName handle=$handle")
            return false
        }
        val ok = instance.bridge.callObject(methodName, objectArg)
        if (ok && shouldLogScriptMethodCall(methodName)) {
            val kind = if (instance.resource.path == PROBE_SCRIPT_PATH) "built-in probe" else "project script"
            log("$kind object method call handle=$handle path=${instance.resource.path} method=$methodName")
        }
        return ok
    }

    fun callScriptInstanceArgs(handle: Long, methodIndex: Int, arg1: Long, arg2: Long, arg3: Long): Boolean {
        val instance = scriptInstances[handle]
        if (instance == null) {
            log("args call skipped for missing script instance handle=$handle")
            return false
        }
        val method = instance.resource.descriptor?.methods?.getOrNull(methodIndex)
        if (method == null) {
            log("args call skipped for missing method index=$methodIndex handle=$handle")
            return false
        }
        return callScriptInstanceArgs(handle, method.name, arg1, arg2, arg3)
    }

    fun callScriptInstanceArgs(handle: Long, methodName: String, arg1: Long, arg2: Long, arg3: Long): Boolean {
        val instance = scriptInstances[handle]
        if (instance == null) {
            log("args call skipped for missing script instance handle=$handle")
            return false
        }
        val ok = instance.bridge.callArgs(methodName, arg1, arg2, arg3)
        if (ok && shouldLogScriptMethodCall(methodName)) {
            val kind = if (instance.resource.path == PROBE_SCRIPT_PATH) "built-in probe" else "project script"
            log("$kind args method call handle=$handle path=${instance.resource.path} method=$methodName")
        }
        return ok
    }

    fun callScriptInstanceVector2i(handle: Long, methodIndex: Int, x: Long, y: Long): Boolean {
        val instance = scriptInstances[handle]
        if (instance == null) {
            log("vector2i call skipped for missing script instance handle=$handle")
            return false
        }
        val method = instance.resource.descriptor?.methods?.getOrNull(methodIndex)
        if (method == null) {
            log("vector2i call skipped for missing method index=$methodIndex handle=$handle")
            return false
        }
        return callScriptInstanceVector2i(handle, method.name, x, y)
    }

    fun callScriptInstanceVector2i(handle: Long, methodName: String, x: Long, y: Long): Boolean {
        val instance = scriptInstances[handle]
        if (instance == null) {
            log("vector2i call skipped for missing script instance handle=$handle")
            return false
        }
        val ok = instance.bridge.callVector2i(methodName, x, y)
        if (ok && shouldLogScriptMethodCall(methodName)) {
            val kind = if (instance.resource.path == PROBE_SCRIPT_PATH) "built-in probe" else "project script"
            log("$kind vector2i method call handle=$handle path=${instance.resource.path} method=$methodName")
        }
        return ok
    }

    fun callScriptInstanceLong(handle: Long, methodIndex: Int, value: Long): Boolean {
        val instance = scriptInstances[handle] ?: return false
        val method = instance.resource.descriptor?.methods?.getOrNull(methodIndex) ?: return false
        return callScriptInstanceLong(handle, method.name, value)
    }

    fun callScriptInstanceLong(handle: Long, methodName: String, value: Long): Boolean {
        val instance = scriptInstances[handle] ?: return false
        return instance.bridge.callLong(methodName, value)
    }

    fun setScriptInstanceProperty(handle: Long, propertyIndex: Int, value: Long): Boolean {
        val instance = scriptInstances[handle]
        if (instance == null) {
            log("property set skipped for missing script instance handle=$handle")
            return false
        }
        val ok = instance.bridge.setProperty(propertyIndex, value)
        if (ok) {
            log("property set handle=$handle index=$propertyIndex path=${instance.resource.path}")
        }
        return ok
    }

    fun setScriptInstancePropertyString(handle: Long, propertyIndex: Int, value: String): Boolean {
        val instance = scriptInstances[handle]
        if (instance == null) {
            log("property string set skipped for missing script instance handle=$handle")
            return false
        }
        val ok = instance.bridge.setPropertyString(propertyIndex, value)
        if (ok) {
            log("property string set handle=$handle index=$propertyIndex path=${instance.resource.path}")
        }
        return ok
    }

    fun setScriptInstancePropertyArray(handle: Long, propertyIndex: Int, values: LongArray): Boolean {
        val instance = scriptInstances[handle]
        if (instance == null) {
            log("property array set skipped for missing script instance handle=$handle")
            return false
        }
        val ok = instance.bridge.setPropertyObjectArray(propertyIndex, values)
        if (ok) {
            log("property array set handle=$handle index=$propertyIndex count=${values.size} path=${instance.resource.path}")
        }
        return ok
    }

    fun freeScriptInstance(handle: Long) {
        val instance = scriptInstances[handle]
        if (instance != null) {
            ownerObjectToInstance.remove(instance.ownerObject)
        }
        scriptInstances.remove(handle)
        log("freed script instance handle=$handle")
    }

    @PublishedApi
    internal fun scriptInstanceForOwner(ownerObject: Long): Any? {
        val instanceHandle = ownerObjectToInstance[ownerObject] ?: return null
        val instance = scriptInstances[instanceHandle] ?: return null
        return instance.bridge.scriptInstance
    }

    fun labelSetTextBind(): Long {
        if (labelSetTextBind == 0L) {
            labelSetTextBind = KanamaIosGodot.getMethodBind("Label", "set_text", LABEL_SET_TEXT_HASH)
        }
        return labelSetTextBind
    }

    private fun descriptorForPath(path: String): KanamaIosScriptDescriptor? {
        ensureProjectRegistryLoaded()
        return KanamaIosProjectRegistry.descriptor(path) ?: builtInProbeDescriptor(path)
    }

    private fun ensureProjectRegistryLoaded() {
        if (projectRegistryLoaded) {
            return
        }
        registerKanamaIosProjectScripts()
        projectRegistryLoaded = true
    }

    private fun builtInProbeDescriptor(path: String): KanamaIosScriptDescriptor? {
        if (path != PROBE_SCRIPT_PATH) {
            return null
        }
        return KanamaIosScriptDescriptor(
            path = path,
            baseType = "Label",
            methods = listOf(KanamaIosScriptMethod("_ready")),
            properties = emptyList(),
            signals = emptyList(),
            factory = { ownerObject -> BuiltInProbeScript(ownerObject) },
        )
    }

    private fun shouldLogScriptMethodCall(methodName: String): Boolean =
        methodName != "_process" && methodName != "_physics_process"

    private fun log(message: String) {
        println("[kanama][ios][kn] $message")
    }

    private data class IosScriptResource(
        val path: String,
        val descriptor: KanamaIosScriptDescriptor?,
    )

    private data class IosScriptInstance(
        val scriptHandle: Long,
        val ownerObject: Long,
        val resource: IosScriptResource,
        val bridge: KanamaIosScriptBridge,
        var readyCalled: Boolean = false,
    )

    private class BuiltInProbeScript(
        private val ownerObject: Long,
    ) : KanamaIosScriptBridge {
        override fun call(methodName: String, firstArg: Double): Boolean {
            if (methodName != "_ready") {
                return false
            }
            return KanamaIosGodot.setObjectText(ownerObject, "Kanama iOS Kotlin/Native script ready")
        }
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun writeCString(value: String, buffer: CPointer<ByteVar>?, bufferSize: Int) {
    if (buffer == null || bufferSize <= 0) {
        return
    }
    val bytes = value.encodeToByteArray()
    val count = minOf(bytes.size, bufferSize - 1)
    for (i in 0 until count) {
        buffer[i] = bytes[i]
    }
    buffer[count] = 0.toByte()
}

@OptIn(ExperimentalForeignApi::class)
private object KanamaIosGodot {
    fun setFirstNodeInGroupText(groupName: String, value: String): Boolean =
        kanama_ios_godot_set_first_node_in_group_text(groupName, value) != 0

    fun getMethodBind(className: String, methodName: String, hash: Long): Long =
        kanama_ios_godot_get_method_bind(className, methodName, hash)

    fun setObjectText(objectHandle: Long, value: String): Boolean {
        val bind = KanamaIosRuntime.labelSetTextBind()
        if (bind == 0L || objectHandle == 0L) {
            return false
        }
        kanama_ios_godot_ptrcall_string_arg(bind, objectHandle, value)
        return true
    }
}

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_entry")
fun kanamaIosRuntimeEntry(getProcAddress: Long, library: Long, initialization: Long): Int =
    KanamaIosRuntime.entry(getProcAddress, library, initialization)

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_initialize")
fun kanamaIosRuntimeInitialize(level: Int) {
    KanamaIosRuntime.initialize(level)
}

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_deinitialize")
fun kanamaIosRuntimeDeinitialize(level: Int) {
    KanamaIosRuntime.deinitialize(level)
}

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_frame")
fun kanamaIosRuntimeFrame() {
    KanamaIosRuntime.frame()
}

@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_resource_create")
fun kanamaIosRuntimeScriptResourceCreate(path: CPointer<ByteVar>?): Long =
    KanamaIosRuntime.createScriptResource(path?.toKString())

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_resource_free")
fun kanamaIosRuntimeScriptResourceFree(scriptHandle: Long) {
    KanamaIosRuntime.freeScriptResource(scriptHandle)
}

@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_resource_base_type")
fun kanamaIosRuntimeScriptResourceBaseType(scriptHandle: Long, buffer: CPointer<ByteVar>?, bufferSize: Int) {
    writeCString(KanamaIosRuntime.scriptResourceBaseType(scriptHandle), buffer, bufferSize)
}

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_resource_method_count")
fun kanamaIosRuntimeScriptResourceMethodCount(scriptHandle: Long): Int =
    KanamaIosRuntime.scriptResourceMethodCount(scriptHandle)

@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_resource_method_name")
fun kanamaIosRuntimeScriptResourceMethodName(
    scriptHandle: Long,
    methodIndex: Int,
    buffer: CPointer<ByteVar>?,
    bufferSize: Int,
) {
    writeCString(KanamaIosRuntime.scriptResourceMethodName(scriptHandle, methodIndex), buffer, bufferSize)
}

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_resource_method_argument_count")
fun kanamaIosRuntimeScriptResourceMethodArgumentCount(scriptHandle: Long, methodIndex: Int): Int =
    KanamaIosRuntime.scriptResourceMethodArgumentCount(scriptHandle, methodIndex)

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_resource_property_count")
fun kanamaIosRuntimeScriptResourcePropertyCount(scriptHandle: Long): Int =
    KanamaIosRuntime.scriptResourcePropertyCount(scriptHandle)

@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_resource_property_name")
fun kanamaIosRuntimeScriptResourcePropertyName(
    scriptHandle: Long,
    propertyIndex: Int,
    buffer: CPointer<ByteVar>?,
    bufferSize: Int,
) {
    writeCString(KanamaIosRuntime.scriptResourcePropertyName(scriptHandle, propertyIndex), buffer, bufferSize)
}

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_resource_signal_count")
fun kanamaIosRuntimeScriptResourceSignalCount(scriptHandle: Long): Int =
    KanamaIosRuntime.scriptResourceSignalCount(scriptHandle)

@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_resource_signal_name")
fun kanamaIosRuntimeScriptResourceSignalName(
    scriptHandle: Long,
    signalIndex: Int,
    buffer: CPointer<ByteVar>?,
    bufferSize: Int,
) {
    writeCString(KanamaIosRuntime.scriptResourceSignalName(scriptHandle, signalIndex), buffer, bufferSize)
}

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_instance_create")
fun kanamaIosRuntimeScriptInstanceCreate(scriptHandle: Long, ownerObject: Long): Long =
    KanamaIosRuntime.createScriptInstance(scriptHandle, ownerObject)

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_instance_ready")
fun kanamaIosRuntimeScriptInstanceReady(instanceHandle: Long) {
    KanamaIosRuntime.readyScriptInstance(instanceHandle)
}

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_instance_call")
fun kanamaIosRuntimeScriptInstanceCall(instanceHandle: Long, methodIndex: Int, firstArg: Double): Int =
    if (KanamaIosRuntime.callScriptInstance(instanceHandle, methodIndex, firstArg)) 1 else 0

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_instance_call_object")
fun kanamaIosRuntimeScriptInstanceCallObject(instanceHandle: Long, methodIndex: Int, objectArg: Long): Int =
    if (KanamaIosRuntime.callScriptInstanceObject(instanceHandle, methodIndex, objectArg)) 1 else 0

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_instance_call_args")
fun kanamaIosRuntimeScriptInstanceCallArgs(
    instanceHandle: Long,
    methodIndex: Int,
    arg1: Long,
    arg2: Long,
    arg3: Long,
): Int =
    if (KanamaIosRuntime.callScriptInstanceArgs(instanceHandle, methodIndex, arg1, arg2, arg3)) 1 else 0

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_instance_call_vector2i")
fun kanamaIosRuntimeScriptInstanceCallVector2i(
    instanceHandle: Long,
    methodIndex: Int,
    x: Long,
    y: Long,
): Int =
    if (KanamaIosRuntime.callScriptInstanceVector2i(instanceHandle, methodIndex, x, y)) 1 else 0

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_instance_call_long")
fun kanamaIosRuntimeScriptInstanceCallLong(
    instanceHandle: Long,
    methodIndex: Int,
    value: Long,
): Int =
    if (KanamaIosRuntime.callScriptInstanceLong(instanceHandle, methodIndex, value)) 1 else 0

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_instance_set_property")
fun kanamaIosRuntimeScriptInstanceSetProperty(
    instanceHandle: Long,
    propertyIndex: Int,
    value: Long,
): Int =
    if (KanamaIosRuntime.setScriptInstanceProperty(instanceHandle, propertyIndex, value)) 1 else 0

@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_instance_set_property_string")
fun kanamaIosRuntimeScriptInstanceSetPropertyString(
    instanceHandle: Long,
    propertyIndex: Int,
    value: CPointer<ByteVar>?,
): Int {
    val str = value?.toKString() ?: ""
    return if (KanamaIosRuntime.setScriptInstancePropertyString(instanceHandle, propertyIndex, str)) 1 else 0
}

@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_instance_set_property_array")
fun kanamaIosRuntimeScriptInstanceSetPropertyArray(
    instanceHandle: Long,
    propertyIndex: Int,
    objects: CPointer<LongVar>?,
    count: Int,
): Int {
    val values = if (objects == null || count <= 0) {
        LongArray(0)
    } else {
        LongArray(count) { i -> objects[i] }
    }
    return if (KanamaIosRuntime.setScriptInstancePropertyArray(instanceHandle, propertyIndex, values)) 1 else 0
}

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_instance_free")
fun kanamaIosRuntimeScriptInstanceFree(instanceHandle: Long) {
    KanamaIosRuntime.freeScriptInstance(instanceHandle)
}

@PublishedApi
internal fun iosScriptInstanceForOwner(ownerObject: Long): Any? =
    KanamaIosRuntime.scriptInstanceForOwner(ownerObject)
