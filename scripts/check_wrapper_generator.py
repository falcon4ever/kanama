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
    DESKTOP_ONLY_GENERATED,
    GAP_INDEX_PATH,
    IOS_COMPANION_SUFFIX,
    IOS_HANDSHAPED,
    IOS_HANDWRITTEN_COLLISION_CLASSES,
    IOS_OBJECTCALLS_GENERATED,
    IOS_ONLY_GENERATED,
    IOS_UNSUPPORTED_CLASSES,
    PER_PLATFORM_WRAPPERS,
    TreeResult,
    generated_companion_paths,
    regenerate_tree,
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


def check_ios_fixture(output_dir: Path) -> int:
    """Lock the iOS emission target: generated ObjectCalls helper bodies, the iOS
    wrapper (with its extension-import injection), and the conservative skip report."""
    ios_dir = output_dir / "ios"
    ios_dir.mkdir(parents=True, exist_ok=True)
    objectcalls = ios_dir / "ObjectCallsGenerated.kt"
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
        ("ObjectCallsGenerated.kt", False),
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
    2. Node.createTween() is generated `open` so the hand-written SceneTree subclass can
       override it (the FPS F2 fix) — else regen re-breaks the SIGSEGV path.
    3. a hand-written class (SceneTree) requested for emission is reported as a collision and
       NOT written — else a duplicate-class file breaks the compile.
    """
    policy_dir = output_dir / "ios-policies"
    policy_dir.mkdir(parents=True, exist_ok=True)

    _gen_ios(policy_dir, "KinematicCollision2D", "Node")
    kc = (policy_dir / "KinematicCollision2D.kt").read_text(encoding="utf-8")
    if "fun getCollider(): GodotObject?" not in kc:
        print("[wrapper_generator] FAIL bare-Object return getCollider() dropped on iOS "
              "(GodotObject wrap policy regressed)", file=sys.stderr)
        return 1
    node = (policy_dir / "Node.kt").read_text(encoding="utf-8")
    if "open fun createTween(): Tween?" not in node:
        print("[wrapper_generator] FAIL Node.createTween() is not generated `open` "
              "(subclass-override policy regressed — breaks the SceneTree F2 fix)", file=sys.stderr)
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
    # (MemorySegment) -> Resource selfFactory for @ScriptClass(attachTo = "Resource") type-checks.
    subprocess.run(
        [sys.executable, str(ROOT / "scripts/generate_api_wrapper.py"),
         "--class", "Resource", "--output-dir", str(policy_dir)],
        cwd=ROOT, check=True, capture_output=True,
    )
    resource = (policy_dir / "Resource.kt").read_text(encoding="utf-8")
    if "fun fromHandle(handle: MemorySegment): Resource =" not in resource:
        print("[wrapper_generator] FAIL Resource.fromHandle is not non-null "
              "(script-attachable non-null factory policy regressed)", file=sys.stderr)
        return 1

    # RefCounted return-slot ownership mirror (task 30/31): the iOS RefCounted wrapper must
    # carry the release primitive (close() = unreference + destroy at zero, releaseHandle for
    # the collapse pattern), and fluent self-returns must emit the same collapse pattern as
    # desktop — otherwise every RefCounted-typed ptrcall return leaks an engine ref on iOS.
    _gen_ios(policy_dir, "RefCounted", "Resource")
    rc = (policy_dir / "RefCounted.kt").read_text(encoding="utf-8")
    if "override fun close()" not in rc or "internal fun releaseHandle" not in rc:
        print("[wrapper_generator] FAIL iOS RefCounted lost its ownership custom sections "
              "(close()/releaseHandle — RefCounted returns would leak again)", file=sys.stderr)
        return 1
    ios_resource = (policy_dir / "Resource.kt").read_text(encoding="utf-8")
    if "RefCounted.releaseHandle(ret)" not in ios_resource:
        print("[wrapper_generator] FAIL iOS self-return collapse pattern not emitted "
              "(Resource.duplicate should carry the desktop collapse policy)", file=sys.stderr)
        return 1
    # Receiver-side use-after-close guard (task 98): the iOS RefCounted must carry checkOpen()
    # and every RefCounted-derived method body must open with it, on both platforms.
    if "internal fun checkOpen()" not in rc or "override fun requireOpenHandle()" not in rc:
        print("[wrapper_generator] FAIL iOS RefCounted lost its checkOpen()/requireOpenHandle() "
              "custom section (use-after-close would be a native fault again)", file=sys.stderr)
        return 1
    if "        checkOpen()\n" not in ios_resource:
        print("[wrapper_generator] FAIL iOS RefCounted-derived methods no longer emit checkOpen() "
              "(receiver-side use-after-close guard regressed)", file=sys.stderr)
        return 1
    if "        checkOpen()\n" not in resource:
        print("[wrapper_generator] FAIL desktop RefCounted-derived methods no longer emit checkOpen() "
              "(receiver-side use-after-close guard regressed)", file=sys.stderr)
        return 1

    collision = _gen_ios(policy_dir, "SceneTree")
    if (policy_dir / "SceneTree.kt").exists():
        print("[wrapper_generator] FAIL SceneTree.kt emitted despite being hand-written "
              "(collision policy regressed)", file=sys.stderr)
        return 1
    if "collision: SceneTree is hand-written" not in collision.stderr:
        print("[wrapper_generator] FAIL SceneTree collision not reported "
              "(collision policy regressed)", file=sys.stderr)
        return 1
    return 0


def _api_class_names() -> set[str]:
    import json

    data = json.loads((ROOT / "extension_api.json").read_text(encoding="utf-8"))
    return {cls["name"] for cls in data["classes"]}


def check_adopted_skips(tree: TreeResult, class_name: str, allow_virtual_skips: bool = False) -> int:
    """Adopted classes may only skip Godot virtual callbacks (override-registration design)."""
    skips = tree.skips.get(class_name)
    if skips is None:
        print(f"[wrapper_generator] FAIL adopted class {class_name} is not in the generated tree", file=sys.stderr)
        return 1
    if skips and not allow_virtual_skips:
        print(f"[wrapper_generator] FAIL adopted source {class_name} has skipped methods", file=sys.stderr)
        print("\n".join(skips), file=sys.stderr)
        return 1
    if allow_virtual_skips:
        bad_skips = [line for line in skips if VIRTUAL_SKIP_REASON not in line]
        if bad_skips:
            print(f"[wrapper_generator] FAIL adopted source {class_name} has non-virtual skipped methods", file=sys.stderr)
            print("\n".join(bad_skips), file=sys.stderr)
            return 1
    return 0


def _rel(path: Path) -> str:
    return str(path.relative_to(ROOT))


IOS_HELPER_NAME = re.compile(r"fun ObjectCalls\.(\w+)\(")


def _ios_helper_names(source: str) -> set[str]:
    return set(IOS_HELPER_NAME.findall(source))


def check_single_tree(tree: TreeResult) -> int:
    """The durable convergence gate (task 21, single-tree since task 103): every generated file the
    generator produces -- the shared tree, the per-platform generated classes, the desktop/iOS
    companions, the iOS ObjectCallsGenerated helpers and the gap index -- must equal a fresh regen
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
        if rel == _rel(IOS_OBJECTCALLS_GENERATED):
            # ktfmt reformats this file after every regen (it is outside the api/ exclusion), so
            # compare the helper set, not the bytes: a helper added or dropped is drift, layout is not.
            same = _ios_helper_names(committed) == _ios_helper_names(content)
        elif rel.endswith(".md"):
            same = committed == content
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


def main() -> int:
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
    for class_name in ADOPTED_CLASSES + ADOPTED_RESOURCE_DOWNCAST_CLASSES + ADOPTED_SHELL_ONLY_CLASSES:
        if check_adopted_skips(tree, class_name) != 0:
            return 1
    for class_name in ADOPTED_CLASSES_WITH_HELPERS_AND_VIRTUAL_SKIPS + ADOPTED_CLASSES_WITH_VIRTUAL_SKIPS:
        if check_adopted_skips(tree, class_name, allow_virtual_skips=True) != 0:
            return 1
    if check_single_tree(tree) != 0:
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
