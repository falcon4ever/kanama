#!/usr/bin/env python3
"""Audit ObjectCalls ptrcall helper bodies for obvious ABI layout drift.

The wrapper signature audit checks that wrappers select helpers whose names
match `extension_api.json`. This audit checks the helper implementations
themselves for impossible primitive slot choices, especially the bugs that are
easy to reintroduce while adding generator shapes:

- every scalar integer slot is a 64-bit cell: Godot's ptrcall encodes `int32`/`uint32` (and
  the smaller ints and enums) as int64 (`PtrToArg<int32_t>` converts through `int64_t`, in
  `core/variant/method_ptrcall.h`), so a 4-byte `JAVA_INT` argument cell is read past its end
  and a 4-byte return cell is overflowed by the engine's 8-byte write; `JAVA_INT` is left for
  the components of `Vector2i`/`Vector3i`/`Rect2i`, and no 4-byte scalar cell is allocated or
  touched alone in any function of the cell files (every body, not only the helpers whose names the
  shape parser reads),
- scalar Godot `float` helpers use the 64-bit ptrcall float slot,
- `Color` component helpers remain fixed 32-bit component storage,
- a Kanama virtual (`rRet`, the engine's `PtrToArg<m_ret>::EncodeT`) never writes an integer
  return as 4 bytes, on desktop/Android or in the iOS shim, and the shim's own ptrcalls use no
  4-byte integer return cell,
- a virtual that returns an Array or Dictionary writes it through `BuiltinTypes.writeVirtualReturn`
  (built into Kanama's own cell, then moved over the engine's default-constructed one, of the
  matching type) and the iOS shim releases the cell right before building it: those constructors
  allocate, so building over the cell leaked on every call.

The float/Color rules absorbed `audit_scalar_float_abi.py` (retired in task 99): that
script name-matched 30 `ptrcall*Float*` helpers, 20 of them packed-array helpers, while
this one checks every helper with a float or Color slot in either direction.
"""

from __future__ import annotations

import re
import sys
from dataclasses import dataclass
from pathlib import Path

from audit_wrapper_signatures import HelperShape, helper_shape


ROOT = Path(__file__).resolve().parents[1]
OBJECT_CALLS = ROOT / "src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt"

FUN_RE = re.compile(r"fun\s+(?P<name>ptrcall[A-Za-z0-9_]*)\s*\(")
ARRAY_ALLOC_RE = re.compile(r"val\s+(?P<name>\w+)\s*=\s*arena\.allocate\(ADDRESS,\s*(?P<count>\d+)\)")
SET_INDEX_RE = re.compile(
    r"(?P<array>\w+)\.setAtIndex\(ADDRESS,\s*(?P<index>\d+),\s*(?P<expr>[^\n)]+)\)",
)
TO_INT_RE = re.compile(r"\.\s*toInt\(\)")

IOS_SHIM = ROOT / "ios/bootstrap/kanama_ios_shim.c"
JVM_BINDING = ROOT / "src/jvmMain/kotlin/binding"

