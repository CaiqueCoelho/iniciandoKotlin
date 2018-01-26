package br.unifor.financaskotlin.view.activity

import android.support.v4.content.ContextCompat
import android.view.View
import br.unifor.financaskotlin.extensions.formatoBrasileiro
import br.unifor.financaskotlin.extensions.sumByBigDecimal
import br.unifor.financaskotlin.model.Tipo
import br.unifor.financaskotlin.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(private val view: View,
                 private val transacoes: List<Transacao>) {

    fun refreshSummary() {

        val totalReceita = transacoes.filter { it.tipo == Tipo.RECEITA }.sumByBigDecimal { it.valor }
        val totalDespesa = transacoes.filter { it.tipo == Tipo.DESPESA }.sumByBigDecimal { it.valor }
        val totalGeral = totalReceita.subtract(totalDespesa)

        with(view.resumo_card_receita) {
            setTextColor(ContextCompat.getColor(view.context, Tipo.RECEITA.colorRes()))
            text = totalReceita.formatoBrasileiro()
        }

        with(view.resumo_card_despesa) {
            setTextColor(ContextCompat.getColor(view.context, Tipo.DESPESA.colorRes()))
            text = totalDespesa.formatoBrasileiro()
        }

        with(view.resumo_card_total) {

            val totalColorRes = corPorValor(totalGeral)

            view.resumo_card_total.setTextColor(ContextCompat.getColor(view.context, totalColorRes))
            text = totalGeral.formatoBrasileiro()

        }

    }

    private fun corPorValor(valor: BigDecimal): Int {
        return if (valor > BigDecimal.ZERO) {
            Tipo.RECEITA.colorRes()
        } else {
            Tipo.DESPESA.colorRes()
        }
    }

}