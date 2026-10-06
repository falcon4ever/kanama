package net.multigesture.kanama.buildlogic

import java.io.File
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * The iOS source-line table generator (task 131 item 13) on tool output recorded from a debug
 * `iosArm64` Kanama build (Xcode 27, Kotlin/Native 2.3.21; paths shortened to `/w/...`).
 */
class IosSourceLinesTest {
  // `nm -n --defined-only` of the runtime's own object.
  private val nm =
    """
    00000000022634f0 t _kfun:net.multigesture.kanama.ios.KanamaIosRuntime#helper(){}
    0000000002263518 t _kfun:net.multigesture.kanama.ios#throwSelfTestScriptError(){}kotlin.Nothing
    0000000002263518 t ltmp42
    0000000002263540 s l_.str.12
    00000000022635b0 t _kfun:game.Player#ready(){}
    00000000022635f0 t l___unnamed_29210
    0000000002263640 T _kanama_ios_runtime_frame
    0000000002263700 t _kfun:game.Player.fire#internal
    0000000002263800 t _kfun:game.Player.fire#internal
    """
      .trimIndent()
      .lines()

  // `dwarfdump --debug-line`: DWARF 2, directory 0 (the compilation directory) left implicit.
  private val debugLine =
    """
    libkanama_ios_runtime.a.o:	file format Mach-O arm64

    .debug_line contents:
    debug_line[0x00000000]
    Line table prologue:
        total_length: 0x00998d09
              format: DWARF32
             version: 2
     prologue_length: 0x00007113
     min_inst_length: 1
    default_is_stmt: 1
           line_base: -5
          line_range: 14
         opcode_base: 13
    include_directories[  7] = "/w/kotlin/libraries/stdlib/src/kotlin/util"
    include_directories[ 30] = "/w/kanama/src/iosMain/kotlin/net/multigesture/kanama/ios"
    include_directories[ 31] = "/w/game/kotlin-src/game"
    file_names[   1]:
               name: "CTypeDefinitions"
          dir_index: 0
           mod_time: 0x00000000
             length: 0x00000000
    file_names[1137]:
               name: "Standard.kt"
          dir_index: 7
           mod_time: 0x00000000
             length: 0x00000000
    file_names[1139]:
               name: "KanamaIosScriptErrorProbe.kt"
          dir_index: 30
           mod_time: 0x00000000
             length: 0x00000000
    file_names[1140]:
               name: "Player.kt"
          dir_index: 31
           mod_time: 0x00000000
             length: 0x00000000
    file_names[1141]:
               name: "Standard.kt"
          dir_index: 31
           mod_time: 0x00000000
             length: 0x00000000

    Address            Line   Column File   ISA Discriminator OpIndex Flags
    ------------------ ------ ------ ------ --- ------------- ------- -------------
    0x00000000022634f0      0     32      1   0             0       0
    0x0000000002263504   2716      4      1   0             0       0  is_stmt
    0x0000000002263518      0      0   1139   0             0       0  is_stmt
    0x0000000002263564      9     10   1139   0             0       0  is_stmt prologue_end
    0x0000000002263570      0     10   1139   0             0       0
    0x0000000002263574     10      9   1139   0             0       0  is_stmt
    0x00000000022635b0     12      5   1140   0             0       0  is_stmt
    0x00000000022635c0    103      9   1137   0             0       0  is_stmt
    0x00000000022635c8    104      9   1137   0             0       0  is_stmt
    0x00000000022635d0     14      5   1140   0             0       0  is_stmt
    0x00000000022635e0     14      5   1140   0             0       0  is_stmt end_sequence
    0x0000000002263700     20      5   1140   0             0       0  is_stmt
    0x0000000002263710      7      5   1141   0             0       0  is_stmt
    """
      .trimIndent()
      .lines()

  private val probe = "kfun:net.multigesture.kanama.ios#throwSelfTestScriptError(){}kotlin.Nothing"
  private val probePath =
    "/w/kanama/src/iosMain/kotlin/net/multigesture/kanama/ios/KanamaIosScriptErrorProbe.kt"
  private val roots = listOf(File("/w/game/kotlin-src"), File(probePath))

  private fun isGame(path: String) = roots.any { path == it.path || path.startsWith(it.path + "/") }

  @Test
  fun nmKeepsTheFunctionStartsTheDeviceSees() {
    val symbols = IosSourceLines.parseNm(nm)

    assertEquals(
      listOf(
        "kfun:net.multigesture.kanama.ios.KanamaIosRuntime#helper(){}",
        probe,
        "kfun:game.Player#ready(){}",
        "kanama_ios_runtime_frame",
        "kfun:game.Player.fire#internal",
        "kfun:game.Player.fire#internal",
      ),
      symbols.map { it.name },
    )
    assertEquals(0x2263518L, symbols[1].address)
  }

