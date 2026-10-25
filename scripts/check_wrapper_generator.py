#!/usr/bin/env python3
"""Check the wrapper generator: representative fixtures, the locked iOS policies, and the
single-tree drift gate (committed generated wrappers == a fresh regen, on every platform)."""

from __future__ import annotations

import re
import subprocess
import sys
import tempfile
from pathlib import Path


from generate_api_wrapper import (
    DESKTOP_COMPANION_SUFFIX,
    DESKTOP_HANDSHAPED,
    DESKTOP_OBJECTCALLS,
    DESKTOP_ONLY_GENERATED,
    GAP_INDEX_PATH,
    IOS_COMPANION_SUFFIX,
    IOS_HANDSHAPED,
    IOS_HANDWRITTEN_COLLISION_CLASSES,
    IOS_OBJECTCALLS,
    IOS_ONLY_GENERATED,
    IOS_UNSUPPORTED_CLASSES,
    OBJECTCALLS_EXPECT,
    PER_PLATFORM_WRAPPERS,
    SHARED_HAND_ROOT_FILES,
    TYPES_DIR,
    TreeResult,
    desktop_actual_state,
    formatted_code_key,
    generated_companion_paths,
    ios_generated_region_key,
    regenerate_tree,
    strip_comments,
    whitespace_insensitive,
)
from wrapper_model import DESKTOP_API_DIR, IOS_API_DIR, ROOT, SHARED_API_DIR

FIXTURE_DIR = ROOT / "scripts/fixtures/wrapper_generator"
# Kept for importers (api_wrapper_coverage.py, check_property_coverage.py, upgrade_godot.sh):
# the desktop per-platform directory. The shared tree is SHARED_API_DIR.
API_DIR = DESKTOP_API_DIR

# Task 103 step 1 kept the iOS copies of the shared classes until :ios-runtime compiled the shared
# tree (step 2); a copy under IOS_API_DIR is a gate failure now. Left as a switch for the next
# platform that joins the tree in two steps.
IOS_COPIES_PENDING_DELETION = False

# The per-platform wrapper table lives in generate_api_wrapper.PER_PLATFORM_WRAPPERS (one table,
# platform-tagged). DESKTOP_HANDSHAPED / IOS_HANDSHAPED are derived views of it, re-exported here.
ADOPTED_CLASSES = ("Time", "ProjectSettings", "VirtualJoystick")
ADOPTED_CLASSES_WITH_HELPERS_AND_VIRTUAL_SKIPS = ("AnimationMixer",)
ADOPTED_RESOURCE_DOWNCAST_CLASSES = ("FastNoiseLite", "PlaneMesh", "SphereMesh")
ADOPTED_SHELL_ONLY_CLASSES = (
    "AnimationNodeAdd2",
    "AnimationNodeAdd3",
    "AnimationNodeBlend2",
    "AnimationNodeBlend3",
    "AnimationNodeOutput",
    "AnimationNodeSub2",
    "AnimationNodeTimeScale",
    "AudioEffectBandLimitFilter",
    "AudioEffectBandPassFilter",
    "AudioEffectEQ10",
    "AudioEffectEQ21",
    "AudioEffectEQ6",
    "AudioEffectHighPassFilter",
    "AudioEffectHighShelfFilter",
    "AudioEffectLowPassFilter",
    "AudioEffectLowShelfFilter",
    "AudioEffectNotchFilter",
    "AudioStreamMicrophone",
    "AudioStreamPlaybackOggVorbis",
    "AudioStreamPlaybackPlaylist",
    "AudioStreamPlaybackSynchronized",
    "CCDIK3D",
    "CSGCombiner3D",
    "CompressedCubemap",
    "CompressedCubemapArray",
    "CompressedTexture2DArray",
    "FABRIK3D",
    "FBXDocument",
    "GDScriptSyntaxHighlighter",
    "GLTFDocument",
    "GLTFDocumentExtensionConvertImporterMesh",
    "ImageFormatLoader",
    "JacobianIK3D",
    "LightmapProbe",
    "Lightmapper",
    "LightmapperRD",
    "ORMMaterial3D",
    "OfflineMultiplayerPeer",
    "OggPacketSequencePlayback",
    "OpenXRExtensionWrapperExtension",
    "OpenXRInteractionProfileEditor",
    "OpenXRVisibilityMask",
    "PlaceholderCubemap",
    "PlaceholderCubemapArray",
    "PlaceholderMaterial",
    "PlaceholderTexture2DArray",
    "PointMesh",
    "QuadMesh",
    "RenderDataRD",
    "RenderSceneDataRD",
    "ResourceImporterBMFont",
    "ResourceImporterBitMap",
    "ResourceImporterCSVTranslation",
    "ResourceImporterDynamicFont",
    "ResourceImporterImage",
    "ResourceImporterImageFont",
    "ResourceImporterLayeredTexture",
    "ResourceImporterMP3",
    "ResourceImporterOBJ",
    "ResourceImporterSVG",
    "ResourceImporterScene",
    "ResourceImporterShaderFile",
    "ResourceImporterTexture",
    "ResourceImporterTextureAtlas",
    "ResourceImporterWAV",
    "ShaderGlobalsOverride",
    "SkeletonProfileHumanoid",
    "SpotLight3D",
    "SpringBoneCollisionPlane3D",
    "StyleBoxEmpty",
    "Texture2DArrayRD",
    "TextServerAdvanced",
    "TextServerDummy",
    "TextureCubemapArrayRD",
    "TextureCubemapRD",
    "VideoStreamTheora",
    "VisualShaderNodeCubemapParameter",
    "VisualShaderNodeDeterminant",
    "VisualShaderNodeDistanceFade",
    "VisualShaderNodeDotProduct",
    "VisualShaderNodeFaceForward",
    "VisualShaderNodeFresnel",
    "VisualShaderNodeGlobalExpression",
    "VisualShaderNodeIf",
    "VisualShaderNodeLinearSceneDepth",
    "VisualShaderNodeOuterProduct",
    "VisualShaderNodeOutput",
    "VisualShaderNodeParticleBoxEmitter",
    "VisualShaderNodeParticleConeVelocity",
    "VisualShaderNodeParticleOutput",
    "VisualShaderNodeParticleRingEmitter",
    "VisualShaderNodeParticleSphereEmitter",
    "VisualShaderNodeProximityFade",
    "VisualShaderNodeRandomRange",
    "VisualShaderNodeRotationByAxis",
    "VisualShaderNodeSDFRaymarch",
    "VisualShaderNodeSDFToScreenUV",
    "VisualShaderNodeScreenNormalWorldSpace",
    "VisualShaderNodeScreenUVToSDF",
    "VisualShaderNodeTexture2DArrayParameter",
    "VisualShaderNodeTexture2DParameter",
    "VisualShaderNodeTexture3DParameter",
    "VisualShaderNodeTextureParameterTriplanar",
    "VisualShaderNodeTextureSDF",
    "VisualShaderNodeTextureSDFNormal",
    "VisualShaderNodeTransformCompose",
    "VisualShaderNodeTransformDecompose",
    "VisualShaderNodeUVPolarCoord",
    "VisualShaderNodeVaryingGetter",
    "VisualShaderNodeVaryingSetter",
    "VisualShaderNodeVectorCompose",
    "VisualShaderNodeVectorDecompose",
    "VisualShaderNodeVectorDistance",
    "VisualShaderNodeVectorLen",
    "VisualShaderNodeVectorRefract",
    "VisualShaderNodeWorldPositionFromDepth",
    "XRCamera3D",
    "EditorExportPlatformAndroid",
    "EditorExportPlatformAppleEmbedded",
    "EditorExportPlatformIOS",
    "EditorExportPlatformLinuxBSD",
    "EditorExportPlatformMacOS",
    "EditorExportPlatformPC",
    "EditorExportPlatformVisionOS",
    "EditorExportPlatformWeb",
    "EditorExportPlatformWindows",
    "EditorSceneFormatImporterBlend",
    "EditorSceneFormatImporterFBX2GLTF",
    "EditorSceneFormatImporterGLTF",
    "EditorSceneFormatImporterUFBX",
)
ADOPTED_CLASSES_WITH_VIRTUAL_SKIPS = (
    "AnimationNode",
    "AnimationNodeExtension",
    "AStar2D",
    "AStar3D",
    "AStarGrid2D",
    "AudioStream",
    "AudioStreamPlayback",
    "EditorDebuggerPlugin",
    "EditorExportPlatformExtension",
    "EditorExportPlugin",
    "EditorFileSystemImportFormatSupportQuery",
    "EditorImportPlugin",
    "EditorInspectorPlugin",
    "EditorNode3DGizmo",
    "EditorNode3DGizmoPlugin",
    "EditorResourcePreviewGenerator",
    "EditorResourceTooltipPlugin",
    "EditorSceneFormatImporter",
    "EditorScenePostImport",
    "EditorScenePostImportPlugin",
    "EditorVCSInterface",
    "ImageFormatLoaderExtension",
    "MovieWriter",
    "MultiplayerAPIExtension",
    "MultiplayerPeerExtension",
    "OpenXRSpatialCapabilityConfigurationBaseHeader",
    "OpenXRSpatialComponentData",
    "OpenXRStructureBase",
    "OpenXRExtensionWrapper",
    "PacketPeerExtension",
    "PhysicsDirectBodyState2DExtension",
    "PhysicsDirectBodyState3DExtension",
    "PhysicsDirectSpaceState2DExtension",
    "PhysicsDirectSpaceState3DExtension",
    "PhysicsServer2DExtension",
    "PhysicsServer3DExtension",
    "PhysicsServer3DRenderingServerHandler",
    "PhysicalBone3D",
    "RenderDataExtension",
    "RenderSceneBuffersExtension",
    "RenderSceneDataExtension",
    "ScriptExtension",
    "ScriptLanguageExtension",
    "SkeletonModification2D",
    "StyleBox",
    "StreamPeerExtension",
    "SyntaxHighlighter",
    "TextServerExtension",
    "TileMap",
    "TileMapLayer",
    "VideoStreamPlayback",
    "VisualShaderNodeCustom",
    "WebRTCDataChannelExtension",
    "WebRTCPeerConnectionExtension",
    "XRInterfaceExtension",
)
VIRTUAL_SKIP_REASON = "internal/virtual callback methods are not emitted as public wrappers"
# Methods of a class in the lists above that skip because their call shape has no ObjectCalls helper
# yet, each with its reason (task 139 P5). The class stays held to "no other skip": a Godot bump that
# gives a listed class a method of a new shape adds a row here (upgrade_godot.sh prints it) instead of
# taking the whole class out of its list, which would let every later skip on it through unseen. A
# row whose method generates again, or that names no listed class, fails as stale.
PENDING_HELPER_SKIPS: dict[tuple[str, str], str] = {}
_SKIP_METHOD_RE = re.compile(r"(\w+)\(")
# Strip generated-doc KDoc blocks (single- OR multi-line) so the drift-gate compares wrapper
# *behavior*, letting `sync_kdoc_from_godot_docs.py` own the prose independently. The lazy
# `(?:(?!\*/).)*?` never spans two blocks. Single-line short docs (`/** text. Generated from
# Godot docs: X */`) must strip too, or old-style stale files falsely fail the gate.
GENERATED_KDOC_BLOCK = re.compile(
    r"\n?[ \t]*/\*\*(?:(?!\*/).)*?Generated from Godot docs:(?:(?!\*/).)*?\*/[ \t]*\n",
    re.DOTALL,
)


