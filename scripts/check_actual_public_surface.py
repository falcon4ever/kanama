#!/usr/bin/env python3
"""Gate: an `actual` declares no public member its `expect` does not (task 117 D1, P4′ review).

The Kotlin compiler forces every `expect` member to have an `actual` on desktop (`src/jvmMain`)
and on iOS (`src/iosMain`), but it lets an `actual class` / `actual object` carry EXTRA public
members silently. Such a member is public API on one platform only: a desktop user script that
calls it does not compile for iOS. This is the one-sided-member finding of decision D1 that the
retired `check_wrapper_parity.py` made (it diffed the two platform copies), kept now that the
copies are `expect`/`actual`.

For every top-level `expect` declaration in a `src/commonMain/**/*.expect.kt` file the gate finds
its `actual` on both platforms (same package, same name) and reads the actual's members: its body
declarations (`fun`, `val`/`var`, secondary `constructor`, nested `class`/`object`/`interface`,
a companion), its primary constructor and the `val`/`var` parameters of that constructor. A member
FAILS when it is declared public -- no `private`, `internal` or `protected` modifier; the declared
visibility counts, an `internal` container does not excuse it -- and it is neither marked `actual`
nor has a name + arity the `expect` also declares (an `override` of an expect member is `actual
override`). Make it non-public, or add it to the `expect` so both platforms must have it.

The scan is textual (comments and string literals blanked, bracket tracking) and fails loudly:
an `expect` with no `actual` file on a platform, two actuals, an `expect`/`actual` keyword the
parser did not turn into a declaration, or an actual whose `actual`-marked members do not pair
one-to-one with the expect's, is "could not parse", never a skip.

`ObjectCalls` is excluded: its `expect` is generated as the helper set the shared tree calls, and
each backend legitimately keeps platform-only helpers next to it (the expect file says so);
`scripts/check_objectcalls_parity.py` owns that object.

    python3 scripts/check_actual_public_surface.py                     # the module
    python3 scripts/check_actual_public_surface.py --root /tmp/scratch  # (red runs) another checkout
"""

from __future__ import annotations

import argparse
import re
import sys
from dataclasses import dataclass, field
from pathlib import Path

from generate_api_wrapper import strip_noise

ROOT = Path(__file__).resolve().parents[1]
TAG = "[actual_public_surface]"
EXPECT_SUFFIX = ".expect.kt"
PLATFORM_ROOTS = (("desktop", "src/jvmMain"), ("iOS", "src/iosMain"))

# Expect declarations the gate does not compare, with the reason (printed on every run).
EXCLUDED = {
    "ObjectCalls": "generated helper seam; each backend keeps platform-only helpers beside it "
    "(see the expect file) -- scripts/check_objectcalls_parity.py owns it",
}
# Expect classifiers whose actual is a `typealias` to an existing type: the aliased type's
# surface is not something the actual declares, so there is nothing to compare.
TYPEALIAS_ACTUALS = {
    "RawSegment": "typealias to the platform pointer type (JDK MemorySegment / the iOS shim of it); "
    "address() is the whole common contract",
}

KEYWORD_RE = re.compile(r"\b(fun|val|var|constructor|class|object|interface|typealias)\b")
MODIFIERS = {
    "public", "private", "internal", "protected", "expect", "actual", "override", "open", "final",
    "abstract", "sealed", "data", "enum", "inner", "value", "annotation", "companion", "inline",
    "suspend", "operator", "infix", "tailrec", "external", "const", "lateinit", "fun",
}
HIDDEN = {"private", "internal", "protected"}
CLASSIFIERS = {"class", "object", "interface"}
PROPERTY = "property"
TYPE = "type"


class ParseError(Exception):
    pass


@dataclass
class Decl:
    kind: str  # fun | val | var | constructor | class | object | interface | typealias
    name: str
    arity: int | str  # parameter count, PROPERTY or TYPE
    mods: list[str]
    pos: int
    body: tuple[int, int] | None = None  # offsets of `{` and `}`
    members: list["Decl"] = field(default_factory=list)

    @property
    def key(self) -> tuple[str, int | str]:
        return (self.name, self.arity)

    def describe(self) -> str:
        mods = " ".join(m for m in self.mods if not m.startswith("@"))
        if self.arity == PROPERTY:
            shape = f"{self.kind} {self.name}"
        elif self.arity == TYPE:
            shape = f"{self.kind} {self.name}"
        else:
            shape = f"{self.kind} {self.name}/{self.arity}" if self.kind != "constructor" else f"constructor/{self.arity}"
        return f"{mods} {shape}".strip()


