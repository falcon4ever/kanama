#!/usr/bin/env python3
"""Measure the desktop edit -> running loop on a fresh starter project.

What a user does, timed from the moment a `.kt` file is saved:

* build      `./gradlew buildScripts` in the starter after an edit (warm Gradle daemon).
* restart    the same build, then a new headless game process until the edited log line prints:
             the "press Play again" loop.
* editor     a headless editor with Kanama Tools' build on save on: the plugin sees the save, runs
             Build Scripts, the runtime reloads `kanama-scripts.jar` and the plugin reloads the
             open scene, whose `@Tool` HelloScript prints the edited line again.
* game       a headless game running beside that editor: the save until the game has loaded the new
             build (`hot-reload: reloaded scripts`). Nodes that already exist keep their old code
             until their scene reloads, so the starter's own line does not change in a running game;
             new instances (and Kotlin autoloads) use the new code.

The edit changes the tag in the starter's `GD.print("[kanama:starter] ...")` line. Every leg runs
`--runs` times after one warm-up run that is not counted (it starts the Gradle daemon the editor
build reuses). The script prints the median and p90 (nearest rank) of each leg, and one budget line
for the editor leg. The budget line is a report, not a gate: the run exits 0 when the budget is
missed and non-zero only when a measurement could not be taken.

Usage:
    python3 scripts/measure_edit_loop.py /path/to/godot            # a fresh starter in a temp dir
    python3 scripts/measure_edit_loop.py /path/to/godot --runs 5 --budget 5
    python3 scripts/measure_edit_loop.py /path/to/godot --project /path/to/existing-starter

`--project` reuses a starter made by `createStarterProject` (it edits `kotlin-src/HelloScript.kt`).
The editor leg needs build on save, the Editor Settings > Kanama > Build > Build On Save setting
(`kanama/build/build_on_save`), which is on unless you turned it off. JAVA_HOME should name a
JDK 25+, as for any Kanama build.
"""

from __future__ import annotations

import argparse
import json
import os
import platform
import re
import shutil
import signal
import statistics
import subprocess
import sys
import tempfile
import time
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
GRADLE_TIMEOUT_S = 1800
GODOT_TIMEOUT_S = 600
RUN_TIMEOUT_S = 300
DEFAULT_BUDGET_S = 5.0

MARKER_RE = re.compile(r"\[kanama:starter[^\]]*\] ")
RELOADED_RE = re.compile(r"hot-reload: reloaded scripts from .*\(loader=(\d+),")


def log(msg: str) -> None:
    print(f"[measure_edit_loop] {msg}", flush=True)


def fail(msg: str) -> None:
    print(f"[measure_edit_loop] FAIL: {msg}", file=sys.stderr, flush=True)
    raise SystemExit(1)


def run(cmd: list[str], cwd: Path, timeout: int, quiet: bool = True) -> None:
    result = subprocess.run(
        cmd,
        cwd=cwd,
        stdout=subprocess.PIPE if quiet else None,
        stderr=subprocess.STDOUT if quiet else None,
        text=True,
        timeout=timeout,
    )
    if result.returncode != 0:
        if quiet and result.stdout:
            print(result.stdout[-4000:], file=sys.stderr)
        fail(f"`{' '.join(cmd)}` exited {result.returncode}")


class Process:
    """A Godot process with its own log, killed (with its process group) on stop()."""

    def __init__(self, name: str, cmd: list[str], log_path: Path, env: dict[str, str] | None = None):
        self.name = name
        self.log_path = log_path
        self._log = open(log_path, "w")
        self.proc = subprocess.Popen(
            cmd, stdout=self._log, stderr=subprocess.STDOUT, start_new_session=True, env=env
        )

    def text(self) -> str:
        try:
            return self.log_path.read_text(errors="replace")
        except OSError:
            return ""

    def alive(self) -> bool:
        return self.proc.poll() is None

    def stop(self) -> None:
        if self.alive() and os.name == "nt":
            self.proc.kill()
            self.proc.wait(timeout=15)
        elif self.alive():
            try:
                os.killpg(self.proc.pid, signal.SIGTERM)
            except ProcessLookupError:  # justified: cleanup after the verdict; the process already exited
                pass
            try:
                self.proc.wait(timeout=15)
            except subprocess.TimeoutExpired:  # justified: escalated to SIGKILL on the next line
                try:
                    os.killpg(self.proc.pid, signal.SIGKILL)
                except ProcessLookupError:  # justified: cleanup after the verdict; it exited meanwhile
                    pass
                self.proc.wait(timeout=15)
        self._log.close()


