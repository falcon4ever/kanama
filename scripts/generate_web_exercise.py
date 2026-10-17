#!/usr/bin/env python3
"""Generate the Web API exercise: one row per Web call shape (task 125 P5).

Every generated Web wrapper member is one `GodotBackendCalls.invoke<Shape>(descriptor, ...)` call, and
the Web backend implements each contract *shape* once (an arm of `WebCommonGodotBackend`, a JS bridge
extern and a GDScript arm the processor emits), so a defect in a shape is shared by every opcode of it.
This script writes one row per shape of `scripts/platform_backend_calls.json` into the web3d smoke
fixture, `web-runtime/src/web3dSmoke/web/kotlin-src/ApiExerciseProbe.kt`, and the report
`docs/reference/generated/web-api-exercise.md`.

A row calls the shape through `GodotBackendCalls` with the descriptor of one of its opcodes, on an
object the web3d scene constructs (a fresh instance, a child of the probe node, the probe itself or a
singleton), and asserts a value against a second path through the engine, the generic tier
(`WebExperimentalGenericCall.callImmediate`, `callv` by name):

- `oracle`: a call with a result; the result equals the generic call of the same method with the same
  arguments on the same object. A result equal to its type's zero is counted **weak** (reported, not
  failed).
- `readback`: a void call; the generic getter reads the row's value back (a queued mutation one frame
  later), and read something else before the call.
- `custom`: a row whose oracle the generic tier cannot be (a signal's handler, a new object per call,
  the engine's seeded random generator): the assertion is spelled out in the row table.
- `reach`: the call returns without an exception (the shapes whose arguments the generic tier cannot
  encode and whose effect nothing reads back).
- `excluded`: not called; listed with the reason.

What is hand-kept is the row table below (`ROWS`): per shape the opcode, the receiver, the arguments
and, where the rule cannot derive it, the check. Everything else (the descriptor, the SPI call and its
slot conversions, the generic oracle call, the getter of a setter, the queued-mutation frame) is
derived from the contract, `SIGNATURES` and `extension_api.json`. `--check` fails when a contract
shape has no row (or two), a row names an opcode of another shape, an argument list does not match the
shape's slots, or a committed output differs from a fresh render.

Usage:
    python3 scripts/generate_web_exercise.py            # rewrite both outputs
    python3 scripts/generate_web_exercise.py --check    # committed == fresh render, gate mode
"""

from __future__ import annotations

import argparse
import json
import sys
from dataclasses import dataclass, field
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))

from generate_web_backend import SIGNATURES  # noqa: E402  (SPI parameter list per call shape)
from generate_web_wrappers import descriptor_name, invoke_name  # noqa: E402
from platform_backend_contract import INITIAL_BACKEND_CALLS, BackendCallPolicy  # noqa: E402

ROOT = Path(__file__).resolve().parents[1]
API_PATH = ROOT / "extension_api.json"
PROBE_PATH = ROOT / "web-runtime/src/web3dSmoke/web/kotlin-src/ApiExerciseProbe.kt"
REPORT_PATH = ROOT / "docs/reference/generated/web-api-exercise.md"
TAG = "[web_exercise]"


class GenerationError(RuntimeError):
    pass


# --------------------------------------------------------------------------------------------------
# The row table (hand-kept). One entry per contract shape.
#
#   op      the opcode the row calls (its descriptor and Godot method come from the contract)
#   recv    Kotlin expression for the receiver (omitted for singleton / utility shapes): `freshNode`,
#           `freshRef`, `freshChild` (a fresh node added under the probe, so it is inside the tree),
#           `self` (the probe, a Node2D in the tree), or a name the `setup` lines bind
#   args    Kotlin expressions, one per SPI slot after the receiver, in API types (Vector3, a
#           GodotObject, ...); the generator converts each to its slot's backend type
#   kind    oracle | readback | custom | reach | excluded (default: oracle for a call with a result,
#           readback for a void one)
#   setup   Kotlin statements before the receiver (the generic tier for state, so the row's shape is
#           the only typed call)
#   oargs   the generic oracle's Godot arguments when they differ from `args`
#   getter / gargs / expect   a readback's getter, its arguments and the value it must read (default:
#           the setter's property getter or `get_X`/`is_X`, all but the last argument, the last one)
#   check   a custom row's Kotlin statements (`result` is the call's result, converted to API types)
#   frames  how many frames later a readback or custom check runs (default 1 for a queued mutation)
#   why     the reason, for every row that is not a plain oracle/readback, and for every opcode pick
# --------------------------------------------------------------------------------------------------

V2 = "Vector2(1.5, -2.5)"
V3 = "Vector3(1.0, 2.0, 3.0)"
COLOR = "Color(0.1, 0.2, 0.3, 0.4)"
TEXT = '"kanama125"'
EXERCISE_ACTION = '"kanama_api_exercise"'