@dataclass
class Source:
    path: Path
    text: str  # comments and string/char literals blanked; offsets and newlines kept
    depth: list[int]  # bracket depth before each offset

    def line(self, pos: int) -> int:
        return self.text.count("\n", 0, pos) + 1

    def where(self, pos: int, root: Path) -> str:
        try:
            shown = self.path.relative_to(root)
        except ValueError:
            shown = self.path
        return f"{shown}:{self.line(pos)}"


def load(path: Path) -> Source:
    text = strip_noise(path.read_text(encoding="utf-8"))
    depth = [0] * (len(text) + 1)
    d = 0
    for i, ch in enumerate(text):
        depth[i] = d
        if ch in "([{":
            d += 1
        elif ch in ")]}":
            d -= 1
    depth[len(text)] = d
    if d != 0:
        raise ParseError(f"{path}: unbalanced brackets (depth {d} at end of file)")
    return Source(path, text, depth)


def closer(text: str, start: int) -> int:
    """Index of the bracket closing the opener at [start]; literals are already blanked."""
    pairs = {"(": ")", "[": "]", "{": "}"}
    opener, close = text[start], pairs[text[start]]
    depth = 0
    for i in range(start, len(text)):
        if text[i] == opener:
            depth += 1
        elif text[i] == close:
            depth -= 1
            if depth == 0:
                return i
    raise ParseError(f"unbalanced '{opener}' at offset {start}")


def skip_angles(text: str, i: int) -> int:
    """[i] is at `<`: the index just past its balanced `>` (`->` never closes)."""
    depth = 0
    while i < len(text):
        ch = text[i]
        if ch == "<":
            depth += 1
        elif ch == ">" and text[i - 1] != "-":
            depth -= 1
            if depth == 0:
                return i + 1
        elif ch in "{};":
            break
        i += 1
    raise ParseError(f"unbalanced '<' at offset {i}")


def skip_ws(text: str, i: int) -> int:
    while i < len(text) and text[i].isspace():
        i += 1
    return i


def split_top_level(params: str) -> list[str]:
    out: list[str] = []
    depth = 0
    current = ""
    for i, ch in enumerate(params):
        if ch in "(<[{":
            depth += 1
        elif ch in ")]}" or (ch == ">" and params[i - 1 : i] != "-"):
            depth -= 1
        elif ch == "," and depth == 0:
            out.append(current)
            current = ""
            continue
        current += ch
    out.append(current)
    return [p.strip() for p in out if p.strip()]


def modifiers_before(text: str, pos: int, floor: int) -> list[str]:
    """Modifier keywords and annotations directly before the keyword at [pos]."""
    mods: list[str] = []
    i = pos
    while True:
        j = i
        while j > floor and text[j - 1].isspace():
            j -= 1
        if j <= floor:
            break
        if text[j - 1] == ")":  # annotation arguments: `@JvmName(...)`
            depth, k = 0, j - 1
            while k >= floor:
                if text[k] == ")":
                    depth += 1
                elif text[k] == "(":
                    depth -= 1
                    if depth == 0:
                        break
                k -= 1
            match = re.search(r"@[\w.:]+\s*$", text[floor:k])
            if not match:
                break
            mods.append(text[floor + match.start() : k].strip())
            i = floor + match.start()
            continue
        match = re.search(r"@?[\w.:]+$", text[max(floor, j - 200) : j])
        if not match:
            break
        word = match.group(0)
        if word.startswith("@") or word in MODIFIERS:
            mods.append(word)
            i = j - len(word)
            continue
        break
    return mods


def parse_params(text: str, open_paren: int) -> tuple[list[str], int]:
    close = closer(text, open_paren)
    return split_top_level(text[open_paren + 1 : close]), close


