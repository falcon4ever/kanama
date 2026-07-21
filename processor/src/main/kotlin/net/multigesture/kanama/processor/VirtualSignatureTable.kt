package net.multigesture.kanama.processor

/**
 * Canonical engine-virtual signatures, loaded from the generated `virtual-signatures.tsv` resource
 * (see `scripts/generate_virtual_signature_table.py`, derived from `extension_api.json`).
 *
 * Used by the processor to resolve and validate `@OverrideVirtual` declarations against a script's
 * attach-to class hierarchy. Resolution walks the class inheritance chain so a script attached to
 * e.g. `Sprite2D` can override `CanvasItem._draw`.
 */
internal object VirtualSignatureTable {

  /** One engine virtual's canonical signature (Godot type strings). */
  data class Sig(
    val owner: String,
    val virtualName: String,
    /** Godot arg type strings, in order. Empty for no-arg virtuals. */
    val argTypes: List<String>,
    /** Godot return type string, or `null` for `void` virtuals. */
    val returnType: String?,
  )

  private const val RESOURCE = "/net/multigesture/kanama/processor/virtual-signatures.tsv"

  // class -> parent
  private val inherits: Map<String, String>
  // class -> (virtualName -> Sig)
  private val virtualsByClass: Map<String, Map<String, Sig>>

  init {
    val inh = HashMap<String, String>()
    val virt = HashMap<String, HashMap<String, Sig>>()
    val stream =
      VirtualSignatureTable::class.java.getResourceAsStream(RESOURCE)
        ?: error("virtual-signatures.tsv resource not found on classpath ($RESOURCE)")
    stream.bufferedReader().useLines { lines ->
      for (line in lines) {
        if (line.isEmpty() || line.startsWith("#")) continue
        val f = line.split('\t')
        when (f[0]) {
          "I" -> if (f.size >= 3) inh[f[1]] = f[2]
          "V" ->
            if (f.size >= 3) {
              val owner = f[1]
              val name = f[2]
              val argTypes = if (f.size >= 4 && f[3].isNotEmpty()) f[3].split(',') else emptyList()
              val ret = if (f.size >= 5 && f[4].isNotEmpty()) f[4] else null
              virt.getOrPut(owner) { HashMap() }[name] = Sig(owner, name, argTypes, ret)
            }
        }
      }
    }
    inherits = inh
    virtualsByClass = virt
  }

  /**
   * Resolve [virtualName] for a script attached to [attachTo], walking the inheritance chain
   * (attach-to class first, then ancestors). Returns the canonical [Sig] or `null` if no class in
   * the chain declares it.
   */
  fun resolve(attachTo: String, virtualName: String): Sig? {
    var cls: String? = attachTo
    val seen = HashSet<String>()
    while (cls != null && seen.add(cls)) {
      virtualsByClass[cls]?.get(virtualName)?.let {
        return it
      }
      cls = inherits[cls]
    }
    return null
  }
}
