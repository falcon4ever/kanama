#!/usr/bin/env python3
"""Gate: the public signatures of the Godot API surface change only when CHANGELOG.md says so (task 126).

Task 117 changed public signatures in four parcels (nullable fluent setters, a nullable `getTree`,
twelve renamed properties, `Long` -> `Int` on desktop) and task 128 retyped every enum. Each was
deliberate and announced, but nothing would have noticed an UNANNOUNCED one: a generator change that
retypes one parameter compiles, regenerates the whole tree, and passes the drift gate, because the
drift gate only proves that the committed tree is what the generator writes. This gate holds the
surface itself to a checked-in snapshot, one file per surface under `docs/reference/generated/`
(`SURFACES` below):

  * `public-api-signatures-common.txt` -- the API tree all native backends compile
    (`src/commonMain/.../api`: the generated classes, `GlobalEnums.kt`, the hand roots
    `GodotObject` / `RefCounted` / `GodotCallable` / `GodotHandle`, the `*.expect.kt` declarations);
  * `public-api-signatures-types.txt` -- the builtin value types (`src/commonMain/.../types`);
  * `public-api-signatures-jvm.txt` / `-ios.txt` -- each platform's own api classes
    (`src/jvmMain/.../api`, `src/iosMain/.../api`: per-platform wrappers, `<Class>.jvm.kt` /
    `<Class>.ios.kt` companions, the actuals, GD, the desktop name constants);
  * `public-api-signatures-web.txt` -- the Web wrappers, generated (`web-runtime/.../api/generated`)
    and the hand facades beside them (`web-runtime/.../api/*.kt`).

One line per declaration: `<owner>: <declaration>`, sorted by owner then declaration, for example
`Node: fun addChild(node: Node, forceReadableName: Boolean = ..., internalValue: Node.InternalMode = ...)`.
The snapshot is derived from the Kotlin SOURCES (no Gradle build, no compiled metadata), by a parser
written for the generator's regular output that also reads the hand roots: comments and string
contents are dropped, brackets are matched, and every `fun` / `val` / `var` / `constructor` /
`class` / `object` / `interface` / `typealias` at a class-body level is a declaration. Recorded per
declaration: kind, name, extension receiver, type parameters, parameter names and types (a parameter
name is source API: Kotlin callers may name arguments), whether a parameter HAS a default (its value
is behaviour, not source: shown as `= ...`), the return / property type, supertypes, and the source
modifiers (`open`, `abstract`, `override`, `const`, `operator`, `infix`, `vararg`, `data`, `value`,
`companion`, ...). Not recorded: annotations (`@JvmStatic`, `@JvmName` are JVM interop, not Kotlin
source), constant values, bodies. `private` / `internal` declarations and everything inside them are
skipped; a `var` with a non-public setter is recorded as a `val`. A construct the parser does not
understand is an error naming file:line, never a silently skipped declaration.

A declaration without a written type is recorded as `<inferred>` unless its type is certain from the
source: `= Unit`, a literal (`= 0L`), a constructor call of its own class on the companion
(`val ZERO = Vector3(...)`), or a forwarding extension whose body calls the same-named member with the
same parameters (the Web import-compatibility aliases, `resolve_forwarders`). Every run prints how
many `<inferred>` lines remain; their type changes are not seen. A line that was `<inferred>` and now
states its type is not a break (the type did not change; the gate sees it now): it needs only
`--write`.

The gate fails whenever the surface differs from the snapshot; the message says what to do:

  * a signature REMOVED or CHANGED and no announcement -> FAIL, naming each one (`was` / `now`).
    A change is a source break: announce it with a line in `CHANGELOG.md`'s `## Unreleased` section
    that starts exactly with the marker `- **Source break:**`, then record it with `--write`;
  * a removal/change that IS announced -> FAIL asking for `--write` (the snapshot diff is then part
    of the change under review);
  * only ADDITIONS -> FAIL asking for `--write` (no CHANGELOG line needed: an addition breaks no
    caller, but an unrecorded one would hide its own later removal).

`--write` rewrites the snapshot and refuses to record a removal/change the CHANGELOG does not
announce.

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
from dataclasses import dataclass
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
TAG = "[public_signatures]"
API_PACKAGE = "net.multigesture.kanama.api"
CHANGELOG = "CHANGELOG.md"
# The exact announcement marker: a bullet in `## Unreleased` starting with these characters.
MARKER = "- **Source break:**"
MARKER_RE = re.compile(r"^- \*\*Source break:\*\*\s+\S")
SNAPSHOT_HEADER = (
    "# Public API signatures: {surface} -- GENERATED by scripts/check_public_signature_changes.py --write.\n"
    "# Do not edit by hand. A removed or changed line is a source break and needs a `{marker}` line in\n"
    "# CHANGELOG.md `## Unreleased` (see docs/reference/wrapper-conventions.md, \"Source breaks\").\n"
    "# {count} declarations from {files} files: {what}.\n"
    "# Sources: {sources}\n"
)


@dataclass(frozen=True)
class Surface:
    name: str
    sources: tuple[str, ...]  # directories relative to the root; `*.kt` directly inside each
    snapshot: str  # relative to the root
    what: str  # one line for the snapshot header
    package: str = API_PACKAGE  # declarations in this package are named without it


GENERATED = "docs/reference/generated"
SURFACES = (
    Surface(
        "common",
        ("src/commonMain/kotlin/net/multigesture/kanama/api",),
        f"{GENERATED}/public-api-signatures-common.txt",
        "the API tree desktop, Android and iOS compile from one source (generated classes, "
        "GlobalEnums, the hand roots, the expect declarations)",
    ),
    Surface(
        "types",
        ("src/commonMain/kotlin/net/multigesture/kanama/types",),
        f"{GENERATED}/public-api-signatures-types.txt",
        "the builtin value types every native backend shares (Vector2/3/4, Color, Basis, Transform3D, ...)",
        "net.multigesture.kanama.types",
    ),
    Surface(
        "jvm",
        ("src/jvmMain/kotlin/net/multigesture/kanama/api",),
        f"{GENERATED}/public-api-signatures-jvm.txt",
        "desktop/Android only: the per-platform classes, the <Class>.jvm.kt companions, the actuals, "
        "GD/Mathf and the name constants",
    ),
    Surface(
        "ios",
        ("src/iosMain/kotlin/net/multigesture/kanama/api",),
        f"{GENERATED}/public-api-signatures-ios.txt",
        "iOS only: the per-platform classes, the <Class>.ios.kt companions and the actuals",
    ),
    Surface(
        "web",
        (
            "web-runtime/src/commonMain/kotlin/net/multigesture/kanama/api/generated",
            "web-runtime/src/commonMain/kotlin/net/multigesture/kanama/api",
        ),
        f"{GENERATED}/public-api-signatures-web.txt",
        "the Web wrappers: the generated classes and the hand-written facades beside them",
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
ANNOTATION_RE = re.compile(r"@[\w.:]+(?:\s*\([^()]*\))?")
VISIBILITY = {"public", "private", "internal", "protected"}
HIDDEN = {"private", "internal"}
# Kept in the rendered line, in this order; `public`, `final`, `expect`, `actual` are dropped
# (they change nothing a caller writes).
KEPT_MODIFIERS = (
    "protected",
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
MODIFIERS = set(KEPT_MODIFIERS) | VISIBILITY | {"final", "expect", "actual", "noinline", "crossinline"}
TYPE_STOP_WORDS = ("get", "set", "by", "where")
PROPERTY_NAME_RE = re.compile(r"[^:=\n;{]*")
INFERRED = "<inferred>"


class Source:
    def __init__(self, path: Path, rel: str, text: str):
        self.path = path
        self.rel = rel
        self.text = text
        self.positions: list[int] = []
        self.depths: list[int] = []
        self.closers: dict[int, int] = {}
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
    # For a `fun` whose return type is not written: the function its expression body calls, when
    # the body is a plain call (`= play(name, customSpeed)`); see resolve_forwarders.
    forward: str | None = None


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
            elif ch in "{};=\n" and depth > 0 and ch != "\n":
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

    def line_modifiers(self, pos: int, floor: int) -> list[str]:
        """Modifiers written on the keyword's own line, before it. Anything else there is an error."""
        line_start = self.text.rfind("\n", floor, pos) + 1
        line_start = max(line_start, floor)
        prefix = ANNOTATION_RE.sub(" ", self.text[line_start:pos])
        words = prefix.split()
        for word in words:
            if word not in MODIFIERS:
                raise ParseError(
                    f"{self.src.where(pos)}: unexpected {word!r} before a declaration keyword on the same line"
                )
        return words

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
            mods = self.line_modifiers(pos, start)
            keyword_positions.append(pos)
            hidden = any(m in HIDDEN for m in mods)
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

    def emit(self, scope: str, text: str, pos: int) -> None:
        self.decls.append(Decl(scope, text, pos))

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
        forward = None
        if not ret and text.startswith("=", k) and not text.startswith("==", k):
            ret = "Unit" if re.compile(r"=\s*Unit\s*(?:\n|;|$)").match(text, k) else INFERRED
            call = re.compile(r"\s*(\w+)\s*\(").match(text, k + 1)
            forward = call.group(1) if call and ret == INFERRED else None
        if not hidden:
            rendered = " ".join([*self.kept(mods), f"fun {type_params}{name}{params}"])
            if ret and ret != "Unit":
                rendered += f": {ret}"
            self.emit(scope, rendered, pos)
            self.decls[-1].forward = forward
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
        ctor = re.compile(r"((?:@[\w.:]+(?:\s*\([^()]*\))?\s+|(?:public|private|internal|protected)\s+)*)(constructor\s*)?\(").match(text, i)
        if ctor:
            open_paren = ctor.end() - 1
            close = src.closers[open_paren]
            ctor_mods = ANNOTATION_RE.sub(" ", ctor.group(1)).split()
            raw = text[open_paren + 1 : close]
            if not any(m in HIDDEN for m in ctor_mods):
                ctor_line = " ".join([*self.kept(ctor_mods), "constructor" + render_params(raw, strip_property=True)])
            ctor_props = []
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
        else:
            ctor_props = []
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


