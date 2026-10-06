#!/usr/bin/env python3
"""Gate: every hand-written Kotlin file of the API package is on the budget, and none grows.

Kanama's rule for hand-written code (task 129): the generated API tree is the default, and a file
of the API (under an `api/` directory, or declaring `package net.multigesture.kanama.api...`
anywhere in `src/` or `web-runtime/src/`) is written by hand only when it is
  * `seam`         -- the platform call mechanism (the Web bridge's handle, the iOS cinterop facade);
  * `runtime-core` -- runtime `extension_api.json` does not describe (lifetime roots, signal
                      plumbing, the main-thread queue, script resources);
  * `sugar`        -- GDScript-syntax sugar written once (`getNode<T>`, checked casts, `await`);
  * `transitional` -- a file that should be generated and is not yet, with the task-129 parcel that
                      retires it.
Every file carries a line ratchet. The list lives in `scripts/hand_code_budget.json`. The gate fails
when
  1. a hand-written API file is not listed (a new hand file needs a category, and a reviewer sees
     the budget change in the diff),
  2. a listed file is missing, or is generated now (the entry is stale),
  3. a listed file has more lines than its ratchet. Only the GENERATED ENUMS regions a generator
     really splices into that file (`generate_api_wrapper.enum_region_hosts`,
     `generate_web_wrappers.hand_enum_hosts`) are left out of the count; a BEGIN/END GENERATED
     marker pair in any other API file is itself a failure, so markers cannot hide hand code,
  4. hand Kotlin inside a generator table grows: every key of the native `*_SECTIONS` tables and
     the `METHOD_PRECONDITIONS` rows of `generate_api_wrapper.py`, and every string of the Web
     `CLASS_POLICY` (plus its `SUPPORT_FILE`), has a line ratchet too, so hand code cannot move into
     Python strings unseen.

A ratchet only goes down by itself. A seam, runtime-core or sugar file (or a generator section) that
legitimately grows raises its ratchet with `--write --reason "<why>"`, which records the reason in
the JSON next to the new count. A transitional file never grows: an API addition is generated.

"Generated" is not read from file headers: it is every write target of
`generate_api_wrapper.regenerate_tree()` (minus the hand files it only splices an enum region
into), the three `generate_name_constants.py` outputs and the Web `api/generated/` directory.

Usage:
    python3 scripts/check_hand_code_budget.py                    # the gate
    python3 scripts/check_hand_code_budget.py --write            # lower every ratchet to the current
                                                                 # count, drop entries whose file is gone
    python3 scripts/check_hand_code_budget.py --write --reason "why"
                                                                 # also raise the ratchet of every grown
                                                                 # non-transitional file and section
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
RAISABLE = ("seam", "runtime-core", "sugar")
SCAN_ROOTS = (ROOT / "src", ROOT / "web-runtime/src")
API_PACKAGE = re.compile(r"^package\s+net\.multigesture\.kanama\.api(\.|\s*$)", re.M)
ENUM_REGION = re.compile(
    r"^[ \t]*//\s*=+\s*BEGIN GENERATED ENUMS: .*?^[ \t]*//\s*=+\s*END GENERATED ENUMS[^\n]*\n?", re.M | re.S
)
ANY_MARKER = re.compile(r"^[ \t]*//\s*=+\s*(BEGIN|END) GENERATED", re.M)
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
    "GD_MEMBER_SECTIONS",
)


def rel(path: Path) -> str:
    return path.resolve().relative_to(ROOT).as_posix()


def generated_paths() -> tuple[set[str], set[str]]:
    """(generated files, hand files a generator splices GENERATED ENUMS regions into)."""
    api_path = ROOT / "extension_api.json"
    with contextlib.redirect_stdout(io.StringIO()):  # the generator's progress lines are not this gate's output
        tree = gen.regenerate_tree(api_path)
        native_hosts = {rel(path) for _owner, path, _target in gen.enum_region_hosts(gen.load_api_classes(api_path))}
        web_hosts = {rel(path) for path in web.hand_enum_hosts(web.Api(), web.API_DIR).values()}
    paths = {Path(p).as_posix() for p in tree.files} - native_hosts
    paths |= {rel(generate_name_constants.DEFAULT_OUTPUT_DIR / f"{n}.kt") for n in ("MethodName", "PropertyName", "SignalName")}
    return paths, native_hosts | web_hosts


def is_api_file(path: Path) -> bool:
    if "api" in path.relative_to(ROOT).parts:
        return True
    return bool(API_PACKAGE.search(path.read_text(encoding="utf-8")))


def hand_files(generated: set[str], hosts: set[str], problems: list[str]) -> dict[str, int]:
    for root in SCAN_ROOTS:
        if not root.is_dir():
            raise SystemExit(f"[hand_code_budget] FAIL {rel(root)} is missing; the gate scans it")
    web_generated = rel(web.GENERATED_DIR) + "/"
    result: dict[str, int] = {}
    for root in SCAN_ROOTS:
        for path in sorted(root.rglob("*.kt")):
            name = rel(path)
            if "/build/" in f"/{name}" or name in generated or name.startswith(web_generated) or not is_api_file(path):
                continue
            text = path.read_text(encoding="utf-8")
            if name in hosts:
                text = ENUM_REGION.sub("", text)
            elif ANY_MARKER.search(text):
                problems.append(f"{name}: carries a BEGIN/END GENERATED marker, but no generator owns a region in it "
                                "-- markers do not exempt hand code from the budget; remove them")
            result[name] = len(text.splitlines())
    if not result:
        raise SystemExit("[hand_code_budget] FAIL found no hand-written API file at all; the scan is broken")
    return result


def section_lines() -> dict[str, int]:
    """Line count of each hand-written Kotlin string a generator table carries."""
    counts: dict[str, int] = {}
    for table in NATIVE_SECTION_TABLES:
        for key, value in getattr(gen, table).items():
            text = value[1] if isinstance(value, tuple) else value
            counts[f"generate_api_wrapper.{table}[{key}]"] = len(text.strip("\n").splitlines())
    # Task 129 C: the generator's precondition hook carries Kotlin statements too, keyed by
    # (class, Godot method).
    for (owner, method), statements in gen.METHOD_PRECONDITIONS.items():
        counts[f"generate_api_wrapper.METHOD_PRECONDITIONS[{owner}.{method}]"] = sum(
            len(statement.strip("\n").splitlines()) for statement in statements
        )
    for key, policy in web.CLASS_POLICY.items():
        for field in ("custom", "companion", "top_level"):
            if isinstance(policy.get(field), str):
                counts[f"generate_web_wrappers.CLASS_POLICY[{key}].{field}"] = len(policy[field].strip("\n").splitlines())
    counts["generate_web_wrappers.SUPPORT_FILE"] = len(web.SUPPORT_FILE.strip("\n").splitlines())
    if not counts:
        raise SystemExit("[hand_code_budget] FAIL found no generator section table; the scan is broken")
    return counts


def write(budget: dict, current: dict[str, int], sections_now: dict[str, int], generated: set[str], reason: str | None) -> None:
    listed: dict[str, dict] = budget["files"]
    sections: dict[str, dict] = budget["sections"]
    for path in sorted(listed):
        entry = listed[path]
        if path not in current or path in generated:
            del listed[path]
            continue
        now = current[path]
        if now <= entry["lines"]:
            entry["lines"] = now
        elif reason and entry.get("category") in RAISABLE:
            entry.setdefault("raises", []).append(f"{entry['lines']} -> {now}: {reason}")
            entry["lines"] = now
    for key in sorted(sections):
        if key not in sections_now:
            del sections[key]
            continue
        now = sections_now[key]
        if now <= sections[key]["lines"]:
            sections[key]["lines"] = now
        elif reason:
            sections[key].setdefault("raises", []).append(f"{sections[key]['lines']} -> {now}: {reason}")
            sections[key]["lines"] = now
    BUDGET.write_text(json.dumps(budget, indent=2, sort_keys=True) + "\n", encoding="utf-8")
    print(f"[hand_code_budget] wrote {rel(BUDGET)}")


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    parser.add_argument("--write", action="store_true", help="lower ratchets to the current counts, drop gone entries")
    parser.add_argument("--reason", help="with --write: raise the ratchet of every grown seam/runtime-core/sugar "
                        "file and generator section, recording this reason")
    args = parser.parse_args()
    if args.reason is not None and (not args.write or not args.reason.strip()):
        parser.error("--reason needs --write and a non-empty reason")

    budget = json.loads(BUDGET.read_text(encoding="utf-8"))
    problems: list[str] = []
    generated, hosts = generated_paths()
    current = hand_files(generated, hosts, problems)
    current_sections = section_lines()
    if args.write:
        write(budget, current, current_sections, generated, args.reason)
    listed: dict[str, dict] = budget["files"]
    sections: dict[str, dict] = budget["sections"]

    raise_hint = (f"if this is a legitimate seam/runtime-core/sugar change, raise the ratchet with "
                  f"`python3 scripts/check_hand_code_budget.py --write --reason \"<why>\"`; "
                  "an API addition is generated instead")
    lowerable: list[str] = []
    for path, lines in sorted(current.items()):
        entry = listed.get(path)
        if entry is None:
            problems.append(f"{path}: hand-written ({lines} lines) and not on the budget -- generate it, or add it to "
                            f"{rel(BUDGET)} with a category ({', '.join(CATEGORIES)}), a reason and its line count")
            continue
        category = entry.get("category")
        if category not in CATEGORIES:
            problems.append(f"{path}: category {category!r} is not one of {', '.join(CATEGORIES)}")
        if category == "transitional" and not entry.get("parcel"):
            problems.append(f"{path}: transitional without the parcel that retires it")
        if not entry.get("reason"):
            problems.append(f"{path}: no reason recorded")
        if not isinstance(entry.get("lines"), int):
            problems.append(f"{path}: no line ratchet")
        elif lines > entry["lines"]:
            if category == "transitional":
                problems.append(f"{path}: transitional file grew to {lines} hand lines (ratchet {entry['lines']}); "
                                "it is due to be generated, so generate the addition instead")
            else:
                problems.append(f"{path}: {category} file grew to {lines} hand lines (ratchet {entry['lines']}); "
                                + raise_hint)
        elif lines < entry["lines"]:
            lowerable.append(f"{path} {entry['lines']} -> {lines}")
    for path in sorted(set(listed) - set(current)):
        state = "is generated now" if path in generated else "is not a hand-written API file (gone or moved)"
        problems.append(f"{path}: listed on the budget but {state} -- drop the entry (--write)")
    for key, lines in sorted(current_sections.items()):
        entry = sections.get(key)
        if entry is None:
            problems.append(f"{key}: hand-written Kotlin in a generator table ({lines} lines) and not on the budget")
        elif lines > entry["lines"]:
            problems.append(f"{key}: grew to {lines} lines (ratchet {entry['lines']}); " + raise_hint)
        elif lines < entry["lines"]:
            lowerable.append(f"{key} {entry['lines']} -> {lines}")
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
    print(f"[hand_code_budget] PASS {len(current)} hand-written API files ({summary} lines); "
          f"{len(current_sections)} generator sections, {sum(current_sections.values())} lines")
    for item in lowerable:
        print(f"[hand_code_budget] note: ratchet can be lowered (--write): {item}")
    return 0


if __name__ == "__main__":
    sys.exit(main())