INT64_KINDS = {"int64", "enum", "bitfield", "rid"}
# Scalar ints Godot's ptrcall carries in an int64 cell.
SCALAR_INT32_KINDS = {"int32", "uint32"}
# 32-bit components inside a builtin value: the only legitimate JAVA_INT storage in a helper.
INT_VECTOR_KINDS = {"vector2i", "vector3i", "rect2i"}
# `JAVA_INT`, `ValueLayout.JAVA_INT` or `java.lang.foreign.ValueLayout.JAVA_INT` (and the `_UNALIGNED` forms).
NARROW_LAYOUT_PREFIX = r"(?:java\.lang\.foreign\.)?(?:ValueLayout\.)?"
# A 4-byte scalar cell (an Int/Float/Short layout allocated alone, or one element of it) in a file that
# lays out ptrcall cells. `allocateFrom(JAVA_INT, a, b)` is a vector; with one value it is a scalar.
SCALAR_4BYTE_ALLOC_RE = re.compile(
    r"\ballocate\(\s*" + NARROW_LAYOUT_PREFIX + r"(?:JAVA_INT|JAVA_FLOAT|JAVA_SHORT|JAVA_CHAR)(?:_UNALIGNED)?\s*(?:,\s*1L?\s*)?\)"
    r"|\ballocate\(\s*[24]L?\s*(?:,\s*[124]L?\s*)?\)",
)
ALLOCATE_FROM_RE = re.compile(
    r"\ballocateFrom\(\s*" + NARROW_LAYOUT_PREFIX + r"(?P<layout>(?:JAVA_INT|JAVA_FLOAT|JAVA_SHORT)(?:_UNALIGNED)?)\b",
)
# The files whose functions build ptrcall argument/return cells: every function body in them is scanned,
# not only the helpers whose names the shape parser reads.
CELL_FILES = (
    OBJECT_CALLS,
    ROOT / "src/jvmMain/kotlin/binding/runtime/SignalCallables.kt",
    ROOT / "src/jvmMain/kotlin/binding/runtime/UtilityCalls.kt",
)
NARROW_ACCESS_RE = re.compile(
    r"(?P<cell>[\w.]+)\.(?:set|get)\(\s*" + NARROW_LAYOUT_PREFIX
    + r"(?P<layout>(?:JAVA_INT|JAVA_FLOAT)(?:_UNALIGNED)?),\s*(?P<offset>\w+)",
)
MEMBER_START_RE = re.compile(r"^  (?:/\*\*|@|(?:(?:actual|private|internal|public|inline|override|operator)\s+)*(?:fun|val|var|object|class)\b)")
# A bool virtual return is one byte (uint8); an integer or float one never fits in 2 or 4.
VIRTUAL_NARROW_RET_RE = re.compile(
    r"\brRet(?:\.reinterpret\(\s*\w+\s*\))?\.set\(\s*(?:java\.lang\.foreign\.ValueLayout\.)?(?:JAVA_INT|JAVA_FLOAT|JAVA_SHORT)\b"
    r"|\brRet\.reinterpret\(\s*[24]L?\s*\)",
)
NARROW_C_TYPES = r"(?:int32_t|uint32_t|int16_t|uint16_t|int8_t|float|int|unsigned)"
# `*((int32_t *)ret) = ...` or `*(int32_t *)ret = ...` in a virtual: the return is an 8-byte EncodeT.
SHIM_NARROW_VIRTUAL_RET_RE = re.compile(r"\*\s*\(+\s*" + NARROW_C_TYPES + r"\s*\*\s*\)\s*\(?\s*(?:r_)?ret\s*\)*\s*=")
# A ptrcall whose return argument is the address of a local: the local's declaration in the same function.
SHIM_PTRCALL_ADDR_RET_RE = re.compile(r"\bg_object_method_bind_ptrcall\((?P<args>[^;]*?)&\s*(?P<name>\w+)\s*\)\s*;")
ARRAY_KINDS = {
    "array",
    "typed_object_array",
    "typed_string_array",
    "typed_int_array",
    "typed_node_path_array",
    "typed_array_array",
    "typed_packed_byte_array",
    "typed_packed_string_array",
    "typed_packed_vector2_array",
    "typed_plane_array",
    "typed_projection_array",
    "typed_rect2_array",
    "typed_transform3d_array",
    "typed_vector2_array",
    "typed_vector3_array",
    "typed_node_array",
    "typed_node2d_array",
    "typed_node3d_array",
    "typed_material_array",
    "typed_area2d_array",
    "typed_area3d_array",
    "typed_base_button_array",
    "typed_physics_body3d_array",
    "typed_vector2i_array",
    "typed_vector3i_array",
    "typed_string_name_array",
    "typed_dictionary_array",
    "typed_rid_array",
}
PACKED_KINDS = {
    "packed_byte_array",
    "packed_string_array",
    "packed_int32_array",
    "packed_int64_array",
    "packed_float32_array",
    "packed_float64_array",
    "packed_vector2_array",
    "packed_vector3_array",
    "packed_color_array",
    "packed_vector4_array",
}


@dataclass(frozen=True)
class PtrcallHelper:
    name: str
    line: int
    shape: HelperShape
    body: str


@dataclass(frozen=True)
class SlotAuditResult:
    errors: tuple[str, ...]
    audited: int
    skipped: int


@dataclass(frozen=True)
class ExpressionHelper:
    name: str
    line: int
    shape: HelperShape
    expression: str


@dataclass(frozen=True)
class NarrowingAuditResult:
    errors: tuple[str, ...]
    intentional: int


def find_function_end(content: str, start: int) -> int:
    brace = content.find("{", start)
    if brace == -1:
        return len(content)

    depth = 0
    in_string = False
    escaped = False
    for index in range(brace, len(content)):
        char = content[index]
        if in_string:
            if escaped:
                escaped = False
            elif char == "\\":
                escaped = True
            elif char == '"':
                in_string = False
            continue
        if char == '"':
            in_string = True
        elif char == "{":
            depth += 1
        elif char == "}":
            depth -= 1
            if depth == 0:
                return index + 1
    return len(content)


def find_signature_end(content: str, start: int) -> int:
    depth = 1
    in_string = False
    escaped = False
    for index in range(start, len(content)):
        char = content[index]
        if in_string:
            if escaped:
                escaped = False
            elif char == "\\":
                escaped = True
            elif char == '"':
                in_string = False
            continue
        if char == '"':
            in_string = True
        elif char == "(":
            depth += 1
        elif char == ")":
            depth -= 1
            if depth == 0:
                return index + 1
    return len(content)


