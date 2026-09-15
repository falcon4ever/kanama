#!/usr/bin/env python3
"""Validate hand-written Godot ABI constants against extension_api.json.

This is intentionally a guardrail, not a generator. It catches common version
upgrade mistakes while Kanama still has hand-written wrappers and hashes.
"""

from __future__ import annotations

import argparse
import json
import re
import sys
from pathlib import Path


CONST_RE = re.compile(
    r"^\s*(?:private\s+)?(?:const\s+)?val\s+([A-Za-z_][A-Za-z0-9_]*)"
    r"(?:\s*:\s*[^\n=]+?)?\s*=\s*(\d+)L?",
    re.MULTILINE,
)
VARIANT_TYPE_RE = re.compile(r"\b([A-Z][A-Z0-9_]*)\((\d+)\)")
METHOD_BIND_RE = re.compile(
    r"ObjectCalls\.getMethodBind\(\s*"
    r'"([^"]+)"\s*,\s*"([^"]+)"\s*,\s*([A-Z0-9_]+|\d+L?)',
    re.DOTALL,
)
UTILITY_RE = re.compile(
    r"call[A-Za-z0-9_]*Utility\(\s*"
    r'"([^"]+)"\s*,\s*([A-Z0-9_]+|\d+L?)',
    re.DOTALL,
)
BUILTIN_CALL_RE = re.compile(
    r"type\s*=\s*VariantType\.([A-Z0-9_]+).*?"
    r'method\s*=\s*"([^"]+)".*?'
    r"hash\s*=\s*([A-Za-z_][A-Za-z0-9_]*|\d+L?)",
    re.DOTALL,
)
CONSTRUCT_CALL_RE = re.compile(
    r"type\s*=\s*VariantType\.([A-Z0-9_]+).*?"
    r"constructorIndex\s*=\s*(\d+)",
    re.DOTALL,
)
GODOT_LOOKUP_RE = re.compile(r'GodotFFI\.lookup\(\s*"([^"]+)"', re.DOTALL)

VARIANT_TYPE_NAMES = {
    "NIL": "Nil",
    "BOOL": "bool",
    "INT": "int",
    "FLOAT": "float",
    "STRING": "String",
    "VECTOR2": "Vector2",
    "VECTOR2I": "Vector2i",
    "VECTOR3": "Vector3",
    "VECTOR3I": "Vector3i",
    "QUATERNION": "Quaternion",
    "BASIS": "Basis",
    "TRANSFORM3D": "Transform3D",
    "STRING_NAME": "StringName",
    "NODE_PATH": "NodePath",
    "OBJECT": "Object",
    "CALLABLE": "Callable",
    "DICTIONARY": "Dictionary",
    "ARRAY": "Array",
    "PACKED_BYTE_ARRAY": "PackedByteArray",
    "PACKED_INT32_ARRAY": "PackedInt32Array",
    "PACKED_INT64_ARRAY": "PackedInt64Array",
    "PACKED_FLOAT32_ARRAY": "PackedFloat32Array",
    "PACKED_FLOAT64_ARRAY": "PackedFloat64Array",
    "PACKED_STRING_ARRAY": "PackedStringArray",
    "PACKED_VECTOR2_ARRAY": "PackedVector2Array",
    "PACKED_VECTOR3_ARRAY": "PackedVector3Array",
    "PACKED_COLOR_ARRAY": "PackedColorArray",
    "PACKED_VECTOR4_ARRAY": "PackedVector4Array",
}


def parse_int_token(token: str, constants: dict[str, int], path: Path) -> int:
    token = token.removesuffix("L")
    if token.isdigit():
        return int(token)
    if token not in constants:
        raise ValueError(f"{path}: unresolved hash constant {token}")
    return constants[token]


def load_api(path: Path) -> dict:
    with path.open("r", encoding="utf-8") as f:
        return json.load(f)


def class_method_hashes(api: dict) -> dict[tuple[str, str], set[int]]:
    result: dict[tuple[str, str], set[int]] = {}
    for cls in api.get("classes", []):
        class_name = cls.get("name")
        for method in cls.get("methods", []):
            method_name = method.get("name")
            method_hash = method.get("hash")
            if class_name and method_name and method_hash is not None:
                hashes = result.setdefault((class_name, method_name), set())
                hashes.add(int(method_hash))
                for compatibility_hash in method.get("hash_compatibility") or []:
                    hashes.add(int(compatibility_hash))
    return result


def utility_hashes(api: dict) -> dict[str, set[int]]:
    result: dict[str, set[int]] = {}
    for fn in api.get("utility_functions", []):
        name = fn.get("name")
        fn_hash = fn.get("hash")
        if name and fn_hash is not None:
            result.setdefault(name, set()).add(int(fn_hash))
    return result


