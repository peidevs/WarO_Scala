
package org.peidevs.waro.casino

import org.peidevs.waro.domain._

class Game {
    def playGame(numCards:Int, players:List[Player]):Player = {
        val dealer = new Dealer()
        val table = dealer.deal(numCards, players)
        dealer.play(table)
        val winner = determineWinner(table)
        return winner
    }
    
    // -- internal
    
    def determineWinner(table:Table):Player = {
        val kitty = table.kitty
        val players = table.players

        val winner = players.maxBy { p => p.playerStats.total }
        val max = winner.playerStats.total

        /*
        if (verbose) {
            players.each { p ->
                def stats = p.playerStats
                println "$p.name won $stats.numRoundsWon rounds with $stats.total"
            }                            
        }
        */

        winner.playerStats.numGamesWon += 1
        println("\nGame summary:")
        println("overall WINNER is: " + winner.name)
        
        return winner   
    }
    
}