ROWS: list[dict] = [
    # ---- signals first: the emit rows read what the handlers connected here received ----
    dict(
        shape="STRINGNAME_CALLABLE_LONG_RET_LONG", op=22, recv="self",
        args=['"exercise_connect"', "self", '"on_exercise_connect"', "0L"], kind="custom",
        check=['oracle(self, "emit_signal", "exercise_connect", 41L)',
               "check(result == 0L && connectValue == 41L, \"OK and the handler got 41\", \"$result, $connectValue\")"],
        why="a Callable argument has no generic encoding: the connection returns OK and a generic emit reaches its handler",
    ),
    dict(
        shape="STRINGNAME_BOUND_CALLABLE_LONG_RET_LONG", op=34, recv="self",
        args=['"exercise_bound"', "self", '"on_exercise_bound"', "7L", "0L"], kind="custom",
        check=['oracle(self, "emit_signal", "exercise_bound")',
               "check(result == 0L && boundValue == 7L, \"OK and the handler got the bound 7\", \"$result, $boundValue\")"],
        why="a Callable argument has no generic encoding: the bound connection returns OK and a generic emit hands its handler the bound value",
    ),
    dict(
        shape="STRINGNAME_RET_INT", op=35, recv="self", args=['"exercise_none"'], kind="custom",
        check=['check(result == 0L && noneCount == 1L, "OK and one handler call", "$result, $noneCount")'],
        why="an emit returns OK whether or not anything listens: the probe's connected handler counts the emission",
    ),
    dict(
        shape="STRINGNAME_INT_RET_INT", op=8, recv="self", args=['"exercise_int"', "43"], kind="custom",
        check=['check(result == 0L && intValue == 43L, "OK and the handler got 43", "$result, $intValue")'],
        why="an emit returns OK whether or not anything listens: the probe's connected handler reads the payload",
    ),
    dict(
        shape="STRINGNAME_STRING_RET_INT", op=196, recv="self", args=['"exercise_string"', TEXT], kind="custom",
        check=['check(result == 0L && stringValue == "kanama125", "OK and the handler got kanama125", "$result, $stringValue")'],
        why="an emit returns OK whether or not anything listens: the probe's connected handler reads the payload",
    ),
    dict(
        shape="STRINGNAME_VECTOR2I_RET_INT", op=28, recv="self", args=['"exercise_vector2i"', "Vector2i(3, -4)"],
        kind="custom",
        check=['check(result == 0L && vector2iValue == Vector2i(3, -4), "OK and the handler got (3, -4)", "$result, $vector2iValue")'],
        why="an emit returns OK whether or not anything listens: the probe's connected handler reads the payload",
    ),
    dict(
        shape="STRINGNAME_OBJECT_RET_INT", op=148, setup=['val payload = freshNode("Node")'], recv="self",
        args=['"exercise_object"', "payload"], kind="custom",
        check=['check(result == 0L && near(objectValue, payload), "OK and the handler got the payload node", "$result, ${describe(objectValue)}")'],
        why="an emit returns OK whether or not anything listens: the probe's connected handler reads the payload",
    ),
    # ---- scalar arguments and results ----
    dict(
        shape="BOOL_ARG", op=97, recv='freshNode("Node3D")', args=["false"],
        why="Node3D.set_visible: a fresh Node3D is visible, so false reads back as a change",
    ),
    dict(
        shape="BOOL_RET_INT", op=1, setup=['val parent = freshNode("Node")', "repeat(3) { addChild(parent, freshNode(\"Node\")) }"],
        recv="parent", args=["false"],
        why="Node.get_child_count on a fresh node given three children",
    ),
    dict(
        shape="BOOL_RET_HANDLE", op=38, setup=["val tween = tween()"], recv="tween", args=["true"], kind="custom",
        check=['check(near(result, tween) && near(oracle(tween, "set_parallel", true), tween), "the receiver on both paths", describe(result))'],
        why="Tween.set_parallel returns its receiver: both paths must answer the same tween",
    ),
    dict(shape="DOUBLE_ARG", op=316, recv='freshNode("Camera3D")', args=["61.5"],
         why="Camera3D.set_fov on a fresh camera (75 before)"),
    dict(
        shape="DOUBLE_RET_DOUBLE", op=306,
        setup=['val curve = freshRef("Curve")', 'oracle(curve, "add_point", Vector2(0.0, 0.0))', 'oracle(curve, "add_point", Vector2(1.0, 1.0))'],
        recv="curve", args=["0.25"], why="Curve.sample on a fresh two-point curve",
    ),
    dict(
        shape="DOUBLE_RET_HANDLE", op=321, setup=["val sceneTree = tree()"], recv="sceneTree", args=["5.0"], kind="custom",
        check=['check(result != null && near(oracle(result, "get_time_left"), 5.0), "a timer with 5 s left", describe(result))'],
        why="SceneTree.create_timer makes a new timer per call: the generic tier reads the new timer's time left",
    ),
    dict(shape="LONG_ARG", op=115, recv='freshNode("DirectionalLight3D")', args=["1L"],
         why="DirectionalLight3D.set_sky_mode, the first non-default mode"),
    dict(
        shape="LONG_ARG_SINGLETON", op=269, args=["37L"], getter="get_max_fps",
        setup=['val maxFps = oracle(singleton("Engine"), "get_max_fps")',
               'cleanup { oracle(singleton("Engine"), "set_max_fps", maxFps) }'],
        why="Engine.set_max_fps reads back through Engine.get_max_fps; the value read first is set back at the end",
    ),
    dict(
        shape="LONG_BOOL_ARG", op=155, recv='freshNode("StaticBody3D")', args=["3L", "true"],
        why="CollisionObject3D.set_collision_mask_value on a fresh body (mask 1: layer 3 is off)",
    ),
    dict(
        shape="LONG_BOOL_DOUBLE_LONG_DOUBLE_DOUBLE_ARG_SINGLETON", op=326,
        args=["2L", "true", "0.5", "2L", "50.0", "300.0"], kind="reach",
        why="RenderingServer.environment_set_ssao_quality has no getter; the arguments are the project defaults",
    ),
    dict(shape="LONG_DOUBLE_ARG", op=84, recv='freshNode("DirectionalLight3D")', args=["0L", "0.75"],
         why="Light3D.set_param(PARAM_ENERGY) on a fresh light (1.0 before); Light3D is abstract"),
    dict(shape="LONG_RET_BOOL_SINGLETON", op=313, args=["65L"], why="Input.is_key_pressed(KEY_A): nothing presses it"),
    dict(
        shape="LONG_RET_HANDLE", op=133,
        setup=['val parent = freshNode("Node")', "repeat(3) { addChild(parent, freshNode(\"Node\")) }"],
        recv="parent", args=["1L"], why="Node.get_child, a const lookup, on a fresh node given three children",
    ),
    dict(
        shape="NOARGS_RET_BOOL", op=139, recv='freshNode("Timer")', args=[],
        why="Timer.is_stopped on a fresh timer (true): an immediate read with no snapshot",
    ),
    dict(
        shape="NOARGS_RET_DOUBLE", op=317, recv='freshNode("Camera3D")', args=[],
        why="Camera3D.get_fov on a fresh camera: an immediate read with no snapshot",
    ),
    dict(
        shape="NOARGS_RET_LONG", op=296, setup=['val event = freshRef("InputEventKey")', 'oracle(event, "set_keycode", 65L)'],
        recv="event", args=[], why="InputEventKey.get_keycode after a generic set_keycode(KEY_A)",
    ),
    dict(shape="NOARGS_RET_LONG_SINGLETON", op=145, args=[], why="Input.get_mouse_mode: stable within a run"),
    dict(shape="NOARGS_RET_STRING_SINGLETON", op=329, args=[], why="OS.get_name: the immediate opcode of the shape"),
    dict(
        shape="NOARGS_RET_STRING", op=278, setup=['val named = freshNode("Node")', 'oracle(named, "set_name", "KanamaExercise")'],
        recv="named", args=[], why="Node.get_name after a generic set_name",
    ),
    dict(shape="NOARGS_VOID", op=5, recv="self", args=[], kind="custom", frames=2,
         setup=["val drawsBefore = drawCalls"],
         check=['check(drawCalls > drawsBefore, "a draw callback after queue_redraw", "$drawCalls draws (was $drawsBefore)")'],
         why="CanvasItem.queue_redraw on the probe: its draw callback runs again"),
    dict(
        shape="OBJECT_STRING_RET_LONG_SINGLETON", op=223,
        setup=['val saved = freshRef("Resource")', 'cleanup { oracle(twin(), "remove_file", SAVE_PATH) }'],
        args=["saved", "SAVE_PATH", "0L"],
        why="ResourceSaver.save of a fresh Resource under user:// (removed at the end): both paths answer OK",
    ),
    dict(
        shape="STRINGNAME_RET_BOOL", op=23, recv='freshNode("Node3D")', args=['"Node"'],
        why="Object.is_class on a fresh Node3D",
    ),
    dict(shape="STRINGNAME_RET_BOOL_SINGLETON", op=83, args=['"web"'], why="OS.has_feature(\"web\")"),
    dict(
        shape="STRINGNAME_RET_DOUBLE_SINGLETON", op=187, args=['"physics/2d/default_gravity"'],
        why="ProjectSettings.get_setting of a float setting (980.0): Input's opcodes need a pressed action",
    ),
    dict(
        shape="STRINGNAME_RET_HANDLE", op=12, args=['"Node3D"'], kind="custom",
        check=['check(result != null && near(oracle(result, "get_class"), "Node3D"), "a new Node3D", describe(result))',
               "result?.let { nodes += it }"],
        why="ClassDB.instantiate makes a new object per call: the generic tier reads the new object's class",
    ),
    dict(
        shape="STRINGNAME_RET_LONG", op=264, recv='freshRef("ConfigFile")', args=["MISSING_PATH"],
        why="ConfigFile.load of a path that does not exist: ERR_FILE_NOT_FOUND on both paths (save would be OK = weak)",
    ),
    dict(shape="STRINGNAME_RET_LONG_SINGLETON", op=190, args=['""'], kind="excluded",
         why="OS.shell_open is the only opcode: on Web it opens a browser window"),
    dict(
        shape="STRINGNAME_RET_STRING", op=268,
        setup=['val config = freshRef("ConfigFile")', 'oracle(config, "set_value", "section", "key", "kanama125")'],
        recv="config", args=['"section\\u001Fkey"'], kind="custom",
        check=['check(result == "s:kanama125" && oracle(config, "get_value", "section", "key") == "kanama125", "s:kanama125 on both paths", result)'],
        why="ConfigFile.get_value packs section and key into the one slot and tags the result (`s:` for a String)",
    ),
    dict(
        shape="STRINGNAME_RET_VECTOR2", op=233, setup=['val node = freshNode("Node2D")', 'oracle(node, "set_position", Vector2(3.0, 4.0))'],
        recv="node", args=['"position"'], why="Object.get(\"position\") after a generic set_position",
    ),
    dict(
        shape="STRINGNAME_STRINGNAME_RET_DOUBLE_SINGLETON", op=104,
        setup=['val input = singleton("Input")', 'val inputMap = singleton("InputMap")',
               'oracle(inputMap, "add_action", "kanama_api_exercise_neg")',
               'oracle(inputMap, "add_action", "kanama_api_exercise_pos")',
               'oracle(input, "action_press", "kanama_api_exercise_pos")',
               'cleanup { oracle(inputMap, "erase_action", "kanama_api_exercise_neg") }',
               'cleanup { oracle(inputMap, "erase_action", "kanama_api_exercise_pos") }',
               'cleanup { oracle(input, "action_release", "kanama_api_exercise_pos") }  // runs first: cleanups run last-in first-out'],
        args=['"kanama_api_exercise_neg"', '"kanama_api_exercise_pos"'],
        why="Input.get_axis(negative, positive) with the positive action pressed: 1.0, and -1.0 if the slots swap",
    ),
    dict(
        shape="STRINGNAME_ARG_SINGLETON", op=292, args=[EXERCISE_ACTION], getter="has_action", gargs=[EXERCISE_ACTION],
        expect="true", setup=['cleanup { oracle(singleton("InputMap"), "erase_action", "kanama_api_exercise") }'],
        why="InputMap.add_action reads back through InputMap.has_action; the action is erased at the end",
    ),
    dict(
        shape="STRINGNAME_OBJECT_ARG_SINGLETON", op=293,
        setup=['val actionEvent = freshRef("InputEventKey")', 'oracle(actionEvent, "set_keycode", 66L)',
               'oracle(singleton("InputMap"), "add_action", "kanama_api_exercise_event")',
               'cleanup { oracle(singleton("InputMap"), "erase_action", "kanama_api_exercise_event") }'],
        args=['"kanama_api_exercise_event"', "actionEvent"], getter="action_has_event",
        gargs=['"kanama_api_exercise_event"', "actionEvent"], expect="true",
        why="InputMap.action_add_event reads back through InputMap.action_has_event",
    ),
    dict(shape="UTILITY_NOARGS_VOID", op=9, args=[], kind="reach", why="randomize: nothing to read back"),
    dict(
        shape="UTILITY_NOARGS_RET_DOUBLE", op=11, args=[], kind="custom", setup=['oracle(twin(), "reseed", 125L)'],
        check=['same(result, oracle(twin(), "seeded_randf", 125L))'],
        why="randf after seed(125) equals a GDScript `seed(125); randf()` (the generic tier calls no utility)",
    ),
    dict(
        shape="UTILITY_NOARGS_RET_LONG", op=10, args=[], kind="custom", setup=['oracle(twin(), "reseed", 125L)'],
        check=['same(result, oracle(twin(), "seeded_randi", 125L))'],
        why="randi after seed(125) equals a GDScript `seed(125); randi()` (the generic tier calls no utility)",
    ),
    # ---- value types ----
    dict(
        shape="BASIS_RET_LONG", op=206, recv='freshNode("GridMap")',
        args=["Basis(Vector3(0.0, 0.0, -1.0), Vector3(0.0, 1.0, 0.0), Vector3(1.0, 0.0, 0.0))"],
        why="GridMap.get_orthogonal_index_from_basis of a quarter turn about Y",
    ),
    dict(shape="COLOR_ARG", op=32, recv='freshNode("Node2D")', args=[COLOR],
         why="CanvasItem.set_modulate on a fresh Node2D (CanvasItem is abstract)"),
    dict(
        shape="COLOR_RET_HANDLE", op=290,
        setup=["val tween = tween()", 'val target = freshNode("Node2D")',
               'val tweener = track((tween as Tween).tweenProperty(target, "modulate", Color(0.5, 0.5, 0.5, 1.0), 1.0)) as GodotObject'],
        recv="tweener", args=[COLOR], kind="custom",
        check=['check(near(result, tweener) && near(oracle(tweener, "from", Color(0.2, 0.2, 0.2, 1.0)), tweener), "the receiver on both paths", describe(result))'],
        why="PropertyTweener.from returns its receiver: both paths must answer the same tweener",
    ),
    dict(
        shape="LONG_TRANSFORM3D_ARG", op=211, setup=['val library = freshRef("MeshLibrary")', 'oracle(library, "create_item", 1L)'],
        recv="library", args=["1L", "Transform3D(Basis(Vector3(2.0, 0.0, 0.0), Vector3(0.0, 1.0, 0.0), Vector3(0.0, 0.0, 1.0)), Vector3(4.0, 5.0, 6.0))"],
        why="MeshLibrary.set_item_mesh_transform reads back through get_item_mesh_transform",
    ),
    dict(
        shape="LONG_RET_VECTOR3", op=173,
        setup=['val cast = freshChild("ShapeCast3D")', 'oracle(cast, "set_shape", freshRef("SphereShape3D"))',
               'oracle(cast, "set_position", Vector3(4.0, 1.0, 4.0))', 'oracle(cast, "set_target_position", Vector3(0.0, -4.0, 0.0))',
               'oracle(cast, "force_shapecast_update")',
               'require(near(oracle(cast, "is_colliding"), true)) { "setup: the shape cast over the scene floor hit nothing" }'],
        recv="cast", args=["0L"],
        why="ShapeCast3D.get_collision_point(0) of a cast down onto the scene's floor (index 0 must exist: an empty cast logs an error)",
    ),
    dict(shape="NOARGS_RET_COLOR", op=31, recv='freshNode("Node2D")', args=[],
         why="CanvasItem.get_modulate, the shape's only opcode (a snapshot read; a constructed node's is seeded)"),
    dict(shape="NOARGS_RET_RECT2", op=4, recv="self", args=[],
         why="CanvasItem.get_viewport_rect, a snapshot read: the probe's own (scripted, in the tree) is refreshed by its proxy"),
    dict(
        shape="NOARGS_RET_VECTOR2", op=252, setup=['val control = freshNode("Control")', 'oracle(control, "set_position", Vector2(3.0, 4.0))'],
        recv="control", args=[], why="Control.get_position after a generic set_position: an immediate read with no snapshot",
    ),
    dict(shape="NOARGS_RET_VECTOR2_SINGLETON", op=314, args=[], why="Input.get_last_mouse_velocity: nothing moves the mouse"),
    dict(
        shape="NOARGS_RET_VECTOR3", op=239,
        setup=['val particles = freshNode("CPUParticles3D")', 'oracle(particles, "set_emission_box_extents", Vector3(2.0, 3.0, 4.0))'],
        recv="particles", args=[], why="CPUParticles3D.get_emission_box_extents after a generic set: an immediate read with no snapshot",
    ),
    dict(
        shape="STRINGNAME_VECTOR2_ARG", op=232, recv='freshNode("Node2D")', args=['"position"', V2],
        getter="get_indexed", gargs=['"position"'], why="Object.set_indexed reads back through get_indexed",
    ),
    dict(
        shape="STRINGNAME_VECTOR3_VECTOR3_ARG", op=191, recv='freshChild("Node3D")',
        args=['"look_at_from_position"', V3, "Vector3(4.0, 2.0, -1.0)"], getter="get_position", gargs=[], expect=V3,
        why="Object.call(\"look_at_from_position\", position, target): the node moves to the first vector (it would move to the second if they swap)",
    ),
    dict(shape="VECTOR2_ARG", op=3, recv='freshNode("Node2D")', args=[V2], why="Node2D.set_position (the snapshot-writing setter)"),
    dict(
        shape="VECTOR2_RET_VECTOR3", op=219, recv='freshChild("Camera3D")', args=["Vector2(100.0, 50.0)"],
        why="Camera3D.project_ray_normal (the origin of a camera at the origin is the zero vector); the camera must be in the tree",
    ),
    dict(shape="VECTOR3_ARG", op=74, recv='freshNode("Node3D")', args=[V3], why="Node3D.set_position (the snapshot-writing setter)"),
    dict(
        shape="VECTOR3_RET_HANDLE", op=166, setup=['val body = freshChild("CharacterBody3D")', 'oracle(body, "set_position", Vector3(20.0, 20.0, 20.0))'],
        recv="body", args=["Vector3(1.0, 2.0, 3.0)"], kind="custom",
        check=['check(result == null && near(oracle(body, "get_position"), Vector3(21.0, 22.0, 23.0)), "no collision, moved by (1, 2, 3)", "${describe(result)}, ${oracle(body, "get_position")}")'],
        why="PhysicsBody3D.move_and_collide of a shapeless body far from the scene: no collision, and the motion vector is where it went",
    ),
    dict(
        shape="VECTOR3_VECTOR3_ARG", op=108, recv='freshChild("Node3D")', args=[V3, "Vector3(4.0, 2.0, -1.0)"],
        getter="get_position", gargs=[], expect=V3,
        why="Node3D.look_at_from_position(position, target): the node moves to the first vector (it would move to the second if the slots swap)",
    ),
    dict(
        shape="STRING_LIST_RET_STRING", op=229, recv='freshChild("Node3D")',
        setup=['val query = freshRef("PhysicsRayQueryParameters3D")', 'oracle(query, "set_from", Vector3(4.0, 3.0, 4.0))',
               'oracle(query, "set_to", Vector3(4.0, -3.0, 4.0))'],
        args=['listOf("4.0", "3.0", "4.0", "4.0", "-3.0", "4.0", "4294967295", "1")'], kind="custom",
        check=['val world = oracle(r, "get_world_3d") as GodotObject',
               'val hit = oracle(oracle(world, "get_direct_space_state") as GodotObject, "intersect_ray", query) as Map<*, *>',
               'val entries = track(WebPackedValues.decodeVariant(result.substringAfter(\'\\u001F\'))) as Map<*, *>',
               'val collider = objOf(result.substringBefore(\'\\u001F\'))',
               'check(near(entries["position"], hit["position"]) && near(entries["normal"], hit["normal"]) && near(collider, hit["collider"]), "the hit the generic intersect_ray reports", result)'],
        why="PhysicsDirectSpaceState3D.intersect_ray (receiver: a node of the world; from, to, mask and flags as text parts) packs the collider and the hit; the generic tier queries the same ray with a PhysicsRayQueryParameters3D",
    ),
    dict(
        shape="VECTOR3I_LONG_LONG_ARG", op=203, setup=["val cell = Vector3i(1, 2, 3)"], recv='freshNode("GridMap")',
        args=["cell", "4L", "10L"], kind="custom",
        check=['check(near(oracle(r, "get_cell_item", cell), 4L) && near(oracle(r, "get_cell_item_orientation", cell), 10L), "item 4, orientation 10", "${oracle(r, "get_cell_item", cell)}, ${oracle(r, "get_cell_item_orientation", cell)}")'],
        why="GridMap.set_cell_item(cell, item, orientation) reads back through two getters (a swap of the two longs reads 10, 4)",
    ),
    dict(
        shape="VECTOR3I_RET_LONG", op=204, setup=['val grid = freshNode("GridMap")', 'oracle(grid, "set_cell_item", Vector3i(1, 2, 3), 4L)'],
        recv="grid", args=["Vector3i(1, 2, 3)"], why="GridMap.get_cell_item after a generic set_cell_item",
    ),
    # ---- objects ----
    dict(
        shape="LONG_LONG_RET_HANDLE", op=217, setup=["val state = sceneState()", "val meshIndex = meshPropertyIndex(state)"],
        recv="state", args=["0L", "meshIndex"],
        why="SceneState.get_node_property_value of the packed node's mesh (the Web shape returns object values)",
    ),
    dict(
        shape="LONG_LONG_RET_STRING", op=216, setup=["val state = sceneState()", "val meshIndex = meshPropertyIndex(state)"],
        recv="state", args=["0L", "meshIndex"], why="SceneState.get_node_property_name of the packed node's mesh",
    ),
    dict(shape="LONG_RET_LONG", op=215, setup=["val state = sceneState()"], recv="state", args=["0L"],
         why="SceneState.get_node_property_count of a packed MeshInstance3D with a mesh"),
    dict(shape="LONG_RET_STRING", op=214, setup=["val state = sceneState()"], recv="state", args=["0L"],
         why="SceneState.get_node_type of a packed MeshInstance3D"),
    dict(
        shape="LONG_OBJECT_ARG", op=210, setup=['val library = freshRef("MeshLibrary")', 'oracle(library, "create_item", 1L)'],
        recv="library", args=["1L", 'freshRef("BoxMesh")'],
        why="MeshLibrary.set_item_mesh reads back through get_item_mesh (the item must exist)",
    ),
    dict(
        shape="NODEPATH_RET_HANDLE", op=17,
        setup=['val parent = freshNode("Node")', 'val child = freshNode("Node")', 'oracle(child, "set_name", "Child")', "addChild(parent, child)"],
        recv="parent", args=['"Child"'], why="Node.get_node_or_null of a named child",
    ),
    dict(
        shape="NOARGS_RET_HANDLE", op=73, setup=['val parent = freshNode("Node")', 'val child = freshNode("Node")', "addChild(parent, child)"],
        recv="child", args=[], why="Node.get_parent of a fresh child: a const read of an existing node",
    ),
    dict(
        shape="OBJECT_ARG", op=16, recv='freshNode("Sprite2D")', args=['freshRef("PlaceholderTexture2D")'],
        why="Sprite2D.set_texture reads back through get_texture",
    ),
    dict(
        shape="OBJECT_BOOL_LONG_ARGS", op=13, setup=['val child = freshNode("Node")'], recv='freshNode("Node")',
        args=["child", "false", "0L"], getter="get_child_count", gargs=[], expect="1L",
        why="Node.add_child reads back as one child",
    ),
    dict(shape="OBJECT_LONG_VECTOR2_ARGS", op=21, args=["null", "0L", "Vector2(0.0, 0.0)"], kind="reach",
         why="Input.set_custom_mouse_cursor(null) restores the default cursor; nothing reads a cursor back"),
    dict(
        shape="OBJECT_RET_HANDLE", op=134, setup=["val tween = tween()"], recv="tween", args=['freshNode("Node")'], kind="custom",
        check=['check(near(result, tween), "the receiver", describe(result))'],
        why="Tween.bind_node returns its receiver (a second bind through the generic tier would rebind it)",
    ),
    dict(
        shape="STRINGNAME_OBJECT_ARG", op=281, setup=['val child = freshNode("Node")'], recv='freshNode("Node")',
        args=['"add_child"', "child"], getter="get_child_count", gargs=[], expect="1L", frames=2,
        why="Object.call_deferred(\"add_child\", node) reads back as one child once the deferred call ran",
    ),
    dict(
        shape="STRINGNAME_RET_HANDLE_LIST", op=318,
        setup=['val member = freshChild("Node")', 'oracle(member, "add_to_group", "kanama_api_exercise")', "val sceneTree = tree()"],
        recv="sceneTree", args=['"kanama_api_exercise"'], why="SceneTree.get_nodes_in_group of a group of one",
    ),
    dict(
        shape="STRING_STRING_BOOL_BOOL_RET_HANDLE_LIST", op=224,
        setup=['val parent = freshNode("Node")', 'val first = freshNode("Node")', 'oracle(first, "set_name", "C1")',
               'val second = freshNode("Node3D")', 'oracle(second, "set_name", "C2")', "addChild(parent, first)", "addChild(parent, second)"],
        recv="parent", args=['"C*"', '"Node3D"', "true", "false"],
        why="Node.find_children(pattern, type, recursive, owned = false) of unowned children: the type filter keeps one",
    ),
    dict(shape="NOARGS_RET_HANDLE_LIST", op=167, recv='freshNode("Area3D")', args=[],
         why="Area3D.get_overlapping_bodies, the shape's only opcode: empty out of the tree (weak)"),
    dict(
        shape="NOARGS_RET_VECTOR3I_LIST", op=208, setup=['val grid = freshNode("GridMap")', 'oracle(grid, "set_cell_item", Vector3i(1, 2, 3), 0L)'],
        recv="grid", args=[], why="GridMap.get_used_cells after a generic set_cell_item",
    ),
    dict(shape="NOARGS_RET_LONG_LIST_SINGLETON", op=312, args=[], why="Input.get_connected_joypads: none in a headless browser (weak)"),
    dict(
        shape="NOARGS_RET_STRING_ARRAY", op=72,
        setup=['val sprite = freshNode("AnimatedSprite2D")', 'val frames = freshRef("SpriteFrames")', 'oracle(frames, "add_animation", "run")',
               'oracle(sprite, "set_sprite_frames", frames)',
               "val seeded = obj(GodotBackendCalls.invokeNoArgsRetHandle(D.ANIMATEDSPRITE2D_GET_SPRITE_FRAMES, sprite.requireOpenHandle()))!!"],
        recv="seeded", args=[],
        why="SpriteFrames.get_animation_names, a snapshot read: the bridge seeds it when AnimatedSprite2D.get_sprite_frames hands the frames over",
    ),
    dict(
        shape="STRING_STRING_LONG_RET_HANDLE", op=7, args=['"res://assets/beep.wav"', '""', "1L"],
        why="ResourceLoader.load with CACHE_MODE_REUSE: both paths answer the cached resource",
    ),
    # ---- string arguments ----
    dict(shape="STRINGNAME_ARG", op=66, recv='freshNode("Label")', args=[TEXT],
         why="Label.set_text (AudioStreamPlayer.set_bus reads Master back for a bus that does not exist)"),
    dict(
        shape="STRINGNAME_BOOL_ARG", op=67, recv='freshNode("Node3D")', args=['"visible"', "false"], getter="get",
        gargs=['"visible"'], frames=2, why="Object.set_deferred reads back through get once the deferred set ran",
    ),
    dict(
        shape="STRINGNAME_DOUBLE_ARG", op=151, recv='freshNode("Node3D")', args=['"position:x"', "1.5"], getter="get_indexed",
        gargs=['"position:x"'], why="Object.set_indexed of a component path reads back through get_indexed",
    ),
    dict(
        shape="STRINGNAME_LONG_ARG", op=231, recv='freshNode("Node")', args=['"process_priority"', "7L"], getter="get_indexed",
        gargs=['"process_priority"'], why="Object.set_indexed of an int property reads back through get_indexed",
    ),
    dict(
        shape="STRINGNAME_STRINGNAME_ARG", op=230, recv='freshNode("Label")', args=['"text"', TEXT], getter="get_indexed",
        gargs=['"text"'], why="Object.set_indexed of a String property reads back through get_indexed",
    ),
    # ---- tweens ----
    dict(
        shape="CALLABLE_DOUBLE_RANGE_RET_HANDLE", op=192, setup=["val tween = tween()"], recv="tween",
        args=["self", '"on_exercise_tween"', "0.0", "1.0", "1.0"], kind="reach",
        why="a Callable argument has no generic encoding; the tween is killed before it steps",
    ),
    dict(
        shape="CALLABLE_RET_HANDLE", op=137, setup=["val tween = tween()"], recv="tween", args=["self", '"on_exercise_tween_done"'],
        kind="reach", why="a Callable argument has no generic encoding; the tween is killed before it steps",
    ),
    dict(
        shape="OBJECT_NODEPATH_COLOR_DOUBLE_RET_HANDLE", op=40, setup=["val tween = steppingTween()", 'val target = freshNode("Node2D")'],
        recv="tween", args=["target", '"modulate"', COLOR, "0.0"], kind="custom", frames=3,
        check=['check(near(oracle(target, "get_modulate"), ' + COLOR + '), "the final value once the tween stepped", oracle(target, "get_modulate"))'],
        why="Tween.tween_property makes a new tweener per call: a zero-duration tween sets the final value when it steps",
    ),
    dict(
        shape="OBJECT_NODEPATH_DOUBLE_DOUBLE_RET_HANDLE", op=289, setup=["val tween = steppingTween()", 'val target = freshNode("Node2D")'],
        recv="tween", args=["target", '"rotation"', "0.5", "0.0"], kind="custom", frames=3,
        check=['check(near(oracle(target, "get_rotation"), 0.5), "the final value once the tween stepped", oracle(target, "get_rotation"))'],
        why="Tween.tween_property makes a new tweener per call: a zero-duration tween sets the final value when it steps (a swap of the two doubles leaves it at 0)",
    ),
    dict(
        shape="OBJECT_NODEPATH_VECTOR2_DOUBLE_RET_HANDLE", op=39, setup=["val tween = steppingTween()", 'val target = freshNode("Node2D")'],
        recv="tween", args=["target", '"position"', V2, "0.0"], kind="custom", frames=3,
        check=['check(near(oracle(target, "get_position"), ' + V2 + '), "the final value once the tween stepped", oracle(target, "get_position"))'],
        why="Tween.tween_property makes a new tweener per call: a zero-duration tween sets the final value when it steps",
    ),
    dict(
        shape="OBJECT_NODEPATH_VECTOR3_DOUBLE_RET_HANDLE", op=136, setup=["val tween = steppingTween()", 'val target = freshNode("Node3D")'],
        recv="tween", args=["target", '"position"', V3, "0.0"], kind="custom", frames=3,
        check=['check(near(oracle(target, "get_position"), ' + V3 + '), "the final value once the tween stepped", oracle(target, "get_position"))'],
        why="Tween.tween_property makes a new tweener per call: a zero-duration tween sets the final value when it steps",
    ),
    # ---- drawing ----
    dict(
        shape="TEXTURE2D_VECTOR2_COLOR_ARGS", op=6, args=[], kind="draw",
        why="CanvasItem.draw_texture is valid only inside a draw callback: the probe's draw() makes the call once after queue_redraw",
    ),
]

