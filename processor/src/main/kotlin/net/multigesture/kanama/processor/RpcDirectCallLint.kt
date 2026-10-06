package net.multigesture.kanama.processor

/**
 * Task 131 item 4 (F14): a direct Kotlin call to an `@Rpc` function is a plain local call, so the
 * method runs on this peer only. The GDScript it was ported from (`jump.rpc()`) reached every peer.
 * Nothing failed at compile time, so the tps port lost its multiplayer effects. This lint warns on
 * such calls and names the generated `<Class>Rpcs` sender to use instead.
 *
 * Exempt: `@Rpc(callLocal = true)` functions (Godot runs those locally too, and GDScript calls them
 * directly as well, e.g. tps `player.gd` `add_camera_shake_trauma(0.35)` inside `shoot()`), and
 * generated code (package `net.multigesture.kanama.generated`, which holds the senders and the Web
 * local-peer helpers that must call the function itself).
 *
 * KSP exposes no function bodies, so this reads the source text, with comments and string literals
 * blanked. It warns on:
 * - an unqualified (or `this.`) call in the file that declares the `@Rpc` class;
 * - a qualified call (`robot.hit()`, `x?.hit()`) in any source, when the name is declared exactly
 *   once in the sources and is not a Godot engine method name, so an unrelated `play()` or a
 *   same-named method on another class is not flagged.
 */
internal object RpcDirectCallLint {

  /** One `@Rpc` function of a script class. [declaringFile] is the script class's source path. */
  data class Target(
    val className: String,
    val kotlinName: String,
    val godotName: String,
    val callLocal: Boolean,
    val declaringFile: String,
  )

  data class Source(val path: String, val text: String)

  private const val GENERATED_PACKAGE = "net.multigesture.kanama.generated"

  fun warnings(
    targets: List<Target>,
    sources: List<Source>,
    isEngineMethod: (godotName: String) -> Boolean = { false },
  ): List<String> {
    val remote = targets.filter { !it.callLocal }.distinctBy { it.className to it.kotlinName }
    if (remote.isEmpty()) return emptyList()
    val scanned =
      sources.filter { !isGeneratedSource(it) }.map { it.path to blankCommentsAndStrings(it.text) }
    val declarationCounts = HashMap<String, Int>()
    for ((_, text) in scanned) {
      for (m in FUN_DECLARATION.findAll(text)) {
        declarationCounts.merge(m.groupValues[1], 1, Int::plus)
      }
    }
    val out = mutableListOf<String>()
    for ((path, text) in scanned) {
      val lineStarts = lineStarts(text)
      for (target in remote) {
        val qualifiedAllowed =
          declarationCounts[target.kotlinName] == 1 && !isEngineMethod(target.godotName)
        val inDeclaringFile = samePath(path, target.declaringFile)
        if (!qualifiedAllowed && !inDeclaringFile) continue
        val call = Regex("""(?<![\w$`])${Regex.escape(target.kotlinName)}\s*\(""")
        for (m in call.findAll(text)) {
          val kind = callKind(text, m.range.first)
          val flagged =
            when (kind) {
              CallKind.DECLARATION,
              CallKind.REFERENCE -> false
              CallKind.UNQUALIFIED -> inDeclaringFile
              CallKind.QUALIFIED -> qualifiedAllowed
            }
          if (!flagged) continue
          val line = lineStarts.binarySearch(m.range.first).let { if (it >= 0) it + 1 else -it - 1 }
          out += message(target, path, line)
        }
      }
    }
    return out
  }

  fun message(target: Target, path: String, line: Int): String {
    val suffix = signalHelperSuffix(target.godotName)
    val sender = "${target.className}Rpcs"
    return "$path:$line: ${target.kotlinName}() is an @Rpc function of ${target.className} " +
      "called directly, so it runs on this peer only. Send it with " +
      "$sender.rpc$suffix(instance) or $sender.rpcId$suffix(instance, peerId); to run it here " +
      "as well, declare @Rpc(callLocal = true) and call $sender.callLocal$suffix(instance)."
  }

