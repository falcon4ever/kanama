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
  the components of `Vector2i`/`Vector3i`/`Rect2i`, and no 4-byte scalar cell is allocated,
- scalar Godot `float` helpers use the 64-bit ptrcall float slot,
- `Color` component helpers remain fixed 32-bit component storage,
- a Kanama virtual (`rRet`, the engine's `PtrToArg<m_ret>::EncodeT`) never writes an integer
  return as 4 bytes, on desktop/Android or in the iOS shim, and the shim's own ptrcalls use no
  4-byte integer return cell.

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
# A 4-byte scalar cell anywhere in ObjectCalls (an Int/Float/Short layout allocated alone).
SCALAR_4BYTE_ALLOC_RE = re.compile(
    r"\ballocate\(\s*(?:java\.lang\.foreign\.ValueLayout\.)?(?:JAVA_INT|JAVA_FLOAT|JAVA_SHORT|JAVA_CHAR)\s*\)"
    r"|\ballocate\(\s*[24]L?\s*(?:,\s*[124]L?\s*)?\)",
)
INT_ACCESS_RE = re.compile(r"(?P<cell>[\w.]+)\.(?:set|get)\(\s*(?:java\.lang\.foreign\.ValueLayout\.)?JAVA_INT,\s*(?P<offset>\w+)")
# A bool virtual return is one byte (uint8); an integer or float one never fits in 2 or 4.
VIRTUAL_NARROW_RET_RE = re.compile(
    r"\brRet(?:\.reinterpret\(\s*\w+\s*\))?\.set\(\s*(?:java\.lang\.foreign\.ValueLayout\.)?(?:JAVA_INT|JAVA_FLOAT|JAVA_SHORT)\b"
    r"|\brRet\.reinterpret\(\s*[24]L?\s*\)",
)
SHIM_NARROW_VIRTUAL_RET_RE = re.compile(r"\*\(\(\s*(?:int32_t|uint32_t|int16_t|uint16_t|float)\s*\*\s*\)\s*ret\s*\)\s*=")
SHIM_NARROW_PTRCALL_RET_RE = re.compile(
    r"\b(?:int32_t|uint32_t|int16_t|uint16_t|float)\s+(?P<name>\w+)\s*=[^;]*;\s*\n\s*g_object_method_bind_ptrcall\([^;]*&(?P=name)\s*\)",
)
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
    for cell in scalar_int_cells(helper.body):
        errors.append(
            f"{location} reads or writes `{cell}` as a lone 4-byte JAVA_INT: Godot's ptrcall carries "
            "a scalar int32/uint32 in an 8-byte int64 cell",
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


def scalar_int_cells(body: str) -> list[str]:
    """Cells a helper touches with JAVA_INT only at offset 0: a scalar in a 4-byte slot."""
    offsets: dict[str, set[str]] = {}
    for match in INT_ACCESS_RE.finditer(body):
        offsets.setdefault(match.group("cell"), set()).add(match.group("offset"))
    return sorted(cell for cell, seen in offsets.items() if seen == {"0"})


def audit_scalar_allocations(content: str) -> list[str]:
    errors: list[str] = []
    for match in SCALAR_4BYTE_ALLOC_RE.finditer(content):
        line = content.count("\n", 0, match.start()) + 1
        errors.append(
            f"{OBJECT_CALLS.relative_to(ROOT)}:{line}: allocates a 4-byte scalar cell `{match.group(0)}`; "
            "every scalar int/float ptrcall cell is 8 bytes (JAVA_LONG / JAVA_DOUBLE)",
        )
    return errors


def audit_virtual_returns() -> list[str]:
    errors: list[str] = []
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
        for regex, what in (
            (SHIM_NARROW_VIRTUAL_RET_RE, "a virtual writes a 4-byte integer/float return"),
            (SHIM_NARROW_PTRCALL_RET_RE, "a ptrcall return cell is 4 bytes"),
        ):
            for match in regex.finditer(text):
                line = text.count("\n", 0, match.start()) + 1
                errors.append(
                    f"{IOS_SHIM.relative_to(ROOT)}:{line}: {what}; Godot's ptrcall uses an 8-byte int64/double",
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
        f"intentional_int32_narrowing={narrowing.intentional}",
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
