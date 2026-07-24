#!/usr/bin/env python3
"""Fail loud if any backend binds a deprecated GDExtension interface function, or if the
desktop/Android (JVM) and iOS backends bind *different* variants of the same function family.

Background: the JVM backend (`src/main`) and the iOS Kotlin/Native shim
(`ios/bootstrap/kanama_ios_shim.c`) each choose which GDExtension entry points to bind. They
silently diverged once — desktop on the 4.7-deprecated `classdb_construct_object2`, iOS on
`classdb_construct_object3` — which caused issue #91 (task 61/62). This gate makes that class of
drift, and any newly-deprecated binding after a Godot bump, a hard failure instead of a surprise.

Run standalone or via local_ci.sh / the upgrade gates.
"""
from __future__ import annotations

import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
HEADER = ROOT / "gdextension" / "gdextension_interface.h"

# Each backend and the source it binds GDExtension functions from (as quoted lookup strings).
BACKENDS: dict[str, list[Path]] = {
    "desktop/Android (JVM)": [ROOT / "src" / "main"],
    "iOS (Kotlin/Native)": [ROOT / "ios" / "bootstrap" / "kanama_ios_shim.c"],
}


def parse_header() -> tuple[set[str], set[str]]:
    """Return (all interface function @names, the subset marked @deprecated)."""
    lines = HEADER.read_text(encoding="utf-8").splitlines()
    all_fns: set[str] = set()
    deprecated: set[str] = set()
    for i, line in enumerate(lines):
        m = re.search(r"@name\s+(\w+)", line)
        if not m:
            continue
        name = m.group(1)
        all_fns.add(name)
        if any("@deprecated" in l for l in lines[i : i + 6]):
            deprecated.add(name)
    return all_fns, deprecated


def bound_functions(paths: list[Path], all_fns: set[str]) -> dict[str, str]:
    """Quoted strings in the backend source that name a real GDExtension function -> a sample file."""
    found: dict[str, str] = {}
    files: list[Path] = []
    for p in paths:
        if p.is_file():
            files.append(p)
        else:
            files += [f for f in (*p.rglob("*.kt"), *p.rglob("*.c")) if "/build/" not in str(f)]
    for f in files:
        for s in re.findall(r'"([a-z_][a-z0-9_]*)"', f.read_text(encoding="utf-8")):
            if s in all_fns and s not in found:
                found[s] = str(f.relative_to(ROOT))
    return found


def family(fn: str) -> str:
    """Strip a trailing version digit so e.g. classdb_construct_object3 -> classdb_construct_object."""
    return re.sub(r"\d+$", "", fn)


def main() -> int:
    if not HEADER.exists():
        print(f"[gdext_modernization] header not found: {HEADER}", file=sys.stderr)
        return 2
    all_fns, deprecated = parse_header()
    failures: list[str] = []
    per_backend: dict[str, dict[str, str]] = {}

    for backend, paths in BACKENDS.items():
        bound = bound_functions(paths, all_fns)
        per_backend[backend] = bound
        for fn, sample in sorted(bound.items()):
            if fn in deprecated:
                failures.append(f"[deprecated] {backend} binds deprecated '{fn}' ({sample}) — migrate to the newest variant")

    # Convergence: a function family bound by more than one backend must use the same variant.
    fams: dict[str, dict[str, set[str]]] = {}
    for backend, bound in per_backend.items():
        for fn in bound:
            fams.setdefault(family(fn), {}).setdefault(backend, set()).add(fn)
    for fam, by_backend in sorted(fams.items()):
        if len(by_backend) < 2:
            continue
        variants = set().union(*by_backend.values())
        if len(variants) > 1:
            detail = "; ".join(f"{b}={sorted(v)}" for b, v in by_backend.items())
            failures.append(f"[divergence] family '{fam}': backends bind different variants — {detail}")

    if failures:
        print("[gdext_modernization] FAIL", file=sys.stderr)
        for f in failures:
            print("  " + f, file=sys.stderr)
        return 1

    counts = ", ".join(f"{b.split(' ')[0]}={len(v)}" for b, v in per_backend.items())
    print(
        f"[gdext_modernization] PASS — no deprecated GDExtension bindings; backends converged "
        f"({len(deprecated)} deprecated fns checked; bound: {counts})"
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
