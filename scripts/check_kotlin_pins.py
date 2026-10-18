#!/usr/bin/env python3
"""Assert every Kotlin, KSP and kotlinx-coroutines version pin in the repo matches its one source.

The root `build.gradle.kts` (`kotlin("jvm")`, `kotlin("multiplatform")` and `com.google.devtools.ksp`)
is the single source of truth. The release kit, the KSP processor's `symbol-processing-api`
dependency, the Android plugin build and the toolchain line of `docs/reference/version-support.md`
restate those versions by hand, and a Kotlin bump that missed one still builds here while the
missed artifact compiles against the old compiler. This gate fails on any drift, on a listed pin
it can no longer find, and on any Kotlin or KSP pin it was not told about in a tracked Gradle script (the root one
included), gradle.properties or libs.versions.toml: plugin ids in the infix and the `.version("x")`
call form, `org.jetbrains.kotlin:kotlin-*` and KSP coordinates, `*kotlin*Version=` properties and
version-catalog entries (so a new one cannot drift unseen). kotlinx-coroutines: every coordinate, property and
catalog entry must equal the root build's kotlinx-coroutines-core dependency, except under android/godot-plugin/,
which compiles against the APK's version (`kanamaAndroidCoroutinesVersion` in its gradle.properties, which both
Android modules must read); version-support.md states both. The demos repo pins its own copies and is out of scope.

Usage: python3 scripts/check_kotlin_pins.py
"""

from __future__ import annotations

import re
import subprocess
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
TAG = "[check_kotlin_pins]"

ROOT_PINS = {
    "kotlin-jvm": re.compile(r'^\s*kotlin\("jvm"\) version "([^"]+)"', re.M),
    "kotlin-multiplatform": re.compile(r'^\s*kotlin\("multiplatform"\) version "([^"]+)"', re.M),
    "ksp": re.compile(r'^\s*id\("com\.google\.devtools\.ksp"\) version "([^"]+)"', re.M),
}

# Any Kotlin or KSP version a Gradle script can pin, and which root value it must equal.
# Both the infix form (`kotlin("jvm") version "x"`) and the call form (`kotlin("jvm").version("x")`).
_VERSION = r'(?:\s+version\s+|\s*\.\s*version\s*\(\s*)"([^"]+)"'
GRADLE_PIN_PATTERNS = (
    ("kotlin", re.compile(r'kotlin\("[A-Za-z0-9.-]+"\)' + _VERSION)),
    ("kotlin", re.compile(r'id\("org\.jetbrains\.kotlin\.[A-Za-z0-9.-]+"\)' + _VERSION)),
    ("ksp", re.compile(r'id\("com\.google\.devtools\.ksp"\)' + _VERSION)),
    ("ksp", re.compile(r'"com\.google\.devtools\.ksp:symbol-processing[A-Za-z-]*:([^"]+)"')),
    ("kotlin", re.compile(r'"org\.jetbrains\.kotlin:kotlin-[a-z-]+:([^"]+)"')),
    ("coroutines", re.compile(r'"org\.jetbrains\.kotlinx:kotlinx-coroutines-[a-z0-9-]+:([^"$][^"]*)"')),  # a `${...}` version reads a property, checked at its source
)
# The coroutines version: the root build's kotlinx-coroutines dependency for desktop, iOS and Web; the Android
# AARs compile against the APK's (Godot's template), one property their builds read.
ROOT_COROUTINES_RE = re.compile(r'"org\.jetbrains\.kotlinx:kotlinx-coroutines-core:([^"]+)"')
ANDROID_PROPS = "android/godot-plugin/gradle.properties"
ANDROID_COROUTINES_KEY = "kanamaAndroidCoroutinesVersion"
ANDROID_DIR = "android/godot-plugin/"
COROUTINES_PROPERTY_RE = re.compile(r"^\s*([A-Za-z0-9_.-]*coroutines[A-Za-z0-9_.-]*[Vv]ersion)\s*[=:]\s*(\S+)", re.I)
TOML_COROUTINES_RE = re.compile(r'^\s*(kotlinx-coroutines[A-Za-z0-9_-]*)\s*=\s*"([^"]+)"|kotlinx-coroutines[^}\n]*?\bversion\s*=\s*"([^"]+)"')
# gradle.properties: a key naming Kotlin or KSP and ending in "version" (kotlinVersion=, kotlin.version=, kspVersion=).
PROPERTY_PIN_RE = re.compile(r"^\s*([A-Za-z0-9_.-]*?(kotlin(?!x)|ksp)[A-Za-z0-9_.-]*?[Vv]ersion)\s*[=:]\s*(\S+)", re.I)
# libs.versions.toml: a [versions] key `kotlin`/`kotlin-*`/`ksp*` (not kotlinx), or an inline entry for a Kotlin or KSP
# plugin/module with `version = "x"`, or a "group:name:version" Kotlin/KSP coordinate (the Gradle patterns above).
TOML_VERSION_KEY_RE = re.compile(r'^\s*((kotlin(?!x)|ksp)[A-Za-z0-9_-]*)\s*=\s*"([^"]+)"', re.I)
TOML_INLINE_RE = re.compile(r'(org\.jetbrains\.kotlin|com\.google\.devtools\.ksp)[^}\n]*?\bversion\s*=\s*"([^"]+)"')

