package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Task 132: the references a script-property setter takes belong to the owner's property, not to
 * the Kotlin script object. The GC can collect a `KanamaScript` object before its owner dies (the
 * owner link goes weak at refcount 1), and a cleanup that read the Kotlin property values in `free`
 * then released nothing: City-Builder leaked every DataStructure of `structures`. The desktop
 * registrar now wraps each retaining read in `ScriptBridge.retainScriptProperty(owner, name, ...)`,
 * which registers what the read took and releases the property's previous references; the runtime
 * releases the owner's references in `free`. The generated free-path cleanup only closes what the
 * live Kotlin object holds itself.
 */
class ScriptPropertyRetainsEmitTest {
  private fun prop(name: String, type: TypeMapping = TypeMapping.OBJECT) =
    ScriptPropertyModel(kotlinName = name, godotName = name, type = type, isMutable = true)

  private val retaining =
    listOf(
      prop("texture").copy(objectWrapperFqName = "net.multigesture.kanama.api.Texture2D"),
      prop("meshes", TypeMapping.ARRAY)
        .copy(arrayElementWrapperFqName = "net.multigesture.kanama.api.Mesh"),
      prop("item").copy(customScriptFqName = "test.Item", customScriptIsResource = true),
      prop("items", TypeMapping.ARRAY)
        .copy(
          arrayElementCustomScriptFqName = "test.Item",
          arrayElementCustomScriptIsResource = true,
        ),
      prop("mutableItems", TypeMapping.ARRAY)
        .copy(
          arrayElementCustomScriptFqName = "test.Item",
          arrayElementCustomScriptIsResource = true,
          isMutableList = true,
        ),
      prop("itemMap", TypeMapping.DICTIONARY)
        .copy(
          mapKeyKotlinType = "kotlin.String",
          mapValueCustomScriptFqName = "test.Item",
          mapValueCustomScriptIsResource = true,
        ),
      prop("textureMap", TypeMapping.DICTIONARY)
        .copy(
          mapKeyKotlinType = "kotlin.String",
          mapValueWrapperFqName = "net.multigesture.kanama.api.Texture2D",
        ),
    )

  private val borrowing =
    listOf(
      prop("count", TypeMapping.INT),
      prop("label", TypeMapping.STRING),
      // A node reference to another script: the node's lifetime is the tree's, nothing is taken.
      prop("target").copy(customScriptFqName = "test.Target", customScriptIsResource = false),
      prop("targets", TypeMapping.ARRAY)
        .copy(
          arrayElementCustomScriptFqName = "test.Target",
          arrayElementCustomScriptIsResource = false,
        ),
    )

  private fun model(properties: List<ScriptPropertyModel>) =
    ScriptModel(
      simpleName = "RetainFixture",
      fqName = "test.RetainFixture",
      attachTo = "Resource",
      isTool = false,
      isGlobalClass = false,
      properties = properties,
      toolButtons = emptyList(),
      virtuals = emptyList(),
      methods = emptyList(),
      signals = emptyList(),
    )

  private fun desktopSource() =
    ScriptCodeEmitter(model(retaining + borrowing), "RetainFixtureScriptRegistrar").emit()

  @Test
  fun retainingSettersRegisterWhatTheyTookUnderTheProperty() {
    val source = desktopSource()
    for (p in retaining) {
      assertTrue(
        source.contains("ScriptBridge.retainScriptProperty(godotObject, \"${p.godotName}\", {"),
        "${p.godotName}: the setter's read is not registered under its property",
      )
    }
    assertEquals(
      retaining.size,
      Regex("""ScriptBridge\.retainScriptProperty\(""").findAll(source).count(),
      "one registered read per retaining property, none for the others",
    )
    // The read inside the capture is the retaining reader, and its result is what is assigned.
    assertTrue(
      source.contains(
        "ScriptBridge.retainScriptProperty(godotObject, \"items\", { val read = Arena.ofConfined().use { a -> BuiltinTypes.readVariantObjectArrayRetainedHandles("
      )
    )
    assertTrue(source.contains(".toMutableList(); read }"), "MutableList keeps its copy")
    // The assignment runs inside the call: the old references go only once it succeeded.
    assertTrue(source.contains("; read }) { v -> kt.items = v }"))
  }

  @Test
  fun borrowingSettersRegisterNothing() {
    val source = desktopSource()
    for (p in borrowing) {
      assertFalse(
        source.contains("retainScriptProperty(godotObject, \"${p.godotName}\""),
        "${p.godotName} takes no reference and must not replace a registry entry",
      )
    }
  }

  @Test
  fun theKotlinObjectReleasesNoSetterReference() {
    val source = desktopSource()
    // No raw +1 is given back through the Kotlin values any more (the registry owns those).
    assertFalse(
      source.contains("releaseRefCounted"),
      "a setter reference is released by the Kotlin object",
    )
    // The setters close nothing: re-setting releases what the property's previous set took.
    val set = source.substring(source.indexOf("dispatchSet = {"), source.indexOf("dispatchGet = {"))
    assertFalse(set.contains("closeKanamaOwned"), "a setter closes the Kotlin value it replaces")
    // The free path still closes what the live Kotlin object holds itself (a resource the script
    // assigned), but never touches custom script values: those are script objects.
    val cleanup = source.substring(source.indexOf("internal fun cleanupKanamaOwnedProperties("))
    assertTrue(source.contains("cleanup = { cleanupKanamaOwnedProperties(kt) },"))
    assertTrue(cleanup.contains("closeKanamaOwned(\"texture\", kt.texture)"))
    for (name in listOf("item", "items", "mutableItems", "itemMap", "target", "targets")) {
      assertFalse(cleanup.contains("kt.$name)"), "free path reads custom script property $name")
    }
  }

  /**
   * On iOS the references of an object property set are taken by the runtime, not by the generated
   * bridge: the shim's set path hands the objects the property now holds to
   * `KanamaIosRuntime.retainScriptInstancePropertyObjects`, which references the `RefCounted` ones
   * and records them in the same `ScriptPropertyRetains` registry as desktop. The generated setters
   * only keep the delivered handles (a wrapper over each handle, a custom script resolved to its
   * live instance); a reference taken here as well would be released by nobody.
   */
  @Test
  fun iosGeneratedSettersLeaveTheReferencesToTheRuntime() {
    val errors = mutableListOf<String>()
    val source =
      IosScriptCodeEmitter(
          listOf(IosScriptInput(model(retaining + borrowing), "res://RetainFixture.kt")),
          error = { errors += it },
        )
        .registrySource()
    assertTrue(source.contains("override fun setPropertyObjectArray("), "fixture reaches iOS")
    for (taken in listOf("retainHandle", "retainForKotlinWrapper", "reference(", "retained(")) {
      assertFalse(source.contains(taken), "a generated iOS setter takes a reference (`$taken`)")
    }
  }
}
