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

# Snapshot slots for the mirrored Node3D Vector3 properties.
_POSITION3 = "WebVector3Slot.POSITION"
_ROTATION3 = "WebVector3Slot.ROTATION"
_SCALE3 = "WebVector3Slot.SCALE"
_VELOCITY3 = "WebVector3Slot.VELOCITY"
_ROTATION_DEGREES3 = "WebVector3Slot.ROTATION_DEGREES"
_TARGET_POSITION3 = "WebVector3Slot.TARGET_POSITION"

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
    45: {"double_snapshot": "lifetime"},
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
    65: {"immediate_double_extern": "immediateWebSetProgressRatio"},
    66: {},
    67: {},
    68: {},
    69: {},
    70: {"double_snapshot": "rotation"},
    71: {"ret": "browser"},
    72: {},
    73: {"ret": "node"},
    74: {"vec3_slot": _POSITION3},
    75: {"vec3_slot": _POSITION3},
    76: {"vec3_slot": _ROTATION3},
    77: {"vec3_slot": _ROTATION3},
    78: {"vec3_slot": _SCALE3},
    79: {"vec3_slot": _SCALE3},
    80: {},
    81: {"ret": "browser"},
    82: {},
    83: {},
    84: {},
    85: {},
    86: {},
    87: {},
    88: {"vec3_slot": _VELOCITY3},
    89: {"vec3_slot": _VELOCITY3},
    90: {},
    91: {},
    92: {},
    93: {},
    94: {},
    95: {},
    96: {},
    97: {},
    98: {},
    99: {},
    100: {},
    101: {"vec3_slot": _ROTATION_DEGREES3},
    102: {"vec3_slot": _ROTATION_DEGREES3},
    103: {},
    104: {},
    105: {},
    106: {},
    107: {"immediate_double_extern": "immediateWebSetProgressRatio3D"},
    108: {},
    109: {"immediate_double_extern": "immediateWebRotateY"},
    110: {},
    111: {"ret": "collision"},
    112: {"ret": "node"},
    113: {},
    114: {"ret": "node"},
    115: {},
    116: {},
    117: {},
    118: {"vec3_slot": _TARGET_POSITION3},
    119: {"vec3_slot": _TARGET_POSITION3},
    120: {},
    121: {},
    122: {"ret": "node"},
    123: {},
    124: {},
    125: {},
    126: {},
    127: {},
    128: {},
    129: {},
    130: {},
    131: {},
    132: {},
    133: {"ret": "child"},
    134: {},
    135: {"ret": "existing"},
    136: {},
    137: {},
    138: {},
    139: {},
    140: {},
    141: {},
    142: {"immediate_vec3_query": True},
    143: {"immediate_vec3_query": True},
    144: {},
    145: {},
    146: {},
    147: {},
    148: {},
    149: {},
    150: {},
    151: {},
    152: {},
    153: {},
    154: {},
    155: {},
    156: {},
    157: {},
    158: {},
    159: {},
    160: {},
    161: {},
    162: {},
    163: {},
    164: {},
    165: {},
    166: {},
    167: {},
    168: {},
    169: {},
    170: {},
    171: {},
    172: {},
    173: {},
    174: {"ret": "indexed_node"},
    175: {},
    176: {},
    177: {},
    178: {},
    179: {},
    180: {},
    181: {},
    182: {},
    183: {},
    184: {},
    185: {},
    186: {},
    187: {},
    188: {},
    189: {},
    190: {},
    191: {},
    192: {},
    193: {},
    194: {"ret": "node"},
    195: {},
    196: {},
    197: {},
    198: {},
    199: {},
    200: {},
    201: {},
    202: {},
    203: {},
    204: {},
    205: {},
    206: {},
    207: {},
    208: {},
    209: {},
    210: {},
    211: {},
    212: {"ret": "browser"},
    213: {},
    214: {},
    215: {},
    216: {},
    217: {"ret": "browser"},
    218: {},
    219: {},
    220: {},
    221: {},
    222: {"ret": "browser"},
    223: {},
    224: {},
    225: {"ret": "browser"},
    226: {},
    227: {},
    228: {},
    229: {},
    230: {},
    231: {},
    232: {},
    233: {},
    234: {},
    235: {},
    236: {},
    237: {},
    238: {},
    239: {},
    240: {},
    241: {},
    242: {},
    243: {"ret": "indexed_browser"},
    244: {"ret": "indexed_browser"},
    245: {},
    246: {"ret": "browser"},
    247: {},
    248: {},
    249: {},
    250: {"ret": "node"},
    251: {},
    252: {},
    253: {},
    254: {},
    255: {},
    256: {},
    257: {},
    258: {},
    259: {},
    260: {},
    261: {},
    262: {},
    263: {},
    264: {},
    265: {},
    266: {},
    267: {},
    268: {},
    269: {},
    270: {},
    271: {},
    272: {},
    273: {},
    274: {},
    275: {},
    276: {},
    277: {},
    278: {},
    279: {},
    280: {},
    281: {},
    282: {},
    283: {},
    284: {},
    285: {},
    286: {},
    287: {},
    288: {},
    289: {},
    290: {},
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


