#!/usr/bin/env python3
"""Red runs for the repo's gates (task 118): every gate is shown to FAIL on a known-bad input.

A gate that has only ever been seen green is a claim, not evidence: a gate that swallows its own
failure (a skipped branch, a regex that stopped matching, an `|| true`) is green on every input. This
harness holds each listed gate to the three-step proof, with a reversible mutation of the tree:

    1. green  the gate passes on the tree as it is,
    2. red    after the mutation the gate exits non-zero AND prints the expected text,
    3. green  after the mutation is reverted the gate passes again.

The mutation is applied in place (an edit, a created file or a deleted file) and ALWAYS reverted, also
on an exception or Ctrl-C; at the end the harness compares `git status --porcelain` before and after
and fails if they differ. Run it on a checkout nothing else is writing to.

Usage:
    python3 scripts/gate_red_runs.py                  # every case
    python3 scripts/gate_red_runs.py --only parity    # cases whose gate name contains the text
    python3 scripts/gate_red_runs.py --slow           # also the Godot/Gradle smokes (needs KANAMA_GODOT_BIN)
    python3 scripts/gate_red_runs.py --list
    python3 scripts/gate_red_runs.py --markdown out.md   # also write the README-gates.md table rows

Gates that need a device, a browser, a Godot binary or a long export are NOT here: they carry their own
`--self-test` or red-run recipe, documented in scripts/README-gates.md.
"""

from __future__ import annotations

import argparse
import os
import subprocess
import sys
import time
from dataclasses import dataclass, field
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
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
class Append:
    path: str
    text: str


@dataclass
class Case:
    gate: str  # name shown in the table
    cmd: list[str]  # run from ROOT
    mutations: list  # Edit | Create | Delete
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
    """Applies a list of mutations and reverts them, byte for byte."""

    def __init__(self, mutations: list):
        self.mutations = mutations
        self.saved: list[tuple[Path, bytes | None]] = []
        self.modes: dict[Path, int | None] = {}  # a deleted-then-restored script must keep its executable bit

    def __enter__(self):
        try:
            for mutation in self.mutations:
                path = ROOT / mutation.path
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
                    path.parent.mkdir(parents=True, exist_ok=True)
                    path.write_text(mutation.content, encoding="utf-8")
                elif isinstance(mutation, Delete):
                    path.unlink()
        except BaseException:
            self.__exit__(None, None, None)
            raise
        return self

    def __exit__(self, *exc):
        for path, original in reversed(self.saved):
            if original is None:
                if path.exists():
                    path.unlink()
            else:
                path.parent.mkdir(parents=True, exist_ok=True)
                path.write_bytes(original)
                if self.modes.get(path) is not None:
                    path.chmod(self.modes[path])
        self.saved = []
        return False


def run(case_: Case) -> tuple[int, str, float]:
    env = dict(os.environ)
    env.update(case_.env)
    # A gate that skips under CI would read as red here for the wrong reason; the harness is local.
    env.pop("CI", None)
    started = time.monotonic()
    try:
        proc = subprocess.run(
            case_.cmd, cwd=ROOT, env=env, capture_output=True, text=True, timeout=case_.timeout,
            check=False,  # justified: the exit status IS the datum this harness reads (green must be 0, red must not be)
        )
        out = proc.stdout + proc.stderr
        return proc.returncode, out, time.monotonic() - started
    except subprocess.TimeoutExpired as error:  # justified: reported as exit 124 with the partial output; the case then fails
        return 124, f"timeout after {case_.timeout}s\n{error.stdout or ''}", time.monotonic() - started


def red_excerpt(output: str, expect: str) -> str:
    """One to three lines of the red output: the line holding the expected text, else the last lines."""
    output = output.replace(str(ROOT) + "/", "")  # no workstation paths in the recorded excerpt
    lines = [line.strip() for line in output.splitlines() if line.strip()]
    for index, line in enumerate(lines):
        if expect in line:
            return " / ".join(lines[index : index + 2])[:200]
    return " / ".join(lines[-2:])[:200]