def comparable_source(source: str) -> str:
    """Compare generated wrapper behavior while letting KDoc sync own docs."""
    return GENERATED_KDOC_BLOCK.sub("\n", source)


def check_fixture(output_dir: Path, class_name: str, expected_skip_report: bool) -> int:
    skip_report = output_dir / f"{class_name}.skips.txt"
    subprocess.run(
        [
            sys.executable,
            str(ROOT / "scripts/generate_api_wrapper.py"),
            "--class",
            class_name,
            "--output-dir",
            str(output_dir),
            "--skip-report",
            str(skip_report),
        ],
        cwd=ROOT,
        check=True,
    )

    expected = (FIXTURE_DIR / f"{class_name}.kt").read_text(encoding="utf-8")
    actual = (output_dir / f"{class_name}.kt").read_text(encoding="utf-8")
    if comparable_source(actual) != comparable_source(expected):
        print(f"[wrapper_generator] FAIL {class_name}.kt fixture is stale", file=sys.stderr)
        print(
            f"[wrapper_generator] run generate_api_wrapper.py and update scripts/fixtures/wrapper_generator/{class_name}.kt",
            file=sys.stderr,
        )
        return 1

    actual_skips = skip_report.read_text(encoding="utf-8")
    expected_skip_path = FIXTURE_DIR / f"{class_name}.skips.txt"
    if expected_skip_report:
        expected_skips = expected_skip_path.read_text(encoding="utf-8")
        if actual_skips != expected_skips:
            print(f"[wrapper_generator] FAIL {class_name}.skips.txt fixture is stale", file=sys.stderr)
            print(
                f"[wrapper_generator] update scripts/fixtures/wrapper_generator/{class_name}.skips.txt",
                file=sys.stderr,
            )
            return 1
    elif actual_skips:
        print(f"[wrapper_generator] FAIL {class_name} fixture should not have skipped methods", file=sys.stderr)
        print(actual_skips, file=sys.stderr)
        return 1

    return 0


IOS_FIXTURE_CLASS = "Node3D"


# The GENERATED MEMBERS region the generator splices into the iOS ObjectCalls.kt, captured on its
# own for the fixture: the region body only, so it is comparable byte-for-byte (the real file is
# ktfmt-formatted, being hand-written outside the region). Not a compilable Kotlin file.
IOS_GENERATED_MEMBERS_FIXTURE = "ObjectCalls.generated-members.kt.txt"


def check_ios_fixture(output_dir: Path) -> int:
    """Lock the iOS emission target: the generated ObjectCalls MEMBERS region, the iOS
    wrapper (with its star-import injection), and the conservative skip report."""
    ios_dir = output_dir / "ios"
    ios_dir.mkdir(parents=True, exist_ok=True)
    objectcalls = ios_dir / IOS_GENERATED_MEMBERS_FIXTURE
    skip_report = ios_dir / f"{IOS_FIXTURE_CLASS}.ios.skips.txt"
    subprocess.run(
        [
            sys.executable,
            str(ROOT / "scripts/generate_api_wrapper.py"),
            "--ios-emit-class",
            IOS_FIXTURE_CLASS,
            "--ios-output-dir",
            str(ios_dir),
            "--ios-objectcalls",
            str(objectcalls),
            "--ios-skip-report",
            str(skip_report),
        ],
        cwd=ROOT,
        check=True,
    )

    fixture_ios = FIXTURE_DIR / "ios"
    checks = [
        (f"{IOS_FIXTURE_CLASS}.kt", True),
        (IOS_GENERATED_MEMBERS_FIXTURE, False),
        (f"{IOS_FIXTURE_CLASS}.ios.skips.txt", False),
    ]
    for name, sync_kdoc in checks:
        actual = (ios_dir / name).read_text(encoding="utf-8")
        expected = (fixture_ios / name).read_text(encoding="utf-8")
        if sync_kdoc:
            actual, expected = comparable_source(actual), comparable_source(expected)
        if actual != expected:
            print(f"[wrapper_generator] FAIL iOS fixture ios/{name} is stale", file=sys.stderr)
            print(
                "[wrapper_generator] re-run generate_api_wrapper.py --ios-emit-class "
                f"{IOS_FIXTURE_CLASS} and update scripts/fixtures/wrapper_generator/ios/",
                file=sys.stderr,
            )
            return 1
    return 0


