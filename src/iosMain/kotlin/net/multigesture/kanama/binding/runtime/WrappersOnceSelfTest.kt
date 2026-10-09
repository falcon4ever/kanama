package net.multigesture.kanama.binding.runtime

import net.multigesture.kanama.api.AudioStreamPlayer
import net.multigesture.kanama.api.AudioStreamWAV
import net.multigesture.kanama.api.BoxMesh
import net.multigesture.kanama.api.BoxShape3D
import net.multigesture.kanama.api.Engine
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
  check(
    "project-settings(globalizePath(res://) non-empty, getSettingDouble(gravity) > 0)",
    ProjectSettings.globalizePath("res://").isNotEmpty() &&
      ProjectSettings.getSettingDouble("physics/3d/default_gravity") > 0.0,
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
    check(
      "audio-stream-player(volumeDb -6.5, pitchScale 1.25, streamPaused, maxPolyphony 3 round-trip)",
      player.getVolumeDb() == -6.5 &&
        player.pitchScale == 1.25 &&
        player.getStreamPaused() &&
        player.maxPolyphony == 3 &&
        !player.isPlaying(),
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

  check("wrappers-once(no kanama_ios_fault raised)", ObjectCalls.faultCount() == faultsBefore)
}

private fun <T : RefCounted> newRef(className: String, wrap: (GodotHandle) -> T): T =
  RefCounted.owned(wrap(GodotHandle(ObjectCalls.constructObject(className))))
