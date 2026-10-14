"""Which of Godot's utility functions the Web build runs in Kotlin (task 129 E review B1).

The shared generated `GD` (src/commonMain/.../api/GD.kt) reaches Web through `BuiltinFrame.callUtility`
(float / int / bool utilities) and `UtilityCalls.call` (the Variant forms). Before either crosses to
the engine (10-30 us a crossing), it asks the generated `WebLocalUtilities` (rendered by
`render_web_local_utilities` into the Web wrapper tree, drift-checked by generate_web_wrappers.py
--check) by an id the generator assigns. Every utility function in extension_api.json is in exactly
ONE of the tables below, so a new Godot utility is a reviewed row, never a silent crossing:

* `WEB_LOCAL_UTILITIES`: a port of Godot 4.7.2's formula (core/variant/variant_utility.cpp ->
  core/math/math_funcs.h), with ONE ulp bound per row. 0 is bit-exact and is only allowed for a
  body with no libm transcendental (`check_web_local_utilities`); a transcendental row carries the
  libm reason (the WEB_LOCAL_FACADE precedent, scripts/generate_builtin_ops.py). An optional guard
  sends the inputs a port does not cover (a NaN or out-of-range float->int, posmod by zero) to the
  engine, so its error and its answer stay Godot's. The web3d smoke's GDScript twin
  (`gd_twin.gd` / BundleProbe.gdRows) compares the local results with the engine's within the bound.
* `WEB_LOCAL_VARIANT_UTILITIES`: the Variant forms (`abs(x)`, `max(a, b, ...)`): local when every
  argument is a Kotlin Double / Float (or Long / Int where Godot's int form is listed); vectors and
  mixed types cross.
* `WEB_ENGINE_UTILITIES`: the engine runs it (name -> why).

RNG is one row away either way: the `rand*` / `seed` / `randomize` rows sit in WEB_ENGINE_UTILITIES
until the owner picks the engine RNG or a bit-exact RandomPCG port; the port would move them to
WEB_LOCAL_UTILITIES with bodies calling it.
"""

from __future__ import annotations

import json
import re
from pathlib import Path

LIBM = "libm's last bit differs by platform and from Kotlin/Wasm's kotlin.math (the engine on Web is emscripten's musl)"

