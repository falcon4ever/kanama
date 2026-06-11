#!/usr/bin/env python3
"""Generate the iOS hand-written / stub registry from in-code KANAMA-IOS-* markers.

Scans the iOS backend for the three standardized markers and writes a grouped table to
docs/internals/ios-backend-handwritten.md so every hand-written/stub/sugar site is
discoverable (and so a reviewer can see what still needs a real implementation).

Markers (one per line, in a comment above the code):
- KANAMA-IOS-STUB: a silent stub that SHOULD call real Godot but doesn't yet.
- KANAMA-IOS-HANDWRITTEN: intentionally bespoke (not generatable), correct as-is.
- KANAMA-IOS-SUGAR: hand-added inside a generated wrapper file; regeneration clobbers it.

Run: python3 scripts/ios_handwritten_report.py
"""

from __future__ import annotations

import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
SCAN_DIRS = [ROOT / "ios", ROOT / "ios-runtime/src/iosMain"]
SCAN_SUFFIXES = {".kt", ".c", ".h"}
OUTPUT = ROOT / "docs/internals/ios-backend-handwritten.md"
MARKER_RE = re.compile(r"KANAMA-IOS-(STUB|HANDWRITTEN|SUGAR)\s*:?\s*(.*)")
KINDS = ("STUB", "HANDWRITTEN", "SUGAR")
KIND_BLURB = {
    "STUB": "Silent stubs — should call real Godot but don't yet (follow up to avoid silent-no-op bugs).",
    "HANDWRITTEN": "Intentionally bespoke — not generatable from extension_api.json; correct as-is.",
    "SUGAR": "Hand-added inside a GENERATED wrapper file — regeneration overwrites it; re-add after.",
}


def collect() -> dict[str, list[tuple[str, int, str]]]:
    found: dict[str, list[tuple[str, int, str]]] = {kind: [] for kind in KINDS}
    for scan_dir in SCAN_DIRS:
        for path in sorted(scan_dir.rglob("*")):
            if path.suffix not in SCAN_SUFFIXES or not path.is_file():
                continue
            for lineno, line in enumerate(path.read_text(encoding="utf-8").splitlines(), 1):
                match = MARKER_RE.search(line)
                if match:
                    rel = path.relative_to(ROOT).as_posix()
                    found[match.group(1)].append((rel, lineno, match.group(2).strip()))
    return found


def render(found: dict[str, list[tuple[str, int, str]]]) -> str:
    total = sum(len(v) for v in found.values())
    out = [
        "# iOS backend — hand-written / stub registry",
        "",
        "**GENERATED — do not edit. Run `python3 scripts/ios_handwritten_report.py`.**",
        "",
        "Every hand-written, stubbed, or sugar site in the iOS backend, from the in-code",
        "`// KANAMA-IOS-{STUB,HANDWRITTEN,SUGAR}` markers. The point: nothing silently no-ops",
        "without being listed here, so we don't repeat the deep-dive bugs from unwired",
        "annotations/signals. `scripts/check_ios_no_silent_stubs.py` fails CI on an",
        "un-annotated bare-default return.",
        "",
        f"Totals: **{found_count(found,'STUB')} STUB** · "
        f"**{found_count(found,'HANDWRITTEN')} HANDWRITTEN** · "
        f"**{found_count(found,'SUGAR')} SUGAR** ({total} marked sites).",
        "",
    ]
    for kind in KINDS:
        out.append(f"## {kind}")
        out.append("")
        out.append(f"_{KIND_BLURB[kind]}_")
        out.append("")
        rows = found[kind]
        if not rows:
            out.append("_(none)_")
            out.append("")
            continue
        out.append("| Location | Note |")
        out.append("|---|---|")
        for rel, lineno, note in rows:
            out.append(f"| `{rel}:{lineno}` | {note or '—'} |")
        out.append("")
    return "\n".join(out) + "\n"


def found_count(found: dict[str, list[tuple[str, int, str]]], kind: str) -> int:
    return len(found[kind])


def main() -> int:
    found = collect()
    OUTPUT.parent.mkdir(parents=True, exist_ok=True)
    OUTPUT.write_text(render(found), encoding="utf-8")
    total = sum(len(v) for v in found.values())
    print(f"[ios_handwritten_report] wrote {OUTPUT.relative_to(ROOT)} — {total} marked sites "
          f"(STUB={found_count(found,'STUB')} HANDWRITTEN={found_count(found,'HANDWRITTEN')} "
          f"SUGAR={found_count(found,'SUGAR')})")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
