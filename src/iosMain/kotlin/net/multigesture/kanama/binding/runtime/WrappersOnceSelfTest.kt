package net.multigesture.kanama.binding.runtime

import net.multigesture.kanama.api.AudioStreamPlayer
import net.multigesture.kanama.api.AudioStreamWAV
import net.multigesture.kanama.api.BoxMesh
import net.multigesture.kanama.api.BoxShape3D
import net.multigesture.kanama.api.DirAccess
import net.multigesture.kanama.api.Engine
import net.multigesture.kanama.api.FileAccess
import net.multigesture.kanama.api.GodotError
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.Image
import net.multigesture.kanama.api.ImageTexture
import net.multigesture.kanama.api.Mesh
import net.multigesture.kanama.api.Node
import net.multigesture.kanama.api.NoiseTexture2D
import net.multigesture.kanama.api.PackedScene
import net.multigesture.kanama.api.ParticleProcessMaterial
import net.multigesture.kanama.api.PlaceholderTexture2D
import net.multigesture.kanama.api.ProceduralSkyMaterial
import net.multigesture.kanama.api.ProjectSettings
import net.multigesture.kanama.api.RefCounted
import net.multigesture.kanama.api.ResourceLoader
import net.multigesture.kanama.api.ResourceSaver
import net.multigesture.kanama.api.ShaderMaterial
import net.multigesture.kanama.api.SurfaceTool
import net.multigesture.kanama.api.castOrNull
import net.multigesture.kanama.builtins.md5Text
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3
import platform.posix.usleep

/**
 * Task 129 C rows of the OBJECTCALLS SELFTEST frame-1 phase: one representative member of each
 * class whose iOS path is new now that it is generated once (it was hand-written on iOS, or
 * hand-written on desktop and missing on iOS). Each row asserts a value a call that never ran
 * cannot produce: a round trip through a setter and its getter, a resource saved and loaded back,
 * or an engine fact checked two ways. The frame-1 phase runs with the SceneTree up, so the threaded
 * load and the Node-derived player are safe here. Nothing may raise a shim fault.
 */
