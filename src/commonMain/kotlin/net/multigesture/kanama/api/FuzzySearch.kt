package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Provides fuzzy string searching and matching capabilities.
 *
 * Generated from Godot docs: FuzzySearch
 */
class FuzzySearch(handle: GodotHandle) : RefCounted(handle) {
    var startOffset: Int
        @JvmName("startOffsetProperty")
        get() = getStartOffset()
        @JvmName("setStartOffsetProperty")
        set(value) = setStartOffset(value)

    var maxResults: Int
        @JvmName("maxResultsProperty")
        get() = getMaxResults()
        @JvmName("setMaxResultsProperty")
        set(value) = setMaxResults(value)

    var maxMisses: Int
        @JvmName("maxMissesProperty")
        get() = getMaxMisses()
        @JvmName("setMaxMissesProperty")
        set(value) = setMaxMisses(value)

    var useExactTokens: Boolean
        @JvmName("useExactTokensProperty")
        get() = getUseExactTokens()
        @JvmName("setUseExactTokensProperty")
        set(value) = setUseExactTokens(value)

    var caseSensitive: Boolean
        @JvmName("caseSensitiveProperty")
        get() = getCaseSensitive()
        @JvmName("setCaseSensitiveProperty")
        set(value) = setCaseSensitive(value)

    var filterLowScores: Boolean
        @JvmName("filterLowScoresProperty")
        get() = getFilterLowScores()
        @JvmName("setFilterLowScoresProperty")
        set(value) = setFilterLowScores(value)

    var filterFactor: Double
        @JvmName("filterFactorProperty")
        get() = getFilterFactor()
        @JvmName("setFilterFactorProperty")
        set(value) = setFilterFactor(value)

    var filterCutoff: Double
        @JvmName("filterCutoffProperty")
        get() = getFilterCutoff()
        @JvmName("setFilterCutoffProperty")
        set(value) = setFilterCutoff(value)

