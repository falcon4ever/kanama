#!/usr/bin/env python3
"""Red runs for the repo's gates (task 118): every gate is shown to FAIL on a known-bad input.

A gate that has only ever been seen green is a claim, not evidence: a gate that swallows its own
failure (a skipped branch, a regex that stopped matching, an `|| true`) is green on every input. This
harness holds each listed gate to the three-step proof, with a reversible mutation of the tree:

    1. green  the gate passes on the tree as it is,
    2. red    after the mutation the gate exits non-zero AND prints the expected text,
    3. green  after the mutation is reverted the gate passes again.

Every case runs in a THROWAWAY checkout (`git worktree add --detach` of HEAD, plus the uncommitted
changes and untracked files of the dev checkout), so the checkout you work in is never mutated: a
SIGTERM, a timeout or a crash cannot leave a half-applied mutation behind. The workspace is removed
on exit, also on SIGTERM/SIGHUP/Ctrl-C. A timeout kills the whole process group of the case (a
gate that spawned Gradle or Godot leaves nothing behind). Mutations are still reverted between the
red and the final green run, and the directories a mutation created are removed again.

Usage:
    python3 scripts/gate_red_runs.py                  # every fast case
    python3 scripts/gate_red_runs.py --strict         # a SKIPPED case (missing GODOT_DOCS, ...) is a failure
    python3 scripts/gate_red_runs.py --only parity    # cases whose gate name contains the text
    python3 scripts/gate_red_runs.py --slow           # also the Godot/Gradle smokes (needs KANAMA_GODOT_BIN)
    python3 scripts/gate_red_runs.py --list
    python3 scripts/gate_red_runs.py --markdown out.md   # also write the README-gates.md table rows

`--strict` is implied when `CI` is true/1/yes. `--allow-skip NAME[,NAME]` names the cases a runner
may skip on purpose (CI has no Godot docs checkout: `--allow-skip sync_kdoc`).

Gates that need a device, a browser, a Godot binary or a long export are NOT all here: they carry
their own `--self-test` or red-run recipe, documented in scripts/README-gates.md.
"""

from __future__ import annotations

import argparse
import os
import shutil
import signal
import subprocess
import sys
import tempfile
import time
from dataclasses import dataclass, field
from pathlib import Path

SRC = Path(__file__).resolve().parents[1]  # the dev checkout; never mutated
WORK = SRC  # the throwaway checkout every case runs in (set by main())
PY = sys.executable


@dataclass
class Edit:
    path: str
    old: str
    new: str


@dataclass
class Create:
    path: str
    content: str


@dataclass
class Delete:
    path: str


@dataclass
class Rename:
    path: str
    to: str


@dataclass
class Append:
    path: str
    text: str


@dataclass
class Case:
    gate: str  # name shown in the table
    cmd: list[str]  # run from WORK
    mutations: list  # Edit | Create | Delete | Rename | Append
    expect: str  # substring the red run must print (stdout + stderr)
    how: str  # one line for the table: what the mutation does
    env: dict[str, str] = field(default_factory=dict)
    timeout: int = 600
    pre: list[list[str]] = field(default_factory=list)  # commands run (must succeed) before the baseline
    requires_env: str = ""  # an environment variable that must name an existing path, else the case is skipped
    slow: bool = False  # needs a Godot binary / Gradle and runs for minutes: only with --slow


def script(name: str, *args: str) -> list[str]:
    return [PY, f"scripts/{name}", *args]


# -- helpers for building cases ------------------------------------------------------------------

CASES: list[Case] = []


def case(*args, **kwargs) -> None:
    CASES.append(Case(*args, **kwargs))


class Mutator:
    """Applies a list of mutations inside WORK and reverts them, byte for byte (modes, created dirs too)."""

    def __init__(self, mutations: list):
        self.mutations = mutations
        self.saved: list[tuple[Path, bytes | None]] = []
        self.modes: dict[Path, int | None] = {}  # a deleted-then-restored script must keep its executable bit
        self.created_dirs: list[Path] = []
        self.renamed: list[tuple[Path, Path]] = []

    def _mkdirs(self, directory: Path) -> None:
        missing = []
        while not directory.exists() and directory != WORK:
            missing.append(directory)
            directory = directory.parent
        for d in reversed(missing):
            d.mkdir()
            self.created_dirs.append(d)

    def __enter__(self):
        try:
            for mutation in self.mutations:
                path = WORK / mutation.path
                if isinstance(mutation, Rename):
                    target = WORK / mutation.to
                    path.rename(target)
                    self.renamed.append((target, path))
                    continue
                original = path.read_bytes() if path.exists() else None
                self.modes[path] = path.stat().st_mode if path.exists() else None
                self.saved.append((path, original))
                if isinstance(mutation, Append):
                    if original is None:
                        raise SystemExit(f"gate_red_runs: cannot append to the missing file {mutation.path}")
                    path.write_bytes(original + mutation.text.encode("utf-8"))
                elif isinstance(mutation, Edit):
                    text = original.decode("utf-8") if original is not None else None
                    if text is None or mutation.old not in text:
                        raise SystemExit(
                            f"gate_red_runs: cannot apply the mutation, `{mutation.old[:60]!r}` is not in {mutation.path}"
                            " (the file changed; update the case)"
                        )
                    path.write_text(text.replace(mutation.old, mutation.new, 1), encoding="utf-8")
                elif isinstance(mutation, Create):
                    self._mkdirs(path.parent)
                    path.write_text(mutation.content, encoding="utf-8")
                elif isinstance(mutation, Delete):
                    path.unlink()
        except BaseException:
            self.__exit__(None, None, None)
            raise
        return self

    def __exit__(self, *exc):
        for target, path in reversed(self.renamed):
            target.rename(path)
        for path, original in reversed(self.saved):
            if original is None:
                if path.exists():
                    path.unlink()
            else:
                path.parent.mkdir(parents=True, exist_ok=True)
                path.write_bytes(original)
                if self.modes.get(path) is not None:
                    path.chmod(self.modes[path])
        for directory in reversed(self.created_dirs):
            try:
                directory.rmdir()
            except OSError:  # justified: a directory a gate filled meanwhile is the gate's own output; it is not ours to delete
                pass
        self.saved, self.created_dirs, self.renamed = [], [], []
        return False


def run(case_: Case) -> tuple[int, str, float]:
    env = dict(os.environ)
    env.update(case_.env)
    # A gate that skips under CI would read as red here for the wrong reason; the harness decides about skips itself.
    env.pop("CI", None)
    started = time.monotonic()
    proc = subprocess.Popen(
        case_.cmd, cwd=WORK, env=env, stdout=subprocess.PIPE, stderr=subprocess.STDOUT, text=True,
        start_new_session=True,  # its own process group, so a timeout can kill everything the gate spawned
    )
    try:
        out, _ = proc.communicate(timeout=case_.timeout)
        return proc.returncode, out, time.monotonic() - started
    except subprocess.TimeoutExpired:  # justified: reported as exit 124 with the partial output; the case then fails
        try:
            os.killpg(proc.pid, signal.SIGKILL)
        except ProcessLookupError:  # justified: the group already exited between the timeout and the kill
            pass
        out, _ = proc.communicate()
        return 124, f"timeout after {case_.timeout}s (process group killed)\n{out or ''}", time.monotonic() - started


def red_excerpt(output: str, expect: str) -> str:
    """One to three lines of the red output: the line holding the expected text, else the last lines."""
    output = output.replace(str(WORK) + "/", "").replace(str(SRC) + "/", "")  # no workstation paths in the excerpt
    lines = [line.strip() for line in output.splitlines() if line.strip()]
    for index, line in enumerate(lines):
        if expect in line:
            return " / ".join(lines[index : index + 2])[:200]
    return " / ".join(lines[-2:])[:200]


def git_out(*args: str, cwd: Path = SRC, data: bytes | None = None) -> bytes:
    return subprocess.run(["git", *args], cwd=cwd, input=data, capture_output=True, check=True).stdout


def git_status() -> str:
    """The porcelain status plus the mode changes (`git status` does not show a lost executable bit)."""
    return git_out("status", "--porcelain").decode() + git_out("diff", "--summary").decode()


def make_workspace() -> tuple[Path, Path]:
    """A detached worktree of HEAD carrying the dev checkout's uncommitted state; returns (tmp dir, tree)."""
    tmp = Path(tempfile.mkdtemp(prefix="gate_red_runs.")).resolve()  # resolved: /var is /private/var on macOS
    tree = tmp / "tree"
    git_out("worktree", "add", "--detach", "--quiet", str(tree), "HEAD")
    patch = git_out("diff", "HEAD", "--binary")
    if patch:
        git_out("apply", "--whitespace=nowarn", cwd=tree, data=patch)
    untracked = git_out("ls-files", "--others", "--exclude-standard", "-z").decode().split("\0")
    for rel in filter(None, untracked):
        dest = tree / rel
        dest.parent.mkdir(parents=True, exist_ok=True)
        shutil.copy2(SRC / rel, dest)
    return tmp, tree


def drop_workspace(tmp: Path, tree: Path) -> None:
    subprocess.run(["git", "worktree", "remove", "--force", str(tree)], cwd=SRC, capture_output=True, check=False)  # justified: best effort; the rmtree and prune below finish the job
    shutil.rmtree(tmp, ignore_errors=True)
    subprocess.run(["git", "worktree", "prune"], cwd=SRC, capture_output=True, check=False)  # justified: housekeeping of a registration that is already gone



# ==================================================================================================
# CASES -- one per gate. Keep the mutation minimal and the expected text specific to the gate's own message.
# ==================================================================================================

#CASES-BEGIN
COMMON = "src/commonMain/kotlin/net/multigesture/kanama"
JVM = "src/jvmMain/kotlin"
IOS = "src/iosMain/kotlin/net/multigesture/kanama"
GEN = "docs/reference/generated"
WEBGEN = "web-runtime/src/commonMain/kotlin/net/multigesture/kanama/api/generated"
# Built by concatenation: a literal marker in this file would be a live marker for audit_stale_blockers.py.
BLOCKED_MARKER = "KANAMA" + "-BLOCKED(since:2026-01-01, file:CONTRIBUTING.md): red run"
ENV_PY = {"PYTHONPATH": "scripts"}  # relative: the case runs with its workspace as the working directory
WEB_BACKEND = "web-runtime/src/wasmJsMain/kotlin/net/multigesture/kanama/web/WebCommonGodotBackend.generated.kt"
VIRTUAL_TABLE = "processor/src/main/resources/net/multigesture/kanama/processor/virtual-signatures.tsv"


def py(name, *args):
    return script(name, *args)


# ---- gates found vacuous or silent by task 118 (fixed in the same change) -----------------------------
case("check_gdextension_modernization.py", py("check_gdextension_modernization.py"),
     [Create(f"{JVM}/net/multigesture/kanama/ZzRedRun.kt", 'package net.multigesture.kanama\nval zz = "classdb_construct_object2"\n')],
     "binds deprecated 'classdb_construct_object2'",
     "a desktop source binds a deprecated GDExtension function (this leg checked nothing before task 118: it scanned the deleted src/main)")
case("validate_godot_api.py", py("validate_godot_api.py"),
     [Edit(f"{COMMON}/api/Node.kt", "GET_PARENT_HASH = 3160264692L", "GET_PARENT_HASH = 3160264693L")],
     "Node.get_parent hash 3160264693 not in",
     "a MethodBind hash in the shared wrapper tree is off by one (this tree was not scanned before task 118)")
case("validate_godot_api.py (tree moved)", py("validate_godot_api.py"),
     [Rename("src/commonMain/kotlin", "src/commonMain/kotlin.off")],
     "src/commonMain/kotlin: no Kotlin sources found", "the shared wrapper tree is not where the validator looks (it must fail, not pass on what is left)")
case("audit_builtin_storage_sizes.py", py("audit_builtin_storage_sizes.py"),
     [Edit("ios/bootstrap/kanama_ios_shim.c", "#define KANAMA_IOS_PACKED_ARRAY_OPAQUE_SIZE 16", "#define KANAMA_IOS_PACKED_ARRAY_OPAQUE_SIZE 8")],
     "FAIL", "the iOS shim sizes a Packed*Array slot at 8 bytes")

# ---- check_* ---------------------------------------------------------------------------------------------
case("check_actual_public_surface.py", py("check_actual_public_surface.py"),
     [Edit(f"{JVM}/net/multigesture/kanama/api/MainThread.kt", "actual object MainThread {", "actual object MainThread {\n    fun redRunExtraMember() {}")],
     "declares public `fun redRunExtraMember/0`", "an `actual object` gains a public member its `expect` lacks")
case("check_android_remap_sources.py", py("check_android_remap_sources.py"),
     [Create(f"{JVM}/net/multigesture/kanama/ZzRedRun.kt", "package net.multigesture.kanama\nfun zz() = Files.readString(x)\n")],
     "forbidden after the Android remap", "a runtime source uses a fragment the Android remap cannot compile")
case("check_android_remap_sources.py (string literal)", py("check_android_remap_sources.py"),
     [Create(f"{JVM}/net/multigesture/kanama/ZzRedRun.kt", 'package net.multigesture.kanama\nval zzText: String = "a block-comment opener inside a string: /*"\nfun zz() = Files.readString(x)\n')],
     "forbidden after the Android remap", "a string literal holding a block-comment opener no longer blinds the audit to the rest of the file (task 119 finding 13)")
case("check_android_remap_sources.py (nested template)", py("check_android_remap_sources.py"),
     [Create(f"{JVM}/net/multigesture/kanama/ZzRedRun.kt", 'package net.multigesture.kanama\nval zzText: String = "${"/*"}"\nfun zz() = Files.readString(x)\n')],
     "ZzRedRun.kt:3: forbidden after the Android remap", "a template whose inner literal holds a block-comment opener no longer hides the following lines (task 138 item 28)")
case("check_android_remap_sources.py (reflective Method.invoke)", py("check_android_remap_sources.py"),
     [Create(f"{JVM}/net/multigesture/kanama/ZzRedRun.kt",
             'package net.multigesture.kanama\nfun zz(flag: Boolean) {\n  val entry =\n    Class.forName("x")\n      .getMethod("y", Boolean::class.javaPrimitiveType)\n  entry.invoke(null, flag)\n}\n')],
     "ZzRedRun.kt:6: `.invoke(` on the reflective Method `entry`", "a reflective `Method.invoke` in the runtime sources: the Android remap makes it `invokeWithArguments`, which Method lacks, and the runtime AAR stops compiling (the API exercise hook)")
case("check_android_remap_sources.py (invokeExact)", py("check_android_remap_sources.py"),
     [Edit("android/godot-plugin/buildSrc/src/main/kotlin/KanamaAndroidRemap.kt",
           'needle = ".invokeExact(",', 'needle = ".invokeExactDisabled(",')],
     "src/jvmMain/kotlin/binding/runtime/BuiltinFrame.kt", "the remap stops rewriting `.invokeExact(` (the builtin-call downcall would reach ART)")
case("check_android_remap_sources.py (address space)", py("check_android_remap_sources.py"),
     [Create(f"{JVM}/net/multigesture/kanama/ZzRedRun.kt",
             "package net.multigesture.kanama\nval zz = MemorySegment.NULL.reinterpret(Long.MAX_VALUE)\n")],
     "whole-address-space segment", "a runtime source addresses native memory by absolute address (fails on Android's tagged heap pointers)")
case("check_android_remap_sources.py (address space, wrapped)", py("check_android_remap_sources.py"),
     [Create(f"{JVM}/net/multigesture/kanama/ZzRedRun.kt",
             "package net.multigesture.kanama\nval zz =\n  MemorySegment.NULL\n    .reinterpret(Long.MAX_VALUE)\n")],
     "ZzRedRun.kt:3: whole-address-space segment", "a ktfmt-wrapped `MemorySegment.NULL⏎.reinterpret(` chain")
case("check_android_remap_sources.py (address space, NULL_SEGMENT)", py("check_android_remap_sources.py"),
     [Create(f"{JVM}/net/multigesture/kanama/ZzRedRun.kt",
             "package net.multigesture.kanama\nval zz = NULL_SEGMENT.reinterpret(Long.MAX_VALUE)\n")],
     "ZzRedRun.kt:2: whole-address-space segment", "Kanama's `NULL_SEGMENT` reinterpreted as the whole address space")
case("check_doc_claims.py", py("check_doc_claims.py"),
     [Edit("docs/exporting/web.md", "versioned JavaScript bridge (protocol 39)", "versioned JavaScript bridge (protocol 21)")],
     "stale or malformed claim", "a marked doc line states the wrong Web protocol")
case("check_doc_claims.py (iOS size, marked doc)", py("check_doc_claims.py"),
     [Edit("docs/exporting/ios.md", "roughly 168 MB debug", "roughly 200 MB debug")],
     "claims 200 MB debug / 110 MB release, but", "the iOS export guide states an iOS static library size the measurement in version-support.md does not")
