package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Task 131 item 4 (F14): a direct call to an `@Rpc` function runs on this peer only; the processor
 * warns and names the generated `<Class>Rpcs` sender. `callLocal` functions and generated code are
 * exempt. The lint is backend-neutral (it reads the sources, not an emitter's output), so one suite
 * covers desktop, iOS and Web builds alike.
 */
class RpcDirectCallLintTest {
  private val menuPath = "/p/kotlin-src/Menu.kt"

  private fun target(
    name: String,
    callLocal: Boolean = false,
    className: String = "Menu",
    file: String = menuPath,
  ) =
    RpcDirectCallLint.Target(
      className = className,
      kotlinName = name,
      godotName = name.replace(Regex("([A-Z])")) { "_" + it.value.lowercase() },
      callLocal = callLocal,
      declaringFile = file,
    )

  private val menu =
    """
    package tps

    class Menu {
        @Rpc
        fun prepareGame() {
        }

        @Rpc(callLocal = true)
        fun enterGame() {
        }

        fun onHostPressed() {
            prepareGame()
            enterGame()
        }
    }
    """
      .trimIndent()

  @Test
  fun anUnqualifiedCallInTheDeclaringFileWarnsAndNamesTheSender() {
    val warnings =
      RpcDirectCallLint.warnings(
        listOf(target("prepareGame"), target("enterGame", callLocal = true)),
        listOf(RpcDirectCallLint.Source(menuPath, menu)),
      )
    val warning = warnings.single()
    assertTrue(warning.startsWith("$menuPath:13: prepareGame()"), warning)
    assertTrue(warning.contains("MenuRpcs.rpcPrepareGame(instance)"), warning)
    assertTrue(warning.contains("MenuRpcs.rpcIdPrepareGame(instance, peerId)"), warning)
    assertTrue(warning.contains("MenuRpcs.callLocalPrepareGame(instance)"), warning)
  }

  @Test
  fun callLocalFunctionsAreExempt() {
    val warnings =
      RpcDirectCallLint.warnings(
        listOf(target("enterGame", callLocal = true)),
        listOf(RpcDirectCallLint.Source(menuPath, menu)),
      )
    assertEquals(emptyList(), warnings)
  }

  @Test
  fun senderCallsDeclarationsReferencesCommentsAndStringsDoNotWarn() {
    val source =
      """
      package tps

      class Menu {
          @Rpc
          fun prepareGame() {}

          fun host() {
              MenuRpcs.rpcPrepareGame(this)
              MenuRpcs.rpcIdPrepareGame(this, 2L)
              val ref = ::prepareGame
              // prepareGame() runs on every peer
              /* prepareGame() */
              GD.print("prepareGame()")
              val raw = ""${'"'}prepareGame()""${'"'}
          }
      }
      """
        .trimIndent()
    val warnings =
      RpcDirectCallLint.warnings(
        listOf(target("prepareGame")),
        listOf(RpcDirectCallLint.Source(menuPath, source)),
      )
    assertEquals(emptyList(), warnings)
  }

  @Test
  fun thisQualifiedCallWarns() {
    val source = "class Menu {\n  @Rpc fun prepareGame() {}\n  fun f() { this.prepareGame() }\n}"
    val warnings =
      RpcDirectCallLint.warnings(
        listOf(target("prepareGame")),
        listOf(RpcDirectCallLint.Source(menuPath, source)),
      )
    assertTrue(warnings.single().startsWith("$menuPath:3:"), warnings.toString())
  }

  @Test
  fun aQualifiedCallFromAnotherScriptWarnsWhenTheNameIsUnambiguous() {
    val robotPath = "/p/kotlin-src/RedRobot.kt"
    val robot = "class RedRobot {\n  @Rpc fun hit() {}\n}"
    val bullet =
      "class Bullet {\n  fun f(node: Node) {\n    node.kotlinScriptInstance<RedRobot>()?.hit()\n  }\n}"
    val warnings =
      RpcDirectCallLint.warnings(
        listOf(target("hit", className = "RedRobot", file = robotPath)),
        listOf(
          RpcDirectCallLint.Source(robotPath, robot),
          RpcDirectCallLint.Source("/p/kotlin-src/Bullet.kt", bullet),
        ),
      )
    val warning = warnings.single()
    assertTrue(warning.startsWith("/p/kotlin-src/Bullet.kt:3: hit()"), warning)
    assertTrue(warning.contains("RedRobotRpcs.rpcHit(instance)"), warning)
  }

  @Test
  fun aQualifiedCallIsNotFlaggedWhenTheNameIsAmbiguousOrAnEngineMethod() {
    val robotPath = "/p/kotlin-src/RedRobot.kt"
    val robot = "class RedRobot {\n  @Rpc fun hit() {}\n  @Rpc fun play() {}\n}"
    // Another class declares its own `hit()`; `play()` is AnimationPlayer.play.
    val other =
      "class Wall {\n  fun hit() {}\n  fun f(w: Wall, a: AnimationPlayer) { w.hit(); a.play() }\n}"
    val warnings =
      RpcDirectCallLint.warnings(
        listOf(
          target("hit", className = "RedRobot", file = robotPath),
          target("play", className = "RedRobot", file = robotPath),
        ),
        listOf(
          RpcDirectCallLint.Source(robotPath, robot),
          RpcDirectCallLint.Source("/p/kotlin-src/Wall.kt", other),
        ),
        isEngineMethod = { it == "play" },
      )
    assertEquals(emptyList(), warnings)
  }

  @Test
  fun generatedHelpersAreExempt() {
    // Web's local-peer Rpcs helpers and the desktop *Methods helpers call the function itself.
    val generated =
      """
      package net.multigesture.kanama.generated

      object MenuRpcs {
          fun rpcPrepareGame(instance: tps.Menu) { instance.prepareGame() }
      }
      """
        .trimIndent()
    val warnings =
      RpcDirectCallLint.warnings(
        listOf(target("prepareGame")),
        listOf(
          RpcDirectCallLint.Source(menuPath, "class Menu {\n  @Rpc fun prepareGame() {}\n}"),
          RpcDirectCallLint.Source("/p/build/generated/ksp/MenuRpcs.kt", generated),
        ),
      )
    assertEquals(emptyList(), warnings)
  }

  @Test
  fun theEngineMethodTableKnowsCommonMethodNames() {
    assertTrue(EngineMethodTable.isMethodOfAnyClass("play"))
    assertTrue(!EngineMethodTable.isMethodOfAnyClass("prepare_game"))
  }
}
