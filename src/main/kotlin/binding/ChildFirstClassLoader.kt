package net.multigesture.kanama.binding

import java.net.URL
import java.net.URLClassLoader

/** Child-first classloader for reloadable script classes and generated registrars. */
class ChildFirstClassLoader(
  urls: Array<URL>,
  parent: ClassLoader,
  private val childFirstPrefixes: List<String>,
  val reloadId: Long,
) : URLClassLoader(urls, parent) {

  @Volatile
  var closed: Boolean = false
    private set

  override fun loadClass(name: String, resolve: Boolean): Class<*> {
    synchronized(this) {
      var clazz = findLoadedClass(name)
      if (clazz == null && childFirstPrefixes.any { name.startsWith(it) }) {
        clazz =
          try {
            findClass(name)
          } catch (_: ClassNotFoundException) {
            null
          }
      }
      if (clazz == null) {
        clazz = super.loadClass(name, false)
      }
      if (resolve) {
        resolveClass(clazz)
      }
      return clazz
    }
  }

  override fun close() {
    closed = true
    super.close()
  }
}