def scan_helpers(content: str) -> tuple[list[PtrcallHelper], list[tuple[str, int]]]:
    helpers: list[PtrcallHelper] = []
    skipped: list[tuple[str, int]] = []
    for match in FUN_RE.finditer(content):
        name = match.group("name")
        line = content.count("\n", 0, match.start()) + 1
        shape = helper_shape(name)
        if shape is None:
            skipped.append((name, line))
            continue
        signature_end = find_signature_end(content, match.end())
        next_equals = content.find("=", signature_end)
        next_brace = content.find("{", signature_end)
        if next_equals != -1 and (next_brace == -1 or next_equals < next_brace):
            skipped.append((name, line))
            continue
        body = content[match.start() : find_function_end(content, signature_end)]
        helpers.append(PtrcallHelper(name, line, shape, body))
    return helpers, skipped


def scan_expression_helpers(content: str) -> list[ExpressionHelper]:
    helpers: list[ExpressionHelper] = []
    for match in FUN_RE.finditer(content):
        name = match.group("name")
        shape = helper_shape(name)
        if shape is None:
            continue
        signature_end = find_signature_end(content, match.end())
        next_equals = content.find("=", signature_end)
        next_brace = content.find("{", signature_end)
        if next_equals == -1 or (next_brace != -1 and next_brace < next_equals):
            continue
        expression_end = content.find("\n", next_equals)
        if expression_end == -1:
            expression_end = len(content)
        line = content.count("\n", 0, match.start()) + 1
        expression = content[next_equals + 1 : expression_end].strip()
        helpers.append(ExpressionHelper(name, line, shape, expression))
    return helpers


def uses_java_long(body: str) -> bool:
    return bool(re.search(r"\bJAVA_LONG\b", body))


def uses_java_int(body: str) -> bool:
    return bool(re.search(r"\bJAVA_INT\b", body))


def uses_java_double(body: str) -> bool:
    return bool(re.search(r"\bJAVA_DOUBLE\b|\bValueLayout\.JAVA_DOUBLE\b", body))


def uses_java_float(body: str) -> bool:
    return bool(re.search(r"\bJAVA_FLOAT\b|\bValueLayout\.JAVA_FLOAT\b", body))


def audit_helper(helper: PtrcallHelper) -> list[str]:
    kinds = set(helper.shape.arg_kinds)
    kinds.add(helper.shape.return_kind)

    expects_long = bool(kinds & (INT64_KINDS | SCALAR_INT32_KINDS))
    expects_int_vector = bool(kinds & INT_VECTOR_KINDS)
    expects_float = "float" in kinds
    expects_color = "color" in kinds

    errors: list[str] = []
    location = f"{OBJECT_CALLS.relative_to(ROOT)}:{helper.line}: {helper.name}"

    if uses_java_long(helper.body) and not expects_long:
        errors.append(f"{location} uses JAVA_LONG but helper shape has no integer/enum/bitfield/RID slot")
    if uses_java_int(helper.body) and not expects_int_vector:
        errors.append(
            f"{location} uses JAVA_INT but helper shape has no Vector2i/Vector3i/Rect2i slot "
            "(a scalar int32/uint32 ptrcall slot is an int64 cell)",
        )
    if uses_java_float(helper.body) and not expects_color:
        errors.append(f"{location} uses JAVA_FLOAT outside Color component storage")

    if (kinds & SCALAR_INT32_KINDS) and not uses_java_long(helper.body):
        errors.append(f"{location} has an int32/uint32 slot but does not use a JAVA_LONG (int64) cell")
    if "uint32" in helper.shape.arg_kinds and "BuiltinTypes.requireUInt32(" not in helper.body:
        errors.append(f"{location} has a uint32 arg slot but does not range-check with BuiltinTypes.requireUInt32")
    if (kinds & INT64_KINDS) and not uses_java_long(helper.body):
        errors.append(f"{location} has an int64/enum/bitfield/RID slot but does not use JAVA_LONG")
    if expects_float and not uses_java_double(helper.body):
        errors.append(f"{location} has a scalar float slot but does not use JAVA_DOUBLE")
    if expects_color and not uses_java_float(helper.body):
        errors.append(f"{location} has a Color slot but does not use JAVA_FLOAT component storage")

    return errors


def call_arguments(text: str, open_paren: int) -> list[str]:
    """The top-level comma-separated arguments of the call whose `(` is at [open_paren]."""
    depth = 0
    args: list[str] = []
    start = open_paren + 1
    for index in range(open_paren, len(text)):
        char = text[index]
        if char in "([{":
            depth += 1
        elif char in ")]}":
            depth -= 1
            if depth == 0:
                args.append(text[start:index].strip())
                return args
        elif char == "," and depth == 1:
            args.append(text[start:index].strip())
            start = index + 1
    return args


def member_chunks(content: str) -> list[tuple[int, str]]:
    """The object's members (2-space indent declarations) as (first line, text); a nested class's
    methods stay in its chunk."""
    lines = content.split("\n")
    starts = [index for index, line in enumerate(lines) if MEMBER_START_RE.match(line)]
    starts.append(len(lines))
    return [(start + 1, "\n".join(lines[start:end])) for start, end in zip(starts, starts[1:])]


