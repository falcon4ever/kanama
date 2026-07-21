#!/usr/bin/env python3
"""Audit conservative wrapper-generator object typing policy.

The method generator may only expose Godot object handles when the API type
itself is `Object`. Concrete Godot classes must stay concrete Kotlin wrappers,
and ownership-sensitive namespace/builtin-like classes must remain blocked
until they have an explicit wrapper policy.
"""

from __future__ import annotations

import re
import sys
from pathlib import Path

from generate_api_shell_wrappers import UNSAFE_DEFAULT_EXCLUDES
from generate_api_wrapper import (
    OWNERSHIP_SENSITIVE_OBJECT_TYPES,
    SPECIAL_OBJECT_WRAPPER_TYPES,
    api_object_wrapper_type,
    kotlin_return_type,
    kotlin_type,
    typed_object_array_element,
    unsupported_reason,
    wrapper_has_wrap,
)
from wrapper_model import ApiClass, load_api_classes, load_api_singletons, object_type_names, scan_wrapper_classes


ROOT = Path(__file__).resolve().parents[1]
API_PATH = ROOT / "extension_api.json"
API_DIR = ROOT / "src/main/kotlin/net/multigesture/kanama/api"

# `Object` itself may map to GodotObject because the Godot API is explicitly
# dynamic there. The others are not ordinary Object wrappers in Kanama today.
BLOCKED_OBJECT_TYPES = OWNERSHIP_SENSITIVE_OBJECT_TYPES


def wrapper_file_text(type_name: str) -> str:
    path = API_DIR / f"{type_name}.kt"
    return path.read_text(encoding="utf-8") if path.exists() else ""


def is_namespace_wrapper(type_name: str) -> bool:
    return bool(re.search(rf"\bobject\s+{re.escape(type_name)}\s*\{{", wrapper_file_text(type_name)))


def is_object_type(type_name: str, object_types: set[str]) -> bool:
    return type_name in object_types


def check_object_type(
    *,
    class_name: str,
    method_name: str,
    type_name: str,
    position: str,
    object_types: set[str],
    wrapper_classes: set[str],
    api_classes: dict[str, ApiClass],
    singleton_types: set[str],
) -> list[str]:
    if not is_object_type(type_name, object_types):
        return []

    prefix = f"{class_name}.{method_name} {position} {type_name}"
    errors: list[str] = []

    if type_name == "Callable":
        return errors

    if type_name in BLOCKED_OBJECT_TYPES:
        errors.append(f"{prefix} is ownership/builtin-sensitive and must stay unsupported")
        return errors

    if type_name == "Object":
        return errors

    wrapper_type = api_object_wrapper_type(type_name, wrapper_classes)
    expected_wrapper = SPECIAL_OBJECT_WRAPPER_TYPES.get(type_name, type_name)
    if wrapper_type != expected_wrapper:
        errors.append(f"{prefix} would widen to {wrapper_type or 'no wrapper'} instead of a concrete wrapper")
        return errors

    if (
        wrapper_type == type_name
        and is_namespace_wrapper(type_name)
        and not (position == "return" and type_name in singleton_types)
    ):
        errors.append(f"{prefix} resolves to namespace-style object wrapper {type_name}, not an instance wrapper")

    if position == "return" and not wrapper_has_wrap(API_DIR, wrapper_type):
        errors.append(f"{prefix} has no nullable wrap(handle) helper for generated object returns")

    return errors


def check_rendered_kotlin_type(
    *,
    class_name: str,
    method_name: str,
    api_type_name: str,
    logical_type: str,
    rendered_type: str,
    position: str,
    object_types: set[str],
) -> list[str]:
    """Ensure concrete Godot object API types stay concrete in public wrappers."""
    array_element = typed_object_array_element(logical_type)
    concrete_type = array_element or api_type_name
    if not is_object_type(concrete_type, object_types):
        return []

    prefix = f"{class_name}.{method_name} {position} {concrete_type}"

    if concrete_type == "Callable":
        expected = "GodotCallable?" if position == "return" else "GodotCallable"
        if rendered_type != expected:
            return [f"{prefix} rendered as {rendered_type}, expected {expected}"]
        return []

    if concrete_type == "Object":
        if rendered_type not in {"GodotObject", "GodotObject?", "List<GodotObject>"}:
            return [f"{prefix} exact Object API rendered unexpectedly as {rendered_type}"]
        return []

    errors: list[str] = []
    forbidden_tokens = {"GodotObject", "MemorySegment", "Object", "List<GodotObject>", "List<MemorySegment>", "List<Object>"}
    if rendered_type in forbidden_tokens:
        errors.append(f"{prefix} rendered as unsafe/widened Kotlin type {rendered_type}")

    expected_concrete_type = SPECIAL_OBJECT_WRAPPER_TYPES.get(concrete_type, concrete_type)
    expected = f"List<{expected_concrete_type}>" if array_element else expected_concrete_type
    if rendered_type not in {expected, f"{expected}?"}:
        errors.append(f"{prefix} rendered as {rendered_type}, expected {expected} or {expected}?")

    return errors


