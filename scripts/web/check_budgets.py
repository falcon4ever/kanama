#!/usr/bin/env python3
"""Enforce the Web performance budgets (Task 60f, promotion criterion W4).

A support claim that rests on "it runs" is not worth much: it says nothing about
whether a demo still fits in a payload anyone will download, still starts, and
still keeps its per-tick work off the cross-module path as scenes grow. This
gate turns those into numbers with recorded baselines.

**Why the headline budget is per TICK, not per second.** Godot's Web main loop
is paced by requestAnimationFrame and advances a fixed step per iteration, so the
same build runs at wildly different rates on different hosts -- measured on one
day across four hosts: ~2x real time on a CI Chrome, ~2.7x on a macOS Firefox,
~3x on a macOS Chrome, and ~8.7x on a CI Firefox. Any budget denominated in wall
seconds would therefore be grading the host, and would swing by 4x on hardware
nobody controls. Crossings per engine tick is a property of the *backend*: it is
what the batching and snapshot strategy exist to bound, and it is the one number
that means the same thing everywhere.

A "tick" is one engine dispatch into the script layer, counting BOTH `_process`
and `_physics_process`. Counting render frames alone reported zero ticks for a
third of the corpus, because the character-controller, racing and third-person
demos do all their work in the physics tick and never touch `_process`.

Payload is in bytes, which is host-independent by construction. Startup is
wall-clock and therefore the weakest of the three, so its budget is deliberately
loose -- it exists to catch a demo that stops booting, not to grade a runner.

Usage:
    python3 check_budgets.py <result.json>            # gate one run
    python3 check_budgets.py --print                  # show the declared budgets
    python3 check_budgets.py <result.json> --report   # print measurements, never fail
"""

from __future__ import annotations

import argparse
import json
import os
import sys

BUDGETS_PATH = os.path.join(os.path.dirname(os.path.abspath(__file__)), "budgets.json")


class BudgetError(Exception):
    """Raised when a measured value exceeds its declared budget."""


def load_budgets(path: str = BUDGETS_PATH) -> dict:
    with open(path, encoding="utf-8") as handle:
        return json.load(handle)


def measurements(envelope: dict) -> dict:
    """Pull the budgeted numbers out of a result envelope."""
    performance = envelope.get("performance") or {}
    artifact = envelope.get("artifact") or {}
    startup = envelope.get("startup") or {}
    return {
        "payloadBytes": artifact.get("totalBytes"),
        "startupMs": startup.get("durationMs"),
        "crossingsPerTick": performance.get("crossingsPerTick"),
        "ticksObserved": performance.get("ticksObserved"),
        "simSeconds": performance.get("simSeconds"),
    }


