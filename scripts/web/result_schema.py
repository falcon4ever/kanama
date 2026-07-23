#!/usr/bin/env python3
"""Versioned Web export-smoke result envelope and its fail-loud validator.

Task 57f formalizes the ad-hoc Task-57 per-browser result JSON into a single
machine-readable envelope shared by the Chrome, Firefox, and Safari drivers.
Every driver writes this envelope; ``scripts/web_export_smoke.sh`` validates it
before a browser may be declared green.

The validator is deliberately strict: a missing required field or a skipped
assertion is a harness failure, not a warning. A run is never green from page
load alone -- ``startup.loaded`` is necessary but never sufficient.

Usage:
    python3 result_schema.py <result.json>          # validate, exit non-zero on failure
    python3 result_schema.py --print-version        # emit the current schema version
"""

from __future__ import annotations

import argparse
import json
import sys
from numbers import Real
from typing import Any, Callable

# Bump when the envelope shape changes in a way drivers must follow.
SCHEMA_VERSION = 1

# Browser engines that may report a result. Each is validated identically; the
# smoke shell decides which are CI gates (Chrome) vs local release gates.
KNOWN_ENGINES = ("chrome", "firefox", "safari")


class SchemaError(Exception):
    """Raised when a result envelope violates the schema."""


def _require(condition: bool, message: str) -> None:
    if not condition:
        raise SchemaError(message)


def _get(mapping: Any, key: str, path: str) -> Any:
    _require(isinstance(mapping, dict), f"{path} must be an object")
    _require(key in mapping, f"{path}.{key} is required")
    value = mapping[key]
    _require(value is not None, f"{path}.{key} must not be null (skipped fields fail the harness)")
    return value


def _check_type(value: Any, kind: type | tuple[type, ...], path: str) -> Any:
    # bool is a subclass of int; guard against it leaking into numeric fields.
    if kind in (int, Real) and isinstance(value, bool):
        raise SchemaError(f"{path} must be numeric, not a boolean")
    _require(isinstance(value, kind), f"{path} has the wrong type")
    return value


def _non_negative(value: Any, path: str) -> Real:
    _check_type(value, Real, path)
    _require(value >= 0, f"{path} must be non-negative")
    return value


def _validate_artifact(artifact: Any) -> None:
    path = "artifact"
    _check_type(_get(artifact, "url", path), str, f"{path}.url")
    files = _get(artifact, "files", path)
    _check_type(files, list, f"{path}.files")
    _require(len(files) > 0, f"{path}.files must list at least one served payload file")
    for index, entry in enumerate(files):
        entry_path = f"{path}.files[{index}]"
        _check_type(_get(entry, "name", entry_path), str, f"{entry_path}.name")
        _non_negative(_get(entry, "bytes", entry_path), f"{entry_path}.bytes")
    _non_negative(_get(artifact, "totalBytes", path), f"{path}.totalBytes")
    # Source-tree immutability evidence: a checksum the shell can compare
    # against the pre-run source tree to prove the export did not mutate it.
    _check_type(_get(artifact, "sourceTreeChecksum", path), str, f"{path}.sourceTreeChecksum")


def _validate_browser(browser: Any) -> None:
    path = "browser"
    engine = _get(browser, "engine", path)
    _require(
        engine in KNOWN_ENGINES,
        f"{path}.engine must be one of {KNOWN_ENGINES}, got {engine!r}",
    )
    _check_type(_get(browser, "name", path), str, f"{path}.name")
    _check_type(_get(browser, "version", path), str, f"{path}.version")


def _validate_startup(startup: Any) -> None:
    path = "startup"
    _check_type(_get(startup, "loaded", path), bool, f"{path}.loaded")
    _check_type(_get(startup, "outcome", path), str, f"{path}.outcome")
    _non_negative(_get(startup, "durationMs", path), f"{path}.durationMs")


def _validate_assertions(assertions: Any) -> None:
    path = "assertions"
    summary = _get(assertions, "summary", path)
    total = _non_negative(_get(summary, "total", f"{path}.summary"), f"{path}.summary.total")
    passed = _non_negative(_get(summary, "passed", f"{path}.summary"), f"{path}.summary.passed")
    failed = _non_negative(_get(summary, "failed", f"{path}.summary"), f"{path}.summary.failed")
    skipped = _non_negative(_get(summary, "skipped", f"{path}.summary"), f"{path}.summary.skipped")
    _require(total > 0, f"{path}.summary.total must be > 0 (a page-load-only run is never green)")
    _require(skipped == 0, f"{path}.summary.skipped must be 0; skipped assertions fail the harness")
    _require(
        passed + failed == total,
        f"{path}.summary passed+failed ({passed}+{failed}) must equal total ({total})",
    )
    checks = _get(assertions, "checks", path)
    _check_type(checks, dict, f"{path}.checks")
    _require(len(checks) > 0, f"{path}.checks must be non-empty")
    observed_passed = 0
    for name, value in checks.items():
        _require(
            isinstance(value, bool),
            f"{path}.checks.{name} must be a boolean (a skipped/incomplete check fails the harness)",
        )
        observed_passed += 1 if value else 0
    _require(
        observed_passed == passed and len(checks) == total,
        f"{path}.checks must be consistent with the summary "
        f"(checks={len(checks)}/passed={observed_passed} vs total={total}/passed={passed})",
    )