internal fun wrappersOnceSelfTestRows(check: (String, Boolean) -> Unit) {
  val faultsBefore = ObjectCalls.faultCount()

  // ParticleProcessMaterial: the 71 Kotlin properties iOS lacked, through the generated
  // set_param_min / set_param_max / set_turbulence_enabled / set_particle_flag helpers.
  newRef("ParticleProcessMaterial", ::ParticleProcessMaterial).use { ppm ->
    ppm.angleMin = 12.5
    ppm.angleMax = 40.0
    ppm.turbulenceEnabled = true
    ppm.setParticleFlag(ParticleProcessMaterial.ParticleFlags.ROTATE_Y, true)
    check(
      "particle-process-material(angleMin/angleMax 12.5/40, turbulence, ROTATE_Y round-trip)",
      ppm.angleMin == 12.5 &&
        ppm.angleMax == 40.0 &&
        ppm.turbulenceEnabled &&
        ppm.getParticleFlag(ParticleProcessMaterial.ParticleFlags.ROTATE_Y),
    )
  }

  // ProceduralSkyMaterial.skyCover (iOS gained it) holds the texture it was given; BoxShape3D /
  // BoxMesh go through generated bodies instead of the iOS hand copies, and BoxShape3D gains
  // fromResource.
  newRef("PlaceholderTexture2D", ::PlaceholderTexture2D).use { cover ->
    cover.setSize(Vector2(8.0, 4.0))
    newRef("ProceduralSkyMaterial", ::ProceduralSkyMaterial).use { sky ->
      sky.skyCover = cover
      val back = sky.skyCover
      check(
        "procedural-sky-material(skyCover round-trip -> same texture, width 8)",
        back != null && back.instanceId == cover.instanceId && back.getWidth() == 8,
      )
      back?.close()
    }
  }
  BoxShape3D.create().use { shape ->
    shape.size = Vector3(1.0, 2.0, 3.0)
    val back = BoxShape3D.fromResource(shape)
    check(
      "box-shape-3d(size round-trip, fromResource -> the same shape)",
      shape.size == Vector3(1.0, 2.0, 3.0) && back?.instanceId == shape.instanceId,
    )
    back?.close()
  }
  BoxMesh.create().use { mesh ->
    mesh.size = Vector3(2.0, 3.0, 4.0)
    mesh.subdivideWidth = 3
    check(
      "box-mesh(size + subdivideWidth Int round-trip)",
      mesh.size == Vector3(2.0, 3.0, 4.0) && mesh.subdivideWidth == 3,
    )
  }

  // ImageTexture.createFromImage: Godot's static, through the generated static dispatch.
  val image = Image.createEmpty(4, 2, false, Image.Format.RGBA8)
  if (image != null) {
    image.use {
      val texture = ImageTexture.createFromImage(it)
      check(
        "image-texture(createFromImage(4x2) -> 4x2)",
        texture != null && texture.getWidth() == 4 && texture.getHeight() == 2,
      )
      texture?.close()
    }
  } else check("image-texture(Image.createEmpty returned null)", false)

  // SurfaceTool.commit() with the generated `existing = null` default, and the downcasts iOS
  // gained on ShaderMaterial / NoiseTexture2D (fromObject).
  SurfaceTool.create().use { tool ->
    tool.begin(Mesh.PrimitiveType.TRIANGLES)
    tool.addVertex(Vector3(0.0, 0.0, 0.0))
    tool.addVertex(Vector3(1.0, 0.0, 0.0))
    tool.addVertex(Vector3(0.0, 1.0, 0.0))
    val mesh = tool.commit()
    check("surface-tool(commit() default -> one surface)", mesh?.getSurfaceCount() == 1)
    mesh?.close()
  }
  newRef("ShaderMaterial", ::ShaderMaterial).use { material ->
    val viaObject = ShaderMaterial.fromObject(GodotObject(material.handle))
    check(
      "shader-material(fromObject -> the same material)",
      viaObject?.instanceId == material.instanceId,
    )
    viaObject?.close()
  }
  newRef("NoiseTexture2D", ::NoiseTexture2D).use { noise ->
    noise.setWidth(48)
    noise.setGenerateMipmaps(false)
    val viaObject = NoiseTexture2D.fromObject(GodotObject(noise.handle))
    check(
      "noise-texture-2d(setWidth 48, generateMipmaps false, fromObject -> the same texture)",
      noise.getWidth() == 48 &&
        !noise.isGeneratingMipmaps() &&
        viaObject?.instanceId == noise.instanceId,
    )
    viaObject?.close()
  }

  // ProjectSettings (iOS had only getSettingDouble) and Engine (iOS had five members): the same
  // engine fact read through both, and the generated getSetting Variant path.
  val ticks = Engine.getPhysicsTicksPerSecond()
  check(
    "project-settings(getSettingLong(physics_ticks_per_second) == Engine.getPhysicsTicksPerSecond)",
    ticks > 0 &&
      ProjectSettings.hasSetting("physics/common/physics_ticks_per_second") &&
      ProjectSettings.getSettingLong("physics/common/physics_ticks_per_second") == ticks.toLong(),
  )
  // globalizePath: a user:// path is always a real directory. res:// is NOT: an exported game
  // reads its resources from the .pck, where Godot's ProjectSettings::globalize_path("res://...")
  // has no filesystem root to substitute and returns the path with the "res://" prefix stripped
  // (the empty string for "res://" itself), so res:// is reported, never asserted non-empty.
  val userDir = ProjectSettings.globalizePath("user://")
  val resDir = ProjectSettings.globalizePath("res://")
  val gravity = ProjectSettings.getSettingDouble("physics/3d/default_gravity")
  println(
    "[kanama][ios][kn] OBJECTCALLS SELFTEST (frame 1) INFO: project-settings " +
      "globalizePath(user://)='$userDir' globalizePath(res://)='$resDir' " +
      "getSettingDouble(physics/3d/default_gravity)=$gravity"
  )
  checkParts(
    check,
    "project-settings(globalizePath(user://) absolute, getSettingDouble(gravity) > 0)",
    "globalizePath(user://)" to (userDir.startsWith("/") to userDir),
    "getSettingDouble(physics/3d/default_gravity) > 0" to ((gravity > 0.0) to gravity.toString()),
  )
  check(
    "engine(getVersionInfo major == 4, getMainLoop is the SceneTree)",
    (Engine.getVersionInfo()["major"] as? Number)?.toLong() == 4L &&
      Engine.getMainLoop()?.isClass("SceneTree") == true,
  )

  // Engine.registerSingleton: the generated METHOD_PRECONDITIONS guard rejects a RefCounted before
  // Godot sees it; an Object-derived singleton registers and unregisters.
  newRef("RefCounted", ::RefCounted).use { counted ->
    val rejected =
      runCatching { Engine.registerSingleton("KanamaSelfTestRefCounted", counted) }
        .exceptionOrNull()
    check(
      "engine(registerSingleton(RefCounted) -> IllegalArgumentException, not registered)",
      rejected is IllegalArgumentException && !Engine.hasSingleton("KanamaSelfTestRefCounted"),
    )
  }
  val singletonSegment = ObjectCalls.constructObject("Object")
  if (singletonSegment.address() != 0L) {
    val singleton = GodotObject(GodotHandle(singletonSegment))
    Engine.registerSingleton("KanamaSelfTestSingleton", singleton)
    val registered =
      Engine.hasSingleton("KanamaSelfTestSingleton") &&
        Engine.getSingleton("KanamaSelfTestSingleton")?.instanceId == singleton.instanceId
    Engine.unregisterSingleton("KanamaSelfTestSingleton")
    check(
      "engine(registerSingleton(Object) -> registered, then unregistered)",
      registered && !Engine.hasSingleton("KanamaSelfTestSingleton"),
    )
    ObjectCalls.destroyObject(singletonSegment)
  } else check("engine(registerSingleton) (Object construct returned 0)", false)

  // AudioStreamPlayer: the generated setters replace the six iOS C shim entry points (a scalar
  // float is a double at the ptrcall boundary: the old inaudible-audio bug), and iOS gains the
  // getters. setStreamFromPath goes through ResourceLoader.loadAudioStream.
  val audioPath = "user://kanama_129c_selftest_stream.tres"
  val audioSaved =
    newRef("AudioStreamWAV", ::AudioStreamWAV).use { wav ->
      wav.mixRate = 22050
      ResourceSaver.save(wav, audioPath) == GodotError.OK
    }
  val playerSegment = ObjectCalls.constructObject("AudioStreamPlayer")
  if (playerSegment.address() != 0L) {
    val player = AudioStreamPlayer(GodotHandle(playerSegment))
    player.setVolumeDb(-6.5)
    player.pitchScale = 1.25
    player.setStreamPaused(true)
    player.maxPolyphony = 3
    player.setAutoplay(true)
    // Godot derives get_stream_paused from the live playbacks, and a player with no stream has
    // none: it reads false after set_stream_paused(true) on every platform (the desktop
    // runtime_smoke pins `paused=false`). The bool ARGUMENT path is proved by autoplay instead.
    val volumeDb = player.getVolumeDb()
    val pitchScale = player.pitchScale
    val streamPaused = player.getStreamPaused()
    val maxPolyphony = player.maxPolyphony
    val autoplay = player.isAutoplayEnabled()
    val playing = player.isPlaying()
    println(
      "[kanama][ios][kn] OBJECTCALLS SELFTEST (frame 1) INFO: audio-stream-player " +
        "volumeDb=$volumeDb pitchScale=$pitchScale streamPaused=$streamPaused " +
        "maxPolyphony=$maxPolyphony autoplay=$autoplay playing=$playing"
    )
    checkParts(
      check,
      "audio-stream-player(volumeDb -6.5, pitchScale 1.25, maxPolyphony 3, autoplay round-trip; streamPaused false without a stream)",
      "volumeDb == -6.5" to ((volumeDb == -6.5) to volumeDb.toString()),
      "pitchScale == 1.25" to ((pitchScale == 1.25) to pitchScale.toString()),
      "maxPolyphony == 3" to ((maxPolyphony == 3) to maxPolyphony.toString()),
      "autoplay == true" to (autoplay to autoplay.toString()),
      "streamPaused == false (no stream, no playback)" to
        (!streamPaused to streamPaused.toString()),
      "playing == false" to (!playing to playing.toString()),
    )
    player.setStreamFromPath(audioPath)
    val stream = player.getStream()
    check(
      "audio-stream-player(setStreamFromPath(saved WAV) -> getStream mixRate 22050)",
      audioSaved && stream != null && AudioStreamWAV.fromHandle(stream.handle)?.mixRate == 22050,
    )
    stream?.close()
    player.setStream(null)
    ObjectCalls.destroyObject(playerSegment)
  } else check("audio-stream-player(construct returned 0)", false)

  // ResourceLoader: the typed loaders now go through the generated `load` bind (they used the C
  // shim's resource_loader_load), and loadThreadedGet through a generated ptrcall: the old
  // Variant-call path returned a PackedScene whose instantiate() yielded null on the device.
  val texturePath = "user://kanama_129c_selftest_texture.tres"
  val textureSaved =
    newRef("PlaceholderTexture2D", ::PlaceholderTexture2D).use { texture ->
      texture.setSize(Vector2(16.0, 8.0))
      ResourceSaver.save(texture, texturePath) == GodotError.OK
    }
  val loadedTexture = ResourceLoader.loadTexture2D(texturePath, ResourceLoader.CacheMode.IGNORE)
  check(
    "resource-loader(loadTexture2D(saved 16x8) -> 16x8, exists)",
    textureSaved &&
      loadedTexture?.getSize() == Vector2(16.0, 8.0) &&
      ResourceLoader.exists(texturePath),
  )
  loadedTexture?.close()

  val scenePath = "user://kanama_129c_selftest_scene.tscn"
  val rootSegment = ObjectCalls.constructObject("Node")
  var sceneSaved = false
  if (rootSegment.address() != 0L) {
    val root = Node(GodotHandle(rootSegment))
    root.setName("KanamaSelfTestScene")
    PackedScene.create().use { packed ->
      sceneSaved =
        packed.pack(root) == GodotError.OK && ResourceSaver.save(packed, scenePath) == GodotError.OK
    }
    ObjectCalls.destroyObject(rootSegment)
  }
  val requested =
    ResourceLoader.loadThreadedRequest(
      scenePath,
      "PackedScene",
      false,
      ResourceLoader.CacheMode.IGNORE,
    )
  // Poll (bounded, ~5 s) until LOADED: Godot reports progress 1.0 for a loaded resource, so a
  // progress read-back that returned nothing or a default cannot pass as 1.0.
  var progress = ResourceLoader.loadThreadedGetStatusWithProgress(scenePath)
  var polls = 0
  while (progress.status != ResourceLoader.ThreadLoadStatus.LOADED && polls++ < 500) {
    usleep(10_000u)
    progress = ResourceLoader.loadThreadedGetStatusWithProgress(scenePath)
  }
  val scene = ResourceLoader.loadThreadedGetPackedScene(scenePath)
  val instance = scene?.instantiate()
  check(
    "resource-loader(threaded load of a saved scene -> instantiate() named KanamaSelfTestScene)",
    sceneSaved &&
      requested == GodotError.OK &&
      progress.status == ResourceLoader.ThreadLoadStatus.LOADED &&
      progress.progress == 1.0 &&
      instance?.getName() == "KanamaSelfTestScene",
  )
  instance?.let { ObjectCalls.destroyObject(it.segment) }
  scene?.close()

  // A loaded texture read as a PackedScene: closed, reported as a Godot error, returned as null
  // (the typed loaders check the class, like GDScript's typed assignment).
  val mismatchRequested =
    ResourceLoader.loadThreadedRequest(
      texturePath,
      "Texture2D",
      false,
      ResourceLoader.CacheMode.IGNORE,
    )
  val mismatch = ResourceLoader.loadThreadedGetPackedScene(texturePath)
  check(
    "resource-loader(loadThreadedGetPackedScene of a texture -> null, not a mis-wrapped PackedScene)",
    textureSaved && mismatchRequested == GodotError.OK && mismatch == null,
  )
  mismatch?.close()

  fileAccessRows(check)

  check("wrappers-once(no kanama_ios_fault raised)", ObjectCalls.faultCount() == faultsBefore)
}

