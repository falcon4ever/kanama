package net.multigesture.kanama.ios

import kotlinx.cinterop.ExperimentalForeignApi
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_set_first_node_in_group_text
import kotlin.experimental.ExperimentalNativeApi
import kotlin.native.CName

private object KanamaIosRuntime {
    private const val PROBE_GROUP = "kanama_ios_probe"

    private var initialized = false
    private var frameCount = 0
    private var probeLabelUpdated = false

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

    private fun log(message: String) {
        println("[kanama][ios][kn] $message")
    }
}

@OptIn(ExperimentalForeignApi::class)
private object KanamaIosGodot {
    fun setFirstNodeInGroupText(groupName: String, value: String): Boolean =
        kanama_ios_godot_set_first_node_in_group_text(groupName, value) != 0
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
