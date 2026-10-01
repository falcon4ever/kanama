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

The scan is textual: comments are stripped, then every parameter list that belongs to a
declaration -- `fun name(...)`, a primary or secondary `constructor(...)`, a class header
`class Name(...)` -- is split on top-level commas, and a parameter with a top-level `=` fails.
Annotation arguments (`@JvmName(name = "x")`) are not parameter lists and are not read.

    python3 scripts/check_expect_no_defaults.py                    # the module's src/ tree
    python3 scripts/check_expect_no_defaults.py --root /tmp/scratch  # (red runs) another tree
"""

from __future__ import annotations

import argparse
import re
import sys
from pathlib import Path

from generate_api_wrapper import match_closer, strip_comments

ROOT = Path(__file__).resolve().parents[1]
TAG = "[expect_defaults]"
EXPECT_SUFFIX = ".expect.kt"

# The opening parenthesis of a declaration's parameter list. Function names may carry type
# parameters and a receiver (`fun <T : Any> Foo.bar(`); a class header may carry type parameters,
# a visibility and the `constructor` keyword before its parameters.
DECLARATION_PAREN_RE = re.compile(
    r"\bfun\s+(?:<[^>]*>\s*)?(?:[\w.]+(?:<[^>]*>)?\??\.)?`?\w+`?\s*\("
    r"|\bconstructor\s*\("
    r"|\bclass\s+\w+\s*(?:<[^>]*>)?\s*"
    r"(?:(?:@\w+\s+)*(?:public|internal|private|protected)\s+)?(?:constructor\s*)?\("
)


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


def scan(path: Path) -> tuple[int, list[str]]:
    """(declarations read, findings) for one `*.expect.kt` file."""
    src = strip_comments(path.read_text(encoding="utf-8"))
    findings: list[str] = []
    declarations = 0
    for match in DECLARATION_PAREN_RE.finditer(src):
        open_paren = match.end() - 1
        close = match_closer(src, open_paren)
        declarations += 1
        for param in split_top_level(src[open_paren + 1 : close]):
            if has_default(param):
                line = src.count("\n", 0, open_paren) + 1
                findings.append(f"{path}:{line}: `{' '.join(param.split())}`")
    return declarations, findings


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
    for path in files:
        count, found = scan(path)
        total += count
        findings.extend(found)
    if findings:
        print(
            f"{TAG} FAIL {len(findings)} default argument(s) on an `expect` declaration. The "
            "Android lane skips *.expect.kt, so the default would not exist there; declare an "
            "overload per omitted argument instead (task 117 D24):",
            file=sys.stderr,
        )
        for finding in findings:
            print(f"    {finding}", file=sys.stderr)
        return 1
    print(
        f"{TAG} PASS {len(files)} *{EXPECT_SUFFIX} file(s), {total} declaration parameter list(s), "
        "no default argument"
    )
    return 0


if __name__ == "__main__":
    sys.exit(main())
