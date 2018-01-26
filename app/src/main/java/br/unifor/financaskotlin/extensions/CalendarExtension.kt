package br.unifor.financaskotlin.extensions

import java.text.SimpleDateFormat
import java.util.*

private val dateFormat = "dd/MM/yyyy"
private val timeFormat = "hh:mm"

fun Calendar.formatoBrasileiro(hora: Boolean = true): String {

    var pattern = dateFormat

    if (hora) {
        pattern += " $timeFormat"
    }

    return SimpleDateFormat(pattern, Locale.getDefault()).format(this.time)
}