# --------------------------------------------------------------------------------------------------
# Derivation
# --------------------------------------------------------------------------------------------------

KINDS = ("oracle", "readback", "custom", "reach", "excluded", "draw")

# SPI slot type -> how an API-typed Kotlin expression becomes the slot's backend value.
VALUE_SLOTS = {"GodotVector2", "GodotVector3", "GodotVector2i", "GodotVector3i", "GodotColor", "GodotBasis", "GodotTransform3D"}
PLAIN_SLOTS = {"Int", "Long", "Double", "Boolean", "String", "List<String>"}

# SPI return type -> the suffix that turns the call's result into an API value the checks compare.
RETURNS = {
    "": "",
    "Int": ".toLong()",
    "Long": "",
    "Double": "",
    "Boolean": "",
    "String": "",
    "GodotVector2": ".toApi()",
    "GodotVector3": ".toApi()",
    "GodotColor": ".toApi()",
    "GodotRect2": ".toApi()",
    "GodotHandle?": ".let { obj(it) }",
    "List<GodotHandle>": ".map { obj(it) }",
    "List<GodotVector3i>": ".map { it.toApi() }",
    "List<Long>": "",
    "List<String>": "",
}


@dataclass
class Api:
    classes: dict[str, dict]
    singletons: set[str]

    @classmethod
    def load(cls, path: Path) -> "Api":
        data = json.loads(path.read_text())
        return cls({c["name"]: c for c in data["classes"]}, {s["name"] for s in data["singletons"]})

    def chain(self, name: str) -> list[str]:
        out = []
        while name:
            out.append(name)
            name = self.classes.get(name, {}).get("inherits", "")
        return out

    def method(self, class_name: str, method: str) -> dict | None:
        for owner in self.chain(class_name):
            for entry in self.classes.get(owner, {}).get("methods", ()):
                if entry["name"] == method:
                    return entry
        return None

    def getter_for(self, class_name: str, setter: str, args: int) -> str | None:
        """The getter a setter reads back through: its property's, else `get_X` / `is_X`."""
        for owner in self.chain(class_name):
            for prop in self.classes.get(owner, {}).get("properties", ()):
                if prop.get("setter") == setter and prop.get("getter"):
                    return prop["getter"]
        if setter.startswith("set_"):
            stem = setter[4:]
            for name in (f"get_{stem}", f"is_{stem}"):
                found = self.method(class_name, name)
                if found and len(found.get("arguments", ())) == args - 1:
                    return name
        return None


