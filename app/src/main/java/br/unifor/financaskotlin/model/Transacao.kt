package br.unifor.financaskotlin.model

import java.math.BigDecimal
import java.util.*

class Transacao(var valor: BigDecimal = BigDecimal.ZERO,
                var categoria: String = "Indefinida",
                var tipo: Tipo,
                var data: Calendar = Calendar.getInstance())

enum class Tipo {
    RECEITA, DESPESA
}