/**
 * Task 129 D: FileAccess and DirAccess are generated RefCounted classes with companion statics on
 * every platform. iOS had 11 FileAccess members and no DirAccess before; these rows go through the
 * generated instance methods, the statics and the owned `open()` result (closed by `use { }`).
 */
private fun fileAccessRows(check: (String, Boolean) -> Unit) {
  val dir = "user://kanama_selftest_files"
  val path = "$dir/notes.txt"
  val made = DirAccess.makeDirRecursiveAbsolute(dir)
  val wrote =
    FileAccess.open(path, FileAccess.ModeFlags.WRITE)?.use { file ->
      file.storeString("alpha") && file.storeLine("beta")
    } ?: false
  val read =
    FileAccess.open(path, FileAccess.ModeFlags.READ)?.use { file ->
      Triple(file.getLength(), file.getLine(), file.getAsText())
    }
  checkParts(
    check,
    "file-access(open user:// WRITE storeString+storeLine, use{} closes; READ getLength 10, getLine alphabeta, getAsText)",
    "makeDir" to ((made == GodotError.OK) to "$made"),
    "write" to (wrote to "$wrote"),
    "length" to ((read?.first == 10L) to "${read?.first}"),
    "line" to ((read?.second == "alphabeta") to "${read?.second}"),
    "text" to ((read?.third == "alphabeta\n") to "${read?.third}"),
  )

  val missing = FileAccess.open("$dir/missing.txt", FileAccess.ModeFlags.READ)
  val openError = FileAccess.getOpenError()
  checkParts(
    check,
    "file-access(open of a missing file -> null, getOpenError ERR_FILE_NOT_FOUND)",
    "null" to ((missing == null) to "$missing"),
    "error" to ((openError == GodotError.ERR_FILE_NOT_FOUND) to "$openError"),
  )
  missing?.close()

  val md5 = FileAccess.getMd5(path)
  checkParts(
    check,
    "file-access(statics fileExists, getFileAsString, getFileAsBytes 10, getSize 10, getMd5 == md5Text)",
    "exists" to (FileAccess.fileExists(path) to "${FileAccess.fileExists(path)}"),
    "string" to
      ((FileAccess.getFileAsString(path) == "alphabeta\n") to FileAccess.getFileAsString(path)),
    "bytes" to
      ((FileAccess.getFileAsBytes(path).size == 10) to "${FileAccess.getFileAsBytes(path).size}"),
    "size" to ((FileAccess.getSize(path) == 10L) to "${FileAccess.getSize(path)}"),
    "md5" to ((md5 == "alphabeta\n".md5Text()) to md5),
  )

  val walked = mutableListOf<String>()
  val listed =
    DirAccess.open(dir)?.use { d ->
      if (d.listDirBegin() == GodotError.OK) {
        while (true) {
          val name = d.getNext()
          if (name.isEmpty()) break
          walked += name
        }
        d.listDirEnd()
      }
      d.getFiles() to d.fileExists("notes.txt")
    }
  checkParts(
    check,
    "dir-access(open -> getFiles, listDirBegin/getNext walk and DirAccess.getFilesAt all list notes.txt)",
    "getFiles" to ((listed?.first == listOf("notes.txt")) to "${listed?.first}"),
    "fileExists" to ((listed?.second == true) to "${listed?.second}"),
    "walk" to (("notes.txt" in walked) to "$walked"),
    "getFilesAt" to
      ((DirAccess.getFilesAt(dir) == listOf("notes.txt")) to "${DirAccess.getFilesAt(dir)}"),
  )

  fileAccessCloseRows(check, path)
  fileAccessIoRows(check, dir)
  cursorReadRows(check, dir)

  val removedFile = DirAccess.removeAbsolute(path)
  val removedDir = DirAccess.removeAbsolute(dir)
  checkParts(
    check,
    "dir-access(removeAbsolute file and dir -> OK, dirExistsAbsolute false after)",
    "file" to ((removedFile == GodotError.OK) to "$removedFile"),
    "dir" to ((removedDir == GodotError.OK) to "$removedDir"),
    "gone" to
      DirAccess.dirExistsAbsolute(dir).let { exists -> !exists to "dirExistsAbsolute=$exists" },
  )
}

