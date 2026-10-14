import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * [KanamaAndroidRemap.strippedSourceLines] keeps code and string text, drops comments, and keeps
 * one entry per input line. The Python mirror (`scripts/check_android_remap_sources.py`) is held to
 * the same inputs by the red runs in `scripts/gate_red_runs.py`.
 */
class KanamaAndroidRemapStripTest {
    private fun strip(vararg lines: String) = KanamaAndroidRemap.strippedSourceLines(lines.toList())

    @Test
    fun `line and block comments are dropped, one entry per line`() {
        assertEquals(
            listOf("val a = 1 ", "", "val b = 2"),
            strip("val a = 1 // note", "/* block", "*/val b = 2"),
        )
    }

    @Test
    fun `a block comment opener inside a string is text`() {
        assertEquals(
            listOf("val s = \"a /* b\"", "fun zz() = Files.readString(x)"),
            strip("val s = \"a /* b\"", "fun zz() = Files.readString(x)"),
        )
    }

    @Test
    fun `a nested template whose inner literal holds a comment opener does not hide the next lines`() {
        val lines = strip("val s = \"\${\"/*\"}\"", "fun zz() = Files.readString(x)")
        assertEquals("val s = \"\${\"/*\"}\"", lines[0])
        assertEquals("fun zz() = Files.readString(x)", lines[1])
    }

    @Test
    fun `a double slash in a nested template literal does not cut the line`() {
        val line = "\"\\t\${quote(\"res://a/\${name}.gd\")},\""
        assertEquals(listOf(line), strip(line))
    }

    @Test
    fun `braces inside a template are counted, so its own closing brace ends it`() {
        val lines = strip("val s = \"\${listOf(1).map { it }}/*\" // c", "val t = 1")
        assertEquals("val s = \"\${listOf(1).map { it }}/*\" ", lines[0])
        assertEquals("val t = 1", lines[1])
    }

    @Test
    fun `a template can span lines and comments inside its code are still dropped`() {
        val lines = strip("val s = \"a\${", "  foo /* x */ + \"y/*\" // c", "}b\"", "val t = 1")
        assertEquals(listOf("val s = \"a\${", "  foo  + \"y/*\" ", "}b\"", "val t = 1"), lines)
    }

    @Test
    fun `a template in a raw string nests the same way`() {
        val lines =
            strip("val s = \"\"\"a \${\"/*\"} b", "c\"\"\"", "fun zz() = Files.readString(x)")
        assertEquals("fun zz() = Files.readString(x)", lines[2])
        assertTrue(lines[0].contains("\"/*\""))
    }

    @Test
    fun `an unterminated regular string does not leak into the next line`() {
        assertEquals(listOf("val s = \"oops", "val t = 1 "), strip("val s = \"oops", "val t = 1 // c"))
    }

    @Test
    fun `a character literal brace or quote is not a delimiter`() {
        assertEquals(
            listOf("val s = \"\${'}'}/*\"", "val t = 1"),
            strip("val s = \"\${'}'}/*\"", "val t = 1"),
        )
    }
}
