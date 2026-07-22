"""Generator-owned execution policy for the platform-neutral backend seam."""

from __future__ import annotations

from dataclasses import dataclass
from enum import Enum
import json
from pathlib import Path


class ExecutionMode(str, Enum):
    IMMEDIATE_RESULT = "IMMEDIATE_RESULT"
    SNAPSHOT_READ = "SNAPSHOT_READ"
    QUEUED_MUTATION = "QUEUED_MUTATION"


@dataclass(frozen=True)
class BackendCallPolicy:
    opcode: int
    descriptor_name: str
    scope: str
    class_name: str
    method_name: str
    expected_hash: int
    arguments: tuple[str, ...]
    return_type: str
    shape: str
    execution_mode: ExecutionMode
    return_ownership: str = "BORROWED"
    is_vararg: bool = False
    typed_vararg_tail: tuple[str, ...] = ()
    introduced_by: str = ""


MANIFEST_PATH = Path(__file__).with_name("platform_backend_calls.json")


def _load_backend_calls(path: Path = MANIFEST_PATH) -> tuple[BackendCallPolicy, ...]:
    payload = json.loads(path.read_text())
    if payload.get("protocolVersion") != 1:
        raise ValueError(f"Unsupported platform backend protocol: {payload.get('protocolVersion')}")

    calls = tuple(
        BackendCallPolicy(
            opcode=entry["opcode"],
            descriptor_name=entry.get("descriptorName", ""),
            scope=entry["scope"],
            class_name=entry["className"],
            method_name=entry["methodName"],
            expected_hash=entry["hash"],
            arguments=tuple(entry.get("arguments", ())),
            return_type=entry.get("returnType", "void"),
            shape=entry["shape"],
            execution_mode=ExecutionMode(entry["executionMode"]),
            return_ownership=entry.get("returnOwnership", "BORROWED"),
            is_vararg=entry.get("isVararg", False),
            typed_vararg_tail=tuple(entry.get("typedVarargTail", ())),
            introduced_by=entry.get("introducedBy", ""),
        )
        for entry in payload["calls"]
    )
    opcodes = tuple(call.opcode for call in calls)
    if opcodes != tuple(range(1, len(calls) + 1)):
        raise ValueError(f"Platform backend opcodes must be append-only and contiguous: {opcodes}")
    identities = tuple(
        (
            call.scope,
            call.class_name,
            call.method_name,
            call.expected_hash,
            call.shape,
            call.typed_vararg_tail,
        )
        for call in calls
    )
    if len(identities) != len(set(identities)):
        raise ValueError("Platform backend typed call identities must be unique")
    base_identities: dict[tuple[str, str, str, int], set[tuple[ExecutionMode, str]]] = {}
    for call in calls:
        base_identity = (call.scope, call.class_name, call.method_name, call.expected_hash)
        base_identities.setdefault(base_identity, set()).add(
            (call.execution_mode, call.return_ownership)
        )
    if any(len(policies) != 1 for policies in base_identities.values()):
        raise ValueError("Typed variants of one Godot call must share execution and ownership policy")
    return calls


# Opcodes are append-only protocol IDs. Never derive them from source or alphabetical order.
BACKEND_CALLS = _load_backend_calls()

# Compatibility name for the Phase 0.5 generator/tests while production naming migrates.
INITIAL_BACKEND_CALLS = BACKEND_CALLS


def execution_mode_for(class_name: str, method_name: str, method_hash: int) -> ExecutionMode:
    """Return audited policy; unaudited calls stay explicit and synchronous."""
    for call in INITIAL_BACKEND_CALLS:
        if (call.class_name, call.method_name, call.expected_hash) == (
            class_name,
            method_name,
            method_hash,
        ):
            return call.execution_mode
    return ExecutionMode.IMMEDIATE_RESULT
