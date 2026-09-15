package net.multigesture.kanama.api

/**
 * Describes a Godot Callable as a target object plus method name.
 *
 * Mirrors the desktop `GodotCallable`. Generated wrappers pass this object+method form to
 * Callable-argument methods; the iOS ptrcall dispatch builds the engine Callable via constructor
 * index 2 and destroys it after the call (see the PT_CALLABLE path in `kanama_ios_shim.c`; the
 * callable-args design record (task 09) lives in the task repo, not under docs/).
 */
data class GodotCallable(
    val target: GodotObject,
    val method: String,
)
