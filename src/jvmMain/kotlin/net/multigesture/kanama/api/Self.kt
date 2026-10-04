package net.multigesture.kanama.api

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