def _gen_ios(output_dir: Path, *class_names: str) -> subprocess.CompletedProcess:
    """Run the iOS generator for one-or-more classes, capturing stdout+stderr."""
    return subprocess.run(
        [
            sys.executable,
            str(ROOT / "scripts/generate_api_wrapper.py"),
            *sum((["--ios-emit-class", c] for c in class_names), []),
            "--ios-output-dir",
            str(output_dir),
        ],
        cwd=ROOT,
        capture_output=True,
        text=True,
        check=True,
    )


def check_ios_policies(output_dir: Path) -> int:
    """Lock the task-11 generator policies so a refactor can't silently regress them.

    1. bare-`Object` returns are emitted on iOS (GodotObject wrap policy) — else regen
       silently drops get_collider()-style methods.
    2. createTween() is a MEMBER of the shared Node and SceneTree on every platform. Until task
       129 A, Tween was hand-written per platform, so the call was a per-platform extension
       (Node.jvm.kt / Node.ios.kt, SceneTree.*.kt) a script had to import by name; Tween is
       generated once now, and a per-platform createTween extension must not come back.
    3. a hand-written class (SceneTree) requested for emission is reported as a collision and
       NOT written — else a duplicate-class file breaks the compile.
    """
    policy_dir = output_dir / "ios-policies"
    policy_dir.mkdir(parents=True, exist_ok=True)

    _gen_ios(policy_dir, "KinematicCollision2D")
    kc = (policy_dir / "KinematicCollision2D.kt").read_text(encoding="utf-8")
    if "fun getCollider(): GodotObject?" not in kc:
        print("[wrapper_generator] FAIL bare-Object return getCollider() dropped on iOS "
              "(GodotObject wrap policy regressed)", file=sys.stderr)
        return 1
    for owner in ("Node", "SceneTree"):
        if "    fun createTween(): Tween {" not in (SHARED_API_DIR / f"{owner}.kt").read_text(encoding="utf-8"):
            print(f"[wrapper_generator] FAIL the shared {owner} no longer declares createTween() as a member "
                  "(task 129 A generated Tween once)", file=sys.stderr)
            return 1
    for directory in (DESKTOP_API_DIR, IOS_API_DIR):
        for path in sorted(directory.glob("*.kt")):
            if re.search(r"\bfun (Node|SceneTree)(\.Companion)?\.createTween\(", path.read_text(encoding="utf-8")):
                print(f"[wrapper_generator] FAIL {_rel(path)} declares a per-platform createTween extension; "
                      "it is a shared member since task 129 A", file=sys.stderr)
                return 1

    # Composite default-value override: Node3D.lookAt(up = Vector3.UP) — demos call the 1-arg
    # lookAt(target) form and rely on this default; a regen must not drop it.
    _gen_ios(policy_dir, "Node3D")
    node3d = (policy_dir / "Node3D.kt").read_text(encoding="utf-8")
    if "up: Vector3 = Vector3.UP" not in node3d:
        print("[wrapper_generator] FAIL Node3D.lookAt lost its `up = Vector3.UP` default "
              "(composite default-value override regressed)", file=sys.stderr)
        return 1

    # Non-null factory policy: Resource.fromHandle must be non-null so KanamaScript's
    # (GodotHandle) -> Resource selfFactory for @ScriptClass(attachTo = "Resource") type-checks.
    subprocess.run(
        [sys.executable, str(ROOT / "scripts/generate_api_wrapper.py"),
         "--class", "Resource", "--output-dir", str(policy_dir)],
        cwd=ROOT, check=True, capture_output=True,
    )
    resource = (policy_dir / "Resource.kt").read_text(encoding="utf-8")
    if "fun fromHandle(handle: GodotHandle): Resource =" not in resource:
        print("[wrapper_generator] FAIL Resource.fromHandle is not non-null "
              "(script-attachable non-null factory policy regressed)", file=sys.stderr)
        return 1

    # RefCounted return-slot ownership (task 30/31): RefCounted carries the release primitive
    # (close() = unreference + destroy at zero, releaseHandle for the collapse pattern), and fluent
    # self-returns must emit the same collapse pattern on iOS as on desktop — otherwise every
    # RefCounted-typed ptrcall return leaks an engine ref on iOS. Since task 117 P3' RefCounted is a
    # shared hand root (SHARED_HAND_ROOTS) written once in the shared tree, so the lock reads that
    # file, and the iOS renderer must refuse to emit a second RefCounted.
    refused = _gen_ios(policy_dir, "RefCounted", "Resource")
    if (policy_dir / "RefCounted.kt").exists() or "hand-root: RefCounted" not in refused.stderr:
        print("[wrapper_generator] FAIL the iOS renderer emitted RefCounted (or did not report the "
              "refusal); it is a shared hand root written once in the shared tree (task 117 P3')",
              file=sys.stderr)
        return 1
    rc = (SHARED_API_DIR / "RefCounted.kt").read_text(encoding="utf-8")
    if "override fun close()" not in rc or "internal fun releaseHandle" not in rc:
        print("[wrapper_generator] FAIL the shared RefCounted lost its ownership members "
              "(close()/releaseHandle — RefCounted returns would leak again)", file=sys.stderr)
        return 1
    ios_resource = (policy_dir / "Resource.kt").read_text(encoding="utf-8")
    if "RefCounted.releaseHandle(ret)" not in ios_resource:
        print("[wrapper_generator] FAIL iOS self-return collapse pattern not emitted "
              "(Resource.duplicate should carry the desktop collapse policy)", file=sys.stderr)
        return 1
    # Receiver-side use-after-close guard (task 98): RefCounted must carry checkOpen() and the
    # requireOpenHandle() override, and every RefCounted-derived method body must open with it, on
    # both platforms.
    if "internal fun checkOpen()" not in rc or "override fun requireOpenHandle()" not in rc:
        print("[wrapper_generator] FAIL the shared RefCounted lost its checkOpen()/requireOpenHandle() "
              "(use-after-close would be a native fault again)", file=sys.stderr)
        return 1
    if "        checkOpen()\n" not in ios_resource:
        print("[wrapper_generator] FAIL iOS RefCounted-derived methods no longer emit checkOpen() "
              "(receiver-side use-after-close guard regressed)", file=sys.stderr)
        return 1
    if "        checkOpen()\n" not in resource:
        print("[wrapper_generator] FAIL desktop RefCounted-derived methods no longer emit checkOpen() "
              "(receiver-side use-after-close guard regressed)", file=sys.stderr)
        return 1

    # No collision probe since task 129 D: FileAccess, the last class PER_PLATFORM_WRAPPERS marked
    # `collision` on iOS, is generated once, and the table is empty.
    return 0


def _api_class_names() -> set[str]:
    import json

    data = json.loads((ROOT / "extension_api.json").read_text(encoding="utf-8"))
    return {cls["name"] for cls in data["classes"]}


def _skip_method(line: str) -> str:
    match = _SKIP_METHOD_RE.search(line)
    return match.group(1) if match else line


