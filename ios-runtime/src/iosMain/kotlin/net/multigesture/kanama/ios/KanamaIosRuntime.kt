package net.multigesture.kanama.ios

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.COpaquePointerVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CPointerVar
import kotlinx.cinterop.DoubleVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.FloatVar
import kotlinx.cinterop.IntVar
import kotlinx.cinterop.LongVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.free
import kotlinx.cinterop.get
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.nativeHeap
import kotlinx.cinterop.ptr
import kotlinx.cinterop.readBytes
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.set
import kotlinx.cinterop.toKString
import kotlinx.cinterop.toLong
import kotlinx.cinterop.value
import net.multigesture.kanama.api.MainThread
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_get_method_bind
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_ptrcall_string_arg
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_set_first_node_in_group_text
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.GodotReal
import net.multigesture.kanama.types.GodotRealVar
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3
import kotlin.experimental.ExperimentalNativeApi
import kotlin.native.CName

internal data class KanamaIosScriptMethod(
    val name: String,
    val argumentCount: Int = 0,
)

internal data class KanamaIosScriptProperty(
    val name: String,
    // Godot Variant::Type, so the script instance can advertise this @ScriptProperty to the
    // engine (get_property_list) — required for scene-stored values to be applied. Default 0
    // (NIL) keeps older generated registrars compiling.
    val variantType: Int = 0,
    val hint: Int = 0,
    val hintString: String = "",
    val usage: Int = 6,
)

internal data class KanamaIosScriptSignal(
    val name: String,
)

internal data class KanamaIosRpcConfig(
    val methodName: String,
    val mode: Int,
    val callLocal: Boolean,
    val transferMode: Int,
    val channel: Int,
)

internal data class KanamaIosScriptDescriptor(
    val path: String,
    val baseType: String,
    val methods: List<KanamaIosScriptMethod>,
    val properties: List<KanamaIosScriptProperty>,
    val signals: List<KanamaIosScriptSignal>,
    val rpcConfigs: List<KanamaIosRpcConfig> = emptyList(),
    val factory: (Long) -> KanamaIosScriptBridge?,
)

/** Sentinel returned by [KanamaIosScriptBridge.callVReturning] when the method is not a
 *  value-returning one handled by this bridge (so the void [KanamaIosScriptBridge.callV] path runs). */
object KanamaIosNoReturn

/** Sentinel for a property index the generated bridge does not expose through ScriptInstance.get. */
object KanamaIosNoProperty

internal interface KanamaIosScriptBridge {
    // Generic per-signature inbound dispatch (Phase 3.3): [args] are the already-decoded Kotlin
    // values (one per method param, in order); the generated branch casts/wraps each to its
    // declared type and invokes the script method.
    fun callV(methodName: String, args: List<Any?>): Boolean =
        false

    // Phase 5.3b: value-returning methods/virtuals. Returns the typed result (Bool/Long/Double/
    // Vector2/Vector2i) for the engine return slot, or [KanamaIosNoReturn] if this method has no
    // marshalled return (the void [callV] path then handles it).
    fun callVReturning(methodName: String, args: List<Any?>): Any? =
        KanamaIosNoReturn

    fun setProperty(propertyIndex: Int, value: Long): Boolean =
        false

    fun getProperty(propertyIndex: Int): Any? =
        KanamaIosNoProperty

    fun setPropertyString(propertyIndex: Int, value: String): Boolean =
        false

    fun setPropertyObjectArray(propertyIndex: Int, values: LongArray): Boolean =
        false

    // Integer Array @ScriptProperty delivery. Kept separate from object arrays so malformed
    // scene data can never turn an integer cell into a bogus object handle. The generated
    // List<Enum> bridge converts these ordinals to enum entries with desktop-identical clamping.
    fun setPropertyIntArray(propertyIndex: Int, values: LongArray): Boolean =
        false

    // `List<String>` (Godot PackedStringArray) @ScriptProperty delivery. The C side extracts the
    // packed array's elements to utf8 C strings and hands them here; the generated
    // setPropertyStringArray branch assigns the list to the field. See the C
    // PACKED_STRING_ARRAY set-property dispatch case + the emitter's string-list block.
    fun setPropertyStringArray(propertyIndex: Int, values: List<String>): Boolean =
        false

    // Value-type @ScriptProperty delivery (NodePath/Vector2/Vector3/Color). [value] is the
    // already-decoded Kotlin value; the generated branch casts it to the field's type. See
    // the C set-property value path + setScriptInstancePropertyValue's tag decode.
    fun setPropertyValue(propertyIndex: Int, value: Any): Boolean =
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

// Registers every @ScriptClass found in the project's iOS scripts. The body is generated by the
// shared KSP processor (:processor) into each native leaf source set as the `actual` — see
// IosScriptCodeEmitter. KanamaIosRuntime (shared iosMain) calls it through this `expect` because
// it cannot reference the per-target generated declaration directly. The processor always emits
// an actual (empty when the project has no @ScriptClass), so the expect is always satisfied.
internal expect fun registerKanamaIosProjectScripts()

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
        // Resume any coroutines parked on MainThread.awaitNextFrame() once per engine frame.
        // Runs every frame (before the probe-label early-return) so frame-based waits keep advancing.
        MainThread.pumpNextFrame()
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

    fun scriptResourcePropertyType(handle: Long, propertyIndex: Int): Int =
        scriptResources[handle]?.descriptor?.properties?.getOrNull(propertyIndex)?.variantType ?: 0

    fun scriptResourcePropertyHint(handle: Long, propertyIndex: Int): Int =
        scriptResources[handle]?.descriptor?.properties?.getOrNull(propertyIndex)?.hint ?: 0

    fun scriptResourcePropertyHintString(handle: Long, propertyIndex: Int): String =
        scriptResources[handle]?.descriptor?.properties?.getOrNull(propertyIndex)?.hintString.orEmpty()

    fun scriptResourcePropertyUsage(handle: Long, propertyIndex: Int): Int =
        scriptResources[handle]?.descriptor?.properties?.getOrNull(propertyIndex)?.usage ?: 6

    fun scriptResourceSignalCount(handle: Long): Int =
        scriptResources[handle]?.descriptor?.signals?.size ?: 0

    fun scriptResourceSignalName(handle: Long, signalIndex: Int): String =
        scriptResources[handle]?.descriptor?.signals?.getOrNull(signalIndex)?.name.orEmpty()

    fun scriptResourceRpcConfigCount(handle: Long): Int =
        scriptResources[handle]?.descriptor?.rpcConfigs?.size ?: 0