def lone_narrow_cells(chunk: str) -> list[tuple[str, str]]:
    """(cell, layout) pairs a member touches with JAVA_INT/JAVA_FLOAT only at offset 0: a scalar in a
    4-byte slot (a Vector2i/Rect2i/Color touches its other components too)."""
    offsets: dict[tuple[str, str], set[str]] = {}
    for match in NARROW_ACCESS_RE.finditer(chunk):
        offset = re.sub(r"^0+[lL]?$", "0", match.group("offset"))
        offsets.setdefault((match.group("cell"), match.group("layout")), set()).add(offset)
    return sorted(key for key, seen in offsets.items() if seen == {"0"})


def audit_scalar_allocations(content: str) -> list[str]:
    """Every function body of the cell files, parsed helper or not."""
    errors: list[str] = []
    for path in CELL_FILES:
        text = content if path == OBJECT_CALLS else path.read_text(encoding="utf-8")
        rel = path.relative_to(ROOT)
        for match in SCALAR_4BYTE_ALLOC_RE.finditer(text):
            line = text.count("\n", 0, match.start()) + 1
            errors.append(
                f"{rel}:{line}: allocates a 4-byte scalar cell `{match.group(0)}`; "
                "every scalar int/float ptrcall cell is 8 bytes (JAVA_LONG / JAVA_DOUBLE)",
            )
        for match in ALLOCATE_FROM_RE.finditer(text):
            if len(call_arguments(text, text.index("(", match.start()))) == 2:
                line = text.count("\n", 0, match.start()) + 1
                errors.append(
                    f"{rel}:{line}: allocates a 4-byte scalar cell with `allocateFrom({match.group('layout')}, value)`; "
                    "every scalar int/float ptrcall cell is 8 bytes (JAVA_LONG / JAVA_DOUBLE)",
                )
        for first_line, chunk in member_chunks(text):
            name_match = re.search(r"\bfun\s+(\w+)", chunk)
            name = name_match.group(1) if name_match else "<member>"
            for cell, layout in lone_narrow_cells(chunk):
                escaped = re.escape(cell)
                passed = bool(
                    re.search(rf"setAtIndex\(\s*ADDRESS,\s*[^,]+,\s*{escaped}\s*\)", chunk)
                    or re.search(rf"bindPtrcall\([^)]*\b{escaped}\s*\)", chunk)
                )
                use = re.search(rf"{escaped}\.(?:set|get)\(\s*{NARROW_LAYOUT_PREFIX}{layout}", chunk)
                line = first_line + (chunk.count("\n", 0, use.start()) if use else 0)
                where = " (it is passed to the ptrcall)" if passed else ""
                errors.append(
                    f"{rel}:{line}: {name} reads or writes `{cell}` as a lone 4-byte {layout}{where}: Godot's "
                    "ptrcall carries a scalar int32/uint32 in an 8-byte int64 cell and a float in a double",
                )
    return errors


def audit_shim_ptrcall_returns(text: str) -> list[tuple[int, str]]:
    """Shim ptrcalls whose return is the address of a 4-byte local declared anywhere earlier in the same
    function (an array local is component storage and is left alone)."""
    found: list[tuple[int, str]] = []
    for match in SHIM_PTRCALL_ADDR_RET_RE.finditer(text):
        function_start = text.rfind("\n}", 0, match.start())
        scope = text[function_start + 1 if function_start != -1 else 0 : match.start()]
        name = re.escape(match.group("name"))
        if re.search(rf"\b{NARROW_C_TYPES}\s+{name}\s*(?:=|;)", scope):
            line = text.count("\n", 0, match.start()) + 1
            found.append((line, match.group("name")))
    return found


VIRTUAL_FUN_RE = re.compile(r"\bfun\s+(?P<name>\w+)\([^)]*\brRet:\s*MemorySegment")
VARIANT_TYPE = r"(?:[\w.]+\.)?VariantType\.(?P<kind>ARRAY|DICTIONARY)"
# The only way a virtual may write an Array/Dictionary return: built into Kanama's own cell, then moved
# over Godot's default-constructed one (BuiltinTypes.writeVirtualReturn).
WRAPPED_WRITE_RE = re.compile(r"\bwriteVirtualReturn\(\s*" + VARIANT_TYPE + r"\s*,\s*(?P<cell>\w+)\s*,?\s*\)\s*\{")
# Writers whose return is a Variant (Godot's NIL Variant needs no release): the generated registrar's
# `writeRpcConfig` (`_get_rpc_config`) and `writePropertyDefault` (`_get_property_default_value`).
VARIANT_RET_WRITERS = {"writeRpcConfig", "writePropertyDefault", "writeVirtualReturn", "writeCopyTo"}
# Container writers and the kind they build, for a write inside a writeVirtualReturn block.
WRITER_KINDS = {
    "writeScriptMethodList": "ARRAY",
    "writeScriptPropertyList": "ARRAY",
    "writeScriptSignalList": "ARRAY",
    "writeMethodInfo": "DICTIONARY",
}
VIRTUAL_CONTAINER_WRITES: list[int] = []  # the count the last run read, for the PASS line
SHIM_CONTAINER_RET_RE = re.compile(r"kanama_ios_init_empty_(?P<kind>array|dictionary)\(\s*ret\s*\)")


