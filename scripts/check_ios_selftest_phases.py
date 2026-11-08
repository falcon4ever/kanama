#!/usr/bin/env python3
"""Gate: the iOS scene-init self-test constructs no class whose constructor needs a late server.

The iOS self-test runs in two phases (docs/contributing/backends/ios.md, "The on-device self-test
runs in two phases"). The C ptrcall matrix (`kanama_ios_ptrcall_selftest`) and the Kotlin
`kanamaIosRuntimeObjectCallsSelfTest` run inside `initialize_extensions(INITIALIZATION_LEVEL_SCENE)`;
`kanamaIosRuntimeObjectCallsSelfTestFrame` runs on the first frame. Godot creates some servers only
AFTER scene-level extension init (`Main::setup2`: `CameraServer::create()`,
`PhysicsServer2DManager::initialize_server()`, `PhysicsServer3DManager::initialize_server()`), so
at scene init their C++ singletons are null. A class whose constructor dereferences one of them
segfaults when the scene-init phase constructs it, in the engine, before any Kotlin check runs.

Godot 4.8 made this bite: commit 117e977b25 ("Replace NOTIFICATION_DEBUG_COLLISIONS_HINT_CHANGED
with a signal") added `PhysicsServer2D::get_singleton()->connect(...)` to the constructors of
TileMapLayer, the 2D collision nodes and more (debug builds). `TileMap::TileMap()` memnews a
TileMapLayer, so the scene-init `requireObject("TileMap")` row killed the first 4.8 iPhone run with
SIGSEGV at `TileMapLayer::TileMapLayer()`. Desktop and Android never construct at scene init, so
only a device saw it. This gate makes it a host failure.

The unsafe set is generated, never hand-listed:

  * `--regenerate --godot-src DIR` scans a Godot source checkout. The late servers are the ones
    `Main::setup2` creates between `initialize_extensions(GDExtension::INITIALIZATION_LEVEL_SCENE)`
    and `register_server_singletons()`. A class is unsafe when a constructor in `scene/`,
    `modules/` or `servers/` (not under `editor/` or `tests/`) names `<LateServer>::get_singleton()`
    (body or initializer list), or memnews an unsafe class (to a fixpoint). Only classes in `extension_api.json` are written to
    `scripts/ios_scene_init_unsafe_classes.json`. Rerun it at every Godot bump.
  * The gate expands that set with every `extension_api.json` subclass (CollisionObject3D covers
    StaticBody3D, CharacterBody3D, Area3D, ...).
  * The list records the pin and the Godot commit it was generated from. `--regenerate` refuses a
    checkout that is not exactly the pin: a stable checkout's version.py must equal it; a prerelease
    checkout's says `beta` without the number, so `--godot-bin` (the pinned editor) must report the
    pin and the checkout's commit in `--version`.
  * Always: the list's pin must be the current pin (a bump that skipped the regeneration fails, in
    CI too). With a Godot checkout at the list's commit (`--godot-src`, `GODOT_SRC`, the checkout
    two levels above `GODOT_DOCS`, or the KDoc stage's fallback checkouts) the gate also rescans it
    and fails when the list is stale. An explicit checkout at another commit fails; a fallback at
    another commit is ignored. With no checkout the staleness half prints
    `SKIP: ios-unsafe-classes-staleness` (allowed in ci.yml) and the phase check still runs.

Scene-init code is derived too: the C body of `kanama_ios_ptrcall_selftest`; in
`ObjectCallsSelfTest.kt` everything above the `kanama_ios_runtime_objectcalls_selftest_frame`
function; and the file of every `*SelfTestRows(` function that scene-init text calls. A class is
constructed by `constructObject("X")`, `requireObject("X")`, `construct_object("X")`,
`newRef("X"`, `instantiate("X")` or `X.create()`. The C matrix and the Kotlin scene-init phase
must each yield at least one construction: zero means a pattern stopped matching.

Usage:
    python3 scripts/check_ios_selftest_phases.py                         # gate
    python3 scripts/check_ios_selftest_phases.py --regenerate --godot-src ~/dev/github/godot-4.8-beta2 \
        --godot-bin ~/dev/godot-4.8-beta2/Godot.app/Contents/MacOS/Godot
"""

