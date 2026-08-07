#!/usr/bin/env python3
"""Audit KANAMA-BLOCKED markers: fail when a documented blocker no longer holds.

Every other `scripts/audit_*` compares code to code. This one compares a CLAIM
to reality, and it is deliberately inverted:

    the audit goes RED exactly when the limitation is LIFTED.

That is the failure mode nothing else in this repo notices. A comment that says
"blocked: missing iOS wrapper types (AnimatedSprite3D, RayCast3D)" was true when
written; the task that shipped those wrappers had no reason to grep for it, so
the comment outlived its cause by six weeks and kept steering decisions while
reading as authoritative.

Marker grammar (one line, anywhere a comment can live):

    KANAMA-BLOCKED(since:2026-06-23, symbol:RayCast3D@ios): FPS needs this wrapper

* `since:` is REQUIRED. An undated claim rots invisibly; a dated one at least
  tells a reader how much to trust it.
* Every other token asserts an ABSENCE -- the gap the claim depends on. The
  audit resolves each one and FAILS if the gap has been closed.
* An unresolvable token (unknown kind, unknown tree, a path whose anchor
  directory no longer exists, a Godot method that does not exist) is a LOUD
  error, never a silent pass. A marker that can never fire is worse than no
  marker at all: it is the exact defect this audit exists to kill.

Token kinds:

    symbol:<Name>@<tree>   no Kotlin declaration (or <Name>.kt file) named
                           <Name> exists in the named source tree.
    file:<path-or-glob>    no repo-relative path matches (globs allowed).
    webcall:<Class>.<m>    the Web backend contract
                           (scripts/platform_backend_calls.json) dispatches no
                           <Class>.<m>. <Class>.<m> is validated against
                           extension_api.json, so a typo cannot pass.
    task:<id>              the kanama-tasks spec <id>-*.md is still OPEN (lives
                           at the tasks-repo root, not in archive/). kanama-tasks
                           is a SEPARATE repo: when it is absent these tokens are
                           SKIPPED with a note, never a hard failure. Pass
                           --require-tasks to make an absent tasks repo fatal.

Exit codes: 0 = every blocker still holds, 1 = a blocker was lifted or a token
could not be resolved.
"""

from __future__ import annotations

import argparse
import datetime as _datetime
import json
import os
import re
import subprocess
import sys
from dataclasses import dataclass, field
from pathlib import Path


ROOT = Path(__file__).resolve().parents[1]
CONTRACT_PATH = ROOT / "scripts/platform_backend_calls.json"
EXTENSION_API_PATH = ROOT / "extension_api.json"

# Named Kotlin source trees a `symbol:` token may be scoped to. A symbol token
# MUST name one: an unscoped "does this exist anywhere" question is not the
# question any real blocker asks, and answering it repo-wide would make the
# audit fire on unrelated trees.
SYMBOL_TREES = {
    "desktop": "src/main/kotlin",
    "ios": "ios-runtime/src",
    "web": "web-runtime/src",
    "processor": "processor/src/main/kotlin",
    "example": "example_project",
    "repo": ".",
}

MARKER_RE = re.compile(r"KANAMA-BLOCKED\(([^)]*)\)\s*:\s*(.*)$")
# A marker is the word followed IMMEDIATELY by `(` or `:`. Prose that merely mentions
# the convention ("the KANAMA-BLOCKED marker below") is not a claim, while a botched
# marker (`KANAMA-BLOCKED: reason`) still gets caught as malformed rather than dropped.
CANDIDATE_RE = re.compile(r"KANAMA-BLOCKED[(:]")
DECL_KEYWORDS = ("class", "interface", "object", "fun", "val", "var", "typealias")
SINCE_RE = re.compile(r"^\d{4}-\d{2}-\d{2}$")
SYMBOL_TOKEN_RE = re.compile(r"^([A-Za-z_][A-Za-z0-9_]*)@([a-z]+)$")
WEBCALL_TOKEN_RE = re.compile(r"^([A-Za-z_][A-Za-z0-9_]*)\.([a-z_][a-z0-9_]*)$")
TASK_TOKEN_RE = re.compile(r"^[0-9]+[a-z]?$")

# Files whose KANAMA-BLOCKED text is grammar, not a claim. This audit spells the
# marker out; nothing else may be exempted, and the count is printed on PASS so
# the exemption cannot grow quietly.
EXEMPT_PATHS = {"scripts/audit_stale_blockers.py"}

