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
from wrapper_model import DESKTOP_API_DIR, IOS_API_DIR, ROOT, wrapper_source_files
from godot_enum_model import GLOBAL_ENUM_RENAMES, enum_value_name, load_enums, read_lock

# The api scope covers the shared wrapper tree plus the desktop per-platform files and their
# generated companions (task 103); pass --api-dir to sync one directory only.
DEFAULT_API_DIR = DESKTOP_API_DIR
# The value types are one shared set under the commonMain tree since task 104 step 2
# (the root JVM module, :ios-runtime and the Android copy task all compile it).
DEFAULT_TYPES_DIR = Path("src/commonMain/kotlin/net/multigesture/kanama/types")
GENERATED_MARKER = "Generated from Godot docs:"

METHOD_BIND_RE = re.compile(
    r"ObjectCalls\.getMethodBind\(\s*"
    r'"([^"]+)"\s*,\s*"([^"]+)"\s*,\s*([A-Z0-9_]+|\d+L?)',
    re.DOTALL,
)
# The optional receiver (`fun Time.getX(`, `fun X.Companion.create(`) is the generated desktop
# companion form (`<Class>.jvm.kt`, task 103): extension members over a shared-tree class.
FUN_RE = re.compile(r"^(\s*)(?:public\s+)?fun\s+(?:<[^>]+>\s+)?(?:[A-Za-z_][\w.]*\.)?([A-Za-z_][A-Za-z0-9_]*)\s*\(")
CLASS_RE = re.compile(r"^(\s*)(?:(?:actual|open|abstract|sealed|data|value)\s+)*class\s+([A-Za-z_][A-Za-z0-9_]*)\b")
OBJECT_RE = re.compile(r"^(\s*)(?:actual\s+)?object\s+([A-Za-z_][A-Za-z0-9_]*)\b")
# Typed enums (task 128 A): a nested / global `value class X(val value: Long)` and its companion
# values (`val ALWAYS: ProcessMode get() = ProcessMode(3L)`, the actual form `actual val ...`,
# the expect form `val ALWAYS: ProcessMode`). Each value gets the Godot doc of the constant it maps to.
ENUM_CLASS_RE = re.compile(r"^\s*(?:(?:actual|expect)\s+)?value\s+class\s+([A-Za-z_][A-Za-z0-9_]*)\b")
ENUM_VALUE_RE = re.compile(
    r"^(\s*)(?:actual\s+)?val\s+([A-Z][A-Z0-9_]*)\s*:\s*([A-Za-z_]\w*)(?:\s*get\(\)\s*=\s*([A-Za-z_]\w*)\(-?\d+L\))?\s*$"
)
# The file of the @GlobalScope enums; its docs live in @GlobalScope.xml.
GLOBAL_ENUMS_STEM = "GlobalEnums"
GLOBAL_SCOPE_DOCS = "@GlobalScope"
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
    text = re.sub(
        r"\[constant\s+([^\]]+)\]", lambda m: f"`{rewrite_constant_reference(m.group(1), _DOC_CONTEXT_CLASS)}`", text
    )
    text = re.sub(r"\[(?:param|method|member|constant|enum|signal|annotation|theme_item)\s+([^\]]+)\]", r"`\1`", text)
    text = re.sub(r"\[([A-Za-z_@][A-Za-z0-9_@.]*)\]", r"`\1`", text)
    text = re.sub(r"\[/[A-Za-z_]+\]", "", text)
    text = first_paragraph(text)
    text = re.sub(r"\s+", " ", text).strip()
    text = re.sub(r"\s+Example:.*$", "", text).strip()
    text = text.replace("/*", "/\\*").replace("*/", "* /")
    return text or None


def load_godot_docs(docs_dir: Path, class_name: str) -> GodotClassDocs | None:
    global _DOC_CONTEXT_CLASS
    path = docs_dir / f"{class_name}.xml"
    if not path.exists():
        return None
    _DOC_CONTEXT_CLASS = None if class_name == GLOBAL_SCOPE_DOCS else class_name
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