def container_writes(body: str, cell: str) -> list[tuple[int, str]]:
    """(offset, kind) of every Array/Dictionary write into [cell] in [body]; kind is ARRAY, DICTIONARY
    or "?" for a writer whose kind this audit does not know."""
    escaped = re.escape(cell)
    found: list[tuple[int, str]] = []
    for match in re.finditer(r"\bconstruct\(\s*" + VARIANT_TYPE + rf"\s*,\s*{escaped}\b", body):
        found.append((match.start(), match.group("kind")))
    for match in re.finditer(rf"\binit(?P<what>Dictionary|Array\w*)\(\s*{escaped}\b", body):
        found.append((match.start(), "DICTIONARY" if match.group("what") == "Dictionary" else "ARRAY"))
    for match in re.finditer(rf"\b(?P<writer>write\w*)\((?P<args>[^()]*(?:\([^()]*\)[^()]*)*)\)", body):
        args = [arg.strip() for arg in match.group("args").split(",")]
        if match.group("writer") in VARIANT_RET_WRITERS or cell not in args:
            continue
        found.append((match.start(), WRITER_KINDS.get(match.group("writer"), "?")))
    for match in re.finditer(r"\bdestroyTyped\(\s*" + VARIANT_TYPE + rf"\s*,\s*{escaped}\b", body):
        found.append((match.start(), "destroy"))
    return sorted(found)


BUILTIN_TYPES = ROOT / "src/jvmMain/kotlin/binding/runtime/BuiltinTypes.kt"


def audit_write_virtual_return_order() -> list[str]:
    """writeVirtualReturn itself: the build runs first, then Godot's value is destroyed, then the built
    pointer moves in. Destroying before the build is the use-after-free the helper exists to prevent."""
    rel = BUILTIN_TYPES.relative_to(ROOT)
    text = BUILTIN_TYPES.read_text(encoding="utf-8")
    match = re.search(r"\bfun\s+writeVirtualReturn\(", text)
    if match is None:
        return [f"{rel}: no `fun writeVirtualReturn(`: the container-return check has no helper to rely on"]
    body = text[match.start() : find_function_end(text, match.end())]
    line = text.count("\n", 0, match.start()) + 1
    steps = {
        "build(built)": re.search(r"^\s*build\(\s*built\s*\)", body, re.MULTILINE),
        "destroyTyped(type, rRet)": re.search(r"^\s*destroyTyped\(\s*type\s*,\s*rRet\s*\)", body, re.MULTILINE),
        "MemorySegment.copy(built, ..., rRet...)": re.search(r"^\s*MemorySegment\.copy\(\s*built\b[^\n]*\brRet\b", body, re.MULTILINE),
    }
    missing = [name for name, found in steps.items() if found is None]
    if missing:
        return [f"{rel}:{line}: writeVirtualReturn lacks {', '.join(missing)}"]
    order = [name for name, _ in sorted(steps.items(), key=lambda item: item[1].start())]
    if order != list(steps):
        return [
            f"{rel}:{line}: writeVirtualReturn runs {' -> '.join(order)}; it must build first, then destroy "
            "Godot's value, then move the built pointer in (a destroy before the build hands Godot freed "
            "storage when the build throws)",
        ]
    return []


