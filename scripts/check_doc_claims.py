#!/usr/bin/env python3
"""Verify LIVE documentation claims against their source of truth (task 85 slice 2).

Docs state facts that live somewhere else in the tree, and nothing has ever checked
that the two still agree. Measured 2026-08-14: six places in `docs/` named a Web
protocol version, and the two that described the *current* state said 16 and 18 while
the emitter said 19. `DEFERRED.md` meanwhile said "no web support at all … do not
re-ask" a month after the Web backend shipped. A release publishes claims; stale ones
are how a reader ends up believing something the code stopped doing.

WHY MARKERS, and not a grep for every number:

Most protocol mentions in these docs are *history* and are correct exactly as written
— "Before protocol 18 this ran through …", "at protocol 17 (the version current when
task 80 landed)". Blindly bumping every number to the current one would falsify
accurate history, which is worse than the drift. So a claim is checked only when it
opts in with a marker on the same line:

    ... the JavaScript bridge (protocol 19). <!-- kanama-claim: protocol -->

Unmarked mentions are historical by definition. That mirrors the stale-blocker marker
convention from slice 1 (see scripts/audit_stale_blockers.py): machine-checkable
assertions, explicitly placed rather than inferred from prose.

(Written without the literal marker token on purpose: slice 1's auditor flags any
occurrence followed by "(" or ":" as a candidate, and it caught this very file's
docstring on first CI contact -- the claim auditor auditing the claim auditor's
documentation, and behaving exactly as designed.)

Adding a claim kind: give it an extractor here and mark the doc line. The check fails
if a marked line's stated value disagrees with the source, and it fails just as loudly
if a marker exists whose kind it does not know — an unrecognised marker must never be
silently skipped, since that would be a claim believed to be checked and not checked.

Usage:
    python3 scripts/check_doc_claims.py            # from the repo root
    python3 scripts/check_doc_claims.py --root .
"""

from __future__ import annotations

import argparse
import re
import sys
from pathlib import Path

MARKER = re.compile(r"<!--\s*kanama-claim:\s*(?P<kind>[a-z0-9-]+)\s*-->")
EMITTER = Path("processor/src/main/kotlin/net/multigesture/kanama/processor/WebScriptCodeEmitter.kt")


def _current_protocol(root: Path) -> int:
    source = (root / EMITTER).read_text()
    match = re.search(r"const val PROTOCOL_VERSION = (\d+)", source)
    if not match:
        raise SystemExit(
            f"check_doc_claims: FAIL — could not read PROTOCOL_VERSION from {EMITTER}; "
            "the source of truth moved and this check would silently pass"
        )
    return int(match.group(1))


def _stated_protocol(line: str) -> int | None:
    match = re.search(r"protocol (\d+)", line)
    return int(match.group(1)) if match else None


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--root", type=Path, default=Path("."))
    args = parser.parse_args()
    root = args.root

    docs = sorted((root / "docs").rglob("*.md"))
    # Assert a non-empty scan before believing a clean result: pointed at the wrong
    # root this would otherwise report "all claims agree" having read nothing.
    if not docs:
        print(f"check_doc_claims: FAIL — no docs found under {root / 'docs'}", file=sys.stderr)
        return 2

    protocol = _current_protocol(root)
    checked = 0
    failures: list[str] = []

    for path in docs:
        for number, line in enumerate(path.read_text().splitlines(), start=1):
            marker = MARKER.search(line)
            if not marker:
                continue
            kind = marker.group("kind")
            if kind == "protocol":
                stated = _stated_protocol(line)
                checked += 1
                if stated is None:
                    failures.append(
                        f"{path}:{number}: marked as a protocol claim but states no "
                        f"'protocol <N>'"
                    )
                elif stated != protocol:
                    failures.append(
                        f"{path}:{number}: claims protocol {stated}, but "
                        f"WebScriptCodeEmitter declares {protocol}"
                    )
            else:
                # An unknown kind is a failure, never a skip: a marker nobody checks is
                # a claim everybody believes is checked.
                failures.append(f"{path}:{number}: unknown claim kind '{kind}'")

    if not checked and not failures:
        print(
            "check_doc_claims: FAIL — scanned "
            f"{len(docs)} docs and found no kanama-claim markers at all; the convention "
            "has been dropped or the scan is looking in the wrong place",
            file=sys.stderr,
        )
        return 2

    if failures:
        print(f"check_doc_claims: FAIL — {len(failures)} stale or malformed claim(s):", file=sys.stderr)
        for failure in failures:
            print(f"  {failure}", file=sys.stderr)
        return 1

    print(
        f"check_doc_claims: OK — {checked} marked claim(s) across {len(docs)} docs agree "
        f"with their sources (protocol {protocol})"
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