  @Test
  fun debugLineRowsCarryTheFullPath() {
    val rows = IosSourceLines.parseDebugLine(debugLine.asSequence())

    assertEquals(12, rows.size, "end_sequence rows are left out")
    assertEquals("CTypeDefinitions", rows[0].path, "an implicit directory 0 keeps the bare name")
    assertEquals(probePath, rows[3].path)
    assertEquals(9, rows[3].line)
    assertEquals("/w/game/kotlin-src/game/Player.kt", rows[6].path)
    assertEquals("/w/kotlin/libraries/stdlib/src/kotlin/util/Standard.kt", rows[7].path)
  }

  @Test
  fun aRowNamingAnUndeclaredFileFails() {
    val broken = debugLine.map { it.replace("   1140   0", "   1199   0") }

    val error =
      assertFailsWith<IllegalStateException> { IosSourceLines.parseDebugLine(broken.asSequence()) }
    assertContains(error.message.orEmpty(), "file 1199")
  }

  // `atos -o <object> -arch arm64 -i -f <addresses>`: an inlined address (the lambda, then the
  // inline function, then the function it is inlined into), a plain one, one atos cannot place.
  private val atos =
    """
    <inlined-lambda> (in libkanama_ios_runtime.a.o) (Basis.kt:194)
    kfun:kotlin#also__at__0:0(kotlin.Function1<0:0,kotlin.Unit>){0§<kotlin.Any?>}0:0 (in libkanama_ios_runtime.a.o) (Standard.kt:103)
    kfun:net.multigesture.kanama.types.Basis.toGodotRealArray#internal (in libkanama_ios_runtime.a.o) (Basis.kt:193)

    kfun:net.multigesture.kanama.ios#throwSelfTestScriptError(){}kotlin.Nothing (in libkanama_ios_runtime.a.o) (KanamaIosScriptErrorProbe.kt:10)

    0x7fffffff0

    """
      .trimIndent()
      .lines()

  @Test
  fun atosChainsLineUpWithTheirAddresses() {
    val chains = IosSourceLines.parseAtosChains(atos, 3)

    assertEquals(
      listOf(
        IosSourceLines.Frame("Basis.kt", 194),
        IosSourceLines.Frame("Standard.kt", 103),
        IosSourceLines.Frame("Basis.kt", 193),
      ),
      chains[0],
    )
    assertEquals(listOf(IosSourceLines.Frame("KanamaIosScriptErrorProbe.kt", 10)), chains[1])
    assertEquals(listOf<IosSourceLines.Frame?>(null), chains[2])
  }

  @Test
  fun aMalformedAtosAnswerFails() {
    // A missing blank line merges two chains: every later chain would answer the wrong address.
    val merged = atos.filterIndexed { index, line -> !(index == 3 && line.isBlank()) }

    val error = assertFailsWith<IllegalStateException> { IosSourceLines.parseAtosChains(merged, 3) }
    assertContains(error.message.orEmpty(), "2 inline chains for 3 addresses")
  }

  private fun table(
    chains: Map<Long, List<IosSourceLines.Frame?>> = emptyMap()
  ): Map<String, IosSourceLines.Function> {
    val asked = mutableListOf<Long>()
    val functions =
      IosSourceLines.functions(
        IosSourceLines.parseNm(nm),
        IosSourceLines.parseDebugLine(debugLine.asSequence()),
        ::isGame,
        { it.substringAfterLast('/') },
      ) { addresses ->
        asked += addresses
        addresses.associateWith { chains[it].orEmpty() }
      }
    assertEquals(listOf(0x22635c0L, 0x22635c8L), asked, "atos is asked for the inlined rows only")
    return functions.associateBy { it.name }
  }

  @Test
  fun gameFunctionsGetTheirRowsAndUnknownLinesStayZero() {
    val functions = table()

    assertEquals(
      setOf(probe, "kfun:game.Player#ready(){}"),
      functions.keys,
      "runtime functions and the repeated #internal name are left out",
    )
    val probeFunction = functions.getValue(probe)
    assertEquals(0x98, probeFunction.size, "up to the next real symbol, past the ltmp label")
    assertEquals(
      listOf(
        IosSourceLines.Row(0x00, "", 0),
        IosSourceLines.Row(0x4c, "KanamaIosScriptErrorProbe.kt", 9),
        // Line 0 in the middle is "unknown", not the line before it.
        IosSourceLines.Row(0x58, "", 0),
        IosSourceLines.Row(0x5c, "KanamaIosScriptErrorProbe.kt", 10),
      ),
      probeFunction.rows,
    )
    // Inlined rows atos could not place in a game file are unknown too.
    assertEquals(
      listOf(
        IosSourceLines.Row(0x00, "Player.kt", 12),
        IosSourceLines.Row(0x10, "", 0),
        IosSourceLines.Row(0x20, "Player.kt", 14),
      ),
      functions.getValue("kfun:game.Player#ready(){}").rows,
    )
  }

