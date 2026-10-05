package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.test.fail

/**
 * Task 133 value types — Rect2, Rect2i, Vector4, Vector4i, Plane, AABB, Transform2D, Transform3D,
 * Projection (and on iOS / Web Vector3i, Quaternion, Basis) are script types: `@Export` with
 * constant / literal defaults, registered-function arguments and returns, `@Signal` arguments.
 * Desktop marshals Godot's memory layout at the `real_t` width (task 134 A), iOS their raw bytes,
 * Web their packed components.
 */
class ValueTypeScriptTypesTest {
  private val t = "net.multigesture.kanama.types"

  private val newTypes =
    listOf(
      TypeMapping.RECT2,
      TypeMapping.RECT2I,
      TypeMapping.VECTOR4,
      TypeMapping.VECTOR4I,
      TypeMapping.PLANE,
      TypeMapping.AABB,
      TypeMapping.TRANSFORM2D,
      TypeMapping.TRANSFORM3D,
      TypeMapping.PROJECTION,
    )

  private fun model(types: List<TypeMapping>, defaults: Map<TypeMapping, String> = emptyMap()) =
    ScriptModel(
      simpleName = "Shapes",
      fqName = "net.multigesture.kanama.test.Shapes",
      attachTo = "Node",
      isTool = false,
      isGlobalClass = false,
      properties =
        types.map {
          ScriptPropertyModel(
            kotlinName = it.name.lowercase(),
            godotName = it.name.lowercase(),
            type = it,
            isMutable = true,
            defaultLiteral = defaults[it] ?: it.kotlinLiteralZero,
          )
        },
      toolButtons = emptyList(),
      virtuals = emptyList(),
      methods =
        types.flatMap {
          val name = it.name.lowercase()
          listOf(
            MethodModel(
              "take_$name",
              "take_$name",
              null,
              listOf(ArgModel("v", it)),
              MethodKind.REGULAR,
            ),
            MethodModel("get_$name", "get_$name", it, emptyList(), MethodKind.REGULAR),
            MethodModel(
              "echo_$name",
              "echo_$name",
              it,
              listOf(ArgModel("v", it)),
              MethodKind.REGULAR,
            ),
          )
        },
      signals =
        types.map { SignalModel("${it.name.lowercase()}_changed", listOf(ArgModel("v", it))) },
    )

  @Test
  fun everyValueTypeResolves() {
    val fq =
      mapOf(
        "Rect2" to TypeMapping.RECT2,
        "Rect2i" to TypeMapping.RECT2I,
        "Vector4" to TypeMapping.VECTOR4,
        "Vector4i" to TypeMapping.VECTOR4I,
        "Plane" to TypeMapping.PLANE,
        "AABB" to TypeMapping.AABB,
        "Transform2D" to TypeMapping.TRANSFORM2D,
        "Transform3D" to TypeMapping.TRANSFORM3D,
        "Projection" to TypeMapping.PROJECTION,
      )
    fq.forEach { (name, type) ->
      assertEquals(type, KanamaProcessor.fqToTypeMapping("$t.$name"), name)
      assertEquals(type.name, type.variantTypeEnum)
    }
    assertTrue(
      FunctionRegistration.SUPPORTED_FUNCTION_TYPES.contains("Transform3D, Projection"),
      "the unsupported-type message lists the new types",
    )
  }

