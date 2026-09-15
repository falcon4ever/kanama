package net.multigesture.kanama.api

import net.multigesture.kanama.binding.KanamaScript

/**
 * An owning handle to a resource script created with [newScriptInstance].
 *
 * Holds the live Kotlin script [instance] and the owning [resource] wrapper. The factory takes
 * one reference on the fresh resource (like GDScript `.new()`), so it survives a
 * `ResourceSaver.save(resource, ...)`. **Release that reference** by [close]ing this handle (or
 * wrapping the call in `use { }`) once you are done — unless you have handed ownership on by
 * assigning the resource into a node/scene or an exported `Resource` slot, in which case the
 * engine keeps it alive and closing is unnecessary.
 *
 * This replaces the previous bare-`T` return, which left the owning reference unreleasable
 * through any supported API (the only exit was closing a `Resource.fromHandle` view, which
 * contradicts that view's non-owning contract).
 */
class OwnedScriptResource<out T> @PublishedApi internal constructor(
    /** The live Kotlin script instance — your `@ScriptClass(attachTo = "Resource")` type. */
    val instance: T,
    /** The owning `Resource`. Save it, assign it into a slot, or [close] this handle to release. */
    val resource: Resource,
) : AutoCloseable {
    override fun close() = resource.close()
}

/**
 * Creates a brand-new, engine-backed script resource of type [T] purely from Kotlin — the
 * programmatic equivalent of GDScript's `MyResource.new()` or C#'s `new MyResource()`.
 *
 * [T] must be a custom resource declared with `@ScriptClass(attachTo = "Resource")` (and, for
 * its `res://` path to be discoverable, `@GlobalClass` with a matching file name). Returns an
 * [OwnedScriptResource] holding the live [instance][OwnedScriptResource.instance] and its owning
 * [resource][OwnedScriptResource.resource]:
 *
 * ```kotlin
 * newScriptInstance<Weapon>().use { owned ->
 *     owned.instance.damage = 25
 *     ResourceSaver.save(owned.resource, "res://weapon.tres")
 * }
 * ```
 *
 * Ownership: the returned resource comes back with one owning reference (like `.new()`), so it
 * stays valid across a `ResourceSaver.save`. Release it with [OwnedScriptResource.close] / `use`,
 * or hand ownership on by assigning it into a node/scene or an exported `Resource` slot.
 *
 * Works at runtime and from `@Tool` editor code (the real instance is built even for a
 * non-`@Tool` resource class). Throws [IllegalStateException]/[IllegalArgumentException] with a
 * descriptive message if [T] is not a registered resource script class.
 */
inline fun <reified T : Any> newScriptInstance(): OwnedScriptResource<T> {
    val (instance, resource) =
        KanamaScript.instantiateResourceScript(T::class.qualifiedName, T::class.simpleName)
    if (instance !is T) {
        // The created instance is a different @ScriptClass than requested — the template lookup
        // falls back from fully-qualified to simple name, so two classes sharing a simple name in
        // different packages can resolve to the wrong one. Release the owning reference and fail
        // with a clear message instead of a bare ClassCastException.
        resource.close()
        error(
            "newScriptInstance<${T::class.simpleName}>: created a '${instance::class.qualifiedName}', " +
                "not '${T::class.qualifiedName}'. Two @ScriptClass types may share the simple name " +
                "'${T::class.simpleName}' in different packages — give them distinct class names.",
        )
    }
    return OwnedScriptResource(instance, resource)
}
