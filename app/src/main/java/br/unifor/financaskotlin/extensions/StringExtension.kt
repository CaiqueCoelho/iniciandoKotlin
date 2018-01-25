package br.unifor.financaskotlin.extensions

fun String.trimTo(limit: Int = 14): String {
    return if(this.length > 14){
        "${this.substring(0, limit)}..."
    }else{
        this
    }
}