  @Test
  fun desktopLayoutIsGodotsMemoryOrderAtTheRealWidth() {
    // Real components go through GodotRealSegment at the engine's width; ints are int32.
    val rect = TypeMapping.RECT2.readPtrcallArg("p0")
    assertTrue(rect.contains("GodotReal.SIZE_BYTES * 4L"), rect)
    assertTrue(rect.contains("$t.Rect2($t.Vector2(") && rect.contains("readIndex(p, 3)"), rect)
    val rect2i = TypeMapping.RECT2I.readFromScratch("s")
    assertEquals(
      "$t.Rect2i($t.Vector2i(s.get(JAVA_INT, 0L), s.get(JAVA_INT, 4L)), " +
        "$t.Vector2i(s.get(JAVA_INT, 8L), s.get(JAVA_INT, 12L)))",
      rect2i,
    )
    // Godot stores a Basis as rows: index 1 is the y column's x.
    val transform = TypeMapping.TRANSFORM3D.writeToScratch("s", "v")
    assertTrue(transform.contains("writeIndex(s, 1, v.basis.y.x)"), transform)
    assertTrue(transform.contains("writeIndex(s, 3, v.basis.x.y)"), transform)
    assertTrue(transform.contains("writeIndex(s, 11, v.origin.z)"), transform)
    val read = TypeMapping.TRANSFORM3D.readFromScratch("s")
    assertTrue(
      read.contains(
        "$t.Basis($t.Vector3($t.GodotRealSegment.readIndex(s, 0), " +
          "$t.GodotRealSegment.readIndex(s, 3), $t.GodotRealSegment.readIndex(s, 6))"
      ),
      read,
    )
    val projection = TypeMapping.PROJECTION.writePtrcallReturn("v")
    assertTrue(projection.contains("GodotReal.SIZE_BYTES * 16L"), projection)
    assertTrue(projection.contains("writeIndex(p, 15, v.w.w)"), projection)
    assertTrue(TypeMapping.PLANE.writeToScratch("s", "v").contains("writeIndex(s, 3, v.d)"))
    assertTrue(TypeMapping.TRANSFORM2D.writeToScratch("s", "v").contains("writeIndex(s, 2, v.y.x)"))
    assertTrue(TypeMapping.VECTOR4I.writeToScratch("s", "v").contains("s.set(JAVA_INT, 12L, v.w)"))
  }

  @Test
  fun defaultsNormalizeConstantsAndLiterals() {
    fun n(text: String, type: TypeMapping) = normalizeScriptPropertyDefaultLiteral(text, type)
    assertEquals("$t.Vector4.ZERO", n("Vector4.ZERO", TypeMapping.VECTOR4))
    assertEquals("$t.Transform3D.IDENTITY", n("Transform3D.IDENTITY", TypeMapping.TRANSFORM3D))
    assertEquals("$t.Plane.PLANE_XY", n("$t.Plane.PLANE_XY", TypeMapping.PLANE))
    assertEquals("$t.Vector4i.MAX", n("Vector4i.MAX", TypeMapping.VECTOR4I))
    assertNull(n("Vector4.NOPE", TypeMapping.VECTOR4), "only real constants")
    assertEquals(
      "$t.Vector4(1.0, 2.5, 0.0, -1.0)",
      n("Vector4(1, 2.5f, 0, -1.0)", TypeMapping.VECTOR4),
    )
    assertEquals(
      "$t.Rect2($t.Vector2(1.0, 2.0), $t.Vector2.ZERO)",
      n("Rect2(Vector2(1, 2), Vector2.ZERO)", TypeMapping.RECT2),
    )
    assertEquals(
      "$t.Rect2i($t.Vector2i(1, 2), $t.Vector2i(3, 4))",
      n("Rect2i(Vector2i(1, 2), Vector2i(3, 4))", TypeMapping.RECT2I),
    )
    assertEquals(
      "$t.Transform3D($t.Basis.IDENTITY, $t.Vector3(1.0, 2.0, 3.0))",
      n("Transform3D(Basis.IDENTITY, Vector3(1.0, 2.0, 3.0))", TypeMapping.TRANSFORM3D),
    )
    assertEquals("$t.Plane(1.0, 0.0, 0.0, 2.0)", n("Plane(1.0, 0.0, 0.0, 2.0)", TypeMapping.PLANE))
    assertEquals("$t.Plane($t.Vector3.UP, 2.0)", n("Plane(Vector3.UP, 2)", TypeMapping.PLANE))
    assertEquals("$t.Quaternion.IDENTITY", n("Quaternion.IDENTITY", TypeMapping.QUATERNION))
    assertEquals("$t.Vector3i(1, 2, 3)", n("Vector3i(1, 2, 3)", TypeMapping.VECTOR3I))
    assertNull(n("Transform3D.IDENTITY.translated(Vector3.UP)", TypeMapping.TRANSFORM3D))
    // The Web proxy spelling: no package, and ZERO of the types GDScript lacks it on is `Type()`.
    assertEquals("Rect2()", gdValueTypeDefault("$t.Rect2.ZERO", TypeMapping.RECT2))
    assertEquals(
      "Transform3D(Basis.IDENTITY, Vector3(1.0, 2.0, 3.0))",
      gdValueTypeDefault(
        "$t.Transform3D($t.Basis.IDENTITY, $t.Vector3(1.0, 2.0, 3.0))",
        TypeMapping.TRANSFORM3D,
      ),
    )
    assertEquals("Plane.PLANE_XY", gdValueTypeDefault("$t.Plane.PLANE_XY", TypeMapping.PLANE))
  }