def _slot3(opcode: int) -> str:
    slot = WEB_POLICY[opcode].get("vec3_slot")
    if slot is None:
        raise GenerationError(f"opcode {opcode} missing vec3_slot")
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
    tween = [c for c in calls if WEB_POLICY[c.opcode].get("ret") != "browser"]
    browser = [c for c in calls if WEB_POLICY[c.opcode].get("ret") == "browser"]
    lines = [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "commands.flush()",
        "return when (descriptor.opcode) {",
    ]
    if tween:
        lines.append(",\n".join(str(c.opcode) for c in tween) + " ->")
        lines += [
            "existingReturnedObject(",
            "receiver,",
            "immediateWebTweenBoolRetObject(descriptor.opcode, receiver.webId(), value),",
            ")",
        ]
    for c in browser:
        lines.append(f"{c.opcode} ->")
        lines += [
            "// The Boolean rides the property-object-query string; the bridge appends the",
            "// proposed handle and the applier registers the duplicate under it.",
            "registerReturnedBrowserObject(",
            "immediateWebPropertyObjectQuery(",
            "descriptor.opcode,",
            "receiver.webId(),",
            'if (value) "1" else "0",',
            ")",
            ")",
        ]
    lines += [
        'else -> error("Unsupported Web Boolean-return-handle opcode=${descriptor.opcode}")',
        "}",
    ]
    return lines


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
    queued = [c for c in calls if c.execution_mode.value == "QUEUED_MUTATION"]
    immediate = [c for c in calls if c.execution_mode.value == "IMMEDIATE_RESULT"]
    finite = [
        "require(value.isFinite()) {",
        '"Kanama Web ${descriptor.className}.${descriptor.methodName} requires a finite Double"',
        "}",
    ]
    if not immediate:
        return [
            f"require(descriptor.executionMode == {_QUEUED})",
            f"require({_opcode_guard(queued)})",
            *finite,
            "commands.appendDoubleMutation(descriptor.opcode, receiver.webId(), value)",
        ]
    lines = [*finite, "when (descriptor.executionMode) {"]
    if queued:
        lines += [
            f"{_QUEUED} -> {{",
            f"require({_opcode_guard(queued)})",
            "commands.appendDoubleMutation(descriptor.opcode, receiver.webId(), value)",
            "}",
        ]
    lines += [f"{_IMMEDIATE} -> {{", f"require({_opcode_guard(immediate)})", "commands.flush()", "when (descriptor.opcode) {"]
    for c in immediate:
        extern = WEB_POLICY[c.opcode]["immediate_double_extern"]
        lines.append(f"{c.opcode} -> {extern}(receiver.webId(), value)")
    lines += [
        'else -> error("Unsupported Web immediate Double opcode=${descriptor.opcode}")',
        "}",
        "}",
        f"{_SNAPSHOT} ->",
        'error("Double argument cannot use snapshot execution for opcode=${descriptor.opcode}")',
        "}",
    ]
    return lines


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


def body_NOARGS_RET_VECTOR3(calls):
    snapshot_arms = [c for c in calls if c.execution_mode.value == "SNAPSHOT_READ"]
    immediate = [c for c in calls if c.execution_mode.value == "IMMEDIATE_RESULT"]
    lines = ["return when (descriptor.executionMode) {"]
    lines.append(f"{_SNAPSHOT} ->")
    lines.append("when (descriptor.opcode) {")
    for c in snapshot_arms:
        lines.append(f"{c.opcode} -> webVector3Snapshot(receiver.webId(), {_slot3(c.opcode)})")
    lines += [
        "else -> null",
        "}",
        "?: error(",
        '"Missing Web ${descriptor.className}.${descriptor.methodName} snapshot for " +',
        '"object handle=${receiver.webId()}"',
        ")",
    ]
    if immediate:
        lines += [
            f"{_IMMEDIATE} -> {{",
            f"require({_opcode_guard(immediate)})",
            "commands.flush()",
            "GodotVector3(",
            "immediateWebNoArgsVector3X(descriptor.opcode, receiver.webId()).toFloat(),",
            "immediateWebNoArgsVector3Y().toFloat(),",
            "immediateWebNoArgsVector3Z().toFloat(),",
            ")",
            "}",
        ]
    lines += [
        f"{_QUEUED} ->",
        'error("Vector3 return cannot use queued execution for opcode=${descriptor.opcode}")',
        "}",
    ]
    return lines


def body_STRINGNAME_DOUBLE_ARG(calls):
    return [
        f"require(descriptor.executionMode == {_QUEUED})",
        f"require({_opcode_guard(calls)})",
        "require(doubleValue.isFinite())",
        "commands.appendStringNameDoubleMutation("
        "descriptor.opcode, receiver.webId(), value, doubleValue)",
    ]


def body_STRINGNAME_STRINGNAME_RET_DOUBLE_SINGLETON(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "commands.flush()",
        "// Two action names are packed into one query string (unit separator) and the axis is",
        "// returned scaled by 1000 through the shared object-query transport.",
        'return immediateWebObjectQuery(descriptor.opcode, requireActiveWebScriptHandle(), '
        'first + "\\u001f" + second) / 1000.0',
    ]


def body_VECTOR3_RET_HANDLE(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "commands.flush()",
        "// Motion packed as three floats; the bridge allocates the collision slot and the",
        "// applier registers the KinematicCollision3D under it (null collision returns 0).",
        "return registerReturnedBrowserObject(",
        "immediateWebMoveAndCollide(",
        "descriptor.opcode,",
        "receiver.webId(),",
        'listOf(value.x, value.y, value.z).joinToString("\u001f"),',
        ")",
        ")",
    ]


def body_NOARGS_RET_HANDLE_LIST(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "commands.flush()",
        "// The applier packs the SCRIPT handles of scripted overlapping bodies (unit",
        "// separator); bodies without Kanama scripts are omitted by contract.",
        'return immediateWebStringQuery(descriptor.opcode, receiver.webId(), "")',
        ".split('\u001f')",
        ".filter { it.isNotEmpty() }",
        ".map { GodotHandle.fromBackendToken(it.toLong()) }",
    ]


def body_LONG_RET_VECTOR3(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "require(value in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())",
        "commands.flush()",
        "return GodotVector3(",
        "immediateWebIndexedVector3X(descriptor.opcode, receiver.webId(), value.toInt()).toFloat(),",
        "immediateWebNoArgsVector3Y().toFloat(),",
        "immediateWebNoArgsVector3Z().toFloat(),",
        ")",
    ]


def body_STRINGNAME_RET_DOUBLE_SINGLETON(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "commands.flush()",
        "// Scaled by 1000 through the shared integer object-query transport.",
        "return immediateWebObjectQuery(descriptor.opcode, requireActiveWebScriptHandle(), value) /",
        "1000.0",
    ]


def body_STRINGNAME_STRING_RET_INT(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "commands.flush()",
        "// Signal name and String argument packed into one query (unit separator).",
        "return immediateWebObjectQuery(",
        "descriptor.opcode,",
        "receiver.webId(),",
        'name + "\u001f" + value,',
        ")",
    ]


def body_STRINGNAME_VECTOR3_VECTOR3_ARG(calls):
    return [
        f"require(descriptor.executionMode == {_QUEUED})",
        f"require({_opcode_guard(calls)})",
        "commands.appendStringNameVector3Vector3Mutation(",
        "descriptor.opcode,",
        "receiver.webId(),",
        "name,",
        "first,",
        "second,",
        ")",
    ]


