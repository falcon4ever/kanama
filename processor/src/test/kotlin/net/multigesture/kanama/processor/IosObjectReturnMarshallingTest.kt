package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Task 115 — Object-typed values must reach the engine from the iOS bridge.
 *
 * A `fun target(): Node?` used to be warned as "not yet marshalled on iOS" and dropped from
 * `callVReturning`, so `call("target")` answered nil on the phone while desktop returned the node.
 * `TypeMapping.OBJECT` is now in the marshalled return set (the runtime's encodeIosReturn ships the
 * wrapper's owner handle PT_OBJECT-tagged). Reverting the set entry fails this test.
 */
class IosObjectReturnMarshallingTest {

  private fun emit(vararg methods: MethodModel): Pair<String, List<String>> {
    val warnings = mutableListOf<String>()
    val model =
      ScriptModel(
        simpleName = "ObjectReturnFixture",
        fqName = "net.multigesture.kanama.test.ObjectReturnFixture",
        attachTo = "Node",
        isTool = false,
        isGlobalClass = false,
        properties = emptyList(),
        toolButtons = emptyList(),
        virtuals = emptyList(),
        methods = methods.toList(),
        signals = emptyList(),
      )
    val source =
      IosScriptCodeEmitter(
          listOf(IosScriptInput(model, "res://ObjectReturnFixture.kt")),
          warn = { warnings += it },
          error = { error(it) },
        )
        .registrySource()
    return source to warnings
  }

  private fun callVReturningBlock(source: String): String {
    val start = source.indexOf("override fun callVReturning(")
    if (start < 0) return ""
    val next = source.indexOf("override fun ", start + 20)
    return source.substring(start, if (next < 0) source.length else next)
  }

  @Test
  fun objectReturningMethodDispatchesViaCallVReturning() {
    val (source, warnings) =
      emit(
        MethodModel(
          kotlinName = "target",
          godotName = "target",
          returnType = TypeMapping.OBJECT,
          args = emptyList(),
          kind = MethodKind.REGULAR,
        )
      )
    assertEquals(
      emptyList(),
      warnings.filter { it.contains("target") },
      "an Object-returning method must not be warned as unmarshalled on iOS",
    )
    assertTrue(
      callVReturningBlock(source).contains("\"target\" -> script.target()"),
      "an Object-returning method must dispatch through callVReturning; block was:\n" +
        callVReturningBlock(source),
    )
  }
}
