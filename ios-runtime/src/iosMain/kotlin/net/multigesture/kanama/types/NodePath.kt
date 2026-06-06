package net.multigesture.kanama.types

data class NodePath(val path: String) {
    override fun toString(): String = path

    companion object {
        val EMPTY = NodePath("")
    }
}
