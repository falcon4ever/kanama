package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for MultiplayerSpawner (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP MultiplayerSpawner waits on: ptrcallNoArgsRetCallable
// Index: docs/reference/generated/ios-shape-gap.md

fun MultiplayerSpawner.getSpawnFunction(): GodotCallable? {
    return ObjectCalls.ptrcallNoArgsRetCallable(getSpawnFunctionBind, handle)
}

val MultiplayerSpawner.spawnFunction: GodotCallable?
    @JvmName("spawnFunctionProperty")
    get() = getSpawnFunction()

private const val GET_SPAWN_FUNCTION_HASH = 1307783378L
private val getSpawnFunctionBind by lazy {
    ObjectCalls.getMethodBind("MultiplayerSpawner", "get_spawn_function", GET_SPAWN_FUNCTION_HASH)
}