    /**
     * Number of leading characters to omit from matching. For example, this can be used to skip a
     * common prefix such as `res://`.
     *
     * Generated from Godot docs: FuzzySearch.set_start_offset
     */
    fun setStartOffset(startOffset: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setStartOffsetBind, segment, startOffset)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Number of leading characters to omit from matching. For example, this can be used to skip a
     * common prefix such as `res://`.
     *
     * Generated from Godot docs: FuzzySearch.get_start_offset
     */
    fun getStartOffset(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getStartOffsetBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Maximum number of results which can be returned by `search_all`.
     *
     * Generated from Godot docs: FuzzySearch.set_max_results
     */
    fun setMaxResults(maxResults: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxResultsBind, segment, maxResults)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Maximum number of results which can be returned by `search_all`.
     *
     * Generated from Godot docs: FuzzySearch.get_max_results
     */
    fun getMaxResults(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxResultsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Maximum number of non-matched characters in the query before skipping a target as non-matching.
     * This option is ignored if `use_exact_tokens` is `true`.
     *
     * Generated from Godot docs: FuzzySearch.set_max_misses
     */
    fun setMaxMisses(maxMisses: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxMissesBind, segment, maxMisses)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Maximum number of non-matched characters in the query before skipping a target as non-matching.
     * This option is ignored if `use_exact_tokens` is `true`.
     *
     * Generated from Godot docs: FuzzySearch.get_max_misses
     */
    fun getMaxMisses(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxMissesBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `true`, only targets which contain each token as a non-overlapping substring are returned.
     * Gaps and missed characters are not considered valid matches, but `case_sensitive` is still used.
     *
     * Generated from Godot docs: FuzzySearch.set_use_exact_tokens
     */
    fun setUseExactTokens(useExactTokens: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseExactTokensBind, segment, useExactTokens)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If `true`, only targets which contain each token as a non-overlapping substring are returned.
     * Gaps and missed characters are not considered valid matches, but `case_sensitive` is still used.
     *
     * Generated from Godot docs: FuzzySearch.get_use_exact_tokens
     */
    fun getUseExactTokens(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.getUseExactTokensBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Whether the query character casing should be matched exactly or not.
     *
     * Generated from Godot docs: FuzzySearch.set_case_sensitive
     */
    fun setCaseSensitive(caseSensitive: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setCaseSensitiveBind, segment, caseSensitive)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Whether the query character casing should be matched exactly or not.
     *
     * Generated from Godot docs: FuzzySearch.get_case_sensitive
     */
    fun getCaseSensitive(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.getCaseSensitiveBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `true`, lower quality matches are not returned by `search_all`. The default filtering
     * behavior is tuned to keep exact matches and reject significantly broken up matches. Setting this
     * to `false` can potentially improve results when searching a small number of items.
     *
     * Generated from Godot docs: FuzzySearch.set_filter_low_scores
     */
    fun setFilterLowScores(filterLowScores: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setFilterLowScoresBind, segment, filterLowScores)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If `true`, lower quality matches are not returned by `search_all`. The default filtering
     * behavior is tuned to keep exact matches and reject significantly broken up matches. Setting this
     * to `false` can potentially improve results when searching a small number of items.
     *
     * Generated from Godot docs: FuzzySearch.get_filter_low_scores
     */
    fun getFilterLowScores(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.getFilterLowScoresBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Biases the filtering cutoff score between the average score and max score. Value should be
     * between `0` and `1`.
     *
     * Generated from Godot docs: FuzzySearch.set_filter_factor
     */
    fun setFilterFactor(filterFactor: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFilterFactorBind, segment, filterFactor)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Biases the filtering cutoff score between the average score and max score. Value should be
     * between `0` and `1`.
     *
     * Generated from Godot docs: FuzzySearch.get_filter_factor
     */
    fun getFilterFactor(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFilterFactorBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Minimum score for filtering results returned by `search_all`.
     *
     * Generated from Godot docs: FuzzySearch.set_filter_cutoff
     */
    fun setFilterCutoff(filterCutoff: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFilterCutoffBind, segment, filterCutoff)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Minimum score for filtering results returned by `search_all`.
     *
     * Generated from Godot docs: FuzzySearch.get_filter_cutoff
     */
    fun getFilterCutoff(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFilterCutoffBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): FuzzySearch? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): FuzzySearch? =
            if (handle.address() == 0L) null else RefCounted.owned(FuzzySearch(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): FuzzySearch? =
            if (handle.address() == 0L) null else FuzzySearch(GodotHandle(handle))

        /** A new FuzzySearch, GDScript's `FuzzySearch.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): FuzzySearch =
            RefCounted.owned(FuzzySearch(GodotHandle(requireConstructed(ObjectCalls.constructObject("FuzzySearch"), "FuzzySearch"))))
    }

    private object Binds {
        private const val SET_START_OFFSET_HASH = 1286410249L
        @JvmField
        val setStartOffsetBind =
            ObjectCalls.getMethodBind("FuzzySearch", "set_start_offset", SET_START_OFFSET_HASH)

        private const val GET_START_OFFSET_HASH = 3905245786L
        @JvmField
        val getStartOffsetBind =
            ObjectCalls.getMethodBind("FuzzySearch", "get_start_offset", GET_START_OFFSET_HASH)

        private const val SET_MAX_RESULTS_HASH = 1286410249L
        @JvmField
        val setMaxResultsBind =
            ObjectCalls.getMethodBind("FuzzySearch", "set_max_results", SET_MAX_RESULTS_HASH)

        private const val GET_MAX_RESULTS_HASH = 3905245786L
        @JvmField
        val getMaxResultsBind =
            ObjectCalls.getMethodBind("FuzzySearch", "get_max_results", GET_MAX_RESULTS_HASH)

        private const val SET_MAX_MISSES_HASH = 1286410249L
        @JvmField
        val setMaxMissesBind =
            ObjectCalls.getMethodBind("FuzzySearch", "set_max_misses", SET_MAX_MISSES_HASH)

        private const val GET_MAX_MISSES_HASH = 3905245786L
        @JvmField
        val getMaxMissesBind =
            ObjectCalls.getMethodBind("FuzzySearch", "get_max_misses", GET_MAX_MISSES_HASH)

        private const val SET_USE_EXACT_TOKENS_HASH = 2586408642L
        @JvmField
        val setUseExactTokensBind =
            ObjectCalls.getMethodBind("FuzzySearch", "set_use_exact_tokens", SET_USE_EXACT_TOKENS_HASH)

        private const val GET_USE_EXACT_TOKENS_HASH = 36873697L
        @JvmField
        val getUseExactTokensBind =
            ObjectCalls.getMethodBind("FuzzySearch", "get_use_exact_tokens", GET_USE_EXACT_TOKENS_HASH)

        private const val SET_CASE_SENSITIVE_HASH = 2586408642L
        @JvmField
        val setCaseSensitiveBind =
            ObjectCalls.getMethodBind("FuzzySearch", "set_case_sensitive", SET_CASE_SENSITIVE_HASH)

        private const val GET_CASE_SENSITIVE_HASH = 36873697L
        @JvmField
        val getCaseSensitiveBind =
            ObjectCalls.getMethodBind("FuzzySearch", "get_case_sensitive", GET_CASE_SENSITIVE_HASH)

        private const val SET_FILTER_LOW_SCORES_HASH = 2586408642L
        @JvmField
        val setFilterLowScoresBind =
            ObjectCalls.getMethodBind("FuzzySearch", "set_filter_low_scores", SET_FILTER_LOW_SCORES_HASH)

        private const val GET_FILTER_LOW_SCORES_HASH = 36873697L
        @JvmField
        val getFilterLowScoresBind =
            ObjectCalls.getMethodBind("FuzzySearch", "get_filter_low_scores", GET_FILTER_LOW_SCORES_HASH)

        private const val SET_FILTER_FACTOR_HASH = 373806689L
        @JvmField
        val setFilterFactorBind =
            ObjectCalls.getMethodBind("FuzzySearch", "set_filter_factor", SET_FILTER_FACTOR_HASH)

        private const val GET_FILTER_FACTOR_HASH = 1740695150L
        @JvmField
        val getFilterFactorBind =
            ObjectCalls.getMethodBind("FuzzySearch", "get_filter_factor", GET_FILTER_FACTOR_HASH)

        private const val SET_FILTER_CUTOFF_HASH = 373806689L
        @JvmField
        val setFilterCutoffBind =
            ObjectCalls.getMethodBind("FuzzySearch", "set_filter_cutoff", SET_FILTER_CUTOFF_HASH)

        private const val GET_FILTER_CUTOFF_HASH = 1740695150L
        @JvmField
        val getFilterCutoffBind =
            ObjectCalls.getMethodBind("FuzzySearch", "get_filter_cutoff", GET_FILTER_CUTOFF_HASH)
    }
}
