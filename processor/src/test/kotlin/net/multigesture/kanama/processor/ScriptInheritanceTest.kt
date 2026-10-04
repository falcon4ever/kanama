package net.multigesture.kanama.processor

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import net.multigesture.kanama.processor.ScriptInheritance.Declaration

/**
 * Task 133 C — a script class's members include its superclasses' (`ScriptInheritance`): one member
 * per signature, the most derived declaration wins, annotations follow the override, conflicts are
 * errors. The desktop, iOS and Web emitters all build from the resulting model; the example
 * project's `InheritanceSmokeChild.kt` (`scripts/runtime_smoke.sh`) runs the same rules in Godot.
 */
class ScriptInheritanceTest {

  private fun decl(
    key: String,
    annotated: Boolean = false,
    isPrivate: Boolean = false,
    ref: String,
  ) = Declaration(key, isPrivate, annotated, ref)

  @Test
  fun inheritedMembersNeedNoForwarding() {
    // class Motorcycle : Vehicle — the subclass declares nothing of Vehicle's.
    val members =
      ScriptInheritance.members(
        listOf(
          listOf(decl("effectBody(kotlin.Double)", ref = "Motorcycle.effectBody")),
          listOf(
            decl("ready()", annotated = true, ref = "Vehicle.ready"),
            decl("physicsProcess(kotlin.Double)", annotated = true, ref = "Vehicle.physicsProcess"),
            decl("effectBody(kotlin.Double)", ref = "Vehicle.effectBody"),
            decl("getVehiclePosition()", ref = "Vehicle.getVehiclePosition"),
          ),
        )
      )
    assertEquals(
      listOf(
        "Motorcycle.effectBody",
        "Vehicle.ready",
        "Vehicle.physicsProcess",
        "Vehicle.getVehiclePosition",
      ),
      members.map { it.declaration },
    )
    assertEquals(listOf(0, 1, 1, 1), members.map { it.level })
    assertEquals(listOf(false, true, true, false), members.map { it.annotated })
  }

  @Test
  fun anUnannotatedOverrideKeepsTheBaseAnnotations() {
    // Base: @OnReady open fun ready(); Child: override fun ready() { super.ready(); ... }
    val member =
      ScriptInheritance.members(
          listOf(
            listOf(decl("ready()", ref = "Child.ready")),
            listOf(decl("ready()", annotated = true, ref = "Base.ready")),
          )
        )
        .single()
    assertEquals("Child.ready", member.declaration, "Godot calls the override")
    assertEquals("Base.ready", member.annotationSource, "...as the base's _ready handler")
    assertTrue(member.annotated)
  }

  @Test
  fun anAnnotatedOverrideWins() {
    // Child: @GodotName("tick") override fun step() over Base: @GodotName("step_once") fun step()
    val member =
      ScriptInheritance.members(
          listOf(
            listOf(decl("step()", annotated = true, ref = "Child.step")),
            listOf(decl("step()", annotated = true, ref = "Base.step")),
            listOf(decl("step()", annotated = true, ref = "Root.step")),
          )
        )
        .single()
    assertEquals("Child.step", member.annotationSource)
  }

  @Test
  fun theNearestAnnotatedAncestorWinsThroughAPlainMiddleClass() {
    val member =
      ScriptInheritance.members(
          listOf(
            listOf(decl("f()", ref = "C.f")),
            listOf(decl("f()", ref = "B.f")),
            listOf(decl("f()", annotated = true, ref = "A.f")),
          )
        )
        .single()
    assertEquals("C.f", member.declaration)
    assertEquals("A.f", member.annotationSource)
  }

  @Test
  fun aSuperclassPrivateMemberIsNotInherited() {
    val members =
      ScriptInheritance.members(
        listOf(
          listOf(decl("helper()", isPrivate = true, ref = "Child.helper")),
          listOf(
            decl("helper()", isPrivate = true, annotated = true, ref = "Base.helper"),
            decl("secret", isPrivate = true, annotated = true, ref = "Base.secret"),
          ),
        )
      )
    // The class's own private member stays (the registration rules report it); the base's do not.
    assertEquals(listOf("Child.helper"), members.map { it.declaration })
    assertEquals(false, members.single().annotated)
  }

  @Test
  fun duplicateExportedNamesAreErrors() {
    val errors =
      ScriptInheritance.duplicatePropertyErrors(
        "Child",
        listOf("speed" to "topSpeed", "speed" to "Base.speed", "jump" to "jump"),
      )
    assertEquals(1, errors.size)
    assertTrue(errors.single().contains("topSpeed and Base.speed"))
    assertTrue(errors.single().contains("'speed'"))
  }

  @Test
  fun twoHandlersOfOneVirtualAcrossTheChainAreAnError() {
    // Base: @OnReady fun ready(); Child: @OnReady fun setup() — both `_ready`.
    val errors =
      FunctionRegistration.duplicateNameErrors(
        "Child",
        listOf("_ready" to "setup", "_ready" to "Base.ready"),
      )
    assertTrue(errors.single().contains("setup and Base.ready"), errors.single())
  }
}