def check_adopted_skips(
    tree: TreeResult,
    class_name: str,
    allow_virtual_skips: bool = False,
    baseline_methods: dict[str, set[str]] | None = None,
    pending: list[str] | None = None,
) -> int:
    """Adopted classes may only skip Godot virtual callbacks (override-registration design).

    A non-virtual skip listed in PENDING_HELPER_SKIPS is reported, not failed. With
    [baseline_methods] (the previous Godot's methods per class, `--baseline-api`: an upgrade run) a
    skip of a method the previous Godot did not have is reported too, as helper work the bump found
    (appended to [pending]); a method that existed before and skips now is still a failure."""
    skips = tree.skips.get(class_name)
    if skips is None:
        print(f"[wrapper_generator] FAIL adopted class {class_name} is not in the generated tree", file=sys.stderr)
        return 1
    bad_skips = [line for line in skips if not (allow_virtual_skips and VIRTUAL_SKIP_REASON in line)]
    failing: list[str] = []
    for line in bad_skips:
        method = _skip_method(line)
        if (class_name, method) in PENDING_HELPER_SKIPS:
            print(f"[wrapper_generator] pending helper: {class_name}.{method} skips "
                  f"({PENDING_HELPER_SKIPS[(class_name, method)]})")
        elif baseline_methods is not None and method not in baseline_methods.get(class_name, set()):
            if pending is not None:
                pending.append(f"{class_name}: {line}")
        else:
            failing.append(line)
    if failing:
        kind = "non-virtual skipped methods" if allow_virtual_skips else "skipped methods"
        print(f"[wrapper_generator] FAIL adopted source {class_name} has {kind}", file=sys.stderr)
        print("\n".join(failing), file=sys.stderr)
        print("    add the helper shape, or a PENDING_HELPER_SKIPS row with its reason "
              "(scripts/check_wrapper_generator.py); do not take the class out of its list", file=sys.stderr)
        return 1
    return 0


def stale_pending_skips(tree: TreeResult, listed: set[str]) -> list[str]:
    """PENDING_HELPER_SKIPS rows whose class is in no list or whose method no longer skips."""
    problems: list[str] = []
    for (class_name, method), _reason in sorted(PENDING_HELPER_SKIPS.items()):
        if class_name not in listed:
            problems.append(f"{class_name}.{method}: {class_name} is in none of the adopted-class lists")
        elif method not in {_skip_method(line) for line in tree.skips.get(class_name, [])}:
            problems.append(f"{class_name}.{method}: generates now; delete the row")
    return problems


def baseline_api_methods(path: Path) -> dict[str, set[str]]:
    """Method names per class of an `extension_api.json` (the previous Godot's, for `--baseline-api`)."""
    import json

    data = json.loads(path.read_text(encoding="utf-8"))
    return {cls["name"]: {m["name"] for m in cls.get("methods") or []} for cls in data.get("classes", [])}


def check_adoption(tree: TreeResult) -> int:
    """Task 139 P5: every class of the API has a wrapper (ADOPTION_EXCLUDED names the exceptions).
    `--write-tree` adopts a class the API gained, so a missing one means the tree was not re-adopted."""
    from generate_api_wrapper import unadopted_api_classes
    from wrapper_model import load_api_classes

    missing = unadopted_api_classes(load_api_classes(ROOT / "extension_api.json"))
    if missing:
        print(f"[wrapper_generator] FAIL {len(missing)} Godot class(es) in extension_api.json have no wrapper: "
              f"{', '.join(missing[:20])}{' ...' if len(missing) > 20 else ''}", file=sys.stderr)
        print("    adopt them: python3 scripts/generate_api_wrapper.py --write-tree  (or list one in "
              "ADOPTION_EXCLUDED in generate_api_wrapper.py with its reason)", file=sys.stderr)
        return 1
    return 0


def _rel(path: Path) -> str:
    return str(path.relative_to(ROOT))


def check_single_tree(tree: TreeResult) -> int:
    """The durable convergence gate (task 21, single-tree since task 103): every generated file the
    generator produces -- the shared tree, the per-platform generated classes, the desktop/iOS
    companions, the iOS ObjectCalls GENERATED MEMBERS region and the gap index -- must equal a fresh
    regen
    (behavior-comparable: sync_kdoc_from_godot_docs.py owns the KDoc prose). A hand edit to a
    generated file, an un-adopted generator improvement, a stale companion, or a per-platform copy
    of a shared class all fail here, so the platforms cannot drift: there is one tree to drift."""
    rc = 0
    missing: list[str] = []
    stale: list[str] = []
    for rel, content in sorted(tree.files.items()):
        path = ROOT / rel
        if not path.exists():
            missing.append(rel)
            continue
        committed = path.read_text(encoding="utf-8")
        if rel == _rel(IOS_OBJECTCALLS):
            # This file is HAND-WRITTEN outside its GENERATED MEMBERS region and ktfmt reformats the
            # region after every regen, so compare the region with ktfmt's changes normalised away
            # (`ios_generated_region_key`): a helper, a body or a PT_* tag value that differs is
            # drift, layout is not. The member names and their parameter names are also held to
            # desktop's by scripts/check_objectcalls_parity.py, a local_ci stage.
            same = ios_generated_region_key(committed) == ios_generated_region_key(content)
        elif rel == _rel(DESKTOP_OBJECTCALLS):
            # Hand-written except for WHICH members carry `actual` (task 139 P4): that set is the
            # generated expect's, and the regen toggles only the modifier. Compare the set, so a
            # ktfmt re-wrap is not drift but a hand-added or hand-dropped `actual` is.
            want = desktop_actual_state(content)[0]
            have = desktop_actual_state(committed)[0]
            same = want == have
            for name, _params in sorted(want - have):
                print(f"[wrapper_generator]     {rel}: {name} is in the expect but not `actual`", file=sys.stderr)
            for name, _params in sorted(have - want):
                print(f"[wrapper_generator]     {rel}: {name} is `actual` but not in the expect", file=sys.stderr)
        elif rel == _rel(OBJECTCALLS_EXPECT):
            # Under ktfmt like any hand-formatted source (the generator emits it unwrapped), so
            # compare with ktfmt's changes normalised away, as for the iOS region above.
            same = formatted_code_key(committed) == formatted_code_key(content)
        elif rel.endswith(".md"):
            same = committed == content
        elif (ROOT / rel).parent == TYPES_DIR:
            # A ktfmt-formatted value type carrying a GENERATED ENUMS region (task 128 A): ktfmt
            # re-wraps the region, so compare everything but whitespace (and the KDoc sync owns).
            same = whitespace_insensitive(comparable_source(committed)) == whitespace_insensitive(
                comparable_source(content)
            )
        else:
            same = comparable_source(committed) == comparable_source(content)
        if not same:
            stale.append(rel)
    if missing:
        rc = 1
        print(f"[wrapper_generator] FAIL single-tree drift-gate: {len(missing)} generated files are not committed", file=sys.stderr)
        for rel in missing[:40]:
            print(f"    {rel}", file=sys.stderr)
    if stale:
        rc = 1
        print(f"[wrapper_generator] FAIL single-tree drift-gate: {len(stale)} committed generated files differ from a fresh regen", file=sys.stderr)
        for rel in stale[:40]:
            print(f"    {rel}", file=sys.stderr)
        print("    re-adopt: python3 scripts/generate_api_wrapper.py --write-tree  (then sync_kdoc_from_godot_docs.py --write)", file=sys.stderr)
        print("    or, if the class is deliberately hand-shaped, add it to PER_PLATFORM_WRAPPERS in generate_api_wrapper.py", file=sys.stderr)

    orphan_companions = [_rel(p) for p in generated_companion_paths() if _rel(p) not in tree.files]
    if orphan_companions:
        rc = 1
        print(f"[wrapper_generator] FAIL {len(orphan_companions)} committed companion files are no longer generated (gap closed?): {orphan_companions[:20]}", file=sys.stderr)
        print("    remove them: python3 scripts/generate_api_wrapper.py --write-tree", file=sys.stderr)

    desktop_copies = [name for name in tree.shared if (DESKTOP_API_DIR / f"{name}.kt").exists()]
    ios_copies = [name for name in tree.shared if (IOS_API_DIR / f"{name}.kt").exists()]
    if desktop_copies:
        rc = 1
        print(f"[wrapper_generator] FAIL {len(desktop_copies)} shared classes also have a desktop copy under {_rel(DESKTOP_API_DIR)}: {desktop_copies[:20]}", file=sys.stderr)
    if ios_copies and not IOS_COPIES_PENDING_DELETION:
        rc = 1
        print(f"[wrapper_generator] FAIL {len(ios_copies)} shared classes also have an iOS copy under {_rel(IOS_API_DIR)}: {ios_copies[:20]}", file=sys.stderr)

    unexplained = unexplained_platform_files(tree)
    if unexplained:
        rc = 1
        print(f"[wrapper_generator] FAIL {len(unexplained)} unexplained file(s) in a platform api directory:", file=sys.stderr)
        for problem in unexplained:
            print(f"    {problem}", file=sys.stderr)

    missing_hand = missing_platform_hand_files()
    if missing_hand:
        rc = 1
        print(f"[wrapper_generator] FAIL {len(missing_hand)} listed platform hand file(s) no longer exist:", file=sys.stderr)
        for problem in missing_hand:
            print(f"    {problem}", file=sys.stderr)

    stale_roots = stale_hand_root_copies()
    if stale_roots:
        rc = 1
        print(f"[wrapper_generator] FAIL {len(stale_roots)} stale per-platform copies of a shared hand root "
              f"(task 117 P3' writes GodotObject/RefCounted/GodotCallable once, in {_rel(SHARED_API_DIR)}):",
              file=sys.stderr)
        for problem in stale_roots:
            print(f"    {problem}", file=sys.stderr)

    table_problems: list[str] = []
    for name, home in sorted(PER_PLATFORM_WRAPPERS.items()):
        if (SHARED_API_DIR / f"{name}.kt").exists():
            table_problems.append(f"{name}: per-platform class has a file in the shared tree")
        if home.desktop == "hand" and not (DESKTOP_API_DIR / f"{name}.kt").exists():
            table_problems.append(f"{name}: desktop hand-shaped but {_rel(DESKTOP_API_DIR)}/{name}.kt is missing")
        if home.ios == "hand" and not (IOS_API_DIR / f"{name}.kt").exists():
            table_problems.append(f"{name}: iOS hand-shaped but {_rel(IOS_API_DIR)}/{name}.kt is missing")
        if home.ios == "unsupported" and (IOS_API_DIR / f"{name}.kt").exists():
            table_problems.append(f"{name}: iOS unsupported yet {_rel(IOS_API_DIR)}/{name}.kt exists")
    if table_problems:
        rc = 1
        print("[wrapper_generator] FAIL PER_PLATFORM_WRAPPERS does not match the committed files:", file=sys.stderr)
        for problem in table_problems:
            print(f"    {problem}", file=sys.stderr)

    if rc == 0:
        companions = sum(1 for rel in tree.files if rel.endswith(DESKTOP_COMPANION_SUFFIX))
        members = sum(len(r.desktop_only_members) for r in tree.gap.values())
        helpers = {token for r in tree.gap.values() for token in r.waits_on}
        pending = f" ios-copies-pending-deletion={len(ios_copies)}" if ios_copies else ""
        print(
            f"[wrapper_generator] PASS single-tree drift-gate shared={len(tree.shared)} "
            f"desktop-only={len(tree.desktop_only)} ios-only={len(tree.ios_only)} "
            f"desktop-companions={companions} (gap: {members} members, {len(helpers)} helpers/wrappers) "
            f"ios-companions={sum(1 for rel in tree.files if rel.endswith(IOS_COMPANION_SUFFIX))} "
            f"(hand: desktop={len(DESKTOP_HANDSHAPED)} ios={len(IOS_HANDSHAPED)} "
            f"collision={len(IOS_HANDWRITTEN_COLLISION_CLASSES)} unsupported={len(IOS_UNSUPPORTED_CLASSES)}){pending}"
        )
    return rc


