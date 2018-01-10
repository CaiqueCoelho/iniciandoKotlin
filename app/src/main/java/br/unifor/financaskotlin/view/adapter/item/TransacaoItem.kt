package br.unifor.financaskotlin.view.adapter.item

import android.view.View
import br.unifor.financaskotlin.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*
import java.util.*

class TransacaoItem(private val view: View,
                    private val transacao: Transacao) {

    fun bind(){
        view.transacao_valor.text = String.format(Locale.getDefault() , "%.2f", transacao.valor)
    }

}