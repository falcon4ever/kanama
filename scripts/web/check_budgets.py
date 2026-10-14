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

Payload is in bytes, which is host- and engine-independent by construction.

**Startup is recorded but NOT gated.** It is wall-clock and it does not survive a
loaded machine: the same thirdperson build measured 4.4s on an idle Chrome and
64.7s on a Firefox sharing a busy workstation. A gate that red-lights because
something else was compiling is measuring the machine, and the failure it would
have caught -- a demo that stops booting -- already surfaces as a driver timeout.

**Crossings per tick is gated PER ENGINE.** It is engine-stable across most of the
corpus (chrome/firefox: bunnymark 2.22/1.97, dodge 0.19/0.20, fps 1.10/1.02) but
not for the input-heavy demos, where Firefox does several times the boundary work
(charactercontroller 4.50/19.58, thirdperson 1.15/6.01). A Chrome-derived number
gating a Firefox run is how this was found: it reddened main.

Usage:
    python3 check_budgets.py <result.json>            # gate one run
    python3 check_budgets.py --print                  # show the declared budgets
    python3 check_budgets.py <result.json> --report   # print measurements, never fail
    python3 check_budgets.py --self-test              # recorded envelopes against the shipped builtin-call ceilings
"""

from __future__ import annotations

import argparse
import contextlib
import io
import json
import os
import sys

BUDGETS_PATH = os.path.join(os.path.dirname(os.path.abspath(__file__)), "budgets.json")


class BudgetError(Exception):
    """Raised when a measured value exceeds its declared budget."""


def load_budgets(path: str = BUDGETS_PATH) -> dict:
    with open(path, encoding="utf-8") as handle:
        return json.load(handle)


BUILTIN_KEYS = ("maxBuiltinCallsPerTick", "maxBuiltinCallsPerSimSecond")


def engine_limits(limits: dict, engine: str | None) -> dict:
    """Merge a demo's per-engine budget over its demo-level one.

    Crossings-per-tick is engine-stable for most of the corpus but emphatically not
    for the input-heavy demos -- charactercontroller measured 4.50 on Chrome and
    19.58 on Firefox for the same build -- so a Chrome-derived number has no
    business gating a Firefox run. It did, and it reddened main.

    The builtin-call ceilings are one kind or the other (per tick, or per simulated second, which
    replaces the per-tick cap): an engine that names one kind drops the demo-level ceiling of the other.
    """
    merged = dict(limits)
    per_engine = (limits.get("engines") or {}).get(engine or "")
    if per_engine:
        for key in ("maxCrossingsPerTick", "minTicksForVerdict", "tickRatioNotApplicable", *BUILTIN_KEYS):
            if key in per_engine:
                merged[key] = per_engine[key]
        named = [key for key in BUILTIN_KEYS if key in per_engine]
        if len(named) == 1:
            merged.pop(next(key for key in BUILTIN_KEYS if key != named[0]), None)
    return merged


def measurements(envelope: dict) -> dict:
    """Pull the budgeted numbers out of a result envelope."""
    performance = envelope.get("performance") or {}
    artifact = envelope.get("artifact") or {}
    startup = envelope.get("startup") or {}
    return {
        "payloadBytes": artifact.get("totalBytes"),
        "startupMs": startup.get("durationMs"),
        "crossingsPerTick": performance.get("crossingsPerTick"),
        "builtinCalls": performance.get("builtinCalls"),
        "builtinCallsPerTick": performance.get("builtinCallsPerTick"),
        "ticksObserved": performance.get("ticksObserved"),
        "simSeconds": performance.get("simSeconds"),
    }


def check(demo: str, measured: dict, budgets: dict, engine: str | None = None) -> list[str]:
    """Return a list of human-readable budget violations (empty when within)."""
    # Fixtures are looked up after real demos and never silently: the scaffold
    # self-test drives a static fake export, which has no meaningful budget but
    # must still exercise this gate rather than skipping it.
    limits = (budgets.get("demos") or {}).get(demo) or (budgets.get("fixtures") or {}).get(demo)
    if limits is not None:
        limits = engine_limits(limits, engine)
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

    per_tick = measured["crossingsPerTick"]
    ticks = measured["ticksObserved"]

    # An engine can be RECORD-ONLY (task 86): its crossings numbers are printed and
    # recorded but gate nothing, with the reason stated. This is for Safari, whose
    # ceilings were never measured -- `engine_limits` would otherwise silently grade
    # it against the demo-level (chrome-era) number. Payload still gates above: it
    # is a property of the export, not the engine. The reason requirement mirrors
    # `tickRatioNotApplicable`: an exemption without a stated reason is not one.
    engine_policy = (budgets.get("enginePolicies") or {}).get(engine or "")
    if engine_policy is not None and "recordOnly" in (engine_policy or {}):
        reason = engine_policy["recordOnly"]
        if not reason:
            raise BudgetError(
                f"engine {engine!r} is recordOnly with no stated reason. An exemption "
                f"without a stated reason is not an exemption."
            )
        print(
            f"web_export_smoke: {demo} crossings/tick RECORDED, not gated, on "
            f"{engine} (crossings/tick={per_tick} ticks={ticks}) -- {reason}"
        )
        # The builtin / utility call count is a property of the program, not of how this engine paces
        # requestAnimationFrame (that pacing is why crossings/tick is record-only here), so it is still gated
        # with the demo's own ceilings (task 138 item 33 review N6).
        violations += check_builtin_calls(demo, measured, budgets, limits)
        return violations

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

    violations += check_builtin_calls(demo, measured, budgets, limits)
    return violations


def builtin_gate(demo: str, limits: dict, budgets: dict) -> tuple[str, float | None]:
    """How a demo's synchronous builtin / utility calls are graded: `("tick", ceiling)`,
    `("sim-second", ceiling)` or `("exempt", None)`.

    Every demo has the per-TICK cap (`defaultMaxBuiltinCallsPerTick`, or its own
    `maxBuiltinCallsPerTick`). A demo whose calls follow SIMULATED time instead declares
    `maxBuiltinCallsPerSimSecond`, which REPLACES the per-tick cap for it (see check_builtin_calls).
    A demo whose tick ratio is exempt (an input-driven run, `tickRatioNotApplicable`) is exempt here too
    unless it declares a ceiling.
    """
    if "maxBuiltinCallsPerTick" in limits and "maxBuiltinCallsPerSimSecond" in limits:
        raise BudgetError(
            f"{demo}: declares both maxBuiltinCallsPerTick and maxBuiltinCallsPerSimSecond. The per-simulated-second "
            f"ceiling replaces the per-tick cap for a demo whose calls follow simulated time; declare one."
        )
    if "maxBuiltinCallsPerSimSecond" in limits:
        return "sim-second", limits["maxBuiltinCallsPerSimSecond"]
    if "maxBuiltinCallsPerTick" in limits:
        return "tick", limits["maxBuiltinCallsPerTick"]
    if limits.get("maxCrossingsPerTick") is None:
        return "exempt", None
    ceiling = budgets.get("defaultMaxBuiltinCallsPerTick")
    if ceiling is None:
        raise BudgetError(f"{demo}: no maxBuiltinCallsPerTick and no defaultMaxBuiltinCallsPerTick in budgets.json")
    return "tick", ceiling


def check_builtin_calls(demo: str, measured: dict, budgets: dict, limits: dict) -> list[str]:
    """Task 129 E review B1: immediate builtin / utility crossings, per tick (or per simulated second).

    A value-type method or GD utility the engine runs is one synchronous bridge round trip
    (10-30 us), not a batched command, so `crossingsPerTick` never sees it. Every demo is gated, by
    the per-tick cap unless it declares a per-simulated-second ceiling (per engine like the crossings).

    Why the per-tick cap stays (task 138 item 33 review): per tick is what caught the fps regression
    (1.06 calls per tick against the 0.5 cap, 2.1x), and most demos' calls are a fixed startup count or a
    small rate per dispatch, so a per-second figure reads how long the run was (web3d: 87 calls whether the
    run is 1.2 s of game time on Firefox or 2.7 s on Chrome, so 72.5 vs 31.8 per second, 0.09 vs 0.06 per tick).

    Why bunnymark uses simulated seconds instead: its calls are GD.randf draws on a spawn, which follows
    SIMULATED time, while a fixed-length run's tick count does not -- a slower host advances more simulated
    time per tick (a larger delta), so calls per TICK rose with load (the old 10.5 ceiling tripped at ~29
    ticks per simulated second; a Mac runs ~55) while calls per simulated SECOND stayed within ~10%
    (283-315 over twelve Chrome and Firefox runs). `builtinCalls / simSeconds` comes straight from the
    envelope's performance section.
    """
    kind, ceiling = builtin_gate(demo, limits, budgets)
    if kind == "exempt":
        print(f"web_export_smoke: {demo} builtin calls EXEMPT (no per-tick denominator) -- {limits.get('tickRatioNotApplicable')}")
        return []
    calls = measured.get("builtinCalls")
    sim = measured.get("simSeconds")
    ticks = measured.get("ticksObserved")
    if kind == "tick":
        per_tick = measured.get("builtinCallsPerTick")
        if per_tick is None:
            return [
                "builtin calls per tick were NOT MEASURED (no builtinCallsPerTick in the performance "
                "section) -- the driver could not read the bridge's builtinCalls counter"
            ]
        if ticks is not None and ticks < limits.get("minTicksForVerdict", 30):
            return []  # the crossings check above already reports too few ticks
        if per_tick > ceiling:
            return [
                f"builtin calls/tick {per_tick} exceeds budget {ceiling} (over {ticks} ticks) -- "
                f"a per-tick value-type method or GD utility is crossing to the engine instead of "
                f"running in Kotlin"
            ]
        return []
    if calls is None or sim is None:
        return [
            "builtin calls per simulated second were NOT MEASURED (no builtinCalls / simSeconds in the "
            "performance section) -- the driver could not read the bridge's counters"
        ]
    if ticks is not None and ticks < limits.get("minTicksForVerdict", 30):
        return []  # the crossings check above already reports too few ticks
    floor = budgets.get("minSimSecondsForVerdict", 0.25)
    if sim < floor:
        return [
            f"only {sim}s of simulated time observed; too few to judge builtin calls per simulated "
            f"second (need {floor}s)"
        ]
    rate = calls / sim
    if rate > ceiling:
        return [
            f"builtin calls/sim-second {rate:.2f} exceeds budget {ceiling} ({calls} calls over "
            f"{sim}s simulated, {ticks} ticks) -- a value-type method or GD utility is "
            f"crossing to the engine more often than the demo's simulated time explains"
        ]
    return []


def _per_sim_second(measured: dict) -> str:
    """The builtin-call rate per simulated second for a report line, `unmeasured` when the envelope lacks the inputs."""
    calls, sim = measured.get("builtinCalls"), measured.get("simSeconds")
    if calls is None or not sim:
        return "unmeasured"
    return f"{calls / sim:.2f}"


def builtin_summary(demo: str, measured: dict, budgets: dict, limits: dict) -> str:
    """The builtin-call part of the 'within budget' line: the gated rate and its ceiling, or `exempt`."""
    kind, ceiling = builtin_gate(demo, limits, budgets)
    if kind == "exempt":
        return "builtin calls exempt"
    if kind == "sim-second":
        return f"{_per_sim_second(measured)} builtin calls/sim-second (<= {ceiling})"
    return f"{measured['builtinCallsPerTick']} builtin calls/tick (<= {ceiling})"


def _builtin_measured(calls: int, ticks: int, sim: float) -> dict:
    return {
        "builtinCalls": calls,
        "ticksObserved": ticks,
        "simSeconds": sim,
        "builtinCallsPerTick": round(calls / ticks, 2) if ticks else 0,
    }


def run_self_test(budgets: dict) -> int:
    """The builtin-call rules against the SHIPPED ceilings and the recorded envelopes (task 138 item 33 review):
    the pre-fix regressions stay red with margin, the corpus's measured runs stay green on both engines."""
    failures: list[str] = []

    def builtin_verdict(demo: str, engine: str, measured: dict) -> list[str]:
        limits = engine_limits((budgets["demos"] or {}).get(demo) or (budgets.get("fixtures") or {})[demo], engine)
        return check_builtin_calls(demo, measured, budgets, limits)

    def expect(name: str, violations: list[str], red: bool) -> None:
        if bool(violations) != red:
            failures.append(f"{name}: expected {'red' if red else 'green'}, got {violations or 'green'}")

    # Pre-fix envelopes (recorded 2026-10-14, Chrome 155, before the Web-local utilities): red by a wide margin.
    default_tick = budgets["defaultMaxBuiltinCallsPerTick"]
    for demo, calls, ticks, sim in (("fps", 408, 386, 48.92), ("racing", 4988, 541, 18.87), ("citybuilder", 372, 716, 36.42), ("tpsdemo", 272, 453, 26.42)):
        measured = _builtin_measured(calls, ticks, sim)
        expect(f"pre-fix {demo} ({measured['builtinCallsPerTick']} per tick)", builtin_verdict(demo, "chrome", measured), red=True)
    fps_prefix = _builtin_measured(408, 386, 48.92)["builtinCallsPerTick"]
    if fps_prefix < 2 * default_tick:
        failures.append(f"the pre-fix fps envelope ({fps_prefix} per tick) must exceed the per-tick cap {default_tick} by 2x or more")
    # Measured runs of the shipped build (macOS arm64, Chrome 155 / Firefox 157, 2026-10-14, after the simSeconds
    # fix): green on both engines.
    for demo, engine, calls, ticks, sim in (
        ("fps", "chrome", 93, 464, 2.93), ("fps", "chrome", 122, 497, 62.22), ("fps", "firefox", 65, 426, 0.83),
        ("web3d", "chrome", 87, 1447, 2.74), ("web3d", "firefox", 87, 974, 1.2),
        ("dodge", "chrome", 64, 4003, 10.57), ("dodge", "firefox", 40, 3634, 10.8), ("squash", "firefox", 18, 2150, 3.17),
        ("tpsdemo", "firefox", 30, 1008, 2.36),
        ("bunnymark", "chrome", 1278, 186, 4.06), ("bunnymark", "chrome", 979, 192, 3.46), ("bunnymark", "firefox", 1009, 191, 3.27),
        # a slow CI host: ~30 ticks per simulated second (166 ticks over 5.54 s) at the same 300 calls per second
        ("bunnymark", "chrome", 1662, 166, 5.54),
    ):
        expect(f"{demo} on {engine} ({calls} calls, {ticks} ticks, {sim}s)", builtin_verdict(demo, engine, _builtin_measured(calls, ticks, sim)), red=False)
    # bunnymark is graded per simulated second: twice the measured rate is red, and the per-tick cap is NOT applied to it.
    expect("bunnymark at 2.2x its measured rate", builtin_verdict("bunnymark", "chrome", _builtin_measured(2300, 191, 3.45)), red=True)
    # a demo without a per-simulated-second ceiling keeps the per-tick cap whatever its rate per second
    expect("fps over the per-tick cap on a slow host", builtin_verdict("fps", "chrome", _builtin_measured(300, 300, 600.0)), red=True)
    expect("too little simulated time", builtin_verdict("bunnymark", "chrome", _builtin_measured(10, 191, 0.1)), red=True)
    expect("unmeasured calls", builtin_verdict("bunnymark", "chrome", {"builtinCalls": None, "simSeconds": 3.0, "ticksObserved": 191, "builtinCallsPerTick": None}), red=True)
    expect("unmeasured calls per tick", builtin_verdict("fps", "chrome", {"builtinCalls": None, "simSeconds": 3.0, "ticksObserved": 191, "builtinCallsPerTick": None}), red=True)
    # exempt demos are exempt, and say so
    if builtin_gate("match3", engine_limits(budgets["demos"]["match3"], "chrome"), budgets)[0] != "exempt":
        failures.append("match3 (input-driven, tickRatioNotApplicable) must be exempt from the builtin-call cap")
    # a record-only engine still has its builtin calls graded (the count is the program's, not the engine's pacing)
    record_only = [name for name, policy in (budgets.get("enginePolicies") or {}).items() if "recordOnly" in policy]
    for engine in record_only:
        with contextlib.redirect_stdout(io.StringIO()):  # the "RECORDED, not gated" line is the behaviour under test, not output
            violations = check("fps", {**_builtin_measured(408, 386, 48.92), "payloadBytes": 1, "crossingsPerTick": 99.0}, budgets, engine)
        expect(f"fps pre-fix builtin calls on record-only {engine}", violations, red=True)
        if any("crossings" in v for v in violations):
            failures.append(f"record-only {engine}: crossings/tick must not be graded, got {violations}")
    # one kind of ceiling per demo and engine
    both_declared_raised = False
    try:
        builtin_gate("x", {"maxBuiltinCallsPerTick": 1.0, "maxBuiltinCallsPerSimSecond": 5.0, "maxCrossingsPerTick": 1.0}, budgets)
    except BudgetError:  # justified: the raise IS the expected outcome; the flag below turns its absence into a failure
        both_declared_raised = True
    if not both_declared_raised:
        failures.append("a demo declaring both builtin ceilings must be a budget error")
    merged = engine_limits({"maxBuiltinCallsPerSimSecond": 5.0, "engines": {"firefox": {"maxBuiltinCallsPerTick": 1.0}}}, "firefox")
    if "maxBuiltinCallsPerSimSecond" in merged:
        failures.append("an engine naming the per-tick cap must drop the demo-level per-simulated-second ceiling")
    # every ceiling is at least 2x the highest measured run recorded for it
    bunnymark_ceiling = budgets["demos"]["bunnymark"]["maxBuiltinCallsPerSimSecond"]
    if bunnymark_ceiling < 2 * 314.78:
        failures.append(f"bunnymark's ceiling {bunnymark_ceiling} per simulated second is under 2x the highest measured run (314.78)")
    if default_tick < 2 * 0.245:
        failures.append(f"the per-tick default {default_tick} is under 2x the highest measured run (fps on Chrome, 0.245)")
    if failures:
        print("check_budgets --self-test FAIL:", file=sys.stderr)
        for failure in failures:
            print(f"  {failure}", file=sys.stderr)
        return 1
    print("check_budgets --self-test PASS: pre-fix regressions red, measured Chrome and Firefox runs green, per-tick cap kept, "
          "bunnymark per simulated second, exempt and record-only handled")
    return 0


