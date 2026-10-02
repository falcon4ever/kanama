#!/usr/bin/env python3
"""Gate: no `expect` declaration in a `*.expect.kt` file carries a default argument (task 117 D24).

Kotlin Multiplatform forbids restating a default on the `actual` ("Actual function cannot have
default argument values. They must be declared in the expected function"), so a default can only
live on the `expect`. Android compiles a COPY of the module's common and JVM sources as a plain
Kotlin library: `KanamaAndroidRemap` skips every `*.expect.kt` file and strips the `actual`
modifier from the JVM actuals, so on that lane a default declared only on an `expect` simply does
not exist, and every caller that omits the argument compiles on desktop and iOS and fails on
Android. The rule is therefore "no defaults on an `expect`; overloads instead" -- overloads keep
positional and trailing-lambda call sites source-compatible on all four lanes.

The scan is textual: comments and string literals are blanked (offsets and lines kept), then
every parameter list that belongs to a declaration -- `fun name(...)`, a primary or secondary
`constructor(...)`, a class header `class Name(...)` -- is split on top-level commas, and a
parameter with a top-level `=` fails. Annotation arguments (`@JvmName(name = "x")`) are not
parameter lists and are not read.

Type parameters and receivers are skipped with a bracket-balanced scan, so nested generics
(`fun <T : Comparable<T>> f(`, `fun <K, V> Map<K, List<V>>.f(`, `class Y<T : Comparable<T>>(`)
are read like any other declaration. And the scan is complete or it fails: every `fun`,
`constructor` and `class` keyword is a declaration that must resolve -- to a parameter list, or
(for `class`) to a header with no keyword-less primary constructor -- and one that does not is
"unparsed declaration at file:line", never a silent skip.

    python3 scripts/check_expect_no_defaults.py                    # the module's src/ tree
    python3 scripts/check_expect_no_defaults.py --root /tmp/scratch  # (red runs) another tree
"""

from __future__ import annotations

import argparse
import re
import sys
from pathlib import Path

from generate_api_wrapper import match_closer, strip_noise

ROOT = Path(__file__).resolve().parents[1]
TAG = "[expect_defaults]"
EXPECT_SUFFIX = ".expect.kt"

# Every keyword that opens a declaration with (or, for `class`, possibly with) a parameter list.
# `fun interface` is not a function, `Foo::class` is not a declaration.
DECLARATION_KEYWORD_RE = re.compile(r"(?<!::)\b(fun|constructor|class)\b(?!\s+interface\b)")
# What may follow a class name (and its type parameters) when the class has no keyword-less primary
# constructor: a body, a supertype list, a `where` clause, the next declaration, the end of the
# enclosing body or the file, or `constructor` with its modifiers (that keyword is scanned itself).
CLASS_NO_PARAMS_RE = re.compile(
    r"(?:$|[{:}]|where\b|@|(?:public|private|internal|protected|expect|actual|constructor|"
    r"class|interface|object|fun|val|var|typealias|sealed|abstract|open|data|enum|annotation|"
    r"value|inner|companion|override|suspend|inline|operator)\b)"
)


class Unparsed(Exception):
    """A declaration keyword the scan could not resolve."""


def skip_ws(src: str, i: int) -> int:
    while i < len(src) and src[i].isspace():
        i += 1
    return i


def skip_angles(src: str, i: int) -> int:
    """[i] is at `<`: the index just past its balanced `>`. `->` never closes; a body, statement end
    or the end of the file before the balance is reached means the declaration is unparsed."""
    depth = 0
    while i < len(src):
        ch = src[i]
        if ch == "<":
            depth += 1
        elif ch == ">" and src[i - 1] != "-":
            depth -= 1
            if depth == 0:
                return i + 1
        elif ch in "{};=":
            break
        i += 1
    raise Unparsed("unbalanced `<`")


def fun_params(src: str, i: int) -> int:
    """[i] is just past `fun`: the offset of its parameter list's `(`."""
    i = skip_ws(src, i)
    if i < len(src) and src[i] == "<":
        i = skip_ws(src, skip_angles(src, i))
    if i < len(src) and src[i] == "(":  # a parenthesized receiver: `fun ((Int) -> Unit).f(`
        i = skip_ws(src, match_closer(src, i) + 1)
        if not (src.startswith(".", i) or src.startswith("?.", i)):
            raise Unparsed("no name after a parenthesized receiver")
    name_start = i
    while i < len(src):
        ch = src[i]
        if ch == "<":
            i = skip_angles(src, i)
        elif ch.isalnum() or ch in "_.?`":
            i += 1
        else:
            break
    if i == name_start or not re.search(r"\w`?$", src[name_start:i]):
        raise Unparsed("no function name")
    i = skip_ws(src, i)
    if i >= len(src) or src[i] != "(":
        raise Unparsed("no parameter list after the function name")
    return i


def class_params(src: str, i: int) -> int | None:
    """[i] is just past `class`: the offset of a keyword-less primary constructor's `(`, or None
    when the header has none (a `constructor` keyword is scanned on its own)."""
    match = re.compile(r"\s*`?\w+`?").match(src, i)
    if not match:
        raise Unparsed("no class name")
    i = skip_ws(src, match.end())
    if i < len(src) and src[i] == "<":
        i = skip_ws(src, skip_angles(src, i))
    if i < len(src) and src[i] == "(":
        return i
    if CLASS_NO_PARAMS_RE.match(src, i):
        return None
    raise Unparsed("unexpected text after the class name")