def body_CALLABLE_DOUBLE_RANGE_RET_HANDLE(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "commands.flush()",
        "return registerReturnedBrowserObject(",
        "immediateWebTweenMethod(",
        "descriptor.opcode,",
        "receiver.webId(),",
        "target.webId(),",
        "method,",
        "fromValue,",
        "toValue,",
        "duration,",
        ")",
        ")",
    ]


def body_LONG_BOOL_ARG(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "commands.flush()",
        "// Layer number and flag packed into one query string (unit separator).",
        "check(",
        "immediateWebObjectQuery(",
        "descriptor.opcode,",
        "receiver.webId(),",
        'layer.toString() + "\u001f" + (if (value) "1" else "0"),',
        ") == 1",
        ") {",
        '"Kanama Web ${descriptor.className}.${descriptor.methodName} was not applied"',
        "}",
    ]


def body_VECTOR3_VECTOR3_ARG(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "commands.flush()",
        "// Six Float32 components are packed into one query string (unit separator); the applier",
        "// re-pushes the Node3D transform snapshot so rotation reads reflect the new orientation.",
        "val packed =",
        'listOf(first.x, first.y, first.z, second.x, second.y, second.z).joinToString("\\u001f")',
        "check(immediateWebObjectQuery(descriptor.opcode, receiver.webId(), packed) == 1) {",
        '"Kanama Web ${descriptor.className}.${descriptor.methodName} was not applied"',
        "}",
    ]


def body_LONG_ARG_SINGLETON(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "require(value in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())",
        "commands.flush()",
        "immediateWebObjectQuery(descriptor.opcode, requireActiveWebScriptHandle(), value.toString())",
    ]


def body_STRINGNAME_ARG_SINGLETON(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "commands.flush()",
        "immediateWebObjectQuery(descriptor.opcode, requireActiveWebScriptHandle(), value)",
    ]


def body_NOARGS_RET_STRING_SINGLETON(calls):
    op = _only(calls).opcode
    return [
        f"require(descriptor.executionMode == {_SNAPSHOT})",
        f"require(descriptor.opcode == {op})",
        "return webRenderingMethodSnapshot(requireActiveWebScriptHandle())",
        "?: error(",
        '"Missing Web ${descriptor.className}.${descriptor.methodName} snapshot for " +',
        '"active script handle"',
        ")",
    ]


def body_LONG_DOUBLE_ARG(calls):
    return [
        f"require(descriptor.executionMode == {_QUEUED})",
        f"require({_opcode_guard(calls)})",
        "require(longValue in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())",
        "require(doubleValue.isFinite()) {",
        '"Kanama Web ${descriptor.className}.${descriptor.methodName} requires a finite Double"',
        "}",
        "commands.appendLongDoubleMutation("
        "descriptor.opcode, receiver.webId(), longValue, doubleValue)",
    ]