def kdoc_block(indent: str, description: str, source: str, *, ktfmt_width: bool = False) -> list[str]:
    lines = [f"{indent}/**\n"]
    words = description.split(" ")
    current = ""
    # api/ wrappers keep the historical fixed 96-char content width: they are excluded from
    # ktfmt and byte-compared against generate_api_wrapper.py, so their wrapping must not move.
    # types/ value-type wrappers ARE ktfmt-formatted, so wrap them to the emitted line's 100-col
    # budget — the width ktfmt (googleStyle) reflows to. A fixed content width ignored the
    # indented ` * ` prefix and emitted 101-char lines that ktfmt re-wrapped, leaving sync_kdoc
    # --check and ktfmtCheck permanently fighting over that one column.
    def fits(candidate: str) -> bool:
        if ktfmt_width:
            return len(indent) + len(" * ") + len(candidate) <= 100
        return len(candidate) <= 96

    for word in words:
        candidate = word if not current else f"{current} {word}"
        if not fits(candidate) and current:
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

    replacements.extend(collect_enum_value_replacements(lines, docs, path.stem.split(".")[0]))

    documented_godot_methods = {
        method_docs[match.group(2)][0]
        for line in lines
        if (match := FUN_RE.match(line)) and match.group(2) in method_docs
    }
    missing_methods = len(wrapped_methods - documented_godot_methods)
    return replacements, documented_methods, missing_methods


_ENUM_NAME_MAPS: dict[str, dict[str, str]] | None = None
_ENUM_BITFIELDS: dict[str, bool] | None = None
_CONSTANT_SPELLINGS: dict[str | None, dict[str, tuple[str, str]]] | None = None
_INHERITS: dict[str, str] | None = None
KOTLIN_OWNER = {"Object": "GodotObject"}


def _enum_bitfields() -> dict[str, bool]:
    global _ENUM_BITFIELDS
    if _ENUM_BITFIELDS is None:
        _ENUM_BITFIELDS = {key: spec.is_bitfield for key, spec in load_enums(ROOT / "extension_api.json").items()}
    return _ENUM_BITFIELDS


def _constant_spellings() -> dict[str | None, dict[str, tuple[str, str]]]:
    """Godot owner (None = @GlobalScope) -> {Godot constant name: (enum simple name, Kotlin spelling
    from outside the owner)} through the generator's naming function and the frozen lock."""
    global _CONSTANT_SPELLINGS
    if _CONSTANT_SPELLINGS is None:
        lock = read_lock()
        _CONSTANT_SPELLINGS = {}
        for key, spec in load_enums(ROOT / "extension_api.json").items():
            prefix = lock.get(key)
            if prefix is None:
                continue
            table = _CONSTANT_SPELLINGS.setdefault(spec.owner, {})
            for value in spec.values:
                table.setdefault(value.name, (spec.simple_name, f"{spec.kotlin_name}.{enum_value_name(prefix, value.name)}"))
    return _CONSTANT_SPELLINGS


def _inherits() -> dict[str, str]:
    global _INHERITS
    if _INHERITS is None:
        import json

        data = json.loads((ROOT / "extension_api.json").read_text(encoding="utf-8"))
        _INHERITS = {c["name"]: c.get("inherits") or "" for c in data.get("classes", [])}
    return _INHERITS