from __future__ import annotations

import argparse
import json
import os
import re
import subprocess
import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
from gate_skip import skip  # noqa: E402

ROOT = Path(__file__).resolve().parents[1]
TAG = "[ios-selftest-phases]"
DATA = "scripts/ios_scene_init_unsafe_classes.json"
SHIM = "ios/bootstrap/kanama_ios_shim.c"
RUNTIME = "src/iosMain/kotlin/net/multigesture/kanama/binding/runtime"
OBJECTCALLS = f"{RUNTIME}/ObjectCallsSelfTest.kt"
FRAME_MARKER = '@CName("kanama_ios_runtime_objectcalls_selftest_frame")'
SCAN_DIRS = ("scene", "modules", "servers")  # servers/: CameraFeed's constructor asks CameraServer for an id
SKIP_PARTS = {"editor", "tests"}
# Scene-init sources that construct objects today. A scan that finds none in one of them means a pattern stopped
# matching (the C matrix read as 0 classes for a whole review cycle), not that the source became safe.
MUST_CONSTRUCT = ("kanama_ios_ptrcall_selftest", "(scene-init phase)")

CTOR = re.compile(r"^(\w+)::\1\(([^)]*)\)\s*(:[^{;]*)?\{", re.M)
CONSTRUCTS = [
    # No `\b` before the names: the C matrix calls `kanama_ios_godot_construct_object(...)`, and `_` is a word
    # character, so a word boundary never matched it (the C body yielded 0 classes until the review caught it).
    re.compile(r'(?<![A-Za-z0-9])(?:constructObject|requireObject|construct_object|newRef|instantiate)\(\s*"(\w+)"'),
    re.compile(r"\b(\w+)\.create\(\)"),
]


def fail(msg: str) -> int:
    print(f"{TAG} FAIL {msg}", file=sys.stderr)
    return 1


def matching_brace(text: str, open_index: int) -> int:
    """Index just past the brace that closes the one at text[open_index - 1]."""
    depth, i = 1, open_index
    while depth and i < len(text):
        depth += (text[i] == "{") - (text[i] == "}")
        i += 1
    return i


def api_classes(root: Path) -> dict[str, str | None]:
    api = json.loads((root / "extension_api.json").read_text())
    return {c["name"]: c.get("inherits") for c in api["classes"]}


SETUP2_MARKERS = (
    "Error Main::setup2(",
    "initialize_extensions(GDExtension::INITIALIZATION_LEVEL_SCENE)",
    "register_server_singletons();",
)


def late_servers(godot: Path) -> list[str]:
    main = (godot / "main/main.cpp").read_text()
    at = 0
    found, positions = [], []
    for marker in SETUP2_MARKERS:  # in this order, each after the previous one
        at = main.find(marker, at)
        if at < 0:
            raise SystemExit(
                fail(
                    f"{godot}/main/main.cpp: no `{marker}` after {found or 'the start'}; Godot changed Main::setup2, "
                    "so update SETUP2_MARKERS (the late servers are those created between the scene-level "
                    "extension init and register_server_singletons)"
                )
            )
        found.append(marker)
        positions.append(at)
    window = main[positions[1] : positions[2]]
    names = set(re.findall(r"\b(\w+)Manager::initialize_server\(\)", window))
    names |= set(re.findall(r"=\s*(\w+)::create\(", window))
    return sorted(names)


