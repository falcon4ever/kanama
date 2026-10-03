#!/usr/bin/env python3
"""Gate: the public signatures of the Godot API surface change only when CHANGELOG.md says so (task 126).

Task 117 changed public signatures in four parcels (nullable fluent setters, a nullable `getTree`,
twelve renamed properties, `Long` -> `Int` on desktop) and task 128 retyped every enum. Each was
deliberate and announced, but nothing would have noticed an UNANNOUNCED one: a generator change that
retypes one parameter compiles, regenerates the whole tree, and passes the drift gate, because the
drift gate only proves that the committed tree is what the generator writes. This gate holds the
surface itself to checked-in snapshots, one file per surface under `api-snapshots/` (`SURFACES`):

  * `common.txt` -- the API tree all native backends compile (`src/commonMain/.../api`: the generated
    classes, `GlobalEnums.kt`, the hand roots, the `*.expect.kt` declarations);
  * `types.txt` -- the builtin value types (`src/commonMain/.../types`);
  * `jvm.txt` / `ios.txt` -- each platform's own api files (`src/jvmMain/.../api`,
    `src/iosMain/.../api`: per-platform wrappers, `<Class>.jvm.kt` / `<Class>.ios.kt` companions, the
    actuals, GD, the desktop name constants);
  * `web.txt` -- the Web wrappers, generated and hand-written (`web-runtime/.../api`, recursively);
  * `web-types.txt` -- the Web value types (`web-runtime/.../types`).

Every source directory is read recursively.

One line per declaration: `<owner>: <declaration>`, sorted by owner, for example
`Node: fun addChild(node: Node, forceReadableName: Boolean = ..., internalValue: Node.InternalMode = ...)`.
The snapshot is derived from the Kotlin SOURCES (no Gradle build, no compiled metadata) by a parser
written for the generator's regular output that also reads the hand-written files: comments and
string contents are dropped, brackets are matched, and every `fun` / `val` / `var` / `constructor` /
`class` / `object` / `interface` / `typealias` at a class-body level is a declaration. Its modifiers
and annotations are the ones on its own line plus those on the lines directly above it that hold
nothing else (`private` on the line before `fun x()` makes `x` private). Recorded per declaration:
kind, name, extension receiver, type parameters and `where` clauses, parameter names and types (a
parameter name is source API: Kotlin callers may name arguments), whether a parameter HAS a default
(its value is behaviour, not source: shown as `= ...`), the return / property type, supertypes, and
the source modifiers (`open`, `final`, `abstract`, `override`, `const`, `operator`, `infix`, `vararg`,
`data`, `value`, `companion`, ...). Not recorded: annotations (`@JvmStatic`, `@JvmName` are JVM
interop, not Kotlin source), constant values, bodies. `private` / `internal` declarations, those
marked `@Deprecated(level = DeprecationLevel.HIDDEN)` (invisible to callers), and everything inside
them are skipped; a `var` with a non-public setter is recorded as a `val`. A construct the parser does
not understand is an error naming file:line, never a silently skipped declaration.

Every public declaration must have a type the gate can see. A type not written out is accepted only
where it is certain from the source: `= Unit`, a literal (`= 0L`), a constructor call of its own class
on the companion (`val ZERO = Vector3(...)`), or a forwarding extension whose whole body is a call to
the same-named member with the same parameters (the Web import-compatibility aliases,
`resolve_forwarders`). Any other declaration without a written type FAILS the gate, naming file:line:
write the type.

Comparison with the snapshot:

  * a signature REMOVED or CHANGED is a source break. Every break must be announced: its owner (the
    outermost class or object, or `top-level` / the receiver class for a top-level declaration) must
    be named, as a whole word, in a line of `CHANGELOG.md`'s `## Unreleased` section that starts with
    the marker `- **Source break:**` (indented, as a nested bullet, is fine). An unannounced break
    FAILS, naming each one (`was` / `now`); an announced one FAILS until the snapshot is rewritten
    with `--write` (so the snapshot diff is part of the change under review);
  * a source-compatible change -- a parameter gains a default, a declaration becomes `open`, a class
    gains a supertype -- and an ADDITION are not breaks: they FAIL only until `--write` (no CHANGELOG
    line needed: an addition breaks no caller, but an unrecorded one would hide its own later
    removal). Removing a default is a break.

`--write` rewrites the snapshots and refuses to record an unannounced break. A missing
`## Unreleased` section counts as one with no markers.

    python3 scripts/check_public_signature_changes.py
    python3 scripts/check_public_signature_changes.py --write
    python3 scripts/check_public_signature_changes.py --root /tmp/scratch   # red runs on a scratch copy
"""

from __future__ import annotations

import argparse
import bisect
import re
import sys
import time
from collections import Counter
from dataclasses import dataclass, field
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
TAG = "[public_signatures]"
API_PACKAGE = "net.multigesture.kanama.api"
TYPES_PACKAGE = "net.multigesture.kanama.types"
CHANGELOG = "CHANGELOG.md"
# The announcement marker: a bullet in `## Unreleased` starting with these characters (it may be
# indented, as a nested bullet). The line must name the owner of every break it announces.
MARKER = "- **Source break:**"
MARKER_RE = re.compile(r"^\s*[-*] \*\*Source break:\*\*\s+\S")
SNAPSHOT_DIR = "api-snapshots"
SNAPSHOT_HEADER = (
    "# Public API signatures: {surface} -- GENERATED by scripts/check_public_signature_changes.py --write.\n"
    "# Do not edit by hand. A removed or changed line is a source break: name its owner in a `{marker}`\n"
    "# line of CHANGELOG.md `## Unreleased` (see docs/reference/wrapper-conventions.md, \"Source breaks\").\n"
    "# {count} declarations from {files} files: {what}.\n"
    "# Sources (recursive): {sources}\n"
)


@dataclass(frozen=True)
class Surface:
    name: str
    sources: tuple[str, ...]  # directories relative to the root, read recursively
    what: str  # one line for the snapshot header
    package: str = API_PACKAGE  # declarations in this package are named without it

    @property
    def snapshot(self) -> str:
        return f"{SNAPSHOT_DIR}/{self.name}.txt"


