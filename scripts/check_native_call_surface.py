#!/usr/bin/env python3
"""Gate: every native downcall adapter shape is prewarmed before the first upcall.

`java.lang.foreign.Linker` generates a native adapter once per `FunctionDescriptor`
(`AbstractLinker` keys its downcall cache on the descriptor plus the linker options; the
symbol-bound overload is `downcallHandle(descriptor).bindTo(symbol)`). The unit of native
code generation is therefore the *shape*, not the call site.

Kanama prewarms every shape during JNI bootstrap, before any Godot->JVM lifecycle upcall
exists, so an upcall only ever executes adapters that already exist (task 83). This script
keeps that true as code changes: it extracts every downcall shape reachable from the
desktop/Android backend sources and asserts each one is declared in
`src/main/kotlin/ffi/NativeCallSurface.kt`.

It also enforces the funnel that makes the shapes findable in the first place: outside
`GodotFFI.kt`, nothing may call `linker.downcallHandle` / `linker.upcallStub` directly.

Usage:
    python3 scripts/check_native_call_surface.py            # gate
    python3 scripts/check_native_call_surface.py --list     # print the inventory
"""

from __future__ import annotations

import argparse
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent

# Backend sources that can link a native adapter. `processor/` emits Kotlin as string
# literals, so it is scanned through the string-literal reconstruction below.
SCAN_ROOTS = (
    Path("src/main/kotlin"),
    Path("processor/src/main/kotlin"),
)

REGISTRY_FILE = Path("src/main/kotlin/ffi/NativeCallSurface.kt")
FUNNEL_FILE = Path("src/main/kotlin/ffi/GodotFFI.kt")

# Call sites that link a downcall adapter, and which positional argument carries the
# descriptor.
DOWNCALL_SITES = {
    "GodotFFI.lookup": 1,
    "GodotFFI.downcallHandle": 1,
    "GodotFFI.prelinkDowncall": 0,
    "linker.downcallHandle": 1,
}

# Reported, not gated -- see NativeCallSurface.kt for why upcall stubs are a different
# problem (each stub is a distinct native blob, and script classes are only known inside
# the initialize upcall).
UPCALL_SITES = {
    "GodotFFI.upcallStub": 1,
    "Upcalls.stub": 3,
    "linker.upcallStub": 1,
}

RAW_LINKER_CALL = re.compile(r"\blinker\s*\.\s*(downcallHandle|upcallStub)\s*\(")

STRING_LITERAL = re.compile(r'"((?:[^"\\]|\\.)*)"')


def source_text(path: Path) -> str:
    """Kotlin to scan for a file.

    For the KSP processor the interesting Kotlin is inside string literals it appends to a
    StringBuilder, so reconstruct the emitted text by concatenating the literals.
    """
    text = path.read_text(encoding="utf-8")
    if "processor/src/main/kotlin" in path.as_posix():
        parts = [
            m.group(1).replace('\\"', '"').replace("\\$", "$").replace("\\n", "\n")
            for m in STRING_LITERAL.finditer(text)
        ]
        return "\n".join(parts)
    return text


def balanced(text: str, open_paren_index: int) -> tuple[str, int]:
    """Return the text between the parens starting at `open_paren_index`, and the index after."""
    depth = 0
    i = open_paren_index
    while i < len(text):
        c = text[i]
        if c == "(":
            depth += 1
        elif c == ")":
            depth -= 1
            if depth == 0:
                return text[open_paren_index + 1 : i], i + 1
        i += 1
    raise ValueError(f"unbalanced parentheses at offset {open_paren_index}")


def split_top_level(args: str) -> list[str]:
    parts: list[str] = []
    depth = 0
    current = ""
    for c in args:
        if c in "(<[":
            depth += 1
        elif c in ")>]":
            depth -= 1
        if c == "," and depth == 0:
            parts.append(current)
            current = ""
        else:
            current += c
    if current.strip():
        parts.append(current)
    return [p.strip() for p in parts]


def canonical_shape(expr: str) -> str | None:
    """Normalize a `FunctionDescriptor.of[Void](...)` expression to a comparable string."""
    expr = expr.strip()
    match = re.match(r"^FunctionDescriptor\s*\.\s*(of|ofVoid)\s*\(", expr)
    if not match:
        return None
    args, _ = balanced(expr, expr.index("(", match.start(1)))
    layouts = [a for a in split_top_level(args) if a]
    layouts = [re.sub(r"^(java\.lang\.foreign\.)?ValueLayout\.", "", a) for a in layouts]
    return f"{match.group(1)}({','.join(layouts)})"


def descriptor_aliases(text: str) -> dict[str, str]:
    """Map local `val someDesc = FunctionDescriptor...` names to canonical shapes."""
    aliases: dict[str, str] = {}
    for match in re.finditer(
        r"\bval\s+(\w+)\s*(?::\s*FunctionDescriptor\s*)?=\s*(FunctionDescriptor\s*\.)", text
    ):
        shape = canonical_shape(text[match.start(2) :])
        if shape:
            aliases[match.group(1)] = shape
    return aliases


