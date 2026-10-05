#!/usr/bin/env python3
"""The String, NodePath and PackedByteArray builtin methods (task 134 D2), rendered for
`generate_builtin_ops.py` (which writes and checks them with the rest of its outputs) and read by
`check_builtin_coverage.py`.

Kotlin represents these builtin classes with its own types -- `String` (and `StringName`),
`NodePath`, `ByteArray` (and `List<Int>`, `List<Float>`, ... for the other packed arrays) -- so
their Godot methods cannot be members of a Kanama class the way the value types' are:

* **String** methods become extension functions on `kotlin.String` in the package
  `net.multigesture.kanama.builtins` (`builtins/GodotString.kt`), Godot's statics
  (`String.num`, `String.chr`, ...) extensions on `String.Companion`. An extension is found by the
  IDE's auto-import from the call site (`path.getExtension()`), and outside a file that imports
  the package it adds nothing to `String`. A Godot method whose Kotlin name is already a stdlib
  `String` function (`length`, `split`, `replace`, `toInt`, ...) is NOT generated: importing the
  package must never change what an existing Kotlin call means. It gets a recorded reason naming
  the Kotlin form instead (STRING_REASONS), as do the ones the stdlib answers identically.
  `StringName` is a Kotlin `String` too: its methods are String's.
* **NodePath** methods are members of the `NodePath` value class (a GENERATED BUILTIN MEMBERS
  region in `types/NodePath.kt`, like the value types').
* **PackedByteArray** methods without a stdlib equivalent become extensions on `ByteArray`
  (`builtins/GodotBytes.kt`), and the other packed arrays' `to_byte_array` extensions on their
  Kotlin list types. The byte codecs (`decode_*`, `encode_*`, `bswap*`, `to_*_array`, `hex_encode`)
  are Godot's few lines, ported (pure Kotlin, parity-tested); the rest run in the engine.

Engine-run members go through `UtilityCalls.callMethod` (the boxed builtin call: the base and the
arguments converted like a utility function's), so their semantics are the engine's exactly --
Unicode handling, error prints and all. Only `const` methods may be engine-run (the boxed base is
destroyed after the call); every non-const one here is pure Kotlin or has a reason.
"""

from __future__ import annotations

import re
from dataclasses import dataclass
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
BUILTINS_DIR = ROOT / "src/commonMain/kotlin/net/multigesture/kanama/builtins"
STRING_KT = BUILTINS_DIR / "GodotString.kt"
BYTES_KT = BUILTINS_DIR / "GodotBytes.kt"
NODE_PATH_KT = ROOT / "src/commonMain/kotlin/net/multigesture/kanama/types/NodePath.kt"
GENERATOR = "scripts/generate_builtin_ops.py"

# ---------------------------------------------------------------------------------------------
# Dispositions: every String / StringName / NodePath / PackedByteArray method is a member or has a
# reason here (the coverage gate reads both).
# ---------------------------------------------------------------------------------------------

_SHADOW = "a generated `{name}` would shadow Kotlin's in every file that imports the package"
STRING_REASONS: dict[str, str] = {
    "length": "Kotlin's `length` property (UTF-16 units; Godot counts code points, which differ only outside the "
    "BMP: `codePointCount`); " + _SHADOW.format(name="length"),
    "is_empty": "Kotlin `isEmpty()`: the same test; " + _SHADOW.format(name="isEmpty"),
    "contains": "Kotlin `contains(what)`: the same case-sensitive substring test; " + _SHADOW.format(name="contains"),
    "begins_with": "Kotlin `startsWith(text)`: the same test",
    "ends_with": "Kotlin `endsWith(text)`: the same test; " + _SHADOW.format(name="endsWith"),
    "replace": "Kotlin `replace(what, forwhat)`: the same (every occurrence, case-sensitive); "
    + _SHADOW.format(name="replace"),
    "repeat": "Kotlin `repeat(count)`: the same; " + _SHADOW.format(name="repeat"),
    "split": "Kotlin `split(delimiter)` (`limit` = maxsplit + 1); " + _SHADOW.format(name="split")
    + ". Godot's extra forms: `allow_empty = false` is `.filter { it.isNotEmpty() }`, an empty delimiter "
    "splits into characters; `rsplit` is generated",
    "join": "Kotlin `parts.joinToString(separator)`: the same",
    "trim_prefix": "Kotlin `removePrefix(prefix)`: the same",
    "trim_suffix": "Kotlin `removeSuffix(suffix)`: the same",
    "to_int": "Kotlin `toLong()` (strict: throws on `\"12abc\"`, which Godot's lenient parse reads as 12; check "
    "with the generated `isValidInt()`); " + _SHADOW.format(name="toInt"),
    "to_float": "Kotlin `toDouble()` (strict, like `toLong()`; check with the generated `isValidFloat()`); "
    + _SHADOW.format(name="toFloat"),
}

STRING_NAME_REASON = "a StringName is a Kotlin `String` (marshalled at the boundary): String's member `{kn}` covers it"
STRING_NAME_REASON_OF = "a StringName is a Kotlin `String`: as String.{name}: {why}"

NODE_PATH_REASONS: dict[str, str] = {}

# PackedByteArray: the methods Kotlin's ByteArray / MutableList already have.
_PBA_STDLIB = {
    "get": "`bytes[index]`",
    "set": "`bytes[index] = value`",
    "size": "`bytes.size`",
    "is_empty": "`bytes.isEmpty()`",
    "push_back": "a `ByteArray` has a fixed size: `bytes + value` (or a `MutableList<Byte>`)",
    "append": "a `ByteArray` has a fixed size: `bytes + value`",
    "append_array": "`bytes + other`",
    "remove_at": "`bytes.copyOfRange` / a `MutableList<Byte>`",
    "insert": "a `MutableList<Byte>`'s `add(index, value)`",
    "fill": "`bytes.fill(value)`",
    "resize": "`bytes.copyOf(newSize)`",
    "clear": "`ByteArray(0)`",
    "has": "`value in bytes`",
    "reverse": "`bytes.reverse()`",
    "slice": "`bytes.copyOfRange(begin, end)`",
    "sort": "`bytes.sort()` (signed order; Godot sorts unsigned: `sortedBy { it.toUByte() }`)",
    "bsearch": "`bytes.binarySearch(value)` on sorted bytes",
    "duplicate": "`bytes.copyOf()`",
    "find": "`bytes.indexOf(value)`",
    "rfind": "`bytes.lastIndexOf(value)`",
    "count": "`bytes.count { it == value }`",
    "erase": "a `MutableList<Byte>`'s `remove(value)`",
}
PBA_REASONS: dict[str, str] = {name: f"Kotlin {how}" for name, how in _PBA_STDLIB.items()}
PBA_REASONS["encode_var"] = (
    "not const (writes into the array, which the boxed engine call cannot hand back): "
    "`GD.varToBytes(value).copyInto(bytes, offset)` is the same encoding (Godot's `var_to_bytes`)"
)

# PackedByteArray methods computed in Kotlin: Godot's byte codec, ported (variant_call.cpp). An
# offset or size Godot rejects with an error print and a zero/empty result throws here instead
# (IndexOutOfBoundsException / IllegalArgumentException), like any out-of-range ByteArray access.
PBA_PURE = {
    *(f"decode_{s}{w}" for s in "us" for w in (8, 16, 32, 64)),
    *(f"encode_{s}{w}" for s in "us" for w in (8, 16, 32, 64)),
    "decode_half", "decode_float", "decode_double", "encode_half", "encode_float", "encode_double",
    "bswap16", "bswap32", "bswap64", "hex_encode",
    "to_int32_array", "to_int64_array", "to_float32_array", "to_float64_array",
    "to_vector2_array", "to_vector3_array", "to_vector4_array", "to_color_array",
}