# name -> (ulp bound, reason, Kotlin expression over a1..aN (Double / Long / Boolean as declared),
#          guard over a1..aN or "" (false: the engine runs it)).
# A float->int row ("floori") states its body as the Double Godot converts; the generator adds
# the int64 range guard (C++'s int64_t(double) is undefined outside it; NaN included).
WEB_LOCAL_UTILITIES: dict[str, tuple[int, str, str, str]] = {
    # Bit-exact: IEEE arithmetic, sqrt, fmod and comparisons only.
    "absf": (0, "", "kotlin.math.abs(a1)", ""),
    "absi": (0, "", "kotlin.math.abs(a1)", ""),
    "signf": (0, "", "Mathf.sign(a1)", ""),
    "signi": (0, "", "Mathf.sign(a1)", ""),
    "clampf": (0, "", "Mathf.clamp(a1, a2, a3)", ""),
    "clampi": (0, "", "Mathf.clamp(a1, a2, a3)", ""),
    "minf": (0, "", "Mathf.min(a1, a2)", ""),
    "mini": (0, "", "Mathf.min(a1, a2)", ""),
    "maxf": (0, "", "Mathf.max(a1, a2)", ""),
    "maxi": (0, "", "Mathf.max(a1, a2)", ""),
    "lerpf": (0, "", "Mathf.lerp(a1, a2, a3)", ""),
    "inverse_lerp": (0, "", "Mathf.inverseLerp(a1, a2, a3)", ""),
    "remap": (0, "", "Mathf.remap(a1, a2, a3, a4, a5)", ""),
    "deg_to_rad": (0, "", "Mathf.degToRad(a1)", ""),
    "rad_to_deg": (0, "", "Mathf.radToDeg(a1)", ""),
    "smoothstep": (0, "", "Mathf.smoothStep(a1, a2, a3)", ""),
    "move_toward": (0, "", "Mathf.moveToward(a1, a2, a3)", ""),
    "rotate_toward": (0, "", "Mathf.rotateToward(a1, a2, a3)", ""),
    "angle_difference": (0, "", "angleDifference(a1, a2)", ""),
    "lerp_angle": (0, "", "Mathf.lerpAngle(a1, a2, a3)", ""),
    "snappedf": (0, "", "Mathf.snapped(a1, a2)", ""),
    "wrapf": (0, "", "Mathf.wrap(a1, a2, a3)", ""),
    "wrapi": (0, "", "Mathf.wrap(a1, a2, a3)", ""),
    "fmod": (0, "", "a1 % a2", ""),
    "fposmod": (0, "", "Mathf.fposmod(a1, a2)", ""),
    # Mathf.posmod sends a zero divisor here: the engine reports it and answers 0.
    "posmod": (0, "", "Mathf.posmod(a1, a2)", "a2 != 0L"),
    "pingpong": (0, "", "Mathf.pingPong(a1, a2)", ""),
    "nearest_po2": (0, "", "Mathf.nearestPo2(a1)", ""),
    "floorf": (0, "", "kotlin.math.floor(a1)", ""),
    "ceilf": (0, "", "kotlin.math.ceil(a1)", ""),
    "roundf": (0, "", "Mathf.round(a1)", ""),
    "floori": (0, "", "kotlin.math.floor(a1)", ""),
    "ceili": (0, "", "kotlin.math.ceil(a1)", ""),
    "roundi": (0, "", "Mathf.round(a1)", ""),
    "snappedi": (0, "", "Mathf.snapped(a1, a2.toDouble())", ""),
    "sqrt": (0, "", "kotlin.math.sqrt(a1)", ""),
    "is_nan": (0, "", "a1.isNaN()", ""),
    "is_inf": (0, "", "a1.isInfinite()", ""),
    "is_finite": (0, "", "a1.isFinite()", ""),
    "is_equal_approx": (0, "", "Mathf.isEqualApprox(a1, a2)", ""),
    "is_zero_approx": (0, "", "Mathf.isZeroApprox(a1)", ""),
    "cubic_interpolate": (0, "", "cubicInterpolate(a1, a2, a3, a4, a5)", ""),
    "cubic_interpolate_angle": (0, "", "cubicInterpolateAngle(a1, a2, a3, a4, a5)", ""),
    "cubic_interpolate_in_time": (0, "", "cubicInterpolateInTime(a1, a2, a3, a4, a5, a6, a7, a8)", ""),
    "cubic_interpolate_angle_in_time": (0, "", "cubicInterpolateAngleInTime(a1, a2, a3, a4, a5, a6, a7, a8)", ""),
    "bezier_interpolate": (0, "", "bezierInterpolate(a1, a2, a3, a4, a5)", ""),
    "bezier_derivative": (0, "", "bezierDerivative(a1, a2, a3, a4, a5)", ""),
    # libm transcendentals: Kotlin/Wasm's kotlin.math against the engine's libm.
    "sin": (1, "sin: " + LIBM, "kotlin.math.sin(a1)", ""),
    "cos": (1, "cos: " + LIBM, "kotlin.math.cos(a1)", ""),
    "tan": (1, "tan: " + LIBM, "kotlin.math.tan(a1)", ""),
    "asin": (1, "asin: " + LIBM, "kotlin.math.asin(a1)", ""),
    "acos": (1, "acos: " + LIBM, "kotlin.math.acos(a1)", ""),
    "atan": (1, "atan: " + LIBM, "kotlin.math.atan(a1)", ""),
    "atan2": (1, "atan2: " + LIBM, "kotlin.math.atan2(a1, a2)", ""),
    "sinh": (1, "sinh: " + LIBM, "kotlin.math.sinh(a1)", ""),
    "cosh": (1, "cosh: " + LIBM, "kotlin.math.cosh(a1)", ""),
    "tanh": (1, "tanh: " + LIBM, "kotlin.math.tanh(a1)", ""),
    "asinh": (1, "asinh: " + LIBM, "kotlin.math.asinh(a1)", ""),
    "acosh": (1, "acosh: " + LIBM, "kotlin.math.acosh(a1)", ""),
    "atanh": (1, "atanh: " + LIBM, "kotlin.math.atanh(a1)", ""),
    "pow": (1, "pow: " + LIBM, "a1.pow(a2)", ""),
    "log": (1, "log: " + LIBM, "kotlin.math.ln(a1)", ""),
    "exp": (1, "exp: " + LIBM, "kotlin.math.exp(a1)", ""),
    "ease": (1, "pow: " + LIBM + "; on the twin's inputs the result stays within 1 ulp", "ease(a1, a2)", ""),
    "db_to_linear": (1, "exp: " + LIBM, "kotlin.math.exp(a1 * 0.11512925464970228420089957273422)", ""),
    "linear_to_db": (1, "log: " + LIBM, "kotlin.math.ln(a1) * 8.6858896380650365530225783783321", ""),
}