case("check_doc_claims.py (iOS size, add-on README)", py("check_doc_claims.py"),
     [Edit("build.gradle.kts", "roughly 168 MB debug / 110 MB release", "roughly 168 MB debug / 88 MB release")],
     "build.gradle.kts", "the packaged iOS add-on README states a stale release size (it carries no marker; its size line is always checked)")
case("check_expect_no_defaults.py", py("check_expect_no_defaults.py"),
     [Create(f"{COMMON}/api/ZzRedRun.expect.kt", "package net.multigesture.kanama.api\n\nexpect fun redRun(a: Int = 1)\n")],
     "default argument(s) on an `expect` declaration", "an `expect fun` declares a default argument")
case("check_gate_evidence.py", py("check_gate_evidence.py"),
     [Edit("evidence/gates.json", '"schemaVersion": 1', '"schemaVersion": 2')],
     "ledger must be an object with schemaVersion 1", "the evidence ledger carries a wrong schema version")
case("check_godot_version_pin.py", py("check_godot_version_pin.py"),
     [Edit("gradle.properties", "kanamaGodotVersion=4.7.2.stable", "kanamaGodotVersion=4.7.1.stable")],
     "Godot version pins drifted", "gradle.properties pins a different Godot than CI")
case("check_kotlin_pins.py", py("check_kotlin_pins.py"),
     [Edit("templates/release-kit/build.gradle.kts", 'id("com.google.devtools.ksp") version "', 'id("com.google.devtools.ksp") version "0.')],
     "templates/release-kit/build.gradle.kts:3: ksp 0.2", "the release kit pins a KSP version the root build does not use")
case("check_kotlin_pins.py (unlisted pin)", py("check_kotlin_pins.py"),
     [Append("buildSrc/build.gradle.kts", '\nplugins { kotlin("plugin.serialization") version "2.0.0" }\n')],
     "kotlin 2.0.0 but the root build.gradle.kts pins", "a Gradle script the check does not list pins an older Kotlin plugin: the scan covers every tracked script")
case("check_kotlin_pins.py (root coordinate)", py("check_kotlin_pins.py"),
     [Append("build.gradle.kts", '\nval redRunStdlib = "org.jetbrains.kotlin:kotlin-stdlib:2.0.0"\n')],
     "build.gradle.kts:", "the root build itself names an older kotlin-stdlib coordinate (the scan covers the root script and `org.jetbrains.kotlin:kotlin-*` coordinates)")
case("check_kotlin_pins.py (version() call form)", py("check_kotlin_pins.py"),
     [Append("buildSrc/build.gradle.kts", '\nplugins { id("org.jetbrains.kotlin.plugin.serialization").version("2.0.0") }\n')],
     "buildSrc/build.gradle.kts:", "a plugin pinned with the `.version(\"x\")` call form instead of the infix `version \"x\"`")
case("check_kotlin_pins.py (gradle.properties)", py("check_kotlin_pins.py"),
     [Append("gradle.properties", "\nkotlinVersion=2.0.0\n")],
     "gradle.properties:", "gradle.properties names an older Kotlin version")
case("check_kotlin_pins.py (version catalog)", py("check_kotlin_pins.py"),
     [Create("gradle/libs.versions.toml", '[versions]\nkotlin = "2.0.0"\nksp = "2.3.12"\n\n[plugins]\nkotlin-jvm = { id = "org.jetbrains.kotlin.jvm", version = "2.0.0" }\n')],
     "gradle/libs.versions.toml:2: kotlin 2.0.0", "a new version catalog pins an older Kotlin (a [versions] key and an inline plugin entry)")
case("check_kotlin_pins.py (coroutines)", py("check_kotlin_pins.py"),
     [Edit("web-runtime/build.gradle.kts", '"org.jetbrains.kotlinx:kotlinx-coroutines-core:', '"org.jetbrains.kotlinx:kotlinx-coroutines-core:0.')],
     "web-runtime/build.gradle.kts:", "the Web runtime pins a kotlinx-coroutines the root build does not use")
case("check_kotlin_pins.py (Android coroutines literal)", py("check_kotlin_pins.py"),
     [Edit("android/godot-plugin/plugin/build.gradle.kts", '${providers.gradleProperty("kanamaAndroidCoroutinesVersion").get()}', "1.11.0")],
     "coroutines-android 1.11.0 but android/godot-plugin/gradle.properties pins", "the Android runtime compiles against the desktop coroutines again instead of the APK's (the property)")
case("check_kotlin_pins.py (Android coroutines doc)", py("check_kotlin_pins.py"),
     [Edit("android/godot-plugin/gradle.properties", "kanamaAndroidCoroutinesVersion=", "kanamaAndroidCoroutinesVersion=9.")],
     "docs/reference/version-support.md: coroutines-android", "the Android coroutines property moves but version-support.md still states the old version")
case("check_kotlin_pins.py (doc version)", py("check_kotlin_pins.py"),
     [Edit("docs/reference/version-support.md", "The current source build uses Kotlin `", "The current source build uses Kotlin `9")],
     "docs/reference/version-support.md: kotlin 9", "version-support.md states a Kotlin version the build does not use")
case("check_kotlin_pins.py (doc line reworded)", py("check_kotlin_pins.py"),
     [Edit("docs/reference/version-support.md", "The current source build uses Kotlin `", "The current source build is on Kotlin `")],
     "docs/reference/version-support.md: the kotlin pin is gone or reworded", "the version-support toolchain line is reworded: the check must fail, not pass on nothing")
case("check_ios_no_silent_stubs.py", py("check_ios_no_silent_stubs.py"),
     [Create(f"{COMMON}/api/ZzRedRun.kt", "package net.multigesture.kanama.api\n\nclass ZzRedRun {\n    fun redRun(): Boolean = false\n}\n")],
     "un-annotated silent stub", "a shared wrapper function whose whole body is a bare default, without a marker")
case("check_hand_code_budget.py", py("check_hand_code_budget.py"),
     [Create(f"{IOS}/api/ZzRedRun.kt", "package net.multigesture.kanama.api\n\nfun redRun(): Int = 1\n")],
     "ZzRedRun.kt: hand-written (3 lines) and not on the budget", "a new hand-written file appears under an api/ directory")
case("check_hand_code_budget.py (ratchet)", py("check_hand_code_budget.py"),
     [Append(f"{IOS}/api/IosGodotApi.kt", "\n// red run: a transitional file grows\n")],
     "transitional file grew", "a transitional hand file (iOS IosGodotApi.kt) grows past its line ratchet")
case("check_hand_code_budget.py (shared extension section)", py("check_hand_code_budget.py"),
     [Edit("scripts/generate_api_wrapper.py", "fun AnimationMixer.getParameter(path: String): Any? =\n    getIndexed(path)", "fun AnimationMixer.getParameter(path: String): Any? =\n    // red run\n    getIndexed(path)")],
     "SHARED_EXTENSION_SECTIONS[AnimationMixer]: grew to", "the shared extension section (task 129 D) grows past its ratchet")
case("check_hand_code_budget.py (package outside api/)", py("check_hand_code_budget.py"),
     [Create(f"{JVM}/net/multigesture/kanama/ZzRedRunPkg.kt", "package net.multigesture.kanama.api\n\nfun redRunPkg(): Int = 1\n")],
     "ZzRedRunPkg.kt: hand-written (3 lines) and not on the budget",
     "a hand-written file outside any api/ directory declares the API package")
case("check_hand_code_budget.py (fake GENERATED markers)", py("check_hand_code_budget.py"),
     [Append(f"{COMMON}/api/KanamaScript.kt", "\n// ===== BEGIN GENERATED ENUMS: RedRun (red run) =====\nfun redRunHidden(): Int = 1\n// ===== END GENERATED ENUMS: RedRun =====\n")],
     "carries a BEGIN/END GENERATED marker, but no generator owns a region in it",
     "hand code in a sugar file is wrapped in a GENERATED marker pair to hide it from the count")
case("check_hand_code_budget.py (decided without a decision)", py("check_hand_code_budget.py"),
     [Edit("scripts/hand_code_budget.json", '"decision": "Ray-query design, decisions D2', '"decisionX": "Ray-query design, decisions D2')],
     "decided without the `decision` field", "a decided (hand-shaped by decision) file loses the decision it cites")
case("check_hand_code_budget.py (sugar ratchet)", py("check_hand_code_budget.py"),
     [Append(f"{COMMON}/api/KanamaScript.kt", "\n// red run: a sugar file grows\nfun redRunSugar(): Int = 1\n")],
     "sugar file grew", "a sugar file (KanamaScript.kt) grows past its line ratchet")
case("check_hand_copies.py", py("check_hand_copies.py"),
     [Edit(f"{IOS}/annotations/Annotations.kt", "annotation class ExportPlaceholder(val placeholder: String)",
           "annotation class ExportPlaceholder(val text: String)")],
     "@ExportPlaceholder parameters differ", "the iOS copy of an annotation renames a parameter")
case("check_ios_shim_faults.py", py("check_ios_shim_faults.py"),
     [Edit("ios/bootstrap/kanama_ios_shim.c", '        kanama_ios_fault(__func__, "api-unresolved", NULL);', "        /* red run: guard returns silently */")],
     "returns without calling kanama_ios_fault", "a guarded early return in the iOS shim stops reporting its fault")
case("check_ios_single_call_returns.py", py("check_ios_single_call_returns.py"),
     [Create(f"{IOS}/binding/runtime/ZzRedRunReadBack.kt",
             "package net.multigesture.kanama.binding.runtime\n\n"
             "private fun redRunDispatch(buf: Long): Long = buf\n\n"
             "internal fun redRunReadBack(): Long {\n  val len = redRunDispatch(0L)\n  return redRunDispatch(len)\n}\n")],
     "double-call", "an iOS read-back measures with one call and fills with a second (the method runs twice)")
case("check_ios_single_call_returns.py (loop)", py("check_ios_single_call_returns.py"),
     [Create(f"{IOS}/binding/runtime/ZzRedRunReadBack.kt",
             "package net.multigesture.kanama.binding.runtime\n\n"
             "private fun redRunDispatch(buf: Long): Long = buf\n\n"
             "internal fun redRunReadBack(): Long {\n  var cap = 0L\n  while (cap < 4L) {\n    cap = redRunDispatch(cap + 1L)\n  }\n  return cap\n}\n")],
     "loop-call", "an iOS read-back grows its buffer and calls the entry point again in a loop")
def _single_call_case(label, body, expected, desc):
    """A red run of check_ios_single_call_returns.py: [body] is the Kotlin that runs the engine method twice."""
    case(f"check_ios_single_call_returns.py ({label})", py("check_ios_single_call_returns.py"),
         [Create(f"{IOS}/binding/runtime/ZzRedRunBypass.kt",
                 "package net.multigesture.kanama.binding.runtime\n\n"
                 "private fun redRunDispatch(buf: Long): Long = buf\n\n" + body)],
         expected, desc)


_single_call_case(
    "higher-level helper twice",
    "internal fun redRunTwice(): String {\n"
    "  val first = ObjectCalls.ptrcallRetUtf8(0L, 0L, 0L, 0L)\n  return first + ObjectCalls.ptrcallRetUtf8(0L, 0L, 0L, 0L)\n}\n"
    "internal fun redRunTwiceNoArgs(m: RawSegment, i: RawSegment): String =\n"
    "  ObjectCalls.ptrcallNoArgsRetString(m, i) + ObjectCalls.ptrcallNoArgsRetString(m, i)\n",
    "double-call", "a higher-level single-call helper (ptrcallRetUtf8 / ptrcallNoArgsRetString) is called twice in one function")
_single_call_case(
    "List(n) lambda",
    "internal fun redRunList(n: Int): List<Long> = List(n) { redRunDispatch(it.toLong()) }\n",
    "loop-call", "an entry point runs inside a List(n) { .. } initializer lambda")
_single_call_case(
    "map lambda",
    "internal fun redRunMap(n: Int): List<Long> = (0 until n).map { redRunDispatch(it.toLong()) }\n"
    "internal fun redRunMapIndexed(n: Int) = (0 until n).mapIndexed { i, _ -> redRunDispatch(i.toLong()) }\n"
    "internal fun redRunForEach(n: Int) { (0 until n).forEach { redRunDispatch(it.toLong()) } }\n"
    "internal fun redRunRepeat(n: Int) { repeat(n) { redRunDispatch(1L) } }\n",
    "inside a lambda of", "an entry point runs inside a map / mapIndexed / forEach / repeat lambda")
_single_call_case(
    "brace-less loops",
    "internal fun redRunFor(n: Int) {\n  for (i in 0 until n) redRunDispatch(i.toLong())\n}\n"
    "internal fun redRunWhile(n: Int) {\n  var i = 0\n  while (i < n)\n    i += redRunDispatch(1L).toInt()\n}\n",
    "inside a for loop", "an entry point is the body of a brace-less for / while")
_single_call_case(
    "callable-reference alias",
    "internal fun redRunAlias() {\n  val call = ::redRunDispatch\n  call(1L)\n  call(2L)\n}\n",
    "alias ", "an entry point is aliased with a callable reference and called twice through the alias")
case("check_ios_static_dispatch.py", py("check_ios_static_dispatch.py"),
     [Edit(f"{IOS}/binding/runtime/ObjectCalls.kt", "      kanama_ios_godot_ptrcall_static(methodBind, argTypes, argPtrs, argCount, retType, retOut)",
           "      kanama_ios_godot_ptrcall(0L, methodBind, argTypes, argPtrs, argCount, retType, retOut)")],
     "dispatcher-no-static", "a static call is routed through a guarded iOS entry point")
# The iOS self-test verdict (task 138 item 24) on a saved console log: the fixture is a clean three-summary run, the
# red runs drop or fail a summary. No device: the same selftest_verdict a device run applies to its console.
IOS_SELFTEST_LOG = "scripts/fixtures/ios_selftest_console.log"
IOS_SELFTEST_CHECK = ["bash", "scripts/ios_visual_smoke.sh", "--check-selftest-log", IOS_SELFTEST_LOG]
IOS_SELFTEST_SUMMARIES = ("[kanama][ios][c] PTRCALL SELFTEST MATRIX: 120 passed, 0 failed\n"
                          "[kanama][ios][kn] OBJECTCALLS SELFTEST: 308 passed, 0 failed faults=7 expected=7 in 412 ms\n"
                          "[kanama][ios][kn] OBJECTCALLS SELFTEST (frame 1): 25 passed, 0 failed faults=7 expected=7 in 38 ms\n"
                          "[kanama][ios][kn] OBJECTCALLS SELFTEST (api exercise): 90 passed, 0 failed, 5 weak, 0 known, 0 reach faults=7 expected=7 in 120 ms\n")
case("ios_visual_smoke.sh --check-selftest-log (no self-test)", IOS_SELFTEST_CHECK,
     [Edit(IOS_SELFTEST_LOG, IOS_SELFTEST_SUMMARIES, "")],
     "summary missing: /PTRCALL SELFTEST MATRIX", "a console with no self-test lines at all (a build without -PkanamaIosSelfTest=true) must fail, not pass as clean")
case("ios_visual_smoke.sh --check-selftest-log (no frame 1)", IOS_SELFTEST_CHECK,
     [Edit(IOS_SELFTEST_LOG, "[kanama][ios][kn] OBJECTCALLS SELFTEST (frame 1): 25 passed, 0 failed faults=7 expected=7 in 38 ms\n", "")],
     "summary missing: /OBJECTCALLS SELFTEST \\(frame 1\\)", "the app never reached its first frame: the frame-1 summary is missing while the other two passed")
case("ios_visual_smoke.sh --check-selftest-log (no api exercise)", IOS_SELFTEST_CHECK,
     [Edit(IOS_SELFTEST_LOG, "[kanama][ios][kn] OBJECTCALLS SELFTEST (api exercise): 90 passed, 0 failed, 5 weak, 0 known, 0 reach faults=7 expected=7 in 120 ms\n", "")],
     "summary missing: /OBJECTCALLS SELFTEST \\(api exercise\\)", "the generated API exercise did not run or did not finish: its summary is missing while the other three passed")
case("ios_visual_smoke.sh --check-selftest-log (api exercise row failed)", IOS_SELFTEST_CHECK,
     [Edit(IOS_SELFTEST_LOG, "(api exercise): 90 passed, 0 failed", "(api exercise): 89 passed, 1 failed")],
     "runtime self-test reported failures", "the API exercise summary reports a failed row with no FAIL line")
case("ios_visual_smoke.sh --check-selftest-log (1 failed)", IOS_SELFTEST_CHECK,
     [Edit(IOS_SELFTEST_LOG, "(frame 1): 25 passed, 0 failed", "(frame 1): 24 passed, 1 failed")],
     "runtime self-test reported failures", "a summary reports `N passed, 1 failed` with no FAIL line")
