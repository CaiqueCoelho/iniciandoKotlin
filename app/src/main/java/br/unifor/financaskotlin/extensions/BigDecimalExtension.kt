package br.unifor.financaskotlin.extensions

import java.math.BigDecimal
import java.util.*

private val mask = "R$ %.2f"

fun BigDecimal.formatoBrasileiro(): String {
    return String.format(Locale.getDefault(), mask, this)
}