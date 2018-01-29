package br.unifor.financaskotlin.extensions

import java.text.SimpleDateFormat
import java.util.*

private val brDateFormat = "dd/MM/yyyy"
private val brTimeFormat = "hh:mm"

fun Calendar.mascaraBrasileira(hora: Boolean = false): String {
    var pattern = brDateFormat
    if (hora) {
        pattern += " $brTimeFormat"
    }
    return pattern
}

fun Calendar.formatoBrasileiro(hora: Boolean = true): String {
    val pattern = mascaraBrasileira(hora)
    return SimpleDateFormat(pattern, Locale.getDefault()).format(this.time)
}

