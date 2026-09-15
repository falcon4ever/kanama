#!/usr/bin/env python3
"""Print the Web backend's hand-written growth metric (task 96; review findings R3 and R13).

The "Option B" trigger in docs/contributing/backends/web.md once watched only the dispatch
companion (bookkeeping + transport) against the generated dispatch, one fifth of the hand-written
Web code. This prints every hand-written family next to everything generated from the contract, so
an admission PR pastes the whole picture and the reconsider line is judged on all of it.

Families (all paths relative to the repo root; a family is the SUM of its files' line counts):

  wrappers      hand-written files under web-runtime/.../api/ (outside generated/)
  wrapper policy scripts/generate_web_wrappers.py (per-opcode/per-class policy + custom sections)
  contract      kanama-common-api GodotBackendContract.kt (SPI + typed facade; the probe classes
                lived here before task 96)
  dispatch      WebBackendBookkeeping.kt + WebBackendTransport.kt + generate_web_backend.py's policy
  runtime       the other hand-written Kotlin/Wasm runtime files (Main, interop, generic call)
  emitter       processor WebScriptCodeEmitter.kt (R13: tracked per parcel)
  bridge        web-runtime kanama-web-bridge.js
  generated     WebCommonGodotBackend.generated.kt + InitialGodotCallDescriptors.generated.kt +
                api/generated/*.kt

Usage:
    python3 scripts/web_hand_metric.py                 # the working tree
    python3 scripts/web_hand_metric.py --ref origin/main
    python3 scripts/web_hand_metric.py --ref main --ref HEAD   # side by side
"""

from __future__ import annotations

import argparse
import fnmatch
import subprocess
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
API = "web-runtime/src/commonMain/kotlin/net/multigesture/kanama/api"
WASM = "web-runtime/src/wasmJsMain/kotlin/net/multigesture/kanama/web"
COMMON_WEB = "web-runtime/src/commonMain/kotlin/net/multigesture/kanama/web"

# family -> glob patterns (matched against repo-relative paths)
FAMILIES: dict[str, list[str]] = {
    "wrappers (hand)": [f"{API}/*.kt"],
    "wrapper policy (generate_web_wrappers.py)": ["scripts/generate_web_wrappers.py"],
    "contract (SPI + facade)": [
        "kanama-common-api/src/commonMain/kotlin/net/multigesture/kanama/backend/GodotBackendContract.kt"
    ],
    "dispatch companion": [
        f"{WASM}/WebBackendBookkeeping.kt",
        f"{WASM}/WebBackendTransport.kt",
        "scripts/generate_web_backend.py",
    ],
    "runtime (Main, interop, generic call, web/)": [
        f"{WASM}/Main.kt",
        f"{WASM}/WebCommandInterop.kt",
        f"{WASM}/WebGenericCall.kt",
        f"{COMMON_WEB}/*.kt",
    ],
    "emitter (WebScriptCodeEmitter.kt)": [
        "processor/src/main/kotlin/net/multigesture/kanama/processor/WebScriptCodeEmitter.kt"
    ],
    "bridge (kanama-web-bridge.js)": ["web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js"],
}
GENERATED: list[str] = [
    f"{WASM}/WebCommonGodotBackend.generated.kt",
    "kanama-common-api/src/commonMain/kotlin/net/multigesture/kanama/backend/InitialGodotCallDescriptors.generated.kt",
    f"{API}/generated/*.kt",
]


def tree_files(ref: str | None) -> dict[str, int]:
    """path -> line count for every tracked file (at `ref`, or the working tree)."""
    if ref is None:
        names = subprocess.run(
            ["git", "ls-files", "--cached", "--others", "--exclude-standard"],
            cwd=ROOT, capture_output=True, text=True, check=True,
        ).stdout.split("\n")
        return {n: _count(ROOT / n) for n in names if n and (ROOT / n).is_file()}
    names = subprocess.run(
        ["git", "ls-tree", "-r", "--name-only", ref], cwd=ROOT, capture_output=True, text=True, check=True
    ).stdout.split("\n")
    out: dict[str, int] = {}
    for name in names:
        if not name or not _interesting(name):
            continue
        blob = subprocess.run(["git", "show", f"{ref}:{name}"], cwd=ROOT, capture_output=True, check=True).stdout
        out[name] = blob.count(b"\n")
    return out


def _interesting(name: str) -> bool:
    patterns = [p for ps in FAMILIES.values() for p in ps] + GENERATED
    return any(fnmatch.fnmatch(name, p) for p in patterns)


def _count(path: Path) -> int:
    try:
        return path.read_bytes().count(b"\n")
    except OSError:
        return 0


def _is_generated(name: str) -> bool:
    return any(fnmatch.fnmatch(name, p) for p in GENERATED)


def measure(files: dict[str, int]) -> tuple[dict[str, int], int]:
    # fnmatch's `*` crosses `/`, so a hand family's glob also matches the generated files beneath
    # it; generated files are excluded from every hand family explicitly.
    totals: dict[str, int] = {}
    for family, patterns in FAMILIES.items():
        totals[family] = sum(
            n
            for name, n in files.items()
            if not _is_generated(name) and any(fnmatch.fnmatch(name, p) for p in patterns)
        )
    generated = sum(n for name, n in files.items() if _is_generated(name))
    return totals, generated


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--ref", action="append", help="git ref to measure (repeatable); default: working tree")
    args = parser.parse_args()
    refs: list[str | None] = args.ref or [None]
    columns = [(r or "worktree", *measure(tree_files(r))) for r in refs]
    width = max(len(f) for f in FAMILIES) + 2
    header = f"{'family':<{width}}" + "".join(f"{label:>14}" for label, _, _ in columns)
    print(header)
    print("-" * len(header))
    for family in FAMILIES:
        print(f"{family:<{width}}" + "".join(f"{totals[family]:>14,}" for _, totals, _ in columns))
    print("-" * len(header))
    print(f"{'hand-written total':<{width}}" + "".join(f"{sum(t.values()):>14,}" for _, t, _ in columns))
    print(f"{'generated from the contract':<{width}}" + "".join(f"{g:>14,}" for _, _, g in columns))
    print(
        f"{'hand / generated':<{width}}"
        + "".join(f"{(sum(t.values()) / g if g else float('inf')):>14.2f}" for _, t, g in columns)
    )
    return 0


if __name__ == "__main__":
    sys.exit(main())
