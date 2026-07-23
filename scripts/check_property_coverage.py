#!/usr/bin/env python3
"""Property-coverage gate: fail on any *silently dropped* generated wrapper property.

Every property in extension_api.json that lives on a generated wrapper class must resolve
to a Kotlin `var`/`val` member — on the class itself or an ancestor wrapper. A property that
does not is either:

  * backed by a private `_get_*` engine internal (no public bound accessor — genuinely
    unwrappable, allowed by rule), or
  * a documented generator limitation in KNOWN_UNWRAPPABLE_PROPERTIES below (mostly indexed
    properties whose accessor is *inherited*, e.g. SpotLight3D.spot_range via Light3D.get_param,
    and redeclared-getter cases), or
  * a NEW silent drop — which fails this gate.

This is the durable guard that turned the `albedo_texture` regression (indexed properties were
skipped with no signal) into a build failure. Hand-shaped classes (DESKTOP_HANDSHAPED) are
exempt, exactly as in the wrapper drift gate — their surface is curated by hand.

Retiring an entry: teach the generator to emit the property (e.g. resolve inherited accessors),
re-adopt, and delete the line here — a now-covered allow-list entry also fails the gate, so the
list cannot rot.
"""

from __future__ import annotations

import json
import re
import sys
from pathlib import Path

from generate_api_wrapper import PROPERTY_NAME_OVERRIDES, camel_name

from check_wrapper_generator import API_DIR, DESKTOP_HANDSHAPED

ROOT = Path(__file__).resolve().parents[1]

# (class, raw_property_name) pairs on generated classes whose property the generator cannot yet
# express. Overwhelmingly indexed properties reached through an *inherited* accessor (get_param on
# the Light3D subclasses) or a getter that lives on a parent while the setter is local
# (CurveTexture.width). Keep sorted; see module docstring for how to retire an entry.
KNOWN_UNWRAPPABLE_PROPERTIES: frozenset[tuple[str, str]] = frozenset(
    {
        ("AimModifier3D", "setting_count"),
        ("AnimationNodeTransition", "input_count"),
        ("AreaLight3D", "area_attenuation"),
        ("AreaLight3D", "area_range"),
        ("ArrayOccluder3D", "indices"),
        ("ArrayOccluder3D", "vertices"),
        ("ConvertTransformModifier3D", "setting_count"),
        ("CopyTransformModifier3D", "setting_count"),
        ("CurveTexture", "width"),
        ("CurveXYZTexture", "width"),
        ("DirectionalLight2D", "height"),
        ("DirectionalLight3D", "directional_shadow_fade_start"),
        ("DirectionalLight3D", "directional_shadow_max_distance"),
        ("DirectionalLight3D", "directional_shadow_pancake_size"),
        ("DirectionalLight3D", "directional_shadow_split_1"),
        ("DirectionalLight3D", "directional_shadow_split_2"),
        ("DirectionalLight3D", "directional_shadow_split_3"),
        ("ExternalTexture", "size"),
        ("FontFile", "font_name"),
        ("FontFile", "font_stretch"),
        ("FontFile", "font_style"),
        ("FontFile", "font_weight"),
        ("FontFile", "style_name"),
        ("FontVariation", "opentype_features"),
        ("FontVariation", "spacing_bottom"),
        ("FontVariation", "spacing_glyph"),
        ("FontVariation", "spacing_space"),
        ("FontVariation", "spacing_top"),
        ("GradientTexture1D", "width"),
        ("GradientTexture2D", "height"),
        ("GradientTexture2D", "width"),
        ("ImageTexture", "image"),
        ("InputEventAction", "pressed"),
        ("InputEventJoypadButton", "pressed"),
        ("InputEventScreenTouch", "canceled"),
        ("InputEventScreenTouch", "pressed"),
        ("IterateIK3D", "setting_count"),
        ("Node2D", "global_transform"),
        ("Node2D", "transform"),
        ("NoiseTexture3D", "depth"),
        ("NoiseTexture3D", "height"),
        ("NoiseTexture3D", "width"),
        ("OmniLight3D", "omni_attenuation"),
        ("OmniLight3D", "omni_range"),
        ("PlaceholderMesh", "aabb"),
        ("PlaceholderTexture2D", "size"),
        ("PlaceholderTextureLayered", "layers"),
        ("PointLight2D", "height"),
        ("SplineIK3D", "setting_count"),
        ("SpotLight3D", "spot_angle"),
        ("SpotLight3D", "spot_angle_attenuation"),
        ("SpotLight3D", "spot_attenuation"),
        ("SpotLight3D", "spot_range"),
        ("SystemFont", "font_stretch"),
        ("SystemFont", "font_weight"),
        ("TwoBoneIK3D", "setting_count"),
        ("VisibleOnScreenNotifier3D", "aabb"),
    }
)

