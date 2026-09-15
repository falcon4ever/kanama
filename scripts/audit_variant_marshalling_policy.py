#!/usr/bin/env python3
"""Audit generic Variant marshalling policy.

Generated wrappers may pass Array/Dictionary contents through the generic
Variant path. That path must fail loudly for unsupported Kotlin values; silently
coercing unknown values to strings hides ABI and type-policy bugs.
"""

from __future__ import annotations

import re
import sys
from pathlib import Path


ROOT = Path(__file__).resolve().parents[1]
BUILTIN_TYPES = ROOT / "src/jvmMain/kotlin/binding/runtime/BuiltinTypes.kt"


def find_function_body(content: str, name: str) -> str:
    match = re.search(rf"\bfun\s+{re.escape(name)}\s*\(", content)
    if not match:
        raise RuntimeError(f"missing function {name}")

    brace = content.find("{", match.end())
    if brace == -1:
        raise RuntimeError(f"missing body for function {name}")

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
                return content[brace : index + 1]
    raise RuntimeError(f"unterminated body for function {name}")


def main() -> int:
    content = BUILTIN_TYPES.read_text(encoding="utf-8")
    errors: list[str] = []
    body = find_function_body(content, "initVariantFromAny")

    if ".toString()" in body:
        errors.append("initVariantFromAny must not stringify unsupported values")
    if "is Map<*, *> -> variantFromDictionaryInto" not in body:
        errors.append("initVariantFromAny must route Map values through Dictionary marshalling")
    if 'else -> error("Unsupported Variant value type:' not in body:
        errors.append("initVariantFromAny must throw for unsupported values")

    dictionary_reader = find_function_body(content, "readDictionaryScalars")
    if "variantToScalar(keyVariant, arena) as? String ?: continue" not in dictionary_reader:
        errors.append("readDictionaryScalars must keep the String-key-only Dictionary policy explicit")
    dictionary_writer = find_function_body(content, "initDictionary")
    if "fun initDictionary(dest: MemorySegment, entries: Map<String, Any?>)" not in content:
        errors.append("initDictionary must only accept String-key dictionaries")
    if "variantFromStringInto(key, keyVar, arena)" not in dictionary_writer:
        errors.append("initDictionary must encode Dictionary keys as String Variants")

    if errors:
        print("[variant_marshalling_policy_audit] FAIL", file=sys.stderr)
        for error in errors:
            print(f"  - {error}", file=sys.stderr)
        return 1

    print("[variant_marshalling_policy_audit] PASS")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
