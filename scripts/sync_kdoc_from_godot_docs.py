#!/usr/bin/env python3
"""Sync generated KDoc for Kotlin wrappers from Godot XML class docs."""

from __future__ import annotations

import argparse
import html
import os
import re
import sys
import xml.etree.ElementTree as ET
from dataclasses import dataclass
from pathlib import Path


DEFAULT_GODOT_DOCS = Path(os.environ.get("GODOT_DOCS", "godot/doc/classes"))
DEFAULT_API_DIR = Path("src/main/kotlin/net/multigesture/kanama/api")
DEFAULT_TYPES_DIR = Path("src/main/kotlin/net/multigesture/kanama/types")
GENERATED_MARKER = "Generated from Godot docs:"

METHOD_BIND_RE = re.compile(
    r"ObjectCalls\.getMethodBind\(\s*"
    r'"([^"]+)"\s*,\s*"([^"]+)"\s*,\s*([A-Z0-9_]+|\d+L?)',
    re.DOTALL,
)
FUN_RE = re.compile(r"^(\s*)(?:public\s+)?fun\s+(?:<[^>]+>\s+)?([A-Za-z_][A-Za-z0-9_]*)\s*\(")
CLASS_RE = re.compile(r"^(\s*)(?:(?:open|abstract|sealed|data|value)\s+)*class\s+([A-Za-z_][A-Za-z0-9_]*)\b")
OBJECT_RE = re.compile(r"^(\s*)object\s+([A-Za-z_][A-Za-z0-9_]*)\b")
PROPERTY_RE = re.compile(r"^(\s*)(?:const\s+)?(?:val|var)\s+([A-Za-z_][A-Za-z0-9_]*)\b")


@dataclass(frozen=True)
class GodotClassDocs:
    class_name: str
    class_doc: str | None
    methods: dict[str, str]
    accessors: dict[str, str]
    members: dict[str, str]
    constants: dict[str, str]


@dataclass(frozen=True)
class Replacement:
    start: int
    end: int
    lines: list[str]


def snake_to_camel(name: str) -> str:
    prefix = ""
    while name.startswith("_"):
        prefix += "_"
        name = name[1:]
    parts = [part for part in name.split("_") if part]
    if not parts:
        return prefix
    return prefix + parts[0] + "".join(part[:1].upper() + part[1:] for part in parts[1:])


def element_text(element: ET.Element | None) -> str:
    if element is None:
        return ""
    return "".join(element.itertext())


def first_paragraph(text: str) -> str:
    lines = [line.strip() for line in text.strip().splitlines()]
    paragraphs: list[str] = []
    current: list[str] = []
    for line in lines:
        if not line:
            if current:
                paragraphs.append(" ".join(current))
                current = []
            continue
        current.append(line)
    if current:
        paragraphs.append(" ".join(current))
    return paragraphs[0] if paragraphs else ""


def normalize_godot_text(text: str) -> str | None:
    text = html.unescape(text)
    text = re.sub(r"\[codeblocks?(?:\s+[^\]]+)?\].*?\[/codeblocks?\]", "", text, flags=re.DOTALL)
    text = re.sub(r"\[code(?:\s+[^\]]+)?\](.*?)\[/code\]", r"`\1`", text, flags=re.DOTALL)
    text = re.sub(r"\[url=([^\]]+)\](.*?)\[/url\]", r"\2 (\1)", text, flags=re.DOTALL)
    text = re.sub(r"\[/?(?:b|i|u|kbd|center|br)\]", "", text)
    text = re.sub(r"\[(?:param|method|member|constant|enum|signal|annotation|theme_item)\s+([^\]]+)\]", r"`\1`", text)
    text = re.sub(r"\[([A-Za-z_@][A-Za-z0-9_@.]*)\]", r"`\1`", text)
    text = re.sub(r"\[/[A-Za-z_]+\]", "", text)
    text = first_paragraph(text)
    text = re.sub(r"\s+", " ", text).strip()
    text = re.sub(r"\s+Example:.*$", "", text).strip()
    text = text.replace("/*", "/\\*").replace("*/", "* /")
    return text or None


