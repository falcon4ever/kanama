package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.BuiltinTypes
import net.multigesture.kanama.binding.runtime.GodotStrings
import net.multigesture.kanama.ffi.GodotFFI
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_BYTE
import java.lang.foreign.ValueLayout.JAVA_DOUBLE
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG
import java.lang.invoke.MethodHandle
import java.util.concurrent.ConcurrentHashMap

/**
 * C#-style global utility API backed by Godot utility functions.
 */
object GD {

    private const val COMMON_VARARG_HASH: Long = 2648703342L
    private const val STR_HASH: Long = 32569176L
    private const val ERROR_STRING_HASH: Long = 942708242L
    private const val TYPE_STRING_HASH: Long = 942708242L
    private const val RANDI_HASH: Long = 701202648L
    private const val RANDF_HASH: Long = 2086227845L
    private const val RANDI_RANGE_HASH: Long = 3133453818L
    private const val RANDF_RANGE_HASH: Long = 92296394L
    private const val RANDOMIZE_HASH: Long = 1691721052L
    private const val SEED_HASH: Long = 382931173L
    private const val RANDFN_HASH: Long = 92296394L
    private const val TYPEOF_HASH: Long = 326422594L
    private const val HASH_HASH: Long = 326422594L
    private const val IS_INSTANCE_VALID_HASH: Long = 996128841L
    private const val IS_INSTANCE_ID_VALID_HASH: Long = 2232439758L
    private const val ONE_FLOAT_HASH: Long = 2140049587L
    private const val TWO_FLOAT_HASH: Long = 92296394L
    private const val THREE_FLOAT_HASH: Long = 998901048L
    private const val FIVE_FLOAT_HASH: Long = 1090965791L
    private const val TWO_INT_HASH: Long = 3133453818L
    private const val THREE_INT_HASH: Long = 650295447L
    private const val IS_EQUAL_APPROX_HASH: Long = 1400789633L
    private const val IS_ZERO_APPROX_HASH: Long = 3569215213L
    private const val SNAPPEDI_HASH: Long = 3570758393L
    private const val NEAREST_PO2_HASH: Long = 2157319888L

    private val getPtrUtilityFunction = GodotFFI.lookup(
        "variant_get_ptr_utility_function",
        FunctionDescriptor.of(ADDRESS, ADDRESS, JAVA_LONG),
    )

    private val utilityCalls = ConcurrentHashMap<Pair<String, Long>, MethodHandle>()