def resolve_forwarders(decls: list[Decl]) -> None:
    """Give a forwarding extension the return type of the member it calls.

    The Web wrappers carry import-compatibility extensions next to each member,
    `fun AnimatedSprite2D.play(name: String = "") = play(name)`, whose return type Kotlin infers from
    the member. When an extension's body is a call to the member of the same name with the same
    parameter list, on its receiver class or a supertype in the same surface, the line takes that
    member's return type. Anything else keeps `<inferred>`, which the gate counts and prints."""
    members: dict[str, dict[str, str]] = {}
    supertypes: dict[str, list[str]] = {}
    for decl in decls:
        if not decl.scope:
            continue
        if decl.text.startswith(("fun ", "suspend fun ", "override fun ", "open fun ")) and INFERRED not in decl.text:
            key = re.sub(r"^.*?\bfun ", "fun ", decl.text)
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
                break
            queue += supertypes.get(cls, [])


def surface_files(root: Path, surface: Surface) -> list[Path]:
    files: list[Path] = []
    for source in surface.sources:
        found = sorted((root / source).glob("*.kt"))
        if not found:
            raise ParseError(f"{source}: no Kotlin sources (wrong --root, or the tree moved)")
        files += found
    return files


def surface_lines(root: Path, surface: Surface) -> tuple[list[str], int, int]:
    """(sorted lines, file count, `<inferred>` lines left after forwarding resolution)."""
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
    inferred = sum(1 for line in lines if INFERRED in line)
    return sort_lines(lines), len(files), inferred


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