def class_header(src: Source, decl: Decl, after_name: int, end: int) -> tuple[int, list[Decl]]:
    """Primary constructor (as members) and the body; returns the offset the header scan ended at."""
    text = src.text
    i = skip_ws(text, after_name)
    if i < end and text[i] == "<":
        i = skip_angles(text, i)
    members: list[Decl] = []
    ctor = re.compile(
        r"\s*((?:@[\w.:]+\s+|(?:public|private|internal|protected|actual|expect)\s+)*)(constructor\s*)?\("
    ).match(text, i)
    if ctor:
        open_paren = ctor.end() - 1
        params, close = parse_params(text, open_paren)
        ctor_mods = ctor.group(1).split()
        members.append(Decl("constructor", "constructor", len(params), ctor_mods, open_paren))
        offset = open_paren + 1
        for param in split_top_level(text[open_paren + 1 : close]):
            at = text.index(param, offset)
            offset = at + len(param)
            prop = re.match(r"((?:@[\w.:]+(?:\([^)]*\))?\s+|\w+\s+)*?)(val|var)\s+`?(\w+)`?", param)
            if prop:
                mods = [m for m in prop.group(1).split() if m]
                members.append(Decl(prop.group(2), prop.group(3), PROPERTY, mods, at + prop.start(2)))
        i = close + 1
    # The body: the first `{` at this nesting level before the next declaration keyword.
    base = src.depth[decl.pos]
    j = i
    while j < end:
        ch = text[j]
        if src.depth[j] == base:
            if ch == "{":
                body_close = closer(text, j)
                decl.body = (j, body_close)
                decl.members = members + parse_decls(src, j + 1, body_close)
                return body_close + 1, decl.members
            if ch == "}" or ch == ";":
                break
            if KEYWORD_RE.match(text, j) and (j == 0 or not (text[j - 1].isalnum() or text[j - 1] == "_")):
                break
        j += 1
    decl.members = members
    return j, members


def parse_decls(src: Source, start: int, end: int) -> list[Decl]:
    """Declarations directly inside [start, end) (top level of a file, or one class body)."""
    text = src.text
    base = src.depth[start]
    decls: list[Decl] = []
    resume = start
    for match in KEYWORD_RE.finditer(text, start, end):
        pos = match.start()
        if pos < resume or src.depth[pos] != base:
            continue
        kind = match.group(1)
        prev = text[max(start, pos - 2) : pos]
        if prev.endswith(".") or prev == "::":
            continue  # `Foo::class`, a qualified name
        after = skip_ws(text, match.end())
        mods = modifiers_before(text, pos, start)
        if kind == "fun" and text.startswith("interface", after):
            continue  # `fun interface`: the `interface` keyword carries it
        if kind == "fun" and text[after] == "(":
            continue  # an anonymous function expression
        if kind == "fun":
            i = after
            if text[i] == "<":
                i = skip_angles(text, i)
            open_paren = i
            depth = 0
            while open_paren < end:
                ch = text[open_paren]
                if ch == "<":
                    depth += 1
                elif ch == ">" and text[open_paren - 1] != "-":
                    depth -= 1
                elif ch == "(" and depth == 0:
                    break
                elif ch in "{};=\n" and depth == 0:
                    raise ParseError(f"{src.where(pos, ROOT)}: no parameter list after `fun`")
                open_paren += 1
            name_match = re.search(r"`?(\w+)`?\s*$", text[i:open_paren])
            if not name_match:
                raise ParseError(f"{src.where(pos, ROOT)}: no function name")
            params, close = parse_params(text, open_paren)
            decls.append(Decl("fun", name_match.group(1), len(params), mods, pos))
            resume = close + 1
        elif kind in ("val", "var"):
            i = after
            if text[i] == "<":
                i = skip_angles(text, i)
            j, depth = i, 0
            while j < end:
                ch = text[j]
                if ch == "<":
                    depth += 1
                elif ch == ">" and text[j - 1] != "-":
                    depth -= 1
                elif depth == 0 and (ch in ":=;\n{" or text.startswith(" by ", j)):
                    break
                j += 1
            name_match = re.search(r"`?(\w+)`?\s*$", text[i:j])
            if not name_match:
                raise ParseError(f"{src.where(pos, ROOT)}: no property name after `{kind}`")
            decls.append(Decl(kind, name_match.group(1), PROPERTY, mods, pos))
            resume = j
        elif kind == "constructor":
            if text[after] != "(":
                raise ParseError(f"{src.where(pos, ROOT)}: `constructor` without a parameter list")
            params, close = parse_params(text, after)
            decls.append(Decl("constructor", "constructor", len(params), mods, pos))
            resume = close + 1
        elif kind == "typealias":
            name_match = re.compile(r"`?(\w+)`?").match(text, after)
            if not name_match:
                raise ParseError(f"{src.where(pos, ROOT)}: no typealias name")
            decls.append(Decl("typealias", name_match.group(1), TYPE, mods, pos))
            resume = name_match.end()
        else:  # class / object / interface
            name_match = re.compile(r"`?(\w+)`?").match(text, after)
            if kind == "object" and not name_match:
                if "companion" not in mods:
                    continue  # an object expression: `object : Foo { ... }`
                name, after_name = "Companion", after
            elif not name_match:
                raise ParseError(f"{src.where(pos, ROOT)}: no name after `{kind}`")
            else:
                name, after_name = name_match.group(1), name_match.end()
            decl = Decl(kind, name, TYPE, mods, pos)
            resume, _ = class_header(src, decl, after_name, end)
            decls.append(decl)
    return decls