  private enum class CallKind {
    DECLARATION,
    REFERENCE,
    UNQUALIFIED,
    QUALIFIED,
  }

  private fun callKind(text: String, nameStart: Int): CallKind {
    var i = nameStart - 1
    while (i >= 0 && text[i].isWhitespace()) i--
    if (i >= 1 && text[i] == ':' && text[i - 1] == ':') return CallKind.REFERENCE
    if (i >= 0 && text[i] == '.') {
      // `this.name(` and `this@Outer.name(` are the same as an unqualified call.
      val receiver = text.substring(0, i).trimEnd()
      if (THIS_RECEIVER.containsMatchIn(receiver)) return CallKind.UNQUALIFIED
      return CallKind.QUALIFIED
    }
    val lineStart = text.lastIndexOf('\n', nameStart - 1) + 1
    if (DECLARATION_PREFIX.containsMatchIn(text.substring(lineStart, nameStart))) {
      return CallKind.DECLARATION
    }
    return CallKind.UNQUALIFIED
  }

  private fun isGeneratedSource(source: Source): Boolean =
    PACKAGE.find(blankCommentsAndStrings(source.text))?.groupValues?.get(1) == GENERATED_PACKAGE

  private fun samePath(a: String, b: String): Boolean = a.replace('\\', '/') == b.replace('\\', '/')

  private fun lineStarts(text: String): IntArray {
    val starts = mutableListOf(0)
    text.forEachIndexed { i, c -> if (c == '\n') starts += i + 1 }
    return starts.toIntArray()
  }

  /**
   * [text] with comment bodies and string/char literal contents replaced by spaces (line breaks
   * kept, so offsets and line numbers still match the file). `${...}` template bodies are blanked
   * with their string; a call written inside a template is not linted.
   */
  internal fun blankCommentsAndStrings(text: String): String {
    val out = StringBuilder(text)
    fun blank(from: Int, to: Int) {
      for (k in from until minOf(to, text.length)) if (out[k] != '\n') out[k] = ' '
    }
    var i = 0
    while (i < text.length) {
      when {
        text.startsWith("//", i) -> {
          val end = text.indexOf('\n', i).let { if (it < 0) text.length else it }
          blank(i, end)
          i = end
        }
        text.startsWith("/*", i) -> {
          var depth = 1
          var j = i + 2
          while (j < text.length && depth > 0) {
            when {
              text.startsWith("/*", j) -> {
                depth++
                j += 2
              }
              text.startsWith("*/", j) -> {
                depth--
                j += 2
              }
              else -> j++
            }
          }
          blank(i, j)
          i = j
        }
        text.startsWith("\"\"\"", i) -> {
          val close = text.indexOf("\"\"\"", i + 3)
          var end = if (close < 0) text.length else close + 3
          while (end < text.length && text[end] == '"') end++
          blank(i + 3, end - 3)
          i = end
        }
        text[i] == '"' || text[i] == '\'' -> {
          val quote = text[i]
          var j = i + 1
          while (j < text.length && text[j] != quote && text[j] != '\n') {
            j += if (text[j] == '\\') 2 else 1
          }
          blank(i + 1, j)
          i = j + 1
        }
        else -> i++
      }
    }
    return out.toString()
  }

  private val FUN_DECLARATION = Regex("""\bfun\s+(?:<[^>]*>\s*)?(?:[\w.<>?, ]+\.)?(\w+)\s*\(""")
  private val DECLARATION_PREFIX = Regex("""\bfun\s+(?:<[^>]*>\s*)?(?:[\w.<>?, ]+\.)?$""")
  private val THIS_RECEIVER = Regex("""(?<![\w$])this(?:@\w+)?$""")
  private val PACKAGE = Regex("""^\s*package\s+([\w.]+)""", RegexOption.MULTILINE)
}
