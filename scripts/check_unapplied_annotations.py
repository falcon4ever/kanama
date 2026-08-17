#!/usr/bin/env python3
"""Fail when a Kanama lifecycle annotation is imported but never applied.

A dead lifecycle hook is invisible. `FullScreenHandler` imported `OnReady`, never
applied it, and so `ready()` never dispatched -- upstream sets PROCESS_MODE_ALWAYS
there precisely so the node survives a paused tree, and on desktop the fullscreen
toggle had been dead whenever the game was paused. Nothing failed.

Why this check and not something stronger:

  * The Kotlin compiler does NOT warn here (verified on a full --rerun-tasks build):
    there was no discarded signal to escalate.
  * The processor DOES print the fact -- `virtuals=1` without the annotation,
    `virtuals=2` with it -- but nothing compares that count to an expectation.
  * The exercised-member census (task 81) asserts DISPATCH rather than spelling and
    would be the better instrument, except it runs on Web only. This file's web
    override legitimately has no `ready()` at all, so the Web census structurally
    cannot see a desktop-only lifecycle bug. Until a desktop census exists (task 81
    fix #3), a source check is the only net there is.

So this is deliberately a TRIPWIRE FOR ONE SHAPE, not a general correctness gate: an
import with no matching use. It cannot see a hook-shaped function in a file that never
imports the annotation -- the same bug with no tell. Do not read a pass here as "the
lifecycle wiring is right".
"""

from __future__ import annotations

import argparse
import pathlib
import re
import sys

# Annotations whose absence silently disables behaviour. @ScriptClass/@ScriptProperty
# are excluded on purpose: omitting those fails the build or drops a property the
# generators already report, so they are not silent.
LIFECYCLE_ANNOTATIONS = (
    "OnReady",
    "OnProcess",
    "OnPhysicsProcess",
    "OnInput",
    "OnUnhandledInput",
    "OnEnterTree",
    "OnExitTree",
    "OnDraw",
    "RegisterFunction",
    "Signal",
)

IMPORT_RE = re.compile(
    r"^\s*import\s+net\.multigesture\.kanama\.annotations\.(\w+)\s*$", re.M
)
STAR_IMPORT_RE = re.compile(r"^\s*import\s+net\.multigesture\.kanama\.annotations\.\*", re.M)


def findings_for(path: pathlib.Path) -> list[str]:
    text = path.read_text(encoding="utf-8")
    if STAR_IMPORT_RE.search(text):
        # A star import makes "imported but unused" unanswerable by inspection; say so
        # rather than pass silently.
        return [f"{path}: star-imports the annotations package -- this check cannot verify it"]
    out = []
    for name in IMPORT_RE.findall(text):
        if name not in LIFECYCLE_ANNOTATIONS:
            continue
        if not re.search(rf"@{name}\b", text):
            out.append(
                f"{path}: imports @{name} but never applies it -- "
                f"the annotated behaviour is silently disabled"
            )
    return out


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument(
        "roots",
        nargs="*",
        default=["."],
        help="directories to scan for .kt sources (default: cwd)",
    )
    args = parser.parse_args()

    files: list[pathlib.Path] = []
    for root in args.roots:
        base = pathlib.Path(root)
        if not base.exists():
            print(f"[unapplied_annotations] SKIP missing root: {base}")
            continue
        files.extend(
            p
            for p in base.rglob("*.kt")
            if "/build/" not in p.as_posix() and "/.git/" not in p.as_posix()
        )

    # An empty scan is a broken invocation, not a pass -- the vacuous-gate lesson.
    if not files:
        print(
            "[unapplied_annotations] FAIL no .kt files found under "
            f"{args.roots} -- refusing to report a vacuous pass"
        )
        return 2

    findings = [f for path in sorted(files) for f in findings_for(path)]
    for finding in findings:
        print(f"[unapplied_annotations] {finding}")
    if findings:
        print(f"[unapplied_annotations] FAIL {len(findings)} finding(s) across {len(files)} files")
        return 1
    print(f"[unapplied_annotations] PASS {len(files)} files scanned, 0 findings")
    return 0


if __name__ == "__main__":
    sys.exit(main())
