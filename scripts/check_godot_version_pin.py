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


def read_canonical() -> str:
    text = (ROOT / "gradle.properties").read_text()
    m = re.search(r"^kanamaGodotVersion=(.+)$", text, re.MULTILINE)
    if not m:
        print("[check_godot_version_pin] FAIL kanamaGodotVersion missing from gradle.properties")
        sys.exit(1)
    return m.group(1).strip()


def to_dash(dot_version: str) -> str:
    # `4.7.stable` -> `4.7-stable`: only the status separator becomes a dash.
    return re.sub(r"\.([^.]+)$", r"-\1", dot_version)


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--list-files", action="store_true", help="print the files a pin edit touches, one per line")
    parser.add_argument("--skip-api-header", action="store_true", help="do not compare the extension_api.json header")
    args = parser.parse_args()
    if args.list_files:
        print("\n".join(PIN_FILES))
        return 0
    canonical = read_canonical()
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
