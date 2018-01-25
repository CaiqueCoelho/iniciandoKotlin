package br.unifor.financaskotlin.model

import br.unifor.financaskotlin.R
import java.math.BigDecimal
import java.util.*

class Transacao(var valor: BigDecimal = BigDecimal.ZERO,
                var categoria: String = "Indefinida",
                var tipo: Tipo,
                var data: Calendar = Calendar.getInstance())

enum class Tipo {

    RECEITA {
        override fun iconRes(): Int {
            return R.drawable.icone_transacao_item_receita
        }

        override fun colorRes(): Int {
            return R.color.receita
        }
    },

    DESPESA {
        override fun iconRes(): Int {
            return R.drawable.icone_transacao_item_despesa
        }

        override fun colorRes(): Int {
            return R.color.despesa
        }
    };

    abstract fun colorRes(): Int
    abstract fun iconRes(): Int

}