@dataclass
class Row:
    shape: str
    call: BackendCallPolicy
    kind: str
    recv: str | None
    args: list[str]
    slots: list[tuple[str, str]]
    ret: str
    setup: list[str] = field(default_factory=list)
    oargs: list[str] = field(default_factory=list)
    getter: str | None = None
    gargs: list[str] = field(default_factory=list)
    expect: str | None = None
    check: list[str] = field(default_factory=list)
    frames: int = 0
    why: str = ""

    @property
    def label(self) -> str:
        return f"{self.call.class_name}.{self.call.method_name}"

    @property
    def singleton(self) -> bool:
        return self.call.scope != "class" or self.shape.endswith("_SINGLETON")


def _calls() -> dict[int, BackendCallPolicy]:
    return {call.opcode: call for call in INITIAL_BACKEND_CALLS}


def build_rows(api: Api) -> list[Row]:
    calls = _calls()
    shapes = sorted({call.shape for call in calls.values()})
    seen: dict[str, int] = {}
    rows: list[Row] = []
    problems: list[str] = []
    for spec in ROWS:
        shape = spec["shape"]
        seen[shape] = seen.get(shape, 0) + 1
        call = calls.get(spec["op"])
        if call is None:
            problems.append(f"{shape}: opcode {spec['op']} is not in the contract")
            continue
        if call.shape != shape:
            problems.append(f"{shape}: opcode {spec['op']} ({call.class_name}.{call.method_name}) has shape {call.shape}")
            continue
        params, ret = SIGNATURES[shape]
        slots = [tuple(p.split(": ", 1)) for p in params]
        has_receiver = bool(slots) and slots[0][0] == "receiver"
        slots = [s for s in slots if s[0] != "receiver"]
        kind = spec.get("kind") or ("oracle" if ret else "readback")
        if kind not in KINDS:
            problems.append(f"{shape}: unknown kind {kind}")
            continue
        recv = spec.get("recv")
        if kind != "draw" and has_receiver != (recv is not None):
            problems.append(f"{shape}: the shape {'takes' if has_receiver else 'takes no'} receiver, the row {'gives none' if recv is None else 'gives one'}")
            continue
        args = list(spec.get("args", ()))
        if kind != "draw" and len(args) != len(slots):
            problems.append(f"{shape}: {len(args)} arguments for {len(slots)} slots {[s[0] for s in slots]}")
            continue
        if ret not in RETURNS:
            problems.append(f"{shape}: no comparison for SPI return type {ret!r}")
            continue
        if call.class_name != "@GlobalScope" and api.method(call.class_name, call.method_name) is None:
            problems.append(f"{shape}: {call.class_name}.{call.method_name} is not in extension_api.json")
            continue
        if not spec.get("why"):
            problems.append(f"{shape}: the row gives no reason")
        row = Row(shape, call, kind, recv, args, slots, ret, list(spec.get("setup", ())),
                  list(spec.get("oargs", args)), spec.get("getter"), list(spec.get("gargs", args[:-1])),
                  spec.get("expect"), list(spec.get("check", ())), int(spec.get("frames", 0)), spec.get("why", ""))
        if kind == "readback":
            if ret:
                problems.append(f"{shape}: a readback row needs a void call")
            if row.getter is None:
                row.getter = api.getter_for(call.class_name, call.method_name, len(args))
                if row.getter is None:
                    problems.append(f"{shape}: no getter reads {row.label} back; give `getter`")
            elif api.method(call.class_name, row.getter) is None and api.method(row_class(api, row), row.getter) is None:
                problems.append(f"{shape}: getter {row.getter} is not a method of {call.class_name}")
            if row.expect is None:
                row.expect = args[-1]
        if kind in ("readback", "custom") and row.frames == 0 and call.execution_mode == "QUEUED_MUTATION":
            row.frames = 1
        if kind == "custom" and not row.check:
            problems.append(f"{shape}: a custom row needs `check`")
        rows.append(row)
    for shape in shapes:
        if shape not in seen:
            problems.append(f"{shape}: the contract shape has no exercise row")
    for shape, count in seen.items():
        if count > 1:
            problems.append(f"{shape}: {count} rows (one per shape)")
        if shape not in shapes:
            problems.append(f"{shape}: not a contract shape")
    if problems:
        raise GenerationError("\n".join(problems))
    return rows