# Leading annotations (`@Suppress("X")`, on the same line or the lines above) and every modifier a
# stale copy could carry, `private` and `sealed` included: a `private class GodotObject` in a
# platform file still shadows the shared root inside that file.
_ROOT_DECLARATION_RE = re.compile(
    r"(?m)^(?:@[\w.]+(?:\([^)]*\))?\s+)*"
    r"(?:(?:public|internal|private|open|abstract|sealed|data|expect|actual)\s+)*class\s+("
    + "|".join(SHARED_HAND_ROOT_FILES)
    + r")\b"
)


# The hand-written files that may sit in a platform's api directory (task 119 finding 34). A wrapper
# class is generated once into the shared tree, or listed in PER_PLATFORM_WRAPPERS; everything else
# here is platform glue with a stated job. A file in neither list (a retired wrapper restored by a
# merge, a copy pasted back) compiled and passed every gate before this check.
PLATFORM_HAND_FILES: dict[str, dict[str, str]] = {
    "desktop": {
        "GodotSignal": "actual of the shared GodotSignal expect",
        "MainThread": "actual of the shared MainThread expect",
        "ScriptResources": "desktop's script-resource wrappers",
        "ScriptRuntime": "desktop's script runtime seam",
    },
    "iOS": {
        "GodotSignal": "actual of the shared GodotSignal expect",
        "MainThread": "actual of the shared MainThread expect",
        "IosGodotApi": "the iOS host's entry glue",
        "ScriptAccessSelfTest": "the iOS script-access self-test",
        "ScriptResources": "iOS's script-resource wrappers",
        "ScriptRuntime": "iOS's script runtime seam",
    },
}


def unexplained_platform_files(tree: TreeResult) -> list[str]:
    """Files in a platform api directory that are neither generated (`tree.files`), nor a
    PER_PLATFORM_WRAPPERS class, nor a listed hand file (task 119 finding 34)."""
    problems: list[str] = []
    for label, directory in (("desktop", DESKTOP_API_DIR), ("iOS", IOS_API_DIR)):
        for path in sorted(directory.glob("*.kt")):
            if _rel(path) in tree.files or path.stem in PER_PLATFORM_WRAPPERS:
                continue
            if path.stem in PLATFORM_HAND_FILES[label]:
                continue
            problems.append(
                f"{_rel(path)}: not generated, not in PER_PLATFORM_WRAPPERS and not a listed "
                f"{label} hand file (PLATFORM_HAND_FILES in scripts/check_wrapper_generator.py) "
                "-- a stale copy of a retired wrapper? delete it"
            )
    return problems


def missing_platform_hand_files() -> list[str]:
    """The reverse of [unexplained_platform_files]: a PLATFORM_HAND_FILES entry whose file no longer
    exists. A retired hand file stays listed, so the list would keep excusing a later file of that
    name (a stale copy restored by a merge) that nothing generates."""
    problems: list[str] = []
    for label, directory in (("desktop", DESKTOP_API_DIR), ("iOS", IOS_API_DIR)):
        for name in sorted(PLATFORM_HAND_FILES[label]):
            if not (directory / f"{name}.kt").exists():
                problems.append(
                    f"{_rel(directory)}/{name}.kt: listed as a {label} hand file in "
                    "PLATFORM_HAND_FILES (scripts/check_wrapper_generator.py) but the file does "
                    "not exist -- remove the entry, or restore the file"
                )
    return problems


