package net.multigesture.kanama.buildlogic

import java.io.File

/**
 * The iOS source-line table of a debug device build (task 131 item 13), read by
 * `ios/bootstrap/kanama_ios_source_lines.c`.
 *
 * An iPhone app carries no DWARF, so a Kotlin/Native frame on the device is only `kfun:<symbol> +
 * <offset>`. The offset inside a function is fixed when the static library is built, so the table
 * maps it ahead of time: for every function of the debug runtime library with code from a game
 * source file, its symbol, its size and the rows (offset from the function's start, file, line) of
 * its line table. A row inside code inlined from elsewhere (the standard library's `error`,
 * `forEach`) takes the innermost enclosing frame from a game file, as `atos -i` resolves the inline
 * chain. A row whose game line cannot be told for sure gets line 0, so the lookup answers "unknown"
 * there: never a wrong line.
 *
 * The parsers take the tools' output as text, so the tests run on recorded output.
 */
object IosSourceLines {
  /** A function start as the device's `dladdr` sees it. */
  data class Symbol(val address: Long, val name: String)

  /** One `.debug_line` row: [path] is the source file's full path, `""` when unknown. */
  data class LineRow(val address: Long, val line: Int, val path: String)

  /** One frame of an `atos -i` inline chain: the file's base name and the line. */
  data class Frame(val file: String, val line: Int)

  /** From [offset] on (bytes from the function's start), [file]:[line]; line 0 = unknown. */
  data class Row(val offset: Int, val file: String, val line: Int)

  /** A function of the table: its symbol (no leading underscore), size in bytes and rows. */
  data class Function(val name: String, val size: Int, val rows: List<Row>)

  /**
   * The function starts of `nm -n --defined-only` output: the real symbols of `__text` (`t`/`T`,
   * starting with `_`). Linker-private `l`/`ltmp` labels never reach the app, so the device names a
   * frame in their code after the symbol before them; they do not end a function here either.
   */
  fun parseNm(lines: List<String>): List<Symbol> =
    lines
      .mapNotNull { line ->
        val parts = line.split(' ', limit = 3)
        if (parts.size == 3 && (parts[1] == "t" || parts[1] == "T") && parts[2].startsWith("_")) {
          Symbol(parts[0].toLong(16), parts[2].substring(1))
        } else null
      }
      .sortedBy { it.address }

  /**
   * The rows of every line table in `dwarfdump --debug-line` output (DWARF 2 to 5), each with its
   * file's full path. A file whose directory is not listed (DWARF before 5 leaves directory 0, the
   * compilation directory, implicit) keeps its name as given, so it never matches a source root.
   */
  fun parseDebugLine(lines: Sequence<String>): List<LineRow> {
    val rows = mutableListOf<LineRow>()
    val dirs = mutableMapOf<Int, String>()
    val names = mutableMapOf<Int, String>()
    val fileDirs = mutableMapOf<Int, Int>()
    val paths = mutableMapOf<Int, String>()
    var currentFile = -1
    for (raw in lines) {
      val line = raw.trim()
      when {
        line.startsWith("debug_line[") -> {
          dirs.clear()
          names.clear()
          fileDirs.clear()
          paths.clear()
        }
        line.startsWith("include_directories[") -> dirs[index(line)] = quoted(line)
        line.startsWith("file_names[") -> currentFile = index(line)
        line.startsWith("name:") -> names[currentFile] = quoted(line)
        line.startsWith("dir_index:") ->
          fileDirs[currentFile] = line.substringAfter(':').trim().toInt()
        line.startsWith("0x") && !line.contains("end_sequence") -> {
          val fields = line.split(WHITESPACE)
          require(fields.size >= 4) { "unexpected dwarfdump row: $raw" }
          val file = fields[3].toInt()
          val path =
            paths.getOrPut(file) {
              val name = names[file] ?: error("dwarfdump row names file $file, not declared: $raw")
              val dir = dirs[fileDirs[file]]
              if (name.startsWith("/") || dir == null) name else "$dir/$name"
            }
          rows += LineRow(fields[0].removePrefix("0x").toLong(16), fields[1].toInt(), path)
        }
      }
    }
    return rows
  }