def git_status() -> str:
    """The porcelain status plus the mode changes (`git status` does not show a lost executable bit)."""
    status = subprocess.run(
        ["git", "status", "--porcelain"], cwd=ROOT, capture_output=True, text=True, check=True
    ).stdout
    modes = subprocess.run(
        ["git", "diff", "--summary"], cwd=ROOT, capture_output=True, text=True, check=True
    ).stdout
    return status + modes


# ==================================================================================================
# CASES -- one per gate. Keep the mutation minimal and the expected text specific to the gate's own message.
# ==================================================================================================

#CASES-BEGIN
COMMON = "src/commonMain/kotlin/net/multigesture/kanama"
JVM = "src/jvmMain/kotlin"
IOS = "src/iosMain/kotlin/net/multigesture/kanama"
GEN = "docs/reference/generated"
WEBGEN = "web-runtime/src/commonMain/kotlin/net/multigesture/kanama/api/generated"
ENV_PY = {"PYTHONPATH": str(ROOT / "scripts")}
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
case("check_doc_claims.py", py("check_doc_claims.py"),
     [Edit("docs/exporting/web.md", "versioned JavaScript bridge (protocol 29)", "versioned JavaScript bridge (protocol 21)")],
     "stale or malformed claim", "a marked doc line states the wrong Web protocol")
case("check_expect_no_defaults.py", py("check_expect_no_defaults.py"),
     [Create(f"{COMMON}/api/ZzRedRun.expect.kt", "package net.multigesture.kanama.api\n\nexpect fun redRun(a: Int = 1)\n")],
     "default argument(s) on an `expect` declaration", "an `expect fun` declares a default argument")
case("check_gate_evidence.py", py("check_gate_evidence.py"),
     [Edit("evidence/gates.json", '"schemaVersion": 1', '"schemaVersion": 2')],
     "ledger must be an object with schemaVersion 1", "the evidence ledger carries a wrong schema version")
case("check_godot_version_pin.py", py("check_godot_version_pin.py"),
     [Edit("gradle.properties", "kanamaGodotVersion=4.7.2.stable", "kanamaGodotVersion=4.7.1.stable")],
     "Godot version pins drifted", "gradle.properties pins a different Godot than CI")
case("check_ios_no_silent_stubs.py", py("check_ios_no_silent_stubs.py"),
     [Create(f"{COMMON}/api/ZzRedRun.kt", "package net.multigesture.kanama.api\n\nclass ZzRedRun {\n    fun redRun(): Boolean = false\n}\n")],
     "un-annotated silent stub", "a shared wrapper function whose whole body is a bare default, without a marker")
case("check_ios_shim_faults.py", py("check_ios_shim_faults.py"),
     [Edit("ios/bootstrap/kanama_ios_shim.c", '        kanama_ios_fault(__func__, "api-unresolved", NULL);', "        /* red run: guard returns silently */")],
     "returns without calling kanama_ios_fault", "a guarded early return in the iOS shim stops reporting its fault")
case("check_ios_static_dispatch.py", py("check_ios_static_dispatch.py"),
     [Edit(f"{IOS}/binding/runtime/ObjectCalls.kt", "      kanama_ios_godot_ptrcall_static(methodBind, argTypes, argPtrs, argCount, retType, retOut)",
           "      kanama_ios_godot_ptrcall(0L, methodBind, argTypes, argPtrs, argCount, retType, retOut)")],
     "dispatcher-no-static", "a static call is routed through a guarded iOS entry point")
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
case("check_property_coverage.py", py("check_property_coverage.py"),
     [Edit(f"{COMMON}/api/CanvasItem.kt", "    var visible: Boolean", "    var visibleRedRun: Boolean")],
     "silently dropped", "a generated wrapper property disappears")
case("check_protocol_pins.py", py("check_protocol_pins.py"),
     [Edit("web-runtime/src/webSpikeGodot/assets/kanama-web-bridge.js", "KANAMA_WEB_PROTOCOL_VERSION = 29", "KANAMA_WEB_PROTOCOL_VERSION = 28")],
     "protocol version disagrees", "the bridge pins protocol 28 while the emitter says 29")
case("check_pt_tag_tables.py", py("check_pt_tag_tables.py"),
     [Edit("scripts/generate_api_wrapper.py", '    "PT_VOID": 0,', '    "PT_VOID": 99,')],
     "value-mismatch VOID", "one copy of the iOS ptrcall tag table is renumbered")
