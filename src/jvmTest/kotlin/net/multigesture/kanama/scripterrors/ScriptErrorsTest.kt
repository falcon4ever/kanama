package net.multigesture.kanama.scripterrors

import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import net.multigesture.kanama.binding.runtime.ScriptErrorReport
import net.multigesture.kanama.binding.runtime.ScriptErrors
import net.multigesture.kanama.binding.runtime.Upcalls

/**
 * Task 131 item 1 (F4): a contained Kotlin exception is reported to Godot as a script error naming
 * the Kotlin file:line of the top game frame. The engine call itself is proved by the runtime smoke
 * (`script_error_smoke.tscn`); here the report goes to the test sink.
 *
 * This file's package is deliberately outside Kanama's runtime packages, so its own frames count as
 * "game" frames.
 */
class ScriptErrorsTest {
  private val reports = mutableListOf<ScriptErrorReport>()

  init {
    ScriptErrors.sinkOverride = { reports += it }
  }

  @AfterTest
  fun clearSink() {
    ScriptErrors.sinkOverride = null
  }

  private fun throwable(message: String?, vararg frames: StackTraceElement): Throwable =
    IllegalStateException(message).apply { stackTrace = arrayOf(*frames) }

  private fun frame(cls: String, method: String, file: String?, line: Int) =
    StackTraceElement(cls, method, file, line)

  @Test
  fun reportsTheTopGameFrameNotTheRuntimeOrTheJdk() {
    val t =
      throwable(
        "boom",
        frame("java.util.ArrayList", "get", "ArrayList.java", 427),
        frame("net.multigesture.kanama.api.Node", "getChild", "Node.kt", 900),
        frame("com.example.game.Player", "ready", "Player.kt", 42),
        frame("net.multigesture.kanama.generated.PlayerScriptRegistrar", "dispatch", "Gen.kt", 1),
        frame("net.multigesture.kanama.binding.ScriptBridge", "siCall", "ScriptBridge.kt", 480),
      )

    val report = ScriptErrors.reportFor(t, "Player._ready")

    assertEquals("java.lang.IllegalStateException", report.description)
    assertEquals("java.lang.IllegalStateException: boom", report.message)
    assertEquals("Player.ready", report.function)
    assertEquals("Player.kt", report.file)
    assertEquals(42, report.line)
  }

  @Test
  fun gameScriptsUnderTheKanamaRootPackageAreGameCode() {
    // The example project's scripts live in net.multigesture.kanama.example.
    val t =
      throwable(
        null,
        frame("net.multigesture.kanama.binding.runtime.ObjectCalls", "call", "ObjectCalls.kt", 3),
        frame("net.multigesture.kanama.example.HelloScript", "ready", "HelloScript.kt", 77),
      )

    val report = ScriptErrors.reportFor(t, "HelloScript._ready")

    assertEquals("java.lang.IllegalStateException", report.message) // no message: the class alone
    assertEquals("HelloScript.ready", report.function)
    assertEquals("HelloScript.kt", report.file)
    assertEquals(77, report.line)
  }

  @Test
  fun aWrappingExceptionIsSeenThroughToTheGameFrameOfItsCause() {
    val cause = throwable("inner", frame("com.example.game.Enemy", "hit", "Enemy.kt", 12))
    val wrapper =
      RuntimeException("wrapped", cause).apply {
        stackTrace =
          arrayOf(frame("net.multigesture.kanama.binding.ScriptBridge", "siCall", "SB.kt", 480))
      }

    val report = ScriptErrors.reportFor(wrapper, "Enemy.hit")

    assertEquals("java.lang.RuntimeException: wrapped", report.message)
    assertEquals("Enemy.kt", report.file)
    assertEquals(12, report.line)
  }

  @Test
  fun aRuntimeFailureFallsBackToTheTopKanamaFrameAndASiteWithoutFramesToTheLabel() {
    val runtimeOnly =
      throwable(
        "loader",
        frame("java.nio.file.Files", "read", "Files.java", 10),
        frame("net.multigesture.kanama.binding.KanamaResourceFormatLoader", "load", "L.kt", 216),
      )
    val fromRuntime = ScriptErrors.reportFor(runtimeOnly, "KanamaResourceFormatLoader.load")
    assertEquals("KanamaResourceFormatLoader.load", fromRuntime.function)
    assertEquals("L.kt", fromRuntime.file)
    assertEquals(216, fromRuntime.line)

    val noFrames = ScriptErrors.reportFor(throwable("x"), "Upcall.site")
    assertEquals("Upcall.site", noFrames.function)
    assertEquals("", noFrames.file)
    assertEquals(0, noFrames.line)
  }

