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
import os
import sys
from numbers import Real
from typing import Any, Callable

# Bump when the envelope shape changes in a way drivers must follow.
SCHEMA_VERSION = 1

# Task 81 (census-as-gate): the per-demo required registered-member lists. A demo
# named there with a non-empty "required" list FAILS validation unless every listed
# member appears in the envelope's exercisedMembers census -- a driver asserting
# frame counters is no longer exactly as green as one asserting an enemy died.
REQUIRED_MEMBERS_FILE = os.path.join(os.path.dirname(os.path.abspath(__file__)), "required_members.json")

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
    warnings = _get(console, "warnings", path)
    _check_type(warnings, list, f"{path}.warnings")
    boundary = _get(console, "boundaryErrors", path)
    _check_type(boundary, list, f"{path}.boundaryErrors")


def _validate_performance(performance: Any) -> None:
    """Validate the optional performance section (Task 60f).

    Optional on purpose: a driver that could not ask the page (it had already torn
    down) must not fail the schema for it. The budget gate reports an absent
    section as "not measured" rather than passing it silently, which is the only
    treatment that keeps a missing measurement from looking like a good one.
    """
    path = "performance"
    for key in ("ticksObserved", "processTicks", "physicsTicks", "kotlinToGodotCalls",
                "appliedCommands"):
        _non_negative(_get(performance, key, path), f"{path}.{key}")
    for key in ("simSeconds", "crossingsPerTick"):
        _non_negative(_get(performance, key, path), f"{path}.{key}")


def _validate_teardown(teardown: Any) -> None:
    path = "teardown"
    _check_type(_get(teardown, "outcome", path), str, f"{path}.outcome")
    _check_type(
        _get(teardown, "ownerRegistriesToBaseline", path),
        bool,
        f"{path}.ownerRegistriesToBaseline",
    )


def _validate_exercised_members(section: Any) -> None:
    """Validate the exercised-member census section's shape (Task 81).

    Optional in the envelope shape (a torn-down page cannot be asked), but a demo
    with a non-empty required-member list fails validation without it -- absence
    must never be softer than presence for a gated demo.
    """
    path = "exercisedMembers"
    _check_type(section, dict, path)
    for script, members in section.items():
        script_path = f"{path}.{script}"
        _check_type(members, dict, script_path)
        for member, count in members.items():
            member_path = f"{script_path}.{member}"
            _check_type(count, int, member_path)
            _require(count >= 1, f"{member_path} must be a positive dispatch count")


def load_required_members(path: str = REQUIRED_MEMBERS_FILE) -> dict[str, Any]:
    """Load and validate the per-demo required registered-member lists (Task 81).

    The file is part of the gate: unreadable or malformed means every validation
    fails, because a silently unenforced requirement is exactly the failure mode
    this gate exists to close. Rows are objects with a `member` ("Class.name",
    suffix-matched against the census's script class names) and a `reason`; a
    demo's `todo` rows are honesty markers for members the current driver or the
    dispatch boundary cannot reach -- documented gaps, never silent omissions.
    """
    try:
        with open(path, encoding="utf-8") as handle:
            data = json.load(handle)
    except (OSError, json.JSONDecodeError) as error:
        raise SchemaError(f"required-members file {path} is unreadable: {error}") from error
    _require(isinstance(data, dict), f"{path} must contain a JSON object")
    _require(data.get("schemaVersion") == 1, f"{path} schemaVersion must be 1")
    demos = data.get("demos")
    _require(isinstance(demos, dict), f"{path} must map 'demos' to an object")
    for demo, entry in demos.items():
        _require(isinstance(entry, dict), f"{path} demos.{demo} must be an object")
        for key in entry:
            _require(
                key in ("required", "todo"),
                f"{path} demos.{demo} has unknown key {key!r} (expected 'required'/'todo')",
            )
        for kind in ("required", "todo"):
            rows = entry.get(kind, [])
            _require(isinstance(rows, list), f"{path} demos.{demo}.{kind} must be a list")
            for index, row in enumerate(rows):
                row_path = f"{path} demos.{demo}.{kind}[{index}]"
                _require(isinstance(row, dict), f"{row_path} must be an object")
                member = row.get("member")
                _require(
                    isinstance(member, str) and "." in member,
                    f"{row_path}.member must be a 'Class.member' string",
                )
                reason = row.get("reason")
                _require(
                    isinstance(reason, str) and reason.strip() != "",
                    f"{row_path}.reason must be a non-empty string",
                )
    return demos


def _enforce_required_members(envelope: Any, demos: dict[str, Any]) -> None:
    """Fail the run when a required registered member did not dispatch (Task 81)."""
    demo = envelope["demo"]
    required = demos.get(demo, {}).get("required", [])
    if not required:
        return
    _require(
        "exercisedMembers" in envelope and envelope["exercisedMembers"] is not None,
        f"demo {demo!r} has required registered members but the envelope carries no "
        "exercisedMembers census (the driver could not read the bridge, or predates the gate)",
    )
    exercised = envelope["exercisedMembers"]
    missing: list[str] = []
    for row in required:
        target = row["member"]
        class_part, member_name = target.rsplit(".", 1)
        scripts = [
            name for name in exercised if name == class_part or name.endswith(f".{class_part}")
        ]
        if any(exercised[name].get(member_name, 0) >= 1 for name in scripts):
            continue
        if scripts:
            observed = sorted({key for name in scripts for key in exercised[name]})
            missing.append(f"{target} (script matched; exercised members: {observed})")
        else:
            missing.append(f"{target} (no script matching {class_part!r} dispatched at all)")
    if missing:
        raw_keys = sorted(
            {
                key
                for members in exercised.values()
                for key in members
                if key.startswith("method#")
            }
        )
        hint = (
            f"; unresolved keys {raw_keys} suggest the export predates the "
            "kanama-web/KanamaWebProtocol.generated.json copy -- rebuild the export"
            if raw_keys
            else ""
        )
        raise SchemaError(
            f"demo {demo!r} required registered member(s) did not run: "
            + "; ".join(missing)
            + hint
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


def validate(envelope: Any, required_members: dict[str, Any] | None = None) -> None:
    """Validate a parsed result envelope, raising SchemaError on the first fault.

    ``required_members`` defaults to the committed ``required_members.json`` next to
    this script; passing a mapping is for tests. The gate is default-on: a caller
    cannot forget it.
    """
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

    # Optional sections are validated when present and never required.
    if "performance" in envelope and envelope["performance"] is not None:
        _validate_performance(envelope["performance"])

    # Task 81: shape-check the census whenever present, then enforce the per-demo
    # required lists (which also fail a GATED demo whose census is absent).
    if "exercisedMembers" in envelope and envelope["exercisedMembers"] is not None:
        _validate_exercised_members(envelope["exercisedMembers"])
    _enforce_required_members(
        envelope, required_members if required_members is not None else load_required_members()
    )

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
