#!/usr/bin/env python3
"""Audit wrapper MethodBind call helpers against extension_api.json.

`validate_godot_api.py` already checks that a MethodBind hash belongs to the
named Godot method. This script goes one step further: it checks that the
ObjectCalls helper used with that MethodBind has the same coarse argument and
return shape as the selected Godot API method.

The audit is intentionally conservative. It skips broad generic helpers and
Variant-heavy paths it cannot prove yet, but it fails on common wrapper drift:
wrong arity, object vs scalar mistakes, scalar `float` vs `int`, and incorrect
return helpers.
"""

from __future__ import annotations

import argparse
import re
import sys
from dataclasses import dataclass
from pathlib import Path

from wrapper_model import ApiMethod, load_api_method_index, wrapper_source_files


ROOT = Path(__file__).resolve().parents[1]
API_PATH = ROOT / "extension_api.json"
API_DIR = ROOT / "src/main/kotlin/net/multigesture/kanama/api"

CONST_RE = re.compile(
    r"^\s*(?:private\s+)?(?:const\s+)?val\s+([A-Za-z_][A-Za-z0-9_]*)"
    r"(?:[ \t]*:[^\n=]+?)?[ \t]*=[ \t]*(\d+)L?",
    re.MULTILINE,
)
BIND_RE = re.compile(
    r"private\s+val\s+([A-Za-z_][A-Za-z0-9_]*)\s+by\s+lazy\s*\{\s*"
    r"ObjectCalls\.getMethodBind\(\s*"
    r'"([^"]+)"\s*,\s*"([^"]+)"\s*,\s*([A-Z0-9_]+|\d+L?)',
    re.DOTALL,
)
CALL_RE = re.compile(
    r"ObjectCalls\.(ptrcall[A-Za-z0-9_]+)\(\s*([A-Za-z_][A-Za-z0-9_]*)\b",
)
JVM_OBJECT_METHOD_RE = re.compile(
    r"^\s*fun\s+(wait|notify|notifyAll|getClass)\s*\(\s*\)",
    re.MULTILINE,
)
GODOT_CLOSE_METHOD_RE = re.compile(
    r'ObjectCalls\.getMethodBind\(\s*"[^"]+"\s*,\s*"close"',
)
KOTLIN_CLOSE_METHOD_RE = re.compile(
    r"^\s*fun\s+close\s*\(",
    re.MULTILINE,
)
FINAL_GODOT_OBJECT_METHOD_RE = re.compile(
    r"^\s*fun\s+("
    r"getClassName|isClass|getInstanceId|isQueuedForDeletion|propertyCanRevert|"
    r"hasMeta|removeMeta|hasUserSignal|removeUserSignal|hasMethod|"
    r"getMethodArgumentCount|hasSignal|hasConnections|emitSignal|"
    r"setBlockSignals|isBlockingSignals|notifyPropertyListChanged|"
    r"setMessageTranslation|canTranslateMessages|call|setDeferred|setScript|"
    r"toString"
    r")\s*\(",
    re.MULTILINE,
)
GODOT_OBJECT_COLLISION_EXEMPT_FILES = {"GodotObject.kt", "Resource.kt"}

MULTIPLIERS = {
    "Two": 2,
    "Three": 3,
    "Four": 4,
}