    fun scriptResourceRpcConfigMethodName(handle: Long, rpcConfigIndex: Int): String =
        scriptResources[handle]?.descriptor?.rpcConfigs?.getOrNull(rpcConfigIndex)?.methodName.orEmpty()

    fun scriptResourceRpcConfigMode(handle: Long, rpcConfigIndex: Int): Int =
        scriptResources[handle]?.descriptor?.rpcConfigs?.getOrNull(rpcConfigIndex)?.mode ?: 0

    fun scriptResourceRpcConfigCallLocal(handle: Long, rpcConfigIndex: Int): Int =
        if (scriptResources[handle]?.descriptor?.rpcConfigs?.getOrNull(rpcConfigIndex)?.callLocal == true) 1 else 0

    fun scriptResourceRpcConfigTransferMode(handle: Long, rpcConfigIndex: Int): Int =
        scriptResources[handle]?.descriptor?.rpcConfigs?.getOrNull(rpcConfigIndex)?.transferMode ?: 0

    fun scriptResourceRpcConfigChannel(handle: Long, rpcConfigIndex: Int): Int =
        scriptResources[handle]?.descriptor?.rpcConfigs?.getOrNull(rpcConfigIndex)?.channel ?: 0

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
        callScriptInstanceV(handle, "_ready", emptyList())
    }

    // Generic per-signature dispatch (Phase 3.3): the C side marshalled the Variant args into
    // PT-tagged buffers and the @CName export decoded them to [args]; route to the generated callV.
    fun callScriptInstanceV(handle: Long, methodIndex: Int, args: List<Any?>): Boolean {
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
        return callScriptInstanceV(handle, method.name, args)
    }

    // Phase 5.3b: value-returning dispatch. Returns the typed result, or KanamaIosNoReturn if the
    // method has no marshalled return (caller then runs the void callScriptInstanceV path).
    fun callScriptInstanceReturning(handle: Long, methodIndex: Int, args: List<Any?>): Any? {
        val instance = scriptInstances[handle] ?: return KanamaIosNoReturn
        val method = instance.resource.descriptor?.methods?.getOrNull(methodIndex) ?: return KanamaIosNoReturn
        return instance.bridge.callVReturning(method.name, args)
    }

    fun callScriptInstanceV(handle: Long, methodName: String, args: List<Any?>): Boolean {
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
        val ok = instance.bridge.callV(methodName, args)
        if (ok && shouldLogScriptMethodCall(methodName)) {
            val kind = if (instance.resource.path == PROBE_SCRIPT_PATH) "built-in probe" else "project script"
            log("$kind method call handle=$handle path=${instance.resource.path} method=$methodName")
        }
        return ok
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

    fun getScriptInstanceProperty(handle: Long, propertyIndex: Int): Any? {
        val instance = scriptInstances[handle]
        if (instance == null) {
            log("property get skipped for missing script instance handle=$handle")
            return KanamaIosNoProperty
        }
        val value = instance.bridge.getProperty(propertyIndex)
        if (value !== KanamaIosNoProperty) {
            log("property get handle=$handle index=$propertyIndex path=${instance.resource.path}")
        }
        return value
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

    fun setScriptInstancePropertyIntArray(handle: Long, propertyIndex: Int, values: LongArray): Boolean {
        val instance = scriptInstances[handle]
        if (instance == null) {
            log("property int-array set skipped for missing script instance handle=$handle")
            return false
        }
        val ok = instance.bridge.setPropertyIntArray(propertyIndex, values)
        if (ok) {
            log("property int-array set handle=$handle index=$propertyIndex count=${values.size} path=${instance.resource.path}")
        }
        return ok
    }

    fun setScriptInstancePropertyStringArray(handle: Long, propertyIndex: Int, values: List<String>): Boolean {
        val instance = scriptInstances[handle]
        if (instance == null) {
            log("property string-array set skipped for missing script instance handle=$handle")
            return false
        }
        val ok = instance.bridge.setPropertyStringArray(propertyIndex, values)
        if (ok) {
            log("property string-array set handle=$handle index=$propertyIndex count=${values.size} path=${instance.resource.path}")
        }
        return ok
    }

    fun setScriptInstancePropertyValue(handle: Long, propertyIndex: Int, value: Any): Boolean {
        val instance = scriptInstances[handle]
        if (instance == null) {
            log("property value set skipped for missing script instance handle=$handle")
            return false
        }
        val ok = instance.bridge.setPropertyValue(propertyIndex, value)
        if (ok) {
            log("property value set handle=$handle index=$propertyIndex path=${instance.resource.path}")
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
            rpcConfigs = emptyList(),
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
        override fun callV(methodName: String, args: List<Any?>): Boolean {
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
@CName("kanama_ios_runtime_script_resource_property_type")
fun kanamaIosRuntimeScriptResourcePropertyType(scriptHandle: Long, propertyIndex: Int): Int =
    KanamaIosRuntime.scriptResourcePropertyType(scriptHandle, propertyIndex)

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_resource_property_hint")
fun kanamaIosRuntimeScriptResourcePropertyHint(scriptHandle: Long, propertyIndex: Int): Int =
    KanamaIosRuntime.scriptResourcePropertyHint(scriptHandle, propertyIndex)

@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_resource_property_hint_string")
fun kanamaIosRuntimeScriptResourcePropertyHintString(
    scriptHandle: Long,
    propertyIndex: Int,
    buffer: CPointer<ByteVar>?,
    bufferSize: Int,
) {
    writeCString(KanamaIosRuntime.scriptResourcePropertyHintString(scriptHandle, propertyIndex), buffer, bufferSize)
}

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_resource_property_usage")
fun kanamaIosRuntimeScriptResourcePropertyUsage(scriptHandle: Long, propertyIndex: Int): Int =
    KanamaIosRuntime.scriptResourcePropertyUsage(scriptHandle, propertyIndex)

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
@CName("kanama_ios_runtime_script_resource_rpc_config_count")
fun kanamaIosRuntimeScriptResourceRpcConfigCount(scriptHandle: Long): Int =
    KanamaIosRuntime.scriptResourceRpcConfigCount(scriptHandle)

@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_resource_rpc_config_method_name")
fun kanamaIosRuntimeScriptResourceRpcConfigMethodName(
    scriptHandle: Long,
    rpcConfigIndex: Int,
    buffer: CPointer<ByteVar>?,
    bufferSize: Int,
) {
    writeCString(KanamaIosRuntime.scriptResourceRpcConfigMethodName(scriptHandle, rpcConfigIndex), buffer, bufferSize)
}

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_resource_rpc_config_mode")
fun kanamaIosRuntimeScriptResourceRpcConfigMode(scriptHandle: Long, rpcConfigIndex: Int): Int =
    KanamaIosRuntime.scriptResourceRpcConfigMode(scriptHandle, rpcConfigIndex)

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_resource_rpc_config_call_local")
fun kanamaIosRuntimeScriptResourceRpcConfigCallLocal(scriptHandle: Long, rpcConfigIndex: Int): Int =
    KanamaIosRuntime.scriptResourceRpcConfigCallLocal(scriptHandle, rpcConfigIndex)

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_resource_rpc_config_transfer_mode")
fun kanamaIosRuntimeScriptResourceRpcConfigTransferMode(scriptHandle: Long, rpcConfigIndex: Int): Int =
    KanamaIosRuntime.scriptResourceRpcConfigTransferMode(scriptHandle, rpcConfigIndex)

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_resource_rpc_config_channel")
fun kanamaIosRuntimeScriptResourceRpcConfigChannel(scriptHandle: Long, rpcConfigIndex: Int): Int =
    KanamaIosRuntime.scriptResourceRpcConfigChannel(scriptHandle, rpcConfigIndex)

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
@CName("kanama_ios_runtime_script_instance_set_property")
fun kanamaIosRuntimeScriptInstanceSetProperty(
    instanceHandle: Long,
    propertyIndex: Int,
    value: Long,
): Int =
    if (KanamaIosRuntime.setScriptInstanceProperty(instanceHandle, propertyIndex, value)) 1 else 0

@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_instance_get_property")
fun kanamaIosRuntimeScriptInstanceGetProperty(
    instanceHandle: Long,
    propertyIndex: Int,
    retTag: CPointer<IntVar>?,
    retBuf: CPointer<ByteVar>?,
): Int {
    retTag?.set(0, IOS_PT_VOID)
    val value = KanamaIosRuntime.getScriptInstanceProperty(instanceHandle, propertyIndex)
    if (value === KanamaIosNoProperty) return 0
    encodeIosReturn(value, retTag, retBuf)
    return if (retTag != null && retTag[0] != IOS_PT_VOID) 1 else 0
}

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

@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_instance_set_property_int_array")
fun kanamaIosRuntimeScriptInstanceSetPropertyIntArray(
    instanceHandle: Long,
    propertyIndex: Int,
    values: CPointer<LongVar>?,
    count: Int,
): Int {
    val ordinals = if (values == null || count <= 0) {
        LongArray(0)
    } else {
        LongArray(count) { i -> values[i] }
    }
    return if (KanamaIosRuntime.setScriptInstancePropertyIntArray(instanceHandle, propertyIndex, ordinals)) 1 else 0
}

@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_instance_set_property_string_array")
fun kanamaIosRuntimeScriptInstanceSetPropertyStringArray(
    instanceHandle: Long,
    propertyIndex: Int,
    strings: CPointer<CPointerVar<ByteVar>>?,
    count: Int,
): Int {
    val values: List<String> = if (strings == null || count <= 0) {
        emptyList()
    } else {
        List(count) { i -> strings[i]?.toKString() ?: "" }
    }
    return if (KanamaIosRuntime.setScriptInstancePropertyStringArray(instanceHandle, propertyIndex, values)) 1 else 0
}

// PT_* tags — must match the KANAMA_IOS_PT_* enum in kanama_ios_shim.c. NODE_PATH/STRING ship a
// null-terminated utf8 cstring; Vector2/3/Color ship contiguous float32, Vector2i contiguous
// int32; INT64/BOOL/FLOAT64/OBJECT ship their scalar in the buffer.
private const val IOS_PT_VOID = 0
private const val IOS_PT_BOOL = 1
private const val IOS_PT_INT64 = 3
private const val IOS_PT_FLOAT64 = 5
private const val IOS_PT_VECTOR2 = 6
private const val IOS_PT_VECTOR2I = 7
private const val IOS_PT_VECTOR3 = 8
private const val IOS_PT_COLOR = 11
private const val IOS_PT_OBJECT = 13
private const val IOS_PT_STRING = 16
private const val IOS_PT_NODE_PATH = 17
// Return-only (task 13): retBuf holds a pointer to a length-prefixed blob
// [int32 count]([int32 byteLen][byteLen utf8])* — the same layout the C read-back emits. The C
// side (kanama_ios_pt_return_to_variant) rebuilds a Godot PackedStringArray from it. Value 28 must
// match KANAMA_IOS_PT_PACKED_STRING_ARRAY appended at the end of the C PT enum.
private const val IOS_PT_PACKED_STRING_ARRAY = 28

// Return-only (task 29): remaining virtual-return families; values must match the C PT enum.
// RID reuses the C arg-side tag 14 (a single uint64 inline in retBuf).
private const val IOS_PT_RID = 14
// DICTIONARY/ARRAY: retBuf holds a pointer to a length-prefixed tagged-entry blob (see
// IosReturnContainerScratch). Fixed-element packed arrays: retBuf holds a pointer to a
// {int64 count, pointer data} desc followed by the flat element buffer (see
// IosReturnPackedDescScratch); PackedVector2Array/PackedColorArray reuse the C BUILD-arg
// tags 23/24 with the same desc layout.
private const val IOS_PT_PACKED_VECTOR2_ARRAY = 23
private const val IOS_PT_PACKED_COLOR_ARRAY = 24
private const val IOS_PT_DICTIONARY = 29
private const val IOS_PT_ARRAY = 30
private const val IOS_PT_PACKED_BYTE_ARRAY = 31
private const val IOS_PT_PACKED_INT32_ARRAY = 32
private const val IOS_PT_PACKED_INT64_ARRAY = 33
private const val IOS_PT_PACKED_FLOAT32_ARRAY = 34
private const val IOS_PT_PACKED_FLOAT64_ARRAY = 35
private const val IOS_PT_PACKED_VECTOR3_ARRAY = 36

// Decodes one PT-tagged inbound call arg (see kanama_ios_script_instance_call). OBJECT yields the
// raw int64 handle; the generated callV branch wraps it in the declared GodotObject subtype.
@OptIn(ExperimentalForeignApi::class)
internal fun decodeIosCallArg(tag: Int, ptr: CPointer<ByteVar>?): Any? {
    if (ptr == null) {
        return null
    }
    return when (tag) {
        IOS_PT_INT64, IOS_PT_OBJECT -> ptr.reinterpret<LongVar>()[0]
        IOS_PT_BOOL -> ptr[0].toInt() != 0
        IOS_PT_FLOAT64 -> ptr.reinterpret<DoubleVar>()[0]
        IOS_PT_VECTOR2 -> {
            val f = ptr.reinterpret<GodotRealVar>()
            Vector2(GodotReal.fromC(f[0]), GodotReal.fromC(f[1]))
        }
        IOS_PT_VECTOR2I -> {
            val n = ptr.reinterpret<IntVar>()
            Vector2i(n[0], n[1])
        }
        IOS_PT_VECTOR3 -> {
            val f = ptr.reinterpret<GodotRealVar>()
            Vector3(GodotReal.fromC(f[0]), GodotReal.fromC(f[1]), GodotReal.fromC(f[2]))
        }
        IOS_PT_COLOR -> {
            val f = ptr.reinterpret<FloatVar>()
            Color(f[0], f[1], f[2], f[3])
        }
        IOS_PT_STRING -> ptr.toKString()
        IOS_PT_NODE_PATH -> NodePath(ptr.toKString())
        else -> null
    }
}

@OptIn(ExperimentalForeignApi::class)
internal fun decodeIosPropertyValue(ptTag: Int, bytes: CPointer<ByteVar>?, length: Int): Any? {
    if (bytes == null) {
        return null
    }
    return when (ptTag) {
        IOS_PT_NODE_PATH -> NodePath(bytes.readBytes(if (length >= 0) length else 0).decodeToString())
        IOS_PT_VECTOR2 -> {
            val f = bytes.reinterpret<GodotRealVar>()
            Vector2(GodotReal.fromC(f[0]), GodotReal.fromC(f[1]))
        }
        IOS_PT_VECTOR3 -> {
            val f = bytes.reinterpret<GodotRealVar>()
            Vector3(GodotReal.fromC(f[0]), GodotReal.fromC(f[1]), GodotReal.fromC(f[2]))
        }
        IOS_PT_COLOR -> {
            val f = bytes.reinterpret<FloatVar>()
            Color(f[0], f[1], f[2], f[3])
        }
        else -> null
    }
}

@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_instance_set_property_value")
fun kanamaIosRuntimeScriptInstanceSetPropertyValue(
    instanceHandle: Long,
    propertyIndex: Int,
    ptTag: Int,
    bytes: CPointer<ByteVar>?,
    length: Int,
): Int {
    val value = decodeIosPropertyValue(ptTag, bytes, length) ?: return 0
    return if (KanamaIosRuntime.setScriptInstancePropertyValue(instanceHandle, propertyIndex, value)) 1 else 0
}

@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_instance_call_v")
fun kanamaIosRuntimeScriptInstanceCallV(
    instanceHandle: Long,
    methodIndex: Int,
    argTags: CPointer<IntVar>?,
    argPtrs: CPointer<COpaquePointerVar>?,
    argCount: Int,
    // Phase 5.3b return slot: the C callback passes a PT-tag out + a >=16-byte scratch. We set the
    // tag to a return PT kind and write the value's bytes when the method returns a marshalled
    // value; otherwise we leave it PT_VOID and the C side keeps the engine return nil.
    retTag: CPointer<IntVar>?,
    retBuf: CPointer<ByteVar>?,
): Int {
    val args: List<Any?> = if (argCount <= 0 || argTags == null || argPtrs == null) {
        emptyList()
    } else {
        List(argCount) { i -> decodeIosCallArg(argTags[i], argPtrs[i]?.reinterpret()) }
    }
    retTag?.set(0, IOS_PT_VOID)
    val rv = KanamaIosRuntime.callScriptInstanceReturning(instanceHandle, methodIndex, args)
    if (rv !== KanamaIosNoReturn) {
        encodeIosReturn(rv, retTag, retBuf)
        return 1
    }
    return if (KanamaIosRuntime.callScriptInstanceV(instanceHandle, methodIndex, args)) 1 else 0
}

// Encode a value-returning virtual/method result into the C return scratch as PT-tagged bytes,
// matching kanama_ios_pt_arg_to_variant's reads (Phase 5.3b). Unsupported/Unit -> PT_VOID.
@OptIn(ExperimentalForeignApi::class)
private fun encodeIosReturn(value: Any?, retTag: CPointer<IntVar>?, retBuf: CPointer<ByteVar>?) {
    if (retTag == null || retBuf == null) return
    when (value) {
        is Boolean -> { retBuf[0] = if (value) 1 else 0; retTag[0] = IOS_PT_BOOL }
        is Long -> { retBuf.reinterpret<LongVar>()[0] = value; retTag[0] = IOS_PT_INT64 }
        is Int -> { retBuf.reinterpret<LongVar>()[0] = value.toLong(); retTag[0] = IOS_PT_INT64 }
        is Double -> { retBuf.reinterpret<DoubleVar>()[0] = value; retTag[0] = IOS_PT_FLOAT64 }
        is Float -> { retBuf.reinterpret<DoubleVar>()[0] = value.toDouble(); retTag[0] = IOS_PT_FLOAT64 }
        is Vector2 -> {
            val f = retBuf.reinterpret<GodotRealVar>()
            f[0] = GodotReal.toC(value.x); f[1] = GodotReal.toC(value.y); retTag[0] = IOS_PT_VECTOR2
        }
        is Vector2i -> {
            val n = retBuf.reinterpret<IntVar>()
            n[0] = value.x; n[1] = value.y; retTag[0] = IOS_PT_VECTOR2I
        }
        // task 29 — Vector3 POD return (3x real_t, 12 bytes inline in the 32-byte scratch).
        is Vector3 -> {
            val f = retBuf.reinterpret<GodotRealVar>()
            f[0] = GodotReal.toC(value.x); f[1] = GodotReal.toC(value.y); f[2] = GodotReal.toC(value.z)
            retTag[0] = IOS_PT_VECTOR3
        }
        // task 29 — RID passthrough return (a single uint64 inline in the scratch).
        is RID -> { retBuf.reinterpret<LongVar>()[0] = value.value; retTag[0] = IOS_PT_RID }
        // Non-POD (task 13): a String return can exceed the fixed retBuf, so we hand back a
        // pointer to a persistent scratch buffer; the C side (kanama_ios_pt_return_to_variant)
        // builds the Godot String from it and destroys the temporary after copying.
        is String -> {
            val ptr = IosReturnStringScratch.encode(value)
            retBuf.reinterpret<CPointerVar<ByteVar>>()[0] = ptr
            retTag[0] = IOS_PT_STRING
        }
        // NodePath ships its path string like a String; the C pt_return_to_variant NODE_PATH case
        // builds a Godot NodePath from it. Makes NodePath @ScriptProperty readable (get parity).
        is NodePath -> {
            val ptr = IosReturnStringScratch.encode(value.path)
            retBuf.reinterpret<CPointerVar<ByteVar>>()[0] = ptr
            retTag[0] = IOS_PT_NODE_PATH
        }
        // task 29 — fixed-element packed-array returns: ship a {count, data} desc + flat element
        // buffer via a reused scratch; the C side rebuilds the matching Packed*Array from it.
        is ByteArray -> {
            retBuf.reinterpret<CPointerVar<ByteVar>>()[0] = IosReturnPackedDescScratch.encodeBytes(value)
            retTag[0] = IOS_PT_PACKED_BYTE_ARRAY
        }
        is IntArray -> {
            retBuf.reinterpret<CPointerVar<ByteVar>>()[0] = IosReturnPackedDescScratch.encodeInts(value)
            retTag[0] = IOS_PT_PACKED_INT32_ARRAY
        }
        is LongArray -> {
            retBuf.reinterpret<CPointerVar<ByteVar>>()[0] = IosReturnPackedDescScratch.encodeLongs(value)
            retTag[0] = IOS_PT_PACKED_INT64_ARRAY
        }
        is FloatArray -> {
            retBuf.reinterpret<CPointerVar<ByteVar>>()[0] = IosReturnPackedDescScratch.encodeFloats(value)
            retTag[0] = IOS_PT_PACKED_FLOAT32_ARRAY
        }
        is DoubleArray -> {
            retBuf.reinterpret<CPointerVar<ByteVar>>()[0] = IosReturnPackedDescScratch.encodeDoubles(value)
            retTag[0] = IOS_PT_PACKED_FLOAT64_ARRAY
        }
        // task 29 — Dictionary return (Map<String, Any?>): ship a tagged-entry blob; the C side
        // rebuilds the Godot Dictionary (String keys, audited scalar values, unaudited -> nil).
        is Map<*, *> -> {
            retBuf.reinterpret<CPointerVar<ByteVar>>()[0] = IosReturnContainerScratch.encodeDictionary(value)
            retTag[0] = IOS_PT_DICTIONARY
        }
        // List returns dispatch on the element type: String -> PackedStringArray (task 13 blob),
        // Vector2/Vector3/Color -> the matching Packed*Array desc (task 29), anything else
        // (including the empty list, which converts to any packed/typed array engine-side) -> a
        // generic Godot Array of audited tagged elements (task 29).
        is List<*> -> when (value.firstOrNull()) {
            is String -> {
                @Suppress("UNCHECKED_CAST")
                val ptr = IosReturnPackedStringArrayScratch.encode(value as List<String>)
                retBuf.reinterpret<CPointerVar<ByteVar>>()[0] = ptr
                retTag[0] = IOS_PT_PACKED_STRING_ARRAY
            }
            is Vector2 -> {
                @Suppress("UNCHECKED_CAST")
                retBuf.reinterpret<CPointerVar<ByteVar>>()[0] =
                    IosReturnPackedDescScratch.encodeVector2s(value as List<Vector2>)
                retTag[0] = IOS_PT_PACKED_VECTOR2_ARRAY
            }
            is Vector3 -> {
                @Suppress("UNCHECKED_CAST")
                retBuf.reinterpret<CPointerVar<ByteVar>>()[0] =
                    IosReturnPackedDescScratch.encodeVector3s(value as List<Vector3>)
                retTag[0] = IOS_PT_PACKED_VECTOR3_ARRAY
            }
            is Color -> {
                @Suppress("UNCHECKED_CAST")
                retBuf.reinterpret<CPointerVar<ByteVar>>()[0] =
                    IosReturnPackedDescScratch.encodeColors(value as List<Color>)
                retTag[0] = IOS_PT_PACKED_COLOR_ARRAY
            }
            else -> {
                retBuf.reinterpret<CPointerVar<ByteVar>>()[0] = IosReturnContainerScratch.encodeArray(value)
                retTag[0] = IOS_PT_ARRAY
            }
        }
        else -> retTag[0] = IOS_PT_VOID
    }
}

// Persistent scratch for String virtual/method returns (task 13). The C return path reads a
// `char *` out of the fixed 32-byte return buffer because a String payload can exceed it. The
// buffer is reused across calls (grown as needed) rather than per-call allocated: script-instance
// dispatch is synchronous on the main thread and C copies the bytes into a Godot String
// immediately, before the next encode can overwrite them — a nested engine call fully completes its
// own read before the outer encode runs. Mirrors the roadmap's "reuse PtrcallScratch-style
// thread-locals, not per-call arenas".
@OptIn(ExperimentalForeignApi::class)
private object IosReturnStringScratch {
    private var buf: CPointer<ByteVar>? = null
    private var capacity: Int = 0

    fun encode(value: String): CPointer<ByteVar> {
        val bytes = value.encodeToByteArray()
        val needed = bytes.size + 1
        val existing = buf
        val b: CPointer<ByteVar> = if (existing == null || capacity < needed) {
            if (existing != null) nativeHeap.free(existing)
            val fresh = nativeHeap.allocArray<ByteVar>(needed)
            buf = fresh
            capacity = needed
            fresh
        } else {
            existing
        }
        for (i in bytes.indices) b[i] = bytes[i]
        b[bytes.size] = 0
        return b
    }

    // Kotlin-side width self-test hook (task 13): encode [value] and read it back through the same
    // pointer the C side would, returning the decoded string so the OBJECTCALLS SELFTEST can assert
    // a long (>32-byte) String survives the fixed-scratch return path uncorrupted.
    fun selfTestRoundTrip(value: String): String = encode(value).toKString()
}

/** OBJECTCALLS-SELFTEST hook for the String virtual-return encoder (task 13). See scratch above. */
@OptIn(ExperimentalForeignApi::class)
internal fun kanamaIosVirtualStringReturnSelfTest(value: String): String =
    IosReturnStringScratch.selfTestRoundTrip(value)

// Persistent scratch for PackedStringArray (List<String>) virtual/method returns (task 13). Encodes
// the list into the length-prefixed blob [int32 count]([int32 byteLen][byteLen utf8])* — the same
// layout the C read-back produces — into a reused buffer (grown as needed), and hands the C return
// path a pointer to it. Int32 fields are written little-endian byte-by-byte because the per-element
// length prefixes land at unaligned offsets (variable-length strings); the C side reads them with
// memcpy, so both agree on this arm64 (little-endian) target.
@OptIn(ExperimentalForeignApi::class)
private object IosReturnPackedStringArrayScratch {
    private var buf: CPointer<ByteVar>? = null
    private var capacity: Int = 0

    private fun ensure(needed: Int): CPointer<ByteVar> {
        val existing = buf
        return if (existing == null || capacity < needed) {
            if (existing != null) nativeHeap.free(existing)
            val fresh = nativeHeap.allocArray<ByteVar>(needed)
            buf = fresh
            capacity = needed
            fresh
        } else {
            existing
        }
    }

    private fun putInt32LE(b: CPointer<ByteVar>, off: Int, v: Int) {
        b[off] = (v and 0xFF).toByte()
        b[off + 1] = ((v ushr 8) and 0xFF).toByte()
        b[off + 2] = ((v ushr 16) and 0xFF).toByte()
        b[off + 3] = ((v ushr 24) and 0xFF).toByte()
    }

    fun encode(values: List<String>): CPointer<ByteVar> {
        val encoded = values.map { it.encodeToByteArray() }
        var needed = 4 // count header
        for (e in encoded) needed += 4 + e.size
        if (needed < 4) needed = 4
        val b = ensure(needed)
        putInt32LE(b, 0, values.size)
        var off = 4
        for (e in encoded) {
            putInt32LE(b, off, e.size)
            off += 4
            for (i in e.indices) b[off + i] = e[i]
            off += e.size
        }
        return b
    }

    // Width self-test hook (task 13): encode [values] and parse the blob back through the same
    // pointer the C side reads, so the OBJECTCALLS SELFTEST can assert element boundaries survive
    // (including a >16B element and an empty list).
    fun selfTestRoundTrip(values: List<String>): List<String> {
        val b = encode(values)
        fun int32LE(off: Int): Int =
            (b[off].toInt() and 0xFF) or ((b[off + 1].toInt() and 0xFF) shl 8) or
                ((b[off + 2].toInt() and 0xFF) shl 16) or ((b[off + 3].toInt() and 0xFF) shl 24)
        val count = int32LE(0)
        val out = ArrayList<String>(count)
        var off = 4
        repeat(count) {
            val len = int32LE(off); off += 4
            val bytes = ByteArray(len) { i -> b[off + i] }
            off += len
            out.add(bytes.decodeToString())
        }
        return out
    }
}

/** OBJECTCALLS-SELFTEST hook for the PackedStringArray virtual-return encoder (task 13). */
@OptIn(ExperimentalForeignApi::class)
internal fun kanamaIosVirtualPackedStringArrayReturnSelfTest(values: List<String>): List<String> =
    IosReturnPackedStringArrayScratch.selfTestRoundTrip(values)

// Persistent scratch for the fixed-element packed-array virtual returns (task 29). Encodes a
// KanamaIosPackedArgDesc header {int64 count, pointer data} followed by the flat element buffer
// into one reused allocation (data points 16 bytes past the header). Same buffer-reuse contract
// as IosReturnStringScratch: dispatch is synchronous and C copies elements out (push_back per
// element) before the next encode can overwrite them. Header and elements are written
// byte-by-byte little-endian (matching this arm64 target); the C side memcpy's per element, so
// no alignment is assumed beyond the malloc'd base the desc struct itself needs.
@OptIn(ExperimentalForeignApi::class)
private object IosReturnPackedDescScratch {
    private var buf: CPointer<ByteVar>? = null
    private var capacity: Int = 0

    private fun ensure(needed: Int): CPointer<ByteVar> {
        val existing = buf
        return if (existing == null || capacity < needed) {
            if (existing != null) nativeHeap.free(existing)
            val fresh = nativeHeap.allocArray<ByteVar>(needed)
            buf = fresh
            capacity = needed
            fresh
        } else {
            existing
        }
    }

    private fun putInt32LE(b: CPointer<ByteVar>, off: Int, v: Int) {
        b[off] = (v and 0xFF).toByte()
        b[off + 1] = ((v ushr 8) and 0xFF).toByte()
        b[off + 2] = ((v ushr 16) and 0xFF).toByte()
        b[off + 3] = ((v ushr 24) and 0xFF).toByte()
    }

    private fun putInt64LE(b: CPointer<ByteVar>, off: Int, v: Long) {
        for (i in 0 until 8) b[off + i] = ((v ushr (i * 8)) and 0xFF).toByte()
    }

    private fun header(count: Int, elemBytes: Int): CPointer<ByteVar> {
        var needed = 16 + count * elemBytes
        if (needed < 16) needed = 16
        val b = ensure(needed)
        putInt64LE(b, 0, count.toLong())
        putInt64LE(b, 8, b.toLong() + 16L)
        return b
    }

    fun encodeBytes(values: ByteArray): CPointer<ByteVar> {
        val b = header(values.size, 1)
        for (i in values.indices) b[16 + i] = values[i]
        return b
    }

    fun encodeInts(values: IntArray): CPointer<ByteVar> {
        val b = header(values.size, 4)
        for (i in values.indices) putInt32LE(b, 16 + i * 4, values[i])
        return b
    }

    fun encodeLongs(values: LongArray): CPointer<ByteVar> {
        val b = header(values.size, 8)
        for (i in values.indices) putInt64LE(b, 16 + i * 8, values[i])
        return b
    }

    fun encodeFloats(values: FloatArray): CPointer<ByteVar> {
        val b = header(values.size, 4)
        for (i in values.indices) putInt32LE(b, 16 + i * 4, values[i].toRawBits())
        return b
    }

    fun encodeDoubles(values: DoubleArray): CPointer<ByteVar> {
        val b = header(values.size, 8)
        for (i in values.indices) putInt64LE(b, 16 + i * 8, values[i].toRawBits())
        return b
    }

    fun encodeVector2s(values: List<Vector2>): CPointer<ByteVar> {
        val b = header(values.size, 8)
        for (i in values.indices) {
            putInt32LE(b, 16 + i * 8, GodotReal.toC(values[i].x).toRawBits())
            putInt32LE(b, 16 + i * 8 + 4, GodotReal.toC(values[i].y).toRawBits())
        }
        return b
    }

    fun encodeVector3s(values: List<Vector3>): CPointer<ByteVar> {
        val b = header(values.size, 12)
        for (i in values.indices) {
            putInt32LE(b, 16 + i * 12, GodotReal.toC(values[i].x).toRawBits())
            putInt32LE(b, 16 + i * 12 + 4, GodotReal.toC(values[i].y).toRawBits())
            putInt32LE(b, 16 + i * 12 + 8, GodotReal.toC(values[i].z).toRawBits())
        }
        return b
    }

    fun encodeColors(values: List<Color>): CPointer<ByteVar> {
        val b = header(values.size, 16)
        for (i in values.indices) {
            putInt32LE(b, 16 + i * 16, values[i].r.toRawBits())
            putInt32LE(b, 16 + i * 16 + 4, values[i].g.toRawBits())
            putInt32LE(b, 16 + i * 16 + 8, values[i].b.toRawBits())
            putInt32LE(b, 16 + i * 16 + 12, values[i].a.toRawBits())
        }
        return b
    }

    // Width self-test hook (task 29): parse the encoded desc back the way the C side reads it
    // (count from the header, data pointer, elemBytes-wide elements) and return the raw element
    // bytes so the OBJECTCALLS SELFTEST can assert widths and boundaries per family.
    fun selfTestReadBack(desc: CPointer<ByteVar>, elemBytes: Int): Pair<Long, ByteArray> {
        fun int64LE(off: Int): Long {
            var v = 0L
            for (i in 0 until 8) v = v or ((desc[off + i].toLong() and 0xFF) shl (i * 8))
            return v
        }
        val count = int64LE(0)
        val data = int64LE(8)
        // data must point 16 bytes past the desc base (the single-allocation contract).
        if (data != desc.toLong() + 16L) return Pair(-1L, ByteArray(0))
        val total = (count * elemBytes).toInt()
        val bytes = ByteArray(total) { i -> desc[16 + i] }
        return Pair(count, bytes)
    }
}

// Persistent scratch for Dictionary/Array virtual returns (task 29). Encodes the container into a
// length-prefixed tagged blob the C side rebuilds —
// Dictionary: [int32 count]([int32 keyLen][key utf8][int32 valTag][int32 valLen][val bytes])*
// Array:      [int32 count]([int32 elemTag][int32 elemLen][elem bytes])*
// Values use the audited scalar encodings of kanama_ios_pt_blob_value_to_variant; an unaudited
// value ships as PT_VOID/nil (the same policy as the Variant-return dispatch). Same buffer-reuse
// contract as the other return scratches.
@OptIn(ExperimentalForeignApi::class)
private object IosReturnContainerScratch {
    private var buf: CPointer<ByteVar>? = null
    private var capacity: Int = 0

    private fun ensure(needed: Int): CPointer<ByteVar> {
        val existing = buf
        return if (existing == null || capacity < needed) {
            if (existing != null) nativeHeap.free(existing)
            val fresh = nativeHeap.allocArray<ByteVar>(needed)
            buf = fresh
            capacity = needed
            fresh
        } else {
            existing
        }
    }

    private fun putInt32LE(dest: ByteArray, off: Int, v: Int) {
        dest[off] = (v and 0xFF).toByte()
        dest[off + 1] = ((v ushr 8) and 0xFF).toByte()
        dest[off + 2] = ((v ushr 16) and 0xFF).toByte()
        dest[off + 3] = ((v ushr 24) and 0xFF).toByte()
    }

    private fun int64Bytes(v: Long): ByteArray = ByteArray(8) { i -> ((v ushr (i * 8)) and 0xFF).toByte() }

    private fun float32Bytes(vararg components: Float): ByteArray {
        val out = ByteArray(components.size * 4)
        for ((i, c) in components.withIndex()) putInt32LE(out, i * 4, c.toRawBits())
        return out
    }

    /** PT tag + payload bytes for one audited container value; unaudited -> PT_VOID/nil. */
    internal fun taggedValue(value: Any?): Pair<Int, ByteArray> = when (value) {
        null -> Pair(IOS_PT_VOID, ByteArray(0))
        is Boolean -> Pair(IOS_PT_BOOL, byteArrayOf(if (value) 1 else 0))
        is Int -> Pair(IOS_PT_INT64, int64Bytes(value.toLong()))
        is Long -> Pair(IOS_PT_INT64, int64Bytes(value))
        is Float -> Pair(IOS_PT_FLOAT64, int64Bytes(value.toDouble().toRawBits()))
        is Double -> Pair(IOS_PT_FLOAT64, int64Bytes(value.toRawBits()))
        is String -> Pair(IOS_PT_STRING, value.encodeToByteArray())
        is Vector2 -> Pair(IOS_PT_VECTOR2, float32Bytes(GodotReal.toC(value.x), GodotReal.toC(value.y)))
        is Vector2i -> {
            val out = ByteArray(8)
            putInt32LE(out, 0, value.x)
            putInt32LE(out, 4, value.y)
            Pair(IOS_PT_VECTOR2I, out)
        }
        is Vector3 -> Pair(
            IOS_PT_VECTOR3,
            float32Bytes(GodotReal.toC(value.x), GodotReal.toC(value.y), GodotReal.toC(value.z)),
        )
        is Color -> Pair(IOS_PT_COLOR, float32Bytes(value.r, value.g, value.b, value.a))
        is RID -> Pair(IOS_PT_RID, int64Bytes(value.value))
        else -> Pair(IOS_PT_VOID, ByteArray(0))
    }

    fun encodeDictionary(map: Map<*, *>): CPointer<ByteVar> {
        val entries = map.entries.map { (k, v) ->
            Pair((k as? String ?: k.toString()).encodeToByteArray(), taggedValue(v))
        }
        var needed = 4
        for ((key, tagged) in entries) needed += 4 + key.size + 8 + tagged.second.size
        val b = ensure(needed)
        var off = 0
        fun putInt32(v: Int) {
            b[off] = (v and 0xFF).toByte()
            b[off + 1] = ((v ushr 8) and 0xFF).toByte()
            b[off + 2] = ((v ushr 16) and 0xFF).toByte()
            b[off + 3] = ((v ushr 24) and 0xFF).toByte()
            off += 4
        }
        fun putBytes(bytes: ByteArray) {
            for (i in bytes.indices) b[off + i] = bytes[i]
            off += bytes.size
        }
        putInt32(entries.size)
        for ((key, tagged) in entries) {
            putInt32(key.size)
            putBytes(key)
            putInt32(tagged.first)
            putInt32(tagged.second.size)
            putBytes(tagged.second)
        }
        return b
    }

    fun encodeArray(values: List<*>): CPointer<ByteVar> {
        val elements = values.map { taggedValue(it) }
        var needed = 4
        for (tagged in elements) needed += 8 + tagged.second.size
        val b = ensure(needed)
        var off = 0
        fun putInt32(v: Int) {
            b[off] = (v and 0xFF).toByte()
            b[off + 1] = ((v ushr 8) and 0xFF).toByte()
            b[off + 2] = ((v ushr 16) and 0xFF).toByte()
            b[off + 3] = ((v ushr 24) and 0xFF).toByte()
            off += 4
        }
        fun putBytes(bytes: ByteArray) {
            for (i in bytes.indices) b[off + i] = bytes[i]
            off += bytes.size
        }
        putInt32(elements.size)
        for (tagged in elements) {
            putInt32(tagged.first)
            putInt32(tagged.second.size)
            putBytes(tagged.second)
        }
        return b
    }

    // Width self-test hook (task 29): parse an encoded blob back the way the C side reads it —
    // a list of (tag, payload) with dictionary entries also carrying their key. Returned as
    // "key=tag:len" / "tag:len" summaries plus the raw payloads for byte-exact checks.
    fun selfTestParse(blob: CPointer<ByteVar>, isDictionary: Boolean): List<Triple<String, Int, ByteArray>> {
        var off = 0
        fun int32LE(): Int {
            val v = (blob[off].toInt() and 0xFF) or ((blob[off + 1].toInt() and 0xFF) shl 8) or
                ((blob[off + 2].toInt() and 0xFF) shl 16) or ((blob[off + 3].toInt() and 0xFF) shl 24)
            off += 4
            return v
        }
        fun bytes(n: Int): ByteArray {
            val out = ByteArray(n) { i -> blob[off + i] }
            off += n
            return out
        }
        val count = int32LE()
        val out = ArrayList<Triple<String, Int, ByteArray>>(count)
        repeat(count) {
            val key = if (isDictionary) bytes(int32LE()).decodeToString() else ""
            val tag = int32LE()
            val len = int32LE()
            out.add(Triple(key, tag, bytes(len)))
        }
        return out
    }
}

/**
 * OBJECTCALLS-SELFTEST hooks for the task-29 return encoders. Each encodes through the same
 * scratch the live return path uses and parses the buffer back the way the C side reads it,
 * so the on-device rows assert element widths and boundaries without needing an engine call.
 */
@OptIn(ExperimentalForeignApi::class)
internal fun kanamaIosVirtualPackedIntsReturnSelfTest(values: IntArray): List<Int> {
    val (count, bytes) = IosReturnPackedDescScratch.selfTestReadBack(
        IosReturnPackedDescScratch.encodeInts(values), 4)
    if (count.toInt() != values.size) return emptyList()
    return List(values.size) { i ->
        (bytes[i * 4].toInt() and 0xFF) or ((bytes[i * 4 + 1].toInt() and 0xFF) shl 8) or
            ((bytes[i * 4 + 2].toInt() and 0xFF) shl 16) or ((bytes[i * 4 + 3].toInt() and 0xFF) shl 24)
    }
}

@OptIn(ExperimentalForeignApi::class)
internal fun kanamaIosVirtualPackedLongsReturnSelfTest(values: LongArray): List<Long> {
    val (count, bytes) = IosReturnPackedDescScratch.selfTestReadBack(
        IosReturnPackedDescScratch.encodeLongs(values), 8)
    if (count.toInt() != values.size) return emptyList()
    return List(values.size) { i ->
        var v = 0L
        for (j in 0 until 8) v = v or ((bytes[i * 8 + j].toLong() and 0xFF) shl (j * 8))
        v
    }
}

@OptIn(ExperimentalForeignApi::class)
internal fun kanamaIosVirtualPackedBytesReturnSelfTest(values: ByteArray): ByteArray {
    val (count, bytes) = IosReturnPackedDescScratch.selfTestReadBack(
        IosReturnPackedDescScratch.encodeBytes(values), 1)
    return if (count.toInt() == values.size) bytes else ByteArray(0)
}

@OptIn(ExperimentalForeignApi::class)
internal fun kanamaIosVirtualPackedDoublesReturnSelfTest(values: DoubleArray): List<Double> {
    val (count, bytes) = IosReturnPackedDescScratch.selfTestReadBack(
        IosReturnPackedDescScratch.encodeDoubles(values), 8)
    if (count.toInt() != values.size) return emptyList()
    return List(values.size) { i ->
        var v = 0L
        for (j in 0 until 8) v = v or ((bytes[i * 8 + j].toLong() and 0xFF) shl (j * 8))
        Double.fromBits(v)
    }
}

@OptIn(ExperimentalForeignApi::class)
internal fun kanamaIosVirtualPackedVector3ReturnSelfTest(values: List<Vector3>): List<Vector3> {
    val (count, bytes) = IosReturnPackedDescScratch.selfTestReadBack(
        IosReturnPackedDescScratch.encodeVector3s(values), 12)
    if (count.toInt() != values.size) return emptyList()
    fun f32(off: Int): Float {
        val bits = (bytes[off].toInt() and 0xFF) or ((bytes[off + 1].toInt() and 0xFF) shl 8) or
            ((bytes[off + 2].toInt() and 0xFF) shl 16) or ((bytes[off + 3].toInt() and 0xFF) shl 24)
        return Float.fromBits(bits)
    }
    return List(values.size) { i ->
        Vector3(
            GodotReal.fromC(f32(i * 12)),
            GodotReal.fromC(f32(i * 12 + 4)),
            GodotReal.fromC(f32(i * 12 + 8)),
        )
    }
}

// Dictionary/Array blob round-trips: returns "key=tag:payloadLen" / "tag:payloadLen" summaries in
// encode order so the row can assert entry boundaries, tag routing, and payload widths.
@OptIn(ExperimentalForeignApi::class)
internal fun kanamaIosVirtualDictionaryReturnSelfTest(map: Map<String, Any?>): List<String> =
    IosReturnContainerScratch.selfTestParse(IosReturnContainerScratch.encodeDictionary(map), true)
        .map { (key, tag, payload) -> "$key=$tag:${payload.size}" }

@OptIn(ExperimentalForeignApi::class)
internal fun kanamaIosVirtualArrayReturnSelfTest(values: List<Any?>): List<String> =
    IosReturnContainerScratch.selfTestParse(IosReturnContainerScratch.encodeArray(values), false)
        .map { (_, tag, payload) -> "$tag:${payload.size}" }

// OBJECTCALLS-SELFTEST hook for Variant virtual returns (task 13). A Variant-returning virtual hands
// back an Any?; encodeIosReturn dispatches on its runtime type to the matching PT tag (the C side
// then builds the concrete Variant). Returns the PT tag chosen for [value] so the self-test can
// assert the routing (e.g. String -> STRING(16), Long -> INT64(3), null -> VOID(0) = nil Variant).
@OptIn(ExperimentalForeignApi::class)
internal fun kanamaIosVariantReturnSelfTest(value: Any?): Int = memScoped {
    val tag = alloc<IntVar>()
    val buf = allocArray<ByteVar>(32)
    tag.value = IOS_PT_VOID
    encodeIosReturn(value, tag.ptr, buf)
    tag.value
}

@OptIn(ExperimentalNativeApi::class)
@CName("kanama_ios_runtime_script_instance_free")
fun kanamaIosRuntimeScriptInstanceFree(instanceHandle: Long) {
    KanamaIosRuntime.freeScriptInstance(instanceHandle)
}

@PublishedApi
internal fun iosScriptInstanceForOwner(ownerObject: Long): Any? =
    KanamaIosRuntime.scriptInstanceForOwner(ownerObject)