BINARY_SUFFIXES = {
    ".png", ".jpg", ".jpeg", ".gif", ".ico", ".webp", ".svg",
    ".zip", ".jar", ".so", ".dylib", ".dll", ".a", ".o",
    ".ttf", ".otf", ".woff", ".woff2", ".wasm", ".pck", ".res",
    ".ogg", ".wav", ".mp3", ".import", ".pdf",
}


@dataclass
class Marker:
    path: str
    line_no: int
    since: str
    tokens: list[str]
    reason: str


@dataclass
class Result:
    lifted: list[str] = field(default_factory=list)
    errors: list[str] = field(default_factory=list)
    resolved: int = 0
    skipped: int = 0


def default_tasks_dir() -> Path:
    """Best guess at the sibling kanama-tasks checkout.

    Resolved beside the MAIN worktree, not beside ROOT: audits are routinely run
    from `.claude/worktrees/<name>`, where ROOT.parent is `worktrees`.
    """
    env = os.environ.get("KANAMA_TASKS_DIR")
    if env:
        return Path(env).expanduser()
    candidates = [ROOT.parent / "kanama-tasks"]
    try:
        common = subprocess.run(
            ["git", "-C", str(ROOT), "rev-parse", "--path-format=absolute", "--git-common-dir"],
            check=True,
            capture_output=True,
            text=True,
        ).stdout.strip()
        if common:
            candidates.append(Path(common).parent.parent / "kanama-tasks")
    except (subprocess.CalledProcessError, OSError):
        pass
    for candidate in candidates:
        if candidate.is_dir():
            return candidate
    return candidates[0]


def tracked_files(root: Path) -> list[str]:
    """Enumerate tracked text files. git keeps build output and vendored trees out."""
    out = subprocess.run(
        ["git", "-C", str(root), "ls-files", "-z"],
        check=True,
        capture_output=True,
        text=True,
    ).stdout
    return [
        name
        for name in out.split("\0")
        if name and Path(name).suffix.lower() not in BINARY_SUFFIXES
    ]


def scan_markers(root: Path) -> tuple[list[Marker], list[str]]:
    markers: list[Marker] = []
    errors: list[str] = []
    for name in tracked_files(root):
        if name in EXEMPT_PATHS:
            continue
        path = root / name
        try:
            text = path.read_text()
        except (UnicodeDecodeError, OSError):
            continue
        if not CANDIDATE_RE.search(text):
            continue
        in_fence = False
        is_markdown = path.suffix.lower() in {".md", ".markdown"}
        for line_no, line in enumerate(text.splitlines(), start=1):
            if is_markdown and line.lstrip().startswith("```"):
                in_fence = not in_fence
                continue
            if not CANDIDATE_RE.search(line):
                continue
            # A marker inside a markdown code fence is documentation of the
            # grammar, not a claim about this repo.
            if in_fence:
                continue
            match = MARKER_RE.search(line)
            if not match:
                errors.append(
                    f"{name}:{line_no}: malformed KANAMA-BLOCKED marker; expected "
                    f"KANAMA-BLOCKED(since:YYYY-MM-DD, <token>, ...): <reason>"
                )
                continue
            raw_tokens = [part.strip() for part in match.group(1).split(",")]
            raw_tokens = [part for part in raw_tokens if part]
            reason = match.group(2).strip()
            since = ""
            tokens: list[str] = []
            for token in raw_tokens:
                if token.startswith("since:"):
                    since = token[len("since:"):].strip()
                else:
                    tokens.append(token)
            if not SINCE_RE.match(since):
                errors.append(
                    f"{name}:{line_no}: KANAMA-BLOCKED needs a since:YYYY-MM-DD token "
                    f"(got {since!r}); undated claims rot invisibly"
                )
                continue
            if not tokens:
                errors.append(
                    f"{name}:{line_no}: KANAMA-BLOCKED has no checkable token; "
                    f"a marker with nothing to resolve is prose, not a claim"
                )
                continue
            if not reason:
                errors.append(f"{name}:{line_no}: KANAMA-BLOCKED has an empty reason")
                continue
            markers.append(Marker(name, line_no, since, tokens, reason))
    return markers, errors


