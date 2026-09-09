package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for MultiplayerAPI (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP MultiplayerAPI waits on: ptrcallWithIntObjectStringNameArrayArgsRetLong,
//   ptrcallWithObjectAndVariantArgRetLong
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sends an RPC to the target `peer`. The given `method` will be called on the remote `object` with
 * the provided `arguments`. The RPC may also be called locally depending on the implementation and
 * RPC configuration. See `Node.rpc` and `Node.rpc_config`. Note: Prefer using `Node.rpc`,
 * `Node.rpc_id`, or `my_method.rpc(peer, arg1, arg2, ...)` (in GDScript), since they are faster.
 * This method is mostly useful in conjunction with `MultiplayerAPIExtension` when extending or
 * replacing the multiplayer capabilities.
 *
 * Generated from Godot docs: MultiplayerAPI.rpc
 */
fun MultiplayerAPI.rpc(peer: Int, objectValue: GodotObject, method: String, arguments: List<Any?> = emptyList()): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithIntObjectStringNameArrayArgsRetLong(rpcBind, handle, peer, objectValue.handle, method, arguments)
}

/**
 * Notifies the MultiplayerAPI of a new `configuration` for the given `object`. This method is used
 * internally by `SceneTree` to configure the root path for this MultiplayerAPI (passing `null` and
 * a valid `NodePath` as `configuration`). This method can be further used by MultiplayerAPI
 * implementations to provide additional features, refer to specific implementation (e.g.
 * `SceneMultiplayer`) for details on how they use it. Note: This method is mostly relevant when
 * extending or overriding the MultiplayerAPI behavior via `MultiplayerAPIExtension`.
 *
 * Generated from Godot docs: MultiplayerAPI.object_configuration_add
 */
fun MultiplayerAPI.objectConfigurationAdd(objectValue: GodotObject, configuration: Any?): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithObjectAndVariantArgRetLong(objectConfigurationAddBind, handle, objectValue.handle, configuration)
}

/**
 * Notifies the MultiplayerAPI to remove a `configuration` for the given `object`. This method is
 * used internally by `SceneTree` to configure the root path for this MultiplayerAPI (passing
 * `null` and an empty `NodePath` as `configuration`). This method can be further used by
 * MultiplayerAPI implementations to provide additional features, refer to specific implementation
 * (e.g. `SceneMultiplayer`) for details on how they use it. Note: This method is mostly relevant
 * when extending or overriding the MultiplayerAPI behavior via `MultiplayerAPIExtension`.
 *
 * Generated from Godot docs: MultiplayerAPI.object_configuration_remove
 */
fun MultiplayerAPI.objectConfigurationRemove(objectValue: GodotObject, configuration: Any?): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithObjectAndVariantArgRetLong(objectConfigurationRemoveBind, handle, objectValue.handle, configuration)
}

private const val RPC_HASH = 2077486355L
private val rpcBind by lazy {
    ObjectCalls.getMethodBind("MultiplayerAPI", "rpc", RPC_HASH)
}

private const val OBJECT_CONFIGURATION_ADD_HASH = 1171879464L
private val objectConfigurationAddBind by lazy {
    ObjectCalls.getMethodBind("MultiplayerAPI", "object_configuration_add", OBJECT_CONFIGURATION_ADD_HASH)
}

private const val OBJECT_CONFIGURATION_REMOVE_HASH = 1171879464L
private val objectConfigurationRemoveBind by lazy {
    ObjectCalls.getMethodBind("MultiplayerAPI", "object_configuration_remove", OBJECT_CONFIGURATION_REMOVE_HASH)
}
