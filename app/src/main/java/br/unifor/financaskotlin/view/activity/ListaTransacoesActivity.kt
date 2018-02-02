package br.unifor.financaskotlin.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import br.unifor.financaskotlin.R
import br.unifor.financaskotlin.model.Tipo
import br.unifor.financaskotlin.model.Transacao
import br.unifor.financaskotlin.view.adapter.TransacoesAdapter
import br.unifor.financaskotlin.view.dialog.TransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val idRemoveContextButton: Int = 1;

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

        //Implementação da interface do kotlin
//        TransacaoDialog(this, viewActivity, tipo)
//                .showAdicionaTransacao(object : TransacaoDelegate {
//                    override fun delegate(transacao: Transacao) {
//                        lista_transacoes_adiciona_menu.close(true)
//                        addTransaction(transacao)
//                    }
//
//                })

        //Implementação utilizando High Order Function / HOF
        TransacaoDialog(this, viewActivity, tipo)
                .showAdicionaTransacao {
                    lista_transacoes_adiciona_menu.close(true)
                    addTransaction(it)
                }


    }

    private fun showAlertAlteraTransacao(transacao: Transacao, position: Int) {

        TransacaoDialog(this, viewActivity, transacao.tipo)
                .showAlteraTransacao(transacao) { transacaoAlterada ->
                    updateTransaction(position, transacaoAlterada)
                }

    }

    private fun addTransaction(transacao: Transacao) {
        transacoes.add(transacao)
        transacoesAdapter.notifyDataSetChanged()
        setupSummary()
    }

    private fun removeTransaction(position: Int) {
        transacoes.removeAt(position)
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

        with(lista_transacoes_listview) {
            adapter = transacoesAdapter
            setOnItemClickListener { _, _, position, _ ->
                showAlertAlteraTransacao(transacoes[position], position)
            }

            setOnCreateContextMenuListener { contextMenu, _, _ ->
                contextMenu.add(Menu.NONE, idRemoveContextButton, Menu.NONE, R.string.remover)
            }

        }

    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {

        item?.let {
            when(it.itemId){
                idRemoveContextButton -> {
                    val contextMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
                    removeTransaction(contextMenuInfo.position)
                    return true
                }
                else -> super.onContextItemSelected(item)
            }
        }

        return super.onContextItemSelected(item)

    }

}