def kotlin_symbol_hits(tree: Path, name: str) -> list[str]:
    hits: list[str] = []
    decl_re = re.compile(rf"\b(?:{'|'.join(DECL_KEYWORDS)})\s+{re.escape(name)}\b")
    for path in sorted(tree.rglob("*.kt")):
        if path.stem == name:
            hits.append(str(path.relative_to(ROOT)))
            continue
        try:
            text = path.read_text()
        except (UnicodeDecodeError, OSError):
            continue
        if name not in text:
            continue
        for line_no, line in enumerate(text.splitlines(), start=1):
            if decl_re.search(line):
                hits.append(f"{path.relative_to(ROOT)}:{line_no}")
                break
    return hits


def glob_anchor(pattern: str) -> Path:
    """Deepest wildcard-free directory of a glob.

    A `file:` token whose whole subtree was renamed would otherwise match
    nothing forever and pass forever. Requiring the anchor to exist turns that
    silent pass into a loud error.
    """
    anchor = ROOT
    for part in Path(pattern).parent.parts:
        if any(ch in part for ch in "*?["):
            break
        anchor = anchor / part
    return anchor


def load_contract_calls() -> set[str]:
    data = json.loads(CONTRACT_PATH.read_text())
    return {
        f"{call.get('className')}.{call.get('methodName')}"
        for call in data.get("calls", [])
    }


def load_godot_methods() -> dict[str, set[str]]:
    data = json.loads(EXTENSION_API_PATH.read_text())
    methods: dict[str, set[str]] = {}
    for klass in data.get("classes", []):
        methods[klass["name"]] = {m["name"] for m in klass.get("methods", []) or []}
    return methods


def task_files(tasks_dir: Path) -> tuple[dict[str, str], dict[str, str]]:
    open_tasks: dict[str, str] = {}
    closed_tasks: dict[str, str] = {}
    id_re = re.compile(r"^(\d+[a-z]?)-")
    for path in sorted(tasks_dir.glob("*.md")):
        match = id_re.match(path.name)
        if match:
            open_tasks[match.group(1)] = path.name
    archive = tasks_dir / "archive"
    if archive.is_dir():
        for path in sorted(archive.rglob("*.md")):
            match = id_re.match(path.name)
            if match:
                closed_tasks[match.group(1)] = f"archive/{path.name}"
    return open_tasks, closed_tasks