def stale_hand_root_copies() -> list[str]:
    """Task 119 item 34, for the shared hand roots (task 117 P3'): each root is ONE hand-written file
    in the shared tree, so a `<Root>.kt` in a platform directory, or a `class <Root>` declared in any
    platform api file (the iOS GodotObject used to live inside IosGodotApi.kt), is a stale copy that
    would either fail one platform's compile as a redeclaration or, worse, shadow the shared class
    in a lane that compiles the platform tree alone."""
    problems: list[str] = []
    for name in SHARED_HAND_ROOT_FILES:
        if not (SHARED_API_DIR / f"{name}.kt").exists():
            problems.append(f"{name}: {_rel(SHARED_API_DIR)}/{name}.kt is missing")
    for directory in (DESKTOP_API_DIR, IOS_API_DIR):
        for path in sorted(directory.glob("*.kt")):
            if path.stem in SHARED_HAND_ROOT_FILES:
                problems.append(f"{path.stem}: per-platform file {_rel(path)}")
                continue
            for match in _ROOT_DECLARATION_RE.finditer(strip_comments(path.read_text(encoding="utf-8"))):
                problems.append(f"{match.group(1)}: declared in {_rel(path)}")
    return problems


SHARED_SOURCE_ROOTS = (ROOT / "src/commonMain/kotlin",)


def check_shared_tree_pointer() -> int:
    """No file under src/commonMain may name a `java.lang.foreign` type (task 104 step 3).

    src/commonMain/kotlin is the module's KMP common fragment -- the value types, GodotHandle, the
    expect seams and, since task 117 P4', the whole generated wrapper tree -- compiled on every
    platform, where a JDK package cannot be declared or `expect`ed at all. The compiler already
    refuses one there; this gate names the file next to the edit, before a Gradle run, and keeps
    the message pointing at the fix. The raw engine pointer travels under Kanama's own name,
    `net.multigesture.kanama.binding.runtime.RawSegment`, which each platform aliases.

    Comments are stripped first: the `expect` files' KDoc names the JVM type on purpose when it
    explains the `actual typealias`, and prose is not what this gate is about (the Android remap
    audit strips comments for the same reason since task 104 step 3 parcel D).
    """
    offenders = sorted(
        path
        for root in SHARED_SOURCE_ROOTS
        for path in root.rglob("*.kt")
        if "java.lang.foreign" in strip_comments(path.read_text(encoding="utf-8"))
    )
    if offenders:
        roots = ", ".join(_rel(root) for root in SHARED_SOURCE_ROOTS)
        print(
            f"[wrapper_generator] FAIL {len(offenders)} files under {roots} name a "
            "java.lang.foreign type; the shared sources use RawSegment / NULL_SEGMENT",
            file=sys.stderr,
        )
        for path in offenders[:40]:
            print(f"    {_rel(path)}", file=sys.stderr)
        return 1
    scanned = sum(1 for root in SHARED_SOURCE_ROOTS for _ in root.rglob("*.kt"))
    roots = ", ".join(_rel(root) for root in SHARED_SOURCE_ROOTS)
    print(
        f"[wrapper_generator] PASS shared sources name no java.lang.foreign type "
        f"({scanned} files under {roots})"
    )
    return 0


RETIRED_SHARED_API_DIR = ROOT / "src/sharedApi"


def check_no_shared_api_dir() -> int:
    """`src/sharedApi` must not come back (task 117 P4').

    The API tree moved to `src/commonMain/kotlin/net/multigesture/kanama/api/` and no source set
    lists `src/sharedApi` any more, so files resurrected there (a stale branch, a bad merge, an old
    script) would compile in NO lane: silently dead code that still looks like the API. Any entry,
    even an empty directory, fails.
    """
    if RETIRED_SHARED_API_DIR.exists():
        entries = sorted(_rel(path) for path in RETIRED_SHARED_API_DIR.rglob("*") if path.is_file())
        print(
            f"[wrapper_generator] FAIL {_rel(RETIRED_SHARED_API_DIR)} exists ({len(entries)} file(s)); the API "
            "tree lives in src/commonMain/kotlin/net/multigesture/kanama/api since task 117 P4' and no "
            "source set compiles src/sharedApi -- move the files there or delete the directory",
            file=sys.stderr,
        )
        for rel in entries[:40]:
            print(f"    {rel}", file=sys.stderr)
        return 1
    return 0


TYPED_SIGNAL_ACCESSOR = re.compile(
    r'val (\w+): Signal(\d)\b[^\n]*\n\s*@JvmName\("\w+TypedSignal"\)\n\s*get\(\) = Signal\d\((?:this|GodotObject\(GodotHandle\(singleton\)\)), "(\w+)"'
)


def check_typed_signals() -> int:
    """Task 134 D4: every engine signal in extension_api.json has a typed accessor (`val bodyEntered:
    Signal1<Node2D>`) on its class, with the arity Godot declares, under the name
    signal_accessor_names() chose (the `Signal` suffix on collisions). Object's two signals live on
    the hand-written GodotObject, so the gate reads every wrapper source, generated or not."""
    import json

    from generate_api_wrapper import signal_accessor_names
    from wrapper_model import load_api_classes

    api_classes = load_api_classes(ROOT / "extension_api.json")
    expected = signal_accessor_names(api_classes)
    raw = json.loads((ROOT / "extension_api.json").read_text(encoding="utf-8"))
    arity = {
        (cls["name"], sig["name"]): len(sig.get("arguments") or [])
        for cls in raw.get("classes", [])
        for sig in cls.get("signals") or []
    }
    found: dict[tuple[str, str], set[tuple[str, int]]] = {}
    for directory in (SHARED_API_DIR, DESKTOP_API_DIR, IOS_API_DIR):
        for path in sorted(directory.glob("*.kt")):
            text = path.read_text(encoding="utf-8")
            for match in TYPED_SIGNAL_ACCESSOR.finditer(text):
                prop, n, signal = match.group(1), int(match.group(2)), match.group(3)
                found.setdefault((path.name.split(".")[0], signal), set()).add((prop, n))
    missing: list[str] = []
    wrong: list[str] = []
    for (cls, signal), n in sorted(arity.items()):
        file_cls = "GodotObject" if cls == "Object" else cls
        hits = found.get((file_cls, signal))
        if not hits:
            missing.append(f"{cls}.{signal}")
            continue
        name = expected.get((cls, signal))
        for prop, got in hits:
            if got != n or (name is not None and prop != name):
                wrong.append(f"{cls}.{signal}: {prop}: Signal{got} (expected {name}: Signal{n})")
    # The nullability/type tables must name real object arguments (a Godot upgrade that renames a
    # signal or moves an argument must not leave a silent no-op entry).
    from generate_api_wrapper import (
        SIGNAL_ARG_TYPE_OVERRIDES,
        SIGNAL_NON_NULL_RESOURCE_ARGS,
        SIGNAL_NULLABLE_OBJECT_ARGS,
    )

    declared = {
        (cls["name"], sig["name"], index): arg["type"]
        for cls in raw.get("classes", [])
        for sig in cls.get("signals") or []
        for index, arg in enumerate(sig.get("arguments") or [])
    }
    for table_name, table in (
        ("SIGNAL_NULLABLE_OBJECT_ARGS", SIGNAL_NULLABLE_OBJECT_ARGS),
        ("SIGNAL_NON_NULL_RESOURCE_ARGS", SIGNAL_NON_NULL_RESOURCE_ARGS),
        ("SIGNAL_ARG_TYPE_OVERRIDES", SIGNAL_ARG_TYPE_OVERRIDES),
    ):
        for key in table:
            if declared.get(key) not in api_classes:
                wrong.append(f"{table_name} entry {key} is not an object argument of the API")
    if missing or wrong:
        for line in missing:
            print(f"[wrapper_generator] FAIL typed signal missing: {line}", file=sys.stderr)
        for line in wrong:
            print(f"[wrapper_generator] FAIL typed signal mismatch: {line}", file=sys.stderr)
        return 1
    renamed = sorted(f"{c}.{s} -> {n}" for (c, s), n in expected.items() if n.endswith("Signal") and not s.endswith("signal"))
    print(
        f"[wrapper_generator] PASS typed signals: {len(arity)} engine signals, each with a typed accessor; "
        f"{len(renamed)} renamed with the Signal suffix{': ' + ', '.join(renamed) if renamed else ''}"
    )
    return 0


