#!/usr/bin/env python3
"""Generate the Kotlin/Wasm Web backend dispatch from the shared backend contract.

Task 60a ("Option A"): the mechanical opcode dispatch, execution-mode guards, and codec calls of
`WebCommonGodotBackend` are generated from the platform-neutral
`scripts/platform_backend_calls.json` (shared with desktop/Android/iOS) joined with the Web-local
per-opcode policy in this file. Web-only stateful bookkeeping lives hand-written in
`WebBackendBookkeeping.kt`; the JS bridge externs live hand-written in `WebBackendTransport.kt`.
Admitting a new call family of an existing shape is a JSON entry plus a `WEB_POLICY` entry (a
regenerated diff); a genuinely new call shape additionally needs a shape emitter here plus its
hand-written transport/bookkeeping. See docs/contributing/web-internals.md ("Backend-dispatch
codegen").

Drift is gated whitespace-insensitively (`--check`): the generated token stream must match the
committed file. The committed file's formatting is separately guaranteed by ktfmt.
"""

from __future__ import annotations

import argparse
import re
import sys
from pathlib import Path

from platform_backend_contract import INITIAL_BACKEND_CALLS, BackendCallPolicy

# --------------------------------------------------------------------------------------------------
# Web-local per-opcode policy. NOT part of the shared platform-neutral model: these are the Web
# transport/bookkeeping leaves the shared JSON deliberately does not carry (see the module docstring).
# Every opcode in the shared contract must have an entry here or generation fails loud.
# --------------------------------------------------------------------------------------------------

# Snapshot slots for the mirrored Vector2 properties.
_POSITION = "WebVector2Slot.POSITION"
_SCALE = "WebVector2Slot.SCALE"

WEB_POLICY: dict[int, dict[str, object]] = {
    1: {},
    2: {"vec2_slot": _POSITION},
    3: {"vec2_slot": _POSITION},
    4: {},
    5: {},
    6: {},
    7: {},
    8: {},
    9: {},
    10: {},
    11: {},
    12: {},
    13: {},
    14: {},
    15: {"on_queue_free": True},
    16: {},
    17: {},
    18: {"ret": "node"},
    19: {"ret": "node"},
    20: {},
    21: {},
    22: {},
    23: {},
    24: {},
    25: {},
    26: {},
    27: {},
    28: {},
    29: {"vec2_slot": _SCALE},
    30: {"vec2_slot": _SCALE},
    31: {},
    32: {},
    33: {},
    34: {},
    35: {},
    36: {"ret": "browser"},
    37: {},
    38: {},
    39: {},
    40: {},
    41: {"ret": "existing"},
    42: {"ret": "existing"},
    43: {"bool_snapshot": "emitting"},
    44: {},
    45: {},
    46: {},
    47: {},
    48: {},
    49: {},
    50: {},
    51: {"ret": "browser"},
    52: {},
    53: {},
    54: {},
    55: {},
    56: {},
    57: {},
    58: {},
    59: {},
    60: {},
    61: {},
    62: {},
    63: {},
    64: {},
    65: {},
    66: {},
    67: {},
}


class GenerationError(RuntimeError):
    pass


def _by_shape() -> dict[str, list[BackendCallPolicy]]:
    grouped: dict[str, list[BackendCallPolicy]] = {}
    for call in INITIAL_BACKEND_CALLS:
        if call.opcode not in WEB_POLICY:
            raise GenerationError(f"opcode {call.opcode} has no Web policy entry")
        grouped.setdefault(call.shape, []).append(call)
    for calls in grouped.values():
        calls.sort(key=lambda c: c.opcode)
    return grouped


def _slot(opcode: int) -> str:
    slot = WEB_POLICY[opcode].get("vec2_slot")
    if slot is None:
        raise GenerationError(f"opcode {opcode} missing vec2_slot")
    return str(slot)


def _register(ret: str, token_expr: str, *, receiver: bool = False) -> str:
    if ret == "node":
        return f"registerReturnedNode({token_expr})"
    if ret == "browser":
        return f"registerReturnedBrowserObject({token_expr})"
    if ret == "existing":
        return f"existingReturnedObject(receiver, {token_expr})"
    raise GenerationError(f"unknown return wrapper {ret!r}")


# --------------------------------------------------------------------------------------------------
# Shape body emitters. Each returns the statements inside the SPI override (the method wrapper,
# signature, and `requireOpcode(...)` line are emitted by `_method`). Structure is shape-inherent;
# per-opcode leaves come from WEB_POLICY.
# --------------------------------------------------------------------------------------------------