TOKEN_KIND = {
    "Void": "void",
    "ConstGDExtensionInitializationFunctionPtr": "raw_pointer",
    "ConstVoidPtr": "raw_pointer",
    "ArrayOfDictionaries": "array",
    "Array": "array",
    "PackedStringArray": "packed_string_array",
    "PackedStringList": "packed_string_array",
    "PackedInt32Array": "packed_int32_array",
    "PackedInt32List": "packed_int32_array",
    "PackedInt64Array": "packed_int64_array",
    "PackedInt64List": "packed_int64_array",
    "PackedFloat32Array": "packed_float32_array",
    "PackedFloat32List": "packed_float32_array",
    "PackedFloat64Array": "packed_float64_array",
    "PackedFloat64List": "packed_float64_array",
    "PackedVector2Array": "packed_vector2_array",
    "PackedVector2List": "packed_vector2_array",
    "PackedVector3Array": "packed_vector3_array",
    "PackedVector3List": "packed_vector3_array",
    "PackedColorArray": "packed_color_array",
    "PackedColorList": "packed_color_array",
    "PackedVector4Array": "packed_vector4_array",
    "PackedVector4List": "packed_vector4_array",
    "ByteArray": "packed_byte_array",
    "ArrayList": "typed_array_array",
    "ByteArrayList": "typed_packed_byte_array",
    "PackedStringListList": "typed_packed_string_array",
    "PackedVector2ListList": "typed_packed_vector2_array",
    "PlaneList": "typed_plane_array",
    "Rect2List": "typed_rect2_array",
    "Transform3DList": "typed_transform3d_array",
    "Vector2List": "typed_vector2_array",
    "Vector3List": "typed_vector3_array",
    "TypedStringList": "typed_string_array",
    "TypedIntList": "typed_int_array",
    "LongList": "typed_int_array",
    "NodePathList": "typed_node_path_array",
    "TypedNodeList": "typed_node_array",
    "TypedNode2DList": "typed_node2d_array",
    "TypedNode3DList": "typed_node3d_array",
    "TypedMaterialList": "typed_material_array",
    "Vector2iList": "typed_vector2i_array",
    "Vector3iList": "typed_vector3i_array",
    "StringNameList": "typed_string_name_array",
    "DictionaryList": "typed_dictionary_array",
    "RIDList": "typed_rid_array",
    "ObjectList": "typed_object_array",
    "StringName": "string_name",
    "NodePath": "node_path",
    "Transform2D": "transform2d",
    "Transform3D": "transform3d",
    "Projection": "projection",
    "Quaternion": "quaternion",
    "Dictionary": "dictionary",
    "Callable": "callable",
    "Variant": "variant",
    "Object": "object",
    "String": "string",
    "Bool": "bool",
    "Long": "int64",
    "UInt32": "uint32",
    "Int": "int32",
    "Double": "float",
    "Float": "float",
    "RID": "rid",
    "Vector3i": "vector3i",
    "Vector2i": "vector2i",
    "Vector4": "vector4",
    "Vector3": "vector3",
    "Vector2": "vector2",
    "Rect2i": "rect2i",
    "Rect2": "rect2",
    "AABB": "aabb",
    "Basis": "basis",
    "Color": "color",
    "Plane": "plane",
}

ORDERED_TOKENS = sorted(TOKEN_KIND, key=len, reverse=True)
TYPED_OBJECT_ARRAY_KINDS = {
    "typed_object_array",
    "typed_node_array",
    "typed_node2d_array",
    "typed_node3d_array",
    "typed_material_array",
    "typed_area2d_array",
    "typed_area3d_array",
    "typed_base_button_array",
    "typed_physics_body3d_array",
}


@dataclass(frozen=True)
class Bind:
    variable: str
    class_name: str
    method_name: str
    method_hash: int
    line: int


@dataclass(frozen=True)
class HelperShape:
    arg_kinds: tuple[str, ...]
    return_kind: str


SPECIAL_HELPER_SHAPES = {
    "ptrcallWithFourLongLongLongLongLongLongArgsRetRID": HelperShape(
        arg_kinds=("int64",) * 10,
        return_kind="rid",
    ),
}


def parse_int_token(token: str, constants: dict[str, int], path: Path) -> int:
    bare = token.removesuffix("L")
    if bare.isdigit():
        return int(bare)
    if bare not in constants:
        raise ValueError(f"{path}: unresolved hash constant {token}")
    return constants[bare]


def constants_for(content: str) -> dict[str, int]:
    return {name: int(value) for name, value in CONST_RE.findall(content)}


def parse_token_sequence(text: str) -> tuple[str, ...] | None:
    if not text or text == "NoArgs":
        return ()

    result: list[str] = []
    pos = 0
    while pos < len(text):
        if text.startswith("And", pos):
            pos += 3
            continue

        multiplier = 1
        for prefix, count in MULTIPLIERS.items():
            if text.startswith(prefix, pos):
                multiplier = count
                pos += len(prefix)
                break

        matched = False
        for token in ORDERED_TOKENS:
            if text.startswith(token, pos):
                result.extend([TOKEN_KIND[token]] * multiplier)
                pos += len(token)
                matched = True
                break
        if not matched:
            return None
    return tuple(result)