# The iOS device scripts' identity and row-count checks (task 138 item 10), proven red without a device: the bundle id
# is read out of a fixture .app's Info.plist by the same assert_built_bundle_id a device run applies before it
# installs, and the gate's verdict runs over a fixture results.tsv (a clean fresh + nine-demo matrix).
IOS_FIXTURE_APP = "scripts/fixtures/ios_built_app/Fixture.app"
IOS_BUNDLE_CHECK = ["bash", "scripts/ios_visual_smoke.sh", "--check-built-bundle-id", IOS_FIXTURE_APP, "net.multigesture.kanama.iosvisualsmoke"]
case("ios_visual_smoke.sh --check-built-bundle-id (other id)", IOS_BUNDLE_CHECK,
     [Edit(f"{IOS_FIXTURE_APP}/Info.plist", "<string>net.multigesture.kanama.iosvisualsmoke</string>", "<string>net.multigesture.kanama.thirdperson</string>")],
     "refusing to install a different app than the one it would launch", "the built .app carries another bundle id than the one the run launches (a stale preset)")
case("ios_visual_smoke.sh --check-built-bundle-id (no Info.plist)", IOS_BUNDLE_CHECK,
     [Delete(f"{IOS_FIXTURE_APP}/Info.plist")],
     "cannot read CFBundleIdentifier", "the built .app has no readable Info.plist: the check fails instead of passing on an empty id")
IOS_RESULTS_CHECK = ["bash", "scripts/ios_device_gate.sh", "--check-results", "scripts/fixtures/ios_gate_results.tsv"]
case("ios_device_gate.sh --check-results (row missing)", IOS_RESULTS_CHECK,
     [Edit("scripts/fixtures/ios_gate_results.tsv", "Starter-Kit-Racing\tPASS\t120\tStarter-Kit-Racing.log\n", "")],
     "a step did not run", "a planned demo step left no result row: no FAIL row, yet the matrix is incomplete")
case("ios_device_gate.sh --check-results (FAIL row)", IOS_RESULTS_CHECK,
     [Edit("scripts/fixtures/ios_gate_results.tsv", "Bunnymark\tPASS", "Bunnymark\tFAIL")],
     "one or more gate steps failed", "a step recorded FAIL")
# A --start-at run writes a SKIP row for every demo before the start demo (the fixture holds the rows such a run writes:
# seven SKIP rows, then FPS and the last demo); the verdict holds each planned step to exactly one row of its kind.
IOS_RESULTS_START_AT = "scripts/fixtures/ios_gate_results_start_at.tsv"
IOS_RESULTS_START_AT_CHECK = ["bash", "scripts/ios_device_gate.sh", "--demos-only", "--start-at", "Starter-Kit-FPS", "--check-results", IOS_RESULTS_START_AT]
case("ios_device_gate.sh --check-results (--start-at plan, row missing)", IOS_RESULTS_START_AT_CHECK,
     [Edit(IOS_RESULTS_START_AT, "godot-4-3d-third-person-controller\tPASS\t120\tgodot-4-3d-third-person-controller.log\n", "")],
     "no result row for the planned step 'godot-4-3d-third-person-controller'", "a --start-at run left no row for a demo it planned, while the SKIP rows keep the row total at the full matrix")
case("ios_device_gate.sh --check-results (--start-at plan, skipped demo not SKIP)", IOS_RESULTS_START_AT_CHECK,
     [Edit(IOS_RESULTS_START_AT, "Starter-Kit-Racing\tSKIP", "Starter-Kit-Racing\tPASS")],
     "comes before --start-at Starter-Kit-FPS but its row is PASS, not SKIP", "a demo before the --start-at demo has a PASS row: the rows are not the ones this plan writes")
case("check_jdk_locations_parity.py", py("check_jdk_locations_parity.py"),
     [Edit("bootstrap/bootstrap.c", '{"linux", "/usr/lib64/jvm", "", ""},', '{"linux", "/usr/lib65/jvm", "", ""},')],
     "differs from bootstrap/bootstrap.c", "one JDK location row differs between bootstrap.c and the plugins")
case("check_native_call_surface.py", py("check_native_call_surface.py"),
     [Create(f"{JVM}/net/multigesture/kanama/ZzRedRun.kt",
             "package net.multigesture.kanama\nval zz = GodotFFI.downcallHandle(null, FunctionDescriptor.of(ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE, ValueLayout.JAVA_BYTE))\n")],
     "native-call-surface] FAIL", "a new native downcall shape that NativeCallSurface does not prewarm")
case("check_objectcalls_parity.py", py("check_objectcalls_parity.py"),
     [Edit(f"{COMMON}/binding/runtime/ObjectCalls.expect.kt", "  fun ptrcallWithBoolArg(methodBind: RawSegment, instance: RawSegment, value: Boolean)",
           "  fun ptrcallWithBoolArgRedRun(methodBind: RawSegment, instance: RawSegment, value: Boolean)")],
     "does not match the referenced set", "the expect ObjectCalls renames a helper the shared tree calls")
API_EXERCISE_CHECK = py("generate_api_exercise.py", "--check")
case("generate_api_exercise.py --check (unclassified helper)", API_EXERCISE_CHECK,
     [Create(f"{COMMON}/binding/runtime/ZzRedRun.kt", "package net.multigesture.kanama.binding.runtime\n\ninternal fun zzRedRun() = ObjectCalls.redRunUnclassifiedHelper()\n")],
     "unclassified referenced helper redRunUnclassifiedHelper", "the shared sources call an `ObjectCalls` helper that no generated method calls with a MethodBind and `NON_BIND_HELPERS` does not list")
case("generate_api_exercise.py --check (call the scanner misses)", API_EXERCISE_CHECK,
     [Edit(f"{COMMON}/api/AnimationMixer.kt", "ObjectCalls.ptrcallWithBoolArg(Binds.setActiveBind, segment, active)", "ObjectCalls.ptrcallWithBoolArg(Binds.setActiveBind, (segment), active)")],
     "AnimationMixer.kt:172: `ObjectCalls.ptrcallWithBoolArg(` is a call the site scanner did not read", "a generated call spells its receiver in a way the site scanner does not read: the call must fail with its file:line, not drop out of the inventory")
case("generate_api_exercise.py --check (exclusion names no class)", API_EXERCISE_CHECK,
     [Edit("scripts/api_exercise_policy.py", '    "Window": _WINDOW,\n', '    "Window": _WINDOW,\n    "WindowRedRun": _WINDOW,\n')],
     "EXCLUDE_EXACT names WindowRedRun, a class the API lacks", "an exclusion entry names a class the API lacks (as a Godot bump that renames or drops a class leaves it)")
case("generate_api_exercise.py --check (static args name no method)", API_EXERCISE_CHECK,
     [Edit("scripts/api_exercise_policy.py", '    ("FileAccess", "open"): (\'scratch("fixture.txt")\', "FileAccess.READ"),\n', '    ("FileAccess", "open_red_run"): (\'scratch("fixture.txt")\', "FileAccess.READ"),\n')],
     "STATIC_ARGS names FileAccess.open_red_run, a method the API lacks", "a `STATIC_ARGS` entry names a method the API lacks (and the static it was for is left without arguments)")
case("generate_api_exercise.py --check (override no longer applies)", API_EXERCISE_CHECK,
     [Edit("scripts/api_exercise_policy.py", '    ("RegEx", "search"): ("F", _NEW_OBJECT),\n', '    ("RegEx", "search"): ("F", _NEW_OBJECT),\n    ("RegEx", "clear"): ("F", _NEW_OBJECT),\n')],
     "METHOD_OVERRIDES[RegEx.clear] sets case F, which the class rules already give it", "a `METHOD_OVERRIDES` entry sets the case the class rules already give the method (a void method made F): the entry changes nothing")
case("generate_api_exercise.py --check (static argument off its default)", API_EXERCISE_CHECK,
     [Edit("scripts/api_exercise_policy.py", 'Arg(\'"kanama-api-exercise"\', "a recognisable name for the OS temp directory the call creates (removed on free)"),', '\'"kanama-api-exercise"\',')],
     "but extension_api.json's default is", "a `STATIC_ARGS` argument differs from the API default without the `Arg(value, reason)` marker that says it is deliberate")
case("generate_api_exercise.py --check (rows hand-edited)", API_EXERCISE_CHECK,
     [Edit("src/commonMain/kotlin/net/multigesture/kanama/exercise/ApiExercise.generated.kt", "r.staticError(got.value, oracle)", "r.staticValue(got.value, oracle, weak = true)")],
     "ApiExercise.generated.kt is stale or hand-edited", "a generated API exercise row is edited by hand (an Error static made weak): the committed rows must be the generator's")
WEB_EXERCISE_CHECK = py("generate_web_exercise.py", "--check")
case("generate_web_exercise.py --check (shape without a row)", WEB_EXERCISE_CHECK,
     [Edit("scripts/generate_web_exercise.py", 'dict(shape="UTILITY_NOARGS_VOID", op=9,', 'dict(shape="UTILITY_NOARGS_VOID_RED_RUN", op=9,')],
     "UTILITY_NOARGS_VOID: the contract shape has no exercise row", "a Web call shape of the contract has no exercise row (here: the row names a shape the contract lacks)")
case("generate_web_exercise.py --check (rows hand-edited)", WEB_EXERCISE_CHECK,
     [Edit("web-runtime/src/web3dSmoke/web/kotlin-src/ApiExerciseProbe.kt", 'same(result, oracle(singleton("OS"), "get_name"))', "reach()")],
     "stale: web-runtime/src/web3dSmoke/web/kotlin-src/ApiExerciseProbe.kt", "a generated Web exercise row is edited by hand (an oracle row made a reach row): the committed probe must be the generator's")
# --check-jar over a jar built from scripts/fixtures/api_exercise/jar/ (zipped by the command): a shipped jar that
# carries an exercise class (a build made with -PkanamaApiExercise=true) must fail the check.
API_EXERCISE_JAR = ["python3", "-c",
    "import pathlib, subprocess, sys, tempfile, zipfile\n"
    "root = pathlib.Path('scripts/fixtures/api_exercise/jar')\n"
    "with tempfile.TemporaryDirectory() as tmp:\n"
    "    jar = pathlib.Path(tmp) / 'kanama.jar'\n"
    "    with zipfile.ZipFile(jar, 'w') as z:\n"
    "        [z.write(f, f.relative_to(root).as_posix()) for f in sorted(root.rglob('*')) if f.is_file()]\n"
    "    code = subprocess.run([sys.executable, 'scripts/generate_api_exercise.py', '--check-jar', str(jar)]).returncode\n"
    "sys.exit(code)\n"]
case("generate_api_exercise.py --check-jar (exercise class in a jar)", API_EXERCISE_JAR,
     [Create("scripts/fixtures/api_exercise/jar/net/multigesture/kanama/exercise/ApiExerciseRows.class", "placeholder\n")],
     "carries 1 API exercise class(es)", "a jar with an `exercise/` class (a -PkanamaApiExercise=true build) is published or installed: the check must fail it")
# The same over a runtime AAR (the jar zipped as its classes.jar): a flagged Android runtime AAR must fail the check
# packageMobileAddonAndroid, CI's Android lane and android_export_minified.sh run.
API_EXERCISE_AAR = ["python3", "-c",
    "import io, pathlib, subprocess, sys, tempfile, zipfile\n"
    "root = pathlib.Path('scripts/fixtures/api_exercise/jar')\n"
    "inner = io.BytesIO()\n"
    "with zipfile.ZipFile(inner, 'w') as z:\n"
    "    [z.write(f, f.relative_to(root).as_posix()) for f in sorted(root.rglob('*')) if f.is_file()]\n"
    "with tempfile.TemporaryDirectory() as tmp:\n"
    "    aar = pathlib.Path(tmp) / 'plugin-debug.aar'\n"
    "    with zipfile.ZipFile(aar, 'w') as z:\n"
    "        z.writestr('classes.jar', inner.getvalue())\n"
    "    code = subprocess.run([sys.executable, 'scripts/generate_api_exercise.py', '--check-jar', str(aar)]).returncode\n"
    "sys.exit(code)\n"]
case("generate_api_exercise.py --check-jar (exercise class in a runtime AAR)", API_EXERCISE_AAR,
     [Create("scripts/fixtures/api_exercise/jar/net/multigesture/kanama/exercise/ApiExerciseRows.class", "placeholder\n")],
     "carries 1 API exercise class(es)", "a runtime AAR whose classes.jar has an `exercise/` class (a -PkanamaApiExercise=true Android build) is packaged or exported for release: the check must fail it")
case("generate_api_exercise.py --check (RID source no row takes)", API_EXERCISE_CHECK,
     [Edit("scripts/api_exercise_policy.py", '    ("TextServer", "shaped"): "TextServer.create_shaped_text",\n',
           '    ("TextServer", "shaped"): "TextServer.create_shaped_text",\n    ("TextServer", "red_run"): "TextServer.create_font",\n')],
     "RID_ARG_SOURCES names TextServer argument `red_run`, which no B, D or F row of TextServer takes", "a `RID_ARG_SOURCES` entry names a RID argument no row passes (renamed or gone): it would make and free a RID for nothing")
case("generate_api_exercise.py --check (row arguments of no row)", API_EXERCISE_CHECK,
     [Edit("scripts/api_exercise_policy.py", '    ("ClassDB", "class_get_enum_list"): (\'"Node"\',),\n',
           '    ("ClassDB", "class_get_enum_list"): (\'"Node"\',),\n    ("ClassDB", "class_get_enum_list_red_run"): (\'"Node"\',),\n')],
     "ROW_ARGS names ClassDB.class_get_enum_list_red_run, which is not a B, D or F row", "a `ROW_ARGS` entry names a method that is no B or D row (renamed or gone): its arguments would silently go unused")
case("generate_api_exercise.py --check (known finding names no row)", API_EXERCISE_CHECK,
     [Edit("scripts/api_exercise_policy.py", "tuple[tuple[str, ...], str, str, str]] = {}",
           'tuple[tuple[str, ...], str, str, str]] = {("A", "CharFXTransform.font_red_run"): (("ios",), "got null", "red run", "red run")}')],
     "KNOWN_FINDINGS names A CharFXTransform.font_red_run, which is not a generated row", "a KNOWN_FINDINGS entry names a row the generator does not write (renamed or gone): it would hide nothing and stay forever")
case("generate_api_exercise.py --check (Variant type without a value)", API_EXERCISE_CHECK,
     [Edit("scripts/api_exercise_policy.py", '    "Vector3": ("Vector3(1, 2.5, -3)", "Vector3(1.0, 2.5, -3.0)", {}),\n', "")],
     "Variant type Vector3 (Variant.Type in extension_api.json) has no VARIANT_TYPE_VALUES entry", "a Variant type (a new one in a Godot bump) without a value would run through no generic Variant path")
case("generate_api_exercise.py --check (reach receiver of no row)", API_EXERCISE_CHECK,
     [Edit("scripts/api_exercise_policy.py", '    "Tween": ("r.tween()",', '    "TweenRedRun": ("r.tween()", "red run"),\n    "Tween": ("r.tween()",')],
     "REACH_RECEIVERS names TweenRedRun, which is the receiver of no reach row", "a `REACH_RECEIVERS` entry for a class no reach row uses (renamed or gone) would stay forever")
case("generate_api_exercise.py --check (rule order)", API_EXERCISE_CHECK,
     [Edit("scripts/generate_api_exercise.py", 'ORDER = "CABDFE"', 'ORDER = "ACBDFE"')],
     "does not match the rules run on the fixture API", "the case ranking puts property pairs before statics: the toy-API fixture and the committed page both catch it")
case("check_property_coverage.py", py("check_property_coverage.py"),
     [Edit(f"{COMMON}/api/CanvasItem.kt", "    var visible: Boolean", "    var visibleRedRun: Boolean")],
     "silently dropped", "a generated wrapper property disappears")
case("check_protocol_pins.py", py("check_protocol_pins.py"),
     [Edit("web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js", "KANAMA_WEB_PROTOCOL_VERSION = 39", "KANAMA_WEB_PROTOCOL_VERSION = 38")],
     "protocol version disagrees", "the bridge pins protocol 38 while the emitter says 39")
case("check_protocol_pins.py (sentinel)", py("check_protocol_pins.py"),
     [Edit("web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js", 'scriptErrorResult: "\\u001fkanama-script-error\\u001f",', 'scriptErrorResult: "\\u001fkanama-script-errorX\\u001f",')],
     "script-error sentinel", "the bridge's script-error sentinel differs from the emitter's GDScript constant")
case("check_protocol_pins.py (freed-check opcode)", py("check_protocol_pins.py"),
     [Edit("web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js", "KANAMA_WEB_OPCODE_FREED_CHECK = 1004;", "KANAMA_WEB_OPCODE_FREED_CHECK = 1005;")],
     "freed-check opcode", "the bridge asks the freed check on opcode 1005 while the proxy answers 1004")
