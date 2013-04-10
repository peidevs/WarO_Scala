
package org.peidevs.waro.domain

class Player(val name:String, var hand:List[Int], val playerStats:PlayerStats) {
    
    def this(name:String) {
        this(name, List(), new PlayerStats(0,0,0))
    }    

    def this(name:String, hand:List[Int]) {
        this(name, hand, new PlayerStats(0,0,0))
    }    
    
    def getBid(prizeCard:Int):Bid = {

        val offer = hand(0)
        val bid = new Bid(offer, this)
        hand = hand diff List(offer)        
        
        return bid
    }
    
    def clear() = {
        hand = List()
        playerStats.clear()
    }
    
    def winsRound(prizeCard:Int) = {
        playerStats.winsRound(prizeCard)
    }
    
    def emitLog():String = {
        val log:String = playerStats.emitLog
        val result:String = name + " " + log
        return result
    }
    
}