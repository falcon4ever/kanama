package net.multigesture.kanama.processor

/**
 * Task 133 C — folds a constant `@Export` initializer to the literal the registrars report as the
 * default and the Web proxy declares: `Mathf.PI / 3.0` is `1.0471975511965976`, as GDScript folds
 * `PI / 3.0` in `@export var fov := PI / 3.0`.
 *
 * Accepted: number literals (`1`, `0.5`, `2.5e-3`, `1f`, `3L`), `+ - * /`, unary `-`/`+`,
 * parentheses, `PI` / `TAU` / `E` (bare or on `Mathf`, `GD`, `Math`, `kotlin.math`), and the
 * functions `degToRad`, `radToDeg` (bare, `Mathf.`, `GD.`), `Math.toRadians`, `Math.toDegrees`,
 * `sqrt`. Anything else (a reference to another property, a call) is not folded and the caller
 * keeps its own rules.
 *
 * Task 133 C2: every subexpression is typed and evaluated as Kotlin evaluates it — an unsuffixed
 * integer literal is an `Int` (a `Long` when it does not fit, or in a `Long` property's
 * initializer, where Kotlin's integer literal type follows the expected type), `3L` a `Long`, `1f`
 * a `Float`, a decimal a `Double`; a binary operation takes the wider operand type (Double >
 * Float > Long > Int), so `5 / 2 + 0.5` is `2 + 0.5 = 2.5` (integer division first) and `1f / 3f`
 * is the float `0.33333334`. Integer division truncates and `Int` wraps at 32 bits, as in Kotlin; a
 * division by an integer zero is not folded.
 */
internal object ConstantFolding {

  private const val DEG_TO_RAD = Math.PI / 180.0

  /** The folded Double default spelled as a literal both Kotlin and GDScript read back exactly. */
  fun foldDoubleLiteral(expression: String): String? {
    val value = Parser(expression, longContext = false).parseAll() ?: return null
    val double = value.toDouble()
    if (double.isNaN() || double.isInfinite()) return null
    return doubleLiteral(double)
  }

  /** The folded `Long` default (`60 * 5` is `300`), or null for a non-integer expression. */
  fun foldLongLiteral(expression: String): String? {
    val value = Parser(expression, longContext = true).parseAll() ?: return null
    return (value as? Num.Integral)?.value?.toString()
  }

  /** The folded `Float` default as a Kotlin Float literal (`1f / 3f` is `0.33333334f`), or null. */
  fun foldFloatLiteral(expression: String): String? {
    val value = Parser(expression, longContext = false).parseAll() as? Num.F ?: return null
    if (value.value.isNaN() || value.value.isInfinite()) return null
    return value.value.toString().replace("E", "e") + "f"
  }

  /** The folded `Int` default (Kotlin's 32-bit arithmetic), or null. */
  fun foldIntLiteral(expression: String): String? {
    val value = Parser(expression, longContext = false).parseAll() as? Num.I ?: return null
    return value.value.toString()
  }

  /** Shortest round-trip spelling, lower-case exponent (GDScript reads `1.0e-5`). */
  fun doubleLiteral(value: Double): String = value.toString().replace("E", "e")

  /** A typed constant: Kotlin's four numeric types an initializer can fold to. */
  private sealed interface Num {
    /** Kotlin's operand rank: the wider operand decides a binary operation's type. */
    val rank: Int

    fun toDouble(): Double

    sealed interface Integral : Num {
      val value: Long
    }

    data class I(val int: Int) : Integral {
      override val rank = 0
      override val value: Long
        get() = int.toLong()

      override fun toDouble() = int.toDouble()
    }

    data class L(override val value: Long) : Integral {
      override val rank = 1

      override fun toDouble() = value.toDouble()
    }

    data class F(val value: Float) : Num {
      override val rank = 2

      override fun toDouble() = value.toDouble()
    }