def builtin_method_hashes(api: dict) -> dict[tuple[str, str], set[int]]:
    result: dict[tuple[str, str], set[int]] = {}
    for cls in api.get("builtin_classes", []):
        class_name = cls.get("name")
        for method in cls.get("methods", []):
            method_name = method.get("name")
            method_hash = method.get("hash")
            if class_name and method_name and method_hash is not None:
                result.setdefault((class_name, method_name), set()).add(int(method_hash))
    return result


def builtin_constructor_indexes(api: dict) -> dict[str, set[int]]:
    result: dict[str, set[int]] = {}
    for cls in api.get("builtin_classes", []):
        class_name = cls.get("name")
        if not class_name:
            continue
        for constructor in cls.get("constructors", []):
            index = constructor.get("index")
            if index is not None:
                result.setdefault(class_name, set()).add(int(index))
    return result


def builtin_size(api: dict, build_config: str, type_name: str) -> int | None:
    for config in api.get("builtin_class_sizes", []):
        if config.get("build_configuration") != build_config:
            continue
        for size in config.get("sizes", []):
            if size.get("name") == type_name:
                return int(size["size"])
    return None


def variant_type_ids(header: str) -> dict[str, int]:
    result: dict[str, int] = {}
    for name, value in re.findall(r"GDEXTENSION_VARIANT_TYPE_([A-Z0-9_]+)\s*=\s*(\d+)", header):
        result[name] = int(value)
    return result


def constants_for(content: str) -> dict[str, int]:
    return {name: int(value) for name, value in CONST_RE.findall(content)}


def iter_call_bodies(content: str, name: str) -> list[tuple[str, int]]:
    result: list[tuple[str, int]] = []
    pattern = re.compile(rf"(?:BuiltinTypes\.)?{re.escape(name)}\(")
    for match in pattern.finditer(content):
        start = match.end() - 1
        depth = 0
        in_string = False
        escaped = False
        for i in range(start, len(content)):
            ch = content[i]
            if in_string:
                if escaped:
                    escaped = False
                elif ch == "\\":
                    escaped = True
                elif ch == '"':
                    in_string = False
                continue

            if ch == '"':
                in_string = True
            elif ch == "(":
                depth += 1
            elif ch == ")":
                depth -= 1
                if depth == 0:
                    result.append((content[start + 1 : i], start))
                    break
    return result


def validate_kotlin_hashes(
    root: Path,
    methods: dict[tuple[str, str], set[int]],
    utilities: dict[str, set[int]],
    builtin_methods: dict[tuple[str, str], set[int]],
    builtin_constructors: dict[str, set[int]],
) -> list[str]:
    errors: list[str] = []
    for path in sorted((root / "src/jvmMain/kotlin").rglob("*.kt")):
        content = path.read_text(encoding="utf-8")
        constants = constants_for(content)

        for class_name, method_name, token in METHOD_BIND_RE.findall(content):
            try:
                actual_hash = parse_int_token(token, constants, path)
            except ValueError as e:
                errors.append(str(e))
                continue

            expected = methods.get((class_name, method_name))
            if not expected:
                errors.append(f"{path}: {class_name}.{method_name} not found in extension_api.json")
            elif actual_hash not in expected:
                expected_text = ", ".join(str(v) for v in sorted(expected))
                errors.append(
                    f"{path}: {class_name}.{method_name} hash {actual_hash} not in {{{expected_text}}}",
                )

        for function_name, token in UTILITY_RE.findall(content):
            try:
                actual_hash = parse_int_token(token, constants, path)
            except ValueError as e:
                errors.append(str(e))
                continue

            expected = utilities.get(function_name)
            if not expected:
                errors.append(f"{path}: utility {function_name} not found in extension_api.json")
            elif actual_hash not in expected:
                expected_text = ", ".join(str(v) for v in sorted(expected))
                errors.append(
                    f"{path}: utility {function_name} hash {actual_hash} not in {{{expected_text}}}",
                )

        for body, start in iter_call_bodies(content, "call"):
            match = BUILTIN_CALL_RE.search(body)
            if not match:
                continue
            type_name, method_name, token = match.groups()
            call_constants = constants_for(content[:start])
            builtin_name = VARIANT_TYPE_NAMES.get(type_name)
            if not builtin_name:
                errors.append(f"{path}: VariantType.{type_name} has no validator builtin-name mapping")
                continue

            try:
                actual_hash = parse_int_token(token, call_constants, path)
            except ValueError as e:
                errors.append(str(e))
                continue

            expected = builtin_methods.get((builtin_name, method_name))
            if not expected:
                errors.append(f"{path}: builtin {builtin_name}.{method_name} not found in extension_api.json")
            elif actual_hash not in expected:
                expected_text = ", ".join(str(v) for v in sorted(expected))
                errors.append(
                    f"{path}: builtin {builtin_name}.{method_name} hash {actual_hash} not in {{{expected_text}}}",
                )

        for body, _start in iter_call_bodies(content, "construct"):
            match = CONSTRUCT_CALL_RE.search(body)
            if not match:
                continue
            type_name, index_text = match.groups()
            builtin_name = VARIANT_TYPE_NAMES.get(type_name)
            if not builtin_name:
                errors.append(f"{path}: VariantType.{type_name} has no validator builtin-name mapping")
                continue

            index = int(index_text)
            expected = builtin_constructors.get(builtin_name)
            if not expected:
                errors.append(f"{path}: builtin {builtin_name} constructors not found in extension_api.json")
            elif index not in expected:
                expected_text = ", ".join(str(v) for v in sorted(expected))
                errors.append(
                    f"{path}: builtin {builtin_name} constructor index {index} not in {{{expected_text}}}",
                )

        if "EMIT_SIGNAL_HASH" in constants:
            expected = methods.get(("Object", "emit_signal"))
            actual_hash = constants["EMIT_SIGNAL_HASH"]
            if not expected or actual_hash not in expected:
                expected_text = ", ".join(str(v) for v in sorted(expected or []))
                errors.append(
                    f"{path}: Object.emit_signal hash {actual_hash} not in {{{expected_text}}}",
                )
    return errors