# Task 138 item 6 (R6.3): one probe per body shape, each with the fence lines it must render after
# its engine call (class file, function header, required lines in order).
KEEP_REACHABLE_PROBES = (
    # void, receiver-guarded
    ("Curve", "    fun clearPoints() {", ("checkOpen()", "ObjectCalls.", "ObjectCalls.keepReachable(this)")),
    # returning, receiver-guarded
    ("Thread", "    fun getId(): String {", ("val ret = ObjectCalls.", "ObjectCalls.keepReachable(this)", "return ret")),
    # Callable argument
    ("Thread", "    fun start(", ("ObjectCalls.keepReachable(this)", "ObjectCalls.keepReachable(callable)", "return ret")),
    # self-return collapse: fenced before either return
    ("Tween", "    fun setLoops(", ("val ret = ObjectCalls.", "ObjectCalls.keepReachable(this)", "return this", "return ")),
    # nullable object argument on a Node receiver (no receiver fence)
    ("MeshInstance3D", "    fun setMesh(mesh: Mesh?) {", ("ObjectCalls.", "ObjectCalls.keepReachable(mesh)")),
    # Signal argument
    ("Tween", "    fun tweenAwait(", ("ObjectCalls.keepReachable(this)", "ObjectCalls.keepReachable(signal)", "return ret")),
    # varargs: the receiver only (callWithVariantArgs fences the list)
    ("GDScript", "    fun new(vararg extraArgs: Any?): Any? {", ("val ret = ObjectCalls.callWithVariantArgs(", "ObjectCalls.keepReachable(this)", "return ret")),
    # from* downcast: the source stays reachable until the +1 is taken
    ("ArrayMesh", "        fun fromResource(", ("RefCounted.retained(ArrayMesh(value.handle), value)",)),
)

# An argument's engine pointer read in a generated body: `x.requireOpenHandle()`, `x?.segment`,
# `x.target.segment` (Callable), `x.owner.segment` (Signal). The bare receiver read is `segment`.
_ARG_HANDLE_READ = re.compile(r"\b(\w+)\??\.(?:requireOpenHandle\(\)|segment\b|target\.segment\b|owner\.segment\b)")
_RECEIVER_HANDLE_READ = re.compile(r"(?<![\w.?])segment\b")
_ENGINE_CALL = re.compile(r"\bObjectCalls\.(?!keepReachable\()\w+")
_FUN_START = re.compile(r"^(\s*)(?:[\w@]+(?:\([^)]*\))?\s+)*fun\s+(?:<[^>]*>\s*)?(?:([\w.]+)\.)?\w+\s*\(")
_CLASS_HEADER = re.compile(
    r"^(?:(?:open|abstract|sealed|data|internal|public)\s+)*class\s+(\w+)(?:\s+internal\s+constructor)?\s*\([^)]*\)\s*:\s*(\w+)\(",
    re.M,
)


def _header_end(lines: list[str], start: int) -> tuple[int, int, str] | None:
    """(line, column, `{` or `=`) that ends the header of the function declared at lines[start].

    Parentheses (parameter lists, default arguments) and string literals are skipped, so a header
    may span lines and carry default values."""
    depth = 0
    for j in range(start, len(lines)):
        line = lines[j]
        k = 0
        while k < len(line):
            ch = line[k]
            if ch == '"':
                k = line.find('"', k + 1)
                if k < 0:
                    break
            elif ch == "(":
                depth += 1
            elif ch == ")":
                depth -= 1
            elif depth == 0 and ch == "{":
                return j, k, "{"
            elif depth == 0 and ch == "=" and line[k - 1 : k] not in ("!", "<", ">", "=") and line[k + 1 : k + 2] != "=":
                return j, k, "="
            k += 1
    return None


def _function_bodies(text: str) -> list[tuple[int, str | None, str]]:
    """(header line number, extension receiver type or None, body text) of every function in a
    generated source: block bodies (`{ ... }`, also on one line) and expression bodies (`= ...`,
    continued on deeper-indented lines), with the header on one line or several."""
    lines = text.splitlines()
    bodies: list[tuple[int, str | None, str]] = []
    i = 0
    while i < len(lines):
        match = _FUN_START.match(lines[i])
        end = _header_end(lines, i) if match else None
        if not match or end is None:
            i += 1
            continue
        indent, receiver = match.group(1), match.group(2)
        j, col, kind = end
        rest = lines[j][col + 1 :]
        if kind == "{" and rest.count("{") < rest.count("}"):  # `fun f() { call() }`
            body, nxt = [rest[: rest.rfind("}")]], j + 1
        elif kind == "{":
            closer = indent + "}"
            nxt = j + 1
            while nxt < len(lines) and lines[nxt].rstrip() != closer:
                nxt += 1
            body, nxt = [rest] + lines[j + 1 : nxt], nxt + 1
        else:
            body, nxt = [rest], j + 1
            while nxt < len(lines) and lines[nxt].strip() and len(lines[nxt]) - len(lines[nxt].lstrip()) > len(indent):
                body.append(lines[nxt])
                nxt += 1
        bodies.append((i + 1, receiver.split("<")[0] if receiver else None, "\n".join(body)))
        i = nxt
    return bodies


def _refcounted_classes(files: dict[str, str]) -> set[str]:
    """Every class of the tree that is RefCounted or inherits it (the receivers the drain releases)."""
    parent = {name: base for text in files.values() for name, base in _CLASS_HEADER.findall(text)}
    found = {"RefCounted"}
    for name in parent:
        chain, cls = [], name
        while cls in parent and cls not in found and cls not in chain:
            chain.append(cls)
            cls = parent[cls]
        if cls in found:
            found.update(chain)
    return found


def keep_reachable_problems(files: dict[str, str]) -> list[str]:
    """Every generated body that reads an engine pointer fences what it read (task 138 item 6).

    A body of a RefCounted-derived receiver (its class, or the receiver type of an extension
    function) that makes an engine call (`ObjectCalls.`) and reads the bare `segment` -- block- or
    expression-bodied, its header on one line or several -- or that carries the receiver guard
    (`checkOpen()`) must call `ObjectCalls.keepReachable(this)` after its last engine call; a body
    that reads an argument's handle must call `ObjectCalls.keepReachable(arg)` there too.
    """
    problems: list[str] = []
    refcounted = _refcounted_classes(files)
    for rel, text in sorted(files.items()):
        # The hand roots (GodotObject, RefCounted, GodotCallable) carry only a generated enum region;
        # their bodies fence through GodotObject.fenced, audited by hand.
        if not rel.endswith(".kt") or "/api/" not in rel or Path(rel).stem in SHARED_HAND_ROOT_FILES:
            continue
        owner = Path(rel).name.split(".")[0]
        for line_no, receiver, body in _function_bodies(text):
            calls = list(_ENGINE_CALL.finditer(body))
            if not calls:
                continue
            last = calls[-1].start()
            fences = set(re.findall(r"ObjectCalls\.keepReachable\((\w+)\)", body[last:]))
            guarded = re.search(r"(?m)^\s*checkOpen\(\)\s*$", body) is not None
            reads_receiver = (receiver or owner) in refcounted and _RECEIVER_HANDLE_READ.search(body) is not None
            if (guarded or reads_receiver) and "this" not in fences:
                why = "receiver-guarded" if guarded else "RefCounted receiver"
                problems.append(f"{rel}:{line_no}: {why} body without ObjectCalls.keepReachable(this) after its last call")
            for name in sorted({m.group(1) for m in _ARG_HANDLE_READ.finditer(body)} - {"this", "handle"}):
                if name not in fences:
                    problems.append(f"{rel}:{line_no}: reads {name}'s handle without ObjectCalls.keepReachable({name}) after the call")
    return problems


