package br.unifor.financaskotlin.view.adapter.item

import android.view.View
import br.unifor.financaskotlin.extensions.formatoBrasileiro
import br.unifor.financaskotlin.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class TransacaoItem {

    companion object {

        fun bind(view: View,
                 transacao: Transacao){

            view.transacao_valor.text = transacao.valor.formatoBrasileiro()
            view.transacao_categoria.text = transacao.categoria
            view.transacao_data.text = transacao.data.formatoBrasileiro()

        }

    }

}