  @Test
  fun desktopRegistrarMarshalsEveryType() {
    val source = ScriptCodeEmitter(model(newTypes), "ShapesScriptRegistrar").emit()
    newTypes.forEach { type ->
      val name = type.name.lowercase()
      assertTrue(
        source.contains("ClassDB.PropertySpec(\"$name\", VariantType.${type.name}, 0, \"\", 6)"),
        name,
      )
      assertTrue(source.contains("Signals.Arg(VariantType.${type.name}, v)"), name)
      assertTrue(
        source.contains("BuiltinTypes.readVariantScalar(") &&
          source.contains("as? ${type.kotlinType}"),
        name,
      )
    }
    assertTrue(
      source.contains("private var defaultTransform3d: $t.Transform3D = $t.Transform3D.IDENTITY")
    )
  }

  @Test
  fun iosRegistryMarshalsEveryType() {
    val types = newTypes + listOf(TypeMapping.VECTOR3I, TypeMapping.QUATERNION, TypeMapping.BASIS)
    val errors = mutableListOf<String>()
    val warnings = mutableListOf<String>()
    val emitter =
      IosScriptCodeEmitter(
        listOf(IosScriptInput(model(types), "res://Shapes.kt")),
        error = { errors += it },
        warn = { warnings += it },
      )
    val source = emitter.registrySource()
    assertEquals(emptyList(), errors)
    assertEquals(emptyList(), warnings.filter { "Shapes" in it })
    val variantTypes =
      mapOf(
        TypeMapping.RECT2 to 7,
        TypeMapping.RECT2I to 8,
        TypeMapping.VECTOR3I to 10,
        TypeMapping.TRANSFORM2D to 11,
        TypeMapping.VECTOR4 to 12,
        TypeMapping.VECTOR4I to 13,
        TypeMapping.PLANE to 14,
        TypeMapping.QUATERNION to 15,
        TypeMapping.AABB to 16,
        TypeMapping.BASIS to 17,
        TypeMapping.TRANSFORM3D to 18,
        TypeMapping.PROJECTION to 19,
      )
    types.forEach { type ->
      val name = type.name.lowercase()
      assertTrue(
        source.contains(
          "KanamaIosScriptProperty(\"$name\", ${variantTypes.getValue(type)}, 0, \"\", 6)"
        ),
        "$name\n$source",
      )
      // Set (value path), get, call argument and return are all emitted.
      assertTrue(source.contains("script.$name = value as ${type.kotlinType}"), name)
      assertTrue(source.contains("as ${type.kotlinType}"), name)
    }
    assertTrue(IOS_RAW_VALUE_TYPES.containsAll(types - newTypes.toSet() + newTypes))
  }

