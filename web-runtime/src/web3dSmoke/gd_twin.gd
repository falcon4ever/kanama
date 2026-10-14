extends Node
## Harness-only (task 129 E): the GDScript twin of BundleProbe's GD / Mathf rows. Each case is the
## GDScript call the Kotlin row makes through the shared GD; the Kotlin side compares the bits.

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
	return NAN

func gd_text(index: int) -> String:
	match index:
		0: return var_to_str({"k": [1, 2]})
		1: return str(1, "a", 2.5)
		2: return "{a}-{b}".format({"a": 1, "b": "x"})
		3: return "{0}+{1}".format([2, 3.5])
	return ""
