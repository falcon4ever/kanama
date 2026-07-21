package net.multigesture.kanama.example

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.AnimatedSprite2D
import net.multigesture.kanama.api.AnimatedSprite3D
import net.multigesture.kanama.api.AnimationMixer
import net.multigesture.kanama.api.AnimationNode
import net.multigesture.kanama.api.AnimationNodeExtension
import net.multigesture.kanama.api.AnimationPlayer
import net.multigesture.kanama.api.ArrayMesh
import net.multigesture.kanama.api.AudioStream
import net.multigesture.kanama.api.AudioStreamPlayback
import net.multigesture.kanama.api.BaseMaterial3D
import net.multigesture.kanama.api.Camera3D
import net.multigesture.kanama.api.Control
import net.multigesture.kanama.api.EditorDebuggerPlugin
import net.multigesture.kanama.api.EditorExportPlatformExtension
import net.multigesture.kanama.api.EditorExportPlugin
import net.multigesture.kanama.api.EditorImportPlugin
import net.multigesture.kanama.api.EditorInspectorPlugin
import net.multigesture.kanama.api.EditorNode3DGizmo
import net.multigesture.kanama.api.EditorNode3DGizmoPlugin
import net.multigesture.kanama.api.EditorResourcePreviewGenerator
import net.multigesture.kanama.api.EditorResourceTooltipPlugin
import net.multigesture.kanama.api.EditorSceneFormatImporter
import net.multigesture.kanama.api.EditorScenePostImport
import net.multigesture.kanama.api.EditorScenePostImportPlugin
import net.multigesture.kanama.api.EditorVCSInterface
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.GridMap
import net.multigesture.kanama.api.ImageFormatLoaderExtension
import net.multigesture.kanama.api.InputEventKey
import net.multigesture.kanama.api.InputEventMouseButton
import net.multigesture.kanama.api.InputEventMouseMotion
import net.multigesture.kanama.api.Light3D
import net.multigesture.kanama.api.Material
import net.multigesture.kanama.api.Mesh
import net.multigesture.kanama.api.MeshDataTool
import net.multigesture.kanama.api.MeshLibrary
import net.multigesture.kanama.api.MovieWriter
import net.multigesture.kanama.api.Node3D
import net.multigesture.kanama.api.OpenXRExtensionWrapper
import net.multigesture.kanama.api.OpenXRSpatialCapabilityConfigurationBaseHeader
import net.multigesture.kanama.api.OpenXRSpatialComponentData
import net.multigesture.kanama.api.OpenXRStructureBase
import net.multigesture.kanama.api.PhysicsBody3D
import net.multigesture.kanama.api.PhysicsDirectSpaceState2DExtension
import net.multigesture.kanama.api.PhysicsDirectSpaceState3DExtension
import net.multigesture.kanama.api.PhysicsServer2DExtension
import net.multigesture.kanama.api.PhysicsServer3DExtension
import net.multigesture.kanama.api.PhysicsServer3DRenderingServerHandler
import net.multigesture.kanama.api.Resource
import net.multigesture.kanama.api.SkeletonModification2D
import net.multigesture.kanama.api.StandardMaterial3D
import net.multigesture.kanama.api.StyleBox
import net.multigesture.kanama.api.SurfaceTool
import net.multigesture.kanama.api.SyntaxHighlighter
import net.multigesture.kanama.api.TextureRect
import net.multigesture.kanama.api.Viewport
import net.multigesture.kanama.api.VisualShaderNodeCustom
import net.multigesture.kanama.api.XRInterfaceExtension
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

@Suppress("unused")
@ScriptClass(attachTo = "Node")
class WrapperConvenienceProbe(val godotObject: MemorySegment) {
  fun node3dConveniences(node: Node3D) {
    node.lookAt(Vector3.ZERO)
    node.lookAtFromPosition(Vector3.ZERO, Vector3.UP)
  }

