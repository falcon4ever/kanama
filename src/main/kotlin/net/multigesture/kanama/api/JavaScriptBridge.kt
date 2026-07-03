package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Singleton that connects the engine with the browser's JavaScript context in Web export.
 *
 * Generated from Godot docs: JavaScriptBridge
 */
object JavaScriptBridge {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("JavaScriptBridge")
    }

    @JvmStatic
    /**
     * Execute the string `code` as JavaScript code within the browser window. This is a call to the
     * actual global JavaScript function `eval()`. If `use_global_execution_context` is `true`, the
     * code will be evaluated in the global execution context. Otherwise, it is evaluated in the
     * execution context of a function within the engine's runtime environment.
     *
     * Generated from Godot docs: JavaScriptBridge.eval
     */
    fun eval(code: String, useGlobalExecutionContext: Boolean = false): Any? {
        return ObjectCalls.ptrcallWithStringAndBoolArgRetVariantScalar(evalBind, singleton, code, useGlobalExecutionContext)
    }

    /**
     * Returns an interface to a JavaScript object that can be used by scripts. The `interface` must be
     * a valid property of the JavaScript `window`. The callback must accept a single `Array` argument,
     * which will contain the JavaScript `arguments`. See `JavaScriptObject` for usage.
     *
     * Generated from Godot docs: JavaScriptBridge.get_interface
     */
    @JvmStatic
    fun getInterface(interfaceValue: String): JavaScriptObject? {
        return JavaScriptObject.wrap(ObjectCalls.ptrcallWithStringArgRetObject(getInterfaceBind, singleton, interfaceValue))
    }

    /**
     * Creates a reference to a `Callable` that can be used as a callback by JavaScript. The reference
     * must be kept until the callback happens, or it won't be called at all. See `JavaScriptObject`
     * for usage. Note: The callback function must take exactly one `Array` argument, which is going to
     * be the JavaScript arguments object
     * (https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions/arguments)
     * converted to an array.
     *
     * Generated from Godot docs: JavaScriptBridge.create_callback
     */
    @JvmStatic
    fun createCallback(callable: GodotCallable): JavaScriptObject? {
        return JavaScriptObject.wrap(ObjectCalls.ptrcallWithCallableArgRetObject(createCallbackBind, singleton, callable.target.handle, callable.method))
    }

    /**
     * Returns `true` if the given `javascript_object` is of type ArrayBuffer
     * (https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/ArrayBuffer),
     * DataView
     * (https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/DataView), or
     * one of the many typed array objects
     * (https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/TypedArray).
     *
     * Generated from Godot docs: JavaScriptBridge.is_js_buffer
     */
    @JvmStatic
    fun isJsBuffer(javascriptObject: JavaScriptObject?): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(isJsBufferBind, singleton, javascriptObject?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns a copy of `javascript_buffer`'s contents as a `PackedByteArray`. See also
     * `is_js_buffer`.
     *
     * Generated from Godot docs: JavaScriptBridge.js_buffer_to_packed_byte_array
     */
    @JvmStatic
    fun jsBufferToPackedByteArray(javascriptBuffer: JavaScriptObject?): ByteArray {
        return ObjectCalls.ptrcallWithObjectArgRetByteArray(jsBufferToPackedByteArrayBind, singleton, javascriptBuffer?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Creates a new JavaScript object using the `new` constructor. The `object` must a valid property
     * of the JavaScript `window`. See `JavaScriptObject` for usage.
     *
     * Generated from Godot docs: JavaScriptBridge.create_object
     */
    @JvmStatic
    fun createObject(objectValue: String, vararg extraArgs: Any?): Any? {
        return ObjectCalls.callWithVariantArgs(createObjectBind, singleton, listOf(objectValue, *extraArgs))
    }

    /**
     * Prompts the user to download a file containing the specified `buffer`. The file will have the
     * given `name` and `mime` type. Note: The browser may override the MIME type
     * (https://en.wikipedia.org/wiki/Media_type) provided based on the file `name`'s extension. Note:
     * Browsers might block the download if `download_buffer` is not being called from a user
     * interaction (e.g. button click). Note: Browsers might ask the user for permission or block the
     * download if multiple download requests are made in a quick succession.
     *
     * Generated from Godot docs: JavaScriptBridge.download_buffer
     */
    @JvmStatic
    fun downloadBuffer(buffer: ByteArray, name: String, mime: String = "application/octet-stream") {
        ObjectCalls.ptrcallWithByteArrayTwoStringArgs(downloadBufferBind, singleton, buffer, name, mime)
    }

    /**
     * Returns `true` if a new version of the progressive web app is waiting to be activated. Note:
     * Only relevant when exported as a Progressive Web App.
     *
     * Generated from Godot docs: JavaScriptBridge.pwa_needs_update
     */
    @JvmStatic
    fun pwaNeedsUpdate(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(pwaNeedsUpdateBind, singleton)
    }

    /**
     * Performs the live update of the progressive web app. Forcing the new version to be installed and
     * the page to be reloaded. Note: Your application will be reloaded in all browser tabs. Note: Only
     * relevant when exported as a Progressive Web App and `pwa_needs_update` returns `true`.
     *
     * Generated from Godot docs: JavaScriptBridge.pwa_update
     */
    @JvmStatic
    fun pwaUpdate(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(pwaUpdateBind, singleton)
    }

    /**
     * Force synchronization of the persistent file system (when enabled). Note: This is only useful
     * for modules or extensions that can't use `FileAccess` to write files.
     *
     * Generated from Godot docs: JavaScriptBridge.force_fs_sync
     */
    @JvmStatic
    fun forceFsSync() {
        ObjectCalls.ptrcallNoArgs(forceFsSyncBind, singleton)
    }

    object Signals {
        const val pwaUpdateAvailable: String = "pwa_update_available"
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): JavaScriptBridge? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): JavaScriptBridge? =
        if (handle.address() == 0L) null else this

    private const val EVAL_HASH = 218087648L
    private val evalBind by lazy {
        ObjectCalls.getMethodBind("JavaScriptBridge", "eval", EVAL_HASH)
    }

    private const val GET_INTERFACE_HASH = 1355533281L
    private val getInterfaceBind by lazy {
        ObjectCalls.getMethodBind("JavaScriptBridge", "get_interface", GET_INTERFACE_HASH)
    }

    private const val CREATE_CALLBACK_HASH = 422818440L
    private val createCallbackBind by lazy {
        ObjectCalls.getMethodBind("JavaScriptBridge", "create_callback", CREATE_CALLBACK_HASH)
    }

    private const val IS_JS_BUFFER_HASH = 821968997L
    private val isJsBufferBind by lazy {
        ObjectCalls.getMethodBind("JavaScriptBridge", "is_js_buffer", IS_JS_BUFFER_HASH)
    }

    private const val JS_BUFFER_TO_PACKED_BYTE_ARRAY_HASH = 64409880L
    private val jsBufferToPackedByteArrayBind by lazy {
        ObjectCalls.getMethodBind("JavaScriptBridge", "js_buffer_to_packed_byte_array", JS_BUFFER_TO_PACKED_BYTE_ARRAY_HASH)
    }

    private const val CREATE_OBJECT_HASH = 3093893586L
    private val createObjectBind by lazy {
        ObjectCalls.getMethodBind("JavaScriptBridge", "create_object", CREATE_OBJECT_HASH)
    }

    private const val DOWNLOAD_BUFFER_HASH = 3352272093L
    private val downloadBufferBind by lazy {
        ObjectCalls.getMethodBind("JavaScriptBridge", "download_buffer", DOWNLOAD_BUFFER_HASH)
    }

    private const val PWA_NEEDS_UPDATE_HASH = 36873697L
    private val pwaNeedsUpdateBind by lazy {
        ObjectCalls.getMethodBind("JavaScriptBridge", "pwa_needs_update", PWA_NEEDS_UPDATE_HASH)
    }

    private const val PWA_UPDATE_HASH = 166280745L
    private val pwaUpdateBind by lazy {
        ObjectCalls.getMethodBind("JavaScriptBridge", "pwa_update", PWA_UPDATE_HASH)
    }

    private const val FORCE_FS_SYNC_HASH = 3218959716L
    private val forceFsSyncBind by lazy {
        ObjectCalls.getMethodBind("JavaScriptBridge", "force_fs_sync", FORCE_FS_SYNC_HASH)
    }
}
