package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.RID

// KANAMA-IOS-HANDWRITTEN: [glue] PhysicsServer3D singleton (engine global), mirroring OS.kt.
object PhysicsServer3D {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("PhysicsServer3D")
    }

    fun bodyAddCollisionException(body: RID, exceptedBody: RID) =
        ObjectCalls.ptrcallWithTwoRIDArgs(bodyAddCollisionExceptionBind, singleton, body, exceptedBody)

    private val bodyAddCollisionExceptionBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_add_collision_exception", 395945892L)
    }

    // Callable-arg methods (task 09 / Phase 1.4): object+method GodotCallable through the
    // PT_CALLABLE ptrcall path. body_set_force_integration_callback stays unsupported (its extra
    // Variant "userdata" arg needs a Variant ptrcall arg kind, out of this task's scope).
    fun areaSetMonitorCallback(area: RID, callback: GodotCallable) =
        ObjectCalls.ptrcallWithRIDCallableArgs(areaSetMonitorCallbackBind, singleton, area, callback.target.handle, callback.method)

    private val areaSetMonitorCallbackBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_set_monitor_callback", 3379118538L)
    }

    fun areaSetAreaMonitorCallback(area: RID, callback: GodotCallable) =
        ObjectCalls.ptrcallWithRIDCallableArgs(areaSetAreaMonitorCallbackBind, singleton, area, callback.target.handle, callback.method)

    private val areaSetAreaMonitorCallbackBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "area_set_area_monitor_callback", 3379118538L)
    }

    fun bodySetStateSyncCallback(body: RID, callable: GodotCallable) =
        ObjectCalls.ptrcallWithRIDCallableArgs(bodySetStateSyncCallbackBind, singleton, body, callable.target.handle, callable.method)

    private val bodySetStateSyncCallbackBind by lazy {
        ObjectCalls.getMethodBind("PhysicsServer3D", "body_set_state_sync_callback", 3379118538L)
    }
}
