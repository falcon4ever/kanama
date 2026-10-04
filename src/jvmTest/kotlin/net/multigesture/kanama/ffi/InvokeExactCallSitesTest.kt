package net.multigesture.kanama.ffi

import java.lang.classfile.ClassFile
import java.lang.classfile.instruction.InvokeInstruction
import java.nio.file.Files
import java.util.zip.ZipFile
import kotlin.io.path.extension
import kotlin.io.path.readBytes
import kotlin.io.path.toPath
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.fail

/**
 * Guards the exact downcalls of task 131 item 16. The hot `MethodHandle`s are called with
 * `invokeExact`, whose call-site descriptor must equal the handle's type. Kotlin types a
 * signature-polymorphic call `Object` when its value is used -- the last expression of a lambda or
 * a `try`, or a call whose result is not cast -- and such a site throws `WrongMethodTypeException`
 * the first time it runs, which a desktop build only finds if a smoke happens to reach it. No
 * Kanama downcall takes or returns a plain `Object`, so any `Ljava/lang/Object;` in an
 * `invokeExact` descriptor of the compiled runtime is such a site: keep each `invokeExact` a
 * statement in a block-bodied function, or cast its result to the handle's return type.
 */
class InvokeExactCallSitesTest {
  private data class Site(val owner: String, val method: String, val descriptor: String)

  private fun runtimeClassFiles(): Sequence<Pair<String, ByteArray>> {
    val location = GodotFFI::class.java.protectionDomain.codeSource.location.toURI().toPath()
    return if (Files.isDirectory(location)) {
      Files.walk(location)
        .use { paths -> paths.filter { it.extension == "class" }.toList() }
        .asSequence()
        .map { location.relativize(it).toString() to it.readBytes() }
    } else {
      val entries =
        ZipFile(location.toFile()).use { zip ->
          zip
            .entries()
            .asSequence()
            .filter { it.name.endsWith(".class") }
            .map { it.name to zip.getInputStream(it).readBytes() }
            .toList()
        }
      entries.asSequence()
    }
  }

  private fun invokeExactSites(): List<Site> =
    runtimeClassFiles()
      .flatMap { (name, bytes) ->
        val model = ClassFile.of().parse(bytes)
        model.methods().asSequence().flatMap { method ->
          val code = method.code().orElse(null) ?: return@flatMap emptySequence<Site>()
          code
            .elementList()
            .asSequence()
            .filterIsInstance<InvokeInstruction>()
            .filter {
              it.owner().asInternalName() == "java/lang/invoke/MethodHandle" &&
                it.name().stringValue() == "invokeExact"
            }
            .map {
              Site(name, method.methodName().stringValue(), it.typeSymbol().descriptorString())
            }
        }
      }
      .toList()

  @Test
  fun everyInvokeExactSiteHasAnExactDescriptor() {
    val sites = invokeExactSites()
    // The hot handles of ObjectCalls, ObjectRuntime, GodotStrings and InstanceBindings: if the
    // scan finds none, it is looking in the wrong place, not proving anything.
    assertTrue(sites.size >= 8, "expected the runtime's invokeExact sites, found ${sites.size}")
    val generic = sites.filter { "Ljava/lang/Object;" in it.descriptor }
    if (generic.isNotEmpty()) {
      fail(
        "invokeExact typed with Object (throws WrongMethodTypeException at run time):\n" +
          generic.joinToString("\n") { "  ${it.owner} ${it.method}: ${it.descriptor}" }
      )
    }
  }
}