def row_class(api: Api, row: Row) -> str:
    """The class a `freshNode("X")`-style receiver constructs, else the contract class."""
    recv = row.recv or ""
    for prefix in ('freshNode("', 'freshRef("', 'freshChild("'):
        if recv.startswith(prefix):
            return recv[len(prefix):].split('"', 1)[0]
    return row.call.class_name


# --------------------------------------------------------------------------------------------------
# Rendering
# --------------------------------------------------------------------------------------------------


def slot_expr(expr: str, slot_type: str) -> str:
    if slot_type == "GodotHandle":
        return f"{expr}.requireOpenHandle()"
    if slot_type == "GodotHandle?":
        return "null" if expr == "null" else f"{expr}.requireOpenHandle()"
    if slot_type in VALUE_SLOTS:
        return f"{expr}.toBackend()"
    if slot_type in PLAIN_SLOTS:
        return expr
    raise GenerationError(f"no conversion for SPI slot type {slot_type!r}")


def bound(expr: str) -> bool:
    """An argument the row binds to a `val` first: it makes an object, so it must be evaluated once."""
    return "fresh" in expr


def arg_name(row: Row, expr: str) -> str:
    """The expression a row uses for one of its arguments (the bound `val` when there is one)."""
    if bound(expr) and expr in row.args:
        return f"arg{row.args.index(expr)}"
    return expr


