package br.unifor.financaskotlin.view.adapter.item

import android.support.v4.content.ContextCompat
import android.view.View
import br.unifor.financaskotlin.extensions.formatoBrasileiro
import br.unifor.financaskotlin.extensions.trimTo
import br.unifor.financaskotlin.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class TransacaoItem {

    companion object {

        fun bind(view: View,
                 transacao: Transacao) {

            view.transacao_valor.text = transacao.valor.formatoBrasileiro()
            view.transacao_categoria.text = transacao.categoria.trimTo()
            view.transacao_data.text = transacao.data.formatoBrasileiro()

            val colorRes: Int = transacao.tipo.colorRes()
            val drawableRes: Int = transacao.tipo.iconRes()

            view.transacao_valor.setTextColor(ContextCompat.getColor(view.context, colorRes))
            view.transacao_icone.setBackgroundResource(drawableRes)

        }

    }

}