  fun inputEventConveniences(value: GodotObject) {
    val key: InputEventKey = InputEventKey.create()
    val maybeKey: InputEventKey? = InputEventKey.from(value)
    val maybeButton: InputEventMouseButton? = InputEventMouseButton.from(value)
    val maybeMotion: InputEventMouseMotion? = InputEventMouseMotion.from(value)
    val keyConstants: List<Long> = listOf(InputEventKey.KEY_W, InputEventKey.KEY_F10)
    val mouseConstants: List<Long> =
      listOf(InputEventMouseButton.MOUSE_BUTTON_LEFT, InputEventMouseButton.MOUSE_BUTTON_WHEEL_DOWN)

    key.setKeycode(InputEventKey.KEY_W)
    key.setPressed(true)
  }

  fun resourceFactoryConveniences(resource: Resource, mesh: Mesh) {
    val arrayMesh: ArrayMesh? = ArrayMesh.fromResource(resource)
    val meshLibrary: MeshLibrary = MeshLibrary.create()
    val meshDataTool: MeshDataTool = MeshDataTool.create()
    val surfaceTool: SurfaceTool = SurfaceTool.create()

    surfaceTool.begin(Mesh.PRIMITIVE_TRIANGLES)
    surfaceTool.createFrom(mesh, 0)
    surfaceTool.commit(arrayMesh)
  }

  fun animationConveniences(
    animationPlayer: AnimationPlayer,
    animatedSprite2D: AnimatedSprite2D,
    animatedSprite3D: AnimatedSprite3D,
    animationMixer: AnimationMixer,
  ) {
    animationPlayer.play()
    animatedSprite2D.play()
    animatedSprite3D.play()
    animationMixer.setParameter("parameters/active", true)
    animationMixer.getParameter("parameters/active")
    animationMixer.getStateMachinePlayback("parameters/playback")
  }

  fun promotedBaseWrapperConveniences(
    styleBox: StyleBox,
    audioStream: AudioStream,
    playback: AudioStreamPlayback,
    syntaxHighlighter: SyntaxHighlighter,
    animationNode: AnimationNode,
  ) {
    styleBox.getMinimumSize()
    styleBox.setContentMargin(0L, 2.0)
    styleBox.getContentMargin(0L)
    styleBox.getMargin(0L)
    styleBox.getOffset()
    styleBox.testMask(Vector2.ZERO, Rect2.ZERO)
    audioStream.getLength()
    audioStream.isMonophonic()
    audioStream.instantiatePlayback()
    audioStream.canBeSampled()
    audioStream.isMetaStream()
    playback.start()
    playback.seek()
    playback.getLoopCount()
    playback.getPlaybackPosition()
    playback.isPlaying()
    syntaxHighlighter.updateCache()
    syntaxHighlighter.clearHighlightingCache()
    syntaxHighlighter.getLineSyntaxHighlighting(0)
    syntaxHighlighter.getTextEdit()
    animationNode.setFilterEnabled(animationNode.isFilterEnabled())
    animationNode.setFilterPath(NodePath("root"), true)
    animationNode.isPathFiltered(NodePath("root"))
    animationNode.setParameter("speed", 1.0)
    animationNode.getParameter("speed")
  }

  fun promotedOpenXRConveniences(
    structure: OpenXRStructureBase,
    extensionWrapper: OpenXRExtensionWrapper,
    componentData: OpenXRSpatialComponentData,
    capability: OpenXRSpatialCapabilityConfigurationBaseHeader,
    movieWriter: MovieWriter,
  ) {
    structure.getStructureType()
    structure.next = structure.next
    extensionWrapper.getOpenxrApi()
    extensionWrapper.registerExtensionWrapper()
    componentData.setCapacity(0L)
    capability.hasValidConfiguration()
    MovieWriter.addWriter(movieWriter)
  }

  fun promotedPhysicsServerHandlerConveniences(handler: PhysicsServer3DRenderingServerHandler) {
    handler.setVertex(0, Vector3.ZERO)
    handler.setNormal(0, Vector3.UP)
    handler.setAabb(AABB.ZERO)
  }

