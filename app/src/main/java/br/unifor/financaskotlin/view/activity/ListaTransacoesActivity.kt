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

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()
    private val transacoesAdapter = TransacoesAdapter(transacoes, this)

    //You shold use lateinit when a var is required
    //private lateinit var viewActivity: View
    //Otherwise you can use "by lazy" with val!
    private val viewActivity by lazy { main_layout }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        setupTransactionsList()

        setupSummary()

        setupFabs()

    }

    private fun setupFabs() {

        lista_transacoes_adiciona_receita.setOnClickListener {
            showAlertAdicionaTransacao(Tipo.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            showAlertAdicionaTransacao(Tipo.DESPESA)
        }

    }

    private fun showAlertAdicionaTransacao(tipo: Tipo) {

        TransacaoDialog(this, viewActivity, tipo)
                .showAdicionaTransacao(object : TransacaoDelegate {
                    override fun delegate(transacao: Transacao) {
                        lista_transacoes_adiciona_menu.close(true)
                        addTransaction(transacao)
                    }

                })

    }

    private fun showAlertAlteraTransacao(transacao: Transacao, position: Int) {

        TransacaoDialog(this, viewActivity, transacao.tipo)
                .showAlteraTransacao(transacao, object : TransacaoDelegate {
                    override fun delegate(transacao: Transacao) {
                        updateTransaction(position, transacao)
                    }
                })

    }

    private fun addTransaction(transacao: Transacao) {
        transacoes.add(transacao)
        transacoesAdapter.notifyDataSetChanged()
        setupSummary()
    }

    private fun updateTransaction(position: Int, transacao: Transacao) {
        transacoes[position] = transacao
        transacoesAdapter.notifyDataSetChanged()
        setupSummary()
    }

    private fun setupSummary() {
        ResumoView(viewActivity, transacoes).refreshSummary()
    }

    private fun setupTransactionsList() {

        lista_transacoes_listview.adapter = transacoesAdapter

        lista_transacoes_listview.setOnItemClickListener { _, _, position, _ ->
            showAlertAlteraTransacao(transacoes[position], position)
        }

    }

}