SURFACES = (
    Surface(
        "common",
        ("src/commonMain/kotlin/net/multigesture/kanama/api",),
        "the API tree desktop, Android and iOS compile from one source (generated classes, "
        "GlobalEnums, the hand roots, the expect declarations)",
    ),
    Surface(
        "types",
        ("src/commonMain/kotlin/net/multigesture/kanama/types",),
        "the builtin value types every native backend shares (Vector2/3/4, Color, Basis, Transform3D, ...)",
        TYPES_PACKAGE,
    ),
    Surface(
        "jvm",
        ("src/jvmMain/kotlin/net/multigesture/kanama/api",),
        "desktop/Android only: the per-platform classes, the <Class>.jvm.kt companions, the actuals, "
        "GD/Mathf and the name constants",
    ),
    Surface(
        "ios",
        ("src/iosMain/kotlin/net/multigesture/kanama/api",),
        "iOS only: the per-platform classes, the <Class>.ios.kt companions and the actuals",
    ),
    Surface(
        "web",
        ("web-runtime/src/commonMain/kotlin/net/multigesture/kanama/api",),
        "the Web wrappers: the generated classes (api/generated) and the hand-written facades",
    ),
    Surface(
        "web-types",
        ("web-runtime/src/commonMain/kotlin/net/multigesture/kanama/types",),
        "the Web value types (Vector2/3, Color, NodePath, ...)",
        TYPES_PACKAGE,
    ),
)


class ParseError(Exception):
    pass


# --------------------------------------------------------------------------------------------------
# Lexing: drop comments, empty every string/char literal (templates included), keep the newlines.
# --------------------------------------------------------------------------------------------------

_SPECIAL = re.compile(r'//|/\*|"""|"|\'')
_BLOCK = re.compile(r"/\*|\*/")
_IN_STRING = re.compile(r'[\\"\n]|\$\{')
_IN_RAW = re.compile(r'"""|\$\{')
_IN_CODE = re.compile(r'//|/\*|"""|"|\'|[{}]')
_CHAR = re.compile(r"'(?:\\u[0-9a-fA-F]{4}|\\.|[^'\\\n])'")


def _skip_block_comment(src: str, i: int) -> int:
    depth, k = 1, i
    while depth:
        m = _BLOCK.search(src, k)
        if not m:
            raise ParseError("unterminated block comment")
        depth += 1 if m.group() == "/*" else -1
        k = m.end()
    return k


def _skip_template(src: str, i: int) -> int:
    """[i] is just past `${`: the index just past its closing `}`."""
    depth, k = 1, i
    while True:
        m = _IN_CODE.search(src, k)
        if not m:
            raise ParseError("unterminated string template")
        tok = m.group()
        if tok == "{":
            depth += 1
            k = m.end()
        elif tok == "}":
            depth -= 1
            k = m.end()
            if depth == 0:
                return k
        elif tok == "//":
            nl = src.find("\n", m.end())
            k = len(src) if nl < 0 else nl
        elif tok == "/*":
            k = _skip_block_comment(src, m.end())
        elif tok == '"""':
            k = _skip_raw(src, m.end())
        elif tok == '"':
            k = _skip_string(src, m.end())
        else:
            k = _skip_char(src, m.start())


def _skip_string(src: str, i: int) -> int:
    k = i
    while True:
        m = _IN_STRING.search(src, k)
        if not m or m.group() == "\n":
            raise ParseError("unterminated string literal")
        tok = m.group()
        if tok == "\\":
            k = m.end() + 1
        elif tok == '"':
            return m.end()
        else:
            k = _skip_template(src, m.end())


def _skip_raw(src: str, i: int) -> int:
    k = i
    while True:
        m = _IN_RAW.search(src, k)
        if not m:
            raise ParseError("unterminated raw string")
        if m.group() == "${":
            k = _skip_template(src, m.end())
            continue
        end = m.end()
        while end < len(src) and src[end] == '"':  # `""""` ends with the LAST quote run
            end += 1
        return end


def _skip_char(src: str, i: int) -> int:
    m = _CHAR.match(src, i)
    if not m:
        raise ParseError("unrecognised character literal")
    return m.end()


def compact(src: str) -> str:
    out: list[str] = []
    i = 0
    while True:
        m = _SPECIAL.search(src, i)
        if not m:
            out.append(src[i:])
            return "".join(out)
        out.append(src[i : m.start()])
        tok = m.group()
        if tok == "//":
            nl = src.find("\n", m.end())
            i = len(src) if nl < 0 else nl
        elif tok == "/*":
            end = _skip_block_comment(src, m.end())
            out.append("\n" if "\n" in src[m.start() : end] else " ")
            i = end
        elif tok == '"""':
            i = _skip_raw(src, m.end())
            out.append('""')
        elif tok == '"':
            i = _skip_string(src, m.end())
            out.append('""')
        else:
            i = _skip_char(src, m.start())
            out.append("' '")


# --------------------------------------------------------------------------------------------------
# Declarations
# --------------------------------------------------------------------------------------------------

KEYWORD_RE = re.compile(r"(?<![\w.$:@`])(fun|val|var|constructor|class|object|interface|typealias)\b")
# An annotation: `@Name`, `@get:Name`, `@Name(args)` with the arguments directly after the name (one
# nesting level). No whitespace before `(`, so `@Composable (Int) -> Unit` keeps its function type.
ANNOTATION_RE = re.compile(r"@[\w.:]+(?:\((?:[^()]|\([^()]*\))*\))?")
VISIBILITY = {"public", "private", "internal", "protected"}
HIDDEN = {"private", "internal"}
# Kept in the rendered line, in this order; `public`, `expect`, `actual` are dropped (they change
# nothing a caller writes).
KEPT_MODIFIERS = (
    "protected",
    "final",
    "abstract",
    "open",
    "sealed",
    "override",
    "lateinit",
    "const",
    "suspend",
    "inline",
    "infix",
    "operator",
    "tailrec",
    "external",
    "data",
    "value",
    "enum",
    "annotation",
    "inner",
    "fun",
    "companion",
)
MODIFIERS = set(KEPT_MODIFIERS) | VISIBILITY | {"expect", "actual", "noinline", "crossinline"}
TYPE_STOP_WORDS = ("get", "set", "by", "where")
PROPERTY_NAME_RE = re.compile(r"[^:=\n;{]*")
INFERRED = "<inferred>"
HIDDEN_DEPRECATION_RE = re.compile(r"\bDeprecationLevel\.HIDDEN\b")


