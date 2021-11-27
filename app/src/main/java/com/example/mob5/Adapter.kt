/*
Адаптер — это структурный паттерн проектирования,
который позволяет объектам с несовместимыми
интерфейсами работать вместе.
*/

package com.example.mob5

class MarcoV1(encoding: String, separator: Char){
    private val encode = encoding
    private val separate = separator

    fun getEncoding():String{
        return encode
    }
    fun getSeparator():Char{
        return separate
    }
}

class MarcoV2(Config: MutableList<Any>){
    private val conf = Config

    fun getConfigs():MutableList<Any>{
        return conf
    }
}

class AdapterV1V2(macroV1: MarcoV1) {
    private val v1 = macroV1

    fun getConfig():MutableList<Any>{
        return mutableListOf(v1.getEncoding(), v1.getSeparator())
    }
}

fun main(){
    val myConfigOnV1 = MarcoV1("unicode-32", ';')

    val adapter = AdapterV1V2(myConfigOnV1)

    val myConfigOnV2 = MarcoV2(adapter.getConfig())
    println(myConfigOnV2.getConfigs())
}