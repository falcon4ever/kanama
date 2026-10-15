extends Node
# Task 138 item 6: keeps the game running until the runtime's ownership self-test
# (KANAMA_OWNERSHIP_SELFTEST / kanama/debug/ownership_self_test) has printed its verdict, which it
# marks with the Engine metadata below; the timer is only the cap.

const DONE_META := "kanama_ownership_self_test"


func _ready() -> void:
	get_tree().create_timer(60.0).timeout.connect(get_tree().quit)


func _process(_delta: float) -> void:
	if Engine.has_meta(DONE_META):
		get_tree().quit()