  @Test
  fun webProxyAndRegistryMarshalTheWebValueTypes() {
    // Task 134 D1: the Web build compiles the shared value types, so every one is a script type.
    val webTypes =
      listOf(
        TypeMapping.VECTOR3I,
        TypeMapping.RECT2,
        TypeMapping.RECT2I,
        TypeMapping.PLANE,
        TypeMapping.QUATERNION,
        TypeMapping.BASIS,
        TypeMapping.TRANSFORM3D,
        TypeMapping.VECTOR4,
        TypeMapping.VECTOR4I,
        TypeMapping.AABB,
        TypeMapping.TRANSFORM2D,
        TypeMapping.PROJECTION,
      )
    assertEquals(webTypes.toSet(), WebValueTypes.COMPONENTS.keys)
    val defaults =
      mapOf(
        TypeMapping.RECT2 to "$t.Rect2($t.Vector2(1.0, 2.0), $t.Vector2(3.0, 4.0))",
        TypeMapping.TRANSFORM3D to "$t.Transform3D.IDENTITY",
        TypeMapping.PLANE to "$t.Plane.PLANE_XY",
      )
    val model = model(webTypes, defaults)
    val options = mapOf("kanamaRuntimeTarget" to "web")
    assertEquals(emptyList(), WebScriptCodeEmitter.unsupportedWebPropertyErrors(model, options))
    val emitter = WebScriptCodeEmitter(listOf(WebScriptInput(model, "res://Shapes.kt")))
    val proxy = emitter.proxySources().single { it.sourceResourcePath.isNotEmpty() }.source
    assertTrue(
      proxy.contains("var rect2: Rect2 = Rect2(Vector2(1.0, 2.0), Vector2(3.0, 4.0))"),
      proxy,
    )
    assertTrue(proxy.contains("var transform3d: Transform3D = Transform3D.IDENTITY"), proxy)
    assertTrue(proxy.contains("var plane: Plane = Plane.PLANE_XY"), proxy)
    assertTrue(proxy.contains("var rect2i: Rect2i = Rect2i()"), proxy)
    assertTrue(
      proxy.contains(
        "_kanama_bridge.setPackedValueProperty(_kanama_handle, 2, _kanama_web_pack_value(rect2))"
      ),
      proxy,
    )
    assertTrue(proxy.contains("rect2 = _kanama_web_unpack_value(TYPE_RECT2, _kanama_packed_2)"))
    // Exact decimals in, ints as ints, and the unpack constructors in Godot's memory layout (a
    // Basis as its rows, so column x is components 0, 3, 6).
    assertTrue(proxy.contains("_kanama_web_float_text(value.basis.z.z)"), proxy)
    assertTrue(proxy.contains("str(value.position.x)"), "Rect2i packs ints")
    assertTrue(
      proxy.contains(
        "return Transform3D(Basis(Vector3(p[0], p[3], p[6]), Vector3(p[1], p[4], p[7]), " +
          "Vector3(p[2], p[5], p[8])), Vector3(p[9], p[10], p[11]))"
      ),
      proxy,
    )
    assertTrue(proxy.contains("return Rect2i(int(p[0]), int(p[1]), int(p[2]), int(p[3]))"))
    assertTrue(
      proxy.contains(
        "return Projection(Vector4(p[0], p[1], p[2], p[3]), Vector4(p[4], p[5], p[6], p[7]), " +
          "Vector4(p[8], p[9], p[10], p[11]), Vector4(p[12], p[13], p[14], p[15]))"
      ),
      proxy,
    )
    assertTrue(proxy.contains("return AABB(Vector3(p[0], p[1], p[2]), Vector3(p[3], p[4], p[5]))"))
    assertTrue(
      proxy.contains("TYPE_VECTOR2, TYPE_VECTOR2I, TYPE_RECT2, TYPE_RECT2I, TYPE_VECTOR3"),
      "signal payloads pack",
    )
    val registry = emitter.registrySource()
    assertTrue(registry.contains("fun setPackedValueProperty("), registry)
    assertTrue(
      registry.contains(
        "(script as Shapes).rect2 = (net.multigesture.kanama.web.WebPackedValues.decode(packed, $t.Rect2::class) as $t.Rect2)"
      ),
      registry,
    )
    assertTrue(
      registry.contains(
        "net.multigesture.kanama.web.WebPackedValues.encode((script as Shapes).basis)"
      )
    )
    model.methods.forEach { method ->
      assertTrue(WebScriptCodeEmitter.methodDispatch(method).isTyped, method.kotlinName)
      // Task 134 D1: an argument AND a return rides the packed list in and the packed return out.
      if (method.kotlinName.startsWith("echo_")) {
        assertEquals(WebMethodArm.PACKED_ARGS_RETURN, WebScriptCodeEmitter.methodArm(method))
      }
    }
    assertTrue(registry.contains("fun callPackedArgs("), registry)
    assertTrue(
      registry.contains(
        "net.multigesture.kanama.web.WebPackedValues.encode((script as Shapes).echo_projection(" +
          "(net.multigesture.kanama.web.WebPackedValues.decode(packedArgs[0], $t.Projection::class) as $t.Projection)))"
      ),
      registry,
    )
    assertTrue(proxy.contains("_kanama_bridge.callPackedArgs(_kanama_handle, "), proxy)
    // Small values ride the six numeric slots; Basis and Transform3D the exact packed list.
    val takeRect = model.methods.single { it.kotlinName == "take_rect2" }
    assertEquals(WebMethodArm.NUMERIC_VOID, WebScriptCodeEmitter.methodArm(takeRect))
    val takeTransform = model.methods.single { it.kotlinName == "take_transform3d" }
    assertEquals(WebMethodArm.PACKED_ARGS, WebScriptCodeEmitter.methodArm(takeTransform))
    model.signals.forEach { signal ->
      assertEquals(WebDispatchStatus.TYPED, WebScriptCodeEmitter.signalDispatch(signal).status)
    }
  }

