#!/usr/bin/env python3
"""Gate: the runtime sources survive the Android PanamaPort source remap (task 131).

Android compiles a textual COPY of `src/jvmMain/kotlin` and `src/commonMain/kotlin` in which
`KanamaAndroidRemap.remapLine` rewrites every `.invoke(` to `.invokeWithArguments(` -- right for a
`MethodHandle`, a compile error for a Kotlin function value -- and then undoes it for a short list
of known callback call sites. `:plugin:auditAndroidKanamaSources` catches the result, but only in
an Android build, which most desktop runs never start: task 131 shipped
`callbacks[id]?.callback?.invoke(args)` green on desktop and the Pixel lane failed before install.

This gate replays the remap on the desktop: it reads the rules and the forbidden fragments out of
`android/godot-plugin/buildSrc/src/main/kotlin/KanamaAndroidRemap.kt` (so it cannot drift from
them), applies the rules line by line to the same two trees (skipping `*.expect.kt`, as the copy
does), strips comments, and fails on any forbidden fragment -- the same verdict the Gradle audit
would reach. Call a function value as `f(args)` or `f?.let { it(args) }`, never `.invoke(`.

It also fails on a whole-address-space segment (`MemorySegment.NULL.reinterpret(...)`, also
`NULL_SEGMENT`, `ofAddress(0)`/`ofAddress(0x0)`, a wrapped chain, or a same-file `val` alias of
one; task 138 item 21): such a segment is addressed with ABSOLUTE addresses as offsets, and on Android every
native heap pointer is tagged (top byte `0xB4`), negative as a signed `long`, so the access throws
`IndexOutOfBoundsException` on the device while every desktop run passes. Address native memory
through a segment derived from the pointer (`ptr.reinterpret(size)`, offsets relative to it).

It also fails on `.invoke(` on a reflective receiver (a `java.lang.reflect.Method`: a value typed
`Method`, a `val`/`var` bound from `getMethod(...)` / `getDeclaredMethod(...)`, or a chained
`.getMethod(...).invoke(`): the remap turns it into `.invokeWithArguments(`, which `Method` does not
have, and the Android copy stops compiling (task 125: the API exercise hook). Call it through a
`MethodHandle` (`MethodHandles.lookup().unreflect(method)`), whose `invokeWithArguments` exists.

Usage:
    python3 scripts/check_android_remap_sources.py
"""

from __future__ import annotations

import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
REMAP = ROOT / "android/godot-plugin/buildSrc/src/main/kotlin/KanamaAndroidRemap.kt"
TREES = (ROOT / "src/jvmMain/kotlin", ROOT / "src/commonMain/kotlin")
EXPECT_SUFFIX = ".expect.kt"
TAG = "[android-remap-sources]"

# A segment based at address 0 (`MemorySegment.NULL`, Kanama's `NULL_SEGMENT`, `ofAddress(0)`):
# reinterpreted, its offsets are absolute addresses (task 138 item 21). Matched against the
# comment-stripped file joined into one string, so a ktfmt-wrapped chain cannot slip past.
_ZERO_BASE = r"(?:\bNULL\b|\bNULL_SEGMENT\b|\bofAddress\s*\(\s*(?:0[xX]0+|0+)[lL]?\s*\))"
ADDRESS_SPACE_SEGMENT = re.compile(_ZERO_BASE + r"\s*\??\.\s*reinterpret\s*\(")
# A `val` aliasing a zero-based segment in the same file (`val n = MemorySegment.NULL`); a use
# `n.reinterpret(` is the same idiom. An alias in another file is not traced.
ZERO_BASE_ALIAS = re.compile(
    r"\bval\s+(\w+)\s*(?::\s*MemorySegment\s*)?=\s*(?:MemorySegment\s*\.\s*)?" + _ZERO_BASE + r"(?!\s*\??\.)"
)

# Hot call sites that desktop calls with `invokeExact`, which ART rejects (task 131 item 16): the
# remap must rewrite every one to `invokeWithArguments`. Each file listed here must contain at least
# one such site the remap rewrote, so a site moved out of the gate's sight fails loudly instead of
# passing by having nothing to check (task 134 B: the builtin-call frame's one downcall).
REQUIRED_EXACT_SITES = (
    "src/jvmMain/kotlin/binding/runtime/BuiltinFrame.kt",
    "src/jvmMain/kotlin/binding/runtime/GodotStrings.kt",
)


