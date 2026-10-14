extends Node
## Harness-only (task 129 E): the GDScript twin of BundleProbe's GD / Mathf rows. Each case is the
## GDScript call the Kotlin row makes through the shared GD, which Web runs in Kotlin (task 129 E
## review B1: scripts/web_local_utilities.py); the engine answers here and the Kotlin side compares
## the bits within the row's ulp bound.

func gd_value(index: int) -> float:
	match index:
		0: return sin(0.7)
		1: return pow(2.5, 1.3)
		2: return atan2(0.3, -0.8)
		3: return lerpf(1.0, 3.0, 0.25)
		4: return snappedf(7.37, 0.25)
		5: return wrapf(-0.5, 0.0, 2.0)
		6: return sin(1.1)
		7: return smoothstep(0.0, 2.0, 0.6)
		8:
			seed(42)
			return randf()
		9: return fposmod(-5.5, 3.0)
		10: return ease(0.4, 2.0)
		11: return db_to_linear(-6.0)
		12: return float(roundi(2.5))
		13: return exp(1.7)
		14: return cos(2.3)
		15: return tan(0.4)
		16: return asin(0.3)
		17: return acos(-0.6)
		18: return atan(1.7)
		19: return log(12.5)
		20: return sinh(0.9)
		21: return cosh(0.9)
		22: return tanh(0.9)
		23: return asinh(1.3)
		24: return acosh(2.4)
		25: return atanh(0.45)
		26: return linear_to_db(0.3)
		27: return ease(0.7, -2.4)
		28: return ease(0.2, 0.4)
		29: return cubic_interpolate(1.0, 4.0, -2.0, 7.0, 0.3)
		30: return cubic_interpolate_angle(0.2, 6.0, -0.4, 6.4, 0.6)
		31: return cubic_interpolate_in_time(1.0, 4.0, -2.0, 7.0, 0.3, 1.0, -0.5, 1.5)
		32: return cubic_interpolate_angle_in_time(0.5, 5.9, -1.0, 2.0, 0.4, 1.0, -0.5, 1.5)
		33: return bezier_interpolate(0.0, 1.5, 2.5, 4.0, 0.35)
		34: return bezier_derivative(0.0, 1.5, 2.5, 4.0, 0.35)
		35: return lerp_angle(0.3, 5.9, 0.4)
		36: return angle_difference(0.3, 5.9)
		37: return rotate_toward(0.2, -2.8, 0.5)
		38: return pingpong(7.3, 2.0)
		39: return inverse_lerp(2.0, 7.0, 3.3)
		40: return remap(3.3, 2.0, 7.0, -1.0, 1.0)
		41: return move_toward(1.0, 4.0, 0.7)
		42: return fmod(-7.3, 2.1)
		43: return sqrt(2.0)
		44: return deg_to_rad(33.0)
		45: return float(floori(-3.5))
		46: return float(snappedi(17.4, 5))
		47: return float(posmod(-7, 3))
		48: return float(nearest_po2(37))
		49: return float(wrapi(-3, 0, 5))
		50: return max(1.5, 2.5, -1.0)
		51: return clamp(0.0, 3.0, 1.0)
		52: return float(min(4, -2, 9))
	return NAN

func gd_text(index: int) -> String:
	match index:
		0: return var_to_str({"k": [1, 2]})
		1: return str(1, "a", 2.5)
		2: return "{a}-{b}".format({"a": 1, "b": "x"})
		3: return "{0}+{1}".format([2, 3.5])
	return ""