case("check_pt_tag_tables.py", py("check_pt_tag_tables.py"),
     [Edit("scripts/generate_api_wrapper.py", '    "PT_VOID": 0,', '    "PT_VOID": 99,')],
     "value-mismatch VOID", "one copy of the iOS ptrcall tag table is renumbered")
case("check_public_signature_changes.py", py("check_public_signature_changes.py"),
     [Edit(f"{COMMON}/api/Node.kt", "    fun setProcessMode(mode: Node.ProcessMode) {", "    fun setProcessMode(mode: Node.ProcessMode, extra: Int) {")],
     "unannounced source break", "a public signature of Node changes without a CHANGELOG `Source break` line")
case("check_public_signature_changes.py (qualified owner)", py("check_public_signature_changes.py"),
     [Edit("CHANGELOG.md", "\n## Unreleased\n", "\n## Unreleased\n\n- **Source break:** red run: `Node.getChildCount` changed.\n"),
      Edit(f"{COMMON}/api/Node.kt", "    fun setProcessMode(mode: Node.ProcessMode) {", "    fun setProcessMode(mode: Node.ProcessMode, extra: Int) {")],
     "unannounced source break", "a marker naming `Node.getChildCount` announces that member only, not a Node.setProcessMode change")
case("check_public_signature_changes.py", py("check_public_signature_changes.py"),
     [Edit(f"{COMMON}/api/Label.kt", "    fun setText(text: String) {", "    fun setText(text: String, extra: Int) {")],
     "unannounced source break", "a public signature changes without a CHANGELOG `Source break` line")
case("check_public_signature_changes.py", py("check_public_signature_changes.py"),
     [Edit(f"{JVM}/net/multigesture/kanama/api/ScriptResources.kt", "    val resource: Resource,", "    val resourceRedRun: Resource,")],
     "unannounced source break", "a desktop-only declaration disappears and is not in the common tree (not a move to common, task 129 C)")
case("check_public_signature_changes.py", py("check_public_signature_changes.py"),
     [Edit("build.gradle.kts", "|    fun toC(value: Double): $storage", "|    fun toC(component: Double): $storage")],
     "unannounced source break", "the generated Real.kt (a build.gradle.kts template) changes a public signature")
case("check_public_signature_changes.py (annotations)", py("check_public_signature_changes.py"),
     [Edit("annotations/src/main/kotlin/net/multigesture/kanama/annotations/Annotations.kt", "annotation class GodotName(val name: String)", "annotation class GodotName(val name: String, val extra: Int)")],
     "unannounced source break", "a parameter is added to a script annotation (annotations/ is a snapshotted surface, task 137 item 9)")

case("check_public_signature_changes.py (Web parity)", py("check_public_signature_changes.py"),
     [Edit("web-runtime/src/commonMain/kotlin/net/multigesture/kanama/api/generated/GodotObject.kt",
           "  internal fun callQueued(method: String, value: Double) {", "  fun call(method: String, value: Double) {")],
     "new drift (not in WEB_GODOT_OBJECT_DRIFT): web-only GodotObject: fun call(method: String, value: Double)",
     "Web declares a `call` overload desktop does not have (a Unit-returning queued call hides the Variant `call`, task 129 E review S3)")
case("check_public_signature_changes.py (Web path-form set)", py("check_public_signature_changes.py"),
     [Edit("web-runtime/src/commonMain/kotlin/net/multigesture/kanama/api/generated/GodotObject.kt",
           "  internal fun setIndexedDouble(propertyPath: String, value: Double) {", "  fun set(propertyPath: String, value: Double) {")],
     "new drift (not in WEB_GODOT_OBJECT_DRIFT): web-only GodotObject: fun set(propertyPath: String, value: Double)",
     "Web publishes its typed set_indexed arm as a `set` overload again: set(\"position:x\", 5.0) would write the component on Web and nothing on desktop")
case("check_public_signature_changes.py (Web GodotObject extension)", py("check_public_signature_changes.py"),
     [Edit("web-runtime/src/commonMain/kotlin/net/multigesture/kanama/api/WebFacades.kt",
           "fun GodotObject.asObject(): GodotObject = this",
           "fun GodotObject.asObject(): GodotObject = this\n\nfun GodotObject.get(property: String): Long = 0L")],
     "new drift (not in WEB_GODOT_OBJECT_DRIFT): web-only <top-level>: fun GodotObject.get(property: String): Long",
     "a Web-only top-level extension on GodotObject takes a call desktop sends to another member (fix round 2)")
case("check_public_signature_changes.py (Web call on another class)", py("check_public_signature_changes.py"),
     [Edit("web-runtime/src/commonMain/kotlin/net/multigesture/kanama/api/generated/Node.kt", "  fun queueFree() {", "  fun call(method: String) {}\n\n  fun queueFree() {")],
     "new drift (not in WEB_GODOT_OBJECT_DRIFT): web-only Node: fun call(method: String)",
     "Web declares a `call` overload on Node (not GodotObject) that desktop does not: a call that compiles on both silently takes it (fix round 3)")
case("check_public_signature_changes.py (Web callDeferred extension)", py("check_public_signature_changes.py"),
     [Edit("web-runtime/src/commonMain/kotlin/net/multigesture/kanama/api/WebFacades.kt",
           "fun GodotObject.asObject(): GodotObject = this",
           "fun GodotObject.asObject(): GodotObject = this\n\nfun Node.callDeferred(method: String) {}")],
     "new drift (not in WEB_GODOT_OBJECT_DRIFT): web-only <top-level>: fun Node.callDeferred(method: String)",
     "a Web-only top-level `callDeferred` extension on a class other than GodotObject (fix round 3)")
case("check_public_signature_changes.py (Web generic-receiver extension)", py("check_public_signature_changes.py"),
     [Edit("web-runtime/src/commonMain/kotlin/net/multigesture/kanama/api/WebFacades.kt",
           "fun GodotObject.asObject(): GodotObject = this",
           "fun GodotObject.asObject(): GodotObject = this\n\nfun <T : GodotObject> T.zzRedRun(): T = this")],
     "new drift (not in WEB_GODOT_OBJECT_DRIFT): web-only <top-level>: fun <T: GodotObject> T.zzRedRun(): T",
     "a Web-only extension on a generic receiver `T : GodotObject` that desktop does not have (fix round 3)")
case("check_public_signature_changes.py (Web generic Node receiver)", py("check_public_signature_changes.py"),
     [Edit("web-runtime/src/commonMain/kotlin/net/multigesture/kanama/api/WebFacades.kt",
           "fun GodotObject.asObject(): GodotObject = this",
           "fun GodotObject.asObject(): GodotObject = this\n\nfun <T : Node> T.zzRedRun(): T = this")],
     "new drift (not in WEB_GODOT_OBJECT_DRIFT): web-only <top-level>: fun <T: Node> T.zzRedRun(): T",
     "a Web-only extension on a generic receiver `T : Node` that desktop does not have (fix round 3)")
case("check_public_signature_changes.py (Web differing default)", py("check_public_signature_changes.py"),
     [Edit("web-runtime/src/commonMain/kotlin/net/multigesture/kanama/api/generated/GodotObject.kt", "  fun signal(name: String): GodotSignal = GodotSignal(this, name)",
           "  fun signal(name: String): GodotSignal = GodotSignal(this, name)\n\n"
           "  fun getMeta(name: String, defaultValue: Any? = 1): Any? = null")],
     "new drift (not in WEB_GODOT_OBJECT_DRIFT): differing default GodotObject: fun getMeta(name: String, defaultValue: Any? = ...): Any?",
     "Web declares a desktop member with a different default value (`= 1` against desktop's `= null`); the snapshot shows both as `= ...` (fix round 3)")
case("check_public_signature_changes.py (Web drift list stale)", py("check_public_signature_changes.py"),
     [Edit("web-runtime/src/commonMain/kotlin/net/multigesture/kanama/api/WebFacades.kt",
           "fun GodotObject.asObject(): GodotObject = this", "")],
     "stale WEB_GODOT_OBJECT_DRIFT entry (the drift is gone; delete it): web-only <top-level>: fun GodotObject.asObject(): GodotObject",
     "a known Web drift is gone but its WEB_GODOT_OBJECT_DRIFT entry stays (the list must shrink)")
case("check_typed_enums.py", py("check_typed_enums.py"),
     [Edit(f"{COMMON}/api/Node.kt", "    fun setProcessMode(mode: Node.ProcessMode) {", "    fun setProcessMode(mode: Long) {")],
     "typed_enums] FAIL", "an enum parameter goes back to a raw Long")
case("check_unapplied_annotations.py", py("check_unapplied_annotations.py"),
     [Create(f"{JVM}/net/multigesture/kanama/ZzRedRun.kt", "package net.multigesture.kanama\nimport net.multigesture.kanama.annotations.OnReady\nclass ZzRedRun\n")],
     "imports @OnReady but never applies it", "a lifecycle annotation is imported and never applied")
case("check_web_callback_flush.py", py("check_web_callback_flush.py"),
     [Edit("web-runtime/src/wasmJsMain/kotlin/net/multigesture/kanama/web/Main.kt",
           "    userScript { KanamaWebProjectRegistry.enterTree(record.scriptId, record.script) }\n    commands.flush()",
           "    userScript { KanamaWebProjectRegistry.enterTree(record.scriptId, record.script) }")],
     "without flushing the command buffer", "a Web callback boundary stops flushing the command buffer")
case("check_web_callback_flush.py (constructor)", py("check_web_callback_flush.py"),
     [Edit("web-runtime/src/wasmJsMain/kotlin/net/multigesture/kanama/web/Main.kt",
           "    commands.flush()\n    handle\n  } catch",
           "    handle\n  } catch")],
     "runs a script constructor", "the constructor boundary stops flushing the command buffer")
case("check_web_typed_enums.py", py("check_web_typed_enums.py"),
     [Edit(f"{WEBGEN}/Node.kt", "  fun setProcessMode(mode: Node.ProcessMode) {", "  fun setProcessMode(mode: Long) {")],
     "web_typed_enums] FAIL", "a Web wrapper enum parameter goes back to a raw Long")
case("check_wrapper_generator.py", py("check_wrapper_generator.py"),
     [Edit(f"{COMMON}/api/Node.kt", "    fun setProcessMode(mode: Node.ProcessMode) {", "    fun setProcessMode(mode: Node.ProcessMode ) {")],
     "single-tree drift-gate", "a generated wrapper is hand-edited (one stray space)")

case("check_wrapper_generator.py (stale platform file)", py("check_wrapper_generator.py"),
     [Create(f"{JVM}/net/multigesture/kanama/api/SceneTreeHandle.kt", "package net.multigesture.kanama.api\n\nclass SceneTreeHandle\n")],
     "unexplained file(s) in a platform api directory", "a retired per-platform wrapper is restored under src/jvmMain/.../api: it compiles, but nothing generates or lists it (task 119 finding 34)")
case("check_wrapper_generator.py (missing hand file)", py("check_wrapper_generator.py"),
     [Delete(f"{IOS}/api/ScriptAccessSelfTest.kt")],
     "listed platform hand file(s) no longer exist", "a file listed in PLATFORM_HAND_FILES is deleted: the list would keep excusing a later file of that name")
case("check_wrapper_generator.py (iOS region body)", py("check_wrapper_generator.py"),
     [Edit(f"{IOS}/binding/runtime/ObjectCalls.kt", "      ptrcallDispatch(methodBind.address(), instance.address(), null, null, 0, PT_RID, ret.ptr)\n      RID(ret.value)",
           "      ptrcallDispatch(methodBind.address(), instance.address(), null, null, 0, PT_INT, ret.ptr)\n      RID(ret.value)")],
     "single-tree drift-gate", "a generated iOS ObjectCalls helper keeps its name and parameters but its body (here a PT_* tag) changes: the region is compared by content, not by member names (task 119 finding 4)")
case("check_wrapper_generator.py (keep-reachable sweep)", py("check_wrapper_generator.py"),
     [Edit("scripts/check_wrapper_generator.py", "reads_receiver = (receiver or owner) in refcounted and", "reads_receiver = False and")],
     "the sweep misses a known-bad shape", "the keep-reachable sweep stops seeing a RefCounted body that reads `segment` without its fence (expression body, header on several lines, no checkOpen(); task 138 item 6)")
case("check_wrapper_generator.py (expect ObjectCalls)", py("check_wrapper_generator.py"),
     [Append(f"{COMMON}/binding/runtime/ObjectCalls.expect.kt", "\nval redRunHandEdit = 1\n")],
     "ObjectCalls.expect.kt", "the generated `expect object ObjectCalls` file is hand-edited (it is in the drift compare since task 119 finding 25)")

# ---- audit_* -----------------------------------------------------------------------------------------------
case("audit_api_wrapper_inheritance.py", py("audit_api_wrapper_inheritance.py"),
     [Edit(f"{COMMON}/api/CanvasItem.kt", "open class CanvasItem(handle: GodotHandle) : Node(handle)", "open class CanvasItem(handle: GodotHandle) : RefCounted(handle)")],
     "inheritance_audit] FAIL", "a wrapper extends an unrelated handle type", env=ENV_PY)
case("audit_claims.sh", ["bash", "scripts/audit_claims.sh"],
     [Delete("scripts/check_protocol_pins.py")],
     "FAIL: the check script is missing", "one of the aggregated check scripts is deleted (it used to be a SKIPPED line and exit 0)",
     env={"KANAMA_TASKS_DIR": "/nonexistent", "CI": "", "KANAMA_ALLOW_SKIP": ""})
case("audit_generator_object_policy.py", py("audit_generator_object_policy.py"),
     [Edit("scripts/generate_api_shell_wrappers.py", '{"Callable", "DirAccess", "FileAccess", "Object", "SceneTree"}', '{"Callable", "DirAccess", "FileAccess", "SceneTree"}')],
     "UNSAFE_DEFAULT_EXCLUDES is missing", "the shell generator stops excluding `Object`", env=ENV_PY)
case("audit_generator_shape_policy.py", py("audit_generator_shape_policy.py"),
     [Edit(f"{JVM}/binding/runtime/ObjectCalls.kt", "BuiltinTypes.initDictionary(", "BuiltinTypes.initDictionaryRedRun(")],
     "Dictionary argument is not initialized through BuiltinTypes.initDictionary", "a Dictionary-argument helper stops using the explicit initializer", env=ENV_PY)
case("audit_godot_object_script_paths.py", py("audit_godot_object_script_paths.py"),
     [Edit(f"{COMMON}/api/GodotObject.kt", "ptrcallWithStringNameAndVariantArg(Binds.objectSetBind", "ptrcallWithStringNameAndVariantArgX(Binds.objectSetBind")],
     "must call the Object.set MethodBind", "GodotObject.set stops calling the Object.set bind")
case("audit_ptrcall_helper_layouts.py", py("audit_ptrcall_helper_layouts.py"),
     [Edit(f"{JVM}/binding/runtime/ObjectCalls.kt", "    return ret.get(JAVA_INT, 0).toLong() and 0xffff_ffffL\n  }\n\n  /** Calls [methodBind] with no arguments and RID return value. */",
           "    return ret.get(JAVA_LONG, 0).toLong() and 0xffff_ffffL\n  }\n\n  /** Calls [methodBind] with no arguments and RID return value. */")],
     "uses JAVA_LONG", "a uint32 helper reads a 64-bit slot")
case("audit_replicated_script_properties.py", py("audit_replicated_script_properties.py", "example_project"),
     [Create("example_project/zz_redrun.kt", "class ZzRedRun\n"),
      Create("example_project/zz_redrun.tscn",
             '[gd_scene load_steps=3 format=3]\n\n[ext_resource type="Script" path="res://zz_redrun.kt" id="1_s"]\n\n'
             '[sub_resource type="SceneReplicationConfig" id="Rep_1"]\nproperties/0/path = NodePath(".:health")\n\n'
             '[node name="Root" type="Node"]\nscript = ExtResource("1_s")\n\n'
             '[node name="Sync" type="MultiplayerSynchronizer" parent="."]\nreplication_config = SubResource("Rep_1")\n')],
     "does not expose replicated property 'health'", "a scene replicates a property its Kotlin script does not export")
case("audit_runtime_node_lookups.py", py("audit_runtime_node_lookups.py", "example_project"),
     [Create("example_project/zz_redrun.kt",
             'import net.multigesture.kanama.annotations.OnProcess\nclass ZzRedRun {\n  @OnProcess\n  fun tick(delta: Double) {\n    self.requireAs("Child", ::Node)\n  }\n}\n')],
     "runtime_node_lookup] FAIL", "a per-frame callback resolves a node path")
case("audit_singleton_refcounted_policy.py", py("audit_singleton_refcounted_policy.py"),
     [Edit(f"{COMMON}/api/Engine.kt", "fun registerSingleton(name: String, instance: GodotObject)", "fun registerSingletonRedRun(name: String, instance: GodotObject)")],
     "Engine.registerSingleton wrapper not found", "the Engine wrapper loses registerSingleton")
