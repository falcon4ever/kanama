package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
import net.multigesture.kanama.types.Vector2i

/**
 * Result returned by `FuzzySearch` containing match information.
 *
 * Generated from Godot docs: FuzzySearchMatch
 */
class FuzzySearchMatch(handle: GodotHandle) : RefCounted(handle) {
    var target: String
        @JvmName("targetProperty")
        get() = getTarget()
        @JvmName("setTargetProperty")
        set(value) = setTarget(value)

    var score: Int
        @JvmName("scoreProperty")
        get() = getScore()
        @JvmName("setScoreProperty")
        set(value) = setScore(value)

    var originalIndex: Int
        @JvmName("originalIndexProperty")
        get() = getOriginalIndex()
        @JvmName("setOriginalIndexProperty")
        set(value) = setOriginalIndex(value)

    /**
     * Target string which was matched against.
     *
     * Generated from Godot docs: FuzzySearchMatch.set_target
     */
    fun setTarget(target: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setTargetBind, segment, target)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Target string which was matched against.
     *
     * Generated from Godot docs: FuzzySearchMatch.get_target
     */
    fun getTarget(): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetString(Binds.getTargetBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Match score determined by `FuzzySearch`. Scores are not normalized and generally should not be
     * directly compared between different queries.
     *
     * Generated from Godot docs: FuzzySearchMatch.set_score
     */
    fun setScore(score: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setScoreBind, segment, score)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Match score determined by `FuzzySearch`. Scores are not normalized and generally should not be
     * directly compared between different queries.
     *
     * Generated from Godot docs: FuzzySearchMatch.get_score
     */
    fun getScore(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getScoreBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The original index of `target` in the provided array of targets when using
     * `FuzzySearch.search_all` otherwise `-1`.
     *
     * Generated from Godot docs: FuzzySearchMatch.set_original_index
     */
    fun setOriginalIndex(originalIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setOriginalIndexBind, segment, originalIndex)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The original index of `target` in the provided array of targets when using
     * `FuzzySearch.search_all` otherwise `-1`.
     *
     * Generated from Godot docs: FuzzySearchMatch.get_original_index
     */
    fun getOriginalIndex(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getOriginalIndexBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns each matched substring interval of `target`. The `Vector2i.x` component is the start of
     * the interval and the `Vector2i.y` component is the length, corresponding to the arguments for
     * `String.substr`.
     *
     * Generated from Godot docs: FuzzySearchMatch.get_matched_substrings
     */
    fun getMatchedSubstrings(): List<Vector2i> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector2iList(Binds.getMatchedSubstringsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): FuzzySearchMatch? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): FuzzySearchMatch? =
            if (handle.address() == 0L) null else RefCounted.owned(FuzzySearchMatch(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): FuzzySearchMatch? =
            if (handle.address() == 0L) null else FuzzySearchMatch(GodotHandle(handle))

        /** A new FuzzySearchMatch, GDScript's `FuzzySearchMatch.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): FuzzySearchMatch =
            RefCounted.owned(FuzzySearchMatch(GodotHandle(requireConstructed(ObjectCalls.constructObject("FuzzySearchMatch"), "FuzzySearchMatch"))))
    }

    private object Binds {
        private const val SET_TARGET_HASH = 83702148L
        @JvmField
        val setTargetBind =
            ObjectCalls.getMethodBind("FuzzySearchMatch", "set_target", SET_TARGET_HASH)

        private const val GET_TARGET_HASH = 201670096L
        @JvmField
        val getTargetBind =
            ObjectCalls.getMethodBind("FuzzySearchMatch", "get_target", GET_TARGET_HASH)

        private const val SET_SCORE_HASH = 1286410249L
        @JvmField
        val setScoreBind =
            ObjectCalls.getMethodBind("FuzzySearchMatch", "set_score", SET_SCORE_HASH)

        private const val GET_SCORE_HASH = 3905245786L
        @JvmField
        val getScoreBind =
            ObjectCalls.getMethodBind("FuzzySearchMatch", "get_score", GET_SCORE_HASH)

        private const val SET_ORIGINAL_INDEX_HASH = 1286410249L
        @JvmField
        val setOriginalIndexBind =
            ObjectCalls.getMethodBind("FuzzySearchMatch", "set_original_index", SET_ORIGINAL_INDEX_HASH)

        private const val GET_ORIGINAL_INDEX_HASH = 3905245786L
        @JvmField
        val getOriginalIndexBind =
            ObjectCalls.getMethodBind("FuzzySearchMatch", "get_original_index", GET_ORIGINAL_INDEX_HASH)

        private const val GET_MATCHED_SUBSTRINGS_HASH = 3995934104L
        @JvmField
        val getMatchedSubstringsBind =
            ObjectCalls.getMethodBind("FuzzySearchMatch", "get_matched_substrings", GET_MATCHED_SUBSTRINGS_HASH)
    }
}
