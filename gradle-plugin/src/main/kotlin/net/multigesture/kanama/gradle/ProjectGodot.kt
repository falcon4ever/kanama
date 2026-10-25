package net.multigesture.kanama.gradle

import java.io.File

/** Edits of a project's `project.godot` that an export needs and the user should not have to make. */
internal object ProjectGodot {
  const val ETC2_ASTC_SETTING = "rendering/textures/vram_compression/import_etc2_astc"

  /**
   * Godot's Android export refuses to start ("ETC2/ASTC texture compression is required") until the
   * project imports textures for it. Returns true when the file changed.
   */
  fun enableEtc2AstcImport(projectGodot: File): Boolean {
    val text = projectGodot.readText()
    val updated = setSetting(text, ETC2_ASTC_SETTING, "true")
    if (updated == text) return false
    projectGodot.writeText(updated)
    return true
  }

  /**
   * Sets `section/key` (the part before the first `/` is the section) to [value], keeping every other
   * line: the key's line is replaced, or the key is added at the end of its section, or the section
   * is appended.
   */
  fun setSetting(text: String, setting: String, value: String): String {
    val section = setting.substringBefore('/')
    val key = setting.substringAfter('/')
    val newline = if ("\r\n" in text) "\r\n" else "\n"
    val lines = text.lines().toMutableList()
    if (lines.lastOrNull() == "") lines.removeAt(lines.lastIndex)
    val header = lines.indexOfFirst { it.trim() == "[$section]" }
    if (header < 0) {
      if (lines.lastOrNull()?.isNotBlank() == true) lines.add("")
      lines.add("[$section]")
      lines.add("")
      lines.add("$key=$value")
      return lines.joinToString(newline, postfix = newline)
    }
    var end = lines.size
    for (index in header + 1 until lines.size) {
      if (lines[index].trimStart().startsWith("[")) {
        end = index
        break
      }
      if (lines[index].substringBefore('=').trim() == key) {
        lines[index] = "$key=$value"
        return lines.joinToString(newline, postfix = newline)
      }
    }
    var insert = end
    while (insert > header + 1 && lines[insert - 1].isBlank()) insert--
    lines.add(insert, "$key=$value")
    return lines.joinToString(newline, postfix = newline)
  }
}
