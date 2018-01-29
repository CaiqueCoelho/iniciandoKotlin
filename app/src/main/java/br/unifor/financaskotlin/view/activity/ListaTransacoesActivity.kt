package br.unifor.financaskotlin.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.unifor.financaskotlin.R
import br.unifor.financaskotlin.delegate.TransacaoDelegate
import br.unifor.financaskotlin.model.Tipo
import br.unifor.financaskotlin.model.Transacao
import br.unifor.financaskotlin.view.adapter.TransacoesAdapter
import br.unifor.financaskotlin.view.dialog.TransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()
    private val transacoesAdapter = TransacoesAdapter(transacoes, this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        sampleTransactions()

        setupTransactionsList()

        setupSummary()

        setupFabs()

    }

    private fun setupFabs() {

        lista_transacoes_adiciona_receita.setOnClickListener {
            showAlert(Tipo.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            showAlert(Tipo.DESPESA)
        }

    }

    private fun showAlert(tipo: Tipo) {

        TransacaoDialog(this, main_layout, tipo)
                .showAdicionaTransacao(object : TransacaoDelegate {
                    override fun transacaoCriada(transacao: Transacao) {
                        lista_transacoes_adiciona_menu.close(true)
                        addTransaction(transacao)
                    }
                })

    }

    private fun addTransaction(transacao: Transacao) {
        transacoes.add(transacao)
        transacoesAdapter.notifyDataSetChanged()
        setupSummary()
    }

    private fun setupSummary() {
        ResumoView(window.decorView, transacoes).refreshSummary()
    }

    private fun setupTransactionsList() {
        lista_transacoes_listview.adapter = transacoesAdapter
    }

    private fun sampleTransactions() {

        val samples = listOf(Transacao(BigDecimal(2.5), "Lanche", tipo = Tipo.DESPESA),
                Transacao(BigDecimal(200.38), "Noitada", tipo = Tipo.DESPESA),
                Transacao(BigDecimal(30.00), tipo = Tipo.RECEITA))

        transacoes.addAll(samples)

    }

}