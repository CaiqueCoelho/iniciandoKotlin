package br.unifor.financaskotlin.view.activity

import android.support.v4.content.ContextCompat
import android.view.View
import br.unifor.financaskotlin.extensions.formatoBrasileiro
import br.unifor.financaskotlin.model.Resumo
import br.unifor.financaskotlin.model.Tipo
import br.unifor.financaskotlin.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*

class ResumoView(private val view: View,
                 private val transacoes: List<Transacao>) {

    fun refreshSummary() {

        val totalReceita = Resumo().totaliza(transacoes.filter { it.tipo == Tipo.RECEITA })
        val totalDespesa = Resumo().totaliza(transacoes.filter { it.tipo == Tipo.DESPESA })
        val totalGeral = totalReceita.subtract(totalDespesa)

        view.resumo_card_receita.setTextColor(ContextCompat.getColor(view.context, Tipo.RECEITA.colorRes()))
        view.resumo_card_receita.text = totalReceita.formatoBrasileiro()

        view.resumo_card_despesa.setTextColor(ContextCompat.getColor(view.context, Tipo.DESPESA.colorRes()))
        view.resumo_card_despesa.text = totalDespesa.formatoBrasileiro()

        view.resumo_card_total.text = totalGeral.formatoBrasileiro()

    }

}