/**
 * Task 129 D: `FileAccess.close()` closes the file as GDScript's `file.close()` does, also through
 * a borrowed `fromHandle` view and while a second reference (`castOrNull`) keeps the object alive.
 */
private fun fileAccessCloseRows(check: (String, Boolean) -> Unit, path: String) {
  val owner = FileAccess.open(path, FileAccess.ModeFlags.WRITE)
  owner?.storeString("gamma")
  owner?.let { FileAccess.fromHandle(it.handle) }?.close()
  val viewClosed = owner?.isOpen()
  val viewText = FileAccess.getFileAsString(path)
  owner?.close()
  checkParts(
    check,
    "file-access(close() on a borrowed fromHandle view closes the file: owner isOpen false, write flushed)",
    "opened" to ((owner != null) to "$owner"),
    "isOpen" to ((viewClosed == false) to "$viewClosed"),
    "text" to ((viewText == "gamma") to viewText),
  )

  val first = FileAccess.open(path, FileAccess.ModeFlags.WRITE)
  first?.storeString("delta")
  val second = first?.castOrNull<FileAccess>()
  val refs = first?.getReferenceCount()
  second?.close()
  val secondClosed = first?.isOpen()
  val secondText = FileAccess.getFileAsString(path)
  first?.close()
  checkParts(
    check,
    "file-access(close() while a second reference holds the object closes the file: refs 2, isOpen false, write flushed)",
    "refs" to ((refs == 2) to "$refs"),
    "isOpen" to ((secondClosed == false) to "$secondClosed"),
    "text" to ((secondText == "delta") to secondText),
  )
}