def wait_for(what: str, check, procs: list[Process], timeout: float = RUN_TIMEOUT_S) -> float:
    """Polls `check()` every 20 ms; returns the monotonic time it first held."""
    deadline = time.monotonic() + timeout
    while time.monotonic() < deadline:
        if check():
            return time.monotonic()
        for p in procs:
            if not p.alive():
                tail = "\n".join(p.text().splitlines()[-40:])
                fail(f"{p.name} exited while waiting for {what}; log tail:\n{tail}")
        time.sleep(0.02)
    tails = "\n".join(f"--- {p.name} ({p.log_path})\n" + "\n".join(p.text().splitlines()[-40:]) for p in procs)
    fail(f"timed out after {timeout:.0f} s waiting for {what}\n{tails}")
    return 0.0


def set_marker(script: Path, tag: str) -> float:
    """Rewrites the starter's print tag and returns the time of the save."""
    text = script.read_text()
    if not MARKER_RE.search(text):
        fail(f"{script} has no `[kanama:starter...] ` print line to edit")
    script.write_text(MARKER_RE.sub(f"[kanama:starter:{tag}] ", text, count=1))
    return time.monotonic()


def marker_line(tag: str) -> str:
    return f"[kanama:starter:{tag}] "


def percentile(values: list[float], pct: float) -> float:
    ordered = sorted(values)
    rank = max(1, -(-len(ordered) * pct // 100))  # nearest rank: ceil(n * pct / 100)
    return ordered[int(rank) - 1]


def summary(values: list[float]) -> dict[str, float]:
    return {
        "median": round(statistics.median(values), 2),
        "p90": round(percentile(values, 90), 2),
        "min": round(min(values), 2),
        "max": round(max(values), 2),
    }


def make_starter(parent: Path) -> Path:
    project = parent / "kanama-starter"
    gradlew = str(ROOT / "gradlew")
    log("creating a fresh starter (createStarterProject + installAddonJar)")
    run([gradlew, "-q", "createStarterProject", f"-PkanamaStarterProjectDir={project}"], ROOT, GRADLE_TIMEOUT_S)
    run(
        [
            gradlew,
            "-q",
            "installAddonJar",
            f"-PkanamaProjectDir={project}",
            f"-PkanamaProjectScriptsDir={project / 'kotlin-src'}",
        ],
        ROOT,
        GRADLE_TIMEOUT_S,
    )
    return project


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("godot", nargs="?", default=os.environ.get("GODOT_BIN", ""), help="Godot 4.7.2 binary")
    parser.add_argument("--runs", type=int, default=5)
    parser.add_argument("--project", type=Path, help="an existing starter to measure in (it is edited)")
    parser.add_argument("--budget", type=float, default=DEFAULT_BUDGET_S, help="editor-leg median budget, seconds")
    parser.add_argument("--json", type=Path, help="also write the results here")
    parser.add_argument("--keep", action="store_true", help="keep the temp starter and logs")
    args = parser.parse_args()
    if not args.godot:
        fail("pass the Godot binary (or set GODOT_BIN)")
    if args.runs < 1:
        fail("--runs must be at least 1")
    godot = args.godot

    work = Path(tempfile.mkdtemp(prefix="kanama_edit_loop."))
    procs: list[Process] = []
    ok = False
    restore: list[tuple[Path, str]] = []
    project: Path | None = None
    try:
        project = args.project.resolve() if args.project else make_starter(work)
        script = project / "kotlin-src" / "HelloScript.kt"
        gradlew = project / ("gradlew.bat" if os.name == "nt" else "gradlew")
        if not script.is_file() or not gradlew.is_file():
            fail(f"{project} is not a starter (needs kotlin-src/HelloScript.kt and its own gradlew)")
        restore = [(script, script.read_text()), (project / "project.godot", (project / "project.godot").read_text())]
        log(f"project: {project}")
        run([godot, "--headless", "--path", str(project), "--import"], project, GODOT_TIMEOUT_S)

        results: dict[str, list[float]] = {"build": [], "restart": [], "editor": [], "game": []}

        # build + restart legs (no editor running, so nothing else builds on the save)
        for i in range(args.runs + 1):
            tag = f"r{i}"
            saved = set_marker(script, tag)
            run([str(gradlew), "-q", "buildScripts"], project, GRADLE_TIMEOUT_S)
            built = time.monotonic()
            game = Process(f"game r{i}", [godot, "--headless", "--path", str(project)], work / f"restart_{i}.log")
            procs.append(game)
            shown = wait_for(f"`{marker_line(tag)}` in a restarted game", lambda: marker_line(tag) in game.text(), [game])
            game.stop()
            procs.remove(game)
            if i == 0:
                log(f"warm-up: build {built - saved:.2f} s, restart {shown - saved:.2f} s (not counted)")
                continue
            results["build"].append(built - saved)
            results["restart"].append(shown - saved)
            log(f"run {i}: build {built - saved:.2f} s, build + restart {shown - saved:.2f} s")

        # editor + game legs: one save, both processes watching
        tag = "live0"
        set_marker(script, tag)
        run([str(gradlew), "-q", "buildScripts"], project, GRADLE_TIMEOUT_S)
        editor = Process(
            "editor", [godot, "--headless", "--editor", "--path", str(project), "res://main.tscn"], work / "editor.log"
        )
        game = Process("game", [godot, "--headless", "--path", str(project)], work / "game.log")
        procs.extend([editor, game])
        wait_for("the editor to open main.tscn", lambda: marker_line(tag) in editor.text(), procs, GODOT_TIMEOUT_S)
        wait_for("the game to start", lambda: marker_line(tag) in game.text(), procs, GODOT_TIMEOUT_S)
        time.sleep(2.0)  # the plugin's first .kt scan and a whole-second mtime boundary

        builds_per_save: list[int] = []
        for i in range(args.runs + 1):
            tag = f"e{i}"
            builds_before = editor.text().count("Kotlin save detected")
            reloads_before = len(RELOADED_RE.findall(game.text()))
            saved = set_marker(script, tag)
            seen: dict[str, float] = {}

            def done() -> bool:
                now = time.monotonic()
                if "editor" not in seen and marker_line(tag) in editor.text():
                    seen["editor"] = now
                if "game" not in seen and len(RELOADED_RE.findall(game.text())) > reloads_before:
                    seen["game"] = now
                return len(seen) == 2

            wait_for(f"run e{i} (editor `{marker_line(tag)}` and a game reload)", done, procs)
            # Let a second build (if any) start and finish before the next save, and count it.
            time.sleep(3.0)
            wait_for(
                "the editor build to finish",
                lambda: editor.text().count("Kotlin save detected")
                <= editor.text().count("buildScripts succeeded") + editor.text().count("buildScripts failed"),
                procs,
            )
            builds = editor.text().count("Kotlin save detected") - builds_before
            if i == 0:
                log(f"warm-up: editor {seen['editor'] - saved:.2f} s, game {seen['game'] - saved:.2f} s (not counted)")
                continue
            builds_per_save.append(builds)
            results["editor"].append(seen["editor"] - saved)
            results["game"].append(seen["game"] - saved)
            log(
                f"run {i}: editor {seen['editor'] - saved:.2f} s, game reload {seen['game'] - saved:.2f} s, "
                f"builds for this save: {builds}"
            )

        game_text = game.text()
        for p in list(procs):
            p.stop()
            procs.remove(p)

        machine = f"{platform.system()} {platform.release()} {platform.machine()}, {os.cpu_count()} CPUs"
        report = {
            "machine": machine,
            "runs": args.runs,
            "legs": {name: summary(values) for name, values in results.items()},
            "samples": {name: [round(v, 2) for v in values] for name, values in results.items()},
            "builds_per_save": builds_per_save,
            "game_retired_loader_check_lines": game_text.count("retired-loader-check"),
        }
        print()
        print(f"edit -> running loop, {args.runs} runs after a warm-up, {machine}")
        labels = {
            "build": "build only (./gradlew buildScripts)",
            "restart": "build + restart the game",
            "editor": "editor build on save -> scene shows it",
            "game": "running game loads the new build",
        }
        for name, values in results.items():
            s = report["legs"][name]
            print(f"  {labels[name]:<42} median {s['median']:5.2f} s  p90 {s['p90']:5.2f} s  (min {s['min']:.2f}, max {s['max']:.2f})")
        print(f"  builds per save in the editor: {builds_per_save}")
        median = report["legs"]["editor"]["median"]
        verdict = "within" if median <= args.budget else "OVER"
        print(f"budget (report only, not a gate): editor save -> visible median {median:.2f} s <= {args.budget:.1f} s: {verdict}")
        if args.json:
            args.json.write_text(json.dumps(report, indent=2) + "\n")
        ok = True
        return 0
    finally:
        for p in procs:
            p.stop()
        for path, text in restore:
            path.write_text(text)
        if args.project and restore:
            # The edited project gets back its own script and settings, and a jar built from them.
            run([str(project / ("gradlew.bat" if os.name == "nt" else "gradlew")), "-q", "buildScripts"], project, GRADLE_TIMEOUT_S)
        if args.keep or not ok:
            log(f"logs kept in {work}")
        else:
            shutil.rmtree(work, ignore_errors=True)


if __name__ == "__main__":
    sys.exit(main())