  @Test
  fun anInlinedRowTakesTheInnermostGameFrame() {
    val chain =
      listOf(
        IosSourceLines.Frame("Standard.kt", 103),
        IosSourceLines.Frame("Player.kt", 13),
        IosSourceLines.Frame("Player.kt", 12),
      )
    val rows =
      table(mapOf(0x22635c0L to chain, 0x22635c8L to chain))
        .getValue("kfun:game.Player#ready(){}")
        .rows

    assertEquals(
      listOf(
        IosSourceLines.Row(0x00, "Player.kt", 12),
        IosSourceLines.Row(0x10, "Player.kt", 13),
        IosSourceLines.Row(0x20, "Player.kt", 14),
      ),
      rows,
    )
  }

  @Test
  fun anInlinedFrameWhoseBaseNameIsAmbiguousIsUnknown() {
    // The game has a `Standard.kt` too (game/Standard.kt), so atos's base name cannot tell the
    // game frame from the standard library's.
    val chain =
      listOf(IosSourceLines.Frame("Standard.kt", 103), IosSourceLines.Frame("Standard.kt", 7))

    val rows = table(mapOf(0x22635c0L to chain)).getValue("kfun:game.Player#ready(){}").rows

    assertEquals(IosSourceLines.Row(0x10, "", 0), rows[1])
  }

  @Test
  fun theCTableEscapesAndSortsLikeStrcmp() {
    val source =
      IosSourceLines.render(
        listOf(
          IosSourceLines.Function("kfun:b#é\"x?", 8, listOf(IosSourceLines.Row(0, "B.kt", 3))),
          IosSourceLines.Function("kfun:B#z", 4, listOf(IosSourceLines.Row(0, "B.kt", 1))),
        )
      )

    // Upper case sorts first; UTF-8 bytes, `"` and `?` are three-digit octal escapes.
    assertTrue(source.indexOf("kfun:B#z") < source.indexOf("kfun:b#"), source)
    assertContains(source, """kfun:b#\303\251\042x\077\000""")
    assertContains(source, "static const uint32_t k_symbol_count = 2u;")
    assertContains(source, "    {0u, 4u, 0u, 1u},\n")
    assertNull(Regex("""\\[0-7]{1,2}[^0-7]""").find(source), "every escape has three digits")
  }

  @Test
  fun aLibraryFromTheBuildCacheMatchesTheGameFilesByTheirPathFromTheRoot() {
    // The device run of task 131 item 13: Gradle's build cache gave the demo build a Kotlin/Native
    // library compiled in another checkout, so its DWARF names that checkout's paths.
    val root = File("/w/demos-wt/game/kotlin-src")
    val player = File(root, "Player.kt")
    val enemy = File(root, "enemies/Enemy.kt")
    val probeFile = File("/w/kanama-copy/src/iosMain/kotlin/net/multigesture/kanama/ios/Probe.kt")
    val matches =
      IosSourceLines.matchGameFiles(
        setOf(
          "/w/demos/game/kotlin-src/Player.kt",
          "/w/demos/game/kotlin-src/enemies/Enemy.kt",
          "/w/kanama/src/iosMain/kotlin/net/multigesture/kanama/ios/Probe.kt",
          "/w/kotlin/libraries/stdlib/src/kotlin/util/Standard.kt",
          "/w/demos/game/kotlin-src/Other.kt",
        ),
        listOf(root, probeFile),
        listOf(player, enemy, probeFile),
      )

    assertEquals(
      mapOf(
        "/w/demos/game/kotlin-src/Player.kt" to player,
        "/w/demos/game/kotlin-src/enemies/Enemy.kt" to enemy,
        "/w/kanama/src/iosMain/kotlin/net/multigesture/kanama/ios/Probe.kt" to probeFile,
      ),
      matches,
    )
  }

  @Test
  fun aGameFileTwoLibraryPathsMatchIsLeftOut() {
    val root = File("/w/game/kotlin-src")
    val player = File(root, "Player.kt")

    val matches =
      IosSourceLines.matchGameFiles(
        setOf(
          "/w/a/kotlin-src/Player.kt",
          "/w/b/kotlin-src/Player.kt",
          "/w/game/kotlin-src/Player.kt",
        ),
        listOf(root),
        listOf(player),
      )

    assertEquals(emptyMap(), matches)
  }

  @Test
  fun aFileInAGodotProjectIsReportedByItsResPath() {
    val project = File("/w/game")
    fun display(path: String) =
      IosSourceLines.displayPath(path, listOf(File("/w/game/kotlin-src")), { it == project })

    assertEquals("res://kotlin-src/game/Player.kt", display("/w/game/kotlin-src/game/Player.kt"))
    assertEquals(
      "KanamaIosScriptErrorProbe.kt",
      IosSourceLines.displayPath(probePath, listOf(File(probePath))) { false },
    )
  }
}