def load_godot_docs(docs_dir: Path, class_name: str) -> GodotClassDocs | None:
    path = docs_dir / f"{class_name}.xml"
    if not path.exists():
        return None
    root = ET.parse(path).getroot()
    class_doc = normalize_godot_text(element_text(root.find("brief_description")))
    methods: dict[str, str] = {}
    accessors: dict[str, str] = {}
    members: dict[str, str] = {}
    constants: dict[str, str] = {}

    methods_node = root.find("methods")
    if methods_node is not None:
        for method in methods_node.findall("method"):
            name = method.attrib.get("name")
            description = normalize_godot_text(element_text(method.find("description")))
            if name and description:
                methods[name] = description

    members_node = root.find("members")
    if members_node is not None:
        for member in members_node.findall("member"):
            description = normalize_godot_text(element_text(member))
            if not description:
                continue
            name = member.attrib.get("name")
            if name:
                members[name] = description
            for attribute in ("getter", "setter"):
                accessor = member.attrib.get(attribute)
                if accessor:
                    accessors[accessor] = description

    constants_node = root.find("constants")
    if constants_node is not None:
        for constant in constants_node.findall("constant"):
            name = constant.attrib.get("name")
            description = normalize_godot_text(element_text(constant))
            if name and description:
                constants[name] = description

    return GodotClassDocs(
        class_name=class_name,
        class_doc=class_doc,
        methods=methods,
        accessors=accessors,
        members=members,
        constants=constants,
    )


def kdoc_block(indent: str, description: str, source: str) -> list[str]:
    lines = [f"{indent}/**\n"]
    words = description.split(" ")
    current = ""
    for word in words:
        candidate = word if not current else f"{current} {word}"
        if len(candidate) > 96 and current:
            lines.append(f"{indent} * {current}\n")
            current = word
        else:
            current = candidate
    if current:
        lines.append(f"{indent} * {current}\n")
    lines.append(f"{indent} *\n")
    lines.append(f"{indent} * {GENERATED_MARKER} {source}\n")
    lines.append(f"{indent} */\n")
    return lines


def builtin_class_description(description: str) -> str:
    return (
        f"{description} Kanama value types are immutable snapshots; assign a new value back to the "
        "Godot property after changing components."
    )


def _is_single_line_block(stripped: str) -> bool:
    """A one-line KDoc block: `/** ... */`. Older syncs emitted these for brief-only
    class docs; the new multi-line block must REPLACE one, not stack above it."""
    return stripped.startswith("/**") and stripped.endswith("*/") and len(stripped) > len("/**/")


def find_generated_kdoc_start(lines: list[str], declaration_index: int) -> int | None:
    index = declaration_index - 1
    while index >= 0 and lines[index].strip().startswith("@"):
        index -= 1
    if index < 0:
        return None
    stripped = lines[index].strip()
    if _is_single_line_block(stripped):
        return index if GENERATED_MARKER in stripped else None
    if stripped != "*/":
        return None
    end = index
    while index >= 0 and lines[index].strip() != "/**":
        index -= 1
    if index < 0:
        return None
    if any(GENERATED_MARKER in line for line in lines[index : end + 1]):
        return index
    return None


def find_kdoc_start_ending_at(lines: list[str], end_index: int) -> int | None:
    if end_index < 0:
        return None
    stripped = lines[end_index].strip()
    if _is_single_line_block(stripped):
        return end_index
    if stripped != "*/":
        return None
    index = end_index
    while index >= 0 and lines[index].strip() != "/**":
        index -= 1
    return index if index >= 0 else None


def find_class_replacement_start(lines: list[str], declaration_index: int) -> int:
    insert_at = find_insertion_index(lines, declaration_index)
    generated_start = find_generated_kdoc_start(lines, declaration_index)
    if generated_start is None:
        existing_start = find_kdoc_start_ending_at(lines, insert_at - 1)
        return existing_start if existing_start is not None else insert_at

    previous_end = generated_start - 1
    while previous_end >= 0 and not lines[previous_end].strip():
        previous_end -= 1
    previous_start = find_kdoc_start_ending_at(lines, previous_end)
    return previous_start if previous_start is not None else generated_start


def find_insertion_index(lines: list[str], declaration_index: int) -> int:
    index = declaration_index
    while index > 0 and lines[index - 1].strip().startswith("@"):
        index -= 1
    return index