  /**
   * The inline chains of `atos -i` output, one per address asked, innermost frame first. atos
   * answers each address with its frames and a blank line; any other shape fails, since the chains
   * would no longer line up with their addresses. A frame without `(File.kt:line)` (an address atos
   * cannot symbolicate) is null.
   */
  fun parseAtosChains(lines: List<String>, addresses: Int): List<List<Frame?>> {
    val chains = mutableListOf<MutableList<Frame?>>()
    var open = false
    for (line in lines) {
      if (line.isBlank()) {
        open = false
        continue
      }
      if (!open) {
        chains += mutableListOf<Frame?>()
        open = true
      }
      chains.last() +=
        ATOS_FRAME.find(line)?.let { Frame(it.groupValues[1], it.groupValues[2].toInt()) }
    }
    check(chains.size == addresses) {
      "atos -i answered ${chains.size} inline chains for $addresses addresses"
    }
    return chains
  }

  /**
   * The table's functions: those of [symbols] with a row from a game file ([isGame] on its full
   * path). [display] turns a game file's path into the name the report shows; [inlineChains]
   * answers the `atos -i` chains of the rows inside inlined code, by address.
   */
  fun functions(
    symbols: List<Symbol>,
    rows: List<LineRow>,
    isGame: (String) -> Boolean,
    display: (String) -> String,
    inlineChains: (List<Long>) -> Map<Long, List<Frame?>>,
  ): List<Function> {
    val starts = symbols.map { it.address }.toLongArray()
    fun functionOf(address: Long): Int {
      val index = java.util.Arrays.binarySearch(starts, address)
      return if (index >= 0) index else -index - 2
    }
    // A repeated local name is ambiguous on the device: such a function gets no lines.
    val repeated = symbols.groupingBy { it.name }.eachCount().filterValues { it > 1 }.keys
    val gamePaths = rows.map { it.path }.filter(isGame).toSet()
    val byFunction =
      rows
        .groupBy { functionOf(it.address) }
        .filterKeys { it >= 0 && symbols[it].name !in repeated }
        .filterValues { functionRows -> functionRows.any { it.path in gamePaths } }
    // A base name names one game file only if no other file of the table has it.
    val pathsByName = rows.map { it.path }.toSet().groupBy { it.substringAfterLast('/') }
    val gameByName =
      pathsByName
        .filterValues { it.size == 1 && it.single() in gamePaths }
        .mapValues { it.value.single() }
    val inlined = byFunction.values.flatten().filter { it.path !in gamePaths }.map { it.address }
    val chains = if (inlined.isEmpty()) emptyMap() else inlineChains(inlined.distinct())
    return byFunction
      .map { (index, functionRows) ->
        val start = symbols[index].address
        val end = symbols.getOrNull(index + 1)?.address ?: (functionRows.maxOf { it.address } + 4)
        val resolved = mutableListOf<Row>()
        for (row in functionRows.sortedBy { it.address }) {
          val (path, line) =
            if (row.path in gamePaths) row.path to row.line
            else {
              // The innermost enclosing frame (the chain without the row's own) from a game file.
              chains[row.address]?.drop(1)?.firstNotNullOfOrNull { frame ->
                frame?.let { gameByName[it.file] }?.let { it to frame.line }
              } ?: ("" to 0)
            }
          val next = Row((row.address - start).toInt(), if (line > 0) display(path) else "", line)
          val last = resolved.lastOrNull()
          if (last != null && last.offset == next.offset) resolved.removeAt(resolved.size - 1)
          val previous = resolved.lastOrNull()
          if (previous != null && previous.file == next.file && previous.line == next.line) continue
          resolved += next
        }
        Function(symbols[index].name, (end - start).toInt(), resolved)
      }
      .filter { function -> function.rows.any { it.line > 0 } }
  }

