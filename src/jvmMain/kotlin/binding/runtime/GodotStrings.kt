package net.multigesture.kanama.binding.runtime

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG
import java.lang.invoke.MethodHandle
import net.multigesture.kanama.ffi.GodotFFI

/**
 * StringName / String construction — the two opaque 8-byte handle types we have to hand to almost
 * every ClassDB call.
 *
 * Both types allocate their storage in [GodotFFI.arena] so it lives as long as the JVM. StringName
 * values are cached by text because hot paths such as Object.set("parameters/...", value) can
 * otherwise allocate the same native wrapper every frame.
 */
object GodotStrings {

  /**
   * StringName storage is a single `_Data *` on 64-bit builds. Same text → same pointer (interned),
   * which we exploit for the get_virtual hot path (compare the long, not the text).
   */
  private const val STRING_NAME_SIZE = 8L

  /** String storage is an opaque `CowData<char32_t> *`. Also 8 bytes. */
  private const val STRING_SIZE = 8L

  // The per-call String handles as JVM constants (task 131 item 16): `@JvmField` in an `object` is
  // a `static final` field the JIT folds, called through `invokeExact` on its exact type (no
  // generic invoker or `asType` per String argument or return).
  // Each holder resolves its own entry point on first use (after `GodotFFI.bootstrap`), so one
  // missing entry point does not take the others down. Unlike `by lazy`, a failed resolution is not
  // retried: the first use throws `ExceptionInInitializerError` (its cause is the lookup's error)
  // and every later use `NoClassDefFoundError`. Kanama's containment catches `Throwable`, and the
  // script error report names the cause (`ScriptErrors.reportFor`); see ObjectCalls.

  /** `string_name_new_with_utf8_chars`: `(MemorySegment, MemorySegment)V`. */
  private object StringNameNew {
    @JvmField
    val HANDLE: MethodHandle =
      GodotFFI.lookup(
        "string_name_new_with_utf8_chars",
        FunctionDescriptor.ofVoid(ADDRESS, ADDRESS),
      )
  }

  /** `string_new_with_utf8_chars`: `(MemorySegment, MemorySegment)V`. */
  private object StringNew {
    @JvmField
    val HANDLE: MethodHandle =
      GodotFFI.lookup("string_new_with_utf8_chars", FunctionDescriptor.ofVoid(ADDRESS, ADDRESS))
  }

  /** `string_to_utf8_chars`: `(MemorySegment, MemorySegment, J)J`. */
  private object StringToUtf8 {
    @JvmField
    val HANDLE: MethodHandle =
      GodotFFI.lookup(
        "string_to_utf8_chars",
        FunctionDescriptor.of(JAVA_LONG, ADDRESS, ADDRESS, JAVA_LONG),
      )
  }

  /** The String ptr destructor from `variant_get_ptr_destructor(STRING)`: `(MemorySegment)V`. */
  private object StringDestructor {
    @JvmField val HANDLE: MethodHandle = ptrDestructor(VariantType.STRING, "string_destructor")
  }

  // Block-bodied, so each invokeExact is a statement and compiles to the handle's exact `...V`
  // type (as the last expression of a lambda it would be typed `Object` and fail at run time).
  private fun newStringName(dest: MemorySegment, utf8: MemorySegment) {
    StringNameNew.HANDLE.invokeExact(dest, utf8)
  }

  private fun newString(dest: MemorySegment, utf8: MemorySegment) {
    StringNew.HANDLE.invokeExact(dest, utf8)
  }

  private val ownedStringNames = LinkedHashMap<String, MemorySegment>()

  fun makeStringName(value: String): MemorySegment {
    return synchronized(ownedStringNames) {
      ownedStringNames[value]
        ?: run {
          val storage = GodotFFI.arena.allocate(STRING_NAME_SIZE, 8)
          val cString = GodotFFI.arena.allocateFrom(value)
          newStringName(storage, cString)
          ownedStringNames[value] = storage
          storage
        }
    }
  }

  fun makeString(value: String): MemorySegment {
    val storage = GodotFFI.arena.allocate(STRING_SIZE, 8)
    val cString = GodotFFI.arena.allocateFrom(value)
    newString(storage, cString)
    return storage
  }

  /**
   * Read the interned `_Data*` out of a StringName's storage so it can be compared against another
   * StringName as a plain long.
   */
  fun stringNameStorage(name: MemorySegment): Long = name.get(JAVA_LONG, 0)

  /** Convenience: resolve text → interned long in one step. */
  fun stringNameStorage(value: String): Long = stringNameStorage(makeStringName(value))

  val emptyString: MemorySegment by lazy { makeString("") }

