#!/usr/bin/env python3
"""Fail a Web smoke run whose browser is below the declared version floor.

A green gate is only evidence about the browser it ran on. Without this check a
run on an unvalidated (older) browser produces the same PASS line as a run on a
declared one, and the distinction is lost the moment the log scrolls by.

The floors live in ``browser_floors.json`` -- one file, quoted by the docs rather
than restated in them, so a floor can never be raised in prose and left unchanged
in the gate.

Two things fail here, both deliberately loud:

* the driven version is below the engine's declared ``minimum``; and
* the version cannot be read out of the recorded user-agent at all, which means
  the evidence does not say what it ran on.

Usage:
    python3 check_browser_floor.py <result.json>
    python3 check_browser_floor.py --engine chrome --version 129.0.6668.100
    python3 check_browser_floor.py --print-floors
"""

from __future__ import annotations

import argparse
import json
import os
import sys

from browser_version import parse_version, version_tuple

FLOORS_PATH = os.path.join(os.path.dirname(os.path.abspath(__file__)), "browser_floors.json")


class FloorError(Exception):
    """Raised when a run is below floor or its browser cannot be identified."""


def load_floors(path: str = FLOORS_PATH) -> dict:
    with open(path, encoding="utf-8") as handle:
        return json.load(handle)


def check(engine: str, version: str | None, floors: dict) -> str:
    """Return the accepted version, or raise FloorError."""
    entry = (floors.get("engines") or {}).get(engine)
    if entry is None:
        raise FloorError(f"no declared floor for engine {engine!r}; add one to browser_floors.json")
    if not version:
        raise FloorError(
            f"{engine}: could not read a version from the recorded user-agent -- "
            "the result does not say which browser produced it"
        )
    minimum = entry["minimum"]
    if version_tuple(version) < version_tuple(minimum):
        raise FloorError(
            f"{engine} {version} is below the declared floor {minimum} "
            f"(basis: {entry['basis']}). Either run a newer browser or, if this "
            f"version is genuinely supported, re-validate and lower the floor in "
            f"browser_floors.json with dated evidence."
        )
    return version


def check_result_file(path: str, floors: dict) -> tuple[str, str]:
    with open(path, encoding="utf-8") as handle:
        envelope = json.load(handle)
    browser = envelope.get("browser") or {}
    engine = browser.get("engine")
    if not engine:
        raise FloorError(f"{path}: result envelope has no browser.engine")
    # The envelope records the raw user-agent; the version is read from it here
    # with the same parser the matrix summary uses.
    version = parse_version(engine, browser.get("version"))
    return engine, check(engine, version, floors)


def main(argv: list[str]) -> int:
    parser = argparse.ArgumentParser(description="Check a Web smoke run against the browser floors.")
    parser.add_argument("result", nargs="?", help="path to a result JSON envelope")
    parser.add_argument("--engine", help="check an explicit engine instead of a result file")
    parser.add_argument("--version", help="version to check with --engine (dotted, or a user-agent)")
    parser.add_argument("--print-floors", action="store_true", help="print the declared floors")
    args = parser.parse_args(argv)

    floors = load_floors()

    if args.print_floors:
        for engine, entry in floors["engines"].items():
            print(f"{engine}: >= {entry['minimum']} ({entry['basis']})")
        return 0

    try:
        if args.engine:
            if not args.version:
                parser.error("--engine requires --version")
            # --version takes either a dotted version or a whole user-agent. A
            # string that is neither must not be smuggled through as a version:
            # it would compare as "below floor" and hide that we cannot tell.
            version = parse_version(args.engine, args.version)
            if version is None and version_tuple(args.version):
                version = args.version
            accepted = check(args.engine, version, floors)
            engine = args.engine
        else:
            if not args.result:
                parser.error("a result JSON path is required unless --engine or --print-floors is given")
            engine, accepted = check_result_file(args.result, floors)
    except FloorError as error:
        print(f"web_export_smoke: browser floor violation: {error}", file=sys.stderr)
        return 1
    except (OSError, json.JSONDecodeError) as error:
        print(f"web_export_smoke: could not read floors or result: {error}", file=sys.stderr)
        return 2

    minimum = floors["engines"][engine]["minimum"]
    print(f"web_export_smoke: {engine} {accepted} satisfies the declared floor {minimum}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv[1:]))
