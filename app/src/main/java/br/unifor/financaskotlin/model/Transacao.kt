package br.unifor.financaskotlin.model

import java.math.BigDecimal
import java.util.*

class Transacao (var valor: BigDecimal,
                 var categoria: String,
                 var data: Calendar)