# The other packed arrays' `to_byte_array`: pure Kotlin on their Kotlin list type.
TO_BYTE_ARRAY_LISTS = {
    "PackedInt32Array": "Int",
    "PackedInt64Array": "Long",
    "PackedFloat32Array": "Float",
    "PackedFloat64Array": "Double",
    "PackedStringArray": "String",
    "PackedVector2Array": "Vector2",
    "PackedVector3Array": "Vector3",
    "PackedColorArray": "Color",
    "PackedVector4Array": "Vector4",
}

# Boxed-call conversion of an argument: (Kotlin type, Variant type constant of UtilityCalls).
BOXED_ARG = {
    "String": ("String", "BoxedType.STRING"),
    "StringName": ("String", "BoxedType.STRING_NAME"),
    "int": ("Long", "BoxedType.INT"),
    "float": ("Double", "BoxedType.FLOAT"),
    "bool": ("Boolean", "BoxedType.BOOL"),
    "Variant": ("Any?", "BoxedType.NIL"),
    "PackedByteArray": ("ByteArray", "BoxedType.PACKED_BYTE_ARRAY"),
}
# ... and of a return: (Kotlin type, Variant type constant, cast).
BOXED_RET = {
    "String": ("String", "BoxedType.STRING", " as String"),
    "StringName": ("String", "BoxedType.STRING_NAME", " as String"),
    "NodePath": ("NodePath", "BoxedType.NODE_PATH", " as NodePath"),
    "int": ("Long", "BoxedType.INT", " as Long"),
    "float": ("Double", "BoxedType.FLOAT", " as Double"),
    "bool": ("Boolean", "BoxedType.BOOL", " as Boolean"),
    "PackedByteArray": ("ByteArray", "BoxedType.PACKED_BYTE_ARRAY", " as ByteArray"),
    "PackedStringArray": ("List<String>", "BoxedType.PACKED_STRING_ARRAY", " as List<String>"),
    "PackedFloat64Array": ("List<Double>", "BoxedType.PACKED_FLOAT64_ARRAY", " as List<Double>"),
    "Variant": ("Any?", "BoxedType.NIL", ""),
}
BASE_VT = {"String": "BoxedType.STRING", "NodePath": "BoxedType.NODE_PATH", "PackedByteArray": "BoxedType.PACKED_BYTE_ARRAY"}

KOTLIN_HARD_KEYWORDS = {
    "as", "break", "class", "continue", "do", "else", "false", "for", "fun", "if", "in", "interface", "is",
    "null", "object", "package", "return", "super", "this", "throw", "true", "try", "typealias", "typeof",
    "val", "var", "when", "while",
}


def camel(name: str) -> str:
    head, *rest = name.split("_")
    return head + "".join(part[:1].upper() + part[1:] for part in rest)


def param_name(name: str) -> str:
    kn = camel(name)
    return f"`{kn}`" if kn in KOTLIN_HARD_KEYWORDS else kn


def kotlin_default(godot_type: str, value: str) -> str:
    if godot_type == "int":
        return f"{value}L"
    if godot_type == "float":
        return value if any(ch in value for ch in ".e") else f"{value}.0"
    if godot_type in ("bool",):
        return value
    if godot_type in ("String", "StringName"):
        return value  # Godot writes them as Kotlin-compatible literals: "", " ", "{_}"
    raise SystemExit(f"[builtin_boxed_methods] no Kotlin default for {godot_type} = {value}")


@dataclass
class Boxed:
    cls: str
    godot: dict
    kn: str
    impl: str  # "facade" | "pure"
    code: str


def builtin(api: dict, name: str) -> dict:
    return next(c for c in api["builtin_classes"] if c["name"] == name)


def variant_ids(api: dict) -> dict[str, int]:
    ids = {}
    for enum in api["global_enums"]:
        if enum["name"] == "Variant.Type":
            for value in enum["values"]:
                ids[value["name"].removeprefix("TYPE_")] = value["value"]
    return ids


def _sig_name(types: list[str]) -> str:
    return "_".join(t.removeprefix("BoxedType.") for t in types) or "NONE"


SIGNATURES: dict[str, list[str]] = {}


def facade_fun(cls: str, m: dict, receiver: str, methods_object: str, *, member: bool = False) -> str:
    """One engine-run function: the boxed call with the base, the arguments and the return."""
    kn = camel(m["name"])
    static = m.get("is_static", False)
    params, args, vts = [], [], []
    for arg in m.get("arguments", []):
        ktype, vt = BOXED_ARG[arg["type"]]
        default = f" = {kotlin_default(arg['type'], arg['default_value'])}" if "default_value" in arg else ""
        pn = param_name(arg["name"])
        params.append(f"{pn}: {ktype}{default}")
        args.append(pn)
        vts.append(vt)
    sig = _sig_name(vts)
    SIGNATURES[sig] = vts
    ret = m.get("return_type", "void")
    rtype, rvt, cast = BOXED_RET[ret]
    base = "null" if static else "this"
    call = (
        f"UtilityCalls.callMethod({methods_object}.{kn}, {BASE_VT[cls]}, {base}, BoxedSig.{sig}, "
        f"arrayOf<Any?>({', '.join(args)}), {rvt}){cast}"
    )
    head = f"fun {receiver}{kn}({', '.join(params)}): {rtype}"
    if rtype.startswith("List<"):
        return f'@Suppress("UNCHECKED_CAST")\n{head} = {call}'
    return f"{head} = {call}"


def runtime_imports(code: str, extra: tuple[str, ...] = ()) -> list[str]:
    """The `binding.runtime` imports [code] uses (ktfmt drops unused ones, so emit exactly these)."""
    return [f"import net.multigesture.kanama.binding.runtime.{n}" for n in sorted(set(extra))]


def methods_object(name: str, cls: str, entries: list[Boxed], vid: int) -> list[str]:
    out = [f"/** {cls}'s engine-run builtin methods (resolved on their first call). */", f"internal object {name} {{"]
    for e in entries:
        out.append(f"  @JvmField val {e.kn} = BuiltinMethod({vid}, \"{e.godot['name']}\", {e.godot['hash']}L)")
        out.append("")
    if out[-1] == "":
        out.pop()
    out.append("}")
    return out


BOXED_TYPES = ("NIL", "BOOL", "INT", "FLOAT", "STRING", "STRING_NAME", "NODE_PATH", "PACKED_BYTE_ARRAY",
               "PACKED_FLOAT64_ARRAY", "PACKED_STRING_ARRAY")


def boxed_type_object(ids: dict[str, int]) -> list[str]:
    """The Variant type ids the boxed calls name (from extension_api.json's Variant.Type): one source
    the native and the Web build both compile (the native `VT_*` of BuiltinTags.kt are not on Web)."""
    out = ["/** Godot's `Variant.Type` ids of the boxed calls' bases, arguments and returns. */",
           "internal object BoxedType {"]
    for name in BOXED_TYPES:
        out.append(f"  const val {name} = {ids[name]}")
        out.append("")
    out.pop()
    out.append("}")
    return out


