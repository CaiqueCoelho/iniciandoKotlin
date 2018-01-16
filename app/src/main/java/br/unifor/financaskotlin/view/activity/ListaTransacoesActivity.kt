package br.unifor.financaskotlin.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.unifor.financaskotlin.R
import br.unifor.financaskotlin.model.Tipo
import br.unifor.financaskotlin.view.adapter.TransacoesAdapter
import br.unifor.financaskotlin.model.Transacao
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

//Classe : Herança, Interface1, Interface2
class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes = listOf(Transacao(BigDecimal(2.5), "Lanche", tipo = Tipo.DESPESA),
                Transacao(BigDecimal(20.38), "Noitada", tipo = Tipo.DESPESA),
                Transacao(BigDecimal(30.00), tipo = Tipo.RECEITA))

        val adapter = TransacoesAdapter(transacoes, this)

        lista_transacoes_listview.adapter = adapter

    }

}