_IMMEDIATE = "GodotExecutionMode.IMMEDIATE_RESULT"
_QUEUED = "GodotExecutionMode.QUEUED_MUTATION"
_SNAPSHOT = "GodotExecutionMode.SNAPSHOT_READ"


def _only(calls: list[BackendCallPolicy]) -> BackendCallPolicy:
    if len(calls) != 1:
        raise GenerationError(f"shape expected exactly one opcode, got {[c.opcode for c in calls]}")
    return calls[0]


def _opcode_guard(calls: list[BackendCallPolicy]) -> str:
    """Membership guard over a shape's admitted opcodes.

    Set membership (not a contiguous range) so a shape can admit non-adjacent opcodes as the corpus
    grows — e.g. Node2D.set_rotation joining the audio DOUBLE_ARG family.
    """
    opcodes = [c.opcode for c in calls]
    if len(opcodes) == 1:
        return f"descriptor.opcode == {opcodes[0]}"
    return "descriptor.opcode in setOf(" + ", ".join(str(o) for o in opcodes) + ")"


def body_BOOL_RET_INT(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "commands.flush() // explicit ordering barrier for mutations issued before this result",
        "return immediateWebChildCount(receiver.webId(), value)",
    ]


def body_BOOL_RET_HANDLE(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "commands.flush()",
        "return existingReturnedObject(",
        "receiver,",
        "immediateWebTweenBoolRetObject(descriptor.opcode, receiver.webId(), value),",
        ")",
    ]


def body_BOOL_ARG(calls):
    lines = [
        f"require(descriptor.executionMode == {_QUEUED})",
        f"require({_opcode_guard(calls)})",
        "val objectId = receiver.webId()",
        "commands.appendBoolMutation(descriptor.opcode, objectId, value)",
    ]
    snapshot_arms = [c for c in calls if WEB_POLICY[c.opcode].get("bool_snapshot") == "emitting"]
    if snapshot_arms:
        lines.append("when (descriptor.opcode) {")
        for c in snapshot_arms:
            lines.append(f"{c.opcode} -> webWriteEmittingSnapshot(objectId, value)")
        lines.append("else -> {}")
        lines.append("}")
    return lines


def body_DOUBLE_ARG(calls):
    return [
        f"require(descriptor.executionMode == {_QUEUED})",
        f"require({_opcode_guard(calls)})",
        "require(value.isFinite()) {",
        '"Kanama Web ${descriptor.className}.${descriptor.methodName} requires a finite Double"',
        "}",
        "commands.appendDoubleMutation(descriptor.opcode, receiver.webId(), value)",
    ]


def body_NOARGS_RET_VECTOR2(calls):
    snapshot_arms = [c for c in calls if c.execution_mode.value == "SNAPSHOT_READ"]
    lines = ["return when (descriptor.executionMode) {"]
    if snapshot_arms:
        lines.append(f"{_SNAPSHOT} ->")
        lines.append("when (descriptor.opcode) {")
        for c in snapshot_arms:
            lines.append(f"{c.opcode} -> webVector2Snapshot(receiver.webId(), {_slot(c.opcode)})")
        lines.append("else -> null")
        lines.append("}")
        lines.append("?: error(")
        lines.append('"Missing Web ${descriptor.className}.${descriptor.methodName} snapshot for " +')
        lines.append('"object handle=${receiver.webId()}"')
        lines.append(")")
    lines += [
        f"{_IMMEDIATE} -> {{",
        "commands.flush()",
        "GodotVector2(",
        "immediateWebNoArgsVector2X(descriptor.opcode, receiver.webId()).toFloat(),",
        "immediateWebNoArgsVector2Y().toFloat(),",
        ")",
        "}",
        f"{_QUEUED} ->",
        'error("Vector2 return cannot use queued execution for opcode=${descriptor.opcode}")',
        "}",
    ]
    return lines


def body_VECTOR2_ARG(calls):
    lines = [
        f"require(descriptor.executionMode == {_QUEUED})",
        "val objectId = receiver.webId()",
        "commands.appendVector2Mutation(descriptor.opcode, objectId, value.x, value.y)",
        "when (descriptor.opcode) {",
    ]
    for c in calls:
        if WEB_POLICY[c.opcode].get("vec2_slot") is not None:
            lines.append(
                f"{c.opcode} -> webWriteVector2Snapshot(objectId, {_slot(c.opcode)}, value)"
            )
        else:
            lines.append(f"{c.opcode} -> {{}}")
    lines.append('else -> error("Unsupported Web Vector2 mutation opcode=${descriptor.opcode}")')
    lines.append("}")
    return lines