def audit_virtual_container_returns() -> list[str]:
    """A virtual's Array/Dictionary return cell arrives default-constructed (`PtrToArg<m_ret>::EncodeT ret;`,
    whose constructor allocates). Building over it leaks that allocation on every call, and destroying it
    before a build that can throw leaves Godot a freed value to read and destroy again: a virtual writes
    such a return only through `BuiltinTypes.writeVirtualReturn(VariantType.<kind>, rRet) { ... }`, whose
    block builds the matching kind into Kanama's own cell."""
    errors: list[str] = []
    wrapped_total = 0
    for path in sorted(JVM_BINDING.rglob("*.kt")):
        text = path.read_text(encoding="utf-8")
        rel = path.relative_to(ROOT)
        wrapped_total += len(WRAPPED_WRITE_RE.findall(text))
        for match in VIRTUAL_FUN_RE.finditer(text):
            fun_start = match.start()
            body = text[fun_start : find_function_end(text, match.end())]
            name = match.group("name")
            if name == "writeVirtualReturn":  # the helper itself (BuiltinTypes)
                continue
            cells = {"rRet"} | set(re.findall(r"\bva[lr]\s+(\w+)\s*=\s*rRet\b", body))
            calls = len(re.findall(r"\bwriteVirtualReturn\(", body))
            if calls != len(WRAPPED_WRITE_RE.findall(body)):
                line = text.count("\n", 0, fun_start) + 1
                errors.append(
                    f"{rel}:{line}: {name} calls writeVirtualReturn in a form this audit cannot read "
                    "(`writeVirtualReturn(VariantType.<kind>, rRet) { ... }`)",
                )
            for wrapped in WRAPPED_WRITE_RE.finditer(body):
                if wrapped.group("cell") not in cells:
                    continue
                block_end = find_function_end(body, wrapped.end() - 1)
                block = body[wrapped.end() : block_end]
                param = re.match(r"\s*(\w+)\s*->", block)
                inner = param.group(1) if param else "it"
                kinds = {kind for _, kind in container_writes(block, inner)}
                line = text.count("\n", 0, fun_start + wrapped.start()) + 1
                if not kinds:
                    errors.append(f"{rel}:{line}: {name}'s writeVirtualReturn block builds nothing into `{inner}`")
                for kind in sorted(kinds - {wrapped.group("kind")}):
                    errors.append(
                        f"{rel}:{line}: {name} declares writeVirtualReturn(VariantType.{wrapped.group('kind')}) but its "
                        f"block builds {kind}: the engine's cell would be destroyed as the wrong type",
                    )
            for cell in sorted(cells):
                for offset, kind in container_writes(body, cell):
                    line = text.count("\n", 0, fun_start + offset) + 1
                    what = "destroys" if kind == "destroy" else "builds an Array/Dictionary into"
                    errors.append(
                        f"{rel}:{line}: {name} {what} the engine's default-constructed return cell `{cell}` directly; "
                        "use BuiltinTypes.writeVirtualReturn (building over it leaks, a release before a build that "
                        "can throw hands Godot freed storage)",
                    )
    if wrapped_total == 0:
        errors.append(f"{JVM_BINDING.relative_to(ROOT)}: no writeVirtualReturn block found: the container-return check read nothing")
    VIRTUAL_CONTAINER_WRITES.append(wrapped_total)
    if IOS_SHIM.exists():
        text = IOS_SHIM.read_text(encoding="utf-8")
        for match in SHIM_CONTAINER_RET_RE.finditer(text):
            line_start = text.rfind("\n", 0, match.start())
            previous = text[text.rfind("\n", 0, line_start) + 1 : line_start]
            kind = match.group("kind")
            if not re.fullmatch(rf"\s*kanama_ios_release_virtual_{kind}_return\(\s*ret\s*\);\s*", previous):
                line = text.count("\n", 0, match.start()) + 1
                errors.append(
                    f"{IOS_SHIM.relative_to(ROOT)}:{line}: a virtual builds an empty {kind} over the engine's "
                    f"default-constructed return cell without a kanama_ios_release_virtual_{kind}_return(ret); "
                    "statement right before it (leaks the cell's storage on every call)",
                )
    return errors


def audit_virtual_returns() -> list[str]:
    errors: list[str] = audit_write_virtual_return_order() + audit_virtual_container_returns()
    for path in sorted(JVM_BINDING.rglob("*.kt")):
        text = path.read_text(encoding="utf-8")
        for match in VIRTUAL_NARROW_RET_RE.finditer(text):
            line = text.count("\n", 0, match.start()) + 1
            errors.append(
                f"{path.relative_to(ROOT)}:{line}: a virtual writes its return through `{match.group(0)}`; "
                "an int/enum/Error virtual return is an 8-byte int64 cell (PtrToArg<m_ret>::EncodeT)",
            )
    if IOS_SHIM.exists():
        text = IOS_SHIM.read_text(encoding="utf-8")
        for match in SHIM_NARROW_VIRTUAL_RET_RE.finditer(text):
            line = text.count("\n", 0, match.start()) + 1
            errors.append(
                f"{IOS_SHIM.relative_to(ROOT)}:{line}: a virtual writes a 4-byte integer/float return; "
                "Godot's ptrcall uses an 8-byte int64/double",
            )
        for line, name in audit_shim_ptrcall_returns(text):
            errors.append(
                f"{IOS_SHIM.relative_to(ROOT)}:{line}: a ptrcall return cell is 4 bytes (`{name}`); "
                "Godot's ptrcall uses an 8-byte int64/double",
            )
    return errors


def audit_narrowing(
    helpers: list[PtrcallHelper],
    expression_helpers: list[ExpressionHelper],
) -> NarrowingAuditResult:
    errors: list[str] = []
    intentional = 0

    for helper in helpers:
        narrowing_count = len(TO_INT_RE.findall(helper.body))
        if narrowing_count == 0:
            continue
        location = f"{OBJECT_CALLS.relative_to(ROOT)}:{helper.line}: {helper.name}"
        if "int32" in helper.shape.arg_kinds or helper.shape.return_kind == "int32":
            intentional += narrowing_count
        else:
            errors.append(
                f"{location} narrows with toInt() but helper shape has no explicit int32 argument or return",
            )

    for helper in expression_helpers:
        narrowing_count = len(TO_INT_RE.findall(helper.expression))
        if narrowing_count == 0:
            continue
        location = f"{OBJECT_CALLS.relative_to(ROOT)}:{helper.line}: {helper.name}"
        if "int32" in helper.shape.arg_kinds:
            intentional += narrowing_count
        else:
            errors.append(
                f"{location} expression narrows with toInt() but helper shape has no explicit int32 argument",
            )

    return NarrowingAuditResult(tuple(errors), intentional=intentional)


