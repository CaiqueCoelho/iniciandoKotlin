package br.unifor.financaskotlin.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import br.unifor.financaskotlin.R
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

//Classe : Herança, Interface1, Interface2
class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        //Constante VAL, Variável VAR
        val transacoes = listOf("Compra 1 - R$100", "Compra 2 - R$35,00")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, transacoes)
        lista_transacoes_listview.adapter = arrayAdapter

    }

}