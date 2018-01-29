package br.unifor.financaskotlin.delegate

import br.unifor.financaskotlin.model.Transacao

interface TransacaoDelegate {

    fun transacaoCriada(transacao: Transacao)

}