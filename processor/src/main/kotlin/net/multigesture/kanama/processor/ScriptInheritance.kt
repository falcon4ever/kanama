package net.multigesture.kanama.processor

/**
 * Task 133 C — a script class's members include its superclasses' (GDScript `extends`).
 *
 * A `@ScriptClass` that extends another class of the same compilation (a `@ScriptClass` base like
 * `Vehicle`, or a plain abstract one) has every exported property, signal, lifecycle handler,
 * `@OverrideVirtual`, tool button and registered public function of the chain, so the subclass
 * declares only what it adds or changes; before 0.5 it had to re-annotate a forwarding override of
 * each one. The rules:
 * 1. **One member per signature.** The most derived declaration is the member (Kotlin dispatch is
 *    virtual, so the generated registrar calling it reaches an override anyway); a superclass's
 *    `private` declaration is not inherited.
 * 2. **Annotations follow the override.** A declaration with its own Kanama annotations uses them
 *    (the subclass wins: `@GodotName("x") override fun f()` renames it, `@OnProcess override fun
 *    tick()` rewires it); one without any keeps the nearest overridden declaration's, so `override
 *    fun ready()` of a base's `@OnReady open fun ready()` is still the `_ready` handler, and the
 *    override of an `@Export open var` is still exported.
 * 3. **Conflicts are build errors.** Two different functions of the chain that land on one Godot
 *    name (a base `@OnReady fun ready()` and a subclass `@OnReady fun setup()`, both `_ready`) or
 *    two exported properties with one Godot name fail with a message naming both, instead of one
 *    silently shadowing the other: override the base member instead.
 *
 * Pure: the processor hands over each class's declarations as [Declaration]s (nearest class first)
 * and builds the models from the [Member]s, so the rules are unit-tested without a compilation.
 */
internal object ScriptInheritance {

  /**
   * One declared function or property of one class of the chain. [key] identifies the member across
   * the chain (a function's name and parameter types, a property's name); [annotated] is whether
   * the declaration carries Kanama annotations of its own.
   */
  data class Declaration<T>(
    val key: String,
    val isPrivate: Boolean,
    val annotated: Boolean,
    val ref: T,
  )

  /**
   * The member: its most derived [declaration], the declaration its annotations come from
   * ([annotated] false when no declaration of the chain has any), and the [level] it is declared
   * on.
   */
  data class Member<T>(
    val declaration: T,
    val annotationSource: T,
    val annotated: Boolean,
    val level: Int,
  )

  /** [levels] = the declarations of the script class (index 0) and of each superclass in order. */
  fun <T> members(levels: List<List<Declaration<T>>>): List<Member<T>> {
    val byKey = LinkedHashMap<String, MutableList<Pair<Int, Declaration<T>>>>()
    levels.forEachIndexed { level, declarations ->
      for (declaration in declarations) {
        if (level > 0 && declaration.isPrivate) continue
        byKey.getOrPut(declaration.key) { mutableListOf() } += level to declaration
      }
    }
    return byKey.values.map { chain ->
      val (level, top) = chain.first()
      val source = chain.firstOrNull { it.second.annotated }?.second
      Member(top.ref, (source ?: top).ref, source != null, level)
    }
  }

  /**
   * Exported properties sharing one Godot property name: [entries] are `godotName to shownName`
   * (`Vehicle.speed` for an inherited one), in declaration order.
   */
  fun duplicatePropertyErrors(owner: String, entries: List<Pair<String, String>>): List<String> =
    entries
      .groupBy({ it.first }, { it.second })
      .filter { (_, names) -> names.size > 1 }
      .map { (godotName, names) ->
        "$owner: the exported properties ${names.joinToString(" and ")} all register as the " +
          "Godot property '$godotName' (a property name is unique on an object). Rename one, " +
          "or give it @Export(name = \"...\")."
      }
}
