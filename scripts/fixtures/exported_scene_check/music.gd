extends AudioStreamPlayer

# An autoplaying autoload, as Squash the Creeps' MusicPlayer: the scene check must quit without
# starting it, or Godot leaks the still-playing stream at exit.
func _init() -> void:
	stream = AudioStreamGenerator.new()
	autoplay = true