class Source:
    def __init__(self, path: Path, rel: str, text: str):
        self.path = path
        self.rel = rel
        self.text = text
        self.positions: list[int] = []
        self.depths: list[int] = []
        self.closers: dict[int, int] = {}
        self.openers: dict[int, int] = {}
        stack: list[tuple[int, str]] = []
        pairs = {")": "(", "]": "[", "}": "{"}
        for m in re.finditer(r"[()\[\]{}]", text):
            ch, pos = m.group(), m.start()
            if ch in "([{":
                stack.append((pos, ch))
            else:
                if not stack or stack[-1][1] != pairs[ch]:
                    raise ParseError(f"{rel}:{self.line(pos)}: unbalanced '{ch}'")
                opener, _ = stack.pop()
                self.closers[opener] = pos
                self.openers[pos] = opener
            self.positions.append(pos)
            self.depths.append(len(stack))
        if stack:
            raise ParseError(f"{rel}:{self.line(stack[-1][0])}: unclosed '{stack[-1][1]}'")

    def depth(self, pos: int) -> int:
        """Bracket depth just before [pos]."""
        idx = bisect.bisect_left(self.positions, pos)
        return self.depths[idx - 1] if idx else 0

    def line(self, pos: int) -> int:
        return self.text.count("\n", 0, pos) + 1

    def where(self, pos: int) -> str:
        return f"{self.rel}:{self.line(pos)}"


def norm(text: str) -> str:
    """One spelling of a type/parameter fragment: single spaces, none inside brackets."""
    text = ANNOTATION_RE.sub("", text)
    text = re.sub(r"\s+", " ", text).strip()
    text = re.sub(r"([(<\[]) ", r"\1", text)
    text = re.sub(r" ([)>\],?.])", r"\1", text)
    text = re.sub(r"\.\s+", ".", text)
    text = re.sub(r"\s*:\s*", ": ", text)
    text = re.sub(r"\s*->\s*", " -> ", text)
    return text.rstrip(",").strip()


def split_top_level(params: str) -> list[str]:
    out: list[str] = []
    depth = 0
    start = 0
    for i, ch in enumerate(params):
        if ch in "(<[{":
            depth += 1
        elif ch in ")]}" or (ch == ">" and params[i - 1 : i] != "-"):
            depth -= 1
        elif ch == "," and depth == 0:
            out.append(params[start:i])
            start = i + 1
    out.append(params[start:])
    return [p.strip() for p in out if p.strip()]


def top_level_equals(param: str) -> int:
    depth = 0
    for i, ch in enumerate(param):
        if ch in "(<[{":
            depth += 1
        elif ch in ")]}" or (ch == ">" and param[i - 1 : i] != "-"):
            depth -= 1
        elif ch == "=" and depth == 0 and param[i + 1 : i + 2] != "=" and param[i - 1 : i] not in "!<>=":
            return i
    return -1


def render_param(param: str, strip_property: bool) -> str:
    param = ANNOTATION_RE.sub("", param).strip()
    eq = top_level_equals(param)
    default = ""
    if eq >= 0:
        param, default = param[:eq], " = ..."
    words = param.split()
    mods = []
    while words and words[0] in MODIFIERS | {"vararg", "val", "var"}:
        mods.append(words.pop(0))
    if strip_property:
        mods = [m for m in mods if m not in ("val", "var", "override", "private", "internal", "public", "protected")]
    body = norm(" ".join(words))
    return " ".join([*mods, body]) + default


def render_params(raw: str, strip_property: bool = False) -> str:
    return "(" + ", ".join(render_param(p, strip_property) for p in split_top_level(raw)) + ")"


def infer_literal(expr: str) -> str:
    e = expr.strip()
    if re.fullmatch(r"-?(?:0[xX][0-9a-fA-F_]+|\d[\d_]*)L", e):
        return "Long"
    if re.fullmatch(r"-?(?:0[xX][0-9a-fA-F_]+|\d[\d_]*)", e):
        return "Int"
    if re.fullmatch(r"-?\d[\d_]*(?:\.\d+)?(?:[eE][+-]?\d+)?[fF]", e):
        return "Float"
    if re.fullmatch(r"-?\d[\d_]*\.\d+(?:[eE][+-]?\d+)?|-?\d[\d_]*[eE][+-]?\d+", e):
        return "Double"
    if e in ("true", "false"):
        return "Boolean"
    if e == '""':
        return "String"
    return INFERRED


@dataclass
class Decl:
    scope: str
    text: str
    pos: int
    origin: str = ""  # file:line, filled for a declaration whose type is `<inferred>`
    # For a `fun` whose return type is not written: the function its expression body calls, when the
    # WHOLE body is that one call (`= play(name, customSpeed)`); see resolve_forwarders.
    forward: str | None = None


WORD_CHARS = frozenset("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_.:")


def word_start(text: str, j: int, floor: int) -> int:
    """Start of the `@?[\\w.:]+` run that ends just before [j] (== j when there is none)."""
    k = j
    while k > floor and text[k - 1] in WORD_CHARS:
        k -= 1
    if k > floor and text[k - 1] == "@" and k < j:
        k -= 1
    return k


@dataclass
class Leading:
    """What stands before a declaration keyword: modifiers and annotation texts."""

    mods: list[str] = field(default_factory=list)
    annotations: list[str] = field(default_factory=list)