def body_NOARGS_RET_RECT2(calls):
    return [
        f"require(descriptor.executionMode == {_SNAPSHOT})",
        "return webViewportRectSnapshot(receiver.webId())",
        '?: error("Missing Web viewport snapshot for object handle=${receiver.webId()}")',
    ]


def body_NOARGS_VOID(calls):
    free_op = next((c.opcode for c in calls if WEB_POLICY[c.opcode].get("on_queue_free")), None)
    immediate = [c for c in calls if c.execution_mode.value == "IMMEDIATE_RESULT"]
    lines = [
        "val objectId = receiver.webId()",
        "when (descriptor.executionMode) {",
        f"{_QUEUED} -> {{",
        "commands.appendNoArgsMutation(descriptor.opcode, objectId)",
    ]
    if free_op is not None:
        lines.append(f"if (descriptor.opcode == {free_op}) onWebQueueFree(objectId)")
    lines.append("}")
    kill_op = _only(immediate).opcode
    lines += [
        f"{_IMMEDIATE} -> {{",
        f"require(descriptor.opcode == {kill_op})",
        "commands.flush()",
        "check(immediateWebTweenNoArgs(descriptor.opcode, objectId) == 1) {",
        '"Kanama Web Tween.kill failed for handle=$objectId"',
        "}",
        "}",
        f"{_SNAPSHOT} ->",
        'error("Void call cannot use snapshot execution for opcode=${descriptor.opcode}")',
        "}",
    ]
    return lines


def body_TEXTURE2D_VECTOR2_COLOR_ARGS(calls):
    return [
        f"require(descriptor.executionMode == {_QUEUED})",
        "requireWebBrowserHandle(texture.webId(), WebBrowserHandleKind.RESOURCE)",
        "drawCommands.appendDrawTexture(",
        "descriptor.opcode,",
        "receiver.webId(),",
        "texture.webId(),",
        "position.x,",
        "position.y,",
        "modulate.r,",
        "modulate.g,",
        "modulate.b,",
        "modulate.a,",
        ")",
    ]


def body_STRING_STRING_LONG_RET_HANDLE(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "require(value in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())",
        "commands.flush()",
        "return registerLoadedResource(immediateWebResourceLoad(first, second, value.toInt()))",
    ]


def body_STRINGNAME_INT_RET_INT(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "commands.flush()",
        "return immediateWebEmitSignal(receiver.webId(), name, value)",
    ]


def body_UTILITY_NOARGS_VOID(calls):
    return [f"require(descriptor.executionMode == {_IMMEDIATE})", "WebRandom.randomize()"]


def body_UTILITY_NOARGS_RET_LONG(calls):
    return [f"require(descriptor.executionMode == {_IMMEDIATE})", "return WebRandom.randi()"]


def body_UTILITY_NOARGS_RET_DOUBLE(calls):
    return [f"require(descriptor.executionMode == {_IMMEDIATE})", "return WebRandom.randf()"]


def body_STRINGNAME_RET_HANDLE(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "commands.flush()",
        "return registerConstructedNode(immediateWebConstructObject(value), value)",
    ]


def body_STRINGNAME_RET_INT(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "commands.flush()",
        "return immediateWebEmitSignalNoArgs(receiver.webId(), value)",
    ]


def body_OBJECT_BOOL_LONG_ARGS(calls):
    return [
        f"require(descriptor.executionMode == {_QUEUED})",
        "requireWebNodeHandle(objectValue.webId())",
        "commands.appendObjectBoolLongArgs(",
        "descriptor.opcode,",
        "receiver.webId(),",
        "objectValue.webId(),",
        "boolValue,",
        "longValue,",
        ")",
    ]