def main() -> int:
    api_classes = load_api_classes(API_PATH)
    singleton_types = load_api_singletons(API_PATH)
    object_types = object_type_names(api_classes)
    wrapper_classes = scan_wrapper_classes(API_DIR)

    errors: list[str] = []

    missing_shell_excludes = BLOCKED_OBJECT_TYPES | {"Object"}
    missing = sorted(missing_shell_excludes - UNSAFE_DEFAULT_EXCLUDES)
    if missing:
        errors.append(
            "generate_api_shell_wrappers.UNSAFE_DEFAULT_EXCLUDES is missing: "
            + ", ".join(missing),
        )

    for cls in api_classes.values():
        for method_list in cls.methods.values():
            for method in method_list:
                reason = unsupported_reason(cls.name, method, object_types, wrapper_classes, api_classes, API_DIR)
                if reason is not None:
                    continue

                logical_args = method.logical_arg_kinds(object_types)
                logical_return = method.logical_return_kind(object_types)

                return_array_element = typed_object_array_element(logical_return)
                if return_array_element is not None:
                    errors.extend(
                        check_object_type(
                            class_name=cls.name,
                            method_name=method.name,
                            type_name=return_array_element,
                            position="typed-array return",
                            object_types=object_types,
                            wrapper_classes=wrapper_classes,
                            api_classes=api_classes,
                            singleton_types=singleton_types,
                        ),
                    )
                else:
                    errors.extend(
                        check_object_type(
                            class_name=cls.name,
                            method_name=method.name,
                            type_name=method.return_type,
                            position="return",
                            object_types=object_types,
                            wrapper_classes=wrapper_classes,
                            api_classes=api_classes,
                            singleton_types=singleton_types,
                        ),
                    )
                if logical_return != "void":
                    rendered_return_type = kotlin_return_type(
                        logical_return,
                        method.return_type,
                        wrapper_classes,
                        api_classes,
                        API_DIR,
                    )
                    errors.extend(
                        check_rendered_kotlin_type(
                            class_name=cls.name,
                            method_name=method.name,
                            api_type_name=method.return_type,
                            logical_type=logical_return,
                            rendered_type=rendered_return_type,
                            position="return",
                            object_types=object_types,
                        ),
                    )

                for index, (type_name, logical_arg) in enumerate(
                    zip(method.argument_types, logical_args, strict=True),
                    start=1,
                ):
                    array_element = typed_object_array_element(logical_arg)
                    errors.extend(
                        check_object_type(
                            class_name=cls.name,
                            method_name=method.name,
                            type_name=array_element or type_name,
                            position=f"arg{index}",
                            object_types=object_types,
                            wrapper_classes=wrapper_classes,
                            api_classes=api_classes,
                            singleton_types=singleton_types,
                        ),
                    )
                    rendered_arg_type = kotlin_type(logical_arg, type_name, wrapper_classes, api_classes)
                    errors.extend(
                        check_rendered_kotlin_type(
                            class_name=cls.name,
                            method_name=method.name,
                            api_type_name=type_name,
                            logical_type=logical_arg,
                            rendered_type=rendered_arg_type,
                            position=f"arg{index}",
                            object_types=object_types,
                        ),
                    )

    errors.extend(check_object_param_nullability_policy(api_classes))

    if errors:
        print("[generator_object_policy] FAIL", file=sys.stderr)
        for error in errors:
            print(f"  - {error}", file=sys.stderr)
        return 1

    print("[generator_object_policy] PASS")
    return 0


def _arg_meta(api_classes: dict, class_name: str, method_name: str, arg_name: str) -> str | None:
    cls = api_classes.get(class_name)
    if cls is None:
        return None
    for method in cls.methods.get(method_name, []):
        if arg_name in method.argument_names:
            return method.argument_metas[method.argument_names.index(arg_name)]
    return None


def check_object_param_nullability_policy(api_classes: dict) -> list[str]:
    """Task 52a/52b — prove the contextual object-parameter nullability policy and validate the
    override table. Removing a policy branch, or leaving a stale/colliding override, fails here."""
    from generate_api_wrapper import NULLABLE_OBJECT_PARAM_OVERRIDES, object_param_is_nullable

    errs: list[str] = []

    # Override table: every entry must resolve to a current Object argument that is NOT meta:required
    # (an override must never admit null for a parameter Godot marks required — a refresh that adds
    # `required` to an old override must fail here for human review).
    for class_name, method_name, arg_name in NULLABLE_OBJECT_PARAM_OVERRIDES:
        meta = _arg_meta(api_classes, class_name, method_name, arg_name)
        if meta is None:
            errs.append(f"stale NULLABLE_OBJECT_PARAM_OVERRIDES entry: {(class_name, method_name, arg_name)} not found in the API")
        elif meta == "required":
            errs.append(f"NULLABLE_OBJECT_PARAM_OVERRIDES entry {(class_name, method_name, arg_name)} collides with meta:required")

    # The four canonical cases (Node = non-resource object; Texture2D = Resource-derived):
    cases = [
        # (class, method, arg, type, meta, expected_nullable, why)
        ("Node", "set_owner", "owner", "Node", "", True, "audited override -> nullable"),
        ("Node", "add_child", "node", "Node", "required", False, "required ordinary Object -> non-null"),
        ("TextureRect", "set_texture", "texture", "Texture2D", "", True, "unmarked Resource -> nullable (legacy)"),
        # 52b tightening: a required Resource-like param is now non-null (the `required` check sits
        # above the resource-like line), calling requireOpenHandle() without a safe-call.
        ("CanvasItem", "draw_texture", "texture", "Texture2D", "required", False, "required Resource -> non-null (52b)"),
    ]
    for class_name, method_name, arg_name, type_name, meta, expected, why in cases:
        actual = object_param_is_nullable(class_name, method_name, arg_name, type_name, meta, api_classes)
        if actual != expected:
            errs.append(f"object_param_is_nullable({class_name}.{method_name}.{arg_name}) = {actual}, expected {expected} ({why})")

    return errs


if __name__ == "__main__":
    raise SystemExit(main())