class Parser:
    def __init__(self, src: Source, package_prefix: str):
        self.src = src
        self.text = src.text
        self.prefix = package_prefix
        self.decls: list[Decl] = []

    # -- small readers ------------------------------------------------------------------------
    def skip_ws(self, i: int, newlines: bool = True) -> int:
        text = self.text
        while i < len(text) and (text[i] in " \t\r" or (newlines and text[i] == "\n")):
            i += 1
        return i

    def skip_angles(self, i: int) -> int:
        depth = 0
        text = self.text
        while i < len(text):
            ch = text[i]
            if ch == "(" or ch == "[":
                i = self.src.closers[i] + 1
                continue
            if ch == "<":
                depth += 1
            elif ch == ">" and text[i - 1] != "-":
                depth -= 1
                if depth == 0:
                    return i + 1
            elif ch in "{};=" and depth > 0:
                break
            i += 1
        raise ParseError(f"{self.src.where(i)}: unbalanced '<'")

    def read_type(self, i: int, end: int) -> tuple[str, int]:
        """A type starting at [i]; stops at `{`, `=`, `;`, `}`, a newline, or get/set/by/where at depth 0."""
        text = self.text
        start = i
        angle = 0
        while i < end:
            ch = text[i]
            if ch in "([":
                i = self.src.closers[i] + 1
                continue
            if ch == "<":
                angle += 1
            elif ch == ">" and text[i - 1] != "-":
                angle -= 1
            elif angle == 0 and ch in "{=;}\n,)":
                if ch == "\n" and text[start:i].rstrip().endswith(("->", ".", "<", ",")):
                    i += 1
                    continue
                break
            elif angle == 0 and ch.isalpha() and (i == 0 or not (text[i - 1].isalnum() or text[i - 1] in "_.")):
                word = re.match(r"\w+", text[i:]).group()
                if word in TYPE_STOP_WORDS and text[i - 1 : i].isspace():
                    break
                i += len(word)
                continue
            i += 1
        return norm(text[start:i]), i

    def read_where(self, k: int, end: int) -> tuple[str, int]:
        """A `where T : A, T : B` clause at [k] (if any), up to the body or the end of the line."""
        text = self.text
        if not re.compile(r"where\b").match(text, k):
            return "", k
        i = k
        while i < end:
            ch = text[i]
            if ch in "([":
                i = self.src.closers[i] + 1
                continue
            if ch in "{=;}\n":
                break
            i += 1
        return " " + norm(text[k:i]), self.skip_ws(i, newlines=False)

    def leading(self, pos: int, floor: int) -> Leading:
        """Modifiers and annotations of the declaration whose keyword is at [pos].

        They are the tokens directly before the keyword on its own line, plus those on the lines
        directly above that hold nothing but modifiers and annotations (`private` on the line before
        `fun x()`, `@JvmStatic` above a `fun`). Anything else before the keyword on its own line is an
        error; a line above that holds anything else ends the search (it belongs to other code)."""
        text = self.text
        src = self.src
        keyword_line = max(text.rfind("\n", floor, pos) + 1, floor)
        tokens: list[tuple[int, str]] = []  # (line start, token)
        stop = -1  # where the tokens end: the last character of the code before them, if any
        i = pos
        while True:
            j = i
            while j > floor and text[j - 1] in " \t\r\n":
                j -= 1
            if j <= floor:
                break
            if text[j - 1] == ")":
                opener = src.openers.get(j - 1)
                start = word_start(text, opener, floor) if opener is not None else -1
                if start < 0 or start == opener or text[start] != "@":
                    stop = j - 1
                    break
                tokens.append((max(text.rfind("\n", floor, start) + 1, floor), text[start:j]))
                i = start
                continue
            start = word_start(text, j, floor)
            if start == j:
                stop = j - 1
                break
            token = text[start:j]
            if token.startswith("@") or token in MODIFIERS:
                tokens.append((max(text.rfind("\n", floor, start) + 1, floor), token))
                i = start
                continue
            stop = start
            break
        if stop >= 0:
            stop_line = max(text.rfind("\n", floor, stop) + 1, floor)
            if stop_line == keyword_line:
                raise ParseError(
                    f"{src.where(pos)}: unexpected {text[stop_line:pos].strip()!r} before a declaration keyword "
                    "on the same line"
                )
            tokens = [(line, token) for line, token in tokens if line != stop_line]
        result = Leading()
        for _, token in reversed(tokens):
            (result.annotations if token.startswith("@") else result.mods).append(token)
        return result

    # -- the walk -----------------------------------------------------------------------------
    def parse_body(self, start: int, end: int, scope: str, enum_body: bool = False) -> None:
        src = self.src
        text = self.text
        base = src.depth(start)
        if enum_body:
            start = self.enum_entries(start, end, base, scope)
        resume = start
        properties: list[tuple[int, int, int]] = []  # (decl index, header end, keyword pos)
        keyword_positions: list[int] = []
        for match in KEYWORD_RE.finditer(text, start, end):
            pos = match.start()
            if pos < resume or src.depth(pos) != base:
                continue
            kind = match.group(1)
            after = self.skip_ws(match.end())
            if kind == "fun" and text.startswith("interface", after):
                continue
            if kind == "fun" and text[after] == "(":
                continue  # an anonymous function
            if kind == "object" and not re.match(r"\w", text[after : after + 1]):
                line_start = max(text.rfind("\n", start, pos) + 1, start)
                if not re.search(r"\bcompanion\s+$", text[line_start:pos]):
                    continue  # an object expression: `= object : Foo() { ... }`
            lead = self.leading(pos, start)
            mods = lead.mods
            keyword_positions.append(pos)
            hidden = any(m in HIDDEN for m in mods) or any(HIDDEN_DEPRECATION_RE.search(a) for a in lead.annotations)
            if kind == "fun":
                resume = self.parse_fun(pos, after, end, mods, scope, hidden)
            elif kind in ("val", "var"):
                index = len(self.decls)
                resume = self.parse_property(kind, pos, after, end, mods, scope, hidden)
                if not hidden and kind == "var":
                    properties.append((index, resume, pos))
            elif kind == "constructor":
                resume = self.parse_secondary_constructor(pos, after, mods, scope, hidden)
            elif kind == "typealias":
                nl = text.find("\n", after)
                nl = end if nl < 0 or nl > end else nl
                if not hidden:
                    self.emit(scope, " ".join([*self.kept(mods), "typealias", norm(text[after:nl])]), pos)
                resume = nl
            else:
                resume = self.parse_classifier(kind, pos, after, end, mods, scope, hidden)
        # A `var` whose setter is not public is a `val` to every caller.
        keyword_positions.append(end)
        for index, header_end, pos in properties:
            next_pos = next(p for p in keyword_positions if p > pos)
            tail = text[header_end:next_pos]
            if re.search(r"\b(?:private|internal|protected)\s+set\b", tail):
                decl = self.decls[index]
                decl.text = re.sub(r"\bvar\b", "val", decl.text, count=1)

    def enum_entries(self, start: int, end: int, base: int, scope: str) -> int:
        text = self.text
        i = start
        while i < end:
            if text[i] in "([{":
                i = self.src.closers[i] + 1
                continue
            if text[i] == ";":
                break
            i += 1
        for entry in split_top_level(text[start:i]):
            name = re.match(r"\w+", ANNOTATION_RE.sub("", entry).strip())
            if not name:
                raise ParseError(f"{self.src.where(start)}: unreadable enum entry {entry!r}")
            self.emit(scope, f"enum entry {name.group()}", start)
        return min(i + 1, end)

    def kept(self, mods: list[str]) -> list[str]:
        return [m for m in KEPT_MODIFIERS if m in mods]

    def emit(self, scope: str, text: str, pos: int, forward: str | None = None) -> None:
        origin = self.src.where(pos) if INFERRED in text else ""
        self.decls.append(Decl(scope, text, pos, origin, forward))

    def whole_body_call(self, k: int) -> str | None:
        """The function name if the expression body at [k] (`=`) is exactly one call `name(...)`."""
        text = self.text
        call = re.compile(r"=\s*(\w+)\s*\(").match(text, k)
        if not call:
            return None
        close = self.src.closers[call.end() - 1]
        rest = re.compile(r"[ \t\r]*(?:\n\s*|;|$)").match(text, close + 1)
        if not rest:
            return None  # something follows the call on its line: `= f(x).g()`, `= f(x) { ... }`
        nxt = rest.end()
        if nxt < len(text) and text[nxt] in ".?:+-*/%|&^<>=":
            return None  # a continuation on the next line
        return call.group(1)

    def parse_fun(self, pos: int, after: int, end: int, mods: list[str], scope: str, hidden: bool) -> int:
        text = self.text
        i = after
        type_params = ""
        if text[i] == "<":
            j = self.skip_angles(i)
            type_params = norm(text[i:j]) + " "
            i = self.skip_ws(j)
        open_paren = i
        angle = 0
        while open_paren < end:
            ch = text[open_paren]
            if ch == "<":
                angle += 1
            elif ch == ">" and text[open_paren - 1] != "-":
                angle -= 1
            elif ch == "(" and angle == 0:
                break
            elif ch in "{};=\n" and angle == 0:
                raise ParseError(f"{self.src.where(pos)}: no parameter list after `fun`")
            open_paren += 1
        name = norm(text[i:open_paren])
        if not name:
            raise ParseError(f"{self.src.where(pos)}: no function name")
        close = self.src.closers[open_paren]
        params = render_params(text[open_paren + 1 : close])
        j = self.skip_ws(close + 1, newlines=False)
        ret = ""
        if text[j] == ":":
            ret, j = self.read_type(self.skip_ws(j + 1), end)
        k = self.skip_ws(j, newlines=False)
        where, k = self.read_where(k, end)
        forward = None
        if not ret and text.startswith("=", k) and not text.startswith("==", k):
            if re.compile(r"=\s*Unit\s*(?:\n|;|$)").match(text, k):
                ret = "Unit"
            else:
                ret = INFERRED
                forward = self.whole_body_call(k)
        if not hidden:
            rendered = " ".join([*self.kept(mods), f"fun {type_params}{name}{params}"])
            if ret and ret != "Unit":
                rendered += f": {ret}"
            self.emit(scope, rendered + where, pos, forward)
        if text.startswith("{", k):
            return self.src.closers[k] + 1
        return k

    def parse_property(self, kind: str, pos: int, after: int, end: int, mods: list[str], scope: str, hidden: bool) -> int:
        text = self.text
        i = after
        type_params = ""
        if text[i] == "<":
            j = self.skip_angles(i)
            type_params = norm(text[i:j]) + " "
            i = self.skip_ws(j)
        m = PROPERTY_NAME_RE.match(text, i)
        name_part = m.group()
        stop = re.search(r"\s(?:by|get|set)\b", name_part)
        if stop:
            name_part = name_part[: stop.start()]
        name = norm(name_part)
        j = i + len(name_part)
        if not re.fullmatch(r"[\w.`<>?, ]+", name or "#"):
            raise ParseError(f"{self.src.where(pos)}: unreadable property name {name!r}")
        j = self.skip_ws(j, newlines=False)
        prop_type = ""
        if text[j] == ":":
            prop_type, j = self.read_type(self.skip_ws(j + 1), end)
        k = self.skip_ws(j, newlines=False)
        if not prop_type:
            if text.startswith("=", k):
                nl = text.find("\n", k)
                prop_type = infer_literal(text[k + 1 : nl if nl >= 0 else end])
                # `val ZERO = Vector3(0f, 0f, 0f)` on Vector3's companion: a constructor call of the
                # owning class has exactly that type.
                owner = [part for part in scope.split(".") if part != "Companion"][-1:] if scope else []
                call = re.compile(r"\s*(\w+)\s*\(").match(text, k + 1)
                if prop_type == INFERRED and owner and call and call.group(1) == owner[0]:
                    prop_type = owner[0]
            else:
                prop_type = INFERRED
        if not hidden:
            rendered = " ".join([*self.kept(mods), f"{kind} {type_params}{name}: {prop_type}"])
            self.emit(scope, rendered, pos)
        return k

    def parse_secondary_constructor(self, pos: int, after: int, mods: list[str], scope: str, hidden: bool) -> int:
        text = self.text
        if text[after] != "(":
            raise ParseError(f"{self.src.where(pos)}: `constructor` without a parameter list")
        close = self.src.closers[after]
        if not hidden:
            rendered = " ".join([*self.kept(mods), "constructor" + render_params(text[after + 1 : close])])
            self.emit(scope, rendered, pos)
        return close + 1

    def parse_classifier(self, kind: str, pos: int, after: int, end: int, mods: list[str], scope: str, hidden: bool) -> int:
        text = self.text
        src = self.src
        name_match = re.compile(r"`?(\w+)`?").match(text, after)
        if kind == "object" and not name_match:
            name, i = "Companion", after
        elif not name_match:
            raise ParseError(f"{src.where(pos)}: no name after `{kind}`")
        else:
            name, i = name_match.group(1), name_match.end()
        own_scope = f"{scope}.{name}" if scope else f"{self.prefix}{name}"
        i = self.skip_ws(i, newlines=False)
        type_params = ""
        if text.startswith("<", i):
            j = self.skip_angles(i)
            type_params = norm(text[i:j])
            i = self.skip_ws(j, newlines=False)
        ctor_line = None
        ctor_props: list[str] = []
        ctor = re.compile(
            r"((?:@[\w.:]+(?:\((?:[^()]|\([^()]*\))*\))?\s+|(?:public|private|internal|protected)\s+)*)(constructor\s*)?\("
        ).match(text, i)
        if ctor:
            open_paren = ctor.end() - 1
            close = src.closers[open_paren]
            ctor_mods = ANNOTATION_RE.sub(" ", ctor.group(1)).split()
            raw = text[open_paren + 1 : close]
            if not any(m in HIDDEN for m in ctor_mods):
                ctor_line = " ".join([*self.kept(ctor_mods), "constructor" + render_params(raw, strip_property=True)])
            for param in split_top_level(raw):
                p = ANNOTATION_RE.sub("", param).strip()
                pm = re.match(r"((?:\w+\s+)*?)(val|var)\s+`?(\w+)`?\s*:\s*(.*)$", p, re.S)
                if pm:
                    pmods = pm.group(1).split()
                    if any(m in HIDDEN for m in pmods):
                        continue
                    ptype = pm.group(4)
                    eq = top_level_equals(ptype)
                    if eq >= 0:
                        ptype = ptype[:eq]
                    ctor_props.append(" ".join([*self.kept(pmods), f"{pm.group(2)} {pm.group(3)}: {norm(ptype)}"]))
            i = close + 1
        # Supertypes, up to the body or the end of the header.
        supertypes: list[str] = []
        j = self.skip_ws(i, newlines=False)
        if text.startswith(":", j):
            k = j + 1
            while k < end:
                ch = text[k]
                if ch in "([":
                    k = src.closers[k] + 1
                    continue
                if ch == "{" or ch == "}" or ch == ";":
                    break
                if ch == "\n" and not text[j + 1 : k].rstrip().endswith((",", ":")) and text[j + 1 : k].strip():
                    break
                k += 1
            for entry in split_top_level(text[j + 1 : k]):
                entry = re.sub(r"\s+by\s+.*$", "", entry.strip(), flags=re.S)
                entry = re.sub(r"\((?:[^()]|\([^()]*\))*\)\s*$", "", entry.strip())
                supertypes.append(norm(entry))
            j = k
        body_start = self.skip_ws(j)
        has_body = text.startswith("{", body_start) and src.depth(body_start) == src.depth(pos)
        if not hidden:
            header = " ".join([*self.kept(mods), kind])
            if not ("companion" in mods and name == "Companion"):
                header += f" {name}{type_params}"
            if supertypes:
                header += " : " + ", ".join(supertypes)
            self.emit(own_scope, header, pos)
            if ctor_line is not None:
                self.emit(own_scope, ctor_line, pos)
            for prop in ctor_props:
                self.emit(own_scope, prop, pos)
        if has_body:
            close = src.closers[body_start]
            if not hidden:
                self.parse_body(body_start + 1, close, own_scope, enum_body="enum" in mods and kind == "class")
            return close + 1
        return j


