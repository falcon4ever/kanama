package net.multigesture.kanama.binding.runtime

/**
 * A contained Kotlin exception rendered as the fields of GDExtension's
 * `print_script_error_with_message` (task 131 item 1): Godot prints it as `SCRIPT ERROR: <message>`
 * / `at: <function> (<file>:<line>)` and its debugger shows it in the editor's Errors tab, which is
 * what a GDScript error does. Before this a Kotlin exception reached only the process stderr, which
 * the editor's Play button does not capture.
 *
 * Shared by the desktop/Android backend (frames from `Throwable.stackTrace`) and iOS (frames parsed
 * from Kotlin/Native's `getStackTrace()` lines by [parseNativeFrame]); each backend only supplies
 * its frames and its way of reaching the engine.
 */
class ScriptErrorReport(
  /** The error condition: the exception's class name (Godot's `<Kotlin Error>` row). */
  val description: String,
  /** `<exception class>: <message>`, the line Godot prints after `SCRIPT ERROR:`. */
  val message: String,
  /** `<SimpleClass>.<method>` of the top user frame, or the containment site if none. */
  val function: String,
  /** The Kotlin source file of the top user frame (`Player.kt`), or `""` when unknown. */
  val file: String,
  /** The line in [file], or 0 when unknown. */
  val line: Int,
) {
  override fun toString(): String = "$message at $function ($file:$line)"

  companion object {
    /**
     * Builds the report for [t]. The reported frame is the top frame of [t] (then of its causes)
     * that is not Kanama runtime, generated glue, the Kotlin/Java standard library or a platform
     * class -- the line of the game script that threw or that made the throwing call. [where] names
     * the containment site and stands in for the function when no frame qualifies. [exceptionName]
     * is the backend's name for `t`'s class (`t.javaClass.name` on the JVM: plain Java reflection,
     * which survives R8, unlike `KClass.qualifiedName`).
     */
    fun of(
      t: Throwable,
      where: String,
      exceptionName: String,
      framesOf: (Throwable) -> List<ScriptErrorFrame>,
    ): ScriptErrorReport {
      val frame = reportedFrame(t, framesOf)
      val text = t.message
      return ScriptErrorReport(
        description = exceptionName,
        message = if (text.isNullOrEmpty()) exceptionName else "$exceptionName: $text",
        function = frame?.let(::functionName) ?: where,
        file = frame?.fileName.orEmpty(),
        line = frame?.line ?: 0,
      )
    }

    /**
     * `Player.ready` for a member; just `helper` for a top-level function, whose owner is a file
     * facade (`PlayerKt`, kept: it names the file) on the JVM and a package on Kotlin/Native.
     */
    private fun functionName(frame: ScriptErrorFrame): String {
      val owner = frame.className.substringAfterLast('.')
      return if (owner.firstOrNull()?.isUpperCase() == true) "$owner.${frame.methodName}"
      else frame.methodName
    }

    /**
     * The top game frame of [t] or its causes; failing that (the runtime itself threw), the top
     * Kanama frame with a file, then any frame with a file.
     */
    private fun reportedFrame(
      t: Throwable,
      framesOf: (Throwable) -> List<ScriptErrorFrame>,
    ): ScriptErrorFrame? {
      var current: Throwable? = t
      var depth = 0
      var kanamaFrame: ScriptErrorFrame? = null
      var anyFrame: ScriptErrorFrame? = null
      while (current != null && depth < MAX_CAUSE_DEPTH) {
        val frames = framesOf(current)
        frames
          .firstOrNull { !isRuntimeClass(it.className) }
          ?.let {
            return it
          }
        if (kanamaFrame == null) {
          kanamaFrame =
            frames.firstOrNull { it.fileName.isNotEmpty() && !isPlatformClass(it.className) }
        }
        if (anyFrame == null) anyFrame = frames.firstOrNull { it.fileName.isNotEmpty() }
        current = current.cause
        depth++
      }
      return kanamaFrame ?: anyFrame
    }

    private const val MAX_CAUSE_DEPTH = 8

    // Kanama's own packages. Game scripts may live under net.multigesture.kanama.* too (the example
    // project does), so the runtime is listed package by package, not by the root.
    private val KANAMA_PREFIXES =
      listOf(
        "net.multigesture.kanama.api.",
        "net.multigesture.kanama.binding.",
        "net.multigesture.kanama.generated.",
        "net.multigesture.kanama.ffi.",
        "net.multigesture.kanama.types.",
        "net.multigesture.kanama.ios.",
        "net.multigesture.kanama.annotations.",
      )

    private val PLATFORM_PREFIXES =
      listOf(
        "kotlin.",
        "kotlinx.",
        "java.",
        "javax.",
        "jdk.",
        "sun.",
        "com.sun.",
        "com.v7878.",
        "dalvik.",
        "libcore.",
        "android.",
        "com.android.",
      )

    /**
     * True for a class (or, for a Kotlin/Native top-level function, a package) that belongs to the
     * runtime or the platform rather than to the game: such a frame is skipped when choosing the
     * reported line.
     */
    fun isRuntimeClass(className: String): Boolean {
      if (className.isEmpty() || isPlatformClass(className)) return true
      if (className.substringBeforeLast('.', "") == "net.multigesture.kanama") return true
      val dotted = "$className."
      return KANAMA_PREFIXES.any { dotted.startsWith(it) }
    }

    private fun isPlatformClass(className: String): Boolean {
      val dotted = "$className."
      return PLATFORM_PREFIXES.any { dotted.startsWith(it) }
    }

    private val NATIVE_FRAME = Regex("""kfun:([^#\s(]+)#([^(\s]*)""")
    private val NATIVE_SOURCE = Regex("""\(([^()\s]*?)([^/()\s]+\.kt):(\d+)(?::\d+)?\)\s*$""")

    /**
     * Parses one Kotlin/Native `Throwable.getStackTrace()` line, e.g. `at 3 libfoo 0x1049c2f3c
     * kfun:com.example.Player#ready(){} + 52 (/src/Player.kt:12:5)`. The `(file:line:col)` suffix
     * is present only when the binary carries source info (debug builds); without it the frame
     * still names the class and the method, with an empty file and line 0. A line without a `kfun:`
     * symbol (a C or Objective-C frame) yields null.
     */
    fun parseNativeFrame(line: String): ScriptErrorFrame? {
      val symbol = NATIVE_FRAME.find(line) ?: return null
      val owner = symbol.groupValues[1]
      val method = symbol.groupValues[2].ifEmpty { "<anonymous>" }
      val source = NATIVE_SOURCE.find(line)
      return ScriptErrorFrame(
        className = owner,
        methodName = method,
        fileName = source?.groupValues?.get(2).orEmpty(),
        line = source?.groupValues?.get(3)?.toIntOrNull() ?: 0,
      )
    }
  }
}

/** One stack frame, reduced to what [ScriptErrorReport] needs. */
class ScriptErrorFrame(
  val className: String,
  val methodName: String,
  val fileName: String,
  val line: Int,
)