  /**
   * The C source of the table: `k_strings` (every symbol and file, NUL-terminated), `k_symbols`
   * (sorted in `strcmp` order: unsigned UTF-8 bytes) and `k_rows`.
   */
  fun render(functions: List<Function>): String {
    // The string pool as C literal pieces: printable ASCII as is, every other byte (and `"`, `\`,
    // `?`) as a three-digit octal escape, so no escape can run into the next character.
    val pieces = mutableListOf<String>()
    val offsets = mutableMapOf<String, Int>()
    var size = 0
    fun intern(value: String): Int =
      offsets.getOrPut(value) {
        val at = size
        val bytes = value.toByteArray(Charsets.UTF_8) + 0.toByte()
        for (byte in bytes) {
          val c = byte.toInt() and 0xff
          pieces +=
            if (c in 0x20..0x7e && c.toChar() !in "\"\\?") c.toChar().toString()
            else "\\" + c.toString(8).padStart(3, '0')
        }
        size += bytes.size
        at
      }
    val sorted =
      functions.sortedWith { a, b ->
        java.util.Arrays.compareUnsigned(
          a.name.toByteArray(Charsets.UTF_8),
          b.name.toByteArray(Charsets.UTF_8),
        )
      }
    val symbols = StringBuilder()
    val rows = StringBuilder()
    var rowCount = 0
    for (function in sorted) {
      symbols.append(
        "    {${intern(function.name)}u, ${function.size}u, ${rowCount}u, ${function.rows.size}u},\n"
      )
      for (row in function.rows) {
        rows.append("    {${row.offset}u, ${intern(row.file)}u, ${row.line}u},\n")
      }
      rowCount += function.rows.size
    }
    return buildString {
      append("// Generated by generateIosDeviceDebugSourceLines (build.gradle.kts). Do not edit.\n")
      append("static const char k_strings[] =\n    \"")
      var lineLength = 0
      for (piece in pieces) {
        if (lineLength >= 120) {
          append("\"\n    \"")
          lineLength = 0
        }
        append(piece)
        lineLength += piece.length
      }
      append("\";\n")
      append("static const uint32_t k_symbol_count = ${sorted.size}u;\n")
      append("static const KanamaIosSourceSymbol k_symbols[${maxOf(sorted.size, 1)}] = {\n")
      append(symbols.ifEmpty { "    {0u, 0u, 0u, 0u},\n" })
      append("};\n")
      append("static const KanamaIosSourceRow k_rows[${maxOf(rowCount, 1)}] = {\n")
      append(rows.ifEmpty { "    {0u, 0u, 0u},\n" })
      append("};\n")
    }
  }

  /**
   * The name a report shows for the game file [path]: its `res://` path when a directory between
   * the file and the parent of its source root holds `project.godot` (the editor's Errors tab can
   * open it, as on desktop), else its base name.
   */
  fun displayPath(path: String, sourceRoots: List<File>, isProject: (File) -> Boolean): String {
    val file = File(path)
    val root =
      sourceRoots.firstOrNull { path == it.path || path.startsWith(it.path + "/") }
        ?: return file.name
    val ceiling = root.parentFile ?: root
    var dir = file.parentFile
    while (dir != null) {
      if (isProject(dir)) return "res://" + file.relativeTo(dir).invariantSeparatorsPath
      if (dir == ceiling) break
      dir = dir.parentFile
    }
    return file.name
  }

  /**
   * Matches the source paths a debug library's DWARF names to the game's [gameFiles] (each under
   * one of [sourceRoots]). A path matches its own file, or a file whose path from its root's parent
   * (`kotlin-src/game/Player.kt`; for a file root, from its directory's parent) ends it: Gradle's
   * build cache hands a build the Kotlin/Native library compiled in another checkout, whose DWARF
   * names that checkout's paths (task 131 item 13: the device build found no function). A file two
   * DWARF paths match, or a path two files match, is ambiguous and left out: never a wrong line.
   */
  fun matchGameFiles(
    dwarfPaths: Set<String>,
    sourceRoots: List<File>,
    gameFiles: List<File>,
  ): Map<String, File> {
    val suffixes =
      gameFiles.associateWith { file ->
        val root =
          sourceRoots.firstOrNull { file.path == it.path || file.path.startsWith(it.path + "/") }
        val base = (if (root == null || root == file) file.parentFile else root).parentFile
        "/" + (if (base == null) file.name else file.relativeTo(base).invariantSeparatorsPath)
      }
    val candidates =
      dwarfPaths.associateWith { path ->
        gameFiles.filter { path == it.path || path.endsWith(suffixes.getValue(it)) }
      }
    val matched = candidates.filterValues { it.size == 1 }.mapValues { it.value.single() }
    val shared = matched.values.groupingBy { it }.eachCount().filterValues { it > 1 }.keys
    return matched.filterValues { it !in shared }
  }