def package_prefix(text: str, home: str) -> str:
    m = re.search(r"^\s*package\s+([\w.]+)", text, re.MULTILINE)
    package = m.group(1) if m else ""
    return "" if package == home else (f"{package}." if package else "<root>.")


SUPERTYPES_RE = re.compile(r"^(?:\w+ )*(?:class|object|interface)\b[^:]*:\s*(.*)$")
FUN_LINE_RE = re.compile(r"^(?:\w+ )*fun ")


def resolve_forwarders(decls: list[Decl]) -> None:
    """Give a forwarding extension the return type of the member it calls.

    The Web wrappers carry import-compatibility extensions next to each member,
    `fun AnimatedSprite2D.play(name: String = "") = play(name)`, whose return type Kotlin infers from
    the member. When an extension's whole body is a call to the member of the same name with the same
    parameter list, on its receiver class or a supertype in the same surface, the line takes that
    member's return type. Anything else stays `<inferred>`, which fails the gate."""
    members: dict[str, dict[str, str]] = {}
    supertypes: dict[str, list[str]] = {}
    for decl in decls:
        if not decl.scope:
            continue
        if FUN_LINE_RE.match(decl.text) and INFERRED not in decl.text:
            key = FUN_LINE_RE.sub("fun ", decl.text)
            head, _, ret = key.rpartition("): ")
            signature, ret = (head + ")", ret) if head and "(" in head else (key, "")
            members.setdefault(decl.scope, {})[signature] = ret
        header = SUPERTYPES_RE.match(decl.text)
        if header and decl.scope.rsplit(".", 1)[-1] in decl.text:
            supertypes[decl.scope] = [re.sub(r"<.*$", "", t.strip()) for t in split_top_level(header.group(1))]
    for decl in decls:
        if decl.scope or decl.forward is None or not decl.text.endswith(f": {INFERRED}"):
            continue
        m = re.match(r"^(?:\w+ )*fun (?:<[^>]*> )?([\w.]+)\.(\w+)(\(.*\)): " + re.escape(INFERRED) + "$", decl.text)
        if not m or m.group(2) != decl.forward:
            continue
        owner, name, params = m.groups()
        wanted = f"fun {name}{params}"
        queue, seen = [owner], set()
        while queue:
            cls = queue.pop(0)
            if cls in seen:
                continue
            seen.add(cls)
            if wanted in members.get(cls, {}):
                ret = members[cls][wanted]
                prefix = decl.text[: -len(f": {INFERRED}")]
                decl.text = f"{prefix}: {ret}" if ret else prefix
                decl.origin = ""
                break
            queue += supertypes.get(cls, [])