def resolve(arg: str, aliases: dict[str, str]) -> str | None:
    shape = canonical_shape(arg)
    if shape:
        return shape
    return aliases.get(arg.strip())


def scan_sites(text: str, sites: dict[str, int]) -> list[tuple[str | None, str, int]]:
    """Return (shape, callee, line) per matching call site; shape is None when unresolvable."""
    aliases = descriptor_aliases(text)
    found: list[tuple[str | None, str, int]] = []
    for callee, position in sites.items():
        pattern = re.compile(re.escape(callee) + r"\s*\(")
        for match in pattern.finditer(text):
            try:
                args, _ = balanced(text, text.index("(", match.end() - 1))
            except ValueError:
                continue
            parts = split_top_level(args)
            shape = resolve(parts[position], aliases) if position < len(parts) else None
            found.append((shape, callee, text.count("\n", 0, match.start()) + 1))
    return found


def kotlin_files() -> list[Path]:
    files: list[Path] = []
    for root in SCAN_ROOTS:
        files.extend(sorted((ROOT / root).rglob("*.kt")))
    return files


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--list", action="store_true", help="print the inventory and exit 0")
    args = parser.parse_args()

    registry_text = (ROOT / REGISTRY_FILE).read_text(encoding="utf-8")
    registry: set[str] = set()
    for match in re.finditer(r"FunctionDescriptor\s*\.\s*of", registry_text):
        shape = canonical_shape(registry_text[match.start() :])
        if shape:
            registry.add(shape)
    if not registry:
        print(f"[native-call-surface] no shapes declared in {REGISTRY_FILE}", file=sys.stderr)
        return 1

    downcalls: dict[str, list[str]] = {}
    upcalls: dict[str, list[str]] = {}
    raw_linker: list[str] = []
    unresolved: list[str] = []

    # GodotFFI.kt is the funnel: its own calls take the descriptor as a parameter, so they
    # have no shape of their own. NativeCallSurface.kt is the registry being checked against.
    skip_sites = {FUNNEL_FILE.as_posix(), REGISTRY_FILE.as_posix()}

    for path in kotlin_files():
        rel = path.relative_to(ROOT).as_posix()
        text = source_text(path)
        if rel != FUNNEL_FILE.as_posix():
            for match in RAW_LINKER_CALL.finditer(text):
                line = text.count("\n", 0, match.start()) + 1
                raw_linker.append(f"{rel}:{line}: linker.{match.group(1)}")
        if rel in skip_sites:
            continue
        for shape, callee, line in scan_sites(text, DOWNCALL_SITES):
            if shape is None:
                unresolved.append(f"{rel}:{line} ({callee})")
            else:
                downcalls.setdefault(shape, []).append(f"{rel}:{line} ({callee})")
        for shape, callee, line in scan_sites(text, UPCALL_SITES):
            # Upcalls are reported, not gated; sites that pass the descriptor through a
            # local helper (ScriptBridge's info3 table) resolve to None and are skipped.
            if shape is not None:
                upcalls.setdefault(shape, []).append(f"{rel}:{line} ({callee})")

    if args.list:
        print(f"downcall shapes reached from the backend ({len(downcalls)}):")
        for shape in sorted(downcalls):
            mark = "  " if shape in registry else "!!"
            print(f"{mark}{shape}")
            for site in downcalls[shape]:
                print(f"      {site}")
        if unresolved:
            print()
            print("downcall sites whose descriptor could not be resolved statically:")
            for site in unresolved:
                print(f"  {site}")
        print()
        print(f"upcall shapes, reported only ({len(upcalls)}):")
        for shape in sorted(upcalls):
            print(f"  {shape}  ({len(upcalls[shape])} site(s))")
        unused = registry - set(downcalls)
        if unused:
            print()
            print("declared but not reached from any scanned site:")
            for shape in sorted(unused):
                print(f"  {shape}")
        return 0

    failures: list[str] = []
    for shape in sorted(downcalls):
        if shape not in registry:
            failures.append(
                f"downcall shape {shape} is linked but not prewarmed by {REGISTRY_FILE}:\n    "
                + "\n    ".join(downcalls[shape])
            )
    for site in unresolved:
        failures.append(
            f"{site} links a downcall adapter with a descriptor this gate cannot resolve; "
            "pass a FunctionDescriptor literal or a local val holding one"
        )
    for entry in raw_linker:
        failures.append(
            f"{entry} bypasses the GodotFFI funnel; use GodotFFI.downcallHandle / "
            "GodotFFI.upcallStub so the adapter is traceable and prewarmable"
        )

    if failures:
        print("[native-call-surface] FAIL", file=sys.stderr)
        for failure in failures:
            print(f"  - {failure}", file=sys.stderr)
        print(
            "\nAdd the shape to NativeCallSurface.prewarm() (and say which call family "
            "needs it), or route the call through an existing shape.",
            file=sys.stderr,
        )
        return 1

    print(
        f"[native-call-surface] OK: {len(downcalls)} downcall shape(s) reached, "
        f"all prewarmed before the first lifecycle upcall"
    )
    return 0


if __name__ == "__main__":
    sys.exit(main())