def signatures_object() -> list[str]:
    out = ["/** The argument Variant types of the boxed calls above, one array per signature. */",
           "internal object BoxedSig {"]
    for sig in sorted(SIGNATURES):
        types = ", ".join(SIGNATURES[sig])
        out.append(f"  @JvmField val {sig} = intArrayOf({types})")
        out.append("")
    if out[-1] == "":
        out.pop()
    out.append("}")
    return out


# ---------------------------------------------------------------------------------------------
# String.
# ---------------------------------------------------------------------------------------------


def string_dispositions(api: dict) -> dict[str, tuple[str, str]]:
    """Godot String method -> ('member', Kotlin name) or ('reason', why)."""
    out = {}
    for m in builtin(api, "String")["methods"]:
        name = m["name"]
        if name in STRING_REASONS:
            out[name] = ("reason", STRING_REASONS[name])
        else:
            out[name] = ("member", camel(name))
    return out


def string_members(api: dict) -> list[Boxed]:
    members = []
    for m in builtin(api, "String")["methods"]:
        if m["name"] in STRING_REASONS:
            continue
        if not m.get("is_const", False) and not m.get("is_static", False):
            raise SystemExit(f"[builtin_boxed_methods] String.{m['name']} is not const: it cannot be engine-run boxed")
        if m["name"] == "format":
            members.append(Boxed("String", m, "format", "facade", format_funs(m)))
            continue
        receiver = "String.Companion." if m.get("is_static", False) else "String."
        members.append(Boxed("String", m, camel(m["name"]), "facade", facade_fun("String", m, receiver, "StringMethods")))
    return members


def format_funs(m: dict) -> str:
    """`format(values: Variant, placeholder)`: typed Map / List overloads instead of `Any?`, so that
    `"%d".format(5)` (JVM's `String.format`) still means what it means when the package is imported."""
    SIGNATURES["NIL_STRING"] = ["BoxedType.NIL", "BoxedType.STRING"]
    out = []
    for vtype in ("Map<String, Any?>", "List<Any?>"):
        out.append(
            f'fun String.format(values: {vtype}, placeholder: String = "{{_}}"): String =\n'
            f"  UtilityCalls.callMethod(StringMethods.format, BoxedType.STRING, this, BoxedSig.NIL_STRING, "
            f"arrayOf<Any?>(values, placeholder), BoxedType.STRING) as String"
        )
    return "\n\n".join(out)


def file_text(package: str, comment: list[str], body: list[str], imports: set[str]) -> str:
    """A generated Kotlin file: header, the sorted imports [body] uses, the comment, the body."""
    code = "\n".join(body)
    imported = set(imports)
    for line in runtime_imports(code):
        imported.add(line.removeprefix("import "))
    out = [f"// GENERATED by {GENERATOR} from extension_api.json — do not edit.", f"package {package}", ""]
    out += [f"import {name}" for name in sorted(imported)]
    out.append("")
    out += comment
    if comment:
        out.append("")
    out += body
    return "\n".join(out).rstrip() + "\n"


def render_string_kt(api: dict) -> str:
    vid = variant_ids(api)["STRING"]
    members = string_members(api)
    body = []
    for e in members:
        body.append(e.code)
        body.append("")
    body.extend(methods_object("StringMethods", "String", members, vid))
    comment = [
        "// Godot's String methods on Kotlin's String (task 134 D2):",
        "// `import net.multigesture.kanama.builtins.*`, then `path.getExtension()`,",
        "// `name.toSnakeCase()`, `String.num(x, 2)`. Each runs Godot's own",
        "// implementation (the boxed builtin call), so Unicode, paths and number formatting behave as in",
        "// GDScript. The methods Kotlin's String already has under the same name are not repeated here:",
        "// check_builtin_coverage.py lists each with its Kotlin form (`length`, `split`, `replace`, ...).",
    ]
    imports = {
        "kotlin.jvm.JvmField",
        "net.multigesture.kanama.binding.runtime.BuiltinMethod",
        "net.multigesture.kanama.binding.runtime.UtilityCalls",
    }
    return file_text("net.multigesture.kanama.builtins", comment, body, imports)


# ---------------------------------------------------------------------------------------------
# NodePath.
# ---------------------------------------------------------------------------------------------


def node_path_members(api: dict) -> list[Boxed]:
    members = []
    for m in builtin(api, "NodePath")["methods"]:
        if not m.get("is_const", False):
            raise SystemExit(f"[builtin_boxed_methods] NodePath.{m['name']} is not const")
        members.append(Boxed("NodePath", m, camel(m["name"]), "facade", facade_fun("NodePath", m, "", "NodePathMethods")))
    return members


def node_path_dispositions(api: dict) -> dict[str, tuple[str, str]]:
    return {m["name"]: ("member", camel(m["name"])) for m in builtin(api, "NodePath")["methods"]}


def node_path_region_sources(api: dict) -> list[str]:
    return [e.code for e in node_path_members(api)]


NODE_PATH_IMPORTS = (
    "net.multigesture.kanama.binding.runtime.UtilityCalls",
    "net.multigesture.kanama.builtins.BoxedSig",
    "net.multigesture.kanama.builtins.BoxedType",
    "net.multigesture.kanama.builtins.NodePathMethods",
)


# ---------------------------------------------------------------------------------------------
# PackedByteArray (+ the other packed arrays' to_byte_array).
# ---------------------------------------------------------------------------------------------

_INT_DECODE = {
    "u8": "readLE(o, 1)",
    "s8": "readLE(o, 1).toByte().toLong()",
    "u16": "readLE(o, 2)",
    "s16": "readLE(o, 2).toShort().toLong()",
    "u32": "readLE(o, 4)",
    "s32": "readLE(o, 4).toInt().toLong()",
    "u64": "readLE(o, 8)",
    "s64": "readLE(o, 8)",
}
_ARRAYS = {
    # name: (Kotlin element, element bytes expression, read expression at byte index `at`)
    "to_int32_array": ("Int", "4", "readLE(at, 4).toInt()"),
    "to_int64_array": ("Long", "8", "readLE(at, 8)"),
    "to_float32_array": ("Float", "4", "Float.fromBits(readLE(at, 4).toInt())"),
    "to_float64_array": ("Double", "8", "Double.fromBits(readLE(at, 8))"),
    "to_vector2_array": ("Vector2", "2 * REAL_BYTES", "Vector2.raw(readReal(at), readReal(at + REAL_BYTES))"),
    "to_vector3_array": (
        "Vector3",
        "3 * REAL_BYTES",
        "Vector3.raw(readReal(at), readReal(at + REAL_BYTES), readReal(at + 2 * REAL_BYTES))",
    ),
    "to_vector4_array": (
        "Vector4",
        "4 * REAL_BYTES",
        "Vector4.raw(readReal(at), readReal(at + REAL_BYTES), readReal(at + 2 * REAL_BYTES), readReal(at + 3 * REAL_BYTES))",
    ),
    "to_color_array": (
        "Color",
        "16",
        "Color.raw(Float.fromBits(readLE(at, 4).toInt()), Float.fromBits(readLE(at + 4, 4).toInt()), "
        "Float.fromBits(readLE(at + 8, 4).toInt()), Float.fromBits(readLE(at + 12, 4).toInt()))",
    ),
}
# The Godot message of each to_*_array size check (variant_call.cpp), named by element.
_ARRAY_WHAT = {
    "to_int32_array": ("4", "size of 32-bit integer", "PackedInt32Array"),
    "to_int64_array": ("8", "size of 64-bit integer", "PackedInt64Array"),
    "to_float32_array": ("4", "size of 32-bit float", "PackedFloat32Array"),
    "to_float64_array": ("8", "size of 64-bit double", "PackedFloat64Array"),
    "to_vector2_array": ("${2 * REAL_BYTES}", "size of Vector2", "PackedVector2Array"),
    "to_vector3_array": ("${3 * REAL_BYTES}", "size of Vector3", "PackedVector3Array"),
    "to_vector4_array": ("${4 * REAL_BYTES}", "size of Vector4", "PackedVector4Array"),
    "to_color_array": ("16", "size of Color variant", "PackedColorArray"),
}