  @Test
  fun webAcceptsTheValueTypesItOnceRefused() {
    // Task 134 D1: the five types Web had no value type for are script types there too now.
    val options = mapOf("kanamaRuntimeTarget" to "web")
    listOf(
        TypeMapping.VECTOR4,
        TypeMapping.VECTOR4I,
        TypeMapping.AABB,
        TypeMapping.TRANSFORM2D,
        TypeMapping.PROJECTION,
      )
      .forEach { type ->
        val errors = WebScriptCodeEmitter.unsupportedWebPropertyErrors(model(listOf(type)), options)
        assertEquals(emptyList(), errors, "$type")
      }
  }

  /** Every TypeMapping the registrars hand to `variant_to_type`: the Variant payload sizes. */
  @Test
  fun everyScratchHoldsItsVariantPayload() {
    // extension_api.json builtin_class_sizes, float_64 / double_64 (Godot 4.7.2).
    val api = java.io.File("../extension_api.json").readText()
    fun sizes(config: String): Map<String, Int> {
      val block = api.substringAfter("\"build_configuration\": \"$config\"").substringBefore("]")
      return Regex("\"name\": \"(\\w+)\",\\s*\"size\": (\\d+)").findAll(block).associate {
        it.groupValues[1] to it.groupValues[2].toInt()
      }
    }
    val apiName =
      mapOf(
        "NIL" to "Variant",
        "BOOL" to "bool",
        "INT" to "int",
        "FLOAT" to "float",
        "STRING" to "String",
        "OBJECT" to "Object",
        "ARRAY" to "Array",
        "DICTIONARY" to "Dictionary",
        "RID" to "RID",
        "AABB" to "AABB",
      )
    fun godotName(type: TypeMapping): String =
      apiName[type.variantTypeEnum]
        ?: type.variantTypeEnum
          .split('_')
          .joinToString("") { part -> part.lowercase().replaceFirstChar { it.uppercase() } }
          .replace("Vector2i", "Vector2i")
          .replace(Regex("(\\d)d$"), "$1D")
    val layoutBytes =
      mapOf(
        "JAVA_LONG" to 8,
        "JAVA_DOUBLE" to 8,
        "JAVA_BYTE" to 1,
        "ADDRESS" to 8,
        "JAVA_INT" to 4,
        "JAVA_FLOAT" to 4,
      )
    for ((config, real) in listOf("float_64" to 4, "double_64" to 8)) {
      val payload = sizes(config)
      for (type in TypeMapping.entries) {
        val expr = type.scratchAllocationExpr
        val bytes =
          layoutBytes[expr]
            ?: Regex("GodotReal\\.SIZE_BYTES \\* (\\d+)L").find(expr)?.let {
              it.groupValues[1].toInt() * real
            }
            ?: expr.substringBefore("L,").trim().toInt()
        val name = godotName(type)
        val need = payload[name] ?: error("no Godot size for ${type.name} ($name)")
        assertTrue(bytes >= need, "${type.name}: scratch $bytes < $name payload $need ($config)")
      }
    }
  }

