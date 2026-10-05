package net.multigesture.kanama.api

/**
 * A [SignalArgReader] over arguments that were already decoded (`GodotObject.call` style): the
 * registrars generated before task 131 hand the runtime a `List<Any?>`, and the JVM tests drive the
 * typed signals through it without an engine. It applies the same type rules as the platform
 * readers.
 */
internal class ListSignalArgReader(private val values: List<Any?>) : SignalArgReader {
  override val count: Int
    get() = values.size

  private fun at(index: Int): Any? {
    if (index !in values.indices) {
      throw SignalArgumentException("argument ${index + 1} was not emitted (${values.size} were)")
    }
    return values[index]
  }

  private fun mismatch(index: Int, expected: String, value: Any?): Nothing =
    throw SignalArgumentException(
      "argument ${index + 1}: expected $expected, got ${value?.let { it::class.simpleName } ?: "null"}"
    )

  override fun long(index: Int): Long = at(index).let { it as? Long ?: mismatch(index, "int", it) }

  override fun double(index: Int): Double =
    when (val value = at(index)) {
      is Double -> value
      is Long -> value.toDouble()
      else -> mismatch(index, "float", value)
    }

  override fun bool(index: Int): Boolean = at(index).let { it as? Boolean ?: mismatch(index, "bool", it) }

  override fun string(index: Int): String = at(index).let { it as? String ?: mismatch(index, "String", it) }

  override fun objectHandle(index: Int): GodotHandle? =
    when (val value = at(index)) {
      null -> null
      is GodotObject -> value.handle
      else -> mismatch(index, "Object", value)
    }

  override fun value(index: Int): Any? = at(index)
}