    data class D(val value: Double) : Num {
      override val rank = 3

      override fun toDouble() = value
    }
  }

  private fun Num.toFloat(): Float =
    when (this) {
      is Num.I -> int.toFloat()
      is Num.L -> value.toFloat()
      is Num.F -> value
      is Num.D -> value.toFloat()
    }

  private class Parser(private val text: String, private val longContext: Boolean) {
    private var pos = 0

    fun parseAll(): Num? {
      val value = runCatching { expression() }.getOrNull() ?: return null
      skipSpaces()
      return if (pos == text.length) value else null
    }

    private fun expression(): Num {
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

    private fun term(): Num {
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

    /** One Kotlin binary operation, in the wider operand's type. */
    private fun combine(a: Num, b: Num, op: Char): Num =
      when (maxOf(a.rank, b.rank)) {
        0 -> {
          val x = (a as Num.I).int
          val y = (b as Num.I).int
          Num.I(
            when (op) {
              '+' -> x + y
              '-' -> x - y
              '*' -> x * y
              else -> {
                require(y != 0)
                x / y
              }
            }
          )
        }
        1 -> {
          val x = (a as Num.Integral).value
          val y = (b as Num.Integral).value
          Num.L(
            when (op) {
              '+' -> x + y
              '-' -> x - y
              '*' -> x * y
              else -> {
                require(y != 0L)
                x / y
              }
            }
          )
        }
        2 -> {
          val x = a.toFloat()
          val y = b.toFloat()
          Num.F(
            when (op) {
              '+' -> x + y
              '-' -> x - y
              '*' -> x * y
              else -> x / y
            }
          )
        }
        else -> {
          val x = a.toDouble()
          val y = b.toDouble()
          Num.D(
            when (op) {
              '+' -> x + y
              '-' -> x - y
              '*' -> x * y
              else -> x / y
            }
          )
        }
      }

    private fun unary(): Num {
      skipSpaces()
      return when (peek()) {
        '-' -> {
          pos++
          when (val value = unary()) {
            is Num.I -> Num.I(-value.int)
            is Num.L -> Num.L(-value.value)
            is Num.F -> Num.F(-value.value)
            is Num.D -> Num.D(-value.value)
          }
        }
        '+' -> {
          pos++
          unary()
        }
        else -> primary()
      }
    }

    private fun primary(): Num {
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
        requireOwner(name)
        return when (name.substringAfterLast('.')) {
          "degToRad",
          "toRadians" -> Num.D(argument.toDouble() * DEG_TO_RAD)
          "radToDeg",
          "toDegrees" -> Num.D(argument.toDouble() / DEG_TO_RAD)
          // kotlin.math.sqrt has a Float overload.
          "sqrt" ->
            if (argument is Num.F) Num.F(kotlin.math.sqrt(argument.value))
            else Num.D(kotlin.math.sqrt(argument.toDouble()))
          else -> throw IllegalArgumentException("not a foldable function: $name")
        }
      }
      requireOwner(name)
      return when (name.substringAfterLast('.')) {
        "PI" -> Num.D(Math.PI)
        "TAU" -> Num.D(Math.PI * 2.0)
        "E" -> Num.D(Math.E)
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

    private fun number(): Num {
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
      return when (peek()) {
        'f',
        'F' -> {
          // A Float literal: its value is the float nearest the digits.
          pos++
          Num.F(digits.toFloat())
        }
        'd',
        'D' -> {
          pos++
          Num.D(digits.toDouble())
        }
        'L' -> {
          pos++
          require(!decimal)
          Num.L(digits.toLong())
        }
        else ->
          when {
            decimal -> Num.D(digits.toDouble())
            longContext -> Num.L(digits.toLong())
            else ->
              digits.toLong().let {
                if (it in Int.MIN_VALUE..Int.MAX_VALUE) Num.I(it.toInt()) else Num.L(it)
              }
          }
      }
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