def pure_bytes(m: dict) -> str:
    name = m["name"]
    kn = camel(name)
    if name.startswith("decode_") and name[7:] in _INT_DECODE:
        width = int(name[8:]) // 8
        return (
            f"fun ByteArray.{kn}(byteOffset: Long): Long {{\n"
            f"  val o = checkOffset(byteOffset, {width})\n"
            f"  return {_INT_DECODE[name[7:]]}\n}}"
        )
    if name.startswith("encode_") and name[7:] in _INT_DECODE:
        width = int(name[8:]) // 8
        return f"fun ByteArray.{kn}(byteOffset: Long, value: Long) {{\n  writeLE(checkOffset(byteOffset, {width}), {width}, value)\n}}"
    if name == "decode_half":
        return "fun ByteArray.decodeHalf(byteOffset: Long): Double =\n  halfToFloat(readLE(checkOffset(byteOffset, 2), 2).toInt()).toDouble()"
    if name == "decode_float":
        return "fun ByteArray.decodeFloat(byteOffset: Long): Double =\n  Float.fromBits(readLE(checkOffset(byteOffset, 4), 4).toInt()).toDouble()"
    if name == "decode_double":
        return "fun ByteArray.decodeDouble(byteOffset: Long): Double =\n  Double.fromBits(readLE(checkOffset(byteOffset, 8), 8))"
    if name == "encode_half":
        return (
            "fun ByteArray.encodeHalf(byteOffset: Long, value: Double) {\n"
            "  writeLE(checkOffset(byteOffset, 2), 2, makeHalfFloat(value.toFloat()).toLong())\n}"
        )
    if name == "encode_float":
        return (
            "fun ByteArray.encodeFloat(byteOffset: Long, value: Double) {\n"
            "  writeLE(checkOffset(byteOffset, 4), 4, value.toFloat().toRawBits().toLong())\n}"
        )
    if name == "encode_double":
        return (
            "fun ByteArray.encodeDouble(byteOffset: Long, value: Double) {\n"
            "  writeLE(checkOffset(byteOffset, 8), 8, value.toRawBits())\n}"
        )
    if name.startswith("bswap"):
        width = int(name[5:]) // 8
        return (
            f"fun ByteArray.{kn}(offset: Long = 0L, count: Long = -1L) {{\n"
            f"  swapBytes(offset, count, {width})\n}}"
        )
    if name == "hex_encode":
        return (
            "fun ByteArray.hexEncode(): String = buildString(size * 2) {\n"
            "  for (b in this@hexEncode) {\n"
            "    val v = b.toInt() and 0xFF\n"
            "    append(HEX_DIGITS[v shr 4]).append(HEX_DIGITS[v and 0xF])\n"
            "  }\n}"
        )
    if name in _ARRAYS:
        element, size, read = _ARRAYS[name]
        n, what, packed = _ARRAY_WHAT[name]
        return (
            f"fun ByteArray.{kn}(): List<{element}> {{\n"
            f"  val elementBytes = {size}\n"
            f"  if (isEmpty()) return emptyList()\n"
            f"  require(size % elementBytes == 0) {{\n"
            f'    "PackedByteArray size must be a multiple of {n} ({what}) to convert to {packed}."\n'
            f"  }}\n"
            f"  return List(size / elementBytes) {{\n"
            f"    val at = it * elementBytes\n"
            f"    {read}\n"
            f"  }}\n}}"
        )
    raise SystemExit(f"[builtin_boxed_methods] no pure form for PackedByteArray.{name}")


def to_byte_array_fun(cls: str, element: str) -> str:
    jvm = f'@JvmName("{camel(cls[0].lower() + cls[1:])}ToByteArray")'
    if element == "String":
        body = (
            "  val parts = map { it.encodeToByteArray() }\n"
            "  val out = ByteArray(parts.sumOf { it.size + 1 })\n"
            "  var at = 0\n"
            "  for (part in parts) {\n"
            "    part.copyInto(out, at)\n"
            "    at += part.size + 1 // Godot terminates each string with a NUL\n"
            "  }\n"
            "  return out"
        )
        return f"{jvm}\nfun List<String>.toByteArray(): ByteArray {{\n{body}\n}}"
    width, writes = {
        "Int": ("4", ["writeLE(at, 4, v.toLong())"]),
        "Long": ("8", ["writeLE(at, 8, v)"]),
        "Float": ("4", ["writeLE(at, 4, v.toRawBits().toLong())"]),
        "Double": ("8", ["writeLE(at, 8, v.toRawBits())"]),
        "Vector2": ("2 * REAL_BYTES", ["writeReal(at, v.rawX)", "writeReal(at + REAL_BYTES, v.rawY)"]),
        "Vector3": (
            "3 * REAL_BYTES",
            ["writeReal(at, v.rawX)", "writeReal(at + REAL_BYTES, v.rawY)", "writeReal(at + 2 * REAL_BYTES, v.rawZ)"],
        ),
        "Vector4": (
            "4 * REAL_BYTES",
            [
                "writeReal(at, v.rawX)",
                "writeReal(at + REAL_BYTES, v.rawY)",
                "writeReal(at + 2 * REAL_BYTES, v.rawZ)",
                "writeReal(at + 3 * REAL_BYTES, v.rawW)",
            ],
        ),
        "Color": (
            "16",
            [f"writeLE(at + {4 * i}, 4, v.raw{c}.toRawBits().toLong())" for i, c in enumerate("RGBA")],
        ),
    }[element]
    body = [
        f"  val elementBytes = {width}",
        "  val out = ByteArray(size * elementBytes)",
        "  for (i in indices) {",
        "    val v = this[i]",
        "    val at = i * elementBytes",
        *[f"    out.{w}" for w in writes],
        "  }",
        "  return out",
    ]
    return f"{jvm}\nfun List<{element}>.toByteArray(): ByteArray {{\n" + "\n".join(body) + "\n}"


