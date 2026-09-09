#!/usr/bin/env python3
"""Generate docs/reference/generated/gates.md: every gate, what it proves, where it runs (task 99, R18).

Kanama had 18 `audit_*` and 11 `check_*` scripts, 50-odd `local_ci.sh` stages, four CI
workflows and a dozen local-only device/browser gates -- and not one page listing them.
A gate nobody can find is a gate nobody re-runs. This script derives the index from the
things themselves, so it cannot drift:

  * `scripts/local_ci.sh`   -- every `stage "..."` line, in order, with the command under it;
  * `.github/workflows/*`  -- every job in ci.yml, web.yml and package.yml, with its trigger,
                              its `if:` condition and the repo scripts it invokes;
  * a small hand-maintained table below for the gates that run on a device, a browser, or
    a release machine and appear in no workflow (LOCAL_ONLY_GATES);
  * `evidence/gates.json`  -- the latest ledgered run per local-only gate.

"What it proves" is taken from each script's own docstring or header comment (first
sentence), so the page says what the script says, not what someone remembered.

"First landed" is `git log --diff-filter=A` for the script. CI's `local-ci` job checks out
a shallow clone, where that date cannot be derived; `--check` there carries the dates from
the committed page and SAYS SO, while write mode refuses to run on a shallow clone.

Usage:
    python3 scripts/generate_gates_index.py --markdown docs/reference/generated/gates.md
    python3 scripts/generate_gates_index.py --markdown docs/reference/generated/gates.md --check
"""

from __future__ import annotations

import argparse
import json
import re
import subprocess
import sys
from dataclasses import dataclass
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
LOCAL_CI = ROOT / "scripts/local_ci.sh"
WORKFLOWS = ("ci.yml", "web.yml", "package.yml")
LEDGER = ROOT / "evidence/gates.json"
TAG = "[generate_gates_index]"

STAGE_RE = re.compile(r'^\s*stage "(?P<name>[^"]+)"\s*$')
SCRIPT_RE = re.compile(r"scripts/[A-Za-z0-9_./-]+\.(?:py|sh)")
JOB_RE = re.compile(r"^  (?P<id>[A-Za-z0-9_-]+):\s*$")
DATE_ROW_RE = re.compile(r"`(?P<path>scripts/[^`]+)`\s*\|\s*(?P<date>\d{4}-\d{2}-\d{2}|—)\s*\|\s*$")

CI_DEFAULT_WHERE = "PR + push to main (ci.yml `local-ci`); local"

# Stages whose reach differs from "every local_ci run", keyed by stage name.
STAGE_WHERE_OVERRIDES = {
    "wrapper KDoc staleness check (4.7-stable)": "local only when a Godot `doc/classes` checkout is present (`GODOT_DOCS`); CI prints a skip line",
    "claim audit (task 85 aggregator)": "PR + push to main (ci.yml `local-ci`); local. The task-index check inside it runs only where `kanama-tasks` exists and is reported SKIPPED elsewhere",
    "bootstrap cmake build": "PR + push to main; local when cmake is installed (`--skip-bootstrap` skips)",
    "mkdocs strict build": "PR + push to main via the `docs (mkdocs strict)` job (`local-ci` passes `--skip-docs`); local when mkdocs is installed",
    "Linux native bootstrap preflight: file": "PR + push to main (Linux runner); Linux hosts only",
    "Linux native bootstrap preflight: ldd": "PR + push to main (Linux runner); Linux hosts only",
    "Linux native bootstrap preflight: readelf": "PR + push to main (Linux runner); Linux hosts only",
    "web bridge + driver syntax": "PR + push to main; local when node is installed (`--skip-web` skips)",
    "web driver lint (eslint)": "PR + push to main; local when node is installed (`--skip-web` skips)",
}

