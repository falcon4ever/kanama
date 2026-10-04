#!/usr/bin/env python3
"""Fail if a built Web export embeds workstation-absolute paths.

`exportWeb` already asserts that the exported `index.html` leaks neither the
staging directory nor `$HOME`. That covers the shell Godot writes, but a served
export is more than its HTML: the Godot engine JS, the pck, the Kotlin/Wasm
payload and the export report are all shipped, and any of them embedding a build
machine's paths would make the artifact non-portable evidence (task 60g, W3).

This scans every served byte for the forbidden prefixes the caller names — the
build workspace, the build user's home — and reports each hit with its file and
a short context window. Binary files are scanned too: the point is exactly to
catch a path baked into a compiled payload.

Usage:
    python3 check_no_local_paths.py <export-dir> --forbid <absolute-path>...
                                    [--allow-file <relative-path>]...
    python3 check_no_local_paths.py --self-test

`--self-test` is this gate's red run (task 118): it builds an export-shaped tree in a scratch directory,
requires a clean tree to pass, then plants a forbidden path in a text file and in a binary file and
requires the scan to find both, and requires `--allow-file` to silence exactly the allowed one.
"""

from __future__ import annotations

import os
import sys

_CONTEXT = 60


def _hits(data: bytes, needle: bytes) -> list[int]:
    found: list[int] = []
    start = 0
    while True:
        index = data.find(needle, start)
        if index < 0:
            return found
        found.append(index)
        start = index + 1
        if len(found) >= 5:
            return found


def scan(export_dir: str, forbidden: list[str], allowed: set[str]) -> list[str]:
    problems: list[str] = []
    needles = [(text, text.encode("utf-8")) for text in forbidden if text]
    for dirpath, dirnames, filenames in os.walk(export_dir):
        dirnames.sort()
        for name in sorted(filenames):
            path = os.path.join(dirpath, name)
            rel = os.path.relpath(path, export_dir)
            if rel in allowed:
                continue
            with open(path, "rb") as handle:
                data = handle.read()
            for text, needle in needles:
                for offset in _hits(data, needle):
                    window = data[max(0, offset - 10) : offset + _CONTEXT]
                    context = window.decode("utf-8", errors="replace").replace("\n", " ")
                    problems.append(f"{rel}: embeds {text!r} at byte {offset} ({context!r})")
    return problems


def self_test() -> int:
    import tempfile

    forbidden = ["/Users/redrun/build-workspace"]
    with tempfile.TemporaryDirectory() as scratch:
        with open(os.path.join(scratch, "clean.js"), "w", encoding="utf-8") as handle:
            handle.write("console.log('no paths here');\n")
        if scan(scratch, forbidden, set()):
            print("check_no_local_paths --self-test FAIL: a clean tree was reported dirty", file=sys.stderr)
            return 1
        with open(os.path.join(scratch, "leak.js"), "w", encoding="utf-8") as handle:
            handle.write("const root = '/Users/redrun/build-workspace/out';\n")
        with open(os.path.join(scratch, "payload.wasm"), "wb") as handle:
            handle.write(b"\x00asm\x01\x00\x00\x00/Users/redrun/build-workspace/classes\x00")
        problems = scan(scratch, forbidden, set())
        if len(problems) != 2 or not any("leak.js" in p for p in problems) or not any("payload.wasm" in p for p in problems):
            print(f"check_no_local_paths --self-test FAIL: expected the text and the binary leak, got {problems}", file=sys.stderr)
            return 1
        remaining = scan(scratch, forbidden, {"leak.js"})
        if len(remaining) != 1 or "payload.wasm" not in remaining[0]:
            print(f"check_no_local_paths --self-test FAIL: --allow-file must silence only leak.js, got {remaining}", file=sys.stderr)
            return 1
    print("check_no_local_paths --self-test: PASS (clean tree green; text and binary leaks both found; allow-file exact)")
    return 0


def main(argv: list[str]) -> int:
    if argv == ["--self-test"]:
        return self_test()
    if not argv:
        print(
            "usage: check_no_local_paths.py <export-dir> --forbid <path>... "
            "[--allow-file <relative-path>]...",
            file=sys.stderr,
        )
        return 2
    export_dir = argv[0]
    forbidden: list[str] = []
    allowed: set[str] = set()
    rest = argv[1:]
    while rest:
        flag = rest[0]
        if flag in ("--forbid", "--allow-file") and len(rest) >= 2:
            if flag == "--forbid":
                forbidden.append(os.path.normpath(rest[1]))
            else:
                allowed.add(rest[1])
            rest = rest[2:]
            continue
        print(f"unexpected argument: {flag}", file=sys.stderr)
        return 2

    if not os.path.isdir(export_dir):
        print(f"not a directory: {export_dir}", file=sys.stderr)
        return 2
    if not forbidden:
        print("at least one --forbid path is required", file=sys.stderr)
        return 2

    problems = scan(export_dir, forbidden, allowed)
    if problems:
        print(f"check_no_local_paths: {len(problems)} leak(s) in {export_dir}", file=sys.stderr)
        for problem in problems:
            print(f"  {problem}", file=sys.stderr)
        return 1
    print(f"check_no_local_paths: OK ({export_dir}: no local paths in any served file)")
    return 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv[1:]))
