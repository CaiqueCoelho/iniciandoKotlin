package br.unifor.financaskotlin.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

fun BigDecimal.formatoBrasileiro(): String {
    val currencyInstance = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
    return currencyInstance.format(this)
            .replace("R$", "R$ ")
            .replace("-R$ ", "R$ -")
}

/**
 * Returns the sum of all values produced by [selector] function applied to each element in
 * the collection.
 */
inline fun <T> Iterable<T>.sumByBigDecimal(selector: (T) -> BigDecimal): BigDecimal {
    var sum: BigDecimal = BigDecimal.ZERO
    for (element in this) {
        sum += selector(element)
    }
    return sum
}