
package org.peidevs.waro.domain

import org.peidevs.waro.strategy._

class Player(val name:String, val maxCard:Int, val strategy:Strategy,
                var hand:List[Int], val playerStats:PlayerStats) {
    
    def this(name:String, maxCard:Int, strategy:Strategy) = {
        this(name, maxCard, strategy, List(), new PlayerStats(0,0,0))
    }    

    def this(name:String, maxCard:Int, strategy:Strategy, hand:List[Int]) = {
        this(name, maxCard, strategy, hand, new PlayerStats(0,0,0))
    }    
    
    def getBid(prizeCard:Int):Bid = {

        val offer = strategy.selectCard(prizeCard, hand, maxCard)
        hand = hand diff List(offer)        
        new Bid(offer, this)
    }
    
    def clear():Unit = {
        hand = List()
        playerStats.clear()
    }
    
    def winsRound(prizeCard:Int):Unit = {
        playerStats.winsRound(prizeCard)
    }
    
    def winsGame():Unit = {
        playerStats.winsGame()
    }
    
    def emitLog():String = {
        name + " " + playerStats.emitLog
    }
    
}