def surface_files(root: Path, surface: Surface) -> list[Path]:
    files: list[Path] = []
    for source in surface.sources:
        found = sorted((root / source).rglob("*.kt"))
        if not found:
            raise ParseError(f"{source}: no Kotlin sources (wrong --root, or the tree moved)")
        files += found
    return sorted(set(files))


@dataclass
class SurfaceResult:
    lines: list[str]
    files: int
    inferred: list[str]  # "file:line: line" of every declaration whose type the gate cannot see


def surface_lines(root: Path, surface: Surface) -> SurfaceResult:
    files = surface_files(root, surface)
    decls: list[Decl] = []
    for path in files:
        rel = str(path.relative_to(root))
        try:
            text = compact(path.read_text(encoding="utf-8"))
        except ParseError as error:
            raise ParseError(f"{rel}: {error}") from None
        src = Source(path, rel, text)
        parser = Parser(src, package_prefix(text, surface.package))
        parser.parse_body(0, len(text), "")
        decls += parser.decls
    resolve_forwarders(decls)
    lines = [f"{decl.scope or '<top-level>'}: {decl.text}" for decl in decls]
    inferred = [f"{decl.origin}: {line}" for decl, line in zip(decls, lines) if INFERRED in decl.text]
    return SurfaceResult(sort_lines(lines), len(files), inferred)