def spi_call(row: Row) -> str:
    args = [f"D.{descriptor_name(row.call)}"]
    if row.recv is not None:
        args.append("r.requireOpenHandle()")
    args += [slot_expr(arg_name(row, expr), slot_type) for expr, (_, slot_type) in zip(row.args, row.slots)]
    return f"GodotBackendCalls.{invoke_name(row.shape)}({', '.join(args)}){RETURNS[row.ret]}"


def oracle_target(row: Row) -> str:
    return f'singleton("{row.call.class_name}")' if row.recv is None else "r"


def oracle_call(target: str, method: str, args: list[str], row: Row | None = None) -> str:
    named = [arg_name(row, arg) for arg in args] if row else args
    return f'oracle({", ".join([target, kotlin_string(method), *named])})'


def kotlin_string(text: str) -> str:
    return '"' + text.replace("\\", "\\\\").replace('"', '\\"') + '"'


def function_name(row: Row) -> str:
    return "row" + "".join(part.capitalize() for part in row.shape.split("_"))


def render_row(row: Row) -> list[str]:
    mode = row.call.execution_mode.value
    head = f"  /** {row.shape}: opcode {row.call.opcode} `{row.label}` ({mode}). {row.why}. */"
    lines = [head]
    lines.append(f'  private fun {function_name(row)}() = row("{row.shape}", "{row.label}") {{')
    body: list[str] = list(row.setup)
    if row.recv is not None:
        body.append(f"val r = {row.recv}")
    for index, expr in enumerate(row.args):
        if bound(expr):
            body.append(f"val arg{index} = {expr}")
    if row.kind == "oracle":
        body.append(f"val result = {spi_call(row)}")
        body.append(f"same(result, {oracle_call(oracle_target(row), row.call.method_name, row.oargs, row)})")
    elif row.kind == "readback":
        target = oracle_target(row)
        body.append(f"val before = {oracle_call(target, row.getter, row.gargs, row)}")
        body.append(spi_call(row))
        readback = f"readback(before, {oracle_call(target, row.getter, row.gargs, row)}, {arg_name(row, row.expect)})"
        body += [f"later({row.frames}) {{ {readback} }}"] if row.frames else [readback]
    elif row.kind == "custom":
        body.append(f"val result = {spi_call(row)}" if row.ret else spi_call(row))
        if row.frames:
            body.append(f"later({row.frames}) {{")
            body += [f"  {line}" for line in row.check]
            body.append("}")
        else:
            body += row.check
    elif row.kind == "reach":
        body.append(spi_call(row))
        body.append("reach()")
    elif row.kind == "draw":
        body.append("armDraw(this)")
    lines += [f"    {line}" for line in body]
    lines.append("  }")
    return lines


def render_draw(rows: list[Row]) -> list[str]:
    (row,) = [r for r in rows if r.kind == "draw"]
    call = row.call
    return [
        "  /** The draw row's call, made inside the probe's draw callback (see [armDraw]). */",
        "  private fun drawRowCall(texture: GodotObject) {",
        f"    GodotBackendCalls.{invoke_name(row.shape)}(",
        f"      D.{descriptor_name(call)},",
        "      self.requireOpenHandle(),",
        "      texture.requireOpenHandle(),",
        "      Vector2(-8.0, -8.0).toBackend(),",
        "      Color(1.0, 1.0, 1.0, 1.0).toBackend(),",
        "    )",
        "  }",
    ]


def render_probe(rows: list[Row]) -> str:
    counted = [r for r in rows if r.kind != "excluded"]
    excluded = [r for r in rows if r.kind == "excluded"]
    out = PROBE_HEAD.format(rows=len(rows), counted=len(counted), excluded=len(excluded)).splitlines()
    out.append("  /** Runs every row once; the queued readbacks land over the next frames. */")
    out.append("  private fun runRows() {")
    for row in rows:
        if row.kind == "excluded":
            out.append(f'    excludedRow("{row.shape}", "{row.label}")')
        else:
            out.append(f"    {function_name(row)}()")
    out.append("  }")
    out.append("")
    for row in counted:
        out += render_row(row)
        out.append("")
    out += render_draw(rows)
    out += PROBE_TAIL.splitlines()
    return "\n".join(out) + "\n"