# Shapes the sweep must flag, checked on every run so the detector cannot go blind (P2 review): an
# expression body, a header on several lines, and a RefCounted body without the receiver guard.
KEEP_REACHABLE_RED_FIXTURES = (
    ("expression body", "class Curve(handle: GodotHandle) : Resource(handle) {\n"
     "    fun getMinValue(): Double = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMinValueBind, segment)\n}\n"),
    ("header on several lines", "class Curve(handle: GodotHandle) : Resource(handle) {\n"
     "    fun addPoint(\n        position: Vector2,\n        leftTangent: Double = 0.0,\n    ): Long {\n"
     "        checkOpen()\n        return ObjectCalls.ptrcallWithVector2AndDoubleArgsRetLong(Binds.addPointBind, segment, position, leftTangent)\n"
     "    }\n}\n"),
    ("RefCounted body without checkOpen()", "class Curve(handle: GodotHandle) : Resource(handle) {\n"
     "    fun clearPoints() {\n        ObjectCalls.ptrcallNoArgs(Binds.clearPointsBind, segment)\n    }\n}\n"),
    ("argument read off the call line", "class MeshInstance3D(handle: GodotHandle) : Node3D(handle) {\n"
     "    fun setMesh(mesh: Mesh?) {\n        val raw = mesh?.requireOpenHandle()\n"
     "        ObjectCalls.ptrcallWithObjectArg(Binds.setMeshBind, segment, raw)\n    }\n}\n"),
)


def keep_reachable_detector_blind_spots() -> list[str]:
    """The red fixtures the sweep does NOT flag (empty when the detector works)."""
    base = {"src/commonMain/kotlin/net/multigesture/kanama/api/Resource.kt":
            "open class Resource(handle: GodotHandle) : RefCounted(handle) {\n}\n"}
    blind = []
    for name, text in KEEP_REACHABLE_RED_FIXTURES:
        cls = re.search(r"class (\w+)", text).group(1)
        files = dict(base, **{f"src/commonMain/kotlin/net/multigesture/kanama/api/{cls}.kt": text})
        if not keep_reachable_problems(files):
            blind.append(name)
    return blind


def check_keep_reachable(tree: TreeResult) -> int:
    """R6.3: the probes render their fence lines, and no generated body misses one."""
    failures: list[str] = []
    for cls, header, required in KEEP_REACHABLE_PROBES:
        text = tree.files.get(_rel(SHARED_API_DIR / f"{cls}.kt"), "")
        start = text.find("\n" + header)
        if start < 0:
            failures.append(f"probe {cls}: no `{header.strip()}` in the regenerated tree")
            continue
        indent = header[: len(header) - len(header.lstrip())]
        end = text.find("\n" + indent + "}", start + 1)
        body = text[start : end if header.rstrip().endswith("{") else text.find("\n\n", start + 1)]
        position = 0
        for needle in required:
            found = body.find(needle, position)
            if found < 0:
                failures.append(f"probe {cls}.{header.strip()}: `{needle}` missing or out of order")
                break
            position = found + len(needle)
    failures += [f"the sweep misses a known-bad shape: {name}" for name in keep_reachable_detector_blind_spots()]
    failures += keep_reachable_problems(tree.files)
    if failures:
        for line in failures[:40]:
            print(f"[wrapper_generator] FAIL keep-reachable fence: {line}", file=sys.stderr)
        if len(failures) > 40:
            print(f"[wrapper_generator] ... and {len(failures) - 40} more", file=sys.stderr)
        return 1
    fenced = sum(text.count("ObjectCalls.keepReachable(") for text in tree.files.values())
    print(
        f"[wrapper_generator] PASS keep-reachable fences: {len(KEEP_REACHABLE_PROBES)} body-shape probes, "
        f"{len(KEEP_REACHABLE_RED_FIXTURES)} known-bad shapes flagged, {fenced} fence lines, every engine-pointer read fenced"
    )
    return 0


def main() -> int:
    import argparse

    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument(
        "--baseline-api",
        type=Path,
        help="the previous Godot's extension_api.json (upgrade_godot.sh passes HEAD's): a skip of a method "
             "that Godot did not have is reported as pending helper work instead of failing its class's list",
    )
    args = parser.parse_args()
    baseline = baseline_api_methods(args.baseline_api) if args.baseline_api else None

    if check_no_shared_api_dir() != 0:
        return 1
    name_constants = subprocess.run(
        [sys.executable, str(ROOT / "scripts/generate_name_constants.py"), "--check"],
        cwd=ROOT,
    )
    if name_constants.returncode != 0:
        return name_constants.returncode

    with tempfile.TemporaryDirectory(prefix="kanama-wrapper-generator-") as tmp:
        output_dir = Path(tmp)
        if check_fixture(output_dir, "TextureRect", expected_skip_report=False) != 0:
            return 1
        if check_fixture(output_dir, "OptionButton", expected_skip_report=True) != 0:
            return 1
        if check_ios_fixture(output_dir) != 0:
            return 1
        if check_ios_policies(output_dir) != 0:
            return 1

    tree = regenerate_tree(ROOT / "extension_api.json")
    pending: list[str] = []
    rc = 0
    for class_name in ADOPTED_CLASSES + ADOPTED_RESOURCE_DOWNCAST_CLASSES + ADOPTED_SHELL_ONLY_CLASSES:
        rc |= check_adopted_skips(tree, class_name, baseline_methods=baseline, pending=pending)
    for class_name in ADOPTED_CLASSES_WITH_HELPERS_AND_VIRTUAL_SKIPS + ADOPTED_CLASSES_WITH_VIRTUAL_SKIPS:
        rc |= check_adopted_skips(tree, class_name, allow_virtual_skips=True, baseline_methods=baseline, pending=pending)
    listed = set(
        ADOPTED_CLASSES
        + ADOPTED_RESOURCE_DOWNCAST_CLASSES
        + ADOPTED_SHELL_ONLY_CLASSES
        + ADOPTED_CLASSES_WITH_HELPERS_AND_VIRTUAL_SKIPS
        + ADOPTED_CLASSES_WITH_VIRTUAL_SKIPS
    )
    stale = stale_pending_skips(tree, listed)
    if stale:
        print("[wrapper_generator] FAIL stale PENDING_HELPER_SKIPS rows:", file=sys.stderr)
        for problem in stale:
            print(f"    {problem}", file=sys.stderr)
        rc = 1
    if pending:
        print(f"[wrapper_generator] PENDING {len(pending)} method(s) the new Godot added to an adopted-list class "
              "skip for want of a helper (reported, not failed, under --baseline-api). Add the helper, or one "
              "PENDING_HELPER_SKIPS row each in scripts/check_wrapper_generator.py:")
        for line in pending:
            cls, _, rest = line.partition(": ")
            print(f'    ("{cls}", "{_skip_method(rest)}"): "{rest.split(": ", 1)[-1]}",')
    if rc != 0:
        return rc
    if check_adoption(tree) != 0:
        return 1
    if check_single_tree(tree) != 0:
        return 1
    if check_shared_tree_pointer() != 0:
        return 1
    if check_typed_signals() != 0:
        return 1
    if check_keep_reachable(tree) != 0:
        return 1

    adopted = (
        ADOPTED_CLASSES
        + ADOPTED_RESOURCE_DOWNCAST_CLASSES
        + ADOPTED_SHELL_ONLY_CLASSES
        + ADOPTED_CLASSES_WITH_HELPERS_AND_VIRTUAL_SKIPS
        + ADOPTED_CLASSES_WITH_VIRTUAL_SKIPS
    )
    print(f"[wrapper_generator] PASS adopted={','.join(adopted)}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
