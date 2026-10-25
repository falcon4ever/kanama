#!/usr/bin/env python3
"""Assert a kit's version pins move in lockstep with the checkout that builds it (task 135 item 5).

A kit or store addon carries prebuilt Kanama artifacts (the runtime jar, the Android runtime AAR, the Kanama Gradle
plugin) beside build files a user runs with them. The plugin's inner builds take every version from the generated
`KanamaVersions` object, which `gradle-plugin/build.gradle.kts` reads from the checkout's own pins when it builds the
plugin, so those cannot drift. What a kit still restates by hand is checked here against its one source:

* Kotlin and KSP: `templates/release-kit/build.gradle.kts` against the root build's plugins block (the kit compiles
  scripts against klibs and jars made by that compiler; check_kotlin_pins.py holds the rest of the repo);
* Godot: the `config/features` minor version of `templates/release-kit/project.godot` against `kanamaGodotVersion`;
* PanamaPort: the default coordinate of the two Android modules against the root build's (the runtime AAR links one,
  the scripts AAR compiles against it, and the `.gdap` the plugin writes names the root's);
* Android Min SDK: the preset package_install_smoke.sh exports with and the export guard's ANDROID_MIN_SDK against
  the scripts module's `minSdk`, which the plugin's inner build uses;
* the Kanama version: the editor plugin's `plugin.cfg` in example_project (the kit's copy is generated) against the
  root build's `version`;
* and that the generator of `KanamaVersions` still reads each of those sources.

Usage: python3 scripts/check_kit_version_pins.py
"""

from __future__ import annotations

import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
TAG = "[check_kit_version_pins]"


def read(rel: str) -> str:
    return (ROOT / rel).read_text(encoding="utf-8")


def pin(rel: str, pattern: str, failures: list[str], what: str) -> str | None:
    match = re.search(pattern, read(rel), re.M)
    if match is None:
        failures.append(f"{rel}: no {what} found (/{pattern}/)")
        return None
    return match.group(1)


def main() -> int:
    failures: list[str] = []
    root = "build.gradle.kts"
    kit = "templates/release-kit/build.gradle.kts"

    for name, pattern in (
        ("Kotlin", r'^\s*kotlin\("jvm"\) version "([^"]+)"'),
        ("KSP", r'^\s*id\("com\.google\.devtools\.ksp"\) version "([^"]+)"'),
    ):
        source = pin(root, pattern, failures, f"{name} pin")
        restated = pin(kit, pattern, failures, f"{name} pin")
        if source and restated and source != restated:
            failures.append(f"{kit}: {name} {restated}, but {root} builds the kit's artifacts with {name} {source}")

    godot = pin("gradle.properties", r"^kanamaGodotVersion=(\S+)", failures, "kanamaGodotVersion")
    features = pin("templates/release-kit/project.godot", r'^config/features=PackedStringArray\("([0-9.]+)"', failures, "config/features")
    if godot and features and ".".join(godot.split(".")[:2]) != features:
        failures.append(f"templates/release-kit/project.godot: config/features {features}, but kanamaGodotVersion is {godot}")

    panama_pattern = r'"(com\.github\.falcon4ever\.PanamaPort:Core:[^"]+)"'
    panama = pin(root, panama_pattern, failures, "PanamaPort coordinate")
    for rel in ("android/godot-plugin/plugin/build.gradle.kts", "android/godot-plugin/scripts/build.gradle.kts"):
        restated = pin(rel, panama_pattern, failures, "PanamaPort coordinate")
        if panama and restated and restated != panama:
            failures.append(f"{rel}: PanamaPort {restated}, but {root} (and the plugin's .gdap) says {panama}")

    min_sdk = pin("android/godot-plugin/scripts/build.gradle.kts", r"^\s*minSdk = (\d+)", failures, "minSdk")
    preset = pin("scripts/package_install_smoke.sh", r'^gradle_build/min_sdk="(\d+)"', failures, "the Android preset's min_sdk")
    if min_sdk and preset and preset != min_sdk:
        failures.append(f"scripts/package_install_smoke.sh: the smoke's preset has Min SDK {preset}, but the scripts AAR is built for {min_sdk}")
    guard = "templates/starter/addons/kanama_tools/kanama_export_guard.gd"
    guard_match = re.search(r"^const ANDROID_MIN_SDK\s*:?=\s*(\d+)", read(guard), re.M)
    if min_sdk and guard_match and guard_match.group(1) != min_sdk:
        failures.append(f"{guard}: ANDROID_MIN_SDK {guard_match.group(1)}, but the scripts AAR is built for {min_sdk}")

    version = pin(root, r'^version = "([^"]+)"', failures, "version")
    cfg = pin("example_project/addons/kanama_tools/plugin.cfg", r'^version="([^"]+)"', failures, "plugin version")
    if version and cfg and cfg != version:
        failures.append(f"example_project/addons/kanama_tools/plugin.cfg: version {cfg}, but Kanama is {version}")
    template_cfg = read("templates/starter/addons/kanama_tools/plugin.cfg")
    if 'version="@KANAMA_VERSION@"' not in template_cfg:
        failures.append('templates/starter/addons/kanama_tools/plugin.cfg: the version must be version="@KANAMA_VERSION@" (packaging fills it in)')

    generator = read("gradle-plugin/build.gradle.kts")
    for source in (
        '"build.gradle.kts"',
        '"gradle.properties"',
        '"android/godot-plugin/build.gradle.kts"',
        '"android/godot-plugin/gradle.properties"',
        '"android/godot-plugin/scripts/build.gradle.kts"',
        'extra["kanamaPanamaPortCoreDependency"]',
    ):
        if source not in generator:
            failures.append(f"gradle-plugin/build.gradle.kts: KanamaVersions no longer reads {source}")

    if failures:
        for failure in failures:
            print(f"{TAG} FAIL {failure}")
        return 1
    print(f"{TAG} PASS the kit template, the Android modules, the smoke preset, the export guard and plugin.cfg agree with "
          f"their sources (Kotlin, KSP, Godot {godot}, PanamaPort, Min SDK {min_sdk}, Kanama {version})")
    return 0


if __name__ == "__main__":
    sys.exit(main())
