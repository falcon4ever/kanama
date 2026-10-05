#!/usr/bin/env python3
"""Gate: every hand-written Kotlin file under an `api/` directory is on the budget, and none grows.

Kanama's rule for hand-written code (task 129): the generated API tree is the default, and a file
under `api/` is written by hand only when it is
  * `seam`         -- the platform call mechanism (the Web bridge's handle, the iOS cinterop facade);
  * `runtime-core` -- runtime `extension_api.json` does not describe (lifetime roots, signal
                      plumbing, the main-thread queue, script resources);
  * `sugar`        -- GDScript-syntax sugar written once (`getNode<T>`, checked casts, `await`);
  * `transitional` -- a file that should be generated and is not yet, with the task-129 parcel that
                      retires it and a line-count ratchet.
The list lives in `scripts/hand_code_budget.json`. This gate fails when
  1. a hand-written `.kt` file under an `api/` directory is not listed (a new hand file needs a
     category, and a reviewer sees the budget change in the diff),
  2. a listed file is missing, or is generated now (the entry is stale),
  3. a `transitional` file has more lines than its ratchet (lines inside BEGIN/END GENERATED
     regions do not count), or carries no parcel,
  4. hand Kotlin inside a generator table grows: every key of the native `*_SECTIONS` tables of
     `generate_api_wrapper.py` and every string of the Web `CLASS_POLICY` (plus its `SUPPORT_FILE`)
     has a line ratchet too, so hand code cannot move into Python strings unseen.

"Generated" is not read from file headers: it is every write target of
`generate_api_wrapper.regenerate_tree()` (minus the hand files it only splices an enum region
into), the three `generate_name_constants.py` outputs and the Web `api/generated/` directory.

Usage:
    python3 scripts/check_hand_code_budget.py           # the gate
    python3 scripts/check_hand_code_budget.py --write   # lower every ratchet to the current count
                                                        # and drop entries whose file is gone;
                                                        # never adds a file or raises a count
"""

from __future__ import annotations

import argparse
import contextlib
import io
import json
import re
import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))

import generate_api_wrapper as gen  # noqa: E402
import generate_name_constants  # noqa: E402
import generate_web_wrappers as web  # noqa: E402

ROOT = Path(__file__).resolve().parents[1]
BUDGET = ROOT / "scripts/hand_code_budget.json"
CATEGORIES = ("seam", "runtime-core", "sugar", "transitional")
NATIVE_API_DIRS = (gen.SHARED_API_DIR, gen.DESKTOP_API_DIR, gen.IOS_API_DIR)
WEB_SRC = ROOT / "web-runtime/src"
GENERATED_REGION = re.compile(r"^\s*//\s*=+\s*BEGIN GENERATED .*?^\s*//\s*=+\s*END GENERATED[^\n]*\n?", re.M | re.S)
# The generator tables whose values are hand-written Kotlin (task 129 phase 0 counted 582 native
# lines and 580 Web lines in them).
NATIVE_SECTION_TABLES = (
    "SHARED_MEMBER_SECTIONS",
    "SHARED_COMPANION_MEMBER_SECTIONS",
    "DESKTOP_MEMBER_SECTIONS",
    "DESKTOP_COMPANION_MEMBER_SECTIONS",
    "IOS_MEMBER_SECTIONS",
    "IOS_COMPANION_MEMBER_SECTIONS",
    "DESKTOP_EXTENSION_SECTIONS",
    "IOS_EXTENSION_SECTIONS",
)


def rel(path: Path) -> str:
    return path.resolve().relative_to(ROOT).as_posix()


def hand_lines(text: str) -> int:
    """Lines of a file outside its generator-owned BEGIN/END GENERATED regions."""
    return len(GENERATED_REGION.sub("", text).splitlines())


def generated_paths() -> set[str]:
    api_path = ROOT / "extension_api.json"
    with contextlib.redirect_stdout(io.StringIO()):  # the generator's progress lines are not this gate's output
        tree = gen.regenerate_tree(api_path)
    hosts = {rel(path) for _owner, path, _target in gen.enum_region_hosts(gen.load_api_classes(api_path))}
    paths = {Path(p).as_posix() for p in tree.files} - hosts
    paths |= {rel(generate_name_constants.DEFAULT_OUTPUT_DIR / f"{n}.kt") for n in ("MethodName", "PropertyName", "SignalName")}
    return paths


def hand_files(generated: set[str]) -> dict[str, int]:
    files: list[Path] = []
    for directory in NATIVE_API_DIRS:
        if not directory.is_dir():
            raise SystemExit(f"[hand_code_budget] FAIL {rel(directory)} is missing; the gate scans it")
        files += directory.rglob("*.kt")
    if not WEB_SRC.is_dir():
        raise SystemExit(f"[hand_code_budget] FAIL {rel(WEB_SRC)} is missing; the gate scans it")
    web_generated = rel(web.GENERATED_DIR)
    files += [
        p for p in WEB_SRC.rglob("*.kt")
        if "/net/multigesture/kanama/api/" in p.as_posix() and not rel(p).startswith(web_generated + "/")
    ]
    result = {rel(p): hand_lines(p.read_text(encoding="utf-8")) for p in files if rel(p) not in generated}
    if not result:
        raise SystemExit("[hand_code_budget] FAIL found no hand-written api/ file at all; the scan is broken")
    return result