def validate_variant_types(root: Path, header_path: Path) -> list[str]:
    paths = sorted((root / "src/jvmMain/kotlin").rglob("VariantType.kt"))
    if not paths:
        return [f"{root / 'src/jvmMain/kotlin'}: missing VariantType.kt"]
    path = paths[0]
    header = header_path.read_text(encoding="utf-8") if header_path.exists() else ""
    expected = variant_type_ids(header)
    if not expected:
        return [f"{header_path}: missing GDExtensionVariantType enum"]

    errors: list[str] = []
    content = path.read_text(encoding="utf-8")
    for name, value_text in VARIANT_TYPE_RE.findall(content):
        if name not in VARIANT_TYPE_NAMES:
            continue
        actual = int(value_text)
        expected_id = expected.get(name)
        if expected_id is None:
            errors.append(f"{path}: VariantType.{name} not found in gdextension_interface.h")
        elif actual != expected_id:
            errors.append(f"{path}: VariantType.{name}={actual} but gdextension_interface.h has {expected_id}")
    return errors


def validate_lookups(root: Path, header_path: Path) -> list[str]:
    errors: list[str] = []
    header = header_path.read_text(encoding="utf-8") if header_path.exists() else ""
    if not header:
        return [f"{header_path}: missing GDExtension header"]

    for path in sorted((root / "src/jvmMain/kotlin").rglob("*.kt")):
        content = path.read_text(encoding="utf-8")
        for symbol in GODOT_LOOKUP_RE.findall(content):
            if symbol not in header:
                errors.append(f"{path}: GodotFFI.lookup({symbol}) not found in gdextension_interface.h")
    return errors


def validate_builtin_sizes(root: Path, api: dict) -> list[str]:
    errors: list[str] = []
    expected_variant_size = builtin_size(api, "float_64", "Variant")
    if expected_variant_size is None:
        errors.append("extension_api.json: missing float_64 Variant size")
        return errors

    for path in sorted((root / "src/jvmMain/kotlin").rglob("*.kt")):
        content = path.read_text(encoding="utf-8")
        constants = constants_for(content)
        if "VARIANT_SIZE" in constants and constants["VARIANT_SIZE"] != expected_variant_size:
            errors.append(
                f"{path}: VARIANT_SIZE={constants['VARIANT_SIZE']} but extension_api float_64 Variant={expected_variant_size}",
            )
    return errors


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--api", default="extension_api.json", help="Path to Godot extension_api.json")
    parser.add_argument(
        "--header",
        default="gdextension/gdextension_interface.h",
        help="Path to gdextension_interface.h",
    )
    args = parser.parse_args()

    root = Path(__file__).resolve().parents[1]
    api_path = (root / args.api).resolve()
    header_path = (root / args.header).resolve()

    api = load_api(api_path)
    errors: list[str] = []
    errors += validate_kotlin_hashes(
        root,
        class_method_hashes(api),
        utility_hashes(api),
        builtin_method_hashes(api),
        builtin_constructor_indexes(api),
    )
    errors += validate_lookups(root, header_path)
    errors += validate_builtin_sizes(root, api)
    errors += validate_variant_types(root, header_path)

    if errors:
        print("[validate_godot_api] FAIL", file=sys.stderr)
        for error in errors:
            print(f"  - {error}", file=sys.stderr)
        return 1

    print(f"[validate_godot_api] PASS api={api_path.name} header={header_path}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
