/*
Наблюдатель — это поведенческий паттерн проектирования,
который создаёт механизм подписки,
позволяющий одним объектам следить и реагировать на события,
происходящие в других объектах.
*/

package com.example.mob5

interface Message{
    fun getMessage(user: User, command: String)
}

class User(login:String): Message{
    val login = login
    override fun getMessage(user: User, command: String) {
        println("Игрок ${user.login} сделал ход $command")
    }
}

class GameServer(){
    private var playerList = mutableListOf<User>()

    fun subscribe(player:User){
        playerList.add(player)
    }

    fun unsubscribe(player:User){
        playerList.remove(player)
    }

    fun notify(user:User, command:String){
        for (i in 1 .. playerList.size){
            if (playerList[i-1] != user)
                playerList[i-1].getMessage(user, command)
        }
    }
}

class ChessGameLog(){
    private val amogus = User("Виктор Петрович")
    private val sugoma = User("Петр Викторович")

    fun onCreate(){
        val log = GameServer()
        log.subscribe(amogus)
        log.subscribe(sugoma)
        log.notify(amogus,"h2-h4")
        log.notify(sugoma,"a1-a5")
        log.notify(amogus,"f4-d3")
        println("Пробная версия завершена")
        log.unsubscribe(amogus)
        log.unsubscribe(sugoma)
    }
}

fun main(){
    ChessGameLog().onCreate()
}