  fun promotedSkeletonModificationConveniences(modification: SkeletonModification2D) {
    modification.enabled = modification.enabled
    modification.executionMode = modification.executionMode
    modification.getModificationStack()
    modification.setIsSetup(modification.getIsSetup())
    modification.clampAngle(0.0, -1.0, 1.0, false)
    modification.setEditorDrawGizmo(modification.getEditorDrawGizmo())
  }

  fun promotedEditorExtensionConveniences(
    exportPlugin: EditorExportPlugin,
    debuggerPlugin: EditorDebuggerPlugin,
    scenePostImportPlugin: EditorScenePostImportPlugin,
    sceneFormatImporter: EditorSceneFormatImporter,
    importPlugin: EditorImportPlugin,
    previewGenerator: EditorResourcePreviewGenerator,
    tooltipPlugin: EditorResourceTooltipPlugin,
    inspectorPlugin: EditorInspectorPlugin,
    gizmo: EditorNode3DGizmo,
    gizmoPlugin: EditorNode3DGizmoPlugin,
    control: Control,
    textureRect: TextureRect,
    material: StandardMaterial3D,
  ) {
    exportPlugin.getOption("kanama_probe")
    exportPlugin.getExportPreset()
    exportPlugin.getExportPlatform()
    debuggerPlugin.getSession(0)
    debuggerPlugin.getSessions()
    scenePostImportPlugin.getOptionValue("kanama_probe")
    scenePostImportPlugin.addImportOption("kanama_probe", true)
    scenePostImportPlugin.addImportOptionAdvanced(
      EditorScenePostImportPlugin.INTERNAL_IMPORT_CATEGORY_NODE,
      "kanama_probe",
      true,
    )
    sceneFormatImporter.addImportOption("kanama_probe", true)
    sceneFormatImporter.addImportOptionAdvanced(
      EditorSceneFormatImporter.IMPORT_SCENE,
      "kanama_probe",
      true,
    )
    importPlugin.appendImportExternalResource("res://kanama_probe.res")
    previewGenerator.requestDrawAndWait(RID.EMPTY)
    tooltipPlugin.requestThumbnail("res://kanama_probe.res", textureRect)
    inspectorPlugin.addCustomControl(control)
    inspectorPlugin.addPropertyEditor("kanama_probe", control)
    gizmo.addLines(emptyList(), null, modulate = Color(1f, 1f, 1f))
    gizmo.addMesh(null, null, Transform3D.IDENTITY, null)
    gizmo.addCollisionSegments(emptyList())
    gizmo.addHandles(emptyList(), null, emptyList())
    gizmo.clear()
    gizmo.setHidden(false)
    gizmo.isSubgizmoSelected(0)
    gizmo.getSubgizmoSelection()
    gizmo.getNode3d()
    gizmo.getPlugin()
    gizmoPlugin.createMaterial("kanama_probe", Color(1f, 1f, 1f))
    gizmoPlugin.addMaterial("kanama_probe", material)
    gizmoPlugin.getMaterial("kanama_probe", gizmo)
  }