# Stages whose script has no header to quote and whose stage carries no comment. Used only
# after the script docstring and the stage comment have both come up empty.
STAGE_PROVES_FALLBACK = {
    "public docs local-path guard": "Tracked public docs and templates contain no local machine paths or personal checkout names.",
    "JDWP bootstrap/project-setting guard": "The JDWP debug settings, the libjvm diagnostic and the editor/game-runner split are wired through bootstrap.c and both editor plugins.",
    "gradle sync": "`syncExampleAddonJar` builds the addon jar and copies it into example_project.",
    "KSP script-property default literals": "The KSP-generated registrars carry the source default literals, export metadata, cleanup hooks and method/RPC helpers the example project expects.",
    "external addon install": "`installAddonJar` into a fresh project yields kanama.jar, kanama-scripts.jar, the .gdextension and the native bootstrap, and enables the extension.",
    "Linux native bootstrap preflight: file": "The installed Linux bootstrap is an ELF shared object.",
    "Linux native bootstrap preflight: ldd": "The installed Linux bootstrap has no missing dynamic dependencies.",
    "Linux native bootstrap preflight: readelf": "The installed Linux bootstrap's dynamic section carries no build-machine absolute paths.",
    "publish to mavenLocal": "`publishKanamaToMavenLocal` succeeds.",
    "mavenLocal publication": "The kanama, annotations and processor jars and sources jars exist at the published version in mavenLocal.",
    "bootstrap cmake build": "The native bootstrap configures and builds in Release with CMake.",
    "mkdocs strict build": "`mkdocs build --strict` passes (a broken link or a page missing from the nav fails).",
    "web bridge + driver syntax": "`node --check` parses the JS bridge and every Web driver.",
    "runtime smoke: $godot_bin": "Godot loads the GDExtension, starts the JVM, registers the script language and resource loader, loads Kotlin scripts, and runs the example project to its expected log markers.",
    "@Tool smoke: $godot_bin": "A `@Tool` script executes inside the headless editor process and its expected log patterns appear.",
    "hot reload smoke: $godot_bin": "Across two editor runs around a HelloScript.kt rebuild, the `hot-reload: reloaded scripts from ...kanama-scripts.jar (loader, old_loader, rebound)` marker appears.",
    "in-process hot reload smoke: $godot_bin": "One running editor process reloads an edited script after the `in-process hot reload smoke ready` signal, without a restart.",
}