case("audit_singleton_refcounted_policy.py", py("audit_singleton_refcounted_policy.py"),
     [Edit("scripts/generate_api_wrapper.py", 'require(!instance.isClass("RefCounted"))', 'require(!instance.isClass("Node"))')],
     "METHOD_PRECONDITIONS has no", "the generator's precondition row loses the RefCounted guard")
case("audit_stale_blockers.py", py("audit_stale_blockers.py"),
     [Edit("CONTRIBUTING.md", "# Contributing to Kanama", "# Contributing to Kanama\n<!-- " + BLOCKED_MARKER + " -->")],
     "stale_blockers] FAIL", "a stale-blocker marker whose blocker no longer holds")
case("audit_value_type_wrappers.py", py("audit_value_type_wrappers.py", "--strict"),
     [Edit(f"{COMMON}/types/Quaternion.kt", "    f.putDouble(2, weight)\n    f.call(QuaternionMethods.slerp, 2)",
           "    f.putReal(2, 0, narrowReal(weight))\n    f.call(QuaternionMethods.slerp, 2)")],
     "Quaternion.slerp passes 1 Godot float arg", "a Godot `float` argument is marshalled as a real_t component array")
case("check_builtin_coverage.py", py("check_builtin_coverage.py"),
     [Edit(f"{COMMON}/types/Vector2.kt", "  fun orthogonal(): Vector2 = raw(rawY, -rawX)", "  fun orthogonalVector(): Vector2 = raw(rawY, -rawX)")],
     "Vector2.orthogonal: no Kotlin member", "a value-type method loses its Kotlin member")
case("check_builtin_coverage.py (stdlib names)", py("check_builtin_coverage.py"),
     [Edit(f"{COMMON}/builtins/GodotString.kt", "fun String.godotHexToInt(", "fun String.hexToInt(")],
     "fun String.hexToInt collides with kotlin-stdlib", "a generated String extension takes a kotlin-stdlib name (task 134 D2)")
case("generate_builtin_ops.py", py("generate_builtin_ops.py", "--check"),
     [Edit(f"{COMMON}/types/Vector2.kt", "  fun orthogonal(): Vector2 = raw(rawY, -rawX)", "  fun orthogonal(): Vector2 = raw(-rawY, rawX)")],
     "generate_builtin_ops] FAIL", "a generated value-type member is edited by hand")
case("audit_vararg_ptrcalls.py", py("audit_vararg_ptrcalls.py"),
     [Create(f"{COMMON}/api/ZzRedRun.kt",
             'package net.multigesture.kanama.api\n\nprivate val redRunBind by lazy { ObjectCalls.getMethodBind("Object", "call", 1L) }\nfun redRun(x: RawSegment) { ObjectCalls.ptrcallNoArgs(redRunBind, x) }\n')],
     "is vararg and must use dynamic Object.call", "a vararg Godot method is wrapped through ptrcall")
case("audit_variant_marshalling_policy.py", py("audit_variant_marshalling_policy.py"),
     [Edit(f"{JVM}/binding/runtime/BuiltinTypes.kt", 'else -> error("Unsupported Variant value type:', 'else -> println("Unsupported Variant value type:')],
     "variant_marshalling_policy_audit] FAIL", "the Variant marshaller coerces an unknown value instead of failing")
case("audit_wrapper_abi_policy.py", py("audit_wrapper_abi_policy.py", "--strict"),
     [Edit(f"{COMMON}/api/Node.kt", "return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getParentBind, segment))", "return Node.wrap(ObjectCalls.ptrcallNoArgsRetBool(Binds.getParentBind, segment))")],
     "return helper slot is bool", "a wrapper selects a bool return helper for an object return", env=ENV_PY)
case("audit_wrapper_signatures.py", py("audit_wrapper_signatures.py"),
     [Edit(f"{COMMON}/api/Node.kt", "return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getParentBind, segment))", "return Node.wrap(ObjectCalls.ptrcallNoArgsRetBool(Binds.getParentBind, segment))")],
     "wrapper_signature_audit] FAIL", "a wrapper's return helper disagrees with extension_api.json")

# ---- the other python gates local_ci runs ------------------------------------------------------------------
case("type_coverage_audit.py", py("type_coverage_audit.py"),
     [Edit(f"{JVM}/binding/runtime/BuiltinTypes.kt", "is Vector2 ->", "is Vector2Zz ->")],
     "lack Variant marshal coverage", "a value type loses its Variant write branch")
for name, args, target, expect, how in [
    ("api_wrapper_coverage.py --check", ("api_wrapper_coverage.py", "--markdown", f"{GEN}/api-coverage.md", "--check"), f"{GEN}/api-coverage.md", "FAIL stale markdown", "the committed coverage page is hand-edited"),
    ("api_wrapper_generator_report.py --check", ("api_wrapper_generator_report.py", "--markdown", f"{GEN}/wrapper-generator-report.md", "--check"), f"{GEN}/wrapper-generator-report.md", "FAIL", "the committed generator report is hand-edited"),
    ("migrate_enum_constants.py --check", ("migrate_enum_constants.py", "--table", f"{GEN}/enum-migration.md", "--check"), f"{GEN}/enum-migration.md", "is stale", "the committed enum migration table is hand-edited"),
    ("ios_handwritten_report.py --check", ("ios_handwritten_report.py", "--check"), f"{GEN}/ios-backend-handwritten.md", "FAIL stale", "the committed iOS handwritten report is hand-edited"),
    ("generate_gates_index.py --check", ("generate_gates_index.py", "--markdown", f"{GEN}/gates.md", "--check"), f"{GEN}/gates.md", "FAIL stale gates index", "the committed gates index is hand-edited"),
    ("generate_api_exercise.py --check", ("generate_api_exercise.py", "--check"), f"{GEN}/api-exercise.md", "is stale", "the committed API exercise inventory is hand-edited"),
    ("generate_virtual_signature_table.py --check", ("generate_virtual_signature_table.py", "--check"), VIRTUAL_TABLE, "STALE", "the committed virtual-signature table is hand-edited"),
    ("generate_engine_method_table.py --check", ("generate_engine_method_table.py", "--check"), "processor/src/main/resources/net/multigesture/kanama/processor/engine-methods.tsv", "STALE", "the committed engine-method table is hand-edited"),
    ("generate_web_backend.py --check", ("generate_web_backend.py", "--output", WEB_BACKEND, "--check"), WEB_BACKEND, "generated Web backend dispatch drift", "the generated Web backend dispatch is hand-edited (a real token: comments and wrapping are normalised away)"),
    ("generate_web_wrappers.py --check", ("generate_web_wrappers.py", "--check"), f"{WEBGEN}/Node.kt", "FAIL", "a generated Web wrapper is hand-edited"),
]:
    case(name, py(*args), [Append(target, "\nval redRunHandEdit = 1\n" if target.endswith(".kt") else "\nred run: hand edit\n")], expect, how)
case("generate_gates_index.py --check (squash-merge dates)", py("generate_gates_index.py", "--markdown", f"{GEN}/gates.md", "--check"),
     [Edit("scripts/generate_gates_index.py", 'page_date if page_date != "—" and git_date != "—" and page_date <= git_date else git_date', "git_date")],
     "FAIL stale gates index", "the generator re-derives every first-landed date instead of keeping the page's: #361's script dates 2026-10-16 on the page (its branch commit) but 2026-10-17 in main's history (the squash), so main goes stale")
case("generate_gates_index.py --check (later page date)", py("generate_gates_index.py", "--markdown", f"{GEN}/gates.md", "--check"),
     [Edit(f"{GEN}/gates.md", "`scripts/generate_api_exercise.py` | 2026-10-16 |", "`scripts/generate_api_exercise.py` | 2099-01-01 |")],
     "FAIL stale gates index", "a first-landed date on the page is hand-edited to a day after the script's history: only an earlier (squash-merge) date is kept")
case("api_wrapper_generator_report.py --check (dropped default)", py("api_wrapper_generator_report.py", "--markdown", f"{GEN}/wrapper-generator-report.md", "--check"),
     [Edit("scripts/generate_api_wrapper.py", '    ("Node3D", "look_at", "up"): "Vector3.UP",\n', "")],
     "FAIL stale markdown", "a Godot default the generator expressed is dropped: the parameter turns required and the report's \"Godot Defaults Not Expressed\" list no longer matches")

case("generate_web_wrappers.py --check (inert facades)", py("generate_web_wrappers.py", "--check"),
     [Append("web-runtime/src/commonMain/kotlin/net/multigesture/kanama/api/WebFacades.kt", "\nobject Marshalls\n")],
     "declares Godot class Marshalls", "a hand-written Web file declares a Godot class WEB_INERT_FACADES does not list (task 129 E)")
case("generate_web_wrappers.py --check (RID transport)", py("generate_web_wrappers.py", "--check"),
     [Edit("scripts/generate_web_wrappers.py", '"RID": "RID", "typedarray::RID": "List<RID>"}', '"RID": "GodotObject", "typedarray::RID": "List<RID>"}')],
     "the generic tier does not render a RID slot as RID", "the generic tier renders a RID slot as an object handle again (the old never-cross adaptation)")
case("generate_web_wrappers.py --check (Web-local utilities)", py("generate_web_wrappers.py", "--check"),
     [Edit("scripts/web_local_utilities.py", '"sin": (1, "sin: " + LIBM,', '"sin": (0, "sin: " + LIBM + "",')],
     "bound 0 on a body that calls a libm transcendental", "a Web-local transcendental utility claims bit equality (task 129 E review B1)")
case("generate_web_wrappers.py --check (unclassified utility)", py("generate_web_wrappers.py", "--check"),
     [Edit("scripts/web_local_utilities.py", '"step_decimals": "an editor helper', '"step_decimals_gone": "an editor helper')],
     "utility step_decimals: in no table", "a Godot utility function is in none of the Web tables, so it would cross unreviewed (task 129 E review B1)")
case("generate_api_shell_wrappers.py --fail-if-candidates", py("generate_api_shell_wrappers.py", "--from-skip-report", "build/wrapper-generator/skips.txt", "--dry-run", "--fail-if-candidates"),
     [Delete(f"{COMMON}/api/Curve2D.kt"),
      Append("build/wrapper-generator/skips.txt", "\nCurve2D.foo object return wrapper is missing for Curve2D\n")],
     "candidates=1", "a wrapper file is gone while the skip report names it (the shell generator then has a candidate to write)",
     env=ENV_PY, pre=[py("api_wrapper_generator_report.py", "--markdown", f"{GEN}/wrapper-generator-report.md", "--check")])
case("sync_kdoc_from_godot_docs.py --check", py("sync_kdoc_from_godot_docs.py", "--godot-docs", os.environ.get("GODOT_DOCS", "/nonexistent"), "--check"),
     [Edit(f"{COMMON}/api/Node.kt", "    fun getParent(): Node? {", "    /** red run: a KDoc block the Godot class docs do not contain */\n    fun getParent(): Node? {")],
     "changed_files=1", "a wrapper KDoc block differs from the Godot class docs", requires_env="GODOT_DOCS")
case("sync_kdoc_from_godot_docs.py --check (module class)", py("sync_kdoc_from_godot_docs.py", "--godot-docs", os.environ.get("GODOT_DOCS", "/nonexistent"), "--check"),
     [Edit(f"{COMMON}/api/ZIPReader.kt", " * Opens the zip archive at the given `path` and reads its file index.", " * Opens the zip archive (red run: hand-edited KDoc).")],
     "changed_files=1", "a KDoc block of a module-defined class (ZIPReader, documented under modules/zip/doc_classes) is hand-edited (task 138 item 25)", requires_env="GODOT_DOCS")
case("sync_kdoc_from_godot_docs.py --check (no module docs)",
     py("sync_kdoc_from_godot_docs.py", "--godot-docs", "build/redrun-kdoc/doc/classes", "--scope", "api", "--classes", "Node,ZIPReader", "--check"),
     [Rename("build/redrun-kdoc/modules", "build/redrun-kdoc/modules-off")],
     "0 module doc dirs were found", "the docs directory is a source checkout's doc/classes but its modules/*/doc_classes are gone: module-defined wrappers would go unchecked (task 138 item 25 review N5)",
     pre=[[PY, "-c", "import os, pathlib, shutil; docs = pathlib.Path(os.environ['GODOT_DOCS']); root = pathlib.Path('build/redrun-kdoc'); shutil.rmtree(root, ignore_errors=True); "
           "(root / 'doc/classes').mkdir(parents=True); (root / 'modules/zip/doc_classes').mkdir(parents=True); "
           "shutil.copy(docs / 'Node.xml', root / 'doc/classes'); shutil.copy(docs.parent.parent / 'modules/zip/doc_classes/ZIPReader.xml', root / 'modules/zip/doc_classes')"]],
     requires_env="GODOT_DOCS")

# ---- shell gates ---------------------------------------------------------------------------------------------
case("audit_swallowed_failures.py", py("audit_swallowed_failures.py"),
     [Append("scripts/install-git-hooks.sh", "\nfalse || true\n")],
     "no `# justified: <why>` and no known idiom", "a gate script swallows a failure (`|| true`) without saying why")
case("audit_swallowed_failures.py (stale table)", py("audit_swallowed_failures.py"),
     [Edit("scripts/README-gates.md", "justified: probe; the exit status is the test", "justified: probe; the exit status is NOT the test")],
     "audit table in scripts/README-gates.md is stale", "the generated audit table no longer matches the `# justified:` comments")
case("check_shell_lint.sh", ["bash", "scripts/check_shell_lint.sh"],
     [Create("scripts/zz_redrun.sh", '#!/usr/bin/env bash\ncd /tmp\nrm -rf $UNSET_VAR/\n')],
     "zz_redrun.sh", "a new gate script carries a shellcheck warning (unquoted variable, unchecked cd)")


# ---- script portability (task 138 item 34): one case per shape ---------------------------------------------------
def portability_case(name, body, expect, how, path="scripts/zz_redrun_portability.sh"):
    case(f"check_script_portability.py ({name})", py("check_script_portability.py"),
         [Create(path, "#!/usr/bin/env bash\n" + body)], expect, how)