def _validate_handles(handles: Any) -> None:
    path = "handles"
    _non_negative(_get(handles, "liveAfterGameplay", path), f"{path}.liveAfterGameplay")
    # Teardown must return the handle registry to baseline.
    live_after_teardown = _non_negative(
        _get(handles, "liveAfterTeardown", path), f"{path}.liveAfterTeardown"
    )
    _require(
        live_after_teardown == 0,
        f"{path}.liveAfterTeardown must be 0 after full teardown, got {live_after_teardown}",
    )
    # Stale-handle rejection is a required, exercised invariant.
    _non_negative(_get(handles, "staleRejected", path), f"{path}.staleRejected")


def _validate_counter_group(mapping: Any, path: str, keys: tuple[str, ...]) -> None:
    for key in keys:
        _non_negative(_get(mapping, key, path), f"{path}.{key}")


def _validate_console(console: Any) -> None:
    path = "console"
    errors = _get(console, "errors", path)
    _check_type(errors, list, f"{path}.errors")
    boundary = _get(console, "boundaryErrors", path)
    _check_type(boundary, list, f"{path}.boundaryErrors")


def _validate_teardown(teardown: Any) -> None:
    path = "teardown"
    _check_type(_get(teardown, "outcome", path), str, f"{path}.outcome")
    _check_type(
        _get(teardown, "ownerRegistriesToBaseline", path),
        bool,
        f"{path}.ownerRegistriesToBaseline",
    )


_TOP_LEVEL: tuple[tuple[str, Callable[[Any], None]], ...] = (
    ("artifact", _validate_artifact),
    ("browser", _validate_browser),
    ("startup", _validate_startup),
    ("assertions", _validate_assertions),
    ("handles", _validate_handles),
    ("console", _validate_console),
    ("teardown", _validate_teardown),
)


def validate(envelope: Any) -> None:
    """Validate a parsed result envelope, raising SchemaError on the first fault."""
    _require(isinstance(envelope, dict), "result envelope must be a JSON object")

    version = _get(envelope, "schemaVersion", "envelope")
    _check_type(version, int, "schemaVersion")
    _require(
        version == SCHEMA_VERSION,
        f"schemaVersion must be {SCHEMA_VERSION}, got {version}",
    )

    demo = _get(envelope, "demo", "envelope")
    _check_type(demo, str, "demo")
    _require(demo != "", "demo must be non-empty")

    protocol = _get(envelope, "protocolVersion", "envelope")
    _check_type(protocol, int, "protocolVersion")
    _require(protocol > 0, "protocolVersion must be positive")

    _non_negative(_get(envelope, "durationMs", "envelope"), "durationMs")

    for key, validator in _TOP_LEVEL:
        validator(_get(envelope, key, "envelope"))

    # Crossing counters and the lifecycle registries (callbacks, connections,
    # scheduler) are required groups; each driver reports the counters relevant
    # to its demo, but the group itself must be present and numeric.
    _validate_counter_group(_get(envelope, "crossings", "envelope"), "crossings", ())
    crossings = envelope["crossings"]
    _require(isinstance(crossings, dict) and len(crossings) > 0, "crossings must be a non-empty object")
    for name, value in crossings.items():
        _non_negative(value, f"crossings.{name}")

    for group in ("callbacks", "connections", "scheduler"):
        mapping = _get(envelope, group, "envelope")
        _check_type(mapping, dict, group)
        for name, value in mapping.items():
            _non_negative(value, f"{group}.{name}")

    # An overall pass mirror must agree with the assertion summary.
    overall = _get(envelope, "pass", "envelope")
    _check_type(overall, bool, "pass")
    summary = envelope["assertions"]["summary"]
    expected_pass = summary["failed"] == 0 and envelope["startup"]["loaded"] is True
    _require(
        overall == expected_pass,
        f"pass ({overall}) must reflect startup.loaded and zero failed assertions ({expected_pass})",
    )


def main(argv: list[str]) -> int:
    parser = argparse.ArgumentParser(description="Validate a Web export-smoke result envelope.")
    parser.add_argument("result", nargs="?", help="path to the result JSON envelope")
    parser.add_argument(
        "--print-version",
        action="store_true",
        help="print the current schema version and exit",
    )
    args = parser.parse_args(argv)

    if args.print_version:
        print(SCHEMA_VERSION)
        return 0

    if not args.result:
        parser.error("a result JSON path is required unless --print-version is given")

    try:
        with open(args.result, encoding="utf-8") as handle:
            envelope = json.load(handle)
    except (OSError, json.JSONDecodeError) as error:
        print(f"web_export_smoke: could not read result {args.result}: {error}", file=sys.stderr)
        return 2

    try:
        validate(envelope)
    except SchemaError as error:
        print(f"web_export_smoke: result schema violation in {args.result}: {error}", file=sys.stderr)
        return 1

    print(f"web_export_smoke: result {args.result} satisfies schema v{SCHEMA_VERSION}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv[1:]))