def main(argv: list[str]) -> int:
    parser = argparse.ArgumentParser(description="Check a Web smoke run against its budgets.")
    parser.add_argument("result", nargs="?", help="path to a result JSON envelope")
    parser.add_argument("--print", dest="print_budgets", action="store_true",
                        help="print the declared budgets and exit")
    parser.add_argument("--report", action="store_true",
                        help="print measurements without failing (baseline gathering)")
    parser.add_argument("--self-test", action="store_true",
                        help="grade the recorded pre-fix and measured builtin-call envelopes against the shipped ceilings")
    args = parser.parse_args(argv)

    budgets = load_budgets()
    if args.self_test:
        return run_self_test(budgets)

    if args.print_budgets:
        for demo, limits in budgets["demos"].items():
            ratio = (
                f"crossings/tick <= {limits.get('maxCrossingsPerTick')}"
                if limits.get("maxCrossingsPerTick") is not None
                else "crossings/tick EXEMPT"
            )
            print(
                f"{demo}: payload <= {limits['maxPayloadBytes'] / 1e6:.0f} MB, {ratio}"
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
    engine = (envelope.get("browser") or {}).get("engine")
    measured = measurements(envelope)

    if args.report:
        # Task 88 (finding 13): --report is contractually "print measurements, never fail",
        # and measurements() deliberately yields None for absent fields -- so the unguarded
        # division raised TypeError on exactly the partial envelope this mode exists for.
        payload = measured["payloadBytes"]
        payload_text = "unreported" if payload is None else f"{payload / 1e6:.1f}MB"
        print(
            f"budget report: {demo} "
            f"payload={payload_text} "
            f"startup={measured['startupMs']}ms "
            f"crossings/tick={measured['crossingsPerTick']} "
            f"builtins/tick={measured['builtinCallsPerTick']} "
            f"builtins/sim-s={_per_sim_second(measured)} "
            f"(ticks={measured['ticksObserved']}, sim={measured['simSeconds']}s)"
        )
        return 0

    try:
        violations = check(demo, measured, budgets, engine)
    except BudgetError as error:
        print(f"web_export_smoke: budget error: {error}", file=sys.stderr)
        return 1

    if violations:
        print(f"web_export_smoke: {demo} is OVER BUDGET:", file=sys.stderr)
        for violation in violations:
            print(f"  - {violation}", file=sys.stderr)
        return 1

    limits = engine_limits(
        (budgets.get("demos") or {}).get(demo) or (budgets.get("fixtures") or {}).get(demo) or {},
        engine,
    )
    checked = [f"payload {measured['payloadBytes'] / 1e6:.1f}MB", f"startup {measured['startupMs']}ms recorded"]
    exempt = []
    record_only = "recordOnly" in ((budgets.get("enginePolicies") or {}).get(engine or "") or {})
    if record_only:
        exempt.append(f"crossings/tick (recorded only on {engine})")
    elif limits.get("maxCrossingsPerTick") is not None:
        checked.append(f"{measured['crossingsPerTick']} crossings/tick")
    else:
        exempt.append("crossings/tick")
    if builtin_gate(demo, limits, budgets)[0] == "exempt":
        exempt.append("builtin calls")
    else:
        checked.append(builtin_summary(demo, measured, budgets, limits))
    print(
        f"web_export_smoke: {demo} within budget ({', '.join(checked)})"
        + (f"; EXEMPT, not graded: {', '.join(exempt)}" if exempt else "")
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv[1:]))
