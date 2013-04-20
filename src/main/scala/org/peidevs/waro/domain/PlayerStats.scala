
package org.peidevs.waro.domain

class PlayerStats(var total: Int, var numRoundsWon:Int, var numGamesWon:Int) {
    def winsRound(prizeCard:Int) = {
        numRoundsWon = numRoundsWon + 1
        total = total + prizeCard        
    }

    def winsGame() = {
        numGamesWon = numGamesWon + 1
    }
    
    def clear() = {
        total = 0
        numRoundsWon = 0
    }
    
    def emitLog:String = {
        "(" + total + " , " + numRoundsWon + " , " + numGamesWon + " )"
    }
}