def scan_wrapper_methods(content: str, class_name: str) -> set[str]:
    return {
        method_name
        for bind_class, method_name, _token in METHOD_BIND_RE.findall(content)
        if bind_class == class_name
    }


def collect_api_replacements(path: Path, docs: GodotClassDocs) -> tuple[list[Replacement], int, int]:
    lines = path.read_text(encoding="utf-8").splitlines(keepends=True)
    wrapped_methods = scan_wrapper_methods("".join(lines), docs.class_name)
    method_docs = {snake_to_camel(name): (name, doc) for name, doc in docs.methods.items()}
    method_docs.update({snake_to_camel(name): (name, doc) for name, doc in docs.accessors.items()})
    replacements: list[Replacement] = []
    documented_methods = 0
    missing_methods = 0

    for index, line in enumerate(lines):
        class_match = CLASS_RE.match(line) or OBJECT_RE.match(line)
        if class_match and class_match.group(2) == docs.class_name and docs.class_doc:
            insert_at = find_insertion_index(lines, index)
            replacements.append(
                Replacement(
                    start=find_class_replacement_start(lines, index),
                    end=insert_at,
                    lines=kdoc_block(class_match.group(1), docs.class_doc, docs.class_name),
                ),
            )
            continue

        fun_match = FUN_RE.match(line)
        if not fun_match:
            continue
        kotlin_name = fun_match.group(2)
        doc_entry = method_docs.get(kotlin_name)
        if not doc_entry:
            continue
        godot_name, description = doc_entry
        if godot_name not in wrapped_methods:
            continue
        insert_at = find_insertion_index(lines, index)
        replace_start = find_generated_kdoc_start(lines, index)
        replacements.append(
            Replacement(
                start=replace_start if replace_start is not None else insert_at,
                end=insert_at,
                lines=kdoc_block(fun_match.group(1), description, f"{docs.class_name}.{godot_name}"),
            ),
        )
        documented_methods += 1

    documented_godot_methods = {
        method_docs[match.group(2)][0]
        for line in lines
        if (match := FUN_RE.match(line)) and match.group(2) in method_docs
    }
    missing_methods = len(wrapped_methods - documented_godot_methods)
    return replacements, documented_methods, missing_methods


def collect_builtin_replacements(path: Path, docs: GodotClassDocs) -> tuple[list[Replacement], int, int]:
    lines = path.read_text(encoding="utf-8").splitlines(keepends=True)
    method_docs = {snake_to_camel(name): (name, doc) for name, doc in docs.methods.items()}
    replacements: list[Replacement] = []
    documented_members = 0
    documented_functions = 0
    seen_member_docs: set[str] = set()

    for index, line in enumerate(lines):
        class_match = CLASS_RE.match(line) or OBJECT_RE.match(line)
        if class_match and class_match.group(2) == docs.class_name and docs.class_doc:
            description = builtin_class_description(docs.class_doc)
            insert_at = find_insertion_index(lines, index)
            replacements.append(
                Replacement(
                    start=find_class_replacement_start(lines, index),
                    end=insert_at,
                    lines=kdoc_block(class_match.group(1), description, docs.class_name),
                ),
            )
            continue

        fun_match = FUN_RE.match(line)
        if fun_match:
            kotlin_name = fun_match.group(2)
            doc_entry = method_docs.get(kotlin_name)
            if doc_entry:
                godot_name, description = doc_entry
                insert_at = find_insertion_index(lines, index)
                replace_start = find_generated_kdoc_start(lines, index)
                replacements.append(
                    Replacement(
                        start=replace_start if replace_start is not None else insert_at,
                        end=insert_at,
                        lines=kdoc_block(fun_match.group(1), description, f"{docs.class_name}.{godot_name}"),
                    ),
                )
                documented_functions += 1
            continue

        property_match = PROPERTY_RE.match(line)
        if not property_match:
            continue
        kotlin_name = property_match.group(2)
        description = docs.members.get(kotlin_name)
        source = f"{docs.class_name}.{kotlin_name}"
        if description is None:
            description = docs.constants.get(kotlin_name)
            source = f"{docs.class_name}.{kotlin_name}"
        if description is None:
            continue
        insert_at = find_insertion_index(lines, index)
        replace_start = find_generated_kdoc_start(lines, index)
        replacements.append(
            Replacement(
                start=replace_start if replace_start is not None else insert_at,
                end=insert_at,
                lines=kdoc_block(property_match.group(1), description, source),
            ),
        )
        documented_members += 1
        seen_member_docs.add(kotlin_name)

    documented = documented_members + documented_functions
    available = set(docs.members) | set(docs.constants) | {name for name, _doc in docs.methods.items()}
    present = seen_member_docs | {
        method_docs[match.group(2)][0]
        for line in lines
        if (match := FUN_RE.match(line)) and match.group(2) in method_docs
    }
    missing = len(available - present)
    return replacements, documented, missing