CLASSIFIER_LINE_RE = re.compile(r"^(?:\w+ )*(?:class|object|interface)\b")


def sort_key(line: str) -> tuple[list[str], int, str]:
    """Owner path, then the owner's own header line, its constructors, then members by text."""
    scope, _, decl = line.partition(": ")
    rank = 0 if CLASSIFIER_LINE_RE.match(decl) else 1 if decl.startswith(("constructor", "protected constructor")) else 2
    return (scope.split("."), rank, decl)


def sort_lines(lines: list[str]) -> list[str]:
    return sorted(lines, key=sort_key)


def read_snapshot(path: Path) -> list[str] | None:
    if not path.is_file():
        return None
    return [line for line in path.read_text(encoding="utf-8").splitlines() if line and not line.startswith("#")]


def write_snapshot(path: Path, surface: Surface, result: SurfaceResult) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    header = SNAPSHOT_HEADER.format(
        surface=surface.name,
        marker=MARKER,
        count=len(result.lines),
        files=result.files,
        what=surface.what,
        sources=", ".join(surface.sources),
    )
    path.write_text(header + "\n".join(result.lines) + "\n", encoding="utf-8")


def unreleased_markers(changelog: Path) -> tuple[list[tuple[int, str]], bool]:
    """The marker lines of `## Unreleased` as (line number, text), and whether the section exists.
    No section (or no CHANGELOG): no markers."""
    if not changelog.is_file():
        return [], False
    found: list[tuple[int, str]] = []
    inside = False
    seen = False
    for number, line in enumerate(changelog.read_text(encoding="utf-8").splitlines(), 1):
        if line.startswith("## "):
            inside = line.strip() == "## Unreleased"
            seen = seen or inside
            continue
        if inside and MARKER_RE.match(line):
            found.append((number, line.strip()))
    return found, seen


# --------------------------------------------------------------------------------------------------
# Comparison
# --------------------------------------------------------------------------------------------------


def member_key(line: str) -> str:
    """`<owner>: <kind> <name>` -- what a changed line keeps, so a removal and an addition pair up."""
    scope, _, decl = line.partition(": ")
    words = [w for w in re.split(r"[\s(:<]+", decl) if w]
    kinds = ("fun", "val", "var", "constructor", "class", "object", "interface", "typealias", "entry")
    for index, word in enumerate(words):
        if word in kinds:
            name = words[index + 1] if index + 1 < len(words) and word not in ("constructor",) else ""
            if word in ("val", "var"):
                word = "property"
            return f"{scope}: {word} {name}".rstrip()
    return line


def owners_of(line: str) -> list[str]:
    """The names an announcement may use for the owner of [line]: the outermost class or object of
    its owner path, or for a top-level declaration `top-level` and the receiver of an extension."""
    scope, _, decl = line.partition(": ")
    if scope == "<top-level>":
        names = ["top-level"]
        receiver = re.match(r"^(?:\w+ )*(?:fun|val|var) (?:<[^>]*> )?([A-Z]\w*)[\w.<>?, ]*\.\w+", decl)
        if receiver:
            names.append(receiver.group(1))
        return names
    parts = scope.split(".")
    outer = next((p for p in parts if p[:1].isupper()), scope)
    return [outer]


def announced(line: str, marker_text: str) -> bool:
    return any(re.search(rf"(?<![\w-]){re.escape(name)}(?![\w-])", marker_text) for name in owners_of(line))


DEFAULT = " = ..."


def _decl_parts(line: str) -> tuple[str, list[str], str, list[str], list[str]]:
    """(scope, modifiers, rest of the declaration, parameters, supertypes) of a snapshot line."""
    scope, _, decl = line.partition(": ")
    words = decl.split(" ")
    mods: list[str] = []
    while len(words) > 1 and words[0] in KEPT_MODIFIERS:
        if (words[0] == "fun" and words[1] != "interface") or (words[0] == "enum" and words[1] == "entry"):
            break  # the `fun` keyword itself, an enum entry
        mods.append(words.pop(0))
    rest = " ".join(words)
    params: list[str] = []
    supers: list[str] = []
    if CLASSIFIER_LINE_RE.match(rest) and " : " in rest:
        rest, _, tail = rest.partition(" : ")
        supers = split_top_level(tail)
    elif "(" in rest:
        start = rest.index("(")
        depth = 0
        for i in range(start, len(rest)):
            depth += {"(": 1, ")": -1}.get(rest[i], 0)
            if depth == 0:
                params = split_top_level(rest[start + 1 : i])
                rest = rest[:start] + "(" + ", ".join(p.removesuffix(DEFAULT) for p in params) + ")" + rest[i + 1 :]
                break
    return scope, mods, rest, params, supers


def compatible(old: str, new: str) -> bool:
    """Whether [new] only adds to [old] in a way no caller notices: a parameter gains a default, the
    declaration becomes `open`, a class gains a supertype. Removing any of them is a break."""
    o_scope, o_mods, o_rest, o_params, o_supers = _decl_parts(old)
    n_scope, n_mods, n_rest, n_params, n_supers = _decl_parts(new)
    if o_scope != n_scope or o_rest != n_rest or len(o_params) != len(n_params):
        return False
    if [m for m in o_mods if m != "open"] != [m for m in n_mods if m != "open"]:
        return False
    if "open" in o_mods and "open" not in n_mods:
        return False
    if any(o.endswith(DEFAULT) and not n.endswith(DEFAULT) for o, n in zip(o_params, n_params)):
        return False
    if not set(o_supers) <= set(n_supers):
        return False
    return old != new


def pair_compatible(removed: Counter, added: Counter) -> Counter:
    """The removed lines whose replacement among [added] is a source-compatible change."""
    by_key: dict[str, list[str]] = {}
    for line in added.elements():
        by_key.setdefault(member_key(line), []).append(line)
    result: Counter = Counter()
    for line in sorted(removed.elements()):
        candidates = by_key.get(member_key(line), [])
        for candidate in candidates:
            if compatible(line, candidate):
                candidates.remove(candidate)
                result[line] += 1
                break
    return result