portability_case("rg", "rg -q foo file\n", "[banned-tool] `rg`", "a script calls ripgrep, which the CI runner does not have (kanama#328)")
portability_case("rg probe", "if command -v rg >/dev/null; then :; fi\n", "[banned-tool] `rg`", "a script probes for ripgrep: a conditional convenience tool is two paths, each machine exercises one")
portability_case("gsed", "gsed -n 1p file\n", "[banned-tool] `gsed`", "a script calls the Homebrew GNU tool name")
portability_case("unlisted command", "sha256sum file\n", "[unlisted-command] `sha256sum`", "a script calls a command outside the portable list (not on a stock macOS)")
portability_case("grep -P", "grep -oP 'a\\K.*' file\n", "[grep-P]", "a script uses PCRE grep, which BSD grep lacks")
portability_case("mktemp suffix", 'f="$(mktemp /tmp/kanama_x.XXXXXX.kt)"\n', "[mktemp-suffix]", "a mktemp template has a suffix after its X's: BSD/macOS mktemp then creates one fixed path that concurrent runs share")
portability_case("fixed /tmp default", 'LOG="${KANAMA_X_LOG:-/tmp/kanama_x.log}"\n', "[fixed-tmp]", "a script defaults a log to a fixed /tmp path: two concurrent runs interleave one file (two runtime smokes did)")
portability_case("sed -i", "sed -i 's/a/b/' file\n", "[sed-i] `sed -i` differs", "a script edits in place with GNU-only sed -i (outside scripts/portable.sh)")
portability_case("sed -i ''", "sed -i '' 's/a/b/' file\n", "[sed-i] `sed -i ''` is the BSD spelling", "a script edits in place with the BSD-only spelling")
portability_case("declare -A", "declare -A map\n", "[bash4] `declare -A`", "a script uses an associative array (bash 4; macOS ships 3.2)")
portability_case("mapfile", "mapfile -t lines < file\n", "[bash4] `mapfile`", "a script uses mapfile (bash 4)")
portability_case("case modification", 'name="${1,,}"\n', "[bash4] case-modifying", "a script lower-cases with ${x,,} (bash 4)")
portability_case("pipe-ampersand", "cat file |& grep x\n", "[bash4] `|&`", "a script pipes stderr with |& (bash 4)")
portability_case("coproc", "coproc cat\n", "[bash4] `coproc`", "a script uses coproc (bash 4)")
portability_case("macOS-only command", "xcrun simctl list\n", "[macos-only] `xcrun`", "a CI-invocable script calls a macOS-only command outside a DEVICE_ONLY script")
portability_case("stat -f", "stat -f %m file\n", "[macos-only]", "a script reads a modification time with BSD stat")
portability_case("date -j", "date -j -f %s 0\n", "[macos-only]", "a script parses a date with BSD date")
portability_case("GNU-only option", "date -d yesterday +%s\n", "[gnu-only]", "a script uses GNU date -d")
portability_case("timeout wrapper", "timeout 5 sleep 1\n", "[banned-tool] `timeout`", "a script runs a command under GNU `timeout` (not on a stock macOS): the wrapper is judged, not skipped (review S3)")
portability_case("stdbuf wrapper", "stdbuf -oL cat file | cat\n", "[banned-tool] `stdbuf`", "a script runs a command under GNU `stdbuf` (not on a stock macOS)")
portability_case("wrapped banned tool", "env A=1 nice rg x file\n", "[banned-tool] `rg`", "the command behind a chain of wrappers is still judged")
portability_case("bash -c", "bash -c 'rg x file'\n", "[banned-tool] `rg`", "ripgrep inside a `bash -c '...'` string (review N1)")
portability_case("sh -c double-quoted", 'sh -c "cat file | rg x"\n', "[banned-tool] `rg`", "ripgrep inside a `sh -c \"...\"` string")
portability_case("eval", 'eval "rg x file"\n', "[banned-tool] `rg`", "ripgrep inside an `eval \"...\"` string")
portability_case("one-line case", "case $1 in a) rg x file;; esac\n", "[banned-tool] `rg`", "ripgrep in a single-line `case $x in a) cmd;; esac` clause (the scanner used to swallow it)")
portability_case("cygpath unguarded", 'p="$(cygpath -u "$1")"\n', "[platform-only] `cygpath`", "a script calls the Windows-only cygpath outside a listed guarded use (review N3)")
portability_case("cmake unguarded", "cmake -S . -B build\n", "[platform-only] `cmake`", "a script calls cmake (a toolchain install, not on every machine) outside a listed guarded use")
portability_case("bash -o pipefail -c", "bash -o pipefail -c 'rg x file'\n", "[banned-tool] `rg`", "ripgrep inside a `bash -o pipefail -c '...'` string: options before -c no longer hide the script (review round 2)")
portability_case("bash -O extglob -c", 'bash -O extglob -c "cat file | rg x"\n', "[banned-tool] `rg`", "ripgrep inside a `bash -O extglob -c \"...\"` string")
portability_case("find -exec", "find . -name '*.kt' -exec rg x {} \\;\n", "[banned-tool] `rg`", "ripgrep run by `find -exec ... \\;` (review round 2)")
portability_case("find -exec +", "find . -type f -exec rg x {} +\n", "[banned-tool] `rg`", "ripgrep run by `find -exec ... +`")
portability_case("env -C", "env -C /tmp ls\n", "[gnu-only] `env -C DIR`", "a script changes directory with GNU-only `env -C` (BSD env has no -C)")
portability_case("env -C wrapped tool", "env -C /tmp rg x\n", "[banned-tool] `rg`", "the command behind `env -C DIR` is judged, not the directory")
portability_case("sudo -u wrapped tool", "sudo -u root rg x\n", "[banned-tool] `rg`", "the command behind `sudo -u USER` is judged, not the user name")
case("check_script_portability.py (extra guarded-tool use)", py("check_script_portability.py"),
     [Append("scripts/check_shell_lint.sh", "\nshellcheck --version >/dev/null\n")],
     "[platform-use-count] `shellcheck` is used 4 times here (lines ", "one more `shellcheck` in a script whose listed use is guarded: the entry pins its use count, so the extra, unguarded use fails, and the finding lists every use so the reader can see the extra one (review rounds 2 and 3)")
case("check_script_portability.py (python bash -o pipefail -c)", py("check_script_portability.py"),
     [Create("scripts/zz_redrun_portability.py", 'import subprocess\nsubprocess.run(["bash", "-o", "pipefail", "-c", "rg x"])\n')],
     "[banned-tool] `rg`", "a Python gate hides ripgrep in a [\"bash\", \"-o\", \"pipefail\", \"-c\", ...] command line")
case("check_script_portability.py (python find -exec)", py("check_script_portability.py"),
     [Create("scripts/zz_redrun_portability.py", 'import subprocess\nsubprocess.run(["find", ".", "-exec", "rg", "x", "{}", ";"])\n')],
     "[banned-tool] `rg`", "a Python gate hides ripgrep behind `find -exec`")
case("check_script_portability.py (python shell=True)", py("check_script_portability.py"),
     [Create("scripts/zz_redrun_portability.py", 'import subprocess\nsubprocess.run("ls | rg x", shell=True)\n')],
     "[banned-tool] `rg`", "a Python gate hides ripgrep in a shell=True command line (review N1)")
case("check_script_portability.py (python bash -c)", py("check_script_portability.py"),
     [Create("scripts/zz_redrun_portability.py", 'import subprocess\nsubprocess.run(["bash", "-c", "rg x"])\n')],
     "[banned-tool] `rg`", "a Python gate hides ripgrep in a [\"bash\", \"-c\", ...] command line")
case("check_script_portability.py (python timeout)", py("check_script_portability.py"),
     [Create("scripts/zz_redrun_portability.py", 'import subprocess\nsubprocess.run(["timeout", "5", "sleep", "1"])\n')],
     "[banned-tool] `timeout`", "a Python gate shells out through GNU timeout")
case("check_script_portability.py (python subprocess)", py("check_script_portability.py"),
     [Create("scripts/zz_redrun_portability.py", 'import subprocess\nsubprocess.run(["rg", "x"])\n')],
     "[banned-tool] `rg`", "a Python gate shells out to ripgrep")
case("check_script_portability.py (workflow step)", py("check_script_portability.py"),
     [Edit(".github/workflows/ci.yml", "          scripts/local_ci.sh --skip-docs", "          rg --version\n          scripts/local_ci.sh --skip-docs")],
     ".github/workflows/ci.yml", "a CI workflow step calls ripgrep")
case("check_script_portability.py (selftest mode)", py("check_script_portability.py"),
     [Edit("scripts/ios_visual_smoke.sh", "selftest_verdict() {\n", "selftest_verdict() {\n  rg -q x /dev/null\n")],
     "scripts/ios_visual_smoke.sh", "ripgrep returns to the CI-invocable --check-selftest-log path of the macOS smoke (the kanama#328 failure)")
case("check_script_portability.py (device path)", py("check_script_portability.py"),
     [Edit("scripts/ios_visual_smoke.sh", "selftest_verdict() {\n", "selftest_verdict() {\n  xcrun simctl list >/dev/null\n")],
     "[macos-only] `xcrun`", "a macOS-only command moves above the device-only marker into the CI-invocable --check-selftest-log path")
case("check_script_portability.py (stale device marker)", py("check_script_portability.py"),
     [Edit("scripts/ios_visual_smoke.sh", "# portability: device-only below", "# portability: device-only marker removed")],
     "[device-marker]", "a DEVICE_ONLY script loses its marker line (every macOS-only command in it would be unexplained)")
case("check_script_portability.py (marker above the CI block)", py("check_script_portability.py"),
     [Edit("scripts/ios_visual_smoke.sh", 'if [[ "${1:-}" == "--check-selftest-log" ]]; then\n', '# portability: device-only below (moved up)\nif [[ "${1:-}" == "--check-selftest-log" ]]; then\n')],
     "[device-marker] the `# portability: device-only below` marker is above the end of the CI-invocable block",
     "the device-only marker moves above the --check-selftest-log block: the code CI runs on Linux would be exempt from the portable rule (review N2)")
case("check_script_portability.py --self-test", py("check_script_portability.py", "--self-test"),
     [Edit("scripts/portable.sh", '  rm -f "${file}.portable-bak"\n', "  :\n")],
     "sed_inplace:", "sed_inplace leaves its backup file behind")
case("check_script_portability.py --self-test (tokeniser)", py("check_script_portability.py", "--self-test"),
     [Edit("scripts/check_script_portability.py", 'self.add(line, "banned-tool", f"', 'self.add(line, "banned-tool-off", f"')],
     "--self-test FAIL", "the gate stops classifying a banned tool (its own shape table must say so)")


# ---- web gate self-test: break the gate, its own --self-test must go red ------------------------------------
case("check_no_local_paths.py --self-test", py("web/check_no_local_paths.py", "--self-test"),
     [Edit("scripts/web/check_no_local_paths.py", "                for offset in _hits(data, needle):", "                for offset in []:")],
     "--self-test FAIL: expected the text and the binary leak", "the scan stops finding leaks (the gate is broken; its self-test must say so)")

# ---- smokes that drive Godot (slow: --slow, needs KANAMA_GODOT_BIN) ---------------------------------------------
GODOT = os.environ.get("KANAMA_GODOT_BIN", "/nonexistent-godot")
HELLO = "example_project/HelloScript.kt"
# A fresh worktree has no addon jar and no .godot import cache; the smokes' 30-60 s pattern timeouts are not
# meant to cover a cold build and a cold import, so warm both first (the same two steps every smoke starts with).
WARM_EXAMPLE = ["bash", "-c", f'./gradlew -p . syncExampleAddonJar >/dev/null && "{GODOT}" --headless --import --path example_project']
case("tool_smoke.sh", ["bash", "scripts/tool_smoke.sh", GODOT],
     [Edit(HELLO, "HelloScript(file)._ready health=", "HelloScript(file)._readyX health=")],
     "missing pattern", "the example script no longer logs its scene-delivered properties: the failure comes from the editor run's log, not the plugin-copy pre-check",
     requires_env="KANAMA_GODOT_BIN", slow=True)
case("tool_smoke.sh (plugin copies)", ["bash", "scripts/tool_smoke.sh", GODOT],
     [Append("example_project/addons/kanama_tools/plugin.gd", "\n# red run: this copy now differs from the starter template\n")],
     "plugin copies differ", "the example project's editor plugin drifts from the starter template's copy (the cheap pre-check)",
     requires_env="KANAMA_GODOT_BIN", slow=True)
# Only deleting the wait leaves a race: the crash needs Godot's doc thread to still be loading its cache when the
# export quits. Linux runners always lose it; a Mac wins it about half the time (the thread finishes, its queued
# update runs before the editor frees the docs, exit 1). So on the failing export the mutation also restarts the
# thread in _exit_tree, as the first scan's late extension load does: the crash is then deterministic on every host
# (10/10 on macOS), and the real wait still covers that restart (exit 1 with both lines in place). The restart is
# limited to the export so the smoke's plain `--editor --quit` run, which would crash the same way, stays green.
PLUGIN_DOCS_WAIT = ("    _finish_pending_editor_docs()\n    var editor_settings",
                    '    if OS.get_cmdline_args().has("--export-release"):\n'
                    "        GDExtensionManager.extensions_reloaded.emit()\n    var editor_settings")
case("tool_smoke.sh (failing export exit)", ["bash", "scripts/tool_smoke.sh", GODOT],
     [Edit("templates/starter/addons/kanama_tools/plugin.gd", *PLUGIN_DOCS_WAIT),
      Edit("example_project/addons/kanama_tools/plugin.gd", *PLUGIN_DOCS_WAIT)],
     "failing export of a fresh project exited 134", "the editor plugin restarts the editor's doc thread on exit and no longer waits for it: a failing export of a project without .godot/ crashes in Godot's exit path instead of exiting 1 (the restart makes the crash deterministic, not a race only Linux loses)",
     requires_env="KANAMA_GODOT_BIN", slow=True)
case("runtime_smoke.sh", ["bash", "scripts/runtime_smoke.sh", GODOT],
     [Edit(HELLO, "HelloScript(file)._ready health=", "HelloScript(file)._readyX health=")],
     "missing pattern", "the example script no longer logs its scene-delivered properties",
     requires_env="KANAMA_GODOT_BIN", slow=True)
JVM_OBJECT_CALLS = "src/jvmMain/kotlin/binding/runtime/ObjectCalls.kt"
case("runtime_smoke.sh (progress read-back)", ["bash", "scripts/runtime_smoke.sh", GODOT],
     [Edit(JVM_OBJECT_CALLS, "(BuiltinTypes.readArrayScalars(argArray).firstOrNull() as? Number)?.toDouble()",
           "(emptyList<Any?>().firstOrNull() as? Number)?.toDouble()"),
      Edit(JVM_OBJECT_CALLS, '?: error("ResourceLoader.load_threaded_get_status wrote no progress into its out-Array")', "?: 0.0")],
     "missing pattern", "the threaded-load progress is never read back from Godot's out-Array (a silent 0.0): a LOADED request must report 1.0",
     requires_env="KANAMA_GODOT_BIN", slow=True)
case("runtime_smoke.sh (call container elements)", ["bash", "scripts/runtime_smoke.sh", GODOT],
     [Edit(JVM_OBJECT_CALLS, "BuiltinTypes.readVariantScalar(retVariant, arena, ownElements = true)",
           "BuiltinTypes.readVariantScalar(retVariant, arena, ownElements = false)")],
     "missing pattern", "the RefCounted elements of an Object.call container result come back borrowed again (task 122 item 3): RegEx.search_all's fresh matches die with the return Variant",
     requires_env="KANAMA_GODOT_BIN", slow=True)
case("runtime_smoke.sh (typed-signal self-test)", ["bash", "scripts/runtime_smoke.sh", GODOT],
     [Edit("src/jvmMain/kotlin/binding/runtime/SignalEmitFrame.kt", "      if (!send) return\n", "      if (!send || count >= 0) return\n")],
     "SIGNAL SELFTEST: [1-9][0-9]* passed, 0 failed", "a typed `emit(...)` never reaches Godot (the emit frame skips emit_signal): the first-frame self-test must report failures, not pass",
     requires_env="KANAMA_GODOT_BIN", slow=True)
case("runtime_smoke.sh (self-test setting)", ["bash", "scripts/runtime_smoke.sh", GODOT],
     [Edit("src/jvmMain/kotlin/binding/runtime/SignalSelfTest.kt", "setting = { boolSetting(SETTING) },", "setting = { false },")],
     "did not run the self-test", "the kanama/debug/signal_self_test project setting stops opting a run in (the Android smoke can only use the setting)",
     requires_env="KANAMA_GODOT_BIN", slow=True)
# The API exercise's rows hold the argument shapes: two component writes swapped in the desktop Vector3 helper (a
# property-pair row) and the two Vector2 arguments of a static swapped (a static row reading its result back)
# must each turn the exercise's own check red, the first one the smoke makes, naming the helper.
case("runtime_smoke.sh (API exercise pair rows)", ["bash", "scripts/runtime_smoke.sh", GODOT],
     [Edit(JVM_OBJECT_CALLS,
           "    value: Vector3,\n  ) {\n    Arena.ofConfined().use { arena ->\n      val vec = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)\n"
           "      GodotRealSegment.writeRaw(vec, 0, value.rawX)\n      GodotRealSegment.writeRaw(vec, 1, value.rawY)\n",
           "    value: Vector3,\n  ) {\n    Arena.ofConfined().use { arena ->\n      val vec = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)\n"
           "      GodotRealSegment.writeRaw(vec, 0, value.rawY)\n      GodotRealSegment.writeRaw(vec, 1, value.rawX)\n")],
     "API EXERCISE FAIL: ptrcallNoArgsRetVector3 ptrcallWithVector3Arg", "ptrcallWithVector3Arg writes x and y into each other's slot: the API exercise's Vector3 property-pair row must fail, not pass",
     requires_env="KANAMA_GODOT_BIN", slow=True)
RAY_ARGS = ("        GodotRealSegment.writeRaw(toCell, 1, to.rawY)\n        maskCell.set(JAVA_INT, 0, checkedMask)\n"
            "        BuiltinTypes.initArrayOfRids(excludeCell, exclude)\n        val args = arena.allocate(ADDRESS, 4)\n")
case("runtime_smoke.sh (API exercise static arguments)", ["bash", "scripts/runtime_smoke.sh", GODOT],
     [Edit(JVM_OBJECT_CALLS, RAY_ARGS + "        args.setAtIndex(ADDRESS, 0, fromCell)\n        args.setAtIndex(ADDRESS, 1, toCell)\n",
           RAY_ARGS + "        args.setAtIndex(ADDRESS, 0, toCell)\n        args.setAtIndex(ADDRESS, 1, fromCell)\n")],
     "API EXERCISE FAIL: ptrcallWithTwoVector2UInt32RIDListArgsRetObject", "PhysicsRayQueryParameters2D.create's helper passes `to` as `from` and back: the static row must read the swap back from its result",
     requires_env="KANAMA_GODOT_BIN", slow=True)