def write_snapshot(path: Path, surface: Surface, lines: list[str], files: int) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    header = SNAPSHOT_HEADER.format(
        surface=surface.name, marker=MARKER, count=len(lines), files=files, what=surface.what,
        sources=", ".join(surface.sources)
    )
    path.write_text(header + "\n".join(lines) + "\n", encoding="utf-8")


def unreleased_markers(changelog: Path) -> list[tuple[int, str]]:
    if not changelog.is_file():
        raise ParseError(f"{changelog}: missing")
    found: list[tuple[int, str]] = []
    inside = False
    seen_unreleased = False
    for number, line in enumerate(changelog.read_text(encoding="utf-8").splitlines(), 1):
        if line.startswith("## "):
            inside = line.strip() == "## Unreleased"
            seen_unreleased = seen_unreleased or inside
            continue
        if inside and MARKER_RE.match(line):
            found.append((number, line.strip()))
    if not seen_unreleased:
        raise ParseError(f"{changelog}: no `## Unreleased` section; the gate cannot tell whether a break is announced")
    return found


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


def describe_changes(removed: Counter, added: Counter, limit: int) -> list[str]:
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
            out.append(f"CHANGED  {key}")
            out += [f"           was: {line}" for line in was]
            out += [f"           now: {line}" for line in now]
        elif was:
            out += [f"REMOVED  {line}" for line in was]
        else:
            out += [f"ADDED    {line}" for line in now]
    if len(out) > limit:
        out = out[:limit] + [f"... and {len(out) - limit} more lines (run with --limit 0 for all)"]
    return out