# Gates that run on a device, in a GUI browser, or on a release host and appear in no
# workflow. `proves` None means: take it from the script's own header.
LOCAL_ONLY_GATES = (
    {
        "gate": "Android device matrix (debug, four models)",
        "proves": "Nine-demo debug APK matrix on real hardware: export, install, launch, Kanama startup in logcat, non-blank screenshot; per demo via `android_smoke.sh`, the matrix loop is `kanama-demos/scripts/android_smoke_all.sh`",
        "where": "local only (adb device); ledger by hand: `record_gate_evidence.py --gate android-device-matrix`",
        "script": "scripts/android_smoke.sh",
    },
    {
        "gate": "Android Vulkan/Mobile renderer matrix",
        "proves": "The same nine-demo matrix with `KANAMA_ANDROID_RENDERER=mobile`, asserting the Vulkan renderer actually initialized (a silent GL fallback fails)",
        "where": "local only (adb device); ledger by hand: `record_gate_evidence.py --gate android-vulkan-mobile-matrix`",
        "script": "scripts/android_smoke.sh",
    },
    {
        "gate": "Android R8-minified release",
        "proves": None,
        "where": "local only (adb device); ledger by hand: `record_gate_evidence.py --gate android-r8-release`",
        "script": "scripts/android_export_minified.sh",
    },
    {
        "gate": "iOS device gate",
        "proves": None,
        "where": "local only (USB device + Apple team); ledgers itself on PASS",
        "script": "scripts/ios_device_gate.sh",
    },
    {
        "gate": "iOS visual smoke",
        "proves": None,
        "where": "local only (device or simulator)",
        "script": "scripts/ios_visual_smoke.sh",
    },
    {
        "gate": "iOS export-template preflight",
        "proves": None,
        "where": "local (also invoked by the iOS Gradle export path)",
        "script": "scripts/ios_template_preflight.sh",
    },
    {
        "gate": "Web Safari corpus",
        "proves": "The Web export corpus driven in Safari (`--engine safari`, run alone): Safari has no headless mode, so this is a GUI-session gate, spot-checked not gated",
        "where": "local only; ledgers itself on PASS (Safari runs)",
        "script": "scripts/web_ci_matrix.sh",
    },
    {
        "gate": "Web tps-demo cell",
        "proves": "The one corpus demo a hosted runner cannot build (Kotlin/Wasm compile OOM): `--demo-set full` runs it, `ci` announces and skips it",
        "where": "local only (`--demo-set full`); nightly CI records it as skipped-with-reason",
        "script": "scripts/web_ci_matrix.sh",
    },
    {
        "gate": "Web per-cell export smoke",
        "proves": None,
        "where": "PR + push to main + nightly via `web_ci_matrix.sh`; local",
        "script": "scripts/web_export_smoke.sh",
    },
    {
        "gate": "Web fresh-checkout export",
        "proves": None,
        "where": "local only (pre-release, pre-promotion)",
        "script": "scripts/web_fresh_checkout_smoke.sh",
    },
    {
        "gate": "Web package smoke",
        "proves": None,
        "where": "local only (after `packageWebExport`)",
        "script": "scripts/web_package_smoke.sh",
    },
    {
        "gate": "Fresh-clone source + demo gate",
        "proves": None,
        "where": "local only (before a release tag)",
        "script": "scripts/fresh_clone_smoke.sh",
    },
    {
        "gate": "Exported-game smoke",
        "proves": None,
        "where": "tag / manual (package.yml `exported-game`); local",
        "script": "scripts/export_game_smoke.sh",
    },
    {
        "gate": "Package install smoke",
        "proves": None,
        "where": "tag / manual (package.yml `desktop-kit`, `store-addon`); local",
        "script": "scripts/package_install_smoke.sh",
    },
    {
        "gate": "Desktop demo smoke matrix",
        "proves": "Nine-demo desktop runtime smoke on a host Godot binary; the demos repo has no CI, so this is the desktop gameplay evidence for every host",
        "where": "local only; ledger by hand per host (`macos-local-ci-desktop-smoke`, `windows-local-revalidation`, `linux-*-full-gate`)",
        "script": "kanama-demos/scripts/desktop_smoke_all.sh",
    },
)

# Gates removed on purpose, with the evidence that something else covers them. A retired
# check that is simply deleted looks like a check that never existed.
RETIRED_GATES = (
    {
        "script": "scripts/audit_scalar_float_abi.py",
        "retired": "2026-09-09 (task 99)",
        "subsumed_by": "scripts/audit_ptrcall_helper_layouts.py",
        "evidence": "The layout audit fails any helper without a Color slot that uses JAVA_FLOAT and any float-slot helper that does not use JAVA_DOUBLE (`audit_helper`), across all 1,500 parsed helpers; the retired script name-matched 30 `ptrcall*Float*` helpers (20 of them packed-array helpers) and a ±10-line \"Color\" text window. Measured at retirement: all 30 helpers and all 464 JAVA_FLOAT sites in ObjectCalls.kt lie inside layout-audited bodies. Not carried over: the KDoc-wording check that those 30 helpers say \"scalar float\".",
    },
)

# Considered for retirement and KEPT, with the gap that keeps them.
KEPT_AFTER_REVIEW = (
    {
        "script": "scripts/audit_wrapper_signatures.py",
        "candidate": "scripts/audit_wrapper_abi_policy.py",
        "gap": "The ABI policy audit re-checks arity, argument and return storage kinds with the exact `value_policy` model (stricter than the coarse `compatible()`), but has no counterpart for the three name-collision checks: generated methods colliding with `java.lang.Object` (`wait`/`notify`/`getClass`), a Godot `close` shadowing `AutoCloseable.close()`, and collisions with final `GodotObject` API. Both the policy audit and the layout audit also import `helper_shape`, `BIND_RE`, `CALL_RE` and friends from it.",
    },
)


@dataclass(frozen=True)
class Stage:
    name: str
    command: str
    script: str | None
    comment: str