def rewrite_constant_reference(ref: str, context_class: str | None) -> str:
    """`[constant X]` in Godot's docs, spelled the Kanama way since task 128 A: an enum value becomes
    its typed spelling (`PROCESS_MODE_WHEN_PAUSED` in Node's docs -> `ProcessMode.WHEN_PAUSED`,
    `Node.PROCESS_MODE_ALWAYS` -> `Node.ProcessMode.ALWAYS`, `@GlobalScope.KEY_A` -> `Key.A`). A
    constant that is not an enum value keeps Godot's name."""
    tables = _constant_spellings()
    if "." in ref:
        owner, name = ref.rsplit(".", 1)
        owner = None if owner == "@GlobalScope" else owner
        hit = tables.get(owner, {}).get(name)
        return hit[1] if hit else ref
    cursor = context_class
    while cursor:
        hit = tables.get(cursor, {}).get(ref)
        if hit:
            return f"{hit[0]}.{hit[1].rsplit('.', 1)[1]}" if cursor == context_class else hit[1]
        cursor = _inherits().get(cursor)
    hit = tables.get(None, {}).get(ref)
    return hit[1] if hit else ref


_DOC_CONTEXT_CLASS: str | None = None


def _enum_name_maps() -> dict[str, dict[str, str]]:
    """Godot enum key -> {Kotlin value name: Godot constant name}, through the generator's one naming
    function and the frozen prefix lock."""
    global _ENUM_NAME_MAPS
    if _ENUM_NAME_MAPS is None:
        lock = read_lock()
        _ENUM_NAME_MAPS = {}
        for key, spec in load_enums(ROOT / "extension_api.json").items():
            prefix = lock.get(key)
            if prefix is None:
                continue
            _ENUM_NAME_MAPS[key] = {enum_value_name(prefix, value.name): value.name for value in spec.values}
    return _ENUM_NAME_MAPS


TOP_LEVEL_RE = re.compile(r"^(?:(?:public|internal|actual|expect|open|abstract|sealed|data)\s+)*(?:class|object)\s+(\w+)")
# Kotlin owner names that are not their Godot class's name.
KOTLIN_TO_GODOT = {"GodotObject": "Object", "FileAccessHandle": "FileAccess", "DirAccessHandle": "DirAccess"}


