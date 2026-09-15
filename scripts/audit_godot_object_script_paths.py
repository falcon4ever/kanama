#!/usr/bin/env python3
"""Guard GodotObject convenience APIs that must preserve script semantics.

`GodotObject.get/set/setScript` are hand-written because generated ClassDB
wrappers are not enough for dynamically attached Kanama scripts. Keep this
audit narrow and explicit so future wrapper cleanups do not accidentally
restore native-ClassDB-only behavior.
"""

from pathlib import Path
import re
import sys


ROOT = Path(__file__).resolve().parents[1]
GODOT_OBJECT = ROOT / "src/jvmMain/kotlin/net/multigesture/kanama/api/GodotObject.kt"


def function_body(source: str, name: str) -> str:
    match = re.search(rf"\n    fun {re.escape(name)}\([^)]*\)[^\n]*\{{", source)
    if not match:
        expression = re.search(rf"\n    fun {re.escape(name)}\([^)]*\)[^\n]*=\n        ([^\n]+)", source)
        if expression:
            return expression.group(1)
        raise ValueError(f"missing GodotObject.{name} function")
    start = match.end()
    depth = 1
    index = start
    while index < len(source) and depth > 0:
        char = source[index]
        if char == "{":
            depth += 1
        elif char == "}":
            depth -= 1
        index += 1
    if depth != 0:
        raise ValueError(f"could not parse GodotObject.{name} body")
    return source[start:index - 1]


def main() -> int:
    source = GODOT_OBJECT.read_text()
    errors: list[str] = []

    get_body = function_body(source, "get")
    if "ClassDB.classGetProperty" in get_body:
        errors.append("GodotObject.get must use Object.get, not ClassDB.classGetProperty")
    if "ptrcallWithStringNameArgRetVariantScalar(objectGetBind" not in get_body:
        errors.append("GodotObject.get must call the Object.get MethodBind")

    set_body = function_body(source, "set")
    if "ClassDB.classSetProperty" in set_body:
        errors.append("GodotObject.set must use Object.set, not ClassDB.classSetProperty")
    if "ptrcallWithStringNameAndVariantArg(objectSetBind" not in set_body:
        errors.append("GodotObject.set must call the Object.set MethodBind")
    if "ScriptBridge.applyOrRecordScriptPropertySet" not in set_body:
        errors.append("GodotObject.set must preserve pending Kanama script property replay")

    set_script_body = function_body(source, "setScript")
    if "ScriptBridge.noteSetScript" not in set_script_body:
        errors.append("GodotObject.setScript must notify ScriptBridge before attaching scripts")

    if "Object\", \"set\", OBJECT_SET_HASH" not in source:
        errors.append("GodotObject must bind Object.set explicitly")
    if "Object\", \"get\", OBJECT_GET_HASH" not in source:
        errors.append("GodotObject must bind Object.get explicitly")

    if errors:
        print("[godot_object_script_paths_audit] FAIL", file=sys.stderr)
        for error in errors:
            print(f"  - {error}", file=sys.stderr)
        return 1

    print("[godot_object_script_paths_audit] PASS")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