def keyword_count(src: str) -> int:
    """The token scan, independent of the parser: every `fun`, `constructor` and `class` keyword,
    less `fun interface` and `::class`."""
    tokens = len(re.findall(r"\b(?:fun|constructor|class)\b", src))
    return tokens - len(re.findall(r"\bfun\s+interface\b", src)) - len(re.findall(r"::class\b", src))


def declaration_lists(src: str) -> tuple[int, list[int], list[int]]:
    """(class headers resolved with no keyword-less primary constructor, offsets of the parameter
    lists read, offsets of the keywords that could not be resolved) for one blanked source."""
    no_params = 0
    lists: list[int] = []
    unparsed: list[int] = []
    for match in DECLARATION_KEYWORD_RE.finditer(src):
        kind = match.group(1)
        try:
            if kind == "fun":
                lists.append(fun_params(src, match.end()))
            elif kind == "constructor":
                i = skip_ws(src, match.end())
                if i >= len(src) or src[i] != "(":
                    raise Unparsed("`constructor` without a parameter list")
                lists.append(i)
            else:
                open_paren = class_params(src, match.end())
                if open_paren is not None:
                    lists.append(open_paren)
                else:
                    no_params += 1  # a class header with no keyword-less primary constructor
        except (Unparsed, ValueError):
            unparsed.append(match.start())
    return no_params, lists, unparsed


def split_top_level(params: str) -> list[str]:
    """Split a parameter list on top-level commas; `->` never closes a generic."""
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


def has_default(param: str) -> bool:
    """True when [param] has a top-level `=` that is an assignment (not `==`, `>=`, `->`, ...)."""
    depth = 0
    for i, ch in enumerate(param):
        if ch in "(<[{":
            depth += 1
        elif ch in ")]}" or (ch == ">" and param[i - 1 : i] != "-"):
            depth -= 1
        elif ch == "=" and depth == 0:
            before = param[i - 1 : i]
            after = param[i + 1 : i + 2]
            if before not in ("=", "!", "<", ">") and after != "=":
                return True
    return False


def scan(path: Path) -> tuple[int, list[str], list[str]]:
    """(parameter lists read, default findings, unparsed declarations) for one `*.expect.kt` file."""
    original = path.read_text(encoding="utf-8")
    src = strip_noise(original)  # same offsets as [original]
    keywords = keyword_count(src)
    no_params, lists, unparsed_at = declaration_lists(src)

    def line(offset: int) -> int:
        return src.count("\n", 0, offset) + 1

    unparsed = [f"{path}:{line(offset)}: `{src[offset:src.find(chr(10), offset)].strip()}`" for offset in unparsed_at]
    if keywords != len(lists) + no_params + len(unparsed_at):
        unparsed.append(
            f"{path}: the token scan found {keywords} `fun`/`constructor`/`class` keyword(s), the parser "
            f"accounted for {len(lists) + no_params + len(unparsed_at)} ({len(lists)} parameter list(s), "
            f"{no_params} class header(s) without one)"
        )
    findings: list[str] = []
    for open_paren in lists:
        close = match_closer(src, open_paren)
        offset = open_paren + 1
        for param in split_top_level(src[open_paren + 1 : close]):
            at = src.index(param, offset)
            offset = at + len(param)
            if has_default(param):
                shown = " ".join(original[at : at + len(param)].split())
                findings.append(f"{path}:{line(at)}: `{shown}`")
    return len(lists), findings, unparsed


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument(
        "--root",
        type=Path,
        default=ROOT / "src",
        help="directory scanned recursively for *.expect.kt (default: the module's src/)",
    )
    args = parser.parse_args()

    files = sorted(args.root.rglob(f"*{EXPECT_SUFFIX}"))
    if not files:
        print(f"{TAG} FAIL no *{EXPECT_SUFFIX} file under {args.root}", file=sys.stderr)
        return 1
    total = 0
    findings: list[str] = []
    unparsed: list[str] = []
    for path in files:
        count, found, missed = scan(path)
        total += count
        findings.extend(found)
        unparsed.extend(missed)
    if unparsed:
        print(
            f"{TAG} FAIL {len(unparsed)} unparsed declaration(s); the scan never skips one:",
            file=sys.stderr,
        )
        for entry in unparsed:
            print(f"    unparsed declaration at {entry}", file=sys.stderr)
    if findings:
        print(
            f"{TAG} FAIL {len(findings)} default argument(s) on an `expect` declaration. The "
            "Android lane skips *.expect.kt, so the default would not exist there; declare an "
            "overload per omitted argument instead (task 117 D24):",
            file=sys.stderr,
        )
        for finding in findings:
            print(f"    {finding}", file=sys.stderr)
    if unparsed or findings:
        return 1
    print(
        f"{TAG} PASS {len(files)} *{EXPECT_SUFFIX} file(s), {total} declaration parameter list(s), "
        "no default argument"
    )
    return 0


if __name__ == "__main__":
    sys.exit(main())