def body_OBJECT_ARG(calls):
    lines = ["when (descriptor.opcode) {"]
    for c in calls:
        op = c.opcode
        if op == 14:
            lines += [
                "14 -> {",
                f"require(descriptor.executionMode == {_QUEUED})",
                "requireWebNodeHandle(checkNotNull(value).webId())",
                "}",
            ]
        elif op == 16:
            lines += [
                "16 -> {",
                f"require(descriptor.executionMode == {_QUEUED})",
                "value?.let { requireWebBrowserHandle(it.webId(), WebBrowserHandleKind.RESOURCE) }",
                "webWriteTextureSnapshot(receiver.webId(), value?.webId() ?: 0)",
                "}",
            ]
        elif op == 46:
            lines += [
                "46 -> {",
                f"require(descriptor.executionMode == {_IMMEDIATE})",
                "value?.let { requireWebBrowserHandle(it.webId(), WebBrowserHandleKind.RESOURCE) }",
                "}",
            ]
        else:
            raise GenerationError(f"OBJECT_ARG opcode {op} has no emitter arm")
    lines += [
        'else -> error("Unsupported Web object-argument opcode=${descriptor.opcode}")',
        "}",
        "commands.appendObjectArg(descriptor.opcode, receiver.webId(), value?.webId() ?: 0)",
        "if (descriptor.opcode == 46) commands.flush()",
    ]
    return lines


def body_NODEPATH_RET_HANDLE(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "commands.flush()",
        "return registerReturnedNode(immediateWebNodeLookup(receiver.webId(), path))",
    ]


def body_LONG_ARG(calls):
    op = _only(calls).opcode
    return [
        f"require(descriptor.executionMode == {_QUEUED})",
        f"require(descriptor.opcode == {op})",
        "require(value in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong()) {",
        '"Kanama Web SceneTree.quit exit code must fit Godot\'s int32 ABI"',
        "}",
        "commands.appendLongMutation(descriptor.opcode, receiver.webId(), value)",
    ]


def body_LONG_RET_HANDLE(calls):
    lines = [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "require(value in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())",
        "commands.flush()",
        "return when (descriptor.opcode) {",
    ]
    node_ops = [c.opcode for c in calls if WEB_POLICY[c.opcode].get("ret") == "node"]
    existing_ops = [c.opcode for c in calls if WEB_POLICY[c.opcode].get("ret") == "existing"]
    for op in node_ops:
        lines.append(f"{op} ->")
        lines.append(
            "registerReturnedNode(immediateWebPackedSceneInstantiate(receiver.webId(), value.toInt()))"
        )
    if existing_ops:
        lines.append(",\n".join(str(op) for op in existing_ops) + " ->")
        lines += [
            "existingReturnedObject(",
            "receiver,",
            "immediateWebTweenLongRetObject(descriptor.opcode, receiver.webId(), value.toInt()),",
            ")",
        ]
    lines.append('else -> error("Unsupported Web Long-return-handle opcode=${descriptor.opcode}")')
    lines.append("}")
    return lines


def body_NOARGS_RET_HANDLE(calls):
    immediate = [c for c in calls if c.execution_mode.value == "IMMEDIATE_RESULT"]
    snapshot = [c for c in calls if c.execution_mode.value == "SNAPSHOT_READ"]
    lines = [
        "return when (descriptor.executionMode) {",
        f"{_IMMEDIATE} -> {{",
        "commands.flush()",
        "val token = immediateWebNoArgsObject(descriptor.opcode, receiver.webId())",
        "when (descriptor.opcode) {",
    ]
    for c in immediate:
        ret = WEB_POLICY[c.opcode].get("ret")
        lines.append(f"{c.opcode} -> {_register(ret, 'token')}")
    lines += [
        'else -> error("Unsupported Web no-args-object opcode=${descriptor.opcode}")',
        "}",
        "}",
    ]
    snap = _only(snapshot).opcode
    lines += [
        f"{_SNAPSHOT} -> {{",
        f"require(descriptor.opcode == {snap})",
        "val objectId = receiver.webId()",
        "val textureId =",
        "webTextureSnapshot(objectId)",
        '?: error("Missing Web Sprite2D.get_texture snapshot for object handle=$objectId")',
        "textureId.takeIf { it > 0 }?.let { GodotHandle.fromBackendToken(it.toLong()) }",
        "}",
        f"{_QUEUED} ->",
        'error("Handle return cannot use queued execution for opcode=${descriptor.opcode}")',
        "}",
    ]
    return lines


def body_OBJECT_LONG_VECTOR2_ARGS(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "require(longValue in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())",
        "objectValue?.let { requireWebBrowserHandle(it.webId(), WebBrowserHandleKind.RESOURCE) }",
        "commands.flush()",
        "immediateWebSetCustomMouseCursor(",
        "requireActiveWebScriptHandle(),",
        "objectValue?.webId() ?: 0,",
        "longValue.toInt(),",
        "vectorValue.x.toDouble(),",
        "vectorValue.y.toDouble(),",
        ")",
    ]


def body_STRINGNAME_CALLABLE_LONG_RET_LONG(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "require(flags in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())",
        "commands.flush()",
        "return immediateWebConnect(receiver.webId(), signal, target.webId(), method, flags.toInt())",
        ".toLong()",
    ]


