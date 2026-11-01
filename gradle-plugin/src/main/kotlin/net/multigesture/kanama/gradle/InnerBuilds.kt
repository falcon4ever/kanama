package net.multigesture.kanama.gradle

/**
 * [text] as the inside of a Kotlin string literal of an inner build script under
 * `build/kanama/<platform>`: a project path with a `$`, a `"` or a `\` in it must neither end the
 * literal nor start a string template.
 */
internal fun kotlinString(text: String): String =
  text.replace("\\", "\\\\").replace("\"", "\\\"").replace("$", "\\$")