/** Task 129 D: FileAccess members iOS gained (it had 11 of Godot's 68), write then read back. */
private fun fileAccessIoRows(check: (String, Boolean) -> Unit, dir: String) {
  val path = "$dir/io.bin"
  val bytes = byteArrayOf(1, 2, 3, 4)
  FileAccess.open(path, FileAccess.ModeFlags.WRITE)?.use { it.storeBuffer(bytes) }
  val readBytes = FileAccess.open(path, FileAccess.ModeFlags.READ)?.use { it.getBuffer(4L) }
  checkParts(
    check,
    "file-access(storeBuffer/getBuffer round trip of 4 bytes)",
    "bytes" to ((readBytes?.contentEquals(bytes) == true) to "${readBytes?.toList()}"),
  )

  FileAccess.open(path, FileAccess.ModeFlags.WRITE)?.use {
    it.storeVar("variant-smoke")
    it.storeVar(42L)
  }
  val vars = FileAccess.open(path, FileAccess.ModeFlags.READ)?.use { it.getVar() to it.getVar() }
  checkParts(
    check,
    "file-access(storeVar/getVar round trip of a String and an int)",
    "string" to ((vars?.first == "variant-smoke") to "${vars?.first}"),
    "int" to ((vars?.second == 42L) to "${vars?.second}"),
  )

  FileAccess.open(path, FileAccess.ModeFlags.WRITE)?.use {
    it.storeCsvLine(listOf("alpha", "beta"))
  }
  val csv = FileAccess.open(path, FileAccess.ModeFlags.READ)?.use { it.getCsvLine() }
  checkParts(
    check,
    "file-access(storeCsvLine/getCsvLine round trip alpha,beta)",
    "csv" to ((csv == listOf("alpha", "beta")) to "$csv"),
  )

  val temp = FileAccess.createTemp(FileAccess.ModeFlags.WRITE_READ, "kanama", "txt")
  val tempPath = temp?.getPathAbsolute().orEmpty()
  val tempStored = temp?.storeString("temp") ?: false
  temp?.seek(0L)
  val tempText = temp?.getAsText().orEmpty()
  temp?.close()
  checkParts(
    check,
    "file-access(createTemp WRITE_READ: open, path set, storeString then getAsText reads it back)",
    "opened" to ((temp != null) to "$temp"),
    "path" to (tempPath.isNotEmpty() to tempPath),
    "store" to (tempStored to "$tempStored"),
    "text" to ((tempText == "temp") to tempText),
  )

  val compressed = "$dir/compressed.bin"
  val compressedWrote =
    FileAccess.openCompressed(compressed, FileAccess.ModeFlags.WRITE)?.use {
      it.storeString("packed")
    }
  val compressedText =
    FileAccess.openCompressed(compressed, FileAccess.ModeFlags.READ)?.use { it.getAsText() }
  val compressedCleanup = DirAccess.removeAbsolute(compressed)
  checkParts(
    check,
    "file-access(openCompressed WRITE then READ round trip, FASTLZ)",
    "write" to ((compressedWrote == true) to "$compressedWrote"),
    "text" to ((compressedText == "packed") to "$compressedText"),
    "cleanup" to ((compressedCleanup == GodotError.OK) to "$compressedCleanup"),
  )

  val encrypted = "$dir/encrypted.bin"
  val encryptedWrote =
    FileAccess.openEncryptedWithPass(encrypted, FileAccess.ModeFlags.WRITE, "kanama-pass")?.use {
      it.storeString("secret")
    }
  val encryptedText =
    FileAccess.openEncryptedWithPass(encrypted, FileAccess.ModeFlags.READ, "kanama-pass")?.use {
      it.getAsText()
    }
  val encryptedCleanup = DirAccess.removeAbsolute(encrypted)
  checkParts(
    check,
    "file-access(openEncryptedWithPass WRITE then READ round trip)",
    "write" to ((encryptedWrote == true) to "$encryptedWrote"),
    "text" to ((encryptedText == "secret") to "$encryptedText"),
    "cleanup" to ((encryptedCleanup == GodotError.OK) to "$encryptedCleanup"),
  )
  DirAccess.removeAbsolute(path)
}