BYTES_HELPERS = '''private const val HEX_DIGITS = "0123456789abcdef"

/** Bytes of one `real_t` component: Godot's storage width (`float_32` unless a double build). */
private val REAL_BYTES: Int = if (REAL_IS_SINGLE) 4 else 8

/** [offset] as an index when [width] bytes at it are inside the array (Godot's ERR_FAIL_COND). */
private fun ByteArray.checkOffset(offset: Long, width: Int): Int {
  if (offset < 0 || offset > size.toLong() - width) {
    throw IndexOutOfBoundsException("byte offset $offset (+$width bytes) is outside the array (size $size)")
  }
  return offset.toInt()
}

/** [width] bytes at [at], little-endian (Godot's `decode_uint*`), zero-extended. */
private fun ByteArray.readLE(at: Int, width: Int): Long {
  var v = 0L
  for (i in width - 1 downTo 0) v = (v shl 8) or (this[at + i].toLong() and 0xFF)
  return v
}

/** The low [width] bytes of [value] at [at], little-endian (Godot's `encode_uint*`). */
private fun ByteArray.writeLE(at: Int, width: Int, value: Long) {
  for (i in 0 until width) this[at + i] = (value ushr (8 * i)).toByte()
}

private fun ByteArray.readReal(at: Int): GodotRealStorage =
  if (REAL_IS_SINGLE) narrowReal(Float.fromBits(readLE(at, 4).toInt()).toDouble())
  else narrowReal(Double.fromBits(readLE(at, 8)))

private fun ByteArray.writeReal(at: Int, value: GodotRealStorage) {
  if (REAL_IS_SINGLE) writeLE(at, 4, widenReal(value).toFloat().toRawBits().toLong())
  else writeLE(at, 8, widenReal(value).toRawBits())
}

/** Godot's `bswap16/32/64`: [count] words of [width] bytes from [offset] (-1: to the end). */
private fun ByteArray.swapBytes(offset: Long, count: Long, width: Int) {
  if (isEmpty() || count == 0L) return
  if (offset < 0 || offset > size.toLong() - width) {
    throw IndexOutOfBoundsException("byte offset $offset (+$width bytes) is outside the array (size $size)")
  }
  val available = (size - offset) / width
  val words = if (count < 0) available else count
  require(words <= available) { "$words words of $width bytes from $offset exceed the array (size $size)" }
  for (w in 0 until words.toInt()) {
    val at = offset.toInt() + w * width
    for (i in 0 until width / 2) {
      val t = this[at + i]
      this[at + i] = this[at + width - 1 - i]
      this[at + width - 1 - i] = t
    }
  }
}

/** Godot's `Math::half_to_float` (math_funcs.h): subnormals, signed zeros, INF and NaN kept. */
private fun halfToFloat(half: Int): Float {
  var hExp = half and 0x7c00
  val fSgn = (half and 0x8000) shl 16
  val bits =
    when (hExp) {
      0x0000 -> {
        var hSig = half and 0x03ff
        if (hSig == 0) {
          fSgn
        } else {
          hSig = hSig shl 1
          while (hSig and 0x0400 == 0) {
            hSig = hSig shl 1
            hExp++
          }
          fSgn + ((127 - 15 - hExp) shl 23) + ((hSig and 0x03ff) shl 13)
        }
      }
      0x7c00 -> fSgn + 0x7f800000 + ((half and 0x03ff) shl 13)
      else -> fSgn + (((half and 0x7fff) + 0x1c000) shl 13)
    }
  return Float.fromBits(bits)
}

/** Godot's `Math::make_half_float`: truncating, subnormal results become +0, NaN stays NaN. */
private fun makeHalfFloat(value: Float): Int {
  val x = value.toRawBits()
  val sign = x ushr 31
  var mantissa = x and ((1 shl 23) - 1)
  val exponent = x and (0xFF shl 23)
  return when {
    exponent >= 0x47800000 -> {
      mantissa = if (mantissa != 0 && exponent == (0xFF shl 23)) (1 shl 23) - 1 else 0
      (sign shl 15) or (0x1F shl 10) or (mantissa ushr 13)
    }
    exponent <= 0x38000000 -> 0 // Godot: "denormals do not work for 3D, convert to zero"
    else -> ((sign shl 15) or ((exponent - 0x38000000) ushr 13) or (mantissa ushr 13)) and 0xFFFF
  }
}'''


def bytes_dispositions(api: dict) -> dict[str, tuple[str, str]]:
    out = {}
    for m in builtin(api, "PackedByteArray")["methods"]:
        name = m["name"]
        if name in PBA_REASONS:
            out[name] = ("reason", PBA_REASONS[name])
        else:
            out[name] = ("member", camel(name))
    return out


def bytes_members(api: dict) -> list[Boxed]:
    members = []
    for m in builtin(api, "PackedByteArray")["methods"]:
        name = m["name"]
        if name in PBA_REASONS:
            continue
        if name in PBA_PURE:
            members.append(Boxed("PackedByteArray", m, camel(name), "pure", pure_bytes(m)))
            continue
        if not m.get("is_const", False):
            raise SystemExit(f"[builtin_boxed_methods] PackedByteArray.{name} is not const: pure or a reason")
        members.append(
            Boxed("PackedByteArray", m, camel(name), "facade", facade_fun("PackedByteArray", m, "ByteArray.", "BytesMethods"))
        )
    return members


def render_bytes_kt(api: dict) -> str:
    vid = variant_ids(api)["PACKED_BYTE_ARRAY"]
    members = bytes_members(api)
    body = []
    for e in members:
        body.append(e.code)
        body.append("")
    body.append("// The other packed arrays' `to_byte_array`, on their Kotlin list types (pure: Godot's memcpy).")
    body.append("")
    for cls, element in TO_BYTE_ARRAY_LISTS.items():
        body.append(to_byte_array_fun(cls, element))
        body.append("")
    body.append(BYTES_HELPERS)
    body.append("")
    facade = [e for e in members if e.impl == "facade"]
    body.extend(methods_object("BytesMethods", "PackedByteArray", facade, vid))
    comment = [
        "// Godot's PackedByteArray methods on Kotlin's ByteArray (task 134 D2):",
        "// `import net.multigesture.kanama.builtins.*`, then `bytes.decodeU32(0)`,",
        "// `bytes.getStringFromUtf8()`, `bytes.compress()`. The byte codecs are Godot's, ported (an offset",
        "// Godot rejects throws IndexOutOfBoundsException); the rest run in the engine. ByteArray's own",
        "// members cover size/get/set/fill/sort/... (check_builtin_coverage.py lists each).",
    ]
    imports = {
        "kotlin.jvm.JvmField",
        "kotlin.jvm.JvmName",
        "net.multigesture.kanama.binding.runtime.BuiltinMethod",
        "net.multigesture.kanama.binding.runtime.UtilityCalls",
        "net.multigesture.kanama.types.Color",
        "net.multigesture.kanama.types.GodotRealStorage",
        "net.multigesture.kanama.types.REAL_IS_SINGLE",
        "net.multigesture.kanama.types.Vector2",
        "net.multigesture.kanama.types.Vector3",
        "net.multigesture.kanama.types.Vector4",
        "net.multigesture.kanama.types.narrowReal",
        "net.multigesture.kanama.types.widenReal",
    }
    return file_text("net.multigesture.kanama.builtins", comment, body, imports)


def to_byte_array_dispositions() -> dict[tuple[str, str], tuple[str, str]]:
    return {(cls, "to_byte_array"): ("member", f"List<{el}>.toByteArray") for cls, el in TO_BYTE_ARRAY_LISTS.items()}


def render_signatures_kt(api: dict) -> str:
    """BoxedSig (filled while the members above render) and NodePath's method constants."""
    ids = variant_ids(api)
    vid = ids["NODE_PATH"]
    body = boxed_type_object(ids) + [""] + signatures_object() + [""]
    body += methods_object("NodePathMethods", "NodePath", node_path_members(api), vid)
    imports = {"kotlin.jvm.JvmField", "net.multigesture.kanama.binding.runtime.BuiltinMethod"}
    return file_text("net.multigesture.kanama.builtins", [], body, imports)


SIGNATURES_KT = BUILTINS_DIR / "BoxedMethods.kt"