case("check_public_signature_changes.py", py("check_public_signature_changes.py"),
     [Edit(f"{COMMON}/api/Node.kt", "    fun setProcessMode(mode: Node.ProcessMode) {", "    fun setProcessMode(mode: Node.ProcessMode, extra: Int) {")],
     "unannounced source break", "a public signature changes without a CHANGELOG `Source break` line")
case("check_typed_enums.py", py("check_typed_enums.py"),
     [Edit(f"{COMMON}/api/Node.kt", "    fun setProcessMode(mode: Node.ProcessMode) {", "    fun setProcessMode(mode: Long) {")],
     "typed_enums] FAIL", "an enum parameter goes back to a raw Long")
case("check_unapplied_annotations.py", py("check_unapplied_annotations.py"),
     [Create(f"{JVM}/net/multigesture/kanama/ZzRedRun.kt", "package net.multigesture.kanama\nimport net.multigesture.kanama.annotations.OnReady\nclass ZzRedRun\n")],
     "imports @OnReady but never applies it", "a lifecycle annotation is imported and never applied")
case("check_web_callback_flush.py", py("check_web_callback_flush.py"),
     [Edit("web-runtime/src/wasmJsMain/kotlin/net/multigesture/kanama/web/Main.kt",
           "    KanamaWebProjectRegistry.enterTree(record.scriptId, record.script)\n    commands.flush()",
           "    KanamaWebProjectRegistry.enterTree(record.scriptId, record.script)")],
     "without flushing the command buffer", "a Web callback boundary stops flushing the command buffer")
case("check_web_typed_enums.py", py("check_web_typed_enums.py"),
     [Edit(f"{WEBGEN}/Node.kt", "  fun setProcessMode(mode: Node.ProcessMode) {", "  fun setProcessMode(mode: Long) {")],
     "web_typed_enums] FAIL", "a Web wrapper enum parameter goes back to a raw Long")
case("check_wrapper_generator.py", py("check_wrapper_generator.py"),
     [Edit(f"{COMMON}/api/Node.kt", "    fun setProcessMode(mode: Node.ProcessMode) {", "    fun setProcessMode(mode: Node.ProcessMode ) {")],
     "single-tree drift-gate", "a generated wrapper is hand-edited (one stray space)")

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
     "Dictionary argument is not initialized through BuiltinTypes.initDictionary", "a Dictionary-argument helper stops using the explicit initializer (script is in flight under task 132; red run only)", env=ENV_PY)
case("audit_godot_object_script_paths.py", py("audit_godot_object_script_paths.py"),
     [Edit(f"{COMMON}/api/GodotObject.kt", "ptrcallWithStringNameAndVariantArg(objectSetBind", "ptrcallWithStringNameAndVariantArgX(objectSetBind")],
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
     [Edit(f"{JVM}/net/multigesture/kanama/api/Engine.kt", "fun registerSingleton(name: String, objectArg: GodotHandle)", "fun registerSingletonRedRun(name: String, objectArg: GodotHandle)")],
     "Engine.registerSingleton wrapper not found", "the Engine wrapper loses registerSingleton")
case("audit_stale_blockers.py", py("audit_stale_blockers.py"),
     [Edit("CONTRIBUTING.md", "# Contributing to Kanama", "# Contributing to Kanama\n<!-- KANAMA-BLOCKED(since:2026-01-01, file:CONTRIBUTING.md): red run -->")],
     "stale_blockers] FAIL", "a KANAMA-BLOCKED marker whose blocker no longer holds")
case("audit_value_type_wrappers.py", py("audit_value_type_wrappers.py", "--strict"),
     [Edit(f"{COMMON}/types/Quaternion.kt", "listOf(BArg.Floats(PT_QUATERNION, to.toGodotRealArray()), BArg.Real(weight))",
           "listOf(BArg.Floats(PT_QUATERNION, to.toGodotRealArray()), BArg.Floats(PT_FLOAT, doubleArrayOf(weight)))")],
     "value_type_audit]", "a Godot `float` argument is marshalled as a real_t component array")
