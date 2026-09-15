package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Task 114 — the generated iOS bridge must honour Kotlin default arguments.
 *
 * `Coin.spawn(coinDelay: Double = 0.5)` was emitted as `script.spawn(args[0] as Double)`, so a
 * Godot-side caller that omitted the defaulted parameter (`coin.spawn()`, `call("spawn")`) threw
 * `IndexOutOfBoundsException` inside `callV` and the uncaught exception aborted the app on the
 * iPhone. The JVM bridge has always dispatched by argument count; this locks the same shape on iOS
 * for both `callV` (void) and `callVReturning` (value-returning) methods, and keeps the single
 * positional call for methods without defaults.
 */
class IosCallVDefaultArgumentsTest {

  private fun method(name: String, args: List<ArgModel>, returnType: TypeMapping? = null) =
    MethodModel(
      kotlinName = name,
      godotName = name,
      returnType = returnType,
      args = args,
      kind = MethodKind.REGULAR,
    )

  private fun emit(vararg methods: MethodModel): String {
    val model =
      ScriptModel(
        simpleName = "DefaultsFixture",
        fqName = "net.multigesture.kanama.test.DefaultsFixture",
        attachTo = "Node",
        isTool = false,
        isGlobalClass = false,
        properties = emptyList(),
        toolButtons = emptyList(),
        virtuals = emptyList(),
        methods = methods.toList(),
        signals = emptyList(),
      )
    return IosScriptCodeEmitter(
        listOf(IosScriptInput(model, "res://DefaultsFixture.kt")),
        error = { error(it) },
      )
      .registrySource()
  }

  private fun line(source: String, godotName: String): String =
    source.lines().single { it.trimStart().startsWith("\"$godotName\" ->") }

  @Test
  fun voidMethodWithTrailingDefaultDispatchesByArgumentCount() {
    val source =
      emit(
        method(
          "spawn",
          listOf(ArgModel(name = "coinDelay", type = TypeMapping.FLOAT, hasDefault = true)),
        )
      )
    val spawn = line(source, "spawn")
    assertTrue(spawn.contains("when (args.size) {"), spawn)
    assertTrue(spawn.contains("0 -> script.spawn()"), spawn)
    assertTrue(spawn.contains("else -> script.spawn(args[0] as Double)"), spawn)
  }

  @Test
  fun onlyTrailingDefaultsProduceBranches() {
    val source =
      emit(
        method(
          "damage",
          listOf(
            ArgModel(name = "amount", type = TypeMapping.INT),
            ArgModel(name = "knockback", type = TypeMapping.FLOAT, hasDefault = true),
            ArgModel(name = "silent", type = TypeMapping.BOOL, hasDefault = true),
          ),
        )
      )
    val damage = line(source, "damage")
    assertTrue(damage.contains("1 -> script.damage(args[0] as Long)"), damage)
    assertTrue(damage.contains("2 -> script.damage(args[0] as Long, args[1] as Double)"), damage)
    assertTrue(
      damage.contains(
        "else -> script.damage(args[0] as Long, args[1] as Double, args[2] as Boolean)"
      ),
      damage,
    )
    // No branch for zero arguments: `amount` is required.
    assertEquals(false, damage.contains("0 -> script.damage()"), damage)
  }

  @Test
  fun valueReturningMethodGetsTheSameDispatch() {
    val source =
      emit(
        method(
          "score",
          listOf(ArgModel(name = "bonus", type = TypeMapping.INT, hasDefault = true)),
          returnType = TypeMapping.INT,
        )
      )
    val score = line(source, "score")
    assertTrue(
      score.contains(
        "when (args.size) { 0 -> script.score(); else -> script.score(args[0] as Long) }"
      ),
      score,
    )
  }

  @Test
  fun methodWithoutDefaultsKeepsTheSinglePositionalCall() {
    val source = emit(method("reset", listOf(ArgModel(name = "hard", type = TypeMapping.BOOL))))
    val reset = line(source, "reset")
    assertEquals(false, reset.contains("when (args.size)"), reset)
    assertTrue(reset.contains("script.reset(args[0] as Boolean)"), reset)
  }
}
