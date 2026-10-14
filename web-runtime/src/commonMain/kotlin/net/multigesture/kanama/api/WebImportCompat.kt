package net.multigesture.kanama.api

// Web-only import-compat names (task 129 E). Web compiles the shared GD and Mathf now; these
// spellings existed only in the Web copies they replaced, and Web game sources still import them.

/** FPS-era import-compat alias: the pure-Kotlin Basis lives with the value types. */
typealias Basis = net.multigesture.kanama.types.Basis

// Import-compat (task 129 E): the Web-only GD used to emit these shadowed extensions, and Web
// sources import them by name (`import net.multigesture.kanama.api.radToDeg`). Each calls the
// shared GD member it shadows.
@Suppress("EXTENSION_SHADOWED_BY_MEMBER") fun GD.randomize(): Unit = randomize()

@Suppress("EXTENSION_SHADOWED_BY_MEMBER") fun GD.randi(): Long = randi()

@Suppress("EXTENSION_SHADOWED_BY_MEMBER") fun GD.randf(): Double = randf()

@Suppress("EXTENSION_SHADOWED_BY_MEMBER") fun GD.radToDeg(radians: Double): Double = radToDeg(radians)

@Suppress("EXTENSION_SHADOWED_BY_MEMBER") fun GD.pushError(message: Any?): Unit = pushError(message)
