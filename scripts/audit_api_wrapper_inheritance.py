#!/usr/bin/env python3
"""Audit API wrapper base classes against extension_api.json.

Kanama sometimes skips intermediate Godot base classes while wrapper coverage is
still growing. This audit allows that as long as the Kotlin base is still an
ancestor of the Godot class. It catches unsafe shell wrappers that extend an
unrelated handle type.
"""

from __future__ import annotations

import re
import sys
from pathlib import Path

from wrapper_model import ancestors, load_api_classes


ROOT = Path(__file__).resolve().parents[1]
API_DIR = ROOT / "src/main/kotlin/net/multigesture/kanama/api"
API_PATH = ROOT / "extension_api.json"

CLASS_RE = re.compile(
    r"^(?:open\s+)?class\s+(?P<name>\w+).*?\)\s*:\s*(?P<base>\w+)\(",
    re.MULTILINE | re.DOTALL,
)

# Root wrappers that legitimately extend a hand base rather than their Godot parent's wrapper.
# `Resource` was removed (task 51): it now extends `RefCounted`, matching Godot's real
# `Object > RefCounted > Resource` chain (as iOS already did), so the audit enforces that parent
# instead of whitelisting it as a root.
ROOT_WRAPPER_BASES = {"GodotObject", "AutoCloseable"}
ROOT_CLASSES = {"Object", "RefCounted"}


def allowed_bases(class_name: str, api_classes: dict) -> set[str]:
    if class_name == "Object":
        return {"GodotObject"}
    if class_name in ROOT_CLASSES:
        return ROOT_WRAPPER_BASES | {"GodotObject", "RefCounted", "Resource"}

    cls = api_classes[class_name]
    allowed = set(ancestors(class_name, api_classes))
    if cls.inherits:
        allowed.add(cls.inherits)
    allowed.add("GodotObject")
    return allowed


def main() -> int:
    api_classes = load_api_classes(API_PATH)
    errors: list[str] = []

    for path in sorted(API_DIR.glob("*.kt")):
        content = path.read_text(encoding="utf-8")
        match = CLASS_RE.search(content)
        if not match:
            continue

        class_name = match.group("name")
        base_name = match.group("base")
        if class_name not in api_classes:
            continue

        if base_name not in allowed_bases(class_name, api_classes):
            expected = api_classes[class_name].inherits or "Object"
            errors.append(
                f"{path.relative_to(ROOT)}: {class_name} extends {base_name}, "
                f"but Godot inherits from {expected}",
            )

    if errors:
        print("[api_wrapper_inheritance_audit] FAIL", file=sys.stderr)
        for error in errors:
            print(f"  - {error}", file=sys.stderr)
        return 1

    print("[api_wrapper_inheritance_audit] PASS")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