case("audit_vararg_ptrcalls.py", py("audit_vararg_ptrcalls.py"),
     [Create(f"{COMMON}/api/ZzRedRun.kt",
             'package net.multigesture.kanama.api\n\nprivate val redRunBind by lazy { ObjectCalls.getMethodBind("Object", "call", 1L) }\nfun redRun(x: RawSegment) { ObjectCalls.ptrcallNoArgs(redRunBind, x) }\n')],
     "is vararg and must use dynamic Object.call", "a vararg Godot method is wrapped through ptrcall")
case("audit_variant_marshalling_policy.py", py("audit_variant_marshalling_policy.py"),
     [Edit(f"{JVM}/binding/runtime/BuiltinTypes.kt", 'else -> error("Unsupported Variant value type:', 'else -> println("Unsupported Variant value type:')],
     "variant_marshalling_policy_audit] FAIL", "the Variant marshaller coerces an unknown value instead of failing")
case("audit_wrapper_abi_policy.py", py("audit_wrapper_abi_policy.py", "--strict"),
     [Edit(f"{COMMON}/api/Node.kt", "return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(getParentBind, segment))", "return Node.wrap(ObjectCalls.ptrcallNoArgsRetBool(getParentBind, segment))")],
     "return helper slot is bool", "a wrapper selects a bool return helper for an object return", env=ENV_PY)
case("audit_wrapper_signatures.py", py("audit_wrapper_signatures.py"),
     [Edit(f"{COMMON}/api/Node.kt", "return Node.wrap(ObjectCalls.ptrcallNoArgsRetObject(getParentBind, segment))", "return Node.wrap(ObjectCalls.ptrcallNoArgsRetBool(getParentBind, segment))")],
     "wrapper_signature_audit] FAIL", "a wrapper's return helper disagrees with extension_api.json")

# ---- the other python gates local_ci runs ------------------------------------------------------------------
case("type_coverage_audit.py", py("type_coverage_audit.py"),
     [Edit(f"{JVM}/binding/runtime/BuiltinTypes.kt", "is Vector2 ->", "is Vector2Zz ->")],
     "lack Variant marshal coverage", "a value type loses its Variant write branch")
for name, args, target, expect, how in [
    ("api_wrapper_coverage.py --check", ("api_wrapper_coverage.py", "--markdown", f"{GEN}/api-coverage.md", "--check"), f"{GEN}/api-coverage.md", "FAIL stale markdown", "the committed coverage page is hand-edited"),
    ("api_wrapper_generator_report.py --check", ("api_wrapper_generator_report.py", "--markdown", f"{GEN}/wrapper-generator-report.md", "--check"), f"{GEN}/wrapper-generator-report.md", "FAIL", "the committed generator report is hand-edited"),
    ("migrate_enum_constants.py --check", ("migrate_enum_constants.py", "--table", f"{GEN}/enum-migration.md", "--check"), f"{GEN}/enum-migration.md", "is stale", "the committed enum migration table is hand-edited"),
    ("generate_gates_index.py --check", ("generate_gates_index.py", "--markdown", f"{GEN}/gates.md", "--check"), f"{GEN}/gates.md", "FAIL stale gates index", "the committed gates index is hand-edited"),
    ("generate_virtual_signature_table.py --check", ("generate_virtual_signature_table.py", "--check"), VIRTUAL_TABLE, "STALE", "the committed virtual-signature table is hand-edited"),
    ("generate_engine_method_table.py --check", ("generate_engine_method_table.py", "--check"), "processor/src/main/resources/net/multigesture/kanama/processor/engine-methods.tsv", "STALE", "the committed engine-method table is hand-edited"),
    ("generate_web_backend.py --check", ("generate_web_backend.py", "--output", WEB_BACKEND, "--check"), WEB_BACKEND, "generated Web backend dispatch drift", "the generated Web backend dispatch is hand-edited (a real token: comments and wrapping are normalised away)"),
    ("generate_web_wrappers.py --check", ("generate_web_wrappers.py", "--check"), f"{WEBGEN}/Node.kt", "FAIL", "a generated Web wrapper is hand-edited"),
]:
    case(name, py(*args), [Append(target, "\nval redRunHandEdit = 1\n" if target.endswith(".kt") else "\nred run: hand edit\n")], expect, how)
