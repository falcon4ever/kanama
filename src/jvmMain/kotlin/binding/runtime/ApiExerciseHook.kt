package net.multigesture.kanama.binding.runtime

import net.multigesture.kanama.api.Engine
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.OS

/**
 * Runs the generated API exercise once, on the first frame of the main loop (called from the script
 * language's `frame()`), on the desktop and Android: one row per `ObjectCalls` helper the shared
 * wrapper tree calls, each asserting a value a silent no-op cannot produce (see
 * `docs/reference/generated/api-exercise.md` and the rows' runner,
 * `exercise/ApiExerciseRunner.kt`).
 *
 * A Kanama test hook, OFF for users, decided like [SignalSelfTest]: the project setting [SETTING]
 * (`kanama/debug/api_exercise`, default false) in a debug build that is not the editor, or
 * [ENVIRONMENT_VARIABLE] (`KANAMA_API_EXERCISE=1` forces it on, `0` off). The rows themselves are
 * compiled only into a build made with `-PkanamaApiExercise=true`, which no publish or install task
 * accepts; this hook is in every build and reaches them by reflection, so a run asked for in a
 * build without them prints a FAIL line instead of nothing. `KANAMA_API_EXERCISE_TRACE=1` prints
 * one line before each row (the headless sweep reads it to attribute an engine error or a crash).
 */
internal object ApiExerciseHook {
  const val ENVIRONMENT_VARIABLE: String = "KANAMA_API_EXERCISE"
  const val TRACE_VARIABLE: String = "KANAMA_API_EXERCISE_TRACE"
  const val SETTING: String = "kanama/debug/api_exercise"
  private const val ENTRY_CLASS = "net.multigesture.kanama.exercise.ApiExercise"

  @Volatile private var done = false

  private fun boolSetting(name: String): Boolean {
    val settings = GodotObject(GodotHandle(ObjectCalls.getSingleton("ProjectSettings")))
    return settings.call("has_setting", name) == true && settings.call("get_setting", name) == true
  }

  /** Called on every main-loop frame; does its work on the first one only. */
  fun onFrame() {
    if (done) return
    done = true
    val enabled =
      runCatching {
          SignalSelfTest.decide(
            OS.getEnvironment(ENVIRONMENT_VARIABLE),
            setting = { boolSetting(SETTING) },
            debugBuild = { OS.isDebugBuild() },
            editorHint = { Engine.isEditorHint() },
          )
        }
        .getOrDefault(false)
    if (!enabled) return
    val trace = runCatching { OS.getEnvironment(TRACE_VARIABLE).trim() == "1" }.getOrDefault(false)
    val entry =
      try {
        Class.forName(ENTRY_CLASS)
          .getMethod("runDesktop", Boolean::class.javaPrimitiveType, Function1::class.java)
      } catch (_: ReflectiveOperationException) {
        System.err.println(
          "[kanama] API EXERCISE FAIL: the exercise rows are not in this build " +
            "(build kanama.jar with -PkanamaApiExercise=true)"
        )
        return
      }
    val print: (String) -> Unit = { line -> System.err.println(line) }
    try {
      entry.invoke(null, trace, print)
    } catch (t: Throwable) {
      val cause = (t as? java.lang.reflect.InvocationTargetException)?.targetException ?: t
      System.err.println(
        "[kanama] API EXERCISE FAIL: ${cause::class.qualifiedName}: ${cause.message}"
      )
      cause.printStackTrace(System.err)
    }
  }
}