def check(demo: str, measured: dict, budgets: dict) -> list[str]:
    """Return a list of human-readable budget violations (empty when within)."""
    # Fixtures are looked up after real demos and never silently: the scaffold
    # self-test drives a static fake export, which has no meaningful budget but
    # must still exercise this gate rather than skipping it.
    limits = (budgets.get("demos") or {}).get(demo) or (budgets.get("fixtures") or {}).get(demo)
    if limits is None:
        raise BudgetError(
            f"no budget declared for demo {demo!r}. Add one to budgets.json with a "
            f"measured baseline -- an unbudgeted demo is an unmeasured demo."
        )

    violations: list[str] = []

    payload = measured["payloadBytes"]
    if payload is None:
        violations.append("payload size was not reported")
    elif payload > limits["maxPayloadBytes"]:
        violations.append(
            f"payload {payload / 1e6:.1f} MB exceeds budget "
            f"{limits['maxPayloadBytes'] / 1e6:.1f} MB"
        )

    startup = measured["startupMs"]
    if startup is None:
        violations.append("startup duration was not reported")
    elif startup > limits["maxStartupMs"]:
        violations.append(
            f"startup {startup} ms exceeds budget {limits['maxStartupMs']} ms"
        )

    per_tick = measured["crossingsPerTick"]
    ticks = measured["ticksObserved"]
    if limits.get("maxCrossingsPerTick") is None:
        # An exempt demo must SAY WHY. A null budget with no reason is an
        # unbudgeted demo wearing a budget's clothes, so it is a hard error.
        reason = limits.get("tickRatioNotApplicable")
        if not reason:
            raise BudgetError(
                f"{demo}: maxCrossingsPerTick is null with no `tickRatioNotApplicable` "
                f"reason. An exemption without a stated reason is not an exemption."
            )
        print(f"web_export_smoke: {demo} crossings/tick not applicable -- {reason}")
    elif per_tick is None or ticks is None:
        # Not measured is never silently fine: the whole point of the budget is
        # that the number exists.
        violations.append(
            "crossings-per-tick was NOT MEASURED (no performance section in the "
            "envelope) -- the driver could not read the bridge counters"
        )
    elif ticks < limits.get("minTicksForVerdict", 30):
        violations.append(
            f"only {ticks} engine ticks observed; too few to judge crossings per tick "
            f"(need {limits.get('minTicksForVerdict', 30)})"
        )
    elif per_tick > limits["maxCrossingsPerTick"]:
        violations.append(
            f"crossings/tick {per_tick} exceeds budget {limits['maxCrossingsPerTick']} "
            f"(over {ticks} ticks) -- per-tick work is reaching the cross-module "
            f"path instead of being batched"
        )

    return violations


def main(argv: list[str]) -> int:
    parser = argparse.ArgumentParser(description="Check a Web smoke run against its budgets.")
    parser.add_argument("result", nargs="?", help="path to a result JSON envelope")
    parser.add_argument("--print", dest="print_budgets", action="store_true",
                        help="print the declared budgets and exit")
    parser.add_argument("--report", action="store_true",
                        help="print measurements without failing (baseline gathering)")
    args = parser.parse_args(argv)

    budgets = load_budgets()

    if args.print_budgets:
        for demo, limits in budgets["demos"].items():
            ratio = (
                f"crossings/tick <= {limits['maxCrossingsPerTick']}"
                if limits.get("maxCrossingsPerTick") is not None
                else "crossings/tick EXEMPT"
            )
            print(
                f"{demo}: payload <= {limits['maxPayloadBytes'] / 1e6:.0f} MB, "
                f"startup <= {limits['maxStartupMs']} ms, {ratio}"
            )
        return 0

    if not args.result:
        parser.error("a result JSON path is required unless --print is given")

    try:
        with open(args.result, encoding="utf-8") as handle:
            envelope = json.load(handle)
    except (OSError, json.JSONDecodeError) as error:
        print(f"web_export_smoke: could not read result {args.result}: {error}", file=sys.stderr)
        return 2

    demo = envelope.get("demo", "")
    measured = measurements(envelope)

    if args.report:
        payload = measured["payloadBytes"]
        print(
            f"budget report: {demo} "
            f"payload={payload / 1e6:.1f}MB "
            f"startup={measured['startupMs']}ms "
            f"crossings/tick={measured['crossingsPerTick']} "
            f"(ticks={measured['ticksObserved']}, sim={measured['simSeconds']}s)"
        )
        return 0

    try:
        violations = check(demo, measured, budgets)
    except BudgetError as error:
        print(f"web_export_smoke: budget error: {error}", file=sys.stderr)
        return 1

    if violations:
        print(f"web_export_smoke: {demo} is OVER BUDGET:", file=sys.stderr)
        for violation in violations:
            print(f"  - {violation}", file=sys.stderr)
        return 1

    limits = (budgets.get("demos") or {}).get(demo) or (budgets.get("fixtures") or {}).get(demo) or {}
    ratio = (
        f"{measured['crossingsPerTick']} crossings/tick"
        if limits.get("maxCrossingsPerTick") is not None
        else "crossings/tick exempt"
    )
    print(
        f"web_export_smoke: {demo} within budget "
        f"(payload {measured['payloadBytes'] / 1e6:.1f}MB, "
        f"startup {measured['startupMs']}ms, {ratio})"
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv[1:]))
