package net.multigesture.kanama.example

import java.lang.foreign.MemorySegment
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import net.multigesture.kanama.annotations.ExportCategory
import net.multigesture.kanama.annotations.ExportGroup
import net.multigesture.kanama.annotations.ExportSubgroup
import net.multigesture.kanama.annotations.GlobalClass
import net.multigesture.kanama.annotations.OnEnterTree
import net.multigesture.kanama.annotations.OnExitTree
import net.multigesture.kanama.annotations.OnProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.PropertyHint
import net.multigesture.kanama.annotations.RegisterFunction
import net.multigesture.kanama.annotations.Rpc
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.ScriptProperty
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.annotations.Tool
import net.multigesture.kanama.annotations.ToolButton
import net.multigesture.kanama.api.AnimationMixer
import net.multigesture.kanama.api.AnimationPlayer
import net.multigesture.kanama.api.Area3D
import net.multigesture.kanama.api.AudioStreamPlayer
import net.multigesture.kanama.api.AudioStreamPlayer3D
import net.multigesture.kanama.api.BaseMaterial3D
import net.multigesture.kanama.api.BoxMesh
import net.multigesture.kanama.api.BoxShape3D
import net.multigesture.kanama.api.Button
import net.multigesture.kanama.api.CPUParticles3D
import net.multigesture.kanama.api.Camera3D
import net.multigesture.kanama.api.CharacterBody3D
import net.multigesture.kanama.api.ClassDB
import net.multigesture.kanama.api.CollisionShape3D
import net.multigesture.kanama.api.Control
import net.multigesture.kanama.api.DirAccess
import net.multigesture.kanama.api.DisplayServer
import net.multigesture.kanama.api.Engine
import net.multigesture.kanama.api.FileAccess
import net.multigesture.kanama.api.GD
import net.multigesture.kanama.api.GPUParticles3D
import net.multigesture.kanama.api.GeometryInstance3D
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.Input
import net.multigesture.kanama.api.KanamaCoroutineOwner
import net.multigesture.kanama.api.KanamaScope
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.Label
import net.multigesture.kanama.api.LineEdit
import net.multigesture.kanama.api.MainThread
import net.multigesture.kanama.api.ManualGodotLifetimeApi
import net.multigesture.kanama.api.Mathf
import net.multigesture.kanama.api.MeshInstance3D
import net.multigesture.kanama.api.MethodName
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.OS
import net.multigesture.kanama.api.OptionButton
import net.multigesture.kanama.api.PackedScene
import net.multigesture.kanama.api.ProjectSettings
import net.multigesture.kanama.api.PropertyName
import net.multigesture.kanama.api.RayCast3D
import net.multigesture.kanama.api.RefCounted
import net.multigesture.kanama.api.Resource
import net.multigesture.kanama.api.ResourceLoader
import net.multigesture.kanama.api.ResourceSaver
import net.multigesture.kanama.api.SceneTree
import net.multigesture.kanama.api.SignalName
import net.multigesture.kanama.api.StandardMaterial3D
import net.multigesture.kanama.api.StaticBody3D
import net.multigesture.kanama.api.TabBar
import net.multigesture.kanama.api.Texture2D
import net.multigesture.kanama.api.Time
import net.multigesture.kanama.api.Timer
import net.multigesture.kanama.api.Tween
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.generated.HelloScriptNames
import net.multigesture.kanama.generated.HelloScriptSignals
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Projection
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.Vector3i
import net.multigesture.kanama.types.Vector4
import net.multigesture.kanama.types.Vector4i

enum class SmokeDifficulty {
  EASY,
  NORMAL,
  HARD,
}