/**
 * Task 129 D: getters that advance a cursor (`FileAccess.getLine`, `DirAccess.getNext`) run the
 * Godot method once per Kotlin call. The no-arg String read-back used to call it twice (measure,
 * then fill), so each call skipped one line / entry and returned "". Two reads in sequence must
 * return the first and the second value.
 */
private fun cursorReadRows(check: (String, Boolean) -> Unit, dir: String) {
  val path = "$dir/lines.txt"
  FileAccess.open(path, FileAccess.ModeFlags.WRITE)?.use {
    it.storeLine("one")
    it.storeLine("two")
  }
  val lines =
    FileAccess.open(path, FileAccess.ModeFlags.READ)?.use { file ->
      listOf(file.getLine(), file.getLine(), file.getPosition().toString())
    }
  val linesCleanup = DirAccess.removeAbsolute(path)
  checkParts(
    check,
    "file-access(getLine twice reads one then two: one engine call per getLine)",
    "first" to ((lines?.get(0) == "one") to "${lines?.get(0)}"),
    "second" to ((lines?.get(1) == "two") to "${lines?.get(1)}"),
    "position" to ((lines?.get(2) == "8") to "${lines?.get(2)}"),
    "cleanup" to ((linesCleanup == GodotError.OK) to "$linesCleanup"),
  )

  val walkDir = "$dir/walk"
  val made = DirAccess.makeDirRecursiveAbsolute(walkDir)
  FileAccess.open("$walkDir/a.txt", FileAccess.ModeFlags.WRITE)?.use { it.storeString("a") }
  FileAccess.open("$walkDir/b.txt", FileAccess.ModeFlags.WRITE)?.use { it.storeString("b") }
  val walked = mutableListOf<String>()
  DirAccess.open(walkDir)?.use { d ->
    if (d.listDirBegin() == GodotError.OK) {
      while (walked.size < 8) {
        val name = d.getNext()
        if (name.isEmpty()) break
        walked += name
      }
      d.listDirEnd()
    }
  }
  val walkCleanup =
    listOf(
      DirAccess.removeAbsolute("$walkDir/a.txt"),
      DirAccess.removeAbsolute("$walkDir/b.txt"),
      DirAccess.removeAbsolute(walkDir),
    )
  checkParts(
    check,
    "dir-access(getNext walk of two files returns both: one engine call per getNext)",
    "makeDir" to ((made == GodotError.OK) to "$made"),
    "walk" to ((walked.sorted() == listOf("a.txt", "b.txt")) to "$walked"),
    "cleanup" to (walkCleanup.all { it == GodotError.OK } to "$walkCleanup"),
  )
}

private fun <T : RefCounted> newRef(className: String, wrap: (GodotHandle) -> T): T =
  RefCounted.owned(wrap(GodotHandle(ObjectCalls.constructObject(className))))

/**
 * One row made of named sub-checks. Each part is `name to (passed to value read back)`; on a
 * failure the row's label lists every failing part with the value it got, so the device log says
 * which part broke and what it saw.
 */
private fun checkParts(
  check: (String, Boolean) -> Unit,
  label: String,
  vararg parts: Pair<String, Pair<Boolean, String>>,
) {
  val failed = parts.filter { !it.second.first }
  check(
    if (failed.isEmpty()) label
    else label + " :: " + failed.joinToString("; ") { "${it.first} got '${it.second.second}'" },
    failed.isEmpty(),
  )
}