def extract_arg_array(body: str, expected_count: int) -> tuple[str, dict[int, str]] | None:
    if expected_count == 0:
        return None

    arrays = {
        match.group("name"): int(match.group("count"))
        for match in ARRAY_ALLOC_RE.finditer(body)
        if int(match.group("count")) == expected_count
    }
    if not arrays:
        return None

    assignments: dict[str, dict[int, str]] = {name: {} for name in arrays}
    for match in SET_INDEX_RE.finditer(body):
        array_name = match.group("array")
        if array_name not in assignments:
            continue
        index = int(match.group("index"))
        if index >= expected_count:
            continue
        assignments[array_name][index] = match.group("expr").strip()

    complete = [
        (name, slots)
        for name, slots in assignments.items()
        if set(slots) == set(range(expected_count))
    ]
    if len(complete) != 1:
        return None
    return complete[0]


def variable_layouts(body: str, name: str) -> set[str]:
    layouts: set[str] = set()

    escaped = re.escape(name)
    allocation = re.search(rf"val\s+{escaped}\s*=\s*(?P<expr>[^\n]+)", body)
    allocation_expr = allocation.group("expr") if allocation else ""

    if "BuiltinTypes.allocatePackedArray(arena)" in allocation_expr:
        if re.search(rf"initPackedByteArray\(\s*{escaped}\b", body):
            layouts.add("packed_byte_array")
        if re.search(rf"initPackedStringArray\(\s*{escaped}\b", body):
            layouts.add("packed_string_array")
        if re.search(rf"initPackedInt32Array\(\s*{escaped}\b", body):
            layouts.add("packed_int32_array")
        if re.search(rf"initPackedInt64Array\(\s*{escaped}\b", body):
            layouts.add("packed_int64_array")
        if re.search(rf"initPackedFloat32Array\(\s*{escaped}\b", body):
            layouts.add("packed_float32_array")
        if re.search(rf"initPackedFloat64Array\(\s*{escaped}\b", body):
            layouts.add("packed_float64_array")
        if re.search(rf"initPackedVector2Array\(\s*{escaped}\b", body):
            layouts.add("packed_vector2_array")
        if re.search(rf"initPackedVector3Array\(\s*{escaped}\b", body):
            layouts.add("packed_vector3_array")
        if re.search(rf"initPackedColorArray\(\s*{escaped}\b", body):
            layouts.add("packed_color_array")
        if re.search(rf"initPackedVector4Array\(\s*{escaped}\b", body):
            layouts.add("packed_vector4_array")
        if not layouts:
            layouts.add("packed_array")
        return layouts

    if "arena.allocate(ADDRESS)" in allocation_expr:
        if re.search(rf"{escaped}\.set\(ADDRESS,\s*0,\s*", body):
            layouts.add("object")
            layouts.add("address")
        return layouts

    if "arena.allocate(8L, 8L)" in allocation_expr:
        if re.search(rf"GodotStrings\.initString\(\s*{escaped}\b", body):
            layouts.add("string")
        if re.search(rf"BuiltinTypes\.construct\(\s*type\s*=\s*VariantType\.NODE_PATH,\s*dest\s*=\s*{escaped}\b", body, re.DOTALL):
            layouts.add("node_path")
        if re.search(rf"BuiltinTypes\.initDictionary\(\s*{escaped}\b", body):
            layouts.add("dictionary")
        if re.search(rf"BuiltinTypes\.initArray(?:Of[A-Za-z0-9]+)?\(\s*{escaped}\b", body):
            layouts.add("array")
        return layouts

    if re.search(rf"{escaped}\.set\(\s*(?:java\.lang\.foreign\.ValueLayout\.)?JAVA_BYTE\b", body):
        layouts.add("bool")
    if re.search(rf"{escaped}\.set\(\s*(?:java\.lang\.foreign\.ValueLayout\.)?JAVA_DOUBLE\b", body):
        layouts.add("float")
    if re.search(rf"{escaped}\.set\(\s*(?:java\.lang\.foreign\.ValueLayout\.)?JAVA_LONG\b", body):
        layouts.add("int64")
    if re.search(rf"{escaped}\.set\(\s*(?:java\.lang\.foreign\.ValueLayout\.)?JAVA_FLOAT\b", body):
        layouts.add("color")

    int_offsets = {
        int(match.group("offset"))
        for match in re.finditer(rf"{escaped}\.set\(\s*JAVA_INT,\s*(?P<offset>\d+),", body)
    }
    if int_offsets == {0, 4}:
        layouts.add("vector2i")
    elif int_offsets == {0, 4, 8}:
        layouts.add("vector3i")
    elif int_offsets == {0, 4, 8, 12}:
        layouts.add("rect2i")
    elif 0 in int_offsets:
        # A lone 4-byte int: no ptrcall slot has this layout (scalar ints are int64 cells).
        layouts.add("int32_4byte")

    real_indices = {
        int(match.group("index"))
        for match in re.finditer(rf"GodotReal\.writeIndex\(\s*{escaped},\s*(?P<index>\d+),", body)
    }
    if real_indices == {0, 1}:
        layouts.add("vector2")
    elif real_indices == {0, 1, 2}:
        layouts.add("vector3")
    elif real_indices == {0, 1, 2, 3}:
        if re.search(rf"\b(?:q|quat|quaternion)\w*\s*=\s*{escaped}\b|\b{escaped}\b.*quaternion", body, re.IGNORECASE):
            layouts.add("quaternion")
        else:
            layouts.update({"plane", "quaternion", "vector4"})
    elif real_indices == set(range(6)):
        layouts.add("transform2d")
    elif real_indices == set(range(12)):
        layouts.add("transform3d")
    elif real_indices == set(range(16)):
        layouts.add("projection")

    if "arena.allocate(16L, 4L)" in allocation_expr and "color" not in layouts and "rect2i" not in layouts:
        if re.search(rf"{escaped}\.set\(\s*JAVA_FLOAT,", body):
            layouts.add("color")

    return layouts


