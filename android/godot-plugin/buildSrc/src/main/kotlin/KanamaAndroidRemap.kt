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

    /**
     * A copied file whose name ends with this is skipped entirely: it holds `expect` declarations
     * only (task 104 step 3 parcel C'), which have no body to remap. Android compiles the
     * `src/jvmMain` `actual` beside it as plain Kotlin, with the modifier stripped by the
     * `actual-modifier` rule below.
     */
    const val EXPECT_FILE_SUFFIX = ".expect.kt"

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

    /** Kotlin modifiers and annotations that may sit between the line start and `actual`. */
    private const val LEADING_MODIFIERS =
        """(?:@[\w.]+(?:\([^)]*\))?\s+|(?:public|internal|private|protected|open|final|abstract|override|inline|infix|operator|suspend|external|tailrec|companion|data|value|sealed|enum|annotation|inner|const|lateinit)\s+)*"""

    /** The declaration keywords an `expect`/`actual` modifier can precede. */
    private const val DECLARATION_KEYWORDS = """(class|object|interface|val|var|fun|typealias)"""

    /**
     * The `actual` modifier of a `src/jvmMain` declaration, dropped on the way in.
     *
     * The root module is Kotlin Multiplatform (task 104 step 3) and its JVM half carries `actual`
     * on the declarations that implement the common `expect` seams -- `RawSegment`, `NULL_SEGMENT`,
     * `BuiltinCalls`, `ObjectCalls` and its 1,352 members. Android compiles the SAME sources as a
     * plain Kotlin library with no common fragment (the `*.expect.kt` files are skipped), so the
     * modifier has to go.
     *
     * Any run of annotations and modifiers may precede it -- `@JvmStatic actual fun`,
     * `override actual fun`, `internal actual val` -- which the earlier "optional visibility
     * keyword only" spelling copied through unstripped (task 119 finding 20). The match is still
     * anchored at the line start through that run, so `actual` inside prose (`* the actual class of
     * the object`, `// actual fun …`) is left alone: those lines begin with `*` or `//`, which the
     * run does not admit.
     */
    private val ACTUAL_MODIFIER = Regex("""^(\s*$LEADING_MODIFIERS)actual\s+""")

    val forbiddenSourceFragments = listOf(
        "java.lang.foreign",
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

    /**
     * `expect` / `actual` declarations must never reach the Android tree.
     *
     * The root module is Kotlin Multiplatform (task 104 step 3) and Android compiles a COPY of its
     * common and JVM sources as a plain Kotlin library: there is no common fragment, so an `expect`
     * declaration would be a compile error and an `actual` modifier would have nothing to
     * actualize. The `*.expect.kt` files are skipped on the way in ([isSkippedSourceFile]) and the
     * `actual-modifier` rule strips the modifier; this pattern is the assertion that both worked.
     *
     * A declaration keyword, not the bare words: `expect` and `actual` appear in KDoc and in
     * identifiers all over these sources ("the expect declaration", `actualValue`), and the two
     * fragments this replaced (`expect class` / `actual class`) tripped on prose that merely quoted
     * them (task 104 step 3 parcel A hit exactly that). Modifiers and annotations between the
     * keyword and the declaration are allowed for, and the match is not anchored -- the lines
     * reaching it have had their comments stripped, so anywhere on the line is code (task 119
     * finding 20).
     */
    val forbiddenDeclarationPattern =
        Regex("""\b(expect|actual)\s+$LEADING_MODIFIERS$DECLARATION_KEYWORDS\b""")

    val forbiddenDemoSourcePatterns = listOf(
        Regex("""\?\.\s*invoke\s*\(""") to
            "nullable Kotlin callback invoke is unsafe for Android source remap; use explicit state or callback?.let { it() }",
    )

    fun remapLine(line: String): String =
        rules
            .fold(line) { rewritten, rule -> rewritten.replace(rule.needle, rule.replacement) }
            .let { ACTUAL_MODIFIER.replace(it) { match -> match.groupValues[1] } }

    /** True for a copied file the remap skips entirely (see [EXPECT_FILE_SUFFIX]). */
    fun isSkippedSourceFile(name: String): Boolean = name.endsWith(EXPECT_FILE_SUFFIX)

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

    /**
     * Strip Kotlin comments, leaving one entry per input line so diagnostics keep their numbers.
     *
     * Line comments AND block comments/KDoc: what the audits below assert is about CODE, and the
     * remapped sources carry KDoc that quotes the very things they forbid -- `java.lang.foreign`
     * when it explains the PanamaPort rewrite, `expect`/`actual` when it explains the seam.
     * String literals are not tracked: a block-comment opener inside a string literal is
     * vanishingly rare here, and treating it as a comment can only make the audit stricter than
     * the code it reads, never blind to it.
     */
    fun strippedSourceLines(lines: List<String>): List<String> {
        var inBlockComment = false
        return lines.map { line ->
            val out = StringBuilder()
            var i = 0
            while (i < line.length) {
                if (inBlockComment) {
                    if (line.startsWith("*/", i)) {
                        inBlockComment = false
                        i += 2
                    } else {
                        i++
                    }
                    continue
                }
                if (line.startsWith("/*", i)) {
                    inBlockComment = true
                    i += 2
                    continue
                }
                if (line.startsWith("//", i)) {
                    break
                }
                out.append(line[i])
                i++
            }
            out.toString()
        }
    }

    /** Post-remap audit of a generated Android source tree (stale desktop APIs, bad rewrites). */
    fun auditGeneratedSources(root: File, label: String) {
        val failures = mutableListOf<String>()
        root.walkTopDown()
            .filter { it.isFile && (it.extension == "kt" || it.extension == "java") }
            .forEach { file ->
                if (isSkippedSourceFile(file.name)) {
                    failures += "${file.relativeTo(root)}: an `expect` source file reached the Android tree"
                    return@forEach
                }
                strippedSourceLines(file.readLines()).forEachIndexed { index, sourceLine ->
                    forbiddenSourceFragments.forEach { fragment ->
                        if (sourceLine.contains(fragment)) {
                            failures += "${file.relativeTo(root)}:${index + 1}: forbidden Android source fragment '$fragment'"
                        }
                    }
                    forbiddenDeclarationPattern.find(sourceLine)?.let { match ->
                        failures +=
                            "${file.relativeTo(root)}:${index + 1}: forbidden Android source declaration " +
                                "'${match.groupValues[1]} ${match.groupValues[2]}' (the Android tree has no common fragment)"
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