# The float->int rows: Godot converts the Double body with int64_t(...).
WEB_LOCAL_TO_INT64 = frozenset({"floori", "ceili", "roundi", "snappedi"})

# Variant forms: name -> (body over `d: DoubleArray` or None, body over `l: LongArray` or None,
# argument count or None for Godot's vararg max / min). The int body runs only when every argument
# is a Long / Int, the float one only when every argument is a Double / Float.
WEB_LOCAL_VARIANT_UTILITIES: dict[str, tuple[str | None, str | None, int | None]] = {
    "abs": ("kotlin.math.abs(d[0])", "kotlin.math.abs(l[0])", 1),
    "sign": ("Mathf.sign(d[0])", "Mathf.sign(l[0])", 1),
    "floor": ("kotlin.math.floor(d[0])", None, 1),
    "ceil": ("kotlin.math.ceil(d[0])", None, 1),
    "round": ("Mathf.round(d[0])", None, 1),
    # Godot's Variant clamp tests `value < min` then `value > max` on the result (min > max answers
    # max), unlike clampf's CLAMP macro.
    "clamp": ("clampVariant(d[0], d[1], d[2])", "clampVariant(l[0], l[1], l[2])", 3),
    # The Variant max / min keep the first of equal or unordered (NaN) arguments (OP_LESS /
    # OP_GREATER against the running result), unlike maxf / minf.
    "max": ("maxVariant(d)", "maxVariant(l)", None),
    "min": ("minVariant(d)", "minVariant(l)", None),
    "lerp": ("Mathf.lerp(d[0], d[1], d[2])", None, 3),
    "snapped": ("Mathf.snapped(d[0], d[1])", None, 2),
    "wrap": ("Mathf.wrap(d[0], d[1], d[2])", None, 3),
}

# The text utilities Kotlin prints itself when no proxy is installed yet (review N9: a GD.print
# before any script is ready must not throw): name -> separator between the values.
WEB_TEXT_FALLBACK: dict[str, str] = {
    "print": "", "print_rich": "", "print_verbose": "", "printerr": "", "printraw": "", "prints": " ",
    "printt": "\\t", "push_error": "", "push_warning": "",
}

RNG_PENDING = "RNG: the engine's RandomPCG until the owner picks the engine RNG or a bit-exact Kotlin port"
WEB_ENGINE_UTILITIES: dict[str, str] = {
    **{name: "text output: the engine's log, console and debugger" for name in (
        "print", "print_rich", "print_verbose", "printerr", "printraw", "prints", "printt", "push_error",
        "push_warning")},
    **{name: "Variant text and serialization: the engine's formatter" for name in (
        "str", "var_to_str", "str_to_var", "var_to_bytes", "var_to_bytes_with_objects", "bytes_to_var",
        "bytes_to_var_with_objects", "error_string", "type_string")},
    **{name: "Variant type machinery" for name in ("typeof", "type_convert", "hash", "is_same")},
    **{name: "object identity and liveness: the engine's ObjectDB" for name in (
        "weakref", "instance_from_id", "is_instance_id_valid", "is_instance_valid")},
    **{name: "RID allocation: the engine's counter" for name in ("rid_allocate_id", "rid_from_int64")},
    "step_decimals": "an editor helper with a 32-bit int conversion; not a per-frame call",
    **{name: RNG_PENDING for name in (
        "randf", "randi", "randf_range", "randi_range", "randfn", "randomize", "seed", "rand_from_seed")},
}