def collect_enum_value_replacements(
    lines: list[str], docs: GodotClassDocs | None, file_stem: str, docs_for=None, ktfmt_width: bool = False
) -> list[Replacement]:
    """KDoc for each typed enum in [lines] (task 128 A): a one-line class doc (Godot's XML documents
    enum values, not enums) and each value's Godot constant doc. The owner of a nested enum is the
    enclosing top-level class; [docs_for] loads another owner's docs (files that host several
    classes, such as iOS IosGodotApi.kt)."""
    replacements: list[Replacement] = []
    renamed = {kotlin: godot for godot, kotlin in GLOBAL_ENUM_RENAMES.items()}
    current: str | None = None
    current_simple = ""
    current_docs = docs
    top_owner: str | None = None
    for index, line in enumerate(lines):
        top = TOP_LEVEL_RE.match(line)
        if top:
            top_owner = KOTLIN_TO_GODOT.get(top.group(1), top.group(1))
        enum_match = ENUM_CLASS_RE.match(line)
        if enum_match:
            current_simple = enum_match.group(1)
            if file_stem == GLOBAL_ENUMS_STEM:
                current = renamed.get(current_simple, current_simple)
                current_docs = docs
                source = f"{GLOBAL_SCOPE_DOCS}.{current}"
                shown = GLOBAL_ENUM_RENAMES.get(current, current)
            else:
                owner = top_owner if (docs_for is not None and top_owner) else (docs.class_name if docs else None)
                if owner is None:
                    current = None
                    continue
                current = f"{owner}.{current_simple}"
                current_docs = docs if (docs is not None and docs.class_name == owner) else (docs_for(owner) if docs_for else None)
                source = current
                shown = f"{KOTLIN_OWNER.get(owner, owner)}.{current_simple}"
            if current in _enum_name_maps():
                kind = "bitfield" if _enum_bitfields().get(current) else "enum"
                description = (
                    f"Godot's `{current}` {kind} as a typed value: `.value` is the raw number Godot uses, and "
                    f"the companion holds the named values (`{shown}.<NAME>`)."
                )
                indent = re.match(r"\s*", line).group(0)
                insert_at = find_insertion_index(lines, index)
                replace_start = find_generated_kdoc_start(lines, index)
                replacements.append(
                    Replacement(
                        start=replace_start if replace_start is not None else insert_at,
                        end=insert_at,
                        lines=kdoc_block(indent, description, source, ktfmt_width=ktfmt_width),
                    ),
                )
            continue
        if current is None or current_docs is None:
            continue
        value_match = ENUM_VALUE_RE.match(line)
        if not value_match or (value_match.group(3) or value_match.group(4)) != current_simple:
            continue
        godot_name = _enum_name_maps().get(current, {}).get(value_match.group(2))
        description = current_docs.constants.get(godot_name) if godot_name else None
        if not description:
            continue
        insert_at = find_insertion_index(lines, index)
        replace_start = find_generated_kdoc_start(lines, index)
        source = f"{GLOBAL_SCOPE_DOCS if file_stem == GLOBAL_ENUMS_STEM else current_docs.class_name}.{godot_name}"
        replacements.append(
            Replacement(
                start=replace_start if replace_start is not None else insert_at,
                end=insert_at,
                lines=kdoc_block(value_match.group(1), description, source, ktfmt_width=ktfmt_width),
            ),
        )
    return replacements


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
                    lines=kdoc_block(class_match.group(1), description, docs.class_name, ktfmt_width=True),
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
                        lines=kdoc_block(fun_match.group(1), description, f"{docs.class_name}.{godot_name}", ktfmt_width=True),
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
                lines=kdoc_block(property_match.group(1), description, source, ktfmt_width=True),
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
        targets.extend(("api", path) for path in wrapper_source_files(args.api_dir, companions=True))
    if args.scope in ("all", "types"):
        targets.extend(("types", path) for path in sorted(args.types_dir.glob("*.kt")))
    changed: list[Path] = []
    total_classes = 0
    classes_with_docs = 0
    total_methods = 0
    missing_methods = 0

    for scope, path in targets:
        class_name = path.stem.split(".")[0]  # `Time.jvm.kt` documents Time
        if class_name == GLOBAL_ENUMS_STEM:
            class_name = GLOBAL_SCOPE_DOCS
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
            # The builtin value types' generated enums (`Vector3.Axis`, task 128 A); these files are
            # ktfmt-formatted, so the blocks wrap to ktfmt's width.
            replacements.extend(
                collect_enum_value_replacements(
                    path.read_text(encoding="utf-8").splitlines(keepends=True), docs, class_name, ktfmt_width=True
                )
            )
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

    # The iOS sources (task 128 A review): only their typed enums are documented here -- the iOS-only
    # generated classes and the GENERATED ENUMS regions of the hand-written iOS classes get the same
    # class and value KDoc as desktop. A file hosting several classes (IosGodotApi.kt) documents each
    # enum from its enclosing class's docs.
    if args.scope in ("all", "api") and args.api_dir == DEFAULT_API_DIR:
        docs_cache: dict[str, GodotClassDocs | None] = {}

        def docs_for(owner: str) -> GodotClassDocs | None:
            if owner not in docs_cache:
                docs_cache[owner] = load_godot_docs(args.godot_docs, owner)
            return docs_cache[owner]

        for path in sorted(IOS_API_DIR.glob("*.kt")):
            lines = path.read_text(encoding="utf-8").splitlines(keepends=True)
            ios_replacements = collect_enum_value_replacements(lines, None, path.stem, docs_for=docs_for)
            if not ios_replacements:
                continue
            after_lines = list(lines)
            for replacement in sorted(ios_replacements, key=lambda item: item.start, reverse=True):
                after_lines[replacement.start : replacement.end] = replacement.lines
            if "".join(after_lines) != "".join(lines):
                if args.write:
                    path.write_text("".join(after_lines), encoding="utf-8")
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
