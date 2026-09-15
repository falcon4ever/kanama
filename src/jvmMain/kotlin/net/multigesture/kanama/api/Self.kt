package net.multigesture.kanama.api

/**
 * Base class for attachable scripts that want a typed wrapper for their own
 * Godot object.
 *
 * Kotlin's `this` is the JVM script object, not the Godot node/resource the
 * script is attached to. [self] is a non-owning wrapper around that Godot
 * object, typed to the script's primary `attachTo` class:
 *
 * ```kotlin
 * @ScriptClass(attachTo = "CharacterBody3D")
 * class Player(godotObject: GodotHandle) :
 *     KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D) {
 *
 *     private val node = selfAs(::Node)
 *
 *     @OnPhysicsProcess
 *     fun physicsProcess(delta: Double) {
 *         if (self.isOnFloor()) node.translate(Vector3(0f, 1f, 0f))
 *     }
 * }
 * ```
 *
 * [self] and [selfAs] do not own the native object and must not free it.
 */
abstract class KanamaScript<Self : Any>(
    val godotObject: GodotHandle,
    selfFactory: (GodotHandle) -> Self,
) {
    val self: Self = selfFactory(godotObject)

    /**
     * Wrap this script's Godot object as another compatible Kanama wrapper type.
     */
    inline fun <T> selfAs(ctor: (GodotHandle) -> T): T = ctor(godotObject)

    /**
     * Refresh the Godot inspector after exported property metadata changes.
     *
     * This is mainly useful from `@Tool` scripts whose visible properties,
     * groups, or usage flags depend on editor-time state.
     */
    fun notifyInspectorChanged() {
        GodotObject(godotObject).notifyPropertyListChanged()
    }

    /**
     * True when the script is running inside the Godot editor.
     */
    fun isEditorHint(): Boolean =
        Engine.isEditorHint()
}

/**
 * Legacy helper that wraps a script's `godotObject` handle as the given Kanama
 * wrapper type. New attachable scripts should extend [KanamaScript] and use
 * [KanamaScript.self] or [KanamaScript.selfAs].
 *
 * Equivalent to `ctor(godotObject)` — the value is the named idiom, not a
 * shorter call site. Inline so there is no allocation/dispatch overhead vs.
 * direct construction.
 */
@Deprecated("Use KanamaScript<T>.self or KanamaScript.selfAs(::Type) in attachable scripts.")
inline fun <T> selfAs(godotObject: GodotHandle, ctor: (GodotHandle) -> T): T = ctor(godotObject)