TRANSCENDENTAL = re.compile(r"\b(sin|cos|tan|asin|acos|atan|atan2|sinh|cosh|tanh|asinh|acosh|atanh|exp|ln|log|pow|ease)\(")

_KOTLIN = {"float": "Double", "int": "Long", "bool": "Boolean"}
_READ = {"float": "f.slots[{i}][0]", "int": "f.longs[{i}]", "bool": "f.slots[{i}][0] != 0.0"}
_STORE = {
    "float": "f.ret[0] = {v}\n        f.retType = 3",
    "int": "f.retLongValue = {v}\n        f.retType = 2",
    "bool": "f.ret[0] = if ({v}) 1.0 else 0.0\n        f.retType = 1",
}

# Ports of Godot's core/math/math_funcs.h helpers the bodies call, in Godot's operation order.
HELPERS = """\
  private const val TAU = Mathf.TAU

  /** Godot's C++ int64_t(double) is defined for these (NaN fails both tests). */
  private const val INT64_LOW = -9.223372036854775808E18
  private const val INT64_HIGH = 9.223372036854775808E18

  private fun angleDifference(from: Double, to: Double): Double {
    val difference = (to - from) % TAU
    return (2.0 * difference) % TAU - difference
  }

  private fun cubicInterpolate(from: Double, to: Double, pre: Double, post: Double, weight: Double): Double =
    0.5 *
      ((from * 2.0) +
        (-pre + to) * weight +
        (2.0 * pre - 5.0 * from + 4.0 * to - post) * (weight * weight) +
        (-pre + 3.0 * from - 3.0 * to + post) * (weight * weight * weight))

  private fun cubicInterpolateAngle(
    from: Double,
    to: Double,
    pre: Double,
    post: Double,
    weight: Double,
  ): Double {
    val fromRot = from % TAU
    val preDiff = (pre - fromRot) % TAU
    val preRot = fromRot + (2.0 * preDiff) % TAU - preDiff
    val toDiff = (to - fromRot) % TAU
    val toRot = fromRot + (2.0 * toDiff) % TAU - toDiff
    val postDiff = (post - toRot) % TAU
    val postRot = toRot + (2.0 * postDiff) % TAU - postDiff
    return cubicInterpolate(fromRot, toRot, preRot, postRot, weight)
  }

  // Barry-Goldman, as Godot's cubic_interpolate_in_time.
  private fun cubicInterpolateInTime(
    from: Double,
    to: Double,
    pre: Double,
    post: Double,
    weight: Double,
    toT: Double,
    preT: Double,
    postT: Double,
  ): Double {
    val t = Mathf.lerp(0.0, toT, weight)
    val a1 = Mathf.lerp(pre, from, if (preT == 0.0) 0.0 else (t - preT) / -preT)
    val a2 = Mathf.lerp(from, to, if (toT == 0.0) 0.5 else t / toT)
    val a3 = Mathf.lerp(to, post, if (postT - toT == 0.0) 1.0 else (t - toT) / (postT - toT))
    val b1 = Mathf.lerp(a1, a2, if (toT - preT == 0.0) 0.0 else (t - preT) / (toT - preT))
    val b2 = Mathf.lerp(a2, a3, if (postT == 0.0) 1.0 else t / postT)
    return Mathf.lerp(b1, b2, if (toT == 0.0) 0.5 else t / toT)
  }

  private fun cubicInterpolateAngleInTime(
    from: Double,
    to: Double,
    pre: Double,
    post: Double,
    weight: Double,
    toT: Double,
    preT: Double,
    postT: Double,
  ): Double {
    val fromRot = from % TAU
    val preDiff = (pre - fromRot) % TAU
    val preRot = fromRot + (2.0 * preDiff) % TAU - preDiff
    val toDiff = (to - fromRot) % TAU
    val toRot = fromRot + (2.0 * toDiff) % TAU - toDiff
    val postDiff = (post - toRot) % TAU
    val postRot = toRot + (2.0 * postDiff) % TAU - postDiff
    return cubicInterpolateInTime(fromRot, toRot, preRot, postRot, weight, toT, preT, postT)
  }

  private fun bezierInterpolate(start: Double, control1: Double, control2: Double, end: Double, t: Double): Double {
    val omt = 1.0 - t
    val omt2 = omt * omt
    val omt3 = omt2 * omt
    val t2 = t * t
    val t3 = t2 * t
    return start * omt3 + control1 * omt2 * t * 3.0 + control2 * omt * t2 * 3.0 + end * t3
  }

  private fun bezierDerivative(start: Double, control1: Double, control2: Double, end: Double, t: Double): Double {
    val omt = 1.0 - t
    val omt2 = omt * omt
    val t2 = t * t
    return (control1 - start) * 3.0 * omt2 + (control2 - control1) * 6.0 * omt * t + (end - control2) * 3.0 * t2
  }

  // Godot's Math::ease (core/math/math_funcs.cpp).
  private fun ease(x: Double, curve: Double): Double {
    val clamped = if (x < 0.0) 0.0 else if (x > 1.0) 1.0 else x
    return if (curve > 0.0) {
      if (curve < 1.0) 1.0 - (1.0 - clamped).pow(1.0 / curve) else clamped.pow(curve)
    } else if (curve < 0.0) {
      if (clamped < 0.5) {
        (clamped * 2.0).pow(-curve) * 0.5
      } else {
        (1.0 - (1.0 - (clamped - 0.5) * 2.0).pow(-curve)) * 0.5 + 0.5
      }
    } else {
      0.0
    }
  }

  private fun clampVariant(x: Double, min: Double, max: Double): Double {
    var value = x
    if (value < min) value = min
    if (value > max) value = max
    return value
  }

  private fun clampVariant(x: Long, min: Long, max: Long): Long {
    var value = x
    if (value < min) value = min
    if (value > max) value = max
    return value
  }

  private fun maxVariant(d: DoubleArray): Double {
    var base = d[0]
    for (i in 1 until d.size) if (base < d[i]) base = d[i]
    return base
  }

  private fun maxVariant(l: LongArray): Long {
    var base = l[0]
    for (i in 1 until l.size) if (base < l[i]) base = l[i]
    return base
  }

  private fun minVariant(d: DoubleArray): Double {
    var base = d[0]
    for (i in 1 until d.size) if (base > d[i]) base = d[i]
    return base
  }

  private fun minVariant(l: LongArray): Long {
    var base = l[0]
    for (i in 1 until l.size) if (base > l[i]) base = l[i]
    return base
  }

  /** Every argument as a Double when each is a Double / Float, else null. */
  private fun doubles(args: Array<out Any?>): DoubleArray? {
    val out = DoubleArray(args.size)
    for (i in args.indices) {
      out[i] =
        when (val value = args[i]) {
          is Double -> value
          is Float -> value.toDouble()
          else -> return null
        }
    }
    return out
  }

  /** Every argument as a Long when each is a Long / Int, else null. */
  private fun longs(args: Array<out Any?>): LongArray? {
    val out = LongArray(args.size)
    for (i in args.indices) {
      out[i] =
        when (val value = args[i]) {
          is Long -> value
          is Int -> value.toLong()
          else -> return null
        }
    }
    return out
  }
"""