  @Test
  fun registerClassMarshalsEveryValueTypeBothWays() {
    val types =
      listOf(
        TypeMapping.VECTOR2,
        TypeMapping.VECTOR2I,
        TypeMapping.VECTOR3,
        TypeMapping.VECTOR3I,
        TypeMapping.QUATERNION,
        TypeMapping.BASIS,
        TypeMapping.COLOR,
      ) + newTypes
    val classModel =
      ClassModel(
        simpleName = "Shapes",
        fqName = "net.multigesture.kanama.test.Shapes",
        parentClassName = "Node",
        isTool = false,
        methods =
          types.map {
            MethodModel(
              "echo_${it.name.lowercase()}",
              "echo_${it.name.lowercase()}",
              it,
              listOf(ArgModel("v", it)),
              MethodKind.REGULAR,
            )
          },
        properties = emptyList(),
        virtuals = emptyList(),
        signals = emptyList(),
      )
    val source = CodeEmitter(classModel, "ShapesRegistrar").emit()
    // A brace block right after `arena.allocate(...)` / `val result = ...` parses as a trailing
    // lambda (the review's 104 compile errors): every write is a `run { }` statement or a call.
    val lines = source.lines()
    lines.forEachIndexed { i, line ->
      if (line.trimStart().startsWith("{")) {
        fail("line ${i + 1} starts a brace block after `${lines[i - 1].trim()}`")
      }
    }
    types.forEach { type ->
      val name = type.name.lowercase()
      assertTrue(source.contains("fun call_echo_$name("), name)
      assertTrue(source.contains("fun ptrcall_echo_$name("), name)
      assertTrue(source.contains("arena.allocate(${type.scratchAllocationExpr})"), name)
    }
    assertTrue(source.contains("arena.allocate(8L, 4L)"), "Vector2i scratch holds both int32")
    assertTrue(source.contains("arena.allocate(12L, 4L)"), "Vector3i scratch holds all three int32")
  }

  @Test
  fun everyDefaultTheNormalizerAcceptsForAWebTypeIsInTheWebFixture() {
    val fixture =
      java.io
        .File("../web-runtime/src/web3dSmoke/web/kotlin-src/ValueTypeDefaultsFixture.kt")
        .readText()
    val exports =
      Regex("""@Export var \w+: (\w+) = (.+)""")
        .findAll(fixture)
        .map { it.groupValues[1] to it.groupValues[2].trim() }
        .toList()
    val byName = TypeMapping.entries.associateBy { it.kotlinType.substringAfterLast('.') }
    val webTypes =
      WebValueTypes.COMPONENTS.keys +
        setOf(TypeMapping.VECTOR2, TypeMapping.VECTOR2I, TypeMapping.VECTOR3)
    for (type in webTypes) {
      val simple = type.kotlinType.substringAfterLast('.')
      for (constant in VALUE_TYPE_DEFAULT_CONSTANTS.getValue(type)) {
        assertTrue(
          exports.contains(simple to "$simple.$constant"),
          "fixture lacks $simple.$constant",
        )
      }
    }
    exports.forEach { (simple, initializer) ->
      val type = byName.getValue(simple)
      assertTrue(type in webTypes, simple)
      val normalized = normalizeScriptPropertyDefaultLiteral(initializer, type)
      assertTrue(normalized != null, "$simple = $initializer does not normalize")
    }
  }
}