case("generate_api_shell_wrappers.py --fail-if-candidates", py("generate_api_shell_wrappers.py", "--from-skip-report", "build/wrapper-generator/skips.txt", "--dry-run", "--fail-if-candidates"),
     [Delete(f"{COMMON}/api/Curve2D.kt"),
      Append("build/wrapper-generator/skips.txt", "\nCurve2D.foo object return wrapper is missing for Curve2D\n")],
     "candidates=1", "a wrapper file is gone while the skip report names it (the shell generator then has a candidate to write)",
     env=ENV_PY, pre=[py("api_wrapper_generator_report.py", "--markdown", f"{GEN}/wrapper-generator-report.md", "--check")])
case("sync_kdoc_from_godot_docs.py --check", py("sync_kdoc_from_godot_docs.py", "--godot-docs", os.environ.get("GODOT_DOCS", "/nonexistent"), "--check"),
     [Edit(f"{COMMON}/api/Node.kt", "    fun getParent(): Node? {", "    /** red run: a KDoc block the Godot class docs do not contain */\n    fun getParent(): Node? {")],
     "changed_files=1", "a wrapper KDoc block differs from the Godot class docs", requires_env="GODOT_DOCS")

# ---- shell gates ---------------------------------------------------------------------------------------------
case("check_shell_lint.sh", ["bash", "scripts/check_shell_lint.sh"],
     [Create("scripts/zz_redrun.sh", '#!/usr/bin/env bash\ncd /tmp\nrm -rf $UNSET_VAR/\n')],
     "zz_redrun.sh", "a new gate script carries a shellcheck warning (unquoted variable, unchecked cd)")


# ---- web gate self-test: break the gate, its own --self-test must go red ------------------------------------
case("check_no_local_paths.py --self-test", py("web/check_no_local_paths.py", "--self-test"),
     [Edit("scripts/web/check_no_local_paths.py", "                for offset in _hits(data, needle):", "                for offset in []:")],
     "--self-test FAIL: expected the text and the binary leak", "the scan stops finding leaks (the gate is broken; its self-test must say so)")

# ---- smokes that drive Godot (slow: --slow, needs KANAMA_GODOT_BIN) ---------------------------------------------
GODOT = os.environ.get("KANAMA_GODOT_BIN", "/nonexistent-godot")
HELLO = "example_project/HelloScript.kt"
case("tool_smoke.sh", ["bash", "scripts/tool_smoke.sh", GODOT],
     [Append("example_project/addons/kanama_tools/plugin.gd", "\n# red run: this copy now differs from the starter template\n")],
     "plugin copies differ", "the example project's editor plugin drifts from the starter template's copy",
     requires_env="KANAMA_GODOT_BIN", slow=True)
case("runtime_smoke.sh", ["bash", "scripts/runtime_smoke.sh", GODOT],
     [Edit(HELLO, "HelloScript(file)._ready health=", "HelloScript(file)._readyX health=")],
     "missing pattern", "the example script no longer logs its scene-delivered properties (script unchanged: in-flight task 132; red run only)",
     requires_env="KANAMA_GODOT_BIN", slow=True)
case("hot_reload_smoke.sh", ["bash", "scripts/hot_reload_smoke.sh", GODOT],
     [Edit(HELLO, "HelloScript(file)._ready health=", "HelloScript(f)._ready health=")],
     "missing marker", "the reloadable script line the smoke rewrites is gone, so no marker can reach the log",
     requires_env="KANAMA_GODOT_BIN", slow=True)
case("hot_reload_in_process_smoke.sh", ["bash", "scripts/hot_reload_in_process_smoke.sh", GODOT],
     [Edit(HELLO, "HelloScript(file)._ready health=", "HelloScript(f)._ready health=")],
     "hot_reload_in_process_smoke] FAIL --", "the reloadable script line the smoke rewrites is gone, so no marker can reach the log",
     requires_env="KANAMA_GODOT_BIN", slow=True)