def _functions(api_path: Path) -> dict[str, dict]:
    return {f["name"]: f for f in json.loads(api_path.read_text(encoding="utf-8"))["utility_functions"]}


def check_web_local_utilities(api_path: Path) -> list[str]:
    """Every utility in exactly one table; frame rows fit the frame; bounds carry their reasons."""
    functions = _functions(api_path)
    tables = {
        "WEB_LOCAL_UTILITIES": set(WEB_LOCAL_UTILITIES),
        "WEB_LOCAL_VARIANT_UTILITIES": set(WEB_LOCAL_VARIANT_UTILITIES),
        "WEB_ENGINE_UTILITIES": set(WEB_ENGINE_UTILITIES),
    }
    problems: list[str] = []
    for name in sorted(functions):
        homes = [t for t, names in tables.items() if name in names]
        if len(homes) != 1:
            problems.append(f"utility {name}: in {homes or 'no table'}; list it in exactly one of {list(tables)}")
    for table, names in tables.items():
        problems += [f"{table} {n}: not a utility function in extension_api.json" for n in sorted(names - set(functions))]
    problems += [f"WEB_TEXT_FALLBACK {n}: not a text utility in WEB_ENGINE_UTILITIES" for n in sorted(WEB_TEXT_FALLBACK)
                 if not WEB_ENGINE_UTILITIES.get(n, "").startswith("text output")]
    for name, (bound, reason, body, _guard) in WEB_LOCAL_UTILITIES.items():
        fn = functions.get(name)
        if fn is None:
            continue
        args = fn.get("arguments", [])
        if fn["is_vararg"] or any(a["type"] not in _KOTLIN for a in args) or fn.get("return_type") not in _KOTLIN:
            problems.append(f"WEB_LOCAL_UTILITIES {name}: not a float / int / bool utility (frame form)")
        if bound == 0 and TRANSCENDENTAL.search(body):
            problems.append(f"WEB_LOCAL_UTILITIES {name}: bound 0 on a body that calls a libm transcendental")
        if bound and not reason:
            problems.append(f"WEB_LOCAL_UTILITIES {name}: nonzero ulp bound without a reason")
        if (name in WEB_LOCAL_TO_INT64) != (fn.get("return_type") == "int" and any(a["type"] == "float" for a in args[:1])):
            problems.append(f"WEB_LOCAL_TO_INT64 {name}: must list exactly the float->int rows")
    for name, (double_body, long_body, arity) in WEB_LOCAL_VARIANT_UTILITIES.items():
        fn = functions.get(name)
        if fn is None:
            continue
        if (arity is None) != fn["is_vararg"] or (arity is not None and arity != len(fn.get("arguments", []))):
            problems.append(f"WEB_LOCAL_VARIANT_UTILITIES {name}: arity does not match extension_api.json")
        for body in (double_body, long_body):
            if body and TRANSCENDENTAL.search(body):
                problems.append(f"WEB_LOCAL_VARIANT_UTILITIES {name}: a Variant form must be bit-exact")
    return problems