def apply_replacements(path: Path, replacements: list[Replacement]) -> bool:
    original = path.read_text(encoding="utf-8").splitlines(keepends=True)
    updated = list(original)
    for replacement in sorted(replacements, key=lambda item: item.start, reverse=True):
        updated[replacement.start : replacement.end] = replacement.lines
    if updated == original:
        return False
    path.write_text("".join(updated), encoding="utf-8")
    return True


def parse_class_filter(value: str | None) -> set[str] | None:
    if not value:
        return None
    return {part.strip() for part in value.split(",") if part.strip()}


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--godot-docs", type=Path, default=DEFAULT_GODOT_DOCS)
    parser.add_argument("--api-dir", type=Path, default=DEFAULT_API_DIR)
    parser.add_argument("--types-dir", type=Path, default=DEFAULT_TYPES_DIR)
    parser.add_argument("--classes", help="Comma-separated API wrapper or builtin type names to sync.")
    parser.add_argument(
        "--scope",
        choices=("all", "api", "types"),
        default="all",
        help="Select Godot object wrappers, builtin value types, or both.",
    )
    parser.add_argument("--write", action="store_true", help="Write generated KDoc to wrapper files.")
    parser.add_argument("--check", action="store_true", help="Fail if generated KDoc is not up to date.")
    args = parser.parse_args()

    class_filter = parse_class_filter(args.classes)
    targets: list[tuple[str, Path]] = []
    if args.scope in ("all", "api"):
        targets.extend(("api", path) for path in sorted(args.api_dir.glob("*.kt")))
    if args.scope in ("all", "types"):
        targets.extend(("types", path) for path in sorted(args.types_dir.glob("*.kt")))
    changed: list[Path] = []
    total_classes = 0
    classes_with_docs = 0
    total_methods = 0
    missing_methods = 0

    for scope, path in targets:
        class_name = path.stem
        if class_filter is not None and class_name not in class_filter:
            continue
        total_classes += 1
        docs = load_godot_docs(args.godot_docs, class_name)
        if docs is None:
            print(f"[sync_kdoc] {class_name}: no Godot XML docs found")
            continue
        classes_with_docs += 1
        if scope == "api":
            replacements, documented_methods, missing = collect_api_replacements(path, docs)
        else:
            replacements, documented_methods, missing = collect_builtin_replacements(path, docs)
        total_methods += documented_methods
        missing_methods += missing
        documented_label = "documented_methods" if scope == "api" else "documented_items"
        missing_label = "missing_wrapped_method_docs" if scope == "api" else "unmatched_godot_docs"
        print(
            f"[sync_kdoc] {scope}/{class_name}: generated_blocks={len(replacements)} "
            f"{documented_label}={documented_methods} {missing_label}={missing}",
        )
        if replacements:
            before = path.read_text(encoding="utf-8")
            if args.write:
                if apply_replacements(path, replacements):
                    changed.append(path)
            else:
                lines = before.splitlines(keepends=True)
                after_lines = list(lines)
                for replacement in sorted(replacements, key=lambda item: item.start, reverse=True):
                    after_lines[replacement.start : replacement.end] = replacement.lines
                if "".join(after_lines) != before:
                    changed.append(path)

    print(
        f"[sync_kdoc] classes={total_classes} classes_with_docs={classes_with_docs} "
        f"documented_items={total_methods} missing_or_unmatched_docs={missing_methods} "
        f"changed_files={len(changed)}",
    )
    if changed:
        for path in changed:
            print(f"[sync_kdoc] changed {path}")
    if args.check and changed:
        return 1
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