# A const (B) row compares the wrapper with the Variant path on the same object: the second double argument of
# Noise.get_noise_2d's helper written as a float32 into its float64 cell reaches the engine as another value, and
# the row must name the helper.
NOISE_ARGS = "      arg0.set(JAVA_DOUBLE, 0, first)\n      arg1.set(JAVA_DOUBLE, 0, second)\n"
case("runtime_smoke.sh (API exercise const rows)", ["bash", "scripts/runtime_smoke.sh", GODOT],
     [Edit(JVM_OBJECT_CALLS,
           "    first: Double,\n    second: Double,\n  ): Double {\n    Arena.ofConfined().use { arena ->\n"
           "      val arg0 = arena.allocate(JAVA_DOUBLE)\n      val arg1 = arena.allocate(JAVA_DOUBLE)\n" + NOISE_ARGS,
           "    first: Double,\n    second: Double,\n  ): Double {\n    Arena.ofConfined().use { arena ->\n"
           "      val arg0 = arena.allocate(JAVA_DOUBLE)\n      val arg1 = arena.allocate(JAVA_DOUBLE)\n"
           "      arg0.set(JAVA_DOUBLE, 0, first)\n      arg1.set(JAVA_FLOAT, 0, second.toFloat())\n")],
     "API EXERCISE FAIL: ptrcallWithTwoDoubleArgsRetDouble", "ptrcallWithTwoDoubleArgsRetDouble writes its second argument as a float32 into the float64 cell: the const row (wrapper vs Object.call) must fail, not pass",
     requires_env="KANAMA_GODOT_BIN", slow=True)
# An Array return buffer must hold a constructed Array: the engine resizes it in place for a C++ Vector<Variant>
# return (Animation.method_track_get_params). Left zero-filled, the const row crashes the process, and the smoke
# names the row the trace saw last.
case("runtime_smoke.sh (API exercise Array return crash)", ["bash", "scripts/runtime_smoke.sh", GODOT],
     [Edit(JVM_OBJECT_CALLS,
           "      val ret = arena.allocate(8L, 8L)\n      BuiltinTypes.constructEmptyArray(ret)\n      bindPtrcall(methodBind, instance, args, ret)\n      return try {\n        reader(ret)",
           "      val ret = arena.allocate(8L, 8L)\n      bindPtrcall(methodBind, instance, args, ret)\n      return try {\n        reader(ret)")],
     "in row B Animation.method_track_get_params", "callArrayReturn hands the engine a zero-filled Array return buffer: the const row of a Vector<Variant> return crashes the run, and the smoke must name that row",
     requires_env="KANAMA_GODOT_BIN", slow=True)
case("runtime_smoke.sh (API exercise known finding passes)", ["bash", "scripts/runtime_smoke.sh", GODOT],
     [Edit("src/commonMain/kotlin/net/multigesture/kanama/exercise/ApiExercise.generated.kt",
           '"Tween.interpolate_value")', '"Tween.interpolate_value", known = "desktop", knownWhy = "red run", knownFailure = "expected")')],
     "known finding no longer reproduces on desktop", "a KNOWN_FINDINGS row passes on its platform: the run must fail until the entry is removed (a quarantine that outlives its gap)",
     requires_env="KANAMA_GODOT_BIN", slow=True)
# A known finding excuses only its own failure: the Vector3 pair row marked known on the desktop for a ByteArray
# encoder gap, then broken by the Vector3 argument swap, must still FAIL (its failure is not the known one).
case("runtime_smoke.sh (API exercise known finding fails differently)", ["bash", "scripts/runtime_smoke.sh", GODOT],
     [Edit("src/commonMain/kotlin/net/multigesture/kanama/exercise/ApiExercise.generated.kt",
           '"Area3D.gravity_direction")', '"Area3D.gravity_direction", known = "desktop", knownWhy = "red run", knownFailure = "unsupported arg type ByteArray")'),
      Edit(JVM_OBJECT_CALLS,
           "    value: Vector3,\n  ) {\n    Arena.ofConfined().use { arena ->\n      val vec = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)\n"
           "      GodotRealSegment.writeRaw(vec, 0, value.rawX)\n      GodotRealSegment.writeRaw(vec, 1, value.rawY)\n",
           "    value: Vector3,\n  ) {\n    Arena.ofConfined().use { arena ->\n      val vec = arena.allocate(GodotReal.SIZE_BYTES * 3, GodotReal.ALIGN_BYTES)\n"
           "      GodotRealSegment.writeRaw(vec, 0, value.rawY)\n      GodotRealSegment.writeRaw(vec, 1, value.rawX)\n")],
     "not the known finding on desktop", "a KNOWN_FINDINGS row fails for another reason than its known one: it must fail, not print KNOWN",
     requires_env="KANAMA_GODOT_BIN", slow=True)
case("runtime_smoke.sh (API exercise setting)", ["bash", "scripts/runtime_smoke.sh", GODOT],
     [Edit("src/jvmMain/kotlin/binding/runtime/ApiExerciseHook.kt", "setting = { SignalSelfTest.boolSetting(SETTING) },", "setting = { false },")],
     "API EXERCISE: [1-9][0-9]* passed, 0 failed", "the kanama/debug/api_exercise project setting stops opting a run in (the smoke and the Android build can only use the setting)",
     requires_env="KANAMA_GODOT_BIN", slow=True)
# The publish guard: a flagged build must not publish, package or install. The command passes when Gradle refuses
# a flagged `publishKanamaToMavenLocal --dry-run`; with the guard's publish clause gone, the dry run succeeds.
API_EXERCISE_GUARD = ["bash", "-c",
    'out="$(./gradlew -p . publishKanamaToMavenLocal packageMobileAddonIos -PkanamaApiExercise=true --dry-run 2>&1)"; '
    'if printf "%s" "$out" | grep -q "refusing to publish, package or install"; then echo "guard refused the flagged build"; exit 0; fi; '
    'printf "%s\\n" "$out" | tail -n 20; echo "API EXERCISE GUARD MISSING: a flagged build was allowed to publish"; exit 1']
case("build.gradle.kts API exercise publish guard", API_EXERCISE_GUARD,
     [Edit("build.gradle.kts", '          it.startsWith("publish") ||\n          it.startsWith("package")', "          false")],
     "API EXERCISE GUARD MISSING", "the -PkanamaApiExercise=true guard stops refusing the publish and package tasks: the rows would ship",
     timeout=1800, slow=True)
case("hot_reload_smoke.sh", ["bash", "scripts/hot_reload_smoke.sh", GODOT],
     [Edit(HELLO, "HelloScript(file)._ready health=", "HelloScript(f)._ready health=")],
     "missing marker", "the reloadable script line the smoke rewrites is gone, so no marker can reach the log",
     requires_env="KANAMA_GODOT_BIN", slow=True)
case("hot_reload_in_process_smoke.sh", ["bash", "scripts/hot_reload_in_process_smoke.sh", GODOT],
     [Edit(HELLO, "HelloScript(file)._ready health=", "HelloScript(f)._ready health=")],
     "hot_reload_in_process_smoke] FAIL --", "the reloadable script line the smoke rewrites is gone, so no marker can reach the log",
     pre=[WARM_EXAMPLE],
     requires_env="KANAMA_GODOT_BIN", slow=True)
case("check_exported_scene_properties_selftest.sh", ["bash", "scripts/check_exported_scene_properties_selftest.sh", GODOT],
     [Edit("scripts/check_exported_scene_properties.gd", "lost script property", "lost a thing")],
     "the missing override property was not reported", "the scene check stops saying `lost script property` (its own red run is inside the self-test)",
     requires_env="KANAMA_GODOT_BIN", slow=True)
case("check_exported_scene_properties_selftest.sh (--require-converted)", ["bash", "scripts/check_exported_scene_properties_selftest.sh", GODOT],
     [Edit("scripts/check_exported_scene_properties.gd", 'OS.get_cmdline_user_args().has("--require-converted")', "false")],
     "--require-converted passed", "the scene check stops honouring --require-converted: a caller that expects converted scenes would pass an export that converted none",
     requires_env="KANAMA_GODOT_BIN", slow=True)
case("check_exported_scene_properties_selftest.sh (conversion off)", ["bash", "scripts/check_exported_scene_properties_selftest.sh", GODOT],
     [Edit("scripts/check_exported_scene_properties.gd", "\tif not bool(ProjectSettings.get_setting(CONVERT_SETTING, true)) and not _has_feature_override(CONVERT_SETTING):", "\tif false:")],
     "on a project that turns conversion off", "the scene check ignores editor/export/convert_text_resources_to_binary=false: --require-converted fails a project whose scenes ship as text with their values",
     requires_env="KANAMA_GODOT_BIN", slow=True)
case("tool_smoke.sh (export guard check)",["bash", "scripts/tool_smoke.sh", GODOT],
     [Create("example_project/export_guard_red.tscn",
             '[gd_scene load_steps=2 format=3]\n\n[ext_resource type="Script" path="res://ExportGuardRed.kt" id="1"]\n\n[node name="Red" type="Node"]\nscript = ExtResource("1")\n'),
      Create("example_project/ExportGuardRed.kt", "class ExportGuardRed\n")],
     "ExportGuardRed.kt (not in kanama-scripts.jar:", "a scene attaches a .kt script that kanama-scripts.jar does not contain: the editor export guard's check must name it",
     requires_env="KANAMA_GODOT_BIN", slow=True)
GUARD_ONE_CLICK_NAME = ("    return RegEx.create_from_string(ONE_CLICK_EXPORT_PATTERN).search(target.substr(prefix.length())) != null\n",
                        "    return true\n")
case("tool_smoke.sh (export guard one-click clean-up)", ["bash", "scripts/tool_smoke.sh", GODOT],
     [Edit("templates/starter/addons/kanama_tools/kanama_export_guard.gd", *GUARD_ONE_CLICK_NAME),
      Edit("example_project/addons/kanama_tools/kanama_export_guard.gd", *GUARD_ONE_CLICK_NAME)],
     "[export_guard_discard] FAIL: discard_export deleted", "the guard takes any export into the temp folder for a one-click deploy: a user's /tmp/game.apk would be deleted after a failed check",
     requires_env="KANAMA_GODOT_BIN", slow=True)
case("export_guard_selftest.sh",["bash", "scripts/export_guard_selftest.sh", GODOT],
     [Edit("templates/starter/addons/kanama_tools/kanama_export_guard.gd", "    if problems.is_empty():\n        return\n    var message", "    if true:\n        return\n    var message")],
     "the stale export exited 0", "the export guard stops finding problems: a stale-jar export must still exit non-zero with the message (its own green and red exports are inside the self-test)",
     requires_env="KANAMA_GODOT_BIN", slow=True)
case("check_jdk_lookup_parity.sh", ["bash", "scripts/check_jdk_lookup_parity.sh", GODOT],
     [Edit("templates/starter/addons/kanama_tools/plugin.gd", '["all", "~/.jdks", "", ""]', '["all", "~/.jdkz", "", ""]')],
     "FAIL table_home_dot_jdks", "the editor plugin's JDK location table drifts from bootstrap.c's (a `~/.jdks` row renamed)",
     pre=[WARM_EXAMPLE], timeout=1800,  # cold worktree: Gradle builds the native bootstrap first
     requires_env="KANAMA_GODOT_BIN", slow=True)
case("check_bootstrap_jdk_resolution.sh", ["bash", "scripts/check_bootstrap_jdk_resolution.sh"],
     [Edit("bootstrap/CMakeLists.txt", "predates JDK 21 (no JNI_VERSION_21)", "is fine")],
     "FAIL env_stale_header", "the bootstrap's stale-JDK diagnostic changes text (its fail cases then match nothing)", slow=True)
case("check_gradle_templates_configure.sh", ["bash", "scripts/check_gradle_templates_configure.sh"],
     [Edit("templates/release-kit/settings.gradle.kts", "pluginManagement {", "plugins { }\npluginManagement {")],
     "does not configure", "the release-kit settings file puts `plugins {}` before `pluginManagement {}`", slow=True)
case("check_gradle_templates_configure.sh (starter wrapper)", ["bash", "scripts/check_gradle_templates_configure.sh"],
     [Edit("build.gradle.kts", '  from(layout.projectDirectory.file("gradlew")) { filePermissions { unix("rwxr-xr-x") } }\n  from(layout.projectDirectory.file("gradlew.bat"))\n  from(layout.projectDirectory.dir("gradle")) { into("gradle") }\n}', "}")],
     "source-checkout starter has no gradlew", "createStarterProject stops copying the Gradle wrapper (the starter then has no `./gradlew buildScripts`)", slow=True)
case("check_gradle_templates_configure.sh (documented task)", ["bash", "scripts/check_gradle_templates_configure.sh"],
     [Edit("README.md", "cd /path/to/kanama-starter\n./gradlew buildScripts", "cd /path/to/kanama-starter\n./gradlew buildKotlinScripts")],
     "has no task buildKotlinScripts", "the README Quick Start tells the user to run a starter task that does not exist", slow=True)
case("check_gradle_templates_configure.sh (task path)", ["bash", "scripts/check_gradle_templates_configure.sh"],
     [Edit("README.md", "cd /path/to/kanama-starter\n./gradlew buildScripts", "cd /path/to/kanama-starter\n./gradlew :buildKotlinScripts")],
     "has no task :buildKotlinScripts", "a documented task given by its path (`:task`) is skipped by the doc-command scan", slow=True)
case("check_gradle_templates_configure.sh (kit README, titled fence)", ["bash", "scripts/check_gradle_templates_configure.sh"],
     [Edit("templates/release-kit/README.md", "```sh\n./gradlew buildScripts\n```", "```bash title=\"Build\"\n./gradlew buildKotlinScripts\n```")],
     "has no task buildKotlinScripts", "the README a kit ships with tells the user to run a task that does not exist, in a ```bash title=... block", slow=True)


case("web/scaffold_selftest.sh", ["bash", "scripts/web/scaffold_selftest.sh"],
     [Edit("scripts/web_export_smoke.sh", 'if ! python3 "$WEB_DIR/check_budgets.py" "$RESULT"; then', 'if false; then')],
     "over-budget expected exit 1", "web_export_smoke.sh stops running the budget gate (the self-test's own `over-budget` case must catch that)")
case("check_budgets.py (builtin calls)", ["bash", "scripts/web/scaffold_selftest.sh"],
     [Edit("scripts/web/check_budgets.py", "\n    violations += check_builtin_calls(demo, measured, budgets, limits)\n    return violations", "\n    return violations")],
     "over-builtin-budget expected exit 1", "the budget gate stops counting synchronous builtin / utility calls, per tick or per simulated second (task 129 E review B1)")
case("check_budgets.py (builtin calls per simulated second)", ["bash", "scripts/web/scaffold_selftest.sh"],
     [Edit("scripts/web/check_budgets.py", "    rate = calls / sim\n", "    rate = 1000.0 * calls / ticks\n")],
     "slow-host-simtime expected exit 0, got 1", "a per-simulated-second ceiling is taken per engine tick again (load-sensitive): a slow host's envelope (3.0 calls per tick, 9.0 per simulated second) goes red (task 138 item 33)")
case("check_budgets.py (builtin calls per tick cap)", ["bash", "scripts/web/scaffold_selftest.sh"],
     [Edit("scripts/web/check_budgets.py", "        if per_tick > ceiling:\n", "        if False:\n")],
     "slow-host-per-tick expected exit 1, got 0", "the per-tick builtin-call cap stops gating a demo with no per-simulated-second ceiling (it caught the fps regression at 2.1x; review of task 138 item 33)")
case("check_budgets.py (exempt verdict)", ["bash", "scripts/web/scaffold_selftest.sh"],
     [Edit("scripts/web/check_budgets.py", '    if builtin_gate(demo, limits, budgets)[0] == "exempt":\n        exempt.append("builtin calls")\n    else:', '    if False:\n        exempt.append("builtin calls")\n    else:')],
     "an exempt demo's verdict line does not say exempt", "an exempt demo's final line reads \"within budget\" for a metric it was never graded on (review N6)")
case("check_budgets.py --self-test (per-tick cap)", py("web/check_budgets.py", "--self-test"),
     [Edit("scripts/web/budgets.json", '"defaultMaxBuiltinCallsPerTick": 0.5,', '"defaultMaxBuiltinCallsPerTick": 0.9,')],
     "must exceed the per-tick cap 0.9 by 2x or more", "the per-tick cap is loosened until the pre-fix fps envelope (1.06 per tick) is no longer red by 2x")
case("check_budgets.py --self-test (record-only engine)", py("web/check_budgets.py", "--self-test"),
     [Edit("scripts/web/check_budgets.py", "        violations += check_builtin_calls(demo, measured, budgets, limits)\n        return violations\n", "        return violations\n")],
     "fps pre-fix builtin calls on record-only safari: expected red", "a record-only engine (Safari) stops grading builtin calls (review N6)")
case("check_budgets.py --self-test (too few ticks, per tick)", py("web/check_budgets.py", "--self-test"),
     [Edit("scripts/web/check_budgets.py", '            return [too_few_ticks_message("builtin calls per tick", ticks, limits)]', "            return []")],
     "on record-only safari: expected red", "the per-tick builtin-call gate passes a run with too few ticks ungraded again (Safari skips the crossings check that used to report it)")