# Pins that must exist (a pattern that stops matching must fail, not pass on nothing).
REQUIRED = (
    ("templates/release-kit/build.gradle.kts", "kotlin", re.compile(r'kotlin\("jvm"\) version "([^"]+)"')),
    ("templates/release-kit/build.gradle.kts", "ksp", re.compile(r'id\("com\.google\.devtools\.ksp"\) version "([^"]+)"')),
    ("processor/build.gradle.kts", "ksp", re.compile(r'"com\.google\.devtools\.ksp:symbol-processing-api:([^"]+)"')),
    ("android/godot-plugin/build.gradle.kts", "kotlin", re.compile(r'id\("org\.jetbrains\.kotlin\.android"\) version "([^"]+)"')),
    ("docs/reference/version-support.md", "kotlin", re.compile(r"source build uses Kotlin `([^`]+)`")),
    ("docs/reference/version-support.md", "ksp", re.compile(r"source build uses Kotlin `[^`]+`, KSP `([^`]+)`")),
    ("docs/reference/version-support.md", "coroutines", re.compile(r"source build uses Kotlin `[^`]+`, KSP `[^`]+`, and\s+kotlinx\.coroutines `([^`]+)`")),
    ("docs/reference/version-support.md", "coroutines-android", re.compile(r"against kotlinx\.coroutines ([0-9][^:\s]*):")),
    # The two Android modules read the property (a literal there would be checked too, but must not be needed).
    ("android/godot-plugin/plugin/build.gradle.kts", "uses-property", re.compile(r'gradleProperty\("(kanamaAndroidCoroutinesVersion)"\)')),
    ("android/godot-plugin/scripts/build.gradle.kts", "uses-property", re.compile(r'gradleProperty\("(kanamaAndroidCoroutinesVersion)"\)')),
)


def tracked(*patterns: str) -> list[str]:
    # tracked files and new ones not yet added (ignored build output stays out)
    out = subprocess.run(["git", "ls-files", "--cached", "--others", "--exclude-standard", *patterns], cwd=ROOT, capture_output=True, text=True, check=True).stdout
    return [p for p in out.splitlines() if p]


def scan_pins(rel: str, text: str) -> list[tuple[int, str, str]]:
    """Every (line, kind, version) Kotlin/KSP pin in a tracked build file."""
    pins: list[tuple[int, str, str]] = []
    is_props = rel.endswith("gradle.properties")
    is_toml = rel.endswith(".toml")
    for lineno, line in enumerate(text.splitlines(), 1):
        stripped = line.lstrip()
        if stripped.startswith(("//", "#")):
            continue
        if is_props:
            m = PROPERTY_PIN_RE.match(line)
            if m:
                pins.append((lineno, "ksp" if m.group(2).lower() == "ksp" else "kotlin", m.group(3)))
            m = COROUTINES_PROPERTY_RE.match(line)
            if m:
                android = rel == ANDROID_PROPS and m.group(1) == ANDROID_COROUTINES_KEY
                pins.append((lineno, "coroutines-android" if android else "coroutines", m.group(2)))
            continue
        if is_toml:
            m = TOML_VERSION_KEY_RE.match(line)
            if m:
                pins.append((lineno, "ksp" if m.group(2).lower() == "ksp" else "kotlin", m.group(3)))
            for m in TOML_INLINE_RE.finditer(line):
                pins.append((lineno, "ksp" if "ksp" in m.group(1) else "kotlin", m.group(2)))
            for m in TOML_COROUTINES_RE.finditer(line):
                pins.append((lineno, "coroutines", m.group(2) or m.group(3)))
        for kind, pattern in GRADLE_PIN_PATTERNS:
            for m in pattern.finditer(line):
                pins.append((lineno, kind, m.group(1)))
    return pins


