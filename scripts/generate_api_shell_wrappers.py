#!/usr/bin/env python3
"""Generate handle-only API shell wrappers from extension_api.json.

Shell wrappers intentionally do not emit method binds. They only provide a
typed handle and nullable wrap helper so the conservative method generator can
type object returns and arguments without falling back to dynamic calls.
"""

from __future__ import annotations

import argparse
import re
from pathlib import Path

from wrapper_model import load_api_classes, scan_wrapper_classes
from wrapper_model import SHARED_API_DIR


ROOT = Path(__file__).resolve().parents[1]
API_PATH = ROOT / "extension_api.json"
API_DIR = ROOT / "src/main/kotlin/net/multigesture/kanama/api"

MISSING_WRAPPER_RE = re.compile(
    r"(?:object|typed object-array) (?:return|argument) wrapper is missing for ([A-Za-z_]\w*)",
)
UNSAFE_DEFAULT_EXCLUDES = {"Callable", "DirAccess", "FileAccess", "Object", "SceneTree"}


def open_wrapper_classes(api_dir: Path) -> set[str]:
    names: set[str] = set()
    for path in api_dir.glob("*.kt"):
        text = path.read_text(encoding="utf-8")
        match = re.search(r"\bopen\s+class\s+([A-Za-z_]\w*)\b", text)
        if match:
            names.add(match.group(1))
    names.add("GodotObject")
    return names


def classes_from_skip_report(path: Path) -> list[str]:
    names: set[str] = set()
    for line in path.read_text(encoding="utf-8").splitlines():
        match = MISSING_WRAPPER_RE.search(line)
        if match:
            names.add(match.group(1))
    return sorted(names)


def ancestor_chain(class_name: str, api_classes: dict) -> list[str]:
    chain: list[str] = []
    current = api_classes.get(class_name)
    while current and current.inherits:
        parent = current.inherits
        if parent in chain:
            break
        chain.append(parent)
        current = api_classes.get(parent)
    return chain


def nearest_base(
    class_name: str,
    api_classes: dict,
    generated: set[str],
    open_existing: set[str],
) -> str:
    chain = ancestor_chain(class_name, api_classes)
    if not chain or chain[0] == "Object":
        return "GodotObject"
    for ancestor in chain:
        if ancestor in generated or ancestor in open_existing:
            return ancestor
    return "GodotObject"


def inheritance_depth(class_name: str, api_classes: dict) -> int:
    return len(ancestor_chain(class_name, api_classes))


def render_shell(class_name: str, base_name: str) -> str:
    return "\n".join(
        [
            "package net.multigesture.kanama.api",
            "",
            "import java.lang.foreign.MemorySegment",
            "",
            "/**",
            f" * Generated shell wrapper from Godot API metadata: {class_name}.",
            " */",
            f"open class {class_name} internal constructor(handle: MemorySegment) : {base_name}(handle) {{",
            "    companion object {",
            "        @JvmStatic",
            f"        fun fromHandle(handle: MemorySegment): {class_name}? =",
            "            wrap(handle)",
            "",
            f"        internal fun wrap(handle: MemorySegment): {class_name}? =",
            f"            if (handle.address() == 0L) null else {class_name}(handle)",
            "    }",
            "}",
            "",
        ],
    )


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--api", type=Path, default=API_PATH)
    parser.add_argument("--api-dir", type=Path, default=API_DIR, help="Platform directory whose wrapper set (shared tree included) counts as existing.")
    parser.add_argument("--output-dir", type=Path, default=SHARED_API_DIR, help="Where new shell wrappers are written (default: the shared tree).")
    parser.add_argument("--from-skip-report", type=Path)
    parser.add_argument("--class", dest="classes", action="append", default=[])
    parser.add_argument("--exclude", action="append", default=[])
    parser.add_argument("--dry-run", action="store_true")
    parser.add_argument("--fail-if-candidates", action="store_true")
    args = parser.parse_args()

    api_classes = load_api_classes(args.api)
    existing = scan_wrapper_classes(args.api_dir)
    open_existing = open_wrapper_classes(args.api_dir)
    excludes = UNSAFE_DEFAULT_EXCLUDES | set(args.exclude)

    requested = set(args.classes)
    if args.from_skip_report:
        requested.update(classes_from_skip_report(args.from_skip_report))

    candidates = sorted(
        (
            name
            for name in requested
            if name in api_classes and name not in existing and name not in excludes
        ),
        key=lambda name: (inheritance_depth(name, api_classes), name),
    )

    generated: set[str] = set()
    for class_name in candidates:
        base_name = nearest_base(class_name, api_classes, generated, open_existing)
        output = args.output_dir / f"{class_name}.kt"
        if args.dry_run:
            print(f"{class_name}: {base_name}")
            continue
        output.write_text(render_shell(class_name, base_name), encoding="utf-8")
        generated.add(class_name)
        open_existing.add(class_name)
        print(f"[generate_api_shell_wrappers] wrote {output.relative_to(ROOT)} : {base_name}")

    if args.dry_run:
        print(f"[generate_api_shell_wrappers] candidates={len(candidates)}")
        if candidates and args.fail_if_candidates:
            return 1
    else:
        print(f"[generate_api_shell_wrappers] wrote={len(generated)}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