def parse_remap(text: str) -> tuple[list[tuple[str, str]], list[str]]:
    rules_block = text[text.index("val rules = listOf(") : text.index("LEADING_MODIFIERS")]
    rules = re.findall(r'needle\s*=\s*"([^"]*)",\s*replacement\s*=\s*"([^"]*)"', rules_block)
    frag_start = text.index("val forbiddenSourceFragments = listOf(")
    frag_block = text[frag_start : text.index(")\n", frag_start)]
    fragments = re.findall(r'"([^"]*)"', frag_block)
    if not rules or not fragments:
        raise SystemExit(f"{TAG} FAIL: could not parse rules/fragments from {REMAP}")
    return rules, fragments


CHAR_LITERAL = re.compile(r"'(?:\\[^']+|[^\\'])'")

# The open lexical contexts of strip_comments, innermost last: a string, a raw string, or (>= 0) a
# `${ ... }` template expression holding the number of braces opened inside it so far.
STRING_CONTEXT = -1
RAW_STRING_CONTEXT = -2


def strip_comments(lines: list[str]) -> list[str]:
    """Mirror of KanamaAndroidRemap.strippedSourceLines, line for line: line and block comments, with
    string and character literals tracked (a `/*` or `//` inside one is text; `\"\"\"` raw strings span
    lines) and `${ ... }` template expressions nested (task 138 item 28: in `"${"/*"}"` the inner
    literal is its own string, so the `/*` is text and the later lines stay visible; a template may
    span lines, a regular string open at the end of its line is dropped). Keep the two in step
    (task 119 finding 13)."""
    out_lines: list[str] = []
    in_block = False
    contexts: list[int] = []
    for line in lines:
        out = []
        i = 0
        while i < len(line):
            if in_block:
                if line.startswith("*/", i):
                    in_block = False
                    i += 2
                else:
                    i += 1
                continue
            top = contexts[-1] if contexts else None
            if top == RAW_STRING_CONTEXT:
                if line.startswith('"""', i):
                    contexts.pop()
                    out.append('"""')
                    i += 3
                elif line.startswith("${", i):
                    contexts.append(0)
                    out.append("${")
                    i += 2
                else:
                    out.append(line[i])
                    i += 1
                continue
            if top == STRING_CONTEXT:
                c = line[i]
                if c == "\\" and i + 1 < len(line):
                    out.append(c + line[i + 1])
                    i += 2
                elif c == '"':
                    contexts.pop()
                    out.append(c)
                    i += 1
                elif line.startswith("${", i):
                    contexts.append(0)
                    out.append("${")
                    i += 2
                else:
                    out.append(c)
                    i += 1
                continue
            if line.startswith('"""', i):
                contexts.append(RAW_STRING_CONTEXT)
                out.append('"""')
                i += 3
                continue
            if line[i] == '"':
                contexts.append(STRING_CONTEXT)
                out.append('"')
                i += 1
                continue
            if line[i] == "'":
                literal = CHAR_LITERAL.match(line, i)
                if literal:
                    out.append(literal.group(0))
                    i = literal.end()
                    continue
            if line.startswith("/*", i):
                in_block = True
                i += 2
                continue
            if line.startswith("//", i):
                break
            if top is not None:
                # Inside a template expression: count its braces; its own `}` closes it.
                if line[i] == "{":
                    contexts[-1] = top + 1
                elif line[i] == "}":
                    if top == 0:
                        contexts.pop()
                    else:
                        contexts[-1] = top - 1
            out.append(line[i])
            i += 1
        # A regular string literal ends on its own line unless it is a raw string.
        while contexts and contexts[-1] == STRING_CONTEXT:
            contexts.pop()
        out_lines.append("".join(out))
    return out_lines


def address_space_segments(text: str) -> list[int]:
    """Line numbers where [text] (comment-stripped, whole file) reinterprets a zero-based segment."""
    starts = [m.start() for m in ADDRESS_SPACE_SEGMENT.finditer(text)]
    for alias in {m.group(1) for m in ZERO_BASE_ALIAS.finditer(text)}:
        use = re.compile(r"(?<![\w.])" + re.escape(alias) + r"\s*\??\.\s*reinterpret\s*\(")
        starts += [m.start() for m in use.finditer(text)]
    return sorted({text.count("\n", 0, start) + 1 for start in starts})