def scan_godot(godot: Path, root: Path, classes: dict[str, str | None]) -> dict:
    servers = late_servers(godot)
    if not servers:
        raise SystemExit(fail(f"found no late server in {godot}/main/main.cpp Main::setup2"))
    server_ref = re.compile(r"\b(" + "|".join(servers) + r")::get_singleton\(\)")
    direct: dict[str, set[str]] = {}
    memnews: dict[str, set[str]] = {}
    for top in SCAN_DIRS:
        for path in sorted((godot / top).rglob("*.cpp")):
            if SKIP_PARTS & set(path.relative_to(godot).parts):
                continue
            # justified: a non-UTF-8 byte sits in a comment or string literal of the Godot source; constructor names and singleton calls are ASCII
            text = path.read_text(errors="ignore")
            for m in CTOR.finditer(text):
                body = (m.group(3) or "") + text[m.end() : matching_brace(text, m.end())]
                name = m.group(1)
                direct.setdefault(name, set()).update(server_ref.findall(body))
                memnews.setdefault(name, set()).update(re.findall(r"\bmemnew\(\s*(\w+)", body))
    reasons = {name: ", ".join(sorted(refs)) for name, refs in direct.items() if refs}
    changed = True
    while changed:
        changed = False
        for name, targets in memnews.items():
            if name in reasons:
                continue
            hit = sorted(t for t in targets if t in reasons)
            if hit:
                reasons[name] = f"memnew({hit[0]})"
                changed = True
    commit = source_commit(godot)
    return {
        "_comment": "Generated by scripts/check_ios_selftest_phases.py --regenerate --godot-src DIR; do not edit.",
        "pin": pin_version(root),
        "godot_source": source_version(godot) + (f" ({commit[:10]})" if commit else ""),
        "godot_commit": commit,
        "late_servers": servers,
        "classes": {name: reasons[name] for name in sorted(reasons) if name in classes},
    }


def source_version(godot: Path) -> str:
    """The checkout's version.py in the pin's spelling: 4.8.beta, 4.7.2.stable (a .0 release has no patch)."""
    text = (godot / "version.py").read_text() if (godot / "version.py").is_file() else ""
    v = {k: val.strip('"') for k, val in re.findall(r"^(major|minor|patch|status)\s*=\s*(.+)$", text, re.M)}
    patch = "" if v.get("patch", "0") == "0" else f".{v['patch']}"
    return f"{v.get('major', '?')}.{v.get('minor', '?')}{patch}.{v.get('status', '?')}"


def source_commit(godot: Path) -> str:
    out = subprocess.run(["git", "-C", str(godot), "rev-parse", "HEAD"], capture_output=True, text=True, timeout=60)
    return out.stdout.strip() if out.returncode == 0 else ""


def pin_version(root: Path) -> str:
    m = re.search(r"^kanamaGodotVersion=(\S+)", (root / "gradle.properties").read_text(), re.M)
    return m.group(1) if m else "?"


def check_source_is_pin(godot: Path, root: Path, godot_bin: str | None) -> str | None:
    """None when the checkout is exactly the pinned Godot, else why not. A stable checkout's version.py equals the pin.
    A prerelease checkout's says `beta` without the number, which only the build knows, so the pinned editor must be
    given: its `--version` (4.8.beta2.official.cafc17a19) must name the pin and the checkout's commit."""
    src, pin = source_version(godot), pin_version(root)
    if src == pin:
        return None
    if not re.fullmatch(re.escape(src) + r"\d+", pin):
        return f"{godot} is Godot {src}, the pin is {pin}"
    binary = godot_bin or os.environ.get("KANAMA_GODOT_BIN") or os.environ.get("GODOT_BIN")
    if not binary:
        return f"{godot} is Godot {src} (no prerelease number in version.py); pass --godot-bin <the {pin} editor>"
    if not os.access(binary, os.X_OK):
        return f"the Godot editor {binary} is not an executable file"
    out = subprocess.run([binary, "--version"], capture_output=True, text=True, timeout=60).stdout.strip()
    build = out.splitlines()[-1] if out else ""
    commit = source_commit(godot)
    if not build.startswith(pin + ".") or not commit or not commit.startswith(build.rsplit(".", 1)[-1]):
        return f"{godot} is at commit {commit[:10] or '(not a git checkout)'}, the {binary} build is {build or '(no output)'}"
    return None