@dataclass(frozen=True)
class Job:
    workflow: str
    job_id: str
    name: str
    condition: str
    scripts: tuple[str, ...]


def first_sentence(text: str, limit: int = 240) -> str:
    text = re.sub(r"\s+", " ", text).strip()
    match = re.search(r"\.(?:\s|$)", text)
    if match and match.end() < len(text):
        text = text[: match.start() + 1]
    if len(text) > limit:
        text = text[: limit - 1].rstrip() + "…"
    return text.rstrip(":")


def describe_script(path: Path) -> str:
    """First sentence of a script's module docstring / header comment / usage heredoc."""
    if not path.is_file():
        return ""
    text = path.read_text(encoding="utf-8", errors="replace")
    if path.suffix == ".py":
        match = re.search(r'^(?:[rR]?)"""(.*?)"""', text, re.DOTALL | re.MULTILINE)
        if not match:
            return ""
        return first_sentence(match.group(1).strip().split("\n\n")[0])
    lines = text.splitlines()
    header: list[str] = []
    for line in lines[1:]:
        if line.startswith("#"):
            if line.startswith("# shellcheck"):
                continue
            body = line.lstrip("#").strip()
            if not body and header:
                break
            if body:
                header.append(body)
            continue
        if (line.strip() == "" or line.startswith("set ")) and not header:
            continue
        break
    if header:
        joined = " ".join(header)
        joined = re.sub(r"^[A-Za-z0-9_./-]+\.sh\s+--\s+", "", joined)
        return first_sentence(joined)
    usage = re.search(r"cat <<'EOF'\n(.*?)\nEOF", text, re.DOTALL)
    if usage:
        body = [
            line.strip()
            for line in usage.group(1).splitlines()
            if line.strip() and not re.match(r"^(usage:|Usage:|\s)", line)
        ]
        paragraph: list[str] = []
        for line in body:
            if re.match(r"^(Options|Required|Optional|Arguments|Environment)", line):
                break
            paragraph.append(line)
        if paragraph:
            return first_sentence(" ".join(paragraph))
    return ""


def md_cell(text: str) -> str:
    return text.replace("|", "\\|").replace("\n", " ").strip()


def code(text: str) -> str:
    return "`" + text.replace("|", "\\|").replace("`", "'") + "`"


def parse_stages(path: Path = LOCAL_CI) -> list[Stage]:
    lines = path.read_text(encoding="utf-8").splitlines()
    stages: list[Stage] = []
    for index, line in enumerate(lines):
        match = STAGE_RE.match(line)
        if not match:
            continue
        comment_lines: list[str] = []
        cursor = index - 1
        while cursor >= 0 and lines[cursor].strip().startswith("#"):
            comment_lines.insert(0, lines[cursor].strip().lstrip("#").strip())
            cursor -= 1
        command_lines: list[str] = []
        trailing_comment: list[str] = []
        cursor = index + 1
        while cursor < len(lines):
            candidate = lines[cursor].strip()
            cursor += 1
            if not candidate or candidate.startswith("#"):
                if command_lines:
                    break
                if candidate.startswith("#") and not candidate.startswith("# shellcheck"):
                    trailing_comment.append(candidate.lstrip("#").strip())
                continue
            command_lines.append(candidate.rstrip("\\").strip())
            if not candidate.endswith("\\"):
                break
        command = " ".join(command_lines)
        script_match = SCRIPT_RE.search(command)
        stages.append(
            Stage(
                name=match.group("name"),
                command=command,
                script=script_match.group(0) if script_match else None,
                comment=" ".join(comment_lines or trailing_comment),
            )
        )
    if not stages:
        raise SystemExit(f"{TAG} FAIL no `stage \"...\"` lines found in {path}; the parser is vacuous")
    return stages