def all_dispositions(api: dict) -> dict[tuple[str, str], tuple[str, str]]:
    """(class, Godot method) -> ('member', what) / ('reason', why) for every class this module owns."""
    out: dict[tuple[str, str], tuple[str, str]] = {}
    strings = string_dispositions(api)
    for name, d in strings.items():
        out[("String", name)] = d
    for m in builtin(api, "StringName")["methods"]:
        status, what = strings.get(m["name"], ("reason", "?"))
        if status == "member":
            out[("StringName", m["name"])] = ("reason", STRING_NAME_REASON.format(kn=what))
        else:
            out[("StringName", m["name"])] = ("reason", STRING_NAME_REASON_OF.format(name=m["name"], why=what))
    for name, d in node_path_dispositions(api).items():
        out[("NodePath", name)] = d
    for name, d in bytes_dispositions(api).items():
        out[("PackedByteArray", name)] = d
    out.update(to_byte_array_dispositions())
    for name, d in CALLABLE_DISPOSITIONS.items():
        out[("Callable", name)] = d
    for name, d in SIGNAL_DISPOSITIONS.items():
        out[("Signal", name)] = d
    return out


# ---------------------------------------------------------------------------------------------
# Callable and Signal: members of the hand-written GodotCallable / GodotSignal (runtime roots), each
# the emitter's / target's Object call Godot's own Callable and Signal make. The gate checks that a
# `fun <name>(` exists in the file named here.
# ---------------------------------------------------------------------------------------------

CALLABLE_KT = ROOT / "src/commonMain/kotlin/net/multigesture/kanama/api/GodotCallable.kt"
SIGNAL_KT = ROOT / "src/commonMain/kotlin/net/multigesture/kanama/api/GodotSignal.expect.kt"

_BOUND = (
    "not offered: a GodotCallable is an object + method pair with no bound arguments, and every Callable "
    "argument path builds the engine Callable from that pair (the desktop ObjectCalls helpers, the iOS "
    "PT_CALLABLE descriptor, Web); binding arguments for a connection is `GodotObject.connect(signal, "
    "target, method, boundArgs)` (task 134 D2: bind/unbind need a GodotCallable that carries arguments)"
)
CALLABLE_DISPOSITIONS: dict[str, tuple[str, str]] = {
    "create": ("reason", "`GodotCallable(target, method)` is the constructor; a Callable over a builtin value "
               "(`Callable.create(\"text\", \"to_upper\")`) has no Kotlin form (call the String member instead)"),
    "callv": ("member", "callv"),
    "is_null": ("reason", "a GodotCallable always names a target and a method: never null"),
    "is_custom": ("reason", "a GodotCallable is always a standard (object + method) Callable: `false`"),
    "is_standard": ("reason", "a GodotCallable is always a standard (object + method) Callable: `true`"),
    "is_valid": ("member", "isValid"),
    "get_object": ("member", "getObject"),
    "get_object_id": ("member", "getObjectId"),
    "get_method": ("reason", "the `method` property"),
    "get_argument_count": ("member", "getArgumentCount"),
    "get_bound_arguments_count": ("reason", _BOUND),
    "get_bound_arguments": ("reason", _BOUND),
    "get_unbound_arguments_count": ("reason", _BOUND),
    "hash": ("reason", "Kotlin `hashCode()` (a data class over the target and the method)"),
    "bindv": ("reason", _BOUND),
    "unbind": ("reason", _BOUND),
    "call": ("member", "call"),
    "call_deferred": ("member", "callDeferred"),
    "rpc": ("member", "rpc"),
    "rpc_id": ("member", "rpcId"),
    "bind": ("reason", _BOUND),
}
SIGNAL_DISPOSITIONS: dict[str, tuple[str, str]] = {
    "is_null": ("reason", "a GodotSignal always names an emitter and a signal: never null"),
    "get_object": ("member", "getObject"),
    "get_object_id": ("member", "getObjectId"),
    "get_name": ("reason", "the `name` property"),
    "connect": ("member", "connect"),
    "disconnect": ("member", "disconnect"),
    "is_connected": ("member", "isConnected"),
    "get_connections": ("member", "getConnections"),
    "has_connections": ("member", "hasConnections"),
    "emit": ("member", "emit"),
}


def member_sources() -> dict[str, tuple[Path, str]]:
    """Godot class -> (file, the `fun` receiver prefix) the gate finds a member's declaration in."""
    return {
        "String": (STRING_KT, "String."),
        "NodePath": (NODE_PATH_KT, ""),
        "PackedByteArray": (BYTES_KT, "ByteArray."),
        "Callable": (CALLABLE_KT, ""),
        "Signal": (SIGNAL_KT, ""),
    }


# ---------------------------------------------------------------------------------------------
# Parity rows of the runtime smoke's probe pair (example_project/BuiltinParitySmoke.kt +
# builtin_parity_ref.gd): `bytes=` hashes every pure byte codec over BYTES_ROUNDS fixed-seed random
# arrays; `text=` every engine-run String / NodePath / PackedByteArray member over fixed samples,
# Unicode outside the BMP included. Each entry is (key, Kotlin lines, GDScript lines); results are
# mixed with `mixText` (a String as its UTF-8 bytes on both sides).
# ---------------------------------------------------------------------------------------------

BYTES_ROUNDS = 64

TEXT_SAMPLES = [
    "res://folder/file.tar.gz",
    "user://a/../b/./c.png",
    "Hello World",
    "ünïcödé ÄÖÜ ß",
    "日本語のテキスト",
    "emoji 🎉👍 text",
    "snake_case_name",
    "camelCaseName",
    "  padded\\t ",
    "-12.5e3",
    "a,b,,c",
    '<tag a=\\"v\\">&amp;</tag>',
    "192.168.0.1",
    "%E3%81%82 x+y",
]
# No empty path: Godot's get_concatenated_names/subnames print an error on one.
NODE_PATH_SAMPLES = ["Path/To:prop:sub", "/root/Main/Player", "../Sibling", "%Unique/Child:position:x", ".", "Node", ":only:sub"]
ASCII_SAMPLES = ["Hello World", "snake_case_name", "a,b,,c", "192.168.0.1"]
STRING_POOL = ["a", "/", "Wörld", "日本", "{0}", "e"]
# Int arguments by Godot parameter name: values valid for every method that takes the name.
INT_POOLS = {
    "at": [0, 1],
    "position": [0, 2],
    "from": [0, 1],
    "to": [0, 5],
    "len": [-1, 3],
    "length": [-2, 3],
    "chars": [1, 2],
    "slice": [0, 1],
    "maxsplit": [0, 1],
    "min_length": [3, 20],
    "digits": [0, 3],
    "delimiter": [ord(","), ord("/")],
    "key": [ord("a"), ord("日")],
    "with": [ord("Z"), 0x1F389],
    "what": [ord("e"), ord(" ")],
    "base": [10, 16, 2],
    "number": [255, -42, 0],
    "size": [1023, 1048576, 123456789],
    "code": [65, 0x263A, 0x1F389],
    "decimals": [-1, 2, 0],
}
FLOAT_POOLS = {"number": [3.14159, -0.5, 1e21, 2.5]}
# Methods whose base must be of a form (else Godot prints an error): base samples of their own.
BASE_OVERRIDES = {
    "hex_decode": ["48656c6c6f", "e697a5", ""],
    "hex_to_int": ["0x1F", "ff", "-0xA"],
    "bin_to_int": ["0b101", "1101", "-0b11"],
    # Godot prints a "cannot represent as ASCII" line per non-ASCII character.
    "to_ascii_buffer": ASCII_SAMPLES,
}
# Methods called with their default arguments only (a pooled value would be invalid).
DEFAULTS_ONLY = {"to_multibyte_char_buffer"}


