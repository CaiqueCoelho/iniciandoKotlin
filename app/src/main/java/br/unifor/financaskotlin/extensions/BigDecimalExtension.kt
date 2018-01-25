package br.unifor.financaskotlin.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

fun BigDecimal.formatoBrasileiro(): String {
    val currencyInstance = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
    return currencyInstance.format(this).replace("R$", "R$ ")
}