  @Test
  fun anR8FrameWithoutSourceIsNeverAttributedAsGameCode() {
    // R8 renames classes and files; `a.b.c` here may be an obfuscated runtime class.
    val t =
      throwable(
        "minified",
        frame("a.b.c", "a", "SourceFile", 12),
        frame("a.b.d", "b", null, -1),
        frame("net.multigesture.kanama.binding.ScriptBridge", "siCall", null, -1),
      )

    val report = ScriptErrors.reportFor(t, "Player._process")

    assertEquals("Player._process", report.function) // the containment label
    assertEquals("", report.file)
    assertEquals(0, report.line)
  }

  @Test
  fun aGamePackageUnderTheKanamaRootIsGameCodeButTheRootRuntimeClassIsNot() {
    assertFalse(ScriptErrorReport.isRuntimeClass("net.multigesture.kanama.example.HelloScript"))
    assertFalse(ScriptErrorReport.isRuntimeClass("net.multigesture.kanama.example")) // K/N package
    assertFalse(ScriptErrorReport.isRuntimeClass("net.multigesture.kanama.mygame.Player"))
    assertTrue(ScriptErrorReport.isRuntimeClass("net.multigesture.kanama.KanamaBinding"))
    assertTrue(ScriptErrorReport.isRuntimeClass("net.multigesture.kanama.KanamaBinding\$init\$1"))
    assertTrue(ScriptErrorReport.isRuntimeClass("net.multigesture.kanama.binding.runtime"))
    assertFalse(ScriptErrorReport.isRuntimeClass("")) // a default-package top-level function
  }

  @Test
  fun aTopLevelFunctionKeepsItsFileFacade() {
    val t = throwable("x", frame("com.example.game.HelpersKt", "spawn", "Helpers.kt", 5))
    assertEquals("HelpersKt.spawn", ScriptErrors.reportFor(t, "site").function)
  }

  @Test
  fun aRealThrowIsAttributedToTheLineThatThrew() {
    val t = runCatching { failHere() }.exceptionOrNull()
    assertNotNull(t)

    val report = ScriptErrors.reportFor(t, "site")

    assertEquals("ScriptErrorsTest.failHere", report.function)
    assertEquals("ScriptErrorsTest.kt", report.file)
    assertEquals(failLine, report.line)
  }

  private var failLine = 0

  private fun failHere() {
    failLine = Throwable().stackTrace[0].lineNumber + 1
    throw IllegalArgumentException("deliberate")
  }

  @Test
  fun reportGoesToTheSinkAndDoesNotReenter() {
    var inner: Boolean? = null
    ScriptErrors.sinkOverride = {
      reports += it
      inner = ScriptErrors.report(IllegalStateException("nested"), "nested")
    }

    assertTrue(ScriptErrors.report(IllegalStateException("outer"), "outer"))

    assertEquals(false, inner) // a report raised while reporting is dropped, not recursed
    assertEquals(listOf("java.lang.IllegalStateException: outer"), reports.map { it.message })
  }

  @Test
  fun theUpcallContainmentFloorReportsWhatItContains() {
    val target =
      MethodHandles.lookup()
        .findStatic(
          ScriptErrorsTest::class.java,
          "throwingUpcall",
          MethodType.methodType(Int::class.javaPrimitiveType),
        )

    val result = Upcalls.contain(target, "Smoke.throwingUpcall").invoke() as Int

    assertEquals(0, result) // contained: the zero of the return type
    val report = reports.single()
    assertEquals("java.lang.IllegalStateException: upcall failure", report.message)
    assertEquals("ScriptErrorsTest.kt", report.file)
    assertTrue(report.function.endsWith("throwingUpcall"), report.function)
  }

  @Test
  fun parsesKotlinNativeStackTraceLines() {
    val withSource =
      ScriptErrorReport.parseNativeFrame(
        "at 3   libkanama   0x0000000104a1c2f3c kfun:com.example.game.Player#ready(){} + 52 " +
          "(/Users/dev/game/kotlin-src/com/example/game/Player.kt:12:5)"
      )
    assertNotNull(withSource)
    assertEquals("com.example.game.Player", withSource.className)
    assertEquals("ready", withSource.methodName)
    assertEquals("Player.kt", withSource.fileName)
    assertEquals(12, withSource.line)

    val withoutSource =
      ScriptErrorReport.parseNativeFrame(
        "at 4   libkanama   0x0000000104a1c3000 kfun:net.multigesture.kanama.ios#kanamaIosRuntimeScriptInstanceReady(kotlin.Long){} + 20"
      )
    assertNotNull(withoutSource)
    assertEquals("net.multigesture.kanama.ios", withoutSource.className)
    assertEquals("kanamaIosRuntimeScriptInstanceReady", withoutSource.methodName)
    assertEquals("", withoutSource.fileName)
    assertEquals(0, withoutSource.line)

    assertNull(ScriptErrorReport.parseNativeFrame("at 9   libdyld.dylib   0x1 start + 4"))

    // A top-level function in the default package has an empty owner.
    val defaultPackage =
      ScriptErrorReport.parseNativeFrame("at 2 lib 0x3 kfun:#spawn(){} + 9 (/g/Main.kt:7:3)")
    assertNotNull(defaultPackage)
    assertEquals("", defaultPackage.className)
    assertEquals("spawn", defaultPackage.methodName)
    assertEquals(7, defaultPackage.line)
  }

