package br.unifor.financaskotlin.model

import java.math.BigDecimal

class Resumo {

    fun totaliza(transacoes: List<Transacao>) : BigDecimal {

        var total = BigDecimal.ZERO

        for (transacao in transacoes) {
            total = total.plus(transacao.valor)
        }

        return total

    }

}