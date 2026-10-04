#!/usr/bin/env python3
"""Hand-kept copies that must stay equal (task 133 C2).

1. The script annotations exist twice: `annotations/` (JVM: desktop, Android, Web's KSP) and the
   Kotlin/Native copy in `src/iosMain/.../annotations/Annotations.kt` (the `annotations` module is
   JVM-only). Every annotation of one must be in the other with the same parameters (name, type,
   default, `vararg`), or a script that builds on desktop fails on iOS or reads another default
   there. `@RegisterClass` is the one JVM-only annotation (ClassDB registration has no iOS path).
2. `kanamaAutoloadInputs` (the KSP inputs that type `Autoloads`) is copied into each build that runs
   KSP on scripts and into the consumer Gradle template users receive; the copies must be the same
   code (indentation aside).

Usage: python3 scripts/check_hand_copies.py
"""

from __future__ import annotations

import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
TAG = "[hand_copies]"

JVM_ANNOTATIONS = sorted((ROOT / "annotations/src/main/kotlin/net/multigesture/kanama/annotations").glob("*.kt"))
IOS_ANNOTATIONS = ROOT / "src/iosMain/kotlin/net/multigesture/kanama/annotations/Annotations.kt"
JVM_ONLY = {"RegisterClass"}

AUTOLOAD_INPUT_COPIES = (
    ROOT / "build.gradle.kts",
    ROOT / "project-scripts/build.gradle.kts",
    ROOT / "web-runtime/build.gradle.kts",
    ROOT / "templates/consumer-gradle/kanama-project.gradle.kts",
)
AUTOLOAD_INPUT_FUN = "fun kanamaAutoloadInputs("

ANNOTATION_RE = re.compile(r"\bannotation\s+class\s+(\w+)\s*(\((?P<params>[^()]*)\))?")
COMMENT_RE = re.compile(r"//[^\n]*|/\*.*?\*/", re.S)


def annotations(paths: list[Path]) -> dict[str, tuple[str, ...]]:
    found: dict[str, tuple[str, ...]] = {}
    for path in paths:
        text = COMMENT_RE.sub("", path.read_text(encoding="utf-8"))
        for match in ANNOTATION_RE.finditer(text):
            if match.group(2) is None and text[match.end() :].lstrip().startswith("("):
                # A parameter list with nested parentheses: refuse rather than compare it as empty.
                raise SystemExit(f"{TAG} FAIL {path.relative_to(ROOT)}: cannot parse @{match.group(1)}'s parameters")
            params = match.group("params") or ""
            normalized = tuple(
                " ".join(part.split()) for part in params.split(",") if part.strip()
            )
            found[match.group(1)] = normalized
    return found


def function_body(path: Path) -> list[str] | None:
    lines = path.read_text(encoding="utf-8").splitlines()
    for index, line in enumerate(lines):
        if line.lstrip().startswith(AUTOLOAD_INPUT_FUN):
            indent = len(line) - len(line.lstrip())
            body = [line.strip()]
            for following in lines[index + 1 :]:
                body.append(following.strip())
                if following.strip() == "}" and len(following) - len(following.lstrip()) == indent:
                    return [b for b in body if b]
            return None
    return None


def main() -> int:
    problems: list[str] = []

    jvm = annotations(JVM_ANNOTATIONS)
    ios = annotations([IOS_ANNOTATIONS])
    if not jvm or not ios:
        print(f"{TAG} FAIL no annotation declarations parsed (jvm={len(jvm)}, ios={len(ios)})", file=sys.stderr)
        return 1
    for name in sorted(set(jvm) - set(ios) - JVM_ONLY):
        problems.append(f"@{name} is in annotations/ but not in the iOS copy ({IOS_ANNOTATIONS.relative_to(ROOT)})")
    for name in sorted(set(ios) - set(jvm)):
        problems.append(f"@{name} is in the iOS copy but not in annotations/")
    for name in sorted(JVM_ONLY & set(ios)):
        problems.append(f"@{name} is JVM-only (no iOS path) but the iOS copy declares it")
    for name in sorted(set(jvm) & set(ios)):
        if jvm[name] != ios[name]:
            problems.append(f"@{name} parameters differ: annotations/ ({', '.join(jvm[name])}) vs iOS ({', '.join(ios[name])})")

    bodies = {path: function_body(path) for path in AUTOLOAD_INPUT_COPIES}
    for path, body in bodies.items():
        if body is None:
            problems.append(f"{path.relative_to(ROOT)} has no `{AUTOLOAD_INPUT_FUN}...` function")
    reference_path = AUTOLOAD_INPUT_COPIES[0]
    reference = bodies[reference_path]
    for path, body in bodies.items():
        if reference is not None and body is not None and body != reference:
            first = next(
                (i for i, (a, b) in enumerate(zip(reference, body)) if a != b), min(len(reference), len(body))
            )
            problems.append(
                f"{path.relative_to(ROOT)}: kanamaAutoloadInputs differs from {reference_path.relative_to(ROOT)} "
                f"at line {first + 1} of the function"
            )

    if problems:
        print(f"{TAG} FAIL {len(problems)} hand-kept copy difference(s):", file=sys.stderr)
        for problem in problems:
            print(f"  - {problem}", file=sys.stderr)
        return 1
    print(
        f"{TAG} PASS {len(ios)} annotations equal in annotations/ and the iOS copy "
        f"({', '.join(sorted(JVM_ONLY))} JVM-only); kanamaAutoloadInputs equal in {len(bodies)} builds"
    )
    return 0


if __name__ == "__main__":
    sys.exit(main())