def workflow_triggers(text: str) -> str:
    on_block = re.search(r"^on:\n(?P<body>(?:[ \t]+.*\n|\n)+)", text, re.MULTILINE)
    if not on_block:
        return "?"
    body = on_block.group("body")
    parts: list[str] = []
    if re.search(r"^  pull_request:", body, re.MULTILINE):
        parts.append("PR")
    if re.search(r"^  push:\n(?:.*\n)*?\s+branches:.*main", body, re.MULTILINE):
        parts.append("push to main")
    if re.search(r"^  push:\n(?:.*\n)*?\s+tags:", body, re.MULTILINE):
        parts.append("tag `v*`")
    if re.search(r"^  schedule:", body, re.MULTILINE):
        parts.append("nightly (cron)")
    if re.search(r"^  workflow_dispatch:", body, re.MULTILINE):
        parts.append("manual")
    return " + ".join(parts) or "?"


def describe_condition(condition: str, triggers: str) -> str:
    if not condition:
        return triggers
    if "schedule" in condition:
        return "nightly + manual only"
    if "refs/tags/v" in condition:
        return "tag `v*` only"
    changed = re.search(r"needs\.changes\.outputs\.(\w+)", condition)
    if changed:
        return f"{triggers}, only when `{changed.group(1)}` paths changed (docs-only PRs skip; push to main always runs)"
    return f"{triggers}, if {code(condition)}"


def parse_jobs(workflow: str) -> tuple[str, list[Job]]:
    text = (ROOT / ".github/workflows" / workflow).read_text(encoding="utf-8")
    triggers = workflow_triggers(text)
    lines = text.splitlines()
    try:
        start = lines.index("jobs:")
    except ValueError as error:
        raise SystemExit(f"{TAG} FAIL no jobs: block in {workflow}") from error
    jobs: list[Job] = []
    current: str | None = None
    block: list[str] = []

    def flush() -> None:
        if current is None:
            return
        body = "\n".join(block)
        name = re.search(r"^    name:\s*(.+?)\s*$", body, re.MULTILINE)
        condition = re.search(r"^    if:\s*(.+?)\s*$", body, re.MULTILINE)
        scripts = tuple(dict.fromkeys(SCRIPT_RE.findall(body)))
        jobs.append(
            Job(
                workflow=workflow,
                job_id=current,
                name=name.group(1) if name else current,
                condition=describe_condition(condition.group(1) if condition else "", triggers),
                scripts=scripts,
            )
        )

    for line in lines[start + 1 :]:
        if line and not line.startswith(" "):
            break
        match = JOB_RE.match(line)
        if match:
            flush()
            current = match.group("id")
            block = []
            continue
        block.append(line)
    flush()
    if not jobs:
        raise SystemExit(f"{TAG} FAIL no jobs parsed from {workflow}")
    return triggers, jobs


def is_shallow() -> bool:
    out = subprocess.run(
        ["git", "-C", str(ROOT), "rev-parse", "--is-shallow-repository"],
        capture_output=True,
        text=True,
        check=False,
    )
    return out.stdout.strip() == "true"


def first_landed(path: str, cache: dict[str, str], carried: dict[str, str] | None) -> str:
    if path in cache:
        return cache[path]
    if not (ROOT / path).is_file():
        cache[path] = "—"
        return "—"
    if carried is not None:
        cache[path] = carried.get(path, "—")
        return cache[path]
    out = subprocess.run(
        ["git", "-C", str(ROOT), "log", "--diff-filter=A", "--follow", "--format=%ad", "--date=short", "--", path],
        capture_output=True,
        text=True,
        check=False,
    ).stdout.split()
    cache[path] = out[-1] if out else "—"
    return cache[path]


def carried_dates(page: Path) -> dict[str, str]:
    dates: dict[str, str] = {}
    if page.is_file():
        for line in page.read_text(encoding="utf-8").splitlines():
            match = DATE_ROW_RE.search(line)
            if match:
                dates[match.group("path")] = match.group("date")
    return dates


def ledger_rows() -> list[dict]:
    if not LEDGER.is_file():
        raise SystemExit(f"{TAG} FAIL ledger missing: {LEDGER}")
    data = json.loads(LEDGER.read_text(encoding="utf-8"))
    latest: dict[str, dict] = {}
    for entry in data["gates"]:
        gate = entry["gate"]
        if gate not in latest or entry["date"] > latest[gate]["date"]:
            latest[gate] = entry
    return [latest[gate] for gate in sorted(latest)]


