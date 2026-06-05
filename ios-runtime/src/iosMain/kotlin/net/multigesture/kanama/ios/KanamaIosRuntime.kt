package net.multigesture.kanama.ios

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toKString
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_get_method_bind
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_ptrcall_string_arg
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_set_first_node_in_group_text
import kotlin.experimental.ExperimentalNativeApi
import kotlin.native.CName

private object KanamaIosRuntime {
    private const val PROBE_GROUP = "kanama_ios_probe"
    private const val LABEL_SET_TEXT_HASH = 83702148L

    private var initialized = false
    private var frameCount = 0
    private var probeLabelUpdated = false
    private var nextHandle = 1L
    private var labelSetTextBind = 0L
    private val scriptResources = linkedMapOf<Long, ProbeScriptResource>()
    private val scriptInstances = linkedMapOf<Long, ProbeScriptInstance>()

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
            log("cleared iOS script probe state instances=$instances resources=$resources")
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
        val handle = nextHandle++
        scriptResources[handle] = ProbeScriptResource(path = path.orEmpty())
        log("created script resource handle=$handle path=${path.orEmpty()}")
        return handle
    }

    fun freeScriptResource(handle: Long) {
        scriptResources.remove(handle)
        log("freed script resource handle=$handle")
    }

    fun createScriptInstance(scriptHandle: Long, ownerObject: Long): Long {
        if (ownerObject == 0L) {
            log("refusing script instance for null owner script=$scriptHandle")
            return 0
        }
        val handle = nextHandle++
        scriptInstances[handle] = ProbeScriptInstance(
            scriptHandle = scriptHandle,
            ownerObject = ownerObject,
        )
        log(
            "created script instance handle=$handle script=$scriptHandle " +
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
        if (instance.readyCalled) {
            return
        }
        instance.readyCalled = true
        if (KanamaIosGodot.setObjectText(instance.ownerObject, "Kanama iOS Kotlin/Native script ready")) {
            log(
                "script instance ready handle=$handle " +
                    "owner=0x${instance.ownerObject.toULong().toString(16)}",
            )
        } else {
            log(
                "script instance ready failed to update owner handle=$handle " +
                    "owner=0x${instance.ownerObject.toULong().toString(16)}",
            )
        }
    }

    fun freeScriptInstance(handle: Long) {
        scriptInstances.remove(handle)
        log("freed script instance handle=$handle")
    }

    private fun log(message: String) {
        println("[kanama][ios][kn] $message")
    }

    private data class ProbeScriptResource(
        val path: String,
    )

    private data class ProbeScriptInstance(
        val scriptHandle: Long,
        val ownerObject: Long,
        var readyCalled: Boolean = false,
    )

    fun labelSetTextBind(): Long {
        if (labelSetTextBind == 0L) {
            labelSetTextBind = KanamaIosGodot.getMethodBind("Label", "set_text", LABEL_SET_TEXT_HASH)
        }
        return labelSetTextBind
    }
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
@CName("kanama_ios_runtime_script_instance_free")
fun kanamaIosRuntimeScriptInstanceFree(instanceHandle: Long) {
    KanamaIosRuntime.freeScriptInstance(instanceHandle)
}