  fun promotedExtensionBaseConveniences(
    animationNodeExtension: AnimationNodeExtension,
    editorScenePostImport: EditorScenePostImport,
    imageFormatLoaderExtension: ImageFormatLoaderExtension,
    spaceState2D: PhysicsDirectSpaceState2DExtension,
    spaceState3D: PhysicsDirectSpaceState3DExtension,
    visualShaderNodeCustom: VisualShaderNodeCustom,
    editorVCSInterface: EditorVCSInterface,
    editorExportPlatformExtension: EditorExportPlatformExtension,
    xrInterfaceExtension: XRInterfaceExtension,
    physicsServer2DExtension: PhysicsServer2DExtension,
    physicsServer3DExtension: PhysicsServer3DExtension,
  ) {
    AnimationNodeExtension.isLooping(emptyList())
    AnimationNodeExtension.getRemainingTime(emptyList(), false)
    animationNodeExtension.getParameter("kanama_probe")
    editorScenePostImport.getSourceFile()
    imageFormatLoaderExtension.addFormatLoader()
    imageFormatLoaderExtension.removeFormatLoader()
    spaceState2D.isBodyExcludedFromQuery(RID.EMPTY)
    spaceState3D.isBodyExcludedFromQuery(RID.EMPTY)
    visualShaderNodeCustom.getOptionIndex(0)
    val line = editorVCSInterface.createDiffLine(1, 1, "content", "new")
    val hunk = editorVCSInterface.createDiffHunk(1, 1, 1, 1)
    val file = editorVCSInterface.createDiffFile("new.gd", "old.gd")
    editorVCSInterface.createCommit("message", "author", "id", 0L, 0L)
    editorVCSInterface.createStatusFile(
      "res://kanama_probe.gd",
      EditorVCSInterface.CHANGE_TYPE_MODIFIED,
      EditorVCSInterface.TREE_AREA_UNSTAGED,
    )
    editorVCSInterface.addLineDiffsIntoDiffHunk(hunk, listOf(line))
    editorVCSInterface.addDiffHunksIntoDiffFile(file, listOf(hunk))
    editorVCSInterface.popupError("kanama_probe")
    editorExportPlatformExtension.setConfigError(editorExportPlatformExtension.getConfigError())
    editorExportPlatformExtension.setConfigMissingTemplates(
      editorExportPlatformExtension.getConfigMissingTemplates()
    )
    xrInterfaceExtension.getColorTexture()
    xrInterfaceExtension.getDepthTexture()
    xrInterfaceExtension.getVelocityTexture()
    xrInterfaceExtension.addBlit(
      RID.EMPTY,
      Rect2.ZERO,
      Rect2i.ZERO,
      false,
      0L,
      false,
      Vector2.ZERO,
      0.0,
      0.0,
      1.0,
      1.0,
    )
    xrInterfaceExtension.getRenderTargetTexture(RID.EMPTY)
    physicsServer2DExtension.bodyTestMotionIsExcludingBody(RID.EMPTY)
    physicsServer2DExtension.bodyTestMotionIsExcludingObject(0L)
    physicsServer3DExtension.bodyTestMotionIsExcludingBody(RID.EMPTY)
    physicsServer3DExtension.bodyTestMotionIsExcludingObject(0L)
  }

  fun objectAndSceneConveniences(
    value: GodotObject,
    viewport: Viewport,
    material: Material,
    light: Light3D,
  ) {
    value.set("metadata/kanama_probe", true)
    value.get("metadata/kanama_probe")
    value.setIndexed("metadata/kanama_probe", true)
    value.getIndexed(NodePath("metadata/kanama_probe"))
    value.getPropertyList()
    value.getMethodList()
    value.propertyGetRevert("metadata/kanama_probe")
    value.setMeta("kanama_probe", true)
    value.getMeta("kanama_probe", false)
    value.getMetaList()
    value.addUserSignal("kanama_probe_signal")
    value.getSignalList()
    value.getSignalConnectionList(GodotObject.Signals.propertyListChanged)
    value.getIncomingConnections()
    value.isConnected(
      GodotObject.Signals.propertyListChanged,
      value,
      "notify_property_list_changed",
    )
    value.callDeferred("notify_property_list_changed")
    value.callv("has_method", listOf("to_string"))
    value.tr("Probe")
    value.trN("Probe", "Probes", 2)
    value.getTranslationDomain()
    value.setTranslationDomain(value.getTranslationDomain())
    value.notification(GodotObject.NOTIFICATION_POSTINITIALIZE.toInt())
    viewport.getCamera3D()
    BaseMaterial3D.fromMaterial(material)
    light.lightEnergy = light.lightEnergy
  }

  fun constantsAndFactories(): Camera3D {
    val invalidCellItem: Long = GridMap.INVALID_CELL_ITEM
    val bodyAxis: Long = PhysicsBody3D.BODY_AXIS_ANGULAR_X

    return Camera3D.create()
  }
}