def refines(removed_line: str, added: Counter) -> bool:
    """Whether [removed_line] ends in `<inferred>` and an added line is the same declaration with its
    type written out (`: X`, or nothing for `Unit`)."""
    suffix = f": {INFERRED}"
    if not removed_line.endswith(suffix):
        return False
    prefix = removed_line[: -len(suffix)]
    return any(line == prefix or line.startswith(prefix + ": ") for line in added)


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__.split("\n\n")[0])
    parser.add_argument("--root", type=Path, default=ROOT, help="repository root (a scratch copy for red runs)")
    parser.add_argument("--write", action="store_true", help="rewrite the snapshot (refuses an unannounced break)")
    parser.add_argument("--limit", type=int, default=60, help="max change lines to print (0 = all)")
    args = parser.parse_args()
    root = args.root.resolve()
    started = time.monotonic()
    limit = args.limit if args.limit > 0 else 10**9

    try:
        current = {surface.name: surface_lines(root, surface) for surface in SURFACES}
        markers = unreleased_markers(root / CHANGELOG)
    except ParseError as error:
        print(f"{TAG} FAIL cannot read the surface: {error}", file=sys.stderr)
        return 1

    removed_total: Counter = Counter()
    added_total: Counter = Counter()
    refined_total: Counter = Counter()
    report: list[str] = []
    missing: list[str] = []
    for surface in SURFACES:
        lines, _, _ = current[surface.name]
        snapshot = read_snapshot(root / surface.snapshot)
        if snapshot is None:
            missing.append(surface.snapshot)
            continue
        removed = Counter(snapshot) - Counter(lines)
        added = Counter(lines) - Counter(snapshot)
        # A line that was `<inferred>` and now states its type is not a break: the type did not
        # change, the gate just sees it now. It still needs --write, like an addition.
        refined = Counter({line: n for line, n in removed.items() if refines(line, added)})
        if removed or added:
            report.append(f"  {surface.name} ({surface.snapshot}): {sum((removed - refined).values())} "
                          f"removed/changed, {sum(refined.values())} inferred type(s) now written, "
                          f"{sum(added.values())} added")
            report += [f"    {line}" for line in describe_changes(removed, added, limit)]
        removed_total += removed - refined
        added_total += added
        refined_total += refined

    counts = ", ".join(f"{name} {len(lines)}" for name, (lines, _, _) in current.items())
    inferred = sum(n for _, _, n in current.values())
    if inferred:
        counts += (f"; {inferred} recorded as {INFERRED}, whose inferred type the gate cannot see")
    elapsed = f"{time.monotonic() - started:.1f}s"
    announced = "; ".join(f"{CHANGELOG}:{n}: {text[:100]}" for n, text in markers)

    if args.write:
        if removed_total and not markers:
            print(f"{TAG} FAIL refusing to record {sum(removed_total.values())} removed/changed signature(s): "
                  f"{CHANGELOG} `## Unreleased` has no `{MARKER}` line.", file=sys.stderr)
            print("\n".join(report), file=sys.stderr)
            return 1
        for surface in SURFACES:
            lines, files, _ = current[surface.name]
            write_snapshot(root / surface.snapshot, surface, lines, files)
        print(f"{TAG} wrote the snapshot (declarations: {counts}; {elapsed})"
              + (f"; break announced by {announced}" if removed_total else ""))
        return 0

    if missing:
        print(f"{TAG} FAIL no snapshot at {', '.join(missing)}; create it with "
              "`python3 scripts/check_public_signature_changes.py --write`", file=sys.stderr)
        return 1

    if not removed_total and not added_total and not refined_total:
        print(f"{TAG} PASS the public API surface matches the snapshot (declarations: {counts}; {elapsed})")
        return 0

    if removed_total and not markers:
        print(f"{TAG} FAIL unannounced source break: {sum(removed_total.values())} public signature(s) "
              f"removed or changed ({elapsed}):", file=sys.stderr)
        print("\n".join(report), file=sys.stderr)
        print(f"{TAG} If the break is intended, announce it in {CHANGELOG} under `## Unreleased` with a line "
              f"starting `{MARKER}` (what changed and how to migrate), then record it: "
              "`python3 scripts/check_public_signature_changes.py --write`. If it is not intended, fix the "
              "generator (or the hand root) instead.", file=sys.stderr)
        return 1

    if removed_total:
        print(f"{TAG} FAIL the snapshot is out of date: {sum(removed_total.values())} removed/changed and "
              f"{sum(added_total.values())} added signature(s); the break is announced ({announced}). "
              "Regenerate the snapshot and commit it with the change: "
              "`python3 scripts/check_public_signature_changes.py --write`", file=sys.stderr)
    else:
        print(f"{TAG} FAIL the snapshot is out of date: {sum(added_total.values()) - sum(refined_total.values())} "
              f"signature(s) added, {sum(refined_total.values())} inferred type(s) now written (neither is a break; "
              "no CHANGELOG line needed). Record them: "
              "`python3 scripts/check_public_signature_changes.py --write`", file=sys.stderr)
    print("\n".join(report), file=sys.stderr)
    return 1


if __name__ == "__main__":
    raise SystemExit(main())