  /**
   * Writes the table of the debug Kotlin/Native [runtimeLib] for the game files under [sourceRoots]
   * to [table]. Fails when a source root has no code in the library: a table without the game's
   * functions would only make every report line 0. Returns the function and row counts.
   */
  fun write(
    runtimeLib: File,
    sourceRoots: List<File>,
    table: File,
    workDir: File,
    developerDir: String,
  ): Pair<Int, Int> {
    val roots = sourceRoots.map { it.absoluteFile.normalize() }
    val gameFiles =
      roots.flatMap { root ->
        root.walkTopDown().filter { it.isFile && it.extension == "kt" }.toList()
      }
    workDir.deleteRecursively()
    workDir.mkdirs()
    val tools = Xcrun(developerDir, workDir)
    // The program's own object; the `-cache` members are the prebuilt standard library and
    // dependencies, never game code.
    val members =
      tools.lines("ar", "-t", runtimeLib.absolutePath).filter {
        it.endsWith(".o") && "-cache" !in it
      }
    tools.lines("ar", "-x", runtimeLib.absolutePath, *members.toTypedArray())
    val found = mutableSetOf<File>()
    val functions =
      members.flatMap { member ->
        val objectFile = File(workDir, member).absolutePath
        val symbols = parseNm(tools.lines("nm", "-n", "--defined-only", objectFile))
        val rows = tools.useLines("dwarfdump", "--debug-line", objectFile) { parseDebugLine(it) }
        val matches = matchGameFiles(rows.map { it.path }.toSet(), roots, gameFiles)
        found += matches.values
        functions(
          symbols,
          rows,
          { it in matches },
          { path ->
            displayPath(matches.getValue(path).path, roots) { File(it, "project.godot").isFile }
          },
        ) { addresses ->
          val input = File(workDir, "inlined-addresses.txt")
          input.writeText(addresses.joinToString("\n") { "0x" + it.toString(16) } + "\n")
          val output =
            tools.lines("atos", "-o", objectFile, "-arch", "arm64", "-i", "-f", input.absolutePath)
          addresses.zip(parseAtosChains(output, addresses.size)).toMap()
        }
      }
    val missing = roots.filter { root -> found.none { it == root || it.startsWith(root) } }
    check(missing.isEmpty()) {
      "the debug iOS library $runtimeLib has no code from ${missing.joinToString()}: its source " +
        "lines would be empty and every iOS script error would report line 0"
    }
    // A name in two members is as ambiguous as a repeated one.
    val unique = functions.groupBy { it.name }.filterValues { it.size == 1 }.values.flatten()
    check(unique.isNotEmpty()) { "no game function in the debug iOS library $runtimeLib" }
    table.parentFile.mkdirs()
    table.writeText(render(unique))
    workDir.deleteRecursively()
    return unique.size to unique.sumOf { it.rows.size }
  }

  private class Xcrun(private val developerDir: String, private val workDir: File) {
    fun lines(vararg command: String): List<String> = useLines(*command) { it.toList() }

    fun <T> useLines(vararg command: String, read: (Sequence<String>) -> T): T {
      val process =
        ProcessBuilder(listOf("xcrun") + command)
          .directory(workDir)
          .redirectError(ProcessBuilder.Redirect.INHERIT)
          .apply { environment()["DEVELOPER_DIR"] = developerDir }
          .start()
      val result = process.inputStream.bufferedReader().useLines(read)
      val exit = process.waitFor()
      check(exit == 0) { "xcrun ${command.first()} failed (exit $exit)" }
      return result
    }
  }

  private val WHITESPACE = Regex("\\s+")
  private val QUOTED = Regex(""""([^"]*)"\s*$""")
  private val ATOS_FRAME = Regex("""\(([^()\s]+\.kt):(\d+)\)\s*$""")

  private fun index(line: String): Int =
    line.substringAfter('[').substringBefore(']').trim().toInt()

  private fun quoted(line: String): String =
    QUOTED.find(line)?.groupValues?.get(1) ?: error("dwarfdump line without a quoted value: $line")
}
