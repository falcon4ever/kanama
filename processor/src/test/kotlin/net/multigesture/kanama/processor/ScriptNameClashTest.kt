package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * A script class may take any name, including one a generated file also imports: `Arena`
 * (`java.lang.foreign.Arena` in the desktop registrar) did not compile in the Bulletmark port, and
 * `Signals` (the runtime's `Signals`) is how many games name their event bus. Every emitter imports
 * the script class under an alias, so no import it writes can clash with the script's simple name.
 * The example project's `name_clash/Arena.kt` and `name_clash/Signals.kt` compile the desktop
 * registrar for real (`scripts/local_ci.sh` builds them).
 */
class ScriptNameClashTest {

  private fun model(simpleName: String, packageName: String = "net.multigesture.kanama.test") =
    ScriptModel(
      simpleName = simpleName,
      fqName = "$packageName.$simpleName",
      attachTo = "Node",
      isTool = false,
      isGlobalClass = false,
      properties =
        listOf(
          ScriptPropertyModel(
            kotlinName = "rounds",
            godotName = "rounds",
            type = TypeMapping.INT,
            isMutable = true,
          )
        ),
      toolButtons = emptyList(),
      virtuals = listOf(VirtualModel("_ready", "callReady", "ready")),
      methods =
        listOf(
          MethodModel(
            kotlinName = "nextRound",
            godotName = "next_round",
            returnType = TypeMapping.INT,
            args = listOf(ArgModel("round", TypeMapping.INT)),
            kind = MethodKind.REGULAR,
          )
        ),
      signals = listOf(SignalModel("round_ended", listOf(ArgModel("round", TypeMapping.INT)))),
    )

  private fun importedSimpleNames(source: String): List<String> =
    source
      .lines()
      .filter { it.startsWith("import ") }
      .map { line ->
        val target = line.removePrefix("import ").trim()
        if (" as " in target) target.substringAfter(" as ").trim()
        else target.substringAfterLast('.')
      }

  private fun assertNoImportClash(source: String) {
    val names = importedSimpleNames(source)
    assertTrue(names.size == names.toSet().size, "two imports share a simple name: $names\n$source")
  }

  @Test
  fun theDesktopRegistrarImportsTheScriptUnderAnAlias() {
    for (name in listOf("Arena", "Signals", "MemorySegment", "VariantType")) {
      val source = ScriptCodeEmitter(model(name), "${name}ScriptRegistrar").emit()
      assertTrue(
        source.contains("import net.multigesture.kanama.test.$name as $SCRIPT_TYPE_ALIAS"),
        source,
      )
      assertFalse(source.contains("import net.multigesture.kanama.test.$name\n"), source)
      assertNoImportClash(source)
      assertTrue(
        source.contains(
          "val kt = $SCRIPT_TYPE_ALIAS(net.multigesture.kanama.api.GodotHandle(godotObject))"
        ),
        source,
      )
      assertTrue(
        source.contains("fun nextRound(instance: $SCRIPT_TYPE_ALIAS, round: Long): Long ="),
        source,
      )
      assertTrue(source.contains("kotlinScriptInstance<$SCRIPT_TYPE_ALIAS>()"), source)
    }
  }

  @Test
  fun theWebRegistryImportsEachScriptUnderAnAlias() {
    // `GodotHandle` and `Vector2i` are names the registry imports; two `Arena`s live in two
    // packages.
    val source =
      WebScriptCodeEmitter(
          listOf(
            WebScriptInput(model("Arena"), "res://a/Arena.kt"),
            WebScriptInput(model("Arena", "net.multigesture.kanama.other"), "res://b/Arena.kt"),
            WebScriptInput(model("GodotHandle"), "res://c/GodotHandle.kt"),
            WebScriptInput(model("Vector2i"), "res://d/Vector2i.kt"),
          )
        )
        .registrySource()
    assertNoImportClash(source)
    assertTrue(source.contains("import net.multigesture.kanama.test.Arena as S1_Arena"), source)
    assertTrue(source.contains("import net.multigesture.kanama.other.Arena as S2_Arena"), source)
    assertTrue(
      source.contains("import net.multigesture.kanama.test.GodotHandle as S3_GodotHandle"),
      source,
    )
    assertTrue(source.contains("2 -> S2_Arena(WebObjectId(objectId))"), source)
    assertTrue(source.contains("(script as S4_Vector2i).ready()"), source)
  }

  @Test
  fun theIosRegistryImportsEachScriptUnderAnAlias() {
    val source =
      IosScriptCodeEmitter(
          listOf(
            IosScriptInput(model("Arena"), "res://Arena.kt"),
            IosScriptInput(model("MemorySegment"), "res://MemorySegment.kt"),
          ),
          warn = {},
          error = {},
          allowSkips = false,
        )
        .registrySource()
    assertNoImportClash(source)
    assertTrue(source.contains("import net.multigesture.kanama.test.Arena as S1_Arena"), source)
    assertTrue(
      source.contains("import net.multigesture.kanama.test.MemorySegment as S2_MemorySegment"),
      source,
    )
    assertTrue(source.contains("private val script: S2_MemorySegment,"), source)
    assertTrue(
      source.contains(
        "(S1_Arena(net.multigesture.kanama.api.GodotHandle(MemorySegment.ofAddress(ownerObject))))"
      ),
      source,
    )
  }
}