def package_of(src: Source) -> str:
    match = re.search(r"^\s*package\s+([\w.]+)", src.text, re.MULTILINE)
    return match.group(1) if match else ""


def count_keyword(src: Source, word: str, start: int, end: int, header_ctor: bool = True) -> int:
    """`word` tokens at the nesting level of [start]; `header_ctor=False` leaves out a class header's
    `actual constructor(` (it is a member of that class, not a top-level declaration)."""
    base = src.depth[start]
    pattern = rf"\b{word}\b" if header_ctor else rf"\b{word}\b(?!\s+constructor\b)"
    return sum(1 for m in re.finditer(pattern, src.text[start:end]) if src.depth[start + m.start()] == base)


def implicit_constructor(decl: Decl) -> list[Decl]:
    """A class with no declared constructor has a public no-argument one."""
    if decl.kind != "class" or any(m.kind == "constructor" for m in decl.members):
        return []
    if any(m in ("sealed", "abstract", "enum", "annotation") for m in decl.mods):
        return []
    return [Decl("constructor", "constructor", 0, [], decl.pos)]


def compare(expect: Decl, actual: Decl, src: Source, root: Path, platform: str) -> tuple[list[str], list[str]]:
    """(findings, parse errors) for one actual against its expect."""
    findings: list[str] = []
    errors: list[str] = []
    expected = [m.key for m in expect.members]
    marked = [m for m in actual.members if "actual" in m.mods]
    if sorted(map(str, expected)) != sorted(map(str, (m.key for m in marked))):
        errors.append(
            f"{src.where(actual.pos, root)}: could not parse {platform} `actual {actual.kind} {actual.name}`: "
            f"{len(marked)} `actual` member(s) do not pair with the expect's {len(expected)} "
            f"(expect {sorted(map(str, expected))}, actual {sorted(map(str, (m.key for m in marked)))})"
        )
    body = actual.body or (actual.pos, actual.pos)
    in_body = count_keyword(src, "actual", body[0] + 1, body[1]) if actual.body else 0
    parsed_in_body = sum(1 for m in marked if actual.body and body[0] < m.pos < body[1])
    if in_body != parsed_in_body:
        errors.append(
            f"{src.where(actual.pos, root)}: could not parse {platform} `{actual.name}`: {in_body} `actual` "
            f"keyword(s) in the body, {parsed_in_body} parsed as members"
        )
    for member in actual.members + implicit_constructor(actual):
        if HIDDEN & set(member.mods):
            continue
        if "actual" in member.mods or member.key in expected:
            continue
        findings.append(
            f"{src.where(member.pos, root)}: {platform} `actual {actual.kind} {actual.name}` declares public "
            f"`{member.describe()}`, which the expect does not"
        )
    return findings, errors


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--root", type=Path, default=ROOT, help="module checkout to scan (default: this one)")
    args = parser.parse_args()
    root = args.root.resolve()

    errors: list[str] = []
    findings: list[str] = []

    expect_files = sorted((root / "src/commonMain").rglob(f"*{EXPECT_SUFFIX}"))
    if not expect_files:
        print(f"{TAG} FAIL could not parse: no *{EXPECT_SUFFIX} under {root / 'src/commonMain'}", file=sys.stderr)
        return 1

    # Index every top-level `actual` declaration per platform: (package, name) -> [(source, decl)].
    actual_index: dict[str, dict[tuple[str, str], list[tuple[Source, Decl]]]] = {}
    for platform, rel in PLATFORM_ROOTS:
        index: dict[tuple[str, str], list[tuple[Source, Decl]]] = {}
        for path in sorted((root / rel).rglob("*.kt")):
            raw = path.read_text(encoding="utf-8")
            if "actual" not in raw:
                continue
            try:
                src = load(path)
                if count_keyword(src, "actual", 0, len(src.text)) == 0:
                    continue
                decls = parse_decls(src, 0, len(src.text))
            except ParseError as error:
                errors.append(f"could not parse {path}: {error}")
                continue
            tops = [d for d in decls if "actual" in d.mods]
            top_count = count_keyword(src, "actual", 0, len(src.text), header_ctor=False)
            if len(tops) != top_count:
                errors.append(
                    f"could not parse {src.where(0, root)}: {top_count} "
                    f"top-level `actual` keyword(s), {len(tops)} parsed"
                )
            for decl in tops:
                index.setdefault((package_of(src), decl.name), []).append((src, decl))
        actual_index[platform] = index

    compared: list[str] = []
    skipped: list[str] = []
    for path in expect_files:
        try:
            src = load(path)
            decls = parse_decls(src, 0, len(src.text))
        except ParseError as error:
            errors.append(f"could not parse {path}: {error}")
            continue
        expects = [d for d in decls if "expect" in d.mods]
        keyword_count = count_keyword(src, "expect", 0, len(src.text))
        if not expects or len(expects) != keyword_count:
            errors.append(
                f"could not parse {src.where(0, root)}: {keyword_count} top-level `expect` keyword(s), "
                f"{len(expects)} parsed"
            )
            continue
        package = package_of(src)
        for expect in expects:
            if expect.name in EXCLUDED:
                skipped.append(f"{expect.name} ({EXCLUDED[expect.name]})")
                continue
            for platform, _rel in PLATFORM_ROOTS:
                found = actual_index[platform].get((package, expect.name), [])
                if expect.kind in ("fun", "val", "var"):
                    found = [(s, d) for s, d in found if d.kind == expect.kind]
                    if not found:
                        errors.append(
                            f"{src.where(expect.pos, root)}: could not parse: no {platform} "
                            f"`actual {expect.kind} {expect.name}` in package {package}"
                        )
                    continue
                if len(found) != 1:
                    places = ", ".join(s.where(d.pos, root) for s, d in found) or "none"
                    errors.append(
                        f"{src.where(expect.pos, root)}: could not parse: expected one {platform} actual of "
                        f"`expect {expect.kind} {expect.name}` in package {package}, found {len(found)} ({places})"
                    )
                    continue
                actual_src, actual = found[0]
                if actual.kind == "typealias":
                    if expect.name not in TYPEALIAS_ACTUALS:
                        errors.append(
                            f"{actual_src.where(actual.pos, root)}: could not parse: {platform} actualizes "
                            f"`{expect.name}` with a typealias the gate cannot compare (list it in TYPEALIAS_ACTUALS)"
                        )
                    continue
                new_findings, new_errors = compare(expect, actual, actual_src, root, platform)
                findings.extend(new_findings)
                errors.extend(new_errors)
            if expect.kind in CLASSIFIERS and expect.name not in TYPEALIAS_ACTUALS:
                compared.append(f"{expect.name} ({len(expect.members)} member(s))")

    for reason in sorted(set(skipped)):
        print(f"{TAG} excluded: {reason}")
    for name, reason in TYPEALIAS_ACTUALS.items():
        print(f"{TAG} not compared: {name} -- {reason}")
    if errors:
        print(f"{TAG} FAIL could not parse {len(errors)} declaration(s); the gate never skips one:", file=sys.stderr)
        for error in errors:
            print(f"    {error}", file=sys.stderr)
    if findings:
        print(
            f"{TAG} FAIL {len(findings)} public member(s) on an `actual` that the `expect` does not declare. "
            "They are API on one platform only; make them internal/private, or declare them on the expect "
            "so every platform must have them:",
            file=sys.stderr,
        )
        for finding in findings:
            print(f"    {finding}", file=sys.stderr)
    if errors or findings:
        return 1
    print(
        f"{TAG} PASS {len(compared)} expect classifier(s) x {len(PLATFORM_ROOTS)} platforms "
        f"({', '.join(compared)}): no actual adds a public member"
    )
    return 0


if __name__ == "__main__":
    sys.exit(main())
