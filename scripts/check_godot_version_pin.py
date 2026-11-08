#!/usr/bin/env python3
"""Assert the Godot baseline version is consistent across all pins (review F4).

`gradle.properties` (`kanamaGodotVersion`, dot form e.g. `4.7.stable`) is the single
source of truth. `build.gradle.kts` and `scripts/ios_template_preflight.sh` read it
directly; the CI workflows use the dash form (`4.7-stable`, the GitHub release tag), and
`web.yml` derives the dot-form export-template folder from it.
This gate fails if any of them drift from the canonical value, so the next baseline
bump can't land a partial upgrade.

    python3 scripts/check_godot_version_pin.py                   # the gate
    python3 scripts/check_godot_version_pin.py --list-files      # the files a pin edit touches
    python3 scripts/check_godot_version_pin.py --skip-api-header # pins only (before the API re-dump)
    python3 scripts/check_godot_version_pin.py --release         # refuse a pin a release may not ship on

A prerelease pin (`4.8.beta2`) may live on main; a release may not. `--release` fails unless the pin is
a stable Godot, and `package.yml` runs it first on every `v*` tag, in the job every package job needs, so
a tag on a prerelease pin publishes nothing. The plain gate fails when that step is gone from the workflow,
when a package job has no `needs` path to that job (it would start, and build, on a refused tag), or when a
job overrides that path with a job-level `always()`, `failure()` or `cancelled()` condition.

`--list-files` is what `scripts/upgrade_godot.sh` allows to be dirty when a bump starts (the human
pin edit), so the preflight and this gate cannot disagree about which files a bump edits.
`--skip-api-header` leaves out the `extension_api.json` header check: before the re-dump the
committed header is still the previous Godot's.
"""
import argparse
import pathlib
import re
import sys

ROOT = pathlib.Path(__file__).resolve().parent.parent

# The workflows that carry GODOT_VERSION. package.yml is the release tag; ci.yml, web.yml and
# gate-red-runs-nightly.yml download the binary and export templates by it.
WORKFLOWS = ("package.yml", "ci.yml", "web.yml", "gate-red-runs-nightly.yml")
# Every file a Godot pin edit changes by hand (the rest read gradle.properties).
PIN_FILES = ("gradle.properties", *(f".github/workflows/{wf}" for wf in WORKFLOWS))
# The job of package.yml that every package job needs, directly or not, and the step that stops it on a tag.
RELEASE_GUARD_JOB = "gdextension-header"
RELEASE_GUARD_IF = "if: startsWith(github.ref, 'refs/tags/v')"
RELEASE_GUARD_RUN = "python3 scripts/check_godot_version_pin.py --release"


def read_canonical(properties: pathlib.Path) -> str:
    text = properties.read_text()
    m = re.search(r"^kanamaGodotVersion=(.+)$", text, re.MULTILINE)
    if not m:
        print(f"[check_godot_version_pin] FAIL kanamaGodotVersion missing from {properties}")
        sys.exit(1)
    return m.group(1).strip()


def workflow_jobs(workflow: str) -> dict[str, str]:
    """The jobs of a workflow file: job id -> the job's body (its lines below the `  <id>:` line)."""
    jobs_block = re.search(r"^jobs:\n(.*)", workflow, re.MULTILINE | re.DOTALL)
    if jobs_block is None:
        return {}
    return {
        m.group(1): m.group(2)
        for m in re.finditer(r"^  ([A-Za-z0-9_-]+):[ \t]*\n(.*?)(?=^  \S|\Z)", jobs_block.group(1), re.MULTILINE | re.DOTALL)
    }


def job_needs(body: str) -> list[str]:
    """The `needs:` of one job body: `needs: a`, `needs: [a, b]` or a block list of `- a` lines."""
    m = re.search(r"^    needs:[ \t]*(.*)$", body, re.MULTILINE)
    if m is None:
        return []
    value = m.group(1).split("#", 1)[0].strip()
    if value.startswith("["):
        return [n.strip().strip("'\"") for n in value.strip("[]").split(",") if n.strip()]
    if value:
        return [value.strip("'\"")]
    block = re.match(r"((?:^      - .*\n?)+)", body[m.end() + 1 :], re.MULTILINE)
    return [line.strip()[2:].split("#", 1)[0].strip().strip("'\"") for line in block.group(1).splitlines()] if block else []


