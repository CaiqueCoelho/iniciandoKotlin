package br.unifor.financaskotlin.view.dialog

import android.content.Context
import android.view.ViewGroup
import br.unifor.financaskotlin.R
import br.unifor.financaskotlin.model.Tipo
import br.unifor.financaskotlin.model.Transacao

class TransacaoDialog(context: Context,
                      viewGroup: ViewGroup,
                      private val tipo: Tipo) : BaseTransacaoDialog(context, viewGroup, tipo) {

    override fun showAdicionaTransacao(delegate: (Transacao) -> Unit) {
        val titleRes = configuraTitulo(tipo)
        val positiveRes = R.string.adicionar
        buildDialog(delegate, titleRes, positiveRes)
    }

    override fun showAlteraTransacao(transacao: Transacao, delegate: (Transacao) -> Unit) {
        val titleRes = configuraTitulo(tipo, alteracao = true)
        val positiveRes = R.string.alterar
        this.transacao = transacao
        buildDialog(delegate, titleRes, positiveRes)
    }

}