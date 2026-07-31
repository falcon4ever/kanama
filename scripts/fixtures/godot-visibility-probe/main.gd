extends Node2D

# Plain-GDScript control for task 71. NO Kanama anywhere in this project.
#
# It mirrors what dodge's mobs do -- a node moves steadily off screen and is
# expected to report screen_exited -- and reports everything to the SERVER via
# fetch, so it needs no browser-automation protocol.
#
# It reports more than pass/fail on purpose. If the signal does not fire, the
# samples below distinguish the candidate explanations from each other:
#
#   * is_on_screen() staying true while x is far outside the viewport
#     -> the engine still thinks the node is visible; the notifier's rect or the
#        viewport rect is not what we assume (NOT a signal-delivery problem)
#   * is_on_screen() flipping false with no screen_exited beacon
#     -> the state updates but the signal does not reach us
#   * screen_entered never firing either
#     -> the notifier is inert on this host from the start
#   * fps / sim-rate
#     -> tests the "~8.7x frame rate" hypothesis directly, since that is the only
#        property unique to the failing CI cell
#   * the video adapter string
#     -> names the actual GL stack per browser, which is the thing two Ubuntu
#        boxes and the CI runner disagree about

const SPEED := 400.0
const GIVE_UP_SECONDS := 25.0
const SAMPLE_EVERY := 2.0

var _elapsed := 0.0
var _since_sample := SAMPLE_EVERY
var _reported := false
var _entered := false

func _ready() -> void:
	var adapter := "unknown"
	if RenderingServer.has_method("get_video_adapter_name"):
		adapter = str(RenderingServer.get_video_adapter_name())
	_beacon("ready-adapter-%s" % _slug(adapter))
	var notifier: VisibleOnScreenNotifier2D = $Mover/Notifier
	notifier.screen_entered.connect(_on_entered)
	notifier.screen_exited.connect(_on_exited)

func _process(delta: float) -> void:
	_elapsed += delta
	_since_sample += delta
	$Mover.position.x += SPEED * delta
	var notifier: VisibleOnScreenNotifier2D = $Mover/Notifier
	var rect := get_viewport_rect()
	$Label.text = "x=%d t=%.1f on=%s" % [int($Mover.position.x), _elapsed, str(notifier.is_on_screen())]

	if _since_sample >= SAMPLE_EVERY:
		_since_sample = 0.0
		# sim seconds, node x, engine's own visibility answer, fps, viewport rect,
		# and wall-clock ms -- sim vs wall is the frame-rate hypothesis.
		_beacon("sample-t%d-x%d-on%d-fps%d-vp%dx%d-wall%d" % [
			int(_elapsed),
			int($Mover.position.x),
			1 if notifier.is_on_screen() else 0,
			int(Engine.get_frames_per_second()),
			int(rect.size.x),
			int(rect.size.y),
			int(Time.get_ticks_msec()),
		])

	if _elapsed > GIVE_UP_SECONDS and not _reported:
		_reported = true
		_beacon("timeout-x%d-on%d-entered%d" % [
			int($Mover.position.x),
			1 if notifier.is_on_screen() else 0,
			1 if _entered else 0,
		])

func _on_entered() -> void:
	_entered = true
	_beacon("entered-x%d" % int($Mover.position.x))

func _on_exited() -> void:
	if _reported:
		return
	_reported = true
	_beacon("exited-x%d-t%d" % [int($Mover.position.x), int(_elapsed)])

func _slug(value: String) -> String:
	var out := ""
	for c in value:
		out += c if c.is_valid_identifier() or c.is_valid_int() else "_"
	return out.substr(0, 40)

func _beacon(what: String) -> void:
	JavaScriptBridge.eval("fetch('/probe/" + what + "')", true)