  val emptyStringName: MemorySegment by lazy { makeStringName("") }

  // ---- Transient String helpers (caller-managed lifetime) ----

  /**
   * `variant_get_ptr_destructor(type_id)` → returns a `void(*)(void*)` destructor for the given
   * variant type. We call it once for STRING (id=4) and cache the handle.
   */
  private val getPtrDestructor by lazy {
    GodotFFI.lookup("variant_get_ptr_destructor", FunctionDescriptor.of(ADDRESS, JAVA_INT))
  }

  private fun ptrDestructor(type: VariantType, label: String): MethodHandle {
    val fn = getPtrDestructor.invoke(type.id) as MemorySegment
    check(fn.address() != 0L) { "variant_get_ptr_destructor(${type.name}) returned NULL" }
    return GodotFFI.downcallHandle(fn, FunctionDescriptor.ofVoid(ADDRESS), label)
  }

  // Teardown only (destroyOwnedStringNames), so it stays lazy.
  private val stringNameDestructor: MethodHandle by lazy {
    ptrDestructor(VariantType.STRING_NAME, "string_name_destructor")
  }

  /**
   * Initialize a Godot StringName in [dest] (caller-allocated, 8 bytes) from a JVM String. Used
   * when Godot provides the storage (e.g. ptrcall returns) and we write into it rather than
   * allocating ourselves.
   */
  fun initStringName(dest: MemorySegment, value: String) {
    Arena.ofConfined().use { arena -> newStringName(dest, arena.allocateFrom(value)) }
  }

  /**
   * Initialize a Godot String in [dest] (caller-allocated, 8 bytes) from a JVM String. Uses a
   * confined arena internally for the UTF-8 bytes. The caller is responsible for eventually calling
   * [destroyString].
   *
   * For ptrcall returns, the caller (Godot) owns the String and will destroy it; do NOT call
   * [destroyString] in that case.
   */
  fun initString(dest: MemorySegment, value: String) {
    Arena.ofConfined().use { arena -> newString(dest, arena.allocateFrom(value)) }
  }

  /**
   * Read a Godot String (at [strPtr]) as a JVM String. Does not take ownership — the caller must
   * [destroyString] if it owns the String (variant-to-type scratch path), or skip it if the String
   * is borrowed (ptrcall const arg).
   */
  fun readString(strPtr: MemorySegment): String {
    val length = StringToUtf8.HANDLE.invokeExact(strPtr, MemorySegment.NULL, 0L) as Long
    if (length <= 0L) return ""
    Arena.ofConfined().use { arena ->
      val buf = arena.allocate(length + 1)
      StringToUtf8.HANDLE.invokeExact(strPtr, buf, length + 1) as Long
      return buf.reinterpret(length + 1).getString(0, Charsets.UTF_8)
    }
  }

  /**
   * Read a Godot StringName as a JVM String by calling StringName.substr(0, -1).
   *
   * The GDExtension interface exposes constructors/destructors for StringName but no direct
   * StringName-to-UTF8 function, so this uses the builtin API to materialize a transient String.
   */
  fun readStringName(namePtr: MemorySegment): String {
    Arena.ofConfined().use { arena ->
      val from = arena.allocate(JAVA_LONG)
      val len = arena.allocate(JAVA_LONG)
      val retString = arena.allocate(8L, 8L)
      from.set(JAVA_LONG, 0, 0L)
      len.set(JAVA_LONG, 0, -1L)
      return try {
        BuiltinTypes.call(
          type = VariantType.STRING_NAME,
          method = "substr",
          hash = 787537301L,
          base = namePtr,
          args = listOf(from, len),
          rReturn = retString,
        )
        readString(retString)
      } finally {
        destroyString(retString)
      }
    }
  }

  /** Release a Godot String's internal buffer. Call after [initString] or variantToType(STRING). */
  fun destroyString(strPtr: MemorySegment) {
    StringDestructor.HANDLE.invokeExact(strPtr)
  }

  /** Release all StringName storage owned by this runtime. */
  fun destroyOwnedStringNames() {
    val snapshot =
      synchronized(ownedStringNames) {
        ownedStringNames.values.toList().also { ownedStringNames.clear() }
      }
    if (snapshot.isEmpty()) return
    var destroyed = 0
    snapshot.forEach { ptr ->
      try {
        stringNameDestructor.invoke(ptr)
        destroyed++
      } catch (_: Throwable) {
        // Best-effort teardown.
      }
    }
    System.err.println(
      "[kanama:kt] GodotStrings.destroyOwnedStringNames destroyed=$destroyed total=${snapshot.size}"
    )
  }
}