def helper_shape(helper: str) -> HelperShape | None:
    if helper in SPECIAL_HELPER_SHAPES:
        return SPECIAL_HELPER_SHAPES[helper]
    if not helper.startswith("ptrcall"):
        return None
    suffix = helper.removeprefix("ptrcall")
    if "Ret" in suffix:
        arg_part, ret_part = suffix.split("Ret", 1)
    else:
        arg_part, ret_part = suffix, "Void"

    if arg_part == "NoArgs":
        args = ()
    elif arg_part.startswith("With") and arg_part.endswith("Args"):
        args = parse_token_sequence(arg_part.removeprefix("With").removesuffix("Args"))
    elif arg_part.startswith("With") and arg_part.endswith("Arg"):
        args = parse_token_sequence(arg_part.removeprefix("With").removesuffix("Arg"))
    else:
        return None

    if args is None:
        return None
    returns = parse_token_sequence(ret_part)
    if returns is None or len(returns) > 1:
        return None
    return HelperShape(arg_kinds=args, return_kind=returns[0] if returns else "void")


def compatible(expected: str, actual: str) -> bool:
    if actual == "variant":
        return True
    if expected == actual:
        return True
    if actual == "typed_object_array" and expected in TYPED_OBJECT_ARRAY_KINDS:
        return True
    if expected == "object" and actual == "object":
        return True
    if expected == "array" and actual in {"array", "packed_string_array"}:
        return True
    if expected in {"enum", "bitfield"} and actual in {"int32", "int64"}:
        return True
    return False


def scan_file(path: Path, api_methods: dict[tuple[str, str, int], ApiMethod]) -> list[str]:
    content = path.read_text(encoding="utf-8")
    constants = constants_for(content)
    errors: list[str] = []
    binds: dict[str, Bind] = {}

    for match in JVM_OBJECT_METHOD_RE.finditer(content):
        line = content.count("\n", 0, match.start()) + 1
        errors.append(
            f"{path.relative_to(ROOT)}:{line}: generated method {match.group(1)}() collides with java.lang.Object",
        )

    if GODOT_CLOSE_METHOD_RE.search(content):
        for match in KOTLIN_CLOSE_METHOD_RE.finditer(content):
            line = content.count("\n", 0, match.start()) + 1
            errors.append(
                f"{path.relative_to(ROOT)}:{line}: Godot close method must not shadow AutoCloseable.close()",
            )

    if path.name not in GODOT_OBJECT_COLLISION_EXEMPT_FILES:
        for match in FINAL_GODOT_OBJECT_METHOD_RE.finditer(content):
            line = content.count("\n", 0, match.start()) + 1
            errors.append(
                f"{path.relative_to(ROOT)}:{line}: generated method {match.group(1)}() "
                "collides with final GodotObject API",
            )

    for match in BIND_RE.finditer(content):
        variable, class_name, method_name, token = match.groups()
        line = content.count("\n", 0, match.start()) + 1
        try:
            method_hash = parse_int_token(token, constants, path)
        except ValueError as e:
            errors.append(str(e))
            continue
        binds[variable] = Bind(variable, class_name, method_name, method_hash, line)

    for match in CALL_RE.finditer(content):
        helper, variable = match.groups()
        if variable not in binds:
            continue
        shape = helper_shape(helper)
        if shape is None:
            continue
        bind = binds[variable]
        api_method = api_methods.get((bind.class_name, bind.method_name, bind.method_hash))
        if api_method is None:
            continue

        line = content.count("\n", 0, match.start()) + 1
        location = f"{path.relative_to(ROOT)}:{line}: {bind.class_name}.{bind.method_name}"
        if len(shape.arg_kinds) != len(api_method.abi_arg_kinds):
            errors.append(
                f"{location} uses {helper} with {len(shape.arg_kinds)} arg(s), "
                f"API has {len(api_method.abi_arg_kinds)}: {api_method.signature}",
            )
            continue

        for index, (expected, actual) in enumerate(zip(api_method.abi_arg_kinds, shape.arg_kinds), start=1):
            if not compatible(expected, actual):
                errors.append(
                    f"{location} arg {index} uses {actual}, API expects {expected}: "
                    f"{api_method.signature}",
                )

        if not compatible(api_method.abi_return_kind, shape.return_kind):
            errors.append(
                f"{location} return uses {shape.return_kind}, API expects {api_method.abi_return_kind}: "
                f"{api_method.signature}",
            )

    return errors


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--api", type=Path, default=API_PATH)
    parser.add_argument("--api-dir", type=Path, default=API_DIR)
    args = parser.parse_args()

    api_methods = load_api_method_index(args.api)
    errors: list[str] = []
    for path in wrapper_source_files(args.api_dir, companions=True):
        errors.extend(scan_file(path, api_methods))

    if errors:
        print("[wrapper_signature_audit] FAIL", file=sys.stderr)
        for error in errors:
            print(f"  - {error}", file=sys.stderr)
        return 1

    print("[wrapper_signature_audit] PASS")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
