#!/usr/bin/env python3
"""Fail if a vararg Godot method is wrapped through ptrcall.

Godot's vararg methods, such as `Object.call(...)` and
`SceneTree.call_group(...)`, must go through the dynamic call path. The ptrcall
ABI cannot carry a variable argument list safely, and Godot reports
`ptrcall can't be used with vararg methods` when a wrapper gets this wrong.
"""

from __future__ import annotations

import json
import re
import sys
from dataclasses import dataclass
from pathlib import Path

from wrapper_model import wrapper_source_files


ROOT = Path(__file__).resolve().parents[1]
API_PATH = ROOT / "extension_api.json"
API_DIR = ROOT / "src/main/kotlin/net/multigesture/kanama/api"

BIND_RE = re.compile(
    r"(?:private\s+)?val\s+([A-Za-z_][A-Za-z0-9_]*)\s+by\s+lazy\s*\{\s*"
    r"ObjectCalls\.getMethodBind\(\s*"
    r'"([^"]+)"\s*,\s*"([^"]+)"',
    re.DOTALL,
)
PTRCALL_RE_TEMPLATE = r"ObjectCalls\.ptrcall[A-Za-z0-9_]*\(\s*{}\b"


@dataclass(frozen=True)
class Finding:
    path: str
    line: int
    message: str


def line_number(text: str, offset: int) -> int:
    return text.count("\n", 0, offset) + 1


def load_vararg_methods() -> set[tuple[str, str]]:
    data = json.loads(API_PATH.read_text(encoding="utf-8"))
    methods: set[tuple[str, str]] = set()
    for cls in data.get("classes", []):
        class_name = cls.get("name")
        for method in cls.get("methods", []):
            if method.get("is_vararg"):
                methods.add((class_name, method.get("name")))
    return methods


def audit_file(path: Path, vararg_methods: set[tuple[str, str]]) -> tuple[list[Finding], int]:
    text = path.read_text(encoding="utf-8")
    rel = path.relative_to(ROOT).as_posix()
    findings: list[Finding] = []
    checked = 0

    for match in BIND_RE.finditer(text):
        variable, class_name, method_name = match.groups()
        if (class_name, method_name) not in vararg_methods:
            continue
        checked += 1
        ptrcall_re = re.compile(PTRCALL_RE_TEMPLATE.format(re.escape(variable)))
        for call_match in ptrcall_re.finditer(text):
            findings.append(
                Finding(
                    rel,
                    line_number(text, call_match.start()),
                    f"{class_name}.{method_name} is vararg and must use dynamic Object.call, not ptrcall",
                )
            )

    return findings, checked


def main() -> int:
    vararg_methods = load_vararg_methods()
    findings: list[Finding] = []
    checked = 0

    for path in wrapper_source_files(API_DIR, companions=True):
        file_findings, file_checked = audit_file(path, vararg_methods)
        findings.extend(file_findings)
        checked += file_checked

    if findings:
        print("vararg ptrcall audit failed:", file=sys.stderr)
        for finding in findings:
            print(f"{finding.path}:{finding.line}: {finding.message}", file=sys.stderr)
        return 1

    print(f"[audit_vararg_ptrcalls] PASS checked {checked} vararg MethodBind reference(s)")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
