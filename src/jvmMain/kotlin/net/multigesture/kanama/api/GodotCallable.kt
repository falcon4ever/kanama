package net.multigesture.kanama.api

/**
 * Describes a Godot Callable as a target object plus method name.
 */
data class GodotCallable(
    val target: GodotObject,
    val method: String,
)