def body_STRINGNAME_BOUND_CALLABLE_LONG_RET_LONG(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "require(boundValue in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())",
        "require(flags in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())",
        "commands.flush()",
        "return immediateWebConnectBound(",
        "receiver.webId(),",
        "signal,",
        "target.webId(),",
        "method,",
        "boundValue.toInt(),",
        "flags.toInt(),",
        ")",
        ".toLong()",
    ]


def body_STRINGNAME_RET_BOOL(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "commands.flush()",
        "return immediateWebObjectQuery(descriptor.opcode, receiver.webId(), value) != 0",
    ]


def body_NOARGS_RET_BOOL(calls):
    snapshot = [c for c in calls if c.execution_mode.value == "SNAPSHOT_READ"]
    snap = _only(snapshot).opcode
    return [
        "return when (descriptor.executionMode) {",
        f"{_SNAPSHOT} -> {{",
        f"require(descriptor.opcode == {snap})",
        "webEmittingSnapshot(receiver.webId())",
        "?: error(",
        '"Missing Web GPUParticles2D.is_emitting snapshot for object handle=${receiver.webId()}"',
        ")",
        "}",
        f"{_IMMEDIATE} -> {{",
        "commands.flush()",
        'immediateWebObjectQuery(descriptor.opcode, receiver.webId(), "") != 0',
        "}",
        f"{_QUEUED} ->",
        'error("Boolean return cannot use queued execution for opcode=${descriptor.opcode}")',
        "}",
    ]


def body_NOARGS_RET_DOUBLE(calls):
    op = _only(calls).opcode
    return [
        f"require(descriptor.executionMode == {_SNAPSHOT})",
        f"require(descriptor.opcode == {op})",
        "return webLifetimeSnapshot(receiver.webId())",
        "?: error(",
        '"Missing Web GPUParticles2D.get_lifetime snapshot for object handle=${receiver.webId()}"',
        ")",
    ]


def body_NOARGS_RET_LONG(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "commands.flush()",
        'return immediateWebObjectQuery(descriptor.opcode, receiver.webId(), "").toLong()',
    ]


def body_STRINGNAME_ARG(calls):
    return [
        f"require(descriptor.executionMode == {_QUEUED})",
        f"require({_opcode_guard(calls)})",
        "commands.appendStringNameMutation(descriptor.opcode, receiver.webId(), value)",
    ]


def body_STRINGNAME_BOOL_ARG(calls):
    return [
        f"require(descriptor.executionMode == {_QUEUED})",
        f"require({_opcode_guard(calls)})",
        "commands.appendStringNameBoolMutation(descriptor.opcode, receiver.webId(), name, value)",
    ]


def body_STRINGNAME_VECTOR2I_RET_INT(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "commands.flush()",
        "return immediateWebEmitSignalVector2i(receiver.webId(), name, value.x, value.y)",
    ]


def body_NOARGS_RET_COLOR(calls):
    return [
        f"require(descriptor.executionMode == {_SNAPSHOT})",
        "return webModulateSnapshot(receiver.webId())",
        '?: error("Missing Web CanvasItem.get_modulate snapshot for object handle=${receiver.webId()}")',
    ]


def body_COLOR_ARG(calls):
    return [
        f"require(descriptor.executionMode == {_QUEUED})",
        "commands.appendColorMutation(",
        "descriptor.opcode,",
        "receiver.webId(),",
        "value.r,",
        "value.g,",
        "value.b,",
        "value.a,",
        ")",
        "webWriteModulateSnapshot(receiver.webId(), value)",
    ]


def body_OBJECT_NODEPATH_VECTOR2_DOUBLE_RET_HANDLE(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "require(duration.isFinite() && duration >= 0.0)",
        "commands.flush()",
        "return registerReturnedBrowserObject(",
        "immediateWebTweenPropertyVector2(",
        "descriptor.opcode,",
        "receiver.webId(),",
        "target.webId(),",
        "property,",
        "finalValue.x.toDouble(),",
        "finalValue.y.toDouble(),",
        "duration,",
        ")",
        ")",
    ]


def body_OBJECT_NODEPATH_COLOR_DOUBLE_RET_HANDLE(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "require(duration.isFinite() && duration >= 0.0)",
        "commands.flush()",
        "return registerReturnedBrowserObject(",
        "immediateWebTweenPropertyColor(",
        "descriptor.opcode,",
        "receiver.webId(),",
        "target.webId(),",
        "property,",
        "finalValue.r.toDouble(),",
        "finalValue.g.toDouble(),",
        "finalValue.b.toDouble(),",
        "finalValue.a.toDouble(),",
        "duration,",
        ")",
        ")",
    ]


