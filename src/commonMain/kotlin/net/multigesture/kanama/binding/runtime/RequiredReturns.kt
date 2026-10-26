package net.multigesture.kanama.binding.runtime

/**
 * The one helper behind every Godot object return marked `meta: "required"` (task 128 A, decision
 * 9).
 *
 * Godot promises such a method never returns null on success (`RequiredResult<T>`), so the wrapper
 * returns a non-null type. A null anyway is an engine bug and must not pass silently: it throws,
 * naming the Godot class and method (`"Godot returned null from required Node.create_tween"`). The
 * generated wrappers call it on every platform, and the hand-written per-platform classes (Tween)
 * call the same function, so the message and the exception type cannot drift.
 */
internal fun <T : Any> requireGodotReturn(value: T?, godotMethod: String): T =
  value ?: throw IllegalStateException("Godot returned null from required $godotMethod")

/**
 * The object a generated `X.create()` constructed, or a loud failure: Godot answers null for a
 * class it cannot instantiate here, such as an editor class in an exported game (GDScript does not
 * even parse `EditorPlugin.new()` there), and a wrapper over null would crash on its first call.
 */
internal fun requireConstructed(segment: RawSegment, className: String): RawSegment {
  check(segment.address() != 0L) {
    "Godot could not instantiate $className: it is not available here (an editor class outside the editor?)"
  }
  return segment
}