def expression_layouts(body: str, expression: str) -> set[str]:
    if expression.startswith("GodotStrings.makeStringName("):
        return {"string_name"}
    if expression == "MemorySegment.NULL":
        return {"object"}
    if re.fullmatch(r"\w+", expression):
        return variable_layouts(body, expression)
    return set()


def slot_compatible(expected: str, layouts: set[str]) -> bool:
    if not layouts:
        return True
    if expected in layouts:
        return True
    if expected in SCALAR_INT32_KINDS and "int64" in layouts:
        return True
    if expected in {"enum", "bitfield", "rid"} and "int64" in layouts:
        return True
    if expected == "int64" and "int64" in layouts:
        return True
    if expected == "raw_pointer" and "address" in layouts:
        return True
    if expected in ARRAY_KINDS and "array" in layouts:
        return True
    if expected in PACKED_KINDS and ("packed_array" in layouts or expected in layouts):
        return True
    return False


def audit_helper_slots(helper: PtrcallHelper) -> SlotAuditResult:
    arg_kinds = helper.shape.arg_kinds
    if not arg_kinds:
        return SlotAuditResult((), audited=0, skipped=0)

    extracted = extract_arg_array(helper.body, len(arg_kinds))
    if extracted is None:
        return SlotAuditResult((), audited=0, skipped=len(arg_kinds))

    _array_name, slots = extracted
    errors: list[str] = []
    location = f"{OBJECT_CALLS.relative_to(ROOT)}:{helper.line}: {helper.name}"
    audited = 0
    skipped = 0
    for index, expected in enumerate(arg_kinds):
        expression = slots[index]
        layouts = expression_layouts(helper.body, expression)
        if not layouts:
            skipped += 1
            continue
        audited += 1
        if not slot_compatible(expected, layouts):
            actual = ", ".join(sorted(layouts)) if layouts else "unknown"
            errors.append(
                f"{location} arg {index + 1} expects {expected} but slot {index} "
                f"uses {actual} via `{expression}`",
            )
    return SlotAuditResult(tuple(errors), audited=audited, skipped=skipped)


def main() -> int:
    content = OBJECT_CALLS.read_text(encoding="utf-8")
    helpers, skipped = scan_helpers(content)
    expression_helpers = scan_expression_helpers(content)
    errors: list[str] = []
    slot_audited = 0
    slot_skipped = 0
    for helper in helpers:
        errors.extend(audit_helper(helper))
        result = audit_helper_slots(helper)
        errors.extend(result.errors)
        slot_audited += result.audited
        slot_skipped += result.skipped
    narrowing = audit_narrowing(helpers, expression_helpers)
    errors.extend(narrowing.errors)
    errors.extend(audit_scalar_allocations(content))
    errors.extend(audit_virtual_returns())

    if errors:
        print("[ptrcall_helper_layout_audit] FAIL", file=sys.stderr)
        for error in errors:
            print(f"  - {error}", file=sys.stderr)
        return 1

    print(
        "[ptrcall_helper_layout_audit] PASS "
        f"checked={len(helpers)} skipped_unparsed={len(skipped)} "
        f"slot_checked={slot_audited} slot_skipped={slot_skipped} "
        f"intentional_int32_narrowing={narrowing.intentional} "
        f"virtual_container_returns={sum(VIRTUAL_CONTAINER_WRITES)}",
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