def describe_changes(removed: Counter, added: Counter, compat: Counter, limit: int) -> list[str]:
    removed_by_key: dict[str, list[str]] = {}
    for line in sorted(removed.elements(), key=sort_key):
        removed_by_key.setdefault(member_key(line), []).append(line)
    added_by_key: dict[str, list[str]] = {}
    for line in sorted(added.elements(), key=sort_key):
        added_by_key.setdefault(member_key(line), []).append(line)
    out: list[str] = []
    for key in sorted(set(removed_by_key) | set(added_by_key)):
        was = removed_by_key.get(key, [])
        now = added_by_key.get(key, [])
        if was and now:
            label = "COMPATIBLE" if all(compat[line] for line in was) else "CHANGED"
            out.append(f"{label}  {key}")
            out += [f"           was: {line}" for line in was]
            out += [f"           now: {line}" for line in now]
        elif was:
            out += [f"REMOVED  {line}" for line in was]
        else:
            out += [f"ADDED    {line}" for line in now]
    if len(out) > limit:
        out = out[:limit] + [f"... and {len(out) - limit} more lines (run with --limit 0 for all)"]
    return out


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__.split("\n\n")[0])
    parser.add_argument("--root", type=Path, default=ROOT, help="repository root (a scratch copy for red runs)")
    parser.add_argument("--write", action="store_true", help="rewrite the snapshots (refuses an unannounced break)")
    parser.add_argument("--limit", type=int, default=60, help="max change lines to print (0 = all)")
    args = parser.parse_args()
    root = args.root.resolve()
    started = time.monotonic()
    limit = args.limit if args.limit > 0 else 10**9

    try:
        current = {surface.name: surface_lines(root, surface) for surface in SURFACES}
    except ParseError as error:
        print(f"{TAG} FAIL cannot read the surface: {error}", file=sys.stderr)
        return 1
    markers, has_unreleased = unreleased_markers(root / CHANGELOG)
    marker_text = "\n".join(text for _, text in markers)

    inferred = [line for result in current.values() for line in result.inferred]
    if inferred:
        print(f"{TAG} FAIL {len(inferred)} public declaration(s) without a type the gate can see; write the "
              "type out (`fun x(): Unit = ...`, `val y: Foo = ...`):", file=sys.stderr)
        for line in inferred[:limit]:
            print(f"    {line}", file=sys.stderr)
        return 1

    breaks: Counter = Counter()
    unannounced: Counter = Counter()
    added_total = 0
    compatible_total = 0
    report: list[str] = []
    missing: list[str] = []
    for surface in SURFACES:
        lines = current[surface.name].lines
        snapshot = read_snapshot(root / surface.snapshot)
        if snapshot is None:
            missing.append(surface.snapshot)
            continue
        removed = Counter(snapshot) - Counter(lines)
        added = Counter(lines) - Counter(snapshot)
        compat = pair_compatible(removed, added)
        surface_breaks = removed - compat
        surface_unannounced = Counter({line: n for line, n in surface_breaks.items() if not announced(line, marker_text)})
        if removed or added:
            report.append(
                f"  {surface.name} ({surface.snapshot}): {sum(surface_breaks.values())} removed/changed "
                f"({sum(surface_unannounced.values())} unannounced), {sum(compat.values())} compatible change(s), "
                f"{sum(added.values()) - sum(compat.values())} added"
            )
            report += [f"    {line}" for line in describe_changes(removed, added, compat, limit)]
        breaks += surface_breaks
        unannounced += surface_unannounced
        added_total += sum(added.values()) - sum(compat.values())
        compatible_total += sum(compat.values())

    counts = ", ".join(f"{name} {len(result.lines)}" for name, result in current.items())
    elapsed = f"{time.monotonic() - started:.1f}s"
    announced_by = "; ".join(f"{CHANGELOG}:{n}: {text[:100]}" for n, text in markers)
    unannounced_owners = sorted({owners_of(line)[0] for line in unannounced.elements()})
    unannounced_message = (
        f"{sum(unannounced.values())} public signature(s) removed or changed and not announced: no "
        f"`{MARKER}` line in {CHANGELOG} `## Unreleased` names {', '.join(unannounced_owners)}"
        + (
            f" (marker lines found: {announced_by})"
            if markers
            else " (no marker lines found)"
            if has_unreleased
            else f" ({CHANGELOG} has no `## Unreleased` section: keep one, even an empty one)"
        )
    )

    if args.write:
        if unannounced:
            print(f"{TAG} FAIL refusing to record {unannounced_message}.", file=sys.stderr)
            print("\n".join(report), file=sys.stderr)
            return 1
        for surface in SURFACES:
            write_snapshot(root / surface.snapshot, surface, current[surface.name])
        print(f"{TAG} wrote the snapshots (declarations: {counts}; {elapsed})"
              + (f"; breaks announced by {announced_by}" if breaks else ""))
        return 0

    if missing:
        print(f"{TAG} FAIL no snapshot at {', '.join(missing)}; create it with "
              "`python3 scripts/check_public_signature_changes.py --write`", file=sys.stderr)
        return 1

    if not breaks and not added_total and not compatible_total:
        print(f"{TAG} PASS the public API surface matches the snapshots (declarations: {counts}; {elapsed})")
        return 0

    if unannounced:
        print(f"{TAG} FAIL unannounced source break ({elapsed}): {unannounced_message}:", file=sys.stderr)
        print("\n".join(report), file=sys.stderr)
        print(f"{TAG} If the break is intended, announce it in {CHANGELOG} under `## Unreleased` with a line "
              f"starting `{MARKER}` that names each owner above (what changed and how to migrate), then "
              "record it: `python3 scripts/check_public_signature_changes.py --write`. If it is not intended, "
              "fix the generator (or the hand-written file) instead.", file=sys.stderr)
        return 1

    if breaks:
        print(f"{TAG} FAIL the snapshots are out of date: {sum(breaks.values())} removed/changed signature(s), "
              f"announced by {announced_by}; {compatible_total} compatible change(s), {added_total} added. "
              "Regenerate the snapshots and commit them with the change: "
              "`python3 scripts/check_public_signature_changes.py --write`", file=sys.stderr)
    else:
        print(f"{TAG} FAIL the snapshots are out of date: {added_total} signature(s) added, {compatible_total} "
              "source-compatible change(s) (a default added, made `open`, a supertype added); neither is a "
              "break, no CHANGELOG line needed. Record them: "
              "`python3 scripts/check_public_signature_changes.py --write`", file=sys.stderr)
    print("\n".join(report), file=sys.stderr)
    return 1


if __name__ == "__main__":
    raise SystemExit(main())
