package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: WebRTCPeerConnection
 */
open class WebRTCPeerConnection(handle: MemorySegment) : RefCounted(handle) {
    fun initialize(configuration: Map<String, Any?> = emptyMap()): Long {
        return ObjectCalls.ptrcallWithDictionaryArgRetLong(initializeBind, handle, configuration)
    }

    fun createDataChannel(label: String, options: Map<String, Any?> = emptyMap()): WebRTCDataChannel? {
        return WebRTCDataChannel.wrap(ObjectCalls.ptrcallWithStringAndDictionaryArgRetObject(createDataChannelBind, handle, label, options))
    }

    fun createOffer(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(createOfferBind, handle)
    }

    fun setLocalDescription(type: String, sdp: String): Long {
        return ObjectCalls.ptrcallWithTwoStringArgsRetLong(setLocalDescriptionBind, handle, type, sdp)
    }

    fun setRemoteDescription(type: String, sdp: String): Long {
        return ObjectCalls.ptrcallWithTwoStringArgsRetLong(setRemoteDescriptionBind, handle, type, sdp)
    }

    fun addIceCandidate(media: String, index: Int, name: String): Long {
        return ObjectCalls.ptrcallWithStringIntStringArgsRetLong(addIceCandidateBind, handle, media, index, name)
    }

    fun poll(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(pollBind, handle)
    }

    fun closeConnection() {
        ObjectCalls.ptrcallNoArgs(closeConnectionBind, handle)
    }

    fun getConnectionState(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getConnectionStateBind, handle)
    }

    fun getGatheringState(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getGatheringStateBind, handle)
    }

    fun getSignalingState(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSignalingStateBind, handle)
    }

    object Signals {
        const val sessionDescriptionCreated: String = "session_description_created"
        const val iceCandidateCreated: String = "ice_candidate_created"
        const val dataChannelReceived: String = "data_channel_received"
    }

    companion object {
        fun setDefaultExtension(extensionClass: String) {
            ObjectCalls.ptrcallWithStringNameArg(setDefaultExtensionBind, MemorySegment.NULL, extensionClass)
        }

        const val STATE_NEW: Long = 0L
        const val STATE_CONNECTING: Long = 1L
        const val STATE_CONNECTED: Long = 2L
        const val STATE_DISCONNECTED: Long = 3L
        const val STATE_FAILED: Long = 4L
        const val STATE_CLOSED: Long = 5L
        const val GATHERING_STATE_NEW: Long = 0L
        const val GATHERING_STATE_GATHERING: Long = 1L
        const val GATHERING_STATE_COMPLETE: Long = 2L
        const val SIGNALING_STATE_STABLE: Long = 0L
        const val SIGNALING_STATE_HAVE_LOCAL_OFFER: Long = 1L
        const val SIGNALING_STATE_HAVE_REMOTE_OFFER: Long = 2L
        const val SIGNALING_STATE_HAVE_LOCAL_PRANSWER: Long = 3L
        const val SIGNALING_STATE_HAVE_REMOTE_PRANSWER: Long = 4L
        const val SIGNALING_STATE_CLOSED: Long = 5L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): WebRTCPeerConnection? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): WebRTCPeerConnection? =
            if (handle.address() == 0L) null else WebRTCPeerConnection(handle)

        private const val SET_DEFAULT_EXTENSION_HASH = 3304788590L
        private val setDefaultExtensionBind by lazy {
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "set_default_extension", SET_DEFAULT_EXTENSION_HASH)
        }

        private const val INITIALIZE_HASH = 2625064318L
        private val initializeBind by lazy {
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "initialize", INITIALIZE_HASH)
        }

        private const val CREATE_DATA_CHANNEL_HASH = 1288557393L
        private val createDataChannelBind by lazy {
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "create_data_channel", CREATE_DATA_CHANNEL_HASH)
        }

        private const val CREATE_OFFER_HASH = 166280745L
        private val createOfferBind by lazy {
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "create_offer", CREATE_OFFER_HASH)
        }

        private const val SET_LOCAL_DESCRIPTION_HASH = 852856452L
        private val setLocalDescriptionBind by lazy {
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "set_local_description", SET_LOCAL_DESCRIPTION_HASH)
        }

        private const val SET_REMOTE_DESCRIPTION_HASH = 852856452L
        private val setRemoteDescriptionBind by lazy {
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "set_remote_description", SET_REMOTE_DESCRIPTION_HASH)
        }

        private const val ADD_ICE_CANDIDATE_HASH = 3958950400L
        private val addIceCandidateBind by lazy {
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "add_ice_candidate", ADD_ICE_CANDIDATE_HASH)
        }

        private const val POLL_HASH = 166280745L
        private val pollBind by lazy {
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "poll", POLL_HASH)
        }

        private const val CLOSE_HASH = 3218959716L
        private val closeConnectionBind by lazy {
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "close", CLOSE_HASH)
        }

        private const val GET_CONNECTION_STATE_HASH = 2275710506L
        private val getConnectionStateBind by lazy {
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "get_connection_state", GET_CONNECTION_STATE_HASH)
        }

        private const val GET_GATHERING_STATE_HASH = 4262591401L
        private val getGatheringStateBind by lazy {
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "get_gathering_state", GET_GATHERING_STATE_HASH)
        }

        private const val GET_SIGNALING_STATE_HASH = 3342956226L
        private val getSignalingStateBind by lazy {
            ObjectCalls.getMethodBind("WebRTCPeerConnection", "get_signaling_state", GET_SIGNALING_STATE_HASH)
        }
    }
}
