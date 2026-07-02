#!/usr/bin/env python3
"""Check conservative wrapper generator output against committed fixtures."""

from __future__ import annotations

import re
import subprocess
import sys
import tempfile
from pathlib import Path


ROOT = Path(__file__).resolve().parents[1]
FIXTURE_DIR = ROOT / "scripts/fixtures/wrapper_generator"
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
GENERATED_KDOC_BLOCK = re.compile(
    r"\n?[ \t]*/\*\*.*?Generated from Godot docs: .*?\n[ \t]*\*/\n",
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


def check_adopted_source(output_dir: Path, class_name: str, allow_virtual_skips: bool = False) -> int:
    skip_report = output_dir / f"{class_name}.adopted.skips.txt"
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

    expected = (output_dir / f"{class_name}.kt").read_text(encoding="utf-8")
    actual_path = ROOT / "src/main/kotlin/net/multigesture/kanama/api" / f"{class_name}.kt"
    actual = actual_path.read_text(encoding="utf-8")
    if comparable_source(actual) != comparable_source(expected):
        print(f"[wrapper_generator] FAIL adopted source {class_name}.kt is stale", file=sys.stderr)
        print(
            f"[wrapper_generator] run generate_api_wrapper.py --emit-class {class_name} --allow-overwrite",
            file=sys.stderr,
        )
        return 1

    actual_skips = skip_report.read_text(encoding="utf-8")
    if actual_skips and not allow_virtual_skips:
        print(f"[wrapper_generator] FAIL adopted source {class_name} has skipped methods", file=sys.stderr)
        print(actual_skips, file=sys.stderr)
        return 1
    if allow_virtual_skips:
        bad_skips = [line for line in actual_skips.splitlines() if VIRTUAL_SKIP_REASON not in line]
        if bad_skips:
            print(f"[wrapper_generator] FAIL adopted source {class_name} has non-virtual skipped methods", file=sys.stderr)
            print("\n".join(bad_skips), file=sys.stderr)
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
        for class_name in ADOPTED_CLASSES + ADOPTED_RESOURCE_DOWNCAST_CLASSES + ADOPTED_SHELL_ONLY_CLASSES:
            if check_adopted_source(output_dir, class_name) != 0:
                return 1
        for class_name in ADOPTED_CLASSES_WITH_HELPERS_AND_VIRTUAL_SKIPS:
            if check_adopted_source(output_dir, class_name, allow_virtual_skips=True) != 0:
                return 1
        for class_name in ADOPTED_CLASSES_WITH_VIRTUAL_SKIPS:
            if check_adopted_source(output_dir, class_name, allow_virtual_skips=True) != 0:
                return 1
        if check_ios_fixture(output_dir) != 0:
            return 1
        if check_ios_policies(output_dir) != 0:
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