def resolve(
    marker: Marker,
    token: str,
    result: Result,
    contract: set[str],
    godot_methods: dict[str, set[str]],
    tasks: tuple[dict[str, str], dict[str, str]] | None,
) -> None:
    where = f"{marker.path}:{marker.line_no}"
    if ":" not in token:
        result.errors.append(
            f"{where}: token {token!r} has no kind; expected "
            f"symbol:/file:/webcall:/task:"
        )
        return
    kind, _, value = token.partition(":")
    kind = kind.strip()
    value = value.strip()
    if not value:
        result.errors.append(f"{where}: token {token!r} has an empty value")
        return

    if kind == "symbol":
        match = SYMBOL_TOKEN_RE.match(value)
        if not match:
            result.errors.append(
                f"{where}: symbol token must be symbol:<Name>@<tree> (got {value!r}); "
                f"trees: {', '.join(sorted(SYMBOL_TREES))}"
            )
            return
        name, tree_name = match.groups()
        if tree_name not in SYMBOL_TREES:
            result.errors.append(
                f"{where}: unknown symbol tree {tree_name!r}; "
                f"known: {', '.join(sorted(SYMBOL_TREES))}"
            )
            return
        tree = ROOT / SYMBOL_TREES[tree_name]
        if not tree.is_dir():
            result.errors.append(
                f"{where}: symbol tree {tree_name!r} -> {SYMBOL_TREES[tree_name]} "
                f"does not exist; the tree moved and this token can never fire"
            )
            return
        hits = kotlin_symbol_hits(tree, name)
        result.resolved += 1
        if hits:
            result.lifted.append(
                f"{where}: BLOCKER LIFTED -- {name} now exists in the {tree_name} tree "
                f"({', '.join(hits[:3])}{', ...' if len(hits) > 3 else ''}). "
                f"Claim: {marker.reason}"
            )
        return

    if kind == "file":
        anchor = glob_anchor(value)
        if not anchor.exists():
            result.errors.append(
                f"{where}: file token anchor {anchor.relative_to(ROOT)} does not exist; "
                f"the path moved and this token can never fire"
            )
            return
        hits = sorted(str(p.relative_to(ROOT)) for p in ROOT.glob(value))
        result.resolved += 1
        if hits:
            result.lifted.append(
                f"{where}: BLOCKER LIFTED -- {value} now matches "
                f"{', '.join(hits[:3])}{', ...' if len(hits) > 3 else ''}. "
                f"Claim: {marker.reason}"
            )
        return

    if kind == "webcall":
        match = WEBCALL_TOKEN_RE.match(value)
        if not match:
            result.errors.append(
                f"{where}: webcall token must be webcall:<Class>.<godot_method_name> "
                f"(got {value!r})"
            )
            return
        class_name, method_name = match.groups()
        if class_name not in godot_methods:
            result.errors.append(
                f"{where}: webcall token names class {class_name!r}, which is not in "
                f"extension_api.json; this token can never fire"
            )
            return
        if method_name not in godot_methods[class_name]:
            result.errors.append(
                f"{where}: webcall token names {class_name}.{method_name}, which Godot "
                f"does not declare; this token can never fire"
            )
            return
        result.resolved += 1
        if value in contract:
            result.lifted.append(
                f"{where}: BLOCKER LIFTED -- the Web backend contract now dispatches "
                f"{value}. Claim: {marker.reason}"
            )
        return

    if kind == "task":
        if not TASK_TOKEN_RE.match(value):
            result.errors.append(
                f"{where}: task token must be a task id like task:71 (got {value!r})"
            )
            return
        if tasks is None:
            result.skipped += 1
            return
        open_tasks, closed_tasks = tasks
        if value in open_tasks:
            result.resolved += 1
            return
        if value in closed_tasks:
            result.resolved += 1
            result.lifted.append(
                f"{where}: BLOCKER LIFTED -- task {value} is closed "
                f"({closed_tasks[value]}). Claim: {marker.reason}"
            )
            return
        result.errors.append(
            f"{where}: task {value} exists neither open nor archived in the "
            f"kanama-tasks checkout; this token can never fire"
        )
        return

    result.errors.append(
        f"{where}: unknown token kind {kind!r}; expected symbol:/file:/webcall:/task:"
    )


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    parser.add_argument(
        "--tasks-dir",
        type=Path,
        default=None,
        help="kanama-tasks checkout (default: $KANAMA_TASKS_DIR, else the sibling of the main worktree)",
    )
    parser.add_argument(
        "--require-tasks",
        action="store_true",
        help="fail instead of skipping when the kanama-tasks checkout is absent",
    )
    parser.add_argument(
        "--list",
        action="store_true",
        help="print every marker with its age",
    )
    args = parser.parse_args()

    markers, errors = scan_markers(ROOT)
    result = Result(errors=list(errors))

    tasks_dir = args.tasks_dir if args.tasks_dir is not None else default_tasks_dir()
    tasks: tuple[dict[str, str], dict[str, str]] | None = None
    tasks_note = ""
    if tasks_dir.is_dir():
        tasks = task_files(tasks_dir)
    elif args.require_tasks:
        result.errors.append(
            f"--require-tasks was passed but {tasks_dir} is not a directory"
        )
    else:
        tasks_note = f" task-tokens-skipped (no checkout at {tasks_dir})"

    contract = load_contract_calls()
    godot_methods = load_godot_methods()

    for marker in markers:
        for token in marker.tokens:
            resolve(marker, token, result, contract, godot_methods, tasks)

    if args.list:
        today = _datetime.date.today()
        for marker in sorted(markers, key=lambda m: (m.since, m.path, m.line_no)):
            try:
                age = (today - _datetime.date.fromisoformat(marker.since)).days
            except ValueError:
                age = -1
            print(
                f"  {marker.path}:{marker.line_no}  since {marker.since} ({age}d)  "
                f"{' '.join(marker.tokens)}  -- {marker.reason}"
            )

    if result.lifted or result.errors:
        print("[stale_blockers] FAIL", file=sys.stderr)
        for line in result.lifted:
            print(f"  - {line}", file=sys.stderr)
        for line in result.errors:
            print(f"  - UNRESOLVABLE {line}", file=sys.stderr)
        if result.lifted:
            print(
                "\n  A lifted blocker is not a bug in this audit: the comment above is "
                "now a lie.\n  Delete the marker and the claim it guards, then re-run.",
                file=sys.stderr,
            )
        return 1

    oldest = min((m.since for m in markers), default="-")
    print(
        f"[stale_blockers] PASS markers={len(markers)} tokens={result.resolved} "
        f"skipped={result.skipped} oldest={oldest} exempt={len(EXEMPT_PATHS)}"
        f"{tasks_note}"
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
