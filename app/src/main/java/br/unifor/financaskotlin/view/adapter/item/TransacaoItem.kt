package br.unifor.financaskotlin.view.adapter.item

import android.support.v4.content.ContextCompat
import android.view.View
import br.unifor.financaskotlin.R
import br.unifor.financaskotlin.extensions.formatoBrasileiro
import br.unifor.financaskotlin.model.Tipo
import br.unifor.financaskotlin.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class TransacaoItem {

    companion object {

        fun bind(view: View,
                 transacao: Transacao) {

            view.transacao_valor.text = transacao.valor.formatoBrasileiro()
            view.transacao_categoria.text = transacao.categoria
            view.transacao_data.text = transacao.data.formatoBrasileiro()

            when (transacao.tipo) {

                Tipo.RECEITA -> {
                    view.transacao_valor.setTextColor(ContextCompat.getColor(view.context, R.color.receita))
                    view.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_receita)
                }

                Tipo.DESPESA -> {
                    view.transacao_valor.setTextColor(ContextCompat.getColor(view.context, R.color.despesa))
                    view.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_despesa)
                }

            }

        }

    }

}