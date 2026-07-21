import java.io.File
import org.gradle.api.GradleException

/**
 * The PanamaPort textual source remap and its guarding audits, shared by the
 * `:plugin` (runtime AAR) and `:scripts` (per-project scripts AAR) modules.
 *
 * The remap is a pragmatic Android ART compatibility path (AGENTS.md "Looks
 * Wrong But Isn't"): desktop sources written against `java.lang.foreign` and
 * signature-polymorphic `MethodHandle.invoke` are rewritten line-by-line to
 * `com.v7878.foreign` / `invokeWithArguments`, with targeted undo rules for
 * the known Kotlin-callback invoke sites. Do not widen its scope without
 * extending the audits below — they are the mitigation that makes the hack
 * acceptable.
 */
object KanamaAndroidRemap {

    data class Rule(
        val name: String,
        val needle: String,
        val replacement: String,
    )

    val rules = listOf(
        Rule(
            name = "foreign-package",
            needle = "java.lang.foreign",
            replacement = "com.v7878.foreign",
        ),
        Rule(
            name = "panama-method-handle-invoke",
            needle = ".invoke(",
            replacement = ".invokeWithArguments(",
        ),
        Rule(
            name = "kotlin-registration-callback-invoke",
            needle = "registerAll.invokeWithArguments",
            replacement = "registerAll.invoke",
        ),
        Rule(
            name = "kotlin-script-factory-invoke",
            needle = "script.factory?.invokeWithArguments",
            replacement = "script.factory?.invoke",
        ),
        Rule(
            name = "kotlin-property-default-callback-invoke",
            needle = "writePropertyDefault?.invokeWithArguments",
            replacement = "writePropertyDefault?.invoke",
        ),
        Rule(
            name = "kotlin-dispatch-has-method-callback-invoke",
            needle = "dispatchHasMethod?.invokeWithArguments",
            replacement = "dispatchHasMethod?.invoke",
        ),
        Rule(
            name = "kotlin-dispatch-call-callback-invoke",
            needle = "dispatchCall?.invokeWithArguments",
            replacement = "dispatchCall?.invoke",
        ),
        Rule(
            name = "kotlin-signal-callback-invoke",
            needle = "callbacks[id]?.invokeWithArguments",
            replacement = "callbacks[id]?.invoke",
        ),
        Rule(
            name = "generated-signal-callback-registry-invoke",
            needle = "SignalCallbackRegistry.invokeWithArguments",
            replacement = "SignalCallbackRegistry.invoke",
        ),
    )

    val forbiddenSourceFragments = listOf(
        "java.lang.foreign",
        "expect class",
        "actual class",
        "Files.readString",
        "Files.writeString",
        "getClassLoadingLock",
        "registerAll.invokeWithArguments",
        "script.factory?.invokeWithArguments",
        "writePropertyDefault?.invokeWithArguments",
        "dispatchHasMethod?.invokeWithArguments",
        "dispatchCall?.invokeWithArguments",
        "callbacks[id]?.invokeWithArguments",
        "SignalCallbackRegistry.invokeWithArguments",
        "?.invokeWithArguments(",
    )

    val forbiddenDemoSourcePatterns = listOf(
        Regex("""\?\.\s*invoke\s*\(""") to
            "nullable Kotlin callback invoke is unsafe for Android source remap; use explicit state or callback?.let { it() }",
    )

    fun remapLine(line: String): String =
        rules.fold(line) { rewritten, rule ->
            rewritten.replace(rule.needle, rule.replacement)
        }

    /** Pre-remap audit of consumer-project Kotlin sources (nullable callback invokes). */
    fun auditOriginalDemoSources(root: File) {
        val failures = mutableListOf<String>()
        if (!root.exists()) return

        root.walkTopDown()
            .filter { it.isFile && it.extension == "kt" }
            .forEach { file ->
                file.readLines().forEachIndexed { index, line ->
                    val sourceLine = line.substringBefore("//")
                    forbiddenDemoSourcePatterns.forEach { (pattern, message) ->
                        if (pattern.containsMatchIn(sourceLine)) {
                            failures += "${file.relativeTo(root)}:${index + 1}: $message"
                        }
                    }
                }
            }

        if (failures.isNotEmpty()) {
            throw GradleException(
                buildString {
                    appendLine("Android demo source audit failed before remap:")
                    failures.take(40).forEach { appendLine("  $it") }
                    if (failures.size > 40) {
                        appendLine("  ... ${failures.size - 40} more")
                    }
                },
            )
        }
    }

    /** Post-remap audit of a generated Android source tree (stale desktop APIs, bad rewrites). */
    fun auditGeneratedSources(root: File, label: String) {
        val failures = mutableListOf<String>()
        root.walkTopDown()
            .filter { it.isFile && (it.extension == "kt" || it.extension == "java") }
            .forEach { file ->
                file.readLines().forEachIndexed { index, line ->
                    val sourceLine = line.substringBefore("//")
                    forbiddenSourceFragments.forEach { fragment ->
                        if (sourceLine.contains(fragment)) {
                            failures += "${file.relativeTo(root)}:${index + 1}: forbidden Android source fragment '$fragment'"
                        }
                    }
                }
            }

        if (failures.isNotEmpty()) {
            throw GradleException(
                buildString {
                    appendLine("Android Kanama source remap audit failed ($label):")
                    failures.take(40).forEach { appendLine("  $it") }
                    if (failures.size > 40) {
                        appendLine("  ... ${failures.size - 40} more")
                    }
                },
            )
        }
    }
}