def body_VECTOR3_ARG(calls):
    queued = [c for c in calls if c.execution_mode.value == "QUEUED_MUTATION"]
    immediate = [c for c in calls if c.execution_mode.value == "IMMEDIATE_RESULT"]
    lines = [
        "val objectId = receiver.webId()",
        "when (descriptor.executionMode) {",
        f"{_QUEUED} -> {{",
        "commands.appendVector3Mutation(descriptor.opcode, objectId, value.x, value.y, value.z)",
        "when (descriptor.opcode) {",
    ]
    for c in queued:
        # Opcodes without a mirrored slot are write-only on the Kotlin side: their reads (where
        # the corpus has any) go through a synchronous immediate rather than a snapshot.
        if WEB_POLICY[c.opcode].get("vec3_slot") is None:
            lines.append(f"{c.opcode} -> {{}}")
        else:
            lines.append(
                f"{c.opcode} -> webWriteVector3Snapshot(objectId, {_slot3(c.opcode)}, value)"
            )
    lines.append('else -> error("Unsupported Web Vector3 mutation opcode=${descriptor.opcode}")')
    lines.append("}")
    lines.append("}")
    if immediate:
        lines += [
            f"{_IMMEDIATE} -> {{",
            f"require({_opcode_guard(immediate)})",
            "commands.flush()",
            "// Three Float32 components packed into one query string (unit separator); the",
            "// applier writes the global transform and re-pushes the node's snapshot.",
            "val packed =",
            'listOf(value.x, value.y, value.z).joinToString("\u001f")',
            "check(immediateWebObjectQuery(descriptor.opcode, objectId, packed) == 1) {",
            '"Kanama Web ${descriptor.className}.${descriptor.methodName} was not applied"',
            "}",
            "}",
        ]
    lines += [
        f"{_SNAPSHOT} ->",
        'error("Vector3 argument cannot use snapshot execution for opcode=${descriptor.opcode}")',
        "}",
    ]
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
    lines += [f"{_IMMEDIATE} -> {{", "commands.flush()", "when (descriptor.opcode) {"]
    for c in immediate:
        if c.opcode == 37:
            lines += [
                "37 ->",
                "check(immediateWebTweenNoArgs(descriptor.opcode, objectId) == 1) {",
                '"Kanama Web Tween.kill failed for handle=$objectId"',
                "}",
            ]
        else:
            lines += [
                f"{c.opcode} ->",
                f'check(immediateWebObjectQuery({c.opcode}, objectId, "") == 1) {{',
                '"Kanama Web ${descriptor.className}.${descriptor.methodName} was not applied"',
                "}",
            ]
    lines += [
        'else -> error("Unsupported Web immediate void opcode=${descriptor.opcode}")',
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
        elif op == 130:
            lines += [
                "130 -> {",
                f"require(descriptor.executionMode == {_QUEUED})",
                "value?.let { requireWebBrowserHandle(it.webId(), WebBrowserHandleKind.RESOURCE) }",
                "}",
            ]
        elif op in (165, 170, 171):
            lines += [
                f"{op} -> {{",
                f"require(descriptor.executionMode == {_QUEUED})",
                "checkNotNull(value)",
                "}",
            ]
        elif op == 202:
            lines += [
                "202 -> {",
                f"require(descriptor.executionMode == {_QUEUED})",
                "// The MeshLibrary was constructed via ClassDB.instantiate, so it is",
                "// registered under the NODE kind like every constructed handle.",
                "value?.let { requireWebBrowserHandle(it.webId(), WebBrowserHandleKind.NODE) }",
                "}",
            ]
        elif op == 247:
            lines += [
                "247 -> {",
                f"require(descriptor.executionMode == {_QUEUED})",
                "// Duplicated materials return through the browser-object channel.",
                "value?.let { requireWebBrowserHandle(it.webId(), WebBrowserHandleKind.OBJECT) }",
                "}",
            ]
        elif op == 257:
            lines += [
                "257 -> {",
                f"require(descriptor.executionMode == {_QUEUED})",
                "// The ButtonGroup was constructed via ClassDB.instantiate (NODE kind).",
                "value?.let { requireWebBrowserHandle(it.webId(), WebBrowserHandleKind.NODE) }",
                "}",
            ]
        elif op == 284:
            lines += [
                "284 -> {",
                f"require(descriptor.executionMode == {_QUEUED})",
                "// Baked lightmap data arrives through ResourceLoader.load (RESOURCE kind).",
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
        "return when (descriptor.opcode) {",
        "17 -> registerReturnedNode(immediateWebNodeLookup(receiver.webId(), path))",
        "149,",
        "184 ->",
        "registerReturnedBrowserObject(",
        "immediateWebPropertyObjectQuery(descriptor.opcode, receiver.webId(), path)",
        ")",
        'else -> error("Unsupported Web path-to-handle opcode=${descriptor.opcode}")',
        "}",
    ]


def body_LONG_ARG(calls):
    return [
        f"require(descriptor.executionMode == {_QUEUED})",
        f"require({_opcode_guard(calls)})",
        "require(value in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong()) {",
        '"Kanama Web ${descriptor.className}.${descriptor.methodName} argument must fit '
        "Godot's int32 ABI\"",
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
    collision_ops = [c.opcode for c in calls if WEB_POLICY[c.opcode].get("ret") == "collision"]
    indexed_node_ops = [
        c.opcode for c in calls if WEB_POLICY[c.opcode].get("ret") == "indexed_node"
    ]
    indexed_browser_ops = [
        c.opcode for c in calls if WEB_POLICY[c.opcode].get("ret") == "indexed_browser"
    ]
    for op in indexed_browser_ops:
        # Resource-valued indexed reads ride the property-object channel (which allocates an
        # OBJECT-kind slot), not the node-shaped indexed lookup: a Material is never a Node.
        lines.append(f"{op} ->")
        lines.append(
            "registerReturnedBrowserObject("
            "immediateWebPropertyObjectQuery(descriptor.opcode, receiver.webId(), value.toString())"
            ")"
        )
    for op in indexed_node_ops:
        lines.append(f"{op} ->")
        lines.append(
            "registerReturnedNode("
            "immediateWebIndexedObjectLookup(descriptor.opcode, receiver.webId(), value.toInt())"
            ")"
        )
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
    for op in collision_ops:
        lines.append(f"{op} ->")
        lines.append(
            "registerReturnedBrowserObject(immediateWebSlideCollision(receiver.webId(), value.toInt()))"
        )
    child_ops = [c.opcode for c in calls if WEB_POLICY[c.opcode].get("ret") == "child"]
    for op in child_ops:
        lines.append(f"{op} ->")
        lines.append("registerReturnedNode(immediateWebNodeChild(receiver.webId(), value.toInt()))")
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
        "return when (descriptor.opcode) {",
        "34 ->",
        "immediateWebConnectBound(",
        "receiver.webId(),",
        "signal,",
        "target.webId(),",
        "method,",
        "boundValue.toInt(),",
        "flags.toInt(),",
        ")",
        ".toLong()",
        "193 ->",
        "immediateWebDisconnectBound(",
        "receiver.webId(),",
        "signal,",
        "target.webId(),",
        "method,",
        "boundValue.toInt(),",
        ")",
        ".toLong()",
        'else -> error("Unsupported Web bound-callable opcode=${descriptor.opcode}")',
        "}",
    ]


def body_OBJECT_RET_HANDLE(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "commands.flush()",
        "return existingReturnedObject(",
        "receiver,",
        "immediateWebTweenObjectRetObject(descriptor.opcode, receiver.webId(), value.webId()),",
        ")",
    ]


def body_COLOR_RET_HANDLE(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "commands.flush()",
        "return registerReturnedBrowserObject(",
        "immediateWebColorRetHandle(",
        "descriptor.opcode,",
        "receiver.webId(),",
        "value.r.toDouble(),",
        "value.g.toDouble(),",
        "value.b.toDouble(),",
        "value.a.toDouble(),",
        ")",
        ")",
    ]


def body_OBJECT_NODEPATH_DOUBLE_DOUBLE_RET_HANDLE(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "require(duration.isFinite() && duration >= 0.0)",
        "require(finalValue.isFinite())",
        "commands.flush()",
        "return registerReturnedBrowserObject(",
        "immediateWebTweenPropertyDouble(",
        "descriptor.opcode,",
        "receiver.webId(),",
        "target.webId(),",
        "property,",
        "finalValue,",
        "duration,",
        ")",
        ")",
    ]


def body_OBJECT_NODEPATH_VECTOR3_DOUBLE_RET_HANDLE(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "require(duration.isFinite() && duration >= 0.0)",
        "commands.flush()",
        "return registerReturnedBrowserObject(",
        "immediateWebTweenPropertyVector3(",
        "descriptor.opcode,",
        "receiver.webId(),",
        "target.webId(),",
        "property,",
        "finalValue.x.toDouble(),",
        "finalValue.y.toDouble(),",
        "finalValue.z.toDouble(),",
        "duration,",
        ")",
        ")",
    ]


def body_CALLABLE_RET_HANDLE(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "commands.flush()",
        "return registerReturnedBrowserObject(",
        "immediateWebTweenCallback(descriptor.opcode, receiver.webId(), target.webId(), method)",
        ")",
    ]


def body_NOARGS_RET_LONG_SINGLETON(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "commands.flush()",
        'return immediateWebObjectQuery(descriptor.opcode, requireActiveWebScriptHandle(), "")',
        ".toLong()",
    ]


def body_STRINGNAME_OBJECT_RET_INT(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "commands.flush()",
        "// Signal name and object handle packed into one query string (unit separator);",
        "// the applier resolves the object from the shared handle dictionary and emits.",
        "return immediateWebObjectQuery(",
        "descriptor.opcode,",
        "receiver.webId(),",
        'name + "\u001f" + value.webId().toString(),',
        ")",
    ]


def body_STRINGNAME_RET_BOOL(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "commands.flush()",
        "return immediateWebObjectQuery(descriptor.opcode, receiver.webId(), value) != 0",
    ]


def body_STRINGNAME_RET_BOOL_SINGLETON(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "commands.flush()",
        "return immediateWebObjectQuery("
        "descriptor.opcode, requireActiveWebScriptHandle(), value) != 0",
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


_DOUBLE_SNAPSHOT_HOOK = {"lifetime": "webLifetimeSnapshot", "rotation": "webRotationSnapshot"}


def body_NOARGS_RET_DOUBLE(calls):
    snapshot = [c for c in calls if c.execution_mode.value == "SNAPSHOT_READ"]
    immediate = [c for c in calls if c.execution_mode.value == "IMMEDIATE_RESULT"]
    lines = ["return when (descriptor.executionMode) {"]
    lines.append(f"{_SNAPSHOT} ->")
    lines.append("when (descriptor.opcode) {")
    for c in snapshot:
        hook = _DOUBLE_SNAPSHOT_HOOK[WEB_POLICY[c.opcode]["double_snapshot"]]
        lines.append(f"{c.opcode} -> {hook}(receiver.webId())")
    lines += [
        "else -> null",
        "}",
        "?: error(",
        '"Missing Web ${descriptor.className}.${descriptor.methodName} snapshot for " +',
        '"object handle=${receiver.webId()}"',
        ")",
    ]
    if immediate:
        lines += [
            f"{_IMMEDIATE} -> {{",
            f"require({_opcode_guard(immediate)})",
            "commands.flush()",
            "// Scaled by 1000 through the shared integer object-query transport.",
            'immediateWebObjectQuery(descriptor.opcode, receiver.webId(), "") / 1000.0',
            "}",
        ]
    lines += [
        f"{_QUEUED} ->",
        'error("Double return cannot use queued execution for opcode=${descriptor.opcode}")',
        "}",
    ]
    return lines


def body_NOARGS_RET_LONG(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        "commands.flush()",
        'return immediateWebObjectQuery(descriptor.opcode, receiver.webId(), "").toLong()',
    ]


def body_NOARGS_RET_STRING_ARRAY(calls):
    op = _only(calls).opcode
    return [
        f"require(descriptor.executionMode == {_SNAPSHOT})",
        f"require(descriptor.opcode == {op})",
        "return webAnimationNamesSnapshot(receiver.webId())",
        "?: error(",
        '"Missing Web ${descriptor.className}.${descriptor.methodName} snapshot for " +',
        '"object handle=${receiver.webId()}"',
        ")",
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


def body_STRINGNAME_STRINGNAME_ARG(calls):
    return [
        f"require(descriptor.executionMode == {_QUEUED})",
        f"require({_opcode_guard(calls)})",
        "commands.appendStringNameStringNameMutation(descriptor.opcode, receiver.webId(), first, second)",
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


def body_VECTOR3I_LONG_LONG_ARG(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "require(first in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())",
        "require(second in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())",
        "commands.flush()",
        "// Cell position plus item/orientation packed as five integers (unit separator).",
        "val packed =",
        'listOf(value.x, value.y, value.z, first.toInt(), second.toInt()).joinToString("\\u001f")',
        "check(immediateWebObjectQuery(descriptor.opcode, receiver.webId(), packed) == 1) {",
        '"Kanama Web ${descriptor.className}.${descriptor.methodName} was not applied"',
        "}",
    ]


def body_VECTOR3I_RET_LONG(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "commands.flush()",
        "// Cell position packed as three integers; the result (INVALID_CELL_ITEM = -1",
        "// included) rides the shared integer object-query transport.",
        "return immediateWebObjectQuery(",
        "descriptor.opcode,",
        "receiver.webId(),",
        'listOf(value.x, value.y, value.z).joinToString(""),',
        ")",
        ".toLong()",
    ]


def body_BASIS_RET_LONG(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "commands.flush()",
        "// Nine Float32 components packed column-major (x, y, z axes; unit separator).",
        "val packed =",
        "listOf(",
        "value.x.x,",
        "value.x.y,",
        "value.x.z,",
        "value.y.x,",
        "value.y.y,",
        "value.y.z,",
        "value.z.x,",
        "value.z.y,",
        "value.z.z,",
        ")",
        '.joinToString("")',
        "return immediateWebObjectQuery(descriptor.opcode, receiver.webId(), packed).toLong()",
    ]


def body_NOARGS_RET_VECTOR3I_LIST(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "commands.flush()",
        "// The applier packs one comma-joined integer triple per cell (unit separator).",
        'return immediateWebStringQuery(descriptor.opcode, receiver.webId(), "")',
        ".split('')",
        ".filter { it.isNotEmpty() }",
        ".map { triple ->",
        "val parts = triple.split(',')",
        "GodotVector3i(parts[0].toInt(), parts[1].toInt(), parts[2].toInt())",
        "}",
    ]


def body_LONG_OBJECT_ARG(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "require(longValue in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())",
        "requireWebBrowserHandle(objectValue.webId(), WebBrowserHandleKind.OBJECT)",
        "commands.flush()",
        "// Item index and object handle packed into one query string (unit separator).",
        "check(",
        "immediateWebObjectQuery(",
        "descriptor.opcode,",
        "receiver.webId(),",
        'longValue.toString() + "" + objectValue.webId().toString(),',
        ") == 1",
        ") {",
        '"Kanama Web ${descriptor.className}.${descriptor.methodName} was not applied"',
        "}",
    ]


def body_LONG_TRANSFORM3D_ARG(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "require(longValue in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())",
        "commands.flush()",
        "// Item index plus twelve Float32 transform components (basis columns then origin).",
        "val packed =",
        "listOf(",
        "longValue.toInt().toFloat(),",
        "value.basis.x.x,",
        "value.basis.x.y,",
        "value.basis.x.z,",
        "value.basis.y.x,",
        "value.basis.y.y,",
        "value.basis.y.z,",
        "value.basis.z.x,",
        "value.basis.z.y,",
        "value.basis.z.z,",
        "value.origin.x,",
        "value.origin.y,",
        "value.origin.z,",
        ")",
        '.joinToString("")',
        "check(immediateWebObjectQuery(descriptor.opcode, receiver.webId(), packed) == 1) {",
        '"Kanama Web ${descriptor.className}.${descriptor.methodName} was not applied"',
        "}",
    ]


def body_LONG_RET_STRING(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "require(value in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())",
        "commands.flush()",
        "return immediateWebStringQuery(descriptor.opcode, receiver.webId(), value.toString())",
    ]


def body_LONG_RET_LONG(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "require(value in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())",
        "commands.flush()",
        "return immediateWebObjectQuery(descriptor.opcode, receiver.webId(), value.toString())",
        ".toLong()",
    ]


def body_LONG_LONG_RET_STRING(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "require(first in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())",
        "require(second in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())",
        "commands.flush()",
        "// Both indices packed into one query string (unit separator).",
        "return immediateWebStringQuery(",
        "descriptor.opcode,",
        "receiver.webId(),",
        'first.toString() + "" + second.toString(),',
        ")",
    ]


def body_LONG_LONG_RET_HANDLE(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "require(first in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())",
        "require(second in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())",
        "commands.flush()",
        "// Both indices ride the property-object-query string; the bridge appends the",
        "// proposed handle and non-object values resolve to a null handle.",
        "return registerReturnedBrowserObject(",
        "immediateWebPropertyObjectQuery(",
        "descriptor.opcode,",
        "receiver.webId(),",
        'first.toString() + "" + second.toString(),',
        ")",
        ")",
    ]


def body_VECTOR2_RET_VECTOR3(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "commands.flush()",
        "return GodotVector3(",
        "immediateWebVector2ArgVector3X(",
        "descriptor.opcode,",
        "receiver.webId(),",
        "value.x.toDouble(),",
        "value.y.toDouble(),",
        ")",
        ".toFloat(),",
        "immediateWebNoArgsVector3Y().toFloat(),",
        "immediateWebNoArgsVector3Z().toFloat(),",
        ")",
    ]


def body_OBJECT_STRING_RET_LONG_SINGLETON(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "require(flags in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())",
        "commands.flush()",
        "// Resource handle, destination path, and flags packed into one query string; the",
        "// applier pulls current Kotlin property values into scripted resources first.",
        "return immediateWebObjectQuery(",
        "descriptor.opcode,",
        "requireActiveWebScriptHandle(),",
        'resource.webId().toString() + "" + path + "" + flags.toString(),',
        ")",
        ".toLong()",
    ]


def body_STRING_STRING_BOOL_BOOL_RET_HANDLE_LIST(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "commands.flush()",
        "// Pattern, type, and both flags packed into one query string; the applier packs the",
        "// matches back as handles (scripted matches resolve to script handles, engine nodes",
        "// get tracked browser handles).",
        "val packed =",
        'listOf(pattern, type, if (recursive) "1" else "0", if (owned) "1" else "0")',
        '.joinToString("")',
        "return immediateWebStringQuery(descriptor.opcode, receiver.webId(), packed)",
        ".split('')",
        ".filter { it.isNotEmpty() }",
        ".map { GodotHandle.fromBackendToken(it.toLong()) }",
    ]


def body_STRINGNAME_LONG_ARG(calls):
    return [
        f"require(descriptor.executionMode == {_QUEUED})",
        f"require({_opcode_guard(calls)})",
        "require(value in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong()) {",
        '"Kanama Web ${descriptor.className}.${descriptor.methodName} argument must fit '
        "Godot's int32 ABI\"",
        "}",
        "commands.appendStringNameLongMutation(descriptor.opcode, receiver.webId(), name, value)",
    ]


def body_STRINGNAME_VECTOR2_ARG(calls):
    return [
        f"require(descriptor.executionMode == {_QUEUED})",
        f"require({_opcode_guard(calls)})",
        "commands.appendStringNameVector2Mutation(",
        "descriptor.opcode,",
        "receiver.webId(),",
        "name,",
        "value.x,",
        "value.y,",
        ")",
    ]


def body_STRINGNAME_OBJECT_ARG(calls):
    return [
        f"require(descriptor.executionMode == {_QUEUED})",
        f"require({_opcode_guard(calls)})",
        "commands.appendStringNameObjectMutation(",
        "descriptor.opcode,",
        "receiver.webId(),",
        "name,",
        "value.webId(),",
        ")",
    ]


def body_STRINGNAME_RET_VECTOR2(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "commands.flush()",
        "// Both components ride the shared string channel (unit separator); non-Vector2",
        "// properties resolve to the zero vector applier-side.",
        "val packed = immediateWebStringQuery(descriptor.opcode, receiver.webId(), name)",
        "val parts = packed.split('\u001f')",
        "require(parts.size == 2) {",
        '"Kanama Web ${descriptor.className}.${descriptor.methodName} returned $packed"',
        "}",
        "return GodotVector2(parts[0].toFloat(), parts[1].toFloat())",
    ]


def body_NOARGS_RET_STRING(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "commands.flush()",
        'return immediateWebStringQuery(descriptor.opcode, receiver.webId(), "")',
    ]


def body_STRINGNAME_RET_STRING(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "commands.flush()",
        "return immediateWebStringQuery(descriptor.opcode, receiver.webId(), name)",
    ]


def body_DOUBLE_RET_DOUBLE(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "require(value.isFinite()) {",
        '"Kanama Web ${descriptor.className}.${descriptor.methodName} requires a finite Double"',
        "}",
        "commands.flush()",
        "// Scaled by 1000 through the shared integer double-query transport.",
        "return immediateWebDoubleQuery(descriptor.opcode, receiver.webId(), value) / 1000.0",
    ]


def body_VECTOR3_VECTOR3_LONG_OBJECT_RET_STRING(calls):
    return [
        f"require(descriptor.executionMode == {_IMMEDIATE})",
        f"require({_opcode_guard(calls)})",
        "require(collisionMask in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())",
        "commands.flush()",
        "// Ray endpoints, mask, and the optional exclusion handle ride one query string",
        "// (unit separator). The applier resolves the receiver's world, builds the query",
        "// parameters, and turns the exclusion handle into an RID on its own side.",
        "val packed =",
        "listOf(",
        "from.x.toString(),",
        "from.y.toString(),",
        "from.z.toString(),",
        "to.x.toString(),",
        "to.y.toString(),",
        "to.z.toString(),",
        "collisionMask.toString(),",
        "(exclude?.webId() ?: 0).toString(),",
        ")",
        '.joinToString("\u001f")',
        "return immediateWebStringQuery(descriptor.opcode, receiver.webId(), packed)",
    ]


# Signature: (params-after-descriptor/callSite, return-type). `receiver` present unless noted.
SIGNATURES: dict[str, tuple[list[str], str]] = {
    "BOOL_RET_INT": (["receiver: GodotHandle", "value: Boolean"], "Int"),
    "BOOL_RET_HANDLE": (["receiver: GodotHandle", "value: Boolean"], "GodotHandle?"),
    "BOOL_ARG": (["receiver: GodotHandle", "value: Boolean"], ""),
    "DOUBLE_ARG": (["receiver: GodotHandle", "value: Double"], ""),
    "NOARGS_RET_VECTOR2": (["receiver: GodotHandle"], "GodotVector2"),
    "VECTOR2_ARG": (["receiver: GodotHandle", "value: GodotVector2"], ""),
    "NOARGS_RET_VECTOR3": (["receiver: GodotHandle"], "GodotVector3"),
    "VECTOR3_ARG": (["receiver: GodotHandle", "value: GodotVector3"], ""),
    "LONG_DOUBLE_ARG": (
        ["receiver: GodotHandle", "longValue: Long", "doubleValue: Double"],
        "",
    ),
    "NOARGS_RET_STRING_SINGLETON": ([], "String"),
    "STRINGNAME_ARG_SINGLETON": (["value: String"], ""),
    "STRINGNAME_DOUBLE_ARG": (
        ["receiver: GodotHandle", "value: String", "doubleValue: Double"],
        "",
    ),
    "STRINGNAME_STRINGNAME_RET_DOUBLE_SINGLETON": (["first: String", "second: String"], "Double"),
    "VECTOR3_VECTOR3_ARG": (
        ["receiver: GodotHandle", "first: GodotVector3", "second: GodotVector3"],
        "",
    ),
    "LONG_ARG_SINGLETON": (["value: Long"], ""),
    "OBJECT_RET_HANDLE": (["receiver: GodotHandle", "value: GodotHandle"], "GodotHandle?"),
    "OBJECT_NODEPATH_VECTOR3_DOUBLE_RET_HANDLE": (
        [
            "receiver: GodotHandle",
            "target: GodotHandle",
            "property: String",
            "finalValue: GodotVector3",
            "duration: Double",
        ],
        "GodotHandle?",
    ),
    "OBJECT_NODEPATH_DOUBLE_DOUBLE_RET_HANDLE": (
        [
            "receiver: GodotHandle",
            "target: GodotHandle",
            "property: String",
            "finalValue: Double",
            "duration: Double",
        ],
        "GodotHandle?",
    ),
    "COLOR_RET_HANDLE": (
        ["receiver: GodotHandle", "value: GodotColor"],
        "GodotHandle?",
    ),
    "CALLABLE_RET_HANDLE": (
        ["receiver: GodotHandle", "target: GodotHandle", "method: String"],
        "GodotHandle?",
    ),
    "NOARGS_RET_LONG_SINGLETON": ([], "Long"),
    "STRINGNAME_OBJECT_RET_INT": (
        ["receiver: GodotHandle", "name: String", "value: GodotHandle"],
        "Int",
    ),
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
    "STRINGNAME_RET_BOOL_SINGLETON": (["value: String"], "Boolean"),
    "NOARGS_RET_BOOL": (["receiver: GodotHandle"], "Boolean"),
    "NOARGS_RET_DOUBLE": (["receiver: GodotHandle"], "Double"),
    "NOARGS_RET_LONG": (["receiver: GodotHandle"], "Long"),
    "NOARGS_RET_STRING_ARRAY": (["receiver: GodotHandle"], "List<String>"),
    "STRINGNAME_ARG": (["receiver: GodotHandle", "value: String"], ""),
    "STRINGNAME_BOOL_ARG": (
        ["receiver: GodotHandle", "name: String", "value: Boolean"],
        "",
    ),
    "LONG_BOOL_ARG": (
        ["receiver: GodotHandle", "layer: Long", "value: Boolean"],
        "",
    ),
    "VECTOR3_RET_HANDLE": (
        ["receiver: GodotHandle", "value: GodotVector3"],
        "GodotHandle?",
    ),
    "NOARGS_RET_HANDLE_LIST": (["receiver: GodotHandle"], "List<GodotHandle>"),
    "LONG_RET_VECTOR3": (
        ["receiver: GodotHandle", "value: Long"],
        "GodotVector3",
    ),
    "STRINGNAME_RET_DOUBLE_SINGLETON": (["value: String"], "Double"),
    "STRINGNAME_STRING_RET_INT": (
        ["receiver: GodotHandle", "name: String", "value: String"],
        "Int",
    ),
    "STRINGNAME_VECTOR3_VECTOR3_ARG": (
        [
            "receiver: GodotHandle",
            "name: String",
            "first: GodotVector3",
            "second: GodotVector3",
        ],
        "",
    ),
    "CALLABLE_DOUBLE_RANGE_RET_HANDLE": (
        [
            "receiver: GodotHandle",
            "target: GodotHandle",
            "method: String",
            "fromValue: Double",
            "toValue: Double",
            "duration: Double",
        ],
        "GodotHandle?",
    ),
    "STRINGNAME_STRINGNAME_ARG": (
        ["receiver: GodotHandle", "first: String", "second: String"],
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
    "VECTOR3I_LONG_LONG_ARG": (
        ["receiver: GodotHandle", "value: GodotVector3i", "first: Long", "second: Long"],
        "",
    ),
    "VECTOR3I_RET_LONG": (["receiver: GodotHandle", "value: GodotVector3i"], "Long"),
    "BASIS_RET_LONG": (["receiver: GodotHandle", "value: GodotBasis"], "Long"),
    "NOARGS_RET_VECTOR3I_LIST": (["receiver: GodotHandle"], "List<GodotVector3i>"),
    "LONG_OBJECT_ARG": (
        ["receiver: GodotHandle", "longValue: Long", "objectValue: GodotHandle"],
        "",
    ),
    "LONG_TRANSFORM3D_ARG": (
        ["receiver: GodotHandle", "longValue: Long", "value: GodotTransform3D"],
        "",
    ),
    "LONG_RET_STRING": (["receiver: GodotHandle", "value: Long"], "String"),
    "LONG_RET_LONG": (["receiver: GodotHandle", "value: Long"], "Long"),
    "LONG_LONG_RET_STRING": (
        ["receiver: GodotHandle", "first: Long", "second: Long"],
        "String",
    ),
    "LONG_LONG_RET_HANDLE": (
        ["receiver: GodotHandle", "first: Long", "second: Long"],
        "GodotHandle?",
    ),
    "VECTOR2_RET_VECTOR3": (["receiver: GodotHandle", "value: GodotVector2"], "GodotVector3"),
    "OBJECT_STRING_RET_LONG_SINGLETON": (
        ["resource: GodotHandle", "path: String", "flags: Long"],
        "Long",
    ),
    "STRINGNAME_LONG_ARG": (["receiver: GodotHandle", "name: String", "value: Long"], ""),
    "STRINGNAME_VECTOR2_ARG": (
        ["receiver: GodotHandle", "name: String", "value: GodotVector2"],
        "",
    ),
    "STRINGNAME_OBJECT_ARG": (
        ["receiver: GodotHandle", "name: String", "value: GodotHandle"],
        "",
    ),
    "STRINGNAME_RET_VECTOR2": (["receiver: GodotHandle", "name: String"], "GodotVector2"),
    "NOARGS_RET_STRING": (["receiver: GodotHandle"], "String"),
    "STRINGNAME_RET_STRING": (["receiver: GodotHandle", "name: String"], "String"),
    "DOUBLE_RET_DOUBLE": (["receiver: GodotHandle", "value: Double"], "Double"),
    "VECTOR3_VECTOR3_LONG_OBJECT_RET_STRING": (
        [
            "receiver: GodotHandle",
            "from: GodotVector3",
            "to: GodotVector3",
            "collisionMask: Long",
            "exclude: GodotHandle?",
        ],
        "String",
    ),
    "STRING_STRING_BOOL_BOOL_RET_HANDLE_LIST": (
        [
            "receiver: GodotHandle",
            "pattern: String",
            "type: String",
            "recursive: Boolean",
            "owned: Boolean",
        ],
        "List<GodotHandle>",
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
    "VECTOR3": "Vector3",
    "VECTOR3I": "Vector3i",
    "BASIS": "Basis",
    "TRANSFORM3D": "Transform3d",
    "STRING": "String",
    "STRINGNAME": "StringName",
    "NODEPATH": "NodePath",
    "TEXTURE2D": "Texture2D",
    "OBJECT": "Object",
    "UTILITY": "Utility",
    "CALLABLE": "Callable",
    "BOUND": "Bound",
    "SINGLETON": "Singleton",
    "ARRAY": "Array",
    "LIST": "List",
    "RANGE": "Range",
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
    "LONG_BOOL_ARG",
    "VECTOR3_RET_HANDLE",
    "NOARGS_RET_HANDLE_LIST",
    "LONG_RET_VECTOR3",
    "STRINGNAME_RET_DOUBLE_SINGLETON",
    "STRINGNAME_VECTOR3_VECTOR3_ARG",
    "STRINGNAME_STRING_RET_INT",
    "CALLABLE_DOUBLE_RANGE_RET_HANDLE",
    "STRINGNAME_STRINGNAME_ARG",
    "NODEPATH_RET_HANDLE",
    "LONG_RET_HANDLE",
    "NOARGS_RET_HANDLE",
    "OBJECT_LONG_VECTOR2_ARGS",
    "STRINGNAME_CALLABLE_LONG_RET_LONG",
    "STRINGNAME_BOUND_CALLABLE_LONG_RET_LONG",
    "STRINGNAME_RET_BOOL",
    "STRINGNAME_RET_BOOL_SINGLETON",
    "NOARGS_RET_BOOL",
    "NOARGS_RET_DOUBLE",
    "NOARGS_RET_LONG",
    "NOARGS_RET_STRING_ARRAY",
    "STRINGNAME_VECTOR2I_RET_INT",
    "NOARGS_RET_COLOR",
    "COLOR_ARG",
    "OBJECT_NODEPATH_VECTOR2_DOUBLE_RET_HANDLE",
    "OBJECT_NODEPATH_COLOR_DOUBLE_RET_HANDLE",
    "NOARGS_RET_VECTOR3",
    "VECTOR3_ARG",
    "LONG_DOUBLE_ARG",
    "NOARGS_RET_STRING_SINGLETON",
    "STRINGNAME_ARG_SINGLETON",
    "STRINGNAME_DOUBLE_ARG",
    "STRINGNAME_STRINGNAME_RET_DOUBLE_SINGLETON",
    "VECTOR3_VECTOR3_ARG",
    "LONG_ARG_SINGLETON",
    "OBJECT_RET_HANDLE",
    "OBJECT_NODEPATH_VECTOR3_DOUBLE_RET_HANDLE",
    "OBJECT_NODEPATH_DOUBLE_DOUBLE_RET_HANDLE",
    "COLOR_RET_HANDLE",
    "CALLABLE_RET_HANDLE",
    "NOARGS_RET_LONG_SINGLETON",
    "STRINGNAME_OBJECT_RET_INT",
    "VECTOR3I_LONG_LONG_ARG",
    "VECTOR3I_RET_LONG",
    "BASIS_RET_LONG",
    "NOARGS_RET_VECTOR3I_LIST",
    "LONG_OBJECT_ARG",
    "LONG_TRANSFORM3D_ARG",
    "LONG_RET_STRING",
    "LONG_RET_LONG",
    "LONG_LONG_RET_STRING",
    "LONG_LONG_RET_HANDLE",
    "VECTOR2_RET_VECTOR3",
    "OBJECT_STRING_RET_LONG_SINGLETON",
    "STRING_STRING_BOOL_BOOL_RET_HANDLE_LIST",
    "STRINGNAME_LONG_ARG",
    "STRINGNAME_VECTOR2_ARG",
    "STRINGNAME_OBJECT_ARG",
    "STRINGNAME_RET_VECTOR2",
    "NOARGS_RET_STRING",
    "STRINGNAME_RET_STRING",
    "DOUBLE_RET_DOUBLE",
    "VECTOR3_VECTOR3_LONG_OBJECT_RET_STRING",
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
import net.multigesture.kanama.backend.GodotBasis
import net.multigesture.kanama.backend.GodotCallDescriptor
import net.multigesture.kanama.backend.GodotCallSite
import net.multigesture.kanama.backend.GodotColor
import net.multigesture.kanama.backend.GodotExecutionMode
import net.multigesture.kanama.backend.GodotHandle
import net.multigesture.kanama.backend.GodotRect2
import net.multigesture.kanama.backend.GodotTransform3D
import net.multigesture.kanama.backend.GodotVector2
import net.multigesture.kanama.backend.GodotVector2i
import net.multigesture.kanama.backend.GodotVector3
import net.multigesture.kanama.backend.GodotVector3i
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
