/*
Строитель — это порождающий паттерн проектирования, который позволяет создавать объекты пошагово.

В отличие от других порождающих паттернов, Строитель позволяет производить различные продукты, используя один и тот же процесс строительства.
*/

package com.example.mob5

interface CharBuilder{
    fun setHp(hp:String)
    fun setGuts(guts:String)
    fun setWeight(weight:String)
}

class MayBuilder():CharBuilder{
    var character: MutableList<String> = mutableListOf()

    override fun setHp(hp:String) {
        character.add("11000")
    }

    override fun setGuts(guts:String) {
        character.add("0.95")
    }

    override fun setWeight(weight:String) {
        character.add("light")
    }

    fun getResult():MutableList<String>{
        return character
    }
}

class SolBuilder():CharBuilder{
    var character: MutableList<String> = mutableListOf()

    override fun setHp(hp:String) {
        character.add("15000")
    }

    override fun setGuts(guts:String) {
        character.add("1.05")
    }

    override fun setWeight(weight:String) {
        character.add("heavy")
    }

    fun getResult():MutableList<String>{
        return character
    }
}

fun CharSelect(charBuilder:CharBuilder) {
    // TODO: 27.11.2021
}

fun main(){
    CharSelect(MayBuilder())
    CharSelect(SolBuilder())
}