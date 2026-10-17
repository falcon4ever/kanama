package net.multigesture.kanama.binding.runtime

import net.multigesture.kanama.exercise.ApiExercise

// The generated API exercise on iOS: the common rows (exercise/ApiExercise.generated.kt, one per
// ObjectCalls helper with a static, property-pair, const or singleton row, and the RID factory
// rows) run through the Kotlin/Native runtime and the C shim, at the end of the frame-1 self-test
// phase (every class and singleton registered). A
// Kanama test hook like the other self-tests: this file and the rows are compiled only into a
// -PkanamaIosSelfTest=true build (build.gradle.kts), which the device runners make.
//
// Prints one summary line the device runners REQUIRE (scripts/ios_visual_smoke.sh, kanama-demos
// scripts/ios_device_run.sh), in one line:
//
//   [kanama][ios][kn] OBJECTCALLS SELFTEST (api exercise): <n> passed, <m> failed, <w> weak,
//   <k> known, <r> reach faults=<f> expected=<e> in <ms> ms
//
// and one `OBJECTCALLS SELFTEST (api exercise) FAIL: <helpers> <row> <what>` line per failed row. A
// known finding (KNOWN_FINDINGS, scripts/api_exercise_policy.py) that fails prints `... KNOWN: ...`
// instead, which no runner treats as a failure; one that passes prints a FAIL line.
// A factory row whose RID is invalid on both paths (the renderer makes no such resource) prints a
// `... (api exercise) WEAK: ...` line with that reason and counts as weak.
// The fault count is the process-wide one, so a row whose call the shim refused shows as a FAULT
// line and as faults above the deliberate probes' [expectedFaults]. The demos runner fails on both;
// ios_visual_smoke.sh does not compare the counts (it fails on FAIL lines and failed rows).
internal fun apiExerciseSelfTestRows(expectedFaults: Int) {
  val result =
    ApiExercise.run(
      platform = "ios",
      fail = { println("[kanama][ios][kn] OBJECTCALLS SELFTEST (api exercise) FAIL: $it") },
      known = { println("[kanama][ios][kn] OBJECTCALLS SELFTEST (api exercise) KNOWN: $it") },
      weak = { println("[kanama][ios][kn] OBJECTCALLS SELFTEST (api exercise) WEAK: $it") },
      trace = null,
    )
  println(
    "[kanama][ios][kn] OBJECTCALLS SELFTEST (api exercise): ${result.passed} passed, " +
      "${result.failed} failed, ${result.weak} weak, ${result.known} known, ${result.reach} reach " +
      "faults=${ObjectCalls.faultCount()} expected=$expectedFaults in ${result.millis} ms"
  )
}