case("check_budgets.py --self-test (too few ticks, per simulated second)", py("web/check_budgets.py", "--self-test"),
     [Edit("scripts/web/check_budgets.py", '        return [too_few_ticks_message("builtin calls per simulated second", ticks, limits)]', "        return []")],
     "bunnymark, 10 ticks (graded per simulated second) on record-only safari: expected red", "the per-simulated-second builtin-call gate passes a run with too few ticks ungraded again")
case("check_budgets.py --self-test (both ceilings at demo level)", py("web/check_budgets.py", "--self-test"),
     [Edit("scripts/web/check_budgets.py", "    if all(key in limits for key in BUILTIN_KEYS):\n", "    if False:\n")],
     "must be a budget error for engine", "an engine naming one builtin-call ceiling silently drops the other of a demo that declared both")
case("check_budgets.py --self-test (default cap margin)", py("web/check_budgets.py", "--self-test"),
     [Edit("scripts/web/budgets.json", '"defaultMaxBuiltinCallsPerTick": 0.5,', '"defaultMaxBuiltinCallsPerTick": 0.45,')],
     "under 1.9x the highest measured run", "the per-tick default sinks below 1.9x the CI runner's fps measurement (0.26 per tick)")
case("web/bridge_simtime_selftest.mjs", ["node", "scripts/web/bridge_simtime_selftest.mjs"],
     [Edit("web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js", "      if (this.simProcessRafTick !== this.rafTick) {\n", "      if (true) {\n")],
     "scripted node(s): simSeconds", "the bridge adds the _process delta once per scripted handle again: simSeconds scales with the number of scripted nodes (dodge's mobs made it 4-7x game time)")
case("web/bridge_simtime_selftest.mjs (physics)", ["node", "scripts/web/bridge_simtime_selftest.mjs"],
     [Edit("web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js", "      if (handle === this.simPhysicsClockHandle) this.simPhysicsSeconds += delta;\n", "      this.simPhysicsSeconds += delta;\n")],
     "_physics_process", "the bridge adds the physics delta once per scripted handle again")
case("web/bridge_rc_ownership_selftest.mjs (fallback hold)", ["node", "scripts/web/bridge_rc_ownership_selftest.mjs"],
     [Edit("web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js", "      if (this.wrapperRegistryWorks === false) return 0;\n      const slot = this.browserHandleSlot(handle);\n      if (!slot?.refCounted) return 0;\n      slot.wrappers = Math.max(0, slot.wrappers + delta);\n", "      const slot = this.browserHandleSlot(handle);\n      if (!slot?.refCounted) return 0;\n      slot.wrappers = Math.max(0, slot.wrappers + delta);\n")],
     "fallback: a texture snapshot's hold", "without a working FinalizationRegistry a Sprite2D texture snapshot's hold counts again, and its end releases a texture a Kotlin wrapper still names")
case("web/bridge_rc_ownership_selftest.mjs (fallback drop)", ["node", "scripts/web/bridge_rc_ownership_selftest.mjs"],
     [Edit("web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js", "      if (this.wrapperRegistryWorks === false) return 0;\n      const slot = this.browserHandleSlot(handle);\n      if (!slot?.refCounted) return 0;\n      if (this.wrapperRegistry?.unregister(ref)", "      const slot = this.browserHandleSlot(handle);\n      if (!slot?.refCounted) return 0;\n      if (this.wrapperRegistry?.unregister(ref)")],
     "late fallback: dropWrapper released", "once the registry refused a wrapper, ending a counted temporary's hold releases the handle an uncounted wrapper still names")
case("web/bridge_rc_ownership_selftest.mjs (lent cap order)", ["node", "scripts/web/bridge_rc_ownership_selftest.mjs"],
     [Edit("web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js", "      if (this.lentRefCountedByAge.delete(handle)) this.lentRefCountedByAge.add(handle);\n", "")],
     "cap: evicted the first minted lend", "the lent cap evicts the first minted lend again, not the least recently wrapped one")
case("web/bridge_rc_ownership_selftest.mjs (Variant result cap)", ["node", "scripts/web/bridge_rc_ownership_selftest.mjs"],
     [Edit("web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js", "        slot.lendFrame = this.ownershipFrame;\n        this.lentRefCountedByAge.add(handle);\n", "")],
     "variant cap: a Variant result's RefCounted is not under the lent cap", "a Variant result's RefCounted (a per-frame GD.weakref) leaves the lent cap again: without a collection the handle table grows without bound")
case("web/dodge_restart_selftest.mjs", ["node", "scripts/web/dodge_restart_selftest.mjs"],
     [Edit("scripts/web/drivers/demos/dodge.mjs", "      await callMain(evaluate, mainIds._on_StartTimer_timeout);\n", "")],
     "early hit in round 1 (1st mob): checks red: mobsInstantiated", "dodge stops resuming a round the player was hit in before its 4th mob: the run hangs on the RNG again (an early hit fails mobsInstantiated)")
case("web/dodge_restart_selftest.mjs (per-round mob count)", ["node", "scripts/web/dodge_restart_selftest.mjs"],
     [Edit("scripts/web/drivers/demos/dodge.mjs", "mobsInstantiated: rounds.length >= 2 && rounds.every((finished) => finished.mobs >= 4),", "mobsInstantiated: peak.mobInstantiations >= 4,")],
     "round 2 hit early, spawning never resumes: mobsInstantiated must be false", "dodge counts mobs across rounds again: round 1's 4 mobs plus a round 2 that stops spawning after 2 reach 6 and pass")
case("web/dodge_restart_selftest.mjs (per-round retention)", ["node", "scripts/web/dodge_restart_selftest.mjs"],
     [Edit("scripts/web/drivers/demos/dodge.mjs", "for (const finished of rounds) {", "for (const finished of rounds.slice(-1)) {")],
     "leaked per mob of round 1 (the restarted round): noPerMobHandleRetention must be false", "dodge judges only the last round's retention again: a per-mob leak in a round that ended in a restart passes")
case("web/dodge_restart_selftest.mjs (restart every run)", ["node", "scripts/web/dodge_restart_selftest.mjs"],
     [Edit("scripts/web/drivers/demos/dodge.mjs", "if (roundRestarts === 0 && p.roundMobs >= FORCED_RESTART_AT_MOBS) {", "if (false) {")],
     "one handle leaked per mob of round 2 only: noPerMobHandleRetention must be false", "dodge stops ending round 1 with a restart in every run: a leak that only shows after the first new_game passes CI (it was behind KANAMA_WEB_DODGE_FORCE_RESTART)")
case("web/dodge_restart_selftest.mjs (forced collection)", ["node", "scripts/web/dodge_restart_selftest.mjs"],
     [Edit("scripts/web/drivers/demos/dodge.mjs", "const takeCollected = () => snapshot(collecting(evaluate));", "const takeCollected = take;")],
     "healthy: checks red: noPerMobHandleRetention", "dodge's between-rounds trough stops forcing a collection: a mob's dropped SpriteFrames wrapper reads as a retained handle until the browser collects on its own (CI: 4 over 7 mobs)")
case("web/dodge_restart_selftest.mjs (instant kill)", ["node", "scripts/web/dodge_restart_selftest.mjs"],
     [Edit("scripts/web/drivers/demos/dodge.mjs", "noHitBeforeFirstMob: !hits.some((hit) => hit.mobs === 0),", "noHitBeforeFirstMob: true,")],
     "hit before round 1's first mob: noHitBeforeFirstMob must be false", "dodge stops failing a build where the player is hit before a round spawned a mob (resuming the round would hide it)")
case("web/dodge_restart_selftest.mjs (instant kill, round 2)", ["node", "scripts/web/dodge_restart_selftest.mjs"],
     [Edit("scripts/web/drivers/demos/dodge.mjs", "noHitBeforeFirstMob: !hits.some((hit) => hit.mobs === 0),", "noHitBeforeFirstMob: !hits.some((hit) => hit.round === 1 && hit.mobs === 0),")],
     "hit before round 2's first mob: noHitBeforeFirstMob must be false", "dodge judges a hit before the first mob in round 1 only again: an instant kill after the restart passes")
case("web/dodge_restart_selftest.mjs (second hit)", ["node", "scripts/web/dodge_restart_selftest.mjs"],
     [Edit("scripts/web/drivers/demos/dodge.mjs", "atMostOneHitPerRound: Object.values(hitsPerRound).every((count) => count <= 1),", "atMostOneHitPerRound: true,")],
     "the hit does not disable the player: atMostOneHitPerRound must be false", "dodge passes a round the player was hit in twice: a Player whose hit no longer hides and disables it is resumed after every hit and stays green")
case("web/dodge_restart_selftest.mjs (early hit)", ["node", "scripts/web/dodge_restart_selftest.mjs"],
     [Edit("scripts/web/drivers/demos/dodge.mjs", "noEarlyHit: !hits.some((hit) => hit.mobs > 0 && hit.sinceFirstSpawn !== null && hit.sinceFirstSpawn < EARLIEST_HIT_SECONDS),", "noEarlyHit: true,")],
     "a mob hits the player as it spawns: noEarlyHit must be false", "dodge stops failing a hit sooner than a mob can fly from the screen edge to the stationary player (an instant kill on the first mob passes as bad luck)")
case("web/dodge_restart_selftest.mjs (hit across the press)", ["node", "scripts/web/dodge_restart_selftest.mjs"],
     [Edit("scripts/web/drivers/demos/dodge.mjs", "const target = previousRound && event.sim === pressSim ? previousRound : round;", "const target = round;")],
     "round 1's hit reported in the first frame after the restart: checks red: noHitBeforeFirstMob", "dodge bills a hit Godot reports in the first frame after Start (an overlap from the old round's last physics step) to the new round: a healthy run goes red as an instant kill")
case("web/dodge_restart_selftest.mjs (no screen exit)", ["node", "scripts/web/dodge_restart_selftest.mjs"],
     [Edit("scripts/web/drivers/demos/dodge.mjs", "finished.screenExitFrees + finished.roundEndFrees === finished.mobs),", "finished.screenExitFrees === finished.mobs),")],
     "no mob is ever reported off screen (Linux CI Firefox): red checks are [mobsSpawnAndFree, mobsFreedOffScreen], expected exactly [mobsFreedOffScreen]", "dodge's mobsSpawnAndFree counts only the mobs that freed themselves off screen again: a host that never fires screen_exited (the Linux CI runner's Firefox, task 71) fails it too, although new_game frees every mob")
case("web/dodge_restart_selftest.mjs (off-screen free required)", ["node", "scripts/web/dodge_restart_selftest.mjs"],
     [Edit("scripts/web/drivers/demos/dodge.mjs", "mobsFreedOffScreen: mobScreenExitFrees >= 1 && mobScreenExitSignals >= 1,", "mobsFreedOffScreen: true,")],
     "no mob is ever reported off screen (Linux CI Firefox): mobsFreedOffScreen must be false", "dodge stops requiring one mob to free itself off screen: a host or build where screen_exited never fires or never frees the mob (task 71) passes")
case("web_export_smoke.sh (browser-floor)", ["bash", "scripts/web/scaffold_selftest.sh"],
     [Edit("scripts/web_export_smoke.sh", 'if ! python3 "$WEB_DIR/check_browser_floor.py" "$RESULT"; then', 'if false; then')],
     "below-floor expected exit 1", "web_export_smoke.sh stops running the browser-floor gate (the `below-floor` case must catch that)")

#CASES-END

def main() -> int:
    global WORK
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--only", help="run only the cases whose gate name contains this text")
    parser.add_argument("--slow", action="store_true", help="also run the cases that drive Godot and Gradle (minutes each)")
    parser.add_argument("--strict", action="store_true", help="a SKIPPED case is a failure (implied when CI is true)")
    parser.add_argument("--allow-skip", default="", help="comma-separated case-name substrings a --strict run may skip")
    parser.add_argument("--list", action="store_true")
    parser.add_argument("--markdown", type=Path, help="write the table rows to this file")
    parser.add_argument("--shard", help="K/N: run every N-th selected case starting at the K-th (CI runs N shards in parallel)")
    args = parser.parse_args()
    strict = args.strict or os.environ.get("CI", "").strip().lower() in ("true", "1", "yes")
    allowed_skips = [s.strip() for s in args.allow_skip.split(",") if s.strip()]

    cases = [c for c in CASES if (not args.only or args.only in c.gate) and (args.slow or not c.slow or args.only)]
    if args.shard:
        try:
            k, n = (int(x) for x in args.shard.split("/"))
        except ValueError:  # justified: a malformed --shard falls through to the range check below, which exits 2
            k = n = 0
        if not 1 <= k <= n:
            print(f"gate_red_runs: --shard wants K/N with 1 <= K <= N, got {args.shard!r}", file=sys.stderr)
            return 2
        cases = cases[k - 1 :: n]
    if args.list:
        for c in cases:
            print(f"{c.gate}: {c.how}")
        return 0
    if not cases:
        print("gate_red_runs: no case matches", file=sys.stderr)
        return 2

    def on_signal(signum, _frame):  # the finally below then removes the workspace
        raise SystemExit(128 + signum)

    for signum in (signal.SIGTERM, signal.SIGHUP, signal.SIGINT):
        signal.signal(signum, on_signal)

    before = git_status()
    tmp, WORK = make_workspace()
    try:
        failures, rows, skipped = run_cases(cases, strict, allowed_skips)
    finally:
        drop_workspace(tmp, WORK)
        WORK = SRC
    after = git_status()
    if before != after:
        failures.append("the dev checkout changed across the run (this must never happen):\n" + "\n".join(sorted(set(after.splitlines()) ^ set(before.splitlines()))))

    if args.markdown:
        header = "| gate | how to make it red | red output | green after revert |\n|---|---|---|---|\n"
        args.markdown.write_text(header + "\n".join(rows) + "\n", encoding="utf-8")

    if failures:
        print("\ngate_red_runs: FAIL", file=sys.stderr)
        for failure in failures:
            print(f"- {failure}", file=sys.stderr)
        return 1
    done = len(cases) - len(skipped)
    print(f"\ngate_red_runs: PASS {done} gate(s) went red on a known-bad input and green again after the revert"
          + (f"; {len(skipped)} SKIPPED ({', '.join(skipped)})" if skipped else ""))
    return 0


def run_cases(cases: list[Case], strict: bool, allowed_skips: list[str]) -> tuple[list[str], list[str], list[str]]:
    failures: list[str] = []
    rows: list[str] = []
    skipped: list[str] = []
    for c in cases:
        print(f"== {c.gate}: {c.how}", flush=True)
        if c.requires_env and not Path(os.environ.get(c.requires_env, "/nonexistent")).exists():
            print(f"SKIP: {c.gate}: needs {c.requires_env} to name an existing path", flush=True)
            skipped.append(c.gate)
            rows.append(f"| `{c.gate}` | {c.how} | SKIPPED: needs {c.requires_env} | - |")
            if strict and not any(a in c.gate for a in allowed_skips):
                failures.append(f"{c.gate}: SKIPPED (needs {c.requires_env}) in a strict run; set it, or pass --allow-skip")
            continue
        for pre in c.pre:
            pre_rc, pre_out, _ = run(Case(c.gate, pre, [], "", "", env=c.env, timeout=c.timeout))
            if pre_rc != 0:
                failures.append(f"{c.gate}: prerequisite {pre} failed (exit {pre_rc}):\n{pre_out[-600:]}")
        rc, out, _secs = run(c)
        if rc != 0:
            failures.append(f"{c.gate}: not green before the mutation (exit {rc}):\n{out[-800:]}")
            print(f"   baseline NOT green (exit {rc}); skipped", flush=True)
            continue
        with Mutator(c.mutations):
            red_rc, red_out, red_secs = run(c)
        green_rc, green_out, _ = run(c)
        ok = True
        if red_rc == 0:
            failures.append(f"{c.gate}: STAYED GREEN after the mutation ({c.how}); the gate does not catch it")
            ok = False
        elif c.expect not in red_out:
            failures.append(
                f"{c.gate}: failed (exit {red_rc}) but without the expected text {c.expect!r}:\n{red_out[-800:]}"
            )
            ok = False
        if green_rc != 0:
            failures.append(f"{c.gate}: NOT green after the revert (exit {green_rc}):\n{green_out[-800:]}")
            ok = False
        excerpt = red_excerpt(red_out, c.expect).replace("|", "\\|")
        print(f"   red   exit {red_rc} in {red_secs:.1f}s: {excerpt}", flush=True)
        print(f"   green after revert: exit {green_rc}", flush=True)
        rows.append(
            f"| `{c.gate}` | {c.how} | `{excerpt}` | {'green' if green_rc == 0 else 'NOT GREEN'} |"
            if ok
            else f"| `{c.gate}` | {c.how} | UNPROVEN | - |"
        )
    return failures, rows, skipped


if __name__ == "__main__":
    raise SystemExit(main())