def release_guard_problem(package_yml: str) -> str | None:
    """Why package.yml would let a tag through on a prerelease pin, or None."""
    jobs = workflow_jobs(package_yml)
    if RELEASE_GUARD_JOB not in jobs:
        return f"no job `{RELEASE_GUARD_JOB}`, which holds the release guard"
    body = jobs[RELEASE_GUARD_JOB]
    if RELEASE_GUARD_RUN not in body or RELEASE_GUARD_IF not in body:
        return f"job `{RELEASE_GUARD_JOB}` lost its release guard: a step with `{RELEASE_GUARD_IF}` running `{RELEASE_GUARD_RUN}`"
    # Every other job must wait for the guard: through `needs`, directly or through other jobs, and with no
    # job-level condition that runs it when a job it needs failed.
    problems = []
    for job, job_body in jobs.items():
        if job == RELEASE_GUARD_JOB:
            continue
        condition = re.search(r"^    if:(.*)$", job_body, re.MULTILINE)
        if condition and re.search(r"\b(always|failure|cancelled)\(\)", condition.group(1)):
            problems.append(f"job `{job}` runs whatever the jobs it needs did (`if:{condition.group(1)}`)")
        seen: set[str] = set()
        todo = [job]
        while todo:
            for need in job_needs(jobs.get(todo.pop(), "")):
                if need not in seen:
                    seen.add(need)
                    todo.append(need)
        if RELEASE_GUARD_JOB not in seen:
            problems.append(f"job `{job}` has no `needs` path to `{RELEASE_GUARD_JOB}`, so it builds on a refused tag")
    return "; ".join(problems) or None


def to_dash(dot_version: str) -> str:
    # `4.7.stable` -> `4.7-stable`: only the status separator becomes a dash.
    return re.sub(r"\.([^.]+)$", r"-\1", dot_version)


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--list-files", action="store_true", help="print the files a pin edit touches, one per line")
    parser.add_argument("--skip-api-header", action="store_true", help="do not compare the extension_api.json header")
    parser.add_argument("--release", action="store_true", help="fail unless the pin is a stable Godot (a release may not ship on a prerelease)")
    parser.add_argument("--properties", type=pathlib.Path, default=ROOT / "gradle.properties", help="the file holding kanamaGodotVersion (a red run points --release at a scratch copy)")
    args = parser.parse_args()
    if args.list_files:
        print("\n".join(PIN_FILES))
        return 0
    canonical = read_canonical(args.properties)
    if args.release:
        if canonical.endswith(".stable"):
            print(f"[check_godot_version_pin] PASS — a release may ship on kanamaGodotVersion={canonical}")
            return 0
        print(
            f"[check_godot_version_pin] FAIL — kanamaGodotVersion={canonical} is not a stable Godot release. "
            "A prerelease pin may live on main, but no release is cut on one: move the pin to the stable "
            "release with scripts/upgrade_godot.sh first (docs/contributing/release.md)."
        )
        return 1
    dash = to_dash(canonical)
    failures: list[str] = []

    # CI workflows: every GODOT_VERSION must equal the dash form; a stale one silently gates the
    # whole tree on the previous Godot.
    for wf in WORKFLOWS:
        text = (ROOT / ".github/workflows" / wf).read_text()
        m = re.search(r"^\s*GODOT_VERSION:\s*(\S+)\s*$", text, re.MULTILINE)
        if not m:
            failures.append(f".github/workflows/{wf}: GODOT_VERSION not found")
        elif m.group(1) != dash:
            failures.append(
                f".github/workflows/{wf}: GODOT_VERSION={m.group(1)} but expected {dash} "
                f"(from kanamaGodotVersion={canonical})"
            )
        # A literal export-template folder must be the canonical dot form; deriving it from
        # GODOT_VERSION (`${GODOT_VERSION/-/.}`) is the preferred spelling.
        for stray in re.findall(r"export_templates/([^/\"'\s]+)/", text):
            if "$" not in stray and stray != canonical:
                failures.append(
                    f".github/workflows/{wf}: stray export_templates/{stray}/ pin "
                    f"(expected {canonical}, or a GODOT_VERSION-derived form)"
                )

    guard = release_guard_problem((ROOT / ".github/workflows/package.yml").read_text())
    if guard:
        failures.append(f".github/workflows/package.yml: {guard}")

    # build.gradle.kts must not re-hardcode an export-template version (it reads the property).
    bg = (ROOT / "build.gradle.kts").read_text()
    for stray in re.findall(r"export_templates/([0-9][^/\"]*)/", bg):
        if "$" not in stray and stray != canonical:
            failures.append(f"build.gradle.kts: stray export_templates/{stray}/ pin (expected the property)")

    # extension_api.json header should match the canonical version_status (sanity).
    with (ROOT / "extension_api.json").open() as fh:
        api = fh.read(4096)
    status = None if args.skip_api_header else re.search(r'"version_status":\s*"([^"]+)"', api)
    if status and not canonical.endswith(status.group(1)):
        failures.append(
            f"extension_api.json version_status={status.group(1)} not reflected in "
            f"kanamaGodotVersion={canonical}"
        )

    if failures:
        print("[check_godot_version_pin] FAIL — Godot version pins drifted:")
        for f in failures:
            print(f"  - {f}")
        return 1
    print(f"[check_godot_version_pin] PASS — all pins agree (kanamaGodotVersion={canonical}, ci={dash})")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
