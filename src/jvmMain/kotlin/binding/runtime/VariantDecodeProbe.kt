package net.multigesture.kanama.binding.runtime

import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.JAVA_BYTE
import java.lang.foreign.ValueLayout.JAVA_LONG
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.Resource

/**
 * The decode-buffer probe `FreedObjectSmoke` runs (the Godot 4.8 trial's top break). Public for
 * that smoke scene only; game code does not call it.
 *
 * Each container decode of [BuiltinTypes] reads the elements one by one through `Array.get` /
 * `Dictionary.get` (and a Dictionary's keys through `Array.get` of `keys()`), and Godot ASSIGNS the
 * Variant result into the buffer Kanama passes. A buffer reused after a plain `variant_destroy`,
 * not [BuiltinTypes.destroyVariantToNil], still holds the previous element, so the assignment
 * releases that element a second time.
 *
 * Every decode gets a fresh container. An Array is `[v0, v1, bound(v2), bound(v3), nil]`: two
 * Resources, then two Callables bound (`bindv`) to two more Resources, then nil, so each element is
 * followed by one that overwrites the buffer. A Dictionary holds the same values under String keys,
 * after two entries whose KEYS are a Resource `k0` and `bound(k1)` (values nil), so its key buffer
 * sees an Object and a custom Callable too. A Godot 4.8 Object Variant no longer nulls its pointer
 * on destroy, so it is released twice there; a bound Callable is a custom Callable, released twice
 * on 4.7 as well, which keeps the probe meaningful on 4.7.2. The result per decode is the
 * Resources' reference counts after the decode (2 each: the probe's wrapper, and the container or
 * the bound Callable), then, after the container is destroyed, 1 each: `2,2,2,2:1,1,1,1` for an
 * Array, the same with `k0,k1` appended for a Dictionary. A wrong count leaves the container
 * undestroyed (`:kept`), because it holds an element already released once too often; its healthy
 * entries are cleared first, so nothing else leaks.
 */
object VariantDecodeProbe {
  private class Decoder(
    val name: String,
    val dictionary: Boolean,
    val decode: (MemorySegment, Arena) -> Unit,
  )

  private val decoders =
    listOf(
      Decoder("array_objects", false) { c, _ -> BuiltinTypes.readArrayObjects(c) },
      Decoder("array_scalars", false) { c, _ -> BuiltinTypes.readArrayScalars(c) },
      Decoder("array_vector2i", false) { c, _ -> BuiltinTypes.readArrayVector2i(c) },
      Decoder("array_vector3i", false) { c, _ -> BuiltinTypes.readArrayVector3i(c) },
      Decoder("array_longs", false) { c, _ -> BuiltinTypes.readArrayLongs(c) },
      Decoder("dictionary_scalars", true) { c, _ -> BuiltinTypes.readDictionaryScalars(c) },
      Decoder("dictionary_any", true) { c, arena ->
        val variant = arena.allocate(BuiltinTypes.VARIANT_SIZE, 8L)
        VariantConverters.variantFromType(VariantType.DICTIONARY).invoke(variant, c)
        try {
          BuiltinTypes.readVariantDictionaryAny(variant, arena)
        } finally {
          BuiltinTypes.destroyVariant(variant)
        }
      },
    )

  private const val ARRAY_PUSH_BACK_HASH = 3316032543L
  private const val ARRAY_SET_HASH = 3798478031L
  private const val DICTIONARY_SET_HASH = 2175348267L
  private const val DICTIONARY_ERASE_HASH = 1776646889L
  private const val CALLABLE_BINDV_HASH = 3564560322L
  private val VALUE_KEYS = listOf("v0", "v1", "v2", "v3", "n")

  /** `decode_buffers <decode>=<counts after>:<counts after the destroy> ...`; [target] is bound. */
  fun run(target: GodotObject): String =
    decoders.joinToString(" ", prefix = "decode_buffers ") { "${it.name}=${probe(target, it)}" }

  private fun probe(target: GodotObject, decoder: Decoder): String {
    val dictionary = decoder.dictionary
    // v0..v3, then (a Dictionary) the key Resources k0, k1.
    val held = List(if (dictionary) 6 else 4) { Resource.create() }
    try {
      Arena.ofConfined().use { arena ->
        val container = arena.allocate(8L, 8L)
        val type = if (dictionary) VariantType.DICTIONARY else VariantType.ARRAY
        BuiltinTypes.construct(type, container, constructorIndex = 0)
        if (dictionary) {
          setEntry(container, { objectVariant(it, held[4]) }, BuiltinTypes::initNilVariant)
          setEntry(
            container,
            { boundCallable(it, target, held[5], arena) },
            BuiltinTypes::initNilVariant,
          )
        }
        for (i in 0 until 5) {
          val init: (MemorySegment) -> Unit =
            when (i) {
              0,
              1 -> { out -> objectVariant(out, held[i]) }
              2,
              3 -> { out -> boundCallable(out, target, held[i], arena) }
              else -> BuiltinTypes::initNilVariant
            }
          if (dictionary) setEntry(container, stringKey(VALUE_KEYS[i]), init)
          else arrayCall(container, "push_back", ARRAY_PUSH_BACK_HASH, null, init)
        }
        val expected = held.joinToString(",") { "2" }
        val before = counts(held)
        decoder.decode(container, arena)
        val after = counts(held)
        if (before != expected || after != before) {
          clearHealthy(container, dictionary, target, held, arena)
          return "$after:kept(before=$before)"
        }
        BuiltinTypes.destroyTyped(type, container)
        return "$after:${counts(held)}"
      }
    } finally {
      held.forEach { it.close() }
    }
  }