# Signature: (params-after-descriptor/callSite, return-type). `receiver` present unless noted.
SIGNATURES: dict[str, tuple[list[str], str]] = {
    "BOOL_RET_INT": (["receiver: GodotHandle", "value: Boolean"], "Int"),
    "BOOL_RET_HANDLE": (["receiver: GodotHandle", "value: Boolean"], "GodotHandle?"),
    "BOOL_ARG": (["receiver: GodotHandle", "value: Boolean"], ""),
    "DOUBLE_ARG": (["receiver: GodotHandle", "value: Double"], ""),
    "NOARGS_RET_VECTOR2": (["receiver: GodotHandle"], "GodotVector2"),
    "VECTOR2_ARG": (["receiver: GodotHandle", "value: GodotVector2"], ""),
    "NOARGS_RET_RECT2": (["receiver: GodotHandle"], "GodotRect2"),
    "NOARGS_VOID": (["receiver: GodotHandle"], ""),
    "TEXTURE2D_VECTOR2_COLOR_ARGS": (
        [
            "receiver: GodotHandle",
            "texture: GodotHandle",
            "position: GodotVector2",
            "modulate: GodotColor",
        ],
        "",
    ),
    "STRING_STRING_LONG_RET_HANDLE": (
        ["first: String", "second: String", "value: Long"],
        "GodotHandle?",
    ),
    "STRINGNAME_INT_RET_INT": (
        ["receiver: GodotHandle", "name: String", "value: Int"],
        "Int",
    ),
    "UTILITY_NOARGS_VOID": ([], ""),
    "UTILITY_NOARGS_RET_LONG": ([], "Long"),
    "UTILITY_NOARGS_RET_DOUBLE": ([], "Double"),
    "STRINGNAME_RET_HANDLE": (["value: String"], "GodotHandle?"),
    "OBJECT_BOOL_LONG_ARGS": (
        [
            "receiver: GodotHandle",
            "objectValue: GodotHandle",
            "boolValue: Boolean",
            "longValue: Long",
        ],
        "",
    ),
    "OBJECT_ARG": (["receiver: GodotHandle", "value: GodotHandle?"], ""),
    "NODEPATH_RET_HANDLE": (["receiver: GodotHandle", "path: String"], "GodotHandle?"),
    "LONG_ARG": (["receiver: GodotHandle", "value: Long"], ""),
    "LONG_RET_HANDLE": (["receiver: GodotHandle", "value: Long"], "GodotHandle?"),
    "NOARGS_RET_HANDLE": (["receiver: GodotHandle"], "GodotHandle?"),
    "OBJECT_LONG_VECTOR2_ARGS": (
        ["objectValue: GodotHandle?", "longValue: Long", "vectorValue: GodotVector2"],
        "",
    ),
    "STRINGNAME_CALLABLE_LONG_RET_LONG": (
        [
            "receiver: GodotHandle",
            "signal: String",
            "target: GodotHandle",
            "method: String",
            "flags: Long",
        ],
        "Long",
    ),
    "STRINGNAME_BOUND_CALLABLE_LONG_RET_LONG": (
        [
            "receiver: GodotHandle",
            "signal: String",
            "target: GodotHandle",
            "method: String",
            "boundValue: Long",
            "flags: Long",
        ],
        "Long",
    ),
    "STRINGNAME_RET_INT": (["receiver: GodotHandle", "value: String"], "Int"),
    "STRINGNAME_RET_BOOL": (["receiver: GodotHandle", "value: String"], "Boolean"),
    "NOARGS_RET_BOOL": (["receiver: GodotHandle"], "Boolean"),
    "NOARGS_RET_DOUBLE": (["receiver: GodotHandle"], "Double"),
    "NOARGS_RET_LONG": (["receiver: GodotHandle"], "Long"),
    "STRINGNAME_ARG": (["receiver: GodotHandle", "value: String"], ""),
    "STRINGNAME_BOOL_ARG": (
        ["receiver: GodotHandle", "name: String", "value: Boolean"],
        "",
    ),
    "STRINGNAME_VECTOR2I_RET_INT": (
        ["receiver: GodotHandle", "name: String", "value: GodotVector2i"],
        "Int",
    ),
    "NOARGS_RET_COLOR": (["receiver: GodotHandle"], "GodotColor"),
    "COLOR_ARG": (["receiver: GodotHandle", "value: GodotColor"], ""),
    "OBJECT_NODEPATH_VECTOR2_DOUBLE_RET_HANDLE": (
        [
            "receiver: GodotHandle",
            "target: GodotHandle",
            "property: String",
            "finalValue: GodotVector2",
            "duration: Double",
        ],
        "GodotHandle?",
    ),
    "OBJECT_NODEPATH_COLOR_DOUBLE_RET_HANDLE": (
        [
            "receiver: GodotHandle",
            "target: GodotHandle",
            "property: String",
            "finalValue: GodotColor",
            "duration: Double",
        ],
        "GodotHandle?",
    ),
}


