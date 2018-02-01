package br.unifor.financaskotlin.view.dialog

import android.content.Context
import android.view.ViewGroup
import br.unifor.financaskotlin.R
import br.unifor.financaskotlin.delegate.TransacaoDelegate
import br.unifor.financaskotlin.model.Tipo
import br.unifor.financaskotlin.model.Transacao

class TransacaoDialog(context: Context,
                      viewGroup: ViewGroup,
                      private val tipo: Tipo) : BaseTransacaoDialog(context, viewGroup, tipo) {

    override fun showAdicionaTransacao(transacaoDelegate: TransacaoDelegate) {
        val titleRes = configuraTitulo(tipo)
        val positiveRes = R.string.adicionar
        buildDialog(transacaoDelegate, titleRes, positiveRes)
    }

    override fun showAlteraTransacao(transacao: Transacao, transacaoDelegate: TransacaoDelegate) {
        val titleRes = configuraTitulo(tipo, alteracao = true)
        val positiveRes = R.string.alterar
        this.transacao = transacao
        buildDialog(transacaoDelegate, titleRes, positiveRes)
    }

}