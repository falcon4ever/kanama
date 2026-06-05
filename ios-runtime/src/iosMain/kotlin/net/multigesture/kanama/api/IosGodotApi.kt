@file:Suppress("unused")

package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_canvas_item_get_viewport_rect
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_get_method_bind
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node2d_get_position
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node2d_set_position
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_add_child
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_get_child
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_get_child_count
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_node_remove_child
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_emit_signal_int
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_object_queue_free
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_resource_loader_load
import net.multigesture.kanama.ios.cinterop.kanama_ios_godot_sprite2d_set_texture
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2
import kotlin.random.Random

@RequiresOptIn(
    message = "This API exposes a Godot object whose lifetime is owned outside Kotlin.",
    level = RequiresOptIn.Level.WARNING,
)
annotation class ManualGodotLifetimeApi

abstract class KanamaScript<Self : Any>(
    val godotObject: MemorySegment,
    selfFactory: (MemorySegment) -> Self,
) {
    val self: Self = selfFactory(godotObject)

    inline fun <T> selfAs(ctor: (MemorySegment) -> T): T = ctor(godotObject)
}

open class GodotObject(
    val handle: MemorySegment,
) {
    constructor(handle: Long) : this(MemorySegment.ofAddress(handle))

    fun requireOpenHandle(): MemorySegment = handle

    fun queueFree() {
        IosGodot.objectQueueFree(handle.address())
    }

    fun emitSignal(signalName: String, value: Int): Int =
        IosGodot.objectEmitSignalInt(handle.address(), signalName, value.toLong())

    fun emitSignal(signalName: String, value: Long): Int =
        IosGodot.objectEmitSignalInt(handle.address(), signalName, value)

    open fun close() {
    }
}

open class Resource(handle: MemorySegment) : GodotObject(handle)

open class Texture2D(handle: MemorySegment) : Resource(handle)

open class Node(handle: MemorySegment) : GodotObject(handle) {
    fun addChild(child: Node) {
        IosGodot.nodeAddChild(handle.address(), child.handle.address())
    }

    fun removeChild(child: Node) {
        IosGodot.nodeRemoveChild(handle.address(), child.handle.address())
    }

    fun getChildCount(): Long =
        IosGodot.nodeGetChildCount(handle.address())

    fun getChild(index: Int): Node? =
        IosGodot.nodeGetChild(handle.address(), index).takeIf { it != 0L }?.let {
            Node(MemorySegment.ofAddress(it))
        }
}

open class CanvasItem(handle: MemorySegment) : Node(handle) {
    fun getViewportRect(): Rect2 =
        IosGodot.canvasItemGetViewportRect(handle.address())
}

open class Node2D(handle: MemorySegment) : CanvasItem(handle) {
    var position: Vector2
        get() = IosGodot.node2dGetPosition(handle.address())
        set(value) {
            IosGodot.node2dSetPosition(handle.address(), value)
        }
}

open class Control(handle: MemorySegment) : CanvasItem(handle)

class Label(handle: MemorySegment) : Control(handle) {
    var text: String
        get() = ""
        set(value) {
            IosGodot.setObjectText(handle.address(), value)
        }
}

class Sprite2D(handle: MemorySegment) : Node2D(handle) {
    fun setTexture(texture: Texture2D?) {
        IosGodot.sprite2dSetTexture(handle.address(), texture?.handle?.address() ?: 0L)
    }
}

object ResourceLoader {
    fun load(path: String): Resource? =
        IosGodot.resourceLoaderLoad(path, "").takeIf { it != 0L }?.let {
            Resource(MemorySegment.ofAddress(it))
        }

    fun loadTexture2D(path: String): Texture2D? =
        IosGodot.resourceLoaderLoad(path, "Texture2D").takeIf { it != 0L }?.let {
            Texture2D(MemorySegment.ofAddress(it))
        }
}

object GD {
    fun randomize() {
    }

    fun randi(): Long =
        Random.nextLong().let { if (it == Long.MIN_VALUE) 0L else kotlin.math.abs(it) }

    fun randf(): Double =
        Random.nextDouble()
}

@OptIn(ExperimentalForeignApi::class)
private object IosGodot {
    private const val LABEL_SET_TEXT_HASH = 83702148L
    private var labelSetTextBind = 0L

    fun setObjectText(objectHandle: Long, value: String): Boolean {
        val bind = labelSetTextBind()
        if (bind == 0L || objectHandle == 0L) {
            return false
        }
        net.multigesture.kanama.ios.cinterop.kanama_ios_godot_ptrcall_string_arg(bind, objectHandle, value)
        return true
    }

    fun objectQueueFree(objectHandle: Long) {
        kanama_ios_godot_object_queue_free(objectHandle)
    }

    fun nodeAddChild(parent: Long, child: Long) {
        kanama_ios_godot_node_add_child(parent, child)
    }

    fun nodeRemoveChild(parent: Long, child: Long) {
        kanama_ios_godot_node_remove_child(parent, child)
    }

    fun nodeGetChildCount(node: Long): Long =
        kanama_ios_godot_node_get_child_count(node)

    fun nodeGetChild(node: Long, index: Int): Long =
        kanama_ios_godot_node_get_child(node, index)

    fun node2dGetPosition(node: Long): Vector2 =
        memScoped {
            val x = alloc<DoubleVarCompat>()
            val y = alloc<DoubleVarCompat>()
            kanama_ios_godot_node2d_get_position(node, x.ptr, y.ptr)
            Vector2(x.value, y.value)
        }

    fun node2dSetPosition(node: Long, value: Vector2) {
        kanama_ios_godot_node2d_set_position(node, value.x, value.y)
    }

    fun canvasItemGetViewportRect(objectHandle: Long): Rect2 =
        memScoped {
            val x = alloc<DoubleVarCompat>()
            val y = alloc<DoubleVarCompat>()
            val width = alloc<DoubleVarCompat>()
            val height = alloc<DoubleVarCompat>()
            kanama_ios_godot_canvas_item_get_viewport_rect(
                objectHandle,
                x.ptr,
                y.ptr,
                width.ptr,
                height.ptr,
            )
            Rect2(Vector2(x.value, y.value), Vector2(width.value, height.value))
        }

    fun resourceLoaderLoad(path: String, typeHint: String): Long =
        kanama_ios_godot_resource_loader_load(path, typeHint)

    fun sprite2dSetTexture(sprite: Long, texture: Long) {
        kanama_ios_godot_sprite2d_set_texture(sprite, texture)
    }

    fun objectEmitSignalInt(objectHandle: Long, signalName: String, value: Long): Int =
        kanama_ios_godot_object_emit_signal_int(objectHandle, signalName, value)

    private fun labelSetTextBind(): Long {
        if (labelSetTextBind == 0L) {
            labelSetTextBind = kanama_ios_godot_get_method_bind(
                "Label",
                "set_text",
                LABEL_SET_TEXT_HASH,
            )
        }
        return labelSetTextBind
    }
}

@OptIn(ExperimentalForeignApi::class)
private typealias DoubleVarCompat = kotlinx.cinterop.DoubleVar
