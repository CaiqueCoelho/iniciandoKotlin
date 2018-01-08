package br.unifor.finanaskotlin.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.unifor.finanaskotlin.R

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
    }

}