  @Test
  fun aNativeGameExceptionReportsItsThrowSiteNotItsConstructor() {
    // Kotlin/Native traces start inside the constructor chain, unlike the JVM's.
    val frames =
      listOf(
          "at 0 lib 0x1 kfun:kotlin.Throwable#<init>(kotlin.String?){} + 8 (/k/Throwable.kt:24:37)",
          "at 1 lib 0x2 kfun:kotlin.Exception#<init>(kotlin.String?){} + 4 (/k/Exceptions.kt:23:1)",
          "at 2 lib 0x3 kfun:com.example.game.SpawnError#<init>(kotlin.String){} + 4 (/g/SpawnError.kt:3:1)",
          "at 3 lib 0x4 kfun:com.example.game.Spawner#spawn(){} + 9 (/g/Spawner.kt:31:9)",
        )
        .mapNotNull(ScriptErrorReport::parseNativeFrame)

    val dropped = ScriptErrorReport.dropOwnConstructorFrames(frames, "com.example.game.SpawnError")
    assertEquals("spawn", dropped.first().methodName)

    // A game constructor that threw a platform exception is kept: it is the throw site.
    val inInit =
      listOf(
          "at 0 lib 0x1 kfun:kotlin.Throwable#<init>(kotlin.String?){} + 8 (/k/Throwable.kt:24:37)",
          "at 1 lib 0x2 kfun:kotlin.IllegalStateException#<init>(kotlin.String?){} + 4 (/k/E.kt:7:1)",
          "at 2 lib 0x3 kfun:com.example.game.Board#<init>(){} + 4 (/g/Board.kt:12:5)",
        )
        .mapNotNull(ScriptErrorReport::parseNativeFrame)
    assertEquals(
      "com.example.game.Board",
      ScriptErrorReport.dropOwnConstructorFrames(inInit, "kotlin.IllegalStateException")
        .first()
        .className,
    )
    // An unknown exception name drops only the platform constructor frames.
    assertEquals(
      "com.example.game.SpawnError",
      ScriptErrorReport.dropOwnConstructorFrames(frames, "<unknown>").first().className,
    )
  }

  @Test
  fun aNativeReleaseFrameWithoutSourceStillNamesTheGameFunction() {
    val lines =
      listOf(
        "at 0 lib 0x1 kfun:com.example.game.Player#ready(){} + 52",
        "at 1 lib 0x2 kfun:net.multigesture.kanama.ios#kanamaIosRuntimeScriptInstanceReady(){} + 4",
      )

    val report =
      ScriptErrorReport.of(
        IllegalStateException("x"),
        "site",
        "kotlin.IllegalStateException",
        sourcelessGameFrames = true,
      ) {
        lines.mapNotNull(ScriptErrorReport::parseNativeFrame)
      }

    assertEquals("Player.ready", report.function)
    assertEquals("", report.file)
    assertEquals(0, report.line)
  }

  @Test
  fun aNativeTraceReportsTheGameFrameAndSkipsTheIosRuntime() {
    val lines =
      listOf(
        "at 0 lib 0x1 kfun:kotlin.Throwable#<init>(kotlin.String?){} + 8 (/k/Throwable.kt:24:37)",
        "at 1 lib 0x2 kfun:kotlin.IllegalStateException#<init>(kotlin.String?){} + 4 (/k/Exceptions.kt:7:1)",
        "at 2 lib 0x3 kfun:com.example.game#spawnEnemy(){} + 9 (/g/Spawner.kt:31:9)",
        "at 3 lib 0x4 kfun:net.multigesture.kanama.ios#kanamaIosRuntimeScriptInstanceCallV(){} + 1",
      )
    val t = IllegalStateException("native")

    val report =
      ScriptErrorReport.of(t, "site", "kotlin.IllegalStateException") {
        lines.mapNotNull(ScriptErrorReport::parseNativeFrame)
      }

    assertEquals("spawnEnemy", report.function) // a package owner: the function alone
    assertEquals("Spawner.kt", report.file)
    assertEquals(31, report.line)
    assertFalse(ScriptErrorReport.isRuntimeClass("com.example.game"))
    assertTrue(ScriptErrorReport.isRuntimeClass("net.multigesture.kanama.ios"))
  }

  companion object {
    @JvmStatic fun throwingUpcall(): Int = throw IllegalStateException("upcall failure")
  }
}
