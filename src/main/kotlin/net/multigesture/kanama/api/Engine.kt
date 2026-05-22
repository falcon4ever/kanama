package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName

/**
 * Provides access to engine properties.
 *
 * Generated from Godot docs: Engine
 */
object Engine {
    var maxFps: Long
        @JvmName("maxFpsProperty")
        get() = getMaxFps()
        @JvmName("setMaxFpsProperty")
        set(value) = setMaxFps(value)

    private const val GET_INT_HASH = 3905245786L
    private const val GET_FLOAT_HASH = 1740695150L
    private const val GET_FLOAT_ALT_HASH = 191475506L
    private const val GET_STRING_HASH = 201670096L
    private const val BOOL_NOARGS_HASH = 36873697L
    private const val PACKED_STRING_ARRAY_NOARGS_HASH = 1139954409L
    private const val DICTIONARY_NOARGS_HASH = 3102165223L
    private const val SET_INT_HASH = 1286410249L
    private const val SET_FLOAT_HASH = 373806689L
    private const val SET_BOOL_HASH = 2586408642L
    private const val GET_COPYRIGHT_INFO_HASH = 3995934104L
    private const val CAPTURE_SCRIPT_BACKTRACES_HASH = 873284517L

    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("Engine")
    }

    private val setPhysicsTicksPerSecondBind by lazy {
        ObjectCalls.getMethodBind("Engine", "set_physics_ticks_per_second", SET_INT_HASH)
    }

    private val getPhysicsTicksPerSecondBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_physics_ticks_per_second", GET_INT_HASH)
    }

    private val setMaxPhysicsStepsPerFrameBind by lazy {
        ObjectCalls.getMethodBind("Engine", "set_max_physics_steps_per_frame", SET_INT_HASH)
    }

    private val getMaxPhysicsStepsPerFrameBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_max_physics_steps_per_frame", GET_INT_HASH)
    }

    private val setPhysicsJitterFixBind by lazy {
        ObjectCalls.getMethodBind("Engine", "set_physics_jitter_fix", SET_FLOAT_HASH)
    }

    private val getPhysicsJitterFixBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_physics_jitter_fix", GET_FLOAT_HASH)
    }

    private val getPhysicsInterpolationFractionBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_physics_interpolation_fraction", GET_FLOAT_HASH)
    }

    private val setMaxFpsBind by lazy {
        ObjectCalls.getMethodBind("Engine", "set_max_fps", SET_INT_HASH)
    }

    private val getMaxFpsBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_max_fps", GET_INT_HASH)
    }

    private val setTimeScaleBind by lazy {
        ObjectCalls.getMethodBind("Engine", "set_time_scale", SET_FLOAT_HASH)
    }

    private val getTimeScaleBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_time_scale", GET_FLOAT_ALT_HASH)
    }

    private val getFramesDrawnBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_frames_drawn", 2455072627L)
    }

    private val getFramesPerSecondBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_frames_per_second", GET_FLOAT_HASH)
    }

    private val getPhysicsFramesBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_physics_frames", GET_INT_HASH)
    }

    private val getProcessFramesBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_process_frames", GET_INT_HASH)
    }

    private val getScriptLanguageCountBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_script_language_count", 2455072627L)
    }

    private val getScriptLanguageBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_script_language", 2151255799L)
    }

    private val getArchitectureNameBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_architecture_name", GET_STRING_HASH)
    }

    private val getMainLoopBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_main_loop", 1016888095L)
    }

    private val getLicenseTextBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_license_text", GET_STRING_HASH)
    }

    private val getVersionInfoBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_version_info", DICTIONARY_NOARGS_HASH)
    }

    private val getAuthorInfoBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_author_info", DICTIONARY_NOARGS_HASH)
    }

    private val getDonorInfoBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_donor_info", DICTIONARY_NOARGS_HASH)
    }

    private val getLicenseInfoBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_license_info", DICTIONARY_NOARGS_HASH)
    }

    private val getCopyrightInfoBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_copyright_info", GET_COPYRIGHT_INFO_HASH)
    }

    private val isInPhysicsFrameBind by lazy {
        ObjectCalls.getMethodBind("Engine", "is_in_physics_frame", BOOL_NOARGS_HASH)
    }

    private val hasSingletonBind by lazy {
        ObjectCalls.getMethodBind("Engine", "has_singleton", 2619796661L)
    }

    private val getSingletonBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_singleton", 1371597918L)
    }

    private val getSingletonListBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_singleton_list", PACKED_STRING_ARRAY_NOARGS_HASH)
    }

    private val registerSingletonBind by lazy {
        ObjectCalls.getMethodBind("Engine", "register_singleton", 965313290L)
    }

    private val unregisterSingletonBind by lazy {
        ObjectCalls.getMethodBind("Engine", "unregister_singleton", 3304788590L)
    }

    private val registerScriptLanguageBind by lazy {
        ObjectCalls.getMethodBind("Engine", "register_script_language", 1850254898L)
    }

    private val unregisterScriptLanguageBind by lazy {
        ObjectCalls.getMethodBind("Engine", "unregister_script_language", 1850254898L)
    }

    private val captureScriptBacktracesBind by lazy {
        ObjectCalls.getMethodBind("Engine", "capture_script_backtraces", CAPTURE_SCRIPT_BACKTRACES_HASH)
    }

    private val isEditorHintBind by lazy {
        ObjectCalls.getMethodBind("Engine", "is_editor_hint", BOOL_NOARGS_HASH)
    }

    private val isEmbeddedInEditorBind by lazy {
        ObjectCalls.getMethodBind("Engine", "is_embedded_in_editor", BOOL_NOARGS_HASH)
    }

    private val getWriteMoviePathBind by lazy {
        ObjectCalls.getMethodBind("Engine", "get_write_movie_path", GET_STRING_HASH)
    }

    private val setPrintToStdoutBind by lazy {
        ObjectCalls.getMethodBind("Engine", "set_print_to_stdout", SET_BOOL_HASH)
    }

    private val isPrintingToStdoutBind by lazy {
        ObjectCalls.getMethodBind("Engine", "is_printing_to_stdout", BOOL_NOARGS_HASH)
    }

    private val setPrintErrorMessagesBind by lazy {
        ObjectCalls.getMethodBind("Engine", "set_print_error_messages", SET_BOOL_HASH)
    }

    private val isPrintingErrorMessagesBind by lazy {
        ObjectCalls.getMethodBind("Engine", "is_printing_error_messages", BOOL_NOARGS_HASH)
    }

    /**
     * The number of fixed iterations per second. This controls how often physics simulation and the
     * `Node._physics_process` method are run. CPU usage scales approximately with the physics tick
     * rate. However, at very low tick rates (usually below 30), physics behavior can break down. Input
     * can also become less responsive at low tick rates as there can be a gap between input being
     * registered, and the response on the next physics tick. High tick rates give more accurate
     * physics simulation, particularly for fast moving objects. For example, racing games may benefit
     * from increasing the tick rate above the default 60. See also `max_fps` and
     * `ProjectSettings.physics/common/physics_ticks_per_second`. Note: Only
     * `max_physics_steps_per_frame` physics ticks may be simulated per rendered frame at most. If more
     * physics ticks have to be simulated per rendered frame to keep up with rendering, the project
     * will appear to slow down (even if `delta` is used consistently in physics calculations).
     * Therefore, it is recommended to also increase `max_physics_steps_per_frame` if increasing
     * `physics_ticks_per_second` significantly above its default value. Note: Consider enabling
     * physics interpolation ($DOCS_URL/tutorials/physics/interpolation/index.html) if you change
     * `physics_ticks_per_second` to a value that is not a multiple of `60`. Using physics
     * interpolation will avoid jittering when the monitor refresh rate and physics update rate don't
     * exactly match.
     *
     * Generated from Godot docs: Engine.set_physics_ticks_per_second
     */
    @JvmStatic
    fun setPhysicsTicksPerSecond(value: Long) {
        ObjectCalls.ptrcallWithIntArg(setPhysicsTicksPerSecondBind, singleton, value.toInt())
    }

    /**
     * The number of fixed iterations per second. This controls how often physics simulation and the
     * `Node._physics_process` method are run. CPU usage scales approximately with the physics tick
     * rate. However, at very low tick rates (usually below 30), physics behavior can break down. Input
     * can also become less responsive at low tick rates as there can be a gap between input being
     * registered, and the response on the next physics tick. High tick rates give more accurate
     * physics simulation, particularly for fast moving objects. For example, racing games may benefit
     * from increasing the tick rate above the default 60. See also `max_fps` and
     * `ProjectSettings.physics/common/physics_ticks_per_second`. Note: Only
     * `max_physics_steps_per_frame` physics ticks may be simulated per rendered frame at most. If more
     * physics ticks have to be simulated per rendered frame to keep up with rendering, the project
     * will appear to slow down (even if `delta` is used consistently in physics calculations).
     * Therefore, it is recommended to also increase `max_physics_steps_per_frame` if increasing
     * `physics_ticks_per_second` significantly above its default value. Note: Consider enabling
     * physics interpolation ($DOCS_URL/tutorials/physics/interpolation/index.html) if you change
     * `physics_ticks_per_second` to a value that is not a multiple of `60`. Using physics
     * interpolation will avoid jittering when the monitor refresh rate and physics update rate don't
     * exactly match.
     *
     * Generated from Godot docs: Engine.get_physics_ticks_per_second
     */
    @JvmStatic
    fun getPhysicsTicksPerSecond(): Long =
        ObjectCalls.ptrcallNoArgsRetInt(getPhysicsTicksPerSecondBind, singleton).toLong()

    /**
     * The maximum number of physics steps that can be simulated each rendered frame. Note: The default
     * value is tuned to prevent expensive physics simulations from triggering even more expensive
     * simulations indefinitely. However, the game will appear to slow down if the rendering FPS is
     * less than `1 / max_physics_steps_per_frame` of `physics_ticks_per_second`. This occurs even if
     * `delta` is consistently used in physics calculations. To avoid this, increase
     * `max_physics_steps_per_frame` if you have increased `physics_ticks_per_second` significantly
     * above its default value.
     *
     * Generated from Godot docs: Engine.set_max_physics_steps_per_frame
     */
    @JvmStatic
    fun setMaxPhysicsStepsPerFrame(value: Long) {
        ObjectCalls.ptrcallWithIntArg(setMaxPhysicsStepsPerFrameBind, singleton, value.toInt())
    }

    /**
     * The maximum number of physics steps that can be simulated each rendered frame. Note: The default
     * value is tuned to prevent expensive physics simulations from triggering even more expensive
     * simulations indefinitely. However, the game will appear to slow down if the rendering FPS is
     * less than `1 / max_physics_steps_per_frame` of `physics_ticks_per_second`. This occurs even if
     * `delta` is consistently used in physics calculations. To avoid this, increase
     * `max_physics_steps_per_frame` if you have increased `physics_ticks_per_second` significantly
     * above its default value.
     *
     * Generated from Godot docs: Engine.get_max_physics_steps_per_frame
     */
    @JvmStatic
    fun getMaxPhysicsStepsPerFrame(): Long =
        ObjectCalls.ptrcallNoArgsRetInt(getMaxPhysicsStepsPerFrameBind, singleton).toLong()

    /**
     * How much physics ticks are synchronized with real time. If `0` or less, the ticks are fully
     * synchronized. Higher values cause the in-game clock to deviate more from the real clock, but
     * they smooth out framerate jitters. Note: The default value of `0.5` should be good enough for
     * most cases; values above `2` could cause the game to react to dropped frames with a noticeable
     * delay and are not recommended. Note: When using a custom physics interpolation solution, or
     * within a network game, it's recommended to disable the physics jitter fix by setting this
     * property to `0`.
     *
     * Generated from Godot docs: Engine.set_physics_jitter_fix
     */
    @JvmStatic
    fun setPhysicsJitterFix(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPhysicsJitterFixBind, singleton, value)
    }

    /**
     * How much physics ticks are synchronized with real time. If `0` or less, the ticks are fully
     * synchronized. Higher values cause the in-game clock to deviate more from the real clock, but
     * they smooth out framerate jitters. Note: The default value of `0.5` should be good enough for
     * most cases; values above `2` could cause the game to react to dropped frames with a noticeable
     * delay and are not recommended. Note: When using a custom physics interpolation solution, or
     * within a network game, it's recommended to disable the physics jitter fix by setting this
     * property to `0`.
     *
     * Generated from Godot docs: Engine.get_physics_jitter_fix
     */
    @JvmStatic
    fun getPhysicsJitterFix(): Double =
        ObjectCalls.ptrcallNoArgsRetDouble(getPhysicsJitterFixBind, singleton)

    /**
     * Returns the fraction through the current physics tick we are at the time of rendering the frame.
     * This can be used to implement fixed timestep interpolation.
     *
     * Generated from Godot docs: Engine.get_physics_interpolation_fraction
     */
    @JvmStatic
    fun getPhysicsInterpolationFraction(): Double =
        ObjectCalls.ptrcallNoArgsRetDouble(getPhysicsInterpolationFractionBind, singleton)

    /**
     * The maximum number of frames that can be rendered every second (FPS). A value of `0` means the
     * framerate is uncapped. Limiting the FPS can be useful to reduce the host machine's power
     * consumption, which reduces heat, noise emissions, and improves battery life. If
     * `ProjectSettings.display/window/vsync/vsync_mode` is Enabled or Adaptive, the setting takes
     * precedence and the max FPS number cannot exceed the monitor's refresh rate. See also
     * `DisplayServer.screen_get_refresh_rate`. If `ProjectSettings.display/window/vsync/vsync_mode` is
     * Enabled, on monitors with variable refresh rate enabled (G-Sync/FreeSync), using an FPS limit a
     * few frames lower than the monitor's refresh rate will reduce input lag while avoiding tearing
     * (https://blurbusters.com/howto-low-lag-vsync-on/). At higher refresh rates, the difference
     * between the FPS limit and the monitor refresh rate should be increased to ensure frames to
     * account for timing inaccuracies. The optimal formula for the FPS limit value in this scenario is
     * `r - (r * r) / 3600.0`, where `r` is the monitor's refresh rate. Note: The actual number of
     * frames per second may still be below this value if the CPU or GPU cannot keep up with the
     * project's logic and rendering. Note: If `ProjectSettings.display/window/vsync/vsync_mode` is
     * Disabled, limiting the FPS to a high value that can be consistently reached on the system can
     * reduce input lag compared to an uncapped framerate. Since this works by ensuring the GPU load is
     * lower than 100%, this latency reduction is only effective in GPU-bottlenecked scenarios, not
     * CPU-bottlenecked scenarios.
     *
     * Generated from Godot docs: Engine.set_max_fps
     */
    @JvmStatic
    fun setMaxFps(value: Long) {
        ObjectCalls.ptrcallWithIntArg(setMaxFpsBind, singleton, value.toInt())
    }

    /**
     * The maximum number of frames that can be rendered every second (FPS). A value of `0` means the
     * framerate is uncapped. Limiting the FPS can be useful to reduce the host machine's power
     * consumption, which reduces heat, noise emissions, and improves battery life. If
     * `ProjectSettings.display/window/vsync/vsync_mode` is Enabled or Adaptive, the setting takes
     * precedence and the max FPS number cannot exceed the monitor's refresh rate. See also
     * `DisplayServer.screen_get_refresh_rate`. If `ProjectSettings.display/window/vsync/vsync_mode` is
     * Enabled, on monitors with variable refresh rate enabled (G-Sync/FreeSync), using an FPS limit a
     * few frames lower than the monitor's refresh rate will reduce input lag while avoiding tearing
     * (https://blurbusters.com/howto-low-lag-vsync-on/). At higher refresh rates, the difference
     * between the FPS limit and the monitor refresh rate should be increased to ensure frames to
     * account for timing inaccuracies. The optimal formula for the FPS limit value in this scenario is
     * `r - (r * r) / 3600.0`, where `r` is the monitor's refresh rate. Note: The actual number of
     * frames per second may still be below this value if the CPU or GPU cannot keep up with the
     * project's logic and rendering. Note: If `ProjectSettings.display/window/vsync/vsync_mode` is
     * Disabled, limiting the FPS to a high value that can be consistently reached on the system can
     * reduce input lag compared to an uncapped framerate. Since this works by ensuring the GPU load is
     * lower than 100%, this latency reduction is only effective in GPU-bottlenecked scenarios, not
     * CPU-bottlenecked scenarios.
     *
     * Generated from Godot docs: Engine.get_max_fps
     */
    @JvmStatic
    fun getMaxFps(): Long =
        ObjectCalls.ptrcallNoArgsRetInt(getMaxFpsBind, singleton).toLong()

    /**
     * The speed multiplier at which the in-game clock updates, compared to real time. For example, if
     * set to `2.0` the game runs twice as fast, and if set to `0.5` the game runs half as fast. This
     * value affects `Timer`, `SceneTreeTimer`, and all other simulations that make use of `delta` time
     * (such as `Node._process` and `Node._physics_process`). Note: It's recommended to keep this
     * property above `0.0`, as the game may behave unexpectedly otherwise. Note: This does not affect
     * audio playback speed. Use `AudioServer.playback_speed_scale` to adjust audio playback speed
     * independently of `Engine.time_scale`. Note: This does not automatically adjust
     * `physics_ticks_per_second`. With values above `1.0` physics simulation may become less precise,
     * as each physics tick will stretch over a larger period of engine time. If you're modifying
     * `Engine.time_scale` to speed up simulation by a large factor, consider also increasing
     * `physics_ticks_per_second` to make the simulation more reliable.
     *
     * Generated from Godot docs: Engine.set_time_scale
     */
    @JvmStatic
    fun setTimeScale(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTimeScaleBind, singleton, value)
    }

    /**
     * The speed multiplier at which the in-game clock updates, compared to real time. For example, if
     * set to `2.0` the game runs twice as fast, and if set to `0.5` the game runs half as fast. This
     * value affects `Timer`, `SceneTreeTimer`, and all other simulations that make use of `delta` time
     * (such as `Node._process` and `Node._physics_process`). Note: It's recommended to keep this
     * property above `0.0`, as the game may behave unexpectedly otherwise. Note: This does not affect
     * audio playback speed. Use `AudioServer.playback_speed_scale` to adjust audio playback speed
     * independently of `Engine.time_scale`. Note: This does not automatically adjust
     * `physics_ticks_per_second`. With values above `1.0` physics simulation may become less precise,
     * as each physics tick will stretch over a larger period of engine time. If you're modifying
     * `Engine.time_scale` to speed up simulation by a large factor, consider also increasing
     * `physics_ticks_per_second` to make the simulation more reliable.
     *
     * Generated from Godot docs: Engine.get_time_scale
     */
    @JvmStatic
    fun getTimeScale(): Double =
        ObjectCalls.ptrcallNoArgsRetDouble(getTimeScaleBind, singleton)

    /**
     * Returns the total number of frames drawn since the engine started. Note: On headless platforms,
     * or if rendering is disabled with `--disable-render-loop` via command line, this method always
     * returns `0`. See also `get_process_frames`.
     *
     * Generated from Godot docs: Engine.get_frames_drawn
     */
    @JvmStatic
    fun getFramesDrawn(): Long =
        ObjectCalls.ptrcallNoArgsRetInt(getFramesDrawnBind, singleton).toLong()

    /**
     * Returns the average frames rendered every second (FPS), also known as the framerate.
     *
     * Generated from Godot docs: Engine.get_frames_per_second
     */
    @JvmStatic
    fun getFramesPerSecond(): Double =
        ObjectCalls.ptrcallNoArgsRetDouble(getFramesPerSecondBind, singleton)

    /**
     * Returns the total number of frames passed since the engine started. This number is increased
     * every physics frame. See also `get_process_frames`. This method can be used to run expensive
     * logic less often without relying on a `Timer`:
     *
     * Generated from Godot docs: Engine.get_physics_frames
     */
    @JvmStatic
    fun getPhysicsFrames(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(getPhysicsFramesBind, singleton)

    /**
     * Returns the total number of frames passed since the engine started. This number is increased
     * every process frame, regardless of whether the render loop is enabled. See also
     * `get_frames_drawn` and `get_physics_frames`. This method can be used to run expensive logic less
     * often without relying on a `Timer`:
     *
     * Generated from Godot docs: Engine.get_process_frames
     */
    @JvmStatic
    fun getProcessFrames(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(getProcessFramesBind, singleton)

    /**
     * Returns the number of available script languages. Use with `get_script_language`.
     *
     * Generated from Godot docs: Engine.get_script_language_count
     */
    @JvmStatic
    fun getScriptLanguageCount(): Long =
        ObjectCalls.ptrcallNoArgsRetInt(getScriptLanguageCountBind, singleton).toLong()

    /**
     * Returns an instance of a `ScriptLanguage` with the given `index`.
     *
     * Generated from Godot docs: Engine.get_script_language
     */
    @JvmStatic
    fun getScriptLanguage(index: Long): MemorySegment =
        ObjectCalls.ptrcallWithIntArgRetObject(getScriptLanguageBind, singleton, index.toInt())

    /**
     * Returns the name of the CPU architecture the Godot binary was built for. Possible return values
     * include `"x86_64"`, `"x86_32"`, `"arm64"`, `"arm32"`, `"rv64"`, `"ppc64"`, `"loongarch64"`,
     * `"wasm64"`, and `"wasm32"`. To detect whether the current build is 64-bit, or the type of
     * architecture, don't use the architecture name. Instead, use `OS.has_feature` to check for the
     * `"64"` feature tag, or tags such as `"x86"` or `"arm"`. See the Feature Tags
     * ($DOCS_URL/tutorials/export/feature_tags.html) documentation for more details. Note: This method
     * does not return the name of the system's CPU architecture (like `OS.get_processor_name`). For
     * example, when running an `x86_32` Godot binary on an `x86_64` system, the returned value will
     * still be `"x86_32"`.
     *
     * Generated from Godot docs: Engine.get_architecture_name
     */
    @JvmStatic
    fun getArchitectureName(): String =
        ObjectCalls.ptrcallNoArgsRetString(getArchitectureNameBind, singleton)

    /**
     * Returns the instance of the `MainLoop`. This is usually the main `SceneTree` and is the same as
     * `Node.get_tree`. Note: The type instantiated as the main loop can changed with
     * `ProjectSettings.application/run/main_loop_type`.
     *
     * Generated from Godot docs: Engine.get_main_loop
     */
    @JvmStatic
    fun getMainLoop(): MemorySegment =
        ObjectCalls.ptrcallNoArgsRetObject(getMainLoopBind, singleton)

    /**
     * Returns the full Godot license text.
     *
     * Generated from Godot docs: Engine.get_license_text
     */
    @JvmStatic
    fun getLicenseText(): String =
        ObjectCalls.ptrcallNoArgsRetString(getLicenseTextBind, singleton)

    /**
     * Returns the current engine version information as a `Dictionary` containing the following
     * entries: - `major` - Major version number as an int; - `minor` - Minor version number as an int;
     * - `patch` - Patch version number as an int; - `hex` - Full version encoded as a hexadecimal int
     * with one byte (2 hex digits) per number (see example below); - `status` - Status (such as
     * "beta", "rc1", "rc2", "stable", etc.) as a String; - `build` - Build name (e.g. "custom_build")
     * as a String; - `hash` - Full Git commit hash as a String; - `timestamp` - Holds the Git commit
     * date UNIX timestamp in seconds as an int, or `0` if unavailable; - `string` - `major`, `minor`,
     * `patch`, `status`, and `build` in a single String. The `hex` value is encoded as follows, from
     * left to right: one byte for the major, one byte for the minor, one byte for the patch version.
     * For example, "3.1.12" would be `0x03010C`. Note: The `hex` value is still an `int` internally,
     * and printing it will give you its decimal representation, which is not particularly meaningful.
     * Use hexadecimal literals for quick version comparisons from code:
     *
     * Generated from Godot docs: Engine.get_version_info
     */
    @JvmStatic
    fun getVersionInfo(): Map<String, Any?> =
        ObjectCalls.ptrcallNoArgsRetDictionary(getVersionInfoBind, singleton)

    /**
     * Returns the engine author information as a `Dictionary`, where each entry is an `Array` of
     * strings with the names of notable contributors to the Godot Engine: `lead_developers`,
     * `founders`, `project_managers`, and `developers`.
     *
     * Generated from Godot docs: Engine.get_author_info
     */
    @JvmStatic
    fun getAuthorInfo(): Map<String, Any?> =
        ObjectCalls.ptrcallNoArgsRetDictionary(getAuthorInfoBind, singleton)

    /**
     * Returns a `Dictionary` of categorized donor names. Each entry is an `Array` of strings:
     * {`platinum_sponsors`, `gold_sponsors`, `silver_sponsors`, `bronze_sponsors`, `mini_sponsors`,
     * `gold_donors`, `silver_donors`, `bronze_donors`}
     *
     * Generated from Godot docs: Engine.get_donor_info
     */
    @JvmStatic
    fun getDonorInfo(): Map<String, Any?> =
        ObjectCalls.ptrcallNoArgsRetDictionary(getDonorInfoBind, singleton)

    /**
     * Returns a `Dictionary` of licenses used by Godot and included third party components. Each entry
     * is a license name (such as "Expat
     * (https://en.wikipedia.org/wiki/MIT_License#Ambiguity_and_variants)") and its associated text.
     *
     * Generated from Godot docs: Engine.get_license_info
     */
    @JvmStatic
    fun getLicenseInfo(): Map<String, Any?> =
        ObjectCalls.ptrcallNoArgsRetDictionary(getLicenseInfoBind, singleton)

    /**
     * Returns an `Array` of dictionaries with copyright information for every component of Godot's
     * source code. Every `Dictionary` contains a `name` identifier, and a `parts` array of
     * dictionaries. It describes the component in detail with the following entries: - `files` -
     * `Array` of file paths from the source code affected by this component; - `copyright` - `Array`
     * of owners of this component; - `license` - The license applied to this component (such as "Expat
     * (https://en.wikipedia.org/wiki/MIT_License#Ambiguity_and_variants)" or "CC-BY-4.0
     * (https://creativecommons.org/licenses/by/4.0/)").
     *
     * Generated from Godot docs: Engine.get_copyright_info
     */
    @JvmStatic
    fun getCopyrightInfo(): List<Map<String, Any?>> =
        ObjectCalls.ptrcallNoArgsRetDictionaryList(getCopyrightInfoBind, singleton)

    /**
     * Returns `true` if the engine is inside the fixed physics process step of the main loop.
     *
     * Generated from Godot docs: Engine.is_in_physics_frame
     */
    @JvmStatic
    fun isInPhysicsFrame(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isInPhysicsFrameBind, singleton)

    /**
     * Returns `true` if a singleton with the given `name` exists in the global scope. See also
     * `get_singleton`.
     *
     * Generated from Godot docs: Engine.has_singleton
     */
    @JvmStatic
    fun hasSingleton(name: String): Boolean =
        ObjectCalls.ptrcallWithStringNameArgRetBool(hasSingletonBind, singleton, name)

    /**
     * Returns the global singleton with the given `name`, or `null` if it does not exist. Often used
     * for plugins. See also `has_singleton` and `get_singleton_list`. Note: Global singletons are not
     * the same as autoloaded nodes, which are configurable in the project settings.
     *
     * Generated from Godot docs: Engine.get_singleton
     */
    @JvmStatic
    fun getSingleton(name: String): MemorySegment =
        ObjectCalls.ptrcallWithStringNameArgRetObject(getSingletonBind, singleton, name)

    /**
     * Returns a list of names of all available global singletons. See also `get_singleton`.
     *
     * Generated from Godot docs: Engine.get_singleton_list
     */
    @JvmStatic
    fun getSingletonList(): List<String> =
        ObjectCalls.ptrcallNoArgsRetPackedStringList(getSingletonListBind, singleton)

    /**
     * Registers the given `Object` `instance` as a singleton, available globally under `name`. Useful
     * for plugins.
     *
     * Generated from Godot docs: Engine.register_singleton
     */
    @JvmStatic
    fun registerSingleton(name: String, objectArg: MemorySegment) {
        if (objectArg.address() != 0L && GodotObject(objectArg).isClass("RefCounted")) {
            error("Engine.registerSingleton does not accept RefCounted instances; use an Object-derived singleton")
        }
        ObjectCalls.ptrcallWithStringNameAndObjectArg(registerSingletonBind, singleton, name, objectArg)
    }

    /**
     * Removes the singleton registered under `name`. The singleton object is not freed. Only works
     * with user-defined singletons registered with `register_singleton`.
     *
     * Generated from Godot docs: Engine.unregister_singleton
     */
    @JvmStatic
    fun unregisterSingleton(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(unregisterSingletonBind, singleton, name)
    }

    /**
     * Registers a `ScriptLanguage` instance to be available with `ScriptServer`. Returns: - `OK` on
     * success; - `ERR_UNAVAILABLE` if `ScriptServer` has reached the limit and cannot register any new
     * language; - `ERR_ALREADY_EXISTS` if `ScriptServer` already contains a language with similar
     * extension/name/type.
     *
     * Generated from Godot docs: Engine.register_script_language
     */
    @JvmStatic
    fun registerScriptLanguage(scriptLanguageObject: MemorySegment): Long =
        ObjectCalls.ptrcallWithObjectArgsRetLong(
            registerScriptLanguageBind,
            singleton,
            listOf(scriptLanguageObject),
        )

    /**
     * Unregisters the `ScriptLanguage` instance from `ScriptServer`. Returns: - `OK` on success; -
     * `ERR_DOES_NOT_EXIST` if the language is not registered in `ScriptServer`.
     *
     * Generated from Godot docs: Engine.unregister_script_language
     */
    @JvmStatic
    fun unregisterScriptLanguage(scriptLanguageObject: MemorySegment): Long =
        ObjectCalls.ptrcallWithObjectArgsRetLong(
            unregisterScriptLanguageBind,
            singleton,
            listOf(scriptLanguageObject),
        )

    /**
     * Captures and returns backtraces from all registered script languages. By default, the returned
     * `ScriptBacktrace` will only contain stack frames in editor builds and debug builds. To enable
     * them for release builds as well, you need to enable
     * `ProjectSettings.debug/settings/gdscript/always_track_call_stacks`. If `include_variables` is
     * `true`, the backtrace will also include the names and values of any global variables (e.g.
     * autoload singletons) at the point of the capture, as well as local variables and class member
     * variables at each stack frame. This will however will only be respected when running the game
     * with a debugger attached, like when running the game from the editor. To enable it for export
     * builds as well, you need to enable
     * `ProjectSettings.debug/settings/gdscript/always_track_local_variables`. Warning: When
     * `include_variables` is `true`, any captured variables can potentially (e.g. with GDScript
     * backtraces) be their actual values, including any object references. This means that storing
     * such a `ScriptBacktrace` will prevent those objects from being deallocated, so it's generally
     * recommended not to do so.
     *
     * Generated from Godot docs: Engine.capture_script_backtraces
     */
    @JvmStatic
    fun captureScriptBacktraces(includeVariables: Boolean = false): List<ScriptBacktrace> =
        ObjectCalls.ptrcallWithBoolArgRetTypedObjectList(
            captureScriptBacktracesBind,
            singleton,
            includeVariables,
            ScriptBacktrace::fromHandle,
        )

    /**
     * Returns `true` if the script is currently running inside the editor, otherwise returns `false`.
     * This is useful for `@tool` scripts to conditionally draw editor helpers, or prevent accidentally
     * running "game" code that would affect the scene state while in the editor:
     *
     * Generated from Godot docs: Engine.is_editor_hint
     */
    @JvmStatic
    fun isEditorHint(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isEditorHintBind, singleton)

    /**
     * Returns `true` if the engine is running embedded in the editor. This is useful to prevent
     * attempting to update window mode or window flags that are not supported when running the project
     * embedded in the editor.
     *
     * Generated from Godot docs: Engine.is_embedded_in_editor
     */
    @JvmStatic
    fun isEmbeddedInEditor(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isEmbeddedInEditorBind, singleton)

    /**
     * Returns the path to the `MovieWriter`'s output file, or an empty string if the engine wasn't
     * started in Movie Maker mode. The default path can be changed in
     * `ProjectSettings.editor/movie_writer/movie_file`.
     *
     * Generated from Godot docs: Engine.get_write_movie_path
     */
    @JvmStatic
    fun getWriteMoviePath(): String =
        ObjectCalls.ptrcallNoArgsRetString(getWriteMoviePathBind, singleton)

    /**
     * If `false`, stops printing messages (for example using `@GlobalScope.print`) to the console, log
     * files, and editor Output log. This property is equivalent to the
     * `ProjectSettings.application/run/disable_stdout` project setting. Note: This does not stop
     * printing errors or warnings produced by scripts to the console or log files, for more details
     * see `print_error_messages`.
     *
     * Generated from Godot docs: Engine.set_print_to_stdout
     */
    @JvmStatic
    fun setPrintToStdout(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPrintToStdoutBind, singleton, enabled)
    }

    /**
     * If `false`, stops printing messages (for example using `@GlobalScope.print`) to the console, log
     * files, and editor Output log. This property is equivalent to the
     * `ProjectSettings.application/run/disable_stdout` project setting. Note: This does not stop
     * printing errors or warnings produced by scripts to the console or log files, for more details
     * see `print_error_messages`.
     *
     * Generated from Godot docs: Engine.is_printing_to_stdout
     */
    @JvmStatic
    fun isPrintingToStdout(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isPrintingToStdoutBind, singleton)

    /**
     * If `false`, stops printing error and warning messages to the console and editor Output log. This
     * can be used to hide error and warning messages during unit test suite runs. This property is
     * equivalent to the `ProjectSettings.application/run/disable_stderr` project setting. Note: This
     * property does not impact the editor's Errors tab when running a project from the editor.
     * Warning: If set to `false` anywhere in the project, important error messages may be hidden even
     * if they are emitted from other scripts. In a `@tool` script, this will also impact the editor
     * itself. Do not report bugs before ensuring error messages are enabled (as they are by default).
     *
     * Generated from Godot docs: Engine.set_print_error_messages
     */
    @JvmStatic
    fun setPrintErrorMessages(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPrintErrorMessagesBind, singleton, enabled)
    }

    /**
     * If `false`, stops printing error and warning messages to the console and editor Output log. This
     * can be used to hide error and warning messages during unit test suite runs. This property is
     * equivalent to the `ProjectSettings.application/run/disable_stderr` project setting. Note: This
     * property does not impact the editor's Errors tab when running a project from the editor.
     * Warning: If set to `false` anywhere in the project, important error messages may be hidden even
     * if they are emitted from other scripts. In a `@tool` script, this will also impact the editor
     * itself. Do not report bugs before ensuring error messages are enabled (as they are by default).
     *
     * Generated from Godot docs: Engine.is_printing_error_messages
     */
    @JvmStatic
    fun isPrintingErrorMessages(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isPrintingErrorMessagesBind, singleton)

}
