package br.unifor.financaskotlin.extensions

import java.text.SimpleDateFormat
import java.util.*

private val dateFormat = "dd/MM/yyyy hh:mm"

fun Calendar.formatoBrasileiro() : String {
    return SimpleDateFormat(dateFormat, Locale.getDefault()).format(this.time)
}