def section_lines() -> dict[str, int]:
    """Line count of each hand-written Kotlin string a generator table carries."""
    counts: dict[str, int] = {}
    for table in NATIVE_SECTION_TABLES:
        for key, value in getattr(gen, table).items():
            text = value[1] if isinstance(value, tuple) else value
            counts[f"generate_api_wrapper.{table}[{key}]"] = len(text.strip("\n").splitlines())
    for key, policy in web.CLASS_POLICY.items():
        for field in ("custom", "companion", "top_level"):
            if isinstance(policy.get(field), str):
                counts[f"generate_web_wrappers.CLASS_POLICY[{key}].{field}"] = len(policy[field].strip("\n").splitlines())
    counts["generate_web_wrappers.SUPPORT_FILE"] = len(web.SUPPORT_FILE.strip("\n").splitlines())
    if not counts:
        raise SystemExit("[hand_code_budget] FAIL found no generator section table; the scan is broken")
    return counts


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    parser.add_argument("--write", action="store_true", help="lower ratchets to the current counts, drop gone entries")
    args = parser.parse_args()

    budget = json.loads(BUDGET.read_text(encoding="utf-8"))
    listed: dict[str, dict] = budget["files"]
    sections: dict[str, int] = budget["sections"]
    generated = generated_paths()
    current = hand_files(generated)
    current_sections = section_lines()

    if args.write:
        for path in sorted(listed):
            entry = listed[path]
            if not (ROOT / path).exists() or path in generated:
                del listed[path]
            elif entry.get("category") == "transitional":
                entry["lines"] = min(entry["lines"], current[path])
        for key in sorted(sections):
            if key not in current_sections:
                del sections[key]
            else:
                sections[key] = min(sections[key], current_sections[key])
        BUDGET.write_text(json.dumps(budget, indent=2, sort_keys=True) + "\n", encoding="utf-8")
        print(f"[hand_code_budget] wrote {rel(BUDGET)}")

    problems: list[str] = []
    lowerable: list[str] = []
    for path, lines in sorted(current.items()):
        entry = listed.get(path)
        if entry is None:
            problems.append(f"{path}: hand-written ({lines} lines) and not on the budget -- generate it, or add it to "
                            f"{rel(BUDGET)} with a category ({', '.join(CATEGORIES)})")
            continue
        category = entry.get("category")
        if category not in CATEGORIES:
            problems.append(f"{path}: category {category!r} is not one of {', '.join(CATEGORIES)}")
        elif category == "transitional":
            if not entry.get("parcel"):
                problems.append(f"{path}: transitional without the parcel that retires it")
            if not isinstance(entry.get("lines"), int):
                problems.append(f"{path}: transitional without a line ratchet")
            elif lines > entry["lines"]:
                problems.append(f"{path}: transitional file grew to {lines} hand lines (ratchet {entry['lines']}); "
                                "generate the addition instead")
            elif lines < entry["lines"]:
                lowerable.append(f"{path} {entry['lines']} -> {lines}")
        if not entry.get("reason"):
            problems.append(f"{path}: no reason recorded")
    for path in sorted(set(listed) - set(current)):
        state = "is generated now" if path in generated else "does not exist"
        problems.append(f"{path}: listed on the budget but {state} -- drop the entry (--write)")
    for key, lines in sorted(current_sections.items()):
        if key not in sections:
            problems.append(f"{key}: hand-written Kotlin in a generator table ({lines} lines) and not on the budget")
        elif lines > sections[key]:
            problems.append(f"{key}: grew to {lines} lines (ratchet {sections[key]})")
        elif lines < sections[key]:
            lowerable.append(f"{key} {sections[key]} -> {lines}")
    for key in sorted(set(sections) - set(current_sections)):
        problems.append(f"{key}: listed on the budget but the section is gone -- drop the entry (--write)")

    totals: dict[str, int] = {}
    for path, lines in current.items():
        category = listed.get(path, {}).get("category", "unlisted")
        totals[category] = totals.get(category, 0) + lines
    summary = ", ".join(f"{name}={totals[name]}" for name in sorted(totals))
    if problems:
        print(f"[hand_code_budget] FAIL {len(problems)} problem(s):", file=sys.stderr)
        for problem in problems:
            print(f"    {problem}", file=sys.stderr)
        return 1
    print(f"[hand_code_budget] PASS {len(current)} hand-written api/ files ({summary} lines); "
          f"{len(current_sections)} generator sections, {sum(current_sections.values())} lines")
    for item in lowerable:
        print(f"[hand_code_budget] note: ratchet can be lowered (--write): {item}")
    return 0


if __name__ == "__main__":
    sys.exit(main())