case("check_exported_scene_properties_selftest.sh", ["bash", "scripts/check_exported_scene_properties_selftest.sh", GODOT],
     [Edit("scripts/check_exported_scene_properties.gd", "lost script property", "lost a thing")],
     "the missing override property was not reported", "the scene check stops saying `lost script property` (its own red run is inside the self-test)",
     requires_env="KANAMA_GODOT_BIN", slow=True)
case("check_jdk_lookup_parity.sh", ["bash", "scripts/check_jdk_lookup_parity.sh", GODOT],
     [Edit("templates/starter/addons/kanama_tools/plugin.gd", '["all", "~/.jdks", "", ""]', '["all", "~/.jdkz", "", ""]')],
     "FAIL table_home_dot_jdks", "the editor plugin's JDK location table drifts from bootstrap.c's (a `~/.jdks` row renamed)",
     requires_env="KANAMA_GODOT_BIN", slow=True)
case("check_bootstrap_jdk_resolution.sh", ["bash", "scripts/check_bootstrap_jdk_resolution.sh"],
     [Edit("bootstrap/CMakeLists.txt", "predates JDK 21 (no JNI_VERSION_21)", "is fine")],
     "FAIL env_stale_header", "the bootstrap's stale-JDK diagnostic changes text (its fail cases then match nothing)", slow=True)
case("check_gradle_templates_configure.sh", ["bash", "scripts/check_gradle_templates_configure.sh"],
     [Edit("templates/release-kit/settings.gradle.kts", "pluginManagement {", "plugins { }\npluginManagement {")],
     "does not configure", "the release-kit settings file puts `plugins {}` before `pluginManagement {}`", slow=True)


case("web/scaffold_selftest.sh", ["bash", "scripts/web/scaffold_selftest.sh"],
     [Edit("scripts/web_export_smoke.sh", 'if ! python3 "$WEB_DIR/check_budgets.py" "$RESULT"; then', 'if false; then')],
     "over-budget expected exit 1", "web_export_smoke.sh stops running the budget gate (the self-test's own `over-budget` case must catch that)")
case("web_export_smoke.sh (browser-floor)", ["bash", "scripts/web/scaffold_selftest.sh"],
     [Edit("scripts/web_export_smoke.sh", 'if ! python3 "$WEB_DIR/check_browser_floor.py" "$RESULT"; then', 'if false; then')],
     "below-floor expected exit 1", "web_export_smoke.sh stops running the browser-floor gate (the `below-floor` case must catch that)")

#CASES-END


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("--only", help="run only the cases whose gate name contains this text")
    parser.add_argument("--slow", action="store_true", help="also run the cases that drive Godot and Gradle (minutes each)")
    parser.add_argument("--list", action="store_true")
    parser.add_argument("--markdown", type=Path, help="write the table rows to this file")
    args = parser.parse_args()

    cases = [c for c in CASES if (not args.only or args.only in c.gate) and (args.slow or not c.slow or args.only)]
    if args.list:
        for c in cases:
            print(f"{c.gate}: {c.how}")
        return 0
    if not cases:
        print("gate_red_runs: no case matches", file=sys.stderr)
        return 2

    before = git_status()
    failures: list[str] = []
    rows: list[str] = []
    skipped: list[str] = []
    for c in cases:
        print(f"== {c.gate}: {c.how}", flush=True)
        if c.requires_env and not Path(os.environ.get(c.requires_env, "/nonexistent")).exists():
            print(f"SKIP: {c.gate}: needs {c.requires_env} to name a directory", flush=True)
            skipped.append(c.gate)
            rows.append(f"| `{c.gate}` | {c.how} | SKIPPED: needs {c.requires_env} | - |")
            continue
        for pre in c.pre:
            pre_rc, pre_out, _ = run(Case(c.gate, pre, [], "", "", env=c.env, timeout=c.timeout))
            if pre_rc != 0:
                failures.append(f"{c.gate}: prerequisite {pre} failed (exit {pre_rc}):\n{pre_out[-600:]}")
        rc, out, secs = run(c)
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

    after = git_status()
    if before != after:
        changed = sorted(set(after.splitlines()) ^ set(before.splitlines()))
        failures.append("the working tree changed across the run (a mutation was not reverted):\n" + "\n".join(changed))

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


if __name__ == "__main__":
    raise SystemExit(main())
