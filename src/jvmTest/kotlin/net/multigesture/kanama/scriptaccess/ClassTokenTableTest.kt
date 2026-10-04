package net.multigesture.kanama.scriptaccess

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import net.multigesture.kanama.api.AudioStreamPlayer
import net.multigesture.kanama.api.Camera3D
import net.multigesture.kanama.api.FileAccessHandle
import net.multigesture.kanama.api.GodotClasses
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.PackedScene
import net.multigesture.kanama.api.PlatformGodotClasses
import net.multigesture.kanama.api.RefCounted
import net.multigesture.kanama.api.SharedGodotClasses
import net.multigesture.kanama.api.Timer
import net.multigesture.kanama.api.Tween

/**
 * Task 133 item 2 (F3): the generated class-token tables behind `castOrNull<T>()`, `requireAs<T>()`
 * and the `node<T>()` / `preload<T>()` delegates. The engine-side half (`Object.is_class` and the
 * wrap) is proved by the runtime smoke (`script_access_smoke.tscn`); here the tables themselves.
 */
class ClassTokenTableTest {
  private val tables = listOf(SharedGodotClasses, PlatformGodotClasses)

  @Test
  fun tablesAreIndexAlignedAndHaveNoDuplicates() {
    for (table in tables) {
      assertEquals(table.classes.size, table.names.size)
      assertEquals(table.classes.size, table.classes.toSet().size, "duplicate class")
      assertEquals(table.names.size, table.names.toSet().size, "duplicate Godot name")
    }
    val shared = SharedGodotClasses.names.toSet()
    val platform = PlatformGodotClasses.names.toSet()
    assertTrue(
      (shared intersect platform).isEmpty(),
      "a class is in both tables: ${shared intersect platform}",
    )
    assertEquals(shared.size + platform.size, GodotClasses.size)
  }

  @Test
  fun wrappersMapToTheirGodotClassNames() {
    val expected =
      mapOf(
        GodotObject::class to "Object",
        RefCounted::class to "RefCounted",
        Node::class to "Node",
        Timer::class to "Timer",
        Camera3D::class to "Camera3D",
        PackedScene::class to "PackedScene",
        // per-platform classes, from the desktop table
        AudioStreamPlayer::class to "AudioStreamPlayer",
        Tween::class to "Tween",
        FileAccessHandle::class to "FileAccess",
      )
    for ((type, godotName) in expected) {
      assertEquals(godotName, GodotClasses.token(type).godotName, "token of ${type.simpleName}")
    }
  }

  /** The table lists every class the shared tree declares with a handle constructor. */
  @Test
  fun everySharedWrapperClassIsInTheTable() {
    val apiDir = File("src/commonMain/kotlin/net/multigesture/kanama/api")
    assertTrue(apiDir.isDirectory, "run from the repository root: ${apiDir.absolutePath}")
    val declaration =
      Regex(
        """(?m)^(?:(?:open|abstract)\s+)*class\s+(\w+)\s*(?:internal\s+constructor\s*)?\(\s*(?:val\s+)?handle\s*:\s*GodotHandle"""
      )
    val declared =
      apiDir
        .listFiles { file -> file.name.endsWith(".kt") && !file.name.endsWith(".expect.kt") }!!
        .flatMap { file -> declaration.findAll(file.readText()).map { it.groupValues[1] }.toList() }
        .map { if (it == "GodotObject") "Object" else it }
        .toSet()
    val listed = SharedGodotClasses.names.toSet()
    assertEquals(emptySet(), declared - listed, "shared wrapper classes missing from the table")
    assertEquals(emptySet(), listed - declared, "table entries without a shared wrapper class")
  }

  /** A class the tables do not list (here a subclass the build would refuse) is a clear error. */
  @Test
  fun aClassThatIsNotAGeneratedWrapperIsRejected() {
    class NotAWrapper(handle: GodotHandle) : Node(handle)

    val error = assertFailsWith<IllegalArgumentException> { GodotClasses.token(NotAWrapper::class) }
    assertTrue(error.message!!.contains("NotAWrapper is not a Kanama wrapper"), error.message)

    // A delegate resolves its token on first read and names the property it backs.
    val named =
      assertFailsWith<IllegalArgumentException> {
        GodotClasses.token(NotAWrapper::class, "Main.scoreTimer")
      }
    assertTrue(named.message!!.startsWith("Main.scoreTimer: NotAWrapper is not"), named.message)
  }
}
