package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: WebRTCPeerConnection
 */
open class WebRTCPeerConnection(handle: GodotHandle) : RefCounted(handle) {
    fun initialize(configuration: Map<String, Any?> = emptyMap()): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithDictionaryArgRetLong(Binds.initializeBind, segment, configuration))
    }

    fun createDataChannel(label: String, options: Map<String, Any?> = emptyMap()): WebRTCDataChannel? {
        checkOpen()
        return WebRTCDataChannel.wrapOwned(ObjectCalls.ptrcallWithStringAndDictionaryArgRetObject(Binds.createDataChannelBind, segment, label, options))
    }

    fun createOffer(): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallNoArgsRetLong(Binds.createOfferBind, segment))
    }

    fun setLocalDescription(type: String, sdp: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithTwoStringArgsRetLong(Binds.setLocalDescriptionBind, segment, type, sdp))
    }

    fun setRemoteDescription(type: String, sdp: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithTwoStringArgsRetLong(Binds.setRemoteDescriptionBind, segment, type, sdp))
    }

    fun addIceCandidate(media: String, index: Int, name: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringIntStringArgsRetLong(Binds.addIceCandidateBind, segment, media, index, name))
    }

    fun poll(): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallNoArgsRetLong(Binds.pollBind, segment))
    }

    fun closeConnection() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.closeConnectionBind, segment)
    }

    fun getConnectionState(): WebRTCPeerConnection.ConnectionState {
        checkOpen()
        return WebRTCPeerConnection.ConnectionState(ObjectCalls.ptrcallNoArgsRetLong(Binds.getConnectionStateBind, segment))
    }

    fun getGatheringState(): WebRTCPeerConnection.GatheringState {
        checkOpen()
        return WebRTCPeerConnection.GatheringState(ObjectCalls.ptrcallNoArgsRetLong(Binds.getGatheringStateBind, segment))
    }

    fun getSignalingState(): WebRTCPeerConnection.SignalingState {
        checkOpen()
        return WebRTCPeerConnection.SignalingState(ObjectCalls.ptrcallNoArgsRetLong(Binds.getSignalingStateBind, segment))
    }

    /** Signal `session_description_created(type: String, sdp: String)`; see [TypedSignal]. */
    val sessionDescriptionCreated: Signal2<String, String>
        @JvmName("sessionDescriptionCreatedTypedSignal")
        get() = Signal2(this, "session_description_created", SignalArgType.STRING, SignalArgType.STRING)

    /** Signal `ice_candidate_created(media: String, index: int, name: String)`; see [TypedSignal]. */
    val iceCandidateCreated: Signal3<String, Long, String>
        @JvmName("iceCandidateCreatedTypedSignal")
        get() = Signal3(this, "ice_candidate_created", SignalArgType.STRING, SignalArgType.LONG, SignalArgType.STRING)

    /** Signal `data_channel_received(channel: WebRTCDataChannel)`; see [TypedSignal]. */
    val dataChannelReceived: Signal1<WebRTCDataChannel>
        @JvmName("dataChannelReceivedTypedSignal")
        get() = Signal1(this, "data_channel_received", SignalArgType.objectOf("WebRTCDataChannel") { WebRTCDataChannel(it) })

    object Signals {
        const val sessionDescriptionCreated: String = "session_description_created"
        const val iceCandidateCreated: String = "ice_candidate_created"
        const val dataChannelReceived: String = "data_channel_received"
    }

    @JvmInline
    value class ConnectionState(override val value: Long) : GodotEnumValue {
        companion object {
            val NEW: ConnectionState get() = ConnectionState(0L)
            val CONNECTING: ConnectionState get() = ConnectionState(1L)
            val CONNECTED: ConnectionState get() = ConnectionState(2L)
            val DISCONNECTED: ConnectionState get() = ConnectionState(3L)
            val FAILED: ConnectionState get() = ConnectionState(4L)
            val CLOSED: ConnectionState get() = ConnectionState(5L)
        }
    }

    @JvmInline
    value class GatheringState(override val value: Long) : GodotEnumValue {
        companion object {
            val NEW: GatheringState get() = GatheringState(0L)
            val GATHERING: GatheringState get() = GatheringState(1L)
            val COMPLETE: GatheringState get() = GatheringState(2L)
        }
    }

    @JvmInline
    value class SignalingState(override val value: Long) : GodotEnumValue {
        companion object {
            val STABLE: SignalingState get() = SignalingState(0L)
            val HAVE_LOCAL_OFFER: SignalingState get() = SignalingState(1L)
            val HAVE_REMOTE_OFFER: SignalingState get() = SignalingState(2L)
            val HAVE_LOCAL_PRANSWER: SignalingState get() = SignalingState(3L)
            val HAVE_REMOTE_PRANSWER: SignalingState get() = SignalingState(4L)
            val CLOSED: SignalingState get() = SignalingState(5L)
        }
    }

    companion object {
        fun setDefaultExtension(extensionClass: String) {
            ObjectCalls.ptrcallWithStringNameArg(Binds.setDefaultExtensionBind, NULL_SEGMENT, extensionClass)
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): WebRTCPeerConnection? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): WebRTCPeerConnection? =
            if (handle.address() == 0L) null else RefCounted.owned(WebRTCPeerConnection(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): WebRTCPeerConnection? =
            if (handle.address() == 0L) null else WebRTCPeerConnection(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_DEFAULT_EXTENSION_HASH = 3304788590L
        @JvmField
        val setDefaultExtensionBind =
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "set_default_extension", SET_DEFAULT_EXTENSION_HASH)

        private const val INITIALIZE_HASH = 2625064318L
        @JvmField
        val initializeBind =
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "initialize", INITIALIZE_HASH)

        private const val CREATE_DATA_CHANNEL_HASH = 1288557393L
        @JvmField
        val createDataChannelBind =
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "create_data_channel", CREATE_DATA_CHANNEL_HASH)

        private const val CREATE_OFFER_HASH = 166280745L
        @JvmField
        val createOfferBind =
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "create_offer", CREATE_OFFER_HASH)

        private const val SET_LOCAL_DESCRIPTION_HASH = 852856452L
        @JvmField
        val setLocalDescriptionBind =
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "set_local_description", SET_LOCAL_DESCRIPTION_HASH)

        private const val SET_REMOTE_DESCRIPTION_HASH = 852856452L
        @JvmField
        val setRemoteDescriptionBind =
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "set_remote_description", SET_REMOTE_DESCRIPTION_HASH)

        private const val ADD_ICE_CANDIDATE_HASH = 3958950400L
        @JvmField
        val addIceCandidateBind =
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "add_ice_candidate", ADD_ICE_CANDIDATE_HASH)

        private const val POLL_HASH = 166280745L
        @JvmField
        val pollBind =
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "poll", POLL_HASH)

        private const val CLOSE_HASH = 3218959716L
        @JvmField
        val closeConnectionBind =
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "close", CLOSE_HASH)

        private const val GET_CONNECTION_STATE_HASH = 2275710506L
        @JvmField
        val getConnectionStateBind =
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "get_connection_state", GET_CONNECTION_STATE_HASH)

        private const val GET_GATHERING_STATE_HASH = 4262591401L
        @JvmField
        val getGatheringStateBind =
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "get_gathering_state", GET_GATHERING_STATE_HASH)

        private const val GET_SIGNALING_STATE_HASH = 3342956226L
        @JvmField
        val getSignalingStateBind =
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "get_signaling_state", GET_SIGNALING_STATE_HASH)
    }
}