def _kt_lit(value, godot_type: str) -> str:
    if godot_type in ("String", "StringName"):
        return '"' + value + '"'
    if godot_type == "int":
        return f"{value}L"
    if godot_type == "float":
        return repr(float(value))
    if godot_type == "bool":
        return "true" if value else "false"
    raise SystemExit(f"[builtin_boxed_methods] no parity literal for {godot_type}")


def _gd_lit(value, godot_type: str) -> str:
    if godot_type in ("String", "StringName"):
        return '"' + value + '"'
    if godot_type == "bool":
        return "true" if value else "false"
    if godot_type == "float":
        return repr(float(value))
    return str(value)


def _arg_sets(m: dict) -> list[list[tuple[str, str]]]:
    """Argument lists (Kotlin, GDScript) to call [m] with: each pooled value once, defaults last."""
    args = m.get("arguments", [])
    if m["name"] in DEFAULTS_ONLY:
        args = [a for a in args if "default_value" not in a]
    pools = []
    for a in args:
        t, name = a["type"], a["name"]
        if t in ("String", "StringName"):
            pools.append(STRING_POOL)
        elif t == "int":
            pools.append(INT_POOLS[name])
        elif t == "float":
            pools.append(FLOAT_POOLS[name])
        elif t == "bool":
            pools.append([True, False])
        else:
            raise SystemExit(f"[builtin_boxed_methods] no parity pool for {m['name']}({t} {name})")
    if not pools:
        return [[]]
    width = max(len(p) for p in pools)
    sets = []
    for i in range(width):
        sets.append([(_kt_lit(p[i % len(p)], a["type"]), _gd_lit(p[i % len(p)], a["type"])) for p, a in zip(pools, args)])
    return sets


def text_parity_entries(api: dict) -> list[tuple[str, list[str], list[str]]]:
    entries = []
    samples_kt = "TEXT_SAMPLES"
    for e in string_members(api):
        m = e.godot
        key = f"String.{m['name']}"
        if m["name"] == "format":
            kt = [
                'mixText(KEY, "{0} and {1}".format(listOf<Any?>(1L, "x")))',
                'mixText(KEY, "{name} 🎉".format(mapOf<String, Any?>("name" to "Kanama")))',
                'mixText(KEY, "<_>".format(listOf<Any?>("ü"), "<_>"))',
            ]
            gd = [
                'mix_text(KEY, "{0} and {1}".format([1, "x"]))',
                'mix_text(KEY, "{name} 🎉".format({"name": "Kanama"}))',
                'mix_text(KEY, "<_>".format(["ü"], "<_>"))',
            ]
        elif m.get("is_static", False):
            kt, gd = [], []
            for args in _arg_sets(m):
                kt.append(f"mixText(KEY, String.{e.kn}({', '.join(a for a, _ in args)}))")
                gd.append(f"mix_text(KEY, String.{m['name']}({', '.join(g for _, g in args)}))")
        else:
            bases = BASE_OVERRIDES.get(m["name"])
            source_kt = "listOf(" + ", ".join('"' + b + '"' for b in bases) + ")" if bases else samples_kt
            source_gd = "[" + ", ".join('"' + b + '"' for b in bases) + "]" if bases else "TEXT_SAMPLES"
            kt = [f"for (s in {source_kt}) {{"]
            gd = [f"for s in {source_gd}:"]
            for args in _arg_sets(m):
                kt.append(f"  mixText(KEY, s.{e.kn}({', '.join(a for a, _ in args)}))")
                gd.append(f"\tmix_text(KEY, s.{m['name']}({', '.join(g for _, g in args)}))")
            kt.append("}")
        entries.append((key, [line.replace("KEY", f'"{key}"') for line in kt], [line.replace("KEY", f'"{key}"') for line in gd]))
    for e in node_path_members(api):
        m = e.godot
        key = f"NodePath.{m['name']}"
        if m["name"] in ("get_name", "get_subname"):
            count_kt = "getNameCount" if m["name"] == "get_name" else "getSubnameCount"
            count_gd = "get_name_count" if m["name"] == "get_name" else "get_subname_count"
            kt = ["for (s in NODE_PATH_SAMPLES) {", "  val p = NodePath(s)",
                  f"  for (i in 0 until p.{count_kt}()) {{", f"    mixText(KEY, p.{e.kn}(i))", "  }", "}"]
            gd = ["for s in NODE_PATH_SAMPLES:", "\tvar p := NodePath(s)",
                  f"\tfor i in p.{count_gd}():", f"\t\tmix_text(KEY, p.{m['name']}(i))"]
        elif m["name"] == "slice":
            kt = ["for (s in NODE_PATH_SAMPLES) {", "  val p = NodePath(s)",
                  "  mixText(KEY, p.slice(0L))", "  mixText(KEY, p.slice(1L))", "  mixText(KEY, p.slice(-2L, -1L))", "}"]
            gd = ["for s in NODE_PATH_SAMPLES:", "\tvar p := NodePath(s)",
                  "\tmix_text(KEY, p.slice(0))", "\tmix_text(KEY, p.slice(1))", "\tmix_text(KEY, p.slice(-2, -1))"]
        else:
            kt = ["for (s in NODE_PATH_SAMPLES) {", f"  mixText(KEY, NodePath(s).{e.kn}())", "}"]
            gd = ["for s in NODE_PATH_SAMPLES:", f"\tmix_text(KEY, NodePath(s).{m['name']}())"]
        entries.append((key, [line.replace("KEY", f'"{key}"') for line in kt], [line.replace("KEY", f'"{key}"') for line in gd]))
    for e in bytes_members(api):
        if e.impl != "facade":
            continue
        m = e.godot
        name = m["name"]
        key = f"PackedByteArray.{name}"
        if name.startswith("get_string_from_"):
            enc = name.removeprefix("get_string_from_")
            src_kt = {"ascii": "toAsciiBuffer()", "utf8": "toUtf8Buffer()", "utf16": "toUtf16Buffer()", "utf32": "toUtf32Buffer()",
                      "wchar": "toWcharBuffer()", "multibyte_char": "toMultibyteCharBuffer()"}[enc]
            src_gd = {"ascii": "to_ascii_buffer()", "utf8": "to_utf8_buffer()", "utf16": "to_utf16_buffer()", "utf32": "to_utf32_buffer()",
                      "wchar": "to_wchar_buffer()", "multibyte_char": "to_multibyte_char_buffer()"}[enc]
            source = "ASCII_SAMPLES" if enc == "ascii" else "TEXT_SAMPLES"
            kt = [f"for (s in {source}) {{", f"  mixText(KEY, s.{src_kt}.{e.kn}())", "}"]
            gd = [f"for s in {source}:", f"\tmix_text(KEY, s.{src_gd}.{name}())"]
        elif name == "compress":
            kt = ["for (mode in 0L..3L) {", "  for (s in TEXT_SAMPLES) {", "    mixText(KEY, s.toUtf8Buffer().compress(mode))",
                  "  }", "}"]
            gd = ["for mode in 4:", "\tfor s in TEXT_SAMPLES:", "\t\tmix_text(KEY, s.to_utf8_buffer().compress(mode))"]
        elif name == "decompress":
            kt = ["for (mode in 0L..3L) {", "  for (s in TEXT_SAMPLES) {", "    val raw = s.toUtf8Buffer()",
                  "    mixText(KEY, raw.compress(mode).decompress(raw.size.toLong(), mode))", "  }", "}"]
            gd = ["for mode in 4:", "\tfor s in TEXT_SAMPLES:", "\t\tvar raw: PackedByteArray = s.to_utf8_buffer()",
                  "\t\tmix_text(KEY, raw.compress(mode).decompress(raw.size(), mode))"]
        elif name == "decompress_dynamic":
            kt = ["for (mode in listOf(1L, 3L)) {", "  for (s in TEXT_SAMPLES) {", "    val raw = s.toUtf8Buffer()",
                  "    mixText(KEY, raw.compress(mode).decompressDynamic(raw.size * 2L, mode))", "  }", "}"]
            gd = ["for mode in [1, 3]:", "\tfor s in TEXT_SAMPLES:", "\t\tvar raw: PackedByteArray = s.to_utf8_buffer()",
                  "\t\tmix_text(KEY, raw.compress(mode).decompress_dynamic(raw.size() * 2, mode))"]
        elif name in ("has_encoded_var", "decode_var", "decode_var_size"):
            kt = ['for (v in listOf<Any?>(42L, "ünï 🎉", Vector2(1.5, -2.0), listOf<Any?>(1L, "a"), null)) {',
                  f"  mixText(KEY, GD.varToBytes(v).{e.kn}(0L))",
                  f"  mixText(KEY, (GD.varToBytes(v) + ByteArray(3)).{e.kn}(0L, true))", "}"]
            gd = ['for v in [42, "ünï 🎉", Vector2(1.5, -2.0), [1, "a"], null]:',
                  f"\tmix_text(KEY, var_to_bytes(v).{name}(0))",
                  f"\tmix_text(KEY, (var_to_bytes(v) + PackedByteArray([0, 0, 0])).{name}(0, true))"]
        else:
            raise SystemExit(f"[builtin_boxed_methods] no text parity entry for PackedByteArray.{name}")
        entries.append((key, [line.replace("KEY", f'"{key}"') for line in kt], [line.replace("KEY", f'"{key}"') for line in gd]))
    return entries