PROBE_HEAD = '''// Generated by scripts/generate_web_exercise.py — do not edit.
@file:OptIn(InternalKanamaBackendApi::class)

package web3d

import kotlin.math.abs
import kotlin.math.max
import net.multigesture.kanama.annotations.GodotName
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.api.ClassDB
import net.multigesture.kanama.api.GodotHandle
import net.multigesture.kanama.api.GodotObject
import net.multigesture.kanama.api.KanamaScript
import net.multigesture.kanama.api.MainThread
import net.multigesture.kanama.api.Node2D
import net.multigesture.kanama.api.Tween
import net.multigesture.kanama.api.toApi
import net.multigesture.kanama.api.toBackend
import net.multigesture.kanama.api.toWebId
import net.multigesture.kanama.api.webWrap
import net.multigesture.kanama.backend.GodotBackendCalls
import net.multigesture.kanama.backend.GodotHandle as BackendGodotHandle
import net.multigesture.kanama.backend.InitialGodotCallDescriptors as D
import net.multigesture.kanama.backend.InternalKanamaBackendApi
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.Vector3i
import net.multigesture.kanama.web.WebExperimentalGenericCall
import net.multigesture.kanama.web.WebObjectId
import net.multigesture.kanama.web.WebPackedValues

private const val SAVE_PATH = "user://kanama-api-exercise-web.tres"
private const val MISSING_PATH = "user://kanama-api-exercise-missing.cfg"

/**
 * Harness-only (task 125 P5): the Web API exercise, one row per Web call shape ({rows} rows, {excluded}
 * excluded), generated from scripts/platform_backend_calls.json by scripts/generate_web_exercise.py
 * (the row table is there; the report is docs/reference/generated/web-api-exercise.md).
 *
 * Each row calls its shape through `GodotBackendCalls` (the call every generated Web wrapper member
 * makes) and checks the result against the generic tier (`WebExperimentalGenericCall`, `callv` by
 * name) on the same object. Main's `api_exercise_probe` arms the rows, `api_exercise_probe_after`
 * reads `passed * 1000 + failed` once the last queued readback ran (-1 before), and
 * `api_exercise_rows` is the number of rows that must pass. Every object a row makes is freed or
 * closed before the result is published, and every global a row changes is set back.
 */
@ScriptClass(attachTo = "Node2D")
class ApiExerciseProbe(godotObject: GodotHandle) : KanamaScript<Node2D>(godotObject, ::Node2D) {{
  @Signal("exercise_connect") fun exerciseConnect(value: Long) = Unit

  @Signal("exercise_bound") fun exerciseBound() = Unit

  @Signal("exercise_none") fun exerciseNone() = Unit

  @Signal("exercise_int") fun exerciseInt(value: Long) = Unit

  @Signal("exercise_string") fun exerciseString(value: String) = Unit

  @Signal("exercise_vector2i") fun exerciseVector2i(value: Vector2i) = Unit

  @Signal("exercise_object") fun exerciseObject(value: GodotObject) = Unit

  private var connectValue = 0L
  private var boundValue = 0L
  private var noneCount = 0L
  private var intValue = 0L
  private var stringValue = ""
  private var vector2iValue = Vector2i(0, 0)
  private var objectValue: GodotObject? = null
  private var drawCalls = 0L

  @GodotName("on_exercise_connect")
  fun onExerciseConnect(value: Long) {{
    connectValue = value
  }}

  @GodotName("on_exercise_bound")
  fun onExerciseBound(value: Long) {{
    boundValue = value
  }}

  @GodotName("on_exercise_none")
  fun onExerciseNone() {{
    noneCount += 1
  }}

  @GodotName("on_exercise_int")
  fun onExerciseInt(value: Long) {{
    intValue = value
  }}

  @GodotName("on_exercise_string")
  fun onExerciseString(value: String) {{
    stringValue = value
  }}

  @GodotName("on_exercise_vector2i")
  fun onExerciseVector2i(value: Vector2i) {{
    vector2iValue = value
  }}

  @GodotName("on_exercise_object")
  fun onExerciseObject(value: GodotObject) {{
    objectValue = track(value) as GodotObject
  }}

  /** The tween rows' callables: a killed tween never calls them. */
  @GodotName("on_exercise_tween")
  fun onExerciseTween(value: Double) = Unit

  @GodotName("on_exercise_tween_done")
  fun onExerciseTweenDone() = Unit

  private var armed = false
  private var done = false
  private var passed = 0L
  private var failed = 0L
  private var weak = 0L
  private var reached = 0L
  private var excludedRows = 0L
  private var pending = 0
  private var finishing = false
  private var startedMs = 0.0
  private var drawRow: RowScope? = null
  private var drawTexture: GodotObject? = null
  private val wrappers = mutableListOf<GodotObject>()
  private val nodes = mutableListOf<GodotObject>()
  private val tweens = mutableListOf<GodotObject>()
  private val steppingTweens = mutableListOf<GodotObject>()
  private val cleanups = mutableListOf<() -> Unit>()
  private val singletons = mutableMapOf<String, GodotObject>()
  private var twinNode: GodotObject? = null

  /** Runs the rows; the result is [apiExerciseResult] once the queued readbacks have landed. */
  fun apiExerciseArm(value: Long): Long {{
    if (armed) return 0L
    armed = true
    startedMs = WebExperimentalGenericCall.nowMillis()
    for (signal in listOf("exercise_none", "exercise_int", "exercise_string", "exercise_vector2i", "exercise_object")) {{
      self.connect(signal, self, "on_" + signal)
    }}
    runRows()
    // A tween that never steps is killed before the frame ends: an empty one would log
    // "started with no Tweeners", and a callback one would call the probe.
    for (tween in tweens) oracle(tween, "kill")
    tweens.clear()
    settle()
    return 1L
  }}

  /** `passed * 1000 + failed` once every row has its verdict and the cleanup ran, -1 before. */
  fun apiExerciseResult(value: Long): Long = if (done) passed * 1000 + failed else -1L

  /** The number of rows that must pass ({counted}: every row but the excluded). */
  fun apiExerciseRows(value: Long): Long = {counted}L

  @GodotName("_draw")
  fun draw() {{
    drawCalls += 1
    val row = drawRow ?: return
    val texture = drawTexture ?: return
    drawRow = null
    row.guarded {{
      drawRowCall(texture)
      reach()
    }}
    pending -= 1
    settle()
  }}

  // ---------- the rows' vocabulary ----------

  private inner class RowScope(val shape: String, val label: String) {{
    private var settled = false

    fun guarded(block: RowScope.() -> Unit) {{
      try {{
        block()
      }} catch (error: Throwable) {{
        fail("no exception", "${{error::class.simpleName}}: ${{error.message}}")
      }}
    }}

    /** The verdict comes later, from another scope. */
    fun deferred() {{
      settled = true
    }}

    fun pass(isWeak: Boolean) {{
      if (settled) return
      settled = true
      passed += 1
      if (isWeak) weak += 1
    }}

    fun fail(expected: Any?, got: Any?) {{
      if (settled) return
      settled = true
      failed += 1
      println("[kanama:web] API EXERCISE FAIL: $shape $label expected ${{describe(expected)}} got ${{describe(got)}}")
    }}

    /** The call's result equals the generic tier's; a result equal to its type's zero is weak. */
    fun same(got: Any?, expected: Any?) {{
      if (near(got, expected)) pass(isZero(got)) else fail(expected, got)
    }}

    /** A void call's value read back: it is [expected] now, and was something else before. */
    fun readback(before: Any?, after: Any?, expected: Any?) {{
      when {{
        near(before, expected) -> fail("a value other than ${{describe(before)}} before the call", before)
        near(after, expected) -> pass(false)
        else -> fail(expected, after)
      }}
    }}

    fun check(ok: Boolean, expected: Any?, got: Any?) {{
      if (ok) pass(false) else fail(expected, got)
    }}

    /** A reach row: the call came back. */
    fun reach() {{
      reached += 1
      pass(false)
    }}

    /** Runs [block] [frames] frames later (a queued mutation is applied by then). */
    fun later(frames: Int, block: RowScope.() -> Unit) {{
      deferred()
      val scope = RowScope(shape, label)
      pending += 1
      MainThread.postAfterFrames(frames) {{
        scope.guarded(block)
        pending -= 1
        settle()
      }}
    }}
  }}

  private fun row(shape: String, label: String, block: RowScope.() -> Unit) {{
    RowScope(shape, label).guarded(block)
  }}

  private fun excludedRow(shape: String, label: String) {{
    excludedRows += 1
  }}

  /** The draw row: its verdict is the draw callback's (see [draw]). */
  private fun armDraw(scope: RowScope) {{
    scope.deferred()
    pending += 1
    drawRow = RowScope(scope.shape, scope.label)
    drawTexture = freshRef("PlaceholderTexture2D")
    oracle(self, "queue_redraw")
  }}

  /** Once no row waits for a frame, the cleanup runs a frame later (queued frees go first). */
  private fun settle() {{
    if (pending == 0 && armed && !finishing) {{
      finishing = true
      MainThread.postAfterFrames(1) {{ finish() }}
    }}
  }}

  private fun finish() {{
    if (done) return
    for (step in cleanups.asReversed()) runCatching(step)
    for (tween in steppingTweens) runCatching {{ oracle(tween, "kill") }}
    for (node in nodes) runCatching {{ WebExperimentalGenericCall.queueVoidCall(node, "queue_free") }}
    for (wrapper in wrappers) (wrapper as? AutoCloseable)?.close()
    val ms = (WebExperimentalGenericCall.nowMillis() - startedMs).toLong()
    println(
      "[kanama:web] API EXERCISE: $passed passed, $failed failed, $weak weak, $reached reach, " +
        "$excludedRows excluded in $ms ms"
    )
    done = true
  }}

  private fun cleanup(step: () -> Unit) {{
    cleanups += step
  }}

  // ---------- receivers ----------

  private fun freshObject(className: String): GodotObject {{
    val handle = checkNotNull(ClassDB.instantiate(className)) {{ "Godot could not instantiate $className" }}
    return track(webWrap(handle.toWebId())) as GodotObject
  }}

  /** A fresh node out of the tree, freed at the end. */
  private fun freshNode(className: String): GodotObject = freshObject(className).also {{ nodes += it }}

  /** A fresh RefCounted, closed at the end. */
  private fun freshRef(className: String): GodotObject = freshObject(className)

  /** A fresh node added under the probe, so it is inside the tree. */
  private fun freshChild(className: String): GodotObject = freshNode(className).also {{ addChild(self, it) }}

  private fun addChild(parent: GodotObject, child: GodotObject) {{
    oracle(parent, "add_child", child)
  }}

  private fun tree(): GodotObject = oracle(self, "get_tree") as GodotObject

  /**
   * A tween of the probe's, killed before the frame ends (it never steps). The typed
   * `create_tween` makes it: the bridge's tween arms know the tweens it hands out.
   */
  private fun tween(): GodotObject = (track(self.createTween()) as GodotObject).also {{ tweens += it }}

  /** A tween of the probe's that steps over the next frames, killed at the end. */
  private fun steppingTween(): GodotObject =
    (track(self.createTween()) as GodotObject).also {{ steppingTweens += it }}

  /** The engine singleton [name], through the GDScript twin (the generic tier needs a handle). */
  private fun singleton(name: String): GodotObject =
    singletons.getOrPut(name) {{ oracle(twin(), "singleton", name) as GodotObject }}

  private fun twin(): GodotObject = twinNode ?: (oracle(self, "get_node", "../ApiTwin") as GodotObject).also {{ twinNode = it }}

  /** A SceneState of a packed MeshInstance3D that carries a mesh. */
  private fun sceneState(): GodotObject {{
    val node = freshNode("MeshInstance3D")
    oracle(node, "set_mesh", freshRef("BoxMesh"))
    val packed = freshRef("PackedScene")
    require(oracle(packed, "pack", node) == 0L) {{ "setup: PackedScene.pack failed" }}
    return oracle(packed, "get_state") as GodotObject
  }}

  private fun meshPropertyIndex(state: GodotObject): Long {{
    val count = oracle(state, "get_node_property_count", 0L) as Long
    return (0L until count).first {{ oracle(state, "get_node_property_name", 0L, it) == "mesh" }}
  }}

  // ---------- the generic oracle and the comparison ----------

  private fun oracle(target: GodotObject, method: String, vararg args: Any?): Any? =
    track(WebExperimentalGenericCall.callImmediate(target, method, args.toList()).asAny())

  /** A wrapper of an SPI handle result, closed at the end. */
  private fun obj(handle: BackendGodotHandle?): GodotObject? = handle?.let {{ track(webWrap(it.toWebId())) as GodotObject }}

  /** A wrapper of a handle token a packed result carries (0 = none), closed at the end. */
  private fun objOf(token: String): GodotObject? =
    token.toInt().takeIf {{ it > 0 }}?.let {{ track(webWrap(WebObjectId(it))) as GodotObject }}

  private fun track(value: Any?): Any? {{
    when (value) {{
      is GodotObject -> wrappers += value
      is List<*> -> value.forEach {{ track(it) }}
      is Map<*, *> -> value.forEach {{ (key, item) ->
        track(key)
        track(item)
      }}
    }}
    return value
  }}

  private fun instanceId(target: GodotObject): Long =
    WebExperimentalGenericCall.callImmediate(target, "get_instance_id").asLong()

  private fun near(a: Any?, b: Any?): Boolean =
    when {{
      a == null || b == null -> a == null && b == null
      a is GodotObject && b is GodotObject -> instanceId(a) == instanceId(b)
      a is Double || b is Double || a is Float || b is Float -> {{
        val x = (a as? Number)?.toDouble()
        val y = (b as? Number)?.toDouble()
        x != null && y != null && abs(x - y) <= 1e-3 * max(1.0, abs(y))
      }}
      a is Number && b is Number -> a.toLong() == b.toLong()
      a is Vector2 && b is Vector2 -> a.isEqualApprox(b)
      a is Vector3 && b is Vector3 -> a.isEqualApprox(b)
      a is Color && b is Color -> a.isEqualApprox(b)
      a is Rect2 && b is Rect2 -> a.isEqualApprox(b)
      a is Basis && b is Basis -> a.isEqualApprox(b)
      a is Transform3D && b is Transform3D -> a.isEqualApprox(b)
      a is List<*> && b is List<*> -> a.size == b.size && a.indices.all {{ near(a[it], b[it]) }}
      else -> a == b
    }}

  private fun isZero(value: Any?): Boolean =
    when (value) {{
      null -> true
      is Boolean -> !value
      is Number -> value.toDouble() == 0.0
      is String -> value.isEmpty()
      is List<*> -> value.isEmpty()
      is Vector2 -> value == Vector2(0.0, 0.0)
      is Vector3 -> value == Vector3(0.0, 0.0, 0.0)
      is Vector2i -> value == Vector2i(0, 0)
      is Vector3i -> value == Vector3i(0, 0, 0)
      else -> false
    }}

  private fun describe(value: Any?): String =
    when (value) {{
      is GodotObject -> "object#${{runCatching {{ instanceId(value) }}.getOrDefault(0L)}}"
      is List<*> -> value.joinToString(", ", "[", "]") {{ describe(it) }}
      else -> value.toString()
    }}

  // ---------- the rows ----------
'''

