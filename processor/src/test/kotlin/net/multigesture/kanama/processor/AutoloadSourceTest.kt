package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue
import net.multigesture.kanama.processor.AutoloadSource.Entry
import net.multigesture.kanama.processor.AutoloadSource.Kind
import net.multigesture.kanama.processor.AutoloadSource.Resolved
import net.multigesture.kanama.processor.AutoloadSource.SceneRoot

/**
 * Task 133 C — `Autoloads.<Name>` from `project.godot`: parsing the `[autoload]` section, typing a
 * scene autoload by its root and a GDScript one by its `extends`, and the source the desktop/iOS
 * and Web builds compile. The example project's `AutoloadSmoke.kt` reads the generated object in
 * Godot (`scripts/runtime_smoke.sh`).
 */
class AutoloadSourceTest {

  @Test
  fun parsesTheAutoloadSection() {
    val project =
      """
      config_version=5

      [application]
      config/name="Demo"

      [autoload]

      Settings="*res://kotlin-src/Settings.kt"
      CameraMode="*res://camera_mode/camera_mode.tscn"
      Hidden="res://hidden.gd"

      [display]
      window/size/viewport_width=1920
      """
        .trimIndent()
    assertEquals(
      listOf(
        Entry("Settings", "res://kotlin-src/Settings.kt", true),
        Entry("CameraMode", "res://camera_mode/camera_mode.tscn", true),
        Entry("Hidden", "res://hidden.gd", false),
      ),
      AutoloadSource.parseProjectGodot(project),
    )
    assertEquals(emptyList(), AutoloadSource.parseProjectGodot("[application]\nx=1\n"))
  }

  @Test
  fun readsASceneRoot() {
    val kotlinRoot =
      """
      [gd_scene format=3 uid="uid://q3osrwkgcbi0"]

      [ext_resource type="Script" path="res://kotlin-src/CameraMode.kt" id="1_iqqgo"]

      [node name="CameraMode" type="Node3D" unique_id=1506626699]
      process_mode = 3
      script = ExtResource("1_iqqgo")

      [node name="Child" type="Node" parent="."]
      script = ExtResource("other")
      """
        .trimIndent()
    assertEquals(
      SceneRoot("Node3D", "res://kotlin-src/CameraMode.kt", false),
      AutoloadSource.parseSceneRoot(kotlinRoot),
    )
    val plain =
      """
      [gd_scene format=3]
      [ext_resource type="AudioStream" path="res://loop.ogg" id="1"]
      [node name="MusicPlayer" type="AudioStreamPlayer" unique_id=1]
      stream = ExtResource("1")
      """
        .trimIndent()
    assertEquals(SceneRoot("AudioStreamPlayer", null, false), AutoloadSource.parseSceneRoot(plain))
    val inherited = "[gd_scene format=3]\n[node name=\"Root\" instance=ExtResource(\"1\")]\n"
    assertEquals(SceneRoot(null, null, true), AutoloadSource.parseSceneRoot(inherited))
    assertNull(AutoloadSource.parseSceneRoot("[gd_resource type=\"Theme\" format=3]\n"))
  }

  @Test
  fun readsAGdscriptExtends() {
    assertEquals("Node", AutoloadSource.gdscriptExtends("extends Node\n\nvar x = 1\n"))
    assertEquals(
      "AudioStreamPlayer",
      AutoloadSource.gdscriptExtends(
        "@tool\nclass_name Music\n# comment\nextends AudioStreamPlayer\n"
      ),
    )
    assertNull(AutoloadSource.gdscriptExtends("extends \"res://base.gd\"\n"))
    assertNull(AutoloadSource.gdscriptExtends("var x = 1\n"))
  }

  @Test
  fun readsAOneLineClassNameExtends() {
    // Task 133 C2: GDScript's one-line form.
    assertEquals(
      "Node2D",
      AutoloadSource.gdscriptExtends("class_name Hud extends Node2D # the HUD\nvar x = 1\n"),
    )
  }

  @Test
  fun aFeatureTagKeyIsAnOverrideNotAnAutoload() {
    val entries =
      AutoloadSource.parseProjectGodot(
        "[autoload]\n\nMusic=\"*res://music.gd\"\nMusic.android=\"*res://music_mobile.gd\"\n"
      )
    assertEquals(listOf(false, true), entries.map(AutoloadSource::isFeatureOverride))
  }

  @Test
  fun readsTheUidAFileDeclares() {
    assertEquals("uid://b6x2", AutoloadSource.declaredUid("music.gd.uid", "uid://b6x2\n"))
    assertEquals(
      "uid://c3sp",
      AutoloadSource.declaredUid(
        "settings.tscn",
        "[gd_scene load_steps=2 format=3 uid=\"uid://c3sp\"]\n\n[node name=\"S\" type=\"Node\"]\n",
      ),
    )
    assertNull(AutoloadSource.declaredUid("old.tscn", "[gd_scene load_steps=2 format=3]\n"))
    assertNull(AutoloadSource.declaredUid("music.gd", "extends Node\n"))
  }

  private val autoloads =
    listOf(
      Resolved(
        Entry("Settings", "res://kotlin-src/Settings.kt", true),
        Kind.Script("tps.Settings"),
        "res://kotlin-src/Settings.kt",
      ),
      Resolved(
        Entry("MusicPlayer", "res://MusicPlayer.tscn", true),
        Kind.Node("AudioStreamPlayer", "net.multigesture.kanama.api.AudioStreamPlayer"),
        "res://MusicPlayer.tscn",
      ),
      Resolved(
        Entry("in", "res://in.gd", true),
        Kind.Node("Node", "net.multigesture.kanama.api.Node"),
        "res://in.gd",
      ),
    )

  @Test
  fun emitsTypedNativeAccessors() {
    val source = AutoloadSource.emit(autoloads, "net.multigesture.kanama.generated", web = false)
    assertTrue(source.contains("package net.multigesture.kanama.generated"))
    assertTrue(source.contains("object Autoloads {"))
    assertTrue(
      source.contains(
        "val Settings: tps.Settings get() = AutoloadAccess.script<tps.Settings>(\"Settings\")"
      ),
      source,
    )
    assertTrue(
      source.contains(
        "val MusicPlayer: net.multigesture.kanama.api.AudioStreamPlayer get() = " +
          "AutoloadAccess.node<net.multigesture.kanama.api.AudioStreamPlayer>(\"MusicPlayer\")"
      ),
      source,
    )
    // A name that is a Kotlin keyword is escaped.
    assertTrue(source.contains("val `in`: net.multigesture.kanama.api.Node get()"), source)
  }

  @Test
  fun emitsWebAccessorsWithoutAClassTokenTable() {
    val source = AutoloadSource.emit(autoloads, "net.multigesture.kanama.generated", web = true)
    assertTrue(
      source.contains(
        "AutoloadAccess.node(\"MusicPlayer\", \"AudioStreamPlayer\") { " +
          "net.multigesture.kanama.api.AudioStreamPlayer(it) }"
      ),
      source,
    )
    assertTrue(source.contains("AutoloadAccess.script<tps.Settings>(\"Settings\")"))
  }
}
