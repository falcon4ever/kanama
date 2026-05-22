#!/usr/bin/env python3
"""Audit Kotlin scripts for risky runtime node lookups and string calls.

This is intentionally lightweight. It catches the highest-risk shape for ports:
scene node paths resolved with requireAs/requireNodeAs, or dynamic Godot method
dispatch, inside Godot-invoked runtime methods, signal callbacks, or coroutine
bodies instead of being cached or routed through typed helpers.
"""

from __future__ import annotations

import argparse
from dataclasses import dataclass
from pathlib import Path
import re
import sys


RISKY_ANNOTATIONS = {
    "OnInput",
    "OnPhysicsProcess",
    "OnProcess",
    "RegisterFunction",
    "Rpc",
}
RISKY_LAMBDA_MARKERS = (
    ".connect(",
    "kanamaScope.launch",
    ".await(",
)
LOOKUP_RE = re.compile(r"\brequire(?:Node)?As\s*\(")
DYNAMIC_CALL_RE = re.compile(r"\.call\s*\(")
RAW_RPC_RE = re.compile(r"\.(?:callLocalRpc|rpc|rpcId)\s*\(")
ANNOTATION_RE = re.compile(r"^\s*@([A-Za-z_][A-Za-z0-9_]*)\b")
FUN_RE = re.compile(r"\bfun\s+([A-Za-z_][A-Za-z0-9_]*)\b")


@dataclass
class Scope:
    kind: str
    name: str
    start_line: int
    depth: int


@dataclass
class Finding:
    path: Path
    line_no: int
    scope: Scope | None
    text: str


def brace_delta(line: str) -> int:
    code = line.split("//", 1)[0]
    return code.count("{") - code.count("}")


def audit_file(path: Path) -> list[Finding]:
    findings: list[Finding] = []
    pending_annotations: list[str] = []
    scopes: list[Scope] = []
    depth = 0

    for line_no, line in enumerate(path.read_text().splitlines(), start=1):
        stripped = line.strip()
        annotation = ANNOTATION_RE.match(line)
        if annotation:
            pending_annotations.append(annotation.group(1))

        fun_match = FUN_RE.search(line)
        if fun_match:
            risky = bool(RISKY_ANNOTATIONS.intersection(pending_annotations))
            if risky:
                scopes.append(Scope("function", fun_match.group(1), line_no, depth))
            pending_annotations = []
        elif stripped and not stripped.startswith("@"):
            pending_annotations = []

        if any(marker in line for marker in RISKY_LAMBDA_MARKERS) and "{" in line:
            scopes.append(Scope("lambda", stripped, line_no, depth))

        if RAW_RPC_RE.search(line):
            findings.append(Finding(path, line_no, scopes[-1] if scopes else None, stripped))

        active_scope = scopes[-1] if scopes else None
        if active_scope is not None and (LOOKUP_RE.search(line) or DYNAMIC_CALL_RE.search(line)):
            findings.append(Finding(path, line_no, active_scope, stripped))

        depth += brace_delta(line)
        while scopes and depth <= scopes[-1].depth:
            scopes.pop()

    return findings


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("roots", nargs="+", type=Path)
    args = parser.parse_args()

    files: list[Path] = []
    for root in args.roots:
        files.extend(sorted(root.rglob("*.kt")) if root.is_dir() else [root])

    findings = [finding for path in files for finding in audit_file(path)]
    if findings:
        print("[runtime_node_lookup] FAIL")
        for finding in findings:
            scope = (
                f"{finding.scope.kind} {finding.scope.name}"
                if finding.scope is not None else "file scope"
            )
            print(
                f"{finding.path}:{finding.line_no}: "
                f"{scope} "
                f"contains runtime required node lookup or dynamic call: {finding.text}"
            )
        return 1

    print(f"[runtime_node_lookup] PASS files={len(files)}")
    return 0


if __name__ == "__main__":
    sys.exit(main())
