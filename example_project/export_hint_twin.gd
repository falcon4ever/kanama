extends Node
# Task 133 C: the GDScript twin of ExportHintSmoke.kt. main.gd compares the two property lists.

@export_range(0, 100, 1) var r_int: int = 5
@export_range(0.0, 1.0, 0.01) var r_float: float = 0.5
@export_range(-10, 10) var r_default_step: float = 0.0
@export_range(0, 100, 0.5, "or_greater", "or_less", "suffix:m") var r_extra: float = 1.0
@export_range(0, 360, 0.1, "radians_as_degrees") var r_rad: float = PI / 3.0
@export_range(1e-05, 1000.0) var r_tiny: float = 1.0
@export_range(0, 1, 0.01, "exp", "degrees", "prefer_slider", "hide_control") var r_flags: float = 0.0
@export_file var f_any: String
@export_file("*.png", "*.jpg") var f_img: String
@export_file_path("*.txt") var f_path: String
@export_dir var d: String
@export_global_file("*.cfg") var gf: String
@export_global_dir var gdir: String
@export_multiline var ml: String
@export_multiline("monospace", "no_wrap") var ml2: String
@export_placeholder("Name here") var ph: String
@export_placeholder("a,b") var ph2: String
@export_enum("Warrior", "Magician:5", "Thief") var en_int: int
@export_enum("Rebecca", "Mary") var en_str: String
@export_flags("Fire", "Water:4", "Earth") var fl: int
@export_flags_2d_render var l2r: int
@export_flags_2d_physics var l2p: int
@export_flags_2d_navigation var l2n: int
@export_flags_3d_render var l3r: int
@export_flags_3d_physics var l3p: int
@export_flags_3d_navigation var l3n: int
@export_flags_avoidance var lav: int
@export_exp_easing var ease: float = 1.0
@export_exp_easing("attenuation", "positive_only") var ease2: float = 1.0
@export_node_path("Button", "TouchScreenButton") var np: NodePath
@export_node_path var np2: NodePath
@export_storage var st: int = 3
@export_custom(PROPERTY_HINT_PASSWORD, "") var pw: String
@export_file("*.png") var f_arr: Array[String]
@export_multiline var ml_arr: Array[String]
@export_enum("A", "B") var en_arr: Array[String]
@export_color_no_alpha var cna: Color = Color.RED
@export var col: Color = Color(0.2, 0.4, 0.6, 0.8)