def godot_source(arg: str | None, commit: str) -> Path | None:
    """The Godot checkout to check the list against: one at the commit the list was generated from. An explicit one
    (--godot-src, GODOT_SRC, GODOT_DOCS) at another commit fails (another version is a wrong comparison, not a
    check); the fallbacks local_ci's KDoc stage tries are used only when they are at that commit."""
    explicit = [arg, os.environ.get("GODOT_SRC")]
    if os.environ.get("GODOT_DOCS"):
        explicit.append(str(Path(os.environ["GODOT_DOCS"]).expanduser().resolve().parent.parent))
    for c in explicit:
        if c and (Path(c).expanduser() / "main/main.cpp").is_file():
            godot = Path(c).expanduser()
            at = source_commit(godot)
            if not commit or at != commit:
                raise SystemExit(
                    fail(
                        f"{godot} is Godot {source_version(godot)} at {at[:10] or '(not a git checkout)'}, the list was "
                        f"generated from {commit[:10] or '(no commit)'}; check that commit out, or for a new pin rerun "
                        f"--regenerate --godot-src {godot}"
                    )
                )
            return godot
    for docs in ("~/godot-projects/godot/doc/classes", "~/dev/godot/doc/classes"):
        godot = Path(docs).expanduser().resolve().parent.parent
        if (godot / "main/main.cpp").is_file() and commit and source_commit(godot) == commit:
            return godot
    return None


def regen_source(arg: str | None) -> Path | None:
    for c in (arg, os.environ.get("GODOT_SRC")):
        if c and (Path(c).expanduser() / "main/main.cpp").is_file():
            return Path(c).expanduser()
    return None


