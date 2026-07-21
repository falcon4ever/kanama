package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Task 46 — cross-backend @ScriptProperty get/set capability parity.
 *
 * The JVM backend (`ScriptBridge`) reads and writes every supported @ScriptProperty type through
 * one generic path, so it is uniformly readable + settable. The iOS backend hand-rolls per-type
 * codegen, so it can silently diverge — which is exactly how value/data types shipped
 * **write-only** (settable from scene data but read back as nil), silently breaking
 * `MultiplayerSynchronizer` replication (the 2026-07-16 "iPhone can't walk/shoot" bug).
 *
 * This locks the contract as a fast, device-free unit test: for every *data* type the iOS emitter
 * supports, it must be both settable AND readable (matching the JVM's uniform capability). Object /
 * custom-script refs are the one documented exemption (a cross-peer handle is meaningless to
 * replicate). The codegen guard already fails the build on a violation; this test is the same rule
 * exercised over an explicit type fixture, so a regression is caught in the JVM test suite too.
 */
class IosScriptPropertyParityTest {

  private fun scalarProp(name: String, type: TypeMapping) =
    ScriptPropertyModel(kotlinName = name, godotName = name, type = type, isMutable = true)

  private fun stringListProp(name: String, mutable: Boolean = true) =
    ScriptPropertyModel(
      kotlinName = name,
      godotName = name,
      type = TypeMapping.ARRAY,
      isMutable = mutable,
      arrayElementString = true,
    )

  private fun objectProp(name: String) =
    ScriptPropertyModel(
      kotlinName = name,
      godotName = name,
      type = TypeMapping.OBJECT,
      isMutable = true,
      objectWrapperFqName = "net.multigesture.kanama.api.Node",
    )

  private class Result(val source: String, val errors: List<String>) {
    // The generated bridge's getProperty is a `when (propertyIndex)`; slice it from its declaration
    // to the next `override fun` so field reads outside it don't produce false positives.
    val getPropertyBlock: String = run {
      val start = source.indexOf("override fun getProperty(")
      if (start < 0) ""
      else {
        val next = source.indexOf("override fun ", start + 20)
        source.substring(start, if (next < 0) source.length else next)
      }
    }

    fun readable(kotlinName: String) = getPropertyBlock.contains("script.$kotlinName")

    // The generated set-side assignment for a list property (`script.<name> = <decode>…`).
    // Returns the tail after the `=` up to the trailing `; true }` so mutability-suffix
    // assertions don't depend on the exact decode expression.
    fun setterRhs(kotlinName: String): String {
      val marker = "script.$kotlinName = "
      val at = source.indexOf(marker)
      if (at < 0) return ""
      val end = source.indexOf("; true }", at)
      return source.substring(at + marker.length, if (end < 0) source.length else end)
    }
  }

  private fun emit(vararg props: ScriptPropertyModel): Result {
    val errors = mutableListOf<String>()
    val model =
      ScriptModel(
        simpleName = "ParityFixture",
        fqName = "net.multigesture.kanama.test.ParityFixture",
        attachTo = "Node",
        isTool = false,
        isGlobalClass = false,
        properties = props.toList(),
        toolButtons = emptyList(),
        virtuals = emptyList(),
        methods = emptyList(),
        signals = emptyList(),
      )
    val source =
      IosScriptCodeEmitter(
          listOf(IosScriptInput(model, "res://ParityFixture.kt")),
          error = { errors += it },
        )
        .registrySource()
    return Result(source, errors)
  }

  @Test
  fun everySettableDataTypeIsAlsoReadable() {
    val r =
      emit(
        scalarProp("motion", TypeMapping.VECTOR2),
        scalarProp("shootTarget", TypeMapping.VECTOR3),
        scalarProp("label", TypeMapping.STRING),
        scalarProp("view", TypeMapping.NODE_PATH),
        stringListProp("tags"),
        // scalars (never at risk, but part of the contract)
        scalarProp("count", TypeMapping.INT),
        scalarProp("speed", TypeMapping.FLOAT),
        scalarProp("armed", TypeMapping.BOOL),
      )

    // The parity guard (as the emitter's hard-error callback) must not fire: no write-only data.
    assertTrue(
      r.errors.isEmpty(),
      "iOS @ScriptProperty parity guard flagged write-only data type(s): ${r.errors}",
    )

    // Each data type is engine-readable (has a getProperty branch) — the exact property the iOS
    // multiplayer bug lost. Reverting a getProperty fix drops the read and fails this.
    for (name in listOf("motion", "shootTarget", "label", "view", "tags")) {
      assertTrue(
        r.readable(name),
        "data @ScriptProperty '$name' is write-only on iOS (no getProperty branch)",
      )
    }
  }

  @Test
  fun objectRefsAreExemptWriteOnlyNoError() {
    val r = emit(objectProp("crosshair"))
    // Object refs are settable (owner handle) but intentionally not engine-readable — replicating
    // a cross-peer handle is meaningless. This must NOT be flagged as a parity violation.
    assertTrue(r.errors.isEmpty(), "object-ref @ScriptProperty wrongly flagged: ${r.errors}")
    assertFalse(r.readable("crosshair"), "object refs are not expected to be engine-readable")
  }

  @Test
  fun listStringIsReadableViaGetProperty() {
    val r = emit(stringListProp("names"))
    assertEquals(emptyList(), r.errors)
    assertTrue(r.readable("names"), "List<String> @ScriptProperty must be engine-readable")
  }

  /**
   * A `MutableList<T>` @ScriptProperty must decode with a `.toMutableList()` suffix: the list
   * decode helpers all yield an immutable `List`, which is not assignable to a `MutableList` field
   * on Kotlin/Native (compile-verified: `Assignment type mismatch: List<String> vs
   * MutableList<String>`). Without the suffix the generated iOS registrar does not compile. The
   * desktop emitter has done this for `p.isMutable` all along; the iOS emitter dropped `isMutable`
   * entirely until this was threaded through. Reverting the suffix fails this test.
   */
  @Test
  fun mutableListPropertyDecodesWithToMutableListSuffix() {
    val r = emit(stringListProp("mutableTags", mutable = true))
    assertEquals(emptyList(), r.errors)
    assertTrue(
      r.setterRhs("mutableTags").contains(".toMutableList()"),
      "MutableList<String> @ScriptProperty must decode with .toMutableList() " +
        "(actual: '${r.setterRhs("mutableTags")}')",
    )
  }

  /** The mirror: an immutable `List<T>` must NOT gain the suffix (it would silently rewrap). */
  @Test
  fun immutableListPropertyHasNoToMutableListSuffix() {
    val r = emit(stringListProp("readonlyTags", mutable = false))
    assertEquals(emptyList(), r.errors)
    assertFalse(
      r.setterRhs("readonlyTags").contains(".toMutableList()"),
      "immutable List<String> @ScriptProperty must not gain a .toMutableList() suffix " +
        "(actual: '${r.setterRhs("readonlyTags")}')",
    )
  }
}