  /**
   * Drops every entry of [container] whose Resource still counts 2, leaving only the ones released
   * once too often (destroying or touching those would release them again).
   */
  private fun clearHealthy(
    container: MemorySegment,
    dictionary: Boolean,
    target: GodotObject,
    held: List<Resource>,
    arena: Arena,
  ) {
    held.forEachIndexed { i, resource ->
      if (resource.getReferenceCount() != 2) return@forEachIndexed
      when {
        !dictionary ->
          arrayCall(container, "set", ARRAY_SET_HASH, i.toLong(), BuiltinTypes::initNilVariant)
        i < 4 -> setEntry(container, stringKey(VALUE_KEYS[i]), BuiltinTypes::initNilVariant)
        i == 4 -> eraseEntry(container) { objectVariant(it, resource) }
        // An equal Callable finds the key: a bound Callable compares by its callable and binds.
        else -> eraseEntry(container) { boundCallable(it, target, resource, arena) }
      }
    }
  }

  private fun counts(held: List<Resource>): String =
    held.joinToString(",") { it.getReferenceCount().toString() }

  private fun objectVariant(out: MemorySegment, value: Resource) {
    Arena.ofConfined().use { arena -> BuiltinTypes.initVariantFromAny(out, value, arena) }
  }

  private fun stringKey(key: String): (MemorySegment) -> Unit = { out ->
    Arena.ofConfined().use { arena -> BuiltinTypes.initVariantFromAny(out, key, arena) }
  }

  /** Builds a Variant with [init], runs [use] on it, destroys it. */
  private inline fun withVariant(init: (MemorySegment) -> Unit, use: (MemorySegment) -> Unit) {
    Arena.ofConfined().use { arena ->
      val variant = arena.allocate(BuiltinTypes.VARIANT_SIZE, 8L)
      init(variant)
      try {
        use(variant)
      } finally {
        BuiltinTypes.destroyVariant(variant)
      }
    }
  }

  /** `Array.push_back(value)` ([index] null) or `Array.set(index, value)`. */
  private fun arrayCall(
    container: MemorySegment,
    method: String,
    hash: Long,
    index: Long?,
    init: (MemorySegment) -> Unit,
  ) {
    withVariant(init) { value ->
      if (index == null) {
        BuiltinTypes.call(VariantType.ARRAY, method, hash, container, listOf(value))
      } else {
        Arena.ofConfined().use { arena ->
          val slot = arena.allocate(JAVA_LONG)
          slot.set(JAVA_LONG, 0, index)
          BuiltinTypes.call(VariantType.ARRAY, method, hash, container, listOf(slot, value))
        }
      }
    }
  }

  /** `Dictionary.set(key, value)`. */
  private fun setEntry(
    container: MemorySegment,
    key: (MemorySegment) -> Unit,
    value: (MemorySegment) -> Unit,
  ) {
    withVariant(key) { k ->
      withVariant(value) { v ->
        Arena.ofConfined().use { arena ->
          BuiltinTypes.call(
            VariantType.DICTIONARY,
            "set",
            DICTIONARY_SET_HASH,
            container,
            listOf(k, v),
            arena.allocate(JAVA_BYTE),
          )
        }
      }
    }
  }

  /** `Dictionary.erase(key)`. */
  private fun eraseEntry(container: MemorySegment, key: (MemorySegment) -> Unit) {
    withVariant(key) { k ->
      Arena.ofConfined().use { arena ->
        BuiltinTypes.call(
          VariantType.DICTIONARY,
          "erase",
          DICTIONARY_ERASE_HASH,
          container,
          listOf(k),
          arena.allocate(JAVA_BYTE),
        )
      }
    }
  }

  /** `Callable(target, "get_name").bindv([bound])` as a Variant in [out]: a custom Callable. */
  private fun boundCallable(
    out: MemorySegment,
    target: GodotObject,
    bound: Resource,
    arena: Arena,
  ) {
    val plain = BuiltinTypes.allocateCallable(arena)
    val args = arena.allocate(8L, 8L)
    val result = BuiltinTypes.allocateCallable(arena)
    BuiltinTypes.initCallable(plain, target.handle.segment, "get_name")
    try {
      BuiltinTypes.initArrayOfObjects(args, listOf(bound))
      try {
        BuiltinTypes.call(
          VariantType.CALLABLE,
          "bindv",
          CALLABLE_BINDV_HASH,
          plain,
          listOf(args),
          result,
        )
        try {
          VariantConverters.variantFromType(VariantType.CALLABLE).invoke(out, result)
        } finally {
          BuiltinTypes.destroyTyped(VariantType.CALLABLE, result)
        }
      } finally {
        BuiltinTypes.destroyTyped(VariantType.ARRAY, args)
      }
    } finally {
      BuiltinTypes.destroyTyped(VariantType.CALLABLE, plain)
    }
  }
}
