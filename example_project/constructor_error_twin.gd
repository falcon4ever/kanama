extends Node

# GDScript twin of ConstructorErrorSmoke.kt: its constructor fails at run time.
var budget: int = _fail()


func probe() -> int:
	return budget


func _fail() -> int:
	var empty: Array = []
	return empty[3]
