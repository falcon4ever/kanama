package net.multigesture.kanama.api

import net.multigesture.kanama.binding.KanamaScript

/**
 * Creates a brand-new, engine-backed script resource of type [T] purely from Kotlin.
 *
 * [T] must be a custom resource declared with `@ScriptClass(attachTo = "Resource")`
 * (and, for its `res://` path to be discoverable, `@GlobalClass` with a matching
 * file name). This is the programmatic equivalent of GDScript's `MyResource.new()`
 * or C#'s `new MyResource()` — it constructs a fresh engine `Resource`, attaches the
 * Kanama script, and returns the live Kotlin instance:
 *
 * ```kotlin
 * val weapon = newScriptInstance<Weapon>()
 * weapon.damage = 25
 * ResourceSaver.save(Resource.fromHandle(weapon.godotObject), "res://weapon.tres")
 * ```
 *
 * Ownership: the returned resource comes back with one owning reference (like
 * `.new()`), so it stays valid across a `ResourceSaver.save`. Lifetime is manual —
 * keep it by assigning it to an exported `Resource` slot / saving it, or it will
 * persist until process shutdown. Use [Resource.fromHandle] for the non-owning view
 * that `ResourceSaver`/`Resource`-typed APIs expect.
 *
 * Works at runtime and from `@Tool` editor code (the real instance is built even for
 * a non-`@Tool` resource class). Throws [IllegalStateException]/[IllegalArgumentException]
 * with a descriptive message if [T] is not a registered resource script class.
 */
inline fun <reified T : Any> newScriptInstance(): T {
    @Suppress("UNCHECKED_CAST")
    return KanamaScript.instantiateResourceScript(T::class.qualifiedName, T::class.simpleName) as T
}