_WORD_CASE = {
    "NOARGS": "NoArgs",
    "RET": "Ret",
    "ARG": "Arg",
    "ARGS": "Args",
    "VOID": "Void",
    "BOOL": "Bool",
    "INT": "Int",
    "LONG": "Long",
    "DOUBLE": "Double",
    "HANDLE": "Handle",
    "COLOR": "Color",
    "RECT2": "Rect2",
    "VECTOR2": "Vector2",
    "VECTOR2I": "Vector2i",
    "STRING": "String",
    "STRINGNAME": "StringName",
    "NODEPATH": "NodePath",
    "TEXTURE2D": "Texture2D",
    "OBJECT": "Object",
    "UTILITY": "Utility",
    "CALLABLE": "Callable",
    "BOUND": "Bound",
}


def _method_name(shape: str) -> str:
    return "invoke" + "".join(_WORD_CASE[part] for part in shape.split("_"))


# Emit order matches the committed file (grouping and order are cosmetic; the drift gate is
# whitespace/order-insensitive within tokens but we keep a stable order for clean diffs).
EMIT_ORDER = [
    "BOOL_RET_INT",
    "BOOL_RET_HANDLE",
    "BOOL_ARG",
    "DOUBLE_ARG",
    "LONG_ARG",
    "NOARGS_RET_VECTOR2",
    "VECTOR2_ARG",
    "NOARGS_RET_RECT2",
    "NOARGS_VOID",
    "TEXTURE2D_VECTOR2_COLOR_ARGS",
    "STRING_STRING_LONG_RET_HANDLE",
    "UTILITY_NOARGS_VOID",
    "UTILITY_NOARGS_RET_LONG",
    "UTILITY_NOARGS_RET_DOUBLE",
    "STRINGNAME_INT_RET_INT",
    "STRINGNAME_RET_HANDLE",
    "STRINGNAME_RET_INT",
    "OBJECT_BOOL_LONG_ARGS",
    "OBJECT_ARG",
    "STRINGNAME_ARG",
    "STRINGNAME_BOOL_ARG",
    "NODEPATH_RET_HANDLE",
    "LONG_RET_HANDLE",
    "NOARGS_RET_HANDLE",
    "OBJECT_LONG_VECTOR2_ARGS",
    "STRINGNAME_CALLABLE_LONG_RET_LONG",
    "STRINGNAME_BOUND_CALLABLE_LONG_RET_LONG",
    "STRINGNAME_RET_BOOL",
    "NOARGS_RET_BOOL",
    "NOARGS_RET_DOUBLE",
    "NOARGS_RET_LONG",
    "STRINGNAME_VECTOR2I_RET_INT",
    "NOARGS_RET_COLOR",
    "COLOR_ARG",
    "OBJECT_NODEPATH_VECTOR2_DOUBLE_RET_HANDLE",
    "OBJECT_NODEPATH_COLOR_DOUBLE_RET_HANDLE",
]


def _method(shape: str, calls: list[BackendCallPolicy]) -> list[str]:
    params, ret = SIGNATURES[shape]
    emitter = globals()[f"body_{shape}"]
    header = ["descriptor: GodotCallDescriptor", "callSite: GodotCallSite", *params]
    ret_suffix = f": {ret}" if ret else ""
    lines = [f"override fun {_method_name(shape)}("]
    lines += [f"{p}," for p in header]
    lines.append(f"){ret_suffix} {{")
    lines.append("requireOpcode(descriptor, callSite)")
    lines += emitter(calls)
    lines.append("}")
    return lines


