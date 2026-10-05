package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

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
    val webTypes =
      listOf(
        TypeMapping.VECTOR3I,
        TypeMapping.RECT2,
        TypeMapping.RECT2I,
        TypeMapping.PLANE,
        TypeMapping.QUATERNION,
        TypeMapping.BASIS,
        TypeMapping.TRANSFORM3D,
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
    // Exact decimals in, ints as ints, and the unpack constructors in the channel order.
    assertTrue(proxy.contains("String.num_scientific(value.basis.z.z)"), proxy)
    assertTrue(proxy.contains("str(value.position.x)"), "Rect2i packs ints")
    assertTrue(
      proxy.contains(
        "return Transform3D(Basis(Vector3(p[0], p[1], p[2]), Vector3(p[3], p[4], p[5]), " +
          "Vector3(p[6], p[7], p[8])), Vector3(p[9], p[10], p[11]))"
      ),
      proxy,
    )
    assertTrue(proxy.contains("return Rect2i(int(p[0]), int(p[1]), int(p[2]), int(p[3]))"))
    assertTrue(proxy.contains("TYPE_RECT2, TYPE_RECT2I, TYPE_PLANE"), "signal payloads pack")
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
      val typed = WebScriptCodeEmitter.methodDispatch(method).isTyped
      // An argument AND a return has no Web arm whatever the types (pre-existing).
      assertEquals(!method.kotlinName.startsWith("echo_"), typed, method.kotlinName)
    }
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
  fun webRefusesTheTypesItHasNoValueTypeFor() {
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
        assertEquals(1, errors.size, "$type")
        assertTrue(errors.single().contains("no full Kanama Web property arm set"))
      }
  }
}
