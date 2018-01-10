package br.unifor.financaskotlin.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.unifor.financaskotlin.R
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

        //Constante VAL, Variável VAR
//        val transacoes = listOf("Compra 1 - R$100", "Compra 2 - R$35,00")

        val transacoes = listOf(Transacao(BigDecimal(2.5), "Lanche", Calendar.getInstance()),
                Transacao(BigDecimal(20.38), "Noitada", Calendar.getInstance()))

        val adapter = TransacoesAdapter(transacoes, this)

        lista_transacoes_listview.adapter = adapter

    }

}