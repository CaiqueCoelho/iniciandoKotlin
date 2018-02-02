package br.unifor.financaskotlin.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.unifor.financaskotlin.R
import br.unifor.financaskotlin.model.Transacao
import br.unifor.financaskotlin.view.adapter.item.TransacaoItem

class TransacoesAdapter(private var transacoes: List<Transacao>,
                        private val context: Context) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = convertView

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)
        }

        TransacaoItem.bind(view!!, getItem(position))

        return view

    }

    fun refresh(transacoes: List<Transacao>){
        this.transacoes = transacoes
        notifyDataSetChanged()
    }

    override fun getItem(position: Int) = transacoes[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = transacoes.size

}