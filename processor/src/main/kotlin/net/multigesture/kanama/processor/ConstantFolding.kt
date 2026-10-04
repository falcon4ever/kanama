package net.multigesture.kanama.processor

/**
 * Task 133 C — folds a constant `@Export` initializer to the literal the registrars report as the
 * default and the Web proxy declares: `Mathf.PI / 3.0` is `1.0471975511965976`, as GDScript folds
 * `PI / 3.0` in `@export var fov := PI / 3.0`.
 *
 * Accepted: number literals (`1`, `0.5`, `2.5e-3`, `1f`, `3L`), `+ - * /`, unary `-`/`+`,
 * parentheses, `PI` / `TAU` / `E` (bare or on `Mathf`, `GD`, `Math`, `kotlin.math`), and the
 * functions `degToRad`, `radToDeg` (bare, `Mathf.`, `GD.`), `Math.toRadians`, `Math.toDegrees`,
 * `sqrt`. A Double expression is evaluated in IEEE double like Kotlin and GDScript; an integer
 * expression in 64-bit integers (division truncates, as in both languages). Anything else (a
 * reference to another property, a call) is not folded and the caller keeps its own rules.
 */
internal object ConstantFolding {

  private const val DEG_TO_RAD = Math.PI / 180.0

  /** The folded Double default spelled as a literal both Kotlin and GDScript read back exactly. */
  fun foldDoubleLiteral(expression: String): String? {
    val value = Parser(expression, integer = false).parseAll() ?: return null
    if (value.isNaN() || value.isInfinite()) return null
    return doubleLiteral(value)
  }

  /** The folded integer default (`60 * 5` is `300`), or null. */
  fun foldLongLiteral(expression: String): String? {
    val value = Parser(expression, integer = true).parseAll() ?: return null
    return value.toLong().toString()
  }

  /** Shortest round-trip spelling, lower-case exponent (GDScript reads `1.0e-5`). */
  fun doubleLiteral(value: Double): String = value.toString().replace("E", "e")

  private class Parser(private val text: String, private val integer: Boolean) {
    private var pos = 0

    fun parseAll(): Double? {
      val value = runCatching { expression() }.getOrNull() ?: return null
      skipSpaces()
      return if (pos == text.length) value else null
    }

    // Values travel as Double; with [integer] set every operation is done on Longs, so an integer
    // default stays exact up to 2^53 (constant defaults never come close).
    private fun expression(): Double {
      var left = term()
      while (true) {
        skipSpaces()
        left =
          when (peek()) {
            '+' -> {
              pos++
              combine(left, term(), '+')
            }
            '-' -> {
              pos++
              combine(left, term(), '-')
            }
            else -> return left
          }
      }
    }

    private fun term(): Double {
      var left = unary()
      while (true) {
        skipSpaces()
        left =
          when (peek()) {
            '*' -> {
              pos++
              combine(left, unary(), '*')
            }
            '/' -> {
              pos++
              combine(left, unary(), '/')
            }
            else -> return left
          }
      }
    }

    private fun combine(a: Double, b: Double, op: Char): Double {
      if (integer) {
        val x = a.toLong()
        val y = b.toLong()
        return when (op) {
          '+' -> (x + y).toDouble()
          '-' -> (x - y).toDouble()
          '*' -> (x * y).toDouble()
          else -> {
            require(y != 0L)
            (x / y).toDouble()
          }
        }
      }
      return when (op) {
        '+' -> a + b
        '-' -> a - b
        '*' -> a * b
        else -> a / b
      }
    }

    private fun unary(): Double {
      skipSpaces()
      return when (peek()) {
        '-' -> {
          pos++
          -unary()
        }
        '+' -> {
          pos++
          unary()
        }
        else -> primary()
      }
    }

    private fun primary(): Double {
      skipSpaces()
      val c = peek() ?: throw IllegalArgumentException("end of input")
      if (c == '(') {
        pos++
        val value = expression()
        expect(')')
        return value
      }
      if (c.isDigit() || c == '.') return number()
      val name = qualifiedName()
      skipSpaces()
      if (peek() == '(') {
        pos++
        val argument = expression()
        expect(')')
        require(!integer)
        return when (name.substringAfterLast('.')) {
          "degToRad",
          "toRadians" -> argument * DEG_TO_RAD
          "radToDeg",
          "toDegrees" -> argument / DEG_TO_RAD
          "sqrt" -> kotlin.math.sqrt(argument)
          else -> throw IllegalArgumentException("not a foldable function: $name")
        }.also { requireOwner(name) }
      }
      require(!integer)
      requireOwner(name)
      return when (name.substringAfterLast('.')) {
        "PI" -> Math.PI
        "TAU" -> Math.PI * 2.0
        "E" -> Math.E
        else -> throw IllegalArgumentException("not a constant: $name")
      }
    }

    private fun requireOwner(name: String) {
      val owner = name.substringBeforeLast('.', "")
      require(
        owner in
          setOf(
            "",
            "Mathf",
            "GD",
            "Math",
            "java.lang.Math",
            "kotlin.math",
            "net.multigesture.kanama.api.Mathf",
            "net.multigesture.kanama.api.GD",
          )
      )
    }

    private fun number(): Double {
      val start = pos
      while (peek()?.let { it.isDigit() || it == '_' } == true) pos++
      var decimal = false
      if (peek() == '.' && text.getOrNull(pos + 1)?.isDigit() == true) {
        decimal = true
        pos++
        while (peek()?.let { it.isDigit() || it == '_' } == true) pos++
      }
      if (peek() == 'e' || peek() == 'E') {
        decimal = true
        pos++
        if (peek() == '+' || peek() == '-') pos++
        while (peek()?.isDigit() == true) pos++
      }
      val digits = text.substring(start, pos).replace("_", "")
      val suffix = peek()
      when (suffix) {
        'f',
        'F' -> {
          // A Float literal: its value is the float nearest the digits.
          pos++
          require(!integer)
          return digits.toFloat().toDouble()
        }
        'd',
        'D' -> {
          pos++
          require(!integer)
          decimal = true
        }
        'L' -> {
          pos++
          require(!decimal)
        }
      }
      if (integer) {
        require(!decimal)
        return digits.toLong().toDouble()
      }
      return digits.toDouble()
    }

    private fun qualifiedName(): String {
      val start = pos
      while (peek()?.let { it.isLetterOrDigit() || it == '_' || it == '.' } == true) pos++
      require(pos > start)
      return text.substring(start, pos)
    }

    private fun expect(c: Char) {
      skipSpaces()
      require(peek() == c)
      pos++
    }

    private fun skipSpaces() {
      while (peek()?.isWhitespace() == true) pos++
    }

    private fun peek(): Char? = text.getOrNull(pos)
  }
}
