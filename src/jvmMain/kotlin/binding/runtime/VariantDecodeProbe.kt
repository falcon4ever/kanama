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
 * `Dictionary.get`, and Godot ASSIGNS the Variant result into the buffer Kanama passes. A buffer
 * reused after a plain `variant_destroy`, not [BuiltinTypes.destroyVariantToNil], still holds the
 * previous element, so the assignment releases that element a second time.
 *
 * Every decode gets a fresh container `[o0, o1, c0, c1, nil]`: two Resources, then two Callables
 * bound (`bindv`) to two more Resources, then nil, so each element is followed by one that
 * overwrites the buffer. A Godot 4.8 Object Variant no longer nulls its pointer on destroy, so it
 * is released twice there; a bound Callable is a custom Callable, released twice on 4.7 as well,
 * which keeps the probe meaningful on 4.7.2. The result per decode is the four Resources' reference
 * counts after the decode (2 each: the probe's wrapper, and the container or the bound Callable),
 * then, after the container is destroyed, 1 each: `2,2,2,2:1,1,1,1`. A wrong count leaves the
 * container undestroyed (`:kept`), because it holds an element already released once too often.
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
  private const val CALLABLE_BINDV_HASH = 3564560322L
  private val KEYS = listOf("o0", "o1", "c0", "c1", "n")

  /** `decode_buffers <decode>=<counts after>:<counts after the destroy> ...`; [target] is bound. */
  fun run(target: GodotObject): String =
    decoders.joinToString(" ", prefix = "decode_buffers ") { "${it.name}=${probe(target, it)}" }

  private fun probe(target: GodotObject, decoder: Decoder): String {
    val held = List(4) { Resource.create() }
    try {
      Arena.ofConfined().use { arena ->
        val container = arena.allocate(8L, 8L)
        val type = if (decoder.dictionary) VariantType.DICTIONARY else VariantType.ARRAY
        BuiltinTypes.construct(type, container, constructorIndex = 0)
        put(container, decoder.dictionary, 0, append = true) {
          BuiltinTypes.initVariantFromAny(it, held[0], arena)
        }
        put(container, decoder.dictionary, 1, append = true) {
          BuiltinTypes.initVariantFromAny(it, held[1], arena)
        }
        put(container, decoder.dictionary, 2, append = true) {
          boundCallable(it, target, held[2], arena)
        }
        put(container, decoder.dictionary, 3, append = true) {
          boundCallable(it, target, held[3], arena)
        }
        put(container, decoder.dictionary, 4, append = true) { BuiltinTypes.initNilVariant(it) }
        val before = counts(held)
        decoder.decode(container, arena)
        val after = counts(held)
        if (before != "2,2,2,2" || after != before) {
          // The container keeps the slot released once too often (destroying it would release
          // that element again); every other slot is set to nil, so nothing else leaks.
          held.forEachIndexed { i, resource ->
            if (resource.getReferenceCount() == 2) {
              put(container, decoder.dictionary, i, append = false) {
                BuiltinTypes.initNilVariant(it)
              }
            }
          }
          return "$after:kept(before=$before)"
        }
        BuiltinTypes.destroyTyped(type, container)
        return "$after:${counts(held)}"
      }
    } finally {
      held.forEach { it.close() }
    }
  }

  private fun counts(held: List<Resource>): String =
    held.joinToString(",") { it.getReferenceCount().toString() }

  /**
   * Puts the Variant [init] builds into [container]: appended or at [index] of an Array, under key
   * `KEYS[index]` of a Dictionary.
   */
  private fun put(
    container: MemorySegment,
    dictionary: Boolean,
    index: Int,
    append: Boolean,
    init: (MemorySegment) -> Unit,
  ) {
    Arena.ofConfined().use { arena ->
      val value = arena.allocate(BuiltinTypes.VARIANT_SIZE, 8L)
      init(value)
      try {
        if (!dictionary && append) {
          BuiltinTypes.call(
            VariantType.ARRAY,
            "push_back",
            ARRAY_PUSH_BACK_HASH,
            container,
            listOf(value),
          )
          return
        }
        if (!dictionary) {
          val slot = arena.allocate(JAVA_LONG)
          slot.set(JAVA_LONG, 0, index.toLong())
          BuiltinTypes.call(
            VariantType.ARRAY,
            "set",
            ARRAY_SET_HASH,
            container,
            listOf(slot, value),
          )
          return
        }
        val key = arena.allocate(BuiltinTypes.VARIANT_SIZE, 8L)
        BuiltinTypes.initVariantFromAny(key, KEYS[index], arena)
        try {
          val ok = arena.allocate(JAVA_BYTE)
          BuiltinTypes.call(
            VariantType.DICTIONARY,
            "set",
            DICTIONARY_SET_HASH,
            container,
            listOf(key, value),
            ok,
          )
        } finally {
          BuiltinTypes.destroyVariant(key)
        }
      } finally {
        BuiltinTypes.destroyVariant(value)
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
