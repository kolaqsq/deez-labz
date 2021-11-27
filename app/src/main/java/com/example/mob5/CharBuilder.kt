/*
Строитель — это порождающий паттерн проектирования,
который позволяет создавать объекты пошагово.

В отличие от других порождающих паттернов,
Строитель позволяет производить различные продукты,
используя один и тот же процесс строительства.
*/

package com.example.mob5

interface CharBuilder{
    fun setHp(hp:Int)
    fun setGuts(guts:Double)
    fun setWeight(weight:String)
    fun setPuppetHp(puppetHp:Int)
}

class RushBuilder():CharBuilder{
    var character: MutableList<Any> = mutableListOf()

    override fun setHp(hp:Int) {
        character.add(hp)
    }

    override fun setGuts(guts:Double) {
        character.add(guts)
    }

    override fun setWeight(weight:String) {
        character.add(weight)
    }

    override fun setPuppetHp(puppetHp: Int) {
    }

    fun getResult():MutableList<Any>{
        return character
    }
}

class PuppetBuilder():CharBuilder{
    var character: MutableList<Any> = mutableListOf()

    override fun setHp(hp:Int) {
        character.add(hp/2)
    }

    override fun setGuts(guts:Double) {
        character.add(guts)
    }

    override fun setWeight(weight:String) {
        character.add(weight)
    }

    override fun setPuppetHp(puppetHp: Int) {
        character.add(puppetHp)
    }

    fun getResult():MutableList<Any>{
        return character
    }
}

class CharStats() {
    fun StatsBundle(pattern:CharBuilder) {
        pattern.setHp(10000)
        pattern.setGuts(0.96)
        pattern.setWeight("light")
        pattern.setPuppetHp(3000)
    }
}

class App() {
    fun CharSelect() {
        val stats = CharStats()
        val rush = RushBuilder()
        val puppet = PuppetBuilder()

        stats.StatsBundle(rush)
        println(rush.getResult())

        stats.StatsBundle(puppet)
        println(puppet.getResult())
    }
}

fun main(){
    App().CharSelect()
}