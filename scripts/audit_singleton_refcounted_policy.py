#!/usr/bin/env python3
"""Audit singleton lifetime policy for Godot 4.7 RefCounted singleton warnings."""

from __future__ import annotations

import json
import sys
from pathlib import Path


ROOT = Path(__file__).resolve().parents[1]
API_PATH = ROOT / "extension_api.json"
ENGINE_WRAPPER = ROOT / "src/main/kotlin/net/multigesture/kanama/api/Engine.kt"


def load_api() -> tuple[dict[str, dict[str, object]], list[dict[str, object]]]:
    data = json.loads(API_PATH.read_text(encoding="utf-8"))
    classes = {
        str(cls["name"]): cls
        for cls in data.get("classes", [])
        if cls.get("name")
    }
    singletons = [
        singleton
        for singleton in data.get("singletons", [])
        if singleton.get("type") or singleton.get("name")
    ]
    return classes, singletons


def ancestors(class_name: str, classes: dict[str, dict[str, object]]) -> list[str]:
    result: list[str] = []
    current = class_name
    while current:
        cls = classes.get(current)
        if not cls:
            break
        parent = str(cls.get("inherits") or "")
        if not parent:
            break
        result.append(parent)
        current = parent
    return result


def is_refcounted(class_name: str, classes: dict[str, dict[str, object]]) -> bool:
    cls = classes.get(class_name)
    if not cls:
        return False
    return bool(cls.get("is_refcounted")) or "RefCounted" in ancestors(class_name, classes)


def main() -> int:
    classes, singletons = load_api()
    errors: list[str] = []

    for singleton in singletons:
        singleton_name = str(singleton.get("name") or singleton.get("type") or "")
        singleton_type = str(singleton.get("type") or singleton_name)
        if is_refcounted(singleton_type, classes):
            errors.append(f"extension_api.json singleton {singleton_name} has RefCounted type {singleton_type}")

    script_language_parent = "ScriptLanguageExtension"
    if is_refcounted(script_language_parent, classes):
        errors.append("KanamaScriptLanguage ClassDB parent ScriptLanguageExtension is RefCounted")

    engine_source = ENGINE_WRAPPER.read_text(encoding="utf-8")
    if 'fun registerSingleton(name: String, objectArg: MemorySegment)' not in engine_source:
        errors.append("Engine.registerSingleton wrapper not found")
    if 'isClass("RefCounted")' not in engine_source:
        errors.append("Engine.registerSingleton must reject RefCounted instances before calling Godot")

    if errors:
        for error in errors:
            print(f"[singleton_refcounted] {error}", file=sys.stderr)
        return 1

    print(f"[singleton_refcounted] PASS singletons={len(singletons)}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