PROBE_TAIL = '''}'''


# --------------------------------------------------------------------------------------------------
# Report
# --------------------------------------------------------------------------------------------------

KIND_TEXT = {
    "oracle": "oracle",
    "readback": "readback",
    "custom": "custom",
    "reach": "reach",
    "excluded": "**excluded**",
    "draw": "reach (draw)",
}


def render_report(rows: list[Row]) -> str:
    counts: dict[str, int] = {}
    for row in rows:
        counts[row.kind] = counts.get(row.kind, 0) + 1
    calls = _calls()
    lines = [
        "# Web API Exercise",
        "",
        "<!-- GENERATED by scripts/generate_web_exercise.py. DO NOT EDIT BY HAND; the local_ci stage",
        "     \"Web API exercise drift\" (`--check`) fails when this page is stale. -->",
        "",
        "Every generated Web wrapper member is one `GodotBackendCalls.invoke<Shape>(descriptor, ...)` call, and the",
        "Web backend implements each call **shape** of `scripts/platform_backend_calls.json` once (a Kotlin arm, a",
        "JS bridge extern and a GDScript arm), so a defect in a shape is shared by every opcode of it. The web3d",
        "smoke runs one generated row per shape (`ApiExerciseProbe.kt`): the row calls the shape on an object the",
        "scene constructs and checks it against the generic tier (`WebExperimentalGenericCall.callImmediate`, `callv`",
        "by name) on the same object. The browser driver requires every row to pass (`checks.apiExercise`), in",
        "Chrome and Firefox; the console line is `[kanama:web] API EXERCISE: <n> passed, <m> failed, <w> weak,",
        "<r> reach, <e> excluded in <ms> ms`, with one `[kanama:web] API EXERCISE FAIL: <shape> <Class.method>",
        "expected <x> got <y>` per failed row.",
        "",
        f"**Shapes:** {len(rows)} ({len(calls)} opcodes). **Rows by check:** "
        + ", ".join(f"{KIND_TEXT[k].strip('*')} {counts[k]}" for k in KINDS if k in counts)
        + ".",
        "",
        "| check | the row asserts |",
        "|---|---|",
        "| oracle | the call's result equals the generic call of the same method with the same arguments on the same object; a result equal to its type's zero is **weak** (counted, not failed) |",
        "| readback | a void call's value reads back through the generic getter (a queued mutation a frame later), and the getter read something else before the call |",
        "| custom | an assertion the generic tier cannot be the oracle of, written in the row: a connected handler's payload, a new object's state, the engine's seeded random generator |",
        "| reach | the call returns without an exception: the shape's arguments have no generic encoding and nothing reads its effect back |",
        "| excluded | not called (the reason is in the table) |",
        "",
        "The row table (opcode, receiver, arguments, and the check where the rule cannot derive it) is hand-kept in",
        "`scripts/generate_web_exercise.py`; the descriptor, the SPI call, the generic oracle call, a setter's",
        "getter and the queued-mutation frame are derived. A shape the contract gains without a row fails `--check`.",
        "",
        "| shape | opcode | call | mode | check | why |",
        "|---|---|---|---|---|---|",
    ]
    for row in sorted(rows, key=lambda r: r.shape):
        why = row.why.replace("|", "\\|")
        lines.append(
            f"| `{row.shape}` | {row.call.opcode} | `{row.label}` | {row.call.execution_mode.value} | {KIND_TEXT[row.kind]} | {why} |"
        )
    return "\n".join(lines) + "\n"


# --------------------------------------------------------------------------------------------------


def outputs() -> dict[Path, str]:
    api = Api.load(API_PATH)
    rows = build_rows(api)
    return {PROBE_PATH: render_probe(rows), REPORT_PATH: render_report(rows)}


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__.split("\n\n")[0])
    parser.add_argument("--check", action="store_true", help="fail when a committed output differs from a fresh render")
    args = parser.parse_args()
    try:
        rendered = outputs()
    except GenerationError as error:
        print(f"{TAG} FAIL row table:\n{error}", file=sys.stderr)
        return 1
    if args.check:
        stale = [path for path, text in rendered.items() if not path.is_file() or path.read_text() != text]
        if stale:
            for path in stale:
                print(f"{TAG} FAIL stale: {path.relative_to(ROOT)} (run python3 scripts/generate_web_exercise.py)", file=sys.stderr)
            return 1
        print(f"{TAG} PASS {len(ROWS)} rows, one per Web call shape")
        return 0
    for path, text in rendered.items():
        path.parent.mkdir(parents=True, exist_ok=True)
        path.write_text(text)
        print(f"{TAG} wrote {path.relative_to(ROOT)}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
