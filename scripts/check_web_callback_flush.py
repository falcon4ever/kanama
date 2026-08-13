#!/usr/bin/env python3
"""Every Web callback boundary that runs user Kotlin must flush the command buffer.

Task 88. The three `kanamaWebDispatchSignal*` entry points ran a user lambda and
returned WITHOUT `commands.flush()`, while every sibling boundary flushed. Because the
next per-frame entry point opens with `commands.clear()` -- a pure discard, not a flush
-- every QUEUED_MUTATION issued from a Kotlin lambda signal callback was silently
thrown away. `kanamaWebCallInt` had the same gap.

Nothing could see it. The exercised-member census records the callback as *dispatched*,
so the gate went green while the whole effect was discarded; it only ever surfaced as
unexplained symptoms in manual play-testing ("charactercontroller: the eyes never
blink" -- `Timer.start` is a QUEUED_MUTATION and was the sole statement in that lambda).

The rule this enforces: a boundary that dispatches into user script code must end its
`webCallbackBoundary` block with `commands.flush()`. A callback that happens to end on
an IMMEDIATE_RESULT call is rescued by accident (immediate arms flush first) -- that
accident is exactly why this survived so long, so it does not count as compliance.

Usage:
    python3 scripts/check_web_callback_flush.py [--main <path to Main.kt>]
"""

from __future__ import annotations

import argparse
import re
import sys
from pathlib import Path

DEFAULT_MAIN = Path("web-runtime/src/wasmJsMain/kotlin/net/multigesture/kanama/web/Main.kt")

# A boundary "runs user Kotlin" if its body invokes one of these dispatch entry points.
# Property accessors are deliberately NOT in this set -- see EXEMPT_PREFIXES below.
USER_DISPATCH_CALLS = (
    "KanamaWebProjectRegistry.call",
    "KanamaWebProjectRegistry.ready",
    "KanamaWebProjectRegistry.enterTree",
    "KanamaWebProjectRegistry.exitTree",
    "KanamaWebProjectRegistry.process",
    "KanamaWebProjectRegistry.physicsProcess",
    "KanamaWebProjectRegistry.input",
    "KanamaWebProjectRegistry.unhandledInput",
    "KanamaWebProjectRegistry.draw",
    "WebSignalCallbackRegistry.dispatch",
)

# Boundaries that dispatch into user code but legitimately do not flush `commands`.
# Each entry must carry a reason; an unexplained exemption is the thing this gate exists
# to prevent.
EXEMPTIONS = {
    # _draw owns a SEPARATE buffer (drawCommands) with its own clear/flush pair, and its
    # return value IS that buffer's applied count. Flushing `commands` here would be
    # unrelated work on the draw path.
    "kanamaWebDraw": "uses the dedicated drawCommands buffer (own clear/flush)",
}

# Property get/set boundaries assign fields rather than run user method bodies, and the
# create/ready sequence that pushes them flushes afterwards. They are out of scope for
# this gate BY NAME so that a future dispatch-style boundary cannot hide behind the
# same exemption. Revisit if @ScriptProperty ever accepts custom setter bodies.
EXEMPT_PREFIXES = ("kanamaWebGet", "kanamaWebSet")

FLUSH = "commands.flush()"


def boundaries(source: str) -> list[tuple[str, int, str]]:
    """Return (name, 1-based line, body) for every top-level kanamaWeb* function."""
    lines = source.split("\n")
    found: list[tuple[str, int, str]] = []
    for index, line in enumerate(lines):
        match = re.match(r"fun (kanamaWeb\w+)\(", line)
        if not match:
            continue
        cursor = index + 1
        body: list[str] = []
        while cursor < len(lines) and not re.match(r"(@JsExport|fun kanamaWeb)", lines[cursor]):
            body.append(lines[cursor])
            cursor += 1
        found.append((match.group(1), index + 1, "\n".join(body)))
    return found


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--main", type=Path, default=DEFAULT_MAIN)
    args = parser.parse_args()

    path = args.main
    if not path.is_file():
        print(f"check_web_callback_flush: FAIL — Main.kt not found at {path}", file=sys.stderr)
        return 1

    source = path.read_text()
    found = boundaries(source)

    # Assert a non-empty extraction before believing any result: a parser that silently
    # matches nothing would report a clean bill of health for a broken file.
    if not found:
        print(
            f"check_web_callback_flush: FAIL — parsed 0 kanamaWeb* boundaries from {path}; "
            "the extraction pattern no longer matches the source",
            file=sys.stderr,
        )
        return 1

    checked = 0
    violations: list[str] = []
    for name, line, body in found:
        if "webCallbackBoundary" not in body:
            continue
        if not any(call in body for call in USER_DISPATCH_CALLS):
            continue
        if name.startswith(EXEMPT_PREFIXES):
            continue
        if name in EXEMPTIONS:
            continue
        checked += 1
        if FLUSH not in body:
            violations.append(
                f"  {path}:{line}  {name} dispatches into user Kotlin but never calls "
                f"{FLUSH}; every queued mutation it issues is discarded by the next "
                f"commands.clear()"
            )

    if not checked:
        print(
            "check_web_callback_flush: FAIL — matched 0 user-dispatch boundaries; the "
            "USER_DISPATCH_CALLS table has drifted from the source",
            file=sys.stderr,
        )
        return 1

    if violations:
        print(
            f"check_web_callback_flush: FAIL — {len(violations)} boundary/boundaries run "
            f"user Kotlin without flushing the command buffer:",
            file=sys.stderr,
        )
        for violation in violations:
            print(violation, file=sys.stderr)
        print(
            "\nAdd `commands.flush()` as the last statement of the webCallbackBoundary "
            "block. Do NOT rely on the callback ending with an immediate call — that "
            "rescue is accidental and shape-dependent (task 88).",
            file=sys.stderr,
        )
        return 1

    print(
        f"check_web_callback_flush: OK — {checked} user-dispatch boundaries all flush "
        f"({len(EXEMPTIONS)} documented exemption(s), parsed {len(found)} kanamaWeb* "
        f"functions from {path})"
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
