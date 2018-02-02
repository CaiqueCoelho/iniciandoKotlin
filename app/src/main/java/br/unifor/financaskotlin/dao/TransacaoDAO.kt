package br.unifor.financaskotlin.dao

import br.unifor.financaskotlin.model.Transacao

class TransacaoDAO {

    companion object {
        private val transacoes: MutableList<Transacao> = mutableListOf()
    }

    fun getTransacoes() = Companion.transacoes.toList()

    fun adiciona(transacao: Transacao){
        Companion.transacoes.add(transacao)
    }

    fun altera(transacao: Transacao, position: Int){
        Companion.transacoes[position] = transacao
    }

    fun remove(position: Int){
        Companion.transacoes.removeAt(position)
    }

}