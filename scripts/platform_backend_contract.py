"""Generator-owned execution policy for the initial platform-backend seam."""

from __future__ import annotations

from dataclasses import dataclass
from enum import Enum


class ExecutionMode(str, Enum):
    IMMEDIATE_RESULT = "IMMEDIATE_RESULT"
    SNAPSHOT_READ = "SNAPSHOT_READ"
    QUEUED_MUTATION = "QUEUED_MUTATION"


@dataclass(frozen=True)
class BackendCallPolicy:
    opcode: int
    class_name: str
    method_name: str
    expected_hash: int
    arguments: tuple[str, ...]
    return_type: str
    shape: str
    execution_mode: ExecutionMode
    return_ownership: str = "BORROWED"


# Opcodes are append-only protocol IDs. Never derive them from source or alphabetical order.
INITIAL_BACKEND_CALLS = (
    BackendCallPolicy(
        opcode=1,
        class_name="Node",
        method_name="get_child_count",
        expected_hash=894402480,
        arguments=("bool",),
        return_type="int",
        shape="BOOL_RET_INT",
        execution_mode=ExecutionMode.IMMEDIATE_RESULT,
    ),
    BackendCallPolicy(
        opcode=2,
        class_name="Node2D",
        method_name="get_position",
        expected_hash=3341600327,
        arguments=(),
        return_type="Vector2",
        shape="NOARGS_RET_VECTOR2",
        execution_mode=ExecutionMode.SNAPSHOT_READ,
    ),
    BackendCallPolicy(
        opcode=3,
        class_name="Node2D",
        method_name="set_position",
        expected_hash=743155724,
        arguments=("Vector2",),
        return_type="void",
        shape="VECTOR2_ARG",
        execution_mode=ExecutionMode.QUEUED_MUTATION,
    ),
)


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
