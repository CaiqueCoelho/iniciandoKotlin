package br.unifor.financaskotlin.model

import java.math.BigDecimal
import java.util.*

class Transacao (public var valor: BigDecimal,
                 public var categoria: String,
                 public var data: Calendar)