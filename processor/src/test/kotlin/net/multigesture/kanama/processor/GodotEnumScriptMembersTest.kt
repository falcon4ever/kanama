package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Task 128 B — Godot enum value classes (`Node.ProcessMode`, `Node.ProcessThreadMessages`) as
 * script members, on the desktop, iOS and Web emitters.
 *
 * The wire stays INT on every backend; the emitters wrap the raw `Long` into the value class on the
 * way in (`X(raw)`) and read `.value` on the way out. These tests pin that marshalling, the
 * inspector metadata (PROPERTY_HINT_ENUM / _FLAGS with Godot's names-with-values hint string), the
 * constant-folded defaults and the `@OverrideVirtual` typed-signature rule. The generated sources
 * are compiled by the example project (desktop: `GodotEnumExportSmoke.kt` and the enum virtual
 * probes, run by `runtime_smoke.sh`) and by the iOS / Web builds of the same shapes.
 */
class GodotEnumScriptMembersTest {

  private val processMode =
    GodotEnumTable.forKotlin("net.multigesture.kanama.api.Node.ProcessMode")!!
  private val threadMessages =
    GodotEnumTable.forKotlin("net.multigesture.kanama.api.Node.ProcessThreadMessages")!!
  private val modeFq = processMode.kotlinFqName
  private val flagsFq = threadMessages.kotlinFqName

  // ---------- the table, hint strings, defaults ----------

  @Test
  fun hintStringsMatchGodotsInspectorFormat() {
    // Node's own `process_mode` property hint is "Inherit,Pausable,When Paused,Always,Disabled";
    // the values are made explicit because Godot's are not always 0..n.
    assertEquals("Inherit:0,Pausable:1,When Paused:2,Always:3,Disabled:4", processMode.hintString)
    assertEquals(GodotEnumTable.PROPERTY_HINT_ENUM, processMode.propertyHint)
    // A bitfield: PROPERTY_HINT_FLAGS with the bit values.
    assertEquals("Messages:1,Messages Physics:2,Messages All:3", threadMessages.hintString)
    assertEquals(GodotEnumTable.PROPERTY_HINT_FLAGS, threadMessages.propertyHint)
    // A global enum with non-sequential values and digit-bearing names.
    val feature = GodotEnumTable.forKotlin("net.multigesture.kanama.api.TextServer.Feature")!!
    assertTrue(
      feature.hintString.startsWith("Simple Layout:1,Bidi Layout:2,Vertical Layout:4,Shaping:8,"),
      feature.hintString,
    )
    // BaseMaterial3D.Flags is a Godot ENUM of flag indices (is_bitfield false), not a bitfield.
    val materialFlags =
      GodotEnumTable.forKotlin("net.multigesture.kanama.api.BaseMaterial3D.Flags")!!
    assertEquals(GodotEnumTable.PROPERTY_HINT_ENUM, materialFlags.propertyHint)
  }

  @Test
  fun capitalizeIsGodotsStringCapitalize() {
    assertEquals("When Paused", godotCapitalize("WHEN_PAUSED"))
    assertEquals("Key 0", godotCapitalize("KEY_0"))
    assertEquals("Rgba 8", godotCapitalize("RGBA8"))
    assertEquals("Etc 2 R 11", godotCapitalize("ETC2_R11"))
    assertEquals("Ok", godotCapitalize("OK"))
  }

  @Test
  fun tableCoversGlobalClassAndBuiltinEnums() {
    assertNotNull(GodotEnumTable.forKotlin("net.multigesture.kanama.api.GodotError"))
    assertNotNull(GodotEnumTable.forKotlin("net.multigesture.kanama.api.Key"))
    assertNotNull(GodotEnumTable.forKotlin("net.multigesture.kanama.types.Vector3.Axis"))
    assertEquals(
      "net.multigesture.kanama.api.Image.Format",
      GodotEnumTable.forGodotType("enum::Image.Format")?.kotlinFqName,
    )
    assertEquals(
      flagsFq,
      GodotEnumTable.forGodotType("bitfield::Node.ProcessThreadMessages")?.kotlinFqName,
    )
    assertNull(GodotEnumTable.forGodotType("int"))
    assertNull(GodotEnumTable.forKotlin("kotlin.Long"))
  }

  @Test
  fun defaultLiteralsConstantFold() {
    val always = "$modeFq(3L)"
    assertEquals(always, GodotEnumTable.defaultLiteral("Node.ProcessMode.ALWAYS", processMode))
    assertEquals(always, GodotEnumTable.defaultLiteral("ProcessMode.ALWAYS", processMode))
    assertEquals(always, GodotEnumTable.defaultLiteral("$modeFq.ALWAYS", processMode))
    assertEquals(always, GodotEnumTable.defaultLiteral("Node.ProcessMode(3L)", processMode))
    assertEquals(always, GodotEnumTable.defaultLiteral("ProcessMode(3)", processMode))
    assertEquals(
      "$flagsFq(3L)",
      GodotEnumTable.defaultLiteral(
        "Node.ProcessThreadMessages.MESSAGES or Node.ProcessThreadMessages.MESSAGES_PHYSICS",
        threadMessages,
      ),
    )
    // Not foldable: an unqualified value, an unknown value, another enum, an expression.
    assertNull(GodotEnumTable.defaultLiteral("ALWAYS", processMode))
    assertNull(GodotEnumTable.defaultLiteral("ProcessMode.NOPE", processMode))
    assertNull(GodotEnumTable.defaultLiteral("Shader.Mode.SKY", processMode))
    assertNull(GodotEnumTable.defaultLiteral("pick()", processMode))
    // `or` only folds for a bitfield.
    assertNull(
      GodotEnumTable.defaultLiteral("ProcessMode.ALWAYS or ProcessMode.PAUSABLE", processMode)
    )
  }

  // ---------- @OverrideVirtual typed signatures ----------

  @Test
  fun enumVirtualSlotsRequireTheValueClass() {
    val where = "Probe._has_feature: @OverrideVirtual(\"_has_feature\")"
    val longOverride =
      KanamaProcessor.virtualEnumSlotError(
        where,
        "parameter 'feature'",
        "enum::TextServer.Feature",
        null,
      )
    assertNotNull(longOverride)
    assertTrue(longOverride.contains("declare it as `TextServer.Feature`"), longOverride)
    assertTrue(longOverride.contains("not `Long`"), longOverride)

    val typed = GodotEnumTable.forGodotType("enum::TextServer.Feature")!!.ref
    assertNull(
      KanamaProcessor.virtualEnumSlotError(
        where,
        "parameter 'feature'",
        "enum::TextServer.Feature",
        typed,
      )
    )
    // The wrong enum, and an enum on a plain int slot, are refused too.
    assertNotNull(KanamaProcessor.virtualEnumSlotError(where, "return", "enum::Shader.Mode", typed))
    assertNotNull(KanamaProcessor.virtualEnumSlotError(where, "return", "int", typed))
    assertNull(KanamaProcessor.virtualEnumSlotError(where, "return", "int", null))
  }

  @Test
  fun everyEnumTypedVirtualSlotResolvesToAValueClass() {
    val resource =
      javaClass.getResourceAsStream("/net/multigesture/kanama/processor/virtual-signatures.tsv")!!
    var slots = 0
    resource.bufferedReader().useLines { lines ->
      lines
        .filter { it.startsWith("V\t") }
        .forEach { line ->
          val f = line.split('\t')
          val types =
            (if (f[3].isEmpty()) emptyList() else f[3].split(',')) + listOfNotNull(f.getOrNull(4))
          types
            .filter { GodotEnumTable.godotKeyOfType(it) != null }
            .forEach { type ->
              slots++
              assertNotNull(GodotEnumTable.forGodotType(type), "$line: no value class for $type")
            }
        }
    }
    assertTrue(slots > 167, "expected the enum-typed virtual slots, found $slots")
  }

  @Test
  fun requiredVirtualReturnsMustBeNonNull() {
    val sig = VirtualSignatureTable.resolve("PhysicsDirectBodyState3DExtension", "_get_space_state")
    assertNotNull(sig)
    assertTrue(sig.returnRequired)
    val where = "Probe._get_space_state: @OverrideVirtual(\"_get_space_state\")"
    val fq = "net.multigesture.kanama.api.GodotObject"
    assertNotNull(KanamaProcessor.virtualRequiredReturnError(where, sig, fq, returnNullable = true))
    assertNull(KanamaProcessor.virtualRequiredReturnError(where, sig, fq, returnNullable = false))
    val optional = VirtualSignatureTable.resolve("Control", "_get_drag_data")!!
    assertTrue(!optional.returnRequired)
    assertEquals(
      2,
      listOf("PhysicsDirectBodyState2DExtension", "PhysicsDirectBodyState3DExtension").count {
        VirtualSignatureTable.resolve(it, "_get_space_state")?.returnRequired == true
      },
    )
  }

  // ---------- the model every emitter consumes ----------

  private val modeArg = ArgModel("mode", TypeMapping.INT, godotEnum = processMode.ref)
  private val flagsArg = ArgModel("flags", TypeMapping.INT, godotEnum = threadMessages.ref)

  private fun model(withList: Boolean = true): ScriptModel =
    ScriptModel(
      simpleName = "EnumFixture",
      fqName = "net.multigesture.kanama.test.EnumFixture",
      attachTo = "Node",
      isTool = false,
      isGlobalClass = false,
      properties =
        listOfNotNull(
          ScriptPropertyModel(
            kotlinName = "mode",
            godotName = "mode",
            type = TypeMapping.INT,
            isMutable = true,
            hint = processMode.propertyHint,
            // What the KSP builder ORs in for a Godot enum (GDScript's class marker).
            usage = 6 or processMode.ref.classUsageFlag,
            hintString = processMode.hintString,
            defaultLiteral = "$modeFq(3L)",
            godotEnum = processMode.ref,
          ),
          ScriptPropertyModel(
            kotlinName = "messages",
            godotName = "messages",
            type = TypeMapping.INT,
            isMutable = true,
            hint = threadMessages.propertyHint,
            usage = 6 or threadMessages.ref.classUsageFlag,
            hintString = threadMessages.hintString,
            defaultLiteral = "$flagsFq(1L)",
            godotEnum = threadMessages.ref,
          ),
          if (withList)
            ScriptPropertyModel(
              kotlinName = "modes",
              godotName = "modes",
              type = TypeMapping.ARRAY,
              isMutable = true,
              hint = 23,
              hintString = "2/2:${processMode.hintString}",
              defaultLiteral = "emptyList()",
              arrayElementGodotEnum = processMode.ref,
            )
          else null,
        ),
      toolButtons = emptyList(),
      virtuals =
        listOf(
          VirtualModel(
            "_get_shader_mode",
            "_get_shader_mode",
            "_get_shader_mode",
            returnType = TypeMapping.INT,
            returnGodotEnum = GodotEnumTable.forGodotType("enum::Shader.Mode")!!.ref,
          )
        ),
      methods =
        listOf(
          MethodModel(
            kotlinName = "nextMode",
            godotName = "next_mode",
            returnType = TypeMapping.INT,
            args = listOf(modeArg),
            kind = MethodKind.REGULAR,
            returnGodotEnum = processMode.ref,
          ),
          MethodModel(
            kotlinName = "applyFlags",
            godotName = "apply_flags",
            returnType = null,
            args = listOf(flagsArg),
            kind = MethodKind.REGULAR,
            rpc = RpcModel(mode = 2, callLocal = true, transferMode = 2, channel = 0),
          ),
        ),
      signals = listOf(SignalModel("mode_changed", listOf(modeArg))),
    )

  @Test
  fun desktopRegistrarMarshalsTheValueClassAsInt() {
    val source = ScriptCodeEmitter(model(), "EnumFixtureScriptRegistrar").emit()
    fun has(fragment: String) = assertTrue(source.contains(fragment), "missing: $fragment")
    // Inspector metadata.
    has(
      "ClassDB.PropertySpec(\"mode\", VariantType.INT, 2, \"Inherit:0,Pausable:1,When Paused:2,Always:3,Disabled:4\", 65542, \"Node.ProcessMode\")"
    )
    has(
      "ClassDB.PropertySpec(\"messages\", VariantType.INT, 6, \"Messages:1,Messages Physics:2,Messages All:3\", 518, \"Node.ProcessThreadMessages\")"
    )
    has("ClassDB.PropertySpec(\"modes\", VariantType.ARRAY, 23, \"2/2:Inherit:0,")
    // The script-level property list (Script.get_script_property_list) reports the same marker.
    has("\"usage\" to 65542, \"class_name\" to \"Node.ProcessMode\")")
    // Default field typed as the value class; reported as its value.
    has("private var defaultMode: $modeFq = $modeFq(3L)")
    has("s.set(JAVA_LONG, 0, defaultMode.value)")
    // Set wraps the raw slot (no ordinal clamp), get reads `.value`.
    has("kt.mode = v.let { raw -> $modeFq(raw) }")
    has("s.set(JAVA_LONG, 0, kt.mode.value)")
    has("BuiltinTypes.readVariantLongList(value, a).map { i -> $modeFq(i) }")
    has("BuiltinTypes.initVariantFromAny(ret, kt.modes.map { it.value }, a)")
    // @RegisterFunction: INT arg wrapped, enum return unwrapped.
    has("val marg0 = $modeFq(marg0Raw)")
    has("s.set(JAVA_LONG, 0, r.value)")
    // Enum-returning virtual.
    has("val vret = kt._get_shader_mode(); ")
    has("s.set(JAVA_LONG, 0, vret.value)")
    // Signals: emitted as INT, delivered typed.
    has("Signals.Arg(VariantType.INT, mode.value)")
    has("callback: ($modeFq) -> Unit,")
    has("callback($modeFq((args.getOrNull(0) as? Long ?: 0L)))")
    // Typed helpers.
    has("fun nextMode(instance: EnumFixture, mode: $modeFq): $modeFq =")
    has("fun rpcApplyFlags(instance: EnumFixture, flags: $flagsFq)")
  }

  @Test
  fun registerClassPtrcallPathWrapsAndUnwraps() {
    val classModel =
      ClassModel(
        simpleName = "EnumClassFixture",
        fqName = "net.multigesture.kanama.test.EnumClassFixture",
        parentClassName = "Node",
        isTool = false,
        methods = model().methods.take(1),
        properties = emptyList(),
        virtuals = emptyList(),
        signals = model().signals,
      )
    val source = CodeEmitter(classModel, "EnumClassFixtureRegistrar").emit()
    fun has(fragment: String) = assertTrue(source.contains(fragment), "missing: $fragment")
    has("val arg0Value = $modeFq(argsArray.get(ADDRESS, 0L).reinterpret(8).get(JAVA_LONG, 0))")
    has("rRet.reinterpret(8).set(JAVA_LONG, 0, result.value)")
    has("val arg0Value = $modeFq(arg0Scratch.get(JAVA_LONG, 0))")
    has("retScratch.set(JAVA_LONG, 0, result.value)")
  }

  @Test
  fun iosBridgeMarshalsTheValueClassAsInt() {
    val errors = mutableListOf<String>()
    val warnings = mutableListOf<String>()
    val source =
      IosScriptCodeEmitter(
          listOf(IosScriptInput(model(), "res://EnumFixture.kt")),
          warn = { warnings += it },
          error = { errors += it },
        )
        .registrySource()
    fun has(fragment: String) = assertTrue(source.contains(fragment), "missing: $fragment")
    assertEquals(emptyList(), errors)
    assertEquals(emptyList(), warnings.filter { "EnumFixture" in it })
    has(
      "KanamaIosScriptProperty(\"mode\", 2, 2, \"Inherit:0,Pausable:1,When Paused:2,Always:3,Disabled:4\", 65542, \"Node.ProcessMode\")"
    )
    has(
      "KanamaIosScriptProperty(\"messages\", 2, 6, \"Messages:1,Messages Physics:2,Messages All:3\", 518, \"Node.ProcessThreadMessages\")"
    )
    has("0 -> { script.mode = $modeFq(value); true }")
    has("0 -> script.mode.value")
    has("2 -> script.modes.map { it.value }")
    has("2 -> { script.modes = values.map { i -> $modeFq(i) }")
    has("\"next_mode\" -> (script.nextMode($modeFq(args[0] as Long))).value")
    has("\"apply_flags\" -> { script.applyFlags($flagsFq(args[0] as Long)); true }")
    has("\"_get_shader_mode\" -> (script._get_shader_mode()).value")
  }

  @Test
  fun webRegistryAndProxyMarshalTheValueClassAsInt() {
    val webOptions = mapOf("kanamaRuntimeTarget" to "web")
    val webModel = model(withList = false)
    // Every member of the scalar fixture dispatches typed on Web; the only virtual is not one of
    // the Web-dispatched lifecycle virtuals, so leave it out of the member check.
    val members = webModel.copy(virtuals = emptyList())
    assertEquals(
      emptyList(),
      WebScriptCodeEmitter.unsupportedWebPropertyErrors(members, webOptions),
    )
    assertEquals(emptyList(), WebScriptCodeEmitter.undispatchedMemberErrors(members, webOptions))

    val emitter = WebScriptCodeEmitter(listOf(WebScriptInput(members, "res://EnumFixture.kt")))
    val registry = emitter.registrySource()
    fun has(source: String, fragment: String) =
      assertTrue(source.contains(fragment), "missing: $fragment")
    has(registry, "1 -> (script as EnumFixture).mode = $modeFq(value)")
    has(registry, "1 -> (script as EnumFixture).mode.value.toString()")
    has(registry, "1 -> (script as EnumFixture).nextMode($modeFq(value)).value")
    has(registry, "2 -> (script as EnumFixture).applyFlags($flagsFq(value))")

    val proxy = emitter.proxySources().single { it.sourceResourcePath.isNotEmpty() }.source
    has(
      proxy,
      "@export_custom(2, \"Inherit:0,Pausable:1,When Paused:2,Always:3,Disabled:4\") var mode: int = 3",
    )
    has(
      proxy,
      "@export_custom(6, \"Messages:1,Messages Physics:2,Messages All:3\") var messages: int = 1",
    )

    val constants = emitter.constantsSource()
    has(
      constants,
      "fun nextMode(instance: net.multigesture.kanama.test.EnumFixture, mode: $modeFq): $modeFq =",
    )

    // `List<Node.ProcessMode>` has no Web array arm, like `List<enum class>`: a build error.
    val listErrors = WebScriptCodeEmitter.unsupportedWebPropertyErrors(model(), webOptions)
    assertEquals(1, listErrors.size, listErrors.toString())
    assertTrue(listErrors.single().contains("List<$modeFq>"), listErrors.single())
  }

  @Test
  fun scriptModelJsonCarriesTheEnumSlots() {
    val json = scriptModelToJson(model())
    assertTrue(json.contains("\"schemaVersion\":$SCRIPT_MODEL_SCHEMA_VERSION"))
    assertTrue(
      json.contains(
        "\"godotEnum\":{\"kotlinFqName\":\"$modeFq\",\"isBitfield\":false,\"godotKey\":\"Node.ProcessMode\"}"
      ),
      json,
    )
    assertTrue(
      json.contains(
        "\"arrayElementGodotEnum\":{\"kotlinFqName\":\"$modeFq\",\"isBitfield\":false,\"godotKey\":\"Node.ProcessMode\"}"
      )
    )
    assertTrue(
      json.contains(
        "\"returnGodotEnum\":{\"kotlinFqName\":\"$modeFq\",\"isBitfield\":false,\"godotKey\":\"Node.ProcessMode\"}"
      )
    )
    assertTrue(
      json.contains(
        "\"godotEnum\":{\"kotlinFqName\":\"$flagsFq\",\"isBitfield\":true,\"godotKey\":\"Node.ProcessThreadMessages\"}"
      )
    )
  }
}
