package br.unifor.financaskotlin.view.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import br.unifor.financaskotlin.R
import br.unifor.financaskotlin.extensions.formatoBrasileiro
import br.unifor.financaskotlin.model.Tipo
import br.unifor.financaskotlin.model.Transacao
import br.unifor.financaskotlin.view.adapter.TransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes = sampleTransactions()

        setupTransactionsList(transacoes)

        setupSummary(transacoes)

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

        val builder = AlertDialog.Builder(this)

        val titleRes = if (tipo == Tipo.RECEITA) {
            R.string.receita
        } else {
            R.string.despesa
        }

        val decorView = window.decorView
        val view = LayoutInflater.from(this).inflate(R.layout.form_transacao, decorView as ViewGroup, false)

        val hoje = Calendar.getInstance()

        val categoriasRes = if (tipo == Tipo.RECEITA) {
            R.array.categorias_de_receita
        } else {
            R.array.categorias_de_despesa
        }

        val categoriasAdapter = ArrayAdapter.createFromResource(this, categoriasRes, android.R.layout.simple_spinner_dropdown_item)

        with(view.form_transacao_data) {
            setText(hoje.formatoBrasileiro(false))
            setOnClickListener {
                showDatePickerDialog(hoje, this)
            }
        }

        with(view.form_transacao_categoria) {
            adapter = categoriasAdapter
        }

        with(builder) {
            setTitle(titleRes)
            setView(view)
        }

        builder.show()

    }

    private fun showDatePickerDialog(calendar: Calendar, campo: EditText) {

        DatePickerDialog(this,
                { view, year, month, day ->
                    calendar.set(year, month, day)
                    campo.setText(calendar.formatoBrasileiro(false))
                }, calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH))
                .show()

    }

    private fun setupSummary(transacoes: List<Transacao>) {
        ResumoView(window.decorView, transacoes).refreshSummary()
    }

    private fun setupTransactionsList(transacoes: List<Transacao>) {
        val adapter = TransacoesAdapter(transacoes, this)
        lista_transacoes_listview.adapter = adapter
    }

    private fun sampleTransactions(): List<Transacao> {
        val transacoes = listOf(Transacao(BigDecimal(2.5), "Lanche", tipo = Tipo.DESPESA),
                Transacao(BigDecimal(200.38), "Noitada", tipo = Tipo.DESPESA),
                Transacao(BigDecimal(30.00), tipo = Tipo.RECEITA))
        return transacoes
    }

}