@ScriptClass(attachTo = "Node")
@GlobalClass
@Tool
@OptIn(ManualGodotLifetimeApi::class)
class HelloScript(godotObject: MemorySegment) :
  KanamaScript<Node>(godotObject, ::Node), KanamaCoroutineOwner {
  override val kanamaScope = KanamaScope()

  @ExportCategory("Inspector Metadata")
  @ExportGroup("Smoke Properties")
  @ScriptProperty(hint = PropertyHint.RANGE, hintString = "0,100,1")
  var health: Long = 99

  @ExportSubgroup("Runtime") @ScriptProperty var speed: Double = 5.1

  @ScriptProperty(hint = PropertyHint.ENUM, hintString = "Easy,Normal,Hard")
  var metadataMode: String = "Normal"

  // task 32 — custom enum export: registers as INT + PROPERTY_HINT_ENUM
  // ("EASY,NORMAL,HARD"), stored as the ordinal; main.tscn overrides it to 2 (HARD).
  @ScriptProperty var smokeDifficulty = SmokeDifficulty.NORMAL

  // task 38 (issue #40) — enum-list export: registers as a typed int Array whose
  // elements carry PROPERTY_HINT_ENUM ("2/2:EASY,NORMAL,HARD"), stored as ordinals;
  // main.tscn overrides it to [HARD, EASY].
  @ScriptProperty var smokeJoints: List<SmokeDifficulty> = emptyList()

  @ScriptProperty var label: String = "hello"

  @ScriptProperty var targetPath: NodePath = NodePath("../Body3D")

  @ScriptProperty var smokeScene: PackedScene? = null

  @ScriptProperty var smokeTextures: List<Texture2D> = emptyList()

  @ScriptProperty var smokeResource: SmokeResource? = null

  @ScriptProperty var smokeResources: List<SmokeResource> = emptyList()

  // task 50 — user accessors that throw. Without containment in ScriptBridge's
  // siSet/siGet these exceptions escape an FFM upcall stub and abort the whole
  // process; with it, the engine sees a rejected write or a null read and keeps
  // running. Kept as two separate properties on purpose: the generated dispatchSet
  // reads the *old* value (for owned-resource cleanup) before assigning, so a
  // property whose getter throws also becomes unwritable — combining both throws on
  // one property wedges it permanently and tests nothing useful.
  @ScriptProperty
  var smokeThrowingSetter: Long = 0
    set(value) {
      check(value != 666L) { "kanama smoke: deliberate property-set failure" }
      field = value
    }

  // Armed via smokeArmGetterThrow() rather than a sentinel value, so the getter can be
  // disarmed without a write (a write would trip the same throwing getter — see above).
  private var smokeGetterThrowArmed: Boolean = false

  @ScriptProperty
  var smokeThrowingGetter: Long = 5
    get() {
      check(!smokeGetterThrowArmed) { "kanama smoke: deliberate property-get failure" }
      return field
    }

  @RegisterFunction
  fun smokeArmGetterThrow(armed: Boolean) {
    smokeGetterThrowArmed = armed
  }

  // MutableList export (preserves mutability through the setter).
  @ScriptProperty var smokeMutableTextures: MutableList<Texture2D> = mutableListOf()

  // issue #40 — typed Map exports (Dictionary + PROPERTY_HINT_DICTIONARY_TYPE):
  // a String-keyed scalar dictionary, the reporter's non-String-keyed custom-resource
  // dictionary (Map<Long, SmokeResource>), and an enum-valued dictionary (ordinals).
  @ScriptProperty var smokeScalarMap: Map<String, Long> = emptyMap()

  @ScriptProperty var smokeRegionMap: Map<Long, SmokeResource> = emptyMap()

  @ScriptProperty var smokeEnumMap: Map<String, SmokeDifficulty> = emptyMap()

  // issue #40 — value-type key (Vector2i) with a scalar value.
  @ScriptProperty var smokeVectorKeyMap: Map<Vector2i, Long> = emptyMap()

  // issue #40 — value-type key (Vector2i) with a custom resource value (combines
  // two features: value-type keys and custom-resource values).
  @ScriptProperty var smokeVectorResourceMap: Map<Vector2i, SmokeResource> = emptyMap()

  // issue #40 — MutableMap export (preserves mutability through the setter).
  @ScriptProperty var smokeMutableMap: MutableMap<String, Long> = mutableMapOf()

  // issue #40 review — nullable scalar value (Map<String, Long?>): mirrors C#'s nil-preserving
  // Dictionary. A null (or wrong-typed) value keeps its key with a null value rather than dropping
  // it. Non-null value maps above cannot hold null, so they drop — the type-forced difference.
  @ScriptProperty var smokeNullableScalarMap: Map<String, Long?> = emptyMap()

  @ToolButton(text = "Reset Health", icon = "Reload")
  fun resetHealth() {
    health = 99
    notifyInspectorChanged()
  }

  private var smokeSignalBodyClass: String = ""
  private var smokeScriptSignalMessage: String = ""
  private var smokeCoroutineStarted: Boolean = false

  @Signal fun smokeChecked(message: String) = Unit

  @OnReady
  fun ready() {
    if (smokeScene == null) {
      smokeScene = PackedScene.create()
    }
    GD.print("Hello from Kanama script")
    // task 51 — Resource now extends RefCounted (Godot's real Object > RefCounted > Resource
    // chain, as iOS already did), so GodotObject's surface (setMeta/getMeta/…) is reachable on
    // any Resource. The former split root hid it — T-bond's chat report. Prove setMeta round-trips
    // directly on a Resource (no asObject() hop) and that the refcount lifetime still closes clean.
    run {
      val res = Resource.create()
      res.setMeta("kanama_t51", 51L)
      val metaBack = (res.getMeta("kanama_t51") as? Number)?.toLong() ?: -1L
      val hasMeta = res.hasMeta("kanama_t51")
      res.close()
      System.err.println("[kanama:kt] task51 resource meta set_get=$metaBack has=$hasMeta")
    }
    val mathLerp = Mathf.lerp(2.0, 4.0, 0.25)
    val mathClamp = Mathf.clamp(12L, 0L, 10L)
    val mathWrap = Mathf.wrap(11L, 0L, 10L)
    val mathApprox = Mathf.isEqualApprox(Mathf.sin(Mathf.PI / 2.0), 1.0)
    val mathRound = Mathf.roundToInt(2.6)
    // Float overloads — mirror C# Mathf so user code with Float Vector
    // components doesn't need .toFloat() at the call site.
    val mathLerpF: Float = Mathf.lerp(2f, 4f, 0.25f)
    val mathClampF: Float = Mathf.clamp(12f, 0f, 10f)
    val mathSinF: Float = Mathf.sin(0f)
    val mathSqrtF: Float = Mathf.sqrt(9f)
    val generatedNameConstantsOk =
      MethodName.play == "play" &&
        PropertyName.visible == "visible" &&
        SignalName.treeExited == "tree_exited"
    ProjectSettings.setSetting("kanama/smoke/string_list", listOf("alpha", "beta"))
    ProjectSettings.setSetting(
      "kanama/smoke/dictionary",
      mapOf("name" to "kanama", "enabled" to true, "count" to 2L, "scale" to 1.5),
    )
    val listSetting = ProjectSettings.getSettingStringList("kanama/smoke/string_list")
    val dictionarySetting = ProjectSettings.getSettingDictionary("kanama/smoke/dictionary")
    val scriptExists = ResourceLoader.exists("res://HelloScript.kt", "Script")
    val rootEntries = ResourceLoader.listDirectory("res://")
    val threadedLoadPath = "res://InstancedMesh.tscn"
    val threadedRequestError =
      ResourceLoader.loadThreadedRequest(
        threadedLoadPath,
        "PackedScene",
        cacheMode = ResourceLoader.CACHE_MODE_IGNORE,
      )
    val threadedStatusBeforeGet = ResourceLoader.loadThreadedGetStatus(threadedLoadPath)
    val threadedResource = ResourceLoader.loadThreadedGet(threadedLoadPath)
    val threadedResourceIsPackedScene = threadedResource?.isClass("PackedScene") ?: false
    val threadedResourcePathLen = threadedResource?.getPath()?.length ?: 0
    val threadedStatusAfterGet = ResourceLoader.loadThreadedGetStatus(threadedLoadPath)
    threadedResource?.close()
    val generatedSceneUniqueId = Resource.generateSceneUniqueId()
    val loadedScript = ResourceLoader.load("res://HelloScript.kt", "Script")
    val defaultProbeScript = ResourceLoader.load("res://DefaultProbeScript.kt", "Script")
    val loadedScriptPath = loadedScript?.getPath().orEmpty()
    val loadedScriptName = loadedScript?.getName().orEmpty()
    val loadedScriptSceneId = loadedScript?.getSceneUniqueId().orEmpty()
    val loadedScriptPathId = loadedScript?.getIdForPath("res://HelloScript.kt").orEmpty()
    val loadedScriptBuiltIn = loadedScript?.isBuiltIn() ?: false
    val loadedScriptLocalToScene = loadedScript?.isLocalToScene() ?: false
    val duplicatedScript = loadedScript?.duplicate()
    val duplicatedScriptIsScript = duplicatedScript?.isClass("Script") ?: false
    val duplicatedScriptPathLen = duplicatedScript?.getPath()?.length ?: 0
    duplicatedScript?.close()
    val deepDuplicatedScript = loadedScript?.duplicateDeep()
    val deepDuplicatedScriptIsScript = deepDuplicatedScript?.isClass("Script") ?: false
    deepDuplicatedScript?.close()
    val loadedScriptIsScript = loadedScript?.isClass("Script") ?: false
    val loadedScriptRefCount = loadedScript?.getReferenceCount() ?: 0L
    val saveExtensions =
      loadedScript?.let { ResourceSaver.getRecognizedExtensions(it) } ?: emptyList()
    val savePath = "res://.kanama_resource_saver_smoke.kt"
    val saveAbsolutePath = ProjectSettings.globalizePath(savePath)
    DirAccess.removeAbsolute(saveAbsolutePath)
    val saveError = loadedScript?.let { ResourceSaver.save(it, savePath) } ?: -1L
    val saveExists = FileAccess.fileExists(savePath)
    val saveHasClass =
      saveExists && FileAccess.getFileAsString(savePath).contains("class HelloScript")
    val saveUidSetError = if (saveExists) ResourceSaver.setUid(savePath, 8675309L) else -1L
    val saveCleanupError = if (saveExists) DirAccess.removeAbsolute(saveAbsolutePath) else 0L
    DirAccess.removeAbsolute("$saveAbsolutePath.uid")
    val cachedScript = ResourceLoader.getCachedRef("res://HelloScript.kt")
    val cachedScriptPath = cachedScript?.getPath().orEmpty()
    val cachedScriptIsScript = cachedScript?.isClass("Script") ?: false
    val cachedScriptRefCount = cachedScript?.getReferenceCount() ?: 0L
    cachedScript?.close()
    loadedScript?.close()
    val pendingSetProbe = Node(ObjectCalls.constructObject("Node"))
    pendingSetProbe.setScript(defaultProbeScript)
    pendingSetProbe.set("amount", 777L)
    self.addChild(pendingSetProbe)
    val pendingSetAmount = pendingSetProbe.get("amount") as? Long ?: -1L
    pendingSetProbe.queueFree()
    defaultProbeScript?.close()
    // task 52a / issue #60 — Node.setOwner accepts null through the TYPED wrapper (not
    // call("set_owner", null)), and null clears the owner. Also exercises the writable
    // `owner: Node?` property. owner must be an ancestor, so parent owns child.
    run {
      val ownerParent = Node(ObjectCalls.constructObject("Node"))
      val ownerChild = Node(ObjectCalls.constructObject("Node"))
      self.addChild(ownerParent)
      ownerParent.addChild(ownerChild)
      ownerChild.setOwner(ownerParent)
      val ownerSet = ownerChild.getOwner() != null
      ownerChild.setOwner(null)
      val ownerCleared = ownerChild.getOwner() == null
      ownerChild.owner = ownerParent
      val propSet = ownerChild.owner != null
      ownerChild.owner = null
      val propCleared = ownerChild.owner == null
      ownerParent.queueFree()
      System.err.println(
        "[kanama:kt] task52a setOwner set=$ownerSet cleared=$ownerCleared " +
          "prop_set=$propSet prop_cleared=$propCleared"
      )
    }
    val scriptUid = ResourceSaver.getResourceIdForPath("res://HelloScript.kt")
    val fileExists = FileAccess.fileExists("res://HelloScript.kt")
    val fileSize = FileAccess.getSize("res://HelloScript.kt")
    val fileModified = FileAccess.getModifiedTime("res://HelloScript.kt")
    val fileAccessed = FileAccess.getAccessTime("res://HelloScript.kt")
    val fileMd5 = FileAccess.getMd5("res://HelloScript.kt")
    val fileSha256 = FileAccess.getSha256("res://HelloScript.kt")
    val filePermissions = FileAccess.getUnixPermissions("res://HelloScript.kt")
    val fileHidden = FileAccess.getHiddenAttribute("res://HelloScript.kt")
    val fileReadOnly = FileAccess.getReadOnlyAttribute("res://HelloScript.kt")
    val fileExtendedAttrs = FileAccess.getExtendedAttributesList("res://HelloScript.kt")
    val sourceHasClass =
      FileAccess.getFileAsString("res://HelloScript.kt").contains("class HelloScript")
    val fileOpenPath = FileAccess.getPathFor("res://HelloScript.kt")
    val fileOpenAbsolutePath = FileAccess.getPathAbsoluteFor("res://HelloScript.kt")
    val fileOpenIsOpen = FileAccess.isOpenFor("res://HelloScript.kt")
    val fileOpenPosition = FileAccess.getPositionFor("res://HelloScript.kt")
    val fileOpenLength = FileAccess.getLengthFor("res://HelloScript.kt")
    val fileOpenEof = FileAccess.eofReachedFor("res://HelloScript.kt")
    val fileOpenLine = FileAccess.getLineFor("res://HelloScript.kt")
    val fileOpenText = FileAccess.getAsText("res://HelloScript.kt")
    val fileOpenError = FileAccess.getErrorFor("res://HelloScript.kt")
    val directFile = FileAccess.open("res://HelloScript.kt", FileAccess.READ)
    val directFilePresent = directFile != null
    val directFileOpen = directFile?.isOpen() ?: false
    val directFilePathLen = directFile?.getPath()?.length ?: 0
    val directFileLineLen = directFile?.getLine()?.length ?: 0
    val directFileTextHasClass = directFile?.getAsText()?.contains("class HelloScript") ?: false
    directFile?.close()
    val tempFile =
      FileAccess.createTemp(FileAccess.WRITE, "kanama_file_handle_", "tmp", keep = false)
    val tempFilePresent = tempFile != null
    val tempFileOpen = tempFile?.isOpen() ?: false
    val tempFilePathLen = tempFile?.getPath()?.length ?: 0
    val tempFileStoreOk = tempFile?.storeString("temp-handle") ?: false
    tempFile?.flush()
    tempFile?.close()
    val fileByte = FileAccess.get8At("res://HelloScript.kt")
    val fileWord = FileAccess.get16At("res://HelloScript.kt")
    val fileDword = FileAccess.get32At("res://HelloScript.kt")
    val fileQword = FileAccess.get64At("res://HelloScript.kt")
    val fileFloatTextLength = FileAccess.getFloatAt("res://HelloScript.kt").toString().length
    val fileDoubleTextLength = FileAccess.getDoubleAt("res://HelloScript.kt").toString().length
    val fileHalfTextLength = FileAccess.getHalfAt("res://HelloScript.kt").toString().length
    val fileRealTextLength = FileAccess.getRealAt("res://HelloScript.kt").toString().length
    val fileBigEndian = FileAccess.isBigEndianFor("res://HelloScript.kt")
    val fileCsvColumns = FileAccess.getCsvLineAt("res://HelloScript.kt").size
    val fileWritePath = "res://.kanama_file_write_smoke.txt"
    val fileWriteAbsolutePath = ProjectSettings.globalizePath(fileWritePath)
    DirAccess.removeAbsolute(fileWriteAbsolutePath)
    val fileWriteStringOk = FileAccess.writeString(fileWritePath, "alpha")
    val fileWriteStringText = FileAccess.getFileAsString(fileWritePath)
    val fileWriteLineOk = FileAccess.writeLine(fileWritePath, "beta")
    val fileWriteLineText = FileAccess.getFileAsString(fileWritePath)
    val fileResizeError = FileAccess.resizeFile(fileWritePath, 2)
    val fileResizeText = FileAccess.getFileAsString(fileWritePath)
    val fileWriteCleanupError = DirAccess.removeAbsolute(fileWriteAbsolutePath)
    val fileSourceBytes = FileAccess.getFileAsBytes("res://HelloScript.kt")
    val fileBuffer = FileAccess.getBufferAt("res://HelloScript.kt", 8)
    val fileBytesPath = "res://.kanama_file_bytes_smoke.bin"
    val fileBytesAbsolutePath = ProjectSettings.globalizePath(fileBytesPath)
    DirAccess.removeAbsolute(fileBytesAbsolutePath)
    val fileWriteBytesOk = FileAccess.writeBytes(fileBytesPath, byteArrayOf(75, 84))
    val fileWrittenBytes = FileAccess.getFileAsBytes(fileBytesPath)
    val fileBytesCleanupError = DirAccess.removeAbsolute(fileBytesAbsolutePath)
    val fileNumericPath = "res://.kanama_file_numeric_smoke.bin"
    val fileNumericAbsolutePath = ProjectSettings.globalizePath(fileNumericPath)
    DirAccess.removeAbsolute(fileNumericAbsolutePath)
    val fileWrite8Ok = FileAccess.write8(fileNumericPath, 0x7f)
    val fileRead8 = FileAccess.get8At(fileNumericPath)
    val fileWrite16Ok = FileAccess.write16(fileNumericPath, 0x1234, bigEndian = true)
    val fileRead16Bytes = FileAccess.getBufferAt(fileNumericPath, 2).map { it.toInt() and 0xff }
    val fileWrite32Ok = FileAccess.write32(fileNumericPath, 0x01020304)
    val fileRead32 = FileAccess.get32At(fileNumericPath)
    val fileWrite64Ok = FileAccess.write64(fileNumericPath, 0x0102030405060708L)
    val fileRead64 = FileAccess.get64At(fileNumericPath)
    val fileWriteDoubleOk = FileAccess.writeDouble(fileNumericPath, 12.5)
    val fileReadDouble = FileAccess.getDoubleAt(fileNumericPath)
    val fileWriteFloatOk = FileAccess.writeFloat(fileNumericPath, 3.5)
    val fileReadFloat = FileAccess.getFloatAt(fileNumericPath)
    val fileWriteHalfOk = FileAccess.writeHalf(fileNumericPath, 1.5)
    val fileReadHalf = FileAccess.getHalfAt(fileNumericPath)
    val fileWriteRealOk = FileAccess.writeReal(fileNumericPath, 2.25)
    val fileReadReal = FileAccess.getRealAt(fileNumericPath)
    val fileNumericCleanupError = DirAccess.removeAbsolute(fileNumericAbsolutePath)
    val filePascalPath = "res://.kanama_file_pascal_smoke.bin"
    val filePascalAbsolutePath = ProjectSettings.globalizePath(filePascalPath)
    DirAccess.removeAbsolute(filePascalAbsolutePath)
    val filePascalWriteOk = FileAccess.writePascalString(filePascalPath, "kanama")
    val filePascalRead = FileAccess.getPascalStringFor(filePascalPath)
    val filePascalCleanupError = DirAccess.removeAbsolute(filePascalAbsolutePath)
    val fileCsvPath = "res://.kanama_file_csv_smoke.csv"
    val fileCsvAbsolutePath = ProjectSettings.globalizePath(fileCsvPath)
    DirAccess.removeAbsolute(fileCsvAbsolutePath)
    val fileCsvWriteOk = FileAccess.writeCsvLine(fileCsvPath, listOf("alpha", "beta"))
    val fileCsvRead = FileAccess.getCsvLineAt(fileCsvPath)
    val fileCsvEndPosition = FileAccess.getPositionFromEnd(fileCsvPath)
    val fileCsvSize = FileAccess.getSize(fileCsvPath)
    val fileCsvCleanupError = DirAccess.removeAbsolute(fileCsvAbsolutePath)
    val fileVarPath = "res://.kanama_file_var_smoke.bin"
    val fileVarAbsolutePath = ProjectSettings.globalizePath(fileVarPath)
    DirAccess.removeAbsolute(fileVarAbsolutePath)
    val fileVarWriteOk = FileAccess.writeVar(fileVarPath, "variant-smoke")
    val fileVarRead = FileAccess.getVarAt(fileVarPath) as? String
    val fileVarCleanupError = DirAccess.removeAbsolute(fileVarAbsolutePath)
    val fileAttrPath = "res://.kanama_file_attr_smoke.txt"
    val fileAttrAbsolutePath = ProjectSettings.globalizePath(fileAttrPath)
    val fileAttrStringName = "user.kanama.smoke"
    val fileAttrBytesName = "user.kanama.bytes"
    DirAccess.removeAbsolute(fileAttrAbsolutePath)
    FileAccess.writeString(fileAttrPath, "attrs")
    val fileAttrStringSetError =
      FileAccess.setExtendedAttributeString(fileAttrPath, fileAttrStringName, "value")
    val fileAttrStringRead = FileAccess.getExtendedAttributeString(fileAttrPath, fileAttrStringName)
    val fileAttrListAfterString = FileAccess.getExtendedAttributesList(fileAttrPath)
    val fileAttrBytesSetError =
      FileAccess.setExtendedAttribute(fileAttrPath, fileAttrBytesName, byteArrayOf(1, 2, 3))
    val fileAttrBytesRead = FileAccess.getExtendedAttribute(fileAttrPath, fileAttrBytesName)
    val fileAttrStringRemoveError =
      FileAccess.removeExtendedAttribute(fileAttrPath, fileAttrStringName)
    val fileAttrBytesRemoveError =
      FileAccess.removeExtendedAttribute(fileAttrPath, fileAttrBytesName)
    val fileAttrHiddenSetError = FileAccess.setHiddenAttribute(fileAttrPath, true)
    val fileAttrHidden = FileAccess.getHiddenAttribute(fileAttrPath)
    val fileAttrHiddenResetError = FileAccess.setHiddenAttribute(fileAttrPath, false)
    val fileAttrReadOnlySetError = FileAccess.setReadOnlyAttribute(fileAttrPath, true)
    val fileAttrReadOnly = FileAccess.getReadOnlyAttribute(fileAttrPath)
    val fileAttrReadOnlyResetError = FileAccess.setReadOnlyAttribute(fileAttrPath, false)
    val fileAttrPermissionsSetError = FileAccess.setUnixPermissions(fileAttrPath, 420)
    val fileAttrPermissions = FileAccess.getUnixPermissions(fileAttrPath)
    val fileAttrCleanupError = DirAccess.removeAbsolute(fileAttrAbsolutePath)
    val selfNode = self
    val selfAsViaHelper = selfAs(::Node)
    val selfAsHelperMatches = selfAsViaHelper.getInstanceId() == Node(godotObject).getInstanceId()
    val autoload =
      selfNode.getNodeOrNull("/root/KanamaSmokeAutoload")?.let { GodotObject(it.handle) }
    val selfClass = selfNode.getClassName()
    val selfIsNode = selfNode.isClass("Node")
    val selfInstanceId = selfNode.getInstanceId()
    val selfQueued = selfNode.isQueuedForDeletion()
    val selfInsideTree = selfNode.isInsideTree()
    val selfPartOfEditedScene = selfNode.isPartOfEditedScene()
    val selfChildCount = selfNode.getChildCount()
    val selfHasDotNode = selfNode.hasNode(NodePath("."))
    val selfDotNode = selfNode.getNodeOrNull(".")
    val selfMissingNode = selfNode.getNodeOrNull("__kanama_missing_node__")
    val selfParent = selfNode.getParent()
    val selfOwner = selfNode.getOwner()
    val selfDotMatches = selfDotNode?.getInstanceId() == selfInstanceId
    val selfParentIsNode = selfParent?.isClass("Node") ?: false
    val selfOwnerClassLen = selfOwner?.getClassName()?.length ?: 0
    val expectedTargetPath = "../SceneTarget3D"
    val targetPathFromTscn = targetPath.path == expectedTargetPath
    val body3d = selfNode.getAsOrNull(targetPath, ::CharacterBody3D)
    val bodyFound = selfNode.hasNode(targetPath) && body3d != null
    val packedScene = PackedScene.create()
    val packedScenePackError = body3d?.let { packedScene.pack(it) } ?: -1L
    val packedSceneCanInstantiate = packedScene.canInstantiate()
    val packedSceneInstance = packedScene.instantiate()
    val packedSceneInstanceIsBody = packedSceneInstance?.isClass("CharacterBody3D") ?: false
    val packedSceneInstanceChildren = packedSceneInstance?.getChildCount() ?: -1L
    if (packedSceneInstance != null) {
      selfNode.addChild(packedSceneInstance)
      packedSceneInstance.queueFree()
    }
    packedScene.close()
    if (body3d != null) body3d.position = Vector3(1f, 2f, 3f)
    val bodyPosition = body3d?.position ?: Vector3.ZERO
    body3d?.translate(Vector3(0.5f, 0f, -0.5f))
    val bodyTranslated = body3d?.position ?: Vector3.ZERO
    if (body3d != null) body3d.globalPosition = Vector3(2f, 3f, 4f)
    val bodyGlobalPosition = body3d?.globalPosition ?: Vector3.ZERO
    if (body3d != null) body3d.rotationDegrees = Vector3(0f, 45f, 0f)
    val bodyRotationDegrees = body3d?.rotationDegrees ?: Vector3.ZERO
    if (body3d != null) body3d.scale = Vector3.ONE
    val bodyScale = body3d?.scale ?: Vector3.ZERO
    body3d?.setCollisionLayer(3)
    body3d?.setCollisionMask(5)
    body3d?.setRayPickable(true)
    body3d?.setCollisionPriority(2.5)
    val bodyCollisionLayer = body3d?.getCollisionLayer() ?: 0L
    val bodyCollisionMask = body3d?.getCollisionMask() ?: 0L
    val bodyRayPickable = body3d?.isRayPickable() ?: false
    val bodyCollisionPriority = body3d?.getCollisionPriority() ?: 0.0
    if (body3d != null) body3d.velocity = Vector3(4f, 5f, 6f)
    val bodyVelocity = body3d?.velocity ?: Vector3.ZERO
    val bodyMoveAndSlide = body3d?.moveAndSlide() ?: false
    val bodyRealVelocity = body3d?.getRealVelocity() ?: Vector3.ZERO
    val bodyPositionDelta = body3d?.getPositionDelta() ?: Vector3.ZERO
    val bodyUpDirection = body3d?.getUpDirection() ?: Vector3.ZERO
    val bodyOnFloor = body3d?.isOnFloor() ?: false
    val bodyOnWall = body3d?.isOnWall() ?: false
    val bodyOnCeiling = body3d?.isOnCeiling() ?: false
    val collisionShapeNode = selfNode.getNodeOrNull("../Body3D/CollisionShape3D")
    val collisionShapeFound =
      selfNode.hasNode("../Body3D/CollisionShape3D") &&
        collisionShapeNode?.isClass("CollisionShape3D") == true
    val collisionShape3d = collisionShapeNode?.let { CollisionShape3D(it.handle) }
    val assignedBoxShape = BoxShape3D.create()
    assignedBoxShape.setSize(Vector3(2f, 3f, 4f))
    assignedBoxShape.setMargin(0.08)
    assignedBoxShape.setCustomSolverBias(0.2)
    collisionShape3d?.setShape(assignedBoxShape)
    val assignedBoxSize = assignedBoxShape.getSize()
    val assignedShape = collisionShape3d?.getShape()
    val assignedShapeIsBox = assignedShape?.isClass("BoxShape3D") ?: false
    val assignedShapeMargin = assignedShape?.getMargin() ?: 0.0
    val assignedShapeBias = assignedShape?.getCustomSolverBias() ?: 0.0
    assignedShape?.close()
    assignedBoxShape.close()
    collisionShape3d?.setDisabled(true)
    collisionShape3d?.setDebugColor(Color(0.25f, 0.5f, 0.75f, 1.0f))
    collisionShape3d?.setEnableDebugFill(false)
    val collisionShapeDisabled = collisionShape3d?.isDisabled() ?: false
    val collisionShapeDebugColor = collisionShape3d?.getDebugColor() ?: Color(0f, 0f, 0f, 0f)
    val collisionShapeDebugFill = collisionShape3d?.getEnableDebugFill() ?: true
    collisionShape3d?.setDisabled(false)
    val collisionShapeEnabled = collisionShape3d?.isDisabled() == false
    body3d?.setVisible(false)
    val bodyHidden = body3d?.isVisible() == false
    body3d?.setVisible(true)
    val bodyVisible = body3d?.isVisible() == true
    val camera3d = selfNode.getNodeAsOrNull("../Camera3D", "Camera3D", ::Camera3D)
    val cameraFound = selfNode.hasNode("../Camera3D") && camera3d != null
    camera3d?.setFov(70.0)
    camera3d?.setNear(0.1)
    camera3d?.setFar(250.0)
    camera3d?.setProjection(Camera3D.PROJECTION_PERSPECTIVE)
    camera3d?.setCullMask(1)
    camera3d?.setCurrent(true)
    val cameraFov = camera3d?.getFov() ?: 0.0
    val cameraNear = camera3d?.getNear() ?: 0.0
    val cameraFar = camera3d?.getFar() ?: 0.0
    val cameraProjection = camera3d?.getProjection() ?: -1L
    val cameraCullMask = camera3d?.getCullMask() ?: 0L
    val cameraCurrent = camera3d?.isCurrent() ?: false
    val ray3d = selfNode.getNodeAsOrNull("../RayCast3D", "RayCast3D", ::RayCast3D)
    val rayFound = selfNode.hasNode("../RayCast3D") && ray3d != null
    ray3d?.setEnabled(true)
    ray3d?.setTargetPosition(Vector3(0f, -2f, 0f))
    ray3d?.setCollisionMask(1)
    ray3d?.setCollideWithBodies(true)
    ray3d?.setCollideWithAreas(false)
    ray3d?.forceRaycastUpdate()
    val rayEnabled = ray3d?.isEnabled() ?: false
    val rayTarget = ray3d?.getTargetPosition() ?: Vector3.ZERO
    val rayMask = ray3d?.getCollisionMask() ?: 0L
    val rayBodies = ray3d?.isCollideWithBodiesEnabled() ?: false
    val rayAreas = ray3d?.isCollideWithAreasEnabled() ?: true
    val rayColliding = ray3d?.isColliding() ?: false
    val rayPoint = ray3d?.getCollisionPoint() ?: Vector3.ZERO
    val rayNormal = ray3d?.getCollisionNormal() ?: Vector3.ZERO
    val area3d = selfNode.getNodeAsOrNull("../Area3D", "Area3D", ::Area3D)
    val areaFound = selfNode.hasNode("../Area3D") && area3d != null
    area3d?.setMonitoring(true)
    area3d?.setMonitorable(true)
    area3d?.setGravity(12.0)
    area3d?.setPriority(7)
    val areaMonitoring = area3d?.isMonitoring() ?: false
    val areaMonitorable = area3d?.isMonitorable() ?: false
    val areaGravity = area3d?.getGravity() ?: 0.0
    val areaGravityDirection = area3d?.getGravityDirection() ?: Vector3.ZERO
    val areaPriority = area3d?.getPriority() ?: 0L
    val areaHasBodies = area3d?.hasOverlappingBodies() ?: false
    val areaHasAreas = area3d?.hasOverlappingAreas() ?: false
    val areaBodyCount = area3d?.getOverlappingBodies()?.size ?: -1
    val areaAreaCount = area3d?.getOverlappingAreas()?.size ?: -1
    area3d?.setDeferred("monitoring", false)
    val areaDeferredSet = area3d != null
    val staticBody = selfNode.getNodeAsOrNull("../StaticBody3D", "StaticBody3D", ::StaticBody3D)
    val staticBodyFound = staticBody != null
    staticBody?.setConstantLinearVelocity(Vector3(1f, 2f, 3f))
    staticBody?.setConstantAngularVelocity(Vector3(4f, 5f, 6f))
    val staticLinear = staticBody?.getConstantLinearVelocity() ?: Vector3.ZERO
    val staticAngular = staticBody?.getConstantAngularVelocity() ?: Vector3.ZERO
    val audio3d =
      selfNode.getNodeAsOrNull("../Audio3D", "AudioStreamPlayer3D", ::AudioStreamPlayer3D)
    val audioFound = selfNode.hasNode("../Audio3D") && audio3d != null
    audio3d?.setVolumeDb(-6.0)
    audio3d?.setPitchScale(1.25)
    audio3d?.setMaxDistance(42.0)
    audio3d?.setStream(null)
    val audio3dStreamNull = audio3d?.getStream() == null
    audio3d?.setStreamPaused(true)
    val audioPaused = audio3d?.getStreamPaused() ?: false
    audio3d?.setStreamPaused(false)
    val audioVolume = audio3d?.getVolumeDb() ?: 0.0
    val audioPitch = audio3d?.getPitchScale() ?: 0.0
    val audioMaxDistance = audio3d?.getMaxDistance() ?: 0.0
    val audioPlayingBefore = audio3d?.isPlaying() ?: false
    audio3d?.stop()
    val audioPlayingAfterStop = audio3d?.isPlaying() ?: false
    val audio2d = selfNode.getNodeAsOrNull("../Audio2D", "AudioStreamPlayer", ::AudioStreamPlayer)
    val audio2dFound = selfNode.hasNode("../Audio2D") && audio2d != null
    audio2d?.setVolumeDb(-4.0)
    audio2d?.setVolumeLinear(0.5)
    audio2d?.setPitchScale(1.1)
    audio2d?.setStream(null)
    val audio2dStreamNull = audio2d?.getStream() == null
    audio2d?.setBus("Master")
    audio2d?.setAutoplay(false)
    audio2d?.setMaxPolyphony(2)
    audio2d?.setStreamPaused(true)
    val audio2dPaused = audio2d?.getStreamPaused() ?: false
    audio2d?.setStreamPaused(false)
    audio2d?.seek(0.0)
    val audio2dVolume = audio2d?.getVolumeDb() ?: 0.0
    val audio2dVolumeLinear = audio2d?.getVolumeLinear() ?: 0.0
    val audio2dPitch = audio2d?.getPitchScale() ?: 0.0
    val audio2dBus = audio2d?.getBus().orEmpty()
    val audio2dAutoplay = audio2d?.isAutoplayEnabled() ?: true
    val audio2dPolyphony = audio2d?.getMaxPolyphony() ?: 0L
    val audio2dPlaybackPosition = audio2d?.getPlaybackPosition() ?: -1.0
    val audio2dPlayingBefore = audio2d?.isPlaying() ?: false
    audio2d?.stop()
    val audio2dPlayingAfterStop = audio2d?.isPlaying() ?: false
    val animationPlayer =
      selfNode.getNodeAsOrNull("../AnimationPlayer", "AnimationPlayer", ::AnimationPlayer)
    val animationFound = selfNode.hasNode("../AnimationPlayer") && animationPlayer != null
    animationPlayer?.setActive(true)
    animationPlayer?.setDeterministic(true)
    animationPlayer?.setCallbackModeProcess(AnimationMixer.ANIMATION_CALLBACK_MODE_PROCESS_IDLE)
    animationPlayer?.setCallbackModeMethod(AnimationMixer.ANIMATION_CALLBACK_MODE_METHOD_IMMEDIATE)
    animationPlayer?.setCallbackModeDiscrete(
      AnimationMixer.ANIMATION_CALLBACK_MODE_DISCRETE_FORCE_CONTINUOUS
    )
    animationPlayer?.setAudioMaxPolyphony(3)
    animationPlayer?.setRootMotionLocal(true)
    animationPlayer?.setDefaultBlendTime(0.25)
    animationPlayer?.setAutoCapture(true)
    animationPlayer?.setAutoCaptureDuration(0.5)
    animationPlayer?.setSpeedScale(1.5)
    animationPlayer?.setMovieQuitOnFinishEnabled(false)
    animationPlayer?.setProcessCallback(AnimationPlayer.ANIMATION_PROCESS_IDLE)
    animationPlayer?.setMethodCallMode(AnimationPlayer.ANIMATION_METHOD_CALL_IMMEDIATE)
    animationPlayer?.setAssignedAnimation("")
    animationPlayer?.setCurrentAnimation("")
    animationPlayer?.seek(0.0, update = true, updateOnly = false)
    animationPlayer?.play("", customBlend = -1.0, customSpeed = 1.0, fromEnd = false)
    animationPlayer?.playBackwards("", customBlend = -1.0)
    animationPlayer?.clearQueue()
    animationPlayer?.pause()
    animationPlayer?.stop(false)
    animationPlayer?.resetSection()
    animationPlayer?.clearCaches()
    animationPlayer?.advance(0.0)
    val animationActive = animationPlayer?.isActive() ?: false
    val animationDeterministic = animationPlayer?.isDeterministic() ?: false
    val animationProcessMode = animationPlayer?.getCallbackModeProcess() ?: -1L
    val animationMethodMode = animationPlayer?.getCallbackModeMethod() ?: -1L
    val animationDiscreteMode = animationPlayer?.getCallbackModeDiscrete() ?: -1L
    val animationPolyphony = animationPlayer?.getAudioMaxPolyphony() ?: 0L
    val animationRootLocal = animationPlayer?.isRootMotionLocal() ?: false
    val animationRootMotionPosition = animationPlayer?.getRootMotionPosition() ?: Vector3.ZERO
    val animationRootMotionScale = animationPlayer?.getRootMotionScale() ?: Vector3.ZERO
    val animationRootMotionPositionAccumulator =
      animationPlayer?.getRootMotionPositionAccumulator() ?: Vector3.ZERO
    val animationRootMotionScaleAccumulator =
      animationPlayer?.getRootMotionScaleAccumulator() ?: Vector3.ZERO
    val animationDefaultBlend = animationPlayer?.getDefaultBlendTime() ?: 0.0
    val animationAutoCapture = animationPlayer?.isAutoCapture() ?: false
    val animationAutoCaptureDuration = animationPlayer?.getAutoCaptureDuration() ?: 0.0
    val animationPlaying = animationPlayer?.isPlaying() ?: true
    val animationAnimationActive = animationPlayer?.isAnimationActive() ?: true
    val animationSpeedScale = animationPlayer?.getSpeedScale() ?: 0.0
    val animationPlayingSpeed = animationPlayer?.getPlayingSpeed() ?: 0.0
    val animationMovieQuit = animationPlayer?.isMovieQuitOnFinishEnabled() ?: true
    val animationCurrentName = animationPlayer?.getCurrentAnimation().orEmpty()
    val animationAssignedName = animationPlayer?.getAssignedAnimation().orEmpty()
    val animationCurrentPosition = animationPlayer?.getCurrentAnimationPosition() ?: -1.0
    val animationCurrentLength = animationPlayer?.getCurrentAnimationLength() ?: -1.0
    val animationHasSection = animationPlayer?.hasSection() ?: true
    val animationSectionStart = animationPlayer?.getSectionStartTime() ?: 0.0
    val animationSectionEnd = animationPlayer?.getSectionEndTime() ?: 0.0
    val animationPlayerProcessMode = animationPlayer?.getProcessCallback() ?: -1L
    val animationPlayerMethodMode = animationPlayer?.getMethodCallMode() ?: -1L
    val meshInstance3d =
      selfNode.getNodeAsOrNull("../MeshInstance3D", "MeshInstance3D", ::MeshInstance3D)
    val meshFound = selfNode.hasNode("../MeshInstance3D") && meshInstance3d != null
    meshInstance3d?.setLayerMask(7)
    meshInstance3d?.setSortingOffset(2.0)
    meshInstance3d?.setSortingUseAabbCenter(true)
    meshInstance3d?.setCastShadowsSetting(GeometryInstance3D.SHADOW_CASTING_SETTING_OFF)
    meshInstance3d?.setLodBias(1.25)
    meshInstance3d?.setTransparency(0.25)
    meshInstance3d?.setVisibilityRangeBegin(1.0)
    meshInstance3d?.setVisibilityRangeEnd(100.0)
    meshInstance3d?.setVisibilityRangeFadeMode(GeometryInstance3D.VISIBILITY_RANGE_FADE_SELF)
    meshInstance3d?.setExtraCullMargin(0.5)
    meshInstance3d?.setLightmapTexelScale(1.5)
    meshInstance3d?.setIgnoreOcclusionCulling(true)
    val assignedBoxMesh = BoxMesh.create()
    assignedBoxMesh.setSize(Vector3(1.5f, 2.5f, 3.5f))
    assignedBoxMesh.setSubdivideWidth(1)
    assignedBoxMesh.setSubdivideHeight(2)
    assignedBoxMesh.setSubdivideDepth(3)
    assignedBoxMesh.setFlipFaces(true)
    assignedBoxMesh.setAddUv2(true)
    assignedBoxMesh.setUv2Padding(2.0)
    assignedBoxMesh.requestUpdate()
    meshInstance3d?.setMesh(assignedBoxMesh)
    val assignedMaterial = StandardMaterial3D.create()
    assignedMaterial.setAlbedo(Color(0.1f, 0.2f, 0.3f, 0.75f))
    assignedMaterial.setMetallic(0.4)
    assignedMaterial.setRoughness(0.6)
    assignedMaterial.setShadingMode(BaseMaterial3D.SHADING_MODE_UNSHADED)
    assignedMaterial.setTransparency(BaseMaterial3D.TRANSPARENCY_ALPHA)
    assignedMaterial.setCullMode(BaseMaterial3D.CULL_DISABLED)
    assignedMaterial.setRenderPriority(2)
    meshInstance3d?.setMaterialOverride(assignedMaterial)
    meshInstance3d?.setMaterialOverlay(assignedMaterial)
    meshInstance3d?.setSurfaceOverrideMaterial(0, assignedMaterial)
    val autoloadResourceClass = autoload?.call("resource_class_name", assignedMaterial).toString()
    val meshLayerMask = meshInstance3d?.getLayerMask() ?: 0L
    val meshSortingOffset = meshInstance3d?.getSortingOffset() ?: 0.0
    val meshSortingAabb = meshInstance3d?.isSortingUseAabbCenter() ?: false
    val meshCastShadows = meshInstance3d?.getCastShadowsSetting() ?: -1L
    val meshLodBias = meshInstance3d?.getLodBias() ?: 0.0
    val meshTransparency = meshInstance3d?.getTransparency() ?: 0.0
    val meshVisibilityBegin = meshInstance3d?.getVisibilityRangeBegin() ?: 0.0
    val meshVisibilityEnd = meshInstance3d?.getVisibilityRangeEnd() ?: 0.0
    val meshVisibilityFade = meshInstance3d?.getVisibilityRangeFadeMode() ?: -1L
    val meshExtraCull = meshInstance3d?.getExtraCullMargin() ?: 0.0
    val meshLightmapTexelScale = meshInstance3d?.getLightmapTexelScale() ?: 0.0
    val meshIgnoreOcclusion = meshInstance3d?.isIgnoringOcclusionCulling() ?: false
    val meshSurfaceOverrideMaterialCount = meshInstance3d?.getSurfaceOverrideMaterialCount() ?: -1L
    val meshBlendShapeCount = meshInstance3d?.getBlendShapeCount() ?: -1L
    val assignedMesh = meshInstance3d?.getMesh()
    val assignedMeshIsBox = assignedMesh?.isClass("BoxMesh") ?: false
    val assignedMeshSize = assignedBoxMesh.getSize()
    val assignedMeshSubdivideWidth = assignedBoxMesh.getSubdivideWidth()
    val assignedMeshSubdivideHeight = assignedBoxMesh.getSubdivideHeight()
    val assignedMeshSubdivideDepth = assignedBoxMesh.getSubdivideDepth()
    val assignedMeshFlipFaces = assignedBoxMesh.getFlipFaces()
    val assignedMeshAddUv2 = assignedBoxMesh.getAddUv2()
    val assignedMeshUv2Padding = assignedBoxMesh.getUv2Padding()
    val materialOverrideIsStandard =
      meshInstance3d?.getMaterialOverride()?.use { it.isClass("StandardMaterial3D") } ?: false
    val materialOverlayIsStandard =
      meshInstance3d?.getMaterialOverlay()?.use { it.isClass("StandardMaterial3D") } ?: false
    val surfaceMaterialIsStandard =
      meshInstance3d?.getSurfaceOverrideMaterial(0)?.use { it.isClass("StandardMaterial3D") }
        ?: false
    val assignedMaterialAlbedo = assignedMaterial.getAlbedo()
    val assignedMaterialMetallic = assignedMaterial.getMetallic()
    val assignedMaterialRoughness = assignedMaterial.getRoughness()
    val assignedMaterialShading = assignedMaterial.getShadingMode()
    val assignedMaterialTransparency = assignedMaterial.getTransparency()
    val assignedMaterialCull = assignedMaterial.getCullMode()
    val assignedMaterialPriority = assignedMaterial.getRenderPriority()
    meshInstance3d?.setSurfaceOverrideMaterial(0, null)
    meshInstance3d?.setMaterialOverlay(null)
    meshInstance3d?.setMaterialOverride(null)
    meshInstance3d?.setMesh(null)
    assignedMaterial.close()
    assignedBoxMesh.close()
    val instancedMeshRaw = selfNode.getNodeOrNull("../InstancedMesh")
    val instancedMeshClass = instancedMeshRaw?.getClassName() ?: "<null>"
    val instancedMeshIsClass = instancedMeshRaw?.isClass("MeshInstance3D") ?: false
    val instancedMeshTyped =
      selfNode.getNodeAsOrNull("../InstancedMesh", "MeshInstance3D", ::MeshInstance3D)
    val instancedMeshTypedNonNull = instancedMeshTyped != null
    System.err.println(
      "[kanama:kt] InstancedMesh raw_class=$instancedMeshClass is_class_match=$instancedMeshIsClass " +
        "typed_lookup=$instancedMeshTypedNonNull"
    )
    val gpuParticles =
      selfNode.getNodeAsOrNull("../GPUParticles3D", "GPUParticles3D", ::GPUParticles3D)
    gpuParticles?.setAmount(32)
    gpuParticles?.setLifetime(1.75)
    gpuParticles?.setOneShot(true)
    gpuParticles?.setPreProcessTime(0.25)
    gpuParticles?.setExplosivenessRatio(0.5)
    gpuParticles?.setRandomnessRatio(0.125)
    gpuParticles?.setFixedFps(30)
    gpuParticles?.setFractionalDelta(false)
    gpuParticles?.setSpeedScale(1.5)
    gpuParticles?.setDrawOrder(GPUParticles3D.DRAW_ORDER_REVERSE_LIFETIME)
    gpuParticles?.setEmitting(true)
    val gpuParticlesAmount = gpuParticles?.getAmount() ?: -1L
    val gpuParticlesLifetime = gpuParticles?.getLifetime() ?: -1.0
    val gpuParticlesOneShot = gpuParticles?.getOneShot() ?: false
    val gpuParticlesPreProcess = gpuParticles?.getPreProcessTime() ?: -1.0
    val gpuParticlesExplosiveness = gpuParticles?.getExplosivenessRatio() ?: -1.0
    val gpuParticlesRandomness = gpuParticles?.getRandomnessRatio() ?: -1.0
    val gpuParticlesFixedFps = gpuParticles?.getFixedFps() ?: -1L
    val gpuParticlesFractional = gpuParticles?.getFractionalDelta() ?: true
    val gpuParticlesSpeedScale = gpuParticles?.getSpeedScale() ?: -1.0
    val gpuParticlesDrawOrder = gpuParticles?.getDrawOrder() ?: -1L
    val gpuParticlesEmitting = gpuParticles?.isEmitting() ?: false
    gpuParticles?.restart(keepSeed = true)
    val cpuParticles =
      selfNode.getNodeAsOrNull("../CPUParticles3D", "CPUParticles3D", ::CPUParticles3D)
    cpuParticles?.setAmount(24)
    cpuParticles?.setLifetime(2.25)
    cpuParticles?.setOneShot(true)
    cpuParticles?.setPreProcessTime(0.5)
    cpuParticles?.setExplosivenessRatio(0.25)
    cpuParticles?.setRandomnessRatio(0.375)
    cpuParticles?.setFixedFps(20)
    cpuParticles?.setFractionalDelta(false)
    cpuParticles?.setSpeedScale(0.75)
    cpuParticles?.setDrawOrder(CPUParticles3D.DRAW_ORDER_VIEW_DEPTH)
    cpuParticles?.setEmitting(true)
    val cpuParticlesAmount = cpuParticles?.getAmount() ?: -1L
    val cpuParticlesLifetime = cpuParticles?.getLifetime() ?: -1.0
    val cpuParticlesOneShot = cpuParticles?.getOneShot() ?: false
    val cpuParticlesPreProcess = cpuParticles?.getPreProcessTime() ?: -1.0
    val cpuParticlesExplosiveness = cpuParticles?.getExplosivenessRatio() ?: -1.0
    val cpuParticlesRandomness = cpuParticles?.getRandomnessRatio() ?: -1.0
    val cpuParticlesFixedFps = cpuParticles?.getFixedFps() ?: -1L
    val cpuParticlesFractional = cpuParticles?.getFractionalDelta() ?: true
    val cpuParticlesSpeedScale = cpuParticles?.getSpeedScale() ?: -1.0
    val cpuParticlesDrawOrder = cpuParticles?.getDrawOrder() ?: -1L
    val cpuParticlesEmitting = cpuParticles?.isEmitting() ?: false
    cpuParticles?.restart(keepSeed = true)
    val timerNode = selfNode.getNodeAsOrNull("../Timer", "Timer", ::Timer)
    val timerFound = selfNode.hasNode("../Timer") && timerNode != null
    timerNode?.setWaitTime(2.5)
    timerNode?.setOneShot(true)
    timerNode?.setAutostart(false)
    timerNode?.setPaused(true)
    timerNode?.setIgnoreTimeScale(true)
    timerNode?.setTimerProcessCallback(Timer.TIMER_PROCESS_IDLE)
    timerNode?.start(1.25)
    val timerWaitTime = timerNode?.getWaitTime() ?: 0.0
    val timerOneShot = timerNode?.isOneShot() ?: false
    val timerAutostart = timerNode?.hasAutostart() ?: true
    val timerPaused = timerNode?.isPaused() ?: false
    val timerIgnoreTimeScale = timerNode?.isIgnoringTimeScale() ?: false
    val timerProcessCallback = timerNode?.getTimerProcessCallback() ?: -1L
    val timerTimeLeftBeforeStop = timerNode?.getTimeLeft() ?: 0.0
    val timerStoppedBeforeStop = timerNode?.isStopped() ?: true
    timerNode?.stop()
    val timerStoppedAfterStop = timerNode?.isStopped() ?: false
    val sceneTreeTimer =
      SceneTree.createTimer(
        10.0,
        processAlways = true,
        processInPhysics = false,
        ignoreTimeScale = true,
      )
    val sceneTreeTimerClass = sceneTreeTimer?.getClassName().orEmpty()
    val sceneTreeTimerRefCount = sceneTreeTimer?.getReferenceCount() ?: 0L
    sceneTreeTimer?.setTimeLeft(3.0)
    val sceneTreeTimerTimeLeft = sceneTreeTimer?.getTimeLeft() ?: 0.0
    sceneTreeTimer?.close()
    val tween = SceneTree.createTween()
    val tweenClass = tween?.getClassName().orEmpty()
    val tweenRefCount = tween?.getReferenceCount() ?: 0L
    tween
      ?.bindNode(selfNode)
      ?.setProcessMode(Tween.TWEEN_PROCESS_IDLE)
      ?.setPauseMode(Tween.TWEEN_PAUSE_PROCESS)
      ?.setIgnoreTimeScale(true)
      ?.setParallel(false)
      ?.setLoops(1)
      ?.setSpeedScale(1.0)
      ?.setTrans(Tween.TRANS_SINE)
      ?.setEase(Tween.EASE_OUT)
      ?.chain()
      ?.parallel()
    val tweenProperty = tween?.tweenProperty(selfNode, "process_priority", 5L, 0.01)
    tweenProperty?.from(3L)?.setTrans(Tween.TRANS_LINEAR)?.setEase(Tween.EASE_IN_OUT)?.setDelay(0.0)
    val tweenCallback =
      tween?.tweenCallback(selfNode, "notify_property_list_changed")?.setDelay(0.0)
    val tweenInterval = tween?.tweenInterval(0.0)
    // RefCounted return-slot ownership probes (task 31, separate probe tween so the main
    // sequence staging is untouched). Every RefCounted-typed ptrcall return transfers a +1
    // reference the wrapper owns (`meta:"required"` included); fluent self-returns must
    // collapse to the receiver and release the duplicate, so all deltas here must be 0.
    run {
      val probeTween = SceneTree.createTween()
      val probeMethodTweener =
        probeTween?.tweenMethod(selfNode, "set_process_priority", 3L, 3L, 0.01)
      val probeMethodCreated = probeMethodTweener?.getReferenceCount() ?: -1L
      val probeMethodChained = probeMethodTweener?.setDelay(0.0)
      val probeMethodAfterDelay = probeMethodTweener?.getReferenceCount() ?: -1L
      probeMethodTweener?.setTrans(Tween.TRANS_LINEAR)
      val probeMethodAfterTrans = probeMethodTweener?.getReferenceCount() ?: -1L
      val probePropertyTweener = probeTween?.tweenProperty(selfNode, "process_priority", 5L, 0.01)
      val probePropertyCreated = probePropertyTweener?.getReferenceCount() ?: -1L
      probePropertyTweener?.from(3L)
      val probePropertyAfterFrom = probePropertyTweener?.getReferenceCount() ?: -1L
      val probeAwaitTweener = probeTween?.tweenAwait(selfNode.signal("renamed"))
      val probeAwaitCreated = probeAwaitTweener?.getReferenceCount() ?: -1L
      val probeAwaitChained = probeAwaitTweener?.setTimeout(30.0)
      val probeAwaitAfterTimeout = probeAwaitTweener?.getReferenceCount() ?: -1L
      System.err.println(
        "[kanama:kt] Tweener ownership method_delay_delta=${probeMethodAfterDelay - probeMethodCreated} " +
          "method_trans_delta=${probeMethodAfterTrans - probeMethodAfterDelay} " +
          "method_chained_same=${probeMethodChained === probeMethodTweener} " +
          "property_from_delta=${probePropertyAfterFrom - probePropertyCreated} " +
          "await_timeout_delta=${probeAwaitAfterTimeout - probeAwaitCreated} " +
          "await_chained_same=${probeAwaitChained === probeAwaitTweener}"
      )
      probeMethodTweener?.close()
      probePropertyTweener?.close()
      probeAwaitTweener?.close()
      probeTween?.kill()
      probeTween?.close()
    }
    // ClassDB.instantiate ownership probe (task 43, GitHub PR #42): the return Variant
    // holds the fresh instance's ONLY reference, so the owned decode must retain
    // RefCounted results and hand back the owning RefCounted wrapper (close() releases;
    // created_rc must be exactly the wrapper's 1). Non-RefCounted results stay borrowed.
    run {
      val created = ClassDB.instantiate("Gradient")
      val ownedWrapper = created as? RefCounted
      val createdClass = ownedWrapper?.getClassName().orEmpty()
      val createdRc = ownedWrapper?.getReferenceCount() ?: -1L
      val usable = ownedWrapper?.isClass("Resource") ?: false
      selfNode.setMeta("kanama_probe_gradient", ownedWrapper)
      val heldRc = ownedWrapper?.getReferenceCount() ?: -1L
      ownedWrapper?.close()
      val metaHeld = selfNode.getMeta("kanama_probe_gradient") as? GodotObject
      val closedRc = (metaHeld?.call("get_reference_count") as? Number)?.toLong() ?: -1L
      selfNode.removeMeta("kanama_probe_gradient")
      val nodeCreated = ClassDB.instantiate("Node")
      val nodePlain = nodeCreated is GodotObject && nodeCreated !is RefCounted
      val nodeClass = (nodeCreated as? GodotObject)?.getClassName().orEmpty()
      (nodeCreated as? GodotObject)?.call("free")
      System.err.println(
        "[kanama:kt] ClassDB instantiate ownership class=$createdClass " +
          "owned_wrapper=${ownedWrapper != null} created_rc=$createdRc held_rc=$heldRc " +
          "closed_rc=$closedRc usable=$usable node_class=$nodeClass node_plain=$nodePlain"
      )
    }
    // ClassDB.class_call_static ownership probe: class_call_static is a varargs Object.call
    // that, like instantiate, can return a freshly minted RefCounted whose ONLY reference
    // lives in the return Variant. The owned decode must retain it into the owning RefCounted
    // wrapper (close() releases; created_rc must be exactly the wrapper's 1) — the borrowed
    // decode would free it on return (use-after-free). A non-object static return
    // (Thread.is_main_thread -> bool) must pass through the owned path untouched.
    run {
      val created = ClassDB.classCallStatic("RegEx", "create_from_string", "a.c")
      val ownedWrapper = created as? RefCounted
      val createdClass = ownedWrapper?.getClassName().orEmpty()
      val createdRc = ownedWrapper?.getReferenceCount() ?: -1L
      val usable = ownedWrapper?.isClass("RegEx") ?: false
      selfNode.setMeta("kanama_probe_regex", ownedWrapper)
      val heldRc = ownedWrapper?.getReferenceCount() ?: -1L
      ownedWrapper?.close()
      val metaHeld = selfNode.getMeta("kanama_probe_regex") as? GodotObject
      val closedRc = (metaHeld?.call("get_reference_count") as? Number)?.toLong() ?: -1L
      selfNode.removeMeta("kanama_probe_regex")
      val scalarRet = ClassDB.classCallStatic("Thread", "is_main_thread")
      val scalarMainThread = scalarRet as? Boolean ?: false
      System.err.println(
        "[kanama:kt] ClassDB class_call_static ownership class=$createdClass " +
          "owned_wrapper=${ownedWrapper != null} created_rc=$createdRc held_rc=$heldRc " +
          "closed_rc=$closedRc usable=$usable scalar_main_thread=$scalarMainThread"
      )
    }
    val tweenAwaitTweener = tween?.tweenAwait(selfNode.signal("renamed"))?.setTimeout(30.0)
    val tweenValidBefore = tween?.isValid() ?: false
    val processedTweensBeforeKill = SceneTree.getProcessedTweens().size
    val tweenStep = tween?.customStep(0.02) ?: false
    val tweenElapsed = tween?.getTotalElapsedTime() ?: -1.0
    val tweenRunningAfterStep = tween?.isRunning() ?: false
    val tweenLoopsLeft = tween?.getLoopsLeft() ?: -1L
    val tweenPriorityAfterStep = selfNode.getProcessPriority()
    // The first customStep drove the sequence into the AwaitTweener (it connects to the
    // awaited signal when it activates). Emit the signal and step again so the await
    // resolves and disconnects — a tween killed mid-await leaves that connection alive
    // (orphan StringName at exit), so the smoke must finish the await, not abandon it.
    // Drive the sequence until it parks on the AwaitTweener (it consumes all delta while
    // waiting, so customStep keeps returning true), then emit the awaited signal and step
    // once more so the await resolves. The emission must come after the await's start()
    // (start() resets its received flag), hence the bounded spin first.
    var awaitSpin = 0
    while (awaitSpin < 8 && tween?.customStep(0.05) == true) {
      awaitSpin++
    }
    selfNode.emitSignal("renamed")
    tween?.customStep(0.02)
    // customStep's return reports step activity, not post-state — the emission-resolved
    // await shows up as isRunning() flipping false on this step.
    val tweenAwaitFinished = tween?.isRunning() == false
    selfNode.setProcessPriority(3)
    tweenProperty?.close()
    tweenCallback?.close()
    tweenInterval?.close()
    tweenAwaitTweener?.close()
    tween?.kill()
    val tweenValidAfterKill = tween?.isValid() ?: true
    tween?.close()
    val selfIndex = selfNode.getIndex()
    val selfSceneFilePath = selfNode.getSceneFilePath()
    val selfTreeString = selfNode.getTreeString()
    val selfTreeNodeCount = selfNode.getTree().getNodeCount()
    val selfCanProcess = selfNode.canProcess()
    val selfProcessing = selfNode.isProcessing()
    val selfPhysicsProcessing = selfNode.isPhysicsProcessing()
    val selfProcessDelta = selfNode.getProcessDeltaTime()
    val selfPhysicsDelta = selfNode.getPhysicsProcessDeltaTime()
    val selfToString = selfNode.toString()
    val selfReady = selfNode.isNodeReady()
    val groupName = "kanama_node_smoke"
    selfNode.addToGroup(groupName)
    val selfInGroup = selfNode.isInGroup(groupName)
    SceneTree.setGroup(groupName, "editor_description", "kanama scene tree group")
    val sceneTreeGroupSetDescription = selfNode.getEditorDescription()
    SceneTree.setGroupFlags(0L, groupName, "editor_description", "kanama scene tree flags")
    val sceneTreeGroupFlagsDescription = selfNode.getEditorDescription()
    selfNode.removeFromGroup(groupName)
    val selfGroupRemoved = !selfNode.isInGroup(groupName)
    selfNode.setProcess(true)
    val selfProcessingAfterSet = selfNode.isProcessing()
    selfNode.setPhysicsProcess(true)
    val selfPhysicsProcessingAfterSet = selfNode.isPhysicsProcessing()
    selfNode.setProcessInput(true)
    val selfProcessingInput = selfNode.isProcessingInput()
    selfNode.setProcessShortcutInput(true)
    val selfProcessingShortcutInput = selfNode.isProcessingShortcutInput()
    selfNode.setProcessUnhandledInput(true)
    val selfProcessingUnhandledInput = selfNode.isProcessingUnhandledInput()
    selfNode.setProcessUnhandledKeyInput(true)
    val selfProcessingUnhandledKeyInput = selfNode.isProcessingUnhandledKeyInput()
    val selfMultiplayerAuthority = selfNode.getMultiplayerAuthority()
    val selfIsMultiplayerAuthority = selfNode.isMultiplayerAuthority()
    selfNode.setProcessPriority(3)
    val selfProcessPriority = selfNode.getProcessPriority()
    selfNode.setPhysicsProcessPriority(4)
    val selfPhysicsProcessPriority = selfNode.getPhysicsProcessPriority()
    selfNode.setDisplayFolded(true)
    val selfDisplayedFolded = selfNode.isDisplayedFolded()
    selfNode.setUniqueNameInOwner(true)
    val selfUniqueNameInOwner = selfNode.isUniqueNameInOwner()
    selfNode.setEditorDescription("kanama node smoke")
    val selfEditorDescription = selfNode.getEditorDescription()
    selfNode.setProcessMode(Node.PROCESS_MODE_ALWAYS)
    val selfProcessMode = selfNode.getProcessMode()
    selfNode.setProcessThreadGroup(Node.PROCESS_THREAD_GROUP_MAIN_THREAD)
    val selfProcessThreadGroup = selfNode.getProcessThreadGroup()
    selfNode.setProcessThreadMessages(Node.FLAG_PROCESS_THREAD_MESSAGES_ALL)
    val selfProcessThreadMessages = selfNode.getProcessThreadMessages()
    selfNode.setProcessThreadGroupOrder(2)
    val selfProcessThreadGroupOrder = selfNode.getProcessThreadGroupOrder()
    selfNode.setProcessInternal(true)
    val selfProcessingInternal = selfNode.isProcessingInternal()
    selfNode.setPhysicsProcessInternal(true)
    val selfPhysicsProcessingInternal = selfNode.isPhysicsProcessingInternal()
    selfNode.setPhysicsInterpolationMode(Node.PHYSICS_INTERPOLATION_MODE_OFF)
    val selfPhysicsInterpolationMode = selfNode.getPhysicsInterpolationMode()
    val selfPhysicsInterpolated = selfNode.isPhysicsInterpolated()
    val selfPhysicsInterpolatedEnabled = selfNode.isPhysicsInterpolatedAndEnabled()
    selfNode.resetPhysicsInterpolation()
    selfNode.setAutoTranslateMode(Node.AUTO_TRANSLATE_MODE_DISABLED)
    val selfAutoTranslateMode = selfNode.getAutoTranslateMode()
    val selfCanAutoTranslate = selfNode.canAutoTranslate()
    selfNode.setTranslationDomainInherited()
    selfNode.setSceneInstanceLoadPlaceholder(true)
    val selfScenePlaceholder = selfNode.getSceneInstanceLoadPlaceholder()
    selfNode.setSceneInstanceLoadPlaceholder(false)
    val selfScenePlaceholderAfterReset = selfNode.getSceneInstanceLoadPlaceholder()
    val objectCanRevertName = selfNode.propertyCanRevert("name")
    val objectHasMissingMeta = selfNode.hasMeta("kanama_missing_meta")
    val objectHasMissingUserSignal = selfNode.hasUserSignal("kanama_missing_signal")
    val objectHasQueueFree = selfNode.hasMethod("queue_free")
    val objectQueueFreeArgs = selfNode.getMethodArgumentCount("queue_free")
    val objectHasScriptChanged = selfNode.hasSignal("script_changed")
    val objectHasScriptChangedConnections = selfNode.hasConnections("script_changed")
    val objectSignalConnectError =
      selfNode
        .signal(Node.Signals.childEnteredTree)
        .connect(selfNode, HelloScriptNames.Methods.onSmokeBodyEntered)
    selfNode.signal(Node.Signals.childEnteredTree).emit(selfNode)
    val objectSignalCallbackClass = smokeSignalBodyClass
    selfNode
      .signal(Node.Signals.childEnteredTree)
      .disconnect(selfNode, HelloScriptNames.Methods.onSmokeBodyEntered)
    var objectSignalLambdaClass = ""
    lateinit var objectSignalLambdaConnection: AutoCloseable
    objectSignalLambdaConnection =
      selfNode.signal(Node.Signals.childEnteredTree).connectObject(
        selfNode,
        GodotObject.CONNECT_ONE_SHOT,
      ) { body ->
        objectSignalLambdaClass = body.getClassName()
        objectSignalLambdaConnection.close()
      }
    selfNode.signal(Node.Signals.childEnteredTree).emit(selfNode)
    selfNode.setBlockSignals(true)
    val objectBlockingSignals = selfNode.isBlockingSignals()
    selfNode.setBlockSignals(false)
    val objectBlockingSignalsAfterReset = selfNode.isBlockingSignals()
    val scriptSignalConnectError =
      selfNode
        .signal(HelloScriptNames.Signals.smokeChecked)
        .connect(selfNode, HelloScriptNames.Methods.onSmokeChecked)
    HelloScriptSignals.smokeChecked(this, "helper")
    val scriptSignalCallback = smokeScriptSignalMessage
    selfNode
      .signal(HelloScriptNames.Signals.smokeChecked)
      .disconnect(selfNode, HelloScriptNames.Methods.onSmokeChecked)
    kanamaScope.launch(start = CoroutineStart.UNDISPATCHED) {
      smokeCoroutineStarted = true
      MainThread.awaitNextFrame()
      SceneTree.delaySeconds(0.001)
    }
    notifyInspectorChanged()
    selfNode.setMessageTranslation(false)
    val objectCanTranslateDisabled = selfNode.canTranslateMessages()
    selfNode.setMessageTranslation(true)
    val objectCanTranslateEnabled = selfNode.canTranslateMessages()
    val autoloadDescribe = autoload?.call("describe", "audio", 3L, true, 1.5).toString()
    val autoloadAdd = autoload?.call("add_count", 4L, 5L) as? Long ?: -1L
    val autoloadNegate = autoload?.call("negate", false) as? Boolean ?: false
    val autoloadObjectClass = autoload?.call("object_class_name", selfNode).toString()
    val autoloadReturnedObject = autoload?.call("return_object", selfNode) as? GodotObject
    val autoloadReturnedObjectClass = autoloadReturnedObject?.getClassName().orEmpty()
    val autoloadVector2 = autoload?.call("vector2_sum", Vector2(2f, 3f)) as? Vector2 ?: Vector2.ZERO
    val autoloadVector3 =
      autoload?.call("vector3_sum", Vector3(2f, 3f, 4f)) as? Vector3 ?: Vector3.ZERO
    val autoloadColor =
      autoload?.call("color_mix", Color(0.1f, 0.2f, 0.3f, 0.4f)) as? Color ?: Color(0f, 0f, 0f, 0f)
    val autoloadQuaternion =
      autoload?.call("quaternion_negate", Quaternion(0.1f, 0.2f, 0.3f, 0.4f)) as? Quaternion
        ?: Quaternion(0f, 0f, 0f, 0f)
    val autoloadVector4 =
      autoload?.call("vector4_negate", Vector4(1f, 2f, 3f, 4f)) as? Vector4 ?: Vector4.ZERO
    val autoloadRect2 =
      autoload?.call("rect2_grow", Rect2(Vector2(0f, 0f), Vector2(10f, 20f))) as? Rect2
        ?: Rect2.ZERO
    val autoloadAABB =
      autoload?.call("aabb_grow", AABB(Vector3(0f, 0f, 0f), Vector3(10f, 20f, 30f))) as? AABB
        ?: AABB.ZERO
    val autoloadPlane =
      autoload?.call("plane_negate", Plane(Vector3(1f, 0f, 0f), 5f)) as? Plane ?: Plane.ZERO
    val autoloadBasis =
      autoload?.call(
        "basis_translate",
        Basis(Vector3(1f, 0f, 0f), Vector3(0f, 1f, 0f), Vector3(0f, 0f, 1f)),
      ) as? Basis ?: Basis.IDENTITY
    val autoloadTransform3D =
      autoload?.call(
        "transform3d_translate",
        Transform3D(
          Basis(Vector3(1f, 0f, 0f), Vector3(0f, 1f, 0f), Vector3(0f, 0f, 1f)),
          Vector3(1f, 2f, 3f),
        ),
      ) as? Transform3D ?: Transform3D.IDENTITY
    val autoloadTransform2D =
      autoload?.call(
        "transform2d_translate",
        Transform2D(Vector2(1f, 0f), Vector2(0f, 1f), Vector2(5f, 6f)),
      ) as? Transform2D ?: Transform2D.IDENTITY
    val autoloadProjection =
      autoload?.call(
        "projection_negate_w",
        Projection(
          Vector4(1f, 2f, 3f, 4f),
          Vector4(5f, 6f, 7f, 8f),
          Vector4(9f, 10f, 11f, 12f),
          Vector4(13f, 14f, 15f, 16f),
        ),
      ) as? Projection ?: Projection.IDENTITY
    val autoloadVector2i =
      autoload?.call("vector2i_negate", Vector2i(2, 3)) as? Vector2i ?: Vector2i(0, 0)
    val autoloadVector3i =
      autoload?.call("vector3i_negate", Vector3i(2, 3, 4)) as? Vector3i ?: Vector3i(0, 0, 0)
    val autoloadVector4i =
      autoload?.call("vector4i_negate", Vector4i(1, 2, 3, 4)) as? Vector4i ?: Vector4i.ZERO
    val autoloadRect2i =
      autoload?.call("rect2i_grow", Rect2i(Vector2i(0, 0), Vector2i(10, 20))) as? Rect2i
        ?: Rect2i.ZERO
    val autoloadNodePath =
      autoload?.call("nodepath_describe", NodePath("foo/bar/baz"))?.toString() ?: ""
    val uiRoot = selfNode.getNodeAsOrNull("../UiRoot", "Control", ::Control)
    if (uiRoot != null) {
      uiRoot.position = Vector2(8f, 12f)
      uiRoot.size = Vector2(260f, 120f)
      uiRoot.customMinimumSize = Vector2(180f, 80f)
    }
    uiRoot?.setMouseFilter(Control.MOUSE_FILTER_PASS)
    val uiPosition = uiRoot?.position ?: Vector2.ZERO
    val uiSize = uiRoot?.size ?: Vector2.ZERO
    val uiMinSize = uiRoot?.customMinimumSize ?: Vector2.ZERO
    val uiMouseFilter = uiRoot?.getMouseFilter() ?: -1L
    val uiVisibleBefore = uiRoot?.isVisible() ?: false
    uiRoot?.hide()
    val uiVisibleHidden = uiRoot?.isVisible() ?: false
    uiRoot?.show()
    val uiVisibleShown = uiRoot?.isVisible() ?: false
    val smokeLabel = selfNode.getNodeAsOrNull("../UiRoot/SmokeLabel", "Label", ::Label)
    if (smokeLabel != null) smokeLabel.text = "kanama label"
    val labelText = smokeLabel?.text.orEmpty()
    val smokeButton = selfNode.getNodeAsOrNull("../UiRoot/SmokeButton", "Button", ::Button)
    if (smokeButton != null) smokeButton.text = "kanama button"
    smokeButton?.setToggleMode(true)
    smokeButton?.setPressed(true)
    smokeButton?.setDisabled(false)
    smokeButton?.setFocusMode(Control.FOCUS_ALL)
    smokeButton?.grabFocus()
    val buttonText = smokeButton?.text.orEmpty()
    val buttonToggle = smokeButton?.isToggleMode() ?: false
    val buttonPressed = smokeButton?.isPressed() ?: false
    val buttonDisabled = smokeButton?.isDisabled() ?: true
    val buttonFocusMode = smokeButton?.getFocusMode() ?: -1L
    val buttonFocused = smokeButton?.hasFocus() ?: false
    smokeButton?.releaseFocus()
    val dynamicLabel =
      uiRoot?.let {
        Label(ObjectCalls.constructObject("Label")).also { label ->
          label.text = "dynamic label"
          label.position = Vector2(12f, 32f)
          it.addChild(label)
        }
      }
    val dynamicButton =
      uiRoot?.let {
        Button(ObjectCalls.constructObject("Button")).also { button ->
          button.text = "dynamic button"
          button.position = Vector2(12f, 56f)
          button.size = Vector2(96f, 28f)
          it.addChild(button)
        }
      }
    val dynamicLabelText = dynamicLabel?.text.orEmpty()
    val dynamicButtonText = dynamicButton?.text.orEmpty()
    val dynamicLabelPosition = dynamicLabel?.position ?: Vector2.ZERO
    val dynamicButtonPosition = dynamicButton?.position ?: Vector2.ZERO
    val dynamicUiChildCount = uiRoot?.getChildCount() ?: -1L
    if (dynamicButton != null) {
      uiRoot.removeChild(dynamicButton)
      dynamicButton.queueFree()
    }
    if (dynamicLabel != null) {
      uiRoot.removeChild(dynamicLabel)
      dynamicLabel.queueFree()
    }
    val smokeOptionButton =
      selfNode.getNodeAsOrNull("../UiRoot/SmokeOptionButton", "OptionButton", ::OptionButton)
    if (smokeOptionButton != null) {
      smokeOptionButton.clear()
      smokeOptionButton.addItem("First", 10)
      smokeOptionButton.setItemMetadata(0, "option-meta")
      smokeOptionButton.select(0)
    }
    val optionItemMetadata = smokeOptionButton?.getItemMetadata(0)?.toString().orEmpty()
    val optionSelectedMetadata = smokeOptionButton?.getSelectedMetadata()?.toString().orEmpty()
    val optionSelectedId = smokeOptionButton?.getSelectedId() ?: -1
    val smokeTabBar = selfNode.getNodeAsOrNull("../UiRoot/SmokeTabBar", "TabBar", ::TabBar)
    if (smokeTabBar != null) {
      smokeTabBar.clearTabs()
      smokeTabBar.addTab("Alpha")
      smokeTabBar.setTabMetadata(0, "tab-meta")
    }
    val tabCount = smokeTabBar?.getTabCount() ?: -1
    val tabTitle = smokeTabBar?.getTabTitle(0).orEmpty()
    val tabMetadata = smokeTabBar?.getTabMetadata(0)?.toString().orEmpty()
    val smokeLineEdit = selfNode.getNodeAsOrNull("../UiRoot/SmokeLineEdit", "LineEdit", ::LineEdit)
    smokeLineEdit?.setStructuredTextBidiOverrideOptions(emptyList())
    val lineEditBidiOptionsSize = smokeLineEdit?.getStructuredTextBidiOverrideOptions()?.size ?: -1
    val vector3Length = Vector3(3f, 4f, 0f).length()
    val vector3Normalized = Vector3(0f, 3f, 4f).normalized()
    val vector3Dot = Vector3(1f, 2f, 3f).dot(Vector3(4f, 5f, 6f))
    val vector3Cross = Vector3(1f, 0f, 0f).cross(Vector3(0f, 1f, 0f))
    val vector3Lerp = Vector3.ZERO.lerp(Vector3(2f, 4f, 6f), 0.5)
    val vector3Limited = Vector3(10f, 0f, 0f).limitLength(2.0)
    val vector3Distance = Vector3.ZERO.distanceTo(Vector3(0f, 0f, 2f))
    val vector2Length = Vector2(3f, 4f).length()
    val vector2Angle = Vector2.RIGHT.angle()
    val vector2Lerp = Vector2.ZERO.lerp(Vector2(4f, 6f), 0.25)
    val vector3WithX = Vector3(1f, 2f, 3f).withX(9f)
    val vector3WithY = Vector3(1f, 2f, 3f).withY(9.0)
    val vector3WithZ = Vector3(1f, 2f, 3f).withZ(9f)
    val vector2WithX = Vector2(1f, 2f).withX(9f)
    val vector2WithY = Vector2(1f, 2f).withY(9.0)
    val grantedPermissions = OS.getGrantedPermissions()
    val memoryInfo = OS.getMemoryInfo()
    val singletonList = Engine.getSingletonList()
    val engineVersionInfo = Engine.getVersionInfo()
    val engineAuthorInfo = Engine.getAuthorInfo()
    val engineDonorInfo = Engine.getDonorInfo()
    val engineLicenseInfo = Engine.getLicenseInfo()
    val engineCopyrightInfo = Engine.getCopyrightInfo()
    val engineBacktraces = Engine.captureScriptBacktraces()
    val engineBacktraceCount = engineBacktraces.size
    engineBacktraces.forEach { it.close() }
    val connectedJoypads = Input.getConnectedJoypads()
    val joyInfo = Input.getJoyInfo(connectedJoypads.firstOrNull()?.toInt() ?: 0)
    val nowUnix = Time.getUnixTimeFromSystem().toLong()
    val systemDateTime = Time.getDateTimeDictFromSystem()
    val systemDate = Time.getDateDictFromSystem()
    val systemTime = Time.getTimeDictFromSystem()
    val unixDateTime = Time.getDateTimeDictFromUnixTime(nowUnix)
    val unixDate = Time.getDateDictFromUnixTime(nowUnix)
    val unixTime = Time.getTimeDictFromUnixTime(nowUnix)
    val parsedDateTime = Time.getDateTimeDictFromDateTimeString("2024-01-02T03:04:05", true)
    val timeZone = Time.getTimeZoneFromSystem()
    val dirFiles = DirAccess.getFilesAt("res://")
    val dirDirectories = DirAccess.getDirectoriesAt("res://")
    val driveCount = DirAccess.getDriveCount()
    val dirFileExists = DirAccess.fileExistsAt("res://", "HelloScript.kt")
    val dirDirExists = DirAccess.dirExistsAt("res://", "addons")
    val dirCurrentDrive = DirAccess.getCurrentDriveAt("res://")
    val dirCurrentDir = DirAccess.getCurrentDirAt("res://")
    val dirSpaceLeft = DirAccess.getSpaceLeftAt("res://")
    val dirFilesWithOptions = DirAccess.getFilesAt("res://", includeHidden = true)
    val dirDirectoriesWithOptions = DirAccess.getDirectoriesAt("res://", includeHidden = true)
    val dirIncludeHidden = DirAccess.getIncludeHiddenAt("res://")
    val dirIncludeNavigational = DirAccess.getIncludeNavigationalAt("res://")
    val dirEntries = DirAccess.listEntriesAt("res://")
    val dirFilesystemType = DirAccess.getFilesystemTypeAt("res://")
    val dirIsLink = DirAccess.isLinkAt("res://", "HelloScript.kt")
    val dirReadLink = DirAccess.readLinkAt("res://", "HelloScript.kt")
    val dirIsBundle = DirAccess.isBundleAt("res://", "HelloScript.kt")
    val dirCaseSensitive = DirAccess.isCaseSensitiveAt("res://", "HelloScript.kt")
    val dirEquivalent = DirAccess.isEquivalentAt("res://", "HelloScript.kt", "HelloScript.kt")
    val directDir = DirAccess.open("res://")
    val directDirPresent = directDir != null
    val directDirFileExists = directDir?.fileExists("HelloScript.kt") ?: false
    val directDirCurrentLen = directDir?.getCurrentDir()?.length ?: 0
    val directDirFilesHasHello = directDir?.getFiles()?.contains("HelloScript.kt") ?: false
    directDir?.close()
    val tempDir = DirAccess.createTemp("kanama_dir_handle_", keep = false)
    val tempDirPresent = tempDir != null
    val tempDirCurrentLen = tempDir?.getCurrentDir()?.length ?: 0
    tempDir?.close()
    val dirSmokePath = ProjectSettings.globalizePath("res://.kanama_dir_smoke")
    val dirSmokeNestedPath = ProjectSettings.globalizePath("res://.kanama_dir_smoke/nested")
    DirAccess.removeAbsolute(dirSmokeNestedPath)
    DirAccess.removeAbsolute(dirSmokePath)
    val dirMakeError = DirAccess.makeDirAbsolute(dirSmokePath)
    val dirMakeExists = DirAccess.dirExistsAbsolute(dirSmokePath)
    val dirMakeCleanupError = DirAccess.removeAbsolute(dirSmokePath)
    val dirRecursiveError = DirAccess.makeDirRecursiveAbsolute(dirSmokeNestedPath)
    val dirRecursiveExists = DirAccess.dirExistsAbsolute(dirSmokeNestedPath)
    val dirRecursiveNestedCleanupError = DirAccess.removeAbsolute(dirSmokeNestedPath)
    val dirRecursiveCleanupError = DirAccess.removeAbsolute(dirSmokePath)
    val dirCopyPath = ProjectSettings.globalizePath("res://.kanama_dir_copy_smoke.kt")
    val dirRenamePath = ProjectSettings.globalizePath("res://.kanama_dir_rename_smoke.kt")
    DirAccess.removeAbsolute(dirCopyPath)
    DirAccess.removeAbsolute(dirRenamePath)
    val dirCopyError =
      DirAccess.copyAbsolute(ProjectSettings.globalizePath("res://HelloScript.kt"), dirCopyPath)
    val dirCopyExists = FileAccess.fileExists("res://.kanama_dir_copy_smoke.kt")
    val dirCopyHasClass =
      dirCopyExists &&
        FileAccess.getFileAsString("res://.kanama_dir_copy_smoke.kt").contains("class HelloScript")
    val dirRenameError = DirAccess.renameAbsolute(dirCopyPath, dirRenamePath)
    val dirRenameExists = FileAccess.fileExists("res://.kanama_dir_rename_smoke.kt")
    val dirRenameOldMissing = !FileAccess.fileExists("res://.kanama_dir_copy_smoke.kt")
    val dirRenameHasClass =
      dirRenameExists &&
        FileAccess.getFileAsString("res://.kanama_dir_rename_smoke.kt")
          .contains("class HelloScript")
    val dirRenameCleanupError = if (dirRenameExists) DirAccess.removeAbsolute(dirRenamePath) else 0L
    DirAccess.removeAbsolute(dirCopyPath)
    DirAccess.removeAbsolute(
      ProjectSettings.globalizePath("res://.kanama_dir_instance_smoke/nested")
    )
    DirAccess.removeAbsolute(ProjectSettings.globalizePath("res://.kanama_dir_instance_smoke"))
    DirAccess.removeAbsolute(ProjectSettings.globalizePath("res://.kanama_dir_instance_copy.kt"))
    DirAccess.removeAbsolute(ProjectSettings.globalizePath("res://.kanama_dir_instance_rename.kt"))
    val dirChangeError = DirAccess.changeDirAt("res://", "addons")
    val dirInstanceMakeError = DirAccess.makeDirAt("res://", ".kanama_dir_instance_smoke")
    val dirInstanceMakeExists =
      DirAccess.dirExistsAbsolute(ProjectSettings.globalizePath("res://.kanama_dir_instance_smoke"))
    val dirInstanceRecursiveError =
      DirAccess.makeDirRecursiveAt("res://", ".kanama_dir_instance_smoke/nested")
    val dirInstanceRecursiveExists =
      DirAccess.dirExistsAbsolute(
        ProjectSettings.globalizePath("res://.kanama_dir_instance_smoke/nested")
      )
    val dirInstanceNestedCleanupError =
      DirAccess.removeAt("res://", ".kanama_dir_instance_smoke/nested")
    val dirInstanceCleanupError = DirAccess.removeAt("res://", ".kanama_dir_instance_smoke")
    val dirInstanceCopyError =
      DirAccess.copyAt("res://", "HelloScript.kt", ".kanama_dir_instance_copy.kt")
    val dirInstanceCopyExists = FileAccess.fileExists("res://.kanama_dir_instance_copy.kt")
    val dirInstanceRenameError =
      DirAccess.renameAt("res://", ".kanama_dir_instance_copy.kt", ".kanama_dir_instance_rename.kt")
    val dirInstanceRenameExists = FileAccess.fileExists("res://.kanama_dir_instance_rename.kt")
    val dirInstanceRenameOldMissing = !FileAccess.fileExists("res://.kanama_dir_instance_copy.kt")
    val dirInstanceRenameCleanupError =
      DirAccess.removeAt("res://", ".kanama_dir_instance_rename.kt")
    DirAccess.removeAbsolute(ProjectSettings.globalizePath("res://.kanama_dir_instance_copy.kt"))
    val displayName = DisplayServer.getName()
    val screenCount = DisplayServer.getScreenCount()
    val darkModeSupported = DisplayServer.isDarkModeSupported()
    val darkMode = DisplayServer.isDarkMode()
    val touchscreenAvailable = DisplayServer.isTouchscreenAvailable()
    val screenKeptOn = DisplayServer.screenIsKeptOn()
    val clipboardSupported = DisplayServer.hasFeature(DisplayServer.FEATURE_CLIPBOARD)
    val clipboardHas = if (clipboardSupported) DisplayServer.clipboardHas() else false
    val clipboardHasImage = if (clipboardSupported) DisplayServer.clipboardHasImage() else false
    val clipboardText = if (clipboardSupported) DisplayServer.clipboardGet() else ""
    val clipboardPrimaryText =
      if (DisplayServer.hasFeature(DisplayServer.FEATURE_CLIPBOARD_PRIMARY)) {
        DisplayServer.clipboardGetPrimary()
      } else {
        ""
      }
    val cursorShape = DisplayServer.cursorGetShape()
    val mouseMode = DisplayServer.mouseGetMode()
    val keyboardFocusScreen = DisplayServer.getKeyboardFocusScreen()
    val swapCancelOk = DisplayServer.getSwapCancelOk()
    val additionalOutputs = DisplayServer.hasAdditionalOutputs()
    val hardwareKeyboard = DisplayServer.hasHardwareKeyboard()
    val windowTransparency = DisplayServer.isWindowTransparencyAvailable()
    val screenDpi = DisplayServer.screenGetDpi()
    val maxScale = DisplayServer.screenGetMaxScale()
    val imeSelection =
      if (DisplayServer.hasFeature(DisplayServer.FEATURE_IME)) {
        DisplayServer.imeGetSelection()
      } else {
        Vector2i(0, 0)
      }
    val imeText =
      if (DisplayServer.hasFeature(DisplayServer.FEATURE_IME)) DisplayServer.imeGetText() else ""
    val tabletDriverCount = DisplayServer.tabletGetDriverCount()
    val tabletCurrentDriver = DisplayServer.tabletGetCurrentDriver()
    val tabletFirstDriver =
      if (tabletDriverCount > 0) {
        DisplayServer.tabletGetDriverName(0)
      } else {
        ""
      }
    val ttsSpeaking = DisplayServer.ttsIsSpeaking()
    val ttsPaused = DisplayServer.ttsIsPaused()
    val ttsVoices = DisplayServer.ttsGetVoicesForLanguage("en")
    val virtualKeyboardHeight =
      if (DisplayServer.hasFeature(DisplayServer.FEATURE_VIRTUAL_KEYBOARD)) {
        DisplayServer.virtualKeyboardGetHeight()
      } else {
        0L
      }
    val activePopup = DisplayServer.windowGetActivePopup()
    val windowInstanceId = DisplayServer.windowGetAttachedInstanceId()
    val windowCurrentScreen = DisplayServer.windowGetCurrentScreen()
    val windowCanDraw = DisplayServer.windowCanDraw()
    val windowFocused = DisplayServer.windowIsFocused()
    val windowMaximizeAllowed = DisplayServer.windowIsMaximizeAllowed()
    val maximizeDblClick = DisplayServer.windowMaximizeOnTitleDblClick()
    val minimizeDblClick = DisplayServer.windowMinimizeOnTitleDblClick()
    val accessibilityScreenReader = DisplayServer.accessibilityScreenReaderActive()
    val accessibilityContrast = DisplayServer.accessibilityShouldIncreaseContrast()
    val accessibilityReduceAnimation = DisplayServer.accessibilityShouldReduceAnimation()
    val accessibilityReduceTransparency = DisplayServer.accessibilityShouldReduceTransparency()
    val windowMaxSize = DisplayServer.windowGetMaxSize()
    val windowMinSize = DisplayServer.windowGetMinSize()
    val windowPosition = DisplayServer.windowGetPosition()
    val windowPositionDecorated = DisplayServer.windowGetPositionWithDecorations()
    val windowSize = DisplayServer.windowGetSize()
    val windowSizeDecorated = DisplayServer.windowGetSizeWithDecorations()
    val keyboardLayoutCount = DisplayServer.keyboardGetLayoutCount()
    val keyboardCurrentLayout = DisplayServer.keyboardGetCurrentLayout()
    val keyboardLayoutName =
      if (keyboardLayoutCount > 0) {
        DisplayServer.keyboardGetLayoutName(keyboardCurrentLayout.coerceAtLeast(0))
      } else {
        ""
      }
    val keyboardLayoutLanguage =
      if (keyboardLayoutCount > 0) {
        DisplayServer.keyboardGetLayoutLanguage(keyboardCurrentLayout.coerceAtLeast(0))
      } else {
        ""
      }
    val mousePosition =
      if (DisplayServer.hasFeature(DisplayServer.FEATURE_MOUSE)) {
        DisplayServer.mouseGetPosition()
      } else {
        Vector2i(0, 0)
      }
    val mouseButtons =
      if (DisplayServer.hasFeature(DisplayServer.FEATURE_MOUSE)) {
        DisplayServer.mouseGetButtonState()
      } else {
        0L
      }
    System.err.println(
      "[kanama:kt] HelloScript(file)._ready health=$health speed=$speed label=$label difficulty=$smokeDifficulty"
    )
    System.err.println(
      "[kanama:kt] Mathf lerp=$mathLerp clamp=$mathClamp wrap=$mathWrap approx=$mathApprox round=$mathRound lerpf=$mathLerpF clampf=$mathClampF sinf=$mathSinF sqrtf=$mathSqrtF"
    )
    System.err.println("[kanama:kt] Generated name constants ok=$generatedNameConstantsOk")
    System.err.println("[kanama:kt] ProjectSettings string_list=${listSetting.joinToString("|")}")
    System.err.println(
      "[kanama:kt] ProjectSettings dictionary name=${dictionarySetting["name"]} " +
        "enabled=${dictionarySetting["enabled"]} count=${dictionarySetting["count"]} " +
        "scale=${dictionarySetting["scale"]}"
    )
    System.err.println(
      "[kanama:kt] ResourceLoader exists=$scriptExists has_hello=${rootEntries.contains("HelloScript.kt")} " +
        "loaded_path_len=${loadedScriptPath.length} loaded_is_script=$loadedScriptIsScript " +
        "loaded_ref_count=$loadedScriptRefCount loaded_name_len=${loadedScriptName.length} " +
        "loaded_scene_id_len=${loadedScriptSceneId.length} loaded_path_id_len=${loadedScriptPathId.length} " +
        "loaded_built_in=$loadedScriptBuiltIn loaded_local_to_scene=$loadedScriptLocalToScene " +
        "threaded_request=$threadedRequestError threaded_status_before=$threadedStatusBeforeGet " +
        "threaded_status_after=$threadedStatusAfterGet threaded_packed=$threadedResourceIsPackedScene " +
        "threaded_path_len=$threadedResourcePathLen " +
        "generated_scene_id_len=${generatedSceneUniqueId.length} " +
        "packed_scene_pack_error=$packedScenePackError packed_scene_can=$packedSceneCanInstantiate " +
        "packed_scene_instance_body=$packedSceneInstanceIsBody " +
        "packed_scene_instance_children=$packedSceneInstanceChildren " +
        "duplicate_is_script=$duplicatedScriptIsScript duplicate_path_len=$duplicatedScriptPathLen " +
        "deep_duplicate_is_script=$deepDuplicatedScriptIsScript " +
        "save_ext_has_kt=${saveExtensions.contains("kt")} " +
        "save_error=$saveError save_exists=$saveExists save_has_class=$saveHasClass " +
        "save_uid_set_error=$saveUidSetError save_cleanup_error=$saveCleanupError " +
        "cached_path_len=${cachedScriptPath.length} " +
        "cached_is_script=$cachedScriptIsScript cached_ref_count=$cachedScriptRefCount"
    )
    System.err.println("[kanama:kt] ResourceSaver script_uid=$scriptUid")
    System.err.println("[kanama:kt] Script property replay object_set_amount=$pendingSetAmount")
    System.err.println(
      "[kanama:kt] FileAccess exists=$fileExists size_positive=${fileSize > 0} has_class=$sourceHasClass"
    )
    System.err.println(
      "[kanama:kt] FileAccess metadata modified_positive=${fileModified > 0} " +
        "accessed_nonnegative=${fileAccessed >= 0} md5_len=${fileMd5.length} " +
        "sha256_len=${fileSha256.length} permissions=$filePermissions " +
        "hidden=$fileHidden read_only=$fileReadOnly xattrs=${fileExtendedAttrs.size}"
    )
    System.err.println(
      "[kanama:kt] FileAccess instance path_len=${fileOpenPath.length} abs_path_len=${fileOpenAbsolutePath.length} " +
        "is_open=$fileOpenIsOpen position=$fileOpenPosition length_matches=${fileOpenLength == fileSize} " +
        "eof=$fileOpenEof first_line_len=${fileOpenLine.length} text_has_class=${fileOpenText.contains("class HelloScript")} " +
        "error=$fileOpenError"
    )
    System.err.println(
      "[kanama:kt] FileAccess handle open_present=$directFilePresent open_is_open=$directFileOpen " +
        "path_len=$directFilePathLen line_len=$directFileLineLen text_has_class=$directFileTextHasClass " +
        "temp_present=$tempFilePresent temp_open=$tempFileOpen temp_path_len=$tempFilePathLen temp_store=$tempFileStoreOk"
    )
    System.err.println(
      "[kanama:kt] FileAccess primitive byte_positive=${fileByte > 0} word_nonnegative=${fileWord >= 0} " +
        "dword_nonnegative=${fileDword >= 0} qword_nonnegative=${fileQword >= 0} " +
        "float_text_len=$fileFloatTextLength double_text_len=$fileDoubleTextLength " +
        "half_text_len=$fileHalfTextLength real_text_len=$fileRealTextLength " +
        "big_endian=$fileBigEndian csv_cols=$fileCsvColumns"
    )
    System.err.println(
      "[kanama:kt] FileAccess write_fixture string_ok=$fileWriteStringOk " +
        "string_text=$fileWriteStringText line_ok=$fileWriteLineOk " +
        "line_text=${fileWriteLineText.trim()} resize_error=$fileResizeError " +
        "resize_text=$fileResizeText cleanup_error=$fileWriteCleanupError"
    )
    System.err.println(
      "[kanama:kt] FileAccess byte_fixture source_bytes_positive=${fileSourceBytes.isNotEmpty()} " +
        "buffer_len=${fileBuffer.size} write_bytes_ok=$fileWriteBytesOk " +
        "written_len=${fileWrittenBytes.size} first=${fileWrittenBytes.firstOrNull()?.toInt()} " +
        "second=${fileWrittenBytes.getOrNull(1)?.toInt()} cleanup_error=$fileBytesCleanupError"
    )
    System.err.println(
      "[kanama:kt] FileAccess numeric_fixture write8=$fileWrite8Ok read8=$fileRead8 " +
        "write16=$fileWrite16Ok be16=${fileRead16Bytes.joinToString("-")} " +
        "write32=$fileWrite32Ok read32=$fileRead32 write64=$fileWrite64Ok read64=$fileRead64 " +
        "write_double=$fileWriteDoubleOk read_double=$fileReadDouble " +
        "write_float=$fileWriteFloatOk read_float=$fileReadFloat " +
        "write_half=$fileWriteHalfOk read_half_text_len=${fileReadHalf.toString().length} " +
        "write_real=$fileWriteRealOk read_real_text_len=${fileReadReal.toString().length} " +
        "cleanup_error=$fileNumericCleanupError"
    )
    System.err.println(
      "[kanama:kt] FileAccess string_fixture pascal_write=$filePascalWriteOk pascal_read=$filePascalRead " +
        "pascal_cleanup=$filePascalCleanupError csv_write=$fileCsvWriteOk " +
        "csv_cols=${fileCsvRead.size} csv_first=${fileCsvRead.firstOrNull()} " +
        "csv_end_matches=${fileCsvEndPosition == fileCsvSize} csv_cleanup=$fileCsvCleanupError " +
        "var_write=$fileVarWriteOk var_read=$fileVarRead var_cleanup=$fileVarCleanupError"
    )
    System.err.println(
      "[kanama:kt] FileAccess attr_fixture xattr_string_set=$fileAttrStringSetError " +
        "xattr_string_read=$fileAttrStringRead xattr_list_has=${fileAttrListAfterString.contains(fileAttrStringName)} " +
        "xattr_bytes_set=$fileAttrBytesSetError xattr_bytes_len=${fileAttrBytesRead.size} " +
        "xattr_string_remove=$fileAttrStringRemoveError xattr_bytes_remove=$fileAttrBytesRemoveError " +
        "hidden_set=$fileAttrHiddenSetError hidden=$fileAttrHidden hidden_reset=$fileAttrHiddenResetError " +
        "readonly_set=$fileAttrReadOnlySetError readonly=$fileAttrReadOnly " +
        "readonly_reset=$fileAttrReadOnlyResetError permissions_set=$fileAttrPermissionsSetError " +
        "permissions=$fileAttrPermissions cleanup_error=$fileAttrCleanupError"
    )
    System.err.println(
      "[kanama:kt] Node self class=$selfClass is_node=$selfIsNode instance_positive=${selfInstanceId > 0} " +
        "queued=$selfQueued inside_tree=$selfInsideTree child_count=$selfChildCount index=$selfIndex " +
        "part_edited=$selfPartOfEditedScene scene_path_len=${selfSceneFilePath.length} " +
        "tree_string_len=${selfTreeString.length} " +
        "can_process=$selfCanProcess processing=$selfProcessing physics_processing=$selfPhysicsProcessing " +
        "process_delta_nonnegative=${selfProcessDelta >= 0.0} " +
        "physics_delta_nonnegative=${selfPhysicsDelta >= 0.0} tostring_len=${selfToString.length} " +
        "selfas_match=$selfAsHelperMatches"
    )
    System.err.println(
      "[kanama:kt] Node lookup has_dot=$selfHasDotNode dot_matches=$selfDotMatches " +
        "missing_null=${selfMissingNode == null} parent_is_node=$selfParentIsNode " +
        "owner_class_len=$selfOwnerClassLen"
    )
    System.err.println(
      "[kanama:kt] Node3D body found=$bodyFound target_path=${targetPath.path} " +
        "target_path_tscn=$targetPathFromTscn pos=${bodyPosition.x},${bodyPosition.y},${bodyPosition.z} " +
        "translated=${bodyTranslated.x},${bodyTranslated.y},${bodyTranslated.z} " +
        "global=${bodyGlobalPosition.x},${bodyGlobalPosition.y},${bodyGlobalPosition.z} " +
        "rot_y=${bodyRotationDegrees.y} scale=${bodyScale.x},${bodyScale.y},${bodyScale.z} " +
        "hidden=$bodyHidden visible=$bodyVisible"
    )
    System.err.println(
      "[kanama:kt] CharacterBody3D velocity=${bodyVelocity.x},${bodyVelocity.y},${bodyVelocity.z} " +
        "moved=$bodyMoveAndSlide real_len=${bodyRealVelocity.toString().length} " +
        "delta_len=${bodyPositionDelta.toString().length} up_y=${bodyUpDirection.y} " +
        "floor=$bodyOnFloor wall=$bodyOnWall ceiling=$bodyOnCeiling"
    )
    System.err.println(
      "[kanama:kt] CollisionObject3D layer=$bodyCollisionLayer mask=$bodyCollisionMask " +
        "ray_pickable=$bodyRayPickable priority=$bodyCollisionPriority"
    )
    System.err.println(
      "[kanama:kt] CollisionShape3D found=$collisionShapeFound disabled=$collisionShapeDisabled " +
        "enabled_after_reset=$collisionShapeEnabled fill=$collisionShapeDebugFill " +
        "color=${collisionShapeDebugColor.r},${collisionShapeDebugColor.g},${collisionShapeDebugColor.b}," +
        "${collisionShapeDebugColor.a} shape_box=$assignedShapeIsBox " +
        "shape_size=${assignedBoxSize.x},${assignedBoxSize.y},${assignedBoxSize.z} " +
        "shape_margin=$assignedShapeMargin shape_bias=$assignedShapeBias"
    )
    System.err.println(
      "[kanama:kt] Camera3D found=$cameraFound current=$cameraCurrent fov=$cameraFov " +
        "near=$cameraNear far=$cameraFar projection=$cameraProjection cull_mask=$cameraCullMask"
    )
    System.err.println(
      "[kanama:kt] RayCast3D found=$rayFound enabled=$rayEnabled target=${rayTarget.x},${rayTarget.y},${rayTarget.z} " +
        "mask=$rayMask bodies=$rayBodies areas=$rayAreas colliding=$rayColliding " +
        "point_len=${rayPoint.toString().length} normal_len=${rayNormal.toString().length}"
    )
    System.err.println(
      "[kanama:kt] Area3D found=$areaFound monitoring=$areaMonitoring monitorable=$areaMonitorable " +
        "gravity=$areaGravity gravity_y=${areaGravityDirection.y} priority=$areaPriority " +
        "bodies=$areaHasBodies areas=$areaHasAreas body_count=$areaBodyCount " +
        "area_count=$areaAreaCount deferred_set=$areaDeferredSet"
    )
    System.err.println(
      "[kanama:kt] StaticBody3D found=$staticBodyFound " +
        "linear=${staticLinear.x},${staticLinear.y},${staticLinear.z} " +
        "angular=${staticAngular.x},${staticAngular.y},${staticAngular.z}"
    )
    System.err.println(
      "[kanama:kt] AudioStreamPlayer3D found=$audioFound paused=$audioPaused volume=$audioVolume " +
        "pitch=$audioPitch max_distance=$audioMaxDistance stream_null=$audio3dStreamNull " +
        "playing_before=$audioPlayingBefore " +
        "playing_after_stop=$audioPlayingAfterStop"
    )
    System.err.println(
      "[kanama:kt] AudioStreamPlayer found=$audio2dFound paused=$audio2dPaused volume=$audio2dVolume " +
        "linear=$audio2dVolumeLinear pitch=$audio2dPitch bus=$audio2dBus autoplay=$audio2dAutoplay " +
        "polyphony=$audio2dPolyphony position=$audio2dPlaybackPosition stream_null=$audio2dStreamNull " +
        "playing_before=$audio2dPlayingBefore " +
        "playing_after_stop=$audio2dPlayingAfterStop"
    )
    System.err.println(
      "[kanama:kt] AnimationPlayer found=$animationFound active=$animationActive " +
        "deterministic=$animationDeterministic mixer_process=$animationProcessMode " +
        "mixer_method=$animationMethodMode mixer_discrete=$animationDiscreteMode " +
        "polyphony=$animationPolyphony root_local=$animationRootLocal " +
        "root_pos_len=${animationRootMotionPosition.toString().length} " +
        "root_scale_len=${animationRootMotionScale.toString().length} " +
        "root_pos_acc_len=${animationRootMotionPositionAccumulator.toString().length} " +
        "root_scale_acc_len=${animationRootMotionScaleAccumulator.toString().length}"
    )
    System.err.println(
      "[kanama:kt] AnimationPlayer playback blend=$animationDefaultBlend auto_capture=$animationAutoCapture " +
        "auto_duration=$animationAutoCaptureDuration playing=$animationPlaying " +
        "animation_active=$animationAnimationActive speed_scale=$animationSpeedScale " +
        "playing_speed=$animationPlayingSpeed movie_quit=$animationMovieQuit " +
        "current_len=${animationCurrentName.length} assigned_len=${animationAssignedName.length} " +
        "position=$animationCurrentPosition length=$animationCurrentLength has_section=$animationHasSection " +
        "section_start=$animationSectionStart section_end=$animationSectionEnd " +
        "process=$animationPlayerProcessMode method=$animationPlayerMethodMode"
    )
    System.err.println(
      "[kanama:kt] MeshInstance3D found=$meshFound layer_mask=$meshLayerMask sorting=$meshSortingOffset " +
        "sorting_aabb=$meshSortingAabb shadows=$meshCastShadows lod=$meshLodBias " +
        "transparency=$meshTransparency visibility=$meshVisibilityBegin,$meshVisibilityEnd " +
        "fade=$meshVisibilityFade extra_cull=$meshExtraCull lightmap_texel=$meshLightmapTexelScale " +
        "ignore_occlusion=$meshIgnoreOcclusion surfaces=$meshSurfaceOverrideMaterialCount " +
        "blend_shapes=$meshBlendShapeCount mesh_box=$assignedMeshIsBox " +
        "mesh_size=${assignedMeshSize.x},${assignedMeshSize.y},${assignedMeshSize.z} " +
        "mesh_subdivide=$assignedMeshSubdivideWidth,$assignedMeshSubdivideHeight,$assignedMeshSubdivideDepth " +
        "mesh_flip=$assignedMeshFlipFaces mesh_uv2=$assignedMeshAddUv2 mesh_uv2_padding=$assignedMeshUv2Padding"
    )
    System.err.println(
      "[kanama:kt] Material3D override=$materialOverrideIsStandard overlay=$materialOverlayIsStandard " +
        "surface=$surfaceMaterialIsStandard albedo=${assignedMaterialAlbedo.r},${assignedMaterialAlbedo.g}," +
        "${assignedMaterialAlbedo.b},${assignedMaterialAlbedo.a} metallic=$assignedMaterialMetallic " +
        "roughness=$assignedMaterialRoughness shading=$assignedMaterialShading " +
        "transparency=$assignedMaterialTransparency cull=$assignedMaterialCull priority=$assignedMaterialPriority"
    )
    System.err.println(
      "[kanama:kt] Particles3D gpu_present=${gpuParticles != null} gpu_amount=$gpuParticlesAmount " +
        "gpu_lifetime=$gpuParticlesLifetime gpu_one_shot=$gpuParticlesOneShot " +
        "gpu_pre=$gpuParticlesPreProcess gpu_explosive=$gpuParticlesExplosiveness " +
        "gpu_random=$gpuParticlesRandomness gpu_fps=$gpuParticlesFixedFps " +
        "gpu_fractional=$gpuParticlesFractional gpu_speed=$gpuParticlesSpeedScale " +
        "gpu_draw=$gpuParticlesDrawOrder gpu_emitting=$gpuParticlesEmitting " +
        "cpu_present=${cpuParticles != null} cpu_amount=$cpuParticlesAmount " +
        "cpu_lifetime=$cpuParticlesLifetime cpu_one_shot=$cpuParticlesOneShot " +
        "cpu_pre=$cpuParticlesPreProcess cpu_explosive=$cpuParticlesExplosiveness " +
        "cpu_random=$cpuParticlesRandomness cpu_fps=$cpuParticlesFixedFps " +
        "cpu_fractional=$cpuParticlesFractional cpu_speed=$cpuParticlesSpeedScale " +
        "cpu_draw=$cpuParticlesDrawOrder cpu_emitting=$cpuParticlesEmitting"
    )
    System.err.println(
      "[kanama:kt] Timer found=$timerFound wait=$timerWaitTime one_shot=$timerOneShot " +
        "autostart=$timerAutostart paused=$timerPaused ignore_time_scale=$timerIgnoreTimeScale " +
        "process=$timerProcessCallback time_left_positive=${timerTimeLeftBeforeStop > 0.0} " +
        "stopped_before=$timerStoppedBeforeStop stopped_after=$timerStoppedAfterStop"
    )
    System.err.println(
      "[kanama:kt] SceneTreeTimer class=$sceneTreeTimerClass ref_count_positive=${sceneTreeTimerRefCount > 0} " +
        "time_left=$sceneTreeTimerTimeLeft"
    )
    System.err.println(
      "[kanama:kt] Tween class=$tweenClass ref_count_positive=${tweenRefCount > 0} " +
        "valid_before=$tweenValidBefore prop_class=${tweenProperty?.getClassName().orEmpty()} " +
        "callback_class=${tweenCallback?.getClassName().orEmpty()} interval_class=${tweenInterval?.getClassName().orEmpty()} " +
        "await_class=${tweenAwaitTweener?.getClassName().orEmpty()} await_finished=$tweenAwaitFinished " +
        "step=$tweenStep elapsed_nonnegative=${tweenElapsed >= 0.0} running_after_step=$tweenRunningAfterStep " +
        "loops_left=$tweenLoopsLeft priority_after_step=$tweenPriorityAfterStep processed_before_kill=$processedTweensBeforeKill " +
        "valid_after_kill=$tweenValidAfterKill"
    )
    System.err.println(
      "[kanama:kt] Node controls ready=$selfReady in_group=$selfInGroup group_removed=$selfGroupRemoved " +
        "group_set=${sceneTreeGroupSetDescription == "kanama scene tree group"} " +
        "group_flags=${sceneTreeGroupFlagsDescription == "kanama scene tree flags"} " +
        "processing_after_set=$selfProcessingAfterSet " +
        "physics_processing_after_set=$selfPhysicsProcessingAfterSet processing_input=$selfProcessingInput " +
        "shortcut_input=$selfProcessingShortcutInput unhandled_input=$selfProcessingUnhandledInput " +
        "unhandled_key_input=$selfProcessingUnhandledKeyInput " +
        "multiplayer_authority=$selfMultiplayerAuthority is_multiplayer_authority=$selfIsMultiplayerAuthority"
    )
    System.err.println(
      "[kanama:kt] Node scalar_controls process_priority=$selfProcessPriority " +
        "physics_process_priority=$selfPhysicsProcessPriority displayed_folded=$selfDisplayedFolded " +
        "unique_name=$selfUniqueNameInOwner editor_description_len=${selfEditorDescription.length} " +
        "tree_node_count_positive=${selfTreeNodeCount > 0}"
    )
    System.err.println(
      "[kanama:kt] Vector helpers v3_len=$vector3Length v3_norm=${vector3Normalized.x}," +
        "${vector3Normalized.y},${vector3Normalized.z} v3_dot=$vector3Dot " +
        "v3_cross=${vector3Cross.x},${vector3Cross.y},${vector3Cross.z} " +
        "v3_lerp=${vector3Lerp.x},${vector3Lerp.y},${vector3Lerp.z} " +
        "v3_limited=${vector3Limited.x},${vector3Limited.y},${vector3Limited.z} " +
        "v3_distance=$vector3Distance v2_len=$vector2Length v2_angle=$vector2Angle " +
        "v2_lerp=${vector2Lerp.x},${vector2Lerp.y} " +
        "v3_withx=${vector3WithX.x},${vector3WithX.y},${vector3WithX.z} " +
        "v3_withy=${vector3WithY.x},${vector3WithY.y},${vector3WithY.z} " +
        "v3_withz=${vector3WithZ.x},${vector3WithZ.y},${vector3WithZ.z} " +
        "v2_withx=${vector2WithX.x},${vector2WithX.y} " +
        "v2_withy=${vector2WithY.x},${vector2WithY.y}"
    )
    System.err.println(
      "[kanama:kt] Node process_modes mode=$selfProcessMode thread_group=$selfProcessThreadGroup " +
        "thread_messages=$selfProcessThreadMessages thread_order=$selfProcessThreadGroupOrder " +
        "internal=$selfProcessingInternal physics_internal=$selfPhysicsProcessingInternal " +
        "physics_interp_mode=$selfPhysicsInterpolationMode physics_interp=$selfPhysicsInterpolated " +
        "physics_interp_enabled=$selfPhysicsInterpolatedEnabled auto_translate=$selfAutoTranslateMode " +
        "can_auto_translate=$selfCanAutoTranslate scene_load_flag=$selfScenePlaceholder " +
        "scene_load_flag_reset=$selfScenePlaceholderAfterReset"
    )
    System.err.println(
      "[kanama:kt] Object introspection can_revert_name=$objectCanRevertName " +
        "missing_meta=$objectHasMissingMeta missing_user_signal=$objectHasMissingUserSignal " +
        "has_queue_free=$objectHasQueueFree queue_free_args=$objectQueueFreeArgs " +
        "has_script_changed=$objectHasScriptChanged script_changed_connections=$objectHasScriptChangedConnections " +
        "signal_connect=$objectSignalConnectError signal_callback=$objectSignalCallbackClass " +
        "signal_lambda=$objectSignalLambdaClass " +
        "script_signal_connect=$scriptSignalConnectError script_signal_callback=$scriptSignalCallback " +
        "coroutine_started=$smokeCoroutineStarted " +
        "blocking=$objectBlockingSignals blocking_after_reset=$objectBlockingSignalsAfterReset " +
        "translate_disabled=$objectCanTranslateDisabled translate_enabled=$objectCanTranslateEnabled"
    )
    System.err.println(
      "[kanama:kt] Object call autoload_present=${autoload != null} describe=$autoloadDescribe " +
        "add=$autoloadAdd negate=$autoloadNegate object=$autoloadObjectClass " +
        "returned=$autoloadReturnedObjectClass resource=$autoloadResourceClass " +
        "v2=${autoloadVector2.x},${autoloadVector2.y} " +
        "v3=${autoloadVector3.x},${autoloadVector3.y},${autoloadVector3.z} " +
        "color=${autoloadColor.r},${autoloadColor.g},${autoloadColor.b},${autoloadColor.a} " +
        "quat=${autoloadQuaternion.x},${autoloadQuaternion.y},${autoloadQuaternion.z},${autoloadQuaternion.w} " +
        "v4=${autoloadVector4.x},${autoloadVector4.y},${autoloadVector4.z},${autoloadVector4.w} " +
        "rect2=${autoloadRect2.position.x},${autoloadRect2.position.y},${autoloadRect2.size.x},${autoloadRect2.size.y} " +
        "aabb=${autoloadAABB.position.x},${autoloadAABB.position.y},${autoloadAABB.position.z},${autoloadAABB.size.x},${autoloadAABB.size.y},${autoloadAABB.size.z} " +
        "plane=${autoloadPlane.normal.x},${autoloadPlane.normal.y},${autoloadPlane.normal.z},${autoloadPlane.d} " +
        "basis=${autoloadBasis.x.x},${autoloadBasis.y.y},${autoloadBasis.z.z} " +
        "t3d=${autoloadTransform3D.origin.x},${autoloadTransform3D.origin.y},${autoloadTransform3D.origin.z} " +
        "t2d=${autoloadTransform2D.origin.x},${autoloadTransform2D.origin.y} " +
        "proj=${autoloadProjection.x.x},${autoloadProjection.w.x},${autoloadProjection.w.y},${autoloadProjection.w.z},${autoloadProjection.w.w} " +
        "v2i=${autoloadVector2i.x},${autoloadVector2i.y} " +
        "v3i=${autoloadVector3i.x},${autoloadVector3i.y},${autoloadVector3i.z} " +
        "v4i=${autoloadVector4i.x},${autoloadVector4i.y},${autoloadVector4i.z},${autoloadVector4i.w} " +
        "rect2i=${autoloadRect2i.position.x},${autoloadRect2i.position.y},${autoloadRect2i.size.x},${autoloadRect2i.size.y} " +
        "np_described=$autoloadNodePath"
    )
    System.err.println(
      "[kanama:kt] UI wrappers ui_present=${uiRoot != null} pos=${uiPosition.x},${uiPosition.y} " +
        "size=${uiSize.x},${uiSize.y} min=${uiMinSize.x},${uiMinSize.y} mouse_filter=$uiMouseFilter " +
        "visible_before=$uiVisibleBefore hidden=$uiVisibleHidden shown=$uiVisibleShown " +
        "label=$labelText button=$buttonText toggle=$buttonToggle pressed=$buttonPressed " +
        "disabled=$buttonDisabled focus_mode=$buttonFocusMode focused=$buttonFocused"
    )
    System.err.println(
      "[kanama:kt] UI metadata option_item=$optionItemMetadata option_selected=$optionSelectedMetadata " +
        "option_id=$optionSelectedId tab_count=$tabCount tab_title=$tabTitle tab_metadata=$tabMetadata " +
        "line_bidi_options=$lineEditBidiOptionsSize"
    )
    System.err.println(
      "[kanama:kt] Dynamic UI label=$dynamicLabelText button=$dynamicButtonText " +
        "label_pos=${dynamicLabelPosition.x},${dynamicLabelPosition.y} " +
        "button_pos=${dynamicButtonPosition.x},${dynamicButtonPosition.y} child_count=$dynamicUiChildCount"
    )
    System.err.println(
      "[kanama:kt] OS granted_permissions=${grantedPermissions.size} memory_info_keys=${memoryInfo.size}"
    )
    System.err.println(
      "[kanama:kt] Engine singletons count=${singletonList.size} has_os=${singletonList.contains("OS")} " +
        "version_major=${engineVersionInfo["major"]} version_minor=${engineVersionInfo["minor"]} " +
        "author_keys=${engineAuthorInfo.size} donor_keys=${engineDonorInfo.size} license_keys=${engineLicenseInfo.size} " +
        "copyright_entries=${engineCopyrightInfo.size} backtraces=$engineBacktraceCount"
    )
    System.err.println(
      "[kanama:kt] Input joypads count=${connectedJoypads.size} joy_info_keys=${joyInfo.size}"
    )
    System.err.println(
      "[kanama:kt] Time dictionaries system_dt_year=${systemDateTime["year"]} " +
        "system_date_month=${systemDate["month"]} system_time_hour=${systemTime["hour"]} " +
        "unix_dt_year=${unixDateTime["year"]} unix_date_month=${unixDate["month"]} " +
        "unix_time_hour=${unixTime["hour"]} parsed_weekday=${parsedDateTime["weekday"]} " +
        "time_zone_keys=${timeZone.size}"
    )
    System.err.println(
      "[kanama:kt] DirAccess has_hello=${dirFiles.contains("HelloScript.kt")} " +
        "has_addons=${dirDirectories.contains("addons")} drive_count=$driveCount"
    )
    System.err.println(
      "[kanama:kt] DirAccess instance file_exists=$dirFileExists dir_exists=$dirDirExists " +
        "current_drive=$dirCurrentDrive current_dir_len=${dirCurrentDir.length} space_left=$dirSpaceLeft " +
        "fs_type_len=${dirFilesystemType.length} is_link=$dirIsLink read_link_len=${dirReadLink.length} " +
        "is_bundle=$dirIsBundle case_sensitive=$dirCaseSensitive equivalent=$dirEquivalent"
    )
    System.err.println(
      "[kanama:kt] DirAccess handle open_present=$directDirPresent file_exists=$directDirFileExists " +
        "current_dir_len=$directDirCurrentLen files_has_hello=$directDirFilesHasHello " +
        "temp_present=$tempDirPresent temp_current_dir_len=$tempDirCurrentLen"
    )
    System.err.println(
      "[kanama:kt] DirAccess list_controls files_has_hello=${dirFilesWithOptions.contains("HelloScript.kt")} " +
        "dirs_has_addons=${dirDirectoriesWithOptions.contains("addons")} include_hidden=$dirIncludeHidden " +
        "include_nav=$dirIncludeNavigational entries_has_hello=${dirEntries.any { it.name == "HelloScript.kt" && !it.isDirectory }}"
    )
    System.err.println(
      "[kanama:kt] DirAccess write_fixture make_error=$dirMakeError make_exists=$dirMakeExists " +
        "make_cleanup_error=$dirMakeCleanupError recursive_error=$dirRecursiveError " +
        "recursive_exists=$dirRecursiveExists recursive_nested_cleanup_error=$dirRecursiveNestedCleanupError " +
        "recursive_cleanup_error=$dirRecursiveCleanupError copy_error=$dirCopyError " +
        "copy_exists=$dirCopyExists copy_has_class=$dirCopyHasClass rename_error=$dirRenameError " +
        "rename_exists=$dirRenameExists rename_old_missing=$dirRenameOldMissing " +
        "rename_has_class=$dirRenameHasClass rename_cleanup_error=$dirRenameCleanupError"
    )
    System.err.println(
      "[kanama:kt] DirAccess instance_write change_error=$dirChangeError make_error=$dirInstanceMakeError " +
        "make_exists=$dirInstanceMakeExists recursive_error=$dirInstanceRecursiveError " +
        "recursive_exists=$dirInstanceRecursiveExists nested_cleanup_error=$dirInstanceNestedCleanupError " +
        "cleanup_error=$dirInstanceCleanupError copy_error=$dirInstanceCopyError " +
        "copy_exists=$dirInstanceCopyExists rename_error=$dirInstanceRenameError " +
        "rename_exists=$dirInstanceRenameExists rename_old_missing=$dirInstanceRenameOldMissing " +
        "rename_cleanup_error=$dirInstanceRenameCleanupError"
    )
    System.err.println("[kanama:kt] DisplayServer name=$displayName screen_count=$screenCount")
    System.err.println(
      "[kanama:kt] DisplayServer env dark_supported=$darkModeSupported dark=$darkMode " +
        "touch=$touchscreenAvailable kept_on=$screenKeptOn keyboard_layouts=$keyboardLayoutCount " +
        "keyboard_current=$keyboardCurrentLayout"
    )
    System.err.println(
      "[kanama:kt] DisplayServer input mouse=${mousePosition.x},${mousePosition.y} " +
        "buttons=$mouseButtons keyboard_name_len=${keyboardLayoutName.length} " +
        "keyboard_lang_len=${keyboardLayoutLanguage.length}"
    )
    System.err.println(
      "[kanama:kt] DisplayServer passive clipboard=$clipboardHas image=$clipboardHasImage " +
        "clipboard_len=${clipboardText.length} primary_len=${clipboardPrimaryText.length} " +
        "cursor=$cursorShape mouse_mode=$mouseMode keyboard_focus=$keyboardFocusScreen " +
        "swap_cancel=$swapCancelOk additional_outputs=$additionalOutputs hardware_keyboard=$hardwareKeyboard " +
        "window_transparency=$windowTransparency dpi=$screenDpi max_scale=$maxScale " +
        "ime_selection=${imeSelection.x},${imeSelection.y} ime_text_len=${imeText.length} " +
        "tablet_drivers=$tabletDriverCount tablet_current_len=${tabletCurrentDriver.length} " +
        "tablet_first_len=${tabletFirstDriver.length}"
    )
    System.err.println(
      "[kanama:kt] DisplayServer tts speaking=$ttsSpeaking paused=$ttsPaused " +
        "voices=${ttsVoices.size} vk_height=$virtualKeyboardHeight active_popup=$activePopup " +
        "window_instance=$windowInstanceId window_screen=$windowCurrentScreen can_draw=$windowCanDraw " +
        "focused=$windowFocused maximize_allowed=$windowMaximizeAllowed " +
        "max_dbl=$maximizeDblClick min_dbl=$minimizeDblClick"
    )
    System.err.println(
      "[kanama:kt] DisplayServer accessibility screen_reader=$accessibilityScreenReader " +
        "contrast=$accessibilityContrast reduce_animation=$accessibilityReduceAnimation " +
        "reduce_transparency=$accessibilityReduceTransparency window_max=${windowMaxSize.x},${windowMaxSize.y} " +
        "window_min=${windowMinSize.x},${windowMinSize.y} window_pos=${windowPosition.x},${windowPosition.y} " +
        "window_pos_decorated=${windowPositionDecorated.x},${windowPositionDecorated.y} " +
        "window_size=${windowSize.x},${windowSize.y} " +
        "window_size_decorated=${windowSizeDecorated.x},${windowSizeDecorated.y}"
    )
  }

  @OnEnterTree
  fun enterTree() {
    System.err.println("[kanama:kt] HelloScript(file)._enter_tree")
  }

  @OnExitTree
  fun exitTree() {
    System.err.println("[kanama:kt] HelloScript(file)._exit_tree")
  }

  @OnProcess
  fun process(delta: Double) {
    // Intentionally quiet: per-frame logs flood the editor output.
  }

  @RegisterFunction
  fun greet(name: String): String {
    val msg = "Hello from script file, $name! health=$health"
    System.err.println("[kanama:kt] HelloScript(file).greet(\"$name\") -> \"$msg\"")
    return msg
  }

  @RegisterFunction
  fun onSmokeBodyEntered(body: Node) {
    smokeSignalBodyClass = body.getClassName()
  }

  @RegisterFunction
  fun onSmokeChecked(message: String) {
    smokeScriptSignalMessage = message
  }

  @RegisterFunction
  @Rpc(callLocal = true)
  fun replaceSmokeScene(): Boolean {
    smokeScene = PackedScene.create()
    return smokeScene != null
  }
}
