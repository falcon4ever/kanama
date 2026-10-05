package net.multigesture.kanama.web

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Task 134 D1 review S1: a decimal crosses the Web text channels as the text of its IEEE-754 bits,
 * the form the proxy's `_kanama_web_float_text` writes (`str(decode_s64)` after `encode_double`)
 * and `_kanama_web_float` reads, so every double survives both ways -- the review's probe doubles
 * (random bit patterns, float32 values, -0, 1e-7, denormals) included, which decimal text did not
 * carry exactly (Godot's `String.to_float` misses about half; Kotlin/Wasm's `toDouble` rarely).
 */
class WebPackedFloatsTest {
  @Test
  fun encodesTheBits() {
    assertEquals("4612811918334230528", WebPackedFloats.encode(2.5))
    assertEquals("-9223372036854775808", WebPackedFloats.encode(-0.0))
    assertEquals("9218868437227405312", WebPackedFloats.encode(Double.POSITIVE_INFINITY))
    assertEquals(2.5, WebPackedFloats.decode("4612811918334230528"))
    assertEquals(Double.NEGATIVE_INFINITY, WebPackedFloats.decode("-4503599627370496"))
    assertTrue(WebPackedFloats.decode(WebPackedFloats.encode(Double.NaN)).isNaN())
  }

  @Test
  fun roundTripsEveryValueBitForBit() {
    val values =
      listOf(
        0.0,
        -0.0,
        1.0 / 3.0,
        1.0e-300,
        2.3e-308,
        Double.MIN_VALUE,
        3.4028234663852886e38,
        Double.MAX_VALUE,
        Double.NEGATIVE_INFINITY,
        0.1 + 0.2,
        9007199254740993.0,
      ) + REVIEW_PROBE_BITS.map(Double::fromBits)
    for (value in values) {
      assertEquals(
        value.toRawBits(),
        WebPackedFloats.decode(WebPackedFloats.encode(value)).toRawBits(),
        "$value",
      )
    }
  }

  private companion object {
    /**
     * The review probe's doubles (p6.gd, every 30th of its 3,000: random bit patterns and float32
     * values, then -0, 1e-7, 5e-324).
     */
    val REVIEW_PROBE_BITS =
      listOf(
        4779076360129740800L,
        5079618040992104448L,
        9057936583145953406L,
        -6456447569972953684L,
        -8428744897911116170L,
        -7849761963362304798L,
        1273859270594940518L,
        -2204440964245719229L,
        -7817088876386258190L,
        -43986577189202783L,
        -2733014319316953732L,
        -7241285478939348454L,
        3400470817486196988L,
        -3239758379627314868L,
        5644510508811385120L,
        -2107415900004082358L,
        5308266125612493976L,
        2954549755146991708L,
        -1051627503454969929L,
        -1890297182162599050L,
        8145683324011602094L,
        -8883651363647146294L,
        3462997038086757652L,
        -2080545519606903307L,
        -8975087409298810478L,
        404434773186349257L,
        -3264537910426245330L,
        -3861239823761056964L,
        -5878268534680695443L,
        -1391292838988829076L,
        6563631052217947964L,
        -7463025894950493054L,
        312216696569810789L,
        2626307245270542477L,
        -3918911092426344094L,
        5103489374174510820L,
        8946035223611753356L,
        6413235322828751137L,
        -4094067018007239467L,
        652747580360937655L,
        4491987981021486074L,
        -2307043696605005052L,
        3247999099762780267L,
        -7726316561817047034L,
        8213678593495961152L,
        -5549909391004467054L,
        -3449954134478799585L,
        665693076619454524L,
        -8558797005936573137L,
        -7562242942688034294L,
        -1458086564291117826L,
        603716247181449074L,
        -6129636762204644602L,
        5486450868588383014L,
        5105556637901723515L,
        -4788557962225072913L,
        2185185646795884543L,
        -1180844002467447559L,
        -4964887822470217728L,
        4510082930432802816L,
        4287986946036203520L,
        -4082292441552519168L,
        -4875124861409689600L,
        4625326219305418752L,
        4552673970402361344L,
        -4376703865664307200L,
        -4146976734773772288L,
        -4537916958305157120L,
        4815319122755715072L,
        -4245715474718916608L,
        4722708218273333248L,
        -4581783207887765504L,
        4589128713286713344L,
        -4078307267563225088L,
        2390901565421833433L,
        -235470748294043547L,
        1784338565423424010L,
        -6131714377842495644L,
        -2634496175283203246L,
        8117534349441814087L,
        6676547577392125369L,
        -4883848298108830608L,
        6610904634058993526L,
        -2559911407129311860L,
        7015475391210604773L,
        -2343074455483380591L,
        -215886433427071831L,
        4434502262297711028L,
        3797044194079808087L,
        -6222156871342514931L,
        -8345367806238729249L,
        -313388250435049893L,
        -4209983534640549063L,
        -8290921741154171855L,
        4887922432787157066L,
        3518161666987449159L,
        -5460744328377643876L,
        6540527020436563256L,
        3449332116088507685L,
        1446524287409917596L,
        5486092717653078024L,
        1172012418878059074L,
        4502148214488346440L,
      )
  }
}
