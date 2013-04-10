
package org.peidevs.waro.domain

class PlayerStats(var total: Int, var numRoundsWon:Int, var numGamesWon:Int) {
    def winsRound(prizeCard:Int) = {
        numRoundsWon = numRoundsWon + 1
        total = total + prizeCard        
    }
    
    def clear() = {
        total = 0
        numRoundsWon = 0
        numGamesWon = 0
    }
    
    def emitLog:String = {
        val result = "(" + total + " , " + numRoundsWon + " , " + numGamesWon + " )"
        return result
    }
}