_MEMBER_RE = re.compile(r"^    (?:open |override |final |lateinit )*(?:var|val) (\w+)\s*:", re.MULTILINE)


def _wrapped_members() -> dict[str, set[str]]:
    return {f.stem: set(_MEMBER_RE.findall(f.read_text(encoding="utf-8"))) for f in Path(API_DIR).glob("*.kt")}


def main() -> int:
    api = json.loads((ROOT / "extension_api.json").read_text(encoding="utf-8"))
    by_name = {c["name"]: c for c in api["classes"]}
    wrapped = _wrapped_members()

    def covered(class_name: str, member: str) -> bool:
        cursor: str | None = class_name
        while cursor:
            if member in wrapped.get(cursor, set()):
                return True
            cursor = by_name.get(cursor, {}).get("inherits")
        return False

    new_drops: list[tuple[str, str, str]] = []
    allowed_hits: set[tuple[str, str]] = set()
    private_getter = 0

    for cls in api["classes"]:
        name = cls["name"]
        if name not in wrapped or name in DESKTOP_HANDSHAPED:
            continue
        for prop in cls.get("properties", ()):
            raw = str(prop.get("name") or "")
            member = PROPERTY_NAME_OVERRIDES.get((name, raw), camel_name(raw))
            if not member or covered(name, member):
                continue
            getter = str(prop.get("getter") or "")
            if getter.startswith("_"):
                private_getter += 1  # private engine internal — no public accessor to bind
            elif (name, raw) in KNOWN_UNWRAPPABLE_PROPERTIES:
                allowed_hits.add((name, raw))
            else:
                new_drops.append((name, raw, getter))

    stale = sorted(KNOWN_UNWRAPPABLE_PROPERTIES - allowed_hits)

    rc = 0
    if new_drops:
        rc = 1
        print(
            f"[property_coverage] FAIL {len(new_drops)} generated property(ies) are silently dropped "
            "(no wrapper member, not private, not allow-listed):",
            file=sys.stderr,
        )
        for name, raw, getter in sorted(new_drops):
            print(f"    {name}.{raw} (getter={getter or '<none>'})", file=sys.stderr)
        print(
            "    Fix the generator to emit them, or add to KNOWN_UNWRAPPABLE_PROPERTIES with a reason.",
            file=sys.stderr,
        )
    if stale:
        rc = 1
        print(
            f"[property_coverage] FAIL {len(stale)} KNOWN_UNWRAPPABLE_PROPERTIES entries are now covered "
            "(remove them):",
            file=sys.stderr,
        )
        for name, raw in stale:
            print(f"    {name}.{raw}", file=sys.stderr)

    if rc == 0:
        print(
            f"[property_coverage] PASS wrapped-class properties covered "
            f"(exempt: {private_getter} private-getter, {len(allowed_hits)} known-unwrappable, "
            f"hand-shaped classes skipped)"
        )
    return rc


if __name__ == "__main__":
    raise SystemExit(main())