HEADER = """// Generated by scripts/generate_web_backend.py — do not edit.
@file:OptIn(ExperimentalWasmJsInterop::class, InternalKanamaBackendApi::class)

package net.multigesture.kanama.web

import kotlin.js.ExperimentalWasmJsInterop
import net.multigesture.kanama.backend.GodotBackendCalls
import net.multigesture.kanama.backend.GodotBackendSpi
import net.multigesture.kanama.backend.GodotCallDescriptor
import net.multigesture.kanama.backend.GodotCallSite
import net.multigesture.kanama.backend.GodotColor
import net.multigesture.kanama.backend.GodotExecutionMode
import net.multigesture.kanama.backend.GodotHandle
import net.multigesture.kanama.backend.GodotRect2
import net.multigesture.kanama.backend.GodotVector2
import net.multigesture.kanama.backend.GodotVector2i
import net.multigesture.kanama.backend.InternalKanamaBackendApi

/**
 * Kotlin/Wasm backend dispatch: opcode routing, execution-mode guards, and JS-bridge codec calls.
 *
 * Generated from scripts/platform_backend_calls.json + the Web-local policy in
 * scripts/generate_web_backend.py. Web-only state (property snapshots, browser handle-kind tracking,
 * free-time cache clearing) lives hand-written in WebBackendBookkeeping.kt and is reached through its
 * hooks; the js(...) transport primitives live hand-written in WebBackendTransport.kt. See
 * docs/contributing/web-internals.md ("Backend-dispatch codegen").
 */
internal object WebCommonGodotBackend : GodotBackendSpi {
  override fun requireLive(handle: GodotHandle) {
    val token = handle.webId()
    if (!instances.isLive(token)) {
      check(containsWebBrowserHandle(token)) { "Stale Kanama Web browser handle=$token" }
    }
  }

  override fun resolve(descriptor: GodotCallDescriptor): GodotCallSite =
    GodotCallSite.fromBackendToken(descriptor.opcode.toLong())
"""

FOOTER = """
  private fun requireOpcode(descriptor: GodotCallDescriptor, callSite: GodotCallSite) {
    require(callSite.backendToken() == descriptor.opcode.toLong()) {
      "Web Godot call-site opcode does not match ${descriptor.className}.${descriptor.methodName}"
    }
  }
}

internal fun installWebCommonGodotBackend() {
  GodotBackendCalls.install(WebCommonGodotBackend)
}
"""


def render() -> str:
    grouped = _by_shape()
    missing = [s for s in grouped if s not in SIGNATURES]
    if missing:
        raise GenerationError(f"call shapes with no Web emitter: {sorted(missing)}")
    unordered = set(grouped) - set(EMIT_ORDER)
    if unordered:
        raise GenerationError(f"call shapes missing from EMIT_ORDER: {sorted(unordered)}")

    body: list[str] = []
    for shape in EMIT_ORDER:
        if shape not in grouped:
            raise GenerationError(f"EMIT_ORDER shape {shape} has no admitted opcodes")
        body.append("")
        body += _method(shape, grouped[shape])

    return HEADER + "\n".join(body) + FOOTER


_COMMENTS = re.compile(r"/\*.*?\*/|//[^\n]*", re.S)
# String literals stay one token; identifiers/numbers are one token; every other non-space char is
# its own token. This makes the drift compare fully insensitive to formatting and line wrapping.
_TOKEN = re.compile(r'"(?:\\.|[^"\\])*"|[A-Za-z0-9_]+|\S', re.S)
_CLOSERS = {")", "]", "}", ">"}


def _normalize(text: str) -> str:
    """Formatting-insensitive token stream for the drift gate.

    Ignores whitespace, line wrapping, comments, and trailing commas (ktfmt varies all of these when
    a construct wraps; Kotlin trailing commas are purely cosmetic) while preserving string-literal
    contents. Any real change — a different arm, opcode, extern, or guard — still changes the stream.
    """
    tokens: list[str] = []
    for token in _TOKEN.findall(_COMMENTS.sub(" ", text)):
        if token in _CLOSERS and tokens and tokens[-1] == ",":
            tokens.pop()
        tokens.append(token)
    return " ".join(tokens)


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--output", type=Path, required=True)
    parser.add_argument("--check", action="store_true")
    args = parser.parse_args()

    generated = render()
    if args.check:
        current = args.output.read_text() if args.output.is_file() else ""
        if _normalize(current) != _normalize(generated):
            print(
                f"[web_backend] FAIL generated Web backend dispatch drift: {args.output}",
                file=sys.stderr,
            )
            return 1
        print("[web_backend] PASS")
        return 0

    args.output.parent.mkdir(parents=True, exist_ok=True)
    args.output.write_text(generated)
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