def render(carried: dict[str, str] | None) -> str:
    date_cache: dict[str, str] = {}
    stages = parse_stages()
    lines: list[str] = []
    out = lines.append

    out("# Gates Index")
    out("")
    out("Generated by `python3 scripts/generate_gates_index.py --markdown docs/reference/generated/gates.md`; `--check` runs as a `local_ci.sh` stage, so this page cannot drift from the scripts and workflows it lists.")
    out("")
    out("Every check that decides whether Kanama is green, in one place: what each one proves (in the script's own words), where it runs, and when it first landed. Three tiers:")
    out("")
    out("- **Local CI stages** run on every PR and push to `main` (`ci.yml` job `local-ci (linux)` runs `scripts/local_ci.sh --skip-docs`) and locally with `scripts/local_ci.sh <godot>`.")
    out("- **CI workflow jobs** are the GitHub lanes around it: docs, mobile builds, the Web browser matrix, packaging.")
    out("- **Local-only gates** need a device, a GUI browser, or a release host. They appear in no workflow, so their runs are ledgered in `evidence/gates.json` and `scripts/check_gate_evidence.py` (inside `scripts/audit_claims.sh`) fails when a Supported claim's latest run predates the current Godot pin.")
    out("")
    out("A green PR means the first two tiers passed. It does not mean the third tier was run — that is what the ledger is for.")
    out("")

    out(f"## Local CI stages ({len(stages)})")
    out("")
    out("In `scripts/local_ci.sh` order. \"What it proves\" is the first sentence of the script's docstring or header where a script exists, otherwise the stage's own comment.")
    out("")
    out("| # | Stage | What it proves | Where it runs | Script | First landed |")
    out("| ---: | --- | --- | --- | --- | --- |")
    for index, stage in enumerate(stages, start=1):
        proves = describe_script(ROOT / stage.script) if stage.script else ""
        if not proves:
            proves = first_sentence(stage.comment) if stage.comment else ""
        if not proves:
            proves = STAGE_PROVES_FALLBACK.get(stage.name, "(inline check; see the stage command)")
        where = STAGE_WHERE_OVERRIDES.get(stage.name, CI_DEFAULT_WHERE)
        script_cell = code(stage.script) if stage.script else code(first_sentence(stage.command, 80))
        landed = first_landed(stage.script, date_cache, carried) if stage.script else "—"
        name = stage.name.replace("$godot_bin", "<godot>")
        out(f"| {index} | {code(name)} | {md_cell(proves)} | {md_cell(where)} | {script_cell} | {landed} |")
    out("")

    out("## CI workflow jobs")
    out("")
    out("Parsed from `.github/workflows/{ci,web,package}.yml`. Scripts are the repo gate scripts a job invokes directly; `local-ci` runs every stage above.")
    out("")
    out("| Workflow | Job | When | Scripts |")
    out("| --- | --- | --- | --- |")
    for workflow in WORKFLOWS:
        _triggers, jobs = parse_jobs(workflow)
        for job in jobs:
            scripts = ", ".join(code(script) for script in job.scripts) or "—"
            out(f"| {code(workflow)} | {md_cell(job.name)} ({code(job.job_id)}) | {md_cell(job.condition)} | {scripts} |")
    out("")

    out("## Local-only gates")
    out("")
    out("Hand-maintained in `scripts/generate_gates_index.py` (`LOCAL_ONLY_GATES`); descriptions come from the script headers where they exist.")
    out("")
    out("| Gate | What it proves | Where it runs | Script | First landed |")
    out("| --- | --- | --- | --- | --- |")
    for gate in LOCAL_ONLY_GATES:
        proves = gate["proves"] or describe_script(ROOT / gate["script"]) or "(no header in the script)"
        landed = first_landed(gate["script"], date_cache, carried)
        out(f"| {md_cell(gate['gate'])} | {md_cell(proves)} | {md_cell(gate['where'])} | {code(gate['script'])} | {landed} |")
    out("")

    out("## Retired gates")
    out("")
    out("Removed on purpose, with the evidence that another gate covers the same ground. Hand-maintained in `scripts/generate_gates_index.py` (`RETIRED_GATES`, `KEPT_AFTER_REVIEW`).")
    out("")
    out("| Script | Retired | Subsumed by | Evidence |")
    out("| --- | --- | --- | --- |")
    for gate in RETIRED_GATES:
        out(f"| {code(gate['script'])} | {md_cell(gate['retired'])} | {code(gate['subsumed_by'])} | {md_cell(gate['evidence'])} |")
    out("")
    out("Reviewed as a retirement candidate and kept:")
    out("")
    out("| Script | Candidate successor | Why it stays |")
    out("| --- | --- | --- |")
    for gate in KEPT_AFTER_REVIEW:
        out(f"| {code(gate['script'])} | {code(gate['candidate'])} | {md_cell(gate['gap'])} |")
    out("")

    out("## Gate evidence ledger")
    out("")
    out("`evidence/gates.json` records each run of a local-only gate: gate, Godot pin, Kanama commit, date, device or browser, result, and the doc line it backs. `scripts/ios_device_gate.sh` and `scripts/web_ci_matrix.sh` (Safari runs) append to it on PASS via `scripts/record_gate_evidence.py`; the Android matrix (its loop lives in the demos repo) and the Windows/Linux host revalidations are recorded by hand:")
    out("")
    out("```sh")
    out("python3 scripts/record_gate_evidence.py --gate android-device-matrix --result PASS \\")
    out('  --where "Pixel 7 (Android 16), Moto g 5G 2023 (Android 14)" --claim "Android Supported"')
    out("```")
    out("")
    out("`scripts/check_gate_evidence.py` fails when the latest run of a gate is on an older pin than `gradle.properties` unless the entry carries `acceptedStaleUntil` (a future date) and `acceptedStaleReason`; an accepted-stale entry passes with a loud WARN, and an expired acceptance fails. Latest run per gate:")
    out("")
    out("| Gate | Claim | Last run | Godot pin | Result | Accepted stale until |")
    out("| --- | --- | --- | --- | --- | --- |")
    for entry in ledger_rows():
        until = entry.get("acceptedStaleUntil", "—")
        out(
            f"| {code(entry['gate'])} | {md_cell(entry['claim'])} | {entry['date']} | {code(entry['godotPin'])} | "
            f"{entry['result']} | {until} |"
        )
    out("")
    return "\n".join(lines) + "\n"


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--markdown", type=Path, required=True, help="page to write (or `-` for stdout)")
    parser.add_argument("--check", action="store_true", help="fail if the page is stale instead of writing it")
    args = parser.parse_args()

    shallow = is_shallow()
    carried: dict[str, str] | None = None
    if shallow:
        if not args.check or str(args.markdown) == "-":
            print(f"{TAG} FAIL shallow clone: first-landed dates cannot be derived; fetch full history to regenerate", file=sys.stderr)
            return 1
        carried = carried_dates(args.markdown)
        print(f"{TAG} NOTE shallow clone: first-landed dates carried from the committed page ({len(carried)} scripts), not re-derived")

    markdown = render(carried)
    if str(args.markdown) == "-":
        print(markdown, end="")
        return 0
    if args.check:
        current = args.markdown.read_text(encoding="utf-8") if args.markdown.exists() else ""
        if current != markdown:
            print(f"{TAG} FAIL stale gates index: {args.markdown}", file=sys.stderr)
            print(f"{TAG} run: python3 scripts/generate_gates_index.py --markdown {args.markdown}", file=sys.stderr)
            return 1
        print(f"{TAG} PASS gates index up to date: {args.markdown}")
        return 0
    args.markdown.parent.mkdir(parents=True, exist_ok=True)
    args.markdown.write_text(markdown, encoding="utf-8")
    print(f"{TAG} wrote {args.markdown}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
