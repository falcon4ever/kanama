package net.multigesture.kanama.api

/**
 * Describes a Godot Callable as a target object plus method name.
 *
 * Written once for every backend (task 117 P3′, D20). Generated wrappers pass this object+method
 * form to Callable-argument methods; the iOS ptrcall dispatch builds the engine Callable via
 * constructor index 2 and destroys it after the call (see the PT_CALLABLE path in
 * `kanama_ios_shim.c`; the callable-args design record (task 09) lives in the task repo, not under
 * docs/).
 */
data class GodotCallable(
    val target: GodotObject,
    val method: String,
) {
    // Godot's Callable methods for this object+method form (task 134 D2). A standard Callable's
    // `call`, `call_deferred`, `get_argument_count` and `rpc` are the target's Object calls with the
    // method name (variant_call.cpp / callable.cpp), so each is that call here. `bind`/`unbind`
    // and the bound-argument queries are not offered: a GodotCallable carries no bound arguments.

    /** Godot's `Callable.call`: `target.call(method, *args)`. */
    fun call(vararg args: Any?): Any? = target.call(method, *args)

    /** Godot's `Callable.callv`: `target.callv(method, arguments)`. */
    fun callv(arguments: List<Any?>): Any? = target.callv(method, arguments)

    /** Godot's `Callable.call_deferred`: the call at the end of the frame (`Object.call_deferred`). */
    fun callDeferred(vararg args: Any?) {
        target.callDeferred(method, *args)
    }

    /** Godot's `Callable.is_valid`: the target is alive and has [method]. */
    fun isValid(): Boolean = GD.isInstanceValid(target) && target.hasMethod(method)

    /** Godot's `Callable.get_object`: the target, or null once it is freed. */
    fun getObject(): GodotObject? = if (GD.isInstanceValid(target)) target else null

    /** Godot's `Callable.get_object_id`: the target's instance id, also once it is freed (as in Godot). */
    fun getObjectId(): Long = target.instanceId

    /** Godot's `Callable.get_argument_count`: [method]'s argument count on the target. */
    fun getArgumentCount(): Long = target.getMethodArgumentCount(method)

    /**
     * Godot's `Callable.rpc`: the target node's `rpc(method, *args)`. On a target that is not a
     * `Node` both report a call error (here Godot's missing-method error for `rpc`).
     */
    fun rpc(vararg args: Any?) {
        target.call("rpc", method, *args)
    }

    /** Godot's `Callable.rpc_id`: the target node's `rpc_id(peerId, method, *args)`. */
    fun rpcId(peerId: Long, vararg args: Any?) {
        target.call("rpc_id", peerId, method, *args)
    }
}