def render_web_local_utilities(api_path: Path, header: str) -> str:
    problems = check_web_local_utilities(api_path)
    if problems:
        raise SystemExit("[web_local_utilities] " + "\n[web_local_utilities] ".join(problems))
    functions = _functions(api_path)
    frame_names = sorted(WEB_LOCAL_UTILITIES)
    variant_names = sorted(WEB_LOCAL_VARIANT_UTILITIES)
    ids = {name: i for i, name in enumerate(frame_names + variant_names)}
    id_lines = [f'      "{name}" -> {ids[name]}' for name in frame_names + variant_names]

    frame_arms: list[str] = []
    for name in frame_names:
        bound, _reason, body, guard = WEB_LOCAL_UTILITIES[name]
        fn = functions[name]
        args = fn.get("arguments", [])
        lines = [f"      {ids[name]} -> {{ // {name}" + (f", within {bound} ulp" if bound else "")]
        for i, arg in enumerate(args, start=1):
            if f"a{i}" in body or f"a{i}" in guard:
                lines.append(f"        val a{i} = {_READ[arg['type']].format(i=i)}")
        if guard:
            lines.append(f"        if (!({guard})) return false")
        if name in WEB_LOCAL_TO_INT64:
            lines.append(f"        val r = {body}")
            lines.append("        if (!(r >= INT64_LOW && r < INT64_HIGH)) return false")
            lines.append("        " + _STORE["int"].format(v="r.toLong()"))
        else:
            lines.append("        " + _STORE[fn["return_type"]].format(v=body))
        lines.append("      }")
        frame_arms.append("\n".join(lines))

    boxed_arms: list[str] = []
    for name in variant_names:
        double_body, long_body, arity = WEB_LOCAL_VARIANT_UTILITIES[name]
        lines = [f"      {ids[name]} -> {{ // {name}"]
        if arity is not None:
            lines.append(f"        if (args.size != {arity}) return NOT_LOCAL")
        if double_body:
            lines.append(f"        doubles(args)?.let {{ d -> return {double_body} }}")
        if long_body:
            lines.append(f"        longs(args)?.let {{ l -> return {long_body} }}")
        lines.append("      }")
        boxed_arms.append("\n".join(lines))

    # Review N5: the Variant type each frame-form utility returns (0: void), so a nil answer from the
    # engine fails loud instead of reading as 0.
    frame_types = {"float": 3, "int": 2, "bool": 1, "void": 0}
    returns = sorted(
        (name, frame_types[fn.get("return_type", "void")])
        for name, fn in functions.items()
        if not fn["is_vararg"]
        and all(a["type"] in _KOTLIN for a in fn.get("arguments", []))
        and fn.get("return_type", "void") in frame_types
    )
    return_lines = [f'      "{name}" -> {t}' for name, t in returns]
    return "\n".join(
        [
            header.rstrip("\n"),
            "// Rows: scripts/web_local_utilities.py (task 129 E review B1).",
            "package net.multigesture.kanama.api",
            "",
            "import kotlin.math.pow",
            "import net.multigesture.kanama.binding.runtime.BuiltinFrame",
            "",
            "/**",
            " * The utility functions Web runs in Kotlin instead of crossing to the engine: ports of Godot",
            " * 4.7.2's formulas, bit-exact or within the ulp bound `scripts/web_local_utilities.py` records per",
            " * row (libm transcendentals). `BuiltinFrame.callUtility` and `UtilityCalls.call` ask here first by",
            " * the id [idOf] gives each `UtilityFunction`; an input a port does not cover answers false /",
            " * [NOT_LOCAL] and the engine runs it.",
            " */",
            "internal object WebLocalUtilities {",
            "  /** [callBoxed]'s answer when the engine must run the call. */",
            "  val NOT_LOCAL = Any()",
            "",
            "  /** The local id of utility [name], or -1 when the engine runs it. */",
            "  fun idOf(name: String): Int =",
            "    when (name) {",
            *id_lines,
            "      else -> -1",
            "    }",
            "",
            "  /**",
            "   * The Variant type frame-form utility [name] returns (0 for void), or -1 (a Variant) for the",
            "   * others, which `UtilityCalls.call` decodes by the type the generated GD passes.",
            "   */",
            "  fun frameReturnType(name: String): Int =",
            "    when (name) {",
            *return_lines,
            "      else -> -1",
            "    }",
            "",
            "  /** The separator text utility [name] prints its values with, or null for the others. */",
            "  fun textSeparator(name: String): String? =",
            "    when (name) {",
            *[f'      "{name}" -> "{sep}"' for name, sep in sorted(WEB_TEXT_FALLBACK.items())],
            "      else -> null",
            "    }",
            "",
            "  /** Runs utility [id] on the frame's slots 1..N into its return slot; false: the engine's. */",
            "  fun call(f: BuiltinFrame, id: Int): Boolean {",
            "    when (id) {",
            *frame_arms,
            "      else -> return false",
            "    }",
            "    return true",
            "  }",
            "",
            "  /** Runs Variant-form utility [id] on [args]; [NOT_LOCAL]: the engine's. */",
            "  fun callBoxed(id: Int, args: Array<out Any?>): Any? {",
            "    when (id) {",
            *boxed_arms,
            "    }",
            "    return NOT_LOCAL",
            "  }",
            "",
            HELPERS.rstrip("\n"),
            "}",
            "",
        ]
    )