    private fun utilityCall(name: String, hash: Long): MethodHandle =
        utilityCalls.getOrPut(name to hash) {
            val fn = getPtrUtilityFunction.invoke(GodotStrings.makeStringName(name), hash) as MemorySegment
            check(fn.address() != 0L) {
                "variant_get_ptr_utility_function($name, $hash) returned NULL"
            }
            GodotFFI.linker.downcallHandle(
                fn,
                FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, JAVA_INT),
            )
        }

    private data class VariantScratch(
        val variant: MemorySegment,
        val release: () -> Unit,
    )

    /**
     * Encodes [value] as a real Variant for the utility-call ABI.
     *
     * Delegates to [BuiltinTypes.initVariantFromAny] — the same encoder the ptrcall and
     * `Object.call` paths use — so a [GodotObject] arrives as a `TYPE_OBJECT` variant and
     * every value type keeps its own Variant type instead of collapsing to one of six
     * scalars.
     *
     * There is deliberately no `toString()` fallback here (task 78): encoding an
     * unsupported value as a STRING variant made `isInstanceValid(node)` ask Godot
     * whether a *string* was a live instance, so it answered false for every live
     * object, and `typeOf(node)` reported `TYPE_STRING`. Unsupported types now fail with
     * `Unsupported Variant value type: <class>` instead of silently lying. The
     * text-rendering utilities keep their conversion in [displayArgument].
     */
    private fun encodeVariant(arena: Arena, value: Any?): VariantScratch {
        val variant = arena.allocate(BuiltinTypes.VARIANT_SIZE, 8L)
        BuiltinTypes.initVariantFromAny(variant, value, arena)
        return VariantScratch(variant) { BuiltinTypes.destroyVariant(variant) }
    }

    /**
     * Argument conversion for the `print`/`str` family only.
     *
     * Those utilities exist to render text, so a value Godot has no Variant for is
     * rendered with Kotlin's `toString()` rather than aborting the call. That is the
     * pre-task-78 behaviour, kept here — confined to the text-rendering family — instead
     * of living inside the encoder that every utility shares. The scalar set below is
     * passed through untouched so Godot keeps formatting numbers and booleans itself.
     */
    private fun displayArgument(value: Any?): Any? =
        when (value) {
            null, is String, is Boolean, is Int, is Long, is Float, is Double -> value
            else -> value.toString()
        }

    private fun callVoidVarargUtility(name: String, hash: Long, values: Array<out Any?>) {
        Arena.ofConfined().use { arena ->
            val encoded = values.map { encodeVariant(arena, displayArgument(it)) }
            try {
                val args = if (encoded.isEmpty()) {
                    MemorySegment.NULL
                } else {
                    arena.allocate(ADDRESS, encoded.size.toLong()).also { arr ->
                        encoded.forEachIndexed { index, scratch ->
                            arr.setAtIndex(ADDRESS, index.toLong(), scratch.variant)
                        }
                    }
                }
                utilityCall(name, hash).invoke(MemorySegment.NULL, args, encoded.size)
            } finally {
                encoded.forEach { it.release() }
            }
        }
    }

    private fun callVoidNoArgsUtility(name: String, hash: Long) {
        utilityCall(name, hash).invoke(MemorySegment.NULL, MemorySegment.NULL, 0)
    }

    private fun callVoidOneLongUtility(name: String, hash: Long, value: Long) {
        Arena.ofConfined().use { arena ->
            val arg0 = arena.allocate(JAVA_LONG)
            arg0.set(JAVA_LONG, 0, value)
            val args = arena.allocate(ADDRESS, 1)
            args.setAtIndex(ADDRESS, 0, arg0)
            utilityCall(name, hash).invoke(MemorySegment.NULL, args, 1)
        }
    }

    private fun callStringVarargUtility(name: String, hash: Long, values: Array<out Any?>): String {
        Arena.ofConfined().use { arena ->
            val encoded = values.map { encodeVariant(arena, displayArgument(it)) }
            val ret = arena.allocate(8L, 8L)
            GodotStrings.initString(ret, "")
            try {
                val args = if (encoded.isEmpty()) {
                    MemorySegment.NULL
                } else {
                    arena.allocate(ADDRESS, encoded.size.toLong()).also { arr ->
                        encoded.forEachIndexed { index, scratch ->
                            arr.setAtIndex(ADDRESS, index.toLong(), scratch.variant)
                        }
                    }
                }
                utilityCall(name, hash).invoke(ret, args, encoded.size)
                return GodotStrings.readString(ret)
            } finally {
                encoded.forEach { it.release() }
                GodotStrings.destroyString(ret)
            }
        }
    }

    private fun callStringOneIntUtility(name: String, hash: Long, value: Long): String {
        Arena.ofConfined().use { arena ->
            val arg = arena.allocate(JAVA_LONG)
            arg.set(JAVA_LONG, 0, value)
            val ret = arena.allocate(8L, 8L)
            GodotStrings.initString(ret, "")
            try {
                val args = arena.allocate(ADDRESS, 1)
                args.setAtIndex(ADDRESS, 0, arg)
                utilityCall(name, hash).invoke(ret, args, 1)
                return GodotStrings.readString(ret)
            } finally {
                GodotStrings.destroyString(ret)
            }
        }
    }

    private fun callLongNoArgsUtility(name: String, hash: Long): Long {
        Arena.ofConfined().use { arena ->
            val ret = arena.allocate(JAVA_LONG)
            utilityCall(name, hash).invoke(ret, MemorySegment.NULL, 0)
            return ret.get(JAVA_LONG, 0)
        }
    }

    private fun callLongOneArgUtility(name: String, hash: Long, value: Long): Long {
        Arena.ofConfined().use { arena ->
            val arg0 = arena.allocate(JAVA_LONG)
            arg0.set(JAVA_LONG, 0, value)
            val args = arena.allocate(ADDRESS, 1)
            args.setAtIndex(ADDRESS, 0, arg0)
            val ret = arena.allocate(JAVA_LONG)
            utilityCall(name, hash).invoke(ret, args, 1)
            return ret.get(JAVA_LONG, 0)
        }
    }

    private fun callDoubleNoArgsUtility(name: String, hash: Long): Double {
        Arena.ofConfined().use { arena ->
            val ret = arena.allocate(JAVA_DOUBLE)
            utilityCall(name, hash).invoke(ret, MemorySegment.NULL, 0)
            return ret.get(JAVA_DOUBLE, 0)
        }
    }

    private fun callLongOneDoubleUtility(name: String, hash: Long, value: Double): Long {
        Arena.ofConfined().use { arena ->
            val arg0 = arena.allocate(JAVA_DOUBLE)
            arg0.set(JAVA_DOUBLE, 0, value)
            val args = arena.allocate(ADDRESS, 1)
            args.setAtIndex(ADDRESS, 0, arg0)
            val ret = arena.allocate(JAVA_LONG)
            utilityCall(name, hash).invoke(ret, args, 1)
            return ret.get(JAVA_LONG, 0)
        }
    }

    private fun callDoubleOneArgUtility(name: String, hash: Long, value: Double): Double {
        Arena.ofConfined().use { arena ->
            val arg0 = arena.allocate(JAVA_DOUBLE)
            arg0.set(JAVA_DOUBLE, 0, value)
            val args = arena.allocate(ADDRESS, 1)
            args.setAtIndex(ADDRESS, 0, arg0)
            val ret = arena.allocate(JAVA_DOUBLE)
            utilityCall(name, hash).invoke(ret, args, 1)
            return ret.get(JAVA_DOUBLE, 0)
        }
    }

    private fun callLongRangeUtility(name: String, hash: Long, from: Long, to: Long): Long {
        Arena.ofConfined().use { arena ->
            val arg0 = arena.allocate(JAVA_LONG)
            val arg1 = arena.allocate(JAVA_LONG)
            arg0.set(JAVA_LONG, 0, from)
            arg1.set(JAVA_LONG, 0, to)
            val args = arena.allocate(ADDRESS, 2)
            args.setAtIndex(ADDRESS, 0, arg0)
            args.setAtIndex(ADDRESS, 1, arg1)
            val ret = arena.allocate(JAVA_LONG)
            utilityCall(name, hash).invoke(ret, args, 2)
            return ret.get(JAVA_LONG, 0)
        }
    }

    private fun callDoubleRangeUtility(name: String, hash: Long, from: Double, to: Double): Double {
        Arena.ofConfined().use { arena ->
            val arg0 = arena.allocate(JAVA_DOUBLE)
            val arg1 = arena.allocate(JAVA_DOUBLE)
            arg0.set(JAVA_DOUBLE, 0, from)
            arg1.set(JAVA_DOUBLE, 0, to)
            val args = arena.allocate(ADDRESS, 2)
            args.setAtIndex(ADDRESS, 0, arg0)
            args.setAtIndex(ADDRESS, 1, arg1)
            val ret = arena.allocate(JAVA_DOUBLE)
            utilityCall(name, hash).invoke(ret, args, 2)
            return ret.get(JAVA_DOUBLE, 0)
        }
    }

    private fun callDoubleTwoArgsUtility(name: String, hash: Long, first: Double, second: Double): Double {
        Arena.ofConfined().use { arena ->
            val arg0 = arena.allocate(JAVA_DOUBLE)
            val arg1 = arena.allocate(JAVA_DOUBLE)
            arg0.set(JAVA_DOUBLE, 0, first)
            arg1.set(JAVA_DOUBLE, 0, second)
            val args = arena.allocate(ADDRESS, 2)
            args.setAtIndex(ADDRESS, 0, arg0)
            args.setAtIndex(ADDRESS, 1, arg1)
            val ret = arena.allocate(JAVA_DOUBLE)
            utilityCall(name, hash).invoke(ret, args, 2)
            return ret.get(JAVA_DOUBLE, 0)
        }
    }

    private fun callDoubleThreeArgsUtility(name: String, hash: Long, a: Double, b: Double, c: Double): Double {
        Arena.ofConfined().use { arena ->
            val arg0 = arena.allocate(JAVA_DOUBLE)
            val arg1 = arena.allocate(JAVA_DOUBLE)
            val arg2 = arena.allocate(JAVA_DOUBLE)
            arg0.set(JAVA_DOUBLE, 0, a)
            arg1.set(JAVA_DOUBLE, 0, b)
            arg2.set(JAVA_DOUBLE, 0, c)
            val args = arena.allocate(ADDRESS, 3)
            args.setAtIndex(ADDRESS, 0, arg0)
            args.setAtIndex(ADDRESS, 1, arg1)
            args.setAtIndex(ADDRESS, 2, arg2)
            val ret = arena.allocate(JAVA_DOUBLE)
            utilityCall(name, hash).invoke(ret, args, 3)
            return ret.get(JAVA_DOUBLE, 0)
        }
    }

    private fun callDoubleFiveArgsUtility(
        name: String,
        hash: Long,
        a: Double,
        b: Double,
        c: Double,
        d: Double,
        e: Double,
    ): Double {
        Arena.ofConfined().use { arena ->
            val arg0 = arena.allocate(JAVA_DOUBLE)
            val arg1 = arena.allocate(JAVA_DOUBLE)
            val arg2 = arena.allocate(JAVA_DOUBLE)
            val arg3 = arena.allocate(JAVA_DOUBLE)
            val arg4 = arena.allocate(JAVA_DOUBLE)
            arg0.set(JAVA_DOUBLE, 0, a)
            arg1.set(JAVA_DOUBLE, 0, b)
            arg2.set(JAVA_DOUBLE, 0, c)
            arg3.set(JAVA_DOUBLE, 0, d)
            arg4.set(JAVA_DOUBLE, 0, e)
            val args = arena.allocate(ADDRESS, 5)
            args.setAtIndex(ADDRESS, 0, arg0)
            args.setAtIndex(ADDRESS, 1, arg1)
            args.setAtIndex(ADDRESS, 2, arg2)
            args.setAtIndex(ADDRESS, 3, arg3)
            args.setAtIndex(ADDRESS, 4, arg4)
            val ret = arena.allocate(JAVA_DOUBLE)
            utilityCall(name, hash).invoke(ret, args, 5)
            return ret.get(JAVA_DOUBLE, 0)
        }
    }

    private fun callLongTwoArgsUtility(name: String, hash: Long, first: Long, second: Long): Long {
        Arena.ofConfined().use { arena ->
            val arg0 = arena.allocate(JAVA_LONG)
            val arg1 = arena.allocate(JAVA_LONG)
            arg0.set(JAVA_LONG, 0, first)
            arg1.set(JAVA_LONG, 0, second)
            val args = arena.allocate(ADDRESS, 2)
            args.setAtIndex(ADDRESS, 0, arg0)
            args.setAtIndex(ADDRESS, 1, arg1)
            val ret = arena.allocate(JAVA_LONG)
            utilityCall(name, hash).invoke(ret, args, 2)
            return ret.get(JAVA_LONG, 0)
        }
    }

    private fun callLongThreeArgsUtility(name: String, hash: Long, a: Long, b: Long, c: Long): Long {
        Arena.ofConfined().use { arena ->
            val arg0 = arena.allocate(JAVA_LONG)
            val arg1 = arena.allocate(JAVA_LONG)
            val arg2 = arena.allocate(JAVA_LONG)
            arg0.set(JAVA_LONG, 0, a)
            arg1.set(JAVA_LONG, 0, b)
            arg2.set(JAVA_LONG, 0, c)
            val args = arena.allocate(ADDRESS, 3)
            args.setAtIndex(ADDRESS, 0, arg0)
            args.setAtIndex(ADDRESS, 1, arg1)
            args.setAtIndex(ADDRESS, 2, arg2)
            val ret = arena.allocate(JAVA_LONG)
            utilityCall(name, hash).invoke(ret, args, 3)
            return ret.get(JAVA_LONG, 0)
        }
    }

    private fun callLongDoubleLongUtility(name: String, hash: Long, value: Double, step: Long): Long {
        Arena.ofConfined().use { arena ->
            val arg0 = arena.allocate(JAVA_DOUBLE)
            val arg1 = arena.allocate(JAVA_LONG)
            arg0.set(JAVA_DOUBLE, 0, value)
            arg1.set(JAVA_LONG, 0, step)
            val args = arena.allocate(ADDRESS, 2)
            args.setAtIndex(ADDRESS, 0, arg0)
            args.setAtIndex(ADDRESS, 1, arg1)
            val ret = arena.allocate(JAVA_LONG)
            utilityCall(name, hash).invoke(ret, args, 2)
            return ret.get(JAVA_LONG, 0)
        }
    }

    private fun callBoolOneDoubleUtility(name: String, hash: Long, value: Double): Boolean {
        Arena.ofConfined().use { arena ->
            val arg0 = arena.allocate(JAVA_DOUBLE)
            arg0.set(JAVA_DOUBLE, 0, value)
            val args = arena.allocate(ADDRESS, 1)
            args.setAtIndex(ADDRESS, 0, arg0)
            val ret = arena.allocate(JAVA_BYTE)
            utilityCall(name, hash).invoke(ret, args, 1)
            return ret.get(JAVA_BYTE, 0).toInt() != 0
        }
    }

    private fun callBoolTwoDoubleUtility(name: String, hash: Long, first: Double, second: Double): Boolean {
        Arena.ofConfined().use { arena ->
            val arg0 = arena.allocate(JAVA_DOUBLE)
            val arg1 = arena.allocate(JAVA_DOUBLE)
            arg0.set(JAVA_DOUBLE, 0, first)
            arg1.set(JAVA_DOUBLE, 0, second)
            val args = arena.allocate(ADDRESS, 2)
            args.setAtIndex(ADDRESS, 0, arg0)
            args.setAtIndex(ADDRESS, 1, arg1)
            val ret = arena.allocate(JAVA_BYTE)
            utilityCall(name, hash).invoke(ret, args, 2)
            return ret.get(JAVA_BYTE, 0).toInt() != 0
        }
    }

    private fun callBoolOneLongUtility(name: String, hash: Long, value: Long): Boolean {
        Arena.ofConfined().use { arena ->
            val arg0 = arena.allocate(JAVA_LONG)
            arg0.set(JAVA_LONG, 0, value)
            val args = arena.allocate(ADDRESS, 1)
            args.setAtIndex(ADDRESS, 0, arg0)
            val ret = arena.allocate(JAVA_BYTE)
            utilityCall(name, hash).invoke(ret, args, 1)
            return ret.get(JAVA_BYTE, 0).toInt() != 0
        }
    }

    private fun callLongOneVariantUtility(name: String, hash: Long, value: Any?): Long {
        Arena.ofConfined().use { arena ->
            val encoded = encodeVariant(arena, value)
            val args = arena.allocate(ADDRESS, 1)
            val ret = arena.allocate(JAVA_LONG)
            try {
                args.setAtIndex(ADDRESS, 0, encoded.variant)
                utilityCall(name, hash).invoke(ret, args, 1)
                return ret.get(JAVA_LONG, 0)
            } finally {
                encoded.release()
            }
        }
    }

    private fun callBoolOneVariantUtility(name: String, hash: Long, value: Any?): Boolean {
        Arena.ofConfined().use { arena ->
            val encoded = encodeVariant(arena, value)
            val args = arena.allocate(ADDRESS, 1)
            val ret = arena.allocate(JAVA_BYTE)
            try {
                args.setAtIndex(ADDRESS, 0, encoded.variant)
                utilityCall(name, hash).invoke(ret, args, 1)
                return ret.get(JAVA_BYTE, 0).toInt() != 0
            } finally {
                encoded.release()
            }
        }
    }

    @JvmStatic
    fun print(vararg values: Any?) = callVoidVarargUtility("print", COMMON_VARARG_HASH, values)

    @JvmStatic
    fun printRich(vararg values: Any?) = callVoidVarargUtility("print_rich", COMMON_VARARG_HASH, values)

    @JvmStatic
    fun printErr(vararg values: Any?) = callVoidVarargUtility("printerr", COMMON_VARARG_HASH, values)

    @JvmStatic
    fun printS(vararg values: Any?) = callVoidVarargUtility("prints", COMMON_VARARG_HASH, values)

    @JvmStatic
    fun printT(vararg values: Any?) = callVoidVarargUtility("printt", COMMON_VARARG_HASH, values)

    @JvmStatic
    fun printRaw(vararg values: Any?) = callVoidVarargUtility("printraw", COMMON_VARARG_HASH, values)

    @JvmStatic
    fun printVerbose(vararg values: Any?) = callVoidVarargUtility("print_verbose", COMMON_VARARG_HASH, values)

    @JvmStatic
    fun pushWarning(vararg values: Any?) = callVoidVarargUtility("push_warning", COMMON_VARARG_HASH, values)

    @JvmStatic
    fun pushError(vararg values: Any?) = callVoidVarargUtility("push_error", COMMON_VARARG_HASH, values)

    @JvmStatic
    fun str(vararg values: Any?): String = callStringVarargUtility("str", STR_HASH, values)

    @JvmStatic
    fun typeString(type: Int): String = callStringOneIntUtility("type_string", TYPE_STRING_HASH, type.toLong())

    @JvmStatic
    fun errorString(error: Int): String = callStringOneIntUtility("error_string", ERROR_STRING_HASH, error.toLong())

    /**
     * Returns a random unsigned 32-bit integer from Godot's global random
     * number generator.
     *
     * Generated from Godot docs: @GlobalScope.randi
     */
    @JvmStatic
    fun randi(): Long = callLongNoArgsUtility("randi", RANDI_HASH)

    /**
     * Returns a random floating-point value between `0.0` and `1.0`
     * inclusive from Godot's global random number generator.
     *
     * This is the Kotlin form of GDScript's global `randf()`.
     *
     * Generated from Godot docs: @GlobalScope.randf
     */
    @JvmStatic
    fun randf(): Double = callDoubleNoArgsUtility("randf", RANDF_HASH)

    /**
     * Returns a random signed integer between `from` and `to` inclusive from
     * Godot's global random number generator. Godot swaps the bounds if `to`
     * is less than `from`.
     *
     * This is the Kotlin form of GDScript's global `randi_range(from, to)`.
     *
     * Generated from Godot docs: @GlobalScope.randi_range
     */
    @JvmStatic
    fun randiRange(from: Long, to: Long): Long = callLongRangeUtility("randi_range", RANDI_RANGE_HASH, from, to)

    /**
     * Returns a random floating-point value between `from` and `to` inclusive
     * from Godot's global random number generator.
     *
     * This is the Kotlin form of GDScript's global `randf_range(from, to)`.
     *
     * Generated from Godot docs: @GlobalScope.randf_range
     */
    @JvmStatic
    fun randfRange(from: Double, to: Double): Double = callDoubleRangeUtility("randf_range", RANDF_RANGE_HASH, from, to)

    /**
     * Returns a normally distributed pseudo-random floating-point value using
     * the given `mean` and standard `deviation`.
     *
     * Generated from Godot docs: @GlobalScope.randfn
     */
    @JvmStatic
    fun randfn(mean: Double = 0.0, deviation: Double = 1.0): Double =
        callDoubleTwoArgsUtility("randfn", RANDFN_HASH, mean, deviation)

    /**
     * Randomizes Godot's global random number generator seed. Godot already
     * calls this automatically when a project runs; call [seed] instead when
     * you need reproducible random values.
     *
     * Generated from Godot docs: @GlobalScope.randomize
     */
    @JvmStatic
    fun randomize() = callVoidNoArgsUtility("randomize", RANDOMIZE_HASH)

    /**
     * Sets Godot's global random number generator seed for reproducible
     * sequences from functions such as [randf] and [randi].
     *
     * Generated from Godot docs: @GlobalScope.seed
     */
    @JvmStatic
    fun seed(value: Long) = callVoidOneLongUtility("seed", SEED_HASH, value)

    /**
     * Returns the `Variant.Type` of [value] (`TYPE_OBJECT` = 24 for a wrapper,
     * `TYPE_STRING` = 4 for a `String`, and so on).
     */
    @JvmStatic
    fun typeOf(value: Any?): Long = callLongOneVariantUtility("typeof", TYPEOF_HASH, value)

    /** Returns Godot's Variant hash of [value]. */
    @JvmStatic
    fun hash(value: Any?): Long = callLongOneVariantUtility("hash", HASH_HASH, value)

    /**
     * Returns true when [instance] still refers to a live Godot object.
     *
     * The parameter is a typed wrapper rather than `Any?` on purpose: Godot answers
     * false for every non-object Variant, so an id or a string would compile and then
     * always return false (task 78). Use [isInstanceIdValid] to check a raw instance id.
     *
     * Note that building the OBJECT variant reads the object's header, so this asks the
     * question through the wrapper's raw pointer. GDScript's `is_instance_valid` reads
     * the instance id its Variant already cached instead. If you are holding a wrapper
     * across a `free()` you can capture `getInstanceId()` while it is alive and use
     * [isInstanceIdValid], which never touches the object.
     */
    @JvmStatic
    fun isInstanceValid(instance: GodotObject?): Boolean =
        callBoolOneVariantUtility("is_instance_valid", IS_INSTANCE_VALID_HASH, instance)

    /**
     * Returns true when [id] — an id from `GodotObject.getInstanceId()` — still resolves
     * to a live Godot object. This is the id-shaped counterpart of [isInstanceValid];
     * unlike that call it does not dereference the object, so it stays valid to ask
     * after the object has been freed.
     */
    @JvmStatic
    fun isInstanceIdValid(id: Long): Boolean =
        callBoolOneLongUtility("is_instance_id_valid", IS_INSTANCE_ID_VALID_HASH, id)

    @JvmStatic
    fun degToRad(value: Double): Double = callDoubleOneArgUtility("deg_to_rad", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun radToDeg(value: Double): Double = callDoubleOneArgUtility("rad_to_deg", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun lerpf(from: Double, to: Double, weight: Double): Double =
        callDoubleThreeArgsUtility("lerpf", THREE_FLOAT_HASH, from, to, weight)

    @JvmStatic
    fun inverseLerp(from: Double, to: Double, weight: Double): Double =
        callDoubleThreeArgsUtility("inverse_lerp", THREE_FLOAT_HASH, from, to, weight)

    @JvmStatic
    fun lerpAngle(from: Double, to: Double, weight: Double): Double =
        callDoubleThreeArgsUtility("lerp_angle", THREE_FLOAT_HASH, from, to, weight)

    @JvmStatic
    fun moveToward(from: Double, to: Double, delta: Double): Double =
        callDoubleThreeArgsUtility("move_toward", THREE_FLOAT_HASH, from, to, delta)

    @JvmStatic
    fun smoothstep(from: Double, to: Double, x: Double): Double =
        callDoubleThreeArgsUtility("smoothstep", THREE_FLOAT_HASH, from, to, x)

    @JvmStatic
    fun ease(x: Double, curve: Double): Double = callDoubleTwoArgsUtility("ease", TWO_FLOAT_HASH, x, curve)

    @JvmStatic
    fun remap(value: Double, istart: Double, istop: Double, ostart: Double, ostop: Double): Double =
        callDoubleFiveArgsUtility("remap", FIVE_FLOAT_HASH, value, istart, istop, ostart, ostop)

    @JvmStatic
    fun clampf(value: Double, min: Double, max: Double): Double =
        callDoubleThreeArgsUtility("clampf", THREE_FLOAT_HASH, value, min, max)

    @JvmStatic
    fun clampi(value: Long, min: Long, max: Long): Long =
        callLongThreeArgsUtility("clampi", THREE_INT_HASH, value, min, max)

    @JvmStatic
    fun minf(a: Double, b: Double): Double = callDoubleTwoArgsUtility("minf", TWO_FLOAT_HASH, a, b)

    @JvmStatic
    fun maxf(a: Double, b: Double): Double = callDoubleTwoArgsUtility("maxf", TWO_FLOAT_HASH, a, b)

    @JvmStatic
    fun mini(a: Long, b: Long): Long = callLongTwoArgsUtility("mini", TWO_INT_HASH, a, b)

    @JvmStatic
    fun maxi(a: Long, b: Long): Long = callLongTwoArgsUtility("maxi", TWO_INT_HASH, a, b)

    @JvmStatic
    fun snappedf(value: Double, step: Double): Double =
        callDoubleTwoArgsUtility("snappedf", TWO_FLOAT_HASH, value, step)

    @JvmStatic
    fun snappedi(value: Double, step: Long): Long =
        callLongDoubleLongUtility("snappedi", SNAPPEDI_HASH, value, step)

    @JvmStatic
    fun wrapf(value: Double, min: Double, max: Double): Double =
        callDoubleThreeArgsUtility("wrapf", THREE_FLOAT_HASH, value, min, max)

    @JvmStatic
    fun wrapi(value: Long, min: Long, max: Long): Long =
        callLongThreeArgsUtility("wrapi", THREE_INT_HASH, value, min, max)

    @JvmStatic
    fun isEqualApprox(a: Double, b: Double): Boolean =
        callBoolTwoDoubleUtility("is_equal_approx", IS_EQUAL_APPROX_HASH, a, b)

    @JvmStatic
    fun isZeroApprox(value: Double): Boolean =
        callBoolOneDoubleUtility("is_zero_approx", IS_ZERO_APPROX_HASH, value)

    @JvmStatic
    fun isFinite(value: Double): Boolean =
        callBoolOneDoubleUtility("is_finite", IS_ZERO_APPROX_HASH, value)

    @JvmStatic
    fun isNaN(value: Double): Boolean =
        callBoolOneDoubleUtility("is_nan", IS_ZERO_APPROX_HASH, value)

    @JvmStatic
    fun isInf(value: Double): Boolean =
        callBoolOneDoubleUtility("is_inf", IS_ZERO_APPROX_HASH, value)

    @JvmStatic
    fun sin(value: Double): Double = callDoubleOneArgUtility("sin", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun cos(value: Double): Double = callDoubleOneArgUtility("cos", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun tan(value: Double): Double = callDoubleOneArgUtility("tan", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun asin(value: Double): Double = callDoubleOneArgUtility("asin", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun acos(value: Double): Double = callDoubleOneArgUtility("acos", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun atan(value: Double): Double = callDoubleOneArgUtility("atan", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun atan2(y: Double, x: Double): Double = callDoubleTwoArgsUtility("atan2", TWO_FLOAT_HASH, y, x)

    @JvmStatic
    fun sqrt(value: Double): Double = callDoubleOneArgUtility("sqrt", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun pow(x: Double, y: Double): Double = callDoubleTwoArgsUtility("pow", TWO_FLOAT_HASH, x, y)

    @JvmStatic
    fun fmod(x: Double, y: Double): Double = callDoubleTwoArgsUtility("fmod", TWO_FLOAT_HASH, x, y)

    @JvmStatic
    fun fposmod(x: Double, y: Double): Double = callDoubleTwoArgsUtility("fposmod", TWO_FLOAT_HASH, x, y)

    @JvmStatic
    fun posmod(x: Long, y: Long): Long = callLongTwoArgsUtility("posmod", TWO_INT_HASH, x, y)

    @JvmStatic
    fun log(value: Double): Double = callDoubleOneArgUtility("log", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun exp(value: Double): Double = callDoubleOneArgUtility("exp", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun floorf(value: Double): Double = callDoubleOneArgUtility("floorf", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun floori(value: Double): Long = callLongOneDoubleUtility("floori", 2780425386L, value)

    @JvmStatic
    fun ceilf(value: Double): Double = callDoubleOneArgUtility("ceilf", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun ceili(value: Double): Long = callLongOneDoubleUtility("ceili", 2780425386L, value)

    @JvmStatic
    fun roundf(value: Double): Double = callDoubleOneArgUtility("roundf", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun roundi(value: Double): Long = callLongOneDoubleUtility("roundi", 2780425386L, value)

    @JvmStatic
    fun absf(value: Double): Double = callDoubleOneArgUtility("absf", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun absi(value: Long): Long = callLongOneArgUtility("absi", NEAREST_PO2_HASH, value)

    @JvmStatic
    fun signf(value: Double): Double = callDoubleOneArgUtility("signf", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun signi(value: Long): Long = callLongOneArgUtility("signi", NEAREST_PO2_HASH, value)

    @JvmStatic
    fun dbToLinear(value: Double): Double = callDoubleOneArgUtility("db_to_linear", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun linearToDb(value: Double): Double = callDoubleOneArgUtility("linear_to_db", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun nearestPo2(value: Long): Long = callLongOneArgUtility("nearest_po2", NEAREST_PO2_HASH, value)

    @JvmStatic
    fun pingpong(value: Double, length: Double): Double =
        callDoubleTwoArgsUtility("pingpong", TWO_FLOAT_HASH, value, length)

    @JvmStatic
    fun sinh(value: Double): Double = callDoubleOneArgUtility("sinh", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun cosh(value: Double): Double = callDoubleOneArgUtility("cosh", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun tanh(value: Double): Double = callDoubleOneArgUtility("tanh", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun asinh(value: Double): Double = callDoubleOneArgUtility("asinh", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun acosh(value: Double): Double = callDoubleOneArgUtility("acosh", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun atanh(value: Double): Double = callDoubleOneArgUtility("atanh", ONE_FLOAT_HASH, value)

    @JvmStatic
    fun rotateToward(from: Double, to: Double, delta: Double): Double =
        callDoubleThreeArgsUtility("rotate_toward", THREE_FLOAT_HASH, from, to, delta)

}