def bytes_parity_entries(api: dict) -> list[tuple[str, list[str], list[str]]]:
    """Each pure byte codec on the round's random array `b` (Kotlin ByteArray / GDScript
    PackedByteArray); offsets, counts and values drawn from the shared xorshift."""
    entries = []
    for e in bytes_members(api):
        if e.impl != "pure":
            continue
        name = e.godot["name"]
        key = f"PackedByteArray.{name}"
        kn = e.kn
        if name.startswith("decode_"):
            w = {"8": 1, "16": 2, "32": 4, "64": 8, "half": 2, "float": 4, "double": 8}[name.split("_")[1].lstrip("us")]
            kt = [f"val o = nextRandom() % (b.size - {w} + 1)", f"mixText(KEY, b.{kn}(o))"]
            gd = [f"var o := next_random() % (b.size() - {w} + 1)", f"mix_text(KEY, b.{name}(o))"]
        elif name.startswith("encode_"):
            kind = name.split("_")[1]
            w = {"half": 2, "float": 4, "double": 8}.get(kind) or int(kind.lstrip("us")) // 8
            value_kt = "nv()" if kind in ("half", "float", "double") else "(rl() shl 32) or rl()"
            value_gd = "nv()" if kind in ("half", "float", "double") else "(next_random() << 32) | next_random()"
            kt = ["val c = b.copyOf()", f"val o = nextRandom() % (c.size - {w} + 1)", f"c.{kn}(o, {value_kt})", "mixText(KEY, c)"]
            gd = ["var c := b.duplicate()", f"var o := next_random() % (c.size() - {w} + 1)", f"c.{name}(o, {value_gd})",
                  "mix_text(KEY, c)"]
        elif name.startswith("bswap"):
            w = int(name[5:]) // 8
            kt = ["val c = b.copyOf()", f"val o = nextRandom() % (c.size - {w} + 1)", f"val available = (c.size - o) / {w}",
                  "val count = if (rb()) -1L else nextRandom() % (available + 1)", f"c.{kn}(o, count)", "mixText(KEY, c)"]
            gd = ["var c := b.duplicate()", f"var o := next_random() % (c.size() - {w} + 1)", f"var available := (c.size() - o) / {w}",
                  "var count := -1 if rb() else next_random() % (available + 1)", f"c.{name}(o, count)", "mix_text(KEY, c)"]
        elif name == "hex_encode":
            kt = ["mixText(KEY, b.hexEncode())"]
            gd = ["mix_text(KEY, b.hex_encode())"]
        elif name.startswith("to_") and name.endswith("_array"):
            elem = {"to_int32_array": 4, "to_int64_array": 8, "to_float32_array": 4, "to_float64_array": 8,
                    "to_vector2_array": 8, "to_vector3_array": 12, "to_vector4_array": 16, "to_color_array": 16}[name]
            kt = [f"mixText(KEY, b.copyOf(b.size / {elem} * {elem}).{kn}())"]
            gd = [f"mix_text(KEY, b.slice(0, b.size() / {elem} * {elem}).{name}())"]
        else:
            raise SystemExit(f"[builtin_boxed_methods] no bytes parity entry for {name}")
        entries.append((key, [line.replace("KEY", f'"{key}"') for line in kt], [line.replace("KEY", f'"{key}"') for line in gd]))
    lists = {
        "PackedInt32Array": ("listOf(ri(), ri(), ri())", "PackedInt32Array([ri(), ri(), ri()])"),
        "PackedInt64Array": ("listOf((rl() shl 32) or rl(), rl())", "PackedInt64Array([(next_random() << 32) | next_random(), next_random()])"),
        "PackedFloat32Array": ("listOf(nv().toFloat(), nv().toFloat())", "PackedFloat32Array([nv(), nv()])"),
        "PackedFloat64Array": ("listOf(nv(), nv())", "PackedFloat64Array([nv(), nv()])"),
        "PackedStringArray": ('listOf("ünï", "", "🎉 x")', 'PackedStringArray(["ünï", "", "🎉 x"])'),
        "PackedVector2Array": ("listOf(Vector2(nv(), nv()))", "PackedVector2Array([Vector2(nv(), nv())])"),
        "PackedVector3Array": ("listOf(Vector3(nv(), nv(), nv()))", "PackedVector3Array([Vector3(nv(), nv(), nv())])"),
        "PackedColorArray": ("listOf(Color(nv(), nv(), nv(), nv()))", "PackedColorArray([Color(nv(), nv(), nv(), nv())])"),
        "PackedVector4Array": ("listOf(Vector4(nv(), nv(), nv(), nv()))", "PackedVector4Array([Vector4(nv(), nv(), nv(), nv())])"),
    }
    for cls, (kt_list, gd_list) in lists.items():
        key = f"{cls}.to_byte_array"
        entries.append((key, [f'mixText("{key}", {kt_list}.toByteArray())'], [f'mix_text("{key}", {gd_list}.to_byte_array())']))
    return entries


TEXT_SAMPLES_KT = "listOf(" + ", ".join('"' + s.replace("$", "\\$") + '"' for s in TEXT_SAMPLES) + ")"
TEXT_SAMPLES_GD = "[" + ", ".join('"' + s + '"' for s in TEXT_SAMPLES) + "]"
NODE_PATH_SAMPLES_KT = "listOf(" + ", ".join('"' + s + '"' for s in NODE_PATH_SAMPLES) + ")"
NODE_PATH_SAMPLES_GD = "[" + ", ".join('"' + s + '"' for s in NODE_PATH_SAMPLES) + "]"
ASCII_SAMPLES_KT = "listOf(" + ", ".join('"' + s + '"' for s in ASCII_SAMPLES) + ")"
ASCII_SAMPLES_GD = "[" + ", ".join('"' + s + '"' for s in ASCII_SAMPLES) + "]"
