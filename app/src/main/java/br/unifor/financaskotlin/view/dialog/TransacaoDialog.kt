package br.unifor.financaskotlin.view.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
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

    private val view = criaView()
    private var transacao: Transacao? = null

    private fun buildDialog(positiveListener: DialogInterface.OnClickListener, titleRes: Int, positiveRes: Int): AlertDialog.Builder {

        val builder = AlertDialog.Builder(context)

        builder.setPositiveButton(positiveRes, positiveListener)
        builder.setNegativeButton(R.string.cancelar, null)

        configuraValor()
        configuraData()
        configuraCategorias()
        configuraBuilder(builder, titleRes)

        return builder

    }

    fun showAdicionaTransacao(transacaoDelegate: TransacaoDelegate) {
        val titleRes = configuraTitulo(tipo)
        val positiveRes = R.string.adicionar
        val positiveListener = positiveListener(transacaoDelegate)
        buildDialog(positiveListener, titleRes, positiveRes).show()
    }

    fun showAlteraTransacao(transacao: Transacao, transacaoDelegate: TransacaoDelegate) {
        val titleRes = configuraTitulo(tipo, alteracao = true)
        val positiveRes = R.string.alterar
        val positiveListener = positiveListener(transacaoDelegate)
        this.transacao = transacao
        buildDialog(positiveListener, titleRes, positiveRes).show()
    }

    private fun positiveListener(transacaoDelegate: TransacaoDelegate): DialogInterface.OnClickListener {

        return DialogInterface.OnClickListener { _, _ ->

            val (categoriaText, valor, calendar) = converteInputs()
            val transacao = Transacao(valor = valor, data = calendar, categoria = categoriaText, tipo = tipo)

            transacaoDelegate.delegate(transacao)

        }

    }

    private fun criaView() =
            LayoutInflater.from(context).inflate(R.layout.form_transacao, viewGroup, false)

    private fun converteInputs(): Triple<String, BigDecimal, Calendar> {
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

    private fun configuraBuilder(builder: AlertDialog.Builder, titleRes: Int) {
        with(builder) {
            setTitle(titleRes)
            setView(view)
        }
    }

    private fun configuraValor() {

        val valorTransacao = transacao?.valor ?: BigDecimal.ZERO

        with(view.form_transacao_valor) {
            setText(String.format(valorTransacao.toString()))
        }

    }

    private fun configuraCategorias() {

        val categoriasRes = configuraCategoria(tipo)
        val categoriasAdapter = ArrayAdapter.createFromResource(context, categoriasRes, android.R.layout.simple_spinner_dropdown_item)

        val categoriaTransacao = transacao?.categoria
        val indexCategoria = categoriasAdapter.getPosition(categoriaTransacao)

        with(view.form_transacao_categoria) {
            adapter = categoriasAdapter
            setSelection(indexCategoria, true)
        }

    }

    private fun configuraData() {

        val dataTransacao = transacao?.data ?: Calendar.getInstance()

        with(view.form_transacao_data) {
            setText(dataTransacao.formatoBrasileiro(false))
            setOnClickListener {
                showDatePickerDialog(dataTransacao, this)
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
        return if (tipo == Tipo.RECEITA) {
            R.array.categorias_de_receita
        } else {
            R.array.categorias_de_despesa
        }
    }

    private fun configuraTitulo(tipo: Tipo, alteracao: Boolean = false): Int {
        return if (tipo == Tipo.RECEITA) {
            if (alteracao) R.string.altera_receita else R.string.adiciona_receita
        } else {
            if (alteracao) R.string.altera_despesa else R.string.adiciona_despesa
        }
    }

}