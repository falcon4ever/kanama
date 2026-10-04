#!/usr/bin/env python3
"""Gate: the runtime sources survive the Android PanamaPort source remap (task 131).

Android compiles a textual COPY of `src/jvmMain/kotlin` and `src/commonMain/kotlin` in which
`KanamaAndroidRemap.remapLine` rewrites every `.invoke(` to `.invokeWithArguments(` -- right for a
`MethodHandle`, a compile error for a Kotlin function value -- and then undoes it for a short list
of known callback call sites. `:plugin:auditAndroidKanamaSources` catches the result, but only in
an Android build, which most desktop runs never start: task 131 shipped
`callbacks[id]?.callback?.invoke(args)` green on desktop and the Pixel lane failed before install.

This gate replays the remap on the desktop: it reads the rules and the forbidden fragments out of
`android/godot-plugin/buildSrc/src/main/kotlin/KanamaAndroidRemap.kt` (so it cannot drift from
them), applies the rules line by line to the same two trees (skipping `*.expect.kt`, as the copy
does), strips comments, and fails on any forbidden fragment -- the same verdict the Gradle audit
would reach. Call a function value as `f(args)` or `f?.let { it(args) }`, never `.invoke(`.

Usage:
    python3 scripts/check_android_remap_sources.py
"""

from __future__ import annotations

import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
REMAP = ROOT / "android/godot-plugin/buildSrc/src/main/kotlin/KanamaAndroidRemap.kt"
TREES = (ROOT / "src/jvmMain/kotlin", ROOT / "src/commonMain/kotlin")
EXPECT_SUFFIX = ".expect.kt"
TAG = "[android-remap-sources]"

# Hot call sites that desktop calls with `invokeExact`, which ART rejects (task 131 item 16): the
# remap must rewrite every one to `invokeWithArguments`. Each file listed here must contain at least
# one such site the remap rewrote, so a site moved out of the gate's sight fails loudly instead of
# passing by having nothing to check (task 134 B: the builtin-call frame's one downcall).
REQUIRED_EXACT_SITES = (
    "src/jvmMain/kotlin/binding/runtime/BuiltinFrame.kt",
    "src/jvmMain/kotlin/binding/runtime/GodotStrings.kt",
)


def parse_remap(text: str) -> tuple[list[tuple[str, str]], list[str]]:
    rules_block = text[text.index("val rules = listOf(") : text.index("LEADING_MODIFIERS")]
    rules = re.findall(r'needle\s*=\s*"([^"]*)",\s*replacement\s*=\s*"([^"]*)"', rules_block)
    frag_start = text.index("val forbiddenSourceFragments = listOf(")
    frag_block = text[frag_start : text.index(")\n", frag_start)]
    fragments = re.findall(r'"([^"]*)"', frag_block)
    if not rules or not fragments:
        raise SystemExit(f"{TAG} FAIL: could not parse rules/fragments from {REMAP}")
    return rules, fragments


def strip_comments(lines: list[str]) -> list[str]:
    """Mirror of KanamaAndroidRemap.strippedSourceLines (line and block comments)."""
    out_lines: list[str] = []
    in_block = False
    for line in lines:
        out = []
        i = 0
        while i < len(line):
            if in_block:
                if line.startswith("*/", i):
                    in_block = False
                    i += 2
                else:
                    i += 1
                continue
            if line.startswith("/*", i):
                in_block = True
                i += 2
                continue
            if line.startswith("//", i):
                break
            out.append(line[i])
            i += 1
        out_lines.append("".join(out))
    return out_lines


def main() -> int:
    rules, fragments = parse_remap(REMAP.read_text(encoding="utf-8"))
    failures: list[str] = []
    files = 0
    exact_sites: dict[str, int] = {}
    for tree in TREES:
        for path in sorted(tree.rglob("*.kt")):
            if path.name.endswith(EXPECT_SUFFIX):
                continue
            files += 1
            remapped = []
            for line in path.read_text(encoding="utf-8").splitlines():
                for needle, replacement in rules:
                    line = line.replace(needle, replacement)
                remapped.append(line)
            original = strip_comments(path.read_text(encoding="utf-8").splitlines())
            for number, (before, after) in enumerate(zip(original, strip_comments(remapped)), start=1):
                if ".invokeExact(" in before:
                    rel = str(path.relative_to(ROOT))
                    if ".invokeWithArguments(" in after and ".invokeExact(" not in after:
                        exact_sites[rel] = exact_sites.get(rel, 0) + 1
            for number, line in enumerate(strip_comments(remapped), start=1):
                for fragment in fragments:
                    if fragment in line:
                        failures.append(
                            f"{path.relative_to(ROOT)}:{number}: forbidden after the Android remap: "
                            f"'{fragment}' (call a function value as f(args) / f?.let {{ it(args) }}, "
                            "never .invoke()"
                        )
    for required in REQUIRED_EXACT_SITES:
        if not exact_sites.get(required):
            failures.append(
                f"{required}: no `.invokeExact(` call site rewritten to `.invokeWithArguments(` -- the "
                "hot downcall moved out of the remap's sight (or lost its rule)"
            )
    if failures:
        for failure in failures:
            print(f"{TAG} FAIL {failure}", file=sys.stderr)
        return 1
    print(
        f"{TAG} PASS {files} file(s) remapped with {len(rules)} rule(s); "
        f"no forbidden fragment ({len(fragments)} checked); {sum(exact_sites.values())} invokeExact site(s) "
        f"rewritten ({', '.join(f'{Path(k).name}: {v}' for k, v in sorted(exact_sites.items()))})"
    )
    return 0


if __name__ == "__main__":
    sys.exit(main())
