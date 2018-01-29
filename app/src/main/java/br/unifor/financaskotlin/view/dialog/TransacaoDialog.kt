package br.unifor.financaskotlin.view.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import br.unifor.financaskotlin.R
import br.unifor.financaskotlin.delegate.TransacaoDelegate
import br.unifor.financaskotlin.extensions.formatoBrasileiro
import br.unifor.financaskotlin.extensions.mascaraBrasileira
import br.unifor.financaskotlin.model.Tipo
import br.unifor.financaskotlin.model.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class TransacaoDialog(private val context: Context,
                      private val viewGroup: ViewGroup,
                      private val tipo: Tipo) {

    fun showAdicionaTransacao(transacaoDelegate: TransacaoDelegate){

        val builder = AlertDialog.Builder(context)
        val view = criaView()

        builder.setPositiveButton(R.string.adicionar, positiveListener(view, transacaoDelegate))
        builder.setNegativeButton(R.string.cancelar, null)

        configuraData(view)
        configuraCategorias(view)
        configuraBuilder(builder, view)

        builder.show()

    }

    private fun positiveListener(view: View, transacaoDelegate: TransacaoDelegate): DialogInterface.OnClickListener {

        return DialogInterface.OnClickListener { _, _ ->

            val (categoriaText, valor, calendar) = converteInputs(view)
            val transacao = Transacao(valor = valor, data = calendar, categoria = categoriaText, tipo = tipo)

            transacaoDelegate.transacaoCriada(transacao)

        }

    }

    private fun criaView() =
            LayoutInflater.from(context).inflate(R.layout.form_transacao, viewGroup, false)

    private fun converteInputs(view: View): Triple<String, BigDecimal, Calendar> {
        val valorText = view.form_transacao_valor.text.toString()
        val dataText = view.form_transacao_data.text.toString()
        val categoriaText = view.form_transacao_categoria.selectedItem.toString()

        val valor = try {
            BigDecimal(valorText)
        } catch (e: NumberFormatException) {
            Snackbar.make(viewGroup, R.string.valor_invalido, Snackbar.LENGTH_SHORT).show()
            BigDecimal.ZERO
        }

        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat(calendar.mascaraBrasileira(), Locale.getDefault())
        calendar.time = simpleDateFormat.parse(dataText)
        return Triple(categoriaText, valor, calendar)
    }

    private fun configuraBuilder(builder: AlertDialog.Builder, view: View?) {
        val titleRes = configuraTitulo(tipo)
        with(builder) {
            setTitle(titleRes)
            setView(view)
        }
    }

    private fun configuraCategorias(view: View) {

        val categoriasRes = configuraCategoria(tipo)
        val categoriasAdapter = ArrayAdapter.createFromResource(context, categoriasRes, android.R.layout.simple_spinner_dropdown_item)

        with(view.form_transacao_categoria) {
            adapter = categoriasAdapter
        }

    }

    private fun configuraData(view: View) {
        val hoje = Calendar.getInstance()
        with(view.form_transacao_data) {
            setText(hoje.formatoBrasileiro(false))
            setOnClickListener {
                showDatePickerDialog(hoje, this)
            }
        }
    }

    private fun showDatePickerDialog(calendar: Calendar, campo: EditText) {

        DatePickerDialog(context,
                { _, year, month, day ->
                    calendar.set(year, month, day)
                    campo.setText(calendar.formatoBrasileiro(false))
                }, calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH))
                .show()

    }

    private fun configuraCategoria(tipo: Tipo): Int {
        val categoriasRes = if (tipo == Tipo.RECEITA) {
            R.array.categorias_de_receita
        } else {
            R.array.categorias_de_despesa
        }
        return categoriasRes
    }

    private fun configuraTitulo(tipo: Tipo): Int {
        val titleRes = if (tipo == Tipo.RECEITA) {
            R.string.adiciona_receita
        } else {
            R.string.adiciona_despesa
        }
        return titleRes
    }

}