def scene_init_sources(root: Path) -> list[tuple[str, str]]:
    shim = (root / SHIM).read_text()
    m = re.search(r"^static void kanama_ios_ptrcall_selftest\(void\)\s*\{", shim, re.M)
    if not m:
        raise SystemExit(fail(f"{SHIM}: kanama_ios_ptrcall_selftest not found"))
    sources = [(f"{SHIM}:kanama_ios_ptrcall_selftest", shim[m.start() : matching_brace(shim, m.end())])]
    objectcalls = (root / OBJECTCALLS).read_text()
    if FRAME_MARKER not in objectcalls or "fun kanamaIosRuntimeObjectCallsSelfTest()" not in objectcalls:
        raise SystemExit(fail(f"{OBJECTCALLS}: phase functions not found"))
    scene_kt = objectcalls[: objectcalls.index(FRAME_MARKER)]
    sources.append((f"{OBJECTCALLS} (scene-init phase)", scene_kt))
    for rows in sorted(set(re.findall(r"\b(\w+SelfTestRows)\(", scene_kt))):
        defining = [p for p in sorted((root / RUNTIME).glob("*.kt")) if re.search(rf"\bfun {rows}\(", p.read_text())]
        if len(defining) != 1:
            raise SystemExit(fail(f"{rows}: expected one defining file under {RUNTIME}, found {len(defining)}"))
        sources.append((f"{defining[0].relative_to(root)} ({rows}, called at scene init)", defining[0].read_text()))
    return sources


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__.split("\n", 1)[0])
    parser.add_argument("--root", default=str(ROOT), help="tree to check (red runs)")
    parser.add_argument("--godot-src", help="Godot source checkout (default: GODOT_SRC, or GODOT_DOCS/../..)")
    parser.add_argument("--regenerate", action="store_true", help=f"rewrite {DATA} from --godot-src")
    parser.add_argument("--godot-bin", help="the pinned editor, for --regenerate from a prerelease checkout (default: "
                        "KANAMA_GODOT_BIN, GODOT_BIN)")
    parser.add_argument("--data", default=DATA, help=f"the generated list, relative to --root (default {DATA}; red runs)")
    args = parser.parse_args()
    root = Path(args.root)
    data_path = root / args.data
    classes = api_classes(root)

    if args.regenerate:
        godot = regen_source(args.godot_src)
        if godot is None:
            return fail("--regenerate needs a Godot source checkout (--godot-src DIR)")
        why = check_source_is_pin(godot, root, args.godot_bin)
        if why:
            return fail(f"--regenerate: {why}")
        data = scan_godot(godot, root, classes)
        data_path.parent.mkdir(parents=True, exist_ok=True)
        data_path.write_text(json.dumps(data, indent=2) + "\n")
        print(f"{TAG} wrote {args.data}: {len(data['classes'])} classes, late servers {data['late_servers']}")
        return 0

    data = json.loads(data_path.read_text())
    if data.get("pin") != pin_version(root):
        return fail(
            f"{args.data} was generated for Godot {data.get('pin')}, the pin is {pin_version(root)}; rerun with "
            "--regenerate --godot-src <a checkout of the pin> (upgrade_godot.sh does)"
        )
    godot = godot_source(args.godot_src, data.get("godot_commit", ""))
    if godot is None:
        staleness = "staleness not checked"
        # CI has no Godot source checkout: ci.yml lists this id in KANAMA_ALLOW_SKIP, as it does kdoc-staleness.
        skip("ios-unsafe-classes-staleness", f"no Godot source checkout to check {args.data} against (set GODOT_SRC or GODOT_DOCS)")
    else:
        fresh = scan_godot(godot, root, classes)
        if {k: fresh[k] for k in ("late_servers", "classes")} != {k: data[k] for k in ("late_servers", "classes")}:
            added = sorted(set(fresh["classes"]) - set(data["classes"]))
            removed = sorted(set(data["classes"]) - set(fresh["classes"]))
            return fail(
                f"{args.data} is stale against {godot} ({fresh['godot_source']}): added {added}, removed {removed}, "
                f"late servers {fresh['late_servers']} (list has {data['late_servers']}); "
                f"rerun with --regenerate --godot-src {godot}"
            )
        staleness = f"current against {fresh['godot_source']}"

    roots = data["classes"]
    unsafe: dict[str, str] = {}
    for name in classes:
        chain, cur = [], name
        while cur is not None and cur not in roots:
            chain.append(cur)
            cur = classes.get(cur)
        if cur is not None:
            unsafe[name] = roots[cur] if cur == name else f"inherits {cur}: {roots[cur]}"

    findings, constructed, counts = [], set(), []
    for label, text in scene_init_sources(root):
        found = set()
        for pattern in CONSTRUCTS:
            for m in pattern.finditer(text):
                name = m.group(1)
                if name not in classes:
                    continue
                found.add(name)
                if name in unsafe:
                    line = text.count("\n", 0, m.start()) + 1
                    findings.append(f"{label} line {line}: constructs {name} ({unsafe[name]})")
        constructed |= found
        counts.append(f"{label.split(' ')[0].rsplit('/', 1)[-1]} {len(found)}")
        if not found and any(key in label for key in MUST_CONSTRUCT):
            return fail(f"{label}: found no object construction; a construction pattern stopped matching")
    if findings:
        print(
            f"{TAG} FAIL {len(findings)} scene-init self-test construction(s) need a server Godot creates only after "
            f"scene-level extension init ({', '.join(data['late_servers'])}); move the row to "
            "kanamaIosRuntimeObjectCallsSelfTestFrame:",
            file=sys.stderr,
        )
        for f in findings:
            print(f"  {f}", file=sys.stderr)
        return 1
    print(
        f"{TAG} PASS {len(constructed)} classes constructed at scene init ({', '.join(counts)}), none of "
        f"{len(unsafe)} unsafe ({len(roots)} generated roots + subclasses); {args.data} {staleness}"
    )
    return 0


if __name__ == "__main__":
    sys.exit(main())