_GET_METHOD = r"\.\s*get(?:Declared)?Method\s*\("
# A value typed `Method` (a parameter, a property, a local).
METHOD_TYPED = re.compile(r"\b(\w+)\s*:\s*(?:java\.lang\.reflect\.)?Method\b\??")
# A `val`/`var` whose initializer (up to the next declaration) calls getMethod and does not hand it to
# `unreflect` (a MethodHandle, which takes `.invoke(` fine).
METHOD_BOUND = re.compile(r"\b(?:val|var)\s+(\w+)\b((?:(?!\b(?:val|var|fun)\b).){0,600}?)" + _GET_METHOD, re.DOTALL)
CHAINED_INVOKE = re.compile(
    _GET_METHOD + r"(?:[^()]|\((?:[^()]|\([^()]*\))*\))*\)\s*\??\.\s*invokeWithArguments\s*\("
)


def reflective_invokes(original: str, remapped: str) -> list[tuple[int, str]]:
    """(line, receiver) of every call on a reflective `Method` of [original] (comment-stripped) that
    reads `.invokeWithArguments(` in [remapped] (the same file after the remap, whose undo rules put
    the known sites such as `registerAll.invoke` back): `Method` has no such member."""
    names = set(METHOD_TYPED.findall(original))
    for match in METHOD_BOUND.finditer(original):
        if "unreflect" not in match.group(2):
            names.add(match.group(1))
    text = remapped
    out = []
    for name in sorted(names):
        for call in re.finditer(r"\b" + re.escape(name) + r"\s*\??\.\s*invokeWithArguments\s*\(", text):
            out.append((text.count("\n", 0, call.start()) + 1, name))
    for call in CHAINED_INVOKE.finditer(text):
        out.append((text.count("\n", 0, call.start()) + 1, "getMethod(...)"))
    return out


def main() -> int:
    rules, fragments = parse_remap(REMAP.read_text(encoding="utf-8"))
    failures: list[str] = []
    files = 0
    exact_sites: dict[str, int] = {}
    for tree in TREES:
        for path in sorted(tree.rglob("*.kt")):
            if path.name.endswith(EXPECT_SUFFIX):
                continue
            files += 1
            remapped = []
            for line in path.read_text(encoding="utf-8").splitlines():
                for needle, replacement in rules:
                    line = line.replace(needle, replacement)
                remapped.append(line)
            original = strip_comments(path.read_text(encoding="utf-8").splitlines())
            for number, receiver in reflective_invokes("\n".join(original), "\n".join(strip_comments(remapped))):
                failures.append(
                    f"{path.relative_to(ROOT)}:{number}: `.invoke(` on the reflective Method `{receiver}`: the "
                    "Android remap rewrites it to `.invokeWithArguments(`, which java.lang.reflect.Method does "
                    "not have, so the Android copy does not compile -- call it through a MethodHandle "
                    "(MethodHandles.lookup().unreflect(method).invokeWithArguments(...))"
                )
            for number in address_space_segments("\n".join(original)):
                failures.append(
                    f"{path.relative_to(ROOT)}:{number}: whole-address-space segment: an Android heap "
                    "pointer is tagged (top byte 0xB4) and negative as a long, so it is never a valid "
                    "offset -- address memory through the pointer's own segment (ptr.reinterpret(size))"
                )
            for number, (before, after) in enumerate(zip(original, strip_comments(remapped)), start=1):
                if ".invokeExact(" in before:
                    rel = str(path.relative_to(ROOT))
                    if ".invokeWithArguments(" in after and ".invokeExact(" not in after:
                        exact_sites[rel] = exact_sites.get(rel, 0) + 1
            for number, line in enumerate(strip_comments(remapped), start=1):
                for fragment in fragments:
                    if fragment in line:
                        failures.append(
                            f"{path.relative_to(ROOT)}:{number}: forbidden after the Android remap: "
                            f"'{fragment}' (call a function value as f(args) / f?.let {{ it(args) }}, "
                            "never .invoke()"
                        )
    for required in REQUIRED_EXACT_SITES:
        if not exact_sites.get(required):
            failures.append(
                f"{required}: no `.invokeExact(` call site rewritten to `.invokeWithArguments(` -- the "
                "hot downcall moved out of the remap's sight (or lost its rule)"
            )
    if failures:
        for failure in failures:
            print(f"{TAG} FAIL {failure}", file=sys.stderr)
        return 1
    print(
        f"{TAG} PASS {files} file(s) remapped with {len(rules)} rule(s); "
        f"no forbidden fragment ({len(fragments)} checked), no whole-address-space segment; {sum(exact_sites.values())} invokeExact site(s) "
        f"rewritten ({', '.join(f'{Path(k).name}: {v}' for k, v in sorted(exact_sites.items()))})"
    )
    return 0


if __name__ == "__main__":
    sys.exit(main())
