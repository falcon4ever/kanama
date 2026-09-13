package net.multigesture.kanama.api

import net.multigesture.kanama.binding.ScriptBridge

/**
 * Returns the Kotlin object backing this Kanama script instance, when the object has one.
 */
inline fun <reified T> GodotObject.kotlinScriptInstance(): T? =
    ScriptBridge.kotlinObjectForOwner(handle.segment) as? T

/**
 * Returns the Kotlin object backing this node's Kanama script instance, when the object has one.
 */
inline fun <reified T> Node.kotlinScriptInstance(): T? =
    GodotObject(handle).kotlinScriptInstance<T>()