def main() -> int:
    root_text = (ROOT / "build.gradle.kts").read_text(encoding="utf-8")
    found: dict[str, str] = {}
    failures: list[str] = []
    for name, pattern in ROOT_PINS.items():
        m = pattern.search(root_text)
        if not m:
            failures.append(f"build.gradle.kts: no {name} plugin version in the plugins block")
        else:
            found[name] = m.group(1)
    if failures:
        print(f"{TAG} FAIL — cannot read the root pins:", file=sys.stderr)
        for f in failures:
            print(f"  - {f}", file=sys.stderr)
        return 1
    if found["kotlin-jvm"] != found["kotlin-multiplatform"]:
        failures.append(
            f'build.gradle.kts: kotlin("jvm") {found["kotlin-jvm"]} but kotlin("multiplatform") {found["kotlin-multiplatform"]}'
        )
    expected = {"kotlin": found["kotlin-jvm"], "ksp": found["ksp"]}
    source = {"kotlin": "the root build.gradle.kts", "ksp": "the root build.gradle.kts"}
    m = ROOT_COROUTINES_RE.search(root_text)
    if not m:
        failures.append("build.gradle.kts: no kotlinx-coroutines-core dependency; the coroutines source moved")
    else:
        expected["coroutines"], source["coroutines"] = m.group(1), "the root build.gradle.kts"
    props = (ROOT / ANDROID_PROPS).read_text(encoding="utf-8") if (ROOT / ANDROID_PROPS).is_file() else ""
    m = re.search(rf"^{ANDROID_COROUTINES_KEY}=(\S+)$", props, re.M)
    if not m:
        failures.append(f"{ANDROID_PROPS}: no {ANDROID_COROUTINES_KEY}; the Android coroutines source moved")
    else:
        expected["coroutines-android"], source["coroutines-android"] = m.group(1), ANDROID_PROPS
    if failures:
        print(f"{TAG} FAIL — cannot read the pins:", file=sys.stderr)
        for f in failures:
            print(f"  - {f}", file=sys.stderr)
        return 1

    for rel, kind, pattern in REQUIRED:
        path = ROOT / rel
        text = path.read_text(encoding="utf-8") if path.is_file() else ""
        m = pattern.search(text)
        if not m:
            failures.append(f"{rel}: the {kind} pin is gone or reworded (pattern {pattern.pattern!r}); update this check")
        elif kind != "uses-property" and m.group(1) != expected[kind] and not rel.endswith((".gradle.kts", ".gradle")):  # the scan below reports Gradle pins with their line
            failures.append(f"{rel}: {kind} {m.group(1)} but {source[kind]} pins {expected[kind]}")

    seen = 0
    # The root build.gradle.kts too: its plugins block is the source, but any other pin in it must agree.
    for rel in tracked("*.gradle.kts", "*.gradle", "*gradle.properties", "*.versions.toml"):
        for lineno, kind, version in scan_pins(rel, (ROOT / rel).read_text(encoding="utf-8")):
            if kind == "coroutines" and rel.startswith(ANDROID_DIR):
                kind = "coroutines-android"  # the Android build compiles against the APK's coroutines
            seen += 1
            if version != expected[kind]:
                failures.append(f"{rel}:{lineno}: {kind} {version} but {source[kind]} pins {expected[kind]}")

    if failures:
        print(f"{TAG} FAIL — Kotlin/KSP/coroutines pins drifted from their sources:", file=sys.stderr)
        for f in failures:
            print(f"  - {f}", file=sys.stderr)
        return 1
    print(
        f"{TAG} PASS — Kotlin {expected['kotlin']}, KSP {expected['ksp']}, kotlinx-coroutines {expected['coroutines']} "
        f"(Android {expected['coroutines-android']}): {seen} build-file pin(s) and the version-support lines agree"
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
