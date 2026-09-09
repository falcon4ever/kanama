package net.multigesture.kanama.api

/**
 * Deprecated, no longer applied to any Kanama API (task 97).
 *
 * `RefCounted.close()` used to carry this opt-in requirement. It warned on the one
 * thing the documented ownership rule tells you to do — close the wrapper you were
 * handed — so it punished correct code and was opted into blindly (task 93, R6).
 * The rule itself lives in `docs/game-dev/godot-api.md` "Resource Ownership".
 *
 * The class is kept so an existing `@OptIn(ManualGodotLifetimeApi::class)` still
 * compiles (with a deprecation warning); delete the opt-in, nothing replaces it.
 */
@Deprecated("No longer required; close() is the documented contract (docs/game-dev/godot-api.md#resource-ownership)")
@RequiresOptIn(
    message = "Manual Godot lifetime APIs are for low-level interop. Gameplay scripts should use Godot lifecycle APIs unless this value is explicitly caller-owned.",
    level = RequiresOptIn.Level.WARNING,
)
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class ManualGodotLifetimeApi
